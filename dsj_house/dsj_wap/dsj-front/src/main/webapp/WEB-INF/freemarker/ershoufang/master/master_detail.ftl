<#include "common/taglibs.ftl">
 <!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<meta charset="UTF-8">
	<title>${oldHouseMaster.title}-${oldHouseMaster.title}二手房详情-大搜家</title>
	<meta content="${oldHouseMaster.title},${dicPo.dicName}二手房详情,北京二手房,北京二手房价格,北京二手房户型" name="keywords">
	<meta content="${oldHouseMaster.title}二手房，位于${dicPo.tradingAreaName}商圈。朝向<#if oldHouseMaster.orientations??><#list orientationsMap?keys as key><#if orientationsMap?? && oldHouseMaster.orientations==key>${orientationsMap[key]}</#if></#list></#if>户型，${oldHouseMaster.room}室${oldHouseMaster.hall}厅${oldHouseMaster.toilet}卫${oldHouseMaster.kitchen}厨，楼层${oldHouseMaster.floorTypeName}，挑选优质二手房上大搜家。" name="description">
	<!-- swiper -->
	<link rel="stylesheet" href="${ctx}/static/front/css/swiper-3.4.2.min.css">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.src.js"></script>
    <![endif]-->
</head>
  <body>
  	<div id="dsj">
	  <!-- 经纪人前端头部 -->
	   
	  <!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	        <a href="${ctx}/"> <h1>大搜家</h1></a>
	        <span>二手房</span>
	      </div>
	      <div class="BHLogoRight">
	        <!-- 输入框搜索按钮 -->
	          <div class="TextSeek">
	             <form class="form-inline"  action="${ctx}/ershoufang" >
	              <div class="form-group">
	                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	                <div class="input-group">
	                  <input type="text" class="form-control" name="k"  id="inputseek"  placeholder="请输入小区名称或地址">
	                </div>
	                 <ul class="grabble_kuang">
                  	</ul>
                 </ul>
	              </div>
	              <button type="submit" class="seek">
	                <!-- <span class="glyphicon glyphicon-search"></span> -->
	              </button>
	            </form>
	          </div>
	        <!-- 地图找房 -->
	          <div class="MapFindHouse">
	         <a href="${ctx}/map/oldMap">   地图找房</a>
	          </div>
	      </div>
	    </div>
	    
	     <div id="nav_ceiling">
            <!-- 主体导航 -->
            <nav class="newhouse_nav">
                <a href="#basic" class="active">基本信息</a>
                <a href="#sellingpoint">核心卖点</a>
                <a href="#mapBox">周边配套</a>
                <a href="#dic">小区信息</a>
                <a href="#recommend">推荐房源</a>
            </nav>
        </div>
	    
	  <!-- 进程 -->
	    <div class="PGress"> 
	      <ul>
	        <li><a href="${ctx}/">大搜家首页</a>
	        <div class="progressTriangle">
	          <div> 
	          </div>
	        </div>
	        </li>
	        <li><a href="${ctx}/ershoufang">二手房列表</a>
	         <div class="progressTriangle">
	          <div> 
	          </div>
	        </div></li>
	        
	        <li><a href="##">二手房详情</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main rent-details secondhand-details">
			<!-- 主体标题 -->
		  	<h2>
		  		${oldHouseMaster.title} 
		  		<#if followId??&&followId!=0>
		  			<div style="cursor:pointer;" onmouseover="$(this).html('取消关注')" onmouseout="$(this).html('已关注')" class="attention" onclick="cancelHouse()">
			  		已关注
			  	</div>
			  	<#else>
				  <div style="cursor:pointer;" class="attention" onclick="attentionHouse()">
			  		 + 关注房源
			  		</div>
			  	</#if>
		  	</h2>
		  	<!-- 主体banner -->
		  	<div class="dsj-main-banner ershou_banner">
		  		<div class="banner_left">
		  			<!-- Swiper -->
		  			<div id="carousel" class="dsj-main-banner carousel slide dsj-carousel" data-ride="carousel" data-interval="false">
		  			  <!-- Wrapper for slides -->
		  			  <div class="dsj-carousel__inner">
		  			    <div class="carousel-inner" role="listbox">
		  			    	<#if oldHousePictures?size gt 0>
			  			    	<#list oldHousePictures as po>
				  			      <div class="item
									<#if 0==po_index>
									active
									</#if>
									">
				  			        <img src="${po.pictureUrl}?x-oss-process=image/resize,m_lfit,h_400,w_640" alt="...">
				  			      </div>
			  			    	</#list>
		  			    	<#else>
		  			    	
		  			    	<div class="item active">
			  			        <img src="${ctx}/static/front/img/default/ershoufangxiangqing.jpg" alt="...">
			  			      </div>
		  			    	</#if>
		  			    	
		  			    </div>

		  			    <!-- Controls -->
		  			    <a class="swiper-button-next swiper-button-white" href="#carousel" role="button" data-slide="next">
		  			    </a>
		  			    <a class="swiper-button-prev swiper-button-white" href="#carousel" role="button" data-slide="prev">
		  			    </a>
		  			  </div>


		  			  <div class="preview-container">
		  			    <div class="carousel-indicators preview-indicators left">
		  			    	<#list oldHousePictures as po>
			  			      <div data-target="#carousel" data-slide-to="${po_index}" class="preview-pic
				  			      <#if 0==po_index>
				  			      	active
				  			      </#if>
								">
			  			        <img class="indicateimg" src="${po.pictureUrl}" alt="...">
			  			      </div>
		  			    	</#list>

		  			    </div>
		  			    <div class="swiper-button-white preview-prev"></div>
		  			    <div class="swiper-button-white preview-next"></div>
		  			  </div>

		  			</div>
		  		</div>
		  		<div class="banner_right">
	      			<div class="r_price">
	      				<p><strong>${oldHouseMaster.price}</strong>万</p>
	      				<ul>
	      					<li>参考单价：${oldHouseMaster.unitPrice}元/m<sup>2</sup></li>
	      					<li><#if oldHouseMaster.payments??>
	      					参考首付：${oldHouseMaster.payments}万  
	      					<#else>
	      						<#if oldHouseMaster.price>
	      						参考首付：${oldHouseMaster.price*0.3}万  
	      						</#if>
	      					</#if>
	      					<#if oldHouseMaster.monthpay??> 月供：${oldHouseMaster.monthpay}元（参考）</#if></li>
	      				</ul>	
	      			</div>
	      			<div class="r_details">
	      				<figure>
							<p>${oldHouseMaster.floorTypeName}层（共${oldHouseMaster.floorNum}层）</p>
							<h4>${oldHouseMaster.buildArea} m²</h4>
						</figure>
						<figure>
							<p>
								<#list renovationMap?keys as key>
								<#if key==oldHouseMaster.renovation>
									${renovationMap[key]}
								</#if>
							</#list>
							</p>
							<h4>${oldHouseMaster.room}室${oldHouseMaster.hall}厅${oldHouseMaster.toilet}卫${oldHouseMaster.kitchen}厨</h4>
						</figure>
						<figure>
							<p>${oldHouseMaster.buildYear}
							<#if oldHouseMaster.buildYear?? || oldHouseMaster.buildYear!=''>年建/</#if> 
							<#list houseTypeMap?keys as key>
								<#if key==oldHouseMaster.houseType>
									${houseTypeMap[key]}
								</#if>
							</#list></p>
							<h4>
							<#list orientationsMap?keys as key>
								<#if key==oldHouseMaster.orientations>
									${orientationsMap[key]}
								</#if>
							</#list>
							</h4>
						</figure>
	      			</div>
	      			<div class="m_details">
	      					<p class="labelling">
	      					
	      					<#if oldHouseMaster.featureNames??>
	      							<#list oldHouseMaster.featureNames?split(",") as featureName>
	      					
									<span class="color_${featureName_index}  ">${featureName}</span>
									</#list>
							</#if>
	      					
	      					</p>
	      					<p class="xiaoquname">
	      						<span>小区名称：<a class="none_hover" href="#dic">${dicPo.sprayName}</a></span>
	      						<a class="mapfind" href="#mapBox"></a>
	      					</p>
	      			</div>
	      			<!-- 中介价与等级章二选一 -->
	      			<div class="more_agent">
	      				<#if listAgents??&&listAgents?size gt 0>
	      				<#list listAgents as agent>
		      				<#if agent.userId?? >
			      				<div class="bannder_contact agent_cart" <#if agent_index gt 1 > style="display:none" </#if>>
						  			<img  class="img_70"  src="${agent.dsjAvatarUrl}">
						  			<div class="contact_text">
						  				<div class="name_header">
							  				<div class="name_company"><span>${agent.dsjAgentName}</span>-${agent.dsjShortName}</div>
							  				<div class="planner">
							  				<#if agent.smallIcon??>
							  				<img src="${agent.smallIcon}">
							  				</#if>
							  				${agent.gradeName}</div>
						  				</div> 
						  				<p class="phone_c"><#if agent.mobile??>400-898-6868转${agent.mobile}</#if></p>
						  			</div>
						  		</div>
						  	</#if>
					  		<#if !agent.userId??>
					  			<div class="bannder_contact agent_cart" <#if agent_index gt 1 > style="display:none" </#if>>
						  			
						  			<#if agent.companyType==2> <img  class="img_70"  src="${ctx}/static/front/img/company/lianjia.png"> </#if>
						  			
						  			<#if agent.companyType==3> <img  class="img_70"  src="${ctx}/static/front/img/company/maitian.png"> </#if>
						  			
						  			<#if agent.companyType==4> <img  class="img_70"  src="${ctx}/static/front/img/company/zhongyuan.png"> </#if>
						  			
						  			<#if agent.companyType==5> <img  class="img_70"  src="${ctx}/static/front/img/company/5i5j.png"> </#if>
						  			<div class="contact_text">
						  				<div class="name_header">
							  				<div class="name_company"><span>${agent.agentName}</span>-${agent.companyTypeName}</div>
							  				<p class="c_fei">
						  					<strong>${agent.price}</strong>万 中介费约<strong>${agent.agentFree}</strong>万
						  					</p>
						  				</div> 
						  				<p class="phone_c">${agent.agentPhone}</p>
						  			</div>
					  			</div>
					  		</#if>
				  		</#list>
				  		</#if>
				  		<#if listAgents??&&listAgents?size gt 2>
				  		<!-- 当收起时添加class="packup_botton" 内容为点击收起 -->
				  		<label class="isfold_button" id="unfold_button_id">
				  			展开更多经纪人
				  			
				  		</label>
				  		</#if>
	      			</div>
				  	
		      	</div>	
		  	</div>
		  	<!-- 主体导航 -->
		  	<nav class="newhouse_nav">
	            <a href="#basic" class="active">基本信息
	            <!-- 三角形样式 -->
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	            <a href="#sellingpoint">核心卖点
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#mapBox">周边配套
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a  href="#dic">小区信息
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#recommend">推荐房源
	              <span class="activeTriangle">
	              </span>
	            </a>
	        </nav>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content old_message" >
	      		<!-- 房屋信息 -->
	      		<div class="newhouse_content_list none_border">
	      			<a id="basic" class="spy-link"></a>
	      			<h3>基本信息</h3>
	      			<div class="list_content2_border">
	      				<div class="list_content list_content2">
		      				<ul>
		      					<li>
		      					    <span class="width_60">小区：</span>
		      					    <p><a href="${ctx}/houseDirectory/details/${dicPo.id}">${dicPo.sprayName}</a></p>
		      					</li>
		      					<li>
		      						<span class="width_60">板块：</span>
		      					    <p>${dicPo.areaName3} - ${dicPo.tradingAreaName} - ${dicPo.sprayName}</p>
		      					</li>
		      					<li>
		      						<span class="width_60">年代：</span>
		      					    <p><#if oldHouseMaster.buildYear??>${oldHouseMaster.buildYear}<#else>暂无</#if></p>
		      					</li>
		      					<li>
		      						<span class="width_60">类型：</span>
		      					    <p><#if oldHouseMaster.houseType??> 
			      					    <#list houseTypeMap?keys as key>
											<#if key==oldHouseMaster.houseType>
												${houseTypeMap[key]}
											</#if>
										</#list>
		      					    <#else> 暂无</#if></p>
		      					</li>
		      				</ul>
				  		</div>
					  	<div class="list_content list_content2">
							<ul>
		      					<li>
		      					    <span class="width_60">房型：</span>
		      					    <p class="list_address">${oldHouseMaster.room}室${oldHouseMaster.hall}厅${oldHouseMaster.toilet}卫${oldHouseMaster.kitchen}厨</p>
		      					</li>
		      					<li>
		      						<span class="width_60">面积：</span>
		      					    <p><#if oldHouseMaster.orientations??>  ${oldHouseMaster.buildArea}m<sup>2</sup>
		      					    	<#else>
		      					    		暂无
		      					    	</#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span class="width_60">朝向：</span>
		      					    <p><#if oldHouseMaster.orientations??> 
		      					    <#list orientationsMap?keys as key>
											<#if orientationsMap?? && oldHouseMaster.orientations==key>
												<span class="color_yellow">${orientationsMap[key]}</span>
											</#if>
										</#list>
		      					    <#else> 暂无</#if></p>
		      					</li>
		      					<li>
		      						<span class="width_60">总楼层：</span>
		      					    <p>${oldHouseMaster.floorNum}</p>
		      					</li>
		      				</ul>
				 		</div>
				 		<div class="list_content list_content2">
							<ul>
		      					<li>
		      					    <span>装修程度：</span>
		      					    <p>
		      					    	<#if oldHouseMaster.renovation??>
			      					    <#list renovationMap?keys as key>
											<#if key==oldHouseMaster.renovation>
												${renovationMap[key]}
											</#if>
										</#list>
										<#else>
										暂无
										</#if>
							        </p>
		      					</li>
		      					<li>
		      						<span>房屋单价：</span>
		      					    <p>${oldHouseMaster.unitPrice} 元/m<sup>2</sup></p>
		      					</li>
		      					<li>
		      						<span>参考首付：</span>
		      					    <p>
		      					    <#if oldHouseMaster.payments??>
		      					    ${oldHouseMaster.payments}万元  
	      					<#else>
	      						<#if oldHouseMaster.price>
	      						${oldHouseMaster.price*0.3}万元 
	      						</#if>
	      					</#if></p>
		      					</li>
		      					<li>
		      						<span>参考月供：</span>
		      					    <p> <#if oldHouseMaster.monthpay??> ${oldHouseMaster.monthpay}元 <#else> 暂无</#if></p>
		      					</li>
		      				</ul>
				 		</div>
		      		</div>		
		      	</div>
		      	<!-- 核心卖点 -->
	      		<div class="newhouse_content_list none_border">
	      			<a id="sellingpoint" class="spy-link"></a>
	      			<h3>核心卖点</h3>
	      			<div class="list_content2_border none_background">
	      				${oldHouseMaster.sellingPoint}
		      		</div>		
	      		<!--周边配置 地图 -->
	      		<div class="newhouse_content_list  none-border" id="zhouBianPeiTao">
	      		<input type="hidden" id="dimension" value="${dicPo.accuracy}">
	      		<input type="hidden" id="accuracy" value="${dicPo.dimension}">
	      			<input type="hidden" id="newHouseName" value="${dicPo.sprayName }"> 
	      		<#if dicPo.accuracy??>
	      			<h3>周边配套</h3>
      			<div class="map_container" style="position:relative;">
      			<div class="Map_box"  id="mapBox">
	      		</div>	
	      		<div class="assort_tabs">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
					    <li role="presentation" class="active"><a href="javascript:void(0);" onclick="change('交通')" aria-controls="traffic" role="tab" data-toggle="tab">交通</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('医疗')" aria-controls="hospital" role="tab" data-toggle="tab">医疗</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('购物')" aria-controls="shopping" role="tab" data-toggle="tab">购物</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('生活')" aria-controls="live" role="tab" data-toggle="tab">生活</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('娱乐')" aria-controls="recreation" role="tab" data-toggle="tab">娱乐</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('教科')" aria-controls="education" role="tab" data-toggle="tab">科教</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
					    <div role="tabpanel" class="tab-pane active" id="traffic">
							<div class="traffic_tabs">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist" id="twoMapTab">
									    <li role="presentation" class="active"><a href="javascript:void(0);" onclick="change('地铁站')" aria-controls="subway" role="tab" data-toggle="tab">地铁站</a></li>
									    <li role="presentation"><a href="javascript:void(0);" onclick="change('公交站')"  aria-controls="public" role="tab" data-toggle="tab">公交站</a></li>
									    <li role="presentation"><a href="javascript:void(0);" onclick="change('火车站')"  aria-controls="train" role="tab" data-toggle="tab">火车站</a></li>
									</ul>
									<div class="tab-content">
									    <div role="tabpanel" class="tab-pane active" >
									    	<ul class="address_list" id="results">
									    	</ul>
									    </div>
									</div>
							</div>
					    </div>
					</div>

					</div>	
					</div>
		      	</div>
		      	</#if>
		      	<!-- 小区信息 -->
	      		<div class="newhouse_content_list none_border">
	      			<a id="dic" class="spy-link"></a>
	      			<div class="house_type_tittle">
	      				<h2>小区信息
						</h2>
						<div class="more"><a href="${ctx}/houseDirectory/details/${dicPo.id}">查看详情</a>
								<div class="progressTriangle">
						        </div>
						</div>
			    	</div>
	      			<div class="estate_message">
	      				<div class="estate_message_div">
	      					<#if houseDicPic.pictureUrl??>
	      						<img src="${houseDicPic.pictureUrl}" alt="cart.png">
							<#else>
								<img src="http://dasouk.oss-cn-qingdao.aliyuncs.com/upload/pic/20170929211001818806.png?x-oss-process=image/resize,m_lfit,h_273,w_362" alt="cart.png">
							</#if>
	      				</div>
	      				<div class="estate_message_div">
	      					<h4><a href="${ctx}/houseDirectory/details/${dicPo.id}">${dicPo.sprayName}</a></h4>		  		
				      		<ul>
			      				<li>
			      					<span>小区均价：</span>
			      					<p class="yellow_1"><#if dicPo.averagePrice??> ${dicPo.averagePrice}元/m<sup>2</sup> <#else> 暂无</#if> </p>
			      				</li>
			      				<li>
			      					<span>建筑面积：</span>
			      					<p><#if dicPo.builtUp??> ${dicPo.builtUp}m<sup>2</sup> <#else> 暂无 </#if></p>
			      				</li>
			      				<li>
			      					<span>供暖方式：</span>
			      						<#if dicPo.heatingMode==null>
			      						暂无
			      						</#if>
				      					<#list heatingModeMap?keys as key>
											<#if heatingModeMap?? && dicPo.heatingMode==key>
												<p>${heatingModeMap[key]}</p>
											</#if>
										</#list>
													      					
			      				</li>
			      				<li>
			      					<span>所在版块：</span>
			      					<p>${dicPo.areaName3}-${dicPo.tradingAreaName}</p>
			      				</li>
			      				<li>
			      					<span>物业费用：</span>
			      					<p><#if dicPo.propertyFee??> ${dicPo.propertyFee} <#else>暂无 </#if></p>
			      				</li>
			      				<li>
			      					<span>物业公司：</span>
			      					<p>${dicPo.property}</p>
			      				</li>
			      			</ul>	
					    </div>	
	      				<div class="estate_message_div">
	      					<ul> 
			      				<li>
			      					<span>总住户：</span>
			      					<p>${dicPo.houseNum}</p>
			      				</li>
			      				<li>
			      					<span>绿化率：</span>
			      					<p><#if dicPo.greenRate??> ${dicPo.greenRate}% <#else> 暂无</#if></p>
			      				</li>
			      				<li>
			      					<span>容积率：</span>
			      					<p><#if dicPo.plotRatio??>${dicPo.plotRatio}%<#else> 暂无</#if></p>
			      				</li>
			      				<li>
			      					<span>车位数：</span>
			      					<p><#if dicPo.parkNumber??>${dicPo.parkNumber}<#else> 暂无</#if></p>
			      				</li>
			      				<li>
			      					<span>开发商：</span>
			      					<p class="ellipsis_detail_15"><#if dicPo.developers??> ${dicPo.developers}<#else> 暂无</#if></p>
			      				</li>
			      			</ul>
	      				</div>
	      			</div>		
		      	</div>
		      	<!-- 推荐房源 -->
		      	<#if recommendVos?? &&recommendVos?size gt 0>
	      		<div class="newhouse_content_list none_border margin-b70">
	      			<a id="recommend" class="spy-link"></a>
	      			<h3>推荐房源</h3>
	      			<div class="house_recommend">
	      				<#list recommendVos as vo>
	      				<a href="${ctx}/oldmaster/${vo.id}.html" target="_blank">
	      				<figure>
	      					<#if vo.imageUrl??>
							<img src="${vo.imageUrl}?x-oss-process=image/resize,m_lfit,h_234,w_350" width="350" height="234" />
							<#else>
							<img   style="cursor:pointer" width="350" height="234"  src="${ctx}/static/front/img/default/default_list.png"/>
							</#if>
							<p>${vo.title}</p>
							<p  class="figure_price"><strong>${vo.price}</strong>万<span class="square" class="square">${vo.buildArea}<span>m<sup>2</sup></span></span></p>
						</figure>
						</a>
						</#list>
		      		</div>		
		      	</div>
		      	</#if>
			</div>	
		</div>
		</div>
	  <!-- footer -->
  <#include "common/popup_menu.ftl">
	</div>
	</div> 
	
	<!-- Initialize Swiper -->
	
 <script src="${ctx }/static/back/oldHouse/ershoufang_search.js"></script>
    <script>
    	// swiper
    	$(function(){
    		  var galleryTop = new Swiper('.gallery-top', {
    		        nextButton: '.swiper-button-next',
    		        prevButton: '.swiper-button-prev',
    		        spaceBetween: 10,
    		        loop : true
    		    });
    		    var galleryThumbs = new Swiper('.gallery-thumbs', {
    		    	nextButton: '.swiper-button-next',
    		        prevButton: '.swiper-button-prev',
    		        spaceBetween: 10,
    		        centeredSlides: true,
    		        slidesPerView: 'auto',
    		        touchRatio: 0.2,
    		        slideToClickedSlide: true,
    		        loop : true
    		    });
    		    galleryTop.params.control = galleryThumbs;
    		    galleryThumbs.params.control = galleryTop;  
    		/* 
    		 $(".seek").on("click",function(){
    				window.location.href="${ctx}/ershoufang";
    		 }) */
    			seekevent();
    		
    			 isPcOrOther('http://wap.dasoujia.com/mobile/views/dsj-usedhouse/used-details.html?id=${oldHouseMaster.id}');
    	})
   	 var user="${Session.pc_user_session}";
	 //关注
   	 function attentionHouse(){
   		 if(user==''){
   			show_box(7);
   		 }else{
   			 $.ajax({
  		  		type:"post",
  		  		url:_ctx+"/front/agentIndex/addFollow",
  		  		data:{
  		  			"objectId":'${oldHouseMaster.id}',
  		  			"type":2
  		  		},
  		  		dataType:"json",
  		  		success: function(result){
  		  			if(result.status==200){
		  				location.reload();
		  			}
  		  		}
  		  	});
   		 }
   	
   	 }
   	 //取消关注
   	 function cancelHouse(){
   		 if(user==''){
   			show_box(7);
    		 }else{
    			 $.ajax({
   		  		type:"post",
   		  		url:_ctx+"/front/agentIndex/cancleFollow",
   		  		data:{
   		  			"objectId":'${oldHouseMaster.id}',
   		  			"type":2
   		  		},
   		  		dataType:"json",
   		  		success: function(result){
   		  			if(result.status==200){
   		  				location.reload();
   		  			}
   		  		}
   		  	});
    	}
   	 }
   	 
   //地图
     $(".nav-tabs").delegate("li","click",function(){		
     	if(!$(this).hasClass("active")){
     		 $(this).addClass("active").siblings().removeClass("active");
     	}
     });
   	 
    </script>
    <script src="${ctx }/static/front/js/newhouse.js"></script>	
    <script src="${ctx }/static/front/js/scrollspy.js"></script>
	 <script>
		$('body').scrollspy({ target: '#nav_ceiling' });
		$('[data-spy="scroll"]').each(function () {
			var $spy = $(this).scrollspy('refresh');
		});
		
			function ismore(){
		  			var unfold = $("#unfold_button_id").find("input"),
	  				agent_carts = $(".agent_cart"),
	            	agent2 = agent_carts.slice(2,agent_carts.length);
		  			
		  			if($("#unfold_button_id").hasClass("unfold_button") ){
		  				$("#unfold_button_id").addClass("isfold_button");
		  				$("#unfold_button_id").removeClass("unfold_button");
		  				$("#unfold_button_id").html("展开经纪人列表");
		  				agent2.each(function (index, item) { item.style.display ="none"});
		  			}else{
		  				$("#unfold_button_id").addClass("unfold_button");
		  				$("#unfold_button_id").removeClass("isfold_button");
		  				$("#unfold_button_id").html("收起经纪人列表");
		  				agent_carts.each(function (index, item) { item.style.display="block"});
		  				 
		  			}
	  		}  
			
			 
		    $("#unfold_button_id").on("click",function(){
		    	ismore();
		    	event.stopPropagation();
		    });
	 
	 //   window.onload =ismore;

	    $(function(argument) {
	    	// body...
		      var bottom = {
		        "preview_window" : 4,
		        "distence" : 154,
		        "total" : $(".carousel-inner .item").length,
		        "start" : 0,
		        "current" : 1,
		        "move_right":move_right,
		        "move_left":move_left
		      }
		      function move_right() {
		      if ( this.total - this.start > this.preview_window ) {
		          this.start += this.preview_window;
		          $(".preview-indicators").css("margin-left",-this.start*this.distence)
		      } else {
		          return;
		      }
		      }
		      function move_left() {
		      if ( this.start >= this.preview_window) {
		          this.start -= this.preview_window;
		          $(".preview-indicators").css("margin-left",-this.start*this.distence)
		      } else {
		          return
		      }
		      }
		    
		    // var move_window_size = whole - preview_window;


		    $(document).on("click",".preview-prev",function(argument) {
		      bottom.move_left();
		      // var margin_left = $(".preview-indicators").css("margin-left");
		      // margin_left = parseInt(margin_left);
		      // $(".preview-indicators").css("margin-left",margin_left-107)
		    })
		    $(document).on("click",".preview-next",function(argument) {
		          bottom.move_right();

		      // var margin_left = $(".preview-indicators").css("margin-left");
		      // margin_left = parseInt(margin_left);
		      // $(".preview-indicators").css("margin-left",margin_left+107)

		    })
		   
		})

    </script>
    	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=10ldxb9hp1tc0PcUKXt71u2wiHy12Lp5"></script>
		<script src="${ctx }/static/back/newHouse/map.js"></script>
	
  	</body>
</html>