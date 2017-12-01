<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <meta content="房屋过户,房产过户,继承,赠予,企业过户,网上签约,权证服务,夫妻更名,企业产权过户" name="keywords">
  <meta content="北京权威的房产权证网站,为您提供过户、办理房产证、房产信息变更、房产证增名、房产证减名、网上签约、自行成交等服务。" name="description">
  <meta charset="UTF-8">
  <title>自交易-二手房过户-大搜家</title>
  <#include "common/taglibs.ftl">
  <link rel="icon" href="${ctx }/static/front/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant-index.css">
  <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/footer.css">
  <script src="${ctx }/static/front/js/fles.js"></script>
</head>  
<body>	
    <div class="warrant_header">
      <div class="w_header">
        <div class="h_left">
          <a href="##" class="w_logo"></a>
          <span class="headline">自交易</span>
        </div>
        <div class="h_right">
          <a href="${ctx}/">首页</a>
          <a href="${ctx}/front/newHouse/list">新房</a>
          <a href="${ctx}/ershoufang">二手房</a>
          <a href="${ctx}/front/agent">经纪人</a>
          <a href="${ctx}/front/entrust/sell">业主委托</a>
          <div class="login">
          	<#if Session.pc_user_session??>
  				<#if pc_user_session.userType==5>
  					<a href="${ctx}/front/personCenter/center" >${sessionUsername}</a>
  				<#else>
  					<a href="javaScript:void(0)">${sessionUsername}</a>
  				</#if>
     	  		<input type="hidden" id="usernameIM" value="${pc_user_session.username}" />
				<input type="hidden" id="passwordIM" value="${pc_user_session.imPassword}" />
         	  	<input type="hidden" id="userIdIM" value="${pc_user_session.id}" />
	  			<a href="javascript:void(0);" onclick="loginOut()">退出</a>
	  		<#else>
	  			<a href="${ctx}/login/tologin">登录</a>
	  			<a href="${ctx}/login/register">注册</a>
	  			<input type="hidden" id="usernameIM" value="" />
			    <input type="hidden" id="passwordIM" value="" />
			    <input type="hidden" id="userIdIM" value="" />
	  		</#if>
          </div>
        </div>
      </div>
      <div class="w_slogan">
        
      </div>
    </div>
    <div class="warrant_main">
      <div class="m_line">
        <h4>自交易服务</h4>
        <a class="see_more" href="${ctx }/front/warrant/list">查看更多</a>
      </div>
      <div class="m_car">
      		<#if list??>
			  		<#list list as spu> 
			  			<#if spu.type==1> 
			  			 			<#if spu.name=="房屋过户"> 
							  			<a target="_blank" href="${ctx}/front/warrant/detail?id=${spu.skuId}" class="house_transfer fangwuguohu">
							  		<#elseif spu.name=="房屋继承">
							  			<a target="_blank" href="${ctx}/front/warrant/detail?id=${spu.skuId}" class="house_transfer fangwujicheng">
							  		<#elseif spu.name=="房屋赠予">
							  			<a target="_blank" href="${ctx}/front/warrant/detail?id=${spu.skuId}" class="house_transfer fangchanzengyu">
							  		<#elseif spu.name=="夫妻更名">
							  			<a target="_blank" href="${ctx}/front/warrant/detail?id=${spu.skuId}" class="house_transfer fuqi">
							  		<#elseif spu.name=="企业产权过户">
							  			<a target="_blank" href="${ctx}/front/warrant/detail?id=${spu.skuId}" class="house_transfer qiyechanquanguohu">
							  		<#elseif spu.name=="补证登记">
							  			<a target="_blank" href="${ctx}/front/warrant/detail?id=${spu.skuId}" class="house_transfer buzheng">
							  		 </#if>
					          <h3>${spu.name }</h3>
					          <div class="m_price">
					            <strong>${spu.minPrice }</strong>元
					          </div>
					          <p class="describe">${spu.content }</p>
					          <div class="view_details">查看详情</div>
					        </a>
					  </#if>
			  		</#list>
			    </#if>
      </div>
    </div>
    <div class="clear"></div>
    <div class="warrant_kuang">
      <div class="bottom_center">
        <div class="m_line">
          <h4>为您服务</h4>
        </div>
        <div class="c_car">
       		 <#if proList??>
				<#list proList as pro>
					 <a href="javascript:void(0);" onclick="toFwUserDetail(${pro.id})">
			            <img src="${pro.avatarMasterUrl}?x-oss-process=image/resize,m_fixed,h_400,w_283">
			            <div class="people_car">
			              <h3>${pro.realName }</h3>
			              <ul>
			                <li>好评率：<span><#if pro.haoPingLv==null>0%<#else>${pro.haoPingLv}</#if></span></li>
			                <li>接单量：<span>${pro.deal}</span></li>
			                <li>联络方式：<span>${pro.tellPhone}</span></li>
			                <li>经纪公司：<span class="company_name">${pro.companyName}</span></li>
			              </ul>
			            </div>
			          </a>
				</#list>
			</#if>
        </div>
      </div>
    </div>
    <div class="newhouse_footer">
            		<div class="footer_kuang">
            		<ul class="footer1">
                		<li><a href="${ctx}/about/page?about=0">关于我们</a></li>
                		<li><a href="${ctx}/about/page?about=1">平台协议</a></li>
                		<li><a href="${ctx}/about/page?about=2">合作伙伴</a></li>
                		<li><a href="http://agent.dasoujia.com/login/to_login">经纪人登录</a></li>
                		<li><a href="http://pc.dasoujia.com/dsj-developer-back/login/to_login">开发商登录</a></li>
                		<li><a href="http://wap.dasoujia.com/dsj-warrant-back/login/to_login">自交易商家登录</a></li>
                		<li class="phone_lianxi">400-898-6868转888</li>
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
    <script src="${ctx }/static/front/js/jquery.js"></script>
  <script type="text/javascript">
	$(function(){
	        var sUserAgent= navigator.userAgent.toLowerCase(); 
			var bIsIphoneOs= sUserAgent.match(/iphone os/i) == "iphone os"; 
			var bIsMidp= sUserAgent.match(/midp/i) == "midp"; 
			var bIsUc7= sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4"; 
			var bIsUc= sUserAgent.match(/ucweb/i) == "ucweb"; 
			var bIsAndroid= sUserAgent.match(/android/i) == "android"; 
			var bIsCE= sUserAgent.match(/windows ce/i) == "windows ce"; 
			var bIsWM= sUserAgent.match(/windows mobile/i) == "windows mobile"; 
			 
			if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) { 
			window.location.href= 'http://wap.dasoujia.com/mobile/views/quanzheng/index.html'; 
			}
	   })
  function loginOut(){
 		$.ajax({
	  		type:"post",
	  		url:_ctx+"/login/loginOut",
	  		data:{
	  			url:window.location.href
	  		},
	  		dataType:"json",
	  		success: function(result){
	  			window.location.href="${ctx}/";
	  		}
	  	});
 	}
    function List_name(txt,idName) {
          var str = txt;
          if(str.length >10){
             str = str.substr(0,10) + '...' ;
            var id=$(idName);
            id.html(str);
          }
      }
    var data = $(".company_name").html().trim();
    List_name(data,'.company_name');

    $(".c_car>a").on("mouseover",function(){
      $(this).find(".people_car").show();
    });
    $(".c_car>a").on("mouseout",function(){
      $(this).find(".people_car").hide();
    });
    function toFwUserDetail(id){
  		location=_ctx+"/front/warrant/fwuserDetail?id="+id;
  	}
  </script>
    </body>
</html>