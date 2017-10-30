<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
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
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html >
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>疯狂猜图 - 排行榜</title>
<link rel="stylesheet" href="">
<link href="<%=basePath%>css/ct_style.css?v=3.3.5" rel="stylesheet">
<link href="<%=basePath %>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=basePath%>css/plugins/blueimp/css/blueimp-gallery.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=basePath%>css/plugins/swiper/swiper.min.css?v=3.3.5" rel="stylesheet" type="text/css" />
 <style>

        /*排行版*/
        .index_re_Hd {overflow:hidden;padding:0 ;border-right: 1px solid #F83AE6;border-left: 1px solid #F83AE6;border-bottom: 1px solid #F83AE6; }
        .index_re_Hd ul li {display:block;background-color:#FFF;}
        .index_re_Hd ul li .t {height:40px;position: relative;border-bottom: 1px solid #F83AE6;margin: 10px 10px 10px 10px;}
        .index_re_Hd ul li .t .rz_icon_a img {margin: 15px 0 0 5px;}
        .index_re_Hd ul li .t a .index_re_List_Tx {display:block;width:30px;height:30px;border-radius:30px;float:left;margin:5px 0 0 10px;}
        .index_re_Hd ul li .t a .index_re_List_Tx img {width:30px;height:30px;border-radius:30px;}
        /*.index_re_Hd ul li .t a:active .index_re_List_Tx img {opacity:0.7;}*/
        .index_re_Hd ul li .t a .index_re_List_Name {display:inline-block;font-size:14px;color:#333;float:left;margin:8px 0 0 8px;width: 30%;overflow: hidden;  white-space: nowrap;  text-overflow: ellipsis;}
        /*.index_re_Hd ul li .t a:active .index_re_List_Name {color:#dde1ea;}*/
        .index_re_Hd ul li .t a .index_re_List_rank {display:block;width:25px;height:25px;border-radius:30px;float:left;margin:5px 0 0 10px;color:#F83AE6 }
        .index_re_Hd ul li .t a .index_re_List_rank img {width:25px;height:25px;border-radius:30px;}
        .index_re_Hd ul li .t a .index_re_List_num {display:inline-block;font-size:14px;color:#333;float:right;margin:8px 8px 0 8px;}
        .dropload-down{height: 30px;}
        .dropload-refresh, .dropload-update, .dropload-load, .dropload-noData{height: 30px;line-height: 30px;}
        .game_rank_bk{width: 100%;position: relative}
        .game_rank_wxws{position: absolute;top: 20px;width: 86px;right: 10px;;z-index: 10;}
        .game_rank_bd{border-right: 1px solid #F83AE6;border-left: 1px solid #F83AE6;border-top: 1px solid #F83AE6;height: 200px;position: absolute;top: 95px;background-color: rgba(255,255,255,0);left: 3% ;width: 94%;z-index:9;}
        .game_rank_zj{position: absolute;top: 80px;width: 120px; left: calc(50% - 60px);z-index: 10}
        .game_rank_zjrank{position: absolute;top: 85px;width: 106px; left: calc(50% - 53px);z-index: 10;text-align: center;color: #fff;font-size: 15px;}
        .game_rank_zjhead{position: absolute;top: 20%;width: 60px; left: 13%;z-index: 10;border-radius: 100%;}
        .game_rank_my{position: absolute;top: 21%;width: 100px;text-align: left; right: 30%;z-index: 10;border-radius: 100%;color: #fff;}
        .game_rank_mynum{position: absolute;top: 25%;width: 100px; text-align: left;right: 30%;z-index: 10;border-radius: 100%;color: #fff;}
        .game_rank_cg{position: absolute;top: 21%;width: 100px;text-align: left; right: 5%;z-index: 10;border-radius: 100%;color: #fff;}
        .game_rank_cgnum{position: absolute;top: 25%;width: 100px;text-align: left; right: 5%;z-index: 10;border-radius: 100%;color: #fff;}
        .game_rank_div{font-size: 15px;}
        @media only screen and (device-height :480px) and (-webkit-device-pixel-ratio:2){/*4 4s*/
            .index_re_Hd ul li .t a .index_re_List_Name{width: 30%;}
        }
        @media (device-height:568px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone5 */
            .index_re_Hd ul li .t a .index_re_List_Name{width: 35%;}}
        @media (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2){/*6*/
            .index_re_Hd ul li .t a .index_re_List_Name{width: 45%;}}
        @media (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3){/*6+*/
            .index_re_Hd ul li .t a .index_re_List_Name{width: 50%;}}
        .mui-content{
            background: -webkit-linear-gradient(#97318a, #5b0694); /* Safari 5.1 - 6.0 */
            background: -o-linear-gradient(#97318a, #5b0694); /* Opera 11.1 - 12.0 */
            background: -moz-linear-gradient(#97318a, #5b0694); /* Firefox 3.6 - 15 */
            background: linear-gradient(#97318a, #5b0694); /* 标准的语法（必须放在最后） */
            min-height:500px;
        }
        @media only screen and (device-height :480px) and (-webkit-device-pixel-ratio:2){/*4 4s*/
            body{min-height:430px;}
        }
        @media (device-height:568px) and (-webkit-min-device-pixel-ratio:2){/* 兼容iphone5 */
            body{min-height:518px;}}
        @media (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2){/*6*/
            body{min-height:617px;}}
        @media (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3){/*6+*/
            body{min-height:686px;}
            .game_rank_div{font-size:16px;}
            .game_rank_zjhead{width:70px;}
        }
    </style>
<body class="mui-content" >
		 <div style="background: #fff;margin: 0 3%;">
        <div class="game_rank_div">
            <img src="<%=basePath %>images/games/rank4.png"  class="game_rank_bk">
            <img src="<%=basePath %>images/games/rank5.png"  class="game_rank_wxws">

            <div class="game_rank_bd"></div>
            <img src="<%=basePath %>images/games/rank8.png"  class="game_rank_zj">
            <div class="game_rank_zjrank">最佳排行榜</div>
            <img src="${imageurl }"  class="game_rank_zjhead">
            <div   class="game_rank_my">我的排行</div>
            <c:if test="${data.level eq -1}">
            	<div   class="game_rank_mynum">未上榜</div>
            </c:if>
             <c:if test="${data.level ne -1}">
            	<div   class="game_rank_mynum">${data.level}名</div>
            </c:if>
            <div   class="game_rank_cg">我的闯关</div>
            <div   class="game_rank_cgnum">${data.rightCount}关</div>
            </div>
        <div class="index_re_Hd weui_panel">
            <ul class="index_re_Ul weui_panel_bd">
               
            </ul>
        </div>
    </div>
    <div style="background: url('<%=basePath %>images/games/rank7.png');background-size: cover;height:20px;"></div>

</div>
<input type="hidden" id="_page_" value="1">
<script src="<%=basePath%>js/jquery.min.js?v=1.1.1.1" type="text/javascript" ></script>
<script src="<%=basePath%>js/wechat_index.js?v=1.1.1.1"></script>
<script type="text/javascript">
loadData();
$(function() {
	var i = 1; //设置当前页数 
	$(window).scroll(function() {		
		if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
			loadData();
		}
	});
});

function loadData(){
	 $(".sk-spinner").remove();
	 $("#NotMore").remove(); 
	 $(".weui_panel_bd").append(getLoading());
	 $.getJSON("<%=basePath%>GameQuestion/phoneGameQuestionRank",
		{
		 pageNumber:$('#_page_').val(),
		 type:'${type}',
		 pageSize:10,
		 submitType:'phone',
		 sina:"<%= SmBaseUtil.Random() %>"
		 },function(json){ 
            if(json && json.Status==true){ 
            	
            	var tmp="";
                $.each(json.data,function(index,array){ 
                	var rank='';
                	if ((array.level) <= 3) {
                		rank ='<img src="<%=basePath %>images/games/rank'+ array.level +'.png">' ;
                    }else {
                    	rank =array.level ;
                    }
                	tmp +=
                         '<li class="index_re_List">' +
                         '<div class="t">' +
                         '<a href="javascript:void(0)">' +
                         '<div class="index_re_List_rank">'+ rank +'</div>' +
                         '<div class="index_re_List_Tx"><img src="'+ array.student.imageUrl.split(',')[0] +'" ></div>' +
                         '<span class="index_re_List_Name">'+ array.student.name +'</span>' +
                         '<span class="index_re_List_num">闯关数：'+ array.rightCount +'</span>' +
                         '</a>' +
                         '</div>' +
                         '</li>';
                         
                	 
                }); 
                $(".weui_panel_bd").append(tmp); 
                if(json.data.length<=0){
                	$(".sk-spinner").remove();
                }else{
                   $(".sk-spinner").remove();
					$(".weui_panel_bd").append(getLoading());
    
                }
                var _page_=$('#_page_').val();
                $('#_page_').val(parseInt(_page_)+1);

            }else{ 
            	$(".sk-spinner").remove();	
            	return false; 
            }
            bindImgError();
        }); 
}

</script>

<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
</body>
</html>