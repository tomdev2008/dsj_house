<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<script src="${ctx }/static/back/rentHouse/rentDetial.js"></script>
  	<div id="dsj">
  		<!-- 吸顶  -->
  		<div id="nav_ceiling">
            <!-- 主体导航 -->
            <nav class="newhouse_nav">
                <a href="##" class="active">房屋信息</a>
                <a href="##">房源描述</a>
                <a href="##">房屋配置</a>
                <a href="##">周边配套</a>
                <a href="##">推荐房源</a>
            </nav>
        </div>
		<!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	        <h1>大搜家</h1>
	        <span>经纪人</span>
	      </div>
	      <div class="BHLogoRight">
	        <!-- 输入框搜索按钮 -->
	          <div class="TextSeek">
	            <form class="form-inline">
	              <div class="form-group">
	                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	                <div class="input-group">
	                  <input type="text" class="form-control" id="exampleInputAmount" placeholder="请输入地铁线附件的房子开始找房">
	                </div>
	              </div>
	              <button type="submit" class="seek">
	                <!-- <span class="glyphicon glyphicon-search"></span> -->
	              </button>
	            </form>
	          </div>
	        <!-- 地图找房 -->
	          <div class="MapFindHouse">
	            地图找房
	          </div>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress"> 
	      <ul>
	        <li><a href="##">大搜家首页</a>
	        <div class="progressTriangle">
	          <div> 
	          </div>
	        </div>
	        </li>
	        <li><a href="##">租房</a>
	           <div class="progressTriangle">
	          <div> 
	          </div>
	        </div>
	        </li>
	        <li><a href="${ctx}/rentHouse">租房列表</a>
	         <div class="progressTriangle">
	          <div> 
	          </div>
	        </div></li>
	        <li><a href="##">租房详情</a></li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main rent-details">
			<!-- 主体标题 -->
		  	<h2>
	  			${payTypeName} 
	  			${originVo.houseTitle}
			  	<div class="attention">
			  	 + 关注房源
			  	</div>
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
	      			<span>租金</span><strong>${originVo.rentPrice}</strong><span> 元</span>
					<span>本月上涨</span><strong>0.0%</strong>
	      			</p>
	      			<div class="bannder_right_content">
	      				<ul>
	      					<li>
	      					    <span>面积：</span>
	      					    <p>${originVo.area}㎡</p>
	      					</li>
	      					<li>
	      						<span>朝向：</span>
	      					    	<p>${orientationName}</p>
	      					</li>
	      					<li>
	      						<span>户型：</span>
	      					    <p>
	      					    ${originVo.door}室
	      					    ${originVo.hall}厅
	      					    ${originVo.toilet}卫
	      					    ${originVo.kitchen}厨
	      					    </p>
	      					</li>
	      					<li>
	      						<span>租赁方式：</span>
	      					    <p>${originVo.rentTypeName}</p>
	      					</li>
	      					<li>
	      						<span>性别限制：</span>
	      					    <p>${sexTypeName}</p>
	      					</li>
	      					<li>
	      						<span>付款方式：</span>
	      					    <p>${payTypeName}</p>
	      					</li>
	      					<li>
	      						<span>小区名称：</span>
	      					    <p><a href="${ctx}/houseDirectory/details/${originVo.dicId}">
	      					    	${originVo.sprayName}</a></p>
	      					    <input id="rentId" type="hidden" value="${originVo.id}" />
	      					</li>
	      				</ul>
				  	</div>
				  	<div class="banner_right_bottom">
				  		<div class="bannder_contact">
				  			<img src="${agentVo.avatar}">
				  			<div class="contact_text">
				  				<div>
				  				<strong>${agentVo.realname}</strong>
				  				-${agentVo.companyName}
				  				<div class="online-consult">
				  					在线咨询
				  				</div>
				  				</div>
				  				<p>${agentVo.phone}</p>
				  			</div>
				  		</div>
				  	</div>
		      	</div>	
		  	</div>
		  	<!-- 主体导航 -->
		  	<nav class="newhouse_nav">
	            <a class="active">房屋信息	
	            	<!-- 三角形样式 -->
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	            <a >房源描述
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a >房屋配置
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a >周边配套
	              <span class="activeTriangle" >
	              </span>
	            </a>
	            <a >推荐房源
	              <span class="activeTriangle">
	              </span>
	            </a>
	        </nav>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content">
	      		<!-- 房屋信息 -->
	      		<div class="newhouse_content_list none_border">
	      			<h3>基本信息</h3>
	      			<div class="list_content2_border">
	      				<div class="list_content list_content2">
		      				<ul>
		      					<li>
		      					    <span>房源租金：</span>
		      					    <p>${originVo.rentPrice}元</p>
		      					</li>
		      					<li>
		      						<span>付款方式：</span>
		      					    <p>${payTypeName}</p>
		      					</li>
		      					<li>
		      						<span>租赁方式：</span>
		      					    <p>${originVo.rentTypeName}</p>
		      					</li>
		      					<li>
		      						<span>性别限制：</span>
		      					    <p>${sexTypeName}</p>
		      					</li>
		      				</ul>
				  		</div>
					  	<div class="list_content list_content2">
							<ul>
		      					<li>
		      					    <span>面积：</span>
		      					    <p>${originVo.area}㎡</p>
		      					</li>
		      					<li>
		      						<span>朝向：</span>
		      					    <p>${orientationName}</p>
		      					</li>
		      					<li>
		      						<span>楼层：</span>
		      					    <p>${originVo.houseFloor}层/${originVo.totalFloors}层</p>
		      					</li>
		      					<li>
		      						<span>户型：</span>
		      					    <p>
				      					${originVo.door}室
			      					    ${originVo.hall}厅
			      					    ${originVo.toilet}卫
			      					    ${originVo.kitchen}厨
		      					    </p>
		      					</li>
		      				</ul>
				 		</div>
				 		<div class="list_content list_content2">
							<ul>
		      					<li>
		      					    <span>小区名称：</span>
		      					    <p>${originVo.rentTypeName}</p>
		      					</li>
		      					<li>
		      						<span>建筑类型：</span>
		      					    <p>${zxTypeName}</p>
		      					</li>
		      					<li>
		      						<span>物业类型：</span>
		      					    <p>${wyTypeName}</p>
		      					</li>
		      					<li>
		      						<span>发布时间：</span>
		      					    <p>${originVo.updateTime?string("yyyy-MM-dd")}</p>
		      					</li>
		      				</ul>
				 		</div>
		      		</div>		
		      	</div>
		      	<!-- 房源描述 -->
	      		<div class="newhouse_content_list none_border">
	      			<h3>房源描述</h3>
	      			<div class="list_content2_border none_background">
	      				${originVo.houseDesc}
	      			</div>		
		      	</div>
		      	<!-- 房源配置 -->
	      		<div class="newhouse_content_list none_border">
	      			<h3>房源配置</h3>
	      			<div class="house_deploy" data-all="${originVo.detailPoint}">
	      				<a data-p="398" class="hidden" ><i></i><p>床</p></a>
	      				<a data-p="399" class="hidden" ><i></i><p>衣柜</p></a>
	      				<a data-p="400" class="hidden" ><i></i><p>电视</p></a>
	      				<a data-p="401" class="hidden" ><i></i><p>空调</p></a>
	      				<a data-p="402" class="hidden" ><i></i><p>洗衣机</p></a>
	      				<a data-p="403" class="hidden" ><i></i><p>热水器</p></a>
	      				<a data-p="404" class="hidden" ><i></i><p>沙发</p></a>
	      				<a data-p="405" class="hidden" ><i></i><p>暖气</p></a>
	      				<a data-p="406" class="hidden" ><i></i><p>阳台</p></a>
		      		</div>		
		      	</div>
		      	<!--周边配置 地图 -->
	      		<div class="newhouse_content_list  none-border">
	      		<input type="hidden" id="dimension" value="${directoryPo.dimension}">
	      		<input type="hidden" id="accuracy" value="${directoryPo.accuracy}">
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
		      	<!-- 推荐房源 -->
	      		<div class="newhouse_content_list none_border">
	      			<h3>推荐房源</h3>
	      			<div class="house_recommend" id="house_recommend">
						<!--
						<figure>
							<img src="img/new-house/tuijian.png" width="350" height="234" />
							<p>黄浦江上的的卢浦大桥</p>
							<p>昌平-回龙观东大街</p>
						</figure>
						-->
		      		</div>		
		      	</div>
			</div>	
		</div>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
		<script src="${ctx }/static/back/newHouse/map.js"></script>
