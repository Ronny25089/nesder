/********************路由********************/
export default() => {
  // 页面加载完不会触发 hashchange，这里主动触发一次 hashchange 事件
  window.addEventListener('DOMContentLoaded', onHashChange);
  // 监听路由变化
  window.addEventListener('hashchange', onHashChange);

  let routerView = document.querySelector('#routeView');
  routerView.data = {
    moduleJs:''
  };
}

// 路由变化时，根据路由渲染对应 UI
function onHashChange(evet) {
  // 路由视图 
  let routerView = document.querySelector('#routeView');
  switch (location.hash) {
    case '#/home':
      routerView.innerHTML = getPageComponent('page/home.html');
      routerView.data.moduleJs = '../js/page/home.js';
      break;
    case '#/module_A':
      routerView.innerHTML = getPageComponent('page/module_A.html');
      routerView.data.moduleJs = '../js/page/module_A.js';
      break;
    default:
      routerView.innerHTML = getPageComponent('page/home.html');
      break;
  }
  // 初始化该视图
  import(routerView.data.moduleJs).then((module)=>{
    module.default();
});
  $(window).scrollTop(0);
}

// 获取目标页面的html内容，渲染到index.html上
function getPageComponent(pageUrl) {
  let result;
  $.ajax({
    url: pageUrl,
    dataType: 'text',
    async: false,//关闭异步处理，获得response：data，返回给外部
    success: function(data) {
      result = data;
    }
  });
  return result;
}
/********************路由********************/
