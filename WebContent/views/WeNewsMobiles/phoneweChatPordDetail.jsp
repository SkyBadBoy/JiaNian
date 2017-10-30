<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.WeixinUtil"%>
<%@page import="wtb.core.model.Product"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
	String beforeJobUrl=basePath+"images/weixinwenshe/boforeJob.png";
	Product obj=(Product)request.getAttribute("Data");
	if(obj!=null && obj.getMemo()!=null && !obj.getMemo().isEmpty()){
		beforeJobUrl = basePath+"images/weixinwenshe/boforeJob_w.png";
	}
	if(obj==null){
		obj = new Product();
	}
	String ispre = request.getParameter("ispre");
	if(ispre==null || (ispre!=null || ispre.equals("1"))){
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
		if(!WeixinUtil.isWeXin(request, requestUrl)){
			request.setAttribute("ispre", 1);
		}
		
	}
	if(user!=null){
		request.setAttribute("userID", user.getID());
	}
	
	
%>
<HTML lang="en">
<HEAD>

<META charset="utf-8">
<title>${Title}_新浪微新闻社</title>
<jsp:include page="/include/commonMobileCss.jsp" />
</head>
<style type="text/css">
.context {
	height: 250px;
	min-width: 224px;
}

html,body{
    /*height:100%;*/
    margin: 0 auto;max-width: 640px;background-color: #fff;}
   
    p.com_word {
    color: #656565;
}
.footer{
background: #fff;
}
 .app_img_class{width:100%;height:100%;}

</style>

<c:if test="${NoticesVoteID eq 1 and VoteHeadUrl!=null }"> 
<style>

.details_bk{background: url("${VoteMiddleUrl}?v=1.1") repeat-y;  background-size: 100% 100%;position: relative}
        .comment{min-height:80px;background: none}
        .headWord{background: none;color: #fff;}
        .bk_head{background: url("${VoteHeadUrl}") no-repeat ;  background-size: 100%; width: 100%;  }
        .footer{background: url("${VoteFootUrl}?v=1.1") repeat-y;  background-size: 100% 100%;width: 100%}
        .bk_footer{background: url("${VoteFootUrl}");  background-size: 100% 100%; width: 100%; margin-bottom: 30px;}
        .bk_a{position: absolute;top: 20px;}
        #module_1{margin-top: -60px}
        @media only screen and (device-height :480px) and (-webkit-device-pixel-ratio:2){/*4 4s*/
            #module_1{margin-top: -40px}
        }
        @media (device-height:568px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone5 */
            #module_1{margin-top: -40px}}
        @media (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2){/*6*/
            #module_1{margin-top: -50px}}
        @media (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3){/*6+*/
            #module_1{margin-top: -70px}}
            
           
</style>
</c:if>

<body >
	
	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
	<input type="hidden" id="type" name="type" value="${Type}" />
	<input type="hidden" id="dataContent" value="${DataContent}">
	<input type="hidden" id="_page_" value="2">
	
<c:if test="${ispre==0}">
		<div class="header ">
		    <a class="header_left" href="#menu"></a>
		    <a href="<%=basePath%>Students/phoneSinaIndex" style="padding-top:35px;"><img src="${imageurl}" class="header_img" style="margin-top: 12.5px;"></a>
		    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?AreaId=${_area_}&SID=${_area_}">
		        <span>我要投稿</span>
		        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
		    </a>
		</div>
		<div style="height: 20px;"></div>
		<div class="mt56 details_bk" >
		<!-- 广告展示轮播-->
		<div class="video_activity mt56 bk_head">

</c:if>
<c:if test="${ispre==1}">
		<div class="mt56" style="margin-top:0px">
		<!-- 广告展示轮播-->
		<div class="video_activity mt56 " style="margin-top:0px">
</c:if>
		<div class="index_title">${Data.name}</div>
		  
		
<div class="video_two" style="width: 100%">

            <a href="<%=basePath%>Students/phoneSinaUserInfo?pid=${PublishUserID}&sina=<%=SmBaseUtil.Random()%>"><span class="video_name"><span style="text-decoration: underline;">${Data.author}</span><c:if test="${LevelIcon!=''}">
			      	 <img src="${LevelIcon}" style="width: 20px;margin-top: -5px;">
			      </c:if><c:if test="${PositionIcon!=''}">
		      	 <img src="${PositionIcon}" style="width: 15px;height:15px">
		      </c:if>
		      <c:forEach var="Image" items="${HonourIcon}">
				<img src="${Image}" style="width: 15px;height:15px">
			</c:forEach>
		      </span>
          </a>
            <c:if test="${ispre==0}">
            	<span class="video_time" style="">${Data.createTime.substring(0,19)}</span>
            </c:if>
            <span class="video_read">阅读：${Data.clickCount}</span>
</div>
 		<c:if test="${ImageCount==1}">
	 		<div id="module_1" class="swiper-container"  style="width: 100%">
	            <div class="swiper-wrapper">
	                <div class="two swiper-slide"><a data-gallery="" href="${Data.image.url.split(',')[0]}" title="${Data.name}">
	                	<c:if test="${Type==3}">
							<div id="${Data.PKID}" vid="${Data.URL}" name="div_videoList"></div>
						</c:if> 
						<c:if test="${Type!=3}">
							<img src="${Data.image.url.split(',')[0]}"  class="img_response"/>				 		
						</c:if>  
	                	
	                </a></div>
	            </div>
	        </div>
		</c:if> 

		<c:if test="${ImageCount>1}">
			<div id="module_1" class="floor swiper-container relative-container context">
 				<div class="swiper-wrapper" id="gallery">
					<c:forEach var="Image" items="${ImageList}">
						<div class="two swiper-slide">
							<a href="${Image.url.split(',')[0]}" data-gallery="">
							<img class="img_response"
								src="${Image.url.split(',')[0]}" />
							</a>
						</div>
					</c:forEach>
				</div> 
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
			</div>
		</c:if>
		 <script>
			var swiper = new Swiper('#module_1.swiper-container', {
	            pagination: '.swiper-pagination',
	            paginationClickable: true,
	            loop: true,
	            loopAdditionalSlides: 0,
	            autoplay: 1000000, //可选选项，自动滑动
	            autoplayDisableOnInteraction: false
	        });
        </script>
		<div class="video_p" style="word-wrap:break-word">
		${Content}
		</div>
		<br/>
<div style="text-align: center;">
	
	<c:if test="${ Data.memo!=null}">
	<div class="goodJob" style="height:auto;">
		<div class="sz_header"></div>
	        <div class="sz_body">${Data.memo}</div>
		<div class="sz_footer">
	
	</c:if>
	 <c:if test="${ Data.memo==null or Data.memo==''}">
	 <div class="goodJob" style="height:auto;border-top: 0px solid #ededed;">
	 <div>
	 </c:if>
        <div class="jobBtn" id="Praise">
            <a href="javascript:;">
            <c:if test="${likecount eq 0 }">
                <img src="<%=beforeJobUrl %>">
                 <c:if test="${LikeCount>0}">
                	<span class="go_gref" >${LikeCount}</span>
	            </c:if>
	            <c:if test="${LikeCount<=0}">
	            	<span class="go_gref" >0</span>
	            </c:if>
            </c:if>
            <c:if test="${likecount ne 0 }">
           		 <img src="<%=basePath %>images/weixinwenshe/afterJob.png">
           		 <c:if test="${LikeCount>0}">
                	<span class="go_gref" style="color: red;" >${LikeCount}</span>
	            </c:if>
	            <c:if test="${LikeCount<=0}">
	            	<span class="go_gref" >0</span>
	            </c:if>
            </c:if>
            
            </a>
        </div>
       	<c:if test="${IsActivity }">
	        <div class="votedBtn">
	            <a id="aBtn"  >投票(<span >${VoteCount}</span>)</a>
	        </div>
       </c:if>
       <c:if test="${!IsActivity and userID==PublishUserID}">
	        <div class="votedBtn">
	            <a  href="<%=basePath %>Activity/phoneTeacherReview?nid=${Data.PKID}&aid=${applyListID}&sina=<%=SmBaseUtil.Random()%>"
	            <c:if test="${ Data.memo!=null}">
	            		style="background: url(<%=basePath %>images/weixinwenshe/msdp_b.png);
	            </c:if>
	             <c:if test="${ Data.memo==null or Data.memo==''}">
	            		style="background: url(<%=basePath %>images/weixinwenshe/msdp.png);
	            </c:if>
    background-size: 90% 101%;
    background-repeat: no-repeat;
    border: 0px solid #ddd;
    border-radius: 0;"  ></a>
	        </div>
       </c:if>
        <div class="giveBtn">
            <a href="<%=basePath%>Students/phoneSinaReWard?uid=${PublishUserID}&pid=${Data.PKID}">打赏TA</a>
        </div>
        </div>
    </div>
 </div>

		<br/>
    </div>
 <br/>
 <c:if test="${Banner_Adverts!=null }">
 
 <%--  app推广广告位   --%>
<a href="${Banner_Adverts.url }" >
 <!--a href="<%=basePath%>Activity/phoneSummerCampApply?cid=842041063417974784"-->
 <img src="${Banner_Adverts.imageurl }"  style="height: 100px;" class="img_response">
 </a>
 </c:if>
 <c:if test="${Banner_Adverts == null }">
 
 <%--  app推广广告位   --%>
<a href="<%=basePath%>Students/phoneAppDownLoad?sina=<%=SmBaseUtil.Random()%>" >
 <!--a href="<%=basePath%>Activity/phoneSummerCampApply?cid=842041063417974784"-->
 <img src="<%=basePath%>images/bannerApp2.jpg?sina=<%=SmBaseUtil.Random()%>"  style="height: 100px;" class="img_response">
 </a>
 </c:if>
  <div class="headContent swiper-slide swiper-slide-active">
        <div class="headWord">
            评论
        </div>
         <div class="personal-border  border-bottom"></div>   
    </div>
    <div id="comments">
    	<c:if test="${CommentCount>0}">
    		<c:forEach var="Comments" items="${CommentData}">
			    <div class="comment">
			        <img class="comment_img" src="${Comments.userImage }">
			        <div style="float: left;margin-left: 5%;width: 80%">
			            <p class="com_word" style="margin:0;">${Comments.userName }<lable style="margin-left: 10px;font-size: 13px;">${Comments.fromtTime }</lable></p>  
			            <p class="com_content" style="margin:0;color: #333;padding: 4px 0;">${Comments.content }</p>
			           
			        </div>
			    </div>
			 </c:forEach>
		  </c:if>  
		  <c:if test="${CommentCount eq 0}">
		  <div style="text-align: center;margin-top: 25px;" id="nothiing">
		  	还没有人给我评价~
		  </div>
		  </c:if>
    </div>

	</div>
	<div class="write_com" >
    <div class="write_com_cont">
  
    </div>
    <img class="write_com_icon" src="<%=basePath%>images/write_icon.png" >
    <span class="write_com_head">写评论</span>
    <img class="write_com_smile" style="border-radius: 50%;" src='${CommentImageUrl}'>

</div> 

<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" >
    <div style="width: 100%;position: absolute;bottom: 0;background: #efefef;height: 150px">
        <div style='width: 100%;height: 50px;line-height: 50px;position: relative;'>
            <a onclick="cancelModal()" style="position: absolute;left: 34px;top: 0">取消</a>
            <p style="margin: 0;width: 100%;height: 50px;line-height: 50px;text-align: center;">写评论</p>
            <a id="postTo" href="javascript:;" style="position: absolute;right: 34px;top: 0">发送</a>
        </div>
        <form>
            <textarea style="width: 90%;margin-left: 5%;height: 79px;padding:0;resize: none;"></textarea>
        </form>
    </div>
</div>
<c:if test="${ischance ==true}">
<div onclick="window.top.location  = '<%=basePath%>Students/phoneSinaChanceReWard'" style="position: fixed;
    z-index: 100;
    bottom: 70px;
    right: 5%;">
 <img style="height: 50px" src='<%=basePath%>images/911/chance.png'>
</div>
</c:if>
<footer style="padding:10px 0;float: left;width: 100%" class="details_bk">
	<div style="text-align: center;margin-bottom: 50px;">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
	</footer>


<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
	<br />
	<br />

	<c:if test="${NoticesVoteID eq 1 and  VoteFootUrl!=null }">
	<img src="${VoteFootUrl}" class="bk_footer">
	</c:if>
	 
	<div id="blueimp-gallery" class="blueimp-gallery">
		<div class="slides"></div>
		<h3 class="title"></h3>
		<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a
			class="play-pause"></a>
		<ol class="indicator"></ol>
	</div>
	<label style="display: none;" id="localId"></label> 
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
 <div id="go" style="height: 58%;">
        <div class="wechat_shop">
            <img src="<%=basePath%>images/qrcode_for_gh_298035d75ad7_430.jpg" alt="">
            <p class="go_name">扫一扫关注，先一步知道投票结果！</p>
            <p class="go_pnum" id="go_pnum"><span>${VoteCount }</span>票</p>
            <button class="go_vote" id="go_vote">投票</button>
            <br/>
        </div>
</div>
   
     
    </div>

  	<jsp:include page="/include/commonMobileJs.jsp" />

	<script type="text/javascript">

	if(<%=obj.getMemo()!=null && !obj.getMemo().isEmpty() %>){
		if($(".go_gref").attr("style")=="" || $(".go_gref").attr("style")== undefined  ){
			$(".go_gref").attr("style","color:#ffffff");
		}
		
	}
	$.ajax({
		url:"<%=basePath%>Notices/checkVoteNotices",
		data:{
			'nid':"${Data.PKID}",
			sina:Math.random()
		},
		success : function(obj) {
			
            setTimeout(function () {
                $('.modal').css('overflow-y', 'hidden');
                $('.modal-backdrop').css('background', 'rgba(0,0,0,0)');
            }, 13);
            if(obj.status){
            	 $('#go_vote').attr("disabled","disabled");
	             $('#go_vote').css("background-color","#ddd");
	             $("#go_vote").html("已投票");
            }
            if(obj.VoteCount){
            	$('#aBtn').find('span').text(obj.VoteCount);
            }
		}});
	
	function callbackFun(){
		$.ajax({
			url:basePath+"Notices/shareAddIntegral",
			data:{
				'sid':'${PublishUserID}',
				'newsid':'${NID}'
			},
			success : function(obj) {
			}});
	}

        $('#aBtn').on('mousedown', function () {
        	
        	$("#myModal1").modal("show");
        });
	   function cancelModal(){
	   	$("#myModal2").modal("hide");
	   }
	    $('.modal').css('overflow-y','hidden  ')
	 var date = new Date().getTime();;
		function getLocalTime(nS) {

            return new Date(parseInt(nS)).toLocaleString().substr(5,5).replace(/\//g, ".");
        }
        var newDate = getLocalTime(date);
	 $('#postTo').on('mousedown',function(){

            var areaVal = $('#myModal2').find('form textarea').val()
            var areaLeng = $('#myModal2').find('form textarea').val().length
           if(areaVal == ''){
                 webToast("输入不能为空","bottom", 2000);
           }else if(areaLeng >= 100){
                 webToast("输入不能超过100个字","bottom", 2000);
           }else{
			$.getJSON("<%=path%>/Comment/addComment",
			{
				pid:"${Data.PKID}",
				userid:"${UserID}",
				content:encodeURI(areaVal),
				type:1
				 
			},function(json){
				if(json.status){
					if ($("#nothiing").show()) {
						$("#nothiing").hide();
					}
		              $('#comments').prepend('<div class="comment" style="width: 100%;margin-left: 5%;">'+
		            '<img src="'+json.img+'" style="float: left;width: 35px;height: 35px;border-radius: 18px">'+
		           '<div style="float: left;margin-left: 5%;width: 80%">'+
		        '<p class="com_word" style="margin:0;">'+json.name+' <lable style="margin-left: 10px;font-size: 13px;">'+json.time+'</lable></p> '+
		        ' <p class="com_content" style="margin:0;color: #333;padding: 4px 0;">'+areaVal+'</p>'+
		       
		        '</div>'+
		        '</div>')
		           $('#myModal2').modal('hide');
		            $('#myModal2').find('form textarea').val('')
				}	 

			});
           }

        })
		
		InitVideo(1);
		var iNum = true;
		var isclick=false;
		$(function () {
			$(".write_com").click(function(){$("#myModal2").modal("show")})
	        $('nav#menu').mmenu();
	        $.getJSON("<%=path%>/Notices/checkIslikeNotices",
			{
               	 nid:"${Data.PKID}",
               	 sina:Math.random()
			 },function(json){
					 if(json && json.Data>0){
						 iNum=false;
						 $('.jobBtn').find('img').attr('src','<%=basePath%>images/weixinwenshe/afterJob.png');
						 $('.jobBtn').find('span').attr("style","color:red");
					 }else{
						 iNum=true;
					 }
				 
				 
			 });
	      
	     
	        $('.jobBtn').click(function(){
	        	isclick=true;
	            if(iNum){
	            	$('.jobBtn').find('img').attr('src','<%=basePath%>images/weixinwenshe/afterJob.png');
	                 var oAdd = $('.jobBtn').find('span').html()
	                  if(oAdd=="0"){
	                	  $('.jobBtn').find('span').html("1");
	                  }else{
	                	  $('.jobBtn').find('span').html((Number(oAdd)+1));
	                  }
	                 $('.jobBtn').find('span').attr("style","color:red");
	                 iNum = false;
	                 $.getJSON("<%=path%>/Notices/likeNotices",
						{
	                	 nid:"${Data.PKID}",
	                	 type:0
						 },function(json){});
	             }else{
	            	 $('.jobBtn').find('img').attr('src','<%=beforeJobUrl%>');
	                 var aAdd = $('.jobBtn').find('span').html()
	                 if(aAdd=="赞!"){
	                	  $('.jobBtn').find('span').html("赞!");
	                  }else{
	                	  var resu=(Number(aAdd)-1);
	                	  if(resu<=0){
	                		  resu="0";
	                	  }
	                	  $('.jobBtn').find('span').html(resu);
	                	  $('.jobBtn').find('span').attr("style","");
	                	  if(<%=obj.getMemo()!=null && !obj.getMemo().isEmpty() %>){
	                			
	                				$(".go_gref").attr("style","color:#ffffff");
	                			
	                			
	                		}
	                  }
	                 iNum = true;
	                 $.getJSON("<%=path%>/Notices/likeNotices",
						{
		                	 nid:"${Data.PKID}",
		                	 type:1
						 },function(json){});
	             }
	             
	             
	         })
	        
	        

	    
		
		 //$('.modal').css('overflow-y','hidden  ');
	
		});

           $(function() {
        	   
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					 $("#NotMore").remove(); 
					 $("#comments").append(getLoading());
					 $.getJSON("<%=basePath%>Comment/getCommentList",
						{
						 pageNumber:$('#_page_').val(),
   						 nid:'${Data.PKID}',
						 pageSize:10,
						 submitType:'phone',
						 sina:"<%= SmBaseUtil.Random() %>"
						 },function(json){ 
			                if(json && json.Status==1){ 
			                	
			                	var tmp="";
			                    $.each(json.CommentData,function(index,array){ 

					                    tmp+=' <div class="comment">'+
			        '<img class="comment_img" src="'+array.userImage.split(",")[0]+'">'+
			        '<div style="float: left;margin-left: 5%;width: 80%">'+
			            '<p class="com_word" style="margin:0;">'+array.userName+'<lable style="margin-left: 10px;font-size: 13px;">'+array.fromtTime+'</lable></p>  '+
			            '<p class="com_content" style="margin:0;color: #333;padding: 4px 0;">'+array.content+'</p>'+
			           
			        '</div>'+
			    '</div>'
			                    }); 
			                    $("#comments").append(tmp); 
			                    if(json.CommentData.length<=0){
			                    	$(".sk-spinner").remove();
			                    }else{
				                   $(".sk-spinner").remove();
									$("#comments").append(getLoading());
				                    i++; 
			                    }
			                    var _page_=$('#_page_').val();
			                    $('#_page_').val(parseInt(_page_)+1);

			                }else{ 
			                	$(".sk-spinner").remove();	
			                	return false; 
			                }
			                bindImgError();
			            }); 
				}
			});
		});
           $('#go_vote').on('mousedown', function () {
        
               
    		$.getJSON("<%=path%>/Notices/voteNotices",
			{
               	 nid:"${Data.PKID}",
               	 sina:Math.random()
			 },function(json){
				 if(json.status==true){
				 	       var vote_oAdd = $('#go_pnum').find('span').html();
			               console.log(vote_oAdd);
			               $('#go_pnum').find('span').html((Number(vote_oAdd) + 1));
			               $('#aBtn').find('span').html((Number(vote_oAdd) + 1));
			               $('#go_vote').attr("disabled","disabled");
			               $('#go_vote').css("background-color","#ddd");
			               $("#go_vote").html("已投票");
			               $('#myModal1').modal('hide');
			               webToast("投票成功","bottom", 2000);
								
				 }else{
					webToast(json.Message, "bottom", 1000);
				 }
				 
			 });
           });
     
   		var $current = $(".video_p") 
		//在使用find()方法可以找到子元素然后遍历 
		$current.find("img").each(function(i){ 
			$(this).attr("width",window.screen.width-40);
			$(this).attr("height","auto");
		});
	</script>
</body>
</html> 