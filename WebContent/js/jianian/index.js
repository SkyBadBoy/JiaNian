$(function() {
	//退出登录
	$('#layout').click(function() {
		window.location = 'login.html';
	});
	//课程表周数定位
	$(window).scroll(function(){
		if($(window).scrollTop()>15){
			$('.week-list').css({
				top:'0'
			});
			$('.schedule-list').css({
				marginTop:'0px'
			});
		}else{
			$('.week-list').css({
				top:'60px'
			});
			$('.schedule-list').css({
				marginTop:'35px'
			});
		}
	});

	//选择金额
	$('.money li').click(function() {
		$('#my_money').val('');
		$('.money li').removeClass('money_checked');
		$(this).addClass('money_checked');
	});
	$('#my_money').focus(function() {
		$('.money li').removeClass('money_checked');
	});
	$('#my_money').blur(function() {
		var a = $('#my_money').val();
		if(checkMoney(a)) {
			$('.money li').removeClass('money_checked');
		} else if(a == '') {

		} else {
			popup_msg('金额输入无效！');
		}
	});

	$('.popupDom ').height($(window).height);

	//在线留言验证
	$('#send_btn').click(function() {
		var title = $('#contactTitle').val();
		var content = $('#contactMessage').val();
		if(title == '') {
			popup_msg('请输入内容简述！');
		} else {
			if(content == '') {
				popup_msg('请输入内容详情！');
			} else {
				popup_msg('留言发送成功！');
			}
		}
	});
	//充值验证
	$('#cz_btn').click(function() {
		var l = $('.money_checked').length;
		var a = $('#my_money').val();
		var num = $('#cz_number').val();
		if(checkNum(num)) {
			if(l == 1) {
				var val = parseInt($('.money_checked').find('span').html());
				popup_msg('成功充值' + val + '元！');
			} else if(l == 0) {
				if(a == '') {
					popup_msg('请选择充值金额！');
				} else if(!checkMoney(a)) {
					popup_msg('金额输入无效！');
				} else {
					popup_msg('成功充值' + a + '元！');
				}
			}
		} else if(num == '') {
			popup_msg('请输入学号！');
		} else {
			popup_msg('该学号不存在！');
		}
	});
	//	修改密码
	$('#changepwd_btn').click(function() {
		var pwd1 = $('#old_pwd').val();
		var pwd2 = $('#new_pwd').val();
		var pwd3 = $('#re_pwd').val();
		if(checkPwd(pwd1)) {
			if(checkPwd(pwd2)) {
				if(pwd3 == pwd2) {
					popup_msg('密码修改成功！');
				} else if(pwd3 == '') {
					popup_msg('请输入确认密码！');
				} else {
					popup_msg('两次密码输入不一致！');
				}
			} else if(pwd2 == '') {
				popup_msg('请输入新密码！');
			} else {
				popup_msg('新密码格式不正确！');
			}
		} else if(pwd1 == '') {
			popup_msg('请输入原密码！');
		} else {
			popup_msg('原密码输入错误！');
		}
	});
	//登录提交
	$('#login_btn').click(function(e) {
		var num = $('#login_number').val();
		var pwd = $('#login_pwd').val();
		if(checkNum(num)) {
			if(checkPwd(pwd)) {
				saveInfo();
				window.location = "index.html";
			} else if(pwd == '') {
				popup_msg('请输入密码！');
			} else {
				e.preventDefault();
				popup_msg('密码错误！');
			}
		} else if(num == '') {
			popup_msg('请输入学号！');
		} else {
			popup_msg('学号不存在！');
			e.preventDefault();
		}
	});
	//找回密码
	$('#findpwd_btn').click(function() {
		var num = $('#find_num').val();
		var per = $('#find_per').val();
		var pwd1 = $('#new_pwd').val();
		var pwd2 = $('#renew_pwd').val();
		if(checkNum(num)) {
			if(checkPerson(per)) {
				if(checkPwd(pwd1)) {
					if(checkPwd(pwd2) && (pwd1 == pwd2)) {
						popup_msg('密码修改成功！');
						window.location='login.html';
					}else if(pwd2==''){
						popup_msg('请输入确认密码！');
					}else if(pwd2!=pwd1){
						popup_msg('两次密码输入不一致！');
					}
				} else if(pwd1 == '') {
					popup_msg('请输入新密码！');
				} else {
					popup_msg('新密码格式错误！');
				}
			}else if(per==''){
				popup_msg('请输入身份证号！');
			}else{
				popup_msg('身份证号格式错误！');
			}
		}else if(num==''){
			popup_msg('请输入学工号！');
		}else{
			popup_msg('学工号不存在！');
		}
	});
	//	请假提交验证
	$('#qingjia_btn').click(function() {
		var uname = $('#qj_uname').val();
		var num = $('#qj_number').val();
		var classname = $('#qj_class').val();
		var phone = $('#qj_phone').val();
		var stardate = $('#dateinput').val();
		var stopdate = $('#dateinput2').val();
		var reason = $('#qj_reason').html();
		var str_inputfile_delay = $(".inputfile_delay").html();
		if(checkName(uname)) {
			if(checkNum(num)) {
				if(classname != '') {
					if(checkPhone(phone)) {
						if(stardate != '') {
							if(stopdate != '') {
								if (str_inputfile_delay == "请上传附件(100M以内)" ) {
									popup_msg('请上传附件');
								} else {
									if(reason != '') {
										popup_msg('请假申请成功，等待审核！');
									} else {
										popup_msg('请填写请假原因！')
									}
								}
							} else {
								popup_msg('请输入请假结束时间！');
							}
						} else {
							popup_msg('请输入请假开始时间！');
						}
					} else if(phone == '') {
						popup_msg('请输入紧急联系号码！');
					} else {
						popup_msg('号码格式不正确！');
					}
				} else {
					popup_msg('请输入所在班级！');
				}
			} else if(num == '') {
				popup_msg('请输入学号！');
			} else {
				popup_msg('学号格式错误！');
			}
		} else if(uname == '') {
			popup_msg('请输入姓名！');
		} else {
			popup_msg('姓名格式错误！');
		}
	});

	//	作业提交验证
	$('#homework_btn').click(function() {
		var uname = $('#qj_uname').val();
		var num = $('#qj_number').val();
		var classname = $('#qj_class').val();
		var str_inputfile_span = $(".inputfile_span").html();
		if(checkName(uname)) {
			if(checkNum(num)) {
				if(classname != '') {
					if (str_inputfile_span == "请上传附件(100M以内)" ) {
						popup_msg('请上传附件');
					} else {
						popup_msg('请假申请成功，等待审核！');
					}
				} else {
					popup_msg('请输入所在班级！');
				}
			} else if(num == '') {
				popup_msg('请输入学号！');
			} else {
				popup_msg('学号格式错误！');
			}
		} else if(uname == '') {
			popup_msg('请输入姓名！');
		} else {
			popup_msg('姓名格式错误！');
		}
	});
});

