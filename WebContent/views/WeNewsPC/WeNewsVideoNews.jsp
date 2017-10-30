<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String img= basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
	request.setAttribute("img", img);
%>
<html lang="en">
<head>
    <title>视频新闻</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
<body>
<jsp:include page="/include/wenewspc/commonNav.jsp"/>
<div class="container center_block">
	<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
    <div class="content_left">
    <c:if test="${parentID eq 0}">
    <div class="ad_top_" style="width: 500px;height: 260px; border: 1px solid #DFE0E4;margin-top: 20px;box-shadow: 0 0 2px rgba(0,0,0,0.15);border-radius: 4px;">
					<div class="banner">
						<ul class="">
							<c:forEach var="Image" items="${ImageList }">
								<li>
								
								<c:if test="${Image.level==-1 }">
									<a href="<%=basePath%>WeNews/VideoDetails?VideoID=${Image.PKID}"><img src="${Image.imageUrl.split(',')[0]}" alt=""></a>
								</c:if>
								<c:if test="${Image.level!=-1 }">
									<a href="<%=basePath%>WeNews/VideoNews?parentID=${Image.PKID}"><img src="${Image.imageUrl.split(',')[0]}" alt=""></a>
								</c:if>
									
								</li>
							</c:forEach>
						</ul>
						<div class="left-btn"></div>
						<div class="right-btn"></div>
						<div class="img-btn-list"></div>
					</div>
				</div>
				</c:if>
			<!--内容部分 （中间） -->
			<div id="newsList">
				
			</div>				
    </div>
<div class="content_right">
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="school_top">
					<c:if test="${School_Adverts!=null }">
					<a href="${School_Adverts.url }" target="_blank">
						<div class="school_img">
							<img class="" src="${School_Adverts.imageurl}">
						</div>
					</a>
				</c:if>
				<c:if test="${School_Adverts==null }">
					<a href="#" target="_blank">
						<div class="school_img">
							<img class="" src="${(SchoolImageUrl!='')?SchoolImageUrl:DefaultSchoolImageUrl}">
						</div>
					</a>
				</c:if>
					 <c:forEach var="SchoolRank" items="${SchoolRank }" varStatus="vs">
					 	<p class="list_school">
							<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${SchoolRank.areaID }"><b>${SchoolRank.SRegion.name }</b></a>
						</p>
						<p>投稿：${SchoolRank.count}篇</p>
						<p>
						<c:if test="${schoolType=='week'}">
							本周
						</c:if>
						<c:if test="${schoolType!='week'}">
							总
						</c:if>
						排名：<em class="red">${SchoolRank.level}</em></p>
						<div class="clear_float"></div>
					 </c:forEach>
					<hr>
					<c:if test="${Mycount!=null}">
						<p>本周我的投稿数：${Mycount}</p>
							<p 
						<c:if test="${Mycount==0}">
							style=" width:100%;   max-width: 120px;"
						</c:if>
						>本周排名：${Mylevel}</p>
						<div class="clear_float"></div>
					</c:if>
					
					
				</div>
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="match_top">
					<p>
					<c:if test="${readType=='week'}">
						本周
					</c:if>
					阅读排行榜
						<a href="<%=basePath %>WeNews/GloryList?type=0">更多>></a>
					</p>
					<ul>
						<c:forEach var="ReadNotices" items="${ReadNotices }" varStatus="vs">
							<li>
								<span>${ReadNotices.level }</span>
								<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${ReadNotices.PKID }">
									<p class="word_rule">${ReadNotices.title }</p>
								</a>
								<a href="<%=basePath %>WeNews/User?Uid=${ReadNotices.userID}" class="rankAuthor">${ReadNotices.student.name }</a>
							</li>
						</c:forEach>
					</ul>

				</div>
				
				<div class="match_top match_top_fix">
					<p>
					<c:if test="${noticeType=='week'}">
						本周
					</c:if>
					投稿排行榜
						<a href="<%=basePath %>WeNews/GloryList?type=1">更多>></a>
					</p>
					<ul>
						<c:forEach var="LikeNotices" items="${NoticesCount }" varStatus="vs">
							<li>
								<span>${LikeNotices.level }</span>
								<a href="<%=basePath %>WeNews/User?Uid=${LikeNotices.userID }">
									<p class="word_rule">${LikeNotices.student.name }</p>
								</a>
								<a href="<%=basePath %>WeNews/User?Uid=${LikeNotices.userID}" class="rankAuthor">${LikeNotices.count }</a>
							</li>
						</c:forEach>
					</ul>		
				</div>
			</div>
			
</div>
<input type="hidden" id="_page_" value="1">
	<input type="hidden" id="type" name="type" value="2" />
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/wenewspc/commonJs.jsp"/>
<script type="text/javascript">
bannerListFn(
	    $(".banner"),
	    $(".img-btn-list"),
	    $(".left-btn"),
	    $(".right-btn"),
	    5000,
	    true
	);
	loadNews();
	var i = 1; //设置当前页数 
	$(window).scroll(function() {		
		if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
			loadNews();
		}
	});

function loadNews(){

	 $(".sk-spinner").remove();
	 $("#NotMore").remove(); 
	 $("#newsList").append(getLoading());
	 $.getJSON("<%=basePath%>weChatGroup/getWeNewsPaJsonrtList",
		{
		 _page_:$('#_page_').val(),
		 pageSize:10,
		 submitType:'phone',
		 _pc_:'PC',
		 _area_:0,
		 videoType:'${parentID}',
		 type:3
		 },function(json){ 
           if(json && json.Status==1){ 
           	var html="";	
               $.each(json.Data,function(index,array){ 
            	   html+=addVideoNewsDom(array.video);                 	
               }); 
               $("#newsList").append(html); 
               InitVideo(0);
              
               $(".sk-spinner").remove();
               if(json.Data.length<=0){
               	$(".sk-spinner").remove();
					if($("#NotMore").length<=0){
						if($("#newsList")!=null){
							$("#newsList").append(getNoMoreInfo());
						}else{
							$("#newsList").append(getNoMoreInfo()); 
						}
						
					}
               }else{
                   var _page_=$('#_page_').val();
                   $('#_page_').val(parseInt(_page_)+1);
                   $(".sk-spinner").remove();
					$("#newsList").append(getLoading());
               }
           }else{ 
           		$(".sk-spinner").remove();
					if($("#NotMore").length<=0){
						if($("#newsList")!=null){
							$("#newsList").append(getNoMoreInfo())
						}else{
							$("#newsList").append(getNoMoreInfo()); 
						}
					}
					 bindImgError();
           	return false; 
           }
         
       }); 
	
	
}
</script>
</body>
</html>