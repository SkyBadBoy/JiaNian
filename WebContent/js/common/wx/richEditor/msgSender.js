define("common/wx/richEditor/msgSender.js",["common/wx/popup.js","widget/msg_sender.css","common/qq/jquery.plugin/tab.js","common/wx/richEditor/emotionEditor.js","media/media_dialog.js","common/wx/media/factory.js","common/qq/Class.js","common/wx/Tips.js","common/wx/media/audio.js","common/wx/media/img.js","common/wx/media/video.js","common/wx/media/cardmsg.js","common/wx/tooltip.js","common/wx/media/appmsg.js","biz_web/utils/upload.js"],function(e){
"use strict";
function t(e,t){
for(var i=[],n=0;n<e.length;++n){
var s=e[n];
t&&t[s.acl]&&i.push(s);
}
return i;
}
function i(e){
var t={},i=e.slice();
i.push({
acl:"can_video_msg",
className:"tab_video",
selector:"js_videoArea",
text:"视频",
type:4,
index:3
},{
acl:"can_use_shortvideo",
className:"tab_video",
selector:"js_videoArea",
text:"视频",
type:21,
index:3
});
for(var n=0;n<i.length;++n){
var s=i[n];
s.index=s.index||n,t[s.type]=s;
}
return t;
}
e("common/wx/popup.js"),e("widget/msg_sender.css");
var n=(e("common/qq/jquery.plugin/tab.js"),e("common/wx/richEditor/emotionEditor.js")),s=e("media/media_dialog.js"),a=e("common/wx/media/factory.js"),o=e("common/qq/Class.js"),r=e("common/wx/Tips.js"),d=(e("common/wx/media/audio.js"),
e("common/wx/media/img.js"),e("common/wx/media/video.js"),e("common/wx/media/cardmsg.js")),c=(e("common/wx/tooltip.js"),
e("common/wx/media/appmsg.js"),e("biz_web/utils/upload.js")),p=1,l=[{
text:"图文消息",
acl:"can_app_msg",
className:"tab_appmsg",
selector:"js_appmsgArea",
type:10
},{
text:"文字",
acl:"can_text_msg",
className:"tab_text",
selector:"js_textArea",
innerClassName:"no_extra",
type:1
},{
text:"图片",
acl:"can_image_msg",
className:"tab_img",
selector:"js_imgArea",
type:2
},{
text:"语音",
acl:"can_voice_msg",
className:"tab_audio",
selector:"js_audioArea",
type:3
},{
text:"视频",
acl:"can_video_msg",
className:"tab_video",
selector:"js_videoArea",
type:15
},{
text:"商品消息",
acl:"can_commodity_app_msg",
className:"tab_commondity_appmsg",
selector:"js_commondityAppmsgArea",
type:11
},{
text:"卡券",
acl:"can_card_msg",
className:"tab_cardmsg",
selector:"js_cardmsgArea",
type:16
}],m=a.itemRender,g=o.declare({
select:function(){
this.msgSender.type=this.type;
},
fillData:function(){},
getData:function(){},
click:function(){
this.msgSender.type=this.type;
}
}),f=g.Inherit({
init:function(e){
this.msgSender=e,this.type=1,this.info=e.infos[this.type],this.wordlimit=e.opt.wordlimit,
this.index=this.info&&this.info.index;
},
fillData:function(e){
var t=this.msgSender;
t.type=this.type,t.select(this.index),t.emotionEditor.setContent(e.content);
},
getData:function(){
var e=this.msgSender.emotionEditor.getContent();
return{
type:this.type,
content:e
};
},
clear:function(){
return this.fillData({
content:""
});
},
isValidate:function(e){
var t=e&&1==e.type&&!!(""!=e.content&&e.content.length<=this.wordlimit);
return t||r.err("文字必须为1到%s个字".sprintf(this.wordlimit)),t;
},
click:function(){
var e=this;
this.msgSender.type=this.type,setTimeout(function(){
e.msgSender.emotionEditor.insertHTML();
});
}
}),u=g.Inherit({
init:function(e,t){
this.type=t,this.msgSender=e,this.info=e.infos[t],this.index=this.info&&this.info.index;
},
click:function(){
var e=this,t=this.type;
if(this.msgSender.type=t,2==t||3==t){
var i=2==t?"msgSendImgUploadBt":"msgSendAudioUploadBt";
c.uploadImageLibFile({
container:"#"+i,
type:t,
doublewrite:!0,
groupid:1,
pick:{
multiple:!1
},
onComplete:function(i,n,s,a){
if(0==a.base_resp.ret){
var o,r="msgSender_media_%s_%s".sprintf(e.msgSender.gid,t);
o=2==t?{
file_id:a.content,
source:"file"
}:{
file_id:a.content,
source:"file",
name:s.name,
play_length:s.size
},$("."+e.info.selector).find(".jsMsgSendTab").hide().after('<div id="%s"></div>'.sprintf(r)),
m[t]&&m[t]("#"+r,o),$("#"+r).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
e.msgSender.opt.onSelect&&e.msgSender.opt.onSelect(t,o);
}
},
onAllComplete:function(){
r.suc("上传成功");
}
}),function(){
$("#"+i).trigger("click");
}.delay(.1);
}
if(10!=this.type&&2!=this.type&&3!=this.type&&11!=this.type&&15!=this.type){
var n=null,e=this;
return n=10==e.type||11==e.type||15==e.type?s.getAppmsg:s.getFile,n({
type:e.type,
begin:0,
count:10,
onSelect:function(t,i){
var n=e.msgSender;
e.msgSender.type=t,n.select(e.index);
var s="msgSender_media_%s_%s".sprintf(n.gid,t);
$("."+e.info.selector).html('<div id="%s"></div>'.sprintf(s)),m[t]&&m[t]("#"+s,i),
$("#"+s).append('<a href="javascript:;" class="link_dele jsmsgSenderDelBt" onclick="return false;">删除</a>'),
e.msgSender.opt.onSelect&&e.msgSender.opt.onSelect(t,i);
}
}),!1;
}
},
fillData:function(e){
var t=this.msgSender,i=this.type,n="msgSender_media_%s_%s".sprintf(t.gid,i);
$("."+this.info.selector).find(".jsMsgSendTab").hide().after('<div id="%s"></div>'.sprintf(n)),
t.select(this.index),this.msgSender.type=i,m[i]&&m[i]("#"+n,e),$("#"+n).append('<a href="javascript:;" class="link_dele jsmsgSenderDelBt" onclick="return false;">删除</a>');
},
clear:function(){
this.type;
return $("."+this.info.selector).html("");
},
getData:function(e){
var t=this.type,i="msgSender_media_%s_%s".sprintf(this.msgSender.gid,t),n=$("#"+i).data("opt");
if(n){
if(e){
n.type=t;
var s=n.data||{};
return $.extend(n,s);
}
return 10==t||11==t?{
type:t,
app_id:n.data.app_id
}:15==t?{
type:t,
app_id:n.app_id
}:{
type:t,
file_id:n.file_id
};
}
return!1;
},
isValidate:function(e){
var t=!!e;
if(e){
var i=e.type;
t=10==i||11==i||15==i?!(!e.type||!e.app_id):!(!e.type||!e.file_id);
}
return t||r.err("请添加素材"),t;
}
}),h={
wordlimit:600
},_=o.declare({
init:function(e,n){
var s=this,a=0;
e=$(e).show(),s.dom=e,s.gid=p++,s.opt=$.extend(!0,{},h,n);
var o=l,r=n&&n.acl||{};
o=t(o,r),s.infos=i(o),s.op={
1:new f(s),
2:new u(s,2),
3:new u(s,3),
4:new u(s,4),
6:new u(s,6),
7:new u(s,15),
9:new u(s,21),
10:new u(s,10),
11:new u(s,11),
15:new u(s,15),
16:new d(s),
21:new u(s,21)
},s.tab=e.tab({
index:a,
tabs:o,
select:function(){},
click:function(e,t,i,a){
return n.onClick&&n.onClick(e,t,i,a),s.op[a]&&s.op[a].click();
}
}),s._init(e,n),s.initEvent();
var c=n.data;
c?10==this.opt.data.type?c.data&&s.setData(c):s.setData(c):s.type=o[0]&&o[0].type?o[0].type:10;
},
initEvent:function(){
var e=this;
$(".jsMsgSenderPopBt").click(function(){
var t,i=$(this).data("type"),n=$(this).data("index"),a=$(".jsMsgSendTab[data-index="+n+"]");
(t=10==i||11==i||15==i?s.getAppmsg:s.getFile)({
type:i,
begin:0,
count:10,
onSelect:function(t,i){
e.type=t,e.select(n);
var s="msgSender_media_%s_%s".sprintf(e.gid,t);
a.hide().after('<div id="%s"></div>'.sprintf(s)),m[t]&&m[t]("#"+s,i),$("#"+s).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
e.opt.onSelect&&e.opt.onSelect(t,i);
}
});
}),this.dom.on("click",".jsmsgSenderDelBt",function(){
$(this).parent().siblings(".jsMsgSendTab").show(),$(this).parent().remove();
var t;
$("#msgSendImgUploadBt").is(":visible")&&0==$("#msgSendImgUploadBt").parent().find("input[type=file]").length?t=2:$("#msgSendAudioUploadBt").is(":visible")&&0==$("#msgSendAudioUploadBt").parent().find("input[type=file]").length&&(t=3),
(2==t||3==t)&&(c.uploadImageLibFile({
container:2==t?"#msgSendImgUploadBt":"#msgSendAudioUploadBt",
type:t,
doublewrite:!0,
groupid:1,
pick:{
multiple:!1
},
onComplete:function(i,n,s,a){
if(0==a.base_resp.ret){
var o,r="msgSender_media_%s_%s".sprintf(e.gid,t);
o=2==t?{
file_id:a.content,
source:"file"
}:{
file_id:a.content,
source:"file",
name:s.name,
play_length:s.size
},$(".jsMsgSendTab[data-type="+t+"]").hide().after('<div id="%s"></div>'.sprintf(r)),
m[t]&&m[t]("#"+r,o),$("#"+r).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
e.opt.onSelect&&e.opt.onSelect(t,o);
}
},
onAllComplete:function(){
r.suc("上传成功");
}
}),function(){
$(2==t?"#msgSendImgUploadBt":"#msgSendAudioUploadBt").trigger("click");
}.delay(.1));
});
},
setData:function(e){
if(e){
var t=this,i=null,n=e.type||10;
t.type=n,(i=t.op[n])&&i.fillData(e);
}
},
_init:function(e){
this.dom=e,this.emotionEditor=new n($(".js_textArea",e),{
wordlimit:this.opt.wordlimit,
linebreak:!0
});
},
getData:function(e){
if(this.type){
var t=this.op[this.type].getData(e);
return{
error:!this.isValidate(),
data:t
};
}
return{
error:!0
};
},
getArea:function(e){
return this.dom.find("."+this.infos[e].selector);
},
getTabs:function(){
return this.tab.getTabs();
},
isValidate:function(){
var e=this.type&&this.op[this.type].getData();
return this.type&&this.op[this.type].isValidate(e);
},
clear:function(){
return this.type&&this.op[this.type].clear();
},
select:function(e){
return this.tab.select(e);
}
});
return _;
});