function popup_msg(msg) {
	$(".popup").html("" + msg + "");
	$(".popupDom").show();
	setTimeout(function() {
		$(".popupDom").fadeOut();
	}, 1500);
}
//表单验证
var checkMoney = function(a) {
	var patrn = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
	if(!patrn.exec(a)) return false;
	return true;
}
var checkNum = function(a) {
	var patrn = /^[0-9]{10}$/;
	if(!patrn.exec(a)) return false;
	return true;
}
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
var checkName = function(a) {
	var patrn = /^[\u4e00-\u9fa5]{2,4}$/;
	if(!patrn.exec(a)) return false;
	return true;
}
var checkPerson = function(a) {
	var isIDCard1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	var isIDCard2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	if(!(isIDCard1.exec(a) || isIDCard2.exec(a))) return false;
	return true;
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return decodeURIComponent(r[2]);
	return null;
}

//记住密码
saveInfo = function() { //新增
	try {
		var isSave = $('#chkRememberPwd').is(':checked'); //保存按键是否选中 
		if(isSave) {
			var usernm = $('#login_number').val();
			var userpsw = $('#login_pwd').val();
			SetCookie(usernm, userpsw);
		} else {
			SetCookie("", "");
		}
	} catch(e) {

	}
}

function SetCookie(usern, psw) { //新增
	var Then = new Date()
	Then.setTime(Then.getTime() + 1866240000000)
	document.cookie = "username=" + usern + "%%" + psw + ";expires=" + Then.toGMTString();
}

function GetCookie() { //新增
	var nmpsd;
	var nm;
	var psd;
	var cookieString = new String(document.cookie)
	var cookieHeader = "username="
	var beginPosition = cookieString.indexOf(cookieHeader)
	cookieString = cookieString.substring(beginPosition);
	var ends = cookieString.indexOf(";");
	if(ends != -1) {
		cookieString = cookieString.substring(0, ends);
	}
	if(beginPosition > -1) {
		nmpsd = cookieString.substring(cookieHeader.length);
		if(nmpsd != "") {
			beginPosition = nmpsd.indexOf("%%");
			nm = nmpsd.substring(0, beginPosition);
			psd = nmpsd.substring(beginPosition + 2);
			$('#login_number').val(nm);
			$('#login_pwd').val(psd);
			if(nm != "" && psd != "") {
				$('#chkRememberPwd').attr('checked', 'checked');
			}
		}
	}
}