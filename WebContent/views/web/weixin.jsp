
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 选项卡 &amp; 面板</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">
    <base target="_blank">
    <style type="text/css">
        /** {*/
        /*padding: 0;*/
        /*margin: 0;*/
        /*}*/

        .bg {
            position: absolute;
            z-index: -1;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            opacity: 0.8;
        }

        ul, ol, li, dl {
            list-style-type: none;
        }

        .weixin_box {
            width: 100%;
            display: -webkit-box;
            display: -moz-box;
            -webkit-box-orient: horizontal;
            -moz-box-orient: horizontal;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            margin-bottom: 0px;
            height: 40px;
        }

        .weixin_box > * {
            -webkit-box-flex: 1;
            -moz-box-flex: 1;
        }

        .weixin_nav4 ul {
            position: absolute;
            z-index: 200;
            bottom: 0;
            left: 0;
            width: 100%
        }

        .weixin_nav4 li {
            border: 1px solid rgba(190, 190, 190, 1);
            height: 40px;
            border-bottom: 0;
            border-right: 0;
            position: relative;
            -webkit-box-shadow: inset 0 0 3px #fff;
            float: left;
            width: 33.26%;
        }

        .weixin_nav4 li:nth-of-type(1) {
            border-left: 0;
        }

        .weixin_nav4 li > a {
            font-size: 13px;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
            border-bottom: 0;
            display: block;
            line-height: 40px;
            text-align: center;
            background: -webkit-gradient(linear, 0 0, 0 100%, from(#f1f1f1), to(#dcdcdc), color-stop(35%, #ededed), color-stop(50%, #e3e3e3));
            background: #fff;
        }

        .weixin_nav4 li > a:only-child span {
            background: none;
            padding-left: 0;
        }

        .weixin_nav4 li > a.on + dl {
            display: block;
        }

        .weixin_nav4 li > a span {
            color: #4f4d4f;
            display: inline-block;
            padding-left: 15px;
            background: url(images/index/1.svg#2) no-repeat 3px 15px;
            -webkit-background-size: 9px auto;
            text-shadow: 0px 1px 0px #ffffff;
            font-size: 12px;;
        }

        /***********************/
        .weixin_nav4 dl {
            display: none;
            position: absolute;
            z-index: 220;
            bottom: 40px;
            left: 1%;
            width: 100px;
            /*margin-left: -50px;*/
            background: #e4e3e2;
            border-radius: 5px;
            -webkit-box-shadow: inset 0 0 3px #fff;
            background: url(images/index/2.svg#3) no-repeat center center;
            -webkit-background-size: 100%;
            background-size: 100%;
            width: 70px;
        }

        /*, .nav4 dl:after*/
        .weixin_nav4 dl:before {
            content: "";
            display: inline-block;
            position: absolute;
            z-index: 240;
            left: 50%;
            width: 10px;
            height: 8px;
            background: url(images/index/1.svg#2) no-repeat center -55px;
            -webkit-background-size: 10px auto;
            bottom: -7px;
            margin-left: -5px;
        }

        /*.weixin_nav4 dl dd:last-child {*/
        /*border-radius: 5px;*/
        /*}*/

        .weixin_nav4 dl dd {
            line-height: 45px;
            text-align: center;
            background: -webkit-gradient(linear, 0 0, 100% 0, from(rgba(194, 194, 194, 0.8)), to(rgba(194, 194, 194, 0.8)), color-stop(50%, rgba(194, 194, 194, 0.8)));
            background-size: 80% 1px;
            background-repeat: no-repeat;
            background-position: center bottom;
            background-color: #fff;
            border-bottom: 1px solid #ddd;
            /*background: url(imgs/3.svg#4) no-repeat center bottom;
            -webkit-background-size:100px 1px;*/
        }

        .weixin_nav4 dl dd:last-of-type {
            background: none;
        }

        /*.weixin_nav4 dl dd a:first-child {*/
        /*border-radius: 5px 5px 0 0;*/
        /*}*/

        /*.weixin_nav4 dl dd a:last-child{border-radius: 0 0  5px 5px;}*/
        .weixin_nav4 dl dd a {
            font-size: 13px;
            display: block;
            color: #4f4d4f;
            text-shadow: 0px 1px 0px #ffffff;
            white-space: pre;
            overflow: hidden;
            text-overflow: ellipsis;
            background-color: #fff;
            height: 35px;
            line-height: 35px;
        }

        .weixin_nav4 .weixin_masklayer_div {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 180;
            background: rgba(0, 0, 0, 0);
        }

        .weixin_nav4 .weixin_masklayer_div.on {
            display: block;
        }

        .checkbox-inline input[type="checkbox"] .checkbox-inline input[type="radio"] {
            margin-top: 6px;;
        }

        .media_cover {
            text-align: center;
        }

        .media_cover a {
            display: block;
            color: #d9dadc;
        }
        .weixin_add{width:20px;margin-top: 0px;}
        /*.create_access{padding:42px 0;margin-bottom:0px;line-height:normal; position: relative;}*/
        /*.tab_cont_cover .create_access .add_gray_wrp{display: inline-block;}*/
        /*.create_access a{display:none;vertical-align:middle;margin-left:10px;margin-right:10px;color:#8d8d8d;font-size:14px;line-height:normal}.}*/

    </style>
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeIn">

    <div class="row">
        <div class="col-sm-3" style="position: relative;height:450px;">
            <div class="bg"><img src="images/index/home-default17.jpg" width="100%" height="100%"></div>
            <div class="weixin_nav4">
                <!--<nav>-->
                <div id="weixin_nav4_ul" class="nav_4">
                    <ul class="weixin_box">
                        <li>
                            <a href="javascript:void(0);" class=""><span>产品授权</span></a>
                            <dl>
                                <dd><a href=""><span>商家入驻</span></a></dd>
                                <dd><a href=""><img src="images/index/plus.png" class="weixin_add"></a></dd>
                            </dl>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class=""><span>推荐产品</span></a>
                            <dl>
                                <dd><a href=""><span>微社区</span></a></dd>
                                <dd><a href="webpage.html"><span>网站定制</span></a></dd>
                                <dd><a href="wechat.html"><span>公众号定制</span></a></dd>
                                <dd><a href="app.html"><img src="images/index/plus.png" class="weixin_add"></a></dd>
                            </dl>
                        </li>
                        <li>
                            <a href="javascript:void (0);" class=""><img src="images/index/plus.png" class="weixin_add"></a>
                            <dl>
                                <dd><a href="www.whohelp.cc"><img src="images/index/plus.png" class="weixin_add"></a></dd>
                                <!--<dd><a href=""><span>互办理念</span></a></dd>-->
                                <!--<dd><a href="timer_shaft.html"><span>互办往事</span></a></dd>-->
                                <!--<dd><a href="" style=""> <img src="images/index/plus.png" style="width:20px;margin-top: 0px;"></a></dd>-->
                            </dl>
                        </li>

                    </ul>
                </div>
                <!--</nav>-->
                <!--<div id="weixin_nav4_masklayer" class="weixin_masklayer_div on">&nbsp;</div>-->
            </div>
        </div>

        <div class="col-sm-9">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>子菜单名称</h5>

                    <form method="get" class="form-horizontal" id="signupForm_weixin_add">
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">菜单名称</label>

                            <div class="col-sm-10">
                                <input type="text" name="keyword" class="form-control" placeholder="菜单名称"> <span
                                    class="help-block m-b-none">字数不超过8个汉字或16个汉字</span>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">子菜单内容</label>

                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="radio" value="option2" id="options_weixin" name="optionsRadios">
                                    <label for="options_weixin" style="color: #737373;font-weight: normal;">发送消息</label>
                                    <input type="radio" checked="" value="option1" id="options_weixin1"
                                           name="optionsRadios">
                                    <label for="options_weixin1"
                                           style="color: #737373;font-weight: normal;">跳转页面</label>
                                </label>
                                <label class="checkbox-inline">
                                </label>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="panel blank-panel">
                                <div class="panel-heading">
                                    <div class="panel-options">
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a data-toggle="tab" href="weixin.html#tab-4">
                                                    <i class="fa fa-laptop"></i>图文信息
                                                </a>
                                            </li>
                                            <li class=""><a data-toggle="tab" href="weixin.html#tab-5"><i
                                                    class="fa fa-desktop"></i>图片</a>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab-4" class="tab-pane active">
                                            <div class="row">
                                                <div class="col-sm-6" style="border-right: 1px solid #ddd;">
                                                    <div class="media_cover">
			                                     <span class="create_access">
			                                        <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;"
                                                       data-type="10" data-index="0">
                                                        <i class="fa fa-plus-square-o fa-5x"></i>

                                                        <p>原新闻中</p>
                                                    </a>
			                                     </span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6" style="">
                                                    <div class="media_cover">
			                                     <span class="create_access">
			                                        <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;"
                                                       data-type="10" data-index="0">
                                                        <i class="fa fa-plus-square-o fa-5x"></i>

                                                        <p>新建图标</p>
                                                    </a>
			                                     </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tab-5" class="tab-pane">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">菜单名称</label>

                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" placeholder="菜单名称">
                                                    <span class="help-block m-b-none">帮助文本，可能会超过一行，以块级元素显示</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4">
                                <button class="btn btn-primary" type="submit">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.5"></script>
<script src="js/content.min.js?v=1.0.0"></script>
<script src="js/plugins/validate/jquery.validate.min.js"></script>
<script src="js/demo/form-validate-demo.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        var nav4 = (function () {

            bindClick = function (els, mask) {
                if (!els || !els.length) {
                    return;
                }
                var isMobile = "ontouchstart" in window;
                for (var i = 0, ci; ci = els[i]; i++) {
                    ci.addEventListener("click", evtFn, false);
                }
                function evtFn(evt, ci) {
                    ci = this;
                    for (var j = 0, cj; cj = els[j]; j++) {
                        if (cj != ci) {
                            console.log(cj);
                            cj.classList.remove("on");
                        }
                    }
                    if (ci == mask) {
                        mask.classList.remove("on");
                        return;
                    }
                    switch (evt.type) {
                        case "click":
                            var on = ci.classList.toggle("on");
                            mask.classList[on ? "add" : "remove"]("on");
                            break;
                    }
                }

                mask.addEventListener(isMobile ? "touchstart" : "click", evtFn, false);
            };
            return {"bindClick": bindClick};
        })();
        nav4.bindClick(document.getElementById("weixin_nav4_ul").querySelectorAll("li>a"), document.getElementById("weixin_nav4_masklayer"));
    });
</script>
</body>

</html>