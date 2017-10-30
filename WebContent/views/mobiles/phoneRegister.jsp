<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", path);
%>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>注册</title>
<link rel="stylesheet" href="">
<link href="<%=basePath%>css/phone-bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="<%=basePath%>css/index-login-findpasseord.css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/jquery.validate.min.js"></script>
<script src="<%=basePath%>js/wechat_index.js?v=1.0.2"></script>
<script type="text/javascript">
	//jquery.validate表单验证
	$(document).ready(function() {
		//注册表单验证
		$("#registerForm").validate({
			rules : {
				Name : {
					required : true,//必填
					minlength : 2, //最少6个字符
					maxlength : 32
				//最多20个字符
				},
				PassWord : {
					required : true,
					minlength : 3,
					maxlength : 32
				},
				Phone : {
					required : true,
					phone_number : true,//自定义的规则
					digits : true
				//整数
				},
				AuthCode : {
					required : true,
					minlength : 4
				}
			},
			//错误信息提示
			messages : {
				Name : {
					required : "必须填写用户名",
					minlength : "用户名至少为2个字符",
					maxlength : "用户名至多为32个字符"
				//                    remote: "用户名已存在"
				},
				PassWord : {
					required : "必须填写密码",
					minlength : "密码至少为3个字符",
					maxlength : "密码至多为32个字符"
				},
				Phone : {
					required : "请输入手机号码",
					digits : "请输入正确的手机号码"
				},
				AuthCode : {
					required : "请输入正确的验证码",
					minlength : "二维码至少为4个数字",
					maxlength : "二维码至多为6个字符"
				}
			}
		});
		//添加自定义验证规则
		jQuery.validator.addMethod("phone_number", function(value, element) {
			var length = value.length;
			var phone_number = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\d{8}$/
			return this.optional(element) || (length == 11 && phone_number.test(value));
		}, "手机号码格式错误");
	});
</script>
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/menu_elastic.css" />

</head>
<body >
<div class="register-container">
    <h1 style="color: #FF2626;">小编注册</h1>
	<div class="login">
		<div class="container logdv">
			<form:form action="${path}/Students/phoneRegister"
				id="registerForm" method="post" modelAttribute="StudentsForm"
				 enctype="multipart/form-data">
				<div>

					<form:input  type="text" path="Name" name="Name"
						id="real_name" class="boxt" placeholder="请输入姓名"></form:input>
					<form:errors path="Name" cssClass="help-block m-b-none errorMsg"></form:errors>
				</div>
				<div>

					<select class="index-select" id="Province"
						onchange="getCity(this);">

					
					</select>

				</div>
				<div>

					<select id="City" class="index-select" onchange="getAreaID(this)">
					
					</select>

				</div>
				<div>

					<select class="index-select" id="AreaID"
						onchange="getUnitAreaID(this)">
						
					</select>

				</div>
				<div>
					<select class="index-select" name="UnitAreaID" id="UnitAreaID">
					
					</select>

				</div>
				<div>

					<form:input type="text"  path="Phone"
						name="Phone" id="tel" class="boxt" placeholder="请输入手机号码"></form:input>
					<form:errors path="Phone" cssClass="help-block m-b-none errorMsg"></form:errors>
				</div>
				<div>

					<form:password path="PassWord" name="PassWord"
						id="password" class="boxt" placeholder="设置密码"></form:password>
					<form:errors path="PassWord"
						cssClass="help-block m-b-none errorMsg"></form:errors>
				</div>


				<div style="position: relative;">
					<input type="text" name="AuthCode" id="photo_code"
						placeholder="请输入验证码" autocomplete="off" /> <input
						class="phone_code" style="" type="button" id="send" value="发送验证码" />
				</div>


				
					<button type="submit" class="btnlg" value="注册">注 册</button>
				
			</form:form>
		</div>
	</div>
	</div>
	<script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
	<script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
	<script src="<%=basePath%>js/wechat_index.js?v=1.0.0.2"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>
	<script type="text/javascript">
		getProvince();
		getCurrentlocation();
		var times = 60, timer = null;
		$("#send").click(function() {
			var PhoneNum = $("#tel").val();
			SendAuthCode(PhoneNum);
		});
		
	</script>
<!-- 友盟统计代码 begin -->
<%@ include file="../../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>
</html>