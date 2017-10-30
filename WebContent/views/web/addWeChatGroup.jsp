<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp">
</head>
<body>
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a href="<%=path%>/weChatGroup/weChatGroupList?sina=<%= SmBaseUtil.Random() %>"
						target="_self">社区列表</a></li>
					<li><strong>社区编辑</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="WeChatGroupForm" modelAttribute="WeChatGroupForm"
					target="_self" enctype="multipart/form-data"
					action="${path}/weChatGroup/addWeChatGroup" method="post">
					<form:input path="ID" type="hidden" id="ID" name="ID" />
					<form:input path="Code" type="hidden" id="Code" name="Code" />
					<input type="hidden" id="wids" name="wids" />
					<input type="hidden" id="CityAreaID" name="CityAreaID" value="${AreaID}" />
					<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}"
						id="ProvinceID" />
						<div class="row">
					<div class="col-sm-8">
						<div class="form-group" required>
							<label>社区名称</label>
							<form:input type="text" class="form-control" path="title"
								name="Title" placeholder="请输入社区名称"
								data-bv-notempty-message="社区名称不能为空" />
							<form:errors path="Title" cssClass="errorMsg"></form:errors>
						</div>
					</div>
					</div>
					<div class="row">
					<div class="form-group">
						<div class="col-md-2">
							<label>社区地区</label>
							<div class="input-group m-b">
								<select class="form-control m-b" id="Province"<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									onchange="getCity(this)" name="Province">
									
									<c:forEach var="Part" items="${Province}">
										<option value="${Part.ID}"
											<c:if test="${Part.ID== ProvinceID}">selected="selected" </c:if>>${Part.name}</option>
									</c:forEach>
								</select>

							</div>
						</div>
						<div class="col-md-2">
							<label>城市</label>
							<div class="input-group m-b">
								<select class="form-control m-b" id="City" <c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									onchange="getAreaID(this)" name="City">
									
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<label>地区</label>
							<div class="input-group m-b">
								<select class="form-control m-b" <c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if> id="AreaID" name="AreaID">
									
								</select>
							</div>
						</div>
					</div>
					<form:errors path="AreaID" cssClass="help-block m-b-none errorMsg"></form:errors>
					</div>
					<div class="row">
					<div class="col-sm-8">
					
					<div class="form-group" required>
						<label>行业</label>
						<form:input type="text" class="form-control" path="industry" 
							name="Industry" placeholder="请输入行业"
							data-bv-notempty-message="行业不能为空" />
						<form:errors path="Industry" cssClass="errorMsg"></form:errors>
					</div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-8">
					<label>请选择要组成社区的公众号</label>
					<div id="ErrorMessage" role="alert"></div>
						<c:if test="${Status==1}">
					<div class="btn-group hidden-xs" id="WeChatGroupPartToolbar"
						role="group">
					
							<button type="button" data-toggle="modal" data-target="#myModal"
								onclick="getWechatPublic()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
							</button>
							<button type="button" onclick="deleteWeChat()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
							</button>
					
					</div>
				</c:if>
					<table id="WeChatGroupPartEvents" data-height="250"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="pkid">ID</th>
								<th data-field="weChat.weChat">微信公众号</th>
								<th data-field="weChat.company">公司名称</th>
								<th data-field="weChat.content">公司简介</th>
							</tr>
						</thead>
					</table>
					</div>
					</div>
					<br />
					<p>
						<button type="submit" onclick="submitFrom()"
							class="btn btn-primary">保存</button>
					</p>
					
				</form:form>
			</div>
		</div>
		</div>
		<!-- /.row -->

	



	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">参照微信公众号</h4>
				</div>
				<div class="modal-body">
					<table id="wechatlist" data-height="400"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="pkid">ID</th>
								<th data-field="weChat">微信公众号</th>
								<th data-field="company">公司名称</th>
								<th data-field="content">公司简介</th>
							</tr>
						</thead>
					</table>
					<button id="referWechat" data-dismiss="modal"
						class="btn btn-warning">参照</button>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	getCity(document.getElementById("Province"));
	var DataTable=$("#WeChatGroupPartEvents");
	DataTable.bootstrapTable(
				{	clickToSelect:true,
					url : "<%=path%>/weChatGroup/getWeChatPublicList?sina=<%= SmBaseUtil.Random() %>&wid="
							+ $("#ID").val(),
					toolbar : "#WeChatGroupPartToolbar"

				});
		$("#WeChatGroupForm").validate({
			rules : {
				title : {
					required : !0,
					maxlength : 20
				},
				industry : {
					required : !0
				},
				AreaID : {
					required : !0
				}
			},
			messages : {
				title : {
					required : "标题不能为空",
					maxlength : "标题长度不能超过20个字符"
				},
				industry : {
					required : "所属行业不能为空"
				},
				AreaID : {
					required : "社区地区不能为空"
				}
			}
		});

		$('#referWechat').click(
				function() {
					var selectContent = $("#wechatlist").bootstrapTable(
							'getSelections');

					for (i in selectContent) {
						var rowObj = new Object();
						rowObj.pkid = selectContent[i].pkid;
						rowObj.weChat = selectContent[i];
						rowObj.state = selectContent[i].state;
						DataTable.bootstrapTable("append",
								rowObj);

					}

				});
		/*公众号参照*/
		function getWechatPublic() {
			$("#wechatlist").bootstrapTable("refresh", {
				url : "<%=path%>/weChatPublic/getReferWeChatPublicList?sina=<%= SmBaseUtil.Random() %>&AreaID="+$("#AreaID").val()
			});
			$("#wechatlist").bootstrapTable({
				url : "<%=path%>/weChatPublic/getReferWeChatPublicList?sina=<%= SmBaseUtil.Random() %>&AreaID="+$("#AreaID").val(),
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				clickToSelect:true,
				showColumns : !0,
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbar",
				icons : geticons
			});
		}

		function deleteWeChat() {
			var GroupID = $("#ID").val();
			if (GroupID != "") {
				if(CheckhasChecked(DataTable)==true){
					var wids = getRecordPKIDs(DataTable);
					if (wids != '') {
						$.ajax({
							url : "<%=path%>/weChatGroupPart/deleteWeChatGroupPart",
							data : {
								'wid' : wids
							},
							success : function() {
								DataTable.bootstrapTable(
												"refresh",
												{
													url : "<%=path%>/weChatGroupPart/getWeChatGroupPartList?sina=<%= SmBaseUtil.Random() %>&GroupID="
															+ GroupID
												});
							}
						});
					}
				}

		}
		}
		function submitFrom() {
			var wids = getRecordPKIDs(DataTable);
			$("#wids").val(wids);
		}
	</script>
</body>
</html>