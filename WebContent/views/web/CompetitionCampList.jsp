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
	table
  {
  table-layout:fixed;
  }
  td{
  text-overflow: ellipsis; 
white-space: nowrap; 
overflow: hidden; 
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
					<input type="hidden" id="type" name="type"  />
					<input type="hidden" id="WeChatID" name="WeChatID" iscon="true" value="${WeChatID}"/>
					
					<input type="hidden" id="ApplyID" name="ApplyID" iscon="true" value="${ApplyID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" title="报名列表" id='relatedShow' class="btn btn-outline btn-default">
								<i  title="报名列表"  aria-hidden="true"></i>
								报名列表
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">编号</th>
									<th data-field="title">标题</th>
									<th data-field="name">负责人</th>
									<th data-field="phone">联系方式</th>
									<th data-field="createTime">创建时间</th>
									<th data-field="stringParamA">费用</th>
									<th data-field="stringParamB">预付</th>
									<th data-field="stringParamC">城市</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>
	<div class="modal" id="myModalActivity" style="overflow: auto;margin-left: -500px" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:1100px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">报名列表</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageStudent"
							role="alert"></div>
					<table id="Activity" data-height="500" 
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">编号</th>
									<th data-field="name">报名人</th>
									<th data-field="phone">联系方式</th>
									<th data-field="createTime">报名时间</th>
									<th data-field="stringParamC">选修课</th>
									<th data-field="stringParamD">是否需要影集</th>
									<th data-field="stringParamE">报名期次</th>
									<th data-field="stringParamA">邀请人</th>
									<th data-field="stringParamB">微米折扣数量</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/commonJs.jsp" />
	
	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
	var DataTable2=$("#Activity");
	var myPay=$("#mypayTable");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Competition/getCompetitionCampList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入标题";},
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
				
			}();

		}(document, window, jQuery);

		
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			var params=MakeQueryParam($(".example-wrap"));
			$("#ApplyID").val("");
			DataTable.bootstrapTable("refresh", {
				url : "${path}/Competition/getCompetitionList?sina=<%= SmBaseUtil.Random() %>&searchText="+ searchText +"&&"+params
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				PC : "PC"
			};

		}
		
		$("#relatedShow").click(function(){
			
			
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true){
				$("#type").val(t['pkid']);
				DataTable2.bootstrapTable({
					url : "<%=path%>/Competition/getCompetitionList?sina=<%= SmBaseUtil.Random() %>",
					//search : !0,
					pagination : !0,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
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
					toolbar : "#StudentListEventsToolbar",
					icons : geticons
				});
				$("#myModalActivity").modal("show");
				DataTable2.bootstrapTable("refresh", {
						url : "<%=path%>/Competition/getCompetitionList?sina=<%= SmBaseUtil.Random() %>"
				});
			}
		});
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				type : $("#type").val()
			};

		}

	</script>
	<div id="loading"></div>
</body>
</html>