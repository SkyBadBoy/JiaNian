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
%>
<html lang="en">
<head>
    <title>光荣榜</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
	<style type="text/css">
	
	 table tr:nth-child(2n){background-color: #f7f3f0}

	 table {
	     width: 90%;
	     margin: 0 auto;
	 }
	</style>
</head>
	<body>
		<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
		<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
		
		
		 <div class="content_left">
        <ul class="list_check">
            <li>
            <div class="RankSetting1">阅读排行榜
						<ul>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=read&type=3">今日阅读排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=read&type=1">本周阅读排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=read&type=4">上周阅读排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=read&type=2">本月阅读排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=read&type=0">总阅读排行</a>
							</li>
							<li>
								<a style="color:#FF6B6B" href="<%=basePath %>WeNews/GloryList?rank=read&type=5&voteid=879990030779944960">微新闻大赛</a>
							</li>
						</ul>
					</div>
            </li>
            <li><div class="RankSetting1">学校投稿榜
						<ul>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=snotice&type=3">今日投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=snotice&type=1">本周投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=snotice&type=2">本月投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=snotice&type=0">总投稿排行</a>
							</li>
							<li>
								<a style="color:#FF6B6B" href="<%=basePath %>WeNews/GloryList?rank=snotice&type=5&voteid=879990030779944960">微新闻大赛</a>
							</li>
						</ul>
					</div></li>
            <li><div class="RankSetting1">小编投稿榜
						<ul>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=notice&type=3">今日投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=notice&type=1">本周投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=notice&type=4">上周投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=notice&type=2">本月投稿排行</a>
							</li>
							<li>
								<a href="<%=basePath %>WeNews/GloryList?rank=notice&type=0">总投稿排行</a>
							</li>
							<li>
								<a style="color:#FF6B6B" href="<%=basePath %>WeNews/GloryList?rank=notice&type=5&voteid=879990030779944960">微新闻大赛</a>
							</li>
						</ul>
					</div></li>
        </ul>
       <div class="container center_block ">

 	<div class="alllist" style=" margin-top: -20px;" >
 	<img alt="" src="/WeNewsAgency/images/list_bg.jpg" style="width: 100%">
				<table id="newsList">
					<tr>
						<th style="text-align: center;">排名</th>
						<c:if test="${rank eq 'read' }">
							<th style="text-align: center;">文章</th>
						</c:if>
						<c:if test="${rank != 'snotice' }">
							<th style="text-align: center;">作者</th>
						</c:if>
						
						<th style="text-align: center;">学校</th>
						<c:if test="${rank eq 'read' }">
							<th style="text-align: center;">阅读量</th>
						</c:if>
						<c:if test="${rank != 'read' }">
							<th style="text-align: center;">投稿量</th>
						</c:if>
						
					</tr>
				</table>
			</div>
		</div>
    </div>
			
		<input type="hidden" id="_page_" value="0">
		<input type="hidden" id="type" value="${type}">
	<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		<script type="text/javascript">
	
			loadNews();
			
			 if('${rank}'=='notice'){
				 $('.list_check>li:eq(2)').css("color","rgb(51, 51, 51)");
				 $('.list_check>li:eq(1)').css("color","rgb(158, 158, 158)");
				 $('.list_check>li:eq(0)').css("color","rgb(158, 158, 158)");
			 }else if('${rank}'=='snotice'){
				 $('.list_check>li:eq(1)').css("color","rgb(51, 51, 51)");
				 $('.list_check>li:eq(2)').css("color","rgb(158, 158, 158)");
				 $('.list_check>li:eq(0)').css("color","rgb(158, 158, 158)");
			 }else{
				 $('.list_check>li:eq(0)').css("color","rgb(51, 51, 51)");
				 $('.list_check>li:eq(1)').css("color","rgb(158, 158, 158)");
				 $('.list_check>li:eq(2)').css("color","rgb(158, 158, 158)");
			 }
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					loadNews();
				}else{
					$(".sk-spinner").remove();
					if($("#NotMore").length<=0){
						 $(".alllist").append(getNoMoreInfo());
					}
				}
			});
		
		function loadNews(){
			 $(".sk-spinner").remove();
			 $("#NotMore").remove(); 
			 $(".alllist").append(getLoading());
			 var url="<%=basePath%>/Ranking/getphoneNoticeReadRankingJson";
			 if('${rank}'=='notice'){
				 url="<%=basePath%>/Ranking/phonegetNoticesStudentRankingList";
			 }
			 if('${rank}'=='snotice'){
				 url="<%=basePath%>/Ranking/phonegetNoticesSchoolRankingList";
			 }
			 $.getJSON(url,
				{
				 	pageNumber : $('#_page_').val(),
				 	pageSize : 20,
					type : $("#type").val(),
					submitType:'pc',
					rand:Math.random(),
					voteid:'${voteid}'
				 },function(json){ 
	                if(json && json.Status==true){ 
	                	var html="";	
	                	if(json.Data.length<=0){
	                    	$(".sk-spinner").remove();
							if($("#NotMore").length<=0){
								$("#alllist").append(getNoMoreInfo());
							}
	                    }
	                    $.each(json.Data,function(index,array){  	
	                    	
	                    		html+='					<tr>';
	                    		if(parseInt(array.level)<=10){
	                    			html+='						<td class="list_num" style="text-align: center;"><span><em>'+(array.level)+'</em></span></td>';
	                    		}else{
	                    			html+='						<td class="list_num_other" style="text-align: center;"><span><em>'+(array.level)+'</em></span></td>';
	                    		}
	                    		 if('${rank}'!='notice'){
	                    			 html+='						<td class="title_list" style="text-align: center;">';
	 	                    		html+='							<a href="'+ $("#basePath").val() +'WeNews/NewsDetails?NoticeID='+array.id+'">';
	 	                    		html+='								<p class="word_rule rankAuthor2">'+array.title+'</p>';
	 	                    		html+='							</a>';
	 	                    		html+='						</td>';
	                			 }
	                    		 if('${rank}'!='snotice'){
	                    			 html+='						<td class="first_line" style="text-align: center;">';
	 	                    		html+='							<a href="'+ $("#basePath").val() +'WeNews/User?Uid='+array.uid+'">';
	 	                    		html+='							<p class="text_center rankAuthor2">'+array.author+'</p>';
	 	                    		html+='							</a>';
	 			                    html+='						</td>';
	                    		 }
	                    		 if('${rank}'!='snotice'){
			                	html+='						<td class="first_line" style="text-align: center;">';
			                    html+='							<p class="text_center rankAuthor2">'+array.school+'</p>';
			                    html+='						</td>';
	                    		 }
	                    		html+='						<td class="read_list center_block" style="text-align: center;">';
	                    		html+='							<p class="text_center">'+array.readCount+'</p>';
	                    		html+='						</td>';
	                    		html+='					</tr>';
	                    	
	                    }); 
	                    $(".sk-spinner").remove();
	                    $("#newsList").append(html); 
	                    var _page_=$('#_page_').val();
	                    $('#_page_').val(parseInt(_page_)+20);
	                    $(".sk-spinner").remove();
	                }else{ 
	                	$(".sk-spinner").remove();
						if($("#NotMore").length<=0){
							$("#alllist").append(getNoMoreInfo());
						}
	                	return false; 
	                }
	            }); 
		}
		</script>
	</body>

</html>


