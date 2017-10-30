<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
%>
<html>
<head>
    <title>新西兰国际峰会报名申请</title>
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
    <link rel="stylesheet" href="<%=basePath%>css/school/weui.min.css?v=1.1.0">
    <link href="<%=basePath%>css/plugin/alertPopShow/alertPopShow.css?v=1" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/school/mui.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/school/jquery-weui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/school/activity.css?v=1.1.2">
</head>
<body>
<div class="banner">
    <img src="<%=basePath%>images/school/banner3.jpg?v=1.0.0" id="imgId"/>
</div>
<div id="main">
    <div class="main bg_ffffff color_333">
        <h2 class="main_h2 bg_073b92">新西兰-中国2017年首届未来青年领袖国际峰会</h2>
       <p class="act_title color_073b92">申请表</p>
        <form id="formSummitMeeting" onsubmit="return false;" >
       <input name="Type"  type="hidden" value="2" id="Type"/>
        <table border="0" cellspacing="0" cellpadding="0">
       
            <tr>
                <td class="posi-rela">
                    <input name="Name" placeholder="中文姓名 / Chinese Name" type="text" class="srk1 border_073b92" id="Name"/>
                </td>
            </tr>
              <tr>
                <td class="posi-rela">
                    <input name="StringParamI" placeholder="英文姓名  / English Name" type="text" class="srk1 border_073b92" id="StringParamI"/>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <select name="StringParamJ" class="srk1 border_073b92" id="StringParamJ">
                        <option selected="selected" value="女">女</option>
                        <option value="男">男</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <input name="IntParamB" placeholder="年龄 / Age" type="number" class="srk1 border_073b92" id="IntParamB"
                           oninput="if(value.length>2)value=value.slice(0,2)"/>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <input name="StringParamA" placeholder="学校名称 / School" type="text" class="srk1 border_073b92" id="StringParamA"/>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <input name="StringParamB" placeholder="班级  / Grade(例3年级2班)" type="text" class="srk1 border_073b92" id="StringParamB"/>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <input name="StringParamC" placeholder="城市 / City(例浙江省杭州市江干区)" type="text" class="srk1 border_073b92" id="StringParamC"/>
                </td>
            </tr>

            <tr>
                <td class="posi-rela"><input name="Teacher" placeholder="父母/监护人 | Parent/Guardian names" type="text" class="srk1 border_073b92" id="Teacher"/>
                </td>
            </tr>
            <tr>
                <td class="posi-rela"><input name="Phone" placeholder="联系方式 / Phone" type="text" class="srk1 border_073b92" id="Phone"/>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <p class="tarea_title color_073b92">下述问题请用英文书写：</p>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <textarea class="tarea border_073b92" id="StringParamD" name="StringParamD" placeholder="请输入自我介绍 / Self-introduction" rows="3" ></textarea>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <textarea class="tarea border_073b92" id="StringParamE" name="StringParamE" placeholder="请输入兴趣爱好和特长  / Interests & Specialties" rows="3" ></textarea>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <p class="tarea_com_qus">下述问题任选一个回答：</p>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <p class="tarea_com">您认为作为未来青年领袖的基本素质有哪些？What qualities should a youth leader have?</p>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <textarea class="tarea border_073b92" id="StringParamF" name="StringParamF" placeholder="请输入问题答案" rows="10" ></textarea>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <p class="tarea_com">谈谈您对新西兰的认识？Talk about your understanding of New Zealand?</p>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <textarea class="tarea border_073b92" id="StringParamG" name="StringParamG" placeholder="请输入问题答案" rows="10" ></textarea>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <p class="tarea_com">谈谈您对未来国际关系的认识？Talk about your understanding of future global relationship?</p>
                </td>
            </tr>
            <tr>
                <td class="posi-rela">
                    <textarea class="tarea border_073b92" id="StringParamH" name="StringParamH" placeholder="请输入问题答案" rows="10" ></textarea>
                </td>
            </tr>
            
            <tr class="posi-rela">
                <td><input class="an bg_073b92"  type="submit" name="toastBtn" id="toastBtn" value="提交"/></td>
            </tr>
        </table>
        </form>
    </div>
