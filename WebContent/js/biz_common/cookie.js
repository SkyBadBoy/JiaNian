define("biz_common/cookie.js",[],function(){
var e={};
return e.set=function(e,n,t,o){
t=t||30;
var i=new Date;
if(i.setTime(i.getTime()+24*t*60*60*1e3),o){
var c=[];
$.each(o,function(e,n){
c.push(";"+e+"="+n);
}),o=c.join("");
}else o="";
document.cookie=e+"="+escape(n)+";expires="+i.toGMTString()+o;
},e.get=function(e){
var n=new RegExp(["(^|;|\\s+)",e.replace(/([\^\.\[\$\(\)\|\*\+\?\{\\])/gi,"\\$1"),"=([^;]*);?"].join(""));
if(n.test(document.cookie))try{
return decodeURIComponent(RegExp.$2);
}catch(t){
return RegExp.$2;
}
},e;
});