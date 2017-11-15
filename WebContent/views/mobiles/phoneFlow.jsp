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
		<title>学车流程</title>
		<style>
		body {
			background-color: #4f4a49;
			height: auto;
			padding-bottom: 35px;
		}
		
		.top {
			width: 100%;
			height: auto;
		}
		
		dl {
			margin-top: 20px;
			padding-bottom: 10px;
			overflow: hidden;
		}
		
		.sub_title {
			display: inline-block;
			zoom: 1;
			background-color: #45c0f8;
			padding: 0 15px;
			font-size: 15px;
			color: #fff;
			line-height: 36px;
			height: 36px;
		}
		
		.triggle {
			position: absolute;
			display: inline-block;
			zoom: 1;
			border-color: transparent transparent transparent #45c0f8;
			border-style: solid;
			border-width: 18px 0 18px 18px;
			line-height: 16px;
			height: 0px;
			font-size: 0px;
		}
		
		.text {
			margin: 15px 13px 0 29px;
		}
		
		.list {
			border-left: 1px solid #45c0f8;
			height: auto;
		}
		
		.list li {
			position: relative;
			margin-top: 10px;
		}
		
		.circle {
			width: 21px;
			height: 21px;
			background-color: #45c0f8;
			border-radius: 30px;
			-webkit-border-radius: 30px;
			border: 3px solid #768f9a;
			color: #fff;
			text-align: center;
			font-size: 13px;
			line-height: 21px;
			position: absolute;
			left: -14px;
			top: -4px;
			z-index: 4;
		}
		
		.list li p {
			margin-left: 28px;
			margin-top: 4px;
		}
		
		.list li .title {
			font-size: 15px;
			line-height: 20px;
			color: #45c0f8;
			margin-bottom: 5px;
		}
		
		.list li .normal {
			line-height: 19px;
			text-indent: 6px;
			color: #fff;
			font-size: 13px;
		}
		
		.list li .normal em {
			background-color: #45c0f8;
			width: 4px;
			height: 4px;
			border-radius: 4px;
			-webkit-border-radius: 4px;
			display: inline-block;
			float: left;
			margin-top: 6px;
		}
		
		dl {
			border-top: 1px solid #605b57;
			padding-top: 20px;
		}
		
		dl.first {
			border: 0;
			padding-top: 0px;
		}
		
		.mask {
			display: block;
			width: 4px;
			position: absolute;
			top: 22px;
			left: -2px;
			background-color: #4f4a49;
		}
		</style>

	</head>
		<body >
		<div class="header" style="z-index: 100;">
			<span>学车流程</span>
			<a href="javascript:;" onclick="history.go(-1);" class="back"><i class="iconfont icon-left"></i></a>
		</div>
	<img class="top" src="<%=basePath %>images/jianian/xc_flow_topbg.png" style="margin-top: 44px;"> 
	    <dl class="first" >
			<dt class="sub_title">报名学车前</dt>
			<dd class="triggle">1</dd>
			<dd class="text">
				<ul class="list">
					<li>
						<div class="circle">1</div>
						<p class="title">预约免费学车</p>
						<p class="normal"><em></em>在线提交预约，学车顾问24小时之内与您联系，安排试学时间。</p>
						<p class="normal"><em></em>考察场地，接触教练，感受接送，全程免费。试学满意再报名。</p>
					</li>
					<li>
						<div class="circle">2</div>
						<p class="title">挑选班型报名</p>
						<p class="normal"><em></em>选择班型，了解班型对应服务内容。</p>
						<p class="normal"><em></em>根据自身情况，选择适合的班型。</p>
					</li>
					<li>
						<div class="circle">3</div>
						<p class="title">填写报名信息</p>
						<p class="normal"><em></em>点击“立即报名”，输入您的身份信息及联系方式。</p>
						<p class="normal"><em></em>如果您持有优惠码，请选择/输入您的优惠码。</p>
					</li>
					<li>
						<div class="circle">4</div>
						<p class="title">支付学费并完成报名</p>
						<p class="normal"><em></em>您可以选择您喜欢的支付方式，我们支持微信支付和支付宝支付。</p>
					<span class="mask" style="height:55px"></span></li>
				</ul>
			</dd>
		</dl>
		<dl class="">
			<dt class="sub_title">开始学车</dt>
			<dd class="triggle">1</dd>
			<dd class="text">
				<ul class="list">
					<li>
						<div class="circle">1</div>
						<p class="title">体检</p>
						<p class="normal"><em></em>报名成功24小时，学车顾问会与您取得联系，告知相关事宜。</p>
						<p class="normal"><em></em>体检当天学车顾问会和您一起前往指定地点完成体检。</p>
					</li>
					<li>
						<div class="circle">2</div>
						<p class="title">结业考试</p>
						<p class="normal"><em></em>报名当天在车管所选择科目一学习地点，并连续5天前往学习。</p>
						<p class="normal"><em></em>预约当天您就将得知结业考试时间。</p>
						<p class="normal"><em></em>若理论学习中断，则结业考试作废，您可以在任意一工作日再次前往车管所参加。</p>
					</li>
					<li>
						<div class="circle">3</div>
						<p class="title">科目一</p>
						<p class="normal"><em></em>结业考试85分以上视为通过，通过后，当场预约科目一。</p>
						<p class="normal"><em></em>考试当天前往文晖路/河坊街的制定考场进行考试。</p>
						<p class="normal"><em></em>2次机会均未通过，则需当场缴纳补考费80元并当场预约补考时间进行补考。</p>
						<p class="normal"><em></em>考试通过后，学车顾问将为您制卡，只开完成后即可在线预约模拟一。</p>
					</li>
					<li>
						<div class="circle">4</div>
						<p class="title">模拟学习</p>
						<p class="normal"><em></em>按照在线预约的时间进行模拟学习。</p>
						<p class="normal"><em></em>模拟学习后即可开始上车练习。</p>
					</li>
					<li>
						<div class="circle">5</div>
						<p class="title">科目二</p>
						<p class="normal"><em></em>每次练车前在线进行学习预约。</p>
						<p class="normal"><em></em>我们将安排车辆接送您练车。</p>
						<p class="normal"><em></em>教练判定学习合格后，您可以自行在线预约考试。</p>
					</li>
					<li>
						<div class="circle">6</div>
						<p class="title">科目三</p>
						<p class="normal"><em></em>科目二通过后，您的教练将为您安排科目三训练。</p>
						<p class="normal"><em></em>教练判定学习合格后，您可以在线预约考试。</p>
					</li>
					<li>
						<div class="circle">7</div>
						<p class="title">科目四</p>
						<p class="normal"><em></em>您独立完成相关理论学习后前往车管所或考试点预约。</p>
						<p class="normal"><em></em>按时参加，通过后，当天就可以拿驾照啦~恭喜！</p>
					<span class="mask" style="height:59px"></span></li>
				</ul>
			</dd>
		</dl>
		
		
		
		
	</body>
	
	<jsp:include page="/include/commonJiaNianMobileJs.jsp" /> 

</html>