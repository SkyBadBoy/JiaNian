<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	
	Students user =(Students)request.getSession().getAttribute("StudentName");
	
	String isLogin="0";
	if(user!=null){
		isLogin="1";
	}else{
		isLogin="0";
	}
	request.setAttribute("isLogin", isLogin);
	String version="1.0.0.5.9.15";
%>
<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<jsp:include page="/include/wenewspc/returnTop.jsp"/>

<script src="<%=basePath %>js/jquery.min.js?v=<%=version %>" type="text/javascript"></script>

<script type="text/javascript" src="<%=basePath %>js/wenewspc/plugins/autocomplete.js?v=<%=version %>"></script>
<script src="<%=basePath %>js/wenewspc/plugins/bootstrap.min.js?v=<%=version %>"></script>
<script src="<%=basePath %>js/wenewspc/plugins/webuploader/0.1.5/webuploader.min.js?v=<%=version %>"></script>
<script src="<%=basePath %>js/wenewspc/plugins/webuploader/0.1.5/webuploader-demo.min.js?v=<%=version %>"></script>
<script type="text/javascript" src="<%=basePath %>js/wenewspc/plugins/slide/bannerList.js?v=<%=version %>"></script>
<script type="text/javascript" src="<%=basePath %>js/wenewspc/plugins/vmc.slider.full.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/wenewspc/plugins/pgwslideshow.min.js?v=<%=version %>"></script>
<script src="<%=basePath%>js/wechat_index.js?v=<%=version%>"></script>
<script src="<%=basePath %>js/wenewspc/index.js?v=<%=version %>" type="text/javascript"></script>
<script src="<%=basePath%>js/qrcode.min.js?v=<%=version %>"></script>


<script type="text/javascript">
	
	var BASE_URL = '<%=basePath %>js/wenewspc/plugins/webuploader';
	var proposals = [];

		$('#search-form').autocomplete({
			hints: proposals,
			width: 300,
			height: 30
		});
	
	
	function Login(){
		window.location.href="<%=basePath%>WeNews/UserLogin?returnUrl="+encodeURI(location.pathname+location.search);
	}
	function showLogin(){
		$('.zhezhao').fadeIn();
	}
	var isLogin='${isLogin}'; 
	function UserInfo(){
		if(isLogin == "0") {
			$('.zhezhao').fadeIn();
		}else{
			window.location.href="<%=basePath%>WeNews/UserInfo?returnUrl="+encodeURI(location.pathname+location.search);
		} 
	}
	
</script>
<script>
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
    }
    else {
        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>