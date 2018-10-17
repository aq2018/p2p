<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
//我要投资下拉
$(function(){
	$(".headBody .main-nav li:eq(1)").hover(function(){
	    $(this).children(".menu-drop").stop(false,true).fadeIn();
	},function(){
	    $(this).children(".menu-drop").stop(false,true).hide();
	});
    //个人信息下拉
    $(".logged").hover(function(){
        $.get(
            "${pageContext.request.contextPath}/loan/myMoney",
            function (json) {
                $("#frame_top").html(json.availableMoney);
            }
        );
        $(this).addClass("logged-hover");
        $(".userinfo-drop-down",this).stop().animate({ height: '205px'},300);
    },function(){
        $(".userinfo-drop-down",this).stop().animate({ height: '0px'},300,function(){$(".logged").removeClass("logged-hover");});
    });
});
</script>
<!--页头start-->
<a id="top"></a>
<div class="headBody" id="headBody">
    <div class="header">
    <div class="mainBox clearfix">
        <div class="head_l"><span>客服电话：400-8080-105</span><em></em><a href="http://weibo.com/aqfun" target="_blank" class="weibo"></a></div>
        <div class="head_r"><a href="javascript:void(0);" id="helpcenter.html">帮助中心</a><em></em><a href="javascript:void(0);" id="/webPage/invest/phone_jinxin.html" class="phone_jinxin" style="margin-right:15px;"><i></i>手机客户端</a>
            <div class="phone-ewm" style="display:none;">
                <a href="javascript:void(0)" class="phone_jinxin"><i></i>手机客户端</a>
                <div class="phone-ewm-dropdown">
                    <span><img src="${pageContext.request.contextPath}/images/phone_jinxin_ewm.png" alt=""></span>
                    <p>下载动力宝APP</p>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="headCnt clearfix">
        <div class="logo"><a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/images/logo.jpg"></a></div>
        <ul class="main-nav">
            <li><h2><a id="jinxin" href="${pageContext.request.contextPath}">首页<i></i></a></h2></li><!--current为当前状态-->
            <li>
            	<h2><a id="wytz" href="${pageContext.request.contextPath}/loan/loan">我要投资<i></i></a></h2>
            	<div class="menu-drop" style="display:none;">
	               <a href="${pageContext.request.contextPath}/loan/loan?ptype=1">优选类产品</a>
	               <a href="${pageContext.request.contextPath}/loan/loan?ptype=2">散标类产品</a>
	            </div>
            </li>
            <li><h2><a id="borrowers" href="javascript:void(0);" class="/loan/borrower">借款人信息<i></i></a></h2></li>
            <li><h2><a id="information" href="javascript:void(0);" class="/webPage/newInfoDisclosure/company-profile.htm">信息披露<i></i></a></h2></li>
            <li><h2><a id="insurance" href="javascript:void(0);" class="/webPage/index/insurance.html">安全计划<i></i></a></h2></li>
        </ul>
        <div class="login-right">
            <c:choose>
                <c:when test="${empty user}">
                    <!-- 未登录start -->
                    <div class="no-login"><a href="${pageContext.request.contextPath}/register" class="btn-reg">注册</a><a href="${pageContext.request.contextPath}/login" class="btn-login">登录</a></div>
                    <!-- 未登录end -->
                </c:when>
                <c:otherwise>
                    <!--登录后begin-->
                    <div class="logged">
                        <div class="userinfo-up">
                            <a href="${pageContext.request.contextPath}/loan/myCenter" class="user-phone">${user.phone}</a><a href="${pageContext.request.contextPath}/webPage/account/member_systems.html" class="user-vip" id="member">VIP1</a><i></i>
                        </div>
                        <div class="userinfo-drop-down" style="height: 0px;">
                            <div class="down-bk">
                                <div class="user-balance">余额：<span id="frame_top"></span>元</div>
                                <ul class="quick-entry clearfix">
                                    <li class="icon-recharge"><a href="toRecharge.jsp"><i></i><span>充值</span></a></li>
                                    <li class="icon-invest" style="margin-left:85px;"><a href="${pageContext.request.contextPath}/loan/loan"><i></i><span>投资</span></a></li>
                                </ul>
                                <div class="enter">进入“<a href="${pageContext.request.contextPath}/webPage/account/acc.html?mytime=1501046257129">我的小金库</a>”</div>
                                <div class="exit"><a href="${pageContext.request.contextPath}/loan/logout">退出</a></div>
                            </div>
                        </div>
                    </div>
                    <!--登录后end-->
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<!--页头end-->

<!--二维码弹出层start-->
<div id="ewm" class="ewm_layer" style="display:none;">
    <a href="javascript:void(0)" class="close"></a>
    <div class="ewm_weixin">
        <h3>动力网服务号</h3>
        <h4>更多服务请关注动力网服务号</h4>
        <div class="ewm"><img src="${pageContext.request.contextPath}/images/ewm_fwh.jpg" alt="动力网服务号"></div>
    </div>
    <div class="ewm_weixin">
        <h3>动力网订阅号</h3>
        <h4>获取动力网最新资讯</h4>
        <div class="ewm"><img src="${pageContext.request.contextPath}/images/ewm_dyh.jpg" alt="动力网订阅号"></div>
    </div>
</div>
<!-- 二维码弹出层end -->