<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
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

		
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<input type="hidden" id="AreaID" name="AreaID" value="${AreaID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">						
							<button type="button" title="启用" onclick="upLevel()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-play" title="启用"  aria-hidden="true"></i>
								启用
							</button>
							<button type="button" title="停用" onclick="upLevel()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="停用"  aria-hidden="true"></i>
								停用
							</button>
							
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight-250 %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="imageUrl">头像</th>
									<th data-field="name">名字</th>
									<th data-field="isSex">性别</th>
									<th data-field="age">出生年月</th>
									<th data-field="level">级别</th>
									<th data-field="grade">班级</th>
									<th data-field="Habit">爱好</th>
									<th data-field="phone">手机</th>
									<th data-field="parentName">家长姓名</th>
									<th data-field="email">邮箱</th>
									<th data-field="integration">积分</th>
									<th data-field="noticeCount">发稿数</th>
									<th data-field="school">学校</th> 
									<th data-field="createTime">创建时间</th> 
									<th data-field="chiefName">职位</th> 
									<th data-field="officialName">正式社员</th> 
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- End Example Events -->
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
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!

			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Students/getStudentsList",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入学生姓名或手机号"},
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
						}).on("search.bs.table", function(e, t, o) {
							getQueryList(t);
						})
			}()
		}(document, window, jQuery);
		function queryParams(params) {
			var temp=MakeQueryParamJson($(".panel"));
			temp.pageSize=params.limit;
			temp.pageNumber=params.offset;
			temp.check=$(".search input").val();
			temp.sina=Math.random();
			temp.isStu=1;
			temp.isOpen=1;
			temp.AreaID=$("#AreaID").val();
			return temp;


		}
		function ChangeStudentsState(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Students/deletePicture",
						data : {
							'sid' : wids,
							'Status' : flag
						},
						success : function(result) {
							if(result.Status==1){
								ViewSuccess();
								getQueryList();
							}else{
								if(result.ErrorMessage!=""){
									ViewWarningError(result.ErrorMessage);
								}
							}
						}
					});
				} 
			}

		}
		function getQueryList(searchText){
			console.info("刷新");
			var params=MakeQueryParam($(".panel"));
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Students/getStudentsList?sina=<%= SmBaseUtil.Random() %>&AreaID="+$("#AreaID").val()+"&check="+encodeURI(encodeURI(searchText)),
			}); 
		}
	</script>
</body>
</html>