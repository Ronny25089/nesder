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
    module: "../js/page/home.js",
  },
  {
    // 板块
    path: "forum",
    component: "page/forum.html",
    module: "../js/page/forum.js",
    children: [
      {
        //频道
        path: "channel",
        component: "page/channel.html",
        module: "../js/page/channel.js",
      },
      {
        //详细内容
        path: "details",
        component: "page/details.html",
        module: "../js/page/details.js",
      },
    ],
  },
  {
    //个人中心
    path: "personal",
    component: "page/personal.html",
    module: "../js/page/personal.js",
    children: [
      {
        //动态
        path: "news",
        component: "page/news.html",
        module: "../js/page/news.js",
      },
      {
        //回复
        path: "answers",
        component: "page/answers.html",
        module: "../js/page/answers.js",
      },
      {
        //收藏
        path: "collections",
        component: "page/collections.html",
        module: "../js/page/collections.js",
      },
      {
        //文章
        path: "posts",
        component: "page/posts.html",
        module: "../js/page/posts.js",
      },
      {
        //浏览记录
        path: "browseRecords",
        component: "page/browseRecords.html",
        module: "../js/page/browseRecords.js",
      },
      {
        //关系圈
        path: "following",
        component: "page/following.html",
        module: "../js/page/following.js",
      },
    ],
  },
  {
    //404
    path: "404",
    component: "page/404.html",
    module: "../js/page/notFound.js",
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
    window.referrer = "";
    return;
  }

  // 当referrer存在时只渲染最后一个节点的视图
  // 否则视为第一次访问，渲染所有匹配到的节点
  if (window.referrer !== "") {
    // 只留最后一个节点
    routerList.slice(-1).pop();
  }

  // 渲染routerList下的所有节点
  for (const router of routerList) {
    // 获得该节点的路由视图
    let routerView = document.querySelector(router.routerView);
    // 渲染视图
    routerView.innerHTML = getPageComponent(router.component);

    try {
      // 提取参数(「路由名/XXXX/」通过动态正则表达式(/{路由名}}\/(\w*)/)，获得XXXX)
      // 存入视图中
      routerView.param = location.hash.match(`${router.path}\\/(\\d*)`)[1];
    } catch (e) {
      //无视匹配不到参数的错误，不作处理，即不存储该值
    }

    // 初始化该视图的JS模块
    import(router.module).then((module) => {
      module.default();
    });

    // 初始化滚动条位置
    $(window).scrollTop(0);
  }

  //将当前hash，保存到referrer中
  window.referrer = location.hash;
}

/**
 * 跳转路由
 * @param path 目标路由
 */
export function goto(path) {
  location.hash = path;
}

/**
 * 递归查找所有节点
 * @param currentList 当前操作List/上回
 * @param resultList 匹配成功List
 * @param routerView 每个节点对应的路由视图id
 * @returns List<router>
 */
function matchRouter(currentList, resultList, routerView) {
  resultList = resultList === undefined ? [] : resultList;
  routerView = routerView === undefined ? "#routeView" : routerView;
  // 当前的子节点
  let childrenList = [];

  // 遍历当前次节点
  for (let router of currentList) {
    // 通过当前URL中的hash值，匹配路由
    if (location.hash.match(router.path)) {
      router.routerView = routerView;
      resultList.push(router);

      // 未匹配上，且有子节点的保留起来
    } else if (router.children !== undefined) {
      childrenList = childrenList.concat(router.children);
    }
  }

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
/********************路由********************/
