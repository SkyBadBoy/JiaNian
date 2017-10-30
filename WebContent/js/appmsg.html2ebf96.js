define("tpl/homepage/plugins/plugin3.html.js",[],function(){
return'<div class="article_list">\n    {{each (<name>.plugin3.appmsg_list as key appmsg)}}\n    <a class="list_item" href="javascript:void(0);">\n        <div class="cover">\n            <img class="img" src="{{appmsg.cover}}" alt="">\n            <i class="default_thumb"></i>\n        </div>\n        <div class="cont">\n            <h2 class="title">{{appmsg.title}}</h2>\n            <p class="desc">{{appmsg.digest}}</p>\n        </div>\n    </a>\n    {{/each}}\n</div>';
});define("homepage/cateList.js",["homepage/importAppmsgList.js","tpl/homepage/plugins/plugin2_edit/cate_list_edit.html.js","biz_web/ui/input/lentips.js","common/wx/Tips.js","common/wx/popover.js"],function(t){
"use strict";
var e=t("homepage/importAppmsgList.js"),i=t("tpl/homepage/plugins/plugin2_edit/cate_list_edit.html.js"),n=t("biz_web/ui/input/lentips.js"),s=t("common/wx/Tips.js"),a=t("common/wx/popover.js"),r={
cname:"分类名",
appmsg_list:[{
title:"标题示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6vDmpWKn1M8Sk9tNGOiaGPPmxxPoXR7GM16AFk2lsfiaL2iapIm0RFicCvA/300",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
},{
title:"标题示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6vDmpWKn1M8Sk9tNGOiaGPPmxxPoXR7GM16AFk2lsfiaL2iapIm0RFicCvA/300",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
},{
title:"标题示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6vDmpWKn1M8Sk9tNGOiaGPPmxxPoXR7GM16AFk2lsfiaL2iapIm0RFicCvA/300",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
}]
},o=function(t){
this.opt=t;
var e=t.container,n=t.tab_container,s=t.idx;
this.idx=s,this.default_cname="分类名",this.renderjson=t.data,this.renderjson.cname=this.renderjson.cname||this.default_cname,
e.append('<div style="display:none;" class="js_edit_cate_list_area js_edit_cate_list_area_'+s+'">'+i+"</div>");
var a=$('<li class="tab_nav js_tab_nav_item"><a href="javascript:void(0);">'+this.renderjson.cname+"</a></li>");
this.$li=a,n.find(".js_add_nav").before(a),this._initEditArea(),!!t.setOuterJson&&t.setOuterJson(this.getrenderjson());
};
return o.prototype.cate_default_json=r,o.prototype._initEditArea=function(){
var t=this.opt,i=this,s=t.container,r=t.idx,o=this.$li,p=s.find(".js_edit_cate_list_area_"+r),c=p.find(".js_cate_input");
new n({
input:c.val(this.renderjson.cname.html(!1)),
tip:p.find(".js_cate_input_len_tips"),
maxlimit:4,
seg:"/",
trim:!0,
className:"",
callback:function(t,e){
t?(c.parent().addClass("warn"),i.renderjson.cname=""):(c.parent().removeClass("warn"),
i.renderjson.cname=e.value),i._renderCname();
}
}),i.isSorting=!1;
var l=this.renderjson.appmsg_list||[];
i._importAppmsgList=new e({
container:p.find(".js_import_appmsglist"),
maxNum:30,
title:"内容列表",
list:l,
callback:function(t){
i._renderTpl(i.getrenderjson(t)),i.show();
},
startSort:function(){
i.isSorting=!0;
},
endSort:function(){
i.isSorting=!1;
}
}),o.click(function(){
i.show();
}),p.find(".js_del_cate").click(function(){
var t=$(this);
new a({
dom:t,
content:"确定删除此分类？",
place:"bottom",
margin:"center",
buttons:[{
text:"确定",
click:function(){
this.remove(),i.remove();
},
type:"primary"
},{
text:"取消",
click:function(){
this.remove();
}
}]
});
});
},o.prototype.getrenderjson=function(t){
var e=this.opt,i=e.idx,n=e.container,s=n.find(".js_edit_cate_list_area_"+i),a=s.find(".js_cate_input"),r=$.extend(!0,{},this.cate_default_json);
t=t||this.renderjson.appmsg_list,t&&t.length>0?(r.appmsg_list=t,this.renderjson.appmsg_list=t):this.renderjson.appmsg_list=[];
var o=$.trim(a.val());
return o&&(r.cname=o,this.renderjson.cname=o),r;
},o.prototype._renderCname=function(){
var t=this.opt,e=this.renderjson.cname||this.default_cname;
this.$li.find("a").text(e),!!t.renderCnameCallback&&t.renderCnameCallback(e);
},o.prototype._renderTpl=function(t){
var e=this.opt;
!!e.renderCallback&&e.renderCallback(t);
},o.prototype.initShow=function(){
this._renderTpl(this.getrenderjson()),this.show();
},o.prototype.show=function(){
var t=this.opt,e=t.container,i=t.idx;
e.find(".js_edit_cate_list_area").hide(),e.find(".js_edit_cate_list_area_"+i).show();
var n=t.tab_container;
n.find(".selected").removeClass("selected"),this.$li.addClass("selected"),!!t.afterShow&&t.afterShow();
},o.prototype.remove=function(){
var t=this.opt;
t.canRemove()?(this.$li.remove(),!!t.afterRemove&&t.afterRemove()):s.err("至少保留一个分类");
},o.prototype.getEditData=function(){
if(this.isSorting)return s.err("排序完成后才能发布"),!1;
var t=this.renderjson,e={};
if(!t.cname)return s.err("分类名称必须为1-4个字"),!1;
if(e.cname=t.cname,!t.appmsg_list||t.appmsg_list.length<=0)return s.err("请至少选择一篇文章"),
!1;
for(var i=[],n=0,a=t.appmsg_list.length;a>n;++n)i.push(t.appmsg_list[n].aid);
return e.aid_list=i,e;
},o;
});define("tpl/homepage/plugins/plugin2_edit.html.js",[],function(){
return'<div class="portable_editor to_left">\n    <div class="editor_inner">\n        <!--BEGIN editor_hd-->\n        <div class="editor_hd">\n            <!--BEGIN tab分类-->\n            <div class="section_tab">\n                <ul class="tab_navs js_tab_nav">\n                    <li class="tab_nav no_extra js_add_nav">\n                        <a class="add_nav" href="javascript:void(0);">添加</a>\n                    </li>\n                </ul>\n            </div>\n            <!--END tab分类-->\n        </div>\n        <!--END editor_hd-->\n        <!--BEGIN editor_bd-->\n        <div class="editor_bd js_catelist_area">\n        </div>\n        <!--END editor_bd-->\n        <span class="editor_arrow_wrp">\n            <i class="editor_arrow editor_arrow_out"></i>\n            <i class="editor_arrow editor_arrow_in"></i>\n        </span>\n    </div>\n</div>';
});define("tpl/homepage/plugins/plugin2.html.js",[],function(){
return'<div class="tab">\n    <!--BEGIN tab_hd-->\n    <div class="tab_hd">\n        <div class="tab_hd_inner">\n            {{each (<name>.plugin2.cate_list as key item)}}\n            <a href="javascript:void(0);" style="width:{{100/<name>.plugin2.cate_list.length}}%" class="js_cname_item item {{if (key==0)}}active{{/if}} {{if (key == <name>.plugin2.cate_list.length-1 && <name>.plugin2.cate_list.length > 1)}}no_extra{{/if}}">{{item.cname}}</a>\n            {{/each}}\n        </div>\n    </div>\n    <!--END tab_hd-->\n    <!--BEGIN tab_bd-->\n    <div class="tab_bd">\n        {{each (<name>.plugin2.cate_list as key item)}}\n        <div class="article_list js_article_list" {{if (key!=0)}}style="display: none;"{{/if}}>\n            {{each (item.appmsg_list as key appmsg)}}\n                <a class="list_item" href="javascript:void(0);">\n                    <div class="cover">\n                        <img class="img" src="{{appmsg.cover}}" alt="">\n                    </div>\n                    <div class="cont">\n                        <h2 class="title">{{appmsg.title}}</h2>\n                        <p class="desc">{{appmsg.digest}}</p>\n                    </div>\n                </a>\n            {{/each}}\n        </div>\n        {{/each}}\n    </div>\n    <!--END tab_bd-->\n</div>\n';
});define("homepage/importAppmsgList.js",["biz_common/jquery.ui/jquery.ui.sortable.js","tpl/homepage/importAppmsgList/layout.html.js","tpl/homepage/importAppmsgList/item.html.js","homepage/appmsgdialog.js"],function(t){
"use strict";
t("biz_common/jquery.ui/jquery.ui.sortable.js");
var s=t("tpl/homepage/importAppmsgList/layout.html.js"),i=t("tpl/homepage/importAppmsgList/item.html.js"),e={},r=t("homepage/appmsgdialog.js"),o=function(t){
if(t&&!(t.length<=0))for(var s=0,i=t.length;i>s;++s){
var r=t[s];
e[r.aid]=r;
}
},n=function(t){
this.opt=t,this.list=t.list||[];
var e=this,n=t.container,a=this.list;
t.maxNum=t.maxNum||5,o(a),n.html(wx.T(s,{
title:t.title||""
}));
var l=n.find(".js_appmsg_list");
l.html(wx.T(i,{
appmsg_list:this.list
})),l.sortable({
items:".js_article",
placeholder:"drag_placeholder",
dropOnEmpty:!0,
start:function(t,s){
s.item.addClass("dragging");
},
stop:function(t,s){
s.item.removeClass("dragging");
}
}),l.sortable("disable"),n.find(".js_max_num").text(t.maxNum),n.find(".js_import").click(function(){
var s=t.maxNum-e.list.length;
s=Math.max(0,s),new r({
ids:t.selectLast?e._getAidList():[],
maxNum:s,
callback:function(t){
e.list=e.list.concat(t),o(t),e._resetSortState(),e._refreshEditArea(),e.opt.callback&&e.opt.callback(e.list),
n.find(".js_select_num").text(e.list.length);
}
});
}),e._endSort(),n.find(".js_select_num").text(this.list.length),n.find(".js_sort").click(function(){
var t=$(this);
return t.attr("disabled")?void e._endSort():void e._startSort();
}),n.find(".js_sort_sure").click(function(){
e._resetList(),e._endSort();
}),n.find(".js_sort_cancle").click(function(){
e._refreshEditArea(),e._endSort();
}),n.on("click",".js_del",function(){
$(this).parent().remove(),e._resetList(),n.find(".js_select_num").text(e.list.length);
});
};
return n.prototype._getAidList=function(){
for(var t=[],s=this.list,i=0,e=s.length;e>i;++i)t.push(s[i].aid);
return t;
},n.prototype._startSort=function(){
var t=this.opt,s=t.container,i=s.find(".js_appmsg_list");
i.sortable("enable"),s.find(".js_import,.js_sort,.js_del").hide(),s.find(".js_sort_sure,.js_sort_cancle,.js_sort_item").show(),
!!t.startSort&&t.startSort();
},n.prototype._endSort=function(){
var t=this.opt,s=t.container,i=s.find(".js_appmsg_list");
i.sortable("disable"),s.find(".js_sort_sure,.js_sort_cancle,.js_sort_item").hide(),
s.find(".js_import,.js_sort,.js_del").show(),this._resetSortState(),!!t.endSort&&t.endSort();
},n.prototype._resetSortState=function(){
var t=this.opt,s=t.container,i=this._getlist(),e=s.find(".js_sort");
i.length<=0?e.disable():e.enable();
},n.prototype._getSortList=function(){
var t=[],s=this.opt,i=s.container;
return i.find(".js_article").each(function(){
var s=$(this),i=s.data("aid");
e[i]&&t.push(e[i]);
}),t;
},n.prototype._resetList=function(){
var t=this._getSortList(),s=this.opt;
this.list=t,this._resetSortState(),s.callback&&s.callback(t);
},n.prototype._getlist=function(){
var t=this.list||{};
return t;
},n.prototype._refreshEditArea=function(){
var t=this.opt,s=t.container,e=this.list,r=s.find(".js_appmsg_list"),o="";
e&&(o=wx.T(i,{
appmsg_list:e
})),r.html(o);
},n;
});define("common/wx/wxt.js", [ "biz_web/lib/json.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict", e("biz_web/lib/json.js");
var i = function(e, t) {
return i[typeof t == "object" ? "render" : "compile"].apply(i, arguments);
};
return function(e, t) {
"use strict";
e.version = "2.0.1", e.openTag = "<#", e.closeTag = "#>", e.isEscape = !1, e.isCompress = !1, e.parser = null, e.render = function(e, t) {
var n = r(e);
return n === undefined ? i({
id: e,
name: "Render Error",
message: "No Template"
}) : n(t);
}, e.compile = function(t, r) {
function o(n) {
try {
return new l(n) + "";
} catch (s) {
return a ? (s.id = t || r, s.name = "Render Error", s.source = r, i(s)) : e.compile(t, r, !0)(n);
}
}
var u = arguments, a = u[2], f = "anonymous";
typeof r != "string" && (a = u[1], r = u[0], t = f);
try {
var l = s(r, a);
} catch (c) {
return c.id = t || r, c.name = "Syntax Error", i(c);
}
return o.prototype = l.prototype, o.toString = function() {
return l.toString();
}, t !== f && (n[t] = o), o;
}, e.helper = function(t, n) {
e.prototype[t] = n;
}, e.onerror = function(e) {
var n = "[template]:\n" + e.id + "\n\n[name]:\n" + e.name;
e.message && (n += "\n\n[message]:\n" + e.message), e.line && (n += "\n\n[line]:\n" + e.line, n += "\n\n[source]:\n" + e.source.split(/\n/)[e.line - 1].replace(/^[\s\t]+/, "")), e.temp && (n += "\n\n[temp]:\n" + e.temp), t.console && console.error(n);
};
var n = {}, r = function(r) {
var i = n[r];
if (i === undefined && "document" in t) {
var s = document.getElementById(r);
if (s) {
var o = s.value || s.innerHTML;
return e.compile(r, o.replace(/^\s*|\s*$/g, ""));
}
} else if (n.hasOwnProperty(r)) return i;
}, i = function(t) {
function n() {
return n + "";
}
return e.onerror(t), n.toString = function() {
return "{Template Error}";
}, n;
}, s = function() {
e.prototype = {
$render: e.render,
$escape: function(e) {
return typeof e == "string" ? e.replace(/&(?![\w#]+;)|[<>"']/g, function(e) {
return {
"<": "&#60;",
">": "&#62;",
'"': "&#34;",
"'": "&#39;",
"&": "&#38;"
}[e];
}) : e;
},
$string: function(e) {
return typeof e == "string" || typeof e == "number" || typeof e == "boolean" ? e : typeof e == "function" ? e() : JSON.stringify2(e);
}
};
var t = Array.prototype.forEach || function(e, t) {
var n = this.length >>> 0;
for (var r = 0; r < n; r++) r in this && e.call(t, this[r], r, this);
}, n = function(e, n) {
t.call(e, n);
}, r = "break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined", i = /\/\*(?:.|\n)*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|'[^']*'|"[^"]*"|[\s\t\n]*\.[\s\t\n]*[$\w\.]+/g, s = /[^\w$\u4e00-\u9fa5]+/g, o = new RegExp([ "\\b" + r.replace(/,/g, "\\b|\\b") + "\\b" ].join("|"), "g"), u = /\b\d[^,]*/g, a = /^,+|,+$/g, f = function(e) {
return e = e.replace(i, "").replace(s, ",").replace(o, "").replace(u, "").replace(a, ""), e = e ? e.split(/,+/) : [], e;
};
return function(t, r) {
function i(t) {
return d += t.split(/\n/).length - 1, e.isCompress && (t = t.replace(/[\n\r\t\s]+/g, " ")), t = t.replace(/('|\\)/g, "\\$1").replace(/\r/g, "\\r").replace(/\n/g, "\\n"), t = w[1] + "'" + t + "'" + w[2], t + "\n";
}
function s(t) {
var n = d;
c ? t = c(t) : r && (t = t.replace(/\n/g, function() {
return d++, "$line=" + d + ";";
}));
if (t.indexOf("=") === 0) {
var i = t.indexOf("==") !== 0;
t = t.replace(/^=*|[\s;]*$/g, "");
if (i && e.isEscape) {
var s = t.replace(/\s*\([^\)]+\)/, "");
!m.hasOwnProperty(s) && !/^(include|print)$/.test(s) && (t = "$escape($string(" + t + "))");
} else t = "$string(" + t + ")";
t = w[1] + t + w[2];
}
return r && (t = "$line=" + n + ";" + t), o(t), t + "\n";
}
function o(e) {
e = f(e), n(e, function(e) {
v.hasOwnProperty(e) || (u(e), v[e] = !0);
});
}
function u(e) {
var t;
e === "print" ? t = S : e === "include" ? (g.$render = m.$render, t = x) : (t = "$data." + e, m.hasOwnProperty(e) && (g[e] = m[e], e.indexOf("$") === 0 ? t = "$helpers." + e : t = t + "===undefined?$helpers." + e + ":" + t)), y += e + "=" + t + ",";
}
var a = e.openTag, l = e.closeTag, c = e.parser, h = t, p = "", d = 1, v = {
$data: !0,
$helpers: !0,
$out: !0,
$line: !0
}, m = e.prototype, g = {}, y = "var $helpers=this," + (r ? "$line=0," : ""), b = "".trim, w = b ? [ "$out='';", "$out+=", ";", "$out" ] : [ "$out=[];", "$out.push(", ");", "$out.join('')" ], E = b ? "if(content!==undefined){$out+=content;return content}" : "$out.push(content);", S = "function(content){" + E + "}", x = "function(id,data){if(data===undefined){data=$data}var content=$helpers.$render(id,data);" + E + "}";
n(h.split(a), function(e, t) {
e = e.split(l);
var n = e[0], r = e[1];
e.length === 1 ? p += i(n) : (p += s(n), r && (p += i(r)));
}), h = p, r && (h = "try{" + h + "}catch(e){" + "e.line=$line;" + "throw e" + "}"), h = "'use strict';" + y + w[0] + h + "return new String(" + w[3] + ")";
try {
var T = new Function("$data", h);
return T.prototype = g, T;
} catch (N) {
throw N.temp = "function anonymous($data) {" + h + "}", N;
}
};
}();
e.openTag = "{{", e.closeTag = "}}", e.parser = function(t) {
t = t.replace(/\s*\(\s*/g, " (").replace(/\s*\)\s*/g, ") ").replace(/^\s/, "");
var n = t.split(" "), r = n.shift(), i = e.keywords, s = i[r];
return s && i.hasOwnProperty(r) ? (n = n.join(" "), t = s.call(t, n)) : e.prototype.hasOwnProperty(r) ? (n = n.join(" "), t = "==" + r + "" + n + ";") : (t = t.replace(/[\s;]*$/, ""), t = "=" + t), t;
}, e.keywords = {
"if": function(e) {
return "if(" + e + "){";
},
"else": function(e) {
return e = e.split(" "), e.shift() === "if" ? e = " if(" + e.join(" ") + ")" : e = "", "}else" + e + "{";
},
elseif: function(e) {
return "}else if(" + e + "){";
},
"/if": function() {
return "}";
},
each: function(e) {
e = e.replace(/^\s*\(|\)\s*$/g, "").replace(/\s*\(\s*/g, "("), e = e.split(" ");
var t = e[0] || "$data", n = e[1] || "as", r = e[2] || "$index", i = e[3] || "$value", s = i + "," + r;
return n !== "as" && (t = "[]"), "$each(" + t + ",function(){" + r + "=arguments[0];" + i + "=arguments[1]},function(" + s + "){";
},
"/each": function() {
return "});";
},
echo: function(e) {
return "print(" + e + ");";
},
"break": function(e) {
return "return true";
},
"continue": function(e) {
return "return false";
}
}, e.helper("$each", function(e, t, n) {
var r = Array.isArray || function(e) {
return Object.prototype.toString.call(e) === "[object Array]";
}, i;
if (r(e)) {
for (var s = 0, o = e.length; s < o; s++) if (n.call(e, e[s], s, e)) break;
s--;
} else {
if (Object.keys) i = Object.keys(e); else for (s in e) i.push(s);
i = i.sort();
for (var u = 0, o = i.length; u < o; u++) {
s = i[u];
if (n.call(e, e[s], s)) break;
}
}
t(s, e[s]);
}), e.helper("json_encode", JSON.stringify2), e.helper("json_decode", $.parseJSON), e.helper("strlen", function(e) {
return e.length;
}), e.helper("str_replace", function(e, t, n) {
return n.replace(e, t);
}), e.helper("substr", function(e, t, n) {
return e.substr(t, n);
}), e.helper("trim", function(e) {
return $.trim(e);
}), e.helper("strpos", function(e, t) {
return e.indexOf(t);
}), e.helper("explode", function(e, t) {
return t.split(e);
}), e.helper("join", function(e, t) {
return t.join(e);
}), e.helper("html_decode", function(e) {
return e.html(!1);
}), e.helper("html_encode", function(e) {
return e.html(!0);
}), e.helper("getresfullname", function(e) {
return e;
});
}(i, window), i;
} catch (s) {
wx.jslog({
src: "common/wx/wxt.js"
}, s);
}
});define("homepage/plugins/base.js",["common/wx/wxt.js"],function(t){
"use strict";
var i=t("common/wx/wxt.js");
i.isEscape=!0;
var n=0;
$("#js_preview_area").length>0&&(n=$("#js_preview_area").offset().top);
var e=function(t){
var i=this;
i.opt=t,this.pid=t.pid;
var n=t.container,e=t.js_edit_area,s=t.idx,o=this.edit_tpl;
n.html('<div class="plugin_content"><div class="js_plugin_content" id="js_plugin_content_'+s+'"></div><div class="plugin_mask js_plugin_mask" style="display:none;"><a href="javascript:void(0);"><i class="icon18_common edit_gray js_plugin_edit">编辑</i></a></div></div>'),
this.$content=n.find(".js_plugin_content"),e.append('<div style="display:none;" class="js_plugin_edit_area plugin_edit_area" id="js_plugin_edit_area_'+s+'">'+o+"</div>"),
this.renderjson=t.renderjson,this._renderTpl(this.renderjson),n.on("click",".js_plugin_edit,.js_plugin_content",function(){
i.showEditArea();
}),n.hover(function(){
var t=$(this).find(".js_plugin_mask");
return i.isShowEdit()?!1:void t.fadeIn();
},function(){
var t=$(this).find(".js_plugin_mask");
t.fadeOut();
});
};
e.prototype.isShowEdit=function(){
var t=this.opt,i=t.idx;
return $("#js_plugin_edit_area_"+i).is(":visible");
},e.prototype.showEditArea=function(){
var t=this.opt,i=(t.container,t.js_edit_area),e=t.idx;
i.find(".js_plugin_edit_area").hide(),$("#js_plugin_edit_area_"+e).show().css({
marginTop:$("#js_plugin_content_"+e).offset().top-n
});
},e.prototype.initData=function(){
return{};
},e.prototype.getEditData=function(){
return!1;
},e.prototype._renderTpl=function(t){
{
var n=this.opt;
n.container;
}
t=this.getRenderJson(t);
var e=n.idx,s=n.name||"plugin_"+e,o=this.tpl;
o=o.replace(/<name>/gi,s);
var r={};
r[s]=t,this.realRenderjson=t,this.$content.html(i.compile(o)(r)),this._afterRenderTpl&&this._afterRenderTpl();
};
var s=function(t,i){
var n=function(){};
n.prototype=i.prototype,t.prototype=new n,t.prototype.constructor=t,t.uber=i.prototype;
};
return{
base:e,
inherit:s
};
});define("tpl/homepage/plugins/plugin1_edit.html.js",[],function(){
return'<div class="portable_editor to_left">\n    <div class="editor_inner">\n        <!--BEGIN editor_bd-->\n        <div class="editor_bd js_import_appmsglist">\n        </div>\n        <!--END editor_bd-->\n        <span class="editor_arrow_wrp">\n            <i class="editor_arrow editor_arrow_out"></i>\n            <i class="editor_arrow editor_arrow_in"></i>\n        </span>\n    </div>\n</div>';
});define("tpl/homepage/plugins/plugin1.html.js",[],function(){
return'<div class="slider">\n    <div class="swiper">\n    	{{each (<name>.plugin1.appmsg_list as key item)}}\n        <div class="item">\n            <div class="img"\n                 style="background: url({{item.cover}}) center center no-repeat; background-size: cover;"></div>\n            <p class="desc">{{item.title}}</p>\n        </div>\n        {{/each}}\n    </div>\n    <div class="indicator">\n    	{{each (<name>.plugin1.appmsg_list as key item)}}\n        <a href="javascript:void(0);"><i class="icon_dot {{if (key==0)}}active{{/if}}"></i></a>\n        {{/each}}\n    </div>\n</div>';
});define("tpl/biz_web/ui/checkbox.html.js", [], function(e, t, n) {
return '<label for="_checkbox_{index}" class="frm_{type}_label">\n	<i class="icon_{type}"></i>\n	<input type="{type}" class="frm_{type}" name="{name}" id="_checkbox_{index}">\n	<span class="lbl_content">{label}</span>\n</label>';
});define("tpl/pagebar.html.js", [], function(e, t, n) {
return '<div class="pagination">\n    <span class="page_nav_area">\n        <a href="javascript:void(0);" class="btn page_first">{firstButtonText}</a>\n        <a href="javascript:void(0);" class="btn page_prev"><i class="arrow"></i></a>\n        {if isSimple}\n            <span class="page_num">\n                <label>{initShowPage}</label>\n                <span class="num_gap">/</span>\n                <label>{endPage}</label>\n            </span>\n        {else}\n            {each startRange as pageIndex index}\n            <a href="javascript:void(0);" class="btn page_nav">{pageIndex}</a>\n            {/each}\n            <span class="gap_prev">...</span>\n            {each midRange as pageIndex index}\n            <a href="javascript:void(0);" class="btn page_nav js_mid">{pageIndex}</a>\n            {/each}\n            <span class="gap_next">...</span>\n            {each endRange as pageIndex index}\n            <a href="javascript:void(0);" class="btn page_nav">{pageIndex}</a>\n            {/each}\n        {/if}\n        <a href="javascript:void(0);" class="btn page_next"><i class="arrow"></i></a>\n        <a href="javascript:void(0);" class="btn page_last">{lastButtonText}</a>            \n    </span>\n    {if (endPage>1)}\n    <span class="goto_area">\n        <input type="text">\n        <a href="javascript:void(0);" class="btn page_go">跳转</a>\n    </span>\n    {/if}\n</div>\n';
});define("tpl/top.html.js", [], function(e, t, n) {
return '<ul class="tab_navs title_tab" data-index="{itemIndex=0}">\n    {each data as o index}\n    {if (typeof o.acl == "undefined" || o.acl == 1)}\n    <li data-index="{itemIndex++}" class="tab_nav {if (itemIndex == 1)}first{/if} js_top {o.className}" data-id="{o.id}"><a href="{o.url}" {if o.target==\'_blank\'}target="_blank"{/if}>{o.name}</a></li>\n    {/if}\n    {/each}\n</ul>\n';
});define("tpl/uploader.html.js",[],function(){
return'<li id="uploadItem{id}" data-status="{className}" class="upload_file">\n    <strong class="upload_file_name">{fileName}</strong>\n    <span class="upload_file_size">({size})</span>\n    <div class="progress_bar"><div class="progress_bar_thumb" style="width:0%"></div></div>\n    <a href="javascript:;" data-id="{id}" class="upload_file_cancel js_cancel">取消</a>\n</li>\n';
});define("tpl/media/appmsg.html.js",[],function(){
return'<div class="appmsg {if isMul}multi{else}single{/if} {if first.cover}has_first_cover{/if}" data-id="{id}" data-fileid="{file_id}" data-completed="{completed===true?1:2}">\n    <div class="appmsg_content">\n        <div class="appmsg_info">\n            <em class="appmsg_date">{time}</em>\n            {if completed===false}\n            <div class="undone_tips">文章内容不完整</div>\n            {/if}\n        </div>\n        {if isMul}\n            <div class="cover_appmsg_item">\n                <h4 class="appmsg_title js_title">\n                {if first.title&&first.cover}\n                    <a href="{first.content_url}" target="_blank" data-msgid="{id}" data-idx="{first.seq}">{first.title||"未命名图文"}</a>\n                {else}\n                    <span>{first.title||"未命名图文"}</span>\n                {/if}\n                </h4>\n                {if first.cover}\n                <div class="appmsg_thumb_wrp" style="background-image:url(\'{first.cover}\')">\n                    <!--\n                    {if !first.cover}\n                    <div class="appmsg_thumb default">尚未设置封面</div>\n                    {/if}\n                    -->\n                </div>\n                {else}\n                <div class="appmsg_cover_desc" title="{first.digest}">{first.digest}</div>\n                {/if}\n                {if first.completed && !first.content_url && type == 10}\n                <a href="" class="edit_mask preview_mask js_preview" data-msgid="{id}" data-idx="{first.seq}">\n                    <div class="edit_mask_content">\n                        <p class="">\n                        预览文章                        </p>\n                    </div>\n                    <span class="vm_box"></span>\n                </a>\n                {/if}\n            </div>\n            {each list as item}\n            <div class="appmsg_item {if item.cover}has_cover{/if}">\n                {if item.cover}\n                <div class="appmsg_thumb_wrp" style="background-image:url(\'{item.cover}\');">\n                    <!--\n                    {if !item.cover}\n                    <div class="appmsg_thumb default">\n                        <span class="appmsg_thumb_tips">尚未设置封面</span>\n                        <span class="vm_box"></span>\n                    </div>\n                    {/if}\n                    -->\n                </div>\n                {/if}\n                <h4 class="appmsg_title js_title">\n                    {if item.title&&item.cover}\n                        <a href="{item.content_url}" target="_blank" data-msgid="{id}" data-idx="{item.seq}">{item.title||"未命名图文"}</a>\n                    {else}\n                        <span>{item.title||"未命名图文"}</span>\n                    {/if}                    \n                </h4>\n                {if item.completed && !item.content_url && type == 10}\n                <a href="" class="edit_mask preview_mask js_preview" data-msgid="{id}" data-idx="{item.seq}">\n                    <div class="edit_mask_content">\n                        <p class="">\n                            预览文章                        </p>\n                    </div>\n                    <span class="vm_box"></span>\n                </a>\n                {/if}\n            </div>\n            {/each}\n        {else}\n            <div class="appmsg_item">\n                <h4 class="appmsg_title js_title">\n                    {if first.title&&first.cover}\n                        <a href="{first.content_url}" target="_blank" data-msgid="{id}" data-idx="{first.seq}">{first.title||"未命名图文"}</a>\n                    {else}\n                        <span>{first.title||"未命名图文"}</span>\n                    {/if}                \n                {if first.isreprint}{/if}</h4>\n                {if first.cover}\n                <div class="appmsg_thumb_wrp" style="background-image:url(\'{first.cover}\')">\n                    <!--\n                    {if !first.cover}\n                    <div class="appmsg_thumb default">尚未设置封面</div>\n                    {/if}\n                    -->\n                </div>\n                {/if}\n                <p class="appmsg_desc">{first.digest}</p>\n                {if completed && !first.content_url && type == 10}\n                <a href="" class="edit_mask preview_mask js_preview" data-msgid="{id}" data-idx="{first.seq}">\n                    <div class="edit_mask_content">\n                        <p class="">\n                            预览文章                        </p>\n                    </div>\n                    <span class="vm_box"></span>\n                </a>\n                {/if}\n            </div>\n        {/if}\n    </div>\n    {if completed===false && !showEdit}\n    <div class="edit_mask appmsg_mask">\n        <div class="edit_mask_content">\n            <p>图文不完整，无法选中，<a target="_blank" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&lang=zh_CN&token={token}&type={type}&appmsgid={id}&isMul=1">继续编辑</a></p>\n        </div>\n        <span class="vm_box"></span>\n    </div>\n    {/if}\n    {if showEdit}\n    <div class="appmsg_opr">\n        <ul>\n            <li class="appmsg_opr_item grid_item size1of2">\n            <a target="_blank" class="js_tooltip" href="/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&lang=zh_CN&token={token}&type={type}&appmsgid={id}&isMul=1" data-tooltip="编辑">&nbsp;<i class="icon18_common edit_gray">编辑</i></a>\n            </li>\n            <li class="appmsg_opr_item grid_item size1of2 no_extra">\n                <a class="js_del no_extra js_tooltip" data-id="{id}" href="javascript:void(0);" data-tooltip="删除">&nbsp;<i class="icon18_common del_gray">删除</i></a>\n            </li>\n        </ul>\n    </div>\n    {/if}        \n    {if showMask&&completed!==false}\n    <div class="edit_mask appmsg_mask">\n        <i class="icon_card_selected">已选择</i>\n    </div>\n    {/if}\n</div>\n';
});