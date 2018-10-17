<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>金利金融网-CFCA认证的互联网金融公司</title>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/trafficStatistics.js"></script>
<link rel="stylesheet" type="text/css" href="http://www.jinxin99.cn/static/ssl/css/share.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/security.css"/>
<script type="text/javascript">
$(document).ready(function() {
	$("#overView").removeClass("on");
	$("#myAccount").addClass("on");
	$("#wodexinxi").addClass("on");
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
      		<!-- 账户设置中左边菜单start -->
		    <div class="left-nav">
			  <jsp:include page="commons/leftNav.jsp"/>
		    </div>
      		<!-- 左边菜单end -->
      		
            <div class="right-body securityCenter">
                <div class="right-wap">
                    <div class="securityOuter">
                        <div class="securityTitle">
                            <h3>这里可以管理您账户的基本信息</h3>
                        </div>
                        <dl>
                            <dt>
                                <img id="user_photo" src="${pageContext.request.contextPath}/images/header.png" width="108" height="108" onerror="this.src='${pageContext.request.contextPath}/images/header.png'"/>
                                <a href="javascript:alert('稍后开启，敬请期待')">上传头像</a>
                            </dt>
                            <dd>
                                <span class="name">姓名</span>
                                <span class="txt" title="">张三</span>
								<span class="type f_green">已认证</span>
                            </dd>
                            <dd>
                                <span class="name">身份证号</span>
                                <span class="txt">222222222222222222</span>
                                <span class="type f_green">已认证</span>
                            </dd>
                            <dd>
                                <span class="name">手机号码</span>
                                <span class="txt">13900000005</span>
                                <span class="type f_green">已绑定</span>
							    <span class="revision"><a href="javascript:alert('稍后开启，敬请期待')">修改</a></span>
                            </dd>
                            <dd>
                                <span class="name">账户密码</span>
                                <span class="txt">
                                	<em class="on">低</em><em class="on">中</em><em>高</em>
                                </span>
                                <span class="type f_green">已设置</span>
                                <span class="revision"><a href="javascript:alert('稍后开启，敬请期待')">修改</a></span>
                            </dd>
                            <dd>
                                <span class="name">注册时间</span>
                                <span class="txt">2009-08-12 09:23:44</span>
                            </dd>
                            <dd>
                                <span class="name">最近登录</span>
                                <span class="txt">2017-08-12 11:23:12</span>
                            </dd>
                        </dl>
                    </div>
                    <div class="securityOuter contacts" >
                        <div class="securityTitle">
                            <h3>紧急联系人信息</h3>
                            <div class="text">紧急联系人是在紧急情况下能够联系到与当事人相关的人</div>
                            <div class="click">
								<a href="javaScript:alert('稍后开启，敬请期待')" class="a_button">添加</a>
                            </div>
                        </div>
                        <dl>
                            <dd>
                                <span class="name">紧急联系人姓名</span>
                                <span class="txt"></span>
                                <span class="type">紧急联系人手机号码</span>
                                <span class="revision"></span>
                            </dd>
                        </dl>
                    </div>
                    <div class="securityOuter address" >
                        <div class="securityTitle">
                            <h3>联系地址</h3>
                            <div class="text">用于邮寄资料和礼品</div>
                            <div class="click">
								<a class="a_button" href="javascript:alert('稍后开启，敬请期待')" id="modify">添加</a>
                            </div>
                        </div>                        
                        <!--显示地址信息begin-->
			            <dl id="addressDiv">
			              <dd style="height:auto;" class="clearfix"> 
			              	<span class="name">联系地址</span> 
			              	<span class="txt"  id="contactAddress" style="height:auto;line-height:20px; padding-top:9px;"></span> 
			              	<span class="name">邮编</span> 
			              	<span class="revision"  id="contactPostCode"></span> 
			              </dd>
			            </dl>
			            <!--显示地址信息end-->
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