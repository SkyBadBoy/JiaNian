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
	if(Status!=null && !Status.isEmpty() ){
		isApply=(Integer.parseInt(Status)==SmBaseGlobal.CheckStatus.Apply.getid());
	}
	request.setAttribute("isApply", isApply);
	request.setAttribute("Level", user.getLevel());
	
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />

<style type="text/css">
#Layer1 {
    width: 450px;
    margin:-120px;
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
    background-color:#000;
    position:absolute;
    z-index:49;
    display:none;
    width:100%;
    height:100%;
    opacity:0.6;
    filter: alpha(opacity=60);
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
    margin-top:45px;
    margin-left: -230px;
    z-index: 9997;
    width: 402px;
    height: 603px;
    background: url("<%=basePath%>img/phone2.png") 0 0 no-repeat;
}


#liulan{
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

</style>

</head>

<body>


<div class="container-fluid">
<input type="hidden" name="NewsID" value="${NewsID}" id="NewsID"  />
		<div id="shade">
		</div>
			<div id="Layer1">
			  <div id="iphone">
			  		<div id="liulan">
			  		</div>
                   	<div>
                       	<ul	class="view_list list-unstyled">
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
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" onclick="deleteWeChat(88)" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"  aria-hidden="true"></i>
								删除
							</button>
							<button type="button" onclick="preview()"  class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil"  title="预览"  aria-hidden="true"></i>
								预览
							</button>
						</div>
							<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight-250 %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-width="100" data-field="image.purl">新闻配图</th>
										<th data-width="200" data-field="title">新闻标题</th>
										<th data-field="content">内容</th>						
										<th data-width="150" data-field="createTime">创建日期</th>
										<th data-width="60" data-field="author">作者</th>
										<th data-width="60" data-field="clickCount">阅读量</th>
										<th data-width="60" data-field="like">点赞数</th>
										<th data-width="60" data-field="voteCount">投票数</th>
										<th data-width="60" data-field="commentCount">评论数</th>
									</tr>
								</thead>
							</table>
					</div>
				</div>
				<!-- End Example Events -->
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
					
					url : "<%=path%>/Students/getStudentsNewsList?sina=<%= SmBaseUtil.Random() %>&id=${id}",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入新闻标题"},
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
		}(document, window, jQuery);

		function modifyProduct() {
			if(CheckhasChecked(DataTable)==true){
				var url='<%=path%>/Notices/addNews?type=2&&pid=';
				viewOrModifyByPKID(DataTable,url);
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
			var NewsID="";
			if($("#NewsID").val()!=""){
				NewsID=$("#NewsID").val();
				$("#NewsID").val("");
			}
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Students/getStudentsNewsList?sina=<%= SmBaseUtil.Random() %>&id=${id}&&title="+encodeURI(encodeURI(searchText))
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

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
		$(".demo4").click(function(){
			swal({
				title:"您确定要删除这条信息吗",
				text:"删除后将无法恢复，请谨慎操作！",
				type:"warning",
				showCancelButton:true,
				confirmButtonColor:"#DD6B55",
				confirmButtonText:"是的，我要删除！",
				cancelButtonText:"让我再考虑一下…",
				closeOnConfirm:false,
				closeOnCancel:false},
				function(isConfirm){if(isConfirm){
					swal("删除成功！","您已经永久删除了这条信息。","success")
				}else{
					swal("已取消","您取消了删除操作！","error")
				}})
		});
	</script>
</body>
</html>