<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	GregorianCalendar gc=new GregorianCalendar(); 
	gc.setTime(new Date()); 
	SimpleDateFormat format = new SimpleDateFormat("yyyy年");
	String Date=format.format(gc.getTime());
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader("Expires",0); 
	
	java.util.Date currTime = new java.util.Date();
	int month = currTime.getMonth()+1;//月
	
	
	List<Integer> monthList=new ArrayList<Integer>();
	List<Integer> yearList=new ArrayList<Integer>();
	for(int i=1;i<=12;i++){
		monthList.add(i);
	}
	for(int i=2016;i<=currTime.getYear()+1900 ;i++){
		yearList.add(i);
	}
	
	request.setAttribute("yearList", yearList);
	request.setAttribute("monthList", monthList);
%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico">
<link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=basePath%>css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>css/style.min.css?v=4.0.0" rel="stylesheet">
 <script src="<%=basePath%>js/demo/echarts-all.js"></script>
</head>
<input type="hidden" id="WeChatLastStat" value="${WeChatLastStat}" />
<input type="hidden" id="WeGroupLastStat" value="${WeGroupLastStat}" />
<input type="hidden" id="Date" value="<%=Date%>">
<input type="hidden" id="month" value="<%=month%>">
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-success pull-right">日</span>
						<h5>微新闻支社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${WeChatDayForDay}</h1>
						<div class="stat-percent font-bold text-success">
							${WeChatDayRisePercent}% 
							<c:choose>
							<c:when test="${WeChatDayRisePercent>0}"><i class="fa fa-level-up"></i></c:when>
							<c:when test="${WeChatDayRisePercent<0}"><i class="fa fa-level-down"></i></c:when>
							
							</c:choose>
							
						</div>
						<small>新增</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-info pull-right">本月</span>
						<h5>微新闻支社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${WeChatDayForMonth}</h1>
						<div class="stat-percent font-bold text-info">
							${WeChatMonthRisePercent}% 
							<c:choose>
							<c:when test="${WeChatMonthRisePercent>0}"><i class="fa fa-level-up"></i></c:when>
							<c:when test="${WeChatMonthRisePercent<0}"><i class="fa fa-level-down"></i></c:when>
							
							</c:choose>
						</div>
						<small>新增</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">日</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${WeChatGroupDayForDay}</h1>
						<div class="stat-percent font-bold text-navy">
							${GroupDayRisePercent}% 
							<c:choose>
							<c:when test="${GroupDayRisePercent>0}"><i class="fa fa-level-up"></i></c:when>
							<c:when test="${GroupDayRisePercent<0}"><i class="fa fa-level-down"></i></c:when>
							
							</c:choose>
						</div>
						<small>新增</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-danger pull-right">本月</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${WeChatGroupDayForMonth}</h1>
						<div class="stat-percent font-bold text-danger">
							${GroupMonthRisePercent}% 
							<c:choose>
							<c:when test="${GroupMonthRisePercent>0}"><i class="fa fa-level-up"></i></c:when>
							<c:when test="${GroupMonthRisePercent<0}"><i class="fa fa-level-down"></i></c:when>
							
							</c:choose>
						</div>
						<small>新增</small>
					</div>
				</div>
			</div>
			
					<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">日</span>
						<h5>微新闻</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${TodaySize}</h1>
						<div class="stat-percent font-bold text-navy">
							${YesterdaySize}% 
							<c:choose>
							<c:when test="${a>0}"><i class="fa fa-level-up"></i></c:when>
							<c:when test="${a<0}"><i class="fa fa-level-down"></i></c:when>
							
							</c:choose>
						</div>
						<small>新增</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-danger pull-right">月</span>
						<h5>微新闻</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${MonthSize}</h1>
						<div class="stat-percent font-bold text-danger">
							${YesMonth}% 
							<c:choose>
							<c:when test="${b>0}"><i class="fa fa-level-up"></i></c:when>
							<c:when test="${b<0}"><i class="fa fa-level-down"></i></c:when>
							
							</c:choose>
						</div>
						<small>新增</small>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 活跃度统计 -->
		
		<div class="row">
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-success pull-right">今日</span>
						<h5>微新闻支社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="YesterdayWeNews" ><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>

						<small>活跃</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-info pull-right">本月</span>
						<h5>微新闻支社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="MonthWeNews" ><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>

						<small>活跃</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">今日</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="YesterdayStudents" ><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>

						<small>活跃</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-danger pull-right">本月</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="MonthStudents" ><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
		
						<small>活跃</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2" id="weChatPublicDiv">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-danger pull-right">注册学校</span>
						<h5>新闻支社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="AllWeNews"><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
						
						<small>总数</small>
					</div>
				</div>
			</div>
			
			<div class="col-sm-2" id="registerDiv" style="cursor:pointer;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">注册学生</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="AllStudent"><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
					
						<small>总数</small>
					</div>
				</div>
			</div>
			
			<div class="col-sm-2" id="accessDiv" type="<%=SmBaseGlobal.ProjectType.WeNewsService.getid() %>" style="cursor:pointer;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-danger pull-right">访问量</span>
						<h5>微新闻社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="accessDivData" ><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
						
						<small>今日</small>
					</div>
				</div>
			</div>
			
			<div class="col-sm-2" id="onLineDiv" type="<%=SmBaseGlobal.ProjectType.WeNewsService.getid() %>" style="cursor:pointer;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">在线人数</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="onLineDivData"><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
					
						<small>最近一分钟</small>
					</div>
				</div>
			</div>
			
			<div class="col-sm-2"  id="accessAppDiv" type="<%=SmBaseGlobal.ProjectType.WeNewsInteface.getid() %>" style="cursor:pointer;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-danger pull-right">APP访问量</span>
						<h5>微新闻社</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="accessAppDivData"><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
						
						<small>今日</small>
					</div>
				</div>
			</div>
			
			<div class="col-sm-2" id="onLineAppDiv" type="<%=SmBaseGlobal.ProjectType.WeNewsInteface.getid() %>" style="cursor:pointer;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">APP在线</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="onLineAppDivData"><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
					
						<small>最近一分钟</small>
					</div>
				</div>
			</div>
			<div class="col-sm-2" id="onLineAppDiv" type="<%=SmBaseGlobal.ProjectType.WeNewsInteface.getid() %>" style="cursor:pointer;">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-primary pull-right">APP活跃</span>
						<h5>新浪小编</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins" id="toDayAPPOnlineCount"><img alt="加载中" style="height: 33px;" src="<%=basePath %>images/loading01.gif"></h1>
					
						<small>今日</small>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5><%=Date%>微新闻社增长情况</h5>
						<div class="pull-right">
							
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-9">
								<div class="flot-chart">
									<div class="flot-chart-content" id="flot-dashboard-chart"></div>
								</div>
							</div>
							<div class="col-sm-3">
								<ul class="stat-list">
									<li>
										<h2 class="no-margins">${WeChatGroupLastCountStat}</h2> <small>本月新增访客</small>
										<div class="stat-percent">
											${WeChatRiseLastPercent}% 
											<c:choose>
												<c:when test="${WeChatRiseLastPercent>0}">
												<i class="fa fa-level-up text-navy"></i></c:when>
												<c:when test="${WeChatRiseLastPercent<0}">
												<i class="fa fa-level-down text-navy"></i></c:when>
												
											</c:choose>
										</div>
										<div class="progress progress-mini">
											<div style="width: ${WeChatRiseLastPercent}%;" class="progress-bar"></div>
										</div>
									</li>
									<li>
										<h2 class="no-margins ">${WeChatLastCountStat}</h2> <small>微新闻支社</small>
										<div class="stat-percent">
											${GroupMonthRiseLastPercent}% 
											<c:choose>
												<c:when test="${GroupMonthRiseLastPercent>0}">
												<i class="fa fa-level-up text-navy"></i></c:when>
												<c:when test="${GroupMonthRiseLastPercent<0}">
												<i class="fa fa-level-down text-navy"></i></c:when>
												
											</c:choose>
											
										</div>
										<div class="progress progress-mini">
											<div style="width: ${GroupMonthRiseLastPercent}%;" class="progress-bar"></div>
										</div>
									</li>
									
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		
	
		
		   <div class="ibox float-e-margins">
                    <div class="ibox-content" style="height: 360px">
	                  <div class="col-sm-6" style="width: 60%">
	                        <div id="main" style="height:300px; ">数据加载中,请稍候...</div> 
	                         <div style="text-align: center; ">
						<button type="button" style="margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a id="NoticeAn"> 查看更多</a>
					</button></div>
	                    </div> 
	                     <div class="col-sm-4" >
	                        <div id="mainArea" style="width: auto;height:300px;"></div> 
	                      </div> 
                    </div>
                </div>
                
    		<div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking" style="height:300px;"></div> 
                  
                     <div style="text-align: center; ">
						<button type="button" onclick="MonQuery(7)"  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
					 </div> 
					  <div class="col-sm-4" >
	                        <div id="mainStudents" style="width: auto;height:300px;"></div> 
	                      </div> 
                   </div>
					
                   </div>
                </div>
                	<div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking2" style="height:300px;"></div> 
                        <div style="text-align: center; ">
						<button type="button" onclick="MonQuery(7)"  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
                   </div> 
                   <div class="col-sm-4">
	                        <div id="main2" style="width: auto;height:300px;"></div> 
	                      </div> 
                   </div>
                </div>

             <div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking3" style="height:300px;"></div> 
                        <div style="text-align: center; ">
						<button type="button" onclick=""  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
                   </div> 
                   <div class="col-sm-4" >
                       <div id="mainRead" style="width: auto;height:300px;"></div> 
                     </div> 
                   </div>
                   </div>
            </div>
             <div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking4" style="height:300px;"></div> 
                        <div style="text-align: center; ">
						<button type="button" onclick=""  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
                   </div> 
                   <div class="col-sm-4" >
                       <div id="mainLike" style="width: auto;height:300px;"></div> 
                     </div> 
                   </div>
            </div>
               <div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking5" style="height:300px;"></div> 
                        <div style="text-align: center;">
						<button type="button" onclick=""  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
                   </div> 
                   <div class="col-sm-4">
                       <div id="mainReward" style="width: auto;height:300px;"></div> 
                     </div> 
                   </div>
            </div>
                <div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking6" style="height:300px;"></div> 
                        <div style="text-align: center; ">
						<button type="button" onclick=""  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
                   </div> 
                   <div class="col-sm-4">
                       <div id="mainInt" style="width: auto;height:300px;"></div> 
                     </div> 
                   </div>
            </div>
                            <div class="ibox float-e-margins">
                <div class="ibox-content" style="height: 360px;">
                  <div class="col-sm-6"  style="width: 60%">
                     <div id="Ranking7" style="height:300px;"></div> 
                        <div style="text-align: center; ">
						<button type="button" onclick=""  style="    margin-top: -10px;"
						class="btn btn-outline btn-default">
						<i class="" title=""
							aria-hidden="true"></i><a href="<%=path%>/Ranking/RankingList" target="_self"> 查看更多</a>
					</button></div>
                   </div> 
                   <div class="col-sm-4">
                       <div id="mainWemoney" style="width: auto;height:300px;"></div> 
                     </div> 
                   </div>
            </div>
    
            </div>        
