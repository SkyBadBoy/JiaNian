<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath =SmBaseUtil.getProjectBaseUrl(request);
%>
<html>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/teacherday/1.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/teacherday/animate.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/teacherday/swiper.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/teacherday/style.css">
    <title>制作教师节卡片，给老师一个特别的祝福</title>
</head>
<body>
<div class="swiper-container" id="swiper-container-v">
    <section class="poster_wrap load" id="load">
        <div class="p_loading">
            <div class="p_loading_logo"></div>
            <p class="p_loading_tip">教师节快乐</p>
        </div>
    </section>
    <div class="swiper-wrapper">
        <section class="swiper-slide swiper-slide1 swiper-slide-v">
            <div class="page page-1-1 ">
                <div class="wrap">
                    <img class="img_1 ani" src="<%=basePath%>images/teacherDay/t1-logo.png" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="1.5s"/>
                    <img class="img_2 ani" src="<%=basePath%>images/teacherDay/t1-enshi.png" swiper-animate-effect="myrotateIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"/>
                    <img class="img_3 ani" src="<%=basePath%>images/teacherDay/t1-bj.png" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="2.5s"/>
                    <img class="img_4 ani" src="<%=basePath%>images/teacherDay/t1-xs.png" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.2s"/>
                    <img class="img_5 ani" src="<%=basePath%>images/teacherDay/t1-shu.png" swiper-animate-effect="myrotateIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/>
                </div>
            </div>
        </section>
        <section class="swiper-slide swiper-slide2 swiper-slide-v">
            <div class="page page-2-1 ">
                <div class="wrap">
                    <img class="img_1   ani" src="<%=basePath%>images/teacherDay/t2-sjc.png"  swiper-animate-effect="fadeInDown" swiper-animate-duration="0.8s" swiper-animate-delay="2.1s"/>
                    <img class="img_2   ani" src="<%=basePath%>images/teacherDay/t2-sz.png"  swiper-animate-effect="myrotateIn" swiper-animate-duration="0.8s" swiper-animate-delay="2.5s"/>
                    <img class="img_3   ani" src="<%=basePath%>images/teacherDay/t2-db.png"  swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.8s" swiper-animate-delay="0.3s"/>
                    <img class="img_4   ani" src="<%=basePath%>images/teacherDay/t2-x.png"  swiper-animate-effect="fadeInRight" swiper-animate-duration="0.8s" swiper-animate-delay="0.9s"/>
                    <img class="img_5   ani" src="<%=basePath%>images/teacherDay/t2-h.png"  swiper-animate-effect="fadeInDown" swiper-animate-duration="0.8s" swiper-animate-delay="1.6s"/>
                    <img class="img_6   ani" src="<%=basePath%>images/teacherDay/t2-k.png"  swiper-animate-effect="myrotateIn" swiper-animate-duration="0.8s" swiper-animate-delay="2.7s"/>
                    <img class="img_7   ani" src="<%=basePath%>images/teacherDay/t2-xf.png"  swiper-animate-effect="fadeInDown" swiper-animate-duration="0.8s" swiper-animate-delay="0.5s"/>
                    <img class="img_8   ani" src="<%=basePath%>images/teacherDay/t2-zc.png"  swiper-animate-effect="myrotateIn" swiper-animate-duration="0.8s" swiper-animate-delay="1.3s"/>
                    <img class="img_9   ani" src="<%=basePath%>images/teacherDay/t2-b.png"  swiper-animate-effect="fadeInDown" swiper-animate-duration="0.8s" swiper-animate-delay="2.2s"/>
                    <img class="img_10  ani" src="<%=basePath%>images/teacherDay/icon_up.png"  swiper-animate-effect="fadeInDown" swiper-animate-duration="0.8s" swiper-animate-delay="2.4s"/>
                </div>
            </div>
        </section>
        <section class="swiper-slide swiper-slide6 swiper-slide-v">
            <div class="logo ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s">
                <div class="box1">
                    <div class="box2">
                        <img src="<%=basePath%>images/teacherDay/t1-logo.png">
                    </div>
                </div>
            </div>

            <div class="search_posit " >
                <div class="search_input_con ani" swiper-animate-effect="myrotateIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.1s">
                    <div class="search_input_details" style="width: 40%;">
                        <input type="text" placeholder="" value="" class="search_input" maxlength="4"> <label style="float: right;
    padding-left: -10px;
    padding-right: 20px;
    color: white;">老师</label>
                    </div>
                   
                </div>
                <div class="search_textarea_con ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="2.1s">
                    <div class="search_textarea_details">
                        <textarea  placeholder="请输入祝福内容(50字以内)" rows="6" cols="20" placeholder="${blessing}" class="search_textarea" maxlength="50" ></textarea>
                    </div>
                </div>
                <div class="search_input_con ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s">
                    <div class="search_input_details">
                        <input type="text" placeholder="请输入自己的姓名"  class="search_input" maxlength="5">
                    </div>
                </div>
            </div>
            <a class="search_footer_btn ani" id="f-btn" href="#" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="2.8s" >制作卡片</a>
        </section>

		<section  style="display:none;" class="swiper-slide swiper-slide7 swiper-slide-v">
            <div class="search_posit " style="position: absolute;
    top: 4%;
    /* height: 43px; */
    left: 60px;
    right: 60px;
    overflow: hidden;
    z-index: 8;" >
               <img alt="" style="width:100%;height:100%" id="result_card" src="<%=basePath%>images/teacherDay/11.jpg">
               <label style="
    /* padding: 10px; */
    text-align: center;
    width: 100%;
    display: block;
    padding-top: 10px;
    color: white;
