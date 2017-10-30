<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<c:if test="${!AdminLevel}">
						<li><a href="<%=path%>/weChatPublic/weChatPublicList"
							target="_self">微信公众号列表</a></li>
					</c:if>
					<li><strong>产品信息列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<input type="hidden" id="WeChatID" name="WeChatID" value="${WeChatID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="javascript:window.location.href='<%=path%>/Product/addProduct?WeChatID=${WeChatID}'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="新增" aria-hidden="true"></i>
								新增
							</button>
							<button type="button" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil"  title="修改"  onclick="modifyProduct()" aria-hidden="true"></i>
								修改
							</button>
							<button type="button" onclick="deleteWeChat(88)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"  aria-hidden="true"></i>
								删除
							</button>
							<button type="button" onclick="deleteWeChat(1)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-play" title="恢复"  aria-hidden="true"></i>
								恢复
							</button>
							<button type="button" title="提升排名" onclick="upLevel()" class="btn btn-outline btn-default">
								<i class="fa fa-level-up" title="提升排名"  aria-hidden="true"></i>
								提升排名
							</button>
							
							
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">ID</th>
									<th data-field="name">产品名称</th>
									<th data-field="specifications">规格</th>
									<th data-field="price">单价</th>
									
									
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
		!function(e, t, o) {
			"use strict";
			!

			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Product/getProductList?sina=<%= SmBaseUtil.Random() %>&WeChatID="+$("#WeChatID").val(),
					search : !0,
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
				//	var e = o("#WeNewsAgencyEventsResult");
				DataTable.on(
								"dbl-click-row.bs.table",
								function(e, t, o) {
									if(CheckhasChecked(DataTable)==true){
										var url='<%=path%>/Product/addProduct?pid=';
										viewOrModifyByPKID(DataTable,url);
									}
								})
			}()
		}(document, window, jQuery);
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				PC : "PC"
			};

		}
		function modifyProduct() {
			if(CheckhasChecked(DataTable)==true){
				var url='<%=path%>/Product/addProduct?pid=';
				viewOrModifyByPKID(DataTable,url);
			}
		}

		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Product/deleteProduct",
						data : {
							'pid' : wids,
							'state' : flag
						},
						success : function() {
							ViewSuccess();
							getProductList();
						}
					});
				}
			}

		}
		function upLevel(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Product/levelProduct",
						data : {
							'pid' : wids,
							'wid' : $("#WeChatID").val()
						},
						success : function() {
							ViewSuccess();
							getProductList();
							
						}
					});
				} 
			}

		}
		function getProductList(){
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Product/getProductList?sina=<%= SmBaseUtil.Random() %>&_wgid_="+$("#WeChatID").val()
			});
		}
	</script>
</body>
</html>