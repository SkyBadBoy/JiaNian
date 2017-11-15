$(function(){
	$(document).on('click','.add-attention',function(){
		var a=$(this).find('img').attr('src');
		if(a=='images/live-add.png'){
			$(this).find('img').attr('src','images/live-remove.png');
			$(this).find('img').css({
				left:'0'
			});
			popup_msg1('关注成功');
		}else{
			$(this).find('img').attr('src','images/live-add.png');
			$(this).find('img').css({
				left:'-30px'
			});
			popup_msg1('取关成功');
		}
	});
	var livedel=0;
	$('#live-edit').on('click',function(){
		if(livedel==0){
		$('.live-remove-btn').show();
		$(this).html('完成');
		livedel=1;
		}else{
			$('.live-remove-btn').hide();
		$(this).html('编辑');
		livedel=0;
		}
	});
	//点击喜欢
	$('body').on("click",'.heart',function(){
		
		var A=$(this).attr("id");
		var B=A.split("like");
		var messageID=B[1];
		var C=parseInt($("#likeCount"+messageID).find('i').html());
		$(this).css("background-position","")
		var D=$(this).attr("rel");
		if(D === 'like') {      
			var num=$("#likeCount"+messageID).html(GetNum(C+1)+'<i>'+(C+1)+'</i>');
			$(this).addClass("heartAnimation").attr("rel","unlike");
		}
		else{
			$("#likeCount"+messageID).html(GetNum(C-1)+'<i>'+(C-1)+'</i>');
			$(this).removeClass("heartAnimation").attr("rel","like");
			$(this).css("background-position","left");
		}
	});
	
	
});
