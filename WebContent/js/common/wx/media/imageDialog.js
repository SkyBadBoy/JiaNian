define("common/wx/media/imageDialog.js",["common/wx/Cgi.js","common/wx/Tips.js","common/wx/popup.js","common/wx/pagebar.js","biz_web/utils/upload.js","tpl/media/dialog/image_layout.html.js","tpl/media/dialog/image_list.html.js","tpl/media/dialog/image_group.html.js","page/media/dialog_img_pick.css"],function(e){
"use strict";
var i=e("common/wx/Cgi.js"),t=e("common/wx/Tips.js"),n=(e("common/wx/popup.js"),
e("common/wx/pagebar.js")),o=e("biz_web/utils/upload.js"),a=e("tpl/media/dialog/image_layout.html.js"),r=e("tpl/media/dialog/image_list.html.js"),l=e("tpl/media/dialog/image_group.html.js"),s=(template.render,
template.compile(l)),d=template.compile(r);
e("page/media/dialog_img_pick.css");
var g=function(e){
return new p(e);
},p=function(e){
this.options=e,this.events=[],this.imgArr=[],this.converting=0,this.fromUpload=[],
c.init.call(this);
},c={
init:function(){
var e=this,i=e.options=$.extend(!0,{
tpl:a,
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
}),e.dialog.popup("get").find(".js_loading").show(),u.getImagesByGroupId({
group_id:i.group,
count:i.perPage
},function(t){
if(e.dialog){
var n=t.page_info;
n.scene=i.scene,n.group=i.group;
var o=e.dialog.popup("get"),a=s(n);
o.find(".js_loading").hide(),o.find(".js_group").append(a).find(".js_total").text("(%s)".sprintf(n.file_cnt.img_cnt)),
c.renderImageList(o.find(".js_list"),n,e.imgArr),c.initEvent.call(e,t),c.initPageBar.call(e,n,i.group),
e.dialog.popup("resetPosition");
}
}),c.initUpload.call(e,i.group);
},
initEvent:function(){
var e=this,i=e.dialog.popup("get"),n=e.options;
i.on("click",".js_imageitem",function(){
var o,a=$(this),r=a.find("label"),l=i.find(".js_btn_p").eq(0),s=a.data("url"),d=a.data("id"),g=a.data("format");
r.hasClass("selected")?(s||e.converting--,r.removeClass("selected"),o=m.indexOf(e.imgArr,d),
o>=0&&e.imgArr.splice(o,1),i.find(".js_selected").text(e.imgArr.length)):1==n.maxSelect?(s||(e.converting=1),
r.addClass("selected"),a.siblings().find("label").removeClass("selected"),e.imgArr=[{
url:s,
file_id:d,
format:g
}],i.find(".js_selected").text(e.imgArr.length)):n.maxSelect>e.imgArr.length?(s||e.converting++,
r.addClass("selected"),e.imgArr.push({
url:s,
file_id:d,
format:g
}),i.find(".js_selected").text(e.imgArr.length)):t.err("最多可选%s张".sprintf(n.maxSelect)),
e.imgArr.length>0?l.enable().addClass("btn_primary"):l.disable(),"cdn"==n.scene&&r.hasClass("selected")&&!s&&u.getCdnUrlByFileId({
file_id:d,
group_id:i.find(".js_groupitem.selected").data("groupid")
},function(i){
0==i.errcode?(e.converting--,a.data("url",i.url),o=m.indexOf(e.imgArr,d),o>=0&&(e.imgArr[o].url=i.url),
e.trigger("converted")):(t.err("转存失败"),a.click());
});
}),i.on("click",".js_groupitem",function(t,o){
var a=$(this),r=i.find(".js_list"),l=i.find(".js_loading"),s=i.find(".js_pagebar"),d=a.data("groupid");
a.hasClass("selected")||(a.addClass("selected").siblings(".selected").removeClass("selected"),
$("#js_imageupload").data("groupid",d),r.hide(),s.hide(),l.show(),u.getImagesByGroupId({
group_id:d,
count:n.perPage
},function(t){
if(e.dialog&&d==i.find(".js_groupitem.selected").data("groupid")){
t=t.page_info,t.scene=n.scene,l.hide(),s.show(),c.renderImageList(r,t,e.imgArr),
c.initPageBar.call(e,t,d),c.initUpload.call(e);
for(var a=0;o&&"upload"==o.source&&a<o.count;++a)r.children().eq(a).click();
}
}));
});
},
initPageBar:function(e,i){
var t=this,o=t.dialog.popup("get"),a=t.options;
c.pagebar&&c.pagebar.destroy();
var r=0;
return 0==i?r=e.file_cnt.img_cnt:e.file_group_list.file_group.each(function(e){
e.id==i&&(r=e.count);
}),a.perPage>=r?void o.find(".js_pagebar").empty():void(c.pagebar=new n({
container:o.find(".js_pagebar"),
perPage:a.perPage,
initShowPage:1,
totalItemsNum:r,
first:!1,
last:!1,
isSimple:!0,
callback:function(e){
var i=o.find(".js_groupitem.selected").data("groupid"),n=o.find(".js_list"),r=o.find(".js_loading"),l=o.find(".js_pagebar");
n.hide(),l.hide(),r.show(),u.getImagesByGroupId({
group_id:i,
begin:e.perPage*(e.currentPage-1),
count:a.perPage
},function(e){
e=e.page_info,e.scene=a.scene,r.hide(),l.show(),c.renderImageList(n,e,t.imgArr);
});
}
}));
},
initUpload:function(e){
var i=this,n=i.dialog.popup("get"),a=n.find(".js_imageupload"),r="js_imageupload"+Math.random().toString().substr(2),l=n.find(".js_groupitem.selected").data("groupid")||e||1;
a.attr("id",r).off().children().remove(),o.uploadImageLibFile({
container:"#"+r,
multi:!0,
type:2,
doublewrite:!0,
groupid:l,
onComplete:function(e,n,o,a){
0==a.base_resp.ret&&t.suc("上传成功"),i.fromUpload.push(a.content);
},
onAllComplete:function(e,i){
var t,o=n.find(".js_groupitem.selected");
i.filesUploaded>0&&(t=+o.find("span").text(),o.removeClass("selected").trigger("click",{
source:"upload",
count:i.filesUploaded
}),o.find("span").text(t+i.filesUploaded));
},
showError:!0
});
},
renderImageList:function(e,i,t){
i.file_item.each(function(e){
e.img_url=wx.url("/cgi-bin/getimgdata?mode=small&source=file&fileId=%s".sprintf(e.file_id)),
-1!=m.indexOf(t,e.file_id)&&(e.selected=1);
}),e.html(d(i)).show();
}
},u={
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
},m={
indexOf:function(e,i){
for(var t=0,n=e.length;n>t;++t)if(e[t].file_id==i)return t;
return-1;
}
},f={
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
return $.extend(p.prototype,f),g;
});