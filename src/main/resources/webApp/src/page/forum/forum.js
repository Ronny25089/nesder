import * as router from "/src/router/router.js";
import * as tools from "/src/utils/tools.js";
import * as channel from "/src/page/forum/channel/channel.js";

//模块初始化
export default () => {
  // 获得当前频道ID
  let forum_id = sessionStorage.getItem('forum');
  getChannel(`forum/${forum_id}`);
};

//请求后台，取得相对应的频道列表
export const getChannel = (forum_path) => {
  tools.ajax({
    url: `${HOST}/nesder/channel/get/${forum_path}`,
    type: "GET",
    success: response => {
      //频道的初始值为 第一个角标的id
      sessionStorage.setItem('channel', response.data[0].channel_id)

      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#channel");
      response.data.forEach((item, index) => {
        let dom = `
          <a href="#/${forum_path}/channel/${item.channel_id}" 
            class="list-group-item list-group-item-action border border-d-blue mb-2 ${index == 0 ? 'active' : ''}">
            ${item.channel_name}
          </a>`;
        channelEle.innerHTML += dom;
      });

      $('.list-group-item').click((e) => {
        $('.list-group-item.active').removeClass('active');
        $(e.target).addClass('active');
      });
    },
    fail: status => {
      // 此处为请求失败后的代码
      console.log(status);
    },
  });

  if (!location.hash.match('post')) {
    // 初始化channel区域  
    router.render("#routeView-sub", "channel");
  }
  
}