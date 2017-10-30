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
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />
<style type="text/css">
#Layer1 {
	width: 450px;
	margin: -120px;
	z-index: 50;
	display: none;
	position: relative;
}

#Layer1 #win_top {
	height: 30px;
	width: 450px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #999;
	line-height: 30px;
	color: #666;
	font-family: "微软雅黑", Verdana, sans-serif, "宋体";
	font-weight: bold;
	text-indent: 1em;
}

#Layer1 #win_top a {
	float: right;
	margin-right: 5px;
}

#shade {
	background-color: #000;
	position: absolute;
	z-index: 49;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.6;
	filter: alpha(opacity = 60);
	-moz-opacity: 0.6;
	margin: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
}

#Layer1 .content {
	margin-top: 5px;
	margin-right: 30px;
	margin-left: 30px;
}

#iphone {
	position: fixed;
	left: 50%;
	margin-top: 45px;
	margin-left: -230px;
	z-index: 9997;
	width: 402px;
	height: 603px;
	background: url("<%=basePath%>img/phone2.png") 0 0 no-repeat;
}

#liulan {
	height: 415px;
	width: 226px;
	top: 70px;
	left: 157px;
	display: block;
	position: relative;
	color: #020101;
	z-index: 9999;
	margin-top: 10px;
	background-color: #000;
}

.view_item {
	background-color: #f0eff4;
	border-bottom: 1px solid #dfdfe4;
	white-space: nowrap;
	cursor: pointer;
	min-width: 120px;
}

.view_list {
	line-height: 40px;
	border-radius: 10px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	text-align: center;
	overflow: hidden;
	position: absolute;
	right: 75%;
	top: 200px;
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
	<input type="hidden" name="isOpen" id="isOpen" value="0" />
	<label style="display: none;" id="nsid"></label>
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>更新列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"  onclick="checkCollapse2($(this))"
								aria-collapse="false" class="collapsed">查询条件(点击展开)</a>
								
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
						<div class="panel-body">
							<div class="col-md-3">
								<label>设备</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="ClientTypeQuery" name="ClientTypeQuery">
									<option></option>
									<option value="<%= SmBaseGlobal.ClientType.Android.getid() %>">安卓</option>
									<option value="<%= SmBaseGlobal.ClientType.IOS.getid() %>">苹果</option>
								</select>
							</div>
						</div>
					</div>
					</div>	
						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
								<button type="button" onclick="addProduct()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-trash" title="新增"
										aria-hidden="true"></i> 新增
								</button>
								<button type="button" onclick="modifyProduct()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-trash" title="修改"
										aria-hidden="true"></i> 修改
								</button>
								<button type="button" onclick="deleteWeChat()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-trash" title="删除"
										aria-hidden="true"></i> 删除
								</button>
						</div>
							<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="state" data-radio="true"></th>
										<th data-field="pkid">评论编号</th>
										<th data-field="content">更新内容</th>
										<th data-field="number">发布版本</th>
										<th data-field="build">构建版本号</th>
										<th data-field="updateTypeMemo">更新类型</th>
										<th data-field="clientTypeMemo">客户端类型</th>
										<th data-field="createTime">创建时间</th>
										<th data-field="releaseTime">发布时间</th>
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
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titles">编辑更新信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label style="display: none;" id="pkid"></label>
						<div class="row">
							<label>更新类型</label>
							<select class="form-control m-b" id="UpdateType" name="UpdateType">
								<option value="<%= SmBaseGlobal.UpdateType.NoUpdate.getid() %>">不提醒更新</option>
								<option value="<%= SmBaseGlobal.UpdateType.Update.getid() %>">提醒更新</option>
								<option value="<%= SmBaseGlobal.UpdateType.ForceUpdate.getid() %>">强制更新</option>
							</select>
						</div>
						<div class="row">
							<label>更新设备</label>
							<select class="form-control m-b" id="ClientType" name="ClientType">
								<option value="<%= SmBaseGlobal.ClientType.Android.getid() %>">安卓</option>
								<option value="<%= SmBaseGlobal.ClientType.IOS.getid() %>">苹果</option>
							</select>
						</div>
						<div class="row">
							<label>下载链接</label>
							<input type="text" class="form-control" placeholder="下载链接" id="FilePath"  />
						</div>
						<div class="row">
							<label>版本号</label>
							<input type="text" class="form-control" placeholder="版本号" id="Number"  />
						</div>
						<div class="row">
						<label>更新说明</label>
							<textarea id="Content" class="form-control"  placeholder="更新内容" rows="3" cols=""></textarea>
						</div>
						
					</div>
					<br />
					<button  onclick="modifySendInfo();" class="btn btn-warning">保存</button>
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
					
					url : "<%=path%>/Version/getVersionList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
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
			}()
		}(document, window, jQuery);

		function modifyProduct() {
			if(CheckhasChecked(DataTable)==true){
				var t=DataTable.bootstrapTable("getSelections")[0];
				$("#Number").val(t['number']);
				$("#Content").val(t['content']);
				$("#UpdateType").val(t['updateType']);
				$("#ClientType").val(t['clientType']);
				$("#FilePath").val(t['filePath']);
				$("#pkid").text(t['pkid']);
				$("#myModal").modal("show");
			}

		}
		function addProduct() {
			$("#Number").val("");
			$("#Content").val("");
			$("#pkid").text("");
			$("#FilePath").val("");
			$("#myModal").modal("show");
		}
		function modifySendInfo(){
			$.ajax({
				url : "<%=path%>/Version/addVersion?sina=<%= SmBaseUtil.Random() %>",
					data : {
					'pid':$("#pkid").text(),	
					'clientType':$("#ClientType").val(),
					'content':$("#Content").val(),
					'updateType':$("#UpdateType").val(),
					'number':$("#Number").val(),
					'filePath':$("#FilePath").val()
				},
				success : function(obj) {
					if(obj.status){
						ViewSuccess();
						getQueryList();
						$("#myModal").modal("hide");
					}else{
						ViewWarningError(obj.Message);
					}
				} 
			});
		}
		function deleteWeChat() {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Version/deleteVersion",
						data : {
							'pid' : wids
						},
						success : function(obj) {
							if(obj.result>0){
								ViewSuccess();
								getQueryList();
							}
						}
					});
				} 
			}
		}
		
		function getQueryList(searchText){
			var params=MakeQueryParam($(".panel"));
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Version/getVersionList?sina=<%= SmBaseUtil.Random() %>"
			});
		}
		function queryParams(params) {
			var ClientType =$("#ClientTypeQuery").val();
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				Type :ClientType
			};

		}
		
	</script>
</body>
</html>