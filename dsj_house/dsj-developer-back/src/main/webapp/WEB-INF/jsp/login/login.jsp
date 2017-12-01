<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
	<link rel="icon" href="${ctx}/app/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="${ctx}/app/img/favicon.ico" type="image/x-icon">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>开发商登录-大搜家</title>
     <meta content="新房楼盘，开盘信息，房产信息，购房指导，新房销售" name="keywords">
	<meta content="大搜家作为专业的购房平台，为新房销售提供专业的推广、销售服务，帮助开发商提高楼盘曝光，匹配精准客户，达成房屋成交。" name="description">
    <script type="text/javascript" src="${ctx}/app/js/fles.js"></script>
    <!-- Bootstrap -->
    <link href="${ctx}/app/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/app/css/login.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/app/css/footer.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.src.js"></script>
    <![endif]-->
  </head>
  <body>
      <div id="bingPhone">
      <!-- header -->
        <header class="BingHeader">
          <div class="binglogo"> 
            <h1>大搜家</h1>
          </div>
        </header>
      <!-- mian -->
        <main class="BingMain">
          <div class="BingMainLaft">
            <p></p>
            <p><span>大搜家</span><span>专业房企营销平台</span></p>
          </div>
          <!-- 登录框 -->
          <div class="LoginKuang">
              <!--登录table  -->
              <h4>开发商登录</h4>
              <!-- 错误提示 -->
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
              <div class="errorHint"  id="errorHintId" style="visibility:hidden;">
                ${errormsg}
              </div>
              <!-- form -->
              <form class="form-inline" id="loginForm"  action="${ctx }/login/to_login" method="post">
                <!-- 账户密码登录 -->
                <div class="passworgRegister">
                  <!--账号  -->
                  <div class="form-group">
                    <div class="input-group">
                      <label class="user_leftIcon" for="user_label"></label>
                      <input type="text" name="username" class="form-control ursnameCode" placeholder="请输入您的帐号" id="user_label">
                      <div class="demptyText"></div>
                    </div>
                  </div>
                  <!-- 输入密码 -->
                  <div class="form-group">
                    <div class="input-group">
                       <label class="password_leftIcon" for="pass_label"></label>
                      <input type="password" name="password" class="form-control passwordCode" placeholder="请输入您的密码" id="pass_label" >
                    </div>
                  </div>
                  <!-- 验证码 -->
                  <div class="form-group">
                    <div class="input-group">
                     	<label class="verification_leftIcon" for="verification_label"></label>
                      <input type="text" class="form-control verifyCode" name="verifyCode" id="verification_label" placeholder="请输入验证码">

                      <div class="verifyCodeButton verifyCode_2">
                       <img id="imgObj" src="${ctx}/login/verifyCode" onclick="changeImg('${ctx}')" alt="点击更换"/>
                      </div>
                      <!-- <button type="buttom" class="btn btn-primary verifyCodeButton">获取验证码</button> -->
                    </div>
                  </div>
                </div>
                <!-- 确认登录 -->
                 <button  type="button" onclick="submitClick()"  class="login_botton countersign">登录</button>
                <!-- 下次自动登录 -->
                <div class="checkbox autoRegister">
                  <label class="check_yess">
                    <input type="checkbox"> 下次自动登录
                  </label>
                  <ul  class="forget">
                    <li><a href="${ctx}/app/forget-password.html">忘记密码</a></li>
                    <li><a href="${ctx}/app/register.html" class="color_blue">立即注册</a></li>
                  </ul>
                </div>
              </form>
          </div>
        </main>
        </div>
      <div class="kaifas__footer login_footer">
			<ul class="footer1">
         		<li><a href="http://www.dasoujia.com/about/page?about=0">关于我们</a></li>
         <li><a href="http://www.dasoujia.com/about/page?about=1">平台协议</a></li>
         <li><a href="http://www.dasoujia.com/about/page?about=2">合作伙伴</a></li>
      		</ul>
			<ul class="dsj_lower_footer">
				<li>北京大搜家信息技术服务有限公司&nbsp;&nbsp;|</li>
            	<li>京ICP备17018930号&nbsp;&nbsp;|</li>
            	<li>Copyright © 2017 大搜家 All Rights Reserved</li>
			</ul>
      </div>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="${ctx}/app/js/jquery.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="${ctx}/app/js/bootstrap.js"></script>
      <script src="${ctx}/app/js/handlebars.js"></script>
      <script type="text/javascript" src="${ctx}/app/js/register.js"></script>
  </body>
  <script type="text/javascript">
    //验证码

	function changeImg(ctx){ 
		   var imgSrc = $("#imgObj");     
		   var src = imgSrc.attr("src");

		   imgSrc.attr("src",chgUrl(ctx,src));     
	}     
	//时间戳     
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
	function chgUrl(ctx,url){     
	   var timestamp = (new Date()).valueOf();
		 return  ctx+"/login/verifyCode?timestamp=" + timestamp;
	}
	
	$(function(){
		var msg= "${errormsg}";
		console.info(msg!="");//	visibility: hidden;
		if(msg==null|| msg==''){
			$(".errorHint").css("visibility","hidden");
		}else{
			$(".errorHint").css("visibility","visible");
		}
	})
	
	function submitClick(){
		var b=true;
		
		if($("#user_label").val()==''){
			$(".errorHint").css("visibility","visible");
			$(".errorHint").html("请输入用户名");
			b=false;
		}else if($("#pass_label").val()==''){
			$(".errorHint").css("visibility","visible");
			$(".errorHint").html("请输入密码");
			b=false;
		}else if($("#verification_label").val()==''){
			$(".errorHint").css("visibility","visible");
			$(".errorHint").html("请输入验证码");
			b=false;
		}
		if(b){
			$("#loginForm").submit();
		}
	};
    // 单选框
    $(".check_yess").on("click",function(event){
      var inputItem = $(this).find("input");
       inputItem.prop('checked')?inputItem.parent().addClass('check_yess_active') : inputItem.parent().removeClass('check_yess_active');
      event.stopPropagation();
    });
  </script>
</html>