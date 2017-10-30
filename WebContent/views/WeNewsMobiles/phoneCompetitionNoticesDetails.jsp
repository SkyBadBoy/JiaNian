<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
%>
<html>
<head>
    <title>“见字如面”书信创作大赛</title>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/school/activity.css?v=4">
    <style type="text/css">
.school{
padding-left: 85px;
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
    <img src="<%=basePath %>images/school/banner4.jpg?v=1.0.0" id="imgId"/>
</div>
<div id="main " class="bg_fdeacc" style="margin-bottom: 50px;">
    <div class="main bg_fdfaf3 color_b78974">
        <h2 class="main_h2 bg_fd8396">“见字如面”书信创作大赛</h2>
        <p class="act_title ">活动详情：</p>
        <!-- 一、活动意义-->
        <p class="main_title">一、活动意义</p>
        <p class="main_p">信，人言也。中国是历史文化悠久的礼仪之邦，历来人们的社会交往和思想感情交流，通过“信”这样一种礼仪形式来进行，传递的是中华民族千年来的精神情怀。</p>
        <p class="main_p">“烽火连三月，家书抵万金”、“马上相逢无纸笔,凭君传语报平安”，战争年代书信传递的是割舍不断的牵挂；“欲寄彩笺兼尺素,山长水阔知何处”、“云中谁寄锦书来，雁字回时，月满西楼”，高楼闺阁中书信寄托的是望眼欲穿的相思；《报任安书》、《与山巨源绝交书》寄托着对自我的人生理想和坚定信念；《诫子书》、《傅雷家书》饱含着上一辈对子女的殷殷关切……</p>
        <p class="main_p">你是否读到过令自己记忆犹新的书信？你是否也曾想过给谁写一封信？让我们一起用“写信”、“读信”这样一种简单质朴的仪式，去重拾中华千年的传统，触碰身边可感的人物情状和社会风物，重新领会生命情怀与生活智慧。</p>
        <!-- 二、主办单位-->
        <p class="main_title">二、主办单位</p>
        <p class="main_p_detail">杭州市教育局</p>
        <p class="main_p_detail">杭州青少年活动中心</p>
        <p class="main_p_detail">新浪网浙江教育</p>
        <!-- 三、承办单位-->
        <p class="main_title">三、承办单位</p>
        <p class="main_p">杭州少年文学院</p>
        <p class="main_p">杭州中学校园文学社团联盟</p>
        <!--四、 参赛对象与活动形式-->
        <p class="main_title">四、参赛对象与活动形式</p>
        <p class="main_p_detail">1、参赛对象：浙江省各区、县、市中学文学社团</p>
        <p class="main_p_detail">2、活动形式：线上写作及朗诵展示</p>
        <!--五、 时间安排-->
        <p class="main_title">五、时间安排</p>
        <p class="main_p_detail">1、大赛启动：2017年3月31日</p>
        <p class="main_p_detail">2、线上创作阶段：截止2017年4月20日</p>
        <p class="main_p_detail">3、线上朗诵阶段：2017年5月1日—5月20日</p>
        <p class="main_p_detail">4、大赛结果公布：2017年5月28日</p>
        <!--六、 活动安排-->
        <p class="main_title_care">六、活动安排</p>
        <p class="main_p_detail">1、大赛启动：主办、承办单位发布活动通知，搭建专题网页、微信上传通道、展示通道、投票通道，做好前期宣传工作。</p>
            <!-- 线上创作阶段-->
        <p class="main_p_detail">2、线上创作阶段：</p>
        <p class="main_pt_detail">①全省各中学文学社团动员学生，以社团为单位，择优挑选1到2篇优秀作品，上传到大赛专题投稿通道，由主办方统一将作品上传至大赛活动主页。</p>
        <p class="main_pt_detail">②征稿要求：以“写给XX的信”为主题，自选对象，自定主题，写一封信。作品内容须为原创，对象可以是真实的，也可以是虚拟的。或有真实动人的情感，或有丰富合理的想象，或有独到的思考见解，要求主题积极向上，文辞优美，个性突出，适合朗读。普通话版、方言版均可（方言请在对应文字旁标注读音及注释）。字数不超过2000字。</p>
        <p class="main_pt_detail">③作品以word文档保存（doc格式），并提供社团清晰图标、作者清晰照片各一张（jpg格式，用于网络投票的头像），详细邮寄地址及收件人、打包并以“区县市+学校+社团名+作者姓名”的方式命名，发送至邮箱
            <span class="email_jzrs">xzhbox@163.com</span>，(或麦客表单主页）截止时间4月20日。作品不退稿，举办方拥有所有作品的收藏、出版、宣传、展览等处置权以及网络发言权。</p>
        <p class="main_pt_detail">④作品上传完毕后，主办单位将邀请专业评委对线上创作阶段的作品进行评选，按照作品质量选取若干社团作品作为线上朗诵阶段朗诵素材，在5月1日前发布到大赛专题网页上。</p>
        <p class="main_pt_detail">⑤大赛设置网上交流区，每篇作品下方可进行点评交流互动。</p>
            <!-- 线上朗诵阶段-->
        <p class="main_p_detail">3、线上朗诵阶段：</p>
        <p class="main_pt_detail">①全省各中学文学社团从入选为朗诵素材的书信中挑选作品进行线上朗诵，并将朗诵音频上传到大赛专题投稿通道，由主办方统一将作品上传至大赛活动主页。</p>
        <p class="main_pt_detail">②朗诵要求：朗诵者可为作者本人，也可由该社团其他成员担任，单人或多人合作均可。可添加配乐、音效、特效等优化朗读效果。</p>
        <p class="main_pt_detail">③朗诵作品将在活动主页进行展示，5月20日至5月27日进行网上投票，同时邀请专家评审核定前15强社团，打分比例网络评分占30%，专业评审占70%，并由主办及承办单位盖章确认，颁发获奖证书。</p>
        <p class="main_p_detail">4、大赛结果公布：</p>
        <p class="main_pt_detail">大赛获奖结果将在大赛主页、微信公众号同步发布。获奖证书将以邮寄方式寄至各学校。</p>
        <!--七、 时间安排-->
        <p class="main_title">七、奖项设置</p>
        <p class="main_p_detail">1、集体奖：线上创作阶段和线上朗诵阶段综合表现第一名成为“最具实力文学社”；综合表现第二第三名为“最具竞争力文学社”；综合表现第四到第八名为“最具表现力文学社”；其他前十五强社团获得“优秀文学社团”奖。网络投票最高的社团为“最具人气文学社”。</p>
        <p class="main_p_detail">2、个人奖：</p>
        <p class="main_pt_detail">学生：线上创作阶段前八名优秀作品创作者获“首奖”称号。按照作品比例另设“最具文采奖”、“最具创意奖”、“最具思考力奖”、“最动情奖”“优胜奖”等称号若干。</p>
        <p class="main_pt_detail">老师：获得集体奖的社团指导老师获得最佳指导老师奖。</p>
        <!--&lt;!&ndash;五、大赛评分标准&ndash;&gt;-->
        <!--<p class="main_title">五、大赛评分标准</p>-->
        <!--<p class=""><img class="main_p_img" src="images/biaozhun.png"></p>-->
        <!--&lt;!&ndash;七、注意事项&ndash;&gt;-->
        <!--<p class="main_title_care">七、注意事项</p>-->
        <!--<p class="main_p2">1、请各中小学积极配合和支持此次大赛，做好教师安排、场地安排、选手组织等工作，切实做好学生安全保卫工作。</p>-->
        <!--<p class="main_p2">2、参加浙江省总决赛的学校及选手，组委会负责统一安排食宿，食宿及交通费用自理。</p>-->
        <!--<p class="main_p2">3、有关具体事宜可咨询组委会秘书处或到大赛<a href="http://zj.sina.com.cn/edu/wxws2016/" class="activity_a">官方网站</a>查询</p>-->
        <!--八、大赛组委会秘书处-->
        <p class="main_title">八、联系方式</p>
        <p class="main_p2">①联系地点：杭州市西湖区昭庆寺里街22号杭州青少年活动中心文学楼309办公室 </p>
        <p class="main_p2">②联系电话：<a href="tel:0571-85821068" class="activity_a">0571-85821068</a>（周三到周日）</p>
        <p class="main_p2">③联 系 人：康老师</p>
        <p class="main_p2">④微信公众号：文学部小微（微信号：sngwxb）</p>
        <p class="main_p2">⑤新浪微博：杭州少年文学院</p>
        <!--九、点击关注二维码-->
        <p class=""><img class="main_ewm_img" src="<%=basePath %>images/school/QRCode.png"></p>
        <p class="main_ewm_title">扫描上方二维码点击关注公众号!</p>
    </div>
    <footer style="background: #fff;padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
	</footer>
</div>

<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap"> <a href="javascript:window.scrollTo(0,0)" class="top" title="返回顶部"><span>返回顶部</span></a>
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
        $("#imgId").attr('src',"<%=basePath%>images/school/banner4.jpg");
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
