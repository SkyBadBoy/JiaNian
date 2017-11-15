/*
 * Lazy Load - jQuery plugin for lazy loading images
 *
 * Copyright (c) 2007-2013 Mika Tuupola
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Project home:
 *   http://www.appelsiini.net/projects/lazyload
 *
 * Version:  1.9.3
 *
 */

(function($, window, document, undefined) {
    var $window = $(window);

    $.fn.lazyload = function(options) {
        var elements = this;
        var $container;
        var settings = {
            threshold       : 0,
            failure_limit   : 0,
            event           : "scroll",
            effect          : "show",
            container       : window,
            data_attribute  : "original",
            skip_invisible  : true,
            appear          : null,
            load            : null,
            placeholder: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEEAAABBCAIAAAABlV4SAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTExIDc5LjE1ODMyNSwgMjAxNS8wOS8xMC0wMToxMDoyMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjI3MzBENTUxQ0YyQjExRTZCMEE3RTQ2N0YyOTQ4NkIxIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjI3MzBENTUyQ0YyQjExRTZCMEE3RTQ2N0YyOTQ4NkIxIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MjczMEQ1NEZDRjJCMTFFNkIwQTdFNDY3RjI5NDg2QjEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MjczMEQ1NTBDRjJCMTFFNkIwQTdFNDY3RjI5NDg2QjEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6xsdxoAAAGnUlEQVR42uza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"
        };

        function update() {
            var counter = 0;

            elements.each(function() {
                var $this = $(this);
                if (settings.skip_invisible && !$this.is(":visible")) {
                    return;
                }
                if ($.abovethetop(this, settings) ||
                    $.leftofbegin(this, settings)) {
                        /* Nothing. */
                } else if (!$.belowthefold(this, settings) &&
                    !$.rightoffold(this, settings)) {
                        $this.trigger("appear");
                        /* if we found an image we'll load, reset the counter */
                        counter = 0;
                } else {
                    if (++counter > settings.failure_limit) {
                        return false;
                    }
                }
            });

        }

        if(options) {
            /* Maintain BC for a couple of versions. */
            if (undefined !== options.failurelimit) {
                options.failure_limit = options.failurelimit;
                delete options.failurelimit;
            }
            if (undefined !== options.effectspeed) {
                options.effect_speed = options.effectspeed;
                delete options.effectspeed;
            }

            $.extend(settings, options);
        }

        /* Cache container as jQuery as object. */
        $container = (settings.container === undefined ||
                      settings.container === window) ? $window : $(settings.container);

        /* Fire one scroll event per scroll. Not one scroll event per image. */
        if (0 === settings.event.indexOf("scroll")) {
            $container.bind(settings.event, function() {
                return update();
            });
        }

        this.each(function() {
            var self = this;
            var $self = $(self);

            self.loaded = false;

            /* If no src attribute given use data:uri. */
            if ($self.attr("src") === undefined || $self.attr("src") === false) {
                if ($self.is("img")) {
                    $self.attr("src", settings.placeholder);
                }
            }

            /* When appear is triggered load original image. */
            $self.one("appear", function() {
                if (!this.loaded) {
                    if (settings.appear) {
                        var elements_left = elements.length;
                        settings.appear.call(self, elements_left, settings);
                    }
                    $("<img />")
                        .bind("load", function() {

                            var original = $self.attr("data-" + settings.data_attribute);
                            $self.hide();
                            if ($self.is("img")) {
                                $self.attr("src", original);
                            } else {
                                $self.css("background-image", "url('" + original + "')");
                            }
                            $self[settings.effect](settings.effect_speed);

                            self.loaded = true;

                            /* Remove image from array so it is not looped next time. */
                            var temp = $.grep(elements, function(element) {
                                return !element.loaded;
                            });
                            elements = $(temp);

                            if (settings.load) {
                                var elements_left = elements.length;
                                settings.load.call(self, elements_left, settings);
                            }
                        })
                        .attr("src", $self.attr("data-" + settings.data_attribute));
                }
            });

            /* When wanted event is triggered load original image */
            /* by triggering appear.                              */
            if (0 !== settings.event.indexOf("scroll")) {
                $self.bind(settings.event, function() {
                    if (!self.loaded) {
                        $self.trigger("appear");
                    }
                });
            }
        });

        /* Check if something appears when window is resized. */
        $window.bind("resize", function() {
            update();
        });

        /* With IOS5 force loading images when navigating with back button. */
        /* Non optimal workaround. */
        if ((/(?:iphone|ipod|ipad).*os 5/gi).test(navigator.appVersion)) {
            $window.bind("pageshow", function(event) {
                if (event.originalEvent && event.originalEvent.persisted) {
                    elements.each(function() {
                        $(this).trigger("appear");
                    });
                }
            });
        }

        /* Force initial check if images should appear. */
        $(document).ready(function() {
            update();
        });

        return this;
    };

    /* Convenience methods in jQuery namespace.           */
    /* Use as  $.belowthefold(element, {threshold : 100, container : window}) */

    $.belowthefold = function(element, settings) {
        var fold;

        if (settings.container === undefined || settings.container === window) {
            fold = (window.innerHeight ? window.innerHeight : $window.height()) + $window.scrollTop();
        } else {
            fold = $(settings.container).offset().top + $(settings.container).height();
        }

        return fold <= $(element).offset().top - settings.threshold;
    };

    $.rightoffold = function(element, settings) {
        var fold;

        if (settings.container === undefined || settings.container === window) {
            fold = $window.width() + $window.scrollLeft();
        } else {
            fold = $(settings.container).offset().left + $(settings.container).width();
        }

        return fold <= $(element).offset().left - settings.threshold;
    };

    $.abovethetop = function(element, settings) {
        var fold;

        if (settings.container === undefined || settings.container === window) {
            fold = $window.scrollTop();
        } else {
            fold = $(settings.container).offset().top;
        }

        return fold >= $(element).offset().top + settings.threshold  + $(element).height();
    };

    $.leftofbegin = function(element, settings) {
        var fold;

        if (settings.container === undefined || settings.container === window) {
            fold = $window.scrollLeft();
        } else {
            fold = $(settings.container).offset().left;
        }

        return fold >= $(element).offset().left + settings.threshold + $(element).width();
    };

    $.inviewport = function(element, settings) {
         return !$.rightoffold(element, settings) && !$.leftofbegin(element, settings) &&
                !$.belowthefold(element, settings) && !$.abovethetop(element, settings);
     };

    /* Custom selectors for your convenience.   */
    /* Use as $("img:below-the-fold").something() or */
    /* $("img").filter(":below-the-fold").something() which is faster */

    $.extend($.expr[":"], {
        "below-the-fold" : function(a) { return $.belowthefold(a, {threshold : 0}); },
        "above-the-top"  : function(a) { return !$.belowthefold(a, {threshold : 0}); },
        "right-of-screen": function(a) { return $.rightoffold(a, {threshold : 0}); },
        "left-of-screen" : function(a) { return !$.rightoffold(a, {threshold : 0}); },
        "in-viewport"    : function(a) { return $.inviewport(a, {threshold : 0}); },
        /* Maintain BC for couple of versions. */
        "above-the-fold" : function(a) { return !$.belowthefold(a, {threshold : 0}); },
        "right-of-fold"  : function(a) { return $.rightoffold(a, {threshold : 0}); },
        "left-of-fold"   : function(a) { return !$.rightoffold(a, {threshold : 0}); }
    });

})(jQuery, window, document);
