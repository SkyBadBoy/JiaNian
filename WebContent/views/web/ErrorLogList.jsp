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

	Object nameAll=request.getAttribute("nameAll");
	Object dataAll=request.getAttribute("dataAll");
	Object dataDay=request.getAttribute("dataDay");
	Object nameDay=request.getAttribute("nameDay");
	Object dataCount=request.getAttribute("dataCount");
	Object data=request.getAttribute("data");
	Object max=request.getAttribute("max");
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />

<style type="text/css">
#Layer1 {
    width: 450px;
    margin:-120px;
    z-index: 50;
    display: none;
    position: relative;
}

#Layer1 #win_top {
    height: 30px;
    width: 450px;
    border-bottom-width: 1px;
    border-bottom-style: solid;
    border-bottom-color: #999;
    line-height: 30px;
    color: #666;
    font-family: "微软雅黑", Verdana, sans-serif, "宋体";
    font-weight: bold;
    text-indent: 1em;
}

#Layer1 #win_top a {
    float: right;
    margin-right: 5px;
}

#shade {
    background-color:#000;
    position:absolute;
    z-index:49;
    display:none;
    width:100%;
    height:100%;
    opacity:0.6;
    filter: alpha(opacity=60);
    -moz-opacity: 0.6;
    margin: 0px;
    left: 0px;
    top: 0px;
    right: 0px;
    bottom: 0px;
}

#Layer1 .content {
    margin-top: 5px;
    margin-right: 30px;
    margin-left: 30px;
}


#iphone {
    position: fixed;
    left: 50%;
    margin-top:45px;
    margin-left: -230px;
    z-index: 9997;
    width: 402px;
    height: 603px;
    background: url("<%=basePath%>img/phone2.png") 0 0 no-repeat;
}


#liulan{
	height: 415px;
    width: 226px;
    top: 70px;
    left: 157px;
    display: block;
    position: relative;
    color: #020101;
    z-index: 9999;
    margin-top: 10px;
    background-color: #000;
}


.view_item {
    background-color: #f0eff4;
    border-bottom: 1px solid #dfdfe4;
    white-space: nowrap;
    cursor: pointer;
    min-width: 120px;
}

.view_list {
    line-height: 40px;
    border-radius: 10px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    text-align: center;
    overflow: hidden;
    position: absolute;
    right: 75%;
    top: 200px;
}

</style>

</head>

<body>

