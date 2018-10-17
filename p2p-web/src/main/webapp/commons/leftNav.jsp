<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<dl>
  <dt>我的账户</dt>
  <dd id="chongzhi"><a href="${pageContext.request.contextPath}/toRecharge">充值</a><span></span></dd>
  <dd id="wodexinxi"><a href="${pageContext.request.contextPath}/myAccount">我的信息</a><span></span></dd>
  <%-- <dd id="jiaoyijilu"><a href="${pageContext.request.contextPath}/myDeal.jsp">交易记录</a><span></span></dd> --%>
  <dd id="touzijilu"><a href="${pageContext.request.contextPath}/loan/myInvest">投资记录</a><span></span></dd>
  <dd id="chongzhijilu"><a href="${pageContext.request.contextPath}/loan/myRecharge">充值记录</a><span></span></dd>
  <dd id="shouyijilu"><a href="${pageContext.request.contextPath}/loan/myIncome">收益记录</a><span></span></dd>
</dl>