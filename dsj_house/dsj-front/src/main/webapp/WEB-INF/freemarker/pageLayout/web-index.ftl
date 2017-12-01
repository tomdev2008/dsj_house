<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="baidu-site-verification" content="GuW0deLRPn" />
	<meta charset="UTF-8">
	<title>大搜家  让买房不难</title>
	<meta content="新房信息,二手房信息,经纪人服务,权证服务,二手房过户,大搜家" name="keywords">
    <meta content="大搜家是专业的购房网站。为您提供北京新房信息、北京二手房信息，并且提供网上交易、推荐最优经纪人、代办过户等服务。" name="description">
	<#include "common/taglibs.ftl">
		<link rel="icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
    	<link href="${ctx}/static/front/css/bootstrap.css" rel="stylesheet">
        <link href="${ctx }/static/front/css/web-index.css" rel="stylesheet">
        <link href="${ctx }/static/front/css/footer.css" rel="stylesheet">
        <script src="${ctx }/static/front/js/fles.js"></script>
        <style type="text/css">
        	/* .top-banner {
        		background-image: linear-gradient(to top, #000000, rgba(138, 116, 162, 0.47) 43%, rgba(57, 71, 146, 0.83) 77%);
        		filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=# 778A74A2, endColorstr=#D3394792);
        		zoom: 1;
        	}
        	.big-menu {
        		background:transparent;
        	    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#A50B1A20,endColorstr=#A50B1A20); 
        	    zoom: 1;
        	} */
        </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 10]>

       <style type="text/css">

       .color-block {
           background:transparent;
           filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#7f000000,endColorstr=#7f000000);
           zoom: 1;
        }
        .content-agent__img-detail {
        	background:transparent;
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#E53A7EE3,endColorstr=#E53A7EE3); 
            zoom: 1;
        }
        .content-agent__img-mask {
			background:transparent;
		    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#A50B1A20,endColorstr=#A50B1A20); 
		    zoom: 1;
        }
		.top-banner {
			background-color:rgb(103, 112, 170);
			zoom: 1;
		}
		.big-menu {
			background:transparent;
		    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#A50B1A20,endColorstr=#A50B1A20); 
		    zoom: 1;
		}
		.big-menu__item:hover {
			background:transparent;
		    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#990072FF,endColorstr=#990072FF);
		    zoom: 1;
		}
		.search {
			background:transparent;
		    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#33000000,endColorstr=#33000000); 
		    zoom: 1;
		}


        </style>

    <![endif]-->
    <script type="text/javascript">
	    var sUserAgent= navigator.userAgent.toLowerCase(); 
		var bIsIpad= sUserAgent.match(/ipad/i) == "ipad"; 
		var bIsIphoneOs= sUserAgent.match(/iphone os/i) == "iphone os"; 
		var bIsMidp= sUserAgent.match(/midp/i) == "midp"; 
		var bIsUc7= sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4"; 
		var bIsUc= sUserAgent.match(/ucweb/i) == "ucweb"; 
		var bIsAndroid= sUserAgent.match(/android/i) == "android"; 
		var bIsCE= sUserAgent.match(/windows ce/i) == "windows ce"; 
		var bIsWM= sUserAgent.match(/windows mobile/i) == "windows mobile"; 
		 
		if (bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) { 
		window.location.href= 'http://wap.dasoujia.com/mobile/views/dsj-index/dsj_index.html#/'; 
		}
    </script>
