import * as commonTools from '../js/commonTools.js';

//模块初始化
export default() => {

    commonTools.sendAjax('POST','/nesder/channel/all',,function(response){
        console.log(response)
    },function(response){

    })
    let channel;
    //$("#channel").find(".list-group-item")
};