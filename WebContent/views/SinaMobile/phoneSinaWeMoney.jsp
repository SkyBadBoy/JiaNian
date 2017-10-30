<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Students"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="com.unionpay.mobile.android.widgets.ba"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>微米钱包</title>
<jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <style type="text/css">
        .orderfinished-div1 img{width: 40px;height: 40px;}
        .orderfinished-li{height: 60px;}
        .orderfinished-div2{left: 65px;height: 65px;top: 10px;}
        html{background-color: #fff;}
    </style>
</head>
<body class="whitebg">

<div class="wall_weimibg">
 	<div style="text-align: right;
    color: #fff;
    padding: 10px;"><a style=" color: #fff;" href="<%=basePath %>WeMoney/phoneSinaWeMoneyPay?uid=<%=user.getID() %>&&money=">我的收款码</a></div>
    <div class="wall_weimi">我的微米</div>
    <div class="wall_money">${WeMoney }</div>
    <div class="wall_moneydetail"> <a href="<%=basePath %>WeMoney/phoneWeMoneyDetail" style="">查看充值明细 >></a></div>
    <div class="wall_conversion">（1微米 = 0.1元  ）</div>
</div>
<div class="wall_weimipic">
    <img src="<%=basePath %>images/weixinwenshe/wallet.png" style="">
</div>
<div class="wall_recharge">
    <div class="wall_gBtn"><a href="<%=basePath%>Activity/phoneSinaWeMoneyRecharge">充值</a></div>
</div>
<div class="wall_recharge_bg">
    <img src="<%=basePath %>images/weixinwenshe/recharge_bg.png" style="width:100%;">
</div>
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
</body>
</html>