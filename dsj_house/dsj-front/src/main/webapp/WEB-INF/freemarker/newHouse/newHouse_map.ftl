	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<script src="${ctx }/static/back/newHouse/newhouse.js"></script>
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
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
	        <!--  <a href="${ctx }/map/newMap">地图找房</a> -->
	          <div class="MapFindHouse">
	             <a href="${ctx }/map/newMap">地图找房</a>
	          </div>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress"> 
	      <ul>
	        <li>
                <a href="${ctx}/">大搜家首页</a>
                <div class="progressTriangle">
                </div>
            </li>
             <li>
	             <a href="${ctx}/front/newHouse/list">新房列表</a>
		         <div class="progressTriangle">
		        </div>
	        </li>
	         <li>
	         	<a href="${ctx}/front/newHouse/index_detail?id=${newHouseId}">新房详情</a>
		         <div class="progressTriangle">
		        </div>
	        </li>
	         <li>
                <a href="javascript:void(0);">周边配套</a>
            </li>
            </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
			<!-- 主体标题 -->
	  		<h2>${newHouseName }</h2>
	  			<input type="hidden" id="newHouseName" value="${newHouseName }"> 
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
	            <a  href="${ctx }/front/newHouse/newHouse_agent?id=${newHouseId}">推荐经纪人
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
	            <a href="${ctx }/front/newHouse/detail?id=${newHouseId}">楼盘详情
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="javascript:void(0);" class="active">周边配套
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	        </nav>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content agent_list">
	      		<!--周边配置 地图 -->
      		<div class="newhouse_content_list  none-border">
	      		<input type="hidden" id="dimension" value="${newHouse.dimension}">
	      		<input type="hidden" id="accuracy" value="${newHouse.accuracy}">
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
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('教育')" aria-controls="education" role="tab" data-toggle="tab">科教</a></li>
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
			</div>	
		</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
<script src="${ctx }/static/back/newHouse/map.js"></script>