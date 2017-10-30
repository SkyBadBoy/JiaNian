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
     <title>微新闻社童子营-体验原汁原味的美式教育</title>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=8.1">
 
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
        .mui-input-row label{padding: 10px 0px;}
        .mui-table-view-cell{padding:11px 15px 11px 15px;}
        .mui-table-view-cell > a:not(.mui-btn){color: #e98751}
        .mui-content > .mui-card:first-child{margin-top: 0px;}
        .norms{margin-bottom: 5px;}
        .affirm_down{margin-bottom:10px;}
        /*.mui-navigate-right:after, .mui-push-right:after{right: 30px;}*/
        /*.mui-table-view-cell.mui-active{background: #fff}*/
        .mui-content{background: #fff;}
        .mui-btn {
            font-size: 16px;
            padding: 8px;
            margin: 3px;
            /*border: 0;*/
        }
        input[type="button"], input[type="submit"], input[type="reset"] {
            -webkit-appearance: none;
        }
        textarea { -webkit-appearance: none;}
        select {
            /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
            border: solid 1px rgba(0, 0, 0, 0.3);
            /*很关键：将默认的select选择框样式清除*/
            appearance:none;
            -moz-appearance:none;
            -webkit-appearance:none;
            /*将背景改为绿色*/
            background:#fff;
            /*留出一点位置，避免被文字覆盖*/
            padding-right: 14px;
        }
        .norms_a.active{
            background: #3ab6d8;
            border: 1px solid #3ab6d8;;
        }
        .norms_wm_a.active{
            background: #3ab6d8;
            border: 1px solid #3ab6d8;;
        }

        ::-webkit-input-placeholder { /* WebKit browsers */
            color:    #3ab6d8;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    #3ab6d8;
            opacity:  1;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #3ab6d8;
            opacity:  1;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color:    #3ab6d8;
        }
        .main table td .srk1{ color:    #3ab6d8;}
    </style>
</head>
<body>
<div id="preloader">
    <div id="status">
        <p class="center-text">加载中…<em>请稍等哦!</em></p>
    </div>
</div>

<div id="main " class="bk_fff">
    <div class="main bk_fff color_000" style="margin-bottom: 50px;">
        <h2 class="main_h2 bk_000">微新闻社童子营-体验原汁原味的美式教育</h2>
        <p class="act_title ">纯“美”生活体验</p>
        <p class="act_title ">遇见·美国</p>
        <p class="t_c">每一个孩子都有独特的天赋被发觉</p>
        <p class="t_c">每一个孩子都有无限的潜能被激发</p>
        <p class="t_c">每一个孩子都有各种的机会去尝试</p>
        <p class="t_c">赶快报名子衿自选课程童子营</p>
        <p class="t_c">给您孩子一个提升自我的机会</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy1.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="act_title ">7天后您的孩子将成为</p>
        <p class="t_c">一个天赋独特的孩子</p>
        <p class="t_c">一个潜力爆发的孩子</p>
        <p class="t_c">一个懂得抓住机会的孩子</p>
        <p class="t_c">一个拥有正确价值观、人生观的孩子</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy2.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy3.png"  data-preview-src="" data-preview-group="1"></p>
        <!--选择童子营的理由一-->
        <p class="tzy_title ">选择童子营的理由一</p>
        <p class="tzy_title1 ">全英语环境</p>
        <!--<p class="main_title "></p>-->
        <p class="tzy_content">让孩子说出来，爱上英语</p>
        <p class="main_p_detail">1:7的师资配比，外教7*24小时和孩子生活在一起，孩子在课上和生活上的任何需求都需要和外教进行交流。</p>
        <p class="tzy_content">让孩子更优秀，懂得更多</p>
        <p class="main_p_detail">省儿童礼仪协会的专业老师，写作、新闻写作类教育工作多年的名师，省摄影家协会的专业老师，专业的主持人。</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy4.png"  data-preview-src="" data-preview-group="1"></p>
        <!--选择童子营的理由二一-->
        <p class="tzy_title ">选择童子营的理由二一</p>
        <p class="tzy_title1 ">精英教育</p>
        <p class="tzy_content">百年美利坚，百年童子营</p>
        <p class="main_p_detail">童子营起源于1908年，距今已有100多年的历史，世界在变经典一直在延续。</p>
        <p class="tzy_content">明星校友，比尔盖茨是你的学长</p>
        <p class="main_p_detail">在童子营100多年的历史中，出现过美国多位名人，包括总统、世界首富等等。</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy5.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy6.png"  data-preview-src="" data-preview-group="1"></p>
        <!--选择童子营的理由三-->
        <p class="tzy_title ">选择童子营的理由三</p>
        <p class="tzy_title1 ">国外学校认可资质</p>
        <p class="tzy_content">证书即分数，留学有加分。</p>
        <p class="main_p_detail">学员毕业证书颁发机构：</p>
        <p class="main_pt_detail">①美国银杏教育基金会（非盈利组织）。</p>
        <p class="main_pt_detail">②美国国家营地协会（ACA）。</p>
        <p class="main_pt_detail">③MSA营地教育协会。</p>
        <p class="main_pt_detail">④美国赛维思教育集团。</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy7.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy8.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="main_p_detail">童子营拥有完善的徽章体系，每完成一门课程均会获得相应的荣誉徽章</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy9.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy10.png"  data-preview-src="" data-preview-group="1"></p>
        <!--营地介绍-->
        <p class="act_title ">营地介绍</p>
        <p class="main_title">台州·蛇蟠岛</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy16.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="main_p_detail">1、这里气候宜人，远离雾霾；</p>
        <p class="main_p_detail">2、这里是4A级的景区；</p>
        <p class="main_p_detail">3、这里风景优美，训练设施完善；</p>
        <p class="main_p_detail">4、这里由美国营地专家倾情打造；</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy12.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy13.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy14.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy15.png"  data-preview-src="" data-preview-group="1"></p>
        <!--住宿条件-->
        <p class="act_title ">住宿条件</p>
        <!--<p class="main_title">蛇蟠岛酒店建于岛上两个洞窟景区（野人洞、海盗村）中间，外观采用江南古建筑风格，与周边的古民居和谐融合，交相辉映，成为岛上一道亮丽风景。且设施齐备、方便温馨、安全卫生。环境优美，空气清新，安静宜人。</p>-->
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy16.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="main_p_detail">蛇蟠岛酒店建于岛上两个洞窟景区（野人洞、海盗村）中间，外观采用江南古建筑风格，与周边的古民居和谐融合，交相辉映，成为岛上一道亮丽风景。且设施齐备、方便温馨、安全卫生。环境优美，空气清新，安静宜人。</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy17.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/tzy/tzy18.png"  data-preview-src="" data-preview-group="1"></p>
        <!--管理优势-->
        <p class="act_title ">管理优势</p>
        <p class="main_p_detail">(1)对不同学员量身打造安全专业健康的营地生活体验和课程安排，四种选修课（写作、礼仪、主持、摄影）</p>
        <p class="main_p_detail">(2)互动交流平台，通过微信以及小记者直播采访的形式，与家长进行实时交流</p>
        <p class="main_p_detail">(3)配备齐全的医疗团队，经验丰富的医生以及相应的应急车辆</p>
        <!--开营时间-->
        <p class="act_title ">开营时间</p>
        <p class="main_title">日程安排</p>
        <p class="main_pt_detail">1）2017年7月16日-7月22日（4980元/人）</p>
        <p class="main_pt_detail">2）2017年7月23日-7月29日（4980元/人）</p>
        <p class="main_pt_detail">3）2017年7月30日-8月5日（4980元/人）</p>
        <p class="main_pt_detail">4）2017年8月6日-8月12日（4980元/人）</p>
        <p class="main_pt_detail">所有营期报名截止日均为每期开营前两周</p>
        <p class="tzy_content">费用说明：</p>
        <p class="main_pt_detail">1、 费用包含：来回蛇蟠岛住宿费用、入营五件套（制服衣服、制服裤子、制服领巾、训练T恤、野营袜子）营养配餐餐费、4A级景区蛇蟠岛门票、挑战项目费用及基地文化课程等。</p>
        <p class="main_pt_detail">2、 费用不含：来回蛇蟠岛交通费、个人物品及个人消费。</p>
        <p class="tzy_content">退费说明：</p>
        <p class="main_pt_detail">1、报名后如因个人原因无法参加（如生病，时间冲突等）提前3天退出可全额退款。</p>
        <p class="main_pt_detail">2、3天内退出退费50%。</p>
        <p class="main_pt_detail">3、课程当天退出，不予退费。</p>
        <p class="main_pt_detail">报名缴费成功即视为同意遵循以上条款</p>
        <p class="main_pt_detail">注：童子营蛇潘岛精英训练营，一期接待人数上限120人，共分为8个班每个班15名学生。</p>
        <!--招生范围-->
        <p class="act_title ">招生范围</p>
        <p class="main_p_detail">一、8-18周岁的儿童及青少年；</p>
        <p class="main_p_detail">二、身体健康，如有对食物和物品的过敏反应，请在报名时提出。餐食上，我们尊重营员的宗教信仰。请家长提前告知，以便我们做好准备；</p>
        <p class="main_p_detail">三、有一定独立生活能力，提前准备好必备的生活用品：如水壶(不允许带玻璃的)、小背包、洗澡用品等；</p>
        <p class="main_p_detail">四、入营前营员的监护人必须签署监护人授权书，以便我们在营地生活中可以更好的照顾到每一位营员的健康和及时应对紧急事件。</p>
         <p class="main_p2">详询：</p>
        <p class="main_p2">杨老师  <a href="tel:15355930038">15355930038</a></p>
        <!--五、关注微信公众号-->
        <p class="act_title ">关注微信公众号</p>
        <p class="main_p2">微信号：xiaobofabu </p>
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
            <img src="<%=basePath%>images/school/tzy/tzy2.png" alt="">
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
        <div class="qnxzb-p-detail" id="Surplus">总额：<span>4980</span></div>
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
