<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}

%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="<%=basePath%>css/plugins/alertPopShow/alertPopShow.css?v=3.3.8" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/reward.css?v=2.4"/>
    
     <script type="text/javascript" src="<%=basePath%>js/flexible.debug.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/flexible_css.debug.js"></script>
<link href="<%=basePath%>css/style.min.css?v=4.0.2" rel="stylesheet"><base target="_blank">
    
   
    <title>新浪小编—给Ta打赏</title>
     <style type="text/css"> 
html{
	overflow-y: visible!important;
} 
::-webkit-scrollbar{
    width: 0px;
    background-color: #F5F5F5;
}
.web_toast{
font-size: 14px;
}
.rewardName{

overflow: hidden;
    width: 100px;
    display: inline-block;
    text-overflow: ellipsis;
    white-space: nowrap;
    text-align: center;
}
</style>
    
  
</head>
<body class="whitebg">
<div class="starCenter">
<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" />
    <div class="starTop">
        <div class="userMes">
            <a href="<%=basePath%>Students/phoneSinaUserInfo?pid=${Student.PKID}"><img src="${imageurl}"></a>
            <div>
                <p>${Student.name}</p>
                <p>${Student.area.name}</p>
            </div>
        </div>
  
        <div class="getStar">
           已经获得<span class="starNum">${shanSum}</span>微米 
        </div>
        <c:if test="${isMe eq -1 }">
	        <div class="topBg">
	            <a data-toggle="modal" onclick="$('.gBtn').text('打赏');" data-target="#myModal1"><img src="<%=basePath%>images/giveBtn.png"></a>
	        </div>
        </c:if>
    </div>
   <div class="starLog">
       <p>打赏记录</p>
       <ul id="list">
       <c:forEach var="WeMoney" items="${WeMoneyRecord}">
        	<li>
                <span class="fl">${WeMoney.formtTime}</span>
                <span >${(WeMoney.fromUser==null || WeMoney.fromUser.name==null )?"系统打赏":WeMoney.fromUser.name}</span>
                <span class="fr">${WeMoney.weMoney}微米</span>
           </li>
       </c:forEach>
       <c:if test="${WeMoneyRecordCount<=0}">
            <li id="noreward">还没有人给我打赏~</li>
        </c:if>
       </ul>
       <br/>
<footer style="margin:10px 0;bottom: 10px;width: 100%">
    <div style="text-align: center">&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %></div>
</footer>
   </div>

</div>
<br/>


<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
    <div class="center">
        <div class="center-top">
            <p class="hMe">我拥有的微米</p>
            <p class="myStar">${MyWeMoney}</p>
        </div>
        <div class="center-bottom">
            <label>
                <input type="radio" name="tags" value="1" />
                <span>1</span>
            </label>
            <label>
                <input type="radio" name="tags" value="3" />
                <span>3</span>
            </label>
            <label>
                <input type="radio" name="tags" value="5" />
                <span>5</span>
            </label>
            <label>
                <input type="radio" name="tags" value="10" />
                <span>10</span>
            </label>
            <label>
                <input type="radio" name="tags" value="20" />
                <span>20</span>
            </label>
            <label>
                <input type="radio" name="tags" value="30" />
                <span>30</span>
            </label>
            <input type="text" name="other" class="ipt" placeholder="其他" >
            <p class="error"></p>
	         <div class="gBtn">打赏</div>
        </div>
    </div>
</div>


<input type="hidden" id="_page_" value="2"> 


    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/plugin/bootstrap/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/plugin/alertPopShow/alertPopShow.js?v=3.3.7"></script>
<script src="<%=basePath%>js/wechat_index.js?v=1.0.2"></script>

