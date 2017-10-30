<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
%>
<html>
<head>
<meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/include/commonCss.jsp" />
</head>
<body>
	<div class="container-fluid">
<%-- 		<input type="hidden" name="basePath" value="<%=basePath%>" id="basePath" />
		<input type="hidden" name="type" value="${type}" id="type" />
		<input type="hidden" name="value" value="${value}" id="value" /> --%>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">处理结果</h1>
			</div>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-12">
				<div class="alert alert-success">
					<span style="font-size: 20px;">保存成功!</span> <button type="button" style="font-weight: bolder;" onclick="javascript:window.location.href='<%=basePath%>Notices/NewsList'" class="btn btn-outline btn-link">${title eq null?"返回列表":title}</button>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</div>

	<!-- /#page-wrapper -->

	<!-- /#wrapper -->
<!-- 	<script type="text/javascript">
		var type=document.getElementById("type").value;
		var value=document.getElementById("value").value;
		var basePath=document.getElementById("basePath").value;
		if(type!="" && value!=""){
			
			if(type=='1'){
				value =basePath+value
			}
			parent.window.modifyInfo(type,value);
		}
	</script> -->
</body>
</html>

