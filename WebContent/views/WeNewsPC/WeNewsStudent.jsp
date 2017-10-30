<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String img= basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
	request.setAttribute("img", img);
%>
<html lang="en">
<head>
    <title>学生空间</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
	<body>
	<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
		<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
			<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" />
			<input type="hidden" name="CityAreaID" value="${CityAreaID}" id="CityAreaID" />
			<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
			<div class="student_block">
				<div class="search_block">
				  <select style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" id="Province"onchange="getCity(this,1);"></select>
                   <select  style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" id="City" onchange="getAreaID(this,1)"></select>
                  <select  style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" id="AreaID" onchange="getUnitAreaID(this,1)"></select>
                   <select  style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" name="UnitAreaID"  id="UnitAreaID"></select>
					<input style="width: 123px; height: 40px; background: #4C9ED9;color: white!important;border: none; border-radius: 8px;" class="search_btn" type="button" id="query" value="查询" />
				</div>
				<div class="student_list" id="student_list">
					<c:forEach var="data" items="${StudentData }">
						<div class="one_student">
							<div>
								<div class="student_img">
									<a href="<%=basePath %>WeNews/User?Uid=${data.PKID}"><img class="img_100" src="${data.imageUrl.split(",")[0] }"></a>
								</div>
								<div class="student_intr">
									<a class="Single_title_ellipsis" href="<%=basePath %>WeNews/User?Uid=${data.PKID}">${data.name }</a>
									<p>投稿（${data.noticeCount }）</p>
									<p>
										<c:if test="${data.school ne null}">
											<a class="Single_title_ellipsis" href="<%=basePath%>WeNews/SchoolInfo?SchoolID=${data.areaID}">
										</c:if>
										<c:if test="${data.school eq null}">
											<a class="Single_title_ellipsis" href="#">
										</c:if>
										${data.school eq null?"该同学还没有设置学校哦~~":data.school }</a>
									</p>
								</div>
								<div class="clear_float"></div>
								<hr>
								<ul>
									<li>
										<a href="">
											<p>${data.habit eq null?"该同学还没有留下脚印哦~~":data.habit }</p>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<input type="hidden" id="AreaID" value="0">
		<input type="hidden" id="_page_" value="2">
		<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
		<jsp:include page="/include/wenewspc/commonJs.jsp"/>
		<script src="<%=basePath%>js/wechat_index.js?v=1.0.19"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=ce9IbdXcl8h6dDRQ9kRWo08KOmDqvCTl"></script>
		<script type="text/javascript">
		 getProvince();
		 if($("#ProvinceID").val()==""){
			 getCurrentlocation();
		 }
	var i = 1; //设置当前页数 
	$(window).scroll(function() {		
		if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
			console.log($('#_page_').val());
			 $(".sk-spinner").remove();
			 $("#NotMore").remove(); 
			 //$("#student_list").append(getLoading());
			 $.getJSON("<%=basePath%>WeNews/getWeNewStudentList",
				{
				 pageNumber:$('#_page_').val(),
				 pageSize:10,
				 submitType:'phone',
				 AreaID:$("#UnitAreaID").val()
				 },function(json){ 
	                if(json && json.Status==1){ 
	                	var html="";	
	                    $.each(json.data,function(index,array){ 
		                    	var habit="该同学还没有留下脚印哦~~";
	                    		if(array.habit!=null && array.habit.length>0){
	                    			habit=array.habit; 
	                    		}
	                    		
	                    		var school=array.school;
	                    		if(array.school==null){
	                    			school="该同学还没有设置学校哦~~";
	                    		}
	                    		
	                    		var image=array.imageUrl;
	                    		if(image==null){
	                    			image='${img}'
	                    		}
	                    		
	                    		html+='<div class="one_student">'
	                    		html+='							<div>'
	                    		html+='						<div class="student_img">'
	                    		html+='					<a  href="<%=basePath %>WeNews/User?Uid='+array.pkid+'"><img class="img_100" src="'+array.imageUrl.split(",")[0]+'"></a>'
	                    		html+='								</div>'
	                    		html+='								<div class="student_intr">'
	                    		html+='									<a class="Single_title_ellipsis" href="<%=basePath %>WeNews/User?Uid='+array.pkid+'">'+array.name+'</a>'
	                    		html+='									<p>投稿（'+array.noticeCount +'）</p>'
	                    		html+='									<p>'
	                    		if(array.areaID){
	                    			html+='										<a class="Single_title_ellipsis" href="<%=basePath %>WeNews/SchoolInfo?SchoolID='+array.areaID+'">'+school +'</a>'
	                    		}else{
	                    			html+='										<a class="Single_title_ellipsis" href="#">'+school +'</a>'
	                    		}
	                    		
	                    		html+='									</p>'
	                    		html+='								</div>'
	                    		html+='								<div class="clear_float"></div>'
	                    		html+='								<hr>'
	                    		html+='								<ul>'
	                    		html+='									<li>'
	                    		html+='										<a href="">'
	                    		html+='											<p>'+habit+'</p>'
	                    		html+='										</a>'
	                    		html+='									</li>'
	                    		html+='								</ul>'
	                    		html+='							</div>'
	                    		html+='</div>';                   	
	                    	
	                    	
	                    }); 
	                    $(".sk-spinner").remove();
	                    $("#student_list").append(html); 
	                    if(json.data.length<=0){
							if($("#NotMore").length<=0){
								$("#student_list").append(getNoMoreInfo()); 
							}
	                    }else{
		                    var _page_=$('#_page_').val();
		                    $('#_page_').val(parseInt(_page_)+1);
		                    i++; 
	                    }
	                }else{ 
	                		$(".sk-spinner").remove();
							if($("#NotMore").length<=0){
								$("#student_list").append(getNoMoreInfo()); 
							}
	                	return false; 
	                }
	                bindImgError();
	            }); 
		}
	});




