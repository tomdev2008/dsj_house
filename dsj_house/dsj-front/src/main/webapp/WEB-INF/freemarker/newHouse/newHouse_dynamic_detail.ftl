	<#include "common/taglibs.ftl">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/newhouse-caption.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/newhouse-dynamic.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/newhouse-like.css">
	<script src="${ctx }/static/back/newHouse/newhouse_dynamic.js"></script>
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
	<script src="${ctx }/static/back/js/cookieEx.js"></script>
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
	        <!-- 地图找房 -->
	          <div class="MapFindHouse">
	            地图找房
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
	         	<a href="${ctx}/front/newHouse/house_dynamic?id=${newHouseId}">楼盘动态列表</a>
		         <div class="progressTriangle">
		        </div>
	        </li>
	         <li>
                <a href="javascript:void(0);">楼盘动态详情</a>
            </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
			<!-- 主体标题 -->
	  		<div class="cap_main">
				<div class="cm_left" id="qrcode">
					<!--<img src="${ctx }/static/front/img/erweima.png"> -->
				</div>
				<div class="cm_center">
					<div class="cmc_div">
					<h3 class="cm_h4">${newHouseName }</h3>
					<span class="sale sale_finish">
						<#if newHouse.status == 1>
			    			待售
			    		<#elseif newHouse.status == 2>
			    			在售
			    		<#elseif newHouse.status == 3>
			    			售完
			    		</#if>
					</span >
					</div>
					<div class="cm_labelling">
						<#if newHouse.dicTraitList??> 
						 	<#list newHouse.dicTraitList as dic> 
		  						<span class="color_${dic_index}">${dic}</span>
		  					</#list>
		  				<#else>
				  			<span class="color_0">暂无</span>
						</#if>
					</div>
				</div>
				<div class="cm_right">
					<div class="or_phone"><span class="or_minphone">售楼处电话：</span><#if newHouse.pcMobile??>400-898-6868<span class="or_minphone">转</span>${newHouse.pcMobile}<#else><span class="or_minphone">暂无</span></#if></div>
					<#if newHouse.authTime>
		    			<div class="gengxin">${newHouse.authTime?string("yyyy")}年${newHouse.authTime?string("MM")}月${newHouse.authTime?string("dd")}日更新</div>
		    		<#else>
		    			<div class="gengxin"></div>
		    		</#if>
				</div>
			</div>
		  	<!-- 主体导航 -->
		  	<nav class="newhouse_nav">
	            <a href="${ctx }/front/newHouse/index_detail?id=${newHouseId}">楼盘首页
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/house_dynamic?id=${newHouseId}" class="active">楼盘动态
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
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
	            <a href="${ctx }/front/newHouse/house_comment?id=${newHouseId}">楼盘点评
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
	        <!-- 主体内容 -->
	      	<div class="new_dynamic">
				<div class="nd_list_kuang nd_dongtai_kuang" style="margin-bottom: 30px;">
					<h4>${houseNews.title}</h4>
					<div class="dongtai_msg">
						<!-- 如果是大搜家，开发商就不要添加a标签 -->
						<div class="msg_icon">
							<#if houseNews.type == 1>
			        			大搜家官方发布
			        		<#elseif houseNews.type == 2>
			        			<a href="${ctx }/front/agentIndex/info?userId=${houseNews.createUser}">${houseNews.userName}</a>发布
			        		<#elseif houseNews.type == 3>
			        			开发商发布
			        		</#if>
						</div>
						<div class="msg_icon">${houseNews.createTime?string("yyyy年MM月dd日 HH:mm")}</div>
					</div>
					<div class="dongtai_news">
						<!-- <img src="${ctx }/static/front/img/erwnnewsbig.png"> -->
						<div class="dongtai_cneter">
							${houseNews.content}
						</div>
					</div>
				</div>
				<div class="nd_like_kuang">
					<div class="like_modle dongtai_model">
						<h2 class="likemodle_header">相关动态</h2>
						<#if relatedNewsList??> 
      						<#list relatedNewsList as news>
      							<a href="#1" data-id="${news.id}" onclick="newsDetail(${news.id},${newHouseId});">
									<p>${news.title}</p>	
								</a>
							</#list>
						<#else>
							<!-- 判断一下，没有数据的时候用暂无便签 -->
							<p class="zanwu">暂无</p>
	      				</#if>
					</div>
				</div>
			</div>
		</div>
		<script>
			var img = new Image();
			img.src=_ctx+"/qr/qrcode?houseId="+${newHouseId};
			$("#qrcode").append(img); 
		</script>