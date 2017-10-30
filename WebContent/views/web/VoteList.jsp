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
					<li><strong>投票列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12" style="margin-top: 10px;">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<input type="hidden" id="WeChatID" name="WeChatID" iscon="true" value="${WeChatID}"/>
					<input type="hidden" id="ApplyID" name="ApplyID" iscon="true" value="${ApplyID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="javascript:window.location.href='${path}/Vote/addVote?IsNew=true'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="新增活动" aria-hidden="true"></i>
								新增投票
							</button>
							<button type="button" 
								onclick="editVote()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								修改投票
							</button>
							<button type="button" 
								onclick="deleteVote()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i></i>
								删除投票
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
								<th data-field="pkid">ID</th>
								<th data-field="title">标题</th>
								<th data-field="startTime">开始时间</th>
								<th data-field="endTime">结束时间</th>
								<th data-field="statusInfo.name">状态</th>
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
					url : "<%=path%>/Vote/getVoteList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入投票标题";},
					striped : !0, //是否显示行间隔色
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					clickToSelect:true,
					//rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				//	var e = o("#WeChatGroupEventsResult");
	
			}();

		}(document, window, jQuery);

		function deleteVote() {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "${path}/Vote/deleteVote",
						data : {
							'pid' : wids
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				} 
			}
		}

		function editVote() {
			if(CheckhasChecked(DataTable)==true){
				var url='${path}/Vote/addVote?IsNew=false&pid=';
				var wids =getRecordPKIDs(DataTable);
				window.location.href =url+wids ;
			}
			
			
		}
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			var params=MakeQueryParam($(".example-wrap"));
			DataTable.bootstrapTable("refresh", {
				url : "${path}/Vote/getVoteList?sina=<%= SmBaseUtil.Random() %>&searchText="+ searchText +"&&"+params
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				PC : "PC"
			};

		}


		function dispErrorRow(row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.baseInfo.name == "删除") {
				strclass = 'danger';//还有一个active
			} else if (row.hot == 1) {
				strclass = 'warning';//还有一个active
			}else {
				return {};
			}
			return {
				classes : strclass
			}
		}
	
	</script>
	<div id="loading"></div>
</body>
</html>