<script type="text/javascript">
var isloading=false;
$(document).ready(function() {

	$(window).scroll(function() {		
		if(isloading){
			return;
		}
		if (getScrollTop() + getWindowHeight() == getScrollHeight()) {
			$(".sk-spinner").remove();
			$("#list").append(getLoading()); 
			isloading=true;
			 $.getJSON("<%=path%>/Students/phoneSinaReWardList",
				{
				 _page_:$('#_page_').val(),
				 uid:'${Student.PKID}',
	    		 pid:'${NewID}',
	    		 fid:'<%=user.getID()%>'
				 },function(json){ 
	                if(json && json.Status==1 && json.data.length>0){ 
	                	$('#_page_').val(json._page_);
	                	var length=json.data.length;
	                	for(var i=0;i<length;i++){
	                		var name="系统奖励";
	                		if(json.data[i].fromUser!=null && json.data[i].fromUser.name!=null && json.data[i].fromUser.name!=""){
	                			name=json.data[i].fromUser.name;
	                		}
	                		var $li = $('<li><span class="fl">'+ json.data[i].formtTime +'</span><span>'+ name +'</span><span class="fr">'+json.data[i].weMoney+'微米</span></li>');
		                	$('.starLog').find('ul').append($li);
	                	}
	                	if(length<=0){
	                		$(".sk-spinner").remove();
	                    	AddNoMoreDOM();
	                    }else{
		                    $(".sk-spinner").remove();
		                    $("#list").append(getLoading()); 
	                    }
	                	var _page_=$('#_page_').val();
	                	$('#_page_').val(parseInt(_page_)+1);
	                }else{ 
	                	$(".sk-spinner").remove();
	                	if($("#NotMore").length<=0){
	                		$("#list").append(getNoMoreInfo()); 
	                	}
	                    return false; 
	                }
	                isloading=false;
	            }); 
			}
	})
});
    $(function () {
    	
        var oNub = null;
        var otrue = false;
        var obol = true;
        $('.ipt').focus(function(){
              $('label').find('input').each(function(i,elem){
                    $(elem).prop("checked",false);
                    otrue = true;
                    obol = true;
              });
              
        })

        var re = /^[0-9]*[1-9][0-9]*$/;
        var $starNub = null;
        var  $iptNum = null;
        $('.ipt').on('keyup',function(){
            var val = re.test($('.ipt').val());
            $iptNum =  parseInt($('.ipt').val()); 
            $starNub = parseInt($('.myStar').text()) ;

            var $oStar = $('.ipt').val();
            var $max = $(".myStar").text();


             if ($('.ipt').val() == ''){
                $('.error').html('请选择要赞赏的微米数量');
                return
             }else if(!val){
                $('.error').html('请输入正确的微米数量');
                return
            }else if($oStar>$max){
                $('.ipt').val($('.ipt').val().substring(0,9))
                $('.error').html('微米不足');
                return
            }else{
                $('.error').html('');
            }  

        })
        var $labNum = null;
        $('label').on('click',function(){
            obol = false;
            $('.error').html('');
        })
   
        $('#myModal1').on('hidden.bs.modal', function () {
             $('.ipt').val('');
        });   
        
        $('#myModal1').find('.gBtn').click(function(){
                var val = re.test($('.ipt').val());
					
                 if ($('.ipt').val() == '' && $("input:checked+span").text()=="" ){
                    $('.error').html('请选择要赞赏的微米数量');
                    return
                 }else if(!val && $("input:checked+span").text()==""){
                    $('.error').html('请输入正确的微米数量');
                    return
                }else{
                    if(otrue && obol){
                        oNub = $("input:checked+span").text();
                        if(oNub==""){
                        	oNub = $('.ipt').val();
                        }
                        if($starNub < $iptNum){
                            $('.error').html('微米不足');
                            return 
                        }
                        check(oNub);
                    }
                    
                }  

                $('label').find('input').each(function(i,elem){
                    if( $(elem).prop("checked") === true){
                        $starNub = parseInt($('.myStar').text()) ;
                        oNub = this.value;
                        if($labNum > $starNub){
                            $('.error').html('微米不足');
                            return 
                        }
                         check(oNub);
                    }
                });
        });
    });
    function check(oNub){
    	
    	if($(".gBtn").text()=="打赏中"){
    		return false;
    	}
    	$(".gBtn").text("打赏中");
    	 $starNub = parseInt($('.myStar').text()) ;
    	 $.getJSON("<%=path%>/Students/phoneSinaToReWard",
				 {
    		 uid:'${Student.PKID}',
    		 nid:'${NewID}',
    		 fid:'<%=user.getID()%>',
    		 money:oNub
			 },function(json){ 
				 if(json.Status==true || json.Status=='true'){
					 $('#myModal1').modal('hide');
                     var $li = $('<li><span class="fl">'+ json.Formamt +'</span><span>'+ json.Message.fromUser.name +'</span><span class="fr">'+oNub+'微米</span></li>')
                     $('.starLog').find('ul').prepend($li);
                     
                     $('.error').html('');
                      otrue = false;
                      var $math =  parseInt($starNub) - parseInt(oNub);
                     $('.myStar').text($math);
                     var starNum=$(".starNum").html();
                     starNum = parseInt(starNum)+parseInt(oNub);
                     $(".starNum").html(starNum);
                     if(json.BackWeMoney>0){
                    	 webToast("由于您昨天打赏积极，系统成功返还【"+json.BackWeMoney+"】微米", "bottom", 5000);
                     }
                     
                     $("#noreward").remove();
                     
				 }else{
					 $('.error').html(json.Message);
					 $(".gBtn").text("打赏");
				 }
				
			 })
    }
    
    
    
</script>
<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
</body>
</html>