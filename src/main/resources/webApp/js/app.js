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

  let href = $(this).attr("href");
  let moduleJs = href.replace("html", "js");

  $("#theModal")
    .modal("show")
    .find(".modal-content")
    .load(href, function (e) {

        //初始化
        import(`../js/${moduleJs}`).then((module) => {
          module.default();
          // 将sign对象的signIn()函数，暴露给window对象
          window.modal = module;
        });
    });
});

function getForum() {
  commonTools.ajax({
    url: "/nesder/forum/all",
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let forumEle = document.querySelector("#forum");
      response.data.forEach((item, index) => {
        let dom =
          `<li class="nav-item border-left border-success">
            <a class="nav-link mx-3" href="#/forum/${item.forum_id}">
              ${item.mname}
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
