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
<style type="text/css">
#Layer1 {
    width: 450px;
    margin:-120px;
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
    background-color:#000;
    position:absolute;
    z-index:49;
    display:none;
    width:100%;
    height:100%;
    opacity:0.6;
    filter: alpha(opacity=60);
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
    margin-top:45px;
    margin-left: -230px;
    z-index: 9997;
    width: 402px;
    height: 603px;
    background: url("<%=basePath%>img/phone2.png") 0 0 no-repeat;
}


#liulan{
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

<div class="container-fluid">
<input type="hidden" name="NewsID" value="${NewsID}" id="NewsID"  />
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>新闻同步管理</strong></li>
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
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" onclick="deleteStudentsLog()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"  aria-hidden="true"></i>
								删除
							</button>
							<button type="button" onclick="urlStudentsLog()" class="btn btn-outline btn-default">
								<i  class="glyphicon glyphicon-play" title="URL"  aria-hidden="true"></i>
								同步
							</button>
						</div>
							<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-checkbox="true" data-width="5%" data-field="state"></th>
										<th data-width="20%" data-field="news.title">新闻标题</th> 
										<th data-width="20%" data-field="students.name">学生名字</th>
										<th data-width="20%" data-field="createTime">时间</th>
										<th data-width="20%" data-field="request">返回结果</th>
										<th data-width="20%" data-field="students.area.Name">学生地区</th>
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
				
					url : "<%=path%>/Students/getStudentsLogList?sina=<%= SmBaseUtil.Random() %>",
					search : !0, 
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "查找学生";},
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
				
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
								var t=DataTable.bootstrapTable("getSelections")[0];
									if(CheckhasChecked(DataTable)==true)
									{
					/* 					swal({
												title:"学生日志管理"+t['students.name'],
												text:"学生日志ID:"+t['pkid']+";\n\n日志创建时间:"+t['createTime']+";\n\n"+
													 "新闻ID:"+t['newID']+";\n\n学生ID:"+t['studentsID']+";\n\n"+
													 "链接地址:"+t['url']+";\n\n链接返回值:"+t['request']+"。"
											}); */
										//alert(t["pkid"]);
									}
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								})
			}()
		}(document, window, jQuery);
		
		function urlStudentsLog()
		{
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true){
				$.ajax({
					url : "<%=path%>/Notices/urlNotices?url="+t['url']+"&&id="+t['pkid'],
	
					success : function() {
						ViewSuccess();
						getQueryList();
					} 
				});
				} 
				
		}
		
		function deleteStudentsLog() {
		if(CheckhasChecked(DataTable)==true){
					var wids = getRecordPKIDs(DataTable);
					$.ajax({
						url : "<%=path%>/Students/deleteStudentsLog",
	 					data : {
							'logid' : wids
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						} 
					});
					}
		}
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			DataTable.bootstrapTable("refresh", {
				
				url : "<%=path%>/Students/getStudentsLogList?sina=<%= SmBaseUtil.Random() %>&SName="+encodeURI(encodeURI(searchText)),
				
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