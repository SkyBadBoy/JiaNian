<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Users"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user =(Users)request.getSession().getAttribute("UserName");
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />
</head>

<body>
	<div class="container-fluid">
<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}"
						id="ProvinceID" />
	<input type="hidden" name="CityAreaID" id="CityAreaID"
		value="${AreaID}" />
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>社区广告列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						<div  id="ErrorMessage"
							role="alert"></div>
							<div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" Title="点击收起" class="collapsed" aria-expanded="false">查询条件</a>
                                            </h4>
                                    </div>
                                    <div id="collapseThree" class="panel-collapse collapse in" aria-expanded="true">
                                        <div class="panel-body">
                                           <div class="col-md-3"
                                           <c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>
                                           >
                                           <label>省份</label>
								<select class="form-control m-b" id="Province" 
									onchange="getCity(this)" name="Province">
									
									<c:forEach var="Part" items="${Province}">
										<option value="${Part.ID}"
											<c:if test="${Part.ID== ProvinceID}">selected="selected" </c:if>>${Part.name}</option>
									</c:forEach>
								</select>
							</div>


							<div class="col-md-2"
							<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>
							>
							<label>城市</label>
								<select class="form-control m-b" id="City"
									onchange="getAreaID(this)" name="City">
									
								</select>
							</div>

							<div class="col-md-2"
							<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>
							>
							<label>区域</label>
								<select class="form-control m-b"
									onchange="getQueryList()" id="AreaID" iscon="true" name="AreaID">
									<option value="${AreaID}" selected></option>
								</select>
							</div>
							<div class="col-md-2">
							<label>年份</label>
								<select class="form-control m-b" id="Year" onchange="getQueryList();" iscon="true" name="Year">
								<c:forEach var="Part" items="${Years}">
									<option value="${Part}"
										<c:if test="${Part== Year}">selected="selected" </c:if>>${Part}</option>
								</c:forEach>
							</select>
							</div>
							<div class="col-md-2">
							<label>月份</label>
								<select class="form-control m-b" id="Month" onchange="getQueryList();" iscon="true" name="Month">
								<c:forEach var="Part" items="${Months}">
									<option value="${Part}"
										<c:if test="${Part== Month}">selected="selected" </c:if>>${Part}</option>
								</c:forEach>
							</select>
							</div>
							<div class="col-md-3">
								<label>状态</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Status" name="Status">
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">正常</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
								</select>
							</div>
                                        </div>
                                    </div>
                                </div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" title="新增"
								onclick="javascript:window.location.href='<%=path%>/weChatbanner/addweChatbanner'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								新增
							</button>
							<button type="button" title="修改" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" onclick="modifyWeChat()" aria-hidden="true"></i>
								修改
							</button>
							<button type="button" title="删除" onclick="deleteWeChat(88)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
								删除
							</button>
							<button type="button" title="恢复" onclick="deleteWeChat(1)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-play" aria-hidden="true"></i>
								恢复
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">ID</th>
									<th data-field="area.name">联盟地区</th>
									<th data-field="weChat.company">公众号</th>
									<th data-field="year">年份</th>
									<th data-field="month">年份</th>
									
									<th data-field="baseInfo.name">状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
		var DataTable=$("#exampleTableEvents");
		getCity(document.getElementById("Province"));
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/weChatbanner/getweChatbannerList?sina=<%= SmBaseUtil.Random() %>&"+MakeQueryParam($(".panel")),
					
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				//	var e = o("#WeChatGroupEventsResult");
				DataTable.on(
								"dbl-click-row.bs.table",
								function(e, t, o) {
									if(CheckhasChecked(DataTable)==true){
										var url='<%=path%>/weChatbanner/addweChatbanner?wid=';
										viewOrModifyByPKID(DataTable,url);
									}
								})
			}()
		}(document, window, jQuery);

		function modifyWeChat() {
			if(CheckhasChecked(DataTable)==true){
				var url='<%=path%>/weChatbanner/addweChatbanner?wid=';
				viewOrModifyByPKID(DataTable,url);
			}
		}

		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/weChatbanner/deleteweChatbanner",
						data : {
							'wid' : wids,
							'state' : flag
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				}
			}

		}
		
		function getQueryList() {
			var params=MakeQueryParam($(".panel"));
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/weChatbanner/getweChatbannerList?sina=<%= SmBaseUtil.Random() %>&"+params
			});

		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
	</script>
</body>
</html>