$(function() {
	$('#add-lesson-btn').click(function() {
		popup_msg1('添加成功');
		$(this).hide();
	});
});

function js_method(obj) {
	$('.outlayer').fadeIn();
	$(obj).addClass('isremoving');
}

function doSearch() {
	var s = $('#search').val();
	if(s == '') {
		popup_msg('请输入搜索内容');
		return false;
	} else {
		window.location.href = "air-search.html?id=" + s;
		$.ajax({
			type: 'GET',
			url: 'air-search.html',
			data: $.param({
				id: s
			}),
			dataType: 'json',
			success: function(data) {
				window.location = 'air-search.html';
			}
		});
	}
}