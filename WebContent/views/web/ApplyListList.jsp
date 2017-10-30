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
					<li><strong>区域代理申请列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree" Title="点击收起" class="collapsed"
								aria-expanded="false">查询条件</a>
								
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse in"
						aria-expanded="true">
						<div class="panel-body">
						<input type="hidden" name="Company" value="${KeyWord}" id="Company" iscon="true" />
							<div class="col-md-3">
								<label>状态</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Status" name="Status">
									<option value="<%= SmBaseGlobal.CheckStatus.Apply.getid() %>">待验证</option>
									<option value="<%= SmBaseGlobal.CheckStatus.UnActivate.getid() %>">未激活</option>
									<option value="<%= SmBaseGlobal.CheckStatus.NotPass.getid() %>">未通过</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">已通过</option>
								</select>
							</div>

						</div>

					</div>
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
								onclick="SuccessApply()"
								class="btn btn-outline btn-default">
								<i class="fa fa-check" title="审核通过" aria-hidden="true"></i>
								审核通过
							</button>
							<button type="button" onclick="UnSuccessApply()"   class="btn btn-outline btn-default">
								<i class="fa fa-close"  title="审核不通过"  aria-hidden="true"></i>
								审核不通过
							</button>
							<button type="button" onclick="DealInfoList()"   class="btn btn-outline btn-default">
								<i class="fa fa-close"  title="处理信息"  aria-hidden="true"></i>
								处理信息
							</button>
							
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">ID</th>
									<th data-field="userName">申请人</th>
									<th data-field="userPhone">申请人手机</th>
									<th data-field="area">申请区域</th>
									<th data-field="createTime">申请日期</th>
									<th data-field="content">申请理由</th>
									<th data-field="checker.name">审核人</th>
									<th data-field="modifyTime">审核日期</th>
									<th data-field="checkResult">审核结果</th>
									
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
	
	
	
	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">理由</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form id="contentForm" action="#">
						<textarea class="form-control" placeholder="请输入您的理由" id="Content" name="Content"  rows="3" ></textarea>
						</form>
					</div>
					<br/>
					<button id="referWechat" 
						class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal" id="myModalDealInfo" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">处理信息</h4>
				</div>
				<div class="modal-body">
					<table id="DealInfolist" data-height="200"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="pkid">ID</th>
								<th data-field="checker.name">处理人</th>
								<th data-field="createTime">处理时间</th>
								<th data-field="result">处理信息</th>
								<th data-field="baseInfo.name">处理结果</th>
							</tr>
						</thead>
					</table>
					<button id="referWechatClose" data-dismiss="modal"
						class="btn btn-warning">关闭</button>
				</div>
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
					url : "<%=path%>/ApplyList/getApplyListList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入申请人姓名"},
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
				//	var e = o("#WeChatGroupEventsResult");
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									if(CheckhasChecked(DataTable)==true){
										var url='<%=path%>/ApplyList/addApplyList?sina=<%= SmBaseUtil.Random() %>&pid=';
										viewOrModifyByPKID(DataTable,url);
									}
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								});
			}();
			setTimeout(function(){$("#Status").trigger("change");}, 500);
			
		}(document, window, jQuery);
		$("#contentForm").validate({
			rules : {
				Content : {
					required : !0,
					maxlength : 200
				}
			},
			messages : {
				Content : {
					required : "提交理由不能为空",
					maxlength : "提交理由不能超过200个字符"
				}
			}
		});
		function SuccessApply() {
			if(CheckhasChecked(DataTable,<%=SmBaseGlobal.CheckStatus.Effective.getid() %>)==true){
				var DataTableObj=DataTable.bootstrapTable("getSelections");
				if(DataTableObj.length>0 && DataTableObj[0].status==<%= SmBaseGlobal.CheckStatus.UnActivate.getid() %>){
					ViewWarningError("当前记录已经处于未激活,无法修改");
					return false;
				}
				$('#myModal').modal('show');
				$("#referWechat").click(function(){
					if(!$("#contentForm").validate().form()){
						return 
					}
					var wids = getRecordPKIDs(DataTable);
					if (wids != '') {
						$.ajax({
							url : "<%=path%>/ApplyList/ApplyResult",
							data : {
								'pid' : wids,
								'Status' : 1,
								'Content':$("#Content").val()
							},
							success : function() {
								ViewSuccess();
								getQueryList();
								$('#myModal').modal('hide');
							}
						});
					} 
				});
			}

		}
		function UnSuccessApply(flag) {
			if(CheckhasChecked(DataTable,<%=SmBaseGlobal.CheckStatus.NotPass.getid() %>)==true){
				var DataTableObj=DataTable.bootstrapTable("getSelections");
				if(DataTableObj.length>0 && DataTableObj[0].status==<%= SmBaseGlobal.CheckStatus.UnActivate.getid() %>){
					ViewWarningError("当前记录已经处于未激活,无法修改");
					return false;
				}
				$('#myModal').modal('show');
				$("#referWechat").click(function(){
					if(!$("#contentForm").validate().form()){
						return 
					}
					var wids = getRecordPKIDs(DataTable);
					if (wids != '') {
						$.ajax({
							url : "<%=path%>/ApplyList/ApplyResult",
							data : {
								'pid' : wids,
								'Status' : -1,
								'Content':$("#Content").val()
							},
							success : function() {
								ViewSuccess();
								getQueryList();
								$('#myModal').modal('hide');
							}
						});
					} 
				});
			}
		}
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			var params=MakeQueryParam($(".panel"));
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/ApplyList/getApplyListList?sina=<%= SmBaseUtil.Random() %>&UserName="+searchText+"&"+params
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				AreaID : $("#AreaID").val()
			};

		}
		
		
		function DealInfoList() {
			if(CheckhasChecked(DataTable)==true){
				var DataTableObj=DataTable.bootstrapTable("getSelections");
				
				$("#DealInfolist").bootstrapTable("refresh",
						{
							url : "<%=path%>/weChatGroup/getDealInfoList?sina=<%= SmBaseUtil.Random() %>&pid="
									+ DataTableObj[0].pkid

						});
				$('#myModalDealInfo').modal('show');
			}

		}
		$("#DealInfolist").bootstrapTable(
				{
					url : "<%=path%>/weChatGroup/getDealInfoList?sina=<%= SmBaseUtil.Random() %>"
		

				});
	</script>
</body>
</html>