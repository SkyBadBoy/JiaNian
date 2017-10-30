<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String img= basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
	request.setAttribute("img", img);
%>
<html lang="en">
	<head>
		<title>微新闻社</title>
		<jsp:include page="/include/wenewspc/meta.jsp"/>
		 <jsp:include page="/include/wenewspc/commonCss.jsp"/>

		 <style type="text/css">
		.sk-spinner-fading-circle.sk-spinner{
		        margin-top:20px!important}
		        .a-tag-goods-info{
		        	padding:0!important;
		        }
		        .index_footer{
		        	margin:10px 0;
		        }
				.viewport * img{
					height:220px;
				}

</style>
	</head>
	<body>
	
		<input type="hidden" id="VideoType" value="${VideoType}">
		<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
			<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			<div class="content_left">
				
				
				
			<!--内容部分 （中间） -->
			</div>
			<c:if test="${VideoCount==0}">
			<div class="content_left" >
			<div style="text-align: center;width: 500px;padding: px;padding-top: 20px;">
			 	<img src="<%=basePath %>images/weixinwenshe/404.png" class="img-rounded">
    			<p style="padding: 10px;">该文章不存在或者已被作者删除！</p>
    			</div>
    			</div>
			</c:if>
			<c:if test="${VideoCount>0}">
				<div class="content_left" id="newsList">
					<div class="one_topic">
						<div class="user_tou">
							<a href="${ClickImageUrl }">
								<img class="img_100" src="${headImageUrl }">
							</a>
						</div>
						<div class="user_info">
							<p>
								<a href="javascript:void(0)"  class="titleOverHide">${video.title }</a>
							</p>
							<p><a  href="${SchoolUrl }">${SchoolName }</a></p>
							<div class="clear_float"></div>
							<p><span> ${video.createTime.substring(0,19) }</span> <a class="titleOverHide1" href="${ClickImageUrl}">${name }</a></p>
						</div>
						<div class="clear_float"></div>
						<div class="one_topic_content">
							
							<div style="width:400px;height: 200px;	">
								
								<div id="${video.PKID}" vid="${VID}" videotype="${video.type}" name="div_videoList" style="width:400px;height: 200px;	"></div>
	
							</div>
							<div class="video_p" style="word-wrap:break-word">
								${Content }
							</div>
							<div class="clear_float"></div>
						</div>
				
						<div class="content_detail">
							<p>阅读：${video.clickCount}</p>
							<div class="do_action">
								<c:if test="${isLike eq 0 }">
									<a class="zan" href="javascript:;" noticeID="${video.PKID}" type="0" id="${video.PKID}">点赞<i>0</i>（<span>${video.likeCount}</span>）</a>
								</c:if>
								<c:if test="${isLike eq 1 }">
									<a class="zan" href="javascript:;" style="color: red;" noticeID="${video.PKID}" type="1">点赞<i>0</i>（<span>${video.likeCount}</span>）</a>
								</c:if>
								&nbsp;|&nbsp;
								<a class="comment" onclick="loadCommentList(this,'${video.PKID }',1);"  href="javascript:;">评论（<span>${video.commentCount }</span>）<i><img src="<%=basePath %>images/wenewspc/tr.png"></i></a>
								<!-- c:if test="${video.isActivity eq 1 }"> -->
									&nbsp;|&nbsp;
									<a class="toupiao" href="javascript:;">我要投票</a>
									<div class="my_toupiao">
										<div class="toupiao_user">
											<img class="img_100" src="${headImageUrl }">
										</div>
										<p>${name }<i>0</i></p>
										<p><span>${video.voteCount }</span>票</p>
										<c:if test="${IsVote eq 0 }">
											<button noticeID="${video.PKID }" class="toupiao_btn" type="button">投票</button>
										</c:if>
										<c:if test="${IsVote eq 1 }">
											<button style="background:#CDCDCD ;border-radius: 4px; width: 38px;height: 20px;font-size: 12px;color: white;border: none; margin-top: 4px;cursor: pointer;" type="button">已投</button>
										</c:if>
										
									</div>
								<!-- /c:if> -->
								&nbsp;|&nbsp;<a class="qrdiv" type="1" pkid="${video.PKID }"  href="javascript:;">分享到微信</a>
							</div>
							<div class="comment_detail">
								<form onSubmit="return change();">
									<input type="text" placeholder="说点什么好呢。">
									<input noticeID="${video.PKID}" type="submit" CommentType="2" class="submit_comment" value="发表评论">
								</form>
								<div class="comments" id="comments">
								
								
								<c:forEach var="Comment" items="${video.comment }">
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
								</c:forEach>
									
									
									
								</div>
								<div class="clear_float"></div>
									<div class="comment_more">
										<a href="javascript:loadMoreComment()">查看更多></a>
									</div>
									<div class="clear_float"></div>
							</div>
						</div>
					</div>
					<div class="clear_float"></div>
			</div>
			
			</c:if>
			
			
			
			<div class="content_right">
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="school_top">
					<a>
						<div class="school_img">
							<img class="img_100" src="${(SchoolImageUrl!='')?SchoolImageUrl:DefaultSchoolImageUrl}">
						</div>
					</a>
					 <c:forEach var="SchoolRank" items="${SchoolRank }" varStatus="vs">
					 	<p class="list_school">
							<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${SchoolRank.areaID }"><b>${SchoolRank.SRegion.name }</b></a>
						</p>
						<p>投稿：${SchoolRank.count}篇</p>
						<p>
						<c:if test="${schoolType=='week'}">
							本周
						</c:if>
						<c:if test="${schoolType!='week'}">
							总
						</c:if>
						排名：<em class="red">${SchoolRank.level}</em></p>
						<div class="clear_float"></div>
					 </c:forEach>
					<hr>
					<c:if test="${Mycount!=null}">
						<p>本周我的投稿数：${Mycount}</p>
							<p 
						<c:if test="${Mycount==0}">
							style=" width:100%;   max-width: 120px;"
						</c:if>
						>本周排名：${Mylevel}</p>
						<div class="clear_float"></div>
					</c:if>
					
				</div>
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="match_top">
					<p>
					<c:if test="${readType=='week'}">
						本周
					</c:if>
					阅读排行榜
						<a href="<%=basePath %>WeNews/GloryList?type=0">更多>></a>
					</p>
					<ul>
						<c:forEach var="ReadNotices" items="${ReadNotices }" varStatus="vs">
							<li>
								<span>${ReadNotices.level }</span>
								<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${ReadNotices.PKID }">
									<p class="word_rule">${ReadNotices.title }</p>
								</a>
								<a href="<%=basePath %>WeNews/User?Uid=${ReadNotices.publishUser}" class="rankAuthor">${ReadNotices.author }</a>
							</li>
						</c:forEach>
					</ul>

				</div>
				
				<div class="match_top match_top_fix">
					<p>
					<c:if test="${noticeType=='week'}">
						本周
					</c:if>
					投稿排行榜
						<a href="<%=basePath %>WeNews/GloryList?type=1">更多>></a>
					</p>
					<ul>
						<c:forEach var="LikeNotices" items="${NoticesCount }" varStatus="vs">
							<li>
								<span>${LikeNotices.level }</span>
								<a href="<%=basePath %>WeNews/User?Uid=${LikeNotices.publishUser }">
									<p class="word_rule">${LikeNotices.student.name }</p>
								</a>
								<a href="<%=basePath %>WeNews/User?Uid=${LikeNotices.publishUser}" class="rankAuthor">${LikeNotices.count }</a>
							</li>
						</c:forEach>
					</ul>		
				</div>
			</div>
		</div>
		
		<!-- 隐藏的内容 -->
	<input type="hidden" id="_page_" value="2">
	<input type="hidden" id="type" name="type" value="2" />
	<input type="hidden" id="_area_" value="${_area_}">
	<input type="hidden" id="_pc_" value="${_pc_}">
	<input type="hidden" id="_isStu_" value="${_isStu_}">
	<input type="hidden" id="basePath" value="<%=basePath%>">

		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		<script type="text/javascript">
		InitVideo(0);

			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					loadMoreComment();
				}
			});
		
		
		function loadMoreComment(){
			$(".sk-spinner").remove();
			 $("#NotMore").remove(); 
			 $("#comments").append(getLoading());
			 $.getJSON("<%=basePath%>Comment/getCommentList",
				{
				 pageNumber:$('#_page_').val(),
					 nid:'${video.PKID}',
				 pageSize:10,
				 submitType:'phone'
				 },function(json){ 
	                if(json && json.Status==1){ 
	                	
	                	var tmp="";
	                    $.each(json.CommentData,function(index,array){ 

	                    	 tmp+='<div class="user_tou">';
	                    	 var imgc=$("#basePath").val()+'img/tx_default.jpg';
	                    	 if(array.userImage!=null && array.userImage!=""){
	                    		 imgc= array.userImage.split(',')[0];
	                    	 }
	                    	 if(array.userID==0){
	                    		 tmp+='<a ><img class="img_100" src="'+imgc+'"></a>';
	                    	 }else{
	                    		 tmp+='<a href="<%=basePath %>WeNews/User?Uid='+array.userID+'"><img class="img_100" src="'+imgc+'"></a>';
	                    	 }
	                    	 tmp+='</div><div class="comment_content">';
	                    	 if(array.userID==0){
	                    		 tmp+='<a >'+array.userName+'</a>';
	                    	 }else{
	                    		 tmp+='<a href="<%=basePath %>WeNews/User?Uid=${Comment.userID}">'+array.userName+'</a>';
	                    	 }
	                    	 tmp+='<p>&nbsp;&nbsp;&nbsp;&nbsp;'+array.fromtTime+'</p>';
	                    	 tmp+='<p style="padding:5px">'+array.content+'</p></div>';
	                    	 
	                    	 tmp+='<div class="clear_float"></div>';
						
	                    }); 
	                    $(".sk-spinner").remove();
	                    $("#comments").append(tmp); 
	                    if(json.CommentData.length<=0){
	                    	$("#comments").append(getNoMoreInfo());
	                    }else{
	                    	 var _page_=$('#_page_').val();
	 	                    $('#_page_').val(parseInt(_page_)+1);
	                    }
	                   

	                }else{ 
	                	$(".sk-spinner").remove();	
	                	return false; 
	                }
	                bindImgError();
	            }); 
		}
		
		
		</script>
	</body>

</html>

