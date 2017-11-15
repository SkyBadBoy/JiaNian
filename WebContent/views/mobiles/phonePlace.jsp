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
		<title>学车场地</title>
		<style>
			body {
				height: auto;
				-webkit-backface-visibility: hidden;
			}
			
			.icon_head {
				width: 50px;
				height: 50px;
			}
			
			.icon_head img {
				width: 100%;
			}
			
			dl {
				
				height: 50px;
				background-color: #fff;
				/*border-bottom: 1px solid #e7e7e7;*/
				padding: 15px 0;
				box-shadow: 0 0 5px #9e9c9c;
				border-radius:10px;
				margin: 2%;
			}
			
			dl dt {
				float: left;
				margin: 0 13px;
			}
			
			dl dd {
				float: left;
			}
			
			dl dd .type {
				font-size: 14px;
				color: #4a5062;
				display: block;
				margin: 8px 0 6px;
			}
			
			dl dd .mess {
				display: block;
				font-size: 13px;
				color: #999;
			}
			
			dl dd:last-child {
				float: right;
				margin-right: 12px;
				font-size: 14px;
				color: #4a5062;
				margin-top: 16px;
				position: absolute;
				right: 0px;
			}
			
			dl dd:last-child em {
				font-size: 18px;
				color: #ff7200;
			}
			
			dl dd .yg_mess {
				display: block;
				font-size: 12px;
				color: #ff3535;
				margin-top: 8px;
			}
			
			dl dd .icon_yg {
				display: inline-block;
				width: 12px;
				height: 12px;
				background-image: url(../<%=basePath %>images/jianian/jianian/icons_add_classlist.png);
				background-size: 12px;
				background-re4 peat: no-repeat;
				margin-right: 5px;
				position: relative;
				top: 1px;
			}
			.i{
				font-size: 0.5rem;
				margin-right: 5px;
				margin-top: 100px;
			}
		</style>
	</head>
		<body style="background-color: #f0f0f0;">
		<div class="header">
			<span>学车场地</span>
			<a href="javascript:;" onclick="history.go(-1);" class="back"><i class="iconfont icon-left"></i></a>
		</div>
		<div class="city_pop_mh" style="margin-top: 1.45rem;">
			<a href="placephoto.html">
				<dl class="hover_color_white" type="14">
						<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_bkwy.png"></dt>
						<dd>
							<span class="type">[下沙]浙江财经大学北门练车点</span>
							<span class="mess">浙江省杭州市下沙高教园区学源街18号</span>
						</dd>
						<dd><i class="iconfont icon-right i"></i></dd>
				</dl>
			</a>
			<a href="placephoto.html">
				<dl class="hover_color_white" type="14">
					<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_bkwy.png"></dt>
					<dd>
						<span class="type">[下沙]浙江传媒大学北门练车点</span>
						<span class="mess">浙江省杭州市下沙高教园区学源街998号</span>
					</dd>
					<dd><i class="iconfont icon-right i"></i></dd>
				</dl>
			</a>
			<a href="placephoto.html">
				<dl class="hover_color_white" type="14">
					<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_bkwy.png"></dt>
					<dd>
						<span class="type">[下沙]计量现代科技学院练车点</span>
						<span class="mess">杭州市江干区下沙高教园区学源街168号</span>
					</dd>
					<dd><i class="iconfont icon-right i"></i></dd>
				</dl>
			</a>
			<a href="placephoto.html">
				<dl class="hover_color_white" type="15">
					<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_bkwy.png"></dt>
					<dd>
						<span class="type">[萧山]萧山区鸿兴路393号练车点</span>
						<span class="mess">浙江省杭州市萧山区鸿兴路393号</span>
					</dd>
					<dd><i class="iconfont icon-right i"></i></dd>
				</dl>
			</a>
			<a href="placephoto.html">
				<dl class="hover_color_white" type="15">
					<dt class="icon_head"><img src="<%=basePath %>images/jianian/icon_bkwy.png"></dt>
					<dd>
						<span class="type">[杭州]其他区域练车点</span>
						<span class="mess">全杭州</span>
					</dd>
					<dd><i class="iconfont icon-right i"></i></dd>
				</dl>
			</a>
		</div>
<!--<div class="confirm">
			<a href="javascript:;" id="send_btn">我也有问题</a>
		</div>-->

		<!--消息提示-->
		<div class="pop-msg">
			<div class="pop-content">
				<p class="pop-p text-center"></p>
				<img src="<%=basePath %>images/jianian/ok.png" class="center">
			</div>
		</div>
		<div class="popupDom">
			<div class="popwindow">
				<p>消息提示</p>
				<div class="popup text-default"></div>
			</div>
		</div>
	</body>
	
	<jsp:include page="/include/commonJiaNianMobileJs.jsp" /> 

</html>