<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
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
<title>请选择您的学校</title>
<link rel="stylesheet" href="">

<link rel="stylesheet" href="<%=basePath%>css/phone-index-login-findpasseord.css?v=3.3.7"/>
<style type="text/css">
        #loading {
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/wsload.gif) no-repeat center center #FFF;
            background-size: 10%;
        }
    </style>
</head>
<body>
	<div class="register-container">
		<h1>请选择您的学校</h1>
		
		<form:form action="${path}/Region/phoneBindRegion"
			modelAttribute="StudentsForm" id="registerForm" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="uid" value="${uid}" />
			<input type="hidden" name="returnView" value="${returnView}" />
			<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
			<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" />
			<input type="hidden" name="CityAreaID" value="${CityAreaID}" id="CityAreaID" />
			<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
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

			<button id="submit" type="submit" onclick="submitFrom()"
				style="color: #fff; font-size: 20px;background-color: #68ccb3">提 交</button>
				<br/>
				<br/>
				<div style="display: block;width: 100%;text-align: right;">
				<a href="<%=path%>/Feedback/phoneFeedback?sina=<%=SmBaseUtil.Random()%>" style="color: #898989;font-size: 13px; padding-right: 20px;text-decoration: underline;">没有我的学校？</a>
				</div>
		</form:form>
	</div>
	<footer style="margin:10px 0;bottom: 10px;width: 100%">
    <div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
	 $("#loading").css("display","none");
	 getProvince();
	 if($("#ProvinceID").val()==""){
		 getCurrentlocation();
	 }
	 
	 
		function submitFrom() {
			if($("#UnitAreaID").val()==""){
				alert("请选择学校");
				return false;
			}else{
				$("#loading").css("display","block");
			}
			
		}
</script>
<div id="loading"></div>
</body>
</html>