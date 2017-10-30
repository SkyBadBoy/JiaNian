define("cardticket/add/member_info_flag.js",[],function(){
"use strict";
function n(n,f){
for(var i=0;i<n.length;i++)if(n[i]===f)return i;
return-1;
}
var f=[1,4096,2,4,8,0,32,64,128,256,512,1024,2048];
return{
sys_info:["手机号","姓名","性别","所在地区","生日","身份证号","邮箱","详细地址","学历","职业","行业","收入","爱好"],
info_flag:f,
flag2info:function(n){
for(var f=[],i=0;i<this.info_flag.length;i++){
var r=this.info_flag[i];
r&n&&f.push(this.sys_info[i]);
}
return f;
},
info2flag:function(f){
for(var i=0,r=0;r<f.length;r++){
var t=n(this.sys_info,f[r]);
t>=0&&(i|=this.info_flag[t]);
}
return i;
}
};
});define("homepage/plugins/plugin3.js",["page/homepage/plugins/plugin3.css","tpl/homepage/plugins/plugin3.html.js","tpl/homepage/plugins/plugin3_edit.html.js","homepage/plugins/base.js","common/wx/wxt.js","homepage/importAppmsgList.js","common/wx/Tips.js"],function(i){
"use strict";
i("page/homepage/plugins/plugin3.css");
var t=i("tpl/homepage/plugins/plugin3.html.js"),p=i("tpl/homepage/plugins/plugin3_edit.html.js"),e=i("homepage/plugins/base.js"),n=i("common/wx/wxt.js"),s=e.base,o=e.inherit,l=i("homepage/importAppmsgList.js"),r=i("common/wx/Tips.js"),g={
plugin3:{
appmsg_list:[{
title:"封面示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6vDmpWKn1M8Sk9tNGOiaGPPmxxPoXR7GM16AFk2lsfiaL2iapIm0RFicCvA/300",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
},{
title:"封面示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6vDmpWKn1M8Sk9tNGOiaGPPmxxPoXR7GM16AFk2lsfiaL2iapIm0RFicCvA/300",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
}]
}
},a=function(i){
var t=this;
t.opt=i,s.apply(this,arguments),this._initEditArea();
};
return o(a,s),a.prototype.tpl=t,a.prototype.edit_tpl=p,a.prototype.default_json=g,
a.prototype._initEditArea=function(){
var i=this.opt,t=this,p=i.idx,e=$("#js_plugin_edit_area_"+p),n=(e.find(".js_appmsg_list"),
[]);
t.renderjson.plugin3&&t.renderjson.plugin3.appmsg_list&&(n=t.renderjson.plugin3.appmsg_list),
t.isSorting=!1,t._importAppmsgList=new l({
container:e.find(".js_import_appmsglist"),
maxNum:30,
title:"内容列表",
list:n,
callback:function(i){
var p=$.extend(!0,{},t.default_json);
i&&i.length>0?(p={
plugin3:{
appmsg_list:i
}
},t.renderjson=p):t.renderjson={
plugin3:{
appmsg_list:[]
}
},t._renderTpl(p);
},
startSort:function(){
t.isSorting=!0;
},
endSort:function(){
t.isSorting=!1;
}
});
},a.prototype._getSelectList=function(){
var i=!!this.renderjson.plugin3&&this.renderjson.plugin3.appmsg_list;
if(i&&i.length>0){
for(var t=[],p=0,e=i.length;e>p;++p)t.push(i[p].aid);
return t;
}
return[];
},a.prototype.getRenderJson=function(i){
return i&&i.plugin3&&i.plugin3.appmsg_list&&i.plugin3.appmsg_list.length>0?i:$.extend(!0,{},this.default_json);
},a.prototype.getEditData=function(){
if(this.isSorting)return r.err("排序完成后才能发布"),!1;
var i=this._getSelectList();
return i.length>0?{
aid_list:i
}:(r.err("请至少选择一篇文章"),!1);
},a.getRenderHTML=function(i){
var p="plugin",e={};
e[p]=i;
var s=t.replace(/<name>/gi,p);
return n.compile(s)(e);
},a;
});define("homepage/plugins/plugin2.js",["page/homepage/plugins/plugin2.css","tpl/homepage/plugins/plugin2.html.js","tpl/homepage/plugins/plugin2_edit.html.js","homepage/plugins/base.js","common/wx/wxt.js","common/wx/Tips.js","homepage/cateList.js"],function(t){
"use strict";
t("page/homepage/plugins/plugin2.css");
var e=t("tpl/homepage/plugins/plugin2.html.js"),n=t("tpl/homepage/plugins/plugin2_edit.html.js"),i=t("homepage/plugins/base.js"),s=t("common/wx/wxt.js"),a=i.base,r=i.inherit,o=t("common/wx/Tips.js"),p=t("homepage/cateList.js"),l={
plugin2:{
cate_list:[]
}
},c=function(t){
var e=this;
e.opt=t,a.apply(this,arguments),this._initEditArea();
};
return r(c,a),c.prototype.tpl=e,c.prototype.edit_tpl=n,c.prototype.default_json=l,
c.prototype._createCateList=function(t,e){
var n=this.opt,i=this,s=n.idx,a=$("#js_plugin_edit_area_"+s),r=n.container,o=a.find(".js_tab_nav");
e=e||{},i.json[t]=e;
var l=new p({
container:a.find(".js_catelist_area"),
idx:t,
tab_container:o,
data:e,
setOuterJson:function(t){
i.json[this.idx]=t;
},
canRemove:function(){
var t=i._getRenderJson();
return t.plugin2.cate_list.length>1;
},
renderCallback:function(t){
var e=this.idx;
i.json[e]=t,i.selectTab=e,i._renderTpl(i._getRenderJson());
},
renderCnameCallback:function(t){
var e=this.idx;
i.json[e].cname=t;
var n=i._getRealShowIdx(e);
r.find(".js_cname_item").eq(n).text(t);
},
afterShow:function(){
var t=i._getRealShowIdx(this.idx);
r.find(".js_cname_item").eq(t).click(),r.find(".js_article_list").hide().eq(t).show();
},
afterRemove:function(){
i.json[this.idx]=!1,i.selectTab=0,i._renderTpl(i._getRenderJson()),o.find(".js_tab_nav_item").eq(0).click();
}
});
return l;
},c.prototype._initEditArea=function(){
{
var t=this.opt,e=this,n=t.idx,i=$("#js_plugin_edit_area_"+n),s=t.container;
i.find(".js_tab_nav");
}
this.json=[],this.selectTab=0,this.cateList=[];
var a=[];
e.renderjson.plugin2&&e.renderjson.plugin2.cate_list&&(a=e.renderjson.plugin2.cate_list);
var r=a.length;
r=r||2;
for(var p=0;r>p;++p){
var l=a[p];
this.cateList.push(this._createCateList(p,l));
}
this._renderTpl(this._getRenderJson()),s.on("click",".js_cname_item",function(){
s.find(".js_cname_item").removeClass("active"),$(this).addClass("active");
}),i.on("click",".js_add_nav",function(){
if(e.getCateLen()>=5)return o.err("最多只能添加5个分类"),!1;
var t=e.cateList.length,n={
cname:"",
appmsg_list:[]
},i=e._createCateList(t,n);
e.cateList.push(i),i.initShow();
}),r>0&&this.cateList[0].show();
},c.prototype._getRealCateIdx=function(t){
for(var e=this.json,n=0,i=e.length;i>n;++n)if(e[n]){
if(0>=t)return n;
t--;
}
return-1;
},c.prototype._getRealShowIdx=function(t){
for(var e=this.json,n=0,i=0;t>i;++i)e[i]&&n++;
return n;
},c.prototype._getRenderJson=function(){
for(var t=this.json,e=[],n=0,i=t.length;i>n;++n){
var s=t[n];
s&&e.push(s);
}
return{
plugin2:{
cate_list:e
}
};
},c.prototype._afterRenderTpl=function(){
var t=this.opt,e=t.container,n=this.selectTab||0,i=e.find(".js_cname_item"),s=i.length;
if(s>0&&(i.css({
width:100/s+"%"
}).removeClass("active").eq(n).addClass("active"),this.cateList)){
var a=this._getRealCateIdx(n);
a>=0&&a<this.cateList.length&&this.cateList[a].show();
}
},c.prototype.getRenderJson=function(t){
return t&&t.plugin2&&t.plugin2.cate_list&&t.plugin2.cate_list.length>0?t:$.extend(!0,{},this.default_json);
},c.prototype.getCateLen=function(){
for(var t=this.cateList,e=this.json,n=0,i=0,s=t.length;s>i;++i)e[i]&&t[i]&&n++;
return n;
},c.prototype.getEditData=function(){
for(var t=this.cateList,e=this.json,n=[],i=0,s=t.length;s>i;++i)if(e[i]&&t[i]){
var a=t[i],r=a.getEditData();
if(0==r)return a.show(),!1;
n.push(r);
}
return{
cate_list:n
};
},c.getRenderHTML=function(t){
var n="plugin",i={};
i[n]=t;
var a=e.replace(/<name>/gi,n);
return s.compile(a)(i);
},c;
});define("homepage/plugins/plugin1.js",["page/homepage/plugins/plugin1.css","tpl/homepage/plugins/plugin1.html.js","tpl/homepage/plugins/plugin1_edit.html.js","homepage/plugins/base.js","common/wx/wxt.js","homepage/importAppmsgList.js","common/wx/Tips.js"],function(t){
"use strict";
t("page/homepage/plugins/plugin1.css");
var i=t("tpl/homepage/plugins/plugin1.html.js"),e=t("tpl/homepage/plugins/plugin1_edit.html.js"),n=t("homepage/plugins/base.js"),p=n.base,s=n.inherit,o=t("common/wx/wxt.js"),g=t("homepage/importAppmsgList.js"),r=t("common/wx/Tips.js"),l={
plugin1:{
appmsg_list:[{
title:"封面示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6gnzTDiaOx5oOeMKOibNZ95hsY9aVozutJUSvqUvRmTxY2OqRvsTFeIyQ/640",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
},{
title:"封面示例",
cover:"http://mmbiz.qpic.cn/mmbiz/Q3auHgzwzM5wLlGTou7JL0oFS9yZGIK6gnzTDiaOx5oOeMKOibNZ95hsY9aVozutJUSvqUvRmTxY2OqRvsTFeIyQ/640",
aid:"0",
link:"javascript:void(0);",
digest:"摘要示例"
}]
}
},a=function(t){
var i=this;
i.opt=t,p.apply(this,arguments),this._initEditArea();
};
return s(a,p),a.prototype.tpl=i,a.prototype.edit_tpl=e,a.prototype.default_json=l,
a.prototype._initEditArea=function(){
var t=this.opt,i=this,e=t.idx,n=$("#js_plugin_edit_area_"+e),p=(n.find(".js_appmsg_list"),
[]);
i.renderjson.plugin1&&i.renderjson.plugin1.appmsg_list&&(p=i.renderjson.plugin1.appmsg_list),
i.isSorting=!1,i._importAppmsgList=new g({
container:n.find(".js_import_appmsglist"),
maxNum:3,
title:"封面文章",
list:p,
callback:function(t){
var e=$.extend(!0,{},i.default_json);
t&&t.length>0?(e={
plugin1:{
appmsg_list:t
}
},i.renderjson=e):i.renderjson={
plugin1:{
appmsg_list:[]
}
},i._renderTpl(e);
},
startSort:function(){
i.isSorting=!0;
},
endSort:function(){
i.isSorting=!1;
}
});
},a.prototype._getSelectList=function(){
var t=!!this.renderjson.plugin1&&this.renderjson.plugin1.appmsg_list;
if(t&&t.length>0){
for(var i=[],e=0,n=t.length;n>e;++e)i.push(t[e].aid);
return i;
}
return[];
},a.prototype.getRenderJson=function(t){
return t&&t.plugin1&&t.plugin1.appmsg_list&&t.plugin1.appmsg_list.length>0?t:$.extend(!0,{},this.default_json);
},a.prototype.getEditData=function(){
if(this.isSorting)return r.err("排序完成后才能发布"),!1;
var t=this._getSelectList();
return t.length>0?{
aid_list:t
}:(r.err("请至少选择一篇文章"),!1);
},a.getRenderHTML=function(t){
var e="plugin",n={};
n[e]=t;
var p=i.replace(/<name>/gi,e);
return o.compile(p)(n);
},a;
});define("tpl/advanced/homepage_list.html.js",[],function(){
return'{each list as item}\n<div class="col_tpl js_item" data-name="{item.name}" data-hid="{item.hid}" data-sn="{item.signature}">\n    <div class="msg_card">\n        <div class="msg_card_inner">\n            <div class="msg_card_bd">\n                <h4 class="msg_card_title">{item.name}</h4>\n                <div class="msg_card_info">\n                    <strong class="msg_card_info_meta">更新于{item.update_time}</strong>\n                </div>\n                <div class="msg_card_extra_info">\n                    <div class="simulator">\n                        <div class="simulator_hd">\n                            <h4 class="title">{nick_name}</h4>\n                        </div>\n                        <div class="simulator_bd">\n                            {=item.subhtml}\n                        </div>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n    <div class="homepage_mask"></div>\n    <i class="icon_card_selected">已选择</i>\n</div>\n{/each}\n';
});define("biz_web/ui/checkbox.js",["tpl/biz_web/ui/checkbox.html.js"],function(t){
"use strict";
function e(t){
var e=$(t);
e.each(function(){
var t=$(this),e=t.prop("checked"),n=t.parent();
e?n.addClass("selected"):n.removeClass("selected");
});
}
function n(t){
var e=$(t);
e.each(function(){
var t=$(this).prop("disabled"),e=$(this).parent();
t?e.addClass("disabled"):e.removeClass("disabled");
});
}
function i(){
return"checkbox"+s++;
}
var a={
container:null,
label:"",
name:"",
type:"checkbox"
},c=t("tpl/biz_web/ui/checkbox.html.js"),r=wx.T,s=1,o=1,p=function(t){
this.options=$.extend(!0,{},a,t),this.options.index=o++,this.$container=$(this.options.container),
this.$dom=$(r(c,this.options)).appendTo(this.$container),this.$input=this.$dom.find("input"),
this.$input.checkbox();
};
return p.prototype={
checked:function(t){
return"undefined"!=typeof t&&(this.$input.prop("checked",t),e(this.$input)),this.$input.prop("checked");
},
disabled:function(t){
return"undefined"!=typeof t&&(this.$input.prop("disabled",t),n(this.$input)),this.$input.prop("disabled");
}
},$.fn.checkbox=function(t){
var a,c,r,s,o=!1;
"boolean"==typeof t?a=t:$.isPlainObject(t)?(a=t.multi,c=t.onChanged):"string"==typeof t?(o=!0,
r=t,s=[].slice.call(arguments,1)):"undefined"==typeof t&&(t={}),"undefined"==typeof a&&(a=this.is("input[type=checkbox]"));
var p=this,d=a?"checkbox":"radio",h={
checked:function(t){
return p.attr("checked",t),p.prop("checked",t),e(p),p;
},
disabled:function(t){
return p.attr("disabled",t),p.prop("disabled",t),n(p),p;
},
value:function(){
var t=p.eq(0);
return t.prop("checked")?t.val():"";
},
values:function(){
var t=[];
return p.each(function(){
$(this).prop("checked")&&t.push($(this).val());
}),t;
},
adjust:function(t){
var n;
return n="string"==typeof t?t.split(","):t,n&&n.length>0&&p.each(function(){
var t=$(this);
n.indexOf(t.val())>=0&&(t.attr("checked",!0),e(t));
}),this;
},
disable:function(t){
var e;
return e="string"==typeof t?t.split(","):t,e&&e.length>0&&p.each(function(){
var t=$(this);
e.indexOf(t.val())>=0&&(t.attr("disabled",!0),n(t));
}),this;
},
setall:function(t){
p.each(function(){
var e=$(this);
e.attr("disabled",t?!1:!0),n(e);
});
},
enable:function(t){
var e;
return e="string"==typeof t?t.split(","):t,e&&e.length>0&&p.each(function(){
var t=$(this);
e.indexOf(t.val())>=0&&(t.attr("disabled",!1),n(t));
}),this;
},
label:function(t){
return t&&(p.parent().find(".lbl_content").text(t),p.attr("data-label",t)),p;
}
};
return o&&"function"==typeof h[r]?h[r].apply(h,s):(this.addClass("frm_"+d).each(function(){
var t=$(this),e=t.parent();
if(!e.is("label")){
var n=t.attr("data-label")||"";
e=$('<label class="frm_{type}_label"><i class="icon_{type}"></i></label>'.format({
type:d
})).append("<span class='lbl_content'>{content}</span>".format({
content:n.html(!0)
})),e.insertBefore(t).prepend(t);
}
if(!this.id){
var a=i();
this.id=a;
}
e.attr("for",this.id);
}),e(this),n(this),t&&t.initOnChanged&&"function"==typeof c&&p.parent().find("input[type=checkbox],input[type=radio]").each(function(){
c.call(h,$(this));
}),this.parent().delegate("input[type=checkbox],input[type=radio]","click",function(){
var t=$(this),n=t.prop("checked");
a?(t.attr("checked",n),e(t)):(p.attr("checked",!1),t.attr("checked",!0).prop("checked",!0),
e(p)),"function"==typeof c&&c.call(h,t);
}).addClass("frm_"+d+"_label"),h);
},p;
});define("tpl/advanced/appmsg_list.html.js",[],function(){
return'{each data as item}\n<tr data-link="{item.link}" data-title="{item.title||\'_(\\"未命名文章，无法选择\\")\'}">\n    <td class="table_cell">\n        <label class="frm_radio_label {if !item.cover||!item.title}disabled {/if}">\n            <i class="icon_radio"></i>\n            <span class="lbl_content">\n            {if !item.title}\n                （未命名文章，无法选择）            {else if !item.cover}\n                {if item.title.length>10}\n                    {item.title.substr(0,10)}...&nbsp;&nbsp;&nbsp;（文章未设置封面无法选择）                {else}\n                    {item.title}&nbsp;&nbsp;&nbsp;（文章未设置封面无法选择）                {/if}\n            {else}\n                {item.title}\n            {/if}\n            </span>\n            <input type="radio" name="hello" class="frm_radio" {if !item.cover||!item.title}disabled {/if}>\n        </label>\n    </td>\n    <td class="table_cell tr date"> {item.date}</td>\n</tr>\n{/each}\n';
});define("tpl/advanced/menu_link_dialog.html.js",[],function(){
return'<div>\n<div class="title_tab js_tab"></div>\n<div class="menu_tab_content">\n    <!--BEGIN 链接\n    <div class="link_insertion" style="display: none;">\n        <div class="frm_control_group">\n            <label class="frm_label" for="">请输入地址：</label>\n        <span class="frm_input_box">\n            <input class="frm_input" type="text"/>\n        </span>\n        </div>\n    </div>\n    END 链接-->\n    <!--BEGIN 已发送-->\n    <div class="media_lib js_content js_hassent">\n        <div class="loading js_loading">\n            <i class="icon_loading_small white"></i>\n        </div>\n        <div class="table_wrp js_hassent_list js_list">\n            <table class="table" cellspacing="0">\n                <thead class="thead">\n                    <tr>\n                        <td class="table_cell">标题</td>\n                        <td class="table_cell">发布日期</td>\n                    </tr>\n                </thead>\n                <tbody class="tbody"></tbody>\n            </table>\n        </div>\n        <div class="pagination_wrp js_pagebar"></div>\n    </div>\n    <!--END 已发送-->\n    <!--BEGIN 素材库-->\n    <div class="media_lib js_content js_appmsg" style="display: none;">\n        <div class="search_wrapper">\n            <span class="frm_input_box search append">\n                <a href="javascript:void(0);" class="frm_input_append js_search_btn" onclick="return false;">\n                    <i class="icon16_common search_gray">\n                        搜索\n                    </i>\n                    &nbsp;\n                </a>\n                <input type="text" placeholder="标题/作者/摘要" value="" class="frm_input js_search">\n            </span>\n        </div>\n        <div class="loading js_loading">\n            <i class="icon_loading_small white"></i>\n        </div>\n        <div class="table_wrp js_appmsg_list js_list">\n            <table class="table" cellspacing="0">\n                <thead class="thead">\n                    <tr>\n                        <td class="table_cell">标题</td>\n                        <td class="table_cell">发布日期</td>\n                    </tr>\n                </thead>\n                <tbody class="tbody"></tbody>\n            </table>\n        </div>\n        <div class="pagination_wrp js_pagebar"></div>\n    </div>\n    <!--END 素材库-->\n    <!--BEGIN 历史消息-->\n    <div class="history_msg js_content js_history">\n        <div class="preview_area">\n            <p class="desc">公众帐号历史消息列表示例</p>\n        </div>\n        <div class="form_area">\n            <label class="frm_checkbox_label">\n                <i class="icon_checkbox"></i>\n                <span class="lbl_content">跳转到历史消息列表</span>\n                <input type="checkbox" class="frm_checkbox">\n            </label>\n        </div>\n    </div>\n    <!--END 历史消息-->\n    <!--BEGIN 页面模板-->\n    <div class="homepage js_content js_homepage">\n        <div class="loading js_loading" style="display:none;">\n            <i class="icon_loading_small white"></i>\n        </div>\n        <div class="js_homepage_list js_list"></div>\n    </div>\n    <!--END 页面模板-->\n</div>\n</div>\n';
});define("common/wx/time.js",[],function(){
"use strict";
function e(e){
var t=new Date(1e3*e),r=new Date,g=t.getTime(),a=r.getTime(),u=864e5;
return u>a-g&&r.getDate()==t.getDate()?"%s:%s".sprintf(n(t.getHours()),n(t.getMinutes())):2*u>a-g&&new Date(1*t+u).getDate()==r.getDate()?"昨天 %s:%s".sprintf(n(t.getHours()),n(t.getMinutes())):6*u>=a-g?"%s %s:%s".sprintf(s[t.getDay()],n(t.getHours()),n(t.getMinutes())):t.getFullYear()==r.getFullYear()?"%s月%s日".sprintf(n(t.getMonth()+1),n(t.getDate())):"%s年%s月%s日".sprintf(t.getFullYear(),n(t.getMonth()+1),n(t.getDate()));
}
function t(e){
var t=new Date(1e3*e);
return"%s-%s-%s %s:%s:%s".sprintf(t.getFullYear(),n(t.getMonth()+1),n(t.getDate()),n(t.getHours()),n(t.getMinutes()),n(t.getSeconds()));
}
function r(e,t){
var r=["日","一","二","三","四","五","六"],n=t.replace(/yyyy|YYYY/,e.getFullYear()).replace(/yy|YY/,g(e.getFullYear()%100,2)).replace(/mm|MM/,g(e.getMonth()+1,2)).replace(/m|M/g,e.getMonth()+1).replace(/dd|DD/,g(e.getDate(),2)).replace(/d|D/g,e.getDate()).replace(/hh|HH/,g(e.getHours(),2)).replace(/h|H/g,e.getHours()).replace(/ii|II/,g(e.getMinutes(),2)).replace(/i|I/g,e.getMinutes()).replace(/ss|SS/,g(e.getSeconds(),2)).replace(/s|S/g,e.getSeconds()).replace(/w/g,e.getDay()).replace(/W/g,r[e.getDay()]);
return n;
}
function g(e,t){
for(var r=0,g=t-(e+"").length;g>r;r++)e="0"+e;
return e+"";
}
var n=function(e){
return e+="",e.length>=2?e:"0"+e;
},s=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
return{
timeFormat:e,
getFullTime:t,
formatDate:r
};
});define("common/wx/pagebar.js",["widget/pagination.css","tpl/pagebar.html.js","common/qq/Class.js","common/wx/Tips.js"],function(t,e){
"use strict";
var i,n,s,a=(t("widget/pagination.css"),t("tpl/pagebar.html.js")),r=t("common/qq/Class.js"),h=t("common/wx/Tips.js");
s=template.compile(a),i=e,n={
first:"首页",
last:"末页",
prev:"上页",
next:"下页",
startPage:1,
initShowPage:1,
perPage:10,
startRange:1,
midRange:3,
endRange:1,
totalItemsNum:0,
container:"",
callback:null,
isNavHide:!1,
isSimple:!0
};
var o=function(t,e,i){
var n;
return n=t+(e-1),n=n>i?i:n;
},g=function(t,e,i){
var n;
return n=i%2===0?e-(i/2-1):e-(i-1)/2,n=t>n?t:n;
},u=function(t,e,i){
var n;
return n=e%2===0?parseInt(t)+e/2:parseInt(t)+(e-1)/2,n=n>i?i:n;
},c=function(t,e,i){
var n;
return n=e-(i-1),n=t>n?t:n;
},l=function(t,e){
if(e[t]&&isNaN(e[t]))throw new Error("Invalid arguments: "+t+" should be a number");
},p=function(t){
if(l("perPage",t),l("totalItemsNum",t),l("startPage",t),l("startRange",t),l("midRange",t),
l("endRange",t),l("initShowPage",t),void 0!==t.callback&&null!==t.callback&&!$.isFunction(t.callback))throw new Error("Invalid arguments: callback should be a function");
},d=function(t,e,i){
var n=t.container.find(".page_"+i);
if("string"==typeof e){
var s=$(e);
0!==s.length&&(n=s);
}else{
if(e!==!1)throw new Error("Invalid Paramter: '"+i+"' should be a string or false");
n.hide();
}
return n;
},P=r.declare({
init:function(t){
if(t.totalItemsNum){
var e;
if(p(t),e=$.extend(!0,{},n,t),this._init(e),e.initShowPage<e.startPage)throw new Error("Invalid arguments: initShowPage should be larger than startPage");
if(e.initShowPage>e.endPage)throw new Error("Invalid arguments: initShowPage should be smaller than endPage");
this.paginate();
}
},
_init:function(t){
this.currentPage=t.initShowPage,this._isNextButtonShow=!0,this._isPrevButtonShow=!0,
this.uid="wxPagebar_"+ +new Date,this.initEventCenter(),this.optionsForTemplate={},
$.extend(this,t),this.container=$(t.container),this.optionsForTemplate.isSimple=t.isSimple,
this.optionsForTemplate.firstButtonText=0===$(t.first).length?t.first:n.first,this.optionsForTemplate.lastButtonText=0===$(t.last).length?t.last:n.last,
this.optionsForTemplate.nextButtonText=0===$(t.next).length?t.next:n.next,this.optionsForTemplate.prevButtonText=0===$(t.prev).length?t.prev:n.prev,
this.optionsForTemplate.isNavHide=t.isNavHide,this.generatePages(parseInt(this.totalItemsNum)),
this.gapForStartRange=this.container.find(".gap_prev"),this.gapForEndRange=this.container.find(".gap_next"),
this.firstButton=d(this,t.first,"first"),this.lastButton=d(this,t.last,"last"),this.prevButton=d(this,t.prev,"prev"),
this.nextButton=d(this,t.next,"next"),this.bindEvent();
},
initEventCenter:function(){
this.eventCenter={
eventList:[],
bind:function(t,e){
this.eventList[t]||(this.eventList[t]=[]),this.eventList[t].push(e);
},
trigger:function(t){
var e,i;
this.eventList[t]||(this.eventList[t]=[]),e=this.eventList[t];
for(var n=0;n<e.length;n++)if(i=Array.prototype.slice.call(arguments,1),e[n].apply(this,i)===!1)return!1;
},
unbind:function(t,e){
if(!this.eventList)throw new Error("The eventList was undefined");
if(!this.eventList[t])throw new Error("The event type "+t+" was not found");
if(void 0===e)this.eventList[t]=[];else for(var i=this.eventList[t],n=i.length,s=0;n>s;s++)if(i[s]===e){
i.splice(s,1);
break;
}
}
};
},
generatePages:function(t){
var e,i,n,a,r,h;
for(this.pageNum=Math.ceil(t/this.perPage),this.endPage=this.startPage+this.pageNum-1,
this.gapForStartRange=null,this.gapForEndRange=null,this.optionsForTemplate.startRange=[],
this.optionsForTemplate.midRange=[],this.optionsForTemplate.endRange=[],i=o(this.startPage,this.startRange,this.endPage),
n=g(this.startPage,this.currentPage,this.midRange),a=u(this.currentPage,this.midRange,this.endPage),
r=c(this.startPage,this.endPage,this.endRange),i>=r&&(r=i+1),e=this.startPage;i>=e;e+=1)this.optionsForTemplate.startRange.push(e);
for(var l=0,e=n;l<this.midRange;l+=1,e+=1)this.optionsForTemplate.midRange.push(e);
for(e=r;e<=this.endPage;e+=1)this.optionsForTemplate.endRange.push(e);
this.optionsForTemplate.endPage=this.endPage,this.optionsForTemplate.initShowPage=this.initShowPage,
h=s(this.optionsForTemplate),this.container.html(h),1==this.pageNum?this.container.hide():this.container.show(),
this.pages=this.container.find(".page_nav"),this.midPages=this.container.find(".js_mid"),
this.labels=this.container.find(".page_num label"),this.container.find(".pagination").attr("id",this.uid);
},
paginate:function(){
var t,e,i,n,s,a,r,h,l,p;
if(this.isSimple===!0)for(var d=0,P=this.labels.length;P>d;d++)d%2===0&&$(this.labels[d]).html(this.currentPage);else{
e=o(this.startPage,this.startRange,this.endPage),a=g(this.startPage,this.currentPage,this.midRange),
r=u(this.currentPage,this.midRange,this.endPage),h=c(this.startPage,this.endPage,this.endRange),
e>=h&&(h=e+1),e>=a&&(a=e+1),r>=h&&(r=h-1),this.pages.show(),this.pages.removeClass("current"),
p=parseInt(this.midPages.length/this.midRange);
for(var d=0,P=p;P>d;d++){
for(s=0,t=a;r>=t;t+=1)n=this.midRange*d+(t-a),l=$(this.midPages[n]),l.html(t),s+=1,
t==this.currentPage&&l.addClass("current");
for(n=this.midRange*d+s;s<this.midRange;s+=1)l=$(this.midPages[n]),l.hide(),l.removeClass("current"),
n+=1;
}
for(var d=0,P=this.pages.length;P>=d;d++)i=$(this.pages[d]),t=parseInt(i.html()),
t===parseInt(this.currentPage)&&i.addClass("current");
if(a>e+1?this.gapForStartRange.show():this.gapForStartRange.hide(),h>r+1?this.gapForEndRange.show():this.gapForEndRange.hide(),
this.isNavHide){
for(var d=this.startPage;d<=this.endPage;d+=1)this.pages.hide();
this.gapForStartRange.hide(),this.gapForEndRange.hide();
}
}
this.checkButtonShown();
},
destroy:function(){
this.container.off("click","#"+this.uid+" a.page_nav"),this.container.off("click","#"+this.uid+" a.page_go"),
this.container.off("keydown","#"+this.uid+" .goto_area input"),this.nextButton.off("click"),
this.prevButton.off("click"),this.firstButton.off("click"),this.lastButton.off("click");
},
bindEvent:function(){
this.container.on("click","#"+this.uid+" a.page_nav",this.proxy(function(t){
var e=$(t.target);
return e.hasClass("current")?!1:(this.clickPage(parseInt(e.html())),!1);
},this)),this.nextButton.on("click",this.proxy(function(t){
$(t.target);
return this.nextPage(),!1;
},this)),this.prevButton.on("click",this.proxy(function(t){
$(t.target);
return this.prevPage(),!1;
},this)),this.firstButton.on("click",this.proxy(function(t){
$(t.target);
return this.goFirstPage(),!1;
},this)),this.lastButton.on("click",this.proxy(function(t){
$(t.target);
return this.goLastPage(),!1;
},this)),this.container.on("click","#"+this.uid+" a.page_go",this.proxy(function(t){
var e=$(t.target).prev();
return this.goPage(e.val()),!1;
},this)),this.container.on("keydown","#"+this.uid+" .goto_area input",this.proxy(function(t){
return wx.isHotkey(t,"enter")?(this.container.find("a.page_go").click(),!1):void 0;
},this));
},
on:function(t,e){
this.eventCenter.bind(t,this.proxy(e,this));
},
callbackFunc:function(t){
var e={
currentPage:this.currentPage,
perPage:this.perPage,
count:this.pageNum
};
return $.isFunction(this.callback)&&this.callback(e)===!1?!1:this.eventCenter.trigger(t,e)===!1?!1:void this.paginate();
},
proxy:function(t,e){
return function(){
var i=Array.prototype.slice.call(arguments,0);
return t.apply(e,i);
};
},
nextPage:function(){
this.currentPage!==this.endPage&&(this.currentPage++,this.callbackFunc("next")===!1&&this.currentPage--);
},
prevPage:function(){
this.currentPage!==this.startPage&&(this.currentPage--,this.callbackFunc("prev")===!1&&this.currentPage++);
},
goFirstPage:function(){
var t=this.currentPage;
this.currentPage=this.startPage,this.callbackFunc("goFirst")===!1&&(this.currentPage=t);
},
goLastPage:function(){
var t=this.currentPage;
this.currentPage=this.endPage,this.callbackFunc("goLast")===!1&&(this.currentPage=t);
},
checkButtonShown:function(){
+this.currentPage===+this.startPage?this.hidePrevButton():this.showPrevButton(),
+this.currentPage===+this.endPage?this.hideNextButton():this.showNextButton();
},
goPage:function(t){
var e=this.currentPage,t=Math.round(t);
return t===this.currentPage?!1:isNaN(t)?(h.err("请输入正确的页码"),!1):""===t?!1:t<this.startPage?(h.err("请输入正确的页码"),
!1):t>this.endPage?(h.err("请输入正确的页码"),!1):(this.currentPage=t,void(this.callbackFunc("go")===!1&&(this.currentPage=e)));
},
clickPage:function(t){
var e=this.currentPage;
isNaN(t)&&(t=this.startPage),this.currentPage=t<this.startPage?this.startPage:t>this.endPage?this.endPage:t,
this.callbackFunc("click")===!1&&(this.currentPage=e);
},
showNextButton:function(){
this.nextButton&&this._isNextButtonShow===!1&&(this.nextButton.show(),this._isNextButtonShow=!0);
},
showPrevButton:function(){
this.prevButton&&this._isPrevButtonShow===!1&&(this.prevButton.show(),this._isPrevButtonShow=!0);
},
hideNextButton:function(){
this.nextButton&&this._isNextButtonShow===!0&&(this.nextButton.hide(),this._isNextButtonShow=!1);
},
hidePrevButton:function(){
this.prevButton&&this._isPrevButtonShow===!0&&(this.prevButton.hide(),this._isPrevButtonShow=!1);
}
});
return e=P;
});define("common/wx/top.js",["tpl/top.html.js"],function(t,a,e){
"use strict";
function i(t,a,e){
return this.dom=$(t),this.dom.addClass("title_tab"),a&&"string"==typeof a&&(a=[{
name:"",
url:"javascript:;",
className:"selected"
}]),$.each(a,function(t,a){
a.url=a.url&&[a.url,wx.data.param].join("")||"javascript:;";
}),this.dom.html(template.compile(n)({
data:a
})),e&&e.render&&"function"==typeof e.render?$.each(this.dom.find("li"),function(t,i){
e.render.apply($(i),[a[t],e&&e.data]);
}):this.dom.html(template.compile(n)({
data:a
})),this.dom.on("click",".top_item",function(){
$(this).addClass("selected").siblings().removeClass("selected");
}),this;
}
var n=t("tpl/top.html.js"),s=wx.acl;
i.prototype.selected=function(t){
this.dom.find(".js_top").removeClass("selected"),"number"==typeof t?this.dom.find(".js_top:eq("+t+")").addClass("selected"):this.dom.find(".js_top[data-id="+t+"]").addClass("selected");
},i.DATA={
setting:[{
id:"info",
name:"帐号详情",
url:"/cgi-bin/settingpage?t=setting/index&action=index"
},{
id:"function",
name:"功能设置",
url:"/cgi-bin/settingpage?t=setting/function&action=function"
}],
mass:[{
id:"send",
name:"新建群发消息",
url:"/cgi-bin/masssendpage?t=mass/send"
},{
id:"jurisdiction",
name:"授权申请",
acl:s&&s.msg_acl&&s.msg_acl.can_use_reprintapply_list,
url:"/cgi-bin/copyrightlib?action=reprint_article&begin=0&count=10&auth_status=0&lang=zh_CN"
},{
id:"list",
name:"已发送",
url:"/cgi-bin/masssendpage?t=mass/list&action=history&begin=0&count=10"
}],
message:[{
id:"total",
name:"全部消息",
url:"/cgi-bin/message?t=message/list&count=20&day=7"
},{
id:"star",
name:"已收藏的消息",
url:"/cgi-bin/message?t=message/list&count=20&action=star"
},{
id:"search",
name:"搜索结果"
}],
media:[{
id:"media11",
name:"商品消息",
acl:s&&s.material_acl&&s.material_acl.can_commodity_app_msg,
url:"/cgi-bin/appmsg?begin=0&count=10&t=media/appmsg_list&type=11&action=list"
},{
id:"media10",
name:"图文消息",
acl:s&&s.material_acl&&s.material_acl.can_app_msg,
url:"/cgi-bin/appmsg?begin=0&count=10&t=media/appmsg_list2&type=10&action=list_card"
},{
id:"media2",
name:"图片",
acl:s&&s.material_acl&&s.material_acl.can_image_msg,
url:"/cgi-bin/filepage?type=2&begin=0&count=12&t=media/img_list"
},{
id:"media3",
name:"语音",
acl:s&&s.material_acl&&s.material_acl.can_voice_msg,
url:"/cgi-bin/filepage?type=3&begin=0&count=21&t=media/list"
},{
id:"media15",
name:"视频",
acl:s&&s.material_acl&&s.material_acl.can_video_msg,
url:"/cgi-bin/appmsg?begin=0&count=9&t=media/appmsg_list&type=15&action=list"
}],
business:[{
id:"overview",
name:"数据概览",
url:"/merchant/business?t=business/overview&action=overview"
},{
id:"order",
name:"订单流水",
url:"/merchant/business?t=business/order&action=order"
},{
id:"info",
name:"商户信息",
url:"/merchant/business?t=business/info&action=info"
},{
id:"test",
name:"支付测试",
url:"/merchant/business?t=business/whitelist&action=whitelist"
},{
id:"rights",
name:"维权仲裁",
url:"/merchant/shop_rights?t=business/rights_list&action=batchgetpayfeedback"
},{
id:"course",
name:"使用教程",
url:"/merchant/business?t=business/course&action=course"
}],
user:[{
id:"useradmin",
name:"已关注",
url:"/cgi-bin/contactmanage?t=user/index&pagesize=10&pageidx=0&type=0&groupid=0"
}],
statistics:{
user:[{
id:"summary",
name:"用户增长",
url:"/misc/pluginloginpage?action=stat_user_summary&pluginid=luopan&t=statistics/index"
},{
id:"attr",
name:"用户属性",
url:"/misc/pluginloginpage?action=stat_user_attr&pluginid=luopan&t=statistics/index"
}],
article:[{
id:"detail",
name:"图文群发",
url:"/misc/pluginloginpage?action=stat_article_detail&pluginid=luopan&t=statistics/index"
},{
id:"analyse",
name:"图文统计",
url:"/misc/pluginloginpage?action=stat_article_analyse&pluginid=luopan&t=statistics/index"
}],
message:[{
id:"message",
name:"消息分析",
url:"/misc/pluginloginpage?action=stat_message&pluginid=luopan&t=statistics/index"
},{
id:"key",
name:"消息关键词",
url:"/misc/pluginloginpage?action=ctr_keyword&pluginid=luopan&t=statistics/index"
}],
"interface":[{
id:"interface",
name:"接口分析",
url:"/misc/pluginloginpage?action=stat_interface&pluginid=luopan&t=statistics/index"
}]
},
notification:[{
id:"notification",
name:"通知中心",
url:"/cgi-bin/frame?t=notification/index_frame"
}],
templateMessage:[{
id:"my_template",
name:"我的模板",
url:"/advanced/tmplmsg?action=list&t=tmplmsg/list"
},{
id:"template_message",
name:"模板库",
url:"/advanced/tmplmsg?action=tmpl_store&t=tmplmsg/store"
}],
assistant:[{
id:"mphelper",
name:"公众号助手",
url:"/misc/assistant?t=setting/mphelper&action=mphelper"
},{
id:"warning",
name:"接口告警",
url:"/misc/assistant?t=setting/warning&action=warning"
}],
shop:[{
id:"shopoverview",
name:"小店概况",
url:"/merchant/merchantstat?t=shop/overview&action=getoverview"
},{
id:"addGoods",
name:"添加商品",
url:"/merchant/goods?type=11&t=shop/precreate",
target:"_blank"
},{
id:"goodsManagement",
name:"商品管理",
url:"/merchant/goodsgroup?t=shop/category&type=1"
},{
id:"shelfManagement",
name:"货架管理",
url:"/merchant/shelf?status=0&action=get_shelflist&t=shop/myshelf&offset=0&count=5"
},{
id:"orderManagement",
name:"订单管理",
url:"/merchant/productorder?action=getlist&t=shop/order_list&last_days=30&count=10&offset=0"
},{
id:"deliverylist",
name:"运费模板管理",
url:"/merchant/delivery?action=getlist&t=shop/delivery_list"
},{
id:"images",
name:"图片库",
url:"/merchant/goodsimage?action=getimage&t=shop/shop_img&count=20&offset=0"
}],
adClient:[{
id:"adclientreport",
name:"报表统计",
url:"/merchant/ad_client_report?t=ad_system/client_report&action=list"
},{
id:"adclientmanage",
name:"广告管理",
url:"/merchant/advert?t=ad_system/promotion_list&action=get_advert_count"
},{
id:"materialmanage",
name:"推广页管理",
url:"/merchant/ad_material?t=material/list&action=get_material_list"
},{
id:"adclientpay",
name:"财务管理",
url:"/cgi-bin/frame?nav=10026&t=ad_system/host_frame"
},{
id:"adservice",
name:"广告服务商",
acl:s&&s.ad_system&&s.ad_system.can_use_sp,
url:"/cgi-bin/frame?nav=10026&t=ad_system/client_service_frame"
}],
adHost:[{
id:"adhostreport",
name:"报表统计",
url:"/merchant/ad_host_report?t=ad_system/host_report"
},{
id:"adhostmanage",
name:"流量管理",
url:"/merchant/ad_host_manage?t=ad_system/host_manage"
},{
id:"adhostpay",
name:"财务管理",
url:"/merchant/ad_host_pay?action=ad_host_pay&t=ad_system/host_pay"
}],
advanced:[{
id:"dev",
name:"日志查询",
url:"/advanced/advanced?action=log_home"
},{
id:"group-alert",
name:"接口报警",
url:"/advanced/advanced?action=alarm&t=advanced/alarm"
}],
cardticket:[{
id:"cardmgr",
name:"卡券管理",
url:"/merchant/electroniccardmgr?action=batch&t=cardticket/batch_card"
},{
id:"permission",
name:"卡券核销",
url:"/merchant/carduse?action=listchecker&t=cardticket/permission"
},{
id:"carduse",
name:"核销记录",
url:"/merchant/carduserecord?action=listrecord&t=cardticket/carduse_record"
},{
id:"cardreport",
name:"数据报表",
url:"/merchant/ecardreport?action=overviewpage&t=cardticket/overviewpage"
}],
infringement:[{
id:"infringement",
name:"我要投诉",
url:"/acct/infringement?action=getmanual&t=infringement/manual&type=1"
},{
id:"antiinfringement",
name:"我要申诉",
url:"/acct/infringement?action=getmanual&t=infringement/manual&type=2"
},{
id:"list",
name:"提交记录",
url:"/acct/infringement?action=getlist&t=infringement/ingringement_list&type=1&begin=0&count=10"
}],
scan:[{
id:"overview",
name:"数据概况",
url:"/merchant/scandataoverview?action=keydata"
},{
id:"product_list",
name:"商品管理",
url:"/merchant/scanproductlist?action=list&page=1&status=1"
},{
id:"firmcat_list",
name:"资质管理",
url:"/merchant/scanqualification?action=firmcatpage"
}],
rumor:[{
id:"list",
name:"谣言池",
url:"/misc/rumor?action=rumorlist&t=rumor/list"
},{
id:"result",
name:"辟谣结果",
url:"/misc/rumor?action=summarylist&t=rumor/result"
}],
reward:[{
id:"list",
name:"数据概况",
url:"/merchant/rewardstat?action=getoverview&t=reward/overview"
},{
id:"setting",
name:"赞赏设置",
url:"/merchant/reward?action=rewardsetting"
}],
discuss:[{
id:"list_latest",
name:"留言列表",
url:"/misc/appmsgcomment?action=list_latest_comment&begin=0&count=10&mp_version=7"
},{
id:"index",
name:"文章管理",
url:"/misc/appmsgcomment?action=list_app_msg&begin=0&count=10"
}],
search:[{
id:"search",
name:"搜索",
url:"/advanced/componentsearch?action=search"
},{
id:"authorized",
name:"已添加",
url:"/cgi-bin/component_unauthorize?action=list&t=service/auth_plugins"
}],
kf:[{
id:"account",
name:"账号管理",
url:"/misc/kf?t=services/list&action=list"
},{
id:"state",
name:"客服数据",
url:"/misc/kf?t=services/kf_stat&action=getstatpage"
},{
id:"media",
name:"客服素材",
url:"/misc/kf?t=services/kf-public-text&action=publicreplypage"
}],
ibeacon:[{
id:"deviceManagement",
name:"设备管理",
url:"/merchant/getdevices?action=list"
},{
id:"pageManagement",
name:"页面管理",
url:""
},{
id:"dataReport",
name:"数据报表",
url:""
}]
},s&&s.ad_system&&s.ad_system.can_use_new_ad&&(i.DATA.adClient[0].url="/cgi-bin/frame?nav=10026&t=ad_system/client_report_frame",
i.DATA.adClient[1].url="/cgi-bin/frame?nav=10026&t=ad_system/promotion_list_frame"),
s&&s.merchant_acl&&s.merchant_acl.can_use_account_manage&&i.DATA.adClient.push({
id:"adclientaccountmanage",
name:"账户管理",
acl:s&&s.ad_system&&s.ad_system.can_use_account_manage,
url:"/cgi-bin/frame?nav=10026&t=ad_system/account_frame"
}),s&&s.merchant_acl&&s.merchant_acl.can_use_pay_tmpl&&i.DATA.templateMessage.push({
id:"template_pay_list",
name:"支付模板消息",
url:"/advanced/tmplmsg?action=pay_list&t=tmplmsg/payment"
}),i.RENDER={
setting:function(t,a){
"meeting"==t.id&&15!=a.role&&this.remove();
},
message:function(t,a){
"search"!=t.id||a&&"search"==a.action||this.remove();
},
assistant:function(t,a){
"warning"!=t.id||a&&0!=a.have_service_package||this.remove();
},
reward:function(t,a){
"invite"!=t.id||a&&0!=a.invite_authority||this.remove();
}
},e.exports=i;
});define("biz_web/lib/spin.js", [], function(e, t, n) {
try {
var r = +(new Date), i = function() {
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
var n = [ p, t, ~~(e * 100) ].join("-"), r = "{" + p + ":" + e + "}", i;
if (!H[n]) {
for (i = 0; i < P[a]; i++) try {
j.insertRule("@" + (P[i] && "-" + P[i].toLowerCase() + "-" || "") + "keyframes " + n + "{0%{" + p + ":1}" + t + "%" + r + "to" + r + "}", j.cssRules[a]);
} catch (s) {}
H[n] = 1;
}
return n;
}
function i(e, t) {
var n = e[m], r, i;
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
var u = "width", a = "length", f = "radius", l = "lines", c = "trail", h = "color", p = "opacity", d = "speed", v = "shadow", m = "style", g = "height", y = "left", b = "top", w = "px", E = "childNodes", S = "firstChild", x = "parentNode", T = "position", N = "relative", C = "absolute", k = "animation", L = "transform", A = "Origin", O = "Timeout", M = "coord", _ = "#000", D = m + "Sheets", P = "webkit0Moz0ms0O".split(0), H = {}, B;
n(document.getElementsByTagName("head")[0], t(m));
var j = document[D][document[D][a] - 1], F = function(e) {
this.opts = o(e || {}, l, 12, c, 100, a, 7, u, 5, f, 10, h, _, p, .25, d, 1);
}, I = F.prototype = {
spin: function(e) {
var t = this, r = t.el = t[l](t.opts);
e && n(e, s(r, y, ~~(e.offsetWidth / 2) + w, b, ~~(e.offsetHeight / 2) + w), e[S]);
if (!B) {
var i = t.opts, o = 0, u = 20 / i[d], a = (1 - i[p]) / (u * i[c] / 100), f = u / i[l];
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
var e = this, t = e.el;
return window["clear" + O](e[O]), t && t[x] && t[x].removeChild(t), e.el = undefined, e;
}
};
I[l] = function(e) {
function i(n, r) {
return s(t(), T, C, u, e[a] + e[u] + w, g, e[u] + w, "background", n, "boxShadow", r, L + A, y, L, "rotate(" + ~~(360 / e[l] * E) + "deg) translate(" + e[f] + w + ",0)", "borderRadius", "100em");
}
var o = s(t(), T, N), m = r(e[p], e[c]), E = 0, S;
for (; E < e[l]; E++) S = s(t(), T, C, b, 1 + ~(e[u] / 2) + w, L, "translate3d(0,0,0)", k, m + " " + 1 / e[d] + "s linear infinite " + (1 / e[l] / e[d] * E - 1 / e[d]) + "s"), e[v] && n(S, s(i(_, "0 0 4px " + _), b, 2 + w)), n(o, n(S, i(e[h], "0 0 1px rgba(0,0,0,.1)")));
return o;
}, I[p] = function(e, t, n) {
e[E][t][m][p] = n;
};
var q = "behavior", R = "url(#default#VML)", U = "group0roundrect0fill0stroke".split(0);
return function() {
var e = s(t(U[0]), q, R), r;
if (!i(e, L) && e.adj) {
for (r = 0; r < U[a]; r++) j.addRule(U[r], q + ":" + R);
I[l] = function() {
function e() {
return s(t(U[0], M + "size", c + " " + c, M + A, -o + " " + -o), u, c, g, c);
}
function r(r, a, c) {
n(d, n(s(e(), "rotation", 360 / i[l] * r + "deg", y, ~~a), n(s(t(U[1], "arcsize", 1), u, o, g, i[u], y, i[f], b, -i[u] / 2, "filter", c), t(U[2], h, i[h], p, i[p]), t(U[3], p, 0))));
}
var i = this.opts, o = i[a] + i[u], c = 2 * o, d = e(), m = ~(i[a] + i[f] + i[u]) + w, E;
if (i[v]) for (E = 1; E <= i[l]; E++) r(E, -2, "progid:DXImage" + L + ".Microsoft.Blur(pixel" + f + "=2,make" + v + "=1," + v + p + "=.3)");
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
var n = $(this), r = n.data();
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
});define("tpl/media/cardmsg.html.js",[],function(){
return'<div class="msg_card{if _className} {_className}{/if}">\n	<div class="card_content" style="background-color: {color};">\n		<img class="logo js_logourl" data-src="{logo_url}" />\n		<div class="card_info">\n			<h4 class="card_title">{title}</h4>\n		</div>\n		<div class="deco"></div>\n	</div>\n	<p class="store">{brand_name}</p>\n</div>\n';
});!function(){
if(!window.define){
var t={};
window.define=function(e,i,n){
t[e]=n;
},window.seajs={
use:function(e){
function i(e){
return t[e]&&t[e](i);
}
return t[e]&&t[e](i);
}
};
}
}(),define("biz_common/utils/norefererimg.js",[],function(){
function t(t){
return window.getComputedStyle?window.getComputedStyle(t):t.currentStyle;
}
function e(t,e,i,n){
if(t&&i){
if(t==window&&"load"==e&&/complete|loaded/.test(document.readyState))return void i({
type:"load"
});
var o=function(t){
var e=i.call(this,t);
e===!1&&(t.stopPropagation&&t.stopPropagation(),t.preventDefault&&t.preventDefault());
};
return i[e+"_handler"]=o,t.addEventListener?void t.addEventListener(e,o,!!n):t.attachEvent?void t.attachEvent("on"+e,o,!!n):void 0;
}
}
return function(i){
var n=i.img,o=i.imgurl,r=i.onload,d=o||n.getAttribute("data-src"),a=/^http:\/\/mmbiz\.qpic\.cn\/|https:\/\/mmbiz\.qlogo\.cn\//;
if(d){
if(n.length&&(n=n[0]),a.test(d))return e(n,"load",r),void n.setAttribute("src",d);
var s=t(n),l=["javascript:\"<html><body style='margin:0;padding:0;'><img style='width:",s.width,";height:",s.height,";' src='",d+"' /></body></html>\""].join(" "),u=document.createElement("iframe");
u.setAttribute("frameBorder","0"),u.setAttribute("scrolling","no"),u.style.width="60px",
u.style.width=s.width+"",u.style.height=s.height,u.style.display=s.display,u.style.borderRadius=s.borderRadius,
u.style.webkitBorderRadius=s.borderRadius,"function"==typeof r&&(u.attachEvent?u.attachEvent("onload",r):u.onload=r),
u.className=n.className;
var c=n.parentNode;
c.insertBefore(u,n),u.src=l,c.removeChild(n);
}
};
});