import * as Sign from '../js/page/sign.js';
import * as Binder from '../js/dataBinder.js'
import * as router from '../js/router.js'

//启动路由
router.default();
// 将双向绑定对象，暴露给window对象,起到全局共享的作用
window.DBind = new Binder.DBind(1);

//　共通，点击打开modal，
//  目标路径为href所指定路径
$('.li-modal').on('click', function(e) {
  e.preventDefault();
  $('#theModal').modal('show').find('.modal-content').load($(this).attr('href'),function(){

    // 当打开注册画面的时候
    if ("sign.html".indexOf($(this).attr('href'))) {
      //初始化
      Sign.default();
      // 将sign对象的signIn()函数，暴露给window对象
      window.signIn = Sign.signIn;
    }
  });
});
