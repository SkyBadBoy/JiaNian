define("common/wx/media/factory.js", ["common/wx/media/img.js", "common/wx/media/audio.js", "common/wx/media/video.js", "common/wx/media/appmsg.js", "biz_common/utils/norefererimg.js", "common/qq/emoji.js", "tpl/media/cardmsg.html.js"], function(e, o, m) {
	"use strict";
	var i = e("common/wx/media/img.js"),
		n = e("common/wx/media/audio.js"),
		t = e("common/wx/media/video.js"),
		r = e("common/wx/media/appmsg.js"),
		c = e("biz_common/utils/norefererimg.js");
	e("common/qq/emoji.js");
	var s = e("tpl/media/cardmsg.html.js"),
		a = {
			1: function(e, o) {
				return $(e).html(o.content.emoji());
			},
			2: function(e, o) {
				return o.container = $(e), new i(o);
			},
			3: function(e, o) {
				return o.selector = $(e), o.source = "file", new n(o);
			},
			4: function(e, o) {
				return o.selector = $(e), o.id = o.file_id, o.source = "file", new t(o);
			},
			10: function(e, o) {
				return o.container = $(e), o.showMask = !1, new r(o);
			},
			11: function(e, o) {
				return o.container = $(e), o.showMask = !1, new r(o);
			},
			15: function(e, o) {
				return o.multi_item && o.multi_item[0] && (o.title = o.multi_item[0].title, o.digest = o.multi_item[0].digest),
					o.selector = $(e), o.id = 1e6 * Math.random() | 0, o.tpl = "videomsg", o.for_selection = !1, o.for_operation = !1,
					new t(o);
			},
			16: function(e, o) {
				$(e).html(template.compile(s)(o));
				var m = $(e).find(".js_logourl");
				m.length && c({
					img: m[0]
				});
			}
		};
	a[21] = a[15];
	var l = {
		render: function(e, o) {
			a[o.type] && $(e).length > 0 && a[o.type]($(e).html(""), o);
		},
		itemRender: a
	};
	m.exports = l;
});