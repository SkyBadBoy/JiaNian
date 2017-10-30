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

.news_head{width: 100%;}
.news_headline{position: absolute;top: calc(50% - 15px);width: 200px;height:30px;line-height:30px;text-align: center;left:calc(50% - 100px);font-size: 21px;font-weight: 600;}
.news_div{height:20px;line-height:20px;margin-bottom: 5px;font-size: 14px;position: relative}
.news_name{float: left;margin-left:10px;color: #7C8087}
.news_time{width:200px;;color: #7C8087;position: absolute;top: 0;left:calc(50% - 100px);text-align: center}
.news_read{float: right;margin-right:10px;color: #7C8087}
#module_1{margin: 0px 10px;}
.news_cont_div{margin: auto 10px;position: relative;padding-bottom:31px;background-color: #fff;border-radius:25px;}
.news_cont_header{width: 100%;}
.news_cont_footer{width: 100%;position: absolute;bottom: 0px;left: 0;}
.news_cont_body{background: url(<%=basePath%>images/news_match4.png) repeat-y;padding:15px 3% 0 3%;text-indent: 20px;line-height: 1.5;background-size: 100%;z-index: 9}
.news_ds_div{height: 80px;line-height: 80px;position: relative}
.news_ds_div_bk{width: 96%;position: absolute;bottom:10px;left: 0;margin: 0 2%;}
.news_ds_tp{height: 50px;line-height: 50px;border-radius: 100%;background-color: #48bc8a;color:#fff;position:absolute;top: 15px;left: calc(21% - 25px);width: 50px;text-align: center;color: #fff;}
.news_ds_dz{height: 50px;line-height: 50px;border-radius: 100%;color:#fff;position:absolute;top: 15px;left: calc(50% - 25px);width: 50px;text-align: center;color: #fff;}
.news_ds_dz_a{background-color: #ddd;color:#fff;border-radius: 100%;}
.news_ds_ds{height: 50px;line-height: 50px;border-radius: 100%;background-color: #5ebfe2;color:#fff;position:absolute;top: 15px;right: calc(20% - 21px);width: 50px;text-align: center;color: #fff;}

@media (device-height:480px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone4/4s */
    .news_ds_tp{height: 43px;line-height: 43px;width: 43px;top: 24px;left: calc(21% - 21.5px);}
    .news_ds_dz{height: 43px;line-height: 43px;width: 43px;top: 24px;left: calc(49.8% - 21.5px);}
    .news_ds_ds{height: 43px;line-height: 43px;width: 43px;top: 24px;right: calc(20.8% - 21.5px);}
}
@media (device-height:568px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone5 */
    .news_ds_tp{height: 43px;line-height: 43px;width: 43px;top: 24px;left: calc(21% - 21.5px);}
    .news_ds_dz{height: 43px;line-height: 43px;width: 43px;top: 24px;left: calc(49.8% - 21.5px);}
    .news_ds_ds{height: 43px;line-height: 43px;width: 43px;top: 24px;right: calc(20.8% - 21.5px);}
}
@media (device-height:667px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone6 */
    .news_ds_tp{height:50px;line-height: 50px;width: 50px;top: 16px;left:  calc(21% - 25px);}
    .news_ds_dz{height:50px;line-height: 50px;width: 50px;top: 16px;left:  calc(50% - 25px);}
    .news_ds_ds{height:50px;line-height: 50px;width: 50px;top: 16px;right:  calc(20.5% - 25px);}
}
@media (device-height:736px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone6 Plus */
    .news_ds_tp{height:55px;line-height: 55px;width: 55px;top: 10px;left:  calc(21% - 27.5px);}
    .news_ds_dz{height:55px;line-height: 55px;width: 55px;top: 10px;left:  calc(50% - 27.5px);}
    .news_ds_ds{height:55px;line-height: 55px;width: 55px;top: 10px;right:  calc(20.5% - 27.5px);}
}


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
            
        .comment{background:url('<%=basePath%>images/news_match7.jpg') repeat-y}
        .header{background:url('<%=basePath%>images/news_match7.jpg') repeat-y}
        .footer_jb{background:url('<%=basePath%>images/news_match7.jpg') repeat-y}
        .write_com{background:url('<%=basePath%>images/news_match7.jpg') repeat-y}
        .pl_div{background:url('<%=basePath%>images/news_match7.jpg') repeat-y;border-top: 1px solid #ddd;border-top-left-radius:0;border-top-right-radius:0}
           
</style>
</c:if>

<body >
	
	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
	<input type="hidden" id="type" name="type" value="${Type}" />
	<input type="hidden" id="dataContent" value="${DataContent}">
	<input type="hidden" id="_page_" value="2">
	

 

		<div class="mt56" style="margin-top:0px">
		<!-- 广告展示轮播-->
		 <div style="position: relative">
        <img src="<%=basePath%>images/news_match1.jpg" class="news_head">
	        <div class="news_headline">美丽家乡发言人</div>
	    </div>
			
		<div class="video_activity mt56 " style="margin-top:0px">

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

		   <div class="news_cont_div">
        <img src="<%=basePath %>images/news_match3.png" class="news_cont_header">
        <div class="news_cont_body">${Content} </div>
            
       

        <img src="<%=basePath %>images/news_match5.jpg" class="news_cont_footer">
    </div>
		<br/>
<div style="text-align: center;">
	

	
	  <div class="news_ds_div">
        <img src="<%=basePath %>images/news_match6.png" class="news_ds_div_bk">
        <div class="news_ds_tp" id="aBtn" data-toggle="modal" data-target="#myModal1">投票</div>
        <div class="news_ds_dz "><a class="jobBtn news_ds_dz_a " href="javascript:;">
        <c:if test="${likecount eq 0 }">
   
	            	<span class="go_gref" likecount="${LikeCount}" >点赞</span>
	
            </c:if>
            <c:if test="${likecount ne 0 }">
	            <span class="go_gref" style="color:red;" >${LikeCount}</span>
            </c:if>
        
        </a></div>
        <a class="news_ds_ds" href="<%=basePath%>Students/phoneSinaReWard?uid=${PublishUserID}&pid=${Data.PKID}">打赏</a>
    </div>
    
       
      
 </div>

		<br/>
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
			'nid':"${Data.PKID}"
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
            	$('#go_pnum').find('span').text(obj.VoteCount);
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
               	 nid:"${Data.PKID}"
			 },function(json){
					 if(json && json.Data>0){
						 iNum=false;
						 $('.jobBtn').find('span').attr("style","color:red");
						 var likecount= $('.jobBtn').find('span').attr("likecount");
						 if(parseInt(likecount)>0){
							 $('.jobBtn').find('span').text(likecount);
						 }
						 
						 
						 
					 }else{
						 iNum=true;
					 }
				 
				 
			 });
	      
	     
	        $('.jobBtn').click(function(){
	        	isclick=true;
	            if(iNum){
	                 var oAdd = $('.jobBtn').find('span').attr("likecount")
	                  if(oAdd=="0"){
	                	  $('.jobBtn').find('span').text("1");
	                  }else{
	                	  $('.jobBtn').find('span').text((Number(oAdd)+1));
	                  }
	                 $('.jobBtn').find('span').attr("style","color:red");
	                 iNum = false;
	                 $.getJSON("<%=path%>/Notices/likeNotices",
						{
	                	 nid:"${Data.PKID}",
	                	 type:0
						 },function(json){});
	             }else{
	                 var aAdd = $('.jobBtn').find('span').text()
	                 if(aAdd=="赞!"){
	                	  $('.jobBtn').find('span').html("赞!");
	                  }else{
	                	  var resu=(Number(aAdd)-1);
	                	  if(resu<=0){
	                		  resu="0";
	                	  }
	                	  $('.jobBtn').find('span').text(resu);
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
               	 nid:"${Data.PKID}"
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