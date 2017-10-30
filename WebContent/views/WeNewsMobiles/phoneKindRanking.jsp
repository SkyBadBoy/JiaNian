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
	Students user =(Students)request.getSession().getAttribute("StudentName");
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
	
%>
<HTML lang="en">
<HEAD>

<META charset="utf-8">
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="<%=basePath%>css/style.css?v=1" rel="stylesheet">
    <link href="<%=basePath%>css/plugin/mmenu/jquery.mmenu.all.css" rel="stylesheet"/>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/plugins/swiper/swiper.min.css?v=3.3.7" />
    <link href="<%=basePath%>css/style.min.css?v=4.0.1" rel="stylesheet"><base target="_blank">
    <link href="<%=basePath%>css/minnie.css" rel="stylesheet"/>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/index.css?v=1"/>
     <link rel="stylesheet" href="<%=basePath%>css/plugin/dropload/dropload.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/plugin/bootstrap/bootstrap.min.js"></script>
    <style>
        .dropload-down{margin-bottom: 45px;}
    </style>
</head>
<body class="bke9e9e9">
	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
	<input type="hidden" id="type" name="type" value="${type}" />
	<input type="hidden" id="_page_" value="1">
	

		<div class="header ">
		    <a class="header_left" href="#menu"></a>
		    <a href="<%=basePath%>Students/phoneSinaIndex" style="padding-top:35px;"><img src="${imageurl}" class="header_img" style="margin-top: 12.5px;"></a>
		    <a class="contribute" href="<%=path%>/Notices/phoneaddnotices?AreaId=${_area_}&SID=${_area_}">
		        <span>我要投稿</span>
		        <img src="<%=basePath%>img/weixinwenshe/write_page.png" style="">
		    </a>
		</div>
	
<div style="height: 75px;"></div>
<div class="searchAdvertise">
    <a href="#"><img class="search-imgresp" src="<%=basePath%>images/bg_month.png"></a>
    <!--<span><img src="images/news.png" class="searchAdvertise-img"></span>-->
    <span class="searchAdvertise-span">${title}</span>
</div>
<div id="myDiv">
  
</div>
<jsp:include page="/include/rankMenu.jsp"></jsp:include>

<footer style="background: #fff;padding:10px 0;float: left;width: 100%">
	<div style="text-align: center;margin-bottom: 50px;">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
	</footer>


<jsp:include page="/include/mobileMenu.jsp"></jsp:include>
<script src="<%=basePath%>js/plugin/webp/modernizr.webp.js"></script>
<script src="<%=basePath%>js/plugin/webp/webp.min.js"></script>
<script src="<%=basePath%>js/wechat_index.js?v=1.0.10"></script>
<script src="<%=basePath%>js/plugin/lazyload/jquery.lazyload.js"></script>
<script src="<%=basePath%>js/plugin/updown/updown.js"></script>
<script src="<%=basePath%>js/plugin/webp/modernizr.webp.js"></script>
<script src="<%=basePath%>js/plugin/webp/webp.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#searchSelected").click(function () {
            if ($(this).hasClass("searchOpen")) {
                $("#searchTab").hide();
                $(this).removeClass("searchOpen");
            } else {
                $("#searchTab").show();
                $(this).addClass("searchOpen");
            }
        });
        $('#content').on('keyup', function () {
            $('#clear').show();
        });
        $('#clear').click(function () {
            $('#content').val('');
        });

        $("#searchTab li").hover(function () {
            $(this).addClass("selected");
        }, function () {
            $(this).removeClass("selected");
        });
        $("#searchTab li").click(function () {
            $("#searchSelected").html($(this).html());
            $("#searchTab").hide();
            $("#searchSelected").removeClass("searchOpen");
        });

    });
