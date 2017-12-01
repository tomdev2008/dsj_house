	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
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
	        <li><a href="javascript:void(0);">新房详情</a>
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
	            <a href="${ctx }/front/newHouse/newHouse_agent?id=${newHouseId}">推荐经纪人
	              <span class="activeTriangle" >
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
	            <a href="javascript:void(0);"  class="active">楼盘详情
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/map?id=${newHouseId}">周边配套
	              <span class="activeTriangle">
	              </span>
	            </a>
	        </nav>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content newhouse_des">
	      		<!-- 模块一 -->
	      		<div class="newhouse_content_list">
	      			<h3 class="first_list">基本信息</h3>
	      			<div class="list_content">
	      				<ul>
	      					<li>
	      					    <span>楼盘名称</span>
	      					    <p><#if newHouse.name??> 
		      					  	${newHouse.name }
		      					  <#else>
		      					  		暂无
		      					   </#if></p>
	      					</li>
	      					<li>
	      						<span>楼盘特点</span>
	      					    <p>
	      					     <#if newHouse.dicTraitList??> 
		      					 	<#list newHouse.dicTraitList as dic> 
			      						<span class="color_${dic_index}">${dic }</span>
			      					</#list>
			      				</#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>参考单价</span>
	      					    <p>
	      					    	<#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.referencePriceMin?? && wyMsg.referencePriceMax??> 
			      					  		  		<#if wyMsg.referencePriceMin!=wyMsg.referencePriceMax> 
			      					  		  			<strong>${wyMsg.referencePriceMin?round }-${wyMsg.referencePriceMax?round }</strong> 元/平&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		<#else>
			      					  		  			<strong>${wyMsg.referencePriceMin?round }</strong> 元/平&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		</#if>
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		  	
		      					  		  	
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>参考总价</span>
	      					    <#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.totalPriceMin?? && wyMsg.totalPriceMax??> 
			      					  		  		<#if wyMsg.totalPriceMin!=wyMsg.totalPriceMax> 
			      					  		  			<strong>${wyMsg.totalPriceMin?round }-${wyMsg.totalPriceMax?round }</strong> 万元/套&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		<#else>
			      					  		  			<strong>${wyMsg.totalPriceMin?round }</strong> 万元/套&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		</#if>
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		  	
		      					  		  	
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
	      					</li>
	      					<li>
	      						<span>物业类型</span>
	      					    <p>
	      					     <#if newHouse.wyTypeName??> 
		      					  	${newHouse.wyTypeName }
		      					  <#else>
		      					  		暂无
		      					   </#if>
	      					    </p>
	      					</li>

	      				</ul>
				  	</div>
				  	<div class="list_content">
						<ul>
	      					<li>
	      					    <span>开发商</span>
	      					    <p class="developers_name">
	      					    <#if newHouse.developers??> 
		      					  	${newHouse.developers }
		      					  <#else>
		      					  		暂无
		      					   </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>区域位置</span>
	      					    <p>
	      					    <#if newHouse.areaDetail??> 
		      					  	${newHouse.areaDetail }
		      					  <#else>
		      					  		暂无
		      					   </#if>
	      					   </p>
	      					</li>
	      					<li>
	      						<span>楼盘地址</span>
	      					    <p>
	      					     <#if newHouse.address??> 
		      					  	${newHouse.address }
		      					  <#else>
		      					  		暂无
		      					   </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>售楼处电话</span>
	      					    <p>
	      					   		<#if newHouse.pcMobile??> 
		      					  		<strong id="phone12">400-898-6868  转  ${newHouse.pcMobile }</strong>
		      					  	<#else>
		      					  		暂无
		      					   </#if>
	      					    </p>
	      					</li>
	      				</ul>
			 		</div>
	      		</div>
	      		<!-- 模块二 -->
	      		<div class="newhouse_content_list">
	      			<h3>销售信息</h3>
	      			<div class="list_content">
	      				<ul>
	      					<li>
	      					    <span>最低首付</span>
	      					    <p>
	      					     <#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.payments??> 
		      					  		  			<strong>${wyMsg.payments?round }</strong> 万&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
			      					  		  	
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					    </p>
	      					</li>
	      					<li>
	      						 <span>月供</span>
	      						 <p>
	      					     <#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.monthPay??> 
		      					  		  			<strong>${wyMsg.monthPay?round}</strong> 元/月&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
			      				</p>
	      					</li>
	      					<li>
	      						<span>楼盘优惠</span>
	      					    <p>
	      					    	<#if newHouse.discount??> 
		      					  		${newHouse.discount }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>楼盘户型</span>
	      					    <p>
	      					    	 <#if newHouse.houseTypeMsg??> 
		      					  		${newHouse.houseTypeMsg }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
	      					    </p>
	      					</li>
	      				</ul>
				  	</div>
				  	<div class="list_content">
						<ul>
	      					<li>
	      					    <span>开盘时间</span>
	      					    <p>
	      					    <#if newHouse.openTimeList?? && newHouse.openTimeList?size gt 0> 
		      					 	<#list newHouse.openTimeList as openTime> 
			      					 	<#if openTime_index<5> 
			      					 		<#if openTime.openHandTime??>
				      					 		${openTime.wyTypeName }
					      							${openTime.describes } ${openTime.openHandTime?string("yyyy-MM-dd") }
				      						</#if>
				      					</#if>
			      					</#list>
			      				<#else>
			      					暂无
			      				</#if>
					      		</p>
	      					</li>
	      					<li>
	      						<span>交房时间</span>
	      						<p>
	      					   <#if newHouse.handTimeList?? && newHouse.handTimeList?size gt 0> 
		      					 	<#list newHouse.handTimeList as openTime> 
			      					 	<#if openTime_index<5> 
			      					 	<#if openTime.openHandTime??> 
			      					 		${openTime.wyTypeName }
				      							${openTime.describes } ${openTime.openHandTime?string("yyyy-MM-dd") }
				      					</#if>
				      					</#if>
			      					</#list>
			      				<#else>
			      					暂无
			      				</#if>
				      			</p>
	      					</li>
	      					<li>
	      						<span>售楼处地址</span>
	      					     <p><#if newHouse.saleAddress??> 
		      					  		${newHouse.saleAddress }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					 </p>
		      					    
	      					</li>
	      					<li>
	      						<span>预售许可证</span>
	      					    <p><#if newHouse.prep??> 
	      					  		${newHouse.prep }
	      					  	<#else>
	      					  		暂无
	      					    </#if>
	      					    </p>
	      					</li>
	      				</ul>
			 		</div>
	      		</div>
	      		<!-- 模块三 -->
	      		<div class="newhouse_content_list">
	      			<h3>小区情况</h3>
	      			<div class="list_content">
	      				<ul>
	      					<li>
	      					    <span>建筑类型</span>
	      					    <p>
	      					    	<#if newHouse.achTypeName?? && newHouse.achTypeName!=''> 
		      					  		${newHouse.achTypeName }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>产权年限</span>
	      						<p>
	      						<#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.propertyRightName?? && wyMsg.propertyRightName!=''> 
			      					  		  		${wyMsg.propertyRightName }&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
			      				</p>
	      					</li>
	      					<li>
	      						<span>容积率</span>
	      						<p>
	      					    <#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.plotRatio?? && wyMsg.plotRatio!=''> 
			      					  		  		${wyMsg.plotRatio }&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
			      					</p>
	      					</li>
	      					<li>
	      						<span>绿化率</span>
	      					    <p>
	      					    <#if newHouse.greenRate?? && newHouse.greenRate!=''> 
		      					  		${newHouse.greenRate }
		      					  	<#else>
		      					  		暂无
		      					    </#if></p>
	      					</li>
	      					<li>
	      						<span>规划户数</span>
	      					    <p>
	      					    <#if newHouse.houseCount?? && newHouse.houseCount!=''> 
		      					  		${newHouse.houseCount }
		      					  	<#else>
		      					  		暂无
		      					    </#if></p>
	      					</li>

	      				</ul>
				  	</div>
				  	<div class="list_content">
						<ul>
	      					<li>
	      					    <span>楼层状况</span>
	      					    <p>
	      					    <#if newHouse.floorMsg?? && newHouse.floorMsg!=''> 
	      					  		${newHouse.floorMsg }
	      					  	<#else>
	      					  		暂无
	      					    </#if></p>
	      					</li>
	      					<li>
	      						<span>工程进度置</span>
	      					    <p>
	      					    <#if newHouse.schedule?? && newHouse.schedule!=''> 
	      					  		${newHouse.schedule }
	      					  	<#else>
	      					  		暂无
	      					    </#if></p>
	      					</li>
	      					<li>
	      						<span>物业管理费</span>
	      						<p>
	      					   <#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.propertyFee?? && wyMsg.propertyFee!=''> 
			      					  		  		${wyMsg.propertyFee } 元/㎡/月
			      					  		  	<#else>
			      					  		  		暂无
			      					  		  	</#if>
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
			      				</p>
	      					</li>
	      					<li>
	      						<span>物业公司</span>
	      					    <p>
	      					    <#if newHouse.property?? && newHouse.property!=''> 
	      					  		${newHouse.property }
	      					  	<#else>
	      					  		暂无
	      					    </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>车位数</span>
	      					    <p>
	      					    <#if newHouse.parkNumber?? && newHouse.parkNumber!=''> 
	      					  		${newHouse.parkNumber }
	      					  	<#else>
	      					  		暂无
	      					    </#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>车位比</span>
	      					    <p>
	      					     <#if newHouse.parkingSpace?? && newHouse.parkingSpace!=''> 
	      					  		${newHouse.parkingSpace }
	      					  	<#else>
	      					  		暂无
	      					    </#if>
	      					    </p>
	      					</li>
	      				</ul>
			 		</div>
	      		</div>	
			</div>	
		</div>
<script>
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
		$("#phone12").each(function(){
			if($("#phone12").text()==("400-898-6868  转 "+" "+arr[i].dsj)){
				$("#phone12").text("400-898-0806 转 "+" "+arr[i].jd);
				return true;
			}
		})
	}
})
</script>