<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<link rel="stylesheet" href="${ctx}/static/front/css/login/style.css">
	<link rel="stylesheet" href="${ctx}/static/front/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="${ctx}/static/front/js/jquery.min.js"></script>
	<script src="${ctx}/static/front/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src='${ctx}/static/front/js/style.js'></script>
	<script type="text/javascript">
	var _ctx='${ctx}';
	</script>
	<title>登录</title>
</head>
<style>

</style>
<body >
<!-- <div class="bj"> -->
<header>
		<img src="${ctx}/static/front/images/login/logo.png" alt="">
	</header>
<!-- </div> -->
	
	<div class="city">
	<img src="${ctx}/static/front/images/login/4.png" alt="">
	<div class="denglu">
	<div class="titBian">
		<span class="tit">用户登录</span>

	</div>
		<form id="loginForm"  action="${ctx }/login/to_login"method="post">
		<div class="fengexian"></div>
		<div class=" alert-danger dengLuTiShi"  id="errorDiv" ><span  class="dengLuTiShiTu glyphicon glyphicon-minus-sign"></span><span  id="error" class="cuo">${errormsg }</span></div>

		<div class="input-group name">
  			<span class="input-group-addon tx1"  id="basic-addon1"><img id="tx" class="tupian" src="${ctx}/static/front/images/login/tuyiyi-com-0.png"  alt=""></span>
  			<input type="text" id="phone" onclick="phone2()" name="username"  class="form-control" autocomplete="off" placeholder="请输入手机号或账号" aria-describedby="basic-addon1">
  			<span class="input-group-addon cha1" id="cha" onclick="qingkong()" >X</span>
		</div>
		<div class="input-group password">
  			<span class="input-group-addon tx1"  id="basic-addon2"><img src="${ctx}/static/front/images/login/7.png" id="mm" class="tupian" alt=""></span>
  			<input type="password"  id="mima"  name="password"  class="form-control" onclick="mma()" autocomplete="off" placeholder="请输入密码" aria-describedby="basic-addon1">
		</div>
		<span class="wangjimima" onclick="wangji()">忘记密码</span>
		  <button  id="login_btn" type="button" class="btn btn-primary btnDL">登录</button>
		</form>
		<%
	//shiro 获取异常,判断异常类型
	Object errorClass=request.getAttribute("shiroLoginFailure");
	if("org.apache.shiro.authc.IncorrectCredentialsException".equals(errorClass)){
	
		request.setAttribute("errormsg", "用户名或密码错误");
	}
	if("org.apache.shiro.authc.UnknownAccountException".equals(errorClass)){
		request.setAttribute("errormsg", "用户不存在或已禁用");
	}
	if("com.dsj.data.shiro.filter.VerifyCodeException".equals(errorClass)){
		request.setAttribute("errormsg", "验证码不正确");
	}
	%>

	</div>

	</div>

	
	
	<div class="yun">
		<div class="kuanCont">
			<a class="kuan">&nbsp;&nbsp;联系我们010-64791900&nbsp;&nbsp;</a>
			<a class="kuan kuanLast">&nbsp;&nbsp;北京大搜家信息技术服务有限公司&nbsp;&nbsp; </a>
		</div>
	</div>
	
	<script type="text/javascript">
	$(function(){
		$("#login_btn").off().on("click", function() {
			var loginFlag = vailPhone();
			if (loginFlag != false) {
				$("#loginForm").submit();
			}
		});
		if('${errormsg }'!=''){
			 var errorDiv = document.getElementById('errorDiv');
			$("#error").html("${errormsg }");
			  errorDiv.style.visibility='visible';
		}
		
	})
	</script>
	<script src="${ctx }/static/back/login/login.js"></script>
</body>
</html>