">长按可以保存图片</label>
 <br/>
            </div>
            <br/>
            <a class="search_footer_btn ani" id="referbut" href="<%=basePath%>Feedback/phoneCreateCard?sina=e608f4ug" style="bottom: 60px;z-index:100" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" >重新制作</a>
        <br/>
    
                  
                      
                 
               
        </section>
    </div>
    <!--<div class="arrow-box"><img src="<%=basePath%>images/teacherDay/arrow.png" id="array"></div>-->
    <!--<div class="swiper-pagination"></div>-->
</div>
<input id="dataContent" type="hidden" value="制作教师节卡片，给老师一个特别的祝福">
<script src="<%=basePath%>js/teacherday/jquery-3.1.1.min.js"></script>
<script src="<%=basePath%>js/teacherday/swiper-3.4.2.min.js"></script>
<script src="<%=basePath%>js/teacherday/swiper.animate1.0.2.min.js"></script>
<script src="<%=basePath%>js/wechat_index.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
        document.getElementById("load").style.display = "none";
        var mySwiper = new Swiper ('#swiper-container-v', {
            grabCursor: true,
            paginationClickable: true,
            autoplay: 4000,
            autoplayStopOnLast: true,
            onInit: function(swiper){ //Swiper2.x的初始化是onFirstInit
                swiperAnimateCache(swiper); //隐藏动画元素
                swiperAnimate(swiper); //初始化完成开始动画
            },
            onSlideChangeEnd: function(swiper){
                swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
            }
        })

    })

    	$(function(){
    		$('#f-btn').on('mousedown',function(){
    			if($('.search_textarea').val() != '' && $(".search_input").eq(0).val() != '' && $(".search_input").eq(1).val() != ''){
    				document.getElementById("load").style.display = "block";
    				$.ajax({
                      url : "<%=basePath%>Feedback/phoneCreateCard",
                      type:"post",
                      data : {
                            'content' : encodeURI($('.search_textarea').val()),
                            'teacher' : encodeURI($(".search_input").eq(0).val()),
                            'name' : encodeURI($(".search_input").eq(1).val())
                              },
                              success : function(obj) {
                            	  
                            	  $("#result_card").attr("src",obj.Data.url.split(",")[0])
                            $(".swiper-slide6").hide(100);
                      			$(".swiper-slide7").fadeIn();
                      			$($(".search_footer_btn")[1]).css('visibility','visible');
                            	  document.getElementById("load").style.display = "none";
                                
                                
                              }
                            });
    			}
    			
    		})
    		
    $(".search_input")[0].focus();
    	})	
    </script>
    <jsp:include page="/include/commonStatistics.jsp"></jsp:include>
</body>
</html>
