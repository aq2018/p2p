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
    
    <div class="unrecognized" style = "display:block;" id="unrecognized1">
     <h3>${trade_msg}</h3>
     <a class="input_btn" href="${pageContext.request.contextPath}/loan/intoRecharge">再去充值</a>
    </div>
   	
   </div>
  </div>      
 </div>
</div>
<!--页中end-->
 
<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->
</body>
</html>