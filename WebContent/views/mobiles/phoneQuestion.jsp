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
		<title>常见问题</title>
		<style>
			p{width: auto;margin: 0 15px;}
	        .tit{margin-top: 25px;font-size: 15px;color: #75afdc;margin-bottom: 8px;}
	        .quest{font-size: 13px;color: #ff7238;margin-top: 10px;margin-bottom: 10px;}
	        .ansow{font-size: 12px;color: #4a5062;line-height: 18px;}
		</style>
	</head>
		<body style="background-color: #f0f0f0;">
		<div class="header">
			<span>常见问题</span>
			<a href="javascript:;" onclick="history.go(-1);" class="back"><i class="iconfont icon-left"></i></a>
		</div>
		<dl class="news" style="margin-top: 1.45rem;padding-top: 1px;padding-bottom: 20px; margin-bottom: 10px;">
			<p class="tit" style="margin-top: 10px;">费用问答</p>
		    <p class="quest">1.你们的费用是怎么样的？后期还有吗？</p>
		    <p class="ansow">58学车费用透明，杜绝传统驾校吃拿卡要的现象。补考无忧班实行一费到底，后期不再进行收费。</p>
		    <p class="quest">2.补考需要交多少费用？</p>
		    <p class="ansow">特惠班考试不通过，补训则需要缴纳300元补训费/科，另外您需要向车管所缴纳80元补考费补考无忧班不收取补训费，您进需要向车管所缴纳80元补考费即可。</p>
		
		    <p class="tit">报名问答</p>
		    <p class="quest">1.我考的是手动还是自动？是否是C1的驾照呢？</p>
		    <p class="ansow">目前驾考都是手动的哦，是C1证。</p>
		    <p class="quest">2.报名后，什么时候签订合同协议？</p>
		    <p class="ansow">因为缴费是通过网上进行，缴费前会有合同电子版，需要您本人确定后缴费，缴费时就是签订合同；线下缴费的学员，您缴费支付成功后登录系统会自动提示您确认合同。</p>
		    <p class="quest">3.报名成功后谁对我负责？</p>
		    <p class="ansow">我们会为每一位学员指派一个专人工作人员，负责跟进服务每一位学员，确保您能有良好体验。</p>
		
		    <p class="tit">练车问答</p>
		    <p class="quest">1.一般多久能学完？</p>
		    <p class="ansow">这取决于您练车进度的情况及考试预约情况。</p>
		    <p class="quest">2.我随时都能预约教练吗？</p>
		    <p class="ansow">只要您提前预约，都能约得上教练。</p>
		    <p class="quest">3.场地都在哪儿，方便吗？</p>
		    <p class="ansow">目前我们在杨家村，三墩，天目山，下沙都有签约的场地，您报名时我们将会为您匹配最方便的训练场地。</p>
		
		    <p class="tit">考试问答</p>
		    <p class="quest">1.科目一是否有学时要求？</p>
		    <p class="ansow">科目一有理论课培训的要求，需要上满5次课，这个体检的时候会进行预约，58学车现场有工作人员会指导您顺利完成。</p>
		    <p class="quest">2.考试是否有名额限定？可以帮我们预约吗？</p>
		    <p class="ansow">考试名额和学员无关，由车管所规定，只要您达到了考试标准，就可以上网向车管所预约。由于有教练带领并接送，因此请学员预约考试的时间和地点钱请提前和教练沟通后再确定预约。</p>
		</dl>
<!--<div class="confirm">
			<a href="javascript:;" id="send_btn">我也有问题</a>
		</div>-->

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