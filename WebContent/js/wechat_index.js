jQuery.fn.limit=function(){
	var self = $("[limit]");
	self.each(function(){
		var objString = $(this).text();
		var objLength = $(this).text().length;
		var num = $(this).attr("limit");
		if(objLength > num){
            $(this).attr("title",objString);
			objString = $(this).text(objString.substring(0,num) + "...");
		}
	})
}
$(function(){
	$("[limit]").limit();
	if(window.share_title!=null && window.share_title!=undefined && window.share_title!=""){
		initialWeChatShareParam(window.share_title);
	}else{
		initialWeChatShareParam();
	}
	
})

var ContentPath="/WeNewsAgency";
$(document).ready(function(){
	bindImgError();
	
});
$(window).load(function() { 
	$('img').each(function() {
	    if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) { 
	    	//http://wenews.top/WeNewsAgency/img/tx_default.jpg
	      this.src = $("#basePath").val()+"img/errorpic.jpg"; 
	      } 
	   });
});
function nofind(){
	var img=event.srcElement;
	img.src=$("#basePath").val()+"img/errorpic.jpg";
	img.onerror=null; //控制不要一直跳动
}
/* 图片绑定，展示失败的显示默认图片 */
function bindImgError(){

		$("img").error(function () {
		    $(this).unbind("error").attr("src", $("#basePath").val()+"img/errorpic.jpg");
		});
	
		
		
}

/*表格相关操作*/
var geticons=
	 {
		refresh : "glyphicon-repeat",
		toggle : "glyphicon-list-alt",
		columns : "glyphicon-list"
	};
function responseHandler(res) {

	if (res.Status==1) {
		return {
			"rows" : res.data?res.data:res.Data,
			"total" : res.total
		};
	} else {
		return {
			"rows" : [],
			"total" : 0
		};
	}

}
function addtoDeleteImageiDs(delImgID){
	if(delImgID!=""){
		var DelImages=$("#DelImages").val();
		if(DelImages!=""){
			DelImages+=",";
		}
		DelImages+=delImgID;
		$("#DelImages").val(DelImages);
	}
	
}
function addImageiDs(imgID){
	if(imgID!=""){
		var Images=$("#Images").val();
		if(Images!=""){
			Images+=",";
		}
		Images+=imgID;
		$("#Images").val(Images);
	}
	
}
function dispErrorRow(row, index) {
	//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
	var strclass = "";
	if (row.baseInfo && row.baseInfo.name == "删除") {
		strclass = 'danger';//还有一个active
	} else {
		return {};
	}
	return {
		classes : strclass
	}
}

function CheckhasChecked(obj,State){
	if(obj.bootstrapTable("getSelections").length<=0){
		ViewWarning();
		return false;
	}
	if(State!=null && obj.bootstrapTable("getSelections")[0].status==State){
		ViewWarningError("当前记录已经处于该状态,无法修改");
		return false;
	}
	return true;
}
//通过PKID来查看那或修改信息
function viewOrModifyByPKID(obj,url){
	var wid = obj.bootstrapTable("getSelections");
	var wids = "";
	if (wid.length > 0) {
		wids += wid[0].pkid;
	}
	if (wids != '') {
		window.location.href =url+wids ;
	}
}




/*获取记录ID*/
function getRecordPKIDs(obj){
	var wid = obj.bootstrapTable("getSelections");
	var wids = "";

	for ( var idobj = 0; idobj < wid.length; idobj++) {
		wids += "," + (wid[idobj].pkid==null?wid[idobj].ID:wid[idobj].pkid);
	}
	if(wids!=""){
		wids=wids.substr(1);
	}
	return wids;
}

function getRecordLogIDs(obj){
	var wid = obj.bootstrapTable("getSelections");
	var wids = "";

	for ( var idobj = 0; idobj < wid.length; idobj++) {
		wids += "," + (wid[idobj].logID==null?wid[idobj].logID:wid[idobj].logID);
	}
	if(wids!=""){
		wids=wids.substr(1);
	}
	return wids;
}

function ViewSuccess(errorobj){
	var obj=errorobj;
	if(obj==null){
		obj=$("#ErrorMessage");
	}
	obj.text('处理成功!').removeClass("alert-warning")
	.addClass("alert alert-success");
	obj.show();
	gotohidden(obj);
}
function gotohidden(obj){
	setTimeout(function(){
		obj.hide(100);
		obj.removeClass("alert-warning");
		obj.removeClass("alert");
		obj.removeClass("alert-success");
		obj.text("");
	},2000);
}
/**
 * 好多模块在用,不好删,所以下面新建了一个自定义错误信息的错误框
 */
