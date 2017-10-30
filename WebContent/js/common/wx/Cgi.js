define("common/wx/Cgi.js", [ "common/qq/mask.js", "common/wx/dialog.js", "common/wx/Tips.js", "common/wx/cgiReport.js", "common/lib/MockJax.js", "common/qq/events.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = e("common/qq/mask.js"), s = e("common/wx/dialog.js"), o = e("common/wx/Tips.js"), u = e("common/wx/cgiReport.js");
e("common/lib/MockJax.js");
var a = e("common/qq/events.js")(!0), f = {
dataType: "json",
mask: !1,
timeout: 45e3,
error: $.noop,
mock: !1,
data: {
token: wx.data.t,
lang: wx.data.lang,
f: "json",
ajax: "1"
}
};
t.get = function(e, t) {
return l("get", e, t);
}, t.post = function(e, t) {
return l("post", e, t);
};
function l(e, t, n) {
var r, s;
n && typeof n == "object" ? (r = n.done, s = n.fail) : r = n, typeof t == "string" && t.length > 0 && (t = {
url: t
}), t = $.extend(!0, {}, f, {
type: e,
data: {
random: Math.random().toString()
}
}, t), t.mock && (t.mock === !0 ? t.mock = {
responseText: {
ret: 0,
data: {},
url: t.url,
param: t.data
}
} : t.mock && !t.mock.responseText && !t.mock.response && (t.mock = {
responseText: t.mock
}), t.mock.url = t.mock.url || t.url, $.mockjax(t.mock)), !t.mask || ($.isPlainObject(t.mask) ? i.show(t.mask) : i.show());
var o = $.ajax(t);
return o.callback = o.done, o.done(function(e) {
r && r(e);
}).fail(function(e, n, r) {
s && s(n), u.error(n, t), a.trigger("xhrError", e, n, r, t);
}).always(function() {
t.mask && i.hide();
}), o;
}
var c = {
"0": "恭喜你，操作成功！",
"-1": "系统错误，请稍后尝试。",
"-2": "参数错误，请核对参数后重试。",
"-3": "登录态超时，请重新登录。",
"-4": "请求页面的域名没有授权。",
"-5": "请求方式错误，请确认请求方式后重试。",
"-6": "表单名称验证出错，请核对表单名称后重试。",
"-7": "对不起，你没有权限访问目标请求。"
};
t.show = function(e, t) {
var n = c[e.base_resp.ret] || "系统繁忙，请稍后尝试！";
if (e.base_resp.ret == 0) {
t ? s.show({
type: "succ",
msg: "系统提示|" + n
}) : o.suc(n);
return;
}
t ? s.show("系统错误|" + n) : o.err(n);
};
} catch (h) {
wx.jslog({
src: "common/wx/Cgi.js"
}, h);
}
});