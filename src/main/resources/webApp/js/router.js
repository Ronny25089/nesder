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

//路由导航
const Routers = [
  {
    //主页
    path: "home",
    component: "page/home.html",
    moduleJs: "../js/page/home.js",
  },
  {
    // 板块
    path: "forum",
    component: "page/forum.html",
    moduleJs: "../js/page/forum.js",
    children: [
      {
        //频道
        path: "channel",
        component: "page/channel.html",
        moduleJs: "../js/page/channel.js",
      },
      {
        //详细内容
        path: "details",
        component: "page/details.html",
        moduleJs: "../js/page/details.js",
      },
    ],
  },
  {
    //个人中心
    path: "personal",
    component: "page/personal.html",
    moduleJs: "../js/page/personal.js",
    children: [
      {
        //动态
        path: "news",
        component: "page/news.html",
        moduleJs: "../js/page/news.js",
      },
      {
        //回复
        path: "answers",
        component: "page/answers.html",
        moduleJs: "../js/page/answers.js",
      },
      {
        //收藏
        path: "collections",
        component: "page/collections.html",
        moduleJs: "../js/page/collections.js",
      },
      {
        //文章
        path: "posts",
        component: "page/posts.html",
        moduleJs: "../js/page/posts.js",
      },
      {
        //浏览记录
        path: "browseRecords",
        component: "page/browseRecords.html",
        moduleJs: "../js/page/browseRecords.js",
      },
      {
        //关系圈
        path: "following",
        component: "page/following.html",
        moduleJs: "../js/page/following.js",
      },
    ],
  },
  {
    //404
    path: "404",
    component: "page/404.html",
    moduleJs: "../js/page/notFound.js",
  },
];

// 当路由发生变化时，根据路由渲染对应区域的UI
function hashGo(evet) {
  // 当空路径的时候默认指向home
  if (location.hash == "") {
    location.hash = "home";
    return;
  }

  // 递归匹配所有路由节点
  let routerList = matchRouter(Routers);

  // 未匹配到路由
  if (routerList.length == 0) {
    location.hash = "404";
    return;
  }

  // 当referrer存在时只渲染最后一个节点的视图
  // 否则视为第一次访问，渲染所有匹配到的节点
  if (window.referrer !== "") {
    // 只留最后一个节点
    routerList = routerList.slice(routerList.length -1);
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
 * 初始化全面渲染视图
 * @param routerView 目标路由视图区域
 * @param component 目标组件
 * @param moduleJs 目标JS模块
 */
function initRender(routerView, router) {
  // 获得目标视图
  let routerTag = document.querySelector(routerView);

  // 渲染视图
  routerTag.innerHTML = getPageComponent(router.component);

  try {
    // 提取参数(「路由名/XXXX/」通过动态正则表达式(/{路由名}}\/(\w*)/)，获得XXXX)
    // 存入视图中
    routerTag.param = location.hash.match(`${router.path}\\/(\\d*)`)[1];
  } catch (e) {
    //无视匹配不到参数的错误，不作处理，即不存储该值
  }

  // 初始化该视图的JS模块
  import(router.moduleJs).then((module) => {
    module.default();
  });
}

/**
 * 递归查找所有节点
 * @param currentList 当前操作List/上回
 * @param resultList 匹配成功List
 * @param routerView 每个节点对应的路由视图id
 * @returns List<router> 返回该hash值所能匹配到的所有路由节点
 */
function matchRouter(currentList, resultList, routerView) {
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
  } else {
    return resultList;
  }
}

/**
 * 获取目标页面的html内容
 * @param pageUrl 目标路径
 * @returns String HTML文本
 */
function getPageComponent(pageUrl) {
  let result;
  $.ajax({
    url: pageUrl,
    dataType: "text",
    async: false, //关闭异步处理，获得response：data，返回给外部
    success: function (data) {
      result = data;
    },
  });
  return result;
}

/**
 * 提供给外部使用的视图初始化方法
 * @param routerView 目标路由视图区域
 * @param component 目标路径
 * @param moduleJs 递归查找用list
 */
export function render(routerView, path, resultList) {
  // 初次为Routers路由导航
  resultList = resultList === undefined ? Routers : resultList;

  // 初始化当前的子节点
  let childrenList = [];

  // 遍历当前节点
  for (let router of resultList) {
    // 匹配路由
    if (path.match(router.path)) {
      //初始化全面渲染视图
      initRender(routerView, router);
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
}

/**
 * 跳转路由
 * @param path 目标路由
 */
export function goto(path) {
  location.hash = path;
}
/********************路由********************/