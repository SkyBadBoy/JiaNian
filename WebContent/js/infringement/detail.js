define("infringement/detail.js", [ "common/wx/top.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = e("common/wx/top.js");
(new i("#topTab", i.DATA.infringement)).selected("list");
} catch (s) {
wx.jslog({
src: "infringement/detail.js"
}, s);
}
});