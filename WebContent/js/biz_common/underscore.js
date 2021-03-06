define("biz_common/underscore.js",[],function(n,t,r){
(function(){
var n=this,e=n._,u={},i=Array.prototype,a=Object.prototype,o=Function.prototype,c=i.push,l=i.slice,f=i.concat,s=a.toString,p=a.hasOwnProperty,h=i.forEach,v=i.map,d=i.reduce,m=i.reduceRight,g=i.filter,y=i.every,b=i.some,_=i.indexOf,x=i.lastIndexOf,w=Array.isArray,j=Object.keys,A=o.bind,E=function(n){
return n instanceof E?n:this instanceof E?void(this._wrapped=n):new E(n);
};
"undefined"!=typeof t?("undefined"!=typeof r&&r.exports&&(t=r.exports=E),t._=E):n._=E,
E.VERSION="1.4.4";
var O=E.each=E.forEach=function(n,t,r){
if(null!=n)if(h&&n.forEach===h)n.forEach(t,r);else if(n.length===+n.length){
for(var e=0,i=n.length;i>e;e++)if(t.call(r,n[e],e,n)===u)return;
}else for(var a in n)if(E.has(n,a)&&t.call(r,n[a],a,n)===u)return;
};
E.map=E.collect=function(n,t,r){
var e=[];
return null==n?e:v&&n.map===v?n.map(t,r):(O(n,function(n,u,i){
e.push(t.call(r,n,u,i));
}),e);
};
var F="Reduce of empty array with no initial value";
E.reduce=E.foldl=E.inject=function(n,t,r,e){
var u=arguments.length>2;
if(null==n&&(n=[]),d&&n.reduce===d)return e&&(t=E.bind(t,e)),u?n.reduce(t,r):n.reduce(t);
if(O(n,function(n,i,a){
u?r=t.call(e,r,n,i,a):(r=n,u=!0);
}),!u)throw new TypeError(F);
return r;
},E.reduceRight=E.foldr=function(n,t,r,e){
var u=arguments.length>2;
if(null==n&&(n=[]),m&&n.reduceRight===m)return e&&(t=E.bind(t,e)),u?n.reduceRight(t,r):n.reduceRight(t);
var i=n.length;
if(i!==+i){
var a=E.keys(n);
i=a.length;
}
if(O(n,function(o,c,l){
c=a?a[--i]:--i,u?r=t.call(e,r,n[c],c,l):(r=n[c],u=!0);
}),!u)throw new TypeError(F);
return r;
},E.find=E.detect=function(n,t,r){
var e;
return k(n,function(n,u,i){
return t.call(r,n,u,i)?(e=n,!0):void 0;
}),e;
},E.filter=E.select=function(n,t,r){
var e=[];
return null==n?e:g&&n.filter===g?n.filter(t,r):(O(n,function(n,u,i){
t.call(r,n,u,i)&&e.push(n);
}),e);
},E.reject=function(n,t,r){
return E.filter(n,function(n,e,u){
return!t.call(r,n,e,u);
},r);
},E.every=E.all=function(n,t,r){
t||(t=E.identity);
var e=!0;
return null==n?e:y&&n.every===y?n.every(t,r):(O(n,function(n,i,a){
return(e=e&&t.call(r,n,i,a))?void 0:u;
}),!!e);
};
var k=E.some=E.any=function(n,t,r){
t||(t=E.identity);
var e=!1;
return null==n?e:b&&n.some===b?n.some(t,r):(O(n,function(n,i,a){
return e||(e=t.call(r,n,i,a))?u:void 0;
}),!!e);
};
E.contains=E.include=function(n,t){
return null==n?!1:_&&n.indexOf===_?-1!=n.indexOf(t):k(n,function(n){
return n===t;
});
},E.invoke=function(n,t){
var r=l.call(arguments,2),e=E.isFunction(t);
return E.map(n,function(n){
return(e?t:n[t]).apply(n,r);
});
},E.pluck=function(n,t){
return E.map(n,function(n){
return n[t];
});
},E.where=function(n,t,r){
return E.isEmpty(t)?r?void 0:[]:E[r?"find":"filter"](n,function(n){
for(var r in t)if(t[r]!==n[r])return!1;
return!0;
});
},E.findWhere=function(n,t){
return E.where(n,t,!0);
},E.max=function(n,t,r){
if(!t&&E.isArray(n)&&n[0]===+n[0]&&n.length<65535)return Math.max.apply(Math,n);
if(!t&&E.isEmpty(n))return-1/0;
var e={
computed:-1/0,
value:-1/0
};
return O(n,function(n,u,i){
var a=t?t.call(r,n,u,i):n;
a>e.computed&&(e={
value:n,
computed:a
});
}),e.value;
},E.min=function(n,t,r){
if(!t&&E.isArray(n)&&n[0]===+n[0]&&n.length<65535)return Math.min.apply(Math,n);
if(!t&&E.isEmpty(n))return 1/0;
var e={
computed:1/0,
value:1/0
};
return O(n,function(n,u,i){
var a=t?t.call(r,n,u,i):n;
a<e.computed&&(e={
value:n,
computed:a
});
}),e.value;
},E.shuffle=function(n){
var t,r=0,e=[];
return O(n,function(n){
t=E.random(r++),e[r-1]=e[t],e[t]=n;
}),e;
};
var R=function(n){
return E.isFunction(n)?n:function(t){
return t[n];
};
};
E.sortBy=function(n,t,r){
var e=R(t);
return E.pluck(E.map(n,function(n,t,u){
return{
value:n,
index:t,
criteria:e.call(r,n,t,u)
};
}).sort(function(n,t){
var r=n.criteria,e=t.criteria;
if(r!==e){
if(r>e||void 0===r)return 1;
if(e>r||void 0===e)return-1;
}
return n.index<t.index?-1:1;
}),"value");
};
var M=function(n,t,r,e){
var u={},i=R(null==t?E.identity:t);
return O(n,function(t,a){
var o=i.call(r,t,a,n);
e(u,o,t);
}),u;
};
E.groupBy=function(n,t,r){
return M(n,t,r,function(n,t,r){
(E.has(n,t)?n[t]:n[t]=[]).push(r);
});
},E.countBy=function(n,t,r){
return M(n,t,r,function(n,t){
E.has(n,t)||(n[t]=0),n[t]++;
});
},E.sortedIndex=function(n,t,r,e){
r=null==r?E.identity:R(r);
for(var u=r.call(e,t),i=0,a=n.length;a>i;){
var o=i+a>>>1;
r.call(e,n[o])<u?i=o+1:a=o;
}
return i;
},E.toArray=function(n){
return n?E.isArray(n)?l.call(n):n.length===+n.length?E.map(n,E.identity):E.values(n):[];
},E.size=function(n){
return null==n?0:n.length===+n.length?n.length:E.keys(n).length;
},E.first=E.head=E.take=function(n,t,r){
return null==n?void 0:null==t||r?n[0]:l.call(n,0,t);
},E.initial=function(n,t,r){
return l.call(n,0,n.length-(null==t||r?1:t));
},E.last=function(n,t,r){
return null==n?void 0:null==t||r?n[n.length-1]:l.call(n,Math.max(n.length-t,0));
},E.rest=E.tail=E.drop=function(n,t,r){
return l.call(n,null==t||r?1:t);
},E.compact=function(n){
return E.filter(n,E.identity);
};
var S=function(n,t,r){
return O(n,function(n){
E.isArray(n)||E.isArguments(n)?t?c.apply(r,n):S(n,t,r):r.push(n);
}),r;
};
E.flatten=function(n,t){
return S(n,t,[]);
},E.without=function(n){
return E.difference(n,l.call(arguments,1));
},E.uniq=E.unique=function(n,t,r,e){
E.isFunction(t)&&(e=r,r=t,t=!1);
var u=r?E.map(n,r,e):n,i=[],a=[];
return O(u,function(r,e){
(t?e&&a[a.length-1]===r:E.contains(a,r))||(a.push(r),i.push(n[e]));
}),i;
},E.union=function(){
return E.uniq(E.flatten(arguments,!0));
},E.intersection=function(n){
var t=l.call(arguments,1);
return E.filter(E.uniq(n),function(n){
return E.every(t,function(t){
return E.indexOf(t,n)>=0;
});
});
},E.difference=function(n){
var t=f.apply(i,l.call(arguments,1));
return E.filter(n,function(n){
return!E.contains(t,n);
});
},E.zip=function(){
return E.unzip(l.call(arguments));
},E.unzip=function(n){
for(var t=E.max(E.pluck(n,"length").concat(0)),r=new Array(t),e=0;t>e;e++)r[e]=E.pluck(n,""+e);
return r;
},E.object=function(n,t){
if(null==n)return{};
for(var r={},e=0,u=n.length;u>e;e++)t?r[n[e]]=t[e]:r[n[e][0]]=n[e][1];
return r;
},E.indexOf=function(n,t,r){
if(null==n)return-1;
var e=0,u=n.length;
if(r){
if("number"!=typeof r)return e=E.sortedIndex(n,t),n[e]===t?e:-1;
e=0>r?Math.max(0,u+r):r;
}
if(_&&n.indexOf===_)return n.indexOf(t,r);
for(;u>e;e++)if(n[e]===t)return e;
return-1;
},E.lastIndexOf=function(n,t,r){
if(null==n)return-1;
var e=null!=r;
if(x&&n.lastIndexOf===x)return e?n.lastIndexOf(t,r):n.lastIndexOf(t);
for(var u=e?r:n.length;u--;)if(n[u]===t)return u;
return-1;
},E.range=function(n,t,r){
arguments.length<=1&&(t=n||0,n=0),r=arguments[2]||1;
for(var e=Math.max(Math.ceil((t-n)/r),0),u=0,i=new Array(e);e>u;)i[u++]=n,n+=r;
return i;
};
var I=function(){};
E.bind=function(n,t){
var r,e;
if(A&&n.bind===A)return A.apply(n,l.call(arguments,1));
if(!E.isFunction(n))throw new TypeError;
return r=l.call(arguments,2),e=function(){
if(!(this instanceof e))return n.apply(t,r.concat(l.call(arguments)));
I.prototype=n.prototype;
var u=new I;
I.prototype=null;
var i=n.apply(u,r.concat(l.call(arguments)));
return Object(i)===i?i:u;
};
},E.partial=function(n){
var t=l.call(arguments,1);
return function(){
return n.apply(this,t.concat(l.call(arguments)));
};
},E.bindAll=function(n){
var t=l.call(arguments,1);
if(0===t.length)throw new Error("bindAll must be passed function names");
return O(t,function(t){
n[t]=E.bind(n[t],n);
}),n;
},E.memoize=function(n,t){
var r={};
return t||(t=E.identity),function(){
var e=t.apply(this,arguments);
return E.has(r,e)?r[e]:r[e]=n.apply(this,arguments);
};
},E.delay=function(n,t){
var r=l.call(arguments,2);
return setTimeout(function(){
return n.apply(null,r);
},t);
},E.defer=function(n){
return E.delay.apply(E,[n,1].concat(l.call(arguments,1)));
},E.throttle=function(n,t,r){
var e,u,i,a=null,o=0,c=function(){
o=new Date,a=null,i=n.apply(e,u);
};
return function(){
var l=new Date;
o||r!==!1||(o=l);
var f=t-(l-o);
return e=this,u=arguments,0>=f?(clearTimeout(a),a=null,o=l,i=n.apply(e,u)):a||(a=setTimeout(c,f)),
i;
};
},E.debounce=function(n,t,r){
var e,u=null;
return function(){
var i=this,a=arguments,o=function(){
u=null,r||(e=n.apply(i,a));
},c=r&&!u;
return clearTimeout(u),u=setTimeout(o,t),c&&(e=n.apply(i,a)),e;
};
},E.once=function(n){
var t,r=!1;
return function(){
return r?t:(r=!0,t=n.apply(this,arguments),n=null,t);
};
},E.wrap=function(n,t){
return function(){
var r=[n];
return c.apply(r,arguments),t.apply(this,r);
};
},E.compose=function(){
var n=arguments;
return function(){
for(var t=arguments,r=n.length-1;r>=0;r--)t=[n[r].apply(this,t)];
return t[0];
};
},E.after=function(n,t){
return 0>=n?t():function(){
return--n<1?t.apply(this,arguments):void 0;
};
},E.keys=j||function(n){
if(n!==Object(n))throw new TypeError("Invalid object");
var t=[];
for(var r in n)E.has(n,r)&&t.push(r);
return t;
},E.values=function(n){
var t=[];
for(var r in n)E.has(n,r)&&t.push(n[r]);
return t;
},E.pairs=function(n){
var t=[];
for(var r in n)E.has(n,r)&&t.push([r,n[r]]);
return t;
},E.invert=function(n){
var t={};
for(var r in n)E.has(n,r)&&(t[n[r]]=r);
return t;
},E.functions=E.methods=function(n){
var t=[];
for(var r in n)E.isFunction(n[r])&&t.push(r);
return t.sort();
},E.extend=function(n){
return O(l.call(arguments,1),function(t){
if(t)for(var r in t)n[r]=t[r];
}),n;
},E.pick=function(n){
var t={},r=f.apply(i,l.call(arguments,1));
return O(r,function(r){
r in n&&(t[r]=n[r]);
}),t;
},E.omit=function(n){
var t={},r=f.apply(i,l.call(arguments,1));
for(var e in n)E.contains(r,e)||(t[e]=n[e]);
return t;
},E.defaults=function(n){
return O(l.call(arguments,1),function(t){
if(t)for(var r in t)void 0===n[r]&&(n[r]=t[r]);
}),n;
},E.clone=function(n){
return E.isObject(n)?E.isArray(n)?n.slice():E.extend({},n):n;
},E.tap=function(n,t){
return t(n),n;
};
var T=function(n,t,r,e){
if(n===t)return 0!==n||1/n==1/t;
if(null==n||null==t)return n===t;
n instanceof E&&(n=n._wrapped),t instanceof E&&(t=t._wrapped);
var u=s.call(n);
if(u!=s.call(t))return!1;
switch(u){
case"[object String]":
return n==String(t);

case"[object Number]":
return n!=+n?t!=+t:0==n?1/n==1/t:n==+t;

case"[object Date]":
case"[object Boolean]":
return+n==+t;

case"[object RegExp]":
return n.source==t.source&&n.global==t.global&&n.multiline==t.multiline&&n.ignoreCase==t.ignoreCase;
}
if("object"!=typeof n||"object"!=typeof t)return!1;
for(var i=r.length;i--;)if(r[i]==n)return e[i]==t;
r.push(n),e.push(t);
var a=0,o=!0;
if("[object Array]"==u){
if(a=n.length,o=a==t.length)for(;a--&&(o=T(n[a],t[a],r,e)););
}else{
var c=n.constructor,l=t.constructor;
if(c!==l&&!(E.isFunction(c)&&c instanceof c&&E.isFunction(l)&&l instanceof l))return!1;
for(var f in n)if(E.has(n,f)&&(a++,!(o=E.has(t,f)&&T(n[f],t[f],r,e))))break;
if(o){
for(f in t)if(E.has(t,f)&&!a--)break;
o=!a;
}
}
return r.pop(),e.pop(),o;
};
E.isEqual=function(n,t){
return T(n,t,[],[]);
},E.isEmpty=function(n){
if(null==n)return!0;
if(E.isArray(n)||E.isString(n))return 0===n.length;
for(var t in n)if(E.has(n,t))return!1;
return!0;
},E.isElement=function(n){
return!(!n||1!==n.nodeType);
},E.isArray=w||function(n){
return"[object Array]"==s.call(n);
},E.isObject=function(n){
return n===Object(n);
},O(["Arguments","Function","String","Number","Date","RegExp"],function(n){
E["is"+n]=function(t){
return s.call(t)=="[object "+n+"]";
};
}),E.isArguments(arguments)||(E.isArguments=function(n){
return!(!n||!E.has(n,"callee"));
}),"function"!=typeof/./&&(E.isFunction=function(n){
return"function"==typeof n;
}),E.isFinite=function(n){
return isFinite(n)&&!isNaN(parseFloat(n));
},E.isNaN=function(n){
return E.isNumber(n)&&n!=+n;
},E.isBoolean=function(n){
return n===!0||n===!1||"[object Boolean]"==s.call(n);
},E.isNull=function(n){
return null===n;
},E.isUndefined=function(n){
return void 0===n;
},E.has=function(n,t){
return p.call(n,t);
},E.noConflict=function(){
return n._=e,this;
},E.identity=function(n){
return n;
},E.times=function(n,t,r){
for(var e=Array(Math.max(0,n)),u=0;n>u;u++)e[u]=t.call(r,u);
return e;
},E.random=function(n,t){
return null==t&&(t=n,n=0),n+Math.floor(Math.random()*(t-n+1));
};
var N={
escape:{
"&":"&amp;",
"<":"&lt;",
">":"&gt;",
'"':"&quot;",
"'":"&#x27;",
"/":"&#x2F;"
}
};
N.unescape=E.invert(N.escape);
var q={
escape:new RegExp("["+E.keys(N.escape).join("")+"]","g"),
unescape:new RegExp("("+E.keys(N.unescape).join("|")+")","g")
};
E.each(["escape","unescape"],function(n){
E[n]=function(t){
return null==t?"":(""+t).replace(q[n],function(t){
return N[n][t];
});
};
}),E.result=function(n,t){
if(null==n)return void 0;
var r=n[t];
return E.isFunction(r)?r.call(n):r;
},E.mixin=function(n){
O(E.functions(n),function(t){
var r=E[t]=n[t];
E.prototype[t]=function(){
var n=[this._wrapped];
return c.apply(n,arguments),P.call(this,r.apply(E,n));
};
});
};
var z=0;
E.uniqueId=function(n){
var t=++z+"";
return n?n+t:t;
},E.templateSettings={
evaluate:/<%([\s\S]+?)%>/g,
interpolate:/<%=([\s\S]+?)%>/g,
escape:/<%-([\s\S]+?)%>/g
};
var B=/(.)^/,D={
"'":"'",
"\\":"\\",
"\r":"r",
"\n":"n",
"	":"t",
"\u2028":"u2028",
"\u2029":"u2029"
},C=/\\|'|\r|\n|\t|\u2028|\u2029/g;
E.template=function(n,t,r){
var e;
r=E.defaults({},r,E.templateSettings);
var u=new RegExp([(r.escape||B).source,(r.interpolate||B).source,(r.evaluate||B).source].join("|")+"|$","g"),i=0,a="__p+='";
n.replace(u,function(t,r,e,u,o){
return a+=n.slice(i,o).replace(C,function(n){
return"\\"+D[n];
}),r&&(a+="'+\n((__t=("+r+"))==null?'':_.escape(__t))+\n'"),e&&(a+="'+\n((__t=("+e+"))==null?'':__t)+\n'"),
u&&(a+="';\n"+u+"\n__p+='"),i=o+t.length,t;
}),a+="';\n",r.variable||(a="with(obj||{}){\n"+a+"}\n"),a="var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};\n"+a+"return __p;\n";
try{
e=new Function(r.variable||"obj","_",a);
}catch(o){
throw o.source=a,o;
}
if(t)return e(t,E);
var c=function(n){
return e.call(this,n,E);
};
return c.source="function("+(r.variable||"obj")+"){\n"+a+"}",c;
},E.chain=function(n){
return E(n).chain();
};
var P=function(n){
return this._chain?E(n).chain():n;
};
E.mixin(E),O(["pop","push","reverse","shift","sort","splice","unshift"],function(n){
var t=i[n];
E.prototype[n]=function(){
var r=this._wrapped;
return t.apply(r,arguments),"shift"!=n&&"splice"!=n||0!==r.length||delete r[0],P.call(this,r);
};
}),O(["concat","join","slice"],function(n){
var t=i[n];
E.prototype[n]=function(){
return P.call(this,t.apply(this._wrapped,arguments));
};
}),E.extend(E.prototype,{
chain:function(){
return this._chain=!0,this;
},
value:function(){
return this._wrapped;
}
});
}).call(this);
});