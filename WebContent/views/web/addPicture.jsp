<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	String ImageURl="#";
	if(user!=null && user.getImage()!=null){
		ImageURl =user.getImage().getUrl();
	}
	request.setAttribute("path", path);
	request.setAttribute("stauts", request.getParameter("stauts"));
	request.setAttribute("ProdID", request.getParameter("ProdID"));
%>
<html>

<head>

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

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<c:if test='${stauts}!=1'>
							<li style="display: block;"><a href="<%=path%>/ProdPicture/ProdPictureList" target="_self">图片列表</a></li>
					</c:if>
					<c:if test='${stauts }=1'>
							<li style="display: none;"><a href="<%=path%>/ProdPicture/ProdPictureList" target="_self">图片列表</a></li>
					</c:if>
					<li><strong>添加图片</strong></li>
					
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-9">
				<form:form id="PictureForm"  modelAttribute="PictureForm" 
					enctype="multipart/form-data"
					action="${path}/ProdPicture/addPicture" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" name="Img_X" id="Img_X" />
					<input type="hidden" name="Img_Y" id="Img_Y" />
					<input type="hidden" name="Img_W" id="Img_W" />
					<input type="hidden" name="Img_H" id="Img_H" />
					<input type="hidden" name="Img_SrcW" id="Img_SrcW" />
					<input type="hidden" name="Img_SrcH" id="Img_SrcH" />
					<input type="hidden" name="type" id="type" value="${type}" />
					<input type="hidden" name="uid" id="uid" value="<%=user.getID()%>" />
					<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
					<input type="hidden" name="ProdID" id="ProdID" value="${ProdID}" />
					<input type="hidden" name="backUrl" id="backUrl" value="<%=basePath%>include/pictureback.html" />
					<form:errors path="ID"
						cssClass="help-block m-b-none errorMsg"></form:errors>

					<div class="form-group">
						<label>请选择图片</label>
						<input type="file" accept="image/*" name="file"  data-bv-notempty-message="图片不能为空"
										value="${inputImage}" id="inputImage" class="hide">
						
						<div class="row">
							<div class="col-md-6">
								<div class="image-crop">
									<img id="ImageSrc" style="height: :100%;width: 100%" src="${inputImage}">
								</div>
							</div>
							<div class="col-md-6">
								<h4>图片预览：</h4>
								<div class="img-preview img-preview-sm"></div>
								<h4>说明：</h4>
								<p>你可以选择新图片上传，然后下载裁剪后的图片</p>
								<div class="btn-group">
									<label title="上传图片" for="inputImage" class="btn btn-primary">
										
										上传新图片
									</label>
								</div>

								<div class="btn-group">
									<button class="btn btn-white" id="zoomIn" type="button">放大</button>
									<button class="btn btn-white" id="zoomOut" type="button">缩小</button>
								</div>
							</div>
						</div>
					</div>
					<button type="button" onclick="InsertPicture($('#PictureForm'))"
									class="btn btn-primary">保存</button>
					<!-- <button type="submit" onclick="submitFrom()"
						class="btn btn-primary">保存</button> -->
				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>


	
	<jsp:include page="/include/commonJs.jsp" />
	
	<script type="text/javascript">
		var o = $(".image-crop > img");
		$(function() {
			$("#PictureForm").validate({
				rules : {
					file:{
						required : !0
						
					}
				},
				messages : {
					file : {
						required : "图片不能为空"
					}
					
				}
			});
			
			$(o).cropper({
				
				autoCropArea: 1,
				 mouseWheelZoom: false,
				preview : ".img-preview",
				done : function() {
				}
			});
			var r = $("#inputImage");
			 (window.FileReader) ? (r.change(function() {
		            var e, i = new FileReader,
		            t = this.files;
		            t.length && (e = t[0], /^image\/\w+$/.test(e.type) ? (i.readAsDataURL(e), i.onload = function() {
		                o.cropper("reset", !0).cropper("replace", this.result);
		            }) : showMessage("请选择图片文件"));
		        })) : r.addClass("hide");
		        
		        $("#zoomIn").click(function() {
		            o.cropper("zoom", .1);
		        });
		        $("#zoomOut").click(function() {
		            o.cropper("zoom", -.1);
		        });

		});
	
		function InsertPicture(objForm) 
		{
			
			$("#loading").css("display","block");
			$("#Img_X").val(o.cropper("getData").x);
			$("#Img_Y").val(o.cropper("getData").y);
			$("#Img_W").val(o.cropper("getData").width);
			$("#Img_H").val(o.cropper("getData").height);
			$("#Img_SrcW").val(o.cropper("getImageData").naturalWidth);
			$("#Img_SrcH").val(o.cropper("getImageData").naturalHeight);
			setTimeout(function(){
				var i_div = document.getElementById("i_frame_div");
				  i_div.innerHTML="<IFrame id=\"i_frame\"  name=\"i_frame\" width=\"1px\" height=\"1px\" style=\"display:none\" src=\"about:blank\"></IFrame>";
				  objForm.attr("action_bak",objForm.attr("action"));
				  objForm.attr("action","<%=SmBaseGlobal.PictureService%>");
				  objForm.attr("target","i_frame");
				  objForm.submit();
			},100); 
				
		}
		function realformsubmit(){
			$("#PictureForm").attr("action",$("#PictureForm").attr("action_bak"));
			$("#PictureForm").attr("target","");
			$("#PictureForm").submit();
		}
		function getIframeVal(val)  
		 {  
			 $("#loading").css("display","none");
			 var json=eval("("+decodeURIComponent(val[0])+")");
			 if(json.Status>0){
				 $("#ID").val(json.Status);
				 realformsubmit();
			 }else{
				 alert(json.ErrorMsg);
			 }
			 
		 }  
	</script>
	<div id="loading"></div>
	<div id="i_frame_div"></div>
</body>
</html>