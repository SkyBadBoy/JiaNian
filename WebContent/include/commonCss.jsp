
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user =(Users)request.getSession().getAttribute("UserName");
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico">
<link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="<%=basePath%>css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/cropper/cropper.min.css" rel="stylesheet">
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/summernote/summernote.css?v=1.0.0.1" rel="stylesheet">
<link href="<%=basePath%>css/plugins/summernote/summernote-bs3.css?v=1.0" rel="stylesheet">
<link href="<%=basePath%>css/image-picker.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/style.min.css?v=4.0.1" rel="stylesheet">
<link href="<%=basePath%>css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="<%=basePath%>css/daterangepicker-bs2.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/iCheck/custom.css" rel="stylesheet">
 <script src="<%=basePath%>js/demo/echarts-all.js"></script>
<script type="text/javascript">
	if ('<%=user%>'=='null'){
		window.top.location ="<%=basePath%>Users/login";
	}
</script>
