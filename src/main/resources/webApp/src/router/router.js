import * as tools from "/src/utils/tools.js";

/********************路由********************/
export default () => {
  // 页面初始化时，不会出发hashchange事件
  // 这里主动触发一次 hashchange 事件
  window.addEventListener("DOMContentLoaded", hashGo);
  // 监听路由变化
  window.addEventListener("hashchange", hashGo);

  // 初始化上一页（referrer）参数
  window.referrer = "";
};

/**
 * 路由导航
 * 第一级路由加载到「#routeView」
 * 第二级路由加载到「#routeView-sub」，以此类推
 */
const Routers = [
  {
    //主页
    path: "home",
    component: "src/page/home/home.html",
    moduleJs: "/src/page/home/home.js",
  },
  {
    // 板块
    path: "forum",
    component: "src/page/forum/forum.html",
    moduleJs: "/src/page/forum/forum.js",
    children: [
      {
        //频道
        path: "channel",
        component: "src/page/forum/channel/channel.html",
        moduleJs: "/src/page/forum/channel/channel.js",
      },
      {
        //文章详细
        path: "post",
        component: "src/page/forum/post/post.html",
        moduleJs: "/src/page/forum/post/post.js",
      },
    ],
  },
  {
    //个人中心
    path: "personal",
    component: "src/page/personal/personal.html",
    moduleJs: "/src/page/personal/personal.js",
    children: [
      {
        //动态
        path: "news",
        component: "src/page/personal/news/news.html",
        moduleJs: "/src/page/personal/news/news.js",
      },
      {
        //回复
        path: "answers",
        component: "src/page/personal/answers/answers.html",
        moduleJs: "/src/page/personal/answers/answers.js",
      },
      {
        //收藏
        path: "collections",
        component: "src/page/personal/collections/collections.html",
        moduleJs: "/src/page/personal/collections/collections.js",
      },
      {
        //文章
        path: "posts",
        component: "src/page/personal/posts/posts.html",
        moduleJs: "/src/page/personal/posts/posts.js",
      },
      {
        //浏览记录
        path: "browseRecords",
        component: "src/page/personal/browseRecords/browseRecords.html",
        moduleJs: "/src/page/personal/browseRecords/browseRecords.js",
      },
      {
        //关系圈
        path: "following",
        component: "src/page/personal/following/following.html",
        moduleJs: "/src/page/personal/following/following.js",
      },
    ],
  },
  {
    //404
    path: "404",
    component: "src/page/404/notFound.html",
    moduleJs: "/src/page/404/notFound.js",
  },
];

// 当路由发生变化时，根据路由渲染对应区域的UI
const hashGo = (evet) => {
  // 当空路径的时候默认指向home
  if (location.hash == "") {
    location.hash = "home";
    return;
  }

  // 递归匹配所有路由节点
  let routerList = matchRouter(Routers);

  // 未匹配到路由
  if (routerList.length == 0) {
    location.hash = "/404";
    return;
  }

  // 渲染routerList下的所有节点
  for (const router of routerList) {
    //初始化全面渲染视图
    initRender(router.routerView, router);
  }

  // 初始化滚动条位置
  let containerTop = document.querySelector(".container.pt-3").offsetTop;
  let navHeight = document.querySelector(".navbar").clientHeight;
  $(window).scrollTop(containerTop - navHeight);

  //将当前hash，保存到referrer中
  window.referrer = location.hash;
}

/**
 * 递归查找所有节点
 * @param currentList 当前操作List/上回
 * @param resultList 匹配成功List
 * @param routerView 每个节点对应的路由视图id
 * @returns List<router> 返回该hash值所能匹配到的所有路由节点
 */
