import * as forum from "../forum/forum.js";

//模块初始化
export default () => {
  //获取全部的频道
  forum.getChannel(`home`);
};