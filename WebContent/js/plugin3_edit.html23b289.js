define("tpl/cardticket/send_card.html.js",[],function(){
return'<div>\n	<div class="wrp_processor js_step_container"></div>\n	<div class="first_step js_step_content js_step1">\n	    <!--选择投放方式弹窗-->\n		<div class="js_card_list"></div>\n		<!--选择投放方式弹窗 end-->\n	</div>\n	<div class="second_step js_step_content js_step2">\n	</div>\n</div>';
});define("cardticket/send_card_table.js",["common/wx/Tips.js","common/wx/Cgi.js","common/wx/Step.js","common/wx/pagebar.js","cardticket/parse_data.js","biz_web/ui/dropdown.js","cardticket/store_cgi.js","cardticket/common_template_helper.js","cardticket/create_card_select.js","tpl/cardticket/card_table.html.js","tpl/cardticket/card_preview.html.js","page/cardticket/dialog_choose_card.css","biz_web/ui/checkbox.js","cardticket/card_quantity.js"],function(t){
"use strict";
function e(t){
{
var e;
t.opt;
}
e=t.$container,e.find(".js_card_list").html(y({
loading:!0
}));
}
function a(t,a){
var r=a.opt,n=$.extend(!0,{
action:"batch",
begin:t.begin,
count:t.count,
tag_filter:r.tag_filter,
filter_out_expired_card:r.filter_out_expired_card
},r.param);
1==r.view_mode&&(n.sub_merchant_id=0),j=!0,e(a),d.get({
url:r.url||"/merchant/electroniccardmgr",
data:n,
complete:function(){
j=!1;
}
},function(t){
if(0==t.base_resp.ret){
var e=t,n=t.card_dispatching_list;
t="string"==typeof t.batch_card?$.parseJSON(t.batch_card):t.batch_card,r.data=t.card_list;
var c=u.parse_cardlist(r.data);
if(w=c.card_cache,r.data=c.card_list,r.cache_data=w,r.acl={
is_can_shake:e.is_can_shake_card,
is_can_use_sns_card:e.is_can_use_sns_card,
is_intercomm_card:e.is_intercomm_card,
is_can_card_friend:e.is_can_use_sns_card
},n)try{
var s=wx.parseJSON(e,"card_dispatching_list");
if(s){
s=s.card_dispatching_list;
for(var o=0;o<s.length;o++){
var _=s[o],l=w[_.card_id];
l&&(l.cansend=!_.is_dispatching);
}
}
}catch(p){}
if(r.pageInfo.total_count=t.total_num,e.biz_quota_json){
var f=wx.parseJSON(e,"biz_quota_json");
f=h.parse_assistsend_quota(f.quota_list),a._quota=f;
}
i(r.pageInfo,a);
}else d.show(t);
});
}
function i(t,e,a){
var i,_=e.opt;
if(_.payflag=_.param.flag,i=e.$container,a){
var d=i.find(".js_select");
return d.each(function(e){
e>=t.begin&&e<t.begin+t.count?$(this).closest("tr").show():$(this).closest("tr").hide();
}),e.pagebar=null,o(_.pageInfo,e),void(e.opt.getDataComplete&&e.opt.getDataComplete.call(e,a));
}
if(_.data&&"undefined"!=typeof _.sub_merchant_id)for(var l=0;l<_.data.length;l++)_.sub_merchant_id?_.data[l].sub_merchant_id!=_.sub_merchant_id&&(_.data[l].is_sub_merchant_disabled=!0):_.data[l].sub_merchant_id&&(_.data[l].is_sub_merchant_disabled=!0);
i.find(".js_card_list").html(y(_));
var u=_.defaultValues,d=i.find(".js_select");
u.length&&d.each(function(){
for(var t=$(this),e=0;e<u.length;e++)if(u[e]==t.attr("data-id")){
t.prop("checked",!0);
break;
}
}),e.select_card_checkbox=d.checkbox({
onChanged:function(){
if(_.multi){
var t=0;
d.each(function(){
$(this).prop("checked")&&t++;
}),$(".js_selectcount",i).text(t);
}
}
}),e.pagebar=null,o(_.pageInfo,e),n(e),c(e),s(e),r(e);
var p,f=[];
1==_.sns_card_type?p=d.filter(".js_select_disabled_1"):2==_.sns_card_type&&(p=d.filter(".js_select_disabled_2")),
p&&(p.each(function(){
f.push($(this).val());
}),e.select_card_checkbox.disable(f)),$(".js_add_card_link",i).click(function(){
return new m({
ispay:_.payflag,
is_sns_card:window.wx_is_can_use_sns_card
}),e.opt.hidePopup&&e.opt.hidePopup(),!1;
}),e.opt.getDataComplete&&e.opt.getDataComplete.call(e,a);
}
function r(t){
var e=t.opt;
if("2"!=e.sns_card_type){
var i=[];
1==e.sns_card_type?i=[{
name:"全部卡券",
value:"friends,0"
}]:0==e.sns_card_type&&(i=[{
name:"全部卡券",
value:""
}],e.acl.is_can_card_friend&&i.push({
name:"朋友共享的券",
value:"friends,1"
})),e.acl.is_can_shake&&i.push({
name:"摇一摇",
value:"shake,1"
}),e.acl.is_intercomm_card;
var r=t.base_tag_filter?"|":"",n={};
if(n[t.base_tag_filter+r+"task,1"]="互通",n[t.base_tag_filter+r+"shake,1"]="摇一摇",n[t.base_tag_filter+r+"friends,1"]="朋友的券",
i.length>1){
new p({
container:$(".js_filter_tag",t.$container),
label:n[e.tag_filter]||"全部卡券",
data:i,
callback:function(i){
var r=t.base_tag_filter+(t.base_tag_filter&&i?"|"+i:i);
r!=e.tag_filter&&(e.tag_filter=r,a(e.pageInfo,t));
}
});
}
}
}
function n(t){
function e(e){
var i=$.trim(n.val());
(!e||e&&wx.isHotkey(e,"enter"))&&(c.param.keyword=i,a(c.pageInfo,t));
}
var i=t.$container,r=$(".js_search",i),n=$(".js_keyword",i),c=t.opt;
r.click(function(){
e();
}),n.keyup(function(t){
e(t);
}),n.val(c.param.keyword);
}
function c(t){
var e=t.$container,a=e.find(".js_card_preview");
a.on("click",function(t){
var e=$(this).data("cid"),a=w[e];
if(a){
var i=($(this),$(this).offset());
$(".js_pop_card_preview").remove();
var r=$(b({
card:a
})).appendTo(document.body).hide();
r.css({
position:"fixed",
left:i.left-r.outerWidth()-10,
top:"50%",
"margin-top":-1*r.outerHeight()/2,
"z-index":"10000"
}).show();
var n=r.offset();
return r.find(".js_arrow").css({
top:i.top-n.top
}),$(document).one("click",function(){
r.remove();
}),t.stopPropagation(),!1;
}
});
}
function s(t){
var e=t.$container,a=e.find(".js_modify_quantity");
a.each(function(){
var e=$(this),a=1*e.attr("data-new")||0;
new x({
container:e,
mode:"fixed",
cache_card:t.opt.cache_data,
setquantity:a?!0:!1,
max_sku_for_eachcard:t._quota&&t._quota.max_sku||1e4,
quantityChange:function(t,a){
var i=w[t];
if(i){
if(i.pay_info.is_swipe_card)return i.pay_info.swipe_card_status=1,void e.hide();
i.quantity=this.opt.setquantity?i.quantity+a:a,e.attr("data-new",1),i.isnew=!0,this.opt.setquantity=!0,
$("#js_ct_tr_"+t).find(".js_sendcard_quantity").text(i.quantity);
}
}
});
});
}
function o(t,e){
var r=t.total_count,n=e.$container;
if(t.count&&r>t.count){
var c=t.begin/t.count;
e.pagebar=new l({
container:$(".js_pager",n),
first:!1,
last:!1,
midRange:5,
initShowPage:c+1,
perPage:t.count,
totalItemsNum:r,
callback:function(r){
if(j)return!1;
var n=r.currentPage;
return n!=c+1&&(t.begin=(n-1)*t.count,e.opt.hasdata&&e.opt.data?i(t,e,!0):a(t,e)),
e.opt.pageChanged&&e.opt.pageChanged.call(e),!0;
}
});
}
}
var _=t("common/wx/Tips.js"),d=t("common/wx/Cgi.js"),l=(t("common/wx/Step.js"),t("common/wx/pagebar.js")),u=t("cardticket/parse_data.js"),p=t("biz_web/ui/dropdown.js"),f=t("cardticket/store_cgi.js"),h=t("cardticket/common_template_helper.js"),m=t("cardticket/create_card_select.js"),g={
multi:!1,
pageInfo:{
begin:0,
count:5,
total_count:0
},
param:{
keyword:"",
status:"3|6",
flag:2
},
neednew:!0,
noexpire:!0,
editquantity:!0,
onHide:$.noop,
selectComplete:$.noop,
data:null,
hasdata:!1,
maxcount:10,
sns_card_type:1,
defaultValues:[],
url:"",
removeOnHide:!0,
source:"",
has_sendout:!1,
acl:{},
view_mode:0,
sub_merchant_id:void 0,
filter_out_expired_card:1
},v=t("tpl/cardticket/card_table.html.js"),b=template.compile(t("tpl/cardticket/card_preview.html.js")),w={};
t("page/cardticket/dialog_choose_card.css"),t("biz_web/ui/checkbox.js");
var k=function(t){
this.opt=$.extend(!0,{},g,t),this.opt.tag_filter=0==this.opt.sns_card_type?"":2==this.opt.sns_card_type?"friends,1":"friends,0",
this.init();
},y=template.compile(v),j=!1,x=t("cardticket/card_quantity.js");
return k.prototype={
_html:v,
init:function(){
var t=this.opt,e=this;
if(this.$container=$(t.container),e.base_tag_filter="",2==t.view_mode&&(e.base_tag_filter="sub_merchant,1",
t.tag_filter=t.tag_filter?e.base_tag_filter+"|"+t.tag_filter:e.base_tag_filter),
t.hasdata&&t.data){
t.pageInfo.total_count=t.data.length,w={};
for(var r=0;r<t.data.length;r++){
var n=t.data[r];
w[n.id]=n;
}
i(t.pageInfo,this);
}else a(t.pageInfo,this);
},
show:function(){
this.$container.show();
},
select:function(){
if(!j){
var t=this,e=this.opt,a=t.select_card_checkbox.values()[0],i=this.$container,r=w[a];
if(!a||!r)return void _.err("请选择卡券");
if(!e.neednew||!r.pay_info.is_swipe_card||0==r.pay_info.swipe_card_status||0!=r.quantity){
if(e.multi)for(var n=t.select_card_checkbox.values(),c=0;c<n.length;c++){
var s=w[n[c]];
if(e.neednew&&(!s.isnew||0==s.quantity))return void _.err("卡券库存不能为0，请先设置库存再投放");
}else if(e.neednew&&(!r.isnew||0==r.quantity))return _.err("卡券库存不能为0，请先设置库存再投放"),
void setTimeout(function(){
var t=i.find("input[data-id="+a+"]");
$(t.closest("tr").find(".js_modify_quantity")[0]).click();
},50);
if(!e.multi&&e.noexpire&&r.is_expire)return void _.err(r.is_sns_card?"卡券已过期":"卡券已过期，无法投放，请到卡券详情去延长有效期再投放");
if(e.multi&&e.noexpire)for(var n=t.select_card_checkbox.values(),c=0;c<n.length;c++){
var s=w[n[c]];
if(s.is_expire)return void _.err("不能选择已过期的卡券，请先到卡券详情去延长有效期");
}
var n=t.select_card_checkbox.values();
return n.length>e.maxcount?void _.err("最多只能选择%s个卡券".sprintf(e.maxcount)):2!=e.sns_card_type||r.is_sns_card?1==e.sns_card_type&&r.is_sns_card?void _.err("朋友的券只能进行社交投放, 请重新选择"):"undefined"!=typeof e.sub_merchant_id&&r.is_sub_merchant_disabled?void _.err("不支持赠送其他商户的“朋友的券”，请重新选择。"):void f.canSendCard({
card_id:a,
success:function(a){
if(a===!1)_.err("没有“审核通过”的门店。确认有至少一个“审核通过”的门店后可进行投放。");else if(a===!0){
var i=t.select_card_checkbox.values(),r=e.multi?i:i,n=[];
if(e.multi)for(var c=0;c<r.length;c++)w[r[c]].cardid=w[r[c]].id,n.push(w[r[c]]);else n=w[r],
n.cardid=w[r].id;
e.selectComplete&&e.selectComplete(n,0);
}
}
}):void _.err("朋友的券才能进行社交投放, 请重新选择");
}
switch(r.pay_info.swipe_card_status){
case 1:
_.err("添加库存暂未生效，待商户审核完成");
break;

case 3:
_.err("请先激活本券");
break;

case 2:
case 4:
_.err("卡券库存不能为0，请先设置库存再投放");
}
}
},
isLoading:function(){
return j;
},
hide:function(){
this.$container.hide();
},
destroy:function(){
this.$container.remove();
}
},k;
});define("common/wx/Step.js", [ "widget/processor_bar.css", "tpl/step.html.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = wx.T, s = e("widget/processor_bar.css"), o = e("tpl/step.html.js"), u = {
selected: 1
}, a = function() {
var e = navigator.userAgent.toLowerCase(), t = /(msie) ([\w.]+)/.exec(e) || [], n = t[1] || "";
return n == "msie";
};
function f(e) {
this.opts = $.extend(!0, {}, u, e), this.init();
}
f.prototype.init = function() {
var e = this.opts, t = e.names.length, n = parseInt(e.selected, 10), r = [], s, u;
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
var t = this.$dom.find("li.step"), n = t.length;
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
});define("tpl/media/dialog/image_water.html.js",[],function(){
return'<div>\n    {if status == 3}\n    <p>不添加水印</p>\n    {else if status == 2}\n    <p>水印类型：公众号名称</p>\n    {else if status == 1}\n    <p>水印类型：微信号</p>\n    {/if}\n\n    <p>\n        {if status == 3}\n        <span>已关闭水印，所有上传的图片都不会带有水印。</span>\n        {else}\n        <span>已开启水印，所有上传的图片都会带有水印。</span>\n        {/if}\n        若需修改请前往        <a target="_blank" href="{set_water_url}">公众号设置/功能设置</a>\n        设置图片水印    </p>\n</div>\n';
});define("tpl/media/dialog/image_group.html.js",[],function(){
return'{each file_group_list.file_group as item}\n<dd id="js_group{item.id}" class="inner_menu_item js_groupitem{if item.id == group} selected{/if}" data-groupid="{item.id}">\n    <a href="javascript:;" class="inner_menu_link" title="{item.name}" onclick="return false">\n        <strong>{item.name}</strong><em class="num">(<span>{item.count}</span>)</em>\n    </a>\n</dd>\n{/each}\n';
});define("tpl/media/dialog/image_list.html.js",[],function(){
return'{if file_item.length == 0}\n<li class="empty_tips">该分组暂时没有图片素材</li>\n{else}\n{each file_item as item}\n<li class="img_item js_imageitem" data-id="{item.file_id}" data-url="{item.cdn_url}" data-oristatus="{item.copyright_status||0}" data-format="{item.img_format}">\n    <label class="frm_checkbox_label{if item.selected} selected{/if} img_item_bd">\n        {if scene == \'cdn\' && item.cdn_url}\n        <!-- <span class="pic cover js_pic" style="background-image:url({item.cdn_url});"></span> -->\n        <div class="pic_box">\n            <img class="pic js_pic" data-src="{item.cdn_url}"></img>\n        </div>\n        <!-- <img src="{item.cdn_url}" alt="{item.name}" class="pic"> -->\n        {else}\n        <div class="pic_box">\n            <img class="pic js_pic" data-src="{item.img_url}"></img>\n        </div>\n        <!-- <span class="pic cover js_pic" style="background-image:url({item.img_url});"></span> -->\n        <!-- <img src="{item.img_url}" alt="{item.name}" class="pic"> -->\n        {/if}\n        <span class="lbl_content">\n            {if item.copyright_status==2}\n            <i class="icon_original accessed"></i>\n            {/if}            \n            {item.name}\n        </span>\n        <div class="selected_mask">\n            <div class="selected_mask_inner"></div>\n            <div class="selected_mask_icon"></div>\n        </div>\n    </label>\n</li>\n{/each}\n{/if}\n';
});define("tpl/media/dialog/image_layout.html.js",[],function(){
return'<div class="img_pick_panel inner_container_box side_l cell_layout">\n    <div class="inner_side">\n        <div class="group_list">\n            <div class="inner_menu_box">\n                <dl class="inner_menu js_group"></dl>\n            </div>                    \n        </div>\n    </div>\n    <div class="inner_main">\n        <div class="img_pick_area">\n            <div class="sub_title_bar in_dialog">\n                <div class="upload_box r align_right">\n                    <span class="upload_area"><a id="js_imageupload" class="btn btn_primary js_imageupload" data-groupid="">本地上传</a></span>\n                </div>\n                <div class="img_water_tips mini_tips icon_after r weak_text">\n                    {if desc}{desc}{/if}<span class="js_water"></span>\n                    <i class="js_water_tips icon_msg_mini ask"></i>\n                </div>\n            </div>\n            <div>\n                <div class="img_pick">\n                    <i class="icon_loading_small white js_loading"></i>\n                    <ul class="group js_list img_list"></ul>\n                </div>\n                <div class="js_pagebar"></div>\n            </div>\n        </div>\n    </div>\n    <p class="dialog_ft_desc">已选<span class="js_selected">0</span>个，可选{maxSelect}个</p>\n</div>\n';
});define("common/wx/tooltips.js",["tpl/tooltips.html.js"],function(o,t,n){
"use strict";
var i={
position:{},
container:"",
type:"hover",
buttons:[],
delay:300,
disabled:!1,
reposition:!1,
container_close:!1,
parentClass:"",
container_mode:"absolute"
},s=wx.T,e=o("tpl/tooltips.html.js"),p="btn_disabled",c="hover",h="show",a=function(o){
if(this.options=o=$.extend(!0,{},i,o),this.$container=$(this.options.container),
this.$container&&0!=this.$container.length){
var t=this.$container.offset(),n=this.$container.height(),a=this.options.position.left||this.$container.data("x")||0,l=n+(this.options.position.top||this.$container.data("y")||0);
this.options.offset={
left:t.left+a,
top:t.top+l,
left_x:a,
top_y:l
},!o.content&&(o.content=this.$container.data("tips")||""),this.$dom=$(s(e,o)).appendTo("body"),
this.options.disabled&&this.$container.addClass(p);
var d=this,f=this.options.type===c||"click"===this.options.type?this.options.type:c;
if(f==c){
var r=null;
this.$container.hover(function(){
d.options.onshow&&"function"==typeof d.options.onshow?d.options.onshow.apply(d):!d.options.disabled&&d.show();
},function(){
r=window.setTimeout(function(){
d.hide();
},d.options.delay);
}),this.$dom.hover(function(){
r&&window.clearTimeout(r);
},function(){
d.hide();
});
}else this.$container.click(function(){
return d.options.disabled||d.options.onbeforeclick&&"function"==typeof d.options.onbeforeclick&&d.options.onbeforeclick.apply(d)===!1?void 0:(d.$dom.data(h)?d.options.onclose&&"function"==typeof d.options.onclose?d.options.onclose.apply(d):d.hide():d.options.onshow&&"function"==typeof d.options.onshow?d.options.onshow.apply(d):d.show(),
!1);
});
$(document).on("click",function(o){
d.$dom.find(o.target).length||(d.options.onclose&&"function"==typeof d.options.onclose?d.options.onclose.apply(d,[o]):d.hide());
}),d.$dom.find(".js_popover_close").on("click",function(o){
return d.options.onclose&&"function"==typeof d.options.onclose?d.options.onclose.apply(d,[o]):d.hide(),
!1;
}),this.$dom.hide(),function(){
$.each(d.$dom.find(".js_btn"),function(o,t){
d.options.buttons[o].click&&$(t).on("click",function(){
d.options.buttons[o].click.apply(d);
});
});
}();
}
};
a.prototype={
constructor:a,
show:function(){
if(this.options.reposition){
var o=this.$container.offset(),t=o.left+this.options.offset.left_x,n=o.top+this.options.offset.top_y;
this.$dom.css({
left:t,
top:n
}).show();
}else this.$dom.show();
this.$dom.data(h,!0);
},
hide:function(){
this.$dom.hide(),this.$dom.data(h,!1);
},
enable:function(){
return this.options.disabled=!1,this.$container.removeClass(p),this;
},
disable:function(){
return this.options.disabled=!0,this.$container.addClass(p),this;
}
},n.exports=a;
});define("resp_types/file_cnt.rt.js",[],function(){
"use strict";
return{
file_cnt_R:{
total:"number",
img_cnt:"number",
voice_cnt:"number",
video_cnt:"number",
app_msg_cnt:"number",
commondity_msg_cnt:"number",
video_msg_cnt:"number",
short_video_cnt:"number",
app_msg_sent_cnt:"number"
}
};
});define("resp_types/base_resp.rt.js",[],function(){
"use strict";
return{
base_resp_R:{
ret_R:"number",
err_msg:"string"
}
};
});define("tpl/richEditor/emotion.html.js",[],function(){
return'<span class="hook">\n	<span class="hook_dec hook_top"></span>\n	<span class="hook_dec hook_btm"></span>\n</span>\n<ul class="emotions" onselectstart="return false;"> \n    {each edata as e index}\n        <li class="emotions_item">\n            <i\n                class="js_emotion_i" \n                data-gifurl=\'{e.gifurl}\' \n                data-title=\'{e.title}\' \n                style=\'{e.bp}\'>\n            </i>\n        </li>\n    {/each}\n</ul>\n<span class="emotions_preview js_emotionPreviewArea"></span>\n';
});define("common/wx/richEditor/editorRange.js", [], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = function() {
return document.selection ? document.selection : (window.getSelection || document.getSelection)();
}, s = function(e, t, n) {
if (!n && e === t) return !1;
if (e.compareDocumentPosition) {
var r = e.compareDocumentPosition(t);
if (r == 20 || r == 0) return !0;
} else if (e.contains(t)) return !0;
return !1;
}, o = function(e, t) {
var n = t.commonAncestorContainer || t.parentElement && t.parentElement() || null;
return n ? s(e, n, !0) : !1;
}, u = function(e) {
var t = i();
if (!t) return null;
var n = t.getRangeAt ? t.rangeCount ? t.getRangeAt(0) : null : t.createRange();
return n ? e ? o(e, n) ? n : null : n : null;
}, a = function(e) {
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
});define("tpl/homepage/plugins/plugin3_edit.html.js",[],function(){
return'<div class="portable_editor to_left">\n    <div class="editor_inner">\n        <!--BEGIN editor_bd-->\n        <div class="editor_bd js_import_appmsglist">\n        </div>\n        <!--END editor_bd-->\n        <span class="editor_arrow_wrp">\n            <i class="editor_arrow editor_arrow_out"></i>\n            <i class="editor_arrow editor_arrow_in"></i>\n        </span>\n    </div>\n</div>';
});