<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>错误日志管理</strong><font size="1">(双击可查看对应的错误信息)</font></li>
				</ol>
			</div>
			<br/>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						<div  id="ErrorMessage"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" id="Solve" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="解决"  aria-hidden="true"></i>
								解决
							</button>
							<button type="button" id="recipient" class="btn btn-outline btn-default">
								<i class="" title="接收者配置"  aria-hidden="true"></i>
								接收者配置
							</button>
							<button type="button" id="logFile" class="btn btn-outline btn-default">
								<i class="" title="日志下载"  aria-hidden="true"></i>
								日志下载
							</button>
							<button type="button" id="ChartView" class="btn btn-outline btn-default">
								<i class="fa fa-image" title="图形视图"  aria-hidden="true"></i>
								图形视图
							</button>
							<button type="button" id="ChartLineView" class="btn btn-outline btn-default">
								<i class="fa fa-image" title="折线视图"  aria-hidden="true"></i>
								折线视图
							</button>
						</div>
							<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
								data-mobile-responsive="true">
								<thead>
									<tr>
										<th data-radio="true" data-width="5%" data-field="state"></th>
										<!--  <th data-width="25%" data-field="notices.title">新闻标题</th>  -->
										<!-- <th  data-field="userInfo.area.Name">学校名字</th>  -->
										<th  data-field="name">报错位置</th>
										<th  data-field="className">报错异常</th>
									<!-- 	<th  data-field="message">报错信息</th>-->
										<th  data-field="createTime">时间</th>
										<th data-field="isStatus">状态</th>
									</tr>
								</thead>
							</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>
	
	<%--图表视图 --%>
			
		<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style=" left:-200px; width:  950px;height: 450px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">错误日志图形模式（饼图）</h4>
				</div>
				<div style="margin: 10px;">
				<div id="main" style="width: 450px;height:400px;  float:left">数据加载中。。。</div>
				<div id="main2" style="width: 450px;height:400px; float:right" >数据加载中。。。</div></div>
			
				
			</div>
		</div>
	</div>
	
	<%--ChartLineModal--%>
		<div class="modal" id="ChartLineModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style=" left:-119px; width: 800px;height: 450px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">错误日志图形模式（折线）</h4>
				</div>
				<div style="margin: 10px;">
				<!--<input type="text" id="STime" >
					<input type="text" id="ETime">
					<input type="button" id="refreshData" value="刷新">-->
					
					
					
               <form class="form-horizontal">
                 <fieldset>
                  <div class="control-group">
                    
                    <div class="controls " style="margin-top: -5px;">
                     <div class="input-prepend input-group" >
                   
                       <input type="text" style="width: 250px" name="reservation" id="reservationtime" class="form-control" value="08/01/2013 1:00 PM - 08/01/2013 1:30 PM"  class="span4"/>
                     </div>
                    </div>
                  </div>
                 </fieldset>
               </form>
					
					
					
					
					
					<div id="ChartLine" style="width:800px;height:375px;margin-top: -30px;"></div>
				</div>
			
				
			</div>
		</div>
	</div>
	
	
	<%--接受者配置 --%>
	<div class="modal" id="myModalRecipient" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 750px; left:-78px;" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">接收者配置管理</h4>
				</div>
				<div  class="modal-body">
				
						<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
						<div  id="ErrorMessageRecipient"
							role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbarRecipient"
							role="group">
								<button type="button" onclick="showModifySendInfo()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="新增"
									aria-hidden="true"></i> 新增
							</button>
							<button type="button" id="IsOpen" class="btn btn-outline btn-default">
								<i class="" title="开启or禁止"  aria-hidden="true"></i>
								开启or禁止
							</button>
							<button type="button" onclick="showModifySendInfo()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" title="修改"
									aria-hidden="true"></i> 修改
							</button>
							
							<button type="button" id="delectErrorSendList" class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"  aria-hidden="true"></i>
								删除
							</button>
						</div>
						
							<table id="exampleTableEventsRecipient" data-height="300"
								data-mobile-responsive="true">
								<thead>
									<tr>
									
										<th data-radio="true" data-width="5%" data-field="state"></th>
										<th  data-field="account">接收者</th>
										<th  data-field="typeMemo">开发人员</th>
										<th  data-field="message">备注</th>
										<th  data-field="count">预警条数</th>
										<th  data-field="isStatus">接收状态</th>
									
									</tr>
								</thead>
							</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	<%-- 接收者管理新增--%>
		<div class="modal" id="myModaladd" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="top: 50px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">编辑接收者</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div  id="ErrorMessageRecipientView" role="alert"></div>
						<div style="color: red;text-align:center">当前默认接收者状态为禁止，如需开启，请完成后点击“开启OR关闭”按钮</div>
						<input type="text" class="form-control" placeholder="请输入接收者的邮箱"id="Account" /> <br/>
						<input type="text" class="form-control" placeholder="请输入接收者的手机号码"id="Phone" /> <br/>
						<select class="form-control" 
							id="Type" name="Type">
							<option value=""></option>
							<option value="<%= SmBaseGlobal.DriveType.Android.getid() %>">安卓</option>
							<option value="<%= SmBaseGlobal.DriveType.IOS.getid() %>">苹果</option>
							<option value="<%= SmBaseGlobal.DriveType.Inteface.getid() %>">接口</option>
							<option value="<%= SmBaseGlobal.DriveType.Service.getid() %>">后台</option>
							<option value="<%= SmBaseGlobal.DriveType.Other.getid() %>">其他</option>
						</select><br/>
						<input type="text" class="form-control" placeholder="请输入预警条数"id="Count" /> <br/>
						<input type="text" class="form-control" placeholder="备注"id="Message"/><br/>
						<input type="hidden"   id="ID"/><br/>
					</div>
					<button id="addErrorSend" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>
	
		
	<div class="modal" id="myModalLogFile" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 750px; left:-78px;" >
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">日志下载</h4>
				</div>
				<div  class="modal-body">
				
						<div class="row">
			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="example">
					<div class="col-md-3">
					<label>设备</label>
					<select class="form-control m-b" onchange="queryLogFileList()" 
							id="DriveType" name="DriveType">
							<option></option>
							<option value="<%= SmBaseGlobal.ClientType.Android.getid() %>">安卓</option>
							<option value="<%= SmBaseGlobal.ClientType.IOS.getid() %>">苹果</option>
						</select><br/>
					</div>
					<div class="col-md-3">
					</div>
					<div class="col-md-3">
					</div>
						<div  id="ErrorMessageLogFile"
							role="alert"></div>
							<table id="exampleTableEventsLogFile" data-height="300"
								data-mobile-responsive="true">
								<thead>
									<tr>
									
										<th  data-field="model">手机型号</th>
										<th  data-field="system">手机系统</th>
										<th  data-field="version">app版本</th>
										<th  data-field="createTime">创建时间</th>
										<th  data-field="deviceTypeMemo">设备类型</th>
										<th  data-field="downUrl">日志下载</th>
									
									</tr>
								</thead>
							</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
				</div>
			</div>
		</div>
	</div>
	
	
			<div class="modal" id="message" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="top: 50px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">错误信息</h4>
				</div>
				<div class="modal-body">
					<textarea rows="10" cols="70" id="messageHtml" readonly="readonly">
					</textarea>
					<!--  <button id="addErrorSend" class="btn btn-warning">确定</button>-->
				</div>
			</div>
		</div>
	</div>
	       
	
