//工具类
export const commonTools {

    // 发送Ajax请求
    function sendAjax(type, actionUrl, param, successCallBack, errorCallBack) {
        const HOST = "127.0.0.1/";
        //* ajax
        $.ajax({
            type: type,
            dataType: 'json',
            url: 'http://' + HOST + actionUrl,
            data: param
        }).done(function (data, textStatus, xhr) {
            //* success
            if (data.status) {
                return successCallBack(data);
            } else {
                return errorCallBack(data);
            }
        });
    }
}