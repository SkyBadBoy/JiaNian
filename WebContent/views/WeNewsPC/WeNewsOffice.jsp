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
	String schoolImg= basePath+SmBaseGlobal.SchoolDefaultImageUrl;
	request.setAttribute("img", img);
	request.setAttribute("SchoolImg", schoolImg);
%>
<html lang="en">
<head>
    <title>分社</title>
	<jsp:include page="/include/wenewspc/meta.jsp"/>
	<jsp:include page="/include/wenewspc/commonCss.jsp"/>
</head>
	<body>      
<jsp:include page="/include/wenewspc/commonNav.jsp"/>
		<div class="container center_block">
		<jsp:include page="/include/wenewspc/commonLeft.jsp"/>
			<div class="student_block">
				<div class="search_block">
			<input type="hidden" name="CityID" value="${CityID}" id="CityID" />
			<input type="hidden" name="UnitID" value="${UnitID}" id="UnitID" />
			<input type="hidden" name="CityAreaID" value="${CityAreaID}" id="CityAreaID" />
			<input type="hidden" name="ProvinceID" value="${ProvinceID}" id="ProvinceID" />
                        <select style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" id="Province"onchange="getCity(this,1);"></select>
                        <select  style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" id="City" onchange="getAreaID(this,1)"></select>
                        <select  style="width:160px;font-size: 16px;padding-left: 20px; border: 1px solid #DFE0E4;border-radius: 4px; height: 42px;" id="AreaID" ></select>
					<input style="width: 123px; height: 40px; background: #4C9ED9;color: white!important;border: none; border-radius: 8px;" class="search_btn" type="button" id="query" value="查询" />
				</div>
				<div class="school_list" id="newsList">
				
					<c:forEach items="${SchoolData }" var="data">
						<div class="one_school">
							<div class="student_img">
								<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${data.areaID }"><img class="img_100" src="${data.logo.url.split(',')[0] eq null?SchoolImg:data.logo.url.split(',')[0] }"></a>
							</div>
							<div class="school_info">
								<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID=${data.areaID }">${data.area.name }</a>
								<a href="user.html">小记者（${data.studentCount }）</a><br>
								<span>地址：${data.address }</span>
								<a href="">投稿（${data.noticeCount }）</a>
							</div>
							<div class="clear_float"></div>
							<hr>
							<div class="school_intr">
								<p><b>学校简介：</b></p>
								<c:if test="${data.content ne null }">
									<p>${data.content}</p>
								</c:if>
								<c:if test="${data.content eq null }">
									<p>该学校还没有留下脚印哦</p>
								</c:if>
								
							</div>
						
							<div class="clear_float" style="margin-top: 20px;"></div>
						</div>
					</c:forEach>
					
					
				</div>
			</div>
		</div>
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
					 $(".sk-spinner").remove();
					 $("#NotMore").remove(); 
					 $("#newsList").append(getLoading());
					 $.getJSON("<%=basePath%>WeNews/getWeNewWeChatPublicList",
						{
						 pageNumber:$('#_page_').val(),
						 pageSize:10,
						 submitType:'phone',
						 AreaID:$("#AreaID").val()
						 },function(json){ 
			                if(json && json.Status==1){ 
			                	var html="";
			                    $.each(json.data,function(index,array){ 
			                    		console.log(array.logo.url!=null);
			                    		console.log(array.logo)
			                    		
			                    		var imgs="";
			                    		if(array.logoID !=null){
			                    			imgs='<img class="img_100" src="'+array.logo.url.split(',')[0] +'">';
			                    		}else{
			                    			imgs='<img class="img_100" src="'+"${SchoolImg}" +'">';
			                    		}
			                    		var content="";
			                    		if(array.content == null){
			                    			content="该学校还没有脚印哦~~";
			                    		}else{
			                    			content=array.content;
			                    		}
			                    		html+='<div class="one_school">'
			                    		html+='<div class="student_img">'
			                    		html+='	<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID='+array.PKID+'">'+imgs+'</a>'
			                    		html+='</div>'
			                    		html+='<div class="school_info">'
			                    		html+='	<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID='+array.PKID+'">'+array.area.name +'</a>'
			                    		html+='	<a >小记者（'+array.studentCount+'）</a><br>'
			                    		html+='	<span>地址：'+array.address+'</span>'
			                    		html+='	<a >投稿（'+array.noticeCount+'）</a>'
			                    		html+='</div>'
			                    		html+='<div class="clear_float"></div>'
			                    		html+='<hr>'
			                    		html+='<div class="school_intr">'
			                    		html+='	<p><b>学校简介：</b></p>'
			                    		html+='	<p>'+content +'</p>'
			                    		html+='</div>'
			                    		html+='<div class="clear_float" style="margin-top: 20px;"></div>'
			                    		html+='</div>'               	
			                    }); 
			                    $("#newsList").append(html); 
			                    if(json.data.length<=0){
			                    	$(".sk-spinner").remove();
									if($("#NotMore").length<=0){
										if($("#newsList")!=null){
											$("#newsList").append(getNoMoreInfo())
										}else{
											$("#newsList").append(getNoMoreInfo()); 
										}
									}
			                    }else{
				                    var _page_=$('#_page_').val();
				                    $('#_page_').val(parseInt(_page_)+1);
				                    $(".sk-spinner").remove();
									$("#newsList").append(getLoading());
				                    i++; 
			                    }
			                }else{ 
			                		$(".sk-spinner").remove();
									if($("#NotMore").length<=0){
										if($("#newsList")!=null){
											$("#newsList").append(getNoMoreInfo())
										}else{
											$("#newsList").append(getNoMoreInfo()); 
										}
									}
			                	return false; 
			                }
			               bindImgError();
			            }); 
				}
			});
		});	
		$("#query").click(function(){
			var AreaID=$("#AreaID").val();
			if(AreaID==null||AreaID==""){
				popup_msg("请选择地区");
			} 
			
			 $.getJSON("<%=basePath%>WeNews/getWeNewWeChatPublicList",
			{
			 pageNumber:$('#_page_').val(),
			 pageSize:10,
			 submitType:'phone',
			 AreaID:AreaID
			 },function(json){ 
                if(json && json.Status==1){ 
                	var html="";
                    $.each(json.data,function(index,array){ 
                    		console.log(array.logo.url!=null);
                    		console.log(array.logo)
                    		
                    		var imgs="";
                    		if(array.logoID !=null){
                    			imgs='<img class="img_100" src="'+array.logo.url.split(',')[0] +'">';
                    		}else{
                    			imgs='<img class="img_100" src="'+"${SchoolImg}" +'">';
                    		}
                    		var content="";
                    		if(array.content == null){
                    			content="该学校还没有脚印哦~~";
                    		}else{
                    			content=array.content;
                    		}
                    		html+='<div class="one_school">'
                    		html+='<div class="student_img">'
                    		html+='	<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID='+array.PKID+'">'+imgs+'</a>'
                    		html+='</div>'
                    		html+='<div class="school_info">'
                    		html+='	<a href="<%=basePath %>WeNews/SchoolInfo?SchoolID='+array.PKID+'">'+array.area.name +'</a>'
                    		html+='	<a >小记者（'+array.studentCount+'）</a><br>'
                    		html+='	<span>地址：'+array.address+'</span>'
                    		html+='	<a >投稿（'+array.noticeCount+'）</a>'
                    		html+='</div>'
                    		html+='<div class="clear_float"></div>'
                    		html+='<hr>'
                    		html+='<div class="school_intr">'
                    		html+='	<p><b>学校简介：</b></p>'
                    		html+='	<p>'+content +'</p>'
                    		html+='</div>'
                    		html+='<div class="clear_float" style="margin-top: 20px;"></div>'
                    		html+='</div>'               	
                    }); 
                    $("#newsList").html(html); 
                }else{
                	popup_msg("未找到数据");
                }
               bindImgError();
            }); 
		
		</script>
		
	</body>

</html>