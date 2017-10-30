
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta charset="utf-8">
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>	
<script type="text/javascript"> 
    
    window._points=[+new Date()];

    
    function wx_main(mod){
        window._points&&(window._points[3]=+new Date());
    };
    window.wx={
    uin:"3211475522"||"0"
    };
</script>
<script onerror="wx_loaderror(this)" type="text/javascript"
	src="<%=basePath%>js/jserr2fa4d8.js"></script>
<link
	href="https://res.wx.qq.com/mpres/htmledition/images/favicon218877.ico"
	rel="Shortcut Icon">
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css"
	href="<%=basePath%>css/layout_head2880f5.css" />
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css"
	href="<%=basePath%>css/base2fb022.css" />
<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css"
	href="<%=basePath%>css/lib2f613f.css" />
<link onerror="wx_loaderror(this)" rel="stylesheet"
	href="<%=basePath%>css/index2b2fad.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/richvideo2b638f.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/tooltip218878.css" />
</head>
<body class="zh_CN">

	
		<div id="js_container_box" class="container_box cell_layout side_l">
			<div class="col_main">
				<div class="main_bd">
					<div class="menu_initial_box js_startMenuBox" style="display: none">
						<p class="tips_global">你尚未添加任何菜单</p>
						<a class="btn btn_primary btn_add js_openMenu"
							href="javascript:void(0);"><i class="icon14_common add_white"></i>添加菜单</a>
					</div>
					<div class="menu_setting_box js_menuBox dn">
						<div
							class="highlight_box border icon_wrap menu_setting_msg js_menustatus dn"
							id='menustatus_2'>
							<i class="icon icon_msg_small info"></i>
							<p class="title">菜单编辑中</p>
							<p class="desc">
								菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box border icon_wrap menu_setting_msg js_menustatus dn"
							id='menustatus_1'>
							<i class="icon icon icon_msg_small success"></i>
							<p class="title">菜单使用中</p>
							<p class="desc">
								可在手机查看菜单内容。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
							id='menustatus_3'>
							<i class="icon icon_msg_small waiting"></i>
							<p class="title">菜单已发布</p>
							<p class="desc">
								<span class='js_waitHour'></span>小时后可在手机查看菜单内容。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border icon_small menu_setting_msg js_menustatus dn"
							id='menustatus_4'>
							<i class="icon lock"></i>
							<p class="title">未开启自定义菜单</p>
							<p class="desc">
								通过编辑和发布自定义菜单来进行便携管理。若开启菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border icon_small menu_setting_msg js_menustatus dn"
							id='menustatus_5'>
							<i class="icon lock"></i>
							<p class="title">未开启自定义菜单</p>
							<p class="desc">
								由于开发者通过接口修改了菜单配置，当前菜单配置已失效并停用。你可以前往<a
									href="/advanced/advanced?action=dev&t=advanced/dev&token=268861669&lang=zh_CN">开发者中心</a>进行停用。
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
							id='menustatus_6'>
							<i class="icon icon icon_msg_small success"></i>
							<p class="title">API版本菜单使用中</p>
							<p class="desc">
								该页面显示的菜单版本已失效。当前生效版本请调用API查看。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
							id='menustatus_7'>
							<i class="icon icon icon_msg_small success"></i>
							<p class="title">API版本/个性化菜单使用中</p>
							<p class="desc">
								该页面显示的菜单版本已失效。当前生效版本请调用API查看。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
							id='menustatus_8'>
							<i class="icon icon_msg_small waiting"></i>
							<p class="title">菜单已发布/个性化菜单使用中</p>
							<p class="desc">
								<span class='js_waitHour'></span>小时后可在手机查看菜单内容。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
							id='menustatus_9'>
							<i class="icon icon_msg_small info"></i>
							<p class="title">菜单编辑中/个性化菜单使用中</p>
							<p class="desc">
								菜单未发布，请确认菜单编辑完成后点击“保存并发布”同步到手机。当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div
							class="highlight_box icon_wrap border menu_setting_msg js_menustatus dn"
							id='menustatus_10'>
							<i class="icon icon icon_msg_small success"></i>
							<p class="title">菜单使用中/个性化菜单使用中</p>
							<p class="desc">
								可在手机查看菜单内容，当前生效的个性化菜单内容请调用API查看。若停用菜单，请<a
									href="/cgi-bin/plugindetails?t=service/profile&pluginid=10007&action=intro&token=268861669&lang=zh_CN"
									class="js_closeMenu">点击这里</a>
							</p>
						</div>
						<div class="page_msg mini plugin_update_tips js_authorized"
							style="display: none; margin: -20px 30px 0 30px">
							<div class="inner group">
								<span class="msg_icon_wrp"><i class="icon_msg_mini info"></i></span>
								<div class="msg_content">
									<p>
										你已授权给<span class='js_auth_name'></span>帮助你运营公众号，点击管理<a
											href="/cgi-bin/component_unauthorize?action=list&t=service/auth_plugins&token=268861669&lang=zh_CN">已授权的第三方平台</a>
									</p>
								</div>
							</div>
						</div>
						<div class="menu_setting_area ">
						
							<div class="menu_preview_area ">
								<div class="mobile_menu_preview" style="width: 0px;height: 0px;">
									<div class="mobile_hd tc">互办科技</div>
									<div class="mobile_bd">
										<ul class="pre_menu_list grid_line" id='menuList'></ul>
									</div>
								</div>
							</div>
							<div class="menu_form_area "style="height: 480px;">
								<div id='js_none' class="menu_initial_tips tips_global"
									style="display: none;"></div>
								<div id='js_rightBox' class="portable_editor to_left"
									style="display: block;">
									<div class="editor_inner">
										<div
											class="global_mod float_layout menu_form_hd js_second_title_bar">
											<h4 class="global_info"></h4>
											<div class="global_extra">
												<a href="javascript:void(0);" id='jsDelBt'>删除菜单</a>
											</div>
										</div>
										<div class="menu_form_bd" id='view'>
											<div id='js_innerNone' style='display: none;'
												class="msg_sender_tips tips_global"></div>
											<div class="frm_control_group js_setNameBox">
												<label for="" class="frm_label"> <strong
													class="title js_menuTitle">菜单名称</strong>
												</label>
												<div class="frm_controls">
													<span class="frm_input_box with_counter counter_in append">
														<input type="text" class="frm_input js_menu_name">
													</span>
													<p class="frm_msg fail js_titleEorTips dn">字数超过上限</p>
													<p class="frm_msg fail js_titlenoTips dn">请输入菜单名称</p>
													<p class="frm_tips js_titleNolTips">字数不超过4个汉字或8个字母</p>
												</div>
											</div>
											<div class="frm_control_group">
												<label for="" class="frm_label"> <strong
													class="title js_menuContent">菜单内容</strong>
												</label>
												<div class="frm_controls frm_vertical_pt">
													<label class="frm_radio_label js_radio_sendMsg"
														data-editing='0'> <i class="icon_radio"></i> <span
														class="lbl_content">发送消息</span> <input type="radio"
														name="hello" class="frm_radio">
													</label> <label class="frm_radio_label js_radio_url"
														data-editing='0'> <i class="icon_radio"></i> <span
														class="lbl_content">跳转网页</span> <input type="radio"
														name="hello" class="frm_radio">
													</label>
												</div>
											</div>
											<div class="menu_content_container">
												<div class="menu_content send jsMain" id="edit">
													<div class="msg_sender" id="editDiv"></div>
													<p
														class="profile_link_msg_global menu_send mini_tips warn dn js_warn">
														请勿添加其他公众号的主页链接</p>
												</div>
												<div class="menu_content url jsMain" id="url">
													<form action="" id="urlForm" onSubmit="return false;">
														<p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下链接</p>
														<div class="frm_control_group">
															<label for="" class="frm_label">页面地址</label>
															<div class="frm_controls">
																<span class="frm_input_box"> <input type="text"
																	class="frm_input" id="urlText" name="urlText">
																</span>
																<p
																	class="profile_link_msg_global menu_url mini_tips warn dn js_warn">
																	请勿添加其他公众号的主页链接</p>
																<p class="frm_tips" id="js_urlTitle"
																	style="display: none;">
																	来自<span class="js_name"></span><span
																		style="display: none;"> -《<span
																		class="js_title"></span>》
																	</span>
																</p>
															</div>
														</div>
														<div class="frm_control_group btn_appmsg_wrap">
															<div class="frm_controls">
																<p class="frm_msg fail dn" id="urlUnSelect">
																	<span for="urlText" class="frm_msg_content"
																		style="display: inline;">请选择一篇文章</span>
																</p>
																<a href="javascript:;" id="js_appmsgPop">从公众号图文消息中选择</a>
																<a href="javascript:void(0);" class='dn btn btn_default'
																	id="js_reChangeAppmsg">重新选择</a>
															</div>
														</div>
													</form>
												</div>
												<div class="menu_content sended" style="display: none;">
													<p class="menu_content_tips tips_global">订阅者点击该子菜单会跳到以下链接</p>
													<div class="msg_wrp" id="viewDiv"></div>
													<p class="frm_tips">
														来自<span class="js_name">素材库</span><span
															style="display: none;"> -《<span class="js_title"></span>》
														</span>
													</p>
												</div>
												<div id='js_errTips' style='display: none;'
													class="msg_sender_msg mini_tips warn"></div>
											</div>
										</div>
										
											<span id="pubBt" class="btn btn_input btn_primary"><button>保存并发布</button></span>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="mobile_preview" id="mobileDiv">
					<div class="mobile_preview_hd">
						<strong class="nickname">TuringMJ</strong>
					</div>
					<div class="mobile_preview_bd">
						<ul id="viewShow" class="show_list">
						</ul>
					</div>
					<div class="mobile_preview_ft">
						<ul class="pre_menu_list grid_line" id="viewList"></ul>
					</div>
					<a href="javascript:void(0);"
						class="mobile_preview_closed btn btn_default" id="viewClose">退出预览</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    try {
        window.wx ={
            version:"4.0.0",
            uin:"3211475522",
            data:{
                t:"268861669",
                ticket:"2252ee05ff72515aae4d311e54ee9d3f92264433",
                lang:'zh_CN',
                param:["&token=268861669",'&lang=zh_CN'].join(""),
                uin:"3211475522",
                uin_base64:"MzIxMTQ3NTUyMg==",
                user_name:"gh_8013f30bb96a",
                nick_name:"TuringMJ",
                time:"1471507362"||new Date().getTime()/1000
            },
            path:{
                video : "https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/video-js218877.swf",    
                audio : "https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/audiojs218877.swf",
                uploadify : "https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/uploadify218877.swf",
                webuploader : "https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/webuploader230dc3.swf",
                zoom : "https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/zoom230dc3.swf",
                zeroClipboard_new : "https://res.wx.qq.com/mpres/zh_CN/htmledition/plprecorder/biz_web/ZeroClipboard_new2ee4ed.swf",
                rimgcrop : "https://res.wx.qq.com/mpres/htmledition/images/cut-round218877.gif"
            },
            acl:{
                                "msg_acl" : {
                    "can_text_msg" : 1,
                    "can_image_msg" : 1,
                    "can_voice_msg" : 1,
                    "can_video_msg" : 1,
                    "can_app_msg" : 1,
                    "can_commodity_app_msg" : 0,
                    "can_card_msg" : "0"*1||0, 
                    "can_use_shortvideo" : "0"*1,
                    "can_use_reprintapply_list":"0"*1 
                },
                "material_acl" : {
                    "can_text_msg" : 1,
                    "can_image_msg" : 1,
                    "can_voice_msg" : 1,
                    "can_video_msg" : 1,
                    "can_app_msg" : 1,
                    "can_commodity_app_msg" : 0,
                    "can_card_msg" : "0"*1||0, 
                    "can_use_shortvideo" : "0"*1,
                    "can_use_reprintapply_list":"0"*1 
                },
                "ivr_acl" : {
                    "can_text_msg" : 1,
                    "can_image_msg" : 1,
                    "can_voice_msg" : 1,
                    "can_video_msg" : 1,
                    "can_app_msg" : 1,
                    "can_commodity_app_msg" : 0
                },
                "merchant_acl" : {
                    "can_use_pay_tmpl" : ""*1,
                    "can_use_account_manage" : ""*1
                },
                                "ad_system" : {
                    "can_use_sp" : ""*1                                        ,"can_use_new_ad" : ""*1
                                    }
            },
            events:{}
        };
    }catch(error){
        if(error&&error.stack){
            error.stack+="|try";
        }
        BJ_REPORT&&BJ_REPORT.report&&BJ_REPORT.report(error);
        var orgOnerror = window.onerror;
        window.onerror = function() {};
        setTimeout(function() {
            window.onerror = orgOnerror;
        }, 50);
        throw error;
    }
