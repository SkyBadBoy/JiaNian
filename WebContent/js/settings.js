
$(document).ready(function () {
    function ratingEnable() {
        $('#example-f').barrating({
            wrapperClass: 'br-wrapper-f',
            showSelectedRating: false
        });
    }
    ratingEnable();
    function iphoneRemove() {
        var u = navigator.userAgent;
        if ((!!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/))) {//ios设备
            $("#back_away").remove();
        }
    }
    iphoneRemove();
    indexBroadside();
    //indexBaidu();
});
function indexBroadside() {
    $('aside.index-slide-wrapper').on('touchstart', 'li', function (e) {
        $(this).addClass('current').siblings('li').removeClass('current');
    });
    $('a.index-slide-menu').on('click', function (e) {
        var wh = $('div.index-wrapper').height();
        $('div.index-slide-mask').css('height', wh).show();
        $('aside.index-slide-wrapper').css('height', wh).addClass('moved');
    });
    $('div.index-slide-mask').on('click', function () {
        $('div.index-slide-mask').hide();
        $('aside.index-slide-wrapper').removeClass('moved');
    });
}
//function indexBaidu() {
//    var ep = $("#end_point").val().split(",");
//    var map = new BMap.Map("indexmap");
//    map.enableScrollWheelZoom();
//    var point = new BMap.Point(ep[0], ep[1]);
//    map.centerAndZoom(point, 16);
//    // 定位对象
//    var geoc = new BMap.Geocoder();
//    var geolocation = new BMap.Geolocation();
//    geolocation.getCurrentPosition(function (r) {
//        if (this.getStatus() == BMAP_STATUS_SUCCESS) {
//            setLocation(r.point);
//        } else {
//            $("#location").attr("placeholder", "请输入您的当前位置");
//            alert('无法定位到您的当前位置，导航失败，请手动输入您的当前位置！' + this.getStatus());
//        }
//    }, {enableHighAccuracy: true});
//    function setLocation(point) {
//        geoc.getLocation(point, function (rs) {
//            var addComp = rs.addressComponents;
//            var result = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
//            $("#location").val(result);
//        });
//    }
//
//    map.addEventListener("click", function (e) {
//        var pt = e.point;
//        geoc.getLocation(pt, function (rs) {
//            var addComp = rs.addressComponents;
//            var result = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
//            $("#location").val(result);
//        });
//    });
//    $("#makesure").click(function () {
//        var result = $("#location").val();
//        location.href = "activity_write.html";
////              $("#location").parent.$("#location-area").val(result);
//        $("#location-area").val(result);
//        window.setTimeout(function () {
//
//        }, 500);
//
//    });
//}
function iphoneRemove() {
    var u = navigator.userAgent;
    if ((!!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/))) {//ios设备
        $("#back_away").remove();
    }
}

