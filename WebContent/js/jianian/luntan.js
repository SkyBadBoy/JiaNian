$(function() {
	//板块选择
	$('.lt-b-choose li').click(function() {
		var eqi = $(this).index();
		$('.lt-b-content >ul >li').hide();
		$('.lt-b-choose li').css({
			'background': '#F7F7F7'
		});
		$('.lt-b-content >ul >li').eq(eqi).show();
		$(this).css({
			'background': 'white'
		});
	});
	$('.lt-b-content >ul > li').css({
		minHeight: $(window).height() - 113
	});
	//板块点击关注
	$(document).on('click', '.one-block button', function(e) {
		e.preventDefault();
		if($(this).hasClass('attention-btn')) {
			$(this).removeClass('attention-btn');
			$(this).html('关注');
		} else {
			$(this).addClass('attention-btn');
			$(this).html('已关注');
			popup_msg1('关注成功');
			var p = $(this).parents('.one-block').clone(true);
			$('#lt-b-attention').append(p);
		}
	});
	$(document).on('click', '#lt-b-attention button', function(e) {
		e.preventDefault();
		$(this).parents('.one-block').remove();
		popup_msg1('取关成功');
	})
	//收藏
	$('#lt-sc').click(function() {
		if($(this).find('img').attr('src') == 'images/star0.png') {
			$(this).find('img').attr('src', 'images/star1.png');
			popup_msg1('收藏成功');
		} else {
			$(this).find('img').attr('src', 'images/star0.png');
		}
	});
	//打赏
	$('#lt-ds-btn').click(function() {
		var pay = parseInt($('.reward-active div p:nth-child(3)').html());
		var mi = parseInt($('#my-mi').html());
		var count = mi - pay;
		if(count >= 0) {
			popup_msg1('打赏成功');
			$('.reward-block').fadeOut();
		} else {
			popup_msg('余额不足，跳转充值页');
			setInterval(function() {
				window.location = 'lt-cz.html';
			}, 1500);
		}
	});
	//充值
	$('.money li').click(function() {
		$('#my_money').val('');
		$('.money li').removeClass('money_checked');
		$(this).addClass('money_checked');
	});
	//评论
	$('.comm-cancel').click(function() {
		$('.add-comm-bg').fadeOut();
		$('.contain').show();
		$('.setting-tips').show();
		$('.comment-list').show();
	});
	$('body').on('click', '.add-comment', function() {
		$('.contain').hide();
		$('.setting-tips').hide();
		$('.comment-list').hide();
		$('.add-comm-bg').show();
	});
	$('.add-comm-bg').css({
		minHeight: $(window).height() - 51
	});
	$('#comm-send-btn').click(function() {
		var content = $('#comm-textarea').val();
		if(content == '') {
			popup_msg('评论不能为空');
		} else {
			$('.add-comm-bg').hide();
			$('.contain').show();
			$('.setting-tips').show();
			$('.comment-list').show();
			popup_msg1('评论成功');
		}
	});
	//赏金榜送礼物
	$('body').on('click','.give-present',function(){
		$('.reward-block').fadeIn();
	})
})