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
     <title>海南童子营槟榔谷教育基地7天6夜精英训练营</title>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=7">
 
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


.qnxzb_p{margin: 10px 10px 5px 0;}
.main_p_img{margin-left: 2%;width: 96%;}
.bk_fff{background: #fff;}
.bk_000{background: rgba(0,0,0,0.5);}
.color_000{color: rgba(0,0,0,0.5);}
.t_c{text-align: center;margin: 2px;}
.main_p_span{font-weight: 700}
.img_p{
    margin: 8px 0;
}
.main_h2 {
        font-size: 20px;
        height: 60px;
        line-height: 60px;
        margin: 0 3%;

    }
    </style>
</head>
<body>
<div id="preloader">
    <div id="status">
        <p class="center-text">加载中…<em>请稍等哦!</em></p>
    </div>
</div>
<div class="banner">
    <img src="<%=basePath%>images/school/tzy1.png" id="imgId"  data-preview-src="" data-preview-group="1" />
</div>
<div id="main " class="bk_fff">
    <div class="main bk_fff color_000">
        <!--<div class="protocol_title">新浪微新闻社暑期集训营</div>-->
        <h2 class="main_h2 bk_000">海南童子营槟榔谷教育基地7天6夜精英训练营 </h2>
        <!--<p class=""><img class="main_p_img" src="images/jxy1.png" data-preview-src="" data-preview-group="1" ></p>-->
        <p class="act_title ">活动详情：</p>
        <!--今年暑假到底怎么过-->
        <p class="t_c">今年暑假到底怎么过？</p>
        <p class="t_c">锻炼意志品质、打开思维模式、重塑创造力与领导力……</p>
        <p class="t_c">还能有阳光、沙滩和蓝天？</p>
        <p class="t_c">这个暑假，就在海南槟榔谷！</p>
        <p class="t_c">7天6夜精英训练营</p>
        <p class="t_c">5A级景区！</p>
        <p class="t_c">全外教阵容！</p>
        <p class="t_c">美国百年精英教育正式来到中国</p>
        <p class="t_c">这个暑假一起和你嗨翻天！</p>
        <!-- 五大优势-->
        <div class="maindiv"></div>
        <p class="main_title">一、五大优势</p>

        <p class="main_p_detail"><span class="main_p_span">1、资质优势：</span></p>

        <p class="main_p_detail"> 学员毕业证书颁发机构：美国银杏教育基金会（非盈利组织）；美国国家营地协会（ACA）；MSA 营地教育协会；美国赛维思教育集团。</p>

        <p class="main_p_detail"><span class="main_p_span">2、纯外教授课，课程优势：</span></p>

        <p class="main_p_detail"> 全外教授课，小班化教学，每班最高人数不超过20人，让孩子体验纯正的美式英语教学，一般课程我们教官与孩子的配比为1：7（14个孩子配备1个外教和1个助教），
            特殊课程例如水上项目，我们的助 教比例会增加一倍。同时我们每位外教也相应配备了中式助教帮助孩子和教官良好的沟通，使孩子能 够充分的享受到我们的精英教育；</p>

        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy2.png" data-preview-src="" data-preview-group="1"></p>

        <p class="main_p_detail"><span class="main_p_span">3、环境优势，天然氧吧，5A级景区：</span></p>

        <p class="main_p_detail"> 营地坐落在中国首家民族文化型5A级景区槟榔谷中，这里万余棵槟榔林海，古木参天、藤蔓交织热
            带雨林，这里夏无酷暑，冬无冰雪，年平均气温在15～20℃之间，每立方厘米负离子含量多达60多
            万个，内有山瑞、坡垒树、野生白茶树等多种国家二级保护动植物。她山之青、水之秀、</p>

        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy3.png" data-preview-src="" data-preview-group="1"></p>

        <p class="main_p_detail"><span class="main_p_span">4、营地优势：</span></p>

        <p class="main_p_detail">营地由美国营地专家专门设计建造评估，符合国际营地的安全标准。且设施齐备、布局合理、方便温
            馨、安全卫生；大多数的运动都需要在一定条件下更专业的器材，这些器材设备都会被我们妥善的安 装并且仔细严格按照安全细则的要求进行安装和使用。我们的导师每时每刻都会检查我们设备的安全
            性，并且标明此设备有无问题所在，并及时上报。</p>

        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy4.png" data-preview-src="" data-preview-group="1"></p>


        <p class="main_p_detail"><span class="main_p_span">5、管理优势：</span></p>

        <p class="main_p_detail"> 对不同学员量身打造安全专业健康的营地生活体验和课程安排，例如我们会为不同健康和信仰的孩
            子提供相应的营养配餐，并为其带上相应的标识予以特殊的关注，以保证其在营地的舒适安全；</p>

        <p class="main_p_detail"> 互动交流平台（每日微信群内家长、老师、教练互动交流），每日更新大量孩子的活动照片并上。
            传至家长微信群内，配以固定的亲子热线时间，以便家长及时了解孩子在营地的情况；同时营地配 备齐全的医疗团队，经验丰富的医生及相应的应急车辆。</p>

        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy5.png" data-preview-src="" data-preview-group="1"></p>

        <!-- 二、你的收获-->
        <p class="main_title">二、你的收获</p>
        <p class="main_p_detail">1、自主学习，丰富体验，正确掌握生存技能并安全享受户外活动带来的酣畅淋漓</p>
        <p class="main_p_detail">2、克服困难，锻炼心智，培养坚韧不拔的优秀品格</p>
        <p class="main_p_detail">3、团队任务，携手共进，提升沟通、协作、领导的综合能力</p>
        <p class="main_p_detail">4、7天6晚，生活自理，养成独立自主的习惯</p>
        <!-- 三、主要内容简介-->
        <p class="main_title">三、主要内容简介</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy6.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">营地课程1：第一天：抵达营地+开营仪式+团队活动+开营篝火晚会（Day 1:Arrival + Opening Ceremony+ Night activities +Opening Campfire）</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy7.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">营地课程2：营地课程（水上运动课程（独木舟、皮划艇、救生、钓鱼等）＋体验1（北极熊挑战、浆板、一英里游泳）+团队竞赛+夜间活动）（Day 2:Camp Course (Aquatics course + ExperienceLesson+ Games Competition+ Night activities)）</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy8.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">营地课程3：第三天：营地课程（户外运动课程（射箭、射击）＋体验2（北极熊挑战、浆板、一英里游泳）+团队竞赛+夜间活动）（Day3: Camp Course (Field Sports Lesson－Experience Lesson+ Games Competition+ Night activities )）</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy9.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">营地课程4：第四天：营地课程（手工课程（编织、陶艺、皮具）团队竞赛+夜间活动）（Day4: Camp Course (Handicraft Lesson－Experience Lesson+ Games Competition+ Night activities)）</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy10.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">第五天：营地课程（小主播课程+童子营课程+选修课程+直播采访）</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy11.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">第六天：营地课程（小主播课程+童子营课程+选修课程+直播采访+篝火晚会）</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy12.png" data-preview-src="" data-preview-group="1" ></p>
        <p class="main_p">第七天：上午小主播课程，下午结营仪式</p>
        <!--五、 日程安排-->
        <p class="main_title">五、日程安排</p>
        <p class="img_p"><img class="main_p_img" src="<%=basePath%>images/school/tzy13.png" data-preview-src="" data-preview-group="1" ></p>
        <!--报名详情-->
        <div class="maindiv"></div>
        <p class="main_title">报名详情</p>
        <!-- 一、蛇蟠岛景区时间表-->
        <p class="main_title">一、【开营日期及价格】</p>
        <p class="main_p_detail">1、2017年6月25日-7月1日（6950元/人）</p>
        <p class="main_p_detail">2、2017年7月2日-7月8日（6950元/人）</p>
        <p class="main_p_detail">3、2017年7月9日-7月15日（6950元/人）</p>
        <p class="main_p_detail">4、2017年7月16日-7月22日（6950元/人）</p>
        <p class="main_p_detail">5、2017年7月23日-7月29日（6950元/人）</p>
        <p class="main_p_detail">6、2017年7月30日-8月5日（6950元/人）</p>
        <p class="main_p_detail">7、2017年8月6日-8月12日（6950元/人）</p>
        <p class="main_p_detail">8、2017年8月13日-8月19日（6950元/人）</p>
        <p class="main_p_detail">9、2017年8月20日-8月26日（6950元/人）</p>
        <p class="main_p_detail">（所有营期报名截止日均为每期开营前两周）</p>
        <!-- 二、诸暨米果果基地-->
        <p class="main_title">二、【费用说明】</p>
        <p class="main_p_detail">1、费用包含： 住宿费用、入营五件套（制服衣服、制服裤子、制服领巾、训练T恤、野营袜  子）</p>
        <p class="main_p_detail">营养配餐餐费、5A级景区槟榔谷门票、挑战项目费用及基地文化课程等。</p>
        <p class="main_p_detail">2、费用不含： 来回海南三亚凤凰机场交通费，个人物品及个人消费。</p>
        <!-- 三、费用说明-->
        <p class="main_title">三、【招生范围】：</p>
        <p class="main_p_detail">1、8-18周岁的儿童及青少年</p>
        <p class="main_p_detail">2、身体健康，如有对食物和物品的过敏反应，请在报名时提出。餐食上，我们尊重营员的宗教信仰。请家长提前告知，以便我们做好准备；</p>
        <p class="main_p_detail">3、有一定独立生活能力，提前准备好必备的生活用品：如水壶、小背包、洗澡用品等；</p>
        <p class="main_p_detail">4、小营员们有一定的英文基础更利于他们与外国教官更顺畅地沟通哦；</p>
        <p class="main_p_detail">5、入营前营员的监护人必须签署监护人授权书，以便我们在营地生活中可以更好的照顾到每一位营员的健康和及时应对紧急事件。</p>
        <!-- 四、招生范围-->
        <p class="main_title">四、【集合地点】三亚凤凰机场：</p>
        <p class="main_p_detail">  我们将会有专门的老师负责接机</p>
        <!--五、【报名方式】-->
        <p class="main_title">五、【报名方式】</p>
        <p class="main_p2">杨老师  <a href="tel:15355930038">15355930038</a></p>
        <!--九、点击关注二维码-->
        <p class=""><img class="main_ewm_img" src="<%=basePath%>images/school/QRCode.png"></p>
        <p class="main_ewm_title">扫描上方二维码点击关注公众号!</p>
    </div>
</div>
<c:if test="${isApply==1}">
<div class="qnxzb_jg  bg_fdfaf3" style="width: 100%;background-color: #E6E6E6;">
    <div class="text-jg "><h2><strong class="color_e98751"><span>您已报名，快邀请好友一起报名吧~</strong>
     
   </h2></div>
</div>
</c:if>
<c:if test="${isApply!=1}">
<div class="qnxzb_jg bg_fdfaf3"  >
    <div class="text-jg "><h2><strong class="color_e98751"><span>付款：</span>￥<span style="color: #e98751;">${Competition.stringParamB }</span></strong>&nbsp;
    
   </h2></div>
</div>
<div class="qnxzb_order bg_e98751">提交 报名</div>
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
            <img src="<%=basePath%>images/school/tzy2.png" alt="">
        </div>
        <div class="affirm_jieshao lf">
            <p class="affirm_introduce">海南童子营槟榔谷教育基地7天6夜精英训练营</p>
            <p class="total">付款金额&nbsp;￥<span total="${Competition.stringParamB}">${Competition.stringParamB }</span></p>
        </div>
    </div>
    <div class="affirm_down">
    	<div class="norms_qc" style="    padding-top: 10px;">
            <p>夏令营期次</p>
             <c:forEach var="Prod" items="${Swimcourse}">
	            <span data-value="${Prod.memo}" value="${Prod.PKID}" class="norms_qc_w ">${Prod.name}(${Prod.memo})</span>
            </c:forEach>
        </div>
        
     
    
    <a href="JavaScript:void()" onclick="checkpay()" class="affirm_ok">确定</a>
</div>
</div>

<div class="qnxzb-shade" id="qnxzb-shade"></div>
<div class="qnxzb-popup qnxzb-popup-in" id="qnxzb-div" >
    <div class="qnxzb-popup-inner">
        <div class="qnxzb-popup-title">确认</div>
        <div class="qnxzb-p-detail" id="Surplus">剩余金额：<span>6950</span>-<span>600</span>=<span>6350</span></div>
        <div class="mui-content">
            <div class="mui-card">
                <form class="mui-input-group">
                
                    <div class="qnxzb-qr ">
                        <div class="qnxzb-qr-jg " style="text-align: center;">
                            <h2 style="font-size: 26px;"><strong class="color_e98751"><span>需要付款：</span>￥<span  style="color: #e98751;font-size: 26px;">${Competition.stringParamB }</span></strong></h2>
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
<a href="javascript:void(0);" class="index-cd-top"></a>
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
<input type="hidden" id="wemoneyNum" name="wemoneyNum" value="0" />
<input type="hidden" id="weimi_value" name="weimi_value" value="${Competition.stringParamB }" />
<jsp:include page="/include/commonMobileJs.jsp" />

<script src="<%=basePath%>js/school/plugins/layer-v3.0.3/layer.js?v=1.4"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=basePath%>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/school/mui.zoom.js"></script>
<script src="<%=basePath%>js/school/mui.previewimage.js"></script>
<script src="<%=basePath%>js/school/globe.js?v=2"></script>
<script type="text/javascript">
mui.previewImage();
function callbackFun(obj){
	window.location.reload();
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
        $("#imgId").attr('src',"<%=basePath%>images/school/tzy/tzy2.png");
        // PC端隐藏点击报名的按钮
        $("#toastBtn").css("display","none");
        // PC端隐藏来报名提示
        $("#guide").css("display","block");
        // PC端隐藏加载中提示
        $("#preloader").css("display","none");
    };
});
function callbackFun(obj){
	if(obj!=1 && obj!=2){//1是朋友圈 2是好友
		window.location="<%=AlipayConfig.return_url%>";
	}
}
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
		period = $(".norms_qc .active").attr("value");
		str+=$(".norms_qc .active").text();
	}
	var c=new Object();
	c.w=0;
	c.b='0';
	c.c= cousr+'-'+time+'-'+period;
	var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.MatchPay.getid(), String.valueOf(user.getID()), String.valueOf(ac.getID()) ) %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
	var kk=eval("["+attach+"]")[0];
	kk.c = c;
	var jsonstr = JSON.stringify(kk);
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
	return true;
}
</script>
</body>
</html>
