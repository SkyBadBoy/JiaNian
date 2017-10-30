<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
    String rootPath=getClass().getResource("/").getFile().toString();
    rootPath=rootPath.replace("classes/", "");
    rootPath=rootPath.replace("WEB-INF/", "");
    System.out.println(rootPath+"图片");
%>
<html>

<head>
	<jsp:include page="/include/commonCss.jsp" />
</head>
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
<body>
	<div class="container-fluid row">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>新闻图片列表</strong></li>
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
					<input type="hidden" id="shang" name="shang" value="${shang}"/>
					<input type="hidden" id="key" name="key" value="${key}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" 
								onclick="javascript:window.location.href='<%=path%>/ProdPicture/addProdPicture?type=1'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="增加图片" aria-hidden="true"></i>
								增加
							</button>
							
							<button type="button" onclick="deleteWeChat()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除图片"  aria-hidden="true"></i>
								删除
							</button>	
							<c:if test="${ not empty shang}">
								<button type="button" onclick="senda()" class="btn btn-outline btn-default">
									<i class="glyphicon " title="上传此图片"  aria-hidden="true"></i>
									上传此图片
								</button>
								<c:if test="${shang==2}">
									当前规定：${key }
								</c:if>
							</c:if>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-width="100" data-field="tempUrl">图片文件</th>
									<th data-field="realUrl">图片</th>
									<th data-width="200" data-field="weChat.company">所属微信号</th>
									
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
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/ProdPicture/getProdPictureList",
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
		function getQueryList(searchText){
			bindImgError();
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/ProdPicture/getProdPictureList"
			});
			
		}
		function queryParams(params) {
			var temp=MakeQueryParamJson($(".panel"));
			temp.pageSize=params.limit;
			temp.pageNumber=params.offset;
			temp.check=$(".search input").val();
			temp.sina='<%= SmBaseUtil.Random() %>';
			temp.TypeNormal=1;
			temp.soft='time';
			return temp;


		}
		
		
		
		function senda()
		{
			if(CheckhasChecked(DataTable)==true){
				var t=DataTable.bootstrapTable("getSelections")[0];
				if($("#shang").val()==1)
				{
					$.ajax({
						url : "<%=path%>/WeChatCustom/GroupSend",
						type : "post",
						data : {
							'type':'image',
							'pic':"<%=rootPath%>"+t['url']
				
						}, 
					});
				}
				if($("#shang").val()==2)
				{
					$.ajax({
						url : "<%=path%>/WeChatCustom/Rules?sina=<%= SmBaseUtil.Random() %>",
						type : "get",
						data : {
							'pic':"<%=rootPath%>"+t['url'],
							'key':encodeURI($("#key").val()),
							'rules':'image'
						}, 
					});
						
				}
	
			}
		}
	
	</script>
</body>
</html>