import * as commonTools from "../commonTools.js";

//模块初始化
export default () => {
  getAllChannel()
};

function getAllChannel() {
  commonTools.ajax({
    let channel_id = document.querySelector("#routeView-sub").param;
    url: `/nesder/details/${channel_id}`,
    type: "GET",
    success: function (response) {
      console.log(response);
      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#details");
      response.data.forEach((element, index) => {
        let dom = `
          <div class="card my-3">
            <div class="row">
              <div class="col-1 mx-1">
                <img src="avatar/Multiavatar-1.png" class="avatar-wrapper">
              </div>
              <div class="col-10 card-body">
                <h5 class="card-title">用户名</h5>
                <h6 class="card-subtitle mb-2 text-muted">用户标签</h6>
                <hr>
                <p class="card-text">因为疫情再起，为了不让掘友在假期太无聊，掘金酱准备了正在进行的技术专题，参加相应专题即可赢取相应的奖品哦！！！因为疫情再起，为了不让掘友在假期太无聊，掘金酱准备了正在进行的技术专题，参加相应专题即可赢取相应的奖品哦！！！</p>
                <small>YYYY/MM/DD hh:mm</small>
              </div>
            </div>
            <div class="btn-group btn-group-sm" role="group">
              <button type="button" class="btn btn-light text-success">
                <i class="bi bi-chat-left-text"></i>
                <small>XXX</small>
              </button>
              <button type="button" class="btn btn-light text-success border-left">
                <i class="bi bi-heart"></i>
                <small>XXX</small>
              </button>
              <button type="button" class="btn btn-light text-success border-left">
                <i class="bi bi-star"></i>
                <small>XXX</small>
              </button>
            </div>
          </div>`;
        channelEle.innerHTML += dom;
        activeClass = ``;
      });
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(response);
    },
  });
}