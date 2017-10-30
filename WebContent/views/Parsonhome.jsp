<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	GregorianCalendar gc = new GregorianCalendar();
	gc.setTime(new Date());
	gc.add(2, -1);
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
	String Date = format.format(gc.getTime());
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	Users user =(Users)request.getSession().getAttribute("UserName");
	Boolean AreaManage= (user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid());
	request.setAttribute("AreaManage", AreaManage);
	
%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico">
<link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min.css?v=4.0.0" rel="stylesheet">
<style>
.textbody {
	padding-bottom: 15px;
	padding-top: 5px;
	cursor: pointer;
	color: #676a6c;
}

.textbody:hover {
	padding-bottom: 15px;
	padding-top: 5px;
	cursor: pointer;
	color: #18a689;
}
</style>
</head>
<input type="hidden" name="pageSize" id="pageSize" value="8" />
<input type="hidden" name="pageNumber" id="pageNumber" value="0" />
<input type="hidden" id="total" value="${total}" />
<body class="gray-bg">

	<div class="wrapper wrapper-content">
	
		<div class="row">
			<div class="col-sm-12">

				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>系统公告</h5>
					</div>
					<div class="ibox-content">
						<c:if test="${data.size()<=0}">
							<div class="feed-element">暂无最新的公告信息</div>
						</c:if>
						<c:if test="${data.size()>0}">
							<div>
								<div class="feed-activity-list" id="NoticesList">


									<c:forEach var="Data" items="${data}">
										<div class="feed-element textbody">
											<div class="media-body"
												onclick="window.open('<%=basePath%>noticesdetail?pid=${Data.PKID}&v=4.0');     ">
												<small class="pull-right">${Data.createTime.substring(0,10)}</small>
												<span>${Data.title}</span>
											</div>
										</div>
									</c:forEach>
									<div style="float: right;" class="m-t">
										<ul class="pagination pagination-outline">

											<li class="page-pre"><a href="javascript:prePage()">上一页</a></li>
											<li class="page-pre disabled"><a id="CurrentPage"
												href="javascript:void(0)">1/100</a></li>
											<li class="page-next"><a href="javascript:nextPage()">下一页</a></li>
										</ul>
									</div>

								</div>
							</div>
						</c:if>
					</div>

				</div>
			</div>


		</div>
	</div>
	
	<script src="<%=basePath%>js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=basePath%>js/wechat_index.js?v=1.0.2"></script>
	<script type="text/javascript">
		initPageControl($("#total").val());
		function prePage(){
			 var currentpage=parseInt($("#pageNumber").val())-parseInt($("#pageSize").val());
			 if(currentpage<0){
				 currentpage=0;
			 }
			 $("#pageNumber").val(currentpage);
			 getNoticesList();
		 }
		 function nextPage(){
			 var currentpage=parseInt($("#pageNumber").val())+parseInt($("#pageSize").val());
			 $("#pageNumber").val(currentpage);
			 getNoticesList();
		 }
		 function getNoticesList(){
				
				var buttomhtml='<div style="float:right;"><ul class="pagination pagination-outline" ></li><li class="page-pre"><a href="javascript:prePage()">上一页</a></li><li class="page-pre disabled"><a id="CurrentPage" href="javascript:void(0)">1/100</a></li><li class="page-next"><a href="javascript:nextPage()">下一页</a></li></ul></div>';
				var htmlStr='';
					$.getJSON("<%=path %>Notices/getNoticesList", {
						type : 1,
						pageSize : $("#pageSize").val(),
						pageNumber : $("#pageNumber").val()
					}, function(json) {
						htmlStr+='';
						if (json && json.Status == 1) {//
							if (json.data && json.data.length > 0) {
								
								$.each(json.data, function(index, array) {
									var url="'<%=basePath%>noticesdetail?pid=" + array.pkid + "&v=4.0'";
							htmlStr += '<div class="feed-element textbody">';
							htmlStr += '<div class="media-body" onclick="window.open(' + url + ');     ">';
							htmlStr += '<small class="pull-right">' + array.createTime.substr(0, 10) + '</small>';
							htmlStr += '<span>' + array.title + '</span>';
							htmlStr += '</div> </div>';
						});

					}
					htmlStr += '';
					$("#NoticesList").html(htmlStr + buttomhtml);

					initPageControl(json.total);
				}
			});

		}
	</script>
<!-- 友盟统计代码 begin -->
<%@ include file="../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>
</html>