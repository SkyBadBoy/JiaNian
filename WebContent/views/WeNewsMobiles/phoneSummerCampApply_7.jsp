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
		ac.setPKID("842041063417974783");
		request.setAttribute("Competition", ac);
	}
%>
<html>
<head>
     <title>夏令营之“我是小健将”</title>
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


    </style>
</head>
<body>
<div id="preloader">
    <div id="status">
        <p class="center-text">加载中…<em>请稍等哦!</em></p>
    </div>
</div>
<div class="banner">
    <img src="<%=basePath %>images/lALO62eOMs0B9M0C7g_750_500.png?v=1.0.1" class="img_response"  id="imgId"/>
</div>
<div id="main " class="bg_26a052">
    <div class="main bg_fffeee color_26a052" style="margin-bottom: 50px;">
        <h2 class="main_h2 bg_26a052">夏令营之“我是小健将”</h2>
        <p class="act_title "> 5天励志营活动方案：</p>
        <!--一、相关信息-->
        <p class="main_title">一、相关信息：</p>
        <p class="main_p_detail">活动费用：1880元/人 </p>
        <p class="main_p_detail">行程天数：5天4夜 </p>
        <p class="main_p_detail">适合对像：7-15周岁身体健康在校中小学生</p>
        <p class="main_p_detail">营会地点：诸暨米果果小镇</p>
        <p class="main_p_detail">特别注意：报名时支付全额营期学费确定名额。</p>
        <!--二、 开营时间-->
        <p class="main_title">二、开营时间</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/WX20170714-000908@2x.png?v=1" data-preview-src="" data-preview-group="1"></p>
        <p class="main_p_detail">共招4期，每期时间5天，每期限招300人（按年龄段进行分班，每班15人-20人）。</p>
        <!--行程安排-->
        <div class="maindiv"></div>
        <p class="main_title">行程安排</p>
        <!-- 一、First Day（第一天）-->
        <p class="main_title">一、First Day（第一天）</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/school/qnxzb2.png?v=1" data-preview-src="" data-preview-group="1"></p>
        <!-- 二、Second Day（第二天）-->
        <p class="main_title">二、Second Day（第二天）</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/school/qnxzb3.png?v=1" data-preview-src="" data-preview-group="1"></p>
        <!-- 三、Third Day（第三天）-->
        <p class="main_title">三、Third Day（第三天）</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/school/qnxzb3.png?v=1" data-preview-src="" data-preview-group="1"></p>
        <!--四、 Fourth Day（第四天）-->
        <p class="main_title">四、Fourth Day（第四天）</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/school/qnxzb4.png?v=1" data-preview-src="" data-preview-group="1"></p>
        <!--五、Fifth Day（第五天）-->
        <p class="main_title">五、Fifth Day（第五天）</p>
  
        <p class=""><img class="main_p_img" src="<%=basePath%>images/school/qnxzb8.png?v=1" data-preview-src="" data-preview-group="1"></p>
        <p class="main_p_detail">每天下午14：00-15:30上选修课</p>
        <!-- 服务标准-->
        <div class="maindiv"></div>
        <p class="main_title ">额外说明</p>
        <!-- 一、服务标准-->
        <p class="main_title">一、服务标准</p>
        <p class="main_p_detail">1、营地食宿、训管、服装、道具、资料等费用：1880元/人。</p>
        <p class="main_p_detail">2、增值服务：（主要指除课程教学外的服务）：</p>
        <p class="main_pt_detail">①每天由助教辅导学员暑假作业。</p>
        <p class="main_pt_detail">②营员报名获赠 夏令营儿童精美纪念活动套装（帽子1顶、迷彩服两套）。</p>
        <p class="main_pt_detail">③营员营期全程获赠价值100000元人身意外伤害保险。</p>
        <p class="main_pt_detail">④对于表现优秀的学生颁发奖状。</p>
        <p class="main_pt_detail">⑤每天夏令营辅导员都会在微信群（QQ群传回照片）可以让家长们第一时间了解孩子们的情况。</p>
        <p class="main_pt_detail">⑥军用水壶一个。</p>
        <p class="main_pt_detail">⑦新浪微新闻社臂章一枚。</p>
        <!--二、注意事项-->
        <p class="main_title_care color_e98751">二、注意事项</p>
        <p class="main_p2">1、年满7—18周岁，身体健康，无先天性疾病或遗传病史之少年均可报名。</p>
        <p class="main_p2">2、家长（监护人）签署招生声明，并提供有效身份证件复印件和24小时联系电话及紧急呼叫备用电话。</p>
        <p class="main_p2">3、禁止携带大量现金及贵重物品，可携带少量零花钱（交由辅导员统一保管）。</p>
        <p class="main_p2">4、禁止携带游戏机、笔记本电脑、刀具等物品，如携带手机，入营交辅导员统一保管。</p>
        <p class="main_p2">5、并请自备个人洗漱用具（牙缸、牙刷、牙膏、毛巾）、用品（换洗内衣裤、外衣裤各两套，袜子三双、运动鞋二双、拖鞋一双）及其它个人所需物品，尽量避免携带贵重物品。</p>
        <!--三、家长说明-->
        <p class="main_title">三、家长说明：</p>
        <p class="main_p_detail">1、关于QQ照片：辅导员主要的职责是负责孩子的生活纪律和安全，在安全允许的状态下，我们会安排他们跟队拍摄，同时每天的微信照片将集中三个时间段上传（中餐、晚餐、睡觉前）。</p>
        <p class="main_p_detail">2、关于喝水：孩子房间内烧水壶处于安全考虑，将会被收掉。休息区有桶装水。随队老师会安排装水。</p>
        <p class="main_p_detail">3、关于想家的孩子：根据我们的经验，基本都为第一天晚上快到睡觉的时候，开始想妈妈，然后第二天基本没事了。但是这个时候，往往也是家长最想知道孩子想不想妈妈的时候，所以建议那些第一次出门的，而你又特别想孩子的，打电话时间安排在中午，晚上不打。或者可以通过辅导员了解孩子的信息，通过打辅导员的手机给孩子接听。这样容易帮助孩子渡过想家期。</p>
        <p class="main_p_detail">4、不会洗澡洗头的孩子：辅导员会帮助孩子们洗手，每个寝室都有吹风机。</p>
        <p class="main_p_detail">5、不会洗衣服的孩子：在家先教会洗一次衣服，特别洗涤过程。</p>
        <p class="main_p_detail">6、不会梳辫子的孩子：我们会在早餐时安排专门的老师梳理辫子，由于孩子容易弄丢橡皮筋，建议家长多带几根橡皮筋。</p>
        <p class="main_p_detail">7、整理衣物：家长只是协助，行李箱里每个孩子都要有一个行李清单，然后跟孩子做一个约定，如果回来不丢一样东西有奖励。</p>
        <p class="main_p_detail">8、物品署名：为了防止东西丢失之后，无人认领，请家长在衣服的标签上，水杯的杯底，背包的里面，帽子的里面等，都要防水笔，写上孩子的名字。不用不干胶贴纸，容易掉。</p>
        <p class="main_p_detail">9、出门前跟孩子约定：约定孩子最弱的部分（要具体到事物：比如洗澡，比如刷牙、比如哭鼻子）而不是叮嘱要听话。</p>
        <!--四、特别约定-->
        <p class="main_title">四、特别约定：</p>
        <p class="main_p_detail">1、家长打电话的技巧：不要直接就问想不想妈妈？问：你今天学到了什么？交了哪些新朋友？今天最开心的事情？有没有什么好玩的事情跟妈妈分享一下？</p>
        <p class="main_p_detail">2、关于照片：活动途中，辅导员以保障孩子的安全为主，中途的空挡，我们会安排辅导导员拍照，但是由于辅导员的个人技术不一，所以请家长不要比较，照片的多少和照片的质量，拍照的目的是为了让你了解孩子今天一天干了什么。</p>
        <p class="main_p_detail">3、家长容易在微信中远程遥控的问题：远程指挥孩子穿脱衣服？或者衣服颜色的搭配？喝水？衣服领子是不是拉好？.....本次活动的目的就是让孩子能够独立生活，所以建议家长尽量不干涉，同时喝水我们会经常提醒。</p>
        <p class="main_p_detail">4、关于单独拍照:我们在活动中多次发现，有伙伴一起来，家长要求跟小伙伴一起合影，由于有的时候，分组的时候，组别不同，小伙伴不能同时拍单体合影，请家长谅解。</p>
        <p class="main_p_detail">5、关于要好的伙伴一个房、一个组的要求：我们尽量满足，但由于房型和队型的问题，如满足不了请家长谅解，但同时我们也想告知家长，本次活动还有一个目的是让孩子多接触社会，多交朋友为目的，并不建议形成小圈子文化。另孩子的陌生到熟悉，一般只需要5分钟。</p>
        <p class="main_p_detail">6、关于打电话：为了防止活动中出现的以外，在活动期间,我们不建议家长打电话，同时孩子的手机白天一律不携带在身边，请家长在规定电话时间打，晚上电话时间（20:00-21:00之间），具体时间辅导员在群里及时公布。</p>
        <p class="main_p_detail">7、关于紧急联系：另每个辅导员的手机电话都会在第一天公布，请家长保存，但是不随时打电话给辅导员，辅导员的电话是在遇到紧急状态的时候打的。</p>
        <p class="main_p_detail">8、关于口服药物：除仁丹和外用药物以外，辅导员一律无权利给孩子服用药物，所有口服药物，都必须得到家长的微信授权，或医生的处方，所以请家长配合及时回复。</p>
        <p class="main_p_detail">9、关于蚊子：蚊子是夏秋天的一个特性，容易招蚊子的孩子，我们也会配备驱蚊水，但是特别容易招蚊子的孩子请自行携带驱蚊设备，同时容易造成蚊虫叮咬后皮肤溃疡的孩子，一定要自行佩带外用药物，并提前告知。</p>
        <!--五、营员物品准备表 -->
        <p class="main_title">五、营员物品准备表：</p>
        <p class=""><img class="main_p_img" src="<%=basePath%>images/school/qnxzb9.png" data-preview-src="" data-preview-group="1"></p>
        <!--七、关注微信公众号-->
        <p class="main_title">六、关注报名</p>
        <p class="main_p2">微信号：Y956553141 </p>
        <p class="main_p2">详询：</p>
        <p class="main_p2">杨老师  <a href="tel:15355930038">15355930038</a></p>
        <p class=""><img class="main_ewm_img" src="<%=basePath%>images/yqr.png"></p>
        <p class="main_ewm_title">扫描上方二维码了解详情!</p>
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
    <div class="text-jg "><h2><strong class="color_e98751"><span>付款：</span>￥<span style="color: #e98751;">1880</span></strong>&nbsp;
    
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
            <img src="<%=basePath%>images/lALO62eOMs0B9M0C7g_750_500.png" class="img_response" alt="">
        </div>
        <div class="affirm_jieshao lf">
            <p class="affirm_introduce">夏令营之“我是小健将”</p>
            <p class="total">付款金额&nbsp;￥<span total="1880">1880</span></p>
        </div>
    </div>
    <div class="affirm_down">
    <div class="norms_" style="    padding-top: 10px;">
    		<p>预定信息</p>
	        <div class="main">
	            <table border="0" cellspacing="0" cellpadding="0">
	                <tbody><tr>
	                    <td class="posi-rela"><input name="name" value="<%=user.getName() %>" placeholder="请输入中文姓名" type="text" class="srk1 bd_d74b68" id="name"></td>
	                </tr>
	                <tr>
	                    <td class="posi-rela"><input name="age"  value="<%=user.getPhone() %>" placeholder="请输入手机号" type="number" class="srk1 bd_d74b68" id="phone"
	                                                 oninput="if(value.length>11)value=value.slice(0,11)"></td>
	                </tr>
	                </tbody></table>
	        </div>
	    </div>
    	<div class="norms_qc" style="    padding-top: 10px;">
            <p>夏令营期次</p>
	         <span data-value="1" value="1" class="norms_qc_a active">第一期</span>
	         <span data-value="2" value="2" class="norms_qc_a ">第二期</span>
	         <span data-value="3" value="3" class="norms_qc_a ">第三期</span>
	         <span data-value="4" value="4" class="norms_qc_a ">第四期</span>
        </div>
        <div class="norms" style="    padding-top: 10px;">
            <p>选修课(免费，三选一)：每天下午14：00-15:30上选修课</p>
            <c:forEach var="Prod" items="${OptionalCourse}">
	            <span data-value="${Prod.memo}" value="${Prod.PKID}" class="norms_a ">${Prod.name}</span>
            </c:forEach>
        </div>
     
    
    <a href="JavaScript:void()" onclick="checkpay()" class="affirm_ok">确定</a>
