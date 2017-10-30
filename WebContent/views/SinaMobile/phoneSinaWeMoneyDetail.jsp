<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <meta name="format-detection" content="telephone=no"/>
  <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <title>微米明细</title>
    <style>
    .add_moneyup{
		color:#319235;    
    }
    </style>
</head>
<body class="whitebg">
<input type="hidden" id="_page_" value="3"> 
<div class="starCenter">

    <div class="starLog">
        <div class="weimi_detail">余额明细</div>
        <ul id="list">
           <c:forEach var="WeMoney" items="${WeMoneyRecord}">
        	<li>
                <span class="fl">${WeMoney.createTime.substring(0,10)}</span>
                <span>${WeMoney.reson}</span>
                 <c:if test="${WeMoney.type eq 3 }">
               		<span class="fr wall_moneyup">-${WeMoney.weMoney}微米</span>
                </c:if>
                <c:if test="${WeMoney.type != 3 }">
               		<span class="fr  add_moneyup">+${WeMoney.weMoney}微米</span>
                </c:if>
               	
           </li>
       </c:forEach>
       <c:if test="${WeMoneyRecordCount<=0}">
            <li id="noreward">我还没有余额记录呢~</li>
        </c:if>
            
        </ul>
    </div>

</div>
<input type="hidden" name="basePath" id="basePath" value="<%=basePath%>" />
<jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
<script type="text/javascript">

			 

      $(function() {
			var i = 1; //设置当前页数 
			$(window).scroll(function() {		
				if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
					 $(".sk-spinner").remove();
					 $("#NotMore").remove(); 
					 $("#list").append(getLoading());
					 $.getJSON("<%=path%>/WeMoney/getWeMoneyRecordList",
						{
						 pageNumber:$('#_page_').val(),
						 pageSize:10,
						 submitType:'phone'
						 },function(json){ 
						 	console.log(1);
			                if(json && json.Status==1){ 
			                	console.log(2);
			                	var tmp="";
			                    $.each(json.data,function(index,array){ 
			          			         	var html="";
				          			         if (array.type==3) {
				          			         	html='<span>'+array.reson+'</span><span class="fr wall_moneyup">-'+array.weMoney+'微米</span>';
				          			         }else{
				          			         	html='<span>'+array.reson+'</span><span class="fr add_moneyup">+'+array.weMoney+'微米</span>';
				          			         }
				          			        
				                            tmp += '<li><span class="fl">'+array.createTime.substring(0,10)+'</span>'+
						                            html+
						                            '</li>';
			                    }); 
			                    $("#list").append(tmp); 
			        
			                    if(json.data.length<=0){
			                    	console.log(4);
			                    	//AddNoMoreDOM($("#myDiv"));
			                    	$(".sk-spinner").remove();
			                    	//$("#list").append(getNoMoreInfo())
									if($("#NotMore").length<=0){
										if($("#list")!=null){
											$("#list").append(getNoMoreInfo())
										}else{
											$("#list").append(getNoMoreInfo()); 
										}
									}
			                    }else{
			                    		console.log(5);
				                    var _page_=$('#_page_').val();
				                    $('#_page_').val(parseInt(_page_)+1);
				                   $(".sk-spinner").remove();
									$("#list").append(getLoading());
				                    i++; 
			                    }

			                }else{ 
			                		console.log(6);
			                	//AddNoMoreDOM($("#myDiv"));
			                		$(".sk-spinner").remove();
			                		//	$("#list").append(getNoMoreInfo())
									if($("#NotMore").length<=0){
										if($("#list")!=null){
											$("#list").append(getNoMoreInfo())
										}else{
											$("#list").append(getNoMoreInfo()); 
										}
									}
			                	return false; 
			                }
			                bindImgError();
			            }); 
				}
			});
		});
</script>
</html>
