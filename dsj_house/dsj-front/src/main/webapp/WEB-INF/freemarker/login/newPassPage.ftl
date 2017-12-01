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
              <h4>设置新密码</h4>
              <!-- 错误提示 -->
              <div class="errorHint" style="visibility: hidden;">
              
              </div>
              <!-- form -->
              <form class="form-inline">
                <!-- 账户密码登录 -->
                <div class="passworgRegister">
                  <!--新密码  -->
                  <div class="form-group new-password">
                    <label for="password">新密码</label>
                    <div class="input-group">
                      <input type="password" class="form-control" id="password1" name="password1" placeholder="6至20位以内的字母、数字、下划线">
                    </div>
                    <div class="showText">
                        <label for="showText" class="showTextlabel showTextlabel1"></label>
                        <input name="showText" id="showText" type="checkbox" value="" />
                      </div>
                  </div>
                  <!-- 确认密码 -->
                  <div class="form-group new-password">
                    <label for="confirm">确认密码</label>
                    <div class="input-group">
                      <input type="password" class="form-control" id="password2" name="password2" placeholder="请再次输入密码">
                      <div class="showText">
                        <label for="showText" class="showTextlabel showTextlabel2"></label>
                        <input name="showText" id="showText" type="checkbox" value="" />
                      </div>
                    </div>
                  </div>
                  <!-- 确认登录 -->
                  <div class="new-password-buttons">
                     <button type="button" class="login_botton countersign">确定</button>
                  </div>
                  <!-- 下次自动登录 -->
                  <div class="checkbox autoRegister">
                    <ul  class="forget">
                      <li><a href="${ctx}/login/tologin" class="color_blue">返回登录</a></li>
                    </ul>
                  </div>
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
      <div id="popup_box" style="display:none">
        
        <div class=" box_style box_succeed" style="display:none">
            <span class="box_close"></span>
            <div class="box_kuang">
                <div class="box_title "> 
                    <span id="successMsg">成功</span>
                </div> 
                <form>
                    <div class=" buttons">    
                        <button type="button" class="btn submit_botton" id="successBox">确认</button>
                    </div>  
                </form>
            </div>       
        </div>
</div>
       <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="${ctx }/static/front/js/jquery.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="${ctx }/static/front/js/bootstrap.js"></script>
      <script src="${ctx }/static/front/js/handlebars.js"></script>
  	<script type="text/javascript">
  //弹出框
  var box_succeed  = ".box_succeed";   //3
  setTimeout(function(){

  	    $(".submit_botton").on("click",function(event){
  	    	 $("#popup_box>div").hide();
  	         $("#popup_box").hide();
  	         event.stopPropagation();
  	    });
  },2000);
  function show_box(state,msg){
      $("#popup_box").show();
      $('#successMsg').html(msg);
      $(box_succeed).show();

  }
    $(".countersign").on("click",function () {
    	var password2=$("#password2").val();
    	var password1=$("#password1").val();
    	if(!password2 && !password1){
    		$(".errorHint").css("visibility","visible"); 
	        $(".errorHint").html("密码不能为空");
	        return false;
    	}
    	if(password2!=password1){
    		$(".errorHint").css("visibility","visible"); 
	        $(".errorHint").html("二次密码不一致");
	        return false;
    	}
    	if(password1.length<6||password1.length>20){
    	$(".errorHint").css("visibility","visible"); 
	        $(".errorHint").html("密码长度应为6-20位");
    	}else{
    		$.ajax({
	            type:"post",
	            url:_ctx+"/login/update_password",
	            dataType:"json",
	            data:{"password1":password1,"password2":password2},
	            success:function(data){
	                if(data.status == 200){
	                	show_box(3,"修改密码成功,3秒后跳回登录页面");
	                	setTimeout(function(){
	                		location=_ctx+"/login/tologin";
	                	},3000);
	                }else{
	                	$(".errorHint").css("visibility","visible"); 
	    		        $(".errorHint").html(data.message);
	                }
	            }
	        	});
    	}
	    		
    });
    
    // 小眼睛
    $(".showTextlabel2").on("click",function(event){
      var ursnameCode_value = $.trim($("#password2").val());
      if(ursnameCode_value != ""){
        var checked_input = $("#showText");
        console.log(checked_input[0].checked);
        if(checked_input[0].checked){
          $(this).removeClass("showTextActive");
          $("#password2").attr("type","password");
        }
        else{
          $(this).addClass("showTextActive");
          $("#password2").attr("type","text");
        }
      }
      event.stopPropagation();
    });
    // 小眼睛
    $(".showTextlabel1").on("click",function(event){
      var ursnameCode_value = $.trim($("#password1").val());
      if(ursnameCode_value != ""){
        var checked_input = $("#showText");
        console.log(checked_input[0].checked);
        if(checked_input[0].checked){
          $(this).removeClass("showTextActive");
          $("#password1").attr("type","password");
        }
        else{
          $(this).addClass("showTextActive");
          $("#password1").attr("type","text");
        }
      }
      event.stopPropagation();
    });
  </script>
   </body>
</html>