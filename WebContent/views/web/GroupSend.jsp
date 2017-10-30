<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Users"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath =SmBaseUtil.getProjectBaseUrl(request);
    String rootPath=getClass().getResource("/").getFile().toString();
    Users user =(Users)request.getSession().getAttribute("UserName");
    rootPath=rootPath.replace("classes/", "");
    rootPath=rootPath.replace("WEB-INF/", "");
    System.out.println(rootPath+"图片");
%>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="/include/commonCss.jsp" />
</head>

<body>
<div>
  <div class="wrapper wrapper-content animated fadeIn">
        <div class="row">
            <div class="col-sm-6">
         		   群发功能
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active" id="wenzi"><a data-toggle="tab" href="#tab-1" aria-expanded="true">文本</a>
                        </li>
                        <li class="" id="tuwen"><a data-toggle="tab" href="#tab-2" aria-expanded="false">图文</a>
                        </li>
                        <li class="" id="image"><a data-toggle="tab" href="#tab-3" aria-expanded="false">图片</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                           <input type="text" id="text1" placeholder="请输入内容"/>
                        </div>
                        <div id="tab-2" class="tab-pane">
							<input type="text" id="urlpic" placeholder="请输入图片地址" /> <input
								type="text" id="author" placeholder="请输入作者" /> <input
								type="text" id="title" placeholder="请输入标题" /> <input
								type="text" id="content_source_url" placeholder="请输入内容地址" /> <input
								type="text" id="tucontent" placeholder="请输入内容" /> <input
								type="text" id="digest" placeholder="请输入详情" />
								<a href="<%=path%>/Notices/NewsList?shang=1">选择新闻</a>
						</div>
						 <div id="tab-3" class="tab-pane">
                           <input type="text" id="pic" placeholder="请输入图片地址"/>
                           <a href="<%=path%>/ProdPicture/ProdPictureList?shang=1">选择图片</a>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
    <input type="button" onclick="send()" value="发送">
    </div>
    <br>
    <br>
    <br>
    <div>
    	刚刚关注的话语
    	<input type="text" id="subscribe"/>
    	<input type="button" onclick="subscribe()" value="确定">
    </div>
    <br>
    <br>
   
     <div>
    	规定<br>
    	请输入关键字<input id="key" type="text"/>
    	
    	      <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active" id="wenzi2"><a data-toggle="tab" href="#tab-4" aria-expanded="true">文本</a>
                        </li>
                      <li class="" id="tuwen2"><a data-toggle="tab" href="#tab-5" aria-expanded="false">图文</a>
                        </li>
                         <li class="" id="image2"><a data-toggle="tab" href="#tab-6" aria-expanded="false">图片</a>
                        </li> 
                    </ul>
                    <div class="tab-content">
                        <div id="tab-4" class="tab-pane active">
                           <input type="text" id="textkey" placeholder="请输入回复内容"/>
                        </div>
                            <div id="tab-5" class="tab-pane">
                            <input type="text" id="title1" placeholder="请输入标题" /> 
                            <input type="text" id="description" placeholder="请输入详情" />
							<input type="text" id="picurl" placeholder="请输入图片地址" />
							<input type="text" id="url" placeholder="请输入内容地址" /> 
							<input type="button" onclick="mpnews()" value="我还要继续上传"/>
							<input type="button" onclick="news()" value="选择新闻"/>
						</div>
					 	 <div id="tab-6" class="tab-pane">
                          	 <input type="text" id="pic1" placeholder="请输入图片地址"/> <input type="button" onclick="tiao()" value="选择图片"/>
                        </div>
                    </div>


                </div>
                <input type="button" onclick="Rules()" value="确定"/> 
    </div>
   <jsp:include page="/include/commonJs.jsp" />
	<script type="text/javascript">
	function Rules()
	{
		var rules=0;
		rules=$("#wenzi2").attr("class")=="active"?"text":($("#tuwen2").attr("class")=="active"?"mpnews":($("#image2").attr("class")=="active")?"image":"其他判断");
		
		console.log(rules);
		if (rules=="text") {
				$.ajax({
					url : "<%=path%>/WeChatCustom/Rules",
					type : "get",
					data : {
						'content':encodeURI($("#textkey").val()),
						'rules':rules,
						'key':encodeURI($("#key").val())
					}, 
				});
		}
		if(rules=="mpnews")
		{
			$.ajax({
				url : "<%=path%>/WeChatCustom/Rules?sina=<%= SmBaseUtil.Random() %>",
				type : "get",
				data : {
					'title':encodeURI($("#title1").val()),
					'description':encodeURI($("#description").val()),
					'key':encodeURI($("#key").val()),
					'picurl':$("#picurl").val(),
					'url':$("#url").val(),
					'rules':rules
				}, 
			});
		}
		
		if(rules=="image")
		{
			$.ajax({
				url : "<%=path%>/WeChatCustom/Rules?sina=<%= SmBaseUtil.Random() %>",
				type : "get",
				data : {
					'pic':$("#pic1").val(),
					'key':encodeURI($("#key").val()),
					'rules':rules
				}, 
			});
		}
	
	}
	
	function mpnews() {
		$.ajax({
			url : "<%=path%>/WeChatCustom/Mpnews?sina=<%= SmBaseUtil.Random() %>",
			type : "get",
			data : {
				'title':encodeURI($("#title1").val()),
				'description':encodeURI($("#description").val()),
				'key':encodeURI($("#key").val()),
				'picurl':$("#picurl").val(),
				'url':$("#url").val(),
			}, 
		});
	}
	function subscribe() 
	{
		
			$.ajax({
				url : "<%=path%>/WeChatCustom/Subscribe?sina=<%= SmBaseUtil.Random() %>",
				type : "get",
				data : {
					'content':	encodeURI(	$("#subscribe").val()),
				}, 
			});
		
	
	}
	
	function tiao()
	{
		url="<%=path%>/ProdPicture/ProdPictureList?sina=<%= SmBaseUtil.Random() %>&shang=2&rules="+encodeURI(encodeURI($("#key").val()));
		self.location.href=url;
	}
	
	
	function news()
	{
		url="<%=path%>/Notices/NewsList?sina=<%= SmBaseUtil.Random() %>&shang=2&rules="+encodeURI(encodeURI($("#key").val()));
		self.location.href=url;
	}
	function send()
	{
		var type=0;
		type=$("#wenzi").attr("class")=="active"?"text":($("#tuwen").attr("class")=="active"?"mpnews":($("#image").attr("class")=="active")?"image":"其他判断");
		console.info(type);
		if (type=="text") {
			
			$.ajax({
				url : "<%=path%>/WeChatCustom/GroupSend?sina=<%= SmBaseUtil.Random() %>",
				type : "post",
				data : {
					'content':$("#text1").val(),
					'type':type
				}, 
			});
		}
	
		if (type=="mpnews") {
			$.ajax({
				url : "<%=path%>/WeChatCustom/GroupSend?sina=<%= SmBaseUtil.Random() %>",
				type : "post",
				data : {
					'type':type,
					'urlpic':$("#urlpic").val(),
					'author':$("#author").val(),
					'title':$("#title").val(),
					'content_source_url':$("#content_source_url").val(),
					'tucontent':$("#tucontent").val(),
					'digest':$("#digest").val()
				}, 
			});
		}

		if (type=="image") {
			$.ajax({
				url : "<%=path%>/WeChatCustom/GroupSend?sina=<%= SmBaseUtil.Random() %>",
				type : "post",
				data : {
					'type':type,
					'pic':$("#pic").val()
		
				}, 
			});
		}

	}
	</script>

</body>

</html>