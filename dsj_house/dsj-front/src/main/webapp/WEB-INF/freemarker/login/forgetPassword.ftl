<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <meta name="keywords" content="北京房地产,北京新房,北京二手房，购房指南，买房知识，权证服务，二手房过户, 专业经纪人">
		<meta name="description" content="大搜家为购房者提供北京房产信息、北京房产楼盘详情、买房流程等内容信息，同时汇集专业经纪人提供新房、二手房买卖、权证过户等服务。">
		<title>用户忘记密码-大搜家</title>
        <#include "common/taglibs.ftl">
        <!-- Bootstrap -->
        <link rel="icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
        <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/login.css">
        <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/footer.css">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.js"></script>
          <script src="js/respond.src.js"></script>
        <![endif]-->
          <script src="${ctx }/static/front/js/fles.js"></script>
    </head>
    <body>
        <div id="bingPhone">
        <!-- header -->
            <div class="BingHeader">
              <div class="binglogo"> 
                <a href="${ctx }/"><h1>大搜家</h1></a>
              </div>
            </div>
        <!-- mian -->
        <div class="BingMain">
            <div class="BingMainLaft">
              <p>大搜家，让买房不难</p>
            </div>
            <!-- 登录框 -->
            <div class="LoginKuang">
              <!--登录table  -->
              <h4>找回密码</h4>
              <!-- 错误提示 -->
              <div class="errorHint" style="visibility: hidden;"> 
                您输入的手机号有误，请重新输入
              </div>
              <!-- form -->
              <form class="form-inline">
                <!-- 账户密码登录 -->
                <div class="passworgRegister">
                  <!--账号  -->
                  <div class="form-group">
                    <div class="input-group">
                       <label class="user_leftIcon" for="phone"></label>
                      <input type="text" name="phone" class="form-control ursnameCode" id="phone" placeholder="请输入手机号">
                      <div class="demptyText"></div>
                    </div>
                  </div>
                  <!-- 验证码 -->
                  <div class="form-group">
                    <div class="input-group">
                   	  <label class="verification_leftIcon" for="code"></label>
                      <input type="text" class="form-control verifyCode"  name="code" id="code" placeholder="请输入验证码">
                      
                      <button type="button" class="btn btn-primary verifyCodeButton">获取验证码</button>
                    </div>
                  </div>

                </div>
                <!-- 确认登录 -->
                 <button type="button" class="login_botton countersign">下一步</button>
                <!-- 下次自动登录 -->
                <div class="checkbox autoRegister">
                  <a href="${ctx}/login/tologin" class="pull-right color_blue">返回登录</a>
                </div>
              </form>
          </div>
          <div class="clear"></div>
        </div>
      </div>
           <!-- footer -->
       <div class="newhouse_footer login_footer">
            <div class="footer_kuang">
            <ul class="footer1">
                <li><a href="${ctx}/about/page?about=0">关于我们</a></li>
                <li><a href="${ctx}/about/page?about=1">平台协议</a></li>
                <li><a href="${ctx}/about/page?about=2">合作伙伴</a></li>
                <li><a href="http://agent.dasoujia.com/login/to_login">经纪人登录</a></li>
                <li><a href="http://www.dasoujia.com/dsj-developer-back/login/to_login">开发商登录</a></li>
                <li><a href="http://wap.dasoujia.com/dsj-warrant-back/login/to_login">自交易商家登录</a></li>
                <li class="phone_lianxi">4008986868转888</li>
            </ul>
            </div>
            <div class="modules_kuang">
            <ul class="modules_a">
             	<li><a href="${ctx}/">首页</a></li>
                <li><a href="${ctx}/front/newHouse/list">新房</a></li>
                <li><a href="${ctx}/ershoufang">二手房</a></li>
                <li><a href="${ctx}/front/agent">经纪人</a></li>
                <li><a href="${ctx}/warrant/index">自交易</a></li>
                <li><a href="${ctx}/front/entrust/sell">业主委托</a></li>
            </ul>
            </div>
           <div class="footer2_kuang">
            <ul class="footer2">
                <li >北京大搜家信息技术服务有限公司</li>
                <li>Copyright  © 2017 大搜家  All Rights Reserved</li>
                <li class="last-li">京ICP备17018930号</li>
            </ul>
            </div> 
		</div>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="${ctx }/static/front/js/jquery.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="${ctx }/static/front/js/bootstrap.js"></script>
      <script src="${ctx }/static/front/js/handlebars.js"></script>
      <script type="text/javascript" src="${ctx }/static/front/js/register.js"></script>
  </body>
  <script type="text/javascript">
    var stop = 0;
    // 验证码获取
    $(".verifyCodeButton").on("click",function(){
        var _this = $(this);
        if ($(this).hasClass('verifyCodeActive')) {
            return;
        }
        $(this).prop("disabled",true);
        
        
        //下面就是发送ajax请求获取验证码了
        var myreg = /^[0-9]{11}$/;       
        var phone=$("#phone").val();
        if(myreg.test(phone)){
            $(".errorHint").css("visibility","hidden"); 
           
            sendVcode(_this);
        }else{
            showErrorMsg("您输入的手机号有误，请重新输入");
        }
    }); 
    //60s后获取验证码
    function waiteSecurityCode (ele, time) {
        if (!ele) {
            return;
        }
        time = time || 120;
        var btntime = setInterval(function() {
            if (time >= 0&&stop==0) {
            ele.html(time + 's后重新获取');
            time--;
            } else {
            	$(".verifyCodeButton").prop("disabled",false);
                ele.removeClass("verifyCodeActive")
                ele.html("获取验证码");
                clearInterval(btntime);
            }
        }, 1000)
    }
      
    //验证验证码
    $(".countersign").on("click",function () {
        var myreg = /^[0-9]{11}$/;       
        var phone=$("#phone").val();
        if(!myreg.test(phone)){
            showErrorMsg("您输入的手机号有误，请重新输入");
            return;
        }
    	var code=$("#code").val();
    	if(code!=null&&code!=''){
	    		$.ajax({
	            type:"post",
	            url:_ctx+"/login/find_passsword_verify",
	            dataType:"json",
	            data:{"phone":$("#phone").val(),"code":code},
	            success:function(data){
	                if(data.status == 200){
	                	window.location=_ctx+"/login/goNewPassPage";
	                }else{
	                	showErrorMsg(data.message);
	            	}
	        	}
            });
    	}else{
            showErrorMsg("请输入验证码");
    	}
    });

    //发送验证码
    function sendVcode(_this){
        $.ajax({
            type:"post",
            url:_ctx+"/login/send_vcode",
            dataType:"json",
            data:{"phone":$("#phone").val(),type:0},
            success:function(data){
                if(data.status == 200){
                     $(this).addClass("verifyCodeActive").html("120s后重新获取");
            			waiteSecurityCode(_this, 120);
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
    }
    
    
  </script>
</html>