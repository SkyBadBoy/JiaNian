<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
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

<style type="text/css">
#loading {
			
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/wsload.gif) no-repeat center center black;
            background-size: 3%;
        }
.modal-backdrop.in {
	display: none;
}


</style>
<style type="text/css">
#loading {
    opacity: 0.8;
    position: fixed;
    top: 0;
    left: 0;
    display:none;
    width: 100%;
    height: 100%;
    z-index: 1055;
    background: url(../img/wsload.gif) no-repeat center center black;
    background-size: 3%;
}
.modal-backdrop.in {
	display: none;
}

.image_picker_selector img {
	width: 100px;
	height: 100px;
}
#Layer1 {
	width: 450px;
	margin: -120px;
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
	background-color: #000;
	position: absolute;
	z-index: 49;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.6;
	filter: alpha(opacity = 60);
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
	margin-top: 45px;
	margin-left: -230px;
	z-index: 9997;
	width: 402px;
	height: 603px;
	background: url('<%=basePath%>img/phone2.png') 0 0 no-repeat;
}

#liulan {
	height: 415px;
	width: 226px;
	top: 70px;
	left: 157px;
	display: block;
	position: relative;
	color: #020101;
	z-index: 9999;
	margin-top: 10px;
	background-color: #FFF;
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
	top: 250px;
}

#Layer2 {
	width: 450px;
	margin: -120px;
	z-index: 50;
	display: none;
	position: relative;
}

#updatebg {
	position: fixed;
	left: 45%;
	margin-top: 20%;
	margin-left: -230px;
	z-index: 9997;
	width: 650px;
	height: 450px;
	background: url('<%=basePath%>img/updatebg2.png') 0 0 no-repeat;
}

#Layer2  #win_top {
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

#Layer2  #win_top a {
	float: right;
	margin-right: 5px;
}

#shade2 {
	background-color: #000;
	position: absolute;
	z-index: 49;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.6;
	filter: alpha(opacity =   60);
	-moz-opacity: 0.6;
	margin: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
}

#Layer2  .content {
	margin-top: 5px;
	margin-right: 30px;
	margin-left: 30px;
}

#liulan2 {
	height: 500px;
	width: 1000px;
	top: 70px;
	left: 157px;
	display: block;
	position: relative;
	color: #020101;
	z-index: 9999;
	margin-top: 10px;
	background-color: #fff;
}

</style>
</head>
<body id="body">
<input type="hidden"  id="HeadUrl" name="HeadUrl" value="${HeadUrl}" />
<input type="hidden"  id="MiddleUrl" name="MiddleUrl"  value="${MiddleUrl}" />
<input type="hidden"  id="FootUrl" name="FootUrl"  value="${FootUrl}" />
<input type="hidden"  id="AreaID" name="AreaID"  value="${AreaID}" />
<div id="shade2"></div>
	<form id="PictureForm" action="<%=SmBaseGlobal.PictureService%>?uid=<%=user.getPKID() %>&backUrl=<%=basePath%>include/pictureback.html" target="i_frame" method="post" enctype="multipart/form-data">
		<div id="Layer2">
			<div id="updatebg">
				<div class="row" style="margin: 20px">
					<div class="col-lg-6">
						<input type="hidden" path="ID" id="ID" name="ID" /> <input
							type="hidden" name="Img_X" id="Img_X" /> <input type="hidden"
							name="Img_Y" id="Img_Y" /> <input type="hidden" name="Img_W"
							id="Img_W" /> <input type="hidden" name="Img_H" id="Img_H" /> <input
							type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
						<input type="hidden" name="Img_SrcW" id="Img_SrcW" />
						<input type="hidden" name="Img_SrcH" id="Img_SrcH" />
						<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
						<input type="hidden" name="uploadType" id="uploadType" value="1"/>
						
						
						<div class="form-group" style="height: 240px; width: 610px;">
							<label>请选择图片</label> <input type="file" accept="image/*"
								name="file" data-bv-notempty-message="图片不能为空"
								value="${inputImage}" id="inputImage" class="hide">
							<div class="row">
								<div class="col-md-6">
									<div class="image-crop">
										<img id="ImageSrc" style="height: :100%; width: 100%"
											src="${inputImage}">
									</div>
								</div>
								<div class="col-md-6">
									<h4>图片预览：</h4>
									<div id="tupian" class="img-preview img-preview-sm"></div>
									<h4>说明：</h4>
									<p>你可以选择新图片上传</p>
									<div class="btn-group">
										<label title="上传图片" for="inputImage" onclick="up()"
											class="btn btn-primary"> 上传新图片 </label>
									</div>
									<div class="btn-group">
										<button class="btn btn-white" id="zoomIn" type="button">放大</button>
										<button class="btn btn-white" id="zoomOut" type="button">缩小</button>
									</div>
								</div>

							</div>
							<div style="margin-top: 10px;">
								<button type="button" onclick="InsertPicture()"
									class="btn btn-primary">保存</button>
								<button type="button" onclick="closeHtml2()"
									class="btn btn-white">关闭</button>
								<a id="up" style="color: red; display: none;">上传成功</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		</form>
		<div id="ErrorMessage" role="alert"></div>
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a
						href="<%=path%>/Vote/VoteList"
						target="_self">投票列表</a></li>
					<li><strong>投票新增/编辑</strong></li>
				</ol>
			</div>
			<br />
		</div>
