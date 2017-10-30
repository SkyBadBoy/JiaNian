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
	<link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css" rel="stylesheet">
   <style>
   		.pay_ercode_div{height: 37px;line-height: 37px;margin: 3%;position: relative;background-color: #fff;border-radius: 5px}
        .pay_ercode_je{position: absolute;left: 2%;top: 0;font-size: 15px;width: 10%;text-align: center}
        .pay_ercode_input{border: 0;width: 70%;height: 35px;line-height: 35px;text-align: right;position: absolute;right: 16%}
        .pay_ercode_y{position: absolute;right: 0;top: 0;font-size: 15px;width: 16%;text-align: center}
        .pay_ercode_ta{font-size:15px;border: 0;width: 93%;text-indent:10px;margin:0 3% 3% 3%;background-color: #fff;border-radius: 5px}
        .mui-content{background: none}
        .mui-btn-block {
            font-size: 18px;
            display: block;
            width: 100%;
            margin-bottom: 10px;
            padding:10px 0;
            border-radius: 5px;;
        }
          #loading {
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/wsload.gif) no-repeat center center #FFF;
            background-size: 10%;
        }
   </style>
    <title>确认付款</title>
</head>
<body class="whitebg">
<header class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <h1 class="mui-title">确认付款</h1>
</header>
<div class="mui-content">
    <div class="pay_ercode_div">
        <span class="pay_ercode_je">金额</span>
        <input type="text" placeholder="" class="pay_ercode_input"  onkeyup="value=value.replace(/[^\d.]/g,'')"
        <c:if test="${money!=null and money ne '' }">
	         readonly="readonly" 
	         value="${money}"
        </c:if>
         id="pay_ercode_input">
        <span class="pay_ercode_y">微米</span>
    </div>
    <textarea rows="3" cols="20" class="pay_ercode_ta" readonly="readonly"  
     <c:if test="${Student!=null}">
         placeholder="确认付款后将会微米付款给${Student.name}"
       </c:if>
        <c:if test="${Student eq null}">
         placeholder="确认付款后将会直接扣除您的微米数量"
       </c:if>
   ></textarea>
    <div style="height: 50px;line-height: 50px;width: 100%;text-align: center;font-size: 20px;color: #000">您当前有  ${WeMoney} 微米</div>
    <div class="mui-content-padded">
        <button type="button" class="mui-btn mui-btn-danger mui-btn-block" id="pay_ercode">确认付款</button>
    </div>
</div>
<footer style="margin:10px 0;color: #777777; font-size: 13px;"><div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div></footer>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<script src="<%=basePath %>js/jquery1.8.3.min.js"></script>
<script src="<%=basePath %>js/school/mui.min.js"></script>
<script src="<%=basePath%>js/plugin/alertPopShow/alertPopShow.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$("#pay_ercode_input").focus();
    $("#pay_ercode").click(function () {
        if ($("#pay_ercode_input").val() == "") {
            mui.toast('请输入微米数量');
            $("#pay_ercode_input").focus();
        } else {
        	$("#loading").css("display","block");
        	$.ajax({
        		url : "<%=basePath %>WeMoney/phonePayWeMoneyResult?uid=${Student.PKID}&&pirce="+ $("#pay_ercode_input").val() +"&sina=<%=SmBaseUtil.Random()%>",
        		type:"get",
        		success : function(json) {
        			if(json.Status){
        				window.location='<%=basePath%>include/paysuccess.html?sina=<%=SmBaseUtil.Random() %>'
        				 $("#loading").css("display","none");
        			}else{
        				webToast(json.Message,"bottom", 2000);
        				$("#loading").css("display","none");
        			}
        		}
        	});
        }
    });

});
</script>
<div id="loading"></div>
</body>
</html>

