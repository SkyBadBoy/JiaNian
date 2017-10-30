<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">

<%@page import="wtb.core.model.Activity"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@page import="wtb.smUtil.tenpay.util.WXUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("path", path);
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
	Activity ac=(Activity)request.getAttribute("Activity");
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${Activity.title}</title>
	<jsp:include page="/include/commonMobileCss.jsp"></jsp:include>
<style type="text/css">

.swiper-container-horizontal > .swiper-pagination {

bottom: 10px;
    left: -45%;
    width: 100%;
}

.lefta{
	position: absolute;
    padding: 5px 15px;
    bottom: 10px;
    left: 10px;
    border-radius: 5px;
    background-color: rgba(42,42,69,0.5);
    font-size: 14px;
    color: #fff;
    }
   
</style>
</head>
<body>
<c:if test="${status==0}">
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
<c:if test="${status==1}">
<div class="mt56" style="margin-top:0px">
<!-- 广告展示轮播-->
<div class="video_activity mt56" style="margin-top:0px">

</c:if>
<div id="myTabContent" class="tab-content ">

        <div class="index_activity_act">
            <div class="index_title">${Activity.title}</div>
            <div class="posi_relative">
            <c:if test="${ImageCount<=1}">
		 		<div id="module_1" style="height: 250px;" class="swiper-container">
		            <div class="swiper-wrapper">
		                <div class="two swiper-slide"><a data-gallery="" href="${Image}" title="${Data.name}">
							<img src="${Image}"  class="img_response"/>
							<c:if test="${ApplyMoney ne 0 }">
								<c:if test="${PayType eq 15}">
									<div class="lefta"> 需要支付${ApplyMoney }元</div>
								</c:if>
								<c:if test="${PayType eq 16}">
									<div class="lefta"> 需要支付${ApplyMoney }微米</div>
								</c:if>
							</c:if>
							<div class="activity_join">
									已有<span class="color_ff3962">
									<c:if test="${ApplyLimit ne 0 and  ApplyLimit ne ApplyCount}">
											${ApplyCount}/${ApplyLimit }
										</c:if>
										<c:if test="${ApplyLimit eq 0 || ApplyLimit eq ApplyCount}">
											${ApplyCount}
										</c:if>
									</span>人报名
                			</div>			 		
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
									<c:if test="${ApplyMoney ne 0 }">
										<div class="lefta"> 需要支付${ApplyMoney }元</div>
									</c:if>
									<div class="activity_join">
	                    				已有<span class="color_ff3962">
											<c:if test="${ApplyLimit ne 0 and  ApplyLimit ne ApplyCount}">
											${ApplyCount}/${ApplyLimit }
										</c:if>
										<c:if test="${ApplyLimit eq 0 || ApplyLimit eq ApplyCount}">
											${ApplyCount}
										</c:if>
										</span>人报名
                					</div>
								</a>
							</div>
						</c:forEach>
					</div> 
					<!-- Add Pagination -->
					<div class="swiper-pagination" style="text-align: right;"></div>
				</div>
			</c:if>
			<!--  div class="activity_join"> a3</div>
                <div class="activity_join">
				                    已有<span class="color_ff3962">
										<c:if test="${ApplyLimit ne 0 and  ApplyLimit ne ApplyCount}">
											${ApplyCount}/${ApplyLimit }
										</c:if>
										<c:if test="${ApplyLimit eq 0 || ApplyLimit eq ApplyCount}">
											${ApplyCount}
										</c:if>
						</span>人报名
                </div>
            </div-->
            <div class="activity_p" style="padding: 10px;">${Activity.content}</div>
        </div>
        <div class="index_activity_a" style="background-color: TRANSPARENT;">
        
        
		<c:if test="${status!=1}">
			<div style="display: block;">
				<c:if test="${IsEnd==0 }" >
					<c:if test="${IsApply==0 }" >
						<c:if test="${IsApplyLimit eq false}">
							<a href="javascript:void(0);"  style="padding-top: 3px;background-color:#a8a1a1">报&nbsp;名&nbsp;人&nbsp;数&nbsp;已&nbsp;满!</a>
						</c:if>
						<c:if test="${IsApplyLimit}">
							<a href="javascript:void(0);"  id="index_activity_submit" style="padding-top: 3px;background-color: #68ccb3;">报&nbsp;名&nbsp;参&nbsp;加</a>
						</c:if>
					</c:if>
					<c:if test="${IsApply!=0 }">
						<a href="javascript:void(0);"  style="padding-top: 3px;background-color:#a8a1a1">您已提交报名申请，等待通知!</a>
					</c:if>
					
				</c:if>
				
				<c:if test="${IsEnd == 1 }">
					<a href="javascript:void(0);"  style="padding-top: 3px;background-color:#a8a1a1">截&nbsp;止&nbsp;报&nbsp;名!</a>
				</c:if>
			</div>
		</c:if>
		
		
		
        </div>
       </div>
       </br>
