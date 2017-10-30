
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="wx_nav4">
    <div id="wx_nav4_ul" class="nav_4">
        <ul class="box">
            <li>
                <a href="javascript:;" style="border-right: 1px solid #d1d1d1"><span>小编投稿排行</span></a>
                <dl>
                    <dd><a href="<%=basePath%>Ranking/phoneStudentsRanking?type=3"><span>今日投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneStudentsRanking?type=1"><span>本周投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneStudentsRanking?type=4"><span>上周投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneStudentsRanking?type=2"><span>本月投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneStudentsRanking?type=0"><span>总投稿排行</span></a></dd>
                </dl>
            </li>
            <li>
                <a href="javascript:;" style="border-right: 1px solid #d1d1d1"><span>学校投稿排行</span></a>
                <dl>
                    <dd><a href="<%=basePath%>Ranking/phoneSchoolRanking?type=3"><span>今日投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneSchoolRanking?type=1"><span>本周投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneSchoolRanking?type=2"><span>本月投稿排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneSchoolRanking?type=0"><span>总投稿排行</span></a></dd>
                </dl>
            </li>
            <li>
                <a href="javascript:;"><span>人气作品榜</span></a>
                <dl>
                    <dd><a href="<%=basePath%>Ranking/phoneReadRanking?type=3"><span>今日阅读排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneReadRanking?type=1"><span>本周阅读排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneReadRanking?type=4"><span>上周阅读排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneReadRanking?type=2"><span>本月阅读排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneReadRanking?type=0"><span>总阅读量排行</span></a></dd>
                    <dd><a href="<%=basePath%>Ranking/phoneKindRanking"><span>微社周之最</span></a></dd>
                </dl>
            </li>


        </ul>
    </div>
    <div id="nav4_masklayer" class="masklayer_div">&nbsp;</div>
</div>