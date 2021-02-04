import * as tools from "/src/utils/tools.js";

//模块初始化
export default () => {
    getPostDetail()
};

function getPostDetail() {
  let post_id = sessionStorage.getItem('post');
  tools.ajax({
    url: `${HOST}/nesder/details/getPostDetails/${post_id}`,
    type: "GET",
    success: function (response) {
      console.log(response);
      let container = document.querySelector("#details");
      let detailsModel = response.data.detailsModel;
      let replyDetailsModelList = response.data.replyDetailsModelList;
      let replyDom = "";
      //   此处执行请求成功后的代码
      replyDetailsModelList.forEach(item => {
        replyDom += `
          <div class="row reply">
              <div class="col-1 mx-1">
                  <img src="${HOST}/${item.created_account_avatarurl}" class="avatar-small ml-2 mt-2">
              </div>
              <div class="col-10 card-body">
                  <small class="float-right">${item.create_date}</small>
                  <h10 class="card-title">${item.created_account_nick_name}</h10>
                  <p class="card-text">${item.content}</p>
                  <div class="d-flex flex-row-reverse bd-highlight">
                    <span role="button" class="bi bi bi bi-chat-right-text ml-5" onclick="">
                      <small> 回复</small>
                    </span>
                    <span role="button" class="bi bi bi-heart-fill text-muted" onclick="">
                      <small> </small>
                    </span>
                  </div>
              </div>
          </div>`;
      });
      console.log(replyDom);

      let postDom = `
      <div class="card my-3 post-item">
            <div class="row">
              <div class="col-1">
                <img src="${HOST}/${detailsModel.created_account_avatarurl}" class="avatar-wrapper ml-2 mt-2">
              </div>
              <div class="col-10 card-body">
                <h5 class="card-title">${detailsModel.created_account_nick_name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${detailsModel.title}</h6>
                <hr>
                <p class="card-text">${detailsModel.content}</p>
                <small>${detailsModel.create_date}</small>
              </div>
            </div>
            <div class="btn-group btn-group-sm" role="group">
              <button type="button" class="btn btn-light text-success">
                <i class="bi bi-chat-left-text"></i>
                <small>${detailsModel.replayCount}</small>
              </button>
              <button type="button" class="btn btn-light text-success border-left">
                <i class="bi bi-heart"></i>
                <small>${detailsModel.likesCount}</small>
              </button>
              <button type="button" class="btn btn-light text-success border-left">
                <i class="bi bi-star"></i>
                <small>${detailsModel.marksCount}</small>
              </button>
            </div>
            <div class="reply-container">
            ${replyDom}
            </div>
        </div>`;
        

        container.innerHTML += postDom;
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(status);
    },
  });
}