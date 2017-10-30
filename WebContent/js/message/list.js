define("message/list.js",["common/wx/top.js","common/qq/mask.js","common/wx/Tips.js","common/wx/pagebar.js","biz_web/ui/dropdown.js","message/message_cgi.js","message/renderList.js","common/wx/searchInput.js"],function(e){
"use strict";
var s=wx.cgiData,t=s.latest_msg_id,n=s.action,i=s.day,a=s.filterivrmsg||"1",o=e("common/wx/top.js"),r=(e("common/qq/mask.js"),
e("common/wx/Tips.js")),c=e("common/wx/pagebar.js"),l=e("biz_web/ui/dropdown.js"),m=e("message/message_cgi.js");
!function(){
function e(e,s){
var t=-1;
return $.each(e,function(e,n){
return n.value==s?(t=e,!1):void 0;
}),t;
}
var t="#topTab",r=new o(t,o.DATA.message,{
render:o.RENDER.message,
data:{
action:n
}
});
$.each($(t).find("a"),function(){
$(this).attr("href",$(this).attr("href")+"&filterivrmsg="+a);
});
var c,m=0,u="",g={
7:["全部消息",0,"文字消息保存5天，其它类型消息只保存3天"],
0:["今天的消息",1],
1:["昨天的消息",2],
2:["前天的消息",3],
3:["更早",4]
},d=[{
name:"最近五天",
value:"7"
},{
name:"今天",
value:"0"
},{
name:"昨天",
value:"1"
},{
name:"前天",
value:"2"
},{
name:"更早",
value:"3"
}],f="#page_title",h="#dayselect",w="/cgi-bin/message?t=message/list&count=20&day=";
if("search"!=n&&"star"!=n){
new l({
container:h,
label:"最近五天",
data:d,
callback:function(e){
location.href=wx.url(w+e+"&filterivrmsg="+a);
}
}).selected(e(d,i),!1);
}
if("search"==n)u="搜索结果",m=2,$("#searchMore").show();else if("star"==n)u="星标消息",m=1,
c=c||"%s条";else{
var p=g[i];
u=p[0],m=p[1],c=p[2],c=c||"%s条","0"==a&&$(f).siblings("label").removeClass("selected"),
$(f).siblings("label").show();
}
$(f).html(u+(c?"<span>(%s)</span>".sprintf(c.sprintf(s.total_count)):"")),console.log("selected_menu="+m),
r.selected(m);
}(),function(){
function e(e){
return 6*i>e&&(e+=i),e;
}
var s={},n=function(e,t){
e=e||s.title||"",t=t||s.time||500,s.timer&&clearTimeout(s.timer),s.timer=t,document.title=s.title=e.substring(1,e.length)+e.substring(0,1),
s.timer=setTimeout(n,t);
},i=1e4,a=i,o=0,r=function(){
t&&m.getNewMsgCount(t,function(s){
if(s&&s.base_resp&&0==s.base_resp.ret){
var t=s.newTotalMsgCount;
t>0?(n("收到%s条新消息...".sprintf(t)),$("#newMsgTip").fadeIn(),$("#newMsgNum").html(t),
a=t==o?e(a):i,o=t):(a=e(a),$("#newMsgTip").hide()),setTimeout(r,a);
}
},function(s){
s&&"-20000"==s.ret||(a=e(a),setTimeout(r,a));
});
};
setTimeout(r,a);
}(),function(s){
var t=s.list,i=null,a=e("message/renderList.js");
if(i=s.frommsgid||t[0]&&t[0].id,"search"!=n){
{
var o=s.offset/s.count+1;
new c({
container:".pageNavigator",
perPage:s.count,
first:!1,
last:!1,
isSimple:!0,
initShowPage:o,
totalItemsNum:s.total_count,
callback:function(e){
var t=e.currentPage;
if(t!=o)return t--,location.href=wx.url("/cgi-bin/message?t=message/list&action=%s&keyword=%s&frommsgid=%s&offset=%s&count=%s&day=%s&filterivrmsg=%s".sprintf(s.action,encodeURIComponent(s.keyword),i,t*s.count,s.count,s.day,s.filterivrmsg)),
!1;
}
});
}
$("#page_title").siblings("label").on("click",function(){
var e=$(this),t=e.hasClass("selected")?"0":"1",n=wx.url("/cgi-bin/message?t=message/list&action=%s&keyword=%s&count=%s&day=%s&filterivrmsg=%s".sprintf(s.action,encodeURIComponent(s.keyword),s.count,s.day,t));
return e.toggleClass("selected"),location.href=n,!1;
});
}else{
var r=t.length,l=i;
r<s.count&&$("#searchMore").hide(),$("#searchMore").click(function(){
$(this).addClass("show_loading"),m.pageNav({
action:"search",
count:s.count,
keyword:s.keyword,
frommsgid:l
},function(e){
a({
container:"#listContainer",
list:e,
action:n
}),$("#searchMore").removeClass("show_loading"),e.length<s.count?$("#searchMore").hide():l=e[e.length-1].id;
});
});
}
$("#listContainer").html(""),a({
container:"#listContainer",
list:t,
action:n
});
}(s),function(){
var t=e("common/wx/searchInput.js");
new t({
id:"#searchBar",
value:s.keyword.html(!1).html(!1),
placeholder:"消息内容",
click:function(e){
e.length>0?location.href=wx.url("/cgi-bin/message?t=message/list&action=search&keyword=%s&count=%s".sprintf(encodeURIComponent(e),s.count)):(r.err("请输入搜索关键词"),
$("#searchBar").find(".searchInput").focus());
}
}),s.keyword&&$(".pageNavigator").hide();
}();
});