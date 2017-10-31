<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Users user =(Users)request.getSession().getAttribute("UserName");
String UserName=(user==null?null:user.getLoginName());
request.setAttribute("path", path);
%>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>人人学车后台管理系统 - 登录</title>
   
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=basePath%>css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
    var url=window.location;
    if ('<%=UserName%>'!='null'){
    	url ="<%=basePath%>index";
    	window.top.location=url;
	}
    
    if(window.top !== window.self){ 
    	window.top.location = url;
    }
    </script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h5 class="logo-name" style="font-size:80px">人人学车</h5>
            </div>
            <br/>
            <h3>欢迎使用人人学车</h3>

            <form:form class="m-t"  id="usersForm" modelAttribute="usersForm" role="form" method="post" target="_self" action="${path}/Users/login">
                <div class="form-group">
                    <form:input type="text" path="LoginName" class="form-control" name="LoginName" placeholder="用户名" />
                    <form:errors path="LoginName" Class="help-block m-b-none errorMsg"></form:errors>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="PassWord" placeholder="密码" />
                    <form:errors path="PassWord" Class="help-block m-b-none errorMsg"></form:errors>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
<p class="text-muted text-center"><%=SmBaseGlobal.CompanyName %>&copy;<%=SmBaseGlobal.CurrentYear%></p>
                
            </form:form>
        </div>
    </div>
    <script src="<%=basePath%>js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=basePath%>js/bootstrap.min.js?v=3.3.5"></script>
    
    <script type="text/javascript">
    $(function(){
    	$("* [name='LoginName']").attr('required', 'required');
		$("* [name='password']").attr('required', 'required');
    	
    });
    
    </script>
<!-- 友盟统计代码 begin -->
<%@ include file="../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>

</html>
