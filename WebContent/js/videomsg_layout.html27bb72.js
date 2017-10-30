define("media/appmsg_temp_url.js",["common/wx/Cgi.js","common/wx/Tips.js"],function(t){
"use strict";
var i=t("common/wx/Cgi.js"),e=t("common/wx/Tips.js");
return function(t,n){
$(t).on("click",n,function(t){
if(!$(this).attr("href")&&!$(this).find("a").attr("href")){
t.preventDefault();
var n=window.open();
i.get({
url:"/cgi-bin/appmsg?action=get_temp_url",
data:{
appmsgid:$(this).data("msgid"),
itemidx:$(this).data("idx")+1
}
},function(t){
t.base_resp&&0==t.base_resp.ret?n.location.href=t.temp_url:(e.err("生成临时链接失败，请重试"),
n.close());
});
}
});
};
});define("tpl/media/videomsg.html.js",[],function(){
return'<div id="wxVideoBox{id}" class="richvideo Js_videomsg">\n    <div class="richvideo_content" style="z-index: 0">\n        <h4 class="title">{title}</h4>\n        <div class="video_info">\n            <em class="time">{time}</em>\n            <!--#0001#-->\n            <em class="res">{from}</em>\n            <!--%0001%-->\n        </div>\n        <div class="video_wrp Js_videoContent">\n            <div id="wxVideoPlayer{id}" class="wxVideoPlayContent video_player">\n                <video id="wxVideo{id}" class="video-js vjs-default-skin"  \n                    preload="auto" controls="controls" data-src="{video_url}"></video>\n            </div>\n            {if for_network}\n            <div class="wxNetworkVideo video_shot" data-contenturl="{content_url}">\n            {else}\n            <div class="{if !for_transfer}wxVideoScreenshot {/if}video_shot">\n            {/if}\n                <!--#0002#-->\n                {if img_url}\n                    <img src="{img_url}"/>\n                {else}\n                    <img src="/cgi-bin/getimgdata?token={token}&msgid={app_id}&mode=large&source=file&fileId={file_id}"/>\n                {/if}\n                <!--%0002%-->\n                <!-- <i class="icon_video"></i> -->\n                <!-- <span class="video_duration"><em>{play_length}"</em></span> -->\n                {if for_transfer}\n                <div class="loading_tips" {if hide_transfer}style="display:none"{/if}>\n                    <i class="icon32_loading dark"></i>\n                    <p>转码中</p>\n                </div>\n                {/if}\n            </div>\n        </div>\n        <div class="video_desc" data-digest="{digest}">{digest}</div>\n    </div>\n    {if for_operation}\n    <div class="richvideo_opr">\n        <ul class="grid_line">\n            {if for_network}\n            <li class="richvideo_opr_item grid_item size1of2">\n                <a class="js_edit js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of2 no_extra">\n                <a class="js_del js_tooltip" data-id="{id}" href="javascript:void(0);" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {else}\n            <li class="richvideo_opr_item grid_item size1of3">\n                <a class="js_edit js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of3">\n                <a {if for_transfer}href="javascript:;" class="js_tooltip js_download"{else}href="{video_download_url}" class="js_tooltip"{/if} data-tooltip="下载">\n                    <i class="icon18_common download_gray">下载</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of3 no_extra">\n                <a class="js_del js_tooltip" data-id="{app_id}" href="javascript:void(0);" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {/if}\n        </ul>\n    </div>\n    {/if}\n    {if for_selection}\n    <div class="richvideo_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n    <div class="richvideo_tips">\n        <i class="icon_richvideo_error"></i>\n        <p>该素材没有标题，<a href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}">马上编辑</a></p>\n    </div>\n    {/if}\n    {if for_notitle}\n    <div class="richvideo_mask"></div>\n    <div class="richvideo_tips">\n        <i class="icon_richvideo_error"></i>\n        <p>该素材没有标题，<a href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}">马上编辑</a></p>\n    </div>\n    {/if}\n</div>\n\n\n';
});define("tpl/media/wxvideo.html.js",[],function(){
return'<div id="wxVideoBox{id}" class="richvideo smallvideo Js_videomsg">\n	<div class="richvideo_content" style="z-index: 0">\n		<h4 class="title">{name}</h4>\n        <div class="video_wrp Js_videoContent">\n            <div id="wxVideoPlayer{id}" class="wxVideoPlayContent video_player">\n                <video id="wxVideo{id}" class="video-js vjs-default-skin"  \n                    preload="auto" controls="controls" data-src="{video_url}"></video>\n            </div>\n			<div class="wxVideoScreenshot video_shot">\n                {if video_thumb_cdn_url}\n                <img src="{video_thumb_cdn_url}">\n                {else}\n				<img src="/cgi-bin/getimgdata?token={token}&msgid={id}&mode=small&source=file&fileId={file_id}">\n                {/if}\n				<div class="video_mask">\n					<span class="ic_play"></span>\n				</div>\n			</div>\n        </div>\n	</div>\n    {if for_operation}\n    <div class="richvideo_opr">\n        <ul class="grid_line">\n            <li class="richvideo_opr_item grid_item size1of2">\n                <a class="js_popedit js_tooltip" data-id="{id}" data-name="{name}" href="javascript:void(0);" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of2 no_extra">\n                <a class="js_del js_tooltip" data-id="{id}" data-type="sv" href="javascript:void(0);" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n        </ul>\n    </div>\n    {/if}\n    {if for_selection}\n    <div class="richvideo_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n    {/if}\n</div>\n\n\n';
});define("tpl/media/simple_videomsg.html.js",[],function(){
return'<!--群发功能-已发送页面视频模板-->\n<div class="appmsgSendedItem simple_videomsg" data-id="{id}" data-src="{video_url}">\n    {if for_network}\n    <a href="{content_url}" class="title_wrp" data-contenturl="{content_url}" target="_blank">\n    {else}\n    <a href="javascript:;" class="title_wrp js_video">\n    {/if}\n        <!-- <img class="icon icon_lh" src="/cgi-bin/getimgdata?token={token}&msgid={app_id}&mode=large&source=file&fileId={file_id}"/> -->\n        <span class="icon icon_lh cover" style="background-image:url("/cgi-bin/getimgdata?token={token}&msgid={app_id}&mode=large&source=file&fileId={file_id}");"></span>\n        <span class="title">[视频]{title}</span>\n    </a>\n    <p class="desc">{if for_transfer}{if !hide_transfer}转码中{/if}{/if} {digest}</p>\n</div>\n';
});define("tpl/media/video.html.js",[],function(){
return'<div id="wxVideoBox{id}" class="mediaBox videoBox{if type == 62} smallvideo_box{/if}">\n	<div class="mediaContent">\n		<div class="wxVideoPlayContent">\n            <div class="wxVideoBoxAction{id}">\n                <a id="wxVideoBoxFold{id}" class="video_switch"><i class="icon14_common switch_gray"></i>收起</a>\n			</div>\n            <div id="wxVideoPlayer{id}" class="wxVideoPlayer">\n                <video id="wxVideo{id}" class="video-js vjs-default-skin" width="260" height="195" preload="auto"  loop controls="controls" src="{src}" poster="/cgi-bin/getimgdata?token={token}&msgid={id}&mode=small&source={source}&fileId={file_id}"></video>\n            </div>\n		</div>\n        <div class="wxVideoScreenshot" data-vid="{id}" data-fid="{fileid}" data-source="{source}">\n            {if video_thumb_url}\n            <img class="wxImg" src="{video_thumb_url}">\n            {else}\n            <img class="wxImg" src="/cgi-bin/getimgdata?token={token}&msgid={id}&mode=small&source={source}&fileId={file_id}" alt="" title=\'点击播放视频\' />\n            {/if}\n			<span class="iconVideo" title=\'点击播放视频\'></span>\n            <div class="videoDuration"><em>{play_length}"</em></div>\n		</div>\n    </div>\n</div>\n';
});define("biz_web/lib/swfobject.js", [], function(e, t, n) {
try {
var r = +(new Date), i = function() {
function e() {
if (U) return;
try {
var e = M.getElementsByTagName("body")[0].appendChild(g("span"));
e.parentNode.removeChild(e);
} catch (t) {
return;
}
U = !0;
var n = P.length;
for (var r = 0; r < n; r++) P[r]();
}
function t(e) {
U ? e() : P[P.length] = e;
}
function n(e) {
if (typeof O.addEventListener != x) O.addEventListener("load", e, !1); else if (typeof M.addEventListener != x) M.addEventListener("load", e, !1); else if (typeof O.attachEvent != x) y(O, "onload", e); else if (typeof O.onload == "function") {
var t = O.onload;
O.onload = function() {
t(), e();
};
} else O.onload = e;
}
function r() {
D ? s() : o();
}
function s() {
var e = M.getElementsByTagName("body")[0], t = g(T);
t.setAttribute("type", k);
var n = e.appendChild(t);
if (n) {
var r = 0;
(function() {
if (typeof n.GetVariable != x) {
var i = n.GetVariable("$version");
i && (i = i.split(" ")[1].split(","), $.pv = [ parseInt(i[0], 10), parseInt(i[1], 10), parseInt(i[2], 10) ]);
} else if (r < 10) {
r++, setTimeout(arguments.callee, 10);
return;
}
e.removeChild(t), n = null, o();
})();
} else o();
}
function o() {
var e = H.length;
if (e > 0) for (var t = 0; t < e; t++) {
var n = H[t].id, r = H[t].callbackFn, i = {
success: !1,
id: n
};
if ($.pv[0] > 0) {
var s = m(n);
if (s) if (b(H[t].swfVersion) && !($.wk && $.wk < 312)) E(n, !0), r && (i.success = !0, i.ref = u(n), r(i)); else if (H[t].expressInstall && a()) {
var o = {};
o.data = H[t].expressInstall, o.width = s.getAttribute("width") || "0", o.height = s.getAttribute("height") || "0", s.getAttribute("class") && (o.styleclass = s.getAttribute("class")), s.getAttribute("align") && (o.align = s.getAttribute("align"));
var c = {}, h = s.getElementsByTagName("param"), p = h.length;
for (var d = 0; d < p; d++) h[d].getAttribute("name").toLowerCase() != "movie" && (c[h[d].getAttribute("name")] = h[d].getAttribute("value"));
f(o, c, n, r);
} else l(s), r && r(i);
} else {
E(n, !0);
if (r) {
var v = u(n);
v && typeof v.SetVariable != x && (i.success = !0, i.ref = v), r(i);
}
}
}
}
function u(e) {
var t = null, n = m(e);
if (n && n.nodeName == "OBJECT") if (typeof n.SetVariable != x) t = n; else {
var r = n.getElementsByTagName(T)[0];
r && (t = r);
}
return t;
}
function a() {
return !z && b("6.0.65") && ($.win || $.mac) && !($.wk && $.wk < 312);
}
function f(e, t, n, r) {
z = !0, q = r || null, R = {
success: !1,
id: n
};
var i = m(n);
if (i) {
i.nodeName == "OBJECT" ? (F = c(i), I = null) : (F = i, I = n), e.id = L;
if (typeof e.width == x || !/%$/.test(e.width) && parseInt(e.width, 10) < 310) e.width = "310";
if (typeof e.height == x || !/%$/.test(e.height) && parseInt(e.height, 10) < 137) e.height = "137";
M.title = M.title.slice(0, 47) + " - Flash Player Installation";
var s = $.ie && $.win ? "ActiveX" : "PlugIn", o = "MMredirectURL=" + O.location.toString().replace(/&/g, "%26") + "&MMplayerType=" + s + "&MMdoctitle=" + M.title;
typeof t.flashvars != x ? t.flashvars += "&" + o : t.flashvars = o;
if ($.ie && $.win && i.readyState != 4) {
var u = g("div");
n += "SWFObjectNew", u.setAttribute("id", n), i.parentNode.insertBefore(u, i), i.style.display = "none", function() {
i.readyState == 4 ? i.parentNode.removeChild(i) : setTimeout(arguments.callee, 10);
}();
}
h(e, t, n);
}
}
function l(e) {
if ($.ie && $.win && e.readyState != 4) {
var t = g("div");
e.parentNode.insertBefore(t, e), t.parentNode.replaceChild(c(e), t), e.style.display = "none", function() {
e.readyState == 4 ? e.parentNode.removeChild(e) : setTimeout(arguments.callee, 10);
}();
} else e.parentNode.replaceChild(c(e), e);
}
function c(e) {
var t = g("div");
if ($.win && $.ie) t.innerHTML = e.innerHTML; else {
var n = e.getElementsByTagName(T)[0];
if (n) {
var r = n.childNodes;
if (r) {
var i = r.length;
for (var s = 0; s < i; s++) (r[s].nodeType != 1 || r[s].nodeName != "PARAM") && r[s].nodeType != 8 && t.appendChild(r[s].cloneNode(!0));
}
}
}
return t;
}
function h(e, t, n) {
var r, i = m(n);
if ($.wk && $.wk < 312) return r;
if (i) {
typeof e.id == x && (e.id = n);
if ($.ie && $.win) {
var s = "";
for (var o in e) e[o] != Object.prototype[o] && (o.toLowerCase() == "data" ? t.movie = e[o] : o.toLowerCase() == "styleclass" ? s += ' class="' + e[o] + '"' : o.toLowerCase() != "classid" && (s += " " + o + '="' + e[o] + '"'));
var u = "";
for (var a in t) t[a] != Object.prototype[a] && (u += '<param name="' + a + '" value="' + t[a] + '" />');
i.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + s + ">" + u + "</object>", B[B.length] = e.id, r = m(e.id);
} else {
var f = g(T);
f.setAttribute("type", k);
for (var l in e) e[l] != Object.prototype[l] && (l.toLowerCase() == "styleclass" ? f.setAttribute("class", e[l]) : l.toLowerCase() != "classid" && f.setAttribute(l, e[l]));
for (var c in t) t[c] != Object.prototype[c] && c.toLowerCase() != "movie" && p(f, c, t[c]);
i.parentNode.replaceChild(f, i), r = f;
}
}
return r;
}
function p(e, t, n) {
var r = g("param");
r.setAttribute("name", t), r.setAttribute("value", n), e.appendChild(r);
}
function d(e) {
var t = m(e);
t && t.nodeName == "OBJECT" && ($.ie && $.win ? (t.style.display = "none", function() {
t.readyState == 4 ? v(e) : setTimeout(arguments.callee, 10);
}()) : t.parentNode.removeChild(t));
}
function v(e) {
var t = m(e);
if (t) {
for (var n in t) typeof t[n] == "function" && (t[n] = null);
t.parentNode.removeChild(t);
}
}
function m(e) {
var t = null;
try {
t = M.getElementById(e);
} catch (n) {}
return t;
}
function g(e) {
return M.createElement(e);
}
function y(e, t, n) {
e.attachEvent(t, n), j[j.length] = [ e, t, n ];
}
function b(e) {
var t = $.pv, n = e.split(".");
return n[0] = parseInt(n[0], 10), n[1] = parseInt(n[1], 10) || 0, n[2] = parseInt(n[2], 10) || 0, t[0] > n[0] || t[0] == n[0] && t[1] > n[1] || t[0] == n[0] && t[1] == n[1] && t[2] >= n[2] ? !0 : !1;
}
function w(e, t, n, r) {
if ($.ie && $.mac) return;
var i = M.getElementsByTagName("head")[0];
if (!i) return;
var s = n && typeof n == "string" ? n : "screen";
r && (W = null, X = null);
if (!W || X != s) {
var o = g("style");
o.setAttribute("type", "text/css"), o.setAttribute("media", s), W = i.appendChild(o), $.ie && $.win && typeof M.styleSheets != x && M.styleSheets.length > 0 && (W = M.styleSheets[M.styleSheets.length - 1]), X = s;
}
$.ie && $.win ? W && typeof W.addRule == T && W.addRule(e, t) : W && typeof M.createTextNode != x && W.appendChild(M.createTextNode(e + " {" + t + "}"));
}
function E(e, t) {
if (!V) return;
var n = t ? "visible" : "hidden";
U && m(e) ? m(e).style.visibility = n : w("#" + e, "visibility:" + n);
}
function S(e) {
var t = /[\\\"<>\.;]/, n = t.exec(e) != null;
return n && typeof encodeURIComponent != x ? encodeURIComponent(e) : e;
}
var x = "undefined", T = "object", N = "Shockwave Flash", C = "ShockwaveFlash.ShockwaveFlash", k = "application/x-shockwave-flash", L = "SWFObjectExprInst", A = "onreadystatechange", O = window, M = document, _ = navigator, D = !1, P = [ r ], H = [], B = [], j = [], F, I, q, R, U = !1, z = !1, W, X, V = !0, $ = function() {
var e = typeof M.getElementById != x && typeof M.getElementsByTagName != x && typeof M.createElement != x, t = _.userAgent.toLowerCase(), n = _.platform.toLowerCase(), r = n ? /win/.test(n) : /win/.test(t), i = n ? /mac/.test(n) : /mac/.test(t), s = /webkit/.test(t) ? parseFloat(t.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : !1, o = !1, u = [ 0, 0, 0 ], a = null;
if (typeof _.plugins != x && typeof _.plugins[N] == T) a = _.plugins[N].description, a && (typeof _.mimeTypes == x || !_.mimeTypes[k] || !!_.mimeTypes[k].enabledPlugin) && (D = !0, o = !1, a = a.replace(/^.*\s+(\S+\s+\S+$)/, "$1"), u[0] = parseInt(a.replace(/^(.*)\..*$/, "$1"), 10), u[1] = parseInt(a.replace(/^.*\.(.*)\s.*$/, "$1"), 10), u[2] = /[a-zA-Z]/.test(a) ? parseInt(a.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0); else if (typeof O.ActiveXObject != x) try {
var f = new ActiveXObject(C);
f && (a = f.GetVariable("$version"), a && (o = !0, a = a.split(" ")[1].split(","), u = [ parseInt(a[0], 10), parseInt(a[1], 10), parseInt(a[2], 10) ]));
} catch (l) {}
return {
w3: e,
pv: u,
wk: s,
ie: o,
win: r,
mac: i
};
}(), J = function() {
if (!$.w3) return;
(typeof M.readyState != x && M.readyState == "complete" || typeof M.readyState == x && (M.getElementsByTagName("body")[0] || M.body)) && e(), U || (typeof M.addEventListener != x && M.addEventListener("DOMContentLoaded", e, !1), $.ie && $.win && (M.attachEvent(A, function() {
M.readyState == "complete" && (M.detachEvent(A, arguments.callee), e());
}), O == top && function() {
if (U) return;
try {
M.documentElement.doScroll("left");
} catch (t) {
setTimeout(arguments.callee, 0);
return;
}
e();
}()), $.wk && function() {
if (U) return;
if (!/loaded|complete/.test(M.readyState)) {
setTimeout(arguments.callee, 0);
return;
}
e();
}(), n(e));
}(), K = function() {
$.ie && $.win && window.attachEvent("onunload", function() {
var e = j.length;
for (var t = 0; t < e; t++) j[t][0].detachEvent(j[t][1], j[t][2]);
var n = B.length;
for (var r = 0; r < n; r++) d(B[r]);
for (var s in $) $[s] = null;
$ = null;
for (var o in i) i[o] = null;
i = null;
});
}();
return {
registerObject: function(e, t, n, r) {
if ($.w3 && e && t) {
var i = {};
i.id = e, i.swfVersion = t, i.expressInstall = n, i.callbackFn = r, H[H.length] = i, E(e, !1);
} else r && r({
success: !1,
id: e
});
},
getObjectById: function(e) {
if ($.w3) return u(e);
},
embedSWF: function(e, n, r, i, s, o, u, l, c, p) {
var d = {
success: !1,
id: n
};
$.w3 && !($.wk && $.wk < 312) && e && n && r && i && s ? (E(n, !1), t(function() {
r += "", i += "";
var t = {};
if (c && typeof c === T) for (var v in c) t[v] = c[v];
t.data = e, t.width = r, t.height = i;
var m = {};
if (l && typeof l === T) for (var g in l) m[g] = l[g];
if (u && typeof u === T) for (var y in u) typeof m.flashvars != x ? m.flashvars += "&" + y + "=" + u[y] : m.flashvars = y + "=" + u[y];
if (b(s)) {
var w = h(t, m, n);
t.id == n && E(n, !0), d.success = !0, d.ref = w;
} else {
if (o && a()) {
t.data = o, f(t, m, n, p);
return;
}
E(n, !0);
}
p && p(d);
})) : p && p(d);
},
switchOffAutoHideShow: function() {
V = !1;
},
ua: $,
getFlashPlayerVersion: function() {
return {
major: $.pv[0],
minor: $.pv[1],
release: $.pv[2]
};
},
hasFlashPlayerVersion: b,
createSWF: function(e, t, n) {
return $.w3 ? h(e, t, n) : undefined;
},
showExpressInstall: function(e, t, n, r) {
$.w3 && a() && f(e, t, n, r);
},
removeSWF: function(e) {
$.w3 && d(e);
},
createCSS: function(e, t, n, r) {
$.w3 && w(e, t, n, r);
},
addDomLoadEvent: t,
addLoadEvent: n,
getQueryParamValue: function(e) {
var t = M.location.search || M.location.hash;
if (t) {
/\?/.test(t) && (t = t.split("?")[1]);
if (e == null) return S(t);
var n = t.split("&");
for (var r = 0; r < n.length; r++) if (n[r].substring(0, n[r].indexOf("=")) == e) return S(n[r].substring(n[r].indexOf("=") + 1));
}
return "";
},
expressInstallCallback: function() {
if (z) {
var e = m(L);
e && F && (e.parentNode.replaceChild(F, e), I && (E(I, !0), $.ie && $.win && (F.style.display = "block")), q && q(R)), z = !1;
}
}
};
}();
return i;
} catch (s) {
wx.jslog({
src: "biz_web/lib/swfobject.js"
}, s);
}
});define("biz_web/lib/video.js", [], function(require, exports, module) {
try {
var report_time_begin = +(new Date);
document.createElement("video"), document.createElement("audio"), document.createElement("track");
var vjs = function(e, t, n) {
var r;
if (typeof e == "string") {
e.indexOf("#") === 0 && (e = e.slice(1));
if (vjs.players[e]) return vjs.players[e];
r = vjs.el(e);
} else r = e;
if (!r || !r.nodeName) throw new TypeError("The element or ID supplied is not valid. (videojs)");
return r.player || new vjs.Player(r, t, n);
}, videojs = vjs;
window.videojs = window.vjs = vjs, vjs.CDN_VERSION = "4.1", vjs.ACCESS_PROTOCOL = "https:" == document.location.protocol ? "https://" : "http://", vjs.options = {
techOrder: [ "html5", "flash" ],
html5: {},
flash: {},
width: 300,
height: 150,
defaultVolume: 0,
children: {
mediaLoader: {},
posterImage: {},
textTrackDisplay: {},
loadingSpinner: {},
bigPlayButton: {},
controlBar: {}
}
}, vjs.CDN_VERSION !== "GENERATED_CDN_VSN" && (videojs.options.flash.swf = vjs.ACCESS_PROTOCOL + "vjs.zencdn.net/" + vjs.CDN_VERSION + "/video-js.swf"), vjs.players = {}, vjs.CoreObject = vjs.CoreObject = function() {}, vjs.CoreObject.extend = function(e) {
var t, n;
e = e || {}, t = e.init || e.init || this.prototype.init || this.prototype.init || function() {}, n = function() {
t.apply(this, arguments);
}, n.prototype = vjs.obj.create(this.prototype), n.prototype.constructor = n, n.extend = vjs.CoreObject.extend, n.create = vjs.CoreObject.create;
for (var r in e) e.hasOwnProperty(r) && (n.prototype[r] = e[r]);
return n;
}, vjs.CoreObject.create = function() {
var e = vjs.obj.create(this.prototype);
return this.apply(e, arguments), e;
}, vjs.on = function(e, t, n) {
var r = vjs.getData(e);
r.handlers || (r.handlers = {}), r.handlers[t] || (r.handlers[t] = []), n.guid || (n.guid = vjs.guid++), r.handlers[t].push(n), r.dispatcher || (r.disabled = !1, r.dispatcher = function(t) {
if (r.disabled) return;
t = vjs.fixEvent(t);
var n = r.handlers[t.type];
if (n) {
var i = n.slice(0);
for (var s = 0, o = i.length; s < o; s++) {
if (t.isImmediatePropagationStopped()) break;
i[s].call(e, t);
}
}
}), r.handlers[t].length == 1 && (document.addEventListener ? e.addEventListener(t, r.dispatcher, !1) : document.attachEvent && e.attachEvent("on" + t, r.dispatcher));
}, vjs.off = function(e, t, n) {
if (!vjs.hasData(e)) return;
var r = vjs.getData(e);
if (!r.handlers) return;
var i = function(t) {
r.handlers[t] = [], vjs.cleanUpEvents(e, t);
};
if (!t) {
for (var s in r.handlers) i(s);
return;
}
var o = r.handlers[t];
if (!o) return;
if (!n) {
i(t);
return;
}
if (n.guid) for (var u = 0; u < o.length; u++) o[u].guid === n.guid && o.splice(u--, 1);
vjs.cleanUpEvents(e, t);
}, vjs.cleanUpEvents = function(e, t) {
var n = vjs.getData(e);
n.handlers[t].length === 0 && (delete n.handlers[t], document.removeEventListener ? e.removeEventListener(t, n.dispatcher, !1) : document.detachEvent && e.detachEvent("on" + t, n.dispatcher)), vjs.isEmpty(n.handlers) && (delete n.handlers, delete n.dispatcher, delete n.disabled), vjs.isEmpty(n) && vjs.removeData(e);
}, vjs.fixEvent = function(e) {
function t() {
return !0;
}
function n() {
return !1;
}
if (!e || !e.isPropagationStopped) {
var r = e || window.event;
e = {};
for (var i in r) i !== "layerX" && i !== "layerY" && (e[i] = r[i]);
e.target || (e.target = e.srcElement || document), e.relatedTarget = e.fromElement === e.target ? e.toElement : e.fromElement, e.preventDefault = function() {
r.preventDefault && r.preventDefault(), e.returnValue = !1, e.isDefaultPrevented = t;
}, e.isDefaultPrevented = n, e.stopPropagation = function() {
r.stopPropagation && r.stopPropagation(), e.cancelBubble = !0, e.isPropagationStopped = t;
}, e.isPropagationStopped = n, e.stopImmediatePropagation = function() {
r.stopImmediatePropagation && r.stopImmediatePropagation(), e.isImmediatePropagationStopped = t, e.stopPropagation();
}, e.isImmediatePropagationStopped = n;
if (e.clientX != null) {
var s = document.documentElement, o = document.body;
e.pageX = e.clientX + (s && s.scrollLeft || o && o.scrollLeft || 0) - (s && s.clientLeft || o && o.clientLeft || 0), e.pageY = e.clientY + (s && s.scrollTop || o && o.scrollTop || 0) - (s && s.clientTop || o && o.clientTop || 0);
}
e.which = e.charCode || e.keyCode, e.button != null && (e.button = e.button & 1 ? 0 : e.button & 4 ? 1 : e.button & 2 ? 2 : 0);
}
return e;
}, vjs.trigger = function(e, t) {
var n = vjs.hasData(e) ? vjs.getData(e) : {}, r = e.parentNode || e.ownerDocument;
typeof t == "string" && (t = {
type: t,
target: e
}), t = vjs.fixEvent(t), n.dispatcher && n.dispatcher.call(e, t);
if (r && !t.isPropagationStopped()) vjs.trigger(r, t); else if (!r && !t.isDefaultPrevented()) {
var i = vjs.getData(t.target);
t.target[t.type] && (i.disabled = !0, typeof t.target[t.type] == "function" && t.target[t.type](), i.disabled = !1);
}
return !t.isDefaultPrevented();
}, vjs.one = function(e, t, n) {
vjs.on(e, t, function() {
vjs.off(e, t, arguments.callee), n.apply(this, arguments);
});
};
var hasOwnProp = Object.prototype.hasOwnProperty;
vjs.createEl = function(e, t) {
var n = document.createElement(e || "div");
for (var r in t) hasOwnProp.call(t, r) && (r.indexOf("aria-") !== -1 || r == "role" ? n.setAttribute(r, t[r]) : n[r] = t[r]);
return n;
}, vjs.capitalize = function(e) {
return e.charAt(0).toUpperCase() + e.slice(1);
}, vjs.obj = {}, vjs.obj.create = Object.create || function(e) {
function t() {}
return t.prototype = e, new t;
}, vjs.obj.each = function(e, t, n) {
for (var r in e) hasOwnProp.call(e, r) && t.call(n || this, r, e[r]);
}, vjs.obj.merge = function(e, t) {
if (!t) return e;
for (var n in t) hasOwnProp.call(t, n) && (e[n] = t[n]);
return e;
}, vjs.obj.deepMerge = function(e, t) {
var n, r, i, s;
s = "[object Object]", e = vjs.obj.copy(e);
for (n in t) hasOwnProp.call(t, n) && (r = e[n], i = t[n], vjs.obj.isPlain(r) && vjs.obj.isPlain(i) ? e[n] = vjs.obj.deepMerge(r, i) : e[n] = t[n]);
return e;
}, vjs.obj.copy = function(e) {
return vjs.obj.merge({}, e);
}, vjs.obj.isPlain = function(e) {
return !!e && typeof e == "object" && e.toString() === "[object Object]" && e.constructor === Object;
}, vjs.bind = function(e, t, n) {
t.guid || (t.guid = vjs.guid++);
var r = function() {
return t.apply(e, arguments);
};
return r.guid = n ? n + "_" + t.guid : t.guid, r;
}, vjs.cache = {}, vjs.guid = 1, vjs.expando = "vdata" + (new Date).getTime(), vjs.getData = function(e) {
var t = e[vjs.expando];
return t || (t = e[vjs.expando] = vjs.guid++, vjs.cache[t] = {}), vjs.cache[t];
}, vjs.hasData = function(e) {
var t = e[vjs.expando];
return !!t && !vjs.isEmpty(vjs.cache[t]);
}, vjs.removeData = function(e) {
var t = e[vjs.expando];
if (!t) return;
delete vjs.cache[t];
try {
delete e[vjs.expando];
} catch (n) {
e.removeAttribute ? e.removeAttribute(vjs.expando) : e[vjs.expando] = null;
}
}, vjs.isEmpty = function(e) {
for (var t in e) if (e[t] !== null) return !1;
return !0;
}, vjs.addClass = function(e, t) {
(" " + e.className + " ").indexOf(" " + t + " ") == -1 && (e.className = e.className === "" ? t : e.className + " " + t);
}, vjs.removeClass = function(e, t) {
if (e.className.indexOf(t) == -1) return;
var n = e.className.split(" ");
for (var r = n.length - 1; r >= 0; r--) n[r] === t && n.splice(r, 1);
e.className = n.join(" ");
}, vjs.TEST_VID = vjs.createEl("video"), vjs.USER_AGENT = navigator.userAgent, vjs.IS_IPHONE = /iPhone/i.test(vjs.USER_AGENT), vjs.IS_IPAD = /iPad/i.test(vjs.USER_AGENT), vjs.IS_IPOD = /iPod/i.test(vjs.USER_AGENT), vjs.IS_IOS = vjs.IS_IPHONE || vjs.IS_IPAD || vjs.IS_IPOD, vjs.IOS_VERSION = function() {
var e = vjs.USER_AGENT.match(/OS (\d+)_/i);
if (e && e[1]) return e[1];
}(), vjs.IS_ANDROID = /Android/i.test(vjs.USER_AGENT), vjs.ANDROID_VERSION = function() {
var e = vjs.USER_AGENT.match(/Android (\d+)(?:\.(\d+))?(?:\.(\d+))*/i), t, n;
return e ? (t = e[1] && parseFloat(e[1]), n = e[2] && parseFloat(e[2]), t && n ? parseFloat(e[1] + "." + e[2]) : t ? t : null) : null;
}(), vjs.IS_OLD_ANDROID = vjs.IS_ANDROID && /webkit/i.test(vjs.USER_AGENT) && vjs.ANDROID_VERSION < 2.3, vjs.IS_FIREFOX = /Firefox/i.test(vjs.USER_AGENT), vjs.IS_CHROME = /Chrome/i.test(vjs.USER_AGENT), vjs.getAttributeValues = function(e) {
var t = {}, n = ",autoplay,controls,loop,muted,default,";
if (e && e.attributes && e.attributes.length > 0) {
var r = e.attributes, i, s;
for (var o = r.length - 1; o >= 0; o--) {
i = r[o].name, s = r[o].value;
if (typeof e[i] == "boolean" || n.indexOf("," + i + ",") !== -1) s = s !== null ? !0 : !1;
t[i] = s;
}
}
return t;
}, vjs.getComputedDimension = function(e, t) {
var n = "";
return document.defaultView && document.defaultView.getComputedStyle ? n = document.defaultView.getComputedStyle(e, "").getPropertyValue(t) : e.currentStyle && (n = e["client" + t.substr(0, 1).toUpperCase() + t.substr(1)] + "px"), n;
}, vjs.insertFirst = function(e, t) {
t.firstChild ? t.insertBefore(e, t.firstChild) : t.appendChild(e);
}, vjs.support = {}, vjs.el = function(e) {
return e.indexOf("#") === 0 && (e = e.slice(1)), document.getElementById(e);
}, vjs.formatTime = function(e, t) {
t = t || e;
var n = Math.floor(e % 60), r = Math.floor(e / 60 % 60), i = Math.floor(e / 3600), s = Math.floor(t / 60 % 60), o = Math.floor(t / 3600);
return i = i > 0 || o > 0 ? i + ":" : "", r = ((i || s >= 10) && r < 10 ? "0" + r : r) + ":", n = n < 10 ? "0" + n : n, i + r + n;
}, vjs.blockTextSelection = function() {
document.body.focus(), document.onselectstart = function() {
return !1;
};
}, vjs.unblockTextSelection = function() {
document.onselectstart = function() {
return !0;
};
}, vjs.trim = function(e) {
return e.toString().replace(/^\s+/, "").replace(/\s+$/, "");
}, vjs.round = function(e, t) {
return t || (t = 0), Math.round(e * Math.pow(10, t)) / Math.pow(10, t);
}, vjs.createTimeRange = function(e, t) {
return {
length: 1,
start: function() {
return e;
},
end: function() {
return t;
}
};
}, vjs.get = function(e, t, n) {
var r = e.indexOf("file:") === 0 || window.location.href.indexOf("file:") === 0 && e.indexOf("http") === -1;
typeof XMLHttpRequest == "undefined" && (window.XMLHttpRequest = function() {
try {
return new window.ActiveXObject("Msxml2.XMLHTTP.6.0");
} catch (e) {}
try {
return new window.ActiveXObject("Msxml2.XMLHTTP.3.0");
} catch (t) {}
try {
return new window.ActiveXObject("Msxml2.XMLHTTP");
} catch (n) {}
throw new Error("This browser does not support XMLHttpRequest.");
});
var i = new XMLHttpRequest;
try {
i.open("GET", e);
} catch (s) {
n(s);
}
i.onreadystatechange = function() {
i.readyState === 4 && (i.status === 200 || r && i.status === 0 ? t(i.responseText) : n && n());
};
try {
i.send();
} catch (s) {
n && n(s);
}
}, vjs.setLocalStorage = function(e, t) {
try {
var n = window.localStorage || !1;
if (!n) return;
n[e] = t;
} catch (r) {
r.code == 22 || r.code == 1014 ? vjs.log("LocalStorage Full (VideoJS)", r) : r.code == 18 ? vjs.log("LocalStorage not allowed (VideoJS)", r) : vjs.log("LocalStorage Error (VideoJS)", r);
}
}, vjs.getAbsoluteURL = function(e) {
return e.match(/^https?:\/\//) || (e = vjs.createEl("div", {
innerHTML: '<a href="' + e + '">x</a>'
}).firstChild.href), e;
}, vjs.log = function() {
vjs.log.history = vjs.log.history || [], vjs.log.history.push(arguments), window.console && window.console.log(Array.prototype.slice.call(arguments));
}, vjs.findPosition = function(e) {
var t, n, r, i, s, o, u, a, f;
return e.getBoundingClientRect && e.parentNode && (t = e.getBoundingClientRect()), t ? (n = document.documentElement, r = document.body, i = n.clientLeft || r.clientLeft || 0, s = window.pageXOffset || r.scrollLeft, o = t.left + s - i, u = n.clientTop || r.clientTop || 0, a = window.pageYOffset || r.scrollTop, f = t.top + a - u, {
left: o,
top: f
}) : {
left: 0,
top: 0
};
}, vjs.Component = vjs.CoreObject.extend({
init: function(e, t, n) {
this.player_ = e, this.options_ = vjs.obj.copy(this.options_), t = this.options(t), this.id_ = t.id || (t.el && t.el.id ? t.el.id : e.id() + "_component_" + vjs.guid++), this.name_ = t.name || null, this.el_ = t.el || this.createEl(), this.children_ = [], this.childIndex_ = {}, this.childNameIndex_ = {}, this.initChildren(), this.ready(n);
}
}), vjs.Component.prototype.dispose = function() {
if (this.children_) for (var e = this.children_.length - 1; e >= 0; e--) this.children_[e].dispose && this.children_[e].dispose();
this.children_ = null, this.childIndex_ = null, this.childNameIndex_ = null, this.off(), this.el_.parentNode && this.el_.parentNode.removeChild(this.el_), vjs.removeData(this.el_), this.el_ = null;
}, vjs.Component.prototype.player_, vjs.Component.prototype.player = function() {
return this.player_;
}, vjs.Component.prototype.options_, vjs.Component.prototype.options = function(e) {
return e === undefined ? this.options_ : this.options_ = vjs.obj.deepMerge(this.options_, e);
}, vjs.Component.prototype.el_, vjs.Component.prototype.createEl = function(e, t) {
return vjs.createEl(e, t);
}, vjs.Component.prototype.el = function() {
return this.el_;
}, vjs.Component.prototype.contentEl_, vjs.Component.prototype.contentEl = function() {
return this.contentEl_ || this.el_;
}, vjs.Component.prototype.id_, vjs.Component.prototype.id = function() {
return this.id_;
}, vjs.Component.prototype.name_, vjs.Component.prototype.name = function() {
return this.name_;
}, vjs.Component.prototype.children_, vjs.Component.prototype.children = function() {
return this.children_;
}, vjs.Component.prototype.childIndex_, vjs.Component.prototype.getChildById = function(e) {
return this.childIndex_[e];
}, vjs.Component.prototype.childNameIndex_, vjs.Component.prototype.getChild = function(e) {
return this.childNameIndex_[e];
}, vjs.Component.prototype.addChild = function(e, t) {
var n, r, i, s;
return typeof e == "string" ? (i = e, t = t || {}, r = t.componentClass || vjs.capitalize(i), t.name = i, n = new window.videojs[r](this.player_ || this, t)) : n = e, this.children_.push(n), typeof n.id == "function" && (this.childIndex_[n.id()] = n), i = i || n.name && n.name(), i && (this.childNameIndex_[i] = n), typeof n.el == "function" && n.el() && this.contentEl().appendChild(n.el()), n;
}, vjs.Component.prototype.removeChild = function(e) {
typeof e == "string" && (e = this.getChild(e));
if (!e || !this.children_) return;
var t = !1;
for (var n = this.children_.length - 1; n >= 0; n--) if (this.children_[n] === e) {
t = !0, this.children_.splice(n, 1);
break;
}
if (!t) return;
this.childIndex_[e.id] = null, this.childNameIndex_[e.name] = null;
var r = e.el();
r && r.parentNode === this.contentEl() && this.contentEl().removeChild(e.el());
}, vjs.Component.prototype.initChildren = function() {
var e = this.options_;
if (e && e.children) {
var t = this;
vjs.obj.each(e.children, function(e, n) {
if (n === !1) return;
var r = function() {
t[e] = t.addChild(e, n);
};
n.loadEvent || r();
});
}
}, vjs.Component.prototype.buildCSSClass = function() {
return "";
}, vjs.Component.prototype.on = function(e, t) {
return vjs.on(this.el_, e, vjs.bind(this, t)), this;
}, vjs.Component.prototype.off = function(e, t) {
return vjs.off(this.el_, e, t), this;
}, vjs.Component.prototype.one = function(e, t) {
return vjs.one(this.el_, e, vjs.bind(this, t)), this;
}, vjs.Component.prototype.trigger = function(e, t) {
return vjs.trigger(this.el_, e, t), this;
}, vjs.Component.prototype.isReady_, vjs.Component.prototype.isReadyOnInitFinish_ = !0, vjs.Component.prototype.readyQueue_, vjs.Component.prototype.ready = function(e) {
return e && (this.isReady_ ? e.call(this) : (this.readyQueue_ === undefined && (this.readyQueue_ = []), this.readyQueue_.push(e))), this;
}, vjs.Component.prototype.triggerReady = function() {
this.isReady_ = !0;
var e = this.readyQueue_;
if (e && e.length > 0) {
for (var t = 0, n = e.length; t < n; t++) e[t].call(this);
this.readyQueue_ = [], this.trigger("ready");
}
}, vjs.Component.prototype.addClass = function(e) {
return vjs.addClass(this.el_, e), this;
}, vjs.Component.prototype.removeClass = function(e) {
return vjs.removeClass(this.el_, e), this;
}, vjs.Component.prototype.show = function() {
return this.el_.style.display = "block", this;
}, vjs.Component.prototype.hide = function() {
return this.el_.style.display = "none", this;
}, vjs.Component.prototype.fadeIn = function() {
return this.removeClass("vjs-fade-out"), this.addClass("vjs-fade-in"), this;
}, vjs.Component.prototype.fadeOut = function() {
return this.removeClass("vjs-fade-in"), this.addClass("vjs-fade-out"), this;
}, vjs.Component.prototype.lockShowing = function() {
return this.addClass("vjs-lock-showing"), this;
}, vjs.Component.prototype.unlockShowing = function() {
return this.removeClass("vjs-lock-showing"), this;
}, vjs.Component.prototype.disable = function() {
this.hide(), this.show = function() {}, this.fadeIn = function() {};
}, vjs.Component.prototype.width = function(e, t) {
return this.dimension("width", e, t);
}, vjs.Component.prototype.height = function(e, t) {
return this.dimension("height", e, t);
}, vjs.Component.prototype.dimensions = function(e, t) {
return this.width(e, !0).height(t);
}, vjs.Component.prototype.dimension = function(e, t, n) {
if (t !== undefined) return ("" + t).indexOf("%") !== -1 || ("" + t).indexOf("px") !== -1 ? this.el_.style[e] = t : t === "auto" ? this.el_.style[e] = "" : this.el_.style[e] = t + "px", n || this.trigger("resize"), this;
if (!this.el_) return 0;
var r = this.el_.style[e], i = r.indexOf("px");
return i !== -1 ? parseInt(r.slice(0, i), 10) : parseInt(this.el_["offset" + vjs.capitalize(e)], 10);
}, vjs.Button = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t);
var n = !1;
this.on("touchstart", function() {
n = !0;
}), this.on("touchmove", function() {
n = !1;
});
var r = this;
this.on("touchend", function(e) {
n && r.onClick(e), e.preventDefault(), e.stopPropagation();
}), this.on("click", this.onClick), this.on("focus", this.onFocus), this.on("blur", this.onBlur);
}
}), vjs.Button.prototype.createEl = function(e, t) {
return t = vjs.obj.merge({
className: this.buildCSSClass(),
innerHTML: '<div class="vjs-control-content"><span class="vjs-control-text">' + (this.buttonText || "Need Text") + "</span></div>",
role: "button",
"aria-live": "polite",
tabIndex: 0
}, t), vjs.Component.prototype.createEl.call(this, e, t);
}, vjs.Button.prototype.buildCSSClass = function() {
return "vjs-control " + vjs.Component.prototype.buildCSSClass.call(this);
}, vjs.Button.prototype.onClick = function() {}, vjs.Button.prototype.onFocus = function() {
vjs.on(document, "keyup", vjs.bind(this, this.onKeyPress));
}, vjs.Button.prototype.onKeyPress = function(e) {
if (e.which == 32 || e.which == 13) e.preventDefault(), this.onClick();
}, vjs.Button.prototype.onBlur = function() {
vjs.off(document, "keyup", vjs.bind(this, this.onKeyPress));
}, vjs.Slider = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), this.bar = this.getChild(this.options_.barName), this.handle = this.getChild(this.options_.handleName), e.on(this.playerEvent, vjs.bind(this, this.update)), this.on("mousedown", this.onMouseDown), this.on("touchstart", this.onMouseDown), this.on("focus", this.onFocus), this.on("blur", this.onBlur), this.on("click", this.onClick), this.player_.on("controlsvisible", vjs.bind(this, this.update)), e.ready(vjs.bind(this, this.update)), this.boundEvents = {};
}
}), vjs.Slider.prototype.createEl = function(e, t) {
return t = t || {}, t.className = t.className + " vjs-slider", t = vjs.obj.merge({
role: "slider",
"aria-valuenow": 0,
"aria-valuemin": 0,
"aria-valuemax": 100,
tabIndex: 0
}, t), vjs.Component.prototype.createEl.call(this, e, t);
}, vjs.Slider.prototype.onMouseDown = function(e) {
e.preventDefault(), vjs.blockTextSelection(), this.boundEvents.move = vjs.bind(this, this.onMouseMove), this.boundEvents.end = vjs.bind(this, this.onMouseUp), vjs.on(document, "mousemove", this.boundEvents.move), vjs.on(document, "mouseup", this.boundEvents.end), vjs.on(document, "touchmove", this.boundEvents.move), vjs.on(document, "touchend", this.boundEvents.end), this.onMouseMove(e);
}, vjs.Slider.prototype.onMouseUp = function() {
vjs.unblockTextSelection(), vjs.off(document, "mousemove", this.boundEvents.move, !1), vjs.off(document, "mouseup", this.boundEvents.end, !1), vjs.off(document, "touchmove", this.boundEvents.move, !1), vjs.off(document, "touchend", this.boundEvents.end, !1), this.update();
}, vjs.Slider.prototype.update = function() {
if (!this.el_) return;
var e, t = this.getPercent(), n = this.handle, r = this.bar;
isNaN(t) && (t = 0), e = t;
if (n) {
var i = this.el_, s = i.offsetWidth, o = n.el().offsetWidth, u = o ? o / s : 0, a = 1 - u, f = t * a;
e = f + u / 2, n.el().style.left = vjs.round(f * 100, 2) + "%";
}
r.el().style.width = vjs.round(e * 100, 2) + "%";
}, vjs.Slider.prototype.calculateDistance = function(e) {
var t, n, r, i, s, o, u, a, f;
t = this.el_, n = vjs.findPosition(t), s = o = t.offsetWidth, u = this.handle;
if (this.options_.vertical) {
i = n.top, e.changedTouches ? f = e.changedTouches[0].pageY : f = e.pageY;
if (u) {
var l = u.el().offsetHeight;
i += l / 2, o -= l;
}
return Math.max(0, Math.min(1, (i - f + o) / o));
}
r = n.left, e.changedTouches ? a = e.changedTouches[0].pageX : a = e.pageX;
if (u) {
var c = u.el().offsetWidth;
r += c / 2, s -= c;
}
return Math.max(0, Math.min(1, (a - r) / s));
}, vjs.Slider.prototype.onFocus = function() {
vjs.on(document, "keyup", vjs.bind(this, this.onKeyPress));
}, vjs.Slider.prototype.onKeyPress = function(e) {
e.which == 37 ? (e.preventDefault(), this.stepBack()) : e.which == 39 && (e.preventDefault(), this.stepForward());
}, vjs.Slider.prototype.onBlur = function() {
vjs.off(document, "keyup", vjs.bind(this, this.onKeyPress));
}, vjs.Slider.prototype.onClick = function(e) {
e.stopImmediatePropagation(), e.preventDefault();
}, vjs.SliderHandle = vjs.Component.extend(), vjs.SliderHandle.prototype.defaultValue = 0, vjs.SliderHandle.prototype.createEl = function(e, t) {
return t = t || {}, t.className = t.className + " vjs-slider-handle", t = vjs.obj.merge({
innerHTML: '<span class="vjs-control-text">' + this.defaultValue + "</span>"
}, t), vjs.Component.prototype.createEl.call(this, "div", t);
}, vjs.Menu = vjs.Component.extend(), vjs.Menu.prototype.addItem = function(e) {
this.addChild(e), e.on("click", vjs.bind(this, function() {
this.unlockShowing();
}));
}, vjs.Menu.prototype.createEl = function() {
var e = this.options().contentElType || "ul";
this.contentEl_ = vjs.createEl(e, {
className: "vjs-menu-content"
});
var t = vjs.Component.prototype.createEl.call(this, "div", {
append: this.contentEl_,
className: "vjs-menu"
});
return t.appendChild(this.contentEl_), vjs.on(t, "click", function(e) {
e.preventDefault(), e.stopImmediatePropagation();
}), t;
}, vjs.MenuItem = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t), this.selected(t.selected);
}
}), vjs.MenuItem.prototype.createEl = function(e, t) {
return vjs.Button.prototype.createEl.call(this, "li", vjs.obj.merge({
className: "vjs-menu-item",
innerHTML: this.options_.label
}, t));
}, vjs.MenuItem.prototype.onClick = function() {
this.selected(!0);
}, vjs.MenuItem.prototype.selected = function(e) {
e ? (this.addClass("vjs-selected"), this.el_.setAttribute("aria-selected", !0)) : (this.removeClass("vjs-selected"), this.el_.setAttribute("aria-selected", !1));
}, vjs.MenuButton = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t), this.menu = this.createMenu(), this.addChild(this.menu), this.items && this.items.length === 0 && this.hide(), this.on("keyup", this.onKeyPress), this.el_.setAttribute("aria-haspopup", !0), this.el_.setAttribute("role", "button");
}
}), vjs.MenuButton.prototype.buttonPressed_ = !1, vjs.MenuButton.prototype.createMenu = function() {
var e = new vjs.Menu(this.player_);
this.options().title && e.el().appendChild(vjs.createEl("li", {
className: "vjs-menu-title",
innerHTML: vjs.capitalize(this.kind_),
tabindex: -1
})), this.items = this.createItems();
if (this.items) for (var t = 0; t < this.items.length; t++) e.addItem(this.items[t]);
return e;
}, vjs.MenuButton.prototype.createItems = function() {}, vjs.MenuButton.prototype.buildCSSClass = function() {
return this.className + " vjs-menu-button " + vjs.Button.prototype.buildCSSClass.call(this);
}, vjs.MenuButton.prototype.onFocus = function() {}, vjs.MenuButton.prototype.onBlur = function() {}, vjs.MenuButton.prototype.onClick = function() {
this.one("mouseout", vjs.bind(this, function() {
this.menu.unlockShowing(), this.el_.blur();
})), this.buttonPressed_ ? this.unpressButton() : this.pressButton();
}, vjs.MenuButton.prototype.onKeyPress = function(e) {
e.preventDefault(), e.which == 32 || e.which == 13 ? this.buttonPressed_ ? this.unpressButton() : this.pressButton() : e.which == 27 && this.buttonPressed_ && this.unpressButton();
}, vjs.MenuButton.prototype.pressButton = function() {
this.buttonPressed_ = !0, this.menu.lockShowing(), this.el_.setAttribute("aria-pressed", !0), this.items && this.items.length > 0 && this.items[0].el().focus();
}, vjs.MenuButton.prototype.unpressButton = function() {
this.buttonPressed_ = !1, this.menu.unlockShowing(), this.el_.setAttribute("aria-pressed", !1);
}, vjs.Player = vjs.Component.extend({
init: function(e, t, n) {
this.tag = e, t = vjs.obj.merge(this.getTagSettings(e), t), this.cache_ = {}, this.poster_ = t.poster, this.controls_ = t.controls, t.customControlsOnMobile !== !0 && (vjs.IS_IOS || vjs.IS_ANDROID) ? (e.controls = t.controls, this.controls_ = !1) : e.controls = !1, vjs.Component.call(this, this, t, n), this.one("play", function(e) {
var t = {
type: "firstplay",
target: this.el_
}, n = vjs.trigger(this.el_, t);
n || (e.preventDefault(), e.stopPropagation(), e.stopImmediatePropagation());
}), this.on("ended", this.onEnded), this.on("play", this.onPlay), this.on("firstplay", this.onFirstPlay), this.on("pause", this.onPause), this.on("progress", this.onProgress), this.on("durationchange", this.onDurationChange), this.on("error", this.onError), this.on("fullscreenchange", this.onFullscreenChange), vjs.players[this.id_] = this, t.plugins && vjs.obj.each(t.plugins, function(e, t) {
this[e](t);
}, this);
}
}), vjs.Player.prototype.options_ = vjs.options, vjs.Player.prototype.dispose = function() {
vjs.players[this.id_] = null, this.tag && this.tag.player && (this.tag.player = null), this.el_ && this.el_.player && (this.el_.player = null), this.stopTrackingProgress(), this.stopTrackingCurrentTime(), this.tech && this.tech.dispose(), vjs.Component.prototype.dispose.call(this);
}, vjs.Player.prototype.getTagSettings = function(e) {
var t = {
sources: [],
tracks: []
};
vjs.obj.merge(t, vjs.getAttributeValues(e));
if (e.hasChildNodes()) {
var n, r, i, s, o;
n = e.childNodes;
for (s = 0, o = n.length; s < o; s++) r = n[s], i = r.nodeName.toLowerCase(), i === "source" ? t.sources.push(vjs.getAttributeValues(r)) : i === "track" && t.tracks.push(vjs.getAttributeValues(r));
}
return t;
}, vjs.Player.prototype.createEl = function() {
var e = this.el_ = vjs.Component.prototype.createEl.call(this, "div"), t = this.tag;
t.removeAttribute("width"), t.removeAttribute("height");
if (t.hasChildNodes()) {
var n, r, i, s, o, u;
n = t.childNodes, r = n.length, u = [];
while (r--) s = n[r], o = s.nodeName.toLowerCase(), (o === "source" || o === "track") && u.push(s);
for (i = 0; i < u.length; i++) t.removeChild(u[i]);
}
return t.id = t.id || "vjs_video_" + vjs.guid++, e.id = t.id, e.className = t.className, t.id += "_html5_api", t.className = "vjs-tech", t.player = e.player = this, this.addClass("vjs-paused"), this.width(this.options_.width, !0), this.height(this.options_.height, !0), t.parentNode && t.parentNode.insertBefore(e, t), vjs.insertFirst(t, e), e;
}, vjs.Player.prototype.loadTech = function(e, t) {
this.tech ? this.unloadTech() : e !== "Html5" && this.tag && (this.el_.removeChild(this.tag), this.tag.player = null, this.tag = null), this.techName = e, this.isReady_ = !1;
var n = function() {
this.player_.triggerReady(), this.features.progressEvents || this.player_.manualProgressOn(), this.features.timeupdateEvents || this.player_.manualTimeUpdatesOn();
}, r = vjs.obj.merge({
source: t,
parentEl: this.el_
}, this.options_[e.toLowerCase()]);
t && (t.src == this.cache_.src && this.cache_.currentTime > 0 && (r.startTime = this.cache_.currentTime), this.cache_.src = t.src), this.tech = new window.videojs[e](this, r), this.tech.ready(n);
}, vjs.Player.prototype.unloadTech = function() {
this.isReady_ = !1, this.tech.dispose(), this.manualProgress && this.manualProgressOff(), this.manualTimeUpdates && this.manualTimeUpdatesOff(), this.tech = !1;
}, vjs.Player.prototype.manualProgressOn = function() {
this.manualProgress = !0, this.trackProgress(), this.tech.one("progress", function() {
this.features.progressEvents = !0, this.player_.manualProgressOff();
});
}, vjs.Player.prototype.manualProgressOff = function() {
this.manualProgress = !1, this.stopTrackingProgress();
}, vjs.Player.prototype.trackProgress = function() {
this.progressInterval = setInterval(vjs.bind(this, function() {
this.cache_.bufferEnd < this.buffered().end(0) ? this.trigger("progress") : this.bufferedPercent() == 1 && (this.stopTrackingProgress(), this.trigger("progress"));
}), 500);
}, vjs.Player.prototype.stopTrackingProgress = function() {
clearInterval(this.progressInterval);
}, vjs.Player.prototype.manualTimeUpdatesOn = function() {
this.manualTimeUpdates = !0, this.on("play", this.trackCurrentTime), this.on("pause", this.stopTrackingCurrentTime), this.tech.one("timeupdate", function() {
this.features.timeupdateEvents = !0, this.player_.manualTimeUpdatesOff();
});
}, vjs.Player.prototype.manualTimeUpdatesOff = function() {
this.manualTimeUpdates = !1, this.stopTrackingCurrentTime(), this.off("play", this.trackCurrentTime), this.off("pause", this.stopTrackingCurrentTime);
}, vjs.Player.prototype.trackCurrentTime = function() {
this.currentTimeInterval && this.stopTrackingCurrentTime(), this.currentTimeInterval = setInterval(vjs.bind(this, function() {
this.trigger("timeupdate");
}), 250);
}, vjs.Player.prototype.stopTrackingCurrentTime = function() {
clearInterval(this.currentTimeInterval);
}, vjs.Player.prototype.onEnded = function() {
this.options_.loop && (this.currentTime(0), this.play());
}, vjs.Player.prototype.onPlay = function() {
vjs.removeClass(this.el_, "vjs-paused"), vjs.addClass(this.el_, "vjs-playing");
}, vjs.Player.prototype.onFirstPlay = function() {
this.options_.starttime && this.currentTime(this.options_.starttime);
}, vjs.Player.prototype.onPause = function() {
vjs.removeClass(this.el_, "vjs-playing"), vjs.addClass(this.el_, "vjs-paused");
}, vjs.Player.prototype.onProgress = function() {
this.bufferedPercent() == 1 && this.trigger("loadedalldata");
}, vjs.Player.prototype.onDurationChange = function() {
this.duration(this.techGet("duration"));
}, vjs.Player.prototype.onError = function(e) {
vjs.log("Video Error", e);
}, vjs.Player.prototype.onFullscreenChange = function() {
this.isFullScreen ? this.addClass("vjs-fullscreen") : this.removeClass("vjs-fullscreen");
}, vjs.Player.prototype.cache_, vjs.Player.prototype.getCache = function() {
return this.cache_;
}, vjs.Player.prototype.techCall = function(e, t) {
if (this.tech && !this.tech.isReady_) this.tech.ready(function() {
this[e](t);
}); else try {
this.tech[e](t);
} catch (n) {
throw vjs.log(n), n;
}
}, vjs.Player.prototype.techGet = function(e) {
if (this.tech.isReady_) try {
return this.tech[e]();
} catch (t) {
throw this.tech[e] === undefined ? vjs.log("Video.js: " + e + " method not defined for " + this.techName + " playback technology.", t) : t.name == "TypeError" ? (vjs.log("Video.js: " + e + " unavailable on " + this.techName + " playback technology element.", t), this.tech.isReady_ = !1) : vjs.log(t), t;
}
return;
}, vjs.Player.prototype.play = function() {
return this.techCall("play"), this;
}, vjs.Player.prototype.pause = function() {
return this.techCall("pause"), this;
}, vjs.Player.prototype.paused = function() {
return this.techGet("paused") === !1 ? !1 : !0;
}, vjs.Player.prototype.currentTime = function(e) {
return e !== undefined ? (this.cache_.lastSetCurrentTime = e, this.techCall("setCurrentTime", e), this.manualTimeUpdates && this.trigger("timeupdate"), this) : this.cache_.currentTime = this.techGet("currentTime") || 0;
}, vjs.Player.prototype.duration = function(e) {
return e !== undefined ? (this.cache_.duration = parseFloat(e), this) : this.cache_.duration;
}, vjs.Player.prototype.remainingTime = function() {
return this.duration() - this.currentTime();
}, vjs.Player.prototype.buffered = function() {
var e = this.techGet("buffered"), t = 0, n = this.cache_.bufferEnd = this.cache_.bufferEnd || 0;
return e && e.length > 0 && e.end(0) !== n && (n = e.end(0), this.cache_.bufferEnd = n), vjs.createTimeRange(t, n);
}, vjs.Player.prototype.bufferedPercent = function() {
return this.duration() ? this.buffered().end(0) / this.duration() : 0;
}, vjs.Player.prototype.volume = function(e) {
var t;
return e !== undefined ? (t = Math.max(0, Math.min(1, parseFloat(e))), this.cache_.volume = t, this.techCall("setVolume", t), vjs.setLocalStorage("volume", t), this) : (t = parseFloat(this.techGet("volume")), isNaN(t) ? 1 : t);
}, vjs.Player.prototype.muted = function(e) {
return e !== undefined ? (this.techCall("setMuted", e), this) : this.techGet("muted") || !1;
}, vjs.Player.prototype.supportsFullScreen = function() {
return this.techGet("supportsFullScreen") || !1;
}, vjs.Player.prototype.requestFullScreen = function() {
var e = vjs.support.requestFullScreen;
return this.isFullScreen = !0, e ? (vjs.on(document, e.eventName, vjs.bind(this, function(t) {
this.isFullScreen = document[e.isFullScreen], this.isFullScreen === !1 && vjs.off(document, e.eventName, arguments.callee), this.trigger("fullscreenchange");
})), this.el_[e.requestFn]()) : this.tech.supportsFullScreen() ? this.techCall("enterFullScreen") : (this.enterFullWindow(), this.trigger("fullscreenchange")), this;
}, vjs.Player.prototype.cancelFullScreen = function() {
var e = vjs.support.requestFullScreen;
return this.isFullScreen = !1, e ? document[e.cancelFn]() : this.tech.supportsFullScreen() ? this.techCall("exitFullScreen") : (this.exitFullWindow(), this.trigger("fullscreenchange")), this;
}, vjs.Player.prototype.enterFullWindow = function() {
this.isFullWindow = !0, this.docOrigOverflow = document.documentElement.style.overflow, vjs.on(document, "keydown", vjs.bind(this, this.fullWindowOnEscKey)), document.documentElement.style.overflow = "hidden", vjs.addClass(document.body, "vjs-full-window"), this.trigger("enterFullWindow");
}, vjs.Player.prototype.fullWindowOnEscKey = function(e) {
e.keyCode === 27 && (this.isFullScreen === !0 ? this.cancelFullScreen() : this.exitFullWindow());
}, vjs.Player.prototype.exitFullWindow = function() {
this.isFullWindow = !1, vjs.off(document, "keydown", this.fullWindowOnEscKey), document.documentElement.style.overflow = this.docOrigOverflow, vjs.removeClass(document.body, "vjs-full-window"), this.trigger("exitFullWindow");
}, vjs.Player.prototype.selectSource = function(e) {
for (var t = 0, n = this.options_.techOrder; t < n.length; t++) {
var r = vjs.capitalize(n[t]), i = window.videojs[r];
if (i.isSupported()) for (var s = 0, o = e; s < o.length; s++) {
var u = o[s];
if (i.canPlaySource(u)) return {
source: u,
tech: r
};
}
}
return !1;
}, vjs.Player.prototype.src = function(e) {
if (e instanceof Array) {
var t = this.selectSource(e), n;
t ? (e = t.source, n = t.tech, n == this.techName ? this.src(e) : this.loadTech(n, e)) : this.el_.appendChild(vjs.createEl("p", {
innerHTML: 'Sorry, no compatible source and playback technology were found for this video. Try using another browser like <a href="http://bit.ly/ccMUEC">Chrome</a> or download the latest <a href="http://adobe.ly/mwfN1">Adobe Flash Player</a>.'
}));
} else e instanceof Object ? window.videojs[this.techName].canPlaySource(e) ? this.src(e.src) : this.src([ e ]) : (this.cache_.src = e, this.isReady_ ? (this.techCall("src", e), this.options_["preload"] == "auto" && this.load(), this.options_.autoplay && this.play()) : this.ready(function() {
this.src(e);
}));
return this;
}, vjs.Player.prototype.load = function() {
return this.techCall("load"), this;
}, vjs.Player.prototype.currentSrc = function() {
return this.techGet("currentSrc") || this.cache_.src || "";
}, vjs.Player.prototype.preload = function(e) {
return e !== undefined ? (this.techCall("setPreload", e), this.options_.preload = e, this) : this.techGet("preload");
}, vjs.Player.prototype.autoplay = function(e) {
return e !== undefined ? (this.techCall("setAutoplay", e), this.options_.autoplay = e, this) : this.techGet("autoplay", e);
}, vjs.Player.prototype.loop = function(e) {
return e !== undefined ? (this.techCall("setLoop", e), this.options_.loop = e, this) : this.techGet("loop");
}, vjs.Player.prototype.poster_, vjs.Player.prototype.poster = function(e) {
return e !== undefined && (this.poster_ = e), this.poster_;
}, vjs.Player.prototype.controls_, vjs.Player.prototype.controls = function(e) {
return e !== undefined && this.controls_ !== e && (this.controls_ = !!e, this.trigger("controlschange")), this.controls_;
}, vjs.Player.prototype.error = function() {
return this.techGet("error");
}, vjs.Player.prototype.ended = function() {
return this.techGet("ended");
}, function() {
var e, t, n;
n = document.createElement("div"), t = {}, n.cancelFullscreen !== undefined ? (t.requestFn = "requestFullscreen", t.cancelFn = "exitFullscreen", t.eventName = "fullscreenchange", t.isFullScreen = "fullScreen") : (document.mozCancelFullScreen ? (e = "moz", t.isFullScreen = e + "FullScreen") : (e = "webkit", t.isFullScreen = e + "IsFullScreen"), n[e + "RequestFullScreen"] && (t.requestFn = e + "RequestFullScreen", t.cancelFn = e + "CancelFullScreen"), t.eventName = e + "fullscreenchange"), document[t.cancelFn] && (vjs.support.requestFullScreen = t);
}(), vjs.ControlBar = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.controls() || this.disable(), e.one("play", vjs.bind(this, function() {
var e, t = vjs.bind(this, this.fadeIn), n = vjs.bind(this, this.fadeOut);
this.fadeIn(), "ontouchstart" in window || (this.player_.on("mouseover", t), this.player_.on("mouseout", n), this.player_.on("pause", vjs.bind(this, this.lockShowing)), this.player_.on("play", vjs.bind(this, this.unlockShowing))), e = !1, this.player_.on("touchstart", function() {
e = !0;
}), this.player_.on("touchmove", function() {
e = !1;
}), this.player_.on("touchend", vjs.bind(this, function(t) {
var n;
e && (n = this.el().className.search("fade-in"), n !== -1 ? this.fadeOut() : this.fadeIn()), e = !1, this.player_.paused() || t.preventDefault();
}));
}));
}
}), vjs.ControlBar.prototype.options_ = {
loadEvent: "play",
children: {
playToggle: {},
currentTimeDisplay: {},
timeDivider: {},
durationDisplay: {},
remainingTimeDisplay: {},
progressControl: {},
fullscreenToggle: {},
volumeControl: {},
muteToggle: {}
}
}, vjs.ControlBar.prototype.createEl = function() {
return vjs.createEl("div", {
className: "vjs-control-bar"
});
}, vjs.ControlBar.prototype.fadeIn = function() {
vjs.Component.prototype.fadeIn.call(this), this.player_.trigger("controlsvisible");
}, vjs.ControlBar.prototype.fadeOut = function() {
vjs.Component.prototype.fadeOut.call(this), this.player_.trigger("controlshidden");
}, vjs.PlayToggle = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t), e.on("play", vjs.bind(this, this.onPlay)), e.on("pause", vjs.bind(this, this.onPause));
}
}), vjs.PlayToggle.prototype.buttonText = "Play", vjs.PlayToggle.prototype.buildCSSClass = function() {
return "vjs-play-control " + vjs.Button.prototype.buildCSSClass.call(this);
}, vjs.PlayToggle.prototype.onClick = function() {
this.player_.paused() ? this.player_.play() : this.player_.pause();
}, vjs.PlayToggle.prototype.onPlay = function() {
vjs.removeClass(this.el_, "vjs-paused"), vjs.addClass(this.el_, "vjs-playing"), this.el_.children[0].children[0].innerHTML = "Pause";
}, vjs.PlayToggle.prototype.onPause = function() {
vjs.removeClass(this.el_, "vjs-playing"), vjs.addClass(this.el_, "vjs-paused"), this.el_.children[0].children[0].innerHTML = "Play";
}, vjs.CurrentTimeDisplay = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateContent));
}
}), vjs.CurrentTimeDisplay.prototype.createEl = function() {
var e = vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-current-time vjs-time-controls vjs-control"
});
return this.content = vjs.createEl("div", {
className: "vjs-current-time-display",
innerHTML: '<span class="vjs-control-text">Current Time </span>0:00',
"aria-live": "off"
}), e.appendChild(vjs.createEl("div").appendChild(this.content)), e;
}, vjs.CurrentTimeDisplay.prototype.updateContent = function() {
var e = this.player_.scrubbing ? this.player_.getCache().currentTime : this.player_.currentTime();
this.content.innerHTML = '<span class="vjs-control-text">Current Time </span>' + vjs.formatTime(e, this.player_.duration());
}, vjs.DurationDisplay = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateContent));
}
}), vjs.DurationDisplay.prototype.createEl = function() {
var e = vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-duration vjs-time-controls vjs-control"
});
return this.content = vjs.createEl("div", {
className: "vjs-duration-display",
innerHTML: '<span class="vjs-control-text">Duration Time </span>0:00',
"aria-live": "off"
}), e.appendChild(vjs.createEl("div").appendChild(this.content)), e;
}, vjs.DurationDisplay.prototype.updateContent = function() {
this.player_.duration() && (this.content.innerHTML = '<span class="vjs-control-text">Duration Time </span>' + vjs.formatTime(this.player_.duration()));
}, vjs.TimeDivider = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t);
}
}), vjs.TimeDivider.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-time-divider",
innerHTML: "<div><span>/</span></div>"
});
}, vjs.RemainingTimeDisplay = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateContent));
}
}), vjs.RemainingTimeDisplay.prototype.createEl = function() {
var e = vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-remaining-time vjs-time-controls vjs-control"
});
return this.content = vjs.createEl("div", {
className: "vjs-remaining-time-display",
innerHTML: '<span class="vjs-control-text">Remaining Time </span>-0:00',
"aria-live": "off"
}), e.appendChild(vjs.createEl("div").appendChild(this.content)), e;
}, vjs.RemainingTimeDisplay.prototype.updateContent = function() {
this.player_.duration() && this.player_.duration() && (this.content.innerHTML = '<span class="vjs-control-text">Remaining Time </span>-' + vjs.formatTime(this.player_.remainingTime()));
}, vjs.FullscreenToggle = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t);
}
}), vjs.FullscreenToggle.prototype.buttonText = "Fullscreen", vjs.FullscreenToggle.prototype.buildCSSClass = function() {
return "vjs-fullscreen-control " + vjs.Button.prototype.buildCSSClass.call(this);
}, vjs.FullscreenToggle.prototype.onClick = function() {
this.player_.isFullScreen ? (this.player_.cancelFullScreen(), this.el_.children[0].children[0].innerHTML = "Fullscreen") : (this.player_.requestFullScreen(), this.el_.children[0].children[0].innerHTML = "Non-Fullscreen");
}, vjs.ProgressControl = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t);
}
}), vjs.ProgressControl.prototype.options_ = {
children: {
seekBar: {}
}
}, vjs.ProgressControl.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-progress-control vjs-control"
});
}, vjs.SeekBar = vjs.Slider.extend({
init: function(e, t) {
vjs.Slider.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateARIAAttributes)), e.ready(vjs.bind(this, this.updateARIAAttributes));
}
}), vjs.SeekBar.prototype.options_ = {
children: {
loadProgressBar: {},
playProgressBar: {},
seekHandle: {}
},
barName: "playProgressBar",
handleName: "seekHandle"
}, vjs.SeekBar.prototype.playerEvent = "timeupdate", vjs.SeekBar.prototype.createEl = function() {
return vjs.Slider.prototype.createEl.call(this, "div", {
className: "vjs-progress-holder",
"aria-label": "video progress bar"
});
}, vjs.SeekBar.prototype.updateARIAAttributes = function() {
var e = this.player_.scrubbing ? this.player_.getCache().currentTime : this.player_.currentTime();
this.el_.setAttribute("aria-valuenow", vjs.round(this.getPercent() * 100, 2)), this.el_.setAttribute("aria-valuetext", vjs.formatTime(e, this.player_.duration()));
}, vjs.SeekBar.prototype.getPercent = function() {
return this.player_.currentTime() / this.player_.duration();
}, vjs.SeekBar.prototype.onMouseDown = function(e) {
vjs.Slider.prototype.onMouseDown.call(this, e), this.player_.scrubbing = !0, this.videoWasPlaying = !this.player_.paused(), this.player_.pause();
}, vjs.SeekBar.prototype.onMouseMove = function(e) {
var t = this.calculateDistance(e) * this.player_.duration();
t == this.player_.duration() && (t -= .1), this.player_.currentTime(t);
}, vjs.SeekBar.prototype.onMouseUp = function(e) {
vjs.Slider.prototype.onMouseUp.call(this, e), this.player_.scrubbing = !1, this.videoWasPlaying && this.player_.play();
}, vjs.SeekBar.prototype.stepForward = function() {
this.player_.currentTime(this.player_.currentTime() + 5);
}, vjs.SeekBar.prototype.stepBack = function() {
this.player_.currentTime(this.player_.currentTime() - 5);
}, vjs.LoadProgressBar = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.on("progress", vjs.bind(this, this.update));
}
}), vjs.LoadProgressBar.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-load-progress",
innerHTML: '<span class="vjs-control-text">Loaded: 0%</span>'
});
}, vjs.LoadProgressBar.prototype.update = function() {
this.el_.style && (this.el_.style.width = vjs.round(this.player_.bufferedPercent() * 100, 2) + "%");
}, vjs.PlayProgressBar = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t);
}
}), vjs.PlayProgressBar.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-play-progress",
innerHTML: '<span class="vjs-control-text">Progress: 0%</span>'
});
}, vjs.SeekHandle = vjs.SliderHandle.extend(), vjs.SeekHandle.prototype.defaultValue = "00:00", vjs.SeekHandle.prototype.createEl = function() {
return vjs.SliderHandle.prototype.createEl.call(this, "div", {
className: "vjs-seek-handle"
});
}, vjs.VolumeControl = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.tech && e.tech.features && e.tech.features.volumeControl === !1 && this.addClass("vjs-hidden"), e.on("loadstart", vjs.bind(this, function() {
e.tech.features && e.tech.features.volumeControl === !1 ? this.addClass("vjs-hidden") : this.removeClass("vjs-hidden");
}));
}
}), vjs.VolumeControl.prototype.options_ = {
children: {
volumeBar: {}
}
}, vjs.VolumeControl.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-volume-control vjs-control"
});
}, vjs.VolumeBar = vjs.Slider.extend({
init: function(e, t) {
vjs.Slider.call(this, e, t), e.on("volumechange", vjs.bind(this, this.updateARIAAttributes)), e.ready(vjs.bind(this, this.updateARIAAttributes)), setTimeout(vjs.bind(this, this.update), 0);
}
}), vjs.VolumeBar.prototype.updateARIAAttributes = function() {
this.el_.setAttribute("aria-valuenow", vjs.round(this.player_.volume() * 100, 2)), this.el_.setAttribute("aria-valuetext", vjs.round(this.player_.volume() * 100, 2) + "%");
}, vjs.VolumeBar.prototype.options_ = {
children: {
volumeLevel: {},
volumeHandle: {}
},
barName: "volumeLevel",
handleName: "volumeHandle"
}, vjs.VolumeBar.prototype.playerEvent = "volumechange", vjs.VolumeBar.prototype.createEl = function() {
return vjs.Slider.prototype.createEl.call(this, "div", {
className: "vjs-volume-bar",
"aria-label": "volume level"
});
}, vjs.VolumeBar.prototype.onMouseMove = function(e) {
this.player_.volume(this.calculateDistance(e));
}, vjs.VolumeBar.prototype.getPercent = function() {
return this.player_.muted() ? 0 : this.player_.volume();
}, vjs.VolumeBar.prototype.stepForward = function() {
this.player_.volume(this.player_.volume() + .1);
}, vjs.VolumeBar.prototype.stepBack = function() {
this.player_.volume(this.player_.volume() - .1);
}, vjs.VolumeLevel = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t);
}
}), vjs.VolumeLevel.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-volume-level",
innerHTML: '<span class="vjs-control-text"></span>'
});
}, vjs.VolumeHandle = vjs.SliderHandle.extend(), vjs.VolumeHandle.prototype.defaultValue = "00:00", vjs.VolumeHandle.prototype.createEl = function() {
return vjs.SliderHandle.prototype.createEl.call(this, "div", {
className: "vjs-volume-handle"
});
}, vjs.MuteToggle = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t), e.on("volumechange", vjs.bind(this, this.update)), e.tech && e.tech.features && e.tech.features.volumeControl === !1 && this.addClass("vjs-hidden"), e.on("loadstart", vjs.bind(this, function() {
e.tech.features && e.tech.features.volumeControl === !1 ? this.addClass("vjs-hidden") : this.removeClass("vjs-hidden");
}));
}
}), vjs.MuteToggle.prototype.createEl = function() {
return vjs.Button.prototype.createEl.call(this, "div", {
className: "vjs-mute-control vjs-control",
innerHTML: '<div><span class="vjs-control-text">Mute</span></div>'
});
}, vjs.MuteToggle.prototype.onClick = function() {
this.player_.muted(this.player_.muted() ? !1 : !0);
}, vjs.MuteToggle.prototype.update = function() {
var e = this.player_.volume(), t = 3;
e === 0 || this.player_.muted() ? t = 0 : e < .33 ? t = 1 : e < .67 && (t = 2), this.player_.muted() ? this.el_.children[0].children[0].innerHTML != "Unmute" && (this.el_.children[0].children[0].innerHTML = "Unmute") : this.el_.children[0].children[0].innerHTML != "Mute" && (this.el_.children[0].children[0].innerHTML = "Mute");
for (var n = 0; n < 4; n++) vjs.removeClass(this.el_, "vjs-vol-" + n);
vjs.addClass(this.el_, "vjs-vol-" + t);
}, vjs.VolumeMenuButton = vjs.MenuButton.extend({
init: function(e, t) {
vjs.MenuButton.call(this, e, t), e.on("volumechange", vjs.bind(this, this.update)), e.tech && e.tech.features && e.tech.features.volumeControl === !1 && this.addClass("vjs-hidden"), e.on("loadstart", vjs.bind(this, function() {
e.tech.features && e.tech.features.volumeControl === !1 ? this.addClass("vjs-hidden") : this.removeClass("vjs-hidden");
})), this.addClass("vjs-menu-button");
}
}), vjs.VolumeMenuButton.prototype.createMenu = function() {
var e = new vjs.Menu(this.player_, {
contentElType: "div"
}), t = new vjs.VolumeBar(this.player_, vjs.obj.merge({
vertical: !0
}, this.options_.volumeBar));
return e.addChild(t), e;
}, vjs.VolumeMenuButton.prototype.onClick = function() {
vjs.MuteToggle.prototype.onClick.call(this), vjs.MenuButton.prototype.onClick.call(this);
}, vjs.VolumeMenuButton.prototype.createEl = function() {
return vjs.Button.prototype.createEl.call(this, "div", {
className: "vjs-volume-menu-button vjs-menu-button vjs-control",
innerHTML: '<div><span class="vjs-control-text">Mute</span></div>'
});
}, vjs.VolumeMenuButton.prototype.update = vjs.MuteToggle.prototype.update, vjs.PosterImage = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t), (!e.poster() || !e.controls()) && this.hide(), e.on("play", vjs.bind(this, this.hide));
}
}), vjs.PosterImage.prototype.createEl = function() {
var e = vjs.createEl("div", {
className: "vjs-poster",
tabIndex: -1
}), t = this.player_.poster();
return t && ("backgroundSize" in e.style ? e.style.backgroundImage = 'url("' + t + '")' : e.appendChild(vjs.createEl("img", {
src: t
}))), e;
}, vjs.PosterImage.prototype.onClick = function() {
this.player_.play();
}, vjs.LoadingSpinner = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), e.on("canplay", vjs.bind(this, this.hide)), e.on("canplaythrough", vjs.bind(this, this.hide)), e.on("playing", vjs.bind(this, this.hide)), e.on("seeked", vjs.bind(this, this.hide)), e.on("seeking", vjs.bind(this, this.show)), e.on("seeked", vjs.bind(this, this.hide)), e.on("error", vjs.bind(this, this.show)), e.on("waiting", vjs.bind(this, this.show));
}
}), vjs.LoadingSpinner.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-loading-spinner"
});
}, vjs.BigPlayButton = vjs.Button.extend({
init: function(e, t) {
vjs.Button.call(this, e, t), e.controls() || this.hide(), e.on("play", vjs.bind(this, this.hide));
}
}), vjs.BigPlayButton.prototype.createEl = function() {
return vjs.Button.prototype.createEl.call(this, "div", {
className: "vjs-big-play-button",
innerHTML: "<span></span>",
"aria-label": "play video"
});
}, vjs.BigPlayButton.prototype.onClick = function() {
this.player_.play();
}, vjs.MediaTechController = vjs.Component.extend({
init: function(e, t, n) {
vjs.Component.call(this, e, t, n);
}
}), vjs.MediaTechController.prototype.onClick = function() {
return vjs.IS_ANDROID ? function() {} : function() {
this.player_.controls() && (this.player_.paused() ? this.player_.play() : this.player_.pause());
};
}(), vjs.MediaTechController.prototype.features = {
volumeControl: !0,
fullscreenResize: !1,
progressEvents: !1,
timeupdateEvents: !1
}, vjs.media = {}, vjs.media.ApiMethods = "play,pause,paused,currentTime,setCurrentTime,duration,buffered,volume,setVolume,muted,setMuted,width,height,supportsFullScreen,enterFullScreen,src,load,currentSrc,preload,setPreload,autoplay,setAutoplay,loop,setLoop,error,networkState,readyState,seeking,initialTime,startOffsetTime,played,seekable,ended,videoTracks,audioTracks,videoWidth,videoHeight,textTracks,defaultPlaybackRate,playbackRate,mediaGroup,controller,controls,defaultMuted".split(",");
function createMethod(e) {
return function() {
throw new Error('The "' + e + "\" method is not available on the playback technology's API");
};
}
for (var i = vjs.media.ApiMethods.length - 1; i >= 0; i--) {
var methodName = vjs.media.ApiMethods[i];
vjs.MediaTechController.prototype[vjs.media.ApiMethods[i]] = createMethod(methodName);
}
vjs.Html5 = vjs.MediaTechController.extend({
init: function(e, t, n) {
this.features.volumeControl = vjs.Html5.canControlVolume(), this.features.movingMediaElementInDOM = !vjs.IS_IOS, this.features.fullscreenResize = !0, vjs.MediaTechController.call(this, e, t, n);
var r = t.source;
r && this.el_.currentSrc == r.src ? e.trigger("loadstart") : r && (this.el_.src = r.src), e.ready(function() {
this.tag && this.options_.autoplay && this.paused() && (delete this.tag.poster, this.play());
}), this.on("click", this.onClick), this.setupTriggers(), this.triggerReady();
}
}), vjs.Html5.prototype.dispose = function() {
vjs.MediaTechController.prototype.dispose.call(this);
}, vjs.Html5.prototype.createEl = function() {
var e = this.player_, t = e.tag, n;
if (!t || this.features.movingMediaElementInDOM === !1) t ? (t.player = null, e.tag = null, e.el().removeChild(t), t = t.cloneNode(!1)) : t = vjs.createEl("video", {
id: e.id() + "_html5_api",
className: "vjs-tech"
}), t.player = e, vjs.insertFirst(t, e.el());
var r = [ "autoplay", "preload", "loop", "muted" ];
for (var i = r.length - 1; i >= 0; i--) {
var s = r[i];
e.options_[s] !== null && (t[s] = e.options_[s]);
}
return t;
}, vjs.Html5.prototype.setupTriggers = function() {
for (var e = vjs.Html5.Events.length - 1; e >= 0; e--) vjs.on(this.el_, vjs.Html5.Events[e], vjs.bind(this.player_, this.eventHandler));
}, vjs.Html5.prototype.eventHandler = function(e) {
this.trigger(e), e.stopPropagation();
}, vjs.Html5.prototype.play = function() {
this.el_.play();
}, vjs.Html5.prototype.pause = function() {
this.el_.pause();
}, vjs.Html5.prototype.paused = function() {
return this.el_.paused;
}, vjs.Html5.prototype.currentTime = function() {
return this.el_.currentTime;
}, vjs.Html5.prototype.setCurrentTime = function(e) {
try {
this.el_.currentTime = e;
} catch (t) {
vjs.log(t, "Video is not ready. (Video.js)");
}
}, vjs.Html5.prototype.duration = function() {
return this.el_.duration || 0;
}, vjs.Html5.prototype.buffered = function() {
return this.el_.buffered;
}, vjs.Html5.prototype.volume = function() {
return this.el_.volume;
}, vjs.Html5.prototype.setVolume = function(e) {
this.el_.volume = e;
}, vjs.Html5.prototype.muted = function() {
return this.el_.muted;
}, vjs.Html5.prototype.setMuted = function(e) {
this.el_.muted = e;
}, vjs.Html5.prototype.width = function() {
return this.el_.offsetWidth;
}, vjs.Html5.prototype.height = function() {
return this.el_.offsetHeight;
}, vjs.Html5.prototype.supportsFullScreen = function() {
if (typeof this.el_.webkitEnterFullScreen == "function") if (/Android/.test(vjs.USER_AGENT) || !/Chrome|Mac OS X 10.5/.test(vjs.USER_AGENT)) return !0;
return !1;
}, vjs.Html5.prototype.enterFullScreen = function() {
var e = this.el_;
e.paused && e.networkState <= e.HAVE_METADATA ? (this.el_.play(), setTimeout(function() {
e.pause(), e.webkitEnterFullScreen();
}, 0)) : e.webkitEnterFullScreen();
}, vjs.Html5.prototype.exitFullScreen = function() {
this.el_.webkitExitFullScreen();
}, vjs.Html5.prototype.src = function(e) {
this.el_.src = e;
}, vjs.Html5.prototype.load = function() {
this.el_.load();
}, vjs.Html5.prototype.currentSrc = function() {
return this.el_.currentSrc;
}, vjs.Html5.prototype.preload = function() {
return this.el_.preload;
}, vjs.Html5.prototype.setPreload = function(e) {
this.el_.preload = e;
}, vjs.Html5.prototype.autoplay = function() {
return this.el_.autoplay;
}, vjs.Html5.prototype.setAutoplay = function(e) {
this.el_.autoplay = e;
}, vjs.Html5.prototype.loop = function() {
return this.el_.loop;
}, vjs.Html5.prototype.setLoop = function(e) {
this.el_.loop = e;
}, vjs.Html5.prototype.error = function() {
return this.el_.error;
}, vjs.Html5.prototype.seeking = function() {
return this.el_.seeking;
}, vjs.Html5.prototype.ended = function() {
return this.el_.ended;
}, vjs.Html5.prototype.defaultMuted = function() {
return this.el_.defaultMuted;
}, vjs.Html5.isSupported = function() {
return !!vjs.TEST_VID.canPlayType;
}, vjs.Html5.canPlaySource = function(e) {
try {
return !!vjs.TEST_VID.canPlayType(e.type);
} catch (t) {
return "";
}
}, vjs.Html5.canControlVolume = function() {
var e = vjs.TEST_VID.volume;
return vjs.TEST_VID.volume = e / 2 + .1, e !== vjs.TEST_VID.volume;
}, vjs.Html5.Events = "loadstart,suspend,abort,error,emptied,stalled,loadedmetadata,loadeddata,canplay,canplaythrough,playing,waiting,seeking,seeked,ended,durationchange,timeupdate,progress,play,pause,ratechange,volumechange".split(","), vjs.IS_OLD_ANDROID && (document.createElement("video").constructor.prototype.canPlayType = function(e) {
return e && e.toLowerCase().indexOf("video/mp4") != -1 ? "maybe" : "";
}), vjs.Flash = vjs.MediaTechController.extend({
init: function(e, t, n) {
vjs.MediaTechController.call(this, e, t, n);
var r = t.source, i = t.parentEl, s = this.el_ = vjs.createEl("div", {
id: e.id() + "_temp_flash"
}), o = e.id() + "_flash_api", u = e.options_, a = vjs.obj.merge({
readyFunction: "videojs.Flash.onReady",
eventProxyFunction: "videojs.Flash.onEvent",
errorEventProxyFunction: "videojs.Flash.onError",
autoplay: u.autoplay,
preload: u.preload,
loop: u.loop,
muted: u.muted
}, t.flashVars), f = vjs.obj.merge({
wmode: "transparent",
bgcolor: "#000000"
}, t.params), l = vjs.obj.merge({
id: o,
name: o,
"class": "vjs-tech"
}, t.attributes);
r && (a.src = encodeURIComponent(vjs.getAbsoluteURL(r.src))), vjs.insertFirst(s, i), t.startTime && this.ready(function() {
this.load(), this.play(), this.currentTime(t.startTime);
});
if (t.iFrameMode === !0 && !vjs.IS_FIREFOX) {
var c = vjs.createEl("iframe", {
id: o + "_iframe",
name: o + "_iframe",
className: "vjs-tech",
scrolling: "no",
marginWidth: 0,
marginHeight: 0,
frameBorder: 0
});
a.readyFunction = "ready", a.eventProxyFunction = "events", a.errorEventProxyFunction = "errors", vjs.on(c, "load", vjs.bind(this, function() {
var e, n = c.contentWindow;
e = c.contentDocument ? c.contentDocument : c.contentWindow.document, e.write(vjs.Flash.getEmbedCode(t.swf, a, f, l)), n.player = this.player_, n.ready = vjs.bind(this.player_, function(t) {
var n = e.getElementById(t), r = this, i = r.tech;
i.el_ = n, vjs.on(n, "click", i.bind(i.onClick)), vjs.Flash.checkReady(i);
}), n.events = vjs.bind(this.player_, function(e, t) {
var n = this;
n && n.techName === "flash" && n.trigger(t);
}), n.errors = vjs.bind(this.player_, function(e, t) {
vjs.log("Flash Error", t);
});
})), s.parentNode.replaceChild(c, s);
} else vjs.Flash.embed(t.swf, s, a, f, l);
}
}), vjs.Flash.prototype.dispose = function() {
vjs.MediaTechController.prototype.dispose.call(this);
}, vjs.Flash.prototype.play = function() {
this.el_.vjs_play();
}, vjs.Flash.prototype.pause = function() {
this.el_.vjs_pause();
}, vjs.Flash.prototype.src = function(e) {
e = vjs.getAbsoluteURL(e), this.el_.vjs_src(e);
if (this.player_.autoplay()) {
var t = this;
setTimeout(function() {
t.play();
}, 0);
}
}, vjs.Flash.prototype.load = function() {
this.el_.vjs_load();
}, vjs.Flash.prototype.poster = function() {
this.el_.vjs_getProperty("poster");
}, vjs.Flash.prototype.buffered = function() {
return vjs.createTimeRange(0, this.el_.vjs_getProperty("buffered"));
}, vjs.Flash.prototype.supportsFullScreen = function() {
return !1;
}, vjs.Flash.prototype.enterFullScreen = function() {
return !1;
};
var api = vjs.Flash.prototype, readWrite = "preload,currentTime,defaultPlaybackRate,playbackRate,autoplay,loop,mediaGroup,controller,controls,volume,muted,defaultMuted".split(","), readOnly = "error,currentSrc,networkState,readyState,seeking,initialTime,duration,startOffsetTime,paused,played,seekable,ended,videoTracks,audioTracks,videoWidth,videoHeight,textTracks".split(","), createSetter = function(e) {
var t = e.charAt(0).toUpperCase() + e.slice(1);
api["set" + t] = function(t) {
return this.el_.vjs_setProperty(e, t);
};
}, createGetter = function(e) {
api[e] = function() {
return this.el_.vjs_getProperty(e);
};
};
(function() {
var e;
for (e = 0; e < readWrite.length; e++) createGetter(readWrite[e]), createSetter(readWrite[e]);
for (e = 0; e < readOnly.length; e++) createGetter(readOnly[e]);
})(), vjs.Flash.isSupported = function() {
return vjs.Flash.version()[0] >= 10;
}, vjs.Flash.canPlaySource = function(e) {
if (e.type in vjs.Flash.formats) return "maybe";
}, vjs.Flash.formats = {
"video/flv": "FLV",
"video/x-flv": "FLV",
"video/mp4": "MP4",
"video/m4v": "MP4"
}, vjs.Flash.onReady = function(e) {
var t = vjs.el(e), n = t.player || t.parentNode.player, r = n.tech;
t.player = n, r.el_ = t, r.on("click", r.onClick), vjs.Flash.checkReady(r);
}, vjs.Flash.checkReady = function(e) {
e.el().vjs_getProperty ? e.triggerReady() : setTimeout(function() {
vjs.Flash.checkReady(e);
}, 50);
}, vjs.Flash.onEvent = function(e, t) {
var n = vjs.el(e).player;
n.trigger(t);
}, vjs.Flash.onError = function(e, t) {
var n = vjs.el(e).player;
n.trigger("error"), vjs.log("Flash Error", t, e);
}, vjs.Flash.version = function() {
var e = "0,0,0";
try {
e = (new window.ActiveXObject("ShockwaveFlash.ShockwaveFlash")).GetVariable("$version").replace(/\D+/g, ",").match(/^,?(.+),?$/)[1];
} catch (t) {
try {
navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin && (e = (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]).description.replace(/\D+/g, ",").match(/^,?(.+),?$/)[1]);
} catch (n) {}
}
return e.split(",");
}, vjs.Flash.embed = function(e, t, n, r, i) {
var s = vjs.Flash.getEmbedCode(e, n, r, i), o = vjs.createEl("div", {
innerHTML: s
}).childNodes[0], u = t.parentNode;
t.parentNode.replaceChild(o, t);
var a = u.childNodes[0];
return setTimeout(function() {
a.style.display = "block";
}, 1e3), o;
}, vjs.Flash.getEmbedCode = function(e, t, n, r) {
var i = '<object type="application/x-shockwave-flash"', s = "", o = "", u = "";
return t && vjs.obj.each(t, function(e, t) {
s += e + "=" + t + "&amp;";
}), n = vjs.obj.merge({
movie: e,
flashvars: s,
allowScriptAccess: "always",
allowNetworking: "all"
}, n), vjs.obj.each(n, function(e, t) {
o += '<param name="' + e + '" value="' + t + '" />';
}), r = vjs.obj.merge({
data: e,
width: "100%",
height: "100%"
}, r), vjs.obj.each(r, function(e, t) {
u += e + '="' + t + '" ';
}), i + u + ">" + o + "</object>";
}, vjs.MediaLoader = vjs.Component.extend({
init: function(e, t, n) {
vjs.Component.call(this, e, t, n);
if (!e.options_.sources || e.options_.sources.length === 0) for (var r = 0, i = e.options_.techOrder; r < i.length; r++) {
var s = vjs.capitalize(i[r]), o = window.videojs[s];
if (o && o.isSupported()) {
e.loadTech(s);
break;
}
} else e.src(e.options_.sources);
}
}), vjs.Player.prototype.textTracks_, vjs.Player.prototype.textTracks = function() {
return this.textTracks_ = this.textTracks_ || [], this.textTracks_;
}, vjs.Player.prototype.addTextTrack = function(e, t, n, r) {
var i = this.textTracks_ = this.textTracks_ || [];
r = r || {}, r.kind = e, r.label = t, r.language = n;
var s = vjs.capitalize(e || "subtitles"), o = new window.videojs[s + "Track"](this, r);
return i.push(o), o;
}, vjs.Player.prototype.addTextTracks = function(e) {
var t;
for (var n = 0; n < e.length; n++) t = e[n], this.addTextTrack(t.kind, t.label, t.language, t);
return this;
}, vjs.Player.prototype.showTextTrack = function(e, t) {
var n = this.textTracks_, r = 0, i = n.length, s, o, u;
for (; r < i; r++) s = n[r], s.id() === e ? (s.show(), o = s) : t && s.kind() == t && s.mode() > 0 && s.disable();
return u = o ? o.kind() : t ? t : !1, u && this.trigger(u + "trackchange"), this;
}, vjs.TextTrack = vjs.Component.extend({
init: function(e, t) {
vjs.Component.call(this, e, t), this.id_ = t.id || "vjs_" + t.kind + "_" + t.language + "_" + vjs.guid++, this.src_ = t.src, this.dflt_ = t["default"] || t.dflt, this.title_ = t.title, this.language_ = t.srclang, this.label_ = t.label, this.cues_ = [], this.activeCues_ = [], this.readyState_ = 0, this.mode_ = 0, this.player_.on("fullscreenchange", vjs.bind(this, this.adjustFontSize));
}
}), vjs.TextTrack.prototype.kind_, vjs.TextTrack.prototype.kind = function() {
return this.kind_;
}, vjs.TextTrack.prototype.src_, vjs.TextTrack.prototype.src = function() {
return this.src_;
}, vjs.TextTrack.prototype.dflt_, vjs.TextTrack.prototype.dflt = function() {
return this.dflt_;
}, vjs.TextTrack.prototype.title_, vjs.TextTrack.prototype.title = function() {
return this.title_;
}, vjs.TextTrack.prototype.language_, vjs.TextTrack.prototype.language = function() {
return this.language_;
}, vjs.TextTrack.prototype.label_, vjs.TextTrack.prototype.label = function() {
return this.label_;
}, vjs.TextTrack.prototype.cues_, vjs.TextTrack.prototype.cues = function() {
return this.cues_;
}, vjs.TextTrack.prototype.activeCues_, vjs.TextTrack.prototype.activeCues = function() {
return this.activeCues_;
}, vjs.TextTrack.prototype.readyState_, vjs.TextTrack.prototype.readyState = function() {
return this.readyState_;
}, vjs.TextTrack.prototype.mode_, vjs.TextTrack.prototype.mode = function() {
return this.mode_;
}, vjs.TextTrack.prototype.adjustFontSize = function() {
this.player_.isFullScreen ? this.el_.style.fontSize = screen.width / this.player_.width() * 1.4 * 100 + "%" : this.el_.style.fontSize = "";
}, vjs.TextTrack.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-" + this.kind_ + " vjs-text-track"
});
}, vjs.TextTrack.prototype.show = function() {
this.activate(), this.mode_ = 2, vjs.Component.prototype.show.call(this);
}, vjs.TextTrack.prototype.hide = function() {
this.activate(), this.mode_ = 1, vjs.Component.prototype.hide.call(this);
}, vjs.TextTrack.prototype.disable = function() {
this.mode_ == 2 && this.hide(), this.deactivate(), this.mode_ = 0;
}, vjs.TextTrack.prototype.activate = function() {
this.readyState_ === 0 && this.load(), this.mode_ === 0 && (this.player_.on("timeupdate", vjs.bind(this, this.update, this.id_)), this.player_.on("ended", vjs.bind(this, this.reset, this.id_)), (this.kind_ === "captions" || this.kind_ === "subtitles") && this.player_.getChild("textTrackDisplay").addChild(this));
}, vjs.TextTrack.prototype.deactivate = function() {
this.player_.off("timeupdate", vjs.bind(this, this.update, this.id_)), this.player_.off("ended", vjs.bind(this, this.reset, this.id_)), this.reset(), this.player_.getChild("textTrackDisplay").removeChild(this);
}, vjs.TextTrack.prototype.load = function() {
this.readyState_ === 0 && (this.readyState_ = 1, vjs.get(this.src_, vjs.bind(this, this.parseCues), vjs.bind(this, this.onError)));
}, vjs.TextTrack.prototype.onError = function(e) {
this.error = e, this.readyState_ = 3, this.trigger("error");
}, vjs.TextTrack.prototype.parseCues = function(e) {
var t, n, r, i = e.split("\n"), s = "", o;
for (var u = 1, a = i.length; u < a; u++) {
s = vjs.trim(i[u]);
if (s) {
s.indexOf("-->") == -1 ? (o = s, s = vjs.trim(i[++u])) : o = this.cues_.length, t = {
id: o,
index: this.cues_.length
}, n = s.split(" --> "), t.startTime = this.parseCueTime(n[0]), t.endTime = this.parseCueTime(n[1]), r = [];
while (i[++u] && (s = vjs.trim(i[u]))) r.push(s);
t.text = r.join("<br/>"), this.cues_.push(t);
}
}
this.readyState_ = 2, this.trigger("loaded");
}, vjs.TextTrack.prototype.parseCueTime = function(e) {
var t = e.split(":"), n = 0, r, i, s, o, u;
return t.length == 3 ? (r = t[0], i = t[1], s = t[2]) : (r = 0, i = t[0], s = t[1]), s = s.split(/\s+/), o = s.splice(0, 1)[0], o = o.split(/\.|,/), u = parseFloat(o[1]), o = o[0], n += parseFloat(r) * 3600, n += parseFloat(i) * 60, n += parseFloat(o), u && (n += u / 1e3), n;
}, vjs.TextTrack.prototype.update = function() {
if (this.cues_.length > 0) {
var e = this.player_.currentTime();
if (this.prevChange === undefined || e < this.prevChange || this.nextChange <= e) {
var t = this.cues_, n = this.player_.duration(), r = 0, i = !1, s = [], o, u, a, f;
e >= this.nextChange || this.nextChange === undefined ? f = this.firstActiveIndex !== undefined ? this.firstActiveIndex : 0 : (i = !0, f = this.lastActiveIndex !== undefined ? this.lastActiveIndex : t.length - 1);
for (;;) {
a = t[f];
if (a.endTime <= e) r = Math.max(r, a.endTime), a.active && (a.active = !1); else if (e < a.startTime) {
n = Math.min(n, a.startTime), a.active && (a.active = !1);
if (!i) break;
} else i ? (s.splice(0, 0, a), u === undefined && (u = f), o = f) : (s.push(a), o === undefined && (o = f), u = f), n = Math.min(n, a.endTime), r = Math.max(r, a.startTime), a.active = !0;
if (i) {
if (f === 0) break;
f--;
} else {
if (f === t.length - 1) break;
f++;
}
}
this.activeCues_ = s, this.nextChange = n, this.prevChange = r, this.firstActiveIndex = o, this.lastActiveIndex = u, this.updateDisplay(), this.trigger("cuechange");
}
}
}, vjs.TextTrack.prototype.updateDisplay = function() {
var e = this.activeCues_, t = "", n = 0, r = e.length;
for (; n < r; n++) t += '<span class="vjs-tt-cue">' + e[n].text + "</span>";
this.el_.innerHTML = t;
}, vjs.TextTrack.prototype.reset = function() {
this.nextChange = 0, this.prevChange = this.player_.duration(), this.firstActiveIndex = 0, this.lastActiveIndex = 0;
}, vjs.CaptionsTrack = vjs.TextTrack.extend(), vjs.CaptionsTrack.prototype.kind_ = "captions", vjs.SubtitlesTrack = vjs.TextTrack.extend(), vjs.SubtitlesTrack.prototype.kind_ = "subtitles", vjs.ChaptersTrack = vjs.TextTrack.extend(), vjs.ChaptersTrack.prototype.kind_ = "chapters", vjs.TextTrackDisplay = vjs.Component.extend({
init: function(e, t, n) {
vjs.Component.call(this, e, t, n), e.options_.tracks && e.options_.tracks.length > 0 && this.player_.addTextTracks(e.options_.tracks);
}
}), vjs.TextTrackDisplay.prototype.createEl = function() {
return vjs.Component.prototype.createEl.call(this, "div", {
className: "vjs-text-track-display"
});
}, vjs.TextTrackMenuItem = vjs.MenuItem.extend({
init: function(e, t) {
var n = this.track = t.track;
t.label = n.label(), t.selected = n.dflt(), vjs.MenuItem.call(this, e, t), this.player_.on(n.kind() + "trackchange", vjs.bind(this, this.update));
}
}), vjs.TextTrackMenuItem.prototype.onClick = function() {
vjs.MenuItem.prototype.onClick.call(this), this.player_.showTextTrack(this.track.id_, this.track.kind());
}, vjs.TextTrackMenuItem.prototype.update = function() {
this.selected(this.track.mode() == 2);
}, vjs.OffTextTrackMenuItem = vjs.TextTrackMenuItem.extend({
init: function(e, t) {
t.track = {
kind: function() {
return t.kind;
},
player: e,
label: function() {
return t.kind + " off";
},
dflt: function() {
return !1;
},
mode: function() {
return !1;
}
}, vjs.TextTrackMenuItem.call(this, e, t), this.selected(!0);
}
}), vjs.OffTextTrackMenuItem.prototype.onClick = function() {
vjs.TextTrackMenuItem.prototype.onClick.call(this), this.player_.showTextTrack(this.track.id_, this.track.kind());
}, vjs.OffTextTrackMenuItem.prototype.update = function() {
var e = this.player_.textTracks(), t = 0, n = e.length, r, i = !0;
for (; t < n; t++) r = e[t], r.kind() == this.track.kind() && r.mode() == 2 && (i = !1);
this.selected(i);
}, vjs.TextTrackButton = vjs.MenuButton.extend({
init: function(e, t) {
vjs.MenuButton.call(this, e, t), this.items.length <= 1 && this.hide();
}
}), vjs.TextTrackButton.prototype.createItems = function() {
var e = [], t;
e.push(new vjs.OffTextTrackMenuItem(this.player_, {
kind: this.kind_
}));
for (var n = 0; n < this.player_.textTracks().length; n++) t = this.player_.textTracks()[n], t.kind() === this.kind_ && e.push(new vjs.TextTrackMenuItem(this.player_, {
track: t
}));
return e;
}, vjs.CaptionsButton = vjs.TextTrackButton.extend({
init: function(e, t, n) {
vjs.TextTrackButton.call(this, e, t, n), this.el_.setAttribute("aria-label", "Captions Menu");
}
}), vjs.CaptionsButton.prototype.kind_ = "captions", vjs.CaptionsButton.prototype.buttonText = "Captions", vjs.CaptionsButton.prototype.className = "vjs-captions-button", vjs.SubtitlesButton = vjs.TextTrackButton.extend({
init: function(e, t, n) {
vjs.TextTrackButton.call(this, e, t, n), this.el_.setAttribute("aria-label", "Subtitles Menu");
}
}), vjs.SubtitlesButton.prototype.kind_ = "subtitles", vjs.SubtitlesButton.prototype.buttonText = "Subtitles", vjs.SubtitlesButton.prototype.className = "vjs-subtitles-button", vjs.ChaptersButton = vjs.TextTrackButton.extend({
init: function(e, t, n) {
vjs.TextTrackButton.call(this, e, t, n), this.el_.setAttribute("aria-label", "Chapters Menu");
}
}), vjs.ChaptersButton.prototype.kind_ = "chapters", vjs.ChaptersButton.prototype.buttonText = "Chapters", vjs.ChaptersButton.prototype.className = "vjs-chapters-button", vjs.ChaptersButton.prototype.createItems = function() {
var e = [], t;
for (var n = 0; n < this.player_.textTracks().length; n++) t = this.player_.textTracks()[n], t.kind() === this.kind_ && e.push(new vjs.TextTrackMenuItem(this.player_, {
track: t
}));
return e;
}, vjs.ChaptersButton.prototype.createMenu = function() {
var e = this.player_.textTracks(), t = 0, n = e.length, r, i, s = this.items = [];
for (; t < n; t++) {
r = e[t];
if (r.kind() == this.kind_ && r.dflt()) {
if (r.readyState() < 2) {
this.chaptersTrack = r, r.on("loaded", vjs.bind(this, this.createMenu));
return;
}
i = r;
break;
}
}
var o = this.menu = new vjs.Menu(this.player_);
o.el_.appendChild(vjs.createEl("li", {
className: "vjs-menu-title",
innerHTML: vjs.capitalize(this.kind_),
tabindex: -1
}));
if (i) {
var u = i.cues_, a, f;
t = 0, n = u.length;
for (; t < n; t++) a = u[t], f = new vjs.ChaptersTrackMenuItem(this.player_, {
track: i,
cue: a
}), s.push(f), o.addChild(f);
}
return this.items.length > 0 && this.show(), o;
}, vjs.ChaptersTrackMenuItem = vjs.MenuItem.extend({
init: function(e, t) {
var n = this.track = t.track, r = this.cue = t.cue, i = e.currentTime();
t.label = r.text, t.selected = r.startTime <= i && i < r.endTime, vjs.MenuItem.call(this, e, t), n.on("cuechange", vjs.bind(this, this.update));
}
}), vjs.ChaptersTrackMenuItem.prototype.onClick = function() {
vjs.MenuItem.prototype.onClick.call(this), this.player_.currentTime(this.cue.startTime), this.update(this.cue.startTime);
}, vjs.ChaptersTrackMenuItem.prototype.update = function() {
var e = this.cue, t = this.player_.currentTime();
this.selected(e.startTime <= t && t < e.endTime);
}, vjs.obj.merge(vjs.ControlBar.prototype.options_.children, {
subtitlesButton: {},
captionsButton: {},
chaptersButton: {}
}), vjs.JSON;
if (typeof window.JSON != "undefined" && window.JSON.parse === "function") vjs.JSON = window.JSON; else {
vjs.JSON = {};
var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
vjs.JSON.parse = function(text, reviver) {
function walk(e, t) {
var n, r, i = e[t];
if (i && typeof i == "object") for (n in i) Object.prototype.hasOwnProperty.call(i, n) && (r = walk(i, n), r !== undefined ? i[n] = r : delete i[n]);
return reviver.call(e, t, i);
}
var j;
text = String(text), cx.lastIndex = 0, cx.test(text) && (text = text.replace(cx, function(e) {
return "\\u" + ("0000" + e.charCodeAt(0).toString(16)).slice(-4);
}));
if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) return j = eval("(" + text + ")"), typeof reviver == "function" ? walk({
"": j
}, "") : j;
throw new SyntaxError("JSON.parse(): invalid or malformed JSON data");
};
}
return vjs.autoSetup = function() {
var e, t, n, r = document.getElementsByTagName("video");
if (r && r.length > 0) for (var i = 0, s = r.length; i < s; i++) {
t = r[i];
if (!t || !t.getAttribute) {
vjs.autoSetupTimeout(1);
break;
}
t.player === undefined && (e = t.getAttribute("data-setup"), e !== null && (e = vjs.JSON.parse(e || "{}"), n = videojs(t, e)));
} else vjs.windowLoaded || vjs.autoSetupTimeout(1);
}, vjs.autoSetupTimeout = function(e) {
setTimeout(vjs.autoSetup, e);
}, vjs.one(window, "load", function() {
vjs.windowLoaded = !0;
}), vjs.autoSetupTimeout(1), vjs.plugin = function(e, t) {
vjs.Player.prototype[e] = t;
}, videojs;
} catch (e) {
wx.jslog({
src: "biz_web/lib/video.js"
}, e);
}
});define("tpl/media/img.html.js",[],function(){
return'<div class="appmsgSendedItem simple_img">\n    <a class="title_wrp" href="/cgi-bin/getimgdata?token={token}&msgid={msgid}&mode=large&source={source}&fileId={id}&ow={ow}" target="_blank">\n        <!-- <img class="icon" src="/cgi-bin/getimgdata?token={token}&msgid={msgid}&mode=small&source={source}&fileId={id}&ow={ow}"> -->\n        <span class="js_media_img icon cover" style="background-image:url(/cgi-bin/getimgdata?token={token}&msgid={msgid}&mode=small&source={source}&fileId={id}&ow={ow});"></span>\n        <span class="title">[图片]</span>\n    </a>\n</div>\n';
});define("tpl/media/qqmusicaudio.html.js",[],function(){
return'<div class="qqmusic_audio " id="wxAudioBox{id}" data-aid="{id}">\n    <a class="audio_switch" href="javascript:;"  onclick=\'return false;\' title="点击播放">\n        <i class="icon_qqmusic"></i>\n    </a>\n</div>\n';
});define("tpl/media/audio.html.js",[],function(){
return'<div class="audio_msg" id="wxAudioBox{id}" data-aid="{id}" data-fid="{file_id}" data-source="{source}">\n    <div class="icon_audio_wrp"><span class="icon_audio_msg"></span></div>\n    <div class="audio_content">\n        <div class="audio_title">{title}</div>\n        <div class="audio_length">{play_length}</div>\n        {if showTime==true}<div class="audio_date">{update_time}</div>{/if}\n    </div>\n</div>\n';
});define("biz_web/lib/soundmanager2.js",[],function(){
"use strict";
function e(e,n){
function o(e){
return pt.preferFlash&&rt&&!pt.ignoreFlash&&pt.flash[e]!==t&&pt.flash[e];
}
function i(e){
return function(t){
var n,o=this._s;
return o&&o._a?n=e.call(this,t):(pt._wD(o&&o.id?o.id+": Ignoring "+t.type:wt+"Ignoring "+t.type),
n=null),n;
};
}
this.setupOptions={
url:e||null,
flashVersion:8,
debugMode:!1,
debugFlash:!1,
useConsole:!1,
consoleOnly:!0,
waitForWindowLoad:!1,
bgColor:"#ffffff",
useHighPerformance:!1,
flashPollingInterval:null,
html5PollingInterval:null,
flashLoadTimeout:1e3,
wmode:null,
allowScriptAccess:"always",
useFlashBlock:!1,
useHTML5Audio:!0,
html5Test:/^(probably|maybe)$/i,
preferFlash:!0,
noSWFCache:!1,
idPrefix:"sound"
},this.defaultOptions={
autoLoad:!1,
autoPlay:!1,
from:null,
loops:1,
onid3:null,
onload:null,
whileloading:null,
onplay:null,
onpause:null,
onresume:null,
whileplaying:null,
onposition:null,
onstop:null,
onfailure:null,
onfinish:null,
multiShot:!0,
multiShotEvents:!1,
position:null,
pan:0,
stream:!0,
to:null,
type:null,
usePolicyFile:!1,
volume:100
},this.flash9Options={
isMovieStar:null,
usePeakData:!1,
useWaveformData:!1,
useEQData:!1,
onbufferchange:null,
ondataerror:null
},this.movieStarOptions={
bufferTime:3,
serverURL:null,
onconnect:null,
duration:null
},this.audioFormats={
mp3:{
type:['audio/mpeg; codecs="mp3"',"audio/mpeg","audio/mp3","audio/MPA","audio/mpa-robust"],
required:!0
},
mp4:{
related:["aac","m4a","m4b"],
type:['audio/mp4; codecs="mp4a.40.2"',"audio/aac","audio/x-m4a","audio/MP4A-LATM","audio/mpeg4-generic"],
required:!1
},
ogg:{
type:["audio/ogg; codecs=vorbis"],
required:!1
},
opus:{
type:["audio/ogg; codecs=opus","audio/opus"],
required:!1
},
wav:{
type:['audio/wav; codecs="1"',"audio/wav","audio/wave","audio/x-wav"],
required:!1
}
},this.movieID="sm2-container",this.id=n||"sm2movie",this.debugID="soundmanager-debug",
this.debugURLParam=/([#?&])debug=1/i,this.versionNumber="V2.97a.20130512",this.version=null,
this.movieURL=null,this.altURL=null,this.swfLoaded=!1,this.enabled=!1,this.oMC=null,
this.sounds={},this.soundIDs=[],this.muted=!1,this.didFlashBlock=!1,this.filePattern=null,
this.filePatterns={
flash8:/\.mp3(\?.*)?$/i,
flash9:/\.mp3(\?.*)?$/i
},this.features={
buffering:!1,
peakData:!1,
waveformData:!1,
eqData:!1,
movieStar:!1
},this.sandbox={
type:null,
types:{
remote:"remote (domain-based) rules",
localWithFile:"local with file access (no internet access)",
localWithNetwork:"local with network (internet access only, no local access)",
localTrusted:"local, trusted (local+internet access)"
},
description:null,
noRemote:null,
noLocal:null
},this.html5={
usingFlash:null
},this.flash={},this.html5Only=!1,this.ignoreFlash=!1;
var a,s,r,l,u,d,f,h,c,p,m,_,g,y,w,v,b,O,D,M,L,T,P,S,F,I,H,E,A,k,C,x,R,N,U,B,W,j,q,V,Q,$,K,J,X,z,G,Z,Y,et,tt,nt,ot,it,at,st,rt,lt,ut,dt,ft,ht,ct,pt=this,mt=null,_t=null,gt="soundManager",yt=gt+": ",wt="HTML5::",vt=navigator.userAgent,bt=window.location.href.toString(),Ot=document,Dt=[],Mt=!0,Lt=!1,Tt=!1,Pt=!1,St=!1,Ft=!1,It=0,Ht=["log","info","warn","error"],Et=8,At=null,kt=null,Ct=!1,xt=!1,Rt=0,Nt=null,Ut=[],Bt=null,Wt=Array.prototype.slice,jt=!1,qt=0,Vt=vt.match(/(ipad|iphone|ipod)/i),Qt=vt.match(/android/i),$t=vt.match(/msie/i),Kt=vt.match(/webkit/i),Jt=vt.match(/safari/i)&&!vt.match(/chrome/i),Xt=vt.match(/opera/i),zt=vt.match(/firefox/i),Gt=vt.match(/(mobile|pre\/|xoom)/i)||Vt||Qt,Zt=!bt.match(/usehtml5audio/i)&&!bt.match(/sm2\-ignorebadua/i)&&Jt&&!vt.match(/silk/i)&&vt.match(/OS X 10_6_([3-7])/i),Yt=window.console!==t&&console.log!==t,en=Ot.hasFocus!==t?Ot.hasFocus():null,tn=Jt&&(Ot.hasFocus===t||!Ot.hasFocus()),nn=!tn,on=/(mp3|mp4|mpa|m4a|m4b)/i,an=1e3,sn="about:blank",rn=Ot.location?Ot.location.protocol.match(/http/i):null,ln=rn?"":"http://",un=/^\s*audio\/(?:x-)?(?:mpeg4|aac|flv|mov|mp4||m4v|m4a|m4b|mp4v|3gp|3g2)\s*(?:$|;)/i,dn=["mpeg4","aac","flv","mov","mp4","m4v","f4v","m4a","m4b","mp4v","3gp","3g2"],fn=new RegExp("\\.("+dn.join("|")+")(\\?.*)?$","i");
this.mimePattern=/^\s*audio\/(?:x-)?(?:mp(?:eg|3))\s*(?:$|;)/i,this.useAltURL=!rn,
W={
swfBox:"sm2-object-box",
swfDefault:"movieContainer",
swfError:"swf_error",
swfTimedout:"swf_timedout",
swfLoaded:"swf_loaded",
swfUnblocked:"swf_unblocked",
sm2Debug:"sm2_debug",
highPerf:"high_performance",
flashDebug:"flash_debug"
},this.hasHTML5=function(){
try{
return Audio!==t&&(Xt&&opera!==t&&opera.version()<10?new Audio(null):new Audio).canPlayType!==t;
}catch(e){
return!1;
}
}(),this.setup=function(e){
var n=!pt.url;
return e!==t&&Pt&&Bt&&pt.ok()&&(e.flashVersion!==t||e.url!==t||e.html5Test!==t)&&Q(N("setupLate")),
m(e),e&&(n&&F&&e.url!==t&&pt.beginDelayedInit(),F||e.url===t||"complete"!==Ot.readyState||setTimeout(P,1)),
pt;
},this.ok=function(){
return Bt?Pt&&!St:pt.useHTML5Audio&&pt.hasHTML5;
},this.supported=this.ok,this.getMovie=function(e){
return s(e)||Ot[e]||window[e];
},this.createSound=function(e,n){
function o(){
return r=q(r),pt.sounds[r.id]=new a(r),pt.soundIDs.push(r.id),pt.sounds[r.id];
}
var i,s,r,l=null;
if(i=gt+".createSound(): ",s=i+N(Pt?"notOK":"notReady"),!Pt||!pt.ok())return Q(s),
!1;
if(n!==t&&(e={
id:e,
url:n
}),r=p(e),r.url=z(r.url),void 0===r.id&&(r.id=pt.setupOptions.idPrefix+qt++),r.id.toString().charAt(0).match(/^[0-9]$/)&&pt._wD(i+N("badID",r.id),2),
pt._wD(i+r.id+(r.url?" ("+r.url+")":""),1),$(r.id,!0))return pt._wD(i+r.id+" exists",1),
pt.sounds[r.id];
if(Y(r))l=o(),pt._wD(r.id+": Using HTML5"),l._setup_html5(r);else{
if(pt.html5Only)return pt._wD(r.id+": No HTML5 support for this sound, and no Flash. Exiting."),
o();
if(pt.html5.usingFlash&&r.url&&r.url.match(/data\:/i))return pt._wD(r.id+": data: URIs not supported via Flash. Exiting."),
o();
d>8&&(null===r.isMovieStar&&(r.isMovieStar=!!(r.serverURL||(r.type?r.type.match(un):!1)||r.url&&r.url.match(fn))),
r.isMovieStar&&(pt._wD(i+"using MovieStar handling"),r.loops>1&&h("noNSLoop"))),
r=V(r,i),l=o(),8===d?_t._createSound(r.id,r.loops||1,r.usePolicyFile):(_t._createSound(r.id,r.url,r.usePeakData,r.useWaveformData,r.useEQData,r.isMovieStar,r.isMovieStar?r.bufferTime:!1,r.loops||1,r.serverURL,r.duration||null,r.autoPlay,!0,r.autoLoad,r.usePolicyFile),
r.serverURL||(l.connected=!0,r.onconnect&&r.onconnect.apply(l))),r.serverURL||!r.autoLoad&&!r.autoPlay||l.load(r);
}
return!r.serverURL&&r.autoPlay&&l.play(),l;
},this.destroySound=function(e,t){
if(!$(e))return!1;
var n,o=pt.sounds[e];
for(o._iO={},o.stop(),o.unload(),n=0;n<pt.soundIDs.length;n++)if(pt.soundIDs[n]===e){
pt.soundIDs.splice(n,1);
break;
}
return t||o.destruct(!0),o=null,delete pt.sounds[e],!0;
},this.load=function(e,t){
return $(e)?pt.sounds[e].load(t):!1;
},this.unload=function(e){
return $(e)?pt.sounds[e].unload():!1;
},this.onPosition=function(e,t,n,o){
return $(e)?pt.sounds[e].onposition(t,n,o):!1;
},this.onposition=this.onPosition,this.clearOnPosition=function(e,t,n){
return $(e)?pt.sounds[e].clearOnPosition(t,n):!1;
},this.play=function(e,t){
var n=null,o=t&&!(t instanceof Object);
if(!Pt||!pt.ok())return Q(gt+".play(): "+N(Pt?"notOK":"notReady")),!1;
if($(e,o))o&&(t={
url:t
});else{
if(!o)return!1;
o&&(t={
url:t
}),t&&t.url&&(pt._wD(gt+'.play(): Attempting to create "'+e+'"',1),t.id=e,n=pt.createSound(t).play());
}
return null===n&&(n=pt.sounds[e].play(t)),n;
},this.start=this.play,this.setPosition=function(e,t){
return $(e)?pt.sounds[e].setPosition(t):!1;
},this.stop=function(e){
return $(e)?(pt._wD(gt+".stop("+e+")",1),pt.sounds[e].stop()):!1;
},this.stopAll=function(){
var e;
pt._wD(gt+".stopAll()",1);
for(e in pt.sounds)pt.sounds.hasOwnProperty(e)&&pt.sounds[e].stop();
},this.pause=function(e){
return $(e)?pt.sounds[e].pause():!1;
},this.pauseAll=function(){
var e;
for(e=pt.soundIDs.length-1;e>=0;e--)pt.sounds[pt.soundIDs[e]].pause();
},this.resume=function(e){
return $(e)?pt.sounds[e].resume():!1;
},this.resumeAll=function(){
var e;
for(e=pt.soundIDs.length-1;e>=0;e--)pt.sounds[pt.soundIDs[e]].resume();
},this.togglePause=function(e){
return $(e)?pt.sounds[e].togglePause():!1;
},this.setPan=function(e,t){
return $(e)?pt.sounds[e].setPan(t):!1;
},this.setVolume=function(e,t){
return $(e)?pt.sounds[e].setVolume(t):!1;
},this.mute=function(e){
var t=0;
if(e instanceof String&&(e=null),e)return $(e)?(pt._wD(gt+'.mute(): Muting "'+e+'"'),
pt.sounds[e].mute()):!1;
for(pt._wD(gt+".mute(): Muting all sounds"),t=pt.soundIDs.length-1;t>=0;t--)pt.sounds[pt.soundIDs[t]].mute();
return pt.muted=!0,!0;
},this.muteAll=function(){
pt.mute();
},this.unmute=function(e){
var t;
if(e instanceof String&&(e=null),e)return $(e)?(pt._wD(gt+'.unmute(): Unmuting "'+e+'"'),
pt.sounds[e].unmute()):!1;
for(pt._wD(gt+".unmute(): Unmuting all sounds"),t=pt.soundIDs.length-1;t>=0;t--)pt.sounds[pt.soundIDs[t]].unmute();
return pt.muted=!1,!0;
},this.unmuteAll=function(){
pt.unmute();
},this.toggleMute=function(e){
return $(e)?pt.sounds[e].toggleMute():!1;
},this.getMemoryUse=function(){
var e=0;
return _t&&8!==d&&(e=parseInt(_t._getMemoryUse(),10)),e;
},this.disable=function(e){
var n;
if(e===t&&(e=!1),St)return!1;
for(St=!0,h("shutdown",1),n=pt.soundIDs.length-1;n>=0;n--)C(pt.sounds[pt.soundIDs[n]]);
return c(e),at.remove(window,"load",w),!0;
},this.canPlayMIME=function(e){
var t;
return pt.hasHTML5&&(t=et({
type:e
})),!t&&Bt&&(t=e&&pt.ok()?!!((d>8?e.match(un):null)||e.match(pt.mimePattern)):null),
t;
},this.canPlayURL=function(e){
var t;
return pt.hasHTML5&&(t=et({
url:e
})),!t&&Bt&&(t=e&&pt.ok()?!!e.match(pt.filePattern):null),t;
},this.canPlayLink=function(e){
return e.type!==t&&e.type&&pt.canPlayMIME(e.type)?!0:pt.canPlayURL(e.href);
},this.getSoundById=function(e,t){
if(!e)return null;
var n=pt.sounds[e];
return n||t||pt._wD(gt+'.getSoundById(): Sound "'+e+'" not found.',2),n;
},this.onready=function(e,t){
var n="onready",o=!1;
if("function"!=typeof e)throw N("needFunction",n);
return Pt&&pt._wD(N("queue",n)),t||(t=window),g(n,e,t),y(),o=!0,o;
},this.ontimeout=function(e,t){
var n="ontimeout",o=!1;
if("function"!=typeof e)throw N("needFunction",n);
return Pt&&pt._wD(N("queue",n)),t||(t=window),g(n,e,t),y({
type:n
}),o=!0,o;
},this._writeDebug=function(e,n){
var o,i,a="soundmanager-debug";
return pt.debugMode?Yt&&pt.useConsole&&(n&&"object"==typeof n?console.log(e,n):Ht[n]!==t?console[Ht[n]](e):console.log(e),
pt.consoleOnly)?!0:(o=s(a))?(i=Ot.createElement("div"),++It%2===0&&(i.className="sm2-alt"),
n=n===t?0:parseInt(n,10),i.appendChild(Ot.createTextNode(e)),n&&(n>=2&&(i.style.fontWeight="bold"),
3===n&&(i.style.color="#ff3333")),o.insertBefore(i,o.firstChild),o=null,!0):!1:!1;
},-1!==bt.indexOf("sm2-debug=alert")&&(this._writeDebug=function(e){
window.alert(e);
}),this._wD=this._writeDebug,this._debug=function(){
var e,t;
for(h("currentObj",1),e=0,t=pt.soundIDs.length;t>e;e++)pt.sounds[pt.soundIDs[e]]._debug();
},this.reboot=function(e,t){
pt.soundIDs.length&&pt._wD("Destroying "+pt.soundIDs.length+" SMSound object"+(1!==pt.soundIDs.length?"s":"")+"...");
var n,o,i;
for(n=pt.soundIDs.length-1;n>=0;n--)pt.sounds[pt.soundIDs[n]].destruct();
if(_t)try{
$t&&(kt=_t.innerHTML),At=_t.parentNode.removeChild(_t);
}catch(a){
h("badRemove",2);
}
if(kt=At=Bt=_t=null,pt.enabled=F=Pt=Ct=xt=Lt=Tt=St=jt=pt.swfLoaded=!1,pt.soundIDs=[],
pt.sounds={},qt=0,e)Dt=[];else for(n in Dt)if(Dt.hasOwnProperty(n))for(o=0,i=Dt[n].length;i>o;o++)Dt[n][o].fired=!1;
return t||pt._wD(gt+": Rebooting..."),pt.html5={
usingFlash:null
},pt.flash={},pt.html5Only=!1,pt.ignoreFlash=!1,window.setTimeout(function(){
T(),t||pt.beginDelayedInit();
},20),pt;
},this.reset=function(){
return h("reset"),pt.reboot(!0,!0);
},this.getMoviePercent=function(){
return _t&&"PercentLoaded"in _t?_t.PercentLoaded():null;
},this.beginDelayedInit=function(){
Ft=!0,P(),setTimeout(function(){
return xt?!1:(H(),L(),xt=!0,!0);
},20),v();
},this.destruct=function(){
pt._wD(gt+".destruct()"),pt.disable(!0);
},a=function(e){
var n,o,i,a,s,r,l,u,c,m,_=this,g=!1,y=[],w=0,v=null;
c={
duration:null,
time:null
},this.id=e.id,this.sID=this.id,this.url=e.url,this.options=p(e),this.instanceOptions=this.options,
this._iO=this.instanceOptions,this.pan=this.options.pan,this.volume=this.options.volume,
this.isHTML5=!1,this._a=null,m=this.url?!1:!0,this.id3={},this._debug=function(){
pt._wD(_.id+": Merged options:",_.options);
},this.load=function(e){
var n,o=null;
if(e!==t?_._iO=p(e,_.options):(e=_.options,_._iO=e,v&&v!==_.url&&(h("manURL"),_._iO.url=_.url,
_.url=null)),_._iO.url||(_._iO.url=_.url),_._iO.url=z(_._iO.url),_.instanceOptions=_._iO,
n=_._iO,pt._wD(_.id+": load ("+n.url+")"),!n.url&&!_.url)return pt._wD(_.id+": load(): url is unassigned. Exiting.",2),
_;
if(_.isHTML5||8!==d||_.url||n.autoPlay||pt._wD(_.id+": Flash 8 load() limitation: Wait for onload() before calling play().",1),
n.url===_.url&&0!==_.readyState&&2!==_.readyState)return h("onURL",1),3===_.readyState&&n.onload&&ct(_,function(){
n.onload.apply(_,[!!_.duration]);
}),_;
if(_.loaded=!1,_.readyState=1,_.playState=0,_.id3={},Y(n))o=_._setup_html5(n),o._called_load?pt._wD(_.id+": Ignoring request to load again"):(_._html5_canplay=!1,
_.url!==n.url&&(pt._wD(h("manURL")+": "+n.url),_._a.src=n.url,_.setPosition(0)),
_._a.autobuffer="auto",_._a.preload="auto",_._a._called_load=!0,n.autoPlay&&_.play());else{
if(pt.html5Only)return pt._wD(_.id+": No flash support. Exiting."),_;
if(_._iO.url&&_._iO.url.match(/data\:/i))return pt._wD(_.id+": data: URIs not supported via Flash. Exiting."),
_;
try{
_.isHTML5=!1,_._iO=V(q(n)),n=_._iO,8===d?_t._load(_.id,n.url,n.stream,n.autoPlay,n.usePolicyFile):_t._load(_.id,n.url,!!n.stream,!!n.autoPlay,n.loops||1,!!n.autoLoad,n.usePolicyFile);
}catch(i){
h("smError",2),f("onload",!1),E({
type:"SMSOUND_LOAD_JS_EXCEPTION",
fatal:!0
});
}
}
return _.url=n.url,_;
},this.unload=function(){
return 0!==_.readyState&&(pt._wD(_.id+": unload()"),_.isHTML5?(a(),_._a&&(_._a.pause(),
v=nt(_._a))):8===d?_t._unload(_.id,sn):_t._unload(_.id),n()),_;
},this.destruct=function(e){
pt._wD(_.id+": Destruct"),_.isHTML5?(a(),_._a&&(_._a.pause(),nt(_._a),jt||i(),_._a._s=null,
_._a=null)):(_._iO.onfailure=null,_t._destroySound(_.id)),e||pt.destroySound(_.id,!0);
},this.play=function(e,n){
var o,i,a,l,f,h,c,y=!0,w=null;
if(o=_.id+": play(): ",n=n===t?!0:n,e||(e={}),_.url&&(_._iO.url=_.url),_._iO=p(_._iO,_.options),
_._iO=p(e,_._iO),_._iO.url=z(_._iO.url),_.instanceOptions=_._iO,!_.isHTML5&&_._iO.serverURL&&!_.connected)return _.getAutoPlay()||(pt._wD(o+" Netstream not connected yet - setting autoPlay"),
_.setAutoPlay(!0)),_;
if(Y(_._iO)&&(_._setup_html5(_._iO),s()),1!==_.playState||_.paused||(i=_._iO.multiShot,
i?pt._wD(o+"Already playing (multi-shot)",1):(pt._wD(o+"Already playing (one-shot)",1),
_.isHTML5&&_.setPosition(_._iO.position),w=_)),null!==w)return w;
if(e.url&&e.url!==_.url&&(_.readyState||_.isHTML5||8!==d||!m?_.load(_._iO):m=!1),
_.loaded?pt._wD(o.substr(0,o.lastIndexOf(":"))):0===_.readyState?(pt._wD(o+"Attempting to load"),
_.isHTML5||pt.html5Only?_.isHTML5?_.load(_._iO):(pt._wD(o+"Unsupported type. Exiting."),
w=_):(_._iO.autoPlay=!0,_.load(_._iO)),_.instanceOptions=_._iO):2===_.readyState?(pt._wD(o+"Could not load - exiting",2),
w=_):pt._wD(o+"Loading - attempting to play..."),null!==w)return w;
if(!_.isHTML5&&9===d&&_.position>0&&_.position===_.duration&&(pt._wD(o+"Sound at end, resetting to position:0"),
e.position=0),_.paused&&_.position>=0&&(!_._iO.serverURL||_.position>0))pt._wD(o+"Resuming from paused state",1),
_.resume();else{
if(_._iO=p(e,_._iO),null!==_._iO.from&&null!==_._iO.to&&0===_.instanceCount&&0===_.playState&&!_._iO.serverURL){
if(l=function(){
_._iO=p(e,_._iO),_.play(_._iO);
},_.isHTML5&&!_._html5_canplay?(pt._wD(o+"Beginning load for from/to case"),_.load({
oncanplay:l
}),w=!1):_.isHTML5||_.loaded||_.readyState&&2===_.readyState||(pt._wD(o+"Preloading for from/to case"),
_.load({
onload:l
}),w=!1),null!==w)return w;
_._iO=u();
}
(!_.instanceCount||_._iO.multiShotEvents||_.isHTML5&&_._iO.multiShot&&!jt||!_.isHTML5&&d>8&&!_.getAutoPlay())&&_.instanceCount++,
_._iO.onposition&&0===_.playState&&r(_),_.playState=1,_.paused=!1,_.position=_._iO.position===t||isNaN(_._iO.position)?0:_._iO.position,
_.isHTML5||(_._iO=V(q(_._iO))),_._iO.onplay&&n&&(_._iO.onplay.apply(_),g=!0),_.setVolume(_._iO.volume,!0),
_.setPan(_._iO.pan,!0),_.isHTML5?_.instanceCount<2?(s(),a=_._setup_html5(),_.setPosition(_._iO.position),
a.play()):(pt._wD(_.id+": Cloning Audio() for instance #"+_.instanceCount+"..."),
f=new Audio(_._iO.url),h=function(){
at.remove(f,"onended",h),_._onfinish(_),nt(f),f=null;
},c=function(){
at.remove(f,"canplay",c);
try{
f.currentTime=_._iO.position/an;
}catch(e){
Q(_.id+": multiShot play() failed to apply position of "+_._iO.position/an);
}
f.play();
},at.add(f,"ended",h),_._iO.position?at.add(f,"canplay",c):f.play()):(y=_t._start(_.id,_._iO.loops||1,9===d?_.position:_.position/an,_._iO.multiShot||!1),
9!==d||y||(pt._wD(o+"No sound hardware, or 32-sound ceiling hit",2),_._iO.onplayerror&&_._iO.onplayerror.apply(_)));
}
return _;
},this.start=this.play,this.stop=function(e){
var t,n=_._iO;
return 1===_.playState&&(pt._wD(_.id+": stop()"),_._onbufferchange(0),_._resetOnPosition(0),
_.paused=!1,_.isHTML5||(_.playState=0),l(),n.to&&_.clearOnPosition(n.to),_.isHTML5?_._a&&(t=_.position,
_.setPosition(0),_.position=t,_._a.pause(),_.playState=0,_._onTimer(),a()):(_t._stop(_.id,e),
n.serverURL&&_.unload()),_.instanceCount=0,_._iO={},n.onstop&&n.onstop.apply(_)),
_;
},this.setAutoPlay=function(e){
pt._wD(_.id+": Autoplay turned "+(e?"on":"off")),_._iO.autoPlay=e,_.isHTML5||(_t._setAutoPlay(_.id,e),
e&&(_.instanceCount||1!==_.readyState||(_.instanceCount++,pt._wD(_.id+": Incremented instance count to "+_.instanceCount))));
},this.getAutoPlay=function(){
return _._iO.autoPlay;
},this.setPosition=function(e){
e===t&&(e=0);
var n,o,i=_.isHTML5?Math.max(e,0):Math.min(_.duration||_._iO.duration,Math.max(e,0));
if(_.position=i,o=_.position/an,_._resetOnPosition(_.position),_._iO.position=i,
_.isHTML5){
if(_._a){
if(_._html5_canplay){
if(_._a.currentTime!==o){
pt._wD(_.id+": setPosition("+o+")");
try{
_._a.currentTime=o,(0===_.playState||_.paused)&&_._a.pause();
}catch(a){
pt._wD(_.id+": setPosition("+o+") failed: "+a.message,2);
}
}
}else if(o)return pt._wD(_.id+": setPosition("+o+"): Cannot seek yet, sound not ready",2),
_;
_.paused&&_._onTimer(!0);
}
}else n=9===d?_.position:o,_.readyState&&2!==_.readyState&&_t._setPosition(_.id,n,_.paused||!_.playState,_._iO.multiShot);
return _;
},this.pause=function(e){
return _.paused||0===_.playState&&1!==_.readyState?_:(pt._wD(_.id+": pause()"),_.paused=!0,
_.isHTML5?(_._setup_html5().pause(),a()):(e||e===t)&&_t._pause(_.id,_._iO.multiShot),
_._iO.onpause&&_._iO.onpause.apply(_),_);
},this.resume=function(){
var e=_._iO;
return _.paused?(pt._wD(_.id+": resume()"),_.paused=!1,_.playState=1,_.isHTML5?(_._setup_html5().play(),
s()):(e.isMovieStar&&!e.serverURL&&_.setPosition(_.position),_t._pause(_.id,e.multiShot)),
!g&&e.onplay?(e.onplay.apply(_),g=!0):e.onresume&&e.onresume.apply(_),_):_;
},this.togglePause=function(){
return pt._wD(_.id+": togglePause()"),0===_.playState?(_.play({
position:9!==d||_.isHTML5?_.position/an:_.position
}),_):(_.paused?_.resume():_.pause(),_);
},this.setPan=function(e,n){
return e===t&&(e=0),n===t&&(n=!1),_.isHTML5||_t._setPan(_.id,e),_._iO.pan=e,n||(_.pan=e,
_.options.pan=e),_;
},this.setVolume=function(e,n){
return e===t&&(e=100),n===t&&(n=!1),_.isHTML5?_._a&&(_._a.volume=Math.max(0,Math.min(1,e/100))):_t._setVolume(_.id,pt.muted&&!_.muted||_.muted?0:e),
_._iO.volume=e,n||(_.volume=e,_.options.volume=e),_;
},this.mute=function(){
return _.muted=!0,_.isHTML5?_._a&&(_._a.muted=!0):_t._setVolume(_.id,0),_;
},this.unmute=function(){
_.muted=!1;
var e=_._iO.volume!==t;
return _.isHTML5?_._a&&(_._a.muted=!1):_t._setVolume(_.id,e?_._iO.volume:_.options.volume),
_;
},this.toggleMute=function(){
return _.muted?_.unmute():_.mute();
},this.onPosition=function(e,n,o){
return y.push({
position:parseInt(e,10),
method:n,
scope:o!==t?o:_,
fired:!1
}),_;
},this.onposition=this.onPosition,this.clearOnPosition=function(e,t){
var n;
if(e=parseInt(e,10),isNaN(e))return!1;
for(n=0;n<y.length;n++)e===y[n].position&&(t&&t!==y[n].method||(y[n].fired&&w--,
y.splice(n,1)));
},this._processOnPosition=function(){
var e,t,n=y.length;
if(!n||!_.playState||w>=n)return!1;
for(e=n-1;e>=0;e--)t=y[e],!t.fired&&_.position>=t.position&&(t.fired=!0,w++,t.method.apply(t.scope,[t.position]));
return!0;
},this._resetOnPosition=function(e){
var t,n,o=y.length;
if(!o)return!1;
for(t=o-1;t>=0;t--)n=y[t],n.fired&&e<=n.position&&(n.fired=!1,w--);
return!0;
},u=function(){
var e,t,n=_._iO,o=n.from,i=n.to;
return t=function(){
pt._wD(_.id+': "To" time of '+i+" reached."),_.clearOnPosition(i,t),_.stop();
},e=function(){
pt._wD(_.id+': Playing "from" '+o),null===i||isNaN(i)||_.onPosition(i,t);
},null===o||isNaN(o)||(n.position=o,n.multiShot=!1,e()),n;
},r=function(){
var e,t=_._iO.onposition;
if(t)for(e in t)t.hasOwnProperty(e)&&_.onPosition(parseInt(e,10),t[e]);
},l=function(){
var e,t=_._iO.onposition;
if(t)for(e in t)t.hasOwnProperty(e)&&_.clearOnPosition(parseInt(e,10));
},s=function(){
_.isHTML5&&K(_);
},a=function(){
_.isHTML5&&J(_);
},n=function(e){
e||(y=[],w=0),g=!1,_._hasTimer=null,_._a=null,_._html5_canplay=!1,_.bytesLoaded=null,
_.bytesTotal=null,_.duration=_._iO&&_._iO.duration?_._iO.duration:null,_.durationEstimate=null,
_.buffered=[],_.eqData=[],_.eqData.left=[],_.eqData.right=[],_.failures=0,_.isBuffering=!1,
_.instanceOptions={},_.instanceCount=0,_.loaded=!1,_.metadata={},_.readyState=0,
_.muted=!1,_.paused=!1,_.peakData={
left:0,
right:0
},_.waveformData={
left:[],
right:[]
},_.playState=0,_.position=null,_.id3={};
},n(),this._onTimer=function(e){
var t,n,o=!1,i={};
return _._hasTimer||e?(_._a&&(e||(_.playState>0||1===_.readyState)&&!_.paused)&&(t=_._get_html5_duration(),
t!==c.duration&&(c.duration=t,_.duration=t,o=!0),_.durationEstimate=_.duration,n=_._a.currentTime*an||0,
n!==c.time&&(c.time=n,o=!0),(o||e)&&_._whileplaying(n,i,i,i,i)),o):void 0;
},this._get_html5_duration=function(){
var e=_._iO,t=_._a&&_._a.duration?_._a.duration*an:e&&e.duration?e.duration:null,n=t&&!isNaN(t)&&1/0!==t?t:null;
return n;
},this._apply_loop=function(e,t){
!e.loop&&t>1&&pt._wD("Note: Native HTML5 looping is infinite.",1),e.loop=t>1?"loop":"";
},this._setup_html5=function(e){
var t,i=p(_._iO,e),a=jt?mt:_._a,s=decodeURI(i.url);
if(jt?s===decodeURI(st)&&(t=!0):s===decodeURI(v)&&(t=!0),a){
if(a._s)if(jt)a._s&&a._s.playState&&!t&&a._s.stop();else if(!jt&&s===decodeURI(v))return _._apply_loop(a,i.loops),
a;
t||(n(!1),a.src=i.url,_.url=i.url,v=i.url,st=i.url,a._called_load=!1);
}else _._a=i.autoLoad||i.autoPlay?new Audio(i.url):Xt&&opera.version()<10?new Audio(null):new Audio,
a=_._a,a._called_load=!1,jt&&(mt=a);
return _.isHTML5=!0,_._a=a,a._s=_,o(),_._apply_loop(a,i.loops),i.autoLoad||i.autoPlay?_.load():(a.autobuffer=!1,
a.preload="auto"),a;
},o=function(){
function e(e,t,n){
return _._a?_._a.addEventListener(e,t,n||!1):null;
}
if(_._a._added_events)return!1;
var t;
_._a._added_events=!0;
for(t in dt)dt.hasOwnProperty(t)&&e(t,dt[t]);
return!0;
},i=function(){
function e(e,t,n){
return _._a?_._a.removeEventListener(e,t,n||!1):null;
}
var t;
pt._wD(_.id+": Removing event listeners"),_._a._added_events=!1;
for(t in dt)dt.hasOwnProperty(t)&&e(t,dt[t]);
},this._onload=function(e){
var t,n=!!e||!_.isHTML5&&8===d&&_.duration;
return t=_.id+": ",pt._wD(t+(n?"onload()":"Failed to load / invalid sound?"+(_.duration?" -":" Zero-length duration reported.")+" ("+_.url+")"),n?1:2),
n||_.isHTML5||(pt.sandbox.noRemote===!0&&pt._wD(t+N("noNet"),1),pt.sandbox.noLocal===!0&&pt._wD(t+N("noLocal"),1)),
_.loaded=n,_.readyState=n?3:2,_._onbufferchange(0),_._iO.onload&&ct(_,function(){
_._iO.onload.apply(_,[n]);
}),!0;
},this._onbufferchange=function(e){
return 0===_.playState?!1:e&&_.isBuffering||!e&&!_.isBuffering?!1:(_.isBuffering=1===e,
_._iO.onbufferchange&&(pt._wD(_.id+": Buffer state change: "+e),_._iO.onbufferchange.apply(_)),
!0);
},this._onsuspend=function(){
return _._iO.onsuspend&&(pt._wD(_.id+": Playback suspended"),_._iO.onsuspend.apply(_)),
!0;
},this._onfailure=function(e,t,n){
_.failures++,pt._wD(_.id+": Failures = "+_.failures),_._iO.onfailure&&1===_.failures?_._iO.onfailure(_,e,t,n):pt._wD(_.id+": Ignoring failure");
},this._onfinish=function(){
var e=_._iO.onfinish;
_._onbufferchange(0),_._resetOnPosition(0),_.instanceCount&&(_.instanceCount--,_.instanceCount||(l(),
_.playState=0,_.paused=!1,_.instanceCount=0,_.instanceOptions={},_._iO={},a(),_.isHTML5&&(_.position=0)),
(!_.instanceCount||_._iO.multiShotEvents)&&e&&(pt._wD(_.id+": onfinish()"),ct(_,function(){
e.apply(_);
})));
},this._whileloading=function(e,t,n,o){
var i=_._iO;
_.bytesLoaded=e,_.bytesTotal=t,_.duration=Math.floor(n),_.bufferLength=o,_.durationEstimate=_.isHTML5||i.isMovieStar?_.duration:i.duration?_.duration>i.duration?_.duration:i.duration:parseInt(_.bytesTotal/_.bytesLoaded*_.duration,10),
_.isHTML5||(_.buffered=[{
start:0,
end:_.duration
}]),(3!==_.readyState||_.isHTML5)&&i.whileloading&&i.whileloading.apply(_);
},this._whileplaying=function(e,n,o,i,a){
var s,r=_._iO;
return isNaN(e)||null===e?!1:(_.position=Math.max(0,e),_._processOnPosition(),!_.isHTML5&&d>8&&(r.usePeakData&&n!==t&&n&&(_.peakData={
left:n.leftPeak,
right:n.rightPeak
}),r.useWaveformData&&o!==t&&o&&(_.waveformData={
left:o.split(","),
right:i.split(",")
}),r.useEQData&&a!==t&&a&&a.leftEQ&&(s=a.leftEQ.split(","),_.eqData=s,_.eqData.left=s,
a.rightEQ!==t&&a.rightEQ&&(_.eqData.right=a.rightEQ.split(",")))),1===_.playState&&(_.isHTML5||8!==d||_.position||!_.isBuffering||_._onbufferchange(0),
r.whileplaying&&r.whileplaying.apply(_)),!0);
},this._oncaptiondata=function(e){
pt._wD(_.id+": Caption data received."),_.captiondata=e,_._iO.oncaptiondata&&_._iO.oncaptiondata.apply(_,[e]);
},this._onmetadata=function(e,t){
pt._wD(_.id+": Metadata received.");
var n,o,i={};
for(n=0,o=e.length;o>n;n++)i[e[n]]=t[n];
_.metadata=i,_._iO.onmetadata&&_._iO.onmetadata.apply(_);
},this._onid3=function(e,t){
pt._wD(_.id+": ID3 data received.");
var n,o,i=[];
for(n=0,o=e.length;o>n;n++)i[e[n]]=t[n];
_.id3=p(_.id3,i),_._iO.onid3&&_._iO.onid3.apply(_);
},this._onconnect=function(e){
e=1===e,pt._wD(_.id+": "+(e?"Connected.":"Failed to connect? - "+_.url),e?1:2),_.connected=e,
e&&(_.failures=0,$(_.id)&&(_.getAutoPlay()?_.play(t,_.getAutoPlay()):_._iO.autoLoad&&_.load()),
_._iO.onconnect&&_._iO.onconnect.apply(_,[e]));
},this._ondataerror=function(e){
_.playState>0&&(pt._wD(_.id+": Data error: "+e),_._iO.ondataerror&&_._iO.ondataerror.apply(_));
},this._debug();
},I=function(){
return Ot.body||Ot._docElement||Ot.getElementsByTagName("div")[0];
},s=function(e){
return Ot.getElementById(e);
},p=function(e,n){
var o,i,a=e||{};
o=n===t?pt.defaultOptions:n;
for(i in o)o.hasOwnProperty(i)&&a[i]===t&&(a[i]="object"!=typeof o[i]||null===o[i]?o[i]:p(a[i],o[i]));
return a;
},ct=function(e,t){
e.isHTML5||8!==d?t():window.setTimeout(t,0);
},_={
onready:1,
ontimeout:1,
defaultOptions:1,
flash9Options:1,
movieStarOptions:1
},m=function(e,n){
var o,i=!0,a=n!==t,s=pt.setupOptions,r=_;
if(e===t){
i=[];
for(o in s)s.hasOwnProperty(o)&&i.push(o);
for(o in r)r.hasOwnProperty(o)&&i.push("object"==typeof pt[o]?o+": {...}":pt[o]instanceof Function?o+": function() {...}":o);
return pt._wD(N("setup",i.join(", "))),!1;
}
for(o in e)if(e.hasOwnProperty(o))if("object"!=typeof e[o]||null===e[o]||e[o]instanceof Array||e[o]instanceof RegExp)a&&r[n]!==t?pt[n][o]=e[o]:s[o]!==t?(pt.setupOptions[o]=e[o],
pt[o]=e[o]):r[o]===t?(Q(N(pt[o]===t?"setupUndef":"setupError",o),2),i=!1):pt[o]instanceof Function?pt[o].apply(pt,e[o]instanceof Array?e[o]:[e[o]]):pt[o]=e[o];else{
if(r[o]!==t)return m(e[o],o);
Q(N(pt[o]===t?"setupUndef":"setupError",o),2),i=!1;
}
return i;
},at=function(){
function e(e){
var t=Wt.call(e),n=t.length;
return i?(t[1]="on"+t[1],n>3&&t.pop()):3===n&&t.push(!1),t;
}
function t(e,t){
var n=e.shift(),o=[a[t]];
i?n[o](e[0],e[1]):n[o].apply(n,e);
}
function n(){
t(e(arguments),"add");
}
function o(){
t(e(arguments),"remove");
}
var i=window.attachEvent,a={
add:i?"attachEvent":"addEventListener",
remove:i?"detachEvent":"removeEventListener"
};
return{
add:n,
remove:o
};
}(),dt={
abort:i(function(){
pt._wD(this._s.id+": abort");
}),
canplay:i(function(){
var e,n=this._s;
if(n._html5_canplay)return!0;
if(n._html5_canplay=!0,pt._wD(n.id+": canplay"),n._onbufferchange(0),e=n._iO.position===t||isNaN(n._iO.position)?null:n._iO.position/an,
n.position&&this.currentTime!==e){
pt._wD(n.id+": canplay: Setting position to "+e);
try{
this.currentTime=e;
}catch(o){
pt._wD(n.id+": canplay: Setting position of "+e+" failed: "+o.message,2);
}
}
n._iO._oncanplay&&n._iO._oncanplay();
}),
canplaythrough:i(function(){
var e=this._s;
e.loaded||(e._onbufferchange(0),e._whileloading(e.bytesLoaded,e.bytesTotal,e._get_html5_duration()),
e._onload(!0));
}),
ended:i(function(){
var e=this._s;
pt._wD(e.id+": ended"),e._onfinish();
}),
error:i(function(){
pt._wD(this._s.id+": HTML5 error, code "+this.error.code),this._s._onload(!1);
}),
loadeddata:i(function(){
var e=this._s;
pt._wD(e.id+": loadeddata"),e._loaded||Jt||(e.duration=e._get_html5_duration());
}),
loadedmetadata:i(function(){
pt._wD(this._s.id+": loadedmetadata");
}),
loadstart:i(function(){
pt._wD(this._s.id+": loadstart"),this._s._onbufferchange(1);
}),
play:i(function(){
this._s._onbufferchange(0);
}),
playing:i(function(){
pt._wD(this._s.id+": playing"),this._s._onbufferchange(0);
}),
progress:i(function(e){
var t,n,o,i=this._s,a=0,s="progress"===e.type,r=e.target.buffered,l=e.loaded||0,u=e.total||1;
if(i.buffered=[],r&&r.length){
for(t=0,n=r.length;n>t;t++)i.buffered.push({
start:r.start(t)*an,
end:r.end(t)*an
});
if(a=(r.end(0)-r.start(0))*an,l=Math.min(1,a/(e.target.duration*an)),s&&r.length>1){
for(o=[],n=r.length,t=0;n>t;t++)o.push(e.target.buffered.start(t)*an+"-"+e.target.buffered.end(t)*an);
pt._wD(this._s.id+": progress, timeRanges: "+o.join(", "));
}
s&&!isNaN(l)&&pt._wD(this._s.id+": progress, "+Math.floor(100*l)+"% loaded");
}
isNaN(l)||(i._onbufferchange(0),i._whileloading(l,u,i._get_html5_duration()),l&&u&&l===u&&dt.canplaythrough.call(this,e));
}),
ratechange:i(function(){
pt._wD(this._s.id+": ratechange");
}),
suspend:i(function(e){
var t=this._s;
pt._wD(this._s.id+": suspend"),dt.progress.call(this,e),t._onsuspend();
}),
stalled:i(function(){
pt._wD(this._s.id+": stalled");
}),
timeupdate:i(function(){
this._s._onTimer();
}),
waiting:i(function(){
var e=this._s;
pt._wD(this._s.id+": waiting"),e._onbufferchange(1);
})
},Y=function(e){
var t;
return t=e&&(e.type||e.url||e.serverURL)?e.serverURL||e.type&&o(e.type)?!1:e.type?et({
type:e.type
}):et({
url:e.url
})||pt.html5Only||e.url.match(/data\:/i):!1;
},nt=function(e){
var t;
return e&&(t=Jt&&!Vt?null:zt?sn:null,e.removeAttribute("src"),void 0!==e._called_unload&&(e._called_load=!1)),
jt&&(st=null),t;
},et=function(e){
if(!pt.useHTML5Audio||!pt.hasHTML5)return!1;
var n,i,a,s,r=e.url||null,l=e.type||null,u=pt.audioFormats;
if(l&&pt.html5[l]!==t)return pt.html5[l]&&!o(l);
if(!tt){
tt=[];
for(s in u)u.hasOwnProperty(s)&&(tt.push(s),u[s].related&&(tt=tt.concat(u[s].related)));
tt=new RegExp("\\.("+tt.join("|")+")(\\?.*)?$","i");
}
return a=r?r.toLowerCase().match(tt):null,a&&a.length?a=a[1]:l?(i=l.indexOf(";"),
a=(-1!==i?l.substr(0,i):l).substr(6)):n=!1,a&&pt.html5[a]!==t?n=pt.html5[a]&&!o(a):(l="audio/"+a,
n=pt.html5.canPlayType({
type:l
}),pt.html5[a]=n,n=n&&pt.html5[l]&&!o(l)),n;
},it=function(){
function e(e){
var t,n,o,i=!1,a=!1;
if(!s||"function"!=typeof s.canPlayType)return i;
if(e instanceof Array){
for(n=0,o=e.length;o>n;n++)(pt.html5[e[n]]||s.canPlayType(e[n]).match(pt.html5Test))&&(a=!0,
pt.html5[e[n]]=!0,pt.flash[e[n]]=!!e[n].match(on));
i=a;
}else t=s&&"function"==typeof s.canPlayType?s.canPlayType(e):!1,i=!(!t||!t.match(pt.html5Test));
return i;
}
if(!pt.useHTML5Audio||!pt.hasHTML5)return pt.html5.usingFlash=!0,Bt=!0,!1;
var n,o,i,a,s=Audio!==t?Xt&&opera.version()<10?new Audio(null):new Audio:null,r={};
i=pt.audioFormats;
for(n in i)if(i.hasOwnProperty(n)&&(o="audio/"+n,r[n]=e(i[n].type),r[o]=r[n],n.match(on)?(pt.flash[n]=!0,
pt.flash[o]=!0):(pt.flash[n]=!1,pt.flash[o]=!1),i[n]&&i[n].related))for(a=i[n].related.length-1;a>=0;a--)r["audio/"+i[n].related[a]]=r[n],
pt.html5[i[n].related[a]]=r[n],pt.flash[i[n].related[a]]=r[n];
return r.canPlayType=s?e:null,pt.html5=p(pt.html5,r),pt.html5.usingFlash=Z(),Bt=pt.html5.usingFlash,
!0;
},M={
notReady:"Unavailable - wait until onready() has fired.",
notOK:"Audio support is not available.",
domError:gt+"exception caught while appending SWF to DOM.",
spcWmode:"Removing wmode, preventing known SWF loading issue(s)",
swf404:yt+"Verify that %s is a valid path.",
tryDebug:"Try "+gt+".debugFlash = true for more security details (output goes to SWF.)",
checkSWF:"See SWF output for more debug info.",
localFail:yt+"Non-HTTP page ("+Ot.location.protocol+" URL?) Review Flash player security settings for this special case:\nhttp://www.macromedia.com/support/documentation/en/flashplayer/help/settings_manager04.html\nMay need to add/allow path, eg. c:/sm2/ or /users/me/sm2/",
waitFocus:yt+"Special case: Waiting for SWF to load with window focus...",
waitForever:yt+"Waiting indefinitely for Flash (will recover if unblocked)...",
waitSWF:yt+"Waiting for 100% SWF load...",
needFunction:yt+"Function object expected for %s",
badID:'Sound ID "%s" should be a string, starting with a non-numeric character',
currentObj:yt+"_debug(): Current sound objects",
waitOnload:yt+"Waiting for window.onload()",
docLoaded:yt+"Document already loaded",
onload:yt+"initComplete(): calling soundManager.onload()",
onloadOK:gt+".onload() complete",
didInit:yt+"init(): Already called?",
secNote:"Flash security note: Network/internet URLs will not load due to security restrictions. Access can be configured via Flash Player Global Security Settings Page: http://www.macromedia.com/support/documentation/en/flashplayer/help/settings_manager04.html",
badRemove:yt+"Failed to remove Flash node.",
shutdown:gt+".disable(): Shutting down",
queue:yt+"Queueing %s handler",
smError:"SMSound.load(): Exception: JS-Flash communication failed, or JS error.",
fbTimeout:"No flash response, applying ."+W.swfTimedout+" CSS...",
fbLoaded:"Flash loaded",
fbHandler:yt+"flashBlockHandler()",
manURL:"SMSound.load(): Using manually-assigned URL",
onURL:gt+".load(): current URL already assigned.",
badFV:gt+'.flashVersion must be 8 or 9. "%s" is invalid. Reverting to %s.',
as2loop:"Note: Setting stream:false so looping can work (flash 8 limitation)",
noNSLoop:"Note: Looping not implemented for MovieStar formats",
needfl9:"Note: Switching to flash 9, required for MP4 formats.",
mfTimeout:"Setting flashLoadTimeout = 0 (infinite) for off-screen, mobile flash case",
needFlash:yt+"Fatal error: Flash is needed to play some required formats, but is not available.",
gotFocus:yt+"Got window focus.",
policy:"Enabling usePolicyFile for data access",
setup:gt+".setup(): allowed parameters: %s",
setupError:gt+'.setup(): "%s" cannot be assigned with this method.',
setupUndef:gt+'.setup(): Could not find option "%s"',
setupLate:gt+".setup(): url, flashVersion and html5Test property changes will not take effect until reboot().",
noURL:yt+"Flash URL required. Call soundManager.setup({url:...}) to get started.",
sm2Loaded:"SoundManager 2: Ready.",
reset:gt+".reset(): Removing event callbacks",
mobileUA:"Mobile UA detected, preferring HTML5 by default.",
globalHTML5:"Using singleton HTML5 Audio() pattern for this device."
},N=function(){
var e,t,n=Wt.call(arguments),o=n.shift(),i=M&&M[o]?M[o]:"";
if(i&&n&&n.length)for(e=0,t=n.length;t>e;e++)i=i.replace("%s",n[e]);
return i;
},q=function(e){
return 8===d&&e.loops>1&&e.stream&&(h("as2loop"),e.stream=!1),e;
},V=function(e,t){
return e&&!e.usePolicyFile&&(e.onid3||e.usePeakData||e.useWaveformData||e.useEQData)&&(pt._wD((t||"")+N("policy")),
e.usePolicyFile=!0),e;
},Q=function(e){
Yt&&console.warn!==t?console.warn(e):pt._wD(e);
},r=function(){
return!1;
},C=function(e){
var t;
for(t in e)e.hasOwnProperty(t)&&"function"==typeof e[t]&&(e[t]=r);
t=null;
},x=function(e){
e===t&&(e=!1),(St||e)&&pt.disable(e);
},R=function(e){
var t,n=null;
if(e)if(e.match(/\.swf(\?.*)?$/i)){
if(n=e.substr(e.toLowerCase().lastIndexOf(".swf?")+4))return e;
}else e.lastIndexOf("/")!==e.length-1&&(e+="/");
return t=(e&&-1!==e.lastIndexOf("/")?e.substr(0,e.lastIndexOf("/")+1):"./")+pt.movieURL,
pt.noSWFCache&&(t+="?ts="+(new Date).getTime()),t;
},O=function(){
d=parseInt(pt.flashVersion,10),8!==d&&9!==d&&(pt._wD(N("badFV",d,Et)),pt.flashVersion=d=Et);
var e=pt.debugMode||pt.debugFlash?"_debug.swf":".swf";
pt.useHTML5Audio&&!pt.html5Only&&pt.audioFormats.mp4.required&&9>d&&(pt._wD(N("needfl9")),
pt.flashVersion=d=9),pt.version=pt.versionNumber+(pt.html5Only?" (HTML5-only mode)":9===d?" (AS3/Flash 9)":" (AS2/Flash 8)"),
d>8?(pt.defaultOptions=p(pt.defaultOptions,pt.flash9Options),pt.features.buffering=!0,
pt.defaultOptions=p(pt.defaultOptions,pt.movieStarOptions),pt.filePatterns.flash9=new RegExp("\\.(mp3|"+dn.join("|")+")(\\?.*)?$","i"),
pt.features.movieStar=!0):pt.features.movieStar=!1,pt.filePattern=pt.filePatterns[8!==d?"flash9":"flash8"],
pt.movieURL=(8===d?"soundmanager2.swf":"soundmanager2_flash9.swf").replace(".swf",e),
pt.features.peakData=pt.features.waveformData=pt.features.eqData=d>8;
},A=function(e,t){
return _t?void _t._setPolling(e,t):!1;
},k=function(){
if(pt.debugURLParam.test(bt)&&(pt.debugMode=!0),s(pt.debugID))return!1;
var e,t,n,o,i;
if(!(!pt.debugMode||s(pt.debugID)||Yt&&pt.useConsole&&pt.consoleOnly)){
e=Ot.createElement("div"),e.id=pt.debugID+"-toggle",o={
position:"fixed",
bottom:"0px",
right:"0px",
width:"1.2em",
height:"1.2em",
lineHeight:"1.2em",
margin:"2px",
textAlign:"center",
border:"1px solid #999",
cursor:"pointer",
background:"#fff",
color:"#333",
zIndex:10001
},e.appendChild(Ot.createTextNode("-")),e.onclick=j,e.title="Toggle SM2 debug console",
vt.match(/msie 6/i)&&(e.style.position="absolute",e.style.cursor="hand");
for(i in o)o.hasOwnProperty(i)&&(e.style[i]=o[i]);
if(t=Ot.createElement("div"),t.id=pt.debugID,t.style.display=pt.debugMode?"block":"none",
pt.debugMode&&!s(e.id)){
try{
n=I(),n.appendChild(e);
}catch(a){
throw new Error(N("domError")+" \n"+a.toString());
}
n.appendChild(t);
}
}
n=null;
},$=this.getSoundById,h=function(e,t){
return e?pt._wD(N(e),t):"";
},j=function(){
var e=s(pt.debugID),t=s(pt.debugID+"-toggle");
return e?(Mt?(t.innerHTML="+",e.style.display="none"):(t.innerHTML="-",e.style.display="block"),
void(Mt=!Mt)):!1;
},f=function(e,n,o){
if(window.sm2Debugger!==t)try{
sm2Debugger.handleEvent(e,n,o);
}catch(i){
return!1;
}
return!0;
},B=function(){
var e=[];
return pt.debugMode&&e.push(W.sm2Debug),pt.debugFlash&&e.push(W.flashDebug),pt.useHighPerformance&&e.push(W.highPerf),
e.join(" ");
},U=function(){
var e=N("fbHandler"),t=pt.getMoviePercent(),n=W,o={
type:"FLASHBLOCK"
};
return pt.html5Only?!1:void(pt.ok()?(pt.didFlashBlock&&pt._wD(e+": Unblocked"),pt.oMC&&(pt.oMC.className=[B(),n.swfDefault,n.swfLoaded+(pt.didFlashBlock?" "+n.swfUnblocked:"")].join(" "))):(Bt&&(pt.oMC.className=B()+" "+n.swfDefault+" "+(null===t?n.swfTimedout:n.swfError),
pt._wD(e+": "+N("fbTimeout")+(t?" ("+N("fbLoaded")+")":""))),pt.didFlashBlock=!0,
y({
type:"ontimeout",
ignoreInit:!0,
error:o
}),E(o)));
},g=function(e,n,o){
Dt[e]===t&&(Dt[e]=[]),Dt[e].push({
method:n,
scope:o||null,
fired:!1
});
},y=function(e){
if(e||(e={
type:pt.ok()?"onready":"ontimeout"
}),!Pt&&e&&!e.ignoreInit)return!1;
if("ontimeout"===e.type&&(pt.ok()||St&&!e.ignoreInit))return!1;
var t,n,o={
success:e&&e.ignoreInit?pt.ok():!St
},i=e&&e.type?Dt[e.type]||[]:[],a=[],s=[o],r=Bt&&!pt.ok();
for(e.error&&(s[0].error=e.error),t=0,n=i.length;n>t;t++)i[t].fired!==!0&&a.push(i[t]);
if(a.length)for(t=0,n=a.length;n>t;t++)a[t].scope?a[t].method.apply(a[t].scope,s):a[t].method.apply(this,s),
r||(a[t].fired=!0);
return!0;
},w=function(){
window.setTimeout(function(){
pt.useFlashBlock&&U(),y(),"function"==typeof pt.onload&&(h("onload",1),pt.onload.apply(window),
h("onloadOK",1)),pt.waitForWindowLoad&&at.add(window,"load",w);
},1);
},lt=function(){
if(rt!==t)return rt;
var e,n,o,i=!1,a=navigator,s=a.plugins,r=window.ActiveXObject;
if(s&&s.length)n="application/x-shockwave-flash",o=a.mimeTypes,o&&o[n]&&o[n].enabledPlugin&&o[n].enabledPlugin.description&&(i=!0);else if(r!==t&&!vt.match(/MSAppHost/i)){
try{
e=new r("ShockwaveFlash.ShockwaveFlash");
}catch(l){
e=null;
}
i=!!e,e=null;
}
return rt=i,i;
},Z=function(){
var e,t,n=pt.audioFormats,o=Vt&&!!vt.match(/os (1|2|3_0|3_1)/i);
if(o?(pt.hasHTML5=!1,pt.html5Only=!0,pt.oMC&&(pt.oMC.style.display="none")):pt.useHTML5Audio&&(pt.html5&&pt.html5.canPlayType||(pt._wD("SoundManager: No HTML5 Audio() support detected."),
pt.hasHTML5=!1),Zt&&pt._wD(yt+"Note: Buggy HTML5 Audio in Safari on this OS X release, see https://bugs.webkit.org/show_bug.cgi?id=32159 - "+(rt?"will use flash fallback for MP3/MP4, if available":" would use flash fallback for MP3/MP4, but none detected."),1)),
pt.useHTML5Audio&&pt.hasHTML5){
G=!0;
for(t in n)n.hasOwnProperty(t)&&n[t].required&&(pt.html5.canPlayType(n[t].type)?pt.preferFlash&&(pt.flash[t]||pt.flash[n[t].type])&&(e=!0):(G=!1,
e=!0));
}
return pt.ignoreFlash&&(e=!1,G=!0),pt.html5Only=pt.hasHTML5&&pt.useHTML5Audio&&!e,
!pt.html5Only;
},z=function(e){
var t,n,o,i=0;
if(e instanceof Array){
for(t=0,n=e.length;n>t;t++)if(e[t]instanceof Object){
if(pt.canPlayMIME(e[t].type)){
i=t;
break;
}
}else if(pt.canPlayURL(e[t])){
i=t;
break;
}
e[i].url&&(e[i]=e[i].url),o=e[i];
}else o=e;
return o;
},K=function(e){
e._hasTimer||(e._hasTimer=!0,!Gt&&pt.html5PollingInterval&&(null===Nt&&0===Rt&&(Nt=setInterval(X,pt.html5PollingInterval)),
Rt++));
},J=function(e){
e._hasTimer&&(e._hasTimer=!1,!Gt&&pt.html5PollingInterval&&Rt--);
},X=function(){
var e;
if(null!==Nt&&!Rt)return clearInterval(Nt),Nt=null,!1;
for(e=pt.soundIDs.length-1;e>=0;e--)pt.sounds[pt.soundIDs[e]].isHTML5&&pt.sounds[pt.soundIDs[e]]._hasTimer&&pt.sounds[pt.soundIDs[e]]._onTimer();
},E=function(e){
e=e!==t?e:{},"function"==typeof pt.onerror&&pt.onerror.apply(window,[{
type:e.type!==t?e.type:null
}]),e.fatal!==t&&e.fatal&&pt.disable();
},ut=function(){
if(!Zt||!lt())return!1;
var e,t,n=pt.audioFormats;
for(t in n)if(n.hasOwnProperty(t)&&("mp3"===t||"mp4"===t)&&(pt._wD(gt+": Using flash fallback for "+t+" format"),
pt.html5[t]=!1,n[t]&&n[t].related))for(e=n[t].related.length-1;e>=0;e--)pt.html5[n[t].related[e]]=!1;
},this._setSandboxType=function(e){
var n=pt.sandbox;
n.type=e,n.description=n.types[n.types[e]!==t?e:"unknown"],"localWithFile"===n.type?(n.noRemote=!0,
n.noLocal=!1,h("secNote",2)):"localWithNetwork"===n.type?(n.noRemote=!1,n.noLocal=!0):"localTrusted"===n.type&&(n.noRemote=!1,
n.noLocal=!1);
},this._externalInterfaceOK=function(e){
if(pt.swfLoaded)return!1;
var t;
return f("swf",!0),f("flashtojs",!0),pt.swfLoaded=!0,tn=!1,Zt&&ut(),e&&e.replace(/\+dev/i,"")===pt.versionNumber.replace(/\+dev/i,"")?void setTimeout(u,$t?100:1):(t=gt+': Fatal: JavaScript file build "'+pt.versionNumber+'" does not match Flash SWF build "'+e+'" at '+pt.url+". Ensure both are up-to-date.",
setTimeout(function(){
throw new Error(t);
},0),!1);
},H=function(e,n){
function o(){
var e,t=[],n=[],o=" + ";
e="SoundManager "+pt.version+(!pt.html5Only&&pt.useHTML5Audio?pt.hasHTML5?" + HTML5 audio":", no HTML5 audio support":""),
pt.html5Only?pt.html5PollingInterval&&t.push("html5PollingInterval ("+pt.html5PollingInterval+"ms)"):(pt.preferFlash&&t.push("preferFlash"),
pt.useHighPerformance&&t.push("useHighPerformance"),pt.flashPollingInterval&&t.push("flashPollingInterval ("+pt.flashPollingInterval+"ms)"),
pt.html5PollingInterval&&t.push("html5PollingInterval ("+pt.html5PollingInterval+"ms)"),
pt.wmode&&t.push("wmode ("+pt.wmode+")"),pt.debugFlash&&t.push("debugFlash"),pt.useFlashBlock&&t.push("flashBlock")),
t.length&&(n=n.concat([t.join(o)])),pt._wD(e+(n.length?o+n.join(", "):""),1),ft();
}
function i(e,t){
return'<param name="'+e+'" value="'+t+'" />';
}
if(Lt&&Tt)return!1;
if(pt.html5Only)return O(),o(),pt.oMC=s(pt.movieID),u(),Lt=!0,Tt=!0,!1;
var a,r,l,d,f,h,c,p,m=n||pt.url,_=pt.altURL||m,g="JS/Flash audio component (SoundManager 2)",y=I(),w=B(),v=null,b=Ot.getElementsByTagName("html")[0];
if(v=b&&b.dir&&b.dir.match(/rtl/i),e=e===t?pt.id:e,O(),pt.url=R(rn?m:_),n=pt.url,
pt.wmode=!pt.wmode&&pt.useHighPerformance?"transparent":pt.wmode,null!==pt.wmode&&(vt.match(/msie 8/i)||!$t&&!pt.useHighPerformance)&&navigator.platform.match(/win32|win64/i)&&(Ut.push(M.spcWmode),
pt.wmode=null),a={
name:e,
id:e,
src:n,
quality:"high",
allowScriptAccess:pt.allowScriptAccess,
bgcolor:pt.bgColor,
pluginspage:ln+"www.macromedia.com/go/getflashplayer",
title:g,
type:"application/x-shockwave-flash",
wmode:pt.wmode,
hasPriority:"true"
},pt.debugFlash&&(a.FlashVars="debug=1"),pt.wmode||delete a.wmode,$t)r=Ot.createElement("div"),
d=['<object id="'+e+'" data="'+n+'" type="'+a.type+'" title="'+a.title+'" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="'+ln+'download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0">',i("movie",n),i("AllowScriptAccess",pt.allowScriptAccess),i("quality",a.quality),pt.wmode?i("wmode",pt.wmode):"",i("bgcolor",pt.bgColor),i("hasPriority","true"),pt.debugFlash?i("FlashVars",a.FlashVars):"","</object>"].join("");else{
r=Ot.createElement("embed");
for(l in a)a.hasOwnProperty(l)&&r.setAttribute(l,a[l]);
}
if(k(),w=B(),y=I())if(pt.oMC=s(pt.movieID)||Ot.createElement("div"),pt.oMC.id)p=pt.oMC.className,
pt.oMC.className=(p?p+" ":W.swfDefault)+(w?" "+w:""),pt.oMC.appendChild(r),$t&&(f=pt.oMC.appendChild(Ot.createElement("div")),
f.className=W.swfBox,f.innerHTML=d),Tt=!0;else{
if(pt.oMC.id=pt.movieID,pt.oMC.className=W.swfDefault+" "+w,h=null,f=null,pt.useFlashBlock||(pt.useHighPerformance?h={
position:"fixed",
width:"8px",
height:"8px",
bottom:"0px",
left:"0px",
overflow:"hidden"
}:(h={
position:"absolute",
width:"6px",
height:"6px",
top:"-9999px",
left:"-9999px"
},v&&(h.left=Math.abs(parseInt(h.left,10))+"px"))),Kt&&(pt.oMC.style.zIndex=1e4),
!pt.debugFlash)for(c in h)h.hasOwnProperty(c)&&(pt.oMC.style[c]=h[c]);
try{
$t||pt.oMC.appendChild(r),y.appendChild(pt.oMC),$t&&(f=pt.oMC.appendChild(Ot.createElement("div")),
f.className=W.swfBox,f.innerHTML=d),Tt=!0;
}catch(D){
throw new Error(N("domError")+" \n"+D.toString());
}
}
return Lt=!0,o(),!0;
},L=function(){
return pt.html5Only?(H(),!1):_t?!1:pt.url?(_t=pt.getMovie(pt.id),_t||(At?($t?pt.oMC.innerHTML=kt:pt.oMC.appendChild(At),
At=null,Lt=!0):H(pt.id,pt.url),_t=pt.getMovie(pt.id)),"function"==typeof pt.oninitmovie&&setTimeout(pt.oninitmovie,1),
ht(),!0):(h("noURL"),!1);
},v=function(){
setTimeout(b,1e3);
},b=function(){
var e,t=!1;
return pt.url?Ct?!1:(Ct=!0,at.remove(window,"load",v),tn&&!en?(h("waitFocus"),!1):(Pt||(e=pt.getMoviePercent(),
e>0&&100>e&&(t=!0)),void setTimeout(function(){
return e=pt.getMoviePercent(),t?(Ct=!1,pt._wD(N("waitSWF")),window.setTimeout(v,1),
!1):(Pt||(pt._wD(gt+": No Flash response within expected time. Likely causes: "+(0===e?"SWF load failed, ":"")+"Flash blocked or JS-Flash security error."+(pt.debugFlash?" "+N("checkSWF"):""),2),
!rn&&e&&(h("localFail",2),pt.debugFlash||h("tryDebug",2)),0===e&&pt._wD(N("swf404",pt.url),1),
f("flashtojs",!1," (Check flash security or flash blockers)")),void(!Pt&&nn&&(null===e?pt.useFlashBlock||0===pt.flashLoadTimeout?(pt.useFlashBlock&&U(),
h("waitForever")):!pt.useFlashBlock&&G?window.setTimeout(function(){
Q(yt+"useFlashBlock is false, 100% HTML5 mode is possible. Rebooting with preferFlash: false..."),
pt.setup({
preferFlash:!1
}).reboot(),pt.didFlashBlock=!0,pt.beginDelayedInit();
},1):(h("waitForever"),y({
type:"ontimeout",
ignoreInit:!0
})):0===pt.flashLoadTimeout?h("waitForever"):x(!0))));
},pt.flashLoadTimeout))):!1;
},D=function(){
function e(){
at.remove(window,"focus",D);
}
return en||!tn?(e(),!0):(nn=!0,en=!0,h("gotFocus"),Ct=!1,v(),e(),!0);
},ht=function(){
Ut.length&&(pt._wD("SoundManager 2: "+Ut.join(" "),1),Ut=[]);
},ft=function(){
ht();
var e,t=[];
if(pt.useHTML5Audio&&pt.hasHTML5){
for(e in pt.audioFormats)pt.audioFormats.hasOwnProperty(e)&&t.push(e+" = "+pt.html5[e]+(!pt.html5[e]&&Bt&&pt.flash[e]?" (using flash)":pt.preferFlash&&pt.flash[e]&&Bt?" (preferring flash)":pt.html5[e]?"":" ("+(pt.audioFormats[e].required?"required, ":"")+"and no flash support)"));
pt._wD("SoundManager 2 HTML5 support: "+t.join(", "),1);
}
},c=function(e){
if(Pt)return!1;
if(pt.html5Only)return h("sm2Loaded"),Pt=!0,w(),f("onload",!0),!0;
var t,n=pt.useFlashBlock&&pt.flashLoadTimeout&&!pt.getMoviePercent(),o=!0;
return n||(Pt=!0,St&&(t={
type:!rt&&Bt?"NO_FLASH":"INIT_TIMEOUT"
})),pt._wD("SoundManager 2 "+(St?"failed to load":"loaded")+" ("+(St?"Flash security/load error":"OK")+")",St?2:1),
St||e?(pt.useFlashBlock&&pt.oMC&&(pt.oMC.className=B()+" "+(null===pt.getMoviePercent()?W.swfTimedout:W.swfError)),
y({
type:"ontimeout",
error:t,
ignoreInit:!0
}),f("onload",!1),E(t),o=!1):f("onload",!0),St||(pt.waitForWindowLoad&&!Ft?(h("waitOnload"),
at.add(window,"load",w)):(pt.waitForWindowLoad&&Ft&&h("docLoaded"),w())),o;
},l=function(){
var e,n=pt.setupOptions;
for(e in n)n.hasOwnProperty(e)&&(pt[e]===t?pt[e]=n[e]:pt[e]!==n[e]&&(pt.setupOptions[e]=pt[e]));
},u=function(){
function e(){
at.remove(window,"load",pt.beginDelayedInit);
}
if(Pt)return h("didInit"),!1;
if(pt.html5Only)return Pt||(e(),pt.enabled=!0,c()),!0;
L();
try{
_t._externalInterfaceTest(!1),A(!0,pt.flashPollingInterval||(pt.useHighPerformance?10:50)),
pt.debugMode||_t._disableDebug(),pt.enabled=!0,f("jstoflash",!0),pt.html5Only||at.add(window,"unload",r);
}catch(t){
return pt._wD("js/flash exception: "+t.toString()),f("jstoflash",!1),E({
type:"JS_TO_FLASH_EXCEPTION",
fatal:!0
}),x(!0),c(),!1;
}
return c(),e(),!0;
},P=function(){
return F?!1:(F=!0,l(),k(),function(){
var e="sm2-usehtml5audio=",t="sm2-preferflash=",n=null,o=null,i=bt.toLowerCase();
-1!==i.indexOf(e)&&(n="1"===i.charAt(i.indexOf(e)+e.length),Yt&&console.log((n?"Enabling ":"Disabling ")+"useHTML5Audio via URL parameter"),
pt.setup({
useHTML5Audio:n
})),-1!==i.indexOf(t)&&(o="1"===i.charAt(i.indexOf(t)+t.length),Yt&&console.log((o?"Enabling ":"Disabling ")+"preferFlash via URL parameter"),
pt.setup({
preferFlash:o
}));
}(),!rt&&pt.hasHTML5&&(pt._wD("SoundManager: No Flash detected"+(pt.useHTML5Audio?". Trying HTML5-only mode.":", enabling HTML5."),1),
pt.setup({
useHTML5Audio:!0,
preferFlash:!1
})),it(),!rt&&Bt&&(Ut.push(M.needFlash),pt.setup({
flashLoadTimeout:1
})),Ot.removeEventListener&&Ot.removeEventListener("DOMContentLoaded",P,!1),L(),
!0);
},ot=function(){
return"complete"===Ot.readyState&&(P(),Ot.detachEvent("onreadystatechange",ot)),
!0;
},S=function(){
Ft=!0,at.remove(window,"load",S);
},T=function(){
Gt&&((!pt.setupOptions.useHTML5Audio||pt.setupOptions.preferFlash)&&Ut.push(M.mobileUA),
pt.setupOptions.useHTML5Audio=!0,pt.setupOptions.preferFlash=!1,(Vt||Qt&&!vt.match(/android\s2\.3/i))&&(Ut.push(M.globalHTML5),
Vt&&(pt.ignoreFlash=!0),jt=!0));
},T(),lt(),at.add(window,"focus",D),at.add(window,"load",v),at.add(window,"load",S),
Ot.addEventListener?Ot.addEventListener("DOMContentLoaded",P,!1):Ot.attachEvent?Ot.attachEvent("onreadystatechange",ot):(f("onload",!1),
E({
type:"NO_DOM2_EVENTS",
fatal:!0
}));
}
var t,n=null;
return void 0!==window.SM2_DEFER&&SM2_DEFER||(n=new e),window.soundManager=n,n;
});define("media/plugin/audio.js",["common/wx/popup.js","tpl/media/plugin/audio.html.js","tpl/media/plugin/audioItem.html.js","common/wx/Cgi.js","biz_web/ui/checkbox.js","common/wx/media/audio.js","common/wx/pagebar.js","biz_common/moment.js"],function(i){
"use strict";
function e(i,n,s){
t.get({
url:"/cgi-bin/filepage",
dataType:"json",
data:{
type:3,
begin:i,
count:n
},
mask:!1
},function(i){
if(0==i.base_resp.ret){
l=i.page_info.file_item;
var n=[];
l.each(function(i){
1==i.trans_state&&n.push({
name:i.name,
title:i.title||i.name,
update_time:d.unix(i.update_time).format("YYYY-MM-DD"),
play_length:i.play_length,
file_id:i.file_id,
voice_encode_fileid:i.voice_encode_fileid,
disabled:s&&i.play_length>6e4,
format_play_length:d.unix(i.play_length/1e3).format("mm:ss")
});
}),l=n;
var p=wx.T(o,{
list:l
});
a.find(".jsPluginAudioList").html(p),a.find(".jsPluginAudioRadio").checkbox(),s&&$(".jsAudioTips").show(),
a.find(".jsPluginAudioPlay").each(function(i,e){
var n=l[i];
return n.selector="#"+$(e).attr("id"),n.source="file",new c($.extend({},n,{
qqmusictpl:!0
}));
}),u||(u=new m({
container:".jsPluginAudioPage",
totalItemsNum:i.page_info.file_cnt.voice_cnt,
callback:function(i){
e(10*(i.currentPage-1),10,s);
}
}));
}else t.show(i);
});
}
i("common/wx/popup.js");
var n=i("tpl/media/plugin/audio.html.js"),o=i("tpl/media/plugin/audioItem.html.js"),t=i("common/wx/Cgi.js");
i("biz_web/ui/checkbox.js");
var a,u,l,c=i("common/wx/media/audio.js"),m=i("common/wx/pagebar.js"),d=i("biz_common/moment.js"),s=function(){};
return window.openAudioPopup=function(i){
u=null,l=[],a=null,a=$(n).popup({
className:"align_edge audio_dialog",
width:"960",
title:"请选择语音",
buttons:[{
text:"确定",
click:function(){
var i=a.find(".jsPluginAudioRadio[checked=checked]").data("index");
s(l&&l[i]),this.remove();
},
type:"primary"
},{
text:"取消",
click:function(){
this.remove();
}
}],
onHide:function(){
this.remove();
}
}),e(0,10,i),$(".jsPluginAudioNew").click(function(){
window.open(wx.url("/cgi-bin/operate_voice?oper=voice_get&t=media/audio_add"),"_blank");
});
},{
registerCb:function(i){
s=i;
}
};
});define("tpl/media/dialog/file.html.js",[],function(){
return'{each list as item}\n{mediaDialogInit item}\n<li class="media_item">\n    <div class="media_info">\n        <label class="media_name frm_radio_label">\n        	<i class="icon_radio"></i>\n        	<input name="media" type="radio" class="frm_radio" value="{item.file_id}">\n            {item.name}\n        </label>\n        <span class="media_size">{item.size}</span>\n        <span class="media_time">{mediaDialogtimeFormat item.update_time}</span>\n    </div>\n    <div id="fileItem{item.file_id}" data-id="{item.file_id}" data-type="{item.type}" class="media_content"></div>\n</li>\n{/each}\n';
});define("tpl/media/dialog/videomsg_layout.html.js",[],function(){
return'<div class="dialog_media_container">\n    <div class="sub_title_bar in_dialog">\n        <div class="title_tab js_videotab"></div>\n        <div class="search_bar dn">\n            <span class="frm_input_box search with_del append">\n                <a class="del_btn" href="javascript:"><i class="icon_search_del"></i>&nbsp;</a>\n                <a id="msgSearchBtn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">搜索</i>&nbsp;</a>\n                <input id="msgSearchInput" type="text" placeholder="关键字" value="" class="frm_input">\n            </span>\n        </div>\n        <div class="richvideo_create js_video_create ">\n            <a class="btn btn_primary btn_add" target="_blank" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&type=15&lang=zh_CN&token={token}">\n                <i class="icon14_common add_white"></i>新建视频            </a>\n        </div>\n    </div>\n    <div class="js_video_status js_video_content dn">\n        <div class="richvideo_list media_dialog" id="js_videomsg_list">\n            <div class="richvideo_col"><div class="inner"></div></div>&nbsp;\n            <div class="richvideo_col"><div class="inner"></div></div>\n        </div>\n    </div>\n    <div class="js_video_status js_video_tencent dn">\n        <div class="video">\n            <div class="frm_control_group">\n                <label for="" class="frm_label">视频网址</label>\n                <div class="frm_controls">\n                    <span class="frm_input_box"><input type="text" class="frm_input js_video_txurl"></span>\n                    <p class="frm_tips">支持腾讯视频</p>\n                </div>\n            </div>\n			<div class="video_preview js_video_preview"></div>\n		</div>\n    </div>\n    <div class="js_video_status js_video_loading">\n        <i class="icon_loading_small white">loading...</i>\n    </div>\n    <div class="js_video_status js_video_none dn">\n        <div class="no_media_wrp">\n            <p class="empty_tips js_empty_tips"></p>\n            <!--\n            <div class="richvideo_create js_video_create">\n                <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&type=15&lang=zh_CN&token={token}">\n                    <i class="icon_richvideo_small"></i><strong>新建视频消息</strong>\n                </a>\n            </div>\n            -->\n        </div>\n        <span class="vm_box"></span>\n    </div>\n    \n    <div class="pagination_wrp pageNavigator js_pagebar"></div>\n</div>\n\n';
});