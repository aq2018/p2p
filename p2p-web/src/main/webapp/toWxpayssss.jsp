<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>正在加载...</title>
</head>
<body>
<form action="${wxpay_pay_url}" method="post">
	<input type="hidden" name="total_fee" value="${total_fee}"/>
    <input type="hidden" name="out_trade_no" value="${out_trade_no}"/>
	<input type="hidden" name="body" value="${body}"/>
	<%--<input type="hidden" id="returnUrl" name="returnUrl" value="${alipay_return_url}"/>
	<input type="hidden" id="notifyUrl" name="notifyUrl" value="${alipay_notify_url}"/>--%>
</form>
<script>document.forms[0].submit();</script>
</body>
</html>