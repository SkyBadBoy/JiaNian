<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
%>
<html>
<head>
  <title>修改成功</title>
  <meta charset="gbk">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
  <meta content="telephone=no" name="format-detection"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <meta content="" name="keywords">
  <meta content="" name="description">
  <link rel="stylesheet" href="<%=basePath%>css/login-index.css"/>
  <jsp:include page="/include/commonMobileJs.jsp" />
</head>
<body>
<nav>
  <a href="javascript:window.history.back()" class='goback'>
    <b></b>
  </a>
  <span style="color:white;">修改成功</span>
</nav>
  <section class='text-center'>
    <a href="#">修改成功！</a> <span><img src="<%=basePath%>img/ico_1.png" width="30px" height="30px" style="margin-bottom:-5px;"></span>
  </section>

<c:if test="${content==null}">
  <section class='text-center'>
    <a href="<%=path%>/Students/phonelogin" style="color:#8B9087;border-bottom:1px solid #000;">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </section>
</c:if>
</body>
</html>