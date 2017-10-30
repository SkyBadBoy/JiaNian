<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
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
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport"
        content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <title>注册</title>
<link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">


  <link href="<%=basePath%>css/plugins/apply/form.min.css" rel="stylesheet" type="text/css">
  <style type="text/css">
    #bottom_info {
      position: relative;
      top: -3.8em;
      margin: 0 0.5em;
      padding: .8em 0;
      text-align: center;
      background-color: #18c178;
      color: #ffffff;
      border: medium hidden;
      border-radius: 0.1em;
      box-sizing: border-box;
      display: none;
    }

    #bottom_info a {
      /*
      color: #adadad;
      */
      width: 100%;
      text-decoration: none;
      outline: none;
    }

    #bottom_jump {
      position: relative;
      top: -3em;
      margin: 0 0.5em;
      padding: .8em 0;
      text-align: center;
      background-color: #62A9E3;
      color: #ffffff;
      border: medium hidden;
      border-radius: 0.1em;
      box-sizing: border-box;
      display: none;
    }
    input[type="submit"]{
      background-color:#18A689;
    }
    #subjects{
      padding-bottom:0;}
    input[type="text"], input[type="password"], select, textarea{
    padding: 3px;}
   
    label.error  {
	color: #cc5965;
	display: inline-block;
	margin-left: 5px
}
.errorMsg{
color: #cc5965;
}
  </style>
</head>
<body>
<div id="subjects">
 	<form:form id="ApplyListForm" modelAttribute="StudentsForm"
					enctype="multipart/form-data" cssClass="wizard-big"
					action="${path}/Students/phoneStudentRegister" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" id="AuthType" name="AuthType" value="Students" />
					<input type="hidden" id="CityAreaID" name="CityAreaID" value="${AreaID}" />
					<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}"
						id="ProvinceID" />
    <div class="form_ctrl page_head" style="background-color: #18A689;" id="1" title="加入平台">
      <h2 style="color: #fff;font-size: 22px;  margin-top: 15px ">注册</h2>
    </div>
    <form:errors path="ID" Class="help-block m-b-none errorMsg"></form:errors>
    <div class="form_ctrl input_text" id="3" title="您的姓名">
      <label class="ctrl_title">您的姓名<span style="color: #ff0000">*</span></label>
      <form:input type="text" path="Name" name="Name"  placeholder="请输入您的姓名"/>
      <form:errors path="Name" Class="help-block m-b-none errorMsg"></form:errors>
    </div>
    <div class="form_ctrl input_text" id="3" title="您的手机">
      <label class="ctrl_title">您的手机<span style="color: #ff0000">*</span></label>
      <form:input type="text" path="Phone" name="Phone"  placeholder="请输入您的手机号码"/>
      <form:errors path="Phone" Class="help-block m-b-none errorMsg"></form:errors>
    </div>
    

    <div class="form_ctrl input_text" id="7" title="省份">
      <label class="ctrl_title">省份<span style="color: #ff0000">*</span></label>
      <select class="form-control m-b" id="Province" onchange="getCity(this)" name="Province">			
			<c:forEach var="Part" items="${Province}">
				<option value="${Part.REGION_ID}"
					<c:if test="${Part.REGION_ID== ProvinceID}">selected="selected" </c:if>>${Part.REGION_NAME}</option>
			</c:forEach>
		</select>
    </div>
    <div class="form_ctrl input_text" id="7" title="城市">
      <label class="ctrl_title">城市<span style="color: #ff0000">*</span></label>
     <select class="form-control m-b" id="City" onchange="getAreaID(this)" name="City">
	 </select>
    </div>
    <div class="form_ctrl input_text" id="7" title="地区">
      <label class="ctrl_title">地区<span style="color: #ff0000">*</span></label>
     <select class="form-control m-b" id="AreaID" onchange="getUnitAreaID(this)" name="AreaID">
	 </select>
    </div>
  <div class="form_ctrl input_text" id="7" title="学校">
      <label class="ctrl_title">学校<span style="color: #ff0000">*</span></label>
     <select class="form-control m-b" id="UnitAreaID" name="UnitAreaID">
	 </select>
    </div>
    <div class="form_ctrl form_submit" id="12" title="">
      <input type="submit" onclick="submitForm()" name="submit" value="提交">
    </div>
  </form:form>
  </div>
  <jsp:include page="/include/commonMobileJs.jsp" />
  	<script type="text/javascript">
  	getProvince();
  	getCurrentlocation();
  	$(function(){
  		$("#ApplyListForm").validate({
  	  		rules: {
  	            Name: {
  	                required: !0,
  	                maxlength: 20
  	            },
  	          	Phone: {
  	                required: !0,
  	              	mobile: !0
  	            },
  	            AreaID: {
  	                required: !0,
  	                maxlength: 20
  	            },
  	            Content: {
  	            	required: !0,
  	                maxlength: 200
  	            }

  	        },
  	        messages: {
  	        	Name: {
  	                required: "您的姓名不能为空",
  	              	mobile: "您的姓名不能大于20个字符"
  	            },
  	          	Phone: {
  	                required: "您的手机不能为空",
  	                maxlength: "请输入正确的手机号码"
  	            },
  	            AreaID: {
  	                required: "所在区域不能为空"
  	            }
  	        }
  		});
  	});
  	
  	function submitForm(){
  		$("#CityAreaID").val($("#AreaID").find("option:selected").val());
  	}
  	</script>
</body>
</html>
