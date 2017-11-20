<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.core.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Users user =(Users)request.getSession().getAttribute("UserName");
	if(user!=null){
		request.setAttribute("SuperManage", 	user.getLevel()==SmBaseGlobal.LevelStatus.SuperManage.getid());
		request.setAttribute("Manage", 			user.getLevel()==SmBaseGlobal.LevelStatus.Manage.getid());
		request.setAttribute("AreaManage", 			user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid());
		request.setAttribute("ParsonManage", 			user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid());
		request.setAttribute("TeacherManage", 			user.getLevel()==SmBaseGlobal.LevelStatus.TeacherManage.getid());
		request.setAttribute("StudentManage", 			user.getLevel()==SmBaseGlobal.LevelStatus.StudentManage.getid());
		
		request.setAttribute("WeChatID", user.getWeChatID());
		String Name="";
		if(user.getName()==null|| user.getName().isEmpty()){
			Name=user.getLoginName();
		}else{
			Name=user.getName();
		}
		Name=Name.replaceAll(" ", "&nbsp;");
		request.setAttribute("Name", Name);
		request.setAttribute("UserID", user.getPKID());
		String Area="";
		if(user.getArea()!=null && (user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid() || user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.ParsonManage.getid())){
			Area=user.getArea().getName();
		}
		request.setAttribute("UesrArea", Area);
		boolean AdminLevel= (user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid());
		boolean AdminLevelArea =(user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid());
		boolean AdminLevelTeacher =(user.getLevel()==SmBaseGlobal.LevelStatus.TeacherManage.getid());
		boolean AdminLevelStudent =(user.getLevel()==SmBaseGlobal.LevelStatus.StudentManage.getid());
		String urlparam=path+"/home?v=4.0";
		if(AdminLevel || AdminLevelArea || AdminLevelTeacher || AdminLevelStudent){
			urlparam=path+"/Parsonhome?v=4.0";
		}
		request.setAttribute("AdminLevelURL", urlparam);
	}
	String ImageURl="img/profile_small.png";
	if(user!=null && user.getImage()!=null && user.getImage().getUrl()!=null ){
		ImageURl =user.getImage().getUrl().split(",")[0];
	}

	
	
	
	
%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Cache" content="no-cache">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta http-equiv="pragma" content="no-store">
    <title>嘉念网络科技 - 人人学车后台管理主页</title>

    <meta name="keywords" content="人人学车">
    <meta name="description" content="微新闻社后台管理主页">

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=basePath%>css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=basePath%>css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.min.css?v=4.0.0" rel="stylesheet">
    <script type="text/javascript">
		if ('<%=user %>'=='null'){
			window.top.location ="<%=basePath%>Users/login";
		}
