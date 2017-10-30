define("tpl/media/dialog/image_list.html.js", [], function() {
	return '{if file_item.length == 0}\n<div class="empty_tips">该分组暂时没有图片素材</div>\n{else}\n{each file_item as item}\n<li class="img_item js_imageitem" data-id="{item.file_id}" data-url="{item.cdn_url}" data-format="{item.img_format}">\n    <label class="frm_checkbox_label{if item.selected} selected{/if} img_item_bd">\n        {if scene == \'cdn\' && item.cdn_url}\n        <img src="{item.cdn_url}" alt="{item.name}" class="pic">\n        {else}\n        <img src="{item.img_url}" alt="{item.name}" class="pic">\n        {/if}\n        <span class="lbl_content">{item.name}</span>\n        <div class="selected_mask">\n            <div class="selected_mask_inner"></div>\n            <div class="selected_mask_icon"></div>\n        </div>\n    </label>\n</li>\n{/each}\n{/if}\n';
});
define("tpl/media/dialog/image_layout.html.js", [], function() {
	return '<div class="img_pick_panel inner_container_box side_l cell_layout">\n    <div class="inner_side">\n        <div class="group_list">\n            <div class="inner_menu_box">\n                <dl class="inner_menu js_group"></dl>\n            </div>                    \n        </div>\n    </div>\n    <div class="inner_main">\n        <div class="img_pick_area">\n            <div class="sub_title_bar in_dialog">\n                <div class="upload_box r align_right">\n                    <span class="upload_area"><a id="js_imageupload" class="btn btn_primary js_imageupload" data-groupid="">本地上传</a></span>\n                </div>\n                {if desc}\n                <div class="bubble_tips bubble_right warn r">\n                    <div class="bubble_tips_inner">{desc}</div> \n                    <i class="bubble_tips_arrow out"></i>\n                    <i class="bubble_tips_arrow in"></i>\n                </div>\n                {/if}\n            </div>\n            <div>\n                <div class="img_pick">\n                    <i class="icon_loading_small white js_loading"></i>\n                    <ul class="group js_list img_list"></ul>\n                </div>\n                <div class="js_pagebar"></div>\n            </div>\n        </div>\n    </div>\n    <p class="dialog_ft_desc">已选<span class="js_selected">0</span>个，可选{maxSelect}个</p>\n</div>\n';
});
define("tpl/pagebar.html.js", [], function(e, t, n) {
	return '<div class="pagination">\n    <span class="page_nav_area">\n        <a href="javascript:void(0);" class="btn page_first">{firstButtonText}</a>\n        <a href="javascript:void(0);" class="btn page_prev"><i class="arrow"></i></a>\n        {if isSimple}\n            <span class="page_num">\n                <label>{initShowPage}</label>\n                <span class="num_gap">/</span>\n                <label>{endPage}</label>\n            </span>\n        {else}\n            {each startRange as pageIndex index}\n            <a href="javascript:void(0);" class="btn page_nav">{pageIndex}</a>\n            {/each}\n            <span class="gap_prev">...</span>\n            {each midRange as pageIndex index}\n            <a href="javascript:void(0);" class="btn page_nav js_mid">{pageIndex}</a>\n            {/each}\n            <span class="gap_next">...</span>\n            {each endRange as pageIndex index}\n            <a href="javascript:void(0);" class="btn page_nav">{pageIndex}</a>\n            {/each}\n        {/if}\n        <a href="javascript:void(0);" class="btn page_next"><i class="arrow"></i></a>\n        <a href="javascript:void(0);" class="btn page_last">{lastButtonText}</a>            \n    </span>\n    {if (endPage>1)}\n    <span class="goto_area">\n        <input type="text">\n        <a href="javascript:void(0);" class="btn page_go">跳转</a>\n    </span>\n    {/if}\n</div>\n';
});
define("tpl/richEditor/emotion.html.js", [], function() {
	return '<span class="hook">\n	<span class="hook_dec hook_top"></span>\n	<span class="hook_dec hook_btm"></span>\n</span>\n<ul class="emotions" onselectstart="return false;"> \n    {each edata as e index}\n        <li class="emotions_item">\n            <i\n                class="js_emotion_i" \n                data-gifurl=\'{e.gifurl}\' \n                data-title=\'{e.title}\' \n                style=\'{e.bp}\'>\n            </i>\n        </li>\n    {/each}\n</ul>\n<span class="emotions_preview js_emotionPreviewArea"></span>\n';
});
define("common/wx/richEditor/editorRange.js", [], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = function() {
				return document.selection ? document.selection : (window.getSelection || document.getSelection)();
			},
			s = function(e, t, n) {
				if (!n && e === t) return !1;
				if (e.compareDocumentPosition) {
					var r = e.compareDocumentPosition(t);
					if (r == 20 || r == 0) return !0;
				} else if (e.contains(t)) return !0;
				return !1;
			},
			o = function(e, t) {
				var n = t.commonAncestorContainer || t.parentElement && t.parentElement() || null;
				return n ? s(e, n, !0) : !1;
			},
			u = function(e) {
				var t = i();
				if (!t) return null;
				var n = t.getRangeAt ? t.rangeCount ? t.getRangeAt(0) : null : t.createRange();
				return n ? e ? o(e, n) ? n : null : n : null;
			},
			a = function(e) {
				return e.parentElement ? e.parentElement() : e.commonAncestorContainer;
			};
		n.exports = {
			getSelection: i,
			getRange: u,
			containsRange: o,
			parentContainer: a
		};
	} catch (f) {
		wx.jslog({
			src: "common/wx/richEditor/editorRange.js"
		}, f);
	}
});
define("tpl/uploader.html.js", [], function() {
	return '<li id="uploadItem{id}" data-status="{className}" class="upload_file">\n    <strong class="upload_file_name">{fileName}</strong>\n    <span class="upload_file_size">({size})</span>\n    <div class="progress_bar"><div class="progress_bar_thumb" style="width:0%"></div></div>\n    <a href="javascript:;" data-id="{id}" class="upload_file_cancel js_cancel">取消</a>\n</li>\n';
});
define("tpl/media/appmsg.html.js", [], function() {
	return '<div class="appmsg {if isMul}multi{/if}" data-id="{id}" data-fileid="{file_id}">\n    <div class="appmsg_content">\n        {if isMul}\n            <div class="appmsg_info">\n                <em class="appmsg_date">{time}</em>\n            </div>\n            <div class="cover_appmsg_item">\n                <h4 class="appmsg_title js_title"><a href="{first.content_url}" target="_blank">{first.title}</a></h4>\n                <div class="appmsg_thumb_wrp"><img src="{first.cover}" alt="" class="appmsg_thumb"></div>\n            </div>\n            {each list as item}\n            <div class="appmsg_item">\n                <img src="{item.cover}" alt="" class="appmsg_thumb">\n                <h4 class="appmsg_title js_title"><a href="{item.content_url}" target="_blank">{item.title}</a></h4>\n            </div>\n            {/each}\n        {else}\n            <h4 class="appmsg_title js_title"><a href="{first.content_url}" target="_blank">{first.title}</a></h4>\n            <div class="appmsg_info">\n                <em class="appmsg_date">{time}</em>\n            </div>\n            <div class="appmsg_thumb_wrp"><img src="{first.cover}" alt="" class="appmsg_thumb" /></div>\n            <p class="appmsg_desc">{first.digest}</p>\n        {/if}\n    </div>\n    {if showEdit}\n    <div class="appmsg_opr">\n        <ul>\n            <li class="appmsg_opr_item grid_item size1of2">\n            <a class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&lang=zh_CN&token={token}&type={type}&appmsgid={id}&isMul=1" data-tooltip="编辑">&nbsp;<i class="icon18_common edit_gray">编辑</i></a>\n            </li>\n            <li class="appmsg_opr_item grid_item size1of2 no_extra">\n                <a class="js_del no_extra js_tooltip" data-id="{id}" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a>\n            </li>\n        </ul>\n    </div>\n    {/if}\n    {if showMask}\n    <div class="appmsg_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n    {/if}\n</div>\n';
});
define("tpl/tooltip.html.js", [], function(e, t, n) {
	return '<div class="tooltip">\n    <div class="tooltip_inner">{content}</div>\n    <i class="tooltip_arrow"></i>\n</div>\n';
});
define("tpl/media/videomsg.html.js", [], function() {
	return '<div id="wxVideoBox{id}" class="richvideo Js_videomsg">\n    <div class="richvideo_content" style="z-index: 0">\n        <h4 class="title">{title}</h4>\n        <div class="video_info">\n            <em class="time">{time}</em>\n            <!--#0001#-->\n            <em class="res">{from}</em>\n            <!--%0001%-->\n        </div>\n        <div class="video_wrp Js_videoContent">\n            <div id="wxVideoPlayer{id}" class="wxVideoPlayContent video_player">\n                <video id="wxVideo{id}" class="video-js vjs-default-skin"  \n                    preload="auto" controls="controls" data-src="{video_url}"></video>\n            </div>\n            {if for_network}\n            <div class="wxNetworkVideo video_shot" data-contenturl="{content_url}">\n            {else}\n            <div class="{if !for_transfer}wxVideoScreenshot {/if}video_shot">\n            {/if}\n                <img src="/cgi-bin/getimgdata?token={token}&msgid={app_id}&mode=large&source=file&fileId={file_id}"/>\n                <!-- <i class="icon_video"></i> -->\n                <!-- <span class="video_duration"><em>{play_length}"</em></span> -->\n                {if for_transfer}\n                <div class="loading_tips" {if hide_transfer}style="display:none"{/if}>\n                    <i class="icon32_loading dark"></i>\n                    <p>转码中</p>\n                </div>\n                {/if}\n            </div>\n        </div>\n        <div class="video_desc" data-digest="{digest}">{digest}</div>\n    </div>\n    {if for_operation}\n    <div class="richvideo_opr">\n        <ul class="grid_line">\n            {if for_network}\n            <li class="richvideo_opr_item grid_item size1of2">\n                <a class="js_edit js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of2 no_extra">\n                <a class="js_del js_tooltip" data-id="{id}" href="javascript:void(0);" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {else}\n            <li class="richvideo_opr_item grid_item size1of3">\n                <a class="js_edit js_tooltip" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of3">\n                <a {if for_transfer}href="javascript:;" class="js_tooltip js_download"{else}href="{video_download_url}" class="js_tooltip"{/if} data-tooltip="下载">\n                    <i class="icon18_common download_gray">下载</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of3 no_extra">\n                <a class="js_del js_tooltip" data-id="{app_id}" href="javascript:void(0);" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n            {/if}\n        </ul>\n    </div>\n    {/if}\n    {if for_selection}\n    <div class="richvideo_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n    <div class="richvideo_tips">\n        <i class="icon_richvideo_error"></i>\n        <p>该素材没有标题，<a href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}">马上编辑</a></p>\n    </div>\n    {/if}\n    {if for_notitle}\n    <div class="richvideo_mask"></div>\n    <div class="richvideo_tips">\n        <i class="icon_richvideo_error"></i>\n        <p>该素材没有标题，<a href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&lang=zh_CN&token={token}&type=15&appmsgid={app_id}">马上编辑</a></p>\n    </div>\n    {/if}\n</div>\n\n\n';
});
define("tpl/media/wxvideo.html.js", [], function() {
	return '<div id="wxVideoBox{id}" class="richvideo smallvideo Js_videomsg">\n	<div class="richvideo_content" style="z-index: 0">\n		<h4 class="title">{name}</h4>\n        <div class="video_wrp Js_videoContent">\n            <div id="wxVideoPlayer{id}" class="wxVideoPlayContent video_player">\n                <video id="wxVideo{id}" class="video-js vjs-default-skin"  \n                    preload="auto" controls="controls" data-src="{video_url}"></video>\n            </div>\n			<div class="wxVideoScreenshot video_shot">\n                {if video_thumb_cdn_url}\n                <img src="{video_thumb_cdn_url}">\n                {else}\n				<img src="/cgi-bin/getimgdata?token={token}&msgid={id}&mode=small&source=file&fileId={file_id}">\n                {/if}\n				<div class="video_mask">\n					<span class="ic_play"></span>\n				</div>\n			</div>\n        </div>\n	</div>\n    {if for_operation}\n    <div class="richvideo_opr">\n        <ul class="grid_line">\n            <li class="richvideo_opr_item grid_item size1of2">\n                <a class="js_popedit js_tooltip" data-id="{id}" data-name="{name}" href="javascript:void(0);" data-tooltip="编辑">\n                    <i class="icon18_common edit_gray">编辑</i>\n                </a>\n            </li>\n            <li class="richvideo_opr_item grid_item size1of2 no_extra">\n                <a class="js_del js_tooltip" data-id="{id}" data-type="sv" href="javascript:void(0);" data-tooltip="删除">\n                    <i class="icon18_common del_gray">删除</i>\n                </a>\n            </li>\n        </ul>\n    </div>\n    {/if}\n    {if for_selection}\n    <div class="richvideo_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n    {/if}\n</div>\n\n\n';
});
define("tpl/media/simple_videomsg.html.js", [], function() {
	return '<!--群发功能-已发送页面视频模板-->\n<div class="appmsgSendedItem simple_videomsg" data-id="{id}" data-src="{video_url}">\n    {if for_network}\n    <a href="{content_url}" class="title_wrp" data-contenturl="{content_url}" target="_blank">\n    {else}\n    <a href="javascript:;" class="title_wrp js_video">\n    {/if}\n        <img class="icon icon_lh" src="/cgi-bin/getimgdata?token={token}&msgid={app_id}&mode=large&source=file&fileId={file_id}"/>\n        <span class="title">[视频]{title}</span>\n    </a>\n    <p class="desc">{if for_transfer}{if !hide_transfer}转码中{/if}{/if} {digest}</p>\n</div>\n';
});
define("tpl/media/video.html.js", [], function() {
	return '<div id="wxVideoBox{id}" class="mediaBox videoBox{if type == 62} smallvideo_box{/if}">\n	<div class="mediaContent">\n		<div class="wxVideoPlayContent">\n            <div class="wxVideoBoxAction{id}">\n                <a id="wxVideoBoxFold{id}" class="video_switch"><i class="icon14_common switch_gray"></i>收起</a>\n			</div>\n            <div id="wxVideoPlayer{id}" class="wxVideoPlayer">\n                <video id="wxVideo{id}" class="video-js vjs-default-skin" width="260" height="195" preload="auto"  loop controls="controls" src="{src}" poster="/cgi-bin/getimgdata?token={token}&msgid={id}&mode=small&source={source}&fileId={file_id}"></video>\n            </div>\n		</div>\n        <div class="wxVideoScreenshot" data-vid="{id}" data-fid="{fileid}" data-source="{source}">\n            {if video_thumb_url}\n            <img class="wxImg" src="{video_thumb_url}">\n            {else}\n            <img class="wxImg" src="/cgi-bin/getimgdata?token={token}&msgid={id}&mode=small&source={source}&fileId={file_id}" alt="" title=\'点击播放视频\' />\n            {/if}\n			<span class="iconVideo" title=\'点击播放视频\'></span>\n            <div class="videoDuration"><em>{play_length}"</em></div>\n		</div>\n    </div>\n</div>\n';
});
define("biz_web/lib/swfobject.js", [], function(e, t, n) {
	try {
		var r = +(new Date),
			i = function() {
				function e() {
					if (U) return;
					try {
						var e = M.getElementsByTagName("body")[0].appendChild(g("span"));
						e.parentNode.removeChild(e);
					} catch (t) {
						return;
					}
					U = !0;
					var n = P.length;
					for (var r = 0; r < n; r++) P[r]();
				}

				function t(e) {
					U ? e() : P[P.length] = e;
				}

				function n(e) {
					if (typeof O.addEventListener != x) O.addEventListener("load", e, !1);
					else if (typeof M.addEventListener != x) M.addEventListener("load", e, !1);
					else if (typeof O.attachEvent != x) y(O, "onload", e);
					else if (typeof O.onload == "function") {
						var t = O.onload;
						O.onload = function() {
							t(), e();
						};
					} else O.onload = e;
				}

				function r() {
					D ? s() : o();
				}

				function s() {
					var e = M.getElementsByTagName("body")[0],
						t = g(T);
					t.setAttribute("type", k);
					var n = e.appendChild(t);
					if (n) {
						var r = 0;
						(function() {
							if (typeof n.GetVariable != x) {
								var i = n.GetVariable("$version");
								i && (i = i.split(" ")[1].split(","), $.pv = [parseInt(i[0], 10), parseInt(i[1], 10), parseInt(i[2], 10)]);
							} else if (r < 10) {
								r++, setTimeout(arguments.callee, 10);
								return;
							}
							e.removeChild(t), n = null, o();
						})();
					} else o();
				}

				function o() {
					var e = H.length;
					if (e > 0)
						for (var t = 0; t < e; t++) {
							var n = H[t].id,
								r = H[t].callbackFn,
								i = {
									success: !1,
									id: n
								};
							if ($.pv[0] > 0) {
								var s = m(n);
								if (s)
									if (b(H[t].swfVersion) && !($.wk && $.wk < 312)) E(n, !0), r && (i.success = !0, i.ref = u(n), r(i));
									else if (H[t].expressInstall && a()) {
									var o = {};
									o.data = H[t].expressInstall, o.width = s.getAttribute("width") || "0", o.height = s.getAttribute("height") || "0", s.getAttribute("class") && (o.styleclass = s.getAttribute("class")), s.getAttribute("align") && (o.align = s.getAttribute("align"));
									var c = {},
										h = s.getElementsByTagName("param"),
										p = h.length;
									for (var d = 0; d < p; d++) h[d].getAttribute("name").toLowerCase() != "movie" && (c[h[d].getAttribute("name")] = h[d].getAttribute("value"));
									f(o, c, n, r);
								} else l(s), r && r(i);
							} else {
								E(n, !0);
								if (r) {
									var v = u(n);
									v && typeof v.SetVariable != x && (i.success = !0, i.ref = v), r(i);
								}
							}
						}
				}

				function u(e) {
					var t = null,
						n = m(e);
					if (n && n.nodeName == "OBJECT")
						if (typeof n.SetVariable != x) t = n;
						else {
							var r = n.getElementsByTagName(T)[0];
							r && (t = r);
						}
					return t;
				}

				function a() {
					return !z && b("6.0.65") && ($.win || $.mac) && !($.wk && $.wk < 312);
				}

				function f(e, t, n, r) {
					z = !0, q = r || null, R = {
						success: !1,
						id: n
					};
					var i = m(n);
					if (i) {
						i.nodeName == "OBJECT" ? (F = c(i), I = null) : (F = i, I = n), e.id = L;
						if (typeof e.width == x || !/%$/.test(e.width) && parseInt(e.width, 10) < 310) e.width = "310";
						if (typeof e.height == x || !/%$/.test(e.height) && parseInt(e.height, 10) < 137) e.height = "137";
						M.title = M.title.slice(0, 47) + " - Flash Player Installation";
						var s = $.ie && $.win ? "ActiveX" : "PlugIn",
							o = "MMredirectURL=" + O.location.toString().replace(/&/g, "%26") + "&MMplayerType=" + s + "&MMdoctitle=" + M.title;
						typeof t.flashvars != x ? t.flashvars += "&" + o : t.flashvars = o;
						if ($.ie && $.win && i.readyState != 4) {
							var u = g("div");
							n += "SWFObjectNew", u.setAttribute("id", n), i.parentNode.insertBefore(u, i), i.style.display = "none",
								function() {
									i.readyState == 4 ? i.parentNode.removeChild(i) : setTimeout(arguments.callee, 10);
								}();
						}
						h(e, t, n);
					}
				}

				function l(e) {
					if ($.ie && $.win && e.readyState != 4) {
						var t = g("div");
						e.parentNode.insertBefore(t, e), t.parentNode.replaceChild(c(e), t), e.style.display = "none",
							function() {
								e.readyState == 4 ? e.parentNode.removeChild(e) : setTimeout(arguments.callee, 10);
							}();
					} else e.parentNode.replaceChild(c(e), e);
				}

				function c(e) {
					var t = g("div");
					if ($.win && $.ie) t.innerHTML = e.innerHTML;
					else {
						var n = e.getElementsByTagName(T)[0];
						if (n) {
							var r = n.childNodes;
							if (r) {
								var i = r.length;
								for (var s = 0; s < i; s++)(r[s].nodeType != 1 || r[s].nodeName != "PARAM") && r[s].nodeType != 8 && t.appendChild(r[s].cloneNode(!0));
							}
						}
					}
					return t;
				}

				function h(e, t, n) {
					var r, i = m(n);
					if ($.wk && $.wk < 312) return r;
					if (i) {
						typeof e.id == x && (e.id = n);
						if ($.ie && $.win) {
							var s = "";
							for (var o in e) e[o] != Object.prototype[o] && (o.toLowerCase() == "data" ? t.movie = e[o] : o.toLowerCase() == "styleclass" ? s += ' class="' + e[o] + '"' : o.toLowerCase() != "classid" && (s += " " + o + '="' + e[o] + '"'));
							var u = "";
							for (var a in t) t[a] != Object.prototype[a] && (u += '<param name="' + a + '" value="' + t[a] + '" />');
							i.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + s + ">" + u + "</object>", B[B.length] = e.id, r = m(e.id);
						} else {
							var f = g(T);
							f.setAttribute("type", k);
							for (var l in e) e[l] != Object.prototype[l] && (l.toLowerCase() == "styleclass" ? f.setAttribute("class", e[l]) : l.toLowerCase() != "classid" && f.setAttribute(l, e[l]));
							for (var c in t) t[c] != Object.prototype[c] && c.toLowerCase() != "movie" && p(f, c, t[c]);
							i.parentNode.replaceChild(f, i), r = f;
						}
					}
					return r;
				}

				function p(e, t, n) {
					var r = g("param");
					r.setAttribute("name", t), r.setAttribute("value", n), e.appendChild(r);
				}

				function d(e) {
					var t = m(e);
					t && t.nodeName == "OBJECT" && ($.ie && $.win ? (t.style.display = "none", function() {
						t.readyState == 4 ? v(e) : setTimeout(arguments.callee, 10);
					}()) : t.parentNode.removeChild(t));
				}

				function v(e) {
					var t = m(e);
					if (t) {
						for (var n in t) typeof t[n] == "function" && (t[n] = null);
						t.parentNode.removeChild(t);
					}
				}

				function m(e) {
					var t = null;
					try {
						t = M.getElementById(e);
					} catch (n) {}
					return t;
				}

				function g(e) {
					return M.createElement(e);
				}

				function y(e, t, n) {
					e.attachEvent(t, n), j[j.length] = [e, t, n];
				}

				function b(e) {
					var t = $.pv,
						n = e.split(".");
					return n[0] = parseInt(n[0], 10), n[1] = parseInt(n[1], 10) || 0, n[2] = parseInt(n[2], 10) || 0, t[0] > n[0] || t[0] == n[0] && t[1] > n[1] || t[0] == n[0] && t[1] == n[1] && t[2] >= n[2] ? !0 : !1;
				}

				function w(e, t, n, r) {
					if ($.ie && $.mac) return;
					var i = M.getElementsByTagName("head")[0];
					if (!i) return;
					var s = n && typeof n == "string" ? n : "screen";
					r && (W = null, X = null);
					if (!W || X != s) {
						var o = g("style");
						o.setAttribute("type", "text/css"), o.setAttribute("media", s), W = i.appendChild(o), $.ie && $.win && typeof M.styleSheets != x && M.styleSheets.length > 0 && (W = M.styleSheets[M.styleSheets.length - 1]), X = s;
					}
					$.ie && $.win ? W && typeof W.addRule == T && W.addRule(e, t) : W && typeof M.createTextNode != x && W.appendChild(M.createTextNode(e + " {" + t + "}"));
				}

				function E(e, t) {
					if (!V) return;
					var n = t ? "visible" : "hidden";
					U && m(e) ? m(e).style.visibility = n : w("#" + e, "visibility:" + n);
				}

				function S(e) {
					var t = /[\\\"<>\.;]/,
						n = t.exec(e) != null;
					return n && typeof encodeURIComponent != x ? encodeURIComponent(e) : e;
				}
				var x = "undefined",
					T = "object",
					N = "Shockwave Flash",
					C = "ShockwaveFlash.ShockwaveFlash",
					k = "application/x-shockwave-flash",
					L = "SWFObjectExprInst",
					A = "onreadystatechange",
					O = window,
					M = document,
					_ = navigator,
					D = !1,
					P = [r],
					H = [],
					B = [],
					j = [],
					F, I, q, R, U = !1,
					z = !1,
					W, X, V = !0,
					$ = function() {
						var e = typeof M.getElementById != x && typeof M.getElementsByTagName != x && typeof M.createElement != x,
							t = _.userAgent.toLowerCase(),
							n = _.platform.toLowerCase(),
							r = n ? /win/.test(n) : /win/.test(t),
							i = n ? /mac/.test(n) : /mac/.test(t),
							s = /webkit/.test(t) ? parseFloat(t.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : !1,
							o = !1,
							u = [0, 0, 0],
							a = null;
						if (typeof _.plugins != x && typeof _.plugins[N] == T) a = _.plugins[N].description, a && (typeof _.mimeTypes == x || !_.mimeTypes[k] || !!_.mimeTypes[k].enabledPlugin) && (D = !0, o = !1, a = a.replace(/^.*\s+(\S+\s+\S+$)/, "$1"), u[0] = parseInt(a.replace(/^(.*)\..*$/, "$1"), 10), u[1] = parseInt(a.replace(/^.*\.(.*)\s.*$/, "$1"), 10), u[2] = /[a-zA-Z]/.test(a) ? parseInt(a.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0);
						else if (typeof O.ActiveXObject != x) try {
							var f = new ActiveXObject(C);
							f && (a = f.GetVariable("$version"), a && (o = !0, a = a.split(" ")[1].split(","), u = [parseInt(a[0], 10), parseInt(a[1], 10), parseInt(a[2], 10)]));
						} catch (l) {}
						return {
							w3: e,
							pv: u,
							wk: s,
							ie: o,
							win: r,
							mac: i
						};
					}(),
					J = function() {
						if (!$.w3) return;
						(typeof M.readyState != x && M.readyState == "complete" || typeof M.readyState == x && (M.getElementsByTagName("body")[0] || M.body)) && e(), U || (typeof M.addEventListener != x && M.addEventListener("DOMContentLoaded", e, !1), $.ie && $.win && (M.attachEvent(A, function() {
							M.readyState == "complete" && (M.detachEvent(A, arguments.callee), e());
						}), O == top && function() {
							if (U) return;
							try {
								M.documentElement.doScroll("left");
							} catch (t) {
								setTimeout(arguments.callee, 0);
								return;
							}
							e();
						}()), $.wk && function() {
							if (U) return;
							if (!/loaded|complete/.test(M.readyState)) {
								setTimeout(arguments.callee, 0);
								return;
							}
							e();
						}(), n(e));
					}(),
					K = function() {
						$.ie && $.win && window.attachEvent("onunload", function() {
							var e = j.length;
							for (var t = 0; t < e; t++) j[t][0].detachEvent(j[t][1], j[t][2]);
							var n = B.length;
							for (var r = 0; r < n; r++) d(B[r]);
							for (var s in $) $[s] = null;
							$ = null;
							for (var o in i) i[o] = null;
							i = null;
						});
					}();
				return {
					registerObject: function(e, t, n, r) {
						if ($.w3 && e && t) {
							var i = {};
							i.id = e, i.swfVersion = t, i.expressInstall = n, i.callbackFn = r, H[H.length] = i, E(e, !1);
						} else r && r({
							success: !1,
							id: e
						});
					},
					getObjectById: function(e) {
						if ($.w3) return u(e);
					},
					embedSWF: function(e, n, r, i, s, o, u, l, c, p) {
						var d = {
							success: !1,
							id: n
						};
						$.w3 && !($.wk && $.wk < 312) && e && n && r && i && s ? (E(n, !1), t(function() {
							r += "", i += "";
							var t = {};
							if (c && typeof c === T)
								for (var v in c) t[v] = c[v];
							t.data = e, t.width = r, t.height = i;
							var m = {};
							if (l && typeof l === T)
								for (var g in l) m[g] = l[g];
							if (u && typeof u === T)
								for (var y in u) typeof m.flashvars != x ? m.flashvars += "&" + y + "=" + u[y] : m.flashvars = y + "=" + u[y];
							if (b(s)) {
								var w = h(t, m, n);
								t.id == n && E(n, !0), d.success = !0, d.ref = w;
							} else {
								if (o && a()) {
									t.data = o, f(t, m, n, p);
									return;
								}
								E(n, !0);
							}
							p && p(d);
						})) : p && p(d);
					},
					switchOffAutoHideShow: function() {
						V = !1;
					},
					ua: $,
					getFlashPlayerVersion: function() {
						return {
							major: $.pv[0],
							minor: $.pv[1],
							release: $.pv[2]
						};
					},
					hasFlashPlayerVersion: b,
					createSWF: function(e, t, n) {
						return $.w3 ? h(e, t, n) : undefined;
					},
					showExpressInstall: function(e, t, n, r) {
						$.w3 && a() && f(e, t, n, r);
					},
					removeSWF: function(e) {
						$.w3 && d(e);
					},
					createCSS: function(e, t, n, r) {
						$.w3 && w(e, t, n, r);
					},
					addDomLoadEvent: t,
					addLoadEvent: n,
					getQueryParamValue: function(e) {
						var t = M.location.search || M.location.hash;
						if (t) {
							/\?/.test(t) && (t = t.split("?")[1]);
							if (e == null) return S(t);
							var n = t.split("&");
							for (var r = 0; r < n.length; r++)
								if (n[r].substring(0, n[r].indexOf("=")) == e) return S(n[r].substring(n[r].indexOf("=") + 1));
						}
						return "";
					},
					expressInstallCallback: function() {
						if (z) {
							var e = m(L);
							e && F && (e.parentNode.replaceChild(F, e), I && (E(I, !0), $.ie && $.win && (F.style.display = "block")), q && q(R)), z = !1;
						}
					}
				};
			}();
		return i;
	} catch (s) {
		wx.jslog({
			src: "biz_web/lib/swfobject.js"
		}, s);
	}
});
define("biz_web/lib/video.js", [], function(require, exports, module) {
	try {
		var report_time_begin = +(new Date);
		document.createElement("video"), document.createElement("audio"), document.createElement("track");
		var vjs = function(e, t, n) {
				var r;
				if (typeof e == "string") {
					e.indexOf("#") === 0 && (e = e.slice(1));
					if (vjs.players[e]) return vjs.players[e];
					r = vjs.el(e);
				} else r = e;
				if (!r || !r.nodeName) throw new TypeError("The element or ID supplied is not valid. (videojs)");
				return r.player || new vjs.Player(r, t, n);
			},
			videojs = vjs;
		window.videojs = window.vjs = vjs, vjs.CDN_VERSION = "4.1", vjs.ACCESS_PROTOCOL = "https:" == document.location.protocol ? "https://" : "http://", vjs.options = {
			techOrder: ["html5", "flash"],
			html5: {},
			flash: {},
			width: 300,
			height: 150,
			defaultVolume: 0,
			children: {
				mediaLoader: {},
				posterImage: {},
				textTrackDisplay: {},
				loadingSpinner: {},
				bigPlayButton: {},
				controlBar: {}
			}
		}, vjs.CDN_VERSION !== "GENERATED_CDN_VSN" && (videojs.options.flash.swf = vjs.ACCESS_PROTOCOL + "vjs.zencdn.net/" + vjs.CDN_VERSION + "/video-js.swf"), vjs.players = {}, vjs.CoreObject = vjs.CoreObject = function() {}, vjs.CoreObject.extend = function(e) {
			var t, n;
			e = e || {}, t = e.init || e.init || this.prototype.init || this.prototype.init || function() {}, n = function() {
				t.apply(this, arguments);
			}, n.prototype = vjs.obj.create(this.prototype), n.prototype.constructor = n, n.extend = vjs.CoreObject.extend, n.create = vjs.CoreObject.create;
			for (var r in e) e.hasOwnProperty(r) && (n.prototype[r] = e[r]);
			return n;
		}, vjs.CoreObject.create = function() {
			var e = vjs.obj.create(this.prototype);
			return this.apply(e, arguments), e;
		}, vjs.on = function(e, t, n) {
			var r = vjs.getData(e);
			r.handlers || (r.handlers = {}), r.handlers[t] || (r.handlers[t] = []), n.guid || (n.guid = vjs.guid++), r.handlers[t].push(n), r.dispatcher || (r.disabled = !1, r.dispatcher = function(t) {
				if (r.disabled) return;
				t = vjs.fixEvent(t);
				var n = r.handlers[t.type];
				if (n) {
					var i = n.slice(0);
					for (var s = 0, o = i.length; s < o; s++) {
						if (t.isImmediatePropagationStopped()) break;
						i[s].call(e, t);
					}
				}
			}), r.handlers[t].length == 1 && (document.addEventListener ? e.addEventListener(t, r.dispatcher, !1) : document.attachEvent && e.attachEvent("on" + t, r.dispatcher));
		}, vjs.off = function(e, t, n) {
			if (!vjs.hasData(e)) return;
			var r = vjs.getData(e);
			if (!r.handlers) return;
			var i = function(t) {
				r.handlers[t] = [], vjs.cleanUpEvents(e, t);
			};
			if (!t) {
				for (var s in r.handlers) i(s);
				return;
			}
			var o = r.handlers[t];
			if (!o) return;
			if (!n) {
				i(t);
				return;
			}
			if (n.guid)
				for (var u = 0; u < o.length; u++) o[u].guid === n.guid && o.splice(u--, 1);
			vjs.cleanUpEvents(e, t);
		}, vjs.cleanUpEvents = function(e, t) {
			var n = vjs.getData(e);
			n.handlers[t].length === 0 && (delete n.handlers[t], document.removeEventListener ? e.removeEventListener(t, n.dispatcher, !1) : document.detachEvent && e.detachEvent("on" + t, n.dispatcher)), vjs.isEmpty(n.handlers) && (delete n.handlers, delete n.dispatcher, delete n.disabled), vjs.isEmpty(n) && vjs.removeData(e);
		}, vjs.fixEvent = function(e) {
			function t() {
				return !0;
			}

			function n() {
				return !1;
			}
			if (!e || !e.isPropagationStopped) {
				var r = e || window.event;
				e = {};
				for (var i in r) i !== "layerX" && i !== "layerY" && (e[i] = r[i]);
				e.target || (e.target = e.srcElement || document), e.relatedTarget = e.fromElement === e.target ? e.toElement : e.fromElement, e.preventDefault = function() {
					r.preventDefault && r.preventDefault(), e.returnValue = !1, e.isDefaultPrevented = t;
				}, e.isDefaultPrevented = n, e.stopPropagation = function() {
					r.stopPropagation && r.stopPropagation(), e.cancelBubble = !0, e.isPropagationStopped = t;
				}, e.isPropagationStopped = n, e.stopImmediatePropagation = function() {
					r.stopImmediatePropagation && r.stopImmediatePropagation(), e.isImmediatePropagationStopped = t, e.stopPropagation();
				}, e.isImmediatePropagationStopped = n;
				if (e.clientX != null) {
					var s = document.documentElement,
						o = document.body;
					e.pageX = e.clientX + (s && s.scrollLeft || o && o.scrollLeft || 0) - (s && s.clientLeft || o && o.clientLeft || 0), e.pageY = e.clientY + (s && s.scrollTop || o && o.scrollTop || 0) - (s && s.clientTop || o && o.clientTop || 0);
				}
				e.which = e.charCode || e.keyCode, e.button != null && (e.button = e.button & 1 ? 0 : e.button & 4 ? 1 : e.button & 2 ? 2 : 0);
			}
			return e;
		}, vjs.trigger = function(e, t) {
			var n = vjs.hasData(e) ? vjs.getData(e) : {},
				r = e.parentNode || e.ownerDocument;
			typeof t == "string" && (t = {
				type: t,
				target: e
			}), t = vjs.fixEvent(t), n.dispatcher && n.dispatcher.call(e, t);
			if (r && !t.isPropagationStopped()) vjs.trigger(r, t);
			else if (!r && !t.isDefaultPrevented()) {
				var i = vjs.getData(t.target);
				t.target[t.type] && (i.disabled = !0, typeof t.target[t.type] == "function" && t.target[t.type](), i.disabled = !1);
			}
			return !t.isDefaultPrevented();
		}, vjs.one = function(e, t, n) {
			vjs.on(e, t, function() {
				vjs.off(e, t, arguments.callee), n.apply(this, arguments);
			});
		};
		var hasOwnProp = Object.prototype.hasOwnProperty;
		vjs.createEl = function(e, t) {
				var n = document.createElement(e || "div");
				for (var r in t) hasOwnProp.call(t, r) && (r.indexOf("aria-") !== -1 || r == "role" ? n.setAttribute(r, t[r]) : n[r] = t[r]);
				return n;
			}, vjs.capitalize = function(e) {
				return e.charAt(0).toUpperCase() + e.slice(1);
			}, vjs.obj = {}, vjs.obj.create = Object.create || function(e) {
				function t() {}
				return t.prototype = e, new t;
			}, vjs.obj.each = function(e, t, n) {
				for (var r in e) hasOwnProp.call(e, r) && t.call(n || this, r, e[r]);
			}, vjs.obj.merge = function(e, t) {
				if (!t) return e;
				for (var n in t) hasOwnProp.call(t, n) && (e[n] = t[n]);
				return e;
			}, vjs.obj.deepMerge = function(e, t) {
				var n, r, i, s;
				s = "[object Object]", e = vjs.obj.copy(e);
				for (n in t) hasOwnProp.call(t, n) && (r = e[n], i = t[n], vjs.obj.isPlain(r) && vjs.obj.isPlain(i) ? e[n] = vjs.obj.deepMerge(r, i) : e[n] = t[n]);
				return e;
			}, vjs.obj.copy = function(e) {
				return vjs.obj.merge({}, e);
			}, vjs.obj.isPlain = function(e) {
				return !!e && typeof e == "object" && e.toString() === "[object Object]" && e.constructor === Object;
			}, vjs.bind = function(e, t, n) {
				t.guid || (t.guid = vjs.guid++);
				var r = function() {
					return t.apply(e, arguments);
				};
				return r.guid = n ? n + "_" + t.guid : t.guid, r;
			}, vjs.cache = {}, vjs.guid = 1, vjs.expando = "vdata" + (new Date).getTime(), vjs.getData = function(e) {
				var t = e[vjs.expando];
				return t || (t = e[vjs.expando] = vjs.guid++, vjs.cache[t] = {}), vjs.cache[t];
			}, vjs.hasData = function(e) {
				var t = e[vjs.expando];
				return !!t && !vjs.isEmpty(vjs.cache[t]);
			}, vjs.removeData = function(e) {
				var t = e[vjs.expando];
				if (!t) return;
				delete vjs.cache[t];
				try {
					delete e[vjs.expando];
				} catch (n) {
					e.removeAttribute ? e.removeAttribute(vjs.expando) : e[vjs.expando] = null;
				}
			}, vjs.isEmpty = function(e) {
				for (var t in e)
					if (e[t] !== null) return !1;
				return !0;
			}, vjs.addClass = function(e, t) {
				(" " + e.className + " ").indexOf(" " + t + " ") == -1 && (e.className = e.className === "" ? t : e.className + " " + t);
			}, vjs.removeClass = function(e, t) {
				if (e.className.indexOf(t) == -1) return;
				var n = e.className.split(" ");
				for (var r = n.length - 1; r >= 0; r--) n[r] === t && n.splice(r, 1);
				e.className = n.join(" ");
			}, vjs.TEST_VID = vjs.createEl("video"), vjs.USER_AGENT = navigator.userAgent, vjs.IS_IPHONE = /iPhone/i.test(vjs.USER_AGENT), vjs.IS_IPAD = /iPad/i.test(vjs.USER_AGENT), vjs.IS_IPOD = /iPod/i.test(vjs.USER_AGENT), vjs.IS_IOS = vjs.IS_IPHONE || vjs.IS_IPAD || vjs.IS_IPOD, vjs.IOS_VERSION = function() {
				var e = vjs.USER_AGENT.match(/OS (\d+)_/i);
				if (e && e[1]) return e[1];
			}(), vjs.IS_ANDROID = /Android/i.test(vjs.USER_AGENT), vjs.ANDROID_VERSION = function() {
				var e = vjs.USER_AGENT.match(/Android (\d+)(?:\.(\d+))?(?:\.(\d+))*/i),
					t, n;
				return e ? (t = e[1] && parseFloat(e[1]), n = e[2] && parseFloat(e[2]), t && n ? parseFloat(e[1] + "." + e[2]) : t ? t : null) : null;
			}(), vjs.IS_OLD_ANDROID = vjs.IS_ANDROID && /webkit/i.test(vjs.USER_AGENT) && vjs.ANDROID_VERSION < 2.3, vjs.IS_FIREFOX = /Firefox/i.test(vjs.USER_AGENT), vjs.IS_CHROME = /Chrome/i.test(vjs.USER_AGENT), vjs.getAttributeValues = function(e) {
				var t = {},
					n = ",autoplay,controls,loop,muted,default,";
				if (e && e.attributes && e.attributes.length > 0) {
					var r = e.attributes,
						i, s;
					for (var o = r.length - 1; o >= 0; o--) {
						i = r[o].name, s = r[o].value;
						if (typeof e[i] == "boolean" || n.indexOf("," + i + ",") !== -1) s = s !== null ? !0 : !1;
						t[i] = s;
					}
				}
				return t;
			}, vjs.getComputedDimension = function(e, t) {
				var n = "";
				return document.defaultView && document.defaultView.getComputedStyle ? n = document.defaultView.getComputedStyle(e, "").getPropertyValue(t) : e.currentStyle && (n = e["client" + t.substr(0, 1).toUpperCase() + t.substr(1)] + "px"), n;
			}, vjs.insertFirst = function(e, t) {
				t.firstChild ? t.insertBefore(e, t.firstChild) : t.appendChild(e);
			}, vjs.support = {}, vjs.el = function(e) {
				return e.indexOf("#") === 0 && (e = e.slice(1)), document.getElementById(e);
			}, vjs.formatTime = function(e, t) {
				t = t || e;
				var n = Math.floor(e % 60),
					r = Math.floor(e / 60 % 60),
					i = Math.floor(e / 3600),
					s = Math.floor(t / 60 % 60),
					o = Math.floor(t / 3600);
				return i = i > 0 || o > 0 ? i + ":" : "", r = ((i || s >= 10) && r < 10 ? "0" + r : r) + ":", n = n < 10 ? "0" + n : n, i + r + n;
			}, vjs.blockTextSelection = function() {
				document.body.focus(), document.onselectstart = function() {
					return !1;
				};
			}, vjs.unblockTextSelection = function() {
				document.onselectstart = function() {
					return !0;
				};
			}, vjs.trim = function(e) {
				return e.toString().replace(/^\s+/, "").replace(/\s+$/, "");
			}, vjs.round = function(e, t) {
				return t || (t = 0), Math.round(e * Math.pow(10, t)) / Math.pow(10, t);
			}, vjs.createTimeRange = function(e, t) {
				return {
					length: 1,
					start: function() {
						return e;
					},
					end: function() {
						return t;
					}
				};
			}, vjs.get = function(e, t, n) {
				var r = e.indexOf("file:") === 0 || window.location.href.indexOf("file:") === 0 && e.indexOf("http") === -1;
				typeof XMLHttpRequest == "undefined" && (window.XMLHttpRequest = function() {
					try {
						return new window.ActiveXObject("Msxml2.XMLHTTP.6.0");
					} catch (e) {}
					try {
						return new window.ActiveXObject("Msxml2.XMLHTTP.3.0");
					} catch (t) {}
					try {
						return new window.ActiveXObject("Msxml2.XMLHTTP");
					} catch (n) {}
					throw new Error("This browser does not support XMLHttpRequest.");
				});
				var i = new XMLHttpRequest;
				try {
					i.open("GET", e);
				} catch (s) {
					n(s);
				}
				i.onreadystatechange = function() {
					i.readyState === 4 && (i.status === 200 || r && i.status === 0 ? t(i.responseText) : n && n());
				};
				try {
					i.send();
				} catch (s) {
					n && n(s);
				}
			}, vjs.setLocalStorage = function(e, t) {
				try {
					var n = window.localStorage || !1;
					if (!n) return;
					n[e] = t;
				} catch (r) {
					r.code == 22 || r.code == 1014 ? vjs.log("LocalStorage Full (VideoJS)", r) : r.code == 18 ? vjs.log("LocalStorage not allowed (VideoJS)", r) : vjs.log("LocalStorage Error (VideoJS)", r);
				}
			}, vjs.getAbsoluteURL = function(e) {
				return e.match(/^https?:\/\//) || (e = vjs.createEl("div", {
					innerHTML: '<a href="' + e + '">x</a>'
				}).firstChild.href), e;
			}, vjs.log = function() {
				vjs.log.history = vjs.log.history || [], vjs.log.history.push(arguments), window.console && window.console.log(Array.prototype.slice.call(arguments));
			}, vjs.findPosition = function(e) {
				var t, n, r, i, s, o, u, a, f;
				return e.getBoundingClientRect && e.parentNode && (t = e.getBoundingClientRect()), t ? (n = document.documentElement, r = document.body, i = n.clientLeft || r.clientLeft || 0, s = window.pageXOffset || r.scrollLeft, o = t.left + s - i, u = n.clientTop || r.clientTop || 0, a = window.pageYOffset || r.scrollTop, f = t.top + a - u, {
					left: o,
					top: f
				}) : {
					left: 0,
					top: 0
				};
			}, vjs.Component = vjs.CoreObject.extend({
				init: function(e, t, n) {
					this.player_ = e, this.options_ = vjs.obj.copy(this.options_), t = this.options(t), this.id_ = t.id || (t.el && t.el.id ? t.el.id : e.id() + "_component_" + vjs.guid++), this.name_ = t.name || null, this.el_ = t.el || this.createEl(), this.children_ = [], this.childIndex_ = {}, this.childNameIndex_ = {}, this.initChildren(), this.ready(n);
				}
			}), vjs.Component.prototype.dispose = function() {
				if (this.children_)
					for (var e = this.children_.length - 1; e >= 0; e--) this.children_[e].dispose && this.children_[e].dispose();
				this.children_ = null, this.childIndex_ = null, this.childNameIndex_ = null, this.off(), this.el_.parentNode && this.el_.parentNode.removeChild(this.el_), vjs.removeData(this.el_), this.el_ = null;
			}, vjs.Component.prototype.player_, vjs.Component.prototype.player = function() {
				return this.player_;
			}, vjs.Component.prototype.options_, vjs.Component.prototype.options = function(e) {
				return e === undefined ? this.options_ : this.options_ = vjs.obj.deepMerge(this.options_, e);
			}, vjs.Component.prototype.el_, vjs.Component.prototype.createEl = function(e, t) {
				return vjs.createEl(e, t);
			}, vjs.Component.prototype.el = function() {
				return this.el_;
			}, vjs.Component.prototype.contentEl_, vjs.Component.prototype.contentEl = function() {
				return this.contentEl_ || this.el_;
			}, vjs.Component.prototype.id_, vjs.Component.prototype.id = function() {
				return this.id_;
			}, vjs.Component.prototype.name_, vjs.Component.prototype.name = function() {
				return this.name_;
			}, vjs.Component.prototype.children_, vjs.Component.prototype.children = function() {
				return this.children_;
			}, vjs.Component.prototype.childIndex_, vjs.Component.prototype.getChildById = function(e) {
				return this.childIndex_[e];
			}, vjs.Component.prototype.childNameIndex_, vjs.Component.prototype.getChild = function(e) {
				return this.childNameIndex_[e];
			}, vjs.Component.prototype.addChild = function(e, t) {
				var n, r, i, s;
				return typeof e == "string" ? (i = e, t = t || {}, r = t.componentClass || vjs.capitalize(i), t.name = i, n = new window.videojs[r](this.player_ || this, t)) : n = e, this.children_.push(n), typeof n.id == "function" && (this.childIndex_[n.id()] = n), i = i || n.name && n.name(), i && (this.childNameIndex_[i] = n), typeof n.el == "function" && n.el() && this.contentEl().appendChild(n.el()), n;
			}, vjs.Component.prototype.removeChild = function(e) {
				typeof e == "string" && (e = this.getChild(e));
				if (!e || !this.children_) return;
				var t = !1;
				for (var n = this.children_.length - 1; n >= 0; n--)
					if (this.children_[n] === e) {
						t = !0, this.children_.splice(n, 1);
						break;
					}
				if (!t) return;
				this.childIndex_[e.id] = null, this.childNameIndex_[e.name] = null;
				var r = e.el();
				r && r.parentNode === this.contentEl() && this.contentEl().removeChild(e.el());
			}, vjs.Component.prototype.initChildren = function() {
				var e = this.options_;
				if (e && e.children) {
					var t = this;
					vjs.obj.each(e.children, function(e, n) {
						if (n === !1) return;
						var r = function() {
							t[e] = t.addChild(e, n);
						};
						n.loadEvent || r();
					});
				}
			}, vjs.Component.prototype.buildCSSClass = function() {
				return "";
			}, vjs.Component.prototype.on = function(e, t) {
				return vjs.on(this.el_, e, vjs.bind(this, t)), this;
			}, vjs.Component.prototype.off = function(e, t) {
				return vjs.off(this.el_, e, t), this;
			}, vjs.Component.prototype.one = function(e, t) {
				return vjs.one(this.el_, e, vjs.bind(this, t)), this;
			}, vjs.Component.prototype.trigger = function(e, t) {
				return vjs.trigger(this.el_, e, t), this;
			}, vjs.Component.prototype.isReady_, vjs.Component.prototype.isReadyOnInitFinish_ = !0, vjs.Component.prototype.readyQueue_, vjs.Component.prototype.ready = function(e) {
				return e && (this.isReady_ ? e.call(this) : (this.readyQueue_ === undefined && (this.readyQueue_ = []), this.readyQueue_.push(e))), this;
			}, vjs.Component.prototype.triggerReady = function() {
				this.isReady_ = !0;
				var e = this.readyQueue_;
				if (e && e.length > 0) {
					for (var t = 0, n = e.length; t < n; t++) e[t].call(this);
					this.readyQueue_ = [], this.trigger("ready");
				}
			}, vjs.Component.prototype.addClass = function(e) {
				return vjs.addClass(this.el_, e), this;
			}, vjs.Component.prototype.removeClass = function(e) {
				return vjs.removeClass(this.el_, e), this;
			}, vjs.Component.prototype.show = function() {
				return this.el_.style.display = "block", this;
			}, vjs.Component.prototype.hide = function() {
				return this.el_.style.display = "none", this;
			}, vjs.Component.prototype.fadeIn = function() {
				return this.removeClass("vjs-fade-out"), this.addClass("vjs-fade-in"), this;
			}, vjs.Component.prototype.fadeOut = function() {
				return this.removeClass("vjs-fade-in"), this.addClass("vjs-fade-out"), this;
			}, vjs.Component.prototype.lockShowing = function() {
				return this.addClass("vjs-lock-showing"), this;
			}, vjs.Component.prototype.unlockShowing = function() {
				return this.removeClass("vjs-lock-showing"), this;
			}, vjs.Component.prototype.disable = function() {
				this.hide(), this.show = function() {}, this.fadeIn = function() {};
			}, vjs.Component.prototype.width = function(e, t) {
				return this.dimension("width", e, t);
			}, vjs.Component.prototype.height = function(e, t) {
				return this.dimension("height", e, t);
			}, vjs.Component.prototype.dimensions = function(e, t) {
				return this.width(e, !0).height(t);
			}, vjs.Component.prototype.dimension = function(e, t, n) {
				if (t !== undefined) return ("" + t).indexOf("%") !== -1 || ("" + t).indexOf("px") !== -1 ? this.el_.style[e] = t : t === "auto" ? this.el_.style[e] = "" : this.el_.style[e] = t + "px", n || this.trigger("resize"), this;
				if (!this.el_) return 0;
				var r = this.el_.style[e],
					i = r.indexOf("px");
				return i !== -1 ? parseInt(r.slice(0, i), 10) : parseInt(this.el_["offset" + vjs.capitalize(e)], 10);
			}, vjs.Button = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t);
					var n = !1;
					this.on("touchstart", function() {
						n = !0;
					}), this.on("touchmove", function() {
						n = !1;
					});
					var r = this;
					this.on("touchend", function(e) {
						n && r.onClick(e), e.preventDefault(), e.stopPropagation();
					}), this.on("click", this.onClick), this.on("focus", this.onFocus), this.on("blur", this.onBlur);
				}
			}), vjs.Button.prototype.createEl = function(e, t) {
				return t = vjs.obj.merge({
					className: this.buildCSSClass(),
					innerHTML: '<div class="vjs-control-content"><span class="vjs-control-text">' + (this.buttonText || "Need Text") + "</span></div>",
					role: "button",
					"aria-live": "polite",
					tabIndex: 0
				}, t), vjs.Component.prototype.createEl.call(this, e, t);
			}, vjs.Button.prototype.buildCSSClass = function() {
				return "vjs-control " + vjs.Component.prototype.buildCSSClass.call(this);
			}, vjs.Button.prototype.onClick = function() {}, vjs.Button.prototype.onFocus = function() {
				vjs.on(document, "keyup", vjs.bind(this, this.onKeyPress));
			}, vjs.Button.prototype.onKeyPress = function(e) {
				if (e.which == 32 || e.which == 13) e.preventDefault(), this.onClick();
			}, vjs.Button.prototype.onBlur = function() {
				vjs.off(document, "keyup", vjs.bind(this, this.onKeyPress));
			}, vjs.Slider = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), this.bar = this.getChild(this.options_.barName), this.handle = this.getChild(this.options_.handleName), e.on(this.playerEvent, vjs.bind(this, this.update)), this.on("mousedown", this.onMouseDown), this.on("touchstart", this.onMouseDown), this.on("focus", this.onFocus), this.on("blur", this.onBlur), this.on("click", this.onClick), this.player_.on("controlsvisible", vjs.bind(this, this.update)), e.ready(vjs.bind(this, this.update)), this.boundEvents = {};
				}
			}), vjs.Slider.prototype.createEl = function(e, t) {
				return t = t || {}, t.className = t.className + " vjs-slider", t = vjs.obj.merge({
					role: "slider",
					"aria-valuenow": 0,
					"aria-valuemin": 0,
					"aria-valuemax": 100,
					tabIndex: 0
				}, t), vjs.Component.prototype.createEl.call(this, e, t);
			}, vjs.Slider.prototype.onMouseDown = function(e) {
				e.preventDefault(), vjs.blockTextSelection(), this.boundEvents.move = vjs.bind(this, this.onMouseMove), this.boundEvents.end = vjs.bind(this, this.onMouseUp), vjs.on(document, "mousemove", this.boundEvents.move), vjs.on(document, "mouseup", this.boundEvents.end), vjs.on(document, "touchmove", this.boundEvents.move), vjs.on(document, "touchend", this.boundEvents.end), this.onMouseMove(e);
			}, vjs.Slider.prototype.onMouseUp = function() {
				vjs.unblockTextSelection(), vjs.off(document, "mousemove", this.boundEvents.move, !1), vjs.off(document, "mouseup", this.boundEvents.end, !1), vjs.off(document, "touchmove", this.boundEvents.move, !1), vjs.off(document, "touchend", this.boundEvents.end, !1), this.update();
			}, vjs.Slider.prototype.update = function() {
				if (!this.el_) return;
				var e, t = this.getPercent(),
					n = this.handle,
					r = this.bar;
				isNaN(t) && (t = 0), e = t;
				if (n) {
					var i = this.el_,
						s = i.offsetWidth,
						o = n.el().offsetWidth,
						u = o ? o / s : 0,
						a = 1 - u,
						f = t * a;
					e = f + u / 2, n.el().style.left = vjs.round(f * 100, 2) + "%";
				}
				r.el().style.width = vjs.round(e * 100, 2) + "%";
			}, vjs.Slider.prototype.calculateDistance = function(e) {
				var t, n, r, i, s, o, u, a, f;
				t = this.el_, n = vjs.findPosition(t), s = o = t.offsetWidth, u = this.handle;
				if (this.options_.vertical) {
					i = n.top, e.changedTouches ? f = e.changedTouches[0].pageY : f = e.pageY;
					if (u) {
						var l = u.el().offsetHeight;
						i += l / 2, o -= l;
					}
					return Math.max(0, Math.min(1, (i - f + o) / o));
				}
				r = n.left, e.changedTouches ? a = e.changedTouches[0].pageX : a = e.pageX;
				if (u) {
					var c = u.el().offsetWidth;
					r += c / 2, s -= c;
				}
				return Math.max(0, Math.min(1, (a - r) / s));
			}, vjs.Slider.prototype.onFocus = function() {
				vjs.on(document, "keyup", vjs.bind(this, this.onKeyPress));
			}, vjs.Slider.prototype.onKeyPress = function(e) {
				e.which == 37 ? (e.preventDefault(), this.stepBack()) : e.which == 39 && (e.preventDefault(), this.stepForward());
			}, vjs.Slider.prototype.onBlur = function() {
				vjs.off(document, "keyup", vjs.bind(this, this.onKeyPress));
			}, vjs.Slider.prototype.onClick = function(e) {
				e.stopImmediatePropagation(), e.preventDefault();
			}, vjs.SliderHandle = vjs.Component.extend(), vjs.SliderHandle.prototype.defaultValue = 0, vjs.SliderHandle.prototype.createEl = function(e, t) {
				return t = t || {}, t.className = t.className + " vjs-slider-handle", t = vjs.obj.merge({
					innerHTML: '<span class="vjs-control-text">' + this.defaultValue + "</span>"
				}, t), vjs.Component.prototype.createEl.call(this, "div", t);
			}, vjs.Menu = vjs.Component.extend(), vjs.Menu.prototype.addItem = function(e) {
				this.addChild(e), e.on("click", vjs.bind(this, function() {
					this.unlockShowing();
				}));
			}, vjs.Menu.prototype.createEl = function() {
				var e = this.options().contentElType || "ul";
				this.contentEl_ = vjs.createEl(e, {
					className: "vjs-menu-content"
				});
				var t = vjs.Component.prototype.createEl.call(this, "div", {
					append: this.contentEl_,
					className: "vjs-menu"
				});
				return t.appendChild(this.contentEl_), vjs.on(t, "click", function(e) {
					e.preventDefault(), e.stopImmediatePropagation();
				}), t;
			}, vjs.MenuItem = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t), this.selected(t.selected);
				}
			}), vjs.MenuItem.prototype.createEl = function(e, t) {
				return vjs.Button.prototype.createEl.call(this, "li", vjs.obj.merge({
					className: "vjs-menu-item",
					innerHTML: this.options_.label
				}, t));
			}, vjs.MenuItem.prototype.onClick = function() {
				this.selected(!0);
			}, vjs.MenuItem.prototype.selected = function(e) {
				e ? (this.addClass("vjs-selected"), this.el_.setAttribute("aria-selected", !0)) : (this.removeClass("vjs-selected"), this.el_.setAttribute("aria-selected", !1));
			}, vjs.MenuButton = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t), this.menu = this.createMenu(), this.addChild(this.menu), this.items && this.items.length === 0 && this.hide(), this.on("keyup", this.onKeyPress), this.el_.setAttribute("aria-haspopup", !0), this.el_.setAttribute("role", "button");
				}
			}), vjs.MenuButton.prototype.buttonPressed_ = !1, vjs.MenuButton.prototype.createMenu = function() {
				var e = new vjs.Menu(this.player_);
				this.options().title && e.el().appendChild(vjs.createEl("li", {
					className: "vjs-menu-title",
					innerHTML: vjs.capitalize(this.kind_),
					tabindex: -1
				})), this.items = this.createItems();
				if (this.items)
					for (var t = 0; t < this.items.length; t++) e.addItem(this.items[t]);
				return e;
			}, vjs.MenuButton.prototype.createItems = function() {}, vjs.MenuButton.prototype.buildCSSClass = function() {
				return this.className + " vjs-menu-button " + vjs.Button.prototype.buildCSSClass.call(this);
			}, vjs.MenuButton.prototype.onFocus = function() {}, vjs.MenuButton.prototype.onBlur = function() {}, vjs.MenuButton.prototype.onClick = function() {
				this.one("mouseout", vjs.bind(this, function() {
					this.menu.unlockShowing(), this.el_.blur();
				})), this.buttonPressed_ ? this.unpressButton() : this.pressButton();
			}, vjs.MenuButton.prototype.onKeyPress = function(e) {
				e.preventDefault(), e.which == 32 || e.which == 13 ? this.buttonPressed_ ? this.unpressButton() : this.pressButton() : e.which == 27 && this.buttonPressed_ && this.unpressButton();
			}, vjs.MenuButton.prototype.pressButton = function() {
				this.buttonPressed_ = !0, this.menu.lockShowing(), this.el_.setAttribute("aria-pressed", !0), this.items && this.items.length > 0 && this.items[0].el().focus();
			}, vjs.MenuButton.prototype.unpressButton = function() {
				this.buttonPressed_ = !1, this.menu.unlockShowing(), this.el_.setAttribute("aria-pressed", !1);
			}, vjs.Player = vjs.Component.extend({
				init: function(e, t, n) {
					this.tag = e, t = vjs.obj.merge(this.getTagSettings(e), t), this.cache_ = {}, this.poster_ = t.poster, this.controls_ = t.controls, t.customControlsOnMobile !== !0 && (vjs.IS_IOS || vjs.IS_ANDROID) ? (e.controls = t.controls, this.controls_ = !1) : e.controls = !1, vjs.Component.call(this, this, t, n), this.one("play", function(e) {
						var t = {
								type: "firstplay",
								target: this.el_
							},
							n = vjs.trigger(this.el_, t);
						n || (e.preventDefault(), e.stopPropagation(), e.stopImmediatePropagation());
					}), this.on("ended", this.onEnded), this.on("play", this.onPlay), this.on("firstplay", this.onFirstPlay), this.on("pause", this.onPause), this.on("progress", this.onProgress), this.on("durationchange", this.onDurationChange), this.on("error", this.onError), this.on("fullscreenchange", this.onFullscreenChange), vjs.players[this.id_] = this, t.plugins && vjs.obj.each(t.plugins, function(e, t) {
						this[e](t);
					}, this);
				}
			}), vjs.Player.prototype.options_ = vjs.options, vjs.Player.prototype.dispose = function() {
				vjs.players[this.id_] = null, this.tag && this.tag.player && (this.tag.player = null), this.el_ && this.el_.player && (this.el_.player = null), this.stopTrackingProgress(), this.stopTrackingCurrentTime(), this.tech && this.tech.dispose(), vjs.Component.prototype.dispose.call(this);
			}, vjs.Player.prototype.getTagSettings = function(e) {
				var t = {
					sources: [],
					tracks: []
				};
				vjs.obj.merge(t, vjs.getAttributeValues(e));
				if (e.hasChildNodes()) {
					var n, r, i, s, o;
					n = e.childNodes;
					for (s = 0, o = n.length; s < o; s++) r = n[s], i = r.nodeName.toLowerCase(), i === "source" ? t.sources.push(vjs.getAttributeValues(r)) : i === "track" && t.tracks.push(vjs.getAttributeValues(r));
				}
				return t;
			}, vjs.Player.prototype.createEl = function() {
				var e = this.el_ = vjs.Component.prototype.createEl.call(this, "div"),
					t = this.tag;
				t.removeAttribute("width"), t.removeAttribute("height");
				if (t.hasChildNodes()) {
					var n, r, i, s, o, u;
					n = t.childNodes, r = n.length, u = [];
					while (r--) s = n[r], o = s.nodeName.toLowerCase(), (o === "source" || o === "track") && u.push(s);
					for (i = 0; i < u.length; i++) t.removeChild(u[i]);
				}
				return t.id = t.id || "vjs_video_" + vjs.guid++, e.id = t.id, e.className = t.className, t.id += "_html5_api", t.className = "vjs-tech", t.player = e.player = this, this.addClass("vjs-paused"), this.width(this.options_.width, !0), this.height(this.options_.height, !0), t.parentNode && t.parentNode.insertBefore(e, t), vjs.insertFirst(t, e), e;
			}, vjs.Player.prototype.loadTech = function(e, t) {
				this.tech ? this.unloadTech() : e !== "Html5" && this.tag && (this.el_.removeChild(this.tag), this.tag.player = null, this.tag = null), this.techName = e, this.isReady_ = !1;
				var n = function() {
						this.player_.triggerReady(), this.features.progressEvents || this.player_.manualProgressOn(), this.features.timeupdateEvents || this.player_.manualTimeUpdatesOn();
					},
					r = vjs.obj.merge({
						source: t,
						parentEl: this.el_
					}, this.options_[e.toLowerCase()]);
				t && (t.src == this.cache_.src && this.cache_.currentTime > 0 && (r.startTime = this.cache_.currentTime), this.cache_.src = t.src), this.tech = new window.videojs[e](this, r), this.tech.ready(n);
			}, vjs.Player.prototype.unloadTech = function() {
				this.isReady_ = !1, this.tech.dispose(), this.manualProgress && this.manualProgressOff(), this.manualTimeUpdates && this.manualTimeUpdatesOff(), this.tech = !1;
			}, vjs.Player.prototype.manualProgressOn = function() {
				this.manualProgress = !0, this.trackProgress(), this.tech.one("progress", function() {
					this.features.progressEvents = !0, this.player_.manualProgressOff();
				});
			}, vjs.Player.prototype.manualProgressOff = function() {
				this.manualProgress = !1, this.stopTrackingProgress();
			}, vjs.Player.prototype.trackProgress = function() {
				this.progressInterval = setInterval(vjs.bind(this, function() {
					this.cache_.bufferEnd < this.buffered().end(0) ? this.trigger("progress") : this.bufferedPercent() == 1 && (this.stopTrackingProgress(), this.trigger("progress"));
				}), 500);
			}, vjs.Player.prototype.stopTrackingProgress = function() {
				clearInterval(this.progressInterval);
			}, vjs.Player.prototype.manualTimeUpdatesOn = function() {
				this.manualTimeUpdates = !0, this.on("play", this.trackCurrentTime), this.on("pause", this.stopTrackingCurrentTime), this.tech.one("timeupdate", function() {
					this.features.timeupdateEvents = !0, this.player_.manualTimeUpdatesOff();
				});
			}, vjs.Player.prototype.manualTimeUpdatesOff = function() {
				this.manualTimeUpdates = !1, this.stopTrackingCurrentTime(), this.off("play", this.trackCurrentTime), this.off("pause", this.stopTrackingCurrentTime);
			}, vjs.Player.prototype.trackCurrentTime = function() {
				this.currentTimeInterval && this.stopTrackingCurrentTime(), this.currentTimeInterval = setInterval(vjs.bind(this, function() {
					this.trigger("timeupdate");
				}), 250);
			}, vjs.Player.prototype.stopTrackingCurrentTime = function() {
				clearInterval(this.currentTimeInterval);
			}, vjs.Player.prototype.onEnded = function() {
				this.options_.loop && (this.currentTime(0), this.play());
			}, vjs.Player.prototype.onPlay = function() {
				vjs.removeClass(this.el_, "vjs-paused"), vjs.addClass(this.el_, "vjs-playing");
			}, vjs.Player.prototype.onFirstPlay = function() {
				this.options_.starttime && this.currentTime(this.options_.starttime);
			}, vjs.Player.prototype.onPause = function() {
				vjs.removeClass(this.el_, "vjs-playing"), vjs.addClass(this.el_, "vjs-paused");
			}, vjs.Player.prototype.onProgress = function() {
				this.bufferedPercent() == 1 && this.trigger("loadedalldata");
			}, vjs.Player.prototype.onDurationChange = function() {
				this.duration(this.techGet("duration"));
			}, vjs.Player.prototype.onError = function(e) {
				vjs.log("Video Error", e);
			}, vjs.Player.prototype.onFullscreenChange = function() {
				this.isFullScreen ? this.addClass("vjs-fullscreen") : this.removeClass("vjs-fullscreen");
			}, vjs.Player.prototype.cache_, vjs.Player.prototype.getCache = function() {
				return this.cache_;
			}, vjs.Player.prototype.techCall = function(e, t) {
				if (this.tech && !this.tech.isReady_) this.tech.ready(function() {
					this[e](t);
				});
				else try {
					this.tech[e](t);
				} catch (n) {
					throw vjs.log(n), n;
				}
			}, vjs.Player.prototype.techGet = function(e) {
				if (this.tech.isReady_) try {
					return this.tech[e]();
				} catch (t) {
					throw this.tech[e] === undefined ? vjs.log("Video.js: " + e + " method not defined for " + this.techName + " playback technology.", t) : t.name == "TypeError" ? (vjs.log("Video.js: " + e + " unavailable on " + this.techName + " playback technology element.", t), this.tech.isReady_ = !1) : vjs.log(t), t;
				}
				return;
			}, vjs.Player.prototype.play = function() {
				return this.techCall("play"), this;
			}, vjs.Player.prototype.pause = function() {
				return this.techCall("pause"), this;
			}, vjs.Player.prototype.paused = function() {
				return this.techGet("paused") === !1 ? !1 : !0;
			}, vjs.Player.prototype.currentTime = function(e) {
				return e !== undefined ? (this.cache_.lastSetCurrentTime = e, this.techCall("setCurrentTime", e), this.manualTimeUpdates && this.trigger("timeupdate"), this) : this.cache_.currentTime = this.techGet("currentTime") || 0;
			}, vjs.Player.prototype.duration = function(e) {
				return e !== undefined ? (this.cache_.duration = parseFloat(e), this) : this.cache_.duration;
			}, vjs.Player.prototype.remainingTime = function() {
				return this.duration() - this.currentTime();
			}, vjs.Player.prototype.buffered = function() {
				var e = this.techGet("buffered"),
					t = 0,
					n = this.cache_.bufferEnd = this.cache_.bufferEnd || 0;
				return e && e.length > 0 && e.end(0) !== n && (n = e.end(0), this.cache_.bufferEnd = n), vjs.createTimeRange(t, n);
			}, vjs.Player.prototype.bufferedPercent = function() {
				return this.duration() ? this.buffered().end(0) / this.duration() : 0;
			}, vjs.Player.prototype.volume = function(e) {
				var t;
				return e !== undefined ? (t = Math.max(0, Math.min(1, parseFloat(e))), this.cache_.volume = t, this.techCall("setVolume", t), vjs.setLocalStorage("volume", t), this) : (t = parseFloat(this.techGet("volume")), isNaN(t) ? 1 : t);
			}, vjs.Player.prototype.muted = function(e) {
				return e !== undefined ? (this.techCall("setMuted", e), this) : this.techGet("muted") || !1;
			}, vjs.Player.prototype.supportsFullScreen = function() {
				return this.techGet("supportsFullScreen") || !1;
			}, vjs.Player.prototype.requestFullScreen = function() {
				var e = vjs.support.requestFullScreen;
				return this.isFullScreen = !0, e ? (vjs.on(document, e.eventName, vjs.bind(this, function(t) {
					this.isFullScreen = document[e.isFullScreen], this.isFullScreen === !1 && vjs.off(document, e.eventName, arguments.callee), this.trigger("fullscreenchange");
				})), this.el_[e.requestFn]()) : this.tech.supportsFullScreen() ? this.techCall("enterFullScreen") : (this.enterFullWindow(), this.trigger("fullscreenchange")), this;
			}, vjs.Player.prototype.cancelFullScreen = function() {
				var e = vjs.support.requestFullScreen;
				return this.isFullScreen = !1, e ? document[e.cancelFn]() : this.tech.supportsFullScreen() ? this.techCall("exitFullScreen") : (this.exitFullWindow(), this.trigger("fullscreenchange")), this;
			}, vjs.Player.prototype.enterFullWindow = function() {
				this.isFullWindow = !0, this.docOrigOverflow = document.documentElement.style.overflow, vjs.on(document, "keydown", vjs.bind(this, this.fullWindowOnEscKey)), document.documentElement.style.overflow = "hidden", vjs.addClass(document.body, "vjs-full-window"), this.trigger("enterFullWindow");
			}, vjs.Player.prototype.fullWindowOnEscKey = function(e) {
				e.keyCode === 27 && (this.isFullScreen === !0 ? this.cancelFullScreen() : this.exitFullWindow());
			}, vjs.Player.prototype.exitFullWindow = function() {
				this.isFullWindow = !1, vjs.off(document, "keydown", this.fullWindowOnEscKey), document.documentElement.style.overflow = this.docOrigOverflow, vjs.removeClass(document.body, "vjs-full-window"), this.trigger("exitFullWindow");
			}, vjs.Player.prototype.selectSource = function(e) {
				for (var t = 0, n = this.options_.techOrder; t < n.length; t++) {
					var r = vjs.capitalize(n[t]),
						i = window.videojs[r];
					if (i.isSupported())
						for (var s = 0, o = e; s < o.length; s++) {
							var u = o[s];
							if (i.canPlaySource(u)) return {
								source: u,
								tech: r
							};
						}
				}
				return !1;
			}, vjs.Player.prototype.src = function(e) {
				if (e instanceof Array) {
					var t = this.selectSource(e),
						n;
					t ? (e = t.source, n = t.tech, n == this.techName ? this.src(e) : this.loadTech(n, e)) : this.el_.appendChild(vjs.createEl("p", {
						innerHTML: 'Sorry, no compatible source and playback technology were found for this video. Try using another browser like <a href="http://bit.ly/ccMUEC">Chrome</a> or download the latest <a href="http://adobe.ly/mwfN1">Adobe Flash Player</a>.'
					}));
				} else e instanceof Object ? window.videojs[this.techName].canPlaySource(e) ? this.src(e.src) : this.src([e]) : (this.cache_.src = e, this.isReady_ ? (this.techCall("src", e), this.options_["preload"] == "auto" && this.load(), this.options_.autoplay && this.play()) : this.ready(function() {
					this.src(e);
				}));
				return this;
			}, vjs.Player.prototype.load = function() {
				return this.techCall("load"), this;
			}, vjs.Player.prototype.currentSrc = function() {
				return this.techGet("currentSrc") || this.cache_.src || "";
			}, vjs.Player.prototype.preload = function(e) {
				return e !== undefined ? (this.techCall("setPreload", e), this.options_.preload = e, this) : this.techGet("preload");
			}, vjs.Player.prototype.autoplay = function(e) {
				return e !== undefined ? (this.techCall("setAutoplay", e), this.options_.autoplay = e, this) : this.techGet("autoplay", e);
			}, vjs.Player.prototype.loop = function(e) {
				return e !== undefined ? (this.techCall("setLoop", e), this.options_.loop = e, this) : this.techGet("loop");
			}, vjs.Player.prototype.poster_, vjs.Player.prototype.poster = function(e) {
				return e !== undefined && (this.poster_ = e), this.poster_;
			}, vjs.Player.prototype.controls_, vjs.Player.prototype.controls = function(e) {
				return e !== undefined && this.controls_ !== e && (this.controls_ = !!e, this.trigger("controlschange")), this.controls_;
			}, vjs.Player.prototype.error = function() {
				return this.techGet("error");
			}, vjs.Player.prototype.ended = function() {
				return this.techGet("ended");
			},
			function() {
				var e, t, n;
				n = document.createElement("div"), t = {}, n.cancelFullscreen !== undefined ? (t.requestFn = "requestFullscreen", t.cancelFn = "exitFullscreen", t.eventName = "fullscreenchange", t.isFullScreen = "fullScreen") : (document.mozCancelFullScreen ? (e = "moz", t.isFullScreen = e + "FullScreen") : (e = "webkit", t.isFullScreen = e + "IsFullScreen"), n[e + "RequestFullScreen"] && (t.requestFn = e + "RequestFullScreen", t.cancelFn = e + "CancelFullScreen"), t.eventName = e + "fullscreenchange"), document[t.cancelFn] && (vjs.support.requestFullScreen = t);
			}(), vjs.ControlBar = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.controls() || this.disable(), e.one("play", vjs.bind(this, function() {
						var e, t = vjs.bind(this, this.fadeIn),
							n = vjs.bind(this, this.fadeOut);
						this.fadeIn(), "ontouchstart" in window || (this.player_.on("mouseover", t), this.player_.on("mouseout", n), this.player_.on("pause", vjs.bind(this, this.lockShowing)), this.player_.on("play", vjs.bind(this, this.unlockShowing))), e = !1, this.player_.on("touchstart", function() {
							e = !0;
						}), this.player_.on("touchmove", function() {
							e = !1;
						}), this.player_.on("touchend", vjs.bind(this, function(t) {
							var n;
							e && (n = this.el().className.search("fade-in"), n !== -1 ? this.fadeOut() : this.fadeIn()), e = !1, this.player_.paused() || t.preventDefault();
						}));
					}));
				}
			}), vjs.ControlBar.prototype.options_ = {
				loadEvent: "play",
				children: {
					playToggle: {},
					currentTimeDisplay: {},
					timeDivider: {},
					durationDisplay: {},
					remainingTimeDisplay: {},
					progressControl: {},
					fullscreenToggle: {},
					volumeControl: {},
					muteToggle: {}
				}
			}, vjs.ControlBar.prototype.createEl = function() {
				return vjs.createEl("div", {
					className: "vjs-control-bar"
				});
			}, vjs.ControlBar.prototype.fadeIn = function() {
				vjs.Component.prototype.fadeIn.call(this), this.player_.trigger("controlsvisible");
			}, vjs.ControlBar.prototype.fadeOut = function() {
				vjs.Component.prototype.fadeOut.call(this), this.player_.trigger("controlshidden");
			}, vjs.PlayToggle = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t), e.on("play", vjs.bind(this, this.onPlay)), e.on("pause", vjs.bind(this, this.onPause));
				}
			}), vjs.PlayToggle.prototype.buttonText = "Play", vjs.PlayToggle.prototype.buildCSSClass = function() {
				return "vjs-play-control " + vjs.Button.prototype.buildCSSClass.call(this);
			}, vjs.PlayToggle.prototype.onClick = function() {
				this.player_.paused() ? this.player_.play() : this.player_.pause();
			}, vjs.PlayToggle.prototype.onPlay = function() {
				vjs.removeClass(this.el_, "vjs-paused"), vjs.addClass(this.el_, "vjs-playing"), this.el_.children[0].children[0].innerHTML = "Pause";
			}, vjs.PlayToggle.prototype.onPause = function() {
				vjs.removeClass(this.el_, "vjs-playing"), vjs.addClass(this.el_, "vjs-paused"), this.el_.children[0].children[0].innerHTML = "Play";
			}, vjs.CurrentTimeDisplay = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateContent));
				}
			}), vjs.CurrentTimeDisplay.prototype.createEl = function() {
				var e = vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-current-time vjs-time-controls vjs-control"
				});
				return this.content = vjs.createEl("div", {
					className: "vjs-current-time-display",
					innerHTML: '<span class="vjs-control-text">Current Time </span>0:00',
					"aria-live": "off"
				}), e.appendChild(vjs.createEl("div").appendChild(this.content)), e;
			}, vjs.CurrentTimeDisplay.prototype.updateContent = function() {
				var e = this.player_.scrubbing ? this.player_.getCache().currentTime : this.player_.currentTime();
				this.content.innerHTML = '<span class="vjs-control-text">Current Time </span>' + vjs.formatTime(e, this.player_.duration());
			}, vjs.DurationDisplay = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateContent));
				}
			}), vjs.DurationDisplay.prototype.createEl = function() {
				var e = vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-duration vjs-time-controls vjs-control"
				});
				return this.content = vjs.createEl("div", {
					className: "vjs-duration-display",
					innerHTML: '<span class="vjs-control-text">Duration Time </span>0:00',
					"aria-live": "off"
				}), e.appendChild(vjs.createEl("div").appendChild(this.content)), e;
			}, vjs.DurationDisplay.prototype.updateContent = function() {
				this.player_.duration() && (this.content.innerHTML = '<span class="vjs-control-text">Duration Time </span>' + vjs.formatTime(this.player_.duration()));
			}, vjs.TimeDivider = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t);
				}
			}), vjs.TimeDivider.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-time-divider",
					innerHTML: "<div><span>/</span></div>"
				});
			}, vjs.RemainingTimeDisplay = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateContent));
				}
			}), vjs.RemainingTimeDisplay.prototype.createEl = function() {
				var e = vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-remaining-time vjs-time-controls vjs-control"
				});
				return this.content = vjs.createEl("div", {
					className: "vjs-remaining-time-display",
					innerHTML: '<span class="vjs-control-text">Remaining Time </span>-0:00',
					"aria-live": "off"
				}), e.appendChild(vjs.createEl("div").appendChild(this.content)), e;
			}, vjs.RemainingTimeDisplay.prototype.updateContent = function() {
				this.player_.duration() && this.player_.duration() && (this.content.innerHTML = '<span class="vjs-control-text">Remaining Time </span>-' + vjs.formatTime(this.player_.remainingTime()));
			}, vjs.FullscreenToggle = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t);
				}
			}), vjs.FullscreenToggle.prototype.buttonText = "Fullscreen", vjs.FullscreenToggle.prototype.buildCSSClass = function() {
				return "vjs-fullscreen-control " + vjs.Button.prototype.buildCSSClass.call(this);
			}, vjs.FullscreenToggle.prototype.onClick = function() {
				this.player_.isFullScreen ? (this.player_.cancelFullScreen(), this.el_.children[0].children[0].innerHTML = "Fullscreen") : (this.player_.requestFullScreen(), this.el_.children[0].children[0].innerHTML = "Non-Fullscreen");
			}, vjs.ProgressControl = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t);
				}
			}), vjs.ProgressControl.prototype.options_ = {
				children: {
					seekBar: {}
				}
			}, vjs.ProgressControl.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-progress-control vjs-control"
				});
			}, vjs.SeekBar = vjs.Slider.extend({
				init: function(e, t) {
					vjs.Slider.call(this, e, t), e.on("timeupdate", vjs.bind(this, this.updateARIAAttributes)), e.ready(vjs.bind(this, this.updateARIAAttributes));
				}
			}), vjs.SeekBar.prototype.options_ = {
				children: {
					loadProgressBar: {},
					playProgressBar: {},
					seekHandle: {}
				},
				barName: "playProgressBar",
				handleName: "seekHandle"
			}, vjs.SeekBar.prototype.playerEvent = "timeupdate", vjs.SeekBar.prototype.createEl = function() {
				return vjs.Slider.prototype.createEl.call(this, "div", {
					className: "vjs-progress-holder",
					"aria-label": "video progress bar"
				});
			}, vjs.SeekBar.prototype.updateARIAAttributes = function() {
				var e = this.player_.scrubbing ? this.player_.getCache().currentTime : this.player_.currentTime();
				this.el_.setAttribute("aria-valuenow", vjs.round(this.getPercent() * 100, 2)), this.el_.setAttribute("aria-valuetext", vjs.formatTime(e, this.player_.duration()));
			}, vjs.SeekBar.prototype.getPercent = function() {
				return this.player_.currentTime() / this.player_.duration();
			}, vjs.SeekBar.prototype.onMouseDown = function(e) {
				vjs.Slider.prototype.onMouseDown.call(this, e), this.player_.scrubbing = !0, this.videoWasPlaying = !this.player_.paused(), this.player_.pause();
			}, vjs.SeekBar.prototype.onMouseMove = function(e) {
				var t = this.calculateDistance(e) * this.player_.duration();
				t == this.player_.duration() && (t -= .1), this.player_.currentTime(t);
			}, vjs.SeekBar.prototype.onMouseUp = function(e) {
				vjs.Slider.prototype.onMouseUp.call(this, e), this.player_.scrubbing = !1, this.videoWasPlaying && this.player_.play();
			}, vjs.SeekBar.prototype.stepForward = function() {
				this.player_.currentTime(this.player_.currentTime() + 5);
			}, vjs.SeekBar.prototype.stepBack = function() {
				this.player_.currentTime(this.player_.currentTime() - 5);
			}, vjs.LoadProgressBar = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.on("progress", vjs.bind(this, this.update));
				}
			}), vjs.LoadProgressBar.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-load-progress",
					innerHTML: '<span class="vjs-control-text">Loaded: 0%</span>'
				});
			}, vjs.LoadProgressBar.prototype.update = function() {
				this.el_.style && (this.el_.style.width = vjs.round(this.player_.bufferedPercent() * 100, 2) + "%");
			}, vjs.PlayProgressBar = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t);
				}
			}), vjs.PlayProgressBar.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-play-progress",
					innerHTML: '<span class="vjs-control-text">Progress: 0%</span>'
				});
			}, vjs.SeekHandle = vjs.SliderHandle.extend(), vjs.SeekHandle.prototype.defaultValue = "00:00", vjs.SeekHandle.prototype.createEl = function() {
				return vjs.SliderHandle.prototype.createEl.call(this, "div", {
					className: "vjs-seek-handle"
				});
			}, vjs.VolumeControl = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.tech && e.tech.features && e.tech.features.volumeControl === !1 && this.addClass("vjs-hidden"), e.on("loadstart", vjs.bind(this, function() {
						e.tech.features && e.tech.features.volumeControl === !1 ? this.addClass("vjs-hidden") : this.removeClass("vjs-hidden");
					}));
				}
			}), vjs.VolumeControl.prototype.options_ = {
				children: {
					volumeBar: {}
				}
			}, vjs.VolumeControl.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-volume-control vjs-control"
				});
			}, vjs.VolumeBar = vjs.Slider.extend({
				init: function(e, t) {
					vjs.Slider.call(this, e, t), e.on("volumechange", vjs.bind(this, this.updateARIAAttributes)), e.ready(vjs.bind(this, this.updateARIAAttributes)), setTimeout(vjs.bind(this, this.update), 0);
				}
			}), vjs.VolumeBar.prototype.updateARIAAttributes = function() {
				this.el_.setAttribute("aria-valuenow", vjs.round(this.player_.volume() * 100, 2)), this.el_.setAttribute("aria-valuetext", vjs.round(this.player_.volume() * 100, 2) + "%");
			}, vjs.VolumeBar.prototype.options_ = {
				children: {
					volumeLevel: {},
					volumeHandle: {}
				},
				barName: "volumeLevel",
				handleName: "volumeHandle"
			}, vjs.VolumeBar.prototype.playerEvent = "volumechange", vjs.VolumeBar.prototype.createEl = function() {
				return vjs.Slider.prototype.createEl.call(this, "div", {
					className: "vjs-volume-bar",
					"aria-label": "volume level"
				});
			}, vjs.VolumeBar.prototype.onMouseMove = function(e) {
				this.player_.volume(this.calculateDistance(e));
			}, vjs.VolumeBar.prototype.getPercent = function() {
				return this.player_.muted() ? 0 : this.player_.volume();
			}, vjs.VolumeBar.prototype.stepForward = function() {
				this.player_.volume(this.player_.volume() + .1);
			}, vjs.VolumeBar.prototype.stepBack = function() {
				this.player_.volume(this.player_.volume() - .1);
			}, vjs.VolumeLevel = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t);
				}
			}), vjs.VolumeLevel.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-volume-level",
					innerHTML: '<span class="vjs-control-text"></span>'
				});
			}, vjs.VolumeHandle = vjs.SliderHandle.extend(), vjs.VolumeHandle.prototype.defaultValue = "00:00", vjs.VolumeHandle.prototype.createEl = function() {
				return vjs.SliderHandle.prototype.createEl.call(this, "div", {
					className: "vjs-volume-handle"
				});
			}, vjs.MuteToggle = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t), e.on("volumechange", vjs.bind(this, this.update)), e.tech && e.tech.features && e.tech.features.volumeControl === !1 && this.addClass("vjs-hidden"), e.on("loadstart", vjs.bind(this, function() {
						e.tech.features && e.tech.features.volumeControl === !1 ? this.addClass("vjs-hidden") : this.removeClass("vjs-hidden");
					}));
				}
			}), vjs.MuteToggle.prototype.createEl = function() {
				return vjs.Button.prototype.createEl.call(this, "div", {
					className: "vjs-mute-control vjs-control",
					innerHTML: '<div><span class="vjs-control-text">Mute</span></div>'
				});
			}, vjs.MuteToggle.prototype.onClick = function() {
				this.player_.muted(this.player_.muted() ? !1 : !0);
			}, vjs.MuteToggle.prototype.update = function() {
				var e = this.player_.volume(),
					t = 3;
				e === 0 || this.player_.muted() ? t = 0 : e < .33 ? t = 1 : e < .67 && (t = 2), this.player_.muted() ? this.el_.children[0].children[0].innerHTML != "Unmute" && (this.el_.children[0].children[0].innerHTML = "Unmute") : this.el_.children[0].children[0].innerHTML != "Mute" && (this.el_.children[0].children[0].innerHTML = "Mute");
				for (var n = 0; n < 4; n++) vjs.removeClass(this.el_, "vjs-vol-" + n);
				vjs.addClass(this.el_, "vjs-vol-" + t);
			}, vjs.VolumeMenuButton = vjs.MenuButton.extend({
				init: function(e, t) {
					vjs.MenuButton.call(this, e, t), e.on("volumechange", vjs.bind(this, this.update)), e.tech && e.tech.features && e.tech.features.volumeControl === !1 && this.addClass("vjs-hidden"), e.on("loadstart", vjs.bind(this, function() {
						e.tech.features && e.tech.features.volumeControl === !1 ? this.addClass("vjs-hidden") : this.removeClass("vjs-hidden");
					})), this.addClass("vjs-menu-button");
				}
			}), vjs.VolumeMenuButton.prototype.createMenu = function() {
				var e = new vjs.Menu(this.player_, {
						contentElType: "div"
					}),
					t = new vjs.VolumeBar(this.player_, vjs.obj.merge({
						vertical: !0
					}, this.options_.volumeBar));
				return e.addChild(t), e;
			}, vjs.VolumeMenuButton.prototype.onClick = function() {
				vjs.MuteToggle.prototype.onClick.call(this), vjs.MenuButton.prototype.onClick.call(this);
			}, vjs.VolumeMenuButton.prototype.createEl = function() {
				return vjs.Button.prototype.createEl.call(this, "div", {
					className: "vjs-volume-menu-button vjs-menu-button vjs-control",
					innerHTML: '<div><span class="vjs-control-text">Mute</span></div>'
				});
			}, vjs.VolumeMenuButton.prototype.update = vjs.MuteToggle.prototype.update, vjs.PosterImage = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t), (!e.poster() || !e.controls()) && this.hide(), e.on("play", vjs.bind(this, this.hide));
				}
			}), vjs.PosterImage.prototype.createEl = function() {
				var e = vjs.createEl("div", {
						className: "vjs-poster",
						tabIndex: -1
					}),
					t = this.player_.poster();
				return t && ("backgroundSize" in e.style ? e.style.backgroundImage = 'url("' + t + '")' : e.appendChild(vjs.createEl("img", {
					src: t
				}))), e;
			}, vjs.PosterImage.prototype.onClick = function() {
				this.player_.play();
			}, vjs.LoadingSpinner = vjs.Component.extend({
				init: function(e, t) {
					vjs.Component.call(this, e, t), e.on("canplay", vjs.bind(this, this.hide)), e.on("canplaythrough", vjs.bind(this, this.hide)), e.on("playing", vjs.bind(this, this.hide)), e.on("seeked", vjs.bind(this, this.hide)), e.on("seeking", vjs.bind(this, this.show)), e.on("seeked", vjs.bind(this, this.hide)), e.on("error", vjs.bind(this, this.show)), e.on("waiting", vjs.bind(this, this.show));
				}
			}), vjs.LoadingSpinner.prototype.createEl = function() {
				return vjs.Component.prototype.createEl.call(this, "div", {
					className: "vjs-loading-spinner"
				});
			}, vjs.BigPlayButton = vjs.Button.extend({
				init: function(e, t) {
					vjs.Button.call(this, e, t), e.controls() || this.hide(), e.on("play", vjs.bind(this, this.hide));
				}
			}), vjs.BigPlayButton.prototype.createEl = function() {
				return vjs.Button.prototype.createEl.call(this, "div", {
					className: "vjs-big-play-button",
					innerHTML: "<span></span>",
					"aria-label": "play video"
				});
			}, vjs.BigPlayButton.prototype.onClick = function() {
				this.player_.play();
			}, vjs.MediaTechController = vjs.Component.extend({
				init: function(e, t, n) {
					vjs.Component.call(this, e, t, n);
				}
			}), vjs.MediaTechController.prototype.onClick = function() {
				return vjs.IS_ANDROID ? function() {} : function() {
					this.player_.controls() && (this.player_.paused() ? this.player_.play() : this.player_.pause());
				};
			}(), vjs.MediaTechController.prototype.features = {
				volumeControl: !0,
				fullscreenResize: !1,
				progressEvents: !1,
				timeupdateEvents: !1
			}, vjs.media = {}, vjs.media.ApiMethods = "play,pause,paused,currentTime,setCurrentTime,duration,buffered,volume,setVolume,muted,setMuted,width,height,supportsFullScreen,enterFullScreen,src,load,currentSrc,preload,setPreload,autoplay,setAutoplay,loop,setLoop,error,networkState,readyState,seeking,initialTime,startOffsetTime,played,seekable,ended,videoTracks,audioTracks,videoWidth,videoHeight,textTracks,defaultPlaybackRate,playbackRate,mediaGroup,controller,controls,defaultMuted".split(",");

		function createMethod(e) {
			return function() {
				throw new Error('The "' + e + "\" method is not available on the playback technology's API");
			};
		}
		for (var i = vjs.media.ApiMethods.length - 1; i >= 0; i--) {
			var methodName = vjs.media.ApiMethods[i];
			vjs.MediaTechController.prototype[vjs.media.ApiMethods[i]] = createMethod(methodName);
		}
		vjs.Html5 = vjs.MediaTechController.extend({
			init: function(e, t, n) {
				this.features.volumeControl = vjs.Html5.canControlVolume(), this.features.movingMediaElementInDOM = !vjs.IS_IOS, this.features.fullscreenResize = !0, vjs.MediaTechController.call(this, e, t, n);
				var r = t.source;
				r && this.el_.currentSrc == r.src ? e.trigger("loadstart") : r && (this.el_.src = r.src), e.ready(function() {
					this.tag && this.options_.autoplay && this.paused() && (delete this.tag.poster, this.play());
				}), this.on("click", this.onClick), this.setupTriggers(), this.triggerReady();
			}
		}), vjs.Html5.prototype.dispose = function() {
			vjs.MediaTechController.prototype.dispose.call(this);
		}, vjs.Html5.prototype.createEl = function() {
			var e = this.player_,
				t = e.tag,
				n;
			if (!t || this.features.movingMediaElementInDOM === !1) t ? (t.player = null, e.tag = null, e.el().removeChild(t), t = t.cloneNode(!1)) : t = vjs.createEl("video", {
				id: e.id() + "_html5_api",
				className: "vjs-tech"
			}), t.player = e, vjs.insertFirst(t, e.el());
			var r = ["autoplay", "preload", "loop", "muted"];
			for (var i = r.length - 1; i >= 0; i--) {
				var s = r[i];
				e.options_[s] !== null && (t[s] = e.options_[s]);
			}
			return t;
		}, vjs.Html5.prototype.setupTriggers = function() {
			for (var e = vjs.Html5.Events.length - 1; e >= 0; e--) vjs.on(this.el_, vjs.Html5.Events[e], vjs.bind(this.player_, this.eventHandler));
		}, vjs.Html5.prototype.eventHandler = function(e) {
			this.trigger(e), e.stopPropagation();
		}, vjs.Html5.prototype.play = function() {
			this.el_.play();
		}, vjs.Html5.prototype.pause = function() {
			this.el_.pause();
		}, vjs.Html5.prototype.paused = function() {
			return this.el_.paused;
		}, vjs.Html5.prototype.currentTime = function() {
			return this.el_.currentTime;
		}, vjs.Html5.prototype.setCurrentTime = function(e) {
			try {
				this.el_.currentTime = e;
			} catch (t) {
				vjs.log(t, "Video is not ready. (Video.js)");
			}
		}, vjs.Html5.prototype.duration = function() {
			return this.el_.duration || 0;
		}, vjs.Html5.prototype.buffered = function() {
			return this.el_.buffered;
		}, vjs.Html5.prototype.volume = function() {
			return this.el_.volume;
		}, vjs.Html5.prototype.setVolume = function(e) {
			this.el_.volume = e;
		}, vjs.Html5.prototype.muted = function() {
			return this.el_.muted;
		}, vjs.Html5.prototype.setMuted = function(e) {
			this.el_.muted = e;
		}, vjs.Html5.prototype.width = function() {
			return this.el_.offsetWidth;
		}, vjs.Html5.prototype.height = function() {
			return this.el_.offsetHeight;
		}, vjs.Html5.prototype.supportsFullScreen = function() {
			if (typeof this.el_.webkitEnterFullScreen == "function")
				if (/Android/.test(vjs.USER_AGENT) || !/Chrome|Mac OS X 10.5/.test(vjs.USER_AGENT)) return !0;
			return !1;
		}, vjs.Html5.prototype.enterFullScreen = function() {
			var e = this.el_;
			e.paused && e.networkState <= e.HAVE_METADATA ? (this.el_.play(), setTimeout(function() {
				e.pause(), e.webkitEnterFullScreen();
			}, 0)) : e.webkitEnterFullScreen();
		}, vjs.Html5.prototype.exitFullScreen = function() {
			this.el_.webkitExitFullScreen();
		}, vjs.Html5.prototype.src = function(e) {
			this.el_.src = e;
		}, vjs.Html5.prototype.load = function() {
			this.el_.load();
		}, vjs.Html5.prototype.currentSrc = function() {
			return this.el_.currentSrc;
		}, vjs.Html5.prototype.preload = function() {
			return this.el_.preload;
		}, vjs.Html5.prototype.setPreload = function(e) {
			this.el_.preload = e;
		}, vjs.Html5.prototype.autoplay = function() {
			return this.el_.autoplay;
		}, vjs.Html5.prototype.setAutoplay = function(e) {
			this.el_.autoplay = e;
		}, vjs.Html5.prototype.loop = function() {
			return this.el_.loop;
		}, vjs.Html5.prototype.setLoop = function(e) {
			this.el_.loop = e;
		}, vjs.Html5.prototype.error = function() {
			return this.el_.error;
		}, vjs.Html5.prototype.seeking = function() {
			return this.el_.seeking;
		}, vjs.Html5.prototype.ended = function() {
			return this.el_.ended;
		}, vjs.Html5.prototype.defaultMuted = function() {
			return this.el_.defaultMuted;
		}, vjs.Html5.isSupported = function() {
			return !!vjs.TEST_VID.canPlayType;
		}, vjs.Html5.canPlaySource = function(e) {
			try {
				return !!vjs.TEST_VID.canPlayType(e.type);
			} catch (t) {
				return "";
			}
		}, vjs.Html5.canControlVolume = function() {
			var e = vjs.TEST_VID.volume;
			return vjs.TEST_VID.volume = e / 2 + .1, e !== vjs.TEST_VID.volume;
		}, vjs.Html5.Events = "loadstart,suspend,abort,error,emptied,stalled,loadedmetadata,loadeddata,canplay,canplaythrough,playing,waiting,seeking,seeked,ended,durationchange,timeupdate,progress,play,pause,ratechange,volumechange".split(","), vjs.IS_OLD_ANDROID && (document.createElement("video").constructor.prototype.canPlayType = function(e) {
			return e && e.toLowerCase().indexOf("video/mp4") != -1 ? "maybe" : "";
		}), vjs.Flash = vjs.MediaTechController.extend({
			init: function(e, t, n) {
				vjs.MediaTechController.call(this, e, t, n);
				var r = t.source,
					i = t.parentEl,
					s = this.el_ = vjs.createEl("div", {
						id: e.id() + "_temp_flash"
					}),
					o = e.id() + "_flash_api",
					u = e.options_,
					a = vjs.obj.merge({
						readyFunction: "videojs.Flash.onReady",
						eventProxyFunction: "videojs.Flash.onEvent",
						errorEventProxyFunction: "videojs.Flash.onError",
						autoplay: u.autoplay,
						preload: u.preload,
						loop: u.loop,
						muted: u.muted
					}, t.flashVars),
					f = vjs.obj.merge({
						wmode: "transparent",
						bgcolor: "#000000"
					}, t.params),
					l = vjs.obj.merge({
						id: o,
						name: o,
						"class": "vjs-tech"
					}, t.attributes);
				r && (a.src = encodeURIComponent(vjs.getAbsoluteURL(r.src))), vjs.insertFirst(s, i), t.startTime && this.ready(function() {
					this.load(), this.play(), this.currentTime(t.startTime);
				});
				if (t.iFrameMode === !0 && !vjs.IS_FIREFOX) {
					var c = vjs.createEl("iframe", {
						id: o + "_iframe",
						name: o + "_iframe",
						className: "vjs-tech",
						scrolling: "no",
						marginWidth: 0,
						marginHeight: 0,
						frameBorder: 0
					});
					a.readyFunction = "ready", a.eventProxyFunction = "events", a.errorEventProxyFunction = "errors", vjs.on(c, "load", vjs.bind(this, function() {
						var e, n = c.contentWindow;
						e = c.contentDocument ? c.contentDocument : c.contentWindow.document, e.write(vjs.Flash.getEmbedCode(t.swf, a, f, l)), n.player = this.player_, n.ready = vjs.bind(this.player_, function(t) {
							var n = e.getElementById(t),
								r = this,
								i = r.tech;
							i.el_ = n, vjs.on(n, "click", i.bind(i.onClick)), vjs.Flash.checkReady(i);
						}), n.events = vjs.bind(this.player_, function(e, t) {
							var n = this;
							n && n.techName === "flash" && n.trigger(t);
						}), n.errors = vjs.bind(this.player_, function(e, t) {
							vjs.log("Flash Error", t);
						});
					})), s.parentNode.replaceChild(c, s);
				} else vjs.Flash.embed(t.swf, s, a, f, l);
			}
		}), vjs.Flash.prototype.dispose = function() {
			vjs.MediaTechController.prototype.dispose.call(this);
		}, vjs.Flash.prototype.play = function() {
			this.el_.vjs_play();
		}, vjs.Flash.prototype.pause = function() {
			this.el_.vjs_pause();
		}, vjs.Flash.prototype.src = function(e) {
			e = vjs.getAbsoluteURL(e), this.el_.vjs_src(e);
			if (this.player_.autoplay()) {
				var t = this;
				setTimeout(function() {
					t.play();
				}, 0);
			}
		}, vjs.Flash.prototype.load = function() {
			this.el_.vjs_load();
		}, vjs.Flash.prototype.poster = function() {
			this.el_.vjs_getProperty("poster");
		}, vjs.Flash.prototype.buffered = function() {
			return vjs.createTimeRange(0, this.el_.vjs_getProperty("buffered"));
		}, vjs.Flash.prototype.supportsFullScreen = function() {
			return !1;
		}, vjs.Flash.prototype.enterFullScreen = function() {
			return !1;
		};
		var api = vjs.Flash.prototype,
			readWrite = "preload,currentTime,defaultPlaybackRate,playbackRate,autoplay,loop,mediaGroup,controller,controls,volume,muted,defaultMuted".split(","),
			readOnly = "error,currentSrc,networkState,readyState,seeking,initialTime,duration,startOffsetTime,paused,played,seekable,ended,videoTracks,audioTracks,videoWidth,videoHeight,textTracks".split(","),
			createSetter = function(e) {
				var t = e.charAt(0).toUpperCase() + e.slice(1);
				api["set" + t] = function(t) {
					return this.el_.vjs_setProperty(e, t);
				};
			},
			createGetter = function(e) {
				api[e] = function() {
					return this.el_.vjs_getProperty(e);
				};
			};
		(function() {
			var e;
			for (e = 0; e < readWrite.length; e++) createGetter(readWrite[e]), createSetter(readWrite[e]);
			for (e = 0; e < readOnly.length; e++) createGetter(readOnly[e]);
		})(), vjs.Flash.isSupported = function() {
			return vjs.Flash.version()[0] >= 10;
		}, vjs.Flash.canPlaySource = function(e) {
			if (e.type in vjs.Flash.formats) return "maybe";
		}, vjs.Flash.formats = {
			"video/flv": "FLV",
			"video/x-flv": "FLV",
			"video/mp4": "MP4",
			"video/m4v": "MP4"
		}, vjs.Flash.onReady = function(e) {
			var t = vjs.el(e),
				n = t.player || t.parentNode.player,
				r = n.tech;
			t.player = n, r.el_ = t, r.on("click", r.onClick), vjs.Flash.checkReady(r);
		}, vjs.Flash.checkReady = function(e) {
			e.el().vjs_getProperty ? e.triggerReady() : setTimeout(function() {
				vjs.Flash.checkReady(e);
			}, 50);
		}, vjs.Flash.onEvent = function(e, t) {
			var n = vjs.el(e).player;
			n.trigger(t);
		}, vjs.Flash.onError = function(e, t) {
			var n = vjs.el(e).player;
			n.trigger("error"), vjs.log("Flash Error", t, e);
		}, vjs.Flash.version = function() {
			var e = "0,0,0";
			try {
				e = (new window.ActiveXObject("ShockwaveFlash.ShockwaveFlash")).GetVariable("$version").replace(/\D+/g, ",").match(/^,?(.+),?$/)[1];
			} catch (t) {
				try {
					navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin && (e = (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]).description.replace(/\D+/g, ",").match(/^,?(.+),?$/)[1]);
				} catch (n) {}
			}
			return e.split(",");
		}, vjs.Flash.embed = function(e, t, n, r, i) {
			var s = vjs.Flash.getEmbedCode(e, n, r, i),
				o = vjs.createEl("div", {
					innerHTML: s
				}).childNodes[0],
				u = t.parentNode;
			t.parentNode.replaceChild(o, t);
			var a = u.childNodes[0];
			return setTimeout(function() {
				a.style.display = "block";
			}, 1e3), o;
		}, vjs.Flash.getEmbedCode = function(e, t, n, r) {
			var i = '<object type="application/x-shockwave-flash"',
				s = "",
				o = "",
				u = "";
			return t && vjs.obj.each(t, function(e, t) {
				s += e + "=" + t + "&amp;";
			}), n = vjs.obj.merge({
				movie: e,
				flashvars: s,
				allowScriptAccess: "always",
				allowNetworking: "all"
			}, n), vjs.obj.each(n, function(e, t) {
				o += '<param name="' + e + '" value="' + t + '" />';
			}), r = vjs.obj.merge({
				data: e,
				width: "100%",
				height: "100%"
			}, r), vjs.obj.each(r, function(e, t) {
				u += e + '="' + t + '" ';
			}), i + u + ">" + o + "</object>";
		}, vjs.MediaLoader = vjs.Component.extend({
			init: function(e, t, n) {
				vjs.Component.call(this, e, t, n);
				if (!e.options_.sources || e.options_.sources.length === 0)
					for (var r = 0, i = e.options_.techOrder; r < i.length; r++) {
						var s = vjs.capitalize(i[r]),
							o = window.videojs[s];
						if (o && o.isSupported()) {
							e.loadTech(s);
							break;
						}
					} else e.src(e.options_.sources);
			}
		}), vjs.Player.prototype.textTracks_, vjs.Player.prototype.textTracks = function() {
			return this.textTracks_ = this.textTracks_ || [], this.textTracks_;
		}, vjs.Player.prototype.addTextTrack = function(e, t, n, r) {
			var i = this.textTracks_ = this.textTracks_ || [];
			r = r || {}, r.kind = e, r.label = t, r.language = n;
			var s = vjs.capitalize(e || "subtitles"),
				o = new window.videojs[s + "Track"](this, r);
			return i.push(o), o;
		}, vjs.Player.prototype.addTextTracks = function(e) {
			var t;
			for (var n = 0; n < e.length; n++) t = e[n], this.addTextTrack(t.kind, t.label, t.language, t);
			return this;
		}, vjs.Player.prototype.showTextTrack = function(e, t) {
			var n = this.textTracks_,
				r = 0,
				i = n.length,
				s, o, u;
			for (; r < i; r++) s = n[r], s.id() === e ? (s.show(), o = s) : t && s.kind() == t && s.mode() > 0 && s.disable();
			return u = o ? o.kind() : t ? t : !1, u && this.trigger(u + "trackchange"), this;
		}, vjs.TextTrack = vjs.Component.extend({
			init: function(e, t) {
				vjs.Component.call(this, e, t), this.id_ = t.id || "vjs_" + t.kind + "_" + t.language + "_" + vjs.guid++, this.src_ = t.src, this.dflt_ = t["default"] || t.dflt, this.title_ = t.title, this.language_ = t.srclang, this.label_ = t.label, this.cues_ = [], this.activeCues_ = [], this.readyState_ = 0, this.mode_ = 0, this.player_.on("fullscreenchange", vjs.bind(this, this.adjustFontSize));
			}
		}), vjs.TextTrack.prototype.kind_, vjs.TextTrack.prototype.kind = function() {
			return this.kind_;
		}, vjs.TextTrack.prototype.src_, vjs.TextTrack.prototype.src = function() {
			return this.src_;
		}, vjs.TextTrack.prototype.dflt_, vjs.TextTrack.prototype.dflt = function() {
			return this.dflt_;
		}, vjs.TextTrack.prototype.title_, vjs.TextTrack.prototype.title = function() {
			return this.title_;
		}, vjs.TextTrack.prototype.language_, vjs.TextTrack.prototype.language = function() {
			return this.language_;
		}, vjs.TextTrack.prototype.label_, vjs.TextTrack.prototype.label = function() {
			return this.label_;
		}, vjs.TextTrack.prototype.cues_, vjs.TextTrack.prototype.cues = function() {
			return this.cues_;
		}, vjs.TextTrack.prototype.activeCues_, vjs.TextTrack.prototype.activeCues = function() {
			return this.activeCues_;
		}, vjs.TextTrack.prototype.readyState_, vjs.TextTrack.prototype.readyState = function() {
			return this.readyState_;
		}, vjs.TextTrack.prototype.mode_, vjs.TextTrack.prototype.mode = function() {
			return this.mode_;
		}, vjs.TextTrack.prototype.adjustFontSize = function() {
			this.player_.isFullScreen ? this.el_.style.fontSize = screen.width / this.player_.width() * 1.4 * 100 + "%" : this.el_.style.fontSize = "";
		}, vjs.TextTrack.prototype.createEl = function() {
			return vjs.Component.prototype.createEl.call(this, "div", {
				className: "vjs-" + this.kind_ + " vjs-text-track"
			});
		}, vjs.TextTrack.prototype.show = function() {
			this.activate(), this.mode_ = 2, vjs.Component.prototype.show.call(this);
		}, vjs.TextTrack.prototype.hide = function() {
			this.activate(), this.mode_ = 1, vjs.Component.prototype.hide.call(this);
		}, vjs.TextTrack.prototype.disable = function() {
			this.mode_ == 2 && this.hide(), this.deactivate(), this.mode_ = 0;
		}, vjs.TextTrack.prototype.activate = function() {
			this.readyState_ === 0 && this.load(), this.mode_ === 0 && (this.player_.on("timeupdate", vjs.bind(this, this.update, this.id_)), this.player_.on("ended", vjs.bind(this, this.reset, this.id_)), (this.kind_ === "captions" || this.kind_ === "subtitles") && this.player_.getChild("textTrackDisplay").addChild(this));
		}, vjs.TextTrack.prototype.deactivate = function() {
			this.player_.off("timeupdate", vjs.bind(this, this.update, this.id_)), this.player_.off("ended", vjs.bind(this, this.reset, this.id_)), this.reset(), this.player_.getChild("textTrackDisplay").removeChild(this);
		}, vjs.TextTrack.prototype.load = function() {
			this.readyState_ === 0 && (this.readyState_ = 1, vjs.get(this.src_, vjs.bind(this, this.parseCues), vjs.bind(this, this.onError)));
		}, vjs.TextTrack.prototype.onError = function(e) {
			this.error = e, this.readyState_ = 3, this.trigger("error");
		}, vjs.TextTrack.prototype.parseCues = function(e) {
			var t, n, r, i = e.split("\n"),
				s = "",
				o;
			for (var u = 1, a = i.length; u < a; u++) {
				s = vjs.trim(i[u]);
				if (s) {
					s.indexOf("-->") == -1 ? (o = s, s = vjs.trim(i[++u])) : o = this.cues_.length, t = {
						id: o,
						index: this.cues_.length
					}, n = s.split(" --> "), t.startTime = this.parseCueTime(n[0]), t.endTime = this.parseCueTime(n[1]), r = [];
					while (i[++u] && (s = vjs.trim(i[u]))) r.push(s);
					t.text = r.join("<br/>"), this.cues_.push(t);
				}
			}
			this.readyState_ = 2, this.trigger("loaded");
		}, vjs.TextTrack.prototype.parseCueTime = function(e) {
			var t = e.split(":"),
				n = 0,
				r, i, s, o, u;
			return t.length == 3 ? (r = t[0], i = t[1], s = t[2]) : (r = 0, i = t[0], s = t[1]), s = s.split(/\s+/), o = s.splice(0, 1)[0], o = o.split(/\.|,/), u = parseFloat(o[1]), o = o[0], n += parseFloat(r) * 3600, n += parseFloat(i) * 60, n += parseFloat(o), u && (n += u / 1e3), n;
		}, vjs.TextTrack.prototype.update = function() {
			if (this.cues_.length > 0) {
				var e = this.player_.currentTime();
				if (this.prevChange === undefined || e < this.prevChange || this.nextChange <= e) {
					var t = this.cues_,
						n = this.player_.duration(),
						r = 0,
						i = !1,
						s = [],
						o, u, a, f;
					e >= this.nextChange || this.nextChange === undefined ? f = this.firstActiveIndex !== undefined ? this.firstActiveIndex : 0 : (i = !0, f = this.lastActiveIndex !== undefined ? this.lastActiveIndex : t.length - 1);
					for (;;) {
						a = t[f];
						if (a.endTime <= e) r = Math.max(r, a.endTime), a.active && (a.active = !1);
						else if (e < a.startTime) {
							n = Math.min(n, a.startTime), a.active && (a.active = !1);
							if (!i) break;
						} else i ? (s.splice(0, 0, a), u === undefined && (u = f), o = f) : (s.push(a), o === undefined && (o = f), u = f), n = Math.min(n, a.endTime), r = Math.max(r, a.startTime), a.active = !0;
						if (i) {
							if (f === 0) break;
							f--;
						} else {
							if (f === t.length - 1) break;
							f++;
						}
					}
					this.activeCues_ = s, this.nextChange = n, this.prevChange = r, this.firstActiveIndex = o, this.lastActiveIndex = u, this.updateDisplay(), this.trigger("cuechange");
				}
			}
		}, vjs.TextTrack.prototype.updateDisplay = function() {
			var e = this.activeCues_,
				t = "",
				n = 0,
				r = e.length;
			for (; n < r; n++) t += '<span class="vjs-tt-cue">' + e[n].text + "</span>";
			this.el_.innerHTML = t;
		}, vjs.TextTrack.prototype.reset = function() {
			this.nextChange = 0, this.prevChange = this.player_.duration(), this.firstActiveIndex = 0, this.lastActiveIndex = 0;
		}, vjs.CaptionsTrack = vjs.TextTrack.extend(), vjs.CaptionsTrack.prototype.kind_ = "captions", vjs.SubtitlesTrack = vjs.TextTrack.extend(), vjs.SubtitlesTrack.prototype.kind_ = "subtitles", vjs.ChaptersTrack = vjs.TextTrack.extend(), vjs.ChaptersTrack.prototype.kind_ = "chapters", vjs.TextTrackDisplay = vjs.Component.extend({
			init: function(e, t, n) {
				vjs.Component.call(this, e, t, n), e.options_.tracks && e.options_.tracks.length > 0 && this.player_.addTextTracks(e.options_.tracks);
			}
		}), vjs.TextTrackDisplay.prototype.createEl = function() {
			return vjs.Component.prototype.createEl.call(this, "div", {
				className: "vjs-text-track-display"
			});
		}, vjs.TextTrackMenuItem = vjs.MenuItem.extend({
			init: function(e, t) {
				var n = this.track = t.track;
				t.label = n.label(), t.selected = n.dflt(), vjs.MenuItem.call(this, e, t), this.player_.on(n.kind() + "trackchange", vjs.bind(this, this.update));
			}
		}), vjs.TextTrackMenuItem.prototype.onClick = function() {
			vjs.MenuItem.prototype.onClick.call(this), this.player_.showTextTrack(this.track.id_, this.track.kind());
		}, vjs.TextTrackMenuItem.prototype.update = function() {
			this.selected(this.track.mode() == 2);
		}, vjs.OffTextTrackMenuItem = vjs.TextTrackMenuItem.extend({
			init: function(e, t) {
				t.track = {
					kind: function() {
						return t.kind;
					},
					player: e,
					label: function() {
						return t.kind + " off";
					},
					dflt: function() {
						return !1;
					},
					mode: function() {
						return !1;
					}
				}, vjs.TextTrackMenuItem.call(this, e, t), this.selected(!0);
			}
		}), vjs.OffTextTrackMenuItem.prototype.onClick = function() {
			vjs.TextTrackMenuItem.prototype.onClick.call(this), this.player_.showTextTrack(this.track.id_, this.track.kind());
		}, vjs.OffTextTrackMenuItem.prototype.update = function() {
			var e = this.player_.textTracks(),
				t = 0,
				n = e.length,
				r, i = !0;
			for (; t < n; t++) r = e[t], r.kind() == this.track.kind() && r.mode() == 2 && (i = !1);
			this.selected(i);
		}, vjs.TextTrackButton = vjs.MenuButton.extend({
			init: function(e, t) {
				vjs.MenuButton.call(this, e, t), this.items.length <= 1 && this.hide();
			}
		}), vjs.TextTrackButton.prototype.createItems = function() {
			var e = [],
				t;
			e.push(new vjs.OffTextTrackMenuItem(this.player_, {
				kind: this.kind_
			}));
			for (var n = 0; n < this.player_.textTracks().length; n++) t = this.player_.textTracks()[n], t.kind() === this.kind_ && e.push(new vjs.TextTrackMenuItem(this.player_, {
				track: t
			}));
			return e;
		}, vjs.CaptionsButton = vjs.TextTrackButton.extend({
			init: function(e, t, n) {
				vjs.TextTrackButton.call(this, e, t, n), this.el_.setAttribute("aria-label", "Captions Menu");
			}
		}), vjs.CaptionsButton.prototype.kind_ = "captions", vjs.CaptionsButton.prototype.buttonText = "Captions", vjs.CaptionsButton.prototype.className = "vjs-captions-button", vjs.SubtitlesButton = vjs.TextTrackButton.extend({
			init: function(e, t, n) {
				vjs.TextTrackButton.call(this, e, t, n), this.el_.setAttribute("aria-label", "Subtitles Menu");
			}
		}), vjs.SubtitlesButton.prototype.kind_ = "subtitles", vjs.SubtitlesButton.prototype.buttonText = "Subtitles", vjs.SubtitlesButton.prototype.className = "vjs-subtitles-button", vjs.ChaptersButton = vjs.TextTrackButton.extend({
			init: function(e, t, n) {
				vjs.TextTrackButton.call(this, e, t, n), this.el_.setAttribute("aria-label", "Chapters Menu");
			}
		}), vjs.ChaptersButton.prototype.kind_ = "chapters", vjs.ChaptersButton.prototype.buttonText = "Chapters", vjs.ChaptersButton.prototype.className = "vjs-chapters-button", vjs.ChaptersButton.prototype.createItems = function() {
			var e = [],
				t;
			for (var n = 0; n < this.player_.textTracks().length; n++) t = this.player_.textTracks()[n], t.kind() === this.kind_ && e.push(new vjs.TextTrackMenuItem(this.player_, {
				track: t
			}));
			return e;
		}, vjs.ChaptersButton.prototype.createMenu = function() {
			var e = this.player_.textTracks(),
				t = 0,
				n = e.length,
				r, i, s = this.items = [];
			for (; t < n; t++) {
				r = e[t];
				if (r.kind() == this.kind_ && r.dflt()) {
					if (r.readyState() < 2) {
						this.chaptersTrack = r, r.on("loaded", vjs.bind(this, this.createMenu));
						return;
					}
					i = r;
					break;
				}
			}
			var o = this.menu = new vjs.Menu(this.player_);
			o.el_.appendChild(vjs.createEl("li", {
				className: "vjs-menu-title",
				innerHTML: vjs.capitalize(this.kind_),
				tabindex: -1
			}));
			if (i) {
				var u = i.cues_,
					a, f;
				t = 0, n = u.length;
				for (; t < n; t++) a = u[t], f = new vjs.ChaptersTrackMenuItem(this.player_, {
					track: i,
					cue: a
				}), s.push(f), o.addChild(f);
			}
			return this.items.length > 0 && this.show(), o;
		}, vjs.ChaptersTrackMenuItem = vjs.MenuItem.extend({
			init: function(e, t) {
				var n = this.track = t.track,
					r = this.cue = t.cue,
					i = e.currentTime();
				t.label = r.text, t.selected = r.startTime <= i && i < r.endTime, vjs.MenuItem.call(this, e, t), n.on("cuechange", vjs.bind(this, this.update));
			}
		}), vjs.ChaptersTrackMenuItem.prototype.onClick = function() {
			vjs.MenuItem.prototype.onClick.call(this), this.player_.currentTime(this.cue.startTime), this.update(this.cue.startTime);
		}, vjs.ChaptersTrackMenuItem.prototype.update = function() {
			var e = this.cue,
				t = this.player_.currentTime();
			this.selected(e.startTime <= t && t < e.endTime);
		}, vjs.obj.merge(vjs.ControlBar.prototype.options_.children, {
			subtitlesButton: {},
			captionsButton: {},
			chaptersButton: {}
		}), vjs.JSON;
		if (typeof window.JSON != "undefined" && window.JSON.parse === "function") vjs.JSON = window.JSON;
		else {
			vjs.JSON = {};
			var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
			vjs.JSON.parse = function(text, reviver) {
				function walk(e, t) {
					var n, r, i = e[t];
					if (i && typeof i == "object")
						for (n in i) Object.prototype.hasOwnProperty.call(i, n) && (r = walk(i, n), r !== undefined ? i[n] = r : delete i[n]);
					return reviver.call(e, t, i);
				}
				var j;
				text = String(text), cx.lastIndex = 0, cx.test(text) && (text = text.replace(cx, function(e) {
					return "\\u" + ("0000" + e.charCodeAt(0).toString(16)).slice(-4);
				}));
				if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) return j = eval("(" + text + ")"), typeof reviver == "function" ? walk({
					"": j
				}, "") : j;
				throw new SyntaxError("JSON.parse(): invalid or malformed JSON data");
			};
		}
		return vjs.autoSetup = function() {
			var e, t, n, r = document.getElementsByTagName("video");
			if (r && r.length > 0)
				for (var i = 0, s = r.length; i < s; i++) {
					t = r[i];
					if (!t || !t.getAttribute) {
						vjs.autoSetupTimeout(1);
						break;
					}
					t.player === undefined && (e = t.getAttribute("data-setup"), e !== null && (e = vjs.JSON.parse(e || "{}"), n = videojs(t, e)));
				} else vjs.windowLoaded || vjs.autoSetupTimeout(1);
		}, vjs.autoSetupTimeout = function(e) {
			setTimeout(vjs.autoSetup, e);
		}, vjs.one(window, "load", function() {
			vjs.windowLoaded = !0;
		}), vjs.autoSetupTimeout(1), vjs.plugin = function(e, t) {
			vjs.Player.prototype[e] = t;
		}, videojs;
	} catch (e) {
		wx.jslog({
			src: "biz_web/lib/video.js"
		}, e);
	}
});