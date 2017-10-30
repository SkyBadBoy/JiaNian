
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
	if(user==null){
		user=new Students();
	}
	String icon1=basePath+"images/n_home.png";
	String icon2=basePath+"images/n_news.png";
	String icon3=basePath+"images/n_vedio.png";
	String icon4=basePath+"images/n_my.png";
	String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");   
	System.err.println("aaaaa:"+requestUrl);
	if(requestUrl.contains("phoneWeNewsHome")){
		icon1 = basePath+"images/n_home1.png";
	}else if(requestUrl.contains("phoneweChatGroupList")){
		icon2 = basePath+"images/n_news1.png";
	}else if(requestUrl.contains("phoneVideoList")){
		icon3 = basePath+"images/n_vedio1.png";
	}else if(requestUrl.contains("phoneSinaIndex")){
		icon4 = basePath+"images/n_my1.png";
	}
	boolean isShowMenu=true;
	boolean isShowOldMenu=true;
	if(requestUrl.contains("Ranking.jsp") 
			|| requestUrl.contains("phoneweChatPordDetail.jsp")
			|| requestUrl.contains("phoneweChatVedioDetail.jsp")
			|| requestUrl.contains("phoneSinaActivityDetail.jsp")){
		isShowMenu=false;
	}
	if(requestUrl.contains("phoneSinaIndex.jsp")) {
		isShowOldMenu=false;
	}
	
	request.setAttribute("isShowMenu", isShowMenu);
	request.setAttribute("isShowOldMenu", isShowOldMenu);
	
	
%>
 <c:if test="${isShowOldMenu}">
<nav id="menu">
    <ul>
        <li style="text-align: center;padding:5px 10px;"><img src="<%=basePath%>img/weixinwenshe/logo.png" style="width: 200px;"></li>
        <li><a href="<%=basePath%>weChatGroup/phoneWeNewsHome?sina=<%=SmBaseUtil.Random()%>">首页</a></li>
        <li><a href="<%=basePath%>weChatGroup/phoneweChatGroupList?_pc_=<%=user.getAreaID() %>&type=2&_area_=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random()%>">校园新闻</a></li>
        <li><a href="<%=basePath%>Activity/phoneactivitylist?Type=1&sina=<%=SmBaseUtil.Random()%>">近期活动</a></li>
        <li><a href="<%=basePath%>weChatGroup/phoneweChatGroupList?type=3&_area_=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random()%>">视频新闻</a></li>
        <li><a href="<%=basePath%>Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random()%>">新浪小编</a></li>
        <li><a href="<%=basePath%>Ranking/phoneReadRanking?type=3">排行榜</a></li>
    </ul>
</nav>
</c:if>
 <c:if test="${isShowMenu}">
	<div class="footer-fixed-bt">
	    <div class="footer-nav" id="footer-nav">
	        <a href="<%=basePath%>weChatGroup/phoneWeNewsHome?sina=<%=SmBaseUtil.Random()%>" class="footer-nav-item "><img src="<%=icon1%>">首页</a>
	        <a href="<%=basePath%>weChatGroup/phoneweChatGroupList?_pc_=<%=user.getAreaID() %>&type=2&_area_=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random()%>" class="footer-nav-item "><span class="icon icon-type"><img src="<%=icon2%>"></span>校园新闻</a>
	        <a href="<%=basePath%>weChatGroup/phoneweChatGroupList?type=3&_area_=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random()%>" class="footer-nav-item "><span class="icon icon-bag"><img src="<%=icon3%>"></span>视频新闻</a>
	        <a href="<%=basePath%>Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random()%>" class="footer-nav-item "><span class="icon icon-user"><img src="<%=icon4%>"></span>个人中心</a>
	    </div>
	</div>
</c:if>