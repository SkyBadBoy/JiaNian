<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.*"%>
<%@	page import="wtb.smUtil.SmBaseGlobal"%>
<%@	page import="wtb.core.model.Users"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//response.setContentType("text/xml;charset=UTF-8");
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />
<style type="text/css">
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
	<input type="hidden" id="dizhi" value="<%=basePath%>"></input>
	<div class="col-lg-12">
		<br />
		<ol class="breadcrumb">
			<li><a href="/WeNewsAgency/home" target="_self">主页</a></li>
			<c:if test="${flag eq 0 }">
				<li><strong>公众号管理</strong></li>
			</c:if>
			<c:if test="${flag eq 1 }">
				<li><strong>微信配置管理</strong></li>
			</c:if>
		</ol>
	</div>
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br /> <br />
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">

						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button"  onclick="addWeChatInfoView(0)" 
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="新增"
									aria-hidden="true"></i> 新增
							</button>
							<c:if test="${flag eq 1 }">
								<button type="button"  onclick="addWeChatInfoView(1)" 
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-pencil" title="修改"
										aria-hidden="true"></i> 修改
								</button>
							</c:if>
							<c:if test="${flag eq 0 }">
								<button type="button" onclick="WeChatInfoDetail()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-eye-open" title="查看"
										aria-hidden="true"></i> 查看
								</button>
							</c:if>
							<button type="button" onclick="deleteWeChat(88)"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"
									aria-hidden="true"></i> 删除
							</button>
							
							<c:if test="${flag eq 1 }">
								<label style="margin-top: 9px;color: #ff000a;">当前默认商户将已高亮形式显示</label>
							</c:if>
							
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="name">名字</th>
									<th data-field="wid">微信号</th>
									<th data-field="appID">AppID</th>
									<th data-field="appsecret">Appsecret</th>
									<th data-field="areaInfo.Name">地区</th>
									
									<c:if test="${flag eq 1 }">
										<th data-field="mchID">商户号</th>
										<th data-field="apiKey">ApiKey</th>
										<th data-field="weight">Weight</th>
									</c:if>
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
						<h4 class="modal-title">公众号编辑</h4>
				
				</div>
				<div class="modal-body">
					<div class="row">
						<form id="contentForm" action="#">
							<input type="hidden" class="form-control" id="id" name="id" />
							请输入名称:<input type="text" class="form-control" placeholder="请输入名称" id="name" name="name" /><br>
							请输入微信号ID:<input type="text" class="form-control" placeholder="请输入微信号ID" id="WID" name="WID" /><br> 
							请输入appID:<input type="text" class="form-control" placeholder="请输入appID" id="appID" name="appID" /> <br /> 
							请输入appsecret:<input type="text" class="form-control" placeholder="请输入appsecret" id="appsecret" name="appsecret" /><br>
							
							<c:if test="${flag eq 1 }">
							请输入商户号:<input type="text" class="form-control" placeholder="请输入商户号" id="mch" name="mch" /><br>
							请输入ApiKey:<input type="text" class="form-control" placeholder="请输入ApiKey" id="apikey" name="apikey" /><br>
							请输入权值:<input type="text" class="form-control" placeholder="请输入权值" id="Weight" name="Weight" /><br>
								
							请设置是否默认：<select class="form-control m-b"  iscon="true" id="type" name="type">
										<option value="0">不设为默认</option>
										<option value="1">设为默认</option>
									</select>
							</c:if>
						</form>
					</div>
					<br />
					<button id="addWeChatInfo" class="btn btn-warning" style="margin-top: -27px;">确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>
	<script type="text/javascript">
	var flag='${flag}';
	if (flag==1||flag=="1") {
	function dispErrorRow(row, index) {
		//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
		var strclass = "";
			if (row.isPay == "1") {
				strclass = 'success';//还有一个active
			} else {
				return {};
			}
			return {
				classes : strclass
			}
		}
	}
		var DataTable=$("#exampleTableEvents");
 
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=basePath%>WeChatCustom/getWeChatInfoHomeList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入公众号名称"},
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle : dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
	
				});
				//	var e = o("#WeChatGroupEventsResult");
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									WeChatInfoDetail();
								});
				DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList(t);
				});
				
			}()
		}(document, window, jQuery);

		function deleteWeChat() {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=basePath%>WeChatCustom/deleteWeChatInfo",
						data : {
							'ID' : wids
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
					 url : "<%=basePath%>WeChatCustom/getWeChatInfoHomeList?sina=<%= SmBaseUtil.Random() %>&check="+encodeURI(encodeURI(searchText))
				});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		
		$("#contentForm").validate({
			rules : {
				name:{
					required : !0,
					maxlength : 13
				},
				WID:{
					required : !0
				},
				appID : {
					required : !0
	
				},
				appsecret : {
					required : !0
				}
			},
			messages : {
				name:{
					required : "公众号名字不能为空",
					maxlength : "公众号名字不能超过13个字符"
				},
				WID:{
					required : "微信号不能为空"
				},
				appID : {
					required : "公众号appID不能为空"
				},
				appsecret : {
					required : "公众号appsecret不能为空"
				}
			}
		});
		$("#addWeChatInfo").click(function(){
			if(!$("#contentForm").validate().form()){
				return 
			}
				$.ajax({
					url : "<%=basePath%>WeChatCustom/addWeChatInfo",
					data : {
						'name':encodeURI($("#name").val()),
						'wid':$("#WID").val(),
						'appID':$("#appID").val(),
						'appsecret':$("#appsecret").val(),
						'mch':$("#mch").val(),
						'apikey':$("#apikey").val(),
						'Weight':$("#Weight").val(),
						'type':$("#type").val(),
						'id':$("#id").val()
					},
					success : function(obj) {
						if(obj.Status==-1){
							ViewWarningError(obj.ErrorMsg);
							getQueryList();
						}else if(obj.Status==1){
							ViewSuccess();
							getQueryList();
						}
					}
				});
				
				$('#myModal').modal('hide');
		
		});
		//0:新增 1修改
		function addWeChatInfoView(flag){
			if (flag==1) {
				if(CheckhasChecked(DataTable)==true){
					var t=$("#exampleTableEvents").bootstrapTable("getSelections")[0];
					var id=t['pkid'];
					var name=t['name'];
					var wid=t['wid'];
					console.log(wid);
					var AppID=t['appID'];
					var Appsecret=t['appsecret'];
					var MchID=t['mchID'];
					var ApiKey=t['apiKey'];
					var Weight=t['weight'];
					var IsPay=t['isPay'];
				
					$('#myModal').modal('show');
					
					$("#name").val(name);
					$("#WID").val(wid);
					$("#appID").val(AppID);
					$("#appsecret").val(Appsecret);
					$("#mch").val(MchID);
					$("#apikey").val(ApiKey);
					$("#mch").val(MchID);
					$("#Weight").val(Weight);
					$("#type").val(IsPay);
					$("#id").val(id);
				}
			}else{
				$("#id").val(0);
				$('#myModal').modal('show');
			}
			
		}
		
		function WeChatInfoDetail()
		{	if(CheckhasChecked(DataTable)==true){
			var t=$("#exampleTableEvents").bootstrapTable("getSelections")[0];
			var name=t['name'];
			var areaid=t['areaID'];
			var areaname=t['areaInfo'].name;
			if(areaid==null || areaid=='null'){
				areaid=""	;
			}
			if(areaname==null || areaname=='null'){
				areaname=""	;
			}
			if(CheckhasChecked(DataTable)==true){
				var url='<%=path%>/WeChatCustom/WeChatInfoDetail?id='+t['pkid']+'&name='+encodeURI(encodeURI(t['name']))+'&appid='+t['appID']+'&appsecret='+t['appsecret']+'&area='+encodeURI(encodeURI(areaname))+'&areaid='+areaid+'&wid='+t['wid'];
				console.log(url);
				window.location.href =url;
			}
		}
		
		}
		

	
	</script>
</body>
</html>