<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
   	<meta name="keywords" content="互联网金融平台，互联网金融产品，互联网金融公司"/>
   	<meta name="description" content="金利金融网是动力教育集团旗下一站式互联网金融平台.接入支付清算协会风险系统，用互联网技术和金融创新使投资与借贷更透明、更高效.优选互联网金融产品随心选。"/>
    <title>金利金融网官网-专业的互联网金融信息服务平台</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ingot.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nivo.slider.pack.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
    <style type="text/css">body{background:#f2f2f2;}</style>
</head>

<body>
<!-- 页头start -->
<div id="header">
<jsp:include page="commons/header.jsp"/>
</div>
<!--页头end-->

<!--轮播图start-->
<div class="flashImg">
    <div class="slider-wrap">
	    <div class="slider-items" style="position:relative; overflow:hidden;">
	    	<ul class="slider" id="slider" style="display:block;"></ul>
		</div>
	</div>
		
    <div class="registerBody">
        <div class="register-bg"></div>
        <c:choose>
        <c:when test="${empty user}">
        <!-- 未登录状态 -->
        <div class="register">
            <h3>金利金融网历史年化收益率</h3>
            <h4><span id="avg">${historyAverageRate}</span>%</h4>
            <div class="bn_register"><a href="${pageContext.request.contextPath}/register" class="btn-1">免费注册领红包</a></div>
            <div class="login">有账号? <a href="${pageContext.request.contextPath}/login">立即登录</a></div>
        </div>
        </c:when>
        <c:otherwise>
		<!-- 登录状态   -->
        <div class="register welcome">
            <div class="welcome-txt">欢迎 <span>“<b>${user.phone}</b>”</span><br/>来金利金融网投资！</div>
            <div class="bn_register"><a href="${pageContext.request.contextPath}/loan/myCenter" class="btn-1">进入我的小金库</a></div>
        </div>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<!--轮播图end-->
<div class="exit"><a href="${pageContext.request.contextPath}/loan/logout">退出</a></div>
<!--品牌宣传begin-->
<div class="brand-promotion">
    <div class="brand-up" id="brand-up"><i></i></div>
    <div class="mainBox">
        <div class="brand-left">
            <ul>
                <li class="brand-1"><i></i><h3>实力雄厚</h3><p>亿级注册资本<br />千万技术投入</p></li>
                <li class="brand-2"><i></i><h3>风控严苛</h3><p>30道风控工序<br />60项信用审核</p></li>
                <li class="brand-3"><i></i><h3>投资省心</h3><p>资金安全风控<br />银行安全托管</p></li>
            </ul>
        </div>
        <div class="jinxin-data">
            <h3>平台用户数</h3>
            <div class="data"><span id="hzxx_ljrs">${allUserCount}</span>位</div>
            <div class="line"></div>
            <h3>累计成交额</h3>
            <div class="data"><span id="hzxx_ljje">${allBidMoney}</span>元</div>
        </div>
    </div>
</div>
<!--品牌宣传end-->

<!--投资产品begin-->
<div id="productArea">
	<!--新手宝 begin-->
	<div class="index-exper">
	<c:forEach items="${xLoanInfoList}" var="xLoanInfo">
	
     <div class="inner">
          <div class="index-exper-top">
               <span class="title"><font style="font-size:28px;">${xLoanInfo.productName}</font></span>
               <p class="text">${xLoanInfo.bidMinLimit }元起投<em>丨
               </em>投资最高限额${xLoanInfo.bidMaxLimit}元<em>丨</em>当日计息</p>
          </div>
          <div class="index-exper-bottom">
               <ul class="list cf">
                   <li class="box1">
                       <p class="text1">${xLoanInfo.rate }<span>%</span></p>
                       <p class="text2">历史年化收益率</p>
                   </li>
                   <li class="box2">
                       <p class="text1">${xLoanInfo.cycle }<span>天</span></p>
                       <p class="text2">投资周期</p>
                   </li>
                   <li class="box3">
                       <p class="text1">${xLoanInfo.leftProductMoney }<span>元</span></p>
                       <p class="text2">剩余可投金额</p>
                   </li>
                   <li class="box4">
					   <a href="${pageContext.request.contextPath}/loan/loanInfo?loanId=${xLoanInfo.id}" class="go-invest">立即投资</a>
                   </li>
               </ul>
          </div>
	</c:forEach>
     </div>
	</div>
	<!--新手宝 end-->

	<!-- 优选计划start -->
	<div class="mainBox pro-body clearfix">
	<c:forEach items="${uLoanInfoList}" var="uLoanInfo">
		<!-- 1:已满标时class样式追加 pro-full-->
		<div class="pro-box <c:if test='${uLoanInfo.productStatus != 0 }'>pro-full</c:if>">
			<div class="pro-top">
				<h3>${uLoanInfo.productName }</h3>
				<p>短期小额信用借款集合投资计划</p>
			</div>
			<div class="pro-main">
				<div class="pro-rate">
					<div class="rate">
						${uLoanInfo.rate }<span>%</span>
					</div>
					<h3>历史年化收益率</h3>
				</div>
				<div class="pro-data clearfix">
					<div class="pro-cycle">
						<h3>投资周期</h3>
						<div class="data"><span>${uLoanInfo.cycle }</span>个月</div>
					</div>
					<div class="pro-money">
						<h3>剩余可投金额</h3>
						<div class="data">
							<span>${uLoanInfo.leftProductMoney}</span>元
						</div>
					</div>
				</div>
				<div class="pro-intr"><p>优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。</p></div>
				<div class="pro-btn">
					<a href="loan/loanInfo?loanId=${uLoanInfo.id}" class="btn-1">立即投资</a>
				</div>
			</div>
		</div>
	
	</c:forEach>
	</div>
	<div class="pro-more"><i class="left-i"></i><a href="loan/loan?ptype=1">查看更多优选类产品</a><i class="right-i"></i></div>
	<!-- 优选计划end -->
	
	<!-- 散标start -->
	<div class="mainBox pro-body disperse-pro clearfix">
	  	<div class="new-pro-icon"><img src="images/new-pro-icon.png"/></div>
		<c:forEach items="${sLoanInfoList}" var="sLoanInfo" varStatus="index">
		<!-- 1:已满标时追加pro-full-->
		<div class="pro-box <c:if test='${sLoanInfo.productStatus != 0 }'>pro-full</c:if>">
			<div class="pro-top">
				<h3>${sLoanInfo.productName }</h3>
				<p>金利金融网特选小额理财产品</p>
			</div>
			<div class="pro-main">
				<div class="pro-rate">
					<div class="rate">${sLoanInfo.rate }<span>%</span></div>
					<h3>历史年化收益率</h3>
				</div>
				<div class="pro-data clearfix">
					<div class="pro-cycle">
						<h3>投资周期</h3>
						<div class="data"><span>${sLoanInfo.cycle }</span>个月</div>
					</div>
					<div class="pro-money">
						<h3>剩余可投金额</h3>
						<div class="data">
							<span>${sLoanInfo.leftProductMoney }</span>元
						</div>
					</div>
				</div>
				<div class="pro-btn">     
					<a href="loan/loanInfo?loanId=${sLoanInfo.id}" class="btn-1">立即投资</a>
				</div>
			</div>
		</div>
		
		<c:if test="${index.count eq 4}">
		<!-- 4个一组，分隔线 -->
		<div style="line-height:25px;">&nbsp;</div>
		</c:if>
		</c:forEach>
	</div>
	<div class="pro-more"><i class="left-i"></i><a href="loan/loan?ptype=2">查看更多散标类产品</a><i class="right-i"></i></div>
	<!-- 散标end -->
</div>
<!--投资产品end-->

<!--底部品牌宣传start-->
<div class="media-section">
    <div class="section-inner">
        <div class="clearfix" id="mtbdList">
        </div>
        <div class="brand-section">
            <ul class="clearfix">
                <li>
                    <i class="icon-1"></i>
                    <h3>优质借款</h3>
                    <p>严苛风控，多重审核</p>
                </li>
                <li>
                    <i class="icon-2"></i>
                    <h3>次日计息</h3>
                    <p>闪电成交，谁比我快</p>
                </li>
                <li>
                    <i class="icon-3"></i>
                    <h3>全年无休</h3>
                    <p>天天可投，永不打烊</p>
                </li>
                <li>
                    <i class="icon-4"></i>
                    <h3>知心托付</h3>
                    <p>百万用户，一路同行</p>
                </li>
                <li>
                    <i class="icon-5"></i>
                    <h3>技术保障</h3>
                    <p>千万投入，专注研发</p>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--底部品牌宣传end-->

<!--合作机构begin-->
<div class="partners-seciton">
    <div class="mainBox clearfix">
        <div class="partners-tit">合作机构</div>
        <div class="partners-img">
            <div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()"></div>
            <div class="pic-cnt" id="ISL_Cont">
                <div class="pic-wap">
                    <div id="List1">
                        <!-- 图片列表 begin -->
                        <div class="pic"><a title="金融安全认证权威机构"  href="http://webverify.cfca.com.cn/WebVerify/webVerifyServlet?domain=www.powernode.com" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_1.jpg"/></a></div>
                        <div class="pic"><a title="专业的网络安全专家" href="http://cn.norton.com/" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_5.jpg"  /></a></div>
                        <div class="pic"><a title="领先的诚信信息服务商" href="http://q.id5.cn/" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_4.jpg"  /></a></div>
                        <div class="pic"><a title="IBM" href="http://www.ibm.com/cn/zh/" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_6.jpg" /></a></div>
                        <div class="pic"><a title="NSFOCUS" href="http://www.nsfocus.com.cn/index.html" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_12.jpg" /></a></div>
                        <div class="pic"><a title="CISCO" href="http://www.cisco.com/web/CN/index.html" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_13.jpg" /></a></div>
                        <div class="pic"><a title="连连支付" href="http://www.lianlianpay.com/" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_14.jpg" /></a></div>
                        <div class="pic"><a title="宝付" href="https://www.baofoo.com/" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_16.jpg" /></a></div>
                        <div class="pic"><a title="富友" href="http://pay.fuiou.com/" target="_blank" rel="nofollow"><img alt="" src="images/logo_cooperate_17.jpg" /></a></div>
                        <!-- 图片列表 end -->
                    </div>
                    <div id="List2"></div>
                </div>
            </div>
            <div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()"></div>
        </div>
    </div>
</div>
<!--合作机构end-->

<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->

<!--返回顶部-->
<div class="back_to_top" id="back-to-top"><a href="#top"></a></div>

<script type="text/javascript">
//加载轮播图
$(document).ready(function() {
	loadCarouselPicture();
});
//轮播图(先静态写死)
function loadCarouselPicture() {
	var liStr = "<li data-opacity='0.2' style='display: none; background: url(";
	liStr += "img/001.jpg) 50% 50% no-repeat;'><a href='";
	liStr += "javascript:void(0)";
	liStr += "' target='_blank'></a></li>";
	liStr += "   ";
	liStr += "<li data-opacity='0.2' style='display: none; background: url(";
	liStr += "img/002.jpg) 50% 50% no-repeat;'><a href='";
	liStr += "javascript:void(0)";
	liStr += "' target='_blank'></a></li>";
	liStr += "   ";
	liStr += "<li data-opacity='0.2' style='display: none; background: url(";
	liStr += "img/003.jpg) 50% 50% no-repeat;'><a href='";
	liStr += "javascript:void(0)";
	liStr += "' target='_blank'></a></li>";
	liStr += "   ";
	liStr += "<li data-opacity='0.2' style='display: none; background: url(";
	liStr += "img/004.jpg) 50% 50% no-repeat;'><a href='";
	liStr += "javascript:void(0)";
	liStr += "' target='_blank'></a></li>";
	liStr += "   ";
	$("#slider").html(liStr);
	//初始化轮播图
	slideSwitch();
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scroll.1.3.js"></script>
</body>
</html>