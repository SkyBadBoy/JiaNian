define("tpl/media/dialog/appmsg_layout.html.js",[],function(){
return'<div class="dialog_media_container appmsg_media_dialog">\n    <div class="sub_title_bar in_dialog">\n        <div class="search_bar">\n            <span class="frm_input_box search with_del append">\n                <a class="del_btn dn" href="javascript:" id="searchCloseBt"><i class="icon_search_del"></i>&nbsp;</a>\n                <a id="msgSearchBtn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">搜索</i>&nbsp;</a>\n                <input id="msgSearchInput" type="text" placeholder="标题/作者/摘要" value="" class="frm_input">\n            </span>\n        </div>\n        <div class="appmsg_create tr">\n            {if type==10}\n            <!--\n            <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&lang=zh_CN&token={token}">\n                <i class="icon_appmsg_small"></i><strong>新建单图文消息</strong>\n            </a>\n            -->\n            <a class="btn btn_primary btn_add" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=1&lang=zh_CN&token={token}">\n                <i class="icon14_common add_white"></i>新建图文消息            </a>\n            {else if type==11}\n            <a class="btn btn_primary btn_add" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&lang=zh_CN&token={token}">\n                <i class="icon14_common add_white"></i>新建单商品消息            </a>\n            <a class="btn btn_primary btn_add" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&isMul=1&lang=zh_CN&token={token}">\n                <i class="icon14_common add_white"></i>新建多商品消息            </a>\n            {/if}\n        </div>\n    </div>\n    <div class="dialog_media_inner">\n        {if tpl=="loading"}\n        <i class="icon_loading_small white">loading...</i>\n        {else if tpl=="no-media"}\n        <div class="no_media_wrp">\n            <p class="tips">暂无素材</p>\n        </div>\n        <span class="vm_box"></span>\n        {else}\n        <div class="js_appmsg_list appmsg_list media_dialog">\n            <div class="appmsg_col"><div class="inner"></div></div>&nbsp;\n            <div class="appmsg_col"><div class="inner"></div></div>\n        </div>\n        <div class="pagination_wrp pageNavigator"></div>\n        {/if}\n    </div>\n</div>\n';
});define("tpl/media/dialog/file_layout.html.js",[],function(){
return'<div class=\'dialog_media_container {if tpl=="no-media"}no_media{/if}\'>\n    {if tpl=="loading"}\n    <i class="icon_loading_small white">loading...</i>\n    {else if tpl=="no-media"}\n    <div class="no_media_wrp">\n        <p class="tips">\n        暂无素材        </p>\n        <div class="upload_box">\n            <span class="upload_area"><a id="js_media_dialog_upload{upload_id}" class="btn btn_upload" data-gid="">上传</a></span>\n            <div class="bubble_tips bubble_right warn">\n                <div class="bubble_tips_inner">\n                    {if type=="2"}\n                        大小不超过5M                    {/if}\n                    {if type=="3"}\n                        大小: 不超过5M,&nbsp;&nbsp;&nbsp;&nbsp;长度: 不超过60s,&nbsp;&nbsp;&nbsp;&nbsp;格式: mp3, wma, wav, amr                    {/if}\n                    {if type=="4"}\n                        大小: 不超过20M,&nbsp;&nbsp;&nbsp;&nbsp;格式: rm, rmvb, wmv, avi, mpg, mpeg, mp4                    {/if}\n                </div> \n                <i class="bubble_tips_arrow out"></i>\n                <i class="bubble_tips_arrow in"></i>\n            </div>\n        </div>\n    </div>\n    <span class="vm_box"></span>\n    {else}\n    <div class="sub_title_bar in_dialog">\n        <div class="search_bar dn">\n            <span class="frm_input_box search with_del append">\n                <a class="del_btn" href="javascript:"><i class="icon_search_del"></i>&nbsp;</a>\n                <a id="msgSearchBtn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">搜索</i>&nbsp;</a>\n                <input id="msgSearchInput" type="text" placeholder="关键字" value="" class="frm_input">\n            </span>\n        </div>\n        <div class="upload_box">\n            <span class="upload_area"><a id="js_media_dialog_upload{upload_id}" class="btn btn_upload" data-gid="">上传</a></span>&nbsp;\n            <div class="bubble_tips bubble_left warn">\n                <div class="bubble_tips_inner">\n                    {if type=="2"}\n                        大小: 不超过5M,&nbsp;&nbsp;&nbsp;&nbsp;格式: bmp, png, jpeg, jpg, gif                    {/if}\n                    {if type=="3"}\n                        大小: 不超过5M,&nbsp;&nbsp;&nbsp;&nbsp;长度: 不超过60s,&nbsp;&nbsp;&nbsp;&nbsp;格式: mp3, wma, wav, amr                    {/if}\n                    {if type=="4"}\n                        大小: 不超过20M,&nbsp;&nbsp;&nbsp;&nbsp;格式: rm, rmvb, wmv, avi, mpg, mpeg, mp4                    {/if}\n                </div>\n                <i class="bubble_tips_arrow out"></i>\n                <i class="bubble_tips_arrow in"></i>\n            </div>\n        </div>&nbsp;\n    </div>\n    <ul class=\'dialog_media_list js_media_list\'></ul>\n    <div class="pagination_wrp pageNavigator"></div>\n    {/if}\n</div>\n';
});define("cardticket/send_card.js",["common/wx/popup.js","common/wx/Step.js","cardticket/send_card_table.js","tpl/cardticket/send_card.html.js"],function(e){
"use strict";
var t=(e("common/wx/popup.js"),{
removeOnHide:!0,
view_mode:window.view_mode||0
}),p=(e("common/wx/Step.js"),function(e){
this.opt=$.extend(!0,{},t,e),this.init();
}),o=e("cardticket/send_card_table.js");
return p.prototype={
_html:e("tpl/cardticket/send_card.html.js"),
init:function(){
var e=this.opt,t=this,p=$(template.compile(this._html)()).popup({
title:"选择卡券",
autoShow:!1,
buttons:[{
text:"确定",
type:"primary",
click:function(){
t.sendCardTable.select();
}
},{
text:"取消",
type:"default",
click:function(){
t.sendCardTable.isLoading()||this.hide();
}
}],
onHide:function(){
e.onHide&&e.onHide.call(t),e.removeOnHide&&this.remove();
},
className:"send_card align_edge",
width:960
});
if(this.$send_popup=p,e.container=this.$send_popup,e.pageChanged=function(){
t.$send_popup.popup("resetPosition");
},e.getDataComplete=function(){
t.$send_popup.popup("resetPosition");
},e.selectComplete){
var n=e.selectComplete;
e.selectComplete=function(){
n.call(t,arguments[0],arguments[1],arguments[2]),t.hide();
};
}else e.selectComplete=function(){
t.hide();
};
e.hidePopup=function(){
t.$send_popup.popup("hide");
},this.sendCardTable=new o(e);
},
show:function(){
this.$send_popup.popup("show"),this.$send_popup.popup("resetPosition");
},
hide:function(){
this.$send_popup.popup("hide");
},
destroy:function(){
this.$send_popup.popup("remove");
}
},p;
});define("common/wx/media/videoDialog.js",["common/wx/popup.js","page/smallvideo/dialog_select_video.css","widget/media/media_dialog.css","common/wx/top.js","common/wx/Tips.js","common/wx/media/video.js","common/wx/pagebar.js","common/wx/time.js","media/media_cgi.js","tpl/media/dialog/videomsg_layout.html.js","tpl/media/videocard.html.js"],function(e){
"use strict";
function i(e){
return e&&e.substr&&"04"==e.substr(1,2)?!0:!1;
}
function t(e,i,t,o){
e=e.replace(/^\s+|\s+$/g,""),e=e.replace(/^http:/,"https:"),e=e.replace(/^v\.qq\.com/,"https://v.qq.com"),
(-1!=e.indexOf("http://v.qq.com")||-1!=e.indexOf("https://v.qq.com"))&&d(e,i,t,o);
}
function o(e,i){
var i=i||document.location.toString(),t=e+"=",o=i.indexOf(t);
if(-1!=o){
var n=i.indexOf("&",o),d=i.indexOf("?",o);
return-1!=d&&(-1==n||n>d)&&(n=d),d=i.indexOf("#",o),-1!=d&&(-1==n||n>d)&&(n=d),-1==n?i.substring(o+t.length):i.substring(o+t.length,n);
}
return"";
}
function n(e){
e=e||window.location.toString();
var i,t=o("vid",e);
return t||(i=e.match(/\/\w{15}\/(\w+)\.html/))&&(t=i[1]),t||((i=e.match(/\/page\/\w{1}\/\w{1}\/\w{1}\/(\w+)\.html/))?t=i[1]:(i=e.match(/\/(page|play)\/+(\w{11})\.html/))&&(t=i[2])),
t||(i=e.match(/\/boke\/gplay\/\w+_\w+_(\w+)\.html/))&&(t=i[1]),encodeURIComponent(t);
}
function d(e,i,t,o){
var d,s,a="",r=t.width,m=t.height;
if(d=e.match(new RegExp("(^|&|\\\\?)vid=([^&]*)(&|$|#)")))a=encodeURIComponent(d[2]),
-1!=a.indexOf("_")&&(a=a.split("_")[0]),/[a-zA-Z0-9]{11}/.test(a)||BJ_REPORT.monitor(94,"vid:"+a+";url="+e,39),
t.vid=a,s="https://v.qq.com/iframe/preview.html?vid="+a+"&width="+r+"&height="+m+"&auto=0",
i(s,t);else if((d=e.match(new RegExp("(http://)?v\\.qq\\.com/cover[^/]*/\\w+/([^/]*)\\.html")))||(d=e.match(new RegExp("(http://)?v\\.qq\\.com/prev[^/]*/\\w+/([^/]*)\\.html")))){
var l=encodeURIComponent(d[2]),c="https://data.video.qq.com/fcgi-bin/data?tid=554&appid=20001184&appkey=85a707e3a07cc44d&otype=json&idlist="+l,_=document.getElementsByTagName("head")[0],p=document.createElement("script");
p.type="text/javascript",p.src=c,p.async=!0,void 0!==p.onreadystatechange?p.onreadystatechange=function(){
if("loaded"==p.readyState)try{
a=QZOutputJson.results[0].fields.video_ids[0],-1!=a.indexOf("_")&&(a=a.split("_")[0]),
/[a-zA-Z0-9]{11}/.test(a)||BJ_REPORT.monitor(94,"vid:"+a+";url="+e,39),t.vid=a,s="https://v.qq.com/iframe/preview.html?vid="+a+"&width="+r+"&height="+m+"&auto=0",
i(s,t);
}catch(o){}
}:p.onload=function(){
try{
a=QZOutputJson.results[0].fields.video_ids[0],-1!=a.indexOf("_")&&(a=a.split("_")[0]),
/[a-zA-Z0-9]{11}/.test(a)||BJ_REPORT.monitor(94,"vid:"+a+";url="+e,39),t.vid=a,s="https://v.qq.com/iframe/preview.html?vid="+a+"&width="+r+"&height="+m+"&auto=0",
i(s,t);
}catch(o){}
},_.appendChild(p);
}else a=n(e),""!=a?(-1!=a.indexOf("_")&&(a=a.split("_")[0]),/[a-zA-Z0-9]{11}/.test(a)||BJ_REPORT.monitor(94,"vid:"+a+";url="+e,39),
t.vid=a,s="https://v.qq.com/iframe/preview.html?vid="+a+"&width="+r+"&height="+m+"&auto=0",
i(s,t)):!!o&&o(-1);
}
e("common/wx/popup.js"),e("page/smallvideo/dialog_select_video.css"),e("widget/media/media_dialog.css");
var s=e("common/wx/top.js"),a=e("common/wx/Tips.js"),r=e("common/wx/media/video.js"),m=e("common/wx/pagebar.js"),l=e("common/wx/time.js"),c=e("media/media_cgi.js"),_=e("tpl/media/dialog/videomsg_layout.html.js"),p=e("tpl/media/videocard.html.js"),v=15,f=21,u=0,h={};
h[v]="video_msg_cnt",h[f]="short_video_cnt";
var g=function(e,i){
var t=$.extend({},i.multi_item?i.multi_item[0]:i);
t.selector=e,t.id=i.app_id,t.app_id=i.app_id,t.tpl="videomsg",t.for_selection=1!=t.is_new_video?!0:3==t.status,
t.for_transfer=!!t.content,t.hide_transfer=!!t.content,t.video_id=t.content,t.source="file",
1==t.is_new_video?(t.time=i.create_time?l.timeFormat(i.create_time):"",t.before_original_video=i.create_time<1453914e3?1:0,
e.html(wx.T(p,t))):(t.create_time=i.create_time,t.img_url=i.img_url,new r(t)),$("#wxVideoBox"+t.id).data("opt",t);
},w=548,j=280,x=function(e){
console.log(e),this.scene=e.scene||"default",this.onOK=e.onOK,this.can_use_shortvideo=e.can_use_shortvideo,
this.can_use_txvideo=e.can_use_txvideo,this.create();
},b={
create:function(){
var e=this,o=$.parseHTML(wx.T(_,{
scene:e.scene,
token:wx.data.t
}));
e.dialog&&e.dialog.popup("remove"),e.dialog=$(o[0]).popup({
title:"选择视频",
className:"dialog_select_video",
width:960,
onOK:function(){
var o=this,n=e.$dom.find(".js_top.selected").data("id"),d=e.$dom.find(".Js_videomsg.selected").data("opt")||e.$dom.find(".Js_videomsg.selected").parent().data("opt"),s=e.$dom.find(".js_video_txurl").val();
if(n&&d&&1==d.is_new_video&&3!=d.status)return a.err("该视频目前无法被选择，请选择其它视频"),!0;
if(n&&d&&0==d.is_new_video&&(0!=d.is_new_video||!d.content_url))return a.err("该视频转码未完成，请选择其它视频"),
!0;
if(n){
if(!d)return a.err("请选择视频"),!0;
if(e.onOK&&!e.onOK(n,d))return!0;
o.remove(),e.dialog=null;
}else{
if(!s)return a.err("请输入视频网址"),!0;
if(-1==s.indexOf("v.qq.com/"))return a.err("请输入腾讯视频网址"),!0;
var r=!1;
if(t(s,function(t,d){
if(0==t.indexOf("http://v.qq.com/")||0==t.indexOf("https://v.qq.com/")){
var s=t.match(/[\?&]vid\=([^&]*)/);
if(null!=s&&s[1]){
var m=s[1];
if(i(m))return a.err("该网址为腾讯微博视频网址，此处引用视频只支持腾讯视频"),r=!0,!0;
var l=e.$dom.find(".js_btn");
m?(l.attr("disabled",!0),$.ajax({
url:wx.url("/cgi-bin/registertopic?id="+m+"&type=2"),
type:"post",
dataType:"json",
success:function(i){
i&&i.base_resp&&0==i.base_resp.ret?(d=$.extend({
url:t
},d),e.onOK&&e.onOK(n,d),o.remove(),e.dialog=null):a.err("系统繁忙，请稍后再试");
},
error:function(){
a.err("系统繁忙，请稍后再试");
},
complete:function(){
l.attr("disabled",!1);
}
})):a.err("无效视频地址");
}else a.err("无效视频地址");
r=!0;
}
return r?!0:(d=$.extend({
url:t
},d),e.onOK&&e.onOK(n,d),o.remove(),void(e.dialog=null));
},{
width:500,
height:375,
align:"none"
},function(){
r=!0,a.err("该网址存在错误，请填写正确的腾讯视频网址");
}),r)return!0;
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
id:f
}]:[];
"ueditor"==e.scene?(u=1,i.unshift({
name:"视频网址"
}),e.initTencentVideo()):(i.unshift({
name:"视频",
id:v
}),e.getList(v,0,10)),"ueditor"==e.scene&&1==e.can_use_txvideo?(i.unshift({
name:"视频",
id:v
}),e.getList(v,0,10),e.$dom.find(".js_video_tencent").hide()):$(".js_video_status").find(".frm_tips").html("支持腾讯视频"),
e.tab=new s(e.$dom.find(".js_videotab"),i),e.tab.selected(0),e.tab.dom.find("a").on("click",function(e){
e.preventDefault();
}),e.$dom.on("click",".js_top",function(){
var i=$(this).data("id");
e.$dom.find(".js_video_status").hide(),e.$dom.find(".js_video_create").hide(),e.$dom.find(".js_pagebar").empty(),
e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled"),i?(i==v&&e.$dom.find(".js_video_create").show(),
e.getList(i,0,10)):e.$dom.find(".js_video_tencent").show(),e.tab.selected($(this).data("index"));
}),e.$dom.on("click",".Js_videomsg",function(){
e.$dom.find(".Js_videomsg.selected").removeClass("selected"),e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"),
$(this).addClass("selected");
}),e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"),e.$dom.on("mousewheel","#js_videomsg_list",function(e){
this.scrollTop-=e.originalEvent.wheelDelta>0?60:-60,e.preventDefault();
}).on("DOMMouseScroll","#js_videomsg_list",function(e){
this.scrollTop+=e.originalEvent.detail>0?60:-60,e.preventDefault();
});
},
initTencentVideo:function(){
var e=this;
e.$dom.find(".js_video_loading").hide(),e.$dom.find(".js_video_tencent").show(),
e.$dom.find(".js_video_txurl").on("input propertychange",function(){
var o=$(this).val();
o?(e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"),t(o,function(t){
var o=t.match(/[\?&]vid\=([^&]*)/);
if(null!=o&&o[1]){
var n=o[1];
if(i(n))return a.err("该网址为腾讯微博视频网址，此处引用视频只支持腾讯视频"),!0;
}
var d="<iframe height="+j+" width="+w+' frameborder=0 src="'+t+'" allowfullscreen></iframe>';
e.$dom.find(".js_video_preview").html(d),e.dialog.popup("resetPosition");
},{
width:w,
height:j
})):e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled");
});
},
initPagebar:function(e,i,t){
var o=this,n=e/i+1;
return i>=t?void o.$dom.find(".js_pagebar").hide():void new m({
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
var o=this,n=e==v?c.appmsg:c;
o.$dom.find(".js_video_content").hide(),o.$dom.find(".js_video_loading").show(),
n.getList(e,i,t,function(n){
if(o.dialog&&e==o.$dom.find(".js_top.selected").data("id")){
var d=n.file_item||n.item,s=o.$dom.find("#js_videomsg_list").find(".inner").empty();
d.length?(d.each(function(e,i){
var t=s.eq(i%2),o=$('<div id="appmsg%s"></div>'.sprintf(e.app_id||e.file_id),t).appendTo(t);
g(o,e);
}),o.$dom.find(".js_video_content").show(),o.dialog.popup("resetPosition")):o.$dom.find(".js_video_none").show().find(".js_empty_tips").html(e==f?"暂无素材<br />（素材来源：通过微信用户上传给公众帐号）":"暂无素材"),
o.$dom.find(".js_video_loading").hide(),o.initPagebar(i,t,n.file_cnt[h[e]]||n.file_cnt.video_cnt);
}
},"",u);
}
};
return $.extend(x.prototype,b),x;
});define("common/wx/media/imageDialog.js",["common/wx/Cgi.js","common/wx/Tips.js","common/wx/popup.js","common/wx/pagebar.js","biz_web/utils/upload.js","common/wx/tooltips.js","tpl/media/dialog/image_layout.html.js","tpl/media/dialog/image_list.html.js","tpl/media/dialog/image_group.html.js","tpl/media/dialog/image_water.html.js","page/media/dialog_img_pick.css"],function(e){
"use strict";
var i=e("common/wx/Cgi.js"),t=e("common/wx/Tips.js"),n=(e("common/wx/popup.js"),
e("common/wx/pagebar.js")),o=e("biz_web/utils/upload.js"),a=e("common/wx/tooltips.js"),r=e("tpl/media/dialog/image_layout.html.js"),s=e("tpl/media/dialog/image_list.html.js"),l=e("tpl/media/dialog/image_group.html.js"),d=e("tpl/media/dialog/image_water.html.js"),c=(template.render,
template.compile(l)),g=template.compile(s);
e("page/media/dialog_img_pick.css");
var p=function(e){
return new u(e);
},u=function(e){
this.options=e,this.events=[],this.imgArr=[],this.converting=0,this.fromUpload=[],
m.init.call(this);
},m={
init:function(){
var e=this,i=e.options=$.extend(!0,{
tpl:r,
title:"选择图片",
scene:"cdn",
maxSelect:1,
perPage:10,
group:1,
onOK:null,
onCancel:null
},e.options);
i.tpl=template.compile(i.tpl)(i),e.on("ok",i.onOK),e.on("cancel",i.onCancel),e.on("hide",i.onHide),
e.dialog=$(i.tpl.trim()).popup({
title:i.title,
className:"img_dialog_wrp",
width:846,
buttons:[{
text:"确定",
type:"disabled",
click:function(){
var n=this.get().find(".js_btn").eq(0).parent();
return n.hasClass("btn_disabled")?void t.err("请选择图片"):(e.popup=this,$.each(e.imgArr,function(i,t){
t.source=-1!=e.fromUpload.indexOf(t.file_id+"")?"upload":"lib";
}),void("cdn"==i.scene&&e.converting>0?(n.btn(!1),e.on("converted",function(){
0==e.converting&&(e.trigger("ok",e.imgArr||[]),n.btn(!0));
})):e.trigger("ok",e.imgArr||[])));
}
},{
text:"取消",
click:function(){
e.trigger("cancel"),this.hide();
}
}],
onHide:function(){
e.trigger("hide");
}
}),e.dialog.popup("get").find(".js_loading").show(),f.getImagesByGroupId({
group_id:i.group,
count:i.perPage
},function(t){
if(e.dialog){
var n=t.page_info;
n.scene=i.scene,n.group=i.group;
var o=e.dialog.popup("get"),a=c(n);
o.find(".js_loading").hide(),o.find(".js_group").append(a).find(".js_total").text("(%s)".sprintf(n.file_cnt.img_cnt)),
m.renderImageList(o.find(".js_list"),n,e.imgArr),m.initEvent.call(e,t),m.initWater.call(e,n),
m.initPageBar.call(e,n,i.group),e.dialog.popup("resetPosition");
}
}),m.initUpload.call(e,i.group);
},
initEvent:function(){
var e=this,i=e.dialog.popup("get"),n=e.options;
i.on("click",".js_imageitem",function(){
var o,a=$(this),r=a.find("label"),s=i.find(".js_btn_p").eq(0),l=a.data("url"),d=a.data("id"),c=a.data("oristatus"),g=a.data("format");
r.hasClass("selected")?(l||e.converting--,r.removeClass("selected"),o=_.indexOf(e.imgArr,d),
o>=0&&e.imgArr.splice(o,1),i.find(".js_selected").text(e.imgArr.length)):1==n.maxSelect?(l||(e.converting=1),
r.addClass("selected"),a.siblings().find("label").removeClass("selected"),e.imgArr=[{
url:l,
file_id:d,
format:g,
copyright_status:c
}],i.find(".js_selected").text(e.imgArr.length)):n.maxSelect>e.imgArr.length?(l||e.converting++,
r.addClass("selected"),e.imgArr.push({
url:l,
file_id:d,
format:g,
copyright_status:c
}),i.find(".js_selected").text(e.imgArr.length)):t.err("最多可选%s张".sprintf(n.maxSelect)),
e.imgArr.length>0?s.enable().addClass("btn_primary"):s.disable(),"cdn"==n.scene&&r.hasClass("selected")&&!l&&f.getCdnUrlByFileId({
file_id:d,
group_id:i.find(".js_groupitem.selected").data("groupid")
},function(i){
0==i.errcode?(e.converting--,a.data("url",i.url),o=_.indexOf(e.imgArr,d),o>=0&&(e.imgArr[o].url=i.url),
e.trigger("converted")):(t.err("转存失败"),a.click());
});
}),i.on("click",".js_groupitem",function(t,o){
var a=$(this),r=i.find(".js_list"),s=i.find(".js_loading"),l=i.find(".js_pagebar"),d=a.data("groupid");
a.hasClass("selected")||(a.addClass("selected").siblings(".selected").removeClass("selected"),
$("#js_imageupload").data("groupid",d),r.hide(),l.hide(),s.show(),f.getImagesByGroupId({
group_id:d,
count:n.perPage
},function(t){
if(e.dialog&&d==i.find(".js_groupitem.selected").data("groupid")){
t=t.page_info,t.scene=n.scene,s.hide(),l.show(),m.renderImageList(r,t,e.imgArr),
m.initPageBar.call(e,t,d),m.initUpload.call(e);
for(var a=0;o&&"upload"==o.source&&a<o.count;++a)r.children().eq(a).click();
}
}));
});
},
initPageBar:function(e,i){
var t=this,o=t.dialog.popup("get"),a=t.options;
m.pagebar&&m.pagebar.destroy();
var r=0;
return 0==i?r=e.file_cnt.img_cnt:e.file_group_list.file_group.each(function(e){
e.id==i&&(r=e.count);
}),a.perPage>=r?void o.find(".js_pagebar").empty():void(m.pagebar=new n({
container:o.find(".js_pagebar"),
perPage:a.perPage,
initShowPage:1,
totalItemsNum:r,
first:!1,
last:!1,
isSimple:!0,
callback:function(e){
var i=o.find(".js_groupitem.selected").data("groupid"),n=o.find(".js_list"),r=o.find(".js_loading"),s=o.find(".js_pagebar");
n.hide(),s.hide(),r.show(),f.getImagesByGroupId({
group_id:i,
begin:e.perPage*(e.currentPage-1),
count:a.perPage
},function(e){
e=e.page_info,e.scene=a.scene,r.hide(),s.show(),m.renderImageList(n,e,t.imgArr);
});
}
}));
},
initUpload:function(e){
var i=this,n=i.dialog.popup("get"),a=n.find(".js_imageupload"),r="js_imageupload"+Math.random().toString().substr(2),s=n.find(".js_groupitem.selected").data("groupid")||e||1,l=i.options;
a.attr("id",r).off().children().remove(),o.uploadImageLibFile({
container:"#"+r,
only_cdn:l.only_cdn,
multi:!0,
type:2,
scene:l.uploadScene,
doublewrite:!0,
groupid:s,
onComplete:function(e,n,o,a){
0==a.base_resp.ret&&t.suc("上传成功"),i.fromUpload.push(a.content);
},
onAllComplete:function(e,i){
var t,o=n.find(".js_groupitem.selected");
i.filesUploaded>0&&(t=+o.find("span").text(),!l.doselected||l.doselected&&i.filesUploaded<=1*l.completeUploadMinSelectNum?o.removeClass("selected").trigger("click",{
source:"upload",
count:i.filesUploaded
}):o.removeClass("selected").trigger("click",{
source:"upload",
count:0
}),o.find("span").text(t+i.filesUploaded));
},
showError:!0
});
},
initWater:function(e){
var i=this,t=i.options,n=i.dialog.popup("get"),o=e.watermark_status,r=template.compile(d)({
status:o,
set_water_url:wx.url("/cgi-bin/settingpage?t=setting/function&action=function&set_water=1")
});
n.find(".js_water").text((t.desc?"，":"")+(3==o?"已关闭":"已开启")+"图片水印"),new a({
container:n.find(".js_water_tips"),
content:r,
parentClass:"js_water img_water",
position:{
left:-138,
top:2
},
reposition:!0,
type:"hover"
});
},
renderImageList:function(e,i,t){
i.file_item.each(function(e){
e.img_url=e.cdn_url?e.cdn_url:wx.url("/cgi-bin/getimgdata?mode=small&source=file&fileId=%s".sprintf(e.file_id)),
-1!=_.indexOf(t,e.file_id)&&(e.selected=1);
}),e.html(g(i)).show();
var n=0,o=0,a=28308,r=28308,s=9,l=10,d=0,c=117,p=$(".js_pic"),u=p.length;
p.each(function(){
var e=$(this);
e.on("error",function(){
++n,++d,d===u&&((new Image).src="/mp/jsmonitor?idkey="+a+"_"+s+"_"+n+";"+r+"_"+l+"_"+o);
}),e.on("load",function(){
++o,++d;
var i=parseInt(e.css("width")),t=parseInt(e.css("height"));
t>i?e.css("width",c):e.css("height",c),d===u&&((new Image).src="/mp/jsmonitor?idkey="+a+"_"+s+"_"+n+";"+r+"_"+l+"_"+o);
}),e.attr("src",e.attr("data-src"));
});
}
},f={
getImagesByGroupId:function(e,t){
e=$.extend({
group_id:1,
begin:0,
count:8,
type:2
},e),i.get({
url:wx.url("/cgi-bin/filepage"),
data:e,
mask:!1
},function(e){
0!=e.base_resp.ret?i.show(e):t(e);
});
},
getCdnUrlByFileId:function(e,t){
e.group_id=e.group_id||1,i.post({
url:wx.url("/cgi-bin/uploadimg2cdn?action=duplicate"),
data:e,
mask:!1
},function(e){
t(e);
});
}
},_={
indexOf:function(e,i){
for(var t=0,n=e.length;n>t;++t)if(e[t].file_id==i)return t;
return-1;
}
},h={
on:function(e,i){
if(i){
var t=this.events;
return t[e]=t[e]||[],t[e].push(i),this;
}
},
trigger:function(e){
var i=this,t=arguments,n=i.events[e];
return n?($.each(n,function(e,n){
n.apply(i,Array.prototype.slice.call(t,1));
}),this):void 0;
},
hide:function(){
return this.dialog.popup("hide"),this;
},
show:function(){
return this.dialog.popup("show"),this;
},
destroy:function(){
this.dialog.popup("remove"),this.dialog=null;
}
};
return $.extend(u.prototype,h),p;
});define("media/media_cgi.js",["common/wx/Tips.js","common/wx/Cgi.js","resp_types/base_resp.rt.js","resp_types/file_cnt.rt.js"],function(e){
"use strict";
var r=e("common/wx/Tips.js"),s=e("common/wx/Cgi.js"),a=e("resp_types/base_resp.rt.js"),t=e("resp_types/file_cnt.rt.js"),i={
del:function(e,t){
s.post({
mask:!1,
url:wx.url("/cgi-bin/operate_appmsg?sub=del&t=ajax-response"),
data:{
AppMsgId:e
},
rtDesc:a,
error:function(){
r.err("删除失败");
}
},function(e){
"0"==e.ret?(r.suc("删除成功"),t&&t(e)):r.err("删除失败");
});
},
del_sv:function(e,t){
s.post({
mask:!1,
url:wx.url("/cgi-bin/modifyfile?oper=del&t=ajax-response"),
data:{
fileid:e
},
rtDesc:a,
error:function(){
r.err("删除失败");
}
},function(e){
e.base_resp&&0==+e.base_resp.ret?(r.suc("删除成功"),t.suc&&t.suc(e)):(r.err("删除失败"),
t.fail&&t.fail(e));
});
},
edit_sv:function(e,t){
s.post({
mask:!1,
url:wx.url("/cgi-bin/modifyfile?oper=rename&t=ajax-response"),
data:{
fileid:e.id,
filename:e.name
},
rtDesc:a,
error:function(){
r.err("编辑失败");
}
},function(e){
e.base_resp&&0==+e.base_resp.ret?(r.suc("编辑成功"),t.suc&&t.suc(e)):(r.err("编辑失败"),
t.fail&&t.fail(e));
});
},
save:function(e,a,t,i,n,c){
var o=wx.url(t.AppMsgId?"/cgi-bin/operate_appmsg?t=ajax-response&sub=update&type=%s".sprintf(a):"/cgi-bin/operate_appmsg?t=ajax-response&sub=create&type=%s".sprintf(a));
t.ajax=1,s.post({
url:o,
data:t,
dataType:"json",
rtDesc:{
ret_R:"string",
appMsgId_R:"number"
},
error:function(e,s){
"timeout"!=s&&r.err("保存失败"),n&&n(!1,-1);
},
complete:c
},function(e){
if("0"==e.ret)r.suc("保存成功"),i&&i(e);else{
var s=!1;
switch(e.ret){
case"64506":
r.err("保存失败,链接不合法");
break;

case"64507":
r.err("内容不能包含链接，请调整");
break;

case"64510":
r.err("内容不能包含语音，请调整");
break;

case"64511":
r.err("内容不能包多个语音，请调整");
break;

case"64512":
r.err("文章中语音错误,请使用语音添加按钮重新添加。");
break;

case"64508":
r.err("查看原文链接可能具备安全风险，请检查");
break;

case"64550":
r.err("请勿插入不合法的已群发的图文消息链接");
break;

case"-99":
r.err("内容超出字数，请调整");
break;

case"-1":
r.err("系统错误，请注意备份内容后重试");
break;

case"-2":
case"200002":
r.err("参数错误，请注意备份内容后重试");
break;

case"64509":
r.err("正文中不能包含超过3个视频，请重新编辑正文后再保存。");
break;

case"-5":
r.err("服务错误，请注意备份内容后重试。");
break;

case"64513":
r.err("请从正文中选择封面，再尝试保存。");
break;

case"-206":
r.err("目前，服务负荷过大，请稍后重试。");
break;

case"10801":
r.err("标题不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"),s=e.msg;
break;

case"10802":
r.err("作者不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"),s=e.msg;
break;

case"10803":
r.err("敏感链接，请重新添加。"),s=e.msg;
break;

case"10804":
r.err("摘要不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"),s=e.msg;
break;

case"10806":
r.err("正文不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"),s=e.msg;
break;

case"-20000":
r.err("登录态超时，请重新登录。");
break;

case"64513":
r.err("封面必须存在正文中，请检查封面");
break;

case"64514":
r.err("你没有权限使用话题卡片功能");
break;

case"15801":
case"15802":
case"15803":
case"15804":
case"15805":
case"15806":
case"1530503":
case"1530504":
break;

default:
r.err("保存失败");
}
n&&n(s,e.ret);
}
});
},
preview:function(e,a,t,i,n){
s.post({
url:wx.url("/cgi-bin/operate_appmsg?sub=preview&t=ajax-appmsg-preview&type=%s".sprintf(a)),
data:t,
dataType:"json",
rtDesc:{
ret_R:"string"
},
error:function(){
r.err("发送失败，请稍后重试"),n&&n();
}
},function(e){
if("0"==e.ret)r.suc("发送预览成功，请留意你的手机微信"),i&&i(e);else{
switch(e.ret){
case"64501":
e.word="你输入的帐号不存在，请重新输入";
break;

case"64502":
e.word="你输入的微信号不存在，请重新输入";
break;

case"10700":
case"64503":
e.word="1.你尚未关注公众号，请先关注 2.如果已经关注公众号，请确认该微信的隐私设置，可以通过QQ号、手机号或者微信号搜索到该微信。";
break;

case"64510":
e.word="内容不能包含语音,请调整";
break;

case"64511":
e.word="内容不能包含多个语音,请调整";
break;

case"64512":
e.word="文章中语音错误,请使用语音添加按钮重新添加。";
break;

case"64550":
e.word="请勿插入不合法的已群发的图文消息链接";
break;

case"10703":
e.word="对方关闭了接收消息";
break;

case"10701":
e.word="用户已被加入黑名单，无法向其发送消息";
break;

case"10704":
case"10705":
e.word="该素材已被删除";
break;

case"64504":
e.word="保存图文消息发送错误，请稍后再试";
break;

case"64505":
e.word="发送预览失败，请稍后再试";
break;

case"64507":
e.word="内容不能包含链接，请调整";
break;

case"-99":
e.word="内容超出字数，请调整";
break;

case"62752":
e.word="可能含有具备安全风险的链接，请检查";
break;

case"10801":
e.word="标题不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。",e.antispam=!0;
break;

case"10802":
e.word="作者不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。",e.antispam=!0;
break;

case"10803":
e.word="敏感链接，请重新添加。",e.antispam=!0;
break;

case"10804":
e.word="摘要不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。",e.antispam=!0;
break;

case"10806":
e.word="正文不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。",e.antispam=!0;
break;

case"10807":
e.word="内容不能违反公众平台协议、相关法律法规和政策，请重新编辑。",e.antispam=!0;
break;

case"-8":
case"-6":
e.ret="-6",e.word="请输入验证码";
break;

case"15801":
case"15802":
case"15803":
case"15804":
case"15805":
case"15806":
break;

default:
e.word="系统繁忙，请稍后重试";
}
15==a&&r.err(e.word),n&&n(e);
}
});
},
getList:function(e,i,n,c,o,p){
var b="";
b=wx.url(o?"/cgi-bin/appmsg?type=%s&action=list&begin=%s&count=%s&query=%s&f=json".sprintf(e,i,n,o):"/cgi-bin/appmsg?type=%s&action=list&begin=%s&count=%s&f=json".sprintf(e,i,n)),
0==p?b=wx.url("/cgi-bin/appmsg?type=%s&action=list&begin=%s&count=%s&f=json".sprintf(e,i,n)):1==p&&(b=wx.url("/cgi-bin/video_mgr?type=%s&action=get_video_list&begin=%s&offset=%s&f=json".sprintf(e,i,n))),
s.get({
mask:!1,
url:b,
rtDesc:$.extend({},a,{
app_msg_info:$.extend({},t,{
item_R:[],
search_cnt:"number"
})
}),
error:function(){
r.err("获取列表失败");
}
},function(e){
e&&e.base_resp&&0==e.base_resp.ret?c&&c(e.app_msg_info):r.err("获取列表失败");
});
},
getSingleList:function(e,a,t,i){
s.get({
mask:!1,
url:wx.url("/cgi-bin/appmsg?type=%s&action=for_advert&begin=%s&count=%s&f=json".sprintf(e,a,t)),
error:function(){
r.err("获取列表失败");
}
},function(e){
e&&e.base_resp&&0==e.base_resp.ret?i&&i(e.app_msg_info):r.err("获取列表失败");
});
}
},n={
save:function(e,a,t){
var i=wx.url("/cgi-bin/operate_vote");
e.ajax=1,s.post({
url:i,
data:e,
error:function(){
r.err("保存失败"),t&&t();
}
},function(e){
e&&e.base_resp&&0==e.base_resp.ret?(r.suc("保存成功"),a&&a(e)):(r.err("保存失败"),t&&t(e));
});
}
};
return{
rename:function(e,a,t){
s.post({
mask:!1,
url:wx.url("/cgi-bin/modifyfile?oper=rename&t=ajax-response"),
data:{
fileid:e,
fileName:a
},
error:function(){
r.err("重命名失败");
}
},function(e){
if(!e||!e.base_resp)return void r.err("重命名失败");
var s=e.base_resp.ret;
if("0"==s)r.suc("重命名成功"),t&&t(e);else switch(s){
case"200002":
r.err("素材名不能包含空格");
break;

default:
r.err("重命名失败");
}
});
},
del:function(e,a){
s.post({
mask:!1,
url:wx.url("/cgi-bin/modifyfile?oper=del&t=ajax-response"),
data:{
fileid:e
},
error:function(){
r.err("删除失败");
}
},function(e){
return e&&e.base_resp?void("0"==e.base_resp.ret?(r.suc("删除成功"),a&&a(e)):r.err("删除失败")):void r.err("删除失败");
});
},
getList:function(e,i,n,c){
s.get({
mask:!1,
url:wx.url("/cgi-bin/filepage?type=%s&begin=%s&count=%s&f=json".sprintf(e,i,n)),
rtDesc:$.extend({},a,{
page_info_R:$.extend({},t,{
file_item_R:[{
file_id_R:"number",
name_R:"string",
size_R:"string",
update_time_R:"number",
type_R:"number"
}]
})
}),
error:function(){
r.err("获取列表失败");
}
},function(e){
e&&e.base_resp&&0==e.base_resp.ret?c&&c(e.page_info):r.err("获取列表失败");
});
},
appmsg:i,
vote:n
};
});define("common/wx/richEditor/emotion.js", [ "tpl/richEditor/emotion.html.js", "common/qq/Class.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = wx.T, s = {
url: wx.resPath + "/mpres/htmledition/images/icon/emotion/",
data: {
"0": "微笑",
"1": "撇嘴",
"2": "色",
"3": "发呆",
"4": "得意",
"5": "流泪",
"6": "害羞",
"7": "闭嘴",
"8": "睡",
"9": "大哭",
"10": "尴尬",
"11": "发怒",
"12": "调皮",
"13": "呲牙",
"14": "惊讶",
"15": "难过",
"16": "酷",
"17": "冷汗",
"18": "抓狂",
"19": "吐",
"20": "偷笑",
"21": "可爱",
"22": "白眼",
"23": "傲慢",
"24": "饥饿",
"25": "困",
"26": "惊恐",
"27": "流汗",
"28": "憨笑",
"29": "大兵",
"30": "奋斗",
"31": "咒骂",
"32": "疑问",
"33": "嘘",
"34": "晕",
"35": "折磨",
"36": "衰",
"37": "骷髅",
"38": "敲打",
"39": "再见",
"40": "擦汗",
"41": "抠鼻",
"42": "鼓掌",
"43": "糗大了",
"44": "坏笑",
"45": "左哼哼",
"46": "右哼哼",
"47": "哈欠",
"48": "鄙视",
"49": "委屈",
"50": "快哭了",
"51": "阴险",
"52": "亲亲",
"53": "吓",
"54": "可怜",
"55": "菜刀",
"56": "西瓜",
"57": "啤酒",
"58": "篮球",
"59": "乒乓",
"60": "咖啡",
"61": "饭",
"62": "猪头",
"63": "玫瑰",
"64": "凋谢",
"65": "示爱",
"66": "爱心",
"67": "心碎",
"68": "蛋糕",
"69": "闪电",
"70": "炸弹",
"71": "刀",
"72": "足球",
"73": "瓢虫",
"74": "便便",
"75": "月亮",
"76": "太阳",
"77": "礼物",
"78": "拥抱",
"79": "强",
"80": "弱",
"81": "握手",
"82": "胜利",
"83": "抱拳",
"84": "勾引",
"85": "拳头",
"86": "差劲",
"87": "爱你",
"88": "NO",
"89": "OK",
"90": "爱情",
"91": "飞吻",
"92": "跳跳",
"93": "发抖",
"94": "怄火",
"95": "转圈",
"96": "磕头",
"97": "回头",
"98": "跳绳",
"99": "挥手",
"100": "激动",
"101": "街舞",
"102": "献吻",
"103": "左太极",
"104": "右太极"
},
ext: ".gif",
replaceEmoji: function(e) {
var t, n, r = s.url, i = s.ext, o = s.data;
for (t in o) n = new RegExp("/" + o[t], "g"), e = e.replace(n, '<img src="{src}" alt="mo-{alt}"/>'.format({
src: r + t + i,
alt: o[t]
}));
return e;
}
}, o = e("tpl/richEditor/emotion.html.js"), u = e("common/qq/Class.js"), a = 24, f = 24, l = 15, c = 7, h = u.declare({
init: function(e) {
this.selector$ = e;
var t = [], n = s.url + "{k}" + s.ext, r = a, u = f, h = l, p = c;
for (var d = 0; d < p; ++d) for (var v = 0; v < h; ++v) {
var m = d * h + v;
t.push({
gifurl: n.format({
k: m
}),
title: s.data[m + ""],
bp: "background-position:" + -m * r + "px 0;"
});
}
this.selector$.html(i(o, {
edata: t
})), this._previewArea$ = this.selector$.find(".js_emotionPreviewArea"), this._initEvent();
},
getEmotionText: function(e) {
return e.replace(/<img.*?alt=["]{0,1}mo-([^"\s]*).*?>/ig, "/$1");
},
getEmotionHTML: function(e) {
var t = e.title;
return '<img src="{src}" alt="{alt}"/>'.format({
src: e.gifurl,
alt: t ? "mo-" + t : ""
});
},
_getData: function(e) {
return {
title: e.data("title"),
gifurl: e.data("gifurl")
};
},
_initEvent: function() {
var e = this;
e.selector$.click(function(t) {
var n = e._getData($(t.target));
!n.gifurl || e.clickCB && e.clickCB(n);
}).mouseover(function(t) {
var n = e._getData($(t.target));
!n.gifurl || e._previewArea$.html(e.getEmotionHTML({
title: "",
gifurl: n.gifurl
})), e.mouseoverCB && e.mouseoverCB();
}).mouseleave(function(t) {
e._previewArea$.html(""), e.mouseleaveCB && e.mouseleaveCB();
});
},
click: function(e) {
this.clickCB = e;
},
mouseleave: function(e) {
return this.mouseleaveCB = e, this;
},
mouseover: function(e) {
return this.mouseoverCB = e, this;
},
show: function() {
this.selector$.fadeIn();
},
hide: function() {
this.selector$.fadeOut();
}
});
h.emoji = s.replaceEmoji, n.exports = h;
} catch (p) {
wx.jslog({
src: "common/wx/richEditor/emotion.js"
}, p);
}
});define("common/wx/richEditor/wysiwyg.js",["common/wx/richEditor/editorRange.js","common/qq/Class.js"],function(e,t,n){
"use strict";
var i=/msie/.test(navigator.userAgent.toLowerCase()),a="Wysiwyg",o=e("common/wx/richEditor/editorRange.js"),s=e("common/qq/Class.js"),r=s.declare({
init:function(e,t){
var n=e,i=$('<div class="edit_area"></div>');
this._dom$=n,this._val="",this._lastRange=null,this._editArea$=i,$.extend(this,t),
this._initEditArea(),this._initEvent();
},
_initEvent:function(){
var e=this,t=function(){
e.__tid&&clearTimeout(e.__tid),e.__tid=setTimeout(function(){
e._filterNode();
},10);
};
e._editArea$.bind({
keydown:function(t){
e._keydown(t);
},
keyup:function(t){
e._keyup(t);
},
compositionend:function(t){
e._compositionend(t);
},
mouseup:function(t){
e._mouseup(t);
},
drop:t,
paste:t
});
},
_keydown:function(e){
var t=this,n=wx.isHotkey;
if(n(e,"backspace")){
var i=o.getSelection();
i.type&&"control"===i.type.toLowerCase()&&(e.preventDefault(),i.clear());
}
(n(e,"ctrlenter")||n(e,"altenter")||n(e,"enter"))&&(e.preventDefault(),t.insertHTML("<br/>")._saveRang()),
t.keydown&&t.keydown(e);
},
_keyup:function(e){
var t=this;
t._saveRang(),t.keyup&&t.keyup(e),t.save();
},
_compositionend:function(e){
var t=this;
t._saveRang(),t.keyup&&t.keyup(e),t.save();
},
_mouseup:function(e){
var t=this;
t._saveRang(),t.mouseup&&t.mouseup(e);
},
focus:function(){
this._editArea$.focus(),this._resotreRange();
},
_setCursorToEnd:function(e){
if(i&&e){
var t=o.getSelection();
t.extend&&(t.extend(e,e.length),t.collapseToEnd&&t.collapseToEnd());
}
},
insertHTML:function(e){
var t=this;
t.focus();
var n=o.getRange();
if(!n)return t;
if(n.createContextualFragment){
e+='<img style="width:1px;height:1px;">';
var a=n.createContextualFragment(e),s=a.lastChild;
n.deleteContents(),n.insertNode(a),n.setEndAfter(s),n.setStartAfter(s);
var r=o.getSelection();
r.removeAllRanges(),r.addRange(n),document.execCommand("Delete",!1,null);
}else i&&/>$/.test(e),!!e&&n.pasteHTML&&n.pasteHTML(e),n.collapse&&n.collapse(!1),
n.select();
return t._saveRang().save(),t;
},
save:function(e){
var t=this,e=e||this.textFilter,n=t._editArea$.html();
return t.val="function"==typeof e?e(n):n,t.change&&t.change(),t;
},
_filterNode:function(e){
for(var t,n=this,e=e||this.nodeFilter,i=n._editArea$.get(0),a=i.childNodes,o=a.length-1;o>=0;o--){
var s=a[o];
switch(s.nodeType){
case 1:
if("BR"!==s.nodeName.toUpperCase()){
var r,c=!1;
if((r=e&&e(s))||(r=s.textContent||s.innerText||"",c=!0),r){
var d=c?document.createTextNode(r):r;
!t&&(t=d),i.replaceChild(d,s);
}else i.removeChild(s);
}
break;

case 3:
break;

default:
i.removeChild(s);
}
}
return n._setCursorToEnd(t),n._saveRang().save();
},
getHTML:function(){
return this._editArea$.html();
},
getText:function(){
return this.getHTML().text();
},
getContent:function(){
return this.val;
},
setContent:function(e,t){
this.clear(),this._editArea$.html(e),this.val=t||e,this.change&&this.change();
},
clear:function(){
this.val="",this._editArea$.html("");
},
_initEditArea:function(){
var e=this;
e._editArea$.attr("class",e._dom$.attr("class")).attr("contentEditable",!0).css({
"overflow-y":"auto",
"overflow-x":"hidden"
}),e._dom$.after(e._editArea$).hide().data(a,e),e.init&&e.init();
},
_saveRang:function(){
return this._lastRange=o.getRange(),this;
},
_resotreRange:function(){
var e=this._lastRange;
if(e){
var t=o.getSelection();
if(t.addRange)t.removeAllRanges(),t.addRange(e);else{
var n=o.getRange();
n.setEndPoint&&(n.setEndPoint("EndToEnd",e),n.setEndPoint("StartToStart",e)),n.select();
}
}
return this;
}
}),c=function(e,t){
return e.data(a)||new r(e,t);
};
n.exports=c;
});define("tpl/richEditor/emotionEditor.html.js",[],function(){
return'<div class="emotion_editor">\n    <div class="edit_area js_editorArea"></div>\n    <div class="editor_toolbar">\n        {if !hideEmotion}\n        <a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch">表情</a>\n        {/if}\n        {if !hideUpload}\n        <div class="upload_box">\n            <div class="upload_area">\n                <a id="emotionEditor_{gid}_" href="javascript:void(0);" class="js_upload upload_access">\n                    <i class="icon18_common upload_gray"></i>\n                    上传图片                </a>\n            </div>\n        </div>\n        {/if}\n        <p class="editor_tip opr_tips">，按下Shift+Enter键换行</p>\n        <p class="editor_tip js_editorTip"></p>\n        <div class="emotion_wrp js_emotionArea">\n			\n		</div>\n    </div>\n</div>\n\n';
});define("tpl/tab.html.js",[],function(){
return'<div class="msg_tab">\n    <div class="tab_navs_panel">\n        <span class="tab_navs_switch_wrp switch_prev js_switch_prev">\n            <span class="tab_navs_switch"></span>\n        </span>\n        <span class="tab_navs_switch_wrp switch_next js_switch_next">\n            <span class="tab_navs_switch"></span>\n        </span>\n        <div class="tab_navs_wrp">\n            <ul class="tab_navs js_tab_navs" style="margin-left:0;">\n                {each tabs as tab}\n                <li class="tab_nav {tab.className}" data-type="{tab.type}" data-tab=".{tab.selector}" data-tooltip="{tab.text}">\n                    <a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">{tab.text}</span></a>\n                </li>\n                {/each}\n            </ul>\n        </div>\n    </div>\n	<div class="tab_panel">\n	    {each tabs as tab i}\n	    <div class="tab_content">\n	    	<div class="{tab.selector} inner {tab.innerClassName}">\n	    		<!--type 10图文 2图片  3语音 15视频 11商品消息-->\n	    		{if tab.type==10}\n			    <div class="tab_cont_cover jsMsgSendTab" data-index="{i}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt"  href="javascript:;" data-type="10" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n			        <div class="media_cover" >\n			            <span class="create_access">\n                            <a target="_blank" class="add_gray_wrp" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=1&isNew=1&lang=zh_CN&token={token}">\n			                    <i class="icon36_common add_gray"></i>\n			                    <strong>新建图文消息</strong>\n			                </a>\n                            <!--\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=0&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create"></i><strong>单图文消息</strong></a>\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=1&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create multi"></i><strong>多图文消息</strong></a>\n                            -->\n			            </span>\n			        </div>\n			    </div>	    		\n	    		{else if tab.type==2}\n                <div class="tab_cont_cover jsMsgSendTab" data-index="{i}" data-type="{tab.type}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt"   href="javascript:;" data-type="{tab.type}"   data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n                    <div class="media_cover">\n			            <span class="create_access" >\n			                <a class="add_gray_wrp" id="msgSendImgUploadBt" data-type="2" href="javascript:;">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>上传图片</strong>\n                            </a>\n			            </span>\n                    </div>\n                </div>\n	    		{else if tab.type==3}\n                <div class="tab_cont_cover jsMsgSendTab" data-index="{i}" data-type="{tab.type}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;"  data-type="{tab.type}" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n                    <div class="media_cover">\n			            <span class="create_access" >\n			                <a class="add_gray_wrp " id="msgSendAudioUploadBt" href="javascript:;">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>新建语音</strong>\n                            </a>\n			            </span>\n                    </div>\n                </div>\n	    		{else if tab.type==15}\n                <div class="tab_cont_cover jsMsgSendTab" data-index="{i}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;"  data-type="15" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a target="_blank" class="add_gray_wrp" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&type=15&lang=zh_CN&token={token}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>新建视频</strong>\n                            </a>\n			            </span>\n                    </div>\n                </div>\n	    		{else if tab.type==11}\n			    <div class="tab_cont_cover jsMsgSendTab" data-index="{i}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt"  href="javascript:;" data-type="11" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n			        <div class="appmsg_cover" >\n			            <span class="create_access">\n			                <a class="add_gray_wrp" href="javascript:;">\n			                    <i class="icon36_common add_gray"></i>\n			                    <strong>新建商品消息</strong>\n			                </a>\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&isMul=0&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create"></i><strong>单商品消息</strong></a>\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&isMul=1&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create multi"></i><strong>多商品消息</strong></a>\n			            </span>\n			        </div>\n			    </div>		    		\n	    		{/if}\n	    	</div>\n	    </div>\n	    {/each}\n	</div>\n</div>\n';
});define("tpl/popup.html.js",[],function(){
return'<div class="dialog_wrp {className}" style="{if width}width:{width}px;{/if}{if height}height:{height}px;{/if}">\n	<div class="dialog">\n		<div class="dialog_hd">\n			<h3>{title}</h3>\n			<!--#0001#-->\n			<a href="javascript:;" onclick="return false" class="icon16_opr closed pop_closed">关闭</a>\n			<!--%0001%-->\n		</div>\n		<div class="dialog_bd">{=content}</div>\n		{if buttons && buttons.length}\n		<div class="dialog_ft">\n			{each buttons as bt index}\n            <span class="btn {bt.type} btn_input js_btn_p"><button type="button" class="js_btn" data-index="{index}">{bt.text}</button></span>\n	        {/each}\n		</div>\n		{/if}\n	</div>\n</div>{if mask}<div class="mask"><iframe frameborder="0" style="filter:progid:DXImageTransform.Microsoft.Alpha(opacity:0);position:absolute;top:0px;left:0px;width:100%;height:100%;" src="about:blank"></iframe></div>{/if}\n';
});define("common/wx/widgetBridge.js", [], function(e, t, n) {
try {
var r = +(new Date);
"use strict", $.widgetBridge || ($.widgetBridge = function(e, t) {
var n = e, r = n.split("."), e = r.length > 1 ? r[1] : r[0];
$.fn[e] = function(r) {
var i = typeof r == "string", s = [].slice.call(arguments, 1), o = this;
r = r || {};
if (i) {
var u;
return r.indexOf("_") !== 0 && this.each(function() {
var t = $.data(this, n);
if (!t) return $.error("cannot call methods on " + e + " prior to initialization; " + "attempted to call method '" + r + "'");
if (r === "instance") return u = t, !1;
if (r === "option") return u = t.options, !1;
u || (u = (t[r] || jQuery.noop).apply(t, s)), r === "destroy" && (t.elements = null);
}), u;
}
var a = this;
return this.each(function() {
var e = this, i = $.data(this, n);
if (!i) {
i = $.extend(!0, {}, t), i.destroy || (i.destroy = function() {
$.removeData(e, n);
}), i.options = $.extend(!0, i.options || {}, r), i.element = $(this), i.elements = a, i._create && (o = i._create.call(i, r));
var s = o && o.length && o.get(0) || this;
$.data(s, n, i);
}
}), o;
};
});
} catch (i) {
wx.jslog({
src: "common/wx/widgetBridge.js"
}, i);
}
});define("tpl/ban/page_msg.html.js",[],function(){
return'<div class="page_msg mini ban_page_msg">\n    <div class="inner group">\n        <span class="msg_icon_wrp"><i class="icon_msg_mini warn"></i></span>\n        <div class="msg_content">{=content}</div>\n    </div>\n</div>';
});define("tpl/ban/highlight_box.html.js",[],function(){
return'<div class="highlight_box icon_wrap icon_small border ban_highlight_box" id="div_stop">\n    <span class="icon lock"></span>\n    <h4 class="title">{title}</h4>\n    <p class="desc">{=desc}</p>\n</div>';
});