define("common/wx/Tips.js", [], function(e, r) {
	"use strict";

	function n(e, r, n) {
		$(".JS_TIPS").remove(), s = $(template.compile('<div class="JS_TIPS page_tips {type}" id="wxTips_' + (new Date).getTime() + '"><div class="inner">{=msg}</div></div>')({
			type: e || "error",
			msg: r
		})).appendTo("body").fadeIn(), t.delay(n || a.delay, null, s);
	}

	function t(e) {
		e.length > 0 && e.fadeOut({
			complete: function() {
				e.remove();
			}
		});
	}

	function c(e) {
		if ("string" != typeof e) return e;
		var r = "";
		return 0 == e.length ? "" : (r = e.replace(/&/g, "&gt;"), r = r.replace(/</g, "&lt;"), r = r.replace(/>/g, "&gt;"),
			r = r.replace(/ /g, "&nbsp;"), r = r.replace(/\'/g, "&#39;"), r = r.replace(/\"/g, "&quot;"),
			r = r.replace(/\n/g, "<br>"));
	}
	var s, i = r,
		a = {
			errMsg: "系统发生错误，请稍后重试",
			sucMsg: "操作成功",
			delay: 2
		};
	i.err = function(e, r) {
		n("error", c(e) || a.errMsg, r);
	}, i.suc = function(e, r) {
		n("success", c(e) || a.sucMsg, r);
	};
});