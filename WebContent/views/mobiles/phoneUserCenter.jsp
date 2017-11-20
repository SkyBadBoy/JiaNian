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
		<title>个人中心</title>
	</head>
		<body class="bge">
			<div class="write-info">
				<span>
				<img src="<%=basePath%>images/jianian/no-person.png">
			</span>
				<p>xxx</p>
			</div>
			<ul class="ask-box">
				<li class="ask-item">
					<a href="user-info.html">
						<i class="iconfont icon-data"></i>
						<span class="ask-word">个人信息</span>
						<span class="goin"><i class="iconfont icon-right"></i></span>
					</a>
				</li>
				<!--<li class="ask-item nobor">
					<a href="changepsw.html">
						<i class="iconfont icon-changpsw"></i>
						<span class="ask-word">修改密码</span>
						<span class="goin"><i class="iconfont icon-right"></i></span>
					</a>
				</li>-->
			</ul>
			<ul class="ask-box">
				<li class="ask-item">
					<a href="self-report.html">
						<i class="iconfont icon-green"></i>
						<span class="ask-word">消息通知</span>
						<span class="my_news">12</span>
						<span class="goin"><i class="iconfont icon-right"></i></span>
					</a>
				</li>
				<li class="ask-item nobor">
					<a href="myanswer.html">
						<i class="iconfont icon-zixun"></i>
						<span class="ask-word">留言记录</span>
						<span class="goin"><i class="iconfont icon-right"></i></span>
					</a>
				</li>
			</ul>
		                
			<!--<div class="sign-out">
				<a href="#">
					<i class="iconfont icon-logout"></i>
					<span>退出</span>
				</a>
			</div>-->
				<div class="footer">
			<ul class="footer-page clearfix">
				<li class="page-item">
					<a href="<%=basePath %>phoneIndex?Rand=<%=SmBaseUtil.Random() %>">
						<i class="iconfont icon-index"></i>
						<p>首页</p>
					</a>
				</li>
				<li class="page-item ">
					<a href="<%=basePath %>ApplyList/phoneApply?Rand=<%=SmBaseUtil.Random() %>">
						<i class="iconfont icon-computer"></i>
						<p>报名</p>
					</a>
				</li>
				
				<li class="page-item active">
					<a href="#">
						<i class="iconfont icon-person1"></i>
						<p>我的</p>
					</a>
				</li>
			</ul>
		</div>
			<div class="outlayer">
				<div class="tip-box">
					<h3 style="font-weight: 100;border-bottom:1px solid #ddd;">退出登录</h3>
					<p class="ta-center">
						<a href="#" class="ok" id="layout">确认</a>
						<a href="#" class="cancel">取消</a>
					</p>
				</div>
			</div>
			<!--消息提示-->
			<div class="pop-msg">
				<div class="pop-content">
					<p class="pop-p text-center"></p>
					<img src="<%=basePath%>images/jianian/ok.png" class="center">
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