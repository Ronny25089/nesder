import * as Binder from "/src/utils/dataBinder.js";
import * as router from "/src/router/router.js";
import * as tools from "/src/utils/tools.js";

window.HOST = `${location.protocol}//${location.hostname}`;

getForum();
//启动路由
window.router = router;
router.default();
// 将双向绑定对象，暴露给window对象,起到全局共享的作用
window.DBind = new Binder.DBind(1);

//　共通，点击打开modal，
//  目标路径为href所指定路径
$(".li-modal").on("click", (e) => {
  e.preventDefault();

  let href = $(e.target).attr("href");

  $("#theModal")
    .modal("show")
    .find(".modal-content")
    .load(href, (e) => {

        //初始化
        import(href.replace("html", "js")).then((module) => {
          module.default();
          // 将sign对象的signIn()函数，暴露给window对象
          window.modal = module;
        });
    });
});

function getForum() {
  tools.ajax({
    url: `${HOST}/nesder/forum/all`,
    type: "GET",
    success: response => {
      //   此处执行请求成功后的代码
      let forumEle = document.querySelector("#forum");
      response.data.forEach((item, index) => {
        let dom =
          `<li class="nav-item border-left border-light">
            <a class="nav-link text-light mx-3" href="#/forum/${item.forum_id}">
              ${item.forum_name}
            </a>
          </li>`;
        forumEle.innerHTML += dom;
      });
    },
    fail: status => {
      // 此处为请求失败后的代码
      console.log(status);
    },
  });
}
