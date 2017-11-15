$(function() {
	//点击分享
	$(document).on('click','.add_share',function(){
		$('.share-block').fadeIn();
	});
	$('.share-block ul').click(function(e){
		e.stopPropagation();
	});	
	//点击打赏
	$(document).on('click','.add-reward',function(){
		$('.reward-block').fadeIn();
	});
	$('.reward-block li').click(function(){
		$('.reward-block li').removeClass('reward-active');
		$(this).addClass('reward-active');
	});
	$('.reward-block').click(function(){
		$(this).fadeOut();
	})
	$('.reward-block ul').click(function(e){
		e.stopPropagation();
	});	
	//返回顶部
	$(window).scroll(function() {
		$('.back-top').css({
			display: 'block'
		});
		if($(window).scrollTop() == 0) {
			$('.back-top').css({
				display: 'none'
			});
		}
	});
	/*确认退出层*/
	$('.sign-out>a').click(function() {
		$(this).parents('body').find('.outlayer').slideDown(200);
	});
	$('.tip-box .cancel').click(function(){
		$(this).parents('.outlayer').slideUp(200);
	});
	
	/*展示选择框*/
	$('.show-btn').click(function(){
		$(this).siblings('.checkshow').toggle().siblings('.goin').toggle();
		$(this).parents('body').find('.cover-bg').show();
	});
	$('.close').click(function(){
		$(this).parent('.checkshow').hide().parents('body').find('.cover-bg').hide();
	});
	$('.checkshow>p').click(function(){
		$(this).addClass('checked').siblings().removeClass('checked');
	});
	$('.sex>label').click(function(){
		$(this).addClass('sex-check').siblings().removeClass('sex-check');
	});
	$('.hd>li').click(function(){
		var index=$(this).index();
		console.log(index)
		$(this).addClass('active').siblings().removeClass('active');
		$('.bd>div').eq(index).show().siblings().hide();
	});

	$('.notice-content a:even').css('background','#eee');
	
	/* $(document).on("click", ".page-item", function (e) {
          $(this).addClass('active');
        });*/
     
    var touch_ele = ".touch,a,button";//Elements that need to be clicked
    touch_feedback(touch_ele);//Add touch feedback
    function touch_feedback(ele) {
        $('body').on("touchstart touchend touchmove tap", ele, function (e) {
            var $this = $(this)
            // e.preventDefault();
            switch (event.type) {
                case "touchstart":
                    $this.addClass("activated");
                    break;
                case "touchmove":
                    $(this).removeClass("activated");
                    break;
                case "touchend":
                    $(this).removeClass("activated");
                    break;
                case "tap":
                    var url=$this.attr("data-url")
                    if(url)
                    window.location.href=url;
                    break;
            }
        });
    };//touch_feedback
    //分享点击空白隐藏
    $('.share-block').click(function(){
		$(this).fadeOut();
	});
});
function result_none(msg,ele) {
	$(".result-none p").html("" + msg + "");
	var str='<div class="result-none"><div><img src="images/c-11.png" /></div><p class="text-center">'+msg+'</p></div>'
	$(ele).append(str);
}
function popup_msg(msg) {
	$(".popup").html("" + msg + "");
	$(".popupDom").show();
	setTimeout(function() {
		$(".popupDom").fadeOut();
	}, 1500);
}
function popup_msg1(msg) {
	$(".pop-p").html("" + msg + "");
	$(".pop-msg").show();
	setTimeout(function() {
		$(".pop-msg").fadeOut();
	}, 1500);
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return decodeURIComponent(r[2]);
	return null;
}
function GetNum(num){
	var s=num/10000;
	if(s<1){
		return num;
	}else{
		s=(num-num%1000)/10000;
		return s+'万';
	}
}
