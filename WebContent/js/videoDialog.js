define("tpl/media/img.html.js", [], function() {
	return '<div class="appmsgSendedItem simple_img">\n    <a class="title_wrp" href="/cgi-bin/getimgdata?token={token}&msgid={msgid}&mode=large&source={source}&fileId={id}&ow={ow}" target="_blank">\n        <img class="icon" src="/cgi-bin/getimgdata?token={token}&msgid={msgid}&mode=small&source={source}&fileId={id}&ow={ow}">\n        <span class="title">[图片]</span>\n    </a>\n</div>';
});
define("tpl/media/qqmusicaudio.html.js", [], function() {
	return '<div class="qqmusic_audio " id="wxAudioBox{id}" data-aid="{id}">\n    <a class="audio_switch" href="javascript:;"  onclick=\'return false;\' title="点击播放">\n        <i class="icon_qqmusic"></i>\n    </a>\n</div>\n';
});
define("tpl/media/audio.html.js", [], function() {
	return '<div class="appmsgSendedItem simple_audiomsg" id="wxAudioBox{id}" data-aid="{id}" data-fid="{file_id}" data-source="{source}">\n    <a class="title_wrp" href="javascript:;"  title="点击播放">\n        <span class="icon icon_lh" src="{appmsg_cover}"/></span>\n        <span class="title">[语音]{play_length}"</span>\n    </a>\n    <p class="desc">{play_length}"</p>\n</div>\n';
});
define("biz_web/lib/soundmanager2.js", [], function(e, t, n) {
	try {
		var r = +(new Date);
		"use strict";
		var i = null,
			s;

		function o(e, t) {
			function n(e) {
				return o.preferFlash && Bt && !o.ignoreFlash && o.flash[e] !== s && o.flash[e];
			}

			function r(e) {
				return function(t) {
					var n = this._s,
						r;
					return !n || !n._a ? (n && n.id ? o._wD(n.id + ": Ignoring " + t.type) : o._wD(c + "Ignoring " + t.type), r = null) : r = e.call(this, t), r;
				};
			}
			this.setupOptions = {
				url: e || null,
				flashVersion: 8,
				debugMode: !1,
				debugFlash: !1,
				useConsole: !1,
				consoleOnly: !0,
				waitForWindowLoad: !1,
				bgColor: "#ffffff",
				useHighPerformance: !1,
				flashPollingInterval: null,
				html5PollingInterval: null,
				flashLoadTimeout: 1e3,
				wmode: null,
				allowScriptAccess: "always",
				useFlashBlock: !1,
				useHTML5Audio: !0,
				html5Test: /^(probably|maybe)$/i,
				preferFlash: !0,
				noSWFCache: !1,
				idPrefix: "sound"
			}, this.defaultOptions = {
				autoLoad: !1,
				autoPlay: !1,
				from: null,
				loops: 1,
				onid3: null,
				onload: null,
				whileloading: null,
				onplay: null,
				onpause: null,
				onresume: null,
				whileplaying: null,
				onposition: null,
				onstop: null,
				onfailure: null,
				onfinish: null,
				multiShot: !0,
				multiShotEvents: !1,
				position: null,
				pan: 0,
				stream: !0,
				to: null,
				type: null,
				usePolicyFile: !1,
				volume: 100
			}, this.flash9Options = {
				isMovieStar: null,
				usePeakData: !1,
				useWaveformData: !1,
				useEQData: !1,
				onbufferchange: null,
				ondataerror: null
			}, this.movieStarOptions = {
				bufferTime: 3,
				serverURL: null,
				onconnect: null,
				duration: null
			}, this.audioFormats = {
				mp3: {
					type: ['audio/mpeg; codecs="mp3"', "audio/mpeg", "audio/mp3", "audio/MPA", "audio/mpa-robust"],
					required: !0
				},
				mp4: {
					related: ["aac", "m4a", "m4b"],
					type: ['audio/mp4; codecs="mp4a.40.2"', "audio/aac", "audio/x-m4a", "audio/MP4A-LATM", "audio/mpeg4-generic"],
					required: !1
				},
				ogg: {
					type: ["audio/ogg; codecs=vorbis"],
					required: !1
				},
				opus: {
					type: ["audio/ogg; codecs=opus", "audio/opus"],
					required: !1
				},
				wav: {
					type: ['audio/wav; codecs="1"', "audio/wav", "audio/wave", "audio/x-wav"],
					required: !1
				}
			}, this.movieID = "sm2-container", this.id = t || "sm2movie", this.debugID = "soundmanager-debug", this.debugURLParam = /([#?&])debug=1/i, this.versionNumber = "V2.97a.20130512", this.version = null, this.movieURL = null, this.altURL = null, this.swfLoaded = !1, this.enabled = !1, this.oMC = null, this.sounds = {}, this.soundIDs = [], this.muted = !1, this.didFlashBlock = !1, this.filePattern = null, this.filePatterns = {
				flash8: /\.mp3(\?.*)?$/i,
				flash9: /\.mp3(\?.*)?$/i
			}, this.features = {
				buffering: !1,
				peakData: !1,
				waveformData: !1,
				eqData: !1,
				movieStar: !1
			}, this.sandbox = {
				type: null,
				types: {
					remote: "remote (domain-based) rules",
					localWithFile: "local with file access (no internet access)",
					localWithNetwork: "local with network (internet access only, no local access)",
					localTrusted: "local, trusted (local+internet access)"
				},
				description: null,
				noRemote: null,
				noLocal: null
			}, this.html5 = {
				usingFlash: null
			}, this.flash = {}, this.html5Only = !1, this.ignoreFlash = !1;
			var i, o = this,
				u = null,
				a = null,
				f = "soundManager",
				l = f + ": ",
				c = "HTML5::",
				h, p = navigator.userAgent,
				d = window.location.href.toString(),
				v = document,
				m, g, y, b, w = [],
				E = !0,
				S, x = !1,
				T = !1,
				N = !1,
				C = !1,
				k = !1,
				L, A = 0,
				O, M, _, D, P, H, B, j, F, I, q, R, U, z, W, X, V, $, J, K, Q, G, Y = ["log", "info", "warn", "error"],
				Z = 8,
				et, tt, nt, rt = null,
				it = null,
				st, ot, ut, at, ft, lt, ct, ht, pt, dt = !1,
				vt = !1,
				mt, gt, yt, bt = 0,
				wt = null,
				Et, St = [],
				xt, Tt = null,
				Nt, Ct, kt, Lt, At, Ot, Mt, _t, Dt = Array.prototype.slice,
				Pt = !1,
				Ht, Bt, jt, Ft, It, qt, Rt, Ut, zt = 0,
				Wt = p.match(/(ipad|iphone|ipod)/i),
				Xt = p.match(/android/i),
				Vt = p.match(/msie/i),
				$t = p.match(/webkit/i),
				Jt = p.match(/safari/i) && !p.match(/chrome/i),
				Kt = p.match(/opera/i),
				Qt = p.match(/firefox/i),
				Gt = p.match(/(mobile|pre\/|xoom)/i) || Wt || Xt,
				Yt = !d.match(/usehtml5audio/i) && !d.match(/sm2\-ignorebadua/i) && Jt && !p.match(/silk/i) && p.match(/OS X 10_6_([3-7])/i),
				Zt = window.console !== s && console.log !== s,
				en = v.hasFocus !== s ? v.hasFocus() : null,
				tn = Jt && (v.hasFocus === s || !v.hasFocus()),
				nn = !tn,
				rn = /(mp3|mp4|mpa|m4a|m4b)/i,
				sn = 1e3,
				on = "about:blank",
				un = v.location ? v.location.protocol.match(/http/i) : null,
				an = un ? "" : "http://",
				fn = /^\s*audio\/(?:x-)?(?:mpeg4|aac|flv|mov|mp4||m4v|m4a|m4b|mp4v|3gp|3g2)\s*(?:$|;)/i,
				ln = ["mpeg4", "aac", "flv", "mov", "mp4", "m4v", "f4v", "m4a", "m4b", "mp4v", "3gp", "3g2"],
				cn = new RegExp("\\.(" + ln.join("|") + ")(\\?.*)?$", "i");
			this.mimePattern = /^\s*audio\/(?:x-)?(?:mp(?:eg|3))\s*(?:$|;)/i, this.useAltURL = !un, at = {
				swfBox: "sm2-object-box",
				swfDefault: "movieContainer",
				swfError: "swf_error",
				swfTimedout: "swf_timedout",
				swfLoaded: "swf_loaded",
				swfUnblocked: "swf_unblocked",
				sm2Debug: "sm2_debug",
				highPerf: "high_performance",
				flashDebug: "flash_debug"
			}, this.hasHTML5 = function() {
				try {
					return Audio !== s && (Kt && opera !== s && opera.version() < 10 ? new Audio(null) : new Audio).canPlayType !== s;
				} catch (e) {
					return !1;
				}
			}(), this.setup = function(e) {
				var t = !o.url;
				return e !== s && N && Tt && o.ok() && (e.flashVersion !== s || e.url !== s || e.html5Test !== s) && ht(st("setupLate")), _(e), e && (t && V && e.url !== s && o.beginDelayedInit(), !V && e.url !== s && v.readyState === "complete" && setTimeout(W, 1)), o;
			}, this.ok = function() {
				return Tt ? N && !C : o.useHTML5Audio && o.hasHTML5;
			}, this.supported = this.ok, this.getMovie = function(e) {
				return h(e) || v[e] || window[e];
			}, this.createSound = function(e, t) {
				function n() {
					return l = lt(l), o.sounds[l.id] = new i(l), o.soundIDs.push(l.id), o.sounds[l.id];
				}
				var r, u, l, c = null;
				r = f + ".createSound(): ", u = r + st(N ? "notOK" : "notReady");
				if (!N || !o.ok()) return ht(u), !1;
				t !== s && (e = {
					id: e,
					url: t
				}), l = M(e), l.url = Et(l.url), l.id === undefined && (l.id = o.setupOptions.idPrefix + zt++), l.id.toString().charAt(0).match(/^[0-9]$/) && o._wD(r + st("badID", l.id), 2), o._wD(r + l.id + (l.url ? " (" + l.url + ")" : ""), 1);
				if (pt(l.id, !0)) return o._wD(r + l.id + " exists", 1), o.sounds[l.id];
				if (Ct(l)) c = n(), o._wD(l.id + ": Using HTML5"), c._setup_html5(l);
				else {
					if (o.html5Only) return o._wD(l.id + ": No HTML5 support for this sound, and no Flash. Exiting."), n();
					if (o.html5.usingFlash && l.url && l.url.match(/data\:/i)) return o._wD(l.id + ": data: URIs not supported via Flash. Exiting."), n();
					b > 8 && (l.isMovieStar === null && (l.isMovieStar = !!(l.serverURL || (l.type ? l.type.match(fn) : !1) || l.url && l.url.match(cn))), l.isMovieStar && (o._wD(r + "using MovieStar handling"), l.loops > 1 && L("noNSLoop"))), l = ct(l, r), c = n(), b === 8 ? a._createSound(l.id, l.loops || 1, l.usePolicyFile) : (a._createSound(l.id, l.url, l.usePeakData, l.useWaveformData, l.useEQData, l.isMovieStar, l.isMovieStar ? l.bufferTime : !1, l.loops || 1, l.serverURL, l.duration || null, l.autoPlay, !0, l.autoLoad, l.usePolicyFile), l.serverURL || (c.connected = !0, l.onconnect && l.onconnect.apply(c))), !l.serverURL && (l.autoLoad || l.autoPlay) && c.load(l);
				}
				return !l.serverURL && l.autoPlay && c.play(), c;
			}, this.destroySound = function(e, t) {
				if (!pt(e)) return !1;
				var n = o.sounds[e],
					r;
				n._iO = {}, n.stop(), n.unload();
				for (r = 0; r < o.soundIDs.length; r++)
					if (o.soundIDs[r] === e) {
						o.soundIDs.splice(r, 1);
						break;
					}
				return t || n.destruct(!0), n = null, delete o.sounds[e], !0;
			}, this.load = function(e, t) {
				return pt(e) ? o.sounds[e].load(t) : !1;
			}, this.unload = function(e) {
				return pt(e) ? o.sounds[e].unload() : !1;
			}, this.onPosition = function(e, t, n, r) {
				return pt(e) ? o.sounds[e].onposition(t, n, r) : !1;
			}, this.onposition = this.onPosition, this.clearOnPosition = function(e, t, n) {
				return pt(e) ? o.sounds[e].clearOnPosition(t, n) : !1;
			}, this.play = function(e, t) {
				var n = null,
					r = t && !(t instanceof Object);
				if (!N || !o.ok()) return ht(f + ".play(): " + st(N ? "notOK" : "notReady")), !1;
				if (!pt(e, r)) {
					if (!r) return !1;
					r && (t = {
						url: t
					}), t && t.url && (o._wD(f + '.play(): Attempting to create "' + e + '"', 1), t.id = e, n = o.createSound(t).play());
				} else r && (t = {
					url: t
				});
				return n === null && (n = o.sounds[e].play(t)), n;
			}, this.start = this.play, this.setPosition = function(e, t) {
				return pt(e) ? o.sounds[e].setPosition(t) : !1;
			}, this.stop = function(e) {
				return pt(e) ? (o._wD(f + ".stop(" + e + ")", 1), o.sounds[e].stop()) : !1;
			}, this.stopAll = function() {
				var e;
				o._wD(f + ".stopAll()", 1);
				for (e in o.sounds) o.sounds.hasOwnProperty(e) && o.sounds[e].stop();
			}, this.pause = function(e) {
				return pt(e) ? o.sounds[e].pause() : !1;
			}, this.pauseAll = function() {
				var e;
				for (e = o.soundIDs.length - 1; e >= 0; e--) o.sounds[o.soundIDs[e]].pause();
			}, this.resume = function(e) {
				return pt(e) ? o.sounds[e].resume() : !1;
			}, this.resumeAll = function() {
				var e;
				for (e = o.soundIDs.length - 1; e >= 0; e--) o.sounds[o.soundIDs[e]].resume();
			}, this.togglePause = function(e) {
				return pt(e) ? o.sounds[e].togglePause() : !1;
			}, this.setPan = function(e, t) {
				return pt(e) ? o.sounds[e].setPan(t) : !1;
			}, this.setVolume = function(e, t) {
				return pt(e) ? o.sounds[e].setVolume(t) : !1;
			}, this.mute = function(e) {
				var t = 0;
				e instanceof String && (e = null);
				if (!e) {
					o._wD(f + ".mute(): Muting all sounds");
					for (t = o.soundIDs.length - 1; t >= 0; t--) o.sounds[o.soundIDs[t]].mute();
					return o.muted = !0, !0;
				}
				return pt(e) ? (o._wD(f + '.mute(): Muting "' + e + '"'), o.sounds[e].mute()) : !1;
			}, this.muteAll = function() {
				o.mute();
			}, this.unmute = function(e) {
				var t;
				e instanceof String && (e = null);
				if (!e) {
					o._wD(f + ".unmute(): Unmuting all sounds");
					for (t = o.soundIDs.length - 1; t >= 0; t--) o.sounds[o.soundIDs[t]].unmute();
					return o.muted = !1, !0;
				}
				return pt(e) ? (o._wD(f + '.unmute(): Unmuting "' + e + '"'), o.sounds[e].unmute()) : !1;
			}, this.unmuteAll = function() {
				o.unmute();
			}, this.toggleMute = function(e) {
				return pt(e) ? o.sounds[e].toggleMute() : !1;
			}, this.getMemoryUse = function() {
				var e = 0;
				return a && b !== 8 && (e = parseInt(a._getMemoryUse(), 10)), e;
			}, this.disable = function(e) {
				var t;
				e === s && (e = !1);
				if (C) return !1;
				C = !0, L("shutdown", 1);
				for (t = o.soundIDs.length - 1; t >= 0; t--) et(o.sounds[o.soundIDs[t]]);
				return O(e), _t.remove(window, "load", B), !0;
			}, this.canPlayMIME = function(e) {
				var t;
				return o.hasHTML5 && (t = kt({
					type: e
				})), !t && Tt && (t = e && o.ok() ? !!((b > 8 ? e.match(fn) : null) || e.match(o.mimePattern)) : null), t;
			}, this.canPlayURL = function(e) {
				var t;
				return o.hasHTML5 && (t = kt({
					url: e
				})), !t && Tt && (t = e && o.ok() ? !!e.match(o.filePattern) : null), t;
			}, this.canPlayLink = function(e) {
				return e.type !== s && e.type && o.canPlayMIME(e.type) ? !0 : o.canPlayURL(e.href);
			}, this.getSoundById = function(e, t) {
				if (!e) return null;
				var n = o.sounds[e];
				return !n && !t && o._wD(f + '.getSoundById(): Sound "' + e + '" not found.', 2), n;
			}, this.onready = function(e, t) {
				var n = "onready",
					r = !1;
				if (typeof e != "function") throw st("needFunction", n);
				return N && o._wD(st("queue", n)), t || (t = window), P(n, e, t), H(), r = !0, r;
			}, this.ontimeout = function(e, t) {
				var n = "ontimeout",
					r = !1;
				if (typeof e != "function") throw st("needFunction", n);
				return N && o._wD(st("queue", n)), t || (t = window), P(n, e, t), H({
					type: n
				}), r = !0, r;
			}, this._writeDebug = function(e, t) {
				var n = "soundmanager-debug",
					r, i;
				if (!o.debugMode) return !1;
				if (Zt && o.useConsole) {
					t && typeof t == "object" ? console.log(e, t) : Y[t] !== s ? console[Y[t]](e) : console.log(e);
					if (o.consoleOnly) return !0;
				}
				return r = h(n), r ? (i = v.createElement("div"), ++A % 2 === 0 && (i.className = "sm2-alt"), t === s ? t = 0 : t = parseInt(t, 10), i.appendChild(v.createTextNode(e)), t && (t >= 2 && (i.style.fontWeight = "bold"), t === 3 && (i.style.color = "#ff3333")), r.insertBefore(i, r.firstChild), r = null, !0) : !1;
			}, d.indexOf("sm2-debug=alert") !== -1 && (this._writeDebug = function(e) {
				window.alert(e);
			}), this._wD = this._writeDebug, this._debug = function() {
				var e, t;
				L("currentObj", 1);
				for (e = 0, t = o.soundIDs.length; e < t; e++) o.sounds[o.soundIDs[e]]._debug();
			}, this.reboot = function(e, t) {
				o.soundIDs.length && o._wD("Destroying " + o.soundIDs.length + " SMSound object" + (o.soundIDs.length !== 1 ? "s" : "") + "...");
				var n, r, i;
				for (n = o.soundIDs.length - 1; n >= 0; n--) o.sounds[o.soundIDs[n]].destruct();
				if (a) try {
					Vt && (it = a.innerHTML), rt = a.parentNode.removeChild(a);
				} catch (s) {
					L("badRemove", 2);
				}
				it = rt = Tt = a = null, o.enabled = V = N = dt = vt = x = T = C = Pt = o.swfLoaded = !1, o.soundIDs = [], o.sounds = {}, zt = 0;
				if (!e) {
					for (n in w)
						if (w.hasOwnProperty(n))
							for (r = 0, i = w[n].length; r < i; r++) w[n][r].fired = !1;
				} else w = [];
				return t || o._wD(f + ": Rebooting..."), o.html5 = {
					usingFlash: null
				}, o.flash = {}, o.html5Only = !1, o.ignoreFlash = !1, window.setTimeout(function() {
					z(), t || o.beginDelayedInit();
				}, 20), o;
			}, this.reset = function() {
				return L("reset"), o.reboot(!0, !0);
			}, this.getMoviePercent = function() {
				return a && "PercentLoaded" in a ? a.PercentLoaded() : null;
			}, this.beginDelayedInit = function() {
				k = !0, W(), setTimeout(function() {
					return vt ? !1 : (J(), U(), vt = !0, !0);
				}, 20), j();
			}, this.destruct = function() {
				o._wD(f + ".destruct()"), o.disable(!0);
			}, i = function(e) {
				var t = this,
					n, r, i, f, l, c, h = !1,
					p = [],
					d = 0,
					v, m, g = null,
					y, w;
				y = {
					duration: null,
					time: null
				}, this.id = e.id, this.sID = this.id, this.url = e.url, this.options = M(e), this.instanceOptions = this.options, this._iO = this.instanceOptions, this.pan = this.options.pan, this.volume = this.options.volume, this.isHTML5 = !1, this._a = null, w = this.url ? !1 : !0, this.id3 = {}, this._debug = function() {
					o._wD(t.id + ": Merged options:", t.options);
				}, this.load = function(e) {
					var n = null,
						r;
					e !== s ? t._iO = M(e, t.options) : (e = t.options, t._iO = e, g && g !== t.url && (L("manURL"), t._iO.url = t.url, t.url = null)), t._iO.url || (t._iO.url = t.url), t._iO.url = Et(t._iO.url), t.instanceOptions = t._iO, r = t._iO, o._wD(t.id + ": load (" + r.url + ")");
					if (!r.url && !t.url) return o._wD(t.id + ": load(): url is unassigned. Exiting.", 2), t;
					!t.isHTML5 && b === 8 && !t.url && !r.autoPlay && o._wD(t.id + ": Flash 8 load() limitation: Wait for onload() before calling play().", 1);
					if (r.url === t.url && t.readyState !== 0 && t.readyState !== 2) return L("onURL", 1), t.readyState === 3 && r.onload && Ut(t, function() {
						r.onload.apply(t, [!!t.duration]);
					}), t;
					t.loaded = !1, t.readyState = 1, t.playState = 0, t.id3 = {};
					if (Ct(r)) n = t._setup_html5(r), n._called_load ? o._wD(t.id + ": Ignoring request to load again") : (t._html5_canplay = !1, t.url !== r.url && (o._wD(L("manURL") + ": " + r.url), t._a.src = r.url, t.setPosition(0)), t._a.autobuffer = "auto", t._a.preload = "auto", t._a._called_load = !0, r.autoPlay && t.play());
					else {
						if (o.html5Only) return o._wD(t.id + ": No flash support. Exiting."), t;
						if (t._iO.url && t._iO.url.match(/data\:/i)) return o._wD(t.id + ": data: URIs not supported via Flash. Exiting."), t;
						try {
							t.isHTML5 = !1, t._iO = ct(lt(r)), r = t._iO, b === 8 ? a._load(t.id, r.url, r.stream, r.autoPlay, r.usePolicyFile) : a._load(t.id, r.url, !!r.stream, !!r.autoPlay, r.loops || 1, !!r.autoLoad, r.usePolicyFile);
						} catch (i) {
							L("smError", 2), S("onload", !1), K({
								type: "SMSOUND_LOAD_JS_EXCEPTION",
								fatal: !0
							});
						}
					}
					return t.url = r.url, t;
				}, this.unload = function() {
					return t.readyState !== 0 && (o._wD(t.id + ": unload()"), t.isHTML5 ? (f(), t._a && (t._a.pause(), g = At(t._a))) : b === 8 ? a._unload(t.id, on) : a._unload(t.id), n()), t;
				}, this.destruct = function(e) {
					o._wD(t.id + ": Destruct"), t.isHTML5 ? (f(), t._a && (t._a.pause(), At(t._a), Pt || i(), t._a._s = null, t._a = null)) : (t._iO.onfailure = null, a._destroySound(t.id)), e || o.destroySound(t.id, !0);
				}, this.play = function(e, n) {
					var r, i, u, f, p, d, v, g = !0,
						y = null;
					r = t.id + ": play(): ", n = n === s ? !0 : n, e || (e = {}), t.url && (t._iO.url = t.url), t._iO = M(t._iO, t.options), t._iO = M(e, t._iO), t._iO.url = Et(t._iO.url), t.instanceOptions = t._iO;
					if (!t.isHTML5 && t._iO.serverURL && !t.connected) return t.getAutoPlay() || (o._wD(r + " Netstream not connected yet - setting autoPlay"), t.setAutoPlay(!0)), t;
					Ct(t._iO) && (t._setup_html5(t._iO), l()), t.playState === 1 && !t.paused && (i = t._iO.multiShot, i ? o._wD(r + "Already playing (multi-shot)", 1) : (o._wD(r + "Already playing (one-shot)", 1), t.isHTML5 && t.setPosition(t._iO.position), y = t));
					if (y !== null) return y;
					e.url && e.url !== t.url && (!t.readyState && !t.isHTML5 && b === 8 && w ? w = !1 : t.load(t._iO)), t.loaded ? o._wD(r.substr(0, r.lastIndexOf(":"))) : t.readyState === 0 ? (o._wD(r + "Attempting to load"), !t.isHTML5 && !o.html5Only ? (t._iO.autoPlay = !0, t.load(t._iO)) : t.isHTML5 ? t.load(t._iO) : (o._wD(r + "Unsupported type. Exiting."), y = t), t.instanceOptions = t._iO) : t.readyState === 2 ? (o._wD(r + "Could not load - exiting", 2), y = t) : o._wD(r + "Loading - attempting to play...");
					if (y !== null) return y;
					!t.isHTML5 && b === 9 && t.position > 0 && t.position === t.duration && (o._wD(r + "Sound at end, resetting to position:0"), e.position = 0);
					if (t.paused && t.position >= 0 && (!t._iO.serverURL || t.position > 0)) o._wD(r + "Resuming from paused state", 1), t.resume();
					else {
						t._iO = M(e, t._iO);
						if (t._iO.from !== null && t._iO.to !== null && t.instanceCount === 0 && t.playState === 0 && !t._iO.serverURL) {
							f = function() {
								t._iO = M(e, t._iO), t.play(t._iO);
							}, t.isHTML5 && !t._html5_canplay ? (o._wD(r + "Beginning load for from/to case"), t.load({
								oncanplay: f
							}), y = !1) : !t.isHTML5 && !t.loaded && (!t.readyState || t.readyState !== 2) && (o._wD(r + "Preloading for from/to case"), t.load({
								onload: f
							}), y = !1);
							if (y !== null) return y;
							t._iO = m();
						}
						(!t.instanceCount || t._iO.multiShotEvents || t.isHTML5 && t._iO.multiShot && !Pt || !t.isHTML5 && b > 8 && !t.getAutoPlay()) && t.instanceCount++, t._iO.onposition && t.playState === 0 && c(t), t.playState = 1, t.paused = !1, t.position = t._iO.position !== s && !isNaN(t._iO.position) ? t._iO.position : 0, t.isHTML5 || (t._iO = ct(lt(t._iO))), t._iO.onplay && n && (t._iO.onplay.apply(t), h = !0), t.setVolume(t._iO.volume, !0), t.setPan(t._iO.pan, !0), t.isHTML5 ? t.instanceCount < 2 ? (l(), u = t._setup_html5(), t.setPosition(t._iO.position), u.play()) : (o._wD(t.id + ": Cloning Audio() for instance #" + t.instanceCount + "..."), p = new Audio(t._iO.url), d = function() {
							_t.remove(p, "onended", d), t._onfinish(t), At(p), p = null;
						}, v = function() {
							_t.remove(p, "canplay", v);
							try {
								p.currentTime = t._iO.position / sn;
							} catch (e) {
								ht(t.id + ": multiShot play() failed to apply position of " + t._iO.position / sn);
							}
							p.play();
						}, _t.add(p, "ended", d), t._iO.position ? _t.add(p, "canplay", v) : p.play()) : (g = a._start(t.id, t._iO.loops || 1, b === 9 ? t.position : t.position / sn, t._iO.multiShot || !1), b === 9 && !g && (o._wD(r + "No sound hardware, or 32-sound ceiling hit", 2), t._iO.onplayerror && t._iO.onplayerror.apply(t)));
					}
					return t;
				}, this.start = this.play, this.stop = function(e) {
					var n = t._iO,
						r;
					return t.playState === 1 && (o._wD(t.id + ": stop()"), t._onbufferchange(0), t._resetOnPosition(0), t.paused = !1, t.isHTML5 || (t.playState = 0), v(), n.to && t.clearOnPosition(n.to), t.isHTML5 ? t._a && (r = t.position, t.setPosition(0), t.position = r, t._a.pause(), t.playState = 0, t._onTimer(), f()) : (a._stop(t.id, e), n.serverURL && t.unload()), t.instanceCount = 0, t._iO = {}, n.onstop && n.onstop.apply(t)), t;
				}, this.setAutoPlay = function(e) {
					o._wD(t.id + ": Autoplay turned " + (e ? "on" : "off")), t._iO.autoPlay = e, t.isHTML5 || (a._setAutoPlay(t.id, e), e && !t.instanceCount && t.readyState === 1 && (t.instanceCount++, o._wD(t.id + ": Incremented instance count to " + t.instanceCount)));
				}, this.getAutoPlay = function() {
					return t._iO.autoPlay;
				}, this.setPosition = function(e) {
					e === s && (e = 0);
					var n, r, i = t.isHTML5 ? Math.max(e, 0) : Math.min(t.duration || t._iO.duration, Math.max(e, 0));
					t.position = i, r = t.position / sn, t._resetOnPosition(t.position), t._iO.position = i;
					if (!t.isHTML5) n = b === 9 ? t.position : r, t.readyState && t.readyState !== 2 && a._setPosition(t.id, n, t.paused || !t.playState, t._iO.multiShot);
					else if (t._a) {
						if (t._html5_canplay) {
							if (t._a.currentTime !== r) {
								o._wD(t.id + ": setPosition(" + r + ")");
								try {
									t._a.currentTime = r, (t.playState === 0 || t.paused) && t._a.pause();
								} catch (u) {
									o._wD(t.id + ": setPosition(" + r + ") failed: " + u.message, 2);
								}
							}
						} else if (r) return o._wD(t.id + ": setPosition(" + r + "): Cannot seek yet, sound not ready", 2), t;
						t.paused && t._onTimer(!0);
					}
					return t;
				}, this.pause = function(e) {
					return t.paused || t.playState === 0 && t.readyState !== 1 ? t : (o._wD(t.id + ": pause()"), t.paused = !0, t.isHTML5 ? (t._setup_html5().pause(), f()) : (e || e === s) && a._pause(t.id, t._iO.multiShot), t._iO.onpause && t._iO.onpause.apply(t), t);
				}, this.resume = function() {
					var e = t._iO;
					return t.paused ? (o._wD(t.id + ": resume()"), t.paused = !1, t.playState = 1, t.isHTML5 ? (t._setup_html5().play(), l()) : (e.isMovieStar && !e.serverURL && t.setPosition(t.position), a._pause(t.id, e.multiShot)), !h && e.onplay ? (e.onplay.apply(t), h = !0) : e.onresume && e.onresume.apply(t), t) : t;
				}, this.togglePause = function() {
					return o._wD(t.id + ": togglePause()"), t.playState === 0 ? (t.play({
						position: b === 9 && !t.isHTML5 ? t.position : t.position / sn
					}), t) : (t.paused ? t.resume() : t.pause(), t);
				}, this.setPan = function(e, n) {
					return e === s && (e = 0), n === s && (n = !1), t.isHTML5 || a._setPan(t.id, e), t._iO.pan = e, n || (t.pan = e, t.options.pan = e), t;
				}, this.setVolume = function(e, n) {
					return e === s && (e = 100), n === s && (n = !1), t.isHTML5 ? t._a && (t._a.volume = Math.max(0, Math.min(1, e / 100))) : a._setVolume(t.id, o.muted && !t.muted || t.muted ? 0 : e), t._iO.volume = e, n || (t.volume = e, t.options.volume = e), t;
				}, this.mute = function() {
					return t.muted = !0, t.isHTML5 ? t._a && (t._a.muted = !0) : a._setVolume(t.id, 0), t;
				}, this.unmute = function() {
					t.muted = !1;
					var e = t._iO.volume !== s;
					return t.isHTML5 ? t._a && (t._a.muted = !1) : a._setVolume(t.id, e ? t._iO.volume : t.options.volume), t;
				}, this.toggleMute = function() {
					return t.muted ? t.unmute() : t.mute();
				}, this.onPosition = function(e, n, r) {
					return p.push({
						position: parseInt(e, 10),
						method: n,
						scope: r !== s ? r : t,
						fired: !1
					}), t;
				}, this.onposition = this.onPosition, this.clearOnPosition = function(e, t) {
					var n;
					e = parseInt(e, 10);
					if (isNaN(e)) return !1;
					for (n = 0; n < p.length; n++) e === p[n].position && (!t || t === p[n].method) && (p[n].fired && d--, p.splice(n, 1));
				}, this._processOnPosition = function() {
					var e, n, r = p.length;
					if (!r || !t.playState || d >= r) return !1;
					for (e = r - 1; e >= 0; e--) n = p[e], !n.fired && t.position >= n.position && (n.fired = !0, d++, n.method.apply(n.scope, [n.position]));
					return !0;
				}, this._resetOnPosition = function(e) {
					var t, n, r = p.length;
					if (!r) return !1;
					for (t = r - 1; t >= 0; t--) n = p[t], n.fired && e <= n.position && (n.fired = !1, d--);
					return !0;
				}, m = function() {
					var e = t._iO,
						n = e.from,
						r = e.to,
						i, s;
					return s = function() {
						o._wD(t.id + ': "To" time of ' + r + " reached."), t.clearOnPosition(r, s), t.stop();
					}, i = function() {
						o._wD(t.id + ': Playing "from" ' + n), r !== null && !isNaN(r) && t.onPosition(r, s);
					}, n !== null && !isNaN(n) && (e.position = n, e.multiShot = !1, i()), e;
				}, c = function() {
					var e, n = t._iO.onposition;
					if (n)
						for (e in n) n.hasOwnProperty(e) && t.onPosition(parseInt(e, 10), n[e]);
				}, v = function() {
					var e, n = t._iO.onposition;
					if (n)
						for (e in n) n.hasOwnProperty(e) && t.clearOnPosition(parseInt(e, 10));
				}, l = function() {
					t.isHTML5 && mt(t);
				}, f = function() {
					t.isHTML5 && gt(t);
				}, n = function(e) {
					e || (p = [], d = 0), h = !1, t._hasTimer = null, t._a = null, t._html5_canplay = !1, t.bytesLoaded = null, t.bytesTotal = null, t.duration = t._iO && t._iO.duration ? t._iO.duration : null, t.durationEstimate = null, t.buffered = [], t.eqData = [], t.eqData.left = [], t.eqData.right = [], t.failures = 0, t.isBuffering = !1, t.instanceOptions = {}, t.instanceCount = 0, t.loaded = !1, t.metadata = {}, t.readyState = 0, t.muted = !1, t.paused = !1, t.peakData = {
						left: 0,
						right: 0
					}, t.waveformData = {
						left: [],
						right: []
					}, t.playState = 0, t.position = null, t.id3 = {};
				}, n(), this._onTimer = function(e) {
					var n, r = !1,
						i, s = {};
					if (t._hasTimer || e) return t._a && (e || (t.playState > 0 || t.readyState === 1) && !t.paused) && (n = t._get_html5_duration(), n !== y.duration && (y.duration = n, t.duration = n, r = !0), t.durationEstimate = t.duration, i = t._a.currentTime * sn || 0, i !== y.time && (y.time = i, r = !0), (r || e) && t._whileplaying(i, s, s, s, s)), r;
				}, this._get_html5_duration = function() {
					var e = t._iO,
						n = t._a && t._a.duration ? t._a.duration * sn : e && e.duration ? e.duration : null,
						r = n && !isNaN(n) && n !== Infinity ? n : null;
					return r;
				}, this._apply_loop = function(e, t) {
					!e.loop && t > 1 && o._wD("Note: Native HTML5 looping is infinite.", 1), e.loop = t > 1 ? "loop" : "";
				}, this._setup_html5 = function(e) {
					var i = M(t._iO, e),
						s = Pt ? u : t._a,
						o = decodeURI(i.url),
						a;
					Pt ? o === decodeURI(Ht) && (a = !0) : o === decodeURI(g) && (a = !0);
					if (s) {
						if (s._s)
							if (Pt) s._s && s._s.playState && !a && s._s.stop();
							else if (!Pt && o === decodeURI(g)) return t._apply_loop(s, i.loops), s;
						a || (n(!1), s.src = i.url, t.url = i.url, g = i.url, Ht = i.url, s._called_load = !1);
					} else i.autoLoad || i.autoPlay ? t._a = new Audio(i.url) : t._a = Kt && opera.version() < 10 ? new Audio(null) : new Audio, s = t._a, s._called_load = !1, Pt && (u = s);
					return t.isHTML5 = !0, t._a = s, s._s = t, r(), t._apply_loop(s, i.loops), i.autoLoad || i.autoPlay ? t.load() : (s.autobuffer = !1, s.preload = "auto"), s;
				}, r = function() {
					function e(e, n, r) {
						return t._a ? t._a.addEventListener(e, n, r || !1) : null;
					}
					if (t._a._added_events) return !1;
					var n;
					t._a._added_events = !0;
					for (n in It) It.hasOwnProperty(n) && e(n, It[n]);
					return !0;
				}, i = function() {
					function e(e, n, r) {
						return t._a ? t._a.removeEventListener(e, n, r || !1) : null;
					}
					var n;
					o._wD(t.id + ": Removing event listeners"), t._a._added_events = !1;
					for (n in It) It.hasOwnProperty(n) && e(n, It[n]);
				}, this._onload = function(e) {
					var n, r = !!e || !t.isHTML5 && b === 8 && t.duration;
					return n = t.id + ": ", o._wD(n + (r ? "onload()" : "Failed to load / invalid sound?" + (t.duration ? " -" : " Zero-length duration reported.") + " (" + t.url + ")"), r ? 1 : 2), !r && !t.isHTML5 && (o.sandbox.noRemote === !0 && o._wD(n + st("noNet"), 1), o.sandbox.noLocal === !0 && o._wD(n + st("noLocal"), 1)), t.loaded = r, t.readyState = r ? 3 : 2, t._onbufferchange(0), t._iO.onload && Ut(t, function() {
						t._iO.onload.apply(t, [r]);
					}), !0;
				}, this._onbufferchange = function(e) {
					return t.playState === 0 ? !1 : e && t.isBuffering || !e && !t.isBuffering ? !1 : (t.isBuffering = e === 1, t._iO.onbufferchange && (o._wD(t.id + ": Buffer state change: " + e), t._iO.onbufferchange.apply(t)), !0);
				}, this._onsuspend = function() {
					return t._iO.onsuspend && (o._wD(t.id + ": Playback suspended"), t._iO.onsuspend.apply(t)), !0;
				}, this._onfailure = function(e, n, r) {
					t.failures++, o._wD(t.id + ": Failures = " + t.failures), t._iO.onfailure && t.failures === 1 ? t._iO.onfailure(t, e, n, r) : o._wD(t.id + ": Ignoring failure");
				}, this._onfinish = function() {
					var e = t._iO.onfinish;
					t._onbufferchange(0), t._resetOnPosition(0), t.instanceCount && (t.instanceCount--, t.instanceCount || (v(), t.playState = 0, t.paused = !1, t.instanceCount = 0, t.instanceOptions = {}, t._iO = {}, f(), t.isHTML5 && (t.position = 0)), (!t.instanceCount || t._iO.multiShotEvents) && e && (o._wD(t.id + ": onfinish()"), Ut(t, function() {
						e.apply(t);
					})));
				}, this._whileloading = function(e, n, r, i) {
					var s = t._iO;
					t.bytesLoaded = e, t.bytesTotal = n, t.duration = Math.floor(r), t.bufferLength = i, !t.isHTML5 && !s.isMovieStar ? s.duration ? t.durationEstimate = t.duration > s.duration ? t.duration : s.duration : t.durationEstimate = parseInt(t.bytesTotal / t.bytesLoaded * t.duration, 10) : t.durationEstimate = t.duration, t.isHTML5 || (t.buffered = [{
						start: 0,
						end: t.duration
					}]), (t.readyState !== 3 || t.isHTML5) && s.whileloading && s.whileloading.apply(t);
				}, this._whileplaying = function(e, n, r, i, o) {
					var u = t._iO,
						a;
					return isNaN(e) || e === null ? !1 : (t.position = Math.max(0, e), t._processOnPosition(), !t.isHTML5 && b > 8 && (u.usePeakData && n !== s && n && (t.peakData = {
						left: n.leftPeak,
						right: n.rightPeak
					}), u.useWaveformData && r !== s && r && (t.waveformData = {
						left: r.split(","),
						right: i.split(",")
					}), u.useEQData && o !== s && o && o.leftEQ && (a = o.leftEQ.split(","), t.eqData = a, t.eqData.left = a, o.rightEQ !== s && o.rightEQ && (t.eqData.right = o.rightEQ.split(",")))), t.playState === 1 && (!t.isHTML5 && b === 8 && !t.position && t.isBuffering && t._onbufferchange(0), u.whileplaying && u.whileplaying.apply(t)), !0);
				}, this._oncaptiondata = function(e) {
					o._wD(t.id + ": Caption data received."), t.captiondata = e, t._iO.oncaptiondata && t._iO.oncaptiondata.apply(t, [e]);
				}, this._onmetadata = function(e, n) {
					o._wD(t.id + ": Metadata received.");
					var r = {},
						i, s;
					for (i = 0, s = e.length; i < s; i++) r[e[i]] = n[i];
					t.metadata = r, t._iO.onmetadata && t._iO.onmetadata.apply(t);
				}, this._onid3 = function(e, n) {
					o._wD(t.id + ": ID3 data received.");
					var r = [],
						i, s;
					for (i = 0, s = e.length; i < s; i++) r[e[i]] = n[i];
					t.id3 = M(t.id3, r), t._iO.onid3 && t._iO.onid3.apply(t);
				}, this._onconnect = function(e) {
					e = e === 1, o._wD(t.id + ": " + (e ? "Connected." : "Failed to connect? - " + t.url), e ? 1 : 2), t.connected = e, e && (t.failures = 0, pt(t.id) && (t.getAutoPlay() ? t.play(s, t.getAutoPlay()) : t._iO.autoLoad && t.load()), t._iO.onconnect && t._iO.onconnect.apply(t, [e]));
				}, this._ondataerror = function(e) {
					t.playState > 0 && (o._wD(t.id + ": Data error: " + e), t._iO.ondataerror && t._iO.ondataerror.apply(t));
				}, this._debug();
			}, $ = function() {
				return v.body || v._docElement || v.getElementsByTagName("div")[0];
			}, h = function(e) {
				return v.getElementById(e);
			}, M = function(e, t) {
				var n = e || {},
					r, i;
				r = t === s ? o.defaultOptions : t;
				for (i in r) r.hasOwnProperty(i) && n[i] === s && (typeof r[i] != "object" || r[i] === null ? n[i] = r[i] : n[i] = M(n[i], r[i]));
				return n;
			}, Ut = function(e, t) {
				!e.isHTML5 && b === 8 ? window.setTimeout(t, 0) : t();
			}, D = {
				onready: 1,
				ontimeout: 1,
				defaultOptions: 1,
				flash9Options: 1,
				movieStarOptions: 1
			}, _ = function(e, t) {
				var n, r = !0,
					i = t !== s,
					u = o.setupOptions,
					a = D;
				if (e === s) {
					r = [];
					for (n in u) u.hasOwnProperty(n) && r.push(n);
					for (n in a) a.hasOwnProperty(n) && (typeof o[n] == "object" ? r.push(n + ": {...}") : o[n] instanceof Function ? r.push(n + ": function() {...}") : r.push(n));
					return o._wD(st("setup", r.join(", "))), !1;
				}
				for (n in e)
					if (e.hasOwnProperty(n))
						if (typeof e[n] != "object" || e[n] === null || e[n] instanceof Array || e[n] instanceof RegExp) i && a[t] !== s ? o[t][n] = e[n] : u[n] !== s ? (o.setupOptions[n] = e[n], o[n] = e[n]) : a[n] === s ? (ht(st(o[n] === s ? "setupUndef" : "setupError", n), 2), r = !1) : o[n] instanceof Function ? o[n].apply(o, e[n] instanceof Array ? e[n] : [e[n]]) : o[n] = e[n];
						else {
							if (a[n] !== s) return _(e[n], n);
							ht(st(o[n] === s ? "setupUndef" : "setupError", n), 2), r = !1;
						}
				return r;
			}, _t = function() {
				function e(e) {
					var t = Dt.call(e),
						n = t.length;
					return i ? (t[1] = "on" + t[1], n > 3 && t.pop()) : n === 3 && t.push(!1), t;
				}

				function t(e, t) {
					var n = e.shift(),
						r = [s[t]];
					i ? n[r](e[0], e[1]) : n[r].apply(n, e);
				}

				function n() {
					t(e(arguments), "add");
				}

				function r() {
					t(e(arguments), "remove");
				}
				var i = window.attachEvent,
					s = {
						add: i ? "attachEvent" : "addEventListener",
						remove: i ? "detachEvent" : "removeEventListener"
					};
				return {
					add: n,
					remove: r
				};
			}(), It = {
				abort: r(function() {
					o._wD(this._s.id + ": abort");
				}),
				canplay: r(function() {
					var e = this._s,
						t;
					if (e._html5_canplay) return !0;
					e._html5_canplay = !0, o._wD(e.id + ": canplay"), e._onbufferchange(0), t = e._iO.position !== s && !isNaN(e._iO.position) ? e._iO.position / sn : null;
					if (e.position && this.currentTime !== t) {
						o._wD(e.id + ": canplay: Setting position to " + t);
						try {
							this.currentTime = t;
						} catch (n) {
							o._wD(e.id + ": canplay: Setting position of " + t + " failed: " + n.message, 2);
						}
					}
					e._iO._oncanplay && e._iO._oncanplay();
				}),
				canplaythrough: r(function() {
					var e = this._s;
					e.loaded || (e._onbufferchange(0), e._whileloading(e.bytesLoaded, e.bytesTotal, e._get_html5_duration()), e._onload(!0));
				}),
				ended: r(function() {
					var e = this._s;
					o._wD(e.id + ": ended"), e._onfinish();
				}),
				error: r(function() {
					o._wD(this._s.id + ": HTML5 error, code " + this.error.code), this._s._onload(!1);
				}),
				loadeddata: r(function() {
					var e = this._s;
					o._wD(e.id + ": loadeddata"), !e._loaded && !Jt && (e.duration = e._get_html5_duration());
				}),
				loadedmetadata: r(function() {
					o._wD(this._s.id + ": loadedmetadata");
				}),
				loadstart: r(function() {
					o._wD(this._s.id + ": loadstart"), this._s._onbufferchange(1);
				}),
				play: r(function() {
					this._s._onbufferchange(0);
				}),
				playing: r(function() {
					o._wD(this._s.id + ": playing"), this._s._onbufferchange(0);
				}),
				progress: r(function(e) {
					var t = this._s,
						n, r, i, s = 0,
						u = e.type === "progress",
						a = e.target.buffered,
						f = e.loaded || 0,
						l = e.total || 1;
					t.buffered = [];
					if (a && a.length) {
						for (n = 0, r = a.length; n < r; n++) t.buffered.push({
							start: a.start(n) * sn,
							end: a.end(n) * sn
						});
						s = (a.end(0) - a.start(0)) * sn, f = Math.min(1, s / (e.target.duration * sn));
						if (u && a.length > 1) {
							i = [], r = a.length;
							for (n = 0; n < r; n++) i.push(e.target.buffered.start(n) * sn + "-" + e.target.buffered.end(n) * sn);
							o._wD(this._s.id + ": progress, timeRanges: " + i.join(", "));
						}
						u && !isNaN(f) && o._wD(this._s.id + ": progress, " + Math.floor(f * 100) + "% loaded");
					}
					isNaN(f) || (t._onbufferchange(0), t._whileloading(f, l, t._get_html5_duration()), f && l && f === l && It.canplaythrough.call(this, e));
				}),
				ratechange: r(function() {
					o._wD(this._s.id + ": ratechange");
				}),
				suspend: r(function(e) {
					var t = this._s;
					o._wD(this._s.id + ": suspend"), It.progress.call(this, e), t._onsuspend();
				}),
				stalled: r(function() {
					o._wD(this._s.id + ": stalled");
				}),
				timeupdate: r(function() {
					this._s._onTimer();
				}),
				waiting: r(function() {
					var e = this._s;
					o._wD(this._s.id + ": waiting"), e._onbufferchange(1);
				})
			}, Ct = function(e) {
				var t;
				return !e || !e.type && !e.url && !e.serverURL ? t = !1 : e.serverURL || e.type && n(e.type) ? t = !1 : t = e.type ? kt({
					type: e.type
				}) : kt({
					url: e.url
				}) || o.html5Only || e.url.match(/data\:/i), t;
			}, At = function(e) {
				var t;
				return e && (t = Jt && !Wt ? null : Qt ? on : null, e.src = t, e._called_unload !== undefined && (e._called_load = !1)), Pt && (Ht = null), t;
			}, kt = function(e) {
				if (!o.useHTML5Audio || !o.hasHTML5) return !1;
				var t = e.url || null,
					r = e.type || null,
					i = o.audioFormats,
					u, a, f, l;
				if (r && o.html5[r] !== s) return o.html5[r] && !n(r);
				if (!Lt) {
					Lt = [];
					for (l in i) i.hasOwnProperty(l) && (Lt.push(l), i[l].related && (Lt = Lt.concat(i[l].related)));
					Lt = new RegExp("\\.(" + Lt.join("|") + ")(\\?.*)?$", "i");
				}
				return f = t ? t.toLowerCase().match(Lt) : null, !f || !f.length ? r ? (a = r.indexOf(";"), f = (a !== -1 ? r.substr(0, a) : r).substr(6)) : u = !1 : f = f[1], f && o.html5[f] !== s ? u = o.html5[f] && !n(f) : (r = "audio/" + f, u = o.html5.canPlayType({
					type: r
				}), o.html5[f] = u, u = u && o.html5[r] && !n(r)), u;
			}, Mt = function() {
				function e(e) {
					var n, r, i, s = !1,
						u = !1;
					if (!t || typeof t.canPlayType != "function") return s;
					if (e instanceof Array) {
						for (r = 0, i = e.length; r < i; r++)
							if (o.html5[e[r]] || t.canPlayType(e[r]).match(o.html5Test)) u = !0, o.html5[e[r]] = !0, o.flash[e[r]] = !!e[r].match(rn);
						s = u;
					} else n = t && typeof t.canPlayType == "function" ? t.canPlayType(e) : !1, s = !!n && !!n.match(o.html5Test);
					return s;
				}
				if (!o.useHTML5Audio || !o.hasHTML5) return o.html5.usingFlash = !0, Tt = !0, !1;
				var t = Audio !== s ? Kt && opera.version() < 10 ? new Audio(null) : new Audio : null,
					n, r, i = {},
					u, a;
				u = o.audioFormats;
				for (n in u)
					if (u.hasOwnProperty(n)) {
						r = "audio/" + n, i[n] = e(u[n].type), i[r] = i[n], n.match(rn) ? (o.flash[n] = !0, o.flash[r] = !0) : (o.flash[n] = !1, o.flash[r] = !1);
						if (u[n] && u[n].related)
							for (a = u[n].related.length - 1; a >= 0; a--) i["audio/" + u[n].related[a]] = i[n], o.html5[u[n].related[a]] = i[n], o.flash[u[n].related[a]] = i[n];
					}
				return i.canPlayType = t ? e : null, o.html5 = M(o.html5, i), o.html5.usingFlash = Nt(), Tt = o.html5.usingFlash, !0;
			}, R = {
				notReady: "Unavailable - wait until onready() has fired.",
				notOK: "Audio support is not available.",
				domError: f + "exception caught while appending SWF to DOM.",
				spcWmode: "Removing wmode, preventing known SWF loading issue(s)",
				swf404: l + "Verify that %s is a valid path.",
				tryDebug: "Try " + f + ".debugFlash = true for more security details (output goes to SWF.)",
				checkSWF: "See SWF output for more debug info.",
				localFail: l + "Non-HTTP page (" + v.location.protocol + " URL?) Review Flash player security settings for this special case:\nhttp://www.macromedia.com/support/documentation/en/flashplayer/help/settings_manager04.html\nMay need to add/allow path, eg. c:/sm2/ or /users/me/sm2/",
				waitFocus: l + "Special case: Waiting for SWF to load with window focus...",
				waitForever: l + "Waiting indefinitely for Flash (will recover if unblocked)...",
				waitSWF: l + "Waiting for 100% SWF load...",
				needFunction: l + "Function object expected for %s",
				badID: 'Sound ID "%s" should be a string, starting with a non-numeric character',
				currentObj: l + "_debug(): Current sound objects",
				waitOnload: l + "Waiting for window.onload()",
				docLoaded: l + "Document already loaded",
				onload: l + "initComplete(): calling soundManager.onload()",
				onloadOK: f + ".onload() complete",
				didInit: l + "init(): Already called?",
				secNote: "Flash security note: Network/internet URLs will not load due to security restrictions. Access can be configured via Flash Player Global Security Settings Page: http://www.macromedia.com/support/documentation/en/flashplayer/help/settings_manager04.html",
				badRemove: l + "Failed to remove Flash node.",
				shutdown: f + ".disable(): Shutting down",
				queue: l + "Queueing %s handler",
				smError: "SMSound.load(): Exception: JS-Flash communication failed, or JS error.",
				fbTimeout: "No flash response, applying ." + at.swfTimedout + " CSS...",
				fbLoaded: "Flash loaded",
				fbHandler: l + "flashBlockHandler()",
				manURL: "SMSound.load(): Using manually-assigned URL",
				onURL: f + ".load(): current URL already assigned.",
				badFV: f + '.flashVersion must be 8 or 9. "%s" is invalid. Reverting to %s.',
				as2loop: "Note: Setting stream:false so looping can work (flash 8 limitation)",
				noNSLoop: "Note: Looping not implemented for MovieStar formats",
				needfl9: "Note: Switching to flash 9, required for MP4 formats.",
				mfTimeout: "Setting flashLoadTimeout = 0 (infinite) for off-screen, mobile flash case",
				needFlash: l + "Fatal error: Flash is needed to play some required formats, but is not available.",
				gotFocus: l + "Got window focus.",
				policy: "Enabling usePolicyFile for data access",
				setup: f + ".setup(): allowed parameters: %s",
				setupError: f + '.setup(): "%s" cannot be assigned with this method.',
				setupUndef: f + '.setup(): Could not find option "%s"',
				setupLate: f + ".setup(): url, flashVersion and html5Test property changes will not take effect until reboot().",
				noURL: l + "Flash URL required. Call soundManager.setup({url:...}) to get started.",
				sm2Loaded: "SoundManager 2: Ready.",
				reset: f + ".reset(): Removing event callbacks",
				mobileUA: "Mobile UA detected, preferring HTML5 by default.",
				globalHTML5: "Using singleton HTML5 Audio() pattern for this device."
			}, st = function() {
				var e = Dt.call(arguments),
					t = e.shift(),
					n = R && R[t] ? R[t] : "",
					r, i;
				if (n && e && e.length)
					for (r = 0, i = e.length; r < i; r++) n = n.replace("%s", e[r]);
				return n;
			}, lt = function(e) {
				return b === 8 && e.loops > 1 && e.stream && (L("as2loop"), e.stream = !1), e;
			}, ct = function(e, t) {
				return e && !e.usePolicyFile && (e.onid3 || e.usePeakData || e.useWaveformData || e.useEQData) && (o._wD((t || "") + st("policy")), e.usePolicyFile = !0), e;
			}, ht = function(e) {
				Zt && console.warn !== s ? console.warn(e) : o._wD(e);
			}, m = function() {
				return !1;
			}, et = function(e) {
				var t;
				for (t in e) e.hasOwnProperty(t) && typeof e[t] == "function" && (e[t] = m);
				t = null;
			}, tt = function(e) {
				e === s && (e = !1), (C || e) && o.disable(e);
			}, nt = function(e) {
				var t = null,
					n;
				if (e)
					if (e.match(/\.swf(\?.*)?$/i)) {
						t = e.substr(e.toLowerCase().lastIndexOf(".swf?") + 4);
						if (t) return e;
					} else e.lastIndexOf("/") !== e.length - 1 && (e += "/");
				return n = (e && e.lastIndexOf("/") !== -1 ? e.substr(0, e.lastIndexOf("/") + 1) : "./") + o.movieURL, o.noSWFCache && (n += "?ts=" + (new Date).getTime()), n;
			}, I = function() {
				b = parseInt(o.flashVersion, 10), b !== 8 && b !== 9 && (o._wD(st("badFV", b, Z)), o.flashVersion = b = Z);
				var e = o.debugMode || o.debugFlash ? "_debug.swf" : ".swf";
				o.useHTML5Audio && !o.html5Only && o.audioFormats.mp4.required && b < 9 && (o._wD(st("needfl9")), o.flashVersion = b = 9), o.version = o.versionNumber + (o.html5Only ? " (HTML5-only mode)" : b === 9 ? " (AS3/Flash 9)" : " (AS2/Flash 8)"), b > 8 ? (o.defaultOptions = M(o.defaultOptions, o.flash9Options), o.features.buffering = !0, o.defaultOptions = M(o.defaultOptions, o.movieStarOptions), o.filePatterns.flash9 = new RegExp("\\.(mp3|" + ln.join("|") + ")(\\?.*)?$", "i"), o.features.movieStar = !0) : o.features.movieStar = !1, o.filePattern = o.filePatterns[b !== 8 ? "flash9" : "flash8"], o.movieURL = (b === 8 ? "soundmanager2.swf" : "soundmanager2_flash9.swf").replace(".swf", e), o.features.peakData = o.features.waveformData = o.features.eqData = b > 8;
			}, Q = function(e, t) {
				if (!a) return !1;
				a._setPolling(e, t);
			}, G = function() {
				o.debugURLParam.test(d) && (o.debugMode = !0);
				if (h(o.debugID)) return !1;
				var e, t, n, r, i;
				if (o.debugMode && !h(o.debugID) && (!Zt || !o.useConsole || !o.consoleOnly)) {
					e = v.createElement("div"), e.id = o.debugID + "-toggle", r = {
						position: "fixed",
						bottom: "0px",
						right: "0px",
						width: "1.2em",
						height: "1.2em",
						lineHeight: "1.2em",
						margin: "2px",
						textAlign: "center",
						border: "1px solid #999",
						cursor: "pointer",
						background: "#fff",
						color: "#333",
						zIndex: 10001
					}, e.appendChild(v.createTextNode("-")), e.onclick = ft, e.title = "Toggle SM2 debug console", p.match(/msie 6/i) && (e.style.position = "absolute", e.style.cursor = "hand");
					for (i in r) r.hasOwnProperty(i) && (e.style[i] = r[i]);
					t = v.createElement("div"), t.id = o.debugID, t.style.display = o.debugMode ? "block" : "none";
					if (o.debugMode && !h(e.id)) {
						try {
							n = $(), n.appendChild(e);
						} catch (s) {
							throw new Error(st("domError") + " \n" + s.toString());
						}
						n.appendChild(t);
					}
				}
				n = null;
			}, pt = this.getSoundById, L = function(e, t) {
				return e ? o._wD(st(e), t) : "";
			}, ft = function() {
				var e = h(o.debugID),
					t = h(o.debugID + "-toggle");
				if (!e) return !1;
				E ? (t.innerHTML = "+", e.style.display = "none") : (t.innerHTML = "-", e.style.display = "block"), E = !E;
			}, S = function(e, t, n) {
				if (window.sm2Debugger !== s) try {
					sm2Debugger.handleEvent(e, t, n);
				} catch (r) {
					return !1;
				}
				return !0;
			}, ut = function() {
				var e = [];
				return o.debugMode && e.push(at.sm2Debug), o.debugFlash && e.push(at.flashDebug), o.useHighPerformance && e.push(at.highPerf), e.join(" ");
			}, ot = function() {
				var e = st("fbHandler"),
					t = o.getMoviePercent(),
					n = at,
					r = {
						type: "FLASHBLOCK"
					};
				if (o.html5Only) return !1;
				o.ok() ? (o.didFlashBlock && o._wD(e + ": Unblocked"), o.oMC && (o.oMC.className = [ut(), n.swfDefault, n.swfLoaded + (o.didFlashBlock ? " " + n.swfUnblocked : "")].join(" "))) : (Tt && (o.oMC.className = ut() + " " + n.swfDefault + " " + (t === null ? n.swfTimedout : n.swfError), o._wD(e + ": " + st("fbTimeout") + (t ? " (" + st("fbLoaded") + ")" : ""))), o.didFlashBlock = !0, H({
					type: "ontimeout",
					ignoreInit: !0,
					error: r
				}), K(r));
			}, P = function(e, t, n) {
				w[e] === s && (w[e] = []), w[e].push({
					method: t,
					scope: n || null,
					fired: !1
				});
			}, H = function(e) {
				e || (e = {
					type: o.ok() ? "onready" : "ontimeout"
				});
				if (!N && e && !e.ignoreInit) return !1;
				if (e.type === "ontimeout" && (o.ok() || C && !e.ignoreInit)) return !1;
				var t = {
						success: e && e.ignoreInit ? o.ok() : !C
					},
					n = e && e.type ? w[e.type] || [] : [],
					r = [],
					i, s, u = [t],
					a = Tt && !o.ok();
				e.error && (u[0].error = e.error);
				for (i = 0, s = n.length; i < s; i++) n[i].fired !== !0 && r.push(n[i]);
				if (r.length)
					for (i = 0, s = r.length; i < s; i++) r[i].scope ? r[i].method.apply(r[i].scope, u) : r[i].method.apply(this, u), a || (r[i].fired = !0);
				return !0;
			}, B = function() {
				window.setTimeout(function() {
					o.useFlashBlock && ot(), H(), typeof o.onload == "function" && (L("onload", 1), o.onload.apply(window), L("onloadOK", 1)), o.waitForWindowLoad && _t.add(window, "load", B);
				}, 1);
			}, jt = function() {
				if (Bt !== s) return Bt;
				var e = !1,
					t = navigator,
					n = t.plugins,
					r, i, o, u = window.ActiveXObject;
				if (n && n.length) i = "application/x-shockwave-flash", o = t.mimeTypes, o && o[i] && o[i].enabledPlugin && o[i].enabledPlugin.description && (e = !0);
				else if (u !== s && !p.match(/MSAppHost/i)) {
					try {
						r = new u("ShockwaveFlash.ShockwaveFlash");
					} catch (a) {
						r = null;
					}
					e = !!r, r = null;
				}
				return Bt = e, e;
			}, Nt = function() {
				var e, t, n = o.audioFormats,
					r = Wt && !!p.match(/os (1|2|3_0|3_1)/i);
				if (r) o.hasHTML5 = !1, o.html5Only = !0, o.oMC && (o.oMC.style.display = "none");
				else if (o.useHTML5Audio) {
					if (!o.html5 || !o.html5.canPlayType) o._wD("SoundManager: No HTML5 Audio() support detected."), o.hasHTML5 = !1;
					Yt && o._wD(l + "Note: Buggy HTML5 Audio in Safari on this OS X release, see https://bugs.webkit.org/show_bug.cgi?id=32159 - " + (Bt ? "will use flash fallback for MP3/MP4, if available" : " would use flash fallback for MP3/MP4, but none detected."), 1);
				}
				if (o.useHTML5Audio && o.hasHTML5) {
					xt = !0;
					for (t in n) n.hasOwnProperty(t) && n[t].required && (o.html5.canPlayType(n[t].type) ? o.preferFlash && (o.flash[t] || o.flash[n[t].type]) && (e = !0) : (xt = !1, e = !0));
				}
				return o.ignoreFlash && (e = !1, xt = !0), o.html5Only = o.hasHTML5 && o.useHTML5Audio && !e, !o.html5Only;
			}, Et = function(e) {
				var t, n, r = 0,
					i;
				if (e instanceof Array) {
					for (t = 0, n = e.length; t < n; t++)
						if (e[t] instanceof Object) {
							if (o.canPlayMIME(e[t].type)) {
								r = t;
								break;
							}
						} else if (o.canPlayURL(e[t])) {
						r = t;
						break;
					}
					e[r].url && (e[r] = e[r].url), i = e[r];
				} else i = e;
				return i;
			}, mt = function(e) {
				e._hasTimer || (e._hasTimer = !0, !Gt && o.html5PollingInterval && (wt === null && bt === 0 && (wt = setInterval(yt, o.html5PollingInterval)), bt++));
			}, gt = function(e) {
				e._hasTimer && (e._hasTimer = !1, !Gt && o.html5PollingInterval && bt--);
			}, yt = function() {
				var e;
				if (wt !== null && !bt) return clearInterval(wt), wt = null, !1;
				for (e = o.soundIDs.length - 1; e >= 0; e--) o.sounds[o.soundIDs[e]].isHTML5 && o.sounds[o.soundIDs[e]]._hasTimer && o.sounds[o.soundIDs[e]]._onTimer();
			}, K = function(e) {
				e = e !== s ? e : {}, typeof o.onerror == "function" && o.onerror.apply(window, [{
					type: e.type !== s ? e.type : null
				}]), e.fatal !== s && e.fatal && o.disable();
			}, Ft = function() {
				if (!Yt || !jt()) return !1;
				var e = o.audioFormats,
					t, n;
				for (n in e)
					if (e.hasOwnProperty(n))
						if (n === "mp3" || n === "mp4") {
							o._wD(f + ": Using flash fallback for " + n + " format"), o.html5[n] = !1;
							if (e[n] && e[n].related)
								for (t = e[n].related.length - 1; t >= 0; t--) o.html5[e[n].related[t]] = !1;
						}
			}, this._setSandboxType = function(e) {
				var t = o.sandbox;
				t.type = e, t.description = t.types[t.types[e] !== s ? e : "unknown"], t.type === "localWithFile" ? (t.noRemote = !0, t.noLocal = !1, L("secNote", 2)) : t.type === "localWithNetwork" ? (t.noRemote = !1, t.noLocal = !0) : t.type === "localTrusted" && (t.noRemote = !1, t.noLocal = !1);
			}, this._externalInterfaceOK = function(e) {
				if (o.swfLoaded) return !1;
				var t;
				S("swf", !0), S("flashtojs", !0), o.swfLoaded = !0, tn = !1, Yt && Ft();
				if (!e || e.replace(/\+dev/i, "") !== o.versionNumber.replace(/\+dev/i, "")) return t = f + ': Fatal: JavaScript file build "' + o.versionNumber + '" does not match Flash SWF build "' + e + '" at ' + o.url + ". Ensure both are up-to-date.", setTimeout(function() {
					throw new Error(t);
				}, 0), !1;
				setTimeout(y, Vt ? 100 : 1);
			}, J = function(e, t) {
				function n() {
					var e = [],
						t, n = [],
						r = " + ";
					t = "SoundManager " + o.version + (!o.html5Only && o.useHTML5Audio ? o.hasHTML5 ? " + HTML5 audio" : ", no HTML5 audio support" : ""), o.html5Only ? o.html5PollingInterval && e.push("html5PollingInterval (" + o.html5PollingInterval + "ms)") : (o.preferFlash && e.push("preferFlash"), o.useHighPerformance && e.push("useHighPerformance"), o.flashPollingInterval && e.push("flashPollingInterval (" + o.flashPollingInterval + "ms)"), o.html5PollingInterval && e.push("html5PollingInterval (" + o.html5PollingInterval + "ms)"), o.wmode && e.push("wmode (" + o.wmode + ")"), o.debugFlash && e.push("debugFlash"), o.useFlashBlock && e.push("flashBlock")), e.length && (n = n.concat([e.join(r)])), o._wD(t + (n.length ? r + n.join(", ") : ""), 1), qt();
				}

				function r(e, t) {
					return '<param name="' + e + '" value="' + t + '" />';
				}
				if (x && T) return !1;
				if (o.html5Only) return I(), n(), o.oMC = h(o.movieID), y(), x = !0, T = !0, !1;
				var i = t || o.url,
					u = o.altURL || i,
					a = "JS/Flash audio component (SoundManager 2)",
					f = $(),
					l = ut(),
					c = null,
					d = v.getElementsByTagName("html")[0],
					m, g, b, w, E, S, N, C;
				c = d && d.dir && d.dir.match(/rtl/i), e = e === s ? o.id : e, I(), o.url = nt(un ? i : u), t = o.url, o.wmode = !o.wmode && o.useHighPerformance ? "transparent" : o.wmode, o.wmode !== null && (p.match(/msie 8/i) || !Vt && !o.useHighPerformance) && navigator.platform.match(/win32|win64/i) && (St.push(R.spcWmode), o.wmode = null), m = {
					name: e,
					id: e,
					src: t,
					quality: "high",
					allowScriptAccess: o.allowScriptAccess,
					bgcolor: o.bgColor,
					pluginspage: an + "www.macromedia.com/go/getflashplayer",
					title: a,
					type: "application/x-shockwave-flash",
					wmode: o.wmode,
					hasPriority: "true"
				}, o.debugFlash && (m.FlashVars = "debug=1"), o.wmode || delete m.wmode;
				if (Vt) g = v.createElement("div"), w = ['<object id="' + e + '" data="' + t + '" type="' + m.type + '" title="' + m.title + '" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="' + an + 'download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0">', r("movie", t), r("AllowScriptAccess", o.allowScriptAccess), r("quality", m.quality), o.wmode ? r("wmode", o.wmode) : "", r("bgcolor", o.bgColor), r("hasPriority", "true"), o.debugFlash ? r("FlashVars", m.FlashVars) : "", "</object>"].join("");
				else {
					g = v.createElement("embed");
					for (b in m) m.hasOwnProperty(b) && g.setAttribute(b, m[b]);
				}
				G(), l = ut(), f = $();
				if (f) {
					o.oMC = h(o.movieID) || v.createElement("div");
					if (!o.oMC.id) {
						o.oMC.id = o.movieID, o.oMC.className = at.swfDefault + " " + l, S = null, E = null, o.useFlashBlock || (o.useHighPerformance ? S = {
							position: "fixed",
							width: "8px",
							height: "8px",
							bottom: "0px",
							left: "0px",
							overflow: "hidden"
						} : (S = {
							position: "absolute",
							width: "6px",
							height: "6px",
							top: "-9999px",
							left: "-9999px"
						}, c && (S.left = Math.abs(parseInt(S.left, 10)) + "px"))), $t && (o.oMC.style.zIndex = 1e4);
						if (!o.debugFlash)
							for (N in S) S.hasOwnProperty(N) && (o.oMC.style[N] = S[N]);
						try {
							Vt || o.oMC.appendChild(g), f.appendChild(o.oMC), Vt && (E = o.oMC.appendChild(v.createElement("div")), E.className = at.swfBox, E.innerHTML = w), T = !0;
						} catch (k) {
							throw new Error(st("domError") + " \n" + k.toString());
						}
					} else C = o.oMC.className, o.oMC.className = (C ? C + " " : at.swfDefault) + (l ? " " + l : ""), o.oMC.appendChild(g), Vt && (E = o.oMC.appendChild(v.createElement("div")), E.className = at.swfBox, E.innerHTML = w), T = !0;
				}
				return x = !0, n(), !0;
			}, U = function() {
				return o.html5Only ? (J(), !1) : a ? !1 : o.url ? (a = o.getMovie(o.id), a || (rt ? (Vt ? o.oMC.innerHTML = it : o.oMC.appendChild(rt), rt = null, x = !0) : J(o.id, o.url), a = o.getMovie(o.id)), typeof o.oninitmovie == "function" && setTimeout(o.oninitmovie, 1), Rt(), !0) : (L("noURL"), !1);
			}, j = function() {
				setTimeout(F, 1e3);
			}, F = function() {
				var e, t = !1;
				if (!o.url) return !1;
				if (dt) return !1;
				dt = !0, _t.remove(window, "load", j);
				if (tn && !en) return L("waitFocus"), !1;
				N || (e = o.getMoviePercent(), e > 0 && e < 100 && (t = !0)), setTimeout(function() {
					e = o.getMoviePercent();
					if (t) return dt = !1, o._wD(st("waitSWF")), window.setTimeout(j, 1), !1;
					N || (o._wD(f + ": No Flash response within expected time. Likely causes: " + (e === 0 ? "SWF load failed, " : "") + "Flash blocked or JS-Flash security error." + (o.debugFlash ? " " + st("checkSWF") : ""), 2), !un && e && (L("localFail", 2), o.debugFlash || L("tryDebug", 2)), e === 0 && o._wD(st("swf404", o.url), 1), S("flashtojs", !1, ": Timed out" + un ? " (Check flash security or flash blockers)" : " (No plugin/missing SWF?)")), !N && nn && (e === null ? o.useFlashBlock || o.flashLoadTimeout === 0 ? (o.useFlashBlock && ot(), L("waitForever")) : !o.useFlashBlock && xt ? window.setTimeout(function() {
						ht(l + "useFlashBlock is false, 100% HTML5 mode is possible. Rebooting with preferFlash: false..."), o.setup({
							preferFlash: !1
						}).reboot(), o.didFlashBlock = !0, o.beginDelayedInit();
					}, 1) : (L("waitForever"), H({
						type: "ontimeout",
						ignoreInit: !0
					})) : o.flashLoadTimeout === 0 ? L("waitForever") : tt(!0));
				}, o.flashLoadTimeout);
			}, q = function() {
				function e() {
					_t.remove(window, "focus", q);
				}
				return en || !tn ? (e(), !0) : (nn = !0, en = !0, L("gotFocus"), dt = !1, j(), e(), !0);
			}, Rt = function() {
				St.length && (o._wD("SoundManager 2: " + St.join(" "), 1), St = []);
			}, qt = function() {
				Rt();
				var e, t = [];
				if (o.useHTML5Audio && o.hasHTML5) {
					for (e in o.audioFormats) o.audioFormats.hasOwnProperty(e) && t.push(e + " = " + o.html5[e] + (!o.html5[e] && Tt && o.flash[e] ? " (using flash)" : o.preferFlash && o.flash[e] && Tt ? " (preferring flash)" : o.html5[e] ? "" : " (" + (o.audioFormats[e].required ? "required, " : "") + "and no flash support)"));
					o._wD("SoundManager 2 HTML5 support: " + t.join(", "), 1);
				}
			}, O = function(e) {
				if (N) return !1;
				if (o.html5Only) return L("sm2Loaded"), N = !0, B(), S("onload", !0), !0;
				var t = o.useFlashBlock && o.flashLoadTimeout && !o.getMoviePercent(),
					n = !0,
					r;
				return t || (N = !0, C && (r = {
					type: !Bt && Tt ? "NO_FLASH" : "INIT_TIMEOUT"
				})), o._wD("SoundManager 2 " + (C ? "failed to load" : "loaded") + " (" + (C ? "Flash security/load error" : "OK") + ")", C ? 2 : 1), C || e ? (o.useFlashBlock && o.oMC && (o.oMC.className = ut() + " " + (o.getMoviePercent() === null ? at.swfTimedout : at.swfError)), H({
					type: "ontimeout",
					error: r,
					ignoreInit: !0
				}), S("onload", !1), K(r), n = !1) : S("onload", !0), C || (o.waitForWindowLoad && !k ? (L("waitOnload"), _t.add(window, "load", B)) : (o.waitForWindowLoad && k && L("docLoaded"), B())), n;
			}, g = function() {
				var e, t = o.setupOptions;
				for (e in t) t.hasOwnProperty(e) && (o[e] === s ? o[e] = t[e] : o[e] !== t[e] && (o.setupOptions[e] = o[e]));
			}, y = function() {
				function e() {
					_t.remove(window, "load", o.beginDelayedInit);
				}
				if (N) return L("didInit"), !1;
				if (o.html5Only) return N || (e(), o.enabled = !0, O()), !0;
				U();
				try {
					a._externalInterfaceTest(!1), Q(!0, o.flashPollingInterval || (o.useHighPerformance ? 10 : 50)), o.debugMode || a._disableDebug(), o.enabled = !0, S("jstoflash", !0), o.html5Only || _t.add(window, "unload", m);
				} catch (t) {
					return o._wD("js/flash exception: " + t.toString()), S("jstoflash", !1), K({
						type: "JS_TO_FLASH_EXCEPTION",
						fatal: !0
					}), tt(!0), O(), !1;
				}
				return O(), e(), !0;
			}, W = function() {
				return V ? !1 : (V = !0, g(), G(), function() {
					var e = "sm2-usehtml5audio=",
						t = "sm2-preferflash=",
						n = null,
						r = null,
						i = d.toLowerCase();
					i.indexOf(e) !== -1 && (n = i.charAt(i.indexOf(e) + e.length) === "1", Zt && console.log((n ? "Enabling " : "Disabling ") + "useHTML5Audio via URL parameter"), o.setup({
						useHTML5Audio: n
					})), i.indexOf(t) !== -1 && (r = i.charAt(i.indexOf(t) + t.length) === "1", Zt && console.log((r ? "Enabling " : "Disabling ") + "preferFlash via URL parameter"), o.setup({
						preferFlash: r
					}));
				}(), !Bt && o.hasHTML5 && (o._wD("SoundManager: No Flash detected" + (o.useHTML5Audio ? ". Trying HTML5-only mode." : ", enabling HTML5."), 1), o.setup({
					useHTML5Audio: !0,
					preferFlash: !1
				})), Mt(), !Bt && Tt && (St.push(R.needFlash), o.setup({
					flashLoadTimeout: 1
				})), v.removeEventListener && v.removeEventListener("DOMContentLoaded", W, !1), U(), !0);
			}, Ot = function() {
				return v.readyState === "complete" && (W(), v.detachEvent("onreadystatechange", Ot)), !0;
			}, X = function() {
				k = !0, _t.remove(window, "load", X);
			}, z = function() {
				if (Gt) {
					(!o.setupOptions.useHTML5Audio || o.setupOptions.preferFlash) && St.push(R.mobileUA), o.setupOptions.useHTML5Audio = !0, o.setupOptions.preferFlash = !1;
					if (Wt || Xt && !p.match(/android\s2\.3/i)) St.push(R.globalHTML5), Wt && (o.ignoreFlash = !0), Pt = !0;
				}
			}, z(), jt(), _t.add(window, "focus", q), _t.add(window, "load", j), _t.add(window, "load", X), v.addEventListener ? v.addEventListener("DOMContentLoaded", W, !1) : v.attachEvent ? v.attachEvent("onreadystatechange", Ot) : (S("onload", !1), K({
				type: "NO_DOM2_EVENTS",
				fatal: !0
			}));
		}
		if (window.SM2_DEFER === undefined || !SM2_DEFER) i = new o;
		return window.soundManager = i, i;
	} catch (u) {
		wx.jslog({
			src: "biz_web/lib/soundmanager2.js"
		}, u);
	}
});
define("tpl/media/cardmsg.html.js", [], function() {
	return '<div class="msg_card{if _className} {_className}{/if}">\n	<div class="card_content" style="background-color: {color};">\n		<img class="logo js_logourl" data-src="{logo_url}" />\n		<div class="card_info">\n			<h4 class="card_title">{title}</h4>\n		</div>\n		<div class="deco"></div>\n	</div>\n	<p class="store">{brand_name}</p>\n</div>\n';
});
define("common/qq/emoji.js", ["widget/emoji.css"], function(e, t, n) {
	try {
		var r = +(new Date);
		e("widget/emoji.css");
		var i = {
				"☀": "2600",
				"☁": "2601",
				"☔": "2614",
				"⛄": "26c4",
				"⚡": "26a1",
				"🌀": "1f300",
				"🌁": "1f301",
				"🌂": "1f302",
				"🌃": "1f303",
				"🌄": "1f304",
				"🌅": "1f305",
				"🌆": "1f306",
				"🌇": "1f307",
				"🌈": "1f308",
				"❄": "2744",
				"⛅": "26c5",
				"🌉": "1f309",
				"🌊": "1f30a",
				"🌋": "1f30b",
				"🌌": "1f30c",
				"🌏": "1f30f",
				"🌑": "1f311",
				"🌔": "1f314",
				"🌓": "1f313",
				"🌙": "1f319",
				"🌕": "1f315",
				"🌛": "1f31b",
				"🌟": "1f31f",
				"🌠": "1f320",
				"🕐": "1f550",
				"🕑": "1f551",
				"🕒": "1f552",
				"🕓": "1f553",
				"🕔": "1f554",
				"🕕": "1f555",
				"🕖": "1f556",
				"🕗": "1f557",
				"🕘": "1f558",
				"🕙": "1f559",
				"🕚": "1f55a",
				"🕛": "1f55b",
				"⌚": "231a",
				"⌛": "231b",
				"⏰": "23f0",
				"⏳": "23f3",
				"♈": "2648",
				"♉": "2649",
				"♊": "264a",
				"♋": "264b",
				"♌": "264c",
				"♍": "264d",
				"♎": "264e",
				"♏": "264f",
				"♐": "2650",
				"♑": "2651",
				"♒": "2652",
				"♓": "2653",
				"⛎": "26ce",
				"🍀": "1f340",
				"🌷": "1f337",
				"🌱": "1f331",
				"🍁": "1f341",
				"🌸": "1f338",
				"🌹": "1f339",
				"🍂": "1f342",
				"🍃": "1f343",
				"🌺": "1f33a",
				"🌻": "1f33b",
				"🌴": "1f334",
				"🌵": "1f335",
				"🌾": "1f33e",
				"🌽": "1f33d",
				"🍄": "1f344",
				"🌰": "1f330",
				"🌼": "1f33c",
				"🌿": "1f33f",
				"🍒": "1f352",
				"🍌": "1f34c",
				"🍎": "1f34e",
				"🍊": "1f34a",
				"🍓": "1f353",
				"🍉": "1f349",
				"🍅": "1f345",
				"🍆": "1f346",
				"🍈": "1f348",
				"🍍": "1f34d",
				"🍇": "1f347",
				"🍑": "1f351",
				"🍏": "1f34f",
				"👀": "1f440",
				"👂": "1f442",
				"👃": "1f443",
				"👄": "1f444",
				"👅": "1f445",
				"💄": "1f484",
				"💅": "1f485",
				"💆": "1f486",
				"💇": "1f487",
				"💈": "1f488",
				"👤": "1f464",
				"👦": "1f466",
				"👧": "1f467",
				"👨": "1f468",
				"👩": "1f469",
				"👪": "1f46a",
				"👫": "1f46b",
				"👮": "1f46e",
				"👯": "1f46f",
				"👰": "1f470",
				"👱": "1f471",
				"👲": "1f472",
				"👳": "1f473",
				"👴": "1f474",
				"👵": "1f475",
				"👶": "1f476",
				"👷": "1f477",
				"👸": "1f478",
				"👹": "1f479",
				"👺": "1f47a",
				"👻": "1f47b",
				"👼": "1f47c",
				"👽": "1f47d",
				"👾": "1f47e",
				"👿": "1f47f",
				"💀": "1f480",
				"💁": "1f481",
				"💂": "1f482",
				"💃": "1f483",
				"🐌": "1f40c",
				"🐍": "1f40d",
				"🐎": "1f40e",
				"🐔": "1f414",
				"🐗": "1f417",
				"🐫": "1f42b",
				"🐘": "1f418",
				"🐨": "1f428",
				"🐒": "1f412",
				"🐑": "1f411",
				"🐙": "1f419",
				"🐚": "1f41a",
				"🐛": "1f41b",
				"🐜": "1f41c",
				"🐝": "1f41d",
				"🐞": "1f41e",
				"🐠": "1f420",
				"🐡": "1f421",
				"🐢": "1f422",
				"🐤": "1f424",
				"🐥": "1f425",
				"🐦": "1f426",
				"🐣": "1f423",
				"🐧": "1f427",
				"🐩": "1f429",
				"🐟": "1f41f",
				"🐬": "1f42c",
				"🐭": "1f42d",
				"🐯": "1f42f",
				"🐱": "1f431",
				"🐳": "1f433",
				"🐴": "1f434",
				"🐵": "1f435",
				"🐶": "1f436",
				"🐷": "1f437",
				"🐻": "1f43b",
				"🐹": "1f439",
				"🐺": "1f43a",
				"🐮": "1f42e",
				"🐰": "1f430",
				"🐸": "1f438",
				"🐾": "1f43e",
				"🐲": "1f432",
				"🐼": "1f43c",
				"🐽": "1f43d",
				"😠": "1f620",
				"😩": "1f629",
				"😲": "1f632",
				"😞": "1f61e",
				"😵": "1f635",
				"😰": "1f630",
				"😒": "1f612",
				"😍": "1f60d",
				"😤": "1f624",
				"😜": "1f61c",
				"😝": "1f61d",
				"😋": "1f60b",
				"😘": "1f618",
				"😚": "1f61a",
				"😷": "1f637",
				"😳": "1f633",
				"😃": "1f603",
				"😅": "1f605",
				"😆": "1f606",
				"😁": "1f601",
				"😂": "1f602",
				"😊": "1f60a",
				"☺": "263a",
				"😄": "1f604",
				"😢": "1f622",
				"😭": "1f62d",
				"😨": "1f628",
				"😣": "1f623",
				"😡": "1f621",
				"😌": "1f60c",
				"😖": "1f616",
				"😔": "1f614",
				"😱": "1f631",
				"😪": "1f62a",
				"😏": "1f60f",
				"😓": "1f613",
				"😥": "1f625",
				"😫": "1f62b",
				"😉": "1f609",
				"😺": "1f63a",
				"😸": "1f638",
				"😹": "1f639",
				"😽": "1f63d",
				"😻": "1f63b",
				"😿": "1f63f",
				"😾": "1f63e",
				"😼": "1f63c",
				"🙀": "1f640",
				"🙅": "1f645",
				"🙆": "1f646",
				"🙇": "1f647",
				"🙈": "1f648",
				"🙊": "1f64a",
				"🙉": "1f649",
				"🙋": "1f64b",
				"🙌": "1f64c",
				"🙍": "1f64d",
				"🙎": "1f64e",
				"🙏": "1f64f",
				"🏠": "1f3e0",
				"🏡": "1f3e1",
				"🏢": "1f3e2",
				"🏣": "1f3e3",
				"🏥": "1f3e5",
				"🏦": "1f3e6",
				"🏧": "1f3e7",
				"🏨": "1f3e8",
				"🏩": "1f3e9",
				"🏪": "1f3ea",
				"🏫": "1f3eb",
				"⛪": "26ea",
				"⛲": "26f2",
				"🏬": "1f3ec",
				"🏯": "1f3ef",
				"🏰": "1f3f0",
				"🏭": "1f3ed",
				"⚓": "2693",
				"🏮": "1f3ee",
				"🗻": "1f5fb",
				"🗼": "1f5fc",
				"🗽": "1f5fd",
				"🗾": "1f5fe",
				"🗿": "1f5ff",
				"👞": "1f45e",
				"👟": "1f45f",
				"👠": "1f460",
				"👡": "1f461",
				"👢": "1f462",
				"👣": "1f463",
				"👓": "1f453",
				"👕": "1f455",
				"👖": "1f456",
				"👑": "1f451",
				"👔": "1f454",
				"👒": "1f452",
				"👗": "1f457",
				"👘": "1f458",
				"👙": "1f459",
				"👚": "1f45a",
				"👛": "1f45b",
				"👜": "1f45c",
				"👝": "1f45d",
				"💰": "1f4b0",
				"💱": "1f4b1",
				"💹": "1f4b9",
				"💲": "1f4b2",
				"💳": "1f4b3",
				"💴": "1f4b4",
				"💵": "1f4b5",
				"💸": "1f4b8",
				"🇨🇳": "1f1e81f1f3",
				"🇩🇪": "1f1e91f1ea",
				"🇪🇸": "1f1ea1f1f8",
				"🇫🇷": "1f1eb1f1f7",
				"🇬🇧": "1f1ec1f1e7",
				"🇮🇹": "1f1ee1f1f9",
				"🇯🇵": "1f1ef1f1f5",
				"🇰🇷": "1f1f01f1f7",
				"🇷🇺": "1f1f71f1fa",
				"🇺🇸": "1f1fa1f1f8",
				"🔥": "1f525",
				"🔦": "1f526",
				"🔧": "1f527",
				"🔨": "1f528",
				"🔩": "1f529",
				"🔪": "1f52a",
				"🔫": "1f52b",
				"🔮": "1f52e",
				"🔯": "1f52f",
				"🔰": "1f530",
				"🔱": "1f531",
				"💉": "1f489",
				"💊": "1f48a",
				"🅰": "1f170",
				"🅱": "1f171",
				"🆎": "1f18e",
				"🅾": "1f17e",
				"🎀": "1f380",
				"🎁": "1f381",
				"🎂": "1f382",
				"🎄": "1f384",
				"🎅": "1f385",
				"🎌": "1f38c",
				"🎆": "1f386",
				"🎈": "1f388",
				"🎉": "1f389",
				"🎍": "1f38d",
				"🎎": "1f38e",
				"🎓": "1f393",
				"🎒": "1f392",
				"🎏": "1f38f",
				"🎇": "1f387",
				"🎐": "1f390",
				"🎃": "1f383",
				"🎊": "1f38a",
				"🎋": "1f38b",
				"🎑": "1f391",
				"📟": "1f4df",
				"☎": "260e",
				"📞": "1f4de",
				"📱": "1f4f1",
				"📲": "1f4f2",
				"📝": "1f4dd",
				"📠": "1f4e0",
				"✉": "2709",
				"📨": "1f4e8",
				"📩": "1f4e9",
				"📪": "1f4ea",
				"📫": "1f4eb",
				"📮": "1f4ee",
				"📰": "1f4f0",
				"📢": "1f4e2",
				"📣": "1f4e3",
				"📡": "1f4e1",
				"📤": "1f4e4",
				"📥": "1f4e5",
				"📦": "1f4e6",
				"📧": "1f4e7",
				"🔠": "1f520",
				"🔡": "1f521",
				"🔢": "1f522",
				"🔣": "1f523",
				"🔤": "1f524",
				"✒": "2712",
				"💺": "1f4ba",
				"💻": "1f4bb",
				"✏": "270f",
				"📎": "1f4ce",
				"💼": "1f4bc",
				"💽": "1f4bd",
				"💾": "1f4be",
				"💿": "1f4bf",
				"📀": "1f4c0",
				"✂": "2702",
				"📍": "1f4cd",
				"📃": "1f4c3",
				"📄": "1f4c4",
				"📅": "1f4c5",
				"📁": "1f4c1",
				"📂": "1f4c2",
				"📓": "1f4d3",
				"📖": "1f4d6",
				"📔": "1f4d4",
				"📕": "1f4d5",
				"📗": "1f4d7",
				"📘": "1f4d8",
				"📙": "1f4d9",
				"📚": "1f4da",
				"📛": "1f4db",
				"📜": "1f4dc",
				"📋": "1f4cb",
				"📆": "1f4c6",
				"📊": "1f4ca",
				"📈": "1f4c8",
				"📉": "1f4c9",
				"📇": "1f4c7",
				"📌": "1f4cc",
				"📒": "1f4d2",
				"📏": "1f4cf",
				"📐": "1f4d0",
				"📑": "1f4d1",
				"🎽": "1f3bd",
				"⚾": "26be",
				"⛳": "26f3",
				"🎾": "1f3be",
				"⚽": "26bd",
				"🎿": "1f3bf",
				"🏀": "1f3c0",
				"🏁": "1f3c1",
				"🏂": "1f3c2",
				"🏃": "1f3c3",
				"🏄": "1f3c4",
				"🏆": "1f3c6",
				"🏈": "1f3c8",
				"🏊": "1f3ca",
				"🚃": "1f683",
				"🚇": "1f687",
				"Ⓜ": "24c2",
				"🚄": "1f684",
				"🚅": "1f685",
				"🚗": "1f697",
				"🚙": "1f699",
				"🚌": "1f68c",
				"🚏": "1f68f",
				"🚢": "1f6a2",
				"✈": "2708",
				"⛵": "26f5",
				"🚉": "1f689",
				"🚀": "1f680",
				"🚤": "1f6a4",
				"🚕": "1f695",
				"🚚": "1f69a",
				"🚒": "1f692",
				"🚑": "1f691",
				"🚓": "1f693",
				"⛽": "26fd",
				"🅿": "1f17f",
				"🚥": "1f6a5",
				"🚧": "1f6a7",
				"🚨": "1f6a8",
				"♨": "2668",
				"⛺": "26fa",
				"🎠": "1f3a0",
				"🎡": "1f3a1",
				"🎢": "1f3a2",
				"🎣": "1f3a3",
				"🎤": "1f3a4",
				"🎥": "1f3a5",
				"🎦": "1f3a6",
				"🎧": "1f3a7",
				"🎨": "1f3a8",
				"🎩": "1f3a9",
				"🎪": "1f3aa",
				"🎫": "1f3ab",
				"🎬": "1f3ac",
				"🎭": "1f3ad",
				"🎮": "1f3ae",
				"🀄": "1f004",
				"🎯": "1f3af",
				"🎰": "1f3b0",
				"🎱": "1f3b1",
				"🎲": "1f3b2",
				"🎳": "1f3b3",
				"🎴": "1f3b4",
				"🃏": "1f0cf",
				"🎵": "1f3b5",
				"🎶": "1f3b6",
				"🎷": "1f3b7",
				"🎸": "1f3b8",
				"🎹": "1f3b9",
				"🎺": "1f3ba",
				"🎻": "1f3bb",
				"🎼": "1f3bc",
				"〽": "303d",
				"📷": "1f4f7",
				"📹": "1f4f9",
				"📺": "1f4fa",
				"📻": "1f4fb",
				"📼": "1f4fc",
				"💋": "1f48b",
				"💌": "1f48c",
				"💍": "1f48d",
				"💎": "1f48e",
				"💏": "1f48f",
				"💐": "1f490",
				"💑": "1f491",
				"💒": "1f492",
				"🔞": "1f51e",
				"©": "a9",
				"®": "ae",
				"™": "2122",
				"ℹ": "2139",
				"#⃣": "2320e3",
				"1⃣": "3120e3",
				"2⃣": "3220e3",
				"3⃣": "3320e3",
				"4⃣": "3420e3",
				"5⃣": "3520e3",
				"6⃣": "3620e3",
				"7⃣": "3720e3",
				"8⃣": "3820e3",
				"9⃣": "3920e3",
				"0⃣": "3020e3",
				"🔟": "1f51f",
				"📶": "1f4f6",
				"📳": "1f4f3",
				"📴": "1f4f4",
				"🍔": "1f354",
				"🍙": "1f359",
				"🍰": "1f370",
				"🍜": "1f35c",
				"🍞": "1f35e",
				"🍳": "1f373",
				"🍦": "1f366",
				"🍟": "1f35f",
				"🍡": "1f361",
				"🍘": "1f358",
				"🍚": "1f35a",
				"🍝": "1f35d",
				"🍛": "1f35b",
				"🍢": "1f362",
				"🍣": "1f363",
				"🍱": "1f371",
				"🍲": "1f372",
				"🍧": "1f367",
				"🍖": "1f356",
				"🍥": "1f365",
				"🍠": "1f360",
				"🍕": "1f355",
				"🍗": "1f357",
				"🍨": "1f368",
				"🍩": "1f369",
				"🍪": "1f36a",
				"🍫": "1f36b",
				"🍬": "1f36c",
				"🍭": "1f36d",
				"🍮": "1f36e",
				"🍯": "1f36f",
				"🍤": "1f364",
				"🍴": "1f374",
				"☕": "2615",
				"🍸": "1f378",
				"🍺": "1f37a",
				"🍵": "1f375",
				"🍶": "1f376",
				"🍷": "1f377",
				"🍻": "1f37b",
				"🍹": "1f379",
				"↗": "2197",
				"↘": "2198",
				"↖": "2196",
				"↙": "2199",
				"⤴": "2934",
				"⤵": "2935",
				"↔": "2194",
				"↕": "2195",
				"⬆": "2b06",
				"⬇": "2b07",
				"➡": "27a1",
				"⬅": "2b05",
				"▶": "25b6",
				"◀": "25c0",
				"⏩": "23e9",
				"⏪": "23ea",
				"⏫": "23eb",
				"⏬": "23ec",
				"🔺": "1f53a",
				"🔻": "1f53b",
				"🔼": "1f53c",
				"🔽": "1f53d",
				"⭕": "2b55",
				"❌": "274c",
				"❎": "274e",
				"❗": "2757",
				"⁉": "2049",
				"‼": "203c",
				"❓": "2753",
				"❔": "2754",
				"❕": "2755",
				"〰": "3030",
				"➰": "27b0",
				"➿": "27bf",
				"❤": "2764",
				"💓": "1f493",
				"💔": "1f494",
				"💕": "1f495",
				"💖": "1f496",
				"💗": "1f497",
				"💘": "1f498",
				"💙": "1f499",
				"💚": "1f49a",
				"💛": "1f49b",
				"💜": "1f49c",
				"💝": "1f49d",
				"💞": "1f49e",
				"💟": "1f49f",
				"♥": "2665",
				"♠": "2660",
				"♦": "2666",
				"♣": "2663",
				"🚬": "1f6ac",
				"🚭": "1f6ad",
				"♿": "267f",
				"🚩": "1f6a9",
				"⚠": "26a0",
				"⛔": "26d4",
				"♻": "267b",
				"🚲": "1f6b2",
				"🚶": "1f6b6",
				"🚹": "1f6b9",
				"🚺": "1f6ba",
				"🛀": "1f6c0",
				"🚻": "1f6bb",
				"🚽": "1f6bd",
				"🚾": "1f6be",
				"🚼": "1f6bc",
				"🚪": "1f6aa",
				"🚫": "1f6ab",
				"✔": "2714",
				"🆑": "1f191",
				"🆒": "1f192",
				"🆓": "1f193",
				"🆔": "1f194",
				"🆕": "1f195",
				"🆖": "1f196",
				"🆗": "1f197",
				"🆘": "1f198",
				"🆙": "1f199",
				"🆚": "1f19a",
				"🈁": "1f201",
				"🈂": "1f202",
				"🈲": "1f232",
				"🈳": "1f233",
				"🈴": "1f234",
				"🈵": "1f235",
				"🈶": "1f236",
				"🈚": "1f21a",
				"🈷": "1f237",
				"🈸": "1f238",
				"🈹": "1f239",
				"🈯": "1f22f",
				"🈺": "1f23a",
				"㊙": "3299",
				"㊗": "3297",
				"🉐": "1f250",
				"🉑": "1f251",
				"➕": "2795",
				"➖": "2796",
				"✖": "2716",
				"➗": "2797",
				"💠": "1f4a0",
				"💡": "1f4a1",
				"💢": "1f4a2",
				"💣": "1f4a3",
				"💤": "1f4a4",
				"💥": "1f4a5",
				"💦": "1f4a6",
				"💧": "1f4a7",
				"💨": "1f4a8",
				"💩": "1f4a9",
				"💪": "1f4aa",
				"💫": "1f4ab",
				"💬": "1f4ac",
				"✨": "2728",
				"✴": "2734",
				"✳": "2733",
				"⚪": "26aa",
				"⚫": "26ab",
				"🔴": "1f534",
				"🔵": "1f535",
				"🔲": "1f532",
				"🔳": "1f533",
				"⭐": "2b50",
				"⬜": "2b1c",
				"⬛": "2b1b",
				"▫": "25ab",
				"▪": "25aa",
				"◽": "25fd",
				"◾": "25fe",
				"◻": "25fb",
				"◼": "25fc",
				"🔶": "1f536",
				"🔷": "1f537",
				"🔸": "1f538",
				"🔹": "1f539",
				"❇": "2747",
				"💮": "1f4ae",
				"💯": "1f4af",
				"↩": "21a9",
				"↪": "21aa",
				"🔃": "1f503",
				"🔊": "1f50a",
				"🔋": "1f50b",
				"🔌": "1f50c",
				"🔍": "1f50d",
				"🔎": "1f50e",
				"🔒": "1f512",
				"🔓": "1f513",
				"🔏": "1f50f",
				"🔐": "1f510",
				"🔑": "1f511",
				"🔔": "1f514",
				"☑": "2611",
				"🔘": "1f518",
				"🔖": "1f516",
				"🔗": "1f517",
				"🔙": "1f519",
				"🔚": "1f51a",
				"🔛": "1f51b",
				"🔜": "1f51c",
				"🔝": "1f51d",
				" ": "2003",
				" ": "2002",
				" ": "2005",
				"✅": "2705",
				"✊": "270a",
				"✋": "270b",
				"✌": "270c",
				"👊": "1f44a",
				"👍": "1f44d",
				"☝": "261d",
				"👆": "1f446",
				"👇": "1f447",
				"👈": "1f448",
				"👉": "1f449",
				"👋": "1f44b",
				"👏": "1f44f",
				"👌": "1f44c",
				"👎": "1f44e",
				"👐": "1f450",
				"": "2600",
				"": "2601",
				"": "2614",
				"": "26c4",
				"": "26a1",
				"": "1f300",
				"[霧]": "1f301",
				"": "1f302",
				"": "1f30c",
				"": "1f304",
				"": "1f305",
				"": "1f306",
				"": "1f307",
				"": "1f308",
				"[雪結晶]": "2744",
				"": "26c5",
				"": "1f30a",
				"[火山]": "1f30b",
				"[地球]": "1f30f",
				"●": "1f311",
				"": "1f31b",
				"○": "1f315",
				"": "1f31f",
				"☆彡": "1f320",
				"": "1f550",
				"": "1f551",
				"": "1f552",
				"": "1f553",
				"": "1f554",
				"": "1f555",
				"": "1f556",
				"": "1f557",
				"": "1f558",
				"": "23f0",
				"": "1f55a",
				"": "1f55b",
				"[腕時計]": "231a",
				"[砂時計]": "23f3",
				"": "2648",
				"": "2649",
				"": "264a",
				"": "264b",
				"": "264c",
				"": "264d",
				"": "264e",
				"": "264f",
				"": "2650",
				"": "2651",
				"": "2652",
				"": "2653",
				"": "26ce",
				"": "1f33f",
				"": "1f337",
				"": "1f341",
				"": "1f338",
				"": "1f339",
				"": "1f342",
				"": "1f343",
				"": "1f33a",
				"": "1f33c",
				"": "1f334",
				"": "1f335",
				"": "1f33e",
				"[とうもろこし]": "1f33d",
				"[キノコ]": "1f344",
				"[栗]": "1f330",
				"[さくらんぼ]": "1f352",
				"[バナナ]": "1f34c",
				"": "1f34f",
				"": "1f34a",
				"": "1f353",
				"": "1f349",
				"": "1f345",
				"": "1f346",
				"[メロン]": "1f348",
				"[パイナップル]": "1f34d",
				"[ブドウ]": "1f347",
				"[モモ]": "1f351",
				"": "1f440",
				"": "1f442",
				"": "1f443",
				"": "1f444",
				"": "1f61d",
				"": "1f484",
				"": "1f485",
				"": "1f486",
				"": "1f487",
				"": "1f488",
				"〓": "2005",
				"": "1f466",
				"": "1f467",
				"": "1f468",
				"": "1f469",
				"[家族]": "1f46a",
				"": "1f46b",
				"": "1f46e",
				"": "1f46f",
				"[花嫁]": "1f470",
				"": "1f471",
				"": "1f472",
				"": "1f473",
				"": "1f474",
				"": "1f475",
				"": "1f476",
				"": "1f477",
				"": "1f478",
				"[なまはげ]": "1f479",
				"[天狗]": "1f47a",
				"": "1f47b",
				"": "1f47c",
				"": "1f47d",
				"": "1f47e",
				"": "1f47f",
				"": "1f480",
				"": "1f481",
				"": "1f482",
				"": "1f483",
				"[カタツムリ]": "1f40c",
				"": "1f40d",
				"": "1f40e",
				"": "1f414",
				"": "1f417",
				"": "1f42b",
				"": "1f418",
				"": "1f428",
				"": "1f412",
				"": "1f411",
				"": "1f419",
				"": "1f41a",
				"": "1f41b",
				"[アリ]": "1f41c",
				"[ミツバチ]": "1f41d",
				"[てんとう虫]": "1f41e",
				"": "1f420",
				"": "1f3a3",
				"[カメ]": "1f422",
				"": "1f423",
				"": "1f426",
				"": "1f427",
				"": "1f436",
				"": "1f42c",
				"": "1f42d",
				"": "1f42f",
				"": "1f431",
				"": "1f433",
				"": "1f434",
				"": "1f435",
				"": "1f43d",
				"": "1f43b",
				"": "1f439",
				"": "1f43a",
				"": "1f42e",
				"": "1f430",
				"": "1f438",
				"": "1f463",
				"[辰]": "1f432",
				"[パンダ]": "1f43c",
				"": "1f620",
				"": "1f64d",
				"": "1f632",
				"": "1f61e",
				"": "1f62b",
				"": "1f630",
				"": "1f612",
				"": "1f63b",
				"": "1f63c",
				"": "1f61c",
				"": "1f60a",
				"": "1f63d",
				"": "1f61a",
				"": "1f637",
				"": "1f633",
				"": "1f63a",
				"": "1f605",
				"": "1f60c",
				"": "1f639",
				"": "263a",
				"": "1f604",
				"": "1f63f",
				"": "1f62d",
				"": "1f628",
				"": "1f64e",
				"": "1f4ab",
				"": "1f631",
				"": "1f62a",
				"": "1f60f",
				"": "1f613",
				"": "1f625",
				"": "1f609",
				"": "1f645",
				"": "1f646",
				"": "1f647",
				"(/_＼)": "1f648",
				"(・×・)": "1f64a",
				"|(・×・)|": "1f649",
				"": "270b",
				"": "1f64c",
				"": "1f64f",
				"": "1f3e1",
				"": "1f3e2",
				"": "1f3e3",
				"": "1f3e5",
				"": "1f3e6",
				"": "1f3e7",
				"": "1f3e8",
				"": "1f3e9",
				"": "1f3ea",
				"": "1f3eb",
				"": "26ea",
				"": "26f2",
				"": "1f3ec",
				"": "1f3ef",
				"": "1f3f0",
				"": "1f3ed",
				"": "1f6a2",
				"": "1f376",
				"": "1f5fb",
				"": "1f5fc",
				"": "1f5fd",
				"[日本地図]": "1f5fe",
				"[モアイ]": "1f5ff",
				"": "1f45f",
				"": "1f460",
				"": "1f461",
				"": "1f462",
				"[メガネ]": "1f453",
				"": "1f45a",
				"[ジーンズ]": "1f456",
				"": "1f451",
				"": "1f454",
				"": "1f452",
				"": "1f457",
				"": "1f458",
				"": "1f459",
				"[財布]": "1f45b",
				"": "1f45c",
				"[ふくろ]": "1f45d",
				"": "1f4b5",
				"": "1f4b1",
				"": "1f4c8",
				"[カード]": "1f4b3",
				"￥": "1f4b4",
				"[飛んでいくお金]": "1f4b8",
				"": "1f1e81f1f3",
				"": "1f1e91f1ea",
				"": "1f1ea1f1f8",
				"": "1f1eb1f1f7",
				"": "1f1ec1f1e7",
				"": "1f1ee1f1f9",
				"": "1f1ef1f1f5",
				"": "1f1f01f1f7",
				"": "1f1f71f1fa",
				"": "1f1fa1f1f8",
				"": "1f525",
				"[懐中電灯]": "1f526",
				"[レンチ]": "1f527",
				"": "1f528",
				"[ネジ]": "1f529",
				"[包丁]": "1f52a",
				"": "1f52b",
				"": "1f52f",
				"": "1f530",
				"": "1f531",
				"": "1f489",
				"": "1f48a",
				"": "1f170",
				"": "1f171",
				"": "1f18e",
				"": "1f17e",
				"": "1f380",
				"": "1f4e6",
				"": "1f382",
				"": "1f384",
				"": "1f385",
				"": "1f38c",
				"": "1f386",
				"": "1f388",
				"": "1f389",
				"": "1f38d",
				"": "1f38e",
				"": "1f393",
				"": "1f392",
				"": "1f38f",
				"": "1f387",
				"": "1f390",
				"": "1f383",
				"[オメデトウ]": "1f38a",
				"[七夕]": "1f38b",
				"": "1f391",
				"[ポケベル]": "1f4df",
				"": "1f4de",
				"": "1f4f1",
				"": "1f4f2",
				"": "1f4d1",
				"": "1f4e0",
				"": "1f4e7",
				"": "1f4eb",
				"": "1f4ee",
				"[新聞]": "1f4f0",
				"": "1f4e2",
				"": "1f4e3",
				"": "1f4e1",
				"[送信BOX]": "1f4e4",
				"[受信BOX]": "1f4e5",
				"[ABCD]": "1f520",
				"[abcd]": "1f521",
				"[1234]": "1f522",
				"[記号]": "1f523",
				"[ABC]": "1f524",
				"[ペン]": "2712",
				"": "1f4ba",
				"": "1f4bb",
				"[クリップ]": "1f4ce",
				"": "1f4bc",
				"": "1f4be",
				"": "1f4bf",
				"": "1f4c0",
				"": "2702",
				"[画びょう]": "1f4cc",
				"[カレンダー]": "1f4c6",
				"[フォルダ]": "1f4c2",
				"": "1f4d2",
				"[名札]": "1f4db",
				"[スクロール]": "1f4dc",
				"[グラフ]": "1f4c9",
				"[定規]": "1f4cf",
				"[三角定規]": "1f4d0",
				"": "26be",
				"": "26f3",
				"": "1f3be",
				"": "26bd",
				"": "1f3bf",
				"": "1f3c0",
				"": "1f3c1",
				"[スノボ]": "1f3c2",
				"": "1f3c3",
				"": "1f3c4",
				"": "1f3c6",
				"": "1f3c8",
				"": "1f3ca",
				"": "1f683",
				"": "24c2",
				"": "1f684",
				"": "1f685",
				"": "1f697",
				"": "1f699",
				"": "1f68c",
				"": "1f68f",
				"": "2708",
				"": "26f5",
				"": "1f689",
				"": "1f680",
				"": "1f6a4",
				"": "1f695",
				"": "1f69a",
				"": "1f692",
				"": "1f691",
				"": "1f6a8",
				"": "26fd",
				"": "1f17f",
				"": "1f6a5",
				"": "26d4",
				"": "2668",
				"": "26fa",
				"": "1f3a1",
				"": "1f3a2",
				"": "1f3a4",
				"": "1f4f9",
				"": "1f3a6",
				"": "1f3a7",
				"": "1f3a8",
				"": "1f3ad",
				"[イベント]": "1f3aa",
				"": "1f3ab",
				"": "1f3ac",
				"[ゲーム]": "1f3ae",
				"": "1f004",
				"": "1f3af",
				"": "1f3b0",
				"": "1f3b1",
				"[サイコロ]": "1f3b2",
				"[ボーリング]": "1f3b3",
				"[花札]": "1f3b4",
				"[ジョーカー]": "1f0cf",
				"": "1f3b5",
				"": "1f3bc",
				"": "1f3b7",
				"": "1f3b8",
				"[ピアノ]": "1f3b9",
				"": "1f3ba",
				"[バイオリン]": "1f3bb",
				"": "303d",
				"": "1f4f7",
				"": "1f4fa",
				"": "1f4fb",
				"": "1f4fc",
				"": "1f48b",
				"": "1f48c",
				"": "1f48d",
				"": "1f48e",
				"": "1f48f",
				"": "1f490",
				"": "1f491",
				"": "1f492",
				"": "1f51e",
				"": "a9",
				"": "ae",
				"": "2122",
				"[ｉ]": "2139",
				"": "2320e3",
				"": "3120e3",
				"": "3220e3",
				"": "3320e3",
				"": "3420e3",
				"": "3520e3",
				"": "3620e3",
				"": "3720e3",
				"": "3820e3",
				"": "3920e3",
				"": "3020e3",
				"[10]": "1f51f",
				"": "1f4f6",
				"": "1f4f3",
				"": "1f4f4",
				"": "1f354",
				"": "1f359",
				"": "1f370",
				"": "1f35c",
				"": "1f35e",
				"": "1f373",
				"": "1f366",
				"": "1f35f",
				"": "1f361",
				"": "1f358",
				"": "1f35a",
				"": "1f35d",
				"": "1f35b",
				"": "1f362",
				"": "1f363",
				"": "1f371",
				"": "1f372",
				"": "1f367",
				"[肉]": "1f356",
				"[なると]": "1f365",
				"[やきいも]": "1f360",
				"[ピザ]": "1f355",
				"[チキン]": "1f357",
				"[アイスクリーム]": "1f368",
				"[ドーナツ]": "1f369",
				"[クッキー]": "1f36a",
				"[チョコ]": "1f36b",
				"[キャンディ]": "1f36d",
				"[プリン]": "1f36e",
				"[ハチミツ]": "1f36f",
				"[エビフライ]": "1f364",
				"": "1f374",
				"": "2615",
				"": "1f379",
				"": "1f37a",
				"": "1f375",
				"": "1f37b",
				"": "2934",
				"": "2935",
				"": "2196",
				"": "2199",
				"⇔": "2194",
				"↑↓": "1f503",
				"": "2b06",
				"": "2b07",
				"": "27a1",
				"": "1f519",
				"": "25b6",
				"": "25c0",
				"": "23e9",
				"": "23ea",
				"▲": "1f53c",
				"▼": "1f53d",
				"": "2b55",
				"": "2716",
				"": "2757",
				"！？": "2049",
				"！！": "203c",
				"": "2753",
				"": "2754",
				"": "2755",
				"～": "27b0",
				"": "27bf",
				"": "2764",
				"": "1f49e",
				"": "1f494",
				"": "1f497",
				"": "1f498",
				"": "1f499",
				"": "1f49a",
				"": "1f49b",
				"": "1f49c",
				"": "1f49d",
				"": "1f49f",
				"": "2665",
				"": "2660",
				"": "2666",
				"": "2663",
				"": "1f6ac",
				"": "1f6ad",
				"": "267f",
				"[旗]": "1f6a9",
				"": "26a0",
				"": "1f6b2",
				"": "1f6b6",
				"": "1f6b9",
				"": "1f6ba",
				"": "1f6c0",
				"": "1f6bb",
				"": "1f6bd",
				"": "1f6be",
				"": "1f6bc",
				"[ドア]": "1f6aa",
				"[禁止]": "1f6ab",
				"[チェックマーク]": "2705",
				"[CL]": "1f191",
				"": "1f192",
				"[FREE]": "1f193",
				"": "1f194",
				"": "1f195",
				"[NG]": "1f196",
				"": "1f197",
				"[SOS]": "1f198",
				"": "1f199",
				"": "1f19a",
				"": "1f201",
				"": "1f202",
				"[禁]": "1f232",
				"": "1f233",
				"[合]": "1f234",
				"": "1f235",
				"": "1f236",
				"": "1f21a",
				"": "1f237",
				"": "1f238",
				"": "1f239",
				"": "1f22f",
				"": "1f23a",
				"": "3299",
				"": "3297",
				"": "1f250",
				"[可]": "1f251",
				"[＋]": "2795",
				"[－]": "2796",
				"[÷]": "2797",
				"": "1f4a1",
				"": "1f4a2",
				"": "1f4a3",
				"": "1f4a4",
				"[ドンッ]": "1f4a5",
				"": "1f4a7",
				"": "1f4a8",
				"": "1f4a9",
				"": "1f4aa",
				"[フキダシ]": "1f4ac",
				"": "2747",
				"": "2734",
				"": "2733",
				"": "1f534",
				"": "25fc",
				"": "1f539",
				"": "2b50",
				"[花丸]": "1f4ae",
				"[100点]": "1f4af",
				"←┘": "21a9",
				"└→": "21aa",
				"": "1f50a",
				"[電池]": "1f50b",
				"[コンセント]": "1f50c",
				"": "1f50e",
				"": "1f510",
				"": "1f513",
				"": "1f511",
				"": "1f514",
				"[ラジオボタン]": "1f518",
				"[ブックマーク]": "1f516",
				"[リンク]": "1f517",
				"[end]": "1f51a",
				"[ON]": "1f51b",
				"[SOON]": "1f51c",
				"": "1f51d",
				"": "270a",
				"": "270c",
				"": "1f44a",
				"": "1f44d",
				"": "261d",
				"": "1f446",
				"": "1f447",
				"": "1f448",
				"": "1f449",
				"": "1f44b",
				"": "1f44f",
				"": "1f44c",
				"": "1f44e",
				"": "1f450"
			},
			s = {
				"/微笑": "0",
				"/撇嘴": "1",
				"/色": "2",
				"/发呆": "3",
				"/得意": "4",
				"/流泪": "5",
				"/害羞": "6",
				"/闭嘴": "7",
				"/睡": "8",
				"/大哭": "9",
				"/尴尬": "10",
				"/发怒": "11",
				"/调皮": "12",
				"/呲牙": "13",
				"/惊讶": "14",
				"/难过": "15",
				"/酷": "16",
				"/冷汗": "17",
				"/抓狂": "18",
				"/吐": "19",
				"/偷笑": "20",
				"/可爱": "21",
				"/白眼": "22",
				"/傲慢": "23",
				"/饥饿": "24",
				"/困": "25",
				"/惊恐": "26",
				"/流汗": "27",
				"/憨笑": "28",
				"/大兵": "29",
				"/奋斗": "30",
				"/咒骂": "31",
				"/疑问": "32",
				"/嘘": "33",
				"/晕": "34",
				"/折磨": "35",
				"/衰": "36",
				"/骷髅": "37",
				"/敲打": "38",
				"/再见": "39",
				"/擦汗": "40",
				"/抠鼻": "41",
				"/鼓掌": "42",
				"/糗大了": "43",
				"/坏笑": "44",
				"/左哼哼": "45",
				"/右哼哼": "46",
				"/哈欠": "47",
				"/鄙视": "48",
				"/委屈": "49",
				"/快哭了": "50",
				"/阴险": "51",
				"/亲亲": "52",
				"/吓": "53",
				"/可怜": "54",
				"/菜刀": "55",
				"/西瓜": "56",
				"/啤酒": "57",
				"/篮球": "58",
				"/乒乓": "59",
				"/咖啡": "60",
				"/饭": "61",
				"/猪头": "62",
				"/玫瑰": "63",
				"/凋谢": "64",
				"/示爱": "65",
				"/爱心": "66",
				"/心碎": "67",
				"/蛋糕": "68",
				"/闪电": "69",
				"/炸弹": "70",
				"/刀": "71",
				"/足球": "72",
				"/瓢虫": "73",
				"/便便": "74",
				"/月亮": "75",
				"/太阳": "76",
				"/礼物": "77",
				"/拥抱": "78",
				"/强": "79",
				"/弱": "80",
				"/握手": "81",
				"/胜利": "82",
				"/抱拳": "83",
				"/勾引": "84",
				"/拳头": "85",
				"/差劲": "86",
				"/爱你": "87",
				"/NO": "88",
				"/OK": "89",
				"/爱情": "90",
				"/飞吻": "91",
				"/跳跳": "92",
				"/发抖": "93",
				"/怄火": "94",
				"/转圈": "95",
				"/磕头": "96",
				"/回头": "97",
				"/跳绳": "98",
				"/挥手": "99",
				"/激动": "100",
				"/街舞": "101",
				"/献吻": "102",
				"/左太极": "103",
				"/右太极": "104",
				"/::)": "0",
				"/::~": "1",
				"/::B": "2",
				"/::|": "3",
				"/:8-)": "4",
				"/::<": "5",
				"/::$": "6",
				"/::X": "7",
				"/::Z": "8",
				"/::(": "9",
				"/::'(": "9",
				"/::-|": "10",
				"/::@": "11",
				"/::P": "12",
				"/::D": "13",
				"/::O": "14",
				"/::(": "15",
				"/::+": "16",
				"/:--b": "17",
				"/::Q": "18",
				"/::T": "19",
				"/:,@P": "20",
				"/:,@-D": "21",
				"/::d": "22",
				"/:,@o": "23",
				"/::g": "24",
				"/:|-)": "25",
				"/::!": "26",
				"/::L": "27",
				"/::>": "28",
				"/::,@": "29",
				"/:,@f": "30",
				"/::-S": "31",
				"/:?": "32",
				"/:,@x": "33",
				"/:,@@": "34",
				"/::8": "35",
				"/:,@!": "36",
				"/:!!!": "37",
				"/:xx": "38",
				"/:bye": "39",
				"/:wipe": "40",
				"/:dig": "41",
				"/:handclap": "42",
				"/:&-(": "43",
				"/:B-)": "44",
				"/:<@": "45",
				"/:@>": "46",
				"/::-O": "47",
				"/:>-|": "48",
				"/:P-(": "49",
				"/::'|": "50",
				"/:X-)": "51",
				"/::*": "52",
				"/:@x": "53",
				"/:8*": "54",
				"/:pd": "55",
				"/:<W>": "56",
				"/:beer": "57",
				"/:basketb": "58",
				"/:oo": "59",
				"/:coffee": "60",
				"/:eat": "61",
				"/:pig": "62",
				"/:rose": "63",
				"/:fade": "64",
				"/:showlove": "65",
				"/:heart": "66",
				"/:break": "67",
				"/:cake": "68",
				"/:li": "69",
				"/:bome": "70",
				"/:kn": "71",
				"/:footb": "72",
				"/:ladybug": "73",
				"/:shit": "74",
				"/:moon": "75",
				"/:sun": "76",
				"/:gift": "77",
				"/:hug": "78",
				"/:strong": "79",
				"/:weak": "80",
				"/:share": "81",
				"/:v": "82",
				"/:@)": "83",
				"/:jj": "84",
				"/:@@": "85",
				"/:bad": "86",
				"/:lvu": "87",
				"/:no": "88",
				"/:ok": "89",
				"/:love": "90",
				"/:<L>": "91",
				"/:jump": "92",
				"/:shake": "93",
				"/:<O>": "94",
				"/:circle": "95",
				"/:kotow": "96",
				"/:turn": "97",
				"/:skip": "98",
				"/:oY": "99",
				"/:#-0": "100",
				"/:hiphot": "101",
				"/:kiss": "102",
				"/:<&": "103",
				"/:&>": "104"
			},
			o = '<span class="emoji emoji%s"></span>',
			u = wx.resPath + "/mpres/htmledition/images/icon/emotion/",
			a = '<img src="' + u + '%s.gif" width="24" height="24">';
		String.prototype.emoji = function() {
			var e = this.toString();
			for (var t in i)
				while (-1 != e.indexOf(t)) e = e.replace(t, o.sprintf(i[t]));
			for (var t in s)
				while (-1 != e.indexOf(t)) e = e.replace(t, a.sprintf(s[t]));
			return e;
		};
	} catch (f) {
		wx.jslog({
			src: "common/qq/emoji.js"
		}, f);
	}
});
! function() {
	if (!window.define) {
		var t = {};
		window.define = function(e, i, n) {
			t[e] = n;
		}, window.seajs = {
			use: function(e) {
				function i(e) {
					return t[e] && t[e](i);
				}
				return t[e] && t[e](i);
			}
		};
	}
}(), define("biz_common/utils/norefererimg.js", [], function() {
	function t(t) {
		return window.getComputedStyle ? window.getComputedStyle(t) : t.currentStyle;
	}

	function e(t, e, i, n) {
		if (t && i) {
			if (t == window && "load" == e && /complete|loaded/.test(document.readyState)) return void i({
				type: "load"
			});
			var o = function(t) {
				var e = i.call(this, t);
				e === !1 && (t.stopPropagation && t.stopPropagation(), t.preventDefault && t.preventDefault());
			};
			return i[e + "_handler"] = o, t.addEventListener ? void t.addEventListener(e, o, !!n) : t.attachEvent ? void t.attachEvent("on" + e, o, !!n) : void 0;
		}
	}
	return function(i) {
		var n = i.img,
			o = i.imgurl,
			r = i.onload,
			d = o || n.getAttribute("data-src"),
			a = /^http:\/\/mmbiz\.qpic\.cn\/|https:\/\/mmbiz\.qlogo\.cn\//;
		if (d) {
			if (n.length && (n = n[0]), a.test(d)) return e(n, "load", r), void n.setAttribute("src", d);
			var s = t(n),
				l = ["javascript:\"<html><body style='margin:0;padding:0;'><img style='width:", s.width, ";height:", s.height, ";' src='", d + "' /></body></html>\""].join(" "),
				u = document.createElement("iframe");
			u.setAttribute("frameBorder", "0"), u.setAttribute("scrolling", "no"), u.style.width = "60px",
				u.style.width = s.width + "", u.style.height = s.height, u.style.display = s.display, u.style.borderRadius = s.borderRadius,
				u.style.webkitBorderRadius = s.borderRadius, "function" == typeof r && (u.attachEvent ? u.attachEvent("onload", r) : u.onload = r),
				u.className = n.className;
			var c = n.parentNode;
			c.insertBefore(u, n), u.src = l, c.removeChild(n);
		}
	};
});
define("tpl/media/dialog/file.html.js", [], function() {
	return '{each list as item}\n{mediaDialogInit item}\n<li class="media_item">\n    <div class="media_info">\n        <label class="media_name frm_radio_label">\n        	<i class="icon_radio"></i>\n        	<input name="media" type="radio" class="frm_radio" value="{item.file_id}">\n            {item.name}\n        </label>\n        <span class="media_size">{item.size}</span>\n        <span class="media_time">{mediaDialogtimeFormat item.update_time}</span>\n    </div>\n    <div id="fileItem{item.file_id}" data-id="{item.file_id}" data-type="{item.type}" class="media_content"></div>\n</li>\n{/each}\n';
});
define("tpl/media/dialog/videomsg_layout.html.js", [], function() {
	return '<div class="dialog_media_container">\n    <div class="sub_title_bar in_dialog">\n        <div class="title_tab js_videotab"></div>\n        <div class="search_bar dn">\n            <span class="frm_input_box search with_del append">\n                <a class="del_btn" href="javascript:"><i class="icon_search_del"></i>&nbsp;</a>\n                <a id="msgSearchBtn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">搜索</i>&nbsp;</a>\n                <input id="msgSearchInput" type="text" placeholder="关键字" value="" class="frm_input">\n            </span>\n        </div>\n        <div class="richvideo_create js_video_create {if scene != \'default\'}dn{/if}">\n            <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&type=15&lang=zh_CN&token={token}">\n                <i class="icon_richvideo_small"></i><strong>新建视频消息</strong>\n            </a>\n        </div>\n    </div>\n    <div class="js_video_status js_video_content dn">\n        <div class="richvideo_list media_dialog" id="js_videomsg_list">\n            <div class="richvideo_col"><div class="inner"></div></div>&nbsp;\n            <div class="richvideo_col"><div class="inner"></div></div>\n        </div>\n    </div>\n    <div class="js_video_status js_video_tencent dn">\n        <div class="video">\n            <div class="frm_control_group">\n                <label for="" class="frm_label">视频网址</label>\n                <div class="frm_controls">\n                    <span class="frm_input_box"><input type="text" class="frm_input js_video_txurl"></span>\n                    <p class="frm_tips">支持腾讯视频和微视频</p>\n                </div>\n            </div>\n			<div class="video_preview js_video_preview"></div>\n		</div>\n    </div>\n    <div class="js_video_status js_video_loading">\n        <i class="icon_loading_small white">loading...</i>\n    </div>\n    <div class="js_video_status js_video_none dn">\n        <div class="no_media_wrp">\n            <p class="empty_tips js_empty_tips"></p>\n            <!--\n            <div class="richvideo_create js_video_create">\n                <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/videomsg_edit&action=video_edit&type=15&lang=zh_CN&token={token}">\n                    <i class="icon_richvideo_small"></i><strong>新建视频消息</strong>\n                </a>\n            </div>\n            -->\n        </div>\n        <span class="vm_box"></span>\n    </div>\n    \n    <div class="pagination_wrp pageNavigator js_pagebar"></div>\n</div>\n';
});
define("tpl/media/dialog/appmsg_layout.html.js", [], function() {
	return '<div class="dialog_media_container appmsg_media_dialog">\n    <div class="sub_title_bar in_dialog">\n        <div class="search_bar">\n            <span class="frm_input_box search with_del append">\n                <a class="del_btn" href="javascript:" id="searchCloseBt"><i class="icon_search_del"></i>&nbsp;</a>\n                <a id="msgSearchBtn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">搜索</i>&nbsp;</a>\n                <input id="msgSearchInput" type="text" placeholder="标题/作者/摘要" value="" class="frm_input">\n            </span>\n        </div>\n        <div class="appmsg_create tr">\n            {if type==10}\n            <!--\n            <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&lang=zh_CN&token={token}">\n                <i class="icon_appmsg_small"></i><strong>新建单图文消息</strong>\n            </a>\n            -->\n            <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=1&lang=zh_CN&token={token}">\n                <i class="icon_appmsg_small multi"></i><strong>新建图文消息</strong>\n            </a>\n            {else if type==11}\n            <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&lang=zh_CN&token={token}">\n                <i class="icon_shopmsg_small"></i><strong>新建单商品消息</strong>\n            </a>\n            <a class="" target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=11&isMul=1&lang=zh_CN&token={token}">\n                <i class="icon_shopmsg_small multi"></i><strong>新建多商品消息</strong>\n            </a>\n            {/if}\n        </div>\n    </div>\n    <div class="dialog_media_inner">\n        {if tpl=="loading"}\n        <i class="icon_loading_small white">loading...</i>\n        {else if tpl=="no-media"}\n        <div class="no_media_wrp">\n            <p class="tips">暂无素材</p>\n        </div>\n        <span class="vm_box"></span>\n        {else}\n        <div class="js_appmsg_list appmsg_list media_dialog">\n            <div class="appmsg_col"><div class="inner"></div></div>&nbsp;\n            <div class="appmsg_col"><div class="inner"></div></div>\n        </div>\n        <div class="pagination_wrp pageNavigator"></div>\n        {/if}\n    </div>\n</div>\n';
});
define("tpl/media/dialog/file_layout.html.js", [], function() {
	return '<div class=\'dialog_media_container {if tpl=="no-media"}no_media{/if}\'>\n    {if tpl=="loading"}\n    <i class="icon_loading_small white">loading...</i>\n    {else if tpl=="no-media"}\n    <div class="no_media_wrp">\n        <p class="tips">\n        暂无素材        </p>\n        <div class="upload_box">\n            <span class="upload_area"><a id="js_media_dialog_upload{upload_id}" class="btn btn_upload" data-gid="">上传</a></span>\n            <div class="bubble_tips bubble_right warn">\n                <div class="bubble_tips_inner">\n                    {if type=="2"}\n                        大小: 不超过2M,&nbsp;&nbsp;&nbsp;&nbsp;格式: bmp, png, jpeg, jpg, gif                    {/if}\n                    {if type=="3"}\n                        大小: 不超过5M,&nbsp;&nbsp;&nbsp;&nbsp;长度: 不超过60s,&nbsp;&nbsp;&nbsp;&nbsp;格式: mp3, wma, wav, amr                    {/if}\n                    {if type=="4"}\n                        大小: 不超过20M,&nbsp;&nbsp;&nbsp;&nbsp;格式: rm, rmvb, wmv, avi, mpg, mpeg, mp4                    {/if}\n                </div> \n                <i class="bubble_tips_arrow out"></i>\n                <i class="bubble_tips_arrow in"></i>\n            </div>\n        </div>\n    </div>\n    <span class="vm_box"></span>\n    {else}\n    <div class="sub_title_bar in_dialog">\n        <div class="search_bar dn">\n            <span class="frm_input_box search with_del append">\n                <a class="del_btn" href="javascript:"><i class="icon_search_del"></i>&nbsp;</a>\n                <a id="msgSearchBtn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">搜索</i>&nbsp;</a>\n                <input id="msgSearchInput" type="text" placeholder="关键字" value="" class="frm_input">\n            </span>\n        </div>\n        <div class="upload_box">\n            <span class="upload_area"><a id="js_media_dialog_upload{upload_id}" class="btn btn_upload" data-gid="">上传</a></span>&nbsp;\n            <div class="bubble_tips bubble_left warn">\n                <div class="bubble_tips_inner">\n                    {if type=="2"}\n                        大小: 不超过2M,&nbsp;&nbsp;&nbsp;&nbsp;格式: bmp, png, jpeg, jpg, gif                    {/if}\n                    {if type=="3"}\n                        大小: 不超过5M,&nbsp;&nbsp;&nbsp;&nbsp;长度: 不超过60s,&nbsp;&nbsp;&nbsp;&nbsp;格式: mp3, wma, wav, amr                    {/if}\n                    {if type=="4"}\n                        大小: 不超过20M,&nbsp;&nbsp;&nbsp;&nbsp;格式: rm, rmvb, wmv, avi, mpg, mpeg, mp4                    {/if}\n                </div>\n                <i class="bubble_tips_arrow out"></i>\n                <i class="bubble_tips_arrow in"></i>\n            </div>\n        </div>&nbsp;\n    </div>\n    <ul class=\'dialog_media_list js_media_list\'></ul>\n    <div class="pagination_wrp pageNavigator"></div>\n    {/if}\n</div>\n';
});
define("common/wx/time.js", [], function() {
	"use strict";

	function e(e) {
		var t = new Date(1e3 * e),
			r = new Date,
			g = t.getTime(),
			a = r.getTime(),
			u = 864e5;
		return u > a - g && r.getDate() == t.getDate() ? "%s:%s".sprintf(n(t.getHours()), n(t.getMinutes())) : 2 * u > a - g && new Date(1 * t + u).getDate() == r.getDate() ? "昨天 %s:%s".sprintf(n(t.getHours()), n(t.getMinutes())) : 6 * u >= a - g ? "%s %s:%s".sprintf(s[t.getDay()], n(t.getHours()), n(t.getMinutes())) : t.getFullYear() == r.getFullYear() ? "%s月%s日".sprintf(n(t.getMonth() + 1), n(t.getDate())) : "%s年%s月%s日".sprintf(t.getFullYear(), n(t.getMonth() + 1), n(t.getDate()));
	}

	function t(e) {
		var t = new Date(1e3 * e);
		return "%s-%s-%s %s:%s:%s".sprintf(t.getFullYear(), n(t.getMonth() + 1), n(t.getDate()), n(t.getHours()), n(t.getMinutes()), n(t.getSeconds()));
	}

	function r(e, t) {
		var r = ["日", "一", "二", "三", "四", "五", "六"],
			n = t.replace(/yyyy|YYYY/, e.getFullYear()).replace(/yy|YY/, g(e.getFullYear() % 100, 2)).replace(/mm|MM/, g(e.getMonth() + 1, 2)).replace(/m|M/g, e.getMonth() + 1).replace(/dd|DD/, g(e.getDate(), 2)).replace(/d|D/g, e.getDate()).replace(/hh|HH/, g(e.getHours(), 2)).replace(/h|H/g, e.getHours()).replace(/ii|II/, g(e.getMinutes(), 2)).replace(/i|I/g, e.getMinutes()).replace(/ss|SS/, g(e.getSeconds(), 2)).replace(/s|S/g, e.getSeconds()).replace(/w/g, e.getDay()).replace(/W/g, r[e.getDay()]);
		return n;
	}

	function g(e, t) {
		for (var r = 0, g = t - (e + "").length; g > r; r++) e = "0" + e;
		return e + "";
	}
	var n = function(e) {
			return e += "", e.length >= 2 ? e : "0" + e;
		},
		s = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
	return {
		timeFormat: e,
		getFullTime: t,
		formatDate: r
	};
});
define("cardticket/send_card.js", ["common/wx/popup.js", "common/wx/Tips.js", "common/wx/Cgi.js", "common/wx/Step.js", "common/wx/pagebar.js", "cardticket/parse_data.js", "cardticket/store_cgi.js", "cardticket/common_template_helper.js", "tpl/cardticket/card_table.html.js", "tpl/cardticket/card_preview.html.js", "page/cardticket/dialog_choose_card.css", "biz_web/ui/checkbox.js", "cardticket/card_quantity.js", "tpl/cardticket/send_card.html.js"], function(t) {
	"use strict";

	function e(t) {
		{
			var e;
			t.opt;
		}
		e = t.$send_popup.popup("get"), e.find(".js_card_list").html(v({
			loading: !0
		}));
	}

	function a(t, a) {
		var i = a.opt,
			n = $.extend(!0, {
				action: "batch",
				begin: t.begin,
				count: t.count
			}, i.param);
		j = !0, e(a), s.get({
			url: i.url || "/merchant/electroniccardmgr",
			data: n,
			complete: function() {
				j = !1;
			}
		}, function(t) {
			if (0 == t.base_resp.ret) {
				t = "string" == typeof t.batch_card ? $.parseJSON(t.batch_card) : t.batch_card, i.data = t.card_list;
				var e = u.parse_cardlist(i.data);
				m = e.card_cache, i.data = e.card_list, i.pageInfo.total_count = t.total_num, o(i.pageInfo, a);
			} else s.show(t);
		});
	}

	function o(t, e, a) {
		var o, p = e.opt;
		if (p.payflag = p.param.flag, o = e.$send_popup.popup("get"), a) {
			var s = o.find(".js_select");
			return s.each(function(e) {
				e >= t.begin && e < t.begin + t.count ? $(this).closest("tr").show() : $(this).closest("tr").hide();
			}), e.$send_popup.popup("resetPosition"), e.pagebar = null, void r(p.pageInfo, e);
		}
		o.find(".js_card_list").html(v(p));
		var d = p.defaultValues,
			s = o.find(".js_select");
		d.length && s.each(function() {
			for (var t = $(this), e = 0; e < d.length; e++)
				if (d[e] == t.attr("data-id")) {
					t.prop("checked", !0);
					break;
				}
		}), e.select_card_checkbox = s.checkbox({
			onChanged: function() {
				if (p.multi) {
					var t = 0;
					s.each(function() {
						$(this).prop("checked") && t++;
					}), $(".js_selectcount", o).text(t);
				}
			}
		}), e.pagebar = null, r(p.pageInfo, e), i(e), n(e), c(e), e.$send_popup.popup("resetPosition");
	}

	function i(t) {
		function e(e) {
			var o = $.trim(n.val());
			(!e || e && wx.isHotkey(e, "enter")) && (c.param.keyword = o, a(c.pageInfo, t));
		}
		var o = t.$send_popup.popup("get"),
			i = $(".js_search", o),
			n = $(".js_keyword", o),
			c = t.opt;
		i.click(function() {
			e();
		}), n.keyup(function(t) {
			e(t);
		}), n.val(c.param.keyword);
	}

	function n(t) {
		var e = t.$send_popup.popup("get"),
			a = e.find(".js_card_preview");
		a.on("click", function(t) {
			var e = $(this).data("cid"),
				a = m[e];
			if (a) {
				var o = ($(this), $(this).offset());
				$(".js_pop_card_preview").remove();
				var i = $(h({
					card: a
				})).appendTo(document.body).hide();
				i.css({
					position: "fixed",
					left: o.left - i.outerWidth() - 10,
					top: "50%",
					"margin-top": -1 * i.outerHeight() / 2,
					"z-index": "10000"
				}).show();
				var n = i.offset();
				return i.find(".js_arrow").css({
					top: o.top - n.top
				}), $(document).one("click", function() {
					i.remove();
				}), t.stopPropagation(), !1;
			}
		});
	}

	function c(t) {
		var e = t.$send_popup.popup("get"),
			a = e.find(".js_modify_quantity");
		a.each(function() {
			var t = $(this),
				e = 1 * t.attr("data-new") || 0;
			new k({
				container: t,
				setquantity: e ? !0 : !1,
				quantityChange: function(e, a) {
					var o = m[e];
					o && (o.quantity = this.opt.setquantity ? o.quantity + a : a, t.attr("data-new", 1), o.isnew = !0,
						this.opt.setquantity = !0, $("#js_ct_tr_" + e).find(".js_sendcard_quantity").text(o.quantity));
				}
			});
		});
	}

	function r(t, e) {
		var i = t.total_count,
			n = e.$send_popup.popup("get");
		if (t.count && i > t.count) {
			var c = t.begin / t.count;
			e.pagebar = new d({
				container: $(".js_pager", n),
				first: !1,
				last: !1,
				midRange: 5,
				initShowPage: c + 1,
				perPage: t.count,
				totalItemsNum: i,
				callback: function(i) {
					if (j) return !1;
					var n = i.currentPage;
					return n != c + 1 && (t.begin = (n - 1) * t.count, e.opt.hasdata && e.opt.data ? o(t, e, !0) : a(t, e)),
						e.$send_popup.popup("resetPosition"), !0;
				}
			});
		}
	}
	var p = (t("common/wx/popup.js"), t("common/wx/Tips.js")),
		s = t("common/wx/Cgi.js"),
		d = (t("common/wx/Step.js"),
			t("common/wx/pagebar.js")),
		u = t("cardticket/parse_data.js"),
		l = t("cardticket/store_cgi.js"),
		_ = (t("cardticket/common_template_helper.js"), {
			multi: !1,
			pageInfo: {
				begin: 0,
				count: 5,
				total_count: 0
			},
			param: {
				keyword: "",
				status: "3|6",
				flag: 2
			},
			neednew: !0,
			noexpire: !0,
			editquantity: !0,
			onHide: $.noop,
			selectComplete: $.noop,
			data: null,
			hasdata: !1,
			maxcount: 10,
			defaultValues: [],
			url: "",
			removeOnHide: !0,
			source: ""
		}),
		f = t("tpl/cardticket/card_table.html.js"),
		h = template.compile(t("tpl/cardticket/card_preview.html.js")),
		m = {};
	t("page/cardticket/dialog_choose_card.css"), t("biz_web/ui/checkbox.js");
	var g = function(t) {
			this.opt = $.extend(!0, {}, _, t), this.init();
		},
		v = template.compile(f),
		j = !1,
		k = t("cardticket/card_quantity.js");
	return g.prototype = {
		_html: t("tpl/cardticket/send_card.html.js"),
		init: function() {
			var t = this.opt,
				e = this,
				i = $(template.compile(this._html)()).popup({
					title: "选择卡券",
					autoShow: !1,
					buttons: [{
						text: "确定",
						type: "primary",
						click: function(a) {
							if (!j) {
								var o = e.select_card_checkbox.values()[0],
									i = this,
									n = i.get(),
									c = m[o];
								if (!o || !c) return void p.err("请选择卡券");
								if (t.multi)
									for (var r = e.select_card_checkbox.values(), s = 0; s < r.length; s++) {
										var d = m[r[s]];
										if (t.neednew && (!d.isnew || 0 == d.quantity)) return p.err("卡券库存不能为0，请先设置库存再投放"), void a.stopPropagation();
									} else if (t.neednew && (!c.isnew || 0 == c.quantity)) {
										p.err("请先设置库存再投放卡券");
										var u = n.find("input[data-id=" + o + "]");
										return $(u.closest("tr").find(".js_modify_quantity")[0]).click(), void a.stopPropagation();
									}
								if (!t.multi && t.noexpire && c.is_expire) return void p.err("卡券已过期，无法投放，请到卡券详情去延长有效期再投放");
								if (t.multi && t.noexpire)
									for (var r = e.select_card_checkbox.values(), s = 0; s < r.length; s++) {
										var d = m[r[s]];
										if (d.is_expire) return p.err("不能选择已过期的卡券，请先到卡券详情去延长有效期"), void a.stopPropagation();
									}
								var r = e.select_card_checkbox.values();
								return r.length > t.maxcount ? (p.err("最多只能选择%s个卡券".sprintf(t.maxcount)), void a.stopPropagation()) : void l.canSendCard({
									card_id: o,
									success: function(a) {
										if (a === !1) p.err("没有“审核通过”的门店。确认有至少一个“审核通过”的门店后可进行投放。");
										else if (a === !0) {
											var o = e.select_card_checkbox.values(),
												n = t.multi ? o : o,
												c = [];
											if (t.multi)
												for (var r = 0; r < n.length; r++) m[n[r]].cardid = m[n[r]].id, c.push(m[n[r]]);
											else c = m[n],
												c.cardid = m[n].id;
											t.selectComplete && t.selectComplete(c, 0), i.hide();
										}
									}
								});
							}
						}
					}, {
						text: "取消",
						type: "default",
						click: function() {
							j || this.hide();
						}
					}],
					onHide: function() {
						t.onHide.call(e), t.removeOnHide && this.remove();
					},
					className: "send_card align_edge",
					width: 960
				});
			if (this.$send_popup = i, t.hasdata && t.data) {
				t.pageInfo.total_count = t.data.length, m = {};
				for (var n = 0; n < t.data.length; n++) {
					var c = t.data[n];
					m[c.id] = c;
				}
				o(t.pageInfo, this);
			} else a(t.pageInfo, this);
		},
		show: function() {
			this.$send_popup.popup("show"), this.$send_popup.popup("resetPosition");
		},
		hide: function() {
			this.$send_popup.popup("hide");
		},
		destroy: function() {
			this.$send_popup.popup("remove");
		}
	}, g;
});
define("common/wx/media/videoDialog.js", ["common/wx/popup.js", "page/smallvideo/dialog_select_video.css", "widget/media/media_dialog.css", "common/wx/top.js", "common/wx/Tips.js", "common/wx/media/video.js", "common/wx/pagebar.js", "media/media_cgi.js", "tpl/media/dialog/videomsg_layout.html.js"], function(e) {
	"use strict";

	function i(e, i, t) {
		e = e.replace(/^\s+|\s+$/g, ""), e = e.replace(/^v\.qq\.com/, "http://v.qq.com"), -1 != e.indexOf("http://v.qq.com") ? n(e, i, t) : (-1 != e.indexOf("http://www.weishi.com") || -1 != e.indexOf("http://weishi.com") || -1 != e.indexOf("http://www.weishi.qq.com") || -1 != e.indexOf("http://weishi.qq.com")) && d(e, i, t);
	}

	function t(e, i) {
		var i = i || document.location.toString(),
			t = e + "=",
			o = i.indexOf(t);
		if (-1 != o) {
			var n = i.indexOf("&", o),
				d = i.indexOf("?", o);
			return -1 != d && (-1 == n || n > d) && (n = d), d = i.indexOf("#", o), -1 != d && (-1 == n || n > d) && (n = d), -1 == n ? i.substring(o + t.length) : i.substring(o + t.length, n);
		}
		return "";
	}

	function o(e) {
		e = e || window.location.toString();
		var i, o = t("vid", e);
		return o || (i = e.match(/\/\w{15}\/(\w+)\.html/)) && (o = i[1]), o || ((i = e.match(/\/page\/\w{1}\/\w{1}\/\w{1}\/(\w+)\.html/)) ? o = i[1] : (i = e.match(/\/(page|play)\/+(\w{11})\.html/)) && (o = i[2])),
			o || (i = e.match(/\/boke\/gplay\/\w+_\w+_(\w+)\.html/)) && (o = i[1]), encodeURIComponent(o);
	}

	function n(e, i, t) {
		var n, d, s = "",
			a = t.width,
			c = t.height;
		if (n = e.match(new RegExp("(^|&|\\\\?)vid=([^&]*)(&|$|#)"))) s = encodeURIComponent(n[2]),
			t.vid = s, d = "http://v.qq.com/iframe/player.html?vid=" + s + "&width=" + a + "&height=" + c + "&auto=0",
			i(d, t);
		else if ((n = e.match(new RegExp("(http://)?v\\.qq\\.com/cover[^/]*/\\w+/([^/]*)\\.html"))) || (n = e.match(new RegExp("(http://)?v\\.qq\\.com/prev[^/]*/\\w+/([^/]*)\\.html")))) {
			var r = encodeURIComponent(n[2]),
				m = "https://sec.video.qq.com/p/sns.video/fcgi-bin/dlib/dataout_ex?auto_id=137&cid=" + r + "&otype=json",
				l = document.getElementsByTagName("head")[0],
				h = document.createElement("script");
			h.type = "text/javascript", h.src = m, h.async = !0, void 0 !== h.onreadystatechange ? h.onreadystatechange = function() {
				if ("loaded" == h.readyState) try {
					s = QZOutputJson.videos[0].vid, t.vid = s, d = "http://v.qq.com/iframe/player.html?vid=" + s + "&width=" + a + "&height=" + c + "&auto=0",
						i(d, t);
				} catch (e) {}
			} : h.onload = function() {
				try {
					s = QZOutputJson.videos[0].vid, t.vid = s, d = "http://v.qq.com/iframe/player.html?vid=" + s + "&width=" + a + "&height=" + c + "&auto=0",
						i(d, t);
				} catch (e) {}
			}, l.appendChild(h);
		} else s = o(e), "" != s && (t.vid = s, d = "http://v.qq.com/iframe/player.html?vid=" + s + "&width=" + a + "&height=" + c + "&auto=0",
			i(d, t));
	}

	function d(e, i, t) {
		var o, n = "",
			d = t.width,
			s = t.height,
			c = e.match(/\/t\/(\d+)/) || e.match(/\/#show\/(\d+)/);
		c && "object" == typeof c && c.length > 1 ? (n = c[1], t.vid = n, o = "http://z.weishi.com/weixin/player.html?msgid=" + n + "&width=" + d + "&height=" + s + "&auto=0",
			i(o, t)) : a.err("微视频仅支持 www.weishi.com/t 开头的网址");
	}
	e("common/wx/popup.js"), e("page/smallvideo/dialog_select_video.css"), e("widget/media/media_dialog.css");
	var s = e("common/wx/top.js"),
		a = e("common/wx/Tips.js"),
		c = e("common/wx/media/video.js"),
		r = e("common/wx/pagebar.js"),
		m = e("media/media_cgi.js"),
		l = e("tpl/media/dialog/videomsg_layout.html.js"),
		h = 15,
		p = 21,
		v = {};
	v[h] = "video_msg_cnt", v[p] = "short_video_cnt";
	var f = function(e, i) {
			var t = $.extend({}, i);
			return t.selector = e, t.id = t.app_id, t.tpl = "videomsg", t.for_selection = !0, t.for_transfer = !!t.content,
				t.hide_transfer = !!t.content, t.video_id = t.content, t.source = "file", new c(t);
		},
		u = 548,
		_ = 280,
		g = function(e) {
			this.scene = e.scene || "default", this.onOK = e.onOK, this.can_use_shortvideo = e.can_use_shortvideo,
				this.create();
		},
		w = {
			create: function() {
				var e = this,
					t = $.parseHTML(wx.T(l, {
						scene: e.scene,
						token: wx.data.t
					}));
				e.dialog && e.dialog.popup("remove"), e.dialog = $(t[0]).popup({
						title: "选择视频",
						className: "dialog_select_video",
						width: 960,
						onOK: function() {
							var t = this,
								o = e.$dom.find(".js_top.selected").data("id"),
								n = e.$dom.find(".Js_videomsg.selected").parent().data("opt"),
								d = e.$dom.find(".js_video_txurl").val();
							if (o) {
								if (!n) return a.err("请选择视频"), !0;
								if (e.onOK && !e.onOK(o, n)) return !0;
								t.remove(), e.dialog = null;
							} else {
								if (!d) return a.err("请输入视频网址"), !0;
								var s = !1;
								if (i(d, function(i, n) {
									if (0 == i.indexOf("http://v.qq.com/")) {
										var d = i.match(/[\?&]vid\=([^&]*)/);
										if (null != d && d[1]) {
											var c = d[1],
												r = e.$dom.find(".js_btn");
											c ? (r.attr("disabled", !0), $.ajax({
												url: wx.url("/cgi-bin/registertopic?id=" + c + "&type=2"),
												type: "post",
												dataType: "json",
												success: function(d) {
													console.log("success"), console.log(d), d && d.base_resp && 0 == d.base_resp.ret ? (n = $.extend({
														url: i
													}, n), e.onOK && e.onOK(o, n), t.remove(), e.dialog = null) : a.err("系统繁忙，请稍后再试");
												},
												error: function() {
													a.err("系统繁忙，请稍后再试");
												},
												complete: function() {
													r.attr("disabled", !1);
												}
											})) : a.err("无效视频地址");
										} else a.err("无效视频地址");
										s = !0;
									}
									return s ? !0 : (n = $.extend({
										url: i
									}, n), e.onOK && e.onOK(o, n), t.remove(), void(e.dialog = null));
								}, {
									width: 500,
									height: 375,
									align: "none"
								}), s) return !0;
							}
						},
						onCancel: function() {
							this.remove(), e.dialog = null;
						},
						onHide: function() {
							this.remove(), e.dialog = null;
						}
					}), e.$dom = e.dialog.popup("get"), e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled"),
					e.init(), e.dialog.popup("resetPosition");
			},
			init: function() {
				var e = this,
					i = e.can_use_shortvideo ? [{
						name: "小视频",
						id: p
					}] : [];
				"ueditor" == e.scene ? (i.unshift({
						name: "视频网址"
					}), e.initTencentVideo()) : (i.unshift({
						name: "视频",
						id: h
					}), e.getList(h, 0, 10)), e.tab = new s(e.$dom.find(".js_videotab"), i), e.tab.selected(0),
					e.tab.dom.find("a").on("click", function(e) {
						e.preventDefault();
					}), e.$dom.on("click", ".js_top", function() {
						var i = $(this).data("id");
						e.$dom.find(".js_video_status").hide(), e.$dom.find(".js_video_create").hide(), e.$dom.find(".js_pagebar").empty(),
							e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled"), i ? (i == h && e.$dom.find(".js_video_create").show(),
								e.getList(i, 0, 10)) : e.$dom.find(".js_video_tencent").show(), e.tab.selected($(this).data("index"));
					}), e.$dom.on("click", ".Js_videomsg", function() {
						e.$dom.find(".Js_videomsg.selected").removeClass("selected"), e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"),
							$(this).addClass("selected");
					}), e.$dom.on("mousewheel", "#js_videomsg_list", function(e) {
						this.scrollTop -= e.originalEvent.wheelDelta > 0 ? 60 : -60, e.preventDefault();
					}).on("DOMMouseScroll", "#js_videomsg_list", function(e) {
						this.scrollTop += e.originalEvent.detail > 0 ? 60 : -60, e.preventDefault();
					});
			},
			initTencentVideo: function() {
				var e = this;
				e.$dom.find(".js_video_loading").hide(), e.$dom.find(".js_video_tencent").show(),
					e.$dom.find(".js_video_txurl").on("input propertychange", function() {
						var t = $(this).val();
						t ? (e.$dom.find(".js_btn_p").eq(0).removeClass("btn_disabled"), i(t, function(i) {
							var t = "<iframe height=" + _ + " width=" + u + ' frameborder=0 src="' + i + '" allowfullscreen></iframe>';
							e.$dom.find(".js_video_preview").html(t), e.dialog.popup("resetPosition");
						}, {
							width: u,
							height: _
						})) : e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled");
					});
			},
			initPagebar: function(e, i, t) {
				var o = this,
					n = e / i + 1;
				return i >= t ? void o.$dom.find(".js_pagebar").hide() : void new r({
					container: o.$dom.find(".js_pagebar").show(),
					perPage: i,
					first: !1,
					last: !1,
					isSimple: !0,
					initShowPage: n,
					totalItemsNum: t,
					callback: function(t) {
						var d = t.currentPage,
							s = o.$dom.find(".js_top.selected").data("id");
						d != n && s && (e = i * --d, o.getList(s, e, i));
					}
				});
			},
			getList: function(e, i, t) {
				var o = this,
					n = e == h ? m.appmsg : m;
				o.$dom.find(".js_video_content").hide(), o.$dom.find(".js_video_loading").show(),
					n.getList(e, i, t, function(n) {
						if (o.dialog && e == o.$dom.find(".js_top.selected").data("id")) {
							var d = n.file_item || n.item,
								s = o.$dom.find("#js_videomsg_list").find(".inner").empty();
							d.length ? (d.each(function(e, i) {
									var t = s.eq(i % 2),
										o = $('<div id="appmsg%s"></div>'.sprintf(e.app_id || e.file_id), t).appendTo(t);
									f(o, e);
								}), o.$dom.find(".js_video_content").show(), o.dialog.popup("resetPosition")) : o.$dom.find(".js_video_none").show().find(".js_empty_tips").html(e == p ? "暂无素材<br />（素材来源：通过微信用户上传给公众帐号）" : "暂无素材"),
								o.$dom.find(".js_video_loading").hide(), o.initPagebar(i, t, n.file_cnt[v[e]]);
						}
					});
			}
		};
	return $.extend(g.prototype, w), g;
});