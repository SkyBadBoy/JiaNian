(function (f) {
    var h = window;
    var j = document;
    var c = f(h);
    var e = f(j);
    f.fn.dropload = function (o) {
        return new a(this, o)
    };
    var a = function (p, o) {
        var q = this;
        q.$element = p;
        q.upInsertDOM = false;
        q.loading = false;
        q.isLockUp = false;
        q.isLockDown = false;
        q.isData = true;
        q._scrollTop = 0;
        q._threshold = 0;
        q.init(o)
    };
    a.prototype.init = function (o) {
        var p = this;
        p.opts = f.extend(true, {}, {
            scrollArea: p.$element,
            domUp: {
                domClass: "dropload-up",
                domRefresh: '<div class="dropload-refresh">↓下拉刷新</div>',
                domUpdate: '<div class="dropload-update">↑释放更新</div>',
                domLoad: '<div class="dropload-load"><span class="weui-loading"></span>加载中...</div>'
            },
            domDown: {
                domClass: "dropload-down",
                domRefresh: '<div class="dropload-refresh">↑上拉加载更多</div>',
                domLoad: '<div class="dropload-load"><span class="weui-loading"></span>加载中...</div>',
                domNoData: '<div class="dropload-noData">没有更多数据了</div>'
            },
            autoLoad: true,
            distance: 50,
            threshold: "",
            loadUpFn: "",
            loadDownFn: ""
        }, o);
        if (p.opts.loadDownFn != "") {
            p.$element.append('<div class="' + p.opts.domDown.domClass + '">' + p.opts.domDown.domRefresh + "</div>");
            p.$domDown = f("." + p.opts.domDown.domClass)
        }
        if (!!p.$domDown && p.opts.threshold === "") {
            p._threshold = Math.floor(p.$domDown.height() * 1 / 3)
        } else {
            p._threshold = p.opts.threshold
        }
        if (p.opts.scrollArea == h) {
            p.$scrollArea = c;
            p._scrollContentHeight = e.height();
            p._scrollWindowHeight = j.documentElement.clientHeight
        } else {
            p.$scrollArea = p.opts.scrollArea;
            p._scrollContentHeight = p.$element[0].scrollHeight;
            p._scrollWindowHeight = p.$element.height()
        }
        d(p);
        c.on("resize", function () {
            if (p.opts.scrollArea == h) {
                p._scrollWindowHeight = h.innerHeight
            } else {
                p._scrollWindowHeight = p.$element.height()
            }
        });
        p.$element.on("touchstart", function (q) {
            if (!p.loading) {
                g(q);
                i(q, p)
            }
        });
        p.$element.on("touchmove", function (q) {
            if (!p.loading) {
                g(q, p);
                k(q, p)
            }
        });
        p.$element.on("touchend", function () {
            if (!p.loading) {
                b(p)
            }
        });
        p.$scrollArea.on("scroll", function () {
            p._scrollTop = p.$scrollArea.scrollTop();
            if (p.opts.loadDownFn != "" && !p.loading && !p.isLockDown && (p._scrollContentHeight - p._threshold) <= (p._scrollWindowHeight + p._scrollTop)) {
                m(p)
            }
        })
    };
    function g(o) {
        if (!o.touches) {
            o.touches = o.originalEvent.touches
        }
    }

    function i(p, o) {
        o._startY = p.touches[0].pageY;
        o.touchScrollTop = o.$scrollArea.scrollTop()
    }

    function k(q, o) {
        o._curY = q.touches[0].pageY;
        o._moveY = o._curY - o._startY;
        if (o._moveY > 0) {
            o.direction = "down"
        } else {
            if (o._moveY < 0) {
                o.direction = "up"
            }
        }
        var p = Math.abs(o._moveY);
        if (o.opts.loadUpFn != "" && o.touchScrollTop <= 0 && o.direction == "down" && !o.isLockUp) {
            q.preventDefault();
            o.$domUp = f("." + o.opts.domUp.domClass);
            if (!o.upInsertDOM) {
                o.$element.prepend('<div class="' + o.opts.domUp.domClass + '"></div>');
                o.upInsertDOM = true
            }
            l(o.$domUp, 0);
            if (p <= o.opts.distance) {
                o._offsetY = p;
                o.$domUp.html(o.opts.domUp.domRefresh)
            } else {
                if (p > o.opts.distance && p <= o.opts.distance * 2) {
                    o._offsetY = o.opts.distance + (p - o.opts.distance) * 0.5;
                    o.$domUp.html(o.opts.domUp.domUpdate)
                } else {
                    o._offsetY = o.opts.distance + o.opts.distance * 0.5 + (p - o.opts.distance * 2) * 0.2
                }
            }
            o.$domUp.css({height: o._offsetY})
        }
    }

    function b(o) {
        var p = Math.abs(o._moveY);
        if (o.opts.loadUpFn != "" && o.touchScrollTop <= 0 && o.direction == "down" && !o.isLockUp) {
            l(o.$domUp, 300);
            if (p > o.opts.distance) {
                o.$domUp.css({height: o.$domUp.children().height()});
                o.$domUp.html(o.opts.domUp.domLoad);
                o.loading = true;
                o.opts.loadUpFn(o)
            } else {
                o.$domUp.css({height: "0"}).on("webkitTransitionEnd mozTransitionEnd transitionend", function () {
                    o.upInsertDOM = false;
                    f(this).remove()
                })
            }
            o._moveY = 0
        }
    }

    function d(o) {
        if (o.opts.autoLoad) {
            if ((o._scrollContentHeight - o._threshold) <= o._scrollWindowHeight) {
                m(o)
            }
        }
    }

    function n(o) {
        if (o.opts.scrollArea == h) {
            o._scrollContentHeight = e.height()
        } else {
            o._scrollContentHeight = o.$element[0].scrollHeight
        }
    }

    function m(o) {
        o.direction = "up";
        o.$domDown.html(o.opts.domDown.domLoad);
        o.loading = true;
        o.opts.loadDownFn(o)
    }

    a.prototype.lock = function (p) {
        var o = this;
        if (p === undefined) {
            if (o.direction == "up") {
                o.isLockDown = true
            } else {
                if (o.direction == "down") {
                    o.isLockUp = true
                } else {
                    o.isLockUp = true;
                    o.isLockDown = true
                }
            }
        } else {
            if (p == "up") {
                o.isLockUp = true
            } else {
                if (p == "down") {
                    o.isLockDown = true;
                    o.direction = "up"
                }
            }
        }
    };
    a.prototype.unlock = function () {
        var o = this;
        o.isLockUp = false;
        o.isLockDown = false;
        o.direction = "up"
    };
    a.prototype.noData = function (o) {
        var p = this;
        if (o === undefined || o == true) {
            p.isData = false
        } else {
            if (o == false) {
                p.isData = true
            }
        }
    };
    a.prototype.resetload = function () {
        var o = this;
        if (o.direction == "down" && o.upInsertDOM) {
            o.$domUp.css({height: "0"}).on("webkitTransitionEnd mozTransitionEnd transitionend", function () {
                o.loading = false;
                o.upInsertDOM = false;
                f(this).remove();
                n(o)
            })
        } else {
            if (o.direction == "up") {
                o.loading = false;
                if (o.isData) {
                    o.$domDown.html(o.opts.domDown.domRefresh);
                    n(o);
                    d(o)
                } else {
                    o.$domDown.html(o.opts.domDown.domNoData)
                }
            }
        }
    };
    function l(p, o) {
        p.css({"-webkit-transition": "all " + o + "ms", transition: "all " + o + "ms"})
    }
})(window.Zepto || window.jQuery);
