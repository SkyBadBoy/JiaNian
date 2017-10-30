<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.*"%>
<%@	page import="wtb.smUtil.SmBaseGlobal"%>
<%@	page import="wtb.core.model.Users"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//response.setContentType("text/xml;charset=UTF-8");
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String rootPath = getClass().getResource("/").getFile().toString();
	rootPath = rootPath.replace("classes/", "");
	rootPath = rootPath.replace("WEB-INF/", "");
	Users user = (Users) request.getSession().getAttribute("UserName");
	String a = request.getAttribute("DataWeChat1").toString();
	String mGroup1 = request.getAttribute("mGroup1").toString();
%>
<html>
<head>

<jsp:include page="/include/commonCss.jsp" />

<script type="text/javascript" src="<%=basePath%>js/jquery.nestable.js"></script>



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
           background: url(../img/wsload.gif) no-repeat center center #FFF;
           background-size: 3%;
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
	margin-top: 70px;
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
	top: 200px;
}
</style>

</head>
<body>
<div id="loading"></div>
	<input type="hidden" id="appid" value="${appid}" />
	<input type="hidden" id="appsecret" value="${appsecret}" />
	<input type="hidden" id="mGroup" value="${mGroup}" />
	<input type="hidden" id="IsSame" value="${IsSame}" />
	<input type="hidden" id="wid" value="${wid}" />
	<input type="hidden" id="name" value="${name}" />
	<input type="hidden" id="id" value="${id}" />
	<input type="hidden" id="numb" />
	<div id="shade"></div>
	<div id="Layer1">
		<div id="iphone">
			<div id="liulan"></div>
			<div>
				<ul class="view_list list-unstyled">
					<c:if test="${isApply}">
						<li class="view_item"
							onclick="deleteWeChat(<%=SmBaseGlobal.CheckStatus.Effective.getid() %>)">通&nbsp;&nbsp;&nbsp;&nbsp;过</li>
						<li class="view_item"
							onclick="deleteWeChat(<%=SmBaseGlobal.CheckStatus.NotPass.getid() %>)">不通过</li>
					</c:if>
					<li class="view_item" onclick="closeHtml()">关&nbsp;&nbsp;&nbsp;&nbsp;闭</li>
				</ul>
			</div>
		</div>

	</div>

	<div class="col-lg-12">
		<br />
		<ol class="breadcrumb">
			<li><a href="/WeNewsAgency/home" target="_self">主页</a></li>
			<li><a href="<%=path%>/WeChatCustom/WeChatCustomHome?sina=<%= SmBaseUtil.Random() %>"
				target="_self">公众号管理</a></li>
			<li><strong>公众号查看与编辑</strong></li>
		</ol>
	</div>


	<div class="tabs-container col-sm-12"
		style="padding-left: 15px; padding-right: 15px; top:15px">
		<ul class="nav nav-tabs">
			<li class="active" id="a1"><a data-toggle="tab" href="#tab-1"
				aria-expanded="true"> 首页</a></li>
			<li class="" id="a2"><a data-toggle="tab" href="#tab-2"
				aria-expanded="false">自定义菜单</a></li>
			<li class="" id="a3"><a data-toggle="tab" href="#tab-3"
				aria-expanded="false">群发管理</a></li>
			<li class="" id="a4"><a data-toggle="tab" href="#tab-4"
				aria-expanded="false">关键字管理</a></li>
		</ul>

		<div class="tab-content">
			<div id="tab-1" class="tab-pane active">
				<div class="panel-body">


					<div class="col-sm-5">
						<div class="well" style="height: 320px;">
							<h3>详情修改：<button id="showModifyView" class="btn btn-white btn-circle">
                            <i class="fa fa-wrench"></i>
                        </button></h3>
							
							<h4 style="padding-left: 35px;margin-top: 5px;">名字：</h4>
							<h5 style="padding-left: 70px;" >${name}</h5>
							<h4 style="padding-left: 35px;">微信号：</h4>
							<h5 style="padding-left: 70px;">${wid}</h5>
							<h4 style="padding-left: 35px;">AppID：</h4>
							<h5 style="padding-left: 70px;">${appid}</h5>
							<h4 style="padding-left: 35px;">Appsecret:</h4>
							<h5 style="padding-left: 70px;">${appsecret}</h5>
							<h4 style="padding-left: 35px;">地区:</h4>
							<h5 style="padding-left: 70px;">${area}</h5>
						</div>

					</div>
					<div class="col-sm-7">
						<div class="well">
							<h3>被添加公众号时自动回复：</h3>
							<%--<div class="mail-text">

								<div class="summernote"></div>
								<div class="clearfix"></div>
							</div>--%>
							<input type="text" id="AutoContent" placeholder="请输入公众号自动回复内容"
								style="height: 50px; width: 100%; margin-top: 20px" /> <br>
							<input type="button" value="确定" onclick="subscribe()"
								style="margin-top: 20px;" class="btn btn-white" />
						</div>
					</div>

				</div>
			</div>
			<div id="tab-2" class="tab-pane">
				<div class="panel-body">
					<div class="col-sm-5">
						<div id="ErrorMessage2" role="alert"></div>

						<button type="button" onclick="addView()"
							class="btn btn-outline btn-default">
							<i class="glyphicon glyphicon-plus" title="增加主菜单"
								aria-hidden="true">增加主菜单</i>
						</button>

						<br> <br>
						<div class="dd" id="nestable3">
							<ol class='dd-list dd3-list'>
								<div id="dd-empty-placeholder"></div>
							</ol>
						</div>

					</div>

					<div class="col-sm-7">

						<div id="body" class="body page_menu_setting">
							<div id="js_container_box"
								class="container_box cell_layout side_l">
								<div class="col_main">
									<div class="main_bd">
										<div class="menu_setting_area_wrp" id="menu_container"
											style="margin-top: 10px;">
											<input style="width: 400px;" type="text" id="caidan"
												placeholder="输入按钮名称" class="form-control" />
											<%-- <input
											style="width: 100px;" type="button" onclick="deleteWeChat()"
											class="btn btn-outline btn-default" value="删除菜单" /> --%>
											<input type="hidden" id="id" /> <input type="hidden"
												id="mgroup" /> <input type="hidden" id="parentid" /> <select
												id="button_type" style="margin-top: 10px;">
												<option value="0">--请选择按钮类型--</option>
												<option value="click">点击</option>
												<option value="view">连接</option>
												<input type="text" style="margin-top: 10px;"
												class="form-control" id="key" />
										</div>

										<div class="tabs-container" name="table"
											style="margin-top: 10px;" style="display: block;">
											<ul class="nav nav-tabs">
												<li class="active" value="1" id="wenzi"><a
													data-toggle="tab" href="#tab-100" aria-expanded="true">文字</a></li>
												<li class="" id="tuwen" value="2"><a data-toggle="tab"
													href="#tab-200" aria-expanded="false">图文</a></li>
											</ul>
											<div class="tab-content">
												<div id="tab-100" class="tab-pane active">
													<div class="panel-body">
														<input type="text" class="form-control" id="text1" />
													</div>
												</div>
												<div id="tab-200" class="tab-pane">
													<div class="panel-body">
														<%-- 	<input type="text" class="form-control" id="Title"
																placeholder="请输入标题"> <input type="text"
																class="form-control" id="Description"
																placeholder="请输入详情"> <input type="text"
																class="form-control" id="PicUrl" placeholder="请输入图片地址">
															<input type="text" class="form-control" id="Url"
																placeholder="请输入图片点击地址">--%>
														<input type="button" onclick="selNews()"
															class="btn btn-outline btn-default" value="从新闻列表中选取" />
													</div>
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
							<div class="tool_bar tc js_totaltool_bar">

								<input type="button" onclick="save1()"
									class="btn btn-outline btn-default" value="保存"
									style="margin-top: 10px;" />
								<%-- 	<input type="button" onclick="shuaxin()"
										class="btn btn-outline btn-default" value="刷新" />--%>
							</div>

						</div>
					</div>
				</div>

			</div>



			<div id="tab-3" class="tab-pane">
				<div class="panel-body">
					<div class="container-fluid">
						<!-- Page Heading -->
						<div class="row">
							<div class="col-sm-12">
								<!-- Example Events -->
								<div class="example-wrap">
									<div class="example">

										<div id="ErrorMessage3" role="alert"></div>
										<div class="btn-group hidden-xs"
											id="exampleTableEventsToolbar3" role="group">
											<button type="button" id="addWeChatContentView3"
												class="btn btn-outline btn-default">
												<i class="glyphicon glyphicon-plus" title="新增"
													aria-hidden="true"></i> 新增群发
											</button>
											<%-- --%>
											<button type="button" id="delWeChatContentForGroup"
												class="btn btn-outline btn-default">
												<i class="glyphicon glyphicon-trash" title="删除"
													aria-hidden="true"></i> 删除群发
											</button>
										</div>

										<table id="exampleTableEvents3" data-height="400"
											data-mobile-responsive="true">
											<thead>
												<tr>
													<th data-field="state" data-radio="true"></th>
													<th data-field="content">标题</th>
													<th data-field="description">简介</th>
													<!--	<th data-field="picUrl" >图片地址</th>
									<th data-field="url" >内容地址</th>-->
													<th data-field="createTime">时间</th>
													<th data-field="tempRules">类型</th>
													<th data-field="baseInfo.name">状态</th>

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
			<div id="tab-4" class="tab-pane">
				<div class="panel-body">
					<%--关键字管理 --%>
					<div class="container-fluid">
						<!-- Page Heading -->
						<div class="row">
							<div class="col-sm-12">
								<!-- Example Events -->
								<div class="example-wrap">
									<div class="example">

										<div id="ErrorMessage4" role="alert"></div>
										<div class="btn-group hidden-xs"
											id="exampleTableEventsToolbar" role="group">
											<button type="button" id="addWeChatContentView"
												class="btn btn-outline btn-default">
												<i class="glyphicon glyphicon-plus" title="新增"
													aria-hidden="true"></i> 新增关键字
											</button>
											<%--	<button type="button" onclick="del()"
												class="btn btn-outline btn-default">
												<i class="glyphicon glyphicon-pencil" title="修改"
													aria-hidden="true"></i> 修改关键字
											</button>
											 --%>
											<button type="button" id="delWeChatContent"
												class="btn btn-outline btn-default">
												<i class="glyphicon glyphicon-trash" title="删除"
													aria-hidden="true"></i> 删除关键字
											</button>
										</div>

										<table id="exampleTableEvents" data-height="400"
											data-mobile-responsive="true">
											<thead>
												<tr>
													<th data-field="state" data-radio="true"></th>
													<th data-field="key">关键字</th>
													<th data-field="content">标题</th>
													<th data-field="description">简介</th>
													<!--	<th data-field="picUrl" >图片地址</th>
									<th data-field="url" >内容地址</th>-->
													<th data-field="createTime">时间</th>
													<th data-field="tempRules">类型</th>
													<th data-field="baseInfo.name">状态</th>

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

	</div>


	<%--文本 --%>
	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">关键词编辑</h4>
				</div>
				<div class="modal-body">
					<div id="ErrorMessagem" role="alert"></div>
					<div style="text-align: center;">当前默认的是文本的新建，如需想新建图文或者图片时，请选者图文或者图片</div>
					<p>
					<div class="row">
						<form id="contentForm" action="#">
							<input type="text" class="form-control" placeholder="请输入关键字(必选)"
								id="keym" name="keym" /><br> <input type="text"
								class="form-control" placeholder="请输入对应回复的内容" id="content"
								name="content" />
						</form>
					</div>
					<br />
					<div style="text-align: right;">
						<button id="addNewsPic2" class="btn btn-outline btn-primary">选择图文</button>
						<%-- <button id="addNewsPic" class="btn btn-outline btn-primary">选择图文</button>--%>
						<button id="addPIC" onclick="addPIC()"
							class="btn btn-outline btn-primary">选择图片</button>
						<button onclick="addWeContentText()"
							class="btn btn-outline btn-danger">发表文本</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%--4上传图片 --%>
	<div class="modal" id="myModal1" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">选择发布图片（目前图片只能上传一张且图片的有效期是3天）</h4>
				</div>
				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="row">
						<br />
					</div>
					<div class="row">

						<div class="col-sm-12">
							<!-- Example Events -->
							<div class="example-wrap">
								<div class="example">
									<div id="ErrorMessagepic" role="alert"></div>
									<div class="btn-group hidden-xs"
										id="exampleTableEventsToolbarpic" role="group">
										<button type="button" id="addShangPIC"
											class="btn btn-outline btn-default">上传图片</button>
									</div>
									<table id="exampleTableEventspic" data-height="300"
										data-width="400" data-mobile-responsive="true">
										<thead>
											<tr>
												<th data-field="state" data-radio="true"></th>
												<th data-field="realUrl">图片文件</th>
												<th data-field="tempUrl">图片</th>
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


	<%--关键字 4图文--%>
	<div class="modal" id="myModalnew4" style="overflow: auto;"
		tabindex="-1">
		<div class="modal-dialog ">
			<div class="modal-content "
				style="width: 800px; margin-left: -115px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">选择图文</h4>
				</div>
				<div class="container-fluid">

					<br>
					<div id="ErrorMessagenew4" role="alert"></div>
					<div style="width: 850px; height: auto;">
						<span class="col-sm-2" style="height: auto; width: 264px;">
							<div id="tishi"
								style="text-align: center; color: red; margin-top: 10px; display: none;">当前显示顺序和微信显示顺序相同</div>
							<div id="showview" class="well"
								style="margin-left: -15px; display: none;">
								<div id="dataout"></div>
							</div>
						</span> <span class="col-sm-8" style="height: auto; margin-left: -30px;">
							<div class="container-fluid">

								<!-- Page Heading -->

								<div class="row">

									<div class="col-sm-12">
										<!-- Example Events -->
										<div class="example-wrap">
											<div class="example">
												<div id="ErrorMessagenew4" role="alert"></div>
												<div class="btn-group hidden-xs"
													id="exampleTableEventsToolbarnew4" role="group">
													<button type="button" id="addShangNew4"
														class="btn btn-outline btn-default">上传图文</button>
													<%-- 	<button type="button" id="preview4"
											class="btn btn-outline btn-default">我要预览</button>--%>
												</div>
												<table id="exampleTableEventsnew4" data-width="400"
													data-mobile-responsive="true">
													<thead>
														<tr>

															<th data-field="state" data-radio="true"></th>
															<th data-width="200" data-field="title">新闻标题</th>

															<th data-width="150" data-field="createTime">创建日期</th>
															<th data-width="60" data-field="author">作者</th>
															<th data-width="60" data-field="baseInfo.name">状态</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<!-- End Example Events -->
									</div>

								</div>
							</div>
						</span>
					</div>



				</div>
			</div>
		</div>


		<%--群发新闻 --%>
		<div class="modal" id="myModalnewGroup" style="overflow: auto;"
			tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content"
					style="width: 800px; margin-left: -115px;">
					<div class="modal-header">
						<a type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</a>
						<h4 class="modal-title">选择图文</h4>
					</div>
					<div class="container-fluid">
						<br>
						<div id="ErrorMessagenewGroup" role="alert"></div>
						<!-- Page Heading -->
						<div style="width: 850px; height: auto;">
							<span class="col-sm-2" style="height: auto; width: 264px;">
								<div id="tishiGroup"
									style="text-align: center; color: red; margin-top: 10px; display: none;">当前显示顺序和微信显示顺序相同</div>
								<div id="showviewGroup" class="well"
									style="margin-left: -15px; display: none;">
									<div id="dataoutGroup"></div>
								</div>
							</span> <span class="col-sm-8" style="height: auto; margin-left: -30px;">

								<div class="col-sm-12">
									<!-- Example Events -->
									<div class="example-wrap">
										<div class="example">

											<div class="btn-group hidden-xs"
												id="exampleTableEventsToolbarnewGroup" role="group">
												<button type="button" id="addShangNewGroup"
													class="btn btn-outline btn-default">上传图文</button>
												<button type="button" id="sendShangNewGroup"
													class="btn btn-outline btn-default">群发图文</button>
											</div>
											<table id="exampleTableEventsnewGroup" data-height="300"
												data-width="400" data-mobile-responsive="true">
												<thead>
													<tr>

														<th data-field="state" data-radio="true"></th>
														<th data-width="200" data-field="title">新闻标题</th>

														<th data-width="150" data-field="createTime">创建日期</th>
														<th data-width="60" data-field="author">作者</th>
														<th data-width="60" data-field="baseInfo.name">状态</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<!-- End Example Events -->
								</div>



							</span>


						</div>

					</div>
				</div>
			</div>

			<%-- 	<div class="modal" id="previewmo" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content" style="height:auto;width:290px;margin-left:150px;">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">预览图文</h4>
					
				</div>
				<div class="modal-body" >
					<div style="text-align:center;color: red;">当前显示顺序和微信显示顺序相同</div>
					<div class="well">
						<div id="dataout">
						</div>
					</div>
				
				
				</div>
			</div>
		</div>
	</div>--%>
			<%--群发 文本--%>
			<div class="modal" id="myModal3" style="overflow: auto;"
				tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</a>
							<h4 class="modal-title">群发编辑</h4>
						</div>
						<div class="modal-body">
							<div id="ErrorMessageg" role="alert"></div>
							<div style="text-align: center;">当前默认的是文本的新建，如需想发表图文或者图片时，请选者图文或者图片</div>
							<div style="text-align: center;">
								<font color="red">当前默认发送为所有人可见,请谨慎使用且有次数规定</font>
							</div>
							<p>
							<div class="row">
								<form id="contentForm3" action="#">
									<input type="text" class="form-control" placeholder="请输入群发的内容"
										id="content3" name="content3" />
								</form>
							</div>
							<br />
							<div style="text-align: right;">
								<button id="addNewsPicForGroup"
									class="btn btn-outline btn-primary">选择图文</button>
								<button id="addPICForGroup" onclick="addPICForGroup()"
									class="btn btn-outline btn-primary">选择图片</button>
								<button onclick="addWeContentTextForGroup()"
									class="btn btn-outline btn-danger">发表文本</button>
							</div>
						</div>
					</div>
				</div>
			</div>


			<%--群发 上传图片--%>
			<div class="modal" id="myModalForGroup" style="overflow: auto;"
				tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</a>
							<h4 class="modal-title">选择发布图片（目前图片只能上传一张）</h4>
						</div>
						<div class="container-fluid">

							<!-- Page Heading -->
							<div class="row">
								<br />
							</div>
							<div class="row">

								<div class="col-sm-12">
									<!-- Example Events -->
									<div class="example-wrap">
										<div class="example">
											<div id="ErrorMessagegroup" role="alert"></div>
											<div class="btn-group hidden-xs"
												id="exampleTableEventsToolbargroup" role="group">
												<button type="button" id="addShangPICGroup"
													class="btn btn-outline btn-default">群发图片</button>
											</div>
											<table id="exampleTableEventsgroup" data-height="300"
												data-width="400" data-mobile-responsive="true">
												<thead>
													<tr>
														<th data-field="state" data-radio="true"></th>
														<th data-field="realUrl">图片文件</th>
														<th data-field="tempUrl">图片</th>
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
		</div>

		
		<%--修改 --%>
		<div class="modal" id="myModalmodify" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">公众号修改</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div style="color: red;text-align: center;">修改成功之后将重新进入公众号管理界面，您重新进入该公众号即可!</div>
						<form id="contentForm" action="#">
							<input type="text" class="form-control" placeholder="请输入名称"
								id="nameM" name="nameM" value="${name}"/><br> <input type="text" class="form-control" placeholder="请输入微信号ID"
								id="WIDM" name="WIDM" value="${wid}"/><br> <input type="text"
								class="form-control" placeholder="请输入appID" id="appIDM"
								name="appIDM" value="${appid }"/> <br /> <input type="text" class="form-control"
								placeholder="请输入appsecret" id="appsecretM" name="appsecretM" value="${appsecret}"/>
						</form>
					</div>
					
					<br />
					<button id="updataWeChatInfo" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/include/commonJs.jsp" />
	<script src="<%=basePath%>js/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
		var DataTable=$("#exampleTableEvents3");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "/WeNewsAgency/WeChatCustom/getWeChatContentListForGroup?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入关键字"},
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar3",
					icons : geticons
	
				});
				//	var e = o("#WeChatGroupEventsResult");
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									
								});
				DataTable.on("search.bs.table", function(e, t, o) {
					getQueryListForGroup(t);
				});
				
			}()
		}(document, window, jQuery);
		
		function getQueryListForGroup(searchText){
			
			if(searchText==null || searchText==undefined){
				searchText="";
			}
				$("#exampleTableEvents3").bootstrapTable("refresh", {
					 url : "/WeNewsAgency/WeChatCustom/getWeChatContentListForGroup?sina=<%= SmBaseUtil.Random() %>&check="+encodeURI(encodeURI(searchText))
				});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		$("#addWeChatContentView3").click(function()
		{
					$('#myModal3').modal('show');
		});
		function selNews()
		{
			$('#myModalnew4').modal('show');
			$("#keym").val($("#key").val());
			$("#ErrorMessagenew4").text("您所设置的关键字为："+$("#key").val()).removeClass("alert-warning").addClass("alert alert-success");
		}
		$("#contentForm3").validate({
			rules : {
				content3:{
					required : !0
				}
			},
			messages : {
				content3:{
					required : "发送内容不能为空"
				
				}
			}
		});
		
		function addWeContentTextForGroup()
		{
			if(!$("#contentForm3").validate().form()){
				return 
			}
			$.ajax({
				url : "<%=path%>/WeChatCustom/GroupSend",
				type : "post",
				data : {
					'content':$("#content3").val(),
					'type':"text",
					'appid':$("#appid").val(),
					'appsecret':$("#appsecret").val(),
					'wid':$("#wid").val()
				}, 
				success : function(obj) {
					if(obj.Status==-1){
						$("#ErrorMessageg").text(obj.ErrorMsg).removeClass("alert-success").addClass("alert alert-warning");
					}else if(obj.Status==1){
						$('#myModal3').modal('hide');
						$("#ErrorMessage3").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
						getQueryListForGroup();
					}
				}
			});
			
		}
	
		function addPICForGroup()
		{
			$('#myModal3').modal('hide');
			$('#myModalForGroup').modal('show');
		}
		
		
		$("#delWeChatContentForGroup").click(function(){
			var t=$("#exampleTableEvents3").bootstrapTable("getSelections")[0];
			
				if(t!=undefined)
				{
					console.log(t['pkid']);
				$.ajax({
					url : "<%=path%>/WeChatCustom/delWeChatContent",
					type : "get",
					data : {
						'id':t['pkid']
					}, 
					success : function(obj) {
						if(obj.Status==-1){
							$("#ErrorMessage3").text("删除失败").removeClass("alert-success").addClass("alert alert-warning");
						}else if(obj.Status==1){
							$("#ErrorMessage3").text('删除成功!').removeClass("alert-warning").addClass("alert alert-success");
							getQueryListForGroup();
						}
					}
				});
				}else
				{
					$("#ErrorMessage4").text("请选中行操作！").removeClass("alert-success").addClass("alert alert-warning");
				}
			
		})
		
		
		$("#addNewsPicForGroup").click(function(){
			$("#numb").val(generateMixed());
			$('#myModal3').modal('hide');
			$("#myModalnewGroup").modal("show");
		})
		
		
	</script>

		<script type="text/javascript">
	/*图片选择 3
	*/
	var DataTable=$("#exampleTableEventsgroup");
	!function(e, t, o) {
		"use strict";
		!
		function() {
			DataTable.bootstrapTable({
				url : "<%=path%>/ProdPicture/getProdPictureList?sina=<%= SmBaseUtil.Random() %>",
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				formatSearch:function(){return "请输入图片名称"},
				sidePagination : "server", //服务端请求
				queryParams : queryParams,
				responseHandler : responseHandler,
				striped : !0, //是否显示行间隔色
				clickToSelect:true,
				
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbargroup",
				icons : geticons

			});
			//	var e = o("#WeChatGroupEventsResult");
			DataTable.on("dbl-click-row.bs.table",
							function(e, t, o) {
								
							});
			DataTable.on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			});
			
		}()
	}(document, window, jQuery);
	
	
	function getQueryList(searchText){
		
		if(searchText==null || searchText==undefined){
			searchText="";
		}
			DataTable.bootstrapTable("refresh", {
				 url : "<%=path%>/ProdPicture/getProdPictureList?sina=<%= SmBaseUtil.Random() %>&check="+encodeURI(encodeURI(searchText))
			});
	}
	function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNumber : params.offset
		};

	}

	$("#addShangPICGroup").click(function(){
		
		if(CheckhasChecked($("#exampleTableEventsgroup"))==true){
			var t=$("#exampleTableEventsgroup").bootstrapTable("getSelections")[0];
			$.ajax({
				url : "<%=path%>/WeChatCustom/GroupSend",
				type : "post",
				data : {
					'type':'image',
					'pic':"<%=rootPath%>"+t['url'],
					'appid':$("#appid").val(),
					'appsecret':$("#appsecret").val()
				}, 
				success : function(obj) {
					if(obj.Status==-1){
						$("#ErrorMessagegroup").text(obj.ErrorMsg).removeClass("alert-success").addClass("alert alert-warning");
					}else if(obj.Status==1){
						$('#myModalForGroup').modal('hide');
						$("#ErrorMessage3").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
						getQueryContentListt();
					}
				}
			});
				
		}else
		{
			$("#ErrorMessagegroup").text("请选中要上传的图片再操作！"+"您所设置的关键字为："+$("#keym").val()).removeClass("alert-success").addClass("alert alert-warning");
		}
	})
	function getQueryContentListt(){
		$("#exampleTableEvents").bootstrapTable("refresh", {
					 url : "/WeNewsAgency/WeChatCustom/getWeChatContentList?sina=<%= SmBaseUtil.Random() %>"
		});
	
	}
	</script>
		<script type="text/javascript">
	/*图文选择 3
	*/
	var GroupSize=0;
	var DataTable=$("#exampleTableEventsnewGroup");
	!function(e, t, o) {
		"use strict";
		!
		function() {
			DataTable.bootstrapTable({
				url : "<%=path%>/Notices/getNoticesList?sina=<%= SmBaseUtil.Random() %>&type=2",
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				formatSearch:function(){return "请输入图片名称"},
				sidePagination : "server", //服务端请求
				queryParams : queryParams,
				responseHandler : responseHandler,
				striped : !0, //是否显示行间隔色
				clickToSelect:true,
				
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbarnewGroup",
				icons : geticons

			});
			//	var e = o("#WeChatGroupEventsResult");
			DataTable.on("dbl-click-row.bs.table",
							function(e, t, o) {
				$('#myModalnewGroup').modal('hide');
				shade.style.display='block';
				Layer1.style.display='block';
					var pid=t["pkid"];
				var areaID=t["areaID"]; 
				var liulan="<iframe id='frame' width='100%' height='100%' src=<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+pid+"&_pc_=&_area_="+areaID+"&_status_="+t["status"]+"></iframe>";
				$("#liulan").append(liulan);
				document.getElementById("body").style.overflow="hidden";
							});
			DataTable.on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			});
			
			
		}()
	}(document, window, jQuery);
	
	
	function getQueryList(searchText){
		
		if(searchText==null || searchText==undefined){
			searchText="";
		}
			DataTable.bootstrapTable("refresh", {
				 url : "<%=path%>/Notices/getNoticesList?sina=<%= SmBaseUtil.Random() %>&type=2?check="+encodeURI(encodeURI(searchText))
			});
	}
	function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNumber : params.offset
		};

	}

	$("#addShangNewGroup").click(function(){
	
		if(CheckhasChecked($("#exampleTableEventsnewGroup"))==true){
			var t=$("#exampleTableEventsnewGroup").bootstrapTable("getSelections")[0];
			$.ajax({
				url : "<%=path%>/WeChatCustom/GroupSend",
				type : "post",
				data : {
					'id':t['pkid'],
					'numb':$("#numb").val(),
					'wid':$("#wid").val(),
					'type':"mpnews",
					'author':t['author'],
					'title':t['title'],
					'content_source_url':"<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+t['pkid'],
					'tucontent':t['content'],
					'digest':t['content'].length>=15?t['content'].substring(0,15):t['content'].substring(0,t['content'].length),
					'appid':$("#appid").val(),
					'appsecret':$("#appsecret").val()
				}, 
				success : function(obj) {
					if(obj.Status==-1){
						$("#ErrorMessagenewGroup").text(obj.ErrorMsg).removeClass("alert-success").addClass("alert alert-warning");
					}else if(obj.Status==1){
							GroupSize++;ShowViewGroup
							
							ShowViewGroup();
							$("#ErrorMessagenewGroup").text('处理成功!您已上传'+GroupSize+'片图文,还可以继续上传'+(8-GroupSize)+'片图文').removeClass("alert-warning").addClass("alert alert-success");
							getQueryContentListt();
						
					}
				}
			});
				
		}else
		{
			$("#ErrorMessagenewGroup").text("请选中要群发的图文再操作！").removeClass("alert-success").addClass("alert alert-warning");
		}
		
		//	$("#ErrorMessagenewGroup").text('您已上传了8片图文，无法继续上传！').removeClass("alert-warning").addClass("alert alert-success");
		
	})
	
	$("#sendShangNewGroup").click(function(){
		$.ajax({
			url : "<%=path%>/WeChatCustom/SendGroupSend",
			type : "Get",
			data : {
				'numb':$("#numb").val(),
				'appid':$("#appid").val(),
				'appsecret':$("#appsecret").val()
			}, 
			success:function(obj)
			{
				if(obj.Status==-1)
				{
					$("#ErrorMessagenewGroup").text(obj.message).removeClass("alert-success").addClass("alert alert-warning");
				}else{
					$("#ErrorMessage3").text("群发成功").removeClass("alert-success").addClass("alert alert-warning");
				}
			}
		});
	})
	function getQueryContentListt(){
		$("#exampleTableEvents").bootstrapTable("refresh", {
					 url : "/WeNewsAgency/WeChatCustom/getWeChatContentList?sina=<%= SmBaseUtil.Random() %>"
		});
	
	}
	</script>




		<script type="text/javascript">
	/*4*/
	var DataTable=$("#exampleTableEvents");
		!function(e, t, o) {
			"use strict";
			!
			function() {
				DataTable.bootstrapTable({
					url : "/WeNewsAgency/WeChatCustom/getWeChatContentList?sina=<%= SmBaseUtil.Random() %>",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "请输入关键字"},
					sidePagination : "server", //服务端请求
					queryParams : queryParams,
					responseHandler : responseHandler,
					striped : !0, //是否显示行间隔色
					clickToSelect:true,
					
					iconSize : "outline",
					toolbar : "#exampleTableEventsToolbar",
					icons : geticons
	
				});
				//	var e = o("#WeChatGroupEventsResult");
				DataTable.on("dbl-click-row.bs.table",
								function(e, t, o) {
									
								});
				DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList4(t);
				});
				
			}()
		}(document, window, jQuery);
		
		function getQueryList4(searchText){
			
			if(searchText==null || searchText==undefined){
				searchText="";
			}
				$("#exampleTableEvents").bootstrapTable("refresh", {
					 url : "/WeNewsAgency/WeChatCustom/getWeChatContentList?sina=<%= SmBaseUtil.Random() %>&check="+encodeURI(encodeURI(searchText))
				});
		}
		function queryParams(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset
			};

		}
		$("#addWeChatContentView").click(function()
				{
					$('#myModal').modal('show');
				});
		
	
		$("#contentForm").validate({
			rules : {
				keym:{
					required : !0
				}
			},
			messages : {
				keym:{
					required : "关键词不能为空"
				
				}
			}
		});
		
		function addWeContentText()
		{
			if(!$("#contentForm").validate().form()){
				return 
			}
			$.ajax({
				url : "<%=path%>/WeChatCustom/Rules?sina=<%= SmBaseUtil.Random() %>",
				type : "get",
				data : {
					'content':encodeURI($("#content").val()),
					'rules':'text',
					'key':encodeURI($("#keym").val()),
					'appid':$("#appid").val(),
					'appsecret':$("#appsecret").val(),
					'wid':$("#wid").val()
					
				}, 
				success : function(obj) {
					if(obj.Status==-1){
						$("#ErrorMessagem").text(obj.ErrorMsg).removeClass("alert-success").addClass("alert alert-warning");
					}else if(obj.Status==1){
						$('#myModal').modal('hide');
						$("#ErrorMessage4").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
						getQueryList4();
					}
				}
			});
			
		}
	
		function addPIC()
		{
			if(!$("#contentForm").validate().form()){
				return 
			}
			$('#myModal').modal('hide');
			$('#myModal1').modal('show');
			$("#ErrorMessagepic").text("您所设置的关键字为："+$("#keym").val()).removeClass("alert-warning").addClass("alert alert-success");
		}
		
		
		$("#delWeChatContent").click(function(){
			var t=$("#exampleTableEvents").bootstrapTable("getSelections")[0];
			
				if(t!=undefined)
				{
					console.log(t['pkid']);
				$.ajax({
					url : "<%=path%>/WeChatCustom/delWeChatContent",
					type : "get",
					data : {
						'id':t['pkid']
					}, 
					success : function(obj) {
						if(obj.Status==-1){
							$("#ErrorMessage4").text("删除失败").removeClass("alert-success").addClass("alert alert-warning");
						}else if(obj.Status==1){
							$("#ErrorMessage4").text('删除成功!').removeClass("alert-warning").addClass("alert alert-success");
							getQueryContentListt();
						}
					}
				});
				}else
				{
					$("#ErrorMessage4").text("请选中行操作！").removeClass("alert-success").addClass("alert alert-warning");
				}
			
		})
		$("#addNewsPic2").click(function()
		{
			
			if(!$("#contentForm").validate().form()){
				return 
			}
			$('#myModal').modal('hide');
			$('#myModalnew4').modal('show');
			$("#ErrorMessagenew4").text("您所设置的关键字为："+$("#keym").val()).removeClass("alert-warning").addClass("alert alert-success");
		})
		function ShowView(){
			$("#tishi").show();
			$("#showview").show();
			$.ajax({
				url : "<%=path%>/WeChatCustom/getWeChatJson?sina=<%= SmBaseUtil.Random() %>",
				type : "Get",
				data : {
					'key':encodeURI(encodeURI($("#keym").val())),
					'wid':$("#wid").val()
				}, 
				success:function(obj)
				{
					if(obj.Status==1)
					{
						var output2 ='';
						var datav=obj.data;
						var dataa=obj.title;
						var lengths=obj.size;
						 function b(item)
						 {
							var html;
							if(lengths==1)
							{
								html='<p>'+item.title+'</p>';
								html+='<p style="font-size:8px;">'+item.time+'</p>';
								html+='<img src="'+item.imgurl+'" style="width:208px;height: 100px; " />';
								html+='<p>'+item.description+'</p>';
							}else{
								if (item.title==dataa) {
									console.log(item.title);
									html='<div><img alt="" src="'+item.imgurl+'" style="width:208px;height: 100px; " >'
									html+='<div style="color: #fffcfc;margin-top: -18px;background-color: rgba(14, 12, 12, 0.71);position: absolute;width: 78.7%;">'+item.title+'</div></div>';
								}else{
							 	html="<div style='padding: 3px 0;border-bottom: 1px solid rgba(82, 69, 69, 0.32);'>";
							 	html+='<span style="float:right;"><img src="'+item.imgurl+'" style="width:40px;height: 40px; "/></span>';
								html+='<span style="line-height:3">'+item.title+'</span>';
							 	html+='</div>';}
							}
						 	return html;
						 }
						 $.each(JSON.parse(datav), function (index, item) {
						     output2 += b(item);
						 });
						 $("#dataout").html(output2);
					}
				}
			});					 			
		}

		
	function ShowViewGroup(){
			$("#tishiGroup").show();
			$("#showviewGroup").show();
				$.ajax({
					url : "<%=path%>/WeChatCustom/getWeChatJson?sina=<%= SmBaseUtil.Random() %>",
					type : "get",
					data : {
						'key':encodeURI(encodeURI($("#numb").val())),
						'wid':$("#wid").val()
					}, 
					success : function(obj) {
						if(obj.Status==1){
							
							var output2 ='';
							var datav=obj.data;
							var dataa=obj.title;
							var lengths=obj.size;
							 function b(item)
							 {
								var html;
								
								if(lengths==1)
								{
									html='<p>'+item.title+'</p>';
									html+='<p style="font-size:8px;">'+item.time+'</p>';
									html+='<img src="'+item.imgurl+'" style="width:208px;height: 100px; " />';
									html+='<p>'+item.description+'</p>';
								}else{
									if (item.title==dataa) {
								 		console.log(item.title);
										html='<div><img alt="" src="'+item.imgurl+'" style="width:208px;height: 100px; " >'
										html+='<div style="color: #fffcfc;margin-top: -18px;background-color: rgba(14, 12, 12, 0.71);position: absolute;width: 78.7%;">'+item.title+'</div></div>';
									}else{
								 	html="<div style='padding: 3px 0;border-bottom: 1px solid rgba(82, 69, 69, 0.32);'>";
								 	html+='<span style="float:right;"><img src="'+item.imgurl+'" style="width:40px;height: 40px; "/></span>';
									html+='<span style="line-height:3">'+item.title+'</span>';
								 	html+='</div>';}
								}
							 	return html;
							 }
							 $.each(JSON.parse(datav), function (index, item) {
							     output2 += b(item);
							 });
							 $("#dataoutGroup").html(output2);
						}
					}
				});
			 
		 
			 
		
		}
	</script>
		<script type="text/javascript">
	/*图片选择 4
	*/
	var DataTable=$("#exampleTableEventspic");
	!function(e, t, o) {
		"use strict";
		!
		function() {
			DataTable.bootstrapTable({
				url : "<%=path%>/ProdPicture/getProdPictureList?sina=<%= SmBaseUtil.Random() %>",
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				formatSearch:function(){return "请输入图片名称"},
				sidePagination : "server", //服务端请求
				queryParams : queryParams,
				responseHandler : responseHandler,
				striped : !0, //是否显示行间隔色
				clickToSelect:true,
				
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbarpic",
				icons : geticons

			});
			//	var e = o("#WeChatGroupEventsResult");
			DataTable.on("dbl-click-row.bs.table",
							function(e, t, o) {
								
							});
			DataTable.on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			});
			
		}()
	}(document, window, jQuery);
	
	
	function getQueryList(searchText){
		
		if(searchText==null || searchText==undefined){
			searchText="";
		}
			DataTable.bootstrapTable("refresh", {
				 url : "<%=path%>/ProdPicture/getProdPictureList?sina=<%= SmBaseUtil.Random() %>&check="+encodeURI(encodeURI(searchText))
			});
	}
	function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNumber : params.offset
		};

	}

	$("#addShangPIC").click(function(){
		if(CheckhasChecked($("#exampleTableEventspic"))==true){
			var t=$("#exampleTableEventspic").bootstrapTable("getSelections")[0];
			$.ajax({
				url : "<%=path%>/WeChatCustom/Rules?sina=<%= SmBaseUtil.Random() %>",
				type : "get",
				data : {
					'pic':"<%=rootPath%>"+t['url'],
					'key':encodeURI($("#keym").val()),
					'rules':'image',
					'appid':$("#appid").val(),
					'appsecret':$("#appsecret").val(),
					'wid':$("#wid").val()
				}, 
				success : function(obj) {
					if(obj.Status==-1){
						$("#ErrorMessagepic").text(obj.ErrorMsg).removeClass("alert-success").addClass("alert alert-warning");
					}else if(obj.Status==1){
						$('#myModal1').modal('hide');
						$("#ErrorMessage4").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
						getQueryContentListt();
					}
				}
			});
				
		}else
		{
			$("#ErrorMessagepic").text("请选中要上传的图片再操作！"+"您所设置的关键字为："+$("#keym").val()).removeClass("alert-success").addClass("alert alert-warning");
		}
	})
	function getQueryContentListt(){
		$("#exampleTableEvents").bootstrapTable("refresh", {
					 url : "/WeNewsAgency/WeChatCustom/getWeChatContentList?sina=<%= SmBaseUtil.Random() %>"
		});
	
	}

	</script>

		<script type="text/javascript">
	/*图文选择 4
	*/
	var DataTable=$("#exampleTableEventsnew4");
	!function(e, t, o) {
		"use strict";
		!
		function() {
			DataTable.bootstrapTable({
				url : "<%=path%>/Notices/getNoticesList?sina=<%= SmBaseUtil.Random() %>&type=2",
				search : !0,
				pagination : !0,
				showRefresh : !0,
				showToggle : !0,
				showColumns : !0,
				formatSearch:function(){return "请输入图片名称"},
				sidePagination : "server", //服务端请求
				queryParams : queryParams,
				responseHandler : responseHandler,
				striped : !0, //是否显示行间隔色
				clickToSelect:true,
				
				iconSize : "outline",
				toolbar : "#exampleTableEventsToolbarnew4",
				icons : geticons

			});
			//	var e = o("#WeChatGroupEventsResult");
			DataTable.on("dbl-click-row.bs.table",
							function(e, t, o) {
								$('#myModalnew4').modal('hide');
								shade.style.display='block';
								Layer1.style.display='block';
									var pid=t["pkid"];
								var areaID=t["areaID"]; 
								var liulan="<iframe id='frame' width='100%' height='100%' src=<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+pid+"&_pc_=&_area_="+areaID+"&_status_="+t["status"]+"></iframe>";
								$("#liulan").append(liulan);
								document.getElementById("body").style.overflow="hidden";
								
							});
			DataTable.on("search.bs.table", function(e, t, o) {
				getQueryList(t);
			});
			
		}()
	}(document, window, jQuery);
	
	function closeHtml()
	{
		var	actices=$("#a1").attr("class")=="active"?1:($("#a2").attr("class")=="active"?2:($("#a3").attr("class")=="active"?3:($("#a4").attr("class")=="active"?4:"在做其他判断")));
		console.log(actices);
		shade.style.display='none';
		Layer1.style.display='none';
		$("#liulan").html("");
		document.getElementById("body").style.overflow="auto";
		if(actices==4){
		$('#myModalnew4').modal('show');}
		if(actices==3){
			$('#myModalnewGroup').modal('show');}
	}
	function getQueryList(searchText){
		
		if(searchText==null || searchText==undefined){
			searchText="";
		}
			DataTable.bootstrapTable("refresh", {
				 url : "<%=path%>/Notices/getNoticesList?sina=<%= SmBaseUtil.Random() %>&type=2?check="+encodeURI(encodeURI(searchText))
			});
	}
	function queryParams(params) {
		return {
			pageSize : params.limit,
			pageNumber : params.offset
		};

	}
	var i=0;
	$("#addShangNew4").click(function(){
		if(CheckhasChecked($("#exampleTableEventsnew4"))==true){
			var t=$("#exampleTableEventsnew4").bootstrapTable("getSelections")[0];
			var image=t['image']!=null?t['image'].url:'upload/2a27c63a-06f7-41b5-97fa-97f3ca35527f.JPG';
			image="<%=rootPath%>"+image;
			key=$("#keym").val()==""?$("#key").val():$("#keym").val();
			$.ajax({
				url : "<%=path%>/WeChatCustom/Mpnews?sina=<%= SmBaseUtil.Random() %>",
				type : "get",
				data : {
					'title':encodeURI(t['title']),
					'description':encodeURI(t['content'].length>=15?t['content'].substring(0,15):t['content'].substring(0,t['content'].length)),
					'key':encodeURI(key),
					'picurl':image,
					'url':"<%=basePath%>Product/phoneweChatPordDetail?_type_=2&_pid_="+t['pkid'],
					'appid':$("#appid").val(),
					'appsecret':$("#appsecret").val(),
					'id':t['pkid'],
					'wid':$("#wid").val()
				}, 
				success : function(obj) {
					if(obj.Status==-1){
						$("#ErrorMessagenew4").text(obj.ErrorMsg).removeClass("alert-success").addClass("alert alert-warning");
					}else if(obj.Status==1){
					//	$('#myModalnew4').modal('hide');
						ShowView();
						i++;
						console.log(i);
						if(i<8)
						{
							$("#ErrorMessagenew4").text('处理成功!您所设置的关键字为：'+$("#keym").val()+"一共可上传8条，您还可以上传"+(8-i)+"条！如果你不想上传继续请关闭！").removeClass("alert-warning").addClass("alert alert-success");
							getQueryContentListt();
						}else
						{
							$('#myModalnew4').modal('hide');
							$("#ErrorMessage4").text('处理成功!').removeClass("alert-warning").addClass("alert alert-success");
							i==0;
							getQueryContentListt();
						}
					}
				}
			});
				
		}else
		{
			$("#ErrorMessagenew4").text("请选中要上传的图文再操作！"+"您所设置的关键字为："+$("#keym").val()).removeClass("alert-success").addClass("alert alert-warning");
		}
	})
	function getQueryContentListt(){
		$("#exampleTableEvents").bootstrapTable("refresh", {
					 url : "/WeNewsAgency/WeChatCustom/getWeChatContentList?sina=<%= SmBaseUtil.Random() %>"
		});
	
	}


	$("#yulan").click(function()
	{
		if(CheckhasChecked(DataTable)==true)
		{
			
		}else
		{
			$("#ErrorMessagenew4").text("请选中要上传的图文再操作！"+"您所设置的关键字为："+$("#keym").val()).removeClass("alert-success").addClass("alert alert-warning");
		}
	})
	</script>

		<script type="text/javascript">
	var c=0;//控制主菜单条数
	
	 $(document).ready(function(){
		 
		   if($("#IsSame").val()==-1)
		    {
		    	swal({
					title : "提示",
					text : "该公众号仅限自己操作，您无法对其操作！"
				});
		    	  $('input').attr("disabled","disabled");//将input元素设置为disabled
		    	  $('.btn').attr("disabled","disabled");
		    }
		 $(".i-checks").iCheck({
			 checkboxClass:"icheckbox_square-green",
			 radioClass:"iradio_square-green",});
	 
		 
			var obj = '<%=a%>';

		    var output = '';
		    function buildItem(item) {
		        var html ;
		        html ="<li style='width: 0px;'class='dd-item' data-id='" + item.id + "'>";
		        if($("#IsSame").val()==1){
		        if(item.children)
			        {
		        		c++
			        	 html += "<div style='width: 400px;'><div style='width: 300px;float:left; ' id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div ><div style='float:left;line-height:40px;'><input id="+item.id+" style='width: 50px;' type='button' data-action='tiejia'  class='btn btn-outline btn-primary' value='添加' /><input type='button' id="+item.id+" style='width: 50px;height:32px;' data-action='delete' class='btn btn-outline btn-danger ' value='删除'/></div></div>";
			        }else
			        {
			        	html +="<div style='width: 370px; '><div style='width: 320px;float:left; ' id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div><div style='float:left;line-height:40px;'><input style='width: 50px;' type='button' id="+item.id+" data-action='delete' class='btn btn-outline btn-danger' value='删除'/></div></div>";
			        }
		        }else
		        {
		        	 if(item.children)
				        {
		        		 html += "<div style='width: 350px;float:left; ' id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div >";
				        }else
				        {
				        	html +="<div style='width: 320px;float:left; ' id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div>";
				        }
		        }
		        if (item.children) {
		        	 html += "<ol class='dd-list' id="+item.id+'ol'+">";
		            $.each(item.children, function (index, sub) {
		                html += buildItem(sub);
		            });
		            html += "</ol>";
		        }
		       
		        html += "</li>";
		        return html;
		    }

		    $.each(JSON.parse(obj), function (index, item) {

		        output += buildItem(item);

		   });

		    $('.dd').on('click', function(e)
		    	    {
		    	        var target = $(e.target),
		    	            action = target.data('action');
		    	       
		    	        if (action === 'tiejia') {
		    	           if($(e.target).attr("id")!='')
		    	           {
		    	        	   target.parent().parent().next().append("<li style='width: 0px;' ParentID="+(e.target).id+" class='dd-item' ><div style='width: 370px; '><div style='width: 320px;float:left; ' ParentID="+(e.target).id+" name='新建子菜单' class='dd-handle'>新建子菜单</div><div style='float:left;line-height:40px;'><input style='width: 50px;' type='button' data-action='delete' class='btn btn-outline btn-danger' value='删除'></div></div></li>");
		    	           }else
		    	           {
		    	        	   target.parent().parent().next().append("<li style='width: 0px;' class='dd-item' ><div style='width: 370px; '><div style='width: 320px;float:left; ' class='dd-handle'>新建子菜单</div><div style='float:left;line-height:40px;'><input style='width: 50px;' type='button' data-action='delete' class='btn btn-outline btn-danger' value='删除'></div></div></li>");
		    	           }
		    	        }
		    	        if(action==="delete")
		    	        {
		    	        	$("#loading").css("display","block");
		    	        	$("#ErrorMessage2").text("正在删除。。。请勿其他操作").removeClass("alert-success").addClass("alert alert-warning");
		    	        	$(e.target).parent().parent().parent().remove();
		    	        	if(c!=1)
		    	        	{
		    	        		$.ajax({
									url:"<%=path%>/WeChatCustom/deleteWeChat",
									data:{
										'id':target.attr("id")
									 },
									 success : function(obj) {
										 if (obj.Status==1) {
											c=c-1;
											console.log(c);
										}
										 if(obj.Status==-1)
										 {
											 console.log("您正在删除子菜单");
										 }
										 send()
									 }
								 });
		    	        	}else
		    	        	{
		    	        		console.log("c=1啦啊");
		    	        		$.ajax({
									url:"<%=path%>/WeChatCustom/deleteWeChatALL",
									data:{
										'id':target.attr("id"),
										'appid':$("#appid").val(),
										'appsecret':$("#appsecret").val()
									 },
									 success : function(obj) {
										 if(obj.Status==1)
										 {
											 c=c-1;
											 console.log(c);
											 $("#loading").css("display","none");
											 $("#ErrorMessage2").text("当前菜单为空").removeClass("alert-success").addClass("alert alert-warning");
											
										 }
										
									 }
								 });
		    	        	}
		    	        	
		    	        	
		    	        	
		    	        }
		    	        
		    	    	var name=target.attr("name");
						var id=target.attr("id");
						var type=target.attr("type");
						var url=target.attr("url");
						var key=target.attr("key");
						var mgroup=target.attr("mgroup");
						var parentid=target.attr("parentid");
						$("#caidan").val(name);
						$("#id").val(id);
						$("#mgroup").val(mgroup);
						$("#parentid").val(parentid);
					
						if(key!=null||key!=undefined)
						{
							$("#button_type").val(type);
							$("#key").val(key);
							if(type=="view")
							{
								$("#key").val(url);
							}
						}
		    	        
		    	        
		    	        
		    	    });
		    $('#dd-empty-placeholder').html(output);
		    
		    $('#nestable3').nestable();
		    
		    
		 
		    
		    
	 
	 });


