//工具类

// 发送Ajax请求
export function sendAjax(type, actionUrl, param, successCallBack, errorCallBack) {
    const HOST = location.hostname;
    //* ajax
    $.ajax({
        type: type,
        dataType: 'json',
        url: 'http://' + HOST + actionUrl,
        data: param,
        success:function(result){
            return successCallBack(result);
        },
        error:function(result){
            return errorCallBack(result);
        } 
    });
}