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
					<li><strong>用户反馈</strong></li>
				</ol>
			</div>
			<br/>
		</div>

		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
				
						<div  id="ErrorMessage"
							role="alert"></div>
	
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
						
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">反馈编号</th>
									<th data-field="name">反馈学生</th>
									<th data-field="student.school">学生学校</th>
									<th data-field="student.phone">学生手机</th>
									<th data-field="content">反馈内容</th>
									<th data-field="createTime">反馈时间</th>
									
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
					url : "<%=path%>/Feedback/getFeedbackList?sina=<%= SmBaseUtil.Random() %>",
					//search : !0,
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
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								});
			}();

		}(document, window, jQuery);
	

//		function getQueryList(searchText){
//			if(searchText==null || searchText==undefined){
//				searchText="";
//			}
//			var params=MakeQueryParam($(".panel"));
//			DataTable.bootstrapTable("refresh", {
//				url : "<%=path%>/ApplyList/getApplyListList?sina=<%= SmBaseUtil.Random() %>&UserName="+searchText+"&"+params
//			});
//		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				//AreaID : $("#AreaID").val()
			};

		}
		
		

	</script>
</body>

</html>