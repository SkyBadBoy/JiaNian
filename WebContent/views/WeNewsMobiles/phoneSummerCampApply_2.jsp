<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
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
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
	Competition ac=(Competition)request.getAttribute("Competition");
	if(ac==null){
		ac=new Competition();
		request.setAttribute("Competition", ac);
	}
%>
<html>
<head>
     <title>${Competition.title }</title>
    <meta charset="utf-8">
    <meta http-equiv="cleartype" content="on">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="application-name" content="商城">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <meta name="apple-mobile-web-app-title" content="商城">
    <link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css?v=11" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/school/mui.min.css?v=2">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=9">
 
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
        .mui-table-view-cell > a:not(.mui-btn){color: #3ab6d8}
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

<div id="main " class="bg_3ab6d8">
    <div class="main bg_fffeee color_3ab6d8" style="margin-bottom: 50px;">
        <h2 class="main_h2 bg_3ab6d8">${Competition.title }</h2>
        <!--一、理想游泳培训说明-->
        <p class="main_title mt10">一、理想游泳培训说明：</p>
        <p class="main_p_detail">1、每期12节课，每期96人，每班12人。</p>
        <p class="main_p_detail">2、组团报名5人开团，每个团最多10人，在申请表中填入开团号，即可加入组团报名，满5人即可享受组团优惠，首次报名成功，刷新该页面即可获得开团号。</p>
        <p class="main_p_detail">3、详询：柴老师  <a href="tel:18612828361">18612828361</a></p>
       
        <p class=""><img class="main_p_img" src="<%=basePath%>/images/school/lADO0-QPxs0HUs0C7g_750_1874.jpg" data-preview-src="" data-preview-group="1"></p>
        <!--二、营员物品准备表 -->
       
        <p class="main_title">二、理想游泳培训照片：</p>
        
       
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming1.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming2.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming3.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming4.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming5.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming6.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming7.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming8.jpg" data-preview-src="" data-preview-group="1"></p>
        <p class=""><img class="main_p_img" src="<%=basePath%>/img/swimm/swimming9.jpg" data-preview-src="" data-preview-group="1"></p>
      
    </div>

</div>

<c:if test="${isApply==1}">
<div class="qnxzb_jg  bg_fdfaf3" style="width: 100%;background-color: #E6E6E6;">
	<c:if test="${applyCount<=1}">
	    <div class="text-jg "><h2><strong class="color_3ab6d8"><span style="font-size: 10px;">您的开团号为：${Apply}，5人组团立享100元优惠，快邀请好友一起报名吧~</strong>
	   </h2></div>
   </c:if>
   <c:if test="${applyCount>1 and applyCount<5}">
	    <div class="text-jg "><h2><strong class="color_3ab6d8"><span style="font-size: 10px;">您的开团号为：${Apply}，还差${5-applyCount}人立享100元优惠，快邀请好友一起报名吧~</strong>
	   </h2></div>
   </c:if>
   <c:if test="${applyCount>=5}">
	    <div class="text-jg "><h2><strong class="color_3ab6d8"><span style="font-size: 10px;">组团成功，已获得立享100元的优惠，快邀请好友一起报名吧~</strong>
	   </h2></div>
   </c:if>
</div>
</c:if>
<c:if test="${isApply!=1}">
<div class="qnxzb_jg bg_fdfaf3"  >
    <div class="text-jg "><h2><strong class="color_3ab6d8"><span>付款：</span>￥<span style="color: #3ab6d8;">${Competition.stringParamB }</span></strong>&nbsp;
    
   </h2></div>
</div>
<div class="qnxzb_order bg_3ab6d8">提交 报名</div>
</c:if>


<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap">
        <a href="" class="edit" title="点击报名"><span>点击报名</span></a>
        <a href="javascript:void(0);" class="top" title="返回顶部"><span>返回顶部</span></a>
    </div>
</div>
<!--弹出框-->
<div id="affirm_join" class="affirm">
    <div class="affirm_up">
        <div class="img_box lf">
            <img src="<%=basePath%>/img/swimm/WechatIMG1.jpeg" class="img_response">
        </div>
        <div class="affirm_jieshao lf">
            <p class="affirm_introduce">${Competition.title }</p>
            <p class="total">付款金额&nbsp;￥<span total="${Competition.stringParamB}">${Competition.stringParamB }</span></p>
        </div>
    </div>
    <div class="affirm_down">
    
    
    
     <div class="norms main" style="    padding-top: 10px;">
            <p>报名表</p>
            <div class=" ">
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td class="posi-rela"><input name="name" placeholder="请输入中文姓名" type="text" class="srk1 border_3ab6d8" id="name"></td>
                </tr>
                <tr>
                    <td class="posi-rela"><input name="age" placeholder="请输入手机号" type="number" class="srk1 border_3ab6d8" id="phone"
                                                 oninput="if(value.length>11)value=value.slice(0,11)"></td>
                </tr>
                 <tr>
                    <td class="posi-rela"><input name="memo" placeholder="若要组团，请输入开团号" type="text" class="srk1 border_3ab6d8" id="memo"></td>
                </tr>
                </tbody></table>
        </div>
        </div>
        
    	<div class="norms_qc" style="    padding-top: 10px;">
            <p>期次</p>
             <c:forEach var="Prod" items="${period}">
	            <span data-value="${Prod.memo}" value="${Prod.PKID}" class="norms_qc_w ">${Prod.name}(${Prod.memo})</span>
            </c:forEach>
        </div>
        <div class="norms_kc" style="    padding-top: 10px;">
            <p>课程</p>
             <c:forEach var="Prod" items="${Swimcourse}">
	            <span data-value="${Prod.memo}" value="${Prod.PKID}" class="norms_qc_w ">${Prod.name}</span>
            </c:forEach>
        </div>
        <div class="norms_time" style="    padding-top: 10px;">
            <p>课程时间</p>
            <c:forEach var="Prod" items="${coursetime}">
	            <span data-value="${Prod.memo}" value="${Prod.PKID}" class="norms_qc_w ">${Prod.name}</span>
            </c:forEach>
        </div>
      <br/>
    <a href="JavaScript:void()" class="affirm_ok">确定</a>
</div>
</div>

<div class="qnxzb-shade" id="qnxzb-shade"></div>
<div class="qnxzb-popup qnxzb-popup-in" id="qnxzb-div" >
    <div class="qnxzb-popup-inner">
        <div class="qnxzb-popup-title">确认</div>
        <div class="qnxzb-p-detail" id="Surplus">还需费用：<span>1280</span>-<span>100</span>=<span>1180</span></div>
        <div class="mui-content">
            <div class="mui-card">
                <form class="mui-input-group">
                <div class="qnxzb-qr ">
                        <div class="qnxzb-qr-jg " style="text-align: center;">
                            <h2 style="font-size: 13px;" id="comfirmInfo"></h2>
                        </div>
                    </div>
                    <div class="qnxzb-qr ">
                        <div class="qnxzb-qr-jg " style="text-align: center;">
                            <h2 style="font-size: 26px;"><strong class="color_3ab6d8"><span>预报名费用：</span>￥<span  style="color: #3ab6d8;font-size: 26px;">${Competition.stringParamB }</span></strong></h2>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!--<div class="qnxzb-popup-input"><input type="text" class="qnxzb-input" placeholder="请输入推荐人"></div>-->
    </div>
    <div class="qnxzb-popup-buttons">
        <span class="qnxzb-popup-button" id="qnxzb-cancle">取消</span>
        <span class="qnxzb-popup-button " id="qnxzb-confirm">确定</span></div>
</div>
<img src="<%=basePath%>/img/swimm/WechatIMG1.jpeg" style="display:none" class="img_response">
<a href="javascript:void(0);" class="index-cd-top"></a>
<input type="hidden" id="CID" name="CID" value="0" />
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
<input type="hidden" id="wemoneyNum" name="wemoneyNum" value="0" />
<input type="hidden" id="dataContent" name="dataContent" value="${Competition.title }新学员报名立减100元" />
<input type="hidden" id="weimi_value" name="weimi_value" value="${Competition.stringParamB }" />
<jsp:include page="/include/commonMobileJs.jsp" />

<script src="<%=basePath%>js/school/plugins/layer-v3.0.3/layer.js?v=1.4"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=basePath%>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/school/mui.zoom.js"></script>
<script src="<%=basePath%>js/school/mui.previewimage.js"></script>
<script src="<%=basePath%>js/school/globe.js?v=5"></script>
<script type="text/javascript">
var share_title= "${Competition.title }";
mui.previewImage();
function callbackFun(obj){
	$.ajax({
		url:"<%=path%>/WeMoney/phoneshareAddIntegral",
		data:{
			'sid':'<%=user.getID() %>',
			'newsid':'${Competition.PKID}',
			'type':'3',
			'intParamB':'100'
		},
		success : function(obj) {
			window.location.reload();
		
		}});
}
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
        $("#imgId").attr('src',"<%=basePath%>/img/swimm/WechatIMG1.jpeg");
        // PC端隐藏点击报名的按钮
        $("#toastBtn").css("display","none");
        // PC端隐藏来报名提示
        $("#guide").css("display","block");
        // PC端隐藏加载中提示
        $("#preloader").css("display","none");
    };
});

