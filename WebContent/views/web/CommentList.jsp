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
		<input type="hidden" name="NewsID" value="${NewsID}" id="NewsID" />
		<input type="hidden" name="shang" id="shang" value="${shang}" />
		<input type="hidden" name="key" id="key" value="${key}" />
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>新闻评价</strong></li>
				</ol>
			</div>
			<br/>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						
						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
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
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="pkid">评论编号</th>
										<th data-field="newTitle">新闻名字</th>
										<th data-field="notice.author">新闻作者</th>
										<th data-field="userName">评价学生</th>
										<th data-field="content">评价内容</th>
										<th data-field="notice.student.school">学生学校</th>
										<th data-field="notice.student.phone">学生电话</th>
										<th data-field="createTime">评价时间</th>
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
	initialRegionQueryInfo($(".panel-body"),'query',1);
	var DataTable=$("#exampleTableEvents");
	var DataTable2=$("#wechatlist");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					
					url : "<%=path%>/Comment/getCommentPCList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入新闻标题或作者"},
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
									preview();
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								})
				DataTable.on("click-row.bs.table",
							function(e, t, o) {
							}).on("search.bs.table", function(e, t, o) {
								getQueryList(t);
							})
			}()
		}(document, window, jQuery);

		function modifyProduct() {
			if(CheckhasChecked(DataTable)==true){
				var url='<%=path%>/Notices/addNews?type=2&&pid=';
				viewOrModifyByPKID(DataTable,url);
			}

		}
		function preview2(){
			 if(CheckhasChecked(DataTable)==true){ 
				var t=DataTable.bootstrapTable("getSelections")[0];
				console.log(t["pkid"]);
				$("#nsid").html(t["pkid"]);
				$("#myModal").modal("show");
				$("#titlens").html("《"+t['title']+"》");
				DataTable2.bootstrapTable("refresh", {
					url : "<%=path%>/Comment/getCommentPCList?sina=<%= SmBaseUtil.Random() %>&nid="+t["pkid"]
				});
		 	} 
		}
		/*获取记录ID*/
		function getRecordTypes(obj){
			var wid = obj.bootstrapTable("getSelections");
			var wids = "";

			for ( var idobj = 0; idobj < wid.length; idobj++) {
				wids += "," + wid[idobj].type;
			}
			if(wids!=""){
				wids=wids.substr(1);
			}
			return wids;
		}
		function deleteE(){
			if(CheckhasChecked(DataTable2)==true){ 
				var wids = getRecordPKIDs(DataTable2);
				var types = getRecordTypes(DataTable2);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Comment/deleteComment",
						data : {
							'pid' : wids,
							'type':types
						},
						success : function(obj) {
							if(obj.result>0){
								//ViewSuccess();
								DataTable2.bootstrapTable("refresh", {
									url : "<%=path%>/Comment/getCommentPCList?sina=<%= SmBaseUtil.Random() %>&nid="+$("#nsid").html()
								});
							}
						}
					});
				} 
			}
		}
		function deleteWeChat() {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				var types = getRecordTypes(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Comment/deleteComment",
						data : {
							'pid' : wids,
							'type':types
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
			var NewsID="";
			if($("#NewsID").val()!=""){
				NewsID=$("#NewsID").val();
				$("#NewsID").val("");
			}
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Comment/getCommentPCList?sina=<%= SmBaseUtil.Random() %>"
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		
	</script>
</body>
</html>