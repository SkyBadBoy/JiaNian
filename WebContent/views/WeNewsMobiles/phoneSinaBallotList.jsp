<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
%>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/style.min.css?v=4.0.1" rel="stylesheet">
    <title>微新闻大赛百强榜</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        body {
            font: 12px "宋体", "Arial Narrow", PingFangSC-Regular;
            background: #fff;
            -webkit-text-size-adjust: 100%;
        }

        a {
            color: #2d374b;
            text-decoration: none
        }

        a:hover {
            color: #cd0200;
            text-decoration: underline
        }

        .searchForm {
            width: 100%;
            height: 48px;
        }

        .searchBtn button, .searchTxt .searchMenu .searchSelected {
            background: url(<%=basePath%>images/searchbg.png) no-repeat;
        }

        .searchTxt {
            float: left;
            padding: 2% 3%;
            width: 94%;
            height: 28px;
            border-right: 0;
            position: relative;
            z-index: 20;
            background: #FBFBFB;
        }

        .searchTxt .searchMenu {
            float: left;
            width: 20%;
            margin-left: 8%;
        }

        .searchTxt .searchMenu .searchSelected {
            color: #a8a8a8;
            cursor: pointer;
            font-size: 14px;
            font-weight: normal;
            height: 30px;
            line-height: 30px;
            
            background-position: 0px -53px;
        }

        .searchTxt .searchMenu .searchOpen {
            background-position: 0px -105px;
        }

        .searchTxt .searchMenu .searchTab {
            border-radius: 4px;
            display: none;
            position: absolute;
            top: 35px;
            right: 10px;
            width: 22%;;
            background: #fff;
            z-index: 20;
        }

        .searchTxt .searchMenu .searchTab li {
            width: 58px;
            height: 25px;
            line-height: 28px;
            color: #a8a8a8;
            font-size: 14px;
            font-weight: normal;
            text-indent: 10px;
            cursor: pointer;
        }

        .searchTxt input {
            float: left;
            border: 0;
            border-radius: 5px;
            background: #F2F2F2;
            color: #9F9FA1;
            font: 14px/22px '宋体', verdana, tahoma, arial, 'SimSun', sans-serif;
            padding: 0 8%;
            width: 56%;
            height: 28px;
            margin: 0;
            outline: medium none;
        }

        ::-webkit-input-placeholder {
            /* WebKit browsers */
            color: #9F9FA1;
        }

        :-moz-placeholder {
            /* Mozilla Firefox 4 to 18 */
            color: #9F9FA1;
            opacity: 1;
        }

        ::-moz-placeholder {
            /* Mozilla Firefox 19+ */
            color: #9F9FA1;
            opacity: 1;
        }

        :-ms-input-placeholder {
            /* Internet Explorer 10+ */
            color: #9F9FA1;
        }

        .searchTxt .clear {
            display: none;
            position: absolute;
            right: 30%;
            top: 11px;
            content: "";
            width: 20px;
            height: 20px;
            background-image: url('<%=basePath%>images/del.svg');
            background-size: 20px 20px;
            cursor: pointer;
        }

        .searchTxt .search {
            position: absolute;
            left: 5%;
            top: 15px;
            width: 13px;
            height: 13px;
            background-image: url('<%=basePath%>images/search.png');
            background-size: 13px 13px;
            cursor: pointer;
        }

        .searchAdvertise {
            /*height: 30px;*/
            /*line-height: 30px;*/
            font-size: 12px;
            width: 100%;
            /*background-color: rgba(0, 0, 0, 0.6);*/
            position: relative;
            text-align: center;
            color: #D1D1D1;
        }

        .searchAdvertise-img {
            position: absolute;
            right: 5%;
            top: 9px;
            width: 13px;
            height: 13px;
            background-image: url('<%=basePath%>images/news.png');
            background-size: 13px 13px;
            cursor: pointer;
        }
 .searchAdvertise span img {
            position: absolute;
            right: 8%;
            top: 15px;
            width: 13px;
            height: 13px;
            background-image: url('<%=basePath%>images/news.png');
            background-size: 13px 13px;
            cursor: pointer;
        }
        .searchRank {
            width: 94%;
            padding: 3% 3% 0 3%;
            font-size: 13px;
        }

        .sRankPeopleAbcolor {
            -webkit-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            -moz-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            height: 126px;
            font-size: 14px;
            width: 100%;
            background-color: #FED055;
            text-align: center;
            color: #FFFFFF;
            border-radius: 4px;
        }

        .sRankPeopleBbcolor {
            -webkit-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            -moz-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            height: 126px;
            font-size: 14px;
            width: 100%;
            background-color: #BDC4CE;
            text-align: center;
            color: #FFFFFF;
            border-radius: 4px;
        }

        .sRankPeopleCbcolor {
            -webkit-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            -moz-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            height: 126px;
            font-size: 14px;
            width: 100%;
            background-color: #DDC4AC;
            text-align: center;
            color: #FFFFFF;
            border-radius: 4px;
        }

        .sRankPeopleDbcolor {
            -webkit-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            -moz-box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            box-shadow: 0 0 4px rgba(34, 17, 34, 0.4);
            height: 71px;
            font-size: 14px;
            width: 100%;
            background-color: #FFFFFF;
            text-align: center;
            color: #FFFFFF;
            border-radius: 4px;
        }

        .sRankPeopleO_div {
            height: 40px;
            width: 94%;
            padding: 2% 3% 0 3%;
            position: relative;
        }

        .sRankPeopleO_divt {
            height: 40px;
        }

        .sRankPeopleO_divtAcolor {
            border-bottom: 1px solid #FFE397;
        }

        .sRankPeopleO_divtBcolor {
            border-bottom: 1px solid #CDD3DC;
        }

        .sRankPeopleO_divtCcolor {
            border-bottom: 1px solid #E7D5C3;
        }

        .sRankPeopleO_divt img {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            float: left;
        }

        .sRankPeopleO_divt span {
            float: left;
            margin: 8px 0 0 15px
        }

        /*.sRankPeopleO_Div{height: 61px;width: 94%;padding:2% 3% 0 3%;position: relative;}*/
        .sRankPeopleO_divf {
            height: 71px;
            line-height: 45px;
        }

        .sRankPeopleO_divf img {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            float: left;
            margin: 18.5px 5px;
        }

        .sRankPeopleO_divf span {
            float: left;
            margin: 13px 5px;
            color: #636363;
        }

        .sRankPeopleO_divf span:first-child {
            font-size: 20px;
            width: 20px;
        }

        .sRankPeopleO_divf span:last-child {
            float: right;
            font-size: 30px;
            margin-right:15px;
        }

        .sRankPeopleT {
            color: #fff;
            height: 79px;
        }

        .sRankPeopleT_spanO {
            float: left;
            margin-left: 44px;
            font-size: 43px;
        }

        .sRankPeopleT_spanO_p {
            font-size: 12px;
            margin-top: 5px;
        }

        .sRankPeopleT_spanT {
            float: right;
            margin-right: 15px;
            font-size: 43px;
        }

        .sRankPeopleT_spanT_p {
            font-size: 12px;
            margin-top: 5px;
            float: right;
        }
         .search-imgresp{width:94%;height: auto;margin: 0 3% -1% 3%;border-radius:5px;}
        .searchAdvertise a{display: block;cursor: pointer;}
        .searchAdvertise-span{
            position: absolute;
            right: 5%;
            bottom:5px;
            border-radius:5px;
            width: 40px;
            height: 20px;
            line-height: 20px;
            background-color:rgba(241,247,252,0.3);
            cursor: pointer;
            color: #fefefe;
        }
        .searchP {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
        }
        .sRankPeopleO_divf span {
            margin: 13px 2px;
        }
        .sRankPeopleO_divf img {
            margin: 18.5px 4px;
        }
        .sRankPeopleO_divf span:first-child {
            font-size: 15px;
            margin: 13px 0 13px 5px;
            width: 25px;
        }
        .sRankPeopleO_divf span:last-child {
            font-size: 25px;
            margin: 13px 5px 13px 0;
        }
        /*4个汉字*/
        .width60{width: 60px;}
        /*6个汉字*/
        .width85{width: 85px;}
        @media screen and (min-width: 375px) {
            .sRankPeopleO_divf span:first-child{
                margin: 13px 6px;
            }
            .sRankPeopleO_divf span {
                margin: 13px 6px;
            }
            .sRankPeopleO_divf span:last-child {
                margin:13px 5px 13px 2px;
            }
            /*5个汉字*/
            .width60{width: 73px;}
            /*7个汉字*/
            .width85{width: 100px;}
        }
        @media screen and (min-width: 414px) {
            .sRankPeopleO_divf span:first-child{
                margin: 13px 8px;
            }
            .sRankPeopleO_divf span {
                margin: 13px 8px;
            }
            .sRankPeopleO_divf span:last-child {
                margin:13px 5px 13px 5px;
            }
            /*5个汉字*/
            .width60{width: 73px;}
            /*8个汉字*/
            .width85{width: 115px;}
        }
    </style>

