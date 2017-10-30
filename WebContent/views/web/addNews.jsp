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

<style>
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
	
	
	
	#Layer1  #win_top {
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
	    background: url("<%=basePath%>img/updatebg2.png") 0 0 no-repeat;
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
		filter: alpha(opacity = 60);
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
	
	#Layer1  #win_top a {
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
	
	
	#Layer1  .content {
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
		background: url("<%=basePath%>img/phone2.png") 0 0 no-repeat;
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
		top: 250px;
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
					<li><a href="<%=path%>/Notices/NewsList" target="_self">新闻列表</a></li>
					<li><strong>新闻编辑</strong></li>
				</ol>
			</div>
			<br />
		</div>
		<%-- 	<jsp:include page="/include/head.jsp" /> --%>
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
	<!-- .start -->
	<form id="PictureForm" action="<%=SmBaseGlobal.PictureService%>" target="i_frame" method="post" enctype="multipart/form-data">
	
	<div id="shade2"></div>
	<div id="Layer2">
		<div id="updatebg">
			<div class="row" style="margin: 20px">
			 	<div class="col-lg-6" >
	 					<input type="hidden" path="ID" id="ID" name="ID" />
						<input type="hidden" name="Img_X" id="Img_X" />
						<input type="hidden" name="Img_Y" id="Img_Y" />
						<input type="hidden" name="Img_W" id="Img_W" />
						<input type="hidden" name="Img_H" id="Img_H" />
						<input type="hidden" name="Img_SrcW" id="Img_SrcW" />
						<input type="hidden" name="Img_SrcH" id="Img_SrcH" />
						<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
						<input type="hidden" name="uid" id="uid" value="<%=user.getPKID() %>" />
						<input type="hidden" name="backUrl" id="backUrl" value="<%=basePath%>include/pictureback.html" />
						
						
 						<div class="form-group" style="height: 350px;width:610px;">
							<label>请选择图片</label> 
							<input  type="file" accept="image/*"  name="file"  data-bv-notempty-message="图片不能为空" value="${inputImage}" id="inputImage" class="hide">
							<div class="row">
								<div class="col-md-6" >
									<div class="image-crop">
										<img id="ImageSrc" style="height: :100%;width: 100%" src="${inputImage}">
									</div>
								</div>
								<div class="col-md-6"  >
									<h4>图片预览：</h4>
									<div id="tupian" class="img-preview img-preview-sm"></div>
									<h4>说明：</h4>
									<p>你可以选择新图片上传</p>
									<div class="btn-group">
										<label title="上传图片" for="inputImage" onclick="up()" class="btn btn-primary">
											上传新图片
										</label>
									</div>
									<div class="btn-group">
										<button class="btn btn-white" id="zoomIn" type="button">放大</button>
										<button class="btn btn-white" id="zoomOut" type="button">缩小</button>
									</div>
								</div>
								
							</div>
							<div style="margin-top: 10px;">
								<button type="button" onclick="InsertPicture()" class="btn btn-primary">保存</button>
								<button type="button" onclick="closeHtml2()" class="btn btn-white">关闭</button>
								<a id="up" style="color: red;display: none;">上传成功</a>
							</div>
						</div>
						
				</div>
			</div>
 		</div> 
	</div>
	</form>
	<!-- .end -->
	</div>
	<!-- /.row -->
	<div class="col-lg-12">
	<div class="row">
		<div class="col-lg-9">
		
			<form:form id="NoticesForm" modelAttribute="NoticesForm"
				enctype="multipart/form-data" action="${path}/Notices/addNotices"
				method="post">
				<form:input type="hidden" path="ID" id="ID" name="ID" />
				<form:input type="hidden" path="ImageID" id="ImageID" name="ImageID" />
				<input type="hidden" id="Content" name="Content" />
				<form:input type="hidden" path="Status" id="Status" name="Status" />
				<input type="hidden" id="Type" name="Type" value="2" />
				<input type="hidden" id="Path" value="<%=basePath%>" />
				<input type="hidden" id="basePath" value="<%=basePath%>" />
				<input type="hidden" name="pageSize" id="pageSize" value="8" />
				<input type="hidden" name="pageNumber" id="pageNumber" value="0" />
				<input type="hidden" name="Img_X" id="Img_X" />
				<input type="hidden" name="Img_Y" id="Img_Y" />
				<input type="hidden" name="Img_W" id="Img_W" />
				<input type="hidden" name="Img_H" id="Img_H" />
				<input type="hidden" name="PKID" id="Pid" value="${Pid}" />
				<input type="hidden" name="AreaID" id="AreaID" value="${AreaID}" />
				<input type="hidden" name="Images" id="Images" />
				<input type="hidden" name="DelImages" id="DelImages" />
				
				<c:if test="${not empty isNew}">
					<input type="hidden" name="isNew" id="isNew" value="${isNew}" />
				</c:if>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>新闻标题</label>
							<form:input type="text" class="form-control" path="Title"
								id="Title" data-bv-notempty-message="新闻标题不能为空" name="Title"
								placeholder="请输入新闻标题" />
							<form:errors path="Title" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>作者</label>
							<form:input type="text" class="form-control" path="Author"
								id="Author" name="Author" placeholder="请输入作者" />
							<form:errors path="Author"
								cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>请选择新闻图片</label>
							<form:errors path="Image" cssClass="help-block m-b-none errorMsg"></form:errors>

							<div class="row">
								<div class="col-sm-6">
									<!-- Example Events -->
									<div class="example-wrap">
										<div class="example">

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
													class="btn btn-outline btn-default" onclick="openAddHtml()"
													>
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
<!-- 							<div id="blueimp-gallery" class="blueimp-gallery">
								<div class="slides"></div>
								<h3 class="title"></h3>
								<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a>
								<a class="play-pause"></a>
								<ol class="indicator"></ol>
							</div> -->

						</div>
					</div>
				</div>
		</div>
	<div class="col-lg-12">	
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<label>新闻内容</label>
					<form:errors path="Content" cssClass="help-block m-b-none errorMsg"></form:errors>
					<div class="ibox-content no-padding">
						<div class="summernote" style="height:500px;">${Content}</div>
					</div>
				</div>
			</div>
		</div>
	
		<button type="submit" onclick="return submitFrom()" class="btn btn-primary">发布</button>
		<button type="button" onclick="openHtml()" class="btn btn-primary">预览</button>
		</form:form>
