<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="UTF-8">
	<title>优质经纪人-大搜家</title>
	<meta content="新房经纪人,二手房经纪人,中介,签约,二手房买卖,专业经纪人" name="keywords">
	<meta content="大搜家集中北京专业房产经纪人，为您提供买新房、买二手房、权证办理等方面的专业服务。通过经纪人的信用、成交、擅长业务，您可以快速找到需要的经纪人。" name="description">
	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
</head>
<body>
<!-- 经纪人前端logo搜索框 -->
<div class="BHLogo">
  <div class="BHLogoLeft">
    <a href="${ctx}/"><h1>大搜家</h1></a>
    <span>经纪人</span>
  </div>
  <div class="BHLogoRight">
    <!-- 输入框搜索按钮 -->
      <div class="TextSeek">
        <form class="form-inline" onsubmit="${ctx}/front/agent">
          <div class="form-group">
            <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
            <div class="input-group">
              <input type="text" class="form-control" value="${keywords}" name="k" id="inputseek" placeholder="请输入区域，经纪人，公司">
            </div>
            <ul class="grabble_kuang"></ul>
          </div>
          <button type="submit" class="seek">
            <!-- <span class="glyphicon glyphicon-search"></span> -->
          </button>
        </form>
      </div>
  </div>
</div>
<!-- 进程 -->
<div class="PGress"> 
  <ul>
    <li><a href="${ctx}/">大搜家首页</a>
    	<div class="progressTriangle"></div>
    </li>
    <li><a href="##">经纪人列表</a></li>
