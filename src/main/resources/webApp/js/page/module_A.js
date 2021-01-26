import * as commonTools from '../commonTools.js';

//模块初始化
export default() => {

    commonTools.sendAjax('GET'
        ,'/nesder/channel/all'
        ,{}
        ,function(response){
            //成功
            let channelEle = document.querySelector("#channel");
            response.data.forEach((element,index) => {
                let changeEle = `
                    <a href="#" class="list-group-item 
                                list-group-item-action 
                                border border-success 
                                mt-3 ` + (index == 0 ? `list-group-item-success">` : `">`)
                                + element.name + 
                                `</a>`;
                channelEle.innerHTML += changeEle;
            });
            
        },function(response){

        });
    let channel;
    
};