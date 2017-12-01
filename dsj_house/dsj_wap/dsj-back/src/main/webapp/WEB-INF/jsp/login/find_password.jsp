<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<%@ include file="../layouts/dialog.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/static/front/css/login/style.css">
	<link rel="stylesheet" href="${ctx}/static/front/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="${ctx}/static/front/js/jquery.min.js"></script>
	<script src="${ctx}/static/front/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src='${ctx}/static/front/js/style.js'></script>
	<script src='${ctx}/static/back/js/common/base.js'></script>
	<title>发送验证码</title>
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
		<span class="tit">找回密码</span>

	</div>
		<div class="fengexian"></div>
		<div class=" alert-danger dengLuTiShi"  id="errorDiv" ><span class="dengLuTiShiTu glyphicon glyphicon-minus-sign"></span><span  id="error" class="cuo"></span></div>

		<div class="input-group name">
  			<span class="input-group-addon tx1"  id="basic-addon1"><img id="tx" class="tupian" src="${ctx}/static/front/images/login/tuyiyi-com-0.png"  alt=""></span>
  			<input type="text" id="phone" onclick="phone2()"  class="form-control" placeholder="请输入手机号或账号" aria-describedby="basic-addon1">
  			<span class="input-group-addon cha1" id="cha" onclick="qingkong()">X</span>
		</div>
		<div class="input-group password">
  			<span class="input-group-addon tx1"  id="basic-addon3"><img id="ma" src="${ctx}/static/front/images/login/7.png" id="mm" class="tupian" alt=""></span>
  			<input type="text"  id="yanzma" class="form-control" onclick="mma()" placeholder="请输入验证码" aria-describedby="basic-addon1">
  			<span class=" getCode"  >获取验证码</span>
		</div>
		<!-- <span style="position: absolute;right: 10%;margin-top: 5.8%;color: #0598e2;font-family: '微软雅黑'" onclick="wangji()">忘记密码</span> -->
		  <button type="button" class="btn btn-primary xiayibu"  onclick="xiayibu()">下一步</button>
	</div>

	</div>

	
	
	<div class="yun">
	<!-- <img src="${ctx}/static/front/images/login/1.png" alt=""> -->
	<div class="kuanCont">
	<a class="kuan">关于我们</a>
	<a class="kuan">安全保证</a>
	<a class="kuan">媒体频道</a>
	<a class="kuan">帮助中心</a>
	<a class="kuan">联系我们010-64791900</a>
	<a class="kuan">北京朝阳区融科望京中心A座1805</a>
	<a class="kuan kuanLast" style="border-right: none;">北京大搜家信息技术服务有限公司</a>
	</div>
	</div>

	<script type="text/javascript">
	var _ctx = '${ctx}';
		  $(".getCode").click(function(){
            time(this);
            $.ajax({
                type:"post",
                url:"${ctx}/loginuser/send_vcode",
                dataType:"json",
                data:{"phone":$("#phone").val()},
                success:function(data){
                    if(data.status == 200){
                    	modalSuccess("验证码已发送到您手机");
                    }else{
                        
                    }
                }
            });
        });
		  
		  $("#phone").blur(function(){
				  phone2();
		 });
	</script>
</body>
</html>