const matchRouter = (currentList, resultList, routerView) => {
  // 初次清空
  resultList = resultList === undefined ? [] : resultList;
  // 初次为「routeView」
  routerView = routerView === undefined ? "#routeView" : routerView;

  // 初始化当前的子节点
  let childrenList = [];

  // 遍历当前节点
  for (let router of currentList) {
    // 通过当前URL中的hash值，匹配路由
    if (location.hash.match(router.path)) {
      router.routerView = routerView;
      resultList.push(router);
    }
    // 有子节点的保留起来
    if (router.children !== undefined) {
      childrenList = childrenList.concat(router.children);
    }
  }

  // 还有子节点的情况下，继续递归
  if (childrenList.length > 0) {
    routerView += "-sub";
    // 递归再查找
    return matchRouter(childrenList, resultList, routerView);
  }
  
  // 当referrer存在时只渲染最后一个节点的视图
  // 否则视为第一次访问，渲染所有匹配到的节点
  if (window.referrer !== "") {
    // 只留最后一个节点
    resultList = resultList.slice(resultList.length -1);
  }
  return resultList;
}

/**
 * 初始化渲染视图
 * @param routerView 目标路由视图区域
 * @param router 目标路由对象
 * @param setValueFlg 
 */
const initRender = (routerView, router, setValueFlg) => {
  // 获得目标视图
  let routerTag = document.querySelector(routerView);

  // 渲染视图
  routerTag.innerHTML = getPageComponent(router.component);

  try {
    if (!setValueFlg) {
      // 提取参数(「路由名/XXXX/」通过动态正则表达式(/{路由名}}\/(\w*)/)，获得XXXX)
      // 存入session
      let value = location.hash.match(`${router.path}\\/(\\d*)`)[1];
      sessionStorage.setItem(router.path, value);
    }
  } catch (e) {
    //匹配不到参数的错误
    let key = router.path.match('home') ? 'forum' : router.path;
    sessionStorage.setItem(key, '');
  }

  // 初始化该视图的JS模块
  import(router.moduleJs).then((module) => {
    //执行 初始化视图的逻辑代码
    module.default(module);

    //获得该模块里 所有的导出函数
    for (var functionName in module) {
      if (functionName === 'default') continue;

      //将default 之外的函数方法设为全局函数
      //通过函数名获得该函数实体
      let method = Object.getOwnPropertyDescriptor(module,functionName).value
      
      //在window对象上定义一个新属性，
      //属性名为该函数名，将值设为该函数实体
      //目的是为了，能在画面被渲染后，在各个module里写的function也能被调用到
      Object.defineProperty(window, functionName, {
        value: method
      });
    }
    
  });
}

/**
 * 获取目标页面的html内容
 * @param pageUrl 目标路径
 * @returns String HTML文本
 */
const getPageComponent = pageUrl => {
  let result;
  tools.ajax({
    url: pageUrl,
    type:'GET',
    dataType: "text",
    async: false, //关闭异步处理，获得response：data，返回给外部
    success: data => {
      result = data;
    },
  });
  return result;
}

/**
 * 提供给外部使用的视图初始化方法
 * @param routerView 目标路由视图区域
 * @param path 目标路径
 * @param resultList 递归查找用list
 */
export const render = (routerView, path, resultList) => {
  // 初次为Routers路由导航
  resultList = resultList === undefined ? Routers : resultList;

  // 初始化当前的子节点
  let childrenList = [];

  // 遍历当前节点
  for (let router of resultList) {
    // 匹配路由
    if (path.match(router.path)) {
      //初始化全面渲染视图
      initRender(routerView, router, true);
      return;

    // 不匹配，且有子节点的保留起来
    } else if (router.children !== undefined) {
      childrenList = childrenList.concat(router.children);
    }
  }

  // 未找到且还有子节点的情况下，继续递归
  if (childrenList.length > 0) {
    render(routerView, path, childrenList);
  }
  return;
}

export const getCurrentPage = () => {
  return location.hash;
} 

/**
 * 跳转路由
 * @param path 目标路由
 */
export const goto = (path) => {
  location.hash = path;
}
/********************路由********************/