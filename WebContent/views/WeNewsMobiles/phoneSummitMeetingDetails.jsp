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
    <title>新西兰-中国2017年首届未来青年领袖国际峰会</title>
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
    <img src="<%=basePath %>images/school/banner3.jpg?v=1.0.1" id="imgId"/>
</div>
<div id="main" >
    <div class="main bg_ffffff color_333">
        <h2 class="main_h2 bg_073b92">新西兰-中国2017年首届未来青年领袖国际峰会</h2>
        <p class="act_title color_073b92">活动介绍</p>
        <!--大赛总则-->
        <p class="main_title ">奥克兰市长致辞：</p>
        <p class="main_p">
            我特别荣幸的欢迎你们来到南太平洋的美丽城市奥克兰，你们是奥克兰中国姐妹城市的下一代领袖。
            作为未来全球领袖的你们，本次峰会中将接受来自各个不同层次领域的挑战，锻炼和培养你们的全球化思维。
            你们将反思如何更好地作为新一代的青年领袖，与国际伙伴共同探讨文化差异、传统文化和思维方式等议题，
            一起迎接快速变化世界带来的困难和挑战。
        </p>
      <p class="main_title">峰会举办目的与意义</p>
        <p class="main_p">作为新一代的未来青年国际领袖，将会面临各种不同的全球性问题和挑战。而往往这些问题和挑战，不是单独靠一个国家就能解决的，需要全球领导人共同磋商、合作解决。因此，作为新一代的未来青年领袖必须学会如何在不同文化、语言和传统中沟通交流，如何进行跨文化的参与与合作，共同促进经济的繁荣和社会发展。 </p>
        <p class="main_p">未来青年领袖国际峰会的举办，正是在这一背景下，旨在通过一系列的研讨、会议、论坛、比赛等活动，促进新西兰和中国学生之间的交流，培养学生的跨文化沟通交流能力、整合思维能力，提升学生对国际事务和国际参与的认识与探索，塑造学生的全球领袖才能。  </p>
        <p class="main_title">与精英的对话</p>
        <p class="main_p">聆听不同领域精英人士的分享，探讨相关的国际议题，了解新一代国际青年领袖面临的机遇与挑战，提高对全球性问题的认知。</p>
        <p class="main_title">专家、教授指导</p>
        <p class="main_p">在不同领域的专家、教授的指导下，与新西兰学生共同探讨未来青年领袖面临的挑战，探索解决策略，促进学业发展和职业发展。</p>
        <p class="main_title">与国际伙伴交流竞赛</p>
        <p class="main_p">与新西兰学生组成团队，共同探讨国际议题，参加比赛；结交国际朋友，建立国际社交网络，提高跨文化沟通交流能力。</p>
        <p class="main_title">收获奖学金与推荐信</p>
        <p class="main_p">比赛中，表现优秀学生可获得：<br/>
一等奖：在中国/新西兰城市学习一个学期的学费；<br/>
二等奖：在中国/新西兰城市学习英语/普通话一个月学费；<br/>
三等奖：在中国/新西兰城市学习英语/普通话两周的学费；<br/>
以及推荐信；<br/>
（每个奖项1名学生，一年有效）</p>
        <p class="main_title">难得的实践机会</p>
        <p class="main_p">表现优秀的学生可获得：
作为学生志愿者，参与2018年新西兰-中国未来青年领袖国际峰会的策划、筹备和举办。
作为学生大使，在2018年新西兰-中国未来青年领袖国际峰会中发表演讲</p>
         <p class="main_title">收获荣誉与证书</p>
        <p class="main_p">对促进本次峰会的相关组织单位，将获得肯定与荣誉。
参与峰会的学生将获得峰会参加证书。</p>
 <p class="main_title">峰会时间安排</p>
        <p class="main_p">出发时间：2017年7月10日<br/>
&nbsp;&nbsp;报到时间：2017年7月11日<br/>
&nbsp;&nbsp;峰会时间：2017年7月12日-7月19日<br/>
&nbsp;&nbsp;返程时间：2017年7月20日<br/>
（共计12天）</p>
         <p class="main_title">峰会参加人员</p>
        <p class="main_p">学生人数：200人（其中新浪浙江微新闻社20人）<br/>
