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
	Users user = (Users) request.getSession().getAttribute("UserName");
	long userid=user.getID();
	int userLevel=user.getLevel();
	Boolean Superlevel=user.getLevel()==SmBaseGlobal.LevelStatus.SuperManage.getid();
	
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />
<style>
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
	<input type="hidden" id="LevelID" name="LevelID" value="<%=user.getLevel() %>" />
	<input type="hidden" id="CityAreaID" name="CityAreaID" value="${CityAreaID}" />
						<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
						<input type="hidden" name="UnitID" value="${UnitAreaID}" id="UnitID" />
						<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
	<input type="hidden" id="basePath" value="<%=basePath%>"
		name="basePath" />
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>支社列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">

					<div class="panel panel-default ">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"  onclick="checkCollapse2($(this))"
								aria-collapse="false" class="collapsed">查询条件(点击展开)</a>
								
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
						<div class="panel-body">
						<input type="hidden" name="isOpen" id="isOpen" iscon="true" value="0" />
							<div class="col-md-3">
								<label>状态</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Status" name="Status">
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">正常</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
								</select>
								
											
							
								<label>是否嫁接</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="bind" name="bind">
									<option value="1">已嫁接</option>
									<option value="0">未嫁接</option>
								</select>
						

						
							</div>

						</div>

					</div>
				</div>
				
				
					<div class="example">
						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button"
								onclick="javascript:window.location.href='<%=path%>/weChatPublic/addWeChatPublic'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="增加"
									aria-hidden="true"></i> 增加
							</button>
							<button type="button" onclick="modifyWeChat()" title="修改"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
							</button>
							<button type="button" onclick="deleteWeChat(88)" title="删除"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 删除
							</button>
							<button type="button" onclick="deleteWeChat(1)" title="恢复"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-play" aria-hidden="true"></i> 恢复
							</button>
							<button type="button" onclick="viewStudents()" title="小记者列表"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i>
								新浪小编列表
							</button>
							<button type="button" title="投稿趋势" id='NoticeAn' class="btn btn-outline btn-default">
								<i  title="投稿趋势"  aria-hidden="true"></i>
								投稿趋势
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>" 
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<!-- <th data-width="150" data-field="pkid">ID</th> -->
									<th data-width="150" data-field="weChat">支社微信号</th>
									<th data-field="company">学校名称</th>
									<th data-field="contactPerson">联系人</th>
									
									<th data-field="baseInfo.name">状态</th>
									<th data-field="isBind">绑定状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>
	<div class="modal" id="myModal_students" style="overflow: auto;margin-left: -500px" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:1100px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >学生列表</h4>
				</div>
				<div class="modal-body">
					<iframe id="iframe_students" style="border:0px" src="#" width="1024" height="500"> </iframe> 
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="NoticesAng" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">投稿趋势</h4>
				</div>
				<div class="modal-body">
				<div class="col-md-3">
				<select class="form-control m-b"  onchange="referNoticesCount()" 
						id="showType" name="showType">
						<option value="">按日展示</option>
						<option value="year">按月展示</option>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referNoticesCount()" 
						id="showyear" name="showyear">
						<option value=""></option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
					</select>
					</div>
				 <div id="main" style="height:300px; width: 600px">数据加载中,请稍候...</div> 
					
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/commonJs.jsp" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>
	<script type="text/javascript">
		var DataTable = $("#exampleTableEvents");
		initialRegionQueryInfo($(".panel-body"),'query',1);
		

				DataTable.bootstrapTable({
					url : "<%=path%>/weChatPublic/getWeChatPublicList",
					search : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					pageList:[10,25,50,100],
					striped : !0, //是否显示行间隔色
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					clickToSelect : true,
					rowStyle : dispErrorRow,
					iconSize : "outline",
					formatSearch:function(){return "请输入学校名称"},
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				DataTable.on("dbl-click-row.bs.table", function(e, t, o) {
					if (CheckhasChecked(DataTable) == true) {
						var url = '<%=path%>/weChatPublic/addWeChatPublic?wid=';
						viewOrModifyByPKID(DataTable, url);
					}
				}).on("search.bs.table", function(e, t, o) {
					getQueryList(t);
				})
			
		//修改产品
		function viewStudents() {
			if (CheckhasChecked(DataTable) == true) {
				$("#iframe_students").attr("src",'<%=path%>/Students/StudentsList?sina=<%= SmBaseUtil.Random() %>&AreaID='+DataTable.bootstrapTable("getSelections")[0].areaID);
				$("#myModal_students").modal("show");
			}
		}
		
		/* flag=1表示启用,0为反之为删除*/
		function deleteWeChat(flag) {
			if (CheckhasChecked(DataTable) == true) {
				var wids = getRecordPKIDs(DataTable);
				$.ajax({
					url : "<%=path%>/weChatPublic/deleteWeChatPublic",
					data : {
						'wid' : wids,
						'state' : flag
					},
					success : function() {
						ViewSuccess();
						getQueryList();
					}
				});
			}
		}
		function queryParams(params) {
			var temp=MakeQueryParamJson($(".panel"));
			temp.pageSize=params.limit;
			temp.pageNumber=params.offset;
			temp.Company=$(".search input").val();
			temp.sina='<%= SmBaseUtil.Random() %>';
			return temp;
		
		}
		function getQueryList(searchText) {
			limitQuery($("#LevelID"));
			$("#Company").val(searchText);
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/weChatPublic/getWeChatPublicList"
			});

		}
		function modifyWeChat() {
			if (CheckhasChecked(DataTable) == true) {
				var url = '<%=path%>/weChatPublic/addWeChatPublic?wid=';
				viewOrModifyByPKID(DataTable, url);
			}
		}
		  function limitQuery(obj){
		    	if((<%=userLevel%>== <%=SmBaseGlobal.LevelStatus.ParsonManage.getid()%> ||<%=userLevel%>==<%=SmBaseGlobal.LevelStatus.TeacherManage.getid()%>) ){
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
		    

function checkCollapse2(obj){
	if($("#collapseThree").css("height")=="0px"){
		obj.text("查询条件(点击收开)");
		$("#isOpen").val("1");
		
	}else{
		obj.text("查询条件(点击展开)");
		$("#isOpen").val("0");
	}
	getQueryList();
}
		
$("#NoticeAn").click(function(){
	var t=DataTable.bootstrapTable("getSelections")[0];
	if(CheckhasChecked(DataTable)==true)
	{
		$("#NoticesAng").modal("show");
		referNoticesCount(0,"",t['areaID']);
		
	}
});
		    

function referNoticesCount(year,type,uid){
	if(uid==null){
		var t=DataTable.bootstrapTable("getSelections")[0];
		uid=t['areaID'];
	}
	var year=$("#showyear").val();
	var type=$("#showType").val();
	if(year==null || year=="" || year=="0"){
		var date = new Date();
		year=1900+date.getYear();
	}
	if(type==null || type==""){
		type="";
	}
	var myChart = echarts.init(document.getElementById('main'));

	 $.get('<%=path%>/getCurrentYearNotices?type='+ type +'&year='+ year +'&areaID='+ uid +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		 	initialNoticesStatChat(myChart,data);

 	});
}
</script>
</body>
</html>