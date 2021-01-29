import * as commonTools from "../commonTools.js";

//模块初始化
export default () => {
  let channel_id = document.querySelector("#routeView-sub").param;
  getAllPost(channel_id);
};

export function getAllPost(channelId) {
  commonTools.ajax({
    url: `/nesder/details/get/${channelId}`,
    type: "GET",
    success: function (response) {
      //   此处执行请求成功后的代码
      let channelEle = document.querySelector("#details");
      response.data.forEach((item, index) => {
        let dom = `
          <div class="card my-3">
            <div class="row">
              <div class="col-1 mx-1">
                <img src="${item.created_account_avatarurl}" class="avatar-wrapper">
              </div>
              <div class="col-10 card-body">
                <h5 class="card-title">${item.created_account_nick_name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${item.title}</h6>
                <hr>
                <p class="card-text">${item.content}</p>
                <small>${item.create_date}</small>
              </div>
            </div>
            <div class="btn-group btn-group-sm" role="group">
              <button type="button" class="btn btn-light text-success">
                <i class="bi bi-chat-left-text"></i>
                <small>${item.replayCount}</small>
              </button>
              <button type="button" class="btn btn-light text-success border-left">
                <i class="bi bi-heart"></i>
                <small>${item.likesCount}</small>
              </button>
              <button type="button" class="btn btn-light text-success border-left">
                <i class="bi bi-star"></i>
                <small>${item.marksCount}</small>
              </button>
            </div>
          </div>`;
        channelEle.innerHTML += dom;
      });
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(response);
    },
  });
}