</script>
<script type="text/javascript">
$("#myDiv").append(getLoading()); 
$(function() {
	var i = 1; //设置当前页数 
	$(window).scroll(
	function() {
		if (getScrollTop() + getWindowHeight() == getScrollHeight() || $("#_page_").val()==1) {
			 $(".sk-spinner").remove();
			$("#myDiv").append(getLoading()); 
			$.getJSON("<%=basePath%>/Ranking/getphoneRankingKing",
			{
				pageNumber : $('#_page_').val(),
				type : $("#type").val(),
				submitType:'phone',
				rand:Math.random()
			},
			function(json) {
				if (json && json.Status == 1) {
					$.each(json.Data,
						function(index, array) {
	                    	var str=getRankDOM(array);
							$("#myDiv").append(str);
						});
					if(json.Data.length<=0){
						AddNoMoreDOM($("#myDiv"));
					}else{
						var _page_ = $('#_page_').val();
						$('#_page_').val(parseInt(_page_) + 1);
						$(".sk-spinner").remove();
						if(json.Data.length<10){
							AddNoMoreDOM($("#myDiv"));
						}else{
							$("#myDiv").append(getLoading());
						}
						
					}
				} else {
					AddNoMoreDOM($("#myDiv"));
					return false;
				}
				bindImgError();
			});
		}
	});
});
function bindImgError(){

	$("img").error(function () {
	    $(this).unbind("error").attr("src", $("#basePath").val()+"img/tx_default.jpg");
	});

}
//获取动态添加的活动和产品信息
function getRankDOM(array){
	var tmp = '';
		var imageUrl="<%=path%>/images/write.png";
		var color="#FFCA0C";;
		if(array.level=="阅读之最"){
			imageUrl="<%=path%>/images/read.png";
			color="#FFCA0C";
		}else if(array.level=="点赞之最"){
			imageUrl="<%=path%>/images/afterJob.png";
			color="#FD4C5D";
		}else if(array.level=="发稿之最"){
			imageUrl="<%=path%>/images/write.png";
			color="#FFCA0C";
		}else if(array.level=="分享之最"){
			imageUrl="<%=path%>/images/us.png";
			color="#7EA6E3";
		}else if(array.level=="赞赏之最"){
			imageUrl="<%=path%>/images/weimi.png";
			color="#FFCA0C";
		}else if(array.level=="打赏之最"){
			imageUrl="<%=path%>/images/giveMoney.png";
			color="#FFA767";
		}else if(array.level=="评论之最"){
			imageUrl="<%=path%>/img/publish-detail-icon.png";
			color="#08A8E1";
		}else if(array.level=="社长之最"){
			if(array.sex==1){
				imageUrl="<%=path%>/img/boy.png";
				color="#56ABE4";
			}else if(array.sex==2){
				imageUrl="<%=path%>/img/girl.png";
				color="#EB4F38";
			}else{
				imageUrl="<%=path%>/img/tx_default.jpg";
				color="#56ABE4";
			}
			
		}else if(array.level=="人气之最"){
			imageUrl="<%=path%>/img/1.png";
			color="#FF6E04";
		}
		if(array.type==1){
			tmp = '<a href="<%=path%>/Students/phoneSinaUserInfo?pid='+ array.id +'&sina=<%=SmBaseUtil.Random() %>">';
		}else{
			tmp = '<a href="<%=path%>/Product/phoneweChatPordDetail?_type_=2&_pid_='+ array.id +'&_pc_=<%=SmBaseUtil.Random() %>&_area_=&?sina=<%=SmBaseUtil.Random() %>">';
		}
		
        tmp +=  '<div class="searchRank" style="">' +
                '<div class="sRankPeopleDbcolor" style="">' +
                '<div class="sRankPeopleO_divf ">' +
                '<img src="'+ array.imageUrl +'" style="height:51px;">' +
                '<span class="sRankPeopleO_divf_spanone" style="font-size: 16px;color:'+ color +'">' + array.level + '</span>' +
                '<span limit="5" class="rank_name"> ' + array.title + '</span>' +
                '<span limit="8" class="rank_school"> ' + array.school + '</span>' +
                '<div class="rank_weimi"><img src="'+ imageUrl +'"></div>' +
                '<div class="rank_weiminum"> ' + array.readCount + '</div>' +
                '</div>' +
                '</div>' +
                '</div></a>';
   
	return tmp;
}

</script>
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
    nav4.bindClick(document.getElementById("wx_nav4_ul").querySelectorAll("li>a"), document.getElementById("nav4_masklayer"));
</script>
<script type="text/javascript">
	$(function () {
        $('nav#menu').mmenu();
    	var _page_ = $('#_page_').val();
    	if(parseInt(_page_)>1){
    		$('#_page_').val(parseInt(_page_) - 1);
    	}else{
    		$('#_page_').val(1);
    	}
		
        $(window).scroll();
    
        
    });
	
</script>
<!-- 友盟统计代码 begin -->
<%@ include file="../../cs.jsp" %>
<%CS cs = new CS(1260405162);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 友盟统计代码 end -->
</body>
</html> 