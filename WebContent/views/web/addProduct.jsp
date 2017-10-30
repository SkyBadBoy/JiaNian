<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath =SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
%>
<html>

<head>
<jsp:include page="/include/commonCss.jsp" />
<style>
.modal-backdrop.in{
	display:none;
}
.image_picker_selector img{
	width:100px;
	height:100px;
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
					<c:if test="${!AdminLevel}">
						<li><a href="<%=path%>/weChatPublic/weChatPublicList?sina=<%= SmBaseUtil.Random() %>"
							target="_self">微信公众号列表</a></li>
					</c:if>
					<li><a
						href="<%=path%>/Product/ProductList?sina=<%= SmBaseUtil.Random() %>&WeChatID=${WeChatID}"
						target="_self">微信公众号产品列表</a></li>

					<li><strong>公众号产品编辑</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="ProductForm" modelAttribute="ProductForm"
					enctype="multipart/form-data"
					action="${path}/Product/addProduct" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" value="${ImageID}" id="ImageID" name="ImageID" />
					<input type="hidden"  id="Content" name="Content" />
					<input type="hidden" id="WeChatID" name="WeChatID"
						value="${WeChatID}" />
					<input type="hidden" name="Img_X" id="Img_X" />
					<input type="hidden" name="Img_Y" id="Img_Y" />
					<input type="hidden" name="Img_W" id="Img_W" />
					<input type="hidden" name="Img_H" id="Img_H" />
					<input type="hidden" name="pageSize" id="pageSize"value="8" />
					<input type="hidden" name="pageNumber" id="pageNumber" value="0" />
					
					<input type="hidden" id="Path" value="<%=basePath%>" />
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品名称</label>
								<form:input type="text" class="form-control" path="Name"
									id="Name" data-bv-notempty-message="产品名称不能为空" name="Name"
									placeholder="请输入产品名称" />
								<form:errors path="Name" cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品规格</label>
								<form:input type="text" class="form-control"
									path="Specifications" data-bv-notempty-message="产品规格不能为空"
									id="Specifications" name="Specifications" placeholder="请输入产品规格" />
								<form:errors path="Specifications"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品单价</label>
								<form:input type="text" class="form-control" path="Price"
									id="Price" data-bv-notempty-message="产品单价不能为空" name="Price"
									placeholder="请输入产品单价" />
								<form:errors path="Price"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>请选择产品图片</label>
								<form:errors path="Image"
									cssClass="help-block m-b-none errorMsg"></form:errors>
								<div class="row">
									<div class="col-md-6">
										<div class="image-crop">
											<img style="height: :100%; width: 100%"
												src="<%=path%>/${Image}" name="BannerImg">
										</div>
									</div>
									<div class="col-md-6">
										<h4>图片预览：</h4>
										<div class="img-preview img-preview-normal"></div>
										<h4>说明：</h4>
										<p>你可以选择新图片上传，然后下载裁剪后的图片</p>
										<div class="btn-group">
											<label title="上传图片" for="inputImage" class="btn btn-primary">
												<input type="file" accept="image/*" name="fileImage"
												value="${Image}" id="inputImage" class="hide">
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
							<div class="row">
								<div class="col-sm-10">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5>产品简介</h5>
										</div>
										<div class="ibox-content no-padding">

											<div class="summernote">${Content}</div>

										</div>
									</div>
								</div>
							</div>
							<button type="submit" onclick="submitFrom()"
								class="btn btn-primary">保存</button>
						</div>

					</div>



				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	var o = $(".image-crop > img");
		$(function() {
				$(o).cropper({
					aspectRatio : 1,
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
			                $("#ImageID").val(0);
			            	o.cropper("reset", !0).cropper("replace", this.result);
			            }) : showMessage("请选择图片文件"));
			        })) : r.addClass("hide");
			        
			        $("#zoomIn").click(function() {
			            o.cropper("zoom", .1);
			        });
			        $("#zoomOut").click(function() {
			            o.cropper("zoom", -.1);
			        });
		        $("#ProductForm").validate({
					rules : {
						Name:{
							required : !0,
							maxlength : 100
						},
						Specifications : {
							maxlength : 40
						},
						Price : {
							required : !0,
							number:!0
						},
						Image : {
							Image : !0
						}
					},
					messages : {
						Name : {
							required : "产品名称不能为空",
							maxlength : "产品名称不能超过100个字符"
						},
						Specifications : {
							maxlength : "产品规格不能超过40个字符"
						},
						Price : {
							required : "产品单价不能为空",
							number:"产品单价必须为数字类型"
						},
						Image : {
							Image : "产品图片不能为空"
						}
					}
				});
		       
		       

		});
	
		function submitFrom() {
			$("#Img_X").val(o.cropper("getData").x);
			$("#Img_Y").val(o.cropper("getData").y);
			$("#Img_W").val(o.cropper("getData").width);
			$("#Img_H").val(o.cropper("getData").height);
			$("#Content").val($(".note-editable").code());
		}
		
		$(document).ready(function() {
		    $(".summernote").summernote({
		        lang: "zh-CN"
		    })
		    getImageList();
		
		});
		
		
	
	</script>
</body>
</html>