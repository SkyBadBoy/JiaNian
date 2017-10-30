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
					<li><strong>视频列表</strong></li>
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
								onclick="javascript:window.location.href='<%=path%>/Video/addVideo?rand=<%=SmBaseUtil.getRandomString(5) %>'"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="增加视频" aria-hidden="true"></i>
								增加
							</button>
							<button type="button" onclick="modifyWeChat()" title="修改"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
							</button>
							<button type="button" onclick="deleteWeChat()" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除视频"  aria-hidden="true"></i>
								删除
							</button>	
							<button type="button" onclick="setTop(1)" title="热门视频会更新到视频列表的广告位上" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-open"   aria-hidden="true"></i>
								热门视频
							</button>
							<button type="button" onclick="setTop(0)" title="取消热门" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash"   aria-hidden="true"></i>
								取消热门
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-width="300" data-field="title">视频文件</th>
									<th data-field="tempUrl">视频</th>
									<!--  th data-width="200" data-field="weChat.company">所属微信号</th-->
									
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
	var SchoolDataTable=$("#School");
	var StudentDataTable=$("#Student");
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Video/getVideoList?sina=<%= SmBaseUtil.Random() %>&"+MakeQueryParam($(".example-wrap")),
					search : !0,
					formatSearch:function(){return "请输入视频文件名称"},
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
				modifyWeChat();
			}).on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			})
		}(document, window, jQuery);
		
		

		
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Video/deleteVideo",
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
				url : "<%=path%>/Video/getVideoList?sina=<%= SmBaseUtil.Random() %>&"+params
			});
			
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		//修改产品
		function modifyWeChat() {
			if (CheckhasChecked(DataTable) == true) {
				var url = '<%=path%>/Video/addVideo?pid=';
				viewOrModifyByPKID(DataTable, url);
			}
		}
		
		function setTop(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "<%=path%>/Video/HotVideo",
						data : {
							'pid' : wids,
							'type':flag
							
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				} 
			}

		}
		
		function dispErrorRow(row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.baseInfo.name == "删除") {
				strclass = 'danger';//还有一个active
			} else if (row.hot == 1) {
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