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
    <title>光荣榜</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
	<body>
		<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
		<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			<div class="container center_block">
    <div class="content_left">
        <ul class="list_check">
            <li>小编投稿榜</li>
            <li>学校投稿榜</li>
            <li>人气作品月排行</li>
        </ul>
        <div class="list_topic">
            <ul class="topic_list">
				<c:forEach var="data" items="${NoticeCountData }">
					<li><a href="schoolinfo.html">${data.student.school}</a><a href="user.html">${data.student.name}</a><span>${data.count }篇</span></li>
				</c:forEach>
            </ul>
            <ul class="topic_list">
            	<c:forEach var="data" items="${SchoolData }">
               		 <li><a href="schoolinfo.html">${data.SRegion.name }</a><a href="user.html">${data.student.name}</a><span>${data.count }篇</span></li>
				</c:forEach>
            </ul>
            <ul class="topic_list">
               <c:forEach var="data" items="${LikeData }">
               		 <li><a href="schoolinfo.html">${data.title }</a><a href="user.html">${data.author}</a><span>${data.like }赞</span></li>
				</c:forEach>
            </ul>
        </div>
    </div>
		</div>
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
	</body>

</html>


