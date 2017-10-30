define("common/qq/jquery.plugin/tab.js", ["tpl/tab.html.js", "widget/msg_tab.css"], function(t) {
	"use strict"; {
		var a = {
				index: 0
			},
			n = t("tpl/tab.html.js");
		t("widget/msg_tab.css");
	}
	$.fn.tab = function(t) {
		if (t && t.tabs) {
			this.html(wx.T(n, {
				tabs: t.tabs,
				token: wx.data.t
			}));
			var e = this,
				s = e.find(".tab_navs"),
				i = s.find(".tab_nav"),
				d = i.length,
				c = null,
				r = null,
				l = function(a) {
					var n = a.data("tab"),
						s = a.data("type");
					n && (c != a && (c && c.removeClass("selected"), r && r.hide(), c = a, r = e.find(n).parent(), r.show(),
						c.addClass("selected")), !!t.select && t.select(a, r, n, s));
				},
				u = function(a) {
					var n = a.data("tab"),
						s = a.data("type");
					return t.click ? t.click(a, e.find(n), n, s) : !0;
				};
			return t = $.extend(!0, {}, a, t), i.each(function(a) {
				var n = t.index,
					s = $(this).addClass("width" + d),
					i = s.data("tab");
				a == n ? l(s) : e.find(i).parent().hide(), a == d - 1 && s.addClass("no_extra");
			}), s.on("click", ".tab_nav", function() {
				var t = u($(this));
				return 0 != t && l($(this));
			}), {
				getLen: function() {
					return d;
				},
				getTabs: function() {
					return i;
				},
				select: function(t) {
					return l(i.eq(t));
				}
			};
		}
	};
});
define("safe/tpl/safe_check.html.js", [], function() {
	return '<div>\n<!-- -->\n	<div class="safe_check js_mobilecheck">\n	    <div class="form">\n            <div class="inner">\n                <div class="frm_hd">\n                    <h3 class="frm_title">您的帐号开启了手机保护功能，需要输入手机验证码</h3>\n                </div>\n                <div class="frm_bd">\n                    <div class="frm_control_group">\n                        <label class="frm_label">手机号</label>\n                        <div class="frm_controls frm_vertical_pt">\n                            <p class="js_old"></p>\n                        </div>\n                    </div>\n                    <div class="frm_control_group">\n                        <label class="frm_label">验证码</label>\n                        <div class="frm_controls">\n                            <span class="frm_input_box vcode">\n                                <input type="text" placeholder="验证码" class="frm_input js_num">\n                            </span>\n                            <a href="javascript:void(0);" class="btn btn_default btn_vcode js_oldsend">获取验证码</a>\n                            <p class="frm_msg fail js_num_msg">● 验证码错误</p>\n                            <!--<div class="frm_tips">若xxxxxx，请点击</div>-->\n                        </div>\n                    </div>\n                </div>\n            </div>\n        </div>\n	</div>\n<!-- -->\n	<div class="safe_check js_wxcheck js_wxchecks">\n        <div class="qrcode_scan qrcode_scan_light">\n            <div class="qrcode_wrp">\n                <img class="qrcode js_qrcode" src="" alt="微信二维码" title="微信二维码" />\n            </div>\n            <div class="js_status"></div>\n        </div>\n        <p class="tc">可用管理员微信号扫码验证，同时支持非管理员微信号扫码申请，申请后需管理员验证。<a href="/cgi-bin/readtemplate?t=setting/safe-operation-guide-faq_tmpl&lang=zh_CN" target="_blank">操作指引</a></p>\n	</div>\n	<div class="safe_check js_wxcheck0 js_wxchecks">\n        <div class="qrcode_scan qrcode_scan_light">\n            <div class="qrcode_wrp">\n                <img class="qrcode js_qrcode" src="" alt="微信二维码" title="微信二维码" />\n            </div>\n            <div class="js_status"></div>\n        </div>\n        <p class="tc">管理员微信号与运营者微信号可直接扫码验证，非管理员微信号扫码后需管理员验证通过。<a href="/cgi-bin/readtemplate?t=setting/safe-operation-guide-faq_tmpl&lang=zh_CN" target="_blank">操作指引</a></p>\n	</div>\n	<div class="safe_check js_wxcheck1 js_wxchecks">\n        <p class="tc">您的帐号开启了微信保护功能，查看密钥操作需进行微信验证</p>\n        <div class="qrcode_scan qrcode_scan_light">\n            <div class="qrcode_wrp">\n                <img class="qrcode js_qrcode" src="" alt="微信二维码" title="微信二维码" />\n            </div>\n            <div class="js_status"></div>\n        </div>\n        <p class="tc">可用管理员微信号扫码验证，同时支持非管理员微信号扫码申请，申请后需管理员验证。<a href="/cgi-bin/readtemplate?t=setting/safe-operation-guide-faq_tmpl&lang=zh_CN" target="_blank">操作指引</a></p>\n	</div>\n<!--steps-->\n    <div class="safe_check js_bind">\n        <div class="processor_wrp js_process"></div>\n<!--step1-->\n        <div class="form form_width_auto js_step1">\n            <div class="inner">\n                <div class="frm_hd">\n                    <h3 class="frm_title js_title">在开启微信保护前，需要先验证您的身份，请选择验证方式：</h3>\n                </div>\n                <div class="frm_control_group">\n                    <div class="frm_controls">\n                        <label class="frm_radio_label selected">\n                            <i class="icon_radio"></i>\n                            <span class="lbl_content">通过注册时登记的手机号来验证</span>\n                            <input type="radio" value="1" class="frm_radio" checked="checked">\n                        </label>\n                        <p class="frm_radio_block_desc">通过发送验证短信到你的绑定手机<span class="js_step1_num"></span>进行验证</p>\n                    </div>\n                </div>\n                <div class="frm_control_group">\n                    <div class="frm_controls">\n                        <label class="frm_radio_label">\n                            <i class="icon_radio"></i>\n                            <span class="lbl_content">通过注册时登记的身份证号来验证</span>\n                            <input type="radio" value="0" class="frm_radio">\n                        </label>\n                        <p class="frm_radio_block_desc">通过填写帐号运营者姓名、身份证号进行验证</p>\n                    </div>\n                </div>\n                <div class="frm_control_group js_option" style="display:none;">\n                    <div class="frm_controls">\n                        <label class="frm_radio_label">\n                            <i class="icon_radio"></i>\n                            <span class="lbl_content">通过登录邮箱来验证</span>\n                            <input type="radio" value="2" class="frm_radio">\n                        </label>\n                        <p class="frm_radio_block_desc">将会发送安全验证码到你的登录邮箱<span class="js_step1_email"></span>，填写正确验证码可通过验证</p>\n                    </div>\n                </div>\n            </div>\n            <div class="tool_bar border tc">\n                <a href="javascript:;" class="btn btn_primary js_step1_next">下一步</a>\n                <a href="javascript:;" class="btn btn_default js_step1_cancel">取消</a>\n            </div>\n        </div>\n<!--step2 mobile-->\n        <div class="form disableform js_setp2_mobile" style="display:none;">\n            <div class="inner">\n                <div class="frm_hd">\n                    <h3 class="frm_title">请输入手机验证码进行验证</h3>\n                </div>\n                <div class="frm_control_group">\n                    <label class="frm_label">手机号</label>\n                    <div class="frm_controls frm_vertical_pt">\n                        <p>\n                            <span class="js_old"></span>\n                            <a class="ml1e js_mobile_forget" href=\'javascript:;\'>重置手机号</a>\n                        </p>\n                    </div>\n                </div>\n                <div class="frm_control_group">\n                    <label class="frm_label">验证码</label>\n                    <div class="frm_controls">\n                        <span class="frm_input_box vcode">\n                            <input type="text" placeholder="验证码" class="frm_input js_num">\n                        </span>\n                        <a href="javascript:" class="btn btn_default btn_vcode js_oldsend">获取验证码</a>\n                    </div>\n                </div>\n            </div>\n            <div class="tool_bar border tc">\n                <a href="javascript:;" class="btn btn_default js_step2_prev">上一步</a>\n                <a href="javascript:;" class="btn btn_primary js_step2_mobilecheck">下一步</a>\n            </div>\n        </div>\n<!--step2 name-->\n        <div class="form form_owner_info js_step2_name" style="display:none;">\n            <div class="inner">\n                <div class="frm_hd">\n                    <h3 class="frm_title">请正确填写以下信息，以验证你的身份</h3>\n                </div>\n                <div class="frm_control_group">\n                    <label class="frm_label">运营者身份证姓名</label>\n                    <div class="frm_controls">\n                        <span class="frm_input_box">\n                            <input type="text" class="frm_input js_cardname">\n                        </span>\n                        <p class="frm_tips">姓名需与身份证上姓名一致</p>\n                    </div>\n                </div>\n                <div class="frm_control_group">\n                    <label class="frm_label">运营者身份证号码</label>\n                    <div class="frm_controls">\n                        <span class="frm_input_box">\n                            <input type="text" class="frm_input js_cardid">\n                        </span>\n                        <p class="frm_tips">请正确填写注册时所登记的身份证号码</p>\n                    </div>\n                </div>\n            </div>\n            <div class="tool_bar border tc">\n                <a href="javascript:;" class="btn btn_default js_step2_prev">上一步</a>\n                <a href="javascript:;" class="btn btn_primary js_step2_namecheck">下一步</a>\n            </div>\n        </div>\n<!--step2 email-->\n        <div class="page_msg large default simple js_step2_mail" style="display:none;">\n            <div class="inner group">\n                    <span class="msg_icon_wrp">\n                        <i class="icon_msg mail"></i>\n                    </span>\n                <div class="msg_content">\n                    <h4>已发送验证邮件</h4>\n                    <p>已发送验证邮件到你的注册邮箱<span class="js_mail"></span>，请填写验证码：</p>\n                    <div class="mt1e spacing frm_control_group">\n                        <label class="frm_label">邮件验证码</label>\n                        <div class="oh">\n                                <span class="frm_input_box vcode">\n                                    <input type="text" class="frm_input js_email_code">\n                                </span>\n                        </div>\n                    </div>\n                    <div class="extra_msg">\n                        <h4>没有收到邮件？</h4>\n                        <ul>\n                            <li>1. 检查你的邮箱垃圾箱</li>\n                            <li>2. 若仍未收到确认，请尝试<a href=\'javascript:;\' class=\'js_resend_mail\'>重新发送</a></li>\n                        </ul>\n                    </div>\n                </div>\n            </div>\n            <div class="tool_bar border tc">\n                <a href="javascript:;" class="btn btn_default js_step2_prev">上一步</a>\n                <a href="javascript:;" class="btn btn_primary js_step2_mailcheck">下一步</a>\n            </div>\n        </div>\n<!--step3-->\n		<div class="js_step3" style="display:none;">\n            <div class="qrcode_scan qrcode_scan_light">\n                <div class="qrcode_wrp">\n                    <img class="qrcode js_qrcode" src="" alt="微信二维码" title="微信二维码" />\n                </div>\n                <div class="js_status">\n                </div>\n            </div>\n            <p class="js_forget tc" style="display:none;">若此微信号已无法使用，请点此<a href=\'javascript:;\'>重置绑定微信号</a></p>\n		</div>\n    </div>\n<!--tip no protect-->\n    <div class="safe_check js_off_protect">\n        <div class="page_msg large simple default">\n            <div class="inner group">\n                <span class="msg_icon_wrp">\n                    <i class="icon_msg info"></i>\n                </span>\n                <div class="msg_content">\n                    <h4>为了更好的保障公众号的安全，群发消息需开启微信保护</h4>\n                    <p>群发消息前，请你开启微信保护，开启后即可进行群发（需管理员微信号进行验证）。<a target="_blank" href="/cgi-bin/readtemplate?t=setting/safe-protect-detail-faq_tmpl&lang=zh_CN" target="_blank">详细说明</a></p>\n                </div>\n            </div>\n        </div>\n    </div>\n    <div class="safe_check js_off_protect1">\n        <div class="page_msg large simple default">\n            <div class="inner group">\n                <span class="msg_icon_wrp">\n                    <i class="icon_msg info"></i>\n                </span>\n                <div class="msg_content">\n                    <h4>为了更好的保护公众号的安全，查看密钥需开启微信保护</h4>\n                    <p>查看完整密钥前，请开启微信保护，开启后即可进行查看（需管理员微信号进行验证）。<a href=\'/cgi-bin/readtemplate?t=setting/safe-protect-detail-faq_tmpl&lang=zh_CN\' target="_blank">详细说明</a></p>\n                </div>\n            </div>\n        </div>\n    </div>\n<!--tip no helper-->\n    <div class="safe_check js_no_helper">\n        <div class="page_msg large simple default">\n            <div class="inner group">\n                <span class="msg_icon_wrp">\n                    <i class="icon_msg info"></i>\n                </span>\n                <div class="msg_content">\n                    <h4>为了更好的保障公众号的安全，群发消息需开启微信保护</h4>\n                    <p>群发消息前，需先绑定管理员微信号并开启微信保护。开启后即可进行群发（需管理员微信号进行验证）。<a target="_blank" href="/cgi-bin/readtemplate?t=setting/safe-protect-detail-faq_tmpl&lang=zh_CN" target="_blank">详细说明</a></p>\n                </div>\n            </div>\n        </div>\n    </div>\n    <div class="safe_check js_no_helper1">\n        <div class="page_msg large simple default">\n            <div class="inner group">\n                <span class="msg_icon_wrp">\n                    <i class="icon_msg info"></i>\n                </span>\n                <div class="msg_content">\n                    <h4>为了更好的保护公众号的安全，查看密钥需开启微信保护</h4>\n                    <p>查看完整密钥前，需先绑定管理员微信号并开启微信保护。开启后即可进行查看（需管理员微信号进行验证）。<a href=\'/cgi-bin/readtemplate?t=setting/safe-protect-detail-faq_tmpl&lang=zh_CN\' target="_blank">详细说明</a></p>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>\n';
});
define("common/wx/Step.js", ["widget/processor_bar.css", "tpl/step.html.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = wx.T,
			s = e("widget/processor_bar.css"),
			o = e("tpl/step.html.js"),
			u = {
				selected: 1
			},
			a = function() {
				var e = navigator.userAgent.toLowerCase(),
					t = /(msie) ([\w.]+)/.exec(e) || [],
					n = t[1] || "";
				return n == "msie";
			};

		function f(e) {
			this.opts = $.extend(!0, {}, u, e), this.init();
		}
		f.prototype.init = function() {
			var e = this.opts,
				t = e.names.length,
				n = parseInt(e.selected, 10),
				r = [],
				s, u;
			n = n < 0 ? 0 : n > t ? t : n;
			for (s = 0; s < t; s++) u = f.getClass(s + 1, n), r.push({
				name: e.names[s],
				cls: u
			});
			this.$dom = $(i(o, {
				stepArr: r,
				length: t
			})).appendTo(e.container), a() && this.$dom.addClass("ie");
		}, f.prototype.setStep = f.prototype.go = function(e) {
			var t = this.$dom.find("li.step"),
				n = t.length;
			return e = e < 0 ? 0 : e > n ? n : e, t.each(function(t, r) {
				var i = f.getClass(t + 1, e);
				t + 1 == n ? r.className = "no_extra " + "step grid_item size1of%s %s".sprintf(n, i) : r.className = "step grid_item size1of%s %s".sprintf(n, i);
			}), this;
		}, f.getClass = function(e, t) {
			var n;
			return e < t - 1 ? n = "pprev" : e === t - 1 ? n = "prev" : e === t ? n = "current" : e === t + 1 ? n = "next" : e > t + 1 && (n = "nnext"), n;
		}, n.exports = f;
	} catch (l) {
		wx.jslog({
			src: "common/wx/Step.js"
		}, l);
	}
});
define("safe/Mobile.js", ["common/wx/Tips.js", "common/wx/Cgi.js"], function(e) {
	"use strict";

	function t(e) {
		e = $.extend(!0, {}, d, e);
		var t = "click",
			a = this;
		a.container = "object" == typeof e.container ? e.container : $(e.container), e.mobile_num ? (a.container.find(e.old_num).text(e.mobile_num),
			a.container.find(e.old_btn).on(t, function() {
				if (!$(this).attr("disabled")) {
					var t = e.old_sendurl,
						d = {};
					e.auth && (d.auth = e.auth), o.post({
						url: t,
						mask: !1,
						data: d,
						error: function() {
							n.err("发送失败");
						}
					}, function(t) {
						var o = t.err_code;
						if (0 == o) {
							n.suc("发送成功"), e.send_msg && e.send_msg_txt && a.container.find(e.send_msg).text(e.send_msg_txt).show();
							var d = null,
								i = function() {
									var t = a.container.find(e.old_btn),
										n = +t.data("left");
									if (1 >= n) d && window.clearInterval(d), t.html("重发").removeAttr("disabled").removeClass("btn_disabled").addClass("btn_default"),
										e.send_msg && e.send_msg_txt && a.container.find(e.send_msg).text("");
									else {
										var o = --n;
										t.data("left", o).html(o + "秒后可重发");
									}
								};
							a.container.find(e.old_btn).data("left", e.timeout).html(e.timeout + "秒后可重发").attr("disabled", "true").removeClass("btn_default").addClass("btn_disabled"),
								d = window.setInterval(i, 1e3);
						} else n.err("-2341" == o ? "操作频率过快，请稍后再试。" : "发送失败");
					});
				}
			}), a.container.find(e.old_submit).on(t, function() {
				var t = a.container.find(e.old_code).val().trim() || "";
				return "" != t && /^\d{6}$/.test(t) ? e.before_check && "function" == typeof e.before_check && !e.before_check.apply(this) ? !1 : void o.post({
					url: e.old_checkurl,
					data: e.old_checkparam(t),
					mask: !1,
					error: e.old_callback
				}, e.old_callback) : (n.err("请输入正确的手机验证码"), !1);
			}), e.auto_send && setTimeout(function() {
				a.container.find(e.old_btn).click();
			}, 300)) : (a.container.find(e.old_btn).on(t, function() {
			if (!$(this).attr("disabled")) {
				var t = a.container.find(e.old_num).val().trim() || "";
				if ("" != t && /^\d{11}$/.test(t)) {
					var d = {
						mobile_num: t
					};
					e.auth && (d.auth = e.auth), o.post({
						url: e.get_code_new,
						data: d,
						mask: !1,
						error: function() {
							n.err("发送失败");
						}
					}, function(t) {
						var o = t.err_code;
						if (0 == o) {
							n.suc("发送成功");
							var d = null,
								i = a.container.find(e.old_num),
								r = a.container.find(e.old_btn),
								l = function() {
									var t = a.container.find(e.old_btn),
										n = +t.data("left");
									if (1 >= n) d && window.clearInterval(d), t.html("重发").removeAttr("disabled").removeClass("btn_disabled").addClass("btn_default"),
										a.container.find(e.old_num).removeAttr("disabled");
									else {
										var o = --n;
										t.data("left", o).html(o + "秒后可重发");
									}
								};
							r.data("left", e.timeout).html(e.timeout + "秒后可重发").attr("disabled", "true").removeClass("btn_default").addClass("btn_disabled"),
								i.attr("disabled", "true"), d = window.setInterval(l, 1e3);
						} else n.err("-2341" == o ? "操作频率过快，请稍后再试。" : "发送失败");
					});
				} else n.err("请输入正确的手机号");
			}
		}), a.container.find(e.old_submit).on(t, function() {
			var t = a.container.find(e.old_code).val().trim() || "",
				d = a.container.find(e.old_num).val().trim() || "";
			return "" != t && /^\d{6}$/.test(t) ? void o.post({
				url: e.check_code_new,
				data: e.old_checkparam(d, t),
				mask: !1,
				error: function() {
					n.err("验证失败");
				}
			}, e.old_callback) : (n.err("请输入正确的手机验证码"), !0);
		}));
	}
	var n = e("common/wx/Tips.js"),
		o = e("common/wx/Cgi.js"),
		d = {
			container: "",
			timeout: "60",
			mobile_num: "",
			old_num: ".js_old",
			old_code: ".js_num",
			old_btn: ".js_oldsend",
			old_submit: ".js_oldsubmit",
			send_msg: "",
			send_msg_txt: "",
			old_callback: $.noop,
			old_sendurl: "/cgi-bin/mmbizverifysms?action=get_code_default",
			old_checkurl: "/cgi-bin/mmbizverifysms?action=check_code_default",
			get_code_new: "/cgi-bin/mmbizverifysms?action=get_code_new",
			check_code_new: "/cgi-bin/mmbizverifysms?action=check_code_new",
			old_checkparam: $.noop,
			before_check: null,
			auto_send: !1,
			auth: ""
		};
	return t.prototype = {
		destroy: function() {}
	}, t;
});
define("safe/Scan.js", ["widget/qrcode_scan.css", "common/wx/Tips.js", "common/wx/Cgi.js"], function(t) {
	"use strict";

	function e(t) {
		t = $.extend(!0, {}, o, t);
		var e = {
				OK: 0,
				ERR_SYS: -1,
				ERR_ARGS: -2,
				ERR_APP_BLOCK: -10,
				UUID_SCANNING: 401,
				UUID_EXPIRED: 402,
				UUID_CANCELED: 403,
				UUID_SCANED: 404,
				UUID_CONFIRM: 405,
				UUID_INIT: 406,
				UUID_REQUEST: 407,
				UUID_ERROR: 500
			},
			s = {
				init: '<div class="status tips"><p>请用管理员微信(%s)扫描以上二维码进行验证</p></div>'.sprintf(t.wx_name),
				multi_init: '<div class="status tips"><p>非管理员扫码后，请联系管理员(%s)要求其验证</p></div>'.sprintf(t.wx_name),
				suc: '<div class="status"><i class="icon_qrcode_scan succ"></i><div class="status_txt"><h4>扫描成功</h4><p>请在微信上点击确认即可</p></div></div>',
				cannel: '<div class="status"><i class="icon_qrcode_scan warn"></i><div class="status_txt"><h4>您已取消此次操作</h4><p>您可以重新扫描验证，或关闭窗口</p></div></div>',
				ok: '<div class="status"><i class="icon_qrcode_scan succ"></i><div class="status_txt"><h4>确认成功</h4></div></div>',
				request: "<h2>已发送操作申请，请耐心等待</h2><p>等待公众号助手%s审核您的申请，您也可主动联系ta，请ta通过您的申请</p>".sprintf(t.wx_name)
			},
			a = this;
		a.container = "object" == typeof t.container ? t.container : $(t.container), a.status_container = "null" == t.status_container ? null : a.container.find(t.status_container),
			a.qrcode_container = a.container.find(t.qrcode_container), a.opt = t, a.opt.onshow && "function" == typeof a.opt.onshow ? a.opt.onshow.apply(a) : a.status_container && a.status_container.html(a.opt.dom_init ? a.opt.dom_init : a.opt.distinguish && !a.opt.default_initdom ? s.multi_init : s.init),
			a.timer = null, a.ctimer = null, a.json = {}, a.retcode = e.UUID_SCANNING, a.retcodes = {
				0: !0
			}, a.usedTimes = 0, a.uselessTimes = 0, a.repeatTimes = 0, a.firstChange = 0, a.speedy = 1, a.longWait = 0;
		var c = "https://huatuospeed.weiyun.com/cgi-bin/r.cgi?flag1=7839&flag2=12&flag3=3",
			r = function(t) {
				var e = t > 20 ? t - 20 : 0,
					i = t > 10 ? e > 0 ? 10 : t - 10 : 0,
					n = i > 0 ? 10 : t;
				return 4 * e + 2 * i + n;
			},
			u = function(t) {
				var e = 20;
				return t >= 40 ? e = 11 : t >= 30 ? e = 10 : t >= 20 ? e = 9 : t >= 16 ? e = 8 : t >= 12 ? e = 7 : t >= 10 ? e = 6 : t >= 7 ? e = 5 : t >= 4 && (e = 4),
					e;
			},
			d = function() {
				a.longWait++;
				var t = a.longWait > 6 ? 18 : 11 + a.longWait,
					e = c + "&" + t + "=1000";
				if (window.location.href.indexOf("https") > -1) {
					var i = new Image;
					i.src = e;
				} else console.log(e);
			},
			p = function() {
				var t = [];
				t.push("&1=" + 100 * a.usedTimes), t.push("&2=" + 100 * a.uselessTimes), t.push("&3=" + 100 * a.firstChange);
				var e = r(a.firstChange),
					n = u(e);
				n > 0 && t.push("&" + n + "=" + 100 * e), 0 == a.longWait && t.push("&19=1000");
				var o = c + t.join("");
				setTimeout(function() {
					if (window.location.href.indexOf("https") > -1) {
						var t = new Image;
						t.src = o;
					} else console.log(o);
				}, 0), setTimeout(function() {
					a.opt.onconfirm && "function" == typeof a.opt.onconfirm ? a.opt.onconfirm.apply(a) : i.suc("已确认成功");
				}, 150);
			},
			l = function() {
				a.timer && window.clearInterval(a.timer), a.timer = setInterval(m, a.opt.timeout * a.speedy),
					a.ctimer && window.clearInterval(a.ctimer), a.ctimer = setInterval(_, a.opt.checktimeout * a.speedy);
			},
			m = function() {
				a.opt.uuid ? n.post({
					url: wx.url("/safe/safeuuid?timespam=" + (new Date).getTime()),
					data: {
						uuid: a.opt.uuid,
						action: "json",
						type: "json"
					},
					mask: !1
				}, function(t) {
					a.json = t;
					var e = t && t.errcode ? +t.errcode : 0;
					if (a.retcode == e) {
						a.uselessTimes++, a.repeatTimes++;
						var i = !1;
						a.repeatTimes >= 20 && 4 != a.speedy ? (a.speedy = 4, i = !0) : a.repeatTimes >= 10 && 2 != a.speedy && (a.speedy = 2,
							i = !0), i && l();
					} else a.retcode = e, a.usedTimes++, 0 == a.firstChange && a.repeatTimes > 0 && (a.firstChange = a.repeatTimes),
						a.repeatTimes = 0, a.speedy = 1, l();
				}) : (a.timer && window.clearInterval(a.timer), a.ctimer && window.clearInterval(a.ctimer),
					f());
			},
			_ = function() {
				if (a.json && a.json.errcode == e.UUID_SCANED && 602 == +a.json.check_status && (a.retcode = e.UUID_CONFIRM), !a.retcodes[a.retcode]) switch (a.retcodes[a.retcode] = !0, a.retcode) {
					case e.UUID_ERROR:
						return a.timer && window.clearInterval(a.timer), a.ctimer && window.clearInterval(a.ctimer),
							void f();

					case e.UUID_EXPIRED:
						return a.timer && window.clearInterval(a.timer), a.ctimer && window.clearInterval(a.ctimer),
							void f();

					case e.UUID_SCANED:
						a.retcodes[e.UUID_CANCELED] = !1, a.opt.onscaned && "function" == typeof a.opt.onscaned ? a.opt.onscaned.apply(a) : a.status_container && a.status_container.html(s.suc);
						break;

					case e.UUID_CANCELED:
						a.retcodes[e.UUID_SCANED] = !1, a.opt.oncancel && "function" == typeof a.opt.oncancel ? a.opt.oncancel.apply(a) : a.status_container && a.status_container.html(s.cannel);
						break;

					case e.UUID_CONFIRM:
						if (a.timer && window.clearInterval(a.timer), a.ctimer && window.clearInterval(a.ctimer),
							a.json.code && (a.code = a.json.code), a.opt.auto_msgid && (a.msgid = a.opt.msgid), a.opt.distinguish) {
							var t = function() {
								var e = {
									action: "get_uuid",
									uuid: a.opt.uuid
								};
								a.opt.auth && (e.auth = a.opt.auth), n.post({
									url: wx.url("/misc/safeassistant"),
									data: e,
									mask: !1
								}, {
									done: function(t) {
										t && 0 == t.isadmin ? (a.isadmin = !1, a.distinguish = !0) : (a.issubadmin = !(1 == t.isadmin), a.isadmin = !0,
											a.distinguish = !0), p();
									},
									fail: function() {
										setTimeout(t, 300);
									}
								});
							};
							setTimeout(t, 0);
						} else a.status_container && a.status_container.html(s.ok), p();
						break;

					case e.UUID_REQUEST:
						a.json.code && (a.code = a.json.code), a.timer && window.clearInterval(a.timer), a.ctimer && window.clearInterval(a.ctimer),
							a.container.html(s.request), a.opt.onrequest && "function" == typeof a.opt.onrequest ? a.opt.onrequest.apply(a) : i.suc("已申请成功");
				}
			},
			f = function() {
				function t() {
					n.post({
						url: wx.url("/safe/safeqrconnect"),
						data: {
							appid: "wx3a432d2dbe2442ce",
							scope: "snsapi_contact",
							state: "0",
							redirect_uri: "https://mp.weixin.qq.com",
							login_type: "safe_center",
							f: "json",
							type: a.opt.uuid_type || "json",
							ticket: a.opt.ticket
						},
						mask: !1
					}, function(t) {
						if (t && t.uuid) {
							a.opt.uuid = t.uuid;
							var e = "/safe/safeqrcode?ticket=%s&uuid=%s&action=%s".sprintf(a.opt.ticket, a.opt.uuid, a.opt.type);
							a.opt.code && (e = e + "&code=" + a.opt.code), a.opt.source && (e = e + "&type=" + a.opt.source),
								a.opt.auth && (e = e + "&auth=" + a.opt.auth), a.opt.msgid && (e = e + "&msgid=" + a.opt.msgid), a.opt.second_openid && (e = e + "&second_openid=" + a.opt.second_openid),
								a.qrcode_container.attr("src", e), l(), a.json = {}, a.retcode = 0, a.retcodes = {
									0: !0
								};
						}
					});
				}
				if (a.opt.ticket) t();
				else {
					var e = {
						action: "get_ticket"
					};
					a.opt.auth && (e.auth = a.opt.auth), n.post({
						url: wx.url("/misc/safeassistant"),
						data: e,
						mask: !1
					}, {
						done: function(e) {
							e && e.base_resp && e.ticket && 0 == e.base_resp.ret ? (a.opt.ticket = e.ticket, a.opt.auto_msgid && e.operation_seq && (a.opt.msgid = e.operation_seq),
								t()) : setTimeout(function() {
								f();
							}, 1e3);
						},
						fail: function() {
							setTimeout(function() {
								f();
							}, 1e3);
						}
					});
				}
			};
		f(!0), setInterval(d, 3e5);
	}
	t("widget/qrcode_scan.css");
	var i = t("common/wx/Tips.js"),
		n = t("common/wx/Cgi.js"),
		o = {
			wx_name: "",
			container: "",
			type: "",
			ticket: "",
			source: "",
			second_openid: "",
			code: "",
			msgid: "",
			auto_msgid: !1,
			auth: "",
			uuid_type: "",
			status_container: ".js_status",
			qrcode_container: ".js_qrcode",
			timeout: 1e3,
			checktimeout: 1200,
			onshow: null,
			onscaned: null,
			oncancel: null,
			onconfirm: null,
			onrequest: null,
			dom_init: "",
			distinguish: !1,
			default_initdom: !1
		};
	return e.prototype = {
		destroy: function() {
			return this.timer && window.clearInterval(this.timer), this.ctimer && window.clearInterval(this.ctimer),
				this;
		}
	}, e;
});
define("tpl/biz_web/ui/dropdown.html.js", [], function(e, t, n) {
	return '<a href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label class="jsBtLabel" {if search}contenteditable="true"{/if}>{label}</label><i class="arrow"></i></a>\n<div class="dropdown_data_container jsDropdownList">\n    <ul class="dropdown_data_list">\n        {if renderHtml}\n        {renderHtml}\n        {else}\n            {each data as o index}\n            <li class="dropdown_data_item {if o.className}{o.className}{/if}">  \n                <a onclick="return false;" href="javascript:;" class="jsDropdownItem" data-value="{o.value}" data-index="{index}" data-name="{o.name}">{o.name}</a>\n            </li>\n            {/each}        \n        {/if}\n    </ul>\n</div>\n';
});
define("tpl/top.html.js", [], function(e, t, n) {
	return '<ul class="tab_navs title_tab" data-index="{itemIndex=0}">\n    {each data as o index}\n    {if (typeof o.acl == "undefined" || o.acl == 1)}\n    <li data-index="{itemIndex++}" class="tab_nav {if (itemIndex == 1)}first{/if} js_top {o.className}" data-id="{o.id}"><a href="{o.url}" {if o.target==\'_blank\'}target="_blank"{/if}>{o.name}</a></li>\n    {/if}\n    {/each}\n</ul>\n';
});
define("biz_web/lib/spin.js", [], function(e, t, n) {
	try {
		var r = +(new Date),
			i = function() {
				function e(e, t) {
					var n = ~~((e[a] - 1) / 2);
					for (var r = 1; r <= n; r++) t(e[r * 2 - 1], e[r * 2]);
				}

				function t(t) {
					var n = document.createElement(t || "div");
					return e(arguments, function(e, t) {
						n[e] = t;
					}), n;
				}

				function n(e, t, r) {
					return r && !r[x] && n(e, r), e.insertBefore(t, r || null), e;
				}

				function r(e, t) {
					var n = [p, t, ~~(e * 100)].join("-"),
						r = "{" + p + ":" + e + "}",
						i;
					if (!H[n]) {
						for (i = 0; i < P[a]; i++) try {
							j.insertRule("@" + (P[i] && "-" + P[i].toLowerCase() + "-" || "") + "keyframes " + n + "{0%{" + p + ":1}" + t + "%" + r + "to" + r + "}", j.cssRules[a]);
						} catch (s) {}
						H[n] = 1;
					}
					return n;
				}

				function i(e, t) {
					var n = e[m],
						r, i;
					if (n[t] !== undefined) return t;
					t = t.charAt(0).toUpperCase() + t.slice(1);
					for (i = 0; i < P[a]; i++) {
						r = P[i] + t;
						if (n[r] !== undefined) return r;
					}
				}

				function s(t) {
					return e(arguments, function(e, n) {
						t[m][i(t, e) || e] = n;
					}), t;
				}

				function o(t) {
					return e(arguments, function(e, n) {
						t[e] === undefined && (t[e] = n);
					}), t;
				}
				var u = "width",
					a = "length",
					f = "radius",
					l = "lines",
					c = "trail",
					h = "color",
					p = "opacity",
					d = "speed",
					v = "shadow",
					m = "style",
					g = "height",
					y = "left",
					b = "top",
					w = "px",
					E = "childNodes",
					S = "firstChild",
					x = "parentNode",
					T = "position",
					N = "relative",
					C = "absolute",
					k = "animation",
					L = "transform",
					A = "Origin",
					O = "Timeout",
					M = "coord",
					_ = "#000",
					D = m + "Sheets",
					P = "webkit0Moz0ms0O".split(0),
					H = {},
					B;
				n(document.getElementsByTagName("head")[0], t(m));
				var j = document[D][document[D][a] - 1],
					F = function(e) {
						this.opts = o(e || {}, l, 12, c, 100, a, 7, u, 5, f, 10, h, _, p, .25, d, 1);
					},
					I = F.prototype = {
						spin: function(e) {
							var t = this,
								r = t.el = t[l](t.opts);
							e && n(e, s(r, y, ~~(e.offsetWidth / 2) + w, b, ~~(e.offsetHeight / 2) + w), e[S]);
							if (!B) {
								var i = t.opts,
									o = 0,
									u = 20 / i[d],
									a = (1 - i[p]) / (u * i[c] / 100),
									f = u / i[l];
								(function h() {
									o++;
									for (var e = i[l]; e; e--) {
										var n = Math.max(1 - (o + e * f) % u * a, i[p]);
										t[p](r, i[l] - e, n, i);
									}
									t[O] = t.el && window["set" + O](h, 50);
								})();
							}
							return t;
						},
						stop: function() {
							var e = this,
								t = e.el;
							return window["clear" + O](e[O]), t && t[x] && t[x].removeChild(t), e.el = undefined, e;
						}
					};
				I[l] = function(e) {
					function i(n, r) {
						return s(t(), T, C, u, e[a] + e[u] + w, g, e[u] + w, "background", n, "boxShadow", r, L + A, y, L, "rotate(" + ~~(360 / e[l] * E) + "deg) translate(" + e[f] + w + ",0)", "borderRadius", "100em");
					}
					var o = s(t(), T, N),
						m = r(e[p], e[c]),
						E = 0,
						S;
					for (; E < e[l]; E++) S = s(t(), T, C, b, 1 + ~(e[u] / 2) + w, L, "translate3d(0,0,0)", k, m + " " + 1 / e[d] + "s linear infinite " + (1 / e[l] / e[d] * E - 1 / e[d]) + "s"), e[v] && n(S, s(i(_, "0 0 4px " + _), b, 2 + w)), n(o, n(S, i(e[h], "0 0 1px rgba(0,0,0,.1)")));
					return o;
				}, I[p] = function(e, t, n) {
					e[E][t][m][p] = n;
				};
				var q = "behavior",
					R = "url(#default#VML)",
					U = "group0roundrect0fill0stroke".split(0);
				return function() {
					var e = s(t(U[0]), q, R),
						r;
					if (!i(e, L) && e.adj) {
						for (r = 0; r < U[a]; r++) j.addRule(U[r], q + ":" + R);
						I[l] = function() {
							function e() {
								return s(t(U[0], M + "size", c + " " + c, M + A, -o + " " + -o), u, c, g, c);
							}

							function r(r, a, c) {
								n(d, n(s(e(), "rotation", 360 / i[l] * r + "deg", y, ~~a), n(s(t(U[1], "arcsize", 1), u, o, g, i[u], y, i[f], b, -i[u] / 2, "filter", c), t(U[2], h, i[h], p, i[p]), t(U[3], p, 0))));
							}
							var i = this.opts,
								o = i[a] + i[u],
								c = 2 * o,
								d = e(),
								m = ~(i[a] + i[f] + i[u]) + w,
								E;
							if (i[v])
								for (E = 1; E <= i[l]; E++) r(E, -2, "progid:DXImage" + L + ".Microsoft.Blur(pixel" + f + "=2,make" + v + "=1," + v + p + "=.3)");
							for (E = 1; E <= i[l]; E++) r(E);
							return n(s(t(), "margin", m + " 0 0 " + m, T, N), d);
						}, I[p] = function(e, t, n, r) {
							r = r[v] && r[l] || 0, e[S][E][t + r][S][S][p] = n;
						};
					} else B = i(e, k);
				}(), F;
			}();
		$.fn.spin = function(e, t) {
			return this.each(function() {
				var n = $(this),
					r = n.data();
				r.spinner && (r.spinner.stop(), delete r.spinner), e !== !1 && (e = $.extend({
					color: t || n.css("color")
				}, $.fn.spin.presets[e] || e), r.spinner = (new i(e)).spin(this));
			});
		}, $.fn.spin.presets = {
			tiny: {
				lines: 8,
				length: 2,
				width: 2,
				radius: 3
			},
			small: {
				lines: 8,
				length: 4,
				width: 3,
				radius: 5
			},
			large: {
				lines: 10,
				length: 8,
				width: 4,
				radius: 8
			}
		};
	} catch (s) {
		wx.jslog({
			src: "biz_web/lib/spin.js"
		}, s);
	}
});
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
define("cardticket/parse_data.js", ["cardticket/add/member_info_flag.js"], function(e) {
	"use strict";

	function t(e) {
		var t = c[e.card_type] || e.card_type;
		switch (t += "") {
			case "2":
				e = e.discount;
				break;

			case "1":
				e = e.groupon;
				break;

			case "3":
				e = e.gift;
				break;

			case "4":
				e = e.cash;
				break;

			case "0":
				e = e.general_coupon;
				break;

			case "10":
				e = e.member_card;
				break;

			case "21":
				e = e.scenic_ticket;
				break;

			case "22":
				e = e.movie_ticket;
				break;

			default:
				e = e.general_coupon || e.coupon;
		}
		return e ? (e.type = t, e) : null;
	}

	function _(e, t) {
		return "number" != typeof e && (e = praseFloat(e), isNaN(e)) ? 0 : (t || (t = 2), parseFloat(e.toFixed(t)));
	}

	function i(e) {
		var t = /^https?:\/\/mp.weixin.qq.com\/s/,
			_ = /^http:\/\/mp.weixin.qq.com\/bizmall\/cardshelf/,
			i = /^http:\/\/mp.weixin.qq.com\/bizmall\/mallshelf/;
		return t.test(e) ? 1 : _.test(e) ? 2 : i.test(e) ? 3 : 4;
	}

	function a(e, t) {
		return 4 == t ? e.replace("http://", "") : e;
	}

	function o(e) {
		var o = {},
			e = t(e);
		if (!e) return null;
		r(o, e), r(o, e.base_info);
		var n = e.base_info.date_info || {};
		o.time_type = f[n.type] || n.type, 1 == o.time_type && (o.begin_time = n.begin_timestamp, o.end_time = n.end_timestamp),
			o.from_day = n.fixed_begin_term || 0, o.fixed_term = n.fixed_term || 30, o.quantity = e.base_info.sku.quantity,
			o.shop_id_list = e.base_info.shop_id_list, o.location_id_list = e.base_info.location_id_list;
		var c = l[o.code_type];
		if (o.code_type = "undefined" != typeof c ? c : o.code_type, "undefined" == typeof o.code_type && (o.code_type = 1),
			o.least_cost = e.least_cost && e.least_cost / 100, o.reduce_cost = e.reduce_cost && e.reduce_cost / 100,
			"0" == o.least_cost && (o.least_cost = ""), o.discount = o.discount && (100 - o.discount) / 10,
			o.detail = 1 == o.type ? o.deal_detail : o.default_detail, /^http/.test(o.logo_url) || (o.logo_url = ""),
			o.shop_type || (o.shop_type = o.location_id_list && o.location_id_list.length ? 2 : 3), o.auto_update_new_location && (o.shop_type = 1),
			o.isnew = o.func_flag ? 16 & o.func_flag : !1, o.ispay = o.func_flag ? 64 & o.func_flag : !1, o.func_flag && (o.show_in_nearby = 32 & o.func_flag),
			o.ispay && (o.can_share = !0), o.ispay && (o.detail = o.detail ? o.detail.replace(/\n微信价：.*?元$/gm, "") : ""),
			o.price = _(e.base_info.sku.price / 100), o.original_price = _(e.base_info.sku.original_price / 100),
			1 == o.create_source && (o.isnew = !0), 1 == o.time_type && o.end_time < new Date / 1e3 && (o.is_expire = !0),
			o.is_intercomm = 16384 & o.func_flag, "undefined" != typeof e.base_info.task_info && (o.is_from_intercomm = !0,
				o.task_info = e.base_info.task_info), o.is_from_intercomm && (o.isnew = !0), o.isnew || (o.quantity = "--"),
			o.status = u[o.status] || o.status, o.discount && (o.supply_discount = !0), o.can_edit_quantity = 10 != o.type,
			10 == o.type) {
			if (o.promotion_url_name) {
				var p = {
					name: o.promotion_url_name,
					tips: o.promotion_url_sub_title,
					url: o.promotion_url
				};
				p.url_type = i(p.url), p.url = a(p.url);
				var d = [p];
			} else var d = [];
			e.custom_cell1 && (e.custom_cell1.url_type = i(e.custom_cell1.url), e.custom_cell1.url = a(e.custom_cell1.url),
				d.push(e.custom_cell1)), e.custom_cell2 && (e.custom_cell2.url_type = i(e.custom_cell2.url),
				e.custom_cell2.url = a(e.custom_cell2.url), d.push(e.custom_cell2)), o.config_url = d;
			var m = e.required_info || {
					info_flag: 0
				},
				y = e.optional_info || {
					info_flag: 0
				};
			o.require_keywords = s.flag2info(m.info_flag), o.option_keywords = s.flag2info(y.info_flag),
				o.require_self_keywords = m.field_list, o.option_self_keywords = y.field_list, o.must_activate = !o.auto_activate,
				o.supply_discount && (o.prerogative = o.prerogative.replace(/^用卡可享受.*?折优惠\n/, "")), o.quantity = "--";
		}
		return o;
	}

	function r(e, t) {
		for (var _ in t) t.hasOwnProperty(_) && "object" != typeof t[_] && (e[_] = t[_]);
		return e;
	}

	function n(e) {
		for (var t = {}, _ = [], i = 0; i < e.length; i++) {
			var a = o(e[i]);
			a && (t[a.id] = a, _.push(a));
		}
		return {
			card_cache: t,
			card_list: _
		};
	}
	var s = e("cardticket/add/member_info_flag.js"),
		c = {
			DISCOUNT: "2",
			MEMBER_CARD: "10",
			GROUPON: "1",
			GIFT: "3",
			CASH: "4",
			GENERAL_COUPON: "0",
			SCENIC_TICKET: "21",
			MOVIE_TICKET: "22"
		},
		l = {
			CODE_TYPE_TEXT: 0,
			CODE_TYPE_BARCODE: 1,
			CODE_TYPE_QRCODE: 2
		},
		u = {
			CARD_STATUS_INIT: 0,
			CARD_STATUS_NOT_VERIFY: 1,
			CARD_STATUS_VERIFY_FAIL: 2,
			CARD_STATUS_VERIFY_OK: 3,
			CARD_STATUS_DELETE: 4,
			CARD_STATUS_SYS_DELETE: 5,
			CARD_STATUS_DISPATCH: 6,
			CARD_STATUS_EXPIRED: 8
		},
		f = {
			DATE_TYPE_FIX_TIME_RANGE: 1,
			DATE_TYPE_FIX_TERM: 2,
			DATE_TYPE_PERMANENT: 100
		};
	return {
		parse_cardticket: o,
		parse_cardlist: n,
		url_type: i
	};
});
/**
 * @author cunjinli
 * @Usage:
 * var Checkbox = req("biz_web/ui/checkbox"");
 * $("input[type=checkbox]").checkbox(); 
 *
 * $("input").checkbox({onChanged: function($me){}});
 * $("input[type=radio]").checkbox({ multi: true }); 
 * 
 * $("input").checkbox("checked", true/false); 调用checked函数
 * $("input").checkbox("value");
 * $("input").checkbox("values");
 * 
 * 
 * var checkbox = new Checkbox({
	container: "body",
	label: "同意",
	name: "agree",
	type: "checkbox" 
 * });
 *
 */
