<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
%>
<html >
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>绑定信息</title>
<link rel="stylesheet" href="">
<link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
 <link href="<%=basePath%>css/plugin/alertPopShow/alertPopShow.css?v=1" rel="stylesheet">
  <link href="<%=basePath%>css/plugin/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/apply/form.min.css?v=1"
	rel="stylesheet" type="text/css">
  <link href="<%=basePath%>css/style.css" rel="stylesheet">
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
        .web_toast{
        
        font-size: 14px;
        }
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

#bottom_info a { /*
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

input[type="submit"] {
	background-color: #18A689;
}

#subjects {
	padding-bottom: 0;
}

input[type="text"],input[type="password"],select,textarea {
	padding: 3px;
}

label.error {
	color: #cc5965;
	display: inline-block;
	margin-left: 5px
}

.errorMsg {
	color: #cc5965;
}

.help-block {
	color: #cc5965;
}
.ctrl_title{
    font-size: 14px;
    color: #6B6B6B;
    display: block;
    line-height: 1.2em;
    font-weight: 600;
    margin: .4em 0;
}
input[type="number"] {
    width: 100%;
    padding: 3px;
    margin: 1px 0;
    border-radius: 0;
    background-color: white;
    border: 1px solid #bfbfbf;
    }
    html{
    	background-color: #f4f4f4;
    }
    </style>
</head>
<body >
	<div class="">		

		<form id="registerForm" >
			<input type="hidden" id="uid" name="uid" value="<%=user.getID() %>" />
			<input type="hidden" name="returnView" value="${returnView}" />
			<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
			<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" />
			<input type="hidden" name="CityAreaID" value="${CityAreaID}" id="CityAreaID" />
			<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
			<div class="form_ctrl input_text" id="3" title="公司名称">
				<label class="ctrl_title">家长姓名<span style="color: #ff0000">*</span></label>
				 <input type="text" name="parentName" id="parentName" placeholder="请输入家长姓名"  />
			</div>
			<div class="form_ctrl input_text" id="3" title="孩子姓名">
				<label class="ctrl_title">孩子姓名<span style="color: #ff0000">*</span></label>
				 <input type="text" name="name" id="name" placeholder="请输入孩子姓名"  />
			</div>
			<div class="form_ctrl input_text" id="7" title="省份">
				<label class="ctrl_title">省份<span style="color: #ff0000">*</span></label>
				<select class="form-control m-b" id="Province"
					onchange="getCity(this);" name="Province">
				</select>
			</div>
			<div class="form_ctrl input_text" id="7" title="城市">
				<label class="ctrl_title">城市<span style="color: #ff0000">*</span></label>
				<select class="form-control m-b" id="City"
					onchange="getAreaID(this)" name="City">
				</select>
			</div>
			<div class="form_ctrl input_text" id="7" title="区域">
				<label class="ctrl_title">区域<span style="color: #ff0000">*</span></label>
				<select class="form-control m-b" id="AreaID"
					onchange="getUnitAreaID(this)" name="AreaID">
				</select>
			</div>
			<div class="form_ctrl input_text" id="7" title="学校">
				<label class="ctrl_title">学校<span style="color: #ff0000">*</span></label>
				<select class="form-control m-b" id="UnitAreaID" name="UnitAreaID">
				</select>
			</div>
			<div class="form_ctrl input_text" id="3" title="邮箱">
				<label class="ctrl_title">邮箱</label>
				 <input type="text" name="email" id="email" placeholder="请输入联系邮箱"  />
			</div>
			<div class="form_ctrl input_text" id="3" title="班级">
				<label class="ctrl_title">班级</label>
				 <input type="number" name="grade" id="grade" placeholder="请输入班级,如506"  />
			</div>
 			<div class="form_ctrl input_text" id="3" title="特长">
				<label class="ctrl_title">特长</label>
				 <textarea rows="3" id="habit" name="habit" placeholder="您的孩子有啥特长" ></textarea>
			</div>
			<div class="form_ctrl input_text" style="text-align: center;">
			<button id="submit1" type="submit" onclick="return submitFrom();"
				style="color: #fff; font-size: 16px;background-color: #68ccb3 ; width: 90%; border-radius:5px; -webkit-border-radius: 5px; padding: 5px" >提 交</button>
			</div>
		</form>
	</div>
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
	 $("#loading").css("display","none");
	 getProvince();
	 if($("#ProvinceID").val()==""){
		 getCurrentlocation();
	 }
	 
	 
		function submitFrom() {
			if($("#UnitAreaID").val()==""){
				webToast("请选择学校！","bottom", 3000);
				return false;
			}else if($("#parentName").val()==""){
				webToast("家长姓名不能为空！","bottom", 3000);
				return false;
			}else if($("#name").val()==""){
				webToast("孩子姓名不能为空！","bottom", 3000);
				return false;
			}else{
				$("#loading").css("display","block");
				$.ajax({
		    		url : "${path}/Students/phoneRegister?sina=<%=SmBaseUtil.Random()%>",
		    		type:"POST",
		    		data : {
		    			'UnitAreaID' : $("#UnitAreaID").val(),
		    			'parentName' : $("#parentName").val(),
		    			'name' : $("#name").val(),
		    			'grade' : $("#grade").val(),
		    			'habit' : $('#habit').val(),
		    			'email' : $("#email").val(),
		    			'uid' : $("#uid").val(),
		    			'school' : $("#UnitAreaID").find("option:selected").text()
		    		},
		    		success : function(json) {
		    			if(json.Status==true){
		    				 webToast("绑定成功,正在跳转！","bottom", 2000);
		    				 $("#loading").css("display","none");
		    				
		    				 console.log(json.returnURL)
		    				 if(json.returnURL!=null && json.returnURL!=undefined && json.returnURL!=""&&json.returnURL!="null"){
		    					
		    					 setTimeout(function () {
		    						 console.log(json.returnURL)
		    						 window.location.href=json.returnURL;
		        	              }, 2000);
		    				 }else{
		    				
		    					 setTimeout(function () {
		    						 window.location.href="<%=path%>/Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random() %>";
		        	              }, 2000);
		    					 
		    				 }
		    				if(json.zhuye){
		    					
		    					 setTimeout(function () {
		    						 window.location.href="<%=path%>/Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random() %>";
		        	              }, 2000);
		    				}
		    			
		    			}else{
		    				webToast(json.Message,"bottom", 2000);
		    				$("#loading").css("display","none");
		    			}
		    		}
		    	});
			}
			return false;
			
		}
</script>
<div id="loading"></div>
</body>
</html>