<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
	
%>
<html >
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>APP使用手册</title>
<link rel="stylesheet" href="">

<link rel="stylesheet" href="<%=basePath%>css/school/mui.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/school/activity.css">

</head>
<body >
	<div class="mui-content">
    <div class="mui-content-padded">
        <h4 class="p_ti">如何登录新浪小编？</h4>
        <p class="p_ti">【1】输入手机号码，获取验证码，点击“登录”，如下图所示</p>
        <p><img src="<%=basePath%>images/activity/help1.jpg" data-preview-src="" data-preview-group="1"/></p>
         <p class="p_ti">【2】若是已经在"校播"服务号上注册过的小编可以直接使用微信登录,点击下方"微信登录"进行授权确认即可</p>
         <p><img src="<%=basePath%>images/activity/help5.png" data-preview-src="" data-preview-group="1"/></p>
        <h4 class="p_ti">微新闻社如何投稿？</h4>
        <p class="p_ti">【1】点击下面的“+”号，选择写新闻，如下图所示</p>
        <p><img src="<%=basePath%>images/activity/help2.jpg" data-preview-src="" data-preview-group="1"/></p>
        <p class="p_ti">【2】输入标题、内容和图片，点击发布按钮即可发布，如下图所示</p>
        <p><img src="<%=basePath%>images/activity/help3.jpg" data-preview-src="" data-preview-group="1"/></p>
        <p class="p_ti">【3】发布新闻后会跳转到个人中心的我的新闻列表中</p>
        <p><img src="<%=basePath%>images/activity/help4.png" data-preview-src="" data-preview-group="1"/></p>
        <p class="p_ti">【4】在新闻列表中可以查看自己发布的图文</p>
         <h4 class="p_ti">微新闻社如何投稿视频？</h4>
        <p class="p_ti">【1】点击下面的“+”号，选择发视频</p>
        <p><img src="<%=basePath%>images/activity/help2.jpg" data-preview-src="" data-preview-group="1"/></p>
        <p class="p_ti">【2】点击右上方的“录制视频”，即可开始视频录制，点击右上方的照相机图标可以切换前后摄像头</p>
        <p><img src="<%=basePath%>images/activity/help6.jpg" data-preview-src="" data-preview-group="1"/></p>
        <p class="p_ti">【3】点击底部红色按钮开始录制视频，点击“完成按钮，录制完成，点击下方的视频上传即可发布视频新闻，若是不满意可以点击返回重新录制”</p>
        <p><img src="<%=basePath%>images/activity/help8.jpg" data-preview-src="" data-preview-group="1"/></p>
         <p class="p_ti">【2】输入标题、内容和图片，点击发布按钮即可发布，如下图所示</p>
        <p><img src="<%=basePath%>images/activity/help3.jpg" data-preview-src="" data-preview-group="1"/></p>
        <p class="p_ti">【4】在视频列表中可以查看自己发布的视频</p>
        <p><img src="<%=basePath%>images/activity/help7.jpg" data-preview-src="" data-preview-group="1"/></p>
         
    </div>
</div>
<footer style="margin:10px 0;font-size: 13px;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<script src="<%=basePath%>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/school/mui.zoom.js"></script>
<script src="<%=basePath%>js/school/mui.previewimage.js"></script>
</body>
</html>