define("biz_web/ui/checkbox.js", ["tpl/biz_web/ui/checkbox.html.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = {
				container: null,
				label: "",
				name: "",
				type: "checkbox"
			},
			s = e("tpl/biz_web/ui/checkbox.html.js"),
			o = wx.T,
			u = 1,
			a = 1;

		function f(e) {
			var t = $(e);
			t.each(function() {
				var e = $(this),
					t = e.prop("checked"),
					n = e.parent();
				t ? n.addClass("selected") : n.removeClass("selected");
			});
		}

		function l(e) {
			var t = $(e);
			t.each(function() {
				var e = $(this).prop("disabled"),
					t = $(this).parent();
				e ? t.addClass("disabled") : t.removeClass("disabled");
			});
		}

		function c() {
			return "checkbox" + u++;
		}
		var h = function(e) {
			this.options = $.extend(!0, {}, i, e), this.options.index = a++, this.$container = $(this.options.container), this.$dom = $(o(s, this.options)).appendTo(this.$container), this.$input = this.$dom.find("input"), this.$input.checkbox();
		};
		return h.prototype = {
			checked: function(e) {
				return typeof e != "undefined" && (this.$input.prop("checked", e), f(this.$input)), this.$input.prop("checked");
			},
			disabled: function(e) {
				return typeof e != "undefined" && (this.$input.prop("disabled", e), l(this.$input)), this.$input.prop("disabled");
			}
		}, $.fn.checkbox = function(e) {
			var t, n, r = !1,
				i, s;
			typeof e == "boolean" ? t = e : $.isPlainObject(e) ? (t = e.multi, n = e.onChanged) : typeof e == "string" ? (r = !0, i = e, s = [].slice.call(arguments, 1)) : typeof e == "undefined" && (e = {}), typeof t == "undefined" && (t = this.is("input[type=checkbox]"));
			var o = this,
				u = t ? "checkbox" : "radio",
				a = {
					checked: function(e) {
						return o.attr("checked", e), o.prop("checked", e), f(o), o;
					},
					disabled: function(e) {
						return o.attr("disabled", e), o.prop("disabled", e), l(o), o;
					},
					value: function() {
						var e = o.eq(0);
						return e.prop("checked") ? e.val() : "";
					},
					values: function() {
						var e = [];
						return o.each(function() {
							$(this).prop("checked") && e.push($(this).val());
						}), e;
					},
					adjust: function(e) {
						var t;
						return typeof e == "string" ? t = e.split(",") : t = e, t && t.length > 0 && o.each(function() {
							var e = $(this);
							t.indexOf(e.val()) >= 0 && (e.attr("checked", !0), f(e));
						}), this;
					},
					disable: function(e) {
						var t;
						return typeof e == "string" ? t = e.split(",") : t = e, t && t.length > 0 && o.each(function() {
							var e = $(this);
							t.indexOf(e.val()) >= 0 && (e.attr("disabled", !0), l(e));
						}), this;
					},
					setall: function(e) {
						o.each(function() {
							var t = $(this);
							t.attr("disabled", e ? !1 : !0), l(t);
						});
					},
					enable: function(e) {
						var t;
						return typeof e == "string" ? t = e.split(",") : t = e, t && t.length > 0 && o.each(function() {
							var e = $(this);
							t.indexOf(e.val()) >= 0 && (e.attr("disabled", !1), l(e));
						}), this;
					},
					label: function(e) {
						return e && (o.parent().find(".lbl_content").text(e), o.attr("data-label", e)), o;
					}
				};
			return r && typeof a[i] == "function" ? a[i].apply(a, s) : (this.addClass("frm_" + u).each(function() {
				var e = $(this),
					t = e.parent();
				if (!t.is("label")) {
					var n = e.attr("data-label");
					t = $('<label class="frm_{type}_label"><i class="icon_{type}"></i></label>'.format({
						type: u
					})).append("<span class='lbl_content'>{content}</span>".format({
						content: n
					})), t.insertBefore(e).prepend(e);
				}
				if (!this.id) {
					var r = c();
					this.id = r;
				}
				t.attr("for", this.id);
			}), f(this), l(this), !!e && !!e.initOnChanged && typeof n == "function" && o.parent().find("input[type=checkbox],input[type=radio]").each(function() {
				n.call(a, $(this));
			}), this.parent().delegate("input[type=checkbox],input[type=radio]", "click", function(e) {
				var r = $(this),
					i = r.prop("checked");
				t ? (r.attr("checked", i), f(r)) : (o.attr("checked", !1), r.attr("checked", !0).prop("checked", !0), f(o)), typeof n == "function" && n.call(a, r);
			}).addClass("frm_" + u + "_label"), a);
		}, h;
	} catch (p) {
		wx.jslog({
			src: "biz_web/ui/checkbox.js"
		}, p);
	}
});
define("common/wx/richEditor/msgSender.js", ["common/wx/popup.js", "widget/msg_sender.css", "common/qq/jquery.plugin/tab.js", "common/wx/richEditor/emotionEditor.js", "media/media_dialog.js", "common/wx/media/factory.js", "common/qq/Class.js", "common/wx/Tips.js", "common/wx/media/audio.js", "common/wx/media/img.js", "common/wx/media/video.js", "common/wx/media/cardmsg.js", "common/wx/tooltip.js", "common/wx/media/appmsg.js", "biz_web/utils/upload.js"], function(e) {
	"use strict";

	function t(e, t) {
		for (var i = [], n = 0; n < e.length; ++n) {
			var s = e[n];
			t && t[s.acl] && i.push(s);
		}
		return i;
	}

	function i(e) {
		var t = {},
			i = e.slice();
		i.push({
			acl: "can_video_msg",
			className: "tab_video",
			selector: "js_videoArea",
			text: "视频",
			type: 4,
			index: 3
		}, {
			acl: "can_use_shortvideo",
			className: "tab_video",
			selector: "js_videoArea",
			text: "视频",
			type: 21,
			index: 3
		});
		for (var n = 0; n < i.length; ++n) {
			var s = i[n];
			s.index = s.index || n, t[s.type] = s;
		}
		return t;
	}
	e("common/wx/popup.js"), e("widget/msg_sender.css");
	var n = (e("common/qq/jquery.plugin/tab.js"), e("common/wx/richEditor/emotionEditor.js")),
		s = e("media/media_dialog.js"),
		a = e("common/wx/media/factory.js"),
		o = e("common/qq/Class.js"),
		r = e("common/wx/Tips.js"),
		d = (e("common/wx/media/audio.js"),
			e("common/wx/media/img.js"), e("common/wx/media/video.js"), e("common/wx/media/cardmsg.js")),
		c = (e("common/wx/tooltip.js"),
			e("common/wx/media/appmsg.js"), e("biz_web/utils/upload.js")),
		p = 1,
		l = [{
			text: "图文消息",
			acl: "can_app_msg",
			className: "tab_appmsg",
			selector: "js_appmsgArea",
			type: 10
		}, {
			text: "文字",
			acl: "can_text_msg",
			className: "tab_text",
			selector: "js_textArea",
			innerClassName: "no_extra",
			type: 1
		}, {
			text: "图片",
			acl: "can_image_msg",
			className: "tab_img",
			selector: "js_imgArea",
			type: 2
		}, {
			text: "语音",
			acl: "can_voice_msg",
			className: "tab_audio",
			selector: "js_audioArea",
			type: 3
		}, {
			text: "视频",
			acl: "can_video_msg",
			className: "tab_video",
			selector: "js_videoArea",
			type: 15
		}, {
			text: "商品消息",
			acl: "can_commodity_app_msg",
			className: "tab_commondity_appmsg",
			selector: "js_commondityAppmsgArea",
			type: 11
		}, {
			text: "卡券",
			acl: "can_card_msg",
			className: "tab_cardmsg",
			selector: "js_cardmsgArea",
			type: 16
		}],
		m = a.itemRender,
		g = o.declare({
			select: function() {
				this.msgSender.type = this.type;
			},
			fillData: function() {},
			getData: function() {},
			click: function() {
				this.msgSender.type = this.type;
			}
		}),
		f = g.Inherit({
			init: function(e) {
				this.msgSender = e, this.type = 1, this.info = e.infos[this.type], this.wordlimit = e.opt.wordlimit,
					this.index = this.info && this.info.index;
			},
			fillData: function(e) {
				var t = this.msgSender;
				t.type = this.type, t.select(this.index), t.emotionEditor.setContent(e.content);
			},
			getData: function() {
				var e = this.msgSender.emotionEditor.getContent();
				return {
					type: this.type,
					content: e
				};
			},
			clear: function() {
				return this.fillData({
					content: ""
				});
			},
			isValidate: function(e) {
				var t = e && 1 == e.type && !!("" != e.content && e.content.length <= this.wordlimit);
				return t || r.err("文字必须为1到%s个字".sprintf(this.wordlimit)), t;
			},
			click: function() {
				var e = this;
				this.msgSender.type = this.type, setTimeout(function() {
					e.msgSender.emotionEditor.insertHTML();
				});
			}
		}),
		u = g.Inherit({
			init: function(e, t) {
				this.type = t, this.msgSender = e, this.info = e.infos[t], this.index = this.info && this.info.index;
			},
			click: function() {
				var e = this,
					t = this.type;
				if (this.msgSender.type = t, 2 == t || 3 == t) {
					var i = 2 == t ? "msgSendImgUploadBt" : "msgSendAudioUploadBt";
					c.uploadImageLibFile({
							container: "#" + i,
							type: t,
							doublewrite: !0,
							groupid: 1,
							pick: {
								multiple: !1
							},
							onComplete: function(i, n, s, a) {
								if (0 == a.base_resp.ret) {
									var o, r = "msgSender_media_%s_%s".sprintf(e.msgSender.gid, t);
									o = 2 == t ? {
											file_id: a.content,
											source: "file"
										} : {
											file_id: a.content,
											source: "file",
											name: s.name,
											play_length: s.size
										}, $("." + e.info.selector).find(".jsMsgSendTab").hide().after('<div id="%s"></div>'.sprintf(r)),
										m[t] && m[t]("#" + r, o), $("#" + r).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
										e.msgSender.opt.onSelect && e.msgSender.opt.onSelect(t, o);
								}
							},
							onAllComplete: function() {
								r.suc("上传成功");
							}
						}),
						function() {
							$("#" + i).trigger("click");
						}.delay(.1);
				}
				if (10 != this.type && 2 != this.type && 3 != this.type && 11 != this.type && 15 != this.type) {
					var n = null,
						e = this;
					return n = 10 == e.type || 11 == e.type || 15 == e.type ? s.getAppmsg : s.getFile, n({
						type: e.type,
						begin: 0,
						count: 10,
						onSelect: function(t, i) {
							var n = e.msgSender;
							e.msgSender.type = t, n.select(e.index);
							var s = "msgSender_media_%s_%s".sprintf(n.gid, t);
							$("." + e.info.selector).html('<div id="%s"></div>'.sprintf(s)), m[t] && m[t]("#" + s, i),
								$("#" + s).append('<a href="javascript:;" class="link_dele jsmsgSenderDelBt" onclick="return false;">删除</a>'),
								e.msgSender.opt.onSelect && e.msgSender.opt.onSelect(t, i);
						}
					}), !1;
				}
			},
			fillData: function(e) {
				var t = this.msgSender,
					i = this.type,
					n = "msgSender_media_%s_%s".sprintf(t.gid, i);
				$("." + this.info.selector).find(".jsMsgSendTab").hide().after('<div id="%s"></div>'.sprintf(n)),
					t.select(this.index), this.msgSender.type = i, m[i] && m[i]("#" + n, e), $("#" + n).append('<a href="javascript:;" class="link_dele jsmsgSenderDelBt" onclick="return false;">删除</a>');
			},
			clear: function() {
				this.type;
				return $("." + this.info.selector).html("");
			},
			getData: function(e) {
				var t = this.type,
					i = "msgSender_media_%s_%s".sprintf(this.msgSender.gid, t),
					n = $("#" + i).data("opt");
				if (n) {
					if (e) {
						n.type = t;
						var s = n.data || {};
						return $.extend(n, s);
					}
					return 10 == t || 11 == t ? {
						type: t,
						app_id: n.data.app_id
					} : 15 == t ? {
						type: t,
						app_id: n.app_id
					} : {
						type: t,
						file_id: n.file_id
					};
				}
				return !1;
			},
			isValidate: function(e) {
				var t = !!e;
				if (e) {
					var i = e.type;
					t = 10 == i || 11 == i || 15 == i ? !(!e.type || !e.app_id) : !(!e.type || !e.file_id);
				}
				return t || r.err("请添加素材"), t;
			}
		}),
		h = {
			wordlimit: 600
		},
		_ = o.declare({
			init: function(e, n) {
				var s = this,
					a = 0;
				e = $(e).show(), s.dom = e, s.gid = p++, s.opt = $.extend(!0, {}, h, n);
				var o = l,
					r = n && n.acl || {};
				o = t(o, r), s.infos = i(o), s.op = {
					1: new f(s),
					2: new u(s, 2),
					3: new u(s, 3),
					4: new u(s, 4),
					6: new u(s, 6),
					7: new u(s, 15),
					9: new u(s, 21),
					10: new u(s, 10),
					11: new u(s, 11),
					15: new u(s, 15),
					16: new d(s),
					21: new u(s, 21)
				}, s.tab = e.tab({
					index: a,
					tabs: o,
					select: function() {},
					click: function(e, t, i, a) {
						return n.onClick && n.onClick(e, t, i, a), s.op[a] && s.op[a].click();
					}
				}), s._init(e, n), s.initEvent();
				var c = n.data;
				c ? 10 == this.opt.data.type ? c.data && s.setData(c) : s.setData(c) : s.type = o[0] && o[0].type ? o[0].type : 10;
			},
			initEvent: function() {
				var e = this;
				$(".jsMsgSenderPopBt").click(function() {
					var t, i = $(this).data("type"),
						n = $(this).data("index"),
						a = $(".jsMsgSendTab[data-index=" + n + "]");
					(t = 10 == i || 11 == i || 15 == i ? s.getAppmsg : s.getFile)({
						type: i,
						begin: 0,
						count: 10,
						onSelect: function(t, i) {
							e.type = t, e.select(n);
							var s = "msgSender_media_%s_%s".sprintf(e.gid, t);
							a.hide().after('<div id="%s"></div>'.sprintf(s)), m[t] && m[t]("#" + s, i), $("#" + s).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
								e.opt.onSelect && e.opt.onSelect(t, i);
						}
					});
				}), this.dom.on("click", ".jsmsgSenderDelBt", function() {
					$(this).parent().siblings(".jsMsgSendTab").show(), $(this).parent().remove();
					var t;
					$("#msgSendImgUploadBt").is(":visible") && 0 == $("#msgSendImgUploadBt").parent().find("input[type=file]").length ? t = 2 : $("#msgSendAudioUploadBt").is(":visible") && 0 == $("#msgSendAudioUploadBt").parent().find("input[type=file]").length && (t = 3), (2 == t || 3 == t) && (c.uploadImageLibFile({
						container: 2 == t ? "#msgSendImgUploadBt" : "#msgSendAudioUploadBt",
						type: t,
						doublewrite: !0,
						groupid: 1,
						pick: {
							multiple: !1
						},
						onComplete: function(i, n, s, a) {
							if (0 == a.base_resp.ret) {
								var o, r = "msgSender_media_%s_%s".sprintf(e.gid, t);
								o = 2 == t ? {
										file_id: a.content,
										source: "file"
									} : {
										file_id: a.content,
										source: "file",
										name: s.name,
										play_length: s.size
									}, $(".jsMsgSendTab[data-type=" + t + "]").hide().after('<div id="%s"></div>'.sprintf(r)),
									m[t] && m[t]("#" + r, o), $("#" + r).append('<a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="%s" onclick="return false;">删除</a>'.sprintf(t)),
									e.opt.onSelect && e.opt.onSelect(t, o);
							}
						},
						onAllComplete: function() {
							r.suc("上传成功");
						}
					}), function() {
						$(2 == t ? "#msgSendImgUploadBt" : "#msgSendAudioUploadBt").trigger("click");
					}.delay(.1));
				});
			},
			setData: function(e) {
				if (e) {
					var t = this,
						i = null,
						n = e.type || 10;
					t.type = n, (i = t.op[n]) && i.fillData(e);
				}
			},
			_init: function(e) {
				this.dom = e, this.emotionEditor = new n($(".js_textArea", e), {
					wordlimit: this.opt.wordlimit,
					linebreak: !0
				});
			},
			getData: function(e) {
				if (this.type) {
					var t = this.op[this.type].getData(e);
					return {
						error: !this.isValidate(),
						data: t
					};
				}
				return {
					error: !0
				};
			},
			getArea: function(e) {
				return this.dom.find("." + this.infos[e].selector);
			},
			getTabs: function() {
				return this.tab.getTabs();
			},
			isValidate: function() {
				var e = this.type && this.op[this.type].getData();
				return this.type && this.op[this.type].isValidate(e);
			},
			clear: function() {
				return this.type && this.op[this.type].clear();
			},
			select: function(e) {
				return this.tab.select(e);
			}
		});
	return _;
});
define("message/message_cgi.js", ["common/wx/Tips.js", "common/wx/Cgi.js"], function(e, s, r) {
	"use strict";
	var t = {
			masssend: "/cgi-bin/masssend?t=ajax-response",
			massdel: "/cgi-bin/masssendpage?action=delete",
			star: "/cgi-bin/setstarmessage?t=ajax-setstarmessage",
			save: "/cgi-bin/savemsgtofile?t=ajax-response",
			sendMsg: "/cgi-bin/singlesend?t=ajax-response&f=json",
			getNewMsg: "/cgi-bin/singlesendpage?tofakeid=%s&f=json&action=sync&lastmsgfromfakeid=%s&lastmsgid=%s&createtime=%s",
			getNewMsgCount: "/cgi-bin/getnewmsgnum?f=json&t=ajax-getmsgnum&lastmsgid=",
			pageNav: "/cgi-bin/message?f=json&offset=%s&day=%s&keyword=%s&action=%s&frommsgid=%s&count=%s",
			searchMsgByKeyword: "/cgi-bin/getmessage?t=ajax-message&count=10&keyword="
		},
		n = e("common/wx/Tips.js"),
		o = e("common/wx/Cgi.js");
	r.exports = {
		masssend: function(e, s, r) {
			o.post({
				url: wx.url(t.masssend),
				data: e,
				error: function() {
					n.err("发送失败"), r && r();
				}
			}, function(e) {
				if (!e || !e.base_resp) return n.err("发送失败"), void(r && r(e));
				var t = e.base_resp.ret;
				return "0" == t ? (n.suc("发送成功"), void(s && s(e))) : (n.err("64004" == t ? "今天的群发数量已到，无法群发" : "67008" == t ? "消息中可能含有具备安全风险的链接，请检查" : "-8" == t ? "请输入验证码" : "14002" == t ? "没有“审核通过”的门店。确认有至少一个“审核通过”的门店后可进行卡券投放。" : "14003" == t ? "投放用户缺少测试权限，请先设置白名单" : "发送失败"),
					void(r && r(e)));
			});
		},
		massdel: function(e, s, r, a) {
			o.post({
				url: wx.url(t.massdel),
				data: {
					id: e,
					idx: a
				},
				error: function() {
					n.err("删除失败");
				}
			}, function(e) {
				return e && e.base_resp && 0 == e.base_resp.ret ? (n.suc("删除成功"), void(s && s(e))) : (n.err("删除失败"),
					void(r && r(e)));
			});
		},
		getNewMsg: function(e, s, r, n, a, i) {
			o.get({
				url: wx.url(t.getNewMsg.sprintf(e, s, r, n)),
				mask: !1,
				handlerTimeout: !0,
				error: i
			}, function(e) {
				e && e.base_resp && 0 == e.base_resp.ret && a && a(e.page_info.msg_items.msg_item);
			});
		},
		save: function(e, s, r, a) {
			"function" == typeof r && (a = r, r = ""), o.post({
				mask: !1,
				url: wx.url(t.save),
				data: {
					msgid: e,
					filename: s,
					digest: r
				},
				error: function() {
					n.err("保存素材失败");
				}
			}, function(e) {
				if (!e || !e.base_resp) return void n.err("保存素材失败");
				var s = e.base_resp.ret;
				"0" == s ? (n.suc("保存素材成功"), "function" == typeof a && a(e)) : n.err("保存素材失败");
			});
		},
		star: function(e, s, r) {
			o.post({
				mask: !1,
				url: wx.url(t.star),
				data: {
					msgid: e,
					value: 1 == s ? 0 : 1
				},
				error: function() {
					n.err(1 == s ? "取消收藏失败" : "收藏消息失败");
				}
			}, function(e) {
				if (!e || !e.base_resp) return void n.err(1 == s ? "取消收藏失败" : "收藏消息失败");
				var t = e.base_resp.ret;
				0 != t ? n.err(1 == s ? "取消收藏失败" : "收藏消息失败") : (n.suc(1 == s ? "取消收藏成功" : "收藏消息成功"), "function" == typeof r && r(e));
			});
		},
		sendMsg: function(e, s, r) {
			o.post({
				url: wx.url(t.sendMsg),
				data: e,
				error: function() {
					n.err("发送失败"), r && r();
				}
			}, function(e) {
				if (!e || !e.base_resp) return n.err("发送失败"), void(r && r(e));
				var t = e.base_resp.ret;
				return 0 == t ? (n.suc("回复成功"), void("function" == typeof s && s(e))) : (n.err(10703 == t ? "对方关闭了接收消息" : 10700 == t ? "不能发送，对方不是你的粉丝" : 10701 == t ? "该用户已被加入黑名单，无法向其发送消息" : 62752 == t ? "消息中可能含有具备安全风险的链接，请检查" : 10704 == t ? "该素材已被删除" : 10705 == t ? "该素材已被删除" : 10706 == t ? "由于该用户48小时未与你互动，你不能再主动发消息给他。直到用户下次主动发消息给你才可以对其进行回复。" : -8 == t ? "请输入验证码" : "发送失败"),
					void(r && r(e)));
			});
		},
		getNewMsgCount: function(e, s, r) {
			o.post({
				mask: !1,
				handlerTimeout: !0,
				url: wx.url(t.getNewMsgCount + e),
				error: r
			}, function(e) {
				"function" == typeof s && s(e);
			});
		},
		quickReply: function(e, s, r) {
			this.sendMsg({
				mask: !1,
				tofakeid: e.toFakeId,
				imgcode: e.imgcode,
				type: 1,
				content: e.content,
				out_trade_no: e.out_trade_no,
				quickreplyid: e.quickReplyId
			}, s, r);
		},
		pageNav: function(e, s, r) {
			var n = t.pageNav.sprintf((e.page - 1) * e.count, e.day || "", e.keyword || "", e.action || "", e.frommsgid || "", e.count || "");
			o.post({
				dataType: "json",
				url: wx.url(n),
				mask: !1,
				data: {},
				error: r
			}, function(e) {
				e && e.base_resp && 0 == e.base_resp.ret && "function" == typeof s && s(e.msg_items.msg_item);
			});
		},
		searchMsgByKeyword: function(e, s, r) {
			o.post({
				dataType: "html",
				mask: !1,
				url: wx.url(url.searchMsgByKeyword + e),
				error: function() {
					n.err("系统发生异常，请刷新页面重试"), r && r({});
				}
			}, function(e) {
				"function" == typeof s && s($.parseJSON(e));
			});
		}
	};
});
define("safe/safe_check.js", ["common/wx/Tips.js", "common/wx/popup.js", "common/wx/Cgi.js", "safe/Scan.js", "safe/Mobile.js", "biz_web/ui/checkbox.js", "common/wx/Step.js", "safe/tpl/safe_check.html.js"], function(e, t, s) {
	"use strict";
	var i = e("common/wx/Tips.js"),
		n = (e("common/wx/popup.js"), e("common/wx/Cgi.js")),
		o = e("safe/Scan.js"),
		a = e("safe/Mobile.js"),
		c = (e("biz_web/ui/checkbox.js"),
			e("common/wx/Step.js")),
		r = e("safe/tpl/safe_check.html.js");
	s.exports = {
		_types: {
			send: '_"(群发)"'
		},
		check: function(e, t, s) {
			s && s.onClose && s.onClose(), e.checkType = e.wx_protect ? 2 : 0;
			var i = !0;
			switch (e.source) {
				case "msgs":
					i = 2 == (2 & e.protect_status);
					break;

				case "cburl":
					i = 4 == (4 & e.protect_status);
					break;

				case "appkey":
					i = 8 == (8 & e.protect_status);
					break;

				case "showas":
					i = !0;
					break;

				default:
					i = !1;
			}
			if (i) {
				var n, a = null,
					c = $(r).find(s && s.checkdom ? s.checkdom : ".js_wxcheck").popup({
						title: "微信验证",
						width: 860,
						onShow: function() {
							a = this, $(this.$dialogWrp.get(0)).css({
								"margin-top": -227
							});
						},
						close: function() {
							n && n.destroy(), s && s.onClose && s.onClose(), this.remove();
						}
					});
				n = new o({
					container: c,
					type: "check",
					source: e.source,
					msgid: e.msgid,
					distinguish: e.distinguish,
					wx_name: "wx.pass" == e.wx_alias ? "" : e.wx_alias,
					onconfirm: function() {
						var i = {
							code: this.code
						};
						this.isadmin && !this.issubadmin || !this.distinguish ? (i.type = 1, a.remove()) : (i.type = this.issubadmin ? 2 : -1,
							"msgs" == e.source && 2 == i.type ? a.remove() : (this.container.find(".js_wxchecks").html((s && s.unadmin_html ? s.unadmin_html : '<div class="page_msg large simple default"><div class="inner group"><span class="msg_icon_wrp"><i class="icon_msg_primary waiting"></i></span><div class="msg_content"><h4>已发送操作申请</h4><p>请等待管理员(%s)验证操作申请，验证通过后操作将立即进行。此申请在30分钟后过期，请尽快联系管理员验证。</p></div></div><div class="tool_bar border"><a href="javascript:;" class="btn btn_primary js_unadminsend">我知道了</a></div></div>').sprintf(this.opt.wx_name)),
								this.container.find(".js_wxchecks").find(".js_unadminsend").on("click", function() {
									a.remove(), e.unadmin_url ? location.href = e.unadmin_url : location.reload();
								}), this.container.find(".pop_closed").on("click", function() {
									a.remove(), e.unadmin_url ? location.href = e.unadmin_url : location.reload();
								}), a.resetPosition())), "function" == typeof t && t(i);
					},
					onrequest: function() {
						"function" == typeof t && t({
							code: this.code,
							type: -1
						});
					}
				}), a.resetPosition();
			} else "function" == typeof t && t("wx.pass");
		},
		off_protect_tip: function(e, t) {
			t && t.onClose && t.onClose();
			$(r).find(t.dom ? t.dom : ".js_off_protect").popup({
				title: "开启微信保护",
				width: 860,
				close: function() {
					this.remove();
				},
				buttons: [{
					text: "开始",
					click: function() {
						this.remove(), "function" == typeof e && e();
					},
					type: "primary"
				}, {
					text: "取消",
					click: function() {
						t && t.onClose && t.onClose(), this.remove();
					},
					type: "default"
				}]
			});
		},
		no_helper_tip: function(e, t) {
			t && t.onClose && t.onClose();
			$(r).find(t.dom ? t.dom : ".js_no_helper").popup({
				title: "开启微信保护",
				width: 860,
				close: function() {
					this.remove();
				},
				buttons: [{
					text: "开始",
					click: function() {
						this.remove(), "function" == typeof e && e();
					},
					type: "primary"
				}, {
					text: "取消",
					click: function() {
						t && t.onClose && t.onClose(), this.remove();
					},
					type: "default"
				}]
			});
		},
		bind: function(e, t, s, d) {
			function l() {
				var i = w.find(".js_step3");
				i.show(), u = new o({
					container: i,
					type: e,
					source: t.source,
					code: t.code,
					auth: t.auth,
					dom_init: '<div class="status tips"><p>请使用微信扫描二维码进行验证</p></div>',
					onconfirm: function() {
						h.remove(), "function" == typeof s && s({
							data: this,
							wx_name: "wx.pass"
						});
					}
				});
			}

			function f() {
				var e = w.find(".js_step3"),
					i = e.find(".js_forget"),
					n = "/misc/rebindverify?action=mail_get&safeaction=wx_mail_get&t=setting/safe-rebind";
				i.find("a").attr("href", wx.url(n + ("ticket" == t.auth ? "&auth=ticket" : ""))), i.show(),
					e.show(), u = new o({
						container: e,
						type: _,
						source: t.source,
						code: t.code,
						auth: t.auth,
						wx_name: "wx.pass" == t.wx_alias ? "" : t.wx_alias,
						onconfirm: function() {
							h.remove(), "function" == typeof s && s({
								data: this,
								wx_name: "wx.pass"
							});
						}
					});
			}!t && (t = {}), !d && (d = {});
			var _;
			switch (e) {
				case "bind_showas":
					_ = "change_protect_showas";
					break;

				case "bind_masssend":
					_ = "change_protect_masssend";
					break;

				case "bind_login":
					_ = "change_protect_login";
					break;

				default:
					_ = "bind";
			}
			d && d.onClose && d.onClose();
			var u, m, h, p = t && t.wx_alias ? !0 : !1,
				b = "click",
				w = $(r).find(".js_bind").popup({
					className: "dialog_process",
					title: "bind" == e ? "绑定公众号安全管理员" : "开启微信保护",
					width: 960,
					onShow: function() {
						h = this, $(this.$dialogWrp.get(0)).css({
							"margin-top": -227
						});
					},
					close: function() {
						d && d.onClose && d.onClose(), u && u.destroy(), this.remove();
					}
				});
			n.post({
					url: wx.url("/misc/safeassistant?action=checkwx_report") + (t.auth ? "&auth=ticket" : ""),
					mask: !1
				}, $.noop), m = new c({
					container: w.find(".js_process"),
					selected: 1,
					names: ["1 选择验证方式", "2 账号验证", p ? "3 开启微信保护" : "3 绑定微信号"]
				}), w.find(".js_step1_num").text(t && t.mobile ? t.mobile : ""), w.find(".js_step1_email").text(t && t.bind_mail ? t.bind_mail : ""),
				t && "1" == t.third_status && w.find(".js_option").show(), w.find(".frm_radio").checkbox({
					multi: !1,
					onChanged: function(e) {
						w.find(".js_step1_next").data("type", e.val());
					}
				}), w.find(".js_step1_next").data("type", "1").on(b, function() {
					var e = $(this).data("type");
					if (!("1" != e || t && t.mobile)) return void i.err("手机号为空，请选择其他验证方式");
					if (!("2" != e || t && t.bind_mail)) return void i.err("邮箱为空，请选择其他验证方式");
					if (m.setStep(2), w.find(".js_step1").hide(), "1" == e) {
						var s = w.find(".js_setp2_mobile");
						s.find(".js_mobile_forget").attr("href", wx.url("/misc/rebindverify?action=mail_get&safeaction=mobile_mail_get&t=setting/safe-rebind" + ("ticket" == t.auth ? "&auth=ticket" : ""))),
							s.show(), s.find(".js_oldsend").click();
					} else if ("2" == e) {
						var s = w.find(".js_step2_mail");
						s.show(), s.find(".js_resend_mail").click();
					} else w.find(".js_step2_name").show();
				}), w.find(".js_step1_cancel").on(b, function() {
					h.remove();
				}), w.find(".js_step2_prev").on(b, function() {
					$(this).parent().parent().hide(), m.setStep(1), w.find(".js_step1").show();
				}), t && t.mobile && new a({
					container: w.find(".js_setp2_mobile"),
					mobile_num: t.mobile,
					old_submit: ".js_step2_mobilecheck",
					auth: t.auth,
					old_callback: function(e) {
						w.find(".js_step2_mobilecheck").html("下一步").removeClass("btn_loading").attr("disabled", !1);
						var s = e.err_code;
						0 == s ? (w.find(".js_setp2_mobile").hide(), m.setStep(3), t.wx_alias ? f() : l()) : i.err("验证失败");
					},
					old_checkparam: function(e) {
						t.code = e, t.source = "mobile";
						var s = {
							code_num: e
						};
						return t.auth && (s.auth = t.auth), s;
					},
					before_check: function() {
						return $(this).attr("disabled") ? !1 : ($(this).html("验证中<i></i>").addClass("btn_loading").attr("disabled", !0), !0);
					}
				}), w.find(".js_resend_mail").on(b, function() {
					n.post({
						url: wx.url("/misc/rebindverify?action=send_safe_code"),
						mask: !1
					}, function(e) {
						if (!e && !e.base_resp) return void i.err("邮件发送失败");
						switch (+e.base_resp.ret) {
							case 0:
								i.suc("邮件发送成功");
								break;

							default:
								i.err("邮件发送失败");
						}
					});
				}), w.find(".js_step2_namecheck").on(b, function() {
					var e = w.find(".js_cardname"),
						s = w.find(".js_cardid"),
						o = e.val().trim(),
						a = s.val().trim();
					if (!o) return i.err("请输入身份证姓名"), !1;
					if (!a) return i.err("请输入身份证号码"), !1;
					$(this).html("验证中<i></i>").addClass("btn_loading").attr("disabled", !0);
					var c = {
						card_name: o,
						card_id: a
					};
					t.auth && (c.auth = t.auth), n.post({
						url: wx.url("/misc/safeassistant?action=check_id"),
						data: c,
						mask: !1
					}, function(e) {
						if (w.find(".js_step2_namecheck").html("下一步").removeClass("btn_loading").attr("disabled", !1), !e && !e.check_flag && !e.code) return void i.err("验证失败");
						switch (+e.check_flag) {
							case 1:
								t.code = e.code, t.source = "id", w.find(".js_step2_name").hide(), m.setStep(3), t.wx_alias ? f() : l();
								break;

							case -5:
								i.err("请1分钟后重新尝试");
								break;

							default:
								i.err("验证失败");
						}
					});
				}), w.find(".js_step2_mailcheck").on(b, function() {
					var e = w.find(".js_email_code").val().trim();
					if (!e) return i.err("请输入邮件验证码"), !1;
					$(this).html("验证中<i></i>").addClass("btn_loading").attr("disabled", !0);
					var s = {
						safecode: e
					};
					t.auth && (s.auth = t.auth), n.post({
						url: wx.url("/misc/safeassistant?action=check_safecode"),
						data: s,
						mask: !1
					}, function(e) {
						if (w.find(".js_step2_mailcheck").html("下一步").removeClass("btn_loading").attr("disabled", !1), !e && !e.check_flag && !e.code) return void i.err("验证失败");
						switch (+e.check_flag) {
							case 1:
								t.code = e.code, t.source = "safecode", w.find(".js_step2_mail").hide(), m.setStep(3), t.wx_alias ? f() : l();
								break;

							default:
								i.err("验证失败");
						}
					});
				});
		}
	};
});
define("biz_web/ui/dropdown.js", ["biz_web/widget/dropdown.css", "tpl/biz_web/ui/dropdown.html.js"], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict", e("biz_web/widget/dropdown.css");
		var i = e("tpl/biz_web/ui/dropdown.html.js"),
			s = {
				label: "请选择",
				data: [],
				callback: $.noop,
				render: $.noop,
				delay: 500,
				disabled: !1,
				search: !1
			},
			o = "dropdown_menu";

		function u(e) {
			e.render && typeof e.render && (e.renderHtml = "", $.each(e.data, function(t, n) {
				e.renderHtml += e.render(n);
			})), e = $.extend(!0, {}, s, e);
			var t = this;
			t.container = $(e.container), t.container.addClass(e.search ? o + " search" : o), this.isDisabled = e.disabled, e.disabled && t.container.addClass("disabled"), t.opt = e, t.container.html(template.compile(i)(e)).find(".jsDropdownList").hide(), t.bt = t.container.find(".jsDropdownBt"), t.dropdown = t.container.find(".jsDropdownList"), $.each(e.data, function(e, n) {
				$.data(t.dropdown.find(".jsDropdownItem")[e], "value", n.value), $.data(t.dropdown.find(".jsDropdownItem")[e], "name", n.name), $.data(t.dropdown.find(".jsDropdownItem")[e], "item", n);
			}), typeof e.index != "undefined" && e.data.length !== 0 && (t.bt.find(".jsBtLabel").html(e.data[e.index].name || e.label), t.value = e.data[e.index].value), t.bt.on("click", function() {
				return a(), e.disabled || (t.dropdown.show(), t.container.addClass("open")), !1;
			}), e.search && t.bt.find(".jsBtLabel").on("keyup", function(e) {
				if (t.disabled) return;
				var n = $(this);
				if (e.keyCode == 13) t.value ? (n.html(n.data("name")).removeClass("error"), t.dropdown.hide()) : n.find("div").remove();
				else {
					var r = n.html().trim(),
						i = [];
					t.value = null, t.dropdown.show().find(".jsDropdownItem").each(function() {
						var e = $(this);
						e.hasClass("js_empty") || (e.data("name").indexOf(r) > -1 ? (e.parent().show(), i.push({
							name: e.data("name"),
							value: e.data("value")
						})) : e.parent().hide());
					}), i.length == 0 ? t.dropdown.find(".js_empty").length == 0 && t.dropdown.append('<li class="jsDropdownItem js_empty empty">未找到"' + r + '"</li>') : (t.dropdown.find(".js_empty").remove(), i.length == 1 && (i[0].name == r ? n.removeClass("error") : n.data("name", i[0].name), t.value = i[0].value));
				}
			}).on("blur", function() {
				if (t.disabled) return;
				var n = $(this);
				t.value ? $(this).html() != $(this).data("name") && (n.addClass("error"), t.value = null) : n.html() != "" ? n.addClass("error") : (n.html(e.label).removeClass("error"), t.value = null);
			}).on("focus", function() {
				if (t.disabled) return;
				var n = $(this),
					r = $(this).html().trim();
				r == e.label && n.html("").removeClass("error"), r == "" && n.removeClass("error"), t.dropdown.show(), t.container.addClass("open");
			}), $(document).on("click", a), t.dropdown.on("click", ".jsDropdownItem", function(n) {
				var r = $(this).data("value"),
					i = $(this).data("name"),
					s = $(this).data("index");
				if (!t.value || t.value && t.value != r) {
					t.value = r, t.name = i;
					if (e.callback && typeof e.callback == "function") {
						var o = e.callback(r, i, s, $(this).data("item")) || i;
						e.search ? t.bt.find(".jsBtLabel").html(o).data("name", o).removeClass("error") : t.bt.find(".jsBtLabel").html(o);
					}
				}
				t.dropdown.hide();
			});
		}

		function a() {
			$(".jsDropdownList").hide(), $(".dropdown_menu").each(function() {
				!$(this).hasClass("dropdown_checkbox") && $(this).removeClass("open");
			});
		}
		return u.prototype = {
			selected: function(e, t) {
				var n = this;
				if (typeof e == "number") {
					if (this.opt.data && this.opt.data[e]) {
						var r = this.opt.data[e].name,
							i = this.opt.data[e].value;
						t != 0 && this.dropdown.find(".jsDropdownItem:eq(" + e + ")").trigger("click", i), this.bt.find(".jsBtLabel").html(r);
					}
				} else $.each(this.opt.data, function(r, s) {
					if (e == s.value || e == s.name) return t != 0 && n.dropdown.find(".jsDropdownItem:eq(" + r + ")").trigger("click", i), n.bt.find(".jsBtLabel").html(s.name), !1;
				});
				return this;
			},
			reset: function() {
				return this.bt.find(".jsBtLabel").html(this.opt.label), this.value = null, this;
			},
			hidegreater: function(e) {
				var t = this;
				return typeof e == "number" && t.opt.data && t.opt.data[e] && (t.dropdown.find(".jsDropdownItem").show(), t.dropdown.find(".jsDropdownItem:gt(" + e + ")").hide()), this;
			},
			destroy: function() {
				return this.isDisabled && this.container.removeClass("disabled"), this.container.children().remove(), this.container.off(), this;
			},
			enable: function() {
				return this.opt.disabled = !1, this.container.removeClass("disabled"), this.opt.search && this.bt.find(".jsBtLabel").attr("contenteditable", !0), this;
			},
			disable: function() {
				return this.opt.disabled = !0, this.container.addClass("disabled"), this.opt.search && this.bt.find(".jsBtLabel").attr("contenteditable", !1), this;
			}
		}, u;
	} catch (f) {
		wx.jslog({
			src: "biz_web/ui/dropdown.js"
		}, f);
	}
});