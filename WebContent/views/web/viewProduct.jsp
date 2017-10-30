<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
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

					<li><strong>公众号产品查看</strong></li>
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
				
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品名称</label>
								<form:input type="text" readonly="true" class="form-control" path="Name"
									id="Name" data-bv-notempty-message="产品名称不能为空" name="Name"
									 />
								
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品规格</label>
								<form:input type="text" class="form-control" readonly="true"
									path="Specifications" data-bv-notempty-message="产品规格不能为空"
									id="Specifications" name="Specifications"  />
								
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品单价</label>
								<form:input type="text" class="form-control" path="Price" readonly="true"
									id="Price" data-bv-notempty-message="产品单价不能为空" name="Price"
									 />
								
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>产品图片</label>
								
								<div class="row">
									<div class="col-md-6">
										<div class="image-crop">
											<img style="height: :100%; width: 100%"
												src="<%=path%>/${Image}" name="BannerImg">
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

											<div class="summernote"><div class="note-editable" style="overflow:overflow-x;word-wrap: break-word; ">${Content}</div></div>

										</div>
									</div>
								</div>
							</div>
							
						</div>

					</div>



				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>

	<jsp:include page="/include/commonJs.jsp" />

</body>
</html>