<%@page import="wtb.smUtil.ErrorUtil"%>
<%@page import="java.io.PrintStream"%>  
<%@page import="java.io.ByteArrayOutputStream"%>   
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" isErrorPage="true"%>  
<%  //此处输出异常信息  
     exception.printStackTrace();  
 
     ByteArrayOutputStream ostr = new ByteArrayOutputStream();  
     exception.printStackTrace(new PrintStream(ostr));  

     ErrorUtil.HandleError(null, "500页面", (Exception)exception);
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!--<meta http-equiv="refresh" content="10;url=index.html">-->
    <meta name="format-detection" content="telephone=no"/>
    <link href="http://wenews.top/WeNewsAgency/css/bootstrap.min.css" rel="stylesheet">
    <!--<link href="css/style.css" rel="stylesheet">-->
    <title>500</title>
    <style type="text/css">
        .starCenter{text-align: center;margin-top:30%;color: #666;font-size: 15px;}
        .starCenter img{width: 50%;margin-right:15px;}
        .starCenter p{margin:5% 0 6% 0;font-size: 15px;color: #a09f9f;}
        .starCenter div{margin:6% 0 0 0;}
        .starCenter a{padding: 4px 28px;background-color: #b8b8b8;color: #fff;border-radius: 5px;text-decoration: none}
        @media screen and (max-width: 414px) {
            .starCenter img{width: 50%;margin-right:25px;}
        }
        @media screen and (max-width: 375px) {
            .starCenter img{width: 50%;margin-right:20px;}
        }
        @media screen and (max-width: 320px) {
        }
    </style>
</head>
<body class="whitebg">
<div class="starCenter">
    <img src="http://wenews.top/WeNewsAgency/images/weixinwenshe/500.png" class="img-rounded">
    <p>出错啦！服务器内部问题！</p>
</div>
</body> 

</html>