function shuaxin()
{
	
	$.ajax({
		url:"<%=path%>/WeChatCustom/getJson?sina=<%= SmBaseUtil.Random() %>",
		data:{
			'appid':$("#appid").val(),
			'appsecret':$("#appsecret").val()
		 },
		 success : function(obj) {
			 if(obj.Status==1)
			 {
					var mGroup=obj.mGroup;
					var json=obj.menu;
					$("#mGroup").val(mGroup);
				
				    var output = '';
				    function buildItem1(item) {
				        var html ;
				        html ="<li style='width: 0px;'class='dd-item' data-id='" + item.id + "'>";
				     
				        if(item.children)
				        {
				        	 html += "<div style='width: 400px;'><div style='width: 300px;float:left; ' id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div ><div style='float:left;line-height:40px;'><input id="+item.id+" style='width: 50px;' type='button' data-action='tiejia'  class='btn btn-outline btn-primary' value='添加' /><input type='button' id="+item.id+" style='width: 50px;height:32px;' data-action='delete' class='btn btn-outline btn-danger ' value='删除'/></div></div>";
				        }else
				        {
				        	html +="<div style='width: 370px; '><div style='width: 320px;float:left; ' id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div><div style='float:left;line-height:40px;'><input style='width: 50px;' type='button' id="+item.id+" data-action='delete' class='btn btn-outline btn-danger' value='删除'/></div></div>";
				        }
				       
				       
				       
				        if (item.children) {
				        	 html += "<ol class='dd-list' id="+item.id+'ol'+">";
				            $.each(item.children, function (index, sub) {
				                html += buildItem1(sub);
				            });
				            html += "</ol>";
				        }
				        html += "</li>";
				        return html;
				    }

				    $.each(JSON.parse(json), function (index, item) {
						
				        output += buildItem1(item);

				   });
					  
					  $('#dd-empty-placeholder').html(output);
					  $('#nestable3').nestable();
					  $("#loading").css("display","none");
				 
			 }
			
		 }
	 });
}
	 
		$('.dd').nestable({
		    onDragStart: function(l,e){
		    	
				var name=e.context.getAttribute("name");
				var id=e.context.getAttribute("id");
				var type=e.context.getAttribute("type");
				var url=e.context.getAttribute("url");
				var key=e.context.getAttribute("key");
				var mgroup=e.context.getAttribute("mgroup");
				var parentid=e.context.getAttribute("parentid");
				$("#caidan").val(name);
				$("#id").val(id);
				$("#mgroup").val(mgroup);
				$("#parentid").val(parentid);
			
				if(key!=null||key!=undefined)
				{
					$("#button_type").val(type);
					$("#key").val(key);
					if(type=="view")
					{
						$("#key").val(url);
					}
				}
		    }
		});
		
		 function addView()
		 {
			 
			 if(c<3)
			 {
				 $("#loading").css("display","block");
				 $("#ErrorMessage2").text("正在增加主菜单,请勿其他操作").removeClass("alert-success").addClass("alert alert-warning");
				 if($("#mGroup").val()!='')
				 {
						$.ajax({
							url:"<%=path%>/WeChatCustom/saveWeChatInfo",
							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							data:{
								'mgroup':$("#mGroup").val(),
								'name': encodeURI("新建菜单"),
								'type':"view",
								'key':'http://www.baidu.com'
							 },				 
							 success : function(obj) {
								 if(obj.Stauts==1)
								 {
									 $("#dd-empty-placeholder").append("<li style='width: 0px;' class='dd-item' ><div style='width: 400px;'><div style='width: 300px;float:left; ' class='dd-handle' name='新建菜单' mGroup="+$("#mGroup").val()+">新建菜单</div><div style='float:left;line-height:40px;'><input style='width: 50px;' type='button' data-action='tiejia' class='btn btn-outline btn-primary'  mGroup="+$("#mGroup").val()+" value='添加'><input type='button' style='width: 50px;height:32px;' data-action='delete' class='btn btn-outline btn-danger ' value='删除'></div></div><ol mGroup="+$("#mGroup").val()+" class='dd-list' ></ol></li>");
									 $("#mGroup").val(obj.mGroup);
									 c++
									 console.log(c);
									 send();
								 }
							 }
							 
						 });
				 }else
				 {
					 $("#loading").css("display","block");
					 $("#dd-empty-placeholder").append("<li style='width: 0px;' class='dd-item' ><div style='width: 400px;'><div style='width: 300px;float:left; ' class='dd-handle'>新建菜单</div><div style='float:left;line-height:40px;'><input style='width: 50px;' type='button' data-action='tiejia' class='btn btn-outline btn-primary' value='添加'><input type='button' style='width: 50px;height:32px;' data-action='delete' class='btn btn-outline btn-danger ' value='删除'></div></div><ol class='dd-list' ></ol></li>");
						$.ajax({
							url:"<%=path%>/WeChatCustom/saveWeChatInfo",
							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							data:{
								'name': encodeURI("新建菜单"),
								'type':"view",
								'key':'http://www.baidu.com'
							 },				 
							 success : function(obj) {
								 if(obj.Stauts==1)
								 {
									 $("#mGroup").val(obj.mGroup);
									 c++
									 console.log(c);
									 send();
								 }
								
								
							 }
							 
						 });
				 
				 }
			
			 }else
			 {
				 $("#ErrorMessage2").text("增加主菜单失败，因为主菜单最多3个").removeClass("alert-warning").addClass("alert alert-success");
			 }
		 }

	 var save=function(){
		 var aHTML=$(".click2edit").code();
		 $(".click2edit").destroy()
		 };

		    $(document).ready(function () {

		        var nav4 = (function () {

		            bindClick = function (els, mask) {
		                if (!els || !els.length) {
		                    return;
		                }
		                var isMobile = "ontouchstart" in window;
		                for (var i = 0, ci; ci = els[i]; i++) {
		                    ci.addEventListener("click", evtFn, false);
		                }
		                function evtFn(evt, ci) {
		                    ci = this;
		                    for (var j = 0, cj; cj = els[j]; j++) {
		                        if (cj != ci) {
		                            console.log(cj);
		                            cj.classList.remove("on");
		                        }
		                    }
		                    if (ci == mask) {
		                        mask.classList.remove("on");
		                        return;
		                    }
		                    switch (evt.type) {
		                        case "click":
		                            var on = ci.classList.toggle("on");
		                            mask.classList[on ? "add" : "remove"]("on");
		                            break;
		                    }
		                }
		                mask.addEventListener(isMobile ? "touchstart" : "click", evtFn, false);
		            };
		            return {"bindClick": bindClick};
		        })();
		        nav4.bindClick(document.getElementById("weixin_nav4_ul").querySelectorAll("li>a"), document.getElementById("weixin_nav4_masklayer"));
		    });	
		    
		    
			function subscribe() 
			{
				$.ajax({
						url : "<%=path%>/WeChatCustom/Subscribe",
				type : "get",
				data : {
					'content' : encodeURI($('#AutoContent').val()),
					'AppID' : $('#appid').val(),
					'wid':$("#wid").val()
				},
				success : function(obj) {
					if (obj.Status == 1) {
						swal({
							title : "提示",
							text : "添加回复成功！"
						});

					} else {
						swal({
							title : "提示",
							text : "添加失败，请检查内容是否符合规范，是否为空？"
						});
					}
				}
			});
		}
			
			function save1()
			 {
				
				 $("#ErrorMessage2").text("正在保存中。。。。请稍等，请勿操作").removeClass("alert-success").addClass("alert alert-warning");
				var type=$("#button_type").val()
				var mgroup=$("#mgroup").val();
				var ParentID=$("#parentid").val();
				var id=$("#id").val();
				console.log(ParentID);
				if(($("#mgroup").val()=='') && ($("#id").val()=='')&&(ParentID==''))
				{
					console.log("mgroup=0and id=0");
					$("#loading").css("display","block");
					$.ajax({
						url:"<%=path%>/WeChatCustom/saveWeChatInfo",
						contentType:'application/x-www-form-urlencoded; charset=UTF-8',
						data:{
							'name': encodeURI($("#caidan").val()),
							'type':type,
							'key':$("#key").val()
						 },				 
						 success : function() {
							 send();
						 }
					 });
				}else{
				if(ParentID!='')
				{
					$("#loading").css("display","block");
					console.log("ParentID");
					addSun(type,ParentID)
				}
				if(mgroup!="")
				{
					$("#loading").css("display","block");
					console.log(mgroup);
					$.ajax({
						url:"<%=path%>/WeChatCustom/saveWeChatInfo",
						contentType:'application/x-www-form-urlencoded; charset=UTF-8',
						data:{
							'mgroup':$("#mgroup").val(),
							'name': encodeURI($("#caidan").val()),
							'type':type,
							'key':$("#key").val()
						 },success : function() {
							 send();
						 }			 
					 });
					
				}
				if(id!=''){
					if (type=='0'||type=='' || type==null) 
					{
						$("#loading").css("display","block");
						console.log("1");
						$.ajax({
							url:"<%=path%>/WeChatCustom/saveWeChatInfo",
							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							data:{
								'id':id,
								'name': encodeURI($("#caidan").val()),
								'v':Math.random()
							 }, success : function() {
								 send();
							 }
							
						 });
					}else
					{
						if(type=="click")
						{
							$("#loading").css("display","block");
							console.log("2");
							$.ajax({
								url:"<%=path%>/WeChatCustom/saveWeChatInfo",
								contentType:'application/x-www-form-urlencoded; charset=UTF-8',
								data:{
									'id':id,
									'name': encodeURI($("#caidan").val()),
									'type':'click',
									'key':$("#key").val()
								 },
								 success : function() {
									 addWeChatContent();
									 
								 }
							 });
						}
						if(type=="view")
						{
							$("#loading").css("display","block");
							console.log("3");
							$.ajax({
								url:"<%=path%>/WeChatCustom/saveWeChatInfo",
								contentType:'application/x-www-form-urlencoded; charset=UTF-8',
								data:{
									'id':id,
									'name': encodeURI($("#caidan").val()),
									'type':'view',
									'key':$("#key").val()
								 },
								 success : function() {
									 addWeChatContent();
									 
								 }
							 });
						}
						
					}
				
				}
				}}
			 function addSun(type,ParentID)
			 {
					if(type=="click")
					{
						$.ajax({
							url:"<%=path%>/WeChatCustom/addSun",
							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							data:{
								"ParentID":ParentID,
								'name': encodeURI($("#caidan").val()),
								'type':'click',
								'key':$("#key").val()
							 },
							 success : function() {
								 addWeChatContent();
							 }
							 
						 });
					}
					if(type=="view")
					{
						$.ajax({
							url:"<%=path%>/WeChatCustom/addSun",
							contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
							data : {
								'ParentID' : ParentID,
								'name' : encodeURI($("#caidan").val()),
								'type' : 'view',
								'key' : $("#key").val()
							},
							success : function() {
								addWeChatContent();
								
							}
						});
					}
				}
			 function send()
			 {
				$.ajax({
					url:"<%=path%>/WeChatCustom/sendWeChatInfo",
					data:{
						'mGroup':$("#mGroup").val(),
						'appid':$("#appid").val(),
						'appsecret':$("#appsecret").val()
					 },success : function(obj) {
						 if(obj.Status==1)
						 {
							// $("#mGroup").val(obj.mGroup);
							$("#ErrorMessage2").text("操作成功").removeClass("alert-warning").addClass("alert alert-success");
							
						 }else
						 {
							 $("#ErrorMessage2").text("操作失败，请查阅文档，错误码："+obj.error+"！或请刷新界面，在试试").removeClass("alert-success").addClass("alert alert-warning");
							 
						 }
						 shuaxin();
						}
				 });
			 }
			 function addWeChatContent()
			 {
			 	var type=0;
			 	var key=0;
			 	if($("#key").val()==""||$("#key").val()==undefined||$("#key").val()==null)
			 	{
			 		key=$("#subbut").val();
			 	}else
			 	{
			 		key=$("#key").val();
			 	}
			 	type=$("#wenzi").attr("class")=="active"?1:($("#tuwen").attr("class")=="active"?2:"在做其他判断");
			 	if (type==1) {
			 		$.ajax({
			 			url : "addWeChatContent",
			 			type : "get",
			 			data : {
			 				'content':encodeURI($("#text1").val()),
			 				'key':key,
			 				'active':type,
			 				'wid':$("#wid").val()
			 			}, success : function() {
							send();
						}
			 			
			 		});
			 	}
			 	if (type==2) {
			 		$.ajax({
			 			url : "addWeChatContent",
			 			type : "get",
			 			data : {
			 				'key':$("#key").val(),
			 				'active':type,
			 				'title':encodeURI($("#Title").val()),
			 				'description':encodeURI($("#Description").val()),
			 				'picUrl':$("#PicUrl").val(),
			 				'url':$("#Url").val(),
			 				'wid':$("#wid").val()
			 			}, 
			 			 success : function() {
							send();
						}
			 			
			 		});
			 	}
			 	
			 	 
			 }
			 var jschars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
			 function generateMixed() {
			     var res = "";
			     for(var i = 0; i < 15 ; i ++) {
			         var id = Math.ceil(Math.random()*35);
			         res += jschars[id];
			     }
			     return res;
			 }
				$("#contentForm").validate({
					rules : {
						name:{
							required : !0,
							maxlength : 13
						},
						WID:{
							required : !0
						},
						appID : {
							required : !0
			
						},
						appsecret : {
							required : !0
						}
					},
					messages : {
						name:{
							required : "公众号名字不能为空",
							maxlength : "公众号名字不能超过13个字符"
						},
						WID:{
							required : "微信号不能为空"
						},
						appID : {
							required : "公众号appID不能为空"
						},
						appsecret : {
							required : "公众号appsecret不能为空"
						}
					}
				});
			 $("#showModifyView").click(function(){
				 $("#myModalmodify").modal("show");
			 })
			 $("#updataWeChatInfo").click(function(){
				 if(!$("#contentForm").validate().form()){
						return 
					}
					$.ajax({
						url : "/WeNewsAgency/WeChatCustom/updataWeChatInfo",
						data : {
							'id':$("#id").val(),
							'name':encodeURI($("#nameM").val()),
							'wid':$("#WIDM").val(),
							'appID':$("#appIDM").val(),
							'appsecret':$("#appsecretM").val()
						},
						success : function(obj) {
							if(obj.Status==-1){
								alert("修改失败");
								
							}else if(obj.Status==1){
								var url ="<%=path%>/WeChatCustom/WeChatCustomHome?sina=<%= SmBaseUtil.Random() %>";
								window.location.href =url;
								
							}
							
						}
					});
			 })
	</script>
</body>
</html>