<div class="modal" id="NoticesAng" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">投稿趋势</h4>
					
					
				</div>
				<div class="modal-body">
				<div class="col-md-3">
				<select class="form-control m-b"  onchange="referNoticesCount()" 
						id="showType" name="showType">
						<option value="">按日展示</option>
						<option value="year">按月展示</option>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referNoticesCount()" 
						id="showyear" name="showyear">
						<option value=""></option>
						<c:forEach var="year" items="${yearList}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					</div>
				 <div id="mainStat" style="height:300px; width: 600px">数据加载中,请稍候...</div> 
					
					
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="WeChatPublicAng" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">增长趋势</h4>
					
					
				</div>
				<div class="modal-body">
				<div class="col-md-3">
				<select class="form-control m-b"  onchange="referWeChatPublicCount()" 
						id="wpshowType" name="wpshowType">
						<option value="month">按日展示</option>
						<option value="year">按月展示</option>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referWeChatPublicCount()" 
						id="wpshowyear" name="wpshowyear">
						<option value=""></option>
						<c:forEach var="year" items="${yearList}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referWeChatPublicCount()" 
						id="wpshowmonth" name="wpshowmonth">
						<option value=""></option>
						<c:forEach var="month" items="${monthList}">
							<option value="${month}">${month}</option>
						</c:forEach>
					</select>
					</div>
				 <div id="wpmainStat" style="height:300px; width: 600px">数据加载中,请稍候...</div> 
					
					
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="modal" id="AccessActivityAng" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >访问趋势</h4>
					
					
				</div>
				<div class="modal-body">
				<div class="col-md-3">
				<select class="form-control m-b"  onchange="referAccessActiveCount()" 
						id="aashowType" name="aashowType">
						<option value="hour">今日</option>
						<option value="month">按日展示</option>
						<option value="year">按月展示</option>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referAccessActiveCount()" 
						id="aashowyear" name="aashowyear">
						<option value=""></option>
						<c:forEach var="year" items="${yearList}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referAccessActiveCount()" 
						id="aashowmonth" name="aashowmonth">
						<option value=""></option>
						<c:forEach var="month" items="${monthList}">
							<option value="${month}">${month}</option>
						</c:forEach>
					</select>
					</div>
				 <div id="aamainStat" style="height:300px; width: 600px">数据加载中,请稍候...</div> 
					
					
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="modal" id="RegisterAng" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >注册趋势</h4>
					
					
				</div>
				<div class="modal-body">
				<div class="col-md-3">
				<select class="form-control m-b"  onchange="referRegisterCount()" 
						id="rsshowType" name="rsshowType">
						<option value="hour">今日</option>
						<option value="month">按日展示</option>
						<option value="year">按月展示</option>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referRegisterCount()" 
						id="rsshowyear" name="rsshowyear">
						<option value=""></option>
						<c:forEach var="year" items="${yearList}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					</div>
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="referRegisterCount()" 
						id="rsshowmonth" name="rsshowmonth">
						<option value=""></option>
						<c:forEach var="month" items="${monthList}">
							<option value="${month}">${month}</option>
						</c:forEach>
					</select>
					</div>
				 <div id="rsmainStat" style="height:300px; width: 600px">数据加载中,请稍候...</div> 
					
					
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="modal" id="onLineAng" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" >在线人数</h4>
					
					
				</div>
				<div class="modal-body">
					<div class="col-md-3">
					<select class="form-control m-b"  onchange="refeonLineCount()" 
						id="onLineyear" name="onLineyear">
						<option value="1">最近15分钟</option>
						<option value="2">最近1小时</option>
						<option value="3">今日</option>
					</select>
					</div>
				 <div id="onLineStat" style="height:300px; width: 600px">数据加载中,请稍候...</div> 
					
					
				</div>
			</div>
		</div>
	</div>
		<script src="<%=basePath%>js/jquery.min.js?v=2.1.4"></script>
		<script src="<%=basePath%>js/bootstrap.min.js?v=3.3.5"></script>
		<script src="<%=basePath%>js/content.min.js?v=1.0.0"></script>
		<script src="<%=basePath%>js/plugins/flot/jquery.flot.js"></script>
		<script src="<%=basePath%>js/plugins/flot/jquery.flot.tooltip.min.js"></script>
		<script src="<%=basePath%>js/plugins/flot/jquery.flot.spline.js"></script>
		<script src="<%=basePath%>js/plugins/flot/jquery.flot.resize.js"></script>
		<script src="<%=basePath%>js/plugins/flot/jquery.flot.pie.js"></script>
		<script src="<%=basePath%>js/plugins/flot/jquery.flot.symbol.js"></script>
		<script src="<%=basePath%>js/plugins/peity/jquery.peity.min.js"></script>
		<script src="<%=basePath%>js/plugins/jquery-ui/jquery-ui.min.js"></script>
		<script
			src="<%=basePath%>js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script
			src="<%=basePath%>js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
		<script
			src="<%=basePath%>js/plugins/easypiechart/jquery.easypiechart.js"></script>
		<script
			src="<%=basePath%>js/plugins/sparkline/jquery.sparkline.min.js"></script>
		<script src="<%=basePath%>js/wechat_index.js?v=1.0.6.3"></script>
		<script>
		getAccessData();
		getAllStatData();
		$(document).ready(function() {

			var data2 = $("#WeGroupLastStat").val().toString().split(";"); 
			var data3 = $("#WeChatLastStat").val().toString().split(";");  
			data2.forEach(function(e, index, data2) {
			    var child=e.split(":");
			    
			    child.forEach(function(e, index, child) {
			        child[index] = eval(child[index])
			    });
			    data2[index] = child;
			})
			data3.forEach(function(e, index, data3) {
			    var child=e.split(":");
			    
			    child.forEach(function(e, index, child) {
			        child[index] = eval(child[index])
			    });
			    data3[index] = child;
				
			});
			
			var dataset = [{
		        label: "微新闻社",
		        data: data3,
		        color: "#1ab394",
		        bars: {
		            show: true,
		            align: "center",
		            barWidth: 24 * 60 * 60 * 600,
		            lineWidth: 0
		        }
		    },
		    {
		        label: "新浪小编",
		        data: data2,
		        yaxis: 2,
		        color: "#464f88",
		        lines: {
		            lineWidth: 1,
		            show: true,
		            fill: true,
		            fillColor: {
		                colors: [{
		                    opacity: 0.2
		                },
		                {
		                    opacity: 0.2
		                }]
		            }
		        },
		        splines: {
		            show: false,
		            tension: 0.6,
		            lineWidth: 1,
		            fill: 0.1
		        },
		    }];
		    var options = {
		        xaxis: {
		            mode: "time",
		            tickSize: [3, "day"],
		            tickLength: 0,
		            axisLabel: "Date",
		            axisLabelUseCanvas: true,
		            axisLabelFontSizePixels: 12,
		            axisLabelFontFamily: "Arial",
		            axisLabelPadding: 10,
		            color: "#838383"
		        }, 
		        yaxes: [{
		            position: "left",
		            
		            color: "#838383",
		            axisLabelUseCanvas: true,
		            axisLabelFontSizePixels: 12,
		            axisLabelFontFamily: "Arial",
		            axisLabelPadding: 3
		        },
		        {
		            position: "right",
		            clolor: "#838383",
		            axisLabelUseCanvas: true,
		            axisLabelFontSizePixels: 12,
		            axisLabelFontFamily: " Arial",
		            axisLabelPadding: 67
		        }],
		        legend: {
		            noColumns: 1,
		            labelBoxBorderColor: "#000000",
		            position: "nw"
		        },
		        grid: {
		            hoverable: false,
		            borderWidth: 0,
		            color: "#838383"
		        }
		    };
		    function gd(year, month, day) {
		        return new Date(year, month - 1, day).getTime()
		    }
		   
		    $.plot($("#flot-dashboard-chart"), dataset, options);
		});

	var myChart = echarts.init(document.getElementById('main'));
	
		 $.get('getCurrentMonthNotices?v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		  var days=[];
		  for(var i=0;i<data.dayCount;i++){
			  days.push((i+1)+'号');
		  }

		  myChart.setOption({
			  title : {
			    	 text: $("#Date").val()+data.name+"微新闻新闻投稿增长情况",
			    },
			    tooltip: {trigger: "axis"},
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data :days
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} 条'
			            }
			        }
			    ],
			    series : [
			        {
			            name:data.name+'投稿数',
			            type:'line',
			            data:data.data,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'} 
			                ] 
			            },
			        }
			    ]
			  })

	 		});
		  
		 var myChart2 = echarts.init(document.getElementById('main2'));
		// 异步加载数据
		 $.get('getSchoolNotices').done(function (data) {
			 var days=[];
			  for(var i=0;i<data.name.length;i++){
				  days.push(data.name[i]);
			  }
			 myChart2.setOption({
				 title : {
			    	 text: '昨天各学校发稿量统计',
				        x:'center'
			    },
			  
			    tooltip: {},
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data: ['昨天各学校发稿前10']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'投稿数',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:data.data
			        }
			    ]
			  });
		 });
		 
		 var myChartArea = echarts.init(document.getElementById('mainArea'));
			// 异步加载数据
			 $.get('getAreaNotices?v=2').done(function (data) {
				 var days=[];
				  for(var i=0;i<data.name.length;i++){
					  days.push(data.name[i]);
				  }
				  myChartArea.setOption({
					 title : {
				    	 text: '昨天各地区发稿量统计',
					        x:'center'
				    },
				  
				    tooltip: {},
				    legend: {
				        orient : 'vertical',
				        x : 'left',
				        data: ['昨天各地区发稿前10']
				    },
				    calculable : true,
				    series : [
				        {
				            name:'投稿数',
				            type:'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data.data
				        }
				    ]
				  });
			 });
			 
			var myChartStudent = echarts.init(document.getElementById('mainStudents'));
				// 异步加载数据
				 $.get('getStudentsNoticesCount?v=2').done(function (data) {
					 var days=[];
					  myChartStudent.setOption({
						 title : {
					    	 text: '昨天学生发稿量前10',
						        x:'center'
					    },
					  
					    tooltip: {},
					    legend: {
					        orient : 'vertical',
					        x : 'left',
					        data: ['昨天学生发稿量前10']
					    },
					    calculable : true,
					    series : [
					        {
					            name:'投稿数',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:data.data
					        }
					    ]
					  });
				 });	 
	var Ranking = echarts.init(document.getElementById('Ranking'));
		 $.get('getAllStudentsNotices?v=1').done(function (data) {
			 var days=[];
			  for(var i=0;i<data.name.length;i++){
				  days.push(data.name[i]);
			  }
				Ranking.setOption({
					title : {
				        text: '学生发稿排行榜TOP20'

				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['发稿量']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : days
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'发稿量',
				            type:'bar',
				            data:data.data
				        }

				    ]
					
				});
				
		 });
				
				
				
				
				
	var Ranking2 = echarts.init(document.getElementById('Ranking2'));
	 $.get('getAllSchoolNotices?v=1').done(function (data) {		
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
			Ranking2.setOption({
				title : {
			        text: '学校发稿排行榜TOP20'

			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['发稿量']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : days
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'发稿量',
			            type:'bar',
			            data:data.data
			        }

			    ]
				});
			});

	 var myChartRead = echarts.init(document.getElementById('mainRead'));
		// 异步加载数据
		 $.get('getAllReadNotices?type=1').done(function (data) {
			 var days=[];
			  for(var i=0;i<data.name.length;i++){
				  days.push(data.name[i]);
			  }
			  myChartRead.setOption({
				 title : {
			    	 text: '昨天新闻阅读排行榜TOP10',
				        x:'center'
			    },
			  
			    tooltip: {},
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data: ['昨天新闻阅读排行榜TOP10']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'阅读数',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:data.data
			        }
			    ]
			  });
		 });
	var Ranking3 = echarts.init(document.getElementById('Ranking3'));
	 $.get('getAllReadNotices?v=1').done(function (data) {	
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
		Ranking3.setOption({
			title : {
		        text: '新闻阅读排行榜TOP20'

		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['阅读数']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : days
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'阅读数',
		            type:'bar',
		            data:data.data
		        }

		    ]
		});
		
	});
				    

	 var myChartLike = echarts.init(document.getElementById('mainLike'));
		// 异步加载数据
		 $.get('getAllLikeNotices?type=1').done(function (data) {
			 var days=[];
			  for(var i=0;i<data.name.length;i++){
				  days.push(data.name[i]);
			  }
			  myChartLike.setOption({
				 title : {
			    	 text: '昨天新闻点赞排行榜TOP10',
				        x:'center'
			    },
			  
			    tooltip: {},
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data: ['昨天新闻点赞排行榜TOP10']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'点赞数',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:data.data
			        }
			    ]
			  });
		 });
	var Ranking4 = echarts.init(document.getElementById('Ranking4'));

	 $.get('getAllLikeNotices?v=1').done(function (data) {	
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
		  Ranking4.setOption({
			  title : {
			        text: '新闻点赞排行榜TOP20'

			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['点赞量']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : days
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'点赞量',
			            type:'bar',
			            data:data.data
			        }

			    ]
			  });
		  });

				    

	 var myChartReward = echarts.init(document.getElementById('mainReward'));
		// 异步加载数据
		 $.get('getAllRewardNotices?type=1').done(function (data) {
			 var days=[];
			  for(var i=0;i<data.name.length;i++){
				  days.push(data.name[i]);
			  }
			  myChartReward.setOption({
				 title : {
			    	 text: '昨天新闻赞赏排行榜TOP10',
				        x:'center'
			    },
			  
			    tooltip: {},
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data: ['昨天新闻赞赏排行榜TOP10']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'赞赏数',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:data.data
			        }
			    ]
			  });
		 });
	var Ranking5 = echarts.init(document.getElementById('Ranking5'));
	$.get('getAllRewardNotices?v=1').done(function (data) {	
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
		Ranking5.setOption({
			title : {
		        text: '新闻赞赏排行榜TOP20'

		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['赞赏量']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : days
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'赞赏量',
		            type:'bar',
		            data:data.data
		        }

		    ]
		});
	});
				    
	 var myChartInt = echarts.init(document.getElementById('mainInt'));
		// 异步加载数据
		 $.get('getIntegrationNotices?type=1').done(function (data) {
			 var days=[];
			  for(var i=0;i<data.name.length;i++){
				  days.push(data.name[i]);
			  }
			  myChartInt.setOption({
				 title : {
			    	 text: '昨天积分增长排行Top10',
				        x:'center'
			    },
			  
			    tooltip: {},
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data: ['昨天积分增长排行Top10']
			    },
			    calculable : true,
			    series : [
			        {
			            name:'积分数',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:data.data
			        }
			    ]
			  });
		 });
	var Ranking6 = echarts.init(document.getElementById('Ranking6')); 
	$.get('getAllIntegrationNotices?v=1').done(function (data) {	
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
		  Ranking6.setOption({
			  title : {
			        text: '学生积分排行榜TOP20'

			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['积分数']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : days
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'积分数',
			            type:'bar',
			            data:data.data
			        }

			    ]
		  });
		  
	});	    
	var myChartWemoney = echarts.init(document.getElementById('mainWemoney'));
	// 异步加载数据
	 $.get('getWeMoneyNotices?type=1').done(function (data) {
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
		  myChartWemoney.setOption({
			 title : {
		    	 text: '昨天微米打赏排行Top10',
			        x:'center'
		    },
		  
		    tooltip: {},
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data: ['昨天微米打赏排行Top10']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'微米数',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:data.data
		        }
		    ]
		  });
	 });
	var Ranking7 = echarts.init(document.getElementById('Ranking7'));
	$.get('getAllWeMoneyNotices?v=1').done(function (data) {	
		 var days=[];
		  for(var i=0;i<data.name.length;i++){
			  days.push(data.name[i]);
		  }
		  Ranking7.setOption({
			  title : {
			        text: '学生微米排行榜TOP20'

			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['微米数']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : days
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'微米数',
			            type:'bar',
			            data:data.data
			        }

			    ]
			  
		  });
	});

	
	
	
	
	$("#NoticeAn").click(function(){

		$("#NoticesAng").modal("show");
		referNoticesCount(0);
	});
	
	function referNoticesCount(year,type){
		var year=$("#showyear").val();
		var type=$("#showType").val();
		var myChart = echarts.init(document.getElementById('mainStat'));
		if(year==null || year=="" || year=="0"){
			var date = new Date();
			year=1900+date.getYear();
		}
		if(type==null || type==""){
			type="";
		}
		 $.get('getCurrentYearNotices?type='+ type +'&year='+ year +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		 
			 initialNoticesStatChat(myChart,data);
	 		});
	}
	
	
	var AccType=1;
	
	$("#accessDiv").click(function(){

		$("#AccessActivityAng").modal("show");
		AccType = $(this).attr("type")
		referAccessActiveCount(0);
	});
	$("#accessAppDiv").click(function(){

		$("#AccessActivityAng").modal("show");
		AccType = $(this).attr("type")
		referAccessActiveCount(0);
	});
	
	function referAccessActiveCount(year){
		var year=$("#aashowyear").val();
		var type=$("#aashowType").val();
		var month=$("#aashowmonth").val();
		
		var myChart = echarts.init(document.getElementById('aamainStat'));
		if(year==null || year=="" || year=="0"){
			var date = new Date();
			year=1900+date.getYear();
		}
		if(type==null || type==""){
			type="";
		}
		 $.get('getCurrentYearAccessActive?atype='+ AccType +'&type='+ type +'&month='+ month +'&year='+ year +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		 
			 initialNoticesStatChat(myChart,data);
	 		});
	}
	
	
	
	
	
	$("#registerDiv").click(function(){

		$("#RegisterAng").modal("show");
		referRegisterCount(0);
	});
	
	$("#weChatPublicDiv").click(function(){

		$("#WeChatPublicAng").modal("show");
		referWeChatPublicCount(0);
	});
	
	
	function referWeChatPublicCount(){
		var year=$("#wpshowyear").val();
		var type=$("#wpshowType").val();
		var month=$("#wpshowmonth").val();
		var myChart = echarts.init(document.getElementById('wpmainStat'));
		if(year==null || year=="" || year=="0"){
			var date = new Date();
			year=1900+date.getYear();
		}
		if(type==null || type==""){
			type="";
		}
		 $.get('getCurrentWeChatPublic?type='+ type +'&month='+ month +'&year='+ year +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		 
			 initialNoticesStatChat(myChart,data);
	 		});
	}
	
	
	
	
	function referRegisterCount(year,type){
		var year=$("#rsshowyear").val();
		var type=$("#rsshowType").val();
		var month=$("#rsshowmonth").val();
		var myChart = echarts.init(document.getElementById('rsmainStat'));
		if(year==null || year=="" || year=="0"){
			var date = new Date();
			year=1900+date.getYear();
		}
		if(type==null || type==""){
			type="";
		}
		 $.get('getCurrentYearStudentsRegister?type='+ type +'&month='+ month +'&year='+ year +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		 
			 initialNoticesStatChat(myChart,data);
	 		});
	}
	
	var onLineType=1
	$("#onLineDiv").click(function(){

		$("#onLineAng").modal("show");
		onLineType=$(this).attr("type");
		refeonLineCount(0);
	});
	$("#onLineAppDiv").click(function(){

		$("#onLineAng").modal("show");
		onLineType=$(this).attr("type");
		refeonLineCount(0);
	});
	function refeonLineCount(year){
		var type=$("#onLineyear").val();
		var myChart = echarts.init(document.getElementById('onLineStat'));
		
		 $.get('getCurrentOnlineStat?atype='+ onLineType +'&type='+ type +'&v=<%=SmBaseUtil.Random() %>').done(function (data) {	    
		 
			 initialNoticesStatChat(myChart,data);
	 		});
	}
	
	
	function getAccessData(){
		 $.get('getAccessData').done(function (data) {	    
		 	
		 	$("#onLineDivData").text(data.currentOnline);
		 	$("#accessDivData").text(data.todayAccessCount);
		 	
	 	});
		 $.get('getAPPAccessData').done(function (data) {	    
			 	$("#onLineAppDivData").text(data.currentAPPOnline);
			 	$("#accessAppDivData").text(data.todayAPPAccessCount);
			 	$("#toDayAPPOnlineCount").text(data.toDayAPPOnlineCount);
			 	
		 });
		 
		 
	}
	
	function getAllStatData(){
		 $.get('getYesterdayWeNews').done(function (data) {	    
		 	$("#YesterdayWeNews").text(data.YesterdayWeNews);
	 	});
		 $.get('getMonthWeNews').done(function (data) {	    
			 	$("#MonthWeNews").text(data.MonthWeNews);
		 	});
		 $.get('getYesterdayStudents').done(function (data) {	    
			 	$("#YesterdayStudents").text(data.YesterdayStudents);
		 	});
		 $.get('getMonthStudents').done(function (data) {	    
			 	$("#MonthStudents").text(data.MonthStudents);
		 	});
		 $.get('getAllWeNews').done(function (data) {	    
			 	$("#AllWeNews").text(data.AllWeNews);
		 	});
		 $.get('getAllStudent').done(function (data) {	    
			 	$("#AllStudent").text(data.AllStudent);
		 });
	}
	
	
		</script>
	</div>
</body>
</html>