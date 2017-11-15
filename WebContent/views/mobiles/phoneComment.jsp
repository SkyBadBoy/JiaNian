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
		<title>学员评价</title>
	</head>
	<body style="background-color: #f0f0f0;">
		<div class="header">
			<span>学员评价</span>
			<a href="javascript:;" onclick="history.go(-1);" class="back"><i class="iconfont icon-left"></i></a>
		</div>
		<dl class="news" style="margin-top: 1.45rem;margin-bottom: 1.45rem;">
		</dl>

		<div class="confirm">
			<a href="javascript:;" id="send_btn">我也来说说</a>
		</div>
		<!--消息提示-->
		<div class="pop-msg">
			<div class="pop-content">
				<p class="pop-p text-center"></p>
				<img src="images/ok.png" class="center">
			</div>
		</div>
		<div class="popupDom">
			<div class="popwindow">
				<p>消息提示</p>
				<div class="popup text-default"></div>
			</div>
		</div>
		<div class="pop-msg"   id="loading" >
			<div class="pop-content">
				<img src="<%=basePath %>images/jianian/loading.gif" class="center" style="width: 101px;height: 50px;margin-left: 0px;">
			</div>
		</div>
	</body>
	<jsp:include page="/include/commonJiaNianMobileJs.jsp" /> 
	<script type="text/javascript">
	$(function() {
		
		var counter = 0;
		// 每页展示4个
		var num = 10;
		var pageStart = 0,
			pageEnd = 0;
		var page = 1;
		$('.dongtai').dropload({
			scrollArea: window,
			autoLoad: true,
			domDown: { //上拉
				domClass: 'dropload-down',
				domRefresh: '<div class="dropload-refresh f15 "><i class="icon icon-20"></i>上拉加载更多</div>',
				domLoad: '<div class="dropload-load f15"><span class="weui-loading"></span>正在加载中...</div>',
				domNoData: '<div class="dropload-noData">没有更多数据了</div>'
			},
			loadDownFn: function(me) { //加载更多
				$("#loading").show()
				$.ajax({
					type: 'GET',
					url: '<%=basePath %>Comment/getCommentPhoneList',
					dataType: 'json',
					data: { pageNumber: page ,
						    pageSize:num,
						    submitType:'phone'},
					success: function(data) {
						if (data.Data.length>0) {
							var result = '';
							page++;
							for(var i = 0; i < data.Data.length; i++) {
								result+='<a >';
								result+='<dd class="news-content clearfix">';
								result+='<div class="nc-left fl" style="text-align: center;">';
								result+='<img style="width: 80px;height: 80px;border-radius: 40px;" src="'+data.Data[i].imageUrl+'">';
								result+='</div>'
								result+='<div class="nc-right fr">';
								result+='<div class="ncr-top">'+data.Data[i].content +'</div>';
								result+='<span class="nc-time">'+data.Data[i].createTime +'</span>';	
								result+='</div>';				
								result+='</dd>';			
								result+='</a>';		
								if((i + 1) >= data.Data[i].length) {
									me.lock();
									me.noData();
									break;
								} 
							}
							$('.news').append(result);
							me.resetload();
							$("img.lazy").lazyload({ effect: "fadeIn" });
							setTimeout(function () {
								$("#loading").hide();
							 },500);
						}else{
							me.lock();
							me.noData();
							$("#loading").hide();
						}
					},
					error: function(xhr, type) {
						me.resetload();
					}
				});
			}
		});
	});
	
	</script>

</html>