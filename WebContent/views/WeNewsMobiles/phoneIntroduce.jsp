<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
%>
<html>
<head>
    <title>新浪浙江微新闻社战略学校申请</title>
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
    <link rel="stylesheet" href="<%=basePath %>css/school/weui.min.css?v=1">
    <link rel="stylesheet" href="<%=basePath %>css/school/jquery-weui.min.css?v=1">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=3">
    <style type="text/css">
		 <style type="text/css">
        .mui-preview-image.mui-fullscreen {
            position: fixed;
            z-index: 20;
            background-color: #000;
        }
        .mui-preview-header,
        .mui-preview-footer {
            position: absolute;
            width: 100%;
            left: 0;
            z-index: 10;
        }
        .mui-preview-header {
            height: 44px;
            top: 0;
        }
        .mui-preview-footer {
            height: 50px;
            bottom: 0px;
        }
        .mui-preview-header .mui-preview-indicator {
            display: block;
            line-height: 25px;
            color: #fff;
            text-align: center;
            margin: 15px auto 4px;
            width: 70px;
            background-color: rgba(0, 0, 0, 0.4);
            border-radius: 12px;
            font-size: 16px;
        }
        .mui-preview-image {
            display: none;
            -webkit-animation-duration: 0.5s;
            animation-duration: 0.5s;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }
        .mui-preview-image.mui-preview-in {
            -webkit-animation-name: fadeIn;
            animation-name: fadeIn;
        }
        .mui-preview-image.mui-preview-out {
            background: none;
            -webkit-animation-name: fadeOut;
            animation-name: fadeOut;
        }
        .mui-preview-image.mui-preview-out .mui-preview-header,
        .mui-preview-image.mui-preview-out .mui-preview-footer {
            display: none;
        }
        .mui-zoom-scroller {
            position: absolute;
            display: -webkit-box;
            display: -webkit-flex;
            display: flex;
            -webkit-box-align: center;
            -webkit-align-items: center;
            align-items: center;
            -webkit-box-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            left: 0;
            right: 0;
            bottom: 0;
            top: 0;
            width: 100%;
            height: 100%;
            margin: 0;
            -webkit-backface-visibility: hidden;
        }
        .mui-zoom {
            -webkit-transform-style: preserve-3d;
            transform-style: preserve-3d;
        }
        .mui-slider .mui-slider-group .mui-slider-item img {
            width: auto;
            height: auto;
            max-width: 100%;
            max-height: 100%;
        }
        .mui-android-4-1 .mui-slider .mui-slider-group .mui-slider-item img {
            width: 100%;
        }
        .mui-android-4-1 .mui-slider.mui-preview-image .mui-slider-group .mui-slider-item {
            display: inline-table;
        }
        .mui-android-4-1 .mui-slider.mui-preview-image .mui-zoom-scroller img {
            display: table-cell;
            vertical-align: middle;
        }
        .mui-preview-loading {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            display: none;
        }
        .mui-preview-loading.mui-active {
            display: block;
        }
        .mui-preview-loading .mui-spinner-white {
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -25px;
            margin-top: -25px;
            height: 50px;
            width: 50px;
        }
        .mui-preview-image img.mui-transitioning {
            -webkit-transition: -webkit-transform 0.5s ease, opacity 0.5s ease;
            transition: transform 0.5s ease, opacity 0.5s ease;
        }
        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }
        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }
        @-webkit-keyframes fadeOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }
        @keyframes fadeOut {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }
        p img {
            max-width: 100%;
            height: auto;
        }
    </style>
    </style>
</head>
<body>
<div id="preloader">
    <div id="status">
        <p class="center-text">加载中…<em>请稍等哦!</em></p>
    </div>
