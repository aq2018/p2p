$(function () {
    $("#realName").focus(function () {
        clearCaptchaErrTip();
    });
    $("#idCard").focus(function () {
        clearCaptchaErrTip();
    });
});
function clearCaptchaErrTip() {
    $("#captchaErr").hide();
    $("#captchaErr").html("");
    $("#captchaErr").removeClass("input-red");
}
//验证姓名
function checkRealName() {
	var realName =  $.trim($("#realName").val());
	if("" == realName || "请输入您的真实姓名" == realName){
        showError("realName","姓名不能为空");
        return false;
	}else if(!/^[\u4e00-\u9fa5]+$/.test(realName)){
		showError("realName","姓名只能输入中文");
		return false;
	}
	showSuccess("realName");
	return true;
}

//验证身份证号
function checkIdCard() {
	var idCard =  $.trim($("#idCard").val());
	if("" == idCard || "请输入15位或20位身份证号码" == idCard){
        showError("idCard","请输入身份证号码");
        return false;
	} else if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idCard)) {
        showError("idCard","请输入正确的身份证号码");
        return false;
	}
    showSuccess("idCard");
	return true;
}
//确认身份证
function reCheckIdCard() {
    var idCard = $.trim($("#idCard").val());
    var replayIdCard =  $.trim($("#replayIdCard").val());
    if("" == idCard || "请输入15位或20位身份证号码" == idCard){
        showError("idCard","请输入身份证号码");
        return false;
    } else if("" == replayIdCard){
        showError("replayIdCard","请输入确认身份证号码");
        return false;
    }
    else if (idCard != replayIdCard) {
        showError("replayIdCard","两次输入的身份证号不一致");
        return false;
    }
    showSuccess("replayIdCard");
    return true;

}



//同意实名认证协议
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});
});
//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}

//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}