import * as commonTools from "../commonTools.js";

//模块初始化
export default () => {
  commonTools.ajax({
    url: "/nesder/channel/all",
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#channel");
      response.data.forEach((element, index) => {
        let changeEle =
          `<a href="#" class="list-group-item 
                                list-group-item-action 
                                border border-success 
                                mt-3 ` 
                                + (index == 0 ? `list-group-item-success">` : `">`)
                                + element.name +
          `</a>`;
        channelEle.innerHTML += changeEle;
      });
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(response);
    }
  });
};
