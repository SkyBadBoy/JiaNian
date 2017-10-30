/**
 * Created by minli on 2017/2/18.
 */
$(document).ready(function(){
	
	setTimeout(function() {
		bindImgError();
	}, 500);
	
	
});

	//登录遮罩层
	$('.zhezhao').width($(window).width());
	$('.zhezhao').height($(window).height());
	$('.qrcord_box').width($(window).width());
	$('.qrcord_box').height($(window).height());
	$(window).resize(function(){
		$('.zhezhao').width($(window).width());
	    $('.zhezhao').height($(window).height());
	    $('.qrcord_box').width($(window).width());
		$('.qrcord_box').height($(window).height());
	});
	$('.switch-close').click(function(){
		$('.zhezhao').hide();
		$('#loginName').val('');
		$('#loginPwd').val('');
	});
	$('.qrcord_box .switch-close').click(function(){
		$('.qrcord_box').hide();
	});
	var movecount = 0;
	$('.sj').click(function() {
		if(movecount == 0) {
			$('.select-loginType .model_login').animate({ left: "300px", opacity: '1' }, 1000);
			$('.select-loginType .model_login').css({ zIndex: '1' });
			movecount = 1;
		} else {
			$('.select-loginType .model_login').animate({ left: "150px", opacity: '0',zIndex: '-1' }, 1000);
			$('#loginName').val('');
		    $('#loginPwd').val('');
		    $('.wrong_model').fadeOut();
			movecount = 0;
		}
	});
	$(document).on('click','.autocomplete-button',function() {
		if($('.autocomplete-input').val()==''){
			popup_msg("请输入搜索内容！");
		}else{
			window.location.href=$("#basePath").val()+"WeNews/Search?query="+encodeURI($('.autocomplete-input').val())
		}
	});
$("a,button,input[type=button],input[type=submit],input[type=file]").on("focus", function() {
		if(this.blur) {
			this.blur();
		}
	});
	$('.wrong_model span').click(function() {
		$('.wrong_model').fadeOut();
	});
	$('.wrong_userinfo span').click(function() {
		$(this).parent('.wrong_userinfo').fadeOut();
	});
	//$('.head_login').click(function() {
	//	$('.model_login').fadeOut();
	//});
	$('.login_btn').click(function() {
		$('.model_login').fadeIn();
		$('#body_bk').show();
		$('#login_bk').show();
	});

	$('#body_bk').click(function() {
		$('.model_login').hide();
		$('#body_bk').hide();
		$('#login_bk').hide();
	});
	$('#login_bk').click(function() {
		$('.model_login').hide();
		$('#body_bk').hide();
		$('#login_bk').hide();
	});
	
	$(document).on('click','.zan',function() {
		console.log("进入点赞");
		var noticeID=$(this).attr("noticeID");
		var thisi=$(this);
		var type=$(this).attr("type");
		var likeCount=$("#likeCount").html();
		$.getJSON("/WeNewsAgency/Notices/likeNotices",
				{
					 nid:noticeID
				},
				function(json) {
						console.log(json);
						if(json.Status=="true"){
							if(type == 0) {
								thisi.attr("type","1");
								thisi.css({
									color: 'red'
								});
								thisi.find('i').html('1');
								thisi.find('span').html(parseInt(thisi.find('span').html()) + 1);
								likeCount++
							} else {
								thisi.attr("type","0");
								thisi.css({
									color: '#808080'
								});
								thisi.find('i').html('0');
								thisi.find('span').html(parseInt(thisi.find('span').html()) - 1);
								likeCount--
							}
							$("#likeCount").html(likeCount)
							popup_msg(json.Message);
						}
				});
	});
