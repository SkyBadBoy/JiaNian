<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

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
    <c:if test="${phone!=null}">
   		验证手机号码
   	</c:if>
   	 <c:if test="${phone==null}">
   		手机号绑定
   	</c:if>
  </title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/plugin/alertPopShow/alertPopShow.css" rel="stylesheet">
  <link href="<%=basePath%>css/plugin/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/style.css" rel="stylesheet">
  <style>
      .verify_phoneright {
          height: 38px;
      }
      .phone_modify-help{background-color: #C6C6C6}
  </style>
</head>
<body class="backgroundcolorfff">
	<div class="" id="back_away_mt51" style="margin-top: 5px;">
    <form class="registerForm" method="post" id="registerForm">
        <div class="verify_phonenum">
            <div class="verify_phoneico"><span>手机号</span></div>
            <input type="number" name="phone_modify_iphone"
            <c:if test="${phone!=null}">
        		disabled="disabled"
        	</c:if>
             value="${phone}" id="phone_modify_iphone" class="verify_phoneright" placeholder="请输入手机号" oninput="if(value.length>11)value=value.slice(0,11)" />
        </div>
        <div class="verify_phonenum mt10">
            <div class="verify_phoneico"><span>验证码</span></div>
            <input type="number" name="phone_modify_vecode" class="verify_phoneright" id="phone_modify_vecode" placeholder="请输入6位验证码"/>
            <input class="phone_modify-help" type="button" id="phone_modify_sendcode" value="发送验证码" disabled="disabled"/>
        </div>
        <div class="orderfinished-help" style="">
            <a href="javascript:void(0);" id="phone_modify_submit" style="">下 一 步</a>
        </div>
        <c:if test="${type==3}">
       <div style="text-align: left;padding: 30px;">
      
        	<p><b style="font-size: 15px;">已有小编帐号？</b></p>
       		<p>1.如果您已经注册成为小编，请输入注册时绑定的手机号码！</p>
       		<p>2.我们将会帮您绑定当前微信为登录账户！</p>
       		<br/>
       		<p><strong style="font-size: 15px;">没有小编帐号？</strong></p>
       		<p>1.请您输入能够正常接收验证码的手机号码，获取6位验证码！</p>
       		<p>2.输入6位验证码，验证成功即可注册成为新浪小编！</p>
       		<br/>
       		<p>详细注册教程请<a href="http://mp.weixin.qq.com/s/TN93ApZcbcFU2JVk_dPcow" >点击这里</a>！</p>
       </div>
       </c:if>
    </form>
</div>
<!--弹出层-->
<article id="verify_tip">
    <div class="verify_pack verify_pack_yl">
        <h1 class="tc">温馨提示</h1>

        <p class="tc"></p>

        <div><a href="#">确定</a></div>
    </div>
</article>
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
	var ttype="${ttype}";
	if(ttype=="" || ttype==null || ttype==undefined){
		ttype=3;
	}
    var phone_modify_times = 30,
            phone_modify_timer = null;

    $("#phone_modify_sendcode").click(function () {
    	var str_iphone = $("#phone_modify_iphone").val();
    	 if (str_iphone.length != 11 || !checkPhone(str_iphone)) {
            webToast("请输入正确的手机号！", "bottom", 2000);
    	}  
    	if(phone_modify_sendcode.value != "发送验证码"){
    		return false;
    	}
    	 $.getJSON(ContentPath+"/Users/getAuthCode", {
    			Phone : str_iphone,
    			type : ttype
    		}, function(json) {
    			if(json.Status){
    				webToast("验证码发送成功！", "bottom", 2000);
    			}else{
    				webToast(json.Message, "bottom", 2000);
    			}
    			
    	});
        phone_modify_timer = setInterval(phone_modify_vercode, 1000);// 计时开始
        
    });
    function phone_modify_vercode() {
        phone_modify_sendcode.value = phone_modify_times + "秒后重试";
        phone_modify_sendcode.setAttribute('disabled', 'disabled');
        phone_modify_sendcode.style.background = '#ddd';
        phone_modify_times--;
        if (phone_modify_times <= 0) {
            phone_modify_sendcode.value = "发送验证码";
            phone_modify_sendcode.style.background = '#68ccb3';
            phone_modify_sendcode.removeAttribute('disabled');
            phone_modify_times = 30;
            clearInterval(phone_modify_timer);
        }
    }
    var checkPhone = function (a) {
        var patrn = /^((?:13|15|18|14|17)\d{9}|0(?:10|2\d|[3-9]\d{2})[1-9]\d{6,7})$/;
        if (!patrn.exec(a)) return false;
        return true;
    };
    var checkVercode = function (a) {
        
        if (a=="") return false;
        return true;
    };
    $(function () {
        var u = navigator.userAgent;
        if (!(!!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/))) {//安卓设备
            $("#back_away").removeClass("hidden");
            $("#back_away_mt51").css("margin-top", "51px");
        }

        $("#verify_tip .verify_pack a").on("click", function () {
            $("#verify_tip").fadeOut();
            $("#verify_tip .verify_pack p").html("");
            return false;
        });
        function alerths(str) {
            $("#verify_tip").fadeIn();
            $("#verify_tip .verify_pack p").html(str);
            return false;
        }

        $("#phone_modify_submit").on("click", function () {
            
            var str_vercode = $("#phone_modify_vecode").val();
            var str_iphone = $("#phone_modify_iphone").val();
            if (checkVercode(str_vercode)) {
                $.getJSON(ContentPath+"/Students/phoneUpdatePhoneResult", {
        			Phone : str_iphone,
        			Code : str_vercode,
        			auth : '${phone}',
        			type : '${type}'
	        		}, function(json) {
	        			if(json.Status>=1){
	        				 webToast("手机号绑定成功！", "bottom", 2000);
	        				 setTimeout(function () {
	        					 window.location.href="<%=path%>/Students/phoneSinaUserInfoEdit?pid=<%=user.getID() %>&AreaId=<%=user.getAreaID() %>&sina=<%=SmBaseUtil.Random() %>";
	        	              }, 2000);
	        				
	        			}else if(json.Status==-1){
	        				window.location.href="<%=basePath%>Region/phoneBindWeChatUser?openid=${openid}&uid=${uid}&sina=<%=SmBaseUtil.Random() %>";
	        			}else if(json.Status==-2){//跳转注册
	        				window.location.href="<%=basePath%>Students/phoneRegister?sina=<%=SmBaseUtil.Random() %>";
	        			}else{
	        				if(json.Message!=null && json.Message!=undefined){
	        					webToast(json.Message, "bottom", 2000);
	        				}else{
	        					webToast("验证码错误！", "bottom", 2000);
	        				}
	        				
	        			}
	        			
	        	});
            } else {
                   webToast("请输入验证码！", "bottom", 2000);
            }
           
            return false;
        })
		isDisabled();
        $('#phone_modify_iphone').bind('input porpertychange', function() {
        	isDisabled();
        });
        
        function isDisabled(){
        	if(phone_modify_sendcode.value != "发送验证码"){
        		return false;
        	}
            var str = $('#phone_modify_iphone').val();
            if(str.length == 11) {
                $('#phone_modify_sendcode').removeAttr('disabled');
                $('#phone_modify_sendcode').css({
                    background: '#68ccb3'
                });
            } else {
                $('#phone_modify_sendcode').attr('disabled', 'disabled');
                $('#phone_modify_sendcode').css({
                    background: '#C6C6C6'
                });
            }
        }

    })
</script>
</body>
</html>
