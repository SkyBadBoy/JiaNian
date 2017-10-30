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
					<li><strong>系统通知列表</strong></li>
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
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="ViewApply()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="查看" aria-hidden="true"></i>
								查看
							</button>
							
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">ID</th>
									<th data-field="title">活动标题</th>
									<!--  th data-field="notices.tudent.school">学校</th-->
									<th data-field="createTime">申请日期</th>
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
					url : "<%=path%>/Message/getMessageList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入系统通知标题"},
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				//	var e = o("#WeNewsAgencyEventsResult");
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									ViewApply();
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								})
			}()
		}(document, window, jQuery);

		function ViewApply() {
			if(CheckhasChecked(DataTable)==true){
				var url="";
				if(DataTable.bootstrapTable("getSelections")[0].type==<%= SmBaseGlobal.NewType.Applys.getid() %>){
					url='<%=path%>/ApplyList/ApplyListList?sina=<%= SmBaseUtil.Random() %>&pid='+DataTable.bootstrapTable("getSelections")[0].srcID;
				}
				if(DataTable.bootstrapTable("getSelections")[0].type==<%= SmBaseGlobal.NewType.News.getid() %>){
					url='<%=path%>/Notices/NewsList?sina=<%= SmBaseUtil.Random() %>&Type=2&Status=7&pid='+DataTable.bootstrapTable("getSelections")[0].srcID;
				}
				window.location.href=url;
			}

		}
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Message/deleteMessage",
						data : {
							'pid' : wids,
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
		
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Message/getMessageList?sina=<%= SmBaseUtil.Random() %>&Title="+searchText
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