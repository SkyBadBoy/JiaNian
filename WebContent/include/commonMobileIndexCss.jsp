
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String version="3.3.9.4";
%>
<link href="<%=basePath%>css/plugins/animate/animate.min.css?v=<%=version%>" rel="stylesheet">
	<link href="<%=basePath%>/css/font-awesome.min.css?v=<%=version%>" rel="stylesheet" type="text/css" >
	<link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css?v=<%=version%>" rel="stylesheet">
	 <link href="<%=basePath%>css/plugins/font-awesome-4.5.0/css/font-awesome.min.css?v=<%=version%>" rel="stylesheet">
	<link href="<%=basePath%>css/bootstrap.min.css?v=<%=version%>" rel="stylesheet">
    <link href="<%=basePath%>css/style.css?v=<%=version%>" rel="stylesheet">
