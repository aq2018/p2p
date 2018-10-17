<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="keywords" content="金利金融网，动力金融，动力金融财富,金元宝，金元宝，网络理财，个人理财，投资理财，P2P理财，互联网金融，投资理财，债权转让，理财，网络贷款，个人贷款，理财服务，网贷，小额贷款，网络投融资平台, 网络理财,固定收益,100%本息保障" />
<meta name="description" content="金利金融网用户注册，专业的互联网金融信息服务平台"/>
<title>登录金利金融网-金利金融网,专业的互联网金融信息服务平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.md5.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
</head>

<body>
<!--页头start-->
<div id="header">
<jsp:include page="commons/header.jsp"/>
</div>
<!--页头end-->

<div class="login-body">
  <div class="mainBox">
    <div class="homeWap">
      <div class="login-cnt clearfix">
        <div class="login-form">
		     <h2>欢迎登录</h2>
		     
		     <div class="login-box clearfix" style="z-index:10;">
		     <label>账号</label>
		     	<input id="phone" name="phone" type="text" class="input_text" placeholder="请输入11位手机号码" maxlength="11" onblur="checkPhone()"/>
		     </div>
		     
		     <div class="login-box clearfix" style="z-index:8; padding-bottom:0px;">
		     <label>密码</label>
		     	<input id="loginPassword" name="loginPassword" type="password" placeholder="请输入登录密码" class="input_text" maxlength="16" onblur="checkLoginPassword()"/>
		     </div>
		     
		     <div class="login-yzm">
		     <div id="showCaptcha" class="yzm-box" style="display:block;">
			 	<input id="captcha" type="text" class="yzm" placeholder="点击右侧图片可刷新" onblur="checkCaptcha()"/>
			 	<img src="${pageContext.request.contextPath}/jcaptcha/captcha" alt="图片看不清？点击重新得到验证码" id="imgCode" onclick="this.src='${pageContext.request.contextPath}/jcaptcha/captcha?d='+new Date().getTime()" style="cursor:pointer;border:0; display:inline;vertical-align:middle;"/>
		     </div>
		     <ul><li id="showId" style="color:red;font-size:12px;width:222px;margin-top:10px;margin-bottom:10px;line-height:18px;"></li></ul>
		     </div>
		     
		     <div class="bn-login" id="loginId"><button onclick="login()">登&nbsp;&nbsp;录</button></div>
    	</div>
    	
        <div class="login-right">
          <div class="login-boy"></div>
          <div class="top-txt">
		          金利金融网，便捷的投资平台
			<br/><br/>
			加入金利金融网<br/>坐享<span class="historyAverageRate">&nbsp;&nbsp;&nbsp;&nbsp;</span>%历史年化收益
          </div>
           
          <div class="currently">
          	平台用户数：<span id="user">&nbsp;&nbsp;&nbsp;&nbsp;</span>位，累计成交额： <span id="gold">&nbsp;&nbsp;&nbsp;&nbsp;</span>元
          </div>
          
          <div class="bn-reg" id="quickRegBtn">
            <button onclick="location.href='${pageContext.request.contextPath}/register'">马上注册</button>
          </div>
          
           <ul class="clearfix">
            <li><i></i>
              <h3>优选投资计划</h3>
              <p>
              	历史年化收益<span><span class="historyAverageRate"></span>%</span><br/>
                                        投资周期<span>1-12月</span>灵活选择
              </p>
            </li>
            <li class="jpsb"><i></i>
              <h3>优质借款</h3>
              <p>
              	投资周期短<br/>
                                        安全计划
              </p>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->
</body>
</html>