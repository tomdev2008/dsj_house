<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="北京楼盘,北京新楼盘,北京新房,北京新房价格,北京优质新房" name="keywords">
	<meta content="大搜家新房频道提供全、新、准的新房信息，楼盘价格、在售户型实时更新，让您快速的找到适合自己的房源。" name="description">
	<meta charset="UTF-8">
	<title>北京新房-北京新房价格-大搜家</title>
	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
	<style type="text/css">
   .hidden{
       visibility:hidden
    }
    .none{
     	display:none
    }
    .borderbm{
    	border-bottom: 1px solid #ececec;
    	padding-bottom: 16px;
    	height: 90px;
    }
    .margintop15{
    	margin-top: 15px;
    }
    .dsj_row .dsj_row_content .suan1212{
    	font-size: 18px;
    }
    .dsj_row .dsj_row_content .contect_phone .suan1212ipen{
    	background: url(${ctx }/static/front/img/1212/2.jpeg) no-repeat center;
    	display: inline-block;
    	width: 124px;
    	height: 26px;
    }
    .jd1212{
        font-size: 16px;
        background: url(${ctx }/static/front/img/1212/1.jpeg) no-repeat left center; 
    	font-weight: bold;
    	padding-left: 82px;
    	margin-bottom: 0;
    	line-height: 32px;
    	height: 25px;   
    }
    .dsj_row .dsj_row_content .dsj_uncentain .daishou12{
    	 font-size: 23px;
    	 color:#333;
    }
    .contect_phone strong{
    	color:#333;
    	font-weight: bold;
    }
    .dsj_form .dsj_sort{
    	padding-top:0;
    }
 </style>