</script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/sea27d2ff.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/lib27616c.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/wx2fcc9b.js"></script>
	<script type="text/javascript">
function getico(o){
var t=new Image(1,1);
t.src=wx.url&&wx.url(location.protocol+"//"+location.host+"/misc/getico?location="+(o||-1)+"&rand="+Math.random());
}
function cacheHitRate(o){
if(window.performance&&performance.getEntries){
var t=0,n=0,e=[];
performance.getEntries().forEach(function(o){
("script"===o.initiatorType||"link"===o.initiatorType)&&(t++,(o.duration<10||o.requestStart&&Math.floor(o.requestStart)===Math.floor(o.responseStart))&&(n+=100,
e.push(o)));
}),t&&((new Image).src="/mp/jsmonitor?idkey=27823_"+o+"_"+n+";27823_"+(o-1)+"_"+t);
}
}
jQuery(function(){
window._points&&(window._points[4]=+new Date),getico("1708");
}),window.onload=function(){
try{
window._points&&(window._points[5]=+new Date);
var o=[1,3,5,7,9,11,13],t="edit";
"/cgi-bin/appmsg"==location.pathname&&"index"==t&&(o=[21,23,25,27,29,31,33]);
var n=[],e=window.performance&&window.performance.timing,i=e&&e.navigationStart||_points[0],r=e&&e.responseStart||_points[1],a=_points[3],s=e&&e.domComplete||_points[4],c=e&&e.loadEventEnd||_points[5];
n.push([o[0],c-i,28]),n.push([o[1],r-i,29]),n.push([o[2],a-i,30]),n.push([o[3],s-r,31]),
e&&e.secureConnectionStart&&(n.push([o[4],e.connectEnd-e.connectStart,32]),n.push([o[5],e.connectEnd-e.secureConnectionStart,33])),
seajs.use("biz_common/utils/monitor",function(t){
var e,i;
for(e=0;e<n.length;++e)i=n[e],i[1]>0&&i[1]<36e5&&t.setAvg(27823,i[0],i[1]);
t.send(),cacheHitRate(o[6]);
}),seajs.use("biz_common/utils/huatuo",function(o){
"/cgi-bin/appmsg"==location.pathname&&"index"==t?o.setFlags(1635,1,1):o.setFlags(1635,1,2);
var e,i;
for(e=0;e<n.length;++e)i=n[e],i[1]>0&&i[1]<36e5&&o.setPoint(i[2],i[1]);
o.report();
});
}catch(p){
p&&p.stack&&(p.stack+="|try"),BJ_REPORT&&BJ_REPORT.report&&BJ_REPORT.report(p);
var u=window.onerror;
throw window.onerror=function(){},setTimeout(function(){
window.onerror=u;
},50),p;
}
};
</script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/card_table.html2d3016.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/audio.html2767e5.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/plugin3_edit.html23b289.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/appmsg.html2ebf96.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/videomsg_layout.html27bb72.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/highlight_box.html2a0043.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/norefererimg26d05a.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/dialog.html253a4f.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/xss26d05a.js"></script>
	<script onerror="wx_loaderror(this)" type="text/javascript"
		src="<%=basePath%>js/menusetting2f613f.js"></script>
	<script type="html/text" id="tpl">

        {each data as menu}
                    <li class="jsMenu pre_menu_item grid_item jslevel1 size1of3 ui-sortable ui-sortable-disabled" id="{menu.id}">
                        <a href="javascript:void(0);" class="pre_menu_link" draggable="false">
                            
                            <i class="icon_menu_dot js_icon_menu_dot dn"></i>
                            <i class="icon20_common sort_gray"></i>
                            <span class='js_l1Title'>{menu.name}</span>
                        </a>
                        <div class="sub_pre_menu_box js_l2TitleBox" style='display:none;'>
                            <ul class="sub_pre_menu_list">
                                {each menu.subs as sub}
                                <li id="{sub.id}" class='jslevel2'><a href="javascript:void(0);" class="jsSubView" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class='js_l2Title'>{sub.name}</span></span></a></li>
                                {/each}
                                <li class='js_addMenuBox'><a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a></li>
                            </ul>
                            <i class="arrow arrow_out"></i>
                            <i class="arrow arrow_in"></i>
                        </div>
                    </li>
        {/each}
                    <li class="js_addMenuBox pre_menu_item grid_item no_extra">
                        <a href="javascript:void(0);" class="pre_menu_link js_addL1Btn" title="最多添加3个一级菜单" draggable="false">
                            <i class="icon14_menu_add"></i>
                        </a>
                    </li>
