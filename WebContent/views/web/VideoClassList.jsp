<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
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


	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>视频分类列表</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<input type="hidden" id="searchText" name="searchText" iscon="true" value="${searchText}"/>
					<input type="hidden" id="searchText4" name="searchText4" iscon="true" />
					<input type="hidden" id="WeChatID" name="WeChatID" iscon="true" value="${WeChatID}"/>
					<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>"/>
					<input type="hidden" id="parentID1" name="parentID" value=""/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="addVideoClass(1)"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="增加视频类别" aria-hidden="true"></i>
								增加
							</button>
							<button type="button" onclick="addVideoClass()" title="修改"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
							</button>
							<button type="button" onclick="deleteWeChat()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除视频"  aria-hidden="true"></i>
								删除
							</button>
							<button type="button" id="relatedShow" title="二级分类列表" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-open"   aria-hidden="true"></i>
								子分类列表
							</button>	
							<button type="button" id="VideoListShow" title="分类的视频列表" class="btn btn-outline btn-default VideoListShow">
								<i class="glyphicon glyphicon-open"   aria-hidden="true"></i>
								视频列表
							</button>	
							<button type="button"  onclick="showVideoClassHot()" title="设置热门" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-open"   aria-hidden="true"></i>
								设置热门
							</button>
							<button type="button" onclick="setVideoClassHot();"  title="取消热门" class="btn btn-outline btn-default ">
								<i class="glyphicon glyphicon-trash"   aria-hidden="true"></i>
								取消热门
							</button>		
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-width="300" data-field="title">视频分类</th>
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
	
	
	<div class="modal" id="myModalVideoList2" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:600px">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >视频列表</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageVideo2"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="VideoListEventsToolbar2"
							role="group">
							<button onclick="AddVideoClassVideo(1)"
						class="btn btn-warning">确定</button>
						</div>
						<label style="display: none;" id="belongID"></label>
					<table id="VideoList2" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="pkid">编号</th>
									<th data-field="title">视频分类</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
		</div>
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
						<label style="display: none;" id="parentID"></label>
						<label style="display: none;" id="pkid"></label>
						<input type="text" class="form-control" placeholder="分类名称" id="reason"  /><br>
						<input type="text" class="form-control" placeholder="备注"id="num" /><br>
					</div>
					<br />
					<button id="addIntegration" class="btn btn-warning">确定增加</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	<div class="modal" id="myModalActivity" style="overflow: auto;margin-left: -200px"" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width:700px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">分类列表</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageStudent"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="StudentListEventsToolbar"
							role="group">
							<button onclick="addVideoClass2(1)"
						class="btn btn-warning">新增二级分类</button>
						<button onclick="addVideoClass2()"
						class="btn btn-outline btn-default">修改二级分类</button>
						<button onclick="deleteWeChat()"
						class="btn btn-outline btn-default">删除二级分类</button>
						<button id="VideoListShow1"
						class="btn btn-warning ">视频列表</button>
						</div>
						
					<table id="Activity" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">编号</th>
									<th data-field="title">视频分类</th>
									<th data-field="memo">备注</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal" id="myModalVideoList" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >视频列表</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageVideo"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="VideoListEventsToolbar"
							role="group">
							<button onclick="showVideoList(0)"
						class="btn btn-warning">移入分类</button>
						<button onclick="AddVideoClassVideo(0)"
						class="btn btn-outline btn-default">移出分类</button>
						<input type="hidden" id="VideoType" name="VideoType" value="0"/>
						</div>
					<table id="VideoList" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="pkid">编号</th>
									<th data-field="title">视频分类</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="myModalHot" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titles">设置热门Banner</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="HotType" name="HotType" value="0"/>
						<input type="text" onchange="changeImageUrl()"  class="form-control" placeholder="图片链接" id="imageUrl"  /><br>
						<img src="" id="hotImageBanner" style="width:200;height:100;" >
					</div>
					<br />
					<button id="addHot" onclick="setVideoClassHot()"  class="btn btn-warning">保存</button>
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
	var DataTable=$("#exampleTableEvents");
	var DataTable2=$("#Activity");
	var DataTable3=$("#VideoList");
	var DataTable4=$("#VideoList2");
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/VideoClass/getVideoClassList?sina=<%= SmBaseUtil.Random() %>&"+MakeQueryParam($(".example-wrap")),
					search : !0,
					formatSearch:function(){return "请输入视频分类名称"},
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					queryParams : queryParams,
					responseHandler: responseHandler,
					sidePagination : "server", //服务端请求
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			}()
			DataTable.on("dbl-click-row.bs.table", function(e, t, o) {
				addVideoClass();
			}).on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			})
			DataTable4.bootstrapTable({
				url : "<%=path%>/VideoClass/getVideoClassList?isend=1&sina=<%= SmBaseUtil.Random() %>&parentID=0",
				search : !0,
				formatSearch:function(){return "请输入视频名称"},
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
				toolbar : "#VideoListEventsToolbar2",
				icons : geticons
			});
			
			DataTable4.on("search.bs.table", function(e, t, o) {
				getQueryListForTab4(t);
			})
		
		}(document, window, jQuery);
		
		

		
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/VideoClass/deleteVideoClass",
						data : {
							'pid' : wids
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				}
			}

		}
		
		function deleteWeChat2(flag) {
			if(CheckhasChecked(DataTable2)==true){
				var wids = getRecordPKIDs(DataTable2);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/VideoClass/deleteVideoClass",
						data : {
							'pid' : wids
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
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			$("#searchText").val(searchText);
			var params=MakeQueryParam($(".example-wrap"));
			bindImgError();
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/VideoClass/getVideoClassList?sina=<%= SmBaseUtil.Random() %>&"+params
			});
			
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		
		function addVideoClass(type ){
			if(type==1){
				$("#myModal").modal("show");
				$("#titles").html("新增视频分类");
				$("#parentID").html(0);
				$("#pkid").html(0);
				$("#reason").val("");
				$("#num").val("");
			}else{
				if(CheckhasChecked(DataTable)==true){
					var t=DataTable.bootstrapTable("getSelections")[0];
					$("#myModal").modal("show");
					$("#titles").html(t['title']);
					$("#parentID").html(t['parentID']);
					$("#pkid").html(t['pkid']);
					$("#reason").val(t['title']);
					$("#num").val(t['memo']);
				}
			}
		}
		
		function addVideoClass2(type){
			var tt=DataTable.bootstrapTable("getSelections")[0];
			
			if(type==1){
				$("#myModalActivity").modal("hide");
				$("#myModal").modal("show");
				$("#titles").html("新增视频分类");
				$("#parentID").html(tt['pkid']);
				$("#pkid").html(0);
				$("#reason").val("");
				$("#num").val("");
			}else{
				if(CheckhasChecked(DataTable2,$("#ErrorMessageStudent"))==true){
					var t=DataTable2.bootstrapTable("getSelections")[0];
					$("#myModalActivity").modal("hide");
					$("#myModal").modal("show");
					$("#titles").html(t['title']);
					$("#parentID").html(t['parentID']);
					$("#pkid").html(t['pkid']);
					$("#reason").val(t['title']);
					$("#num").val(t['memo']);
				}
			}
		}
		function CheckhasChecked(obj,errobj){
			if(obj.bootstrapTable("getSelections").length<=0){
				ViewWarning(errobj);
				return false;
			}
			return true;
		}
		$("#addIntegration").click(function(){
			var parentID=$("#parentID").html();
			var id=$("#pkid").html();
			var title=$("#reason").val();
			var memo=$("#num").val();
			 $.ajax({
					url : "<%=path%>/VideoClass/addVideoClass",
					data : {
						'id' : id,
						'title' : encodeURI(title),
						'memo':memo,
						'parentID':parentID,
						'type':0
					},
					success : function(obj) {
						$("#myModal").modal("hide");
						var view=$("#ErrorMessage");
						if (obj.status) {
								
							if(obj.parentID!=0){
								$("#myModalActivity").modal("show");
								view=$("#ErrorMessageStudent");
							}
							view.text('处理成功!').removeClass("alert-warning")
							.addClass("alert alert-success");
							view.show();
							gotohidden(view);
						}else{
								view.text('处理失败!').addClass("alert-warning")
								.removeClass("alert alert-success");
								view.show();
								gotohidden(view);
						}
						
					}
				});

		})
		function AddVideoClassVideo(flag) {
			if(flag==1){
				if(CheckhasChecked(DataTable4,$("#ErrorMessageVideo2"))==false){
					return false;
				}
			}else{
				if(CheckhasChecked(DataTable3,$("#ErrorMessageVideo"))==false){
					return false;
				}
			}
			
				var wids = getRecordPKIDs(DataTable4);
				if(flag==0){
					wids = getRecordPKIDs(DataTable3);
				}
				belongID=$("#belongID").html();
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/VideoClass/AddVideoClassVideo",
						data : {
							'pid' : wids,
							'type':flag,
							'belongID':belongID
							
						},
						success : function() {
							if(flag==1){
								$("#myModalVideoList2").modal("hide");
								var type=$("#VideoType").val();
								if(type==1){
									$("#myModalActivity").modal("show");
									ViewSuccess($("#ErrorMessageStudent"));
								}else{
									$("#myModalVideoList").modal("show");
									ViewSuccess($("#ErrorMessageVideo"));
								}
							}else{
								ViewSuccess($("#ErrorMessageVideo"));
							}
							
						}
					});
				} 
			

		}
		
		$("#relatedShow").click(function(){
			if(CheckhasChecked(DataTable)==true){
				var t=DataTable.bootstrapTable("getSelections")[0];
				$.ajax({
					url : "<%=path%>/VideoClass/CheckVideoClassExistChild",
					data : {
						'belongID' : t['pkid'],
						'type':0
						
					},
					success : function(obj) {
						if(obj.result>0){
							ViewWarningError("该分类已经存在视频,无法添加子分类到该分类");
						}else{
							$("#myModal").modal("hide");
							$("#myModalActivity").modal("show");
							DataTable2.bootstrapTable({
								url : "<%=path%>/VideoClass/getVideoClassList?sina=<%= SmBaseUtil.Random() %>&parentID="+t['pkid'],
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
							DataTable2.bootstrapTable("refresh", {
								url : "<%=path%>/VideoClass/getVideoClassList?sina=<%= SmBaseUtil.Random() %>&parentID="+t['pkid'],
							});
						}
					}
				});
			}
		})
		
		$(".VideoListShow").click(function(){
			if(CheckhasChecked(DataTable)==true){
				var t=DataTable.bootstrapTable("getSelections")[0];
				 $("#parentID1").val(t['pkid']);
				$.ajax({
					url : "<%=path%>/VideoClass/CheckVideoClassExistChild",
					data : {
						'belongID' : t['pkid'],
						'type':1
						
					},
					success : function(obj) {
						if(obj.result>0){
							ViewWarningError("该分类已经存在子分类,无法添加视频到该分类");
						}else{
							$("#belongID").html(t['pkid']);
							$("#VideoType").val(0);
							$("#myModal").modal("hide");
							$("#myModalActivity").modal("hide");
							$("#myModalVideoList").modal("show");
							DataTable3.bootstrapTable({
								url : "<%=path%>/VideoClass/getVideoClassList?isend=1&sina=<%= SmBaseUtil.Random() %>",
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
								toolbar : "#VideoListEventsToolbar",
								icons : geticons
							});
							DataTable3.bootstrapTable("refresh", {
								url : "<%=path%>/VideoClass/getVideoClassList?isend=1&sina=<%= SmBaseUtil.Random() %>",
							});
						}
					}
				});
				
				
			}
		})
		
		
		$("#VideoListShow1").click(function(){
			if(CheckhasChecked(DataTable2,$("#ErrorMessageStudent"))==true){
				var t=DataTable2.bootstrapTable("getSelections")[0];
				 $("#parentID1").val(t['pkid']);
				$.ajax({
					url : "<%=path%>/VideoClass/CheckVideoClassExistChild",
					data : {
						'belongID' : t['pkid'],
						'type':1
						
					},
					success : function(obj) {
						if(obj.result>0){
							ViewWarningError("该分类已经存在子分类,无法添加视频到该分类");
						}else{
							$("#belongID").html(t['pkid']);
							$("#VideoType").val(1);
							$("#myModal").modal("hide");
							$("#myModalActivity").modal("hide");
							$("#myModalVideoList").modal("show");
							DataTable3.bootstrapTable({
								url : "<%=path%>/VideoClass/getVideoClassList?isend=1&sina=<%= SmBaseUtil.Random() %>",
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
								toolbar : "#VideoListEventsToolbar",
								icons : geticons
							});
							DataTable3.bootstrapTable("refresh", {
								url : "<%=path%>/VideoClass/getVideoClassList?isend=1&sina=<%= SmBaseUtil.Random() %>",
							});
						}
					}
				});
				
				
			}
		})
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				parentID : $("#parentID1").val()
			};

		}
		function showVideoList(){
			var t;
			var type=$("#VideoType").val();
			if(type==1){//分类列表
				if(CheckhasChecked(DataTable2,$("#ErrorMessageStudent"))==false){
					return false;
				}
				t=DataTable2.bootstrapTable("getSelections")[0];
				$("#myModalActivity").modal("hide");
				
			}else{
				t=DataTable.bootstrapTable("getSelections")[0];
			}
			$("#myModalVideoList").modal("hide");
			$("#belongID").html(t['pkid']);
			$("#myModalVideoList2").modal("show");
			
			getQueryListForTab4();
		}
		function getQueryListForTab4(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			DataTable4.bootstrapTable("refresh", {
				url : "<%=path%>/VideoClass/getVideoClassList?searchText="+ searchText +"&isend=1&sina=<%= SmBaseUtil.Random() %>&parentID=0",
			});
		}
		
		function setVideoClassHot(type){
			var t=null;
			if(type==1){//
				if(CheckhasChecked(DataTable2,$("#ErrorMessageStudent"))==false){
					return false;
				}
				t=DataTable2.bootstrapTable("getSelections")[0];
			}else{
				if(CheckhasChecked(DataTable)==false){
					return false;
				}
				t=DataTable.bootstrapTable("getSelections")[0];
			}
			var imageurl=t['imageUrl'];
			var type = t['type'];
			if(t['type']==0){
				type = 1;
				if($("#imageUrl").val()!=""){
					imageurl = $("#imageUrl").val();
				}
				
			}else{
				type =0;
			}
			$.ajax({
				url : "<%=path%>/VideoClass/HotVideoClass",
				data : {
					'id' : t['pkid'],
					'type' : type,
					'imageUrl':imageurl
					
				},
				success : function(obj) {
					if(obj.result>0){
						ViewSuccess();
						$('#myModalHot').modal('hide');
					}else{
						if(type==1){
							ViewWarningError("处理失败",$("#ErrorMessageStudent"));
						}else{
							ViewWarningError("处理失败");
						}
						
					}
				}
			});
			
		}
		
		function showVideoClassHot(){
			
			if(CheckhasChecked(DataTable)==false){
				return false;
			}
			var t=DataTable.bootstrapTable("getSelections")[0];
			$('#myModalHot').modal('show');
			$("#imageUrl").val(t['imageUrl']);
			$("#hotImageBanner").attr("src",$("#imageUrl").val());
			$("#hotImageBanner").attr("width",200);
			$("#hotImageBanner").attr("height",100);
			
		}
		
		function changeImageUrl(){
			$("#hotImageBanner").attr("src",$("#imageUrl").val());
			$("#hotImageBanner").attr("width",200);
			$("#hotImageBanner").attr("height",100);
			
		}
		function dispErrorRow(row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.type == 1) {
				strclass = 'warning';//还有一个active
			}else {
				return {};
			}
			return {
				classes : strclass
			}
		}
	</script>
</body>
</html>