<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="gp.common.Model"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = SmBaseUtil.getProjectBaseUrl(request);
request.setAttribute("path", path);
%>
<html>

<head>
<title>导入学生</title>
<jsp:include page="/include/commonCss.jsp" />
<style>
#loading {
			
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/wsload.gif) no-repeat center center black;
            background-size: 3%;
        }
</style>
</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-9">
				<form id="fileForm"  
					enctype="multipart/form-data"
					action="#" method="post">			
					<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
				

					<div class="form-group">
						<label>请选择文件模版</label>
						<input type="file"  name="file"  data-bv-notempty-message="上传文件不能为空"
										 id="file" >
						
						
					</div>
					
					<div class="form-group">
						请下载模版，并按照模版的格式录入数据，否则可能导致无法成功上传：<a href="<%=basePath %>/upload/Templates.xlsx">下载模版</a>
						
						
					</div>
					<div class="form-group hide">
						<p id="errorMsg" class="errorMsg"></p>
					</div>
					<button type="button" onclick="InsertPicture($('#fileForm'))"
									class="btn btn-primary">保存</button>
				</form>
			</div>
		</div>
		<!-- /.row -->

	</div>


	
	<jsp:include page="/include/commonJs.jsp" />
	
	<script type="text/javascript">
		$(function() {
			$("#fileForm").validate({
				rules : {
					file:{
						required : !0
						
					}
				},
				messages : {
					file : {
						required : "上传文件不能为空"
					}
					
				}
			});

		});
	
		function InsertPicture(objForm) 
		{
			if($("#fileForm").valid()){
				$("#loading").css("display","block");
				 var formData = new FormData($( "#fileForm" )[0]);  
			     $.ajax({  
			          url: '${path}/Students/importStudents' ,  
			          type: 'POST',  
			          data: formData,  
			          async: true,  
			          cache: false,  
			          contentType: false,  
			          processData: false,  
			          success: function (returndata) {
			        	  if(returndata.status){
			        		  parent.getQueryList();
			        		  alert("上传成功");
			        		  
			        		  parent.hideImport();
			        	  }else{
			        		  $(".hide").removeClass("hide")
			        		  $("#errorMsg").text(returndata.message);
			        	  }
			        	  $("#loading").css("display","none");
			              
			          },  
			          error: function (returndata) {  
			        	  $(".hide").removeClass("hide")
			              $("#errorMsg").text("上传失败,稍后再试");  
			              $("#loading").css("display","none");
			          }  
			     });  
				
			}
		}
	</script>
	<div id="loading"></div>
</body>
</html>