$("#qnxzb-cancle").click(function(){
    $("#qnxzb-shade").hide();
    $("#qnxzb-div").hide();
});
function checkpay(){
	var str="您选择了：";
	var num=${Competition.stringParamA};
	var cousr=0;
	var period=0;
	var time=1;
	if($(".norms_qc .active").length>0){
		//period = $(".norms_qc .active").attr("value");
		str+=$(".norms_qc .active").text();
	}
	
	if($(".norms_kc .active").length>0){
		//cousr = $(".norms_kc .active").attr("value");
		str+="；"+$(".norms_kc .active").text();
	}
	if($(".norms_time .active").length>0){
		//time = $(".norms_time .active").attr("value");
		str+="；"+$(".norms_time .active").text();
	}
	
	$('#comfirmInfo').text(str);
	var c=new Object();
	c.w=$("#CID").val();
	c.b='0';
	c.c= cousr+'-'+time+'-'+period;
	var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.OtherPay.getid(), String.valueOf(user.getID()), String.valueOf(ac.getID()) ) %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
	var kk=eval("["+attach+"]")[0];
	kk.c = c;
	var jsonstr = JSON.stringify(kk);
	$("#weimi_value").val("100");
	initialWePayParam('#qnxzb-confirm',"#weimi_value",jsonstr,getQueryString("WID"));
		
	
	
	
	
}

