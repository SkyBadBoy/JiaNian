<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@page import="wtb.core.model.Students"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
	request.setAttribute("path", path);
	Students user =(Students)request.getSession().getAttribute("StudentName");
	if(user==null){
		user=new Students();
	}
%>
<html >
<head>
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>疯狂猜图</title>
<link rel="stylesheet" href="">
<link href="<%=basePath%>css/ct_style.css?v=3.3.8.1" rel="stylesheet">
<style type="text/css">
#loading {
            opacity: 0.8;
            position: fixed;
            top: 0;
            left: 0;
            display:none;
            width: 100%;
            height: 100%;
            z-index: 1055;
            background: url(../img/wsload.gif) no-repeat center center #FFF;
            background-size: 10%;
        }
</style>
 <script>
  			var num = 1 / window.devicePixelRatio;
			document.write('<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale='+num+',minimum-scale='+num+',maximum-scale='+num+'" />')
			var fontNum = document.documentElement.clientWidth / 10;
			var html = document.getElementsByTagName('html')[0];
			html.style.fontSize = fontNum + 'px';
 		 </script>


</head>
<body >
		<div id="warp">
			<!--<header><span class="sl">1</span></header>-->
			<img class="weixinwenshe" src="<%=basePath %>images/games/ct_bj4.png">
			<img class="gs_num" src="<%=basePath %>images/games/ct_bj3.png">
			<span class="guanshu">关数</span>
			<span class="sl">1</span>
			<div style="height:  1.7rem;"></div>
			<div class="content">
				<span class="heder">电影&电视</span>
				<img class="heder_img" src="<%=basePath %>images/games/ct_bj2.png">
				<img class="img" src=""/>
				<ul class="wz">
					
				</ul>
				<span class="x"></span>
				<ul class="wz1">
					
				</ul>
			</div>
		</div>

<script src="<%=basePath%>js/jquery.min.js?v=1.1.1.1" type="text/javascript" ></script>
<script src="<%=basePath%>js/char.js?v=1.1.1.1" type="text/javascript" ></script>
<script type="text/javascript">

//数据库
//-------------------------------------
var arrData=null
//jq代码	
//---------------------------------
//本地缓存


 
//-------------------

var sz=[];
var b=0;
function len(){
	sz=arrData.wz.slice();
	sz.length=24;
};

//-------------------------------------
//上面的li生成函数
function scWz(){
	for(var i=0;i<arrData.gs;i++){
		$('.wz')[0].innerHTML+='<li></li>';
	}
	
	$('.wz').css('left',(document.body.clientWidth/2)+(10)-($('.wz').width()/2)+'px')
};
function bt(){
	$('.heder').html(arrData.title);
};
//图片地址切换
function img(){
	$('.sl').html(arrData.num);
	$('.img').attr('src',arrData.data+"?sina="+Math.random());
	$('.img').css('background-size','100% 100%');
}
	//下面的html点击总函数
function scWz1(){
	var str='<li></li>';
	for(var i=0;i<24;i++){
		$('.wz1')[0].innerHTML+=str;
	}
	for(var i=0;i<sz.length;i++){
		if(typeof sz[i]=='undefined'){
			sz[i]=randomText();
		}
	}
//数组随机数	
sz.sort(function (){
	return 0.5-Math.random();
});
//随机结果存储到下面的所有html中
for(var i=0;i<sz.length;i++){
		$('.wz1 li')[i].innerHTML=sz[i];
	}
//随机产生汉字
function randomText(){
	var _str = "";
	var _base = 28000;
	var _range = 3599;
	var _lower = parseInt(Math.random() * _range);
	//_str = String.fromCharCode(_base + _lower);
	var _str = game_HZ_char.split('')[_lower];
	return _str;
}
}

