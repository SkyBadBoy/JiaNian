//function option_change(){
//    if($(this).hasClass('active')){
//        $("#affirm_join p.total span").text($(this).attr('data-value'));
//    }
//    //$("#affirm_join p.total span").text($(this).attr('data-value'));
//}
$(function(){

    var s_dj=$("#affirm_join p.total span").text();
    var dj=parseFloat($("#affirm_join p.total span").text());
    if($(".norms_a").hasClass('active')){
        $("#affirm_join p.total span").text($(".norms .active").attr('data-value'));
        dj=parseFloat($("#affirm_join p.total span").text());
        //alert(dj);
    }
    //$(".norms_a").click(function(){
    //    $(this).addClass("active").siblings().removeClass("active");
    //    if($(".norms_a").hasClass('active')){
    //        data_dj= parseFloat($("#affirm_join p.total span").text($(".norms .active").attr('data-value')));
    //    }
    //    dj=data_dj;
    //    //alert(dj);
    //});
    $(".norms_a").click(function() {
    	if($(this).hasClass('active')){
        	$(this).removeClass("active");
        }else{
        	$(this).addClass("active").siblings().removeClass("active");
        }
        var norms_yj =$(".norms_yj .active").attr('data-value');
        if(norms_yj==undefined){
        	norms_yj="0";
        }
        console.log("b");
        //添加
        dj=$(".norms .active").attr('data-value');
        if(dj==undefined){
        	dj="0";
        }
        var wemoney=0;
        
        if($(".norms_wm_a").hasClass('active')){
        	wemoney = parseFloat($(".norms_wm .active").attr('data-value'))/10;
        }
        var itemPirce = parseFloat(dj);
        var num = parseInt($("#affirm_join p.total span").attr("total"));
       // var total = itemPirce + num-wemoney+parseFloat(norms_yj);
        var total =  num;//-wemoney;
        $("#affirm_join .total span").text(total.toFixed(2));
        $("#weimi_value").val(total);
        $($(".qnxzb-qr-jg span").get(1)).text(total.toFixed(2));
        $($(".text-jg span").get(1)).text(total.toFixed(2));

    });
    

    
    $(".qnxzb_order").click(function(){
        layer.open({
            type: 1,
            title:false,
            closeBtn: 1, //显示关闭按钮
            shift: 2,
            scrollbar: false,
            shadeClose: true, //开启遮罩关闭
            content: $("#affirm_join"),
            area: ['100%','60%'],
            offset: 'rb'
        });
        $(".affirm_jieshao").css("width",parseInt($(".affirm_up").width())-(parseInt($(".img_box").width())+20)+"px");
    });
    $("#affirm_join .affirm_ok").click(function(){
    	if((window.checkparam==null || window.checkparam==undefined) || (window.checkparam  && window.checkparam())){
    		layer.closeAll('page');
            $("#qnxzb-shade").show();
            $("#qnxzb-div").show();
    	}
        

    });

});



$(function () {
//        向上的按钮
    var offset = 300, offset_opacity = 2000, scroll_top_duration = 700, $back_to_top = $('.index-cd-top');
    $(window).scroll(function () {
        ( $(this).scrollTop() > offset ) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
        if ($(this).scrollTop() > offset_opacity) {
            $back_to_top.addClass('cd-fade-out');
        }
    });
    $back_to_top.on('click', function (event) {
        event.preventDefault();
        $('body,html').animate({
                scrollTop: 0
            }, scroll_top_duration
        );
    });
});