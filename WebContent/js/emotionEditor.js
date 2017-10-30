define("common/wx/cgiReport.js", ["common/wx/Tips.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = e("common/wx/Tips.js");
		t.error = function(e, t) {
			var n = 11;
			switch (e) {
				case "timeout":
					n = 7;
					break;
				case "error":
					n = 8;
					break;
				case "notmodified":
					n = 9;
					break;
				case "parsererror":
					n = 10;
			}
			t.data.lang && delete t.data.lang, t.data.random && delete t.data.random, t.data.f && delete t.data.f, t.data.ajax && delete t.data.ajax, t.data.token && delete t.data.token, $.ajax({
				url: "/misc/jslog?1=1",
				data: {
					content: "[fakeid={uin}] [xhr] [url={url}] [param={param}] [info={info}] [useragent={userAgent}] [page={page}]".format({
						uin: wx.data.uin,
						useragent: window.navigator.userAgent,
						page: location.href,
						url: t.url,
						param: $.param(t.data).substr(0, 50),
						info: e
					}),
					id: n,
					level: "error"
				},
				type: "POST"
			}), $.ajax({
				url: "/misc/jslog?1=1",
				data: {
					content: "[fakeid={uin}] [xhr] [url={url}] [param={param}] [info={info}] [useragent={userAgent}] [page={page}]".format({
						uin: wx.data.uin,
						useragent: window.navigator.userAgent,
						page: location.href,
						url: t.url,
						param: $.param(t.data).substr(0, 50),
						info: e
					}),
					id: 6,
					level: "error"
				},
				type: "POST"
			}), e == "timeout" && i.err("你的网络环境较差，请稍后重试");
		};
	} catch (s) {
		wx.jslog({
			src: "common/wx/cgiReport.js"
		}, s);
	}
});
define("tpl/biz_web/ui/checkbox.html.js", [], function(e, t, n) {
	return '<label for="_checkbox_{index}" class="frm_{type}_label">\n	<i class="icon_{type}"></i>\n	<input type="{type}" class="frm_{type}" name="{name}" id="_checkbox_{index}">\n	<span class="lbl_content">{label}</span>\n</label>';
});
define("tpl/dialog.html.js", [], function() {
	return '<div class="dialog_wrp {className}" style="{if width}width:{width}px;{/if}{if height}height:{height}px;{/if};display:none;">\n  <div class="dialog" id="{id}">\n    <div class="dialog_hd">\n      <h3>{title}</h3>\n      {if !hideClose}\n      <!--#0001#-->\n      <a href="javascript:;" class="pop_closed" onclick="return false;">关闭</a>\n      <!--%0001%-->\n      {/if}\n    </div>\n    <div class="dialog_bd">\n      <div class="page_msg large simple default {msg.msgClass}">\n        <div class="inner group">\n          <span class="msg_icon_wrapper"><i class="icon_msg {icon} "></i></span>\n          <div class="msg_content ">\n          {if msg.title}<h4>{=msg.title}</h4>{/if}\n          {if msg.text}<p>{=msg.text}</p>{/if}\n          </div>\n        </div>\n      </div>\n    </div> \n    <div class="dialog_ft">\n  	{if !hideClose}\n      {each buttons as bt index}\n      <a href="javascript:;" class="btn {bt.type} js_btn" onclick="return false;">{bt.text}</a>\n      {/each}\n  	{/if}\n    </div>\n  </div>\n</div>\n{if mask}<div class="mask"></div>{/if}\n\n';
});
define("biz_common/jquery.ui/jquery.ui.draggable.js", [], function() {
	! function(t, e) {
		function i(e, i) {
			var o, n, r, a = e.nodeName.toLowerCase();
			return "area" === a ? (o = e.parentNode, n = o.name, e.href && n && "map" === o.nodeName.toLowerCase() ? (r = t("img[usemap=#" + n + "]")[0], !!r && s(r)) : !1) : (/input|select|textarea|button|object/.test(a) ? !e.disabled : "a" === a ? e.href || i : i) && s(e);
		}

		function s(e) {
			return t.expr.filters.visible(e) && !t(e).parents().addBack().filter(function() {
				return "hidden" === t.css(this, "visibility");
			}).length;
		}
		var o = 0,
			n = /^ui-id-\d+$/;
		t.ui = t.ui || {}, t.extend(t.ui, {
			version: "1.10.3"
		}), t.fn.extend({
			focus: function(e) {
				return function(i, s) {
					return "number" == typeof i ? this.each(function() {
						var e = this;
						setTimeout(function() {
							t(e).focus(), s && s.call(e);
						}, i);
					}) : e.apply(this, arguments);
				};
			}(t.fn.focus),
			scrollParent: function() {
				var e;
				return e = t.ui.ie && /(static|relative)/.test(this.css("position")) || /absolute/.test(this.css("position")) ? this.parents().filter(function() {
					return /(relative|absolute|fixed)/.test(t.css(this, "position")) && /(auto|scroll)/.test(t.css(this, "overflow") + t.css(this, "overflow-y") + t.css(this, "overflow-x"));
				}).eq(0) : this.parents().filter(function() {
					return /(auto|scroll)/.test(t.css(this, "overflow") + t.css(this, "overflow-y") + t.css(this, "overflow-x"));
				}).eq(0), /fixed/.test(this.css("position")) || !e.length ? t(document) : e;
			},
			zIndex: function(i) {
				if (i !== e) return this.css("zIndex", i);
				if (this.length)
					for (var s, o, n = t(this[0]); n.length && n[0] !== document;) {
						if (s = n.css("position"), ("absolute" === s || "relative" === s || "fixed" === s) && (o = parseInt(n.css("zIndex"), 10), !isNaN(o) && 0 !== o)) return o;
						n = n.parent();
					}
				return 0;
			},
			uniqueId: function() {
				return this.each(function() {
					this.id || (this.id = "ui-id-" + ++o);
				});
			},
			removeUniqueId: function() {
				return this.each(function() {
					n.test(this.id) && t(this).removeAttr("id");
				});
			}
		}), t.extend(t.expr[":"], {
			data: t.expr.createPseudo ? t.expr.createPseudo(function(e) {
				return function(i) {
					return !!t.data(i, e);
				};
			}) : function(e, i, s) {
				return !!t.data(e, s[3]);
			},
			focusable: function(e) {
				return i(e, !isNaN(t.attr(e, "tabindex")));
			},
			tabbable: function(e) {
				var s = t.attr(e, "tabindex"),
					o = isNaN(s);
				return (o || s >= 0) && i(e, !o);
			}
		}), t.extend(t.ui, {
			plugin: {
				add: function(e, i, s) {
					var o, n = t.ui[e].prototype;
					for (o in s) n.plugins[o] = n.plugins[o] || [], n.plugins[o].push([i, s[o]]);
				},
				call: function(t, e, i) {
					var s, o = t.plugins[e];
					if (o && t.element[0].parentNode && 11 !== t.element[0].parentNode.nodeType)
						for (s = 0; s < o.length; s++) t.options[o[s][0]] && o[s][1].apply(t.element, i);
				}
			},
			hasScroll: function(e, i) {
				if ("hidden" === t(e).css("overflow")) return !1;
				var s = i && "left" === i ? "scrollLeft" : "scrollTop",
					o = !1;
				return e[s] > 0 ? !0 : (e[s] = 1, o = e[s] > 0, e[s] = 0, o);
			}
		});
	}(jQuery),
	function(t, e) {
		var i = 0,
			s = Array.prototype.slice,
			o = t.cleanData;
		t.cleanData = function(e) {
			for (var i, s = 0; null != (i = e[s]); s++) try {
				t(i).triggerHandler("remove");
			} catch (n) {}
			o(e);
		}, t.widget = function(e, i, s) {
			var o, n, r, a, l = {},
				h = e.split(".")[0];
			e = e.split(".")[1], o = h + "-" + e, s || (s = i, i = t.Widget), t.expr[":"][o.toLowerCase()] = function(e) {
				return !!t.data(e, o);
			}, t[h] = t[h] || {}, n = t[h][e], r = t[h][e] = function(t, e) {
				return this._createWidget ? void(arguments.length && this._createWidget(t, e)) : new r(t, e);
			}, t.extend(r, n, {
				version: s.version,
				_proto: t.extend({}, s),
				_childConstructors: []
			}), a = new i, a.options = t.widget.extend({}, a.options), t.each(s, function(e, s) {
				return t.isFunction(s) ? void(l[e] = function() {
					var t = function() {
							return i.prototype[e].apply(this, arguments);
						},
						o = function(t) {
							return i.prototype[e].apply(this, t);
						};
					return function() {
						var e, i = this._super,
							n = this._superApply;
						return this._super = t, this._superApply = o, e = s.apply(this, arguments), this._super = i,
							this._superApply = n, e;
					};
				}()) : void(l[e] = s);
			}), r.prototype = t.widget.extend(a, {
				widgetEventPrefix: n ? a.widgetEventPrefix : e
			}, l, {
				constructor: r,
				namespace: h,
				widgetName: e,
				widgetFullName: o
			}), n ? (t.each(n._childConstructors, function(e, i) {
				var s = i.prototype;
				t.widget(s.namespace + "." + s.widgetName, r, i._proto);
			}), delete n._childConstructors) : i._childConstructors.push(r), t.widget.bridge(e, r);
		}, t.widget.extend = function(i) {
			for (var o, n, r = s.call(arguments, 1), a = 0, l = r.length; l > a; a++)
				for (o in r[a]) n = r[a][o],
					r[a].hasOwnProperty(o) && n !== e && (i[o] = t.isPlainObject(n) ? t.isPlainObject(i[o]) ? t.widget.extend({}, i[o], n) : t.widget.extend({}, n) : n);
			return i;
		}, t.widget.bridge = function(i, o) {
			var n = o.prototype.widgetFullName || i;
			t.fn[i] = function(r) {
				var a = "string" == typeof r,
					l = s.call(arguments, 1),
					h = this;
				return r = !a && l.length ? t.widget.extend.apply(null, [r].concat(l)) : r, this.each(a ? function() {
					var s, o = t.data(this, n);
					return o ? t.isFunction(o[r]) && "_" !== r.charAt(0) ? (s = o[r].apply(o, l), s !== o && s !== e ? (h = s && s.jquery ? h.pushStack(s.get()) : s, !1) : void 0) : t.error("no such method '" + r + "' for " + i + " widget instance") : t.error("cannot call methods on " + i + " prior to initialization; attempted to call method '" + r + "'");
				} : function() {
					var e = t.data(this, n);
					e ? e.option(r || {})._init() : t.data(this, n, new o(r, this));
				}), h;
			};
		}, t.Widget = function() {}, t.Widget._childConstructors = [], t.Widget.prototype = {
			widgetName: "widget",
			widgetEventPrefix: "",
			defaultElement: "<div>",
			options: {
				disabled: !1,
				create: null
			},
			_createWidget: function(e, s) {
				s = t(s || this.defaultElement || this)[0], this.element = t(s), this.uuid = i++, this.eventNamespace = "." + this.widgetName + this.uuid,
					this.options = t.widget.extend({}, this.options, this._getCreateOptions(), e), this.bindings = t(),
					this.hoverable = t(), this.focusable = t(), s !== this && (t.data(s, this.widgetFullName, this),
						this._on(!0, this.element, {
							remove: function(t) {
								t.target === s && this.destroy();
							}
						}), this.document = t(s.style ? s.ownerDocument : s.document || s), this.window = t(this.document[0].defaultView || this.document[0].parentWindow)),
					this._create(), this._trigger("create", null, this._getCreateEventData()), this._init();
			},
			_getCreateOptions: t.noop,
			_getCreateEventData: t.noop,
			_create: t.noop,
			_init: t.noop,
			destroy: function() {
				this._destroy(), this.element.unbind(this.eventNamespace).removeData(this.widgetName).removeData(this.widgetFullName).removeData(t.camelCase(this.widgetFullName)),
					this.widget().unbind(this.eventNamespace).removeAttr("aria-disabled").removeClass(this.widgetFullName + "-disabled ui-state-disabled"),
					this.bindings.unbind(this.eventNamespace), this.hoverable.removeClass("ui-state-hover"),
					this.focusable.removeClass("ui-state-focus");
			},
			_destroy: t.noop,
			widget: function() {
				return this.element;
			},
			option: function(i, s) {
				var o, n, r, a = i;
				if (0 === arguments.length) return t.widget.extend({}, this.options);
				if ("string" == typeof i)
					if (a = {}, o = i.split("."), i = o.shift(), o.length) {
						for (n = a[i] = t.widget.extend({}, this.options[i]), r = 0; r < o.length - 1; r++) n[o[r]] = n[o[r]] || {},
							n = n[o[r]];
						if (i = o.pop(), s === e) return n[i] === e ? null : n[i];
						n[i] = s;
					} else {
						if (s === e) return this.options[i] === e ? null : this.options[i];
						a[i] = s;
					}
				return this._setOptions(a), this;
			},
			_setOptions: function(t) {
				var e;
				for (e in t) this._setOption(e, t[e]);
				return this;
			},
			_setOption: function(t, e) {
				return this.options[t] = e, "disabled" === t && (this.widget().toggleClass(this.widgetFullName + "-disabled ui-state-disabled", !!e).attr("aria-disabled", e),
						this.hoverable.removeClass("ui-state-hover"), this.focusable.removeClass("ui-state-focus")),
					this;
			},
			enable: function() {
				return this._setOption("disabled", !1);
			},
			disable: function() {
				return this._setOption("disabled", !0);
			},
			_on: function(e, i, s) {
				var o, n = this;
				"boolean" != typeof e && (s = i, i = e, e = !1), s ? (i = o = t(i), this.bindings = this.bindings.add(i)) : (s = i,
					i = this.element, o = this.widget()), t.each(s, function(s, r) {
					function a() {
						return e || n.options.disabled !== !0 && !t(this).hasClass("ui-state-disabled") ? ("string" == typeof r ? n[r] : r).apply(n, arguments) : void 0;
					}
					"string" != typeof r && (a.guid = r.guid = r.guid || a.guid || t.guid++);
					var l = s.match(/^(\w+)\s*(.*)$/),
						h = l[1] + n.eventNamespace,
						c = l[2];
					c ? o.delegate(c, h, a) : i.bind(h, a);
				});
			},
			_off: function(t, e) {
				e = (e || "").split(" ").join(this.eventNamespace + " ") + this.eventNamespace, t.unbind(e).undelegate(e);
			},
			_delay: function(t, e) {
				function i() {
					return ("string" == typeof t ? s[t] : t).apply(s, arguments);
				}
				var s = this;
				return setTimeout(i, e || 0);
			},
			_hoverable: function(e) {
				this.hoverable = this.hoverable.add(e), this._on(e, {
					mouseenter: function(e) {
						t(e.currentTarget).addClass("ui-state-hover");
					},
					mouseleave: function(e) {
						t(e.currentTarget).removeClass("ui-state-hover");
					}
				});
			},
			_focusable: function(e) {
				this.focusable = this.focusable.add(e), this._on(e, {
					focusin: function(e) {
						t(e.currentTarget).addClass("ui-state-focus");
					},
					focusout: function(e) {
						t(e.currentTarget).removeClass("ui-state-focus");
					}
				});
			},
			_trigger: function(e, i, s) {
				var o, n, r = this.options[e];
				if (s = s || {}, i = t.Event(i), i.type = (e === this.widgetEventPrefix ? e : this.widgetEventPrefix + e).toLowerCase(),
					i.target = this.element[0], n = i.originalEvent)
					for (o in n) o in i || (i[o] = n[o]);
				return this.element.trigger(i, s), !(t.isFunction(r) && r.apply(this.element[0], [i].concat(s)) === !1 || i.isDefaultPrevented());
			}
		}, t.each({
			show: "fadeIn",
			hide: "fadeOut"
		}, function(e, i) {
			t.Widget.prototype["_" + e] = function(s, o, n) {
				"string" == typeof o && (o = {
					effect: o
				});
				var r, a = o ? o === !0 || "number" == typeof o ? i : o.effect || i : e;
				o = o || {}, "number" == typeof o && (o = {
					duration: o
				}), r = !t.isEmptyObject(o), o.complete = n, o.delay && s.delay(o.delay), r && t.effects && t.effects.effect[a] ? s[e](o) : a !== e && s[a] ? s[a](o.duration, o.easing, n) : s.queue(function(i) {
					t(this)[e](), n && n.call(s[0]), i();
				});
			};
		});
	}(jQuery),
	function(t) {
		var e = !1;
		t(document).mouseup(function() {
			e = !1;
		}), t.widget("ui.mouse", {
			version: "1.10.3",
			options: {
				cancel: "input,textarea,button,select,option",
				distance: 1,
				delay: 0
			},
			_mouseInit: function() {
				var e = this;
				this.element.bind("mousedown." + this.widgetName, function(t) {
					return e._mouseDown(t);
				}).bind("click." + this.widgetName, function(i) {
					return !0 === t.data(i.target, e.widgetName + ".preventClickEvent") ? (t.removeData(i.target, e.widgetName + ".preventClickEvent"),
						i.stopImmediatePropagation(), !1) : void 0;
				}), this.started = !1;
			},
			_mouseDestroy: function() {
				this.element.unbind("." + this.widgetName), this._mouseMoveDelegate && t(document).unbind("mousemove." + this.widgetName, this._mouseMoveDelegate).unbind("mouseup." + this.widgetName, this._mouseUpDelegate);
			},
			_mouseDown: function(i) {
				if (!e) {
					this._mouseStarted && this._mouseUp(i), this._mouseDownEvent = i;
					var s = this,
						o = 1 === i.which,
						n = "string" == typeof this.options.cancel && i.target.nodeName ? t(i.target).closest(this.options.cancel).length : !1;
					return o && !n && this._mouseCapture(i) ? (this.mouseDelayMet = !this.options.delay, this.mouseDelayMet || (this._mouseDelayTimer = setTimeout(function() {
						s.mouseDelayMet = !0;
					}, this.options.delay)), this._mouseDistanceMet(i) && this._mouseDelayMet(i) && (this._mouseStarted = this._mouseStart(i) !== !1, !this._mouseStarted) ? (i.preventDefault(), !0) : (!0 === t.data(i.target, this.widgetName + ".preventClickEvent") && t.removeData(i.target, this.widgetName + ".preventClickEvent"),
						this._mouseMoveDelegate = function(t) {
							return s._mouseMove(t);
						}, this._mouseUpDelegate = function(t) {
							return s._mouseUp(t);
						}, t(document).bind("mousemove." + this.widgetName, this._mouseMoveDelegate).bind("mouseup." + this.widgetName, this._mouseUpDelegate),
						i.preventDefault(), e = !0, !0)) : !0;
				}
			},
			_mouseMove: function(e) {
				return t.ui.ie && (!document.documentMode || document.documentMode < 9) && !e.button ? this._mouseUp(e) : this._mouseStarted ? (this._mouseDrag(e),
					e.preventDefault()) : (this._mouseDistanceMet(e) && this._mouseDelayMet(e) && (this._mouseStarted = this._mouseStart(this._mouseDownEvent, e) !== !1,
					this._mouseStarted ? this._mouseDrag(e) : this._mouseUp(e)), !this._mouseStarted);
			},
			_mouseUp: function(e) {
				return t(document).unbind("mousemove." + this.widgetName, this._mouseMoveDelegate).unbind("mouseup." + this.widgetName, this._mouseUpDelegate),
					this._mouseStarted && (this._mouseStarted = !1, e.target === this._mouseDownEvent.target && t.data(e.target, this.widgetName + ".preventClickEvent", !0),
						this._mouseStop(e)), !1;
			},
			_mouseDistanceMet: function(t) {
				return Math.max(Math.abs(this._mouseDownEvent.pageX - t.pageX), Math.abs(this._mouseDownEvent.pageY - t.pageY)) >= this.options.distance;
			},
			_mouseDelayMet: function() {
				return this.mouseDelayMet;
			},
			_mouseStart: function() {},
			_mouseDrag: function() {},
			_mouseStop: function() {},
			_mouseCapture: function() {
				return !0;
			}
		});
	}(jQuery),
	function(t) {
		t.widget("ui.draggable", t.ui.mouse, {
			version: "1.10.3",
			widgetEventPrefix: "drag",
			options: {
				addClasses: !0,
				appendTo: "parent",
				axis: !1,
				connectToSortable: !1,
				containment: !1,
				cursor: "auto",
				cursorAt: !1,
				grid: !1,
				handle: !1,
				helper: "original",
				iframeFix: !1,
				opacity: !1,
				refreshPositions: !1,
				revert: !1,
				revertDuration: 500,
				scope: "default",
				scroll: !0,
				scrollSensitivity: 20,
				scrollSpeed: 20,
				snap: !1,
				snapMode: "both",
				snapTolerance: 20,
				stack: !1,
				zIndex: !1,
				drag: null,
				start: null,
				stop: null
			},
			_create: function() {
				"original" !== this.options.helper || /^(?:r|a|f)/.test(this.element.css("position")) || (this.element[0].style.position = "relative"),
					this.options.addClasses && this.element.addClass("ui-draggable"), this.options.disabled && this.element.addClass("ui-draggable-disabled"),
					this._mouseInit();
			},
			_destroy: function() {
				this.element.removeClass("ui-draggable ui-draggable-dragging ui-draggable-disabled"),
					this._mouseDestroy();
			},
			_mouseCapture: function(e) {
				var i = this.options;
				return this.helper || i.disabled || t(e.target).closest(".ui-resizable-handle").length > 0 ? !1 : (this.handle = this._getHandle(e),
					this.handle ? (t(i.iframeFix === !0 ? "iframe" : i.iframeFix).each(function() {
						t("<div class='ui-draggable-iframeFix' style='background: #fff;'></div>").css({
							width: this.offsetWidth + "px",
							height: this.offsetHeight + "px",
							position: "absolute",
							opacity: "0.001",
							zIndex: 1e3
						}).css(t(this).offset()).appendTo("body");
					}), !0) : !1);
			},
			_mouseStart: function(e) {
				var i = this.options;
				return this.helper = this._createHelper(e), this.helper.addClass("ui-draggable-dragging"),
					this._cacheHelperProportions(), t.ui.ddmanager && (t.ui.ddmanager.current = this), this._cacheMargins(),
					this.cssPosition = this.helper.css("position"), this.scrollParent = this.helper.scrollParent(),
					this.offsetParent = this.helper.offsetParent(), this.offsetParentCssPosition = this.offsetParent.css("position"),
					this.offset = this.positionAbs = this.element.offset(), this.offset = {
						top: this.offset.top - this.margins.top,
						left: this.offset.left - this.margins.left
					}, this.offset.scroll = !1, t.extend(this.offset, {
						click: {
							left: e.pageX - this.offset.left,
							top: e.pageY - this.offset.top
						},
						parent: this._getParentOffset(),
						relative: this._getRelativeOffset()
					}), this.originalPosition = this.position = this._generatePosition(e), this.originalPageX = e.pageX,
					this.originalPageY = e.pageY, i.cursorAt && this._adjustOffsetFromHelper(i.cursorAt),
					this._setContainment(), this._trigger("start", e) === !1 ? (this._clear(), !1) : (this._cacheHelperProportions(),
						t.ui.ddmanager && !i.dropBehaviour && t.ui.ddmanager.prepareOffsets(this, e), this._mouseDrag(e, !0),
						t.ui.ddmanager && t.ui.ddmanager.dragStart(this, e), !0);
			},
			_mouseDrag: function(e, i) {
				if ("fixed" === this.offsetParentCssPosition && (this.offset.parent = this._getParentOffset()),
					this.position = this._generatePosition(e), this.positionAbs = this._convertPositionTo("absolute"), !i) {
					var s = this._uiHash();
					if (this._trigger("drag", e, s) === !1) return this._mouseUp({}), !1;
					this.position = s.position;
				}
				return this.options.axis && "y" === this.options.axis || (this.helper[0].style.left = this.position.left + "px"),
					this.options.axis && "x" === this.options.axis || (this.helper[0].style.top = this.position.top + "px"),
					t.ui.ddmanager && t.ui.ddmanager.drag(this, e), !1;
			},
			_mouseStop: function(e) {
				var i = this,
					s = !1;
				return t.ui.ddmanager && !this.options.dropBehaviour && (s = t.ui.ddmanager.drop(this, e)),
					this.dropped && (s = this.dropped, this.dropped = !1), "original" !== this.options.helper || t.contains(this.element[0].ownerDocument, this.element[0]) ? ("invalid" === this.options.revert && !s || "valid" === this.options.revert && s || this.options.revert === !0 || t.isFunction(this.options.revert) && this.options.revert.call(this.element, s) ? t(this.helper).animate(this.originalPosition, parseInt(this.options.revertDuration, 10), function() {
						i._trigger("stop", e) !== !1 && i._clear();
					}) : this._trigger("stop", e) !== !1 && this._clear(), !1) : !1;
			},
			_mouseUp: function(e) {
				return t("div.ui-draggable-iframeFix").each(function() {
					this.parentNode.removeChild(this);
				}), t.ui.ddmanager && t.ui.ddmanager.dragStop(this, e), t.ui.mouse.prototype._mouseUp.call(this, e);
			},
			cancel: function() {
				return this.helper.is(".ui-draggable-dragging") ? this._mouseUp({}) : this._clear(),
					this;
			},
			_getHandle: function(e) {
				return this.options.handle ? !!t(e.target).closest(this.element.find(this.options.handle)).length : !0;
			},
			_createHelper: function(e) {
				var i = this.options,
					s = t.isFunction(i.helper) ? t(i.helper.apply(this.element[0], [e])) : "clone" === i.helper ? this.element.clone().removeAttr("id") : this.element;
				return s.parents("body").length || s.appendTo("parent" === i.appendTo ? this.element[0].parentNode : i.appendTo),
					s[0] === this.element[0] || /(fixed|absolute)/.test(s.css("position")) || s.css("position", "absolute"),
					s;
			},
			_adjustOffsetFromHelper: function(e) {
				"string" == typeof e && (e = e.split(" ")), t.isArray(e) && (e = {
						left: +e[0],
						top: +e[1] || 0
					}), "left" in e && (this.offset.click.left = e.left + this.margins.left), "right" in e && (this.offset.click.left = this.helperProportions.width - e.right + this.margins.left),
					"top" in e && (this.offset.click.top = e.top + this.margins.top), "bottom" in e && (this.offset.click.top = this.helperProportions.height - e.bottom + this.margins.top);
			},
			_getParentOffset: function() {
				var e = this.offsetParent.offset();
				return "absolute" === this.cssPosition && this.scrollParent[0] !== document && t.contains(this.scrollParent[0], this.offsetParent[0]) && (e.left += this.scrollParent.scrollLeft(),
					e.top += this.scrollParent.scrollTop()), (this.offsetParent[0] === document.body || this.offsetParent[0].tagName && "html" === this.offsetParent[0].tagName.toLowerCase() && t.ui.ie) && (e = {
					top: 0,
					left: 0
				}), {
					top: e.top + (parseInt(this.offsetParent.css("borderTopWidth"), 10) || 0),
					left: e.left + (parseInt(this.offsetParent.css("borderLeftWidth"), 10) || 0)
				};
			},
			_getRelativeOffset: function() {
				if ("relative" === this.cssPosition) {
					var t = this.element.position();
					return {
						top: t.top - (parseInt(this.helper.css("top"), 10) || 0) + this.scrollParent.scrollTop(),
						left: t.left - (parseInt(this.helper.css("left"), 10) || 0) + this.scrollParent.scrollLeft()
					};
				}
				return {
					top: 0,
					left: 0
				};
			},
			_cacheMargins: function() {
				this.margins = {
					left: parseInt(this.element.css("marginLeft"), 10) || 0,
					top: parseInt(this.element.css("marginTop"), 10) || 0,
					right: parseInt(this.element.css("marginRight"), 10) || 0,
					bottom: parseInt(this.element.css("marginBottom"), 10) || 0
				};
			},
			_cacheHelperProportions: function() {
				this.helperProportions = {
					width: this.helper.outerWidth(),
					height: this.helper.outerHeight()
				};
			},
			_setContainment: function() {
				var e, i, s, o = this.options;
				return o.containment ? "window" === o.containment ? void(this.containment = [t(window).scrollLeft() - this.offset.relative.left - this.offset.parent.left, t(window).scrollTop() - this.offset.relative.top - this.offset.parent.top, t(window).scrollLeft() + t(window).width() - this.helperProportions.width - this.margins.left, t(window).scrollTop() + (t(window).height() || document.body.parentNode.scrollHeight) - this.helperProportions.height - this.margins.top]) : "document" === o.containment ? void(this.containment = [0, 0, t(document).width() - this.helperProportions.width - this.margins.left, (t(document).height() || document.body.parentNode.scrollHeight) - this.helperProportions.height - this.margins.top]) : o.containment.constructor === Array ? void(this.containment = o.containment) : ("parent" === o.containment && (o.containment = this.helper[0].parentNode),
					i = t(o.containment), s = i[0], void(s && (e = "hidden" !== i.css("overflow"), this.containment = [(parseInt(i.css("borderLeftWidth"), 10) || 0) + (parseInt(i.css("paddingLeft"), 10) || 0), (parseInt(i.css("borderTopWidth"), 10) || 0) + (parseInt(i.css("paddingTop"), 10) || 0), (e ? Math.max(s.scrollWidth, s.offsetWidth) : s.offsetWidth) - (parseInt(i.css("borderRightWidth"), 10) || 0) - (parseInt(i.css("paddingRight"), 10) || 0) - this.helperProportions.width - this.margins.left - this.margins.right, (e ? Math.max(s.scrollHeight, s.offsetHeight) : s.offsetHeight) - (parseInt(i.css("borderBottomWidth"), 10) || 0) - (parseInt(i.css("paddingBottom"), 10) || 0) - this.helperProportions.height - this.margins.top - this.margins.bottom],
						this.relative_container = i))) : void(this.containment = null);
			},
			_convertPositionTo: function(e, i) {
				i || (i = this.position);
				var s = "absolute" === e ? 1 : -1,
					o = "absolute" !== this.cssPosition || this.scrollParent[0] !== document && t.contains(this.scrollParent[0], this.offsetParent[0]) ? this.scrollParent : this.offsetParent;
				return this.offset.scroll || (this.offset.scroll = {
					top: o.scrollTop(),
					left: o.scrollLeft()
				}), {
					top: i.top + this.offset.relative.top * s + this.offset.parent.top * s - ("fixed" === this.cssPosition ? -this.scrollParent.scrollTop() : this.offset.scroll.top) * s,
					left: i.left + this.offset.relative.left * s + this.offset.parent.left * s - ("fixed" === this.cssPosition ? -this.scrollParent.scrollLeft() : this.offset.scroll.left) * s
				};
			},
			_generatePosition: function(e) {
				var i, s, o, n, r = this.options,
					a = "absolute" !== this.cssPosition || this.scrollParent[0] !== document && t.contains(this.scrollParent[0], this.offsetParent[0]) ? this.scrollParent : this.offsetParent,
					l = e.pageX,
					h = e.pageY;
				return this.offset.scroll || (this.offset.scroll = {
					top: a.scrollTop(),
					left: a.scrollLeft()
				}), this.originalPosition && (this.containment && (this.relative_container ? (s = this.relative_container.offset(),
							i = [this.containment[0] + s.left, this.containment[1] + s.top, this.containment[2] + s.left, this.containment[3] + s.top]) : i = this.containment,
						e.pageX - this.offset.click.left < i[0] && (l = i[0] + this.offset.click.left), e.pageY - this.offset.click.top < i[1] && (h = i[1] + this.offset.click.top),
						e.pageX - this.offset.click.left > i[2] && (l = i[2] + this.offset.click.left), e.pageY - this.offset.click.top > i[3] && (h = i[3] + this.offset.click.top)),
					r.grid && (o = r.grid[1] ? this.originalPageY + Math.round((h - this.originalPageY) / r.grid[1]) * r.grid[1] : this.originalPageY,
						h = i ? o - this.offset.click.top >= i[1] || o - this.offset.click.top > i[3] ? o : o - this.offset.click.top >= i[1] ? o - r.grid[1] : o + r.grid[1] : o,
						n = r.grid[0] ? this.originalPageX + Math.round((l - this.originalPageX) / r.grid[0]) * r.grid[0] : this.originalPageX,
						l = i ? n - this.offset.click.left >= i[0] || n - this.offset.click.left > i[2] ? n : n - this.offset.click.left >= i[0] ? n - r.grid[0] : n + r.grid[0] : n)), {
					top: h - this.offset.click.top - this.offset.relative.top - this.offset.parent.top + ("fixed" === this.cssPosition ? -this.scrollParent.scrollTop() : this.offset.scroll.top),
					left: l - this.offset.click.left - this.offset.relative.left - this.offset.parent.left + ("fixed" === this.cssPosition ? -this.scrollParent.scrollLeft() : this.offset.scroll.left)
				};
			},
			_clear: function() {
				this.helper.removeClass("ui-draggable-dragging"), this.helper[0] === this.element[0] || this.cancelHelperRemoval || this.helper.remove(),
					this.helper = null, this.cancelHelperRemoval = !1;
			},
			_trigger: function(e, i, s) {
				return s = s || this._uiHash(), t.ui.plugin.call(this, e, [i, s]), "drag" === e && (this.positionAbs = this._convertPositionTo("absolute")),
					t.Widget.prototype._trigger.call(this, e, i, s);
			},
			plugins: {},
			_uiHash: function() {
				return {
					helper: this.helper,
					position: this.position,
					originalPosition: this.originalPosition,
					offset: this.positionAbs
				};
			}
		}), t.ui.plugin.add("draggable", "connectToSortable", {
			start: function(e, i) {
				var s = t(this).data("ui-draggable"),
					o = s.options,
					n = t.extend({}, i, {
						item: s.element
					});
				s.sortables = [], t(o.connectToSortable).each(function() {
					var i = t.data(this, "ui-sortable");
					i && !i.options.disabled && (s.sortables.push({
						instance: i,
						shouldRevert: i.options.revert
					}), i.refreshPositions(), i._trigger("activate", e, n));
				});
			},
			stop: function(e, i) {
				var s = t(this).data("ui-draggable"),
					o = t.extend({}, i, {
						item: s.element
					});
				t.each(s.sortables, function() {
					this.instance.isOver ? (this.instance.isOver = 0, s.cancelHelperRemoval = !0, this.instance.cancelHelperRemoval = !1,
						this.shouldRevert && (this.instance.options.revert = this.shouldRevert), this.instance._mouseStop(e),
						this.instance.options.helper = this.instance.options._helper, "original" === s.options.helper && this.instance.currentItem.css({
							top: "auto",
							left: "auto"
						})) : (this.instance.cancelHelperRemoval = !1, this.instance._trigger("deactivate", e, o));
				});
			},
			drag: function(e, i) {
				var s = t(this).data("ui-draggable"),
					o = this;
				t.each(s.sortables, function() {
					var n = !1,
						r = this;
					this.instance.positionAbs = s.positionAbs, this.instance.helperProportions = s.helperProportions,
						this.instance.offset.click = s.offset.click, this.instance._intersectsWith(this.instance.containerCache) && (n = !0,
							t.each(s.sortables, function() {
								return this.instance.positionAbs = s.positionAbs, this.instance.helperProportions = s.helperProportions,
									this.instance.offset.click = s.offset.click, this !== r && this.instance._intersectsWith(this.instance.containerCache) && t.contains(r.instance.element[0], this.instance.element[0]) && (n = !1),
									n;
							})), n ? (this.instance.isOver || (this.instance.isOver = 1, this.instance.currentItem = t(o).clone().removeAttr("id").appendTo(this.instance.element).data("ui-sortable-item", !0),
							this.instance.options._helper = this.instance.options.helper, this.instance.options.helper = function() {
								return i.helper[0];
							}, e.target = this.instance.currentItem[0], this.instance._mouseCapture(e, !0), this.instance._mouseStart(e, !0, !0),
							this.instance.offset.click.top = s.offset.click.top, this.instance.offset.click.left = s.offset.click.left,
							this.instance.offset.parent.left -= s.offset.parent.left - this.instance.offset.parent.left,
							this.instance.offset.parent.top -= s.offset.parent.top - this.instance.offset.parent.top,
							s._trigger("toSortable", e), s.dropped = this.instance.element, s.currentItem = s.element,
							this.instance.fromOutside = s), this.instance.currentItem && this.instance._mouseDrag(e)) : this.instance.isOver && (this.instance.isOver = 0,
							this.instance.cancelHelperRemoval = !0, this.instance.options.revert = !1, this.instance._trigger("out", e, this.instance._uiHash(this.instance)),
							this.instance._mouseStop(e, !0), this.instance.options.helper = this.instance.options._helper,
							this.instance.currentItem.remove(), this.instance.placeholder && this.instance.placeholder.remove(),
							s._trigger("fromSortable", e), s.dropped = !1);
				});
			}
		}), t.ui.plugin.add("draggable", "cursor", {
			start: function() {
				var e = t("body"),
					i = t(this).data("ui-draggable").options;
				e.css("cursor") && (i._cursor = e.css("cursor")), e.css("cursor", i.cursor);
			},
			stop: function() {
				var e = t(this).data("ui-draggable").options;
				e._cursor && t("body").css("cursor", e._cursor);
			}
		}), t.ui.plugin.add("draggable", "opacity", {
			start: function(e, i) {
				var s = t(i.helper),
					o = t(this).data("ui-draggable").options;
				s.css("opacity") && (o._opacity = s.css("opacity")), s.css("opacity", o.opacity);
			},
			stop: function(e, i) {
				var s = t(this).data("ui-draggable").options;
				s._opacity && t(i.helper).css("opacity", s._opacity);
			}
		}), t.ui.plugin.add("draggable", "scroll", {
			start: function() {
				var e = t(this).data("ui-draggable");
				e.scrollParent[0] !== document && "HTML" !== e.scrollParent[0].tagName && (e.overflowOffset = e.scrollParent.offset());
			},
			drag: function(e) {
				var i = t(this).data("ui-draggable"),
					s = i.options,
					o = !1;
				i.scrollParent[0] !== document && "HTML" !== i.scrollParent[0].tagName ? (s.axis && "x" === s.axis || (i.overflowOffset.top + i.scrollParent[0].offsetHeight - e.pageY < s.scrollSensitivity ? i.scrollParent[0].scrollTop = o = i.scrollParent[0].scrollTop + s.scrollSpeed : e.pageY - i.overflowOffset.top < s.scrollSensitivity && (i.scrollParent[0].scrollTop = o = i.scrollParent[0].scrollTop - s.scrollSpeed)),
						s.axis && "y" === s.axis || (i.overflowOffset.left + i.scrollParent[0].offsetWidth - e.pageX < s.scrollSensitivity ? i.scrollParent[0].scrollLeft = o = i.scrollParent[0].scrollLeft + s.scrollSpeed : e.pageX - i.overflowOffset.left < s.scrollSensitivity && (i.scrollParent[0].scrollLeft = o = i.scrollParent[0].scrollLeft - s.scrollSpeed))) : (s.axis && "x" === s.axis || (e.pageY - t(document).scrollTop() < s.scrollSensitivity ? o = t(document).scrollTop(t(document).scrollTop() - s.scrollSpeed) : t(window).height() - (e.pageY - t(document).scrollTop()) < s.scrollSensitivity && (o = t(document).scrollTop(t(document).scrollTop() + s.scrollSpeed))),
						s.axis && "y" === s.axis || (e.pageX - t(document).scrollLeft() < s.scrollSensitivity ? o = t(document).scrollLeft(t(document).scrollLeft() - s.scrollSpeed) : t(window).width() - (e.pageX - t(document).scrollLeft()) < s.scrollSensitivity && (o = t(document).scrollLeft(t(document).scrollLeft() + s.scrollSpeed)))),
					o !== !1 && t.ui.ddmanager && !s.dropBehaviour && t.ui.ddmanager.prepareOffsets(i, e);
			}
		}), t.ui.plugin.add("draggable", "snap", {
			start: function() {
				var e = t(this).data("ui-draggable"),
					i = e.options;
				e.snapElements = [], t(i.snap.constructor !== String ? i.snap.items || ":data(ui-draggable)" : i.snap).each(function() {
					var i = t(this),
						s = i.offset();
					this !== e.element[0] && e.snapElements.push({
						item: this,
						width: i.outerWidth(),
						height: i.outerHeight(),
						top: s.top,
						left: s.left
					});
				});
			},
			drag: function(e, i) {
				var s, o, n, r, a, l, h, c, p, u, f = t(this).data("ui-draggable"),
					d = f.options,
					g = d.snapTolerance,
					m = i.offset.left,
					v = m + f.helperProportions.width,
					_ = i.offset.top,
					b = _ + f.helperProportions.height;
				for (p = f.snapElements.length - 1; p >= 0; p--) a = f.snapElements[p].left, l = a + f.snapElements[p].width,
					h = f.snapElements[p].top, c = h + f.snapElements[p].height, a - g > v || m > l + g || h - g > b || _ > c + g || !t.contains(f.snapElements[p].item.ownerDocument, f.snapElements[p].item) ? (f.snapElements[p].snapping && f.options.snap.release && f.options.snap.release.call(f.element, e, t.extend(f._uiHash(), {
						snapItem: f.snapElements[p].item
					})), f.snapElements[p].snapping = !1) : ("inner" !== d.snapMode && (s = Math.abs(h - b) <= g, o = Math.abs(c - _) <= g,
						n = Math.abs(a - v) <= g, r = Math.abs(l - m) <= g, s && (i.position.top = f._convertPositionTo("relative", {
							top: h - f.helperProportions.height,
							left: 0
						}).top - f.margins.top), o && (i.position.top = f._convertPositionTo("relative", {
							top: c,
							left: 0
						}).top - f.margins.top), n && (i.position.left = f._convertPositionTo("relative", {
							top: 0,
							left: a - f.helperProportions.width
						}).left - f.margins.left), r && (i.position.left = f._convertPositionTo("relative", {
							top: 0,
							left: l
						}).left - f.margins.left)), u = s || o || n || r, "outer" !== d.snapMode && (s = Math.abs(h - _) <= g,
						o = Math.abs(c - b) <= g, n = Math.abs(a - m) <= g, r = Math.abs(l - v) <= g, s && (i.position.top = f._convertPositionTo("relative", {
							top: h,
							left: 0
						}).top - f.margins.top), o && (i.position.top = f._convertPositionTo("relative", {
							top: c - f.helperProportions.height,
							left: 0
						}).top - f.margins.top), n && (i.position.left = f._convertPositionTo("relative", {
							top: 0,
							left: a
						}).left - f.margins.left), r && (i.position.left = f._convertPositionTo("relative", {
							top: 0,
							left: l - f.helperProportions.width
						}).left - f.margins.left)), !f.snapElements[p].snapping && (s || o || n || r || u) && f.options.snap.snap && f.options.snap.snap.call(f.element, e, t.extend(f._uiHash(), {
						snapItem: f.snapElements[p].item
					})), f.snapElements[p].snapping = s || o || n || r || u);
			}
		}), t.ui.plugin.add("draggable", "stack", {
			start: function() {
				var e, i = this.data("ui-draggable").options,
					s = t.makeArray(t(i.stack)).sort(function(e, i) {
						return (parseInt(t(e).css("zIndex"), 10) || 0) - (parseInt(t(i).css("zIndex"), 10) || 0);
					});
				s.length && (e = parseInt(t(s[0]).css("zIndex"), 10) || 0, t(s).each(function(i) {
					t(this).css("zIndex", e + i);
				}), this.css("zIndex", e + s.length));
			}
		}), t.ui.plugin.add("draggable", "zIndex", {
			start: function(e, i) {
				var s = t(i.helper),
					o = t(this).data("ui-draggable").options;
				s.css("zIndex") && (o._zIndex = s.css("zIndex")), s.css("zIndex", o.zIndex);
			},
			stop: function(e, i) {
				var s = t(this).data("ui-draggable").options;
				s._zIndex && t(i.helper).css("zIndex", s._zIndex);
			}
		});
	}(jQuery);
});
define("biz_web/utils/upload.js", ["widget/upload.css", "biz_web/lib/webuploader.js", "common/wx/dialog.js", "common/wx/Tips.js", "tpl/uploader.html.js"], function(e) {
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
		u = function(e) {
			var i = n.Uploader;
			0 == i.support("flash") ? p("<p>未安装或未正确配置flash插件，请检查后重试。<br><a href='http://get.adobe.com/cn/flashplayer/' target='_blank'>到adobe去下载flash插件</a></p>") : 0 == i.support() ? p("<p>您的浏览器不支持上传</p>") : e.refresh();
		},
		m = function(e) {
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
					Math.random() < .1 && m(12), u(t);
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
				return Math.random() < .1 && m(13), e.canContinueUpload && !e.canContinueUpload() ? !1 : !(e.onSelect && e.onSelect(null, i.id, i) === !1);
			}), t.on("fileQueued", function(e) {
				var i = {
					id: e.id,
					fileName: e.name,
					size: n.formatSize(e.size)
				};
				p.append(r(o, i)).show();
			}), t.on("fileDequeued", function() {
				Math.random() < .1 && m(14), e.onCancel && e.onCancel();
			}), t.on("uploadProgress", function(i, n) {
				var t = "#uploadItem%s".sprintf(i.id),
					a = p.find(t).find(".progress_bar_thumb");
				a.width("%s%".sprintf(100 * n)), 1 == n && p.find(t).find(".js_cancel").remove(), e.onProgress && e.onProgress(null, i.id, i, {
					percentage: n
				});
			}), t.on("uploadStart", function(e) {
				g[e.id] = +new Date;
			}), t.on("uploadSuccess", function(n, t, o) {
				if (Math.random() < .1 && m(16), t && t.base_resp) {
					var r = +t.base_resp.ret;
					if (0 == r) Math.random() < .1 && (m(17), g[n.id] && i(+new Date - g[n.id]));
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
				Math.random() < .1 && m(15), e.onError && e.onError();
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
define("common/wx/media/appmsg.js", ["widget/media.css", "common/wx/time.js", "tpl/media/appmsg.html.js", "common/qq/Class.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict", e("widget/media.css");
		var i = wx.T,
			s = e("common/wx/time.js"),
			o = e("tpl/media/appmsg.html.js"),
			u = e("common/qq/Class.js"),
			a = u.declare({
				init: function(e) {
					if (!e || !e.container) return;
					e.data = e.data || $.extend({}, e);
					var t = e.data,
						n = t.multi_item || [],
						r = n.length,
						i = null,
						u = [];
					if (r <= 0) return;
					i = n[0];
					for (var a = 1; a < r; ++a) u.push(n[a]);
					var f = {
						id: t.app_id,
						type: e.type,
						file_id: t.file_id,
						time: t.create_time ? s.timeFormat(t.create_time) : "",
						isMul: r > 1,
						first: i,
						list: u,
						token: wx.data.t,
						showEdit: e.showEdit || !1,
						showMask: e.showMask || !1
					};
					$(e.container).html(wx.T(o, f)).data("opt", e), this.renderData = f;
				},
				getData: function() {
					return this.renderData;
				}
			});
		return a;
	} catch (f) {
		wx.jslog({
			src: "common/wx/media/appmsg.js"
		}, f);
	}
});
define("common/wx/tooltip.js", ["tpl/tooltip.html.js", "widget/tooltip.css"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = e("tpl/tooltip.html.js");
		e("widget/tooltip.css");
		var s = {
				dom: "",
				content: "",
				position: {
					x: 0,
					y: 0
				}
			},
			o = function(e) {
				this.options = e = $.extend(!0, {}, s, e), this.$dom = $(this.options.dom), this.init();
			};
		o.prototype = {
			constructor: o,
			init: function() {
				var e = this;
				e.pops = [], e.$dom.each(function() {
					var t = $(this),
						n = t.data("tooltip"),
						r = $(template.compile(i)(n ? $.extend(!0, {}, e.options, {
							content: n
						}) : e.options));
					e.pops.push(r), $("body").append(r), r.css("display", "none"), t.on("mouseenter", function() {
						var n = t.offset();
						r.css({
							top: n.top - (e.options.position.y || 0) - r.height(),
							left: n.left + t.width() / 2 - r.width() / 2 + (e.options.position.x || 0)
						}), r.show();
					}).on("mouseleave", function() {
						r.hide();
					}), t.data("tooltip_pop", r);
				});
			},
			show: function() {
				var e = this,
					t = 0,
					n = e.pops.length;
				for (var t = 0; t < n; t++) e.pops[t].show();
			},
			hide: function() {
				var e = this,
					t = 0,
					n = e.pops.length;
				for (var t = 0; t < n; t++) e.pops[t].hide();
			}
		}, n.exports = o;
	} catch (u) {
		wx.jslog({
			src: "common/wx/tooltip.js"
		}, u);
	}
});
define("common/wx/media/cardmsg.js", ["widget/media.css", "common/wx/time.js", "tpl/media/cardmsg.html.js", "biz_common/utils/norefererimg.js", "common/qq/Class.js", "cardticket/send_card.js", "common/wx/Tips.js"], function(t) {
	"use strict";
	t("widget/media.css");
	var e = wx.T,
		i = (t("common/wx/time.js"), t("tpl/media/cardmsg.html.js")),
		s = t("biz_common/utils/norefererimg.js"),
		n = t("common/qq/Class.js"),
		a = t("cardticket/send_card.js"),
		d = t("common/wx/Tips.js"),
		r = n.declare({
			type: 16,
			init: function(t) {
				this.opt = t.opt, this.info = t.infos[this.type], this.index = this.info && this.info.index,
					this.data = this.opt.data, this.msgSender = t;
			},
			getData: function() {
				var t = $.extend(!0, {
					cardid: this.data.id,
					cardnum: this.data.cardnum
				}, this.data);
				return t.cardtype = t.type, t.type = this.type, t;
			},
			click: function() {
				var t = this;
				return this.send_card = new a({
					multi: !1,
					param: {
						need_member_card: 1
					},
					selectComplete: function(e, i) {
						return e ? (e.cardnum = i, t.fillData(e), void t.msgSender.select(t.index)) : void d.err("请选择卡券");
					},
					source: "直接群发卡券"
				}), this.send_card.show(), !1;
			},
			fillData: function(t) {
				this.data = t, this.msgSender.type = this.type;
				var n = e(i, t),
					a = $("." + this.info.selector).html(n),
					d = a.find(".js_logourl");
				d.length && s({
					img: d[0]
				}), this.msgSender.select(this.index), a.wrapInner("<div/>").children().append('<a href="javascript:;" class="link_dele" onclick="this.parentNode.parentNode.removeChild(this.parentNode);">删除</a>');
			},
			isValidate: function() {
				return this.data.id ? !0 : (d.err("请选择卡券"), !1);
			}
		});
	return r;
});
define("common/wx/media/video.js", ["widget/media/richvideo.css", "widget/media.css", "biz_web/lib/video.js", "common/wx/Cgi.js", "common/wx/time.js", "common/qq/Class.js", "biz_web/lib/swfobject.js", "tpl/media/video.html.js", "tpl/media/simple_videomsg.html.js", "tpl/media/wxvideo.html.js", "tpl/media/videomsg.html.js"], function(e) {
	"use strict";
	e("widget/media/richvideo.css"), e("widget/media.css");
	var i, t = e("biz_web/lib/video.js"),
		o = e("common/wx/Cgi.js"),
		d = e("common/wx/time.js"),
		s = e("common/qq/Class.js"),
		n = e("biz_web/lib/swfobject.js"),
		a = e("tpl/media/video.html.js"),
		r = wx.T,
		l = wx.data.t,
		m = document,
		c = !!n.ua.pv[0],
		f = m.createElement("video"),
		u = navigator.userAgent.toLowerCase(),
		v = /msie/.test(u),
		p = /firefox/.test(u);
	t.options.flash.swf = wx.path.video;
	var h = {
			id: "",
			source: "",
			type: "",
			file_id: ""
		},
		w = 5e3,
		g = function(e) {
			if (e.video_url) {
				{
					var i = "tmp" + (1e5 * Math.random() | 0);
					$('<video id="%s"></video>'.sprintf(i)).appendTo("body");
				}
				t("#" + i).ready(function() {
					$("#" + i).hide();
					var t = this;
					this.on("error", function() {
						t.dispose(), e.dom.find(".loading_tips").show(), e.video_url = "", setTimeout(function() {
							g(e);
						}, w);
					}), this.on("loadedmetadata", function() {
						t.dispose(), $(e.selector).children().remove(), e.for_transfer = !1, e.digest = e.digest ? e.digest.html(!1) : "",
							new _(e);
					});
					var o = e.video_url;
					t.src(f.canPlayType ? o : [{
						type: "video/x-flv",
						src: o + "&trans=1"
					}]), t.play();
				});
			} else o.get({
				url: wx.url("/cgi-bin/appmsg?action=get_video_url&videoid=%s".sprintf(e.video_id)),
				error: function() {
					setTimeout(function() {
						g(e);
					}, w);
				}
			}, function(i) {
				e.video_url = i.video_url || "", e.video_download_url = i.video_download_url || "", setTimeout(function() {
					g(e);
				}, w);
			});
		},
		_ = s.declare({
			init: function(t) {
				var o = this;
				$(t.selector).data("opt", t), t = $.extend(!0, {}, h, t), o.id = t.id, o.source = t.source, o.file_id = t.file_id,
					o.type = t.type, o.video_url = t.video_url, o.tpl = t.tpl, o.ff_must_flash = t.ff_must_flash,
					t.src = o.getVideoURL(), t.token = l || wx.data.t, t.time = t.create_time ? d.timeFormat(t.create_time) : "",
					t.digest = t.digest ? t.digest.replace(/<br.*>/g, "\n").html() : "", t.for_network = "string" == typeof t.video_url ? !t.video_url : !t.content,
					i = e(t.sent ? "tpl/media/simple_videomsg.html.js" : 21 == +t.type || 9 == +t.type || 11 == +t.type ? "tpl/media/wxvideo.html.js" : "tpl/media/videomsg.html.js");
				var s = $("videomsg" == t.tpl ? r(i, t) : r(a, t));
				o.dom = t.dom = $(t.selector).append(s), "videomsg" == t.tpl && t.for_transfer && g(t, o.dom),
					o.dom.find(".video_desc").length && o.dom.find(".video_desc").html(o.dom.find(".video_desc").attr("data-digest").replace(/\n/g, "<br>")),
					o.dom.find(".wxVideoScreenshot").on("click", function() {
						o.dom.find(".mediaContent").css({
							height: "auto"
						}), o.play(t.play);
					}), o.dom.find(".wxNetworkVideo").on("click", function() {
						window.open($(this).attr("data-contenturl"));
					}), o.dom.find(".video_switch").click(function() {
						o.dom.find(".mediaContent").css({
							height: "104px"
						}), o.pause(t.pause);
					});
			},
			getVideoURL: function() {
				var e = this.source,
					i = this.id,
					t = (this.msg_id || "", this.file_id);
				return e && (e = "&source=" + e), this.video_url || "/cgi-bin/getvideodata?msgid={msgid}&fileid={fileid}&token={token}{source}".format({
					msgid: i,
					fileid: t,
					source: e,
					token: wx.data.t
				});
			},
			canPlayType: function() {
				this.type;
				return !f.canPlayType && !c;
			},
			play: function(e) {
				var i = this;
				if (i.canPlayType()) return void alert("您当前浏览器无法播放视频，请安装Flash插件/更换Chrome浏览器");
				var o = this.id,
					d = this.player;
				if (d) return $("#wxVideoBox" + o).addClass("wxVideoPlaying").find(".wxVideoPlayContent").show(),
					d.play(), e && e(this);
				var s = i.getVideoURL();
				$("#wxVideoBox" + o).addClass("wxVideoPlaying").find(".wxVideoPlayContent").show();
				var n = "videomsg" == i.tpl ? {
					width: "100%",
					height: "100%"
				} : {};
				t("#wxVideo" + o, n).ready(function() {
					d = this;
					var t = 0;
					return d.on("fullscreenchange", function() {
						t ? ($("#wxVideoPlayer" + o).css({
							overflow: "hidden",
							zoom: "1"
						}), $("#wxVideoBox" + o).css({
							"z-index": "0"
						})) : ($("#wxVideoPlayer" + o).css({
							overflow: "visible",
							zoom: "normal"
						}), $("#wxVideoBox" + o).css({
							"z-index": "1"
						})), t = ~t;
					}), d.on("ended", function() {
						this.currentTime(0);
					}), d.src(v || !f.canPlayType || i.ff_must_flash && p ? [{
						type: "video/x-flv",
						src: s + "&trans=1"
					}] : s), d.play(), i.player = d, e && e(this);
				});
			},
			pause: function(e) {
				var i = this.player;
				i && i.pause(), $("#wxVideoBox" + this.id).removeClass("wxVideoPlaying").find(".wxVideoPlayContent").hide(),
					e && e(this);
			}
		});
	return _;
});
define("common/wx/media/img.js", ["widget/media.css", "tpl/media/img.html.js", "common/qq/Class.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = wx.T,
			s = e("widget/media.css"),
			o = e("tpl/media/img.html.js"),
			u = e("common/qq/Class.js"),
			a = u.declare({
				init: function(e) {
					if (!e || !e.container) return;
					var t = e;
					$(e.container).html(o.format({
						token: wx.data.t,
						id: e.file_id,
						msgid: e.msgid || "",
						source: e.source,
						ow: ~e.fakeid
					})).data("opt", e), this.data = t;
				},
				getData: function() {
					return this.data;
				}
			});
		return a;
	} catch (f) {
		wx.jslog({
			src: "common/wx/media/img.js"
		}, f);
	}
});
define("common/wx/media/audio.js", ["biz_web/lib/soundmanager2.js", "tpl/media/audio.html.js", "tpl/media/qqmusicaudio.html.js", "widget/media.css", "common/qq/Class.js"], function(i, s, t) {
	"use strict";
	var e = wx.T,
		o = i("biz_web/lib/soundmanager2.js"),
		d = i("tpl/media/audio.html.js"),
		l = i("tpl/media/qqmusicaudio.html.js"),
		n = (i("widget/media.css"),
			i("common/qq/Class.js")),
		u = null,
		a = null,
		r = "wxAudioPlaying",
		h = function() {
			a = o, a.setup({
				url: "/mpres/zh_CN/htmledition/plprecorder/biz_web/",
				preferFlash: !1,
				debugMode: !1
			});
		};
	$(window).load(function() {
		h();
	});
	var c = {
			id: "",
			source: "",
			file_id: ""
		},
		m = n.declare({
			init: function(i) {
				var s = this;
				$.extend(!0, s, c, i), this.soundId = this.id || this.file_id, s.play_length = Math.ceil(1 * s.play_length / 1e3),
					this.qqmusictpl && (d = l);
				var t = $(e(d, s));
				s.dom = $(i.selector).append(t).data("opt", i), t.click(function() {
					s.toggle();
				});
			},
			getAudioURL: function() {
				if (this.qqmusicurl) return this.qqmusicurl;
				var i = this.source,
					s = this.id,
					t = this.file_id;
				return i && (i = "&source=" + i), wx.url("/cgi-bin/getvoicedata?msgid={id}&fileid={fileid}{source}".format({
					id: s,
					fileid: t,
					source: i
				}));
			},
			isPlaying: function() {
				return null != u && this == u;
			},
			toggle: function() {
				this.isPlaying() ? this.stop() : (u && u.stop(), this.play());
			},
			play: function(i) {
				var s = this;
				this.isPlaying() || (this.dom.addClass(r), !!u && u.dom.removeClass(r), u = this, this.sound || (!a && h(),
					this.sound = a.createSound({
						id: s.soundId,
						url: s.getAudioURL(),
						onfinish: function() {
							u && (u.dom.removeClass(r), u = null);
						}
					})), a.play(this.soundId), i && i(this));
			},
			stop: function(i) {
				this.isPlaying() && (u = null, this.dom.removeClass(r), a.stop(this.soundId), i && i(this));
			}
		});
	t.exports = m;
});
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
define("media/media_dialog.js", ["widget/media/media_dialog.css", "widget/media/richvideo.css", "common/wx/popup.js", "common/wx/Tips.js", "media/media_cgi.js", "biz_web/utils/upload.js", "biz_web/ui/checkbox.js", "common/wx/pagebar.js", "common/wx/media/imageDialog.js", "common/wx/media/videoDialog.js", "common/wx/media/audio.js", "common/wx/media/img.js", "common/wx/media/video.js", "common/wx/media/appmsg.js", "cardticket/send_card.js", "common/wx/time.js", "tpl/media/dialog/file_layout.html.js", "tpl/media/dialog/appmsg_layout.html.js", "tpl/media/dialog/videomsg_layout.html.js", "tpl/media/dialog/file.html.js"], function(e) {
	"use strict";

	function i(e, i, t, o, n) {
		{
			var a = e / i + 1;
			new _({
				container: $(".pageNavigator", r.popup("get")),
				perPage: i,
				first: !1,
				last: !1,
				isSimple: !0,
				initShowPage: a,
				totalItemsNum: t,
				callback: function(e) {
					var t = e.currentPage;
					t != a && (t--, n.begin = i * t, K.key && (n.key = K.key), o(n));
				}
			});
		}
	}

	function t(e, i, t, n, a, s) {
		r && r.popup("remove"), 15 == t && (e = "videomsg");
		var m = T++;
		if (r = $(c(F[e], {
				tpl: i,
				upload_id: m,
				type: t,
				token: wx.data.t
			}).trim()).popup({
				title: "选择素材",
				className: u,
				width: 960,
				onOK: function() {
					return a && !a() ? !0 : (this.remove(), r = null, void(document.body.style.overflow = document.documentElement.style.overflow = "auto"));
				},
				onCancel: function() {
					this.remove(), r = null, document.body.style.overflow = document.documentElement.style.overflow = "auto";
				}
			}), document.body.style.overflow = document.documentElement.style.overflow = "hidden",
			p = r.popup("get"), j({
				container: "#js_media_dialog_upload" + m,
				type: t,
				onAllComplete: function() {
					g.suc("上传成功"), s.begin = 0, d(s);
				}
			}), n) {
			if ("file" == e && ($(".js_media_list", p).html(c(J, {
				list: n
			})), $(".frm_radio[type=radio]", p).checkbox(!1), $(".media_content", p).each(function() {
				var e = $(this),
					i = e.data("id"),
					t = e.data("type"),
					o = E[i];
				o && t && P[t] && P[t](e, o);
			})), "appmsg" == e) {
				for (var l = n.length, f = $(".js_appmsg_list .inner", p), v = 0; l > v; ++v) {
					var _ = n[v],
						h = f.eq(v % 2),
						w = _.app_id,
						y = $('<div id="appmsg%s"></div>'.sprintf(w), h).appendTo(h);
					new b({
						container: y,
						data: _,
						showMask: !0
					});
				}
				K.key && ($("#msgSearchInput").val(K.key), $(".appmsg_title>a").each(function(e, i) {
					$(i).html($(i).text().replace(new RegExp(K.key, "g"), '<span class="highlight">' + K.key + "</span>"));
				}));
			}
			if ("videomsg" == e) {
				var x = p.find("#js_videomsg_list").find(".inner");
				n.each(function(e, i) {
					var o = x.eq(i % 2),
						n = $('<div id="appmsg%s"></div>'.sprintf(e.app_id), o).appendTo(o);
					P[t] && P[t](n, e);
				});
			}
			r.popup("resetPosition"), o();
		}
	}

	function o() {
		(10 == K.type || 11 == K.type) && ($("#searchCloseBt").click(function() {
			$("#msgSearchInput").val(""), $("#msgSearchBtn").trigger("click");
		}), $("#msgSearchBtn").click(function() {
			K.key = $("#msgSearchInput").val().length > 0 ? $("#msgSearchInput").val() : "", m(K);
		}), $("#msgSearchInput").keydown(function(e) {
			var i = "which" in e ? e.which : e.keyCode;
			13 == i && $("#msgSearchBtn").trigger("click");
		}));
	}

	function n(e) {
		h({
			scene: "biz",
			maxSelect: 1,
			desc: "大小: 不超过2M,    格式: bmp, png, jpeg, jpg, gif",
			onOK: function(i) {
				var t = {
					file_id: i[0].file_id,
					source: "file"
				};
				e.onSelect && e.onSelect(e.type, t), this.destroy();
			},
			onHide: function() {
				this.destroy();
			}
		});
	}

	function a(e) {
		new w({
			onOK: function(i, t) {
				var o = $.extend({}, t);
				return o ? (e.onSelect && e.onSelect(i, o), !0) : !1;
			}
		});
	}

	function d(e) {
		var o = e.type,
			s = e.onSelect,
			m = e.count || 10,
			l = e.begin || 0;
		return 2 == o ? void n(e) : 21 == o ? void a(e) : (t("file", "loading", o), void f.getList(o, l, m, function(n) {
			if (r) {
				var a = {
						2: "img_cnt",
						3: "voice_cnt",
						4: "video_cnt"
					},
					c = n.file_item,
					u = n.file_cnt[a[o]];
				c.length <= 0 ? t("file", "no-media", o, c, null, e) : (t("file", "files", o, c, function() {
					var e = p.find('[name="media"]:checked').val(),
						i = $("#fileItem" + e).data("opt");
					return i ? (s(o, i), !0) : !1;
				}, e), i(l, m, u, d, e));
			}
		}));
	}

	function s(e) {
		return e.find(".appmsg.selected,.Js_videomsg.selected");
	}

	function m(e) {
		var o = e.type,
			n = e.onSelect,
			d = e.count || 10,
			l = e.key || "",
			c = e.begin || 0;
		return K = $.extend(!0, {}, e), 15 == o ? void a(e) : (t("appmsg", "loading", o), void f.appmsg.getList(o, c, d, function(a) {
			if (r) {
				var l = {
						10: "app_msg_cnt",
						11: "commondity_msg_cnt",
						15: "video_msg_cnt"
					},
					u = a.item,
					f = a.file_cnt[l[o]];
				K.key && (f = a.search_cnt), u.length <= 0 ? t("appmsg", "no-media", o, u, null, e) : (t("appmsg", "files", o, u, function() {
					var e = s(p).parent().data("opt");
					return e ? (n(o, e), !0) : !1;
				}), i(c, d, f, m, e), 15 == o ? (p.on("click", ".Js_videomsg", function() {
					$(this).find(".loading_tips").length ? g.err("视频在转码中，不能选择") : "" != $(this).find(".title").text().trim() && (p.find(".Js_videomsg").removeClass("selected"),
						$(this).addClass("selected"));
				}), p.on("mouseenter", ".Js_videomsg", function() {
					"" == $(this).find(".title").text().trim() && $(this).addClass("no_title");
				}), p.on("mouseleave", ".Js_videomsg", function() {
					$(this).removeClass("no_title");
				})) : p.on("click", ".appmsg", function() {
					s(p).removeClass("selected"), $(this).addClass("selected");
				}));
			}
		}, l));
	}

	function l(e) {
		var i = e.onSelect,
			t = e.type,
			o = new S({
				multi: !1,
				selectComplete: function(e) {
					return e ? (i(t, e), void(o = null)) : void g.err("请选择卡券");
				},
				param: {
					need_member_card: 1
				},
				source: "直接群发卡券"
			});
		o.show();
	}
	e("widget/media/media_dialog.css"), e("widget/media/richvideo.css"), e("common/wx/popup.js");
	var c = wx.T,
		r = null,
		p = null,
		u = "media align_edge",
		g = e("common/wx/Tips.js"),
		f = e("media/media_cgi.js"),
		v = e("biz_web/utils/upload.js"),
		_ = (e("biz_web/ui/checkbox.js"),
			e("common/wx/pagebar.js")),
		h = e("common/wx/media/imageDialog.js"),
		w = e("common/wx/media/videoDialog.js"),
		j = v.uploadBizFile,
		y = (template.render,
			e("common/wx/media/audio.js")),
		x = e("common/wx/media/img.js"),
		k = e("common/wx/media/video.js"),
		b = e("common/wx/media/appmsg.js"),
		S = e("cardticket/send_card.js"),
		C = e("common/wx/time.js"),
		I = C.timeFormat,
		z = e("tpl/media/dialog/file_layout.html.js"),
		D = e("tpl/media/dialog/appmsg_layout.html.js"),
		B = e("tpl/media/dialog/videomsg_layout.html.js"),
		J = e("tpl/media/dialog/file.html.js"),
		T = 1,
		E = {},
		F = {
			appmsg: D,
			videomsg: B,
			file: z
		};
	template.helper("mediaDialogtimeFormat", function(e) {
		return I(e);
	}), template.helper("mediaDialogInit", function(e) {
		return e.file_id ? (E[e.file_id] = e, "") : "";
	});
	var P = {
			2: function(e, i) {
				return new x({
					container: $("#" + e.attr("id")),
					file_id: i.file_id,
					source: "file",
					fakeid: i.fakeid
				});
			},
			3: function(e, i) {
				var t = i;
				return t.selector = "#" + e.attr("id"), t.source = "file", new y(t);
			},
			4: function(e, i) {
				var t = i;
				return t.selector = "#" + e.attr("id"), t.id = t.file_id, t.source = "file", new k(t);
			},
			15: function(e, i) {
				var t = i;
				return t.selector = e, t.id = t.app_id, t.tpl = "videomsg", t.for_selection = !0, t.for_transfer = !!t.content,
					t.hide_transfer = !!t.content, t.video_id = t.content, new k(t);
			}
		},
		K = {};
	return {
		getFile: d,
		getAppmsg: m,
		getCardList: l
	};
});
define("common/wx/richEditor/emotionEditor.js", ["widget/emotion_editor.css", "tpl/richEditor/emotionEditor.html.js", "common/wx/richEditor/wysiwyg.js", "common/wx/richEditor/emotion.js", "biz_web/utils/upload.js", "common/wx/Tips.js", "common/qq/Class.js"], function(t, i, e) {
	"use strict";
	var o = wx.T,
		n = (t("widget/emotion_editor.css"), t("tpl/richEditor/emotionEditor.html.js")),
		r = t("common/wx/richEditor/wysiwyg.js"),
		s = t("common/wx/richEditor/emotion.js"),
		m = t("biz_web/utils/upload.js"),
		c = t("common/wx/Tips.js"),
		l = m.uploadCdnFile,
		d = t("common/qq/Class.js"),
		a = {
			isHTML: !0,
			wordlimit: 500,
			hideUpload: !0
		},
		w = 1,
		h = d.declare({
			init: function(t, i) {
				var e = this;
				i = this.opt = $.extend(!0, {}, a, i), e.selector$ = t, i.gid = w++, e.selector$.html(o(n, i)),
					e.emotion = new s(t.find(".js_emotionArea")), e.wysiwyg = new r(t.find(".js_editorArea"), {
						init: function() {
							t.find(".js_editorTip").html("还可以输入<em>{l}</em>字".format({
								l: i.wordlimit
							}));
						},
						textFilter: function(t) {
							return t = e.emotion.getEmotionText(t).replace(/<br.*?>/gi, "\n").replace(/<.*?>/g, ""),
								t = t.html(!1), i.isHTML ? t : t.replace(/<br.*?>/gi, "\n").replace(/<.*?>/g, "");
						},
						nodeFilter: function(t) {
							var i = "";
							return "IMG" === t.nodeName.toUpperCase() && (i = t), i;
						},
						change: function() {
							var o = e.getContent(),
								n = i.wordlimit - o.length,
								r = t.find(".js_editorTip");
							r.html(0 > n ? "已超出<em{cls}>{l}</em>字".format({
								l: -n,
								cls: ' class="warn"'
							}) : "还可以输入<em>{l}</em>字".format({
								l: n
							}));
						}
					}), e.upload = l({
						container: t.find(".js_upload"),
						type: 2,
						multi: !1,
						onComplete: function(t, i, o, n) {
							if (n && n.base_resp && 0 == n.base_resp.ret) {
								var r = n.content;
								c.suc("上传成功"), e.wysiwyg.insertHTML(r);
							}
						}
					}), e._initEvent(), e.insertHTML(i.text);
			},
			_initEvent: function() {
				var t = $(".js_switch", this.selector$),
					i = this.emotion,
					e = this.wysiwyg;
				i.click(function(t) {
					return e.insertHTML(i.getEmotionHTML(t)), !1;
				}), i.hide(), t.click(function() {
					i.show();
				}), $(document).on("click", "*", function(t) {
					var e = $(t.target),
						o = e.filter(".js_switch"),
						n = e.filter(".js_emotion_i"),
						r = e.filter(".emotions_item");
					o.length || n.length || r.length || i.hide();
				});
			},
			setContent: function(t) {
				var i = t || "";
				i = this.opt.linebreak ? i.replace(/\n/g, "<br>") : i, t = s.emoji(i), this.wysiwyg.setContent(t, i.html(!1));
			},
			insertHTML: function(t) {
				t = t || "", this.wysiwyg.insertHTML(t);
			},
			getContent: function() {
				var t = this.wysiwyg.getContent();
				return t = "string" == typeof t ? t.trim() : "", this.opt.linebreak ? t.replace(/<br[^>]*>/gi, "\n") : t;
			},
			getHTML: function() {
				var t = this.wysiwyg.getHTML().html(!1);
				return "string" == typeof t ? t.trim() : "";
			}
		});
	e.exports = h;
});