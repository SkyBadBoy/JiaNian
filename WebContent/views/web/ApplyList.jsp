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
<style>

#exampleTableEvents {

	  table-layout: fixed;  
    width: 98% border:0px;  
    margin: 0px;  
}

#exampleTableEvents tr td {
	text-overflow: ellipsis; /* for IE */
	-moz-text-overflow: ellipsis; /* for Firefox,mozilla */
	overflow: hidden;
	white-space: nowrap;
	text-align: left;
	
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
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>报名列表</strong></li>
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
							<div class="col-md-3">
								<label>状态</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Type" name="Type">
									<option value="<%= SmBaseGlobal.ApplyType.NoDispose.getid() %>">未处理</option>
									<option value="<%= SmBaseGlobal.ApplyType.LoadDispose.getid() %>">待确认</option>
									<option value="<%= SmBaseGlobal.ApplyType.Dispose.getid() %>">已确认(报名)</option>
									<option value="<%= SmBaseGlobal.ApplyType.NoApply.getid() %>">已确认(未报名)</option>
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
							<button type="button" onclick="ModifyApply(<%=SmBaseGlobal.ApplyType.Dispose.getid() %>)"   class="btn btn-outline btn-default">
								<i class="fa fa-check"  title="已确认(报名)"  aria-hidden="true"></i>
								已确认(报名)
							</button>
							<button type="button"  
								onclick="ModifyApply(<%=SmBaseGlobal.ApplyType.LoadDispose.getid() %>)"
								class="btn btn-outline btn-default">
								<i class="fa fa-close" title="待确认" aria-hidden="true"></i>
								待确认
							</button>
							<button type="button" onclick="ModifyApply(<%=SmBaseGlobal.ApplyType.NoApply.getid() %>)"   class="btn btn-outline btn-default">
								<i class="fa fa-close"  title="已确认(未报名)"  aria-hidden="true"></i>
								已确认(未报名)
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th   data-field="state" data-radio="true"></th>
									<th   data-field="pkid">ID</th>
									<th   data-field="userName">用户名字</th>
									<th   data-field="userPhone">用户手机号</th>
									<th   data-field="content">报名建议</th>
									<th   data-field="createTime">报名时间</th>
									<th   data-field="applyTypeStr">报名渠道</th>
									<th   data-field="feedback">报名进度</th>
									<th   data-field="modifyTime">进度时间</th>
									<th   data-field="user.name">跟进人员</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>
	
	
	
	<div class="modal" id="myModal" style="overflow: auto; " tabindex="-1" >
		<div class="modal-dialog">
			<div class="modal-content" style="width: 800px;left: -150px;height: 450px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">进度填写</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageResult"
							role="alert"></div>
				<form id="contentForm" action="#">
				<label style="display: none;" id="pkid"></label>
					<div class="row">
						<div class="ibox-content no-padding">
							<div class="summernote" style="height:200px;">${Content}</div>
						</div>
						<br/>
					</div>
				</form>
					<br/>
					<button id="referWechat" 
						class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="blueimp-gallery" class="blueimp-gallery">
         <div class="slides"></div>
         <h3 class="title"></h3>
         <a class="prev">‹</a>
         <a class="next">›</a>
         <a class="close">×</a>
         <a class="play-pause"></a>
         <ol class="indicator"></ol>
     </div>
	<input type="hidden" id="flags" >
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	
	var DataTable=$("#exampleTableEvents");
	
		!function(e, t, o) {
			"use strict";
			!

			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/ApplyList/getApplyListList?sina=<%= SmBaseUtil.Random() %>",
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入姓名或手机号"},
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
									}
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								});
			}();
			setTimeout(function(){$("#Status").trigger("change");}, 500);
			
		}(document, window, jQuery);
		$("#contentForm").validate({
			rules : {
				CheckResult : {
					required : !0,
					maxlength : 1000
				},
				Area : {
					required : !0,
					maxlength : 20
				},
				CorrectTeacher : {
					required : !0,
					maxlength : 20
				}
			},
			messages : {
				CheckResult : {
					required : "提交理由不能为空",
					maxlength : "提交理由不能超过1000个字符"
				},
				Area : {
					required : "批改机构不能为空",
					maxlength : "批改机构不能超过20个字符"
				},
				CorrectTeacher : {
					required : "批改教师不能为空",
					maxlength : "批改教师不能超过20个字符"
				}
			}
		});
		function SuccessApply() {
			
			var DataTableObj=DataTable.bootstrapTable("getSelections");
			if(CheckhasChecked(DataTable,<%=SmBaseGlobal.CheckStatus.Effective.getid() %>)){
				
			if(DataTableObj[0].correctTeacher==null || DataTableObj[0].correctTeacher==""){
				ViewWarningError("当前记录还未批改");
				return false;
			}
			var wids = getRecordPKIDs(DataTable);
			if (wids != '') {
				$.ajax({
					url : "<%=path%>/ApplyList/ComfrimApplyResult",
					data : {
						'pid' : wids
					},
					success : function() {
						ViewSuccess();
						getQueryList();
						$('#myModal').modal('hide');
					}
				});
			};
		}
		}
		function ModifyApply(flag) {
			if(CheckhasChecked(DataTable)){
				$("#flags").val(flag);
				var DataTableObj=DataTable.bootstrapTable("getSelections");
				$(".note-editable").html(DataTableObj[0].feedback)
				$('#myModal').modal('show');
				$("#referWechat").click(function(){
					if(!$("#contentForm").validate().form()){
						return 
					}
					var wids = getRecordPKIDs(DataTable);
					if (wids != '') {
						$.ajax({
							url : "<%=path%>/ApplyList/ModifyApplyResult",
							type:'POST',
							data : {
								'Type':flag,
								'pid' : wids,
								'Content':$(".note-editable").code()
							},
							success : function(obj) {
								if (obj.Status>0) {
									ViewSuccess();
									getQueryList();
									$('#myModal').modal('hide');
								}else{
									swal("","哎呦，发生点意外呦", "warning", {
		                  				  button: "重试",
		                  			}); 
								}
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
				url : "<%=path%>/ApplyList/getApplyListList?sina=<%= SmBaseUtil.Random() %>&UserName="+searchText
			});
		}
		function queryParams(params) {
			var ParamJson=MakeQueryParamJson($(".panel"));
			ParamJson.pageSize=params.limit;
			ParamJson.pageNumber=params.offset;
			return ParamJson;
		}
		 $(".summernote").summernote({
		        lang: "zh-CN",
		        height:200
		    })
	
	</script>
</body>
</html>