</div>
<div class="protocol_title bg_f64e68">新浪浙江微新闻社介绍</div>
<div class="mui-content">
<div class="protocol_xy_txt ">
    <!--<h3>新浪微新闻社-致力于打造全媒体小主播</h3>-->
    <h5>一、新浪微新闻社-致力于打造全媒体小主播</h5>
    <p>1.1【审慎阅读】新浪浙江微新闻社是由新浪浙江教育频道发起成立、联合新浪微博平台，致力于打造全媒体小主播，提升孩子的写作能力、采访能力、新闻观察能力、口才与交流能力等综合素质的中小学生的新闻类校园社群。</p>
    <p>1.2【审慎阅读】新浪浙江微新闻社利用新浪网、新浪微博的强大影响力，配合微信的传播，运用文字和图片播报，以及视频播报，利用全新的互联网手段，营造以校园新闻播报、社会新闻播报，让新浪微新闻社小编辑全面体验新闻平台的运作，培养孩子全方面能力。</p>
    <!--<p><strong>1.2【我帮你托服务平台定义】我帮你托服务平台：是指由杭州互办网络科技有限公司开发并运行，包括不限于：官网、手机APP、官方客服电话、第三方链接、微信、短消息等在内的综合信息服务平台，可向您提供平台信息服务。我帮你托服务平台仅为您与具体的社会服务提供者之间提供居间服务，该所对应的社会服务由服务提供者提供。您与服务提供者之间的约定，我帮你托服务平台不是此类约定中的主体。如因服务提供者原因导致、遭受相关财产及人身损害的，我帮你托服务平台免于承担您与服务提供者之间具体社会服务协议履行所导致任何纠纷赔偿责任。</strong></p>-->
    <h5>二、新浪微新闻社成立流程</h5>
    <p>2.1【注册小编】学校学生30%的学生注册成为小编。</p>
    <p>2.2【招募社员】微新闻社进行面试选取品学兼优、热爱新闻报道的学生，组建校园微新闻社支社。</p>
    <p>2.3【授牌培训】集中社员、家长统一授牌，并邀请新浪技术、编辑、大咖对社员、家长进行培训。</p>
    <p>2.4【选举支社家委会】以学校推荐，社长1名，副社长1-2名，编委3-5名。</p>
    <p>2.5【注册发证】微新闻社小编网上注册，提交照片，新浪小编采访证发放。</p>
    <p>2.6【指导配合】学校指定一名指导老师和新浪编辑对接，做好该校微新闻社的管理和运营，同时做好校园微新闻的发布。</p>
    <p>2.7【建立支社交流群】以学校为单位建立支社微信群，由新浪指定编辑维护，定期发布活动安排。</p>
    <h5>三、新浪微新闻社社团优势</h5>
    <p>3.1 稿件形式：文字、图片、视频、直播都可以投稿。</p>
    <p>3.2 投稿途径：微博实名账号发布标题为#微新闻社#加内容并@校播-浙江微新闻社，发布成功。</p>
    <p>3.3 严格把关：所有稿件都将通过新浪审核择优在新浪浙江教育微新闻社专题网页发布，根据网络热度，获得积分兑换奖品。</p>
    <p>3.4 采访权：重大活动、名人、名家、公益活动等采访机会。</p>
    <!--<p><strong>【充值账户】您可以通过我帮你托服务平台在线办理或者拨打客服电话咨询。账户的用户名为您注册的手机号，申请人不能通过现金支付，应通过微信支付、支付宝支付等方式充值。您充值后，账户余额不能办理退款以及赠送部分的优惠券均不能转移、转赠。</strong></p>-->
    <h5>四、新浪小编职责</h5>
    <p>4.1 小编应注意言行举止、形象，外出采访时服从带队老师（家长）和被采访单位工作人员安排。</p>
    <p>4.2 发布的文字、图片、视频稿件必须积极向上，真实有趣，富有亮点。</p>
    <p>4.3 对人礼貌，小编之间应友好互助，共同进步。</p>
    <p>4.4 采访前做好准备，列出提纲，以便提问。</p>
    <p>4.5 外出活动时，注意恰当使用文明礼貌用语，认真做好采访记录。</p>
    <p>4.6 采访期间应尊重他人，遵守纪律，禁止追赶打闹，大声喧哗，不得乱摸乱动他人物品，做出有损小编形象的行为。</p>
    <p>4.7 积极参与微新闻社组织的各类活动。</p>
    <h5>五、积分成长 </h5>
    <p><strong>5.1【五星体系】：</strong><br></p>
    <p class="ti24">(1)新浪小编分为五个等级（一星、二星、三星、四星、五星）；<br></p>
    <p class="ti24">(2)小编积分达到一定的分数后进行升星；<br></p>
    <p class="ti24">(3)不同等级的小编享受的福利不同，等级越高的小编，享受的福利越多；<br></p>
    <p class="ti24">(4)小编的积分同时也可以在小编的“福利商城”里兑换礼品；<br></p>
    <p><strong>5.2【新浪小编成长体系】星级的提升需要依托积分制度，当积分满足到下一级的分值条件后，即可将星级提升到下一级：</strong><br></p>
    <p class="ti24">(1)五星：5001分到10000分；<br></p>
    <p class="ti24">(2)四星：2001分到5000分；<br></p>
    <p class="ti24">(3)三星：501分到2000分；<br></p>
    <p class="ti24">(4)二星：101分 到500分；<br></p>
    <p class="ti24">(4)一星：0分到100分 ；<br></p>
    <p><strong>5.3【积分提升途径】：</strong><br></p>
    <p class="ti24">(1)微新闻每周阅读量前十系统会赠送奖励积分；<br></p>
    <p class="ti24">(2)每日在“个人中心”里签到获得积分；<br></p>
    <p class="ti24">(3)微新闻投稿审核通过后积分+5；转发每天+2;每阅读1次+1；<br></p>
    <p class="ti24">(4)线下活动参与，每参与1次，积分+10；<br></p>
    <h5>六、微新闻社小编福利</h5>
    <p><strong>6.1【教育类培训】：</strong><br></p>
    <p class="ti24">(1)成为新浪微新闻社小编，可以定期接受由新浪微新闻社组织的各种教育类的培训，（课程安居实际情况进行安排）；<br></p>
    <p><strong>6.2【小编活动】：</strong><br></p>
    <p class="ti24">(1)成为新浪微新闻社的小编可以参加由微信微新闻社组织的活动；<br></p>
    <p><strong>6.3【积分及商家福利】：</strong><br></p>
    <p class="ti24">(1)积分可以在小编福利商城里面换购礼品（实物礼品、体验券等等）；<br></p>
    <p class="ti24">(2)新浪微新闻社正式小编可凭小编证（在个人中心里面）在微新闻社合作商家、教育机构、景区等等享受一定的折扣或福利；<br></p>
    <p><strong>6.4【学校配合事项】：</strong><br></p>
    <p class="ti24">(1)填写申请表格；<br></p>
    <p class="ti24">(2)推荐学校总人数30%的预备社员名单；<br></p>
    <p class="ti24">(3)确认授牌时间及第一次预备社员培训时间；<br></p>
    <p class="ti24">(4)4.指定一名指导老师负责对接；<br></p>
    <h5>七、授牌仪式流程</h5>
    <p>7.1【信息的发布】您声明并保证，您对您所发布的信息拥有相应、合法的权利。</p>
    <p><img src="<%=basePath %>images/school/shoupai.png?v=1" data-preview-src="" data-preview-group="1" class="main_p_img" ></p>
    <p><strong>黄色区域是学校配合事宜</strong><br></p>
    <h5>八、咨询电话：</h5>
    <p>18358116600  施老师</p>
</div>
</div>

<footer style="margin:10px 0;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<br/>
<br/>

<input class="joinin bg_f64e68" onclick="window.location='https://www.sojump.hk/m/12859103.aspx'" type="submit" name="toastBtn" id="toastBtn" value="立即申请"/>
<!--<div class="actGotop" style="display: none;" id="actGotop"><a href="activity.html" title="返回顶部"></a></div>-->
<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap">
        <a href="https://www.sojump.hk/jq/12859103.aspx" class="edit" title="立即申请"><span>立即申请</span></a>
        <a href="javascript:window.scrollTo(0,0)" class="top" title="返回顶部"><span>返回顶部</span></a>
    </div>
</div>
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
$(window).load(function () {
    $("#status").fadeOut();
    $("#preloader").delay(350).fadeOut("slow");
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        return;
    } else if (/(Android)/i.test(navigator.userAgent)) {
        return;
    } else {
        $("#imgId").attr('src',"<%=basePath%>img/errorpic.jpg");
        // PC端隐藏点击报名的按钮
        $("#toastBtn").css("display","none");
        // PC端隐藏来报名提示
        $("#guide").css("display","block");
        // PC端隐藏加载中提示
        $("#preloader").css("display","none");
    };
});
</script>
</body>
</html>