</div>
<!-- 前端主体 -->
<div class="dsj-main">
	<!-- 表单选项 -->
	<form class="dsj_form">
		<div id="areaId" class="dsj_form_line small_gap">
			<span style="vertical-align: top;" >区域:</span>
			<div id="areaId" class="dsj_form_line small_gap dsj_tab2" style="width: 1135px;display: inline-block;">
			<span  <#if !areaCode??> class="active" </#if>><a href="${ctx}/front/agent/${url_ar}">不限</a></span>
			<#if areaFirstList?? && (areaFirstList?size > 0)>
				<#list areaFirstList as po>
					<span <#if po.areaCode == areaCode> class="active" </#if>> <a href="${ctx}/front/agent/${url_ar}ar${po.areaCode}"> ${po.name} </a></span>
				</#list>
			</#if>
			</div>
		</div>
		<div class="tab-content dsj_tab">
		  <div id="tradeAreaCode" role="tabpanel" class="tab-pane  <#if areaCode??> active </#if>" >
		  	<span <#if !tradeAreaCode??> class="active" </#if>><a href="${ctx}/front/agent/${url_ta}">全部</a></span>
		  	<#if tradeAreaMap??>
		  		<#list tradeAreaMap?keys as key>
			  		<span class="dsj_index">${key}</span>
			  		<#list tradeAreaMap[key] as po>
						<span <#if po.areaCode == tradeAreaCode></#if>>
			 				<a href="${ctx}/front/agent/${url_ta}ta${po.areaCode}"> ${po.name} </a>
						</span>
					</#list>
				</#list>
			</#if>
		  </div>
		</div>
		<p class="dsj_form_line">
			<span>公司:</span>
			<span <#if !companyNum??> class="active" </#if>><a href="${ctx}/front/agent/${url_ct}">不限</a></span>
			<#if companyType?? && (companyType?size > 0)>
				<#list companyType?keys as key>
					<span <#if companyNum == key> class="active" </#if>><a href="${ctx}/front/agent/${url_ct}ct${key}"> ${companyType[key]}</a></span>
				</#list>
			</#if>
		</p>
		<p class="dsj_form_line">
			<span>服务:</span>
			<span <#if !featureNum??> class="active"  </#if> ><a href="${ctx}/front/agent/${url_fw}">不限</a></span>
			<#if agentFeature?? && (agentFeature?size > 0)>
				<#list agentFeature?keys as key>
					<span <#if featureNum == key> class="active" </#if> ><a href="${ctx}/front/agent/${url_fw}fw${key}">${agentFeature[key]}</a></span>
				</#list>
			</#if>
		</p>
		<hr class="bottom_hr">
		<#if conditionMap??&&(conditionMap?size>0) >
			<p class="dsj_form_line dsj_condition">
				<span>条件:</span>
				<#list conditionMap?keys as key> 
					<#if key!=null>	
					<span>${key}<a href="${ctx}/front/agent/${conditionMap[key]}"><span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></a></span>
					</#if>
				</#list>
				<span class="dsj_clear"><span class="glyphicon glyphicon-trash" aria-hidden="true">
				</span><a href="${ctx}/front/agent/">清空选项</a></span>
			</p>
		</#if>
       	<p class="dsj_form_line dsj_sort">
       	<span>排序:</span>
       	<span <#if !ordering??> class="active" </#if>><a href="${ctx}/front/agent/${url_orderding}">默认</a></span>  
       	<span class="hover_span">
       		<a href="${ctx}/front/agent/${url_orderding}od1">工作年限由高到低
       		</a>
       		<#if ordering?? && ordering == 1>
           			<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		<#else>
           			<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		</#if>
   		</span>
       	<span class="hover_span">
       		<a href="${ctx}/front/agent/${url_orderding}od2">信用由高到低
       		</a>
       		<#if ordering?? && ordering == 2>
           			<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		<#else>
           			<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		</#if>
   		</span>  
       	<span class="hover_span">
       		<a href="${ctx}/front/agent/${url_orderding}od3">成交由高到低
       		</a>
       		<#if ordering?? && ordering == 3>
           			<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		<#else>
           			<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		</#if>
   		</span>  
       	<span class="hover_span">
       		<a href="${ctx}/front/agent/${url_orderding}od4">人气由高到低
       		</a>
       		<#if ordering?? && ordering == 4>
           			<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		<#else>
           			<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           		</#if>
		</span>
		<span class="total pull-right">
			为您找到<span class="bluefont">${page.totalCount}</span>名经纪人
		</span>
       </p>
	</form>

    <!-- 主体内容 -->
  	<div class="newhouse_content agent_list">
  		<#if page.recordList?? && (page.recordList?size > 0)>
	  		<#list page.recordList as listVo>
		  		<div class="dsj_row clearfix" style="position: relative;">
					<div class="dsj_row_img">
						<a href="${ctx}/front/agentIndex/info?userId=${listVo.userId}" target="view_window"><#if listVo.avatarReUrl??><img class="img-responsive" src=${listVo.avatarReUrl}><#else><img class="img-responsive" src="${ctx}/static/front/img/default/default_agent.png"></#if></a>
						<div class="online-consult agent-consult" onClick="openChatWindow('${listVo.username}','${listVo.name}','400-898-6868转${listVo.mobile}','${listVo.avatarUrl}')">在线咨询</div>
					</div>
					
					<div class="dsj_row_content">
						<h4>
						    <a href="${ctx}/front/agentIndex/info?userId=${listVo.userId}" target="view_window">${listVo.name}</a>
						    <div class="validate grade_cion ">
						    	<#if listVo.gradeName??>
						    		<img src="${listVo.smallIcon}">
						    		${listVo.gradeName}
					    	 	</#if>
						    </div>
						    <span class="validate validate_vip">${listVo.auditName}<span class="dsj_vip vip_left"></span></span>
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
							<span class="contect_phone pull-right phone_24">400-898-6868<#if listVo.mobile??><span>转</span>${listVo.mobile}</#if></span></span>
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
								<#if listVo.atiTotalCount?? && listVo.atiHighCount??>
								好评 ${(listVo.atiHighCount/listVo.atiTotalCount*100)?string("#.0")}% &nbsp&nbsp 中评 ${(listVo.atiMidCount/listVo.atiTotalCount*100)?string("#.0")}% &nbsp&nbsp 差评 ${(listVo.atiBadCount/listVo.atiTotalCount*100)?string("#.0")}%
								<#else>
								好评 0&nbsp&nbsp中评 0&nbsp&nbsp差评 0
								</#if>
								<h5>专业服务</h5>
								<#if listVo.proTotalCount?? && listVo.proHighCount??>
								好评 ${(listVo.proHighCount/listVo.proTotalCount*100)?string("#.0")}% &nbsp&nbsp 中评 ${(listVo.proMidCount/listVo.proTotalCount*100)?string("#.0")}% &nbsp&nbsp 差评 ${(listVo.proBadCount/listVo.proTotalCount*100)?string("#.0")}%
								<#else>
								好评 0&nbsp&nbsp中评 0&nbsp&nbsp差评 0
								</#if>
							</div>
							
						</div>
						<div class="agent agent_span_middle_container">
							<span class="agent_span_title">精耕区域:</span>
							${listVo.areaName}-${listVo.businessName}
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
	  		<#include "tags/pagination.ftl">
  		<#else>
			<div class="isfangyuan">
				没有找到符合条件的经纪人
			</div>
  		</#if>
	</div>
	<style type="text/css">
		.agent-consult {
			width: 100px;
			margin-top: 14px;
			background-position: 12px center;
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
			margin-left: 17px;
		}
		.agent-pop {
			display: none;
			width: 263px;
			height: 266px;
			background-color: #ffffff;
			position: absolute;
			top: 43px;
			left: 456px;
			z-index: 10;
			box-shadow: 0px 0px 10px 0px #888888;

		}
		
		.agent-uppper {
			font-size: 14px;
			line-height: 2.0;
			color: #ffffff;
			padding: 15px;
			background-color: #3172dc;
		}
		.agent-uppper h6{
			font-size: 18px;
		}
		.agent-lower {
			padding:0 15px;
			font-size:12px;
		}
		.agent-lower h5{
			font-size: 14px;
			font-weight: bold;
			margin:15px 0;
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
		.emblem_0 img{
			width:19px;
			height:24px;
		}
		.grade_cion{
			display:inline-block;
		}
		.grade_cion>img{
			width:19px;
			height:24px;
		}
	</style>
</div>

<script>
	$(function(){
		isPcOrOther('http://wap.dasoujia.com/mobile/views/dsj-agent/dsj-agent.html');
		seekevent();
		$(".grabble_kuang").hide();


		$(document).on("mouseover",".agent_span_detail",function(argument) {
			$(this).parent().parent().next(".agent-pop").show()
		})
		$(document).on("mouseleave",".agent-pop",function(argument) {
			$(this).hide();
		})


	})
	
	function changeArea(obj){
		$("#areaId").show();
		$(obj).addClass("active");
		$(obj).siblings().removeClass("active");
		$("#tradingAreaId").addClass("active");
	}
	
	//输入框
	function seekevent(){
	    var keywords = $("#inputseek").val();
	    // click事件
	    $("#inputseek").on("click",function(){
	        $(this).removeAttr("placeholder");
	        keywords =  $.trim($("#inputseek").val());
	        if(keywords != ""){
                $(".grabble_kuang").show();
	        }
	    })
	    // 将div中的内容赋值给input的value
	    $(".grabble_kuang").delegate("li","click",function(){
	        keywords = $(this).html();
	        $('#inputseek').val(function() {
	            return keywords;
            });
	        $(".grabble_kuang").hide();
	    })
	};
</script>
</body>
 
</html>