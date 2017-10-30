<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*,wtb.smUtil.IdWorker"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);

	String a = request.getAttribute("DataWeChat1").toString();

	String mGroup1 = request.getAttribute("mGroup1").toString();
%>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="/include/commonCss.jsp" />
<link rel="stylesheet" href="<%=basePath%>css/define_menu.css">
<link rel="stylesheet" href="<%=basePath%>css/conan.css" />
<link rel="stylesheet" href="<%=basePath%>css/jquery.nestable.css" />
</head>

<body>

	<input type="hidden" id="basePath" value="${basePath}" />
	<input type="hidden" id="mGroup" value="${mGroup}" />
	<input type="hidden" id="zhuid1" value="${zhuid1}" />
	<input type="hidden" id="zhuid2" value="${zhuid2}" />
	<input type="hidden" id="zhuid3" value="${zhuid3}" />
	<div class="container-fluid">
		<div class="row">
			<br />
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="<%=path%>/home" target="_self">主页</a></li>
					<li><strong>公众号管理</strong></li>
				</ol>
			</div>
			<br />
		</div>

		<div id="ErrorMessage" role="alert"></div>
		<div class="btn-group hidden-xs" id="exampleTableEventsToolbar"
			role="group">
			<button type="button" onclick="show()"
				class="btn btn-outline btn-default">
				<i class="glyphicon glyphicon-plus" title="更改公众号" aria-hidden="true">更改公众号</i>
			</button>
			<button type="button" onclick="addView()"
				class="btn btn-outline btn-default">
				<i class="glyphicon glyphicon-plus" title="增加主菜单" aria-hidden="true">增加主菜单</i>
			</button>
		</div>


		<div class="wrapper wrapper-content  animated fadeInRight">
			<div class="row">
				<div class="col-sm-6">
					<div class="dd" id="nestable3">
						<ol class='dd-list dd3-list'>
							<div id="dd-empty-placeholder"></div>
						</ol>
					</div>
				</div>

				<div class="col-sm-6">
					<div id="body" class="body page_menu_setting">
						<div id="js_container_box"
							class="container_box cell_layout side_l">
							<div class="col_main">
								<div class="main_bd">
									<div class="menu_setting_area_wrp" id="menu_container" style="">
										<input style="width: 400px;" type="text" id="caidan"
											placeholder="输入按钮名称" class="form-control" /><input
											style="width: 100px;" type="button" onclick="deleteWeChat()"
											class="btn btn-outline btn-default" value="删除菜单" /> <input
											type="hidden" id="id" /> <input type="hidden" id="mgroup" />
										<input type="hidden" id="parentid" /> <select
											id="button_type">
											<option value="0">--请选择按钮类型--</option>
											<option value="click">点击</option>
											<option value="view">连接</option>
											<input type="text" class="form-control" id="key" />
									</div>
								
									<div class="tabs-container" name="table"
										style="display: block;">
										<ul class="nav nav-tabs">
											<li class="active" value="1" id="wenzi"><a
												data-toggle="tab" href="#tab-1" aria-expanded="true">文字</a></li>
											<li class="" id="tuwen" value="2"><a data-toggle="tab"
												href="#tab-2" aria-expanded="false">图文</a></li>
										</ul>
										<div class="tab-content">
											<div id="tab-1" class="tab-pane active">
												<div class="panel-body">
													<input type="text" class="form-control" id="text1" />
												</div>
											</div>
											<div id="tab-2" class="tab-pane">
												<div class="panel-body">
													<input type="text" class="form-control" id="Title"
														placeholder="请输入标题"> <input type="text"
														class="form-control" id="Description" placeholder="请输入详情">
													<input type="text" class="form-control" id="PicUrl"
														placeholder="请输入图片地址"> <input type="text"
														class="form-control" id="Url" placeholder="请输入图片点击地址">
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="tool_bar tc js_totaltool_bar">

							<input type="button" onclick="save()"
								class="btn btn-outline btn-default" value="保存" />
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	</div>
	</div>
	</div>
	</div>


	<div class="modal" id="myModal" style="overflow: auto;" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</a>
					<h4 class="modal-title">公众号编辑</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form id="contentForm" action="#">
							<input type="text" class="form-control" placeholder="请输入appID"
								id="appID" name="appID" /> <br /> <br /> <br>
							<input type="text" class="form-control"
								placeholder="请输入appsecret" id="appsecret" name="appsecret" />
						</form>
					</div>
					<br />
					<button onclick="addWeChatInfo()" class="btn btn-warning">确定</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript" src="<%=basePath%>js/jstree.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/add_menu.js"></script>
	<script src="<%=basePath%>js/plugins/nestable/jquery.nestable.js"></script>
	<script>
	var z=1;
	var x=1;
	$(document).ready(function(){
	    var obj = '<%=a%>';
	    var output = '';
	    function buildItem(item) {
	        var html = "<li class='dd-item' data-id='" + item.id + "'><input id="+item.id+" type='button' data-action='tiejia' style='width: 100px;' class='btn btn-warning' value='添加'/>";
	        html += "<div id="+item.id+" name="+item.name+" type="+item.type+" url="+item.url+" key="+item.key+" class='dd-handle'>" +  item.name + "</div>";
	        html += "<ol class='dd-list' id="+item.id+'ol'+">";
	        if (item.children) {
	            
	            $.each(item.children, function (index, sub) {
	                html += buildItem(sub);
	            });

	        }
	        html += "</ol>";
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
	    	            $('.dd').nestable('tiejia');
	    	   			console.log($(e.target));
	    	           // $("#"+(e.target).id+"ol"+"").append("<div name='未命名' ParentID="+(e.target).id+"  class='dd-handle'>未命名</div>");
	    	           if((e.target).id!='')
	    	           {
	    	        	   target.next().next().append("<div name='未命名' ParentID="+(e.target).id+"  class='dd-handle'>未命名</div>");
	    	           }else
	    	           {
	    	        	   target.next().next().append("<div name='未命名'  class='dd-handle'>未命名</div>");
	    	           }
	    	          
	    	      
	    	          
	    	          
	    	          
	    	        }
	    	        
	    	       
	    	    });
	    $('#dd-empty-placeholder').html(output);
	    $('#nestable3').nestable();
	});
	
	
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
	function show()
	{
		$('#myModal').modal('show');
	}
	 function clea()
	{
		z++;
		$("#subbut").val("");
		$("#zisub").val("");
		
		$("#caidan").val("");
		$("#key").val("");
		$("#text1").val("");
		$("#Title").val("");
		$("#Description").val("");
		$("#PicUrl").val("");
		$("#Url").val("");
		$("#tishi").html("第"+z+"个按钮");
	}
	
	 function addWeChatInfo()
	 {
		 $.ajax({
			 url:"<%=path%>/WeChatCustom/addWeChatInfo",
			 data:{
				'appID':$("#appID").val(),
			 	'appsecret':$("#appsecret").val()
			 }
		 });
		 $('#myModal').modal('hide');
	 }
	 
	 
	 function save()
	 {
		var type=$("#button_type").val()
		var mgroup=$("#mgroup").val();
		var ParentID=$("#parentid").val();
		var id=$("#id").val();
		console.log(ParentID);
		if(($("#mgroup").val()=='') && ($("#id").val()=='')&&(ParentID==''))
		{
			console.log("mgroup=0and id=0");
			$.ajax({
				url:"<%=path%>/WeChatCustom/saveWeChatInfo",
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				data:{
					'name': encodeURI($("#caidan").val()),
					'type':type,
					'key':$("#key").val()
				 },				 
				 success : function() {
					 send()
				 }
				 
			 });
		}else{
		if(ParentID!='')
		{
			console.log("ParentID");
			addSun(type,ParentID)
		}
		if(mgroup!="")
		{
			console.log("mgroup");
			$.ajax({
				url:"<%=path%>/WeChatCustom/saveWeChatInfo",
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				data:{
					'mgroup':$("#mgroup").val(),
					'name': encodeURI($("#caidan").val()),
					'type':type,
					'key':$("#key").val()
				 },				 
				 success : function() {
					 send()
				 }
				 
			 });
			
		}
		if(id!=''){
			if (type=='0'||type=='') 
			{
				$.ajax({
					url:"<%=path%>/WeChatCustom/saveWeChatInfo",
					contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					data:{
						'id':id,
						'name': encodeURI($("#caidan").val()),
					 },
				 });
			}else
			{
				if(type=="click")
				{
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
							 addWeChatContent(),
							 send()
						 }
					 });
				}
				if(type=="view")
				{
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
							 addWeChatContent(),
							 send()
						 }
					 });
				}
				
			}
		
		}
		}
		
			
	 }

	 function deleteWeChat()
	 {
		 
			$.ajax({
				url:"<%=path%>/WeChatCustom/deleteWeChat",
				data:{
					'id':$("#id").val()
				 },
				 success : function() {
					 send()
				 }
			 });
			
		 
	 }
		
	 function send()
	 {
		$.ajax({
			url:"<%=path%>/WeChatCustom/sendWeChatInfo",
			data:{
				'mGroup':$("#mGroup").val(),
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
	 				'type':type
	 			}, 
	 		});
	 	}
	 	if (type==2) {
	 		$.ajax({
	 			url : "addWeChatContent",
	 			type : "get",
	 			data : {
	 				'key':$("#key").val(),
	 				'type':type,
	 				'title':encodeURI($("#Title").val()),
	 				'description':encodeURI($("#Description").val()),
	 				'picUrl':$("#PicUrl").val(),
	 				'url':$("#Url").val(),
	 			}, 
	 		});
	 	}
	 }
	 
	 function addView()
	 {
		 if($("#mGroup").val()!='')
		 {
			 $("#dd-empty-placeholder").append("<li class='dd-item'><input  mGroup="+$("#mGroup").val()+" type='button' data-action='tiejia' style=' width: 100px;' class='btn btn-warning' value='添加'><div name='未命名' mGroup="+$("#mGroup").val()+" type='view' url='http://www.whohelp.cc/' class='dd-handle'>未命名</div><ol  mGroup="+$("#mGroup").val()+" class='dd-list'  ></ol></li>");
		 }else
		 {
			 $("#dd-empty-placeholder").append("<li class='dd-item'><input   type='button' data-action='tiejia' style=' width: 100px;' class='btn btn-warning' value='添加'><div name='未命名'  type='view' url='http://www.whohelp.cc/' class='dd-handle'>未命名</div><ol  class='dd-list'  ></ol></li>");
		 }
		
	 	
	 }
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
						 send()
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
						send()
					}
				});
			}

		}
	</script>
</body>

</html>