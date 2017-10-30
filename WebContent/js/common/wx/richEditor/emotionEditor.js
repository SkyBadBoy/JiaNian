define("common/wx/richEditor/emotionEditor.js",["widget/emotion_editor.css","tpl/richEditor/emotionEditor.html.js","common/wx/richEditor/wysiwyg.js","common/wx/richEditor/emotion.js","biz_web/utils/upload.js","common/wx/Tips.js","common/qq/Class.js"],function(t,i,e){
"use strict";
var o=wx.T,n=(t("widget/emotion_editor.css"),t("tpl/richEditor/emotionEditor.html.js")),r=t("common/wx/richEditor/wysiwyg.js"),s=t("common/wx/richEditor/emotion.js"),m=t("biz_web/utils/upload.js"),c=t("common/wx/Tips.js"),l=m.uploadCdnFile,d=t("common/qq/Class.js"),a={
isHTML:!0,
wordlimit:500,
hideUpload:!0
},w=1,h=d.declare({
init:function(t,i){
var e=this;
i=this.opt=$.extend(!0,{},a,i),e.selector$=t,i.gid=w++,e.selector$.html(o(n,i)),
e.emotion=new s(t.find(".js_emotionArea")),e.wysiwyg=new r(t.find(".js_editorArea"),{
init:function(){
t.find(".js_editorTip").html("还可以输入<em>{l}</em>字".format({
l:i.wordlimit
}));
},
textFilter:function(t){
return t=e.emotion.getEmotionText(t).replace(/<br.*?>/gi,"\n").replace(/<.*?>/g,""),
t=t.html(!1),i.isHTML?t:t.replace(/<br.*?>/gi,"\n").replace(/<.*?>/g,"");
},
nodeFilter:function(t){
var i="";
return"IMG"===t.nodeName.toUpperCase()&&(i=t),i;
},
change:function(){
var o=e.getContent(),n=i.wordlimit-o.length,r=t.find(".js_editorTip");
r.html(0>n?"已超出<em{cls}>{l}</em>字".format({
l:-n,
cls:' class="warn"'
}):"还可以输入<em>{l}</em>字".format({
l:n
}));
}
}),e.upload=l({
container:t.find(".js_upload"),
type:2,
multi:!1,
onComplete:function(t,i,o,n){
if(n&&n.base_resp&&0==n.base_resp.ret){
var r=n.content;
c.suc("上传成功"),e.wysiwyg.insertHTML(r);
}
}
}),e._initEvent(),e.insertHTML(i.text);
},
_initEvent:function(){
var t=$(".js_switch",this.selector$),i=this.emotion,e=this.wysiwyg;
i.click(function(t){
return e.insertHTML(i.getEmotionHTML(t)),!1;
}),i.hide(),t.click(function(){
i.show();
}),$(document).on("click","*",function(t){
var e=$(t.target),o=e.filter(".js_switch"),n=e.filter(".js_emotion_i"),r=e.filter(".emotions_item");
o.length||n.length||r.length||i.hide();
});
},
setContent:function(t){
var i=t||"";
i=this.opt.linebreak?i.replace(/\n/g,"<br>"):i,t=s.emoji(i),this.wysiwyg.setContent(t,i.html(!1));
},
insertHTML:function(t){
t=t||"",this.wysiwyg.insertHTML(t);
},
getContent:function(){
var t=this.wysiwyg.getContent();
return t="string"==typeof t?t.trim():"",this.opt.linebreak?t.replace(/<br[^>]*>/gi,"\n"):t;
},
getHTML:function(){
var t=this.wysiwyg.getHTML().html(!1);
return"string"==typeof t?t.trim():"";
}
});
e.exports=h;
});