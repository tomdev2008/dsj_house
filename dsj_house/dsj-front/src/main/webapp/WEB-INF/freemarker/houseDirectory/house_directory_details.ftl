<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta charset="UTF-8">
	<title>${houseVo.sprayName}-北京优质小区-大搜家</title>
	<meta content="${houseVo.sprayName}小区详情,北京优质小区,北京小区价格，北京小区户型" name="keywords">
	<meta content="<#if houseVo.sprayName??>${houseVo.sprayName}</#if>小区，位于<#if houseVo.tradingAreaName??>${houseVo.tradingAreaName}</#if>商圈，小区共<#if houseVo.floorNum??>${houseVo.floorNum}</#if>栋楼，建成年代<#if houseVo.achYear??>${houseVo.achYear}</#if>，<!--建筑类型<#if houseVo.achTypeName??>${houseVo.achTypeName}</#if>-->。挑选优质小区上大搜家。" name="description">
	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script src="${ctx }/static/back/house_directory/detial.js"></script>
</head>
<body>
		<!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	        <h1>大搜家</h1>
	        <span>小区</span>
	      </div>
	      <div class="BHLogoRight">
	        <!-- 输入框搜索按钮 -->
	          <div class="TextSeek">
	            <form class="form-inline">
	              <div class="form-group">
	                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	                <div class="input-group">
	                  <input type="text" class="form-control" id="exampleInputAmount" placeholder="请输入小区名称或地址">
	                </div>
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
	        </div>
	        </li>
	        <li><a href="">小区详情</a>
	          <div> 
	          </div>
	        </div>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main rent-details ">
	  		<h2>
	  			${houseVo.sprayName}
		  	</h2>
		  	<!-- 主体banner -->
		  	<div class="dsj-main-banner">
		  		<div class="banner_left">
		  			<!-- Swiper -->
				    <div class="swiper-container gallery-top">
				        <div class="swiper-wrapper">
				        	<#list picList as pic>
                		   		<div class="swiper-slide" style="background:url(${pic.pictureUrl}) no-repeat center ;background-size:100%;"></div>
               				</#list>
				        </div>
				        <!-- Add Arrows -->
				        <div class="swiper-button-next swiper-button-white"></div>
				        <div class="swiper-button-prev swiper-button-white"></div>
				    </div>
				    <div class="swiper-container gallery-thumbs">
				        <div class="swiper-wrapper">
				        	<#list picList as pic>
                		   		<div class="swiper-slide" style="background:url(${pic.pictureUrl})no-repeat center ;background-size:100%; "></div>
				        	</#list>
				            </div>
				        <!-- Add Arrows -->
				        <div id="button-prev" class="swiper-button-prev swiper-button-white"></div>
				        <div id="button-next" class="swiper-button-next swiper-button-white"></div>
				    </div>
		  		</div>
		  		<div class="banner_right">
	      			<p>
	      			<span>均价</span><strong>${houseVo.averagePrice}</strong><span> 元</span>
					<!-- <span>本月上涨</span><strong>0.0%</strong> -->
	      			</p>
	      			<div class="bannder_right_content">
	      				<ul>
	      					<li>
	      					    <span>建筑面积：</span>
	      					    <#if houseVo.builtUp??>${houseVo.builtUp}㎡<#else>暂无</#if>
	      					    </p>
	      					</li>
	      					<li>
	      						<span>在租房源：</span>
	      					    <p>暂无</p>
	      					</li>
	      					<li>
	      						<span>物业费用：</span>
	      					    <p>${houseVo.propertyFee}</p>
	      					</li>
	      					<li>
	      						<span>供暖方式：</span>
	      					    <p>${houseVo.heatingModeName}</p>
	      					</li>
	      					<li>
	      						<span>物业公司：</span>
	      					    <p>${houseVo.property}</p>
	      					</li>
	      					<li>
	      						<span>所在板块：</span>
	      					    <p>${houseVo.areaName3}-${houseVo.tradingAreaName}</p>
	      					</li>
	      					<li>
	      						<span>周围中介：</span>
	      					    <p>暂无</p>
	      					</li>
	      				</ul>
				  	</div>
		      	</div>	
		  	</div>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content diretory_details">
	      		<!-- 房屋信息 -->
	      		<div class="newhouse_content_list none_border">
	      			<h3>基本信息</h3>
	      			<div class="list_content2_border">
	      				<div class="list_content list_content2">
		      				<ul>
		      					<li>
		      					    <span>小区名称：</span>
		      					    <p>${houseVo.sprayName}</p>
		      					</li>
		      					<li>
		      						<span>在租房源：</span>
		      					    <p>暂无</p>
		      					</li>
		      					<li>
		      						<span>所在板块：</span>
		      					    <p>${houseVo.areaName3}-${houseVo.tradingAreaName}</p>
		      					</li>
		      					<li>
		      						<span>更新时间：</span>
		      					    <p><#if houseVo.updateTime??> ${houseVo.updateTime?string("yyyy-MM-dd")} </#if> </p>
		      					</li>
		      				</ul>
				  		</div>
				 		<div class="list_content list_content2">
							<ul>
		      					<li>
		      					    <span>总户数：</span>
		      					    <p>
		      					    <#if houseVo.houseNum??>${houseVo.houseNum}户<#else>暂无</#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>绿化率：</span>
		      					    <p>
		      					    <#if houseVo.greenRate??>${houseVo.greenRate}<#else>暂无</#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>容积率：</span>
		      					    <p>
		      					    <#if houseVo.plotRatio??>${houseVo.plotRatio}<#else>暂无</#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>车位数：</span>
		      					    <p>
		      					    <#if houseVo.parkNumber??>${houseVo.parkNumber}<#else>暂无</#if>
		      					    </p>
		      					</li>
		      				</ul>
				 		</div>
				 		<div class="list_content list_content2">
							<ul>
		      					<li>
		      					    <span>物业公司：</span>
		      					    <p>${houseVo.property}</p>
		      					</li>
		      					<li>
		      						<span>物业费：</span>
		      					    <p>${houseVo.propertyFee}</p>
		      					</li>
		      					<li>
		      						<span>供暖方式：</span>
		      					    <p>${houseVo.heatingModeName}</p>
		      					</li>
		      				</ul>
				 		</div>
		      		</div>		
		      	</div>
		      	<!--周边配置 地图 -->
	      		<div class="newhouse_content_list  none-border">
		      		<input type="hidden" id="dimension" value="${houseVo.accuracy}">
		      		<input type="hidden" id="accuracy" value="${houseVo.dimension}">
		      		<input type="hidden" id="newHouseName" value="${houseVo.sprayName }"> 
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
		      	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
				<!--<script src="${ctx }/static/back/house_directory/map.js"></script>-->
				<script src="${ctx }/static/back/newHouse/map.js"></script>
			</div>	
		</div>
</body>
</html>