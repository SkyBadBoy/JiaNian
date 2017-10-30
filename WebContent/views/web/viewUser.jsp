<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	int level=SmBaseGlobal.LevelStatus.ParsonManage.getid();
	int Arealevel=SmBaseGlobal.LevelStatus.AreaManage.getid();
	Boolean isSupperManage=user.getLevel()==SmBaseGlobal.LevelStatus.SuperManage.getid();
	Boolean isAreaManage=user.getLevel()==Arealevel;
	request.setAttribute("SuperManage", SmBaseGlobal.LevelStatus.SuperManage.getid());
	request.setAttribute("path", path);
	
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />
</head>
<body>
	<div class="container-fluid">
	
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a href="<%=path%>/Users/UserList?sina=<%= SmBaseUtil.Random() %>"
						target="_self">用户列表</a></li>
					<li><strong>用户查看</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="usersForm" modelAttribute="usersForm"
					action="${path}/Users/addUser" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" id="CityAreaID" name="CityAreaID" value="${AreaID}" />
					<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
					<input type="hidden" name="LevelID" value="${LevelID}" id="LevelID" />
					
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户名</label>
							<form:input type="text" class="form-control" path="LoginName" readOnly="readOnly"
								id="LoginName"  name="LoginName"
								/>
							<form:errors path="LoginName" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户姓名</label>
							<form:input type="text" class="form-control" path="Name" readOnly="readOnly"
								id="Name"  name="Name"
								 />
							<form:errors path="Name" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户联系方式</label>
							<form:input type="text" class="form-control" path="Phone" readOnly="readOnly"
								id="Phone"  name="Phone"
								 />
							<form:errors path="Phone" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户类型</label>		
							<c:if test="${usersForm.level==SuperManage}">
							超级管理员
							</c:if>
							<c:if test="${usersForm.level!=SuperManage}">
								<select class="form-control m-b" id="Level" onchange="changelevel()" disabled="disabled"
										name="Level">
	
										<c:forEach var="Part" items="${AdminLevelList}">
											<option value="${Part.ID}" <c:if test="${Part.ID== usersForm.level}">selected="selected" </c:if>>
												${Part.name}</option>
										</c:forEach>
									</select>
							</c:if>
						</div>
					</div>
					<div class="col-sm-8" id="wechatDiv">
					<div class="form-group" >
						<label>微信公众号</label>
						<div class="input-group m-b">
							<input type="text" readonly="readonly" 
								value="${WeChatName}" data-bv-notempty-message="微信公众号不能为空"
								name="WeChatName" class="form-control">
							<form:input type="hidden" path="WeChatID" id="WeChatID"
						name="WeChatID" />
						<form:errors path="WeChatID" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					</div>
					
					<div class="col-sm-8"
					id="wechatArea"
					>
					<label>区域</label>
					<div class="form-group" >
					<div class="col-md-3">
								<select class="form-control m-b" id="Province"  disabled="disabled"
									onchange="getCity(this)" name="Province">

									<c:forEach var="Part" items="${Province}">
										<option value="${Part.REGION_ID}"
											<c:if test="${Part.REGION_ID== ProvinceID}">selected="selected" </c:if>>${Part.REGION_NAME}</option>
									</c:forEach>
								</select>
							</div>


							<div class="col-md-3">
								<select class="form-control m-b" id="City" disabled="disabled"
									onchange="getAreaID(this)" name="City">

								</select>
							</div>

							<div class="col-md-3">
								<select class="form-control m-b" onchange="getWeChatGroup()" disabled="disabled"
									id="AreaID" name="AreaID">

								</select>
							</div>
							<form:errors path="AreaID" cssClass="help-block m-b-none errorMsg"></form:errors>
					
					</div>
					</div>
					
					</div>
					
					
				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>
	
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	if($("#Province").val()!=""){
		getCity(document.getElementById("Province"));
	}else{
		getCity(document.getElementById("ProvinceID"));
	}
	
	</script>
</body>
</html>