function ViewWarning(errorobj){
	var obj=errorobj;
	if(obj==null){
		obj=$("#ErrorMessage");
	}
	obj.text('请选择要操作的行!').removeClass("alert-success").addClass("alert alert-warning");
	obj.show();
	gotohidden(obj);
}	
/**
 * 显示错误的错误框
 * @param error
 */
function ViewWarningError(error,errorobj){
	var obj=errorobj;
	if(obj==null){
		obj=$("#ErrorMessage");
	}
	obj.text(error).removeClass("alert-success").addClass("alert alert-warning");
	obj.show();
	gotohidden(obj);
}	



//清除错误信息
function clearError(obj){
	if(document.getElementById(obj)!=null){
		document.getElementById(obj).innerText="";
	}

}

function getNoMoreInfo(){
	return '<div id="NotMore" style="text-align:center;padding-top:5px;">我也是有底线嘀<br/></div>';
}
function getLoading(){
	return '<div class="sk-spinner sk-spinner-fading-circle"><div class="sk-circle1 sk-circle"></div><div class="sk-circle2 sk-circle"></div><div class="sk-circle3 sk-circle"></div><div class="sk-circle4 sk-circle"></div><div class="sk-circle5 sk-circle"></div><div class="sk-circle6 sk-circle"></div><div class="sk-circle7 sk-circle"></div><div class="sk-circle8 sk-circle"></div><div class="sk-circle9 sk-circle"></div><div class="sk-circle10 sk-circle"></div><div class="sk-circle11 sk-circle"></div><div class="sk-circle12 sk-circle"></div></div>';
}

//浏览器视口的高度
function getWindowHeight(){
	var windowHeight = 0;
	if(document.compatMode == "CSS1Compat"){
		windowHeight = document.documentElement.clientHeight;
	}else{
		windowHeight = document.body.clientHeight;
	}
	return windowHeight;
}
function getScrollTop(){
	var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
	if(document.body){
		bodyScrollTop = document.body.scrollTop;
	}
	if(document.documentElement){
		documentScrollTop = document.documentElement.scrollTop;	
	}
	scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
	return scrollTop;
}
//文档的总高度
function getScrollHeight(){
	var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
	if(document.body){
		bodyScrollHeight = document.body.scrollHeight;
	}
	if(document.documentElement){
		documentScrollHeight = document.documentElement.scrollHeight;
	}
	scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
	return scrollHeight;
}

//获取动态添加的活动和产品信息
function getNewProductAndActivityDOM(array,area,basePath,pc){
	var str = '';
	if(array.noticesList!=null){
		str += '<div class="index_activity">';
		str += ' <a href="'+ ContentPath +'/Product/phoneweChatPordDetail?_type_=2&_pid_='+array.noticesList.pkid  +'&_pc_='+ pc +'&_area_='+area+'">';
		str += '        <div class="list-header list-media"><img class="_image_100" src="'+(array.noticesList.student.imageUrl?array.noticesList.student.imageUrl.split(',')[0]:"")+'"><div class="list-body index_title"  style="height:34px">'+array.noticesList.author+'&nbsp;&nbsp;&nbsp;&nbsp;'+array.noticesList.sregion.name+'<p>'+array.noticesList.createTime.substr(0,19)+'</p></div></div>';
		str += '        <div class="index_title">'+array.title+'</div>';
		if(array.noticesList.image.id==0){
			str += '       <div class="mulitlinecontent">'+(array.noticesList.content)+'</div>';
		}else{
			str += '       <div style="height: 220px;overflow: hidden;"><img src="'+(array.noticesList.image.url?array.noticesList.image.url.split(',')[0]:"")+'" style="width: 100%"></div>';
		}
		str += '   </a>';
		str += '   <div class="index_footer_new">';
		str += '       <div class="index_name"><div class="list_dzBtn"><a href="javascript:;"><img src="'+ ContentPath +'/images/boforeJob.png"><span class="go_gref">'+array.noticesList.like+'</span></a></div></div>';
		str += '       <span class="index_name"><img class="list_read" src="'+ ContentPath +'/images/readNews.png">'+array.noticesList.clickCount+'</span>';
		str += '      <span class="index_read index_read_bg"  data-toggle="modal" ><img class="list_comment" src="'+ ContentPath +'/images/comment.png">'+array.noticesList.commentCount+'</span>';
		str += '   </div>';
		str += '</div>';
	}else if(array.video!=null){
		str += '				<li class="mui-table-view-cell mui-media mui-col-xs-6"><a ';
    	str += '					href="'+ ContentPath +'/Product/phoneweChatPordDetail?_type_=3&_pid_='+array.video.pkid  +'&_pc_='+ pc +'&_area_='+area+'" class="a-tag-goods-info">';
    	str += '						<div class="posi_re"><img class="mui-media-object" src="'+ (array.video.image?array.video.image.url.split(",")[0]:"") +'">';
    	str += '						<div class="video_bk">'+array.title+' </div><span class="video-btn"></span></div>';
    	str += '						<div class="mui-media-body">'+array.video.areaName+'</div>';
    	str += '						<div class="mui-media-body">'+array.video.student.name+'</div>';
    	str += '						<div class="mui-media-body"><span class="vide0_play"><img src="'+ ContentPath +'/images/play.png">'+ array.video.clickCount +'次</span><span class="vide0_zan"><img src="'+ ContentPath +'/images/zan.png">'+ array.video.likeCount +'个</span></div>';
    	str += '				</a></li>';
	}
	return str;
}

