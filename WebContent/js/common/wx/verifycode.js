define("common/wx/verifycode.js", ["widget/verifycode.css", "tpl/verifycode.html.js", "common/qq/events.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict", e("widget/verifycode.css");
		var i = e("tpl/verifycode.html.js"),
			s = "/cgi-bin/verifycode?r=",
			o = e("common/qq/events.js"),
			u = o(!0);

		function a(e) {
			var t = this;
			this.$dom = $(i), this.$img = this.$dom.find("img"), this.$input = this.$dom.find("input"), this.$img.on("load", function() {
				u.trigger("VerifyCode:load", t);
			}), this.$dom.find("a").click(function(e) {
				t.$img.attr("src", s + +(new Date));
			}).click(), e && $(e).append(this.$dom) && (this.$container = $(e));
		}
		a.prototype.getCode = function() {
			return this.$input.val();
		}, a.prototype.focus = function() {
			this.$input.focus();
		}, a.prototype.getInput = function() {
			return this.$input;
		}, a.prototype.refresh = function() {
			this.$img.attr("src", s + +(new Date));
		}, $.fn.verifycode = function() {
			return this.each(function() {
				$.data(this, "verifycode", new a(this));
			});
		}, n.exports = a;
	} catch (f) {
		wx.jslog({
			src: "common/wx/verifycode.js"
		}, f);
	}
});