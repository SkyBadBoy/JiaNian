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
     <title>温州瑞安高楼东魁梅</title>
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
            background: #e98751;
            border: 1px solid #e98751;;
        }
        .norms_wm_a.active{
            background: #e98751;
            border: 1px solid #e98751;;
        }

        ::-webkit-input-placeholder { /* WebKit browsers */
            color:    #e98751;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    #e98751;
            opacity:  1;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #e98751;
            opacity:  1;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color:    #e98751;
        }
        .main table td .srk1{ color:    #e98751;}
        .border_e98751{
        background-color: #e98751;
        }
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
    <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry8.jpg"  data-preview-src="" data-preview-group="1"></p>
        <h2 class="main_h2 bg_d74b68" style="">温州瑞安高楼东魁梅</h2>
        <p class="ym_title">温州杨梅据有史记载已有 500 年以上的栽培历史。明朝弘治年间（ 1488-1505 年）《温州府志》载：“杨梅，泰顺尤盛”。
            清光绪 8 年（ 1882 年）《永嘉县志》载：“旧志士产杨梅，今出茶山者，味尤胜”，现温州杨梅种植面积达到29.2万亩，产量7万多吨，
            全市种植面积占浙江省总面积的四分之一，产值达到3.5亿元，基地大致分布在温州瓯海、鹿城、永嘉、瑞安、苍南、平阳、乐清等县（市），
            其中最具有名气的温州杨梅属温州、瑞安高楼杨梅、瓯海茶山丁岙杨梅、永嘉东魁杨梅。在1992 年“高楼杨梅”被浙江省农业厅命名为浙江省四大杨梅优良品种之一。
            因高楼杨梅果重平均 17 克以上，固形物高达 12% 以上，酸甜可口，色紫黑，果柄特长而被誉为“红盘绿蒂”之称。</p>
        <p class="ym_content">瑞安高楼杨梅</p>
        <p class="img_p"><img class="main_p_img img_response"  src="<%=basePath%>images/school/bayberry/bayberry9.png?v=1"  data-preview-src="" data-preview-group="1"></p>
        <p class="ym_content">关于品种：黑炭梅、东魁梅</p>
        <p class="main_p_detail">高楼的黑炭梅、东魁梅均以果大、肉细、汁多、味甜而负盛名</p>
        <p class="img_p"><img class="main_p_img img_response"    src="<%=basePath%>images/school/bayberry/bayberry4.png?v=1"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry6.jpg?v=1"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry7.jpg?v=1"  data-preview-src="" data-preview-group="1"></p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry2.jpg?v=2"  data-preview-src="" data-preview-group="1"></p>
        <!-- 关于包装-->
        <p class="ym_content">关于包装</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry1.jpg?v=1"  data-preview-src="" data-preview-group="1"></p>
        <!-- 关于运输-->
        <p class="ym_content">关于运输：顺丰快递</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry11.png?v=1"  data-preview-src="" data-preview-group="1"></p>
        <!-- 关于采摘时间-->
        <p class="ym_content">关于采摘时间：6月下旬，杨梅旺季</p>
        <p class="main_p_detail">行车路线：G15沈海（甬台温）高速瑞安飞云出口——新56省道——高楼镇各杨梅观光园区</p>
        <p class="main_p_detail">周边旅游景点：游漈门溪景区，赏“十里画廊”，游览九珠潭景区和木活字印刷村，游览花岩国家森林公园 ，游览巾仙溪或双溪寨漂流</p>
        <!-- 关于发货-->
        <p class="ym_content">关于发货</p>
        <p class="img_p"><img class="main_p_img"  src="<%=basePath%>images/school/bayberry/bayberry12.png"  data-preview-src="" data-preview-group="1"></p>
        <p class="ym_content">30元/斤  抢鲜预定只需20元/斤</p>
        <p class="main_p_detail">重量规格：4斤、6斤、8斤，4斤包邮！！！</p>
        
        <p class="main_p2">详询：</p>
        <p class="main_p2">杨先生  <a href="tel:15355930038">15355930038</a></p>
        
    </div>

</div>



<div class="qnxzb_jg bg_fdfaf3"  >
    <div class="text-jg "><h2><strong class="color_e98751"><span>每斤：</span>￥<span style="color: #e98751;">${Competition.stringParamB }</span></strong>&nbsp;
    
   </h2></div>
</div>
<c:if test="${isApply!=0}">
	<div class="qnxzb_order bg_e98751" style="line-height: 28px;">您已购买${isApply}次<br/>再买一次</div>
</c:if>
<c:if test="${isApply eq 0}">
	<div class="qnxzb_order bg_e98751">立即购买</div>
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
            <img src="<%=basePath%>images/school/bayberry/bayberry3.jpg" class="img_response" alt="">
        </div>
        <div class="affirm_jieshao lf">
            <p class="affirm_introduce">温州瑞安高楼东魁梅</p>
            <p class="total">付款金额&nbsp;￥<span total="${Competition.stringParamB}">${Competition.stringParamB }</span></p>
        </div>
    </div>
    <div class="affirm_down">
    	 <div class="norms_yj" style="    padding-top: 10px;">
            <p>预定信息</p>
	        <div class="main">
	            <table border="0" cellspacing="0" cellpadding="0">
	                <tbody><tr>
	                    <td class="posi-rela"><input name="name"  placeholder="请输入中文姓名" type="text" class="srk1 bd_d74b68" id="name"></td>
	                </tr>
	                <tr>
	                    <td class="posi-rela"><input name="age"   placeholder="请输入手机号" type="number" class="srk1 bd_d74b68" id="phone"
	                                                 oninput="if(value.length>11)value=value.slice(0,11)"></td>
	                </tr>
	                <tr>
	                    <td class="posi-rela"><input name="address"     placeholder="详细地址" type="text" class="srk1 bd_d74b68" id="address"></td>
	                </tr>
	                </tbody></table>
	        </div>
        </div>
         
     <div class="norms_qc" style="    padding-top: 10px;">
            <p>数量(斤)</p>
            <div class="affirm_num">数量<button type="button">-</button><span>1</span><button type="button">+</button></div>
            
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
                            <h2 style="font-size: 26px;"><strong class="color_e98751"><span>需要付款：</span>￥<span  style="color: #e98751;font-size: 26px;" id="totalNum">${Competition.stringParamB }</span></strong></h2>
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
<input type="hidden" id="dataContent" name="dataContent" value="${Competition.title }，30元/斤  抢鲜预定只需20元/斤" />

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
        $("#imgId").attr('src',"<%=basePath%>images/school/bayberry/bayberry3.jpg");
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
	}else{
		window.location.reload();
	}
}
$("#qnxzb-cancle").click(function(){
    $("#qnxzb-shade").hide();
    $("#qnxzb-div").hide();
});
function checkpay(){
	var str="您选择了：";
	var num=0;
	var cousr=0;
	var period=0;
	var time=$(".affirm_num span").text();
	if($(".norms_qc .active").length>0){
		period = $(".norms_qc .active").attr("value");
		str+=$(".norms_qc .active").text();
	}
	$.ajax({
		url : "${path}/Students/phoneInputPayInfo?sina=<%=SmBaseUtil.Random()%>",
		type:"POST",
		data : {
			'name' : $("#name").val(),
			'phone' : $("#phone").val(),
			'address' : $("#address").val(),
			'pkid' : '${Competition.PKID}',
			'IntParamA':$(".affirm_num span").text(),
			'buid':'${buid}'
			
		},
		success : function(json) {
			var totalnum=$("#affirm_join .total span").text();
			$("#Surplus span").text("20￥ *  "+ time+"斤 = " + totalnum);
			$("#totalNum").text(totalnum);
			var c=new Object();
			c.w=json.CID;
			c.b='0';
			c.c= cousr+'-'+time+'-'+period;
			var attach= "<%= WXUtil.getWeChatAttach(request, SmBaseGlobal.PayUse.OtherPay.getid(), String.valueOf(user.getID()), String.valueOf(ac.getID()) ) %>";//默认为微米充值attach= "{type:1}";//默认为微米充值
			var kk=eval("["+attach+"]")[0];
			kk.c = c;
			var jsonstr = JSON.stringify(kk);
			initialWePayParam('#qnxzb-confirm',"#weimi_value",jsonstr,getQueryString("WID"));
		}
	});
	
	
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
       // checkpay();
	}
		
	
});

