<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.core.model.Users"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) session.getAttribute("UserName");
	request.setAttribute("path", path);
	String AreaID="";
	if(user!=null){
		AreaID=String.valueOf(user.getAreaID());
	}
%>
<html>
<head>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no;" name="viewport"/>
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <meta name='apple-touch-fullscreen' content='yes'>
  <meta name="full-screen" content="yes">
  <meta name="format-detection" content="telephone=no"/>
  <meta name="format-detection" content="address=no"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" href="<%=basePath%>css/register-wap.css?v=1.0.0"/>
  <title>登录</title>
  <style type="text/css">
    label.error {
	color: #cc5965;
	display: inline-block;
	margin-left: 5px
	  
}
.errorMsg{
color: #cc5965;
}
.register-item label{
	width:100%;
}
</style>
</head>
<body>
<nav>
<strong style="color:white;">登录</strong>
</nav>

	<form:form id="usersForm" modelAttribute="usersForm"
					enctype="multipart/form-data" cssClass="wizard-big"
					action="${path}Users/phonelogin" method="post">
  <!-- 手机注册开始 -->
  <div id="error_display" style="text-align:center;color:red;display:none;">
    <!--错误信息显示区域-->
  </div>
  
  <input type="hidden"  value="${_pc_}"  class="form-control" id="powerCode" name="powerCode" />
<input type="hidden"  value="<%=AreaID%>"  class="form-control" id="CityAreaID" name="CityAreaID" />
<input type="hidden"  value="Students"  class="form-control" id="AuthType" name="AuthType" />
  <section class="phone-register">
    <div class="register-item">
      <div class="inputs ">
        <label for="phone">手机号：</label>
        <form:input type="LoginName" path="LoginName" class="form-control" id="LoginName" name="LoginName" placeholder="请输入微信号"/>
     	<form:errors path="LoginName" Class="help-block m-b-none errorMsg"></form:errors>
      </div>
    </div>
 
    <!-- 用户名 -->
    <div class="register-item ">
      <div class="inputs ">
        <label for="password">密&nbsp;&nbsp;&nbsp;码：</label>
        <span id=box><form:password class="form-control" path="PassWord" placeholder="请输入密码" name="PassWord" id="PassWord"/></span>
       <form:errors path="PassWord" Class="help-block m-b-none errorMsg"></form:errors>
      </div>
      <div id="password_tip_div" class="tip"></div>
    </div>
    

  </section>
  <!-- 注册按键 -->
  <section class="register-btn">
    <button type="submit" id="submit">登录</button>
  </section>
  <div class="register-item" >
      <div class="inputs " style="background-color: #F3F3F3">
       <p>
       <a href="<%=path%>weChatPublic/phoneStudentRegister">注册</a>
       <a href="<%=path%>Users/phonechangepassword?AuthType=Students" style="float:right;">忘记密码?</a>
       
       </p>
      
      </div>
    </div>
</form:form>
	<jsp:include page="/include/commonMobileJs.jsp" />
  	<script type="text/javascript">
  	if($("#CityAreaID").val()!="" && $("#powerCode").val()!=""){
  		window.location.href="<%=basePath%>/weChatGroup/phoneweChatGroupDetailList?_pc_="+ $("#powerCode").val() +"&_area_="+$("#CityAreaID").val();
  	}
  	$(function(){
  		$("#usersForm").validate({
  	  		rules: {
  	  		LoginName: {
  	                required: !0
  	            },
  	          PassWord: {
  	                required: !0
  	                
  	            }
  	        },
  	        messages: {
  	        	LoginName: {
  	                required: "登录名不能为空"
  	            },
  	          PassWord: {
  	                required: "登录密码不能为空"
  	            }
  	        }
  		});
  	});
  	function get_phone_code(){
  		$.getJSON("<%=path%>Users/getAuthCode", {
  			Phone : $("#Phone").val(),
  		}, function(json) {
  			
  		});
  	}
  	
  	</script>
</body>
</html>
