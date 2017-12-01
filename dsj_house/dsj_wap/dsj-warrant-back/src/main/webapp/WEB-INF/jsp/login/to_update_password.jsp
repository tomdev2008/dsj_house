<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="${ctx }/static/front/img/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="${ctx}/static/front/css/login/style.css">
	<link rel="stylesheet" href="${ctx}/static/front/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="${ctx}/static/front/js/jquery.min.js"></script>
	<script src="${ctx}/static/front/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src='${ctx}/static/back/js/common/base.js'></script>
	<script src='${ctx}/static/front/js/style.js'></script>
	<title>找回密码</title>
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
	<div class="mima">
	<div style="margin-top:18.9px ">
		<span class="tit">找回密码</span>

	</div>
		<div class="mimaXian"></div>
		<div class=" alert-danger dengLuTiShi"  id="errorDiv" ><span class="dengLuTiShiTu glyphicon glyphicon-minus-sign"></span><span  id="error" class="cuo"></span></div>
		
			   <div class="xinMiMaCont" >
			   		<span id="xin" >新密码</span>
					<input id="xinmima" onclick="xin()"  type="text" class="form-control" placeholder="请输入新密码！">
			   </div>     
			 

		
		<div class="queRenMiamCont">
			   		<span  id="que">确认密码</span>
					<input id="querenmima"  type="text" class="form-control" onclick="que()" placeholder="请再次输入密码！">
			   </div>     
		<!-- <div class=" alert-danger"  id="errorDiv" style="visibility:hidden;width: 88.5%;margin-left: 10%;margin-top: 1.5%;text-align: left;background: #f8ccc7;color: #cc0000;border: 1px solid #f8ccc7" style='height:20px'><span  style="margin-top: 2px;margin-left: 2.7%;color: #cc0000" class="glyphicon glyphicon-minus-sign"></span><span  id="error" style="margin-left: 3.3%;color: #cc0000"></span></div>

		<div class="input-group name">
  			<span class="input-group-addon" style="width: 40px;border: none;background: #fff" id="basic-addon1">新密码</span>
  			<input type="text" id="phone" onclick="phone2()"  style="position: absolute;width: 69.5%;right: 0px" class="form-control" placeholder="请输入手机号或账号" aria-describedby="basic-addon1">
  			
		</div>
		<div class="input-group password">
  			<span class="input-group-addon" style="width: 40px;border: none;background: #fff" id="basic-addon3">确认密码</span>
  			<input type="text" style="color: #000" id="yanzma" class="form-control" onclick="mma()" placeholder="请输入验证码" aria-describedby="basic-addon1">
  			
		</div> -->
		<!-- <span style="position: absolute;right: 10%;margin-top: 5.8%;color: #0598e2;font-family: '微软雅黑'" onclick="wangji()">忘记密码</span> -->
		  <button type="button" class="btn btn-primary quXiaoBtn"  onclick="quxiao()">取消</button>
		  <button type="button" class="btn btn-primary queDingBtn" onclick="queding()">确定</button>
	</div>

	</div>

	
	
	<div class="yun">
	<!-- <img src="images/1.png" alt=""> -->
	<div class="kuanCont">
	<a class="kuan" href="http://www.dasoujia.com/about/page?about=0">关于我们</a>
	<a class="kuan">联系我们4008100686</a>
	<a class="kuan">北京朝阳区融科望京中心A座1805</a>
	<a class="kuan kuanLast">北京大搜家信息技术服务有限公司</a>
	</div>
	</div>

	<script type="text/javascript">
		var _ctx = '${ctx}';
		  $(".getCode").click(function(){
            time(this);
          });
	</script>
</body>
</html>

