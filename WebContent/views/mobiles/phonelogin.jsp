<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>跳转下载中...</title>
	<link rel="stylesheet" href="">

 <script type="text/javascript">
var url="https://mobile.baidu.com/item?docid=11332220&source=s1001";
try{
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
		url ="https://itunes.apple.com/cn/app/id1227205061";
	} else{
		var winWidth=0;
		if (window.innerWidth)
			winWidth = window.innerWidth;
		else if ((document.body) && (document.body.clientWidth))
			winWidth = document.body.clientWidth;
		if(winWidth>=800){
			url ="http://shouji.baidu.com/software/11332220.html";
		}else{
			url ="https://mobile.baidu.com/item?docid=11332220&source=s1001";
			
		}
	}
}catch (e) {
	window.top.location  = "https://mobile.baidu.com/item?docid=11332220&source=s1001";
}

window.top.location  = url;


</script>
</head>
<body >
<div class="register-container">
    <h1 style="color: #FF2626;">跳转下载中......</h1>
</div>
<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
</body>
</html>