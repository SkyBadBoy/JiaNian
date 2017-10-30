
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="footer">
	<div class="pull-right">
		<%=SmBaseGlobal.CompanyName %>&copy;<%=SmBaseGlobal.CurrentYear%>
	</div>
</div>
</div>
<!--右侧部分结束-->
<!--右侧边栏开始-->
<div id="right-sidebar">
	<div class="sidebar-container">

		<ul class="nav nav-tabs navs-2">

			<li class="active"><a data-toggle="tab" href="#tab-1"> <i
					class="fa fa-gear"></i> 主题
			</a></li>
			<li class=""><a data-toggle="tab" href="#tab-2"> 通知 </a></li>
			
		</ul>

		<div class="tab-content">
			<div id="tab-1" class="tab-pane active">
				<div class="sidebar-title">
					<h3>
						<i class="fa fa-comments-o"></i> 主题设置
					</h3>
					<small><i class="fa fa-tim"></i>
						你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
				</div>
				<div class="skin-setttings">
					<div class="title">主题设置</div>
					<div class="setings-item">
						<span>收起左侧菜单</span>
						<div class="switch">
							<div class="onoffswitch">
								<input type="checkbox" name="collapsemenu"
									class="onoffswitch-checkbox" id="collapsemenu"> <label
									class="onoffswitch-label" for="collapsemenu"> <span
									class="onoffswitch-inner"></span> <span
									class="onoffswitch-switch"></span>
								</label>
							</div>
						</div>
					</div>
					<div class="setings-item">
						<span>固定顶部</span>

						<div class="switch">
							<div class="onoffswitch">
								<input type="checkbox" name="fixednavbar"
									class="onoffswitch-checkbox" id="fixednavbar"> <label
									class="onoffswitch-label" for="fixednavbar"> <span
									class="onoffswitch-inner"></span> <span
									class="onoffswitch-switch"></span>
								</label>
							</div>
						</div>
					</div>
					<div class="setings-item">
						<span> 固定宽度 </span>

						<div class="switch">
							<div class="onoffswitch">
								<input type="checkbox" name="boxedlayout"
									class="onoffswitch-checkbox" id="boxedlayout"> <label
									class="onoffswitch-label" for="boxedlayout"> <span
									class="onoffswitch-inner"></span> <span
									class="onoffswitch-switch"></span>
								</label>
							</div>
						</div>
					</div>
					<div class="title">皮肤选择</div>
					<div class="setings-item default-skin nb">
						<span class="skin-name "> <a href="#" class="s-skin-0">
								默认皮肤 </a>
						</span>
					</div>
					<div class="setings-item blue-skin nb">
						<span class="skin-name "> <a href="#" class="s-skin-1">
								蓝色主题 </a>
						</span>
					</div>
					<div class="setings-item yellow-skin nb">
						<span class="skin-name "> <a href="#" class="s-skin-3">
								黄色/紫色主题 </a>
						</span>
					</div>
				</div>
			</div>
			<div id="tab-2" class="tab-pane">

				<div class="sidebar-title">
					<h3>
						<i class="fa fa-comments-o"></i> 最新通知
					</h3>
					<small><i class="fa fa-tim"></i> 暂无通知</small>
				</div>



			</div>
			
		</div>

	</div>
</div>
</div>

<script src="<%=basePath%>js/jquery.min.js?v=2.1.4"></script>
<script src="<%=basePath%>js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=basePath%>js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script
	src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>js/plugins/layer/layer.min.js?v=1.0.0.1"></script>
<script src="<%=basePath%>js/hplus.min.js?v=4.0.0"></script>
<script src="<%=basePath%>js/contabs.min.js?v=1.0.0.2" type="text/javascript"></script>
<script src="<%=basePath%>js/plugins/pace/pace.min.js"></script>
<script type="text/javascript">
$(window).load(function() { 
	$('img').each(function() {
	    if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) { 
	      this.src = "<%=basePath%>img/errorpic.jpg"; 
	      } 
	   });
});
$(".profile-element .J_menuItem").click(function(){$(".profile-element").removeClass('open')})
 function Search() {
	 var keyword=$("#top-search").val();
     var t = $("#top-search").attr("href")+"?KeyWord="+keyword+"&v=4.1";
    var   a = $("#top-search").data("index");
    var  i = $.trim("搜索“"+ keyword +"”相关的的结果");
     n = !0;
     if (void 0 == t || 0 == $.trim(t).length) return ! 1;
     	addNewTab(t,i);
     
     return ! 1
 }
 function addNewTab(url,title){
	 if ($(".J_menuTab").each(function() {
         return $(this).data("id") == url ? ($(this).hasClass("active") || ($(this).addClass("active").siblings(".J_menuTab").removeClass("active"), e(this), $(".J_mainContent .J_iframe").each(function() {
             return $(this).data("id") == url ? ($(this).show().siblings(".J_iframe").hide(), !1) : void 0
         })), n = !1, !1) : void 0;
     }), n) {
         var s = '<a href="javascript:;" class="active J_menuTab" data-id="' + url + '">' + title + ' <i class="fa fa-times-circle"></i></a>';
         $(".J_menuTab").removeClass("active");
         var r = '<iframe class="J_iframe" name="iframe' + url + '" width="100%" height="100%" src="' + url + '" frameborder="0" data-id="' + url + '" seamless></iframe>';
         $(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(r);
         var o = layer.load();
         $(".J_mainContent iframe:visible").load(function() {
             layer.close(o)
         }),
         $(".J_menuTabs .page-tabs-content").append(s),
         e($(".J_menuTab.active"))
	 }
 }
 function modifyInfo(type,value){
	 if(type==1){
		 $("#UserInfoImg").attr("src",value);
	 }else if(type==3 || type=="3"){
		 while(value.indexOf(' ')!=-1){
			 value=value.replace(" ", "&nbsp;");
		 }
		 $("#UserInfoName").html(value);
	 }
	 
 }
</script>
<!-- 友盟统计代码 begin -->
<%@ include file="../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>
</html>