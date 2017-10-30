<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String type=request.getParameter("type");
	request.setAttribute("type", type);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
	
%>
<html>
<head>
	<title>${title}</title>
	
    <link href="<%=basePath%>css/minnie.css" rel="stylesheet"/>
     <jsp:include page="/include/commonMobileCss.jsp"></jsp:include>
    <style type="text/css">
    
    ._image_100{
        border-radius: 50%;
    }
     .mulitlinecontent{
   	height: 65px;padding-left: 10px;padding-right: 5px;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3; 
   }
    </style>
</head>
<body class="whitebg">
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<input type="hidden" name="_page_" id="_page_" value="2" />

		<div class="header ">
		    <a class="header_left" href="#menu"></a>
		    <a href="<%=basePath%>Students/phoneSinaIndex" style="padding-top:35px;"><img src="${imageurl}" class="header_img" style="margin-top: 12.5px;"></a>
		    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?AreaId=<%=user.getAreaID() %>&SID=<%=user.getID() %>">
		        <span>我要投稿</span>
		        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
		    </a>
		</div>

<!--<div class="content"><p><strong>这是一个演示。</strong><br/>点击菜单图标打开菜单。</p></div>-->
<div style="height: 20px;"></div>
<!-- 广告展示轮播-->

<div class="index_activity mt66 user_card_container">
	<div class="user_card">
		<div class="user_info_card">
			<div class="user_photo">
				<a href=""><img class="img_100" style="border-radius: 50%;" src="${userImageurl}"></a>
			</div>
			<div class="user_info">
				<a href="<%=basePath%>Students/phoneSinaRule#starRule">${Student.name}
				<c:if test="${LevelIcon!=''}">
			      	 <img src="${LevelIcon}" style="width: 20px;margin-top: -5px;">
			      </c:if>
				<c:if test="${PositionIcon!=''}">
		      	 <img src="${PositionIcon}" style="width: 20px;margin-top: -5px;">
		      </c:if>
		      	 <c:forEach var="Image" items="${HonourIcon}">
					<img src="${Image}" style="width: 15px;height:15px">
				</c:forEach>
				</a><span>投稿（${Student.noticeCount}）</span>
				<p>
					<a href="">${Student.school}</a>
				</p>
			</div>
			<hr>
			<p>${Student.habit}</p>
		</div>
	</div>
</div>
<div id="myDiv">
<c:forEach var="Part" varStatus="StatusIndex" items="${data}">
	<c:if test="${StatusIndex.index==0}">
		<div class="index_activity mt66">
	</c:if>
	<c:if test="${StatusIndex.index!=0}">
		<div class="index_activity">
	</c:if>
	    <a href="<%=path%>/Product/phoneweChatPordDetail?_type_=2&_pid_=${Part.PKID}&_area_=${Part.areaID}&_status_=1&sina=<%=SmBaseUtil.Random() %>">
	        <div class="list-header list-media"><img class="_image_100" src="${userImageurl}"><div class="list-body">${Part.author}<p>${Part.createTime.substring(0,19)}</p></div></div>
	        <div class="index_title">${Part.title}</div>
	        <!--<span class="index_title">我的G20垃圾分类我先行</span>-->
	        <c:if test="${Part.image.ID!=0 }">
	        	<div><img src="${Part.image.url.split(',')[0]}" class="img_response"></div>
	        </c:if>
	        <c:if test="${Part.image.ID==0 }">
	        	<div class="mulitlinecontent" >${Part.content}</div>
	        </c:if>
	        
	    </a>
	    <div class="index_footer_new">
	        <div class="index_name"><div class="list_dzBtn"><a href="javascript:;"><img src="<%=basePath%>images/boforeJob.png"><span class="go_gref">${Part.like}</span></a></div></div>
	        <span class="index_name"><img class="list_read" src="<%=basePath%>images/readNews.png">${Part.clickCount}</span>
	        <span class="index_read index_read_bg"  data-toggle="modal" ><img class="list_comment" src="<%=basePath%>images/comment.png">${Part.commentCount}</span>
	    </div>
	</div>
</c:forEach>
</div>
<footer style="background: #fff;padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;margin-bottom: 50px;">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>


<jsp:include page="/include/mobileMenu.jsp"></jsp:include>

<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript">
	$(function () {
        $('nav#menu').mmenu();
        $(window).scroll();
    });
	
</script>
<script type="text/javascript">

        $(function() {
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
						$("#myDiv").append(getLoading()); 
					 $.getJSON("<%=path%>/Notices/getphoneNoticesList",
						{
						 pageNumber:$('#_page_').val(),
						 uid:'${Student.PKID}',
						 submitType:'phone',
						 pageSize:10
						 },function(json){ 
			                if(json && json.Status==1){ 
			                	
			                	var tmp="";
			                	var basePath=$("#basePath").val();
			                	 $.each(json.data,function(index,array){ 
			                		 var imageurl="";
			                		 if(array.image!=null && array.image.url!="" && array.image.url!=null){
			                			 imageurl=array.image.url.split(',')[0];
			                		 }
			                		 tmp += ' <div class="index_activity">'+
			                			  '  <a href="<%=path%>/Product/phoneweChatPordDetail?_type_=2&_pid_='+ array.pkid +'&_area_='+ array.areaID +'&_status_=1&sina=<%=SmBaseUtil.Random() %>">'+
			                			   '     <div class="list-header list-media"><img class="_image_100" src="'+ array.student.imageUrl.split(",")[0] +'"><div class="list-body">'+ array.author +'<p>'+ array.createTime.substr(0,19) +'</p></div></div>'+
			                			   '     <div class="index_title">'+ array.title +'</div>';
			                			   if(imageurl!=""){
			                				   tmp +=    '     <div><img src="'+ imageurl +'" class="img_response"></div>'
			                			   }else{
			                				   tmp +=    '     <div class="mulitlinecontent" >'+ array.content +'</div>'
			                			   }
			                			   
			                			  
			                	 tmp += '  </a>'+
			                			  '  <div class="index_footer_new">'+
			                			    '    <div class="index_name"><div class="list_dzBtn"><a href="javascript:;"><img src="<%=basePath%>images/boforeJob.png"><span class="go_gref">'+ array.like +'</span></a></div></div>'+
			                			     '   <span class="index_name"><img class="list_read" src="<%=basePath%>images/readNews.png">'+ array.clickCount +'</span>'+
			                			     '   <span class="index_read index_read_bg"  data-toggle="modal" ><img class="list_comment" src="<%=basePath%>images/comment.png">'+ array.commentCount +'</span>'+
			                			  '  </div>'+
			                			'</div>';
			                           
			                            
			                    }); 
			                    $("#myDiv").append(tmp); 
			                    if(json.data.length<=0){
			                    	AddNoMoreDOM($("#myDiv"));
			                    }else{
				                    var _page_=json._page_;
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
			                bindUserImgError();
			                
			            }); 
				}
			});
		});
        /* 图片绑定，展示失败的显示默认图片 */
        function bindUserImgError(){
        	$("._image_100").each(function(i,obj){
        		if($(obj).attr("src")==($("#basePath").val()+"img/errorpic.jpg")){
        			$(obj).unbind("error").attr("src", $("#basePath").val()+"img/tx_default.jpg");
        		}
        	})
        	if($(".img_100").attr("src")==($("#basePath").val()+"img/errorpic.jpg")){
        		$(".img_100").unbind("error").attr("src", $("#basePath").val()+"img/tx_default.jpg");
        	}
        }
        setTimeout(function(){bindUserImgError()},100);
</script>
</body>
</html>