<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String version=SmBaseUtil.Random();
%>

<script src="<%=basePath%>js/jianian/jquery.min.js?v=<%=version %>"></script>
<script src="<%=basePath%>js/jianian/fastclick.js?v=<%=version %>"></script>
<script src="<%=basePath%>js/jianian/slick.min.js?v=<%=version %>"></script>
<script src="<%=basePath%>js/jianian/rem.js?v=<%=version %>"></script>
<script src="<%=basePath%>js/jianian/basic.js?v=<%=version %>"></script> 
<script src="<%=basePath%>js/jianian/swiper.jquery.min.js?v=<%=version %>"></script>
<script src="<%=basePath%>js/jianian/index.js?v=<%=version %>"></script>
<script type="text/javascript" src="<%=basePath%>js/jianian/plugins/lazyload/jquery.lazyload.js?v=<%=version %>"></script>
<script type="text/javascript" src="<%=basePath%>js/jianian/plugins/updown/updown.js?v=<%=version %>"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>  
<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
<script type="text/javascript">
	
</script>