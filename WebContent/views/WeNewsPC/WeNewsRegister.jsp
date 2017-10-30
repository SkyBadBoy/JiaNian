<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Students user =(Students)request.getSession().getAttribute("StudentName");
	
	request.setAttribute("user", user);
	String isLogin="0";
	if(user!=null){
		isLogin="1";
	}else{
		isLogin="0";
	}
	request.setAttribute("isLogin", isLogin);
%>
<html lang="en">
<head>
    <title>学生空间</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
	<script type="text/javascript">
	if('${isLogin}'=='1'){
		window.top.location ="<%=basePath%>WeNewsHome";
	}
	
	</script>
</head>
	<body>
		<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
			<div class="signin_block">
				<h4 class="text-center">账号注册</h4>
				<div class="sign_input">
					<div class="wrong_userinfo">
						<span class="tr_top tr_position4"></span>
						<p class="text-center"> </p>
					</div>
					<label>手机号码</label><input type="number" name="phonenum" id="phonenum" maxlength="11" placeholder="请填写正确的手机号"/>
				</div>
				<div class="sign_input">
					<div class="wrong_userinfo">
						<span class="tr_top tr_position4"></span>
						<p class="text-center"> </p>
					</div>
					<label>验证码</label><input type="number" style="margin-right: 10px;" id="code" maxlength="6" placeholder="请填写6位验证码"/>
					<input id="btnSendCode" type="button" value="获取验证码" onClick="sendMessage1(1)" disabled/>
				</div>
				<div class="sign_input">
					<div class="wrong_userinfo">
						<span class="tr_top tr_position4"></span>
						<p class="text-center"> </p>
					</div>
					<label>设置密码</label><input type="password" name="password" id="password" placeholder="密码长度应在6-20个字符之间" />
				</div>
				<div class="sign_input">
					<div class="wrong_userinfo">
						<span class="tr_top tr_position4"></span>
						<p class="text-center"> </p>
					</div>
					<label>确认密码</label><input type="password" name="repassword" id="repassword" placeholder="请确认密码" />
				</div>
				<a href="javascript:;" class="sign_btn  center_block" id="login_submit">下一步</a>
			</div>

		</div>

		<input type="hidden" id="type" value="1"><%--标记   1是注册   2是忘记密码 --%>
		<input id="basePath" value="<%=basePath %>" type="hidden"/>
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		<script type="text/javascript">
		$('#btnSendCode').attr('disabled', 'disabled');
		$('#btnSendCode').css({
			background: '#C6C6C6'
		});
			/*动画（注册）*/
				$("#login_submit").on("click", function () {
					var str_phonenum = $("#phonenum").val();
					var str_code = $("#code").val();
					var str_password = $("#password").val();
					var str_repassword = $("#repassword").val();
					if (str_phonenum.length != 0 ||str_code.length != 0 ||str_password.length != 0 ||str_repassword.length != 0 ) {
						if (!(checkPhone(str_phonenum)&&str_phonenum.length == 11)) {
							popup_msg("请输入正确的手机号码！");
						}else if(!(checkCode(str_code))) {
							popup_msg("请输入正确的6位验证码！");
						}else if(!(checkPwd(str_password))) {
							popup_msg("请输入密码！");
						}else if(!(checkPwd(str_repassword))) {
							popup_msg("请输入确认密码！");
						}else if(str_password!=str_repassword) {
							popup_msg("新密码与确认密码不一致！");
						}else if(checkPhone(str_phonenum) && checkCode(str_code) && checkPwd(str_password)&& checkPwd(str_repassword)) {
							$.ajax({
								type: "GET", //用POST方式传输
								dataType: "JSON", //数据格式:JSON
								url: $("#basePath").val()+'WeNews/BindUser', //目标地址
								data: "Code=" + str_code + "&Phone=" + str_phonenum+"&PassWord="+str_password,
								error: function(XMLHttpRequest, textStatus, errorThrown) {},
								success: function(json) {
									console.log(json);
									if (json.status) {
										popup_msg("验证成功！正在跳转");
										setTimeout(function() {
											if(json.parentName==null || json.parentName==undefined || json.parentName=="" ){
												location.href = $("#basePath").val()+'WeNews/UserEdit';
											}else{
												location.href = $("#basePath").val()+'WeNewsHome';
											}
											
										}, 1000);
									}else{
										popup_msg(json.message);
									}
								}
							});
						}
					} else {
						popup_msg("请填写列表信息！");
					}
					return false;
				});
			
		</script>
	</body>

</html>