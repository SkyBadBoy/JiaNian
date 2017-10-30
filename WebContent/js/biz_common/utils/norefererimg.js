!function(){
if(!window.define){
var t={};
window.define=function(e,i,n){
t[e]=n;
},window.seajs={
use:function(e){
function i(e){
return t[e]&&t[e](i);
}
return t[e]&&t[e](i);
}
};
}
}(),define("biz_common/utils/norefererimg.js",[],function(){
function t(t){
return window.getComputedStyle?window.getComputedStyle(t):t.currentStyle;
}
function e(t,e,i,n){
if(t&&i){
if(t==window&&"load"==e&&/complete|loaded/.test(document.readyState))return void i({
type:"load"
});
var o=function(t){
var e=i.call(this,t);
e===!1&&(t.stopPropagation&&t.stopPropagation(),t.preventDefault&&t.preventDefault());
};
return i[e+"_handler"]=o,t.addEventListener?void t.addEventListener(e,o,!!n):t.attachEvent?void t.attachEvent("on"+e,o,!!n):void 0;
}
}
return function(i){
var n=i.img,o=i.imgurl,r=i.onload,d=o||n.getAttribute("data-src"),a=/^http:\/\/mmbiz\.qpic\.cn\/|https:\/\/mmbiz\.qlogo\.cn\//;
if(d){
if(n.length&&(n=n[0]),a.test(d))return e(n,"load",r),void n.setAttribute("src",d);
var s=t(n),l=["javascript:\"<html><body style='margin:0;padding:0;'><img style='width:",s.width,";height:",s.height,";' src='",d+"' /></body></html>\""].join(" "),u=document.createElement("iframe");
u.setAttribute("frameBorder","0"),u.setAttribute("scrolling","no"),u.style.width="60px",
u.style.width=s.width+"",u.style.height=s.height,u.style.display=s.display,u.style.borderRadius=s.borderRadius,
u.style.webkitBorderRadius=s.borderRadius,"function"==typeof r&&(u.attachEvent?u.attachEvent("onload",r):u.onload=r),
u.className=n.className;
var c=n.parentNode;
c.insertBefore(u,n),u.src=l,c.removeChild(n);
}
};
});