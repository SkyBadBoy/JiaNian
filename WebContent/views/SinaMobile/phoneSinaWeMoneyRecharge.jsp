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
   <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <title>微米充值</title>
    <style type="text/css">
        @media screen and (max-width: 768px) {
            .orderfinished-help{margin: 55px auto 20px auto;background-color: #ffa767;height: 55px;line-height: 55px;}
            .orderfinished-help a{font-size:20px;}
            input[type=text]{font-size: 30px;}
        }
        @media screen and (max-width: 414px) {
            .orderfinished-help{margin: 55px auto 20px auto;background-color: #ffa767;height: 55px;line-height: 55px;}
            .orderfinished-help a{font-size:20px;}
            input[type=text]{font-size: 26px;}
        }
        @media screen and (max-width: 375px) {
            .orderfinished-help{margin: 50px auto 20px auto;background-color: #ffa767;height: 50px;line-height: 50px;}
            .orderfinished-help a{font-size: 18px;}
            input[type=text]{font-size: 24px;}
        }
        @media screen and (max-width: 320px) {
            .orderfinished-help{margin: 40px auto 20px auto;background-color: #ffa767;height: 45px;line-height: 45px;}
            .orderfinished-help a{font-size: 16px;}
            input[type=text]{font-size: 22px;}
        }
    </style>
</head>
<body class="whitebg">
<div class="starCenter">
   <div class="starLog">
       <div class="weixin_recharge">微信充值</div>
       <div class="banlance_recharge">
           充值金额
       </div>
       <!--<div class="money_recharge">100</div>-->
       <div class="weimi_detail">
           <input type="number" placeholder="请输入充值金额" id="weimi_value" class="weimi_detail_one" style="">
       </div>
       <div style="
    width: 100%;
    text-align: center;
    margin-top: 10px;
    color: #7D7D7D;
       ">
       1人民币兑换10微米
       </div>
       <div class="orderfinished-help" style="">
       
           <a href="javascript:void(0);" id="weimi_modify_submit" style="">去付款</a>
       </div>
   </div>

</div>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript">
//初始化微信支付
	var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.Recharge.getid(), String.valueOf(user.getID()), "0") %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
	initialWePayParam('#weimi_modify_submit',"#weimi_value",attach);


  function callbackFun(obj){
         webToast("充值成功，正在跳转到我的钱包","bottom",1800);
         setTimeout(function() {
              location.href = '<%=path%>/WeMoney/phoneWeMoney';
         }, 800);
	}

</script>
</body>
</html>

