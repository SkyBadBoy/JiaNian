var BJ_REPORT=function(e){
function i(){
if(t.id!=S.IDS.DEFAULT||t.key!=S.KEY)return{
id:t.id,
key:t.key
};
var e={
_href:location.href,
href:location.href.replace("https://mp.weixin.qq.com/","")
};
e.cgi=e.href.indexOf("?")>-1?e.href.match(/.*?\?/g)[0].slice(0,-1):e.href;
var i=(e.href+"&").match(/action\=(.*?)&/);
i&&i[1]&&(e.action=i[1]);
var n=S.IDS.DEFAULT,r=S.KEY;
return"cgi-bin/masssendpage"==e.cgi?(n=S.IDS.MASS,r=66):"advanced/autoreply"==e.cgi?(n=S.IDS.AUTO_REPLY,
r=70):"advanced/selfmenu"==e.cgi?(n=S.IDS.SELF_MENU,r=68):"misc/appmsgcomment"==e.cgi?(n=S.IDS.COMMENT,
r=71):"cgi-bin/newoperatevote"==e.cgi?(n=S.IDS.VOTE,r=72):"misc/kf"==e.cgi?(n=S.IDS.KF,
r=73):"merchant/rewardstat"==e.cgi||"merchant/reward"==e.cgi?(n=S.IDS.REWARD,r=74):"cgi-bin/appmsgcopyright"==e.cgi||"cgi-bin/imgcopyright"==e.cgi||"cgi-bin/ori_video"==e.cgi?(n=S.IDS.COPYRIGHT,
r=75):"cgi-bin/message"==e.cgi?(n=S.IDS.MSG,r=76):"cgi-bin/user_tag"==e.cgi?(n=S.IDS.USER,
r=77):"cgi-bin/appmsg"==e.cgi&&("list_card"==e.action||"list"==e.action)||"cgi-bin/filepage"==e.cgi?(n=S.IDS.LIST,
r=78):"cgi-bin/operate_voice"==e.cgi?(n=S.IDS.AUDIO,r=79):"cgi-bin/appmsg"==e.cgi&&"video_edit"==e.action?(n=S.IDS.VEDIO,
r=80):""==e.cgi?(n=S.IDS.APPMSG,r=62):"cgi-bin/frame"==e.cgi&&/t=ad_system/.test(e.href)||/merchant\/ad_/.test(e.cgi)?(n=S.IDS.AD,
r=81):"misc/useranalysis"==e.cgi||"misc/appmsganalysis"==e.cgi||"misc/menuanalysis"==e.cgi||"misc/messageanalysis"==e.cgi||"misc/interfaceanalysis"==e.cgi?(n=S.IDS.ANALYSIS,
r=82):"cgi-bin/settingpage"==e.cgi||"acct/contractorinfo"==e.cgi?(n=S.IDS.SETTING,
r=83):"merchant/store"==e.cgi||"merchant/order"==e.cgi||"acct/wxverify"==e.cgi?(n=S.IDS.VERIFY,
r=84):"cgi-bin/safecenterstatus"==e.cgi||""==e.cgi||""==e.cgi?(n=S.IDS.SAFE,r=85):"cgi-bin/illegalrecord"==e.cgi?(n=S.IDS.ILLEGAL,
r=86):"advanced/advanced"==e.cgi||"advanced/diagram"==e.cgi||"cgi-bin/frame"==e.cgi&&/t=advanced\/dev_tools_frame/.test(e.href)?(n=S.IDS.ADVANCED,
r=87):"acct/contractorpage"==e.cgi?(n=S.IDS.REGISTER,r=88):"cgi-bin/readtemplate"==e.cgi?(n=S.IDS.TMPL,
r=89):"advanced/tmplmsg"==e.cgi?(n=S.IDS.TMPLMSG,r=90):"merchant/entityshop"==e.cgi?(n=S.IDS.SHOP,
r=92):(e.cgi="cgi-bin/home")?(n=S.IDS.HOME,r=93):"merchant/cardapply"==e.cgi&&"listapply"==e.action&&(r=95),
t.id=n,t.key=r,{
id:n,
key:r
};
}
function n(e,i){
return/Mozilla\/5.0.*ipad.*BaiduHD/i.test(i)&&e.indexOf("ReferenceError: Can't find variable: bds")>-1?!1:/Linux; U; Android.*letv/i.test(i)&&e.indexOf("ReferenceError: diableNightMode is not defined")>-1?!1:!0;
}
if(e.BJ_REPORT)return e.BJ_REPORT;
var r=[],t={
uin:0,
url:"https://badjs.weixinbridge.com/badjs",
combo:1,
level:4,
ignore:[],
random:1,
delay:1e3,
submit:null
},o=function(e,i){
return Object.prototype.toString.call(e)==="[object "+(i||"Object")+"]";
},c=function(e){
var i=typeof e;
return"object"===i&&!!e;
},a=function(e){
return null===e?!0:o(e,"Number")?!1:!e;
},s=e.onerror;
e.onerror=function(i,r,t,c,a){
var g=i;
a&&a.stack&&(g=d(a)),o(g,"Event")&&(g+=g.type?"--"+g.type+"--"+(g.target?g.target.tagName+"::"+g.target.src:""):""),
r&&r.length>0&&0==/^https\:\/\/(mp\.weixin\.qq\.com|res\.wx\.qq\.com)/.test(r),0!=n(g,window.navigator.userAgent)&&(S.push({
msg:g+"|onerror",
target:r,
rowNum:t,
colNum:c
}),v(),s&&s.apply(e,arguments));
};
var g=function(e){
try{
if(e.stack){
var i=e.stack.match("https?://[^\n]+");
i=i?i[0]:"";
var r=i.match(":(\\d+):(\\d+)");
if(i&&i.lenth>0&&0==/^https?\:\/\/(mp\.weixin\.qq\.com|res\.wx\.qq\.com)/.test(i))return null;
r||(r=[0,0,0]);
var t=d(e).replace(/https?\:\/\/.*?\.js\:/g,"");
return 0==n(t,window.navigator.userAgent)?null:{
msg:t,
rowNum:r[1],
colNum:r[2],
target:i.replace(r[0],"")
};
}
return e;
}catch(o){
return e;
}
},d=function(e){
var i=e.stack.replace(/\n/gi,"").split(/\bat\b/).slice(0,5).join("@").replace(/\?[^:]+/gi,""),n=e.toString();
return i.indexOf(n)<0&&(i=n+"@"+i),i;
},m=function(e,i){
var n=[],r=[],o=[];
if(c(e)){
e.level=e.level||t.level;
for(var s in e){
var g=e[s];
if(!a(g)){
if(c(g))try{
g=JSON.stringify(g);
}catch(d){
g="[BJ_REPORT detect value stringify error] "+d.toString();
}
o.push(s+":"+g),n.push(s+"="+encodeURIComponent(g)),r.push(s+"["+i+"]="+encodeURIComponent(g));
}
}
}
return[r.join("&"),o.join(","),n.join("&")];
},l=[],u=[],p=function(e){
var i=e.replace(/\&_t=\d*/,"");
for(var n in u)if(u[n]==i)return;
if(u.push(i),t.submit)t.submit(e);else{
var r=new Image;
l.push(r),r.src=e;
}
var o=new Image;
if(o.src="https://mp.weixin.qq.com/misc/jslog?id=65&content=badjs&level=error",t.key){
var r=new Image;
r.src="https://mp.weixin.qq.com/misc/jslog?id="+t.key+"&content=badjs&level=error";
}
},f=[],I=0,v=function(e){
if(t.report){
for(;r.length;){
var i=!1,n=r.shift(),c=m(n,f.length);
if(o(t.ignore,"Array"))for(var a=0,s=t.ignore.length;s>a;a++){
var g=t.ignore[a];
if(o(g,"RegExp")&&g.test(c[1])||o(g,"Function")&&g(n,c[1])){
i=!0;
break;
}
}
i||(t.combo?f.push(c[0]):p(t.report+c[2]+"&_t="+ +new Date),t.onReport&&t.onReport(t.id,n));
}
var d=f.length;
if(d){
var l=function(){
clearTimeout(I),p(t.report+f.join("&")+"&count="+d+"&_t="+ +new Date),I=0,f=[];
};
e?l():I||(I=setTimeout(l,t.delay));
}
}
},S={
KEY:67,
IDS:{
DEFAULT:"5",
MASS:"6",
SELF_MENU:"7",
LINK:"11",
AUTO_REPLY:"12",
COMMENT:"13",
VOTE:"14",
KF:"15",
REWARD:"16",
COPYRIGHT:"17",
MSG:"18",
USER:"19",
LIST:"20",
AUDIO:"21",
VEDIO:"22",
APPMSG:"4",
AD:"23",
ANALYSIS:"24",
SETTING:"25",
VERIFY:"26",
SAFE:"27",
ILLEGAL:"28",
ADVANCED:"29",
REGISTER:"30",
TMPL:"31",
IE:"32",
CARD:"33",
SHOP:"34",
TMPLMSG:"35",
HOME:"36",
Android:"37",
IOS:"38"
},
destory:function(){
v=function(){};
},
push:function(e){
if(Math.random()>=t.random)return S;
var i;
return c(e)?(i=g(e),i&&r.push(i)):r.push({
msg:e
}),v(),S;
},
report:function(e){
return e&&S.push(e),v(!0),S;
},
info:function(e){
return e?(c(e)?e.level=2:e={
msg:e,
level:2
},S.push(e),S):S;
},
debug:function(e){
return e?(c(e)?e.level=1:e={
msg:e,
level:1
},S.push(e),S):S;
},
init:function(e){
for(var i in e)t[i]=e[i];
var n=parseInt(t.id,10),i=parseInt(t.key,10);
return window.navigator.userAgent&&/;\s*MSIE\s*[9|8|7]\.0b?;/i.test(window.navigator.userAgent)?(n=S.IDS.IE,
i=S.KEY):window.navigator.userAgent.indexOf("Android")>-1||window.navigator.userAgent.indexOf("Adr")>-1?(n=S.IDS.Android,
i=S.KEY):window.navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)&&(n=S.IDS.IOS,
i=S.KEY),n&&i&&(t.report=t.url+"?id="+n+"&key="+i+"&uin="+(wx&&wx.uin)+"&from="+encodeURIComponent(location.href)+"&"),
S;
},
monitor:function(e,i,n){
if(i=i||"badjs|monitor",e){
var r=new Image;
r.src="https://mp.weixin.qq.com/misc/jslog?id="+e+"&content="+encodeURIComponent(i)+"&level=error";
}
if(n){
var o=new Image;
o.src=t.url+"?id="+n+"&msg="+encodeURIComponent(i)+"&uin="+(wx&&wx.uin)+"&from="+encodeURIComponent(location.href)+"&level=4";
}
},
getConfig:function(){
return t;
},
__onerror__:e.onerror
};
return"undefined"!=typeof console&&console.error&&setTimeout(function(){
var e=((location.hash||"").match(/([#&])BJ_ERROR=([^&$]+)/)||[])[2];
e&&console.error("BJ_ERROR",decodeURIComponent(e).replace(/(:\d+:\d+)\s*/g,"$1\n"));
},0),t.id=S.IDS.DEFAULT,t.key=S.KEY,i(),S.init(),S;
}(window);
window.wx_loaderror=function(e){
var i=new Image,n=63;
e&&e.tagName&&"script"==e.tagName.toLowerCase()&&(n=64,BJ_REPORT.destory()),i.src="https://badjs.weixinbridge.com/badjs?id="+BJ_REPORT.IDS.LINK+"&uin="+(wx&&wx.uin)+"&msg="+encodeURIComponent("link_error:"+(e&&(e.href||e.src)))+"|link&from="+encodeURIComponent(location.href)+"&level=4&_t="+ +new Date;
var r=new Image;
r.src="https://mp.weixin.qq.com/misc/jslog?id="+n+"&content=link_error&level=error";
};