//	
	

		$(document).on('click','.toupiao_btn',function() {
		if($("#isLogin").val()!="1"){
			$('.zhezhao').fadeIn();
			return false;
		}
		var i = $(this).parent('.my_toupiao').find('i').html();
		var noticeID=$(this).attr("noticeID");
		var thisi=$(this);
		console.log(i);
		if(i == 0) {
			console.log("正在请求")
			$.getJSON($("#basePath").val()+"Notices/voteNotices",
			{
               	 nid:noticeID,
               	 type:$("#type").val()
			 },function(json){
				 if(json.status==true){
					 	thisi.prev('p').find('span').html(parseInt(thisi.prev('p').find('span').html()) + 1);
						thisi.css({
							background: '#CDCDCD'
						});
						thisi.parent('.my_toupiao').find('i').html('1');
						popup_msg(json.Message);
				 }else{
					//ebToast(json.Message, "top", 1000);
					 popup_msg(json.Message);
					 
				 }
				 
			 });
		}
		
		
	});


	function objInit(obj) {
		return $(obj).html('<option>请选择</option>');
	}
	var arrData = {
		杭州市: {
			下沙区: '杭州市中心实验小学,杭州市第一中心小学',
			滨江区: '杭州市天才小学,杭州市文武小学',
			西湖区: '杭州市天使小学,杭州市元一实验小学',
			拱墅区: '杭州市哈萨克小学,杭州市金山毒霸小学',
			江干区: '杭州市电视剧小学,杭州市狮子小学'
		},
		绍兴市: {
			A区: '绍兴市中心实验小学,绍兴市第一中心小学',
			B区: '绍兴市天才小学,绍兴市文武小学',
			C区: '绍兴市天使小学,绍兴市元一实验小学',
			D区: '绍兴市哈萨克小学,绍兴市金山毒霸小学',
			E区: '绍兴市电视剧小学,绍兴市狮子小学'
		},
		台州市: {
			十一区: '台州市中心实验小学,台州市第一中心小学',
			十二区: '台州市天才小学,台州市文武小学',
			十三区: '台州市天使小学,台州市元一实验小学',
			十四区: '台州市哈萨克小学,台州市金山毒霸小学',
			十五区: '台州市电视剧小学,台州市狮子小学'
		},
	};
	$.each(arrData, function(pF) {
		$('#city').append('<option>' + pF + '</option>');
	});
	$('#city').change(function() {
		objInit('#block');
		objInit('#school');
		$.each(arrData, function(pF, pS) {
			if($('#city option:selected').text() == pF) {
				$.each(pS, function(pT, pC) {
					$('#block').append('<option>' + pT + '</option>');
				});
				$('#block').change(function() {
					objInit('#school');
					$.each(pS, function(pT, pC) {
						if($('#block option:selected').text() == pT) {
							$.each(pC.split(","), function() {
								$('#school').append('<option>' + this + '</option>');
							});
						}
					});

				});
			}
		})
	});
	$('.slide_span').click(function() {
		$(this).next().next('p').slideToggle();
	});
	$('.list_check > li').click(function() {
		var eqi = $(this).index();
		$('.list_check > li').css({
			color: '#9E9E9E'
		})
		$(this).css({
			color: '#333333'
		})
		$('.topic_list').hide();
		$('.topic_list').eq(eqi).show();
	});
	$('.list_check li:eq(0)').click();
	$("#returnTop").click(function() {
		var speed = 200; //滑动的速度
		$('body,html').animate({ scrollTop: 0 }, speed);
		return false;
	});
	//  var win=$(window).height();
	$(window).scroll(function() {
		$('#returnTop').css({
			display: 'block'
		});
		if($(window).scrollTop() == 0) {
			$('#returnTop').css({
				display: 'none'
			});
		}
		if($(window).scrollTop() > 700) {
			$('.match_top_fix').addClass('match_top_fixed');
		}
		if($(window).scrollTop() <= 700) {
			$('.match_top_fix').removeClass('match_top_fixed');
		}
	});
	$(document).on('click','.submit_comment',function() {
		var comment = $(this).prev('input').val();
		var noticeID=$(this).attr("noticeID");
		var basePath=$("#basePath").val();
		var thisi=$(this);
		console.log("this1:"+$(this))
		var type=1;
		if($(this).attr("CommentType")!=""){
			type=$(this).attr("CommentType");
		}
		if(comment != '') {
			$.getJSON(basePath+"Comment/addComment",
			{
				pid:noticeID,
				content:encodeURI(comment),
				type:type
			},function(json){
				if(json.status){
					thisi.after('<div class="comments"><div class="user_tou"><img class="img_100" src="'+json.img+'"> </div> <div class="comment_content"> <a href="">'+json.name+'</a><p>' + comment + '</p> </div> <p>'+json.time+'</p> <div class="clear_float"></div> </div>');
					thisi.prev('input').val('');
					thisi.parents('.comment_detail').prev().prev().find('.comment span').html(parseInt(thisi.parents('.comment_detail').prev().prev().find('.comment span').html()) + 1);
				} 
				popup_msg(json.Message);
			});
		}
	});
	$('input').focus(function() {
		$(this).css({
			color: '#333333'
		});
	});
		$('#loginName').blur(function() {
		doreg('#loginName', 'loginName');
	});
	$('#loginPwd').blur(function() {
		doreg('#loginPwd', 'loginPwd');
	});
   $(document).on('click', '.up_img_container', function() {
		$(this).parent().hide();
		$(this).parents('.one_topic').find('.showimg').show();
		if(!$(this).hasClass('SSR_flag')) {
		    $(this).parents('.one_topic').find('.pgwSlideshow').pgwSlideshow({
					transitionEffect: 'fading',
					autoSlide: false
				});
		    $(this).addClass('SSR_flag');
		    $(this).siblings().addClass('SSR_flag');
		}
	    });
	$(document).on('click','.ps-current ul li',function(){
		$(this).parents('.one_topic').find('.showimg').hide();
		$(this).parents('.one_topic').find('.one_topic_content .up_img_container').show();
	});
	load_line();
	$('.login_btn').click(function() {
		$('#dropdownMenu1').click();
		$('.nav_full .user_login').fadeIn();
		$('#body_bk').show();
		$('#login_bk').show();
	});
	$('#phonenum').bind('input porpertychange', function() {
		var str = $('#phonenum').val();
		if(str.length == 11) {
			$('#btnSendCode').removeAttr('disabled');
			$('#btnSendCode').css({
				background: '#75AFFF'
			});
		} else {
			$('#btnSendCode').attr('disabled', 'disabled');
			$('#btnSendCode').css({
				background: '#C6C6C6'
			});
		}
	});
	$('#phonenum_1').bind('input porpertychange', function() {
		var str = $('#phonenum_1').val();
		if(str.length == 11) {
			$('#btnSendCode_1').removeAttr('disabled');
			$('#btnSendCode_1').css({
				background: '#75AFFF'
			});
		} else {
			$('#btnSendCode_1').attr('disabled', 'disabled');
			$('#btnSendCode_1').css({
				background: '#C6C6C6'
			});
		}
	});
	load_line();
	$('#in_btn1').click(function() {
		var phone=$("#loginName").val();
		var password=$("#loginPwd").val();
		if(!checkPhone(phone)){
			$('#loginName').parents('.model_login').find('.wrong_modelName p').html('手机号格式不正确！');
			$('#loginName').parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$('#loginName').parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 3000);
			return false;
		}
		if(password==""){
			$('#loginPwd').parents('.model_login').find('.wrong_modelPwd p').html('密码不能为空！');
			$('#loginPwd').parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$('#loginPwd').parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 3000);
			return false;
		}

		$.ajax({
			type: "GET", //用POST方式传输
			dataType: "JSON", //数据格式:JSON
			url: $("#basePath").val()+'WeNews/PhoneLogin', //目标地址
			data: "password=" + password + "&Phone=" + phone,
			error: function(XMLHttpRequest, textStatus, errorThrown) {},
			success: function(json) {
				console.log(json);
				if(json.status){
					location.reload();
				}else{
					$('#loginName').parents('.model_login').find('.wrong_modelName p').html(json.message);
					$('#loginName').parents('.model_login').find('.wrong_modelName').fadeIn();
				}
			}
		});
			
	});