<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
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
<a href="#0" class="cd-top"></a>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<input type="hidden" name="ActivityID" id="ActivityID" value="${Activity.PKID}" />
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function ($) {
            var offset = 300,  offset_opacity = 1500,  scroll_top_duration = 700,$back_to_top = $('.cd-top');
            $(window).scroll(function () {
                ( $(this).scrollTop() > offset ) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
                if ($(this).scrollTop() > offset_opacity) {
                    $back_to_top.addClass('cd-fade-out');
                }
            });
            $back_to_top.on('click', function (event) {
                event.preventDefault();
                $('body,html').animate({
                            scrollTop: 0
                        }, scroll_top_duration
                );
            });

        });
        

    </script>
    <script type="text/javascript">
var swiper = new Swiper('#module_1.swiper-container', {
    pagination: '.swiper-pagination',
    paginationClickable: true,
    loop: true,
    loopAdditionalSlides: 0,
    autoplay: 1000000, //可选选项，自动滑动
    autoplayDisableOnInteraction: false
});
$(function () {
    $('nav#menu').mmenu();
});
</script>

<script type="text/javascript">
var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.ActivityPay.getid(), String.valueOf(user.getID()), String.valueOf(ac.getID()) ) %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
	var url=location.href.split('#')[0];
	var timestamp="";
	var nonceStr='';
	var money='${ApplyMoney}';
	var PayType='${PayType}';
	$.ajax({
		url:"<%=basePath%>Product/getSignature",
		data:{
			'URL':url,

		},
		success : function(obj) {
			if(obj.status==1){
				timestamp = obj.timeStamp;
				nonceStr=obj.nonceStr;
					wx.config({
					debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
					appId: obj.AppID, // 必填，公众号的唯一标识 wxab5e4ee5a20faf3f
					timestamp:timestamp, // 必填，生成签名的时间戳
					nonceStr: nonceStr, // 必填，生成签名的随机串
					signature: obj.signature,// 必填，签名，见附录1
					jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
				wx.ready(function () {
					document.querySelector('#index_activity_submit').onclick = function () {
						if(PayType=='14'||PayType==14){//免费
							pay(0,0);
						}
						if(PayType=='15'||PayType==15){//人民币
						       $.ajax({
			                        url:"<%=basePath%>WeMoney/payWeChat",
			                        data:{
			                            'pirce':money,
			                            'ip':returnCitySN["cip"],
			                            'timestamp':timestamp,
			                            'nonceStr':nonceStr,
			                            'attach' :attach
			                        },
			                        success : function(obj) {
			                            if (obj.status==1) {
			                                data=JSON.parse(obj.data);
			                                 wx.chooseWXPay({
			                                timestamp: timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
			                                nonceStr: nonceStr, // 支付签名随机串，不长于 32 位
			                                package: data.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
			                                signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
			                                paySign: data.paySign, // 支付签名
			                                success: function (res) {
			                                // 支付成功后的回调函数
			                                	//pay(1,obj.orderID);
			                                	 $("#index_activity_submit").click("");
			                	    			 $("#index_activity_submit").attr("disabled","disabled");
			                	    			 $("#index_activity_submit").css("background-color","#a8a1a1");
			                	    			 $("#index_activity_submit").text("已提交报名申请，等待通知！");
			                	    			 $("#index_activity_submit").attr("disabled","disabled");
			                	    			 var count=${ApplyCount}+1;
			                	    			 $(".color_ff3962").html(count+"人");
			                                }
			                         });
			                            }
			                        }});			
						}
						if(PayType=='16'||PayType==16){//微米
							//搞一个提示框在这里
							pay(1,'');
						}
					}
				})
				
				
			}else{
				//webToast("获取签名失败，分享功能将失效","bottom",3000);
			}
		
		}})
		
		   function pay(t,orderID){//t=1 说明是微信支付付款成功之后需要吧记录写进数据库
            $.getJSON("<%=path%>/Activity/phoneApplyActivity", {
	        	aid : $("#ActivityID").val(),
	        	type:t,
	        	orderID:orderID,
	        	pirce:money
	    	}, function(json) {
	    		 if(json.Status==1){
	    			 $("#index_activity_submit").click("");
	    			 $("#index_activity_submit").attr("disabled","disabled");
	    			 $("#index_activity_submit").css("background-color","#a8a1a1");
	    			 $("#index_activity_submit").text("已提交报名申请，等待通知！");
	    			 $("#index_activity_submit").attr("disabled","disabled");
	    			 var count=${ApplyCount}+1;
	    			 $(".color_ff3962").html(count+"人");
	    		 }else if(json.Status==-2){
	    			 window.location.href="<%=SmBaseGlobal.XBLoginOrRegister%>?url=<%=basePath%>"+json.returnView;
	    		 }else{
	    			 $("#index_activity_submit").click("");
	    			 $("#index_activity_submit").attr("disabled","disabled");
	    			 $("#index_activity_submit").css("background-color","#a8a1a1");
	    			 $("#index_activity_submit").text(json.ErrorMessage);
	    		 }
	    	})
        }
	
</script>
</body>
</html>