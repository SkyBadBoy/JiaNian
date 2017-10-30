$(function() {
	//登录遮罩层
	$('.zhezhao').width($(window).width());
	$('.zhezhao').height($(window).height());
	$(window).resize(function() {
		$('.zhezhao').width($(window).width());
		$('.zhezhao').height($(window).height());
	});
	//点击×模态框隐藏
	$('.switch-close').click(function() {
		$('.zhezhao').hide();
		$('.select-loginType .model_login').animate({ left: "150px", opacity: '0', zIndex: '-1' }, 1000); //新增
		$('#loginName').val('');
		$('#loginPwd').val('');
	});
	var movecount = 0;
	$('.sj').click(function() {
		if(movecount == 0) {
			$('.select-loginType .model_login').animate({ left: "300px", opacity: '1' }, 1000);
			$('.select-loginType .model_login').css({ zIndex: '1' });
			movecount = 1;
		} else {
			$('.select-loginType .model_login').animate({ left: "150px", opacity: '0', zIndex: '-1' }, 1000);
			$('#loginName').val('');
			$('#loginPwd').val('');
			$('.wrong_model').fadeOut();
			movecount = 0;
		}
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
	$('#in_btn1').click(function() {
		btn1('#loginName2', '#loginPwd2');
	});
	$('#in_btn2').click(function() {
		btn2('#phonelogin', '#codelogin', '#SendCode');
	});
	codechange('#phonelogin', '#SendCode');
	codeblur('#phonelogin', '#SendCode');
});
var InterValObj; //timer变量，控制时间
var InterValObj2;
var count = 30; //间隔函数，1秒执行
var count2 = 30;
var curCount; //当前剩余秒数
var curCount2;
var code = ""; //验证码
var codeLength = 4; //验证码长度
function sendMessage(inputid, btnid) {
	if(btnid == "#btnSendCode2") {
		curCount2 = count2;
	} else {
		curCount = count;
	}
	var dealType; //验证方式
	var uid = $("#uid").val(); //用户uid
	dealType = "phone";
	//产生验证码
	for(var i = 0; i < codeLength; i++) {
		code += parseInt(Math.random() * 9).toString();
	}

	//设置button效果，开始计时
	$(btnid).css({
		background: '#C6C6C6'
	});
	$(btnid).attr("disabled", "true");
	if(btnid == "#btnSendCode2") {
		$(btnid).val("已发送（" + curCount2 + "）");
		InterValObj2 = window.setInterval(function() {
			SetRemainTime(btnid);
		}, 1000); //启动计时器，1秒执行一次
	} else {
		$(btnid).val("已发送（" + curCount + "）");
		InterValObj = window.setInterval(function() {
			SetRemainTime(btnid);
		}, 1000); //启动计时器，1秒执行一次
	}
	//向后台发送处理数据
	
	$.ajax({
		type: "GET", //用POST方式传输
		dataType: "JSON", //数据格式:JSON
		url: $("#basePath").val()+'Users/getAuthCode', //目标地址
		data: "type=" + 3 + "&Phone=" + $("#phonelogin").val(),
		error: function(XMLHttpRequest, textStatus, errorThrown) {},
		success: function(json) {
			popup_msg(json.Message);
		}
	});
	
}
//timer处理函数
function SetRemainTime(btnid) {
	if(btnid == "#btnSendCode2") {
		if(curCount2 == 0) {
			window.clearInterval(InterValObj2); //停止计时器
			$(btnid).removeAttr("disabled"); //启用按钮
			$(btnid).val("重新发送验证码");
			$(btnid).css({
				background: '#78AEFF'
			});
			code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
		} else {
			curCount2--;
			$(btnid).val("已发送（" + curCount2 + "）");
		}
	} else {
		if(curCount == 0) {
			window.clearInterval(InterValObj); //停止计时器
			$(btnid).removeAttr("disabled"); //启用按钮
			$(btnid).val("重新发送验证码");
			$(btnid).css({
				background: '#78AEFF'
			});
			code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
		} else {
			curCount--;
			$(btnid).val("已发送（" + curCount + "）");
		}
	}
}
//验证码
var checkCode = function(a) {
	var patrn = /^\d{4}$/;
	if(!patrn.exec(a)) return false;
	return true;
};
//邮箱
var checkEmail = function(a) {
	var patrn = /^[_a-zA-Z0-9\-\.]+@([\-_a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,3}$/;
	if(!patrn.exec(a)) return false;
	return true;
};
var checkPhone = function(a) {
	var patrn = /^((?:13|15|18|14|17)\d{9}|0(?:10|2\d|[3-9]\d{2})[1-9]\d{6,7})$/;
	if(!patrn.exec(a)) return false;
	return true;
};
var checkUsername = function(a) {
	var patrn = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
	if(!patrn.exec(a)) return false;
	return true;
};
var checkPwd = function(a) {
	var patrn = /^.{6,20}$/;
	if(!patrn.exec(a)) return false;
	return true;
};

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

function btn1(str1, str2) {
	var user_name = $(str1).val();
	var user_pwd = $(str2).val();
	if(checkPhone(user_name) && checkPwd(user_pwd)) {
		$.ajax({
			type: "GET", //用POST方式传输
			dataType: "JSON", //数据格式:JSON
			url: $("#basePath").val()+'WeNews/PhoneLogin', //目标地址
			data: "password=" + user_pwd + "&Phone=" + user_name,
			error: function(XMLHttpRequest, textStatus, errorThrown) {},
			success: function(json) {
				console.log(json);
				if(json.status){
					location.href = $("#basePath").val()+'WeNewsHome';
				}
				popup_msg(json.message);
			}
		});
		
	} else if(user_name == "") {
		$(str1).parents('.model_login').find('.wrong_modelName p').html('请输入手机号！');
		$(str1).parents('.model_login').find('.wrong_modelName').fadeIn();
		setTimeout(function() {
			$(str1).parents('.model_login').find('.wrong_modelName').fadeOut();
		}, 2000);
	} else if(!checkPhone(user_name)) {
		$(str1).parents('.model_login').find('.wrong_modelName p').html('手机号格式不正确！');
		$(str1).parents('.model_login').find('.wrong_modelName').fadeIn();
		setTimeout(function() {
			$(str1).parents('.model_login').find('.wrong_modelName').fadeOut();
		}, 2000);
	} else if(user_pwd == "") {
		$(str2).parents('.model_login').find('.wrong_modelPwd p').html('请输入密码！');
		$(str2).parents('.model_login').find('.wrong_modelPwd').fadeIn();
		setTimeout(function() {
			$(str2).parents('.model_login').find('.wrong_modelPwd').fadeOut();
		}, 2000);
	} else if(!checkPwd(user_pwd)) {
		$(str2).parents('.model_login').find('.wrong_modelPwd p').html('密码错误！');
		$(str2).parents('.model_login').find('.wrong_modelPwd').fadeIn();
		setTimeout(function() {
			$(str2).parents('.model_login').find('.wrong_modelPwd').fadeOut();
		}, 2000);
	}
}

function btn2(str1, str2, str3) {
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
					window.location.href = $("#basePath").val()+'WeNewsHome';
				}
				popup_msg(json.message);
			}
		});
	}
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