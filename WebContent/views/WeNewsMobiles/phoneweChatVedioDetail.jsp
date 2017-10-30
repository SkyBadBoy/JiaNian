<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.WeixinUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
	String ispre = request.getParameter("ispre");
	if(ispre==null || (ispre!=null || ispre.equals("1"))){
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
		if(!WeixinUtil.isWeXin(request, requestUrl)){
			request.setAttribute("ispre", 1);
		}
		
	}
	
%>
<HTML lang="en">
<HEAD>

<META charset="utf-8">
<title>${Title}_新浪微新闻社</title>
<jsp:include page="/include/commonMobileCss.jsp"></jsp:include>
</head>
<style type="text/css">
.context {
	height: 250px;
	min-width: 224px;
}
html,body{
    /*height:100%;*/
    margin: 0 auto;max-width: 640px;background-color: #fff;}
</style>



<body style="background: #fff;overflow-x:hidden;overflow-y:auto">
	
	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
	<input type="hidden" id="type" name="type" value="${Type}" />
	<input type="hidden" id="dataContent" value='${DataContent}'>
	<input type="hidden" id="_page_" value="2">
	<input type="hidden" id="VideoType" value="${VideoType}">
	
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
		<div class="mt56">
		<!-- 广告展示轮播-->
		<div class="video_activity mt56">

</c:if>
<c:if test="${ispre==1}">
		<div class="mt56" style="margin-top:0px">
		<!-- 广告展示轮播-->
		<div class="video_activity mt56" style="margin-top:0px">
</c:if>

		<div class="index_title">${Data.name}</div>
        <div class="video_two">
            <span class="video_name">${Data.author}</span>
            <span class="video_time">${Data.createTime.substring(0,19)}</span>
            <span class="video_read">阅读：${Data.clickCount}</span>
        </div>
	 		<div id="module_1" class="swiper-container">
	            <div class="swiper-wrapper">
	                <div class="two swiper-slide"><a data-gallery="" href="${Data.image.url.split(',')[0]}" title="${Data.name}">
							<div id="${Data.PKID}" vid="${Data.URL}" name="div_videoList"></div>
	                </a></div>
	            </div>
	        </div>

		<div class="video_p" style="word-wrap:break-word">
		${Content}
		</div>
		<br/>
<div style="text-align: center;">
	<div class="goodJob">
        <div class="jobBtn" id="Praise">
            <a href="javascript:;">
            <c:if test="${likecount eq 0 }">
                <img src="<%=basePath %>images/weixinwenshe/boforeJob.png">
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
                	<span class="go_gref" style="color: red;">${LikeCount}</span>
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
       		<c:if test="${UserType eq 2 }">
       			 <div class="giveBtn">
            		<a href="<%=basePath%>Students/phoneSinaReWard?uid=${UserID}&pid=${Data.PKID}">打赏TA</a>
       			 </div>
       		</c:if>
    </div>
 </div>
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
  <div class="headContent swiper-slide swiper-slide-active" style="margin-top: 25px;">
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
<footer style="background: #fff;padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;margin-bottom: 50px;">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
	</footer>


<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div id="blueimp-gallery" class="blueimp-gallery">
		<div class="slides"></div>
		<h3 class="title"></h3>
		<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a
			class="play-pause"></a>
		<ol class="indicator"></ol>
	</div>
		<label style="display: none;" id="localId"></label> 
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
 <div id="go">
        <div class="wechat_shop">
            <img src="http://pict.wutuo.help/PictureService/upload/banner/QRCode.png" alt="">
            <p class="go_name">关注公众号,了解更多</p>
            <p class="go_pnum" id="go_pnum"><span>${VoteCount}</span>票</p>
            <button class="go_vote" style="    margin-top: 0px;" id="go_vote">投票</button>
        </div>
</div>
   
     
    </div>
	<jsp:include page="/include/commonMobileJs.jsp" />
	<script type="text/javascript">
        $('#aBtn').on('mousedown', function () {
        	//检测视频有没有被投票
        	$.ajax({
				url:"<%=basePath%>Notices/checkVoteNotices",
				data:{
					'nid':"${Data.PKID}"
				},
				success : function(obj) {
					$("#myModal1").modal("show");
		            setTimeout(function () {
		                $('.modal').css('overflow-y', 'hidden  ');
		                $('.modal-backdrop').css('background', 'rgba(0,0,0,0)')
		            }, 13)
		            if(obj.status){
		            	 $('#go_vote').attr("disabled","disabled");
			               $('#go_vote').css("background-color","#ddd");
			               $("#go_vote").html("已投票");
		            }
				}})
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
                 webToast("输入不能为空","top", 2000);
           }else if(areaLeng >= 100){
                 webToast("输入不能超过100个字","top", 2000);
           }else{
			$.getJSON("<%=path%>/Comment/addComment",
			{
				pid:"${Data.PKID}",
				userid:"${UserID}",
				content:encodeURI(areaVal),
				type:2
				 
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
		
		InitVideo(0);
		var iNum = true;
		var isclick=false;
		$(function () {
			$(".write_com").click(function(){$("#myModal2").modal("show")})
	        $('nav#menu').mmenu();
	        $.getJSON("<%=path%>/Notices/checkIslikeNotices",
			{
               	 nid:"${Data.PKID}"
			 },function(json){
				 if(isclick==false){
					 if(json && json.Data>0){
						 iNum=false;
					 }else{
						 iNum=true;
					 }
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
	                 $.getJSON("<%=path%>/Video/likeVideo",
						{
	                	 nid:"${Data.PKID}",
	                	 type:0
						 },function(json){});
	             }else{
	            	 $('.jobBtn').find('img').attr('src','<%=basePath%>images/weixinwenshe/boforeJob.png');
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
	                  }
	                 iNum = true;
	                 $.getJSON("<%=path%>/Video/likeVideo",
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
						 submitType:'phone'
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
							console.log("aaa");
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
                  	 type:3
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
   			           	 webToast("投票成功!", "top", 1000);
   								
   				 }else{
   					webToast(json.Message, "top", 1000);
   				 }
   				 
   			 });
              });
            
	</script>
</body>
</html> 