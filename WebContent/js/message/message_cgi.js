define("message/message_cgi.js",["common/wx/Tips.js","common/wx/Cgi.js"],function(e,s,r){
"use strict";
var t={
masssend:"/cgi-bin/masssend?t=ajax-response",
massdel:"/cgi-bin/masssendpage?action=delete",
star:"/cgi-bin/setstarmessage?t=ajax-setstarmessage",
save:"/cgi-bin/savemsgtofile?t=ajax-response",
sendMsg:"/cgi-bin/singlesend?t=ajax-response&f=json",
getNewMsg:"/cgi-bin/singlesendpage?tofakeid=%s&f=json&action=sync&lastmsgfromfakeid=%s&lastmsgid=%s&createtime=%s",
getNewMsgCount:"/cgi-bin/getnewmsgnum?f=json&t=ajax-getmsgnum&lastmsgid=",
pageNav:"/cgi-bin/message?f=json&offset=%s&day=%s&keyword=%s&action=%s&frommsgid=%s&count=%s",
searchMsgByKeyword:"/cgi-bin/getmessage?t=ajax-message&count=10&keyword="
},n=e("common/wx/Tips.js"),o=e("common/wx/Cgi.js");
r.exports={
masssend:function(e,s,r){
o.post({
url:wx.url(t.masssend),
data:e,
error:function(){
n.err("发送失败"),r&&r();
}
},function(e){
if(!e||!e.base_resp)return n.err("发送失败"),void(r&&r(e));
var t=e.base_resp.ret;
return"0"==t?(n.suc("发送成功"),void(s&&s(e))):(n.err("64004"==t?"今天的群发数量已到，无法群发":"67008"==t?"消息中可能含有具备安全风险的链接，请检查":"-8"==t?"请输入验证码":"14002"==t?"没有“审核通过”的门店。确认有至少一个“审核通过”的门店后可进行卡券投放。":"14003"==t?"投放用户缺少测试权限，请先设置白名单":"发送失败"),
void(r&&r(e)));
});
},
massdel:function(e,s,r,a){
o.post({
url:wx.url(t.massdel),
data:{
id:e,
idx:a
},
error:function(){
n.err("删除失败");
}
},function(e){
return e&&e.base_resp&&0==e.base_resp.ret?(n.suc("删除成功"),void(s&&s(e))):(n.err("删除失败"),
void(r&&r(e)));
});
},
getNewMsg:function(e,s,r,n,a,i){
o.get({
url:wx.url(t.getNewMsg.sprintf(e,s,r,n)),
mask:!1,
handlerTimeout:!0,
error:i
},function(e){
e&&e.base_resp&&0==e.base_resp.ret&&a&&a(e.page_info.msg_items.msg_item);
});
},
save:function(e,s,r,a){
"function"==typeof r&&(a=r,r=""),o.post({
mask:!1,
url:wx.url(t.save),
data:{
msgid:e,
filename:s,
digest:r
},
error:function(){
n.err("保存素材失败");
}
},function(e){
if(!e||!e.base_resp)return void n.err("保存素材失败");
var s=e.base_resp.ret;
"0"==s?(n.suc("保存素材成功"),"function"==typeof a&&a(e)):n.err("保存素材失败");
});
},
star:function(e,s,r){
o.post({
mask:!1,
url:wx.url(t.star),
data:{
msgid:e,
value:1==s?0:1
},
error:function(){
n.err(1==s?"取消收藏失败":"收藏消息失败");
}
},function(e){
if(!e||!e.base_resp)return void n.err(1==s?"取消收藏失败":"收藏消息失败");
var t=e.base_resp.ret;
0!=t?n.err(1==s?"取消收藏失败":"收藏消息失败"):(n.suc(1==s?"取消收藏成功":"收藏消息成功"),"function"==typeof r&&r(e));
});
},
sendMsg:function(e,s,r){
o.post({
url:wx.url(t.sendMsg),
data:e,
error:function(){
n.err("发送失败"),r&&r();
}
},function(e){
if(!e||!e.base_resp)return n.err("发送失败"),void(r&&r(e));
var t=e.base_resp.ret;
return 0==t?(n.suc("回复成功"),void("function"==typeof s&&s(e))):(n.err(10703==t?"对方关闭了接收消息":10700==t?"不能发送，对方不是你的粉丝":10701==t?"该用户已被加入黑名单，无法向其发送消息":62752==t?"消息中可能含有具备安全风险的链接，请检查":10704==t?"该素材已被删除":10705==t?"该素材已被删除":10706==t?"由于该用户48小时未与你互动，你不能再主动发消息给他。直到用户下次主动发消息给你才可以对其进行回复。":-8==t?"请输入验证码":"发送失败"),
void(r&&r(e)));
});
},
getNewMsgCount:function(e,s,r){
o.post({
mask:!1,
handlerTimeout:!0,
url:wx.url(t.getNewMsgCount+e),
error:r
},function(e){
"function"==typeof s&&s(e);
});
},
quickReply:function(e,s,r){
this.sendMsg({
mask:!1,
tofakeid:e.toFakeId,
imgcode:e.imgcode,
type:1,
content:e.content,
out_trade_no:e.out_trade_no,
quickreplyid:e.quickReplyId
},s,r);
},
pageNav:function(e,s,r){
var n=t.pageNav.sprintf((e.page-1)*e.count,e.day||"",e.keyword||"",e.action||"",e.frommsgid||"",e.count||"");
o.post({
dataType:"json",
url:wx.url(n),
mask:!1,
data:{},
error:r
},function(e){
e&&e.base_resp&&0==e.base_resp.ret&&"function"==typeof s&&s(e.msg_items.msg_item);
});
},
searchMsgByKeyword:function(e,s,r){
o.post({
dataType:"html",
mask:!1,
url:wx.url(url.searchMsgByKeyword+e),
error:function(){
n.err("系统发生异常，请刷新页面重试"),r&&r({});
}
},function(e){
"function"==typeof s&&s($.parseJSON(e));
});
}
};
});