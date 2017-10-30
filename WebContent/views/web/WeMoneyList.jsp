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
<input type="hidden"  id="id" value="${id}" />
	<div class="container-fluid">

		
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<input type="hidden" id="AreaID" name="AreaID" value="${AreaID}"/>
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">						
							<!--  button type="button" title="新闻列表" onclick="studentsNewsView()" class="btn btn-outline btn-default">
								<i  title="新闻列表"  aria-hidden="true"></i>
								新闻列表
							</button-->
							&nbsp;&nbsp;&nbsp;该学生微米拥有数：<span style="font-size: 40px;" id="wemoneyNum">${WeMoney}</span>
							
							<button type="button" title="添加微米" id='IntegrationShow' class="btn btn-outline btn-default">
								<i  title="添加微米"  aria-hidden="true"></i>
								添加微米
							</button>
							
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight-250 %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="reson">原因</th>
								 	<th data-field="weMoney">微米</th>
									<th  data-field="createTime">时间</th>
									
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
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titles"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label style="display: none;" id="pkid"></label>
						<input type="text" class="form-control" placeholder="请输入增加原因" id="reason"  /><br>
						<input type="text" class="form-control" placeholder="请输入微米数"id="num" /><br>
					</div>
					<br />
					<button id="addIntegration" class="btn btn-warning">确定增加</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
		var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/WeMoney/getWeMoneyRecordPCList",
					search : 0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入姓名或者手机号"},
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
				
			}();
		}(document, window, jQuery);

function getQueryList(){
	DataTable.bootstrapTable("refresh", {
		url : "<%=path%>/WeMoney/getWeMoneyRecordPCList",
	});
}
function queryParams(params) {
	return {
		pageSize : params.limit,
		pageNumber : params.offset,
		id:$("#id").val(),
		sina: Math.random()
	};

}
$("#IntegrationShow").click(function(){
	$("#myModal").modal("show");
	$("#titles").html('添加微米');
})

$("#addIntegration").click(function(){
	var id=$("#id").val();
	var title=$("#reason").val();
	var num=$("#num").val();
	 $.ajax({
			url : "<%=path%>/Students/addWeMoney",
			data : {
				'id' : id,
				'title' : encodeURI(title),
				'num':num,
				'sina':'<%=SmBaseUtil.Random()%>'
			},
			success : function(obj) {
				$("#myModal").modal("hide");
				var view=$("#ErrorMessage");
				if (obj.Status) {
						view.text('微米增加成功!').removeClass("alert-warning")
						.addClass("alert alert-success");
						view.show();
						gotohidden(view);
						getQueryList();
						$("#wemoneyNum").text(parseFloat($("#wemoneyNum").text())+parseFloat(num));
				}else{
						view.text('微米增加失败!').removeClass("alert-success")
						.addClass("alert alert-warning");
						view.show();
						gotohidden(view);
				}
				
			}
			
		});

})

	</script>
</body>
</html>