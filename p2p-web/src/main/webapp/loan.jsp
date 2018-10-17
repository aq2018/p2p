<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="keywords" content="散标，理财产品，投资产品，投资理财" />
    <meta http-equiv="description" content=""/>
    <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
    <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/huaceng_new.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <style type="text/css">body{background:#f2f2f2;}</style>
    <title>散标,互联网金融产品列表-金利金融网,专业的互联网金融信息服务平台</title>
</head>

<body>
<div id="header">
	<jsp:include page="commons/header.jsp"/>
</div>

<!--产品列表start-->
<div id="loanList">
	<div class="mainBox pro-list-body clearfix" id="rollingLayer">
  	<div class="pro-list <c:if test="${productType eq 2}">disperse-pro</c:if>">
	  <c:forEach items="${loanInfoList}" var="loanInfo">
	   	<!--已满标时追加pro-full-->
		<div class="pro-box <c:if test="${loanInfo.leftProductMoney eq 0}">pro-full</c:if>">
		    <div class="pro-top">
		      <h3>${loanInfo.productName}</h3>
		      <p>金利金融网特选小额信贷消费产品</p>
		    </div>
		    <div class="pro-main" style="height:260px;">
		      <div class="pro-rate">
	        <div class="rate">${loanInfo.rate}<span>%</span></div>
		        <h3>历史年化收益率</h3>
		      </div>
		      <div class="pro-data clearfix">
		        <div class="pro-cycle">
		          <h3>投资周期</h3>
		          <div class="data"><span>${loanInfo.cycle}</span>个月</div>
		        </div>
		        <div class="pro-money">
		          <h3>剩余可投金额</h3>
		          <div class="data">
					<span>${loanInfo.leftProductMoney}</span>元
				  </div>
		        </div>
		      </div>
		      <div class="pro-btn">     
				<a href="${pageContext.request.contextPath}/loan/loanInfo?loanId=${loanInfo.id}" class="btn-1">立即投资</a><!--已满标按钮-->
		      </div>
		    </div>
		  </div>
	  </c:forEach>
	  
	  <div style="clear:both"></div>
      <!--页码 start-->
	  <table class="page_bar">
		<tbody>
		<tr>
			<td>
				共${totalRows}条${totalPage}页　当前为第${currentPage}页　
			<c:choose>
			<c:when test="${currentPage eq 1}">
				首页
				上一页
			</c:when>
			<c:otherwise>
				<a id="lnkNext" href="${pageContext.request.contextPath}/loan/loan">首页</a>
				<a id="lnkNext" href="${pageContext.request.contextPath}/loan/loan?ptype=${ptype}&currentPage=${currentPage-1}">上一页</a>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${currentPage eq totalPage}">
				下一页
				尾页
			</c:when>
			<c:otherwise>
				<a id="lnkNext" href="${pageContext.request.contextPath}/loan/loan?ptype=${ptype}&currentPage=${currentPage+1}">下一页 </a>
				<a id="lnkNext" href="${pageContext.request.contextPath}/loan/loan?ptype=${ptype}&currentPage=${totalPage}">尾页</a>
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</tbody>
	</table>
	<!--页码 end-->
  	</div>
  
	<div class="right-side">
	    <div class="side-box fixed" id="roll" style="top: 0px;">
	        <div id="advertArea"></div>
	        <!--投资排行榜begin-->
	        <div class="system-notice" style="margin-top: 0px;">
	            <div class="system-tit"><h3>投资排行榜</h3></div>
	            <ul>
	            <c:forEach items="${topUserBidList}" var="topUser" varStatus="index">
	            	<li><span>${index.count}.&nbsp;${fn:substring(topUser.phone,0,3)}^-^${fn:substring(topUser.phone,9,11)}&nbsp;&nbsp;${topUser.totalMoney} 元</span></li>
	            </c:forEach>
	            </ul>
	        </div>
	        <!--投资排行榜end-->
	        <!--广告位begin-->
	        <div style="margin-top:20px;"><a href=""><img src="${pageContext.request.contextPath}/images/ad.jpg" alt=""/></a></div>
	        <!--广告位end-->
	    </div>
	</div>
	</div> 
</div>
<!--产品列表end-->

<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->
</body>
</html>