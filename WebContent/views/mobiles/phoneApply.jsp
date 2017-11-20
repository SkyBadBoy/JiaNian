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
		<title>学车报名</title>
		<style>
				body {
				height: auto;
				-webkit-backface-visibility: hidden;
			}
			
			.icon_head {
				width: 50px;
				height: 50px;
			}
			
			.icon_head img {
				width: 100%;
			}
			
			dl {
				
				height: 50px;
				background-color: #fff;
				/*border-bottom: 1px solid #e7e7e7;*/
				padding: 15px 0;
				box-shadow: 0 0 5px #9e9c9c;
				border-radius:10px;
				margin: 2%;
			}
			
			dl dt {
				float: left;
				margin: 0 13px;
			}
			
			dl dd {
				float: left;
			}
			
			dl dd .type {
				font-size: 14px;
				color: #4a5062;
				display: block;
				margin: 8px 0 6px;
			}
			
			dl dd .mess {
				display: block;
				font-size: 13px;
				color: #999;
			}
			
			dl dd:last-child {
				float: right;
				margin-right: 12px;
				font-size: 14px;
				color: #4a5062;
				margin-top: 16px;
				position: absolute;
				right: 0px;
			}
			
			dl dd:last-child em {
				font-size: 18px;
				color: #ff7200;
			}
			
			dl dd .yg_mess {
				display: block;
				font-size: 12px;
				color: #ff3535;
				margin-top: 8px;
			}
			
			dl dd .icon_yg {
				display: inline-block;
				width: 12px;
				height: 12px;
				background-image: url(/mobile/img/icons_add_classlist.png);
				background-size: 12px;
				background-re4 peat: no-repeat;
				margin-right: 5px;
				position: relative;
				top: 1px;
			}
		</style>
	</head>
		<body style="background-color: #f0f0f0;">
		<div class="header">
			<span>报名</span>
		</div>
		<div class="city_pop_mh" style="margin-top: 1.45rem;">
			<a href="applydetails.html">
				<dl class="hover_color_white" type="14">
					<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_bkwy.png"></dt>
					<dd>
						<span class="type">补考无忧班</span>
						<span class="mess">补训免费,一费到底,1车4人</span>
					</dd>
					<dd><em>3850</em>元</dd>
				</dl>
			</a>
			<a href="applydetails.html">
				<dl class="hover_color_white" type="15">
					<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_thb.png"></dt>
					<dd>
						<span class="type">特惠班</span>
						<span class="mess">一费到底,1车4人</span>
					</dd>
					<dd><em>3450</em>元</dd>
				</dl>
			</a>
			</div>
			
		<div class="footer">
			<ul class="footer-page clearfix">
				<li class="page-item">
					<a href="<%=basePath %>phoneIndex?Rand=<%=SmBaseUtil.Random() %>">
						<i class="iconfont icon-index"></i>
						<p>首页</p>
					</a>
				</li>
				<li class="page-item active">
					<a href="#">
						<i class="iconfont icon-computer"></i>
						<p>报名</p>
					</a>
				</li>
				
				<li class="page-item">
					<a href="<%=basePath %>Students/phoneUserCenter?Rand=<%=SmBaseUtil.Random() %>">
						<i class="iconfont icon-person1"></i>
						<p>我的</p>
					</a>
				</li>
			</ul>
		</div>
		<!--消息提示-->
		<div class="pop-msg">
			<div class="pop-content">
				<p class="pop-p text-center"></p>
				<img src="images/ok.png" class="center">
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