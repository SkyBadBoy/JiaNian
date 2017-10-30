define("infringement/list.js", [ "common/wx/top.js", "common/wx/Cgi.js", "common/wx/Tips.js", "common/wx/popover.js", "common/wx/dialog.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = e("common/wx/top.js"), s = e("common/wx/Cgi.js"), o = e("common/wx/Tips.js"), u = e("common/wx/popover.js"), a = e("common/wx/dialog.js"), f = template.render;
(new i("#topTab", i.DATA.infringement)).selected("list"), template.helper("datestring", function(e) {
var t = new Date(e), n = "%s-%s-%s".sprintf(t.getFullYear(), t.getMonth() + 1, t.getDate());
return n;
}), template.helper("bizlist", function(e) {
var t = [];
return $.each(e.data, function(e, n) {
t.push(n.nickname);
}), t.join(", ");
}), $("#js_list").html(f("tpl_list", wx.cgiData.list)), $("body").on("click", "#js_del", function() {
var e = $(this);
new u({
dom: this,
content: "确定删除该投诉单吗？删除后不可恢复",
buttons: [ {
text: "确定",
type: "primary",
click: function() {
var t = this, n = t.$pop.find(".jsPopoverBt").eq(0);
if (n.hasClass("btn_disabled")) return;
n.disable(), s.post({
url: "/acct/infringement",
data: {
action: "deltemp"
},
mask: !1
}, function(r) {
n.enable(), r && r.base_resp && r.base_resp.ret == 0 ? (o.suc("删除成功"), t.remove(), e.closest("tr").remove()) : o.err("删除失败，请重试");
});
}
}, {
text: "取消",
click: function() {
this.remove();
}
} ]
});
});
var l = wx.cgiData.list.temp_info;
$("#js_apply").on("click", function() {
var e = $(this).data("type");
l && l.exist == 1 ? a.show({
type: "warn",
msg: "您有未完成的侵权投诉单，继续完成？|点击重新填写，则清空已填内容，申请新的投诉单。",
buttons: [ {
text: "继续",
click: function() {
window.open(wx.url("/acct/infringement?action=getinfo&t=infringement/infringement_add&refill=1&type=" + l.type)), this.remove();
},
type: "primary"
}, {
text: "重新填写",
click: function() {
window.open(wx.url("/acct/infringement?action=getinfo&t=infringement/infringement_add&type=" + e)), this.remove();
},
type: "normal"
} ],
title: "未完成投诉单"
}) : window.open(wx.url("/acct/infringement?action=getinfo&t=infringement/infringement_add&type=" + e));
});
} catch (c) {
wx.jslog({
src: "infringement/list.js"
}, c);
}
});