<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Users"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	long userid=user.getID();
	int userLevel=user.getLevel();
	Boolean Superlevel=user.getLevel()==SmBaseGlobal.LevelStatus.SuperManage.getid();
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

<body >
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
		<br/>
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>用户列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="panel panel-default select">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"  onclick="checkCollapse($(this))"
								aria-collapse="false" class="collapsed">查询条件(点击展开)</a>
								
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
						<div class="panel-body">
						<input type="hidden" id="CityAreaID" name="CityAreaID" value="${CityAreaID}" />
						<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
						<input type="hidden" name="UnitID" value="${UnitAreaID}" id="UnitID" />
						<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
						<input type="hidden" name="Company" value="${KeyWord}" id="Company" iscon="true" />
						<input type="hidden" name="AdminLevelParseon" value="${AdminLevelParseon}" id="AdminLevelParseon" iscon="true" />
						<input type="hidden" name="AdminLevelArea" value="${AdminLevelArea}" id="AdminLevelArea" iscon="true" />
							<div class="col-md-3">
								<label>状态</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Status" name="Status">
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">正常</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>级别</label>
								<select class="form-control m-b" onchange="getQueryList();" iscon="true"
									id="LevelID" name="LevelID">
								
										<c:forEach var="Part" items="${QueryAdminLevel}">
											<option value="${Part.ID}">
												${Part.name}</option>
										</c:forEach>
									
									
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
						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" title="新增" onclick="javascript:window.location.href='<%=path%>/Users/addUser'"
							class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								新增
							</button>
							<button type="button" onclick="modifyUser()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" title="修改" aria-hidden="true"></i>
								修改
							</button>
							<button type="button" onclick="deleteUser(88)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除" aria-hidden="true"></i>
								删除
							</button>
							<button type="button" onclick="deleteUser(1)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-play"  title="恢复" aria-hidden="true"></i>
								恢复
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="loginName">用户名</th>
									<th data-field="name">姓名</th>
									<th data-field="phone">联系方式</th>
									<th data-field="adminLevel.name">级别</th>
				 				<th data-field="area.name">地区</th> 
									
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
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>


<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
	initialRegionQueryInfo($(".panel-body"),'query',1);
	DataTable.bootstrapTable({
        url: "<%=path%>/Users/getUserList",
        search: !0,
        pagination: !0,
        showRefresh: !0,
        showToggle: !0,
        showColumns: !0,
        sidePagination : "server", //服务端请求
        formatSearch:function(){return "查找姓名"},
		queryParams : queryParams,
		responseHandler : responseHandler,
		clickToSelect : true,
        rowStyle:dispErrorRow,
        iconSize: "outline",
        toolbar: "#exampleTableEventsToolbar",
        icons: geticons
    });
    
	DataTable.on("dbl-click-row.bs.table",
    function(e, t, o) {
		if(CheckhasChecked(DataTable)==true){
			var url='<%=path%>/Users/addUser?uid=';
			viewOrModifyByPKID(DataTable,url);
		}
    }).on("search.bs.table", function(e, t, o) {
    	getQueryList(t);
	})
    
    
   
    function modifyUser() {
		if(CheckhasChecked(DataTable)==true){
			var url='<%=path%>/Users/addUser?uid=';
			viewOrModifyByPKID(DataTable,url);
		}
	}
    
    /* flag=1表示启用,0为反之为删除*/
	function deleteUser(flag) {
		if(CheckhasChecked(DataTable)==true){
			var wids = getRecordPKIDs(DataTable);
			if(wids==<%=userid%>){
				ViewWarningError("不能对自己进行该操作!");
				return false;
			}
			if (wids != '') {
				$.ajax({
							url : "<%=path%>/Users/deleteUser",
							data : {
								'uid' : wids,
								'state' : flag
							},
							success : function(result) {
								if(result.Status==1){
									ViewSuccess();
									getQueryList();
								}else{
									ViewWarningError(result.ErrorMsg);
								}
							}
						});
			}
		}

	}
	function queryParams(params) {
		var temp=MakeQueryParamJson($(".panel"));
		temp.pageSize=params.limit;
		temp.pageNumber=params.offset;
		temp.Name=$(".search input").val();
		temp.sina='<%= SmBaseUtil.Random() %>';
		return temp;
		
	}
    function getQueryList(searchText){
		//根据管理员查询的情况 去掉不相干的条件
		limitQuery($("#LevelID"))
    	DataTable.bootstrapTable(
				"refresh",
				{
					url : "<%=path%>/Users/getUserList"
				});
    }
    function limitQuery(obj){
    	if((<%=userLevel%>== <%=SmBaseGlobal.LevelStatus.ParsonManage.getid()%> ||<%=userLevel%>==<%=SmBaseGlobal.LevelStatus.TeacherManage.getid()%>) ||(obj.val()==<%=SmBaseGlobal.LevelStatus.Manage.getid()%> || obj.val()==<%=SmBaseGlobal.LevelStatus.SuperManage.getid()%>)){
    		$("#UnitAreaIDDiv").css("display","none");
    		$("#ProvinceDiv").css("display","none");
    		$("#AreaIDDiv").css("display","none");
    		$("#CityDiv").css("display","none");
    	}else if(<%=userLevel%>== <%=SmBaseGlobal.LevelStatus.AreaManage.getid()%>){
    		
    		$("#ProvinceDiv").css("display","none");
    		$("#AreaIDDiv").css("display","none");
    		$("#CityDiv").css("display","none");
    	}else{
    		$("#UnitAreaIDDiv").css("display","block");
    		$("#ProvinceDiv").css("display","block");
    		$("#AreaIDDiv").css("display","block");
    		$("#CityDiv").css("display","block");
    	}
    }
    
</script>
</body>
</html>