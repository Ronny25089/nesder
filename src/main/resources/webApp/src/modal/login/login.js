// datePicker时间格式 初始化
export default () => {
  $("#birthDay-datepicker").datetimepicker({
    format: "YYYY/MM/DD",
    locale: "zh-CN",
    dayViewHeaderFormat: "YYYY年 MMM",
  });

  $(".file-upload").on("change", (e) => {
    readURL(e.target);
  });

  $(".upload-button").on("click", () => {
    $(".file-upload").click();
  });
};

// 注册事件
export const sign = () => {
  console.log(window.DBind.get("email"));
};

const readURL = (input) => {
  if (input.files && input.files[0]) {
    let reader = new FileReader();

    reader.onload = (e) => {
      $(".profile-pic").attr("src", e.target.result);
    };

    reader.readAsDataURL(input.files[0]);
  }
};
