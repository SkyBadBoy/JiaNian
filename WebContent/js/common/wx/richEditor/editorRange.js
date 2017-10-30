define("common/wx/richEditor/editorRange.js", [], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = function() {
return document.selection ? document.selection : (window.getSelection || document.getSelection)();
}, s = function(e, t, n) {
if (!n && e === t) return !1;
if (e.compareDocumentPosition) {
var r = e.compareDocumentPosition(t);
if (r == 20 || r == 0) return !0;
} else if (e.contains(t)) return !0;
return !1;
}, o = function(e, t) {
var n = t.commonAncestorContainer || t.parentElement && t.parentElement() || null;
return n ? s(e, n, !0) : !1;
}, u = function(e) {
var t = i();
if (!t) return null;
var n = t.getRangeAt ? t.rangeCount ? t.getRangeAt(0) : null : t.createRange();
return n ? e ? o(e, n) ? n : null : n : null;
}, a = function(e) {
return e.parentElement ? e.parentElement() : e.commonAncestorContainer;
};
n.exports = {
getSelection: i,
getRange: u,
containsRange: o,
parentContainer: a
};
} catch (f) {
wx.jslog({
src: "common/wx/richEditor/editorRange.js"
}, f);
}
});