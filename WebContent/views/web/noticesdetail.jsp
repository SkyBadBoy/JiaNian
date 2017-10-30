<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	GregorianCalendar gc=new GregorianCalendar(); 
	gc.setTime(new Date()); 
	gc.add(2,-1);
	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
	String Date=format.format(gc.getTime());
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader("Expires",0); 
	
%>
<html>

<head>
<jsp:include page="/include/commonCss.jsp" />
</head>
<input type="hidden" id="WeChatLastStat" value="${WeChatLastStat}" />
<input type="hidden" id="WeGroupLastStat" value="${WeGroupLastStat}" />

<body class="gray-bg top-navigation">

    <div id="wrapper">
        <div id="page-wrapper" class="gray-bg">
            <div class="row border-bottom white-bg">
                <nav class="navbar navbar-static-top" role="navigation">
                    <div class="navbar-header">
                        <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                            <i class="fa fa-reorder"></i>
                        </button>
                        <a href="#" class="navbar-brand">微新闻社</a>
                    </div>
                   
                </nav>
            </div>
            <div class="wrapper wrapper-content">
                <div class="container">
                 
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                                <div class="ibox-content">
                                    <div style="text-align:center">
                                       <h3>${Notices.title}</h3>
                                    </div>

                                    <div class="m-t-sm">

                                        <div class="row" style="padding-left: 30px;padding-right: 30px">
                                            
											${Notices.content}
<div style="float: right;padding: 10px;text-align: right">
										<p>${Notices.author}</p>	
										
										
										<p>${Notices.createTime.substring(0,10)}</p>	
										
										
										</div>
                                        </div>
                                        
                                    </div>

                                  

                                </div>
                            </div>
                        </div>
                        

                    </div>

                  

                </div>

            </div>
            <div class="footer">
	<div class="pull-right">
		&copy; <%=SmBaseGlobal.CurrentYear%><%=SmBaseGlobal.CompanyName %>
	</div>
</div>
        </div>
    </div>
</body>
</html>