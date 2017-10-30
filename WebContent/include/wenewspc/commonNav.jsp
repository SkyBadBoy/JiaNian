
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	
Students user =(Students)request.getSession().getAttribute("StudentName");


	String isLogin="0";
	if(user!=null){
		isLogin="1";
		if(user.getName()==null || user.getName().isEmpty() || user.getArea()==null || user.getAreaID().equals(0) || 
				user.getParentName()==null || user.getParentName().isEmpty() ||  user.getPhone()==null || user.getPhone().isEmpty()  ){
			request.setAttribute("isNeedInfo", 1);
		}else{
			request.setAttribute("isNeedInfo", 0);
		}
		
	}else{
		isLogin="0";
	}

	request.setAttribute("user", user);
	request.setAttribute("isLogin", isLogin);
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<input type="hidden" id="isLogin" name="isLogin" value="${isLogin}"/>
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>"/>
	<div class="popupDom">
			<div class="popwindow">
				<p>消息提示</p>
			<div class="popup text-default">1233333333333</div>
			</div>
		</div>
	<div class="zhezhao">
			<div class="login-frameBox">
				<div class="login-publicTitle" style="text-align:center;"><span class="switch-close">×</span><img src="<%=basePath %>images/wenewspc/logo.png"><b>登录</b></div>
				<div class="select-loginType clearfix">
					<a class="sj fl" href="javascript:;"><em></em><span>手机登录</span></a>
					<a class="wechat fl" onclick="Login()"><em></em><span>微信登录</span></a>
					<div class="model_login">
						<div class="wrong_model wrong_modelName"><i style="display: none" >0</i>
							<span class="tr_top tr_position1"></span>
							<p class="text-center">用户名或密码错误！ </p>
						</div>
						<div class="wrong_model wrong_modelPwd"><i style="display: none" >0</i>
							<span class="tr_top tr_position3"></span>
							<p class="text-center">用户名或密码错误！ </p>
						</div>
						<form id="login1">
							<input placeholder="手机号码" name="loginName" id="loginName" type="text">
							<input placeholder="密码" name="loginPwd" id="loginPwd" type="password">
							<div>
								<input name="" id="" value="" type="checkbox">
								<span>记住我</span>
								<a href="<%=basePath %>WeNews/FundPwd">忘记密码</a>
								<a href="javascript:;" style="color:orange" class="model_choose1">手机验证登录</a>
							</div>
							<a href="javascript:;" class="model_btn" id="in_btn1">登录</a>
						</form>
						<form id="login2">
							<input type="tel" placeholder="手机号码" name="phonelogin" maxlength="11" id="phonelogin" />
							<input type="tel" id="codelogin" name="codelogin" maxlength="6" placeholder="验证码" />
							<input id="SendCode" type="button" value="获取验证码" onClick="sendMessage(3)" disabled="disabled" />
							<div>
								<a href="javascript:;" style="color:orange" class="model_choose2">账号密码登录</a>
							</div>
							<a href="javascript:;" class="model_btn" id="in_btn2">登录</a>
						</form>
					</div>
				</div>
			</div>
		</div>
	<div class="nav_full">
			
		
			<nav class="center_block">
				<div class="logo">
					<a href="<%=basePath%>WeNewsHome"><img style="width:100px" 
					<c:if test="${HomeLogo_Adverts==null }">
						src="<%=basePath %>images/wenewspc/logo.png"
					</c:if>
					<c:if test="${HomeLogo_Adverts!=null }">
						src="${HomeLogo_Adverts.imageurl }"
					</c:if>
					
					></a>
				</div>
				<div class="nav_search">
					<form action="<%=basePath%>WeNews/Search" id="search-form">
					</form>
				</div>
				<div class="nav_user">
				<c:if test="${user eq null}">
					<p class="signin_btn" id="login">
						<a style="color: red;" onclick="$('.zhezhao').fadeIn();">登录</a>
					</p>
					<p>|</p>
					<p class="signin_btn">
						<a href="<%=basePath %>WeNews/Register">注册</a>
					</p>
					</c:if>
					<c:if test="${user ne null}">			
						<div class="user_pic">
							<a href="<%=basePath %>WeNews/UserInfo"><img class="img_100" src="${imageurl }"></a>
						</div>
						<p>${user.name }</p>
						<a href="javascript:LoginOut();">注销</a>	
					</c:if>
					<p>|</p>
					<a href="<%=basePath%>WeNewsHome">首页</a>
					<p>|</p>
					<a  style="color: red;" href="<%=basePath%>WeNewsHome">我要发稿</a>
					<p>|</p>
					<a href="<%=basePath %>WeNews/UserInfo">个人中心</a>
				</div>
			</nav>
		
			
		
			
		</div>
		<div class="qrcord_box">
			<div class="login-frameBox">
				<div class="login-publicTitle" style="text-align:center;"><span class="switch-close">×</span><img src="<%=basePath %>images/wenewspc/logo.png"><b>微信扫一扫</b></div>
				<div class="select-loginType clearfix">
					<div class="qrdiv_user"></div>
				</div>
			</div>
		</div>
<script type="text/javascript">



	function LoginOut(){
			window.location.href="<%=basePath %>WeNews/loginOut?returnUrl="+encodeURI(location.pathname+location.search);
	} 
	
	
</script>