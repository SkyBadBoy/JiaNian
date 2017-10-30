<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user =(Users)request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
	Boolean isManage=(user.getLevel()==SmBaseGlobal.LevelStatus.Manage.getid());
	Boolean isSuperManage=(user.getLevel()==SmBaseGlobal.LevelStatus.SuperManage.getid());
	request.setAttribute("isManage", isManage);
	request.setAttribute("isSuperManage", isSuperManage);
%>
<html>

<head>
<jsp:include page="/include/commonCss.jsp" />
<style>
	#loading {
	    opacity: 0.8;
	    position: fixed;
	    top: 0;
	    left: 0;
	    display:none;
	    width: 100%;
	    height: 100%;
	    z-index: 1055;
	    background: url(../img/wsload.gif) no-repeat center center black;
	    background-size: 3%;
	}
	.row{
		margin-left: 0px;
		margin-right: 0px;
	}
	.select{
	    margin-right: 15px;
		margin-left: 15px;
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
					<li><a href="${path}/home" target="_self">主页</a></li>
					<li><strong>活动信息列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<input type="hidden" id="WeChatID" name="WeChatID" iscon="true" value="${WeChatID}"/>
					<input type="hidden" id="ApplyID" name="ApplyID" iscon="true" value="${ApplyID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">编号</th>
									<th data-field="name">学校</th>
									<th data-field="teacher">教师</th>
									<th data-field="phone">手机号</th>
									<th data-field="annexURL">文件地址</th>
									<th data-field="createTime">报名申请时间</th>
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
	var myPay=$("#mypayTable");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Competition/getCompetitionList?type=1&sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入学校或教师标名称";},
					striped : !0, //是否显示行间隔色
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				DataTable.on("dbl-click-row.bs.table",
					function(e, t, o) {
						console.log(t['annexURL']);
						window.location.href=t["annexURL"];
					}).on("search.bs.table", function(e, t, o) {
						getQueryList(t);
					});
			}();

		}(document, window, jQuery);

		
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			var params=MakeQueryParam($(".example-wrap"));
			$("#ApplyID").val("");
			DataTable.bootstrapTable("refresh", {
				url : "${path}/Competition/getCompetitionList?type=1&sina=<%= SmBaseUtil.Random() %>&searchText="+ searchText +"&&"+params
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				PC : "PC"
			};

		}


	</script>
	<div id="loading"></div>
</body>
</html>