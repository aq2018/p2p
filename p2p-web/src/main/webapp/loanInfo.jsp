<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>金利金融网-CFCA认证的互联网金融公司</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />

</head>

<body>
<div id="header">
<jsp:include page="commons/header.jsp"/>
</div>

<!--散标投资 begin-->
<div id="sbtz" class="invest-details">

<!--页中begin-->
<div class="mainBox pro-details-body">
  <div class="homeWap clearfix" id="huacengPar">
    <div class="pro-details-left">
      <!-- 产品详情start -->
      <div class="pro-info-details">
        <div class="pro-name">
          <h2><span>${loanInfo.productName}</span>(${loanInfo.productNo}期)</h2>
        </div>
        <div class="pro-info">
          <ul class="clearfix">
            <li class="info-1">
              <p>历史年化利率</p>
              <h3>${loanInfo.rate }<span>%</span></h3>
              <div class="info-bt">
              <span>本产品采用普通利率</span>
              </div>
            </li>
            <li class="info-2">
              <p>募集金额(元)</p>
              <h3>${loanInfo.productMoney }</h3>
              <div class="info-bt">
              	<span>
              	<c:choose>
              	<c:when test="${loanInfo.productStatus eq 0}">
              		<!-- 只有状态为0时为募集，其它都为已满标 -->
              		募集中 | 剩余募集金额 ${loanInfo.leftProductMoney}元
              	</c:when>
              	<c:otherwise>
              		<!-- 已满标 -->
					已满标              	
              	</c:otherwise>
              	</c:choose>
              	</span>
              </div>
            </li>
            <li class="info-3">
              <p>投资周期</p>
              <c:choose>
              <c:when test="${loanInfo.productType eq 0}">
              <h3>${loanInfo.cycle }<span>天</span></h3>
              </c:when>
              <c:otherwise>
              <!-- 只有新手宝产品周期为天 -->
              <h3>${loanInfo.cycle}<span>个月</span></h3>
              </c:otherwise>
              </c:choose>
              <div class="info-bt"><span></span></div>
            </li>
          </ul>
        </div>
        <dl class="pro-syfs">
          <dt><span>收益获取方式</span></dt>
          <dd><span>收益返还：</span>到期还本付息</dd>
        </dl>
      </div>
      <!-- 产品详情end -->
      
      <!-- 投资记录start -->
      <div class="pro-details-cnt">
        <ul class="tabNav clearfix">
          <li><a id="one3" href="javascript:void(0);" class="s">投资记录</a></li>
        </ul>
        
        <div class="invest-record" id="con_one_3" style="display:block">
        <div class="vertical-side">投资列表</div>
		<dl class="record-list">
		<dt>
			<span class="record-num">序号</span>
			<span class="invest-user">投资人</span>
			<span class="invest-money">投资金额(元)</span>
			<span class="invest-time">投资时间</span>
		</dt>
		<c:choose>
		<c:when test="${empty bidInfoList}">
			<!-- 如果投资记录为空，显示以下文字 -->
			<dd style="text-align:center;">该产品暂时还没有人投资，赶快去投资吧~</dd>
		</c:when>
		<c:otherwise>
			<c:forEach items="${bidInfoList}" var="bidInfo" varStatus="index">
				<!-- 如果有投资记录，循环遍历显示 -->
				<dd>
					<span class="record-num">${index.count }</span>
					<span class="invest-user">${fn:substring(bidInfo.user.phone,0,3)}^-^${fn:substring(bidInfo.user.phone,9,11)}</span>
					<span class="invest-money">${bidInfo.bidMoney }</span>
					<span class="invest-time"><fmt:formatDate value="${bidInfo.bidTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</dd>
			</c:forEach>
		</c:otherwise>
		</c:choose>
		</dl>
        <dd style="text-align:center;cursor: pointer" id="more" ><a onclick="more()">查看更多</a></dd>
		</div>
      </div>
      <!-- 投资记录end -->
    </div>
    <script>
        function formatDate(timestamp) {
            var date = new Date(timestamp);
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = (date.getMinutes()+1 < 10 ? '0'+(date.getMinutes()+1) : date.getMinutes()+1) + ':';
            var s = (date.getSeconds()+1 < 10 ? '0'+(date.getSeconds()+1) : date.getSeconds()+1);
            return Y+M+D+h+m+s;
        }
        var currentPage = ${currentPage};
        function getCurrentPage() {
            currentPage += 1;
            return currentPage;
        }

        function more() {
            var currentPage = getCurrentPage();
            var totalPage = ${totalPage};
            if(currentPage >= totalPage){
                $("#more").html("没有更多了");
                return;
            }
            $.ajax({
                type : "get",
                url : "loan/loanInfoMore",
                data : {
                    "loanId" : "${param.loanId}",
                    "currentPage" : currentPage
                },
                success : function (jsonObject) {
                    var html = "";
                    var i = (currentPage-1) * ${pageSize} + 1;
                    $(jsonObject).each(function () {
                        html += '<dd>';
                        html += '<span class="record-num">' + (i++)+'</span>';
                        html += '<span class="invest-user">' + (this.user.phone).substring(0,3) +'^-^' + (this.user.phone).substring(9,11) + '</span>';
                        html += '<span class="invest-money">' + this.bidMoney +'</span>';
                        html += '<span class="invest-time">' + formatDate(this.bidTime) +'</span>';
                        html += '</dd>';
                    });
                    $(".record-list").append(html);
                }
            });
        }
    </script>
    <!--页面右侧begin-->
    <div class="pro-details-right">
      <div class="right-calculator" id="huaceng">
        <div class="calculator-cnt">
          <h2>立即投资</h2>
          <dl class="profits-mode">
            <dt>收益获取方式</dt>
            <dd class="clearfix"><span id="fanhuan"><em>到期还本付息</em></span></dd>
          </dl>
          <dl class="usable">
            <dt>我的账户可用</dt>
            <dd>资金(元)：
            <c:choose>
            <c:when test="${empty user}">
            	<!-- 判断用户是否登录：未登录，显示登录连接 -->
            	<span style="font-size:18px;color:#ff6161;vertical-align:bottom;"><a href="${pageContext.request.contextPath}/login">请登录</a></span>
            </c:when>
            <c:otherwise>
	          	<!-- 判断用户是否登录：已登录，显示可用余额 -->
         		<span style="font-size:18px;color:#ff6161;vertical-align:bottom;">${financeAccount.availableMoney}</span>
            </c:otherwise>
            </c:choose>
	        
	        
            </dd>
          </dl>
          <div class="expect-box">
            <div class="expect-money">预计本息收入(元)：<span id="shouyi" class="money"></span><span class="prompt" style="display:block;">请在下方输入投资金额</span></div>
            <input type="text" id="bidMoney" name="bidMoney" onblur="checkMoney();" placeholder="请输入投资金额，应为100元的整倍数" maxlength="9"/>
            <div class="max-invest-money"></div>
          </div>
          <div class="invest-btn">
          	<a id="investNow" href="javascript:void(0)" class="btn-1" onclick="invest();">立即投资</a>
          </div>
          <input type="hidden" id="loanId" name="loanId" value="${loanInfo.id}"/>
        </div>
      </div>
    </div>
    <!--页面右侧end-->
  </div>
