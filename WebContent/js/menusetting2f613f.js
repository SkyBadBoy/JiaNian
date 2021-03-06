define("common/qq/mask.js", [ "biz_web/lib/spin.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict", e("biz_web/lib/spin.js");
var i = 0, s = '<div class="mask"></div>';
function o(e) {
if (this.mask) this.mask.show(); else {
var t = "body";
e && !!e.parent && (t = $(e.parent)), this.mask = $(s).appendTo(t), this.mask.id = "wxMask_" + ++i, this.mask.spin("flower");
}
if (e) {
if (e.spin === !1) return this;
this.mask.spin(e.spin || "flower");
}
return this;
}
o.prototype = {
show: function() {
this.mask.show();
},
hide: function() {
this.mask.hide();
},
remove: function() {
this.mask.remove();
}
}, t.show = function(e) {
return new o(e);
}, t.hide = function() {
$(".mask").hide();
}, t.remove = function() {
$(".mask").remove();
};
} catch (u) {
wx.jslog({
src: "common/qq/mask.js"
}, u);
}
});define("common/wx/media/factory.js",["common/wx/media/img.js","common/wx/media/audio.js","common/wx/media/video.js","common/wx/media/appmsg.js","tpl/media/videocard.html.js","biz_common/utils/norefererimg.js","common/qq/emoji.js","tpl/media/cardmsg.html.js"],function(e,i,m){
"use strict";
var o=e("common/wx/media/img.js"),t=e("common/wx/media/audio.js"),n=e("common/wx/media/video.js"),r=e("common/wx/media/appmsg.js"),s=e("tpl/media/videocard.html.js"),c=e("biz_common/utils/norefererimg.js");
e("common/qq/emoji.js");
var d=e("tpl/media/cardmsg.html.js"),l={
1:function(e,i){
return $(e).html(i.content.emoji());
},
2:function(e,i){
return i.container=$(e),new o(i);
},
3:function(e,i){
return i.selector=$(e),i.source="file",new t(i);
},
4:function(e,i){
return i.selector=$(e),i.id=i.file_id,i.source="file",new n(i);
},
10:function(e,i){
return i.container=$(e),i.showMask=!1,new r(i);
},
11:function(e,i){
return i.container=$(e),i.showMask=!1,new r(i);
},
15:function(e,i){
return i.multi_item&&i.multi_item[0]&&(i.title=i.multi_item[0].title,i.digest=i.multi_item[0].digest),
i.selector=$(e),i.id=1e6*Math.random()|0,i.tpl="videomsg",i.for_selection=!1,i.for_operation=!1,
{
dom:$(e).html(wx.T(s,i)),
file_id:i.file_id,
id:i.id,
source:"file",
tpl:"videomsg",
type:"",
video_url:""
};
},
16:function(e,i){
$(e).html(template.compile(d)(i));
var m=$(e).find(".js_logourl");
m.length&&c({
img:m[0]
});
}
};
l[21]=l[15];
var a={
render:function(e,i){
l[i.type]&&$(e).length>0&&l[i.type]($(e).html(""),i);
},
itemRender:l
};
m.exports=a;
});define("common/wx/richEditor/msgSender.js",["common/wx/popup.js","widget/msg_sender.css","common/qq/jquery.plugin/tab.js","common/wx/richEditor/emotionEditor.js","media/media_dialog.js","common/wx/media/factory.js","common/qq/Class.js","common/wx/Tips.js","common/wx/media/audio.js","common/wx/media/img.js","common/wx/media/video.js","common/wx/media/cardmsg.js","common/wx/tooltip.js","common/wx/media/appmsg.js","biz_web/utils/upload.js"],function(e){
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
if(this.msgSender.type=t,3==t&&$("#msgSendAudioUploadBt").click(function(){
window.open(wx.url("/cgi-bin/operate_voice?oper=voice_get&t=media/audio_add"),"_blank");
}),2==t){
var i="msgSendImgUploadBt";
c.uploadImageLibFile({
container:"#"+i,
type:t,
scene:5,
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
return n=10==e.type||11==e.type||15==e.type?s.getAppmsg:3==e.type?s.getVoice:s.getFile,
n({
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
app_id:n.app_id,
vid:n.content
}:2==t?{
type:t,
file_id:n.file_id,
copyright_status:n.copyright_status
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
return t||$(".js_menuSetting").length||r.err("请添加素材"),t;
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
(t=10==i||11==i||15==i?s.getAppmsg:3==i?s.getVoice:s.getFile)({
type:i,
begin:0,
count:10,
onSelect:function(t,i){
e.type=t,e.select(n);
var s="msgSender_media_%s_%s".sprintf(e.gid,t);
$("#"+s).html("");
var o=2==t?' class="msgSender_media_classFixImg"':"";
a.hide().after('<div id="%s"%s></div>'.sprintf(s,o)),m[t]&&m[t]("#"+s,i),$("#"+s).data("opt",i),
$("#"+s).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
e.opt.onSelect&&e.opt.onSelect(t,i);
}
});
}),this.dom.on("click",".jsmsgSenderDelBt",function(){
$(this).parent().siblings(".jsMsgSendTab").show(),$(this).parent().remove();
var t;
$("#msgSendImgUploadBt").is(":visible")&&0==$("#msgSendImgUploadBt").parent().find("input[type=file]").length?t=2:$("#msgSendAudioUploadBt").is(":visible")&&0==$("#msgSendAudioUploadBt").parent().find("input[type=file]").length&&(t=3),
3==t&&$("#msgSendAudioUploadBt").click(function(){
window.open(wx.url("/cgi-bin/operate_voice?oper=voice_get&t=media/audio_add"),"_blank");
}),2==t&&(c.uploadImageLibFile({
container:2==t?"#msgSendImgUploadBt":"#msgSendAudioUploadBt",
type:t,
scene:5,
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
$("#msgSendImgUploadBt").trigger("click");
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
selectPopDialogByType:function(e){
$(".jsMsgSenderPopBt[data-type='"+e+"']").click();
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
});define("common/wx/popover.js",["tpl/popover.html.js"],function(o,t,e){
"use strict";
function i(o){
if(o=$.extend(!0,{},h,o),this.opt=o,this.$dom=$(o.dom),this.$dom.data("popover")){
var t=this.$dom.data("popover");
return s(o,t),t.$pop.show(),t;
}
return o.buttons&&o.buttons&&o.buttons.each(function(o){
o.type=o.type||"default";
}),this.$pop=$(template.compile(p)(o)),o.addCls&&this.$pop.addClass(o.addCls),$("body").append(this.$pop),
n(this,o),s(o,this),this.$pop.show(),this.$dom.data("popover",this),this.clickIn=!0,
this;
}
function n(o,t){
function e(){
clearTimeout(n),o.show();
}
function i(){
n=setTimeout(function(){
o.hide();
},s);
}
if(t.buttons&&t.buttons.length>0&&o.$pop.find(".jsPopoverBt").each(function(e,i){
t.buttons[e]&&"function"==typeof t.buttons[e].click&&$(i).click(function(i){
t.buttons[e].click.call(o,i);
});
}),o.$pop.find(".jsPopoverClose").click(function(){
t.close===!0?o.hide():"function"==typeof t.close&&t.close.call(o);
}),t.hover&&(o.$dom.hover(function(){
o.hoverTime&&clearTimeout(o.hoverTime);
},function(){
o.hoverTime=o.hide.delay(1,o);
}),o.$pop.hover(function(){
o.hoverTime&&clearTimeout(o.hoverTime);
},function(){
o.hoverTime&&clearTimeout(o.hoverTime),o.hoverTime=o.hide.delay(1,o);
})),t.isToggle){
var n=null,s=300;
o.$dom.hover(e,i),o.$pop.hover(e,i);
}
t.hideIfBlur&&(o._onBlur=function(o){
var t=o.data.context,e=o.target,i=t.$dom.get(0),n=t.$pop.get(0);
t.clickIn?t.clickIn=!1:$.contains(i,e)||i===e||$.contains(n,e)||n===e||o.data.context.hide();
},$(document).on("click",{
context:o
},o._onBlur)),o._onResize=function(o){
o.data.context.resetPosition();
},$(window).on("resize",{
context:o
},o._onResize);
}
function s(o,t){
var e=t.$dom.offset();
"left"==o.margin?(console.log(e.top),console.log(t.$dom.height()),t.$pop.css({
top:e.top+t.$dom.height(),
left:e.left
}).addClass("pos_left")):"right"==o.margin?t.$pop.css({
top:e.top+t.$dom.height(),
left:e.left+t.$dom.width()-t.$pop.width()
}).addClass("pos_right"):t.$pop.css({
top:e.top+t.$dom.height(),
left:e.left+t.$dom.outerWidth()/2-t.$pop.width()/2
}).addClass("pos_center");
}
var p=o("tpl/popover.html.js"),h={
dom:"",
content:"",
place:"bottom",
margin:"center",
hideIfBlur:!1,
hover:!1,
addCls:"",
isToggle:!1,
onHide:!1,
onShow:!1,
onRemove:!1
};
i.prototype={
remove:function(){
this.$pop.remove(),this.$dom.removeData("popover"),this._onBlur&&$(document).off("click",this._onBlur),
$(window).off("resize",this._onResize),"function"==typeof this.opt.onRemove&&this.opt.onRemove.call(this);
},
hide:function(){
this.$pop.hide(),"function"==typeof this.opt.onHide&&this.opt.onHide.call(this);
},
show:function(){
this.$pop.show(),"function"==typeof this.opt.onShow&&this.opt.onShow.call(this);
},
resetPosition:function(){
return s(this.opt,this);
}
},e.exports=i;
});define("common/wx/simplePopup.js",["tpl/simplePopup.html.js","common/wx/popup.js","biz_common/jquery.validate.js"],function(e,t,o){
"use strict";
function i(e){
var t=$.Deferred(),o=this;
o.$dom=$(template.compile(p)(e)).popup({
title:e.title||"输入提示框",
buttons:[{
text:"确认",
click:function(){
var i=this;
if(l.form()){
var p=o.$dom.find("input").val().trim();
if(e.callback){
var r=e.callback.call(i,p);
r!==!1&&this.remove();
}else this.remove();
t.resolve(p);
}
},
type:"primary"
},{
text:"取消",
click:function(){
this.remove();
},
type:"default"
}],
className:"simple label_block"
}),o.$dom.find("input").val(e.value).focus(),$.validator.addMethod("_popupMethod",function(t,o,i){
return e&&e.rule&&e.rule(t.trim(),o,i);
},e.msg);
var i=e&&"undefined"!=typeof e.inputrequire&&e.inputrequire===!1?!1:!0,l=o.$dom.find("form").validate({
rules:{
popInput:{
required:i,
_popupMethod:!0
}
},
messages:{
popInput:{
required:"输入框内容不能为空"
}
},
onfocusout:!1
});
return t.callback=t.done,t;
}
var p=e("tpl/simplePopup.html.js");
e("common/wx/popup.js"),e("biz_common/jquery.validate.js"),o.exports=i;
});define("common/wx/tooltip.js", [ "tpl/tooltip.html.js", "widget/tooltip.css" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = e("tpl/tooltip.html.js");
e("widget/tooltip.css");
var s = {
dom: "",
content: "",
position: {
x: 0,
y: 0
}
}, o = function(e) {
this.options = e = $.extend(!0, {}, s, e), this.$dom = $(this.options.dom), this.init();
};
o.prototype = {
constructor: o,
init: function() {
var e = this;
e.pops = [], e.$dom.each(function() {
var t = $(this), n = t.data("tooltip"), r = $(template.compile(i)(n ? $.extend(!0, {}, e.options, {
content: n
}) : e.options));
e.pops.push(r), $("body").append(r), r.css("display", "none"), t.on("mouseenter", function() {
var n = t.offset();
r.css({
top: n.top - (e.options.position.y || 0) - r.height(),
left: n.left + t.width() / 2 - r.width() / 2 + (e.options.position.x || 0)
}), r.show();
}).on("mouseleave", function() {
r.hide();
}), t.data("tooltip_pop", r);
});
},
show: function() {
var e = this, t = 0, n = e.pops.length;
for (var t = 0; t < n; t++) e.pops[t].show();
},
hide: function() {
var e = this, t = 0, n = e.pops.length;
for (var t = 0; t < n; t++) e.pops[t].hide();
}
}, n.exports = o;
} catch (u) {
wx.jslog({
src: "common/wx/tooltip.js"
}, u);
}
});define("advanced/menuSetting.js",["common/wx/Tips.js","common/wx/tooltip.js","common/wx/simplePopup.js","common/wx/popover.js","common/wx/dialog.js","common/wx/Cgi.js","common/wx/richEditor/msgSender.js","common/wx/media/factory.js","common/qq/mask.js","biz_common/xss.js","biz_common/moment.js","common/wx/inputCounter.js","advanced/menu_link_dialog.js","cardticket/parse_data.js","common/wx/ban.js","common/qq/emoji.js","biz_web/lib/json.js","biz_common/jquery.ui/jquery.ui.sortable.js"],function(e){
"use strict";
function t(e){
this.data=e;
}
function s(e,t){
var s=[],i=0,n=0;
$.each(e,function(e,t){
var a=t.url.html(!1);
if(a.match(/^http(s)?:\/\/mp.weixin.qq.com\/s/))i++,l.get({
url:a.replace(/^http:/,"https:")
},{
done:function(e){
n++,e&&e.title&&s.push({
i:t.i,
j:t.j,
name:"素材库",
title:e.title
});
},
fail:function(){
n++;
}
});else if(a.match(/^http(s)?:\/\/mp.weixin.qq.com\/mp\/getmasssendmsg\?__biz=([^&#]+)#wechat_webview_type=1&wechat_redirect$/))s.push({
i:t.i,
j:t.j,
name:"历史消息"
});else if(a.match(/^http(s)?:\/\/mp.weixin.qq.com\/mp\/homepage\?__biz=([^&#]+)&hid=([^&#]+)&sn=([^&#]+)#wechat_redirect/)){
i++;
var r=a.html(!1).match(/[\?&]hid=(\d+)/);
r=r&&r[1],l.get({
url:"/advanced/homepage?t=homepage/edit&action=edit",
data:{
hid:r
},
dataType:"json"
},{
done:function(e){
n++,e&&e.name&&s.push({
i:t.i,
j:t.j,
name:"页面模板",
title:e.name
});
},
fail:function(){
n++;
}
});
}
});
var a=setInterval(function(){
n==i&&(t(s),clearInterval(a));
},500);
}
var i=e("common/wx/Tips.js"),n=(e("common/wx/tooltip.js"),e("common/wx/simplePopup.js"),
e("common/wx/popover.js")),a=e("common/wx/dialog.js"),l=e("common/wx/Cgi.js"),r=e("common/wx/richEditor/msgSender.js"),o=e("common/wx/media/factory.js"),d=e("common/qq/mask.js"),u=(e("biz_common/xss.js"),
e("biz_common/moment.js"),e("common/wx/inputCounter.js"),e("advanced/menu_link_dialog.js")),c=e("cardticket/parse_data.js"),h=e("common/wx/ban.js");
e("common/qq/emoji.js"),e("biz_web/lib/json.js"),e("biz_common/jquery.ui/jquery.ui.sortable.js");
var _,m=("1"==wx.cgiData.service_type||"0"==wx.cgiData.service_type)&&"1"!=wx.cgiData.is_wx_verify&&"1"!=wx.cgiData.is_qverify&&"3"!=wx.cgiData.is_wb_verify,p=0;
t.prototype={
cgi:"/advanced/operselfmenu?op=update&f=json",
get:function(e){
var t;
return this.data.each(function(s){
s.id==e&&(t=s);
}),t;
},
getSub:function(e,t,s){
var i,n;
return s===!0?this.data.each(function(e){
e.subs&&e.subs.each(function(e){
return e.id==t?(n=e,!1):void 0;
});
}):(i=this.get(e),i.subs.each(function(e){
e.id==t&&(n=e);
})),n;
},
add:function(e,t){
var s=(new Date).getTime()+"_menu_"+this.data.length;
this.data.push({
name:e,
id:s,
type:1
}),this.post(t,s);
},
addSub:function(e,t,s){
e.type=0,e.act=null,e.subs||(e.subs=[]);
var i=(new Date).getTime()+"_subMenu_"+e.id+"_"+e.subs.length;
e.subs.push({
name:t,
id:i,
type:1
}),this.post(s,i);
},
del:function(e,t){
var s=this;
$.each(this.data,function(t,i){
return i.id==e?(s.data.splice(t,1),!1):void 0;
}),this.post(t);
},
delSub:function(e,t,s){
var i=this.get(e);
i.subs.each(function(e,s){
return e.id==t?(i.subs.splice(s,1),!1):void 0;
}),0==i.subs.length&&(i.type=1),this.post(s);
},
edit:function(e,t,s){
e.name=t,this.post(s);
},
sort:function(e,t){
var s=this,n=[],a=!1;
e.each(function(e){
var t=[];
e.subs.each(function(i){
t.push(s.getSub(e.id,i,!0));
});
var l=s.get(e.id);
return l=Object.clone(l,!0),l.subs=t,l.subs.length>5?(i.err("同一个一级菜单下最多只能添加五个二级菜单，当前已达设置上限"),
a=!0,!1):void n.push(l);
}),a||(s.data=n,this.post(t));
},
post:function(e,t){
var s=this;
l.post({
url:s.cgi,
data:{
info:s.adapt(s.data)
}
}).success(function(s){
0==s.base_resp.ret?(e(),t&&$("#"+t).find(".inner_menu_link").trigger("click")):1==s.base_resp.ret?i.err("你的帐号尚未通过实名审核或已被警告限制，暂时无法使用该功能;"):11==s.base_resp.ret?i.err("菜单跳转链接URL可能存在安全风险，请检查"):105==s.base_resp.ret?(i.err("保存失败，未认证的订阅号不可添加自定义外链，页面即将重置刷新"),
setTimeout(function(){
location.reload(!0);
},3e3)):1530501==s.base_resp.ret?($("#urlText").focus(),$(".js_warn").show()):(i.err("系统发送异常失败，页面即将重置刷新"),
setTimeout(function(){
location.reload(!0);
},3e3));
});
},
adapt:function(e){
function t(e){
if(e){
var t={};
return $.each(e,function(e,s){
e.endsWith("_data")||("value"==e?t.value=(s+"").html(!1):t[e]=s);
}),[t];
}
return[];
}
var s=[];
return $.each(e,function(e,i){
var n={
name:i.name.html(!1),
type:i.type
};
n.act_list=[],i.subs&&i.subs.length>0?(n.sub_button_list=[],$.each(i.subs,function(e,s){
n.sub_button_list.push({
name:s.name.html(!1),
act_list:t(s.act),
type:s.type,
sub_button_list:[]
});
})):n.act_list=t(i.act),s.push(n);
}),JSON.stringify2({
version:wx.cgiData.menu.version,
name:wx.cgiData.menu.name,
button_list:s
});
},
getExtraInfo:function(e){
var t=this,i=[];
$.each(this.data,function(e,t){
return t.type&&2==t.type&&t.act&&t.act.value?void i.push({
i:e,
url:t.act.value
}):void(t.subs&&$.each(t.subs,function(t,s){
s.type&&2==s.type&&s.act&&s.act.value&&i.push({
i:e,
j:t,
url:s.act.value
});
}));
}),s(i,function(s){
$.each(s,function(e,s){
var i=null;
"undefined"!=typeof s.i&&"undefined"!=typeof s.j?i=t.data[s.i].subs[s.j]:"undefined"!=typeof s.i&&(i=t.data[s.i]),
i.act&&i.act.name&&i.act.title&&(i.act.name=s.name,i.act.title=s.title);
}),s.length&&e();
});
}
};
var f,v=function(){
function e(){
if(h(wx.cgiData.func_ban_info,"menu")){
if(void 0==wx.cgiData.menu)return j.init(),void y();
s(),b.init(),x.init(),j.init(),y();
}
}
function s(){
wx.cgiData.menu=wx.cgiData.menu;
var e=[];
$.each(wx.cgiData.menu&&wx.cgiData.menu.button_list,function(t,s){
var i={
name:s.name,
id:"menu_"+t,
type:s.type
};
if(s.sub_button_list.length>0){
var n=[];
$.each(s.sub_button_list,function(e,t){
n.push({
name:t.name,
act:t.act_list[0],
id:"subMenu_"+i.id+"_"+e,
type:t.type
});
}),i.subs=n;
}else i.act=s.act_list[0];
e.push(i);
}),f=new t(e),window.myMenu=f,f.getExtraInfo(function(){
var e,t=$("#menuList .selected");
t.hasClass("jslevel1")?e=f.get(t.id):t.hasClass("jslevel2")&&(e=f.getSub(t.parents("li.jslevel1").attr("id"),t.get(0).id)),
e&&$("#view").is(":visible")&&w.refresh(e),e&&e.act&&e.act.value&&$("#url").is(":visible")&&w.ui.url(e.act.value,e);
});
}
return{
init:e
};
}(),j=function(){
function e(){
t(),a();
}
function t(){
var e=MenuData;
e.is.isOpen?s(e):($(".js_menuBox").show(),$("#menustatus_5").show(),$("#menu_container").remove(),
$(".js_startMenuBox").remove(),$(".js_editBox").remove(),$("#menustatus_1,#menustatus_2,#menustatus_3,#menustatus_4").remove());
}
function s(e){
!e.is.selfMenu||parseInt(e.selfMenu.lastTime)||parseInt(e.selfMenu.hasButton)?e.is.selfMenu?e.selfMenu.lastTime&&($(".js_menuBox").show(),
$("#menuList").children(".jslevel1").length?(w.ui.none("点击左侧菜单进行编辑操作"),$(".jslevel1").length&&($(".jslevel1").eq(0).children("a").trigger("click"),
$(".jslevel1").eq(0).find(".jslevel2").length&&$(".jslevel1").eq(0).find(".jslevel2").eq(0).children("a").trigger("click")),
"1"==e.selfMenu.useMpOrApi&&"0"==e.selfMenu.usePersonalizedMenu?$("#menustatus_6").show():"1"==e.selfMenu.useMpOrApi&&"1"==e.selfMenu.usePersonalizedMenu?$("#menustatus_7").show():"0"==e.selfMenu.useMpOrApi&&"1"==e.selfMenu.usePersonalizedMenu?"1"==e.selfMenu.status?$("#menustatus_10").show():"2"==e.selfMenu.status?$("#menustatus_9").show():"3"==e.selfMenu.status&&$("#menustatus_8").show().find(".js_waitHour").text(e.selfMenu.leftTime):"1"==e.selfMenu.status?$("#menustatus_1").show():"2"==e.selfMenu.status?$("#menustatus_2").show():"3"==e.selfMenu.status?$("#menustatus_3").show().find(".js_waitHour").text(e.selfMenu.leftTime):$("#menustatus_2").show()):(w.ui.none(""),
"0"==e.selfMenu.useMpOrApi&&"1"==e.selfMenu.usePersonalizedMenu?$("#menustatus_9").show():$("#menustatus_2").show(),
$(".js_menu_setting_tips").hide())):($(".js_menuBox").show(),$("#menustatus_4").show(),
$(".js_editBox").remove()):($(".js_menuBox").show(),$(".js_editBox").hide(),$(".js_startMenuBox").show(),
$(".js_openMenu").on("click",function(){
$(".js_startMenuBox").hide(),$(".js_editBox").show(),w.ui.none("点击左侧的加号，添加菜单。"),
$("#menustatus_2").show(),$(".js_addL1Btn").trigger("click");
}));
}
function i(){
"0"==MenuData.selfMenu.useMpOrApi&&"1"==MenuData.selfMenu.usePersonalizedMenu?($(".js_menustatus").hide(),
$("#menustatus_9").show()):"1"==MenuData.selfMenu.useMpOrApi&&"0"==MenuData.selfMenu.usePersonalizedMenu||"1"==MenuData.selfMenu.useMpOrApi&&"1"==MenuData.selfMenu.usePersonalizedMenu||($(".js_menustatus").hide(),
$("#menustatus_2").show());
}
function a(){
function e(e){
for(var t=0,s=0;s<e.length;s++){
var i=e.charCodeAt(s);
i>=1&&126>=i||i>=65376&&65439>=i?t++:t+=2;
}
return t;
}
$(".js_menu_name").on("blur",function(){
var t=$("#menuList").find(".selected").attr("id"),s=$.trim($(this).val()),i=e($(this).val()),n=function(t){
for(var i=1;t>=i;i++)if(e(s.substring(0,i+1))>t)return i;
};
if($(this).val(s),$(".js_titleEorTips").hide(),$("#"+t).hasClass("jslevel1")){
i>8?(s=s.substring(0,n(8)),$(this).val(s)):0==i&&(s="菜单名称",$(".js_titlenoTips").show().text("请输入菜单名称"));
var a=f.get(t);
f.edit(a,s,function(){
if($(".jslevel1").filter(".selected").attr("id")==t){
$(".js_second_title_bar h4").text(s);
var e=$("#"+t).find(".jslevel2").length;
5>e&&e>0?w.ui.innernone("已添加子菜单，仅可设置菜单名称。"):5==e&&w.ui.innernone("已为“"+$.trim(s)+"”添加了5个子菜单，无法设置其他内容。");
}
$("#"+t).children("a").find(".js_l1Title").text(s),j.refreshTips();
});
}else if($("#"+t).hasClass("jslevel2")){
var l=$(".jslevel2").filter(".selected").parents(".jslevel1").attr("id"),a=f.getSub(l,t);
i>16?(s=s.substring(0,n(16)),$(this).val(s)):0==i&&(s="子菜单名称",$(".js_titlenoTips").show().text("请输入子菜单名称")),
f.edit(a,s,function(){
$(".jslevel2").filter(".selected").attr("id")==t&&$(".js_second_title_bar h4").text(s),
$("#"+t).children("a").find(".js_l2Title").text(s),j.refreshTips();
});
}
}).on("input propertychange",function(){
$(".js_titlenoTips").hide();
var t=e($(this).val()),s=$("#menuList").find(".selected").attr("id");
$("#"+s).hasClass("jslevel1")?t>8?$(".js_titleEorTips").show():$(".js_titleEorTips").hide():$("#"+s).hasClass("jslevel2")&&(t>16?$(".js_titleEorTips").show():$(".js_titleEorTips").hide());
}),$(".js_radio_sendMsg,.js_radio_url").click(function(){
$(this).hasClass("selected")||($(".js_warn").hide(),$(this).siblings(".selected").removeClass("selected"),
$(this).addClass("selected"),$(this).hasClass("js_radio_sendMsg")?($("#edit").show(),
"0"==$(this).attr("data-editing")&&($(this).attr("data-editing","1"),w.ui.edit()),
$("#url").hide()):$(this).hasClass("js_radio_url")&&("0"==$(this).attr("data-editing")?(w.ui.url(),
$("#edit").hide(),$("#url").show(),$(this).attr("data-editing","1")):($("#edit").hide(),
$("#url").show())),j.refreshTips());
}),$(".detail_desc").click(function(){
$("#detail_pop").popup({
buttons:[{
text:"我知道了",
click:function(){
this.hide();
},
type:"primary"
}],
title:"提示"
});
});
var t=function(){
return new u({
can_use_homepage:wx.cgiData.can_use_homepage,
biz:wx.cgiData.biz,
link:1,
onOK:function(e){
if(e){
var t=e,s=t.title,i=t.link;
i&&($("#urlText").val(i).data("url",i).closest(".frm_control_group").show(),$("#js_urlTitle").show().find(".js_name").text(t.name),
s?$("#js_urlTitle").find(".js_title").text(s).parent().show():$("#js_urlTitle").find(".js_title").text("").parent().hide(),
$("#urlUnSelect").hide(),w.autoSaveEdit()),$("#js_appmsgPop").hide(),$("#js_reChangeAppmsg").css({
display:"inline-block"
});
}
}
}),!1;
};
$("#js_appmsgPop,#js_reChangeAppmsg").on("click",t),$("#urlText").on("keyup propertychange",function(){
$(".js_warn").hide();
var e=$(this),t=e.val().trim(),s=e.data("url");
s&&t==s?($("#js_urlTitle").show(),$("#js_reChangeAppmsg").show().css({
display:"inline-block"
}),$("#js_appmsgPop").hide()):($("#js_appmsgPop").show(),$("#js_reChangeAppmsg").hide(),
$("#js_urlTitle").hide(),$("#js_urlTitle").find(".js_name").text(""),$("#js_urlTitle").find(".js_title").text("")),
t&&t.match(/\:\/\/mp\.weixin\.qq\.com\/.*[\?&]tempkey=/)&&new n({
dom:this,
content:"检测到此链接为预览链接，将在短期内失效，是否仍然使用此链接？",
hideIfBlur:!0,
buttons:[{
text:"仍然使用",
type:"primary",
click:function(){
this.remove();
}
},{
text:"取消",
type:"default",
click:function(){
$this.val(""),this.remove();
}
}]
});
}).on("blur",function(){
w.autoSaveEdit();
}),$(".js_editorArea").on("keyup",function(){
$(".js_warn").hide();
});
}
return{
init:e,
refreshTips:i
};
}(),g=function(){
function e(e,s){
t[e]&&s&&s(t[e]),l.get("/merchant/electroniccardmgr?action=get&card_id="+e,function(i){
if(0==i.base_resp.ret&&i.card_detail){
var n=$.parseJSON(i.card_detail);
n=c.parse_cardticket(n),t[e]=n,s&&s(n);
}
});
}
var t={};
return{
getCardData:e
};
}(),b=function(){
function e(){
t(),n();
}
function t(){
var e="";
$("#menuList").html(template.render("tpl",f)),$(f.data).each(function(e,t){
t.subs&&t.subs.length?$("#"+t.id).find(".js_icon_menu_dot").show():$("#"+t.id).find(".js_icon_menu_dot").hide();
});
var t=parseInt($("#menuList").children(".jslevel1").length)+1,s=$("#menuList").children(".jslevel1");
s.removeClass("size1of1 size1of2 size1of3"),$("#menuList").children(".js_addMenuBox").removeClass("size1of1 size1of2 size1of3"),
3>=t?(s.addClass("size1of"+t),$("#menuList").children(".js_addMenuBox").addClass("size1of"+t)):s.addClass("size1of"+(t-1)),
$("#menuList").children(".jslevel1").length?($("#menuList").find(".js_addL1Btn").find(".js_addMenuTips").remove(),
$("#orderBt").css({
display:"inline-block"
}),$("#pubBt,#viewBt").show(),$("#menuList").removeClass("no_menu")):($("#menuList").find(".js_addL1Btn").append('<span class="js_addMenuTips">添加菜单</span>').parents("#menuList").addClass("no_menu"),
$("#orderBt,#pubBt,#viewBt,#orderDis").hide()),$(".jslevel1").length>1||$(".jslevel2").length>1?($("#orderBt").css({
display:"inline-block"
}),$("#orderDis").hide()):($("#orderBt").hide(),$(".jslevel1").length>=1&&$("#orderDis").css({
display:"inline-block"
})),e&&($("#"+e).addClass("selected"),e=""),$(".jsMenu").sortable({
items:".jslevel2",
placeholder:"menu_sub_drag_placeholder",
dropOnEmpty:!0,
start:function(e,t){
t.item.addClass("dragging");
},
stop:function(e,t){
t.item.removeClass("hover").removeClass("dragging");
}
}),$(".jsMenu").sortable("disable"),$("#menuList").sortable({
items:".jsMenu",
placeholder:"menu_drag_placeholder",
dropOnEmpty:!0,
start:function(e,t){
t.item.addClass("dragging"),t.item.siblings("li.drag_placeholder").removeClass("size1of1 size1of2 size1of3").addClass("size1of"+(parseInt($("#menuList").children(".jslevel1").length)+1));
},
stop:function(e,t){
t.item.removeClass("dragging");
}
}),$("#menuList").sortable("disable");
}
function s(){
return l;
}
function n(){
var e,s=function(){
return $(this).parent("li").hasClass("selected")?!1:(p>1&&(w.autoSaveEdit(),j.refreshTips()),
p+=$(this).siblings(".js_l2TitleBox").find(".jslevel2").length?1:2,$(".jslevel1,.jslevel2").removeClass("current"),
$(this).parent("li").addClass("current"),$("#js_rightBox").show(),$(".js_titlenoTips").hide(),
$("#js_none").hide(),$("#js_innerNone").hide(),$("#js_innerNone").siblings("div").show(),
$("#js_errTips").hide(),$(".msg_sender:visible").removeClass("error"),$(".js_radio_sendMsg:visible").attr("data-editing","0"),
$(".js_radio_url:visible").attr("data-editing","0"),$(".js_titleNolTips").show().text("字数不超过4个汉字或8个字母"),
$(".js_menuTitle").text("菜单名称"),$(".js_menuContent").text("菜单内容"),$("#jsDelBt").text("删除菜单"),
$(".js_l2TitleBox").hide(),$(this).siblings(".js_l2TitleBox").show(),5==$(this).siblings(".js_l2TitleBox").find(".jslevel2").length&&$(this).siblings(".js_l2TitleBox").find(".js_addL2Btn").parent("li.js_addMenuBox").hide(),
$("#menuList").find("li").removeClass("selected"),$(this).parent().addClass("selected"),
$(".js_menu_name").val($.trim($(this).text())),$(".js_second_title_bar h4").text($(this).text()),
l=f.get($(this).parent().eq(0).attr("id")),w.refresh(l),!1);
};
$("#menuList").on("click","li.jslevel1>a",s);
var n=function(){
return $(this).parent().hasClass("selected")?!1:(p>1&&(w.autoSaveEdit(),j.refreshTips()),
p+=1,$(".jslevel1,.jslevel2").removeClass("current"),$(this).parent("li").addClass("current"),
$("#js_rightBox").show(),$("#js_none").hide(),$(".js_titlenoTips").hide(),$("#js_innerNone").hide(),
$("#js_innerNone").siblings("div").show(),$("#js_errTips").hide(),$(".msg_sender:visible").removeClass("error"),
$(".js_radio_sendMsg:visible").attr("data-editing","0"),$(".js_radio_url:visible").attr("data-editing","0"),
$(".js_menu_name").val($.trim($(this).text())),$(".js_titleNolTips").show().text("字数不超过8个汉字或16个字母"),
$(".js_menuTitle").text("子菜单名称"),$(".js_menuContent").text("子菜单内容"),$("#jsDelBt").text("删除子菜单"),
$("#menuList").find("li").removeClass("selected"),$(this).parent().addClass("selected"),
$(".js_second_title_bar h4").text($(this).text()),l=f.getSub($(this).parents("li.jslevel1").attr("id"),$(this).parent("li.jslevel2").attr("id")),
w.refresh(l),!1);
};
$("#menuList").on("click","li.jslevel2>a",n);
var r=null,o=null;
$("#orderBt").click(function(){
$(".jslevel2").filter(".selected").length?(o=$(".jslevel2").filter(".selected").attr("id"),
r=$(".jslevel2").filter(".selected").parents(".jslevel1").attr("id")):$(".jslevel1").filter(".selected").length?(o=null,
r=$(".jslevel1").filter(".selected").attr("id")):(o=null,r=null),$(this).hide(),
$("#finishBt").css({
display:"inline-block"
}),$("#menuList").addClass("sorting"),$("#pubBt,#viewBt").hide(),$(".js_addL2Btn").parent("li.js_addMenuBox").hide(),
$(".js_addL1Btn").parent("li.js_addMenuBox").hide();
var t=parseInt($("#menuList").children(".jslevel1").length),s=$("#menuList").children(".jslevel1");
return s.removeClass("size1of1 size1of2 size1of3"),$("#menuList").children(".js_addMenuBox").removeClass("size1of1 size1of2 size1of3"),
3>=t?(s.addClass("size1of"+t),$("#menuList").children(".js_addMenuBox").addClass("size1of"+t),
$(".js_l2TitleBox:visible").find(".jslevel2").length||$(".js_l2TitleBox:visible").hide(),
$("#menuList").find("li.jslevel1>a").unbind("click").click(function(){
return $(".js_l2TitleBox").hide(),$(this).siblings(".js_l2TitleBox").find(".jslevel2").length&&$(this).siblings(".js_l2TitleBox").show(),
!1;
}),$("#menuList").find("li.jslevel2>a").unbind("click").click(function(){
return!1;
}),e=Object.clone(f.data,!0),$("#menuList").sortable("enable"),$(".jsMenu").sortable("enable"),
w.ui.rankTips(1),j.refreshTips(),!1):void 0;
}),$("#finishBt").click(function(){
$(this).hide(),$("#orderBt").css({
display:"inline-block"
}),$(".js_l2TitleBox").hide(),$("#pubBt,#viewBt").show(),$(".js_addL2Btn").parent("li.js_addMenuBox").show(),
$("#menuList").removeClass("sorting"),$("#menuList").find("li.jslevel1>a").unbind("click").click(function(){
return!1;
}),$("#menuList").find("li.jslevel2>a").unbind("click").click(function(){
return!1;
});
var e=[];
return $(".jslevel1").each(function(t,s){
var i={
id:s.id,
subs:[]
};
$(s).find(".jslevel2").each(function(e,t){
i.subs.push(t.id);
}),e.push(i);
}),f.sort(e,function(){
t(),i.suc("菜单排序成功"),$("#menuList").find("li.jslevel1>a").click(s),$("#menuList").find("li.jslevel2>a").click(n),
o&&r?($("#"+r+">a").trigger("click"),$("#"+o+">a").trigger("click")):r&&$("#"+r+">a").trigger("click"),
r=null,o=null;
}),$("#menuList").removeClass("sorting"),$("#menuList").sortable("disable"),j.refreshTips(),
w.ui.rankTips(0),!1;
}),$("#menuList").on("click",".js_addL1Btn",function(){
return $("#menuList").find(".jslevel1").length>=3?(i.err("最多只能添加三个主菜单，当前已达设置上限"),
!1):(f.add("菜单名称",function(){
t(),$(".jslevel1").last().children("a").trigger("click"),$(".jslevel1,.jslevel2").removeClass("current"),
$(".jslevel1").last().addClass("current"),$(this).parent("li").addClass("current"),
$(".js_menu_name").select(),j.refreshTips(),$(".js_menu_setting_tips").show();
}),!1);
}),$("#menuList").on("click",".js_addL2Btn",function(){
if(w.autoSaveEdit(),$(this).parent("li").siblings(".jslevel2").length>=5)return i.err("该菜单最多只能添加5个子菜单，已达到设置上限"),
!1;
var e=$(this).parents(".jslevel1").eq(0).attr("id"),s=f.get(e),n=$(this),l=function(){
f.addSub(s,"子菜单名称",function(){
t(),$("#"+n.parents(".jslevel1").attr("id")).children("a").trigger("click"),$("#"+n.parents(".jslevel1").attr("id")).find(".jslevel2").last().children("a").trigger("click"),
$(".jslevel1,.jslevel2").removeClass("current"),$("#"+n.parents(".jslevel1").attr("id")).find(".jslevel2").last().addClass("current"),
$(".js_menu_name").select();
}),j.refreshTips();
};
return s.act&&!$("#"+e).find(".jslevel2").length?a.show({
type:"warn",
msg:"子菜单确认|添加子菜单后，一级菜单的内容将被清除。确定添加子菜单？",
buttons:[{
text:"确定",
click:function(){
this.hide(),l();
}
},{
text:"取消",
type:"normal",
click:function(){
this.hide();
}
}]
}):l(),!1;
}),$("#jsDelBt").on("click",function(){
$(".js_titlenoTips").hide();
var e=$("#menuList").find(".selected").attr("id"),s="删除确认|删除后“"+$(".js_menu_name:visible").val()+"”菜单下设置的内容将被删除";
if($("#"+e).hasClass("jslevel1"))a.show({
type:"warn",
msg:s,
buttons:[{
text:"确定",
click:function(){
f.del(e,function(){
i.suc("成功删除菜单“"+$.trim($(".js_menu_name").val())+"”"),t(),j.refreshTips(),w.ui.none($("#menuList").children(".jslevel1").length?"点击左侧菜单进行编辑操作":"");
}),this.remove();
}
},{
text:"取消",
type:"normal",
click:function(){
this.hide();
}
}]
});else if($("#"+e).hasClass("jslevel2")){
var n=$("#"+e).parents("li").eq(0).attr("id");
a.show({
type:"warn",
msg:s,
buttons:[{
text:"确定",
click:function(){
f.delSub(n,e,function(){
i.suc("成功删除子菜单“"+$("#"+e).find("a").text()+"”"),w.ui.none("点击左侧菜单进行编辑操作"),t();
}),j.refreshTips(),this.remove();
}
},{
text:"取消",
type:"normal",
click:function(){
this.hide();
}
}]
});
}
return j.refreshTips(),!1;
});
}
var l;
return{
init:e,
refresh:t,
getData:s
};
}(),w=function(){
function e(){
w.ui.none("点击左侧菜单进行编辑操作"),$("#none .initialCreate").hide(),t();
}
function t(){
var e="disabled";
m&&$("#urlText").attr(e,e).parent().addClass(e),$("#sendMsg").click(function(){
return u.edit(),j.refreshTips(),!1;
}),$("#goPage").click(function(){
return u.url(),j.refreshTips(),!1;
}),$("#urlBack").click(function(){
return u.data.act?u.view():u.index(),!1;
}),$("#editBack").click(function(){
return s(b.getData()),!1;
});
}
function s(e){
if(e)switch(u.data=e,e&&e.type){
case 0:
var t=$("#"+e.id).find(".jslevel2").length;
5>t&&t>0?u.innernone("已添加子菜单，仅可设置菜单名称。"):5==t&&u.innernone("已为“"+$.trim($("#"+e.id).children("a").text())+"”添加了5个子菜单，无法设置其他内容。");
break;

case 1:
e.act?u.edit():u.index(),$(".js_radio_sendMsg").addClass("selected"),$(".js_radio_url").removeClass("selected");
break;

case 2:
u.view(),$(".js_radio_sendMsg").removeClass("selected"),$(".js_radio_url").attr("data-editing","1"),
$(".js_radio_url").addClass("selected");
break;

case 3:
e.act&&(e.act.source="file",e.act.id=e.act.file_id);
break;

default:
u.none("点击左侧菜单继续编辑");
}
}
function n(e,t){
if($("#edit").is(":visible")&&!$(".jsMsgSendTab:visible").length&&!$(".js_editorArea:visible").length||$(".js_editorArea:visible").length&&!$(".js_editorArea:visible:empty").length){
for(var s=0,n=$(".tab_content");s<n.length;s++)if($(n[s]).is(":visible")&&($($(n[s]).children(".inner").find(".edit_area")[1]).text()||$(n[s]).children(".inner").children("div").length)){
var a=_.getData(!0);
if(!a.error){
if(10==a.data.type?a.data.type=5:11==a.data.type?a.data.type=8:15==a.data.type?a.data.type=9:16==a.data.type?a.data.type=10:21==a.data.type&&(a.data.type=11),
a=a.data,u.data.act={
type:a.type,
value:a.app_id||a.file_id||a.cardid||a.content.html(!0),
_data:a
},u.data.act._data&&u.data.act._data.content&&(u.data.act._data.content=u.data.act._data.content.html(!0)),
e)return f.adapt(f.data);
f.post(function(){
t&&t();
});
}
p>1&&j.refreshTips();
break;
}
}else if($("#url").is(":visible")&&(!m&&$("#urlText").val().length||m&&$("#urlText").val().match(/http/g))){
var r=b.getData(),o=$("#urlText").val().trim();
if(""==o&&m)$("#urlUnSelect").show();else{
if($("#urlUnSelect").hide(),o.startsWith("http://")||o.startsWith("https://")||(o="http://"+o),
0==o.indexOf("http://mp.weixin.qq.com/s")&&(o=o.replace(/#rd$/,"#wechat_redirect")),
!$.validator.rules.url(o))return $("#urlFail").show(),i.err("请输入正确的URL"),-1;
if($("#urlFail").hide(),r.type=2,r.act={
type:6,
value:o,
name:$("#js_urlTitle").find(".js_name").text(),
title:$("#js_urlTitle").find(".js_title").text()
},o=encodeURIComponent(o),l.get("/misc/checkurl?url="+o+"&f=json&action=check").success(function(e){
return"10302"==e.base_resp.ret?void i.err("填写url是黑名单地址"):void 0;
}),e)return f.adapt(f.data);
f.post(function(){
$("#urlFail").hide(),t&&t();
}),p>1&&j.refreshTips();
}
}else t&&t();
}
function a(e,t){
function s(){
var s;
if(s=t.act?d(t.act):null,!e)return u.data&&u.data.act&&u.data.act.type&&6==u.data.act.type?u.url(u.data.act.value,u.data):u.edit(),
j.refreshTips(),!1;
s&&o.render("#viewDiv",s),$("#editDiv").html(""),$.support.leadingWhitespace&&document.getSelection().removeAllRanges(),
$("#edit").show();
var i,n=s?{
data:s,
acl:wx.acl.msg_acl
}:{
acl:wx.acl.msg_acl
};
if(m){
var a=$.extend(!0,{},n);
a.acl.can_text_msg=0,i=a;
}else i=n;
_=new r("#editDiv",i),$(".tab_nav").children(1).attr("onclick","return false;"),
$(".js_tab_navs").children(".tab_nav").length<=4&&$(".js_switch_next").hide(),$("a.emotion_switch").on("click",function(){
return!1;
}),$("li.tab_cardmsg").insertAfter("li.tab_img");
var l="-"+$(".js_tab_navs").css("width"),c=function(e){
e?$(".js_tab_navs").animate({
"margin-left":l
},300):$(".js_tab_navs").css({
"margin-left":l
}),$(".js_switch_next").hide(),$(".js_switch_prev").show();
};
$(".tab_nav").each(function(e,t){
return $(t).hasClass("selected")&&e>3?(c(!1),!1):!0;
}),$(".js_switch_next").click(function(){
c(!0);
}),$(".js_switch_prev").click(function(){
$(".js_tab_navs").animate({
"margin-left":"0"
},300),$(".js_switch_next").show(),$(".js_switch_prev").hide();
});
}
if(t.act&&t.act.type&&10==t.act.type&&!t.act._data){
var i=t.act.value;
g.getCardData(i,function(e){
e._className="",t.act._data=e,s();
});
}else t.act&&t.act.type&&10==t.act.type&&(t.act._data._className=""),s();
}
function d(e){
if(!e)return null;
var t=null;
return Object.each(e,function(e,s){
return s.endsWith("_data")?(t=e,!1):void 0;
}),t.type=e.type,5==t.type?t.type=10:8==t.type?t.type=11:9==t.type?t.type=15:10==t.type?t.type=16:11==t.type&&(t.type=21),
t;
}
var u={
none:function(e){
this.reset(),$("#js_rightBox").hide(),$("#js_none").show().text(e);
},
innernone:function(e){
$(".js_setNameBox").siblings("div").hide(),$("#js_innerNone").show().html(e);
},
index:function(){
this.reset(),a(!0,this.data),$("#edit").show(),$("#editDiv").children().length||(w.ui.edit(),
j.refreshTips()),$(".js_radio_sendMsg").addClass("selected"),$(".js_radio_url").addClass("selected"),
$("#url").hide();
},
url:function(e,t){
this.reset(),$("#js_urlTitle").find(".js_name").text(""),$("#js_urlTitle").find(".js_title").text(""),
m&&$("#urlText").attr("disabled","disabled").parent(".frm_input_box").addClass("disabled"),
!e&&m?$("#urlText").val("认证后才可手动输入地址"):$("#urlText").val(e&&e.html(!1)).focus(),
t&&t.act&&t.act.name?($("#js_urlTitle").show().find(".js_name").text(t.act.name),
t.act.title?$("#js_urlTitle").find(".js_title").text(t.act.title).parent().show():$("#js_urlTitle").find(".js_title").text("").parent().hide(),
$("#js_appmsgPop").hide(),m&&$("#urlText").attr("disabled","disabled").parent(".frm_input_box").addClass("disabled"),
$("#js_reChangeAppmsg").show().css({
display:"inline-block"
})):($("#js_urlTitle").hide(),$("#js_reChangeAppmsg").hide(),$("#js_appmsgPop").show()),
$("#url").show();
},
view:function(){
if(this.reset(),1==this.data.type)switch(this.data.act.type){
case 1:
$("#viewDiv").html(this.data.act.value.emoji());
break;

case 7:
$("#viewDiv").text("发送名片");
break;

default:
a(!1,this.data);
}else 2==this.data.type&&(6==u.data.act.type?u.url(u.data.act.value,u.data):u.edit());
$("#view").show();
},
initialView:function(){
0==$("#menuList").children(".jsMenu").length?($(".menu_setting_empty_wrp").show(),
$(".menu_setting_area").hide(),$(".js_totaltool_bar").hide()):($(".menu_setting_empty_wrp").hide(),
$(".menu_setting_area").show(),$(".js_totaltool_bar").show());
},
edit:function(){
this.reset(),$("#edit").show(),$(".jsmsgSenderDelBt").each(function(e,t){
$(t).parent().siblings(".jsMsgSendTab").show(),$(t).parent().remove();
}),2==this.data.type&&(this.data.type=1,this.data.act=null),a(!0,this.data);
},
reset:function(){
$(".jsMain").hide(),$("#changeBt").hide(),$("#urlFail").hide(),$("#urlUnSelect").hide();
},
rankTips:function(e){
1==e?(this.none("请通过拖拽左边的菜单进行排序"),$(".initialCreate").hide()):0==e&&this.none("点击左侧菜单进行编辑操作");
}
};
return{
init:e,
refresh:s,
getData:d,
autoSaveEdit:n,
ui:u
};
}(),x=function(){
function e(){
$("#viewBt").click(function(){
var e=$(this);
e.btn(!1);
var t=w.autoSaveEdit(!1,function(){
e.btn(!0),$("#mobileDiv:hidden").length>0&&(d.show({
spin:!1
}),$("#viewList").html(template.render("viewTpl",f)),$("#mobileDiv").show());
});
return-1==t&&e.btn(!0),!1;
}),$("#viewClose").click(function(){
return d.hide(),$("#mobileDiv").hide(),$("#viewShow").html(""),!1;
}),$("#viewList").on("click",".jsView",function(){
$(this).parent().siblings().find(".jsSubViewDiv").hide();
var e=f.get($(this).parent()[0].id);
return e&&e.act?t(e.act):$(this).parent().find(".jsSubViewDiv").toggle(),!1;
}),$("#viewList").on("click",".jsSubView",function(){
var e=f.getSub($(this).parents(".jsViewLi")[0].id,$(this).parent()[0].id);
return e&&e.act&&t(e.act),$(".jsSubViewDiv").hide(),!1;
});
var e=function(e,t){
switch(e.base_resp.ret){
case 0:
t&&(i.suc("保存并发布成功"),window.onbeforeunload=null,window.scrollTo(0,0),window.location.reload());
break;

case 8:
break;

case 9:
var s,n=e.base_resp.err_msg.split("."),l=n[0];
n[1]?(s=n[1],$(".js_l2TitleBox").hide(),$(".jslevel1").eq(l-1).children("div.js_l2TitleBox").show(),
$(".jslevel1").eq(l-1).children("div.js_l2TitleBox").find(".jslevel2").eq(s-1).children("a").trigger("click")):$(".jslevel1").eq(l-1).children("a").trigger("click"),
t?a.show({
type:"err",
msg:"发布失败|存在还未设置内容的菜单，请检查后重试",
buttons:[{
text:"确定",
click:function(){
this.hide(),$("#js_errTips").show().text("请设置当前菜单内容"),$(".msg_sender:visible").addClass("error"),
$(".media_cover,.tab_nav,.js_radio_sendMsg,.js_radio_url,.frm_input_box,.jslevel1,.jslevel2").click(function(){
$("#js_errTips").hide(200),$(".msg_sender").removeClass("error");
});
}
}]
}):($("#js_errTips").show().text("请设置当前菜单内容"),$(".msg_sender:visible").addClass("error"),
$(".media_cover,.tab_nav,.js_radio_sendMsg,.js_radio_url,.frm_input_box,.jslevel1,.jslevel2").click(function(){
$("#js_errTips").hide(200),$(".msg_sender").removeClass("error");
}));
break;

case 10:
t?a.show({
type:"err",
msg:"发布失败|自定义菜单功能处于关闭状态，无法发布",
buttons:[{
text:"确定",
click:function(){
this.hide();
}
}]
}):i.err("自定义菜单功能处于关闭状态，无法发布");
break;

case 11:
t?a.show({
type:"err",
msg:"发布失败|菜单跳转链接URL可能存在安全风险，请检查",
buttons:[{
text:"确定",
click:function(){
this.hide();
}
}]
}):i.err("菜单跳转链接URL可能存在安全风险，请检查");
break;

case 214001:
i.err("删除成功"),window.onbeforeunload=null,window.location.reload();
break;

case 214002:
i.err("删除失败");
break;

case 1530501:
$("#urlText").focus(),$(".js_warn").show();
break;

default:
t?a.show({
type:"err",
msg:"发布失败|系统繁忙，请稍后再试",
buttons:[{
text:"确定",
click:function(){
this.hide();
}
}]
}):i.err("系统繁忙，请稍后再试");
}
};
$("#pubBt").click(function(){
if($(".js_warn").hide(),$("#edit").is(":visible")&&$(".jsMsgSendTab:visible").length&&!$(".js_editorArea:visible").length)$("#js_errTips").show().text("请设置当前菜单内容"),
$(".msg_sender:visible").addClass("error");else if($(".js_editorArea:visible").length&&$(".js_editorArea:visible:empty").length||$(".js_editorTip:visible").find(".warn").length)$("#js_errTips").show().text("文字必须为1到600个字");else if($("#url").is(":visible")&&(!m&&!$("#urlText").val().length||m&&!$("#urlText").val().match(/http/g)))$("#js_errTips").show().text("请输入页面地址");else if(!$("#index").is(":visible")){
var t=function(){
a.show({
type:"warn",
msg:"发布确认|发布成功后会覆盖原版本，且将在24小时内对所有用户生效，确认发布？",
buttons:[{
text:"确定",
click:function(){
var t=w.autoSaveEdit(!0);
t?l.post({
url:"/advanced/operselfmenu?op=update_sync",
data:{
info:t,
Version:wx.cgiData.menu.version
}
},function(t){
e(t,!0);
}):l.post({
url:"/advanced/operselfmenu?op=update_sync",
data:{
info:f.adapt(f.data),
Version:wx.cgiData.menu.version
}
},function(t){
e(t,!0);
}),this.remove();
}
},{
text:"取消",
type:"normal",
click:function(){
this.hide();
}
}]
});
};
if(f.data.length>0){
var s=$(this);
s.btn(!1);
var i=w.autoSaveEdit(!0);
if(-1==i)return void s.btn(!0);
i?l.post({
url:"/advanced/operselfmenu?op=check",
data:{
info:i,
Version:wx.cgiData.menu.version
}
},function(i){
s.btn(!0),e(i,!1),0==i.base_resp.ret?(s.btn(!0),t()):11==i.base_resp.ret&&a.show({
type:"warn",
msg:"清空确认|该版本菜单内容含非法URL，若要继续使用自定义菜单功能，须清空整个菜单内容，确认清空？",
buttons:[{
text:"确定",
click:function(){
l.post({
url:"/advanced/operselfmenu?op=delete",
data:{
Version:wx.cgiData.menu.version
}
},function(t){
e(t,!1);
}),this.remove();
}
},{
text:"取消",
type:"normal",
click:function(){
this.hide();
}
}]
});
}):l.post({
url:"/advanced/operselfmenu?op=check",
data:{
info:f.adapt(f.data),
Version:wx.cgiData.menu.version
}
},function(i){
s.btn(!0),e(i,!1),0==i.base_resp.ret&&t();
});
}
}
return $(".media_cover,.tab_nav,.js_radio_sendMsg,.js_radio_url,.frm_input_box,.jslevel1,.jslevel2").click(function(){
$("#js_errTips").hide(200),$(".msg_sender").removeClass("error");
}),!1;
});
}
function t(e){
var t={
src:$(".head .avatar").attr("src"),
id:"_view_"+1*new Date
};
return e&&e.type&&6==e.type?void window.open(e.value.html(!1)):($("#viewShow").append(template.compile(s)(t)).parent().scrollTop($("#viewShow")[0].scrollHeight),
e&&e.type&&1==e.type?void $("#"+t.id).html(e.value.emoji()):void(e&&e.type&&10==e.type?g.getCardData(e.value,function(s){
s._className="small_card",e._data=s,s&&o.render.defer("#"+t.id,w.getData(e));
}):o.render.defer("#"+t.id,w.getData(e))));
}
var s='<li class="show_item"><img class="avatar" src="{src}"><div class="show_content" id="{id}"></div></li>';
return{
init:e
};
}(),y=function(){
var e="";
if("1"==wx.cgiData.authrized){
var t=wx.cgiData.auth_info;
t&&t.length>0?$.each(t,function(t,s){
e.length>0&&(e+="、"),e+=s.component_name;
}):e="未知",$(".js_authorized").show().find(".js_auth_name").text(e);
}
};
v.init(),window.onbeforeunload=function(e){
e=e||window.event;
var t="";
if($.support.leadingWhitespace&&!$("#menustatus_3:visible").length&&!$("#menustatus_10:visible").length&&!$("#menustatus_8:visible").length){
if(w.autoSaveEdit(),$("#edit").is(":visible")){
for(var s=0,e=$(".tab_content");s<e.length;s++)if($(e[s]).is(":visible")){
if($(e[s]).children(".inner").children("div").not(".jsMsgSendTab").length){
t="已自动为你保存“"+$(".js_menu_name:visible").val()+"”菜单的内容";
break;
}
t="当前菜单“"+$(".js_menu_name:visible").val()+"”还未设置内容";
break;
}
}else $("#url").is(":visible")&&(t=$.trim($("#urlText:visible").val())?"已自动为你保存“"+$(".js_menu_name:visible").val()+"”菜单的内容":"当前菜单“"+$(".js_menu_name:visible").val()+"”还未设置内容");
if(t)return e&&(e.returnValue=t),t;
}
};
});