</script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" style="width:70px;height:70px;" class="img-circle" id="UserInfoImg" src="<%=ImageURl%>" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold" id="UserInfoName">${Name}</strong></span>
                                <span class="text-muted text-xs block">${UesrArea}<%= user!=null?user.getAdminLevel().getName():"" %><b class="caret"></b></span>
                                </span>
                            </a>
                            
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li>
                                	<a class="J_menuItem" href="<%=path%>/Users/modifyheadimg">修改头像</a>
                                </li>
                                <li>
                                	<a class="J_menuItem" href="<%=path%>/Users/changepassword">修改密码</a>
                                </li>
                                <li>
                                	<a class="J_menuItem" href="<%=path%>/Users/addUser?uid=${UserID}&type=self">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                	<a href="<%=path%>/Users/loginOut">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">Jia Nian
                        </div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="${AdminLevelURL}"><i class="fa fa-columns"></i> <span class="nav-label">首页</span></a>
                    </li>
                    <!-- 普通管理和超级管理员 -->
                    <c:if test="${SuperManage || Manage}">
	                    <li>
	                        <a href="#"><i class="fa fa-bell"></i> <span class="nav-label">基础设置</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/Setting/SettingList?parentID=0&sina=<%= SmBaseUtil.Random() %>">基础设置</a>
	                            </li>      
	                        </ul>
	                    </li>
	                    <li>
	                        <a class="J_menuItem" href="<%=path%>/Users/UserList"><i class="fa fa-user"></i> <span class="nav-label">管理员列表</span></a>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-bank"></i> <span class="nav-label">官网管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li>
	                            		<a class="J_menuItem" href="<%=path%>/Combo/ComboList?sina=<%= SmBaseUtil.Random() %>">套餐管理</a>
	                            </li>      
	                        </ul>
	                    </li>
	                     <li>
	                        <a href="#"><i class="fa fa-file-text-o"></i> <span class="nav-label">驾考管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li>
	                          	  <a class="J_menuItem" href="<%=path%>/ApplyList/ApplyListList?type=<%=SmBaseGlobal.DealInfoType.CorrectList.getid() %>&sina=<%= SmBaseUtil.Random() %>">报名管理</a>
	                            </li>  
	                             <li>
	                          	  <a class="J_menuItem" href="<%=path%>/Comment/CommentList?sina=<%= SmBaseUtil.Random() %>">评论管理</a>
	                            </li>   
	                             <li>
	                          	  <a class="J_menuItem" href="<%=path%>/Comment/CommentList?sina=<%= SmBaseUtil.Random() %>">免费试学管理</a>
	                            </li>      
	                        </ul>
	                    </li>
	               <%--      <li>
	                        <a href="#"><i class="fa fa-bell"></i> <span class="nav-label">新闻管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/Notices/NewsList?Status=1&sina=<%= SmBaseUtil.Random() %>">新闻管理</a>
	                            </li>    
	                             <li>
	                             	<a class="J_menuItem" href="<%=path%>/Notices/NewsList?sina=<%= SmBaseUtil.Random() %>&Status=<%=SmBaseGlobal.CheckStatus.Apply.getid()%>">小新闻审核</a>
	                            </li>
	                             <li>
	                             	<a class="J_menuItem" href="<%=path%>/Comment/CommentList?sina=<%= SmBaseUtil.Random() %>">新闻评价</a>
	                            </li>  
	                            <li>
	                             	<a class="J_menuItem" href="<%=path%>/ApplyList/ApplyListList?type=<%=SmBaseGlobal.DealInfoType.CorrectList.getid() %>&sina=<%= SmBaseUtil.Random() %>">名师点评</a>
	                            </li>     
	                        </ul>
	                    </li> --%>
                    <%-- 	 <li>
	                        <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">微新闻社管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/weChatPublic/weChatPublicList?sina=<%= SmBaseUtil.Random() %>">支社管理</a>
	                            </li>        
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/Students/StudentsUserList?sina=<%= SmBaseUtil.Random() %>">学生列表</a>
	                            </li> 
	                             <li>
	                             	<a class="J_menuItem" href="<%=path%>/HonorRecord/honorRecordList?sina=<%= SmBaseUtil.Random() %>">勋章列表</a>
	                            </li> 
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/Vote/VoteList?sina=<%= SmBaseUtil.Random() %>">投票管理</a>
	                            </li> 
	                             <li>
	                             	<a class="J_menuItem" href="<%=path%>/Ranking/RankingList">排行榜</a>
	                            </li>
	                            <li>
	                             	<a class="J_menuItem" href="<%=path%>/Scenic/ScenicList">商家申请加盟</a>
	                            </li>
	                        </ul>
	                    </li>
	                    <li>
	                        <a href="#"><i class="fa fa-bullhorn"></i> <span class="nav-label">活动管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                           
	                       		<li>
	                       			<a class="J_menuItem" href="<%=path%>/Activity/ActivityList?sina=<%= SmBaseUtil.Random() %>">活动管理</a>
	                            </li>
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/Competition/CompetitionList?sina=<%= SmBaseUtil.Random() %>">校园剧大赛</a>
	                            </li>
	                            <li>
	                            	<a class="J_menuItem" href="<%=path%>/Competition/SummitMeetingList?sina=<%= SmBaseUtil.Random() %>">新西兰峰会</a>
	                            </li>
	                             <li>
	                             	<a class="J_menuItem" href="<%=path%>/Competition/CompetitionCampList?sina=<%= SmBaseUtil.Random() %>">夏令营活动</a>
	                            </li>
	                        </ul>
	                    </li>
	                    
	                    <li>
	                       <a href="#"><i class="fa fa-image"></i> <span class="nav-label">素材管理</span><span class="fa arrow"></span></a>
		                     <ul class="nav nav-second-level">
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/ProdPicture/ProdPictureList?sina=<%= SmBaseUtil.Random() %>"></i> <span class="nav-label">素材图片</span></a>
			                    </li>
			                     <li>
			                        <a class="J_menuItem" href="<%=path%>/ProdPicture/ProdPictureHeadList?sina=<%= SmBaseUtil.Random() %>"></i> <span class="nav-label">Banner管理</span></a>
			                    </li>
			                      <li>
			                        <a class="J_menuItem" href="<%=path%>/Advert/AdvertList?sina=<%= SmBaseUtil.Random() %>"></i> <span class="nav-label">广告位管理</span></a>
			                    </li>
			                     <li>
	                       			 <a class="J_menuItem" href="<%=path%>/VideoClass/VideoClassList?sina=<%= SmBaseUtil.Random() %>"> <span class="nav-label">视频分类管理</span></a>
	                   			 </li>
			                     <li>
	                       			 <a class="J_menuItem" href="<%=path%>/Video/VideoList?sina=<%= SmBaseUtil.Random() %>"> <span class="nav-label">视频管理</span></a>
	                   			 </li>
		                    </ul>
		                </li>   
		                 <li>
	                       <a href="#"><i class="fa fa-bell"></i> <span class="nav-label">系统运维</span><span class="fa arrow"></span></a>
		                     <ul class="nav nav-second-level">
			                   	  <li>
			                        <a class="J_menuItem" href="<%=path%>/Feedback/FeedbackList?sina=<%= SmBaseUtil.Random() %>"><i class="glyphicon glyphicon-comment"></i> <span class="nav-label">用户反馈</span></a>
			                    </li>
			                     <li>
			                        <a class="J_menuItem" href="<%=path%>/Region/RegionList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-external-link"></i> <span class="nav-label">地区管理</span></a>
			                    </li>
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/Notices/NoticesList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-bell"></i> <span class="nav-label">系统通知</span></a>
			                    </li>
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/Version/VersionList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-arrow-circle-o-up"></i><span class="nav-label">APP版本更新</span></a>
			                    </li>
		                    </ul>
		                </li>
		                
	                     <li>
	                       <a href="#"><i class="fa fa-file"></i> <span class="nav-label">日志管理</span><span class="fa arrow"></span></a>
		                     <ul class="nav nav-second-level">
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/Students/StudentsLogList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-file"></i> <span class="nav-label">新闻同步管理</span></a>
			                    </li>
			                     <li>
			                        <a class="J_menuItem" href="<%=path%>/Students/SchoolSettledList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-file"></i> <span class="nav-label">学校入驻管理</span></a>
			                    </li>
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/ErrorLog/ErrorLogList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-file"></i> <span class="nav-label">错误日志</span></a>
			                    </li>
		                    </ul>
		                </li>
		                
		                 <li>
	                       <a href="#"><i class="fa fa-weixin"></i> <span class="nav-label">公众号管理</span><span class="fa arrow"></span></a>
		                     <ul class="nav nav-second-level">
			                   
			                     <li>
			                        <a class="J_menuItem" href="<%=path%>/WeChatCustom/WeChatCustomHome?sina=<%= SmBaseUtil.Random() %>&flag=0"> <span class="nav-label">公众号管理</span></a>
			                    </li>
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/WeChatCustom/WeChatCustomHome?sina=<%= SmBaseUtil.Random() %>&flag=1"> <span class="nav-label">微信配置管理</span></a>
			                    </li>
		                    </ul>
		                </li> --%>
                 </c:if>
                 
                 <c:if test="${AreaManage}">
                  
	                     <li>
	                        <a class="J_menuItem" href="<%=path%>/Users/UserList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-user"></i> <span class="nav-label">管理员列表</span></a>
	                    </li>
	                  
	                    <li>
	                        <a href="#"><i class="fa fa-bell"></i> <span class="nav-label">新闻管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li><a class="J_menuItem" href="<%=path%>/Notices/NewsList?Status=1&sina=<%= SmBaseUtil.Random() %>">新闻管理</a>
	                            </li>    
	                             <li><a class="J_menuItem" href="<%=path%>/Notices/NewsList?sina=<%= SmBaseUtil.Random() %>&Status=<%=SmBaseGlobal.CheckStatus.Apply.getid()%>">小新闻审核</a>
	                            </li>  
	                            <li><a class="J_menuItem" href="<%=path%>/Notices/NewsList?sina=<%= SmBaseUtil.Random() %>&Type=2&Status=<%=SmBaseGlobal.CheckStatus.Apply.getid()%>">新闻评价</a>
	                            </li>            
	                        </ul>
	                    </li>
                    	 <li>
	                        <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">微新闻社管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li><a class="J_menuItem" href="<%=path%>/weChatPublic/weChatPublicList?sina=<%= SmBaseUtil.Random() %>">支社管理</a>
	                            </li>        
	                       		<li><a class="J_menuItem" href="<%=path%>/Activity/ActivityList?sina=<%= SmBaseUtil.Random() %>">活动管理</a>
	                            </li>  
	                            <li><a class="J_menuItem" href="<%=path%>/Students/StudentsUserList?sina=<%= SmBaseUtil.Random() %>">学生列表</a>
	                            </li>      
	                        </ul>
	                    </li>
                    	 <li>
                        	<a class="J_menuItem" href="<%=path%>/Video/VideoList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-video-camera"></i> <span class="nav-label">视频管理</span></a>
	                    </li>
	                    <li>
	                       <a href="#"><i class="fa fa-image"></i> <span class="nav-label">图片管理</span><span class="fa arrow"></span></a>
		                     <ul class="nav nav-second-level">
			                    <li>
			                        <a class="J_menuItem" href="<%=path%>/ProdPicture/ProdPictureList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-image"></i> <span class="nav-label">新闻图片管理</span></a>
			                    </li>
			                     <li>
			                        <a class="J_menuItem" href="<%=path%>/ProdPicture/ProdPictureHeadList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-image"></i> <span class="nav-label">列表图片管理</span></a>
			                    </li>
		                    </ul>
		                </li>
				       <li>
	                    	<a class="J_menuItem" href="<%=path%>/WeChatCustom/WeChatCustomHome?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-bullhorn"></i> <span class="nav-label">公众号管理</span></a>
	                    </li>
      
                 </c:if>
                <!-- 学校管理员 -->
                    <c:if test="${ParsonManage }">
	                      <li>
	                        <a href="#"><i class="fa fa-bell"></i> <span class="nav-label">新闻管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                            <li><a class="J_menuItem" href="<%=path%>/Notices/NewsList?Status=1&sina=<%= SmBaseUtil.Random() %>">新闻管理</a>
	                            </li>    
	                             <li><a class="J_menuItem" href="<%=path%>/Notices/NewsList?sina=<%= SmBaseUtil.Random() %>&Status=<%=SmBaseGlobal.CheckStatus.Apply.getid()%>">小新闻审核</a>
	                            </li>           
	                        </ul>
	                      
	                    </li>
	                    
	                     <li>
	                        <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">支社管理</span><span class="fa arrow"></span></a>
	                        <ul class="nav nav-second-level">
	                                  
	                            <li><a class="J_menuItem" href="<%=path%>/Students/StudentsUserList?sina=<%= SmBaseUtil.Random() %>">学生列表</a>
	                            </li> 
	   
	                        </ul>
		                 <li>
	                    	<a class="J_menuItem" href="<%=path%>/WeChatCustom/WeChatCustomHome?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-bullhorn"></i> <span class="nav-label">公众号管理</span></a>
	                    </li>
                    	 <li>
                        	<a class="J_menuItem" href="<%=path%>/Video/VideoList?sina=<%= SmBaseUtil.Random() %>"><i class="fa fa-video-camera"></i> <span class="nav-label">视频管理</span></a>
	                    </li>
	            
                    </c:if>
                    
                    
                   
                    
                   
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" style="padding-left:20px;width:400px" onsubmit="return false;" action="#">
                           <div class="form-group">
                                <h2 style="margin-top:15px">校园微新闻社后台管理系统</h2>
                            </div >
                        </form>
                         
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-bell"></i> <c:if test="${MesageCount>0}"><span class="label label-primary">${MesageCount}</span> </c:if>
                            </a>
                            <c:if test="${MesageCount>0}"> 
                            <ul class="dropdown-menu dropdown-alerts">
	                                <li>
	                                    <a class="J_menuItem" href="<%=path%>/Message/MessageList">
	                                        <div>
	                                            <i class="fa fa-envelope fa-fw"></i>【系统通知】您有${MesageCount}条未读消息
	                                            
	                                        </div>
	                                    </a >
	                                </li>
                            </ul>
                          </c:if>
                        </li>
                       
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                      <a href="javascript:;" class="active J_menuTab" data-id="${AdminLevelURL}">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="<%=path%>/Users/loginOut" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            
            
            
            
            
            
            
            
            
            
            
            
            
            
      
