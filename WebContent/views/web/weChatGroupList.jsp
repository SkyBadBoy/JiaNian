<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.core.model.Users"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
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
	<input type="hidden" name="CityAreaID" id="CityAreaID"
		value="${AreaID}" />
	<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
	<input type="hidden" name="ProvinceID" value="${ProvinceID}" />
	<input type="hidden" id="basePath" value="<%=basePath%>"
		name="basePath" />
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>社区分类列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div>
			<div class="col-sm-12">
				<!-- Example Events -->



				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree" Title="点击收起" class="collapsed"
								aria-expanded="false">查询条件</a>
								<strong style="color:#8a6d3b">社区地址：</strong><span style="color:#8a6d3b;cursor:pointer" onclick="copyToClipboard($('#URL').text())"  id="URL" title="点击复制到剪切板" ></span>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse in"
						aria-expanded="true">
						<div class="panel-body">
							<div class="col-md-3"
							<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>
							>
								<label>省份</label>
								<select class="form-control m-b" id="Province" 
									onchange="getCity(this);" name="Province">

									<c:forEach var="Part" items="${Province}">
										<option value="${Part.ID}"
											<c:if test="${Part.ID== ProvinceID}">selected="selected" </c:if>>${Part.name}</option>
									</c:forEach>
								</select>
							</div>


							<div class="col-md-3"
							<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>
							>
								<label>城市</label>
								<select class="form-control m-b" id="City" 
									onchange="getAreaID(this)" name="City">

								</select>
							</div>

							<div class="col-md-3"
							<c:if test="${AdminLevelArea}">
							style="display:none"
							</c:if>
							>
								<label>地区</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="AreaID" name="AreaID">
									<option value="${AreaID}" selected></option>
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


				<div class="example">
					<div id="ErrorMessage" role="alert"></div>
					<div class="btn-group hidden-xs" id="WeChatGroupEventsToolbar"
						style="width: 700px;" role="group">
						<button type="button" title="新增"
							onclick="javascript:window.location.href='<%=path%>/weChatGroup/addWeChatGroup'"
							class="btn btn-outline btn-default">
							<i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 新增
						</button>
						<button type="button" onclick="modifyWeChat()" title="修改"
							class="btn btn-outline btn-default">
							<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
						</button>
						<button type="button" onclick="deleteWeChat(88)" title="删除"
							class="btn btn-outline btn-default">
							<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 删除
						</button>
						<button type="button" onclick="deleteWeChat(1)" title="恢复"
							class="btn btn-outline btn-default">
							<i class="glyphicon glyphicon-play" aria-hidden="true"></i> 恢复
						</button>

					</div>
					<table id="WeChatGroupEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="state" data-radio="true"></th>
								<th data-field="pkid">ID</th>
								<th data-field="title">社区分类名称</th>
								<th data-field="industry">行业</th>
								<th data-field="area.name">所属社区地区</th>
								<th data-field="weChatCounter">加盟商家数</th>
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
		var DataTable = $("#WeChatGroupEvents");
		getCity(document.getElementById("Province"));
		
		$(document).ready(function(){
			/* 定义所有class为copy标签，点击后可复制被点击对象的文本 */
			    $("#URL").zclip({
			        path: $("#basePath").val()+'/js/ZeroClipboard.swf',
			        copy: function(){
			        return $(this).text();
			        },
			        beforeCopy:function(){/* 按住鼠标时的操作 */
			            $(this).css("color","orange");
			        }
			    });
		});
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/weChatGroup/getWeChatGroupList?sina=<%= SmBaseUtil.Random() %>&AreaID="
							+ $("#AreaID").val(),
					search : !0,
					pagination : !0,
					showRefresh : !0,
					clickToSelect : true,
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					iconSize : "outline",
					formatSearch:function(){return "请输入社区分类名称"},
					toolbar : "#WeChatGroupEventsToolbar",
					icons : geticons
				});
				DataTable
						.on(
								"dbl-click-row.bs.table",
								function(e, t, o) {
									if (CheckhasChecked(DataTable) == true) {
										var url = '<%=path%>/weChatGroup/addWeChatGroup?wid=';
										viewOrModifyByPKID(DataTable, url);
									}
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								})
			}();
			
		}(document, window, jQuery);

		function modifyWeChat() {
			if (CheckhasChecked(DataTable) == true) {
				var url = '<%=path%>/weChatGroup/addWeChatGroup?wid=';
				viewOrModifyByPKID(DataTable, url);
			}
		}

		function deleteWeChat(flag) {
			if (CheckhasChecked(DataTable) == true) {
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/weChatGroup/deleteWeChatGroup",
						data : {
							'wid' : wids,
							'state' : flag
						},
						success : function() {
							ViewSuccess($("#ErrorMessage"));
							getQueryList();
						}
					});
				}
			}

		}
		function getQueryList(SearchText) {
			if(SearchText==null || SearchText==undefined){
				SearchText="";
			}
			var params=MakeQueryParam($(".panel"));
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/weChatGroup/getWeChatGroupList?sina=<%= SmBaseUtil.Random() %>&Title="+ encodeURI(SearchText) +"&"+params
			});
			getAreaURL();

		}
		//获取地区的访问URL
		function getAreaURL() {
			var obj = $("#AreaID").val();
			var basePath = $("#basePath").val();
			$.getJSON(" <%=path%>/weChatGroup/getRegionPowerCode?sina=<%= SmBaseUtil.Random() %>", {
				AreaID : obj,
			}, function(json) {
				if (json.Data != "false") {
					var url = basePath
							+ "WeNewsAgency/phoneweChatGroupDetailList?_pc_="
							+ json.Data + "&&_area_=" + obj;
					$("#URL").text(url);
				}
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				AreaID : $("#AreaID").val()
			};

		}
	</script>
</body>
</html>