</head>
<body>
	  <!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo borderbm">
	      <div class="BHLogoLeft">
	        <h1>大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	        <span>新房</span>
	      </div>
	      <div class="BHLogoRight">
	        <!-- 输入框搜索按钮 -->
	          <div class="TextSeek">
	            <form class="form-inline" onSubmit="return false;">
	              <div class="form-group">
	                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	                <div class="input-group">
	                  <input type="text" class="form-control" value="${kw}" name="k" id="inputseek" placeholder="中粮天恒天悦壹号" autocomplete="off">
	                </div>
	                <ul class="grabble_kuang">
                  
                	 </ul>
	              </div>
	               <button type="button" class="seek" onclick="searchResult()">
	              	 </button>
	            </form>
	          </div>
	          <div class="MapFindHouse">
	             <a href="${ctx }/map/newMap">地图找房</a>
	          </div>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress none"> 
	      <ul>
	        <li><a href="${ctx}/">大搜家首页</a>
	        <div class="progressTriangle">
	        </div>
	        </li>
	        <li><a href="javascript:void(0);">新房列表</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 前端主体 -->
	  	<div class="dsj-main">
	  		<!-- 表单选项 -->
	  		<form class="dsj_form margintop15" >
		 			
	 			<p class="dsj_form_line ">
	 				<span class="dsj_form-title">价格:</span>
	 				<span <#if params?index_of("pr")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/jdlist/${url_pr}${url_kw}">不限</a></span>
	 				<#list priceMap?keys as key>
	 					<span <#if params?index_of("pr"+key)!=-1> class="active" </#if> ><a href="${ctx}/front/newHouse/jdlist/${url_pr}pr${key}${url_kw}">  ${priceMap[key]}</a></span>
	 				</#list>
	 				<span class="double_input_container pull-right">
	  				<span class="double_input">
	  					<input value="${pmn}" name="pmn" id="pmn">
	  					<span><span>-</span></span>
	  					<input value="${pmx}" name="pmx" id="pmx">
	  					<span><span>万</span></span>
	  				</span>
	  				<a href="javascript:void(0);" onclick="priceToUrl()" class="color_blue">确定</a>
	 				</span>
	 			</p>
	  			<p class="dsj_form_line ">
	 				<span class="dsj_form-title">户型:</span>
	 				<span <#if params?index_of("rm")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/jdlist/${url_rm}">不限</a></span>
	 					<#list roomMap?keys as key>
	 					<span <#if params?index_of("rm"+key)!=-1> class="active" </#if>><a href="${ctx}/front/newHouse/jdlist/${url_rm}rm${key}">  ${roomMap[key]}</a></span>
	 				</#list>
	 			</p>
 			
	  			<p class="dsj_form_line">
	  				<span class="dsj_form-title">特色:</span>
	  				<span <#if params?index_of("ts")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/jdlist/${url_ts}">不限</a></span>
	  				<#list tsMap?keys as key>
	 					<span <#if params?index_of("ts"+key)!=-1> class="active" </#if>><a href="${ctx}/front/newHouse/jdlist/${url_ts}ts${key}">  ${tsMap[key]}</a></span>
	 				</#list>
	  			</p>
	  			<p class="dsj_form_line more_option">
	 				<span class="dsj_form-title">类型:</span>
	 				<span <#if params?index_of("wy")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/jdlist/${url_wy}">不限</a></span>
	 				<#list wyType?keys as key>
	 					<span <#if params?index_of("wy"+key)!=-1> class="active" </#if>><a href="${ctx}/front/newHouse/jdlist/${url_wy}wy${key}">  ${wyType[key]}</a></span>
	 				</#list>
	 			</p>
	  			
	  			<hr class="bottom_hr">
	  			<#if conditionMap?? && conditionMap?size gt 0 >	
	 			<p class="dsj_form_line dsj_condition">
	 				<span>条件:</span>
	 				
	 				<#list conditionMap?keys as key> 
	 					<#if key!=null>	
	 					<span>${key}<a href="${ctx}/front/newHouse/jdlist/${conditionMap[key]}"><span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></a> </span>
	 					</#if>
	 				</#list>
	 				<span class="dsj_clear"><span class="glyphicon glyphicon-trash" aria-hidden="true">
	 				
	 				</span><a href="${ctx}/front/newHouse/jdlist/" style="
    color: #999;
    ">清空选项</a></span>
	 			</p>
	 			</#if>

	            <p class="dsj_form_line dsj_sort" style="display: inline-block;">
	            <span>排序:</span>
	            <#if ordering!=3 && ordering!=2 >
	           		<span class="active">
	           	<#else>
	           		<span>
		         </#if>
	           	<a href="${ctx}/front/newHouse/jdlist/${url_orderding}od1">默认排序</a></span>
		           	<span class="hover_span">
		           		<a href="${ctx}/front/newHouse/jdlist/${url_orderding}od2">价格由低到高
		          	 	</a>
		          	 	<#if ordering?? && ordering==2 >
		           			<span class="active dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
			          	<#else>
				          	<span class="dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true" style="margin-right: 0;"></span>
				         </#if>
		           	</span>
	           		<span  class="hover_span">
	           			<a href="${ctx}/front/newHouse/jdlist/${url_orderding}od3">时间由旧到新
		           		</a>
		           		<#if ordering?? && ordering==3>
			           			<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
			           		<#else>
			           			<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
			           		</#if>
	           		</span>
	           		</p>	
	  				<p class=" pull-right jd1212">
	  					京东房产战略合作
	  				</p>
	  		</form>

	        <!-- 主体内容 -->
	      	<div class="newhouse_content">
	      	<#if page.recordList?? && page.recordList?size gt 0>
		      	<#list page.recordList as newHouse>
		     		<div class="dsj_row uncentain clearfix">
							<div class="dsj_row_img">
								<a href="${ctx}/front/newHouse/index_detail?id=${newHouse.id}" target="_blank">
								<#if newHouse.imageUrl??>
									<img class="img-responsive" src="${newHouse.imageUrl }?x-oss-process=image/resize,m_fixed,h_200,w_280">
								<#else>
				           			<img class="img-responsive" src="${ctx }/static/front/img/newHouseList.png">
				           		</#if>
								</a>
							</div>
							<div class="dsj_row_content">
								<h4>
								    <a href="${ctx}/front/newHouse/index_detail?id=${newHouse.id}" target="_blank">${newHouse.name }</a>
									    <#if newHouse.status==1>
									 		 <span class="sale sale_wait">待售</span>
									  <#elseif newHouse.status==2>
										   <span class="sale sale_on">在售</span>
									   <#else>
									   		<span class="sale sale_finish">售罄</span>
									  </#if>
								</h4>
								<p class="clearfix limit-address">
									<span>地址:</span>
									
									<#if newHouse.address??&&newHouse.address!="">
										<span>[${newHouse.areaName3 }-${newHouse.tradingAreaName }] ${newHouse.address }<span>
									<#else>
									   		暂无
								  	 </#if>
								  	 <span class="dsj_uncentain pull-right">
									   <#if newHouse.referencePriceMin??>
								 			单价 <span>${newHouse.referencePriceMin?round }</span> 元/m<sup>2</sup><#if newHouse.referencePriceMin!=newHouse.referencePriceMax>起</#if>
									   <#elseif newHouse.totalPriceMin??>
											 总价<span>  ${newHouse.totalPriceMin?round }</span>万元/套<#if newHouse.totalPriceMin!=newHouse.totalPriceMax>起</#if>
									   <#elseif newHouse.aroundPriceMin??>
											  周边均价  <span>${newHouse.aroundPriceMin?round }</span>  元/m<sup>2</sup><#if newHouse.aroundPriceMin!=newHouse.aroundPriceMax>起</#if>
									   <#else>
									   		<span class="daishou12">售价待定</span>
									   </#if>
									</span>
								</p>
								<p class="clearfix">
									<span>户型:</span>
									<#if newHouse.roomNames??&&newHouse.roomNames!="">
										${newHouse.roomNames }
									<#else>
									   		暂无
								  	 </#if>
								</p>
								<div class="agent">
									<span>动态:</span>
									<#if newHouse.title??&&newHouse.title!="">
										${newHouse.title }
									<#else>
									   		暂无
								  	 </#if>
								  	 <#if newHouse.pcMobile??>
										 <#if newHouse.orderNum!=100>
											 <span class="contect_phone pull-right suan1212"><span class="suan1212ipen"></span>
													 <strong> 400-898-0806 </strong> 转  <strong class="phone12">${newHouse.pcMobile }</strong>
											</span>
										<#else>
											<span class="contect_phone pull-right"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
												 <strong> 400-898-6868 </strong> 转  <strong class="phone12">${newHouse.pcMobile }</strong>
											</span>
										</#if>
									</#if>
								</div>
								<div class="labelling">
			      					 <#if newHouse.dicTraitShowNameArr??> 
			      					 	<#list newHouse.dicTraitShowNameArr as dic><span class="color_${dic_index}">${dic }</span></#list>
			      					</#if>
			      				</div>
								<div class="dsj_contect pull-right pop-contianer none">
									<#if newHouse.agentList??>
										<#list newHouse.agentList as agent>
											<div class="sale-person">
											<#if agent.islz==1>
												<div class="img_vip  img_55">
											<#else>
												<div class="img_55">
											</#if>	
										  			<img class="img-responsive" src="${agent.headImgUrl }?x-oss-process=image/resize,m_fixed,h_60,w_55">
										  			<span class="_vip"></span>
								  				</div>
												<span>${agent.realname }</span>
											</div>
											<div class="pop-content">
											<#if agent.islz==1>
												<div class="pop-content__person img_vip img_55">
											<#else>	
												<div class="pop-content__person img_55">
											</#if>	
													<a target="_blank" href="${ctx }/front/agentIndex/info?userId=${agent.id }"><img  class="img-responsive pop__sale-person" src="${agent.headImgUrl }"></a>
													<span class="_vip"></span>
													<i class="validate_i"><span class="dsj_vip"></span> 已认证</i>
												</div>
												<div class="pop-content__detail">
													<h3 class="agent_names"><a target="_blank" href="${ctx }/front/agentIndex/info?userId=${agent.id }">${agent.realname }</a></h3>
													<div class="huzhang">
														<#if agent.djtb??  && agent.djtb!="null">
															<img src="${agent.djtb}"> ${agent.djch}
														</#if>	
													</div>
													<p class="p_addees"> <span>${agent.companyname }</span> <span>${agent.workyear  } 年服务经验</span></p>
													<p>精耕区域：  ${agent.jgarea}</p>
												</div>
												<div class="pop-contact online-consult" onclick="openChatWindow('${agent.username }', '${agent.realname }', '400-898-6868转${agent.mobile }', '${agent.headImgUrl }')">
													在线咨询
												</div>
											</div>
										</#list>
									</#if>
								</div>
							</div>
							<div class="clear"></div>
			      		</div>
					</#list>
				<#else>
					<div class="isfangyuan">
						没有找到符合条件的房源
					</div>
				</#if>
	      		 <#include "tags/pagination.ftl">
			</div>	
		</div>
