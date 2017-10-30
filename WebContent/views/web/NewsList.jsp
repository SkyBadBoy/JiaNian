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
	System.err.println(Status);
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
					<li><strong>新闻列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>

		<div id="shade"></div>
		<div id="Layer1">
			<div id="iphone">
				<div id="liulan"></div>
				<div>
					<ul class="view_list list-unstyled">
						<c:if test="${isApply}">
							<li class="view_item"
								onclick="deleteWeChat(<%=SmBaseGlobal.CheckStatus.Effective.getid() %>)">通&nbsp;&nbsp;&nbsp;&nbsp;过</li>
							<li class="view_item"
								onclick="deleteWeChat(<%=SmBaseGlobal.CheckStatus.NotPass.getid() %>)">不通过</li>
						</c:if>
						<li class="view_item" onclick="closeHtml()">关&nbsp;&nbsp;&nbsp;&nbsp;闭</li>
					</ul>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						<c:if test="${!isApply}">
							<div class="panel panel-default ">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseThree" onclick="checkCollapse2($(this))"
											aria-collapse="false" class="collapsed">查询条件(点击展开)</a>

									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse"
									aria-expanded="false" style="height: 0px;">
									<div class="panel-body">
										<div class="col-md-3">
											<label>状态</label> <select class="form-control m-b"
												onchange="getQueryList()" iscon="true" id="Status"
												name="Status">
												<option
													value="<%= SmBaseGlobal.CheckStatus.Effective.getid() %>">正常</option>
												<option
													value="<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>">删除</option>
											</select>
										</div>
										<div class="col-md-3">
											<label>是否审核</label> <select class="form-control m-b"
												onchange="getQueryList()" iscon="true" id="IsTop"
												name="IsTop">
												<option
													value="0">未审核</option>
												<option
													value="">全部</option>
												<option
													value="1">已审核</option>
												
											</select>
										</div>
									</div>

								</div>
							</div>
						</c:if>
						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<c:if test="${isApply}">
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
							</c:if>

							<c:if test="${!isApply}">

								<button type="button"
									onclick="javascript:window.location.href='<%=path%>/Notices/addNews?type=2&isNew=0'"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-plus" title="新增"
										aria-hidden="true"></i> 新增
								</button>
								<button type="button" onclick="modifyProduct()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-pencil" title="修改"
										aria-hidden="true"></i> 修改
								</button>
								<button type="button" onclick="deleteWeChat(88)"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-trash" title="删除"
										aria-hidden="true"></i> 删除
								</button>
								<button type="button" onclick="preview()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-zoom-in" title="预览"
										aria-hidden="true"></i> 预览
								</button>
								<button type="button" onclick="preview2()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-option-vertical" title="评价"
										aria-hidden="true"></i> 评价
								</button>
								<button type="button" onclick="addCaptainComment()"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-hand-up" title="社长点评"
										aria-hidden="true"></i> 社长点评
								</button>
								<button type="button" onclick="deleteWeChat(9)"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-star" title="上传官网"
										aria-hidden="true"></i> 上传官网
								</button>
								<button type="button" onclick="deleteWeChat(10)"
									class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-star-empty" title="下架官网"
										aria-hidden="true"></i> 下架官网
								</button>
								<c:if test="${ not empty shang}">
									<button type="button" onclick="send()" class="btn btn-outline btn-default">
										<i class="glyphicon " title="上传此新闻"  aria-hidden="true"></i>
										上传此新闻
									</button>
								<c:if test="${shang==2}">
									当前规定：${key }
								</c:if>
							</c:if>
							</c:if>
						</div>
						<c:if test="${isApply}">
							<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-width="100" data-field="pkid">新闻编号</th>
										<th data-width="100" data-field="image.purl">新闻配图</th>
										<th data-width="100" data-field="title">新闻标题</th>
										<th data-field="content" >内容</th>
										<th data-width="100" data-field="createTime">创建日期</th>
										<th data-width="60" data-field="author">作者</th>
										<th data-width="60" data-field="student.school">作者学校</th>
										<th data-width="60" data-field="clickCount">阅读量</th>
				
									</tr>
								</thead>
							</table>
						</c:if>
						<c:if test="${!isApply}">
							<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
									 	<th data-field="state" data-checkbox="true"></th>
									 	<th data-width="100" data-field="pkid">新闻编号</th>
									 	<th data-width="100" data-field="image.purl">新闻配图</th>
										<th data-width="100" data-field="title">新闻标题</th>
										<th data-field="content" >内容</th>
										<th data-width="100" data-field="createTime">创建日期</th>
										<th data-width="60" data-field="author">作者</th>
										<th data-width="60" data-field="student.phone">作者电话</th>
										<th data-width="60" data-field="student.school">作者学校</th>
										<th data-width="60" data-field="clickCount">阅读量</th>
										<th data-width="60" data-field="like">点赞数</th>
										<th data-width="60" data-field="voteCount">投票数</th>
										<th data-width="60" data-field="commentCount">评论数</th>
									</tr>
								</thead>
							</table>
						</c:if>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>

	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
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
							<button onclick="deleteE()"
						class="btn btn-warning">删除评论</button>
						</div>
					<table id="wechatlist" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
								<th data-field="state" data-radio="true"></th>
								<th data-field="userName">名字</th>
								<th data-field="content">内容</th>
								<th data-field="createTime">时间</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
		</div>
	</div>
		<div class="modal" id="myModal1" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">添加社长点评</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label style="display: none;" id="pkid"></label>
						<textarea id="CaptainComment" class="form-control"  placeholder="请输入点评内容" rows="3" cols=""></textarea>
					</div>
					<br />
					<button id="addIntegration" class="btn btn-warning">增加</button><span style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;社长点评的文章将自动入选微简报</span>
				</div>
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
	initialRegionQueryInfo($(".panel-body"),'query',1);
	var DataTable=$("#exampleTableEvents");
	var DataTable2=$("#wechatlist");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					
					url : "<%=path%>/Notices/getNoticesList",
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000,10000], 
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
			}()
				DataTable2.bootstrapTable({
					url : "<%=path%>/Comment/getCommentPCList",
					//search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					sidePagination : "server", //服务端请求
					queryParams : queryParamsComment,
					responseHandler : responseHandler,
					iconSize : "outline",
					toolbar : "#StudentListEventsToolbar",
					icons : geticons
				});
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
				$("#nsid").html(t["pkid"]);
				$("#myModal").modal("show");
				$("#titlens").html("《"+t['title']+"》");
				DataTable2.bootstrapTable("refresh", {
					url : "<%=path%>/Comment/getCommentPCList"
				});
		 	} 
		}
		function addCaptainComment(){
			 if(CheckhasChecked(DataTable)==true){ 
				var t=DataTable.bootstrapTable("getSelections")[0];
				$("#pkid").text(t["pkid"]);
				$("#CaptainComment").val(t["captainComment"]);
				$("#myModal1").modal("show");
				
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
									url : "<%=path%>/Comment/getCommentPCList"
								});
							}
						}
					});
				} 
			}
		}
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
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Notices/getNoticesList"
			});
		}
		function queryParamsComment(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				Sina	:Math.random(),
				nid:$("#nsid").html()
			};

		}
		function queryParams(params) {
			var NewsID="";
			if($("#NewsID").val()!=""){
				NewsID=$("#NewsID").val();
				$("#NewsID").val("");
			}
			var temp=MakeQueryParamJson($(".panel"));
			temp.pageSize=params.limit;
			temp.pageNumber=params.offset;
			temp.Title=$(".search input").val();
			temp.sina='<%= SmBaseUtil.Random() %>';
			temp.pid=NewsID;
			temp.Status=($("#Status").val()!=undefined?$("#Status").val():'${Status}');
			temp.isOpen=$("#isOpen").val();
			return temp;
		}
		function closeHtml()
		{
 			shade.style.display='none';
			Layer1.style.display='none';
			$("#liulan").html("");
			document.getElementById("body").style.overflow="auto";
		}
		function preview(){
			 if(CheckhasChecked(DataTable)==true){ 
				var t=DataTable.bootstrapTable("getSelections")[0];
				shade.style.display='block';
				Layer1.style.display='block';
					var pid=t["pkid"];
				var areaID=t["areaID"]; 
				var liulan="<iframe id='frame' width='100%' height='100%' src=<%=basePath%>Product/phoneweChatPordDetail?_ispre_=1&_type_=2&_pid_="+pid+"&_pc_=&_area_="+areaID+"&_status_="+t["status"]+"></iframe>";
				$("#liulan").append(liulan);
				document.getElementById("body").style.overflow="hidden";
					
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
	function send()
	{
		if(CheckhasChecked(DataTable)==true){
			var t=DataTable.bootstrapTable("getSelections")[0];
			var image=t['image']!=null?t['image'].url:'upload/2a27c63a-06f7-41b5-97fa-97f3ca35527f.JPG';
			image="<%=rootPath%>"+image;
			if($("#shang").val()==1)
			{
				$.ajax({
					url : "<%=path%>/WeChatCustom/GroupSend",
					type : "post",
					data : {
						'type':"mpnews",
						'urlpic':image,
						'author':t['author'],
						'title':t['title'],
						'content_source_url':"<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+t['pkid'],
						'tucontent':t['content'],
						'digest':t['content'].length>=15?t['content'].substring(0,15):t['content'].substring(0,t['content'].length)
					} 
				});
			}
			if($("#shang").val()==2)
			{
				console.log(image);
				$.ajax({
					url : "<%=path%>/WeChatCustom/Mpnews",
					type : "get",
					data : {
						'title':encodeURI(t['title']),
						'description':encodeURI(t['content'].length>=15?t['content'].substring(0,15):t['content'].substring(0,t['content'].length)),
						'key':encodeURI($("#key").val()),
						'picurl':image,
						'url':"<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+t['pkid'],
					}
				});
			}
		}
		
	}
	
	$("#addIntegration").click(function(){
		var id=$("#pkid").text();
		var title=$("#CaptainComment").val();
		 $.ajax({
				url : "<%=path%>/Notices/addCaptainComment",
				data : {
					'nid' : id,
					'CaptainComment' : encodeURI(title)
				},
				success : function(obj) {
					$("#myModal1").modal("hide");
					var view=$("#ErrorMessage");
					if (obj.status) {
						ViewSuccess();
					}else{
						ViewWarningError("增加失败");
					}
					
				}
			});

	})
		
	</script>
</body>
</html>