<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
%>
<html>
<head>
    <title>校园剧大赛报名申请</title>
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
    <link rel="stylesheet" href="<%=basePath%>css/school/weui.min.css?v=1.1.0">
    <link rel="stylesheet" href="<%=basePath%>css/school/jquery-weui.min.css">
     <link href="<%=basePath%>css/plugin/alertPopShow/alertPopShow.css?v=1" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/school/activity.css?v=1.1.0">
</head>
<body>
<div class="banner">
    <img src="<%=basePath%>images/school/banner.jpg?v=1.0.0" id="imgId"/>
</div>
<div id="main bg_fef4ad">
    <div class="main bg_fffeee color_7b6a01">
        <h2 class="main_h2 bg_f64e68">“我是剧星”浙江中小学校园剧大赛章程</h2>
        <p class="act_title">申请表</p>
        <table border="0" cellspacing="0" cellpadding="0">
        	<form id="postFrom" action="<%=basePath%>Competition/putCompetition" target="i_frame" method="post" enctype="multipart/form-data">
	            <tr><td><input name="School" placeholder="请输入学校全称" type="text" class="srk1 border_ffb1ad" id="School" value="${school }"/></td></tr>
	            <tr><td><input name="Teacher" placeholder="请输入指导老师姓名" type="text" class="srk1 border_ffb1ad" id="Teacher" value="${teacher }"/></td></tr>
	            <tr><td><input name="Phone" placeholder="请输入您的联系方式" type="text" class="srk1 border_ffb1ad" id="Phone" value="${phone }"/></td></tr>
	            <tr><td>
	                <input type="file" name="file" id="file" class="inputfile inputfile-1 border_ffb1ad"    />
	                <label for="file"  class="border_ffb1ad"><span class="inputfile_span">请上传确认函</span></label>
	                <input type="hidden" name="Type" id="Type" value="1"  />
	            </td></tr>
	             
	            <tr><td><input class="an bg_f64e68" type="submit" value="提交" style="border: 0;"/> </td></tr>
	        </form>
        </table>
    </div>
</div>
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
	var Error='${Error}';
	if(Error!=''){
		webToast(Error,"bottom", 2000);
	}
	var Success='${Success}';
	if(Success!=''){
		webToast(Success,"bottom", 2000);
		 setTimeout(function(){
			 window.top.location ="<%=basePath%>Competition/phoneCompetitionDetails";
		 },3000);
	}
	
    $(window).load(function () {
        if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
            return;
        } else if (/(Android)/i.test(navigator.userAgent)) {
            return;
        } else {
            $("#imgId").attr('src',"<%=basePath%>images/school/banner2.jpg");
        };
    });
    //验证手机号
    var checkPhone = function (a) {
        var patrn = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
        if (!patrn.exec(a)) return false;
        return true;
    };

    //清除input内容
    $('.input-close').click(function (e) {
        $(e.target).parent().find(":input").val("");
        $(e.target).hide();
    });
    //监控用户输入
    $(":input").bind('input propertychange', function () {
        if ($(this).val() != "") {
            $(this).siblings(".input-close").show();
        } else {
            $(this).siblings(".input-close").hide();
        }
    });

</script>
</body>
</html>
