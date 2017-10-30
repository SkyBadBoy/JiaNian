<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
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
%>
<html>
<head>
    <title>
    <c:if test="${name eq null }">
     	邀请好友拿大米，100微米疯狂送
    </c:if>
    <c:if test="${name != null }">
     	${name}邀您成为新浪小编
    </c:if>
    </title>
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
    <link href="<%=basePath %>css/bootstrap.min.css?v=111" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/wenewspc/activity.css?v=3">
    <style type="text/css">
.school{
padding-left: 85px;
}
    </style>
</head>
<body >
<div class="main">
    <div class="banner"><img src="<%=basePath %>images/activity/banner-cj.png"/></div>
    <div class="main-body">
        <div id="zhuanpan" style="width: 100%;
    height: 100%;">
            <div class="index_cont">
                <p class="index_cont_p">
                    <span class="activity_yq">注册成功即得10微米！ </span>
                    <div  id="qrcodeTable" class="img-responsive"  style="padding: 0;margin: 0 auto;width: 270px;height: 270px;padding-top:0.5px;padding: 10px;"></div>
                    
                    <span class="activity_sys" style="color:#862a2a">扫一扫二维码快速注册 </span>
                </p>
            </div>
        </div>
    </div>
    <div class="main-footer">
        <a href="#" class="footer-a" data-toggle="modal" data-target="#myModal2">小编优惠>></a>
        <a href="#" class="footer-a2"  data-toggle="modal" data-target="#myModal">邀请注册协议>></a>
    </div>
<footer style="padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;margin-bottom: 50px;">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
</div>

<!-- 注册提示信息 -->
<div id="myModal" class="modal fade s3" tabindex="-1" data-replace="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="zc-title"></div>
        <a href="#" class="close1"></a>
        <div class="modal-body" style="height: 250px; overflow: auto;">
            <p>
                &nbsp;&nbsp;&nbsp;点击邀请好友即默认为您已阅读并同意《邀请注册协议》。为保障您的合法权益，请在参与邀请注册活动之前，仔细阅读本协议。<br/>
                <span class="activity_ct">当您邀请好友注册时，默认您已知悉如下条款：</span>
                1、活动内容：<br/>
                &nbsp;&nbsp;&nbsp;(1)邀请好友注册，好友立得10微米。<br/>
                &nbsp;&nbsp;&nbsp;(2)当好友成功完成注册后，邀请者得100微米<br/>
                2、参与方式：<br/>
                &nbsp;&nbsp;&nbsp;用户通过分享路径邀请好友注册，受邀者在邀请链接中输入注册成为新浪微新闻社小编完成一次邀请。<br/>
                3、注意事项：<br/>
                &nbsp;&nbsp;&nbsp;(1)每位用户的邀请次数无上限，邀请者每成功邀请一次可获得100微米的邀请奖励。<br/>
                &nbsp;&nbsp;&nbsp;(2)受邀者须不是新浪浙江微新闻社的小记者，每位用户最多可获得一次受邀奖励。<br/>
                &nbsp;&nbsp;&nbsp;(3)拥有相同账户、手机号及设备的用户视为同一用户，该规则适用于邀请者与被邀请者。<br/>
                &nbsp;&nbsp;&nbsp;(4)用户可在“校播”微信平台［个人中心］中查看获得的奖励。<br/>
            </p>
        </div>

    </div>
</div>
<div id="myModal2" class="modal fade s3" tabindex="-1" data-replace="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="hd-title"></div>
        <a href="#" class="close2"></a>

        <div class="modal-body" style="height: 250px; overflow: auto;">
            <p>
                &nbsp;&nbsp;&nbsp;1、介绍：新浪浙江微新闻社是由新浪浙江教育频道发起成立、联合新浪微博平台，致力于打造全媒体小主播，提升孩子的写作能力、采访能力、新闻观察能力、口才与交流能力等综合素质的中小学生的新闻类校园社群。<br/>
                2、目标：打造全媒体小主播<br/>
                3、新浪小编可享受的各项福利：<br/>
                &nbsp;&nbsp;&nbsp;(1)金牌实训：可定期接受由新浪微新闻社组织的采编、摄影摄像、写作类的培训（课程按实际情况进行安排）。<br/>
                &nbsp;&nbsp;&nbsp;(2)趣味活动：可免费参加由微信微新闻社组织的各项活动（微新闻大赛、各类专访、校园剧大赛、户外亲子活动等）。<br/>
                &nbsp;&nbsp;&nbsp;(3)礼品换购：可使用积分在“小编福利”商城换购礼品（实物礼品、体验券等）。<br/>
                &nbsp;&nbsp;&nbsp;(4)折扣尊享：可凭小编证享受（在“个人中心”领取）微新闻社合作商家、教育机构、景区等的折扣或福利。
            </p>
        </div>

    </div>
    
</div>

<script src="<%=basePath%>js/jquery.min.js?v=111" type="text/javascript" ></script>
<script src="<%=basePath%>js/bootstrap.min.js?v=11"></script>
<script src="<%=basePath%>js/qrcode.min.js?v=3.3.7"></script>
<script src="<%=basePath%>js/plugin/alertPopShow/alertPopShow.js?v=3.3.7"></script>
<script src="<%=basePath%>js/wechat_index.js?v=3.3.8"></script>
<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
<script type="text/javascript">
var share_title="邀您成为新浪小编";
if(<%= user.getName()!=null && ! user.getName().isEmpty() %>){
	share_title = "<%= user.getName()%>邀您成为新浪小编";
}
var qrcode = new QRCode(document.getElementById("qrcodeTable"), {
	width : 250,
	height : 250
});
makeCode ();

function makeCode () {		
	qrcode.makeCode("<%=basePath%>Students/phoneRegister?uid="+ getQueryString("uid") +"&sina=<%=SmBaseUtil.Random()%>");
}
$(".close1").click(function () {
    $('#myModal').modal('hide');
});
$(".close2").click(function () {
    $('#myModal2').modal('hide');
});
</script>
</body>
</html>
