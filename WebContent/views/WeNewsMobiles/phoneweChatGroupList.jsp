<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html lang="zh-cn">
<head>
<title>${Title}</title>
<jsp:include page="/include/commonMobileCss.jsp"></jsp:include>
<style type="text/css">
._image_100{
        border-radius: 50%;
    }
.list_dzBtn img  {
    width: 15px;
    height: 15px;
    margin: -5px 5px 0 0;
    }
    .list_read  {
    width: 15px;
    height: 15px;
    margin: -5px 5px 0 0;
    }
    .list_comment {
    width: 15px;
    height: 15px;
    margin: -5px 5px 0 0;
    }
   .index_footer_new img{
    width: 15px;
    height: 15px;
    margin: -5px 5px 0 0;
   }
 .mulitlinecontent{
   	height: 220px;padding-left: 10px;padding-right: 5px;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3; 
   }
</style>
</head>
<body>
<div class="header ">
    <a class="header_left" href="#menu"></a>
    <a href="<%=basePath%>Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random() %>" style="padding-top:35px;"><img src="${imageurl}" class="header_img" style="margin-top: 12.5px;"></a>
    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?AreaId=<%=user.getID() %>&SID=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random() %>">
        <span>我要投稿</span>
        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
    </a>
</div>
<div style="height: 20px;"></div>
<div class="mt56" >
	<div class="viewport" id="container">

		<c:forEach var="Part" items="${PartList}">
			<c:if test="${Part.noticesList!=null}">
				<div class="index_activity">
					 <a href="<%=path%>/Product/phoneweChatPordDetail?_type_=2&_pid_=${Part.noticesList.PKID}&_pc_=${_pc_}&_area_=${_area_}&_status_=1&sina=<%=SmBaseUtil.Random() %>">
					        <div class="list-header list-media"><img class="_image_100" src="${Part.noticesList.student.imageUrl.split(',')[0]}"><div class="list-body index_title">${Part.noticesList.author}&nbsp;&nbsp;&nbsp;&nbsp;${Part.noticesList.SRegion.name}<p>${Part.noticesList.createTime.substring(0,19)}</p></div></div>
					        <div class="index_title">${Part.title}</div>
					        <!--<span class="index_title">我的G20垃圾分类我先行</span>-->
					         <c:if test="${Part.noticesList.image.ID!=0 }">
					        	<div style="height: 220px;overflow: hidden;"><img src="${Part.noticesList.image.url.split(',')[0]}" style="width: 100%"></div>
					        </c:if>
					        <c:if test="${Part.noticesList.image.ID==0 }">
					        	<div class="mulitlinecontent" >${Part.noticesList.content}</div>
					        </c:if>
					    </a>
					    <div class="index_footer_new">
					        <div class="index_name"><div class="list_dzBtn"><a href="javascript:;"><img src="<%=basePath%>images/boforeJob.png"><span class="go_gref">${Part.noticesList.like}</span></a></div></div>
					        <span class="index_name"><img class="list_read" src="<%=basePath%>images/readNews.png">${Part.noticesList.clickCount}</span>
					        <span class="index_read index_read_bg"  data-toggle="modal" ><img class="list_comment" src="<%=basePath%>images/comment.png">${Part.noticesList.commentCount}</span>
					    </div>
					</div>
			</c:if>
			<c:if test="${Part.activity!=null}">
			<a class="posi_relative" href="<%=path%>/Activity/phoneSinaActivityDetail?aid=${Part.activity.PKID}&_pc_=${_pc_}&_area_=${_area_}&sina=<%=SmBaseUtil.Random() %>">
		        <img class="img_response" style="width: 100.000%;height:220px" src="${Part.activity.image.url.split(',')[0]}" alt="">
					<div limit="17" class="list_activity_mymatch">${Banner.title}</div>
					<div class="list_activity_myfinish">进入报名</div>
                    <div class="index_mybg" style="width: 100.000%;height:220px"></div>
		    </a>
			</c:if>
			<c:if test="${Part.video!=null}">
				<div class="index_activity">
				
				    <a href="<%=path%>/Product/phoneweChatPordDetail?_type_=3&_pid_=${Part.video.PKID}&_pc_=${_pc_}&_area_=${_area_}&?sina=<%=SmBaseUtil.Random() %>">
				        <div class="index_title">
				            <span limit="17" class="index_title_name">${Part.title}</span>
				            <span class="index_title_read">播放量:${Part.video.clickCount}次</span>
				        </div>
				        <img src="${Part.video.image.realUrl}" style="width:100%"></div>
				        <div class="video_mymatch"><img src="<%=basePath%>images/weixinwenshe/play.png" style="width: 50px;padding-top: 50px;"></div>
				        <div class="video_myfinish">${Part.createTime} </div>
				        <div class="video_mybg"></div>
				    </a>
				</div>
			</c:if>
		</c:forEach>
		<c:if test="${PartListCount==0}">
			<br/>
			<h3 style="text-align: center;" >近期暂无最新的信息~</h3>
			<br/>
		</c:if>
	</div>
	</div>
<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
	<input type="hidden" id="_page_" value="${_page_}">
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<input type="hidden" id="type" value="${type}">
	<input type="hidden" id="_area_" value="${_area_}">
	<input type="hidden" id="_pc_" value="${_pc_}">
	<input type="hidden" id="name" value="${name}">
	<input type="hidden" id="_isStu_" value="${_isStu_}">
	<jsp:include page="/include/commonMobileJs.jsp" />
	<script type="text/javascript">
		$(function() {
			var i = 1; //设置当前页数 
			$(window).scroll(
			function() {
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					$("#container").append(getLoading()); 
					$.getJSON("<%=path%>/weChatGroup/getWeNewsPaJsonrtList",
					{
						_page_ : $('#_page_').val(),
						_area_ : $("#_area_").val(),
						_pc_ : $("#_pc_").val(),
						_isStu_:$("#_isStu_").val(),
						type : $("#type").val(),
						name: $("#name").val()
					},
					function(json) {
						if (json && json.Status == 1) {
							$('#_page_').val(json._page_);
							$.each(json.Data,
								function(index, array) {
									var area=$("#_area_").val();
			                    	var basePath=$("#basePath").val();
			                    	var pc=$("#_pc_").val();
			                    	var str=getNewProductAndActivityDOM(array,area,basePath,pc);
									$("#container").append(str);
								});
							if(json.Data.length<=0){
								AddNoMoreDOM()
							}else{
								var _page_ = $('#_page_').val();
								$('#_page_').val(parseInt(_page_) + 1);
								$(".sk-spinner").remove();
								$("#container").append(getLoading());
								
							}
						} else {
							AddNoMoreDOM()
							return false;
						}
						bindImgError();
					});
				}
			});
		});
		$(function () {
	        $('nav#menu').mmenu();
	    });
	</script>
</body>
</html>