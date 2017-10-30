define("common/wx/media/videoDialog.js",["common/wx/popup.js","page/smallvideo/dialog_select_video.css","widget/media/media_dialog.css","common/wx/top.js","common/wx/Tips.js","common/wx/media/video.js","common/wx/pagebar.js","media/media_cgi.js","tpl/media/dialog/videomsg_layout.html.js"],function(e){
"use strict";
function i(e,i,t){
e=e.replace(/^\s+|\s+$/g,""),e=e.replace(/^v\.qq\.com/,"http://v.qq.com"),-1!=e.indexOf("http://v.qq.com")?n(e,i,t):(-1!=e.indexOf("http://www.weishi.com")||-1!=e.indexOf("http://weishi.com")||-1!=e.indexOf("http://www.weishi.qq.com")||-1!=e.indexOf("http://weishi.qq.com"))&&d(e,i,t);
}
function t(e,i){
var i=i||document.location.toString(),t=e+"=",o=i.indexOf(t);
if(-1!=o){
var n=i.indexOf("&",o),d=i.indexOf("?",o);
return-1!=d&&(-1==n||n>d)&&(n=d),d=i.indexOf("#",o),-1!=d&&(-1==n||n>d)&&(n=d),-1==n?i.substring(o+t.length):i.substring(o+t.length,n);
}
return"";
}
function o(e){
e=e||window.location.toString();
var i,o=t("vid",e);
return o||(i=e.match(/\/\w{15}\/(\w+)\.html/))&&(o=i[1]),o||((i=e.match(/\/page\/\w{1}\/\w{1}\/\w{1}\/(\w+)\.html/))?o=i[1]:(i=e.match(/\/(page|play)\/+(\w{11})\.html/))&&(o=i[2])),
o||(i=e.match(/\/boke\/gplay\/\w+_\w+_(\w+)\.html/))&&(o=i[1]),encodeURIComponent(o);
}
function n(e,i,t){
var n,d,s="",a=t.width,c=t.height;
if(n=e.match(new RegExp("(^|&|\\\\?)vid=([^&]*)(&|$|#)")))s=encodeURIComponent(n[2]),
t.vid=s,d="http://v.qq.com/iframe/player.html?vid="+s+"&width="+a+"&height="+c+"&auto=0",
i(d,t);else if((n=e.match(new RegExp("(http://)?v\\.qq\\.com/cover[^/]*/\\w+/([^/]*)\\.html")))||(n=e.match(new RegExp("(http://)?v\\.qq\\.com/prev[^/]*/\\w+/([^/]*)\\.html")))){
var r=encodeURIComponent(n[2]),m="https://sec.video.qq.com/p/sns.video/fcgi-bin/dlib/dataout_ex?auto_id=137&cid="+r+"&otype=json",l=document.getElementsByTagName("head")[0],h=document.createElement("script");
h.type="text/javascript",h.src=m,h.async=!0,void 0!==h.onreadystatechange?h.onreadystatechange=function(){
if("loaded"==h.readyState)try{
s=QZOutputJson.videos[0].vid,t.vid=s,d="http://v.qq.com/iframe/player.html?vid="+s+"&width="+a+"&height="+c+"&auto=0",
i(d,t);
}catch(e){}
}:h.onload=function(){
try{
s=QZOutputJson.videos[0].vid,t.vid=s,d="http://v.qq.com/iframe/player.html?vid="+s+"&width="+a+"&height="+c+"&auto=0",
i(d,t);
}catch(e){}
},l.appendChild(h);
}else s=o(e),""!=s&&(t.vid=s,d="http://v.qq.com/iframe/player.html?vid="+s+"&width="+a+"&height="+c+"&auto=0",
i(d,t));
}
function d(e,i,t){
var o,n="",d=t.width,s=t.height,c=e.match(/\/t\/(\d+)/)||e.match(/\/#show\/(\d+)/);
c&&"object"==typeof c&&c.length>1?(n=c[1],t.vid=n,o="http://z.weishi.com/weixin/player.html?msgid="+n+"&width="+d+"&height="+s+"&auto=0",
i(o,t)):a.err("微视频仅支持 www.weishi.com/t 开头的网址");
}
e("common/wx/popup.js"),e("page/smallvideo/dialog_select_video.css"),e("widget/media/media_dialog.css");
var s=e("common/wx/top.js"),a=e("common/wx/Tips.js"),c=e("common/wx/media/video.js"),r=e("common/wx/pagebar.js"),m=e("media/media_cgi.js"),l=e("tpl/media/dialog/videomsg_layout.html.js"),h=15,p=21,v={};
v[h]="video_msg_cnt",v[p]="short_video_cnt";
var f=function(e,i){
var t=$.extend({},i);
return t.selector=e,t.id=t.app_id,t.tpl="videomsg",t.for_selection=!0,t.for_transfer=!!t.content,
t.hide_transfer=!!t.content,t.video_id=t.content,t.source="file",new c(t);
},u=548,_=280,g=function(e){
this.scene=e.scene||"default",this.onOK=e.onOK,this.can_use_shortvideo=e.can_use_shortvideo,
this.create();
},w={
create:function(){
var e=this,t=$.parseHTML(wx.T(l,{
scene:e.scene,
token:wx.data.t
}));
e.dialog&&e.dialog.popup("remove"),e.dialog=$(t[0]).popup({
title:"选择视频",
className:"dialog_select_video",
width:960,
onOK:function(){
var t=this,o=e.$dom.find(".js_top.selected").data("id"),n=e.$dom.find(".Js_videomsg.selected").parent().data("opt"),d=e.$dom.find(".js_video_txurl").val();
if(o){
if(!n)return a.err("请选择视频"),!0;
if(e.onOK&&!e.onOK(o,n))return!0;
t.remove(),e.dialog=null;
}else{
if(!d)return a.err("请输入视频网址"),!0;
var s=!1;
if(i(d,function(i,n){
if(0==i.indexOf("http://v.qq.com/")){
var d=i.match(/[\?&]vid\=([^&]*)/);
if(null!=d&&d[1]){
var c=d[1],r=e.$dom.find(".js_btn");
c?(r.attr("disabled",!0),$.ajax({
url:wx.url("/cgi-bin/registertopic?id="+c+"&type=2"),
type:"post",
dataType:"json",
success:function(d){
console.log("success"),console.log(d),d&&d.base_resp&&0==d.base_resp.ret?(n=$.extend({
url:i
},n),e.onOK&&e.onOK(o,n),t.remove(),e.dialog=null):a.err("系统繁忙，请稍后再试");
},
error:function(){
a.err("系统繁忙，请稍后再试");
},
complete:function(){
r.attr("disabled",!1);
}
})):a.err("无效视频地址");
}else a.err("无效视频地址");
s=!0;
}
return s?!0:(n=$.extend({
url:i
},n),e.onOK&&e.onOK(o,n),t.remove(),void(e.dialog=null));
},{
width:500,
height:375,
align:"none"
}),s)return!0;
}
},
onCancel:function(){
this.remove(),e.dialog=null;
},
onHide:function(){
this.remove(),e.dialog=null;
}
}),e.$dom=e.dialog.popup("get"),e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled"),
e.init(),e.dialog.popup("resetPosition");
},
init:function(){
var e=this,i=e.can_use_shortvideo?[{
name:"小视频",
id:p
}]:[];
"ueditor"==e.scene?(i.unshift({
name:"视频网址"
}),e.initTencentVideo()):(i.unshift({
name:"视频",
id:h
}),e.getList(h,0,10)),e.tab=new s(e.$dom.find(".js_videotab"),i),e.tab.selected(0),
e.tab.dom.find("a").on("click",function(e){
e.preventDefault();
}),e.$dom.on("click",".js_top",function(){
var i=$(this).data("id");
e.$dom.find(".js_video_status").hide(),e.$dom.find(".js_video_create").hide(),e.$dom.find(".js_pagebar").empty(),
e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled"),i?(i==h&&e.$dom.find(".js_video_create").show(),
e.getList(i,0,10)):e.$dom.find(".js_video_tencent").show(),e.tab.selected($(this).data("index"));
}),e.$dom.on("click",".Js_videomsg",function(){
e.$dom.find(".Js_videomsg.selected").removeClass("selected"),e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"),
$(this).addClass("selected");
}),e.$dom.on("mousewheel","#js_videomsg_list",function(e){
this.scrollTop-=e.originalEvent.wheelDelta>0?60:-60,e.preventDefault();
}).on("DOMMouseScroll","#js_videomsg_list",function(e){
this.scrollTop+=e.originalEvent.detail>0?60:-60,e.preventDefault();
});
},
initTencentVideo:function(){
var e=this;
e.$dom.find(".js_video_loading").hide(),e.$dom.find(".js_video_tencent").show(),
e.$dom.find(".js_video_txurl").on("input propertychange",function(){
var t=$(this).val();
t?(e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"),i(t,function(i){
var t="<iframe height="+_+" width="+u+' frameborder=0 src="'+i+'" allowfullscreen></iframe>';
e.$dom.find(".js_video_preview").html(t),e.dialog.popup("resetPosition");
},{
width:u,
height:_
})):e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled");
});
},
initPagebar:function(e,i,t){
var o=this,n=e/i+1;
return i>=t?void o.$dom.find(".js_pagebar").hide():void new r({
container:o.$dom.find(".js_pagebar").show(),
perPage:i,
first:!1,
last:!1,
isSimple:!0,
initShowPage:n,
totalItemsNum:t,
callback:function(t){
var d=t.currentPage,s=o.$dom.find(".js_top.selected").data("id");
d!=n&&s&&(e=i*--d,o.getList(s,e,i));
}
});
},
getList:function(e,i,t){
var o=this,n=e==h?m.appmsg:m;
o.$dom.find(".js_video_content").hide(),o.$dom.find(".js_video_loading").show(),
n.getList(e,i,t,function(n){
if(o.dialog&&e==o.$dom.find(".js_top.selected").data("id")){
var d=n.file_item||n.item,s=o.$dom.find("#js_videomsg_list").find(".inner").empty();
d.length?(d.each(function(e,i){
var t=s.eq(i%2),o=$('<div id="appmsg%s"></div>'.sprintf(e.app_id||e.file_id),t).appendTo(t);
f(o,e);
}),o.$dom.find(".js_video_content").show(),o.dialog.popup("resetPosition")):o.$dom.find(".js_video_none").show().find(".js_empty_tips").html(e==p?"暂无素材<br />（素材来源：通过微信用户上传给公众帐号）":"暂无素材"),
o.$dom.find(".js_video_loading").hide(),o.initPagebar(i,t,n.file_cnt[v[e]]);
}
});
}
};
return $.extend(g.prototype,w),g;
});