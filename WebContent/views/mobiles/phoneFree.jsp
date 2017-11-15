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
		<title>免费试学</title>
		<style>
			p{width: auto;margin: 0 15px;}
	        .tit{margin-top: 25px;font-size: 15px;color: #75afdc;margin-bottom: 8px;}
	        .quest{font-size: 13px;color: #ff7238;margin-top: 10px;margin-bottom: 10px;}
	        .ansow{font-size: 12px;color: #4a5062;line-height: 18px;}
		</style>
	</head>
		<body style="background-color: #fffaf5;">
		<div class="header">
			<span>免费试学</span>
			<a href="javascript:;" onclick="history.go(-1);" class="back"><i class="iconfont icon-left"></i></a>
		</div>
		<%-- <img src="<%=basePath %>images/jianian/banner.png" width="100%" style="margin-top: 44px;"/>  --%>
		<img src="<%=basePath %>images/jianian/free.png" width="100%" style="margin-top: 44px;"/>
		<div class="contactFormWrapper" style="background-color: #fffaf5;margin-bottom: 45px;">
			<form method="post" class="contactForm" id="contactForm">
				<fieldset>
					<div class="formFieldWrapper">
						<label for="contactMessage">预约手机</label>
						<input type="text" placeholder="请输入预约手机号" name="contactTitle" value="" class="contactField requiredField" id="contactTitle" data-placeholder="" />
					</div>
					<div class="formTextareaWrapper">
						<label for="contactMessage">预约建议</label>
						<textarea name="contactMessage" class="contactTextarea requiredField" id="contactMessage"></textarea>
					</div>
				
		</div>
				</fieldset>
			</form>

		</div>
	<div class="confirm">
		<a href="javascript:;" id="send_btn">立即预约</a>
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