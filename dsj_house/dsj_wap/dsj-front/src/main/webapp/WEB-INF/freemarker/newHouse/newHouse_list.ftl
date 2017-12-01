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
</head>
<body>
	  <!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo">
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
	                  <input type="text" class="form-control" value="${kw}" name="k" id="inputseek" placeholder="请输入楼盘名称或地址" autocomplete="off">
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
	    <div class="PGress"> 
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
	  		<form class="dsj_form">
	  			<p class="dsj_form_line">
	  				<span class="dsj_form-title">位置:</span>
	  				<span class="dsj_form_dropdown" id="search_eare"><a href="${ctx}/front/newHouse/list" >区域找房</a></span>
	  				<span class="dsj_form_dropdown " id="search_subway"><a  href="${ctx}/front/newHouse/list/line">地铁找房</a></span>
	  			</p>
	 			<div class="tab-content dsj_tab">
	  			  <div role="tabpanel" class="tab-pane active none_border" id="areaId" <#if line?? > style="display:none" </#if>>
	 				<span <#if areaCode3?? > <#else> class="active"</#if> >
	 				<#if params??>
	 			  		<a href="${ctx}/front/newHouse/list/${params}">不限</a>
	 			  	<#else>
	 			  		<a href="${ctx}/front/newHouse/list">不限</a>
	 			  	</#if>
	 				</span>
	 				<#list areaFirstLists as po>
	 				<span <#if po.areaCode ==areaCode3> class="active" </#if> > 
		 				<#if params??>
		 					<a href="${ctx}/front/newHouse/list/d${po.areaCode}/${params}"> ${po.name} </a>
		 				<#else>
		 				<a href="${ctx}/front/newHouse/list/d${po.areaCode}"> ${po.name} </a>
		 				</#if>
	 				</span>
	 				</#list>
	  			  </div>
	  			  
	  			  <div role="tabpanel" class="tab-pane active none_border" id="subwayLineId" <#if line==null > style="display:none"<#else>style="display:block;"  </#if>>
	 				<span <#if subwayLineId??> <#else>class="active"  </#if>>
	 				<#if params??>
	 			  		<a href="${ctx}/front/newHouse/list/line/${params}">不限</a>
	 			  	<#else>
	 			  		<a href="${ctx}/front/newHouse/list/line">不限</a>
	 			  	</#if>
	 				</span>
	 				<#list subwayLineList as po>
	 				<span <#if po.id ==subwayLineId> class="active" </#if> > <a href="${ctx}/front/newHouse/list/line/s${po.id}"> ${po.name} </a></span>
	 				</#list>
	  			  </div>
	  			</div>
	 			
	 			<div class="tab-content dsj_tab sub_tab">
	 			  <div id="tradingAreaId" role="tabpanel" class="tab-pane  <#if  subwayLineId == null && areaCode3??> active </#if>" >
	 			  	<#if tradeAreaMap?? >
	 			  	<span <#if tradingAreaId??> <#else>class="active"  </#if> >
	 			  	<#if params??>
	 			  		<a href="${ctx}/front/newHouse/list/d${areaCode3}/${params}">不限</a>
	 			  	<#else>
	 			  		<a href="${ctx}/front/newHouse/list/d${areaCode3}">不限</a>
	 			  	</#if>
	 			  	</span>
	 			  	<#list tradeAreaMap?keys as key>
					  		<span class="dsj_index">${key}</span>
					  		<#list tradeAreaMap[key] as po>
								<span <#if po.areaCode ==tradingAreaId></#if>>
							
					 				<#if params??>
					 					<a href="${ctx}/front/newHouse/list/d${areaCode3}_${po.areaCode}/${params}"> ${po.name} </a>
					 				<#else>
					 					<a href="${ctx}/front/newHouse/list/d${areaCode3}_${po.areaCode}"> ${po.name} </a>
					 				</#if>
								
								</span>
	 						</#list>
	 				</#list>
	 				</#if>
	 			  </div>
	 			  <div id="subwayId" role="tabpanel" class="tab-pane <#if line?? && subwayLineId??> active </#if>" >
	 			  	<span <#if subwayId??> <#else>class="active"  </#if>>
	 			  	<#if params??>
	 			  		<a href="${ctx}/front/newHouse/list/line/s${subwayLineId}/${params}">不限</a>
	 			  	<#else>
	 			  		<a href="${ctx}/front/newHouse/list/line/s${subwayLineId}">不限</a>
	 			  	</#if>
	 			  	</span>
	 			  	<#list subwayList as po>
	 				<span <#if po.id ==subwayId> class="active" </#if> > <a href="${ctx}/front/newHouse/list/line/s${subwayLineId}_${po.id}"> ${po.name} </a></span>
	 				</#list>
	 			  </div>
	 			</div>
		 			
	 			<p class="dsj_form_line">
	 				<span class="dsj_form-title">价格:</span>
	 				<span <#if params?index_of("pr")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/list/${url_pr}${url_kw}">不限</a></span>
	 				<#list priceMap?keys as key>
	 					<span <#if params?index_of("pr"+key)!=-1> class="active" </#if> ><a href="${ctx}/front/newHouse/list/${url_pr}pr${key}${url_kw}">  ${priceMap[key]}</a></span>
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
	 				<span <#if params?index_of("rm")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/list/${url_rm}">不限</a></span>
	 					<#list roomMap?keys as key>
	 					<span <#if params?index_of("rm"+key)!=-1> class="active" </#if>><a href="${ctx}/front/newHouse/list/${url_rm}rm${key}">  ${roomMap[key]}</a></span>
	 				</#list>
	 			</p>
 			
	  			<p class="dsj_form_line">
	  				<span class="dsj_form-title">特色:</span>
	  				<span <#if params?index_of("ts")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/list/${url_ts}">不限</a></span>
	  				<#list tsMap?keys as key>
	 					<span <#if params?index_of("ts"+key)!=-1> class="active" </#if>><a href="${ctx}/front/newHouse/list/${url_ts}ts${key}">  ${tsMap[key]}</a></span>
	 				</#list>
	  			</p>
	  			<p class="dsj_form_line more_option">
	 				<span class="dsj_form-title">类型:</span>
	 				<span <#if params?index_of("wy")==-1> class="active"  </#if> ><a href="${ctx}/front/newHouse/list/${url_wy}">不限</a></span>
	 				<#list wyType?keys as key>
	 					<span <#if params?index_of("wy"+key)!=-1> class="active" </#if>><a href="${ctx}/front/newHouse/list/${url_wy}wy${key}">  ${wyType[key]}</a></span>
	 				</#list>
	 			</p>
	  			
	  			<hr class="bottom_hr">
	  			<#if conditionMap?? && conditionMap?size gt 0 >	
	 			<p class="dsj_form_line dsj_condition">
	 				<span>条件:</span>
	 				
	 				<#list conditionMap?keys as key> 
	 					<#if key!=null>	
	 					<span>${key}<a href="${ctx}/front/newHouse/list/${conditionMap[key]}"><span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></a> </span>
	 					</#if>
	 				</#list>
	 				<span class="dsj_clear"><span class="glyphicon glyphicon-trash" aria-hidden="true">
	 				
	 				</span><a href="${ctx}/front/newHouse/list/" style="
    color: #999;
    ">清空选项</a></span>
	 			</p>
	 			</#if>

	            <p class="dsj_form_line dsj_sort">
	            <span>排序:</span>
	            <#if ordering!=3 && ordering!=2 >
	           		<span class="active">
	           	<#else>
	           		<span>
		         </#if>
	           	<a href="${ctx}/front/newHouse/list/${url_orderding}od1">默认排序</a></span>
		           	<span class="hover_span">
		           		<a href="${ctx}/front/newHouse/list/${url_orderding}od2">价格由低到高
		          	 	</a>
		          	 	<#if ordering?? && ordering==2 >
		           			<span class="active dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
			          	<#else>
				          	<span class="dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true" style="margin-right: 0;"></span>
				         </#if>
		           	</span>
	           		<span  class="hover_span">
	           			<a href="${ctx}/front/newHouse/list/${url_orderding}od3">时间由旧到新
		           		</a>
		           		<#if ordering?? && ordering==3>
			           			<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
			           		<#else>
			           			<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
			           		</#if>
	           		</span>
	           			
	  				<span class="total pull-right">
	  					为您找到<span class="bluefont">${page.totalCount}</span>个北京楼盘
	  				</span>
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
									   		<span>售价待定</span>
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
									 <#if newHouse.pcMobile??>
										<span class="contect_phone pull-right"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
											 400-898-6868  转  ${newHouse.pcMobile }
										</span>
									</#if>
								</p>
								<div class="agent">
									<span>动态:</span>
									<#if newHouse.title??&&newHouse.title!="">
										${newHouse.title }
									<#else>
									   		暂无
								  	 </#if>
								</div>
								<div class="labelling">
			      					 <#if newHouse.dicTraitShowNameArr??> 
			      					 	<#list newHouse.dicTraitShowNameArr as dic><span class="color_${dic_index}">${dic }</span></#list>
			      					</#if>
			      				</div>
								<div class="dsj_contect pull-right pop-contianer">
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
	isPcOrOther('http://wap.dasoujia.com/mobile/views/dsj-newhouse/newhouse-list.html');
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
		window.location.href="${ctx}/front/newHouse/list/${urlPr}pmn"+pmn+"pmx"+pmx;
	}
	
}
</script>
</body>
 
</html>