</div>
	</div>
	<!-- /.row -->
</div>
	<div id="blueimp-gallery" class="blueimp-gallery">
         <div class="slides"></div>
         <h3 class="title"></h3>
         <a class="prev">‹</a>
         <a class="next">›</a>
         <a class="close">×</a>
         <a class="play-pause"></a>
         <ol class="indicator"></ol>
     </div>
     <jsp:include page="/include/commonJs.jsp" />
	
	<script type="text/javascript">
	
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
		                $("#ImageID").val(0);
		            	o.cropper("reset", !0).cropper("replace", this.result);
		            }) : showMessage("请选择图片文件"));
		        })) : r.addClass("hide");
		       
		        $("#zoomIn").click(function() {
		            o.cropper("zoom", .1);
		        });
		        $("#zoomOut").click(function() {
		            o.cropper("zoom", -.1);
		        });
			
			
		        $("#NoticesForm").validate({
					rules : {
						Title:{
							required : !0,
							maxlength : 100
						}
					},
					messages : {
						Title : {
							required : "新闻名称不能为空",
							maxlength : "新闻名称不能超过100个字符"
						}
					}
				});

		});
	
		function up()
		{
			document.getElementById("up").style.display="none";
		}
		function submitFrom() {
			if($("#NoticesForm").valid()){
				$("#loading").css("display","block");
				$("#Content").val($(".note-editable").code());
				return true;
			}
			return false;
			
		}
		$(document).ready(function() {
		    $(".summernote").summernote({
		        lang: "zh-CN",
		        height:300
		    })
		    getImageList();
		
		});
		

		function openAddHtml()
		{
			returnTop();
			document.getElementById("body").style.overflow="hidden";
			shade2.style.display='block';
			Layer2.style.display='block';
		}
		
		function openHtml()
		{
			if($("#ID").val()=="" || $("#ID").val()=="0"){
				alert("请先进行保存,在预览");
				return false;
			}
			returnTop();
			document.getElementById("body").style.overflow="hidden";
	 		shade.style.display='block';
			Layer1.style.display='block'; 
			var liulan="<iframe id='frame' width='100%' height='100%' src='<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+$("#Pid").val()+"&_pc_=&_area_="+$("#AreaID").val()+"&_status_=1'></iframe>";
			$("#liulan").append(liulan); 

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
		
	</script>

	<script type="text/javascript">
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!function() {
				DataTable.bootstrapTable({
				
					url : "<%=path%>/ProdPicture/getProdPictureByPPIDList?sina=<%= SmBaseUtil.Random() %>&ppid="+$("#NIID").val(),
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
			
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									if(CheckhasChecked(DataTable)==true)
									{
										getImageUrl(DataTable);
									}	
								})
			DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList(t);
					
				})
		}(document, window, jQuery);

	
		
		
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.getJSON("<%=SmBaseGlobal.PictureServiceDelete%>?pid="+ wids +"&jsonpCallback=?",  
				            function(data){  
								alert(json);
								var json=eval("("+json+")");
								if(json.result>0){
									addtoDeleteImageiDs(wids);
									ViewSuccess();
									getQueryList();
								}
					    });  
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
				url : "<%=path%>/ProdPicture/getProdPictureByPPIDList?sina=<%= SmBaseUtil.Random() %>&ppid="+$("#NIID").val()
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
				closeHtml();

			 }else{
				 alert(json.ErrorMsg);
			 }
		 }  
		
		
	
	</script>
	<div id="loading"></div>
	<div id="i_frame_div"></div>
</body>
</html>