</head>
<body>

    <div id="searchTxt" class="searchTxt" onkeydown="searchText(event)" onMouseOver="this.className='searchTxt ';"
         onMouseOut="this.className='searchTxt';">
        <i class="search" id="search"></i>
        <input name="content" id="content" type="text" placeholder="搜索学生姓名,回车搜索"/>
        <i class="clear" id="clear"></i>

        <div class="searchMenu">
            <div class="searchSelected" id="searchSelected">小学组</div>
            <div style="display:none;" class="searchTab" id="searchTab">
                <ul>
                    <li value="1">幼儿组</li>
                    <li value="2">小学组</li>
                    <li value="3">初中组</li>
                </ul>
            </div>
        </div>
    </div>

<div class="searchAdvertise">
 	<a href="#"> <img class="search-imgresp" src="<%=basePath%>images/advertising.jpg" ></a>
   
    <span class="searchAdvertise-span">广告</span>
</div>
<div style="
    text-align: center;
    padding-top: 10px;
    color: #A7A8A9;
    padding-bottom: -5px;
">投票截至9月15号24:00，获奖名单将在19号公布</div>
<div id="myDiv">
</div>
<div class="footer" >
		&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %>
	</div>
	  <br/>
<input type="hidden" value="0" id="page" >
<input type="hidden" value="2" id="type" >
<jsp:include page="/include/commonMobileJs.jsp" />
    <script type="text/javascript">
        $(document).ready(function () {
        	getdata(1);
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
                
                $("#type").val($(this).val());
                $("#page").val(0);
                getdata(1);
            });
            //浏览器视口的高度
            function getWindowHeight() {
                var windowHeight = 0;
                if (document.compatMode == "CSS1Compat") {
                    windowHeight = document.documentElement.clientHeight;
                } else {
                    windowHeight = document.body.clientHeight;
                }
                return windowHeight;
            }

            function getScrollTop() {
                var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
                if (document.body) {
                    bodyScrollTop = document.body.scrollTop;
                }
                if (document.documentElement) {
                    documentScrollTop = document.documentElement.scrollTop;
                }
                scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
                return scrollTop;
            }

