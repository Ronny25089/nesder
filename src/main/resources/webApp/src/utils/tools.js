//工具类

// 原生JS封装Ajax请求
export const ajax = (options) => {
  /**
   * 传入方式默认为对象
   * */
  options = options || {};
  /**
   * 默认为GET请求
   * */
  options.type = (options.type || "GET").toUpperCase();
  /**
   * 返回值类型默认为json
   * */
  options.dataType = options.dataType || "json";
  /**
   * 默认为非异步请求
   * */
  options.async = options.async || false;
  /**
   * 对需要传入的参数的处理
   * */
  let params = getParams(options.data);
  let xhr;
  /**
   * 创建一个 ajax请求
   * W3C标准和IE标准
   */
  if (window.XMLHttpRequest) {
    /**
     * W3C标准
     * */
    xhr = new XMLHttpRequest();
  } else {
    /**
     * IE标准
     * @type {ActiveXObject}
     */
    xhr = new ActiveXObject("Microsoft.XMLHTTP");
  }
  xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) {
      var status = xhr.status;
      if (status >= 200 && status < 300) {
        if (options.dataType=="json") {
          options.success && options.success(JSON.parse(xhr.responseText));  
        } else {
          options.success && options.success(xhr.responseText);
        }
      } else {
        options.fail && options.fail(status);
      }
    }
  };
  if (options.type == "GET") {
    xhr.open("GET", options.url + "?" + params, options.async);
    xhr.send(null);
  } else if (options.type == "POST") {
    /**
     *打开请求
     * */
    xhr.open("POST", options.url, options.async);
    /**
     * POST请求设置请求头
     * */
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    /**
     * 发送请求参数
     */
    xhr.send(params);
  }
}
/**
 * 对象参数的处理
 * @param data
 * @returns {string}
 */
const getParams = (data) => {
  let arr = [];
  for (let param in data) {
    arr.push(encodeURIComponent(param) + "=" + encodeURIComponent(data[param]));
  }
  return arr.join("&");
}
