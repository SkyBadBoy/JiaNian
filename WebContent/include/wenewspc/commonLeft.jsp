<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	
%>
<c:if test="${PCBannerLeft_Adverts!=null}">
	<div class="adv_left"><a href="${PCBannerLeft_Adverts.url }" target="_blank"><img src="${PCBannerLeft_Adverts.imageurl }" width="100%"></a></div>
</c:if>

	<div class="nav_left">
	<ul>
		<a href="<%=basePath%>WeNewsHome">
			<li <c:if test="${flag eq 'Home'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/nav_1.png"></div>
				<p>首页</p>
			</li>
		</a>
		<a href="<%=basePath%>WeNews/News">
			<li <c:if test="${flag eq 'News'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/nav_2.png"></div>
				<p>最新投稿</p>
			</li>
		</a>
		<a href="<%=basePath%>WeNews/VideoNews">
			<li <c:if test="${flag eq 'VideoNews'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/video.png"></div>
				<p>视频新闻</p>
			</li>
		</a>
		<a href="<%=basePath%>WeNews/WeNewsSeminar?vote=879990030779944960">
			<li <c:if test="${flag eq 'WeNewsSeminar'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/WeNewsSeminar.png"></div>
				<p style="color:#FF6B6B">微新闻大赛</p>
			</li>
		</a>
		<a href="<%=basePath %>WeNews/ReviewNews">
			<li <c:if test="${flag eq 'ReviewNews'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/nav_3.png"></div>
				<p>人气作品</p>
			</li>
		</a>
		<a href="<%=basePath%>WeNews/Student">
			<li <c:if test="${flag eq 'Student'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/nav_4.png"></div>
				<p>学生空间</p>
			</li>
		</a>
		<a href="<%=basePath %>WeNews/School">
			<li <c:if test="${flag eq 'School'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/nav_5.png"></div>
				<p>学校空间</p>
			</li>
		</a>
		<a href="<%=basePath %>WeNews/GloryList?rank=read&type=0">
			<li <c:if test="${flag eq 'Glory'}"> class="nav_active" </c:if>>
				<div class="nav_img"><img src="<%=basePath %>images/wenewspc/nav_6.png"></div>
				<p>光荣榜</p>
			</li>
		</a>
	</ul>
	<c:if test="${PCBannerQR_Adverts!=null}">
		<div class="nav_ma" style="background: url('${PCBannerQR_Adverts.imageurl}')  no-repeat; background-size:100% "></div>
	</c:if>
	<c:if test="${PCBannerQR_Adverts==null}">
		<div class="nav_ma">
			<p class="text_center">校播发布</p>
			<div class="center_block">
				<img src="<%=basePath %>images/qrcode_for_gh_298035d75ad7_430.jpg">
			</div>
			<p class="text_center" style="font-size: 14px" >更多精彩活动扫码关注</p>
		</div>
		
	</c:if>
</div>
<c:if test="${PCBannerRight_Adverts!=null}">
	<div class="adv_right"  ><a href="${PCBannerRight_Adverts.url }" target="_blank"><img src="${PCBannerRight_Adverts.imageurl }" width="100%"></a></div>
</c:if>
