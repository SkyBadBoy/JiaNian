define("infringement/login.js",["common/wx/Cgi.js"],function(n){
"use strict";
function i(n){
$(".js_tips").hide(),$("#js_"+n).show();
}
function e(){
t.post({
url:wx.url("/acct/infringementlogin?action=ask&login_ticket="+o.sign),
type:"get",
mask:!1,
error:function(){
a-->0&&setTimeout(e,c);
}
},function(n){
var t=+n.base_resp.ret;
switch(t){
case 0:
location.href="/acct/infringement?action=getmanual&t=infringement/manual&type=1&lang=zh_CN";
break;

case-37:
i("success");
break;

case-35:
location.reload();
}
setTimeout(e,c),a=3;
});
}
var t=n("common/wx/Cgi.js"),o=wx.cgiData,c=5e3,a=3;
setTimeout(e,c);
var r={
ready:function(n){
"undefined"!=typeof WeixinJSBridge&&WeixinJSBridge.invoke?n():document.addEventListener("WeixinJSBridgeReady",n,!1);
},
invoke:function(n,i,e){
r.ready(function(){
WeixinJSBridge.invoke(n,i,e);
});
},
on:function(n,i){
r.ready(function(){
WeixinJSBridge.on(n,i);
});
}
};
r.on("menu:share:appmessage",function(){
r.invoke("sendAppMessage",{
img_url:"https://mp.weixin.qq.com/acct/infringementlogin?action=getqrcode&login_ticket="+wx.cgiData.sign,
link:location.href,
title:"公众平台侵权投诉系统",
desc:"mp.weixin.qq.com"
},function(){});
}),r.on("menu:share:timeline",function(){
r.invoke("shareTimeline",{
img_url:"https://mp.weixin.qq.com/acct/infringementlogin?action=getqrcode&login_ticket="+wx.cgiData.sign,
link:location.href,
title:"公众平台侵权投诉系统"
},function(){});
});
});