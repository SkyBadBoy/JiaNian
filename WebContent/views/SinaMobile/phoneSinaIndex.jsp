<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page import="wtb.smUtil.WeNewsListen"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	String imageurl=basePath+SmBaseGlobal.UserDefaultImageUrl ;
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
	
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
	request.setAttribute("PositionIcon", SmBaseUtil.getPositionIcon(request,user));
	String Adverts_Url = (String)request.getAttribute("Adverts_Url");
	if(!SmBaseUtil.CheckIsNull(Adverts_Url) ){
		Adverts_Url = basePath+"images/weixinwenshe/hbg.png?v=2";
	}
	request.setAttribute("Adverts_Url", Adverts_Url);
	
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>新浪小编证</title>
<jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
	<style type="text/css">
        .vipcenter .vipheader{position: relative;
        
            background: url(${Adverts_Url});
		    background-position: 50%;
		    background-repeat: no-repeat;
		    background-size: 100% 100%;
        }
        .vipcenter .vipheader .touxiang{  position:absolute;top: 60px;left: 50%;margin-left:-27px;width: 54px;height: 54px;}
        .vipcenter .vipsan div{border: 0}
        .vipcenter .vipsan p{color: #222638;margin: 10px 0 0 0;}
        .text-left{text-align: left;}
        .text-right{text-align: right;}
        .col-xs-6{width: 25%}
        .activity_now{display: block;line-height: 20px;border: 1px solid #FFFFFF;
            /*-moz-box-shadow: 1px 1px 10px #ddd;*/
            /*box-shadow: 1px 1px 10px #ddd;*/
        }
        .activity_now img{width: 40px;}
        .vipcenter .vipsan{}
        a{display: block;}
        .col-xs-3{width: 30%;}
        .vipcenter .vipheader .name {
            font-size: 12px;
            color: #fff;
            position: absolute;
            top: 25px;
            left: 50%;
            text-align: center;
            width: 240px;
            margin-left: -120px;
            background-color: #a8a09e;
            border-radius: 50px;
            line-height: 20px;
            padding: 2px 0;
            -webkit-box-shadow: 0 0 10px #a8a09e;
            -moz-box-shadow: 0 0 10px #a8a09e;
            box-shadow: 0 0 10px #a8a09e;
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
      
    </style>
</head>
<body class="b-color-f6f6f6">
<div class="vipcenter">
     <img src="${Adverts_Url}" style="position: absolute;top:0;width: 100%; height: 175px;">
    <div class="vipheader">
            <div class="name"><a style="color: #fff" href="<%=basePath%>weChatGroup/phoneWeNewsHome?_area_=${_area_}&sina=<%=SmBaseUtil.Random()%>">${Student.school}</a></div>
            <div class="touxiang"><a href="<%=path%>/Students/phoneSinaUserInfoEdit?pid=${Student.PKID}&AreaId=${AreaId}&sina=<%=SmBaseUtil.Random() %>"><img src="${imageurl}" alt=""></a></div>
            <div class="pc_shezhi">
                <a href="<%=path%>/Students/phoneSinaUserInfoEdit?pid=${Student.PKID}&AreaId=${AreaId}&sina=<%=SmBaseUtil.Random() %>">
                    <img src="<%=basePath%>images/nt_15.png" >
                </a>
            </div>
        <div class="pc_xingxing">${Student.name}<img src="${LevelIcon}"><img src="${PositionIcon}"></div> 
        <div class="pc_jf"><span>积分：${Student.integration!=null?Student.integration:0}</span> | <span>微米：${weMoney}${Student.diamondLevel }</span></div>
        <div class="pc_jfgz"><a href="<%=basePath%>Students/phoneSinaRule#starCont?sina=<%=SmBaseUtil.Random()%>">积分规则 ></a></div>
    </div>
	 <div class="vipsan" style="border-bottom:0;margin-top: 10px;">
        <div class="pc_div" style="border-bottom: 1px solid #f6f6f6;">
            <div class="col-xs-4 text-center">
                <a class="activity_now" href="<%=basePath%>Students/phoneSinaRule#starCont">
                	<c:if test="${ExcellentIsEnd==1}">
                		<img src="<%=basePath%>images/nt_1.png" >
                	</c:if>
                    <c:if test="${ExcellentIsEnd!=1}">
                		<img src="<%=basePath%>images/weixinwenshe/xwdr_b.png" >
                	</c:if>
                    <p>新闻达人(${ExcellentCount})枚</p>
                </a>
            </div>
            <div class="col-xs-4 text-center">
                <a class="activity_now" href="<%=basePath%>Students/phoneSinaRule#starCont">
                    <c:if test="${WriteIsEnd==1}">
                		 <img src="<%=basePath%>images/nt_2.png" >
                	</c:if>
                    <c:if test="${WriteIsEnd!=1}">
                		<img src="<%=basePath%>images/weixinwenshe/xzdr_b.png" >
                	</c:if>
                    <p>写作达人(${WriteCount})枚</p>
                </a>
            </div>
            <div class="col-xs-4 text-center">
                <a class="activity_now" href="<%=basePath%>Students/phoneSinaRule#starCont">
                    <c:if test="${SpreadIsEnd==1}">
                		 <img src="<%=basePath%>images/nt_3.png" >
                	</c:if>
                    <c:if test="${SpreadIsEnd!=1}">
                		<img src="<%=basePath%>images/weixinwenshe/cbdr_b.png" >
                	</c:if>
                    <p>传播达人(${SpreadCount})枚</p>
                </a>
            </div>
        </div>

        <div class="col-xs-4 text-center b-topddd">
            <a class="activity_now" id="demo0" >
            <!--<a class="activity_now" data-toggle="modal" data-target="#myModal" >-->
            <img src="<%=basePath%>images/nt_4.png" >
                <p>我的二维码</p>
            </a>
        </div>
        <div class="col-xs-4 text-center b-topddd" >
            <a class="activity_now" href="<%=path%>/Notices/phoneaddnotices?AreaId=${AreaId}&SID=${SID}&sina=<%=SmBaseUtil.Random()%>">

            <img src="<%=basePath%>images/nt_5.png" >
                <p>我要投稿</p>
            </a>
        </div>
        <div class="col-xs-4 text-center b-topddd">
            <a class="activity_now" href="<%=basePath%>Students/phoneSinaMyNews?sina=<%=SmBaseUtil.Random()%>">
                <img src="<%=basePath%>images/nt_6.png">
                <p>我的新闻</p>
            </a>
        </div>

    </div>
</div>
<section class="m-top10 my-nav-box b-color-f">
 	<a href="<%=basePath%>Students/phoneAppDownLoad">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/appicon.png">我要视频投稿</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
     <a href="https://weidian.com/s/1224157688?ifr=shopdetail&wfr=c">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/dianicon.png">微小店</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
     <a href="<%=basePath%>Activity/phoneGame_1?type=139">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/dialog_choose_card_z2d0795.png">你知道几个？</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
    <a href="<%=basePath%>Students/phoneShare">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/wemoney.png">邀好友赚微米</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
     
</section>
<section class="m-top10 my-nav-box b-color-f">

<a href="<%=basePath%>Ranking/phoneWXWDSRanking?sina=<%=SmBaseUtil.Random()%>">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/nt_9.png">2017微新闻大赛排行榜</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
    <a href="<%=basePath%>Ranking/phoneReadRanking?type=3&sina=<%=SmBaseUtil.Random()%>">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/nt_9.png">排行榜</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
</section>
<section class="m-top10 my-nav-box b-color-f">
	<a href="<%=basePath%>WeMoney/phoneWeMoney?sina=<%=SmBaseUtil.Random()%>">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/wemony.png">微米钱包</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/hot.png"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
    <a href="http://mall.palcomm.com.cn/index">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/nt_10.png">微米商城</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
    <a onclick="goPositive()" href="javascript:void(0)">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/nt_11.png">转正申请</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
</section>
<section class="m-top10 my-nav-box b-color-f">
    <a href="javascript:void(0)" onclick="SignView()">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/nt_12.png">每日签到</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
    <a href="<%=path%>/Feedback/phoneFeedback?sina=<%=SmBaseUtil.Random()%>">
        <div class="dis-box padding-all my-bottom">
            <h3 class="box-flex text-all-span my-u-title-size"><img class="is-user-size" src="<%=basePath%>images/nt_13.png">意见反馈</h3>
            <span class="t-jiantou"><img class="is-right-size jian-top" src="<%=basePath%>images/nt_14.png"></span>
        </div>
    </a>
</section>

</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
     <div class="c-float-popWrap confirmMode show" style="top: 10%;width: 280px;left: calc(50% - 140px);height: 350px;">
        <div class='pastCont'>
            <div class='pastTop'>
                <ul class="pastLi" id="list">
                	<li style="display: none;" ></li>
                    <c:forEach var="SignRecord" varStatus="status" items="${SignRecordList}">
	                    <c:if test="${SignRecord.week>=1 and status.index<=6}">
							<c:if test="${SignRecord.status==-1}">
								 <li><p>${SignRecord.week}</p></li>
							</c:if>
							<c:if test="${SignRecord.status!=-1}">
								 <li style="background-color:#fff"><p>${SignRecord.week}</p></li>
							</c:if>
						</c:if>
					</c:forEach>
                </ul>
            </div>
            <div class="pastBot">
                <div style="width: 100%;height: 50%;">
                    <p class="nowRank">当前积分</p>
                    <p id="rank">${Student.integration!=null?Student.integration:0}</p>
                    <p style="color: #7e7f84;font-size:16px;text-align: center;">签到可以赚取积分噢~</p>
                </div>
                <p id="pointAdd">积分+2</p>
                	<div id="Signstatus">
					</div>
                
            </div>
        </div>
    </div>
</div>

<div class="weui_mask_transparent fadeInDown hidden" style="background:rgba(0, 0, 0, 0)" >
    <div class="c-float-popWrap confirmMode show" style=" top: 30%; left:50%;width: 200px;margin-left:-100px;-moz-box-shadow: 0px 0px 20px;-ms-box-shadow: 0px 0px 20px;-webkit-box-shadow: 0px 0px 20px;box-shadow: 0px 0px 20px;">
        <div class="c-float-modePop" style="background-color: rgba(255,255,255,0);height:200px;">
            <div class="warnMsg" id="qrcodeTable" class="img-responsive"  style="padding: 0;margin: 0 auto;width: 200px;height: 200px;padding-top:0.5px;background-color: #FFF;
    padding: 10px;"></div>
        </div>
    </div>
</div>
<footer style="margin:10px 0;bottom: 10px;width: 100%">
    <div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
<br/>
<br/>
<br/>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script src="<%=basePath%>js/qrcode.min.js?v=3.3.7"></script>

<script type="text/javascript">
$(document).ready(function(){
	var qrcode = new QRCode(document.getElementById("qrcodeTable"), {
		width : 180,
		height : 180
	});

	function makeCode () {		
		qrcode.makeCode("<%=basePath%>Students/phoneSinaUserInfo?pid=${Student.PKID}&sina=<%=SmBaseUtil.Random()%>");
	}

	makeCode();
	
	/*$("#qrcodeTable").qrcode({
		render	: "table",
		text	: "<%=basePath%>Students/phoneSinaUserInfo?pid=${Student.PKID}&sina=<%=SmBaseUtil.Random()%>",
		width:"200",
		height:"200"
	});	*/
	$('#demo0').on('click', function () {
        $(".weui_mask_transparent").removeClass("hidden");
        $(".weui_mask_transparent").addClass("fadeInDown");
    });
    $(".weui_mask_transparent").on('click', function () {
        $(".weui_mask_transparent").addClass("hidden");
    });
});

var SignFalg=1;

function SignView()
{
	console.log(SignFalg)
	if (SignFalg==1) {
	   	$.getJSON("<%=path%>/Students/phoneGetSignInfo",
	   	{
	   		 uid : '<%=user.getID() %>'
	   	},
		function(json) {
			if (json && json.status == true || json.status == "true") {
				if(json.CurrSignRecord.status!=-1){
					$("#Signstatus").html('<div class="box pastBtn" style="background-color:#C7C7C7" id="oBtn">已签</div>');
				}else{
					$("#Signstatus").html('<div class="box pastBtn" id="oBtn" onclick="signup()" >签到</div>');
				}
				var html="<li style='display: none;' ></li>";
				var obj=json.SignRecordList;
				for(var i=0;i<json.SignRecordList.length;i++){  
					if(json.SignRecordList[i].week>=1 && i<=6){
						if(json.SignRecordList[i].status==-1){
							html+="<li><p>"+json.SignRecordList[i].week+"</p></li>";
						}else{
							html+=" <li style='background-color:#fff'><p>"+json.SignRecordList[i].week+"</p></li>"
						}
					}
				}
				$("#list").html(html);
				$("#myModal").modal("show")	
			}
		});
	}else{
		$("#myModal").modal("show")
	}
}
   var mydate = new Date();
   var week = mydate.getDay();
   var rankNub =  $('#rank').html();
   var obol = true;
   if(week === 0){
       week =7
   }
   function signup(){
	$('#oBtn').html('签到中');
   	$.getJSON("<%=path%>/Students/phoneSignUp?sina=<%=SmBaseUtil.Random()%>",
	{
 			uid : '<%=user.getID() %>'
	},
	function(json) {
		if (json && json.status == true || json.status == "true") {
			SignFalg=2;
			$('#pointAdd').css({
		           '-webkit-transform':"scale(1)",
		           'opacity':'1',
		           'bottom':'36%'
		       });
			   var rank=Number(rankNub)+json.Data;
		       $('#rank').html(rank);
		       $('#myintegr').text("积分："+rank);
			   
		       $('#pointAdd').html("积分+"+json.Data);

		       $('#oBtn').html('已签');
		       $('#oBtn').css("background-color","#C7C7C7");
		       $('#oBtn').removeAttr("onclick");
		      $('.pastLi').find('li').eq(week).css("backgroundColor",'#fff')
		       if(obol){
		            $('#oBtn').removeClass('box');
		            setTimeout(function(){
		           $('#oBtn').addClass('box');
		           obol=false
		           },300)
		       }else{
		    	   $('#pointAdd').css({
			           '-webkit-transform':"scale(1)",
			           'opacity':'1',
			           'bottom':'36%'
			       });
			       $('#pointAdd').html(json.Message);
		       }
		      console.log(SignFalg)
		}
	});
   }
   function addNumber(_idx){
	   var str = '';
	   for(var i = 0; i < _idx; i += 1){
	   str += Math.floor(Math.random() * 10);
	   }
	   return str;
	   }

 function goPositive(){
	 if(<%= user.getOfficial()%>==1){
		 webToast("您已经是正式社员了", "bottom", 2000);
		 return false;
	 }else if(<%= user.getLevel() %><4){
		 webToast("需要达到四星小编才可以申请正式社员，继续加油！", "bottom", 2000);
		 return false;
	 }
	 window.location='http://c.eqxiu.com/s/mGC1KX53?sina=<%=SmBaseUtil.Random()%>';
 }  
</script>
</body>
</html>