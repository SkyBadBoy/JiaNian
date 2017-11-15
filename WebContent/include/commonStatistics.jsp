
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");   
	String returnURL=SmBaseUtil.getCurrentWebUrl(request)+ requestUrl+((request.getQueryString()!=null)?"?"+request.getQueryString():"");
%>
<script type="text/javascript">

var userAgentInfo = navigator.userAgent;
var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone",
            "iPad", "iPod","Wechat"];
var flag = true;
for (var v = 0; v < Agents.length; v++) {
    if (userAgentInfo.indexOf(Agents[v]) > 0) {
        flag = false;
        break;
    }
}
var winWidth=0;
if (window.innerWidth)
	winWidth = window.innerWidth;
else if ((document.body) && (document.body.clientWidth))
	winWidth = document.body.clientWidth;
if(flag==true && winWidth>=800){
	var url=window.top.location.href.split("?")[0];
	if(url.indexOf('/Competition/')<=0 && url.indexOf('Details')<=0 && url.indexOf('phoneSummerCampApply')<=0 ){
		//window.top.location ="<%=basePath%>WeNewsHome";
	}
	
}

</script>
<!-- 友盟统计代码 begin -->
<%@ include file="../../cs.jsp" %>
<%CS cs = new CS(1269379638);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img style="display: none;" src="<%= imgurl %>" width="0" height="0"  />
<img style="display: none;" src="<%=basePath%>AccessActivity/sendAccessActive?type=1&url=<%=URLEncoder.encode(returnURL) %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->