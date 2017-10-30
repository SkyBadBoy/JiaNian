define("biz_common/jquery.ui/jquery.ui.draggable.js",[],function(){
!function(t,e){
function i(e,i){
var o,n,r,a=e.nodeName.toLowerCase();
return"area"===a?(o=e.parentNode,n=o.name,e.href&&n&&"map"===o.nodeName.toLowerCase()?(r=t("img[usemap=#"+n+"]")[0],
!!r&&s(r)):!1):(/input|select|textarea|button|object/.test(a)?!e.disabled:"a"===a?e.href||i:i)&&s(e);
}
function s(e){
return t.expr.filters.visible(e)&&!t(e).parents().addBack().filter(function(){
return"hidden"===t.css(this,"visibility");
}).length;
}
var o=0,n=/^ui-id-\d+$/;
t.ui=t.ui||{},t.extend(t.ui,{
version:"1.10.3"
}),t.fn.extend({
focus:function(e){
return function(i,s){
return"number"==typeof i?this.each(function(){
var e=this;
setTimeout(function(){
t(e).focus(),s&&s.call(e);
},i);
}):e.apply(this,arguments);
};
}(t.fn.focus),
scrollParent:function(){
var e;
return e=t.ui.ie&&/(static|relative)/.test(this.css("position"))||/absolute/.test(this.css("position"))?this.parents().filter(function(){
return/(relative|absolute|fixed)/.test(t.css(this,"position"))&&/(auto|scroll)/.test(t.css(this,"overflow")+t.css(this,"overflow-y")+t.css(this,"overflow-x"));
}).eq(0):this.parents().filter(function(){
return/(auto|scroll)/.test(t.css(this,"overflow")+t.css(this,"overflow-y")+t.css(this,"overflow-x"));
}).eq(0),/fixed/.test(this.css("position"))||!e.length?t(document):e;
},
zIndex:function(i){
if(i!==e)return this.css("zIndex",i);
if(this.length)for(var s,o,n=t(this[0]);n.length&&n[0]!==document;){
if(s=n.css("position"),("absolute"===s||"relative"===s||"fixed"===s)&&(o=parseInt(n.css("zIndex"),10),
!isNaN(o)&&0!==o))return o;
n=n.parent();
}
return 0;
},
uniqueId:function(){
return this.each(function(){
this.id||(this.id="ui-id-"+ ++o);
});
},
removeUniqueId:function(){
return this.each(function(){
n.test(this.id)&&t(this).removeAttr("id");
});
}
}),t.extend(t.expr[":"],{
data:t.expr.createPseudo?t.expr.createPseudo(function(e){
return function(i){
return!!t.data(i,e);
};
}):function(e,i,s){
return!!t.data(e,s[3]);
},
focusable:function(e){
return i(e,!isNaN(t.attr(e,"tabindex")));
},
tabbable:function(e){
var s=t.attr(e,"tabindex"),o=isNaN(s);
return(o||s>=0)&&i(e,!o);
}
}),t.extend(t.ui,{
plugin:{
add:function(e,i,s){
var o,n=t.ui[e].prototype;
for(o in s)n.plugins[o]=n.plugins[o]||[],n.plugins[o].push([i,s[o]]);
},
call:function(t,e,i){
var s,o=t.plugins[e];
if(o&&t.element[0].parentNode&&11!==t.element[0].parentNode.nodeType)for(s=0;s<o.length;s++)t.options[o[s][0]]&&o[s][1].apply(t.element,i);
}
},
hasScroll:function(e,i){
if("hidden"===t(e).css("overflow"))return!1;
var s=i&&"left"===i?"scrollLeft":"scrollTop",o=!1;
return e[s]>0?!0:(e[s]=1,o=e[s]>0,e[s]=0,o);
}
});
}(jQuery),function(t,e){
var i=0,s=Array.prototype.slice,o=t.cleanData;
t.cleanData=function(e){
for(var i,s=0;null!=(i=e[s]);s++)try{
t(i).triggerHandler("remove");
}catch(n){}
o(e);
},t.widget=function(e,i,s){
var o,n,r,a,l={},h=e.split(".")[0];
e=e.split(".")[1],o=h+"-"+e,s||(s=i,i=t.Widget),t.expr[":"][o.toLowerCase()]=function(e){
return!!t.data(e,o);
},t[h]=t[h]||{},n=t[h][e],r=t[h][e]=function(t,e){
return this._createWidget?void(arguments.length&&this._createWidget(t,e)):new r(t,e);
},t.extend(r,n,{
version:s.version,
_proto:t.extend({},s),
_childConstructors:[]
}),a=new i,a.options=t.widget.extend({},a.options),t.each(s,function(e,s){
return t.isFunction(s)?void(l[e]=function(){
var t=function(){
return i.prototype[e].apply(this,arguments);
},o=function(t){
return i.prototype[e].apply(this,t);
};
return function(){
var e,i=this._super,n=this._superApply;
return this._super=t,this._superApply=o,e=s.apply(this,arguments),this._super=i,
this._superApply=n,e;
};
}()):void(l[e]=s);
}),r.prototype=t.widget.extend(a,{
widgetEventPrefix:n?a.widgetEventPrefix:e
},l,{
constructor:r,
namespace:h,
widgetName:e,
widgetFullName:o
}),n?(t.each(n._childConstructors,function(e,i){
var s=i.prototype;
t.widget(s.namespace+"."+s.widgetName,r,i._proto);
}),delete n._childConstructors):i._childConstructors.push(r),t.widget.bridge(e,r);
},t.widget.extend=function(i){
for(var o,n,r=s.call(arguments,1),a=0,l=r.length;l>a;a++)for(o in r[a])n=r[a][o],
r[a].hasOwnProperty(o)&&n!==e&&(i[o]=t.isPlainObject(n)?t.isPlainObject(i[o])?t.widget.extend({},i[o],n):t.widget.extend({},n):n);
return i;
},t.widget.bridge=function(i,o){
var n=o.prototype.widgetFullName||i;
t.fn[i]=function(r){
var a="string"==typeof r,l=s.call(arguments,1),h=this;
return r=!a&&l.length?t.widget.extend.apply(null,[r].concat(l)):r,this.each(a?function(){
var s,o=t.data(this,n);
return o?t.isFunction(o[r])&&"_"!==r.charAt(0)?(s=o[r].apply(o,l),s!==o&&s!==e?(h=s&&s.jquery?h.pushStack(s.get()):s,
!1):void 0):t.error("no such method '"+r+"' for "+i+" widget instance"):t.error("cannot call methods on "+i+" prior to initialization; attempted to call method '"+r+"'");
}:function(){
var e=t.data(this,n);
e?e.option(r||{})._init():t.data(this,n,new o(r,this));
}),h;
};
},t.Widget=function(){},t.Widget._childConstructors=[],t.Widget.prototype={
widgetName:"widget",
widgetEventPrefix:"",
defaultElement:"<div>",
options:{
disabled:!1,
create:null
},
_createWidget:function(e,s){
s=t(s||this.defaultElement||this)[0],this.element=t(s),this.uuid=i++,this.eventNamespace="."+this.widgetName+this.uuid,
this.options=t.widget.extend({},this.options,this._getCreateOptions(),e),this.bindings=t(),
this.hoverable=t(),this.focusable=t(),s!==this&&(t.data(s,this.widgetFullName,this),
this._on(!0,this.element,{
remove:function(t){
t.target===s&&this.destroy();
}
}),this.document=t(s.style?s.ownerDocument:s.document||s),this.window=t(this.document[0].defaultView||this.document[0].parentWindow)),
this._create(),this._trigger("create",null,this._getCreateEventData()),this._init();
},
_getCreateOptions:t.noop,
_getCreateEventData:t.noop,
_create:t.noop,
_init:t.noop,
destroy:function(){
this._destroy(),this.element.unbind(this.eventNamespace).removeData(this.widgetName).removeData(this.widgetFullName).removeData(t.camelCase(this.widgetFullName)),
this.widget().unbind(this.eventNamespace).removeAttr("aria-disabled").removeClass(this.widgetFullName+"-disabled ui-state-disabled"),
this.bindings.unbind(this.eventNamespace),this.hoverable.removeClass("ui-state-hover"),
this.focusable.removeClass("ui-state-focus");
},
_destroy:t.noop,
widget:function(){
return this.element;
},
option:function(i,s){
var o,n,r,a=i;
if(0===arguments.length)return t.widget.extend({},this.options);
if("string"==typeof i)if(a={},o=i.split("."),i=o.shift(),o.length){
for(n=a[i]=t.widget.extend({},this.options[i]),r=0;r<o.length-1;r++)n[o[r]]=n[o[r]]||{},
n=n[o[r]];
if(i=o.pop(),s===e)return n[i]===e?null:n[i];
n[i]=s;
}else{
if(s===e)return this.options[i]===e?null:this.options[i];
a[i]=s;
}
return this._setOptions(a),this;
},
_setOptions:function(t){
var e;
for(e in t)this._setOption(e,t[e]);
return this;
},
_setOption:function(t,e){
return this.options[t]=e,"disabled"===t&&(this.widget().toggleClass(this.widgetFullName+"-disabled ui-state-disabled",!!e).attr("aria-disabled",e),
this.hoverable.removeClass("ui-state-hover"),this.focusable.removeClass("ui-state-focus")),
this;
},
enable:function(){
return this._setOption("disabled",!1);
},
disable:function(){
return this._setOption("disabled",!0);
},
_on:function(e,i,s){
var o,n=this;
"boolean"!=typeof e&&(s=i,i=e,e=!1),s?(i=o=t(i),this.bindings=this.bindings.add(i)):(s=i,
i=this.element,o=this.widget()),t.each(s,function(s,r){
function a(){
return e||n.options.disabled!==!0&&!t(this).hasClass("ui-state-disabled")?("string"==typeof r?n[r]:r).apply(n,arguments):void 0;
}
"string"!=typeof r&&(a.guid=r.guid=r.guid||a.guid||t.guid++);
var l=s.match(/^(\w+)\s*(.*)$/),h=l[1]+n.eventNamespace,c=l[2];
c?o.delegate(c,h,a):i.bind(h,a);
});
},
_off:function(t,e){
e=(e||"").split(" ").join(this.eventNamespace+" ")+this.eventNamespace,t.unbind(e).undelegate(e);
},
_delay:function(t,e){
function i(){
return("string"==typeof t?s[t]:t).apply(s,arguments);
}
var s=this;
return setTimeout(i,e||0);
},
_hoverable:function(e){
this.hoverable=this.hoverable.add(e),this._on(e,{
mouseenter:function(e){
t(e.currentTarget).addClass("ui-state-hover");
},
mouseleave:function(e){
t(e.currentTarget).removeClass("ui-state-hover");
}
});
},
_focusable:function(e){
this.focusable=this.focusable.add(e),this._on(e,{
focusin:function(e){
t(e.currentTarget).addClass("ui-state-focus");
},
focusout:function(e){
t(e.currentTarget).removeClass("ui-state-focus");
}
});
},
_trigger:function(e,i,s){
var o,n,r=this.options[e];
if(s=s||{},i=t.Event(i),i.type=(e===this.widgetEventPrefix?e:this.widgetEventPrefix+e).toLowerCase(),
i.target=this.element[0],n=i.originalEvent)for(o in n)o in i||(i[o]=n[o]);
return this.element.trigger(i,s),!(t.isFunction(r)&&r.apply(this.element[0],[i].concat(s))===!1||i.isDefaultPrevented());
}
},t.each({
show:"fadeIn",
hide:"fadeOut"
},function(e,i){
t.Widget.prototype["_"+e]=function(s,o,n){
"string"==typeof o&&(o={
effect:o
});
var r,a=o?o===!0||"number"==typeof o?i:o.effect||i:e;
o=o||{},"number"==typeof o&&(o={
duration:o
}),r=!t.isEmptyObject(o),o.complete=n,o.delay&&s.delay(o.delay),r&&t.effects&&t.effects.effect[a]?s[e](o):a!==e&&s[a]?s[a](o.duration,o.easing,n):s.queue(function(i){
t(this)[e](),n&&n.call(s[0]),i();
});
};
});
}(jQuery),function(t){
var e=!1;
t(document).mouseup(function(){
e=!1;
}),t.widget("ui.mouse",{
version:"1.10.3",
options:{
cancel:"input,textarea,button,select,option",
distance:1,
delay:0
},
_mouseInit:function(){
var e=this;
this.element.bind("mousedown."+this.widgetName,function(t){
return e._mouseDown(t);
}).bind("click."+this.widgetName,function(i){
return!0===t.data(i.target,e.widgetName+".preventClickEvent")?(t.removeData(i.target,e.widgetName+".preventClickEvent"),
i.stopImmediatePropagation(),!1):void 0;
}),this.started=!1;
},
_mouseDestroy:function(){
this.element.unbind("."+this.widgetName),this._mouseMoveDelegate&&t(document).unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate);
},
_mouseDown:function(i){
if(!e){
this._mouseStarted&&this._mouseUp(i),this._mouseDownEvent=i;
var s=this,o=1===i.which,n="string"==typeof this.options.cancel&&i.target.nodeName?t(i.target).closest(this.options.cancel).length:!1;
return o&&!n&&this._mouseCapture(i)?(this.mouseDelayMet=!this.options.delay,this.mouseDelayMet||(this._mouseDelayTimer=setTimeout(function(){
s.mouseDelayMet=!0;
},this.options.delay)),this._mouseDistanceMet(i)&&this._mouseDelayMet(i)&&(this._mouseStarted=this._mouseStart(i)!==!1,
!this._mouseStarted)?(i.preventDefault(),!0):(!0===t.data(i.target,this.widgetName+".preventClickEvent")&&t.removeData(i.target,this.widgetName+".preventClickEvent"),
this._mouseMoveDelegate=function(t){
return s._mouseMove(t);
},this._mouseUpDelegate=function(t){
return s._mouseUp(t);
},t(document).bind("mousemove."+this.widgetName,this._mouseMoveDelegate).bind("mouseup."+this.widgetName,this._mouseUpDelegate),
i.preventDefault(),e=!0,!0)):!0;
}
},
_mouseMove:function(e){
return t.ui.ie&&(!document.documentMode||document.documentMode<9)&&!e.button?this._mouseUp(e):this._mouseStarted?(this._mouseDrag(e),
e.preventDefault()):(this._mouseDistanceMet(e)&&this._mouseDelayMet(e)&&(this._mouseStarted=this._mouseStart(this._mouseDownEvent,e)!==!1,
this._mouseStarted?this._mouseDrag(e):this._mouseUp(e)),!this._mouseStarted);
},
_mouseUp:function(e){
return t(document).unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate),
this._mouseStarted&&(this._mouseStarted=!1,e.target===this._mouseDownEvent.target&&t.data(e.target,this.widgetName+".preventClickEvent",!0),
this._mouseStop(e)),!1;
},
_mouseDistanceMet:function(t){
return Math.max(Math.abs(this._mouseDownEvent.pageX-t.pageX),Math.abs(this._mouseDownEvent.pageY-t.pageY))>=this.options.distance;
},
_mouseDelayMet:function(){
return this.mouseDelayMet;
},
_mouseStart:function(){},
_mouseDrag:function(){},
_mouseStop:function(){},
_mouseCapture:function(){
return!0;
}
});
}(jQuery),function(t){
t.widget("ui.draggable",t.ui.mouse,{
version:"1.10.3",
widgetEventPrefix:"drag",
options:{
addClasses:!0,
appendTo:"parent",
axis:!1,
connectToSortable:!1,
containment:!1,
cursor:"auto",
cursorAt:!1,
grid:!1,
handle:!1,
helper:"original",
iframeFix:!1,
opacity:!1,
refreshPositions:!1,
revert:!1,
revertDuration:500,
scope:"default",
scroll:!0,
scrollSensitivity:20,
scrollSpeed:20,
snap:!1,
snapMode:"both",
snapTolerance:20,
stack:!1,
zIndex:!1,
drag:null,
start:null,
stop:null
},
_create:function(){
"original"!==this.options.helper||/^(?:r|a|f)/.test(this.element.css("position"))||(this.element[0].style.position="relative"),
this.options.addClasses&&this.element.addClass("ui-draggable"),this.options.disabled&&this.element.addClass("ui-draggable-disabled"),
this._mouseInit();
},
_destroy:function(){
this.element.removeClass("ui-draggable ui-draggable-dragging ui-draggable-disabled"),
this._mouseDestroy();
},
_mouseCapture:function(e){
var i=this.options;
return this.helper||i.disabled||t(e.target).closest(".ui-resizable-handle").length>0?!1:(this.handle=this._getHandle(e),
this.handle?(t(i.iframeFix===!0?"iframe":i.iframeFix).each(function(){
t("<div class='ui-draggable-iframeFix' style='background: #fff;'></div>").css({
width:this.offsetWidth+"px",
height:this.offsetHeight+"px",
position:"absolute",
opacity:"0.001",
zIndex:1e3
}).css(t(this).offset()).appendTo("body");
}),!0):!1);
},
_mouseStart:function(e){
var i=this.options;
return this.helper=this._createHelper(e),this.helper.addClass("ui-draggable-dragging"),
this._cacheHelperProportions(),t.ui.ddmanager&&(t.ui.ddmanager.current=this),this._cacheMargins(),
this.cssPosition=this.helper.css("position"),this.scrollParent=this.helper.scrollParent(),
this.offsetParent=this.helper.offsetParent(),this.offsetParentCssPosition=this.offsetParent.css("position"),
this.offset=this.positionAbs=this.element.offset(),this.offset={
top:this.offset.top-this.margins.top,
left:this.offset.left-this.margins.left
},this.offset.scroll=!1,t.extend(this.offset,{
click:{
left:e.pageX-this.offset.left,
top:e.pageY-this.offset.top
},
parent:this._getParentOffset(),
relative:this._getRelativeOffset()
}),this.originalPosition=this.position=this._generatePosition(e),this.originalPageX=e.pageX,
this.originalPageY=e.pageY,i.cursorAt&&this._adjustOffsetFromHelper(i.cursorAt),
this._setContainment(),this._trigger("start",e)===!1?(this._clear(),!1):(this._cacheHelperProportions(),
t.ui.ddmanager&&!i.dropBehaviour&&t.ui.ddmanager.prepareOffsets(this,e),this._mouseDrag(e,!0),
t.ui.ddmanager&&t.ui.ddmanager.dragStart(this,e),!0);
},
_mouseDrag:function(e,i){
if("fixed"===this.offsetParentCssPosition&&(this.offset.parent=this._getParentOffset()),
this.position=this._generatePosition(e),this.positionAbs=this._convertPositionTo("absolute"),
!i){
var s=this._uiHash();
if(this._trigger("drag",e,s)===!1)return this._mouseUp({}),!1;
this.position=s.position;
}
return this.options.axis&&"y"===this.options.axis||(this.helper[0].style.left=this.position.left+"px"),
this.options.axis&&"x"===this.options.axis||(this.helper[0].style.top=this.position.top+"px"),
t.ui.ddmanager&&t.ui.ddmanager.drag(this,e),!1;
},
_mouseStop:function(e){
var i=this,s=!1;
return t.ui.ddmanager&&!this.options.dropBehaviour&&(s=t.ui.ddmanager.drop(this,e)),
this.dropped&&(s=this.dropped,this.dropped=!1),"original"!==this.options.helper||t.contains(this.element[0].ownerDocument,this.element[0])?("invalid"===this.options.revert&&!s||"valid"===this.options.revert&&s||this.options.revert===!0||t.isFunction(this.options.revert)&&this.options.revert.call(this.element,s)?t(this.helper).animate(this.originalPosition,parseInt(this.options.revertDuration,10),function(){
i._trigger("stop",e)!==!1&&i._clear();
}):this._trigger("stop",e)!==!1&&this._clear(),!1):!1;
},
_mouseUp:function(e){
return t("div.ui-draggable-iframeFix").each(function(){
this.parentNode.removeChild(this);
}),t.ui.ddmanager&&t.ui.ddmanager.dragStop(this,e),t.ui.mouse.prototype._mouseUp.call(this,e);
},
cancel:function(){
return this.helper.is(".ui-draggable-dragging")?this._mouseUp({}):this._clear(),
this;
},
_getHandle:function(e){
return this.options.handle?!!t(e.target).closest(this.element.find(this.options.handle)).length:!0;
},
_createHelper:function(e){
var i=this.options,s=t.isFunction(i.helper)?t(i.helper.apply(this.element[0],[e])):"clone"===i.helper?this.element.clone().removeAttr("id"):this.element;
return s.parents("body").length||s.appendTo("parent"===i.appendTo?this.element[0].parentNode:i.appendTo),
s[0]===this.element[0]||/(fixed|absolute)/.test(s.css("position"))||s.css("position","absolute"),
s;
},
_adjustOffsetFromHelper:function(e){
"string"==typeof e&&(e=e.split(" ")),t.isArray(e)&&(e={
left:+e[0],
top:+e[1]||0
}),"left"in e&&(this.offset.click.left=e.left+this.margins.left),"right"in e&&(this.offset.click.left=this.helperProportions.width-e.right+this.margins.left),
"top"in e&&(this.offset.click.top=e.top+this.margins.top),"bottom"in e&&(this.offset.click.top=this.helperProportions.height-e.bottom+this.margins.top);
},
_getParentOffset:function(){
var e=this.offsetParent.offset();
return"absolute"===this.cssPosition&&this.scrollParent[0]!==document&&t.contains(this.scrollParent[0],this.offsetParent[0])&&(e.left+=this.scrollParent.scrollLeft(),
e.top+=this.scrollParent.scrollTop()),(this.offsetParent[0]===document.body||this.offsetParent[0].tagName&&"html"===this.offsetParent[0].tagName.toLowerCase()&&t.ui.ie)&&(e={
top:0,
left:0
}),{
top:e.top+(parseInt(this.offsetParent.css("borderTopWidth"),10)||0),
left:e.left+(parseInt(this.offsetParent.css("borderLeftWidth"),10)||0)
};
},
_getRelativeOffset:function(){
if("relative"===this.cssPosition){
var t=this.element.position();
return{
top:t.top-(parseInt(this.helper.css("top"),10)||0)+this.scrollParent.scrollTop(),
left:t.left-(parseInt(this.helper.css("left"),10)||0)+this.scrollParent.scrollLeft()
};
}
return{
top:0,
left:0
};
},
_cacheMargins:function(){
this.margins={
left:parseInt(this.element.css("marginLeft"),10)||0,
top:parseInt(this.element.css("marginTop"),10)||0,
right:parseInt(this.element.css("marginRight"),10)||0,
bottom:parseInt(this.element.css("marginBottom"),10)||0
};
},
_cacheHelperProportions:function(){
this.helperProportions={
width:this.helper.outerWidth(),
height:this.helper.outerHeight()
};
},
_setContainment:function(){
var e,i,s,o=this.options;
return o.containment?"window"===o.containment?void(this.containment=[t(window).scrollLeft()-this.offset.relative.left-this.offset.parent.left,t(window).scrollTop()-this.offset.relative.top-this.offset.parent.top,t(window).scrollLeft()+t(window).width()-this.helperProportions.width-this.margins.left,t(window).scrollTop()+(t(window).height()||document.body.parentNode.scrollHeight)-this.helperProportions.height-this.margins.top]):"document"===o.containment?void(this.containment=[0,0,t(document).width()-this.helperProportions.width-this.margins.left,(t(document).height()||document.body.parentNode.scrollHeight)-this.helperProportions.height-this.margins.top]):o.containment.constructor===Array?void(this.containment=o.containment):("parent"===o.containment&&(o.containment=this.helper[0].parentNode),
i=t(o.containment),s=i[0],void(s&&(e="hidden"!==i.css("overflow"),this.containment=[(parseInt(i.css("borderLeftWidth"),10)||0)+(parseInt(i.css("paddingLeft"),10)||0),(parseInt(i.css("borderTopWidth"),10)||0)+(parseInt(i.css("paddingTop"),10)||0),(e?Math.max(s.scrollWidth,s.offsetWidth):s.offsetWidth)-(parseInt(i.css("borderRightWidth"),10)||0)-(parseInt(i.css("paddingRight"),10)||0)-this.helperProportions.width-this.margins.left-this.margins.right,(e?Math.max(s.scrollHeight,s.offsetHeight):s.offsetHeight)-(parseInt(i.css("borderBottomWidth"),10)||0)-(parseInt(i.css("paddingBottom"),10)||0)-this.helperProportions.height-this.margins.top-this.margins.bottom],
this.relative_container=i))):void(this.containment=null);
},
_convertPositionTo:function(e,i){
i||(i=this.position);
var s="absolute"===e?1:-1,o="absolute"!==this.cssPosition||this.scrollParent[0]!==document&&t.contains(this.scrollParent[0],this.offsetParent[0])?this.scrollParent:this.offsetParent;
return this.offset.scroll||(this.offset.scroll={
top:o.scrollTop(),
left:o.scrollLeft()
}),{
top:i.top+this.offset.relative.top*s+this.offset.parent.top*s-("fixed"===this.cssPosition?-this.scrollParent.scrollTop():this.offset.scroll.top)*s,
left:i.left+this.offset.relative.left*s+this.offset.parent.left*s-("fixed"===this.cssPosition?-this.scrollParent.scrollLeft():this.offset.scroll.left)*s
};
},
_generatePosition:function(e){
var i,s,o,n,r=this.options,a="absolute"!==this.cssPosition||this.scrollParent[0]!==document&&t.contains(this.scrollParent[0],this.offsetParent[0])?this.scrollParent:this.offsetParent,l=e.pageX,h=e.pageY;
return this.offset.scroll||(this.offset.scroll={
top:a.scrollTop(),
left:a.scrollLeft()
}),this.originalPosition&&(this.containment&&(this.relative_container?(s=this.relative_container.offset(),
i=[this.containment[0]+s.left,this.containment[1]+s.top,this.containment[2]+s.left,this.containment[3]+s.top]):i=this.containment,
e.pageX-this.offset.click.left<i[0]&&(l=i[0]+this.offset.click.left),e.pageY-this.offset.click.top<i[1]&&(h=i[1]+this.offset.click.top),
e.pageX-this.offset.click.left>i[2]&&(l=i[2]+this.offset.click.left),e.pageY-this.offset.click.top>i[3]&&(h=i[3]+this.offset.click.top)),
r.grid&&(o=r.grid[1]?this.originalPageY+Math.round((h-this.originalPageY)/r.grid[1])*r.grid[1]:this.originalPageY,
h=i?o-this.offset.click.top>=i[1]||o-this.offset.click.top>i[3]?o:o-this.offset.click.top>=i[1]?o-r.grid[1]:o+r.grid[1]:o,
n=r.grid[0]?this.originalPageX+Math.round((l-this.originalPageX)/r.grid[0])*r.grid[0]:this.originalPageX,
l=i?n-this.offset.click.left>=i[0]||n-this.offset.click.left>i[2]?n:n-this.offset.click.left>=i[0]?n-r.grid[0]:n+r.grid[0]:n)),
{
top:h-this.offset.click.top-this.offset.relative.top-this.offset.parent.top+("fixed"===this.cssPosition?-this.scrollParent.scrollTop():this.offset.scroll.top),
left:l-this.offset.click.left-this.offset.relative.left-this.offset.parent.left+("fixed"===this.cssPosition?-this.scrollParent.scrollLeft():this.offset.scroll.left)
};
},
_clear:function(){
this.helper.removeClass("ui-draggable-dragging"),this.helper[0]===this.element[0]||this.cancelHelperRemoval||this.helper.remove(),
this.helper=null,this.cancelHelperRemoval=!1;
},
_trigger:function(e,i,s){
return s=s||this._uiHash(),t.ui.plugin.call(this,e,[i,s]),"drag"===e&&(this.positionAbs=this._convertPositionTo("absolute")),
t.Widget.prototype._trigger.call(this,e,i,s);
},
plugins:{},
_uiHash:function(){
return{
helper:this.helper,
position:this.position,
originalPosition:this.originalPosition,
offset:this.positionAbs
};
}
}),t.ui.plugin.add("draggable","connectToSortable",{
start:function(e,i){
var s=t(this).data("ui-draggable"),o=s.options,n=t.extend({},i,{
item:s.element
});
s.sortables=[],t(o.connectToSortable).each(function(){
var i=t.data(this,"ui-sortable");
i&&!i.options.disabled&&(s.sortables.push({
instance:i,
shouldRevert:i.options.revert
}),i.refreshPositions(),i._trigger("activate",e,n));
});
},
stop:function(e,i){
var s=t(this).data("ui-draggable"),o=t.extend({},i,{
item:s.element
});
t.each(s.sortables,function(){
this.instance.isOver?(this.instance.isOver=0,s.cancelHelperRemoval=!0,this.instance.cancelHelperRemoval=!1,
this.shouldRevert&&(this.instance.options.revert=this.shouldRevert),this.instance._mouseStop(e),
this.instance.options.helper=this.instance.options._helper,"original"===s.options.helper&&this.instance.currentItem.css({
top:"auto",
left:"auto"
})):(this.instance.cancelHelperRemoval=!1,this.instance._trigger("deactivate",e,o));
});
},
drag:function(e,i){
var s=t(this).data("ui-draggable"),o=this;
t.each(s.sortables,function(){
var n=!1,r=this;
this.instance.positionAbs=s.positionAbs,this.instance.helperProportions=s.helperProportions,
this.instance.offset.click=s.offset.click,this.instance._intersectsWith(this.instance.containerCache)&&(n=!0,
t.each(s.sortables,function(){
return this.instance.positionAbs=s.positionAbs,this.instance.helperProportions=s.helperProportions,
this.instance.offset.click=s.offset.click,this!==r&&this.instance._intersectsWith(this.instance.containerCache)&&t.contains(r.instance.element[0],this.instance.element[0])&&(n=!1),
n;
})),n?(this.instance.isOver||(this.instance.isOver=1,this.instance.currentItem=t(o).clone().removeAttr("id").appendTo(this.instance.element).data("ui-sortable-item",!0),
this.instance.options._helper=this.instance.options.helper,this.instance.options.helper=function(){
return i.helper[0];
},e.target=this.instance.currentItem[0],this.instance._mouseCapture(e,!0),this.instance._mouseStart(e,!0,!0),
this.instance.offset.click.top=s.offset.click.top,this.instance.offset.click.left=s.offset.click.left,
this.instance.offset.parent.left-=s.offset.parent.left-this.instance.offset.parent.left,
this.instance.offset.parent.top-=s.offset.parent.top-this.instance.offset.parent.top,
s._trigger("toSortable",e),s.dropped=this.instance.element,s.currentItem=s.element,
this.instance.fromOutside=s),this.instance.currentItem&&this.instance._mouseDrag(e)):this.instance.isOver&&(this.instance.isOver=0,
this.instance.cancelHelperRemoval=!0,this.instance.options.revert=!1,this.instance._trigger("out",e,this.instance._uiHash(this.instance)),
this.instance._mouseStop(e,!0),this.instance.options.helper=this.instance.options._helper,
this.instance.currentItem.remove(),this.instance.placeholder&&this.instance.placeholder.remove(),
s._trigger("fromSortable",e),s.dropped=!1);
});
}
}),t.ui.plugin.add("draggable","cursor",{
start:function(){
var e=t("body"),i=t(this).data("ui-draggable").options;
e.css("cursor")&&(i._cursor=e.css("cursor")),e.css("cursor",i.cursor);
},
stop:function(){
var e=t(this).data("ui-draggable").options;
e._cursor&&t("body").css("cursor",e._cursor);
}
}),t.ui.plugin.add("draggable","opacity",{
start:function(e,i){
var s=t(i.helper),o=t(this).data("ui-draggable").options;
s.css("opacity")&&(o._opacity=s.css("opacity")),s.css("opacity",o.opacity);
},
stop:function(e,i){
var s=t(this).data("ui-draggable").options;
s._opacity&&t(i.helper).css("opacity",s._opacity);
}
}),t.ui.plugin.add("draggable","scroll",{
start:function(){
var e=t(this).data("ui-draggable");
e.scrollParent[0]!==document&&"HTML"!==e.scrollParent[0].tagName&&(e.overflowOffset=e.scrollParent.offset());
},
drag:function(e){
var i=t(this).data("ui-draggable"),s=i.options,o=!1;
i.scrollParent[0]!==document&&"HTML"!==i.scrollParent[0].tagName?(s.axis&&"x"===s.axis||(i.overflowOffset.top+i.scrollParent[0].offsetHeight-e.pageY<s.scrollSensitivity?i.scrollParent[0].scrollTop=o=i.scrollParent[0].scrollTop+s.scrollSpeed:e.pageY-i.overflowOffset.top<s.scrollSensitivity&&(i.scrollParent[0].scrollTop=o=i.scrollParent[0].scrollTop-s.scrollSpeed)),
s.axis&&"y"===s.axis||(i.overflowOffset.left+i.scrollParent[0].offsetWidth-e.pageX<s.scrollSensitivity?i.scrollParent[0].scrollLeft=o=i.scrollParent[0].scrollLeft+s.scrollSpeed:e.pageX-i.overflowOffset.left<s.scrollSensitivity&&(i.scrollParent[0].scrollLeft=o=i.scrollParent[0].scrollLeft-s.scrollSpeed))):(s.axis&&"x"===s.axis||(e.pageY-t(document).scrollTop()<s.scrollSensitivity?o=t(document).scrollTop(t(document).scrollTop()-s.scrollSpeed):t(window).height()-(e.pageY-t(document).scrollTop())<s.scrollSensitivity&&(o=t(document).scrollTop(t(document).scrollTop()+s.scrollSpeed))),
s.axis&&"y"===s.axis||(e.pageX-t(document).scrollLeft()<s.scrollSensitivity?o=t(document).scrollLeft(t(document).scrollLeft()-s.scrollSpeed):t(window).width()-(e.pageX-t(document).scrollLeft())<s.scrollSensitivity&&(o=t(document).scrollLeft(t(document).scrollLeft()+s.scrollSpeed)))),
o!==!1&&t.ui.ddmanager&&!s.dropBehaviour&&t.ui.ddmanager.prepareOffsets(i,e);
}
}),t.ui.plugin.add("draggable","snap",{
start:function(){
var e=t(this).data("ui-draggable"),i=e.options;
e.snapElements=[],t(i.snap.constructor!==String?i.snap.items||":data(ui-draggable)":i.snap).each(function(){
var i=t(this),s=i.offset();
this!==e.element[0]&&e.snapElements.push({
item:this,
width:i.outerWidth(),
height:i.outerHeight(),
top:s.top,
left:s.left
});
});
},
drag:function(e,i){
var s,o,n,r,a,l,h,c,p,u,f=t(this).data("ui-draggable"),d=f.options,g=d.snapTolerance,m=i.offset.left,v=m+f.helperProportions.width,_=i.offset.top,b=_+f.helperProportions.height;
for(p=f.snapElements.length-1;p>=0;p--)a=f.snapElements[p].left,l=a+f.snapElements[p].width,
h=f.snapElements[p].top,c=h+f.snapElements[p].height,a-g>v||m>l+g||h-g>b||_>c+g||!t.contains(f.snapElements[p].item.ownerDocument,f.snapElements[p].item)?(f.snapElements[p].snapping&&f.options.snap.release&&f.options.snap.release.call(f.element,e,t.extend(f._uiHash(),{
snapItem:f.snapElements[p].item
})),f.snapElements[p].snapping=!1):("inner"!==d.snapMode&&(s=Math.abs(h-b)<=g,o=Math.abs(c-_)<=g,
n=Math.abs(a-v)<=g,r=Math.abs(l-m)<=g,s&&(i.position.top=f._convertPositionTo("relative",{
top:h-f.helperProportions.height,
left:0
}).top-f.margins.top),o&&(i.position.top=f._convertPositionTo("relative",{
top:c,
left:0
}).top-f.margins.top),n&&(i.position.left=f._convertPositionTo("relative",{
top:0,
left:a-f.helperProportions.width
}).left-f.margins.left),r&&(i.position.left=f._convertPositionTo("relative",{
top:0,
left:l
}).left-f.margins.left)),u=s||o||n||r,"outer"!==d.snapMode&&(s=Math.abs(h-_)<=g,
o=Math.abs(c-b)<=g,n=Math.abs(a-m)<=g,r=Math.abs(l-v)<=g,s&&(i.position.top=f._convertPositionTo("relative",{
top:h,
left:0
}).top-f.margins.top),o&&(i.position.top=f._convertPositionTo("relative",{
top:c-f.helperProportions.height,
left:0
}).top-f.margins.top),n&&(i.position.left=f._convertPositionTo("relative",{
top:0,
left:a
}).left-f.margins.left),r&&(i.position.left=f._convertPositionTo("relative",{
top:0,
left:l-f.helperProportions.width
}).left-f.margins.left)),!f.snapElements[p].snapping&&(s||o||n||r||u)&&f.options.snap.snap&&f.options.snap.snap.call(f.element,e,t.extend(f._uiHash(),{
snapItem:f.snapElements[p].item
})),f.snapElements[p].snapping=s||o||n||r||u);
}
}),t.ui.plugin.add("draggable","stack",{
start:function(){
var e,i=this.data("ui-draggable").options,s=t.makeArray(t(i.stack)).sort(function(e,i){
return(parseInt(t(e).css("zIndex"),10)||0)-(parseInt(t(i).css("zIndex"),10)||0);
});
s.length&&(e=parseInt(t(s[0]).css("zIndex"),10)||0,t(s).each(function(i){
t(this).css("zIndex",e+i);
}),this.css("zIndex",e+s.length));
}
}),t.ui.plugin.add("draggable","zIndex",{
start:function(e,i){
var s=t(i.helper),o=t(this).data("ui-draggable").options;
s.css("zIndex")&&(o._zIndex=s.css("zIndex")),s.css("zIndex",o.zIndex);
},
stop:function(e,i){
var s=t(this).data("ui-draggable").options;
s._zIndex&&t(i.helper).css("zIndex",s._zIndex);
}
});
}(jQuery);
});define("tpl/popover.html.js",[],function(){
return'<div class="popover {className}">\n    <div class="popover_inner">\n        <div class="popover_content jsPopOverContent">{=content}</div>\n		<!--#0001#-->\n        {if close}<a href="javascript:;" class="popover_close icon16_common close_flat jsPopoverClose">关闭</a>{/if}\n        <!--%0001%-->\n\n        <div class="popover_bar">{each buttons as bt index}<a href="javascript:;" class="btn btn_{bt.type} jsPopoverBt">{bt.text}</a>{if index < buttons.length-1}&nbsp;{/if}{/each}</div>\n    </div>\n    <i class="popover_arrow popover_arrow_out"></i>\n    <i class="popover_arrow popover_arrow_in"></i> \n</div>\n';
});define("biz_common/jquery.validate.js",[],function(){
!function(t){
t.extend(t.fn,{
validate:function(e){
if(!this.length)return void(e&&e.debug&&window.console&&console.warn("Nothing selected, can't validate, returning nothing."));
var i=t.data(this[0],"validator");
return i?i:(this.attr("novalidate","novalidate"),i=new t.validator(e,this[0]),t.data(this[0],"validator",i),
i.settings.onsubmit&&(this.validateDelegate(":submit","click",function(e){
i.settings.submitHandler&&(i.submitButton=e.target),t(e.target).hasClass("cancel")&&(i.cancelSubmit=!0),
void 0!==t(e.target).attr("formnovalidate")&&(i.cancelSubmit=!0);
}),this.submit(function(e){
function r(){
var r;
return i.settings.submitHandler?(i.submitButton&&(r=t("<input type='hidden'/>").attr("name",i.submitButton.name).val(t(i.submitButton).val()).appendTo(i.currentForm)),
i.settings.submitHandler.call(i,i.currentForm,e),i.submitButton&&r.remove(),!1):!0;
}
return i.settings.debug&&e.preventDefault(),i.cancelSubmit?(i.cancelSubmit=!1,r()):i.form()?i.pendingRequest?(i.formSubmitted=!0,
!1):r():(i.focusInvalid(),!1);
})),i);
},
valid:function(){
if(t(this[0]).is("form"))return this.validate().form();
var e=!0,i=t(this[0].form).validate();
return this.each(function(){
e=e&&i.element(this);
}),e;
},
removeAttrs:function(e){
var i={},r=this;
return t.each(e.split(/\s/),function(t,e){
i[e]=r.attr(e),r.removeAttr(e);
}),i;
},
rules:function(e,i){
var r=this[0];
if(e){
var n=t.data(r.form,"validator").settings,s=n.rules,a=t.validator.staticRules(r);
switch(e){
case"add":
t.extend(a,t.validator.normalizeRule(i)),delete a.messages,s[r.name]=a,i.messages&&(n.messages[r.name]=t.extend(n.messages[r.name],i.messages));
break;

case"remove":
if(!i)return delete s[r.name],a;
var o={};
return t.each(i.split(/\s/),function(t,e){
o[e]=a[e],delete a[e];
}),o;
}
}
var u=t.validator.normalizeRules(t.extend({},t.validator.classRules(r),t.validator.attributeRules(r),t.validator.dataRules(r),t.validator.staticRules(r)),r);
if(u.required){
var l=u.required;
delete u.required,u=t.extend({
required:l
},u);
}
return u;
}
}),t.extend(t.expr[":"],{
blank:function(e){
return!t.trim(""+t(e).val());
},
filled:function(e){
return!!t.trim(""+t(e).val());
},
unchecked:function(e){
return!t(e).prop("checked");
}
}),t.validator=function(e,i){
this.settings=t.extend(!0,{},t.validator.defaults,e),this.currentForm=i,this.init();
},t.validator.format=function(e,i){
return 1===arguments.length?function(){
var i=t.makeArray(arguments);
return i.unshift(e),t.validator.format.apply(this,i);
}:(arguments.length>2&&i.constructor!==Array&&(i=t.makeArray(arguments).slice(1)),
i.constructor!==Array&&(i=[i]),t.each(i,function(t,i){
e=e.replace(new RegExp("\\{"+t+"\\}","g"),function(){
return i;
});
}),e);
},t.extend(t.validator,{
defaults:{
messages:{},
groups:{},
rules:{},
errorClass:"error",
validClass:"valid",
errorElement:"label",
focusInvalid:!0,
errorContainer:t([]),
errorLabelContainer:t([]),
onsubmit:!0,
ignore:":hidden",
ignoreTitle:!1,
onfocusin:function(t){
this.lastActive=t,this.settings.focusCleanup&&!this.blockFocusCleanup&&(this.settings.unhighlight&&this.settings.unhighlight.call(this,t,this.settings.errorClass,this.settings.validClass),
this.addWrapper(this.errorsFor(t)).hide());
},
onfocusout:function(t){
this.checkable(t)||this.element(t);
},
onkeyup:function(t,e){
(9!==e.which||""!==this.elementValue(t))&&(t.name in this.submitted||t===this.lastElement)&&this.element(t);
},
onclick:function(t){
t.name in this.submitted?this.element(t):t.parentNode.name in this.submitted&&this.element(t.parentNode);
},
highlight:function(e,i,r){
"radio"===e.type?this.findByName(e.name).addClass(i).removeClass(r):t(e).addClass(i).removeClass(r);
},
unhighlight:function(e,i,r){
"radio"===e.type?this.findByName(e.name).removeClass(i).addClass(r):t(e).removeClass(i).addClass(r);
}
},
setDefaults:function(e){
t.extend(t.validator.defaults,e);
},
messages:{
required:"This field is required.",
remote:"Please fix this field.",
email:"Please enter a valid email address.",
url:"Please enter a valid URL.",
date:"Please enter a valid date.",
dateISO:"Please enter a valid date (ISO).",
number:"Please enter a valid number.",
digits:"Please enter only digits.",
creditcard:"Please enter a valid credit card number.",
equalTo:"Please enter the same value again.",
maxlength:t.validator.format("Please enter no more than {0} characters."),
minlength:t.validator.format("Please enter at least {0} characters."),
rangelength:t.validator.format("Please enter a value between {0} and {1} characters long."),
range:t.validator.format("Please enter a value between {0} and {1}."),
max:t.validator.format("Please enter a value less than or equal to {0}."),
min:t.validator.format("Please enter a value greater than or equal to {0}.")
},
autoCreateRanges:!1,
prototype:{
init:function(){
function e(e){
var i=t.data(this[0].form,"validator"),r="on"+e.type.replace(/^validate/,"");
i.settings[r]&&i.settings[r].call(i,this[0],e);
}
this.labelContainer=t(this.settings.errorLabelContainer),this.errorContext=this.labelContainer.length&&this.labelContainer||t(this.currentForm),
this.containers=t(this.settings.errorContainer).add(this.settings.errorLabelContainer),
this.submitted={},this.valueCache={},this.pendingRequest=0,this.pending={},this.invalid={},
this.reset();
var i=this.groups={};
t.each(this.settings.groups,function(e,r){
"string"==typeof r&&(r=r.split(/\s/)),t.each(r,function(t,r){
i[r]=e;
});
});
var r=this.settings.rules;
t.each(r,function(e,i){
r[e]=t.validator.normalizeRule(i);
}),t(this.currentForm).validateDelegate(":text, [type='password'], [type='file'], select, textarea, [type='number'], [type='search'] ,[type='tel'], [type='url'], [type='email'], [type='datetime'], [type='date'], [type='month'], [type='week'], [type='time'], [type='datetime-local'], [type='range'], [type='color'] ","focusin focusout keyup",e).validateDelegate("[type='radio'], [type='checkbox'], select, option","click",e),
this.settings.invalidHandler&&t(this.currentForm).bind("invalid-form.validate",this.settings.invalidHandler);
},
form:function(){
return this.checkForm(),t.extend(this.submitted,this.errorMap),this.invalid=t.extend({},this.errorMap),
this.valid()||t(this.currentForm).triggerHandler("invalid-form",[this]),this.showErrors(),
this.valid();
},
checkForm:function(){
this.prepareForm();
for(var t=0,e=this.currentElements=this.elements();e[t];t++)this.check(e[t]);
return this.valid();
},
element:function(e){
e=this.validationTargetFor(this.clean(e)),this.lastElement=e,this.prepareElement(e),
this.currentElements=t(e);
var i=this.check(e)!==!1;
return i?delete this.invalid[e.name]:this.invalid[e.name]=!0,this.numberOfInvalids()||(this.toHide=this.toHide.add(this.containers)),
this.showErrors(),i;
},
showErrors:function(e){
if(e){
t.extend(this.errorMap,e),this.errorList=[];
for(var i in e)this.errorList.push({
message:e[i],
element:this.findByName(i)[0]
});
this.successList=t.grep(this.successList,function(t){
return!(t.name in e);
});
}
this.settings.showErrors?this.settings.showErrors.call(this,this.errorMap,this.errorList):this.defaultShowErrors();
},
resetForm:function(){
t.fn.resetForm&&t(this.currentForm).resetForm(),this.submitted={},this.lastElement=null,
this.prepareForm(),this.hideErrors(),this.elements().removeClass(this.settings.errorClass).removeData("previousValue");
},
numberOfInvalids:function(){
return this.objectLength(this.invalid);
},
objectLength:function(t){
var e=0;
for(var i in t)e++;
return e;
},
hideErrors:function(){
this.addWrapper(this.toHide).hide();
},
valid:function(){
return 0===this.size();
},
size:function(){
return this.errorList.length;
},
focusInvalid:function(){
if(this.settings.focusInvalid)try{
t(this.findLastActive()||this.errorList.length&&this.errorList[0].element||[]).filter(":visible").focus().trigger("focusin");
}catch(e){}
},
findLastActive:function(){
var e=this.lastActive;
return e&&1===t.grep(this.errorList,function(t){
return t.element.name===e.name;
}).length&&e;
},
elements:function(){
var e=this,i={};
return t(this.currentForm).find("input, select, textarea").not(":submit, :reset, :image, [disabled]").not(this.settings.ignore).filter(function(){
return!this.name&&e.settings.debug&&window.console&&console.error("%o has no name assigned",this),
this.name in i||!e.objectLength(t(this).rules())?!1:(i[this.name]=!0,!0);
});
},
clean:function(e){
return t(e)[0];
},
errors:function(){
var e=this.settings.errorClass.replace(" ",".");
return t(this.settings.errorElement+"."+e,this.errorContext);
},
reset:function(){
this.successList=[],this.errorList=[],this.errorMap={},this.toShow=t([]),this.toHide=t([]),
this.currentElements=t([]);
},
prepareForm:function(){
this.reset(),this.toHide=this.errors().add(this.containers);
},
prepareElement:function(t){
this.reset(),this.toHide=this.errorsFor(t);
},
elementValue:function(e){
var i=t(e).attr("type"),r=t(e).val();
return"radio"===i||"checkbox"===i?t("input[name='"+t(e).attr("name")+"']:checked").val():"string"==typeof r?r.replace(/\r/g,""):r;
},
check:function(e){
e=this.validationTargetFor(this.clean(e));
var i,r=t(e).rules(),n=!1,s=this.elementValue(e);
for(var a in r){
var o={
method:a,
parameters:r[a]
};
try{
if(i=t.validator.methods[a].call(this,s,e,o.parameters),"dependency-mismatch"===i){
n=!0;
continue;
}
if(n=!1,"pending"===i)return void(this.toHide=this.toHide.not(this.errorsFor(e)));
if(!i)return this.formatAndAdd(e,o),!1;
}catch(u){
throw this.settings.debug&&window.console&&console.log("Exception occurred when checking element "+e.id+", check the '"+o.method+"' method.",u),
u;
}
}
return n?void 0:(this.objectLength(r)&&this.successList.push(e),!0);
},
customDataMessage:function(e,i){
return t(e).data("msg-"+i.toLowerCase())||e.attributes&&t(e).attr("data-msg-"+i.toLowerCase());
},
customMessage:function(t,e){
var i=this.settings.messages[t];
return i&&(i.constructor===String?i:i[e]);
},
findDefined:function(){
for(var t=0;t<arguments.length;t++)if(void 0!==arguments[t])return arguments[t];
return void 0;
},
defaultMessage:function(e,i){
return this.findDefined(this.customMessage(e.name,i),this.customDataMessage(e,i),!this.settings.ignoreTitle&&e.title||void 0,t.validator.messages[i],"<strong>Warning: No message defined for "+e.name+"</strong>");
},
formatAndAdd:function(e,i){
var r=this.defaultMessage(e,i.method),n=/\$?\{(\d+)\}/g;
"function"==typeof r?r=r.call(this,i.parameters,e):n.test(r)&&(r=t.validator.format(r.replace(n,"{$1}"),i.parameters)),
this.errorList.push({
message:r,
element:e
}),this.errorMap[e.name]=r,this.submitted[e.name]=r;
},
addWrapper:function(t){
return this.settings.wrapper&&(t=t.add(t.parent(this.settings.wrapper))),t;
},
defaultShowErrors:function(){
var t,e;
for(t=0;this.errorList[t];t++){
var i=this.errorList[t];
this.settings.highlight&&this.settings.highlight.call(this,i.element,this.settings.errorClass,this.settings.validClass),
this.showLabel(i.element,i.message);
}
if(this.errorList.length&&(this.toShow=this.toShow.add(this.containers)),this.settings.success)for(t=0;this.successList[t];t++)this.showLabel(this.successList[t]);
if(this.settings.unhighlight)for(t=0,e=this.validElements();e[t];t++)this.settings.unhighlight.call(this,e[t],this.settings.errorClass,this.settings.validClass);
this.toHide=this.toHide.not(this.toShow),this.hideErrors(),this.addWrapper(this.toShow).show();
},
validElements:function(){
return this.currentElements.not(this.invalidElements());
},
invalidElements:function(){
return t(this.errorList).map(function(){
return this.element;
});
},
showLabel:function(e,i){
var r=this.errorsFor(e);
r.length?(r.removeClass(this.settings.validClass).addClass(this.settings.errorClass),
r.html(i)):(r=t("<"+this.settings.errorElement+">").attr("for",this.idOrName(e)).addClass(this.settings.errorClass).html(i||""),
this.settings.wrapper&&(r=r.hide().show().wrap("<"+this.settings.wrapper+" class='frm_msg fail'/>").parent()),
this.labelContainer.append(r).length||(this.settings.errorPlacement?this.settings.errorPlacement(r,t(e)):r.insertAfter(e))),
!i&&this.settings.success&&(r.text(""),"string"==typeof this.settings.success?r.addClass(this.settings.success):this.settings.success(r,e)),
this.toShow=this.toShow.add(r);
},
errorsFor:function(e){
var i=this.idOrName(e);
return this.errors().filter(function(){
return t(this).attr("for")===i;
});
},
idOrName:function(t){
return this.groups[t.name]||(this.checkable(t)?t.name:t.id||t.name);
},
validationTargetFor:function(t){
return this.checkable(t)&&(t=this.findByName(t.name).not(this.settings.ignore)[0]),
t;
},
checkable:function(t){
return/radio|checkbox/i.test(t.type);
},
findByName:function(e){
return t(this.currentForm).find("[name='"+e+"']");
},
getLength:function(e,i){
switch(i.nodeName.toLowerCase()){
case"select":
return t("option:selected",i).length;

case"input":
if(this.checkable(i))return this.findByName(i.name).filter(":checked").length;
}
return e.length;
},
depend:function(t,e){
return this.dependTypes[typeof t]?this.dependTypes[typeof t](t,e):!0;
},
dependTypes:{
"boolean":function(t){
return t;
},
string:function(e,i){
return!!t(e,i.form).length;
},
"function":function(t,e){
return t(e);
}
},
optional:function(e){
var i=this.elementValue(e);
return!t.validator.methods.required.call(this,i,e)&&"dependency-mismatch";
},
startRequest:function(t){
this.pending[t.name]||(this.pendingRequest++,this.pending[t.name]=!0);
},
stopRequest:function(e,i){
this.pendingRequest--,this.pendingRequest<0&&(this.pendingRequest=0),delete this.pending[e.name],
i&&0===this.pendingRequest&&this.formSubmitted&&this.form()?(t(this.currentForm).submit(),
this.formSubmitted=!1):!i&&0===this.pendingRequest&&this.formSubmitted&&(t(this.currentForm).triggerHandler("invalid-form",[this]),
this.formSubmitted=!1);
},
previousValue:function(e){
return t.data(e,"previousValue")||t.data(e,"previousValue",{
old:null,
valid:!0,
message:this.defaultMessage(e,"remote")
});
}
},
classRuleSettings:{
required:{
required:!0
},
email:{
email:!0
},
url:{
url:!0
},
date:{
date:!0
},
dateISO:{
dateISO:!0
},
number:{
number:!0
},
digits:{
digits:!0
},
creditcard:{
creditcard:!0
}
},
addClassRules:function(e,i){
e.constructor===String?this.classRuleSettings[e]=i:t.extend(this.classRuleSettings,e);
},
classRules:function(e){
var i={},r=t(e).attr("class");
return r&&t.each(r.split(" "),function(){
this in t.validator.classRuleSettings&&t.extend(i,t.validator.classRuleSettings[this]);
}),i;
},
attributeRules:function(e){
var i={},r=t(e),n=r[0].getAttribute("type");
for(var s in t.validator.methods){
var a;
"required"===s?(a=r.get(0).getAttribute(s),""===a&&(a=!0),a=!!a):a=r.attr(s),/min|max/.test(s)&&(null===n||/number|range|text/.test(n))&&(a=Number(a)),
a?i[s]=a:n===s&&"range"!==n&&(i[s]=!0);
}
return i.maxlength&&/-1|2147483647|524288/.test(i.maxlength)&&delete i.maxlength,
i;
},
dataRules:function(e){
var i,r,n={},s=t(e);
for(i in t.validator.methods)r=s.data("rule-"+i.toLowerCase()),void 0!==r&&(n[i]=r);
return n;
},
staticRules:function(e){
var i={},r=t.data(e.form,"validator");
return r.settings.rules&&(i=t.validator.normalizeRule(r.settings.rules[e.name])||{}),
i;
},
normalizeRules:function(e,i){
return t.each(e,function(r,n){
if(n===!1)return void delete e[r];
if(n.param||n.depends){
var s=!0;
switch(typeof n.depends){
case"string":
s=!!t(n.depends,i.form).length;
break;

case"function":
s=n.depends.call(i,i);
}
s?"string"!=typeof n&&(e[r]=void 0!==n.param?n.param:!0):delete e[r];
}
}),t.each(e,function(r,n){
e[r]=t.isFunction(n)?n(i):n;
}),t.each(["minlength","maxlength"],function(){
e[this]&&(e[this]=Number(e[this]));
}),t.each(["rangelength","range"],function(){
var i;
e[this]&&(t.isArray(e[this])?e[this]=[Number(e[this][0]),Number(e[this][1])]:"string"==typeof e[this]&&(i=e[this].split(/[\s,]+/),
e[this]=[Number(i[0]),Number(i[1])]));
}),t.validator.autoCreateRanges&&(e.min&&e.max&&(e.range=[e.min,e.max],delete e.min,
delete e.max),e.minlength&&e.maxlength&&(e.rangelength=[e.minlength,e.maxlength],
delete e.minlength,delete e.maxlength)),e;
},
normalizeRule:function(e){
if("string"==typeof e){
var i={};
t.each(e.split(/\s/),function(){
i[this]=!0;
}),e=i;
}
return e;
},
addMethod:function(e,i,r){
t.validator.methods[e]=i,t.validator.messages[e]=void 0!==r?r:t.validator.messages[e],
i.length<3&&t.validator.addClassRules(e,t.validator.normalizeRule(e));
},
methods:{
required:function(e,i,r){
if(!this.depend(r,i))return"dependency-mismatch";
if("select"===i.nodeName.toLowerCase()){
var n=t(i).val();
return n&&n.length>0;
}
return this.checkable(i)?this.getLength(e,i)>0:t.trim(e).length>0;
},
email:function(t,e){
return this.optional(e)||/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(t);
},
url:function(t,e){
return this.optional(e)||/^(https?|s?ftp|weixin):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(t);
},
date:function(t,e){
return this.optional(e)||!/Invalid|NaN/.test(new Date(t).toString());
},
dateISO:function(t,e){
return this.optional(e)||/^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test(t);
},
number:function(t,e){
return this.optional(e)||/^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(t);
},
digits:function(t,e){
return this.optional(e)||/^\d+$/.test(t);
},
creditcard:function(t,e){
if(this.optional(e))return"dependency-mismatch";
if(/[^0-9 \-]+/.test(t))return!1;
var i=0,r=0,n=!1;
t=t.replace(/\D/g,"");
for(var s=t.length-1;s>=0;s--){
var a=t.charAt(s);
r=parseInt(a,10),n&&(r*=2)>9&&(r-=9),i+=r,n=!n;
}
return i%10===0;
},
minlength:function(e,i,r){
var n=t.isArray(e)?e.length:this.getLength(t.trim(e),i);
return this.optional(i)||n>=r;
},
maxlength:function(e,i,r){
var n=t.isArray(e)?e.length:this.getLength(t.trim(e),i);
return this.optional(i)||r>=n;
},
rangelength:function(e,i,r){
var n=t.isArray(e)?e.length:this.getLength(t.trim(e),i);
return this.optional(i)||n>=r[0]&&n<=r[1];
},
min:function(t,e,i){
return this.optional(e)||t>=i;
},
max:function(t,e,i){
return this.optional(e)||i>=t;
},
range:function(t,e,i){
return this.optional(e)||t>=i[0]&&t<=i[1];
},
equalTo:function(e,i,r){
var n=t(r);
return this.settings.onfocusout&&n.unbind(".validate-equalTo").bind("blur.validate-equalTo",function(){
t(i).valid();
}),e===n.val();
},
remote:function(e,i,r){
if(this.optional(i))return"dependency-mismatch";
var n=this.previousValue(i);
if(this.settings.messages[i.name]||(this.settings.messages[i.name]={}),n.originalMessage=this.settings.messages[i.name].remote,
this.settings.messages[i.name].remote=n.message,r="string"==typeof r&&{
url:r
}||r,n.old===e)return n.valid;
n.old=e;
var s=this;
this.startRequest(i);
var a={};
return a[i.name]=e,t.ajax(t.extend(!0,{
url:r,
mode:"abort",
port:"validate"+i.name,
dataType:"json",
data:a,
success:function(r){
s.settings.messages[i.name].remote=n.originalMessage;
var a=r===!0||"true"===r;
if(a){
var o=s.formSubmitted;
s.prepareElement(i),s.formSubmitted=o,s.successList.push(i),delete s.invalid[i.name],
s.showErrors();
}else{
var u={},l=r||s.defaultMessage(i,"remote");
u[i.name]=n.message=t.isFunction(l)?l(e):l,s.invalid[i.name]=!0,s.showErrors(u);
}
n.valid=a,s.stopRequest(i,a);
}
},r)),"pending";
}
}
}),t.format=t.validator.format;
}(jQuery),function(t){
var e={};
if(t.ajaxPrefilter)t.ajaxPrefilter(function(t,i,r){
var n=t.port;
"abort"===t.mode&&(e[n]&&e[n].abort(),e[n]=r);
});else{
var i=t.ajax;
t.ajax=function(r){
var n=("mode"in r?r:t.ajaxSettings).mode,s=("port"in r?r:t.ajaxSettings).port;
return"abort"===n?(e[s]&&e[s].abort(),e[s]=i.apply(this,arguments),e[s]):i.apply(this,arguments);
};
}
}(jQuery),function(t){
t.extend(t.fn,{
validateDelegate:function(e,i,r){
return this.bind(i,function(i){
var n=t(i.target);
return n.is(e)?r.apply(n,arguments):void 0;
});
}
});
}(jQuery),function(t){
t.validator.defaults.errorClass="frm_msg_content",t.validator.defaults.errorElement="span",
t.validator.defaults.errorPlacement=function(t,e){
e.parent().after(t);
},t.validator.defaults.wrapper="p",t.validator.messages={
required:"必选字段",
remote:"请修正该字段",
email:"请输入正确格式的电子邮件",
url:"请输入合法的网址",
date:"请输入合法的日期",
dateISO:"请输入合法的日期 (ISO).",
number:"请输入合法的数字",
digits:"只能输入整数",
creditcard:"请输入合法的信用卡号",
equalTo:"请再次输入相同的值",
accept:"请输入拥有合法后缀名的字符串",
maxlength:t.validator.format("请输入一个长度最多是 {0} 的字符串"),
minlength:t.validator.format("请输入一个长度最少是 {0} 的字符串"),
rangelength:t.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
range:t.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
max:t.validator.format("请输入一个最大为 {0} 的值"),
min:t.validator.format("请输入一个最小为 {0} 的值")
},function(){
function e(t){
var e,i=0;
"x"==t[17].toLowerCase()&&(t[17]=10);
for(var r=0;17>r;r++)i+=n[r]*t[r];
return e=i%11,t[17]==s[e]?!0:!1;
}
function i(t){
var e=t.substring(6,10),i=t.substring(10,12),r=t.substring(12,14),n=new Date(e,parseFloat(i)-1,parseFloat(r));
return(new Date).getFullYear()-parseInt(e)<18?!1:n.getFullYear()!=parseFloat(e)||n.getMonth()!=parseFloat(i)-1||n.getDate()!=parseFloat(r)?!1:!0;
}
function r(r){
if(r=t.trim(r.replace(/ /g,"")),15==r.length)return!1;
if(18==r.length){
var n=r.split("");
return i(r)&&e(n)?!0:!1;
}
return!1;
}
var n=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1],s=[1,0,10,9,8,7,6,5,4,3,2];
t.validator.addMethod("idcard",function(t){
return r(t);
},"身份证格式不正确，或者年龄未满18周岁，请重新填写"),t.validator.addMethod("mobile",function(e){
return e=t.trim(e),/^1\d{10}$/.test(e);
},"请输入正确的手机号码"),t.validator.addMethod("telephone",function(e){
return e=t.trim(e),/^\d{1,4}(-\d{1,12})+$/.test(e);
},"请输入正确的座机号码，如020-12345678"),t.validator.addMethod("verifycode",function(e){
return e=t.trim(e),/^\d{6}$/.test(e);
},"验证码应为6位数字"),t.validator.addMethod("byteRangeLength",function(t,e,i){
return this.optional(e)||t.len()<=i[1]&&t.len()>=i[0];
},"_(必须为{0}到{1}个字节之间)");
}();
}(jQuery);
var t={
optional:function(){
return!1;
},
getLength:function(t){
return t?t.length:0;
}
},e=$.validator;
return e.rules={},$.each(e.methods,function(i,r){
e.rules[i]=function(e,i){
return r.call(t,e,null,i);
};
}),e;
});define("tpl/simplePopup.html.js",[],function(){
return'<div class="simple_dialog_content">\n    <form id="popupForm_{id}"  method="post"  class="form" onSubmit="return false;">\n         <div class="frm_control_group">\n            {if label}<label class="frm_label">{label}</label>{/if}\n            <span class="frm_input_box">\n                <input type="text" class="frm_input" name="popInput" value="{value&&value.html(true)}"/>\n                <input style="display:none"/>\n            </span>\n            {if tips}<p class="frm_tips">{=tips}</p>{/if}\n        </div>       \n        <div class="js_verifycode"></div>\n    </form>\n</div>\n';
});define("tpl/tooltip.html.js", [], function(e, t, n) {
return '<div class="tooltip">\n    <div class="tooltip_inner">{content}</div>\n    <i class="tooltip_arrow"></i>\n</div>\n';
});define("biz_common/jquery.ui/jquery.ui.sortable.js",[],function(){
!function(t,e){
function i(e,i){
var n,o,r,h=e.nodeName.toLowerCase();
return"area"===h?(n=e.parentNode,o=n.name,e.href&&o&&"map"===n.nodeName.toLowerCase()?(r=t("img[usemap=#"+o+"]")[0],
!!r&&s(r)):!1):(/input|select|textarea|button|object/.test(h)?!e.disabled:"a"===h?e.href||i:i)&&s(e);
}
function s(e){
return t.expr.filters.visible(e)&&!t(e).parents().addBack().filter(function(){
return"hidden"===t.css(this,"visibility");
}).length;
}
var n=0,o=/^ui-id-\d+$/;
t.ui=t.ui||{},t.extend(t.ui,{
version:"1.10.3",
keyCode:{
BACKSPACE:8,
COMMA:188,
DELETE:46,
DOWN:40,
END:35,
ENTER:13,
ESCAPE:27,
HOME:36,
LEFT:37,
NUMPAD_ADD:107,
NUMPAD_DECIMAL:110,
NUMPAD_DIVIDE:111,
NUMPAD_ENTER:108,
NUMPAD_MULTIPLY:106,
NUMPAD_SUBTRACT:109,
PAGE_DOWN:34,
PAGE_UP:33,
PERIOD:190,
RIGHT:39,
SPACE:32,
TAB:9,
UP:38
}
}),t.fn.extend({
focus:function(e){
return function(i,s){
return"number"==typeof i?this.each(function(){
var e=this;
setTimeout(function(){
t(e).focus(),s&&s.call(e);
},i);
}):e.apply(this,arguments);
};
}(t.fn.focus),
scrollParent:function(){
var e;
return e=t.ui.ie&&/(static|relative)/.test(this.css("position"))||/absolute/.test(this.css("position"))?this.parents().filter(function(){
return/(relative|absolute|fixed)/.test(t.css(this,"position"))&&/(auto|scroll)/.test(t.css(this,"overflow")+t.css(this,"overflow-y")+t.css(this,"overflow-x"));
}).eq(0):this.parents().filter(function(){
return/(auto|scroll)/.test(t.css(this,"overflow")+t.css(this,"overflow-y")+t.css(this,"overflow-x"));
}).eq(0),/fixed/.test(this.css("position"))||!e.length?t(document):e;
},
zIndex:function(i){
if(i!==e)return this.css("zIndex",i);
if(this.length)for(var s,n,o=t(this[0]);o.length&&o[0]!==document;){
if(s=o.css("position"),("absolute"===s||"relative"===s||"fixed"===s)&&(n=parseInt(o.css("zIndex"),10),
!isNaN(n)&&0!==n))return n;
o=o.parent();
}
return 0;
},
uniqueId:function(){
return this.each(function(){
this.id||(this.id="ui-id-"+ ++n);
});
},
removeUniqueId:function(){
return this.each(function(){
o.test(this.id)&&t(this).removeAttr("id");
});
}
}),t.extend(t.expr[":"],{
data:t.expr.createPseudo?t.expr.createPseudo(function(e){
return function(i){
return!!t.data(i,e);
};
}):function(e,i,s){
return!!t.data(e,s[3]);
},
focusable:function(e){
return i(e,!isNaN(t.attr(e,"tabindex")));
},
tabbable:function(e){
var s=t.attr(e,"tabindex"),n=isNaN(s);
return(n||s>=0)&&i(e,!n);
}
}),t("<a>").outerWidth(1).jquery||t.each(["Width","Height"],function(i,s){
function n(e,i,s,n){
return t.each(o,function(){
i-=parseFloat(t.css(e,"padding"+this))||0,s&&(i-=parseFloat(t.css(e,"border"+this+"Width"))||0),
n&&(i-=parseFloat(t.css(e,"margin"+this))||0);
}),i;
}
var o="Width"===s?["Left","Right"]:["Top","Bottom"],r=s.toLowerCase(),h={
innerWidth:t.fn.innerWidth,
innerHeight:t.fn.innerHeight,
outerWidth:t.fn.outerWidth,
outerHeight:t.fn.outerHeight
};
t.fn["inner"+s]=function(i){
return i===e?h["inner"+s].call(this):this.each(function(){
t(this).css(r,n(this,i)+"px");
});
},t.fn["outer"+s]=function(e,i){
return"number"!=typeof e?h["outer"+s].call(this,e):this.each(function(){
t(this).css(r,n(this,e,!0,i)+"px");
});
};
}),t.fn.addBack||(t.fn.addBack=function(t){
return this.add(null==t?this.prevObject:this.prevObject.filter(t));
}),t("<a>").data("a-b","a").removeData("a-b").data("a-b")&&(t.fn.removeData=function(e){
return function(i){
return arguments.length?e.call(this,t.camelCase(i)):e.call(this);
};
}(t.fn.removeData)),t.ui.ie=!!/msie [\w.]+/.exec(navigator.userAgent.toLowerCase()),
t.support.selectstart="onselectstart"in document.createElement("div"),t.fn.extend({
disableSelection:function(){
return this.bind((t.support.selectstart?"selectstart":"mousedown")+".ui-disableSelection",function(t){
t.preventDefault();
});
},
enableSelection:function(){
return this.unbind(".ui-disableSelection");
}
}),t.extend(t.ui,{
plugin:{
add:function(e,i,s){
var n,o=t.ui[e].prototype;
for(n in s)o.plugins[n]=o.plugins[n]||[],o.plugins[n].push([i,s[n]]);
},
call:function(t,e,i){
var s,n=t.plugins[e];
if(n&&t.element[0].parentNode&&11!==t.element[0].parentNode.nodeType)for(s=0;s<n.length;s++)t.options[n[s][0]]&&n[s][1].apply(t.element,i);
}
},
hasScroll:function(e,i){
if("hidden"===t(e).css("overflow"))return!1;
var s=i&&"left"===i?"scrollLeft":"scrollTop",n=!1;
return e[s]>0?!0:(e[s]=1,n=e[s]>0,e[s]=0,n);
}
});
}(jQuery),function(t,e){
var i=0,s=Array.prototype.slice,n=t.cleanData;
t.cleanData=function(e){
for(var i,s=0;null!=(i=e[s]);s++)try{
t(i).triggerHandler("remove");
}catch(o){}
n(e);
},t.widget=function(e,i,s){
var n,o,r,h,a={},l=e.split(".")[0];
e=e.split(".")[1],n=l+"-"+e,s||(s=i,i=t.Widget),t.expr[":"][n.toLowerCase()]=function(e){
return!!t.data(e,n);
},t[l]=t[l]||{},o=t[l][e],r=t[l][e]=function(t,e){
return this._createWidget?void(arguments.length&&this._createWidget(t,e)):new r(t,e);
},t.extend(r,o,{
version:s.version,
_proto:t.extend({},s),
_childConstructors:[]
}),h=new i,h.options=t.widget.extend({},h.options),t.each(s,function(e,s){
return t.isFunction(s)?void(a[e]=function(){
var t=function(){
return i.prototype[e].apply(this,arguments);
},n=function(t){
return i.prototype[e].apply(this,t);
};
return function(){
var e,i=this._super,o=this._superApply;
return this._super=t,this._superApply=n,e=s.apply(this,arguments),this._super=i,
this._superApply=o,e;
};
}()):void(a[e]=s);
}),r.prototype=t.widget.extend(h,{
widgetEventPrefix:o?h.widgetEventPrefix:e
},a,{
constructor:r,
namespace:l,
widgetName:e,
widgetFullName:n
}),o?(t.each(o._childConstructors,function(e,i){
var s=i.prototype;
t.widget(s.namespace+"."+s.widgetName,r,i._proto);
}),delete o._childConstructors):i._childConstructors.push(r),t.widget.bridge(e,r);
},t.widget.extend=function(i){
for(var n,o,r=s.call(arguments,1),h=0,a=r.length;a>h;h++)for(n in r[h])o=r[h][n],
r[h].hasOwnProperty(n)&&o!==e&&(i[n]=t.isPlainObject(o)?t.isPlainObject(i[n])?t.widget.extend({},i[n],o):t.widget.extend({},o):o);
return i;
},t.widget.bridge=function(i,n){
var o=n.prototype.widgetFullName||i;
t.fn[i]=function(r){
var h="string"==typeof r,a=s.call(arguments,1),l=this;
return r=!h&&a.length?t.widget.extend.apply(null,[r].concat(a)):r,this.each(h?function(){
var s,n=t.data(this,o);
return n?t.isFunction(n[r])&&"_"!==r.charAt(0)?(s=n[r].apply(n,a),s!==n&&s!==e?(l=s&&s.jquery?l.pushStack(s.get()):s,
!1):void 0):t.error("no such method '"+r+"' for "+i+" widget instance"):t.error("cannot call methods on "+i+" prior to initialization; attempted to call method '"+r+"'");
}:function(){
var e=t.data(this,o);
e?e.option(r||{})._init():t.data(this,o,new n(r,this));
}),l;
};
},t.Widget=function(){},t.Widget._childConstructors=[],t.Widget.prototype={
widgetName:"widget",
widgetEventPrefix:"",
defaultElement:"<div>",
options:{
disabled:!1,
create:null
},
_createWidget:function(e,s){
s=t(s||this.defaultElement||this)[0],this.element=t(s),this.uuid=i++,this.eventNamespace="."+this.widgetName+this.uuid,
this.options=t.widget.extend({},this.options,this._getCreateOptions(),e),this.bindings=t(),
this.hoverable=t(),this.focusable=t(),s!==this&&(t.data(s,this.widgetFullName,this),
this._on(!0,this.element,{
remove:function(t){
t.target===s&&this.destroy();
}
}),this.document=t(s.style?s.ownerDocument:s.document||s),this.window=t(this.document[0].defaultView||this.document[0].parentWindow)),
this._create(),this._trigger("create",null,this._getCreateEventData()),this._init();
},
_getCreateOptions:t.noop,
_getCreateEventData:t.noop,
_create:t.noop,
_init:t.noop,
destroy:function(){
this._destroy(),this.element.unbind(this.eventNamespace).removeData(this.widgetName).removeData(this.widgetFullName).removeData(t.camelCase(this.widgetFullName)),
this.widget().unbind(this.eventNamespace).removeAttr("aria-disabled").removeClass(this.widgetFullName+"-disabled ui-state-disabled"),
this.bindings.unbind(this.eventNamespace),this.hoverable.removeClass("ui-state-hover"),
this.focusable.removeClass("ui-state-focus");
},
_destroy:t.noop,
widget:function(){
return this.element;
},
option:function(i,s){
var n,o,r,h=i;
if(0===arguments.length)return t.widget.extend({},this.options);
if("string"==typeof i)if(h={},n=i.split("."),i=n.shift(),n.length){
for(o=h[i]=t.widget.extend({},this.options[i]),r=0;r<n.length-1;r++)o[n[r]]=o[n[r]]||{},
o=o[n[r]];
if(i=n.pop(),s===e)return o[i]===e?null:o[i];
o[i]=s;
}else{
if(s===e)return this.options[i]===e?null:this.options[i];
h[i]=s;
}
return this._setOptions(h),this;
},
_setOptions:function(t){
var e;
for(e in t)this._setOption(e,t[e]);
return this;
},
_setOption:function(t,e){
return this.options[t]=e,"disabled"===t&&(this.widget().toggleClass(this.widgetFullName+"-disabled ui-state-disabled",!!e).attr("aria-disabled",e),
this.hoverable.removeClass("ui-state-hover"),this.focusable.removeClass("ui-state-focus")),
this;
},
enable:function(){
return this._setOption("disabled",!1);
},
disable:function(){
return this._setOption("disabled",!0);
},
_on:function(e,i,s){
var n,o=this;
"boolean"!=typeof e&&(s=i,i=e,e=!1),s?(i=n=t(i),this.bindings=this.bindings.add(i)):(s=i,
i=this.element,n=this.widget()),t.each(s,function(s,r){
function h(){
return e||o.options.disabled!==!0&&!t(this).hasClass("ui-state-disabled")?("string"==typeof r?o[r]:r).apply(o,arguments):void 0;
}
"string"!=typeof r&&(h.guid=r.guid=r.guid||h.guid||t.guid++);
var a=s.match(/^(\w+)\s*(.*)$/),l=a[1]+o.eventNamespace,c=a[2];
c?n.delegate(c,l,h):i.bind(l,h);
});
},
_off:function(t,e){
e=(e||"").split(" ").join(this.eventNamespace+" ")+this.eventNamespace,t.unbind(e).undelegate(e);
},
_delay:function(t,e){
function i(){
return("string"==typeof t?s[t]:t).apply(s,arguments);
}
var s=this;
return setTimeout(i,e||0);
},
_hoverable:function(e){
this.hoverable=this.hoverable.add(e),this._on(e,{
mouseenter:function(e){
t(e.currentTarget).addClass("ui-state-hover");
},
mouseleave:function(e){
t(e.currentTarget).removeClass("ui-state-hover");
}
});
},
_focusable:function(e){
this.focusable=this.focusable.add(e),this._on(e,{
focusin:function(e){
t(e.currentTarget).addClass("ui-state-focus");
},
focusout:function(e){
t(e.currentTarget).removeClass("ui-state-focus");
}
});
},
_trigger:function(e,i,s){
var n,o,r=this.options[e];
if(s=s||{},i=t.Event(i),i.type=(e===this.widgetEventPrefix?e:this.widgetEventPrefix+e).toLowerCase(),
i.target=this.element[0],o=i.originalEvent)for(n in o)n in i||(i[n]=o[n]);
return this.element.trigger(i,s),!(t.isFunction(r)&&r.apply(this.element[0],[i].concat(s))===!1||i.isDefaultPrevented());
}
},t.each({
show:"fadeIn",
hide:"fadeOut"
},function(e,i){
t.Widget.prototype["_"+e]=function(s,n,o){
"string"==typeof n&&(n={
effect:n
});
var r,h=n?n===!0||"number"==typeof n?i:n.effect||i:e;
n=n||{},"number"==typeof n&&(n={
duration:n
}),r=!t.isEmptyObject(n),n.complete=o,n.delay&&s.delay(n.delay),r&&t.effects&&t.effects.effect[h]?s[e](n):h!==e&&s[h]?s[h](n.duration,n.easing,o):s.queue(function(i){
t(this)[e](),o&&o.call(s[0]),i();
});
};
});
}(jQuery),function(t){
var e=!1;
t(document).mouseup(function(){
e=!1;
}),t.widget("ui.mouse",{
version:"1.10.3",
options:{
cancel:"input,textarea,button,select,option",
distance:1,
delay:0
},
_mouseInit:function(){
var e=this;
this.element.bind("mousedown."+this.widgetName,function(t){
return e._mouseDown(t);
}).bind("click."+this.widgetName,function(i){
return!0===t.data(i.target,e.widgetName+".preventClickEvent")?(t.removeData(i.target,e.widgetName+".preventClickEvent"),
i.stopImmediatePropagation(),!1):void 0;
}),this.started=!1;
},
_mouseDestroy:function(){
this.element.unbind("."+this.widgetName),this._mouseMoveDelegate&&t(document).unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate);
},
_mouseDown:function(i){
if(!e){
this._mouseStarted&&this._mouseUp(i),this._mouseDownEvent=i;
var s=this,n=1===i.which,o="string"==typeof this.options.cancel&&i.target.nodeName?t(i.target).closest(this.options.cancel).length:!1;
return n&&!o&&this._mouseCapture(i)?(this.mouseDelayMet=!this.options.delay,this.mouseDelayMet||(this._mouseDelayTimer=setTimeout(function(){
s.mouseDelayMet=!0;
},this.options.delay)),this._mouseDistanceMet(i)&&this._mouseDelayMet(i)&&(this._mouseStarted=this._mouseStart(i)!==!1,
!this._mouseStarted)?(i.preventDefault(),!0):(!0===t.data(i.target,this.widgetName+".preventClickEvent")&&t.removeData(i.target,this.widgetName+".preventClickEvent"),
this._mouseMoveDelegate=function(t){
return s._mouseMove(t);
},this._mouseUpDelegate=function(t){
return s._mouseUp(t);
},t(document).bind("mousemove."+this.widgetName,this._mouseMoveDelegate).bind("mouseup."+this.widgetName,this._mouseUpDelegate),
i.preventDefault(),e=!0,!0)):!0;
}
},
_mouseMove:function(e){
return t.ui.ie&&(!document.documentMode||document.documentMode<9)&&!e.button?this._mouseUp(e):this._mouseStarted?(this._mouseDrag(e),
e.preventDefault()):(this._mouseDistanceMet(e)&&this._mouseDelayMet(e)&&(this._mouseStarted=this._mouseStart(this._mouseDownEvent,e)!==!1,
this._mouseStarted?this._mouseDrag(e):this._mouseUp(e)),!this._mouseStarted);
},
_mouseUp:function(e){
return t(document).unbind("mousemove."+this.widgetName,this._mouseMoveDelegate).unbind("mouseup."+this.widgetName,this._mouseUpDelegate),
this._mouseStarted&&(this._mouseStarted=!1,e.target===this._mouseDownEvent.target&&t.data(e.target,this.widgetName+".preventClickEvent",!0),
this._mouseStop(e)),!1;
},
_mouseDistanceMet:function(t){
return Math.max(Math.abs(this._mouseDownEvent.pageX-t.pageX),Math.abs(this._mouseDownEvent.pageY-t.pageY))>=this.options.distance;
},
_mouseDelayMet:function(){
return this.mouseDelayMet;
},
_mouseStart:function(){},
_mouseDrag:function(){},
_mouseStop:function(){},
_mouseCapture:function(){
return!0;
}
});
}(jQuery),function(t){
function e(t,e,i){
return t>e&&e+i>t;
}
function i(t){
return/left|right/.test(t.css("float"))||/inline|table-cell/.test(t.css("display"));
}
t.widget("ui.sortable",t.ui.mouse,{
version:"1.10.3",
widgetEventPrefix:"sort",
ready:!1,
options:{
appendTo:"parent",
axis:!1,
connectWith:!1,
containment:!1,
cursor:"auto",
cursorAt:!1,
dropOnEmpty:!0,
forcePlaceholderSize:!1,
forceHelperSize:!1,
grid:!1,
handle:!1,
helper:"original",
items:"> *",
opacity:!1,
placeholder:!1,
revert:!1,
scroll:!0,
scrollSensitivity:20,
scrollSpeed:20,
scope:"default",
tolerance:"intersect",
zIndex:1e3,
activate:null,
beforeStop:null,
change:null,
deactivate:null,
out:null,
over:null,
receive:null,
remove:null,
sort:null,
start:null,
stop:null,
update:null
},
_create:function(){
var t=this.options;
this.containerCache={},this.element.addClass("ui-sortable"),this.refresh(),this.floating=this.items.length?"x"===t.axis||i(this.items[0].item):!1,
this.offset=this.element.offset(),this._mouseInit(),this.ready=!0;
},
_destroy:function(){
this.element.removeClass("ui-sortable ui-sortable-disabled"),this._mouseDestroy();
for(var t=this.items.length-1;t>=0;t--)this.items[t].item.removeData(this.widgetName+"-item");
return this;
},
_setOption:function(e,i){
"disabled"===e?(this.options[e]=i,this.widget().toggleClass("ui-sortable-disabled",!!i)):t.Widget.prototype._setOption.apply(this,arguments);
},
_mouseCapture:function(e,i){
var s=null,n=!1,o=this;
return this.reverting?!1:this.options.disabled||"static"===this.options.type?!1:(this._refreshItems(e),
t(e.target).parents().each(function(){
return t.data(this,o.widgetName+"-item")===o?(s=t(this),!1):void 0;
}),t.data(e.target,o.widgetName+"-item")===o&&(s=t(e.target)),s&&(!this.options.handle||i||(t(this.options.handle,s).find("*").addBack().each(function(){
this===e.target&&(n=!0);
}),n))?(this.currentItem=s,this._removeCurrentsFromItems(),!0):!1);
},
_mouseStart:function(e,i,s){
var n,o,r=this.options;
if(this.currentContainer=this,this.refreshPositions(),this.helper=this._createHelper(e),
this._cacheHelperProportions(),this._cacheMargins(),this.scrollParent=this.helper.scrollParent(),
this.offset=this.currentItem.offset(),this.offset={
top:this.offset.top-this.margins.top,
left:this.offset.left-this.margins.left
},t.extend(this.offset,{
click:{
left:e.pageX-this.offset.left,
top:e.pageY-this.offset.top
},
parent:this._getParentOffset(),
relative:this._getRelativeOffset()
}),this.helper.css("position","absolute"),this.cssPosition=this.helper.css("position"),
this.originalPosition=this._generatePosition(e),this.originalPageX=e.pageX,this.originalPageY=e.pageY,
r.cursorAt&&this._adjustOffsetFromHelper(r.cursorAt),this.domPosition={
prev:this.currentItem.prev()[0],
parent:this.currentItem.parent()[0]
},this.helper[0]!==this.currentItem[0]&&this.currentItem.hide(),this._createPlaceholder(),
r.containment&&this._setContainment(),r.cursor&&"auto"!==r.cursor&&(o=this.document.find("body"),
this.storedCursor=o.css("cursor"),o.css("cursor",r.cursor),this.storedStylesheet=t("<style>*{ cursor: "+r.cursor+" !important; }</style>").appendTo(o)),
r.opacity&&(this.helper.css("opacity")&&(this._storedOpacity=this.helper.css("opacity")),
this.helper.css("opacity",r.opacity)),r.zIndex&&(this.helper.css("zIndex")&&(this._storedZIndex=this.helper.css("zIndex")),
this.helper.css("zIndex",r.zIndex)),this.scrollParent[0]!==document&&"HTML"!==this.scrollParent[0].tagName&&(this.overflowOffset=this.scrollParent.offset()),
this._trigger("start",e,this._uiHash()),this._preserveHelperProportions||this._cacheHelperProportions(),
!s)for(n=this.containers.length-1;n>=0;n--)this.containers[n]._trigger("activate",e,this._uiHash(this));
return t.ui.ddmanager&&(t.ui.ddmanager.current=this),t.ui.ddmanager&&!r.dropBehaviour&&t.ui.ddmanager.prepareOffsets(this,e),
this.dragging=!0,this.helper.addClass("ui-sortable-helper"),this._mouseDrag(e),!0;
},
_mouseDrag:function(e){
var i,s,n,o,r=this.options,h=!1;
for(this.position=this._generatePosition(e),this.positionAbs=this._convertPositionTo("absolute"),
this.lastPositionAbs||(this.lastPositionAbs=this.positionAbs),this.options.scroll&&(this.scrollParent[0]!==document&&"HTML"!==this.scrollParent[0].tagName?(this.overflowOffset.top+this.scrollParent[0].offsetHeight-e.pageY<r.scrollSensitivity?this.scrollParent[0].scrollTop=h=this.scrollParent[0].scrollTop+r.scrollSpeed:e.pageY-this.overflowOffset.top<r.scrollSensitivity&&(this.scrollParent[0].scrollTop=h=this.scrollParent[0].scrollTop-r.scrollSpeed),
this.overflowOffset.left+this.scrollParent[0].offsetWidth-e.pageX<r.scrollSensitivity?this.scrollParent[0].scrollLeft=h=this.scrollParent[0].scrollLeft+r.scrollSpeed:e.pageX-this.overflowOffset.left<r.scrollSensitivity&&(this.scrollParent[0].scrollLeft=h=this.scrollParent[0].scrollLeft-r.scrollSpeed)):(e.pageY-t(document).scrollTop()<r.scrollSensitivity?h=t(document).scrollTop(t(document).scrollTop()-r.scrollSpeed):t(window).height()-(e.pageY-t(document).scrollTop())<r.scrollSensitivity&&(h=t(document).scrollTop(t(document).scrollTop()+r.scrollSpeed)),
e.pageX-t(document).scrollLeft()<r.scrollSensitivity?h=t(document).scrollLeft(t(document).scrollLeft()-r.scrollSpeed):t(window).width()-(e.pageX-t(document).scrollLeft())<r.scrollSensitivity&&(h=t(document).scrollLeft(t(document).scrollLeft()+r.scrollSpeed))),
h!==!1&&t.ui.ddmanager&&!r.dropBehaviour&&t.ui.ddmanager.prepareOffsets(this,e)),
this.positionAbs=this._convertPositionTo("absolute"),this.options.axis&&"y"===this.options.axis||(this.helper[0].style.left=this.position.left+"px"),
this.options.axis&&"x"===this.options.axis||(this.helper[0].style.top=this.position.top+"px"),
i=this.items.length-1;i>=0;i--)if(s=this.items[i],n=s.item[0],o=this._intersectsWithPointer(s),
o&&s.instance===this.currentContainer&&n!==this.currentItem[0]&&this.placeholder[1===o?"next":"prev"]()[0]!==n&&!t.contains(this.placeholder[0],n)&&("semi-dynamic"===this.options.type?!t.contains(this.element[0],n):!0)){
if(this.direction=1===o?"down":"up","pointer"!==this.options.tolerance&&!this._intersectsWithSides(s))break;
this._rearrange(e,s),this._trigger("change",e,this._uiHash());
break;
}
return this._contactContainers(e),t.ui.ddmanager&&t.ui.ddmanager.drag(this,e),this._trigger("sort",e,this._uiHash()),
this.lastPositionAbs=this.positionAbs,!1;
},
_mouseStop:function(e,i){
if(e){
if(t.ui.ddmanager&&!this.options.dropBehaviour&&t.ui.ddmanager.drop(this,e),this.options.revert){
var s=this,n=this.placeholder.offset(),o=this.options.axis,r={};
o&&"x"!==o||(r.left=n.left-this.offset.parent.left-this.margins.left+(this.offsetParent[0]===document.body?0:this.offsetParent[0].scrollLeft)),
o&&"y"!==o||(r.top=n.top-this.offset.parent.top-this.margins.top+(this.offsetParent[0]===document.body?0:this.offsetParent[0].scrollTop)),
this.reverting=!0,t(this.helper).animate(r,parseInt(this.options.revert,10)||500,function(){
s._clear(e);
});
}else this._clear(e,i);
return!1;
}
},
cancel:function(){
if(this.dragging){
this._mouseUp({
target:null
}),"original"===this.options.helper?this.currentItem.css(this._storedCSS).removeClass("ui-sortable-helper"):this.currentItem.show();
for(var e=this.containers.length-1;e>=0;e--)this.containers[e]._trigger("deactivate",null,this._uiHash(this)),
this.containers[e].containerCache.over&&(this.containers[e]._trigger("out",null,this._uiHash(this)),
this.containers[e].containerCache.over=0);
}
return this.placeholder&&(this.placeholder[0].parentNode&&this.placeholder[0].parentNode.removeChild(this.placeholder[0]),
"original"!==this.options.helper&&this.helper&&this.helper[0].parentNode&&this.helper.remove(),
t.extend(this,{
helper:null,
dragging:!1,
reverting:!1,
_noFinalSort:null
}),this.domPosition.prev?t(this.domPosition.prev).after(this.currentItem):t(this.domPosition.parent).prepend(this.currentItem)),
this;
},
serialize:function(e){
var i=this._getItemsAsjQuery(e&&e.connected),s=[];
return e=e||{},t(i).each(function(){
var i=(t(e.item||this).attr(e.attribute||"id")||"").match(e.expression||/(.+)[\-=_](.+)/);
i&&s.push((e.key||i[1]+"[]")+"="+(e.key&&e.expression?i[1]:i[2]));
}),!s.length&&e.key&&s.push(e.key+"="),s.join("&");
},
toArray:function(e){
var i=this._getItemsAsjQuery(e&&e.connected),s=[];
return e=e||{},i.each(function(){
s.push(t(e.item||this).attr(e.attribute||"id")||"");
}),s;
},
_intersectsWith:function(t){
var e=this.positionAbs.left,i=e+this.helperProportions.width,s=this.positionAbs.top,n=s+this.helperProportions.height,o=t.left,r=o+t.width,h=t.top,a=h+t.height,l=this.offset.click.top,c=this.offset.click.left,u="x"===this.options.axis||s+l>h&&a>s+l,p="y"===this.options.axis||e+c>o&&r>e+c,f=u&&p;
return"pointer"===this.options.tolerance||this.options.forcePointerForContainers||"pointer"!==this.options.tolerance&&this.helperProportions[this.floating?"width":"height"]>t[this.floating?"width":"height"]?f:o<e+this.helperProportions.width/2&&i-this.helperProportions.width/2<r&&h<s+this.helperProportions.height/2&&n-this.helperProportions.height/2<a;
},
_intersectsWithPointer:function(t){
var i="x"===this.options.axis||e(this.positionAbs.top+this.offset.click.top,t.top,t.height),s="y"===this.options.axis||e(this.positionAbs.left+this.offset.click.left,t.left,t.width),n=i&&s,o=this._getDragVerticalDirection(),r=this._getDragHorizontalDirection();
return n?this.floating?r&&"right"===r||"down"===o?2:1:o&&("down"===o?2:1):!1;
},
_intersectsWithSides:function(t){
var i=e(this.positionAbs.top+this.offset.click.top,t.top+t.height/2,t.height),s=e(this.positionAbs.left+this.offset.click.left,t.left+t.width/2,t.width),n=this._getDragVerticalDirection(),o=this._getDragHorizontalDirection();
return this.floating&&o?"right"===o&&s||"left"===o&&!s:n&&("down"===n&&i||"up"===n&&!i);
},
_getDragVerticalDirection:function(){
var t=this.positionAbs.top-this.lastPositionAbs.top;
return 0!==t&&(t>0?"down":"up");
},
_getDragHorizontalDirection:function(){
var t=this.positionAbs.left-this.lastPositionAbs.left;
return 0!==t&&(t>0?"right":"left");
},
refresh:function(t){
return this._refreshItems(t),this.refreshPositions(),this;
},
_connectWith:function(){
var t=this.options;
return t.connectWith.constructor===String?[t.connectWith]:t.connectWith;
},
_getItemsAsjQuery:function(e){
var i,s,n,o,r=[],h=[],a=this._connectWith();
if(a&&e)for(i=a.length-1;i>=0;i--)for(n=t(a[i]),s=n.length-1;s>=0;s--)o=t.data(n[s],this.widgetFullName),
o&&o!==this&&!o.options.disabled&&h.push([t.isFunction(o.options.items)?o.options.items.call(o.element):t(o.options.items,o.element).not(".ui-sortable-helper").not(".ui-sortable-placeholder"),o]);
for(h.push([t.isFunction(this.options.items)?this.options.items.call(this.element,null,{
options:this.options,
item:this.currentItem
}):t(this.options.items,this.element).not(".ui-sortable-helper").not(".ui-sortable-placeholder"),this]),
i=h.length-1;i>=0;i--)h[i][0].each(function(){
r.push(this);
});
return t(r);
},
_removeCurrentsFromItems:function(){
var e=this.currentItem.find(":data("+this.widgetName+"-item)");
this.items=t.grep(this.items,function(t){
for(var i=0;i<e.length;i++)if(e[i]===t.item[0])return!1;
return!0;
});
},
_refreshItems:function(e){
this.items=[],this.containers=[this];
var i,s,n,o,r,h,a,l,c=this.items,u=[[t.isFunction(this.options.items)?this.options.items.call(this.element[0],e,{
item:this.currentItem
}):t(this.options.items,this.element),this]],p=this._connectWith();
if(p&&this.ready)for(i=p.length-1;i>=0;i--)for(n=t(p[i]),s=n.length-1;s>=0;s--)o=t.data(n[s],this.widgetFullName),
o&&o!==this&&!o.options.disabled&&(u.push([t.isFunction(o.options.items)?o.options.items.call(o.element[0],e,{
item:this.currentItem
}):t(o.options.items,o.element),o]),this.containers.push(o));
for(i=u.length-1;i>=0;i--)for(r=u[i][1],h=u[i][0],s=0,l=h.length;l>s;s++)a=t(h[s]),
a.data(this.widgetName+"-item",r),c.push({
item:a,
instance:r,
width:0,
height:0,
left:0,
top:0
});
},
refreshPositions:function(e){
this.offsetParent&&this.helper&&(this.offset.parent=this._getParentOffset());
var i,s,n,o;
for(i=this.items.length-1;i>=0;i--)s=this.items[i],s.instance!==this.currentContainer&&this.currentContainer&&s.item[0]!==this.currentItem[0]||(n=this.options.toleranceElement?t(this.options.toleranceElement,s.item):s.item,
e||(s.width=n.outerWidth(),s.height=n.outerHeight()),o=n.offset(),s.left=o.left,
s.top=o.top);
if(this.options.custom&&this.options.custom.refreshContainers)this.options.custom.refreshContainers.call(this);else for(i=this.containers.length-1;i>=0;i--)o=this.containers[i].element.offset(),
this.containers[i].containerCache.left=o.left,this.containers[i].containerCache.top=o.top,
this.containers[i].containerCache.width=this.containers[i].element.outerWidth(),
this.containers[i].containerCache.height=this.containers[i].element.outerHeight();
return this;
},
_createPlaceholder:function(e){
e=e||this;
var i,s=e.options;
s.placeholder&&s.placeholder.constructor!==String||(i=s.placeholder,s.placeholder={
element:function(){
var s=e.currentItem[0].nodeName.toLowerCase(),n=t("<"+s+">",e.document[0]).addClass(i||e.currentItem[0].className+" ui-sortable-placeholder").removeClass("ui-sortable-helper");
return"tr"===s?e.currentItem.children().each(function(){
t("<td>&#160;</td>",e.document[0]).attr("colspan",t(this).attr("colspan")||1).appendTo(n);
}):"img"===s&&n.attr("src",e.currentItem.attr("src")),i||n.css("visibility","hidden"),
n;
},
update:function(t,n){
(!i||s.forcePlaceholderSize)&&(n.height()||n.height(e.currentItem.innerHeight()-parseInt(e.currentItem.css("paddingTop")||0,10)-parseInt(e.currentItem.css("paddingBottom")||0,10)),
n.width()||n.width(e.currentItem.innerWidth()-parseInt(e.currentItem.css("paddingLeft")||0,10)-parseInt(e.currentItem.css("paddingRight")||0,10)));
}
}),e.placeholder=t(s.placeholder.element.call(e.element,e.currentItem)),e.currentItem.after(e.placeholder),
s.placeholder.update(e,e.placeholder);
},
_contactContainers:function(s){
var n,o,r,h,a,l,c,u,p,f,d=null,m=null;
for(n=this.containers.length-1;n>=0;n--)if(!t.contains(this.currentItem[0],this.containers[n].element[0]))if(this._intersectsWith(this.containers[n].containerCache)){
if(d&&t.contains(this.containers[n].element[0],d.element[0]))continue;
d=this.containers[n],m=n;
}else this.containers[n].containerCache.over&&(this.containers[n]._trigger("out",s,this._uiHash(this)),
this.containers[n].containerCache.over=0);
if(d)if(1===this.containers.length)this.containers[m].containerCache.over||(this.containers[m]._trigger("over",s,this._uiHash(this)),
this.containers[m].containerCache.over=1);else{
for(r=1e4,h=null,f=d.floating||i(this.currentItem),a=f?"left":"top",l=f?"width":"height",
c=this.positionAbs[a]+this.offset.click[a],o=this.items.length-1;o>=0;o--)t.contains(this.containers[m].element[0],this.items[o].item[0])&&this.items[o].item[0]!==this.currentItem[0]&&(!f||e(this.positionAbs.top+this.offset.click.top,this.items[o].top,this.items[o].height))&&(u=this.items[o].item.offset()[a],
p=!1,Math.abs(u-c)>Math.abs(u+this.items[o][l]-c)&&(p=!0,u+=this.items[o][l]),Math.abs(u-c)<r&&(r=Math.abs(u-c),
h=this.items[o],this.direction=p?"up":"down"));
if(!h&&!this.options.dropOnEmpty)return;
if(this.currentContainer===this.containers[m])return;
h?this._rearrange(s,h,null,!0):this._rearrange(s,null,this.containers[m].element,!0),
this._trigger("change",s,this._uiHash()),this.containers[m]._trigger("change",s,this._uiHash(this)),
this.currentContainer=this.containers[m],this.options.placeholder.update(this.currentContainer,this.placeholder),
this.containers[m]._trigger("over",s,this._uiHash(this)),this.containers[m].containerCache.over=1;
}
},
_createHelper:function(e){
var i=this.options,s=t.isFunction(i.helper)?t(i.helper.apply(this.element[0],[e,this.currentItem])):"clone"===i.helper?this.currentItem.clone():this.currentItem;
return s.parents("body").length||t("parent"!==i.appendTo?i.appendTo:this.currentItem[0].parentNode)[0].appendChild(s[0]),
s[0]===this.currentItem[0]&&(this._storedCSS={
width:this.currentItem[0].style.width,
height:this.currentItem[0].style.height,
position:this.currentItem.css("position"),
top:this.currentItem.css("top"),
left:this.currentItem.css("left")
}),(!s[0].style.width||i.forceHelperSize)&&s.width(this.currentItem.width()),(!s[0].style.height||i.forceHelperSize)&&s.height(this.currentItem.height()),
s;
},
_adjustOffsetFromHelper:function(e){
"string"==typeof e&&(e=e.split(" ")),t.isArray(e)&&(e={
left:+e[0],
top:+e[1]||0
}),"left"in e&&(this.offset.click.left=e.left+this.margins.left),"right"in e&&(this.offset.click.left=this.helperProportions.width-e.right+this.margins.left),
"top"in e&&(this.offset.click.top=e.top+this.margins.top),"bottom"in e&&(this.offset.click.top=this.helperProportions.height-e.bottom+this.margins.top);
},
_getParentOffset:function(){
this.offsetParent=this.helper.offsetParent();
var e=this.offsetParent.offset();
return"absolute"===this.cssPosition&&this.scrollParent[0]!==document&&t.contains(this.scrollParent[0],this.offsetParent[0])&&(e.left+=this.scrollParent.scrollLeft(),
e.top+=this.scrollParent.scrollTop()),(this.offsetParent[0]===document.body||this.offsetParent[0].tagName&&"html"===this.offsetParent[0].tagName.toLowerCase()&&t.ui.ie)&&(e={
top:0,
left:0
}),{
top:e.top+(parseInt(this.offsetParent.css("borderTopWidth"),10)||0),
left:e.left+(parseInt(this.offsetParent.css("borderLeftWidth"),10)||0)
};
},
_getRelativeOffset:function(){
if("relative"===this.cssPosition){
var t=this.currentItem.position();
return{
top:t.top-(parseInt(this.helper.css("top"),10)||0)+this.scrollParent.scrollTop(),
left:t.left-(parseInt(this.helper.css("left"),10)||0)+this.scrollParent.scrollLeft()
};
}
return{
top:0,
left:0
};
},
_cacheMargins:function(){
this.margins={
left:parseInt(this.currentItem.css("marginLeft"),10)||0,
top:parseInt(this.currentItem.css("marginTop"),10)||0
};
},
_cacheHelperProportions:function(){
this.helperProportions={
width:this.helper.outerWidth(),
height:this.helper.outerHeight()
};
},
_setContainment:function(){
var e,i,s,n=this.options;
"parent"===n.containment&&(n.containment=this.helper[0].parentNode),("document"===n.containment||"window"===n.containment)&&(this.containment=[0-this.offset.relative.left-this.offset.parent.left,0-this.offset.relative.top-this.offset.parent.top,t("document"===n.containment?document:window).width()-this.helperProportions.width-this.margins.left,(t("document"===n.containment?document:window).height()||document.body.parentNode.scrollHeight)-this.helperProportions.height-this.margins.top]),
/^(document|window|parent)$/.test(n.containment)||(e=t(n.containment)[0],i=t(n.containment).offset(),
s="hidden"!==t(e).css("overflow"),this.containment=[i.left+(parseInt(t(e).css("borderLeftWidth"),10)||0)+(parseInt(t(e).css("paddingLeft"),10)||0)-this.margins.left,i.top+(parseInt(t(e).css("borderTopWidth"),10)||0)+(parseInt(t(e).css("paddingTop"),10)||0)-this.margins.top,i.left+(s?Math.max(e.scrollWidth,e.offsetWidth):e.offsetWidth)-(parseInt(t(e).css("borderLeftWidth"),10)||0)-(parseInt(t(e).css("paddingRight"),10)||0)-this.helperProportions.width-this.margins.left,i.top+(s?Math.max(e.scrollHeight,e.offsetHeight):e.offsetHeight)-(parseInt(t(e).css("borderTopWidth"),10)||0)-(parseInt(t(e).css("paddingBottom"),10)||0)-this.helperProportions.height-this.margins.top]);
},
_convertPositionTo:function(e,i){
i||(i=this.position);
var s="absolute"===e?1:-1,n="absolute"!==this.cssPosition||this.scrollParent[0]!==document&&t.contains(this.scrollParent[0],this.offsetParent[0])?this.scrollParent:this.offsetParent,o=/(html|body)/i.test(n[0].tagName);
return{
top:i.top+this.offset.relative.top*s+this.offset.parent.top*s-("fixed"===this.cssPosition?-this.scrollParent.scrollTop():o?0:n.scrollTop())*s,
left:i.left+this.offset.relative.left*s+this.offset.parent.left*s-("fixed"===this.cssPosition?-this.scrollParent.scrollLeft():o?0:n.scrollLeft())*s
};
},
_generatePosition:function(e){
var i,s,n=this.options,o=e.pageX,r=e.pageY,h="absolute"!==this.cssPosition||this.scrollParent[0]!==document&&t.contains(this.scrollParent[0],this.offsetParent[0])?this.scrollParent:this.offsetParent,a=/(html|body)/i.test(h[0].tagName);
return"relative"!==this.cssPosition||this.scrollParent[0]!==document&&this.scrollParent[0]!==this.offsetParent[0]||(this.offset.relative=this._getRelativeOffset()),
this.originalPosition&&(this.containment&&(e.pageX-this.offset.click.left<this.containment[0]&&(o=this.containment[0]+this.offset.click.left),
e.pageY-this.offset.click.top<this.containment[1]&&(r=this.containment[1]+this.offset.click.top),
e.pageX-this.offset.click.left>this.containment[2]&&(o=this.containment[2]+this.offset.click.left),
e.pageY-this.offset.click.top>this.containment[3]&&(r=this.containment[3]+this.offset.click.top)),
n.grid&&(i=this.originalPageY+Math.round((r-this.originalPageY)/n.grid[1])*n.grid[1],
r=this.containment?i-this.offset.click.top>=this.containment[1]&&i-this.offset.click.top<=this.containment[3]?i:i-this.offset.click.top>=this.containment[1]?i-n.grid[1]:i+n.grid[1]:i,
s=this.originalPageX+Math.round((o-this.originalPageX)/n.grid[0])*n.grid[0],o=this.containment?s-this.offset.click.left>=this.containment[0]&&s-this.offset.click.left<=this.containment[2]?s:s-this.offset.click.left>=this.containment[0]?s-n.grid[0]:s+n.grid[0]:s)),
{
top:r-this.offset.click.top-this.offset.relative.top-this.offset.parent.top+("fixed"===this.cssPosition?-this.scrollParent.scrollTop():a?0:h.scrollTop()),
left:o-this.offset.click.left-this.offset.relative.left-this.offset.parent.left+("fixed"===this.cssPosition?-this.scrollParent.scrollLeft():a?0:h.scrollLeft())
};
},
_rearrange:function(t,e,i,s){
i?i[0].appendChild(this.placeholder[0]):e.item[0].parentNode.insertBefore(this.placeholder[0],"down"===this.direction?e.item[0]:e.item[0].nextSibling),
this.counter=this.counter?++this.counter:1;
var n=this.counter;
this._delay(function(){
n===this.counter&&this.refreshPositions(!s);
});
},
_clear:function(t,e){
this.reverting=!1;
var i,s=[];
if(!this._noFinalSort&&this.currentItem.parent().length&&this.placeholder.before(this.currentItem),
this._noFinalSort=null,this.helper[0]===this.currentItem[0]){
for(i in this._storedCSS)("auto"===this._storedCSS[i]||"static"===this._storedCSS[i])&&(this._storedCSS[i]="");
this.currentItem.css(this._storedCSS).removeClass("ui-sortable-helper");
}else this.currentItem.show();
for(this.fromOutside&&!e&&s.push(function(t){
this._trigger("receive",t,this._uiHash(this.fromOutside));
}),!this.fromOutside&&this.domPosition.prev===this.currentItem.prev().not(".ui-sortable-helper")[0]&&this.domPosition.parent===this.currentItem.parent()[0]||e||s.push(function(t){
this._trigger("update",t,this._uiHash());
}),this!==this.currentContainer&&(e||(s.push(function(t){
this._trigger("remove",t,this._uiHash());
}),s.push(function(t){
return function(e){
t._trigger("receive",e,this._uiHash(this));
};
}.call(this,this.currentContainer)),s.push(function(t){
return function(e){
t._trigger("update",e,this._uiHash(this));
};
}.call(this,this.currentContainer)))),i=this.containers.length-1;i>=0;i--)e||s.push(function(t){
return function(e){
t._trigger("deactivate",e,this._uiHash(this));
};
}.call(this,this.containers[i])),this.containers[i].containerCache.over&&(s.push(function(t){
return function(e){
t._trigger("out",e,this._uiHash(this));
};
}.call(this,this.containers[i])),this.containers[i].containerCache.over=0);
if(this.storedCursor&&(this.document.find("body").css("cursor",this.storedCursor),
this.storedStylesheet.remove()),this._storedOpacity&&this.helper.css("opacity",this._storedOpacity),
this._storedZIndex&&this.helper.css("zIndex","auto"===this._storedZIndex?"":this._storedZIndex),
this.dragging=!1,this.cancelHelperRemoval){
if(!e){
for(this._trigger("beforeStop",t,this._uiHash()),i=0;i<s.length;i++)s[i].call(this,t);
this._trigger("stop",t,this._uiHash());
}
return this.fromOutside=!1,!1;
}
if(e||this._trigger("beforeStop",t,this._uiHash()),this.placeholder[0].parentNode.removeChild(this.placeholder[0]),
this.helper[0]!==this.currentItem[0]&&this.helper.remove(),this.helper=null,!e){
for(i=0;i<s.length;i++)s[i].call(this,t);
this._trigger("stop",t,this._uiHash());
}
return this.fromOutside=!1,!0;
},
_trigger:function(){
t.Widget.prototype._trigger.apply(this,arguments)===!1&&this.cancel();
},
_uiHash:function(e){
var i=e||this;
return{
helper:i.helper,
placeholder:i.placeholder||t([]),
position:i.position,
originalPosition:i.originalPosition,
offset:i.positionAbs,
item:i.currentItem,
sender:e?e.element:null
};
}
});
}(jQuery);
});define("biz_web/lib/json.js", [], function(require, exports, module) {
try {
var report_time_begin = +(new Date);
return typeof JSON != "object" && (JSON = {}), function() {
"use strict";
function f(e) {
return e < 10 ? "0" + e : e;
}
function quote(e) {
return escapable.lastIndex = 0, escapable.test(e) ? '"' + e.replace(escapable, function(e) {
var t = meta[e];
return typeof t == "string" ? t : "\\u" + ("0000" + e.charCodeAt(0).toString(16)).slice(-4);
}) + '"' : '"' + e + '"';
}
function str(e, t) {
var n, r, i, s, o = gap, u, a = t[e];
a && typeof a == "object" && typeof a.toJSON == "function" && (a = a.toJSON(e)), typeof rep == "function" && (a = rep.call(t, e, a));
switch (typeof a) {
case "string":
return quote(a);
case "number":
return isFinite(a) ? String(a) : "null";
case "boolean":
case "null":
return String(a);
case "object":
if (!a) return "null";
gap += indent, u = [];
if (Object.prototype.toString.apply(a) === "[object Array]") {
s = a.length;
for (n = 0; n < s; n += 1) u[n] = str(n, a) || "null";
return i = u.length === 0 ? "[]" : gap ? "[\n" + gap + u.join(",\n" + gap) + "\n" + o + "]" : "[" + u.join(",") + "]", gap = o, i;
}
if (rep && typeof rep == "object") {
s = rep.length;
for (n = 0; n < s; n += 1) typeof rep[n] == "string" && (r = rep[n], i = str(r, a), i && u.push(quote(r) + (gap ? ": " : ":") + i));
} else for (r in a) Object.prototype.hasOwnProperty.call(a, r) && (i = str(r, a), i && u.push(quote(r) + (gap ? ": " : ":") + i));
return i = u.length === 0 ? "{}" : gap ? "{\n" + gap + u.join(",\n" + gap) + "\n" + o + "}" : "{" + u.join(",") + "}", gap = o, i;
}
}
typeof Date.prototype.toJSON != "function" && (Date.prototype.toJSON = function(e) {
return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate()) + "T" + f(this.getUTCHours()) + ":" + f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds()) + "Z" : null;
}, String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function(e) {
return this.valueOf();
});
var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, gap, indent, meta = {
"\b": "\\b",
"	": "\\t",
"\n": "\\n",
"\f": "\\f",
"\r": "\\r",
'"': '\\"',
"\\": "\\\\"
}, rep;
JSON.stringify2 = function(e, t, n) {
var r;
gap = "", indent = "";
if (typeof n == "number") for (r = 0; r < n; r += 1) indent += " "; else typeof n == "string" && (indent = n);
rep = t;
if (!t || typeof t == "function" || typeof t == "object" && typeof t.length == "number") return str("", {
"": e
});
throw new Error("JSON.stringify");
}, typeof JSON.parse != "function" && (JSON.parse = function(text, reviver) {
function walk(e, t) {
var n, r, i = e[t];
if (i && typeof i == "object") for (n in i) Object.prototype.hasOwnProperty.call(i, n) && (r = walk(i, n), r !== undefined ? i[n] = r : delete i[n]);
return reviver.call(e, t, i);
}
var j;
text = String(text), cx.lastIndex = 0, cx.test(text) && (text = text.replace(cx, function(e) {
return "\\u" + ("0000" + e.charCodeAt(0).toString(16)).slice(-4);
}));
if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) return j = eval("(" + text + ")"), typeof reviver == "function" ? walk({
"": j
}, "") : j;
throw new SyntaxError("JSON.parse");
});
}(), JSON;
} catch (e) {
wx.jslog({
src: "biz_web/lib/json.js"
}, e);
}
});define("common/qq/emoji.js",["widget/emoji.css"],function(f){
f("widget/emoji.css");
var a={
"☀":"2600",
"☁":"2601",
"☔":"2614",
"⛄":"26c4",
"⚡":"26a1",
"🌀":"1f300",
"🌁":"1f301",
"🌂":"1f302",
"🌃":"1f303",
"🌄":"1f304",
"🌅":"1f305",
"🌆":"1f306",
"🌇":"1f307",
"🌈":"1f308",
"❄":"2744",
"⛅":"26c5",
"🌉":"1f309",
"🌊":"1f30a",
"🌋":"1f30b",
"🌌":"1f30c",
"🌏":"1f30f",
"🌑":"1f311",
"🌔":"1f314",
"🌓":"1f313",
"🌙":"1f319",
"🌕":"1f315",
"🌛":"1f31b",
"🌟":"1f31f",
"🌠":"1f320",
"🕐":"1f550",
"🕑":"1f551",
"🕒":"1f552",
"🕓":"1f553",
"🕔":"1f554",
"🕕":"1f555",
"🕖":"1f556",
"🕗":"1f557",
"🕘":"1f558",
"🕙":"1f559",
"🕚":"1f55a",
"🕛":"1f55b",
"⌚":"231a",
"⌛":"231b",
"⏰":"23f0",
"⏳":"23f3",
"♈":"2648",
"♉":"2649",
"♊":"264a",
"♋":"264b",
"♌":"264c",
"♍":"264d",
"♎":"264e",
"♏":"264f",
"♐":"2650",
"♑":"2651",
"♒":"2652",
"♓":"2653",
"⛎":"26ce",
"🍀":"1f340",
"🌷":"1f337",
"🌱":"1f331",
"🍁":"1f341",
"🌸":"1f338",
"🌹":"1f339",
"🍂":"1f342",
"🍃":"1f343",
"🌺":"1f33a",
"🌻":"1f33b",
"🌴":"1f334",
"🌵":"1f335",
"🌾":"1f33e",
"🌽":"1f33d",
"🍄":"1f344",
"🌰":"1f330",
"🌼":"1f33c",
"🌿":"1f33f",
"🍒":"1f352",
"🍌":"1f34c",
"🍎":"1f34e",
"🍊":"1f34a",
"🍓":"1f353",
"🍉":"1f349",
"🍅":"1f345",
"🍆":"1f346",
"🍈":"1f348",
"🍍":"1f34d",
"🍇":"1f347",
"🍑":"1f351",
"🍏":"1f34f",
"👀":"1f440",
"👂":"1f442",
"👃":"1f443",
"👄":"1f444",
"👅":"1f445",
"💄":"1f484",
"💅":"1f485",
"💆":"1f486",
"💇":"1f487",
"💈":"1f488",
"👤":"1f464",
"👦":"1f466",
"👧":"1f467",
"👨":"1f468",
"👩":"1f469",
"👪":"1f46a",
"👫":"1f46b",
"👮":"1f46e",
"👯":"1f46f",
"👰":"1f470",
"👱":"1f471",
"👲":"1f472",
"👳":"1f473",
"👴":"1f474",
"👵":"1f475",
"👶":"1f476",
"👷":"1f477",
"👸":"1f478",
"👹":"1f479",
"👺":"1f47a",
"👻":"1f47b",
"👼":"1f47c",
"👽":"1f47d",
"👾":"1f47e",
"👿":"1f47f",
"💀":"1f480",
"💁":"1f481",
"💂":"1f482",
"💃":"1f483",
"🐌":"1f40c",
"🐍":"1f40d",
"🐎":"1f40e",
"🐔":"1f414",
"🐗":"1f417",
"🐫":"1f42b",
"🐘":"1f418",
"🐨":"1f428",
"🐒":"1f412",
"🐑":"1f411",
"🐙":"1f419",
"🐚":"1f41a",
"🐛":"1f41b",
"🐜":"1f41c",
"🐝":"1f41d",
"🐞":"1f41e",
"🐠":"1f420",
"🐡":"1f421",
"🐢":"1f422",
"🐤":"1f424",
"🐥":"1f425",
"🐦":"1f426",
"🐣":"1f423",
"🐧":"1f427",
"🐩":"1f429",
"🐟":"1f41f",
"🐬":"1f42c",
"🐭":"1f42d",
"🐯":"1f42f",
"🐱":"1f431",
"🐳":"1f433",
"🐴":"1f434",
"🐵":"1f435",
"🐶":"1f436",
"🐷":"1f437",
"🐻":"1f43b",
"🐹":"1f439",
"🐺":"1f43a",
"🐮":"1f42e",
"🐰":"1f430",
"🐸":"1f438",
"🐾":"1f43e",
"🐲":"1f432",
"🐼":"1f43c",
"🐽":"1f43d",
"😠":"1f620",
"😩":"1f629",
"😲":"1f632",
"😞":"1f61e",
"😵":"1f635",
"😰":"1f630",
"😒":"1f612",
"😍":"1f60d",
"😤":"1f624",
"😜":"1f61c",
"😝":"1f61d",
"😋":"1f60b",
"😘":"1f618",
"😚":"1f61a",
"😷":"1f637",
"😳":"1f633",
"😃":"1f603",
"😅":"1f605",
"😆":"1f606",
"😁":"1f601",
"😂":"1f602",
"😊":"1f60a",
"☺":"263a",
"😄":"1f604",
"😢":"1f622",
"😭":"1f62d",
"😨":"1f628",
"😣":"1f623",
"😡":"1f621",
"😌":"1f60c",
"😖":"1f616",
"😔":"1f614",
"😱":"1f631",
"😪":"1f62a",
"😏":"1f60f",
"😓":"1f613",
"😥":"1f625",
"😫":"1f62b",
"😉":"1f609",
"😺":"1f63a",
"😸":"1f638",
"😹":"1f639",
"😽":"1f63d",
"😻":"1f63b",
"😿":"1f63f",
"😾":"1f63e",
"😼":"1f63c",
"🙀":"1f640",
"🙅":"1f645",
"🙆":"1f646",
"🙇":"1f647",
"🙈":"1f648",
"🙊":"1f64a",
"🙉":"1f649",
"🙋":"1f64b",
"🙌":"1f64c",
"🙍":"1f64d",
"🙎":"1f64e",
"🙏":"1f64f",
"🏠":"1f3e0",
"🏡":"1f3e1",
"🏢":"1f3e2",
"🏣":"1f3e3",
"🏥":"1f3e5",
"🏦":"1f3e6",
"🏧":"1f3e7",
"🏨":"1f3e8",
"🏩":"1f3e9",
"🏪":"1f3ea",
"🏫":"1f3eb",
"⛪":"26ea",
"⛲":"26f2",
"🏬":"1f3ec",
"🏯":"1f3ef",
"🏰":"1f3f0",
"🏭":"1f3ed",
"⚓":"2693",
"🏮":"1f3ee",
"🗻":"1f5fb",
"🗼":"1f5fc",
"🗽":"1f5fd",
"🗾":"1f5fe",
"🗿":"1f5ff",
"👞":"1f45e",
"👟":"1f45f",
"👠":"1f460",
"👡":"1f461",
"👢":"1f462",
"👣":"1f463",
"👓":"1f453",
"👕":"1f455",
"👖":"1f456",
"👑":"1f451",
"👔":"1f454",
"👒":"1f452",
"👗":"1f457",
"👘":"1f458",
"👙":"1f459",
"👚":"1f45a",
"👛":"1f45b",
"👜":"1f45c",
"👝":"1f45d",
"💰":"1f4b0",
"💱":"1f4b1",
"💹":"1f4b9",
"💲":"1f4b2",
"💳":"1f4b3",
"💴":"1f4b4",
"💵":"1f4b5",
"💸":"1f4b8",
"🇨🇳":"1f1e81f1f3",
"🇩🇪":"1f1e91f1ea",
"🇪🇸":"1f1ea1f1f8",
"🇫🇷":"1f1eb1f1f7",
"🇬🇧":"1f1ec1f1e7",
"🇮🇹":"1f1ee1f1f9",
"🇯🇵":"1f1ef1f1f5",
"🇰🇷":"1f1f01f1f7",
"🇷🇺":"1f1f71f1fa",
"🇺🇸":"1f1fa1f1f8",
"🔥":"1f525",
"🔦":"1f526",
"🔧":"1f527",
"🔨":"1f528",
"🔩":"1f529",
"🔪":"1f52a",
"🔫":"1f52b",
"🔮":"1f52e",
"🔯":"1f52f",
"🔰":"1f530",
"🔱":"1f531",
"💉":"1f489",
"💊":"1f48a",
"🅰":"1f170",
"🅱":"1f171",
"🆎":"1f18e",
"🅾":"1f17e",
"🎀":"1f380",
"🎁":"1f381",
"🎂":"1f382",
"🎄":"1f384",
"🎅":"1f385",
"🎌":"1f38c",
"🎆":"1f386",
"🎈":"1f388",
"🎉":"1f389",
"🎍":"1f38d",
"🎎":"1f38e",
"🎓":"1f393",
"🎒":"1f392",
"🎏":"1f38f",
"🎇":"1f387",
"🎐":"1f390",
"🎃":"1f383",
"🎊":"1f38a",
"🎋":"1f38b",
"🎑":"1f391",
"📟":"1f4df",
"☎":"260e",
"📞":"1f4de",
"📱":"1f4f1",
"📲":"1f4f2",
"📝":"1f4dd",
"📠":"1f4e0",
"✉":"2709",
"📨":"1f4e8",
"📩":"1f4e9",
"📪":"1f4ea",
"📫":"1f4eb",
"📮":"1f4ee",
"📰":"1f4f0",
"📢":"1f4e2",
"📣":"1f4e3",
"📡":"1f4e1",
"📤":"1f4e4",
"📥":"1f4e5",
"📦":"1f4e6",
"📧":"1f4e7",
"🔠":"1f520",
"🔡":"1f521",
"🔢":"1f522",
"🔣":"1f523",
"🔤":"1f524",
"✒":"2712",
"💺":"1f4ba",
"💻":"1f4bb",
"✏":"270f",
"📎":"1f4ce",
"💼":"1f4bc",
"💽":"1f4bd",
"💾":"1f4be",
"💿":"1f4bf",
"📀":"1f4c0",
"✂":"2702",
"📍":"1f4cd",
"📃":"1f4c3",
"📄":"1f4c4",
"📅":"1f4c5",
"📁":"1f4c1",
"📂":"1f4c2",
"📓":"1f4d3",
"📖":"1f4d6",
"📔":"1f4d4",
"📕":"1f4d5",
"📗":"1f4d7",
"📘":"1f4d8",
"📙":"1f4d9",
"📚":"1f4da",
"📛":"1f4db",
"📜":"1f4dc",
"📋":"1f4cb",
"📆":"1f4c6",
"📊":"1f4ca",
"📈":"1f4c8",
"📉":"1f4c9",
"📇":"1f4c7",
"📌":"1f4cc",
"📒":"1f4d2",
"📏":"1f4cf",
"📐":"1f4d0",
"📑":"1f4d1",
"🎽":"1f3bd",
"⚾":"26be",
"⛳":"26f3",
"🎾":"1f3be",
"⚽":"26bd",
"🎿":"1f3bf",
"🏀":"1f3c0",
"🏁":"1f3c1",
"🏂":"1f3c2",
"🏃":"1f3c3",
"🏄":"1f3c4",
"🏆":"1f3c6",
"🏈":"1f3c8",
"🏊":"1f3ca",
"🚃":"1f683",
"🚇":"1f687",
"Ⓜ":"24c2",
"🚄":"1f684",
"🚅":"1f685",
"🚗":"1f697",
"🚙":"1f699",
"🚌":"1f68c",
"🚏":"1f68f",
"🚢":"1f6a2",
"✈":"2708",
"⛵":"26f5",
"🚉":"1f689",
"🚀":"1f680",
"🚤":"1f6a4",
"🚕":"1f695",
"🚚":"1f69a",
"🚒":"1f692",
"🚑":"1f691",
"🚓":"1f693",
"⛽":"26fd",
"🅿":"1f17f",
"🚥":"1f6a5",
"🚧":"1f6a7",
"🚨":"1f6a8",
"♨":"2668",
"⛺":"26fa",
"🎠":"1f3a0",
"🎡":"1f3a1",
"🎢":"1f3a2",
"🎣":"1f3a3",
"🎤":"1f3a4",
"🎥":"1f3a5",
"🎦":"1f3a6",
"🎧":"1f3a7",
"🎨":"1f3a8",
"🎩":"1f3a9",
"🎪":"1f3aa",
"🎫":"1f3ab",
"🎬":"1f3ac",
"🎭":"1f3ad",
"🎮":"1f3ae",
"🀄":"1f004",
"🎯":"1f3af",
"🎰":"1f3b0",
"🎱":"1f3b1",
"🎲":"1f3b2",
"🎳":"1f3b3",
"🎴":"1f3b4",
"🃏":"1f0cf",
"🎵":"1f3b5",
"🎶":"1f3b6",
"🎷":"1f3b7",
"🎸":"1f3b8",
"🎹":"1f3b9",
"🎺":"1f3ba",
"🎻":"1f3bb",
"🎼":"1f3bc",
"〽":"303d",
"📷":"1f4f7",
"📹":"1f4f9",
"📺":"1f4fa",
"📻":"1f4fb",
"📼":"1f4fc",
"💋":"1f48b",
"💌":"1f48c",
"💍":"1f48d",
"💎":"1f48e",
"💏":"1f48f",
"💐":"1f490",
"💑":"1f491",
"💒":"1f492",
"🔞":"1f51e",
"©":"a9",
"®":"ae",
"™":"2122",
"ℹ":"2139",
"#⃣":"2320e3",
"1⃣":"3120e3",
"2⃣":"3220e3",
"3⃣":"3320e3",
"4⃣":"3420e3",
"5⃣":"3520e3",
"6⃣":"3620e3",
"7⃣":"3720e3",
"8⃣":"3820e3",
"9⃣":"3920e3",
"0⃣":"3020e3",
"🔟":"1f51f",
"📶":"1f4f6",
"📳":"1f4f3",
"📴":"1f4f4",
"🍔":"1f354",
"🍙":"1f359",
"🍰":"1f370",
"🍜":"1f35c",
"🍞":"1f35e",
"🍳":"1f373",
"🍦":"1f366",
"🍟":"1f35f",
"🍡":"1f361",
"🍘":"1f358",
"🍚":"1f35a",
"🍝":"1f35d",
"🍛":"1f35b",
"🍢":"1f362",
"🍣":"1f363",
"🍱":"1f371",
"🍲":"1f372",
"🍧":"1f367",
"🍖":"1f356",
"🍥":"1f365",
"🍠":"1f360",
"🍕":"1f355",
"🍗":"1f357",
"🍨":"1f368",
"🍩":"1f369",
"🍪":"1f36a",
"🍫":"1f36b",
"🍬":"1f36c",
"🍭":"1f36d",
"🍮":"1f36e",
"🍯":"1f36f",
"🍤":"1f364",
"🍴":"1f374",
"☕":"2615",
"🍸":"1f378",
"🍺":"1f37a",
"🍵":"1f375",
"🍶":"1f376",
"🍷":"1f377",
"🍻":"1f37b",
"🍹":"1f379",
"↗":"2197",
"↘":"2198",
"↖":"2196",
"↙":"2199",
"⤴":"2934",
"⤵":"2935",
"↔":"2194",
"↕":"2195",
"⬆":"2b06",
"⬇":"2b07",
"➡":"27a1",
"⬅":"2b05",
"▶":"25b6",
"◀":"25c0",
"⏩":"23e9",
"⏪":"23ea",
"⏫":"23eb",
"⏬":"23ec",
"🔺":"1f53a",
"🔻":"1f53b",
"🔼":"1f53c",
"🔽":"1f53d",
"⭕":"2b55",
"❌":"274c",
"❎":"274e",
"❗":"2757",
"⁉":"2049",
"‼":"203c",
"❓":"2753",
"❔":"2754",
"❕":"2755",
"〰":"3030",
"➰":"27b0",
"➿":"27bf",
"❤":"2764",
"💓":"1f493",
"💔":"1f494",
"💕":"1f495",
"💖":"1f496",
"💗":"1f497",
"💘":"1f498",
"💙":"1f499",
"💚":"1f49a",
"💛":"1f49b",
"💜":"1f49c",
"💝":"1f49d",
"💞":"1f49e",
"💟":"1f49f",
"♥":"2665",
"♠":"2660",
"♦":"2666",
"♣":"2663",
"🚬":"1f6ac",
"🚭":"1f6ad",
"♿":"267f",
"🚩":"1f6a9",
"⚠":"26a0",
"⛔":"26d4",
"♻":"267b",
"🚲":"1f6b2",
"🚶":"1f6b6",
"🚹":"1f6b9",
"🚺":"1f6ba",
"🛀":"1f6c0",
"🚻":"1f6bb",
"🚽":"1f6bd",
"🚾":"1f6be",
"🚼":"1f6bc",
"🚪":"1f6aa",
"🚫":"1f6ab",
"✔":"2714",
"🆑":"1f191",
"🆒":"1f192",
"🆓":"1f193",
"🆔":"1f194",
"🆕":"1f195",
"🆖":"1f196",
"🆗":"1f197",
"🆘":"1f198",
"🆙":"1f199",
"🆚":"1f19a",
"🈁":"1f201",
"🈂":"1f202",
"🈲":"1f232",
"🈳":"1f233",
"🈴":"1f234",
"🈵":"1f235",
"🈶":"1f236",
"🈚":"1f21a",
"🈷":"1f237",
"🈸":"1f238",
"🈹":"1f239",
"🈯":"1f22f",
"🈺":"1f23a",
"㊙":"3299",
"㊗":"3297",
"🉐":"1f250",
"🉑":"1f251",
"➕":"2795",
"➖":"2796",
"✖":"2716",
"➗":"2797",
"💠":"1f4a0",
"💡":"1f4a1",
"💢":"1f4a2",
"💣":"1f4a3",
"💤":"1f4a4",
"💥":"1f4a5",
"💦":"1f4a6",
"💧":"1f4a7",
"💨":"1f4a8",
"💩":"1f4a9",
"💪":"1f4aa",
"💫":"1f4ab",
"💬":"1f4ac",
"✨":"2728",
"✴":"2734",
"✳":"2733",
"⚪":"26aa",
"⚫":"26ab",
"🔴":"1f534",
"🔵":"1f535",
"🔲":"1f532",
"🔳":"1f533",
"⭐":"2b50",
"⬜":"2b1c",
"⬛":"2b1b",
"▫":"25ab",
"▪":"25aa",
"◽":"25fd",
"◾":"25fe",
"◻":"25fb",
"◼":"25fc",
"🔶":"1f536",
"🔷":"1f537",
"🔸":"1f538",
"🔹":"1f539",
"❇":"2747",
"💮":"1f4ae",
"💯":"1f4af",
"↩":"21a9",
"↪":"21aa",
"🔃":"1f503",
"🔊":"1f50a",
"🔋":"1f50b",
"🔌":"1f50c",
"🔍":"1f50d",
"🔎":"1f50e",
"🔒":"1f512",
"🔓":"1f513",
"🔏":"1f50f",
"🔐":"1f510",
"🔑":"1f511",
"🔔":"1f514",
"☑":"2611",
"🔘":"1f518",
"🔖":"1f516",
"🔗":"1f517",
"🔙":"1f519",
"🔚":"1f51a",
"🔛":"1f51b",
"🔜":"1f51c",
"🔝":"1f51d",
" ":"2003",
" ":"2002",
" ":"2005",
"✅":"2705",
"✊":"270a",
"✋":"270b",
"✌":"270c",
"👊":"1f44a",
"👍":"1f44d",
"☝":"261d",
"👆":"1f446",
"👇":"1f447",
"👈":"1f448",
"👉":"1f449",
"👋":"1f44b",
"👏":"1f44f",
"👌":"1f44c",
"👎":"1f44e",
"👐":"1f450",
"":"2600",
"":"2601",
"":"2614",
"":"26c4",
"":"26a1",
"":"1f300",
"[霧]":"1f301",
"":"1f302",
"":"1f30c",
"":"1f304",
"":"1f305",
"":"1f306",
"":"1f307",
"":"1f308",
"[雪結晶]":"2744",
"":"26c5",
"":"1f30a",
"[火山]":"1f30b",
"[地球]":"1f30f",
"●":"1f311",
"":"1f31b",
"○":"1f315",
"":"1f31f",
"☆彡":"1f320",
"":"1f550",
"":"1f551",
"":"1f552",
"":"1f553",
"":"1f554",
"":"1f555",
"":"1f556",
"":"1f557",
"":"1f558",
"":"23f0",
"":"1f55a",
"":"1f55b",
"[腕時計]":"231a",
"[砂時計]":"23f3",
"":"2648",
"":"2649",
"":"264a",
"":"264b",
"":"264c",
"":"264d",
"":"264e",
"":"264f",
"":"2650",
"":"2651",
"":"2652",
"":"2653",
"":"26ce",
"":"1f33f",
"":"1f337",
"":"1f341",
"":"1f338",
"":"1f339",
"":"1f342",
"":"1f343",
"":"1f33a",
"":"1f33c",
"":"1f334",
"":"1f335",
"":"1f33e",
"[とうもろこし]":"1f33d",
"[キノコ]":"1f344",
"[栗]":"1f330",
"[さくらんぼ]":"1f352",
"[バナナ]":"1f34c",
"":"1f34f",
"":"1f34a",
"":"1f353",
"":"1f349",
"":"1f345",
"":"1f346",
"[メロン]":"1f348",
"[パイナップル]":"1f34d",
"[ブドウ]":"1f347",
"[モモ]":"1f351",
"":"1f440",
"":"1f442",
"":"1f443",
"":"1f444",
"":"1f61d",
"":"1f484",
"":"1f485",
"":"1f486",
"":"1f487",
"":"1f488",
"〓":"2005",
"":"1f466",
"":"1f467",
"":"1f468",
"":"1f469",
"[家族]":"1f46a",
"":"1f46b",
"":"1f46e",
"":"1f46f",
"[花嫁]":"1f470",
"":"1f471",
"":"1f472",
"":"1f473",
"":"1f474",
"":"1f475",
"":"1f476",
"":"1f477",
"":"1f478",
"[なまはげ]":"1f479",
"[天狗]":"1f47a",
"":"1f47b",
"":"1f47c",
"":"1f47d",
"":"1f47e",
"":"1f47f",
"":"1f480",
"":"1f481",
"":"1f482",
"":"1f483",
"[カタツムリ]":"1f40c",
"":"1f40d",
"":"1f40e",
"":"1f414",
"":"1f417",
"":"1f42b",
"":"1f418",
"":"1f428",
"":"1f412",
"":"1f411",
"":"1f419",
"":"1f41a",
"":"1f41b",
"[アリ]":"1f41c",
"[ミツバチ]":"1f41d",
"[てんとう虫]":"1f41e",
"":"1f420",
"":"1f3a3",
"[カメ]":"1f422",
"":"1f423",
"":"1f426",
"":"1f427",
"":"1f436",
"":"1f42c",
"":"1f42d",
"":"1f42f",
"":"1f431",
"":"1f433",
"":"1f434",
"":"1f435",
"":"1f43d",
"":"1f43b",
"":"1f439",
"":"1f43a",
"":"1f42e",
"":"1f430",
"":"1f438",
"":"1f463",
"[辰]":"1f432",
"[パンダ]":"1f43c",
"":"1f620",
"":"1f64d",
"":"1f632",
"":"1f61e",
"":"1f62b",
"":"1f630",
"":"1f612",
"":"1f63b",
"":"1f63c",
"":"1f61c",
"":"1f60a",
"":"1f63d",
"":"1f61a",
"":"1f637",
"":"1f633",
"":"1f63a",
"":"1f605",
"":"1f60c",
"":"1f639",
"":"263a",
"":"1f604",
"":"1f63f",
"":"1f62d",
"":"1f628",
"":"1f64e",
"":"1f4ab",
"":"1f631",
"":"1f62a",
"":"1f60f",
"":"1f613",
"":"1f625",
"":"1f609",
"":"1f645",
"":"1f646",
"":"1f647",
"(/_＼)":"1f648",
"(・×・)":"1f64a",
"|(・×・)|":"1f649",
"":"270b",
"":"1f64c",
"":"1f64f",
"":"1f3e1",
"":"1f3e2",
"":"1f3e3",
"":"1f3e5",
"":"1f3e6",
"":"1f3e7",
"":"1f3e8",
"":"1f3e9",
"":"1f3ea",
"":"1f3eb",
"":"26ea",
"":"26f2",
"":"1f3ec",
"":"1f3ef",
"":"1f3f0",
"":"1f3ed",
"":"1f6a2",
"":"1f376",
"":"1f5fb",
"":"1f5fc",
"":"1f5fd",
"[日本地図]":"1f5fe",
"[モアイ]":"1f5ff",
"":"1f45f",
"":"1f460",
"":"1f461",
"":"1f462",
"[メガネ]":"1f453",
"":"1f45a",
"[ジーンズ]":"1f456",
"":"1f451",
"":"1f454",
"":"1f452",
"":"1f457",
"":"1f458",
"":"1f459",
"[財布]":"1f45b",
"":"1f45c",
"[ふくろ]":"1f45d",
"":"1f4b5",
"":"1f4b1",
"":"1f4c8",
"[カード]":"1f4b3",
"￥":"1f4b4",
"[飛んでいくお金]":"1f4b8",
"":"1f1e81f1f3",
"":"1f1e91f1ea",
"":"1f1ea1f1f8",
"":"1f1eb1f1f7",
"":"1f1ec1f1e7",
"":"1f1ee1f1f9",
"":"1f1ef1f1f5",
"":"1f1f01f1f7",
"":"1f1f71f1fa",
"":"1f1fa1f1f8",
"":"1f525",
"[懐中電灯]":"1f526",
"[レンチ]":"1f527",
"":"1f528",
"[ネジ]":"1f529",
"[包丁]":"1f52a",
"":"1f52b",
"":"1f52f",
"":"1f530",
"":"1f531",
"":"1f489",
"":"1f48a",
"":"1f170",
"":"1f171",
"":"1f18e",
"":"1f17e",
"":"1f380",
"":"1f4e6",
"":"1f382",
"":"1f384",
"":"1f385",
"":"1f38c",
"":"1f386",
"":"1f388",
"":"1f389",
"":"1f38d",
"":"1f38e",
"":"1f393",
"":"1f392",
"":"1f38f",
"":"1f387",
"":"1f390",
"":"1f383",
"[オメデトウ]":"1f38a",
"[七夕]":"1f38b",
"":"1f391",
"[ポケベル]":"1f4df",
"":"1f4de",
"":"1f4f1",
"":"1f4f2",
"":"1f4d1",
"":"1f4e0",
"":"1f4e7",
"":"1f4eb",
"":"1f4ee",
"[新聞]":"1f4f0",
"":"1f4e2",
"":"1f4e3",
"":"1f4e1",
"[送信BOX]":"1f4e4",
"[受信BOX]":"1f4e5",
"[ABCD]":"1f520",
"[abcd]":"1f521",
"[1234]":"1f522",
"[記号]":"1f523",
"[ABC]":"1f524",
"[ペン]":"2712",
"":"1f4ba",
"":"1f4bb",
"[クリップ]":"1f4ce",
"":"1f4bc",
"":"1f4be",
"":"1f4bf",
"":"1f4c0",
"":"2702",
"[画びょう]":"1f4cc",
"[カレンダー]":"1f4c6",
"[フォルダ]":"1f4c2",
"":"1f4d2",
"[名札]":"1f4db",
"[スクロール]":"1f4dc",
"[グラフ]":"1f4c9",
"[定規]":"1f4cf",
"[三角定規]":"1f4d0",
"":"26be",
"":"26f3",
"":"1f3be",
"":"26bd",
"":"1f3bf",
"":"1f3c0",
"":"1f3c1",
"[スノボ]":"1f3c2",
"":"1f3c3",
"":"1f3c4",
"":"1f3c6",
"":"1f3c8",
"":"1f3ca",
"":"1f683",
"":"24c2",
"":"1f684",
"":"1f685",
"":"1f697",
"":"1f699",
"":"1f68c",
"":"1f68f",
"":"2708",
"":"26f5",
"":"1f689",
"":"1f680",
"":"1f6a4",
"":"1f695",
"":"1f69a",
"":"1f692",
"":"1f691",
"":"1f6a8",
"":"26fd",
"":"1f17f",
"":"1f6a5",
"":"26d4",
"":"2668",
"":"26fa",
"":"1f3a1",
"":"1f3a2",
"":"1f3a4",
"":"1f4f9",
"":"1f3a6",
"":"1f3a7",
"":"1f3a8",
"":"1f3ad",
"[イベント]":"1f3aa",
"":"1f3ab",
"":"1f3ac",
"[ゲーム]":"1f3ae",
"":"1f004",
"":"1f3af",
"":"1f3b0",
"":"1f3b1",
"[サイコロ]":"1f3b2",
"[ボーリング]":"1f3b3",
"[花札]":"1f3b4",
"[ジョーカー]":"1f0cf",
"":"1f3b5",
"":"1f3bc",
"":"1f3b7",
"":"1f3b8",
"[ピアノ]":"1f3b9",
"":"1f3ba",
"[バイオリン]":"1f3bb",
"":"303d",
"":"1f4f7",
"":"1f4fa",
"":"1f4fb",
"":"1f4fc",
"":"1f48b",
"":"1f48c",
"":"1f48d",
"":"1f48e",
"":"1f48f",
"":"1f490",
"":"1f491",
"":"1f492",
"":"1f51e",
"":"a9",
"":"ae",
"":"2122",
"[ｉ]":"2139",
"":"2320e3",
"":"3120e3",
"":"3220e3",
"":"3320e3",
"":"3420e3",
"":"3520e3",
"":"3620e3",
"":"3720e3",
"":"3820e3",
"":"3920e3",
"":"3020e3",
"[10]":"1f51f",
"":"1f4f6",
"":"1f4f3",
"":"1f4f4",
"":"1f354",
"":"1f359",
"":"1f370",
"":"1f35c",
"":"1f35e",
"":"1f373",
"":"1f366",
"":"1f35f",
"":"1f361",
"":"1f358",
"":"1f35a",
"":"1f35d",
"":"1f35b",
"":"1f362",
"":"1f363",
"":"1f371",
"":"1f372",
"":"1f367",
"[肉]":"1f356",
"[なると]":"1f365",
"[やきいも]":"1f360",
"[ピザ]":"1f355",
"[チキン]":"1f357",
"[アイスクリーム]":"1f368",
"[ドーナツ]":"1f369",
"[クッキー]":"1f36a",
"[チョコ]":"1f36b",
"[キャンディ]":"1f36d",
"[プリン]":"1f36e",
"[ハチミツ]":"1f36f",
"[エビフライ]":"1f364",
"":"1f374",
"":"2615",
"":"1f379",
"":"1f37a",
"":"1f375",
"":"1f37b",
"":"2934",
"":"2935",
"":"2196",
"":"2199",
"⇔":"2194",
"↑↓":"1f503",
"":"2b06",
"":"2b07",
"":"27a1",
"":"1f519",
"":"25b6",
"":"25c0",
"":"23e9",
"":"23ea",
"▲":"1f53c",
"▼":"1f53d",
"":"2b55",
"":"2716",
"":"2757",
"！？":"2049",
"！！":"203c",
"":"2753",
"":"2754",
"":"2755",
"～":"27b0",
"":"27bf",
"":"2764",
"":"1f49e",
"":"1f494",
"":"1f497",
"":"1f498",
"":"1f499",
"":"1f49a",
"":"1f49b",
"":"1f49c",
"":"1f49d",
"":"1f49f",
"":"2665",
"":"2660",
"":"2666",
"":"2663",
"":"1f6ac",
"":"1f6ad",
"":"267f",
"[旗]":"1f6a9",
"":"26a0",
"":"1f6b2",
"":"1f6b6",
"":"1f6b9",
"":"1f6ba",
"":"1f6c0",
"":"1f6bb",
"":"1f6bd",
"":"1f6be",
"":"1f6bc",
"[ドア]":"1f6aa",
"[禁止]":"1f6ab",
"[チェックマーク]":"2705",
"[CL]":"1f191",
"":"1f192",
"[FREE]":"1f193",
"":"1f194",
"":"1f195",
"[NG]":"1f196",
"":"1f197",
"[SOS]":"1f198",
"":"1f199",
"":"1f19a",
"":"1f201",
"":"1f202",
"[禁]":"1f232",
"":"1f233",
"[合]":"1f234",
"":"1f235",
"":"1f236",
"":"1f21a",
"":"1f237",
"":"1f238",
"":"1f239",
"":"1f22f",
"":"1f23a",
"":"3299",
"":"3297",
"":"1f250",
"[可]":"1f251",
"[＋]":"2795",
"[－]":"2796",
"[÷]":"2797",
"":"1f4a1",
"":"1f4a2",
"":"1f4a3",
"":"1f4a4",
"[ドンッ]":"1f4a5",
"":"1f4a7",
"":"1f4a8",
"":"1f4a9",
"":"1f4aa",
"[フキダシ]":"1f4ac",
"":"2747",
"":"2734",
"":"2733",
"":"1f534",
"":"25fc",
"":"1f539",
"":"2b50",
"[花丸]":"1f4ae",
"[100点]":"1f4af",
"←┘":"21a9",
"└→":"21aa",
"":"1f50a",
"[電池]":"1f50b",
"[コンセント]":"1f50c",
"":"1f50e",
"":"1f510",
"":"1f513",
"":"1f511",
"":"1f514",
"[ラジオボタン]":"1f518",
"[ブックマーク]":"1f516",
"[リンク]":"1f517",
"[end]":"1f51a",
"[ON]":"1f51b",
"[SOON]":"1f51c",
"":"1f51d",
"":"270a",
"":"270c",
"":"1f44a",
"":"1f44d",
"":"261d",
"":"1f446",
"":"1f447",
"":"1f448",
"":"1f449",
"":"1f44b",
"":"1f44f",
"":"1f44c",
"":"1f44e",
"":"1f450"
},e={
"/微笑":"0",
"/撇嘴":"1",
"/色":"2",
"/发呆":"3",
"/得意":"4",
"/流泪":"5",
"/害羞":"6",
"/闭嘴":"7",
"/睡":"8",
"/大哭":"9",
"/尴尬":"10",
"/发怒":"11",
"/调皮":"12",
"/呲牙":"13",
"/惊讶":"14",
"/难过":"15",
"/酷":"16",
"/冷汗":"17",
"/抓狂":"18",
"/吐":"19",
"/偷笑":"20",
"/可爱":"21",
"/白眼":"22",
"/傲慢":"23",
"/饥饿":"24",
"/困":"25",
"/惊恐":"26",
"/流汗":"27",
"/憨笑":"28",
"/大兵":"29",
"/奋斗":"30",
"/咒骂":"31",
"/疑问":"32",
"/嘘":"33",
"/晕":"34",
"/折磨":"35",
"/衰":"36",
"/骷髅":"37",
"/敲打":"38",
"/再见":"39",
"/擦汗":"40",
"/抠鼻":"41",
"/鼓掌":"42",
"/糗大了":"43",
"/坏笑":"44",
"/左哼哼":"45",
"/右哼哼":"46",
"/哈欠":"47",
"/鄙视":"48",
"/委屈":"49",
"/快哭了":"50",
"/阴险":"51",
"/亲亲":"52",
"/吓":"53",
"/可怜":"54",
"/菜刀":"55",
"/西瓜":"56",
"/啤酒":"57",
"/篮球":"58",
"/乒乓":"59",
"/咖啡":"60",
"/饭":"61",
"/猪头":"62",
"/玫瑰":"63",
"/凋谢":"64",
"/示爱":"65",
"/爱心":"66",
"/心碎":"67",
"/蛋糕":"68",
"/闪电":"69",
"/炸弹":"70",
"/刀":"71",
"/足球":"72",
"/瓢虫":"73",
"/便便":"74",
"/月亮":"75",
"/太阳":"76",
"/礼物":"77",
"/拥抱":"78",
"/强":"79",
"/弱":"80",
"/握手":"81",
"/胜利":"82",
"/抱拳":"83",
"/勾引":"84",
"/拳头":"85",
"/差劲":"86",
"/爱你":"87",
"/NO":"88",
"/OK":"89",
"/爱情":"90",
"/飞吻":"91",
"/跳跳":"92",
"/发抖":"93",
"/怄火":"94",
"/转圈":"95",
"/磕头":"96",
"/回头":"97",
"/跳绳":"98",
"/挥手":"99",
"/激动":"100",
"/街舞":"101",
"/献吻":"102",
"/左太极":"103",
"/右太极":"104",
"/::)":"0",
"/::~":"1",
"/::B":"2",
"/::|":"3",
"/:8-)":"4",
"/::<":"5",
"/::$":"6",
"/::X":"7",
"/::Z":"8",
"/::(":"9",
"/::'(":"9",
"/::-|":"10",
"/::@":"11",
"/::P":"12",
"/::D":"13",
"/::O":"14",
"/::(":"15",
"/::+":"16",
"/:--b":"17",
"/::Q":"18",
"/::T":"19",
"/:,@P":"20",
"/:,@-D":"21",
"/::d":"22",
"/:,@o":"23",
"/::g":"24",
"/:|-)":"25",
"/::!":"26",
"/::L":"27",
"/::>":"28",
"/::,@":"29",
"/:,@f":"30",
"/::-S":"31",
"/:?":"32",
"/:,@x":"33",
"/:,@@":"34",
"/::8":"35",
"/:,@!":"36",
"/:!!!":"37",
"/:xx":"38",
"/:bye":"39",
"/:wipe":"40",
"/:dig":"41",
"/:handclap":"42",
"/:&-(":"43",
"/:B-)":"44",
"/:<@":"45",
"/:@>":"46",
"/::-O":"47",
"/:>-|":"48",
"/:P-(":"49",
"/::'|":"50",
"/:X-)":"51",
"/::*":"52",
"/:@x":"53",
"/:8*":"54",
"/:pd":"55",
"/:<W>":"56",
"/:beer":"57",
"/:basketb":"58",
"/:oo":"59",
"/:coffee":"60",
"/:eat":"61",
"/:pig":"62",
"/:rose":"63",
"/:fade":"64",
"/:showlove":"65",
"/:heart":"66",
"/:break":"67",
"/:cake":"68",
"/:li":"69",
"/:bome":"70",
"/:kn":"71",
"/:footb":"72",
"/:ladybug":"73",
"/:shit":"74",
"/:moon":"75",
"/:sun":"76",
"/:gift":"77",
"/:hug":"78",
"/:strong":"79",
"/:weak":"80",
"/:share":"81",
"/:v":"82",
"/:@)":"83",
"/:jj":"84",
"/:@@":"85",
"/:bad":"86",
"/:lvu":"87",
"/:no":"88",
"/:ok":"89",
"/:love":"90",
"/:<L>":"91",
"/:jump":"92",
"/:shake":"93",
"/:<O>":"94",
"/:circle":"95",
"/:kotow":"96",
"/:turn":"97",
"/:skip":"98",
"/:oY":"99",
"/:#-0":"100",
"/:hiphot":"101",
"/:kiss":"102",
"/:<&":"103",
"/:&>":"104"
},b='<span class="emoji emoji%s"></span>',c=wx.resPath+"/mpres/htmledition/images/icon/emotion/",d='<img src="'+c+'%s.gif" alt="mo-%s" width="24" height="24">';
String.prototype.emoji=function(){
var f=this.toString();
for(var c in a)for(;-1!=f.indexOf(c);)f=f.replace(c,b.sprintf(a[c]));
for(var c in e)for(;-1!=f.indexOf(c);)f=f.replace(c,d.sprintf(e[c],c.substr(1)));
return f;
};
});define("common/wx/ban.js",["tpl/ban/highlight_box.html.js","tpl/ban/page_msg.html.js","common/wx/dialog.js"],function(e,a,i){
"use strict";
var n=e("tpl/ban/highlight_box.html.js"),o=e("tpl/ban/page_msg.html.js"),t=e("common/wx/dialog.js"),p={
"mass-send":{
func_id:1,
name:"群发功能"
},
copyright:{
func_id:2,
name:"原创功能"
},
reward:{
func_id:3,
name:"赞赏功能"
},
seller:{
func_id:4,
name:"流量主功能"
},
comment:{
func_id:5,
name:"留言功能"
},
follow:{
func_id:6,
name:"被关注"
},
search:{
func_id:7,
name:"被搜索"
},
outlink:{
func_id:8,
name:"外链功能"
},
share:{
func_id:9,
name:"文章分享至朋友圈可见"
},
reply:{
func_id:10,
name:"自动回复功能",
highlight:"已禁用自动回复|你的帐号{=reason}，已被{forever}屏蔽自动回复功能{date}，期间用户将不会收到自动回复消息。",
hide:"all"
},
menu:{
func_id:11,
name:"自定义菜单功能",
highlight:"已禁用自定义菜单|你的帐号{=reason}，已被{forever}屏蔽自定义菜单功能{date}，期间自定义菜单将不可见。",
hide:"all"
},
"single-send":{
func_id:12,
name:"聊天功能",
pagemsg:"你的帐号{=reason}，已被{forever}屏蔽聊天功能{date}，期间将不可和粉丝互动聊天。"
},
preview:{
func_id:13,
name:"消息预览功能",
dialogmsg:"你的帐号{=reason}，已被{forever}屏蔽消息预览功能{date}，期间消息预览功能将不可用。"
},
"jssdk-share":{
func_id:14,
name:"JS-SDK分享接口"
},
template:{
func_id:15,
name:"模板消息接口"
},
"customer-service":{
func_id:16,
name:"客服接口"
},
"source-url":{
func_id:17,
name:"原文链接功能"
},
"outer-url":{
func_id:18,
name:"图文编辑外链功能"
}
},r=[{
illegal_reason_id:3,
reason_id:1e4,
reason_name:"涉嫌违规",
reason_type:0,
reason_description:"涉嫌违规",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=32&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:3
},{
illegal_reason_id:3,
reason_id:90004,
reason_name:"滥用原创声明",
reason_type:0,
reason_description:"涉嫌滥用原创声明功能",
reason_rule:"《微信公众平台运营规范》3.6条规定",
wap_url:"",
pc_url:"",
level:3
},{
illegal_reason_id:4,
reason_id:90005,
reason_name:"滥用赞赏",
reason_type:0,
reason_description:"涉嫌滥用赞赏功能",
reason_rule:"《微信公众平台运营规范》3.7条规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_7",
level:3
},{
illegal_reason_id:10,
reason_id:10001,
reason_name:"垃圾广告",
reason_type:0,
reason_description:"涉嫌发布垃圾广告",
reason_rule:"《微信公众平台运营规范》4.8条规定-广告类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=24&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_8",
level:2
},{
illegal_reason_id:11,
reason_id:20001,
reason_name:"政治敏感",
reason_type:0,
reason_description:"涉嫌违反相关法律法规和政策",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=32&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:3
},{
illegal_reason_id:12,
reason_id:20002,
reason_name:"色情",
reason_type:0,
reason_description:"涉及低俗、性暗示或色情信息",
reason_rule:"《微信公众平台运营规范》4.2条规定-色情及色情擦边类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=18&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_2",
level:1
},{
illegal_reason_id:13,
reason_id:20004,
reason_name:"社会事件",
reason_type:0,
reason_description:"涉嫌违反相关法律法规和政策",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:3
},{
illegal_reason_id:14,
reason_id:20006,
reason_name:"违法犯罪",
reason_type:0,
reason_description:"涉嫌违反相关法律法规和政策",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=32&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:15,
reason_id:20008,
reason_name:"欺诈",
reason_type:0,
reason_description:"涉嫌欺诈",
reason_rule:"《微信公众平台运营规范》4.8.1条规定-欺诈虚假广告类",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=24&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_8",
level:1
},{
illegal_reason_id:16,
reason_id:20012,
reason_name:"低俗",
reason_type:0,
reason_description:"涉及低俗、性暗示或色情信息",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=32&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:2
},{
illegal_reason_id:18,
reason_id:20013,
reason_name:"冒名侵权",
reason_type:0,
reason_description:"涉嫌侵犯他人合法权益",
reason_rule:"《微信公众平台运营规范》4.1条规定-侵权或侵犯隐私类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:1
},{
illegal_reason_id:21,
reason_id:20106,
reason_name:"骚扰",
reason_type:0,
reason_description:"涉及骚扰信息",
reason_rule:"《微信公众平台运营规范》4.10条规定-搔扰类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=26&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_10",
level:3
},{
illegal_reason_id:22,
reason_id:21e3,
reason_name:"默认",
reason_type:0,
reason_description:"涉嫌违规",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:3
},{
illegal_reason_id:23,
reason_id:90001,
reason_name:"侵犯隐私",
reason_type:0,
reason_description:"涉嫌侵犯他人隐私",
reason_rule:"《微信公众平台运营规范》4.1条规定-侵权或侵犯隐私类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:3
},{
illegal_reason_id:35,
reason_id:20104,
reason_name:"造遥",
reason_type:0,
reason_description:"涉嫌造谣或传谣",
reason_rule:"《微信公众平台运营规范》4.9条规定-谣言类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=25&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_9",
level:2
},{
illegal_reason_id:36,
reason_id:20105,
reason_name:"诱导分享",
reason_type:0,
reason_description:"涉嫌诱导分享",
reason_rule:"《微信公众平台运营规范》3.3.1条规定-诱导分享",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=13&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_3",
level:2
},{
illegal_reason_id:40,
reason_id:90002,
reason_name:"抄袭",
reason_type:0,
reason_description:"涉嫌抄袭他人内容",
reason_rule:"《微信公众平台运营规范》4.1.2条规定-内容侵权",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:3
},{
illegal_reason_id:41,
reason_id:90003,
reason_name:"诱导关注 ",
reason_type:0,
reason_description:"涉嫌诱导关注",
reason_rule:"《微信公众平台运营规范》3.3.2条规定-诱导关注",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=13&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_3",
level:2
},{
illegal_reason_id:42,
reason_id:1,
reason_name:"默认",
reason_type:1,
reason_description:"其他",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:43,
reason_id:2,
reason_name:"政治敏感",
reason_type:1,
reason_description:"涉嫌违反相关法律法规",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=32&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:44,
reason_id:3,
reason_name:"色情",
reason_type:1,
reason_description:"涉及低俗或色情信息",
reason_rule:"《微信公众平台运营规范》4.2条规定-色情及色情擦边类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=18&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_2",
level:1
},{
illegal_reason_id:45,
reason_id:4,
reason_name:"虚假认证",
reason_type:1,
reason_description:"涉嫌虚假认证",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:46,
reason_id:5,
reason_name:"侵权",
reason_type:1,
reason_description:"涉嫌侵犯他人合法权益",
reason_rule:"《微信公众平台运营规范》4.1条规定-侵权或侵犯隐私类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:1
},{
illegal_reason_id:47,
reason_id:4,
reason_name:"政治敏感",
reason_type:2,
reason_description:"涉嫌违反相关法律法规",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=32&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:3
},{
illegal_reason_id:48,
reason_id:1,
reason_name:"色情",
reason_type:2,
reason_description:"涉嫌低俗或色情",
reason_rule:"《微信公众平台运营规范》4.2条规定-色情及色情擦边类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=18&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_2",
level:1
},{
illegal_reason_id:49,
reason_id:3,
reason_name:"欺诈",
reason_type:2,
reason_description:"涉嫌欺诈",
reason_rule:"《微信公众平台运营规范》4.8.1条规定-欺诈虚假广告类",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=24&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_8",
level:1
},{
illegal_reason_id:50,
reason_id:5,
reason_name:"诱导分享",
reason_type:2,
reason_description:"涉嫌诱导分享",
reason_rule:"《微信公众平台运营规范》3.3.1条规定-诱导分享",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=13&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_3",
level:2
},{
illegal_reason_id:51,
reason_id:19,
reason_name:"诱导关注",
reason_type:2,
reason_description:"涉嫌诱导关注",
reason_rule:"《微信公众平台运营规范》3.3.2条规定-诱导关注",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=13&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_3",
level:2
},{
illegal_reason_id:52,
reason_id:7,
reason_name:"侵犯隐私",
reason_type:2,
reason_description:"涉嫌侵犯隐私",
reason_rule:"《微信公众平台运营规范》4.1.2条规定-内容侵权",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:3
},{
illegal_reason_id:53,
reason_id:6,
reason_name:"侵权",
reason_type:2,
reason_description:"涉嫌侵犯他人合法权益",
reason_rule:"《微信公众平台运营规范》4.1条规定-侵权或侵犯隐私类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:1
},{
illegal_reason_id:54,
reason_id:11,
reason_name:"外挂",
reason_type:2,
reason_description:"涉嫌使用外挂",
reason_rule:"《微信公众平台运营规范》3.1条规定－使用外挂行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_1",
level:1
},{
illegal_reason_id:55,
reason_id:8,
reason_name:"造遥",
reason_type:2,
reason_description:"涉嫌造谣或传谣",
reason_rule:"《微信公众平台运营规范》4.9条规定-谣言类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=25&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_9",
level:2
},{
illegal_reason_id:56,
reason_id:12,
reason_name:"骚扰",
reason_type:2,
reason_description:"涉嫌骚扰他人",
reason_rule:"《微信公众平台运营规范》4.10条规定-搔扰类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_10",
level:3
},{
illegal_reason_id:57,
reason_id:14,
reason_name:"刷粉",
reason_type:2,
reason_description:"涉嫌刷粉",
reason_rule:"《微信公众平台运营规范》3.2条规定－刷粉行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_2",
level:3
},{
illegal_reason_id:58,
reason_id:13,
reason_name:"互推",
reason_type:2,
reason_description:"涉嫌互推",
reason_rule:"《微信公众平台运营规范》3.2条规定－刷粉行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_2",
level:2
},{
illegal_reason_id:59,
reason_id:16,
reason_name:"抄袭",
reason_type:2,
reason_description:"涉嫌抄袭",
reason_rule:"《微信公众平台运营规范》4.1.2条规定-内容侵权",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:3
},{
illegal_reason_id:60,
reason_id:9,
reason_name:"垃圾广告",
reason_type:2,
reason_description:"涉嫌发送垃圾广告",
reason_rule:"《微信公众平台运营规范》4.8条规定-广告类内容",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=24&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_8",
level:2
},{
illegal_reason_id:61,
reason_id:10,
reason_name:"恶意注册",
reason_type:2,
reason_description:"涉嫌恶意注册",
reason_rule:"《微信公众平台运营规范》1条规定－ 注册规范",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot1",
level:1
},{
illegal_reason_id:62,
reason_id:17,
reason_name:"恶意投诉",
reason_type:2,
reason_description:"涉嫌恶意投诉",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:2
},{
illegal_reason_id:63,
reason_id:18,
reason_name:"违规分销",
reason_type:2,
reason_description:"涉嫌多级分销",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:64,
reason_id:90007,
reason_name:"违规声明原创",
reason_type:0,
reason_description:"涉嫌违规使用原创声明功能",
reason_rule:"微信公众平台运营规范》3.6条规定-滥用原创声明功能",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_6",
level:1
},{
illegal_reason_id:65,
reason_id:90011,
reason_name:"刷粉",
reason_type:0,
reason_description:"涉嫌刷粉",
reason_rule:"微信公众平台运营规范》3.2条规定－刷粉行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_2",
level:1
},{
illegal_reason_id:66,
reason_id:90010,
reason_name:"侵犯名誉/商誉/隐私/肖像",
reason_type:0,
reason_description:"涉嫌侵犯名誉/商誉/隐私/肖像",
reason_rule:"《微信公众平台运营规范》4.1.2条规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:1
},{
illegal_reason_id:69,
reason_id:90013,
reason_name:"滥用模版消息接口",
reason_type:0,
reason_description:"涉嫌滥用模版消息接口",
reason_rule:"《微信公众平台运营规范》3.9条规定-滥用模板消息接口行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=33&t=operation/faq_index&nettype=WIFI&fontScale=100&from=singlemessage&isappinstalled=0#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_9",
level:1
},{
illegal_reason_id:70,
reason_id:90012,
reason_name:"滥用客服消息",
reason_type:0,
reason_description:"涉嫌滥用客服消息",
reason_rule:"《微信公众平台运营规范》3.10条规定-滥用客服消息行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=34&t=operation/faq_index&nettype=WIFI&fontScale=100&from=singlemessage&isappinstalled=0#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_10",
level:1
},{
illegal_reason_id:71,
reason_id:90008,
reason_name:"互推",
reason_type:0,
reason_description:"涉嫌互推",
reason_rule:"《微信公众平台运营规范》3.2条规定－刷粉行为",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot3_2",
level:2
},{
illegal_reason_id:72,
reason_id:90014,
reason_name:"广告恶意点击",
reason_type:0,
reason_description:"恶意点击公众号文章底部广告",
reason_rule:"《广告展示违规行为处理细则》-作弊行为",
wap_url:"http://mp.weixin.qq.com/promotion/readtemplate?t=faq/ad_host_faq_5_tmpl#5dot4",
pc_url:"http://mp.weixin.qq.com/promotion/readtemplate?t=faq/ad_host_faq_5_tmpl#5dot4",
level:1
},{
illegal_reason_id:73,
reason_id:20011,
reason_name:"暴力血腥",
reason_type:0,
reason_description:"涉嫌发布暴力信息",
reason_rule:"《微信公众平台运营规范》4.3条规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=19&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?token=2010733288&t=business/faq_operation_tmpl&type=info#3dot4_3",
level:1
},{
illegal_reason_id:74,
reason_id:90016,
reason_name:"侵犯知识产权",
reason_type:0,
reason_description:"涉嫌侵犯他人版权/商标/专利等知识产权",
reason_rule:"《微信公众平台运营规范》4.1.2条规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:1
},{
illegal_reason_id:75,
reason_id:90009,
reason_name:"其他侵权",
reason_type:0,
reason_description:"涉嫌侵犯他人合法权益",
reason_rule:"《微信公众平台运营规范》4.1条规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=17&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN#3dot4_1",
level:1
},{
illegal_reason_id:76,
reason_id:90017,
reason_name:"恶意投诉",
reason_type:0,
reason_description:"涉嫌恶意投诉他人",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:77,
reason_id:25,
reason_name:"假货",
reason_type:2,
reason_description:"制作/售卖/传播假货",
reason_rule:"《微信公众平台运营规范》4.1条规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:78,
reason_id:26,
reason_name:"网赚刷单",
reason_type:2,
reason_description:"诱导用户转发文章、下载app等",
reason_rule:"《微信公众平台运营规范》禁止诱导类行为的规定",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:85,
reason_id:90018,
reason_name:"阅读原文违规",
reason_type:0,
reason_description:"涉嫌阅读原文跳转至恶意链接",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:86,
reason_id:90019,
reason_name:"违反微信链接内容管理规范",
reason_type:0,
reason_description:"涉嫌违反微信链接内容管理规范",
reason_rule:"《微信外部链接内容管理规范》",
wap_url:"http://weixin.qq.com/cgi-bin/readtemplate?t=weixin_external_links_content_management_specification",
pc_url:"http://weixin.qq.com/cgi-bin/readtemplate?t=weixin_external_links_content_management_specification",
level:1
},{
illegal_reason_id:88,
reason_id:90020,
reason_name:"无证经营",
reason_type:0,
reason_description:"涉嫌无证经营",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
},{
illegal_reason_id:90,
reason_id:90021,
reason_name:"多级分销",
reason_type:0,
reason_description:"涉嫌多级分销经营行为",
reason_rule:"《微信公众平台运营规范》",
wap_url:"http://mp.weixin.qq.com/mp/opshowpage?action=oplaw&id=1&t=operation/faq_index#wechat_redirect",
pc_url:"https://mp.weixin.qq.com/cgi-bin/readtemplate?t=business/faq_operation_tmpl&type=info&lang=zh_CN",
level:1
}],_=function(e){
return e.getFullYear()+"/"+(e.getMonth()+1)+"/"+e.getDate();
},l=function(e,a){
for(var i=$(".main_bd"),p=0,l=0;l<r.length;l++)r[l].reason_id==e.reason_id&&(p=l);
var s={};
if(s.reason='<a href="'+(r[p].pc_url?r[p].pc_url:r[0].pc_url)+'">'+r[p].reason_description+"</a>",
e.ban_time===e.unlock_time?(s.forever="永久",s.date=""):(s.forever="",s.date="至"+_(new Date(1e3*e.unlock_time))),
a.hide&&("all"===a.hide?i.children().hide():$(a.hide).hide()),a.highlight){
a.highlight=template.compile(a.highlight)(s);
var m={
title:a.highlight.split("|")[0],
desc:template.compile(a.highlight.split("|")[1])()
};
$(template.compile(n)(m)).prependTo(i);
}
if(a.pagemsg){
var c={
content:template.compile(a.pagemsg)(s)
};
0==i.find(".ban_page_msg").length&&$(template.compile(o)(c)).prependTo(i);
}
return a.dialogmsg&&t.show({
type:"warn",
title:"提示",
msg:"能力封禁提示|"+template.compile(a.dialogmsg)(s),
buttons:[{
text:"确定",
type:"primary",
click:function(){
this.remove();
}
}]
}),!1;
},s=function(e,a,i){
var n=!0;
if(!p[a])return!0;
for(var o=0,t=e.length;t>o;o++)if(e[o].func_id==p[a].func_id){
var r=l(e[o],p[a]);
n=r&&n;
}
return!n&&i&&"function"==typeof i&&i(),n;
};
s.getReason=function(e){
if("default"==e)return r[0];
for(var a=0;a<r.length;a++)if(r[a].reason_id==e)return r[a];
return r[0];
},s.getTypeName=function(e){
for(var a in p)if(p[a].func_id==e)return p[a].name;
},i.exports=s;
});define("cardticket/parse_data.js",["cardticket/add/member_info_flag.js"],function(e){
"use strict";
function _(e){
var _=c[e.card_type]||e.card_type;
switch(_+=""){
case"2":
e=e.discount;
break;

case"1":
e=e.groupon;
break;

case"3":
e=e.gift;
break;

case"4":
e=e.cash;
break;

case"0":
e=e.general_coupon;
break;

case"10":
e=e.member_card;
break;

case"21":
e=e.scenic_ticket;
break;

case"22":
e=e.movie_ticket;
break;

default:
e=e.general_coupon||e.coupon;
}
return e?(e.type=_,e):null;
}
function t(e,_){
return"number"!=typeof e&&(e=praseFloat(e),isNaN(e))?0:(_||(_=2),parseFloat(e.toFixed(_)));
}
function i(e){
var _=/^https?:\/\/mp.weixin.qq.com\/s/,t=/^http:\/\/mp.weixin.qq.com\/bizmall\/cardshelf/,i=/^http:\/\/mp.weixin.qq.com\/bizmall\/mallshelf/;
return _.test(e)?1:t.test(e)?2:i.test(e)?3:4;
}
function s(e){
return e?(e+"").html(!1):"";
}
function o(e){
var _=e.url||"",t=e.url_type;
return 4==t?_.replace("http://",""):s(_);
}
function n(e){
var n={},e=_(e);
if(!e)return null;
a(n,e),a(n,e.base_info),n.background_pic_url=e.background_pic_url;
var r=e.base_info.date_info||{};
n.time_type=f[r.type]||r.type,1==n.time_type&&(n.begin_time=r.begin_timestamp,n.end_time=r.end_timestamp),
n.from_day=r.fixed_begin_term||0,n.fixed_term=r.fixed_term||30,n.quantity=e.base_info.sku.quantity,
n.shop_id_list=e.base_info.shop_id_list,n.location_id_list=e.base_info.location_id_list;
var c=l[n.code_type];
if(n.code_type="undefined"!=typeof c?c:n.code_type,"undefined"==typeof n.code_type&&(n.code_type=1),
n.least_cost=e.least_cost&&e.least_cost/100,n.reduce_cost=e.reduce_cost&&e.reduce_cost/100,
"0"==n.least_cost&&(n.least_cost=""),n.discount=n.discount&&(100-n.discount)/10,
n.detail=1==n.type?n.deal_detail:n.default_detail,/^http/.test(n.logo_url)||(n.logo_url=""),
n.shop_type||(n.shop_type=n.location_id_list&&n.location_id_list.length?2:3),n.auto_update_new_location&&(n.shop_type=1),
n.isnew=n.func_flag?!!(16&n.func_flag):!1,n.ispay=n.func_flag?64&n.func_flag:!1,
n.func_flag&&(n.show_in_nearby=!1),n.ispay&&(n.can_share=!0),n.ispay&&(n.detail=n.detail?n.detail.replace(/\n微信价：.*?元$/gm,""):""),
n.price=t(e.base_info.sku.price/100),n.original_price=t(e.base_info.sku.original_price/100),
1==n.create_source&&(n.isnew=!0),1==n.time_type&&n.end_time<new Date/1e3&&(n.is_expire=!0),
n.is_intercomm=16384&n.func_flag,"undefined"!=typeof e.base_info.task_info&&(n.is_from_intercomm=!0,
n.task_info=e.base_info.task_info),n.is_from_intercomm&&(n.isnew=!0),n.status=m[n.status]||n.status,
n.discount&&(n.supply_discount=!0),10==n.type){
var d=[];
if(n.promotion_url_name){
var p={
name:n.promotion_url_name,
tips:n.promotion_url_sub_title,
url:n.promotion_url
};
p.url_type=i(p.url),p.url=o(p),d=[p];
}
e.custom_cell1&&(e.custom_cell1.url_type=i(e.custom_cell1.url),e.custom_cell1.url=o(e.custom_cell1),
d.push(e.custom_cell1)),e.custom_cell2&&(e.custom_cell2.url_type=i(e.custom_cell2.url),
e.custom_cell2.url=o(e.custom_cell2),d.push(e.custom_cell2)),n.config_url=d;
var y=e.required_info||{
info_flag:0
},g=e.optional_info||{
info_flag:0
};
n.require_keywords=u.flag2info(y.info_flag),n.option_keywords=u.flag2info(g.info_flag),
n.require_self_keywords=y.field_list,n.option_self_keywords=g.field_list,n.must_activate=!n.auto_activate,
n.supply_discount&&(n.prerogative=n.prerogative.replace(/^用卡可享受.*?折优惠\n/,"")),n.quantity="--",
n.can_modify=(e.required_info?e.required_info.can_modify:!1)||(e.optional_info?e.optional_info.can_modify:!1);
}else{
var d=[];
if(n.custom_url_name){
var p={
name:n.custom_url_name,
tips:n.custom_url_sub_title,
url:n.custom_url
};
p.url_type=i(p.url),p.url=o(p),d=[p];
}
n.config_url=d;
}
var b=e.base_info;
if(10==n.type)var h=e.modify_msg_operation||{
_notexist:!0
};else var h=b.consume_msg_operation||{
_notexist:!0
};
n.msg_operation=h.url_cell||h.card_cell||{
_notexist:!0
},n.msg_operation._notexist||(n.msg_operation._type=n.msg_operation.card_id?5:i(n.msg_operation.url),
n.msg_operation.url&&(n.msg_operation.url=s(n.msg_operation.url))),n.msg_operation.endtime=n.msg_operation.end_time,
n.bonus_rule=e.bonus_rule||{},n.bonus_rule.init_bonus=n.bonus_rule.init_increase_bonus,
n.bonus_rule.cost_money_unit=n.bonus_rule.cost_money_unit&&n.bonus_rule.cost_money_unit/100,
n.bonus_rule.reduce_money=n.bonus_rule.reduce_money&&n.bonus_rule.reduce_money/100,
n.bonus_rule.least_money_to_use_bonus=n.bonus_rule.least_money_to_use_bonus&&n.bonus_rule.least_money_to_use_bonus/100,
b.sub_merchant_info&&(n.sub_merchant_id=b.sub_merchant_info.merchant_id);
var v=e.advanced_info;
if(n.use_hours=[],v){
n.is_sns_card=1==v.gen_type,n.orig_time_limit=v.time_limit||[],n.text_image_list=v.text_image_list||[],
n.time_limit=[];
var T={};
if(v.time_limit)for(var E=0;E<v.time_limit.length;E++){
var w=v.time_limit[E];
T[w.type]||(T[w.type]=!0,n.time_limit.push(w));
}
1!=n.create_source&&v.time_limit&&v.time_limit.length&&v.time_limit[0].end_hour&&(n.use_hours.push(v.time_limit[0]),
v.time_limit.length>1&&v.time_limit[0].type==v.time_limit[1].type&&n.use_hours.push(v.time_limit[1])),
n.consume_share_self_num=v.consume_share_self_num,n.consume_share_self_num>0?(n.consume_is_share=!0,
n.consume_share_type=1):v.consume_share_card_list&&v.consume_share_card_list.length?(n.consume_is_share=!0,
n.consume_share_type=2,n.consume_share_card_id=v.consume_share_card_list[0].card_id):n.consume_is_share=!1,
n.business_service=v.business_service;
var A=v.abstract;
A&&($(".section_card_intro").show(),n.abstract=A.abstract,n.cover_logo=A.icon_url_list?A.icon_url_list[0]:"");
}
if(n.is_quit_money=n.func_flag&1<<22,n.can_edit_quantity=!(n.is_quit_money||10==n.type||n.is_from_intercomm||(3!=n.status&&5!=n.status&&6!=n.status||!n.is_sns_card||n.is_expire)&&n.is_sns_card),
n.is_sns_card&&(n.isnew=!0),n.isnew||(n.quantity="--"),3==n.type&&n.is_sns_card){
n.gift_title=n.title;
var k=v.use_condition;
n.title=k?k.least_cost?"满%s送%s".sprintf(k.least_cost/100,n.gift_title):k.object_use_for?"买%s送%s".sprintf(k.object_use_for,n.gift_title):n.gift_title+(n.gift_num?n.gift_num:"")+(n.gift_unit?n.gift_unit:""):n.gift_title+(n.gift_num?n.gift_num:"")+(n.gift_unit?n.gift_unit:"");
}
n.pay_info=b.pay_info&&b.pay_info.swipe_card||{};
var S=65536&n.func_flag;
if(S)n.dispose_method=1;else{
var D=n.func_flag&1<<24;
n.pay_info.is_swipe_card?(n.dispose_method=4,n.code_type=1e4):D?(n.dispose_method=2,
n.code_type=1e4):n.dispose_method=3;
}
var C=n.pay_info;
if(C.auditing_info_list||(C.auditing_info_list=[]),C.is_swipe_card){
var R=C.auditing_info_list;
if(R.length){
var q=R[R.length-1];
if(C.swipe_card_status=0==q.mid_list.length?1:1==q.ret?C.is_active?0:3:2,q.trans_buff){
var I=q.trans_buff.html(!1);
I=$.parseJSON(I),q.trans_buff=I,C.last_audit_item=q;
}
}else C.swipe_card_status=0==n.quantity?4:0;
}
!C.is_swipe_card||1!=C.swipe_card_status&&3!=C.swipe_card_status||(n.can_edit_quantity=!1),
v&&v.consume_cell_info&&(n.need_verify_code=v.consume_cell_info.need_verify_code,
n.need_remark=v.consume_cell_info.need_remark),n._can_global_edit=!n.is_from_intercomm&&(!n.is_sns_card||n.is_sns_card&&!n.is_expire&&(3==n.status||5==n.status||6==n.status)||n.is_sns_card&&(1==n.status||2==n.status));
var k=v&&v.use_condition;
return k&&(n.use_condition_least_cost=k.least_cost/100||"",n.accept_category=k.accept_category,
n.reject_category=k.reject_category,n.can_use_with_other_discount=k.can_use_with_other_discount,
n.object_use_for=k.object_use_for,n.has_condition=k.least_cost||k.object_use_for||k.accept_category||k.reject_category||!k.can_use_with_other_discount,
3==n.type&&(n.use_condition_least_cost_type=n.object_use_for?2:1)),n.is_sns_card&&3==n.type&&(n.has_condition=!0),
n;
}
function a(e,_){
for(var t in _)_.hasOwnProperty(t)&&"object"!=typeof _[t]&&(e[t]=_[t]);
return e;
}
function r(e){
for(var _={},t=[],i=0;i<e.length;i++){
var s=n(e[i]);
s&&(_[s.id]=s,t.push(s));
}
return{
card_cache:_,
card_list:t
};
}
var u=e("cardticket/add/member_info_flag.js"),c={
DISCOUNT:"2",
MEMBER_CARD:"10",
GROUPON:"1",
GIFT:"3",
CASH:"4",
GENERAL_COUPON:"0",
SCENIC_TICKET:"21",
MOVIE_TICKET:"22"
},l={
CODE_TYPE_TEXT:0,
CODE_TYPE_BARCODE:1,
CODE_TYPE_QRCODE:2
},m={
CARD_STATUS_INIT:0,
CARD_STATUS_NOT_VERIFY:1,
CARD_STATUS_VERIFY_FAIL:2,
CARD_STATUS_VERIFY_OK:3,
CARD_STATUS_DELETE:4,
CARD_STATUS_SYS_DELETE:5,
CARD_STATUS_DISPATCH:6,
CARD_STATUS_SYS_OFF_SHELF:7,
CARD_STATUS_EXPIRED:8
},f={
DATE_TYPE_FIX_TIME_RANGE:1,
DATE_TYPE_FIX_TERM:2,
DATE_TYPE_PERMANENT:100
};
return{
parse_cardticket:n,
parse_cardlist:r,
url_type:i
};
});define("advanced/menu_link_dialog.js",["common/wx/popup.js","common/wx/top.js","common/wx/Tips.js","common/wx/Cgi.js","common/wx/pagebar.js","common/wx/time.js","tpl/advanced/menu_link_dialog.html.js","tpl/advanced/appmsg_list.html.js","biz_web/ui/checkbox.js","tpl/advanced/homepage_list.html.js","homepage/plugins/plugin1.js","homepage/plugins/plugin2.js","homepage/plugins/plugin3.js"],function(e){
"use strict";
function t(e){
this.opt=e,this.biz=e.biz,this.onOK=e.onOK,this.can_use_homepage=e.can_use_homepage,
this.createDialog();
}
e("common/wx/popup.js");
var n=e("common/wx/top.js"),i=e("common/wx/Tips.js"),s=e("common/wx/Cgi.js"),a=e("common/wx/pagebar.js"),o=e("common/wx/time.js"),d=e("tpl/advanced/menu_link_dialog.html.js"),l=e("tpl/advanced/appmsg_list.html.js"),p=(e("biz_web/ui/checkbox.js"),
e("tpl/advanced/homepage_list.html.js")),r=e("homepage/plugins/plugin1.js"),m=e("homepage/plugins/plugin2.js"),c=e("homepage/plugins/plugin3.js"),h={
1:r,
2:m,
3:c
},g=0,_="http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=%s#wechat_webview_type=1&wechat_redirect",u="http://mp.weixin.qq.com/mp/homepage?__biz=%s&hid=%s&sn=%s#wechat_redirect",f={
createDialog:function(){
var e=this,t=$.parseHTML(wx.T(d,{}));
this.dialog&&this.dialog.popup("remove"),this.dialog=$(t[0]).popup({
title:"选择图文消息",
className:"align_edge",
width:720,
onOK:function(){
if(e.$btn.hasClass("btn_disabled"))return i.err("请选择图文消息"),!0;
var t=e.currentTab,n={
type:t,
name:e.topData[t].name
},s=e.$dom.find(".js_content").eq(t);
n.title=s.data("title")||"",n.link=s.data("link"),e.onOK&&e.onOK(n);
},
onCancel:function(){
this.hide(),e.dialog=null;
},
onHide:function(){
e.$dom.off(),this.remove(),e.dialog=null;
}
}),e.$dom=e.dialog.popup("get"),e.$btn=e.$dom.find(".js_btn_p").eq(0).addClass("btn_disabled"),
e.init();
},
init:function(){
var e=this;
e.topData=[{
id:"hassent",
name:"已发送"
},{
id:"appmsg",
name:"素材库"
},{
id:"history",
name:"历史消息"
}],e.can_use_homepage&&e.topData.push({
id:"homepage",
name:"页面模板"
}),e.tab=new n(e.$dom.find(".js_tab"),e.topData),e.tab.selected(0),e.currentTab=0,
e.initEvent(),e.initHasSent();
},
initEvent:function(){
var e=this;
e.$dom.on("click",".js_top",function(){
var t=$(this).data("id"),n=$(this).data("index");
if(n!=e.currentTab){
var i=e.$dom.find(".js_content").hide(),s=i.eq(n).show();
switch(s.data("link")?e.$btn.removeClass("btn_disabled"):e.$btn.addClass("btn_disabled"),
t){
case"appmsg":
e.initAppMsg();
break;

case"history":
e.initHistory();
break;

case"homepage":
e.initHomePage();
}
e.tab.selected(e.currentTab=n),e.resetPos();
}
}),e.$dom.on("click","a",function(e){
e.preventDefault();
}),e.$dom.on("mousewheel",".js_hassent_list",function(e){
this.scrollTop-=e.originalEvent.wheelDelta>0?60:-60,e.preventDefault();
}).on("DOMMouseScroll",".js_hassent_list",function(e){
this.scrollTop+=e.originalEvent.detail>0?60:-60,e.preventDefault();
}),e.$dom.on("mousewheel",".js_appmsg_list",function(e){
this.scrollTop-=e.originalEvent.wheelDelta>0?60:-60,e.preventDefault();
}).on("DOMMouseScroll",".js_appmsg_list",function(e){
this.scrollTop+=e.originalEvent.detail>0?60:-60,e.preventDefault();
}),e.$dom.on("mousewheel",".js_content",function(e){
this.scrollTop-=e.originalEvent.wheelDelta>0?60:-60,e.preventDefault();
}).on("DOMMouseScroll",".js_content",function(e){
this.scrollTop+=e.originalEvent.detail>0?60:-60,e.preventDefault();
});
},
initHasSent:function(){
var e=this;
e.appSentInited||(e.getSentList(),e.appSentInited=!0);
},
initAppMsg:function(){
var e=this;
e.appMsgInited||(e.getAppMsgList(),e.$dom.on("click",".js_search_btn",function(){
e.getAppMsgList({
query:e.$dom.find(".js_search").val()
});
}),e.$dom.on("keyup",".js_search",function(t){
wx.isHotkey(t,"enter")&&e.getAppMsgList({
query:e.$dom.find(".js_search").val()
});
}),e.appMsgInited=!0);
},
getSentList:function(e){
var t=this,n=$.extend({
action:"list_ex",
begin:0,
count:10,
query:"",
scene:1,
type:9
},e||{}),a=t.loadingFlag=++g;
t.$dom.find(".js_hassent_list").hide(),t.$dom.find(".js_hassent .js_loading").show(),
t.resetPos(),s.post({
url:"/cgi-bin/appmsg",
data:n,
complete:function(){
t.$dom.find(".js_hassent .js_loading").hide(),t.resetPos();
}
},{
done:function(e){
if(a==t.loadingFlag)if(e&&e.base_resp){
if(0==e.base_resp.ret)return t.renderHasSent(e.app_msg_list),void t.renderPageBar(n.begin,e.app_msg_cnt,9);
s.show(e);
}else i.err("系统错误");
},
fail:function(){
i.err("系统错误");
}
});
},
getAppMsgList:function(e){
var t=this,n=$.extend({
action:"list_ex",
begin:0,
count:10,
query:"",
link:this.opt.link||0,
scene:1
},e||{}),a=t.loadingFlag=++g;
t.$dom.find(".js_appmsg_list").hide(),t.$dom.find(".js_appmsg .js_loading").show(),
t.resetPos(),s.post({
url:"/cgi-bin/appmsg",
data:n,
complete:function(){
t.$dom.find(".js_appmsg .js_loading").hide(),t.resetPos();
}
},{
done:function(e){
if(a==t.loadingFlag)if(e&&e.base_resp){
if(0==e.base_resp.ret)return t.renderAppMsg(e.app_msg_list),void t.renderPageBar(n.begin,e.app_msg_cnt);
s.show(e);
}else i.err("系统错误");
},
fail:function(){
i.err("系统错误");
}
});
},
renderAppMsg:function(e){
var t=this;
$.each(e,function(e,t){
t.date=o.formatDate(new Date(1e3*t.update_time),"YYYY年MM月DD日");
}),t.$dom.find(".js_appmsg_list").show().find("tbody").html(e.length?wx.T(l,{
data:e
}):'<tr class="empty_item"><td colspan="10" class="empty_tips">暂无数据</td></tr>'),
t.$dom.find(".js_appmsg").data("title",null),t.$dom.find(".js_appmsg").data("link",null),
t.$btn.addClass("btn_disabled"),t.$dom.find(".js_appmsg_list input").checkbox({
multi:!1,
onChanged:function(e){
var n=$(e).parents("tr");
if(n){
var i=n.data("title"),s=n.data("link");
t.$dom.find(".js_appmsg").data("title",i),t.$dom.find(".js_appmsg").data("link",s),
t.$btn.removeClass("btn_disabled");
}
}
}),t.resetPos();
},
renderHasSent:function(e){
var t=this;
$.each(e,function(e,t){
t.date=o.formatDate(new Date(1e3*t.update_time),"YYYY年MM月DD日");
}),t.$dom.find(".js_hassent_list").show().find("tbody").html(e.length?wx.T(l,{
data:e
}):'<tr class="empty_item"><td colspan="10" class="empty_tips">暂无数据</td></tr>'),
t.$dom.find(".js_hassent").data("title",null),t.$dom.find(".js_hassent").data("link",null),
t.$btn.addClass("btn_disabled"),t.$dom.find(".js_hassent_list input").checkbox({
multi:!1,
onChanged:function(e){
var n=$(e).parents("tr");
if(n){
var i=n.data("title"),s=n.data("link");
t.$dom.find(".js_hassent").data("title",i),t.$dom.find(".js_hassent").data("link",s),
t.$btn.removeClass("btn_disabled");
}
}
}),t.resetPos();
},
renderPageBar:function(e,t,n){
var i=this;
e=e||0,new a({
container:i.$dom.find(".js_pagebar:visible"),
perPage:10,
initShowPage:(e/10|0)+1,
totalItemsNum:t,
first:!1,
last:!1,
isSimple:!0,
callback:function(e){
var t=e.currentPage;
9==n?i.getSentList({
begin:10*(t-1),
query:i.$dom.find(".js_search").val()
}):i.getAppMsgList({
begin:10*(t-1),
query:i.$dom.find(".js_search").val()
});
}
});
},
initHistory:function(){
var e=this;
e.historyInited||(e.$dom.find(".js_history").on("change","input",function(){
var t="";
$(this).prop("checked")?($(this).closest("label").addClass("selected"),t=_.sprintf(e.biz),
e.$btn.removeClass("btn_disabled")):($(this).closest("label").removeClass("selected"),
t=null,e.$btn.addClass("btn_disabled")),e.$dom.find(".js_history").data("link",t);
}),e.historyInited=!0);
},
initHomePage:function(){
var e=this;
if(!e.homePageInited){
var t=e.$dom.find(".js_homepage");
t.find(".js_homepage_list").hide(),t.find(".js_loading").show(),s.get({
url:"/advanced/homepage?action=list",
complete:function(){
t.find(".js_loading").hide();
}
},{
done:function(t){
0==t.base_resp.ret?e.renderHomePage(JSON.parse(t.data).list):s.show(t);
},
fail:function(){
i.err("网络错误");
}
}),t.on("click",".js_item",function(){
$(this).addClass("selected").siblings().removeClass("selected"),t.data("link",u.sprintf(e.biz,$(this).data("hid"),$(this).data("sn"))),
t.data("title",$(this).data("name")),e.$btn.removeClass("btn_disabled");
}),e.homePageInited=!0;
}
},
renderHomePage:function(e){
var t=this;
$.each(e,function(e,t){
var n=[];
$.each(t.render_json.plugin_data,function(e,t){
n.push(h[t.pid].getRenderHTML(t));
}),t.subhtml=n.join(""),t.update_time=o.formatDate(new Date(1e3*t.update_time),"YYYY-MM-DD HH:mm:ss");
}),e.length?t.$dom.find(".js_homepage_list").html(wx.T(p,{
list:e,
nick_name:wx.cgiData.nick_name
})).show():t.$dom.find(".js_homepage_list").html('<p class="no_homepage">暂无页面模板</p>').show(),
t.resetPos();
},
resetPos:function(){
this.dialog&&this.dialog.popup("resetPosition");
}
};
return $.extend(t.prototype,f),t;
});define("common/wx/inputCounter.js",[],function(t,n,e){
"use strict";
function o(t,n){
this.$input=$(t),this.opts=$.extend(!0,{},i,n),this._init();
}
var i={
minLength:0,
maxLength:20,
showCounter:!0,
useGBKLength:!1,
GBKBased:!1
};
o.prototype._init=function(){
var t=this;
t.$input&&t.$input.length>0?(t.$inputBox=t.$input.parent("textarea"==t.$input.prop("tagName").toLowerCase()?".frm_textarea_box":".frm_input_box"),
t.count=t._getLen(t.getValue()),t.$counter=t.$inputBox.find(".frm_counter"),t.counterExist=!0,
0==t.$counter.length&&(t.counterExist=!1,t.$counter=$('<em class="frm_input_append frm_counter"></em>'),
t.$inputBox.append(t.$counter)),1==t.opts.showCounter?t.show():t.hide(),t.setCount(t.count),
t.inputEvent=function(){
t.setCount(t._getLen(t.getValue()));
},t.$input.on("keydown keyup",t.inputEvent)):console.log("inputCounter Err: input does not exist.");
},o.prototype.getValue=function(){
var t="";
switch(this.$input.prop("tagName")){
case"INPUT":
case"TEXTAREA":
t=this.$input.val();
break;

default:
t=this.$input.text();
}
return t;
},o.prototype._getLen=function(t){
var n=0;
return t=t||"",n=this.opts.useGBKLength?t.replace(/[^\x00-\xff]/g,"**").length:t.length,
this.opts.GBKBased&&(n=Math.ceil(n/2)),n;
},o.prototype.getCount=function(){
return this.count||0;
},o.prototype.setCount=function(t){
this.count=t,this.$counter.html(this.count+"&#47;"+this.opts.maxLength),this.count>this.opts.maxLength?(this.overflowed=!0,
this.$inputBox.addClass("warn")):this.count>0&&this.count<this.opts.minLength?(this.overflowed=!0,
this.$inputBox.addClass("warn")):(this.overflowed=!1,this.$inputBox.removeClass("warn"));
},o.prototype.hasOverflowed=function(){
return this.overflowed;
},o.prototype.show=function(){
this.$inputBox.addClass("with_counter counter_in append count"),this.$counter.show();
},o.prototype.hide=function(){
this.$inputBox.removeClass("with_counter counter_in append count warn"),this.$counter.hide();
},o.prototype.destroy=function(){
this.$input.off("keydown keyup",this.inputEvent),0==this.counterExist&&(this.hide(),
this.$counter.remove());
},e.exports=o;
});define("biz_common/moment.js",[],function(t,e,n){
function s(t,e){
return function(n){
return c(t.call(this,n),e);
};
}
function r(t){
return function(e){
return this.lang().ordinal(t.call(this,e));
};
}
function a(){}
function i(t){
u(this,t);
}
function o(t){
var e=this._data={},n=t.years||t.year||t.y||0,s=t.months||t.month||t.M||0,r=t.weeks||t.week||t.w||0,a=t.days||t.day||t.d||0,i=t.hours||t.hour||t.h||0,o=t.minutes||t.minute||t.m||0,u=t.seconds||t.second||t.s||0,c=t.milliseconds||t.millisecond||t.ms||0;
this._milliseconds=c+1e3*u+6e4*o+36e5*i,this._days=a+7*r,this._months=s+12*n,e.milliseconds=c%1e3,
u+=d(c/1e3),e.seconds=u%60,o+=d(u/60),e.minutes=o%60,i+=d(o/60),e.hours=i%24,a+=d(i/24),
a+=7*r,e.days=a%30,s+=d(a/30),e.months=s%12,n+=d(s/12),e.years=n;
}
function u(t,e){
for(var n in e)e.hasOwnProperty(n)&&(t[n]=e[n]);
return t;
}
function d(t){
return 0>t?Math.ceil(t):Math.floor(t);
}
function c(t,e){
for(var n=t+"";n.length<e;)n="0"+n;
return n;
}
function h(t,e,n){
var s,r=e._milliseconds,a=e._days,i=e._months;
r&&t._d.setTime(+t+r*n),a&&t.date(t.date()+a*n),i&&(s=t.date(),t.date(1).month(t.month()+i*n).date(Math.min(s,t.daysInMonth())));
}
function f(t){
return"[object Array]"===Object.prototype.toString.call(t);
}
function l(t,e){
var n,s=Math.min(t.length,e.length),r=Math.abs(t.length-e.length),a=0;
for(n=0;s>n;n++)~~t[n]!==~~e[n]&&a++;
return a+r;
}
function _(t,e){
return e.abbr=t,A[t]||(A[t]=new a),A[t].set(e),A[t];
}
function m(e){
return e?(!A[e]&&Z&&t("./lang/"+e),A[e]):C.fn._lang;
}
function M(t){
return t.match(/\[.*\]/)?t.replace(/^\[|\]$/g,""):t.replace(/\\/g,"");
}
function y(t){
var e,n,s=t.match(E);
for(e=0,n=s.length;n>e;e++)s[e]=ie[s[e]]?ie[s[e]]:M(s[e]);
return function(r){
var a="";
for(e=0;n>e;e++)a+="function"==typeof s[e].call?s[e].call(r,t):s[e];
return a;
};
}
function Y(t,e){
function n(e){
return t.lang().longDateFormat(e)||e;
}
for(var s=5;s--&&J.test(e);)e=e.replace(J,n);
return se[e]||(se[e]=y(e)),se[e](t);
}
function D(t){
switch(t){
case"DDDD":
return $;

case"YYYY":
return I;

case"YYYYY":
return X;

case"S":
case"SS":
case"SSS":
case"DDD":
return N;

case"MMM":
case"MMMM":
case"dd":
case"ddd":
case"dddd":
case"a":
case"A":
return j;

case"X":
return G;

case"Z":
case"ZZ":
return R;

case"T":
return B;

case"MM":
case"DD":
case"YY":
case"HH":
case"hh":
case"mm":
case"ss":
case"M":
case"D":
case"d":
case"H":
case"h":
case"m":
case"s":
return V;

default:
return new RegExp(t.replace("\\",""));
}
}
function p(t,e,n){
var s,r=n._a;
switch(t){
case"M":
case"MM":
r[1]=null==e?0:~~e-1;
break;

case"MMM":
case"MMMM":
s=m(n._l).monthsParse(e),null!=s?r[1]=s:n._isValid=!1;
break;

case"D":
case"DD":
case"DDD":
case"DDDD":
null!=e&&(r[2]=~~e);
break;

case"YY":
r[0]=~~e+(~~e>68?1900:2e3);
break;

case"YYYY":
case"YYYYY":
r[0]=~~e;
break;

case"a":
case"A":
n._isPm="pm"===(e+"").toLowerCase();
break;

case"H":
case"HH":
case"h":
case"hh":
r[3]=~~e;
break;

case"m":
case"mm":
r[4]=~~e;
break;

case"s":
case"ss":
r[5]=~~e;
break;

case"S":
case"SS":
case"SSS":
r[6]=~~(1e3*("0."+e));
break;

case"X":
n._d=new Date(1e3*parseFloat(e));
break;

case"Z":
case"ZZ":
n._useUTC=!0,s=(e+"").match(te),s&&s[1]&&(n._tzh=~~s[1]),s&&s[2]&&(n._tzm=~~s[2]),
s&&"+"===s[0]&&(n._tzh=-n._tzh,n._tzm=-n._tzm);
}
null==e&&(n._isValid=!1);
}
function g(t){
var e,n,s=[];
if(!t._d){
for(e=0;7>e;e++)t._a[e]=s[e]=null==t._a[e]?2===e?1:0:t._a[e];
s[3]+=t._tzh||0,s[4]+=t._tzm||0,n=new Date(0),t._useUTC?(n.setUTCFullYear(s[0],s[1],s[2]),
n.setUTCHours(s[3],s[4],s[5],s[6])):(n.setFullYear(s[0],s[1],s[2]),n.setHours(s[3],s[4],s[5],s[6])),
t._d=n;
}
}
function w(t){
var e,n,s=t._f.match(E),r=t._i;
for(t._a=[],e=0;e<s.length;e++)n=(D(s[e]).exec(r)||[])[0],n&&(r=r.slice(r.indexOf(n)+n.length)),
ie[s[e]]&&p(s[e],n,t);
t._isPm&&t._a[3]<12&&(t._a[3]+=12),t._isPm===!1&&12===t._a[3]&&(t._a[3]=0),g(t);
}
function T(t){
for(var e,n,s,r,a=99;t._f.length;){
if(e=u({},t),e._f=t._f.pop(),w(e),n=new i(e),n.isValid()){
s=n;
break;
}
r=l(e._a,n.toArray()),a>r&&(a=r,s=n);
}
u(t,s);
}
function k(t){
var e,n=t._i;
if(q.exec(n)){
for(t._f="YYYY-MM-DDT",e=0;4>e;e++)if(Q[e][1].exec(n)){
t._f+=Q[e][0];
break;
}
R.exec(n)&&(t._f+=" Z"),w(t);
}else t._d=new Date(n);
}
function v(t){
var e=t._i,n=P.exec(e);
void 0===e?t._d=new Date:n?t._d=new Date(+n[1]):"string"==typeof e?k(t):f(e)?(t._a=e.slice(0),
g(t)):t._d=new Date(e instanceof Date?+e:e);
}
function S(t,e,n,s,r){
return r.relativeTime(e||1,!!n,t,s);
}
function L(t,e,n){
var s=U(Math.abs(t)/1e3),r=U(s/60),a=U(r/60),i=U(a/24),o=U(i/365),u=45>s&&["s",s]||1===r&&["m"]||45>r&&["mm",r]||1===a&&["h"]||22>a&&["hh",a]||1===i&&["d"]||25>=i&&["dd",i]||45>=i&&["M"]||345>i&&["MM",U(i/30)]||1===o&&["y"]||["yy",o];
return u[2]=e,u[3]=t>0,u[4]=n,S.apply({},u);
}
function b(t,e,n){
var s=n-e,r=n-t.day();
return r>s&&(r-=7),s-7>r&&(r+=7),Math.ceil(C(t).add("d",r).dayOfYear()/7);
}
function F(t){
var e=t._i,n=t._f;
return null===e||""===e?null:("string"==typeof e&&(t._i=e=m().preparse(e)),C.isMoment(e)?(t=u({},e),
t._d=new Date(+e._d)):n?f(n)?T(t):w(t):v(t),new i(t));
}
function H(t,e){
C.fn[t]=C.fn[t+"s"]=function(t){
var n=this._isUTC?"UTC":"";
return null!=t?(this._d["set"+n+e](t),this):this._d["get"+n+e]();
};
}
function O(t){
C.duration.fn[t]=function(){
return this._data[t];
};
}
function z(t,e){
C.duration.fn["as"+t]=function(){
return+this/e;
};
}
for(var C,W,x="2.0.0",U=Math.round,A={},Z="undefined"!=typeof n&&n.exports,P=/^\/?Date\((\-?\d+)/i,E=/(\[[^\[]*\])|(\\)?(Mo|MM?M?M?|Do|DDDo|DD?D?D?|ddd?d?|do?|w[o|w]?|W[o|W]?|YYYYY|YYYY|YY|a|A|hh?|HH?|mm?|ss?|SS?S?|X|zz?|ZZ?|.)/g,J=/(\[[^\[]*\])|(\\)?(LT|LL?L?L?|l{1,4})/g,V=/\d\d?/,N=/\d{1,3}/,$=/\d{3}/,I=/\d{1,4}/,X=/[+\-]?\d{1,6}/,j=/[0-9]*[a-z\u00A0-\u05FF\u0700-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+|[\u0600-\u06FF]+\s*?[\u0600-\u06FF]+/i,R=/Z|[\+\-]\d\d:?\d\d/i,B=/T/i,G=/[\+\-]?\d+(\.\d{1,3})?/,q=/^\s*\d{4}-\d\d-\d\d((T| )(\d\d(:\d\d(:\d\d(\.\d\d?\d?)?)?)?)?([\+\-]\d\d:?\d\d)?)?/,K="YYYY-MM-DDTHH:mm:ssZ",Q=[["HH:mm:ss.S",/(T| )\d\d:\d\d:\d\d\.\d{1,3}/],["HH:mm:ss",/(T| )\d\d:\d\d:\d\d/],["HH:mm",/(T| )\d\d:\d\d/],["HH",/(T| )\d\d/]],te=/([\+\-]|\d\d)/gi,ee="Month|Date|Hours|Minutes|Seconds|Milliseconds".split("|"),ne={
Milliseconds:1,
Seconds:1e3,
Minutes:6e4,
Hours:36e5,
Days:864e5,
Months:2592e6,
Years:31536e6
},se={},re="DDD w W M D d".split(" "),ae="M D H h m s w W".split(" "),ie={
M:function(){
return this.month()+1;
},
MMM:function(t){
return this.lang().monthsShort(this,t);
},
MMMM:function(t){
return this.lang().months(this,t);
},
D:function(){
return this.date();
},
DDD:function(){
return this.dayOfYear();
},
d:function(){
return this.day();
},
dd:function(t){
return this.lang().weekdaysMin(this,t);
},
ddd:function(t){
return this.lang().weekdaysShort(this,t);
},
dddd:function(t){
return this.lang().weekdays(this,t);
},
w:function(){
return this.week();
},
W:function(){
return this.isoWeek();
},
YY:function(){
return c(this.year()%100,2);
},
YYYY:function(){
return c(this.year(),4);
},
YYYYY:function(){
return c(this.year(),5);
},
a:function(){
return this.lang().meridiem(this.hours(),this.minutes(),!0);
},
A:function(){
return this.lang().meridiem(this.hours(),this.minutes(),!1);
},
H:function(){
return this.hours();
},
h:function(){
return this.hours()%12||12;
},
m:function(){
return this.minutes();
},
s:function(){
return this.seconds();
},
S:function(){
return~~(this.milliseconds()/100);
},
SS:function(){
return c(~~(this.milliseconds()/10),2);
},
SSS:function(){
return c(this.milliseconds(),3);
},
Z:function(){
var t=-this.zone(),e="+";
return 0>t&&(t=-t,e="-"),e+c(~~(t/60),2)+":"+c(~~t%60,2);
},
ZZ:function(){
var t=-this.zone(),e="+";
return 0>t&&(t=-t,e="-"),e+c(~~(10*t/6),4);
},
X:function(){
return this.unix();
}
};re.length;)W=re.pop(),ie[W+"o"]=r(ie[W]);
for(;ae.length;)W=ae.pop(),ie[W+W]=s(ie[W],2);
for(ie.DDDD=s(ie.DDD,3),a.prototype={
set:function(t){
var e,n;
for(n in t)e=t[n],"function"==typeof e?this[n]=e:this["_"+n]=e;
},
_months:"January_February_March_April_May_June_July_August_September_October_November_December".split("_"),
months:function(t){
return this._months[t.month()];
},
_monthsShort:"Jan_Feb_Mar_Apr_May_Jun_Jul_Aug_Sep_Oct_Nov_Dec".split("_"),
monthsShort:function(t){
return this._monthsShort[t.month()];
},
monthsParse:function(t){
var e,n,s;
for(this._monthsParse||(this._monthsParse=[]),e=0;12>e;e++)if(this._monthsParse[e]||(n=C([2e3,e]),
s="^"+this.months(n,"")+"|^"+this.monthsShort(n,""),this._monthsParse[e]=new RegExp(s.replace(".",""),"i")),
this._monthsParse[e].test(t))return e;
},
_weekdays:"Sunday_Monday_Tuesday_Wednesday_Thursday_Friday_Saturday".split("_"),
weekdays:function(t){
return this._weekdays[t.day()];
},
_weekdaysShort:"Sun_Mon_Tue_Wed_Thu_Fri_Sat".split("_"),
weekdaysShort:function(t){
return this._weekdaysShort[t.day()];
},
_weekdaysMin:"Su_Mo_Tu_We_Th_Fr_Sa".split("_"),
weekdaysMin:function(t){
return this._weekdaysMin[t.day()];
},
_longDateFormat:{
LT:"h:mm A",
L:"MM/DD/YYYY",
LL:"MMMM D YYYY",
LLL:"MMMM D YYYY LT",
LLLL:"dddd, MMMM D YYYY LT"
},
longDateFormat:function(t){
var e=this._longDateFormat[t];
return!e&&this._longDateFormat[t.toUpperCase()]&&(e=this._longDateFormat[t.toUpperCase()].replace(/MMMM|MM|DD|dddd/g,function(t){
return t.slice(1);
}),this._longDateFormat[t]=e),e;
},
meridiem:function(t,e,n){
return t>11?n?"pm":"PM":n?"am":"AM";
},
_calendar:{
sameDay:"[Today at] LT",
nextDay:"[Tomorrow at] LT",
nextWeek:"dddd [at] LT",
lastDay:"[Yesterday at] LT",
lastWeek:"[last] dddd [at] LT",
sameElse:"L"
},
calendar:function(t,e){
var n=this._calendar[t];
return"function"==typeof n?n.apply(e):n;
},
_relativeTime:{
future:"in %s",
past:"%s ago",
s:"a few seconds",
m:"a minute",
mm:"%d minutes",
h:"an hour",
hh:"%d hours",
d:"a day",
dd:"%d days",
M:"a month",
MM:"%d months",
y:"a year",
yy:"%d years"
},
relativeTime:function(t,e,n,s){
var r=this._relativeTime[n];
return"function"==typeof r?r(t,e,n,s):r.replace(/%d/i,t);
},
pastFuture:function(t,e){
var n=this._relativeTime[t>0?"future":"past"];
return"function"==typeof n?n(e):n.replace(/%s/i,e);
},
ordinal:function(t){
return this._ordinal.replace("%d",t);
},
_ordinal:"%d",
preparse:function(t){
return t;
},
postformat:function(t){
return t;
},
week:function(t){
return b(t,this._week.dow,this._week.doy);
},
_week:{
dow:0,
doy:6
}
},C=function(t,e,n){
return F({
_i:t,
_f:e,
_l:n,
_isUTC:!1
});
},C.utc=function(t,e,n){
return F({
_useUTC:!0,
_isUTC:!0,
_l:n,
_i:t,
_f:e
});
},C.unix=function(t){
return C(1e3*t);
},C.duration=function(t,e){
var n,s=C.isDuration(t),r="number"==typeof t,a=s?t._data:r?{}:t;
return r&&(e?a[e]=t:a.milliseconds=t),n=new o(a),s&&t.hasOwnProperty("_lang")&&(n._lang=t._lang),
n;
},C.version=x,C.defaultFormat=K,C.lang=function(t,e){
return t?(e?_(t,e):A[t]||m(t),void(C.duration.fn._lang=C.fn._lang=m(t))):C.fn._lang._abbr;
},C.langData=function(t){
return t&&t._lang&&t._lang._abbr&&(t=t._lang._abbr),m(t);
},C.isMoment=function(t){
return t instanceof i;
},C.isDuration=function(t){
return t instanceof o;
},C.fn=i.prototype={
clone:function(){
return C(this);
},
valueOf:function(){
return+this._d;
},
unix:function(){
return Math.floor(+this._d/1e3);
},
toString:function(){
return this.format("ddd MMM DD YYYY HH:mm:ss [GMT]ZZ");
},
toDate:function(){
return this._d;
},
toJSON:function(){
return C.utc(this).format("YYYY-MM-DD[T]HH:mm:ss.SSS[Z]");
},
toArray:function(){
var t=this;
return[t.year(),t.month(),t.date(),t.hours(),t.minutes(),t.seconds(),t.milliseconds()];
},
isValid:function(){
return null==this._isValid&&(this._isValid=this._a?!l(this._a,(this._isUTC?C.utc(this._a):C(this._a)).toArray()):!isNaN(this._d.getTime())),
!!this._isValid;
},
utc:function(){
return this._isUTC=!0,this;
},
local:function(){
return this._isUTC=!1,this;
},
format:function(t){
var e=Y(this,t||C.defaultFormat);
return this.lang().postformat(e);
},
add:function(t,e){
var n;
return n="string"==typeof t?C.duration(+e,t):C.duration(t,e),h(this,n,1),this;
},
subtract:function(t,e){
var n;
return n="string"==typeof t?C.duration(+e,t):C.duration(t,e),h(this,n,-1),this;
},
diff:function(t,e,n){
var s,r,a=this._isUTC?C(t).utc():C(t).local(),i=6e4*(this.zone()-a.zone());
return e&&(e=e.replace(/s$/,"")),"year"===e||"month"===e?(s=432e5*(this.daysInMonth()+a.daysInMonth()),
r=12*(this.year()-a.year())+(this.month()-a.month()),r+=(this-C(this).startOf("month")-(a-C(a).startOf("month")))/s,
"year"===e&&(r/=12)):(s=this-a-i,r="second"===e?s/1e3:"minute"===e?s/6e4:"hour"===e?s/36e5:"day"===e?s/864e5:"week"===e?s/6048e5:s),
n?r:d(r);
},
from:function(t,e){
return C.duration(this.diff(t)).lang(this.lang()._abbr).humanize(!e);
},
fromNow:function(t){
return this.from(C(),t);
},
calendar:function(){
var t=this.diff(C().startOf("day"),"days",!0),e=-6>t?"sameElse":-1>t?"lastWeek":0>t?"lastDay":1>t?"sameDay":2>t?"nextDay":7>t?"nextWeek":"sameElse";
return this.format(this.lang().calendar(e,this));
},
isLeapYear:function(){
var t=this.year();
return t%4===0&&t%100!==0||t%400===0;
},
isDST:function(){
return this.zone()<C([this.year()]).zone()||this.zone()<C([this.year(),5]).zone();
},
day:function(t){
var e=this._isUTC?this._d.getUTCDay():this._d.getDay();
return null==t?e:this.add({
d:t-e
});
},
startOf:function(t){
switch(t=t.replace(/s$/,"")){
case"year":
this.month(0);

case"month":
this.date(1);

case"week":
case"day":
this.hours(0);

case"hour":
this.minutes(0);

case"minute":
this.seconds(0);

case"second":
this.milliseconds(0);
}
return"week"===t&&this.day(0),this;
},
endOf:function(t){
return this.startOf(t).add(t.replace(/s?$/,"s"),1).subtract("ms",1);
},
isAfter:function(t,e){
return e="undefined"!=typeof e?e:"millisecond",+this.clone().startOf(e)>+C(t).startOf(e);
},
isBefore:function(t,e){
return e="undefined"!=typeof e?e:"millisecond",+this.clone().startOf(e)<+C(t).startOf(e);
},
isSame:function(t,e){
return e="undefined"!=typeof e?e:"millisecond",+this.clone().startOf(e)===+C(t).startOf(e);
},
zone:function(){
return this._isUTC?0:this._d.getTimezoneOffset();
},
daysInMonth:function(){
return C.utc([this.year(),this.month()+1,0]).date();
},
dayOfYear:function(t){
var e=U((C(this).startOf("day")-C(this).startOf("year"))/864e5)+1;
return null==t?e:this.add("d",t-e);
},
isoWeek:function(t){
var e=b(this,1,4);
return null==t?e:this.add("d",7*(t-e));
},
week:function(t){
var e=this.lang().week(this);
return null==t?e:this.add("d",7*(t-e));
},
lang:function(t){
return void 0===t?this._lang:(this._lang=m(t),this);
}
},W=0;W<ee.length;W++)H(ee[W].toLowerCase().replace(/s$/,""),ee[W]);
H("year","FullYear"),C.fn.days=C.fn.day,C.fn.weeks=C.fn.week,C.fn.isoWeeks=C.fn.isoWeek,
C.duration.fn=o.prototype={
weeks:function(){
return d(this.days()/7);
},
valueOf:function(){
return this._milliseconds+864e5*this._days+2592e6*this._months;
},
humanize:function(t){
var e=+this,n=L(e,!t,this.lang());
return t&&(n=this.lang().pastFuture(e,n)),this.lang().postformat(n);
},
lang:C.fn.lang
};
for(W in ne)ne.hasOwnProperty(W)&&(z(W,ne[W]),O(W.toLowerCase()));
return z("Weeks",6048e5),C.lang("zh-cn",{
months:"一月_二月_三月_四月_五月_六月_七月_八月_九月_十月_十一月_十二月".split("_"),
monthsShort:"1月_2月_3月_4月_5月_6月_7月_8月_9月_10月_11月_12月".split("_"),
weekdays:"星期日_星期一_星期二_星期三_星期四_星期五_星期六".split("_"),
weekdaysShort:"周日_周一_周二_周三_周四_周五_周六".split("_"),
weekdaysMin:"日_一_二_三_四_五_六".split("_"),
longDateFormat:{
LT:"Ah点mm",
L:"YYYY年MMMD日",
LL:"YYYY年MMMD日",
LLL:"YYYY年MMMD日LT",
LLLL:"YYYY年MMMD日ddddLT",
l:"YYYY年MMMD日",
ll:"YYYY年MMMD日",
lll:"YYYY年MMMD日LT",
llll:"YYYY年MMMD日ddddLT"
},
meridiem:function(t,e){
return 9>t?"早上":11>t&&30>e?"上午":13>t&&30>e?"中午":18>t?"下午":"晚上";
},
calendar:{
sameDay:"[今天]LT",
nextDay:"[明天]LT",
nextWeek:"[下]ddddLT",
lastDay:"[昨天]LT",
lastWeek:"[上]ddddLT",
sameElse:"L"
},
ordinal:function(t,e){
switch(e){
case"d":
case"D":
case"DDD":
return t+"日";

case"M":
return t+"月";

case"w":
case"W":
return t+"周";

default:
return t;
}
},
relativeTime:{
future:"%s内",
past:"%s前",
s:"几秒",
m:"1分钟",
mm:"%d分钟",
h:"1小时",
hh:"%d小时",
d:"1天",
dd:"%d天",
M:"1个月",
MM:"%d个月",
y:"1年",
yy:"%d年"
}
}),C;
});define("biz_common/xss.js",[],function(t,e,i){
function s(t,e,i){
if("href"===e||"src"===e){
if(p.lastIndex=0,p.test(i))return"#";
if(v.lastIndex=0,v.test(i))return"#";
}else if("style"===e){
if(m.lastIndex=0,m.test(i))return"#";
if(w.lastIndex=0,w.test(i))return"";
}
}
function r(t,e){
return n(e);
}
function n(t){
return t.replace(f,"&lt;").replace(u,"&gt;");
}
function o(t,e){
return String.fromCharCode(parseInt(e));
}
function a(t){
"use strict";
this.options=t=t||{},this.whiteList=t.whiteList||e.whiteList,this.onTagAttr=t.onTagAttr||e.onTagAttr,
this.onIgnoreTag=t.onIgnoreTag||e.onIgnoreTag;
}
function l(t,e){
var i=new a(e);
return i.process(t);
}
var c={
h1:[],
h2:[],
h3:[],
h4:[],
h5:[],
h6:[],
hr:[],
span:[],
strong:[],
b:[],
i:[],
br:[],
p:[],
pre:[],
code:[],
a:["target","href","title"],
img:["src","alt","title"],
div:[],
table:["width","border"],
tr:[],
td:["width","colspan"],
th:["width","colspan"],
tbody:[],
ul:[],
li:[],
ol:[],
dl:[],
dt:[],
em:[],
cite:[],
section:[],
header:[],
footer:[],
blockquote:[],
audio:["autoplay","controls","loop","preload","src"],
video:["autoplay","controls","loop","preload","src","height","width"]
},f=/</g,u=/>/g,h=/"/g,g=/[^a-zA-Z0-9_:\.\-]/gim,d=/&#([a-zA-Z0-9]*);?/gim,p=/\/\*|\*\//gm,v=/^[\s"'`]*((j\s*a\s*v\s*a|v\s*b|l\s*i\s*v\s*e)\s*s\s*c\s*r\s*i\s*p\s*t\s*|m\s*o\s*c\s*h\s*a):/gi,m=/\/\*|\*\//gm,w=/((j\s*a\s*v\s*a|v\s*b|l\s*i\s*v\s*e)\s*s\s*c\s*r\s*i\s*p\s*t\s*|m\s*o\s*c\s*h\s*a):/gi;
a.prototype.filterAttributes=function(t,e){
"use strict";
t=t.toLowerCase();
for(var i=this,s=this.whiteList[t],r=0,n="",a=!1,l=!1,c=function(e,r){
if(e=e.trim(),!l&&"/"===e)return void(l=!0);
if(e=e.replace(g,"").toLowerCase(),!(e.length<1)&&-1!==s.indexOf(e)){
if(r){
r=r.trim().replace(h,"&quote;"),r=r.replace(d,o);
for(var a="",c=0,f=r.length;f>c;c++)a+=r.charCodeAt(c)<32?" ":r.charAt(c);
r=a.trim();
var u=i.onTagAttr(t,e,r);
"undefined"!=typeof u&&(r=u);
}
n+=e+(r?'="'+r+'"':"")+" ";
}
},f=0,u=e.length;u>f;f++){
var p=e.charAt(f);
if(a!==!1||"="!==p)if(a===!1||f!==r||'"'!==p&&"'"!==p)if(" "!==p);else{
var v=e.slice(r,f).trim();
a===!1?c(v):c(a,v),a=!1,r=f+1;
}else{
var m=e.indexOf(p,f+1);
if(-1===m)break;
var v=e.slice(r+1,m).trim();
c(a,v),a=!1,f=m,r=f+1;
}else a=e.slice(r,f),r=f+1;
}
return r<e.length&&(a===!1?c(e.slice(r)):c(a,e.slice(r))),l&&(n+="/"),n.trim();
},a.prototype.addNewTag=function(t,e,i){
"use strict";
var s="",r="</"===t.slice(0,2)?2:1,o=t.indexOf(" ");
if(-1===o)var a=t.slice(r,t.length-1).trim();else var a=t.slice(r,o+1).trim();
if(a=a.toLowerCase(),a in this.whiteList)if(-1===o)s+=t.slice(0,r)+a+">";else{
var l=this.filterAttributes(a,t.slice(o+1,t.length-1).trim());
s+=t.slice(0,r)+a+(l.length>0?" "+l:"")+">";
}else{
var c={
isClosing:2===r,
position:i,
originalPosition:e-t.length+1
},f=this.onIgnoreTag(a,t,c);
"undefined"==typeof f&&(f=n(t)),s+=f;
}
return s;
},a.prototype.process=function(t){
"use strict";
for(var e="",i=0,s=!1,r=!1,o=0,o=0,a=t.length;a>o;o++){
var l=t.charAt(o);
if(s===!1){
if("<"===l){
s=o;
continue;
}
}else if(r===!1){
if("<"===l){
e+=n(t.slice(i,o)),s=o,i=o;
continue;
}
if(">"===l){
e+=n(t.slice(i,s)),e+=this.addNewTag(t.slice(s,o+1),o,e.length),i=o+1,s=!1;
continue;
}
if('"'===l||"'"===l){
r=l;
continue;
}
}else if(l===r){
r=!1;
continue;
}
}
return i<t.length&&(e+=n(t.substr(i))),e;
},e=i.exports=l,e.FilterXSS=a,e.whiteList=c,e.onTagAttr=s,e.onIgnoreTag=r,e.utils={
tagFilter:function(t,e){
"function"!=typeof e&&(e=function(){});
var i=[],s=!1;
return{
onIgnoreTag:function(r,n,o){
if(-1!==t.indexOf(r)){
var a="[removed]";
if(s!==!1&&o.isClosing){
var l=o.position+a.length;
i.push([s,l]),s=!1;
}else s=o.position;
return a;
}
return e(r,n,o);
},
filter:function(t){
var e="",s=0;
return i.forEach(function(i){
e+=t.slice(s,i[0]),s=i[1];
}),e+=t.slice(s);
}
};
}
};
});