<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="keywords" content="	楼盘信息，购房指导内容，新房销售，二手房买卖，购房需求，端口，大搜家">
<meta name="description" content="大搜家为经纪人提供卖房端口，房产销售品台，个人 运营平台，为经纪人匹配精准购房客户，帮助经纪人实现成交。">
<title>经纪人登录-大搜家</title>
<script type="text/javascript" src="${ctx}/app/js/fles.js"></script>
<!-- Bootstrap -->
<!-- Bootstrap -->
<link rel="icon" href="${ctx}/app/img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/app/img/favicon.ico" type="image/x-icon">
<link href="${ctx}/app/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/app/css/login.css">
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
				<h1>经纪人</h1>
			</div>
		</header>
		<!-- mian -->
		<main class="BingMain">
		<div class="BingMainLaft">
            <p>梦想开始的地方</p>
            <p>
                <span>大搜家成就你的专业梦想</span>
            </p>
        </div>
		<div class="LoginKuang">
			<h4>经纪人登录</h4>
			<!-- 错误提示 -->
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
              <div class="errorHint" >
                ${errormsg}
              </div>
          <!-- form -->
          <form class="form-inline" id="loginForm"  action="${ctx }/login/to_login" method="post">
            <!--账号  -->
            <div class="passworgRegister">
	            <div class="form-group">
	              <div class="input-group">
	                <label class="user_leftIcon" for="user_label"></label>
	                <input type="text" class="form-control ursnameCode"  name="username" autocomplete="off" id="user_label" placeholder="请输入账号">
	                 <div class="demptyText"></div>
	              </div>
	            </div>
           
	            <!-- 输入密码 -->
	            <div class="form-group">
	              <div class="input-group">
	                <label class="password_leftIcon" for="pass_label"></label>
	                <input type="password" name="password" class="form-control passwordCode2" autocomplete="off" id="pass_label" placeholder="请输入密码">
	                <div class="showText">
	                   <label for="showText" class="showTextlabel"></label>
	                   <input name="showText" id="showText" type="checkbox" value="" />
	                 </div>
	              </div>
	            </div>
	            
	             <!-- 验证码 -->
	            <div class="form-group">
	              <div class="input-group">
	              	 <label class="verification_leftIcon" for="verification_label"></label>
	                <input type="text"  name="verifyCode" class="form-control verifyCode" autocomplete="off"   id="verification_label" placeholder="请输入验证码">
	                <div class="verifyCodeButton verifyCode_2">
	                  <img id="imgObj" src="${ctx}/login/verifyCode" onclick="changeImg('${ctx}')" alt="点击更换"/>
	                </div>
	                <!-- <button type="buttom" class="btn btn-primary verifyCodeButton">获取验证码</button> -->
	              </div>
            	</div>
            	<!-- 确认 -->
            <button type="button" onclick="submitClick()" id="submitId" class="login_botton countersign">确认</button>
            </div>
            <!-- 手机号登录 -->
                <div class="phoneRegister"  style="display: none">
                  <!--账号  -->
                  <div class="form-group">
                    <div class="input-group">
                       <label class="user_leftIcon" for="user_label"></label>
                      <input type="text" class="form-control phone ursnameCode"  id="phoneId" placeholder="请输入手机号">
                       <div class="demptyText"></div>
                    </div>
                  </div>
                  <!-- 验证码 -->
                  <div class="form-group">
                    <div class="input-group">
                     <label class="verification_leftIcon" for="verification_label"></label>
                      <input type="text" class="form-control verifyCode" id="verifyCodeId" placeholder="请输入验证码">
                      <div class="verifyCodeButton" style="  cursor: pointer;">
                      	  获取验证码
                      </div>
                      <!-- <button type="buttom" class="btn btn-primary verifyCodeButton">获取验证码</button> -->
                    </div>
                  </div>
                  <!-- 确认 -->
            <button type="button" onclick="phoneLogin()" class="login_botton countersign">确认</button>
                </div>
           
            <!-- 用户协议 -->
            <div class="checkbox autoRegister userAgreement">
              <label class="check_yess">
                <input id="checkboxId" type="checkbox">下次自动登录</a>
              </label>
              <ul class="forget">
                    <li><a href="${ctx}/app/forgetPassword.html">忘记密码</a></li>
                    <li><a href="${ctx}/app/register.html">立即注册</a></li>
              </ul>
            </div>
          </form>
          
      </div>
    </main>
      </div>
  <!-- footer -->
    <footer class="BingFooter">
      <ul class="footer1">
         <li><a href="http://www.dasoujia.com/about/page?about=0">关于我们</a></li>
         <li><a href="http://www.dasoujia.com/about/page?about=1">平台协议</a></li>
         <li><a href="http://www.dasoujia.com/about/page?about=2">合作伙伴</a></li>
      </ul>
      <ul class="footer2">
        	<li>北京大搜家信息技术服务有限公司&nbsp;&nbsp;</li>
            <li>京ICP备17018930号&nbsp;&nbsp;</li>
            <li>Copyright © 2017 大搜家 All Rights Reserved</li>
      </ul> 
    </footer>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ctx}/app/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ctx}/app/js/bootstrap.js"></script>
    <script src="${ctx}/app/js/handlebars.js"></script>
    <script type="text/javascript" src="${ctx}/app/js/register.js"></script>
    <script type="text/javascript">
	var _ctx="${ctx}"
    // 点击table切换
    $(".registerH4").on("click",function(e){
      	var e = e || window.event,
        liText = e.target.innerHTML;
        $("#loginForm")[0].reset();
        if(liText == "手机快捷登录"){
          $(".passworgRegister").hide();
          $(".phoneRegister").show();
          $("#typeId").val(2);
          $(".autoRegister label").html("<input type='checkbox' id='checkboxId'> 我已阅读<a href='##'>用户协议</a>");
        }else{
          $(".passworgRegister").show();
          $(".phoneRegister").hide();
          $("#typeId").val(1);
          $(".autoRegister label").html("<input type='checkbox'> 下次自动登录");
        }
    });
    
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
		if(msg!=""){
			debugger;
			$(".errorHint").css("visibility","visible");
		}
		

	})
	
	function submitClick(){
		var verificationLabel = $("#verification_label").val();
		if (verificationLabel) {
			$("#loginForm").submit();
		} else {
			showErrorMsg("请输入验证码");
		}

			
					
// 		if($("#checkboxId").is(':checked')){
// 		}else{
// 			$(".errorHint").css("visibility","");
// 			$(".errorHint").html("请阅读用户协议");
// 		}
	}
	
	function phoneLogin(){
		var code=$("#verifyCodeId").val();
    	if(code!=null&&code!=''){
	    		$.ajax({
	            type:"post",
	            url:_ctx+"/loginuser/find_passsword_verify",
	            dataType:"json",
	            data:{"phone":$("#phone").val(),"code":code},
	            success:function(data){
	                if(data.status == 200){
	                	window.location=_ctx+"/app/new-password.html";
	                }else{
	                	showErrorMsg(data.message);
	                }
	            }
	        	});
    	}else{
    		showErrorMsg("请输入验证码");
    	}
	}
	
	var mybutton = $(".verifyCodeButton").html();
    /*$(".verifyCodeButton").on("click",function () {
		    var myreg = /^[0-9]{11}$/;       
		    var phone=$("#phoneId").val();
		   	if(myreg.test(phone)){
		   	$(".errorHint").css("visibility","hidden"); 
		   		sendVcode(phone);
			      //event.stopPropagation(); 
			      mybutton = 120;
			      $(".verifyCodeButton").addClass("verifyCodeActive").attr("disabled", true);
			      var time = setInterval(function(){
			    	if(stop==1){
			    		mybutton=0;
			    	}
			        mybutton--;
			        $(".verifyCodeButton").html(mybutton+"s重新获取");
			        if(mybutton <= 0){
			          clearInterval(time);
			          $(".verifyCodeButton").removeClass("verifyCodeActive").html("获取验证码").removeAttr("disabled");
			        }
			      },1000);
		    }else{
		    	showErrorMsg("您输入的手机号有误，请重新输入");
		    }
    })*/
    
    var stop = 0;
    function sendVcode(){
    	$.ajax({
            type:"post",
            url:_ctx+"/loginuser/send_vcode",
            dataType:"json",
            data:{"phone":$("#phoneId").val()},
            success:function(data){
                if(data.status == 200){
                  
                }else{
                	stop=1;
                	showErrorMsg(data.message);
                }
            }
        });
    }

  //错误提示
    function showErrorMsg(msg){
        $(".errorHint").css("visibility","visible"); 
        $(".errorHint").html(msg);
        setTimeout(function(){
            $(".errorHint").css("visibility","hidden"); 
        },2000)
    };
    // 单选框
    $(".check_yess").on("click",function(event){
      var inputItem = $(this).find("input");
       inputItem.prop('checked')?inputItem.parent().addClass('check_yess_active') : inputItem.parent().removeClass('check_yess_active');
      event.stopPropagation();
    });
	
	
  </script>
</body>
</html>