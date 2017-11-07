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
.server{
	width: 198px;
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
					<li><strong>套餐管理</strong></li>
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
									id="Status" name="Status">
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">正常</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
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
								onclick="ModifyApply(<%=SmBaseGlobal.ApplyType.LoadDispose.getid() %>)"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" title="编辑" aria-hidden="true"></i>
								编辑
							</button>
						<%-- 	<button type="button" onclick="ModifyApply(<%=SmBaseGlobal.ApplyType.NoApply.getid() %>)"   class="btn btn-outline btn-default">
								<i class="fa fa-close"  title="已确认(未报名)"  aria-hidden="true"></i>
								删除
							</button> --%>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th   data-field="state" data-radio="true"></th>
									<th   data-field="comboTitle" data-width="100px" >标题</th>
									<th   data-field="comboPrice" data-width="50px">价格</th>
									<th   data-field="comboCreatetime">创建时间</th>
									<th   data-field="comboParameter1Str"  data-width="90px">精准的服务</th>
									<th   data-field="comboParameter2Str">各科现场免费补考一次</th>
									<th   data-field="comboParameter3Str">体检物料.学车用具</th>
									<th   data-field="comboParameter4Str">优先安排学车</th>
									<th   data-field="comboParameter5Str">一对一教学.配学车顾问一名</th>
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
					<h4 class="modal-title">修改套餐</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageResult" role="alert"></div>
				<form id="contentForm" action="#">
				
					<div class="row">
						<input type="text" class="form-control" placeholder="请输入标题" name="title"   id="title"  />
					</div>
					<br>
					<div class="row">
						<input type="text" class="form-control" placeholder="请输入价格" name="price"  id="price" />
					</div>
					<br>
					<div class="row">
						选择服务
						
						<div style="margin-top: 20px;text-align: center;    display: flex;justify-content:space-around; ">
						
							<a class="btn btn-info btn-rounded server" id="service1" onclick="service(1,this)" data-select='1'>精准的服务</a>
							
							<a class="btn btn-info btn-rounded server" id="service2" onclick="service(2,this)"  data-select='1'>各科现场免费补考一次</a>
							
							<a class="btn btn-info btn-rounded server" id="service3" onclick="service(4,this)" data-select='1' >体检物料.学车用具</a>
						</div>
						<div style="margin-top: 20px;text-align: center; display: flex;justify-content:space-around; ">
							
							<a class="btn btn-info btn-rounded server" id="service4" onclick="service(5,this)"data-select='1'>优先安排学车</a>
							
							<a class="btn btn-info btn-rounded server" id="service5" onclick="service(6,this)"data-select='1'>一对一教学.配学车顾问一名</a>
					
							<a class="btn btn-info btn-rounded server" style="visibility:hidden;"  onclick="service(4,this)" data-select='1' >体检物料.学车用具</a>
						</div>
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
	/*background-color: #c2c2c2;  */
	
	function service(flag,that){
		if (that.attributes['data-select'].value==1) {
			that.attributes['data-select'].value=0;
			that.style.backgroundColor= "#c2c2c2";
		}else if(that.attributes['data-select'].value==0){
			that.attributes['data-select'].value=1;
			that.style.backgroundColor= "#23c6c8";
		}
		
		
	}
	
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!

			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Combo/getComboList?sina=<%= SmBaseUtil.Random() %>",
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					search : 0,
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
				title : {
					required : !0,
					maxlength : 20
				},
				price : {
					required : !0,
					maxlength : 20
				}
			},
			messages : {
				title : {
					required : "标题不能为空",
					maxlength : "标题不能超过20个字符"
				},
				price : {
					required : "价格不能为空",
					maxlength : "价格不能超过20个字符"
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
				$("#title").val(DataTableObj[0].comboTitle);
				$("#price").val(DataTableObj[0].comboPrice);
				if (DataTableObj[0].comboParameter1==1) {
					$("#service1").attr('data-select',1);
					$("#service1").css('backgroundColor','#23c6c8');
				}else{
					$("#service1").attr('data-select',0);
					$("#service1").css('backgroundColor','#c2c2c2');
				}
				if (DataTableObj[0].comboParameter2==1) {
					$("#service2").attr('data-select',1);
					$("#service2").css('backgroundColor','#23c6c8');
				}else{
					$("#service2").attr('data-select',0);
					$("#service2").css('backgroundColor','#c2c2c2');
				}
				
				if (DataTableObj[0].comboParameter3==1) {
					$("#service3").attr('data-select',1);
					$("#service3").css('backgroundColor','#23c6c8');
				}else{
					$("#service3").attr('data-select',0);
					$("#service3").css('backgroundColor','#c2c2c2');
				}
				
				if (DataTableObj[0].comboParameter4==1) {
					$("#service4").attr('data-select',1);
					$("#service4").css('backgroundColor','#23c6c8');
				}else{
					$("#service4").attr('data-select',0);
					$("#service4").css('backgroundColor','#c2c2c2');
				}
				
				if (DataTableObj[0].comboParameter5==1) {
					$("#service5").attr('data-select',1);
					$("#service5").css('backgroundColor','#23c6c8');
				}else{
					$("#service5").attr('data-select',0);
					$("#service5").css('backgroundColor','#c2c2c2');
				}
			
				
				
				$(".note-editable").html(DataTableObj[0].feedback)
				$('#myModal').modal('show');
				$("#referWechat").click(function(){
					if(!$("#contentForm").validate().form()){
						return 
					}
					var wids = DataTableObj[0].pkid;
					console.log(wids)
					if (wids != '') {
				 		$.ajax({
							url : "<%=path%>/Combo/ModifyCombo",
							type:'POST',
							data : {
								'pid' : wids,
								'title':$("#title").val(),
								'price':$("#price").val(),
								'comboParameter1':$("#service1").attr('data-select'),
								'comboParameter2':$("#service2").attr('data-select'),
								'comboParameter3':$("#service3").attr('data-select'),
								'comboParameter4':$("#service4").attr('data-select'),
								'comboParameter5':$("#service5").attr('data-select')
							},
							success : function(obj) {
								if (obj.status) {
									ViewSuccess();
									getQueryList();
									$('#myModal').modal('hide');
								}else{
									swal("",obj.message, "warning", {
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
				url : "<%=path%>/Combo/getComboList?sina=<%= SmBaseUtil.Random() %>&UserName="+searchText
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