</div>
<jsp:include page="/include/commonMobileJs.jsp" />
<script type="text/javascript">
	var Error='${Error}';
	if(Error!=''){
		webToast(Error,"bottom", 2000);
	}
	var Success='${Success}';
	if(Success!=''){
		webToast(Success,"bottom", 2000);
		 setTimeout(function(){
			 window.top.location ="<%=basePath%>weChatGroup/phoneWeNewsHome";
		 },3000);
	}
	
    //验证手机号
    var checkPhone = function (a) {
        var patrn = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
        if (!patrn.exec(a)) return false;
        return true;
    };

    //清除input内容
    $('.input-close').click(function (e) {
        $(e.target).parent().find(":input").val("");
        $(e.target).hide();
    });
    //监控用户输入
    $(":input").bind('input propertychange', function () {
        if ($(this).val() != "") {
            $(this).siblings(".input-close").show();
        } else {
            $(this).siblings(".input-close").hide();
        }
    });
    $(document).ready(function () {
        if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        } else if (/(Android)/i.test(navigator.userAgent)) {
        } else {
            $("#imgId").attr('src', "<%=basePath%>images/school/banner3.jpg");
        };

        $("#toastBtn").on("click", function () {
            var str_name = $("#Name").val();
            var str_age = $("#StringParamJ").val();
            var str_school = $("#StringParamA").val();
            var str_class = $("#StringParamB").val();
            var str_city = $("#StringParamC").val();
            var str_parent = $("#Teacher").val();
            var str_phone = $("#Phone").val();
            var str_intro = $("#StringParamD").val();
            var str_hobby = $("#StringParamE").val();
            var str_quest_sz = $("#StringParamF").val();
            var str_quest_rs = $("#StringParamG").val();
            var str_quest_gj = $("#StringParamH").val();
//            var str_inputfile_span = $(".inputfile_span").html();
//            if (str_name.length != 0 || str_age.length != 0 || str_school.length != 0|| str_class.length != 0|| str_city.length != 0|| str_parent.length != 0|| str_phone.length != 0) {
                if (str_name.length == 0) {
                    mui.toast('请输入中文姓名');
                } else if (str_age.length == 0) {
                    mui.toast('请输入年龄');
                } else if (str_school.length == 0) {
                    mui.toast('请输入学校名称');
                } else if (str_class.length == 0) {
                    mui.toast('请输入班级');
                } else if (str_city.length == 0) {
                    mui.toast('请输入城市');
                } else if (str_parent.length == 0) {
                    mui.toast('请输入父母姓名');
                } else if (!(checkPhone(str_phone) && str_phone.length == 11)) {
                    mui.toast('请输入联系方式');
                } else if (str_intro.length == 0) {
                    mui.toast('请输入自我介绍');
                } else if (str_hobby.length == 0) {
                    mui.toast('请输入兴趣爱好和特长');
                } else  if (!(str_quest_sz.length != 0 || str_quest_rs.length  != 0 || str_quest_gj.length  != 0)) {
                    mui.toast('请任选一题进行回答：');
                } else {
                	$.ajax({
                        type: "POST",
                        url:"<%=basePath%>Competition/submitCompetition",
                        data:$('#formSummitMeeting').serialize(),// 你的formid
                        async: false,
                        error: function(request) {
                        	mui.toast("报名失败,请稍后重试");
                        },
                        success: function(data) {
                        	if(data.status==1){
                        		mui.toast(data.message);
                        		 setTimeout(function(){
                        			 window.top.location ="<%=basePath%>Competition/phoneSummitMeetingDetails";
                        		 },3000);
                        	}else{
                        		mui.toast(data.message);
                        	}
                        }
                    });
                }
				
            
            $(".input-close").hide();
            return false;
        });
    });
</script>
</body>
</html>
