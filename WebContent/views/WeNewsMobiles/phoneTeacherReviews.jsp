<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.core.model.Notices"%>
<%@page import="wtb.smUtil.alipay.config.AlipayConfig"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@page import="wtb.core.model.Competition"%>
<%@page import="wtb.smUtil.tenpay.util.WXUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
	Students user =(Students)request.getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
	Notices ac=(Notices)request.getAttribute("Notices");
	if(ac==null){
		ac=new Notices();
		request.setAttribute("Notices", ac);
	}
%>
<html>
<head>
     <title>名师点评-新浪微新闻社</title>
    <meta charset="utf-8">
    <meta http-equiv="cleartype" content="on">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="application-name" content="名师点评-微新闻社">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <meta name="apple-mobile-web-app-title" content="名师点评-微新闻社">
    <link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css?v=11" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/school/mui.min.css?v=2">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=8">
 
    <style type="text/css">

.mui-checkbox label, .mui-radio label{
            background: rgba(255, 255, 255, .95);
            text-align: left;
            width: 98%;
            padding-right:0;
        }
        .mui-card{
            box-shadow:none;
            margin: 0;text-align: left;
        }
        .mui-table-view-cell > a:not(.mui-btn){color: #e98751}
       .mui-input-row label{padding: 10px 0px;}
       .mui-content > .mui-card:first-child{margin-top: 0px;}
       .layui-layer-page .layui-layer-content {  overflow: auto;}
.mui-content > .mui-card:first-child{margin-top: 0px;}
.affirm a:active,.affirm a:hover {color: #fff}


    </style>
</head>
<body>
<div id="preloader">
    <div id="status">
        <p class="center-text">加载中…<em>请稍等哦!</em></p>
    </div>
</div>
<div class="banner">
    <img src="<%=basePath %>images/weixinwenshe/msdp_banner.jpg?v=1.0.2" id="imgId"/>
</div>
<c:if test="${isApply==2}">
<div id="main " class="bg_4F9BA2">
    <div class="main bg_fffeee " style="margin-bottom: 50px;">
        <h2 class="main_h2 bg_4F9BA2 ">文章批改结果</h2>
        <p class="main_title color_4F9BA2">文章修改：</p>
        <div style="background-color: #EAEAEA;" class="main_p_detail">${ApplyList.content} </div>
        <p class="main_title color_4F9BA2">教师批注：</p>
        <div style="background-color: #EAEAEA;" class="main_p_detail">${ApplyList.checkResult} </div>
        
       <br/>
       <br/>
       <br/>
       <br/>
       <br/>
   </div>
   
</div>
</c:if>
<c:if test="${isApply!=2}">
<div id="main " class="bg_4F9BA2">
    <div class="main bg_fffeee color_4F9BA2" style="margin-bottom: 50px;">
        <h2 class="main_h2 bg_4F9BA2">9块9新浪微新闻社特聘名师为您批改文章</h2>
        <br/>
        <p class="main_title_care color_e98751">“每篇9块9 名师来点评” </p>
        <!-- 一、服务标准-->
        <p class="main_title">微新闻社，特别推出</p>
        <p class="main_p2">9块9能买名师点评啦</p>
        <p class="main_p2">在线文章修改</p>
        <p class="main_p2">24小时内回复</p>
          <br/>
        <p class="main_p_detail">9块9可以精炼你语言</p>
        <p class="main_p_detail">9块9可以提升你的文章</p>
        <p class="main_p_detail">9块9可以找出你写作的痛点</p>
        <p class="main_p_detail">家长们</p>
        <p class="main_p_detail">还在为小编的写作能力差而烦恼吗</p>
        <p class="main_p_detail">还在为报各种写作补习班而发愁吗</p>
        <p class="main_p_detail">还在为补习班价格的昂贵而惆怅吗</p>
       
        
        <!--三、家长说明-->
        <p class="main_title">多说无益，直接上图</p>
       
        <p class="main_title">施同学写了一篇文章</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/weixinwenshe/1.jpeg" data-preview-src="" data-preview-group="1"></p>
        <!--七、关注微信公众号-->
        <p class="main_title">看着密密麻麻地文字</p>
        <p class="main_p2">来个清晰版的 </p>
        <p class=""><img class="main_ewm_img" src="<%=basePath%>images/weixinwenshe/3.gif"></p>
        <p class=""><img class="main_ewm_img" src="<%=basePath%>images/weixinwenshe/2.jpeg"></p>
        <p class="main_ewm_title">还等什么，赶紧来体验吧!</p>
    </div>

</div>
</c:if>
<c:if test="${isApply==1}">
<div class="qnxzb_jg  bg_fdfaf3" style="width: 100%;background-color: #e98751;">
    <div class="text-jg "><h2><strong class="color_e98751"><span style="color:#FFF" >正在等待名师批改~</strong>
     
   </h2></div>
</div>
</c:if>
<c:if test="${isApply==2}">
<div class="qnxzb_jg  bg_fdfaf3" style="width: 100%;background-color: #e98751;">
    <div class="text-jg "><h2><strong class="color_e98751"><span style="color:#FFF" >批改教师：${ApplyList.area }${ApplyList.correctTeacher }</strong>
   </h2></div>
</div>
</c:if>
<c:if test="${isApply==0}">
<div class="qnxzb_jg bg_fdfaf3"  >
    <div class="text-jg "><h2><strong class="color_e98751"><span>仅需：</span>￥<span style="color: #e98751;">9.9</span></strong>&nbsp;
    
   </h2></div>
</div>
<div class="qnxzb_order bg_e98751">申请批改</div>
</c:if>


<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap">
        <a href="" class="edit" title="申请批改"><span>申请批改</span></a>
        <a href="javascript:void(0);" class="top" title="返回顶部"><span>返回顶部</span></a>
    </div>
</div>
<!--弹出框-->
<div id="affirm_join" class="affirm">
    <div class="affirm_up">
        <div class="img_box lf">
            <img src="<%=basePath%>images/weixinwenshe/msdp_ico.png" alt="">
        </div>
        <div class="affirm_jieshao lf">
            <p class="affirm_introduce">新浪微新闻社特聘名师为您批改文章</p>
            <p class="total">仅需&nbsp;￥<span total="1.00">9.9</span>&nbsp;</p>
        </div>
    </div>
    <div class="affirm_down">
    	<div class="" style="  padding-top: 10px;">
            
        </div>
     
    
    <a href="JavaScript:void()" onclick="checkpay()" class="affirm_ok">确定</a>
</div>
</div>

<div class="qnxzb-shade" id="qnxzb-shade"></div>
<div class="qnxzb-popup qnxzb-popup-in" id="qnxzb-div" >
    <div class="qnxzb-popup-inner">
        <div class="qnxzb-popup-title">确认</div>
        <div class="qnxzb-p-detail" id="Surplus">9块9邀请微新闻社特聘名师为您批改文章<p>【${Notices.title }】</p></div>
        <div class="mui-content">
            <div class="mui-card">
                <form class="mui-input-group">
                
                    <div class="qnxzb-qr ">
                        <div class="qnxzb-qr-jg " style="text-align: center;">
                            <h2 style="font-size: 26px;"><strong class="color_e98751"><span>需要付款：</span>￥<span  style="color: #e98751;font-size: 26px;">9.9</span></strong></h2>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <div class="qnxzb-popup-buttons">
        <span class="qnxzb-popup-button" id="qnxzb-cancle">取消</span>
        <span class="qnxzb-popup-button " id="qnxzb-confirm">确定</span></div>
</div>
<a href="javascript:void(0);" class="index-cd-top"></a>
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
<input type="hidden" id="wemoneyNum" name="wemoneyNum" value="0" />
<input type="hidden" id="weimi_value" name="weimi_value" value="9.90" />
<jsp:include page="/include/commonMobileJs.jsp" />

<script src="<%=basePath%>js/school/plugins/layer-v3.0.3/layer.js?v=1.4"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=basePath%>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/school/mui.zoom.js"></script>
<script src="<%=basePath%>js/school/mui.previewimage.js"></script>
<script type="text/javascript">

mui.previewImage();
$(window).load(function () {
    $("#status").fadeOut();
    $("#preloader").delay(350).fadeOut("slow");
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        return;
    } else if (/(Android)/i.test(navigator.userAgent)) {
        return;
    }else if (userAgentInfo.indexOf('Wechat') <= 0 && getQueryString("submitType")=='' ) {
    	window.location = window.location.href+"&submitType=mweb";
        return;
    } else {
        $("#imgId").attr('src',"<%=basePath%>images/school/banner5.jpg");
        // PC端隐藏点击报名的按钮
        $("#toastBtn").css("display","none");
        // PC端隐藏来报名提示
        $("#guide").css("display","block");
        // PC端隐藏加载中提示
        $("#preloader").css("display","none");
    };
});
function callbackFun(obj){
	window.location="<%=AlipayConfig.return_url%>";
}
$("#qnxzb-cancle").click(function(){
    $("#qnxzb-shade").hide();
    $("#qnxzb-div").hide();
});
function checkpay(){
	var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.ServicePay.getid(), String.valueOf(user.getID()), String.valueOf(ac.getID()) ) %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
	var kk=eval("["+attach+"]")[0];
	kk.c = <%= SmBaseGlobal.ServicePayType.Correcting.getid() %>;//表示
	var jsonstr = JSON.stringify(kk);
	initialWePayParam('#qnxzb-confirm',"#weimi_value",jsonstr,getQueryString("WID"));
}
$(".qnxzb_order").click(function(){
    layer.open({
        type: 1,
        title:false,
        closeBtn: 1, //显示关闭按钮
        shift: 2,
        scrollbar: false,
        shadeClose: true, //开启遮罩关闭
        content: $("#affirm_join"),
        area: ['100%','30%'],
        offset: 'rb'
    });
    $(".affirm_jieshao").css("width",parseInt($(".affirm_up").width())-(parseInt($(".img_box").width())+20)+"px");
});
$("#affirm_join .affirm_ok").click(function(){
	if((window.checkparam==null || window.checkparam==undefined) || (window.checkparam  && window.checkparam())){
		layer.closeAll('page');
        $("#qnxzb-shade").show();
        $("#qnxzb-div").show();
	}
    

});

$(function () {
//        向上的按钮
    var offset = 300, offset_opacity = 2000, scroll_top_duration = 700, $back_to_top = $('.index-cd-top');
    $(window).scroll(function () {
        ( $(this).scrollTop() > offset ) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
        if ($(this).scrollTop() > offset_opacity) {
            $back_to_top.addClass('cd-fade-out');
        }
    });
    $back_to_top.on('click', function (event) {
        event.preventDefault();
        $('body,html').animate({
                scrollTop: 0
            }, scroll_top_duration
        );
    });
});


</script>
</body>
</html>
