	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
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
	        <li><a href="${ctx}/front/newHouse/list">新房列表</a>
	         <div class="progressTriangle">
	        </div>
	        </li>
	         <li><a href="${ctx}/front/newHouse/index_detail?id=${newHouseId}">新房详情</a>
	         <div class="progressTriangle">
	        </div>
	        </li>
	        <li><a href="javascript:void(0);">推荐经纪人</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
			<!-- 主体标题 -->
		  	<h2>${newHouseName }</h2>
		  	<!-- 主体导航 -->
		  	<nav class="newhouse_nav">
	            <a href="${ctx }/front/newHouse/index_detail?id=${newHouseId}">楼盘首页
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/house_dynamic?id=${newHouseId}">楼盘动态
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/house_type?id=${newHouseId}">楼盘户型
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="javascript:void(0);" class="active">推荐经纪人
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/house_picture?id=${newHouseId}">楼盘相册
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/house_comment?id=${newHouseId}&type=3">楼盘点评
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/detail?id=${newHouseId}">楼盘详情
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/map?id=${newHouseId}">周边配套
	              <span class="activeTriangle">
	              </span>
	            </a>
	        </nav>
			<div class="newhouse_content agent_list">
			 <#if agentList??> 
  		<#list agentList as listVo>
	  		<div class="dsj_row clearfix" style="position: relative;">
				<div class="dsj_row_img">
					<a href="${ctx}/front/agentIndex/info?userId=${listVo.userId}"><img class="img-responsive" src="${listVo.avatarUrl}"></a>
				</div>
				<div class="online-consult agent-consult" onClick="openChatWindow('${listVo.username}','${listVo.name}','400-898-6868转${listVo.mobile}','${listVo.avatarUrl}')">在线咨询</div>
				<div class="dsj_row_content">
					<h4>
					    <a href="${ctx}/front/agentIndex/info?userId=${listVo.userId}">${listVo.name}</a>
					     <div class="validate grade_cion ">
					    	<#if listVo.gradeName??>
					    		<img src="${listVo.smallIcon}">
					    		${listVo.gradeName}
				    	 	</#if>
					    </div>
					    <span class="validate">${listVo.auditName}<span class="dsj_vip"></span></span>
					</h4>
					<p class="clearfix">
						<span class="agent_span_title">服务公司:</span>
						${listVo.companyName}
					</p>
					<p class="clearfix agent_span_middle_container">
						<span class="agent_span_title">职业资历:</span>
						${listVo.workyears}年服务经验

						<span class="agent_span_middle">综合得分：
							<span class="agent-score"><#if listVo.totalScore??>${listVo.totalScore}<#else>0</#if></span> 分
							<span class="agent_span_detail">查看详情</span>
						</span>
						<span class="contect_phone pull-right"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
						 <#if agentList??> 
							400-898-6868转${listVo.mobile}
						<#else>
							暂无
						</#if>
						</span>
					</p>
					<div class="agent-pop">
						<div class="huzhang">
							<#if listVo.bigIcon??><img src="${listVo.bigIcon}"></#if>
						</div>
						<div class="agent-uppper">
							<h6>${listVo.gradeName}</h6>
							综合得分：<#if listVo.totalScore??>${listVo.totalScore}<#else>0</#if> 分<br>
							获评次数：<#if listVo.atiTotalCount?? || listVo.proTotalCount??>${listVo.atiTotalCount + listVo.proTotalCount}<#else>0</#if> 次
						</div>
						<div class="agent-lower">
							<h5>服务态度</h5>
							<#if listVo.atiTotalCount??>
							好评 ${(listVo.atiHighCount/listVo.atiTotalCount*100)?string("#.0")}%      中评 ${(listVo.atiMidCount/listVo.atiTotalCount*100)?string("#.0")}%      差评 ${(listVo.atiBadCount/listVo.atiTotalCount*100)?string("#.0")}%
							<#else>
							好评 0      中评 0      差评 0
							</#if>
							<h5>专业服务</h5>
							<#if listVo.proTotalCount??>
							好评 ${(listVo.proHighCount/listVo.proTotalCount*100)?string("#.0")}%      中评 ${(listVo.proMidCount/listVo.proTotalCount*100)?string("#.0")}%      差评 ${(listVo.proBadCount/listVo.proTotalCount*100)?string("#.0")}%
							<#else>
							好评 0      中评 0      差评 0
							</#if>
						</div>
						
					</div>
					<div class="agent agent_span_middle_container">
						<span class="agent_span_title">精耕区域:</span>
						${listVo.areaName}  ${listVo.businessName}
						<span class="agent_span_middle">带看${listVo.takeLook}套 成交${listVo.deal}套</span>
					</div>
					<p class="dsj_tag">
							<#if listVo.featureArray??>	
								<#list listVo.featureArray as feature> 
									<span class="color_${feature_index}">${feature}</span>
								</#list>
							</#if>
						</p>
				</div>
  			</div>
  		</#list>
  		</#if>
	</div>
		</div>
		<style type="text/css">
		.agent-consult {
			width: 98px;
			position: absolute;
			bottom: 35px;
		}
		.agent-score {
			font-family: Helvetica;
			font-size: 24px;
			font-weight: bold;
			line-height: 1.0;
			color: #ff6600;

		}
		.agent_span_middle_container {
			position: relative;
		}
		.agent_span_middle {
			position: absolute;
			left: 445px;
		}
		.agent_span_detail {
			font-size: 12px;
			color: #3172dc;
			cursor: pointer;
		}
		.agent-pop {
			display: none;
			width: 263px;
			height: 266px;
			background-color: #ffffff;
			position: absolute;
			top: 43px;
			left: 456px;
			z-index: 2;
			box-shadow: 0px 0px 10px 0px #888888;

		}
		
		.agent-uppper {
			font-size: 14px;
			line-height: 2.0;
			color: #ffffff;
			background-color: #3172dc;
			padding: 15px;
			
		}
		.agent-uppper h6{
			font-size: 18px;
		}
		.agent-lower {
			padding: 15px;
		}
		.agent-lower h5{
			font-size: 14px;
			font-weight: 500;
		}
		.grade_cion{
			display:inline-block;
		}
		.grade_cion>img{
			width:19px;
			height:24px;
		}
		.huzhang{
			width:71px;
			height:86px;
			position: absolute;
   	 		top: 16px;
    		right: 16px;
		}
		.huzhang>img{
			width:100%;
			height:100%;
		}
	</style>

<script>
	$(function(){
		$(document).on("mouseover",".agent_span_detail",function(argument) {
			$(this).parent().parent().next(".agent-pop").show()
		})
		$(document).on("mouseleave",".agent-pop",function(argument) {
			$(this).hide();
		})
	})
</script>
