<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
<title>注册金利金融网-金利金融网,专业的互联网金融信息服务平台</title>
</head>

<body>
<div id="header">
<jsp:include page="commons/header.jsp"/>
</div>

<div class="login-body regBody">
  <div class="mainBox">
    <div class="homeWap">
    
      <div class="reg-step">
      <ul class="clearfix">
        <li>注册</li>
        <li class="none">实名认证</li>
        <li class="last">完成</li>
      </ul>
      </div>
      
      <div class="login-cnt reg-body clearfix">
        <div class="fl">
            <div class="reg-link">已有帐号？<a href="${pageContext.request.contextPath}/login">登录</a></div>
            <div class="login-form reg-form">
              <h2>用户注册</h2>
              
              <div class="login-box clearfix" style="z-index:100;">
                <label>手机号</label>
                <input type="text" id="phone" name="phone" class="input_text" maxlength="11" placeholder="请输入11位手机号码" autocomplete="off" onblur="checkPhone();"/>
                <span id="phoneOk" class="ok" style="display:none;"></span>
                <div id="phoneErr" class="form-hint" style="display:none;"></div>
              </div>
              
              <div class="login-box clearfix " style="z-index:90;">
                <label>登录密码</label>
                <input type="password" id="loginPassword" name="loginPassword" class="input_text" maxlength="20"  placeholder="请输入6-20位英文和数字混合密码" autocomplete="off" onblur="checkLoginPassword();"/>
                <span id="loginPasswordOk" class="ok" style="display:none;"></span>
                <div id="loginPasswordErr" class="form-hint" style="display:none;z-index:90;"></div>
              </div>
              
              <div class="login-box clearfix" style="z-index:80;">
                <label>确认密码</label>
                <input type="password" id="replayLoginPassword" name="replayLoginPassword" class="input_text" maxlength="21" placeholder="请再次输入登录密码" onblur="checkLoginPasswordEqu()"/>
                <span id="replayLoginPasswordOk" class="ok" style="display:none;"></span>
                <div id="replayLoginPasswordErr" class="form-hint" style="display:none;"></div>
              </div>
              
              <div class="login-box clearfix" style="z-index:60;">
                <label>图形验证码</label>
                <div class="yzm-box">
                  <input id="captcha" name="captcha" type="text" class="yzm" maxlength="10" placeholder="点击右侧图片可刷新" onblur="checkCaptcha();"/>
                  <a style='cursor:pointer;'><img src="${pageContext.request.contextPath}/jcaptcha/captcha?d="+new Date().getTime() onclick="this.src='${pageContext.request.contextPath}/jcaptcha/captcha?d='+new Date().getTime()"/></a>
                </div>
                <span id="captchaOk" class="ok" style="display:none;"></span>
                <div id="captchaErr" class="form-hint" style="display:none;"></div>
              </div>
              
              <div class="remember" style="z-index:60;">
                <span class="on">
                <input id="agree" name="agree" type="checkbox"/>我已阅读并同意<a href="javascript:alertBox('mask','agreement');"><font style="color: red;">《金利金融网&trade;注册服务协议》</font></a>
                </span>
              </div>
              
              <div class="bn-login">
                <button class="fail" id="btnRegist" onclick="javascript:register();" disabled>注&nbsp;&nbsp;册</button>
              </div>
              
          </div>
        </div>
        <div class="reg-right">
          <div class="reg-txt clearfix">
            <p>
            	万名用户知心托付<br/>
              	千万级技术研发投入<br/>
            </p>
            <p>
            	12.7%历史年化收益<br/>
              	亿级注册资本平台
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->

<!-- 注册协议模板start -->
<span id="includeAgreement">
	<jsp:include page="commons/registerAagreement.jsp"/>
</span>
<!-- 注册协议模板end -->

<!--遮罩层start-->
<div id="mask" class="dialog-overlay" style="display:none;"></div>
<!--遮罩层end-->

</body>
</html>