<div class="modal" id="myModal" style="overflow: auto;margin-top: 130px;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title" id="titles">新增规则</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<label style="display: none;" id="pkid"></label>
						<input type="text" class="form-control" placeholder="请输入规则关键字" id="kwyword"  /><br>
					</div>
					<br />
					<button id="addKeyWord" class="btn btn-warning">确定增加</button>
				</div>
			</div>
		</div>
	</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				
					
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>投票标题</label>
								<input type="text" class="form-control" path="Title" value="${Title }"
									id="Title" data-bv-notempty-message="投票标题不能为空" name="Title"
									placeholder="请输入投票标题" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>开始时间</label> <input type="date" class="form-control"
									value="${StartTime}" data-bv-notempty-message="开始时间不能为空"
									id="StartTime" name="ActivitysTime"
									placeholder="请输入开始时间" />
							
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>过期时间</label> <input type="date" class="form-control"
									value="${EndTime}" data-bv-notempty-message="过期时间不能为空"
									id="EndTime" name="ActivityEndTime"
									placeholder="请输入过期时间" />
								
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
							<label>投票标题规则</label>
								<div class="row">
									<div class="col-sm-12">
										<!-- Example Events -->
										<div class="example-wrap">
											<div class="example">
								
											
												<div class="btn-group hidden-xs"
													id="exampleTableEventsToolbar" role="group">
													<button type="button"
													class="btn btn-outline btn-default"
														onclick="kerWordShow()">
														<i class="glyphicon glyphicon-plus" title="新增关键字规则"
															aria-hidden="true"></i> 新增规则
													</button>

													<button type="button" onclick="deleteVote()"
														class="btn btn-outline btn-default">
														<i class="glyphicon glyphicon-trash" title="删除"
															aria-hidden="true"></i> 删除
													</button>
												</div>
												<table id="exampleTableEvents" data-height="250"
													data-mobile-responsive="true">
													<thead>
														<tr>
															<th data-field="state" data-checkbox="true"></th>
															<th data-width="100" data-field="pkid">规则编号</th>
															<th id="picture" data-field="keyWord">规则关键字</th>

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
					<div class="row">
							<div class="col-sm-8">
								<div class="form-group">
									<label>请选择头部活动图片</label>
									<form:errors path="QRCodeURL"
										Class="help-block m-b-none errorMsg"></form:errors>
									<div class="row">
										<div class="col-md-6">
											<div class="image-crop" >
												<img style="height: :100%;width: 100%" src="${HeadUrl}" id="HeadURLImg" name="HeadURLImg">
											</div>
										</div>
										<div class="col-md-6">
											<p>你可以选择新图片上传</p>
											<div class="btn-group">
												<label title="上传图片" onclick="openAddHtml(1)"
													class="btn btn-primary"> 
													上传新图片
												</label> 
											</div>
										</div>
										
									</div>
									<br />
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>请选择中部活动图片</label>
									<form:errors path="Banner" Class="help-block m-b-none errorMsg"></form:errors>
									<div class="row">
										<div class="col-md-6">
											<div class="image-crop">
												<img style="height: :100%;width: 100%" src="${MiddleUrl}" id="MiddleUrlImg" name="MiddleUrlImg">										
											</div>
										</div>
										<div class="col-md-6">
											<p>你可以选择新图片上传</p>
											<div class="btn-group">
												<label title="上传图片" onclick="openAddHtml(2)"
													class="btn btn-primary"> 
													上传新图片
												</label> 
											</div>
										</div>
										</div>
										<br />
									</div>
									
								</div>
		



							<div class="col-sm-8">
								<div class="form-group">
									<label>请选择底部活动图片</label>
									<form:errors path="Logo" Class="help-block m-b-none errorMsg"></form:errors>
									<div class="row">
										<div class="col-md-6">
											<div class="image-crop">
												<img style="height: :100%;width: 100%"  src="${FootUrl}"  id="FootUrlImg" name="FootUrlImg">
											</div>
										</div>
										<div class="col-md-6">
					
											<p>你可以选择新图片上传</p>
											<div class="btn-group">
												<label title="上传图片" onclick="openAddHtml(3)"
													class="btn btn-primary"> 
													上传新图片
												</label> 
											</div>
							
										</div>
									</div>
									<br />
								</div>
							</div>
						</div>
			</div>
			<div class="col-lg-9">
				<button  onclick="submitFrom()" class="btn btn-primary">保存</button>

			</div>
		
		</div>
	</div>
	<!-- /.row -->

	</div>

	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	
	$("#HeadUrl").val();
	$("#MiddleUrl").val();
	$("#FootUrl").val();
		function submitFrom() {
			 $.ajax({
					url : "<%=path%>/Vote/addVote",
					type:'POST',
					data : {
						'title' : encodeURI($("#Title").val()),
						'VoteID':"${VoteID}",
						'EndTime':$("#EndTime").val(),
						'StartTime':$("#StartTime").val(),
						'IsNew':'${IsNew}',
						'HeadUrl':$("#HeadUrl").val(),
						'MiddleUrl':$("#MiddleUrl").val(),
						'FootUrl':$("#FootUrl").val()
					},
					success : function(obj) {
						var view=$("#ErrorMessage");
						if (obj.status) {
								view.text(obj.Message).removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
								window.location.href="<%=basePath%>Vote/VoteList";
						}else{
								view.text(obj.Message).removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
						}
						
					}
				});
			
		}


		function kerWordShow(){
			$("#myModal").modal("show");
		}
		
		$("#addKeyWord").click(function(){
			 $.ajax({
					url : "<%=path%>/Vote/addKeyWord",
					data : {
						'title' : encodeURI($("#kwyword").val()),
						'VoteID':"${VoteID}"
					},
					success : function(obj) {
						$("#myModal").modal("hide");
						var view=$("#ErrorMessage");
						if (obj.status) {
								view.text(obj.Message).removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
								getQueryList();
						}else{
								view.text(obj.Message).removeClass("alert-warning")
								.addClass("alert alert-success");
								view.show();
								gotohidden(view);
						}
						
					}
				});
		})
		
		
	</script>
	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/Vote/getKeyWordList?sina=<%= SmBaseUtil.Random() %>&VoteID=${VoteID}",
					/*search : !0,*/
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
			DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList(t);
				})
		}(document, window, jQuery);

	
		
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			$("#searchText").val(searchText);
			var params=MakeQueryParam($(".example-wrap"));
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/Vote/getKeyWordList?sina=<%= SmBaseUtil.Random() %>&VoteID=${VoteID}"
			});
			
		}
		function deleteVote() {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "${path}/Vote/deleteKeyWord",
						data : {
							'pid' : wids
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				} 
			}
		}

		var r = $("#inputImage");
		var o = $("#PictureForm .image-crop > img");
		$(o).cropper({
			
			autoCropArea: 1,
		    mouseWheelZoom: false,
			preview : ".img-preview",
			done : function() {
			}
		});
		 (window.FileReader) ? (r.change(function() {
	           var e, i = new FileReader,
	           t = this.files;
	           t.length && (e = t[0], /^image\/\w+$/.test(e.type) ? (i.readAsDataURL(e), i.onload = function() {
	               o.cropper("reset", !0).cropper("replace", this.result);
	           }) : showMessage("请选择图片文件"));
	       })) : r.addClass("hide");
	      
	       $("#zoomIn").click(function() {
	           o.cropper("zoom", .1);
	       });
	       $("#zoomOut").click(function() {
	           o.cropper("zoom", -.1);
	       });
	       
	       function InsertPicture() 
	       {
	       	$("#loading").css("display","block");
	       	var i = new Image(); 
	       	i.src = $("#ImageSrc").attr("src"); 
	       	var rw = i.width; 
	       	var rh = i.height; 
	       	$("#Img_X").val(o.cropper("getData").x-30);
	       	$("#Img_Y").val(o.cropper("getData").y-30);
	       	$("#Img_W").val(o.cropper("getData").width+60);
	       	$("#Img_H").val(o.cropper("getData").height+60);
	       	$("#Img_SrcW").val(rw);
	       	$("#Img_SrcH").val(rh);
	       	
	       	setTimeout(function(){
	       		var i_div = document.getElementById("i_frame_div");
	       		  i_div.innerHTML="<IFrame id=\"i_frame\"  name=\"i_frame\" width=\"1px\" height=\"1px\" style=\"display:none\" src=\"about:blank\"></IFrame>";
	       		  $("#PictureForm").submit();
	       	},100); 
	       		
	       }
	       function getIframeVal(val)  
	        {  
	       	 $("#loading").css("display","none");
	       	 var uploadType = $("#uploadType").val();
	       	 var json=eval("("+decodeURIComponent(val[0])+")");
	       	 if(json.Status>0){
	       		 var imageUrl=json.ImageURL.image_800;
	       		 if(uploadType==1){
	       			 $("#HeadUrl").val(imageUrl);
	       			 $("#HeadURLImg").attr("src",imageUrl);
	       		 }else if(uploadType==2){
	       			 $("#MiddleUrl").val(imageUrl);
	       			 $("#MiddleUrlImg").attr("src",imageUrl);
	       		 }else if(uploadType==3){
	       			 $("#FootUrl").val(imageUrl);
	       			 $("#FootUrlImg").attr("src",imageUrl);
	       		 }
	       	 }else{
	       		 alert(json.ErrorMsg);
	       	 }
	       	 $("#up").css("display","block");
	       	 closeHtml2();
	        }
	       function closeHtml2()
	       {
	       	
	       	document.getElementById("body").style.overflow="auto";
	       		shade2.style.display='none';
	       	Layer2.style.display='none';
	       	document.getElementById("up").style.display="none";
	       }


	       function openAddHtml(type)
	       {
	       	returnTop();
	       	document.getElementById("body").style.overflow="hidden";
	       	shade2.style.display='block';
	       	Layer2.style.display='block';
	       	$("#uploadType").val(type);
	       }
	       function returnTop() {
	   		window.scrollBy(0,-1000);
	   		if(document.body.scrollTop>0) {
	   			sdelay= setTimeout('returnTop()',0);
	   	 }
	   }
	       function up()
	       {
	       	document.getElementById("up").style.display="none";
	       }
	</script>
	
	<div id="blueimp-gallery" class="blueimp-gallery">
         <div class="slides"></div>
         <h3 class="title"></h3>
         <a class="prev">‹</a>
         <a class="next">›</a>
         <a class="close">×</a>
         <a class="play-pause"></a>
         <ol class="indicator"></ol>
     </div>
	<div id="loading"></div>
	<div id="i_frame_div"></div>
	
</body>

</html>