$('.model_choose1').click(function() {
		$('#login1').hide();
		$('#login2').show();
	});
	$('.model_choose2').click(function() {
		$('#login2').hide();
		$('#login1').show();
	});
//验证处理
	blurphone('#loginName2');
	blurpwd('#loginPwd2');
	$('#in_btn2').click(function() {
		btn2('#phonelogin', '#codelogin', '#SendCode');
	});
	codechange('#phonelogin', '#SendCode');
	codeblur('#phonelogin', '#SendCode');


	function btn2(str1, str2, str3) {              //修改else
		var str01 = $(str1).val();
		var str02 = $(str2).val();
		var str03 = $(str3).val();
		if(str01 == "") {
			$(str1).parents('.model_login').find('.wrong_modelName p').html('请输入手机号！');
			$(str1).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$('#in_btn2').parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 2000);
		} else if(!checkPhone(str01)) {
			$(str1).parents('.model_login').find('.wrong_modelName p').html('手机号格式不正确！');
			$(str1).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(str1).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 2000);
		} else if(str03 == '获取验证码') {
			$(str1).parents('.model_login').find('.wrong_modelPwd p').html('请获取验证码！');
			$(str1).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(str1).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 2000);
		} else if(str02 == "") {
			$(str1).parents('.model_login').find('.wrong_modelPwd p').html('请输入验证码！');
			$(str1).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(str1).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 2000);
		} else if(!checkCode(str02)) {
			$(str1).parents('.model_login').find('.wrong_modelPwd p').html('验证码错误！');
			$(str1).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(str1).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 2000);
		} else {
			$.ajax({
				type: "GET", //用POST方式传输
				dataType: "JSON", //数据格式:JSON
				url: $("#basePath").val()+'WeNews/PhoneCodeLogin', //目标地址
				data: "code=" + str02 + "&Phone=" + str01,
				error: function(XMLHttpRequest, textStatus, errorThrown) {},
				success: function(json) {
					console.log(json);
					if(json.status){
						if(json.message=="验证成功"){
							location.href = $("#basePath").val()+"WeNews/UserEdit";
						}else{
							location.reload();
						}
						
					}else{
						$('#loginName').parents('.model_login').find('.wrong_modelName p').html(json.message);
						$('#loginName').parents('.model_login').find('.wrong_modelName').fadeIn();
					}
				}
			});
		}
	}

	//***3月24***end***
	
	
	
//	0328
$('.popupDom').height($(window).height());


function change() {
	return false;
}

function load_line() {
	var hot_fulllength = parseInt($('.hot_read').html());
	var counts = $('.alllist tr').length;
	for(var eqsi = 2; eqsi < counts; eqsi++) {
		$('.alllist tr').eq(eqsi).find('.other_line').css({
			width: 115 / hot_fulllength * parseInt($('.alllist tr').eq(eqsi).find('.other_line').find('p').html()) + 'px'
		});
	}
}
function doreg(ele, reg) {
	var vals = $(ele).val();
	var uname = /^[\u4e00-\u9fa5]{2,4}$/;
	var email = /^\w+[@]\w+[.]\w+$/;
	var phone = /^[0-9]{11}$/;
	var pwd = /^[0-9_a-zA-Z]{8,18}$/;
	var repwd = /^[0-9_a-zA-Z]{8,18}$/;
	var loginName = /^[0-9]{11}$/;
	if(reg == 'loginName') {
		if(loginName.test(vals)) {
			$(ele).parents('.model_login').find('.wrong_modelName i').html(1);
		} else if(vals == '') {
			$(ele).parents('.model_login').find('.wrong_modelName p').html('请输入手机号！');
			$(ele).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 3000);
			$(ele).parents('.model_login').find('.wrong_modelName i').html(0);
		} else {
			$(ele).parents('.model_login').find('.wrong_modelName p').html('手机号不正确！');
			$(ele).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 3000);
			$(ele).parents('.model_login').find('.wrong_modelName i').html(0);
		}
	}
	if(reg == 'loginPwd') {
		if(vals == '') {
			$(ele).parents('.model_login').find('.wrong_modelPwd p').html('请输入密码！');
			$(ele).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 3000);
			$(ele).parents('.model_login').find('.wrong_modelPwd i').html(0);
		} else if(vals == 0) {
			$(ele).parents('.model_login').find('.wrong_modelPwd p').html('密码不正确！');
			$(ele).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 3000);
			$(ele).parents('.model_login').find('.wrong_modelPwd i').html(0);
		} else {
			$(ele).parents('.model_login').find('.wrong_modelPwd i').html(1);
		}
	}
}
var InterValObj; //timer变量，控制时间
var count = 30; //间隔函数，1秒执行
var curCount; //当前剩余秒数
var code = ""; //验证码
var codeLength = 6; //验证码长度
function sendMessage(type) {//1是新用户注册处    2是找回密码

	curCount = count;
	var phone=$("#phonelogin").val();
	if(type==null){
		type="3";
	}
	var SendCode=$("#SendCode");
	//设置button效果，开始计时
	//向后台发送处理数据
	$.ajax({
		type: "GET", //用POST方式传输
		dataType: "JSON", //数据格式:JSON
		url: $("#basePath").val()+'Users/getAuthCode', //目标地址
		data: "type=" + type + "&Phone=" + phone,
		error: function(XMLHttpRequest, textStatus, errorThrown) {},
		success: function(json) {
			if(json.Status){
				SendCode.attr("disabled", "true");
				SendCode.val("已发送（" + curCount + "）");
				SendCode.css({
					background: '#C6C6C6'
				});
				InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			}
			popup_msg(json.Message);
		}
	});
}
//timer处理函数
function SetRemainTime() {
	var SendCode=$("#SendCode");
	if(curCount == 0) {
		window.clearInterval(InterValObj); //停止计时器
		
		SendCode.removeAttr("disabled"); //启用按钮
		SendCode.val("重新发送验证码");
		SendCode.css({
			background: '#78AEFF'
		});
		code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
	} else {
		curCount--;
		SendCode.val("已发送（" + curCount + "）");
	}
}