$(function () {
    window.setTimeout(function () {
        $("#index_loading").fadeOut();
    }, 500);
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
//baidu_earth.html的百度地图light API
$(function () {
    var ep = $("#end_point").val().split(",");
    var map = new BMap.Map("indexmap");
    map.enableScrollWheelZoom();
    var point = new BMap.Point(ep[0], ep[1]);
    map.centerAndZoom(point, 16);
    // 定位对象
    var geoc = new BMap.Geocoder();
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function (r) {
        if (this.getStatus() == BMAP_STATUS_SUCCESS) {
            setLocation(r.point);
        } else {
            $("#location").attr("placeholder", "请输入您的当前位置");
            alert('无法定位到您的当前位置，导航失败，请手动输入您的当前位置！' + this.getStatus());
        }
    }, {enableHighAccuracy: true});
    function setLocation(point) {
        geoc.getLocation(point, function (rs) {
            var addComp = rs.addressComponents;
            var result = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            $("#location").val(result);
        });
    }
    map.addEventListener("click", function (e) {
        var pt = e.point;
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            var result = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            $("#location").val(result);
        });
    });
    $("#makesure").click(function () {
        var result = $("#location").val();
        location.href = "activity_write.html";
        $("#location-area").val(result);
        window.setTimeout(function () {
        }, 500);
    });
});
//baidu_earth.html的弹出层
$(function () {
    $('.inactive').click(function () {
        if ($(this).siblings('ul').css('display') == 'none') {
            $(this).parent('li').siblings('li').removeClass('inactives');
            $(this).addClass('inactives');
            $(this).siblings('ul').slideDown(100).children('li');
            if ($(this).parents('li').siblings('li').children('ul').css('display') == 'block') {
                $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
                $(this).parents('li').siblings('li').children('ul').slideUp(100);
            }
        } else {
            //控制自身变成+号
            $(this).removeClass('inactives');
            //控制自身菜单下子菜单隐藏
            $(this).siblings('ul').slideUp(100);
            //控制自身子菜单变成+号
            $(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
            //控制自身菜单下子菜单隐藏
            $(this).siblings('ul').children('li').children('ul').slideUp(100);
            //控制同级菜单只保持一个是展开的（-号显示）
            $(this).siblings('ul').children('li').children('a').removeClass('inactives');
        }
    })
});

$(function () {
    var curr = new Date().getFullYear();
    var opt = {
        'default': {
            theme: 'default',
            mode: 'scroller',
            display: 'modal',
            animate: 'fade'
        },
        //'dateY': {
        //    preset: 'date',
        //    dateFormat: 'yyyy',
        //    defaultValue: new Date(new Date()),
        //    invalid: {daysOfWeek: [0, 6], daysOfMonth: ['5/1', '12/24', '12/25']},
        //    onBeforeShow: function (inst) {
        //        if (inst.settings.wheels[0].length > 1) {
        //            inst.settings.wheels[0].pop();
        //            inst.settings.wheels[0].pop();
        //        } else {
        //            null
        //        }
        //    }
        //},
        //'dateYM': {
        //    preset: 'date',
        //    dateFormat: 'yyyy-mm',
        //    defaultValue: new Date(new Date()),
        //    onBeforeShow: function (inst) {
        //        if (inst.settings.wheels[0].length > 2) {
        //            inst.settings.wheels[0].pop();
        //        } else {
        //            null
        //        }
        //    }
        //},
        //'dateYMD': {
        //    preset: 'date',
        //    dateFormat: 'yyyy-mm-dd',
        //    defaultValue: new Date(new Date()),
        //    invalid: {daysOfWeek: [0, 6], daysOfMonth: ['5/1', '12/24', '12/25']}
        //},
        //'datetime': {
        //    preset: 'datetime',
        //    minDate: new Date(2012, 3, 10, 9, 22),
        //    maxDate: new Date(2014, 7, 30, 15, 44),
        //    stepMinute: 5
        //},
        //'time': {
        //    preset: 'time'
        //},
        'select': {
            preset: 'select'
        },
        'select-opt': {
            preset: 'select',
            group: true,
            width: 50
        }
    };
    //$('#demo1').scroller($.extend(opt['dateY'], opt['default']));
    //$('#demo2').scroller($.extend(opt['dateYM'], opt['default']));
    //$('#demo3').scroller($.extend(opt['dateYMD'], opt['default']));
    $('#baidu_earth_seday').scroller($.extend(opt['select'], opt['default']));
    $('#baidu_earth_sework').scroller($.extend(opt['select-opt'], opt['default']));
    $('#people_center_sework').scroller($.extend(opt['select-opt'], opt['default']));
});

$(function () {
    var checkPhone = function (a) {
        var patrn = /^((?:13|15|18|14|17)\d{9}|0(?:10|2\d|[3-9]\d{2})[1-9]\d{6,7})$/;
        if (!patrn.exec(a)) return false;
        return true;
    };
    $(function () {
        // ========================================浮层控制
        $("#baidu_tip .baidu_pack a").on("click", function () {
            $("#baidu_tip").fadeOut();
            $("#baidu_tip .baidu_pack p").html("");
            return false;
        });

        function alerths(str) {
            $("#baidu_tip").fadeIn();
            $("#baidu_tip .baidu_pack p").html(str);
            return false;
        }
        $("#submit").on("click", function () {
            alerths("请输入正确的手机号！");
            $("#thing_todo").focus();
            return false;
        });
        $("#baidu_sure").on("click", function () {
            var test = $("#thing_todo").val();
            $(".thing_doing").val(test);
        })
    });
});



$(function () {
});

$(function () {
});

$(function () {
});

$(function () {
});

$(function () {
});



