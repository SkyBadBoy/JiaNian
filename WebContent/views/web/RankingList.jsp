<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath =SmBaseUtil.getProjectBaseUrl(request);
	Users user =(Users)request.getSession().getAttribute("UserName");

	boolean isApply=false;
	String Status=request.getParameter("Status");
	if(Status!=null && !Status.isEmpty() ){
		isApply=(Integer.parseInt(Status)==SmBaseGlobal.CheckStatus.Apply.getid());
	}
	request.setAttribute("isApply", isApply);
	request.setAttribute("Level", user.getLevel());
	
    String rootPath=getClass().getResource("/").getFile().toString();
    rootPath=rootPath.replace("classes/", "");
    rootPath=rootPath.replace("WEB-INF/", "");
	
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />

</head>

<body>

	<label style="display: none;" id="nsid"></label>
	<input type="hidden" id="urlR" value="getStudentRankingList">
	<div class="container-fluid">
		<input type="hidden" name="NewsID" value="${NewsID}" id="NewsID" />
		<input type="hidden" name="shang" id="shang" value="${shang}" />
		<input type="hidden" name="key" id="key" value="${key}" />
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>排行榜</strong></li>
				</ol>
			</div>
			<br/>
			<div id="queryArea" style="width:50%;padding: 10px;"></div>
		</div>
                  <div class="tabs-container" style="margin-top: 20px">
                    <ul class="nav nav-tabs" >
                        <li class="active" style="width: 14.3%;text-align: center;" ><a data-toggle="tab" href="#tab-1" aria-expanded="true"> 学生发稿排行榜</a>
                        </li>
                        <li class="" style="width: 14.2%;text-align: center;"><a data-toggle="tab" href="#tab-2" aria-expanded="false">学校发稿排行榜</a>
                        </li>
                         <li style="width: 14.3%;text-align: center;"><a data-toggle="tab" href="#tab-3" aria-expanded="true"> 新闻阅读排行榜</a>
                        </li>
                        <li class="" style="width: 14.3%;text-align: center;"><a data-toggle="tab" href="#tab-4" aria-expanded="false">新闻点赞排行榜</a>
                        </li>
                         <li style="width: 14.3%;text-align: center;"><a data-toggle="tab" href="#tab-5" aria-expanded="true"> 新闻赞赏排行榜</a>
                        </li>
                        <li class="" style="width: 14.3%;text-align: center;"><a data-toggle="tab" href="#tab-6" aria-expanded="false">学生积分排行榜</a>
                        </li>
                        <li class="" style="width: 14.3%;text-align: center;"><a data-toggle="tab" href="#tab-7" aria-expanded="false"  >学生微米排行榜</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart1" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd1" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 查询
									</button>
									<button type="button" onclick="MonQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
									<button type="button" onclick="SMonQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(1)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents1" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-width="200" data-field="publishUser" >学生编号</th>
										<th data-width="200" data-field="student.name"  >学生姓名</th>
										<th data-width="200" data-field="student.phone" id="name">学生电话</th>
										<th data-width="200" data-field="student.school"  >学校</th>
										<th data-field="count" id="amount" data-width="200" >学生发稿量</th>
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                   <div id="tab-2" class="tab-pane">
                                  <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart2" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd2" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title="Excel导出"
											aria-hidden="true"></i> 查询
									</button>
										<button type="button" onclick="MonQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
										<button type="button" onclick="SMonQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(2)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents2" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
									<th data-width="200" data-field="sregion.ID" >学校编号</th>
										<th data-width="200" data-field="sregion.Name"  style="width: 50%;" >学校名称</th>
										<th data-field="count" id="amount" data-width="200" >学校发稿数</th>
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                         	<div id="tab-3" class="tab-pane">
                                  <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart3" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd3" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title="Excel导出"
											aria-hidden="true"></i> 查询
									</button>
										<button type="button" onclick="MonQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
										<button type="button" onclick="SMonQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(3)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents3" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
									<th data-width="200" data-field="pkid" >新闻编号</th>
										<th data-width="200" data-field="title" >新闻标题</th>
											<th data-field="author">作者</th>
												<th data-width="200" data-field="student.phone" id="name">作者电话</th>
											<th data-field=createTime >发稿时间</th>
											<th data-field="sregion.Name" >学校</th>
										<th data-field="clickCount" >学生阅读数</th>
									
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                        <div id="tab-4" class="tab-pane">
                                  <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart4" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd4" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title="Excel导出"
											aria-hidden="true"></i> 查询
									</button>
										<button type="button" onclick="MonQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
										<button type="button" onclick="SMonQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(4)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents4" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
									<th data-width="200" data-field="pkid" >新闻编号</th>
										<th data-width="200" data-field="title"  style="width: 50%;" >新闻名字</th>
										<th data-width="200" data-field="author"  style="width: 50%;" >作者名字</th>
											<th data-width="200" data-field="student.phone" id="name">作者电话</th>
											<th data-width="200" data-field="sregion.Name" >学校</th>
										<th data-width="200" data-field="createTime"  style="width: 50%;" >发布时间</th>
										<th data-field="like" id="amount"  >学生点赞数</th>
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                        	<div id="tab-5" class="tab-pane">
                                  <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart5" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd5" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title="Excel导出"
											aria-hidden="true"></i> 查询
									</button>
										<button type="button" onclick="MonQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
										<button type="button" onclick="SMonQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(5)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents5" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
									<th data-width="200" data-field="notices.pkid" >新闻编号</th>
										<th data-width="200" data-field="notices.title"  >新闻标题</th>
										<th data-width="200" data-field="notices.author"  >作者</th>
										<th data-width="200" data-field="notices.student.phone" id="name">作者电话</th>
										<th data-width="200" data-field="notices.sregion.Name" >学校</th>
											<th data-width="200" data-field="notices.createTime"  >发布时间</th>
										<th  data-field="count" id="count" data-width="200" >新闻赞赏数</th>
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                          	<div id="tab-6" class="tab-pane">
                                  <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart6" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd6" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title="Excel导出"
											aria-hidden="true"></i> 查询
									</button>
										<button type="button" onclick="MonQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
										<button type="button" onclick="SMonQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(6)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents6" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
									<th data-width="200" data-field="student.pkid" id="name">学生编号</th>
										<th data-width="200" data-field="student.name" id="name">学生姓名</th>
										<th data-width="200" data-field="student.phone" id="name">学生电话</th>
										<th data-field="student.school" id="amount" data-width="200" >学生学校</th>
										<th data-field="num" id="amount" data-width="200" >学生积分数</th>
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                 	<div id="tab-7" class="tab-pane">
                                  <div class="panel-body">
                            	<span style="">
									<input size="16" type="text" id="datetimeStart7" readonly class="form_datetime" style="width: 67px;height: 34px;"> ---
									<input size="16" type="text" id="datetimeEnd7" readonly class="form_datetime" style="width: 67px;height: 34px;">
									<button type="button" onclick="TimeQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title="Excel导出"
											aria-hidden="true"></i> 查询
									</button>
										<button type="button" onclick="MonQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本周
									</button>
									<button type="button" onclick="YueQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本月
									</button>
									<button type="button" onclick="NianQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 本年
									</button>
										<button type="button" onclick="SMonQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上周
									</button>
									<button type="button" onclick="SYueQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上月
									</button>
									<button type="button" onclick="SNianQuery(7)"  style="    margin-top: -2px"
										class="btn btn-outline btn-default">
										<i class="" title=""
											aria-hidden="true"></i> 上年
									</button>
								</span>
                                <table id="exampleTableEvents7" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-width="200" data-field="fromUser.pkid"  >学生编号</th>
										<th data-width="200" data-field="fromUser.name"  >学生姓名</th>
										<th data-width="200" data-field="fromUser.phone" id="name">学生电话</th>
										<th data-width="200" data-field="fromUser.school"  >学校</th>
										<th data-field="count" id="amount" data-width="200" >学生微米数</th>
									</tr>
								</thead>
							</table>
                            </div>
                        </div>
                    </div>


                </div>

	<jsp:include page="/include/commonJs.jsp" />
	<script src="<%=basePath%>js/weekutil.js"></script>

	<script type="text/javascript">
	initialRegionQueryInfo($("#queryArea"),'query',1);
		var d = getPreviousWeekStartEnd();
		Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}

	 
    $("#datetimeStart1").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart1").datetimepicker("setEndDate",$("#datetimeEnd1").val())
    });
    $("#datetimeEnd1").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd1").datetimepicker("setStartDate",$("#datetimeStart1").val())
    });
     $("#datetimeStart2").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart2").datetimepicker("setEndDate",$("#datetimeEnd2").val())
    });
    $("#datetimeEnd2").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd2").datetimepicker("setStartDate",$("#datetimeStart2".val()))
    });
        $("#datetimeStart3").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart3").datetimepicker("setEndDate",$("#datetimeEnd3").val())
    });
    $("#datetimeEnd3").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd3").datetimepicker("setStartDate",$("#datetimeStart3".val()))
    });
        $("#datetimeStart4").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart4").datetimepicker("setEndDate",$("#datetimeEnd4").val())
    });
    $("#datetimeEnd4").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd4").datetimepicker("setStartDate",$("#datetimeStart4".val()))
    });
    $("#datetimeStart5").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart5").datetimepicker("setEndDate",$("#datetimeEnd5").val())
    });
    $("#datetimeEnd5").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd5").datetimepicker("setStartDate",$("#datetimeStart5".val()))
    });
    $("#datetimeStart6").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart6").datetimepicker("setEndDate",$("#datetimeEnd6").val())
    });
    $("#datetimeEnd6").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd6").datetimepicker("setStartDate",$("#datetimeStart6".val()))
    });
    $("#datetimeStart7").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeStart7").datetimepicker("setEndDate",$("#datetimeEnd7").val())
    });
    $("#datetimeEnd7").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        defaultValue: new Date()
    }).on("click",function(){
        $("#datetimeEnd7").datetimepicker("setStartDate",$("#datetimeStart7".val()))
    });
	var DataTable1=$("#exampleTableEvents1");
	var DataTable2=$("#exampleTableEvents2");
	var DataTable3=$("#exampleTableEvents3");
	var DataTable4=$("#exampleTableEvents4");
	var DataTable5=$("#exampleTableEvents5");
	var DataTable6=$("#exampleTableEvents6");
	var DataTable7=$("#exampleTableEvents7");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>",
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>",
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
					DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>",
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
						DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>",
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>",
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>",
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>",
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
			
			}()
		}(document, window, jQuery);

	
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset,
				AreaID : $("#UnitAreaID").val()
			};

		}
		
		function TimeQuery(i){
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart1").val()+"&endTime="+$("#datetimeEnd1").val(),
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart2").val()+"&endTime="+$("#datetimeEnd2").val(),
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart3").val()+"&endTime="+$("#datetimeEnd3").val(),
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});

				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart4").val()+"&endTime="+$("#datetimeEnd4").val(),
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart5").val()+"&endTime="+$("#datetimeEnd5").val(),
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart6").val()+"&endTime="+$("#datetimeEnd6").val(),
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+$("#datetimeStart7").val()+"&endTime="+$("#datetimeEnd7").val(),
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;

			default:
				break;
			}
			
		}
		function MonQuery(i){
			var statrMon=monday.Format("yyyy-MM-dd");
			var endSun=sunday.Format("yyyy-MM-dd");
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;

			default:
				break;
			}
			
		}

		function YueQuery(i){
			var statrMon=getMonthStartDate.Format("yyyy-MM-dd");
			var endSun=getMonthEndDate.Format("yyyy-MM-dd");
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;

			default:
				break;
			}
			
		}

		function NianQuery(i){
			var statrMon=new Date().Format("yyyy-01-01");
			var endSun=new Date().Format("yyyy-12-31");
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;

			default:
				break;
			}
			
		}
		


		function SMonQuery(i){
			var statrMon=d.start;
			var endSun=d.end;
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;

			default:
				break;
			}
			
		}

		function SYueQuery(i){
			var statrMon=getLastMonthStartDate;
			var endSun=getLastMonthEndDate;
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url :"<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
				
				break;

			default:
				break;
			}
			
		}

		function SNianQuery(i){
			var now = new Date();
			now.setFullYear(now.getFullYear()+1);
			var statrMon=now.Format("yyyy-01-01");
			var endSun=now.Format("yyyy-12-31");
			switch (i) {
			case 1:
				DataTable1.bootstrapTable('destroy'); 
				DataTable1.bootstrapTable({
					url : "<%=path%>/Ranking/getStudentRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
					showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 2:
				DataTable2.bootstrapTable('destroy'); 
				DataTable2.bootstrapTable({
					url :  "<%=path%>/Ranking/getNoticesSchoolRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 3:
				DataTable3.bootstrapTable('destroy'); 
				DataTable3.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeReadRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 4:
				DataTable4.bootstrapTable('destroy'); 
				DataTable4.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeLikeRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
				
			case 5:
				DataTable5.bootstrapTable('destroy'); 
				DataTable5.bootstrapTable({
					url : "<%=path%>/Ranking/getNoticeAppreciateRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
		
				break;
			case 6:
				DataTable6.bootstrapTable('destroy'); 
				DataTable6.bootstrapTable({
					url : "<%=path%>/Ranking/getIntegrationRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;
			case 7:
				DataTable7.bootstrapTable('destroy'); 
				DataTable7.bootstrapTable({
					url : "<%=path%>/Ranking/getWeMoneyRankingList?sina=<%= SmBaseUtil.Random() %>&startTime="+statrMon+"&endTime="+endSun,
										showExport: true,                     //是否显示导出
					exportDataType: "basic",              //basic', 'all', 'selected'.
					pageList: [10, 25, 50, 100,1000], 
					pagination : !0,
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					rowStyle:dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			
				break;

			default:
				break;
			}
			
		}
		function getQueryList(){
			$(".nav-tabs").find("li").each(function(i,data){
				if($(data).attr("class")=='active'){
					TimeQuery((i+1));
					console.log(i);
				}
				
			})
		}
	</script>
</body>
</html>