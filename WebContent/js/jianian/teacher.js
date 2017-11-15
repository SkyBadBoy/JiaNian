$(function() {
	//班级相册添加删除
	var photo_flag = 0;
	$(document).on('click', '.photo-edit', function() {
		if(photo_flag == 0) {
			$('.class-photolist span').css('display', 'block');
			$('.photo-edit').html('完成');
			photo_flag = 1;
		} else {
			$('.class-photolist span').css('display', 'none');
			$('.photo-edit').html('编辑');
			photo_flag = 0;
		}
	});
	$(document).on('click', '.class-photolist a', function(e) {
		e.stopPropagation();
	});
	$(document).on('click', '.class-photolist span', function(e) {
		$(this).parents('a').remove();
		e.stopPropagation();
		e.preventDefault();
	});
	//班级照片添加删除
	var photo_flag1 = 0;
	$(document).on('click', '.photo-edit', function() {
		if(photo_flag1 == 0) {
			$('.photo-list span').css('display', 'block');
			$('.photo-edit').html('完成');
			photo_flag1 = 1;
		} else {
			$('.photo-list span').css('display', 'none');
			$('.photo-edit').html('编辑');
			photo_flag1 = 0;
		}
	});
	$(document).on('click', '.photo-list span', function(e) {
		$(this).parent().remove();
		e.stopPropagation();
		e.preventDefault();
	});
	//布置作业
	$('#add_hw_btn').on('click', function() {
		var hw_class = $('#hw_class').val();
		var hw_phone = $('#hw_phone').val();
		var hw_date = $('#hw_date').val();
		var hw_content = $('#hw_content').html();
		if(hw_class == '') {
			popup_msg('请输入班级');
		} else if(hw_phone == '') {
			popup_msg('请输入手机号码');
		} else if(!checkPhone(hw_phone)) {
			popup_msg('手机号码格式不正确');
		} else if(hw_date == '') {
			popup_msg('请输入布置时间');
		} else if(hw_content == '') {
			popup_msg('请输入作业内容');
		} else {
			popup_msg('作业发布成功！');
		}
	});
	//班级管理员登录
	$('#adminlogin_btn').on('click', function() {
		var admin = $('#admin_number').val();
		var pwd = $('#admin_pwd').val();
		if(admin == '') {
			popup_msg('请输入职工号');
		} else if(!checkNum(admin)) {
			popup_msg('职工号不存在');
		} else if(pwd == '') {
			popup_msg('请输入密码');
		} else if(!checkPwd(pwd)) {
			popup_msg('密码错误');
		} else {
			saveInfoadmin();
			window.location = 'tc-index.html';
		}
	});
	//班级管理员找回密码
	$('#adminfindpwd_btn').on('click', function() {
		var num = $('#adminfind_num').val();
		var phone = $('#find_admin').val();
		var pwd1 = $('#adminnew_pwd').val();
		var pwd2 = $('#adminrenew_pwd').val();
		if(num == '') {
			popup_msg('请输入职工号');
		} else if(!checkNum(num)) {
			popup_msg('职工号不存在');
		} else if(phone == '') {
			popup_msg('请输入管理员手机号');
		} else if(!checkPhone(phone)) {
			popup_msg('手机号错误');
		} else if(pwd1 == '') {
			popup_msg('请输入新密码');
		} else if(!checkPwd(pwd1)) {
			popup_msg('新密码格式错误');
		} else if(pwd2 == '') {
			popup_msg('请输入确认密码');
		} else if(pwd1 != pwd2) {
			popup_msg('两次密码输入不一致');
		} else {
			popup_msg('密码修改成功');
			window.location = 'tc-login.html';
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
	//请假审核
	$('#qj_yes_btn').on('click', function() {
		popup_msg('该请假已同意');
		$('.qj_result p').html('已同意');
		$('.qj_result').css('background', '#45C0F8');
		$('.qj-btngroup').hide();
		$('.qj_result').show();
	});
	$('#qj_no_btn').on('click', function() {
		popup_msg('该请假已拒绝');
		$('.qj_result p').html('已拒绝');
		$('.qj-btngroup').hide();
		$('.qj_result').show();
	});
	//点赞功能
	$(document).on('click', '.dt-tip ul li:nth-child(1)', function() {
		var img = $(this).find('img').attr('src');
		if(img == 'images/zan0.png') {
			$(this).find('img').attr('src', 'images/zan1.png');
			$(this).find('span').html(parseInt($(this).find('span').html()) + 1);
		} else {
			$(this).find('img').attr('src', 'images/zan0.png');
			$(this).find('span').html(parseInt($(this).find('span').html()) - 1);
		}
	});
	//评论
	$(document).on('click', '.addcomment', function() {
		$('.comm-list').removeClass('comm-editing');
		$(this).prev().addClass('comm-editing');
		$('.zhezhao-gray').fadeIn();
	});
	$('#comment-text').on("keyup", function() {
		var l = $('#comment-text').val().length;
		$('.comment-content ul li:nth-child(1) span').html(l);
		if(l > 100) {
			popup_msg('字数已超出100');
			$('.comment-content ul li:nth-child(1) span').css('color', 'red');
		} else {
			$('.comment-content ul li:nth-child(1) span').css('color', 'orange');
		}
	});

	$('#comm-cancel').on('click', function() {
		$('#comment-text').val('');
		$('.zhezhao-gray').fadeOut();
		$('.comm-list').removeClass('comm-editing');
		$('.comm-list').removeClass('comm-replaying');
	});
	$(document).on('click', '.comm-list p', function() {
		replay = $(this).find('.pl-name').html();
		$('.zhezhao-gray').fadeIn();
		$('.comm-list').removeClass('comm-replaying');
		$(this).parent().addClass('comm-replaying');
		$(this).parent().addClass('comm-editing');
	});
	$('#comm-send').on('click', function() {
		var texts = $('#comment-text').val();
		var name = '候鸟';
		if(texts == '') {
			popup_msg('评论不能为空');
		} else {
			if($('.comm-list').hasClass('comm-replaying')) {
				$('.comm-editing').append('<p><span class="pl-name">' + name + '</span>&nbsp;&nbsp;回复<a href="" style="color:#1D7DB1">&nbsp;&nbsp;' + replay + '</a>：<span class="pl-content">' + texts + '</span></p>');
			} else {
				$('.comm-editing').append('<p><span class="pl-name">' + name + '</span>：<span class="pl-content">' + texts + '</span></p>');
			}
			$('.zhezhao-gray').hide();
			$('.comm-list').removeClass('comm-editing');
			$('.comm-list').removeClass('comm-replaying');
			$('#comment-text').val('');
			popup_msg1('评论成功');
		}
	});
	//通讯录
	$('.letter').height($(window).height()-56);
});