<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
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
.sk-spinner-fading-circle.sk-spinner{
        margin-top:20px!important}
        .a-tag-goods-info{
        	padding:0!important;
        }
        .index_footer{
        	margin:10px 0;
        }

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
    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?sina=<%=SmBaseUtil.Random() %>">
        <span>我要投稿</span>
        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
    </a>
</div>
<div style="height: 20px;"></div>
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
<div class="mt56" >
   
        <c:choose>
				<c:when test="${BannerListCount>0}"> 
			 <div id="module_1" class="floor swiper-container relative-container context">
        <div class="swiper-wrapper">
				<c:forEach var="Banner" items="${BannerList}">
					<div class="two swiper-slide">
						<a href="<%=path%>/Activity/phoneSinaActivityDetail?aid=${Banner.PKID}&sina=<%=SmBaseUtil.Random() %>" class="posi_relative"> 
						<img class="img_response"
							style="width: 100.000%;height:220px" src="${Banner.image.url.split(',')[0]}" alt="">
							<div class="list_activity_mymatch">${Banner.title}</div>
							<div class="list_activity_myfinish">进入报名</div>
		                  
						</a>
						
					</div>
				</c:forEach>
				</div>
				<c:if test="${BannerListCount>1}"><div class="swiper-pagination"></div></c:if>
				</div>
				</c:when>
				<c:otherwise>
				<c:choose>
				<c:when test="${ImageCount>0}">

						<div id="module_1" class="floor swiper-container relative-container context">
							<div class="swiper-wrapper" id="gallery">
								<c:forEach var="Image" items="${ImageList}">
					 				<div class="two swiper-slide">
									 	<a href="${Image.PUrl}" target="_blank" class="posi_relative">
						                    <img src="${Image.url.split(',')[0]}"  style="height: 200px" class="img_response"/>
						                </a>							
									</div> 
								</c:forEach>
							</div> 
							<!-- Add Pagination -->
							<c:if test="${ImageCount>1}"><div class="swiper-pagination"></div></c:if>
						</div>
					</c:when>
					
					<c:otherwise>
					 <div id="module_1" class="floor swiper-container relative-container context">
        <div class="swiper-wrapper">
		 				<img class="full-image img-hot-sale-cover"
						style="width: 100.000%;min-height:180px;height:180px;" src="<%=basePath%>img/xinlang.jpg?v=1.1"
						alt="">
						<div class="div-recommend-box">
							<span class="recommend-slogan"> </span> 
							<span class="recommend-sale-time"></span>
						</div>
								</div>
								</div>
					</c:otherwise>
		
	</c:choose>
				</c:otherwise>
			</c:choose>
        </div>
        
	<div id="container" class="viewport">
	 
		<c:forEach var="Part" items="${PartList}">
			<c:if test="${Part.noticesList!=null}">
				<div class="index_activity">
				 <a href="<%=path%>/Product/phoneweChatPordDetail?_type_=2&_pid_=${Part.noticesList.PKID}&_pc_=${_pc_}&_area_=${_area_}&_status_=1&sina=<%=SmBaseUtil.Random() %>">
				        <div class="list-header list-media"><img class="_image_100" src="${Part.noticesList.student.imageUrl.split(',')[0]}"><div class="list-body index_title" style="height:34px">${Part.noticesList.author}&nbsp;&nbsp;&nbsp;&nbsp;${Part.noticesList.SRegion.name}<p>${Part.noticesList.createTime.substring(0,19)}</p></div></div>
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
		</c:forEach>
</div>
<br/>

<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<jsp:include page="/include/mobileMenu.jsp"></jsp:include>


	<input type="hidden" id="_page_" value="2">
	<input type="hidden" id="_area_" value="${_area_}">
	<input type="hidden" id="_pc_" value="${_pc_}">
	<input type="hidden" id="_isStu_" value="${_isStu_}">
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<jsp:include page="/include/commonMobileJs.jsp" />
	<script type="text/javascript">
	
	var swiper = new Swiper('#module_1.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        loop: true,
        loopAdditionalSlides: 0,
        autoplay: 1000000, //可选选项，自动滑动
        autoplayDisableOnInteraction: false
    });
		$(function() {
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					 $("#NotMore").remove();
					$("#container").append(getLoading()); 
					 $.getJSON("<%=path%>/weChatGroup/getWeNewsPaJsonrtList",
						{
						 _page_:$('#_page_').val(),
						 _area_:$("#_area_").val(),
						 _pc_:$("#_pc_").val(),
						 _isStu_:$("#_isStu_").val(),
						 type:'2'
						 },function(json){ 
			                if(json && json.Status==1){ 
			                    $.each(json.Data,function(index,array){ 
			                    	var area=$("#_area_").val();
			                    	var basePath=$("#basePath").val();
			                    	var pc=$("#_pc_").val();
			                    	var str=getNewProductAndActivityDOM(array,area,basePath,pc);
									
			                        $("#container").append(str); 
			                    }); 
			                    if(json.Data.length<=0){
			                    	AddNoMoreDOM();
			                    }else{
				                    var _page_=json._page_;
				                    $('#_page_').val(parseInt(_page_)+1);
				                    $(".sk-spinner").remove();
				                    $("#container").append(getLoading()); 
				                    i++; 
			                    }
			                }else{ 
			                	AddNoMoreDOM();
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