function checkparam(){
	if($("#name").val()=="" || $("#phone").val()=="" || $("#address").val()==""){
		webToast("请填写联系人和联系电话和快递地址","bottom",1800);
		 return false;
	}
	return true;
}


$("#affirm_join  .affirm_num button").click(function(event) {
    var elem = event.target;
    if ($(elem).text() == "+") {
        var snum = parseInt($(elem).prev().text());
        $(elem).prev().text(snum + 1);
       
           $("#affirm_join .total span").text((20 * parseInt($("#affirm_join .affirm_num span").text())).toFixed(2));
           $("#weimi_value").val((20 * parseInt($("#affirm_join .affirm_num span").text())))
           //$("#affirm_buy .affirm_num span").text($("#affirm_join .affirm_num span").text());
           //$("#affirm_buy .total span").text((dj * parseInt($("#affirm_buy .affirm_num span").text())).toFixed(2));
    } else {
        var snum = parseInt($(elem).next().text());
        $(elem).next().text(snum - 1);
        if (snum < 4) {
            layer.msg('购买数目4斤起售！',{time:1000   });
            $(elem).next().text(1);
            $(".total span").text(s_dj);
            //$("#affirm_buy .affirm_num span").text(1);
        }else{
            $("#affirm_join .total span").text(parseFloat(20 * parseInt($("#affirm_join .affirm_num span").text())).toFixed(2));
            $("#weimi_value").val(parseFloat(20 * parseInt($("#affirm_join .affirm_num span").text())))
            //$("#affirm_buy .affirm_num span").text($("#affirm_join .affirm_num span").text());
            //$("#affirm_buy .total span").text((dj * parseInt($("#affirm_buy .affirm_num span").text())).toFixed(2));
        }
    }
    event.stopPropagation();
});

</script>
</body>
</html>
