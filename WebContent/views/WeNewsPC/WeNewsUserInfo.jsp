<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
%>
<html lang="en">
	<head>
		<title>用户中心</title>
		<jsp:include page="/include/wenewspc/meta.jsp"/>
		<jsp:include page="/include/wenewspc/commonCss.jsp"/>
	</head>

	<body>
	<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
			<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			
			<div class="user_infomation">
				<div class="size_430" id="crop-avatar" style="width: 190px;height: 190px;">
					<div class="size_430 avatar-view" title="修改头像">
						<img src="${user.imageUrl }"  style="width: 190px;height: 190px;margin: 10px;" alt="Avatar">
					</div>
				</div>
				<div class="student_intr">
					<a href="">${user.name }</a>
					<div id="userSetting">设置
						<ul>
							<li>
								<a href="<%=basePath %>WeNews/UserEdit">个人资料</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/EditPwd">账号安全</a>
							</li>
						</ul>
					</div>
					<p>${user.school eq null ?"还未设置学校哦~~":user.school }</p>
					<p>爱好：${user.habit eq null ?"嘿，哈，我什么都会！厉害了我的。。。。。。":user.habit }</p>
				</div>
				<div class="clear_float"></div>
			</div>	

			<div class="my_tougao">
				<p>我的投稿（${user.noticeCount }）</p>
			</div>
			<div class="content_left" id="newsList">
				<c:forEach items="${NewsData }" var="data">
					<div class="one_topic">
						<div class="user_tou">
							<a ><img class="img_100" src="${user.imageUrl }"></a>
						</div>
						<div class="user_info">
							<p>
								<a>${user.name}</a>
							</p>
							<p>${user.school}</p>
							<div class="clear_float"></div>
							<p>${data.createTime.substring(0,19) } ${user.school}</p>
						</div>
						<div class="clear_float"></div>
						<div class="one_topic_content">
							<p>${data.content }</p>
							<div style="width:442px;">
								<c:forEach var="image" items="${data.pictures }" >
									<div class="up_img_container">
										<img src="${image.url.split(',')[0] }">
									</div>
								</c:forEach>
							</div>
							<div class="clear_float"></div>
						</div>
						<div class="showimg">
							<div class="jq22-content bgcolor-3">
								<ul class="pgwSlideshow">
									<c:forEach var="image" items="${data.pictures }" >
										<li><img src="${image.url.split(',')[0] }"></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="content_detail">
							<p>阅读：${data.clickCount}</p>
							<div class="do_action">
								<c:if test="${data.isLike eq 0 }">
									<a class="zan" href="javascript:;" noticeID="${data.PKID}" type="0" id="${data.PKID}">点赞<i>0</i>（<span>${data.like}</span>）</a>
								</c:if>
								<c:if test="${data.isLike eq 1 }">
									<a class="zan" href="javascript:;" style="color: red;" noticeID="${data.PKID}" type="1">点赞<i>0</i>（<span>${data.like}</span>）</a>
								</c:if>
								&nbsp;|&nbsp;
								<a class="comment" href="javascript:;">评论（<span>${data.commentCount }</span>）<i><img  src="<%=basePath %>images/wenewspc/tr.png"></i></a>

								<c:if test="${data.isActivity eq 1 }">
									&nbsp;|&nbsp;
									<a class="toupiao" href="javascript:;">我要投票</a>
									<div class="my_toupiao">
										<div class="toupiao_user">
											<c:if test="${user.imageUrl!=null}">
												<img class="img_100" src="${user.imageUrl }">
											</c:if>
											<c:if test="${user.imageUrl==null}">
												<img class="img_100" src="${img }">
											</c:if>
										</div>
										<p>${user.name}<i>0</i></p>
										<p><span>${data.voteCount }</span>票</p>
										<c:if test="${data.isVote eq 0 }">
											<button noticeID="${data.PKID }" class="toupiao_btn" type="button">投</button>
										</c:if>
										<c:if test="${data.isVote eq 1 }">
											<button style="background:#CDCDCD ;border-radius: 4px; width: 38px;height: 20px;font-size: 12px;color: white;border: none; margin-top: 4px;cursor: pointer;" type="button">投</button>
										</c:if>
										
									</div>
								</c:if>
								
							</div>
							<div class="clear_float"></div>
							
								<div class="comment_detail">
									<form onSubmit="return change();">
										<input type="text" placeholder="说点什么好呢。">
										<input type="submit" class="submit_comment" value="发表评论" noticeID="${data.PKID}">
									</form>
									
										<div class="comments">
											<c:forEach var="Comment" items="${data.comment }">
												<div class="user_tou">
													<a href="<%=basePath %>WeNews/User?Uid=${Comment.userID}"><img class="img_100" src="${Comment.student.imageUrl }"></a>
												</div>
												<div class="comment_content">
													<a href="user.html">${Comment.student.name }</a>
													<p>${Comment.content }</p>
												</div>
												<p>${Comment.createTime }</p>
												<div class="clear_float"></div>
											</c:forEach>
											<div class="comment_more">
												<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${data.PKID }">查看更多></a>
											</div>
											<div class="clear_float"></div>
										</div>
									
								</div>
						</div>
						
						
						
					</div>
					<div class="clear_float"></div>
				</c:forEach>
			</div>
		</div>
		<input type="hidden" id="_page_" value="2">
		<input type="hidden" id="type" name="type" value="2" />
		<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		<script type="text/javascript">

			var i = 1; //设置当前页数 
			$(window).scroll(function() {	
				console.log(getScrollTop() + getWindowHeight() == getScrollHeight())
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					 $("#NotMore").remove(); 
					 $("#newsList").append(getLoading());
					 $.getJSON("<%=basePath%>Notices/getMyNoticesList?sina=<%=SmBaseUtil.Random()%>",
						{
						 pageNumber:$('#_page_').val(),
						 pageSize:10,
						 submitType:'phone'
						 },function(json){ 
			                if(json && json.Status==1){ 
			                	var html="";	
			                	var basePath=$("#basePath").val();
			                    $.each(json.data,function(index,array){ 
			                    	var area=$("#_area_").val();
			                    	var basePath=$("#basePath").val();
			                   			var image="";
			                   			var imageLun="";
			                   		 	if (array.pictures.length>0) {
				                    		for (var i = 0; i < array.pictures.length; i++) {
				                    				image+='				<div class="up_img_container">'
					                    			image+='					<img src="'+array.pictures[i].url.split(",")[0] +'">'
					                    			image+='				</div>'
					                    			imageLun+='<li><img src="'+array.pictures[i].url.split(",")[0]+'"></li>'
											}
										}
			                   			
			                   		 	var img="";
			                   		 	if (array.student.image!=null) {
											img=array.student.image.url.split(',')[0];
										}else{
											img='${img}';
										}
			                   		 	var likeHtml="";
			                   			if (array.isLike==0) {
			                   				likeHtml='<a class="zan" href="javascript:;"  noticeID="'+array.pkid+'" type="0">点赞<i>0</i>（<span>'+array.like+'</span>）</a>'
										}else{
											likeHtml='<a class="zan" href="javascript:;" style="color: red;" noticeID="'+array.pkid+'" type="1">点赞<i>0</i>（<span>'+array.like+'</span>）</a>'
										}
										
			                   		 	var comment="";
			                   			if (array.comment.length>0) {
			                   				for (var i = 0; i < array.comment.length; i++) {
			                   					var imgc="";
				                   				var name="游客";
					                   		 	if (array.comment[i].student!=null) {
													imgc=array.comment[i].student.image.url.split(',')[0];
													name=array.comment[i].student.name;
												}else{
													imgc='${img}';
												}
				                   				comment+=' <div class="user_tou">';
				                   				comment+=' <a href="<%=basePath %>WeNews/User?Uid='+array.comment[i].userID+'"><img class="img_100" src="'+imgc +'"></a>';
				                   				comment+=' </div>';
				                   				comment+=' <div class="comment_content">';
				                   				comment+=' <a href="<%=basePath %>WeNews/User?Uid='+array.comment[i].userID+'">'+name+'</a>';
				                   				comment+=' <p>'+array.comment[i].content +'</p>';
				                   				comment+=' </div>';
				                   				comment+=' <p>'+array.comment[i].createTime+'</p>';
				                   				comment+=' <div class="clear_float"></div>';
											}
										};
			                   		 	
										var isActivityHtml="";
										if (array.isActivity==1) {
											var voteHtml="";
											if(array.isVote==0){
												voteHtml='<button noticeID="'+array.pkid+'" class="toupiao_btn" type="button">投</button>';
											}else{
												voteHtml='<button style="background:#CDCDCD ;border-radius: 4px; width: 38px;height: 20px;font-size: 12px;color: white;border: none; margin-top: 4px;cursor: pointer;" type="button">投</button>';
											}
											
											isActivityHtml+='							&nbsp;|&nbsp;<a class="toupiao" href="javascript:;">我要投票</a>'
											isActivityHtml+='								<div class="my_toupiao">'
											isActivityHtml+='									<div class="toupiao_user"><img class="img_100" src="${user.imageUrl }"></div>'
											isActivityHtml+='									<p>${user.name}<i>0</i></p>'
											isActivityHtml+='								<p><span>'+array.voteCount+'</span>票</p>'
											isActivityHtml+=voteHtml
											isActivityHtml+='								</div>'
										}	
										
			                   			html+='			<div class="one_topic">'
			                   			html+='						<div class="user_tou">'
			                   			html+='							<a href="user.html"><img class="img_100" src="${user.imageUrl }"></a>'
			                   			html+='						</div>'
			                   			html+='						<div class="user_info">'
			                   			html+='							<p>'
			                   			html+='								<a href="user.html">${user.name}</a>'
			                   			html+='							</p>'
			                   			html+='							<p>${user.school}</p>'
			                   			html+='							<div class="clear_float"></div>'
			                   			html+='							<p>'+array.createTime.substring(0,19) +' ${user.school}</p>'
			                   			html+='					</div>'
			                   			html+='						<div class="clear_float"></div>'
			                   			html+='						<div class="one_topic_content">'
			                   			html+='							<p>'+array.content+'</p>'
			                   			html+='							<div style="width:442px;">'
			                   			html+=									image
			                   			html+='							</div>'
			                   			html+='							<div class="clear_float"></div>'
			                   			html+='</div>'
			                   			html+='						<div class="showimg">'
			                   			html+='							<div class="jq22-content bgcolor-3">'
			                   			html+='								<ul class="pgwSlideshow">'
			                   			html+=imageLun
			                   			html+='								</ul>'
			                   			html+='							</div>'
			                   			html+='						</div>'
			                   			html+='						<div class="content_detail">'
			                   			html+='							<p>阅读：'+array.clickCount+'</p>'
			                   			html+='							<div class="do_action">'
			                   			html+=likeHtml
			                   			html+='								&nbsp;|&nbsp;'
			                   			html+='								<a class="comment" href="javascript:;">评论（<span>'+array.commentCount+'</span>）<i><img src="<%=basePath %>images/wenewspc/tr.png"></i></a>'
			                   			html+=isActivityHtml
			                   			
			                   			html+='								<div class="my_toupiao">'
			                   			html+='									<div class="toupiao_user"><img class="img_100" src="<%=basePath %>images/wenewspc/user.png"></div>'
			                   			html+='									<p>${user.name}<i>0</i></p>'
			                   			html+='								<p><span>'+array.voteCount+'</span>票</p>'
			                   			html+='									<button class="toupiao_btn" type="button">投</button>'
			                   			html+='								</div>'
			                   			html+='							</div>'
			                   			  
			                   			html+='							<div class="clear_float"></div>'
			                   			html+='							<div class="comment_detail">'
			                   			html+='								<form onSubmit="return change();">'
			                   			html+='									<input type="text" placeholder="说点什么好呢。">'
			                   			html+='									<input type="submit" noticeID="'+array.pkid+'" class="submit_comment" value="发表评论">'
			                   			html+='								</form>'
			                   			html+='								<div class="comments">'
			           					html+=comment
			                   			html+='									<div class="comment_more">'
			                   			html+='										<a href="<%=basePath %>WeNews/NewsDetails?NoticeID='+array.pkid+'">查看更多></a>'
			                   			html+='									</div>'
			                   			html+='									<div class="clear_float"></div>'
			                   			html+='								</div>'
			                   			html+='							</div>'
			                   			html+='						</div>'
			                   			html+='					</div>'
			                   			html+='					<div class="clear_float"></div>';                   	
			                    	
			                    }); 
			                    $("#newsList").append(html); 
			                    
			                    if(json.data.length<=0){
			                    	$(".sk-spinner").remove();
									if($("#NotMore").length<=0){
										if($("#newsList")!=null){
											$("#newsList").append(getNoMoreInfo())
										}else{
											$("#newsList").append(getNoMoreInfo()); 
										}
									}
			                    }else{
				                    var _page_=$('#_page_').val();
				                    $('#_page_').val(parseInt(_page_)+1);
				                    $(".sk-spinner").remove();
									$("#newsList").append(getLoading());
				                    i++; 
			                    }
			                }else{ 
			                		$(".sk-spinner").remove();
									if($("#NotMore").length<=0){
										if($("#newsList")!=null){
											$("#newsList").append(getNoMoreInfo())
										}else{
											$("#newsList").append(getNoMoreInfo()); 
										}
									}
			                	return false; 
			                }
			               bindImgError();
			            });
				}
			});
	
       
		
		</script>
	</body>

</html>