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
                <a href="javascript:void(0);">楼盘动态</a>
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
					<div class="or_phone"><span class="or_minphone">售楼处电话：</span>${newHouse.phone}<span class="or_minphone">转</span>22495</div>
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
	            <a href="javascript:void(0);" class="active">楼盘动态
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
	        <!-- 主体内容 -->
	      	<div class="new_dynamic">
				<div class="nd_list_kuang">
					<#if newsList??> 
	      			<#list newsList as news> 
					<div class="nd_list">
						<div class="ndl_left">
							<div class="ls_day">${news.createTime?string("dd")}</div>
							<div class="ls_mkuang">
							<p class="ls_ri">
								${news.createTime?string("yyyy-MM")}
							</p>
							<p class="ls_mon" data-time="${news.createTime?string("yyyy-MM-dd")}"></p>
							</div>
						</div>
						<div class="ndl_center">
							<a href="#" class="nalc_name" data-id="${news.id}" onclick="newsDetail(${news.id},${newHouseId});">${news.title}</a>
							<a class="nalc_cen" href="#" onclick="newsDetail(${news.id},${newHouseId});">
								${news.contentst}
							</a>
							<!-- 如果是大搜家，开发商就不要添加a标签 -->
							<div class="fabu_icon">
							<#if news.type == 1>
			        			大搜家官方发布
			        		<#elseif news.type == 2>
			        			<a href="${ctx }/front/agentIndex/info?userId=${news.createUser}">${news.userName}</a>发布
			        		<#elseif news.type == 3>
			        			开发商发布
			        		</#if>
							
							</div>
						</div>
					</div>
					</#list>
		      		</#if>
		      		<#include "tags/pagination.ftl">
				</div>
				<div class="nd_like_kuang">
					<div class="like_modle">
						<h2 class="likemodle_header">猜你喜欢</h2>
						<#if newHouse.likeList??> 
				  			<#list newHouse.likeList as house>
								<a href="${ctx }/front/newHouse/index_detail?id=${house.id }" target="_blank">
									<p>${house.name}</p>
									<#if house.referencePrice??>
										<span class="lin_price"><strong>${house.referencePrice}</strong>元/平</span>
									<#else>
										<span class="lin_price isconfirmed">价格待定</span>	  				
							 		</#if>
								</a>
							</#list>
					  	<#else>
					  		暂无
					    </#if>
					</div>
				</div>
			</div>
			
		</div>
		<script>
		/*
			function limitNum(){
				$(".dynamic_content_text").each(function(){
				    var str = $(this).attr("data-content");
					if(str.length>80){
			        	str = str.substr(0,80) + '...' ;
			        	$(this).html(str);
			        }else{
			        	$(this).html(str);
			        };
				});
			}
			limitNum();
		*/	
			function List_name(txt,idName) {
		        var str = txt;
		        if(str.length >20){
		        	 str = str.substr(0,20) + '...' ;
		       	 	var id=$(idName);
		        	id.html(str);
		        }
		    }
			var data = $(".nalc_name").html().trim();
			List_name(data,'.nalc_name');
			
			function transDateToWeekDay(){
				$(".ls_mon").each(function(){
				    var dateStr = $(this).attr("data-time");
					var numArr = ['日','一','二','三','四','五','六'];
				    var day = new Date(dateStr).getDay();
					$(this).html("星期"+numArr[day]);
				});
			}
			transDateToWeekDay();
			
			var img = new Image();
			img.src=_ctx+"/qr/qrcode?houseId="+${newHouseId};
			$("#qrcode").append(img); 
		</script>
