var referrer = "";//登录后返回页面
referrer = document.referrer;
if (!referrer) {
	try {
		if (window.opener) {                
			// IE下如果跨域则抛出权限异常，Safari和Chrome下window.opener.location没有任何属性              
			referrer = window.opener.location.href;
		}  
	} catch (e) {
	}
}
$(function () {
   loadStat();
});
//加载初始化数据
function loadStat() {
    $.get(
        "loan/loadStat",
        function (json) {
            $(".historyAverageRate").html(json.historyAverageRate);
            $("#user").html(json.allUserCount);
            $("#gold").html(json.allBidMoney);
        }
    )
}

//按键盘Enter键即可登录
$(document).keyup(function(event){
	if(event.keyCode == 13){
		login();
	}
});

//输入账号
function checkPhone() {
    var phone = $("#phone").val();
    if ("" == phone) {
        $("#showId").html("请输入手机号");
        return false;
    } else if (!/^1[1-9]\d{9}$/.test(phone)) {
        $("#showId").html("请输入正确的手机号");
        return false;
    } else {
        $("#showId").html("");
    }
    return true;
}
//输入密码
function checkLoginPassword() {
    var loginPassword = $("#loginPassword").val();
    if ("" == loginPassword) {
        $("#showId").html("请输入密码");
        return false;
    } else {
        $("#showId").html("");
    }
    return true;
}

//输入验证码
function checkCaptcha() {
    var flag = true;
    var captcha = $("#captcha").val();
    if ("" == captcha) {
        $("#showId").html("请输入验证码");
        return false;
    } else {
        $.ajax({
            url:"loan/checkCaptcha",
            type:"post",
            data:"captcha="+captcha,
            async:false,
            success:function(jsonObject) {
                if(jsonObject.errorMessage == "ok") {
                    $("#showId").html("");
                } else {
                    $("#showId").html(jsonObject.errorMessage);
                    flag = false;
                }

            },
            error:function() {
                $("#showId").html("系统繁忙，请稍后重试...");
                flag = false;
            }
        });
    }
    return flag;
}

//登录
function login() {
    var phone = $("#phone").val();
    var loginPassword = $.md5($("#loginPassword").val());
    var captcha = $("#captcha").val();

    if(checkPhone() && checkLoginPassword() && checkCaptcha()){
    	$.ajax({
			type : "post",
			url : "loan/login",
			data : {
				"phone" : phone,
				"loginPassword" : loginPassword
			},
			success : function (jsonObject){
                $("#loginPassword").val("");
                if(jsonObject.errorMessage == "ok") {
                    //登录成功
                    window.location.href = referrer;
                } else {
                    //登录失败
                    $("#showId").html(jsonObject.errorMessage);
                }
			},
			error : function () {
                $("#showId").html("系统繁忙，请稍后重试...");
            }
		})
	}
}

