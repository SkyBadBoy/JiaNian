<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page import="wtb.smUtil.SmBaseGlobal"%>
<%@ page import="wtb.smUtil.SmBaseUtil"%>
<%@ page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("path", path);
	if(user==null){
		user=new Students();
	}
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑新闻</title>
    <link rel="shortcut icon" href="<%=basePath%>img/logo.ico"/>
    <link rel="stylesheet" href="<%=basePath%>css/common.css">
   <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
   <style type="text/css">
   .navbar .topleft{
	height:100%;
bottom:0;
} 
   
        #loading {
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/loading_refresh.gif) no-repeat center center #FFF;
            background-size: 10%;
        }
        ul, li {
            padding: 0;
            margin: 0 10px;
        }
        .ul_pics li {
            float: left;
            width: 23%;
            margin: 0 1% 10px;
            list-style: none;
        }
        .progress {
            position: relative;
            padding: 1px;
            border-radius: 3px;
            margin: 100% 0 0 0;
        }
        .bar {
            background-color: green;
            display: block;
            width: 0%;
            height: 20px;
            border-radius: 3px;
        }
        .percent {
            position: absolute;
            height: 20px;
            display: inline-block;
            top: 3px;
            left: 2%;
            color: #fff
        }
        .clearfix:after {
            visibility: hidden;
            display: block;
            font-size: 0;
            content: " ";
            clear: both;
            height: 0
        }
        .img_common {
            width: 69px;
            height: 69px;
        }

        .editor-outer {
            padding: 10px;
            background: #fff
        }
        .cdv select {
            margin: 10px auto 0 auto;
            width: 100%;
            height: 30px;
            line-height: 30px;
            border: 1px solid #ddd;
            color: #000;
        }
        button {
            margin-bottom: 10px;
            background-color: #ddd;
            height: 30px;
            width: 50%;
            border-radius: 5px;
        }
        .orderfinished-help{position: fixed;bottom: 10px;left:3%;margin: 0;height: 55px;line-height: 55px;}
        .orderfinished-help a{font-size: 18px;}
        .cp-editor{width:100%;height:150px;border: 1px solid #E3E6F2;border-radius: 6px;padding:5px;}
        .verify_phoneico span{font-size: 17px;}
        .verify_phoneico{margin-left:10px;color: #282930;width: 13%;}
         input[type=text], textarea{font-size: 16px;color: #A3A3A3;}
        .verify_phonenum{border-bottom:1px solid #E3E6F2;height: 45px;line-height: 45px;}
        .wordage_title{color: #A3A3A3;float: right;font-size: 14px;position: absolute;right: 10px;}
        .wordage_conment{color: #A3A3A3;float: right;font-size: 14px;}
        .verify_phoneright{padding-top: 2px;width: 67%;margin-left: 0;}
     </style>
</head>
<body>
<!--后退-->
<input type="hidden" id="imgID"> 
<input type="hidden" id="imgUrl"> 
<input type="hidden" id="Tempdata" value="${Tempdata}"> 
<input type="hidden" id="TempTitle" value="${data.title}"> 
<input type="hidden" id="TempContent" value="${data.content}"> 

<input type="hidden" id="idlist" value="${data.imageIDList}"> 
<input type="hidden" id="imageUrl" value="${data.imageUrl}"> 
<input type="hidden" id="imageLiId" value="${data.imgLiId }">
<input type="hidden" id="imageLiSrc" value="${data.imgLiSrc }">
<input type="hidden" id="NoticeID" value="${NoticeID }">
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
 <nav class="navbar text-center " id="back_away" >
        <button class="topleft" onclick="back()"><i class="fa fa-angle-left fa-3x"></i></button>
        <a class="navbar-tit center-block" style="    line-height: 15px;">发布新闻</a>
<c:if test="${empty NoticeID}">
        	<a href="javascript:void(0);" class="topnav" id="phone_modify_submit">发布</a>
        </c:if>
           <c:if test="${not empty NoticeID}">
        	<a href="javascript:void(0);" class="topnav" id="phone_modify_submit">保存</a>
        </c:if>    </nav>
    <div style="height:50px;"></div>
 <form id="noticesForm" modelAttribute="NoticesForm" name="noticesForm"
					enctype="multipart/form-data"
					action="${path}/Notices/addphoneNotices" method="post">
					<input type="hidden" name="submittype" id="submittype" value="phone" />
					<input type="hidden" name="ImageIDList" id="ImageIDList" value="" />
					<input type="hidden" name="SID" id="SID" value="${SID}" />
					<input id="fileImage" style="display: none;" type="file" size="30" name="fileImage">
    <div class="verify_phonenum">
        <div class="verify_phoneico"><span>标题</span></div>
        
        <c:if test="${data.title eq '' }">
        	<input path="Title" id="Title" maxlength="20" spellcheck="false" value="" class="verify_phoneright" type="text" placeholder="请输入标题"/>
        </c:if>
        <c:if test="${data.title ne '' }">
        	<input path="Title" id="Title" maxlength="20" spellcheck="false" value="${data.title}" class="verify_phoneright" type="text" placeholder="请输入标题"/>
        </c:if>
        <div id="wordage_title" class="wordage_title"></div>

    </div>
    <div class="editor-outer">
       <c:if test="${data.content eq '' }">
       		<textarea id="content" class="cp-editor"  id="content" placeholder="请输入内容，500字符以内...">${Content}</textarea>
        </c:if>
        <c:if test="${data.content ne '' }">
       		<textarea id="content" class="cp-editor"  id="content" placeholder="请输入内容，500字符以内...">${data.content}</textarea>
        </c:if>
        <div id="wordage_conment" class="wordage_conment"></div>
    </div>


<div class="clearfix" style="background-color: #fff;padding-bottom:10px;margin-top: 15px">
    <ul id="ul_pics" class="ul_pics clearfix">
    	
        <li><img src="<%=basePath%>images/weixinwenshe/download.png" id="btn" class="img_common"/></li>
    </ul>
</div>

</form>

<!--用于活动照片添加开始 -->

<!--用于活动照片添加结束-->
<!-- div id="demo" class="demo"></div> -->
<input type="hidden" name="backUrl" id="backUrl" value="<%=basePath%>include/pictureback.html" />
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/plugin/plupload/plupload.full.min.js?v=3.3.7"></script>

 <script type="text/javascript">

		/**
	 * 文本框根据输入内容自适应高度
	 * @param                {HTMLElement}        输入框元素
	 * @param                {Number}                设置光标与输入框保持的距离(默认0)
	 * @param                {Number}                设置最大高度(可选)
	 */
	var autoTextarea = function (elem, extra, maxHeight) {
	        extra = extra || 0;
	        var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
	        isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
	                addEvent = function (type, callback) {
	                        elem.addEventListener ?
	                                elem.addEventListener(type, callback, false) :
	                                elem.attachEvent('on' + type, callback);
	                },
	                getStyle = elem.currentStyle ? function (name) {
	                        var val = elem.currentStyle[name];
	 
	                        if (name === 'height' && val.search(/px/i) !== 1) {
	                                var rect = elem.getBoundingClientRect();
	                                return rect.bottom - rect.top -
	                                        parseFloat(getStyle('paddingTop')) -
	                                        parseFloat(getStyle('paddingBottom')) + 'px';        
	                        };
	 
	                        return val;
	                } : function (name) {
	                                return getComputedStyle(elem, null)[name];
	                },
	                minHeight = parseFloat(getStyle('height'));
	 
	        elem.style.resize = 'none';
	 
	        var change = function () {
	                var scrollTop, height,
	                        padding = 0,
	                        style = elem.style;
	 
	                if (elem._length === elem.value.length) return;
	                elem._length = elem.value.length;
	 
	                if (!isFirefox && !isOpera) {
	                        padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
	                };
	                scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
	 
	                elem.style.height = minHeight + 'px';
	                if (elem.scrollHeight > minHeight) {
	                        if (maxHeight && elem.scrollHeight > maxHeight) {
	                                height = maxHeight - padding;
	                                style.overflowY = 'auto';
	                        } else {
	                                height = elem.scrollHeight - padding;
	                                style.overflowY = 'hidden';
	                        };
	                        style.height = height + extra + 'px';
	                        scrollTop += parseInt(style.height) - elem.currHeight;
	                        document.body.scrollTop = scrollTop;
	                        document.documentElement.scrollTop = scrollTop;
	                        elem.currHeight = parseInt(style.height);
	                };
	        };
	 
	        addEvent('propertychange', change);
	        addEvent('input', change);
	        addEvent('focus', change);
	        change();
	};

	 var text = document.getElementById("content");
	   autoTextarea(text);// 调用
 

	  if($("#Tempdata").val()==1){
	        popTipShow.confirm('发布小提示','发现上次编辑文章的草稿,是否要载入吗?',['确 定','取 消'],
                function(e){
	    
	        	var button = $(e.target).attr('class');
				  if(button == 'ok'){
	        		$("#content").val($("#TempContent").val());
	        		$("#Title").val($("#TempTitle").val());
		        	if ($("#idlist").val()!="") {
		        		var idlist=$("#idlist").val().split(',');
		        	    var imageUrl=$("#imageUrl").val().split(',');
		        	    var imageLiId=$("#imageLiId").val().split(',');
		        	    var imageLiSrc=$("#imageLiSrc").val().split(',');
		        	    var html="";
		        	    for (var i = 0; i < idlist.length; i++) {
		        	    html+='<li id="'+imageLiId[i]+'">'+
		        	          '<input type="hidden" name="pic[]" value="'+idlist[i]+'">'+
		        	          '<input type="hidden" value="'+imageLiId[i]+'" name="imgLiId">'+
		        	          '<input type="hidden" value="'+imageLiSrc[i]+'" name="imgLiSrc">'+
		        	          '<input type="hidden" name="pic_name[]" value="'+imageUrl[i]+'">'+
		        	          "<img class='img_common' onclick=delPic('"+imageLiSrc[i]+"','"+imageLiId[i]+"') src='"+imageUrl[i]+"'>"+
		        	          '</li>';
		        	    }
		        	    html+='<li><img src="<%=basePath%>images/weixinwenshe/download.png" id="btn" class="img_common"/></li>';
		        	    $("#ul_pics").html(html);
		        	    initialUpload();
		        	 }
				  }else{
		    	    	$("#content").val("");
		        		$("#Title").val("");
		        		
		    	  }
				  this.hide();
              })
	        
	    }else{
        	if ($("#idlist").val()!="") {
        	    var idlist=$("#idlist").val().split(',');
        	    var imageUrl=$("#imageUrl").val().split(',');
        	    var imageLiId=$("#imageLiId").val().split(',');
        	    var imageLiSrc=$("#imageLiSrc").val().split(',');
        	    var html="";
        	    for (var i = 0; i < idlist.length; i++) {
        	    html+='<li id="'+imageLiId[i]+'">'+
        	          '<input type="hidden" name="pic[]" value="'+idlist[i]+'">'+
        	          '<input type="hidden" value="'+imageLiId[i]+'" name="imgLiId">'+
        	          '<input type="hidden" value="'+imageLiSrc[i]+'" name="imgLiSrc">'+
        	          '<input type="hidden" name="pic_name[]" value="'+imageUrl[i]+'">'+
        	          "<img class='img_common' onclick=delPic('"+imageLiSrc[i]+"','"+imageLiId[i]+"') src='"+imageUrl[i]+"'>"+
        	          '</li>';
        	    }
        	    html+='<li><img src="<%=basePath%>images/weixinwenshe/download.png" id="btn" class="img_common"/></li>';
        	    $("#ul_pics").html(html);

        	 }
	    }
  
 setInterval("sendCookie()",30000);
 
 function sendCookie(){
	 var obj=$("* [name='pic[]']");
	 var newIds="";
	 for(var i=0;i<obj.length;i++){
		 if(newIds!=""){
			 newIds+=","+$(obj[i]).val();
		 }else{
			 newIds+=$(obj[i]).val();
		 }
	 }
	 var obj=$("* [name='pic_name[]']");
	 var pic_name="";
	 for(var i=0;i<obj.length;i++){
		 if(pic_name!=""){
			 pic_name+=","+$(obj[i]).val();
		 }else{
			 pic_name+=$(obj[i]).val();
		 }
	 }

    var obj=$("* [name='imgLiId']");
     var imgLiId="";
     for(var i=0;i<obj.length;i++){
         if(imgLiId!=""){
             imgLiId+=","+$(obj[i]).val();
         }else{
             imgLiId+=$(obj[i]).val();
         }
     }
    var obj=$("* [name='imgLiSrc']");
     var imgLiSrc="";
     for(var i=0;i<obj.length;i++){
         if(imgLiSrc!=""){
             imgLiSrc+=","+$(obj[i]).val();
         }else{
             imgLiSrc+=$(obj[i]).val();
         }
     }

	 $("#imgID").val(newIds);
	 $("#imgUrl").val(pic_name);
	if($("#Title").val()!="" || $("#content").val()!="" || $("#imgID").val()!=""){
		 $.ajax({
				url : "${path}/Notices/saveCookie",
				
				data : {
					'title' : $("#Title").val(),
					'content' : $("#content").val(),
					'imageIDList' : $("#imgID").val(),
					'imageUrl':$("#imgUrl").val(),
	                'imgLiId':imgLiId,
	                'imgLiSrc':imgLiSrc,
	                "noticeID":$("#NoticeID").val()
	
				},
				success : function(json) {
					 webToast(json.Message,"bottom",3000);
				}
			});
	}
 }
 var Error=$('* [id="Content.errors"]').text();
 if(Error==""){
	 Error=$('* [id="Title.errors"]').text();
 }
 if(Error && Error!=""){
	 webToast("保存失败,请重试,请检测是否包含表情字符,目前暂不支持表情字符","bottom",10000);
 }
 $("#loading").css("display","none");
 
 var limitNum_Title = 0;
 var pattern_Title =  limitNum_Title + '/20';
 $('#wordage_title').html(pattern_Title);
 $('#Title').keyup(
         function(){
             var remain = $(this).val().length;
             if(remain > 20){
                 var result = limitNum_Title + remain;
                 pattern_Title = " <span style='color: #ff2918'>"+result + '/20'+"</span>";
             }else{
                 var result_title = limitNum_Title + remain;
                 pattern_Title =  result_title + '/20';
             }
             $('#wordage_title').html(pattern_Title);
         }
 );
 var limitNum_Comment = 0;
 var pattern_Comment = limitNum_Comment + '/500';
 $('#wordage_conment').html(pattern_Comment);
 $('#content').keyup(
         function(){
             var remain = $(this).val().length;
             if(remain > 500){
                 var result = limitNum_Title + remain;
                 pattern_Comment = " <span style='color: #ff2918'>"+result + '/500'+"</span>";
             }else{
                 var result_Comment = limitNum_Title + remain;
                 pattern_Comment =  result_Comment + '/500';
             }
             $('#wordage_conment').html(pattern_Comment);
         }
 );

 var checkCommentTitle = function (a) {
	 if(a.length>20 || a.length==0)return false
     return true;
 };
 var checkComment = function (a) {
     if(a.length>500 || a.length==0)return false
     return true;
 };
 function autocommit(){
	 if($(".show").length>0){
		 $("#alert_ok").click();
	 }
 }
 $("#phone_modify_submit").on("click", function () {
	 
     var str_contribute_title = $("#Title").val();
     var str_contribute_comment = $("#content").val();
     if ( checkCommentTitle(str_contribute_title)) {
         if (checkComment(str_contribute_comment)) {
             setTimeout(function() {
            	 var obj=$("* [name='pic[]']");
            	 var newIds="";
            	 for(var i=0;i<obj.length;i++){
            		 if(newIds!=""){
            			 newIds+=","+$(obj[i]).val();
            		 }else{
            			 newIds+=$(obj[i]).val();
            		 }
            	 }
            
            	 $("#ImageIDList").val(newIds);
            	 setTimeout("autocommit()",5000); 
            	 
          			popTipShow.confirm('发布小提示','发布此条新闻吗？5秒后将会自动发布',['确 定','取 消'],
          				function(e){
          				  //callback 处理按钮事件
          				  var button = $(e.target).attr('class');
          				  if(button == 'ok'){
          					$("#loading").css("display","block");
          					//按下确定按钮执行的操作
          					//todo ....				
          					this.hide();
          					 $.ajax({
         						url : "${path}/Notices/addphoneNotices?",
         						type:"POST",
         						data : {
         							'title' : $("#Title").val(),
         							'content' : $("#content").val(),
         							'imageIDList' : $("#ImageIDList").val(),
         							"NoticeID":$("#NoticeID").val(),
         							'DeviceType':<%=SmBaseGlobal.NoticeDeviceType.WeChat.getid()%>
         						},
         						success : function(json) {
         							if(json.Status=="true"){
         								$("#loading").css("display","none");
         								 webToast("发布成功,正在跳转！","bottom", 1000);
         								 window.location.href="<%=path%>/Students/phoneSinaMyNews?sina=<%=SmBaseUtil.Random()%>";
         							}else{
         								webToast(json.Message,"bottom", 2000);
         								$("#loading").css("display","none");
         							}
         						}
         					});
          				  }
          				 if(button == 'cancel') {
         					//按下取消按钮执行的操作
         					//todo ....
         					 $("#loading").css("display","none");
         					this.hide();
         					
         				  }
          				}
          			);
             }, 300);
         } else {
             setTimeout(function() {
                 webToast("请输入1到500字的新闻内容！","bottom", 2000);
             }, 300);
             $("#loading").css("display","none");
         }
     } else {
         setTimeout(function() {
             webToast("请输入1到20字的新闻标题！","bottom", 2000);
         }, 300);
         $("#loading").css("display","none");
     }
     return false;
 });

 function back(){
    if(checkCommentTitle($("#Title").val())){
        popTipShow.confirm('发布小提示','文章正在编辑中,确认要退出吗?',['确 定','取 消'],
                        function(e){
                            var button = $(e.target).attr('class');
                            if(button == 'ok'){
                                javascript:history.go(-1);
                            }
                            if(button == 'cancel') {
                                this.hide();
                            }
                        })
    }else{
        javascript:history.go(-1);
    }
 }
 
 function initialUpload(){
	// 图片上传
	 var uploader = new plupload.Uploader({//创建实例的构造方法
	     runtimes: 'html5,flash,silverlight,html4', //上传插件初始化选用那种方式的优先级顺序
	     browse_button: 'btn', // 上传按钮
	     url: "<%=SmBaseGlobal.PictureService%>?uid=<%=user.getPKID()%>&backUrl="+$("#backUrl").val(), //远程上传地址
	     flash_swf_url: 'plupload/Moxie.swf', //flash文件地址
	     silverlight_xap_url: 'plupload/Moxie.xap', //silverlight文件地址
	     filters: {
	         max_file_size: '10mb', //最大上传文件大小（格式100b, 10kb, 10mb, 1gb）
	         mime_types: [//允许文件上传类型
	             {title: "files", extensions: "*,jpg,png,gif,jpeg"}
	         ]
	     },
	     multi_selection: true, //true:ctrl多文件上传, false 单文件上传
	     init: {
	         FilesAdded: function (up, files) { //文件上传前
	             if ($("#ul_pics").children("li").length > 5) {
	                 webToast("您上传的图片太多了！","bottom", 2000);
	                 uploader.destroy();
	             } else {
	                 var li = '';
	                 plupload.each(files, function (file) { //遍历文件
	                     li += "<li  id='" + file['id'] + "' ><div class='progress'><span class='bar'></span><span class='percent'>0%</span></div></li>";
	                 });
	                 $("#ul_pics").prepend(li);

	                 uploader.start();
	             }
	         },
	         UploadProgress: function (up, file) { //上传中，显示进度条
	             var percent = file.percent;
	             $("#" + file.id).find('.bar').css({"width": percent + "%"});
	             $("#" + file.id).find(".percent").text(percent + "%");
	         },
	         FileUploaded: function (up, file, info) { //文件上传成功的时候触发
	        	 var data=getIframeVal(info);
	            if(data!=null){
	            	$("#" + file.id).html("<input type='hidden' value='"+file.id+"' name='imgLiId'><input type='hidden' value='"+data.ImageURL.image_src+"' name='imgLiSrc'><input type='hidden'name='pic[]' value='" + data.Status + "'/><input type='hidden'name='pic_name[]' value='" + data.ImageURL.image_800 + "'/><img class='img_common' onclick=delPic('" + data.ImageURL.image_src + "','" + file.id + "') src='" + data.ImageURL.image_800 + "'/>");//追加图片
	            }
	         },
	         Error: function (up, err) { //上传出错的时候触发
	             webToast(err.message,"bottom", 2000);
	         }
	     }
	 });
	 uploader.init();
 }
 initialUpload();
 function delPic(pic, file_id) { //删除图片 参数1图片路径  参数2 随机数
    $("#" + file_id).remove();
 }

 function getIframeVal(val)  
 {  
	 var result=parseDom(val.response);
	 var urlParam=result[3].value.split('?').slice(1);
	 var json=eval("("+decodeURIComponent(urlParam)+")");
	 if(json.Status>0){
		 $("#ImageIDList").val($("#ImageIDList").val()+","+json.Status);
		 return json;
	 }else{
		 webToast(json.ErrorMsg,"bottom",3000);
	 }
	 return null;
	 
 }

 function parseDom(arg) {
 	var objE = document.createElement("div");
 	objE.innerHTML = arg;
 	return objE.childNodes;
 };
    </script>
    <div id="loading"></div>
</body>
</html>