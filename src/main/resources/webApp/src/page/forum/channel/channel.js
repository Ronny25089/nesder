import * as tools from "/src/utils/tools.js";

//模块初始化
export default () => {
  // 初始化channel区域
  getAllPost();
};

const getAllPost =() => {
  let forum_id = sessionStorage.getItem('forum');
  let channel_id = sessionStorage.getItem('channel');
  tools.ajax({
    url: `${HOST}/nesder/details/get/${channel_id}`,
    type: "GET",
    success: response => {
      console.log(response);
      // 此处执行请求成功后的代码
      let container = document.querySelector("#details");
      // 当频道为【全部的时候】，不显示发布栏
      if (channel_id != 1001) {
        container.innerHTML += `
          <!-- 发布栏 -->
          <div class="card mb-5 p-3">
            <div class="form-group mr-5">
              <label for="exampleFormControlTextarea1">内容</label>
              <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>
            <div class="d-flex flex-row-reverse ">
              <button type="button" class="btn mx-1 custom-select-sm bg-n-blue text-light width-10" onclick="module.post()">
                <i class="bi bi-stickies"></i>
                发布
              </button>
            </div>
          </div>
          <hr>`;
      }
      
      response.data.forEach((item, index) => {
        let dom = `
          <div class="card mb-3 post-item">
            <div class="row">
              <div class="col-1">
                <img src="${HOST}/${item.created_account_avatarurl}" class="avatar-wrapper ml-3 mt-4">
              </div>
              <div role="button" class="col-10 card-body"
              onclick="router.goto('#/${forum_id == '' ? `home`:`forum/${forum_id}`}/post/${item.post_id}')">
                <h5 class="card-title">${item.created_account_nick_name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${item.title}</h6>
                <hr>
                <p class="card-text">${item.content}</p>
                <small>${item.create_date}</small>
              </div>
            </div>
            <div class="btn-group btn-group-sm" role="group">
              <button type="button" class="btn btn-light text-d-blue" 
              onclick="openReplyInput('reply2post${item.post_id}')">
                <i class="bi bi-chat-left-text"></i>
                <small>${item.replayCount}</small>
              </button>
              <button type="button" class="btn btn-light text-d-blue border-left">
                <i class="bi bi-heart"></i>
                <small>${item.likesCount}</small>
              </button>
              <button type="button" class="btn btn-light text-d-blue border-left">
                <i class="bi bi-star"></i>
                <small>${item.marksCount}</small>
              </button>
            </div>
            <div id="reply2post${item.post_id}" class="replyInput card p-3 m-3 border-d-blue d-none" >
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
            </div>
          </div>`;
        container.innerHTML += dom;
      });
    },
    fail: status => {
      // 此处为请求失败后的代码
      console.log(status);
    },
  });
}

export function openReplyInput(postId) {
  let showedEle = document.querySelector(".replyInput:not(.d-none)");
  if (showedEle != null) {
    showedEle.classList.add("d-none");
  }
  document.querySelector(`#${postId}`).classList.remove("d-none");
}