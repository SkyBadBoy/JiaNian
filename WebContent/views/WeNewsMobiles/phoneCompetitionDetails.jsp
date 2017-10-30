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
    <title>校园剧大赛报名申请</title>
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
    <img src="<%=basePath %>images/school/banner.jpg?v=1.0.0" id="imgId"/>
</div>
<div id="main bg_fef4ad">
    <div class="main bg_fffeee color_7b6a01">
        <h2 class="main_h2 bg_f64e68">“我是剧星”浙江中小学校园剧大赛章程</h2>
        <p class="act_title">活动详情</p>
        <!--大赛总则-->
        <p class="main_title">大赛总则</p>
        <p class="main_p"> 为了落实新课改的精神，培养学生的活动能力、组织能力、创造能力、口语表达能力和舞台表演能力；增强团结、友爱、合作精神，提高对课本阅读的兴趣，提高学生的综合素质，进一步推进学校素质教育，丰富校园文化生活，给全省学生提供一个展现自我，展示才艺的平台，新浪浙江微新闻社联合教育部门举办“我是剧星”浙江中小学校园剧大赛。</p>
        <!-- 一、大赛理念-->
        <p class="main_title">一、大赛理念</p>
        <p class="main_p">靠近我，靠近全世界</p>
        <!-- 二、大赛宗旨-->
        <p class="main_title">二、大赛宗旨</p>
        <p class="main_p_detail">丰富校园文化，关注终身发展</p>
        <p class="main_p_detail">展示语言魅力，激发多元潜能</p>
        <p class="main_p_detail">放飞文学梦想，点亮世界舞台</p>
        <p class="main_p_detail">践行环保理念，助力公益精神</p>
        <!-- 三、大赛主题-->
        <p class="main_title">三、大赛主题</p>
        <p class="main_p">助力学子成长，构筑你我梦想</p>
        <!--四、 组织机构-->
        <p class="main_title">四、组织机构</p>
        <p class="main_p_detail">主办单位：<span>新浪浙江</span><br><span class="school">新浪微博</span><br><span  class="school">杭州青少年活动中心 </span><br><span  class="school">小学生时代杂志社</span></p>
        <p class="main_p_detail">承办单位：新浪浙江微新闻社<br><span class="school">杭州校播科技有限公司</span></p>
        <p class="main_p_detail">技术支持：星成长·阳光少年学院</p>
        <p class="main_p_detail">支持单位：全省各中小学校</p>
        <!--赛制说明-->
        <div class="maindiv"></div>
        <p class="main_title">赛制说明</p>
        <!-- 一、大赛分组-->
        <p class="main_title">一、大赛分组</p>
        <p class="main_p">大赛设置两个组别，分别为小学组和初中组。比赛形式为团体赛，以学校为单位组队参赛。每支参赛队不能超过10人，每队配备领队老师一人。参赛形式不限。</p>
        <!-- 二、大赛进程-->
        <p class="main_title">二、大赛进程</p>
        <p class="main_p2">1、报名：2017年3月15日至2017年4月15日；所有参加比赛的团队均需报名（登录<a href="http://zj.sina.com.cn/edu/wxws2016/" class="activity_a">新浪浙江教育官网</a>
            进入大赛专用页面进行报名，或在本页面进行报名）,
            <span>报名团队以学校为单位进行报名，报名应填写学校信息及上传盖有学校公章的确认函（<a href="http://wenews.oss-cn-hangzhou.aliyuncs.com/Documents/%E7%A1%AE%E8%AE%A4%E5%87%BD.docx" class="activity_a">点击下载</a>）。</span>
        </p>
        <p class="main_p2">2、校园选拔赛：2017年4月15日至2017年5月15日；学校内组织选拔赛，每个学校选出一个最优团队，并将该团队校园剧表演的录像于2017年5月16日前发送至以下邮箱：<a href="mailto:284635664@qq.com" class="activity_a">284635664@qq.com</a></p>
        <p class="main_p2"> 3、市级优秀作品展示投票：2017年5月22日至2017年6月22日；校级优秀在新浪浙江微新闻社专题页面发布，每个作品设独立二维码，通过个人微信账号可以转发作品到微信投票，
            <span>6月30日综合作品网络投票数量评选出前30名获奖作品，微新闻社将对30支代表队伍的作品及表演进行指导培训。</span></p>
        <p class="main_p2"> 4、全省总决赛：2017年月8中旬，30只代表队伍现场表演作品，评委现场打分，评出获胜队伍（具体时间地点请关注新浪浙江教育官网及组委会通知）。</p>
        <!-- 三、大赛内容-->
        <p class="main_title">三、大赛内容</p>
        <p class="main_p">内容积极向上，体现社会主义核心价值观。形式不限。</p>
        <p class="main_p">表演时长限定5－10分钟，视频名称格式：浙江省××市××学校××队，上传作品附带少于500字的创意说明。</p>
        <p class="main_p">如：浙江省杭州市学军小学前进队+（创意说明）</p>
        <!--四、 参赛费用-->
        <p class="main_title">四、大赛评分标准</p>
        <p class="main_p_detail">比赛免收报名费，其他各阶段比赛费用自理。</p>
        <!--五、大赛评分标准-->
        <p class="main_title">五、大赛评分标准</p>
        <p class=""><img class="main_p_img" src="<%=basePath %>images/school/biaozhun.png"></p>
        <!--六、奖项设置-->
        <p class="main_title">六、奖项设置</p>
        <p class="main_p2">1、进入市级优秀作品展示环节的参赛团队，每支团队获得证书及300元的奖品。</p>
        <p class="main_p2">2、浙江省总决赛每个组别各设置团队冠军、亚军、季军。获奖团队分别获得价值2000元、1500元、1000元的奖品并颁发证书。</p>
        <p class="main_p2">3、浙江省总决赛设置团队单项奖：每组别设置最佳表现力奖，最佳口语表达奖各1名及其他各类奖项，获奖团队可获得价值1000元的奖品并颁发证书。</p>
        <p class="main_p2">4、浙江省总决赛设置优秀指导教师奖，冠军、亚军、季军团队指导教师可获赠价值1200元、1000元和800元的奖品。</p>
        <p class="main_p2">5、获得浙江省总决赛冠军的参赛团队成员，将成为新浪浙江微新闻社、小学生时代代言人，有机会参与影视拍摄和代言。</p>
        <!--七、注意事项-->
        <p class="main_title_care">七、注意事项</p>
        <p class="main_p2">1、请各中小学积极配合和支持此次大赛，做好教师安排、场地安排、选手组织等工作，切实做好学生安全保卫工作。</p>
        <p class="main_p2">2、参加浙江省总决赛的学校及选手，组委会负责统一安排食宿，食宿及交通费用自理。</p>
        <p class="main_p2">3、有关具体事宜可咨询组委会秘书处或到大赛<a href="http://zj.sina.com.cn/edu/wxws2016/" class="activity_a">官方网站</a>查询</p>
        <!--八、大赛组委会秘书处-->
        <p class="main_title">八、大赛组委会秘书处</p>
        <p class="main_p2">联系人：于老师 </p>
        <p class="main_p2">联系电话：<a href="tel:0571-85096701" class="activity_a">0571-85096701</a></p>
        <p class="main_p2">时间：9:00-17:00（工作日）</p>
        <p class="main_p2">电子邮箱：<a href="mailto:284635664@qq.com" class="activity_a">284635664@qq.com </a></p>
  		<p class=""><img class="main_ewm_img" src="<%=basePath %>images/school/QRCode.png"></p>
        <p class="main_ewm_title">长按二维码，了解更多详情！</p>
    </div>
    <footer style="background: #fff;padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;padding-bottom: 50px">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
	</footer>
</div>


<a class="joinin bg_ea5048" href="<%=basePath %>Competition/phoneCompetition" id="toastBtn" ><span>点击报名</span></a>
<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap">
        <a href="<%=basePath %>Competition/phoneCompetition" class="edit" title="点击报名"><span>快速报名</span></a>
        <a href="http://wenews.oss-cn-hangzhou.aliyuncs.com/Documents/%E7%A1%AE%E8%AE%A4%E5%87%BD.docx" style="font-size: 14px" class="edit" title="点击报名"><span>下载确认函</span></a>
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
        $("#imgId").attr('src',"<%=basePath%>images/school/banner2.jpg");
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
