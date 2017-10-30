<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user = new Students();
	}
	int sex=0;
	if(user.getSex()!=null){
		sex=Integer.parseInt(user.getSex());
	}
%>
<html lang="en">
<head>
    <title>用户信息</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<link href="<%=basePath %>css/wenewspc/plugins/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/wenewspc/plugins/cropper.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/wenewspc/plugins/main.css" rel="stylesheet">
    <link href="<%=basePath %>css/wenewspc/plugins/lhgcalendar.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/wenewspc/plugins/autocomplete.css">
    <link rel="stylesheet" href="<%=basePath %>css/wenewspc/plugins/formtest.css">
    <link href="<%=basePath %>css/wenewspc/index.css" rel="stylesheet">
</head>
<body>
<div id="i_frame_div"><IFrame id="i_frame"  name="i_frame" width="1px" height="1px" style="display:none" src="about:blank"></IFrame></div>
<jsp:include page="/include/wenewspc/commonNav.jsp"/>
<div class="container center_block">
    <!-- 表单验证-->
    <div class="dochange center_block">
        <p class="text_center change_title">资料完善</p>

        
            <div class="center_block size_110" id="crop-avatar">
                <div class="size_110 avatar-view" title="修改头像">
                    <img src="${ImageUrl }" alt="Avatar" id="image">
                </div>
                <div  class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label"
                     role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="avatar-form">
                            <form id="form" class="avatar-form" action="<%=SmBaseGlobal.PictureService%>" target="i_frame" method="post" enctype="multipart/form-data" onsubmit="return saveReport();">
								<input type="hidden" id="Type" name="Type" value="1">
								<input type="hidden" name="Img_X" id="Img_X" />
								<input type="hidden" name="Img_Y" id="Img_Y" />
								<input type="hidden" name="Img_W" id="Img_W" />
								<input type="hidden" name="Img_H" id="Img_H" />
								<input type="hidden" name="Img_SrcW" id="Img_SrcW" />
								<input type="hidden" name="Img_SrcH" id="Img_SrcH" />
								<input type="hidden" name="SaveType" id="SaveType" value="1" />
								<input type="hidden" name="ImageID" id="ImageID" value="<%=user.getImageID()%>"/>
								<input type="hidden" name="backUrl" id="backUrl" value="<%=basePath%>include/pictureback.html" />
                                <div class="modal-header">
                                    <button class="close" data-dismiss="modal" type="button">&times;</button>
                                    <h4 class="modal-title" id="avatar-modal-label">修改头像</h4>
                                </div>
                                <div class="modal-body" >
                                    <div class="avatar-body">
                                        <div class="avatar-upload">
                                            <input class="avatar-src" name="avatar_src" type="hidden">
                                            <input class="avatar-data" name="avatar_data" type="hidden">

                                            <div class="file_up_style">
                                                <input class="avatar-input" id="file" name="file"
                                                       type="file">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-8">
                                                <div class="avatar-wrapper"></div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="avatar-preview preview-lg"></div>
                                                <div class="avatar-preview preview-md"></div>
                                                <div class="avatar-preview preview-sm"></div>
                                            </div>
                                        </div>

                                        <div class="row avatar-btns">
                                            <div class="col-md-9">
                                                <div class="btn-group">
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="-90" type="button" title="Rotate -90 degrees">
                                                        左翻转
                                                    </button>
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="-15" type="button">-15°
                                                    </button>
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="-30" type="button">-30°
                                                    </button>
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="-45" type="button">-45°
                                                    </button>
                                                </div>
                                                <div class="btn-group">
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="90" type="button" title="Rotate 90 degrees">右翻转
                                                    </button>
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="15" type="button">15°
                                                    </button>
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="30" type="button">30°
                                                    </button>
                                                    <button class="btn btn-primary" data-method="rotate"
                                                            data-option="45" type="button">45°
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <button class="btn btn-primary btn-block avatar-save" type="submit">确认
                                                </button>
                                            </div>
                                             </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear_float"></div>
            <p class="text_center student_list">头像</p>
            <hr>

            <div class="register">
                <!--用户名-->
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label for="userName">姓名<span class="red">*</span></label>
                        <input type="text" id="userName" placeholder="孩子姓名" value="${Name }"/>
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label >性别<span class="red">*</span></label>
                        	<input class="gender_input" type="radio"  name="gender" id="wowen" ><label>女</label>
                       		<input class="gender_input" type="radio" name="gender" id="man"><label>男</label>
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <!--生日-->
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label for="birth">生日</label>
                        <input type="text" id="birth" name="birth" placeholder="" value="${Age }">
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label for="mobile">手机号<span class="red">*</span></label>
                        <input type="text" id="mobile" value="${Phone }" placeholder="手机号码" disabled="disabled"/>
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label for="parentName">家长<span class="red">*</span></label>
                        <input type="text" id="parentName" value="${ParentName }" placeholder="家长姓名"/>
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label for="email">邮箱<span class="red">*</span></label>
                        <input type="text" id="email" value="${Email }" placeholder="如:example.qq.com"/>
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <hr>
                <div class="demo">
                
                
			<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
			<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" />
			<input type="hidden" name="CityAreaID" value="${CityAreaID}" id="CityAreaID" />
			<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
			
			    <div class="register-box">
			  	  <div class="box default">
                        <label for="email">省<span class="red">*</span></label>
                        	<select style=" width: 200px;font-size: 16px; padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px;height: 42px;"  id="Province"
						onchange="getCity(this);">
							</select>
                        <i></i>
                 </div>
                 </div>
                  <div class="register-box">
                   <div class="box default">
                        <label for="email">市<span class="red">*</span></label>
                        	<select id="City" style=" width: 200px;font-size: 16px; padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px;height: 42px;"  onchange="getAreaID(this)">
					</select>
							
                        <i></i>
                 </div>
                 </div>
                 <div class="register-box">
                    <div class="box default">
                        <label for="email">区<span class="red">*</span></label>
                        		<select style=" width: 200px;font-size: 16px; padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px;height: 42px;" id="AreaID"
						onchange="getUnitAreaID(this)">
					</select>
                        <i></i>
                 	</div>
                 </div>
                 <div class="register-box">
                 <div class="box default">
					   <label for="email">学校<span class="red">*</span></label>
                        		<select name="UnitAreaID" style=" width: 200px;font-size: 16px; padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px;height: 42px;" id="UnitAreaID">
					</select>
                        <i></i>
				</div>
			</div>
                </div>
                
                
                
                
                
                
                <div class="register-box">
                    <!--表单项-->
                    <div class="box default">
                        <label for="banji">班级<span class="red">*</span></label>
                        <input type="number" id="banji" value="${Grade }" placeholder="如：504"/>
                        <i></i>
                    </div>
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="register-box hidden">
                    <div class="box default">
                        <label for="pwd">设 置 密 码</label>
                        <input type="password" id="pwd" placeholder="建议至少两种字符组合"/>
                        <i></i>
                    </div>
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="register-box hidden">
                    <div class="box default">
                        <label for="pwd2">确 认 密 码</label>
                        <input type="password" id="pwd2" placeholder="请再次输入密码"/>
                        <i></i>
                    </div>
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <div class="input_style">
                    <label class="student_list">爱好</label><textarea id="Habit">${Habit}</textarea>
                </div>

                <!--注册协议-->
                <div class="register-box xieyi">
                    <!--表单项-->
                    <!--
	                    <div class="box default">
	                        <input type="checkbox" id="ck" checked/>
	                        <span>我已阅读并同意<a href="#">《协议》</a></span>
	                    </div>
	                -->
                    <!--提示信息-->
                    <div class="tip">
                        <i></i>
                        <span></span>
                    </div>
                </div>
                <p class="help">（*号部分为必填选项）</p>
                <!--注册-->
                <button id="change_info_btn">保存</button>
            </div>


       
    </div>
