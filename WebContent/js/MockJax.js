define("common/wx/media/imageDialog.js", ["common/wx/Cgi.js", "common/wx/Tips.js", "common/wx/popup.js", "common/wx/pagebar.js", "biz_web/utils/upload.js", "tpl/media/dialog/image_layout.html.js", "tpl/media/dialog/image_list.html.js", "tpl/media/dialog/image_group.html.js", "page/media/dialog_img_pick.css"], function(e) {
	"use strict";
	var i = e("common/wx/Cgi.js"),
		t = e("common/wx/Tips.js"),
		n = (e("common/wx/popup.js"),
			e("common/wx/pagebar.js")),
		o = e("biz_web/utils/upload.js"),
		a = e("tpl/media/dialog/image_layout.html.js"),
		r = e("tpl/media/dialog/image_list.html.js"),
		l = e("tpl/media/dialog/image_group.html.js"),
		s = (template.render,
			template.compile(l)),
		d = template.compile(r);
	e("page/media/dialog_img_pick.css");
	var g = function(e) {
			return new p(e);
		},
		p = function(e) {
			this.options = e, this.events = [], this.imgArr = [], this.converting = 0, this.fromUpload = [],
				c.init.call(this);
		},
		c = {
			init: function() {
				var e = this,
					i = e.options = $.extend(!0, {
						tpl: a,
						title: "选择图片",
						scene: "cdn",
						maxSelect: 1,
						perPage: 10,
						group: 1,
						onOK: null,
						onCancel: null
					}, e.options);
				i.tpl = template.compile(i.tpl)(i), e.on("ok", i.onOK), e.on("cancel", i.onCancel), e.on("hide", i.onHide),
					e.dialog = $(i.tpl.trim()).popup({
						title: i.title,
						className: "img_dialog_wrp",
						width: 846,
						buttons: [{
							text: "确定",
							type: "disabled",
							click: function() {
								var n = this.get().find(".js_btn").eq(0).parent();
								return n.hasClass("btn_disabled") ? void t.err("请选择图片") : (e.popup = this, $.each(e.imgArr, function(i, t) {
									t.source = -1 != e.fromUpload.indexOf(t.file_id + "") ? "upload" : "lib";
								}), void("cdn" == i.scene && e.converting > 0 ? (n.btn(!1), e.on("converted", function() {
									0 == e.converting && (e.trigger("ok", e.imgArr || []), n.btn(!0));
								})) : e.trigger("ok", e.imgArr || [])));
							}
						}, {
							text: "取消",
							click: function() {
								e.trigger("cancel"), this.hide();
							}
						}],
						onHide: function() {
							e.trigger("hide");
						}
					}), e.dialog.popup("get").find(".js_loading").show(), u.getImagesByGroupId({
						group_id: i.group,
						count: i.perPage
					}, function(t) {
						if (e.dialog) {
							var n = t.page_info;
							n.scene = i.scene, n.group = i.group;
							var o = e.dialog.popup("get"),
								a = s(n);
							o.find(".js_loading").hide(), o.find(".js_group").append(a).find(".js_total").text("(%s)".sprintf(n.file_cnt.img_cnt)),
								c.renderImageList(o.find(".js_list"), n, e.imgArr), c.initEvent.call(e, t), c.initPageBar.call(e, n, i.group),
								e.dialog.popup("resetPosition");
						}
					}), c.initUpload.call(e, i.group);
			},
			initEvent: function() {
				var e = this,
					i = e.dialog.popup("get"),
					n = e.options;
				i.on("click", ".js_imageitem", function() {
					var o, a = $(this),
						r = a.find("label"),
						l = i.find(".js_btn_p").eq(0),
						s = a.data("url"),
						d = a.data("id"),
						g = a.data("format");
					r.hasClass("selected") ? (s || e.converting--, r.removeClass("selected"), o = m.indexOf(e.imgArr, d),
							o >= 0 && e.imgArr.splice(o, 1), i.find(".js_selected").text(e.imgArr.length)) : 1 == n.maxSelect ? (s || (e.converting = 1),
							r.addClass("selected"), a.siblings().find("label").removeClass("selected"), e.imgArr = [{
								url: s,
								file_id: d,
								format: g
							}], i.find(".js_selected").text(e.imgArr.length)) : n.maxSelect > e.imgArr.length ? (s || e.converting++,
							r.addClass("selected"), e.imgArr.push({
								url: s,
								file_id: d,
								format: g
							}), i.find(".js_selected").text(e.imgArr.length)) : t.err("最多可选%s张".sprintf(n.maxSelect)),
						e.imgArr.length > 0 ? l.enable().addClass("btn_primary") : l.disable(), "cdn" == n.scene && r.hasClass("selected") && !s && u.getCdnUrlByFileId({
							file_id: d,
							group_id: i.find(".js_groupitem.selected").data("groupid")
						}, function(i) {
							0 == i.errcode ? (e.converting--, a.data("url", i.url), o = m.indexOf(e.imgArr, d), o >= 0 && (e.imgArr[o].url = i.url),
								e.trigger("converted")) : (t.err("转存失败"), a.click());
						});
				}), i.on("click", ".js_groupitem", function(t, o) {
					var a = $(this),
						r = i.find(".js_list"),
						l = i.find(".js_loading"),
						s = i.find(".js_pagebar"),
						d = a.data("groupid");
					a.hasClass("selected") || (a.addClass("selected").siblings(".selected").removeClass("selected"),
						$("#js_imageupload").data("groupid", d), r.hide(), s.hide(), l.show(), u.getImagesByGroupId({
							group_id: d,
							count: n.perPage
						}, function(t) {
							if (e.dialog && d == i.find(".js_groupitem.selected").data("groupid")) {
								t = t.page_info, t.scene = n.scene, l.hide(), s.show(), c.renderImageList(r, t, e.imgArr),
									c.initPageBar.call(e, t, d), c.initUpload.call(e);
								for (var a = 0; o && "upload" == o.source && a < o.count; ++a) r.children().eq(a).click();
							}
						}));
				});
			},
			initPageBar: function(e, i) {
				var t = this,
					o = t.dialog.popup("get"),
					a = t.options;
				c.pagebar && c.pagebar.destroy();
				var r = 0;
				return 0 == i ? r = e.file_cnt.img_cnt : e.file_group_list.file_group.each(function(e) {
					e.id == i && (r = e.count);
				}), a.perPage >= r ? void o.find(".js_pagebar").empty() : void(c.pagebar = new n({
					container: o.find(".js_pagebar"),
					perPage: a.perPage,
					initShowPage: 1,
					totalItemsNum: r,
					first: !1,
					last: !1,
					isSimple: !0,
					callback: function(e) {
						var i = o.find(".js_groupitem.selected").data("groupid"),
							n = o.find(".js_list"),
							r = o.find(".js_loading"),
							l = o.find(".js_pagebar");
						n.hide(), l.hide(), r.show(), u.getImagesByGroupId({
							group_id: i,
							begin: e.perPage * (e.currentPage - 1),
							count: a.perPage
						}, function(e) {
							e = e.page_info, e.scene = a.scene, r.hide(), l.show(), c.renderImageList(n, e, t.imgArr);
						});
					}
				}));
			},
			initUpload: function(e) {
				var i = this,
					n = i.dialog.popup("get"),
					a = n.find(".js_imageupload"),
					r = "js_imageupload" + Math.random().toString().substr(2),
					l = n.find(".js_groupitem.selected").data("groupid") || e || 1;
				a.attr("id", r).off().children().remove(), o.uploadImageLibFile({
					container: "#" + r,
					multi: !0,
					type: 2,
					doublewrite: !0,
					groupid: l,
					onComplete: function(e, n, o, a) {
						0 == a.base_resp.ret && t.suc("上传成功"), i.fromUpload.push(a.content);
					},
					onAllComplete: function(e, i) {
						var t, o = n.find(".js_groupitem.selected");
						i.filesUploaded > 0 && (t = +o.find("span").text(), o.removeClass("selected").trigger("click", {
							source: "upload",
							count: i.filesUploaded
						}), o.find("span").text(t + i.filesUploaded));
					},
					showError: !0
				});
			},
			renderImageList: function(e, i, t) {
				i.file_item.each(function(e) {
					e.img_url = wx.url("/cgi-bin/getimgdata?mode=small&source=file&fileId=%s".sprintf(e.file_id)), -1 != m.indexOf(t, e.file_id) && (e.selected = 1);
				}), e.html(d(i)).show();
			}
		},
		u = {
			getImagesByGroupId: function(e, t) {
				e = $.extend({
					group_id: 1,
					begin: 0,
					count: 8,
					type: 2
				}, e), i.get({
					url: wx.url("/cgi-bin/filepage"),
					data: e,
					mask: !1
				}, function(e) {
					0 != e.base_resp.ret ? i.show(e) : t(e);
				});
			},
			getCdnUrlByFileId: function(e, t) {
				e.group_id = e.group_id || 1, i.post({
					url: wx.url("/cgi-bin/uploadimg2cdn?action=duplicate"),
					data: e,
					mask: !1
				}, function(e) {
					t(e);
				});
			}
		},
		m = {
			indexOf: function(e, i) {
				for (var t = 0, n = e.length; n > t; ++t)
					if (e[t].file_id == i) return t;
				return -1;
			}
		},
		f = {
			on: function(e, i) {
				if (i) {
					var t = this.events;
					return t[e] = t[e] || [], t[e].push(i), this;
				}
			},
			trigger: function(e) {
				var i = this,
					t = arguments,
					n = i.events[e];
				return n ? ($.each(n, function(e, n) {
					n.apply(i, Array.prototype.slice.call(t, 1));
				}), this) : void 0;
			},
			hide: function() {
				return this.dialog.popup("hide"), this;
			},
			show: function() {
				return this.dialog.popup("show"), this;
			},
			destroy: function() {
				this.dialog.popup("remove"), this.dialog = null;
			}
		};
	return $.extend(p.prototype, f), g;
});
define("common/wx/pagebar.js", ["widget/pagination.css", "tpl/pagebar.html.js", "common/qq/Class.js", "common/wx/Tips.js"], function(t, e) {
	"use strict";
	var i, n, s, a = (t("widget/pagination.css"), t("tpl/pagebar.html.js")),
		r = t("common/qq/Class.js"),
		h = t("common/wx/Tips.js");
	s = template.compile(a), i = e, n = {
		first: "首页",
		last: "末页",
		prev: "上页",
		next: "下页",
		startPage: 1,
		initShowPage: 1,
		perPage: 10,
		startRange: 1,
		midRange: 3,
		endRange: 1,
		totalItemsNum: 0,
		container: "",
		callback: null,
		isNavHide: !1,
		isSimple: !0
	};
	var o = function(t, e, i) {
			var n;
			return n = t + (e - 1), n = n > i ? i : n;
		},
		g = function(t, e, i) {
			var n;
			return n = i % 2 === 0 ? e - (i / 2 - 1) : e - (i - 1) / 2, n = t > n ? t : n;
		},
		u = function(t, e, i) {
			var n;
			return n = e % 2 === 0 ? parseInt(t) + e / 2 : parseInt(t) + (e - 1) / 2, n = n > i ? i : n;
		},
		c = function(t, e, i) {
			var n;
			return n = e - (i - 1), n = t > n ? t : n;
		},
		l = function(t, e) {
			if (e[t] && isNaN(e[t])) throw new Error("Invalid arguments: " + t + " should be a number");
		},
		p = function(t) {
			if (l("perPage", t), l("totalItemsNum", t), l("startPage", t), l("startRange", t), l("midRange", t),
				l("endRange", t), l("initShowPage", t), void 0 !== t.callback && null !== t.callback && !$.isFunction(t.callback)) throw new Error("Invalid arguments: callback should be a function");
		},
		d = function(t, e, i) {
			var n = t.container.find(".page_" + i);
			if ("string" == typeof e) {
				var s = $(e);
				0 !== s.length && (n = s);
			} else {
				if (e !== !1) throw new Error("Invalid Paramter: '" + i + "' should be a string or false");
				n.hide();
			}
			return n;
		},
		P = r.declare({
			init: function(t) {
				if (t.totalItemsNum) {
					var e;
					if (p(t), e = $.extend(!0, {}, n, t), this._init(e), e.initShowPage < e.startPage) throw new Error("Invalid arguments: initShowPage should be larger than startPage");
					if (e.initShowPage > e.endPage) throw new Error("Invalid arguments: initShowPage should be smaller than endPage");
					this.paginate();
				}
			},
			_init: function(t) {
				this.currentPage = t.initShowPage, this._isNextButtonShow = !0, this._isPrevButtonShow = !0,
					this.uid = "wxPagebar_" + +new Date, this.initEventCenter(), this.optionsForTemplate = {},
					$.extend(this, t), this.container = $(t.container), this.optionsForTemplate.isSimple = t.isSimple,
					this.optionsForTemplate.firstButtonText = 0 === $(t.first).length ? t.first : n.first, this.optionsForTemplate.lastButtonText = 0 === $(t.last).length ? t.last : n.last,
					this.optionsForTemplate.nextButtonText = 0 === $(t.next).length ? t.next : n.next, this.optionsForTemplate.prevButtonText = 0 === $(t.prev).length ? t.prev : n.prev,
					this.optionsForTemplate.isNavHide = t.isNavHide, this.generatePages(parseInt(this.totalItemsNum)),
					this.gapForStartRange = this.container.find(".gap_prev"), this.gapForEndRange = this.container.find(".gap_next"),
					this.firstButton = d(this, t.first, "first"), this.lastButton = d(this, t.last, "last"), this.prevButton = d(this, t.prev, "prev"),
					this.nextButton = d(this, t.next, "next"), this.bindEvent();
			},
			initEventCenter: function() {
				this.eventCenter = {
					eventList: [],
					bind: function(t, e) {
						this.eventList[t] || (this.eventList[t] = []), this.eventList[t].push(e);
					},
					trigger: function(t) {
						var e, i;
						this.eventList[t] || (this.eventList[t] = []), e = this.eventList[t];
						for (var n = 0; n < e.length; n++)
							if (i = Array.prototype.slice.call(arguments, 1), e[n].apply(this, i) === !1) return !1;
					},
					unbind: function(t, e) {
						if (!this.eventList) throw new Error("The eventList was undefined");
						if (!this.eventList[t]) throw new Error("The event type " + t + " was not found");
						if (void 0 === e) this.eventList[t] = [];
						else
							for (var i = this.eventList[t], n = i.length, s = 0; n > s; s++)
								if (i[s] === e) {
									i.splice(s, 1);
									break;
								}
					}
				};
			},
			generatePages: function(t) {
				var e, i, n, a, r, h;
				for (this.pageNum = Math.ceil(t / this.perPage), this.endPage = this.startPage + this.pageNum - 1,
					this.gapForStartRange = null, this.gapForEndRange = null, this.optionsForTemplate.startRange = [],
					this.optionsForTemplate.midRange = [], this.optionsForTemplate.endRange = [], i = o(this.startPage, this.startRange, this.endPage),
					n = g(this.startPage, this.currentPage, this.midRange), a = u(this.currentPage, this.midRange, this.endPage),
					r = c(this.startPage, this.endPage, this.endRange), i >= r && (r = i + 1), e = this.startPage; i >= e; e += 1) this.optionsForTemplate.startRange.push(e);
				for (var l = 0, e = n; l < this.midRange; l += 1, e += 1) this.optionsForTemplate.midRange.push(e);
				for (e = r; e <= this.endPage; e += 1) this.optionsForTemplate.endRange.push(e);
				this.optionsForTemplate.endPage = this.endPage, this.optionsForTemplate.initShowPage = this.initShowPage,
					h = s(this.optionsForTemplate), this.container.html(h), 1 == this.pageNum ? this.container.hide() : this.container.show(),
					this.pages = this.container.find(".page_nav"), this.midPages = this.container.find(".js_mid"),
					this.labels = this.container.find(".page_num label"), this.container.find(".pagination").attr("id", this.uid);
			},
			paginate: function() {
				var t, e, i, n, s, a, r, h, l, p;
				if (this.isSimple === !0)
					for (var d = 0, P = this.labels.length; P > d; d++) d % 2 === 0 && $(this.labels[d]).html(parseInt(this.currentPage));
				else {
					e = o(this.startPage, this.startRange, this.endPage), a = g(this.startPage, this.currentPage, this.midRange),
						r = u(this.currentPage, this.midRange, this.endPage), h = c(this.startPage, this.endPage, this.endRange),
						e >= h && (h = e + 1), e >= a && (a = e + 1), r >= h && (r = h - 1), this.pages.show(), this.pages.removeClass("current"),
						p = parseInt(this.midPages.length / this.midRange);
					for (var d = 0, P = p; P > d; d++) {
						for (s = 0, t = a; r >= t; t += 1) n = this.midRange * d + (t - a), l = $(this.midPages[n]), l.html(t), s += 1,
							t == this.currentPage && l.addClass("current");
						for (n = this.midRange * d + s; s < this.midRange; s += 1) l = $(this.midPages[n]), l.hide(), l.removeClass("current"),
							n += 1;
					}
					for (var d = 0, P = this.pages.length; P >= d; d++) i = $(this.pages[d]), t = parseInt(i.html()),
						t === parseInt(this.currentPage) && i.addClass("current");
					if (a > e + 1 ? this.gapForStartRange.show() : this.gapForStartRange.hide(), h > r + 1 ? this.gapForEndRange.show() : this.gapForEndRange.hide(),
						this.isNavHide) {
						for (var d = this.startPage; d <= this.endPage; d += 1) this.pages.hide();
						this.gapForStartRange.hide(), this.gapForEndRange.hide();
					}
				}
				this.checkButtonShown();
			},
			destroy: function() {
				this.container.off("click", "#" + this.uid + " a.page_nav"), this.container.off("click", "#" + this.uid + " a.page_go"),
					this.container.off("keydown", "#" + this.uid + " .goto_area input"), this.nextButton.off("click"),
					this.prevButton.off("click"), this.firstButton.off("click"), this.lastButton.off("click");
			},
			bindEvent: function() {
				this.container.on("click", "#" + this.uid + " a.page_nav", this.proxy(function(t) {
					var e = $(t.target);
					return e.hasClass("current") ? !1 : (this.clickPage(parseInt(e.html())), !1);
				}, this)), this.nextButton.on("click", this.proxy(function(t) {
					$(t.target);
					return this.nextPage(), !1;
				}, this)), this.prevButton.on("click", this.proxy(function(t) {
					$(t.target);
					return this.prevPage(), !1;
				}, this)), this.firstButton.on("click", this.proxy(function(t) {
					$(t.target);
					return this.goFirstPage(), !1;
				}, this)), this.lastButton.on("click", this.proxy(function(t) {
					$(t.target);
					return this.goLastPage(), !1;
				}, this)), this.container.on("click", "#" + this.uid + " a.page_go", this.proxy(function(t) {
					var e = $(t.target).prev();
					return this.goPage(e.val()), !1;
				}, this)), this.container.on("keydown", "#" + this.uid + " .goto_area input", this.proxy(function(t) {
					wx.isHotkey(t, "enter") && this.container.find("a.page_go").click();
				}, this));
			},
			on: function(t, e) {
				this.eventCenter.bind(t, this.proxy(e, this));
			},
			callbackFunc: function(t) {
				var e = {
					currentPage: this.currentPage,
					perPage: this.perPage,
					count: this.pageNum
				};
				return $.isFunction(this.callback) && this.callback(e) === !1 ? !1 : this.eventCenter.trigger(t, e) === !1 ? !1 : void this.paginate();
			},
			proxy: function(t, e) {
				return function() {
					var i = Array.prototype.slice.call(arguments, 0);
					return t.apply(e, i);
				};
			},
			nextPage: function() {
				this.currentPage !== this.endPage && (this.currentPage++, this.callbackFunc("next") === !1 && this.currentPage--);
			},
			prevPage: function() {
				this.currentPage !== this.startPage && (this.currentPage--, this.callbackFunc("prev") === !1 && this.currentPage++);
			},
			goFirstPage: function() {
				var t = this.currentPage;
				this.currentPage = this.startPage, this.callbackFunc("goFirst") === !1 && (this.currentPage = t);
			},
			goLastPage: function() {
				var t = this.currentPage;
				this.currentPage = this.endPage, this.callbackFunc("goLast") === !1 && (this.currentPage = t);
			},
			checkButtonShown: function() {
				+this.currentPage === +this.startPage ? this.hidePrevButton() : this.showPrevButton(), +this.currentPage === +this.endPage ? this.hideNextButton() : this.showNextButton();
			},
			goPage: function(t) {
				var e = this.currentPage;
				return t === this.currentPage ? !1 : isNaN(t) ? (h.err("请输入正确的页码"), !1) : "" === t ? !1 : t < this.startPage ? (h.err("请输入正确的页码"), !1) : t > this.endPage ? (h.err("请输入正确的页码"), !1) : (this.currentPage = t, void(this.callbackFunc("go") === !1 && (this.currentPage = e)));
			},
			clickPage: function(t) {
				var e = this.currentPage;
				isNaN(t) && (t = this.startPage), this.currentPage = t < this.startPage ? this.startPage : t > this.endPage ? this.endPage : t,
					this.callbackFunc("click") === !1 && (this.currentPage = e);
			},
			showNextButton: function() {
				this.nextButton && this._isNextButtonShow === !1 && (this.nextButton.show(), this._isNextButtonShow = !0);
			},
			showPrevButton: function() {
				this.prevButton && this._isPrevButtonShow === !1 && (this.prevButton.show(), this._isPrevButtonShow = !0);
			},
			hideNextButton: function() {
				this.nextButton && this._isNextButtonShow === !0 && (this.nextButton.hide(), this._isNextButtonShow = !1);
			},
			hidePrevButton: function() {
				this.prevButton && this._isPrevButtonShow === !0 && (this.prevButton.hide(), this._isPrevButtonShow = !1);
			}
		});
	return e = P;
});
define("media/media_cgi.js", ["common/wx/Tips.js", "common/wx/Cgi.js"], function(e) {
	"use strict";
	var r = e("common/wx/Tips.js"),
		s = e("common/wx/Cgi.js"),
		a = {
			del: function(e, a) {
				s.post({
					mask: !1,
					url: wx.url("/cgi-bin/operate_appmsg?sub=del&t=ajax-response"),
					data: {
						AppMsgId: e
					},
					error: function() {
						r.err("删除失败");
					}
				}, function(e) {
					"0" == e.ret ? (r.suc("删除成功"), a && a(e)) : r.err("删除失败");
				});
			},
			del_sv: function(e, a) {
				s.post({
					mask: !1,
					url: wx.url("/cgi-bin/modifyfile?oper=del&t=ajax-response"),
					data: {
						fileid: e
					},
					error: function() {
						r.err("删除失败");
					}
				}, function(e) {
					e.base_resp && 0 == +e.base_resp.ret ? (r.suc("删除成功"), a.suc && a.suc(e)) : (r.err("删除失败"),
						a.fail && a.fail(e));
				});
			},
			edit_sv: function(e, a) {
				s.post({
					mask: !1,
					url: wx.url("/cgi-bin/modifyfile?oper=rename&t=ajax-response"),
					data: {
						fileid: e.id,
						filename: e.name
					},
					error: function() {
						r.err("编辑失败");
					}
				}, function(e) {
					e.base_resp && 0 == +e.base_resp.ret ? (r.suc("编辑成功"), a.suc && a.suc(e)) : (r.err("编辑失败"),
						a.fail && a.fail(e));
				});
			},
			save: function(e, a, t, i, n, o) {
				var c = wx.url(t.AppMsgId ? "/cgi-bin/operate_appmsg?t=ajax-response&sub=update&type=%s".sprintf(a) : "/cgi-bin/operate_appmsg?t=ajax-response&sub=create&type=%s".sprintf(a));
				t.ajax = 1, s.post({
					url: c,
					data: t,
					dataType: "html",
					error: function(e, s) {
						"timeout" != s && r.err("保存失败"), n && n(!1, -1);
					},
					complete: o
				}, function(s) {
					if (s = $.parseJSON(s), "0" == s.ret) r.suc("保存成功"), i && i(s);
					else {
						var a = !1;
						switch (s.ret) {
							case "64506":
								r.err("保存失败,链接不合法");
								break;

							case "64507":
								r.err("内容不能包含链接，请调整");
								break;

							case "64508":
								r.err("查看原文链接可能具备安全风险，请检查");
								break;

							case "-99":
								r.err("内容超出字数，请调整");
								break;

							case "10801":
								r.err("标题不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"), a = s.msg;
								break;

							case "10802":
								r.err("作者不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"), a = s.msg;
								break;

							case "10803":
								r.err("敏感链接，请重新添加。"), a = s.msg;
								break;

							case "10804":
								r.err(e ? "正文不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。" : "摘要不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"),
									a = s.msg;
								break;

							case "10806":
								r.err("正文不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。"), a = s.msg;
								break;

							case "-20000":
								r.err("登录态超时，请重新登录。");
								break;

							case "15801":
							case "15802":
							case "15803":
							case "15804":
							case "15805":
							case "15806":
								break;

							default:
								r.err("保存失败");
						}
						n && n(a, s.ret);
					}
				});
			},
			preview: function(e, a, t, i, n) {
				s.post({
					url: wx.url("/cgi-bin/operate_appmsg?sub=preview&t=ajax-appmsg-preview&type=%s".sprintf(a)),
					data: t,
					dataType: "html",
					error: function() {
						r.err("发送失败，请稍后重试"), n && n();
					}
				}, function(s) {
					if (s = $.parseJSON(s), "0" == s.ret) r.suc("发送预览成功，请留意你的手机微信"), i && i(s);
					else {
						switch (s.ret) {
							case "64501":
								s.word = "你输入的帐号不存在，请重新输入";
								break;

							case "64502":
								s.word = "你输入的微信号不存在，请重新输入";
								break;

							case "10700":
							case "64503":
								s.word = "你尚未关注公众号，请先关注";
								break;

							case "10703":
								s.word = "对方关闭了接收消息";
								break;

							case "10701":
								s.word = "用户已被加入黑名单，无法向其发送消息";
								break;

							case "10704":
							case "10705":
								s.word = "该素材已被删除";
								break;

							case "64504":
								s.word = "保存图文消息发送错误，请稍后再试";
								break;

							case "64505":
								s.word = "发送预览失败，请稍后再试";
								break;

							case "64507":
								s.word = "内容不能包含链接，请调整";
								break;

							case "-99":
								s.word = "内容超出字数，请调整";
								break;

							case "62752":
								s.word = "可能含有具备安全风险的链接，请检查";
								break;

							case "10801":
								s.word = "标题不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。", s.antispam = !0;
								break;

							case "10802":
								s.word = "作者不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。", s.antispam = !0;
								break;

							case "10803":
								s.word = "敏感链接，请重新添加。", s.antispam = !0;
								break;

							case "10804":
								s.word = e ? "正文不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。" : "摘要不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。",
									s.antispam = !0;
								break;

							case "10806":
								s.word = "正文不能有违反公众平台协议、相关法律法规和政策的内容，请重新编辑。", s.antispam = !0;
								break;

							case "-8":
							case "-6":
								s.ret = "-6", s.word = "请输入验证码";
								break;

							case "15801":
							case "15802":
							case "15803":
							case "15804":
							case "15805":
							case "15806":
								break;

							default:
								s.word = "系统繁忙，请稍后重试";
						}
						15 == a && r.err(s.word), n && n(s);
					}
				});
			},
			getList: function(e, a, t, i, n) {
				var o = "";
				o = wx.url(n ? "/cgi-bin/appmsg?type=%s&action=list&begin=%s&count=%s&query=%s&f=json".sprintf(e, a, t, n) : "/cgi-bin/appmsg?type=%s&action=list&begin=%s&count=%s&f=json".sprintf(e, a, t)),
					s.get({
						mask: !1,
						url: o,
						error: function() {
							r.err("获取列表失败");
						}
					}, function(e) {
						e && e.base_resp && 0 == e.base_resp.ret ? i && i(e.app_msg_info) : r.err("获取列表失败");
					});
			},
			getSingleList: function(e, a, t, i) {
				s.get({
					mask: !1,
					url: wx.url("/cgi-bin/appmsg?type=%s&action=for_advert&begin=%s&count=%s&f=json".sprintf(e, a, t)),
					error: function() {
						r.err("获取列表失败");
					}
				}, function(e) {
					e && e.base_resp && 0 == e.base_resp.ret ? i && i(e.app_msg_info) : r.err("获取列表失败");
				});
			}
		},
		t = {
			save: function(e, a, t) {
				var i = wx.url("/cgi-bin/operate_vote");
				e.ajax = 1, s.post({
					url: i,
					data: e,
					error: function() {
						r.err("保存失败"), t && t();
					}
				}, function(e) {
					e && e.base_resp && 0 == e.base_resp.ret ? (r.suc("保存成功"), a && a(e)) : (r.err("保存失败"), t && t(e));
				});
			}
		};
	return {
		rename: function(e, a, t) {
			s.post({
				mask: !1,
				url: wx.url("/cgi-bin/modifyfile?oper=rename&t=ajax-response"),
				data: {
					fileid: e,
					fileName: a
				},
				error: function() {
					r.err("重命名失败");
				}
			}, function(e) {
				if (!e || !e.base_resp) return void r.err("重命名失败");
				var s = e.base_resp.ret;
				if ("0" == s) r.suc("重命名成功"), t && t(e);
				else switch (s) {
					case "-2":
						r.err("素材名不能包含空格");
						break;

					default:
						r.err("重命名失败");
				}
			});
		},
		del: function(e, a) {
			s.post({
				mask: !1,
				url: wx.url("/cgi-bin/modifyfile?oper=del&t=ajax-response"),
				data: {
					fileid: e
				},
				error: function() {
					r.err("删除失败");
				}
			}, function(e) {
				return e && e.base_resp ? void("0" == e.base_resp.ret ? (r.suc("删除成功"), a && a(e)) : r.err("删除失败")) : void r.err("删除失败");
			});
		},
		getList: function(e, a, t, i) {
			s.get({
				mask: !1,
				url: wx.url("/cgi-bin/filepage?type=%s&begin=%s&count=%s&f=json".sprintf(e, a, t)),
				error: function() {
					r.err("获取列表失败");
				}
			}, function(e) {
				e && e.base_resp && 0 == e.base_resp.ret ? i && i(e.page_info) : r.err("获取列表失败");
			});
		},
		appmsg: a,
		vote: t
	};
});
define("common/wx/richEditor/emotion.js", ["tpl/richEditor/emotion.html.js", "common/qq/Class.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = wx.T,
			s = {
				url: wx.resPath + "/mpres/htmledition/images/icon/emotion/",
				data: {
					"0": "微笑",
					"1": "撇嘴",
					"2": "色",
					"3": "发呆",
					"4": "得意",
					"5": "流泪",
					"6": "害羞",
					"7": "闭嘴",
					"8": "睡",
					"9": "大哭",
					"10": "尴尬",
					"11": "发怒",
					"12": "调皮",
					"13": "呲牙",
					"14": "惊讶",
					"15": "难过",
					"16": "酷",
					"17": "冷汗",
					"18": "抓狂",
					"19": "吐",
					"20": "偷笑",
					"21": "可爱",
					"22": "白眼",
					"23": "傲慢",
					"24": "饥饿",
					"25": "困",
					"26": "惊恐",
					"27": "流汗",
					"28": "憨笑",
					"29": "大兵",
					"30": "奋斗",
					"31": "咒骂",
					"32": "疑问",
					"33": "嘘",
					"34": "晕",
					"35": "折磨",
					"36": "衰",
					"37": "骷髅",
					"38": "敲打",
					"39": "再见",
					"40": "擦汗",
					"41": "抠鼻",
					"42": "鼓掌",
					"43": "糗大了",
					"44": "坏笑",
					"45": "左哼哼",
					"46": "右哼哼",
					"47": "哈欠",
					"48": "鄙视",
					"49": "委屈",
					"50": "快哭了",
					"51": "阴险",
					"52": "亲亲",
					"53": "吓",
					"54": "可怜",
					"55": "菜刀",
					"56": "西瓜",
					"57": "啤酒",
					"58": "篮球",
					"59": "乒乓",
					"60": "咖啡",
					"61": "饭",
					"62": "猪头",
					"63": "玫瑰",
					"64": "凋谢",
					"65": "示爱",
					"66": "爱心",
					"67": "心碎",
					"68": "蛋糕",
					"69": "闪电",
					"70": "炸弹",
					"71": "刀",
					"72": "足球",
					"73": "瓢虫",
					"74": "便便",
					"75": "月亮",
					"76": "太阳",
					"77": "礼物",
					"78": "拥抱",
					"79": "强",
					"80": "弱",
					"81": "握手",
					"82": "胜利",
					"83": "抱拳",
					"84": "勾引",
					"85": "拳头",
					"86": "差劲",
					"87": "爱你",
					"88": "NO",
					"89": "OK",
					"90": "爱情",
					"91": "飞吻",
					"92": "跳跳",
					"93": "发抖",
					"94": "怄火",
					"95": "转圈",
					"96": "磕头",
					"97": "回头",
					"98": "跳绳",
					"99": "挥手",
					"100": "激动",
					"101": "街舞",
					"102": "献吻",
					"103": "左太极",
					"104": "右太极"
				},
				ext: ".gif",
				replaceEmoji: function(e) {
					var t, n, r = s.url,
						i = s.ext,
						o = s.data;
					for (t in o) n = new RegExp("/" + o[t], "g"), e = e.replace(n, '<img src="{src}" alt="mo-{alt}"/>'.format({
						src: r + t + i,
						alt: o[t]
					}));
					return e;
				}
			},
			o = e("tpl/richEditor/emotion.html.js"),
			u = e("common/qq/Class.js"),
			a = 24,
			f = 24,
			l = 15,
			c = 7,
			h = u.declare({
				init: function(e) {
					this.selector$ = e;
					var t = [],
						n = s.url + "{k}" + s.ext,
						r = a,
						u = f,
						h = l,
						p = c;
					for (var d = 0; d < p; ++d)
						for (var v = 0; v < h; ++v) {
							var m = d * h + v;
							t.push({
								gifurl: n.format({
									k: m
								}),
								title: s.data[m + ""],
								bp: "background-position:" + -m * r + "px 0;"
							});
						}
					this.selector$.html(i(o, {
						edata: t
					})), this._previewArea$ = this.selector$.find(".js_emotionPreviewArea"), this._initEvent();
				},
				getEmotionText: function(e) {
					return e.replace(/<img.*?alt=["]{0,1}mo-([^"\s]*).*?>/ig, "/$1");
				},
				getEmotionHTML: function(e) {
					var t = e.title;
					return '<img src="{src}" alt="{alt}"/>'.format({
						src: e.gifurl,
						alt: t ? "mo-" + t : ""
					});
				},
				_getData: function(e) {
					return {
						title: e.data("title"),
						gifurl: e.data("gifurl")
					};
				},
				_initEvent: function() {
					var e = this;
					e.selector$.click(function(t) {
						var n = e._getData($(t.target));
						!n.gifurl || e.clickCB && e.clickCB(n);
					}).mouseover(function(t) {
						var n = e._getData($(t.target));
						!n.gifurl || e._previewArea$.html(e.getEmotionHTML({
							title: "",
							gifurl: n.gifurl
						})), e.mouseoverCB && e.mouseoverCB();
					}).mouseleave(function(t) {
						e._previewArea$.html(""), e.mouseleaveCB && e.mouseleaveCB();
					});
				},
				click: function(e) {
					this.clickCB = e;
				},
				mouseleave: function(e) {
					return this.mouseleaveCB = e, this;
				},
				mouseover: function(e) {
					return this.mouseoverCB = e, this;
				},
				show: function() {
					this.selector$.fadeIn();
				},
				hide: function() {
					this.selector$.fadeOut();
				}
			});
		h.emoji = s.replaceEmoji, n.exports = h;
	} catch (p) {
		wx.jslog({
			src: "common/wx/richEditor/emotion.js"
		}, p);
	}
});
define("common/wx/richEditor/wysiwyg.js", ["common/wx/richEditor/editorRange.js", "common/qq/Class.js"], function(e, t, n) {
	"use strict";
	var i = /msie/.test(navigator.userAgent.toLowerCase()),
		a = "Wysiwyg",
		r = e("common/wx/richEditor/editorRange.js"),
		s = e("common/qq/Class.js"),
		o = s.declare({
			init: function(e, t) {
				var n = e,
					i = $('<div class="edit_area"></div>');
				this._dom$ = n, this._val = "", this._lastRange = null, this._editArea$ = i, $.extend(this, t),
					this._initEditArea(), this._initEvent();
			},
			_initEvent: function() {
				var e = this,
					t = function() {
						e.__tid && clearTimeout(e.__tid), e.__tid = setTimeout(function() {
							e._filterNode();
						}, 10);
					};
				e._editArea$.bind({
					keydown: function(t) {
						e._keydown(t);
					},
					keyup: function(t) {
						e._keyup(t);
					},
					mouseup: function(t) {
						e._mouseup(t);
					},
					drop: t,
					paste: t
				});
			},
			_keydown: function(e) {
				var t = this,
					n = wx.isHotkey;
				if (n(e, "backspace")) {
					var i = r.getSelection();
					i.type && "control" === i.type.toLowerCase() && (e.preventDefault(), i.clear());
				}
				(n(e, "ctrlenter") || n(e, "altenter")) && (e.preventDefault(), t.insertHTML("<br/>")._saveRang()),
				t.keydown && t.keydown(e);
			},
			_keyup: function(e) {
				var t = this;
				t._saveRang(), t.keyup && t.keyup(e), t.save();
			},
			_mouseup: function(e) {
				var t = this;
				t._saveRang(), t.mouseup && t.mouseup(e);
			},
			focus: function() {
				this._editArea$.focus(), this._resotreRange();
			},
			_setCursorToEnd: function(e) {
				if (i && e) {
					var t = r.getSelection();
					t.extend && (t.extend(e, e.length), t.collapseToEnd && t.collapseToEnd());
				}
			},
			insertHTML: function(e) {
				var t = this;
				t.focus();
				var n = r.getRange();
				if (!n) return t;
				if (n.createContextualFragment) {
					e += '<img style="width:1px;height:1px;">';
					var a = n.createContextualFragment(e),
						s = a.lastChild;
					n.deleteContents(), n.insertNode(a), n.setEndAfter(s), n.setStartAfter(s);
					var o = r.getSelection();
					o.removeAllRanges(), o.addRange(n), document.execCommand("Delete", !1, null);
				} else i && />$/.test(e), n.pasteHTML && n.pasteHTML(e), n.collapse && n.collapse(!1), n.select();
				return t._saveRang().save(), t;
			},
			save: function(e) {
				var t = this,
					e = e || this.textFilter,
					n = t._editArea$.html();
				return t.val = "function" == typeof e ? e(n) : n, t.change && t.change(), t;
			},
			_filterNode: function(e) {
				for (var t, n = this, e = e || this.nodeFilter, i = n._editArea$.get(0), a = i.childNodes, r = a.length - 1; r >= 0; r--) {
					var s = a[r];
					switch (s.nodeType) {
						case 1:
							if ("BR" !== s.nodeName.toUpperCase()) {
								var o, d = !1;
								if ((o = e && e(s)) || (o = s.textContent || s.innerText || "", d = !0), o) {
									var c = d ? document.createTextNode(o) : o;
									!t && (t = c), i.replaceChild(c, s);
								} else i.removeChild(s);
							}
							break;

						case 3:
							break;

						default:
							i.removeChild(s);
					}
				}
				return n._setCursorToEnd(t), n._saveRang().save();
			},
			getHTML: function() {
				return this._editArea$.html();
			},
			getText: function() {
				return this.getHTML().text();
			},
			getContent: function() {
				return this.val;
			},
			setContent: function(e, t) {
				this.clear(), this._editArea$.html(e), this.val = t || e, this.change && this.change();
			},
			clear: function() {
				this.val = "", this._editArea$.html("");
			},
			_initEditArea: function() {
				var e = this;
				e._editArea$.attr("class", e._dom$.attr("class")).attr("contentEditable", !0).css({
					"overflow-y": "auto",
					"overflow-x": "hidden"
				}), e._dom$.after(e._editArea$).hide().data(a, e), e.init && e.init();
			},
			_saveRang: function() {
				return this._lastRange = r.getRange(), this;
			},
			_resotreRange: function() {
				var e = this._lastRange;
				if (e) {
					var t = r.getSelection();
					if (t.addRange) t.removeAllRanges(), t.addRange(e);
					else {
						var n = r.getRange();
						n.setEndPoint && (n.setEndPoint("EndToEnd", e), n.setEndPoint("StartToStart", e)), n.select();
					}
				}
				return this;
			}
		}),
		d = function(e, t) {
			return e.data(a) || new o(e, t);
		};
	n.exports = d;
});
define("tpl/richEditor/emotionEditor.html.js", [], function() {
	return '<div class="emotion_editor">\n    <div class="edit_area js_editorArea"></div>\n    <div class="editor_toolbar">\n        {if !hideEmotion}\n        <a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch">表情</a>\n        {/if}\n        {if !hideUpload}\n        <div class="upload_box">\n            <div class="upload_area">\n                <a id="emotionEditor_{gid}_" href="javascript:void(0);" class="js_upload upload_access">\n                    <i class="icon18_common upload_gray"></i>\n                    上传图片                </a>\n            </div>\n        </div>\n        {/if}\n        <p class="editor_tip opr_tips">，按下Shift+Enter键换行</p>\n        <p class="editor_tip js_editorTip"></p>\n        <div class="emotion_wrp js_emotionArea">\n			\n		</div>\n    </div>\n</div>\n\n';
});
define("tpl/tab.html.js", [], function() {
	return '<div class="msg_tab">\n	<ul class="tab_navs">\n	    {each tabs as tab}\n        <li class="tab_nav {tab.className}" data-type="{tab.type}" data-tab=".{tab.selector}" data-tooltip="{tab.text}">\n		    <a href="javascript:void(0);" onclick="return false;">&nbsp;<i class="icon_msg_sender"></i><span class="msg_tab_title">{tab.text}</span></a>\n	    </li>\n	    {/each}\n	</ul>\n	<div class="tab_panel">\n	    {each tabs as tab i}\n	    <div class="tab_content">\n	    	<div class="{tab.selector} inner {tab.innerClassName}">\n	    		<!--type 10图文 2图片  3语音 15视频 11商品消息-->\n	    		{if tab.type==10}\n			    <div class="tab_cont_cover jsMsgSendTab" data-index="{i}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt"  href="javascript:;" data-type="10" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n			        <div class="media_cover" >\n			            <span class="create_access">\n                            <a target="_blank" class="add_gray_wrp" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=1&isNew=1&lang=zh_CN&token={token}">\n			                    <i class="icon36_common add_gray"></i>\n			                    <strong>新建图文消息</strong>\n			                </a>\n                            <!--\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=0&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create"></i><strong>单图文消息</strong></a>\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=1&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create multi"></i><strong>多图文消息</strong></a>\n                            -->\n			            </span>\n			        </div>\n			    </div>	    		\n	    		{else if tab.type==2}\n                <div class="tab_cont_cover jsMsgSendTab" data-index="{i}" data-type="{tab.type}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt"   href="javascript:;" data-type="{tab.type}"   data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n                    <div class="media_cover">\n			            <span class="create_access" >\n			                <a class="add_gray_wrp" id="msgSendImgUploadBt" data-type="2" href="javascript:;">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>上传图片</strong>\n                            </a>\n			            </span>\n                    </div>\n                </div>\n	    		{else if tab.type==3}\n                <div class="tab_cont_cover jsMsgSendTab" data-index="{i}" data-type="{tab.type}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;"  data-type="{tab.type}" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n                    <div class="media_cover">\n			            <span class="create_access" >\n			                <a class="add_gray_wrp " id="msgSendAudioUploadBt" href="javascript:;">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>上传语音</strong>\n                            </a>\n			            </span>\n                    </div>\n                </div>\n	    		{else if tab.type==15}\n                <div class="tab_cont_cover jsMsgSendTab" data-index="{i}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;"  data-type="15" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a target="_blank" class="add_gray_wrp" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&type=15&lang=zh_CN&token={token}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>新建视频</strong>\n                            </a>\n			            </span>\n                    </div>\n                </div>\n	    		{else if tab.type==11}\n			    <div class="tab_cont_cover jsMsgSendTab" data-index="{i}">\n                    <div class="media_cover">\n			            <span class="create_access">\n			                <a class="add_gray_wrp jsMsgSenderPopBt"  href="javascript:;" data-type="11" data-index="{i}">\n                                <i class="icon36_common add_gray"></i>\n                                <strong>从素材库中选择</strong>\n                            </a>\n			            </span>\n                    </div>\n			        <div class="appmsg_cover" >\n			            <span class="create_access">\n			                <a class="add_gray_wrp" href="javascript:;">\n			                    <i class="icon36_common add_gray"></i>\n			                    <strong>新建商品消息</strong>\n			                </a>\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&isMul=0&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create"></i><strong>单商品消息</strong></a>\n			                <a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&isMul=1&isNew=1&lang=zh_CN&token={token}"><i class="icon_shopmsg_create multi"></i><strong>多商品消息</strong></a>\n			            </span>\n			        </div>\n			    </div>		    		\n	    		{/if}\n	    	</div>\n	    </div>\n	    {/each}\n	</div>\n</div>\n';
});
define("tpl/step.html.js", [], function(e, t, n) {
	return '<ul class="processor_bar grid_line">\n    {each stepArr as item index}\n    <li class="{if (index+1==length)}no_extra{/if} step grid_item size1of{length} {item.cls}">\n        <h4>{item.name}</h4>\n    </li>\n    {/each}\n</ul>\n';
});
define("tpl/popup.html.js", [], function() {
	return '<div class="dialog_wrp {className}" style="{if width}width:{width}px;{/if}{if height}height:{height}px;{/if}">\n	<div class="dialog">\n		<div class="dialog_hd">\n			<h3>{title}</h3>\n			<!--#0001#-->\n			<a href="javascript:;" onclick="return false" class="icon16_opr closed pop_closed">关闭</a>\n			<!--%0001%-->\n		</div>\n		<div class="dialog_bd">{=content}</div>\n		{if buttons && buttons.length}\n		<div class="dialog_ft">\n			{each buttons as bt index}\n            <span class="btn {bt.type} btn_input js_btn_p"><button type="button" class="js_btn" data-index="{index}">{bt.text}</button></span>\n	        {/each}\n		</div>\n		{/if}\n	</div>\n</div>{if mask}<div class="mask"><iframe frameborder="0" style="filter:progid:DXImageTransform.Microsoft.Alpha(opacity:0);position:absolute;top:0px;left:0px;width:100%;height:100%;" src="about:blank"></iframe></div>{/if}\n';
});
define("common/wx/widgetBridge.js", [], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict", $.widgetBridge || ($.widgetBridge = function(e, t) {
			var n = e,
				r = n.split("."),
				e = r.length > 1 ? r[1] : r[0];
			$.fn[e] = function(r) {
				var i = typeof r == "string",
					s = [].slice.call(arguments, 1),
					o = this;
				r = r || {};
				if (i) {
					var u;
					return r.indexOf("_") !== 0 && this.each(function() {
						var t = $.data(this, n);
						if (!t) return $.error("cannot call methods on " + e + " prior to initialization; " + "attempted to call method '" + r + "'");
						if (r === "instance") return u = t, !1;
						if (r === "option") return u = t.options, !1;
						u || (u = (t[r] || jQuery.noop).apply(t, s)), r === "destroy" && (t.elements = null);
					}), u;
				}
				var a = this;
				return this.each(function() {
					var e = this,
						i = $.data(this, n);
					if (!i) {
						i = $.extend(!0, {}, t), i.destroy || (i.destroy = function() {
							$.removeData(e, n);
						}), i.options = $.extend(!0, i.options || {}, r), i.element = $(this), i.elements = a, i._create && (o = i._create.call(i, r));
						var s = o && o.length && o.get(0) || this;
						$.data(s, n, i);
					}
				}), o;
			};
		});
	} catch (i) {
		wx.jslog({
			src: "common/wx/widgetBridge.js"
		}, i);
	}
});
define("tpl/verifycode.html.js", [], function() {
	return '<div class="verifycode">\n	<span class="frm_input_box"><input id="imgcode" name="imgcode" type="text" value="" class="frm_input"></span>\n    <div class="verifyimg_wrp">\n        <img src="">\n        <a href="javascript:;" class="changeVerifyCode">换一张</a>\n    </div>\n</div>\n';
});
define("cardticket/add/member_info_flag.js", [], function() {
	"use strict";

	function n(n, f) {
		for (var i = 0; i < n.length; i++)
			if (n[i] === f) return i;
		return -1;
	}
	var f = [1, 4096, 2, 4, 8, 0, 32, 64, 128, 256, 512, 1024, 2048];
	return {
		sys_info: ["手机号", "姓名", "性别", "所在地区", "生日", "身份证号", "邮箱", "详细地址", "学历", "职业", "行业", "收入", "爱好"],
		info_flag: f,
		flag2info: function(n) {
			for (var f = [], i = 0; i < this.info_flag.length; i++) {
				var r = this.info_flag[i];
				r & n && f.push(this.sys_info[i]);
			}
			return f;
		},
		info2flag: function(f) {
			for (var i = 0, r = 0; r < f.length; r++) {
				var t = n(this.sys_info, f[r]);
				t >= 0 && (i |= this.info_flag[t]);
			}
			return i;
		}
	};
});
define("common/qq/events.js", [], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";

		function i(e) {
			e === !0 ? this.data = window.wx.events || {} : this.data = {};
		}
		i.prototype = {
			on: function(e, t) {
				return this.data[e] = this.data[e] || [], this.data[e].push(t), this;
			},
			off: function(e, t) {
				this.data[e] && this.data[e].length > 0 && (t && typeof t == "function" ? $.each(this.data[e], function(n, r) {
					r === t && this.data[e].splice(n, 1);
				}) : this.data[e] = []);
			},
			trigger: function(e) {
				var t = arguments;
				this.data[e] && this.data[e].length > 0 && $.each(this.data[e], function(e, n) {
					var r = n.apply(this, Array.prototype.slice.call(t, 1));
					if (r === !1) return !1;
				});
			}
		}, n.exports = function(e) {
			return new i(e);
		};
	} catch (s) {
		wx.jslog({
			src: "common/qq/events.js"
		}, s);
	}
});
define("common/lib/MockJax.js", [], function(e, t, n) {
	try {
		var r = +(new Date);
		(function(e) {
			function t(t) {
				window.DOMParser == undefined && window.ActiveXObject && (DOMParser = function() {}, DOMParser.prototype.parseFromString = function(e) {
					var t = new ActiveXObject("Microsoft.XMLDOM");
					return t.async = "false", t.loadXML(e), t;
				});
				try {
					var n = (new DOMParser).parseFromString(t, "text/xml");
					if (!e.isXMLDoc(n)) throw "Unable to parse XML";
					var r = e("parsererror", n);
					if (r.length == 1) throw "Error: " + e(n).text();
					return n;
				} catch (i) {
					var s = i.name == undefined ? i : i.name + ": " + i.message;
					return e(document).trigger("xmlParseError", [s]), undefined;
				}
			}

			function n(t, n, r) {
				(t.context ? e(t.context) : e.event).trigger(n, r);
			}

			function r(t, n) {
				var i = !0;
				return typeof n == "string" ? e.isFunction(t.test) ? t.test(n) : t == n : (e.each(t, function(s) {
					if (n[s] === undefined) return i = !1, i;
					typeof n[s] == "object" ? i = i && r(t[s], n[s]) : e.isFunction(t[s].test) ? i = i && t[s].test(n[s]) : i = i && t[s] == n[s];
				}), i);
			}

			function i(t, n) {
				if (e.isFunction(t)) return t(n);
				if (e.isFunction(t.url.test)) {
					if (!t.url.test(n.url)) return null;
				} else {
					var i = t.url.indexOf("*");
					if (t.url !== n.url && i === -1 || !(new RegExp(t.url.replace(/[-[\]{}()+?.,\\^$|#\s]/g, "\\$&").replace(/\*/g, ".+"))).test(n.url)) return null;
				}
				return t.data && n.data && !r(t.data, n.data) ? null : t && t.type && t.type.toLowerCase() != n.type.toLowerCase() ? null : t;
			}

			function s(n, r, i) {
				var s = function(s) {
					return function() {
						return function() {
							var s;
							this.status = n.status, this.statusText = n.statusText, this.readyState = 4, e.isFunction(n.response) && n.response(i), r.dataType == "json" && typeof n.responseText == "object" ? this.responseText = JSON.stringify(n.responseText) : r.dataType == "xml" ? typeof n.responseXML == "string" ? (this.responseXML = t(n.responseXML), this.responseText = n.responseXML) : this.responseXML = n.responseXML : this.responseText = n.responseText;
							if (typeof n.status == "number" || typeof n.status == "string") this.status = n.status;
							typeof n.statusText == "string" && (this.statusText = n.statusText), s = this.onreadystatechange || this.onload, e.isFunction(s) ? (n.isTimeout && (this.status = -1), s.call(this, n.isTimeout ? "timeout" : undefined)) : n.isTimeout && (this.status = -1);
						}.apply(s);
					};
				}(this);
				n.proxy ? v({
					global: !1,
					url: n.proxy,
					type: n.proxyType,
					data: n.data,
					dataType: r.dataType === "script" ? "text/plain" : r.dataType,
					complete: function(e) {
						n.responseXML = e.responseXML, n.responseText = e.responseText, n.status = e.status, n.statusText = e.statusText, this.responseTimer = setTimeout(s, n.responseTime || 0);
					}
				}) : r.async === !1 ? s() : this.responseTimer = setTimeout(s, n.responseTime || 50);
			}

			function o(t, n, r, i) {
				return t = e.extend(!0, {}, e.mockjaxSettings, t), typeof t.headers == "undefined" && (t.headers = {}), t.contentType && (t.headers["content-type"] = t.contentType), {
					status: t.status,
					statusText: t.statusText,
					readyState: 1,
					open: function() {},
					send: function() {
						i.fired = !0, s.call(this, t, n, r);
					},
					abort: function() {
						clearTimeout(this.responseTimer);
					},
					setRequestHeader: function(e, n) {
						t.headers[e] = n;
					},
					getResponseHeader: function(e) {
						if (t.headers && t.headers[e]) return t.headers[e];
						if (e.toLowerCase() == "last-modified") return t.lastModified || (new Date).toString();
						if (e.toLowerCase() == "etag") return t.etag || "";
						if (e.toLowerCase() == "content-type") return t.contentType || "text/plain";
					},
					getAllResponseHeaders: function() {
						var n = "";
						return e.each(t.headers, function(e, t) {
							n += e + ": " + t + "\n";
						}), n;
					}
				};
			}

			function u(e, t, n) {
				a(e), e.dataType = "json";
				if (e.data && y.test(e.data) || y.test(e.url)) {
					l(e, t, n);
					var r = /^(\w+:)?\/\/([^\/?#]+)/,
						i = r.exec(e.url),
						s = i && (i[1] && i[1] !== location.protocol || i[2] !== location.host);
					e.dataType = "script";
					if (e.type.toUpperCase() === "GET" && s) {
						var o = f(e, t, n);
						return o ? o : !0;
					}
				}
				return null;
			}

			function a(e) {
				if (e.type.toUpperCase() === "GET") y.test(e.url) || (e.url += (/\?/.test(e.url) ? "&" : "?") + (e.jsonp || "callback") + "=?");
				else if (!e.data || !y.test(e.data)) e.data = (e.data ? e.data + "&" : "") + (e.jsonp || "callback") + "=?";
			}

			function f(t, n, r) {
				var i = r && r.context || t,
					s = null;
				return n.response && e.isFunction(n.response) ? n.response(r) : typeof n.responseText == "object" ? e.globalEval("(" + JSON.stringify(n.responseText) + ")") : e.globalEval("(" + n.responseText + ")"), c(t, i, n), h(t, i, n), e.Deferred && (s = new e.Deferred, typeof n.responseText == "object" ? s.resolveWith(i, [n.responseText]) : s.resolveWith(i, [e.parseJSON(n.responseText)])), s;
			}

			function l(e, t, n) {
				var r = n && n.context || e,
					i = e.jsonpCallback || "jsonp" + b++;
				e.data && (e.data = (e.data + "").replace(y, "=" + i + "$1")), e.url = e.url.replace(y, "=" + i + "$1"), window[i] = window[i] || function(n) {
					data = n, c(e, r, t), h(e, r, t), window[i] = undefined;
					try {
						delete window[i];
					} catch (s) {}
					head && head.removeChild(script);
				};
			}

			function c(e, t, r) {
				e.success && e.success.call(t, r.responseText || "", status, {}), e.global && n(e, "ajaxSuccess", [{},
					e
				]);
			}

			function h(t, r) {
				t.complete && t.complete.call(r, {}, status), t.global && n("ajaxComplete", [{},
					t
				]), t.global && !--e.active && e.event.trigger("ajaxStop");
			}

			function p(t, n) {
				var r, s, a;
				typeof t == "object" ? (n = t, t = undefined) : n.url = t, s = e.extend(!0, {}, e.ajaxSettings, n);
				for (var f = 0; f < m.length; f++) {
					if (!m[f]) continue;
					a = i(m[f], s);
					if (!a) continue;
					g.push(s), e.mockjaxSettings.log(a, s);
					if (s.dataType === "jsonp")
						if (r = u(s, a, n)) return r;
					return a.cache = s.cache, a.timeout = s.timeout, a.global = s.global, d(a, n),
						function(t, n, i, s) {
							r = v.call(e, e.extend(!0, {}, i, {
								xhr: function() {
									return o(t, n, i, s);
								}
							}));
						}(a, s, n, m[f]), r;
				}
				return v.apply(e, [n]);
			}

			function d(e, t) {
				if (!(e.url instanceof RegExp)) return;
				if (!e.hasOwnProperty("urlParams")) return;
				var n = e.url.exec(t.url);
				if (n.length === 1) return;
				n.shift();
				var r = 0,
					i = n.length,
					s = e.urlParams.length,
					o = Math.min(i, s),
					u = {};
				for (r; r < o; r++) {
					var a = e.urlParams[r];
					u[a] = n[r];
				}
				t.urlParams = u;
			}
			var v = e.ajax,
				m = [],
				g = [],
				y = /=\?(&|$)/,
				b = (new Date).getTime();
			e.extend({
				ajax: p
			}), e.mockjaxSettings = {
				log: function(t, n) {
					if (t.logging === !1 || typeof t.logging == "undefined" && e.mockjaxSettings.logging === !1) return;
					if (window.console && console.log) {
						var r = "MOCK " + n.type.toUpperCase() + ": " + n.url,
							i = e.extend({}, n);
						if (typeof console.log == "function") console.log(r, i);
						else try {
							console.log(r + " " + JSON.stringify(i));
						} catch (s) {
							console.log(r);
						}
					}
				},
				logging: !0,
				status: 200,
				statusText: "OK",
				responseTime: 500,
				isTimeout: !1,
				contentType: "text/plain",
				response: "",
				responseText: "",
				responseXML: "",
				proxy: "",
				proxyType: "GET",
				lastModified: null,
				etag: "",
				headers: {
					etag: "IJF@H#@923uf8023hFO@I#H#",
					"content-type": "text/plain"
				}
			}, e.mockjax = function(e) {
				var t = m.length;
				return m[t] = e, t;
			}, e.mockjaxClear = function(e) {
				arguments.length == 1 ? m[e] = null : m = [], g = [];
			}, e.mockjax.handler = function(e) {
				if (arguments.length == 1) return m[e];
			}, e.mockjax.mockedAjaxCalls = function() {
				return g;
			};
		})(jQuery);
	} catch (i) {
		wx.jslog({
			src: "common/lib/MockJax.js"
		}, i);
	}
});