define("common/wx/media/audio.js",["biz_web/lib/soundmanager2.js","tpl/media/audio.html.js","tpl/media/qqmusicaudio.html.js","widget/media.css","common/qq/Class.js"],function(i,s,t){
"use strict";
var e=wx.T,o=i("biz_web/lib/soundmanager2.js"),d=i("tpl/media/audio.html.js"),l=i("tpl/media/qqmusicaudio.html.js"),n=(i("widget/media.css"),
i("common/qq/Class.js")),u=null,a=null,r="wxAudioPlaying",h=function(){
a=o,a.setup({
url:"/mpres/zh_CN/htmledition/plprecorder/biz_web/",
preferFlash:!1,
debugMode:!1
});
};
$(window).load(function(){
h();
});
var c={
id:"",
source:"",
file_id:""
},m=n.declare({
init:function(i){
var s=this;
$.extend(!0,s,c,i),this.soundId=this.id||this.file_id,s.play_length=Math.ceil(1*s.play_length/1e3),
this.qqmusictpl&&(d=l);
var t=$(e(d,s));
s.dom=$(i.selector).append(t).data("opt",i),t.click(function(){
s.toggle();
});
},
getAudioURL:function(){
if(this.qqmusicurl)return this.qqmusicurl;
var i=this.source,s=this.id,t=this.file_id;
return i&&(i="&source="+i),wx.url("/cgi-bin/getvoicedata?msgid={id}&fileid={fileid}{source}".format({
id:s,
fileid:t,
source:i
}));
},
isPlaying:function(){
return null!=u&&this==u;
},
toggle:function(){
this.isPlaying()?this.stop():(u&&u.stop(),this.play());
},
play:function(i){
var s=this;
this.isPlaying()||(this.dom.addClass(r),!!u&&u.dom.removeClass(r),u=this,this.sound||(!a&&h(),
this.sound=a.createSound({
id:s.soundId,
url:s.getAudioURL(),
onfinish:function(){
u&&(u.dom.removeClass(r),u=null);
}
})),a.play(this.soundId),i&&i(this));
},
stop:function(i){
this.isPlaying()&&(u=null,this.dom.removeClass(r),a.stop(this.soundId),i&&i(this));
}
});
t.exports=m;
});