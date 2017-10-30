<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("path", path);
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html>
<head>
    <title>我的新闻</title>
	 <jsp:include page="/include/commonMobileCss.jsp"></jsp:include>
	  <style type="text/css">
    	.c-float-modePop{
    		width: 80%;
    		margin-left: 10%;
    	}
    	.alertDelet{height: 60px;line-height: 60px}
    	.newsList{width: 100%;position: relative;height: 86px;border-bottom: 1px solid #e3e6f2;list-style: none;}
    	.newsMes{float: left;width: 86px;height: 65px;margin:10px}
    	.news_cent{width: 52%;float: left;height: 65px;margin:  10px 0;position: relative;}
    	.news_time{font-size: 11px;color: #a6a6a6;position: absolute;bottom: 0;right: 0}
    	.iconDelet{width: 19px;height: 19px;position: absolute;right:4%;top: 14px;}
    	.iconEdit{width: 19px;height: 19px;position: absolute;right:14%;top: 14px;}
    	.iconSend{width: 19px;height: 19px;position: absolute;right:25%;top: 14px;}
    	.mesNone{width: 100%;height: 260px;text-align: center;line-height: 50px;background: url(images/notNull.png) no-repeat center center;position: relative;}
        .review{position: absolute;bottom: 10%;right: 4%;background: #ff7272;padding:0 5px;border-radius: 10px;color: #fff;font-size: 10px}
         .review2{position: absolute;bottom: 45%;right: 13%;background: #ff7272;padding:0 5px;border-radius: 10px;color: #fff;font-size: 10px}
    	.isBlock{display: none}
        .mesNone p{width: 100%;text-align: center;position: absolute;font-size: 14px;color: #bdbdbd;bottom: 0}
        .weui_dialog{background: rgba(51,51,51,0.9);}
        .weui_dialog_hd{display: none}
        .weui_mask{background: rgba(0,0,0,0);}
        .weui_btn_dialog.primary{color:#fff;}
        .weui_btn_dialog.primary:active{
            background: #868686
        }
        .weui_btn_dialog.default{color: #868686}
        .weui_btn_dialog.default:active{
            color: #fff;
             background: #868686
        }
        html{
	  		background:#fff;
	  	}
	  	  .sk-spinner-fading-circle.sk-spinner{
        margin-top:20px!important}
        #NotMore{
        	padding-top:20px!important;
        }
        
    </style>
</head>
<body>
<div class="header ">
    <a class="header_left" href="#menu"></a>
    <a href="<%=basePath%>Students/phoneSinaIndex" style="padding-top:35px;"><img src="${imageurl}" class="header_img" style="margin-top: 12.5px;"></a>
    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?AreaId=<%=user.getID() %>&SID=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random()%>">
        <span>我要投稿</span>
        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
    </a>
</div>

<input type="hidden" id="_page_" value="2">
	<input type="hidden" id="Type" value="${Type}">
	<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<div style="height: 20px;"></div>
<div class="mt56" >

	<div class="viewport" id="container" id="myDiv" >
		<ul class="listUl" id="listUl">
		
		<c:if test="${IsCaoGao }">
			<li class="newsList" id="caogao">
				<a href="<%=basePath%>Notices/phoneaddnotices">
				<img class="newsMes" src="${ImgTemp }">
				<div class="news_cent" style="">
					<p style="font-size: 15px">${TitleTemp }</p>
				</div>
				</a>
				<span ><img style="width: 19px;height: 19px;position: absolute;right:4%;top: 28px;" onclick="delCaogao()" src="<%=basePath%>images/refuse.png"></span>
				<span class="review2" >草稿</span>
			</li>
		</c:if>
	
		<c:forEach var="Prod" items="${data}">
			<li class="newsList" pkid="${Prod.PKID }" id="${Prod.PKID }" contentType="${Prod.contentType }">
			
				<a href="<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_=${Prod.PKID }&_pc_=&_area_=${AreaId }&sina=<%=SmBaseUtil.Random()%>">
				<img class="newsMes" src="${ Prod.image.url.split(',')[0]}">
				<div class="news_cent" style="">
					<p style="font-size: 15px">${Prod.title}</p>
					<span class="news_time">${Prod.createTime.substring(0,19)} 阅读：${Prod.clickCount}</span>
				</div>
				</a>
				<c:if test="${Prod.status eq 1 }">

					<span  ><img class="iconEdit"  onclick="Edit('${Prod.PKID }',${Prod.contentType })"style="top: 34px;" src="<%=basePath%>images/weixinwenshe/edit.png"></span>
					<span  ><img class="iconDelet"  onclick="del('${Prod.PKID }')"style="top: 34px;" src="<%=basePath%>images/refuse.png"></span>
				</c:if>
				<c:if test="${Prod.status eq 7}">

				<span  ><img class="iconEdit"  onclick="Edit('${Prod.PKID }',${Prod.contentType })" src="<%=basePath%>images/weixinwenshe/edit.png"></span>
					<span ><img class="iconDelet" onclick="del('${Prod.PKID }')" src="<%=basePath%>images/refuse.png"></span>
					 <span class="review" >待审核</span>
				</c:if>
				 <c:if test="${Prod.status eq 8}">

				 	<span  ><img class="iconEdit"  onclick="Edit('${Prod.PKID }',${Prod.contentType })" src="<%=basePath%>images/weixinwenshe/edit.png"></span>
					<span  ><img class="iconDelet" onclick="del('${Prod.PKID }')" src="<%=basePath%>images/refuse.png"></span>
					 <span class="review" >未通过  </span>
				 </c:if>
				 <c:if test="${Prod.status eq 11}">

				 	<span  ><img class="iconEdit"  onclick="Edit('${Prod.PKID }',${Prod.contentType })" src="<%=basePath%>images/weixinwenshe/edit.png"></span>
					<span  ><img class="iconDelet" onclick="del('${Prod.PKID }')" src="<%=basePath%>images/refuse.png"></span>
					 <span class="review" >编辑中  </span>
				 </c:if>
				 <c:if test="${Prod.status eq 12}">
				 	<span class="glyphicon glyphicon-send iconSend" style="font-size:16px;margin-top: 2px;"></span>
				 	<span  ><img class="iconEdit"  onclick="Edit('${Prod.PKID }',${Prod.contentType })" src="<%=basePath%>images/weixinwenshe/edit.png"></span>
					<span  ><img class="iconDelet" onclick="del('${Prod.PKID }')" src="<%=basePath%>images/refuse.png"></span>
					<span class="review" >草稿 </span>
				 </c:if>
			</li>

		</c:forEach>
		</ul>
	</div>
	<c:if test="${data.size()<=0}">
		<br/>
			<h3 style="text-align: center;" >当前没有我的新闻~</h3>
			<br/>
    </c:if>
<div style="text-align: center;margin-top: 80px; display: none;" id="nothing">
		  <h3 style="text-align: center;"  >当前没有我的新闻~</h3>
	</div>
</div>
<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>js/plugin/plupload/plupload.full.min.js?v=3.3.7"></script>
<script type="text/javascript">
        var num = null
        $(function() {
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					 $("#NotMore").remove(); 
					 $("#listUl").append(getLoading());
					 $.getJSON("<%=basePath%>Notices/getMyNoticesList",
						{
						 pageNumber:$('#_page_').val(),
						 AreaID:'${AreaId}',
   						 sid:'${sid}',
						 pageSize:10,
						 submitType:'phone'
						 },function(json){ 
						 	console.log(1);
			                if(json && json.Status==1){ 
			                	console.log(2);
			                	var tmp="";
			                	var basePath=$("#basePath").val();
			                    $.each(json.data,function(index,array){ 
			                    	var imageurl;
	   								if (array.image.url!=null) {
										imageurl=array.image.url.split(",")[0];
	   								}else{
	   									imageurl="<%=basePath%>img/errorpic.jpg";
	   								}
			          			         	console.log(3);
			          			         	var html="";
				          			         if (array.status==1) {
				          			         	html='<span  ><a href="<%=basePath%>Notices/phoneaddnotices?Notice='+array.pkid+'&sina=<%=SmBaseUtil.Random()%>"><img class="iconEdit"  style="top: 34px;" src="<%=basePath%>images/weixinwenshe/edit.png"></a></span><span ><img class="iconDelet" onclick="del('+array.pkid+')"   style="top: 34px;"  src="<%=basePath%>images/refuse.png"></span>';
				          			         }else if(array.status==7){
				          			         	html='<span  ><a href="<%=basePath%>Notices/phoneaddnotices?Notice='+array.pkid+'&sina=<%=SmBaseUtil.Random()%>"><img class="iconEdit"  src="<%=basePath%>images/weixinwenshe/edit.png"></a></span><span ><img class="iconDelet" onclick="del('+array.pkid+')"  src="<%=basePath%>images/refuse.png"></span><span class="review" >待审核</span>'
				          			         }
				          			         else if(array.status==8){
				          			         	html='<span  ><a href="<%=basePath%>Notices/phoneaddnotices?Notice='+array.pkid+'&sina=<%=SmBaseUtil.Random()%>"><img class="iconEdit"   src="<%=basePath%>images/weixinwenshe/edit.png"></a></span><span ><img class="iconDelet" onclick="del('+array.pkid+')"  src="<%=basePath%>images/refuse.png"></span><span class="review" >未通过</span>'
				          			         }
				                            tmp += '<li class="newsList" contentType="'+array.contentType+'" pkid="'+array.pkid+'" id="'+array.pkid+'">'+
				                        			'<a href="<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_='+array.pkid+'&_pc_=&_area_=${AreaId }&_status_='+array.status+'&sina=<%=SmBaseUtil.Random()%>">'+
						                            '<img class="newsMes" src="'+imageurl+'">'+
						                           	'<div class="news_cent" style=""><p style="font-size: 15px">'+array.title+'</p>'+
						                            '<span class="news_time">'+array.modifyTime.substring(0,19)+'</span></div></a>'+
						                            html+
						                            '</li>';
			                    }); 
			                    $("#listUl").append(tmp); 
			                    $('.iconDelet').each(function(i,elem){
			                	 	$(elem).prop('index',i);
			                	 	$(elem).on('mousedown', function(){
			                		 	num = $(this).prop('index');
			                		 	delitem();
			                		 });
			                	 });






			                    $('.iconEdit').each(function(i,elem){
			                	 	$(elem).prop('index',i);
			                	 	$(elem).on('mousedown', function(){
			                		 	num = $(this).prop('index');
			                		 	//$(".newsList").eq(num).attr("pkid")
			                		 	console.log($(".newsList").eq(num).attr("pkid"));
			                		 //	window.location.href="<%=basePath%>Notices/phoneaddnotices?Notice="+$(".newsList").eq(num).attr("pkid")+"&sina=<%=SmBaseUtil.Random()%>";
			                		 });
			                	 });
			                    $('.iconSend').each(function(i,elem){
			                	 	$(elem).prop('index',i);
			                	 	$(elem).on('mousedown', function(){
			                	 		webToast("该文由APP编辑，请下载微新闻社APP！","bottom", 5000);
			                		 });
			                	 });
			            	
			                    if(json.data.length<=0){
			                    	console.log(4);
			                    	//AddNoMoreDOM($("#myDiv"));
			                    	$(".sk-spinner").remove();
			                    	//$("#listUl").append(getNoMoreInfo())
									if($("#NotMore").length<=0){
										if($("#listUl")!=null){
											$("#listUl").append(getNoMoreInfo())
										}else{
											$("#listUl").append(getNoMoreInfo()); 
										}
									}
			                    }else{
			                    		console.log(5);
				                    var _page_=json._page_;
				                    $('#_page_').val(parseInt(_page_)+1);
				                   $(".sk-spinner").remove();
									$("#listUl").append(getLoading());
				                    i++; 
			                    }

			                }else{ 
			                		console.log(6);
			                	//AddNoMoreDOM($("#myDiv"));
			                		$(".sk-spinner").remove();
			                		//	$("#listUl").append(getNoMoreInfo())
									if($("#NotMore").length<=0){
										if($("#listUl")!=null){
											$("#listUl").append(getNoMoreInfo())
										}else{
											$("#listUl").append(getNoMoreInfo()); 
										}
									}
			                	return false; 
			                }
			                bindImgError();
			            }); 
				}
			});
		});
		    
        
        $(function () {
	        $('nav#menu').mmenu();
	    });
        if($('.iconDelet').length){
     		$('.mesNone').addClass('isBlock')
     	}else{
    		$('.mesNone').removeClass('isBlock')
     	}
        		
        $('.iconDelet').each(function(i,elem){
         	$(elem).prop('index',i)
            
         	$(elem).on('mousedown', function(){
		 	num = $(this).prop('index');
		 	delitem();
		 });
	 });
					 






        
        $('.iconEdit').each(function(i,elem){
    	 	$(elem).prop('index',i);
    	 	$(elem).on('mousedown', function(){
    		 	num = $(this).prop('index');
    		 	//$(".newsList").eq(num).attr("pkid")
    		 	console.log($(".newsList").eq(num).attr("pkid"));
    		 	// window.location.href="<%=basePath%>Notices/phoneaddnotices?Notice="+$(".newsList").eq(num).attr("pkid")+"&sina=<%=SmBaseUtil.Random()%>";
    		 });
    	 });
        $('.iconSend').each(function(i,elem){
    	 	$(elem).prop('index',i);
    	 	$(elem).on('mousedown', function(){
    	 		webToast("该文由APP编辑，请下载微新闻社APP！","bottom", 5000);
    		 });
    	 });
	
	function delitem(){
		$.confirm({
	         text: '确定删除此条新闻',
	        onOK: function () {
               	this.hide();
               	console.log($(".newsList").eq(num).attr("pkid"));
					$.ajax({
							url : "<%=basePath%>Notices/deleteMyNotices",
							data : {
								'pid' : $(".newsList").eq(num).attr("pkid")
								
							},
							success : function(obj) {
								if(obj.result==1){
									$(".newsList").eq(num).remove()
		                        		if ($("#listUl").has("li").length == 0){
																		$("#nothing").show();
																	}
		                        	

		                        	 $('.iconDelet').each(function(i,elem){
		                      
		                        	 	$(elem).prop('index',i)
		                    	 	 	
		                        	 })
								}else{
									if(obj.Message!=null && obj.Message!=undefined){
										webToast(obj.Message,"bottom", 3000);
									}
								}
								
							}
						});
					close();
			         $('.iconDelet').each(function(i,elem){
			            $(elem).prop('index',i);
			         })
	        },
	        onCancel: function () {
	        	close();
	        }
               	
	          
               });
		$('.weui_dialog_bd').css({
            'color':'#fff',
            'padding-top':'43px',
            'padding-bottom':'23px'     
        })
		
	}
	function close(){
		$(".weui_dialog_visible").remove();
		$(".weui_mask_visible").remove();
		$(".weui_mask").remove();
		$(".weui_dialog").remove();
	}

	function delCaogao(){
		
		$.confirm({
	         text: '确定删除此条草稿',
	        onOK: function () {
              	this.hide();
              	
					$.ajax({
							url : "<%=basePath%>Notices/deleteMyCaoGao",
							success : function(obj) {
								if(obj.status){
									$("#caogao").remove();
								}else{
									webToast(obj.Message,"bottom", 3000);
								}
							}
						});
					close();
			        
	        },
	        onCancel: function () {
	        	close();
	        }
              });
	}
	
	function Edit(id,contentType){
		console.log(contentType==0)
		if (contentType==0||contentType=="0") {
			window.location.href="<%=basePath%>Notices/phoneaddnotices?Notice="+id+"&sina=<%=SmBaseUtil.Random()%>";
		}else{
			webToast("该文由APP编辑，请下载微新闻社APP！","bottom", 5000);
		}
		
	}
</script>
</body>
</html>