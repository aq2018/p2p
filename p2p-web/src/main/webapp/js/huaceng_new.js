function right_layer(){
    // 固定层
    function buffer(a, b, c) {
        var d;
        return function () {
            if (d) return;
            d = setTimeout(function () {
                a.call(this), d = undefined
            }, b)
        }
    }
    (function () {
        function e() {
            var offHei = document.getElementById("rollingLayer").offsetHeight + document.getElementById("rollingLayer").offsetTop;
            var d = document.body.scrollTop || document.documentElement.scrollTop;
            var offTop = document.getElementById("roll").offsetHeight + d;
            if (d > b) {
                a.className = "side-box fixed";
                if (offTop >= offHei) {
                    a.style.top = offHei - offTop + "px";
                } else {
                    a.style.top = 0 + "px";
                }
                c && (a.style.top = d - b + "px");
            } else {
                a.className = "side-box";
            }
        }
        var a,b = 0,c, d;
        window.onscroll=function(){
            a = document.getElementById("roll");
            d=a;
            if (a == undefined) return !1;
            while (d) b += d.offsetTop, d = d.offsetParent;
            c = window.ActiveXObject && !window.XMLHttpRequest;
            if (!c || !0) window.onscroll = buffer(e, this)
        }

    })();
}

window.onload=function(){
	right_layer();
	
}