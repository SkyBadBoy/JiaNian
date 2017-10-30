define("tpl/tooltips.html.js", [], function() {
	return '<div class="popover {parentClass}" style="display:none;position:{container_mode};{if offset.left}left:{offset.left}px;top:{offset.top}px;{/if}">\n    <div class="popover_inner">\n        <div class="popover_content">{=content}</div>\n        {if container_close}\n        <!--#0001#-->\n        <a href="javascript:;" class="popover_close icon16_common close_flat js_popover_close">关闭</a>\n        <!--%0001%-->\n        {/if}\n        {if buttons.length > 0}\n        <div class="popover_bar">\n			{each buttons as o index}\n			<a onclick="return false;" href="javascript:;" class="btn {o.type}">{o.text}</a>\n			{/each}\n        </div>\n        {/if}\n    </div>\n    <i class="popover_arrow popover_arrow_out"></i>\n    <i class="popover_arrow popover_arrow_in"></i>\n</div>\n';
});
define("tpl/cardticket/card_quantity.html.js", [], function() {
	return '<div class="pop_store">\n	<!--增减库存-->\n	{if setquantity}\n	<div class="frm_control_group">\n        <div class="frm_controls">\n			<label class="frm_radio_label selected">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">增加</span>\n				<input type="radio" name="isadd" checked value="1" class="frm_radio js_quantity_type">\n			</label>\n			<label class="frm_radio_label">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">减少</span>\n				<input type="radio" name="isadd" value="0" class="frm_radio js_quantity_type">\n			</label>\n		</div>\n	</div>\n	{/if}\n	<div class="frm_control_group">                        \n		<div class="frm_controls">\n			<div class="frm_controls_hint group">\n				<span class="frm_input_box"><input type="text" class="frm_input js_value"></span>\n				<span class="frm_hint">份</span>\n			</div>\n			<p class="frm_tips fail">库存不能少于1</p>\n		</div>\n	</div>\n	<!--增减库存 end-->\n</div>';
});
define("biz_common/moment.js", [], function(t, e, n) {
	function s(t, e) {
		return function(n) {
			return c(t.call(this, n), e);
		};
	}

	function r(t) {
		return function(e) {
			return this.lang().ordinal(t.call(this, e));
		};
	}

	function a() {}

	function i(t) {
		u(this, t);
	}

	function o(t) {
		var e = this._data = {},
			n = t.years || t.year || t.y || 0,
			s = t.months || t.month || t.M || 0,
			r = t.weeks || t.week || t.w || 0,
			a = t.days || t.day || t.d || 0,
			i = t.hours || t.hour || t.h || 0,
			o = t.minutes || t.minute || t.m || 0,
			u = t.seconds || t.second || t.s || 0,
			c = t.milliseconds || t.millisecond || t.ms || 0;
		this._milliseconds = c + 1e3 * u + 6e4 * o + 36e5 * i, this._days = a + 7 * r, this._months = s + 12 * n, e.milliseconds = c % 1e3,
			u += d(c / 1e3), e.seconds = u % 60, o += d(u / 60), e.minutes = o % 60, i += d(o / 60), e.hours = i % 24, a += d(i / 24),
			a += 7 * r, e.days = a % 30, s += d(a / 30), e.months = s % 12, n += d(s / 12), e.years = n;
	}

	function u(t, e) {
		for (var n in e) e.hasOwnProperty(n) && (t[n] = e[n]);
		return t;
	}

	function d(t) {
		return 0 > t ? Math.ceil(t) : Math.floor(t);
	}

	function c(t, e) {
		for (var n = t + ""; n.length < e;) n = "0" + n;
		return n;
	}

	function h(t, e, n) {
		var s, r = e._milliseconds,
			a = e._days,
			i = e._months;
		r && t._d.setTime(+t + r * n), a && t.date(t.date() + a * n), i && (s = t.date(), t.date(1).month(t.month() + i * n).date(Math.min(s, t.daysInMonth())));
	}

	function f(t) {
		return "[object Array]" === Object.prototype.toString.call(t);
	}

	function l(t, e) {
		var n, s = Math.min(t.length, e.length),
			r = Math.abs(t.length - e.length),
			a = 0;
		for (n = 0; s > n; n++) ~~t[n] !== ~~e[n] && a++;
		return a + r;
	}

	function _(t, e) {
		return e.abbr = t, A[t] || (A[t] = new a), A[t].set(e), A[t];
	}

	function m(e) {
		return e ? (!A[e] && Z && t("./lang/" + e), A[e]) : C.fn._lang;
	}

	function M(t) {
		return t.match(/\[.*\]/) ? t.replace(/^\[|\]$/g, "") : t.replace(/\\/g, "");
	}

	function y(t) {
		var e, n, s = t.match(E);
		for (e = 0, n = s.length; n > e; e++) s[e] = ie[s[e]] ? ie[s[e]] : M(s[e]);
		return function(r) {
			var a = "";
			for (e = 0; n > e; e++) a += "function" == typeof s[e].call ? s[e].call(r, t) : s[e];
			return a;
		};
	}

	function Y(t, e) {
		function n(e) {
			return t.lang().longDateFormat(e) || e;
		}
		for (var s = 5; s-- && J.test(e);) e = e.replace(J, n);
		return se[e] || (se[e] = y(e)), se[e](t);
	}

	function D(t) {
		switch (t) {
			case "DDDD":
				return $;

			case "YYYY":
				return I;

			case "YYYYY":
				return X;

			case "S":
			case "SS":
			case "SSS":
			case "DDD":
				return N;

			case "MMM":
			case "MMMM":
			case "dd":
			case "ddd":
			case "dddd":
			case "a":
			case "A":
				return j;

			case "X":
				return G;

			case "Z":
			case "ZZ":
				return R;

			case "T":
				return B;

			case "MM":
			case "DD":
			case "YY":
			case "HH":
			case "hh":
			case "mm":
			case "ss":
			case "M":
			case "D":
			case "d":
			case "H":
			case "h":
			case "m":
			case "s":
				return V;

			default:
				return new RegExp(t.replace("\\", ""));
		}
	}

	function p(t, e, n) {
		var s, r = n._a;
		switch (t) {
			case "M":
			case "MM":
				r[1] = null == e ? 0 : ~~e - 1;
				break;

			case "MMM":
			case "MMMM":
				s = m(n._l).monthsParse(e), null != s ? r[1] = s : n._isValid = !1;
				break;

			case "D":
			case "DD":
			case "DDD":
			case "DDDD":
				null != e && (r[2] = ~~e);
				break;

			case "YY":
				r[0] = ~~e + (~~e > 68 ? 1900 : 2e3);
				break;

			case "YYYY":
			case "YYYYY":
				r[0] = ~~e;
				break;

			case "a":
			case "A":
				n._isPm = "pm" === (e + "").toLowerCase();
				break;

			case "H":
			case "HH":
			case "h":
			case "hh":
				r[3] = ~~e;
				break;

			case "m":
			case "mm":
				r[4] = ~~e;
				break;

			case "s":
			case "ss":
				r[5] = ~~e;
				break;

			case "S":
			case "SS":
			case "SSS":
				r[6] = ~~(1e3 * ("0." + e));
				break;

			case "X":
				n._d = new Date(1e3 * parseFloat(e));
				break;

			case "Z":
			case "ZZ":
				n._useUTC = !0, s = (e + "").match(te), s && s[1] && (n._tzh = ~~s[1]), s && s[2] && (n._tzm = ~~s[2]),
					s && "+" === s[0] && (n._tzh = -n._tzh, n._tzm = -n._tzm);
		}
		null == e && (n._isValid = !1);
	}

	function g(t) {
		var e, n, s = [];
		if (!t._d) {
			for (e = 0; 7 > e; e++) t._a[e] = s[e] = null == t._a[e] ? 2 === e ? 1 : 0 : t._a[e];
			s[3] += t._tzh || 0, s[4] += t._tzm || 0, n = new Date(0), t._useUTC ? (n.setUTCFullYear(s[0], s[1], s[2]),
					n.setUTCHours(s[3], s[4], s[5], s[6])) : (n.setFullYear(s[0], s[1], s[2]), n.setHours(s[3], s[4], s[5], s[6])),
				t._d = n;
		}
	}

	function w(t) {
		var e, n, s = t._f.match(E),
			r = t._i;
		for (t._a = [], e = 0; e < s.length; e++) n = (D(s[e]).exec(r) || [])[0], n && (r = r.slice(r.indexOf(n) + n.length)),
			ie[s[e]] && p(s[e], n, t);
		t._isPm && t._a[3] < 12 && (t._a[3] += 12), t._isPm === !1 && 12 === t._a[3] && (t._a[3] = 0), g(t);
	}

	function T(t) {
		for (var e, n, s, r, a = 99; t._f.length;) {
			if (e = u({}, t), e._f = t._f.pop(), w(e), n = new i(e), n.isValid()) {
				s = n;
				break;
			}
			r = l(e._a, n.toArray()), a > r && (a = r, s = n);
		}
		u(t, s);
	}

	function k(t) {
		var e, n = t._i;
		if (q.exec(n)) {
			for (t._f = "YYYY-MM-DDT", e = 0; 4 > e; e++)
				if (Q[e][1].exec(n)) {
					t._f += Q[e][0];
					break;
				}
			R.exec(n) && (t._f += " Z"), w(t);
		} else t._d = new Date(n);
	}

	function v(t) {
		var e = t._i,
			n = P.exec(e);
		void 0 === e ? t._d = new Date : n ? t._d = new Date(+n[1]) : "string" == typeof e ? k(t) : f(e) ? (t._a = e.slice(0),
			g(t)) : t._d = new Date(e instanceof Date ? +e : e);
	}

	function S(t, e, n, s, r) {
		return r.relativeTime(e || 1, !!n, t, s);
	}

	function L(t, e, n) {
		var s = U(Math.abs(t) / 1e3),
			r = U(s / 60),
			a = U(r / 60),
			i = U(a / 24),
			o = U(i / 365),
			u = 45 > s && ["s", s] || 1 === r && ["m"] || 45 > r && ["mm", r] || 1 === a && ["h"] || 22 > a && ["hh", a] || 1 === i && ["d"] || 25 >= i && ["dd", i] || 45 >= i && ["M"] || 345 > i && ["MM", U(i / 30)] || 1 === o && ["y"] || ["yy", o];
		return u[2] = e, u[3] = t > 0, u[4] = n, S.apply({}, u);
	}

	function b(t, e, n) {
		var s = n - e,
			r = n - t.day();
		return r > s && (r -= 7), s - 7 > r && (r += 7), Math.ceil(C(t).add("d", r).dayOfYear() / 7);
	}

	function F(t) {
		var e = t._i,
			n = t._f;
		return null === e || "" === e ? null : ("string" == typeof e && (t._i = e = m().preparse(e)), C.isMoment(e) ? (t = u({}, e),
			t._d = new Date(+e._d)) : n ? f(n) ? T(t) : w(t) : v(t), new i(t));
	}

	function H(t, e) {
		C.fn[t] = C.fn[t + "s"] = function(t) {
			var n = this._isUTC ? "UTC" : "";
			return null != t ? (this._d["set" + n + e](t), this) : this._d["get" + n + e]();
		};
	}

	function O(t) {
		C.duration.fn[t] = function() {
			return this._data[t];
		};
	}

	function z(t, e) {
		C.duration.fn["as" + t] = function() {
			return +this / e;
		};
	}
	for (var C, W, x = "2.0.0", U = Math.round, A = {}, Z = "undefined" != typeof n && n.exports, P = /^\/?Date\((\-?\d+)/i, E = /(\[[^\[]*\])|(\\)?(Mo|MM?M?M?|Do|DDDo|DD?D?D?|ddd?d?|do?|w[o|w]?|W[o|W]?|YYYYY|YYYY|YY|a|A|hh?|HH?|mm?|ss?|SS?S?|X|zz?|ZZ?|.)/g, J = /(\[[^\[]*\])|(\\)?(LT|LL?L?L?|l{1,4})/g, V = /\d\d?/, N = /\d{1,3}/, $ = /\d{3}/, I = /\d{1,4}/, X = /[+\-]?\d{1,6}/, j = /[0-9]*[a-z\u00A0-\u05FF\u0700-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+|[\u0600-\u06FF]+\s*?[\u0600-\u06FF]+/i, R = /Z|[\+\-]\d\d:?\d\d/i, B = /T/i, G = /[\+\-]?\d+(\.\d{1,3})?/, q = /^\s*\d{4}-\d\d-\d\d((T| )(\d\d(:\d\d(:\d\d(\.\d\d?\d?)?)?)?)?([\+\-]\d\d:?\d\d)?)?/, K = "YYYY-MM-DDTHH:mm:ssZ", Q = [
		["HH:mm:ss.S", /(T| )\d\d:\d\d:\d\d\.\d{1,3}/],
		["HH:mm:ss", /(T| )\d\d:\d\d:\d\d/],
		["HH:mm", /(T| )\d\d:\d\d/],
		["HH", /(T| )\d\d/]
	], te = /([\+\-]|\d\d)/gi, ee = "Month|Date|Hours|Minutes|Seconds|Milliseconds".split("|"), ne = {
		Milliseconds: 1,
		Seconds: 1e3,
		Minutes: 6e4,
		Hours: 36e5,
		Days: 864e5,
		Months: 2592e6,
		Years: 31536e6
	}, se = {}, re = "DDD w W M D d".split(" "), ae = "M D H h m s w W".split(" "), ie = {
		M: function() {
			return this.month() + 1;
		},
		MMM: function(t) {
			return this.lang().monthsShort(this, t);
		},
		MMMM: function(t) {
			return this.lang().months(this, t);
		},
		D: function() {
			return this.date();
		},
		DDD: function() {
			return this.dayOfYear();
		},
		d: function() {
			return this.day();
		},
		dd: function(t) {
			return this.lang().weekdaysMin(this, t);
		},
		ddd: function(t) {
			return this.lang().weekdaysShort(this, t);
		},
		dddd: function(t) {
			return this.lang().weekdays(this, t);
		},
		w: function() {
			return this.week();
		},
		W: function() {
			return this.isoWeek();
		},
		YY: function() {
			return c(this.year() % 100, 2);
		},
		YYYY: function() {
			return c(this.year(), 4);
		},
		YYYYY: function() {
			return c(this.year(), 5);
		},
		a: function() {
			return this.lang().meridiem(this.hours(), this.minutes(), !0);
		},
		A: function() {
			return this.lang().meridiem(this.hours(), this.minutes(), !1);
		},
		H: function() {
			return this.hours();
		},
		h: function() {
			return this.hours() % 12 || 12;
		},
		m: function() {
			return this.minutes();
		},
		s: function() {
			return this.seconds();
		},
		S: function() {
			return ~~(this.milliseconds() / 100);
		},
		SS: function() {
			return c(~~(this.milliseconds() / 10), 2);
		},
		SSS: function() {
			return c(this.milliseconds(), 3);
		},
		Z: function() {
			var t = -this.zone(),
				e = "+";
			return 0 > t && (t = -t, e = "-"), e + c(~~(t / 60), 2) + ":" + c(~~t % 60, 2);
		},
		ZZ: function() {
			var t = -this.zone(),
				e = "+";
			return 0 > t && (t = -t, e = "-"), e + c(~~(10 * t / 6), 4);
		},
		X: function() {
			return this.unix();
		}
	}; re.length;) W = re.pop(), ie[W + "o"] = r(ie[W]);
	for (; ae.length;) W = ae.pop(), ie[W + W] = s(ie[W], 2);
	for (ie.DDDD = s(ie.DDD, 3), a.prototype = {
		set: function(t) {
			var e, n;
			for (n in t) e = t[n], "function" == typeof e ? this[n] = e : this["_" + n] = e;
		},
		_months: "January_February_March_April_May_June_July_August_September_October_November_December".split("_"),
		months: function(t) {
			return this._months[t.month()];
		},
		_monthsShort: "Jan_Feb_Mar_Apr_May_Jun_Jul_Aug_Sep_Oct_Nov_Dec".split("_"),
		monthsShort: function(t) {
			return this._monthsShort[t.month()];
		},
		monthsParse: function(t) {
			var e, n, s;
			for (this._monthsParse || (this._monthsParse = []), e = 0; 12 > e; e++)
				if (this._monthsParse[e] || (n = C([2e3, e]),
						s = "^" + this.months(n, "") + "|^" + this.monthsShort(n, ""), this._monthsParse[e] = new RegExp(s.replace(".", ""), "i")),
					this._monthsParse[e].test(t)) return e;
		},
		_weekdays: "Sunday_Monday_Tuesday_Wednesday_Thursday_Friday_Saturday".split("_"),
		weekdays: function(t) {
			return this._weekdays[t.day()];
		},
		_weekdaysShort: "Sun_Mon_Tue_Wed_Thu_Fri_Sat".split("_"),
		weekdaysShort: function(t) {
			return this._weekdaysShort[t.day()];
		},
		_weekdaysMin: "Su_Mo_Tu_We_Th_Fr_Sa".split("_"),
		weekdaysMin: function(t) {
			return this._weekdaysMin[t.day()];
		},
		_longDateFormat: {
			LT: "h:mm A",
			L: "MM/DD/YYYY",
			LL: "MMMM D YYYY",
			LLL: "MMMM D YYYY LT",
			LLLL: "dddd, MMMM D YYYY LT"
		},
		longDateFormat: function(t) {
			var e = this._longDateFormat[t];
			return !e && this._longDateFormat[t.toUpperCase()] && (e = this._longDateFormat[t.toUpperCase()].replace(/MMMM|MM|DD|dddd/g, function(t) {
				return t.slice(1);
			}), this._longDateFormat[t] = e), e;
		},
		meridiem: function(t, e, n) {
			return t > 11 ? n ? "pm" : "PM" : n ? "am" : "AM";
		},
		_calendar: {
			sameDay: "[Today at] LT",
			nextDay: "[Tomorrow at] LT",
			nextWeek: "dddd [at] LT",
			lastDay: "[Yesterday at] LT",
			lastWeek: "[last] dddd [at] LT",
			sameElse: "L"
		},
		calendar: function(t, e) {
			var n = this._calendar[t];
			return "function" == typeof n ? n.apply(e) : n;
		},
		_relativeTime: {
			future: "in %s",
			past: "%s ago",
			s: "a few seconds",
			m: "a minute",
			mm: "%d minutes",
			h: "an hour",
			hh: "%d hours",
			d: "a day",
			dd: "%d days",
			M: "a month",
			MM: "%d months",
			y: "a year",
			yy: "%d years"
		},
		relativeTime: function(t, e, n, s) {
			var r = this._relativeTime[n];
			return "function" == typeof r ? r(t, e, n, s) : r.replace(/%d/i, t);
		},
		pastFuture: function(t, e) {
			var n = this._relativeTime[t > 0 ? "future" : "past"];
			return "function" == typeof n ? n(e) : n.replace(/%s/i, e);
		},
		ordinal: function(t) {
			return this._ordinal.replace("%d", t);
		},
		_ordinal: "%d",
		preparse: function(t) {
			return t;
		},
		postformat: function(t) {
			return t;
		},
		week: function(t) {
			return b(t, this._week.dow, this._week.doy);
		},
		_week: {
			dow: 0,
			doy: 6
		}
	}, C = function(t, e, n) {
		return F({
			_i: t,
			_f: e,
			_l: n,
			_isUTC: !1
		});
	}, C.utc = function(t, e, n) {
		return F({
			_useUTC: !0,
			_isUTC: !0,
			_l: n,
			_i: t,
			_f: e
		});
	}, C.unix = function(t) {
		return C(1e3 * t);
	}, C.duration = function(t, e) {
		var n, s = C.isDuration(t),
			r = "number" == typeof t,
			a = s ? t._data : r ? {} : t;
		return r && (e ? a[e] = t : a.milliseconds = t), n = new o(a), s && t.hasOwnProperty("_lang") && (n._lang = t._lang),
			n;
	}, C.version = x, C.defaultFormat = K, C.lang = function(t, e) {
		return t ? (e ? _(t, e) : A[t] || m(t), void(C.duration.fn._lang = C.fn._lang = m(t))) : C.fn._lang._abbr;
	}, C.langData = function(t) {
		return t && t._lang && t._lang._abbr && (t = t._lang._abbr), m(t);
	}, C.isMoment = function(t) {
		return t instanceof i;
	}, C.isDuration = function(t) {
		return t instanceof o;
	}, C.fn = i.prototype = {
		clone: function() {
			return C(this);
		},
		valueOf: function() {
			return +this._d;
		},
		unix: function() {
			return Math.floor(+this._d / 1e3);
		},
		toString: function() {
			return this.format("ddd MMM DD YYYY HH:mm:ss [GMT]ZZ");
		},
		toDate: function() {
			return this._d;
		},
		toJSON: function() {
			return C.utc(this).format("YYYY-MM-DD[T]HH:mm:ss.SSS[Z]");
		},
		toArray: function() {
			var t = this;
			return [t.year(), t.month(), t.date(), t.hours(), t.minutes(), t.seconds(), t.milliseconds()];
		},
		isValid: function() {
			return null == this._isValid && (this._isValid = this._a ? !l(this._a, (this._isUTC ? C.utc(this._a) : C(this._a)).toArray()) : !isNaN(this._d.getTime())), !!this._isValid;
		},
		utc: function() {
			return this._isUTC = !0, this;
		},
		local: function() {
			return this._isUTC = !1, this;
		},
		format: function(t) {
			var e = Y(this, t || C.defaultFormat);
			return this.lang().postformat(e);
		},
		add: function(t, e) {
			var n;
			return n = "string" == typeof t ? C.duration(+e, t) : C.duration(t, e), h(this, n, 1), this;
		},
		subtract: function(t, e) {
			var n;
			return n = "string" == typeof t ? C.duration(+e, t) : C.duration(t, e), h(this, n, -1), this;
		},
		diff: function(t, e, n) {
			var s, r, a = this._isUTC ? C(t).utc() : C(t).local(),
				i = 6e4 * (this.zone() - a.zone());
			return e && (e = e.replace(/s$/, "")), "year" === e || "month" === e ? (s = 432e5 * (this.daysInMonth() + a.daysInMonth()),
					r = 12 * (this.year() - a.year()) + (this.month() - a.month()), r += (this - C(this).startOf("month") - (a - C(a).startOf("month"))) / s,
					"year" === e && (r /= 12)) : (s = this - a - i, r = "second" === e ? s / 1e3 : "minute" === e ? s / 6e4 : "hour" === e ? s / 36e5 : "day" === e ? s / 864e5 : "week" === e ? s / 6048e5 : s),
				n ? r : d(r);
		},
		from: function(t, e) {
			return C.duration(this.diff(t)).lang(this.lang()._abbr).humanize(!e);
		},
		fromNow: function(t) {
			return this.from(C(), t);
		},
		calendar: function() {
			var t = this.diff(C().startOf("day"), "days", !0),
				e = -6 > t ? "sameElse" : -1 > t ? "lastWeek" : 0 > t ? "lastDay" : 1 > t ? "sameDay" : 2 > t ? "nextDay" : 7 > t ? "nextWeek" : "sameElse";
			return this.format(this.lang().calendar(e, this));
		},
		isLeapYear: function() {
			var t = this.year();
			return t % 4 === 0 && t % 100 !== 0 || t % 400 === 0;
		},
		isDST: function() {
			return this.zone() < C([this.year()]).zone() || this.zone() < C([this.year(), 5]).zone();
		},
		day: function(t) {
			var e = this._isUTC ? this._d.getUTCDay() : this._d.getDay();
			return null == t ? e : this.add({
				d: t - e
			});
		},
		startOf: function(t) {
			switch (t = t.replace(/s$/, "")) {
				case "year":
					this.month(0);

				case "month":
					this.date(1);

				case "week":
				case "day":
					this.hours(0);

				case "hour":
					this.minutes(0);

				case "minute":
					this.seconds(0);

				case "second":
					this.milliseconds(0);
			}
			return "week" === t && this.day(0), this;
		},
		endOf: function(t) {
			return this.startOf(t).add(t.replace(/s?$/, "s"), 1).subtract("ms", 1);
		},
		isAfter: function(t, e) {
			return e = "undefined" != typeof e ? e : "millisecond", +this.clone().startOf(e) > +C(t).startOf(e);
		},
		isBefore: function(t, e) {
			return e = "undefined" != typeof e ? e : "millisecond", +this.clone().startOf(e) < +C(t).startOf(e);
		},
		isSame: function(t, e) {
			return e = "undefined" != typeof e ? e : "millisecond", +this.clone().startOf(e) === +C(t).startOf(e);
		},
		zone: function() {
			return this._isUTC ? 0 : this._d.getTimezoneOffset();
		},
		daysInMonth: function() {
			return C.utc([this.year(), this.month() + 1, 0]).date();
		},
		dayOfYear: function(t) {
			var e = U((C(this).startOf("day") - C(this).startOf("year")) / 864e5) + 1;
			return null == t ? e : this.add("d", t - e);
		},
		isoWeek: function(t) {
			var e = b(this, 1, 4);
			return null == t ? e : this.add("d", 7 * (t - e));
		},
		week: function(t) {
			var e = this.lang().week(this);
			return null == t ? e : this.add("d", 7 * (t - e));
		},
		lang: function(t) {
			return void 0 === t ? this._lang : (this._lang = m(t), this);
		}
	}, W = 0; W < ee.length; W++) H(ee[W].toLowerCase().replace(/s$/, ""), ee[W]);
	H("year", "FullYear"), C.fn.days = C.fn.day, C.fn.weeks = C.fn.week, C.fn.isoWeeks = C.fn.isoWeek,
		C.duration.fn = o.prototype = {
			weeks: function() {
				return d(this.days() / 7);
			},
			valueOf: function() {
				return this._milliseconds + 864e5 * this._days + 2592e6 * this._months;
			},
			humanize: function(t) {
				var e = +this,
					n = L(e, !t, this.lang());
				return t && (n = this.lang().pastFuture(e, n)), this.lang().postformat(n);
			},
			lang: C.fn.lang
		};
	for (W in ne) ne.hasOwnProperty(W) && (z(W, ne[W]), O(W.toLowerCase()));
	return z("Weeks", 6048e5), C.lang("zh-cn", {
		months: "一月_二月_三月_四月_五月_六月_七月_八月_九月_十月_十一月_十二月".split("_"),
		monthsShort: "1月_2月_3月_4月_5月_6月_7月_8月_9月_10月_11月_12月".split("_"),
		weekdays: "星期日_星期一_星期二_星期三_星期四_星期五_星期六".split("_"),
		weekdaysShort: "周日_周一_周二_周三_周四_周五_周六".split("_"),
		weekdaysMin: "日_一_二_三_四_五_六".split("_"),
		longDateFormat: {
			LT: "Ah点mm",
			L: "YYYY年MMMD日",
			LL: "YYYY年MMMD日",
			LLL: "YYYY年MMMD日LT",
			LLLL: "YYYY年MMMD日ddddLT",
			l: "YYYY年MMMD日",
			ll: "YYYY年MMMD日",
			lll: "YYYY年MMMD日LT",
			llll: "YYYY年MMMD日ddddLT"
		},
		meridiem: function(t, e) {
			return 9 > t ? "早上" : 11 > t && 30 > e ? "上午" : 13 > t && 30 > e ? "中午" : 18 > t ? "下午" : "晚上";
		},
		calendar: {
			sameDay: "[今天]LT",
			nextDay: "[明天]LT",
			nextWeek: "[下]ddddLT",
			lastDay: "[昨天]LT",
			lastWeek: "[上]ddddLT",
			sameElse: "L"
		},
		ordinal: function(t, e) {
			switch (e) {
				case "d":
				case "D":
				case "DDD":
					return t + "日";

				case "M":
					return t + "月";

				case "w":
				case "W":
					return t + "周";

				default:
					return t;
			}
		},
		relativeTime: {
			future: "%s内",
			past: "%s前",
			s: "几秒",
			m: "1分钟",
			mm: "%d分钟",
			h: "1小时",
			hh: "%d小时",
			d: "1天",
			dd: "%d天",
			M: "1个月",
			MM: "%d个月",
			y: "1年",
			yy: "%d年"
		}
	}), C;
});
define("common/wx/upload.js", ["widget/upload.css", "biz_web/lib/webuploader.js", "common/wx/dialog.js", "common/wx/Tips.js", "tpl/uploader.html.js"], function(e) {
	"use strict";

	function i(e) {
		f.src = "http://isdspeed.qq.com/cgi-bin/r.cgi?flag1=7839&flag2=4&flag3=5&1=" + e;
	}
	e("widget/upload.css");
	var n = e("biz_web/lib/webuploader.js"),
		t = e("common/wx/dialog.js"),
		a = e("common/wx/Tips.js"),
		o = e("tpl/uploader.html.js"),
		r = wx.T,
		l = wx.path.webuploader,
		s = ~location.hostname.search(/^mp/) ? "https://mp.weixin.qq.com" : "",
		c = {
			2: {
				accept: {
					extensions: "bmp,png,jpeg,jpg,gif",
					mimeTypes: "image/*"
				},
				fileSingleSizeLimit: 2097152
			},
			3: {
				accept: {
					extensions: "mp3,wma,wav,amr",
					mimeTypes: "audio/*"
				},
				fileSingleSizeLimit: 5242880
			},
			4: {
				accept: {
					extensions: "rm,rmvb,wmv,avi,mpg,mpeg,mp4",
					mimeTypes: "video/*"
				},
				fileSingleSizeLimit: 20971520
			},
			5: {
				accept: {
					extensions: "pdf",
					mimeTypes: "application/pdf"
				},
				fileSingleSizeLimit: 10485760
			},
			6: {
				accept: {
					extensions: "bmp,png,jpeg,jpg,gif,pdf",
					mimeTypes: "image/*,application/pdf"
				},
				fileSingleSizeLimit: 2097152
			},
			7: {
				accept: {
					extensions: "bmp,jpeg,jpg,gif",
					mimeTypes: "image/*"
				},
				fileSingleSizeLimit: 2097152
			},
			8: {
				accept: {
					extensions: "bmp,png,jpeg,jpg",
					mimeTypes: "image/*"
				},
				fileSingleSizeLimit: 2097152
			},
			9: {
				accept: {
					extensions: "xls",
					mimeTypes: "application/vnd.ms-excel"
				},
				fileSingleSizeLimit: 204800
			},
			10: {
				accept: {
					extensions: "xls",
					mimeTypes: "application/vnd.ms-excel"
				},
				fileSingleSizeLimit: 5242880
			},
			11: {
				accept: {
					extensions: "bmp,png,jpeg,jpg",
					mimeTypes: "image/*"
				},
				fileSingleSizeLimit: 2097152
			}
		};
	c[15] = c[4];
	var p = function(e) {
			t.show({
				type: "warn",
				msg: "警告|" + e,
				mask: !0,
				buttons: [{
					text: "确定",
					click: function() {
						this.remove();
					}
				}]
			});
		},
		m = function(e) {
			var i = n.Uploader;
			0 == i.support("flash") ? p("<p>未安装或未正确配置flash插件，请检查后重试。<br><a href='http://get.adobe.com/cn/flashplayer/' target='_blank'>到adobe去下载flash插件</a></p>") : 0 == i.support() ? p("<p>您的浏览器不支持上传</p>") : e.refresh();
		},
		u = function(e) {
			e && wx.jslog({
				src: "common/wx/upload.js"
			}, null, e);
		},
		d = {
			swf: l,
			auto: !0,
			pick: {
				multiple: !0
			},
			fileNumLimit: 5,
			threads: 3,
			sendAsBinary: !1,
			runtimeOrder: "html5,flash",
			compress: {
				width: 1e8,
				height: 1e8,
				quality: 90,
				compressSize: 0,
				noCompressIfLarger: !0
			},
			imageSize: !0,
			chunked: !1,
			duplicate: !0
		},
		f = new Image,
		g = {},
		h = function(e) {
			if (!e.url) throw "missing url";
			var t, l, s, p = $('<ul class="upload_file_box" style="display:none"></ul>'),
				f = $(e.container);
			f.on("click", function() {
					Math.random() < .1 && u(12), m(t);
				}).parent().append(p),
				function() {
					0 == n.Uploader.support("html5") && 0 == n.Uploader.support("flash") && ((new Image).src = "/misc/jslog?level=error&id=36&content=[pageurl:" + encodeURIComponent(location.href) + ",ua:" + encodeURIComponent(window.navigator.userAgent) + "]");
				}(), l = {
					server: wx.url(e.url + "&ticket_id=" + wx.data.user_name + "&ticket=" + wx.data.ticket),
					pick: {
						id: f,
						multiple: e.multi
					},
					fileNumLimit: e.queueSizeLimit
				}, s = c[e.type] || c[2], e = $.extend(!0, {}, d, l, s, e);
			try {
				t = n.create(e);
			} catch (h) {
				if (!t) return;
			}
			return p.on("click", ".js_cancel", function() {
				var e = $(this).data("id");
				t.cancelFile(e), $(this).hide();
			}), t.on("beforeFileQueued", function(i) {
				return Math.random() < .1 && u(13), e.canContinueUpload && !e.canContinueUpload() ? !1 : !(e.onSelect && e.onSelect(null, i.id, i) === !1);
			}), t.on("fileQueued", function(e) {
				var i = {
					id: e.id,
					fileName: e.name,
					size: n.formatSize(e.size)
				};
				p.append(r(o, i)).show();
			}), t.on("fileDequeued", function() {
				Math.random() < .1 && u(14), e.onCancel && e.onCancel();
			}), t.on("uploadProgress", function(i, n) {
				var t = "#uploadItem%s".sprintf(i.id),
					a = p.find(t).find(".progress_bar_thumb");
				a.width("%s%".sprintf(100 * n)), 1 == n && p.find(t).find(".js_cancel").remove(), e.onProgress && e.onProgress(null, i.id, i, {
					percentage: n
				});
			}), t.on("uploadStart", function(e) {
				g[e.id] = +new Date;
			}), t.on("uploadSuccess", function(n, t, o) {
				if (Math.random() < .1 && u(16), t && t.base_resp) {
					var r = +t.base_resp.ret;
					if (0 == r) Math.random() < .1 && (u(17), g[n.id] && i(+new Date - g[n.id]));
					else switch (n.setStatus("invalid"),
						r) {
						case -18:
							a.err("页面停留时间过久，请刷新页面后重试！");
							break;

						case -20:
							a.err("无法解释该图片，请使用另一图片或截图另存");
							break;

						case -13:
							a.err("上传文件过于频繁，请稍后再试");
							break;

						case -10:
							a.err("上传文件过大");
							break;

						case -22:
							a.err("上传音频文件不能超过60秒");
							break;

						case -39:
							a.err("上传图片已超过宽度（或者高度）限制，请将图片调整到宽度4000像素以下，高度4000像素以下后重试");
							break;

						default:
							a.err("上传文件发送出错，请刷新页面后重试！");
					}
				}
				e.onComplete && e.onComplete(null, n.id, n, t, {
					fileCount: o.numOfProgress + o.numOfQueue
				});
			}), t.on("uploadFinished", function(i) {
				this.reset(), p.fadeOut().html(""), g = {}, 0 == i.numOfInvalid && i.numOfSuccess > 0 && e.onAllComplete && e.onAllComplete(null, {
					errors: i.numOfCancel + i.numOfInvalid + i.numOfUploadFailed + i.numofDeleted + i.numofInterrupt,
					filesUploaded: i.numOfSuccess
				});
			}), t.on("uploadError", function() {
				Math.random() < .1 && u(15), e.onError && e.onError();
			}), t.on("error", function(i, t, o) {
				switch ("object" == typeof t && (o = t), i) {
					case "Q_EXCEED_NUM_LIMIT":
						a.err("一次上传最多只能上传%s个文件".sprintf(t));
						break;

					case "F_EXCEED_SIZE":
						a.err("文件大小不能超过%s".sprintf(n.formatSize(t, "0")));
						break;

					case "Q_TYPE_DENIED":
						a.err(e.errTypeMsg || "文件必须为以下格式：%s".sprintf(e.accept.extensions).replace(/,/g, ", "));
				}
			}), t;
		},
		b = function(e) {
			return function(i) {
				return i.url = e, h(i);
			};
		},
		w = function(e) {
			return function(i) {
				return wx.url(e + "&ticket_id=" + wx.data.user_name + "&ticket=" + wx.data.ticket + "&id=" + i);
			};
		};
	return {
		uploadFile: h,
		uploadBizFile: b(s + "/cgi-bin/filetransfer?action=upload_material&f=json"),
		uploadTmpFile: b(s + "/cgi-bin/filetransfer?action=preview&f=json"),
		uploadCdnFile: b(s + "/cgi-bin/filetransfer?action=upload_cdn&f=json"),
		uploadShopFile: b(s + "/merchant/goodsimage?action=uploadimage"),
		uploadShopUnsaveFile: b(s + "/merchant/goodsimage?action=uploadimage&save=0"),
		uploadVideoCdnFile: b(s + "/cgi-bin/filetransfer?action=upload_video_cdn&f=json"),
		uploadRegisterFile: b(s + "/acct/realnamesubmit?type=2&action=file_set"),
		uploadUpgradeFile: b(s + "/acct/servicetypeupgrade?type=2&action=file_set"),
		uploadPoiFile: b(s + "/misc/setlocation?action=upload"),
		uploadCdnFileFromAd: function(e) {
			return b(s + "/cgi-bin/filetransfer?action=upload_cdn_check_size&f=json&width=" + e.w + "&height=" + e.h + "&limit_size=" + e.size);
		},
		uploadImageLibFile: function(e) {
			return e.url = s + "/cgi-bin/filetransfer?action=upload_material&f=json", 1 == e.doublewrite && (e.url += "&writetype=doublewrite&groupid=" + (e.groupid || 1)),
				h(e);
		},
		uploadCdnFileWithCheck: function(e) {
			var i = {
				height: 0,
				width: 0,
				size: 0,
				min_height: 0,
				min_width: 0,
				min_size: 0
			};
			e = $.extend(!0, i, e);
			var n = [];
			for (var t in e) n.push(encodeURIComponent(t) + "=" + encodeURIComponent(e[t]));
			return b(s + "/cgi-bin/filetransfer?action=upload_cdn_check_range&f=json&" + n.join("&"), "tmpfile");
		},
		tmpFileUrl: w(s + "/cgi-bin/filetransfer?action=preview"),
		mediaFileUrl: w(s + "/cgi-bin/filetransfer?action=bizmedia"),
		multimediaFileUrl: w(s + "/cgi-bin/filetransfer?action=multimedia")
	};
});
define("common/wx/tooltipsManager.js", ["common/wx/tooltips.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = e("common/wx/tooltips.js"),
			s = {
				tooltips: [],
				init: function(e, t) {
					var n = this;
					$(e).each(function() {
						t.container = this, n.add(new i(t));
					});
				},
				add: function(e) {
					this.tooltips.push(e);
				},
				hideAll: function() {
					for (var e = 0; e < this.tooltips.length; e++) this.tooltips[e].hide();
				},
				removeItem: function(e) {
					for (var t = 0; t < this.tooltips.length; t++)
						if (this.tooltips[t] === e) return this.tooltips.splice(t, 1), e.$dom.remove(), !0;
					return !1;
				},
				removeIndex: function(e) {
					if (e >= this.tooltips.length || e < 0) return;
					var t = this.tooltips[e];
					this.tooltips.splice(e, 1), t.$dom.remove();
				},
				current: function() {},
				hide: function() {},
				removeAll: function() {
					for (var e = 0; e < this.tooltips.length; e++) this.tooltips[e].$dom.remove();
					this.tooltips = [];
				}
			};
		return s;
	} catch (o) {
		wx.jslog({
			src: "common/wx/tooltipsManager.js"
		}, o);
	}
});
define("common/wx/tooltips.js", ["tpl/tooltips.html.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = {
				position: {},
				container: "",
				type: "hover",
				buttons: [],
				delay: 300,
				disabled: !1,
				reposition: !1,
				container_close: !1,
				parentClass: "",
				container_mode: "absolute"
			},
			s = wx.T,
			o = e("tpl/tooltips.html.js"),
			u = "btn_disabled",
			a = "hover",
			f = "show",
			l = function(e) {
				this.options = e = $.extend(!0, {}, i, e), this.$container = $(this.options.container);
				if (!this.$container || this.$container.length == 0) return;
				var t = this.$container.offset(),
					n = this.$container.height(),
					r = this.options.position.left || this.$container.data("x") || 0,
					l = n + (this.options.position.top || this.$container.data("y") || 0);
				this.options.offset = {
					left: t.left + r,
					top: t.top + l,
					left_x: r,
					top_y: l
				}, !e.content && (e.content = this.$container.data("tips") || ""), this.$dom = $(s(o, e)).appendTo("body"), this.options.disabled && this.$container.addClass(u);
				var c = this,
					h = this.options.type === a || this.options.type === "click" ? this.options.type : a;
				if (h == a) {
					var p = null;
					this.$container.hover(function(e) {
						c.options.onshow && typeof c.options.onshow == "function" ? c.options.onshow.apply(c) : !c.options.disabled && c.show();
					}, function(e) {
						p = window.setTimeout(function() {
							c.hide();
						}, c.options.delay);
					}), this.$dom.hover(function(e) {
						p && window.clearTimeout(p);
					}, function(e) {
						c.hide();
					});
				} else this.$container.click(function(e) {
					if (c.options.disabled) return;
					if (c.options.onbeforeclick && typeof c.options.onbeforeclick == "function" && c.options.onbeforeclick.apply(c) === !1) return;
					return c.$dom.data(f) ? c.options.onclose && typeof c.options.onclose == "function" ? c.options.onclose.apply(c) : c.hide() : c.options.onshow && typeof c.options.onshow == "function" ? c.options.onshow.apply(c) : c.show(), !1;
				});
				$(document).on("click", function(e) {
						c.options.onclose && typeof c.options.onclose == "function" ? c.options.onclose.apply(c, [e]) : c.hide();
					}), c.$dom.find(".js_popover_close").on("click", function(e) {
						return c.options.onclose && typeof c.options.onclose == "function" ? c.options.onclose.apply(c, [e]) : c.hide(), !1;
					}), this.$dom.hide(),
					function() {
						$.each(c.$dom.find(".btn"), function(e, t) {
							c.options.buttons[e].click && $(t).on("click", function() {
								c.options.buttons[e].click.apply(c);
							});
						});
					}();
			};
		l.prototype = {
			constructor: l,
			show: function() {
				if (this.options.reposition) {
					var e = this.$container.offset(),
						t = e.left + this.options.offset.left_x,
						n = e.top + this.options.offset.top_y;
					this.$dom.css({
						left: t,
						top: n
					}).show();
				} else this.$dom.show();
				this.$dom.data(f, !0);
			},
			hide: function() {
				this.$dom.hide(), this.$dom.data(f, !1);
			},
			enable: function() {
				return this.options.disabled = !1, this.$container.removeClass(u), this;
			},
			disable: function() {
				return this.options.disabled = !0, this.$container.addClass(u), this;
			}
		}, n.exports = l;
	} catch (c) {
		wx.jslog({
			src: "common/wx/tooltips.js"
		}, c);
	}
});
define("tpl/cardticket/send_card.html.js", [], function() {
	return '<div>\n<div class="wrp_processor js_step_container"></div>\n	<div class="first_step js_step_content js_step1">\n	    <!--选择投放方式弹窗-->\n		<div class="js_card_list"></div>\n		<!--选择投放方式弹窗 end-->\n	</div>\n	\n</div>';
});
define("cardticket/card_quantity.js", ["common/wx/Cgi.js", "common/wx/Tips.js", "biz_web/ui/checkbox.js", "tpl/cardticket/card_quantity.html.js", "common/wx/tooltips.js", "common/wx/tooltipsManager.js"], function(t) {
	"use strict";
	var e = t("common/wx/Cgi.js"),
		o = t("common/wx/Tips.js"),
		n = (t("biz_web/ui/checkbox.js"),
			template.compile(t("tpl/cardticket/card_quantity.html.js"))),
		i = {
			container: "",
			quantityChange: $.noop,
			setquantity: !0
		},
		a = t("common/wx/tooltips.js"),
		c = t("common/wx/tooltipsManager.js"),
		r = function(t) {
			t = $.extend(!0, {}, i, t), this.opt = t;
			var r = this;
			$(t.container).on("click", function(i) {
				var s, u = $(this).data("cid");
				if (t.before && t.before(u) === !1) return !1;
				var d = new a({
					container: this,
					content: n({
						setquantity: t.setquantity
					}),
					container_mode: t.mode || "absolute",
					type: "click",
					onclose: function(t) {
						if (t) {
							for (var e = this.$dom.get(0), o = t.target, n = !1; o && o !== document.body;) {
								if (o == e) {
									n = !0;
									break;
								}
								o = o.parentNode;
							}
							n ? this.show() : this.hide();
						}
					},
					buttons: [{
						text: "确定",
						type: "btn_primary",
						click: function() {
							var n = d.$dom,
								i = n.find(".js_value"),
								a = parseInt($.trim(i.val()));
							if (isNaN(a) || 0 >= a) return o.err("库存必须是不能小于1的整数"), !1;
							var m = 1e9;
							return a >= m ? (o.err("库存不能大于10亿"), i.focus(), !1) : void e.post({
								url: "/merchant/electroniccardmgr",
								data: {
									action: t.setquantity ? "modifyquantity" : "setquantity",
									card_id: u,
									value: a,
									isadd: s.value()
								}
							}, function(n) {
								0 == n.base_resp.ret ? (o.suc("设置库存成功"), c.removeAll(), t.quantityChange && t.quantityChange.call(r, u, !t.setquantity || s.value() ? a : -a)) : 1e4 == n.base_resp.ret ? o.err("库存不能小于0") : e.show(n);
							});
						}
					}, {
						text: "取消",
						type: "btn_default",
						click: function() {
							c.removeAll();
						}
					}]
				});
				d.show(), c.removeAll(), c.add(d), $(".popover").css("z-index", "10000"), s = d.$dom.find(".js_quantity_type").checkbox(),
					d.$dom.find(".js_value").focus(), i.stopPropagation();
			});
		};
	return r;
});
define("tpl/cardticket/card_preview.html.js", [], function() {
	return '<div class="pop_card_preview js_pop_card_preview">\n	<span class="hook hook_right_top js_arrow">\n	<!--\n		箭头位置 \n		hook_right_top      右偏上\n		\n	-->\n		<span class="hook_top"></span>\n		<span class="hook_btm"></span>\n	</span>\n	<div class="card_preview">\n		<div class="client_side">\n			<div class="banner">{convert_type card.type}</div>\n			<div class="wrp">\n				<div class="top" style="background-color: {card.color};border-bottom-color: {card.color};">\n					<div class="logo group">\n						<div class="avartar l"><img src="{http2https card.logo_url}"></div>\n						<p>{card.brand_name}</p>\n					</div>\n					<div class="msg">\n						<div class="main_msg">\n							<p>{card.title}</p>\n							<p class="title_sub">{card.sub_title}</p>\n						</div>\n						<p class="time">有效期 {validtime card \'YYYY-MM-DD\'}</p>\n					</div>\n					<div class="deco"></div>\n				</div>\n				<div class="wrp_content">\n					<div class="wrp_section section_dispose">\n						{if card.code_type==0}\n							<div class="main_msg sn">1513-2290-1878</div>\n						{else if card.code_type==1}\n							<div class="bar_code_panel">\n								<div class="main_msg bar_code"></div>\n								<p class="sn">1513-2290-1878</p>\n							</div>\n						{else if card.code_type==2}\n							<div class="qr_code_panel">\n								<div class="main_msg qr_code"></div>\n								<p class="sn">1513-2290-1878</p>\n							</div>\n						{/if}\n						<p>{card.notice}</p>\n					</div>\n					<div class="wrp_section">\n						<ul class="info_list">\n							<li class="info_li">\n								<p class="info">{convert_type card.type}详情</p>\n								<span class="supply_area"><i class="ic ic_go"></i></span>\n							</li>\n							<li class="info_li">\n								<p class="info">适用门店</p>\n								<span class="supply_area">{card.location_id_list.length}家<i class="ic ic_go"></i></span>\n							</li>\n						</ul>\n					</div>\n				</div>\n			</div>\n		</div>\n	</div>\n</div>';
});
define("tpl/cardticket/card_table.html.js", [], function() {
	return '<div class="release_method js_card_container">\n	{if loading}\n	<i class="icon_loading_small white">loading...</i>\n	{else}\n	<div class="sub_title_bar group">\n		<!-- <span class="frm_input_box search append">\n			<a href="javascript:void(0);" class="js_search frm_input_append">\n				<i class="icon16_common search_gray">搜索</i>\n				&nbsp;\n			</a>\n			<input type="text" placeholder="请输入卡券名称" class="frm_input js_keyword">\n		</span> -->\n	</div>\n	<div class="table_wrp release_method_select_table_wrp">\n		<table class="table" cellspacing="0">\n			<thead class="thead">\n				<tr>\n					<th class="table_cell release_method_select_box">&nbsp;</th>\n					<th class="table_cell release_method_kind"><div class="td_panel">卡券类型</div></th>\n					<th class="table_cell release_method_name"><div class="td_panel">卡券名称</div></th>\n					<th class="table_cell release_method_time"><div class="td_panel">卡券有效期</div></th>\n					<th class="table_cell release_method_stock"><div class="td_panel">库存</div></th>\n					{if payflag==1||payflag==2}<th class="table_cell release_method_price"><div class="td_panel">微信价(元)</div></th>{/if}\n					<!-- <th class="table_cell release_method_preview"><div class="td_panel">预览</div></th> -->\n					<th class="table_cell release_method_state"><div class="td_panel">卡券状态</div></th>\n				</tr>\n			</thead>\n			<tbody class="tbody">\n			{if !data.length}\n				<tr>\n					<td class="empty_tips" colspan="6">暂无卡券</td>\n				</tr>\n			{else}\n			{each data as card i}\n            <tr  {if hasdata && (i<pageInfo.begin||i>=pageInfo.begin+pageInfo.count)}class="dn"{/if} id="js_ct_tr_{card.id}">\n					<td class="table_cell release_method_select_box"><div class="td_panel">\n						{if !multi}\n						<label class="frm_radio_label">\n							<i class="icon_radio"></i>\n							<input type="radio" data-id="{card.id}" value="{card.id}" class="frm_radio js_select">\n						</label>\n						{else}\n						<label class="frm_checkbox_label">\n							<i class="icon_checkbox"></i>\n							<input type="checkbox" data-id="{card.id}" value="{card.id}" class="frm_checkbox js_select">\n						</label>\n						{/if}\n					</div></td>\n					<td class="table_cell release_method_kind"><div class="td_panel">{convert_type card.type}</div></td>\n					<td class="table_cell release_method_name"><div class="td_panel">{card.title}</div></td>\n					<td class="table_cell release_method_time"><div class="td_panel">{validtime card \'YYYY-MM-DD\'}</div></td>\n					<td class="table_cell release_method_stock"><div class="td_panel"><span class="js_sendcard_quantity">{card.quantity}</span>\n						{if editquantity && !card.is_from_intercomm && card.can_edit_quantity}<a class="icon14_common edit_gray js_modify_quantity" href="javascript:;" data-new="{if card.isnew}1{/if}" data-cid="{card.id}" data-x="-126"></a>{/if}</div>\n					</td>\n					{if payflag==1||payflag==2}<td class="table_cell release_method_price"><div class="td_panel">{if card.ispay}{card.price}{else}--{/if}</div></td>{/if}\n					<!-- <td class="table_cell release_method_preview"><div class="td_panel"><a data-cid="{card.id}" data-x="-125" class="js_card_preview" href="javascript:void(0);">预览</a></div></td> -->\n					<td class="table_cell release_method_state"><div class="td_panel"><span class="fail pass"><i></i>{convert_state card.status}</span></div></td></td>\n				</tr>\n			{/each}\n			{/if}\n			</tbody>\n		</table>\n        <div class="js_pager"></div>\n        {if multi}\n        <p class="dialog_bt_tip">已选<span class="js_selectcount">{defaultValues.length||0}</span>个</p>\n        {/if}\n	</div>\n	{/if}\n</div>\n';
});
define("cardticket/common_template_helper.js", ["common/wx/upload.js", "biz_common/moment.js"], function(e) {
	"use strict";

	function t(e) {
		return e.replace(/\\n/g, "<br/>");
	}

	function r(e) {
		var t = "YYYY-MM-DD HH:mm:ss",
			r = p(e, t);
		return r ? r.format("YYYY-MM-DD") : "";
	}
	var n = e("common/wx/upload.js"),
		p = e("biz_common/moment.js"),
		a = {
			10: "会员卡",
			21: "门票",
			22: "电影票",
			4: "代金券",
			1: "团购券",
			2: "折扣券",
			3: "礼品券",
			0: "优惠券"
		},
		i = {
			1: "审核中",
			2: "未通过",
			3: "待投放",
			4: "已删除",
			5: "待投放",
			6: "已投放",
			8: "已过期"
		},
		p = e("biz_common/moment.js");
	template.helper("convert_state", function(e) {
		return i[e] || e;
	}), template.helper("convert_type", function(e) {
		return a[e] || e;
	}), template.helper("card_type_map", function(e) {
		return e;
	}), template.helper("unixFormat", function(e, t) {
		return t && (t = t.replace(",", " ")), p.unix(e).format(t);
	}), template.helper("validtime", function(e, t) {
		if (1 == e.time_type) {
			var r = p.unix(e.begin_time).format(t) + "至" + p.unix(e.end_time).format(t);
			return e.end_time < p().unix() && (r += "(已过期)"), r;
		}
		return 2 == e.time_type ? (e.from_day = 0 == e.from_day ? "当" : e.from_day, "领取后{from_day}天生效{fixed_term}天有效".format(e)) : "";
	}), template.helper("addtoken", function(e) {
		return wx.url(e);
	}), template.helper("nl2br", function(e) {
		return t(e);
	});
	var m = {
		1: "50万以下",
		2: "50-100万",
		3: "100-500万",
		4: "500-1000万",
		5: "1000万以上"
	};
	template.helper("convert_business_volume_type", function(e) {
		return m[e] || e;
	});
	var l = {
		0: "未审核",
		2: "审核中",
		3: "生效",
		4: "审核失败"
	};
	template.helper("convert_store_state", function(e) {
		return l[e] || e;
	}), template.helper("$preview", function(e) {
		if (!e) return "无";
		var t;
		return 0 === e.indexOf("temp_") ? (e = e.replace(/^temp_/, ""), t = n.tmpFileUrl(e)) : t = n.multimediaFileUrl(e),
			"<a href='%s' target='_blank'><img src='%s' /></a>".sprintf(t, t);
	}), template.helper("$upload_preview", function(e) {
		if (!e) return "";
		var t;
		return 0 === e.indexOf("temp_") ? (e = e.replace(/^temp_/, ""), t = n.tmpFileUrl(e)) : t = n.multimediaFileUrl(e),
			"<img src='%s' style='width:260px;' />".sprintf(t);
	}), template.helper("$preview_stuffs", function(e) {
		for (var t = [], r = e.stuffs, n = 0; n < r.length; n++) {
			var p = r[n] + "_preview_tpl";
			$("#" + p).length && t.push(template.render(p, e));
		}
		return t.join("");
	});
	var u = {
		2: "女",
		1: "男"
	};
	template.helper("convert_gender", function(e) {
		return u[e] || "未知";
	}), template.helper("percentage", function(e, t, r, n) {
		var r = e / t * 100;
		return n && r > n && (r = n), r.toFixed(2);
	});
	var o = {
		"": "全部",
		0: "API渠道",
		1: "嵌入图文消息",
		2: "直接群发卡券",
		3: "下载二维码"
	};
	template.helper("convert_channel", function(e) {
		return o[e] || e;
	}), template.helper("convert_provide_time", r), template.helper("http2https", function(e) {
		return e ? (e + "").http2https() : "";
	}), template.helper("https2http", function(e) {
		return e ? (e + "").https2http() : "";
	}), template.helper("codepad", function(e) {
		var t = new RegExp("([^s]{4})(?=([^s])+$)", "ig");
		return e.replace(t, "$1-");
	}), template.helper("yuan", function(e) {
		if (!e) return "--";
		var e = e / 100;
		return e.toFixed(2);
	}), template.helper("is_paycard", function() {
		return window.wx_is_paycard;
	});
	var c = {
			0: "等待接收",
			1: "已接收",
			3: "过期退回",
			2: "已拒绝"
		},
		_ = {
			0: "等待接收",
			2: "已拒绝",
			1: "已接收",
			3: "过期退回"
		};
	return template.helper("convert_intercard_status", function(e) {
		return c[e] || e;
	}), template.helper("convert_intercard_rec_status", function(e) {
		return _[e] || e;
	}), {
		type_map: a,
		status_map: i,
		store_status: l,
		gender_map: u,
		source_map: o,
		convert_provide_time: r,
		nl2br: t
	};
});
define("cardticket/store_cgi.js", ["common/wx/Cgi.js", "common/wx/Tips.js", "common/wx/tooltips.js", "common/wx/tooltipsManager.js", "common/wx/dialog.js"], function(t) {
	"use strict";
	var e = t("common/wx/Cgi.js"),
		s = t("common/wx/Tips.js"),
		o = t("common/wx/tooltips.js"),
		n = t("common/wx/tooltipsManager.js"),
		c = (t("common/wx/dialog.js"), {
			deleteStore: function(t) {
				e.post({
					url: "/merchant/entityshop?action=delete",
					data: {
						id: t.store_id
					},
					btn: t.btn
				}, function(o) {
					0 == o.base_resp.ret ? (s.suc("删除门店成功"), t.success && t.success()) : e.show(o);
				});
			},
			deleteWithConfirm: function(t) {
				if (3 == t.state || 4 == t.state) {
					var e = new o({
						container: t.container,
						content: "删除将影响在用此门店的相关业务。<br />你确定要删除吗？",
						type: "click",
						buttons: [{
							text: "确定",
							type: "btn_primary",
							click: function() {
								if (t.success) {
									var e = t.success;
									t.success = function() {
										e && e(), n.removeAll();
									};
								}
								c.deleteStore(t);
							}
						}, {
							text: "取消",
							type: "btn_default",
							click: function() {
								n.removeAll();
							}
						}]
					});
					e.show(), n.removeAll(), n.add(e);
				}
			},
			listStore: function(t) {
				var s = $.extend({}, {
					action: "list",
					begin: 0,
					count: 9999999,
					keyword: t.keyword,
					task_id: t.task_id,
					audit_state: t.audit_state || 3
				}, t.getDataExtra);
				e.get({
					url: "/merchant/entityshop",
					data: s
				}, function(s) {
					if (0 == s.base_resp.ret) {
						var o = $.parseJSON(s.data),
							n = o.store_location;
						t.success && t.success({
							shop_list: n,
							total_num: s.total_count
						});
					} else e.show(s);
				});
			},
			canSendCard: function(t) {
				t.success && t.success(!0);
			}
		});
	return c;
});
define("tpl/media/dialog/image_group.html.js", [], function() {
	return '{each file_group_list.file_group as item}\n<dd id="js_group{item.id}" class="inner_menu_item js_groupitem{if item.id == group} selected{/if}" data-groupid="{item.id}">\n    <a href="javascript:;" class="inner_menu_link" title="{item.name}" onclick="return false">\n        <strong>{item.name}</strong><em class="num">(<span>{item.count}</span>)</em>\n    </a>\n</dd>\n{/each}\n';
});