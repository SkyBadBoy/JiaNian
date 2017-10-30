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
.posi_re {
    position: relative;
    height: 100px;
    overflow: hidden;
}
</style>
</head>
<body style="background-color: #fff">
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
	<div class="viewport">
		<c:if test="${ImageCount>0}">
		<div id="module_1" class="swiper-container">
            <div class="swiper-wrapper" id="gallery">
            	<c:forEach var="Banner" items="${ImageList}">
            		<c:if test="${Banner.type eq 0}">
						  <div class="two swiper-slide" style="height:200px;overflow: hidden;"><a href="<%=path%>/Product/phoneweChatPordDetail?_type_=3&_pid_=${Banner.PKID}&_pc_=${_pc_}&_area_=${_area_}&?sina=<%=SmBaseUtil.Random() %>"><img
	                        src="${Banner.imageUrl}"  class="img_response"/></a></div>
                     </c:if>
                     <c:if test="${Banner.type eq 1}">
						  <div class="two swiper-slide" style="height:200px;overflow: hidden;"><a href="<%=path%>/weChatGroup/phoneweChatGroupList?videoType=${Banner.PKID}&type=3&_area_=${_area_}&sina=<%=SmBaseUtil.Random() %>"><img
	                        src="${Banner.imageUrl}"  class="img_response"/></a></div>
                     </c:if>
				</c:forEach>
            </div>
           <c:if test="${ImageCount>1}"><div class="swiper-pagination"></div></c:if>
        </div>
        </c:if>
        <c:if test="${ImageCount>1}">
	        <script>
	            var swiper = new Swiper('#module_1.swiper-container', {
	                pagination: '.swiper-pagination',
	                paginationClickable: true,
	                loop: true,
	                loopAdditionalSlides: 0,
	                autoplay: 10000, //可选选项，自动滑动
	                autoplayDisableOnInteraction: false
	            });
	        </script>
       </c:if>
       <ul class="mui-table-view mui-grid-view" id="container">
			<c:forEach var="Part" items="${PartList}">
				<c:if test="${Part.video!=null}">
					    <li class="mui-table-view-cell mui-media mui-col-xs-6">
					        <a href="<%=path%>/Product/phoneweChatPordDetail?_type_=3&_pid_=${Part.video.PKID}&_pc_=${_pc_}&_area_=${_area_}&?sina=<%=SmBaseUtil.Random() %>">
					            <div class="posi_re">
					                <img class="mui-media-object" src="${Part.video.image.url.split(',')[0]}">
					                <div class="video_bk">${Part.title}</div>
					                <span class="video-btn"></span>
					            </div>
					            <div class="mui-media-body">${Part.video.areaName}</div>
					            <div class="mui-media-body">${Part.video.student.name}</div>
					            <div class="mui-media-body"><span class="vide0_play"><img src="<%=basePath%>images/play.png">${Part.video.clickCount}次</span><span class="vide0_zan"><img src="<%=basePath%>images/zan.png">${Part.video.likeCount}个</span></div>
					        </a>
					    </li>
				</c:if>
			</c:forEach>
		</ul>
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
						name: $("#name").val(),
						videoType:getQueryString('videoType')
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
								AddNoMoreDOM();
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