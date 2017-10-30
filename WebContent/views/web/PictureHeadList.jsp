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
					<h4 class="modal-title">图片链接的编辑</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<input
								type="text" class="form-control" placeholder="请输入图片标题"
								id="Title" name="Title" /> 
								<input
								type="text" class="form-control" placeholder="请输入url地址" style="margin-top: 10px;"
								id="urlAddress" name="urlAddress" /> 
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
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>图片列表</strong></li>
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
					<input type="hidden" id="WeChatID" name="WeChatID" iscon="true" value="${WeChatID}"/>
					<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="javascript:window.location.href='<%=path%>/ProdPicture/addProdPicture?type=<%=SmBaseGlobal.PictureType.Banner.getid() %>'"
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
									<th data-width="100" data-field="tempUrl">图片文件</th>
									<th data-field="title">图片标题</th>
									<th data-field="realUrl">图片</th>
									<th data-width="200" data-field="isEnables">图片状态</th>
									
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
					url : "<%=path%>/ProdPicture/getProdPictureList?sina=<%= SmBaseUtil.Random() %>&"+MakeQueryParam($(".example-wrap"))+"&&type=<%=SmBaseGlobal.PictureType.Banner.getid() %>",
					/*search : !0,*/
					formatSearch:function(){return "请输入微信公众号名称"},
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
						url : "<%=path%>/ProdPicture/deleteProdPictures",
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
		function enableProdPrivate() {
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true){
				var purl=t['purl'];
				var title=t['title'];
				$("#urlAddress").val(purl);
				$("#Title").val(title);
				$('#myModal').modal('show');
				
			}

		}
		function cancelProdPrivate() {
			var t=DataTable.bootstrapTable("getSelections")[0];
			if(CheckhasChecked(DataTable)==true){
				$.ajax({
					url : "<%=path%>/ProdPicture/enableProdPrivate",
					data : {
						'pid' : t['pkid'],
						'enable':0
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
				url : "<%=path%>/ProdPicture/getProdPictureList?rand=<%= SmBaseUtil.Random() %>&"+params+"&&type=<%=SmBaseGlobal.PictureType.Banner.getid() %>"
			});
			
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		
		$("#referWechat").click(function(){
			
			var t=DataTable.bootstrapTable("getSelections")[0];
				$.ajax({
					url : "<%=path%>/ProdPicture/enableProdPrivate",
					data : {
						'pid' : t['pkid'],
						'enable':1,
						'PUrl':$("#urlAddress").val(),
						'title':$("#Title").val(),
						'rand':Math.random()
					},
					success : function() {
						ViewSuccess();
						getQueryList();
						$('#myModal').modal('hide');
					}
				});
			
			
		});
		function dispErrorRow(row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.isEnables == "禁用") {
				//strclass = 'danger';//还有一个active
			} else {
				strclass = 'warning';//还有一个active
			}
			return {
				classes : strclass
			}
		}
	</script>
</body>
</html>