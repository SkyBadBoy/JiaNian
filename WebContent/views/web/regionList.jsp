<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*"%>
<%@	page import="wtb.smUtil.SmBaseGlobal" %>
<%@	page import="wtb.core.model.Users"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//response.setContentType("text/xml;charset=UTF-8");
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Users user = (Users) request.getSession().getAttribute("UserName");
	
%>
<html>

<head>

<jsp:include page="/include/commonCss.jsp" />
<style type="text/css">
	            .row{
    margin-left: 0px;
    margin-right: 0px;
    }
    .select{
        margin-right: 15px;
    	margin-left: 15px;
    }
</style>
</head>

<body>
<input type="hidden" id="dizhi"  value="<%=basePath%>"></input>
	<div class="col-lg-12 row">
	<br />
				<ol class="breadcrumb">
					<li><a href="/WeNewsAgency/home" target="_self">主页</a></li>
					<li><strong>地区列表</strong></li>
				</ol>
			</div><div class="container-fluid row">

		<!-- Page Heading -->
		<div class="row">
			<br />
			
			<br />
		</div>
		<div class="row">

			<div class="col-sm-12">
				<!-- Example Events -->
				<div class="example-wrap">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"  onclick="checkCollapse($(this))"
								aria-collapse="false" class="collapsed">查询条件(点击展开)</a>

							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
							<div class="panel-body">
							</div>
						</div>
					</div>
					<div id="url"></div> 
					<div class="example">

						<div id="ErrorMessage" role="alert"></div>
						<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
							role="group">
							<button type="button" onclick="SuccessApply()"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-plus" title="新增"
									aria-hidden="true"></i> 新增
							</button>
							<button type="button" onclick="SuccessApply(2)"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-pencil" title="修改"
									aria-hidden="true"></i> 修改
							</button>
							<button type="button" onclick="deleteWeChat(88)"
								class="btn btn-outline btn-default">
								<i class="glyphicon glyphicon-trash" title="删除"
									aria-hidden="true"></i> 删除
							</button>
<!-- 							<button  type="button" onclick="CopyUrl()" class="btn btn-outline btn-default">
								<i class=" " title="复制地址" aria-hidden="true"></i> 复制地址
							</button>
 -->
						<button  type="button" onclick="SchooLBind()" class="btn btn-outline btn-default">
								<i class=" " title="学校入驻" aria-hidden="true"></i>学校入驻
							</button>
						</div>
						<table id="exampleTableEvents" data-height="<%=SmBaseGlobal.ScreenHeight %>"
							data-mobile-responsive="true">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="Code">地区编号</th>
									<th data-field="Name">地区名称</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- End Example Events -->
			</div>

		</div>
	</div>

	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">地区信息编辑</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form id="contentForm" action="#">
							<span>所属父级:</span><label ID="ParentNote">北京市上海市</label> <input
								type="hidden" id="CurrParentID" name="CurrParentID" /> <input
								type="hidden" id="CurrentID" value="0" name="CurrentID" />
								<input
								type="text" class="form-control" placeholder="请输入地区编号"
								id="AreaCode" name="AreaCode" /> <br/><input
								type="text" class="form-control" placeholder="请输入地区名称"
								id="Content" name="Content" />
						</form>
					</div>
					<br />
					<button id="referWechat" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>
	<script type="text/javascript">
		var DataTable=$("#exampleTableEvents");
		initialRegionQueryInfo($(".panel-body"),'query',1);
