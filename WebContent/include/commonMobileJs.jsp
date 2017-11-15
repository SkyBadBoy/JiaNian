<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String version="3.4.9.9.4.1";
%>
<script src="<%=basePath%>js/jquery.min.js?v=<%=version%>" type="text/javascript" ></script>
<script src="<%=basePath%>js/plugin/swiper/swiper.min.js?v=<%=version%>" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/plugin/alertPopShow/alertPopShow.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/mmenu/jquery.mmenu.min.all.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/fsgallery/fsgallery.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/bootstrap.min.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugins/blueimp/jquery.blueimp-gallery.min.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/school/custom-file-input.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugins/validate/messages_zh.min.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/nav4.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/mmenu/jquery.mmenu.min.all.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/mobiscroll/mobiscroll.2.13.2.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/LArea/LArea.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/cropper/lrz.all.bundle.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/plugin/cropper/cropper.min.js?v=<%=version%>"></script>
<script src="<%=basePath%>js/wechat_index.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-weui.min.js?v=<%=version%>"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>  
<jsp:include page="/include/commonStatistics.jsp"></jsp:include>