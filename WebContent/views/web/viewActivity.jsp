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
						href="<%=path%>/Activity/ActivityList?sina=<%= SmBaseUtil.Random() %>&WeChatID=${WeChatID}"
						target="_self">活动列表</a></li>

					<li><strong>公众号活动查看</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="ProductForm" modelAttribute="ActivityForm"
					enctype="multipart/form-data"
					action="${path}/Activity/addActivity" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden"  id="Content" name="Content" />
					<input type="hidden" id="WeChatID" name="WeChatID"
						value="${WeChatID}" />
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>活动名称</label>
							<form:input type="text" class="form-control" path="Title" readonly="readonly"
								id="Title" data-bv-notempty-message="活动名称不能为空" name="Title"
								 />
							<form:errors path="Title" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>过期时间</label>
							<input type="text" class="form-control" value="${ActivityEndTime}" readonly="readonly"
								 data-bv-notempty-message="过期时间不能为空"
								id="ActivityEndTime" name="ActivityEndTime"/>
							<form:errors path="EndTime"
								cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>活动图片</label>
							<form:errors path="Image" cssClass="help-block m-b-none errorMsg"></form:errors>
							<div class="row">
								<div class="col-md-6">
									<div class="image-crop">
										<img style="height: :100%; width: 100%"
											src="<%=path%>/${Image}" name="BannerImg">
									</div>
								</div>
								
							</div>
							
						</div>

					</div>
					</div>
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>活动简介</label>
							<div class="ibox-content no-padding">
								<div class="summernote"><div class="note-editable" style="overflow:overflow-x;word-wrap: break-word;">${ActivityForm.content}</div></div>
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