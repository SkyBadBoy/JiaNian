
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String version="3.4.4.3.2.1";
%>

 <link rel="shortcut icon" href="favicon.ico"> 
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css?v=<%=version%>" rel="stylesheet">
<link href="<%=basePath%>css/plugins/mmenu/jquery.mmenu.all.css?v=<%=version%>" rel="stylesheet"/>
<link href="<%=basePath %>css/bootstrap.min.css?v=<%=version%>" rel="stylesheet">
<link href="<%=basePath%>css/plugins/blueimp/css/blueimp-gallery.min.css?v=<%=version%>" rel="stylesheet">
<link href="<%=basePath%>css/plugins/swiper/swiper.min.css?v=<%=version%>" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/plugins/fsgallery/fsgallery.css?v=<%=version%>"rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/weui.min.css?v=<%=version%>" rel="stylesheet"><base target="_blank">
<link href="<%=basePath%>css/plugin/mobiscroll/mobiscroll.2.13.2.css?v=<%=version%>" rel="stylesheet"/>
<link href="<%=basePath%>css/plugins/font-awesome-4.5.0/css/font-awesome.min.css?v=<%=version%>" rel="stylesheet">
<link href="<%=basePath%>css/plugins/LArea/LArea.min.css?v=<%=version%>" rel="stylesheet">
<link href="<%=basePath%>css/plugins/cropper/cropper.min.css?v=<%=version%>" rel="stylesheet" type="text/css" >
<link href="<%=basePath%>css/style.css?v=<%=version%>" rel="stylesheet">
<link href="<%=basePath%>css/index.css?v=<%=version%>"type="text/css" rel="stylesheet"/>
<link href="<%=basePath%>css/style.min.css?v=<%=version%>" rel="stylesheet"><base target="_blank">
<script src="<%=basePath%>js/jquery.min.js?v=<%=version%>" type="text/javascript" ></script>
<script src="<%=basePath%>js/plugin/swiper/swiper.min.js?v=<%=version%>" type="text/javascript" charset="utf-8"></script>