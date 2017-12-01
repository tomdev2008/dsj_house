<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <meta charset="UTF-8">
  <title>自交易列表-自交易-大搜家</title>
  <#include "common/taglibs.ftl">
  <link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
    <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
     <style type="text/css">
     .newhouse_footer{
        margin-top: 0;
       }
    </style>
</head> 
<body>	
  	<div class="warrant">
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	       <h1>大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	        <span>自交易</span>
	      </div>
	      <div class="BHLogoRight">
	          <div class="TextSeek">
	          </div>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress"> 
	      <ul>
	        <li><a href="${ctx }/">大搜家首页</a>
	        <div class="progressTriangle">
	         
	        </div>
	        </li>
	        <li><a href="javascript:void(0);">自交易列表</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main warrant_main">
		  	<div class="warrant_nav">
		  		<a href="javascript:void(0);" class="a_active">自交易服务</a>
		  		<!-- <a href="javascript:void(0);">金融服务</a> -->
		  	</div>
		  	<div class="warrant_center">
		  		<div class="row warrant_s">
		  		<#if list??> 
			  		<#list list as spu> 
			  			<#if spu.type==1> 
			  		    	<div class="col-xs-6 col-md-4">
							  	<div class="warrant_car" onclick="skuDetail(${spu.skuId })">
							  		<h3>${spu.name }</h3>
							  		<p><strong>${spu.minPrice }</strong>元</p>
							  		<div class="car_describe">
							  			${spu.content }
							  		</div>
							  		<!-- 添加class
							  			房屋过户  icon_1 
							  			房屋继承 icon_2
							  			房产赠与 icon_3
					  					夫妻更名 icon_4
					  					企业产权过户 icon_5
					  					新房产证办理 icon_6
					  					补证登记 icon_7
							  		 -->
							  		 <#if spu.name=="房屋过户"> 
							  			<div class="car_icon icon_1"></div>
							  		<#elseif spu.name=="房屋继承">
							  			<div class="car_icon icon_2"></div>
							  		<#elseif spu.name=="房屋赠予">
							  			<div class="car_icon icon_3"></div>
							  		<#elseif spu.name=="夫妻更名">
							  			<div class="car_icon icon_4"></div>
							  		<#elseif spu.name=="企业产权过户">
							  			<div class="car_icon icon_5"></div>
							  		<#elseif spu.name=="新房产证办理">
							  			<div class="car_icon icon_6"></div>
							  		<#elseif spu.name=="补证登记">
							  			<div class="car_icon icon_7"></div>
							  		 </#if>
							  	</div>
						  </div>
					  </#if>
			  		</#list>
			    </#if>
				</div>
				 <div class="row financial_s" style="display: none">
				 	<div class="col-xs-6 col-md-4">
					  	<div class="warrant_car">
					  		<h3>银行按揭贷款</h3>
					  		<p><strong>6000</strong>元</p>
					  		<div class="car_describe">
					  			购房者以所购房屋之产权作为抵押，由银行先行支房款，以后购房者按月向银行分期支付本息
					  		</div>
					  		<div class="car_icon"></div>
					  </div>
					</div>
					  <div class="col-xs-6 col-md-4">
					  	<div class="warrant_car">
					  		<h3>房屋抵押贷款</h3>
					  		<p><strong>6000</strong>元</p>
					  		<div class="car_describe">
					  			产权所有人以房契作为抵押，取得借款按期付息
					  		</div>
					  		<div class="car_icon"></div>
					  	</div>
					  </div>
				</div>
		  	</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		isPcOrOther('http://wap.dasoujia.com/mobile/views/quanzheng/index.html');
	})
	$(".warrant_nav").on("click","a",function(){
		$(this).addClass("a_active").siblings().removeClass("a_active");
		if($(this).html() == "自交易服务"){
			$(".warrant_s").show();
			$(".financial_s").hide();
		}else{
			$(".financial_s").show();
			$(".warrant_s").hide();
		}

	})
	//跳转商品详情id
	function skuDetail(id){
		location = _ctx+"/front/warrant/detail?id="+id
	}
	</script>
</body>
</html>	