$("#query").click(function(){
	var AreaID=$("#UnitAreaID").val();
	console.log(AreaID);
	if(AreaID==null||AreaID==""){
		popup_msg("请选择地区");
	} 
	$('#_page_').val(1);
	 $.getJSON("<%=basePath%>WeNews/getWeNewStudentList",
				{
				 pageNumber:$('#_page_').val(),
				 pageSize:10,
				 submitType:'phone',
				 AreaID:AreaID
				 },function(json){ 
	                if(json && json.Status==1){ 
	                	var html="";	
	                    $.each(json.data,function(index,array){ 
	                    		var habit="该同学还没有留下脚印哦~~";
	                    		if(array.habit!=null && array.habit.length>0){
	                    			habit=array.habit; 
	                    		}
	                    		
	                    		var school=array.school;
	                    		if(array.school==null || array.school==''){
	                    			school="该同学还没有设置学校哦~~";
	                    		}
	                    		
	                    		var image=array.imageUrl;
	                    		if(image==null){
	                    			image='${img}';
	                    		}else{
	                    			image = image.split(",")[0];
	                    		}
	                    		
	                    		html+='<div class="one_student">'
	                    		html+='							<div>'
	                    		html+='						<div class="student_img">'
	                    		html+='					<a  href="<%=basePath %>WeNews/User?Uid='+array.pkid+'"><img class="img_100" src="'+image+'"></a>'
	                    		html+='								</div>'
	                    		html+='								<div class="student_intr">'
	                    		html+='									<a class="Single_title_ellipsis" href="<%=basePath %>WeNews/User?Uid='+array.pkid+'">'+array.name+'</a>'
	                    		html+='									<p>投稿（'+array.noticeCount +'）</p>'
	                    		html+='									<p>'
                    			if(array.areaID){
	                    			html+='										<a class="Single_title_ellipsis" href="<%=basePath %>WeNews/SchoolInfo?SchoolID='+array.areaID+'">'+school +'</a>'
	                    		}else{
	                    			html+='										<a class="Single_title_ellipsis" href="#">'+school +'</a>'
	                    		}
	                    		html+='									</p>'
	                    		html+='								</div>'
	                    		html+='								<div class="clear_float"></div>'
	                    		html+='								<hr>'
	                    		html+='								<ul>'
	                    		html+='									<li>'
	                    		html+='										<a href="">'
	                    		html+='											<p>'+habit+'</p>'
	                    		html+='										</a>'
	                    		html+='									</li>'
	                    		html+='								</ul>'
	                    		html+='							</div>'
	                    		html+='</div>';                   	
	                    	
	                    	
	                    }); 
	                    $("#student_list").html(html); 
	                    bindImgError();
	                    if(json.data.length<=0){
							if($("#NotMore").length<=0){
								$("#student_list").append(getNoMoreInfo()); 
							}
	                    }else{
	                    	$('#_page_').val(2);
	                    }
	                } 
	            }); 
	
})
</script>
	</body>

</html>


