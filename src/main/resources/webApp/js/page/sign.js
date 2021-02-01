// datePicker时间格式 初始化
export default () => {
  $("#birthDay-datepicker").datetimepicker({
    format: "YYYY/MM/DD",
    locale: "zh-CN",
    dayViewHeaderFormat: "YYYY年 MMM",
  });
  $(document).ready(function () {
    var readURL = function (input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
          $(".profile-pic").attr("src", e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
      }
    };

    $(".file-upload").on("change", function () {
      readURL(this);
    });

    $(".upload-button").on("click", function () {
      $(".file-upload").click();
    });
  });
};

// 注册事件
export const sign = () => {
  console.log(window.DBind.get("email"));
};
