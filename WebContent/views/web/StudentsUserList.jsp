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
	if(user==null){
		user=new Users();
	}

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
<input type="hidden" name="isOpen" id="isOpen" value="0" />
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>学生列表</strong></li>
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
								<label>状态</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Status" name="Status">
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">正常</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>是否正式社员</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Official" name="Official">
									<option></option>
									<option value="<%= SmBaseGlobal.OfficialType.Official.getid() %>">是</option>
									<option value="<%= SmBaseGlobal.OfficialType.Normal.getid() %>">否</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>小编星级</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Level" name="Level">
									<option></option>
									<option value="1">1星</option>
									<option value="2">2星</option>
									<option value="3">3星</option>
									<option value="4">4星</option>
									<option value="5">5星</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>小编职位</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="Chief" name="Chief">
									<option></option>
									<option value="<%= SmBaseGlobal.ChiefType.Chief.getid() %>">社长</option>
									<option value="<%= SmBaseGlobal.ChiefType.DeputyChief.getid() %>">副社长</option>
									<option value="<%= SmBaseGlobal.ChiefType.Editorial.getid() %>">编委</option>
									<option value="<%= SmBaseGlobal.ChiefType.Normal.getid() %>">普通</option>
								</select>
							</div>
							<div class="col-md-3">
								<label>是否今日生日</label>
								<select class="form-control m-b" onchange="getQueryList()" iscon="true"
									id="ToDayAge" name="ToDayAge">
									<option></option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
					</div>
					</div>
				</div>
					<input type="hidden" id="AreaID" name="AreaID" value="${AreaID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">						
							<button type="button" title="新闻列表" onclick="studentsNewsView()" class="btn btn-outline btn-default">
								<i  title="新闻列表"  aria-hidden="true"></i>
								新闻列表
							</button>
							<button type="button" title="微米管理" onclick="WeMoneyList()" class="btn btn-outline btn-default">
								<i  title="微米管理"  aria-hidden="true"></i>
								微米管理
							</button>
							<button type="button" title="添加积分" id='IntegrationShow' class="btn btn-outline btn-default">
								<i  title="添加积分"  aria-hidden="true"></i>
								添加积分
							</button>
							<button type="button" title="设置为正式社员" onclick="changeOfficial(1)" class="btn btn-outline btn-default">
								<i  title="设置为正式社员"  aria-hidden="true"></i>
								转正
							</button>
							<button type="button" title="取消正式社员资格" onclick="changeOfficial(2)" class="btn btn-outline btn-default">
								<i  title="取消正式社员资格"  aria-hidden="true"></i>
								取消转正
							</button>
							<button type="button" title="设置职位" id='ChiefShow' class="btn btn-outline btn-default">
								<i  title="设置职位"  aria-hidden="true"></i>
								设置职位
							</button>
							<button type="button" title="投稿趋势" id='NoticeAn' class="btn btn-outline btn-default">
								<i  title="投稿趋势"  aria-hidden="true"></i>
								投稿趋势
							</button>
							<button type="button" title="设置勋章" id='HonorView' class="btn btn-outline btn-default">
								<i  title="设置勋章"  aria-hidden="true"></i>
								设置勋章
							</button>
							<button type="button" title="导入学生" id='importStudents' class="btn btn-outline btn-default">
								<i  title="导入学生"  aria-hidden="true"></i>
								导入学生
							</button>
							<button type="button" title="打钱" id='MakeMoney' class="btn btn-outline btn-default">
								<i  title="打钱"  aria-hidden="true"></i>
								金钱打赏
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
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

     	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titles"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label style="display: none;" id="pkid"></label>
						<input type="text" class="form-control" placeholder="请输入增加原因" id="reason"  /><br>
						<input type="text" class="form-control" placeholder="请输入积分数"id="num" /><br>
					</div>
					<br />
					<button id="addIntegration" class="btn btn-warning">确定增加</button>
					<button id="relatedShow" class="btn btn-warning">关联活动增加积分</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="myModalChief" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >设置职位</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label>职位</label>
						<select class="form-control m-b" iscon="true"
							id="ChiefStatus" name="ChiefStatus">
							<option value="<%= SmBaseGlobal.ChiefType.Normal.getid() %>">无</option>
							<option value="<%= SmBaseGlobal.ChiefType.Chief.getid() %>">社长</option>
							<option value="<%= SmBaseGlobal.ChiefType.DeputyChief.getid() %>">副社长</option>
							<option value="<%= SmBaseGlobal.ChiefType.Editorial.getid() %>">编委</option>
						</select>
					</div>
					<br />
					<button onclick="changeChief();" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>


<div class="modal" id="importStudent" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >导入学生</h4>
				</div>
				<div class="modal-body">
					<iframe id="iframe_importStudent" style="border:0px" src="#" width="500" height="200"> </iframe> 
				</div>
			</div>
		</div>
	</div>

