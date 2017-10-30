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
	String NoProject = SmBaseUtil.getProjectBaseUrlNoProject(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
     <link rel="stylesheet" href="<%=basePath%>css/show_page2.css" />
    <title>微米抽奖</title>
   
</head>
<body>

	    
	   <div class="mui-content">
	      <div  ></div>
	      <div style="background-image:url(${LRSUrl})" ><div style="text-align: center;margin-top: 290px;color: wheat;"><a style="color: wheat;text-decoration: underline;" href="<%=basePath%>Students/phoneAppDownLoad">下载微新闻社App</a><br/>获取更多抽奖机会</div></div>
	      <div id="ring" ></div>
	      <div>
	      	<ul>
		      	<c:if test="${LRS.size()>0}">
		      		<c:forEach var="Prod" items="${LRS}">
			      		<li>
			      			<img src="${Prod.imageUrl}" alt="" />
			      			<span>
			      				<b>${Prod.name}</b>&nbsp;&nbsp;<span>${Prod.prizeCreatetime }</span> <br />
			      				${Prod.prizeRolltitle }<c:if test="${Prod.prizeResiduecount>0 and Prod.prizeResiduecount<100 }"> ，当前剩余${Prod.prizeResiduecount }张！</c:if>
			      			</span>
			      		</li>
		      		</c:forEach>
	      		</c:if>
	      		<c:if test="${LRS.size()==0}">
		      		
			      		<li>
			      			
			      			<span>微米红包，现金券就等你来拿！</span>
			      		</li>
		      		
	      		</c:if>
	      	</ul>
	      	
	      </div>
	      
	   </div>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script src="<%=basePath%>js/jQueryRotate.js?v=111.111.111" type="text/javascript" ></script>
<script type="text/javascript">
$(function(){

	$("#ring").rotate({
		bind:{
			click:function(){
				runzp(this);
			}
		}
	});
});
function randomnum(smin, smax) {// 获取2个值之间的随机数
	var Range = smax - smin;
	var Rand = Math.random();
	return (smin + Math.round(Rand * Range));
}

function runzp(obj) {
	var myreturn=new Object();
	var url="<%=NoProject%>WeNewsInterface/Lottery/Interface/V2/ExtractForWeb?param=";
	var param=encodeURI('{"userId":"<%=user.getID() %>"}');
	$.ajaxSettings.async = true; 
	$.getJSON(url+param,function(data){ 
		//{"lottery":{"demoId":3,"discount":10.00,"index":2,"award":"红包","type":9,"percent":14.500},"message":"抽奖成功","status":true}
		
		if(data.status){
			if(data.lottery.index==0){
				myreturn.angle = data.lottery.index *41;
			}else{
				myreturn.angle = (8-data.lottery.index) *41+41;
			}
			
			if(data.lottery.index>0){
				myreturn.message = data.lottery.award+",下载微新闻社APP去使用吧";
			}else{
				myreturn.message = data.lottery.award;
			}
			$(obj).rotate({
			 	duration:3000,               //转动时间
			 	angle: 0,                    //起始角度
				animateTo:1800+myreturn.angle,      //结束的角度
				easing: $.easing.easeOutSine,//动画效果，需加载jquery.easing.min.js
				callback: function(){
					alert(myreturn.message);//简单的弹出获奖信息
				}
		 	});
			
		}else{
			alert(data.message);
		}
		
	}) 

}



</script>
</body>
</html>