<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
	request.setAttribute("path", path);
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${Title}</title>
    <link href="<%=basePath%>css/plugins/mmenu/jquery.mmenu.all.css?v=3.3.7" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/plugins/swiper/swiper.min.css?v=3.3.7" />
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/index.css?v=3.3.7"/>
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js?v=3.3.7"></script>
	<link href="<%=basePath%>css/style.min.css?v=4.0.1" rel="stylesheet"><base target="_blank">
</head>
<body>
<div class="header ">
    <a class="header_left" href="#menu"></a>
    <a href="<%=basePath%>Students/phoneSinaIndex" style="padding-top:35px;"><img src="${imageurl}" class="header_img" style="margin-top: 12.5px;"></a>
    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?AreaId=<%=user.getID() %>&SID=<%=user.getAreaID() %>">
        <span>我要投稿</span>
        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
    </a>
</div>
<input type="hidden" id="_page_" value="2">
	<input type="hidden" id="Type" value="${Type}">
	<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<div style="height: 20px;"></div>
<div class="mt56" >
	<div class="viewport" id="container" id="myDiv">
		<c:forEach var="Prod" items="${data}">
			<div class="index_activity_my mt41" >
			    <a class="posi_relative" href="<%=path%>/Activity/phoneSinaActivityDetail?aid=${Prod.PKID}&sina=<%=SmBaseUtil.Random()%>">
			        <img class="img_response" style="width: 100.000%;height:220px" src="${Prod.image.url.split(',')[0]}" alt="${Prod.title}"  alt="">
							<div class="list_activity_mymatch">${Prod.title}</div>
							<c:if test="${Type==1}">
								<div class="list_activity_myfinish">进入报名</div>
							</c:if>
							<c:if test="${Type!=1}">
								<div class="list_activity_myfinish">查看详情</div>
							</c:if>
		                    <div class="index_mybg" style="width: 100.000%;height:220px"></div>
			    </a>
			</div>
		</c:forEach>
	</div>
	<c:if test="${data.size()<=0}">
		<br/>
			<h3 style="text-align: center;" >当前没有可以进行的活动~</h3>
			<br/>
    </c:if>
</div>
<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/plugin/mdialog/zepto.min.js?v=3.3.7"></script>
<script type="text/javascript" src="<%=basePath%>js/plugin/mdialog/mdialog.js?v=3.3.7"></script>

<script type="text/javascript">
        
        $(function() {
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
						$("#myDiv").append(getLoading()); 
					 $.getJSON("<%=path%>/Activity/getPhoneActivityList",
						{
						 pageNumber:$('#_page_').val(),
						 Type:$("#Type").val(),
						 submitType:'phone',
						 pageSize:10
						 },function(json){ 
			                if(json && json.Status==1){ 
			                	
			                	var tmp="";
			                	var basePath=$("#basePath").val();
			                    $.each(json.data,function(index,array){ 
			                            tmp += '<div class="index_activity_my mt41" ><a class="posi_relative" href="<%=path%>/Activity/phoneSinaActivityDetail?aid='+ array.pkid +'&sina=<%=SmBaseUtil.Random()%>">'+
			                                    '<img src="' +array.image.url.split(',')[0]+'" alt="'+array.title+'"   style="width: 100.000%;height:220px">' +
			                                   '<div class="list_activity_mymatch">'+array.title+'</div>'+
			        							'<div class="list_activity_myfinish">进入报名</div>'+
			        		                    '<div class="index_mybg" style="width: 100.000%;height:220px"></div>'+
			                            '</div>'+
			                            '</div>'+
			                            '</a></div>';
			                    }); 
			                    $("#myDiv").append(tmp); 
			                    if(json.data.length<=0){
			                    	AddNoMoreDOM($("#myDiv"));
			                    }else{
				                    var _page_=$('#_page_').val();
				                    $('#_page_').val(parseInt(_page_)+1);
				                    $(".sk-spinner").remove();
				                    $("#myDiv").append(getLoading()); 
				                    i++; 
			                    }

			                }else{ 
			                	AddNoMoreDOM($("#myDiv"));
			                	return false; 
			                }
			                bindImgError();
			            }); 
				}
			});
		});
		    
        
        $(function () {
	        $('nav#menu').mmenu();
	    });
        
</script>
<!-- 友盟统计代码 begin -->
<%@ include file="../../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>
</html>