function AddNoMoreDOM(obj){
	$(".sk-spinner").remove();
	if($("#NotMore").length<=0){
		if(obj!=null){
			obj.append(getNoMoreInfo())
		}else{
			$("#container").append(getNoMoreInfo()); 
		}
	}
}

//组织查询参数
function MakeQueryParam(MainObj){
	var Paramobjs=MainObj.find("*[iscon='true']");
	var Params="";
	for(var i=0; i<Paramobjs.length;i++){
		var value="";
		var obj=$(Paramobjs.get(i));
		if(obj.is("input")){
			value=obj.val();
		}else if(obj.is("select")){
			value=obj.find("option:selected").val();
		}else if(obj.is("areatext")){
			value=obj.text();
		}
		if(value==null || value==undefined){
			value="";
		}
		Params+="&&"+$(Paramobjs.get(i)).attr("name")+"="+encodeURI(value);
	}
	return Params!=""?Params.substr(2):Params;
}
function MakeQueryParamJson(MainObj){
	var Paramobjs=MainObj.find("*[iscon='true']");
	var Params={};
	for(var i=0; i<Paramobjs.length;i++){
		var value="";
		var obj=$(Paramobjs.get(i));
		if(obj.is("input")){
			value=obj.val();
		}else if(obj.is("select")){
			value=obj.find("option:selected").val();
		}else if(obj.is("areatext")){
			value=obj.text();
		}
		if(value==null || value==undefined){
			value='0';
		}
		Params[$(Paramobjs.get(i)).attr("name")]=encodeURI(value)
	}
	return Params;
}
function MakeQueryParaNew(MainObj){
	return MakeQueryParam(MainObj);
}
//点击文本框复制其内容到剪贴板上方法
function copyToClipboard(txt) {
    if (window.clipboardData) {
        window.clipboardData.clearData();
        window.clipboardData.setData("Text", txt);
        alert("已经成功复制到剪帖板上！");
    } else if (navigator.userAgent.indexOf("Opera") != -1) {
        window.location = txt;
    } else if (window.netscape) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip) return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans) return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = txt;
        str.data = copytext;
        trans.setTransferData("text/unicode", str, copytext.length * 2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip) return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
        alert("已经成功复制到剪帖板上！");
    }
}
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNumber : params.offset
	};

}
function getImageList(){
	
	var buttomhtml='<div style="float:right;margin-top:-30px;"><ul class="pagination pagination-outline" ></li><li class="page-pre"><a href="javascript:prePage()">上一页</a></li><li class="page-pre disabled"><a id="CurrentPage" href="javascript:void(0)">1/100</a></li><li class="page-next"><a href="javascript:nextPage()">下一页</a></li></ul></div>';
	var htmlStr='';
		$.getJSON(ContentPath+"/ProdPicture/getProdPictureList", {
			WeChatID : $("#WeChatID").val(),
			pageSize : $("#pageSize").val(),
			pageNumber : $("#pageNumber").val(),
			soft:'time'
			
		}, function(json) {
			htmlStr+='<div class="picker">';
			if (json && json.Status == 1) {//
				if (json.data && json.data.length > 0) {
					htmlStr+='<select class="image-picker">';
					$.each(json.data, function(index, array) {
						console.log(array.url.split(",")[0]);
						htmlStr+='<option   data-img-src="'+ array.url.split(",")[0] +'" value="'+ array.pkid +'">'+ array.realUrl +'</option>';
					});
					htmlStr+='</select>';
				} else{
					htmlStr+='<div class="picker"><span>未查询到数据</span>';
				}
				htmlStr+='</div>';
				 $("#ImageListContainer").html(htmlStr+buttomhtml);
		         $("select.image-picker").imagepicker({
		    		hide_select:true
		    	});
		         $(".image_picker_selector img").click(function(){referInsertImageURL(this)})
		         initPageControl(json.total);
			}else{
				htmlStr+='<div class="picker"><span>未查询到数据</span></div>';
			}
		});

}