ffn();
//整个ffn文件是  包括各种小事件 全部封装到一起直接调用
function ffn(){
	$.ajax({
		url : "${path}/GameQuestion/phoneGetGameQuestion?sina=<%=SmBaseUtil.Random()%>",
		 async: false,
		data : {
			'type' : '${type}'
		},
		success : function(json) {
			if(json.Status==true){
				arrData = new Object();
				arrData.title=json.data.title;
				arrData.data=json.data.imageUrl;
				arrData.wz=json.data.answer.split('');
				arrData.gs=json.data.answer.length;
				arrData.text=json.data.content;
				arrData.rc=json.data.rightCount;
				arrData.fc=json.data.failCount;
				arrData.num=json.data.number;
				arrData.pid=json.data.pkid;
				arrData.type=json.data.type;
				len();
				bt();
				scWz();
				scWz1();
				img();
				initialFun();
			}else{
				if(json.IsLimit){
					alert("需要支付一元解锁下一轮的答题");
				}else{
					alert("已经是最后一题了");
				}
				
			}
			
		}
	});
}
	//点击下面文字，发生相应变化，并且提升到上面的html中
function initialFun(){
	$(".wz1 li").on('touchstart',function (){
		if(b<$('.wz li').length){

				$(this).css('opacity','0');
				var _thisHtml=$(this).html();
				for(var i=0;i<$('.wz li').length;i++){
					if($('.wz li').eq(i).html()==''){
						$('.wz li').eq(i).html(_thisHtml);
						b++;
						break;
					}
				}
			
			
		}
		pd();
		//点击上面小文字  点击那个就把对应的内容清空把下面文字还原
		$(".wz li").click(function (){
			var _thisHtml=$(this).html();
			$('.wz li').css('color','');
			$(this).html('');
			for(var i=0;i<$(".wz1 li").length;i++){
				if($(".wz1 li").eq(i).html()==_thisHtml){
					$('wz li').eq(i).html('');
					$(".wz1 li").eq(i).css('opacity',1);
					b--;
					console.log(b);
					break;
				}
			}
		});

	});
}

	

	//判断所有当前上面文字的内容是不是和数组内部文字相等，如果相等就通关调用fn
	function pd(){
	
		
		var answer="";
		for(var i=0;i<$(".wz li").length;i++){
			answer += $(".wz li").eq(i).html();
		}
		if(answer.length!=arrData.wz.length){
			return false;
		}
		$("#loading").css("display","block");
		$.ajax({
			url : "${path}/GameQuestion/phoneCheckAnswer?sina=<%=SmBaseUtil.Random()%>",
			 
			data : {
				'pid' : arrData.pid,
				'answer' : answer,
			},
			success : function(json) {
				$("#loading").css("display","none");
				if(json.Status==true){
					var str='<div class="zg"></div>';
					var title='<span style="text-align: center;font-size: 14pt;width: 100%;display: block;font-weight: bold;color: #256F03;">恭喜你答对了，一起学学小知识</span>';
					if(json.wemoney>0){
						var newtitle='<span style="text-align: center;font-size: 14pt;width: 100%;display: block;font-weight: bold;color: #FFFFFF;background-color: #256F03;">恭喜你答对'+ json.wemoney +'题，奖励'+ json.wemoney +'微米已到账！继续加油~</span>'
						title = newtitle+title
					}
					var div=$('<div class="next"> <div class="game_comment">'+title+ arrData.text +'</div> <a href="${path}/GameQuestion/phoneGameRank_1?type='+ arrData.type +'" class="game_rank">排行版</a><div class="game_next">下一关</div></div>');
					$('body').append(str);
					$('body').append(div);
					fn();
				}else{
					$('.wz li').css('color','red');
					$('.wz li').animate({'left':'+=30px'},50);
					$('.wz li').animate({'left':'-=60px'},100);
					$('.wz li').animate({'left':'+=30px'},50);
				}
			}
		})
	}


//重置所有变量，删除原来多余的内容
function fn(){
	$('.next').click(function (){
		$('div').remove('.zg');
		$('div').remove('.next');
		$('.wz').empty();
		$('.wz1').empty();
		
		b=0;
		sz=[];
		ffn();
	});
}

</script>
<div id="loading"></div>

<jsp:include page="/include/commonStatistics.jsp"></jsp:include>
</body>
</html>