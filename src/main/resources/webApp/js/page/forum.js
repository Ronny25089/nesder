import * as commonTools from "../commonTools.js";
import * as router from "../router.js";
import * as channel from "../page/channel.js";

//模块初始化
export default () => {
  getChannel();
  // 初始化channel区域
  router.render("#routeView-sub", "channel");
};

function getChannel() {
  let forum_id = document.querySelector("#routeView").param;
  commonTools.ajax({
    url: `/nesder/channel/get/${forum_id}`,
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#channel");
      let activeClass = `active`;
      response.data.forEach((item, index) => {
        if (index == 0 ) {
          document.querySelector("#routeView-sub").param = item.channel_id;
        }
        let dom = `
          <a href="#/forum/${forum_id}/channel/${item.channel_id}" 
            class="list-group-item list-group-item-action border border-success mt-3 ${activeClass}">
            ${item.name}
          </a>`;
        channelEle.innerHTML += dom;
        activeClass = ``;
      });

      $('.list-group-item').click(function(e) {
        $('.list-group-item.active').removeClass('active');
        $(this).addClass('active');
      });
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(response);
    },
  });
}