function sendMessage1(type) {//1是新用户注册处    2是找回密码

	curCount = count;
	var phone=$("#phonenum").val();
	if(type==null){
		type="3";
	}
	var SendCode=$("#btnSendCode");
	//设置button效果，开始计时
	//向后台发送处理数据
	$.ajax({
		type: "GET", //用POST方式传输
		dataType: "JSON", //数据格式:JSON
		url: $("#basePath").val()+'Users/getAuthCode', //目标地址
		data: "type=" + type + "&Phone=" + phone,
		error: function(XMLHttpRequest, textStatus, errorThrown) {},
		success: function(json) {
			if(json.Status){
				SendCode.attr("disabled", "true");
				SendCode.val("已发送（" + curCount + "）");
				SendCode.css({
					background: '#C6C6C6'
				});
				InterValObj = window.setInterval(SetRemainTime1, 1000); //启动计时器，1秒执行一次
			}
			popup_msg(json.Message);
		}
	});
}
//timer处理函数
function SetRemainTime1() {
	var SendCode=$("#btnSendCode");

	if(curCount == 0) {
		window.clearInterval(InterValObj); //停止计时器
		
		SendCode.removeAttr("disabled"); //启用按钮
		SendCode.val("重新发送验证码");
		SendCode.css({
			background: '#78AEFF'
		});
		code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
	} else {
		curCount--;
		SendCode.val("已发送（" + curCount + "）");
	}
}
//验证码
var checkCode = function (a) {
	var patrn = /^\d{6}$/;
	if (!patrn.exec(a)) return false;
	return true;
};
var checkEmail = function(a) {
	var patrn = /^[_a-zA-Z0-9\-\.]+@([\-_a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,3}$/;
	if(!patrn.exec(a)) return false;
	return true;
};
var checkPhone = function (a) {
	var patrn = /^((?:13|15|18|14|17)\d{9}|0(?:10|2\d|[3-9]\d{2})[1-9]\d{6,7})$/;
	if (!patrn.exec(a)) return false;
	return true;
};
var checkUsername = function (a) {
	var patrn = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
	if (!patrn.exec(a)) return false;
	return true;
};
var checkPwd = function (a) {
	var patrn = /^.{6,20}$/;
	if (!patrn.exec(a)) return false;
	return true;
};
var checkName=function(a){
	var patrn=/^[\u4e00-\u9fa5]{2,4}$/;
	if(!patrn.exec(a)) return false;
	return true;
}
function popup_msg(msg) {
	 $(".popup").html("" + msg + "");
	  $(".popupDom").show();
	  setTimeout(function() {
	    $(".popupDom").fadeOut();
	  }, 1500);
}




function getNoMoreInfo(){
	return '<div id="NotMore" style="text-align:center;padding-top:25px;">我也是有底线嘀<br/></div>';
}
function getLoading(){
	return '<div class="sk-spinner sk-spinner-fading-circle"><div class="sk-circle1 sk-circle"></div><div class="sk-circle2 sk-circle"></div><div class="sk-circle3 sk-circle"></div><div class="sk-circle4 sk-circle"></div><div class="sk-circle5 sk-circle"></div><div class="sk-circle6 sk-circle"></div><div class="sk-circle7 sk-circle"></div><div class="sk-circle8 sk-circle"></div><div class="sk-circle9 sk-circle"></div><div class="sk-circle10 sk-circle"></div><div class="sk-circle11 sk-circle"></div><div class="sk-circle12 sk-circle"></div></div>';
}

//浏览器视口的高度
function getWindowHeight(){
	var windowHeight = 0;
	if(document.compatMode == "CSS1Compat"){
		windowHeight = document.documentElement.clientHeight;
	}else{
		windowHeight = document.body.clientHeight;
	}
	return windowHeight;
}
function getScrollTop(){
	var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
	if(document.body){
		bodyScrollTop = document.body.scrollTop;
	}
	if(document.documentElement){
		documentScrollTop = document.documentElement.scrollTop;	
	}
	scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
	return scrollTop;
}
//文档的总高度
function getScrollHeight(){
	var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
	if(document.body){
		bodyScrollHeight = document.body.scrollHeight;
	}
	if(document.documentElement){
		documentScrollHeight = document.documentElement.scrollHeight;
	}
	scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
	return scrollHeight;
}
function bindImgError(){

	$("img").error(function () {
		if ( typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) { 
	    	//http://wenews.top/WeNewsAgency/img/tx_default.jpg
	    	if($(this).attr("class")=='img_100'){
	    		$(this).unbind("error").attr("src", $("#basePath").val()+"img/tx_default.jpg"); 
	    	}else{
	    		$(this).unbind("error").attr("src", $("#basePath").val()+"img/errorpic.jpg");
	    	}
	    	
	      } 
	    
	});

}


function AddNoMoreDOM(obj){
	$(".sk-spinner").remove();
	if($("#NotMore").length<=0){
		if(obj!=null){
			obj.append(getNoMoreInfo())
		}else{
			$("#container").append(getNoMoreInfo()); 
		}
	}
}

$(document).on('click', '.ps-current ul li', function() {
	$(this).parents('.one_topic').find('.showimg').hide();
	$(this).parents('.one_topic').find('.one_topic_content > div').show();
});




function blurphone(ele) {
	$(ele).blur(function() {
		var str = $(ele).val();
		if(str == "") {
			$(ele).parents('.model_login').find('.wrong_modelName p').html('请输入用户名！');
			$(ele).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 2000);
		} else if(!checkPhone(str)) {
			$(ele).parents('.model_login').find('.wrong_modelName p').html('用户名不正确！');
			$(ele).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 2000);
		}
	});
}

