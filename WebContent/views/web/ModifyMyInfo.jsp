<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
%>
<html>

<head>
	<jsp:include page="/include/commonCss.jsp" />
</head>
<body>
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>

					<li><strong>修改昵称</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-9">
				<form:form id="usersForm" modelAttribute="usersForm"
					action="${path}/Users/ModifyMyInfo" method="post">
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>昵称</label> <form:input path="Name" class="form-control"
								name="Name" placeholder="请输入昵称" id="Name"
								 />
						<form:errors path="LoginName"
										Class="help-block m-b-none errorMsg"></form:errors>
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
			$("#usersForm").validate({
				rules : {
					Name : {
						required : !0,
						maxlength : 20
					}
				},
				messages : {
					Name : {
						required : "昵称不能为空",
						maxlength : "昵称不能超过20个字符"
					}
				}
			});
		});
	</script>
</body>
</html>