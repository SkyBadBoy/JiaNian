<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
%>
<html>

<head>


<jsp:include page="/include/commonCss.jsp" />
<style>

.modal-backdrop.in {
	display: none;
}

.image_picker_selector img {
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
<div class="modal" id="myModalSchool" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">绑定学校</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageStudent"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="SchoolListEventsToolbar"
							role="group">
							<button id="BindSchool"
						class="btn btn-warning">绑定该学校</button>
						</div>
					<table id="School" data-height="300"
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-radio="true"></th>
									
									<th data-field="code">学校编号</th>
									<th data-field="name">学校名字</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="myModalStudent" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titlens">绑定学生</h4>
				</div>
				<div class="modal-body">
				<div  id="ErrorMessageStudent"
							role="alert"></div>
				<div class="btn-group hidden-xs" id="StudentListEventsToolbar"
							role="group">
							<button id="BindStudent"
						class="btn btn-warning">绑定该学生</button>
						</div>
					<table id="Student" data-height="400"
						data-mobile-responsive="true">
						<thead>
							<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="pkid">用户编号</th>
								 	<th data-field="name">名字</th>
									<!--th  data-field="isSex">性别</th>
									<th data-field="age">出生年月</th>
									<th data-field="level">级别</th-->
									<th data-field="phone">手机</th>
									<th data-field="school">学校</th> 
									<!-- th data-field="baseInfo.name">状态</th--> 
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a
						href="<%=path%>/Video/VideoList?WeChatID=${WeChatID}"
						target="_self">视频列表</a></li>

					<li><strong>公众号视频编辑</strong></li>
				</ol>
			</div>
			<br />
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="ProductForm" modelAttribute="VideoForm"
					enctype="multipart/form-data"
					action="${path}/Video/addVideo?sina=<%= SmBaseUtil.Random() %>&" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" name="pageSize" id="pageSize"value="8" />
					<input type="hidden" name="pageNumber" id="pageNumber" value="0" />
					<input type="hidden" id="Content" name="Content" />
					<input type="hidden" id="Imageurl" name="Imageurl" />
					<form:input type="hidden" path="Type" id="Type" name="Type" />
					<form:input type="hidden" path="ImageID" id="ImageID" name="ImageID" />
					<input type="hidden" id="Path" value="<%=basePath%>" />
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>视频名称</label>
								<form:input type="text" class="form-control" path="Title"
									id="Title" data-bv-notempty-message="视频名称不能为空" name="Title"
									placeholder="请输入视频名称" />
								<form:errors path="Title"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group" id="videodiv">
								<label>视频网址(目前仅支持腾讯视频和新浪视频)</label>
								<input type="text"  class="form-control hide" name="VID" id="VID" />
								<form:input type="text" class="form-control" path="Url"
									onchange="setvideo($('#Url').val())" id="Url"
									data-bv-notempty-message="视频网址不能为空" name="Url"
									placeholder="请输入视频网址" />
								<form:errors path="Url" cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label>视频文件</label>
								<input type="file" accept="video/*"  name="file"   id="inputImage" >
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">

								<div class="row">
									<div class="col-md-1">
										<div id="Video" style="width: 600px;"></div>
									</div>

								</div>

							</div>

						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>关联用户</label>
								<p>*注：不输入用户或学校，则表示关联管理员</p>
								<!--  input type="hidden" id="userType" name="userType" -->
								<button type="button" onclick="Bind(1)"class="btn btn-outline btn-default">
									绑定学校
								</button>
								<button type="button" onclick="Bind(2)"class="btn btn-outline btn-default">
									绑定用户
								</button>
								<button type="button" onclick="Bind(0)" class="btn btn-outline btn-default">
									不绑定
								</button>
								<input  type="hidden"  value="${UserType}" id="UserType" name="UserType" class="form-control" />
								<form:input type="hidden" class="form-control" path="UserID" 
									id="UserID" name="UserID"
									placeholder="输入学生的编号或者学校的编号" />
								<input type="text" class="form-control" readonly="readonly"
									id="TempName" name="TempName"
									placeholder="请选择绑定类型" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>视频简介</label>
								<div class="ibox-content no-padding">
									<div class="summernote">${Content}</div>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" onclick="submitFrom()"
						class="btn btn-primary">保存</button>

				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>

	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	$("#UserType").val('${UserType}');
	$(function() {
		setvideo($("#Url").val(),1);
	        $("#ProductForm").validate({
				rules : {
					Title:{
						required : !0,
						maxlength : 100
					},
//					Url : {
//						required : !0,
//					},
//					VID  : {
//						required : !0,
//					}
				},
				messages : {
					Title : {
						required : "视频名称不能为空",
						maxlength : "视频名称不能超过100个字符"
					},
//					Url : {
//						required : "视频网址不能为空"
//					}
//					,
//					VID : {
//						required : "请输入正确的视频地址"
//					}
				}
			});

	});
	var SchoolDataTable=$("#School");
	var StudentDataTable=$("#Student");
		!function(e, t, o) {
			"use strict";
			!function() {
				SchoolDataTable.bootstrapTable({
					url : "<%=path%>/Region/getRegionLists?rand=<%= SmBaseUtil.Random() %>&"+MakeQueryParam($(".example-wrap")),
					search : !0,
					formatSearch:function(){return "请输入学校名字或编号"},
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					queryParams : queryParams,
					responseHandler: responseHandler,
					sidePagination : "server", //服务端请求
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					iconSize : "outline",
					toolbar : "#SchoolListEventsToolbar",
					icons : geticons
				});
				
				StudentDataTable.bootstrapTable({
					url : "<%=path%>/Students/getStudentsUserList?sina=<%= SmBaseUtil.Random() %>&isStu=1&"+MakeQueryParam($(".example-wrap")),
					search : !0,
					formatSearch:function(){return "请输入姓名或者手机号"},
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					clickToSelect:true,
					queryParams : queryParams,
					responseHandler: responseHandler,
					sidePagination : "server", //服务端请求
					striped : !0, //是否显示行间隔色
					rowStyle : dispErrorRow,
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
				});
			}()
			StudentDataTable.on("dbl-click-row.bs.table", function(e, t, o) {
			}).on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			});
			SchoolDataTable.on("dbl-click-row.bs.table", function(e, t, o) {
			}).on("search.bs.table", function(e, t, o) {
				getQuerySchoolList(t);
			});
		}(document, window, jQuery);
		
		

	
		function submitFrom() {
			$("#Content").val($(".note-editable").code());
		}
		$(document).ready(function() {
		    $(".summernote").summernote({
		        lang: "zh-CN",
		        height:300
		    })
		    getImageList();
		
		});
		
		function  setvideo(obj,type){
			tvp.$.getScript('<%=basePath%>js/tvp.player.setting.js?t='+ (+new Date()), function(){
				var vid=getVideoid(obj);
				if(vid!="" && vid!="1"&& vid!="2"){	
					var video = new tvp.VideoInfo();
					$("#VID").val(vid);
					video.setVid(vid);
					var player =new tvp.Player();
					player.addParam("showend",0);
					player.create({
						 width  : tvpSetting.width/2,
						  height : tvpSetting.height/2,
						  video  : video,
						  modId  : "Video",
						  playerType: 'html5',
						  autoplay:false
					});
					var videoimage=video.getVideoSnap();
					$("#Imageurl").val(videoimage[2]);
					if(type!=1){
						$("#ImageID").val(0);
					}
					$("#Type").val("0");
				}else if(vid=="1"){
					$("#Video").html('<video id="myMovie"  name="myMovie" src="'+ obj +'" width="100%" height="100%" controls="" preload="auto"></video>');
					$("#VID").val(obj);
					$("#Type").val("1");
				}else if(vid=="2"){
					$("#Video").html('<video id="myMovie"  name="myMovie" src="'+ obj +'" width="100%" height="100%" controls="" preload="auto"></video>');
					$("#VID").val(obj);
					$("#Type").val("1");
				}else{
					$("#Video").html('');
					$("#Type").val("0");
				}
			});
			
			
		}
		
		function getVideoid(obj){
			var vid="";
			if(obj.indexOf('sina.com')>0){//表示是新浪视频
				return 1;
			}else if(obj.indexOf('wenews.oss-cn')>0){//自己的视频
				return 2;
			}else{
				if(obj.indexOf("vid")!=-1){
					vid=obj.substr(obj.indexOf("vid")+4);
					if(vid.indexOf("&")!=-1){
						vid=vid.substr(0,vid.indexOf("&"));
					}
				}else if(obj.indexOf("html")!=-1){
					vid=obj.substr(obj.lastIndexOf("/")+1,obj.indexOf(".html")-obj.lastIndexOf("/")-1);
				}
			}
			return vid;
		}
		
		function Bind(id){
			if (id==0) {
				$("#UserID").val("0");
				$("#UserType").val("0");
			}else if(id==1){
				$("#myModalSchool").modal("show");
			}else if(id==2){
				$("#myModalStudent").modal("show");
			}
		}
		function getQueryList(searchText){
			var params=MakeQueryParam($(".panel"));
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			StudentDataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Students/getStudentsUserList?sina=<%= SmBaseUtil.Random() %>&isStu=1&check="+encodeURI(encodeURI(searchText))+"&&"+params,
						
			});
			
		}
		function getQuerySchoolList(searchText){
			var params=MakeQueryParam($(".panel"));
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			SchoolDataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Region/getRegionLists?sina=<%= SmBaseUtil.Random() %>&check="+encodeURI(encodeURI(searchText))+"&&"+params,
						
			});
			
		}
		function queryParams(params) {
			console.log( params.limit)
			console.log(params.offset)
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		
		$("#BindStudent").click(function(){
			if(CheckhasChecked(StudentDataTable)==true){
				var wids = getRecordPKIDs(StudentDataTable);
				$("#TempName").val(StudentDataTable.bootstrapTable("getSelections")[0].name);
				$("#UserID").val(wids);
				$("#UserType").val("2");
				$("#myModalStudent").modal("hide");
			}
		})
			$("#BindSchool").click(function(){
			if(CheckhasChecked(SchoolDataTable)==true){
				var wids = getRecordPKIDs(SchoolDataTable);
				$("#TempName").val(SchoolDataTable.bootstrapTable("getSelections")[0].name);
				$("#UserID").val(wids);
				$("#UserType").val("1");
				$("#myModalSchool").modal("hide");
			}
		})
	</script>
</body>
</html>