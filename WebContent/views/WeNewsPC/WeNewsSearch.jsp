<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String img= basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
	String schoolImg= basePath+SmBaseGlobal.SchoolDefaultImageUrl;
	request.setAttribute("img", img);
	request.setAttribute("SchoolImg", schoolImg);
%>
<html lang="en">
<head>
    <title>在线搜索</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
<body>
<jsp:include page="/include/wenewspc/commonNav.jsp"/>
<div class="container center_block">
	<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
    <div class="content_left">
			<!--内容部分 （中间） -->
			<div id="newsList">
			<c:if test="${NewsDataCount<=0}">
				<div style="text-align: center;font-size: 16px;padding: 10px;">未找到<span style="color:red;">${Search}</span>相关的搜索结果</div>
				<br/>
				<div style="text-align: left;font-size: 16px;padding: 10px;">您可能喜欢</div>
			</c:if>
			<c:forEach var="data" items="${NewsData }">
					<div class="one_topic">
						<div class="user_tou">
							
							<a href="<%=basePath %>WeNews/User?Uid=${data.student.PKID }">
								<c:if test="${data.student.image!=null}">
									<img class="img_100" src="${data.student.image.url.split(',')[0] }">
								</c:if>
								<c:if test="${data.student.image==null}">
									<img class="img_100" src="${img }">
								</c:if>
							</a>
						</div>
						<div class="user_info">
							<p>
								<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${data.PKID }" class="titleOverHide">${data.title }</a>
							</p>
							<p>${data.SRegion.name }</p>
							<div class="clear_float"></div>
							<p><span> ${data.createTime.substring(0,19) }</span> <span class="titleOverHide1">${data.author }</span></p>
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
									<a class="zan" href="javascript:;" style="color: red;" noticeID="${data.PKID}" type="1">取消赞<i>0</i>（<span>${data.like}</span>）</a>
								</c:if>
								
						
								&nbsp;|&nbsp;
								<a class="comment" href="javascript:;">评论（<span>${data.commentCount }</span>）<i><img src="<%=basePath %>images/wenewspc/tr.png"></i></a>
								<c:if test="${data.isActivity eq 1}">
										&nbsp;|&nbsp;<a class="toupiao" href="javascript:;">我要投票</a>
									<div class="my_toupiao">
										<div class="toupiao_user">
											<c:if test="${data.student!=null}">
												<img class="img_100" src="${data.student.imageUrl }">
											</c:if>
											<c:if test="${data.student==null}">
												<img class="img_100" src="${img }">
											</c:if>
										</div>
										<p>${data.author }<i>0</i></p>
										<p><span>${data.voteCount }</span>票</p>
										<c:if test="${data.isVote eq 0}">
											<button class="toupiao_btn" noticeID="${data.PKID}" type="button">投票</button>
										</c:if>
										<c:if test="${data.isVote eq 1 }">
											<button style="background:#CDCDCD ;border-radius: 4px; width: 38px;height: 20px;font-size: 12px;color: white;border: none; margin-top: 4px;cursor: pointer;" type="button">已投</button>
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
													<c:if test="${Comment.userID == null}">
														<a ><img class="img_100" src="${Comment.userImage }"></a>
													</c:if>
													<c:if test="${Comment.userID != null}">
														<a href="<%=basePath %>WeNews/User?Uid=${Comment.userID}"><img class="img_100" src="${Comment.userImage  }"></a>
													</c:if>
												</div>
												<div class="comment_content">
													<c:if test="${Comment.userID == null}">
														<a >${Comment.userName }</a>
													</c:if>
													<c:if test="${Comment.userID != null}">
														<a href="<%=basePath %>WeNews/User?Uid=${Comment.userID}">${Comment.userName }</a>
													</c:if>
													<p>&nbsp;&nbsp;&nbsp;&nbsp;${Comment.fromtTime }</p>
													<p  style="padding:5px">${Comment.content }</p>
												</div>
												
												<div class="clear_float"></div>
												<div class="comment_more">
													<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${data.PKID }">查看更多></a>
												</div>
												<div class="clear_float"></div>
											</c:forEach>
											
										</div>
									
								</div>
						</div>
					</div>
					<div class="clear_float"></div>
				</c:forEach>
				
			</div>				
    </div>
    
    
    
    
  		<div class="content_right">
				<div class="search_user">
					<p>相关用户</p>
					<hr>
					<ul>
						<c:forEach var="Student" items="${Student }">
							<li>
								<div class="search_userpic">
									<a href="<%=basePath %>WeNews/User?Uid=${Student.PKID}">
										<c:if test="${not empty Student.imageUrl }">
											<img class="img_100" src="${Student.imageUrl.split(',')[0] }">
										</c:if>
										<c:if test="${empty Student.imageUrl }">
											<img class="img_100" src="${img }">
										</c:if>
									</a>
								</div> 
								<div class="search_info">
									<p>
										<a href="<%=basePath %>WeNews/User?Uid=${Student.PKID}" class="rankAuthor3">${Student.name }</a>
									</p>
									<c:if test="${not empty Student.school }">
										<p>${Student.school }</p>
									</c:if>
									<c:if test="${empty Student.school }">
										<p>该学生还未设置学校哦~</p>
									</c:if>
								</div>
							</li>
						</c:forEach>
						<c:if test="${StudentCount<=0}">
							<div style="text-align: center;">未找到相关的用户</div>
							<br/>
							
						</c:if>
					</ul>
				</div>
				<div class="search_school">
					<p>相关学校</p>
					<hr>
					<ul>
					<c:forEach var="school" items="${School}">
						<li>
							<div class="search_userpic">
								<c:if test="${not empty school.logoID }">
									<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${school.areaID}"><img class="img_100" src="${school.logo.url.split(',')[0] }"></a>
								</c:if>
								<c:if test="${empty school.logoID}">
									<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${school.areaID}"><img class="img_100" src="${SchoolImg }"></a>
								</c:if>
							</div>
							<div class="search_info">
								<p>
									<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${school.areaID}">${school.company }</a>
								</p>
								<p>投稿数（${school.noticeCount}）</p>
							</div>
						</li>
				</c:forEach>
						<c:if test="${SchoolCount<=0}">
							<div style="text-align: center;">未找到相关的学校</div>
							<br/>
						</c:if>
						
					</ul>
				</div>

			</div>
			
