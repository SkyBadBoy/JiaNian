<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath =SmBaseUtil.getProjectBaseUrl(request);
%>
<html lang="zh-CN">
	<head>
		<jsp:include page="/include/commonJiaNianMobileCss.jsp"></jsp:include>
		<title>服务承诺</title>
	</head>
		<body style="background-color: #f0f0f0;">
		<div class="header">
			<span>服务承诺</span>
			<a href="javascript:;" onclick="history.go(-1);" class="back"><i class="iconfont icon-left"></i></a>
		</div>
		<img src="<%=basePath %>images/jianian/service_1.png" width="100%" style="margin-top: 44px;"/>
		
		<img src="<%=basePath %>images/jianian/service_2.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_3.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_4.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_5.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_6.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_7.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_8.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_9.png" width="100%" style="margin-top: 8px;"/>
		
		<img src="<%=basePath %>images/jianian/service_10.png" width="100%" style="margin-top: 8px;margin-bottom: 25px;"/>

	<div class="confirm">
		<a href="apply.html" id="send_btn">立即报名</a>
	</div>
		<!--消息提示-->
		<div class="pop-msg">
			<div class="pop-content">
				<p class="pop-p text-center"></p>
				<img src="<%=basePath %>images/jianian/ok.png" class="center">
			</div>
		</div>
		<div class="popupDom">
			<div class="popwindow">
				<p>消息提示</p>
				<div class="popup text-default"></div>
			</div>
		</div>
		
		
		
	</body>
	
	<jsp:include page="/include/commonJiaNianMobileJs.jsp" /> 

</html>