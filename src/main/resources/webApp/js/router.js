// 页面加载完不会触发 hashchange，这里主动触发一次 hashchange 事件
window.addEventListener('DOMContentLoaded', onLoad)
// 监听路由变化
window.addEventListener('hashchange', onHashChange)

// 路由视图
var routerView = null

function onLoad() {
  routerView = document.querySelector('#routeView')
  onHashChange()
}

// 路由变化时，根据路由渲染对应 UI
function onHashChange() {
  switch (location.hash) {
    case '#/home':
      routerView.innerHTML = getPageComponent('page/home.html')
      return
    case '#/module_A':
      routerView.innerHTML = getPageComponent('page/module_A.html')
      return
    default:
      routerView.innerHTML = getPageComponent('page/home.html')
      return
  }
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