//文档的总高度
            function getScrollHeight() {
                var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
                if (document.body) {
                    bodyScrollHeight = document.body.scrollHeight;
                }
                if (document.documentElement) {
                    documentScrollHeight = document.documentElement.scrollHeight;
                }
                scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
                return scrollHeight;
            }

            $(window).scroll(function () {
                if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
                	getdata(0);
                }
            });
        });
        
        function getdata(isreload){
        	 var htmlobj = $.parseJSON($.ajax({url: "http://www.whohelp.cc/WeNewsAgency/weChatGroup/getSinaBallotList?school_level="+ $("#type").val() +"&start_page="+ $("#page").val() +"&keywords="+$("#content").val(), async: false}).responseText);
             var tmp = '';
             htmlobj=eval(htmlobj['data']);
             if(htmlobj!=null){
            	 for (var i = 0; i < htmlobj.length; i++) {
            		 var click="onclick=window.location.href='http://www.whohelp.cc/WeChatAPI/directUrl.jsp?type=setp1&&aid="+ htmlobj[i].essay_id +"&api=snsapi_base'";
                     if ((htmlobj[i].ranking_num) == 1) {
                         tmp += '<div class="searchRank" '+ click +'>' +
                                 '<div class="sRankPeopleAbcolor" >' +
                                 '<div class="sRankPeopleO_div">' +
                                 '<div class="sRankPeopleO_divt sRankPeopleO_divtAcolor">' +
                                 '<img src="' + htmlobj[i].title_url + '" >' +
                                 '<span  style="">' + htmlobj[i].child_name + '</span>' +
                                 '<span   style="">' + htmlobj[i].school_name + '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '<div class="sRankPeopleT">' +
                                 '<span class="sRankPeopleT_spanO" style="">' +
                                 '<p >' + htmlobj[i].ranking_num + '</p>' +
                                 '<p class="sRankPeopleT_spanO_p">名次</p>' +
                                 '</span>' +
                                 '<span class="sRankPeopleT_spanT">' +
                                 '<p >' + htmlobj[i].like_count + '</p>' +
                                 '<p class="sRankPeopleT_spanT_p">票数</p>' +
                                 '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '</div>'
                     } else if ((htmlobj[i].ranking_num) == 2) {
                         tmp += '<div class="searchRank" '+ click +'>' +
                                 '<div class="sRankPeopleBbcolor" >' +
                                 '<div class="sRankPeopleO_div">' +
                                 '<div class="sRankPeopleO_divt sRankPeopleO_divtBcolor">' +
                                 '<img src="' + htmlobj[i].title_url + '">' +
                                 '<span  style="">' + htmlobj[i].child_name + '</span>' +
                                 '<span  style="">' + htmlobj[i].school_name + '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '<div class="sRankPeopleT">' +
                                 '<span class="sRankPeopleT_spanO" style="">' +
                                 '<p >' + htmlobj[i].ranking_num + '</p>' +
                                 '<p class="sRankPeopleT_spanO_p">名次</p>' +
                                 '</span>' +
                                 '<span class="sRankPeopleT_spanT">' +
                                 '<p >' + htmlobj[i].like_count + '</p>' +
                                 '<p class="sRankPeopleT_spanT_p">票数</p>' +
                                 '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '</div>'
                     } else if ((htmlobj[i].ranking_num) == 3) {
                         tmp += '<div class="searchRank" '+ click +'>' +
                                 '<div class="sRankPeopleCbcolor" >' +
                                 '<div class="sRankPeopleO_div">' +
                                 '<div class="sRankPeopleO_divt sRankPeopleO_divtCcolor">' +
                                 '<img src="' + htmlobj[i].title_url + '" >' +
                                 '<span  style="">' + htmlobj[i].child_name + '</span>' +
                                 '<span  style="">' + htmlobj[i].school_name + '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '<div class="sRankPeopleT">' +
                                 '<span class="sRankPeopleT_spanO" style="">' +
                                 '<p >' + htmlobj[i].ranking_num + '</p>' +
                                 '<p class="sRankPeopleT_spanO_p">名次</p>' +
                                 '</span>' +
                                 '<span class="sRankPeopleT_spanT">' +
                                 '<p >' + htmlobj[i].like_count + '</p>' +
                                 '<p class="sRankPeopleT_spanT_p">票数</p>' +
                                 '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '</div>'
                     } else {
                         tmp += '<div class="searchRank" '+ click +'>' +
                                 '<div class="sRankPeopleDbcolor">' +
                                 '<div class="sRankPeopleO_divf ">' +
                                 '<span style="">' + htmlobj[i].ranking_num + '</span>' +
                                 '<img src="' + htmlobj[i].title_url + '" >' +
                                 '<span class="searchP width60" style="">' + htmlobj[i].child_name + '</span>' +
                                 '<span class="searchP width85" style="">' + htmlobj[i].school_name + '</span>' +
                                 '<span style="">' + htmlobj[i].like_count + '</span>' +
                                 '</div>' +
                                 '</div>' +
                                 '</div>'
                     }
                 }
            	 if(isreload==1){
                	 $("#myDiv").html(tmp);
                 }else{
                	 $("#myDiv").append(tmp);
                 }
                 
                 if(htmlobj.length<=0){
              		$(".sk-spinner").remove();
              		if(isreload==1){
                   	 $("#myDiv").html("");
                    }
                  	AddNoMoreDOM($("#myDiv"));
                  }else{
                      $(".sk-spinner").remove();
                      if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
                    	  AddNoMoreDOM($("#myDiv"));
                      }else{
                    	  
                    	  $("#myDiv").append(getLoading()); 
                      }
                      
                     
                  }
                 var page=$("#page").val();
                 $("#page").val(parseInt(page)+1);
             }else{
            	 $(".sk-spinner").remove();
           		if(isreload==1){
                	 $("#myDiv").html("");
                 }
               	AddNoMoreDOM($("#myDiv"));
             }
             
             
             
        }
        function searchText(e){
        	e = event ? event :(window.event ? window.event : null); 
        	if(e.keyCode == 13){ 
        	//执行的方法 
        		$("#page").val(0);
            	getdata(1);
        	}
        	
        }
    </script>
</body>
</html>