</div>
</div>

<div class="qnxzb-shade" id="qnxzb-shade"></div>
<div class="qnxzb-popup qnxzb-popup-in" id="qnxzb-div" >
    <div class="qnxzb-popup-inner">
        <div class="qnxzb-popup-title">确认</div>
        <div class="mui-content">
            <div class="mui-card">
                <form class="mui-input-group">
                
                    <div class="qnxzb-qr ">
                        <div class="qnxzb-qr-jg " style="text-align: center;">
                            <h2 style="font-size: 26px;"><strong class="color_e98751"><span>需要付款：</span>￥<span  style="color: #e98751;font-size: 26px;">1880</span></strong></h2>
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
<input type="hidden" id="weimi_value" name="weimi_value" value="1880" />
<input type="hidden" id="dataContent" name="dataContent" value="夏令营之“我是小健将”  5天4夜  诸暨米果果小镇  只要1880元" />

<jsp:include page="/include/commonMobileJs.jsp" />

<script src="<%=basePath%>js/school/plugins/layer-v3.0.3/layer.js?v=1.4"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=basePath%>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/school/mui.zoom.js"></script>
<script src="<%=basePath%>js/school/mui.previewimage.js"></script>
<script src="<%=basePath%>js/school/globe.js?v=2"></script>
<script type="text/javascript">
computerTotal();
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
        $("#imgId").attr('src',"<%=basePath%>images/lALO62eOMs0B9M0C7g_750_500.png");
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
	var str="剩余金额：";
	var num=0;
   if($(".norms_a").hasClass('active')){
       // $("#affirm_join p.total span").text($(".norms .active").attr('data-value'));
        dj=parseFloat($(".norms .active").attr('data-value'));
        //alert(dj);
        str=str+"+"+$(".norms .active").attr('data-value');
        num=num+dj;
    }
   
   if($(".norms_wm_a").hasClass('active')){
   		wemoney = parseFloat($(".norms_wm .active").attr('data-value'))/10;
   		str=str+"-"+wemoney;
   		num=num-wemoney;
   }
   if($(".norms_yj_a").hasClass('active')){
  		var yj = parseFloat($(".norms_yj .active").attr('data-value'));
  		str=str+"+"+yj;
  		num=num+yj;
  }
   str=str+"="+num;
	$("#Surplus").html(str);
	if(checkparam()){
		
	}
	$.ajax({
		url : "${path}/Students/phoneuserinfoedit_temp?sina=<%=SmBaseUtil.Random()%>",
		type:"POST",
		data : {
			'StudentName' : $("#name").val(),
			'StudentPhone' : $("#phone").val()
		},
		success : function(json) {
		}
	});
	var cousr=0;
	var WeMoney=0;
	var photo=0;
	var qc=1;
	if($(".norms .active").length>0){
		cousr = $(".norms .active").attr("value");
	}
	if($(".norms_wm .active").length>0){
		WeMoney = $(".norms_wm .active").attr("data-value");
	}
	if($(".norms_yj .active").length>0){
		photo = 1;
	}
	if($(".norms_qc .active").length>0){
		qc = $(".norms_qc .active").attr("data-value");
	}
	var c=new Object();
	c.w=WeMoney;
	c.b='0';
	c.c= cousr+'-'+photo+'-'+qc;
	var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.MatchPay.getid(), String.valueOf(user.getID()), String.valueOf(ac.getPKID()) ) %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
	var kk=eval("["+attach+"]")[0];
	kk.c = c;
	var jsonstr = JSON.stringify(kk);
	initialWePayParam('#qnxzb-confirm',"#weimi_value",jsonstr,getQueryString("WID"));
	
}

