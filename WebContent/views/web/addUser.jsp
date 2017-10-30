<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	int level=SmBaseGlobal.LevelStatus.ParsonManage.getid();
	int Arealevel=SmBaseGlobal.LevelStatus.AreaManage.getid();
	int Studentlevel=SmBaseGlobal.LevelStatus.StudentManage.getid();
	Boolean isSupperManage=user.getLevel()==SmBaseGlobal.LevelStatus.SuperManage.getid();
	Boolean isAreaManage=user.getLevel()==Arealevel;
	request.setAttribute("SuperManage", SmBaseGlobal.LevelStatus.SuperManage.getid());
	request.setAttribute("StudentsLevel", SmBaseGlobal.LevelStatus.StudentManage.getid());
	String AdminLevelName=user.getAdminLevel().getName();
	request.setAttribute("path", path);
	
%>
<html>

<head>
	
	<jsp:include page="/include/commonCss.jsp" />
</head>
<body>
	<div class="container-fluid">
	
		<!-- Page Heading -->
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><a href="<%=path%>/Users/UserList"
						target="_self">用户列表</a></li>
					<li><strong>用户编辑</strong></li>
				</ol>
			</div>
			<br/>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-9">
				<form:form id="usersForm" modelAttribute="usersForm"
					action="${path}/Users/addUser?sina=<%= SmBaseUtil.Random() %>" method="post">
					<form:input type="hidden" path="ID" id="ID" name="ID" />
					<input type="hidden" id="CityAreaID" name="CityAreaID" value="${CityAreaID}" />
						<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
						<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" />
						<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
						<input type="hidden" name="AdminLevelParseon" value="${AdminLevelParseon}" id="AdminLevelParseon" iscon="true" />
						<input type="hidden" name="AdminLevelArea" value="${AdminLevelArea}" id="AdminLevelArea" iscon="true" />
						<input type="hidden" name="LevelID" value="${LevelID}" id="LevelID" />
					
					<div class="row">
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户名</label>
							<form:input type="text" class="form-control" path="LoginName"
								id="LoginName"  name="LoginName"
								placeholder="请输入用户名" />
							<form:errors path="LoginName" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户姓名</label>
							<form:input type="text" class="form-control" path="Name"
								id="Name"  name="Name"
								placeholder="请输入用户姓名" />
							<form:errors path="Name" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>手机号码</label>
							<form:input type="text" class="form-control" path="Phone"
								id="Phone"  name="Phone"
								placeholder="请输入用户联系方式" />
							<form:errors path="Phone" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8" id="resetpwd">
						<div class="form-group">
							<label><c:if test="${isEdit==1}">重置</c:if>密码</label>			
							<form:password class="form-control" path="PassWord"
								id="PassWord" name="PassWord"
								placeholder="请输入密码" />
							<form:errors path="PassWord" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label>用户类型</label>		
							${isStudent}
							<c:if test="${type=='self' and usersForm.level!=StudentsLevel}">
								<%=AdminLevelName %>
								<input type="hidden" id="Level" name="Level" value="${usersForm.level}"/>
							</c:if>
							<c:if test="${usersForm.level==StudentsLevel}">
								${usersForm.adminLevel.name }
								<input type="hidden" id="Level" name="Level" value="${usersForm.level}"/>
							</c:if> 
							<c:if test="${type==null and usersForm.level!=StudentsLevel}">
								<select class="form-control m-b" id="Level" onchange="changelevel()" name="Level">
	
										<c:forEach var="Part" items="${AdminLevelList}">
											<option value="${Part.ID}" <c:if test="${Part.ID== usersForm.level}">selected="selected" </c:if>>
												${Part.name}</option>
												
										</c:forEach>
								</select>
							</c:if> 
							
						</div>
					</div>
					
					<div class="col-sm-8" id="wechatDiv">
					<div class="form-group" >
						<label>微信公众号</label>
						<div class="input-group m-b">
							<div class="input-group-btn">

								<button type="button" data-toggle="modal" data-target="#myModal" id="ReferWeChatBut"
									onclick="getQueryList()" class="btn btn-outline btn-default">
									<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
								</button>
							</div>
							<input type="text" readonly="readonly" placeholder="请选择微信公众号"
								value="${WeChatName}" data-bv-notempty-message="微信公众号不能为空"
								name="WeChatName" class="form-control">
							<form:input type="hidden" path="WeChatID" id="WeChatID"
						name="WeChatID" />
						<form:errors path="WeChatID" cssClass="help-block m-b-none errorMsg"></form:errors>
						</div>
					</div>
					</div>
					
					<div class="col-sm-8" id="wechatArea" >
					 	<form:errors path="AreaID" cssClass="help-block m-b-none errorMsg"></form:errors>
					</div>
					</div>
					<button type="submit" onclick="submitFrom()"
								class="btn btn-primary">保存</button>
					</form:form>
					</div>
					
					
				
			</div>
		</div>
		<!-- /.row -->
	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1"> 
				   <div class="modal-dialog">
			    <div class="modal-content">
			     <div class="modal-header">
			      <a type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</a>
			      <h4 class="modal-title">参照微信公众号</h4>
			     </div>
			     <div class="modal-body"> 
			     <div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button id="referWechat" data-dismiss="modal" class="btn btn-warning">参照</button>
							</div>
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
				  
			     </div> 
			    </div>
			   </div>
			  </div>
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	var DataTable=$("#wechatlist");
	if($("#Level option").length>0){
		$("#Level").trigger("change");
	}else{
		changelevel();
	}
	if($("#ID").val()==<%=user.getID()%>){
		$("#Level").attr("disabled","disabled");
		$("#resetpwd").css("display","none");
		$("#wechatArea").css("display","none");
		$("#wechatDiv").css("display","none");
		
		
	}
		$(function() {
			initialRegionQueryInfo($("#wechatArea"));
			if ($('#ID').val() != '' && $('#ID').val() != '0') {
		        $("* [name='LoginName']").attr('readOnly', 'readOnly');
		    }
		        $("#usersForm").validate({
					rules : {
						LoginName:{
							required : !0,
							maxlength : 20
						},
						PassWord : {
							
							maxlength : 20
						},
						Name:{
							required : !0,
							maxlength : 40
						},
						Phone:{
							required : !0,
							mobile : !0
						}
					},
					messages : {
						LoginName : {
							required : "用户名不能为空",
							maxlength : "用户名不能超过50个字符"
						},
						PassWord : {
		
							maxlength : "密码不能超过50个字符"
						},
						Name:{
							required : "用户姓名不能为空",
							maxlength : "用户姓名不能超过40个字符"
						},
						Phone:{
							required : "用户联系方式不能为空",
							mobile : "请输入正确的手机号码"
						}
						
					}
				});
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
		        DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList(t);
				})
		});
		$('#referWechat').click(
				function() {
					var selectContent = DataTable.bootstrapTable(
							'getSelections');
					$("* [name='WeChatID']").val(selectContent[0].pkid);
					$("* [name='WeChatName']").val(selectContent[0].company);
					$("#CityAreaID").val(selectContent[0].areaID);
			});
			
			/*公众号参照*/
			function getQueryList(searchText) {
				if(searchText==null || searchText==undefined){
					searchText="";
				}
				DataTable.bootstrapTable("refresh", {
					url : "<%=path%>/weChatPublic/getWeChatPublicForEffList?sina=<%= SmBaseUtil.Random() %>&Company="+ searchText +"&AreaID="+$("#AreaID").val()
				});
				
			}
			function queryParams(params) {
				return {
					pageSize : params.limit,
					pageNumber : params.offset,
					AreaID:$("#AreaID").val()
				};

			}
			
			function changelevel(){
				var Level=$("#Level").find("option:selected").val();
				if(Level!=undefined){
					$("#LevelID").val(Level);
				}else{
					Level=$("#LevelID").val();
				}
				//校级管理员
				if(Level==<%=level%>){
					$("#wechatArea").css("display","none");
					$("#wechatDiv").removeAttr("style");
					$("#AreaID").removeAttr("disabled");
					$("#WeChatID").removeAttr("disabled");
					
					//区域管理员
				}else if(Level==<%=Arealevel%>){
					$("#wechatDiv").css("display","none");
					$("#WeChatID").attr("disabled","disabled");
					$("#wechatArea").removeAttr("style");
					$("#UnitAreaIDDiv").css("display","none");
				}else{
					$("#wechatArea").css("display","none");
					$("#AreaID").attr("disabled","disabled");
					$("#wechatDiv").css("display","none");
					$("#WeChatID").attr("disabled","disabled");
				}
				//是否是区域管理员
				if(<%=isAreaManage%>){
					$("#City").attr("disabled","disabled");
					$("#Province").attr("disabled","disabled");
					$("#AreaID").attr("disabled","disabled");
					if(parseInt($("#ID").val())>0){
						$("#ReferWeChatBut").css("display","none");
					}
				}
			}
			function submitFrom(){
					if($("#CityAreaID").val()=="" || $("#CityAreaID").val()=="0"){
						$("#CityAreaID").val($("#AreaID").val());
					}
					
					var Level=$("#Level").find("option:selected").val();
					if(Level!=undefined){
						$("#LevelID").val(Level);
						console.log($("#Level").val);
					}else{
						$("#LevelID").val($("#Level").val());
					}   
			}
	</script>
</body>
</html>