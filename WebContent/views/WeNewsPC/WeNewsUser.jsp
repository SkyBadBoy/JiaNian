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
	
	
%>
<html lang="en">
<head>
    <title>用户信息</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
<body>
<jsp:include page="/include/wenewspc/commonNav.jsp"/>
<div class="container center_block">
	<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
    <div class="user_infomation" style="height: 130px;">
        <div class="student_img"><img class="img_100" src="${Student.imageUrl.split(",")[0] }"></div>
        <div class="student_intr">
            <a >${Student.name }</a>
            <p>投稿（${Student.noticeCount }）</p>
            <p><a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${Student.areaID}">${Student.school}</a></p>
            
        </div>
        <div class="clear_float"></div>
        <hr>
        <c:if test="${Student.habit==null}">
        	<p  style="margin-left: 10px;">可爱的我啥都会~</p>
        </c:if>
         <c:if test="${Student.habit!=null}">
        	<p  style="margin-left: 10px;">爱好：${Student.habit }</p>
        </c:if>
        
    </div>
    <div class="content_left" id="newsList" style="min-width: 500px;">
					
    </div>
    <div class="content_right">
    
    	<c:if test="${Notice1Count>0}">
	        <div class="fun_topic">
	            <p>热门文章</p>
	            
	            <c:forEach items="${Notice1}" var="Notice">
		            <div>
		                <div class="fun_art">
		                    <div class="art_img"><a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${Notice.PKID }">
		                    		<img class="img_100" style="max-width: 65px;height: 65px;" src="${Notice.student.imageUrl.split(',')[0]}">
		                    </a></div>
		                </div>
		                <a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${Notice.PKID }" class="rankAuthor3">${Notice.title }</a>
		                <a href="<%=basePath %>WeNews/User?Uid=${Notice.publishUser}" class="rankAuthor3">${Notice.author }</a>
		                <div class="clear_float"></div>
		            </div>
				</c:forEach>
	        </div>
        </c:if>
        <c:if test="${Notice2Count>0}">
	        <div class="fun_topic">
	            <p>您可能感兴趣的文章</p>
	              <c:forEach items="${Notice2}" var="Notice">
		            <div>
		                <div class="fun_art">
		                    <div class="art_img"><a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${Notice.PKID }">
		                    		<img class="img_100" style="max-width: 65px;height: 65px;" src="${Notice.student.imageUrl.split(',')[0]}">
		                    </a></div>
		                </div>
		                <a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${Notice.PKID }" class="rankAuthor3">${Notice.title }</a>
		                <a href="<%=basePath %>WeNews/User?Uid=${Notice.publishUser}" class="rankAuthor3">${Notice.author }</a>
		                <div class="clear_float"></div>
		            </div>
				</c:forEach>
	        </div>
	      </c:if>
	   </div>
	    
</div>
<input type="hidden" id="_page_" value="1">
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/wenewspc/commonJs.jsp"/>
<script type="text/javascript">

	loadMyNotices();
	var i = 1; //设置当前页数 
	$(window).scroll(function() {		
		if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
			loadMyNotices();
		}
	});
	function loadMyNotices(){

		 $(".sk-spinner").remove();
		 $("#NotMore").remove(); 
		 $("#newsList").append(getLoading());
		 $.getJSON("<%=basePath%>Notices/getMyNoticesList?sina=<%=SmBaseUtil.Random()%>",
		{
		 pageNumber:$('#_page_').val(),
		 pageSize:10,
		 submitType:'phone',
		 uid:'${uid}'
		 },function(json){ 
               if(json && json.Status==1){ 
               	var html="";	
                   $.each(json.data,function(index,array){ 
                	   html+=addNewsDom(array);
                  			
                   	
                   }); 
                   $("#newsList").append(html); 
                   $(".sk-spinner").remove();
                   if(json.data.length<=0){
                   	$(".sk-spinner").remove();
						if($("#NotMore").length<=0){
							if($("#newsList")!=null){
								$("#newsList").append(getNoMoreInfo())
							}else{
								$("#newsList").append(getNoMoreInfo()); 
							}
						}
                   }else{
	                    var _page_=$('#_page_').val();
	                    $('#_page_').val(parseInt(_page_)+1);
	                    $(".sk-spinner").remove();
						$("#newsList").append(getLoading());
	                    i++; 
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
               	return false; 
               }
               bindImgError();
           }); 
	}


</script>

</body>
</html>