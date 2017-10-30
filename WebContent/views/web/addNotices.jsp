<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
%>
<html>

<head >

<jsp:include page="/include/commonCss.jsp" />
<style>
.modal-backdrop.in{
	display:none;
}
.image_picker_selector img{
	width:100px;
	height:100px;
}
</style>
</head>
<body >

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
						<li><a href="<%=path%>/Notices/NoticesList"
							target="_self">公告列表</a></li>
					<li><strong>公告编辑</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="NoticesForm" modelAttribute="NoticesForm"
					enctype="multipart/form-data"
					action="${path}/Notices/addNotices" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					
					<form:input type="hidden" path="Status" id="Status" name="Status" />
					<input type="hidden"  id="Content" name="Content" />
					<input type="hidden" id="Type" name="Type" value="1" />
					<input type="hidden" id="Path" value="<%=basePath%>" />
					<input type="hidden" name="pageSize" id="pageSize"value="8" />
					<input type="hidden" name="pageNumber" id="pageNumber" value="0" />
					<c:if test="${not empty isNew}">
						<input type="hidden" name="isNew" id="isNew" value="${isNew}" />
					</c:if>
					<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>公告标题</label>
							<form:input type="text" class="form-control" path="Title"
								id="Title" data-bv-notempty-message="公告标题不能为空" name="Title"
								placeholder="请输入公告标题" />
							<form:errors path="Title" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>作者</label>
							<form:input type="text" class="form-control" path="Author"
								id="Author"  name="Author"
								placeholder="请输入作者" />
							<form:errors path="Author" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>公告内容</label>
							<form:errors path="Content" cssClass="help-block m-b-none errorMsg"></form:errors>
							<div class="ibox-content no-padding">
								<div class="summernote" style="height:300px;">${Content}</div>
							</div>
						</div>
					</div>
					</div>
					<button type="submit" onclick="submitFrom()"
								class="btn btn-primary">保存</button>

				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>

	<jsp:include page="/include/commonJs.jsp" />

	<script type="text/javascript">
		$(function() {
		        $("#NoticesForm").validate({
					rules : {
						Title:{
							required : !0,
							maxlength : 100
						}
					},
					messages : {
						Title : {
							required : "公告名称不能为空",
							maxlength : "公告名称不能超过100个字符"
						}
					}
				});

		});
	
		function submitFrom() {
			$("#Content").val($(".note-editable").code());
		}
		$(document).ready(function() {
		    $(".summernote").summernote({
		        lang: "zh-CN",
		        height:300
		    })
		    getImageList();
		
		});
		
	</script>
</body>
</html>