</div>

<div id="returnTop"><img src="<%=basePath%>images/wenewspc/returntop.jpg"><p class="text_center">返回顶部</p></div>
<script src="<%=basePath%>js/wenewspc/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/wenewspc/plugins/lhgcore.min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/wenewspc/plugins/lhgcalendar.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/wenewspc/plugins/autocomplete.js"></script>
<script src="<%=basePath%>js/wechat_index.js?v=1.0.19"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>
<script type="text/javascript">
console.log($("#wowen").is(':checked'))
var sex='${Sex}';
if(sex==2){
	$("input[name='gender']:eq(0)").click()
}else{
	$("input[name='gender']:eq(1)").click()
}
  
	 getProvince();
	 if($("#ProvinceID").val()==""){
		 getCurrentlocation();
	 }
</script>
<script type="text/javascript">
    J(function () {
        J('#birth').calendar({format: 'yyyy-MM-dd'});
    });
</script>
<script src="<%=basePath%>js/wenewspc/plugins/bootstrap.min.js"></script>
<script src="<%=basePath%>js/wenewspc/plugins/cropper.min.js"></script>
<script src="<%=basePath%>js/wenewspc/plugins/main.js"></script>
<script src="<%=basePath%>js/wenewspc/plugins/city4.city.js" type="text/javascript"></script>
<script src="<%=basePath%>js/wenewspc/plugins/city4.js" type="text/javascript"></script>
<script src="<%=basePath%>js/wenewspc/index.js" type="text/javascript"></script>
<script src="<%=basePath%>js/wenewspc/jqfrom.js" type="text/javascript"></script>
<!--<script src="js/formcheck.js" type="text/javascript"></script>-->
<script type="text/javascript">
    /*动画（注册）*/
    $(document).ready(function(){
        $("#change_info_btn").on("click", function () {
            var str_userName = $("#userName").val();
            var str_birth = $("#birth").val();
            var str_mobile = $("#mobile").val();
            var str_parentName = $("#parentName").val();
            var str_email = $("#email").val();
            var str_banji = $("#banji").val();
            var str_provinces = $("#Provinces i").html();
            var str_citys = $("#Citys i").html();
            var str_areas = $("#Areas i").html();
            var str_schools = $("#Schools i").html();
            
            var AreaID=$("#UnitAreaID").val();
            if (str_userName.length != 0 ||str_mobile.length != 0 ||str_parentName.length != 0||str_banji.length != 0  ) {
                if(str_userName.length == 0 ) {
                    popup_msg("请输入孩子姓名！");
                }else if(str_birth == "请选择生日日期" ) {
                    popup_msg("请选择生日日期！");
                }else if (!(checkPhone(str_mobile)&&str_mobile.length == 11)) {
                    popup_msg("请输入正确的手机号码！");
                }else  if(str_parentName.length == 0 ) {
                    popup_msg("请输入家长姓名！");
                }else if (!(checkEmail(str_email))) {
                    popup_msg("请输入邮箱！");
                }else if(str_provinces == "请选择省份" ) {
                    popup_msg("请选择省份！");
                }else if(str_citys == "请选择城市" ) {
                    popup_msg("请选择城市！");
                }else if(str_areas == "请选择地区" ) {
                    popup_msg("请选择地区！");
                }else if(str_schools == "请选择学校" ) {
                    popup_msg("请选择学校！");
                }else  if(str_banji.length == 0 ) {
                    popup_msg("请输入正确班级！");
                }/*else if(!($('#ck').is(':checked'))) {
                    popup_msg("请勾选协议！");
                }*/else {
                	var sex=1;
                	if($("#wowen").is(':checked')){
                		sex=2;
                	}
            		$.getJSON("/WeNewsAgency/WeNews/UserEdits",
       				{
       					Name:str_userName,
       					Age:str_birth,
       					Sex:sex,
       					Phone:str_mobile,
       					ParentName:str_parentName,
       					Habit:$("#Habit").val(),
       					Email:str_email,
       					Grade:str_banji,
       					AreaID:AreaID,
       					ImageID:$("#ImageID").val(),
       					ImageUrl: $("#image").attr("src")
       					
       				},
       				function(json) {
       						console.log(json);
       						if(json.Status==true){
       							popup_msg("保存成功");
       							location.href = $("#basePath").val()+'WeNewsHome';
       						}else{
       							popup_msg("保存失败");
       						}
       				});
                	
                  
                    
                    
                    
                }
            } else {
                popup_msg("请填写资料信息！");
            }
            return false;
        });
    });
    function getIframeVal(val)  
	 {  
		 var json=eval("("+decodeURIComponent(val[0])+")");
		 if(json.Status>0){
			 $("#ImageID").val(json.Status);
			 $("#image").attr("src",json.ImageURL.image_800);
		 }else{
			 webToast(json.ErrorMsg, "bottom", 2000);
		 }
		 
	 }  
    function saveReport() { 
		$("#avatar-modal").modal("hide");
		var $image = $('.avatar-wrapper> img');
		var dataURL = $image.cropper("getCroppedCanvas");
		var imgurl = dataURL.toDataURL("image/jpeg", 0.5);
		$("#image").attr("src",imgurl);
		$("#Img_X").val($image.cropper("getData").x);
		$("#Img_Y").val($image.cropper("getData").y);
		$("#Img_W").val($image.cropper("getData").width);
		$("#Img_H").val($image.cropper("getData").height);
		$("#Img_SrcW").val($image.cropper("getImageData").naturalWidth);
		$("#Img_SrcH").val($image.cropper("getImageData").naturalHeight);
		$("#form").submit();
    	return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
    	} 
</script>
</body>
</html>