<script>

$(function(argument) {
	isPcOrOther('http://wap.dasoujia.com/mobile/views/dsj-newhouse/jd-newhouse-list.html');
	$(document).on("mouseover",".sale-person",function(argument) {
		$(this).next(".pop-content").show()
	})
	$(document).on("mouseleave",".pop-content",function(argument) {
		$(this).hide();
	})
})
function priceToUrl(){
	var urlPr="${url_pr}";
	var pmn=$("#pmn").val();
	var pmx=$("#pmx").val();;
	if(pmn!=null&& pmn!= ''&& pmx!=null&&pmx!=null){
		window.location.href="${ctx}/front/newHouse/jdlist/${urlPr}pmn"+pmn+"pmx"+pmx;
	}
	
}
$(function(){
	var arr = [
	           {"dsj":10817,"jd":1121,"name":"招商中国玺"},
	           {"dsj":10176,"jd":1120,"name":"亦庄金茂府"},
	           {"dsj":10679,"jd":1119,"name":"中粮天恒天悦壹号"},
	           {"dsj":10912,"jd":1118,"name":"天恒半山世家"},
	           {"dsj":10405,"jd":1117,"name":"天恒摩墅"},
	           {"dsj":10675,"jd":1116,"name":"天恒金融街公园懿府"},
	           {"dsj":10882,"jd":1115,"name":"天恒水岸壹号"},
	           {"dsj":11252,"jd":1114,"name":"天恒京西悦府"},
	           {"dsj":10643,"jd":1113,"name":"中铁华侨城和园"},
	           {"dsj":10667,"jd":1112,"name":"中海墅"},
	           {"dsj":11402,"jd":1111,"name":"旭辉城"},
	           {"dsj":10103,"jd":1110,"name":"泷悦长安·观景华宅"},
	           {"dsj":10325,"jd":1109,"name":"永泰西山御园"},
	           {"dsj":11540,"jd":1108,"name":"融创观澜湖公园壹号"},
	           {"dsj":10327,"jd":1107,"name":"长安天街"},
	           {"dsj":10075,"jd":1106,"name":"首开龙湖天琅"},
	           {"dsj":11546,"jd":1105,"name":"景粼原著"},
	           {"dsj":10398,"jd":1104,"name":"K2十里春风"},
	           {"dsj":11543,"jd":1103,"name":"金科天宸荟"},
	           {"dsj":10812,"jd":1102,"name":"首开保利熙悦诚郡"},
	           {"dsj":11538,"jd":1101,"name":"尚峯壹號"},
	           {"dsj":10017,"jd":1100,"name":"首开香溪郡"},
	           {"dsj":11554,"jd":1122,"name":"中海国际城"},
	           {"dsj":10102,"jd":1123,"name":"林肯公园"},
	           {"dsj":10782,"jd":1124,"name":"远洋天著"},
	           {"dsj":10198,"jd":1125,"name":"益田远洋万和风景"},
	           {"dsj":11545,"jd":1126,"name":"远洋7号"},
	           {"dsj":11558,"jd":1127,"name":"北京城建北京合院"},
	           ];
	for(var i in arr){
		$(".phone12").each(function(){
			if($(this).text() == arr[i].dsj){
				$(this).text(arr[i].jd);
				return true;
			}
		})
	}
})

$(".headerRightNav").hide();
</script>
</body>
 
</html>