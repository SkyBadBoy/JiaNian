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
					<li><strong>商家申请加盟</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12" style="margin-top: 20px">
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
									id="status" name="status">
									<option value=""></option>
									<option value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">审核通过</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Apply.getid() %>">待审核</option>
									<option value="<%= SmBaseGlobal.CheckStatus.NotPass.getid() %>">审核不通过</option>
									<option value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
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
									<button type="button"
									onclick="deleteWeChat(<%=SmBaseGlobal.CheckStatus.Effective.getid() %>)"
									class="btn btn-outline btn-default">
									<i class="fa fa-check" title="通过" aria-hidden="true"></i> 通过
								</button>
								<button type="button"
									onclick="deleteWeChat(<%=SmBaseGlobal.CheckStatus.NotPass.getid() %>)"
									class="btn btn-outline btn-default">
									<i class="fa fa-close" title="不通过" aria-hidden="true"></i> 不通过
								</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="id">申请编号</th>
								 	<th data-field="name">名称</th>
									<th data-field="phone">联系方式</th>
									<th data-field="business">营业执照</th>
									<th data-field="content">简介</th>
									<th data-field="createtime">申请时间</th>
									<th data-field="baseInfo.name">状态</th>  
									<th data-field="userid">关联管理员ID</th>  
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

	
	<input type="hidden" id="basePath" value="<%=basePath%>"
		name="basePath" />
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
		var DataTable=$("#exampleTableEvents");
		var DataTable2=$("#Activity");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Scenic/getScenicList",
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
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									//studentsNewsView();
								}).on("search.bs.table", function(e, t, o) {
									getQueryList(t);
								})
			}()
		}(document, window, jQuery);

		function deleteWeChat(flag) {
			console.log("状态"+flag);
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Scenic/makeScenic?pid="+wids+"&state="+flag,
						success : function(json) {
							console.log(json+'json返回值')
							ViewSuccess();
							getQueryList();
						}
					});
				} 
			}
		}
		function getQueryList(searchText){
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Scenic/getScenicList",
						
			});
		}
		function queryParams(params) {
			var temp=MakeQueryParamJson($(".panel"));
			temp.pageSize=params.limit;
			temp.pageNumber=params.offset;
			temp.check=$(".search input").val();
			temp.sina='<%= SmBaseUtil.Random() %>';
			temp.isStu=1;
			temp.isOpen=1;
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