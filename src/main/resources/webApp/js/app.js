import * as Sign from "../js/page/sign.js";
import * as Binder from "../js/dataBinder.js";
import * as router from "../js/router.js";
import * as commonTools from "../js/commonTools.js";

getForum();
//启动路由
router.default();
// 将双向绑定对象，暴露给window对象,起到全局共享的作用
window.DBind = new Binder.DBind(1);

//　共通，点击打开modal，
//  目标路径为href所指定路径
$(".li-modal").on("click", function (e) {
  e.preventDefault();
  $("#theModal")
    .modal("show")
    .find(".modal-content")
    .load($(this).attr("href"), function () {
      // 当打开注册画面的时候
      if ("sign.html".indexOf($(this).attr("href"))) {
        //初始化
        Sign.default();
        // 将sign对象的signIn()函数，暴露给window对象
        window.signIn = Sign.signIn;
      }
    });
});

function getForum() {
  commonTools.ajax({
    url: "/nesder/module/all",
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let forumEle = document.querySelector("#forum");
      response.data.forEach((element, index) => {
        let dom =
          `<li class="nav-item border-left border-success">
            <a class="nav-link mx-3" href="#/forum/${element.module_id}">
              ${element.mname}
            </a>
          </li>`;
        forumEle.innerHTML += dom;
      });
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(response);
    },
  });
}