function initPageControl(total){
	var currentpage=parseInt($("#pageNumber").val())/parseInt($("#pageSize").val());
	var pageCount=parseInt(total)/parseInt($("#pageSize").val());
	currentpage++;
	if(currentpage<=1){
		currentpage=1;
		$(".page-pre").addClass("disabled");
		$(".page-pre a").removeAttr("href");
	}else{
		$(".page-pre").removeClass("disabled");
		$(".page-pre a").attr("href","javascript:prePage()");
	}
	if(currentpage>=pageCount){
		currentpage=pageCount;
		$(".page-next").addClass("disabled");
		$(".page-next a").removeAttr("href");
	}else{
		$(".page-next").removeClass("disabled");
		$(".page-next a").attr("href","javascript:nextPage()");
	}
	$("#CurrentPage").text(Math.ceil(currentpage)+"/"+Math.ceil(pageCount));
}
 function prePage(){
	 var currentpage=parseInt($("#pageNumber").val())-parseInt($("#pageSize").val());
	 if(currentpage<0){
		 currentpage=0;
	 }
	 $("#pageNumber").val(currentpage);
	 getImageList();
 }
 function nextPage(){
	 var currentpage=parseInt($("#pageNumber").val())+parseInt($("#pageSize").val());
	 $("#pageNumber").val(currentpage);
	 getImageList();
	
 }

function referInsertImageURL(obj){
	$("#note-image-url").val($(obj).attr("src"));
	 $(".note-image-btn").toggleClass("disabled", !$("#note-image-url").val()),
	 $(".note-image-btn").attr("disabled", !$("#note-image-url").val());
}
var ImageList=[];
function InitVideo(isAuto){
	$.getScript("http://imgcache.qq.com/tencentvideo_v1/tvp/js/tvp.player_v2_jq.js?v=3.3.7",function(){  //加载test.js,成功后，并执行回调函数
	if(isAuto==1){
		isAuto=true;
	}else{
		isAuto=false;
	}
	
	var videoList=$("div [name='div_videoList']");
	tvp.$.getScript($("#basePath").val()+'js/tvp.player.setting.js?t='+ (+new Date()), function(){
	
		for(var i=0;i<videoList.length;i++){
			if($("#VideoType").val()=="1" || $("#VideoType").val()=="2" || $(videoList.get(i)).attr("videotype")=="1" || $(videoList.get(i)).attr("videotype")=="2"){
				 $(videoList.get(i)).html('<video id="myMovie"  name="myMovie" src="'+ $(videoList.get(i)).attr("vid") +'" width="100%" height="100%" controls="" preload="auto"></video>');
			}else{
				var videoObj=$(videoList.get(i));
				var video = new tvp.VideoInfo();
				video.setVid(videoObj.attr("vid"));
				var player =new tvp.Player();
				player.addParam("showend",0);
				
				player.create({
					 width  : tvpSetting.width-30,
					  height : tvpSetting.height,
					  video  : video,
					  modId  : videoObj.attr("id"),
					  playerType: 'html5',
					  autoplay:isAuto
				});
				var videoimage=video.getVideoSnap()
				var videoObj=new Object();
				videoObj.img=videoimage[2];
				videoObj.id=$(videoList.get(i)).attr("id");
				ImageList.push(videoObj);
			}
		
	}
	
	});
	setTimeout(function() {
		try{
		for(var i=0;i<ImageList.length;i++){
			
			$("#"+ ImageList[i].id +" video").css("background-color","transparent");
			//$("#"+ ImageList[i].id +" video").css("background","url("+ ImageList[i].img +") center ");
			$("#"+ ImageList[i].id +" video").css("background-size","cover");
			 $(".tenvideo_player").attr("style","width:400px;height: 200px;	");
		}
		}catch(e){
			
		}
		
	},1000);
	});
	
}
var AuthCode_times=60;
var timer=null;
/*发送验证码*/
function SendAuthCode(PhoneNum){
	 // 计时开始
    $.getJSON(ContentPath+"/Users/getAuthCode", {
		Phone : PhoneNum,
	}, function(json) {
		webToast("验证码发送成功！", "bottom", 2000);
	});
    timer = setInterval(djs,1000);
}
function djs(){
    send.value = times+"秒后重试";
    send.setAttribute('disabled','disabled');
    send.style.background='#ccc';
    send.style.border='1px solid #ccc';
    times--;
    if(times <= 0){
        send.value = "发送验证码";
        send.style.background='#FF2626';
        send.removeAttribute('disabled');
        times = 60;
        clearInterval(timer);
    }
}