</script>
	<script type="html/text" id="viewTpl">
    {each data as menu index}
    <li class="pre_menu_item grid_item size1of{data.length} jsViewLi {if index+1==data.length}no_extra_flex{/if}" id="{menu.id}">
    <a href="javascript:void(0);" class="jsView pre_menu_link" title="{menu.name}" draggable="false">
    {if menu.subs}
    <i class="icon_menu_dot"></i>
    {/if}{menu.name}
    </a>
    {if menu.subs}
    <div class="sub_pre_menu_box jsSubViewDiv" style="display:none">
        <ul class="sub_pre_menu_list">
            {each menu.subs as sub}
            <li id="{sub.id}"><a href="javascript:void(0);" class="jsSubView" title="{sub.name}" draggable="false">{sub.name}</a></li>
            {/each}
        </ul>
        <i class="arrow arrow_out"></i>
        <i class="arrow arrow_in"></i>
    </div>
    {/if}
    </li>
    {/each}
</script>
	<script type="text/javascript">
    var _json;
            _json={"menu_entity":{"name":"test menu","version":423293075,"button_list":[{"type":0,"name":"第一个","act_mode":0,"act_list":[],"sub_button_list":[{"type":1,"name":"子菜单名称","act_mode":0,"act_list":[{"type":5,"value":"100000005","key":"","appmsg_data":{"app_id":100000005,"file_id":100000004,"create_time":"1468407020","multi_item":[{"cover":"https:\/\/mmbiz.qlogo.cn\/mmbiz\/f3LXIicKrClaeanYO5iar0f9YL6R68VsucJA4GWicbbc5EhkWS8LrIZGNiaqG5msgCL5OYXMS3kdMPLDJDUT0BmEzA\/0?wx_fmt=jpeg","title":"q","digest":"qqq","tags":[]}]}}],"sub_button_list":[]},{"type":1,"name":"子菜单名称","act_mode":0,"act_list":[{"type":5,"value":"100000005","key":"","appmsg_data":{"app_id":100000005,"file_id":100000004,"create_time":"1468407020","multi_item":[{"cover":"https:\/\/mmbiz.qlogo.cn\/mmbiz\/f3LXIicKrClaeanYO5iar0f9YL6R68VsucJA4GWicbbc5EhkWS8LrIZGNiaqG5msgCL5OYXMS3kdMPLDJDUT0BmEzA\/0?wx_fmt=jpeg","title":"q","digest":"qqq","tags":[]}]}}],"sub_button_list":[]},{"type":1,"name":"子菜单名称","act_mode":0,"act_list":[],"sub_button_list":[]},{"type":1,"name":"子菜单名称","act_mode":0,"act_list":[],"sub_button_list":[]},{"type":1,"name":"子菜单名称","act_mode":0,"act_list":[],"sub_button_list":[]}]},{"type":0,"name":"第二个","act_mode":0,"act_list":[],"sub_button_list":[{"type":2,"name":"子菜单名称","act_mode":0,"act_list":[{"type":6,"value":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIxMTQ3NTUyMg==&amp;mid=100000002&amp;idx=1&amp;sn=608927cd07b40409d666814d20c64d9a&amp;scene=18#wechat_redirect","key":"","viewurl_data":{"url":"http:\/\/mp.weixin.qq.com\/s?__biz=MzIxMTQ3NTUyMg==&amp;mid=100000002&amp;idx=1&amp;sn=608927cd07b40409d666814d20c64d9a&amp;scene=18#wechat_redirect"}}],"sub_button_list":[]}]},{"type":0,"name":"第三个","act_mode":0,"act_list":[],"sub_button_list":[{"type":1,"name":"子菜单名称","act_mode":0,"act_list":[{"type":5,"value":"100000002","key":"","appmsg_data":{"app_id":100000002,"file_id":100000003,"create_time":"1468036282","multi_item":[{"cover":"https:\/\/mmbiz.qlogo.cn\/mmbiz\/f3LXIicKrClZw8H72CuqVrXLXEVPwhUUy5pVZoOLlMkkWzxAzLgiaMQjN95QbYmYRkWteE0INZibr4dtEWXPeK8dw\/0?wx_fmt=jpeg","title":"🐷","digest":"猪宝宝","tags":[]}]}}],"sub_button_list":[]}]}]},"menu_limit":{"first_level_limit":3,"second_level_limit":5,"can_set_view_url":1}};
        wx.cgiData={
        biz: "MzIxMTQ3NTUyMg==",
        nick_name : "互办科技",
        can_use_homepage: "0" * 1,
        menu:_json.menu_entity,
        authrized: "0"
            ,auth_info: {}
            ,service_type: "1"
        ,is_wx_verify: "0"
        ,is_qverify: "0"
        ,is_wb_verify: "0"
        ,func_ban_info : [      
                                                                                                ]
    };

    var MenuData = {
                
                    
            can:{
                selfMenu:true            },
            modify:{
                selfMenuModify:true            },          
                     
        is:{
            isOpen:true,
            selfMenu:true        },
        selfMenu:{
            hasMenu:true,
            hasButton:1,
            status:"2",
            lastTime:"1468064972",
            leftTime:"0",
            version:"423293075",
            applyStatus:"2",
            
            useMpOrApi:"0"||'0', 
            usePersonalizedMenu: "0"||'0', 
        }
    };
seajs.use('advanced/menuSetting', wx_main);;

$(".block").css("display","inline-block").hide();


</script>
	<script type="text/html" id="js_faqscene_tpl">
<div class="faqscene" id="js_faqscene_p">
<div class="faqscene_inner">
    <div class="faqscene_name js_faq_trigger">常见问题</div>
    <div class="faqscene_panel js_faq_main_panel">
        <a href="###" class="faqscene_close">x</a>
        <div class="faqscene_hd">{data.question}</div>
        <div class="faqscene_bd">
            <div class="faqscene_tabs">
                <div class="faqscene_tab_hd">
                    <ul class="js_faq_class1">
                        {if data.guide_list.length>2}
                        {each data.guide_list as guide i}
                        {if guide}
                        <li data-report-id="{guide.report_id}"><a {if i==0}class="active"{/if} href="javascript:;">{guide.itemname}</a></li>
                        {/if}

                        {/each}
                        {/if}
                    </ul>
                </div>
                {if data.guide_list.length>1}
                {/if}
                {each data.guide_list as guide i}
                {if guide}
                <div class="faqscene_tab_bd js_faqscene_content">
                    <ul>
                        {each guide.subitems as subitem i}
                        {if subitem}
                        <li><a target="_blank" href="{subitem.kf_url}">{subitem.title}</a></li>
                        {/if}
                        {/each}
                    </ul>
                </div>
                {/if}
                {/each}
            </div>
        </div>
        <div class="faqscene_ft"><a href="{data.more_help.kf_url}" target="_blank">{data.more_help.title}</a></div>
    </div>
</div>
</div>
</script>
	<script type="text/javascript"> 

/* (function() {

var jq = jQuery;
var dom = null;
var panel = null;
var close = null
var faqArea = null;
var timer = null;
var tab_heads = null;
var tab_contents = null;
var token = "268861669"
var report_id = null;


function loadDataAndInit() {
    var param = "&cginame=" + urlParser.parser.pathname.replace(/^\//, '');
    var tValue = urlParser.getParam("t");
    var actionValue = urlParser.getParam("action");
    var pluginid = urlParser.getParam('pluginid'); 

    param += token 
             ? ("&token=" + token ) 
             : "";
    param += tValue
             ? ("&t=" + tValue)
             : ("&action=" + actionValue);
    param += pluginid
             ? ("&pluginid=" + pluginid)
             : "";

    var getFAQUrl = "/misc/faq?action=getfaq&lang=zh_CN&f=json" + param;

    $.ajax({
        type: "GET",
        url: getFAQUrl
    }).success(function(data) {
        if (data.base_resp.ret !== 0) return;
        wx.faq_list = data.base_resp.assistant.problem;
        if (!wx.faq_list.question) return; 
        renderFaq();
        makeActions();
        initReport();
        panel.hide();
        goto1(0);
    }).error(function(error) {
        ; 
    });
}

function renderFaq() {
    jq("body").append(template.render("js_faqscene_tpl", {data: wx.faq_list}));
    dom = jq("#js_faqscene_p");
    panel = dom.find(".js_faq_main_panel");
    close = dom.find(".faqscene_close");
    faqArea = dom.find(".faqscene_inner");
    timer = null;
    tab_heads = dom.find(".js_faq_class1 li");
    tab_contents = dom.find(".js_faqscene_content");
}


function goto1(idx) {
    var $currentTab = tab_heads.eq(idx);
    tab_contents.hide();
    subitem = $currentTab.find("a").text();
    report_id = (idx === 0)
              ? wx.faq_list.guide_list[0].report_id
              : $currentTab.data("report-id");
    jq(tab_contents[idx]).show();
    tab_heads.find("a").removeClass("active");
    jq(tab_heads[idx]).find("a").addClass("active");
}

function makeActions() {
    tab_heads.click(function() {
        var idx = jq(this).index();
        goto1(idx);
    });

    
    close.on("click", function(event) {
        event.preventDefault();
        clearTimeout(timer);
        panel.hide();
    });

    
    faqArea.on("mouseover", function() {
        clearTimeout(timer);
        panel.show();
    });

    faqArea.on("mouseout", function(event) {
        timer = setTimeout(function() {
            panel.hide();
        }, 300);
    });
}


var subitem = null;
var questText = "none";
var questLink = "none";
var urlParser = new URLParser(window.location.href);

function initReport() {
    subitem = wx.faq_list.guide_list[0].itemname;
    report_id = wx.faq_list.guide_list[0].report_id;
    
    jq("div.js_faqscene_content").find("a").click(function(event) {
        var $question = jq(this);
        questText = $question.text();
        questLink = $question.attr("href");
        var reportData = getReportData();
        report(reportData);
    });
    
    jq("div.faqscene_ft a").click(function(event) {
        var $more = jq(this);
        questText = $more.text();
        var reportData = getReportData();
        reportData.report_id = wx.faq_list.help_report_id;
        report(reportData);
    });
}


function report(data) {
    
    var reportUrl = "/misc/faq?action=report"
    $.ajax({
        url: reportUrl,
        data: data,
        type: "POST"
    }).success(function(data) {
        
    });
}


function getReportData() {
    var data = {};
    data.report_id = report_id;
    data.question = questText;
    data.action = "report";

    return data;
}


 
function URLParser(url) {
    
    
    var $el = jq("<a></a>").attr("href", url);
    this.el = $el.get(0);
    this.parser = this.el;
}


URLParser.prototype.getParam = function(key) {
    var KEY_REG = new RegExp("([\?&])" + key + "=([^&#]*)([&#])?");
    var result = this.el.search.match(KEY_REG);
    return result
           ? result[2]
           : null;
};


loadDataAndInit();
})(); */
</script>
</body>
</html>