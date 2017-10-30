define("tpl/media/videocard.html.js",[],function(){
return'<div id="wxVideoBox{id}" class="richvideo Js_videomsg" {if video_ori_status == 1 && is_new_video && status == 3}data-original="1"{else}data-original="0"{/if}>\n    <div class="richvideo_content" style="z-index: 0">\n        <h4 class="title">\n            {if video_ori_status == 1 && is_new_video && (status == 3 || for_selection)}\n            <i class="icon_tag_default original"></i>\n            {else if video_ori_status == 2 && is_new_video && (status == 3 || for_selection)}\n            <i class="icon_tag_default republish"></i>\n            {/if}\n            {title}\n        </h4>\n        <div class="video_info">\n            <em class="time">{time}</em>\n            <em class="res">{from}</em>\n        </div>\n        <div class="video_extra_info" data-seq="{seq}">\n            <img class="video_thumb" src="{if !cover}{if !!multi_item}{each multi_item as value}{value.cover}{/each}{/if}{else}{cover}{/if}" alt="">\n            {if is_new_video && status != 4}\n            <span class="video_length">{duration}</span>\n            {/if}\n            {if status == 0 || (status == 3 && video_ori_status == 0 && !before_original_video)}\n            <div class="status_mask">\n            <span class="status_msg">\n                审核中            </span>\n            <span class="vm_box"></span>\n            </div>\n            {else if status == 1}\n            <div class="status_mask">\n            <span class="status_msg">\n                资料不完整            </span>\n            <span class="vm_box"></span>\n            </div>\n            {else if status == 2}\n            <div class="status_mask">\n            <span class="status_msg mini_tips icon_after">\n                审核不通过                <i class="icon_mini_tips ask_white js_fail_reason" data-seq="{seq}"></i>\n            </span>\n            <span class="vm_box"></span>\n            </div>\n            {else if status == 3 && applyori == 1 && ((video_ori_status == 3 && (ori_fail_reason == 1 || ori_fail_reason == 3 || ori_fail_reason == 5) && is_new_video) || video_ori_status == 2) }\n            <div class="status_mask">\n            <span class="status_msg">\n                原创声明失败<i class="icon_mini_tips ask_white js_declare_fail" data-seq="{seq}" data-url="{url}" data-ori="{video_ori_status}" data-reason="{ori_fail_reason}" data-vid="{content}" data-name="{hit_nickname}"></i>\n            </span>\n            <span class="vm_box"></span>\n            </div>\n            {else if status == 3 && is_new_video} \n            <div class="play_mask">\n                <i class="icon_video_play"> </i>\n                <span class="vm_box"></span>\n            </div>\n\n            {else if status == 4}\n            <div class="status_mask">\n            <span class="status_msg">\n                转码中            </span>\n            <span class="vm_box"></span>\n            </div>\n            {else if status == 5}\n            <div class="status_mask">\n            <span class="status_msg">\n                转码失败<i class="icon_mini_tips ask_white js_fail_code" data-seq="{seq}"></i>\n            </span>\n            <span class="vm_box"></span>\n            </div>\n            {/if}\n        </div>\n        <div class="video_desc" data-digest="{digest}">{digest}</div>\n    </div>\n\n    {if for_operation}\n    <div class="richvideo_opr">\n        <ul class="grid_line" >\n            {if is_new_video}\n            <li class="richvideo_opr_item grid_item size1of2">\n            <a class="js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-seq="{seq}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of2 no_extra">\n            <a class="js_del js_tooltip" href="javascript:void(0);" data-id="{app_id}" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {else if is_new_video==0 && video_url!=""} <!-- 微信视频 -->\n            <li class="richvideo_opr_item grid_item size1of3">\n            <a class="js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-seq="{seq}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of3">\n                <a {if for_transfer}href="javascript:;" class="js_tooltip js_download"{else}href="{video_download_url}" class="js_tooltip"{/if} data-tooltip="下载">\n                    <i class="icon18_common download_gray">下载</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of3 no_extra">\n            <a class="js_del js_tooltip" href="javascript:void(0);" data-id="{app_id}" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {else } <!-- 微视视频 -->\n            <li class="richvideo_opr_item grid_item size1of2">\n            <a class="js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-seq="{seq}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of2 no_extra">\n            <a class="js_del js_tooltip" href="javascript:void(0);" data-id="{app_id}" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {/if}\n        </ul>\n    </div>\n    {/if}\n    {if for_selection && !(status == 3 && video_ori_status == 0 && !before_original_video)}\n    <div class="richvideo_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n    {/if}\n</div>\n';
});define("biz_web/utils/upload.js",["widget/upload.css","biz_web/lib/webuploader.js","common/wx/dialog.js","common/wx/Tips.js","tpl/uploader.html.js"],function(e){
"use strict";
function i(e){
f.src="http://isdspeed.qq.com/cgi-bin/r.cgi?flag1=7839&flag2=4&flag3=5&1="+e;
}
e("widget/upload.css");
var n=e("biz_web/lib/webuploader.js"),t=e("common/wx/dialog.js"),a=e("common/wx/Tips.js"),o=e("tpl/uploader.html.js"),r=wx.T,s=wx.path.webuploader,l=~location.hostname.search(/^mp/)?"https://mp.weixin.qq.com":"",c={
2:{
accept:{
extensions:"bmp,png,jpeg,jpg,gif",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
3:{
accept:{
extensions:"mp3,wma,wav,amr",
mimeTypes:"audio/*"
},
fileSingleSizeLimit:5242880
},
4:{
accept:{
extensions:"rm,rmvb,wmv,avi,mpg,mpeg,mp4",
mimeTypes:"video/*"
},
fileSingleSizeLimit:20971520
},
5:{
accept:{
extensions:"pdf",
mimeTypes:"application/pdf"
},
fileSingleSizeLimit:10485760
},
6:{
accept:{
extensions:"bmp,png,jpeg,jpg,gif,pdf",
mimeTypes:"image/*,application/pdf"
},
fileSingleSizeLimit:5242880
},
7:{
accept:{
extensions:"bmp,jpeg,jpg,gif",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
8:{
accept:{
extensions:"bmp,png,jpeg,jpg",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
9:{
accept:{
extensions:"xls",
mimeTypes:"application/vnd.ms-excel"
},
fileSingleSizeLimit:204800
},
10:{
accept:{
extensions:"xls",
mimeTypes:"application/vnd.ms-excel"
},
fileSingleSizeLimit:5242880
},
11:{
accept:{
extensions:"bmp,png,jpeg,jpg",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
12:{
accept:{
extensions:"mp3,wma,wav,amr",
mimeTypes:"audio/*"
},
fileSingleSizeLimit:31457280
}
};
c[15]=c[4];
var p=function(e){
t.show({
type:"warn",
msg:"警告|"+e,
mask:!0,
buttons:[{
text:"确定",
click:function(){
this.remove();
}
}]
});
},m=function(e){
var i=n.Uploader;
0==i.support("flash")?p("<p>未安装或未正确配置flash插件，请检查后重试。<br><a href='http://get.adobe.com/cn/flashplayer/' target='_blank'>到adobe去下载flash插件</a></p>"):0==i.support()?p("<p>您的浏览器不支持上传</p>"):e.refresh();
},u=function(e){
e&&wx.jslog({
src:"common/wx/upload.js"
},null,e);
},d={
swf:s,
auto:!0,
pick:{
multiple:!0
},
fileNumLimit:20,
threads:3,
sendAsBinary:!1,
runtimeOrder:"html5,flash",
compress:{
width:1280,
height:1e8,
quality:90,
afterCompressSizeLimit:2097152,
compressSize:0,
resizeSize:2097152,
maxResolution:6e6,
noCompressIfLarger:!0
},
imageSize:!0,
chunked:!1,
duplicate:!0
},f=new Image,g={},h=function(e){
if(!e.url)throw"missing url";
var t,s,l,p=$('<ul class="upload_file_box" style="display:none"></ul>'),f=$(e.container);
f.on("click",function(){
Math.random()<.1&&u(12),m(t);
}).parent().append(p),function(){
0==n.Uploader.support("html5")&&0==n.Uploader.support("flash")&&((new Image).src="/misc/jslog?level=error&id=36&content=[pageurl:"+encodeURIComponent(location.href)+",ua:"+encodeURIComponent(window.navigator.userAgent)+"]");
}(),e.only_cdn&&(e.url+="&only_cdn=1"),s={
server:wx.url(e.url+"&ticket_id="+wx.data.user_name+"&ticket="+wx.data.ticket+"&svr_time="+wx.data.time),
pick:{
id:f,
multiple:e.multi
},
fileNumLimit:e.queueSizeLimit
},l=c[e.type]||c[2],e=$.extend(!0,{},d,s,l,e);
e.server;
0==n.Uploader.support("html5")&&e.compress&&(e.compress.quality=70);
try{
t=n.create(e);
}catch(h){
if(!t)return;
}
return p.on("click",".js_cancel",function(){
var e=$(this).data("id");
t.cancelFile(e),$(this).hide();
}),t.on("beforeFileQueued",function(i){
return Math.random()<.1&&u(13),e.canContinueUpload&&!e.canContinueUpload()?!1:!(e.onSelect&&e.onSelect(null,i.id,i)===!1);
}),t.on("fileQueued",function(e){
var i={
id:e.id,
fileName:e.name,
size:n.formatSize(e.size)
};
p.append(r(o,i)).show();
}),t.on("fileDequeued",function(){
Math.random()<.1&&u(14),e.onCancel&&e.onCancel();
}),t.on("uploadProgress",function(i,n){
var t="#uploadItem%s".sprintf(i.id),a=p.find(t).find(".progress_bar_thumb");
a.width("%s%".sprintf(100*n)),1==n&&p.find(t).find(".js_cancel").remove(),e.onProgress&&e.onProgress(null,i.id,i,{
percentage:n
});
}),t.on("uploadStart",function(e){
g[e.id]=+new Date;
}),t.on("uploadSuccess",function(n,t,o){
if(Math.random()<.1&&u(16),t&&t.base_resp){
var r=+t.base_resp.ret;
if(0==r)Math.random()<.1&&(u(17),g[n.id]&&i(+new Date-g[n.id]));else switch(n.setStatus("invalid"),
r){
case-18:
case 200018:
a.err("页面停留时间过久，请刷新页面后重试！");
break;

case-20:
case 200020:
a.err("无法解释该图片，请使用另一图片或截图另存");
break;

case-13:
case 200013:
a.err("上传文件过于频繁，请稍后再试");
break;

case-10:
case 200010:
a.err("上传文件过大");
break;

case-22:
case 200022:
a.err("上传音频文件不能超过60秒");
break;

case-39:
case 200039:
a.err("上传图片高度（像素）与宽度（像素）的乘积不能超过600万");
break;

default:
a.err("上传文件发送出错，请刷新页面后重试！");
}
}
e.onComplete&&e.onComplete(null,n.id,n,t,{
fileCount:o.numOfProgress+o.numOfQueue
});
}),t.on("uploadFinished",function(i){
this.reset(),p.fadeOut().html(""),g={},0==i.numOfInvalid&&i.numOfSuccess>0&&e.onAllComplete&&e.onAllComplete(null,{
errors:i.numOfCancel+i.numOfInvalid+i.numOfUploadFailed+i.numofDeleted+i.numofInterrupt,
filesUploaded:i.numOfSuccess
});
}),t.on("uploadError",function(){
Math.random()<.1&&u(15),e.onError&&e.onError();
}),t.on("error",function(i,t,o){
switch("object"==typeof t&&(o=t),i){
case"Q_EXCEED_NUM_LIMIT":
a.err("一次上传最多只能上传%s个文件".sprintf(t));
break;

case"F_EXCEED_SIZE":
a.err("文件大小不能超过%s".sprintf(n.formatSize(t,"0")));
break;

case"F_EXCEED_COMPRESS_SIZE":
a.err("图片尺寸太大，压缩后不能超过%s，请缩小图片尺寸再试".sprintf(e.compress.afterCompressSizeLimit?e.compress.afterCompressSizeLimit/1048576+"M":"2M")),
u(42);
break;

case"Q_TYPE_DENIED":
a.err(e.errTypeMsg||"文件必须为以下格式：%s".sprintf(e.accept.extensions).replace(/,/g,", "));
}
}),t;
},b=function(e){
return function(i){
return i.url=e,h(i);
};
},w=function(e){
return function(i){
return wx.url(e+"&ticket_id="+wx.data.user_name+"&ticket="+wx.data.ticket+"&id="+i);
};
};
return{
uploadFile:h,
uploadBizFile:b(l+"/cgi-bin/filetransfer?action=upload_material&f=json"),
uploadTmpFile:b(l+"/cgi-bin/filetransfer?action=preview&f=json"),
uploadCdnFile:b(l+"/cgi-bin/filetransfer?action=upload_cdn&f=json"),
uploadShopFile:b(l+"/merchant/goodsimage?action=uploadimage"),
uploadShopUnsaveFile:b(l+"/merchant/goodsimage?action=uploadimage&save=0"),
uploadVideoCdnFile:b(l+"/cgi-bin/filetransfer?action=upload_video_cdn&f=json"),
uploadRegisterFile:b(l+"/acct/realnamesubmit?type=2&action=file_set"),
uploadUpgradeFile:b(l+"/acct/servicetypeupgrade?type=2&action=file_set"),
uploadPoiFile:b(l+"/misc/setlocation?action=upload"),
mediaFile:b(l+"/cgi-bin/filetransfer?action=bizmedia"),
uploadCdnFileFromAd:function(e){
return b(l+"/cgi-bin/filetransfer?action=upload_cdn_check_size&f=json&width="+e.w+"&height="+e.h+"&limit_size="+e.size);
},
uploadImageLibFile:function(e){
return e.url=l+"/cgi-bin/filetransfer?action=upload_material&f=json","undefined"!=typeof e.scene&&(e.url+="&scene="+e.scene),
1==e.doublewrite&&(e.url+="&writetype=doublewrite&groupid="+(e.groupid||1)),h(e);
},
uploadCdnFileWithCheck:function(e){
var i={
height:0,
width:0,
size:0,
min_height:0,
min_width:0,
min_size:0
};
e=$.extend(!0,i,e);
var n=[];
for(var t in e)n.push(encodeURIComponent(t)+"="+encodeURIComponent(e[t]));
return b(l+"/cgi-bin/filetransfer?action=upload_cdn_check_range&f=json&"+n.join("&"),"tmpfile");
},
uploadTmpFileWithCheck:function(e){
var i={
height:0,
width:0,
size:0,
min_height:0,
min_width:0,
min_size:0
};
e=$.extend(!0,i,e);
var n=[];
for(var t in e)n.push(encodeURIComponent(t)+"="+encodeURIComponent(e[t]));
return b(l+"/cgi-bin/filetransfer?action=preview_check_range&f=json&"+n.join("&"));
},
tmpFileUrl:w(l+"/cgi-bin/filetransfer?action=preview"),
mediaFileUrl:w(l+"/cgi-bin/filetransfer?action=bizmedia"),
multimediaFileUrl:w(l+"/cgi-bin/filetransfer?action=multimedia")
};
});define("common/wx/media/appmsg.js",["widget/media.css","common/wx/time.js","media/appmsg_temp_url.js","tpl/media/appmsg.html.js","common/qq/Class.js"],function(t){
"use strict";
t("widget/media.css");
var e=(wx.T,t("common/wx/time.js")),a=t("media/appmsg_temp_url.js"),i=t("tpl/media/appmsg.html.js"),m=t("common/qq/Class.js"),s=m.declare({
init:function(t){
if(t&&t.container){
t.data=t.data||$.extend({},t);
var m=t.data,s=m.multi_item||[],o=s.length,d=null,r=!0,n=[];
if(!(0>=o)){
d=s[0],d.completed=!0,d.title&&d.cover||(r=!1,d.completed=!1);
for(var l=1;o>l;++l){
var p=s[l];
p.completed=!0,n.push(p),p.title&&p.cover||(r=!1,p.completed=!1);
}
var c={
id:m.app_id,
type:t.type,
file_id:m.file_id,
time:m.create_time?e.timeFormat(m.create_time):"",
isMul:o>1,
first:d,
list:n,
completed:r,
token:wx.data.t,
showEdit:t.showEdit||!1,
showMask:t.showMask||!1
};
$(t.container).html(wx.T(i,c)).data("opt",t),this.renderData=c,a(t.container,".js_title a,.js_preview");
}
}
},
getData:function(){
return this.renderData;
}
});
return s;
});define("common/wx/media/cardmsg.js",["widget/media.css","common/wx/time.js","tpl/media/cardmsg.html.js","biz_common/utils/norefererimg.js","common/qq/Class.js","cardticket/send_card.js","common/wx/Tips.js"],function(t){
"use strict";
t("widget/media.css");
var e=wx.T,i=(t("common/wx/time.js"),t("tpl/media/cardmsg.html.js")),s=t("biz_common/utils/norefererimg.js"),n=t("common/qq/Class.js"),a=t("cardticket/send_card.js"),r=t("common/wx/Tips.js"),d=n.declare({
type:16,
init:function(t){
this.opt=t.opt,this.info=t.infos[this.type],this.index=this.info&&this.info.index,
this.data=this.opt.data,this.msgSender=t;
},
getData:function(){
var t=$.extend(!0,{
cardid:this.data.id,
cardnum:this.data.cardnum
},this.data);
return t.cardtype=t.type,t.type=this.type,t;
},
click:function(){
var t=this;
return this.send_card=new a({
multi:!1,
param:{
need_member_card:1
},
selectComplete:function(e,i){
return e?(e.cardnum=i,t.fillData(e),void t.msgSender.select(t.index)):void r.err("请选择卡券");
},
source:"直接群发卡券"
}),this.send_card.show(),!1;
},
fillData:function(t){
this.data=t,this.msgSender.type=this.type;
var n=e(i,t),a=$("."+this.info.selector).html(n),r=a.find(".js_logourl");
r.length&&s({
img:r[0]
}),this.msgSender.select(this.index),a.wrapInner("<div/>").children().append('<a href="javascript:;" class="link_dele" onclick="this.parentNode.parentNode.removeChild(this.parentNode);">删除</a>');
},
isValidate:function(){
return this.data.id?!0:(r.err("请选择卡券"),!1);
},
clear:function(){
16==this.type&&($(".msg_card").parent("div").remove(),$(".tab_text").children("a").trigger("click"));
}
});
return d;
});define("common/wx/media/video.js",["widget/media/richvideo.css","widget/media.css","biz_web/lib/video.js","common/wx/Cgi.js","common/wx/time.js","common/qq/Class.js","biz_web/lib/swfobject.js","tpl/media/video.html.js","tpl/media/simple_videomsg.html.js","tpl/media/wxvideo.html.js","tpl/media/videomsg.html.js"],function(e){
"use strict";
e("widget/media/richvideo.css"),e("widget/media.css");
var i,t=e("biz_web/lib/video.js"),o=e("common/wx/Cgi.js"),d=e("common/wx/time.js"),s=e("common/qq/Class.js"),n=e("biz_web/lib/swfobject.js"),a=e("tpl/media/video.html.js"),r=wx.T,l=wx.data.t,m=document,c=!!n.ua.pv[0],f=m.createElement("video"),u=navigator.userAgent.toLowerCase(),v=/msie/.test(u),p=/firefox/.test(u);
t.options.flash.swf=wx.path.video;
var h={
id:"",
source:"",
type:"",
file_id:""
},w=5e3,g=function(e){
if(e.video_url){
{
var i="tmp"+(1e5*Math.random()|0);
$('<video id="%s"></video>'.sprintf(i)).appendTo("body");
}
t("#"+i).ready(function(){
$("#"+i).hide();
var t=this;
this.on("error",function(){
t.dispose(),e.dom.find(".loading_tips").show(),e.video_url="",setTimeout(function(){
g(e);
},w);
}),this.on("loadedmetadata",function(){
t.dispose(),$(e.selector).children().remove(),e.for_transfer=!1,e.digest=e.digest?e.digest.html(!1):"",
new _(e);
});
var o=e.video_url;
t.src(f.canPlayType?o:[{
type:"video/x-flv",
src:o+"&trans=1"
}]),t.play();
});
}else o.get({
url:wx.url("/cgi-bin/appmsg?action=get_video_url&videoid=%s".sprintf(e.video_id)),
error:function(){
setTimeout(function(){
g(e);
},w);
}
},function(i){
e.video_url=i.video_url||"",e.video_download_url=i.video_download_url||"",setTimeout(function(){
g(e);
},w);
});
},_=s.declare({
init:function(t){
var o=this;
if($(t.selector).data("opt",t),t=$.extend(!0,{},h,t),o.id=t.id,o.source=t.source,
o.file_id=t.file_id,o.type=t.type,o.video_url=t.video_url,o.tpl=t.tpl,o.ff_must_flash=t.ff_must_flash,
t.src=o.getVideoURL(),t.token=l||wx.data.t,t.time=t.create_time?d.timeFormat(t.create_time):"",
t.digest=t.digest?t.digest.replace(/<br.*>/g,"\n").html():"",t.for_network="string"==typeof t.video_url?!t.video_url:!t.content,
!t.file_id&&t.multi_item&&t.multi_item.length>0){
var s=t.multi_item[0];
s&&s.cover&&(t.img_url=s.cover);
}
i=e(t.sent?"tpl/media/simple_videomsg.html.js":21==+t.type||9==+t.type||11==+t.type?"tpl/media/wxvideo.html.js":"tpl/media/videomsg.html.js");
var n=$("videomsg"==t.tpl?r(i,t):r(a,t));
o.dom=t.dom=$(t.selector).append(n),"videomsg"==t.tpl&&t.for_transfer&&g(t,o.dom),
o.dom.find(".video_desc").length&&o.dom.find(".video_desc").html(o.dom.find(".video_desc").attr("data-digest").replace(/\n/g,"<br>")),
o.dom.find(".wxVideoScreenshot").on("click",function(){
o.dom.find(".mediaContent").css({
height:"auto"
}),o.play(t.play);
}),o.dom.find(".wxNetworkVideo").on("click",function(){
window.open($(this).attr("data-contenturl"));
}),o.dom.find(".video_switch").click(function(){
o.dom.find(".mediaContent").css({
height:"104px"
}),o.pause(t.pause);
});
},
getVideoURL:function(){
var e=this.source,i=this.id,t=(this.msg_id||"",this.file_id);
return e&&(e="&source="+e),this.video_url||"/cgi-bin/getvideodata?msgid={msgid}&fileid={fileid}&token={token}{source}".format({
msgid:i,
fileid:t,
source:e,
token:wx.data.t
});
},
canPlayType:function(){
this.type;
return!f.canPlayType&&!c;
},
play:function(e){
var i=this;
if(i.canPlayType())return void alert("您当前浏览器无法播放视频，请安装Flash插件/更换Chrome浏览器");
var o=this.id,d=this.player;
if(d)return $("#wxVideoBox"+o).addClass("wxVideoPlaying").find(".wxVideoPlayContent").show(),
d.play(),e&&e(this);
var s=i.getVideoURL();
$("#wxVideoBox"+o).addClass("wxVideoPlaying").find(".wxVideoPlayContent").show();
var n="videomsg"==i.tpl?{
width:"100%",
height:"100%"
}:{};
t("#wxVideo"+o,n).ready(function(){
d=this;
var t=0;
return d.on("fullscreenchange",function(){
t?($("#wxVideoPlayer"+o).css({
overflow:"hidden",
zoom:"1"
}),$("#wxVideoBox"+o).css({
"z-index":"0"
})):($("#wxVideoPlayer"+o).css({
overflow:"visible",
zoom:"normal"
}),$("#wxVideoBox"+o).css({
"z-index":"1"
})),t=~t;
}),d.on("ended",function(){
this.currentTime(0);
}),d.src(v||!f.canPlayType||i.ff_must_flash&&p?[{
type:"video/x-flv",
src:s+"&trans=1"
}]:s),d.play(),i.player=d,e&&e(this);
});
},
pause:function(e){
var i=this.player;
i&&i.pause(),$("#wxVideoBox"+this.id).removeClass("wxVideoPlaying").find(".wxVideoPlayContent").hide(),
e&&e(this);
}
});
return _;
});define("common/wx/media/img.js",["widget/media.css","tpl/media/img.html.js","common/qq/Class.js"],function(t){
"use strict";
var i=(wx.T,t("widget/media.css"),t("tpl/media/img.html.js")),a=t("common/qq/Class.js"),e=a.declare({
init:function(t){
if(t&&t.container){
var a=t;
$(t.container).html(i.format({
token:wx.data.t,
id:t.file_id,
msgid:t.msgid||"",
source:t.source||"",
ow:~t.fakeid
})).data("opt",t),this.data=a;
}
},
getData:function(){
return this.data;
}
});
return e;
});define("common/wx/media/audio.js",["biz_web/lib/soundmanager2.js","tpl/media/audio.html.js","tpl/media/qqmusicaudio.html.js","widget/media.css","common/qq/Class.js","biz_common/moment.js"],function(i,s,t){
"use strict";
var e=wx.T,o=i("biz_web/lib/soundmanager2.js"),n=i("tpl/media/audio.html.js"),d=i("tpl/media/qqmusicaudio.html.js"),l=(i("widget/media.css"),
i("common/qq/Class.js")),u=i("biz_common/moment.js"),a=null,m=null,h="wxAudioPlaying",c=function(){
m=o,m.setup({
url:"/mpres/zh_CN/htmledition/plprecorder/biz_web/",
preferFlash:!1,
debugMode:!1
});
};
$(window).load(function(){
c();
});
var r={
id:"",
source:"",
file_id:""
},f=l.declare({
init:function(i){
var s=this;
$.extend(!0,s,r,i),this.soundId=this.id||this.file_id,this.title=this.title||this.name,
this.play_length="undefined"==typeof this.play_length||0==this.play_length?"未知时长":u.unix(this.play_length/1e3).format("mm:ss");
var t;
t=$(this.qqmusictpl?e(d,s):e(n,s)),s.dom=$(i.selector).append(t).data("opt",i),t.click(function(){
s.toggle();
});
},
getAudioURL:function(){
if(this.qqmusicurl)return this.qqmusicurl;
var i=this.source,s=this.id,t=this.file_id;
return i&&(i="&source="+i),wx.url(this.voice_encode_fileid?"https://res.wx.qq.com/voice/getvoice?mediaid="+this.voice_encode_fileid:"/cgi-bin/getvoicedata?msgid={id}&fileid={fileid}{source}".format({
id:s,
fileid:t,
source:i
}));
},
isPlaying:function(){
return null!=a&&this==a;
},
toggle:function(){
this.isPlaying()?this.stop():(a&&a.stop(),this.play());
},
play:function(i){
var s=this;
this.isPlaying()||(this.dom.addClass(h),!!a&&a.dom.removeClass(h),a=this,this.sound||(!m&&c(),
this.sound=m.createSound({
id:s.soundId,
url:s.getAudioURL(),
onfinish:function(){
a&&(a.dom.removeClass(h),a=null);
},
onload:function(i){
i||m.unload(s.soundId),!i&&a&&(a.dom.removeClass(h),a.sound=null,a=null);
}
})),m.play(this.soundId),i&&i(this));
},
stop:function(i){
this.isPlaying()&&(a=null,this.dom.removeClass(h),m.stop(this.soundId),i&&i(this));
}
});
t.exports=f;
});define("media/media_dialog.js",["widget/media/media_dialog.css","widget/media/richvideo.css","common/wx/popup.js","common/wx/Tips.js","media/media_cgi.js","biz_web/utils/upload.js","biz_web/ui/checkbox.js","common/wx/pagebar.js","common/wx/media/imageDialog.js","common/wx/media/videoDialog.js","common/wx/media/audio.js","common/wx/media/img.js","common/wx/media/video.js","common/wx/media/appmsg.js","cardticket/send_card.js","common/wx/time.js","tpl/media/dialog/file_layout.html.js","tpl/media/dialog/appmsg_layout.html.js","tpl/media/dialog/videomsg_layout.html.js","tpl/media/dialog/file.html.js","media/plugin/audio.js"],function(e){
"use strict";
function i(e,i,t,o,n){
{
var a=e/i+1;
new _({
container:$(".pageNavigator",p.popup("get")),
perPage:i,
first:!1,
last:!1,
isSimple:!0,
initShowPage:a,
totalItemsNum:t,
callback:function(e){
var t=e.currentPage;
t!=a&&(t--,n.begin=i*t,K.key&&(n.key=K.key),o(n));
}
});
}
}
function t(e,i,t,n,a,d){
p&&p.popup("remove"),15==t&&(e="videomsg");
var l=T++;
if(p=$(r(A[e],{
tpl:i,
upload_id:l,
type:t,
token:wx.data.t
}).trim()).popup({
title:"选择素材",
className:g,
width:960,
onOK:function(){
return a&&!a()?(f.err("请选择素材"),!0):(this.remove(),p=null,void(document.body.style.overflow=document.documentElement.style.overflow="auto"));
},
onCancel:function(){
this.remove(),p=null,document.body.style.overflow=document.documentElement.style.overflow="auto";
}
}),u=p.popup("get"),y({
container:"#js_media_dialog_upload"+l,
type:t,
onAllComplete:function(){
f.suc("上传成功"),d.begin=0,s(d);
}
}),n){
if("file"==e&&($(".js_media_list",u).html(r(P,{
list:n
})),$(".frm_radio[type=radio]",u).checkbox(!1),$(".media_content",u).each(function(){
var e=$(this),i=e.data("id"),t=e.data("type"),o=F[i];
o&&t&&E[t]&&E[t](e,o);
})),"appmsg"==e){
for(var m=n.length,c=$(".js_appmsg_list .inner",u),v=0;m>v;++v){
var h=n[v],_=c.eq(v%2),w=h.app_id,j=$('<div id="appmsg%s"></div>'.sprintf(w),_).appendTo(_);
new S({
container:j,
data:h,
showMask:!0,
type:t
});
}
K.key&&($("#msgSearchInput").val(K.key),$("#searchCloseBt").show(),$(".appmsg_title>a").each(function(e,i){
$(i).html($(i).text().replace(new RegExp(K.key,"g"),'<span class="highlight">'+K.key+"</span>"));
}));
}
if("videomsg"==e){
var x=u.find("#js_videomsg_list").find(".inner");
n.each(function(e,i){
var o=x.eq(i%2),n=$('<div id="appmsg%s"></div>'.sprintf(e.app_id),o).appendTo(o);
E[t]&&E[t](n,e);
});
}
p.popup("resetPosition"),o();
}
}
function o(){
(10==K.type||11==K.type)&&($("#msgSearchInput").on("input",function(){
0==$(this).val().length?$("#searchCloseBt").hide():$("#searchCloseBt").show();
}),$("#searchCloseBt").click(function(){
$("#msgSearchInput").val(""),$(this).hide();
}),$("#msgSearchBtn").click(function(){
return $("#msgSearchInput").val().length>0?(K.key=$("#msgSearchInput").val(),void l(K)):void f.err("请输入搜索关键词");
}),$("#msgSearchInput").keydown(function(e){
var i="which"in e?e.which:e.keyCode;
13==i&&$("#msgSearchBtn").trigger("click");
}));
}
function n(e){
w({
scene:"biz",
uploadScene:6,
maxSelect:1,
desc:"大小不超过5M",
onOK:function(i){
var t={
file_id:i[0].file_id,
copyright_status:i[0].copyright_status,
source:"file"
};
e.onSelect&&e.onSelect(e.type,t),this.destroy();
},
onHide:function(){
this.destroy();
}
});
}
function a(e){
new j({
onOK:function(i,t){
var o=$.extend({},t);
return o?(e.onSelect&&e.onSelect(i,o),!0):!1;
}
});
}
function s(e){
var o=e.type,d=e.onSelect,l=e.count||10,m=e.begin||0;
return 2==o?void n(e):21==o?void a(e):(t("file","loading",o),void v.getList(o,m,l,function(n){
if(p){
var a={
2:"img_cnt",
3:"voice_cnt",
4:"video_cnt"
},c=n.file_item,r=n.file_cnt[a[o]];
c.length<=0?t("file","no-media",o,c,null,e):(t("file","files",o,c,function(){
var e=u.find('[name="media"]:checked').val(),i=$("#fileItem"+e).data("opt");
return i?(d(o,i),!0):!1;
},e),i(m,l,r,s,e));
}
}));
}
function d(e){
return e.find(".appmsg.selected,.Js_videomsg.selected");
}
function l(e){
var o=e.type,n=e.onSelect,s=e.count||10,m=e.key||"",c=e.begin||0;
return K=$.extend(!0,{},e),15==o?void a(e):(t("appmsg","loading",o),void v.appmsg.getList(o,c,s,function(a){
if(p){
var m={
10:"app_msg_cnt",
11:"commondity_msg_cnt",
15:"video_msg_cnt"
},r=a.item,g=a.file_cnt[m[o]];
K.key&&(g=a.search_cnt),r.length<=0?t("appmsg","no-media",o,r,null,e):(t("appmsg","files",o,r,function(){
var e=d(u).parent().data("opt");
return e?(n(o,e),!0):!1;
}),i(c,s,g,l,e),15==o?(u.on("click",".Js_videomsg",function(){
$(this).find(".loading_tips").length?f.err("视频在转码中，不能选择"):""!=$(this).find(".title").text().trim()&&(u.find(".Js_videomsg").removeClass("selected"),
$(this).addClass("selected"));
}),u.on("mouseenter",".Js_videomsg",function(){
""==$(this).find(".title").text().trim()&&$(this).addClass("no_title");
}),u.on("mouseleave",".Js_videomsg",function(){
$(this).removeClass("no_title");
})):u.on("click",".appmsg",function(){
"1"==$(this).data("completed")&&(d(u).removeClass("selected"),$(this).addClass("selected"));
}));
}
},m));
}
function m(e){
var i=e.onSelect,t=e.type,o=new C({
multi:!1,
selectComplete:function(e){
return e?(i(t,e),void(o=null)):void f.err("请选择卡券");
},
param:{
need_member_card:1
},
source:"直接群发卡券"
});
o.show();
}
function c(i){
var t=e("media/plugin/audio.js");
window.openAudioPopup(!0),t.registerCb(function(e){
i.onSelect(3,e);
});
}
e("widget/media/media_dialog.css"),e("widget/media/richvideo.css"),e("common/wx/popup.js");
var r=wx.T,p=null,u=null,g="media align_edge",f=e("common/wx/Tips.js"),v=e("media/media_cgi.js"),h=e("biz_web/utils/upload.js"),_=(e("biz_web/ui/checkbox.js"),
e("common/wx/pagebar.js")),w=e("common/wx/media/imageDialog.js"),j=e("common/wx/media/videoDialog.js"),y=h.uploadBizFile,x=(template.render,
e("common/wx/media/audio.js")),k=e("common/wx/media/img.js"),b=e("common/wx/media/video.js"),S=e("common/wx/media/appmsg.js"),C=e("cardticket/send_card.js"),I=e("common/wx/time.js"),B=I.timeFormat,z=e("tpl/media/dialog/file_layout.html.js"),D=e("tpl/media/dialog/appmsg_layout.html.js"),J=e("tpl/media/dialog/videomsg_layout.html.js"),P=e("tpl/media/dialog/file.html.js"),T=1,F={},A={
appmsg:D,
videomsg:J,
file:z
};
template.helper("mediaDialogtimeFormat",function(e){
return B(e);
}),template.helper("mediaDialogInit",function(e){
return e.file_id?(F[e.file_id]=e,""):"";
});
var E={
2:function(e,i){
return new k({
container:$("#"+e.attr("id")),
file_id:i.file_id,
source:"file",
fakeid:i.fakeid
});
},
3:function(e,i){
var t=i;
return t.selector="#"+e.attr("id"),t.source="file",new x(t);
},
4:function(e,i){
var t=i;
return t.selector="#"+e.attr("id"),t.id=t.file_id,t.source="file",new b(t);
},
15:function(e,i){
var t=i;
return t.selector=e,t.id=t.app_id,t.tpl="videomsg",t.for_selection=!0,t.for_transfer=!!t.content,
t.hide_transfer=!!t.content,t.video_id=t.content,new b(t);
}
},K={};
return{
getFile:s,
getVoice:c,
getAppmsg:l,
getCardList:m
};
});define("common/wx/richEditor/emotionEditor.js",["widget/emotion_editor.css","tpl/richEditor/emotionEditor.html.js","common/wx/richEditor/wysiwyg.js","common/wx/richEditor/emotion.js","biz_web/utils/upload.js","common/wx/Tips.js","common/qq/Class.js"],function(t,i,e){
"use strict";
var o=wx.T,n=(t("widget/emotion_editor.css"),t("tpl/richEditor/emotionEditor.html.js")),s=t("common/wx/richEditor/wysiwyg.js"),r=t("common/wx/richEditor/emotion.js"),c=t("biz_web/utils/upload.js"),l=t("common/wx/Tips.js"),m=c.uploadCdnFile,a=t("common/qq/Class.js"),d={
isHTML:!0,
wordlimit:500,
hideUpload:!0
},w=1,h=a.declare({
init:function(t,i){
var e=this;
i=this.opt=$.extend(!0,{},d,i),e.selector$=t,i.gid=w++,e.selector$.html(o(n,i)),
e.emotion=new r(t.find(".js_emotionArea")),e.wysiwyg=new s(t.find(".js_editorArea"),{
init:function(){
t.find(".js_editorTip").html("还可以输入<em>{l}</em>字".format({
l:i.wordlimit
}));
},
textFilter:function(t){
return t=e.emotion.getEmotionText(t).replace(/<br.*?>/gi,"\n").replace(/<.*?>/g,""),
t=t.html(!1),i.isHTML?t:t.replace(/<br.*?>/gi,"\n").replace(/<.*?>/g,"");
},
nodeFilter:function(t){
var i="";
return"IMG"===t.nodeName.toUpperCase()&&(i=t),i;
},
change:function(){
var o=e.getContent(),n=i.wordlimit-o.length,s=t.find(".js_editorTip");
s.html(0>n?"已超出<em{cls}>{l}</em>字".format({
l:-n,
cls:' class="warn"'
}):"还可以输入<em>{l}</em>字".format({
l:n
}));
}
}),e.upload=m({
container:t.find(".js_upload"),
type:2,
multi:!1,
onComplete:function(t,i,o,n){
if(n&&n.base_resp&&0==n.base_resp.ret){
var s=n.content;
l.suc("上传成功"),e.wysiwyg.insertHTML(s);
}
}
}),e._initEvent(),e.insertHTML(i.text);
},
_initEvent:function(){
var t=$(".js_switch",this.selector$),i=this.emotion,e=this.wysiwyg;
i.click(function(t){
return e.insertHTML(i.getEmotionHTML(t)),!1;
}),i.hide(),t.click(function(){
$(this).parents(".js_editor").hasClass("disabled")||i.show();
}),$(document).on("click","*",function(t){
var e=$(t.target),o=e.filter(".js_switch"),n=e.filter(".js_emotion_i"),s=e.filter(".emotions_item");
o.length||n.length||s.length||i.hide();
});
},
setContent:function(t){
var i=t||"";
i=this.opt.linebreak?i.replace(/\n/g,"<br>"):i,t=r.emoji(i),this.wysiwyg.setContent(t,i.html(!1));
},
insertHTML:function(t){
t=t||"",this.wysiwyg.insertHTML(t);
},
getContent:function(){
var t=this.wysiwyg.getContent();
return t="string"==typeof t?t.trim():"",this.opt.linebreak?t.replace(/<br[^>]*>/gi,"\n"):t;
},
getHTML:function(){
var t=this.wysiwyg.getHTML().html(!1);
return"string"==typeof t?t.trim():"";
},
focus:function(){
this.wysiwyg.focus();
}
});
e.exports=h;
});define("common/qq/jquery.plugin/tab.js",["tpl/tab.html.js","widget/msg_tab.css"],function(t){
"use strict";
{
var a={
index:0
},n=t("tpl/tab.html.js");
t("widget/msg_tab.css");
}
$.fn.tab=function(t){
if(t&&t.tabs){
this.html(wx.T(n,{
tabs:t.tabs,
token:wx.data.t
}));
var e=this,s=e.find(".tab_navs"),i=s.find(".tab_nav"),d=i.length,c=null,r=null,l=function(a){
var n=a.data("tab"),s=a.data("type");
n&&(c!=a&&(c&&c.removeClass("selected"),r&&r.hide(),c=a,r=e.find(n).parent(),r.show(),
c.addClass("selected")),!!t.select&&t.select(a,r,n,s));
},u=function(a){
var n=a.data("tab"),s=a.data("type");
return t.click?t.click(a,e.find(n),n,s):!0;
};
return t=$.extend(!0,{},a,t),i.each(function(a){
var n=t.index,s=$(this).addClass("width"+d),i=s.data("tab");
a==n?l(s):e.find(i).parent().hide(),a==d-1&&s.addClass("no_extra");
}),s.on("click",".tab_nav",function(){
var t=u($(this));
return 0!=t&&l($(this));
}),{
getLen:function(){
return d;
},
getTabs:function(){
return i;
},
select:function(t){
return l(i.eq(t));
}
};
}
};
});define("common/qq/events.js",[],function(t,n,a){
"use strict";
function i(t){
this.data=t===!0?window.wx.events||{}:{};
}
i.prototype={
on:function(t,n){
return this.data[t]=this.data[t]||[],this.data[t].push(n),this;
},
off:function(t,n){
return this.data[t]&&this.data[t].length>0&&(n&&"function"==typeof n?$.each(this.data[t],function(a,i){
i===n&&this.data[t].splice(a,1);
}):this.data[t]=[]),this;
},
trigger:function(t){
var n=arguments;
return this.data[t]&&this.data[t].length>0&&$.each(this.data[t],function(t,a){
var i=a.apply(this,Array.prototype.slice.call(n,1));
return i===!1?!1:void 0;
}),this;
}
},a.exports=function(t){
return new i(t);
};
});define("common/lib/MockJax.js", [], function(e, t, n) {
try {
var r = +(new Date);
(function(e) {
function t(t) {
window.DOMParser == undefined && window.ActiveXObject && (DOMParser = function() {}, DOMParser.prototype.parseFromString = function(e) {
var t = new ActiveXObject("Microsoft.XMLDOM");
return t.async = "false", t.loadXML(e), t;
});
try {
var n = (new DOMParser).parseFromString(t, "text/xml");
if (!e.isXMLDoc(n)) throw "Unable to parse XML";
var r = e("parsererror", n);
if (r.length == 1) throw "Error: " + e(n).text();
return n;
} catch (i) {
var s = i.name == undefined ? i : i.name + ": " + i.message;
return e(document).trigger("xmlParseError", [ s ]), undefined;
}
}
function n(t, n, r) {
(t.context ? e(t.context) : e.event).trigger(n, r);
}
function r(t, n) {
var i = !0;
return typeof n == "string" ? e.isFunction(t.test) ? t.test(n) : t == n : (e.each(t, function(s) {
if (n[s] === undefined) return i = !1, i;
typeof n[s] == "object" ? i = i && r(t[s], n[s]) : e.isFunction(t[s].test) ? i = i && t[s].test(n[s]) : i = i && t[s] == n[s];
}), i);
}
function i(t, n) {
if (e.isFunction(t)) return t(n);
if (e.isFunction(t.url.test)) {
if (!t.url.test(n.url)) return null;
} else {
var i = t.url.indexOf("*");
if (t.url !== n.url && i === -1 || !(new RegExp(t.url.replace(/[-[\]{}()+?.,\\^$|#\s]/g, "\\$&").replace(/\*/g, ".+"))).test(n.url)) return null;
}
return t.data && n.data && !r(t.data, n.data) ? null : t && t.type && t.type.toLowerCase() != n.type.toLowerCase() ? null : t;
}
function s(n, r, i) {
var s = function(s) {
return function() {
return function() {
var s;
this.status = n.status, this.statusText = n.statusText, this.readyState = 4, e.isFunction(n.response) && n.response(i), r.dataType == "json" && typeof n.responseText == "object" ? this.responseText = JSON.stringify(n.responseText) : r.dataType == "xml" ? typeof n.responseXML == "string" ? (this.responseXML = t(n.responseXML), this.responseText = n.responseXML) : this.responseXML = n.responseXML : this.responseText = n.responseText;
if (typeof n.status == "number" || typeof n.status == "string") this.status = n.status;
typeof n.statusText == "string" && (this.statusText = n.statusText), s = this.onreadystatechange || this.onload, e.isFunction(s) ? (n.isTimeout && (this.status = -1), s.call(this, n.isTimeout ? "timeout" : undefined)) : n.isTimeout && (this.status = -1);
}.apply(s);
};
}(this);
n.proxy ? v({
global: !1,
url: n.proxy,
type: n.proxyType,
data: n.data,
dataType: r.dataType === "script" ? "text/plain" : r.dataType,
complete: function(e) {
n.responseXML = e.responseXML, n.responseText = e.responseText, n.status = e.status, n.statusText = e.statusText, this.responseTimer = setTimeout(s, n.responseTime || 0);
}
}) : r.async === !1 ? s() : this.responseTimer = setTimeout(s, n.responseTime || 50);
}
function o(t, n, r, i) {
return t = e.extend(!0, {}, e.mockjaxSettings, t), typeof t.headers == "undefined" && (t.headers = {}), t.contentType && (t.headers["content-type"] = t.contentType), {
status: t.status,
statusText: t.statusText,
readyState: 1,
open: function() {},
send: function() {
i.fired = !0, s.call(this, t, n, r);
},
abort: function() {
clearTimeout(this.responseTimer);
},
setRequestHeader: function(e, n) {
t.headers[e] = n;
},
getResponseHeader: function(e) {
if (t.headers && t.headers[e]) return t.headers[e];
if (e.toLowerCase() == "last-modified") return t.lastModified || (new Date).toString();
if (e.toLowerCase() == "etag") return t.etag || "";
if (e.toLowerCase() == "content-type") return t.contentType || "text/plain";
},
getAllResponseHeaders: function() {
var n = "";
return e.each(t.headers, function(e, t) {
n += e + ": " + t + "\n";
}), n;
}
};
}
function u(e, t, n) {
a(e), e.dataType = "json";
if (e.data && y.test(e.data) || y.test(e.url)) {
l(e, t, n);
var r = /^(\w+:)?\/\/([^\/?#]+)/, i = r.exec(e.url), s = i && (i[1] && i[1] !== location.protocol || i[2] !== location.host);
e.dataType = "script";
if (e.type.toUpperCase() === "GET" && s) {
var o = f(e, t, n);
return o ? o : !0;
}
}
return null;
}
function a(e) {
if (e.type.toUpperCase() === "GET") y.test(e.url) || (e.url += (/\?/.test(e.url) ? "&" : "?") + (e.jsonp || "callback") + "=?"); else if (!e.data || !y.test(e.data)) e.data = (e.data ? e.data + "&" : "") + (e.jsonp || "callback") + "=?";
}
function f(t, n, r) {
var i = r && r.context || t, s = null;
return n.response && e.isFunction(n.response) ? n.response(r) : typeof n.responseText == "object" ? e.globalEval("(" + JSON.stringify(n.responseText) + ")") : e.globalEval("(" + n.responseText + ")"), c(t, i, n), h(t, i, n), e.Deferred && (s = new e.Deferred, typeof n.responseText == "object" ? s.resolveWith(i, [ n.responseText ]) : s.resolveWith(i, [ e.parseJSON(n.responseText) ])), s;
}
function l(e, t, n) {
var r = n && n.context || e, i = e.jsonpCallback || "jsonp" + b++;
e.data && (e.data = (e.data + "").replace(y, "=" + i + "$1")), e.url = e.url.replace(y, "=" + i + "$1"), window[i] = window[i] || function(n) {
data = n, c(e, r, t), h(e, r, t), window[i] = undefined;
try {
delete window[i];
} catch (s) {}
head && head.removeChild(script);
};
}
function c(e, t, r) {
e.success && e.success.call(t, r.responseText || "", status, {}), e.global && n(e, "ajaxSuccess", [ {}, e ]);
}
function h(t, r) {
t.complete && t.complete.call(r, {}, status), t.global && n("ajaxComplete", [ {}, t ]), t.global && !--e.active && e.event.trigger("ajaxStop");
}
function p(t, n) {
var r, s, a;
typeof t == "object" ? (n = t, t = undefined) : n.url = t, s = e.extend(!0, {}, e.ajaxSettings, n);
for (var f = 0; f < m.length; f++) {
if (!m[f]) continue;
a = i(m[f], s);
if (!a) continue;
g.push(s), e.mockjaxSettings.log(a, s);
if (s.dataType === "jsonp") if (r = u(s, a, n)) return r;
return a.cache = s.cache, a.timeout = s.timeout, a.global = s.global, d(a, n), function(t, n, i, s) {
r = v.call(e, e.extend(!0, {}, i, {
xhr: function() {
return o(t, n, i, s);
}
}));
}(a, s, n, m[f]), r;
}
return v.apply(e, [ n ]);
}
function d(e, t) {
if (!(e.url instanceof RegExp)) return;
if (!e.hasOwnProperty("urlParams")) return;
var n = e.url.exec(t.url);
if (n.length === 1) return;
n.shift();
var r = 0, i = n.length, s = e.urlParams.length, o = Math.min(i, s), u = {};
for (r; r < o; r++) {
var a = e.urlParams[r];
u[a] = n[r];
}
t.urlParams = u;
}
var v = e.ajax, m = [], g = [], y = /=\?(&|$)/, b = (new Date).getTime();
e.extend({
ajax: p
}), e.mockjaxSettings = {
log: function(t, n) {
if (t.logging === !1 || typeof t.logging == "undefined" && e.mockjaxSettings.logging === !1) return;
if (window.console && console.log) {
var r = "MOCK " + n.type.toUpperCase() + ": " + n.url, i = e.extend({}, n);
if (typeof console.log == "function") console.log(r, i); else try {
console.log(r + " " + JSON.stringify(i));
} catch (s) {
console.log(r);
}
}
},
logging: !0,
status: 200,
statusText: "OK",
responseTime: 500,
isTimeout: !1,
contentType: "text/plain",
response: "",
responseText: "",
responseXML: "",
proxy: "",
proxyType: "GET",
lastModified: null,
etag: "",
headers: {
etag: "IJF@H#@923uf8023hFO@I#H#",
"content-type": "text/plain"
}
}, e.mockjax = function(e) {
var t = m.length;
return m[t] = e, t;
}, e.mockjaxClear = function(e) {
arguments.length == 1 ? m[e] = null : m = [], g = [];
}, e.mockjax.handler = function(e) {
if (arguments.length == 1) return m[e];
}, e.mockjax.mockedAjaxCalls = function() {
return g;
};
})(jQuery);
} catch (i) {
wx.jslog({
src: "common/lib/MockJax.js"
}, i);
}
});define("common/wx/cgiReport.js", [ "common/wx/Tips.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = e("common/wx/Tips.js");
t.error = function(e, t) {
var n = 11;
switch (e) {
case "timeout":
n = 7;
break;
case "error":
n = 8;
break;
case "notmodified":
n = 9;
break;
case "parsererror":
n = 10;
}
t.data.lang && delete t.data.lang, t.data.random && delete t.data.random, t.data.f && delete t.data.f, t.data.ajax && delete t.data.ajax, t.data.token && delete t.data.token, $.ajax({
url: "/misc/jslog?1=1",
data: {
content: "[fakeid={uin}] [xhr] [url={url}] [param={param}] [info={info}] [useragent={userAgent}] [page={page}]".format({
uin: wx.data.uin,
useragent: window.navigator.userAgent,
page: location.href,
url: t.url,
param: $.param(t.data).substr(0, 50),
info: e
}),
id: n,
level: "error"
},
type: "POST"
}), $.ajax({
url: "/misc/jslog?1=1",
data: {
content: "[fakeid={uin}] [xhr] [url={url}] [param={param}] [info={info}] [useragent={userAgent}] [page={page}]".format({
uin: wx.data.uin,
useragent: window.navigator.userAgent,
page: location.href,
url: t.url,
param: $.param(t.data).substr(0, 50),
info: e
}),
id: 6,
level: "error"
},
type: "POST"
}), e == "timeout" && i.err("你的网络环境较差，请稍后重试");
};
} catch (s) {
wx.jslog({
src: "common/wx/cgiReport.js"
}, s);
}
});define("tpl/dialog.html.js",[],function(){
return'<div class="dialog_wrp {className}" style="{if width}width:{width}px;{/if}{if height}height:{height}px;{/if};display:none;">\n  <div class="dialog" id="{id}">\n    <div class="dialog_hd">\n      <h3>{title}</h3>\n      {if !hideClose}\n      <!--#0001#-->\n      <a href="javascript:;" class="pop_closed" onclick="return false;">关闭</a>\n      <!--%0001%-->\n      {/if}\n    </div>\n    <div class="dialog_bd">\n      <div class="page_msg large simple default {msg.msgClass}">\n        <div class="inner group">\n          <span class="msg_icon_wrapper"><i class="icon_msg {icon} "></i></span>\n          <div class="msg_content ">\n          {if msg.title}<h4>{=msg.title}</h4>{/if}\n          {if msg.text}<p>{=msg.text}</p>{/if}\n          </div>\n        </div>\n      </div>\n    </div> \n    <div class="dialog_ft">\n  	{if !hideClose}\n      {each buttons as bt index}\n      <a href="javascript:;" class="btn {bt.type} js_btn" onclick="return false;">{bt.text}</a>\n      {/each}\n  	{/if}\n    </div>\n  </div>\n</div>\n{if mask}<div class="mask"></div>{/if}\n\n';
});