function blurpwd(ele) {
	$(ele).blur(function() {
		var str = $(ele).val();
		if(str == "") {
			$(ele).parents('.model_login').find('.wrong_modelPwd p').html('请输入密码！');
			$(ele).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 2000);
		} else if(!checkPwd(str)) {
			$(ele).parents('.model_login').find('.wrong_modelPwd p').html('密码不正确！');
			$(ele).parents('.model_login').find('.wrong_modelPwd').fadeIn();
			setTimeout(function() {
				$(ele).parents('.model_login').find('.wrong_modelPwd').fadeOut();
			}, 2000);
		}

	});
}


function codechange(a, b) {
	$(a).bind('input porpertychange', function() {
		var phonelogin = $(a).val();
		if(checkPhone(phonelogin)) {
			if($(b).val() == '获取验证码' || $(b).val() == '重新发送验证码') {
				$(b).removeAttr('disabled');
				$(b).css({
					background: '#78AEFF'
				});
			} else {
				$(b).attr('disabled', 'disabled');
				$(b).css({
					background: '#C6C6C6'
				});
			}
		} else {
			$(b).attr('disabled', 'disabled');
			$(b).css({
				background: '#C6C6C6'
			});
		}
	});
}

function codeblur(a, b) {
	$(a).blur(function() {
		var phonelogin = $(a).val();
		if(checkPhone(phonelogin)) {
			if($(b).val() == '获取验证码' || $(b).val() == '重新发送验证码') {
				$(b).removeAttr('disabled');
				$(b).css({
					background: '#78AEFF'
				});
			} else {
				$(b).attr('disabled', 'disabled');
				$(b).css({
					background: '#C6C6C6'
				});
			}
		} else if(phonelogin == "") {
			$(a).parents('.model_login').find('.wrong_modelName p').html('请输入手机号！');
			$(a).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(a).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 2000);
			$(b).attr('disabled', 'disabled');
			$(b).css({
				background: '#C6C6C6'
			});
		} else {
			$(a).parents('.model_login').find('.wrong_modelName p').html('手机号格式不正确！');
			$(a).parents('.model_login').find('.wrong_modelName').fadeIn();
			setTimeout(function() {
				$(a).parents('.model_login').find('.wrong_modelName').fadeOut();
			}, 2000);
			$(b).attr('disabled', 'disabled');
			$(b).css({
				background: '#C6C6C6'
			});
		}
	});
}

	
	//个人设置按钮
	$('#userSetting').click(function(){
		$(this).find('ul').fadeToggle();
	});
	//个人设置按钮
	$('.RankSetting1').click(function(){
		$(this).find('ul').fadeToggle();
	});
	
	$(document).on('click','.toupiao',function() {
		var pkid = $($(this).next('.my_toupiao').find("img")).attr("pkid");
		var type =$($(this).next('.my_toupiao').find("img")).attr("type");
		var currentObj=$(this);
		$(currentObj.next('.my_toupiao').find("img")).attr("src","");
		 $.getJSON($("#basePath").val()+"WeNews/phoneGetToupiaoQr?rand="+  Math.random() +"&pkid="+ pkid +"&type="+ type,
				function(json){ 
			 		$(currentObj.next('.my_toupiao').find("img")).attr("src",json.data)
			 		currentObj.next('.my_toupiao').fadeToggle();
				 })
		
	});
	$(document).on('click','.qrdiv',function() {
		$(".qrcord_box .qrdiv_user").html("");
		makeCode($(".qrcord_box .qrdiv_user"),$(this).attr("pkid"),$(this).attr("type"));
		$('.qrcord_box').fadeIn();
	});
	
	
	
	function addVideoNewsDom(array){
		var html="";
		var img = $("#basePath").val()+'img/tx_default.jpg';
	 	var likeHtml="";
		if (array.isLike==0) {
				likeHtml='<a class="zan" href="javascript:;"  noticeID="'+array.pkid+'" type="0">点赞<i>0</i>（<span>'+array.likeCount+'</span>）</a>'
		}else{
			likeHtml='<a class="zan" href="javascript:;" style="color: red;" noticeID="'+array.pkid+'" type="1">取消赞<i>0</i>（<span>'+array.likeCount+'</span>）</a>'
		}
		if(array.student!=null){
			img=array.student.imageUrl.split(',')[0];
		}
		 	
		 	var comment="";
			if (array.comment!=null && array.comment.length>0) {
				for (var i = 0; i < array.comment.length; i++) {
					var imgc="";
					var name="游客";
	   		 	if (array.comment[i].student!=null) {
	   		 		imgc=array.comment[i].student.imageUrl.split(',')[0]
					name=array.comment[i].student.name;
				}else{
					imgc=$("#basePath").val()+'img/tx_default.jpg';
				}
					comment+=' <div class="user_tou">';
					comment+=' <a href="'+ $("#basePath").val() +'WeNews/User?Uid='+array.comment[i].userID+'"><img class="img_100" src="'+imgc +'"></a>';
					comment+=' </div>';
					comment+=' <div class="comment_content">';
					comment+=' <a href="'+ $("#basePath").val() +'WeNews/User?Uid='+array.comment[i].userID+'">'+name+'</a>';
					comment+=' <p>&nbsp;&nbsp;&nbsp;&nbsp;'+array.comment[i].fromtTime+'</p>';
					comment+=' <p style="    padding: 5px;">'+array.comment[i].content +'</p>';
					comment+=' </div>';
					
					
					comment+=' <div class="clear_float"></div>';
			}
		};
	 	
		var isActivityHtml="";
		if (array.isActivity==1) {
			var voteHtml="";
			if(array.isVote==0){
				voteHtml='<button noticeID="'+array.pkid+'" class="toupiao_btn" type="button">投票</button>';
			}else{
				voteHtml='<button style="background:#CDCDCD ;border-radius: 4px; width: 38px;height: 20px;font-size: 12px;color: white;border: none; margin-top: 4px;cursor: pointer;" type="button">已投</button>';
			}
			
			isActivityHtml+='							&nbsp;|&nbsp;<a class="toupiao" href="javascript:;">我要投票</a>'
			isActivityHtml+='								<div class="my_toupiao">'
			isActivityHtml+='									<div class="toupiao_user"><img class="img_100" src="'+img+'"></div>'
			isActivityHtml+='									<p>给我投票吧~<i>0</i></p>'
			isActivityHtml+='								<p><span>'+array.voteCount+'</span>票</p>'
			isActivityHtml+=voteHtml
			isActivityHtml+='								</div>'
		}
			var isQRHtml="";
			isQRHtml+='							&nbsp;|&nbsp;<a class="qrdiv" type="1" pkid="'+ array.pkid +'"  href="javascript:;">分享到微信</a>';
		
			
		 	
		
		 	html+='	<div class="one_topic">'
		html+='	<div class="user_tou">'
			if(array.student!=null){
				html+='		<a href="'+ $("#basePath").val() +'WeNews/User?Uid='+ array.student.pkid +'"><img class="img_100" src="'+img+'"></a>'
			}else{
				html+='		<img class="img_100" src="WeNewsAgency/img/schooldefault.png">'
			}
		
		html+='	</div>'
		html+='	<div class="user_info">'
		html+='		<p>'
		html+='			<a href="'+ $("#basePath").val() +'WeNews/VideoDetails?VideoID='+ array.pkid +'" class="titleOverHide">'+array.title+'</a>'
		html+='		</p>'
		html+='		<p>'+array.areaName +'</p>'
		html+='<div class="clear_float"></div>'
		html+='		<p><span >'+array.createTime.substring(0,19) +'</span><span class="titleOverHide1">'+array.author+'</span> </p>'
		html+='	</div>'
		html+='	<div class="clear_float"></div>'
		html+='	<div class="one_topic_content">'
	
		html+='<div style="width:400px;height: 200px;	">'
		var vid=array.vid;
		if(array.type!=0 && array.type!="0"){
			vid = array.url;
		}
		html+='<div id="'+ array.pkid +'" vid="'+ vid +'" videotype="'+ array.type +'" name="div_videoList" style="width:400px;height: 200px;	"></div>'
		html+='	</div>'
		html+='		<div class="video_p" style="word-wrap:break-word">'+array.content +'</div>'
		html+='		<div class="clear_float"></div>'
		html+='	</div>'
		html+='	<div class="content_detail">'
		html+='		<p>阅读：'+array.clickCount+'</p>'
		html+='		<div class="do_action">'
		
		html+=likeHtml;
			
		html+='			<a class="comment" href="javascript:void(0);" onclick="loadCommentList(this,\''+ array.pkid +'\');">评论（<span>'+array.commentCount+'</span>）<i><img src="'+ $("#basePath").val() +'images/wenewspc/tr.png"></i></a>'
		html+=isActivityHtml
		html+=isQRHtml;
		html+='		<div class="clear_float"></div>'
		html+='	</div>';
		html+='		<div class="comment_detail">'
		html+='			<form onSubmit="return change();">'
		html+='				<input type="text" placeholder="给我点评价吧~">'
		html+='				<input type="submit" class="submit_comment" noticeID="'+array.pkid+'" value="发表评论">'
		html+='			</form>'
		html+='			<div class="comments">'
		html+=comment
		html+='				<div class="clear_float"></div>'
		html+='				<div class="comment_more">'
		html+='					<a href="'+ $("#basePath").val() +'WeNews/NewsDetails?NoticeID='+array.pkid +'">查看更多></a>'
		html+='				</div>'
		html+='				<div class="clear_float"></div>'
		html+='			</div>'
		html+='		</div>'
		
		html+='</div>'
		html+='<div class="clear_float"></div>'	;                    	
		 	html+='</div>'
		 	return html;
		
	}

	function addNewsDom(array){
		var html="";
		var image="";
			var imageLun="";
		 	if (array.pictures.length>0) {
			for (var i = 0; i < array.pictures.length; i++) {
					image+='				<div class="up_img_container">'
	    			image+='					<img src="'+array.pictures[i].url.split(",")[0] +'">'
	    			image+='				</div>'
	    			imageLun+='<li><img src="'+array.pictures[i].url.split(",")[0]+'"></li>'
			}
		}
		 	var img=array.student.imageUrl.split(',')[0];

		 	var likeHtml="";
			if (array.isLike==0) {
				likeHtml='<a class="zan" href="javascript:;"  noticeID="'+array.pkid+'" type="0">点赞<i>0</i>（<span>'+array.like+'</span>）</a>'
		}else{
			likeHtml='<a class="zan" href="javascript:;" style="color: red;" noticeID="'+array.pkid+'" type="1">取消赞<i>0</i>（<span>'+array.like+'</span>）</a>'
		}
		 	
		 	var comment="";
			if (array.comment!=null && array.comment.length>0) {
				for (var i = 0; i < array.comment.length; i++) {
					var imgc="";
					var name="游客";
	   		 	if (array.comment[i].student!=null) {
	   		 		imgc=array.comment[i].student.imageUrl.split(',')[0]
					name=array.comment[i].student.name;
				}else{
					imgc=$("#basePath").val()+'img/tx_default.jpg';
				}
					comment+=' <div class="user_tou">';
					comment+=' <a href="'+ $("#basePath").val() +'WeNews/User?Uid='+array.comment[i].userID+'"><img class="img_100" src="'+imgc +'"></a>';
					comment+=' </div>';
					comment+=' <div class="comment_content">';
					comment+=' <a href="'+ $("#basePath").val() +'WeNews/User?Uid='+array.comment[i].userID+'">'+name+'</a>';
					comment+=' <p>&nbsp;&nbsp;&nbsp;&nbsp;'+array.comment[i].fromtTime+'</p>';
					comment+=' <p style="    padding: 5px;">'+array.comment[i].content +'</p>';
					comment+=' </div>';
					
					
					comment+=' <div class="clear_float"></div>';
			}
		};
	 	
		var isActivityHtml="";
		if (array.isActivity==1) {
			var voteHtml="";
			
			
			isActivityHtml+='							&nbsp;|&nbsp;<a class="toupiao" href="javascript:;">我要投票</a>'
			isActivityHtml+='								<div class="my_toupiao">'
			isActivityHtml+='									<div class="toupiao_user"><img pkid="'+ array.pkid +'" type="0"  style="width: 100px;height: 100px;" class="img_100" src="'+img+'"></div>'
			isActivityHtml+='									<p>扫一扫给我投票吧<i>0</i></p>'
			isActivityHtml+='								<p><span>'+array.voteCount+'</span>票</p>'
			isActivityHtml+=voteHtml
			isActivityHtml+='								</div>'
		}
		var isQRHtml="";

			
			
			isQRHtml+='							&nbsp;|&nbsp;<a class="qrdiv" pkid="'+ array.pkid +'" href="javascript:;">分享到微信</a>';

		
		var voteid=879990030779944960;
		var bgm='style="background: url('+ $("#basePath").val()  +'images/wenewspc/head.jpg);background-repeat: no-repeat;"';
		if(array.voteID==voteid){
			html+='	<div style="background: url(http://wenews.top:80/WeNewsAgency/images/wenewspc/line.jpg);"><div class="one_topic" '+ bgm +'>'
		}else{
			html+='	<div class="one_topic">'
		}
		
		html+='	<div class="user_tou">'
		html+='		<a href="'+ $("#basePath").val() +'WeNews/User?Uid='+ array.student.pkid +'"><img class="img_100" src="'+img+'"></a>'
		html+='	</div>'
		html+='	<div class="user_info">'
		html+='		<p>'
		html+='			<a href="'+ $("#basePath").val() +'WeNews/NewsDetails?NoticeID='+ array.pkid +'" class="titleOverHide">'+array.title+'</a>'
		html+='		</p>'
		html+='		<p><a href="'+ $("#basePath").val() +'WeNews/SchoolInfo?SchoolID='+ array.areaID +'">'+array.sregion.name +'</a></p>'
		html+='<div class="clear_float"></div>'
		html+='		<p><span >'+array.createTime.substring(0,19) +'</span><span class="titleOverHide1">'+array.author+'</span> </p>'
		html+='	</div>'
		html+='	<div class="clear_float"></div>'
		html+='	<div class="one_topic_content">'
		html+='		<p>'+array.content +'</p>'
		html+='		<div style="width:442px;">'
		html+=			image	
		html+='		</div>'
		html+='		<div class="clear_float"></div>'
		html+='	</div>'
		html+='	<div class="showimg">'
		html+='		<div class="jq22-content bgcolor-3">'
		html+='			<ul class="pgwSlideshow">'
		html+=imageLun
		html+='			</ul>'
		html+='		</div>'
		html+='	</div>';
		
		if(array.voteID==voteid){
			html+='	<div class="content_detail" style="background: #e6f2da">';
			html+='		<p style="color: #3c763d;">阅读：'+array.clickCount+'</p>';
		}else{
			html+='	<div class="content_detail">';
			html+='		<p>阅读：'+array.clickCount+'</p>';
		}	
		
		
		html+='		<div class="do_action">';
		
		html+=likeHtml;
			
		html+='			<a class="comment" href="javascript:void(0);" onclick="loadCommentList(this,\''+ array.pkid +'\');">评论（<span>'+array.commentCount+'</span>）<i><img src="'+ $("#basePath").val() +'images/wenewspc/tr.png"></i></a>'
		html+=isActivityHtml
		html+=isQRHtml;
		html+='		<div class="clear_float"></div>'
		html+='	</div>';
		html+='		<div class="comment_detail">'
		html+='			<form onSubmit="return change();">'
		html+='				<input type="text" placeholder="给我点评价吧~">'
		html+='				<input type="submit" class="submit_comment" noticeID="'+array.pkid+'" value="发表评论">'
		html+='			</form>'
		html+='			<div class="comments">'
		html+=comment
		html+='				<div class="clear_float"></div>'
		html+='				<div class="comment_more">'
		html+='					<a href="'+ $("#basePath").val() +'WeNews/NewsDetails?NoticeID='+array.pkid +'">查看更多></a>'
		html+='				</div>'
		html+='				<div class="clear_float"></div>'
		html+='			</div>'
		html+='		</div>'
		
		html+='</div>'
		html+='<div class="clear_float"></div>'	;                    	
	 	html+='</div>'
 		if(array.voteID==voteid){
 			html+='</div>'
 		}
		 	return html;
		
	}
	
	
	
	function loadCommentList(obj,nid,type){
		var currentobj=$(obj).parent().next('.comment_detail');
		currentobj.toggle();
		currentobj.find("comments .sk-spinner").remove();
		currentobj.find("#NotMore").remove(); 
		currentobj.find(".comments").append(getLoading());
			 $.getJSON( $("#basePath").val()+"Comment/getCommentList",
				{
				 pageNumber:1,
					 nid:nid,
				 pageSize:10,
				 submitType:'phone'
				 },function(json){ 
	                if(json && json.Status==1){ 
	                	
	                	var tmp="";
	                    $.each(json.CommentData,function(index,array){ 

	                    	 tmp+='<div class="user_tou">';
	                    	 var imgc=$("#basePath").val()+'img/tx_default.jpg';
	                    	 if(array.userImage!=null && array.userImage!=""){
	                    		 imgc= array.userImage.split(',')[0];
	                    	 }
	                    	 if(array.userID==0){
	                    		 tmp+='<a ><img class="img_100" src="'+imgc+'"></a>';
	                    	 }else{
	                    		 tmp+='<a href="'+$("#basePath").val()+'WeNews/User?Uid='+array.userID+'"><img class="img_100" src="'+imgc+'"></a>';
	                    	 }
	                    	 tmp+='</div><div class="comment_content">';
	                    	 if(array.userID==0){
	                    		 tmp+='<a >'+array.userName+'</a>';
	                    	 }else{
	                    		 tmp+='<a href="'+$("#basePath").val()+'WeNews/User?Uid='+array.userID+'">'+array.userName+'</a>';
	                    	 }
	                    	 tmp+='<p>&nbsp;&nbsp;&nbsp;&nbsp;'+array.fromtTime+'</p>';
	                    	 tmp+='<p style="padding:5px">'+array.content+'</p></div>';
	                    	 
	                    	 tmp+='<div class="clear_float"></div>';
						
	                    }); 
	                    var moreinfo="";
	                    if(type!=1){
	                    	moreinfo+='				<div class="comment_more">';
	                    	moreinfo+='					<a href="'+ $("#basePath").val() +'WeNews/NewsDetails?NoticeID='+nid +'">查看更多></a>';
	                    	moreinfo+='				</div>';
	                    	moreinfo+='				<div class="clear_float"></div>';
	                    }
	                    currentobj.find(".sk-spinner").remove();
	                    currentobj.find(".comments").html(tmp); 
	                    currentobj.find(".comments").append(getNoMoreInfo());
	                    currentobj.find(".comments").append(moreinfo);
	                    
	            		
	                    	                   

	                }else{ 
	                	currentobj.find(".sk-spinner").remove();	
	                	return false; 
	                }
	                bindImgError();
	            }); 
		
	}
	function makeCode(obj,ID,type) {	
		if(obj.find("img").length<=0){
			var qrcode = new QRCode(obj.get(0), {
				width : 280,
				height : 280
			});
			if(type==1){//type=1表示视频链接
				qrcode.makeCode($("#basePath").val()+"Product/phoneweChatPordDetail?_type_=3&_pid_="+ ID +"&_pc_=&_area_=&sina="+Math.random());
			}else{
				qrcode.makeCode($("#basePath").val()+"Product/phoneweChatPordDetail?_type_=2&_pid_="+ ID +"&_pc_=&_area_=&_status_=1&sina="+Math.random());
			}
			
			obj.find("img").attr("title","微信扫一扫，分享到朋友圈");
		}
		
	}

	//makeCode();
	
	
	