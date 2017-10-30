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
	String schoolImg= basePath+SmBaseGlobal.SchoolDefaultImageUrl;
	request.setAttribute("img", img);
	request.setAttribute("SchoolImg", schoolImg);
%>
<html lang="en">
<head>
    <title>学校空间</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
	<body>
<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
		<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			<div class="student_block">
				<div class="search_block">
				<div class="school_list" id="newsList">
				
						<div class="one_school">
							<div class="student_img">
								<a href="#"><img class="img_100" src="${School.logo.url.split(',')[0] eq null?SchoolImg:School.logo.url.split(',')[0]}"></a>
							</div>
							<div class="school_info">
								<a href="#">${School.area.name }</a>
								<a href="#">小记者（${School.studentCount }）</a><br>
								<span>地址：${School.addressStr }</span>
								<a href="">投稿（${School.noticeCount }）</a>
							</div>
							<div class="clear_float"></div>
							<hr>
							<div class="school_intr">
								<p><b>学校简介：</b></p>
								<c:if test="${School.content ne null }">
									<p>${School.content}</p>
								</c:if>
								<c:if test="${School.content eq null }">
									<p>该学校还没有留下脚印哦</p>
								</c:if>
								
							</div>
						
							<div class="clear_float" style="margin-top: 20px;"></div>
						</div>
					
					
				</div>
				<div class="allad_list">
					<p>本校小记者（前20名）</p>
					<hr>
					<c:forEach var="data" items="${Students}">
						<div class="ad_list">
							<a href="<%=basePath %>WeNews/User?Uid=${data.PKID}">
								<div class="adimg_100"><img class="img_100" src="${data.imageUrl.split(",")[0]}"></div>
								<p>${data.name}</p>
							</a>
						</div>
					</c:forEach>
				</div>
				
				
				<div class="renqi_topic">
					<p>学校人气文章</p>
					<div id="SchoolnewsList">
					<c:forEach items="${Notices }" var="data">
						<div class="one_renqitopic">
							<a href="<%=basePath%>WeNews/NewsDetails?NoticeID=${data.PKID}">
								<p class="word_rule">${data.title}</p>
								<div class="img_200"><img class="img_200" src="${data.pictures[0].url.split(",")[0] }"></div>
							</a>
						</div>
					</c:forEach>
					</div>
				</div>
				
				
			</div>
		</div>
	<input type="hidden" id="_page_" value="3">
	<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		<script type="text/javascript">

			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					 $("#NotMore").remove(); 
					 $.getJSON("<%=basePath%>WeNews/getSchoolNews",
						{
						 pageNumber:$('#_page_').val(),
						 pageSize:10,
						 submitType:'pc',
						 SchoolID:'${School.areaID}',
						 submitType:'phone'
						 },function(json){ 
			                if(json && json.Status==1){ 
			                	var html="";
			                    $.each(json.data,function(index,array){ 
			                    	var imgurl="";
			                    	if(array.pictures.length>0){
			                    		imgurl=array.pictures[0].url.split(",")[0];
			                    	}
			                    		html+='<div class="one_renqitopic">';
			                    		html+='<a href="<%=basePath%>WeNews/NewsDetails?NoticeID='+ array.pkid +'">';
			                    		html+='		<p class="word_rule">'+ array.title +'</p>';
			                    		html+='	<div class="img_200"><img class="img_200" src="'+ imgurl +'"></div>';
			                    		html+='</a>';
			                    		html+='</div>';
			                    		         	
			                    }); 
			                    $("#SchoolnewsList").append(html); 
			                    if(json.data.length<=0){
			                    	$(".sk-spinner").remove();
									if($("#NotMore").length<=0){
										$("#SchoolnewsList").append(getNoMoreInfo()); 
									}
			                    }else{
				                    var _page_=$('#_page_').val();
				                    $('#_page_').val(parseInt(_page_)+1);
				                    $(".sk-spinner").remove();
				                    i++; 
			                    }
			                }else{ 
			                		$(".sk-spinner").remove();
									if($("#NotMore").length<=0){
										$("#SchoolnewsList").append(getNoMoreInfo()); 
									}
			                	return false; 
			                }
			                setTimeout(function() {
			            		bindImgError();
			            	}, 500);
			            }); 
				}
			});
			
		</script>
		
	</body>

</html>