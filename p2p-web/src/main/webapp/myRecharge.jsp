<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>金利金融网 - 专业的互联网金融信息服务平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cashbox-share.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fund-guanli.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
<script type="text/javascript">
$(function(){
	$("#myCash").attr("class","on");
})
$(document).ready(function() {
	$("#overView").removeClass("on");
	$("#myAccount").addClass("on");
});
$(document).ready(function(){
	$("#chongzhijilu").addClass("on");
});
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
    <div class="right-wap">
     <div class="deal-data">
      <dl>
       <dt>
       	<span class="deal-time">充值时间</span>
       	<span class="deal-name">充值描述</span>
       	<span class="deal-type" style="width:120px">类型</span>
       	<span class="deal-money">充值金额(元)</span>
       	<span class="deal-balance">充值状态</span>
       </dt>

       <%--<dd>
	       <div class="deal-time">2016-09-18 10:23:11</div>
	       <div class="deal-name">支付理财</div>
	       <div class="deal-type" style="width:120px">充值</div>
	       <div class="deal-money">100.0</div>
	       <div class="deal-balance">
	       		充值成功
	       </div>
       </dd>
       <dd>
	       <div class="deal-time">2016-09-18 10:23:11</div>
	       <div class="deal-name">支付理财</div>
	       <div class="deal-type" style="width:120px">充值</div>
	       <div class="deal-money">100.0</div>
	       <div class="deal-balance">
	       		充值中
	       </div>
       </dd>
       <dd>
	       <div class="deal-time">2016-09-18 10:23:11</div>
	       <div class="deal-name">支付理财</div>
	       <div class="deal-type" style="width:120px">充值</div>
	       <div class="deal-money">100.0</div>
	       <div class="deal-balance">
	       		充值失败
	       </div>
       </dd>--%>
		  <!-- 判断充值状态 0:充值中,1充值成功，2:充值失败 -->
		  <c:forEach items="${rechargeRecordList}" var="rechargeRecord">
			  <dd>
				  <div class="deal-time"><fmt:formatDate value="${rechargeRecord.rechargeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				  <div class="deal-name">${rechargeRecord.rechargeDesc}</div>
				  <div class="deal-type" style="width:120px">充值</div>
				  <div class="deal-money">${rechargeRecord.rechargeMoney}</div>
				  <div class="deal-balance">
					  <c:choose>
						  <c:when test="${rechargeRecord.rechargeStatus eq 0}">
							  充值中
						  </c:when>
						  <c:when test="${rechargeRecord.rechargeStatus eq 1}">
							  充值失败
						  </c:when>
						  <c:otherwise>
							  充值成功
						  </c:otherwise>
					  </c:choose>

				  </div>
			  </dd>
		  </c:forEach>
		  &nbsp;&nbsp;
		<div class="touzi_fenye" style="width:100%; text-align:center;line-height:30px;">
			共${totalRows}条${totalPage}页　当前为第 ${currentPage} 页
			<c:choose>
				<c:when test="${currentPage eq 1}">
					首页
					上一页
				</c:when>
				<c:otherwise>
					<a id="linkHomePage" href="${pageContext.request.contextPath}/loan/myRecharge">首页</a>
					<a id="linkPreviousPage" href="${pageContext.request.contextPath}/loan/myRecharge?currentPage=${currentPage-1}">上一页</a>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${currentPage eq totalPage}">
					下一页
					尾页
				</c:when>
				<c:otherwise>
					<a id="linkNextPage" href="${pageContext.request.contextPath}/loan/myRecharge?currentPage=${currentPage+1}">下一页 </a>
					<a id="linkLastPage" href="${pageContext.request.contextPath}/loan/myRecharge?currentPage=${totalPage}">尾页</a>
				</c:otherwise>
			</c:choose>
		</div>
      </dl>
     </div>
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