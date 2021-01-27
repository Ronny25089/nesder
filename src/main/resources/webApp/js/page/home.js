import * as commonTools from "../commonTools.js";

//模块初始化
export default () => {
  commonTools.ajax({
    url: "/nesder/channel/all",
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#channel");
      let activeClass = `list-group-item-success`;
      response.data.forEach((element, index) => {
        console.log(element);
        let dom = `
          <a href="#/channel/${element.channel_id}" 
            class="list-group-item list-group-item-action border border-success mt-3 ${activeClass}">
            ${element.name}
          </a>`;
        channelEle.innerHTML += dom;
        activeClass = ``;
      });
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(response);
    },
  });
};
