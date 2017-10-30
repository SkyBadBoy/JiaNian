define("message/send.js",["common/qq/emoji.js","common/wx/richEditor/msgSender.js","message/message_cgi.js","message/renderList.js","common/wx/verifycode.js"],function(e){
"use strict";
e("common/qq/emoji.js");
var i=wx.cgiData,t=i.tofakeid,s=(i.to_nick_name,i.msg_items.msg_item),n=e("common/wx/richEditor/msgSender.js"),o=e("message/message_cgi.js");
$("#js_nick_name").html(i.to_nick_name.emoji());
var m=1e3,r=5*m;
!function(){
function i(e){
if(!(e.length<=0)){
var i=e[0];
c=i.fakeid,d=i.id,g=i.date_time,f({
container:"#listContainer",
preAppend:!0,
list:e
});
}
}
function a(e){
return 20*m>e&&(e+=5*m),e;
}
var c,d,g,f=e("message/renderList.js"),u=function(){
o.getNewMsg(t,c,d,g,function(e){
i(e),r=e.length<=0?a(r):5*m,setTimeout(u,r);
},function(){
r=a(r),setTimeout(u,r);
});
};
i(s),setTimeout(u,r);
{
var l=new n($("#js_msgSender"),{
data:{
type:1
},
acl:wx.acl.msg_acl
}),_=null,j=$("#verifycode");
e("common/wx/verifycode.js");
}
$("#js_submit").click(function(){
var e=l.getData();
if(!e.error){
var i=e.data;
if(i.tofakeid=t,i.fileid=i.file_id,i.appmsgid=i.app_id,null!=_&&_.getCode().trim().length<=0)return Tips.err("请输入验证码"),
void _.focus();
i.imgcode=_&&_.getCode().trim();
var s=$(this).btn(!1);
o.sendMsg(i,function(){
s.btn(!0),j.html("").hide(),_=null,l.clear(),l.emotionEditor.insertHTML(),r=3*m,
setTimeout(u,r);
},function(e){
j.html("").hide(),s.btn(!0),e.base_resp&&-8==e.base_resp.ret&&(_=j.html("").show().verifycode().data("verifycode"),
_.focus());
});
}
});
}(i);
});