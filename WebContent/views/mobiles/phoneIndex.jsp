<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String path = request.getContextPath();
  String basePath =SmBaseUtil.getProjectBaseUrl(request);
%>
<html lang="zh-CN">
	<head>
		<jsp:include page="/include/commonJiaNianMobileCss.jsp"></jsp:include>
		<title>嘉念人人学车</title>
		<style>
			body{
				background-color: #F0F0F0;
			}
			.contact a{
				display: flex;
				justify-content: center;
				align-items: center;
			}
			.dt_ev{
				    text-align: center;
				    font-size: 0.55rem;
				    font-weight: 100;
				    height: 1.2rem;
				    line-height: 1.2rem;
				    border-bottom: 1px solid #ddd;
			}
		</style>
	</head>
	<body>
		<div class="header" style="z-index: 2;" >
			<span>嘉念人人学车</span>
		</div>
		<div class="banner swiper-container " style="margin-top: 44px;" >
			<div class="swiper-wrapper ">
				<c:forEach var="ImageList" items="${ImageList}">
					<div class="swiper-slide ">
						<a href="${ImageList.PUrl}"><img class="swiper-lazy bannermenu" data-src="${ImageList.url.split(',')[0]}" alt=""></a>
					</div> 
				</c:forEach>
			</div>
			<div class="swiper-pagination"></div>
		</div>
		<div class="menu">
			<ul class="clearfix">
				<li>
					<a href="<%=basePath %>Free/phoneFree?Rand=<%=SmBaseUtil.Random() %>">
						<img src="<%=basePath %>images/jianian/icon1.png" >
						<p class="menu-txt">免费试学</p>
					</a>
				</li>
				<li>
					<a href="<%=basePath %>Service/phoneService?Rand=<%=SmBaseUtil.Random() %>">
						<img src="<%=basePath %>images/jianian/icon3.png">
						<p class="menu-txt">服务承诺</p>
					</a>
				</li>
				<li>
					<a href="<%=basePath %>Comment/phoneComment?Rand=<%=SmBaseUtil.Random() %>">
						<img src="<%=basePath %>images/jianian/icon4.png">
						<p class="menu-txt">学员评价</p>
					</a>
				</li>
			</ul>
			<ul class="clearfix">
				<li>
					<a href="<%=basePath %>Flow/phonePlace?Rand=<%=SmBaseUtil.Random() %>">
						<img src="<%=basePath %>images/jianian/icon5.png">
						<p class="menu-txt">学车场地</p>
					</a>
				</li>
			

				<li>
					<a href="<%=basePath %>Flow/phoneFlow?Rand=<%=SmBaseUtil.Random() %>">
						<img src="<%=basePath %>images/jianian/icon7.png">
						<p class="menu-txt">学车流程</p>
					</a>
				</li>
					<li>
					<a href="<%=basePath %>Question/phoneQuestion?Rand=<%=SmBaseUtil.Random() %>">
						<img src="<%=basePath %>images/jianian/icon2.png">
						<p class="menu-txt">常见问题</p>
					</a>
				</li>
			</ul>
		</div>
		<div class="menu2">
				<ul class="clearfix">
				<li class="contact" style="border-right:1px solid #F0F0F0;">
					<a href="tel:057156050817" >
						<img src="<%=basePath %>images/jianian/phone.png" style="width: 16px;height: 16px;margin-right: 5px;"/>
						<p class="menu-txt2">电话咨询</p>
					</a>
				</li>
				
				<li class="contact">
					<a onclick="showQrcode()">
						<img src="<%=basePath %>images/jianian/weichat.png" style="width: 20px;height: 16px;margin-right: 5px;"/>
						<p class="menu-txt2">微信咨询</p>
					</a>
				</li>
			</ul>
		</div>
	
		
		
		<dl class="news">
			<a ><dt class="news-tit"><span class="news-title">有趣的学员们</span></dt></a>
			<c:forEach var="Comment" items="${Comment}">
				<a >
					<dd class="news-content clearfix">
						<div class="nc-left fl" style="text-align: center;">
							<img style="width: 80px;height: 80px;border-radius: 40px;" src="${Comment.imageUrl}">
						</div>
						<div class="nc-right fr">
							<div class="ncr-top">${Comment.content }
							</div>
							<span class="nc-time">${Comment.createTime }</span>
						</div>
					</dd>
				</a>
			</c:forEach>
			<a href="<%=basePath %>Comment/phoneComment?Rand=<%=SmBaseUtil.Random() %>"><dt class="dt_ev"><span class="news-title">查看更多</span></dt></a>
		</dl>
		<div class="footer">
			<ul class="footer-page clearfix">
				<li class="page-item active">
					<a href="#">
						<i class="iconfont icon-index"></i>
						<p>首页</p>
					</a>
				</li>
				<li class="page-item">
					<a href="<%=basePath %>ApplyList/phoneApply?Rand=<%=SmBaseUtil.Random() %>">
						<i class="iconfont icon-computer"></i>
						<p>报名</p>
					</a>
				</li>
				<li class="page-item">
					<a href="<%=basePath %>Students/phoneUserCenter?Rand=<%=SmBaseUtil.Random() %>">
						<i class="iconfont icon-person1"></i>
						<p>我的</p>
					</a>
				</li>
			</ul>
		</div>
		<!--消息提示-->
		<div class="pop-msg">
			<div class="pop-content">
				<p class="pop-p text-center"></p>
				<img src="<%=basePath %>images/jianian/ok.png" class="center">
			</div>
		</div>
		<div class="pop-msg"   id="loading" >
			<div class="pop-content">
				<img src="<%=basePath %>images/jianian/loading.gif" class="center" style="width: 101px;height: 50px;margin-left: 0px;">
			</div>
		</div>
	
		<div class="popupDom">
			<div class="popwindow">
				<p>消息提示</p>
				<div class="popup text-default"></div>
			</div>
		</div>
		
		<!-- QR code-->
		<div class="popupDom" id="qrcode">
			<div class="popwindow" style="height: 235px;">
				<!-- 客服6 的二维码-->
				<img src="<%=basePath %>images/jianian/qrcode2.jpeg" style="height: 235px;height: 235px;"/>
			</div>
			<img onclick="colseQrcode()" src="<%=basePath %>images/jianian/guanbi.png" style="width: 35px;height: 34px;    margin-top: -180px;"/>
		</div>
		<jsp:include page="/include/commonJiaNianMobileJs.jsp" />
		<script>
			$(function() {
				var banner = new Swiper('.banner', {
					autoplay: 5000,
					pagination: '.swiper-pagination',
					paginationClickable: true,
					lazyLoading: true,
					loop: true
				});
			});
			function showQrcode(){$("#qrcode").show();};//显示二维码
			function colseQrcode(){$("#qrcode").fadeOut();};//关闭二维码
			setTimeout('$("#loading").hide()',500)
			
		</script>
	</body>

</html>