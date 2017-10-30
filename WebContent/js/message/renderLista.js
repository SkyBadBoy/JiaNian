define("message/renderList.js",["common/qq/emoji.js","common/wx/simplePopup.js","common/qq/Class.js","common/wx/media/img.js","common/wx/media/audio.js","common/wx/media/video.js","common/wx/media/idCard.js","tpl/msgListItem.html.js","common/wx/RichBuddy.js","common/wx/remark.js","common/wx/media/simpleAppmsg.js","common/qq/events.js","message/message_cgi.js","common/wx/time.js","common/wx/Tips.js","tpl/message/video_popup.html.js","common/wx/richEditor/emotionEditor.js","common/wx/verifycode.js"],function(t){
"use strict";
function e(t){
var e=new f;
$(".avatar",t).mouseover(function(){
var t=$(this),n=parseInt(t.attr("data-fakeid"),10),i=parseInt(t.attr("data-id"),10),o=t.offset(),a=t.width();
n!=wx.data.uin&&e.show({
fakeId:n,
tmpmsgid:i,
position:{
left:o.left+a+2,
top:o.top
}
});
}).mouseout(function(){
e.hide();
});
}
function n(t){
$(".js_changeRemark",t).unbind("click").click(function(){
var e=$(this),n=(e.closest("li.msgListItem"),e.attr("data-fakeid")),i=$(".nickname[data-fakeid="+n+"]",t),o=$(".remark_name[data-fakeid="+n+"]",t),a=""==$.trim(i.html())?"":o.html();
p.show(n,a);
}),g.on("Remark:changed",function(e,n){
var i,o,a,s;
i=$(".nickname[data-fakeid="+e+"]",t),o=$(".remark_name[data-fakeid="+e+"]",t),a=""==$.trim(i.html())?"":o.html(),
s=""==a?o.html():i.find("strong").html(),""==n&&""!=a?(i.html(""),o.html(s)):""!=n&&""==a?(o.html(n),
i.html("(<strong>{nickName}</strong>)".format({
nickName:s
}))):""!=n&&""!=a&&o.html(n);
});
}
function i(t){
$(t).off("click",".js_save").on("click",".js_save",function(){
var t=$(this),e=t.attr("idx"),n=t.attr("data-type");
4==n?$(x).popup({
title:"保存为视频消息",
onOK:function(){
var t=this.get().find(".title").val(),n=this.get().find(".digest").val();
return t.length<1||t.length>64?(k.err("请输入1到64个字的标题"),!0):""!=n&&n.length>120?(k.err("简介字数不能超过120字"),
!0):void j.save(e,t,n,function(){});
},
onCancel:function(){
this.hide();
},
onHide:function(){
this.remove();
}
}):new r({
title:"填写素材名字",
callback:function(t){
j.save(e,t,function(){});
},
rule:function(t){
var e=$.trim(t);
return""!=e&&e.length<=50&&-1==e.indexOf(" ");
},
msg:"素材名必须为1到50个字符，并且素材名不能包含空格"
});
});
}
function o(t){
t.off("click",".js_star").on("click",".js_star",function(){
var t=$(this),e=t.attr("idx"),n=t.attr("action"),i=t.attr("starred");
j.star(e,i,function(){
1==i?(t.removeClass("star_orange").addClass("star_gray"),t.attr("starred",0)):(t.removeClass("star_gray").addClass("star_orange"),
t.attr("starred",1)),t.attr("title",1==i?"收藏消息":"取消收藏"),"star"==n&&1==i&&$("#msgListItem"+e).fadeOut();
});
});
}
function a(t){
t.off("click",".js_reply").on("click",".js_reply",function(){
var e=$(this),n=e.data("id"),i=$("#msgListItem"+n).toggleClass("replying");
$(".replying",t).each(function(){
var t=$(this),e=t.data("id");
e!=n&&t.removeClass("replying");
}),i.data("hasClickQuickReply")||(s(i.find(".js_quick_reply_box"),i),i.data("hasClickQuickReply",!0));
});
}
function s(e,n){
var i=140,o=$(".js_editor",e),a=new b(o,{
wordlimit:i,
isHTML:!0
}),s=$(".js_reply_OK",e),r=$(".js_reply_pickup",e);
r.unbind("click").click(function(){
var t=$(this).data("id");
$("#msgListItem"+t).removeClass("replying");
}),e.keydown(function(t){
return wx.isHotkey(t,"enter")?(s.click(),!1):void 0;
});
{
var m=null,c=$(".verifyCode",e);
t("common/wx/verifycode.js");
}
s.unbind("click").click(function(){
var t=$(this),e=t.data("id"),o=t.data("fakeid"),s=a.getContent();
return s.length<=0||s.length>i?void k.err("快捷回复的内容必须为1到140个字符"):null!=m&&m.getCode().trim().length<=0?(k.err("请输入验证码"),
void m.focus()):(k.suc("回复中...请稍候"),t.btn(!1),void j.quickReply({
toFakeId:o,
content:s,
quickReplyId:e,
imgcode:m&&m.getCode().trim()
},function(){
a.setContent(""),c.html("").addClass("dn"),m=null,n.addClass("replyed"),t.btn(!0);
},function(e){
t.btn(!0),e&&e.base_resp&&-8==e.base_resp.ret&&(m=c.html("").removeClass("dn").verifycode().data("verifycode"),
m.focus());
}));
});
}
t("common/qq/emoji.js");
var r=t("common/wx/simplePopup.js"),m=(t("common/qq/Class.js"),t("common/wx/media/img.js")),c=t("common/wx/media/audio.js"),d=t("common/wx/media/video.js"),l=t("common/wx/media/idCard.js"),u=t("tpl/msgListItem.html.js"),f=t("common/wx/RichBuddy.js"),p=t("common/wx/remark.js"),v=t("common/wx/media/simpleAppmsg.js"),h=t("common/qq/events.js"),g=h(!0),j=t("message/message_cgi.js"),w=t("common/wx/time.js"),k=t("common/wx/Tips.js"),_=w.timeFormat,x=t("tpl/message/video_popup.html.js"),y=!1,C={
1:function(t,e){
return t.html(e.content.emoji());
},
2:function(t,e){
return new m({
container:$("#"+t.attr("id")),
file_id:0,
msgid:e.id,
source:e.source,
fakeid:e.fakeid
});
},
3:function(t,e){
var n=e;
return n.selector="#"+t.attr("id"),new c(n);
},
4:function(t,e){
var n=e;
return n.selector="#"+t.attr("id"),n.ff_must_flash=!0,new d(n);
},
42:function(t,e){
var n=e;
return n.container="#"+t.attr("id"),new l(n);
},
10:function(t,e){
var n=e;
return n.container="#"+t.attr("id"),new v(n);
},
15:function(t,e){
var n=e;
return n.selector=t,n.tpl="videomsg",n.id=1e5*Math.random()|0,new d(n);
},
62:function(t,e){
var n=e;
return n.selector="#"+t.attr("id"),n.ff_must_flash=!0,"star"==t.closest("li").find(".js_star").attr("action")&&(n.video_url=e.cdn_video_url,
n.video_thumb_url=e.cdn_video_thumb_url),new d(n);
}
},b=t("common/wx/richEditor/emotionEditor.js"),q=function(){
console.log("initImg"),$(".avatar img").each(function(){
var t=$(this);
t.data("src")&&(t.attr("src",t.data("src")),t.removeAttr("data-src"));
});
},I=function(t){
if(t.list){
{
var s=t.list,r={};
s.length;
}
template.helper("mediaInit",function(t){
return t.id?(r[t.id]=t,""):"";
}),template.helper("timeFormat",function(t){
return _(t.date_time);
}),template.helper("id2singleURL",function(t){
return wx.url("/cgi-bin/singlesendpage?tofakeid=%s&t=message/send&action=index".sprintf(t.fakeid));
}),s.each(function(t){
t.video_url&&(t.type=15),t.type={
5:10,
6:10,
11:10,
16:15
}[t.type]||t.type;
var e={
1:0,
2:11,
3:11,
4:1,
10:0,
15:11,
42:11,
62:wx.acl.msg_acl.can_use_shortvideo?10:0
}[t.type];
e="undefined"==typeof e?111:e,t.btnsave=2==(2&e),t.btndown=1==(1&e);
});
var m=$(t.container),c=$(wx.T(u,{
token:wx.data.t,
list:s,
uin:wx.data.uin,
action:t.action
}).trim());
t.preAppend?c.prependTo(m):c.appendTo(m),y?q():(y=!0,console.log("before bind load"),
$(window).on("load",function(){
console.log("window onload"),q();
})),$(".wxMsg",c).each(function(){
var t=$(this),e=t.data("id"),n=r[e];
if(n){
var i=n.type;
i&&C[i]&&C[i](t,n);
}
}),e(m),n(m),i(m),o(m),a(m);
}
};
return I;
});