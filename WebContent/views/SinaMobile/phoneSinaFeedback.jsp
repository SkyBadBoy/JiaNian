<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
  String path = request.getContextPath();
  String basePath =SmBaseUtil.getProjectBaseUrl(request);
%>
<html>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="format-detection" content="telephone=no"/>
   <jsp:include page="/include/commonMobileIndexCss.jsp"></jsp:include>
    <title>意见反馈</title>
    <style type="text/css">
    	html,body{background: #fff}
    	.feedback_wirte {width: 100%}
      	.feedback_wirte textarea{
      		margin: 15px 0;
      		width:88%;
      		margin-left:3%;
      		border-radius: 5px;
      		border: 1px solid #c0c0c0;
      		height: 200px;
      		padding: 3%;
      		font-size: 14px;
      		font-family: "方正兰亭";
      	}
      	.f-btn{
      		position: fixed;
      		bottom: 2%;
      		left: 0;
      		width: 92%;
      		margin-left: 4%;
      		background: #68ccb3;
      		font-weight: bold;
      		text-align: center;
      		padding: 2% 0;
      		color: #fff;
      		font-size: 18px;
      		border-radius: 5px;
      	}
    </style>

</head>
<body>

<div class="feedback_wirte">
	<textarea placeholder="请将您任何想要的告诉我们，我们会尽力帮您满足。"></textarea>
</div>
<a class="f-btn"  style="color: #fff">提交</a>
  <jsp:include page="/include/commonMobileJs.jsp"></jsp:include>
    <script type="text/javascript">
    	$(function(){
    		$('.f-btn').on('mousedown',function(){
    			if($('.feedback_wirte textarea').val() === ''){
    				webToast("输入不能为空","middle", 2000);
    			}else if ($('.feedback_wirte textarea').val().length >= 300) {
    				webToast("超过最大字数300字","middle", 2000);
    			}else{
    				$.ajax({
                      url : "<%=basePath%>Feedback/sendFeedback",
                      data : {
                            'content' : encodeURI($('.feedback_wirte textarea').val())
                                
                              },
                              success : function(obj) {
                                if(obj.Status){
                                  webToast("感谢您的反馈！","middle", 2000);
                                  window.location.href="<%=path%>/Students/phoneSinaIndex";
                                }else{
                                  webToast("反馈失败！","middle", 2000);
                                }
                                
                              }
                            });
    			}
    			
    		})
    	})	
    </script>
</body>
</html>
