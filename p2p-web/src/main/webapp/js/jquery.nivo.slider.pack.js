function slideSwitch() {
    var stay = 6;
    var fade = 0.7;
    var msec = 1000;
    var timer = 400;
    var timeout;
    var prev = next = 0;
    var slider = $('#slider');
    var slideindex = true;
    var controls = true;
    var html = '';
    var term = slider.children('li');
    var len = term.length;
    slider.wrap('<div class="slider-items"></div>');
    if (slideindex) {
        if (len > 1) {
            html += '<div class="slider-index">';
            html += '<ol class="items">';
            for (var i = 1; i <= len; i++) {
                html += '<li ' + (i == 1 ? 'class="current"': '') + '><i></i></li>'
            };
            html += '</ol>';
            html += '</div>'
        }
    };
    if (controls) {
        html += '<div class="slider-updown">';
        html += '<a href="#" class="button prev" data-rel="prev">prev</a>';
        html += '<a href="#" class="button next" data-rel="next">next</a>';
        html += '</div>'
    };
   
   slider.parent('.slider-items').wrap('<div class="slider-wrap"></div>').css({
        'position': 'relative',
        'overflow': 'hidden'
    }).after(html);
    var thumbCont = slider.parent().siblings('.slider-index');
    var thumb = thumbCont.find('li');
    var button = slider.parent().siblings('.slider-updown');
    slider.fadeIn();
    if (len == 1) {
        term.first().fadeIn()
    };
    button.find('.button').bind('click',
    function() {
        var self = $(this).attr('data-rel');
        if (self == 'prev') {
            if (prev == 0) {
                next = len - 1
            } else {
                next = prev - 1
            }
        } else if (self == 'next') {
            if (prev == len - 1) {
                next = 0
            } else {
                next = prev + 1
            }
        };
        autoPlay();
        return false
    });
    thumb.bind('click',
    function() {
        next = thumb.index($(this));
        clearTimeout(timeout);
        changePlay(next);
        if (next == len - 1) {
            next = 0
        } else {
            next++
        }
    });
    slider.hover(function() {
        clearTimeout(timeout)
    },
    function() {
        if (len > 1) {
            timeout = setTimeout(autoPlay, stay * msec)
        }
    });
    var changePlay = function(next) {
        term.eq(prev).fadeOut(fade * msec);
        term.eq(next).fadeIn(fade * msec);
        thumb.removeClass('current');
        thumb.eq(next).addClass('current');
        if (typeof($('#userGain')[0]) != '') {
            $('#userGain').find('.opacity').css({
                opacity: term.eq(next).attr('data-opacity')
            })
        };
        prev = next
    };
    var autoPlay = function() {
        clearTimeout(timeout);
        changePlay(next);
        next = prev + 1;
        if (next >= len) {
            next = 0
        };
        timeout = setTimeout(autoPlay, stay * msec)
    };
    if (len > 1) {
        autoPlay()
    }
};