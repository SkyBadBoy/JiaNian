define("cardticket/create_card_select.js",["biz_web/ui/checkbox.js","common/wx/Tips.js","common/wx/popup.js","common/wx/dialog.js","cardticket/select_sub_merchant_table.js","cardticket/common_template_helper.js","tpl/cardticket/choose_card_type.html.js","common/wx/Step.js"],function(e){
"use strict";
function t(e){
return 1==window.view_mode&&(1==c||2==c)||2==window.view_mode&&e&&h.can_category_use_sns_card(e.PrimaryCategoryId,e.SecondaryCategoryId);
}
function i(e,t){
var i=$(e.step2container).html(f({
flag:e.ispay,
is_sns_card:e.is_sns_card,
show_all_card:e.show_all_card,
view_mode:window.view_mode
})),n=$(".frm_tab").height();
$(".js_is_friend_type_1 .js_card_type",i).checkbox({
onChanged:function(e){
t.card_type1=$(e).val();
var s=$(e).attr("data-not_has_condition");
t.has_condition=1==s?!1:!0;
var o=$(".frm_tab .selected",i).index(),_=0-o*n;
$(".tab_items",i).css("top",_);
}
}),$(".js_is_friend_type_2 .js_card_type",i).checkbox({
onChanged:function(e){
t.card_type2=$(e).val();
var i=$(e).attr("data-not_has_condition");
t.has_condition=1==i?!1:!0;
}
}),i.find(".js_is_friend").checkbox({
onChanged:function(e){
$(".js_is_friend_type",i).hide(),$(".js_is_friend_type_"+$(e).val(),i).show(),1==$(e).val()?(t.is_friend=!0,
setTimeout(function(){
n=$(".frm_tab",i).height();
var e=$(".js_is_friend_type_1 .frm_radio_label",i).length;
$(".choose_card_type,.frm_tab_item",i).css("height",n),$(".tab_items",i).css("height",n*e);
})):t.is_friend=!1,$(".js_is_friend_type_"+$(e).val(),i).find(".js_card_type:checked").click(),
t.$popup.popup("resetPosition");
}
}),"undefined"!=typeof c&&_(e,t,i);
}
function n(e,i){
var n=$(m()).popup({
title:"创建优惠券",
autoShow:!1,
width:956,
buttons:[{
text:"取消",
type:"default",
click:function(){
this.hide();
}
},{
text:"下一步",
type:"primary",
click:function(){
var e=i.merchantSelector.selectedValue();
e&&(i.merchant_data=e,o(i));
}
},{
text:"上一步",
type:"default",
click:function(){
s(i);
}
},{
text:"确定",
type:"primary",
click:function(){
return i.is_friend&&"undefined"==typeof c?!0:(i.is_friend&&!t(i.merchant_data)&&(p.show({
msg:'本公众号子商户类目不支持制作朋友的券|<a href="https://mp.weixin.qq.com/cgi-bin/readtemplate?t=cardticket/faq_tmpl&type=info&lang=zh_CN#1dot1" target="_blank">查看朋友的券类目开放范围</a>',
type:"info",
buttons:[{
text:"取消",
click:function(e){
this.remove(e);
},
type:"normal"
},{
text:"配置子商户",
click:function(e){
window.open(wx.url("/merchant/cardhelpmakesend?action=list")),this.remove(e);
},
type:"primary"
}]
}),this.hide()),i.is_friend&&i.card_type1||!i.is_friend&&i.card_type2?(window.open(wx.url("/merchant/electroniccardmgr?action=%s&type=%s&flag=%s&is_sns_card=%s&has_condition=%s%s".sprintf(i.is_friend?"addsnspage":"addpage",i.is_friend?i.card_type1:i.card_type2,1==e.ispay?1:"0",i.is_friend?1:"0",i.has_condition?1:"0",i.merchant_data?"&sub_merchant_id="+i.merchant_data.Id:""))),
void this.hide()):void d.err("请选择卡券类型"));
}
}],
onHide:function(){
e.onHide&&e.onHide.call(i),this.remove();
},
className:"align_edge"
});
i.$popup=n,i.step=new l({
container:n.find(".js_step_container"),
names:["1 选择代制的子商户","2 选择券类型"]
}),i.$popup.popup("show");
var _=n.popup("get").find(".js_step_content");
i.opt.step2container=_[1],i.opt.container=$(_[0]).find(".js_sub_merchant_list");
}
function s(e){
var t=e.$popup,i=t.popup("get").find(".js_step_content"),n=t.popup("get").find(".js_btn_p");
$(n[0]).show(),$(n[1]).show(),$(n[2]).hide(),$(n[3]).hide(),e.step.go(1),$(i[0]).show(),
$(i[1]).hide(),t.popup("resetPosition");
}
function o(e){
var t=e.$popup,n=t.popup("get").find(".js_step_content"),s=t.popup("get").find(".js_btn_p");
$(s[0]).hide(),$(s[1]).hide(),$(s[2]).show(),$(s[3]).show(),$(n[0]).hide(),$(n[1]).show(),
e.step.go(2),e.opt.merchant_data=e.merchant_data,i(e.opt,e),t.popup("resetPosition");
}
function _(e,i,n){
$(".js_is_friend_tips",n).hide(),!t(i.merchant_data)&&e.show_all_card?($(n.find(".js_is_friend")[1]).click(),
$(n.find(".js_is_friend")[0]).checkbox().disabled(!0),$(".js_is_friend_view_mode"+(window.view_mode||1)+"_tips",n).show()):($(n.find(".js_is_friend")[0]).checkbox().disabled(!1),
$(n.find(".js_is_friend")[0]).click(),$(".js_is_friend_support_tips",n).show());
}
function a(e){
var t=this;
this.opt=e,n(e,t);
var i=t.$popup.popup("get");
if(1==window.view_mode){
o(t);
var i=t.$popup.popup("get");
i.find(".js_step_container").hide();
var a=i.find(".js_btn_p");
$(a[2]).hide();
}else s(t);
var d={
resetPosition:function(){
t.$popup.popup("resetPosition");
},
getDataComplete:function(e){
var i=t.$popup.popup("get");
e&&e.length?$(i.find(".js_btn_p")[0]).removeClass("btn_disabled"):$(i.find(".js_btn_p")[0]).addClass("btn_disabled");
},
container:e.container,
is_sns_card:!1,
max_card:e.max_card
};
t.merchantSelector=new r(d),"undefined"==typeof c&&h.check_assist_brand_name_type(function(n){
c=n,_(e,t,i);
});
}
var c,d=(e("biz_web/ui/checkbox.js"),e("common/wx/Tips.js")),p=(e("common/wx/popup.js"),
e("common/wx/dialog.js")),r=e("cardticket/select_sub_merchant_table.js"),h=e("cardticket/common_template_helper.js"),f=template.compile(e("tpl/cardticket/choose_card_type.html.js")),m=template.compile('<div>			<div class="wrp_processor js_step_container"></div>			<div class="first_step js_step_content js_step1">				<div class="js_sub_merchant_list select_subshop"></div>			</div>			<div class="second_step js_step_content js_step2"></div>			</div>'),l=e("common/wx/Step.js");
return window.view_mode||(window.view_mode=1),a;
});define("cardticket/common_template_helper.js",["common/wx/upload.js","common/wx/Cgi.js","biz_common/moment.js","cardticket/add/msg_operate_type_html.js"],function(e){
"use strict";
function t(e){
for(var t,r,n,a,_=[],i=0;i<e.length;i++){
var s=e[i];
"object"==typeof s&&(s=d[s.type]),a=h[s],s?i==e.length-1?n&&s-n!=1?(_.push(t+(r?"至"+r:"")),
_.push(a)):_.push(t?t+"至"+a:a):n&&s-n!=1?(_.push(t+(r?"至"+r:"")),t=a,r="",n=s):(t?r=a:t=a,
n=s):(s=8,i==e.length-1&&t&&_.push(t+"至"+r),_.push(a),t=r=n="");
}
return _.join("、");
}
function r(e){
return e.replace(/\\n|\n/g,"<br/>");
}
function n(e){
var t="YYYY-MM-DD HH:mm:ss",r=l(e,t);
return r?r.format("YYYY-MM-DD"):"";
}
function a(e){
return 1==e||3==e||2==e;
}
function _(e,t){
return 1==e&&119>=t?!0:(2!=e||215!=t&&210!=t&&208!=t&&207!=t&&206!=t&&204!=t&&203!=t&&211!=t&&201!=t&&202!=t)&&(3!=e||308!=t&&309!=t&&306!=t&&305!=t&&304!=t&&303!=t&&314!=t&&316!=t&&317!=t)&&(6!=e||601!=t&&602!=t&&603!=t)?4==e&&402==t?!0:7==e&&701==t?!0:(5!=e||501!=t&&502!=t&&503!=t)&&(8!=e||812!=t&&811!=t&&808!=t&&817!=t&&818!=t&&827!=t&&804!=t&&803!=t&&802!=t&&801!=t&&824!=t&&822!=t&&823!=t&&821!=t&&828!=t&&814!=t&&825!=t&&826!=t&&809!=t&&807!=t&&816!=t&&819!=t&&813!=t)?!1:!0:!0;
}
function i(e){
for(var t=0;t<M.length;t++){
var r=M[t];
"function"!=typeof r&&(r=$.noop),r(e);
}
M=[];
}
function s(e){
return M.push(e),"undefined"!=typeof I?(i(I),!0):U?!1:(U=!0,u.get({
url:"/merchant/cardhelpmakesend",
data:{
action:"list",
begin:0,
count:9999999,
status_list:1
},
complete:function(){
U=!1;
}
},function(e){
if(0==e.base_resp.ret||-1==e.base_resp.ret){
for(var t=$.parseJSON(e.bind_list),r=t.List,n=!1,a=!1,s=0;s<r.length;s++){
var o=r[s];
if(_(o.PrimaryCategoryId,o.SecondaryCategoryId)){
a=!0;
break;
}
}
e.attr&&e.attr.merchant_info&&(n=_(e.attr.merchant_info.primary_category_id,e.attr.merchant_info.secondary_category_id)),
n&&a&&(I=1),n&&!a&&(I=2),!n&&a&&(I=3),n||a||(I=4),4==I&&e.is_can_use_sns_card&&!e.is_can_use_help_make_and_send&&(I=5),
i(I);
}
}),!1);
}
function o(e,t){
var r=!1;
e.create_time&&e.create_time<1463648400&&(r=!0),"undefined"==typeof t&&(t=!0);
var n="",a=!1;
return 4==e.type||2==e.type?(t&&e.reduce_cost&&(n="价值%s元代金券一张".sprintf(e.reduce_cost)),
r?n:(e.use_condition_least_cost?(n&&(n+="，"),n+="消费满%s元可用".sprintf(e.use_condition_least_cost)):4!=e.type||"1"!=e.is_sns_card&&e.is_sns_card!==!0||(n&&(n+="；"),
n+="无最低消费限制"),e.accept_category&&(n&&(n+="；"),n+="适用于%s".sprintf(e.accept_category),
a=!0),e.reject_category&&(n&&(n+="；"),n+="不适用于%s".sprintf(e.reject_category),a=!0),
"1"!=e.is_sns_card&&e.is_sns_card!==!0||4!=e.type||a||(n&&(n+="；"),n+="全场通用，不限品类"),
!(4!=e.type||"1"!=e.is_sns_card&&e.is_sns_card!==!0||e.has_condition||"0"!=e.uncheckcount&&!e.id),
n)):3==e.type?(t&&(e.title||e.gift_title)&&(n="%s%s%s%s".sprintf("1"==e.is_sns_card||e.is_sns_card===!0?"兑换":"",e.gift_title||e.title,e.gift_num||"",e.gift_unit||"")),
r?n:(2==e.use_condition_least_cost_type&&e.object_use_for&&(n&&(n+="；"),n+="购买%s可用".sprintf(e.object_use_for),
a=!0),1==e.use_condition_least_cost_type&&e.use_condition_least_cost&&(n&&(n+="，"),
n+="消费满%s元可用".sprintf(e.use_condition_least_cost),a=!0),"1"!=e.is_sns_card&&e.is_sns_card!==!0||a||(n&&(n+="；"),
n+="无最低消费限制"),n)):void 0;
}
function c(e){
if(!e.begin_time||!e.end_time)return"";
var t="YYYY.MM.DD";
return l.unix(e.begin_time).format(t)+"-"+l.unix(e.end_time).format(t);
}
var p=e("common/wx/upload.js"),u=e("common/wx/Cgi.js"),l=e("biz_common/moment.js"),m={
10:"会员卡",
21:"门票",
22:"电影票",
4:"代金券",
1:"团购券",
2:"折扣券",
3:"兑换券",
0:"优惠券"
},f={
1:"审核中",
2:"未通过",
3:"待投放",
4:"已删除",
5:"待投放",
6:"已投放",
8:"已过期",
7:"违规下架"
},d={
MONDAY:"1",
TUESDAY:"2",
WEDNESDAY:"3",
THURSDAY:"4",
FRIDAY:"5",
SATURDAY:"6",
SUNDAY:"7"
};
template.helper("$has_day",function(e,t){
if(!e)return"";
for(var r=0;r<e.length;r++){
var n=d[e[r].type];
if(n||(n=8),n==t)return"checked";
}
return"";
});
var h={
1:"周一",
2:"周二",
3:"周三",
4:"周四",
5:"周五",
6:"周六",
7:"周日",
8:"节假日"
};
template.helper("convert_time_limit",function(e){
return t(e);
});
var v={
1:"免费WIFI",
2:"可带宠物",
4:"免费停车",
8:"可外卖"
};
template.helper("convert_business_service",function(e){
if(!e)return"无";
var t=[];
for(var r in v){
var n=parseInt(r);
(e&n)>0&&t.push(v[r]);
}
return t.join("&nbsp;&nbsp;");
});
var l=e("biz_common/moment.js");
template.helper("convert_state",function(e){
return f[e]||e;
}),template.helper("convert_type",function(e){
return m[e]||e;
}),template.helper("card_type_map",function(e){
return e;
}),template.helper("unixFormat",function(e,t){
return t&&(t=t.replace(","," ")),l.unix(e).format(t);
}),template.helper("validtime",function(e,t){
if(1==e.time_type){
var r=l.unix(e.begin_time).format(t)+"至"+l.unix(e.end_time).format(t);
return e.end_time<l().unix()&&(r+="(已过期)"),r;
}
return 2==e.time_type?(e.from_day=0==e.from_day?"当":e.from_day,"领取后{from_day}天生效{fixed_term}天有效".format(e)):"";
}),template.helper("addtoken",function(e){
return wx.url(e);
}),template.helper("nl2br",function(e){
return r(e.html(!0));
});
var g={
1:"50万以下",
2:"50-100万",
3:"100-500万",
4:"500-1000万",
5:"1000万以上"
};
template.helper("convert_business_volume_type",function(e){
return g[e]||e;
});
var y={
0:"已提交",
2:"已提交",
3:"生效",
4:"不通过"
};
template.helper("convert_store_state",function(e){
return y[e]||e;
}),template.helper("$preview",function(e){
if(!e)return"无";
var t;
return 0===e.indexOf("temp_")?(e=e.replace(/^temp_/,""),t=p.tmpFileUrl(e)):t=p.multimediaFileUrl(e),
"<a href='%s' target='_blank'><img src='%s' /></a>".sprintf(t,t);
}),template.helper("$upload_preview",function(e){
if(!e)return"";
var t;
return 0===e.indexOf("temp_")?(e=e.replace(/^temp_/,""),t=p.tmpFileUrl(e)):t=p.multimediaFileUrl(e),
"<img src='%s' style='width:260px;' />".sprintf(t);
}),template.helper("$preview_stuffs",function(e){
for(var t=[],r=e.stuffs,n=0;n<r.length;n++){
var a=r[n]+"_preview_tpl";
$("#"+a).length&&t.push(template.render(a,e));
}
return t.join("");
});
var x={
2:"女",
1:"男"
};
template.helper("convert_gender",function(e){
return x[e]||"未知";
}),template.helper("percentage",function(e,t,r,n){
var r=e/t*100;
return n&&r>n&&(r=n),r.toFixed(2);
});
var b={
"":"全部",
0:"API渠道",
1:"嵌入图文消息",
2:"直接群发卡券",
3:"下载二维码"
};
template.helper("convert_channel",function(e){
return b[e]||e;
}),template.helper("convert_provide_time",n),template.helper("http2https",function(e){
return e?(e+"").http2https():"";
}),template.helper("https2http",function(e){
return e?(e+"").https2http():"";
}),template.helper("codepad",function(e){
var t=new RegExp("([^s]{4})(?=([^s])+$)","ig");
return e.replace(t,"$1-");
}),template.helper("yuan",function(e){
if(!e)return"--";
var e=e/100;
return e.toFixed(2);
}),template.helper("is_paycard",function(){
return window.wx_is_paycard;
});
var w={
0:"等待接收",
1:"已接收",
3:"过期退回",
2:"已拒绝"
},j={
0:"等待接收",
2:"已拒绝",
1:"已接收",
3:"过期退回"
};
template.helper("convert_intercard_status",function(e){
return w[e]||e;
}),template.helper("convert_intercard_rec_status",function(e){
return j[e]||e;
});
var Y={
0:"无",
1:"图文消息",
2:"卡券货架",
3:"小店货架",
4:"网页链接",
5:"卡券"
};
template.helper("convert_msg_operate_type",function(e){
return Y[e]||"无";
});
var k=e("cardticket/add/msg_operate_type_html.js"),u=e("common/wx/Cgi.js");
template.helper("msg_operate_content",function(e){
return 5===e._type?"":e._notexist?"无":template.compile(k[e._type])({
msg_operation:e
})||"";
});
var D={
CHECKING:"审核中",
APPROVED:"已通过",
REJECTED:"未通过",
EXPIRED:"已过期"
};
template.helper("convert_sub_merchant_status",function(e){
return D[e]||e;
}),template.helper("$is_can_use_help_make_and_send",function(){
return 1==window.wx_is_can_use_help_make_and_send;
}),template.helper("wx_url",function(e){
return wx.url(e);
});
var A={
".*?_4":"激活"
};
template.helper("convert_use_source",function(e,t){
var r=e+"_"+t;
return 4==t?"激活":1==t?"微信买单":5==t?"自助核销":3==e?"手机核销":1==e?"网页核销":2==e?"API核销":A[r]||"";
}),template.helper("convert_fee_coin",function(e,t){
return 0==t?"--":a(e)?'<span class="number_add">+%s</span>'.sprintf(t/100):'<span class="number_degress">-%s</span>'.sprintf(t/100);
});
var E={
1:"平台赠送",
2:"充值",
3:"退还券点",
4:"支出",
5:"平台扣减"
};
template.helper("convert_fee_order_type",function(e){
return E[e]||e;
});
var F={
2:{
1:"等待确认",
2:"充值成功",
3:"充值成功",
8:"充值成功"
},
3:"已退券点",
4:{
1:"等待确认",
3:"库存发放中",
4:"库存已发放",
7:"库存添加失败, 已返还券点",
6:"库存已发放",
5:"库存已发放"
}
};
template.helper("convert_fee_order_status",function(e,t){
var r=F[t];
return r?"string"==typeof r?r:r[e]||e:e;
}),template.helper("addhttp",function(e){
return/^http:\/\//.test(e)?e:"http://"+e;
});
var I,C=[],U=!1,M=[];
template.helper("$fix_abstract4friendcard",function(e,t){
return o(e,t);
}),template.helper("$gen_use_time",function(e){
return c(e);
});
var R={
0:"生效",
1:"已使用",
2:"过期",
3:"转赠中",
4:"已转赠",
5:"转赠过期",
6:"已删除"
};
template.helper("convert_user_card_state",function(e){
return R[e]||e;
});
var S={
0:"审核通过",
1:"待商户审核",
2:"审核不通过",
3:"待激活",
4:"请添加库存"
};
return template.helper("convert_swipe_card_status",function(e){
return S[e]||e;
}),{
type_map:m,
status_map:f,
store_status:y,
gender_map:x,
source_map:b,
convert_provide_time:n,
nl2br:r,
sub_merchant_status_map:D,
fix_money:function(e){
var t=/(\.\d{2}).+$/,r=e;
return r=parseFloat((r+"").replace(t,"$1"));
},
parse_assistsend_quota:function(e,t){
for(var r=0,n=0,a=0;a<e.length;a++){
var _=e[a];
_.quota_name==(t||"merchant_auth_create_card")&&(r=_.value),_.quota_name==(t?t+"_max_sku":"merchant_auth_create_card_max_sku")&&(n=_.value);
}
return{
max_card:r,
max_sku:n
};
},
check_friend_card_word:function(e,t){
if(!e)return!0;
for(var r=0;r<C.length;r++)if(e.indexOf(C[r])>=0)return t?t():!0;
return!0;
},
check_assist_brand_name_type:s,
can_category_use_sns_card:_,
fix_abstract4friendcard:o,
strlen:function(e){
for(var t=0,r=0;r<e.length;r++){
var n=e.charCodeAt(r);
128>n?t++:t+=2;
}
return t;
},
gen_use_time:c,
gen_time_limit:t
};
});define("cardticket/store_cgi.js",["common/wx/Cgi.js","common/wx/Tips.js","common/wx/tooltips.js","common/wx/tooltipsManager.js","common/wx/dialog.js"],function(t){
"use strict";
var e=t("common/wx/Cgi.js"),s=t("common/wx/Tips.js"),o=t("common/wx/tooltips.js"),n=t("common/wx/tooltipsManager.js"),c=(t("common/wx/dialog.js"),
{
deleteStore:function(t){
e.post({
url:"/merchant/entityshop?action=delete",
data:{
id:t.store_id
},
btn:t.btn
},function(o){
0==o.base_resp.ret?(s.suc("删除门店成功"),t.success&&t.success()):e.show(o);
});
},
deleteWithConfirm:function(t){
if(3==t.state||4==t.state){
var e=new o({
container:t.container,
content:"删除将影响在用此门店的卡券功能、微信连Wi-Fi、摇一摇周边、LBS广告等相关业务。<br />你确定要删除吗？",
type:"click",
buttons:[{
text:"确定",
type:"btn_primary",
click:function(){
if(t.success){
var e=t.success;
t.success=function(){
e&&e(),n.removeAll();
};
}
c.deleteStore(t);
}
},{
text:"取消",
type:"btn_default",
click:function(){
n.removeAll();
}
}]
});
e.show(),n.removeAll(),n.add(e);
}
},
listStore:function(t){
var s=$.extend({},{
action:"list",
begin:0,
count:9999999,
keyword:t.keyword,
task_id:t.task_id,
audit_state:t.audit_state||3
},t.getDataExtra);
e.get({
url:"/merchant/entityshop",
data:s
},function(s){
if(0==s.base_resp.ret){
var o=$.parseJSON(s.data),n=o.store_location;
t.success&&t.success({
shop_list:n,
total_num:s.total_count
});
}else e.show(s);
});
},
canSendCard:function(t){
t.success&&t.success(!0);
}
});
return c;
});define("biz_web/ui/dropdown.js", [ "biz_web/widget/dropdown.css", "tpl/biz_web/ui/dropdown.html.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict", e("biz_web/widget/dropdown.css");
var i = e("tpl/biz_web/ui/dropdown.html.js"), s = {
label: "请选择",
data: [],
callback: $.noop,
render: $.noop,
delay: 500,
disabled: !1,
search: !1
}, o = "dropdown_menu";
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
if (e.keyCode == 13) t.value ? (n.html(n.data("name")).removeClass("error"), t.dropdown.hide()) : n.find("div").remove(); else {
var r = n.html().trim(), i = [];
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
var n = $(this), r = $(this).html().trim();
r == e.label && n.html("").removeClass("error"), r == "" && n.removeClass("error"), t.dropdown.show(), t.container.addClass("open");
}), $(document).on("click", a), t.dropdown.on("click", ".jsDropdownItem", function(n) {
var r = $(this).data("value"), i = $(this).data("name"), s = $(this).data("index");
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
var r = this.opt.data[e].name, i = this.opt.data[e].value;
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
});define("tpl/step.html.js", [], function(e, t, n) {
return '<ul class="processor_bar grid_line">\n    {each stepArr as item index}\n    <li class="{if (index+1==length)}no_extra{/if} step grid_item size1of{length} {item.cls}">\n        <h4>{item.name}</h4>\n    </li>\n    {/each}\n</ul>\n';
});define("tpl/tooltips.html.js",[],function(){
return'<div class="popover {parentClass}" style="display:none;position:{container_mode};{if offset.left}left:{offset.left}px;top:{offset.top}px;{/if}">\n    <div class="popover_inner">\n        <div class="popover_content">{=content}</div>\n        {if container_close}\n        <!--#0001#-->\n        <a href="javascript:;" class="popover_close icon16_common close_flat js_popover_close">关闭</a>\n        <!--%0001%-->\n        {/if}\n        {if buttons.length > 0}\n        <div class="popover_bar">\n			{each buttons as o index}\n			<a onclick="return false;" href="javascript:;" class="js_btn btn {o.type}">{o.text}</a>\n			{/each}\n        </div>\n        {/if}\n    </div>\n    <i class="popover_arrow popover_arrow_out"></i>\n    <i class="popover_arrow popover_arrow_in"></i>\n</div>\n';
});define("biz_web/ui/input/lentips.js",[],function(){
"use strict";
var n="&nbsp;<em>/</em>&nbsp;",t=function(t){
var e=t.input,i=t.tip,l=t.className,a=t.trim||!0,s=t.seg||n,m=t.maxlimit,u=function(){
var n=e.val();
a&&(n=$.trim(n)),i.html(n.length+s+m),n.length>m?i.addClass(l):i.removeClass(l),
t.callback&&t.callback(n.length>m,{
len:n.length,
maxlimit:m,
value:n
});
};
u(),e.keyup(function(){
u();
});
};
return t;
});define("tpl/homepage/plugins/plugin2_edit/cate_list_edit.html.js",[],function(){
return'<div>\n    <!--BEGIN 填写tab分类-->\n    <div class="section_form">\n        <div class="frm_control_group">\n            <a class="opr js_del_cate" href="javascript:void(0);">删除</a>\n            <label for="" class="frm_label">分类名称</label>\n            <div class="frm_controls">\n                <span class="frm_input_box with_counter counter_in append">\n                    <input type="text" class="frm_input js_cate_input">\n                    <em class="frm_input_append frm_counter js_cate_input_len_tips"></em>\n                </span>\n            </div>\n        </div>\n    </div>\n    <!--END 填写tab分类-->\n    <div class="js_import_appmsglist"></div>\n</div>';
});define("homepage/appmsgdialog.js",["common/wx/Cgi.js","common/wx/Tips.js","biz_web/ui/checkbox.js","common/wx/time.js","common/wx/pagebar.js","common/wx/popup.js","tpl/homepage/appmsgdialog.html.js","tpl/homepage/appmsglist.html.js"],function(t,e,a){
"use strict";
function i(t){
return this._init(t),this;
}
var s=t("common/wx/Cgi.js"),n=t("common/wx/Tips.js"),c=(t("biz_web/ui/checkbox.js"),
t("common/wx/time.js")),l=(wx.T,t("common/wx/pagebar.js")),o=(t("common/wx/popup.js"),
t("tpl/homepage/appmsgdialog.html.js")),g=t("tpl/homepage/appmsglist.html.js");
i.prototype._init=function(t){
var e=this;
this.perPage=t.perPage||10,this._cfg=t,this._cfg.selectData=[],"undefined"==typeof this._cfg.multi&&(this._cfg.multi=!0),
"undefined"==typeof this._cfg.maxNum&&1==this._cfg.multi&&(this._cfg.maxNum=1e4),
this.dlgtpl=this._cfg.dlgtpl||o,this.msglisttpl=this._cfg.msglisttpl||g;
var a=this._mDlg=e._buildDialog(),i=a.find(".js_appmsg_tab"),s=a.find(".js_mass_tab"),n=a.find(".js_appmsg_container"),c=a.find(".js_mass_container");
i.click(function(){
i.addClass("selected"),s.removeClass("selected"),n.show(),c.hide();
}),s.click(function(){
i.removeClass("selected"),s.addClass("selected"),n.hide(),c.show();
}),this._bindEvent(this._mDlg);
},i.prototype._buildList=function(t,e,a,i,s){
var c=this,l=this._formatData(e,s);
a=a||".js_listContainer",i=i||".js_loading";
var o=t.find(a),g=t.find(i);
o.html(template.compile(this.msglisttpl)({
app_msg_list:l,
multi:c._cfg.multi
})).show(),g.hide();
o.find(".js_appmsgid").checkbox({
multi:c._cfg.multi,
onChanged:function(t){
if(c._cfg.multi===!0){
if(t[0].checked)c._cfg.selectData.length>=c._cfg.maxNum?($(t[0]).checkbox().checked(!1),
n.err("最多只能选择%s篇文章".sprintf(c._cfg.maxNum))):$.each(l,function(e,a){
a.aid==t[0].value&&c._cfg.selectData.push(l[e]);
});else{
var e=[];
$.each(c._cfg.selectData,function(a){
c._cfg.selectData[a].aid!=t[0].value&&e.push(c._cfg.selectData[a]);
}),c._cfg.selectData=e;
}
c._countTotal(c._cfg.maxNum-c._cfg.selectData.length);
}else c._cfg.selectData=[],$.each(l,function(e,a){
a.aid==t[0].value&&c._cfg.selectData.push(l[e]);
});
}
});
},i.prototype._countTotal=function(t){
0==this._cfg.multi&&this._mDlg.find(".global_extra").hide(),t>=0?this._mDlg.find(".js_remaincnt").text(t):n.err("最多只能选择30篇文章");
},i.prototype._getData=function(t,e,a,i){
if("undefined"!=typeof this._mDlg){
a=a||".js_listContainer",i=i||".js_loading";
var c=this._mDlg.find(a),l=this._mDlg.find(i);
l.show(),c.hide();
}
var o=this,g=$.extend({
action:"list_ex",
begin:0,
count:o.perPage,
type:e&&e.type?e.type:10,
link:o._cfg.link||0,
query:""
},e);
s.post({
url:"/cgi-bin/appmsg",
data:g,
success:function(e){
e&&0==e.base_resp.ret?(o.retData=e.app_msg_list,o.totalnum=e.app_msg_cnt,t(e)):n.err("系统错误");
}
});
},i.prototype._formatData=function(t,e){
var a=[];
return t&&t.app_msg_list&&(a=t.app_msg_list),$.each(this._cfg.selectData,function(t,e){
$.each(a,function(t,i){
e.aid==i.aid&&(a[t].checkbox=!0);
});
}),$.each(a,function(t,i){
a[t].update_time=c.formatDate(new Date(1e3*i.update_time),"YYYY年MM月DD日"),a[t].type=e||10;
}),a;
},i.prototype._buildDialog=function(){
var t=this,e=$(this.dlgtpl).popup({
title:t._cfg.title||"从素材管理中选择",
buttons:[{
text:"确定",
click:function(){
t._cfg.selectData.length>0&&(1==t._cfg.multi&&t._cfg.selectData.length<=t._cfg.maxNum||0==t._cfg.multi)?(t._cb(t._cfg.selectData),
e.popup("remove")):0==t._cfg.selectData.length?n.err("请选择至少一篇图文"):1==t._cfg.multi&&t._cfg.selectData.length>t._cfg.maxNum&&n.err("最多只能选择30篇文章");
},
type:"primary"
},{
text:"取消",
click:function(){
e.popup("remove");
},
type:"default"
}],
mask:!0,
className:"align_edge"
});
return this._getData(function(a){
t._buildList(e,a),t._initPageBar({
totalnum:t.totalnum,
perpage:t.perPage,
currentpage:1
});
}),this._getData(function(a){
t._buildList(e,a,".js_masslistContainer",".js_mass_loading",9),t._initMassPageBar({
totalnum:t.totalnum,
perpage:t.perPage,
currentpage:1
});
},{
type:9
}),e;
},i.prototype._bindEvent=function(t){
var e=this;
t.find(".js_a_search").on("click",function(){
var a=$.trim(t.find(".js_search").val());
e._getData(function(a){
e._buildList(t,a),e._initPageBar({
totalnum:e.totalnum,
perpage:e.perPage,
currentpage:1
});
},{
query:a
});
}),t.find(".js_search").keyup(function(e){
var a="which"in e?e.which:e.keyCode;
console.log(t.find(".js_search").val()),(13==a||""==t.find(".js_search").val())&&t.find(".js_a_search").trigger("click");
}),e._countTotal(e._cfg.maxNum);
},i.prototype._initPageBar=function(t){
{
var e=this,a=this._mDlg.find(".js_search").val(),i=t&&t.currentpage,s=t&&t.perpage,n=t&&t.totalnum;
new l({
container:e._mDlg.find(".js_pager"),
perPage:s,
initShowPage:i,
totalItemsNum:n,
first:!1,
last:!1,
isSimple:!0,
callback:function(t){
var n=t.currentPage;
n!=i&&(i=n,e._getData(function(t){
e._buildList(e._mDlg,t);
},{
begin:(i-1)*s,
query:a
}));
}
});
}
},i.prototype._initMassPageBar=function(t){
{
var e=this,a=t&&t.currentpage,i=t&&t.perpage,s=t&&t.totalnum;
new l({
container:e._mDlg.find(".js_masspager"),
perPage:i,
initShowPage:a,
totalItemsNum:s,
first:!1,
last:!1,
isSimple:!0,
callback:function(t){
var s=t.currentPage;
s!=a&&(a=s,e._getData(function(t){
e._buildList(e._mDlg,t,".js_masslistContainer",".js_mass_loading",9);
},{
begin:(a-1)*i,
type:9
},".js_masslistContainer",".js_mass_loading"));
}
});
}
},i.prototype._cb=function(t){
this._cfg&&this._cfg.callback&&"function"==typeof this._cfg.callback&&this._cfg.callback(t);
},a.exports=i;
});define("tpl/homepage/importAppmsgList/item.html.js",[],function(){
return'{each appmsg_list as item idx}\n    <div class="article js_article" data-idx="{idx}" data-aid="{item.aid}">\n        <a class="opr js_del" href="javascript:;" data-idx="{idx}">删除</a>\n        <a class="opr icon14_common sort_gray js_sort_item" style="display:none;" href="javascript:;">排序</a>\n        <p class="title"><a href="{item.link}" target="_blank" data-msgid="{item.appmsgid}" data-idx="{item.itemidx-1}">{item.title}</a></p>\n    </div>\n{/each}\n';
});define("tpl/homepage/importAppmsgList/layout.html.js",[],function(){
return'<div>\n    <div class="editor_hd">\n    </div>\n    <div class="editor_bd">\n        <div class="editor_toolbar">\n            <div class="ext">\n                <a class="btn btn_default js_import" href="javascript:;">添加</a>\n                <a class="btn btn_default js_sort" href="javascript:;">排序</a>\n                <a class="btn btn_primary js_sort_sure" href="javascript:;">保存</a>\n                <a class="btn btn_default js_sort_cancle" href="javascript:;">取消</a>\n            </div>\n            <h4 class="title">{title}</h4>\n            <p class="counter"><span class="js_select_num"></span>/<span class="js_max_num"></span></p>\n        </div>\n        <div class="articles js_appmsg_list">\n        </div>\n    </div>\n</div>';
});define("tpl/media/plugin/audioItem.html.js",[],function(){
return'{each list as data i}\n<label class="frm_radio_label audio_item {if data.enable==true}disabled{/if}">\n    <i class="icon_radio"></i>\n    <span class="lbl_content">\n        <span class="audio_meta audio_title">{data.title}</span>\n        <span class="audio_meta audio_date">{data.update_time}</span>\n        <span class="audio_meta audio_length">{data.format_play_length}</span>\n        <span class=\'audio_meta audio_play jsPluginAudioPlay audio_default\' id="pluginAudioPlay_{i}">\n        </span>\n    </span>\n    <input type="radio" {if data.disabled}disabled="disabled"{/if}  data-label="{data.name}" data-value="{data.file_id}" data-index="{i}" class="frm_radio jsPluginAudioRadio" >\n</label>\n{/each}\n';
});define("tpl/media/plugin/audio.html.js",[],function(){
return'<div class="audio_box">\n    <div class="global_mod audio_box_hd float_layout gap_top" id="">\n        <p class="global_info gap_top_item tips_global jsAudioTips" style="display:none;">由于版本兼容的原因,你暂时只可以选择60秒内的语音发送</p>\n        <p class="global_extra">\n            <a class="btn btn_primary btn_add jsPluginAudioNew" href="javascript:;"><i class="icon14_common add_white"></i>新建语音</a>\n        </p>\n    </div>\n    <div class="audio_box_bd audio_list_container" id="">\n\n        <div class="media_list_tips_wrp tips_global" style="display:none;">\n            <span class="tips">暂无素材</span>\n            <span class="vm_box"></span>\n        </div>\n\n\n        <div class="media_list_tips_wrp" style="display:none;">\n            <i class="icon_loading_small white">loading...</i>\n            <span class="vm_box"></span>\n        </div>\n\n\n        <div class="audio_list jsPluginAudioList"></div>\n\n        <div class="pagination_wrp jsPluginAudioPage"></div>\n    </div>\n</div>\n';
});