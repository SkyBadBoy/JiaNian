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
	Users user = (Users) request.getSession().getAttribute("UserName");
	request.setAttribute("path", path);
%>
<html>

<head>
<jsp:include page="/include/commonCss.jsp" />
<link href="<%=basePath%>css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=basePath%>css/plugins/steps/jquery.steps.css?v=1.0.0.3" rel="stylesheet">
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
<body  id="body">
	<div class="container-fluid">
		<!-- Page Heading -->
		<div class="row">
			<c:if test="${Status==404}">
				<div class="alert alert-danger alert-dismissable">
					<button aria-hidden="true" data-dismiss="alert" class="close"
						type="button">×</button>
					<strong>未找到对应的记录</strong>

				</div>
			</c:if>
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a href="<%=path%>/weChatPublic/weChatPublicList"
						target="_self">微新闻支社列表</a></li>
					<li><strong>微新闻支社编辑</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
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
		<div class="row">
			<div class="col-lg-9">
				<form:form id="WeChatPublicForm" modelAttribute="WeChatPublicForm"
					enctype="multipart/form-data" cssClass="wizard-big"
					action="${path}/weChatPublic/addWeChatPublic" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<form:input type="hidden" path="Status" id="Status" name="Status" />
					<form:input type="hidden" path="QRCodeURLID" id="QRCodeURLID" name="QRCodeURLID" />
					<form:input type="hidden" path="LogoID" id="LogoID" name="LogoID" />
					<form:input type="hidden" path="BannerID" id="BannerID" name="BannerID" />
					<form:input type="hidden" path="Bind" id="Bind" name="Bind" />
					<form:errors path="ID" Class="help-block m-b-none errorMsg"></form:errors>
					<input type="hidden" id="CityAreaID" name="CityAreaID" value="${CityAreaID}" />
					<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
					<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" /> 
					<input type="hidden" name="SubmitUnitID" id="SubmitUnitID" /> 
					<input type="hidden" name="basePath" value="<%=basePath%>" id="basePath" /> 	
					
					<h1>基本信息</h1>
					<fieldset>
						
						<div class="row">
							<div class="col-sm-8">
								<div class="form-group">
									<label>支社微信号</label>
									<form:input type="text" class="form-control" path="WeChat"
										name="WeChat" placeholder="请输入支社微信号"
										data-bv-notempty-message="支社微信号不能为空" />
									<form:errors path="WeChat" Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>支社微信公众号名称</label>
									<form:input type="text" class="form-control" path="Company"
										name="Company" placeholder="请输入支社微信公众号名称"
										data-bv-notempty-message="支社微信公众号名称不能为空" />
									<form:errors path="Company"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>公众号类型</label>
									<div class="radio i-checks">
										<label> <form:radiobutton path="PublicType" value="1" />
											<i></i> 订阅号
										</label> <label> <form:radiobutton path="PublicType" value="2" />
											<i></i> 服务号
										</label> <label> <form:radiobutton value="3" path="PublicType" />
											<i></i> 企业号
										</label>
									</div>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>注册主体</label>
									<div class="radio i-checks">
										<label> <form:radiobutton value="1"
												path="RegisterSubject" name="RegisterSubject" /> <i></i> 政府
										</label> <label><form:radiobutton value="2"
												path="RegisterSubject" name="RegisterSubject" /> <i></i> 媒体</label>
										<label> <form:radiobutton path="RegisterSubject"
												value="3" name="RegisterSubject" /> <i></i> 企业
										</label> <label> <form:radiobutton value="4"
												path="RegisterSubject" name="RegisterSubject" /> <i></i>
											其他组织
										</label> <label> <form:radiobutton value="5"
												path="RegisterSubject" name="RegisterSubject" /> <i></i> 个人
										</label>
									</div>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>功能简介</label>
									<form:textarea class="form-control" path="Content"
										name="Content" data-bv-notempty-message="功能简介不能为空" rows="3" />
									<form:errors path="Content"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
						</div>
					</fieldset>

					<h1>证件信息</h1>
					<fieldset>
						
						<div class="row">
							<div class="col-sm-8">
								<div class="form-group">
									<label>组织全称</label>
									<form:input type="text" class="form-control" path="OrgName"
										name="OrgName" placeholder="请输入组织全称"
										data-bv-notempty-message="组织全称不能为空" />
									<form:errors path="OrgName"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>组织机构代码</label>
									<form:input type="text" class="form-control"
										path="Organizational" name="Organizational"
										placeholder="请输入组织机构代码" data-bv-notempty-message="组织机构代码不能为空" />
									<form:errors path="Organizational"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>营业执照代码</label>
									<form:input type="text" class="form-control"
										path="BusinessLicense" name="BusinessLicense"
										placeholder="请输入营业执照代码" data-bv-notempty-message="营业执照代码不能为空" />
									<form:errors path="BusinessLicense"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>

							</div>
						</div>
					</fieldset>


					<h1>联系信息</h1>
					<fieldset>
						
						<div class="row">
							<div class="col-sm-8">
								<div class="form-group">
									<label>联系人</label>
									<form:input type="text" class="form-control"
										path="ContactPerson" name="ContactPerson" placeholder="请输入联系人"
										data-bv-notempty-message="联系人不能为空" />
									<form:errors path="ContactPerson"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>联系人手机</label>
									<form:input type="text" class="form-control"
										path="ContactPhone" name="ContactPhone" placeholder="请输入联系人手机"
										data-bv-notempty-message="联系人手机不能为空" />
									<form:errors path="ContactPhone"
										Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="form-group">
									<label>联系邮箱</label>
									<form:input type="text" class="form-control" path="EMail"
										name="EMail" placeholder="请输入联系邮箱"
										data-bv-notempty-message="联系邮箱不能为空" />
									<form:errors path="EMail" Class="help-block m-b-none errorMsg"></form:errors>
								</div>

							</div>
						</div>
					</fieldset>

					<h1>地址信息</h1>
					<fieldset>
						
						<div class="row">
							<div class="col-sm-2">
								<div class="form-group">
									<label>所在省</label>
									<select class="form-control m-b" id="Province"
									<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									onchange="getCity(this);referMapLatlng()" name="Province">
									
									<c:forEach var="Part" items="${Province}">
										<option value="${Part.ID}"
											<c:if test="${Part.ID== ProvinceID}">selected="selected" </c:if>>${Part.name}</option>
									</c:forEach>
								</select>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label>所在城市</label>
									<select class="form-control m-b" id="City"
									<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
										onchange="getAreaID(this);referMapLatlng()" name="City">
										
									</select>
									
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label>区域</label>
									<select class="form-control m-b" id="AreaID"  onchange="getUnitAreaID(this);referMapLatlng()"
									<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									 name="AreaID">
									
								</select>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label>单位</label>
									<select class="form-control m-b" id="UnitAreaID"  onchange="referMapLatlng()"
									<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									 name="UnitAreaID">
									
								</select>
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<label>详细地址</label> 
									<input type="hidden" value="${WeChatPublicForm.address}" id="detailAddress"/>
									<form:errors path="Address" Class="help-block m-b-none errorMsg"></form:errors>
									<form:input type="text" class="form-control" path="Address" onchange="referMapLatlng()" 
										name="Address" placeholder="请输入详细地址(请勿重复输入省、市、区)" onkeypress="if(event.keyCode == 13)referMapLatlng()"
									 />
									
									<div style="float:left; " >
									<div id="MapContainer" style="float:left;height: 450px;width:600px"></div>
									
									</div>
									<div id="r-result" style="float:left;width:30%;overflow-y:scroll;height: 450px;"></div>
									<form:hidden class="form-control" path="Lng" name="Lng"
										placeholder="请输入经度" data-bv-notempty-message="经度不能为空" />
									<form:hidden class="form-control" path="Lat" name="Lat"
										placeholder="请输入纬度" data-bv-notempty-message="纬度不能为空" />
									<form:errors path="Lng" Class="help-block m-b-none errorMsg"></form:errors>
								</div>
							</div>

						</div>
					</fieldset>
	<h1>图片信息</h1>
					<fieldset>
					
						<div class="row">
							<div class="col-sm-8">
								<div class="form-group">
									<label>请选择二维码图片</label>
									<form:errors path="QRCodeURL"
										Class="help-block m-b-none errorMsg"></form:errors>
									<div class="row">
										<div class="col-md-6">
											<div class="image-crop" >
												<img style="height: :100%;width: 100%" src="${QRCodeURL}" id="QRCodeURLImg" name="QRCodeURLImg">
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
									<label>请选择主页图片</label>
									<form:errors path="Banner" Class="help-block m-b-none errorMsg"></form:errors>
									<div class="row">
										<div class="col-md-6">
											<div class="image-crop">
												<img style="height: :100%;width: 100%" src="${Banner}" id="BannerImg" name="BannerImg">										
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
									<label>请选择LOGO图片</label>
									<form:errors path="Logo" Class="help-block m-b-none errorMsg"></form:errors>
									<div class="row">
										<div class="col-md-6">
											<div class="image-crop">
												<img style="height: :100%;width: 100%"  src="${Logo}"  id="LogoImg" name="LogoImg">
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
					</fieldset>


				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>
	<jsp:include page="/include/commonJs.jsp" />
	<script src="<%=basePath%>js/plugins/iCheck/icheck.min.js"></script>
	<script src="<%=basePath%>js/plugins/staps/jquery.steps.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	<script type="text/javascript">
	
	$(function() {
		
		getProvince();
	    $("#WeChatPublicForm").steps({
	        bodyTag: "fieldset",
	        autoFocus: true,
	        enableAllSteps: true,
	        
	        onStepChanging: function(event, currentIndex, newIndex) {
	            if (currentIndex > newIndex) {
	                return true;
	            }
	            if (newIndex === 5) {
	                return false;
	            }
	            var form = $(this);
	            if (currentIndex < newIndex) {
	                $(".body:eq(" + newIndex + ") label.error", form).remove();
	                $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
	            }
	            form.validate().settings.ignore = ":disabled,:hidden";
	            return form.valid()
	        },
	        onStepChanged: function(event, currentIndex, priorIndex) {
	            if (currentIndex === 5) {
	                $(this).steps("next");
	            }
	            if (currentIndex === 5 && priorIndex === 6) {
	                $(this).steps("previous");
	            }
	        },
	        onFinishing: function(event, currentIndex) {
	            var form = $(this);
	            form.validate().settings.ignore = ":disabled";
	            return form.valid();
	        },
	        onFinished: function(event, currentIndex) {
	            var form = $(this);
	            submitForm();
	            form.submit();
	        }
	    }).validate({
	        errorPlacement: function(error, element) {
	            element.before(error)
	        },
	        rules: {
	            WeChat: {
	                required: !0,
	                maxlength: 50
	            },
	            Company: {
	                required: !0,
	                maxlength: 50
	            },
	            AreaID: {
	                required: !0
	            },
	            QRCodeURLImgfile: {
	            	Image: !0
	            }

	        },
	        messages: {
	            WeChat: {
	                required: "支社微信号不能为空",
	                maxlength: "支社微信号不能大于50个字符"
	            },
	            Company: {
	                required: "支社名不能为空",
	                maxlength: "支社名字不能大于50个字符"
	            },
	            AreaID: {
	                required: "所在区域不能为空"
	            },
	            QRCodeURLImgfile: {
	            	Image: "二维码不能为空"
	            }
	        }
	        
	    })

	    $(".i-checks").iCheck({
	        checkboxClass: "icheckbox_square-green",
	        radioClass: "iradio_square-green",
	    })
	   // if ($('#ID').val() != '' && $('#ID').val() != '0') {
	    //    $("* [name='WeChat']").attr('readOnly', 'readOnly');
	    //}
	    $("* [href='#cancel']").click(function(){window.location='<%=path%>/weChatPublic/weChatPublicList?sina=<%= SmBaseUtil.Random() %>';})

	    referMapLatlng();
	});
	var o = $(".image-crop > img");
	$(o).cropper({
		
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
	
	function submitForm() {
		
	    
	    $("#CityAreaID").val($("#AreaID").find("option:selected").val());
	}
	
	var a ;
	var map = null;
	var myValue;
	var Currentpoint;
	
	var geoc = new BMap.Geocoder();    
	function referMapLatlng(){
		if (map==null){
			map = new BMap.Map("MapContainer"); 
			map.enableScrollWheelZoom(true);
			
			var traffic = new BMap.TrafficLayer();
			map.addTileLayer(traffic);
			map.addControl(new BMap.NavigationControl()); 
			map.addControl(new BMap.ScaleControl()); 
			map.addControl(new BMap.OverviewMapControl()); 
			map.addControl(new BMap.MapTypeControl()); 
		}
		Currentpoint = new BMap.Point(116.404, 39.915);
		map.centerAndZoom(Currentpoint, 16); 
		
		var Address=getCityInfo();

		
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		
		$("#detailAddress").val(Address+$("#Address").val());
	
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint($("#detailAddress").val(), function(point){
			if (point) {
				setCurrentPoint(point);
			}
		}, Address);
		
		SearchMap();
		
		map.centerAndZoom(Currentpoint, 16);
		map.addEventListener("click", function(e){ 
			setCurrentPoint(e.point);
			geoc.getLocation(e.point, function(rs){
				var addComp = rs.addressComponents;
				var detailaddress=addComp.street  + addComp.streetNumber;
				$("#Address").val(detailaddress);
				var Address=getCityInfo();
				$("#detailAddress").val(Address+$("#Address").val());
				
				SearchMap();
				map.clearOverlays();  
				map.addOverlay(new BMap.Marker(Currentpoint));
				
				var infoWindow = new BMap.InfoWindow($("#detailAddress").val(), opts);  // 创建信息窗口对象 
				map.openInfoWindow(infoWindow,Currentpoint); //开启信息窗口
				map.centerAndZoom(Currentpoint, 16);
			});        
			
			
			
			
		});
		$("#SubmitUnitID").val($("#UnitAreaID option:selected").val());
	}
	setTimeout(function(){
		var infoWindow = new BMap.InfoWindow($("#detailAddress").val(), opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,Currentpoint); //开启信息窗口
		map.centerAndZoom(Currentpoint, 16);
	}, 3000);
	
	function getCityInfo(){
		var Address="浙江省杭州市";
		if($("#Province").find("option:selected").text()!=""){
			Address=$("#Province").find("option:selected").text();
		}
		if($("#City").find("option:selected").text()!=""){
			Address=Address+$("#City").find("option:selected").text();
		}
		if($("#AreaID").find("option:selected").text()!=""){
			Address=Address+$("#AreaID").find("option:selected").text();
		}
		return Address;
	}
	
	var opts = {
			  width : 200,     // 信息窗口宽度
			  height: 30,     // 信息窗口高度
			  title : "" , // 信息窗口标题
			  enableMessage:true,//设置允许信息窗发送短息
			  message:""
			}
function SearchMap(){
		var local = new BMap.LocalSearch(map, {
			renderOptions: {map: map, panel: "r-result", selectFirstResult: false  }
			
		});
		local.setInfoHtmlSetCallback(function(poi){
			console.log(poi);
			$("#Address").val(poi.address);
			setCurrentPoint(poi.point);
		});
		local.search($("#detailAddress").val());
}
function setCurrentPoint(point){
		Currentpoint=point;
		$("#Lng").val(point.lng);
		$("#Lat").val(point.lat);
		
		map.centerAndZoom(point, 16);
		map.addOverlay(new BMap.Marker(point));
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


function openAddHtml(type)
{
	returnTop();
	document.getElementById("body").style.overflow="hidden";
	shade2.style.display='block';
	Layer2.style.display='block';
	$("#uploadType").val(type);
}


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
		 if(uploadType==1){
			 $("#QRCodeURLID").val(json.Status);
			 $("#QRCodeURLImg").attr("src",json.ImageURL.image_800);
		 }else if(uploadType==2){
			 $("#BannerID").val(json.Status);
			 $("#BannerImg").attr("src",json.ImageURL.image_800);
		 }else if(uploadType==3){
			 $("#LogoID").val(json.Status);
			 $("#LogoImg").attr("src",json.ImageURL.image_800);
		 }
	 }else{
		 alert(json.ErrorMsg);
	 }
	 $("#up").css("display","block");
	 closeHtml2();
 }
function up()
{
	document.getElementById("up").style.display="none";
}

</script>
<div id="loading"></div>
<div id="i_frame_div"></div>
</body>
</html>