import * as tools from "/src/utils/tools.js";

//模块初始化
export default () => {
    getPostDetail();
};

function getPostDetail() {
  let post_id = sessionStorage.getItem('post');
  tools.ajax({
    url: `${HOST}/nesder/details/getPostDetails/${post_id}`,
    type: "GET",
    success: function (response) {
      let container = document.querySelector("#details");
      let detailsModel = response.data.detailsModel;
      let replyDetailsModelList = response.data.replyDetailsModelList;
      //   此处执行请求成功后的代码

      let dom = `
      <div class="card mb-3 post-item">
            <div class="row">
              <div class="col-1">
                <img src="${HOST}/${detailsModel.created_account_avatarurl}" class="avatar-wrapper ml-3 mt-4">
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
              <button type="button" class="btn btn-light text-d-blue">
                <i class="bi bi-chat-left-text"></i>
                <small>${detailsModel.replayCount}</small>
              </button>
              <button type="button" class="btn btn-light text-d-blue border-left">
                <i class="bi bi-heart"></i>
                <small>${detailsModel.likesCount}</small>
              </button>
              <button type="button" class="btn btn-light text-d-blue border-left">
                <i class="bi bi-star"></i>
                <small>${detailsModel.marksCount}</small>
              </button>
            </div>
            <div class="reply-container">
              <div id="replyInput" class="sticky-on-top card p-3 m-3 border-d-blue">
                <div class="row d-flex align-items-center ">
                  <div class="col-10 mr-auto bd-highlight">
                    <textarea class="form-control" id="reply" placeholder="写下你的评论..."></textarea>
                  </div>
                  <div class="col-2 bd-highlight">
                    <button type="button" class="btn custom-select-sm bg-n-blue text-light" onclick="post()">
                      <i class="bi bi-stickies"></i>
                      回复
                    </button>
                  </div>
                </div>
              </div>`;
            if (replyDetailsModelList.length > 0) {
              dom += `
              <div class="mt-5">`;
              replyDetailsModelList.forEach(item => {
                console.log(item);
                dom += `
                  <div class="reply">
                    <div class="row">
                      <div class="col-1">
                          <img src="${HOST}/${item.created_account_avatarurl}" class="avatar-small ml-2 mt-4">
                      </div>
                      <div class="col-10 card-body">
                          <strong class="card-title">${item.created_account_nick_name}</strong>
                          <p class="card-text mt-2">${item.content}</p>
                          <div class="d-flex bd-highlight ">
                            <small class="mr-auto bd-highlight">${item.create_date}</small>
                            <span role="button" class="bd-highlight bi bi-heart-fill text-muted" onclick="">
                              <small> </small>
                            </span>
                            <span role="button" class="d-highlight bi bi-chat-right-text ml-5" 
                            onclick="openPostReplyInput('${item.reply_id}','${item.created_account_nick_name}','${item.created_account}')">
                              <small> 回复</small>
                            </span>
                          </div>
                      </div>
                    </div>
                    `;
                });
                dom += `
                </div>`;
            }
            dom += `
            </div>
        </div>`;
        
        container.innerHTML += dom;
    },
    fail: function (status) {
      // 此处为请求失败后的代码
      console.log(status);
    },
  });
}

export function openPostReplyInput(replyId,reply2AccountName, reply2AccountId) {
  console.log(replyId,reply2AccountName, reply2AccountId);
  let input = document.querySelector("#reply");
  console.log(input);
  input.value=`@${reply2AccountName}  `;
  
}