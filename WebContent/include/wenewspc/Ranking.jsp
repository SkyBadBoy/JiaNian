<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	
%>
	<div class="content_right">
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="school_top">
					<a>
						<div class="school_img">
							<img class="" src="<%=basePath %>images/wenewspc/school.png">
						</div>
					</a>
					 <c:forEach var="SchoolRank" items="${SchoolRank }" varStatus="vs">
					 	<p class="list_school">
							<a href="${SchoolRank.areaID }"><b>${SchoolRank.SRegion.name }</b></a>
						</p>
						<p>投稿：${SchoolRank.count}篇</p>
						<p>今日排名：<em class="red">${vs.index+1}</em></p>
						<div class="clear_float"></div>
					 </c:forEach>
					<hr>
					<p>我的投稿数：20</p>
					<p>今日排名：20</p>
					<div class="clear_float"></div>
				</div>
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="match_top">
					<p>本周阅读量排行榜
						<a href="<%=basePath %>WeNews/GloryList?type=0">更多>></a>
					</p>
					<ul>
						<c:forEach var="ReadNotices" items="${ReadNotices }" varStatus="vs">
							<li>
								<span>${vs.index+1 }</span>
								<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${ReadNotices.PKID }">
									<p class="word_rule">${ReadNotices.content }</p>
								</a>
								<a href="<%=basePath %>WeNews/User?Uid=${ReadNotices.publishUser}" class="rankAuthor">${ReadNotices.author }</a>
							</li>
						</c:forEach>
					</ul>

				</div>
				
				<div class="match_top match_top_fix">
					<p>本周点赞排行榜
						<a href="<%=basePath %>WeNews/GloryList?type=1">更多>></a>
					</p>
					<ul>
						<c:forEach var="LikeNotices" items="${LikeNotices }" varStatus="vs">
							<li>
								<span>${vs.index+1 }</span>
								<a href="<%=basePath %>WeNews/NewsDetails?NoticeID=${LikeNotices.PKID }">
									<p class="word_rule">${LikeNotices.content }</p>
								</a>
								<a href="<%=basePath %>WeNews/User?Uid=${LikeNotices.publishUser}" class="rankAuthor">${LikeNotices.author }</a>
							</li>
						</c:forEach>
					</ul>		
				</div>
			</div>