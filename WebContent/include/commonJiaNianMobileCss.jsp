
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String version="1.0.0";
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="人人学车--马健">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/slick.css?v=<%=version %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/base.css?v=<%=version %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/style.css?v=<%=version %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/swiper.min.css?v=<%=version %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/iconfont/iconfont.css?v=<%=version %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/index.css?version=1.0.0?v=<%=version %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/jianian/iosLoading.css?Rand=<%=SmBaseUtil.Random() %>" /> 


	
