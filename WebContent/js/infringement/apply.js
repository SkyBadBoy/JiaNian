define("infringement/apply.js",["common/wx/Cgi.js","common/wx/Tips.js","common/wx/Step.js","biz_web/utils/upload.js","common/wx/region.js","common/wx/dialog.js","common/wx/popup.js","biz_common/jquery.validate.js","biz_web/ui/checkbox.js","common/qq/jquery.plugin/serializeObject.js"],function(e){
"use strict";
var i=e("common/wx/Cgi.js"),t=e("common/wx/Tips.js"),n=e("common/wx/Step.js"),a=e("biz_web/utils/upload.js"),l=e("common/wx/region.js"),s=e("common/wx/dialog.js"),o=(e("common/wx/popup.js"),
e("biz_common/jquery.validate.js")),r=(e("biz_web/ui/checkbox.js"),template.render);
e("common/qq/jquery.plugin/serializeObject.js");
var c=function(){
var e,c,p=wx.cgiData,d="ok",_="no",u={
dialog:{},
add:function(e,i){
var t=[],n=u.getList(e).split("	");
i.each(function(){
var e=encodeURIComponent($(this).attr("data-value"));
-1==$.inArray(e,n)&&t.push({
nickname:$(this).data("nickname"),
alias:$(this).data("alias"),
headimg_url:$(this).data("headimg"),
biz_uin:$(this).data("value"),
del:1,
link:$(this).data("value"),
title:$(this).data("title")
});
}),1==e?$("#js_infListEntry1").find(".js_list").append(r("tpl_bizlist",{
data:t,
need_more:0
})):$("#js_infListEntry"+e).find(".js_list").append(r("tpl_linklist",{
data:t
}));
var a=u.getNum(e);
$(".js_remain"+e).text(40-a),$("#js_infListEntry"+e).find(".frm_msg").hide().children().hide();
},
remove:function(e,i){
var t,n;
e>1&&(n=i.parent().index())>=0?(t=$("#js_infListEntry"+e).find(".js_list").children(),
t.eq(n).remove(),t=u.dialog[e].popup("get").find(".js_list").children(),t.eq(n).remove()):i.parent().remove(),
$(".js_remain"+e).text(40-u.getNum(e));
},
list:function(e,i){
var t=u.dialog[1],n=t.popup("get").find(".js_list");
n[i](template.render("tpl_bizlist",e));
},
getNum:function(e){
var i=$("#js_infListEntry"+e).find(".js_list");
return i.length?i.children().length:-1;
},
getList:function(e,i){
var t=[],n=$("#js_infListEntry"+e).find(".js_list").children();
return i?(n.each(function(){
t.push({
headimg:$(this).data("headimg"),
nickname:$(this).data("nickname"),
alias:$(this).data("alias"),
link:$(this).data("value"),
title:$(this).data("title")
});
}),t):(n.each(function(){
var i=$(this).data("value");
if(i=1==e?i:encodeURIComponent(i),""==i)throw"logic err";
t.push(i);
}),t.join("	"));
}
},m=function(){
var i=!0,n=$("input[name=complain_type]:checked").val(),a=u.getNum(n),l=e.obligee.getAll();
return-1==l.country||-1==l.province||-1==l.city?(t.err("请完整填写权利人的通信地址"),!1):1==$("input[name=is_agent]:checked").val()&&(l=e.agent.getAll(),
-1==l.country||-1==l.province||-1==l.city)?(t.err("请完整填写代理人的通信地址"),!1):a>40?(t.err("最多添加40个公众号或文章链接"),
!1):"pending"==d?(t.err("正在校验原创文章链接"),!1):(i=$("#js_form").valid(),0==a&&($("#js_infListEntry"+n).find(".frm_msg").show().children().show(),
i=!1),i);
},f=function(i){
var t,n=$("#js_form").serializeObject();
if(n.obligee_type=$("#js_obligeeType").find("li.selected").data("value"),n.agent_type=$("#js_agentType").find("li.selected").data("value"),
t=e.obligee.getAll(),n.obligee_country=t.country,n.obligee_province=t.province,n.obligee_city=t.city,
t=e.agent.getAll(),n.agent_country=t.country,n.agent_province=t.province,n.agent_city=t.city,
n.is_agent=$("input[name=is_agent]:checked").val()||0,1==n.complain_type){
if(1==p.type)n.complain_uin_list=u.getList(n.complain_type),i&&(n.biz_list=u.getList(n.complain_type,!0));else if(i){
var l=$("input[name=complain_uin_list]");
n.biz_list=[{
headimg:l.data("headimg"),
alias:l.data("alias"),
nickname:l.data("nickname")
}];
}
}else n.complain_url_list=u.getList(n.complain_type),i&&(n.url_list=u.getList(n.complain_type,!0));
if(i&&(n.upload_list=[],$(".js_material"+n.complain_type).each(function(){
$(this).find("input[type=checkbox]").is(":checked")&&n.upload_list.push({
title:$(this).find("input[type=checkbox]").data("label"),
url:a.tmpFileUrl($(this).find("input[type=hidden]").val())
});
}),$("#js_other").is(":checked"))){
var s=[];
$("#js_other").closest(".upload_section").find("img").each(function(){
s.push($(this).attr("src"));
}),n.upload_list.push({
title:$("#js_other").data("label"),
url_list:s
});
}
return n;
},h=function(e){
var n=f();
return 0==n?(t.err("请完整填写表单"),void e(!1)):(n.action="presubmit",n.entrance_source=p.entrance_source,
void i.post({
url:wx.url("/acct/infringement"),
data:n,
mask:!1
},function(i){
i&&i.presubmit_resp&&0==i.presubmit_resp.ret?e(!0):(t.err("请完整填写表单"),e(!1));
}));
},g=function(e){
var n=$("input[name=notice_copy_id]").val();
return""==n?(t.err("请上传投诉通知书扫描件"),void e()):void i.post({
url:wx.url("/acct/infringement"),
data:{
action:"submit",
type:$("input[name=type]").val(),
notice_copy_id:n
},
mask:!1
},function(i){
i&&i.base_resp&&0==i.base_resp.ret?($(".js_stepWrapper").hide(),$("#js_submited").show()):(t.err("请上传投诉通知书扫描件"),
e(i));
});
},v=function(e,i){
var t=i.numberOfInvalids();
0!=t&&$("html, body").animate({
scrollTop:$(i.errorList[0].element).parent().offset().top-100
},500);
},y=function(){
var e=$("#js_step2Wrapper"),i=f(!0);
i.obligee_cert_copy_id=a.tmpFileUrl(i.obligee_cert_copy_id),i.agent_cert_copy_id=a.tmpFileUrl(i.agent_cert_copy_id),
i.agent_permission_copy_id=a.tmpFileUrl(i.agent_permission_copy_id);
var t=r("tpl_preview",i);
e.html(t).show();
},b={
init:function(){
b.initStep(),b.initValidate(),b.initUpload(),b.initRegion(),b.initPopup(),b.initCheckbox(),
b.initEvent(),b.afterload();
},
initStep:function(){
c=new n({
container:"#js_processor",
selected:1,
names:["1 填写投诉内容","2 预览并打印通知书","3 盖章或签名后提交通知书"]
}),1==p.refill&&c.setStep(3);
},
initValidate:function(){
var e=($("input[name=complain_type]:checked").val(),{
obligee_name:{
required:!0,
maxlength:30
},
obligee_cert_no:{
required:0==wx.cgiData.is_wx_verify,
cert:0==wx.cgiData.is_wx_verify
},
obligee_cert_copy_id:{
required:0==wx.cgiData.is_wx_verify
},
obligee_company:{
required:{
depends:function(){
return 0==wx.cgiData.is_wx_verify&&1==$("#js_obligeeType").find("li.selected").data("value");
}
}
},
obligee_address:{
required:!0,
maxlength:50
},
obligee_zip_code:{
required:!0,
postcode:!0
},
obligee_contacts_name:{
required:{
depends:function(){
return 1==$("#js_obligeeType").find("li.selected").data("value");
}
},
maxlength:10
},
obligee_mobile_phone:{
required:!0,
mobile:!0
},
obligee_email:{
required:!0,
email:!0,
email2:!0,
maxlength:50
},
agent_permission_copy_id:{
required:{
depends:function(){
return 1==$("input[name=is_agent]:checked").val();
}
}
},
complain_alias:{
required:{
depends:function(){
return 2==p.type&&1==$("input[name=complain_type]:checked").val();
}
},
complainuinlist:{
depends:function(){
return 2==p.type&&1==$("input[name=complain_type]:checked").val();
}
}
},
complain_desc:{
required:1==p.type,
maxlength:150
},
complain_reason:{
required:2==wx.cgiData.type,
maxlength:150
},
material:{
required:{
depends:function(){
var e=$("input[name=complain_type]:checked").val();
return $(".js_material"+e).find("input[type=checkbox]:checked").length+$("#js_other:checked").length==0;
}
}
},
trademark_copy_id:{
required:{
depends:function(){
return 1==$("input[name=complain_type]:checked").val()&&$("#js_trademark").is(":checked");
}
}
},
license_copy_id:{
required:{
depends:function(){
return 1==$("input[name=complain_type]:checked").val()&&$("#js_license").is(":checked");
}
}
},
id_card_copy_id:{
required:{
depends:function(){
return 1==$("input[name=complain_type]:checked").val()&&$("#js_idCard").is(":checked");
}
}
},
publish_copy_id:{
required:{
depends:function(){
return 2==$("input[name=complain_type]:checked").val()&&$("#js_publish").is(":checked");
}
}
},
valid_publish_copy_id:{
required:{
depends:function(){
return 2==$("input[name=complain_type]:checked").val()&&$("#js_validPublish").is(":checked");
}
}
},
ori_article_copy_id:{
required:{
depends:function(){
return 2==$("input[name=complain_type]:checked").val()&&$("#js_article").is(":checked");
}
}
},
other_copy_id:{
required:{
depends:function(){
return $("#js_other").is(":checked");
}
}
},
ori_article_url:{
url:!0
}
}),i={
obligee_name:{
required:function(){
return 1==$("#js_obligeeType").find("li.selected").data("value")?"请填写该组织的名称":"请填写姓名";
},
maxlength:"不能超过30个字"
},
agent_name:{
required:function(){
return 1==$("#js_agentType").find("li.selected").data("value")?"请填写该组织的名称":"请填写姓名";
},
maxlength:"不能超过10个字"
},
obligee_cert_no:{
required:function(){
return 1==$("#js_obligeeType").find("li.selected").data("value")?"请填写有效证件号":"请填写有效身份证号";
}
},
agent_cert_no:{
required:function(){
return 1==$("#js_agentType").find("li.selected").data("value")?"请填写有效证件号":"请填写有效身份证号";
}
},
obligee_cert_copy_id:{
required:function(){
return 1==$("#js_obligeeType").find("li.selected").data("value")?"请上传有效证件扫描件":"请填写身份证扫描件";
}
},
agent_cert_copy_id:{
required:function(){
return 1==$("#js_agentType").find("li.selected").data("value")?"请上传有效证件扫描件":"请填写身份证扫描件";
}
},
obligee_company:"请填写法定代表人/负责人",
agent_company:"请填写法定代表人/负责人",
obligee_address:{
required:"请填写街道名称",
maxlength:"不能超过50个字"
},
agent_address:{
required:"请填写街道名称",
maxlength:"不能超过50个字"
},
obligee_zip_code:"请填写邮编",
agent_zip_code:"请填写邮编",
obligee_contacts_name:{
required:"请填写联系人",
maxlength:"不能超过10个字"
},
agent_contacts_name:{
required:"请填写联系人",
maxlength:"不能超过10个字"
},
obligee_mobile_phone:"请填写手机号码",
agent_mobile_phone:"请填写手机号码",
obligee_email:{
required:"请填写E-mail",
maxlength:"不能超过50个字"
},
agent_email:{
required:"请填写E-mail",
maxlength:"不能超过50个字"
},
agent_permission_copy_id:"请上传代理许可证",
complain_alias:{
required:"请填写原始ID或微信号"
},
complain_desc:{
required:"您未添加任何描述",
maxlength:"不能超过150个字"
},
complain_reason:{
required:"请填写您的理由",
maxlength:"不能超过150个字"
},
material:"至少选择一项证明材料",
trademark_copy_id:"请上传图片",
license_copy_id:"请上传图片",
id_card_copy_id:"请上传图片",
publish_copy_id:"请上传图片",
valid_publish_copy_id:"请上传图片",
ori_article_copy_id:"请至少上传一张图片",
other_copy_id:"请至少上传一张图片",
ori_article_url:{
url:"请输入正确的文章链接"
}
};
$("#js_form").validate({
rules:e,
messages:i,
ignore:[],
focusInvalid:!0,
submitHandler:g,
invalidHandler:v
}),$("#js_agentForm").find("input").each(function(){
var i=$(this).attr("name"),t={};
~i.indexOf("agent")&&("agent_company"==i||"agent_contacts_name"==i?t.required={
depends:function(){
return 1==$("input[name=is_agent]:checked").val()&&1==$("#js_agentType").find("li.selected").data("value");
}
}:(i="obligee"+i.substr(5),t=$.extend(!0,{},e[i]),$.each(t,function(e,i){
"number"!=typeof i&&(t[e]={
depends:function(){
return 1==$("input[name=is_agent]:checked").val();
}
});
})),$(this).rules("add",t));
}),o.addMethod("cert",function(e,i){
var t=-1==$(i).attr("name").indexOf("agent")?"js_obligeeType":"js_agentType";
return e=$.trim(e),1==$("#"+t).find("li.selected").data("value")?!0:o.rules.idcard(e);
},o.messages.idcard),o.addMethod("postcode",function(e){
return e=$.trim(e),/^\d{6}$/.test(e);
},"请输入正确的邮编格式"),o.addMethod("articleurl",function(e){
return e=$.trim(e),""==e?!0:"ok"==d;
},"请输入正确的文章链接"),o.addMethod("complainuinlist",function(e){
return e=$.trim(e),"ok"==_;
},"请填写正确的原始ID或微信号"),o.addMethod("email2",function(e){
return e=$.trim(e),/^([a-zA-Z0-9_\.\-])+@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(e);
},"请输入正确格式的电子邮件");
},
initUpload:function(){
var e=0;
$(".js_select_file").each(function(){
var i=$(this),n="js_upload"+e++,l=1*(i.data("multi")||0);
i.attr("id",i.attr("id")||n),a.uploadTmpFile({
container:i,
multi:!!l,
type:2,
width:106,
onComplete:function(e,n,s,o){
if(o&&o.base_resp&&0==o.base_resp.ret){
var r=i.parent().find("input[type=hidden]"),c=i.parent().parent().find(".upload_preview").show(),p=i.parent().parent().find(".js_remain"),d=r.val();
if(l){
if(""!=d&&d.split("	").length>=l)return void t.err("最多可以上传"+l+"张");
r.val(d=""==d?o.content:d+"	"+o.content),c.append('<span><img src="%s" /><a href="javascript:;" class="in_opt js_remove">删除</a></span>'.sprintf(a.tmpFileUrl(o.content)));
var _=l-d.split("	").length;
p.text(_);
}else i.text("重新上传"),r.val(o.content),c.html('<img src="%s" />'.sprintf(a.tmpFileUrl(o.content)));
i.closest(".upload_box").find(".frm_msg").hide(),i.closest(".upload_section").parent().find(".frm_msg").hide();
}
},
onAllComplete:function(){
var e=i.parent().find("input[type=hidden]"),t=e.val(),n=l-t.split("	").length;
i.parent().find(".upload_file_box").html("").hide(),0==n&&i.parent().find("object").css("left","99999999px");
}
}),i.on("click",function(){
var e=$(this).data("multi"),n=i.parent().find("input[type=hidden]").val();
e&&n.split("	").length>=e&&t.err("最多可以上传"+e+"张");
});
}),$("body").on("click",".upload_preview .js_remove",function(){
var e=$(this).parent(),i=e.index(),t=e.parent().parent(),n=t.find("input[type=hidden]"),a=n.val().split("	"),l=1*(t.find(".js_select_file").data("multi")||0);
e.remove(),a.splice(i,1),t.find(".js_remain").text(l-a.length),n.val(a.join("	")),
t.find("object").css("left","0");
});
},
initRegion:function(){
e={},e.obligee=new l({
container:"#js_obligeeRegion",
data:{
country:p.obligee_country||"中国",
province:p.obligee_province||null,
city:p.obligee_city||null
}
}),e.agent=new l({
container:"#js_agentRegion",
data:{
country:p.agent_country||"中国",
province:p.agent_province||null,
city:p.agent_city||null
}
});
},
initPopup:function(){
$(".js_infListBtn").on("click",function(){
var e=$("input[name=complain_type]:checked").val(),i=u.getNum(e);
if(i>=40)return void t.err("最多添加40个");
var n=$(1==e?"#tpl_biz":"#tpl_link").popup({
title:1==e?"添加侵权的公众号":"添加侵权文章链接",
data:{
type:e
},
className:"infringement_dialog",
width:1==e?956:void 0,
buttons:[{
text:"确定",
click:function(){
if(1==e){
if(this.$dialogWrp.find(".dialog_ft>span:eq(0)").hasClass("btn_disabled"))return;
var n=this.$dialogWrp.find("li.account_selected");
if(n.length+i>40)return void t.err("最多添加40个公众号");
}else{
var n=this.$dialogWrp.find(".js_list").children();
if(n.length>40)return void t.err("最多添加40个链接");
}
u.add(e,n),this.hide();
},
type:1==e?"disabled":"primary"
},{
text:"取消",
click:function(){
this.hide();
}
}],
onHide:function(){
this.remove();
}
});
switch(n.popup("get").find("input").placeholder(),e){
case"1":
b.initDialog1(n);
break;

case"2":
case"3":
b.initDialog(e,n);
}
}),$("body").on("click",".js_list .js_remove",function(){
$(this).parent().remove();
});
},
initDialog1:function(e){
var n="",a=e.popup("get"),l=function(l){
l=l||0,l||a.find(".js_list").empty(),$("#js_loading").show(),a.find(".js_empty").hide(),
i.post({
url:wx.url("/acct/infringement"),
data:{
action:"getbizlist",
nickname:n,
begin:l,
type:p.type
},
mask:!1
},function(n){
if($("#js_loading").hide(),n&&n.base_resp&&0==n.base_resp.ret&&n.getbizlist_resp){
if(0==n.getbizlist_resp.data.length&&0==l)a.find(".js_empty").show(),a.find(".js_list").hide();else{
a.find(".js_empty").hide(),a.find(".js_list").show();
var s=e.popup("get").find(".js_list"),o=0==l?"html":"append";
s[o](template.render("tpl_bizlist",n.getbizlist_resp));
}
return void(1==n.getbizlist_resp.end&&a.find(".js_more").remove());
}
n.getbizlist_resp?i.show(n):t.err("系统错误，请重试");
});
};
$("#js_search").on("click",function(){
var e=$.trim($("#js_searchInput").val());
return""==e?void t.err("不能为空"):(n=e,void l());
}),$("#js_searchInput").on("keypress",function(e){
13==e.keyCode&&$("#js_search").click();
}),a.on("click",".js_bizItem",function(){
$(this).siblings(".account_selected").length>=40?t.err("最多选择40个"):$(this).toggleClass("account_selected"),
$(this).parent().find(".account_selected").length>0?a.find(".dialog_ft>span:eq(0)").removeClass("btn_disabled").addClass("btn_primary"):a.find(".dialog_ft>span:eq(0)").removeClass("btn_primary").addClass("btn_disabled");
}).on("click",".js_more",function(){
var e=a.find(".js_bizItem").length;
$(this).remove(),l(e);
}).on("mousewheel",".in_bd",function(e){
this.scrollTop-=e.originalEvent.wheelDelta>0?60:-60,e.preventDefault();
}).on("DOMMouseScroll",".in_bd",function(e){
this.scrollTop+=e.originalEvent.detail>0?60:-60,e.preventDefault();
});
},
initDialog:function(e,n){
$("#js_add"+e).on("click",function(){
var a=$(this).find("i");
if(!a.hasClass("btn_loading")){
var l=$.trim($("#js_addInput"+e).val());
if(""==l)return void t.err("不能为空");
if(n.popup("get").find(".js_list").children().length>=40)return void t.err("最多添加40个链接");
if(!/^http(s)?:\/\/mp\.weixin\.qq\.com/.test(l))return void t.err("请输入mp.weixin.qq.com域名下的文章");
a.btn(!1),i.post({
url:wx.url("/acct/infringement"),
data:{
action:"checkurl",
complain_url:encodeURIComponent(l),
type:p.type
},
mask:!1
},function(i){
a.btn(!0);
var s=+(i&&i.checkurl_resp?i.checkurl_resp.base_resp.ret:-1);
if(0==s){
var o=i.checkurl_resp.title,c=n.popup("get").find(".js_list"),p=!1,d=r("tpl_linkitem",{
link:l,
title:o
});
c.children().each(function(){
l==$(this).data("value")&&(p=!0);
}),p||n.popup("get").find(".js_list").append(d),$("#js_addInput"+e).val("");
}else t.err(-1==s?"系统错误，请重试":-2==s?"此文章已被封":"请输入mp.weixin.qq.com域名下的文章");
});
}
}),$("#js_addInput"+e).on("keypress",function(i){
13==i.keyCode&&$("#js_add"+e).click();
}),n.popup("get").find(".js_list").html(r("tpl_linklist",{
data:u.getList(e,!0)
}));
},
initCheckbox:function(){
$("input[name=is_agent]").checkbox({
onChanged:function(e){
1==e.val()?$("#js_agentForm").show():$("#js_agentForm").hide();
}
}).adjust(p.is_agent||"0"),$("input[name=complain_type]").checkbox({
onChanged:function(e){
var i=e.val();
$(".js_infListEntry").hide(),$("#js_infListEntry"+i).show(),$(".js_material").hide(),
$(".js_material"+i).show(),2==i?$("#js_original").show():$("#js_original").hide();
}
});
var e=p.complain_type||"1";
setTimeout(function(){
$("input[name=complain_type][value="+e+"]").trigger("click");
},0),1==p.entrance_source&&($("input[name=complain_type]").eq(0).closest("label").hide(),
$("input[name=complain_type]").eq(2).closest("label").hide(),$("input[name=complain_type]").eq(1).click());
var i=$(".js_material").parent().find("input[type=checkbox]");
i.checkbox({
onChanged:function(e){
e.closest(".upload_section").find(".upload_box").toggle();
}
}),i.each(function(){
var e=$(this).closest(".upload_section");
e.find("input[type=hidden]").val(""),$(this).checkbox("checked",!1);
});
},
initEvent:function(){
function e(){
var e=$("input[name=complain_alias]"),t=$.trim(e.val());
""!=t&&($("input[name=complain_uin_list]").val(""),_="pending",i.post({
url:wx.url("/acct/infringement"),
data:{
action:"getbizlist",
nickname:t,
begin:0,
type:p.type
},
mask:!1
},function(i){
if(i&&i.base_resp&&0==i.base_resp.ret){
var t=i.getbizlist_resp.data,n=e.parent().siblings(".frm_msg");
return void(1==t.length&&t[0].biz_uin?($("input[name=complain_uin_list]").val(t[0].biz_uin).data("headimg",t[0].headimg_url).data("nickname",t[0].nickname).data("alias",t[0].alias),
_="ok"):(n.length?n.show().children().show().text("请填写正确的原始ID或微信号"):e.parent().after('<p class="frm_msg fail" style="display: block;"><span for="complain_alias" class="frm_msg_content" style="display: inline;">请填写正确的原始ID或微信号</span></p>'),
_="wrong"));
}
_="wrong";
}));
}
$("#js_obligeeType").on("click","li",function(){
if(!$(this).hasClass("selected")){
$(this).parent().children().removeClass("selected"),$(this).addClass("selected");
var e={
stop:$("#js_obligeeStop"),
name:$("#js_obligeeName"),
cert:$("#js_obligeeCertNo"),
copyid:$("#js_obligeeCertCopyId"),
company:$("#js_obligeeCompany"),
contact:$("#js_obligeeContactsName")
};
1==$(this).data("value")?(e.stop.css({
left:150
}),e.name.find("label").text("名称"),e.cert.find("label").text("有效证件号"),e.cert.find(".frm_tips").show(),
e.copyid.find("label").text("有效证件扫描件"),e.copyid.find(".upload_tips").html("请上传营业执照或组织机构代码证清晰彩色原件扫描件或数码照<br/>支持.jpg .jpeg .bmp .gif格式照片，大小不超过2M。"),
e.company.show(),e.contact.show()):(e.stop.css({
left:250
}),e.name.find("label").text("姓名"),e.cert.find("label").text("有效证件号(身份证)"),e.cert.find(".frm_tips").hide(),
e.copyid.find("label").text("有效证件扫描件(身份证)"),e.copyid.find(".upload_tips").html("身份证上的所有信息清晰可见，必须能看清证件号码。<br/>支持.jpg .jpeg .bmp .gif格式照片，大小不超过2M。"),
e.company.hide(),e.contact.hide()),e.name.find("input").val(""),e.cert.find("input").val(""),
e.copyid.find(".upload_preview").empty().hide(),e.copyid.find("input").val("");
}
}),$("#js_agentType").on("click","li",function(){
if(!$(this).hasClass("selected")){
$(this).parent().children().removeClass("selected"),$(this).addClass("selected");
var e={
stop:$("#js_agentStop"),
name:$("#js_agentName"),
cert:$("#js_agentCertNo"),
copyid:$("#js_agentCertCopyId"),
company:$("#js_agentCompany"),
contact:$("#js_agentContactsName")
};
1==$(this).data("value")?(e.stop.css({
left:150
}),e.name.find("label").text("名称"),e.cert.find("label").text("有效证件号"),e.cert.find(".frm_tips").show(),
e.copyid.find("label").text("有效证件扫描件"),e.copyid.find(".upload_tips").html("请上传营业执照或组织机构代码证清晰彩色原件扫描件或数码照<br/>支持.jpg .jpeg .bmp .gif格式照片，大小不超过2M。"),
e.company.show(),e.contact.show()):(e.stop.css({
left:250
}),e.name.find("label").text("姓名"),e.cert.find("label").text("有效证件号(身份证)"),e.cert.find(".frm_tips").hide(),
e.copyid.find("label").text("有效证件扫描件(身份证)"),e.copyid.find(".upload_tips").html("身份证上的所有信息清晰可见，必须能看清证件号码。<br/>支持.jpg .jpeg .bmp .gif格式照片，大小不超过2M。"),
e.company.hide(),e.contact.hide()),e.name.find("input").val(""),e.cert.find("input").val(""),
e.copyid.find(".upload_preview").empty().hide(),e.copyid.find("input").val("");
}
}),$("body").on("click",".js_step",function(){
if(!$(this).hasClass("btn_loading")){
var e=$(this),i=$(this).data("type"),n=$(this).data("step");
if("next"==i&&1==n&&m())$(this).btn(!1),h(function(i){
e.btn(!0),i&&($(".js_stepWrapper").hide(),y(),$(window).scrollTop(0),c.setStep(2));
});else if("prev"==i&&2==n)$(".js_stepWrapper").hide(),$("#js_step1Wrapper").show(),
c.setStep(1);else if("next"==i&&2==n){
if(e.hasClass("btn_disabled"))return void t.err("请先打印通知书");
$(".js_stepWrapper").hide(),$("#js_step3Wrapper").show(),c.setStep(3),$(window).scrollTop(0);
}else"prev"==i&&3==n?($(".js_stepWrapper").hide(),$("#js_step2Wrapper").show(),c.setStep(2)):"next"==i&&3==n&&(e.btn(!1),
g(function(){
e.btn(!0);
}));
}
}),$("body").on("click","#js_print",function(){
window.open(wx.url("/acct/infringement?action=getprint&t=infringement/print")),$("#js_printedstep").enable();
}),$("input[name=complain_alias]").on("blur",e),""!=$("input[name=complain_alias]").val()&&e(),
$("#js_reapply").on("click",function(){
s.show({
type:"warn",
msg:"重新填写，则清空已填内容，申请新的投诉单。",
buttons:[{
text:"重新填写",
click:function(){
window.onbeforeunload=null,location.href=wx.url("/acct/infringement?action=getinfo&t=infringement/infringement_add&type="+p.type);
},
type:"primary"
},{
text:"取消",
click:function(){
this.remove();
},
type:"normal"
}],
title:"未完成投诉单"
});
});
var n=0;
$(window).on("scroll",function(){
var e=$("#js_printtips");
if(0!=e.length){
n=n||e.offset().top;
var i=$(window).scrollTop()-n;
i>0?e.addClass("mod_sticky"):e.removeClass("mod_sticky");
}
}),window.onbeforeunload=function(){
return $("#js_step2Wrapper:visible,#js_step3Wrapper:visible").length?"为数据安全起见，关闭后只能上传通知书或反通知书的扫描件，无法进行内容更改或下载。":void 0;
};
},
afterload:function(){
if(1==wx.cgiData.is_wx_ori){
var e=[];
console.log("after"),$("input[name=complain_type]").eq(1).click();
var i=JSON.parse(wx.cgiData.complain_list);
console.log(i),$.each(i.list,function(t){
console.log(i.list[t]),e.push({
link:i.list[t].url,
title:i.list[t].title
});
}),console.log(e),$("#js_infListEntry2").find(".js_list").append(r("tpl_linklist",{
data:e
})),$("input[name=ori_article_url]").val("http://mp.weixin.qq.com"+i.ori_url);
}
}
};
return b;
}();
c.init();
});