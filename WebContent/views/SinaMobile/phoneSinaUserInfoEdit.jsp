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
	int sex=0;
	if(user.getSex()!=null){
		sex=Integer.parseInt(user.getSex());
	}
	request.setAttribute("sex", sex);
	request.setAttribute("imageurl", SmBaseUtil.getUserImageUrl(user, request));
%>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>新浪小编个人信息中心</title>
<link href="<%=basePath%>css/plugin/mobiscroll/mobiscroll.2.13.2.css?v=3.3.7" rel="stylesheet"/>
<link href="<%=basePath%>css/plugins/LArea/LArea.min.css?v=3.3.7" rel="stylesheet">
<link href="<%=basePath%>css/plugins/cropper/cropper.min.css?v=3.3.7" rel="stylesheet" type="text/css" >
<jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <style type="text/css">
    #loading {
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/wsload.gif) no-repeat center center #FFF;
            background-size: 10%;
        }
        input {
            width: 100%;
            box-sizing: border-box;
            border: 0px solid #ccc;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -webkit-border-radius: 3px;
            height: 39px;
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
            color: #c3c5c8;
            text-align: right;
            margin-right: 25px;
            float: right;
            font-size:15px;
        }
        .lyy_year::-webkit-input-placeholder {
            color: #c3c5c8;
        }
        .lyy_year:-moz-placeholder {
            color: #c3c5c8;
        }
        .lyy_year::-moz-placeholder {
            color: #c3c5c8;
        }
        .lyy_year:-ms-input-placeholder {
            color: red;
        }
        select {
            width: 100%;
            padding: 5px;
            margin: 5px 0 10px;
            box-sizing: border-box;
            background: #f5f5f5;
            border: 1px solid #ccc;
            border-radius: 3px;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -webkit-border-radius: 3px;
            color: #666;
        }

        option {
            color: #666;
        }
        .confirm_input_pc_name{width:100%;text-align:left;margin-right: 0px;}
        .confirm_input_pc_tel{width:100%;text-align:left;margin-right: 0px;}
        .confirm_input_pc_head{width:100%;text-align:left;margin-right: 0px;}
        .upload_picture_people_center2{margin: 0;}
        .upload_showEdit{display: none;width:100%;height: 100%;position: absolute;top:0;left: 0;z-index: 9;}
        .upload_showEditDiv{width:100%;position: absolute;bottom:10px;left:0px;}
        .mui-btn-primary, .mui-btn[data-mui-color=primary]{background-color:#68ccb3;border-color:#68ccb3;}
        .modal-backdrop.in{opacity: 1;}
        .modal-backdrop{background-color: rgba(255,255,255,0);}
    </style>
</head>
<body>
<form action="<%=SmBaseGlobal.PictureService%>" target="i_frame"
			 id="registerForm" method="post"
			enctype="multipart/form-data">
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>"/>
<input type="hidden" name="UserID" id="UserID" value="<%=user!=null?user.getPKID():0 %>"/>
<input type="hidden" name="StudentSex" id="StudentSex" value="${sex}"/>
<input type="hidden" name="StudentAge" id="StudentAge" value="<%=user.getAge() %>"/>
<input type="hidden" name="StudentName" id="StudentName" value="<%=user.getName()%>"/>
<input type="hidden" name="ImageID" id="ImageID" value="<%=user.getImageID()%>"/>
<input type="hidden" name="Img_X" id="Img_X" />
<input type="hidden" name="Img_Y" id="Img_Y" />
<input type="hidden" name="Img_W" id="Img_W" />
<input type="hidden" name="Img_H" id="Img_H" />
<input type="hidden" name="Img_SrcW" id="Img_SrcW" />
<input type="hidden" name="Img_SrcH" id="Img_SrcH" />
<input type="hidden" name="SaveType" id="SaveType" value="1" />
<input type="hidden" name="backUrl" id="backUrl" value="<%=basePath%>include/pictureback.html" />
<div class="vipcenter ">
    <ul class="vipul">
        <li id="">
            <a href="javascript:void(0)" style="height: 80px;line-height:50px;">
                <div class="lzz">头像</div>
                <div style="position:absolute;top: 18px;right: 25px;" id="showResult">
    <img src="${imageurl}" id="changeAvatar" style=" width: 45px; height: 45px; border-radius: 100%;">
    <label title="上传图片" for="upload_picture" class="btn  upload_picture_people_center2" style="position:absolute;top: 5px;padding: 20px;right: 0;">
        <input type="file" name="file" id="upload_picture" class="hide" accept="image/*" capture="camera">
    </label>
</div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
            <a href="#" id="pc_name_value">
                <div class="lzz">姓名</div>
                <div class="lyy colorc3c5c8" id="pc_name"><%=user.getName() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
            <a href="#" id="pc_sex_value">
                <div class="lzz">性别</div>
                <div class="lyy colorc3c5c8" id="pc_sex" >${(sex==1)?"男":((sex==0)?"":"女")}</div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
            <a href="#">
                <div class="lzz">出生年月</div>
                <div class="lyy" style="top: 5px;right: 0">
                    <input id="demo3" placeholder="请选择出生年月" onchange="changeage(this)" value="<%=(user.getAge()==null || user.getAge().equals("null"))?"":user.getAge().substring(0,10) %>" />
                </div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>

        <li>
            <a href="<%=basePath%>Students/phoneUpdatePhone?sina=<%=SmBaseUtil.Random()%>">
                <div class="lzz">绑定手机号</div>
                <div class="lyy colorc3c5c8" id="phone_num"><%=user.getPhone() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
        	 <a href="<%=basePath%>Region/phoneBindRegion?type=1&_area_=<%=user.getAreaID()%>&sina=<%=SmBaseUtil.Random()%>">
                <div class="lzz">所在学校</div>
                <div class="lyy colorc3c5c8" id="pc_name"><%=user.getSchool() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
            <a href="#">
                <div class="lzz">监护人姓名</div>
                <div class="lyy colorc3c5c8"  id="pc_parent"><%=(user.getParentName()==null || user.getParentName().equals("null"))?"":user.getParentName() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
            <a href="#">
                <div class="lzz">班级</div>
                <div class="lyy colorc3c5c8"  id="pc_class"><%=(user.getGrade()==null || user.getGrade().equals("null"))?"":user.getGrade() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li >
            <a href="#">
                <div class="lzz">邮箱</div>
                <div class="lyy colorc3c5c8"  id="pc_email"><%=(user.getEmail()==null || user.getEmail().equals("null"))?"":user.getEmail() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
        <li data-toggle="modal" data-target="#myModal2">
            <a href="#">
                <div class="lzz">爱好</div>
                <div class="lyy colorc3c5c8"  id="pc_hobby"><%=(user.getHabit()==null || user.getHabit().equals("null"))?"":user.getHabit() %></div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>
		<li>
            <a href="<%=basePath%>Region/phoneBindWeChatUser?openid=<%=user.getOpenID() %>&uid=<%=user.getPKID() %>" style="height: 80px;line-height:50px;">
                <div class="lzz">绑定微信</div>
                <div class="lyy colorc3c5c8"  >
                <c:forEach var="imageUrl" items="${WeChatImageUrl}">
                	<img src="${imageUrl}"  style=" width: 45px; height: 45px; border-radius: 100%;">
                </c:forEach>
                </div>
                <div class="rizi"><i class="fa fa-angle-right fa-lg"></i></div>
            </a>
        </li>

    </ul>
    <button id="backBtn" type="submit" onclick="window.location='<%=basePath%>/Students/phoneSinaIndex?sina=<%=SmBaseUtil.Random() %>'" style="color: #fff;font-size: 20px;background-color: #68ccb3;width: 90%;margin: 4%;padding: 1px; border-radius: 5px;">返 回</button>
</div>

<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" >
    <div class="pl_div">
        <div class="pl_divt">
            <a class="pl_cancel pl_qx" href="javascript:;" >取消</a>
            <a id="postTo" href="javascript:;" class="pl_qd">确定</a>
        </div>
        <form>
            <textarea class="pl_hobby" placeholder="placeholder="请输入爱好，不能超过20个字" maxlength="20"><%=(user.getHabit()==null || user.getHabit().equals("null"))?"":user.getHabit() %></textarea>
        </form>
    </div>
</div>

<div id="showEdit" class="upload_showEdit">
    <div class="upload_showEditDiv">
        <button class="mui-btn upload_showEditCancle" data-mui-style="fab" id='cancleBtn'
                content="upload_showEditCancle">取消
        </button>
        <button class="mui-btn upload_showEditComfirm" data-mui-style="fab" data-mui-color="primary" id='confirmBtn'
                content="upload_showEditComfirm">确定
        </button>
    </div>
    <div id="report"></div>
</div>
<br/>
<br/>
<footer style="margin:10px 0;position: absolute;bottom: -140px;width: 100%">
    <div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
</form>
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript">
$('#postTo').on('mousedown',function(){
	var objarea=$('#myModal2').find('textarea');
    var areaVal = objarea.val();
    var areaLeng = objarea.val().length;
    if(areaVal == ''){
        webToast("爱好不能为空","top", 2000);
    }else if(areaLeng >= 21){
        webToast("输入字数不能超过20个字","top", 2000);
    }
    else{
        $('#myModal2').modal('hide');
        $('#pc_hobby').html(areaVal);
        updateStudent();
    }

});
$('.pl_cancel').on('mousedown',function(){
    $('#myModal2').modal('hide')
});
	$('.account_check-on').click(function () {
        $(this).toggleClass('account_check-off');
    });
    //用户个人中心的弹出框new Date(<%=user.getAge()%>)
    $(function () {
    	var strTime="2000-01-01"; //字符串日期格式           
		var date= new Date(Date.parse(strTime.replace(/-/g,   "/"))); 
        var opt = {
            'default': {
                theme: 'default',
                mode: 'scroller',
                display: 'modal',
                animate: 'fade'
                
            },
            'dateYMD': {
                preset: 'date',
                dateFormat: 'yyyy-mm-dd',
                defaultValue: new Date(date)
                }
            };
        $('#demo3').scroller($.extend(opt['dateYMD'], opt['default']));
       
        $('#pc_name_value').on('click', function(){
        	 var html = "<label style='width:100%;'><input class='confirm_input' id='name' maxlength='16' value='<%=user.getName() %>' style='margin-right:0;text-align: left;' placeholder='请输入'></label>";
            popTipShow.confirm('姓名',html,['确 定','取 消'],
                    function(e){
                        //callback 处理按钮事件
                        var button = $(e.target).attr('class');
                        if(button == 'ok'){
                            if(null==$("#name").val() || ""==$("#name").val()){
                                webToast("姓名不能为空！","bottom", 3000);
                                return;
                            }
                            this.hide();
                            $('#pc_name').html($("#name").val());
                            $('#StudentName').val($("#name").val());
                            updateStudent();
                        }
                        if(button == 'cancel') {
                            this.hide();
                        }
                    }
            );
        });
        $('#pc_sex_value').on('click', function(){
            var html = "<select class='confirm_input'>" +
                    "<option value='1' <%=(sex==1)?"selected":"" %>>男生</option>" +
                    "<option value='2' <%=!(sex==1)?"selected":"" %>>女生</option>" +
                    "</select>";
            popTipShow.confirm('选择性别',html,['确 定','取 消'],
                    function(e){
                        //callback 处理按钮事件
                        var button = $(e.target).attr('class');
                        if(button == 'ok'){
                            if(null==$(".confirm_input").val() || ""==$(".confirm_input").val()){
                                webToast("性别不能为空！","bottom", 3000);
                                return;
                            }
                            this.hide();
                            if($(".confirm_input").val()==1){
                            	$('#pc_sex').html("男");
                            }else{
                            	$('#pc_sex').html("女");
                            }
                            $('#StudentSex').val($(".confirm_input").val());
                            updateStudent();
                        }
                        if(button == 'cancel') {
                            this.hide();
                        }
                    }
            );
        });
        
      //填写父母的名字
        $('#pc_parent').on('click', function(){
            var html = "<label style='width:100%;'><input class='confirm_input' value='<%=(user.getParentName()==null || user.getParentName().equals("null"))?"":user.getParentName() %>' style='margin-right:0;text-align: left;' placeholder='请输入监护人姓名'></label>";
            popTipShow.confirm('监护人姓名',html,['确 定','取 消'],
                    function(e){
                        var button = $(e.target).attr('class');
                        if(button == 'ok'){
                            if(null==$(".confirm_input").val() || ""==$(".confirm_input").val()){
                                webToast("监护人姓名不能为空！","bottom", 3000);
                                return;
                            }
                            this.hide();
                            $("#pc_parent").text($(".confirm_input").val());
                            updateStudent();
                        }

                        if(button == 'cancel') {
                            this.hide();
                            /*      setTimeout(function() {
                             webToast("您选择“取消”了","top", 2000);
                             }, 300);*/
                        }
                    }
            );
        });
        //填写班级的名称
        $('#pc_class').on('click', function(){
            var html = "<label style='width:100%;'><input class='confirm_input'  type='number' value='<%=(user.getGrade()==null || user.getGrade().equals("null"))?"":user.getGrade()%>' style='margin-right:0;text-align: left;' placeholder='请输入班级，如506'></label>";
            popTipShow.confirm('班级',html,['确 定','取 消'],
                    function(e){
                        var button = $(e.target).attr('class');
                        if(button == 'ok'){
                            if(null==$(".confirm_input").val() || ""==$(".confirm_input").val()){
                                webToast("班级不能为空！","bottom", 3000);
                                return;
                            }
                            this.hide();
                            $("#pc_class").text($(".confirm_input").val());
                            updateStudent();
                        }

                        if(button == 'cancel') {
                            this.hide();
                            /*      setTimeout(function() {
                             webToast("您选择“取消”了","top", 2000);
                             }, 300);*/
                        }
                    }
            );
        });
        //填写邮箱的名称
        $('#pc_email').on('click', function(){
            var html = "<label style='width:100%;'><input class='confirm_input' value='<%=(user.getEmail()==null || user.getEmail().equals("null"))?"":user.getEmail() %>' style='margin-right:0;text-align: left;' placeholder='通知将会通过这个邮箱发送给您'></label>";
            popTipShow.confirm('邮箱',html,['确 定','取 消'],
                    function(e){
                        var button = $(e.target).attr('class');
                        if(button == 'ok'){
                            if(null==$(".confirm_input").val() || ""==$(".confirm_input").val()){
                                webToast("邮箱不能为空！","bottom", 3000);
                                return;
                            }
                            
                            var email=$(".confirm_input").val();
                            if(isEmail(email)){
                            	this.hide();
                            	$("#pc_email").text(email);
                                updateStudent();
                            }else{
                            	webToast("请输入正确的邮箱!","bottom", 2000);
                            }
                            
                        }

                        if(button == 'cancel') {
                            this.hide();
                            /*      setTimeout(function() {
                             webToast("您选择“取消”了","top", 2000);
                             }, 300);*/
                        }
                    }
            );
        });
        
    });
    function changeage(obj){
    	$('#StudentAge').val(obj.value);
    	updateStudent();
    }
    function updateStudent(){
    	$("#loading").css("display","block");
    	$.ajax({
    		url : "${path}/Students/phoneSinaUserInfoEdit?sina=<%=SmBaseUtil.Random()%>",
    		type:"POST",
    		data : {
    			'StudentAge' : $("#StudentAge").val(),
    			'StudentSex' : $("#StudentSex").val(),
    			'StudentName' : $("#StudentName").val(),
    			'ImageID' : $("#ImageID").val(),
    			'StudentParentName' : $("#pc_parent").text(),
    			'StudentHabit' : $('#myModal2').find('textarea').val(),
    			'StudentEmail' : $("#pc_email").text(),
    			'StudentGrade' : $("#pc_class").text()
    		},
    		success : function(json) {
    			if(json.Status=="true"){
    				 webToast("保存成功！","bottom", 2000);
    				 $("#loading").css("display","none");
    			}else{
    				webToast(json.Message,"bottom", 2000);
    				$("#loading").css("display","none");
    			}
    		}
    	});
    	
    }
    function cutImg() {
        $('#showResult').fadeOut();
        $("#showEdit").fadeIn();
        var $image = $('#report > img');
        $image.cropper({
            aspectRatio: 1 / 1,
            autoCropArea: 0.7,
            strict: true,
            guides: false,
            center: true,
            highlight: false,
            dragCrop: false,
            cropBoxMovable: false,
            cropBoxResizable: false,
            zoom: -0.2,
            checkImageOrigin: true,
            background: false,
            minContainerHeight: 300,
            minContainerWidth: 300
        });
    }
    function toFixed2(num) {
        return parseFloat(+num.toFixed(2));
    }
    function doFinish(startTimestamp, sSize, rst) {
        var finishTimestamp = (new Date()).valueOf();
        var elapsedTime = (finishTimestamp - startTimestamp);
        var sourceSize = toFixed2(sSize / 1024),
                resultSize = toFixed2(rst.base64Len / 1024),
                scale = parseInt(100 - (resultSize / sourceSize * 100));
        $("#report").html('<img src="' + rst.base64 + '" style="width: 100%;height:100%">');
        cutImg();
    }
    $('#cancleBtn').on('click', function () {
        $("#showEdit").fadeOut();
        $('#showResult').fadeIn();
        return false;
    });
    $('#confirmBtn').on('click', function () {
        $("#showEdit").fadeOut();
        var $image = $('#report > img');
        var dataURL = $image.cropper("getCroppedCanvas");
		var imgurl = dataURL.toDataURL("image/jpeg", 0.5);
		$("#changeAvatar").attr("src", imgurl);
        $("#ImageID").val(0);
		$("#Img_X").val($image.cropper("getData").x);
		$("#Img_Y").val($image.cropper("getData").y);
		$("#Img_W").val($image.cropper("getData").width);
		$("#Img_H").val($image.cropper("getData").height);
		$("#Img_SrcW").val($image.cropper("getImageData").naturalWidth);
		$("#Img_SrcH").val($image.cropper("getImageData").naturalHeight);
        alert($image.cropper("getData").x+","+$image.cropper("getData").y+","+$image.cropper("getData").width+","+$image.cropper("getData").height+","+$image.cropper("getImageData").naturalWidth+","+$image.cropper("getImageData").naturalHeight)
        $('#showResult').fadeIn();
        $("#loading").css("display","block");
        $("#registerForm").submit();
    });
    $('#upload_picture').on('change', function () {
        var startTimestamp = (new Date()).valueOf();
        var that = this;
        lrz(this.files[0], {
            
            quality: 0.7
        }).then(function (rst) {
                    //console.log(rst);
                    doFinish(startTimestamp, that.files[0].size, rst);
                    return rst;
                })
                .then(function (rst) {
                    // 这里该上传给后端啦
                    // 伪代码：ajax(rst.base64)..
                    return rst;
                })
                .then(function (rst) {
                    // 如果您需要，一直then下去都行
                    // 因为是Promise对象，可以很方便组织代码 \(^o^)/~
                })
                
                .always(function () {
                    // 不管是成功失败，这里都会执行
                });

    });

	function getIframeVal(val)  
	 {  
		 var json=eval("("+decodeURIComponent(val[0])+")");
		 if(json.Status>0){
			 $("#ImageID").val(json.Status);
			 updateStudent();
		 }else{
			 webToast(json.ErrorMsg, "bottom", 2000);
		 }
		 
	 }  
	function isEmail(str){ 
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
		return reg.test(str); 
		} 
	
</script>
<div id="loading"></div>
	<div id="i_frame_div"><IFrame id="i_frame"  name="i_frame" width="1px" height="1px" style="display:none" src="about:blank"></IFrame></div>
</body>
</html>