mui('.mui-input-group').on('change', 'input', function() {  
    var value = this.checked?"true":"false";  
	$("#checkbox1").attr("checkeded",value);
	
    computerTotal();
}); 
function computerTotal(){
	 var norms_yj =$(".norms_yj .active").attr('data-value');
    if(norms_yj==undefined){
    	norms_yj="0";
    }
	var dj=$(".norms .active").attr('data-value');
    if(dj==undefined){
    	dj="0";
    }
    var wemoney=0;
    if($("#checkbox1").attr("checkeded")=="true"){
    	wemoney = parseFloat($("#wemoneyNum").val())/10;
    }
    var itemPirce = parseFloat(dj);
    var num = parseInt($("#affirm_join p.total span").attr("total"));
    var total = itemPirce + num-wemoney-parseFloat(norms_yj);
    $("#affirm_join .total span").text(total.toFixed(2));
    $("#weimi_value").val(total);
    $($(".qnxzb-qr-jg span").get(1)).text(total.toFixed(2));
    $($(".text-jg span").get(1)).text(total.toFixed(2));
}

$(".norms_wm_a").click(function() {
	if($(this).hasClass('active')){
    	$(this).removeClass("active");
    }else{
    	$(this).addClass("active").siblings().removeClass("active");
    }
    var norms_yj =$(".norms_yj .active").attr('data-value');
    if(norms_yj==undefined){
    	norms_yj="0";
    }
    var wemi=$(".norms_wm .active").attr('data-value');
    console.log(wemi);
    if(wemi==undefined){
    	wemi="0";
    }
    $("#wemoneyNum").val(wemi);
    
    var wemoney=0;
    wemoney = parseFloat($("#wemoneyNum").val())/10;
    dj=$(".norms .active").attr('data-value');
    if(dj==undefined){
    	dj="0";
    }
    var itemPirce = parseFloat(dj);
    var num = parseInt($("#affirm_join p.total span").attr("total"));
    var total = itemPirce + num-wemoney+parseFloat(norms_yj);
    $("#affirm_join .total span").text(total.toFixed(2));
    $("#weimi_value").val(total);
    $($(".qnxzb-qr-jg span").get(1)).text(total.toFixed(2));
    $($(".text-jg span").get(1)).text(total.toFixed(2));
    
})

