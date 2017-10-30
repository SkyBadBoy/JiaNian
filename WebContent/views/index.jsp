<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wtb.core.model.Users"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Users user =(Users)request.getSession().getAttribute("UserName");
	if(user!=null){
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
	
%>
<jsp:include page="/include/head.jsp" />

<div class="row J_mainContent" id="content-main">
	<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
		src="${AdminLevelURL}" frameborder="0" data-id="${AdminLevelURL}"
		seamless>
		</iframe>
</div>


<jsp:include page="/include/foot.jsp" />

