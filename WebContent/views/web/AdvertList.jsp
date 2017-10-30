<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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


	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">广告编辑</h4>
				</div>
				<div class="modal-body">
					<div class="row">
					<label>广告标题</label>
						<input
						type="text" class="form-control" placeholder="请输入图片标题"
						id="title" name="title" /> 
						<label>跳转链接</label>
						<input
						type="text" class="form-control" placeholder="请输入url地址" style="margin-top: 10px;"
						id="url" name="url" /> 
						<label>广告位置</label>
						<select class="form-control m-b"  id="type"
							name="type">
							<option value="<%= SmBaseGlobal.AdvertType.APPHome.getid() %>"><%= SmBaseGlobal.AdvertType.APPHome.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.AppLoading.getid() %>"><%= SmBaseGlobal.AdvertType.AppLoading.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.NewDetail.getid() %>"><%= SmBaseGlobal.AdvertType.NewDetail.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.PCBannerLeft.getid() %>"><%= SmBaseGlobal.AdvertType.PCBannerLeft.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.PCBannerQR.getid() %>"><%= SmBaseGlobal.AdvertType.PCBannerQR.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.PCBannerRight.getid() %>"><%= SmBaseGlobal.AdvertType.PCBannerRight.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.UserCenterBanner.getid() %>"><%= SmBaseGlobal.AdvertType.UserCenterBanner.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.PCHomeLogo.getid() %>"><%= SmBaseGlobal.AdvertType.PCHomeLogo.getName() %></option>
							<option value="<%= SmBaseGlobal.AdvertType.PCSchoolBanner.getid() %>"><%= SmBaseGlobal.AdvertType.PCSchoolBanner.getName() %></option>
						</select>
						<label>广告指数</label>
						<input
						type="text" class="form-control"  placeholder="请输入广告指数" style="margin-top: 10px;"
						id="index" name="index" /> 
						<label>开始时间</label>
						<input type="date" class="form-control" id="begin_time" name="begin_time" placeholder="请选择开始时间" />	
						<label>结束时间</label>
						<input type="date" class="form-control" id="end_time" name="end_time" placeholder="请选择结束时间" />	
					</div>
					<br />
					<button id="referWechat" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>


	
	
	
	
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				
			</div>
			<br/>
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<input type="hidden" id="searchText" name="searchText" iscon="true" value="${searchText}"/>
					<input type="hidden" id="WeChatID" name="WeChatID" iscon="true" value="${WeChatID}"/>
					<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="javascript:window.location.href='<%=path%>/ProdPicture/addProdPicture?type=<%=SmBaseGlobal.PictureType.Advert.getid() %>'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="增加图片" aria-hidden="true"></i>
								增加
							</button>
							
							<button type="button" onclick="deleteWeChat()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除图片"  aria-hidden="true"></i>
								删除
							</button>	
							<button type="button" 
								onclick="enableProdPrivate()"
								class="btn btn-outline btn-default">
								<i class="glyphicon " title="启用" aria-hidden="true"></i>
								启用
							</button>
							<button type="button" 
								onclick="cancelProdPrivate()"
								class="btn btn-outline btn-default">
								<i class="glyphicon " title="禁用" aria-hidden="true"></i>
								禁用
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-width="100" data-field="pkid">ID</th>
									<th data-width="100" data-field="imageurl_a">广告图片</th>
									<th data-field="title">广告标题</th>
									<th data-field="begin_time">开始时间</th>
									<th data-field="end_time">结束时间</th>
									<th data-width="200" data-field="url">跳转链接</th>
									<th data-width="100" data-field="type_name">广告位置</th>
									<th data-width="100" data-field="status_name">广告状态</th>
									
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
	$("input:radio[name=Type]").change(function(){
		 var $selectedvalue = $("input[name=Type]:checked").val();
		 console.log($selectedvalue);
		 if($selectedvalue==1){
			 $("#activityHtml").css('display','block'); 
		 }else{
			 $("#activityHtml").css('display','none'); 
		 }
	})
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Advert/getAdvert?"+MakeQueryParam($(".example-wrap"))+"",
					/*search : !0,*/
					formatSearch:function(){return "请输入广告名称"},
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
			DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList(t);
				})
		}(document, window, jQuery);

		
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Advert/deleteAdvert",
						data : {
							'pid' : wids,
							'status':<%= SmBaseGlobal.CheckStatus.Disabled.getid() %>
							
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				}
			}
		}
		function enableProdPrivate() {
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true){
				$("#url").val(t['url']);
				$("#title").val(t['title']);
				$("#type").val(t['type']);
				$("#index").val(t['index']);
				$("#begin_time").val((t['begin_time']!=null && t['begin_time']!="")?t['begin_time'].substr(0,10):t['begin_time']);
				$("#end_time").val((t['end_time']!=null && t['end_time']!="")?t['end_time'].substr(0,10):t['end_time']);
				$('#myModal').modal('show');
				
			}

		}
		function cancelProdPrivate() {
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true){
				$.ajax({
					url : "<%=path%>/Advert/deleteAdvert",
					data : {
						'pid' : t['pkid'],
						'status':<%= SmBaseGlobal.CheckStatus.Apply.getid() %>
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
			$("#searchText").val(searchText);
			var params=MakeQueryParam($(".example-wrap"));
			bindImgError();
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Advert/getAdvert?"+params+""
			});
			
		}
		function queryParams(params) {
			var sina=Math.random();
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				sina : sina
			};

		}
		
		$("#referWechat").click(function(){
			
			var t=DataTable.bootstrapTable("getSelections")[0];
				$.ajax({
					url : "<%=path%>/Advert/addAdvert",
					data : {
						'pid' : t['pkid'],
						'url':$("#url").val(),
						'title':$("#title").val(),
						'type':$("#type").val(),
						'index':$("#index").val(),
						'begin_time':$("#begin_time").val(),
						'end_time':$("#end_time").val(),
						'rand':Math.random()
					},
					success : function(json) {
						if(json.status){
							ViewSuccess();
							getQueryList();
							$('#myModal').modal('hide');
						}else{
							ViewWarning(json.message);
						}
						
					}
				});
			
			
		});
		function dispErrorRow(row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.status_name == "已发布") {
				strclass = 'warning';//还有一个active
			} else {
				
			}
			return {
				classes : strclass
			}
		}
	</script>
</body>
</html>