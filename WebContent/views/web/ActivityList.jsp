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
							<button type="button" 
								onclick="javascript:window.location.href='${path}/Activity/addActivity?sina=<%= SmBaseUtil.Random() %>&WeChatID=${WeChatID}&isNew=0'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="新增活动" aria-hidden="true"></i>
								新增活动
							</button>
							<button type="button" class="btn btn-outline btn-default" onclick="modifyProduct()">
								<i class="glyphicon glyphicon-pencil"  title="修改活动"   aria-hidden="true"></i>
								修改活动
							</button>
							<button type="button" onclick="deleteWeChat(88)" title="删除活动"  class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"  aria-hidden="true"></i>
								删除活动
							</button>
							<button type="button" onclick="activityPartList(1)" title="报名列表"  class="btn btn-outline btn-default">
								<i class="fa fa-hand-paper-o" title="报名列表"  aria-hidden="true"></i>
								报名列表
							</button>
							<button type="button" onclick="activityPartList(2)" title="参赛列表"  class="btn btn-outline btn-default">
								<i class="fa fa-hand-paper-o" title="参赛列表"  aria-hidden="true"></i>
								参赛列表
							</button>
								
							<c:if test="${isManage or isSuperManage}">
								<button type="button" onclick="setTop()" title="热门活动只能设置一个,同时会更新到各大学校微新闻社的主广告位上" class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-open"   aria-hidden="true"></i>
									设热门活动
								</button>
							</c:if>
								<c:if test="${isManage or isSuperManage}">
								<button type="button" onclick="cancelTop()" title="取消热门活动" class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-trash"   aria-hidden="true"></i>
									取消热门
								</button>
							</c:if>
							<button type="button" onclick="myPayRecord()" title="付款记录"  class="btn btn-outline btn-default">
								付款记录
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="title">活动标题</th>
									<th data-field="isHot">是否热门</th>
									<th data-field="endTime">结束日期</th>
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
	<input type="hidden" id="type">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">学生列表</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageStudent"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="StudentListEventsToolbar"
							role="group">
							<button id="referWechat"
						class="btn btn-warning">确认参赛</button>
						<button onclick="refund()" id="reFund" class="btn btn-danger">退款</button>
						</div>
					<table id="wechatlist" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="student.pkid">ID</th>
								<th data-field="student.name">名字</th>
								<th data-field="student.school">学校</th>
								<th data-field="createTime">报名时间</th>
								<th data-field="student.isSex">性别</th>
								<th data-field="student.age">出生年月</th>
								<th data-field="student.phone">手机</th>
								
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="mypay" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="right: 30%;width: 800px;height: 500px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">付款记录</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessagemypay"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="mypayToolbar"
							role="group">
											<label>类型</label> <select class="form-control m-b"
												onchange="getMypayQueryList()" iscon="true" id="Status"
												name="Status">
												<option
													value="1">全部</option>
												<option
													value="<%= SmBaseGlobal.MoneyType.Pay.getid() %>">付款</option>
												<option
													value="<%= SmBaseGlobal.MoneyType.Refund.getid() %>">退款</option>
											</select>

						</div>
					<table id="mypayTable" data-height="400"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="pkid">ID</th>
								<th data-field="student.name">学生</th>
								<th data-field="weChatInfo.name">商家</th>
								<th data-field="payTime">付款时间</th>
								<th data-field="refundTime">退款时间</th>
								<th data-field="baseInfo.name">类型</th>
								
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
	var DataTable2=$("#wechatlist");
	var myPay=$("#mypayTable");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Activity/getActivityList?sina=<%= SmBaseUtil.Random() %>&WeChatID="+$("#WeChatID").val(),
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入活动标题";},
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
				//	var e = o("#WeChatGroupEventsResult");
				DataTable.on("dbl-click-row.bs.table",
					function(e, t, o) {
								if(CheckhasChecked(DataTable)==true){
								var url='<%=path%>/Activity/addActivity?pid=';
								viewOrModifyByPKID(DataTable,url);
							}
					}).on("search.bs.table", function(e, t, o) {
						getQueryList(t);
					});
			}();

		}(document, window, jQuery);

		function modifyProduct() {
			if(CheckhasChecked(DataTable)==true){
				var url='${path}/Activity/addActivity?pid=';
				viewOrModifyByPKID(DataTable,url);
			}
		}
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "${path}/Activity/deleteActivity",
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
		//确认参赛 flag 0表示取消参赛 1表示确认参赛, type 1表示报名列表 2表示参赛列表
		function confirmApply(flag,type) {
			if(CheckhasChecked(DataTable2)==true){
				$("#type").val(type)
				var wids = getRecordPKIDs(DataTable2);
				var aid = getRecordPKIDs(DataTable);
				var stuids=getRecordStuIDs(DataTable2);
				var title=DataTable.bootstrapTable("getSelections")[0].title;
				$("#referWechat").attr("disabled","disabled")
				$("#referWechat").text("处理中...");
				if (wids != '') {
					$.ajax({
						url : "${path}/Activity/confirmApply",
						data : {
							'pid'   : wids,
							'stuids': stuids,
							'aid'   : aid,
							'state' : flag,
							'title' : title
						},
						success : function() {
							$("#referWechat").removeAttr("disabled")
							if(type==2 || type=="2"){
								$("#referWechat").text("取消参赛");
							}else{
								$("#referWechat").text("确认参赛");
							}
							ViewSuccess($("#ErrorMessageStudent"));
							var aid = getRecordPKIDs(DataTable);
							DataTable2.bootstrapTable("refresh", {
								url : "<%=path%>/Activity/getActivityPartList?type="+ type +"&aid="+aid
							});
						}
					});
				} 
			}
		}
		/*获取记录ID*/
		function getRecordStuIDs(obj){
			var wid = obj.bootstrapTable("getSelections");
			var wids = "";

			for ( var idobj = 0; idobj < wid.length; idobj++) {
				wids += "," + (wid[idobj].student==null?wid[idobj].student.pkid:wid[idobj].student.pkid);
			}
			if(wids!=""){
				wids=wids.substr(1);
			}
			return wids;
		} 
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			var params=MakeQueryParam($(".example-wrap"));
			$("#ApplyID").val("");
			DataTable.bootstrapTable("refresh", {
				url : "${path}/Activity/getActivityList?sina=<%= SmBaseUtil.Random() %>&searchText="+ searchText +"&&"+params
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				PC : "PC"
			};

		}
		function setTop(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Activity/setTopActivity",
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
		function cancelTop(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Activity/cancelTopActivity",
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
		function activityPartList(type){
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$('#myModal').modal('show');
					if(type==2 || type=="2"){
						$("#referWechat").text("取消参赛");
						$("#referWechat").click(function(){confirmApply(0,2);});
					}else{
						$("#referWechat").text("确认参赛");
						$("#referWechat").click(function(){confirmApply(1,1);});
					}
					
					DataTable2.bootstrapTable({
						url : "<%=path%>/Activity/getActivityPartList?sina=<%= SmBaseUtil.Random() %>&type="+ type +"&aid="+wids,
						showExport: true,                     //是否显示导出
						exportDataType: "basic",              //basic', 'all', 'selected'.
						pageList: [10, 25, 50, 100,1000], 
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
						toolbar : "#StudentListEventsToolbar",
						icons : geticons
					});
				
				} 
			}
			
		}
		
		
	function myPayRecord(){
		if(CheckhasChecked(DataTable)==true){
			var wids = getRecordPKIDs(DataTable);
			if (wids != '') {
				$('#mypay').modal('show');
				myPay.bootstrapTable({
					url : "<%=path%>/Activity/getMoneyRecordList?sina=<%= SmBaseUtil.Random() %>&aid="+wids+"&type="+$("#Status").val(),
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					striped : !0, //是否显示行间隔色
					rowStyle : dispMypayErrorRow,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					iconSize : "outline",
					toolbar : "#mypayToolbar",
					icons : geticons
				});
			} 
		}
	}
	
	function dispMypayErrorRow(row, index) {
		//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
		var strclass = "";
		if(row.type == 12){
			strclass = 'danger';//还有一个active
		}
		return {
			classes : strclass
		}
	}
	function getMypayQueryList(){
		var wids = getRecordPKIDs(DataTable);
		myPay.bootstrapTable("refresh", {
			url : "<%=path%>/Activity/getMoneyRecordList?sina=<%= SmBaseUtil.Random() %>&aid="+wids+"&type="+$("#Status").val(),
		});
	}
	
	
	function refund(){//退款
		$("#reFund").html("退款中");
		var aid = getRecordPKIDs(DataTable);//活动ID
		var sid=getRecordStuIDs(DataTable2);
		var applyid = getRecordPKIDs(DataTable2);//活动ID
		var paytype=DataTable.bootstrapTable("getSelections")[0].payType;
		$.ajax({
			url : "<%=path%>/Activity/refundWeChat",
			data : {
				'aid' : aid,
				'sid':sid,
				'applyid':applyid,
				'paytype':paytype
			},
			success : function(obj) {
				if (obj.Status) {
					$("#reFund").html("退款成功");
					ViewSuccess($("#ErrorMessageStudent"));
					DataTable2.bootstrapTable("refresh", {
						url : "<%=path%>/Activity/getActivityPartList?sina=<%= SmBaseUtil.Random() %>&type="+ $("#type").val() +"&aid="+aid
					});
					
				}else{
					$("#reFund").html("退款失败");
					ViewSuccess($("#ErrorMessageStudent").html(obj.Message));
				}	
			}
		});
		
	}
	</script>
	<div id="loading"></div>
</body>
</html>