</div>
<!--页中end-->

</div>
<!--散标投资 end-->

<!--遮罩层-->
<div class="dialog-overlay" id="dialog-overlay1" style="display:none;"></div>

<!--投资成功浮层start-->
<div class="layer-body failureSuccess failurePayment" id="failurePayment" style="display:none;width:500px;height:100px;top:75%;">
  <a class="layer-close" href="javascript:closeit();"></a>
  <div style="background:#f2f2f2; line-height:105px;text-align:center;"><font style="font-size:25px;">投资成功</font></div>
</div>
<!--投资成功浮层end-->

<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->

<script type="text/javascript">
//验证用户输入的投资金额
function checkMoney() {
	//获取用户输入的投资金额
	var bidMoney = $.trim($("#bidMoney").val());
	//获取产品详情信息
	var productType = "${loanInfo.productType}";//产品类型
	var rate = "${loanInfo.rate}";//产品利率
	var cycle = "${loanInfo.cycle}";//产品周期
	
	/* 
	验证用户输入投资金额 
	a)  投资金额不能为空 
	b)  投资金额应为大于0 的整数 
	c)  投资金额应为100的整数 
	d)  投资金额不能低于起投金额 
	e)  单笔投资金额不能超过最大投资限额 
	f)  验证通过计算预计本息收益 
	2.  计算预计本息收益，公式如下： 
	本息收益  =  投资金额  *  （年化利率/100/360）*  投资周期
	投资周期：单位为天 */
	
	if("" == bidMoney) {
		$(".max-invest-money").html("");
		$(".max-invest-money").html("请输入投资金额");
		return false;
	} else if(isNaN(bidMoney)) {
		$(".max-invest-money").html("");
		$(".max-invest-money").html("投资金额只能为数字");
		return false;
	} else if(bidMoney <= 0) {
		$(".max-invest-money").html("");
		$(".max-invest-money").html("投资金额应为大于0的数字");
		return false;
	} else if(bidMoney % 100 != 0) {
		$(".max-invest-money").html("");
		$(".max-invest-money").html("投资金额应为100的整数倍");
		return false;
	} else {
		$(".max-invest-money").html("");
		//计算收益 = 投资金额 * 天利率 * 投资周期（单位为天）
		var incomeMoney = "";
		//根据产品周期分类：新手宝（周期为天）和优先、散标（周期为月）
		if("0" == productType) {
			//新手宝产品
			incomeMoney = bidMoney * (parseFloat(rate) / 100 / 360) * cycle;
		} else {
			//优先或散标产品
			incomeMoney = bidMoney * (parseFloat(rate) / 100 / 360) * cycle * 30;
		}
		
		//Math.pow(x,y)：指x的y次幂
		incomeMoney = Math.round(incomeMoney * 100) / 100;
		$("#shouyi").html(incomeMoney);
	}
	return true;
}

