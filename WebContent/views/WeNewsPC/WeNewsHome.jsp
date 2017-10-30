<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String img= basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
	request.setAttribute("img", img);
	
	Students user =(Students)request.getSession().getAttribute("StudentName");
	
	request.setAttribute("user", user);
	if(user==null){
		user = new Students();
	}
	request.setAttribute("uid", user.getID());
	request.setAttribute("DefaultSchoolImageUrl", basePath+"images/wenewspc/school.png");

%>
<html lang="en">
	<head>
		<title>新浪浙江微新闻社</title>
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
				
				
	

</style>
	</head>
	<body>
		<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<input type="hidden" value="uid=${uid}&backUrl=<%=basePath%>include/pictureback.html" id="uploadPicParam" >
		<input type="hidden"  id="ImageIDList" >
		
		<div class="container center_block">
			<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			<div class="content_left">
				<div class="ad_top_" style="width: 500px;height: 260px; border: 1px solid #DFE0E4;margin-top: 20px;box-shadow: 0 0 2px rgba(0,0,0,0.15);border-radius: 4px;">
					<div class="banner">
						<ul class="">
							<c:forEach var="Image" items="${ImageList }">
								<li>
									<a href="${Image.PUrl }"><img src="${Image.url.split(',')[0]}" alt=""></a>
								</li>
							</c:forEach>
						</ul>
						<div class="left-btn"></div>
						<div class="right-btn"></div>
						<div class="img-btn-list"></div>
					</div>
				</div>
				<div class="add_topic">
					<form id="from"  onsubmit="return saveNews();" method="post">
						<input type="text" class="draft_title" id="title" name="title" placeholder="请输入发稿标题~">
						<textarea  placeholder="请输入发稿内容~" name="content" id="content"></textarea>
						<hr>
						<input type="hidden" name="type" id="type" value="pc">
						<div class="wrapper wrapper-content animated fadeIn">
						<c:if test="${isLogin!=0}">
							<div class="row">
								<div class="col-sm-12" style="padding: 0px;">
									<div class="ibox float-e-margins">
										<div class="ibox-content">
											<div class="page-container">
												<div id="uploader" class="wu-example">
													<div class="queueList">
														<div id="dndArea" class="placeholder">
															<div id="filePicker"></div>
														</div>
													</div>
													<div class="statusBar" style="display:none;">
														<div class="progress">
															<span class="text">0%</span>
															<span class="percentage"></span>
														</div>
														<div class="info"></div>
														<div class="btns">
															<div id="filePicker2"></div>
															<div class="uploadBtn">开始上传</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:if>
							
							<div class="clear_float"></div>
							<div class="up_file_bg">
								<c:if test="${isLogin!=0}">
									<c:if test="${isNeedInfo==1}">
										<div style="text-align: right;">您还未完善信息<a style="color:#FF6B6B;" href="<%=basePath %>WeNews/UserInfo" >点击这里</a>，完善信息之后就可进行发稿</div>
									</c:if>
									<c:if test="${isNeedInfo==0}">
										<input type="submit" class="topic_submit" id="topic_submit" value="发布" />
									</c:if>
								
								</c:if>
								<c:if test="${isLogin==0}">
									<div style="text-align: right;">您还未<a style="color:#FF6B6B;" href="javascript:$('.zhezhao').fadeIn();;" >登录</a>，登录之后就可进行发稿</div>
								</c:if>
							</div>
							
						</div>

					</form>
				</div>
				
			<!--内容部分 （中间） -->
			<div id="newsList" style="min-width: 500px;">
		
			</div>
			</div>
			
			
		<div class="content_right">
				<!--右上角（前3个学校的发稿排行榜） -->
				<div class="school_top">
				<c:if test="${School_Adverts!=null }">
					<a href="${School_Adverts.url }" target="_blank">
						<div class="school_img">
							<img class="" src="${School_Adverts.imageurl}">
						</div>
					</a>
				</c:if>
				<c:if test="${School_Adverts==null }">
					<a href="#" target="_blank">
						<div class="school_img">
							<img class="" src="${(SchoolImageUrl!='')?SchoolImageUrl:DefaultSchoolImageUrl}">
						</div>
					</a>
				</c:if>
					
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
	<input type="hidden" id="_page_" value="1">
	<input type="hidden" id="_area_" value="0">
		<input type="hidden" id="type" name="type" value="2" />
	<input type="hidden" id="_pc_" value="${_pc_}">
	<input type="hidden" id="_isStu_" value="${_isStu_}">
	<input type="hidden" id="basePath" value="<%=basePath%>">
		<input type="hidden" id="picurl" value="<%=SmBaseGlobal.PictureService%>">
	
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		
		<script type="text/javascript">
		

		
		loadNews();
		bannerListFn(
			    $(".banner"),
			    $(".img-btn-list"),
			    $(".left-btn"),
			    $(".right-btn"),
			    5000,
			    true
			);
		    function saveReport() { 
		    	$("#form").ajaxSubmit(function(message) { 
		    		console.log(message);
		    		location.href = '<%=basePath%>/WeNewsHome';
		    	}); 
		    	return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
		    	} 
	
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					loadNews();
				}
			});
		
		
		var mydate = new Date();
		
		mydate.toLocaleString( ); //获取日期与时间
		
		//点击发布显示
		var idIndex=0;//图片上传id标识
		var isLogin='${isLogin}'; 
		var isNeedInfo='${isNeedInfo}';
		 function saveNews() { 
				//未登录时
				if(isLogin == "0") {
					$('.zhezhao').fadeIn();
					return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
				} else {
					var str_draft_title = $("#title").val();
					var str_draft_content = $("#content").val();
					
						if(str_draft_title==null||str_draft_title=="") {
							popup_msg("请输入发稿标题！");
							return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
						} else if(str_draft_content==null||str_draft_content=="") {
							popup_msg("请输入发稿内容！");
							return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
						} else {
							 $.ajax({
	         						url : "${path}/WeNewsAgency/Notices/addphoneNotices?",
	         						type:"POST",
	         						data : {
	         							'title' : str_draft_title,
	         							'content' : str_draft_content,
	         							'imageIDList' : $("#ImageIDList").val(),
	         							'DeviceType':<%=SmBaseGlobal.NoticeDeviceType.PC.getid()%>
	         						},
	         						success : function(json) {
	         							if(json.Status=="true"){
	         								popup_msg("发布成功");
	         								 window.location.href="<%=path%>/WeNewsHome?sina=<%=SmBaseUtil.Random()%>";
	         							}else{
	         								popup_msg(json.Message);
	         							}
	         						}
	         					});
							 return false;
						}
					
				}
		    	
		 } 
		 function parseDom(arg) {
			 	var objE = document.createElement("div");
			 	objE.innerHTML = arg;
			 	return objE.childNodes;
			 };
		 
		 function getIframeVal(response)  
		 {  
			 var result=parseDom(response._raw);
			 var urlParam=result[3].value.split('?').slice(1);
			 var json=eval("("+decodeURIComponent(urlParam)+")");
			 if(json.Status>0){
				 $("#ImageIDList").val($("#ImageIDList").val()+","+json.Status);
				 return json;
			 }else{
				 webToast(json.ErrorMsg,"bottom",3000);
			 }
			 return null;
			 
		 }  
		 function loadNews(){
			 $(".sk-spinner").remove();
			 $("#NotMore").remove();
			$("#newsList").append(getLoading()); 
			 $.getJSON("<%=path%>/weChatGroup/getWeNewsPaJsonrtList",
				{
				 _page_:$('#_page_').val(),
				 _area_:$("#_area_").val(),
				 _pc_:$("#_pc_").val(),
				 _isStu_:$("#_isStu_").val(),
				 type:2,
				 top:1
				 },function(json){ 
	                if(json && json.Status==1){ 
	                    $.each(json.Data,function(index,array){ 
	                    	if(array.noticesList!=null){
	                    		var html=addNewsDom(array.noticesList);	
		                        $("#newsList").append(html); 
		                        $(".sk-spinner").remove();
	                    	}
	                    	
	                    }); 
	                    if(json.Data.length<=0){
	                    	AddNoMoreDOM($("#newsList"));
	                    }else{
		                    var _page_=$('#_page_').val();
		                    $('#_page_').val(parseInt(_page_)+1);
		                    $(".sk-spinner").remove();
		                    $("#newsList").append(getLoading()); 
	                    }
	                }else{ 
	                	AddNoMoreDOM($("#newsList"));
	                	return false; 
	                }
	                bindImgError();
	            }); 

		 }
	</script>
		
	</body>

</html>

