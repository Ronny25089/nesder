import * as commonTools from '../commonTools.js';

//模块初始化
export default() => {

    commonTools.sendAjax('GET','/nesder/module/all',{},function(response){
        console.log(response)
    },function(response){

    })
    let channel;
    //$("#channel").find(".list-group-item")
};