//用户投资
function invest() {
	/*
		投资业务规则
			1.用户是否登录
			2.用户是否进行实名认证
			3.投资金额是否小于启投金额
			4.投资金额是否超过单笔投资限额
			5.投资金额是否超过产品剩余可投金额
	*/
	
	//获取用户的投资金额
	var bidMoney = $.trim($("#bidMoney").val());
	//获取产品详情信息
	var user = "${user}";
	var name = "${user.name}";//用户真实姓名
	var loanId = "${loanInfo.id}";//产品标识
	var bidMinLimit = "${loanInfo.bidMinLimit}";//最小投资金额
	var bidMaxLimit = "${loanInfo.bidMaxLimit}";//最大投资限额
	var leftProductMoney = "${loanInfo.leftProductMoney}";//产品剩余可投金额
	var availableMoney = "${financeAccount.availableMoney}";//帐户可用余额
	
	if(checkMoney()) {
		
		if("" == user) {
			if(confirm("您尚未登录，请登录")) {
				window.location.href = "${pageContext.request.contextPath}/login";
			}
		} else if("" == name) {
			if(confirm("您尚未实名认证，请去实名认证")) {
				window.location.href = "${pageContext.request.contextPath}/loan/realName";
			}
		} else if(parseFloat(bidMoney) < parseFloat(bidMinLimit)) {
			$(".max-invest-money").html("");
			$(".max-invest-money").html("起投金额不得低于" + bidMinLimit + "元");
		} else if(parseFloat(bidMoney) > parseFloat(bidMaxLimit)) {
			$(".max-invest-money").html("");
			$(".max-invest-money").html("投资金额不得高于" + bidMaxLimit + "元");
		} else if(parseFloat(bidMoney) > parseFloat(leftProductMoney)) {
			$(".max-invest-money").html("");
			$(".max-invest-money").html("投资金额不得超过产品剩余可投金额" + leftProductMoney + "元");
		} else if(parseFloat(bidMoney) > parseFloat(availableMoney)) {
			$(".max-invest-money").html("");
			$(".max-invest-money").html("帐户余额不足，请充值");
		} else {
			$(".max-invest-money").html("");
			$.ajax({
				url:"${pageContext.request.contextPath}/loan/invest",
				type:"post",
				data:{
					"bidMoney":bidMoney,
					"loanId":loanId
				},
				success:function(jsonObject) {
					if(jsonObject.errorMessage == "ok") {
						$("#dialog-overlay1").show();
						$("#failurePayment").show();

					} else {
						$(".max-invest-money").html("");
						$(".max-invest-money").html(jsonObject.errorMessage);
					}
				},
				error:function() {
					$(".max-invest-money").html("客官请稍后再投资...");
				}
			});
			
		}
		
		
	}
	
	
}

function closeit() {
	$("#failurePayment").hide();
	$("#dialog-overlay1").hide();
	window.location.href="${pageContext.request.contextPath}/loan/myCenter";
}

</script>
</body>
</html>