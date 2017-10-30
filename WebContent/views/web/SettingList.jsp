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

	boolean isApply=false;
	String Status=request.getParameter("Status");
	if(Status!=null && !Status.isEmpty() ){
		isApply=(Integer.parseInt(Status)==SmBaseGlobal.CheckStatus.Apply.getid());
	}
	request.setAttribute("isApply", isApply);
	request.setAttribute("Level", user.getLevel());
	
    String rootPath=getClass().getResource("/").getFile().toString();
    rootPath=rootPath.replace("classes/", "");
    rootPath=rootPath.replace("WEB-INF/", "");
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
		<input type="hidden" name="parentID" value="${parentID}" id="parentID" />
		<!-- Page Heading -->
		 <c:if test="${parentID==0}">
				<div class="row">
					<br />
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li><a href="<%=path%>/home" target="_self">主页</a></li>
							<li><strong>基础设置</strong></li>
						</ol>
					</div>
					<br/>
				</div>
		</c:if>
		<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						
						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button"
									onclick="modify(0)"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-plus" title="新增"
										aria-hidden="true"></i> 新增
								</button>
								<button type="button" title="修改" onclick="modify(1)" class="btn btn-outline btn-default">
									<i  title="修改"  aria-hidden="true"></i>
									修改
								</button>
								 <c:if test="${parentID==0}">
									<button type="button" title="修改选项" onclick="studentsNewsView()" class="btn btn-outline btn-default">
										<i  title="修改选项"  aria-hidden="true"></i>
										修改选项
									</button>
								</c:if>
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
										<th data-field="pkid">编号</th>
										<th data-field="name">名称</th>
										<th data-field="memo">备注</th>
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
					<h4 class="modal-title" id="titles">编辑信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" name="pkid"  id="pkid" />
						<input type="text" class="form-control" placeholder="名称" id="name"  /><br>
						<input type="text" class="form-control" placeholder="备注" id="memo"  />
					</div>
					<br />
					<button id="addIntegration" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="myModalActivity" style="overflow: auto;margin-left: -500px" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:1100px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >修改选项</h4>
				</div>
				<div class="modal-body">
					<iframe id="iframe_wemoney" style="border:0px" src="#" width="1024" height="500"> </iframe> 
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
	var DataTable2=$("#wechatlist");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					
					url : "<%=path%>/Setting/getSettingList?parentID=${parentID}&sina=<%= SmBaseUtil.Random() %>",
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

		function deleteWeChat() {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Setting/deleteSetting",
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
		
		function getQueryList(){
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Setting/getSettingList?parentID=${parentID}&sina="+Math.random()
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				parentID : ${parentID},
				sina:Math.random()
			};

		}
		function studentsNewsView()
		{
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				$("#iframe_wemoney").attr("src",'<%=path%>/Setting/SettingList?sina=<%= SmBaseUtil.Random() %>&parentID='+t['pkid']);
				$("#myModalActivity").modal("show");
				
			}
		}
		
		function modify(type)
		{
			if(type==1){
				var t=DataTable.bootstrapTable("getSelections")[0];
				if(CheckhasChecked(DataTable)==true)
				{
					$("#name").val(t["name"]);
					$("#memo").val(t["memo"]);
					$("#pkid").val(t["pkid"]);
					$("#myModal").modal("show");
					
				}
			}else{
				$("#name").val("");
				$("#pkid").val("0");
				$("#memo").val("");
				$("#myModal").modal("show");
			}
			
		}
		
		$("#addIntegration").click(function(){
			var id=$("#pkid").val();
			var title=$("#name").val();
			var memo=$("#memo").val();
			 $.ajax({
					url : "<%=path%>/Setting/addSetting",
					data : {
						'pid' : id,
						'name' : encodeURI(title),
						'memo' : encodeURI(memo),
						'parentID' : $("#parentID").val(),
						'sina':'<%= SmBaseUtil.Random() %>'
					},
					success : function(obj) {
						$("#myModal").modal("hide");
						if (obj.status) {
							ViewSuccess();
							getQueryList();
						}else{
							ViewWarningError($("#ErrorMessage"),"处理失败!");
						}
						
					}
				});

		})
	</script>
</body>
</html>