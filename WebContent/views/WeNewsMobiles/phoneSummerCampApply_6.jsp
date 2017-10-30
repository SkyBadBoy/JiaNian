<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.alipay.config.AlipayConfig"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@page import="wtb.core.model.Competition"%>
<%@page import="wtb.smUtil.tenpay.util.WXUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
%>
<html>
<head>
     <title>第十六届中国日报社“21世纪·新东方杯”全国中小学生英语演讲比赛浙江赛区参赛通知</title>
    <meta charset="utf-8">
    <meta http-equiv="cleartype" content="on">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="application-name" content="商城">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <meta name="apple-mobile-web-app-title" content="商城">
    <link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css?v=11" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/school/mui.min.css?v=2">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=8.1">
 
   <style type="text/css">
        .mui-checkbox label, .mui-radio label{
            background: rgba(255, 255, 255, .95);
            text-align: left;
            width: 98%;
            padding-right:0;
        }
        .mui-card{
            box-shadow:none;
            margin: 0;text-align: left;
        }
        .mui-input-row label{padding: 10px 0px;}
        .mui-table-view-cell{padding:11px 15px 11px 15px;}
        .mui-table-view-cell > a:not(.mui-btn){color: #e98751}
        .mui-content > .mui-card:first-child{margin-top: 0px;}
        .norms{margin-bottom: 5px;}
        .affirm_down{margin-bottom:10px;}
        /*.mui-navigate-right:after, .mui-push-right:after{right: 30px;}*/
        /*.mui-table-view-cell.mui-active{background: #fff}*/
        .mui-content{background: #fff;}
        .mui-btn {
            font-size: 16px;
            padding: 8px;
            margin: 3px;
            /*border: 0;*/
        }
        input[type="button"], input[type="submit"], input[type="reset"] {
            -webkit-appearance: none;
        }
        textarea { -webkit-appearance: none;}
        select {
            /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
            border: solid 1px rgba(0, 0, 0, 0.3);
            /*很关键：将默认的select选择框样式清除*/
            appearance:none;
            -moz-appearance:none;
            -webkit-appearance:none;
            /*将背景改为绿色*/
            background:#fff;
            /*留出一点位置，避免被文字覆盖*/
            padding-right: 14px;
        }
        .norms_a.active{
            background: #e98751;
            border: 1px solid #e98751;;
        }
        .norms_wm_a.active{
            background: #e98751;
            border: 1px solid #e98751;;
        }

        ::-webkit-input-placeholder { /* WebKit browsers */
            color:    #e98751;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    #e98751;
            opacity:  1;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #e98751;
            opacity:  1;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color:    #e98751;
        }
        .main table td .srk1{ color:    #e98751;}
        .border_e98751{
        background-color: #e98751;
        }
    </style>
</head>
<body>
<div id="preloader">
    <div id="status">
        <p class="center-text">加载中…<em>请稍等哦!</em></p>
    </div>
</div>

<div id="main " class="bk_fff">
    <div class="main bk_fff color_000" style="margin-bottom: 50px;">
        <h2 class="main_h2 bg_d74b68" style="">第十六届中国日报社“21世纪·新东方杯”全国中小学生英语演讲比赛浙江赛区参赛通知</h2>
      
        <p class="img_p"><img class="main_p_img img_response"  src="<%=basePath%>images/school/esy_1.jpg"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img img_response"    src="<%=basePath%>images/school/esy_2.jpg"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/esy_3.jpg?v=1"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/esy_4.jpg"  data-preview-src="" data-preview-group="1"></p>
       
    </div>

</div>


<c:if test="${isApply!=1}">
<div class="qnxzb_jg bg_fdfaf3"  >
    <div class="text-jg "><h2><strong class="color_e98751"><span>下载微新闻社APP，马上参与比赛</span></strong>&nbsp;
    
   </h2></div>
</div>
<div class="qnxzb_order bg_e98751" onclick="window.location ='<%=basePath%>Students/phoneAppDownLoad'" >立即下载</div>
</c:if>


<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap">
        <a href="<%=basePath%>Students/phoneAppDownLoad" class="edit" title="点击报名"><span>点击报名</span></a>
        <a href="javascript:void(0);" class="top" title="返回顶部"><span>返回顶部</span></a>
    </div>
</div>


<a href="javascript:void(0);" class="index-cd-top"></a>
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
<input type="hidden" id="dataContent" name="dataContent" value="第十六届中国日报社“21世纪·新东方杯”全国中小学生英语演讲比赛浙江赛区参赛通知" />

<jsp:include page="/include/commonMobileJs.jsp" />

<script src="<%=basePath%>js/school/plugins/layer-v3.0.3/layer.js?v=1.4"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=basePath%>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/school/mui.zoom.js"></script>
<script src="<%=basePath%>js/school/mui.previewimage.js"></script>
<script src="<%=basePath%>js/school/globe.js?v=2"></script>
<script type="text/javascript">
mui.previewImage();
function callbackFun(obj){
	window.location.reload();
}
$(window).load(function () {
   
   try{
	   $("#status").fadeOut();
	    $("#preloader").delay(350).fadeOut("slow");
	   if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
	        return;
	    } else if (/(Android)/i.test(navigator.userAgent)) {
	        return;
	    }else if (userAgentInfo.indexOf('Wechat') <= 0 && getQueryString("submitType")=='' ) {
	    	window.location = window.location.href+"&submitType=mweb";
	        return;
	    } else {
	        $("#imgId").attr('src',"<%=basePath%>images/school/esysj_banner.jpg");
	        // PC端隐藏点击报名的按钮
	        $("#toastBtn").css("display","none");
	        // PC端隐藏来报名提示
	        $("#guide").css("display","block");
	        // PC端隐藏加载中提示
	        $("#preloader").css("display","none");
	    };
   }catch (e) {
	// TODO: handle exception
}
    
});


</script>
</body>
</html>