function initialRegionQueryInfo(panelbody,type,isempty){
	var AreaHTML="";
	AreaHTML +='<div class="col-md-3" id="ProvinceDiv"><label>省份</label><select class="form-control m-b" id="Province" onchange="getCity(this,'+ isempty +');" name="Province"><option></option></select></div>';
	AreaHTML +='<div class="col-md-3" id="CityDiv"><label>城市</label><select class="form-control m-b" id="City" onchange="getAreaID(this,'+ isempty +')" name="City"><option></option></select></div>';
	AreaHTML +='<div class="col-md-3" id="AreaIDDiv"><label>地区</label><select class="form-control m-b" onchange="getUnitAreaID(this,'+ isempty +')" id="AreaID" name="AreaID"><option value="0" selected></option></select></div>';
	AreaHTML +='<div class="col-md-3" id="UnitAreaIDDiv"><label>单位</label><select class="form-control m-b" onchange="getQueryList()" iscon="true" id="UnitAreaID" name="UnitAreaID"><option value="0" selected></option></select></div>';
	panelbody.append(AreaHTML);
	if(type=="query"){
		if($("#AdminLevelParseon").val()=="true"){
			$("#ProvinceDiv").css("display","none");
			$("#CityDiv").css("display","none");
			$("#AreaIDDiv").css("display","none");
			$("#UnitAreaIDDiv").css("display","none");
		}
	}else{
		if($("#AdminLevelArea").val()=='true'){
			$("#ProvinceDiv").attr("disabled","disabled");
			$("#CityDiv").attr("disabled","disabled");
			$("#AreaIDDiv").attr("disabled","disabled");
		}
	}

	getProvince();
}