</div>
<input type="hidden" id="Search" value="${Search}">
<input type="hidden" id="_page_" value="2">
	<input type="hidden" id="type" name="type" value="2" />
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/wenewspc/commonJs.jsp"/>
<script type="text/javascript">
function bindUserImgError(){
	$('.img_100').each(function() {
	    if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) { 
	    	//http://wenews.top/WeNewsAgency/img/tx_default.jpg
	      this.src = $("#basePath").val()+"img/tx_default.jpg"; 
	      } 
	   });

}

	bindUserImgError();
	
	var i = 1; //设置当前页数 
	$(window).scroll(function() {		
		if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
  			 $(".sk-spinner").remove();
			 $("#NotMore").remove(); 
			 $("#newsList").append(getLoading());
			 $.getJSON("<%=basePath%>WeNews/getWeNewNoticeList",
				{
				 pageNumber:$('#_page_').val(),
				 pageSize:10,
				 submitType:'phone',
					 type:0,
					 Search:$("#Search").val()
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
			                   		 		if(array.comment[i].student.image!=null){
			                   		 			imgc=array.comment[i].student.image.url.split(',')[0];
			                   		 		}else{
			                   		 			imgc='${img}';
			                   		 		}
											
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
									isActivityHtml+='									<div class="toupiao_user"><img class="img_100" src="'+img+'"></div>'
									isActivityHtml+='									<p>'+array.author+'<i>0</i></p>'
									isActivityHtml+='								<p><span>'+array.voteCount+'</span>票</p>'
									isActivityHtml+=voteHtml
									isActivityHtml+='								</div>'
								}	
	                   		 	
								
	                   		 	html+='	<div class="one_topic">'
								html+='	<div class="user_tou">'
								html+='		<a href="<%=basePath %>WeNews/User?Uid=array.student.PKID"><img class="img_100" src="'+img+'"></a>'
								html+='	</div>'
								html+='	<div class="user_info">'
								html+='		<p>'
								html+='			<a href="user.html">'+array.author+'</a>'
								html+='		</p>'
								html+='		<p>'+array.sregion.name +'</p>'
								html+='<div class="clear_float"></div>'
								html+='		<p>'+array.createTime.substring(0,19)+array.sregion.name +'</p>'
								html+='	</div>'
								html+='	<div class="clear_float"></div>'
								html+='	<div class="one_topic_content">'
								html+='		<p>'+array.content +'</p>'
								html+='		<div style="width:442px;">'
								html+=			image	
								html+='		</div>'
								html+='		<div class="clear_float"></div>'
								html+='	</div>'
								html+='	<div class="showimg">'
								html+='		<div class="jq22-content bgcolor-3">'
								html+='			<ul class="pgwSlideshow">'
								html+=imageLun
								html+='			</ul>'
								html+='		</div>'
								html+='	</div>'
								html+='	<div class="content_detail">'
								html+='		<p>阅读：'+array.clickCount+'</p>'
								html+='		<div class="do_action">'
								
								html+=likeHtml
									
								html+='			<a class="comment" href="javascript:;">评论（<span>'+array.commentCount+'</span>）<i><img src="<%=basePath %>images/wenewspc/tr.png"></i></a>'
								html+=isActivityHtml
								html+='			<div class="my_toupiao">'
								html+='				<div class="toupiao_user"><img class="img_100" src="<%=basePath %>images/wenewspc/user.png"></div>'
								html+='				<p>'+array.author+'<i>0</i></p>'
								html+='				<p><span>'+array.voteCount+'</span>票</p>'
								html+='				<button class="toupiao_btn" type="button">投</button>'
								html+='			</div>'
								html+='		</div>'
								html+='		<div class="clear_float"></div>'
								html+='		<div class="comment_detail">'
								html+='			<form onSubmit="return change();">'
								html+='				<input type="text" placeholder="说点什么好呢。">'
								html+='				<input type="submit" class="submit_comment" noticeID="'+array.pkid+'" value="发表评论">'
								html+='			</form>'
								html+='			<div class="comments">'
								html+=comment
								html+='				<div class="clear_float"></div>'
								html+='				<div class="comment_more">'
								html+='					<a href="<%=basePath %>WeNews/NewsDetails?NoticeID='+array.pkid +'">查看更多></a>'
								html+='				</div>'
								html+='				<div class="clear_float"></div>'
								html+='			</div>'
								html+='		</div>'
								html+='	</div>'
								html+='</div>'
								html+='<div class="clear_float"></div>'	;                    	
	                    	
	                    	
	                    }); 
	                    $("#newsList").append(html); 
	                    $(".sk-spinner").remove();
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


</script>
</body>
</html>