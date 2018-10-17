<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>正在加载...</title>
</head>
<body>
<form action="${alipay_pay_url}" method="post">
	<%--<input type="hidden" id="uid" name="uid" value="${sessionScope.user.id}"/>--%>
	<input type="hidden" id="totalAmount" name="totalAmount" value="${rechargeMoney}"/>
    <input type="hidden" id="outTradeNo" name="outTradeNo" value="${rechargeNo}"/>
	<input type="hidden" id="subject" name="subject" value="购买理财产品"/>
	<input type="hidden" id="body" name="body" value="p2p理财支付"/>
	<%--<input type="hidden" id="returnUrl" name="returnUrl" value="${alipay_return_url}"/>
	<input type="hidden" id="notifyUrl" name="notifyUrl" value="${alipay_notify_url}"/>--%>
</form>
<script>document.forms[0].submit();</script>
</body>
</html>