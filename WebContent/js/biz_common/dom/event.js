define("biz_common/dom/event.js",[],function(){
function t(t,e,n,a){
r.isPc||r.isWp?o(t,"click",a,e,n):o(t,"touchend",a,function(t){
var n=t.changedTouches[0];
return Math.abs(r.y-n.clientY)<=5&&Math.abs(r.x-n.clientX)<=5?e.call(this,t):void 0;
},n);
}
function e(t,e){
if(!t||!e||t.nodeType!=t.ELEMENT_NODE)return!1;
var n=t.webkitMatchesSelector||t.msMatchesSelector||t.matchesSelector;
return n?n.call(t,e):(e=e.substr(1),t.className.indexOf(e)>-1);
}
function n(t,n,o){
for(;t&&!e(t,n);)t=t!==o&&t.nodeType!==t.DOCUMENT_NODE&&t.parentNode;
return t;
}
function o(e,o,a,i,c){
var d,s,u;
return"input"==o&&r.isPc,e?("function"==typeof a&&(c=i,i=a,a=""),"string"!=typeof a&&(a=""),
e==window&&"load"==o&&/complete|loaded/.test(document.readyState)?i({
type:"load"
}):"tap"==o?t(e,i,c,a):(d=function(t){
var e=i(t);
return e===!1&&(t.stopPropagation&&t.stopPropagation(),t.preventDefault&&t.preventDefault()),
e;
},a&&"."==a.charAt(0)&&(u=function(t){
var o=t.target||t.srcElement,i=n(o,a,e);
return i?(t.delegatedTarget=i,d(t)):void 0;
}),s=u||d,i[o+"_handler"]=s,e.addEventListener?void e.addEventListener(o,s,!!c):e.attachEvent?void e.attachEvent("on"+o,s,!!c):void 0)):void 0;
}
function a(t,e,n,o){
if(t){
var a=n[e+"_handler"]||n;
return t.removeEventListener?void t.removeEventListener(e,a,!!o):t.detachEvent?void t.detachEvent("on"+e,a,!!o):void 0;
}
}
var i=navigator.userAgent,r={
isPc:/(WindowsNT)|(Windows NT)|(Macintosh)/i.test(navigator.userAgent),
isWp:/Windows\sPhone/i.test(i)
};
return r.isPc||o(document,"touchstart",function(t){
var e=t.changedTouches[0];
r.x=e.clientX,r.y=e.clientY;
}),{
on:o,
off:a,
tap:t
};
});