&nbsp;&nbsp;年龄要求：13-19岁 </p>
         <p class="main_title">峰会主要地点</p>
        <p class="main_p">奥克兰大学 商学院 Owen G Glen大楼</p>

        <div class="maindiv bg_ddd"></div>
        <!-- 峰会时间表-->
        <p class="main_title">峰会主要行程</p>
        <div class="activity_time color_333">
            <div class="history">
                <div class="history-date">
                    <ul>
                        <h2 class="first">
                            <a class="color_333">2017</a>
                            <!--<img src="images/img05.gif" alt="360极速浏览器历程" />-->
                            <a class="more-history color_333" >峰会历程</a>
                        </h2>
                        <li class="green history-date-ul-li">
                            <h3>7月11日<span></span></h3>
                            <dl>
                                <dt>乘坐国际航班前往新西兰奥克兰</dt>
                            </dl>
                        </li>
                        <li class="history-date-ul-li">
                            <h3>7月12日<span></span></h3>
                            <dl>
                                <dt>未来青年领袖国际峰会注册报到</dt>
                            </dl>
                        </li>
                        <li class="history-date-ul-li">
                            <h3>7月13-18日<span></span></h3>
                            <dl>
                                <dt>峰会开幕式与政府欢迎仪式<span>峰会系列论坛</span></dt>
                                <ul  type="no" class="luntan_ul">
                                    <li>系列论坛一：全球事务</li>
                                    <li>系列论坛二：文化理解与国际参与</li>
                                    <li>系列论坛三：未来全球领导人与国际公民的个人素质培养</li>
                                    <li>系列论坛三：未来全球领导人与国际公民的个人素质培养</li>
                                    <li>系列论坛四：全球挑战</li>
                                    <li>系列论坛五：全球议题讨论工作坊</li>
                                    <li>系列论坛六：建立新西兰和中国的有效关系</li>
                                    <li>系列论坛七：中国和新西兰的经济合作</li>
                                    <li>系列论坛八：创新与创业</li>
                                    <li>系列论坛九：学生演讲展示</li>
                                    <li>系列论坛十：中国与新西兰的关系</li>
                                    <li>系列论坛十一：领导与外交</li>
                                </ul>
                                <dt>2018年未来青年领袖高端峰会（中国）的相关事项宣布</dt>
                                <ul  type="no" class="luntan_ul">
                                    <li>实践活动系列一：环境保护实践日</li>
                                    <li>实践活动系列二：毛利文化学习日</li>
                                    <li>实践活动系列三：创新创业教育日</li>
                                    <li>实践活动系列四：探险之旅</li>
                                </ul>
                            </dl>
                        </li>
                        <li class="history-date-ul-li">
                            <h3>7月19日<span></span></h3>
                            <dl>
                                <dt>峰会闭幕式</dt>
                            </dl>
                        </li>

                        <li class="history-date-ul-li">
                            <h3>7月20日<span></span></h3>
                            <dl>
                                <dt>乘坐国际航班返回中国</dt>
                            </dl>
                        </li>
                        <li class="history-date-ul-li">
                            <h3>7月21日<span></span></h3>
                            <dl>
                                <dt>到达国内</dt>
                            </dl>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
        <!--九、点击关注二维码-->
        <p class=""><img class="main_ewm_img" src="<%=basePath %>images/school/QRCode.png"></p>
        <p class="main_ewm_title color_333">扫描上方二维码点击关注公众号!</p>
        <footer style="background: #fff;padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;padding-bottom: 50px">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
	</footer>
    </div>

</div>

<input class="joinin bg_073b92" onclick="window.location='<%=basePath %>Competition/phoneSummitMeeting'" type="submit" name="toastBtn" id="toastBtn" value="点击报名"/>
<!--<div class="actGotop" style="display: none;" id="actGotop"><a href="activity.html" title="返回顶部"></a></div>-->
<div class="guide" id="guide" style="display: none">
    <div class="guide-wrap">
        <a href="<%=basePath %>Competition/phoneSummitMeeting" class="edit" title="点击报名"><span>点击报名</span></a>
        <!--<a href="#" class="find" title="找论坛"><span>找论坛</span></a>-->
        <!--<a href="#" class="report" title="反馈"><span>反馈</span></a>-->
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
        $("#imgId").attr('src',"<%=basePath%>images/school/banner3.jpg");
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
