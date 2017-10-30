define("common/wx/region.js",["common/wx/Cgi.js","biz_web/ui/dropdown.js"],function(t,e,i){
"use strict";
var n=t("common/wx/Cgi.js"),o=t("biz_web/ui/dropdown.js"),a={
disabled:!1
},c=function(t){
this.opt=$.extend(!0,{},a,t),this.container=$(t.container),this._action=null,u.call(this);
},r="/cgi-bin/getregions?t=setting/ajax-getregions&id={id}",s={
country:"国家",
province:"省份",
city:"城市"
},l=function(t){
return void 0===t&&(t=""),t+="",t&&t.replace(/(\u00a0|&nbsp;)/g," ").replace(/&quot;/gi,'"').replace(/&#39;/gi,"'");
},p=function(t,e){
var i=wx.url(r.format({
id:t||-1
}));
n.get({
url:i,
mask:!1
},e);
},u=function(){
var t=this;
t.opt.cgi&&(r=t.opt.cgi+"?t=setting/ajax-getregions&id={id}"),t.opt.list?$.each(["country","province","city"],function(e,i){
t.opt.list[i]=t.opt.list[i]||[];
}):t.opt.list={
country:[],
province:[],
city:[]
},t.opt.display=t.opt.display||{
country:!0,
province:!0,
city:!0
},t.selectors={},$.each(["country","province","city"],function(e,i){
var n="js_"+i+(1e4*Math.random()|0);
$('<div id="'+n+'" style="display:none"></div>').appendTo(t.container),t.selectors[i]="#"+n;
}),d.call(this,"0","country");
},d=function(t,e){
var i=this;
i._action=t,i.status="loading",p(t,function(n){
if(n&&n.base_resp&&0==n.base_resp.ret&&i._action==t){
i.status="loaded";
var o=[],a=i.opt.list[e];
$.each(n.data,function(t,n){
var a=l($.trim(n.name)).replace(/"/g,"&quot;"),c=!0;
i.opt.retain&&i.opt.retain[e]&&i.opt.retain[e].length>0?i.opt.retain[e].indexOf(parseInt(n.id,10))>-1?"中国"==a?o.unshift({
name:a,
value:n.id
}):o.push({
name:a,
value:n.id
}):c=!1:"中国"==a?o.unshift({
name:a,
value:n.id
}):o.push({
name:a,
value:n.id
}),c&&i.opt.remove&&i.opt.remove[e]&&i.opt.remove[e].length>0&&-1!=i.opt.remove[e].indexOf(parseInt(n.id,10))&&("中国"==a?o.shift():o.pop());
}),o=a.concat(o),h.call(i,e,o);
var c=i.opt.data;
c&&c[e]&&i[e].selected(c[e]),(v.call(i,e)||0==i.opt.display[e])&&i[e].container.hide(),
(v.call(i,e)||"city"==e)&&i.opt.onComplete&&i.opt.onComplete.call(i),i.opt.onLoadComplete&&i.opt.onLoadComplete.call(i,e,t);
}
});
},h=function(t,e){
var i=this;
i[t]=new o({
container:i.selectors[t],
label:s[t],
data:e,
disabled:i.opt.disabled,
callback:function(e,n){
switch(t){
case"country":
d.call(i,e,"province");
break;

case"province":
d.call(i,e,"city");
}
switch(t){
case"country":
i.province&&i.province.container.hide(),i.province=null;

case"province":
i.city&&i.city.container.hide(),i.city=null;
}
i.opt.onChange&&i.opt.onChange(t,e,n);
}
}),i[t].container.show();
},v=function(t){
return"loading"!=this.status&&(null==this[t]||0==this[t].opt.data.length);
},g={
get:function(t){
return v.call(this,t)?"":this[t]&&this[t].name||-1;
},
getAll:function(){
return{
country:this.get("country"),
province:this.get("province"),
city:this.get("city")
};
}
};
$.extend(c.prototype,g),i.exports=c;
});