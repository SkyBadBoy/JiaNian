
$(document).ready(function () {
    $.ajax({
        type: "GET",
        asnyc: false,
        url: "photos.json",
        success: function (data) {
//                alert(data);
            var ps = $.parseJSON(data);
            for (var i = 0; i < ps.length; i++) {//经过解析后，将从后太读取的数据逐条添加到页面中显示
                var item = ps[i];
                var activity = $("#activity-article-item-3");
//                    var row = $("<tr><td>" + item.PName + "</td><td>" + item.PSex + "</td><td>" + item.PAge <br>+ "</td><td>" + item.PJob + "</td></tr>");
//                    var row = $("<tr><td>" + item.PName + "</td><td>" + item.PSex + "</td><td>" + item.PAge <br>+ "</td><td>" + item.PJob + "</td></tr>");
                var contentHtml=
                    $("<div class='article-item activity-article-item'>"+
                        "<a>"+
                        "<div class='item-cover'>"+
                        "<img src='images/"+item.images+"+' alt='2016新浪小编夏令营全攻略' style='display: inline;'>"+
                        "</div>"+
                        "<div class='activity-item-summary'>"+
                        "<div class='item-title' style='line-height: 20px;'>"+item.title+"</div>"+
                        "<div class='item-text' style='line-height: 30px;height:30px;'>"+
                        "<div style='float:left;height: 30px;line-height: 30px;'>"+item.read+"</div>"+
                        "<div style='float:right;height: 30px;line-height: 30px;background-color: #FF2626;color: #fff;width: 80px;border-radius: 5px;text-align: center;'>"+item.apply+"</div>"+
                        +"</div>"+
                        "<div class='item-text' style='line-height: 20px;height:20px;'>"+
                        "<div style='float:left;margin-top: 10px;'>"+item.date+"</div>"+
                        "</div>"+
                        "</div>"+
                        "</a>"+
                        "</div>");
                activity.append(contentHtml);
            }
        },
        error: function () {
            flag = false;
        }
    });
//        return flag;
});
