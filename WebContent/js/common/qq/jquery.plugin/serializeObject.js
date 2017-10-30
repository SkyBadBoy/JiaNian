$.fn.extend({
	serializeObject: function() {
		var e = this.serializeArray(),
			t = {};
		return $(e).each(function(e, n) {
			t[n.name] = n.value;
		}), t;
	}
}), define("common/qq/jquery.plugin/serializeObject.js", [], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
	} catch (i) {
		wx.jslog({
			src: "common/qq/jquery.plugin/serializeObject.js"
		}, i);
	}
});