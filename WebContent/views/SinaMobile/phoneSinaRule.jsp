<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
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
//	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <title>新浪小编—积分规则</title>
    <style type="text/css">
        .starCenter{margin: 0;padding: 0}
        .starCenter .starHeader{height: 36%;width: 100%;border-bottom:1px solid #e3e6f2;}
        .starCenter .starHeader .figureHead{height: 200px;width:100%;text-align: center;}
        .starCenter .starHeader .figureHead a{display: inline-block;width: 100px;height: 100px;margin: 0px auto;margin-top: 20px}
        .starCenter .starHeader .figureHead a img{width: 100%;height: 100%;border-radius: 50%;}
        .starCont{margin-top: 26px;}
        .starCont p{font-size: 14px;line-height: 28px;color: #222638;width: 94%;margin-left:3% }
        /*20170515新增*/
		.rank-title{
			font-size: 16px;
			font-weight: 700;
		}
		.rank-xunzhang span{
			color: #CB2D01;
			font-size: 17px;
			font-weight: 700;
		}
		.rank-xunzhang img{
			padding-bottom: 5px;
			width:25px;
		}
		.rank-ad{
			width:100%!important;
		}
    </style>
</head>
<body class="whitebg">
<div class="starCenter">                           
    <div class="starHeader">
        <div class="figureHead">
        	<c:if test="${!isApp }">
        		<a href="<%=basePath%>Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random()%>" >
        	</c:if>
        	<c:if test="${isApp }">
        		<a>
        	</c:if>
                <img src="${imageurl}" alt="" style="box-shadow: 0 0 30px #c1c1c9">
            </a> 
            <div style="margin-top: 10px;text-align: center">
                <span class="colorc3c5c8">
           <c:if test="${Student.diamondLevel==0}">
	                 <c:if test="${Student.level<=1}">
				    	<i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
				    </c:if>
				     <c:if test="${Student.level==2}">
				    	<i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
				    </c:if>
				    <c:if test="${Student.level==3}">
				    	<i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
				    </c:if>
				    <c:if test="${Student.level==4}">
				    	<i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_dddddd fa-2x"></i>
				    </c:if>
				    <c:if test="${Student.level>=5}">
				    	<i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
			            <i class="fa fa-star  color_f6bd32 fa-2x"></i>
				    </c:if>
			    </c:if>
			    <c:if test="${Student.diamondLevel>=1}">
			    	<c:if test="${Student.diamondLevel==1}">
			    		<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    </c:if>
				     <c:if test="${Student.diamondLevel==2}">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    </c:if>
				    <c:if test="${Student.diamondLevel==3}">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    </c:if>
				    <c:if test="${Student.diamondLevel==4}">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-starb.png" style="width: 30px;height:30px">
				    </c:if>
				    <c:if test="${Student.diamondLevel>=5}">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    	<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 30px;height:30px">
				    </c:if>
			    </c:if>
                </span>
                </div>
                 <div style="margin-top: 10px;text-align: center;font-size: 16px;">
                    积分：${Student.integration!=null?Student.integration:0}
                </div>
        </div>   
    </div>
    <div class="starCont">
     	<p class="rank-xunzhang"><span>微米<span></span></p>
        <p>微米是微新闻社内部流通的兑换货币，使用微米可以在微新闻社积分商城兑换实物奖励，1微米等值与人民币1毛钱。</p>
        <br/>
     	<p style="font-weight: bold;">微米获取途径</p>
  		<p>1、通过新闻打赏。</p>
        <p>2、通过积分兑换，每增长积分超过100将会自动增长1微米，原积分不变，增长积分可以参考如下《积分增长规则》。</p> 
        <p>3、每天<span>首稿</span>奖励5微米；</p>
        <br/>
     	<p style="font-weight: bold;">积分增长规则</p>
        <p>1、发表的微新闻被阅读后,每阅读一次，积分+1。</p>
        <p>2、微新闻投票，每投一票，积分+1。</p>
        <p>3、微新闻投稿，每投一稿且审核通过的，积分+5 </p>
        <p>4、线下活动参与，每参与1次，积分+10，根据活动的具体内容，可适当调整积分的奖励措施 </p>
        <p>5、每日签到 </p>
        <br/>
     	<p style="font-weight: bold;">签到规则</p>
        <p>1、以一周为一个签到周期，连续签到7天的积分依次为:1、2、3、4、5、5、5;连续7天签到后共可获得25积分。</p>
        <p>2、连续签到N天后如果中断签到、签到积分从头开始计算。</p>
        <p>3、每周一开始，清空上周的签到数，重新开始计算签到积分，依此类推</p>
     </div>
     <div class="starCont">
     	<p class="rank-xunzhang"><span>星级制度<span></span></p>
        <p>星级的提升需要依托积分制度，当积分满足到下一级的分值条件后，即可将星级提升到下一级。</p>
        <p>目前积分的设定如下：</p>
        <p>一星<img src="<%=basePath%>img/star1.png" style="width: 20px;height:20px">：0分到100分 </p>
        <p>二星<img src="<%=basePath%>img/star2.png" style="width: 20px;height:20px">：100分 到500分</p>
        <p>三星<img src="<%=basePath%>img/star3.png" style="width: 20px;height:20px">：500分到2000分</p>
        <p>四星<img src="<%=basePath%>img/star4.png" style="width: 20px;height:20px">：2000分到5000分</p>
        <p>五星<img src="<%=basePath%>img/star5.png" style="width: 20px;height:20px">：5000分到10000分</p>
        <p>当新浪小编的积分达到<span style="font-weight: bold;">20000</span>以上,就会点亮<img src="<%=basePath%>images/weixinwenshe/d-star.png" style="width: 20px;height:20px">图标之后，将成为微新闻社的金牌社员，将会有一定的岗位薪资，不同的级别岗位工资不同，完成每月额定任务即可获得岗位薪资。</p>
        <p><span style="font-weight: bold;">一钻星</span><img src="<%=basePath%>images/weixinwenshe/d-star1.png" style="width: 20px;height:20px">：20000分到50000分</p>
         <p>每个月完成30篇新闻稿，即可获得薪资3元/月。</p>
        <p><span style="font-weight: bold;">二钻星</span><img src="<%=basePath%>images/weixinwenshe/d-star2.png" style="width: 20px;height:20px">：50000分到100000分</p>
          <p>每个月完成40篇新闻稿，即可获得薪资5元/月。</p>
        <p><span style="font-weight: bold;">三钻星</span><img src="<%=basePath%>images/weixinwenshe/d-star3.png" style="width: 20px;height:20px">：100000分到200000分</p>
          <p>每个月完成50篇新闻稿，即可获得薪资10元/月。</p>
        <p><span style="font-weight: bold;">四钻星</span><img src="<%=basePath%>images/weixinwenshe/d-star4.png" style="width: 20px;height:20px">：200000分到500000分</p>
          <p>每个月完成70篇新闻稿，即可获得薪资50元/月。</p>
        <p><span style="font-weight: bold;">五钻星</span><img src="<%=basePath%>images/weixinwenshe/d-star5.png" style="width: 20px;height:20px">：500000分以上</p>
          <p>每个月完成100篇新闻稿，即可获得薪资100元/月。</p>
     </div>
     
    <div class="starCont" id="starRule" >
     	<p class="rank-xunzhang"><span>荣誉制度</span></p>
        <p><span style="font-weight: bold;">皇冠制度</span>，默认首次注册的用户为灰色皇冠<img src="<%=basePath%>images/weixinwenshe/fcy.png" style="width: 20px;height:20px"></p>
        <p>根据积分的增长规则，当达到三星<img src="<%=basePath%>img/star3.png" style="width: 20px;height:20px">的标准，即可点亮<img src="<%=basePath%>images/weixinwenshe/cy.png" style="width: 20px;height:20px">同时，也将自动变为新浪小编<strong>正式社员</strong><br/>新浪小编的岗位分为<strong>社长</strong>、<strong>副社长</strong>、<strong>编委</strong><br/></p>
        <p>普通社员，不同的岗位皇冠的表示不同，社长岗位的皇冠上会有“社”字<img src="<%=basePath%>images/weixinwenshe/sz.png" style="width: 20px;height:20px">副社长的皇冠上会有“副”字<img src="<%=basePath%>images/weixinwenshe/fsz.png" style="width: 20px;height:20px">编委岗位的皇冠上会有“编”字<img src="<%=basePath%>images/weixinwenshe/bw.png" style="width: 20px;height:20px"></p>
        <div class="starCont">
	     	<p ><img src="<%=basePath%>images/weixinwenshe/sz.png"><strong>社&nbsp;&nbsp;&nbsp;长</strong>尊享300微米奖励</p>
	     	<p ><img src="<%=basePath%>images/weixinwenshe/fsz.png"><strong>副社长</strong>尊享200微米奖励</p>
	     	<p ><img src="<%=basePath%>images/weixinwenshe/bw.png"><strong>编&nbsp;&nbsp;&nbsp;委</strong>尊享100微米奖励</p>
	     </div>
        <br/>
        <p id="starCont"><span style="font-weight: bold;">勋章制度</span>，系统会根据每周的综合情况进行勋章评选，每个勋章只在获得勋章的当周有效，每周一将会重新进行评选，在有效期内勋章将会点亮，勋章分为以下几种：<br/>
        <strong>写作达人<img src="<%=basePath%>images/weixinwenshe/xzdr.png" style="width: 20px;height:20px"></strong>：上周新浪小编个人投稿前十的学生将会获得该勋章<br/>
        <strong>新闻达人<img src="<%=basePath%>images/weixinwenshe/xwdr.png" style="width: 20px;height:20px"></strong>：投稿的新闻入选微新闻简报的小编将会获得该勋章<br/>
        <strong>传播达人<img src="<%=basePath%>images/weixinwenshe/cbdr.png" style="width: 20px;height:20px"></strong>：上周阅读排行榜前十的学生将会获得该勋章<br/>
        	每个学期末会根据勋章的数量和活跃度进行社长,副社长,编委岗位的重新评选。</p>
     </div>
     <div class="starCont rank-xunzhang">
     	 <p class="rank-xunzhang"><span>打赏制度</span></p>
     	 <p>每天打赏对象超过三人(对同一个用户重复打赏仅记为一次)，且打赏的微米数量超过50，则在第二天首次打赏时会返还昨天打赏的微米数，最多返还50微米，
		若第一天打赏对象超过三人，打赏微米超过50，第二天未继续打赏，则第三天打赏的时候微米不再返还</p>
	     <p>每篇新闻稿件<strong>过百</strong>奖励5微米 过两百奖励10微米 <strong>过三百</strong>奖励15微米，</p>
	     <p>依此类推每过百在前基数上多奖励5微米，100微米封顶；</p>
	     <p>点赞数<strong>超过50</strong>，奖励10微米，每过50则在前基数上奖励5微米；</p>
	     <p>评论数，不同的人评论，<strong>超过10条</strong>评论即可开始打赏，打赏5微米，每过10条前基数上奖励5微米。</p>
     </div>
</div>
<footer style="margin:10px 0;bottom: 10px;width: 100%">
    <div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
</body>
</html>