$(".norms_yj_a").click(function() {
	if($(this).hasClass('active')){
    	$(this).removeClass("active");
    }else{
    	$(this).addClass("active").siblings().removeClass("active");
    }
    
    
    var norms_yj =$(".norms_yj .active").attr('data-value');
    if(norms_yj==undefined){
    	norms_yj="0";
    }

    var wemoney=0;
    wemoney = parseFloat($("#wemoneyNum").val())/10;
    dj=$(".norms .active").attr('data-value');
    if(dj==undefined){
    	dj="0";
    }
    var itemPirce = parseFloat(dj);
    var num = parseInt($("#affirm_join p.total span").attr("total"));
    var total = itemPirce + num-wemoney+parseFloat(norms_yj);
    $("#affirm_join .total span").text(total.toFixed(2));
    $("#weimi_value").val(total);
    $($(".qnxzb-qr-jg span").get(1)).text(total.toFixed(2));
    $($(".text-jg span").get(1)).text(total.toFixed(2));
    
})
$(".norms_qc_a").click(function() {
    $(this).addClass("active").siblings().removeClass("active");
})

function checkparam(){
	if($("#name").val()=="" || $("#phone").val()==""){
		webToast("请填写联系人和联系电话","bottom",1800);
		layer.closeAll('page');
		 return false;
	}
	return true;
}
</script>
</body>
</html>
