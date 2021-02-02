import * as commonTools from "../commonTools.js";
import * as router from "../router.js";
import * as channel from "../page/channel.js";

//模块初始化
export default () => {
  // 获得当前频道ID
  let forum_id = document.querySelector("#routeView").param;
  getChannel(forum_id);
};


export function getChannel(forum_id) {
  commonTools.ajax({
    url: `/nesder/channel/get/${forum_id}`,
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#channel");
      document.querySelector("#routeView-sub").param = response.data[0].channel_id;
      response.data.forEach((item, index) => {
        let dom = `
          <a href="#/${forum_id ? `home`:`forum/${forum_id}`}/channel/${item.channel_id}" 
            class="list-group-item list-group-item-action border border-success mt-3 ${index == 0 ? 'active' : ''}">
            ${item.name}
          </a>`;
        channelEle.innerHTML += dom;
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

  // 初始化channel区域
  router.render("#routeView-sub", "channel");
}
