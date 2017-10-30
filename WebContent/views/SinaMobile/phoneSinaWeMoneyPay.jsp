<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.tenpay.util.WXUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
%>
<html>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no"/>
    <link href="<%=basePath%>css/school/mui.min.css?v=1.1.1" rel="stylesheet">
   <style>
    	body{background-color: #fad87a;}
        #qrcode img{width: 50%;margin:0 auto;}
        .pay_div{background-color: #fff;margin: 5%;height: 350px;width: 90%;border-radius: 5px;}
        .pay_sk{height: 30px;line-height: 30px;font-size: 15px;color: #fad87a;width: 100%;text-indent: 5%;background-color: #fafafa;border-top-left-radius: 5px;border-top-right-radius: 5px;}
        .pay_sm{height: 60px;line-height:60px;font-size: 15px;color: #000;width: 100%; text-align: center;}
        .qrcode{width: 100%; text-align: center;margin-bottom: 20px;}
        .pay_je{    float: left;
    width: 100%;
    text-align: center;
    font-size: 25px;}
   </style>
    <title>微米充值</title>
</head>
<body class="whitebg">
<header class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <h1 class="mui-title">${Student}二维码收款码</h1>
</header>
<div class="mui-content" style="background-color: #fad87a;">
    <div class="pay_div">
        <div class="pay_sk">二维码收款</div>
        <div class="pay_sm">扫一扫二维码支付微米</div>
        <div class="qrcode" id="qrcode"></div>
        <c:if test="${money!=null and money ne '' }">
        	<a class="pay_je" style="color:#FF8300">支付  ${money} 微米</a>
        </c:if>
         <c:if test="${money==null or money eq '' }">
        	<a class="pay_je" style="color:#FF8300">扫一扫给我付款</a>
        </c:if>
        <!--<a class="pay_bc">保存收款码</a>-->
    </div>
</div>
<footer style="margin:10px 0;color: #777777; font-size: 13px;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<script src="<%=basePath %>js/jquery1.8.3.min.js"></script>
<script src="<%=basePath %>js/school/mui.min.js"></script>
<script src="<%=basePath %>js/qrcode.min.js"></script>
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode"), {
    width: 250,//设置宽高
    height: 250
});
qrcode.makeCode("<%=basePath%>WeMoney/phoneSinaWeMoneyConfirm?money=${money}&&uid=${uid}");
</script>
</body>
</html>

