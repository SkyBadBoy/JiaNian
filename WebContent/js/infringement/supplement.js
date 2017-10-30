define("infringement/supplement.js",["common/wx/Cgi.js","common/wx/Tips.js","biz_web/utils/upload.js"],function(t){
"use strict";
var e=t("common/wx/Cgi.js"),n=t("common/wx/Tips.js"),i=t("biz_web/utils/upload.js"),p=$("#js_upload"),s=1*p.data("multi");
i.uploadTmpFile({
container:p,
multi:!0,
type:2,
width:106,
onComplete:function(t,e,a,o){
if(o&&o.base_resp&&0==o.base_resp.ret){
var l=p.parent().find("input[type=hidden]"),r=p.parent().parent().find(".upload_preview").show(),d=p.parent().parent().find(".js_remain"),m=l.val();
if(""!=m&&m.split("	").length>=s)return void n.err("最多可以上传"+s+"张");
l.val(m=""==m?o.content:m+"	"+o.content),r.append('<span><img src="%s" /><a href="javascript:;" class="in_opt js_remove">删除</a></span>'.sprintf(i.tmpFileUrl(o.content)));
var c=s-m.split("	").length;
d.text(c),p.closest(".upload_box").find(".frm_msg").hide(),p.closest(".upload_section").parent().find(".frm_msg").hide();
}
},
onAllComplete:function(){
var t=p.parent().find("input[type=hidden]"),e=t.val(),n=s-e.split("	").length;
p.parent().find(".upload_file_box").html("").hide(),0==n&&p.parent().find("object").css("left","99999999px");
}
}),p.on("click",function(){
var t=$(this).data("multi"),e=p.parent().find("input[type=hidden]").val();
t&&e.split("	").length>=t&&n.err("最多可以上传"+t+"张");
}),$("body").on("click",".js_remove",function(){
var t=$(this).parent(),e=t.index(),n=t.parent().parent(),i=n.find("input[type=hidden]"),p=i.val().split("	");
t.remove(),p.splice(e,1),n.find(".js_remain").text(s-p.length),i.val(p.join("	")),
n.find("object").css("left","0");
}),$("#js_submit").on("click",function(){
var t=$("input[name=supplement_copy_id]"),i=t.val();
return""==i?void t.parent().find(".frm_msg").show():($(this).btn(!1),void e.post({
url:wx.url("/acct/infringement"),
type:"post",
data:{
action:"uploadsupplement",
supplement_copy_id:i,
id:wx.cgiData.id
},
mask:!1
},function(t){
t&&0==t.submit_resp.ret?($("#js_supplement").hide(),$("#js_submited").show()):(n.err("系统错误，请重试"),
$(this).btn(!0));
}));
});
});