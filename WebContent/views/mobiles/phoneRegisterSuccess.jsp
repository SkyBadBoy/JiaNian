<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Students user =(Students)request.getSession().getAttribute("StudentName");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册成功提示</title>
    <link href="<%=basePath%>css/phone-bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/phone-style.css" rel="stylesheet">
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/menu_elastic.css"/>
</head>
<body class="huibg">
<div class="mendian " style="border-bottom: dashed 1px #ccc;">
    
    <div class="mdtit" style="font-size: 20px;padding: 4px;margin-bottom: 0;border-bottom: dashed 0px #ccc">账号注册
    </div>
</div>
<div class="dingdan">
    <div class="ddlist">
        <div class="ddok">账号注册成功</div>
        <div class="ddokps">点击确定返回登录页面登录账号</div>
        <div class="modal-footer" align="center">
            <a href="<%=path%>/Students/phonelogin?returnURL=${returnURL}">
                <button type="button" class="btn btn-danger btn-lg">确定</button>
            </a>
        </div>
    </div>
</div>
<!-- 友盟统计代码 begin -->
<%@ include file="../../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>
</html>