<div class="modal" id="myModalHonor" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >设置勋章</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label>选择勋章</label>
						<select class="form-control m-b" id="HonorType" name="HonorType">
							<c:forEach var="data" items="${Data}">
								<option value="${data.ID }">${data.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row">
						<label>到期时间</label>
						<input type="date" class="form-control" id="endTime" name="endTime" placeholder="请输入过期时间" />
					</div>
					<br />
					<button onclick="changeHonor();" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>
	
		
<div class="modal" id="myModal_news" style="overflow: auto;margin-left: -500px" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:1100px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >新闻列表</h4>
				</div>
				<div class="modal-body">
					<iframe id="iframe_news" style="border:0px" src="#" width="1024" height="500"> </iframe> 
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="myModal_wemoney" style="overflow: auto;margin-left: -500px" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:1100px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >微米管理</h4>
				</div>
				<div class="modal-body">
					<iframe id="iframe_wemoney" style="border:0px" src="#" width="1024" height="500"> </iframe> 
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="myModalActivity" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">此条新闻评价</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageStudent"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="StudentListEventsToolbar"
							role="group">
							<button id="relatedActivity"
						class="btn btn-warning">关联活动</button>
						</div>
					<table id="Activity" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="title">活动标题</th>
									<th data-field="isHot">是否热门</th>
									<th data-field="endTime">结束日期</th>
									<th data-field="baseInfo.name">状态</th>
							</tr>
						</thead>
					</table>
					
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
	
	
	
<div class="modal" id="mymoney" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">金钱打赏</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label id="error" style="color: red;"></label>
						<input type="hidden" id="pkidm">
						<input
								type="text" class="form-control" placeholder="请输入金额"
								id="money" name="money" /> 
						<input
								type="text" style="margin-top: 20px;" class="form-control" placeholder="请输入原因"
								id="reasonm" name="reasonm" /> 	
					</div>
					<br />
					<button id="trueMoney" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>

	
	<input type="hidden" id="basePath" value="<%=basePath%>"
		name="basePath" />
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
		var DataTable=$("#exampleTableEvents");
		var DataTable2=$("#Activity");
		if(<%=user.getLevel()<SmBaseGlobal.LevelStatus.ParsonManage.getid() %>){
			initialRegionQueryInfo($(".panel-body"),'query',1);
		}
		
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Students/getStudentsUserList",
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000,10000], 
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入姓名或者手机号";},
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
				

				DataTable2.bootstrapTable({
					url : "<%=path%>/Activity/getActivityList?sina=<%= SmBaseUtil.Random() %>",
					//search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					iconSize : "outline",
					toolbar : "#StudentListEventsToolbar",
					icons : geticons
				});
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									studentsNewsView();
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								})
			}()

		}(document, window, jQuery);


		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Notices/deleteNotices",
						data : {
							'pid' : wids,
							'state' : flag
							
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
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Students/getStudentsUserList",
						
			});
			
		}
		function queryParams(params) {
			var BelongAreaIDAll="";
			
				BelongAreaIDAll = $("#UnitAreaID").val();
				if(BelongAreaIDAll==""){
					BelongAreaIDAll = $("#AreaID").val();
				}
				if(BelongAreaIDAll==""){
					BelongAreaIDAll = $("#City").val();
				}
			if($("#isOpen").val()=="1"){
				if(BelongAreaIDAll==""){
					BelongAreaIDAll=$("#Province").val();
				}
			}
			
			var temp=MakeQueryParamJson($(".panel"));
			temp.pageSize=params.limit;
			temp.pageNumber=params.offset;
			temp.check=$(".search input").val();
			temp.sina='<%= SmBaseUtil.Random() %>';
			temp.isStu=1;
			temp.isOpen=1;
			temp.BelongAreaIDAll = BelongAreaIDAll;
			return temp;

		}
		function studentsNewsView()
		{
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				$("#iframe_news").attr("src",'<%=path%>/Students/StudentsNewsList?sina=<%= SmBaseUtil.Random() %>&id='+t['pkid']);
				$("#myModal_news").modal("show");
				
			}
		}
		function WeMoneyList()
		{
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				$("#iframe_wemoney").attr("src",'<%=path%>/WeMoney/WeMoneyList?sina=<%= SmBaseUtil.Random() %>&id='+t['pkid']);
				$("#myModal_wemoney").modal("show");
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


function changeHonor(type){
	var t=DataTable.bootstrapTable("getSelections")[0];
	if(CheckhasChecked(DataTable)==true)
	{
		$.ajax({
			url : "<%=path%>/HonorRecord/addHonorRecord",
			data : {
				'uid' : t.pkid,
				'type':$("#HonorType").val(),
				'endTime':$("#endTime").val()
			},
			success : function(obj) {
				if (obj.Status) {
					ViewSuccess();
				}else{
					ViewWarningError("设置失败,该学生已经获得该勋章,当前未失效");
				}
				$("#myModalHonor").modal("hide");
			}
		});
		
	}
	
}


function changeOfficial(type){
	var t=DataTable.bootstrapTable("getSelections")[0];
	if(CheckhasChecked(DataTable)==true)
	{
		$.ajax({
			url : "<%=path%>/Students/changeOfficial",
			data : {
				'id' : t.pkid,
				'type':type,
				'sina':Math.random()
			},
			success : function(obj) {
				if (obj.Status) {
					ViewSuccess();
						
				}else{
					ViewWarningError("设置失败");
				}
				
			}
		});
		
	}
	
}

function changeChief(){
	var t=DataTable.bootstrapTable("getSelections")[0];
	if(CheckhasChecked(DataTable)==true)
	{
		$.ajax({
			url : "<%=path%>/Students/changeChief",
			data : {
				'id' : t.pkid,
				'type':$("#ChiefStatus").val()
			},
			success : function(obj) {
				if (obj.Status) {
					ViewSuccess();
				}else{
					ViewWarningError("设置失败");
				}
				$("#myModalChief").modal("hide");
				
			}
		});
		
	}
	
}

$("#ChiefShow").click(function(){
	var t=DataTable.bootstrapTable("getSelections")[0];
	if(CheckhasChecked(DataTable)==true)
	{
		$("#ChiefStatus").val(t.chief);
		$("#myModalChief").modal("show");
	}
})

$("#HonorView").click(function(){
	var t=DataTable.bootstrapTable("getSelections")[0];
	if(CheckhasChecked(DataTable)==true)
	{
		$("#myModalHonor").modal("show");
	}
})



		$("#IntegrationShow").click(function(){
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				$("#myModal").modal("show");
				$("#titles").html('为"'+t['name']+'"添加积分');
				$("#pkid").html(t['pkid']);
			}
		})
		
		$("#addIntegration").click(function(){
			var id=$("#pkid").html();
			var title=$("#reason").val();
			var num=$("#num").val();
			 $.ajax({
					url : "<%=path%>/Students/addIntegration",
					data : {
						'id' : id,
						'title' : encodeURI(title),
						'num':num
					},
					success : function(obj) {
						$("#myModal").modal("hide");
						var view=$("#ErrorMessage");
						if (obj.Status) {
								view.text('积分增加成功!').removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
						}else{
								view.text('积分增加失败!').removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
						}
						
					}
				});

		})

		$("#relatedShow").click(function(){
			$("#myModal").modal("hide");
			$("#myModalActivity").modal("show");
			DataTable2.bootstrapTable("refresh", {
					url : "<%=path%>/Activity/getActivityList?sina=<%= SmBaseUtil.Random() %>"
			});
		})
		
		$("#importStudents").click(function(){
			$("#importStudent").modal("show");
			$("#iframe_importStudent").attr("src",'<%=basePath%>Students/addResources?sina=<%= SmBaseUtil.Random() %>');
		})
		function hideImport(){
	
			$("#importStudent").modal("hide");
		}
		
		
		
		$("#NoticeAn").click(function(){
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				$("#NoticesAng").modal("show");
				referNoticesCount(0,"",t['pkid']);
				
			}
		});
		
		function referNoticesCount(year,type,uid){
			if(uid==null){
				var t=DataTable.bootstrapTable("getSelections")[0];
				uid=t['pkid'];
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
			
			 $.get('<%=path%>/getCurrentYearNotices?type='+ type +'&year='+ year +'&uid='+ uid +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
				 	initialNoticesStatChat(myChart,data);

		 	});
		}
		$("#relatedActivity").click(function(){
			var t=DataTable2.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				var id=$("#pkid").html();//学生编号
				var title=encodeURI(t['title']);
				var aid=t['pkid'];
				 $.ajax({
					url : "<%=path%>/Students/relatedActivity",
					data : {
						'id' : id,
						'title' : title,
						'aid':aid
					},
					success : function(obj) {
						$("#myModalActivity").modal("hide");
						var view=$("#ErrorMessage");
						if (obj.Status) {
								view.text(obj.Content).removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
						}else{
								view.text(obj.Content).removeClass("alert alert-success")
								.addClass("alert alert-warning");
								view.show();
								gotohidden(view);
						}
						
					}
				});
			}
		})
		
		$("#MakeMoney").click(function(){
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true)
			{
				console.log(t.pkid)
				$("#error").html(null);
				$("#pkidm").val(t.pkid);
				$('#mymoney').modal('show');
			}
		});
		$("#trueMoney").click(function(){
			 $.ajax({
					url : "<%=path%>/Students/makeMoney",
					data : {
						'id' : $("#pkidm").val(),
						'money' : $("#money").val(),
						'reason':$("#reasonm").val()
					},
					success : function(obj) {
						//$("#mymoney").modal("hide");
						$("#error").html(obj.message);
					}
				});
		})
	</script>
</body>
</html>