<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
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

<<jsp:include page="/include/commonCss.jsp" />
</head>
<body>
	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a href="<%=path%>/weChatbanner/weChatbannerList?sina=<%= SmBaseUtil.Random() %>"
						target="_self">社区广告列表</a></li>
					<li><strong>社区广告编辑</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-9">
				<form:form id="WeChatBannerForm" modelAttribute="WeChatBannerForm"
					enctype="multipart/form-data"
					action="${path}/weChatbanner/addweChatbanner" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<form:input type="hidden" path="QRCodeURLID" id="QRCodeURLID"
						name="QRCodeURLID" />
					<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
					<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
					<input type="hidden" name="CityAreaID" id="CityAreaID"
		value="${AreaID}" />
					<input type="hidden" name="Img_X" id="Img_X" />
					<input type="hidden" name="Img_Y" id="Img_Y" />
					<input type="hidden" name="Img_W" id="Img_W" />
					<input type="hidden" name="Img_H" id="Img_H" />
					<div class="form-group" >
						<label>标题</label>
						<form:input type="text" class="form-control" path="Title"
							name="Title" placeholder="请输入标题"
							data-bv-notempty-message="标题不能为空" />
						<form:errors path="Title" cssClass="help-block m-b-none errorMsg"></form:errors>
					</div>
					<div class="form-group" >
						<label>微信公众号</label>
						
						<form:errors path="WeChatID" cssClass="help-block m-b-none errorMsg"></form:errors>
						<div class="input-group m-b">
							<div class="input-group-btn">

								<button type="button" data-toggle="modal" data-target="#myModal"
									onclick="getWechatPublic()" class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
							</div>
							<input type="text" readonly="readonly" placeholder="请选择微信公众号"
								value="${WeChatName}" data-bv-notempty-message="微信公众号不能为空"
								name="WeChatName" class="form-control">
							<form:input type="hidden" path="WeChatID" id="WeChatID"
						name="WeChatID" />
						
						</div>

					</div>
					<div class="row">
					<div class="form-group">
						<div class="col-md-2">
							<label>社区地区</label>
							<div class="input-group m-b">
								<select class="form-control m-b" id="Province" 
								<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									onchange="getCity(this)" name="Province">
									
									<c:forEach var="Part" items="${Province}">
										<option value="${Part.ID}"
											<c:if test="${Part.ID== ProvinceID}">selected="selected" </c:if>>${Part.name}</option>
									</c:forEach>
								</select>

							</div>
						</div>
						<div class="col-md-2">
							<label>城市</label>
							<div class="input-group m-b">
								<select class="form-control m-b" id="City"
								<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
									onchange="getAreaID(this)" name="City">
									
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<label>地区</label>
							<form:errors path="AreaID" cssClass="help-block m-b-none errorMsg"></form:errors>
							<div class="input-group m-b">
								<select class="form-control m-b" 
								<c:if test="${AdminLevelArea}">
							disabled="disabled"
							</c:if>
								id="AreaID" name="AreaID">
									
								</select>
							</div>
						</div>
					</div>
					
					</div>
					<div class="row">
					<div class="form-group" >
					<div class="col-md-2">
						<label>投放广告的时间(年)</label> 
						<form:errors path="Year"
							cssClass="help-block m-b-none errorMsg"></form:errors>
						<br/>
						<div class="input-group m-b">
							<select class="form-control m-b" id="Year" onchange="clearError('Year.errors')"  name="Year">
								<c:forEach var="Part" items="${Years}">
									<option value="${Part}"
										<c:if test="${Part== Year}">selected="selected" </c:if>>${Part}</option>
								</c:forEach>
							</select>
							
							</div>
					</div>
					<div class="col-md-2">
					<label>投放广告的月份(月)</label> 
					<form:errors path="Month"
							cssClass="help-block m-b-none errorMsg"></form:errors>
					<div class="input-group m-b">
						<select class="form-control m-b" onchange="clearError('Month.errors')" id="Month"  name="Month">
								<c:forEach var="Part" items="${Months}">
									<option value="${Part}"
										<c:if test="${Part== Month}">selected="selected" </c:if>>${Part}</option>
								</c:forEach>
							</select>
						
						
							</div>
					</div>
					</div>
					</div>
					<div class="form-group">
						<label>请选择广告图片</label>
						<form:errors path="QRCodeURL"
							cssClass="help-block m-b-none errorMsg"></form:errors>
						<div class="row">
							<div class="col-md-6">
								<div class="image-crop">
									<img style="height: :100%; width: 100%" src="<%=path%>/${file}">
								</div>
							</div>
							<div class="col-md-6">
								<h4>图片预览：</h4>
								<div class="img-preview img-preview-sm"></div>
								<h4>说明：</h4>
								<p>你可以选择新图片上传，然后下载裁剪后的图片</p>
								<div class="btn-group">
									<label title="上传图片" for="inputImage" class="btn btn-primary">
										<input type="file" accept="image/*" name="file" onchange="clearError('QRCodeURL.errors')"
										value="${inputImage}" id="inputImage" class="hide">
										上传新图片
									</label> 
								</div>

								<div class="btn-group">
									<button class="btn btn-white" id="zoomIn" type="button">放大</button>
									<button class="btn btn-white" id="zoomOut" type="button">缩小</button>
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

			<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1"> 
				   <div class="modal-dialog">
			    <div class="modal-content">
			     <div class="modal-header">
			      <a type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</a>
			      <h4 class="modal-title">参照微信公众号</h4>
			     </div>
			     <div class="modal-body"> 
				<table id="wechatlist" data-height="400" 
				   data-mobile-responsive="true">
			    <thead>
			     <tr>
			      <th data-field="state" data-radio="true"></th>
			      <th data-field="pkid">ID</th>
			     <th data-field="weChat">微信公众号</th>
			     <th data-field="company">公司名称</th> 
			     </tr> 
			    </thead>
			   </table>
				  <button id="referWechat" data-dismiss="modal" class="btn btn-warning">参照</button>
			     </div> 
			    </div>
			   </div>
			  </div>
	<jsp:include page="/include/commonJs.jsp" />

	<script type="text/javascript">
		var o = $(".image-crop > img");
		var DataTable=$("#wechatlist");
		$(function() {
			if($("#Province").val()!=""){
				getCity(document.getElementById("Province"));
			}else{
				getCity(document.getElementById("ProvinceID"));
			}
			$(o).cropper({
				aspectRatio : 1.618,
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

		});

		$("#WeChatBannerForm").validate({
			rules : {
				Title:{
					required : !0,
					maxlength : 50
				},
				Year : {
					required : !0
				},
				Month : {
					required : !0
				},
				AreaID : {
					required : !0
				}
			},
			messages : {
				Title : {
					required : "标题不能为空",
					maxlength : "标题长度不能超过50个字符"
				},
				Year : {
					required : "过期年份不能为空"
				},
				Month : {
					required : "过期月份不能为空"
				},
				AreaID : {
					required : "社区地区不能为空"
				}
			}
		});

		
		function submitFrom() {
			$("#Img_X").val(o.cropper("getData").x);
			$("#Img_Y").val(o.cropper("getData").y);
			$("#Img_W").val(o.cropper("getData").width);
			$("#Img_H").val(o.cropper("getData").height);
			$("#CityAreaID").val($("#AreaID").val());
		}
		
		$('#referWechat').click(
			function() {
				var selectContent = DataTable.bootstrapTable(
						'getSelections');
				$("* [name='WeChatID']").val(selectContent[0].pkid);
				$("* [name='WeChatName']").val(selectContent[0].company);
		});
		
		/*公众号参照*/
		function getWechatPublic() {
			DataTable.bootstrapTable({
				url : "<%=path%>/weChatPublic/getWeChatPublicForEffList?sina=<%= SmBaseUtil.Random() %>&AreaID="+$("#AreaID").val(),
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				clickToSelect:true,
				sidePagination : "server", //服务端请求
				queryParams : queryParams,
				responseHandler : responseHandler,
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbar",
				icons : geticons
			});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}	
		
	</script>
</body>
</html>