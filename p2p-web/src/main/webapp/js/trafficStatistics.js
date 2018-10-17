//微信二维码关闭界面
function closeEwm(divId) {
	document.getElementById(divId).style.display = 'none';
	document.getElementById('dialog').style.display = 'none';
}
//微信二维码弹出界面
function displayEwm(divId) {
	document.getElementById('dialog').style.display = 'block';
	document.getElementById(divId).style.display = 'block';
}
/**
 * 媒体,公告，关于我们，联系我们等左侧菜单
 * @param cc 需要到div的id
 * @param selected 选中的菜单id
 */
function getLeftMenuxmlHttpAjax(cc,selected) {
	var xmlHttp = getRequest();
	xmlHttp.open("POST","../index/leftmenu.jsp",false);
	xmlHttp.setRequestHeader("content-type","application/x-www-form-urlencoded");
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4) {
			if(xmlHttp.status == 200) {
			    var responseText = xmlHttp.responseText;
				var finance = document.getElementById(cc);
				if(finance != null && responseText.length != 0){
					finance.innerHTML = responseText;
					$("#"+selected).addClass("current");
				}else{

				}
			}
		}
	};
	xmlHttp.send(null);
}

function getMassge(url,name,code){
	var showmsg = "";
	$.ajax({
			 url:""+url,
		     dataType: "text",
		     async: false,
		     data:"name="+name+"&code="+code,
			 success: function(msg){
				 showmsg = msg;
			 },
			 error:function(){
				showmsg = "错误";
			 }
	    });
	return showmsg;
}
//登录后个人信息提示
function firstLogin(){
    var userphone = $(".user-phone").text();
    var reg=new RegExp("(^| )isConfrimed_"+userphone+"=([^;]*)(;|$)");
    var arr = document.cookie.match(reg);
    var arr2 = null;
    if(window.localStorage){
    	arr2 = window.localStorage.getItem("isConfrimed_"+userphone);
	}
    if( (arr==null || arr[2] != 'isConfrimed') && arr2!='isConfrimed'  ){
    	$('body').addClass('personal-prompt');
        $('.personal-prompt .userinfo-up').show();
    }
    $('body').click(function(){
		$('body').removeClass('personal-prompt');
		document.cookie="isConfrimed_"+userphone+"=isConfrimed;path=/;domain=jinxin99.cn;expires=Fri, 31 Dec 9999 23:59:59 GMT";
		if(window.localStorage){
			window.localStorage.setItem("isConfrimed_"+userphone,"isConfrimed");
		}
    });
}
//头部账户效果
$(function(){
	if($(".user-phone").text() != null && $(".user-phone").text() != ''){
		firstLogin();
	}
	//getAccount();
	//页头页脚用到的js,程序写页面时无需在此页面加载下面的js，只需在页头页脚独立文件加载即可
	//手机客户端下拉层
	$(".phone-ewm").hover(function(){
		$(this).addClass('phone-ewm-hover');
	},function(){
		$(this).removeClass('phone-ewm-hover');
	});

	//二维码弹出层显示隐藏
	$(".head-weixin").click(function(){
		$("#dialog,#ewm").fadeIn();
	});
	$("#ewm .close").click(function(){
		$(this).parent().fadeOut();
		$("#dialog").fadeOut();
	});
	//页脚友情链接
	var linksHeight=$(".links-list").innerHeight();
	$(".links-list").css({height:'30px'});
	$("#links-down").click(function(){
		if($(this).hasClass('links-up')){
			$(this).removeClass("links-up");
			$(this).prev().stop().animate({ height: '30px'},500);
		}else{
			$(this).addClass("links-up");
			$(this).prev().stop().animate({ height: linksHeight},500);
		}
	});
	//关闭温馨提示
	$(".footer-hint .hint-close").click(function(){
		$(this).parent().fadeOut();
	});
	//返回顶部
	$(window).scroll(function(){
		if ($(window).scrollTop()>100){
			$("#back-to-top").fadeIn(1500);
		}
		else
		{
			$("#back-to-top").fadeOut(1500);
		}
	});
	//当点击跳转链接后，回到页面顶部位置
	$("#back-to-top").click(function(){
		$('body,html').animate({scrollTop:0},100);
		return false;
	});

	//getMessageNum();
	if($('.user_name,.user-phone').html() != null && $('.user_name,.user-phone').html() != "" && (location.href.indexOf("/account/") == 0 || location.href.indexOf("/cashbox/") == 0)){
		  var url = $(".urlPath").val();
		  $.ajax({
				 type:"POST",
				 url:url+"/user/getMember.do?myTime=" + new Date(), //
			     dataType: "text",
			     async: false,
				 success: function(msg){
					 if(msg != null && msg != ''){
						 var json = eval('(' + msg + ')');
						 if(json.level == "0"){
							 $('#member,#member_over').html("普通会员");
						 }else if(json.level == "1"){
							 $('#member,#member_over').html("VIP1");
						 }else if(json.level == "2"){
							 $('#member,#member_over').html("VIP2");
						 }else if(json.level == "3"){
							 $('#member,#member_over').html("VIP3");
						 }else if(json.level == "4"){
							 $('#member,#member_over').html("VIP4");
						 }
					 }else{
						 $('#member,#member_over').html("普通会员");
					 }
		 		  },
				error:function(){
					//alert("页面加载失败，请重试！");
				  }
		   });
	  }
	
});
/*function getAccount(){
	var url = $(".urlPath").val();
	$.post(url+"/account/getAvailableMoney.do",function(data){
		$("#frame_top").html(data);
		$("#frame_top").show();
		$("#frame_top").next('img').hide();
	},"text");
}*/
function getMessageNum(){
	var url = $(".urlPath").val();
	if(url == undefined){
		return;
	}
	$.post(url+"/account/getMessageNum.do",function(data){
		if(0<data){
			if(data>99)data=9+"+";
			$(".head_message").removeClass("no_message");
			$(".messageNum").html(data);
			$(".messageNum").show();
		}
	},"text");
}
//实名认证  交易密码  start
function popupComplete(path,conpath,type){
	$.ajax({
		 type:"POST",
		 url:conpath+"/ssl/completePopup.do?type="+type, //查看用户欲往操作   0投 1充 2提  3绑卡 4好易联充值
	     dataType: "text",
	     async: false,
		 success: function(data){
			 if(data == 'complete' ){
				 if(type == '4'){
				 }else{
					 window.location.href =path;
				 }
			    }else if( data == 'login'){
			    	window.location.href = "../../webPage/ssl/login.html";
			    }else{
			    	popupCompleteMsg(data,0,conpath,type);
			    }
		  },
		error:function(){
			//alert("页面加载失败，请重试！");
		  }
  });
}

