define("ivr/keywords.js", ["common/qq/emoji.js", "common/wx/popup.js", "ivr/ivr_cgi.js", "common/wx/Tips.js", "common/wx/tooltip.js", "common/wx/richEditor/emotionEditor.js", "media/media_dialog.js", "common/wx/dialog.js", "common/wx/media/audio.js", "common/wx/media/img.js", "common/wx/media/video.js", "common/wx/media/simpleAppmsg.js", "common/wx/media/cardmsg.js", "tpl/media/cardmsg.html.js", "common/wx/Cgi.js", "cardticket/parse_data.js", "biz_common/utils/norefererimg.js", "common/wx/media/appmsg.js"], function(e) {
	"use strict";

	function t() {
		console.log("in initPage"), x.is.autoReply ? ($("#div_start").show(), $("#div_stop").hide(),
				$("#div_replyContent").show()) : ($("#div_start").hide(), $("#div_stop").show(), $("#div_replyContent").hide()),
			x.is.isOpen || ($("#div_replyContent").hide(), $("#div_alertTips").show(), $("#btn_start").removeClass("btn_primary").addClass("btn_disabled"),
				$("#btn_stop").removeClass("btn_warn").addClass("btn_disabled"));
	}

	function i(e) {
		var i, n = {
				type: "POST",
				url: "/misc/skeyform?form=advancedswitchform&lang=zh_CN",
				dataType: "json"
			},
			o = e ? 1 : 0,
			r = 4,
			a = ["关闭自动回复之后，将立即对所有用户生效。确认关闭？", "开启自动回复之后，将立即对所有用户生效。确认开启？"];
		c.show({
			type: "warn",
			msg: "操作确认|" + a[o ? 1 : 0],
			buttons: [{
				text: "确定",
				click: function() {
					i = $.extend(!0, {}, n, {
						data: {
							flag: o,
							type: r,
							token: wx.data.t
						},
						success: function(e) {
							0 == e.base_resp.ret ? (s.suc("操作成功"), replyData.is.autoReply = o, t()) : s.err("系统发生错误，请稍后重试");
						}
					}), $.ajax(i), this.remove();
				}
			}, {
				text: "取消",
				type: "normal",
				click: function() {
					this.remove();
				}
			}]
		});
	}

	function n() {
		$("#div_stop .btn_primary").click(function() {
			i(!0);
		}), $("#div_start .btn_warn").click(function() {
			i(!1);
		}), $(".detail_desc").click(function() {
			$("#detail_pop").popup({
				buttons: [{
					text: "我知道了",
					click: function() {
						this.hide();
					},
					type: "primary"
				}],
				title: "提示"
			});
		});
	}

	function o() {
		t(), n();
	}
	e("common/qq/emoji.js"), e("common/wx/popup.js");
	var r = e("ivr/ivr_cgi.js"),
		s = e("common/wx/Tips.js"),
		a = e("common/wx/tooltip.js"),
		l = e("common/wx/richEditor/emotionEditor.js"),
		d = e("media/media_dialog.js"),
		c = e("common/wx/dialog.js"),
		_ = e("common/wx/media/audio.js"),
		p = e("common/wx/media/img.js"),
		m = e("common/wx/media/video.js"),
		u = e("common/wx/media/simpleAppmsg.js"),
		f = (e("common/wx/media/cardmsg.js"),
			e("tpl/media/cardmsg.html.js")),
		h = e("common/wx/Cgi.js"),
		y = e("cardticket/parse_data.js"),
		v = e("biz_common/utils/norefererimg.js"),
		w = e("common/wx/media/appmsg.js"),
		g = wx.cgiData,
		x = replyData || {},
		J = template.render,
		k = {
			10: 5,
			11: 6,
			15: 7,
			16: 8,
			21: 9
		};
	o();
	var j = function(e) {
			return e = e.replace(/\n|<br[^>]*>/g, " "), e.trim();
		},
		C = function(e) {
			var t, i, n = e.wordlimit || 30;
			return t = $('<div class="emotion_editor_wrp" id="Js_textEditor"></div>').popup({
				title: e.title || "",
				width: 960,
				className: "keywords_edit",
				onShow: function() {
					$(this.$dialogWrp.get(0)).css({
						"margin-top": -264
					});
				},
				onOK: function() {
					var t = i.getContent().length;
					return e.autoValid !== !1 && 1 > t || t > n ? (s.err("文字必须为1到%s个字".sprintf(n)), !0) : (e.onOK && e.onOK.call(i),
						void this.hide());
				},
				onCancel: function() {
					this.hide();
				},
				onHide: function() {
					$("#Js_textEditor").off(), this.remove();
				}
			}), i = new l($("#Js_textEditor"), {
				wordlimit: n,
				linebreak: !0
			}), i.setContent((e.text || "").html()), e.onEnter && $("#Js_textEditor").on("keyup", function(t) {
				13 == t.keyCode && e.onEnter.call(i);
			}), t;
		},
		b = {
			1: function(e, t) {
				var i = t.content.replace(/\n/g, "<br/>");
				return e.html(i.emoji());
			},
			2: function(e, t) {
				return $.extend(t, {
					container: e,
					source: "file",
					fakeid: wx.data.uin
				}), new p(t);
			},
			3: function(e, t) {
				return $.extend(t, {
					selector: e,
					source: "file"
				}), new _(t);
			},
			4: function(e, t) {
				return $.extend(t, {
					selector: e,
					id: t.file_id,
					source: "file",
					play: function(t) {
						var i = 0;
						t.on("fullscreenchange", function() {
							i ? $(e).closest("li").css("z-index", 0) : $(e).closest("li").css("z-index", 1), i = ~i;
						});
					}
				}), new m(t);
			},
			5: function(e, t, i) {
				return $.extend(t, {
					container: e,
					source: "file",
					type: 10
				}), 10 == i ? new w(t) : new u(t);
			},
			6: function(e, t, i) {
				return $.extend(t, {
					container: e,
					source: "file",
					type: 11
				}), 11 == i ? new w(t) : new u(t);
			},
			7: function(e, t) {
				return $.extend(t, {
					selector: e,
					app_id: t.app_id || t.id,
					id: t.id + t.index,
					tpl: "videomsg",
					play: function(t) {
						var i = 0;
						t.on("fullscreenchange", function() {
							i ? $(e).closest("li").css("z-index", 0) : $(e).closest("li").css("z-index", 1), i = ~i;
						});
					}
				}), new m(t);
			},
			8: function(e, t) {
				var i = t.content,
					n = L[i];
				if (n) {
					$(e).html(template.compile(f)(n));
					var o = $(e).find(".js_logourl");
					o.length && v({
						img: o[0]
					});
				} else h.get("/merchant/electroniccardmgr?action=get&card_id=" + t.content, function(t) {
					if (0 == t.base_resp.ret) {
						var i = $.parseJSON(t.card_detail);
						if (i = y.parse_cardticket(i), !i) return;
						$(e).html(template.compile(f)(i));
						var n = $(e).find(".js_logourl");
						n.length && v({
							img: n[0]
						});
					}
				});
			}
		};
	b[9] = b[7];
	var q = $("#Js_ruleList"),
		E = {},
		L = {},
		O = [];
	! function() {
		if (template.helper("render_media", function(e) {
			return E[e.rule_id] = e.reply_list, "";
		}), g.list.length) {
			if (g.list.each(function(e, t, i) {
				e.rule_index += i.length - t + ":";
				for (var n = 0; n < e.reply_list.length; n++) {
					var o = e.reply_list[n];
					8 == o.reply_type && O.push(o.content);
				}
			}), q.html(J("t_ivrrole", {
				list: g.list,
				n: g.list.length
			})), g.list = null, O.length) {
				for (var e = {
					cardcnt: O.length
				}, t = 0; t < O.length; t++) e["cardid" + t] = O[t];
				h.get({
					url: wx.url("/merchant/electroniccardmgr?action=batchgetbyid"),
					data: e
				}, function(e) {
					0 == e.base_resp.ret && (L = y.parse_cardlist(e.list).card_cache || {});
				});
			}
		} else q.html('<p class="empty_tips">暂无创建规则</p>');
		q.on("click", "label", function() {
			$(this).find(":checked").length ? $(this).addClass("selected") : $(this).removeClass("selected");
		});
	}(), q.on("selectstart", ".Js_detail_switch", function() {
		return !1;
	}), q.on("click", ".Js_detail_switch", function() {
		var e = $(this),
			t = e.data("id"),
			i = e.data("reply"),
			n = $("#Js_ruleItem_" + t);
		if (n.toggleClass("open").hasClass("open") ? e.removeClass("dropdown_closed").addClass("dropdown_opened") : e.removeClass("dropdown_opened").addClass("dropdown_closed"),
			"loaded" != i) {
			if ("empty" == i) {
				var o = $("#Js_replyList_" + t);
				o.find(".Js_media_content").each(function() {
					var e = $(this),
						i = e.data("index"),
						n = e.data("type"),
						o = E[t][i];
					o.index = i, b[n] && b[n](e, o);
				}), e.data("reply", "loaded");
			}
			$(".media_type_list").find("li").each(function() {
				new a({
					dom: this,
					position: {
						x: -2,
						y: 1
					}
				});
			});
		}
	}), q.on("click", ".Js_keyword_edit", function() {
		var e = $(this).parents("li").eq(0),
			t = e.find(".Js_keyword_val");
		C({
			title: "修改关键字",
			onOK: function() {
				t.html(this.getContent().html().emoji()), t.attr("data-content", this.getContent());
			},
			text: t.attr("data-content")
		});
	}), q.on("click", ".Js_keyword_mode", function() {
		{
			var e, t = $(this),
				i = "";
			t.parents("li").eq(0);
		}
		e = +t.data("mode"), 1 == e ? (i = "未全匹配", e = 0) : 0 == e && (i = "已全匹配", e = 1), t.text(i), t.data("mode", e);
	}), q.on("click", ".Js_keyword_del", function() {
		$(this).parents("li").eq(0).remove();
	}), q.on("click", ".Js_keyword_add", function() {
		function e() {
			var e, t = this,
				i = j(t.getContent());
			"" != i && i.length > 30 ? s.err("关键字长度不能大于30") : "" != i ? (e = J("t_keyword_add_temp_item", {
				content: i.html()
			}), $("#Js_editorKeywordList").append(e)) : s.err("关键字不能为空");
		}
		var t = $(this).data("id"),
			i = $("#Js_keywordList_" + t),
			n = i.children("li").last().data("index");
		n = +n + 1;
		var o = C({
				title: "添加关键字",
				onOK: function() {
					var o = this,
						r = "";
					"" != j(o.getContent()) && e.call(this), $("#Js_editorKeywordList").children("li").each(function() {
						var e = $(this).attr("data-content").html();
						r += J("t_keyword_item", {
							id: t,
							index: n,
							content: e
						});
					}), i.append(r);
				},
				onEnter: function() {
					e.call(this), this.setContent("");
				},
				autoValid: !1
			}),
			r = $("#Js_textEditor");
		r.append('<div class="tool_area"><p class="tips">输入回车可添加多个关键字，每个关键字少于30个字符</p></div>'),
			r.append('<ul class="overview_keywords_list" id="Js_editorKeywordList"></ul>'), r.on("click", ".Js_temp_item_close", function() {
				$(this).parents("li").eq(0).remove();
			}), o.popup("resetPosition");
	}), q.on("click", ".Js_reply_edit", function() {
		var e = $(this).parents("li").eq(0),
			t = e.find(".Js_media_content");
		C({
			title: "修改回复文字",
			text: e.attr("data-content"),
			wordlimit: 300,
			onOK: function() {
				e.attr("data-content", this.getContent()), b[1](t, {
					content: this.getContent().html()
				});
			}
		});
	}), q.on("click", ".Js_reply_del", function() {
		var e = $(this).parents("li").eq(0),
			t = e.data("id"),
			i = e.data("type");
		if (7 == i && (i = 4), 8 == i) {
			var n = $("#Js_ruleItem_" + t).find(".Js_reply_cnt[data-type=" + i + "]"),
				o = $("#Js_ruleItem_" + t).find(".Js_reply_cnt2[data-type=" + i + "]");
			n.text(+n.text() - 1), o.text(+o.text() - 1);
		} else {
			var n = $("#Js_ruleItem_" + t).find(".Js_reply_cnt").eq(+i - 1),
				o = $("#Js_ruleItem_" + t).find(".Js_reply_cnt2").eq(+i - 1);
			n.text(+n.text() - 1), o.text(+o.text() - 1);
		}
		e.remove();
	}), q.on("click", ".Js_reply_add", function() {
		function e(e, t) {
			t = t.data || t, t.id = n, t.index = +o.children().last().data("index") + 1, t.reply_type = k[e] || e,
				8 == t.reply_type && (t.content = t.cardid), $(J("t_reply_item", t), o).appendTo(o).find(".Js_media_content").each(function() {
					var i = $(this),
						n = (i.data("id"), i.data("type"));
					b[n] && b[n](i, t, e);
				});
			var i = +(7 == t.reply_type ? 4 : t.reply_type);
			if (8 == t.reply_type) {
				var r = $("#Js_ruleItem_" + n).find(".Js_reply_cnt[data-type=" + t.reply_type + "]"),
					s = $("#Js_ruleItem_" + n).find(".Js_reply_cnt2[data-type=" + t.reply_type + "]");
				r.text(+r.text() + 1), s.text(+s.text() + 1);
			} else {
				var r = $("#Js_ruleItem_" + n).find(".Js_reply_cnt").eq(i - 1),
					s = $("#Js_ruleItem_" + n).find(".Js_reply_cnt2").eq(i - 1);
				r.text(+r.text() + 1), s.text(+s.text() + 1);
			}
		}
		var t = $(this),
			i = t.data("type"),
			n = t.data("id"),
			o = $("#Js_replyList_" + n);
		if (o.children().length >= 5) return void s.err("最多5个回复");
		switch (+i) {
			case 1:
				C({
					title: "添加回复文字",
					wordlimit: 300,
					onOK: function() {
						var t = this,
							n = {
								content: t.getContent().html()
							};
						e(i, n);
					}
				});
				break;

			case 2:
			case 3:
				d.getFile({
					type: i,
					onSelect: e
				});
				break;

			case 4:
				break;

			case 5:
				d.getAppmsg({
					type: 10,
					onSelect: e
				});
				break;

			case 6:
				d.getAppmsg({
					type: 11,
					onSelect: e
				});
				break;

			case 7:
				d.getAppmsg({
					type: 15,
					onSelect: e
				});
				break;

			case 8:
				d.getCardList({
					type: 8,
					onSelect: e
				});
		}
	}), $("#Js_rule_add").on("click", function() {
		var e = $(this),
			t = {
				new_rule: 1,
				index: -1,
				rule_index: "新规则",
				rule_id: 0,
				rule_name: "",
				reply_all: 0,
				reply_cnt: 0,
				text_reply_cnt: 0,
				img_reply_cnt: 0,
				audio_reply_cnt: 0,
				video_reply_cnt: 0,
				appmsg_reply_cnt: 0,
				commodity_reply_cnt: 0,
				keyword_list: [],
				reply_list: []
			};
		"not" == e.data("status") ? (q.children().length ? $(J("t_ivrrole_item", t)).insertBefore(q.children().first()).find(".Js_detail_switch").click() : q.html(J("t_ivrrole_item", t)).find(".Js_detail_switch").click(),
				e.data("status", "added")) : (q.children().first().remove(), e.data("status", "not")),
			$(".media_type_list").find("li").each(function() {
				new a({
					dom: this,
					position: {
						x: -2,
						y: 1
					}
				});
			}), $("#Js_keywordList_0").show(), $("#Js_replyList_0").show();
	}), q.on("change", ".Js_reply_all", function() {
		var e = $(this).attr("id").replace("replyAll", "replyAllOverview");
		$(this).is(":checked") ? $("#" + e).removeClass("dn") : $("#" + e).addClass("dn");
	}), q.on("click", ".Js_rule_save", function() {
		var e = $(this);
		if (!e.hasClass("btn_disabled")) {
			var t = e.data("id"),
				i = $("#Js_ruleItem_" + t),
				n = i.find("#Js_replyList_" + t).children(),
				o = i.find("#Js_keywordList_" + t).children(),
				a = {
					replytype: "smartreply",
					ruleid: t,
					rulename: i.find("#Js_ruleName_" + t).val(),
					allreply: i.find("#Js_replyAll_" + t + ":checked").length,
					replycnt: n.length,
					keywordcnt: o.length
				};
			if (0 == a.rulename.length) return void s.err("规则名不能为空");
			if (a.rulename.length > 60) return void s.err("规则名最多60个字");
			if (a.keywordcnt > 10) return void s.err("最多10个关键词");
			if (0 == a.keywordcnt) return void s.err("请至少输入1个关键词");
			if (a.replycnt > 5) return void s.err("最多5个回复");
			if (0 == a.replycnt) return void s.err("请至少输入1个回复");
			var l = 0,
				d = [];
			o.each(function() {
				a["keyword" + l] = $(this).find(".Js_keyword_val").attr("data-content"), a["matchmode" + l] = $(this).find(".Js_keyword_mode").data("mode"),
					d.push({
						content: a["keyword" + l]
					}), l++;
			}), l = 0;
			var c = !1;
			if (n.each(function() {
				var e = $(this).data("type");
				4 == e && (c = !0), a["type" + l] = e, a["fileid" + l] = $(this).data(5 > e || 9 == e ? "fileid" : "appid"),
					a["content" + l] = $(this).attr("data-content"), l++;
			}), c) return void s.err("请更换新的视频消息");
			e.addClass("btn_disabled"), r.replySave(a, function() {
				if (0 == t) return void location.reload();
				$("#Js_keywordsOverview_" + t).html(J("t_keywords_overview", {
					list: d
				}));
				var e = i.find(".Js_detail_switch").click().offset().top;
				$(window).scrollTop(e - 20);
			}, function() {
				e.removeClass("btn_disabled");
			});
		}
	}), q.on("click", ".Js_rule_del", function() {
		if (confirm("确定要删除规则吗？")) {
			{
				var e = $(this),
					t = e.data("id");
				$("#Js_ruleItem_" + t);
			} - 1 == +t ? $("#Js_rule_add").click() : r.replyDel(t, "smartreply", function() {
				location.reload();
			});
		}
	}), q.on("mouseenter", ".Js_videomsg", function() {
		"" == $(this).find(".title").text().trim() && $(this).addClass("no_title");
	}), q.on("mouseleave", ".Js_videomsg", function() {
		$(this).removeClass("no_title");
	});
});