<jsp:include page="/include/commonJs.jsp" />
<script type="text/javascript" src="<%=basePath%>js/echarts.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/moment.js?v=1"></script>
<script type="text/javascript" src="<%=basePath%>js/moment-with-locales.js"></script>
<script type="text/javascript" src="<%=basePath%>js/daterangepicker.js?v=1.1.1"></script>
		
<script type="text/javascript">
var myChart3 = echarts.init(document.getElementById('ChartLine')); 
				$(function() {
            		    $('#reservationtime').daterangepicker({
            		        timePicker: true,
            		        timePickerIncrement: 30,
            		        timePicker24Hour : true,
            		        locale: {
            		            format: 'MM/DD/YYYY H:mm'
            		        }
            		    })
            	
            	   $('#reservationtime').on('apply.daterangepicker', function(ev, picker) {
            		   console.log(picker.startDate.format('YYYY-MM-DD HH:mm:ss'));
            		   console.log(picker.endDate.format('YYYY-MM-DD HH:mm:ss'));
					   
					   
					    if(!myChart3){
          return;
     }
	 
     //更新数据
		$.ajax({
			url : "<%=basePath%>ErrorLog/getChatLine?sina=<%= SmBaseUtil.Random() %>",
	 		data : {
			'STime':picker.startDate.format('YYYY-MM-DD HH:mm:ss'),
			'ETime':picker.endDate.format('YYYY-MM-DD HH:mm:ss')
			},
			success : function(obj) {
				var option = myChart3.getOption();
				var data=obj.data;
				var dataCount=obj.dataCount;
				console.log(data);
				console.log(dataCount);
				var dataArr=data.split(",").map(function (str) {
                return str.replace(' ', '\n').replace(' ', '\n')
            });
				var dataCountArr=dataCount.split(",").map(function (str) {
                return str.replace(' ', '\n').replace(' ', '\n')
            });
				option.xAxis[0].data=dataArr;
				option.series[0].data = dataCountArr;
				option.yAxis[0].max=obj.max;
				myChart3.setOption(option);

			} 
			});
            		 });
					 });
               </script>

	<script type="text/javascript">
	var DataTable=$("#exampleTableEventsRecipient");
	var DataTableLogFile=$("#exampleTableEventsLogFile");
	!function(e, t, o) {
		"use strict";
		!
		function() {
			DataTable.bootstrapTable({
			
				url : "<%=path%>/ErrorLog/getErrorSendList?sina=<%= SmBaseUtil.Random() %>",
				search : 0, 
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				formatSearch:function(){return "查找异常"},
				sidePagination : "server", //服务端请求
				queryParams : queryParamsRecipient,
				responseHandler : responseHandler,
				striped : !0, //是否显示行间隔色
				clickToSelect:true,
				rowStyle:dispErrorRow,
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbarRecipient",
				icons : geticons
			});
			
			DataTableLogFile.bootstrapTable({
				url : "<%=path%>/ErrorLog/getErrorLogFileList?sina=<%= SmBaseUtil.Random() %>",
				search : 0, 
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				formatSearch:function(){return "查找异常"},
				sidePagination : "server", //服务端请求
				queryParams : queryParamsLogFile,
				responseHandler : responseHandler,
				striped : !0, //是否显示行间隔色
				clickToSelect:true,
				rowStyle:dispErrorRow,
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbarLogFile",
				icons : geticons
			});
			
			DataTable.on("dbl-click-row.bs.table",
							function(e, t, o) {
							var t=DataTable.bootstrapTable("getSelections")[0];
							
							}).on("search.bs.table", function(e, t, o) {
								getQueryListRecipient(t);
							})
		}()
	}(document, window, jQuery);
	function getQueryListRecipient(searchText){
		if(searchText==null || searchText==undefined){
			searchText="";
		}
		$("#exampleTableEventsRecipient").bootstrapTable("refresh", {
			
			url : "<%=path%>/ErrorLog/getErrorSendList?sina=<%= SmBaseUtil.Random() %>&searchText="+encodeURI(encodeURI(searchText)),
			
		});
	}
	function queryParamsRecipient(params) {
		return {
			pageSize : params.limit,
			pageNumber : params.offset
		};

	}
	function queryParamsLogFile(params) {
		var DriveType = $("#DriveType").val();
		return {
			pageSize : params.limit,
			pageNumber : params.offset,
			DriveType : DriveType
		};

	}
	
	$("#addErrorSend").click(function(){
		modifySendInfo();
		
	});
	function modifySendInfo(){
		$.ajax({
			url : "<%=path%>/ErrorLog/addErrorSendList?sina=<%= SmBaseUtil.Random() %>",
				data : {
				'ID':$("#ID").val(),	
				'Account':$("#Account").val(),
				'Message':encodeURI(encodeURI($("#Message").val())),
				'Count':$("#Count").val(),
				'Type':$("#Type").val(),
				'Phone':$("#Phone").val()
			},
			success : function(obj) {
				if(obj.Status=1){
					$("#ErrorMessageRecipient").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
					$("#myModaladd").modal("hide")
					getQueryListRecipient();
				}else{
					$("#ErrorMessageRecipientView").text('失败!').removeClass("alert-success").addClass("alert alert-warning");
				}
			} 
		});
	}
	function showModifySendInfo(){
		if($("#exampleTableEventsRecipient").bootstrapTable("getSelections").length>0)
		{
			var t=$("#exampleTableEventsRecipient").bootstrapTable("getSelections")[0];
			$("#Account").val(t['account']);
			$("#Phone").val(t['phone']);
			$("#Message").val(t['message']);
			$("#Count").val(t['count']);
			$("#Type").val(t['type']);
			$("#ID").val(t['pkid']);
		}else{
			$("#Account").val("");
			$("#Phone").val("");
			$("#Message").val("");
			$("#Count").val("");
			$("#Type").val("");
			$("#ID").val("0");
		}
		$("#myModaladd").modal("show");
	}
	
	
	
	$("#delectErrorSendList").click(function(){
		if(CheckhasChecked($("#exampleTableEventsRecipient"))==true)
		{
			var t=$("#exampleTableEventsRecipient").bootstrapTable("getSelections")[0];
			$.ajax({
				url : "<%=path%>/ErrorLog/delectErrorSendList?sina=<%= SmBaseUtil.Random() %>",
					data : {
					'ID':t['pkid']
				},
				success : function() {
					$("#ErrorMessageRecipient").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
					getQueryListRecipient();
				} 
			});
		}
	});
	
	$("#IsOpen").click(function(){
		if(CheckhasChecked($("#exampleTableEventsRecipient"))==true)
		{
			var t=$("#exampleTableEventsRecipient").bootstrapTable("getSelections")[0];
			var status=t['status'];
			
			if(status==0){
				status=1;
			}else{
				status=0;
			}
			$.ajax({
			url : "<%=path%>/ErrorLog/updateOpenErrorSendList?sina=<%= SmBaseUtil.Random() %>",
				data : {
				'ID':t['pkid'],
				'Status':status
			},
			success : function(obj) {
				if(obj.Status=1){
					$("#ErrorMessageRecipient").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
				}else{
					$("#ErrorMessageRecipient").text('操作失败!').removeClass("alert-warning").addClass("alert alert-success");
				}
				getQueryListRecipient();
			}});
		}
	})
	</script>
	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				$("#exampleTableEvents").bootstrapTable({
				
					url : "<%=path%>/ErrorLog/getErrorLogList?sina=<%= SmBaseUtil.Random() %>",
					search : 0, 
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "查找异常"},
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
				
					$("#exampleTableEvents").on("dbl-click-row.bs.table",
					function(e, t, o) {
					var t=$("#exampleTableEvents").bootstrapTable("getSelections")[0];
						if(CheckhasChecked($("#exampleTableEvents"))==true)
						{
		 					//swal({
							//		title:"报错信息",
							//		text:'<font size="10">'+t['message']+'</font>'
							//	}); 
							//alert(t["pkid"]);
							$("#message").modal("show");
							$("#messageHtml").html(t['message']);
						}
					}).on("search.bs.table", function(e, t, o) {
						getQueryList(t);
					})
			}()
		}(document, window, jQuery);
		
		$("#Solve").click(function(){
			if(CheckhasChecked($("#exampleTableEvents"))==true){
			    swal({
			        title: "您确定解决问题了吗？",
			        text: "您真的解决问题了吗？请谨慎操作！",
			        type: "warning",
			        showCancelButton: true,
			        cancelButtonText:"取消",
			        confirmButtonColor: "#DD6B55",
			        confirmButtonText: "解决",
			        closeOnConfirm: false
			    }, function () {
					var wids = getRecordPKIDs($("#exampleTableEvents"));
					$.ajax({
						url : "<%=path%>/ErrorLog/deleteErrorLog?sina=<%= SmBaseUtil.Random() %>",
	 					data : {
							'id' : wids
						},
						success : function(obj) {
							if(obj.Stauts=1){
								 swal("解决成功！", "您已经解决了所选问题", "success");
								 getQueryList();
							}else{
								swal("失败！", "请查看所选的内容", "warning");
							}
							
						} 
					});
			   
			    });
			}
		})
		$("#recipient").click(function(){
			$("#myModalRecipient").modal("show");
		})
		$("#logFile").click(function(){
			$("#myModalLogFile").modal("show");
			queryLogFileList();
		})
		function queryLogFileList(){
			var DriveType = $("#DriveType").val();
			$("#exampleTableEventsLogFile").bootstrapTable("refresh", {
				
				url : "<%=path%>/ErrorLog/getErrorLogFileList?DriveType="+ DriveType +"&sina=<%= SmBaseUtil.Random() %>",
				
			});
		}
	
		$("#ChartView").click(function(){
			$("#myModal").modal("show");
			$.ajax({
				url : "<%=path%>/ErrorLog/getErrorLogStatList",
				success : function(obj) {
					initialMyChat(obj);
					initialMyChat2(obj);
					
				}
			});
		})
		$("#ChartLineView").click(function(){
			
			$("#ChartLineModal").modal("show");
			$.ajax({
				url : "<%=basePath%>ErrorLog/getChatLine?sina=<%= SmBaseUtil.Random() %>",
	
				success : function(obj) {
					initialMyChat3(obj)
			

				} 
				});
		})
		
		function deleteStudentsLog() {
		if(CheckhasChecked($("#exampleTableEvents"))==true){
					var wids = getRecordPKIDs($("#exampleTableEvents"));
					$.ajax({
						url : "<%=path%>/Students/deleteStudentsLog?sina=<%= SmBaseUtil.Random() %>",
	 					data : {
							'logid' : wids
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						} 
					});
					}
		}
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			$("#exampleTableEvents").bootstrapTable("refresh", {
				
				url : "<%=path%>/ErrorLog/getErrorLogList?sina=<%= SmBaseUtil.Random() %>&searchText="+encodeURI(encodeURI(searchText)),
				
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		
		function initialMyChat(json){
			var myChart = echarts.init(document.getElementById('main'));
			
			var  option = {
					    title : {
					        text: '本月异常统计',
					        x:'right'
					       
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					       
					    },
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					        data: json.nameAll
					    },
					    series : [
					        {
					            name: '异常来源',
					            type: 'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:json.dataAll,
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
			 myChart.setOption(option);
			 
			
		}
		function initialMyChat2(json){
			 var myChart2 = echarts.init(document.getElementById('main2'));
				
				var  option2 = {
						    title : {
						        text: '今日已发生异常统计',
						        x:'right'
						       
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						       
						    },
						    legend: {
						        orient: 'vertical',
						        left: 'left',
						        data: json.nameDay
						    },
						    series : [
						        {
						            name: '异常来源',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:json.dataDay,
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						};
				 myChart2.setOption(option2);
		}
		
		
			
	function initialMyChat3(json){ 
		
		option3 = {
			 title : {
	        text: '异常报告关系图',
	        subtext: '数据来自杭州互办网络科技有限公司',
	        x: 'center',
	        align: 'right'
	    },
	    grid: {
	        bottom: 100
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            animation: true
	        }
	    },
	 
	    dataZoom: [
	        {
	            show: true,
	            realtime: true,
	            start: 65,
	            end: 85
	        },
	        {
	            type: 'inside',
	            realtime: true,
	            start: 65,
	            end: 85
	        }
	    ],
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            axisLine: {onZero: false},
	            data : json.data.split(',').map(function (str) {
	                return str.replace(' ', '\n').replace(' ', '\n')
	            })
	        }
	    ],
	    yAxis: [
	        {
	            name: '条数',
	            type: 'value',
	            max: json.max
	        }
	    ],
	    series: [
	        {
	            name:'流量',
	            type:'line',
	            animation: false,
	            areaStyle: {
	                normal: {}
	            }, 
	            lineStyle: {
	                normal: {
	                    width: 1
	                }
	            },
	            data:
	                json.dataCount.split(',')
	            
	        },
	
	    ]
	};
		 myChart3.setOption(option3);
}
	</script>
</body>
</html>