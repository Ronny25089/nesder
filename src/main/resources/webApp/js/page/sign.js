// datePicker时间格式 初始化
export default () => {
  $('#birthDay-datepicker').datetimepicker({
    format: 'YYYY/MM/DD',
    locale: 'zh-CN',
    dayViewHeaderFormat: 'YYYY年 MMM',
  });
};

// 注册事件
export const signIn = () => {
  console.log(window.DBind.get("email"));
};
