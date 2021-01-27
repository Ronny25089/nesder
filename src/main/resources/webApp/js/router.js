/********************路由********************/
export default () => {
  // 页面加载完不会触发 hashchange，这里主动触发一次 hashchange 事件
  window.addEventListener("DOMContentLoaded", hashGo);
  // 监听路由变化
  window.addEventListener("hashchange", hashGo);
};

//路由视图
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
    child: [
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
    child: [
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
];

// 路由变化时，根据路由渲染对应 UI
function hashGo(evet) {
  // 路由视图
  let routerView = document.querySelector("#routeView");

  // 路由不为空时，开始匹配
  if (location.hash != "") {
    // 递归匹配所有节点
    let router = matchRouter(Routers);
    // 渲染视图
    routerView.innerHTML = getPageComponent(router.component);
    // 提取参数(紧跟在该路由「path/」之后的就是该路由的参数)
    let param = location.hash.split(router.path + "/")[1];
    if (param !== undefined) {
      routerView.param = param;
    }
    // 初始化该视图的JS模块
    import(router.module).then((module) => {
      module.default();
    });
    // 初始化滚动条
    $(window).scrollTop(0);
  } else {
    location.hash = location.hash ? location.hash : "home";
  }
}

// 递归查找所有节点
function matchRouter(list) {
  // 该次子节点
  let childList = [];
  // 遍历该次节点
  for (let router of list) {
    // 匹配路由
    if (location.hash.match(router.path)) {
      return router;
      // 未匹配上，且有子节点的保留起来
    } else if (router.child !== undefined) {
      childList = childList.concat(router.child);
    }
  }
  // 递归再查找
  matchRouter(childList);
}

// 获取目标页面的html内容
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
