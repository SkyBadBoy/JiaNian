<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath =SmBaseUtil.getProjectBaseUrl(request);
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
	margin-top: 6%;
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
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a
						href="<%=path%>/Activity/ActivityList?WeChatID=${WeChatID}"
						target="_self">活动列表</a></li>
					<li><strong>公众号活动编辑</strong></li>
				</ol>
			</div>
			<br />
		</div>
		<div id="shade"></div>
		<div id="Layer1">
			<div id="iphone">
				<div id="liulan"></div>
				<div>
					<ul class="view_list list-unstyled">
						<!--                        		<li class="view_item" onclick="">通&nbsp;&nbsp;&nbsp;&nbsp;过</li>
                       		<li class="view_item" onclick="">不通过</li> -->
						<li class="view_item" onclick="closeHtml()">关&nbsp;&nbsp;&nbsp;&nbsp;闭</li>
					</ul>
				</div>
			</div>
		</div>
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
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="ProductForm" modelAttribute="ActivityForm"
					enctype="multipart/form-data" action="${path}/Activity/addActivity"
					method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" id="Content" name="Content" />
					<form:input type="hidden" path="Status" id="Status" name="Status" />
					<input type="hidden" id="WeChatID" name="WeChatID"
						value="${WeChatID}" />
					<input type="hidden" name="Img_X" id="Img_X" />
					<input type="hidden" name="Img_Y" id="Img_Y" />
					<input type="hidden" name="Img_W" id="Img_W" />
					<input type="hidden" name="Img_H" id="Img_H" />
					<input type="hidden" name="pageSize" id="pageSize" value="8" />
					<input type="hidden" name="pageNumber" id="pageNumber" value="0" />
					<input type="hidden" id="Path" value="<%=basePath%>" />
					<input type="hidden" id="aid" name="aid" value="${aid}" />
					<input type="hidden" name="Images" id="Images" />
					<input type="hidden" name="DelImages" id="DelImages" />
					<input type="hidden" name="isNew" id="isNew" value="${isNew}" />
					<input type="hidden" name="ImageID" id="ImageID" value="${ImageID}" />
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>活动名称</label>
								<form:input type="text" class="form-control" path="Title"
									id="Title" data-bv-notempty-message="活动名称不能为空" name="Title"
									placeholder="请输入活动名称" />
								<form:errors path="Title"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>过期时间</label> <input type="date" class="form-control"
									value="${ActivityEndTime}" data-bv-notempty-message="过期时间不能为空"
									id="ActivityEndTime" name="ActivityEndTime"
									placeholder="请输入过期时间" />
								<form:errors path="EndTime"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>限制人数(0表示不限制)</label> <input class="form-control" 
									value="${ActivityApplyLimit}" 
									id="ActivityApplyLimit" name="ActivityApplyLimit"placeholder="请输入报名人数"
									 />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<label>设置支付方式</label>
							：<select class="form-control m-b"  iscon="true" id="PayType" name="PayType" >
											<option value="14">免费</option>
											<option value="15">人民币支付</option>
											<option value="16">微米支付</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label>报名金额(0表示免费)</label> <input  class="form-control"
									value="${ActivityApplyMoney}" data-bv-notempty-message="请输入报名金额"
									id="ActivityApplyMoney" name="ActivityApplyMoney"
									placeholder="请输入报名金额" />
								<form:errors path="ApplyMoney"
									cssClass="help-block m-b-none errorMsg"></form:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<div class="row">
									<div class="col-sm-12">
										<!-- Example Events -->
										<div class="example-wrap">
											<div class="example">
								<form:errors path="Image"
									cssClass="help-block m-b-none errorMsg"></form:errors>
												<input type="hidden" id="NIID" name="NIID" iscon="true"
													value="${id}" /> <input type="hidden" id="WeChatID"
													name="WeChatID" iscon="true" value="${WeChatID}" /> <input
													type="hidden" id="basePath" name="basePath"
													value="<%=basePath%>" />
												<div id="ErrorMessage" role="alert"></div>
												<div class="btn-group hidden-xs"
													id="exampleTableEventsToolbar" role="group">
													<button type="button"
														<%-- onclick="javascript:window.location.href='<%=path%>/ProdPicture/addProdPicture?stauts=1&ProdID=${id}'"  --%>
													class="btn btn-outline btn-default"
														onclick="openAddHtml()">
														<i class="glyphicon glyphicon-plus" title="增加图片"
															aria-hidden="true"></i> 增加
													</button>

													<button type="button" onclick="deleteWeChat()"
														class="btn btn-outline btn-default">
														<i class="glyphicon glyphicon-trash" title="删除图片"
															aria-hidden="true"></i> 删除
													</button>
												</div>
												<table id="exampleTableEvents" data-height="250"
													data-mobile-responsive="true">
													<thead>
														<tr>
															<th data-field="state" data-checkbox="true"></th>
															<th data-width="100" data-field="tempUrl">图片文件</th>
															<th id="picture" data-field="realUrl">图片</th>

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
			<div class="col-lg-9">
				<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>活动简介</label>
							<div class="ibox-content no-padding">
								<div class="summernote">${Content}</div>
							</div>
						</div>
					</div>
				</div>
				<button type="submit" onclick="submitFrom()" class="btn btn-primary">保存</button>

				<button type="button" onclick="openHtml()" class="btn btn-primary">预览</button>
			</div>
			</form:form>
		</div>
	</div>
	<!-- /.row -->

	</div>
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	$("#PayType").val(${PayType});
	var o = $(".image-crop > img");
		$(function() {
				$(o).cropper({
					aspectRatio : 16 / 9,
					autoCropArea: 1,
				    mouseWheelZoom: false,
					preview : ".img-preview",
					done : function() {
					}
				});
				var r = $("#inputImage");
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
		        $("#ProductForm").validate({
					rules : {
						Title:{
							required : !0,
							maxlength : 50
						},
						ActivityEndTime : {
							required : !0,
						}
					},
					messages : {
						Title : {
							required : "活动名称不能为空",
							maxlength : "活动名称不能超过50个字符"
						},
						ActivityEndTime : {
							required : "结束日期不能为空"
						}
					}
				});

		});
	
	
	

		function submitFrom() {
			if($("#ProductForm").valid()){
				$("#loading").css("display","block");
				$("#Content").val($(".note-editable").code());
			}else{
				return false;
			}
			
		}
		$(document).ready(function() {
		    $(".summernote").summernote({
		        lang: "zh-CN",
		        height:300
		    })
		    getImageList();
		    $("#selectFromFiles").removeAttr("style");//显示本地上传的功能按钮
		});
		
		function openHtml()
		{
			returnTop();
	 	  	if($("#isNew").val()=="")
		  	{
	 			 
				document.getElementById("body").style.overflow="hidden"; 
		 		shade.style.display='block';
				Layer1.style.display='block'; 
				
				var liulan="<iframe id='frame' width='100%' height='100%' src='<%=basePath%>Activity/phoneSinaActivityDetail?aid="+$("#aid").val()+"&status=1'></iframe>"; 
				$("#liulan").append(liulan);  
 			}else
			{ 
				
				alert("新增活动，首次需保存才可以预览！");
			} 

		
		}
		function closeHtml()
		{
			document.getElementById("body").style.overflow="auto";
 			shade.style.display='none';
			Layer1.style.display='none';
			$("#liulan").html("");
		}
		
		function closeHtml2()
		{
			
			document.getElementById("body").style.overflow="auto";
 			shade2.style.display='none';
			Layer2.style.display='none';
			document.getElementById("up").style.display="none";
		}

		var sdelay=0;
		
		function returnTop() {
 			window.scrollBy(0,-1000);
 			if(document.body.scrollTop>0) {
  				sdelay= setTimeout('returnTop()',0);
			 }
		}
		
		
		function openAddHtml()
		{
			returnTop();
			document.getElementById("body").style.overflow="hidden";
			shade2.style.display='block';
			Layer2.style.display='block';
			$("#uploadType").val(1);
		}
		
		function openUploadImageHtml()
		{
			$(".note-image-dialog  .close").click();
			returnTop();
			document.getElementById("body").style.overflow="hidden";
			shade2.style.display='block';
			Layer2.style.display='block';
			$("#uploadType").val(0);
			
		}
		
		
	</script>
	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
					url : "<%=path%>/ProdPicture/getProdPictureByPPIDList?sina=<%= SmBaseUtil.Random() %>&ppid="+$("#aid").val(),
					/*search : !0,*/
					formatSearch:function(){return "请输入微信公众号名称"},
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

		
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					 var selects = DataTable.bootstrapTable('getSelections');  
				        SKUNo = $.map(selects, function (row) {  
				            return row.tempUrl;  
				        });  
				        DataTable.bootstrapTable('remove', {  
				            field: 'tempUrl',  
				            values: SKUNo  
				        });  
				        addtoDeleteImageiDs(wids);
						
				}
			}
		}
		
		function getQueryList(searchText){
			if(searchText==null || searchText==undefined){
				searchText="";
			}
			$("#searchText").val(searchText);
			var params=MakeQueryParam($(".example-wrap"));
			DataTable.bootstrapTable("refresh", {
				url : "<%=path%>/ProdPicture/getProdPictureByPPIDList?sina=<%= SmBaseUtil.Random() %>&ppid="+$("#aid").val()
			});
			
		}
		
		function InsertPicture() 
		{
			$("#loading").css("display","block");
			var i = new Image(); 
			i.src = $("#ImageSrc").attr("src"); 
			var rw = i.width; 
			var rh = i.height; 
			$("#Img_X").val(o.cropper("getData").x);
			$("#Img_Y").val(o.cropper("getData").y);
			$("#Img_W").val(o.cropper("getData").width);
			$("#Img_H").val(o.cropper("getData").height);
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
			 if($("#uploadType").val()==1){
				 var json=eval("("+decodeURIComponent(val[0])+")");
				 if(json.Status>0){
					 addImageiDs(json.Status);
					 var rowObj = new Object();
					var html="<a href='"+ json.ImageURL.image_800 +"' title='图片' data-gallery=''><img alt='image' style='width:70px;height:70px;' onerror='nofind()' src='"+ json.ImageURL.image_130 +"'></a>"
					rowObj.pkid = json.Status;
					rowObj.tempUrl = html;
					rowObj.realUrl = $("#inputImage").val();
					DataTable.bootstrapTable("append",
							rowObj);
					$("#up").css("display","block");

				 }else{
					 alert(json.ErrorMsg);
				 } 
			 }else{
				 $("* [data-event='showImageDialog'] ").click();
				 getImageList();
			 }
			 closeHtml2()
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