//实名认证  交易密码  start
function popupCompleteindex(path,conpath,type){
	$.ajax({
		 type:"POST",
		 url:conpath+"/ssl/completePopup.do?type="+type, //查看用户欲往操作   0投 1充 2提  3绑卡 4好易联充值
	     dataType: "text",
	     async: false,
		 success: function(data){
			 if(data != null && data == 'complete'){
				 if(type == '4'){
				 }else{
					 window.location.href =path;
				 }
			 }else if(data != null && data == 'login'){
				 window.location.href = "../ssl/login.html";
			 }else{
				popupCompleteMsg(data,0,conpath,type);
			 }
		  },
		error:function(){
			//alert("页面加载失败，请重试！");
		  }
  });
}

function popupCompleteMsg(type,status,conpath,msg) {
	//type 是实名认证还是交易密码    status 成功还是尚未开始   msg 0投 1充 2提 3绑卡  4好易联充值
	var myTime = new Date().getTime();
	$.ajax({
		 type:"POST",
		 url:conpath+"/ssl/completeMsg.do?msg="+msg, //
	     dataType: "text",
	     async: false,
		 success: function(data){
			 $('body').append(data);
			 $("#earnMaskMsg").show();
			 $("#"+type).show();
			 
			 var vHeight=$("#"+type).innerHeight();
			 $("#"+type).css({"marginTop":-vHeight/2});
			 $(".queding").click(function(){
			 	$("#earnMaskMsg").remove();
			 	$("#earnMsg").remove();
			 	if(msg == "4" && status != "1"){
			 		//重新跳转到充值页面
			 		window.location.href ="../../ssl/cashbox/getRecharge.do?mytime="+myTime;
			 	}else if(status == "1"){
			 		window.location.href ="../../ssl/cashbox/AccountSet.do?mytime="+myTime;
			 	}
			 });	
			 if(msg == "0"){
				 $(".dobeforething").attr("href","../../webPage/invest/wytz.html");
			 }else if(msg == "1"){
				 $(".dobeforething").attr("href","../../ssl/cashbox/getRecharge.do?mytime="+myTime);
			 }else if(msg == "2"){
				 $(".dobeforething").attr("href","../../ssl/cashbox/getBankAccountlist.do?mytime="+myTime);
			 }else if(msg == "3"){
				 $(".dobeforething").attr("href","../../ssl/cashbox/getBankAccountlist.do?mybank=true&mytime="+myTime);
			 }else if(msg == "4"){
				 $(".dobeforething").attr("href","../../ssl/cashbox/getRecharge.do?mytime="+myTime);
			 }else if(msg == "5"){
				 $(".dobeforething").attr("href","../../webPage/goldtip/jinzhi.html");
			 }else if(msg == "6"){//散标投资
				 $(".dobeforething").attr("href","../../webPage/invest/standard_list.html");
			 }else if(msg == "7"){
	//			 $(".dobeforething").attr("href","../../");
				 $(".dobeforething").attr("href","../../webPage/index/index.html");
			 }else{
				 $("#earnMaskMsg").remove();
				 $("#earnMsg").remove();
			 }
		 },
		error:function(){
			//alert("页面加载失败，请重试！");
		  }
 });
}