function getProvince() {
	$.getJSON(ContentPath+"/Region/getRegionList", {
		ParentID : 1,
	}, function(json) {
		if (json && json.Status == 1) {
			$("#Province").empty();
	
			if (json.Data.length > 0) {
				
				$.each(json.Data, function(index, array) {
					if ($("#ProvinceID").val() == array.ID) {
						$("#Province").append(
								"<option selected='selected' value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					} else {
						$("#Province").append(
								"<option  value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					}
	
				});
			} 
			$("#Province").trigger("change");
		} else {
			return false;
		}
	});
}

function getProvince2() {
	$.getJSON(ContentPath+"/Region/getRegionList", {
		ParentID : 12,
	}, function(json) {
		if (json && json.Status == 1) {
			$("#Province").empty();
	
			if (json.Data.length > 0) {
				
				$.each(json.Data, function(index, array) {
					if ($("#ProvinceID").val() == array.ID) {
						$("#Province").append(
								"<option selected='selected' value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					} else {
						$("#Province").append(
								"<option  value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					}
	
				});
			} 
			$("#Province").trigger("change");
		} else {
			return false;
		}
	});
}
function getCity(ParentObj,isempty) {
	var ParentID = ParentObj.value;
	if (ParentID == -1 || ParentID=="") {
		$("#AreaID").empty();
		$("#City").empty();
		$("#UnitAreaID").empty();
		$("#City").append("<option ></option>");
		$("#AreaID").append("<option ></option>");
		$("#UnitAreaID").append("<option ></option>");
		if(window.getQueryList){
			getQueryList();
		}
	} else {
		$.getJSON(ContentPath+"/Region/getRegionList", {
			ParentID : ParentID,
		}, function(json) {
			if (json && json.Status == 1) {
				$("#City").empty();

				if (json.Data.length > 0) {
					if(isempty==1){
						$("#City").append("<option></option>");
					}
					$.each(json.Data, function(index, array) {
						if ($("#CityID").val() == array.ID || (BaiduMapAddressObj!=undefined && BaiduMapAddressObj.city==array.Name) ) {
							$("#City").append(
									"<option selected='selected' value='"+ array.ID +"'>"
											+ array.Name
											+ "</option>");
						} else {
							$("#City").append(
									"<option  value='"+ array.ID +"'>"
											+ array.Name
											+ "</option>");
						}

					});
				} else {
					$("#City").append(
							"<option value='"
									+ $(ParentObj).val()
									+ "'>"
									+ $(ParentObj).find(
											"option:selected").text()
									+ "</option>");
				}
				$("#City").trigger("change");
			} else {
				return false;
			}
		});

	}
}

function getAreaID(Obj,isempty) {
	var ParentID = Obj.value;
	if(ParentID==""){
		$("#AreaID").empty();
		$("#UnitAreaID").empty();
		$("#UnitAreaID").append("<option ></option>");
		if(window.getQueryList){
			getQueryList();
		}
		return false;	
	}
	$.getJSON(ContentPath+"/Region/getRegionList", {
		ParentID : ParentID,
	}, function(json) {
		if (json && json.Status == 1) {
			$("#AreaID").empty();
			if (json.Data.length > 0) {
				if(isempty==1){
					$("#AreaID").append("<option></option>");		
				}
				$.each(json.Data, function(index, array) {
					if ($("#CityAreaID").val() == array.ID || (BaiduMapAddressObj!=undefined && BaiduMapAddressObj.district==array.Name)) {
						$("#AreaID").append(
								"<option selected='selected' value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					} else {
						$("#AreaID").append(
								"<option  value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					}
				});
			} else {
				$("#AreaID").append(
						"<option value='" + $(Obj).val() + "'>"
								+ $(Obj).find("option:selected").text()
								+ "</option>");
				
			}
			$("#AreaID").trigger("change");
			
		} else {
			return false;
		}
	});

}


function getUnitAreaID(Obj,isempty) {
	var ParentID = Obj.value;
	if(ParentID==""){
		$("#UnitAreaID").empty();
		if(window.getQueryList){
			getQueryList();
		}
		return false;	
	}
	$.getJSON(ContentPath+"/Region/getRegionList", {
		ParentID : ParentID,
	}, function(json) {
		if (json && json.Status == 1) {
			$("#UnitAreaID").empty();
			if (json.Data.length > 0) {
				if(isempty==1){
					$("#UnitAreaID").append("<option></option>");		
				}
				$.each(json.Data, function(index, array) {
					if ($("#UnitID").val() == array.ID) {
						$("#UnitAreaID").append(
								"<option selected='selected' value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					} else {
						$("#UnitAreaID").append(
								"<option  value='"+ array.ID +"'>"
										+ array.Name
										+ "</option>");
					}
				});
			} else {
				$("#UnitAreaID").append(
						"<option value='" + $(Obj).val() + "'>"
								+ $(Obj).find("option:selected").text()
								+ "</option>");
				
			}
			$("#UnitAreaID").trigger("change");
			if(window.getQueryList){
				getQueryList();
			}
			if(window.getAreaURL){
				getAreaURL();
			}
			
		} else {
			return false;
		}
	});
}
var BaiduMapAddressObj;
//调用百度地图,获取当前地址
function getCurrentlocation(){
	$.getScript("http://api.map.baidu.com/api?v=2.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl",function(){  //加载test.js,成功后，并执行回调函数
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				BaiduMapAddressObj=r.address;
				setCurrentlocation(BaiduMapAddressObj);
			}
			     
		},{enableHighAccuracy: true});
	});
}
function setCurrentlocation(Address){
	$("#Province option").each(function(obj){
		if($("#Province option")[obj].text==Address.province){
			$($("#Province option")[obj]).attr("selected","selected");
		}
	});
	$("#Province").trigger("change");
	$("#AreaID option").each(function(obj){
		if($("#AreaID option")[obj].text==Address.province){
			$($("#AreaID option")[obj]).attr("selected","selected");
		}
	});
	$("#AreaID").trigger("change");
	$("#UnitAreaID option").each(function(obj){
		if($("#UnitAreaID option")[obj].text==Address.province){
			$($("#UnitAreaID option")[obj]).attr("selected","selected");
		}
	});
	
}


function checkCollapse(obj){
	if($("#collapseThree").css("height")=="0px"){
		obj.text("查询条件(点击收开)");
	}else{
		obj.text("查询条件(点击展开)");
	}
}

//初始化微信分享参数
function initialWeChatShareParam(title){
	$.getScript("http://res.wx.qq.com/open/js/jweixin-1.0.0.js",function(){  //加载test.js,成功后，并执行回调函数
		var url=location.href.split('#')[0];
		var basePath=window.location.origin+"/WeNewsAgency/";
		var dataContent=$("#dataContent").val();
		if($("#basePath").length>0 && $("#basePath").val()!=""){
			basePath=$("#basePath").val();
		}
		if(title==null || title==undefined || title==""){
			title = document.title;
		}
		var imageurl=$(".img_response").eq(1).attr("src");
		if(imageurl==undefined || imageurl==""){
			imageurl = basePath+"img/errorpic2.jpg";
		}
		if(dataContent==null || dataContent==undefined ||  dataContent==""){
			dataContent='新浪微新闻社-致力于打造全媒体小主播';
		}
		$.ajax({
			url:basePath+"Product/getSignature",
			data:{
				'URL':url,
				'appid':'wxab5e4ee5a20faf3f'
			},
			success : function(obj) {
				if(obj.status==1){
						wx.config({
						debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId: 'wxab5e4ee5a20faf3f', // 必填，公众号的唯一标识
						timestamp:obj.timeStamp, // 必填，生成签名的时间戳
						nonceStr: obj.nonceStr, // 必填，生成签名的随机串
						signature: obj.signature,// 必填，签名，见附录1
						jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','startRecord','stopRecord','onVoiceRecordEnd','translateVoice'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});    
					wx.ready(function () {
					//	document.querySelector('#wechat').onclick = function () {
							wx.onMenuShareTimeline({
								title: title, // 分享标题
								link: url, // 分享链接
								imgUrl: imageurl, // 分享图标
								success: function () { 
									// 用户确认分享后执行的回调函数
									if(window.callbackFun){
										callbackFun(1);
									}
									
								},
								cancel: function () { 
									//$("#myModal1").modal("hide")
									webToast("取消分享","bottom",3000);
								}
							});
					//	}
					//	document.querySelector('#wefriend').onclick = function () {
							wx.onMenuShareAppMessage({
								title: title, // 分享标题
								desc: dataContent, // 分享描述
								link: url, // 分享链接
									imgUrl: imageurl, // 分享图标
								type: 'link', // 分享类型,music、video或link，不填默认为link
								dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
								success: function () { 
									callbackFun(2);
								},
								cancel: function () { 
									//$("#myModal1").modal("hide")
									webToast("取消分享","bottom",3000);
								}
							});
						
				
					})
					
					
				}else{
				}
			}});
		});
}



var checkAuthenticationName = function (a) {
    //var patrn = /^[0-9]*[1-9][0-9]*$/;
   // if (!patrn.exec(a)) return false;
    return !isNaN(a);
};

function initialWePayParam(str_but,str_name,attach,wid){
   var url=location.href.split('#')[0];
   var timestamp="";
    var nonceStr="";
    var basePath=window.location.origin+"/WeNewsAgency/";
    var price = $(str_name).val();
    var trade_type = getQueryString("submitType");
	if($("#basePath").val()!=""){
		basePath=$("#basePath").val();
	}
	if(attach==null || attach==undefined || attach==""){
		attach= "{type:1,uid:0,bid:0}";//默认为微米充值
	}
	 if(trade_type=='mweb' || trade_type=='pc' ){
		 trade_type= 'MWEB';
		 document.querySelector(str_but).onclick = function () {
			 price= $(str_name).val();
		   	  	window.location = getAliPayUrl(price,attach);
		 }
	   	  return false;
	     }else{
	   	  	trade_type = 'JSAPI';
	     }
$.ajax({
	url:basePath+"Product/getSignature",
	data:{
		'URL':url,
		'WID':wid
	},
	success : function(obj) {
		if(obj.status==1){
				timestamp = obj.timeStamp;
				nonceStr=obj.nonceStr;
				wx.config({
				debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId: obj.AppID, // 必填，公众号的唯一标识 wxab5e4ee5a20faf3f
				timestamp:obj.timeStamp, // 必填，生成签名的时间戳
				nonceStr: obj.nonceStr, // 必填，生成签名的随机串
				signature: obj.signature,// 必填，签名，见附录1
				jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
				
			wx.ready(function () {
				
				document.querySelector(str_but).onclick = function () {
					price= $(str_name).val();
                    if (checkAuthenticationName(price)) {
                        $.ajax({
                        url:basePath+"WeMoney/payWeChat",
                        data:{
                            'pirce':price,
                            'ip':returnCitySN["cip"],
                            'timestamp':timestamp,
                            'nonceStr':nonceStr,
                            'attach':attach,
                            'trade_type':trade_type,
                    		'WID':wid
                        },
                        success : function(obj) {
                            if (obj.status==1) {
                                data=JSON.parse(obj.data);
                                /*调用H5支付*/
                                if(trade_type=="MWEB"){
                                	window.location = json.data.mweb_url;
                                }else{
                                	 wx.chooseWXPay({
 		                                timestamp: timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
 		                                nonceStr: nonceStr, // 支付签名随机串，不长于 32 位
 		                                package: data.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
 		                                signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
 		                                paySign: data.paySign, // 支付签名
 		                                success: function (res) {
 		                                // 支付成功后的回调函数
 		                                	callbackFun(price,3);
 		                                }
 		                         });
                                }
                                
                            }
                        }})
                    } else {
                        setTimeout(function() {
                            webToast("请输入正确金额！","bottom", 2000);
                        }, 300);
                    }
                    return false;
				}

				
			})
			
			
		}else{
		}
	
	}});
function getAliPayUrl(WIDtotal_fee,attach){
	var url=basePath+"WeMoney/alipayapi?WIDtotal_fee="+WIDtotal_fee+"&WIDsubject="+window.document.title+"&WIDshow_url="+encodeURIComponent(window.top.location.href)+"&attach="+attach+"&rand"+Math.random();
	return url;
}
 
}


/*获取Url参数*/
function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}


////初始化投稿趋势
function initialNoticesStatChat(myChart,data,type){
	var title=data.title;
	 var days=[];
	  for(var i=0;i<data.dayCount;i++){
		  if(data.type==1){
			  if(data.daysTime[i].length>=19){
				  days.push(data.daysTime[i].substring(0,19));
			  }else{
				  days.push(data.daysTime[i]);
			  }
			  
		  }else{
			  if(data.dayCount==12){
				  days.push((i+1)+'月');
			  }else{
				  days.push((i+1)+'号');
			  }
		  }
		  
		  
	  }
	  var series=[
			        {
			            name:data.name1,
			            type:'line',
			            data:data.data1,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name2,
			            type:'line',
			            data:data.data2,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name3,
			            type:'line',
			            data:data.data3,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name4,
			            type:'line',
			            data:data.data4,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name5,
			            type:'line',
			            data:data.data5,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name6,
			            type:'line',
			            data:data.data6,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name7,
			            type:'line',
			            data:data.data7,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name8,
			            type:'line',
			            data:data.data8,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name9,
			            type:'line',
			            data:data.data9,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name10,
			            type:'line',
			            data:data.data10,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name11,
			            type:'line',
			            data:data.data11,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        },
			        {
			            name:data.name12,
			            type:'line',
			            data:data.data12,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        }
			    ];
	  if(data.dayCount==12 || data.type==1 ){
		  if(data.LineCount!=null && data.LineCount!=undefined ){
			  series=[];
			  for(var lc=1;lc<=data.LineCount;lc++){
				  var obj={
				            name:eval("data.name"+lc),
				            type:'line',
				            data:eval("data.data"+lc),
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'} 
				                ] 
				            },
				        };
				  series.push(obj);
			  }
		  }else{
			  series=[
				        {
				            name:data.name1,
				            type:'line',
				            data:data.data1,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'} 
				                ] 
				            },
				        }];
		  }
		 
	 }
	  
	  myChart.setOption({
		  title : {
		    	 text: title,
		    },
		    tooltip: {trigger: "axis"},
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data :days
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} 条'
		            }
		        }
		    ],
		    series : series
		  })
}
