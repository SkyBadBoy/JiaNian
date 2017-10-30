
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user =(Users)request.getSession().getAttribute("UserName");

%>

<script src="<%=basePath%>js/jquery.min.js?v=2.1.4"></script>
<script src="<%=basePath%>js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=basePath%>js/content.min.js?v=1.0.0"></script>
<script src="<%=basePath%>js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=basePath%>js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=basePath%>js/operate/form-validate-demo.min.js"></script>
<script src="<%=basePath%>js/plugins/beautifyhtml/beautifyhtml.js"></script>
<script src="<%=basePath%>js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="<%=basePath%>js/plugins/clockpicker/clockpicker.js"></script>
<script src="<%=basePath%>js/plugins/cropper/cropper.min.js"></script>
<script src="<%=basePath%>js/plugins/summernote/summernote.min.js?v=1.0.0.4"></script>
<script src="<%=basePath%>js/plugins/summernote/summernote-zh-CN.js?v=1.0.0.1"></script>
<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-export.js"></script>
<script src="<%=basePath%>js/plugins/bootstrap-table/tableExport.js"></script>
<script src="<%=basePath%>js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=basePath%>js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=basePath%>js/operate/bootstrap-table-operate.js?v=1.0.0"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="http://imgcache.qq.com/tencentvideo_v1/tvp/js/tvp.player_v2_jq.js" type="text/javascript"></script>
<script src="<%=basePath%>js/wechat_index.js?v=1.0.6.7"></script>
<script src="<%=basePath%>js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
<!--瀑布流布局插件-->
<script src="<%=basePath%>js/jquery.masonry.min.js" type="text/javascript"></script>
<!--图片选择器插件-->
<script src="<%=basePath%>js/image-picker.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="<%=basePath%>js/plugins/nestable/jquery.nestable.js"></script>
<script src="<%=basePath%>js/plugins/easypiechart/jquery.easypiechart.js"></script>