</head>
<body>
  	<div class="top-banner">
  		<div class="big-menu">
  			<div class="big-menu__list clearfix">
  				<div class="big-menu__item">
  				 <a href="${ctx}/front/agent" target="_blank">
  					<img class="big-menu__pic" src="./static/front/img/web-index/agent.png?x-oss-process=image/resize,m_mfit,h_56,w_54">
  					<a class="big-menu__link" href="${ctx}/front/agent" target="_blank">经纪人</a>
  					<ul class="big-menu__sublist">
  						<li class=""><a href="${ctx}/front/agent/od2" target="_blank">信誉</a></li>
  						<li><a href="${ctx}/front/agent/od3" target="_blank">成交</a></li>
  						<li><a href="${ctx}/front/agent/od4" target="_blank">人气</a></li>
  					</ul>
  				</a>
  				</div>
  				
  				<div class="big-menu__item">
  				<a href="${ctx}/front/newHouse/list" target="_blank">
  					<img class="big-menu__pic" src="./static/front/img/web-index/buy_house.png?x-oss-process=image/resize,m_mfit,h_56,w_60">
  					<a class="big-menu__link" href="${ctx}/front/newHouse/list" target="_blank">买房</a>
  					<ul class="big-menu__sublist">
  						<li class=""><a href="${ctx}/front/newHouse/list" target="_blank">新房</a></li>
  						<li><a href="${ctx}/ershoufang" target="_blank">二手房</a></li>
  					</ul>
  				</a>
  				</div>
  				
  				<div class="big-menu__item">
  				<a href="${ctx}/front/entrust/sell" target="_blank">
  					<img class="big-menu__pic" src="./static/front/img/web-index/sell_house.png?x-oss-process=image/resize,m_mfit,h_56,w_63">
  					<a class="big-menu__link" href="${ctx}/front/entrust/sell" target="_blank">卖房</a>
  					<ul class="big-menu__sublist">
  						<li class=""><a href="${ctx}/front/entrust/sell" target="_blank">业主委托</a></li>
  					</ul>
  				</a>
  				</div>
  				
  				<div class="big-menu__item">
  				<a href="${ctx}/warrant/index" target="_blank">
  					<img class="big-menu__pic" src="./static/front/img/web-index/quan_zheng.png?x-oss-process=image/resize,m_mfit,h_56,w_51">
  					<a class="big-menu__link" href="${ctx}/warrant/index">自交易</a>
  					<ul class="big-menu__sublist">
  						<li class=""><a href="${ctx}/front/warrant/detail?id=1"  target="_blank">过户</a></li>|
  						<li class=""><a href="${ctx}/front/warrant/detail?id=3"  target="_blank">赠予</a></li>|
  						<li class=""><a href="${ctx}/front/warrant/detail?id=7"  target="_blank">补证</a></li>
  					</ul>
  				</a>
  				</div>
  				
  			</div>
  		</div>

		<div class="web-index-slogen"><span>大搜家，让买房不难</span></div>
  		<div class="top-banner__header">
  			<h1>
  				大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'">
	         		<img class="logo" src="./static/front/img/web-index/logo1.png?x-oss-process=image/resize,m_mfit,h_44,w_185">
	         	</a>
	         </h1>
  			
  			<span class="top-banner__position"></span>
  			<span class="top-banner__account">
  			<#if Session.pc_user_session??>
  				<#if pc_user_session.userType==5>
  					<span><a href="${ctx}/front/personCenter/center" >${pc_user_session.username}</a></span>
  				<#else>
  					<span><a>${pc_user_session.username}</a></span>
  				</#if>
     	  		<input type="hidden" id="usernameIM" value="${pc_user_session.username}" />
				<input type="hidden" id="passwordIM" value="${pc_user_session.imPassword}" />
         	  	<input type="hidden" id="userIdIM" value="${pc_user_session.id}" />
         	  	<#if pc_user_session.avatar??>
	         	  	<input type="hidden" id="avatarIM" value="${pc_user_session.avatar}" />
         	  	<#else>
          			<input type="hidden" id="avatarIM" value="" />
          		</#if>
	  			<span><a href="javascript:void(0);" onclick="loginOut()">退出</a></span>
	  		<#else>
	  			<span><a href="${ctx}/login/tologin">登录</a></span>
	  			<span><a href="${ctx}/login/register">注册</a></span>
	  			<input type="hidden" id="usernameIM" value="" />
			    <input type="hidden" id="passwordIM" value="" />
			    <input type="hidden" id="avatarIM" value="" />
			    <input type="hidden" id="userIdIM" value="" />
			    
	  		</#if>
		  	  <#if newHouseId??>
	          		<input type="hidden" id="houseIdIM" value="${newHouseId}" />
	          <#else>
	          		<input type="hidden" id="houseIdIM" value="" />
	          </#if>
  			</span>
  			<ul class="menu">
  				<li class="menu__item"><a href="${ctx}/front/newHouse/list" target="_blank">新房</a></li>
  				<li class="menu__item"><a href="${ctx}/ershoufang" target="_blank">二手房</a></li>
  				<li class="menu__item"><a href="${ctx}/front/agent" target="_blank">经纪人</a></li>
  				<li class="menu__item"><a href="${ctx}/front/entrust/sell" target="_blank">业主委托</a></li>
  				<li class="menu__item"><a href="${ctx}/warrant/index" target="_blank">自交易</a></li>
  			</ul>
  		</div>
  		<script>
				        
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
  		</script>
  		<div class="search top-banner__search">
  			<ul class="tab">
  				<li class="tab__item tab__item_active"  name="li"><a href="">搜新房</a></li>
  				<li class="tab__item" name="li"><a href="">搜二手房</a></li>
  				<li class="tab__item" name="li"><a href="">搜经纪人</a></li>
  			</ul>
  			<div class="search-input">
  				<img class="search-input__pic" src="./static/front/img/web-index/search.png?x-oss-process=image/resize,m_mfit,h_40,w_40">
  				<input value="1" type="hidden" id="typeId">
  				<div style="padding:20px">
  					<input type="text" class="search-input__input"  autocomplete="off" value="${keywords}" name="k"  id="inputseek" autocomplete="off" onkeydown="findPcKeydown()"  placeholder="请输入楼盘名称或地址">
  				</div>
  				<ul class="grabble_kuang">
                  
                 </ul>
  				<button class="btn search-input__button" onclick="findPc();">搜索</button>
  			</div>
  			 <script>
  			   var type=1;
  			    onload = function() {  
                var lis = document.getElementsByName("li");  
                var funny = function(i){  
                    lis[i].onclick = function(){  
                        $("#typeId").val(i+1); 
                    }  
                } 
                for(var i=0;i<lis.length;i++){  
                    funny(i);  
                }  
            }  
       		function findPcKeydown() {
       			e = window.event;
       			if(e.which == "13"){
       				findPc();
				}
       		}
              function findPc(){
	           var search=$("#inputseek").val();
	           var type=$("#typeId").val();
	          if(type==1){
	             if(search==null ||search==''){
	              window.open("${ctx}/front/newHouse/list");
	            }else{
	              window.open("${ctx}/front/newHouse/list?k="+search);
	            }
	          }else if(type==2){
	            if(search==null ||search==''){
	              window.open("${ctx}/ershoufang");
	            }else{
	              window.open("${ctx}/ershoufang?k="+search);
	            }
	           
	          }else if(type==3){
	            if(search==null ||search==''){
	              window.open("${ctx}/front/agent");
	            }else{
	           window.open("${ctx}/front/agent?k="+search);
	            }
	          }
               }
             </script>
  			<a class="map_find_house" href="${ctx}/map/newMap" targer="_blank"><img src="./static/front/img/web-index/map_find_house.png"><span class="map_find_house_span">地图找房<span></a>
  		</div>
  	</div>
	<div class="content-newhouse">
	  	<div class="content clearfix">
			<div class="content-title">
				<span><a href="${ctx}/front/newHouse/list" target="_blank">新房</a></span>
				<ul class="content-list">
				    <#list newHouseList as newHouse>
				     <#if newHouse_index==0>
					<li class="content-list__item content-list__item_active"><a href="">${newHouse.label}</a></li>
					 <#else>
					<li class="content-list__item"><a href="">${newHouse.label}</a></li>
					</#if>
					</#list>
				</ul>
			</div>
			<div class="content-card-contianer clearfix">
			    <#list newHouseDirectoryList as newHouseList>
			    <#if newHouseList.labelId==1>
				<div class="content-card">
				    <#if newHouseList.linkAddress==null>
			       <a href="${ctx}/front/newHouse/index_detail?id=${newHouseList.newHouseId}" target="_blank">
			       <#else>
			       <a href="${newHouseList.linkAddress}" target="_blank">
			        </#if>
					<div class="content-card-imgcontianer content-newhouse__img">
						<img src="${newHouseList.picture}?x-oss-process=image/resize,m_mfit,h_287,w_580">
					</div>
					<div class="content-card__title"><span class="ellipsis_newhouse_15" title="${newHouseList.name}">${newHouseList.name}</span>
					   <#if newHouseList.wyTypeName!=null>
					     <span class="content-card__badge">${newHouseList.wyTypeName}</span>
					    </#if>
					   <#if newHouseList.indexPrice?? && newHouseList.indexPrice!="">
					    <span class="content-card__price">
					   		 <span class="content-card__price-unit">${newHouseList.priceName}</span>
							<span class="content-card__price-num">${newHouseList.indexPrice}</span>
							<span class="content-card__price-unit">${newHouseList.pricedw}</span>
						</span>
					<#else>
						 售价待定
					</#if>  
					</div>
					<div>
					<div class="content-card__detail">
						主力户型：${newHouseList.room}室${newHouseList.hall}厅${newHouseList.toilet}卫 
						<span class="margin_10">
							<#if newHouseList.occupyArea!=null>
								建筑面积${newHouseList.occupyArea?number}m²
							</#if>
						</span>
						<#if newHouseList.phone!=null>
						<span class="content-card__phone">
							400-898-6868<span>转</span>${newHouseList.phone}
						</span>
						</#if>
					</div>
					<div class="content-card__detail content-card__detail_last">
						${newHouseList.recommend}
					</div>
					</div>
					</a>
					<div class="card-person">
					   <a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${newHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
						</a>
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						${newHouseList.agentName}
						</a>
						<#if newHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${newHouseList.smallIcon}"></#if> <#if newHouseList.agName!=null>${newHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${newHouseList.userName}','${newHouseList.agentName}','4008986868转${newHouseList.agentPhone}','${newHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${newHouseList.agentAddress}" target="_blank">
						<p class="card-person__info">${newHouseList.comment} </p>
						</a>
					</div>
					
				</div>
				</#if>
				</#list>
			</div>
			<div class="content-card-contianer clearfix" style="display: none;">
				<#list newHouseDirectoryList as newHouseList>
			    <#if newHouseList.labelId==2>
				<div class="content-card">
				     <#if newHouseList.linkAddress==null>
			        <a href="${ctx}/front/newHouse/index_detail?id=${newHouseList.newHouseId}" target="_blank">
			        <#else>
			        <a href="${newHouseList.linkAddress}" target="_blank">
			         </#if>
					<div class="content-card-imgcontianer content-newhouse__img">
						<img src="${newHouseList.picture}?x-oss-process=image/resize,m_mfit,h_287,w_580">
					</div>
					<div class="content-card__title"><span class="ellipsis_newhouse_15" title="${newHouseList.name}">${newHouseList.name}</span>
						 <#if newHouseList.wyTypeName!=null>
					     <span class="content-card__badge">${newHouseList.wyTypeName}</span>
					    </#if>
						 <#if newHouseList.indexPrice?? && newHouseList.indexPrice!="">
					    <span class="content-card__price">
					   		 <span class="content-card__price-unit">${newHouseList.priceName}</span>
							<span class="content-card__price-num">${newHouseList.indexPrice}</span>
							<span class="content-card__price-unit">${newHouseList.pricedw}</span>
						</span>
					    <#else>
						 售价待定
					    </#if>  	
					</div>
					<div>
					<div class="content-card__detail">
						主力户型：${newHouseList.room}室${newHouseList.hall}厅${newHouseList.toilet}卫
						<#if newHouseList.occupyArea!=null>
						建筑面积${newHouseList.occupyArea?number}m²
						</#if>
						<#if newHouseList.phone!=null>
						<span class="content-card__phone">
							400-898-6868<span>转</span>${newHouseList.phone}
						</span>
						</#if>
					</div>
					<div class="content-card__detail content-card__detail_last">
						${newHouseList.recommend}
					</div>
					</div>
					</a>
					<div class="card-person">
					   <a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${newHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
						</a>
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						${newHouseList.agentName}
						</a>
						<#if newHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${newHouseList.smallIcon}"></#if> <#if newHouseList.agName!=null>${newHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${newHouseList.userName}','${newHouseList.agentName}','4008986868转${newHouseList.agentPhone}','${newHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${newHouseList.agentAddress}" target="_blank">
						<p class="card-person__info">${newHouseList.comment} </p>
						</a>
					</div>
				</div>
				</#if>
				</#list>
			</div>
			<div class="content-card-contianer clearfix" style="display: none;">
				<#list newHouseDirectoryList as newHouseList>
			    <#if newHouseList.labelId==3>
				<div class="content-card">
				     <#if newHouseList.linkAddress==null>
			        <a href="${ctx}/front/newHouse/index_detail?id=${newHouseList.newHouseId}" target="_blank">
			        <#else>
			        <a href="${newHouseList.linkAddress}" target="_blank">
			        </#if>
					<div class="content-card-imgcontianer content-newhouse__img">
						<img src="${newHouseList.picture}?x-oss-process=image/resize,m_mfit,h_287,w_580">
					</div>
					<div class="content-card__title"><span class="ellipsis_newhouse_15" title="${newHouseList.name}">${newHouseList.name}</span>
						 <#if newHouseList.wyTypeName!=null>
					     <span class="content-card__badge">${newHouseList.wyTypeName}</span>
					    </#if>
						 <#if newHouseList.indexPrice?? && newHouseList.indexPrice!="">
					    <span class="content-card__price">
					   		 <span class="content-card__price-unit">${newHouseList.priceName}</span>
							<span class="content-card__price-num">${newHouseList.indexPrice}</span>
							<span class="content-card__price-unit">${newHouseList.pricedw}</span>
						</span>
					   <#else>
						 售价待定
					</#if>  			
					</div>
					<div>
					<div class="content-card__detail">
						主力户型：${newHouseList.room}室${newHouseList.hall}厅${newHouseList.toilet}卫
						<#if newHouseList.occupyArea!=null>
						建筑面积${newHouseList.occupyArea?number}m²
						</#if>
						<#if newHouseList.phone!=null>
						<span class="content-card__phone">
							400-898-6868<span>转</span>${newHouseList.phone}
						</span>
						</#if>
					</div>
					<div class="content-card__detail content-card__detail_last">
						${newHouseList.recommend}
					</div>
					</div>
					</a>
					<div class="card-person">
					   <a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${newHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
						</a>
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						${newHouseList.agentName}
						</a>
						<#if newHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${newHouseList.smallIcon}"></#if> <#if newHouseList.agName!=null>${newHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${newHouseList.userName}','${newHouseList.agentName}','4008986868转${newHouseList.agentPhone}','${newHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${newHouseList.agentAddress}" target="_blank">
						<p class="card-person__info">${newHouseList.comment} </p>
						</a>
					</div>
				</div>
				</#if>
				</#list>
			</div>
			<div class="content-card-contianer clearfix" style="display: none;">
				<#list newHouseDirectoryList as newHouseList>
			    <#if newHouseList.labelId==4>
				<div class="content-card">
				   <#if newHouseList.linkAddress==null>
			        <a href="${ctx}/front/newHouse/index_detail?id=${newHouseList.newHouseId}" target="_blank">
			        <#else>
			       <a href="${newHouseList.linkAddress}" target="_blank">
			        </#if>
					<div class="content-card-imgcontianer content-newhouse__img">
						<img src="${newHouseList.picture}?x-oss-process=image/resize,m_mfit,h_287,w_580">
					</div>
					<div class="content-card__title"><span class="ellipsis_newhouse_15" title="${newHouseList.name}">${newHouseList.name}</span>
						 <#if newHouseList.wyTypeName!=null>
					     <span class="content-card__badge">${newHouseList.wyTypeName}</span>
					     </#if>
						 <#if newHouseList.indexPrice?? && newHouseList.indexPrice!="">
					    <span class="content-card__price">
					   		 <span class="content-card__price-unit">${newHouseList.priceName}</span>
							<span class="content-card__price-num">${newHouseList.indexPrice}</span>
							<span class="content-card__price-unit">${newHouseList.pricedw}</span>
						</span>
					<#else>
						 售价待定
					</#if>  		
					</div>
					<div>
					<div class="content-card__detail">
						主力户型：${newHouseList.room}室${newHouseList.hall}厅${newHouseList.toilet}卫 
						<#if newHouseList.occupyArea!=null>
						建筑面积${newHouseList.occupyArea?number}m²
						</#if>
						<#if newHouseList.phone!=null>
						<span class="content-card__phone">
							400-898-6868<span>转</span>${newHouseList.phone}
						</span>
						</#if>
					</div>
					<div class="content-card__detail content-card__detail_last">
						${newHouseList.recommend}
					</div>
					</div>
					</a>
					<div class="card-person">
					   <a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${newHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
						</a>
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${newHouseList.userId}" target="_blank">
						${newHouseList.agentName}
						</a>
						<#if newHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${newHouseList.smallIcon}"></#if> <#if newHouseList.agName!=null>${newHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${newHouseList.userName}','${newHouseList.agentName}','4008986868转${newHouseList.agentPhone}','${newHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${newHouseList.agentAddress}" target="_blank">
						<p class="card-person__info">${newHouseList.comment} </p>
						</a>
					</div>
				</div>
				</#if>
				</#list>
			</div>
		</div>
	</div>
	<div class="content-ershou">
	  	<div class="content clearfix">
			<div class="content-title">
				<span><a href="${ctx}/ershoufang" target="_blank">二手房</a></span>
				<ul class="content-list">
					 <#list oldHouseList as oldHouse>
				     <#if oldHouse_index==0>
					<li class="content-list__item content-list__item_active"><a href="">${oldHouse.label}</a></li>
					 <#else>
					<li class="content-list__item"><a href="">${oldHouse.label}</a></li>
					</#if>
					</#list>
				</ul>
			</div>
			<div class="content-card-contianer clearfix">
			<#list oldHouseDirectoryList as oldHouseList>
			    <#if oldHouseList.labelId==5>
				<div class="content-card content-ershou__card">
				    <#if oldHouseList.address==null>
			    <a href="${ctx}/oldmaster?id=${oldHouseList.oldHouseId}" target="_blank">
			    <#else>
			    <a href="${oldHouseList.address}" target="_blank">
			    </#if>
					<div class="content-card-imgcontianer content-ershou__img">
						<img src="${oldHouseList.picture}?x-oss-process=image/resize,m_mfit,h_260,w_375">
					</div>
					<div class="content-ershou__card-title"><span class="ellipsis_ershou_15" title="${oldHouseList.name}${oldHouseList.recommend}">${oldHouseList.name}  ${oldHouseList.recommend} </span>  <span class="pull-right ershou-price"> ${oldHouseList.price}<t>万元</t></span>
					</div>
					<div class="content-ershou__card-detail">
						${oldHouseList.room}室${oldHouseList.hall}厅  ${oldHouseList.occupyArea}m² ${oldHouseList.typeGroupName}   
					</div>
					<div class="content-ershou__card-agent">
						<#if oldHouseList.companyTypes?contains('2')>
						委托中介：<img src="./static/front/img/web-index/lianjiaICON.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('3')>
						委托中介：<img src="./static/front/img/web-index/maitianicon.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('4')>
						委托中介：<img src="./static/front/img/web-index/zhongyuan.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('5')>
						委托中介：<img src="./static/front/img/web-index/woaiwojia.png">
						</#if>
					</div>
					</a>
					<div class="card-person">
					     <a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${oldHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
					     </a>	
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						${oldHouseList.agentName}  
						</a>
						<#if oldHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${oldHouseList.smallIcon}"></#if> <#if oldHouseList.agName!=null>${oldHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${oldHouseList.userName}','${oldHouseList.agentName}','4008986868转${oldHouseList.agentPhone}','${oldHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${oldHouseList.linkAddress}" target="_blank">
						<p class="card-person__info">${oldHouseList.comment}...</p>
						</a>
					</div>
				</div>
				  </#if>
				</#list>
				</div>
			<div class="content-card-contianer clearfix" style="display: none;">
				<#list oldHouseDirectoryList as oldHouseList>
			    <#if oldHouseList.labelId==6>
				<div class="content-card content-ershou__card">
				    <#if oldHouseList.address==null>
			    <a href="${ctx}/oldmaster?id=${oldHouseList.oldHouseId}" target="_blank">
			    <#else>
			    <a href="${oldHouseList.address}" target="_blank">
			    </#if>
					<div class="content-card-imgcontianer content-ershou__img">
						<img src="${oldHouseList.picture}?x-oss-process=image/resize,m_mfit,h_260,w_375">
					</div>
					<div class="content-ershou__card-title"><span class="ellipsis_ershou_15" title="${oldHouseList.name}${oldHouseList.recommend}">${oldHouseList.name}  ${oldHouseList.recommend} </span>  <span class="pull-right ershou-price"> ${oldHouseList.price}万元</span>
                     
					</div>
					<div class="content-ershou__card-detail">
						${oldHouseList.room}室${oldHouseList.hall}厅  ${oldHouseList.occupyArea}m² ${oldHouseList.typeGroupName}   
					</div>
					<div class="content-ershou__card-agent">
						<#if oldHouseList.companyTypes?contains('2')>
						委托中介：<img src="./static/front/img/web-index/lianjiaICON.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('3')>
						委托中介：<img src="./static/front/img/web-index/maitianicon.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('4')>
						委托中介：<img src="./static/front/img/web-index/zhongyuan.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('5')>
						委托中介：<img src="./static/front/img/web-index/woaiwojia.png">
						</#if>
					</div>
					</a>
					<div class="card-person">
					     <a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${oldHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
					     </a>	
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						${oldHouseList.agentName}  
						</a>
						<#if oldHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${oldHouseList.smallIcon}"></#if> <#if oldHouseList.agName!=null>${oldHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${oldHouseList.userName}','${oldHouseList.agentName}','4008986868转${oldHouseList.agentPhone}','${oldHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${oldHouseList.linkAddress}" target="_blank">
						<p class="card-person__info">${oldHouseList.comment}...</p>
						</a>
					</div>
				</div>
				  </#if>
				</#list>
			</div>
			<div class="content-card-contianer clearfix" style="display: none;"> 
				<#list oldHouseDirectoryList as oldHouseList>
			    <#if oldHouseList.labelId==7>
				<div class="content-card content-ershou__card">
				     <#if oldHouseList.address==null>
			    <a href="${ctx}/oldmaster?id=${oldHouseList.oldHouseId}" target="_blank">
			    <#else>
			    <a href="${oldHouseList.address}" target="_blank">
			    </#if>
					<div class="content-card-imgcontianer content-ershou__img">
						<img src="${oldHouseList.picture}?x-oss-process=image/resize,m_mfit,h_260,w_375">
					</div>
					<div class="content-ershou__card-title"><span class="ellipsis_ershou_15" title="${oldHouseList.name}${oldHouseList.recommend}">${oldHouseList.name}  ${oldHouseList.recommend} </span>  <span class="pull-right ershou-price"> ${oldHouseList.price}万元</span>
                   
					</div>
					<div class="content-ershou__card-detail">
						${oldHouseList.room}室${oldHouseList.hall}厅  ${oldHouseList.occupyArea}m² ${oldHouseList.typeGroupName}   
					</div>
					<div class="content-ershou__card-agent">
						<#if oldHouseList.companyTypes?contains('2')>
						委托中介：<img src="./static/front/img/web-index/lianjiaICON.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('3')>
						委托中介：<img src="./static/front/img/web-index/maitianicon.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('4')>
						委托中介：<img src="./static/front/img/web-index/zhongyuan.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('5')>
						委托中介：<img src="./static/front/img/web-index/woaiwojia.png">
						</#if>
					</div>
					</a>
					<div class="card-person">
					     <a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${oldHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
					     </a>	
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						${oldHouseList.agentName}  
						</a>
						<#if oldHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${oldHouseList.smallIcon}"></#if> <#if oldHouseList.agName!=null>${oldHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${oldHouseList.userName}','${oldHouseList.agentName}','4008986868转${oldHouseList.agentPhone}','${oldHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${oldHouseList.linkAddress}" target="_blank">
						<p class="card-person__info">${oldHouseList.comment}...</p>
						</a>
					</div>
				</div>
				  </#if>
				</#list>
			</div>
			<div class="content-card-contianer clearfix" style="display: none;">
				<#list oldHouseDirectoryList as oldHouseList>
			    <#if oldHouseList.labelId==8>
				<div class="content-card content-ershou__card">
				    <#if oldHouseList.address==null>
			    <a href="${ctx}/oldmaster?id=${oldHouseList.oldHouseId}" target="_blank">
			    <#else>
			    <a href="${oldHouseList.address}" target="_blank">
			    </#if>
					<div class="content-card-imgcontianer content-ershou__img">
						<img src="${oldHouseList.picture}?x-oss-process=image/resize,m_mfit,h_260,w_375">
					</div>
					<div class="content-ershou__card-title"><span class="ellipsis_ershou_15" title="${oldHouseList.name}${oldHouseList.recommend}">${oldHouseList.name}  ${oldHouseList.recommend} </span>  <span class="pull-right ershou-price"> ${oldHouseList.price}万元</span>
					
					</div>
					<div class="content-ershou__card-detail">
						${oldHouseList.room}室${oldHouseList.hall}厅  ${oldHouseList.occupyArea}m² ${oldHouseList.typeGroupName}   
					</div>
					<div class="content-ershou__card-agent">
						<#if oldHouseList.companyTypes?contains('2')>
						委托中介：<img src="./static/front/img/web-index/lianjiaICON.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('3')>
						委托中介：<img src="./static/front/img/web-index/maitianicon.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('4')>
						委托中介：<img src="./static/front/img/web-index/zhongyuan.png">
						</#if>
						<#if oldHouseList.companyTypes?contains('5')>
						委托中介：<img src="./static/front/img/web-index/woaiwojia.png">
						</#if>
					</div>
					</a>
					<div class="card-person">
					     <a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						<div class="card-person__img content-card-imgcontianer">
							<img src="${oldHouseList.avatarUrl}?x-oss-process=image/resize,m_mfit,h_65,w_65">
						</div>
					     </a>	
						<h5 class="card-person__title">
						<a href="${ctx}/front/agentIndex/info?userId=${oldHouseList.userId}" target="_blank">
						${oldHouseList.agentName}  
						</a>
						<#if oldHouseList.smallIcon!=null><img class="card-person__smallIcon" src="${oldHouseList.smallIcon}"></#if> <#if oldHouseList.agName!=null>${oldHouseList.agName}</#if>
						<span class="card-person__contact" onClick="openChatWindow('${oldHouseList.userName}','${oldHouseList.agentName}','4008986868转${oldHouseList.agentPhone}','${oldHouseList.avatarUrl}')">在线咨询</span>
						</h5>
						<a href="${oldHouseList.linkAddress}" target="_blank">
						<p class="card-person__info">${oldHouseList.comment}...</p>
						</a>
					</div>
				</div>
				  </#if>
				</#list>
			</div>
		</div>
	</div>
	<div class="content-rent">
	  	<div class="content clearfix">
			<div class="content-title">
				<span><a href="${ctx}/warrant/index" target="_blank">自交易</a></span>
			</div>
			<div class="content-card-contianer clearfix">
			<#list warrantLists as warrant>
			   <a href="${warrant.linkAddress}" target="_blank">
				<div class="content-card content-rent__card">
					<div class="content-card-imgcontianer content-rent__img">
						<img src="${warrant.picture}?x-oss-process=image/resize,m_mfit,h_260,w_375">
					</div>
					<h4 style="color:#000000;">${warrant.title}</h4>
					<p class="content-rent__card-content">${warrant.label}
						<span class="content-rent__card-price">${warrant.price}
							<span class="content-rent__card-unit">元</span>
						</span>
					</p>
				</div>
				</a>
				</#list>
			</div>
		</div>
	</div>
	<div class="content-agent">
	  	<div class="content clearfix">
			<div class="content-title">
				<span><a href="${ctx}/front/agent" target="_blank">经纪人</a></span>
			</div>
			<div class="content-card-contianer clearfix agent-container">
			<#list agentDirectoryList as agent>
			    <a href="${agent.linkAddress}" target="_blank">
				<div class="content-card content-agent__card">
					<div class="content-card-imgcontianer content-agent__img">
						<!-- <div class="content-agent__img-mask"></div> -->
						<img src="${agent.cardUrl}?x-oss-process=image/resize,m_mfit,h_297,w_400">
					</div>
					<div class="content-agent__img-detail">
						<h5 class="content-agent__img-title">${agent.name}
							<#if agent.smallIcon!=null>
							<img class="card-person__smallIcon" src="${agent.smallIcon}"></#if>
							<b><#if agent.agName!=null>${agent.agName}</#if></b>
						</h5>
						<p class="content-agent__img-content">
						已认证
						  <span>综合得分：${agent.totalScore}</span>
						<#if agent.deal!=null>  <br>
						成交${agent.deal}套，
						</#if>
						<#if agent.takeLook!=null>  
						带看${agent.takeLook}套<br>
						</#if>
						联络方式：400-898-6868转${agent.tellPhone}<br>
						从业年限：${agent.workyears}年<br>
						经纪公司：${agent.companyName}<br>
						精耕区域：${agent.business}  <br>
						</p> 
						<div class="content-agent__img-tags">
						   <#list agent.feature?split(",") as feature>  
							<span class="content-agent__img-tag">${feature}</span>
							</#list>
						</div>             
					</div>
				</div>
				</a>
				</#list>
			</div>
		</div>
	</div>
		<div class="footer-banner">
			<a href="${banner.linkAddress}" target="_blank">
						<img class="footer_img" src="${banner.picture}?x-oss-process=image/resize,m_mfit,h_236,w_1349">

		   </a>			
		</div>
		 <div class="newhouse_footer margin_b70">
			<div class="footer_kuang">
            <ul class="footer1">
                <li><a href="${ctx}/about/page?about=0">关于我们</a></li>
                <li><a href="${ctx}/about/page?about=1">平台协议</a></li>
                <li><a href="${ctx}/about/page?about=2">合作伙伴</a></li>
                <li><a href="http://agent.dasoujia.com/login/to_login">经纪人登录</a></li>
                <li><a href="http://www.dasoujia.com/dsj-developer-back/login/to_login">开发商登录</a></li>
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
                <li>联系方式:010-64791771</li>
            </ul>
            </div> 
		</div>
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
    <script src="${ctx }/static/back/js/web-index.js"></script>
	<#include "./layouts/im.ftl">
	<#include "./common/popup_menu.ftl">
	<script>
	$(".webim-expend").hide();
	$(function(){
	seekevent();
	 $(".grabble_kuang").hide();
})
	</script>
	<script>
	  function seekevent(){
    var seekValue = $("#inputseek").val();
    // click事件
    $("#inputseek").on("click",function(){
        $(this).removeAttr("placeholder");
        seekValue =  $.trim($("#inputseek").val());
        if(seekValue != ""){
                $(".grabble_kuang").show();
        }
    })
    
    // keyup事件
    $("#inputseek").on("keyup",function(){
        seekValue =  $.trim($("#inputseek").val());
         if(seekValue == ""){
            $(".grabble_kuang").hide();
        }
        var type=$("#typeId").val();
        // 发送ajax请求
        if(type==1){
        if(seekValue != ""){
        	$.ajax({
	  			type:"post",
	  			async:true,
	  			data:{"name":seekValue},
	  			dataType:"json",
	  			url:_ctx+"/keyword/search/newHouse",
	  			success:function(resultVo){
	  				if(resultVo.status==200 && resultVo.data!=null){
	  				data=resultVo.data;
	  					$(".grabble_kuang").empty()
	  					for(i=0;i<data.length;i++){
	  						$(".grabble_kuang").append("<li>"+data[i].name+"</li>" );
	  					}
	  					$(".grabble_kuang").show();
					}
	  			}
	  		})
                
        }
    }else if(type==2){
         if(seekValue != ""){
        	$.ajax({
	  			type:"post",
	  			async:true,
	  			data:{"name":seekValue},
	  			dataType:"json",
	  			url:_ctx+"/keyword/search",
	  			success:function(resultVo){
	  				if(resultVo.status==200 && resultVo.data!=null){
	  				data=resultVo.data;
	  					$(".grabble_kuang").empty()
	  					for(i=0;i<data.length;i++){
	  						$(".grabble_kuang").append("<li>"+data[i].name+"</li>" );
	  					}
	  					$(".grabble_kuang").show();
					}
	  			}
	  		})
                
        }
    }
    })
    // 将div中的内容赋值给input的value
    $(".grabble_kuang").delegate("li","click",function(){
        	seekValue = $(this).html();
        	var type=$("#typeId").val();
        	if(type==1){
        	 location = _ctx + "/front/newHouse/list?kw="+seekValue;
        	}else if(type==2){
        	  location = _ctx + "/ershoufang/list?kw="+seekValue;
        	}
        	
    	})
	};
	
	function List_name(txt,idName) {
	        var str = txt;
	        if(str.length >10){
	        	 str = str.substr(0,10) + '...' ;
	       	 	var id=$(idName);
	        	id.html(str);
	        }
	    }
		var data = $(".ellipsis_newhouse_15").html().trim();
		List_name(data,'.ellipsis_newhouse_15');
	</script>
  </body>
</html>

