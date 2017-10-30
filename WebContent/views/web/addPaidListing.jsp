<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%
	String path = request.getContextPath();
	String basePath =SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
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
					<li><a href="<%=path%>/PaidListing/PaidListingList"
						target="_self">排名列表</a></li>
					<li><strong>新增排名</strong></li>
				</ol>
			</div>
			<br />
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="WeChatGroupForm" modelAttribute="PaidListingForm"
					target="_self" action="${path}/PaidListing/addPaidListing"
					method="post">
					<form:input path="ID" type="hidden" id="ID" name="ID" />

					<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}"
						id="ProvinceID" />
						<input type="hidden" name="CityAreaID" id="CityAreaID"
		value="${AreaID}" />
					<div class="row"></div>
					<div class="row">
						<div class="form-group">
							<div class="col-md-2"
								<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>>
								<label>省份</label>
								<div class="input-group m-b">
									<select class="form-control m-b" id="Province"
										onchange="getCity(this)" name="Province">

										<c:forEach var="Part" items="${Province}">
											<option value="${Part.REGION_ID}"
												<c:if test="${Part.REGION_ID== ProvinceID}">selected="selected" </c:if>>${Part.REGION_NAME}</option>
										</c:forEach>
									</select>

								</div>
							</div>
							<div class="col-md-2"
								<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>>
								<label>城市</label>
								<div class="input-group m-b">
									<select class="form-control m-b" id="City"
										onchange="getAreaID(this)" name="City">

									</select>
								</div>
							</div>
							<div class="col-md-2"
								<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>>
								<label>地区</label>
								<div class="input-group m-b">
									<select class="form-control m-b" id="AreaID" name="AreaID">

									</select>
								</div>
							</div>
						</div>
						<form:errors path="AreaID" cssClass="help-block m-b-none errorMsg"></form:errors>
					</div>
					<div class="row">
						<div class="col-sm-2">
							<div class="form-group">
								<label>年份</label> <br />
								<div class="input-group m-b">
									<select class="form-control m-b" id="Year" name="Year">
										<c:forEach var="Part" items="${Years}">
											<option value="${Part}"
												<c:if test="${Part== Year}">selected="selected" </c:if>>${Part}</option>
										</c:forEach>
									</select>
									<form:errors path="Year"
										cssClass="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							<label>月份</label>
							<div class="input-group m-b">
								<select class="form-control m-b" id="Month" name="Month">
									<c:forEach var="Part" items="${Months}">
										<option value="${Part}"
											<c:if test="${Part== Month}">selected="selected" </c:if>>${Part}</option>
									</c:forEach>
								</select>

								<form:errors path="Month"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
			
			<div class="row">
				<div class="col-sm-8">

					<div class="form-group" required>
						<label>微信公众号</label>
						<form:errors path="WeChatID"
								cssClass="help-block m-b-none errorMsg"></form:errors>
						<div class="input-group m-b">
							<div class="input-group-btn">

								<button type="button" data-toggle="modal" data-target="#myModal"
									onclick="getWechatPublic()" class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
							</div>
							<input type="text" readonly="readonly" placeholder="请选择微信公众号"
								value="${WeChatName}" data-bv-notempty-message="微信公众号不能为空"
								name="WeChatName" class="form-control">
							<form:input type="hidden" path="WeChatID" id="WeChatID"
								name="WeChatID" />
							
						</div>

					</div>
				</div>
			</div>
			<br />
			<p>
				<button type="submit" onclick="submitFrom()" class="btn btn-primary">保存</button>
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
								<th data-field="state" data-radio="true"></th>
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
		var DataTable = $("#WeChatGroupPartEvents");
		getCity(document.getElementById("Province"));
		DataTable.bootstrapTable({
			url : "<%=path%>/weChatGroup/getWeChatPublicList?sina=<%= SmBaseUtil.Random() %>&wid=" + $("#ID").val(),
			toolbar : "#WeChatGroupPartToolbar"

		});
		$("#WeChatGroupForm").validate({
			rules : {
				Year : {
					required : !0
				},
				Month : {
					required : !0
				},
				AreaID : {
					required : !0
				}
			},
			messages : {
				Year : {
					required : "年份不能为空"
				},
				Month : {
					required : "月份不能为空"
				},
				AreaID : {
					required : "地区不能为空"
				}
			}
		});

		$('#referWechat').click(function() {
			var selectContent = $("#wechatlist").bootstrapTable('getSelections');

			for (i in selectContent) {
				var rowObj = new Object();
				rowObj.pkid = selectContent[i].pkid;
				rowObj.weChat = selectContent[i];
				rowObj.state = selectContent[i].state;
				DataTable.bootstrapTable("append", rowObj);

			}

		});
		/*公众号参照*/
		function getWechatPublic() {
			var AreaID=$("#AreaID").val();
			$("#wechatlist").bootstrapTable({
				url : "<%=path%>/weChatPublic/getWeChatPublicForEffList?sina=<%= SmBaseUtil.Random() %>&AreaID="+ AreaID,
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				clickToSelect : true,
				sidePagination : "server", //服务端请求
				queryParams : queryParams,
				responseHandler : responseHandler,
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbar",
				icons : {
					refresh : "glyphicon-repeat",
					toggle : "glyphicon-list-alt",
					columns : "glyphicon-list"
				}
			});
			$("#wechatlist").on(
					"search.bs.table",
					function(e, t, o) {
						getQueryList(t);
					})
		}
		function getQueryList(SearchText) {
			if(SearchText==null || SearchText==undefined){
				SearchText="";
			}
			
			$("#wechatlist").bootstrapTable("refresh", {
				url : "<%=path%>/weChatPublic/getWeChatPublicForEffList?sina=<%= SmBaseUtil.Random() %>&AreaID="+ $("#AreaID").val() +"&Company="+SearchText
			});

		}
		function submitFrom() {

			var wids = getRecordPKIDs(DataTable);

			$("#wids").val(wids);
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		$('#referWechat').click(function() {
			var selectContent = $("#wechatlist").bootstrapTable('getSelections');
			$("* [name='WeChatID']").val(selectContent[0].pkid);
			$("* [name='WeChatName']").val(selectContent[0].company);
		});
	</script>
</body>
</html>