mui('.mui-input-group').on('change', 'input', function() {  
    var value = this.checked?"true":"false";  
}); 

$(".norms_qc_w").click(function() {
    $(this).addClass("active").siblings().removeClass("active");
})

$("#affirm_join .affirm_ok").click(function(){
	layer.closeAll('page');
	if(checkparam()){
        $("#qnxzb-shade").show();
        $("#qnxzb-div").show();
        checkpay();
	}
		
	
});

function checkparam(){
	 if(!$(".norms_qc .norms_qc_w").hasClass('active')){
		   webToast("请选择报名期次","bottom",1800);
		   return false;
	    }
	   
	   if(!$(".norms_kc .norms_qc_w").hasClass('active')){
		   webToast("请选择游泳课程","bottom",1800);
		   return false;
	   }
	   if(!$(".norms_time .norms_qc_w").hasClass('active')){
		   webToast("课程时间","bottom",1800);
		   return false;
	  }
	 
	if($("#name").val()=="" || $("#phone").val()==""){
		webToast("请填写联系人和联系电话","bottom",1800);
		 return false;
	}
	var isresult=($.ajax({
		url : "${path}/Students/phoneInputPayInfo?sina=<%=SmBaseUtil.Random()%>",
		type:"POST",
		 async: false,
		data : {
			'name' : $("#name").val(),
			'phone' : $("#phone").val(),
			'StringParamF' : $("#memo").val(),
			'pkid' : '${Competition.PKID}',
			'StringParamB':$("#CID").val(),
			'StringParamC':$(".norms_kc .active").attr("value"),
			'StringParamD' : $(".norms_time .active").attr("value"),
			'StringParamE' : $(".norms_qc .active").attr("value")
			
		},
		success : function(json) {
			if(json.Status==false || json.Status=="false"){
				 webToast(json.Message,"bottom",1800);
				 return false;
			}
			$("#CID").val(json.CID);
			return true;
		}
	}));
	if(isresult.responseJSON.Status=='false'){
		 return false;
	}
	return true;
}
</script>
</body>
</html>
