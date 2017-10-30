define("infringement/manual.js",["common/wx/top.js","common/wx/dialog.js"],function(n){
"use strict";
var e=n("common/wx/top.js"),t=n("common/wx/dialog.js");
1==wx.cgiData.type?new e("#topTab",e.DATA.infringement).selected("infringement"):2==wx.cgiData.type&&new e("#topTab",e.DATA.infringement).selected("antiinfringement");
var i=wx.url("/acct/infringement?action=getinfo&t=infringement/infringement_add&type="+wx.cgiData.type);
wx.cgiData.entrance_source&&(i+="&entrance_source="+wx.cgiData.entrance_source),
$("#js_apply").on("click",function(){
$(this).data("type");
1==wx.cgiData.exist?t.show({
type:"warn",
msg:"您有未完成的侵权投诉单，继续完成？|点击重新填写，则清空已填内容，申请新的投诉单。",
buttons:[{
text:"继续",
click:function(){
window.open(wx.url("/acct/infringement?action=getinfo&t=infringement/infringement_add&refill=1&type="+wx.cgiData.tempType)),
this.remove();
},
type:"primary"
},{
text:"重新填写",
click:function(){
window.open(i),this.remove();
},
type:"normal"
}],
title:"未完成投诉单"
}):window.open(i);
});
});