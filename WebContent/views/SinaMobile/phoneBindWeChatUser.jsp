<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <title>绑定微信</title>
    <style type="text/css">
        .starCenter{margin: 0;padding: 0}
        .starCenter .starHeader{height: 36%;width: 100%;border-bottom:1px solid #e3e6f2;}
        .starCenter .starHeader .figureHead{height: 200px;width:100%;text-align: center;}
        .starCenter .starHeader .figureHead a{display: inline-block;width: 100px;height: 100px;margin: 0px auto;margin-top: 20px}
        .starCenter .starHeader .figureHead a img{width: 100%;height: 100%;border-radius: 50%;}
        .starCont{margin-top: 26px;}
        .starCont p{font-size: 14px;line-height: 28px;color: #222638;width: 94%;margin-left:3% }
        .wechatImgDiv{
        	
  display: block;
    text-align: center;
    padding: 20px;
        	
        }
    </style>
</head>
<body class="whitebg">
<div class="starCenter">
    <div class="starHeader">
        <div class="figureHead">
            <a href="<%=basePath%>Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random()%>" >
                <img src="${imageurl}" alt="" style="box-shadow: 0 0 30px #c1c1c9">
            </a> 
                 <div style="margin-top: 10px;text-align: center;font-size: 16px;">
                    ${StudentsForm.school}<br/>${StudentsForm.name}
                </div>
        </div>   
    </div>
     <div class="starCont">
     <p style="font-weight: bold;">已绑定微信</p>
      	<c:forEach var="imageUrl" items="${WeChatImageUrl}">
      	<div class="wechatImgDiv">
        	<img src="${imageUrl.headImgUrl}"  style=" width: 100px; height: 100px; ">
        	<br>
        	<br>
        	<span>${imageUrl.nickName}</span>&nbsp;&nbsp;&nbsp;
        	<c:if test="${imageUrl.openID!=openid }">
        		<a onclick="unBindWeChat('${imageUrl.PKID}')">解绑</a>
        	</c:if>
        	
        	</div>
        </c:forEach>
     	
    
     </div>

    <button id="backBtn" type="submit" style="color: #fff;font-size: 16px;background-color: #68ccb3;width: 90%;margin: 4%;padding: 5px; border-radius: 5px;">点击右上角分享该页面给要绑定的微信用户</button>
</div>
<footer style="margin:10px 0;bottom: 10px;width: 100%">
    <div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript">

function unBindWeChat(obj){
	$.ajax({
		url : "<%=basePath%>/Region/phoneUnBindWeChatUser?pkid="+obj,
		type:"GET",
		success : function(json) {
			if(json.Status=="1"){
				webToast("解绑成功！","bottom", 2000);
				try{
					window.location="<%=basePath%>Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random()%>";
				}catch (e) {
				}
				
			}else{
				webToast("解绑失败,请重试！","bottom", 2000);
			}
		}
	});
	
}
</script>
</body>
</html>