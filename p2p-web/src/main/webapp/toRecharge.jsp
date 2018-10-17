<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="keywords" content="金利金融网，投资理财，P2P理财，互联网金融，投资理财，理财，网络贷款，个人贷款，小额贷款，网络投融资平台, 网络理财, 固定收益, 100%本息保障" />
<meta name="description" content="金利金融网-专业的互联网金融平台！预期年化收益可高达13%，第三方资金托管，屡获大奖。"/>
<title>金利金融网-专业的互联网金融公司</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/center.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fund-guanli.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/security.css"/>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#overView").removeClass("on");
	$("#myAccount").addClass("on");
	$('#chongzhi').addClass('on');
	//选择第三种支付方式
	$('#alipay').click(function(){
        $(".error-hint").hide();
		$(this).addClass('img_on').append("<i></i>");
		$(this).parent().siblings().children().removeClass('img_on');
		$("#rechargeForm").attr("action","${pageContext.request.contextPath}/loan/toAlipayRecharge");
	});
	$('#weixinpay').click(function(){
        $(".error-hint").hide();
		$(this).addClass('img_on').append("<i></i>");
		$(this).parent().siblings().children().removeClass('img_on');
        $("#rechargeForm").attr("action","${pageContext.request.contextPath}/loan/toWeixinRecharge");
	});
});
function checkData() {
	var alipay = $("#alipay").hasClass("img_on");
	var weixinpay = $("#weixinpay").hasClass("img_on");
	if (alipay==false && weixinpay==false) {
		$("#p1_1").html("请选择支付平台");
		$(".input_text").addClass("input-red");
		$("#div1_1").show();
		return false;
	}
	var doubleType=/^-?\d+\.?\d{0,3}$/;
	var rechargeMoney = $("#rechargeMoney").val();
	if (rechargeMoney=="") {
		$("#p1_1").html("请输入充值金额");
		$(".input_text").addClass("input-red");
		$("#div1_1").show();
		return false;
	} else if (!doubleType.test(rechargeMoney)) {
		$("#p1_1").html("充值金额最多有两位小数");
		$(".input_text").addClass("input-red");
		$("#div1_1").show();
		return false;
	} else {
		$(".input_text").removeClass("input-red");
		$("#div1_1").hide();
		return true;
	}
}
//提交表单前验证表单数据
function submitForm () {
	if (checkData()) {
		$(".input_text").removeClass("input-red");
		$("#div1_1").hide();
		$("#dialog-overlay").show();
		$("#failurePayment").show();
		$("#rechargeForm").submit();
	}
}
</script> 
</head>

<body>
<!--页头start-->
<div id="header">
<jsp:include page="commons/header.jsp"/>
</div>
<!--页头end-->

<!-- 二级导航栏start -->
<jsp:include page="commons/subNav.jsp"/>
<!-- 二级导航栏end -->

<!--页中start-->
<div class="mainBox">
 <div class="homeWap">
  <div class="fund-guanli clearfix">
   <div class="left-nav">
   		<jsp:include page="commons/leftNav.jsp"/>
   </div>
   
   <div class="right-body">
    <div class="leftTitle"><span class="on">第三方支付平台</span></div>
    
    <!--未认证start-->
    <c:if test="${empty user.name}">
    <div class="unrecognized" style = "display:block;" id="unrecognized1">
     <h3>您尚未通过实名认证，通过实名认证后可进行充值操作</h3>
     <a class="input_btn" href="${pageContext.request.contextPath}/realName.jsp">认 证</a>
    </div>
    </c:if>
   	<!--未认证end-->
   	
   	<!-- 充值start -->
   	<c:if test="${!empty user.name}">
   	<form id="rechargeForm" method="post" target="_blank">
    <div class="payInvest" style="display:block;">
          <div class="investMain">
            <h3>请选择支付平台</h3>
            <div class="investContent">
              <div class="investOver clearfix" id = "banks2">
                <label>
                <div class="img_cnt" id="alipay">
                	<img src="${pageContext.request.contextPath}/images/alipay.jpg"/>
                </div>
                </label>
                <label>
                <div class="img_cnt" id="weixinpay">
                	<img src="${pageContext.request.contextPath}/images/weixinpay.jpg"/>
                </div>
                </label>
              </div>
            </div>
          </div>
          <div class="investFooter clearfix">
            <h3>请输入充值金额</h3>
            <div class="inputTxt">
              <input type="text" name="rechargeMoney" id="rechargeMoney" class="input_text" onblur="checkData()"/>
              <span class="input-yuan">元</span>
              <div class="error-hint" style="display:none" id="div1_1"><i></i>
              <p id = "p1_1">充值金额必须是大于1的正数，且最多有两位小数</p></div>
            </div>
            <button id="action_1" type="button" onclick="submitForm()">支&nbsp;&nbsp;付</button>
          </div>
          <div class="tips">
            <h3>温馨提示</h3>
            <p>1. 为了您的使用安全，充值操作建议不要使用公共电脑。<br />
               2. 银行卡充值限额，由各银行限制。<br />
               3. 平台禁止信用卡套现、虚假交易、洗钱等行为，一经发现并确认，将终止该账户的使用。<br />
               4. 如果充值中出现问题，请联系客服400-890-0000。</p>
          </div>
      </div>
   	  </form>
   	  </c:if>
   	  <!-- 充值end -->
   </div>
  </div>      
 </div>
</div>
<!--页中end-->
 
<!--遮罩层-->
<div class="dialog-overlay" id="dialog-overlay" style="display:none;"></div>

<!--支付start-->
<div class="layer-body failureSuccess failurePayment" id="failurePayment" style="display:none;width:500px;height:380px;top:65%;">
  <a class="layer-close" href="${pageContext.request.contextPath}/toRecharge.jsp"></a>
  <div class="layer-top" style="background:#3cbe9b;height:105px;">支付</div>
  <div class="layerBottom">
        <div class="txt-cnt">请在新打开的页面上完成支付，<br/>由于第三方支付渠道问题，所以偶尔存在到账延迟的情况<br/>如没有及时到账，请您等候几分钟或联系客服 400-880-0000。</div>
        <div class="failureClose clearfix">
		    <a class="fl" href="${pageContext.request.contextPath}/toRecharge.jsp">支付遇到问题<br/>重新支付</a>
            <a class="fr" href="${pageContext.request.contextPath}/loan/myRecharge">支付完成</a>
        </div>
    </div>
</div>
<!--支付end-->

<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->
</body>
</html>