/* 	  getCurrentlocation();   */
		!function(e, t, o) {
			"use strict";
			!

			function() {
				DataTable.bootstrapTable({
					url : "/WeNewsAgency/Region/getRegionList",
					search : !0,
					pagination : !0,
					showRefresh : !0,
					showToggle : !0,
					showColumns : !0,
					formatSearch:function(){return "地区编号或者地区名称"},
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
					SuccessApply(2);
								});
				DataTable.on("click-row.bs.table",
								function(e, t, o) 
								{	
									var a=0;
									if($("#AreaID").val()==null)
									{
										a=2;
									}
									if($("#UnitAreaID").val()==null)
									{
										a=1;
									}
									if($(".input-outline").val()!=""){
										a=0;
									}
									$("#url").html($("#dizhi").val()+"weChatGroup/phoneWeNewsHome?_isStu_="+a+"&_paid_="+t['id']);
									
								});
				DataTable.on("search.bs.table", function(e, t, o) {
					getQueryList(t);
				});
				
			}()
		}(document, window, jQuery);

		function modifyProduct() {
			if(CheckhasChecked(DataTable)==true){
				var url='/WeNewsAgency/Region/addRegion?pid=';
				viewOrModifyByPKID(DataTable,url);
			}

		}
		function  SchooLBind(){
			var t=DataTable.bootstrapTable("getSelections")[0];
			
			$.ajax({
				url : "/WeNewsAgency/Region/SchoolBind",
				data : {
					'ID' : t['id']
				},
				success : function() {
					ViewSuccess();
					getQueryList();
					copyToClipboard($("#url").text());
				}
			});
		}
		function deleteWeChat(flag) {
			if(CheckhasChecked(DataTable)==true){
				if(!confirm("您确定要删除该地区,一旦删除,所有与之关联的数据都将变为无效")){
					return false;
				}
				var wids = getRecordPKIDs(DataTable);
				if (wids != '') {
					$.ajax({
						url : "/WeNewsAgency/Region/deleteRegion",
						data : {
							'ID' : wids
						},
						success : function() {
							ViewSuccess();
							getQueryList();
						}
					});
				} 
			}
		}
		function getQueryList(searchText){
			DataTable.bootstrapTable("refresh", {
				 url : "/WeNewsAgency/Region/getRegionList"
			});
		}
		function queryParams(params) {
			var AreaObj=getAreaObj();
			var temp=MakeQueryParamJson($(".panel"));
			temp.limit=params.limit;
			temp.start=params.offset;
			temp.check=$(".search input").val();
			temp.sina=Math.random() ;
			temp.ParentID=AreaObj.areaID;
			if($("#UnitAreaID").val()!=null){
				temp.ID=$("#UnitAreaID").val();
			}
			
			return temp;
			

		}
		$("#contentForm").validate({
			rules : {
				Content : {
					required : !0,
					maxlength : 30
				},
				AreaCode : {
					required : !0,
					maxlength : 30
				}
			},
			messages : {
				Content : {
					required : "地区名称不能为空",
					maxlength : "地区名称不能超过30个字符"
				},
				AreaCode : {
					required : "地区名编号不能为空",
					maxlength : "地区编号不能超过30个字符"
				}
			}
		});
		$("#referWechat").click(function(){
			if(!$("#contentForm").validate().form()){
				return 
			}
		
				$.ajax({
					url : "/WeNewsAgency/Region/addRegion",
					data : {
						'ParentID' : $("#CurrParentID").val(),
						'AreaName':encodeURI($("#Content").val()),
						'AreaCode':encodeURI($("#AreaCode").val()),
						'ID':$("#CurrentID").val()
					},
					success : function(obj) {
						if(obj.Status==-1){
							ViewWarningError(obj.ErrorMsg);
						}else if(obj.Status==1){
							ViewSuccess();
							getQueryList();
							$('#myModal').modal('hide');
						}
						
					}
				});
			
		});
		function SuccessApply(type) {
			if(type!=2 ||CheckhasChecked(DataTable)==true){
				var AreaObj=getAreaObj();
				if(type==2){
					var rows=DataTable.bootstrapTable("getSelections");
					$("#CurrentID").val(rows[0].id);
					$("#Content").val(rows[0].name);
					$("#AreaCode").val(rows[0].code);
					$("#ParentNote").text("正在获取中...");
					$("#CurrParentID").val(rows[0].parentID);
					getParentInfo(rows[0].parentID);
				}else{
					$("#CurrentID").val("0");
					$("#Content").val("");
					$("#AreaCode").val("");
					$("#ParentNote").text(AreaObj.areaName);
					$("#CurrParentID").val(AreaObj.areaID);
				}
				
					$('#myModal').modal('show');
					$("#Content").trigger("blur");
					$("#AreaCode").trigger("blur");
					
				}

		}
		function getParentInfo(parentID){
			$.ajax({
				url : "/WeNewsAgency/Region/getParentRegionInfo",
				data : {
					'parentID' : $("#CurrParentID").val()
				},
				success : function(obj) {
					if(obj.Status==1){
						$("#ParentNote").text(obj.Data[0].name);
					}else {
						$("#ParentNote").text("无");
					}
					
				}
			});
		}
		function getAreaObj(area){
			if(area==null){
				area="AreaID";
			}
			var Areas=document.getElementsByName(area);
			var AreaObj=new Object();
			AreaObj.areaName="";
			for(var i=0;i<Areas.length;i++){
				if($(Areas[i]).find("option:selected").text()!=""){
					AreaObj.areaName+=$(Areas[i]).find("option:selected").text();
				}
				if(Areas[i].value!="" && Areas[i].value!=undefined){
					AreaObj.areaID=Areas[i].value;
				}else{
					if(area=="AreaID"){
						AreaObj=getAreaObj("City");
					}
					if(area=="City"){
						AreaObj=getAreaObj("Province");
					}
				}
			}
			return AreaObj;
		}
		function CopyUrl()
		{
			var url=$("#url").html();
		}
	</script>
</body>
</html>