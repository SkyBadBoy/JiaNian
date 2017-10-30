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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>找回密码</title>
    <link rel="stylesheet" href="">
    <link rel="stylesheet" href="<%=basePath%>css/index-login-findpasseord.css"/>
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <script src="<%=basePath%>js/wechat_index.js?v=1.0.0.2"></script>
    <script src="<%=basePath%>js/jquery.validate.min.js"></script>

</head>
<body>
<div class="register-container">
    <h1 style="color: #FF2626;">忘记密码</h1>
        <form:form action="${path}/Students/phoneFindPassword" method="post" modelAttribute="ChangePassWordForm" id="registerForm" enctype="multipart/form-data">
            <div style="width:90%;margin:0 auto">
              
                <form:input  path="UserName" name="UserName" id="tel"  placeholder="请输入手机号码"></form:input>
            	<form:errors path="UserName" cssClass="help-block m-b-none errorMsg"></form:errors>
            </div>
            <div style="position: relative;width:90%;margin:0 auto">
               	<input type="text" name="AuthCode" id="photo_code" placeholder="请输入验证码" autocomplete="off" />
            	<input class="phone_code" style="right:0px" type="button" id="send"   value="发送验证码"/>
                
				
            </div>
            <div style="width:90%;margin:0 auto">
          
                <form:password  path="NewPassWord" name="NewPassWord" id="password"  placeholder="设置密码"></form:password>
           		<form:errors path="NewPassWord" cssClass="help-block m-b-none errorMsg"></form:errors>
            </div>
            <div style="width:90%;margin:0 auto">
               
                <form:password path="Confirm_NewPassWord" name="Confirm_NewPassWord" id="repassword"  placeholder="重复密码"></form:password>
            	<form:errors path="Confirm_NewPassWord" cssClass="help-block m-b-none errorMsg"></form:errors>
            </div>
            <button id="submit" type="submit">提 交</button>
        </form:form>
    </div>
<script type="text/javascript">

    var times=60,
            timer=null;
    $("#send").click(function () {
    	var PhoneNum = $("#tel").val();
		SendAuthCode(PhoneNum);
    });
   
    $(document).ready(function(){
        //注册表单验证
        $("#registerForm").validate({
            rules:{
               
            	NewPassWord:{
                    required:true,
                    minlength:3,
                    maxlength:32
                },
                Confirm_NewPassWord:{
                    required:true,
                    minlength:3,
                    equalTo:'#password'
                },
                UserName:{
                    required:true,
                    phone_number:true,//自定义的规则
                    digits:true//整数
                },
                AuthCode:{
                    required:true,
                    minlength:4,
                    maxlength:6
                }
            },
            //错误信息提示
            messages:{
                NewPassWord:{
                    required:"必须填写密码",
                    minlength:"密码至少为3个字符",
                    maxlength:"密码至多为32个字符"
                },
                Confirm_NewPassWord:{
                    required: "请再次输入密码",
                    minlength: "确认密码不能少于3个字符",
                    equalTo: "两次输入密码不一致"//与另一个元素相同
                },
                UserName:{
                    required:"请输入手机号码",
                    digits:"请输入正确的手机号码"
                },
                AuthCode:{
                    required:"请输入正确的验证码",
                    minlength:"密码至少为4个字符",
                    maxlength:"密码至多为6个字符"
                }

            }
        });
        //添加自定义验证规则
        jQuery.validator.addMethod("phone_number", function(value, element) {
            var length = value.length;
            var phone_number = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
            return this.optional(element) || (length == 11 && phone_number.test(value));
        }, "手机号码格式错误");
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