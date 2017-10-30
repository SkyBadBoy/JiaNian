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

					<li><strong>修改密码</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-9">
				<form:form id="usersForm" modelAttribute="usersForm"
					action="${path}/Users/changepassword" method="post">
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>原密码</label> <form:password path="OldPassWord" class="form-control"
								name="OldPassWord" placeholder="请输入原密码" id="OldPassWord"  onchange="clearError('OldPassWord.errors')"
								data-bv-notempty-message="原密码不能为空" />
						<form:errors path="OldPassWord"
										Class="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>新密码</label> <form:password path="NewPassWord" class="form-control" id="NewPassWord" 
								name="NewPassWord" placeholder="请输入新密码" />
							<form:errors path="NewPassWord"
										Class="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>确认新密码</label> <form:password path="Confirm_NewPassWord" class="form-control" id="Confirm_NewPassWord"
								name="Confirm_NewPassWord" placeholder="请输入再次新密码" />
							<form:errors path="Confirm_NewPassWord"
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
					OldPassWord : {
						required : !0,
						maxlength : 40
					},
					NewPassWord : {
						required : !0,
						maxlength : 40
					},
					Confirm_NewPassWord : {
						required : !0,
						maxlength : 40,
						equalTo:"#NewPassWord"
					}
				},
				messages : {
					OldPassWord : {
						required : "原密码不能为空",
						maxlength : "原密码不能超过50个字符"
					},
					NewPassWord : {
						required : "新密码不能为空",
						maxlength : "新密码不能超过40个字符"
					},
					Confirm_NewPassWord : {
						required : "确认新密码不能为空",
						maxlength : "确认新密码不能超过40个字符",
						equalTo:"两次新密码不一致"
					}
				}
			});
		});
		function clearError(obj){
			if(document.getElementById(obj)!=null){
				document.getElementById(obj).innerText="";
			}

		}
	</script>
</body>
</html>