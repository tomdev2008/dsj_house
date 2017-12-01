	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/album.css">
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
  	<div id="photoalbum">
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
                <a href="javascript:void(0);">楼盘户型</a>
            </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main rent-details">
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
	            <a href="javascript:void(0);" class="active">楼盘户型
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/newHouse_agent?id=${newHouseId}">推荐经纪人
	              <span class="activeTriangle">
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
	        <!-- nav -->
	        <ul class="nav nav-pills">
			  <li role="presentation" class="active"><a onclick="setStyle(this)" href="javascript:void(0);">全部户型(${houseTypeMap.totalCount })</a></li>
			  <#if houseTypeMap.houseTypeList??> 
			 	<#list houseTypeMap.houseTypeList as houseType> 
					  <li role="presentation"><a onclick="setStyle(this)" href="#houseType${houseType_index }">${houseType.roomName }(${houseType.count })</a></li>
				</#list>
			 </#if>
			</ul>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content">
				<!-- 楼盘主体 -->
			    <div class="main">
			    <#if houseTypeMap.houseTypeList??> 
				 	<#list houseTypeMap.houseTypeList as houseTypeBigList> 
						 <h2 id="houseType${houseTypeBigList_index }">${houseTypeBigList.roomName }(${houseTypeBigList.count })</h2>
				          	<div class="row typeListImg" >
				          		<#list houseTypeBigList.houseTypeList as houseType> 
					            <div class="col-xs-6 col-md-3 pointer">
					            <#if houseType.imgUrl??&&houseType.imgUrl!="">
								 	 <img src="${houseType.imgUrl }?x-oss-process=image/resize,m_pad,h_290,w_220,color_FFFFFF">
								<#else>
				           			  <img src="${ctx }/static/front/img/newHouseType.png">
				           		</#if>
					              <div class="typeDetails">
					                <div class="typeDetailsTop">
					                <p>${houseType.houseName } 
					                 <#if houseType.room??>${houseType.room }室</#if>
					                 <#if houseType.hall??>${houseType.hall }厅</#if>
									 <#if houseType.toilet??>${houseType.toilet }卫</#if>
					                </p>
						                 <#if houseType.houseStatus==1> 
					                 	   	<div class="staySale">主推 </div>
						                 <#elseif houseType.houseStatus==2>
					               		     <div class="staySale">待售 </div>
						                 <#elseif houseType.houseStatus==3>
						                  	 <div class="staySale selling">在售</div>
						                 <#elseif houseType.houseStatus==4>
						             		  <div class="staySale sellOut">售罄 </div>
						                 </#if>
					              
					                </div>
					                <p>建筑面积：${houseType.builtUp }㎡</p>
					              </div>
					            </div>
					            </#list>	
				          	</div>
					</#list>
				 </#if>
			    </div>
			</div>	
		</div>
	</div>
	<div id="album_modal" class="album_modal housetype modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="${ctx }/static/front/img/album/album-close.png"></button>
	      </div>
	      <div class="modal-body" style="padding-bottom: 30px;">
	      	<div>
	      		<div class="swiper-button-next album-right"></div>
	      		<div class="swiper-button-prev album-left"></div>
	      		<div class="swiper-container modal-gallery-top" style="width:1127px;height: 670px;">
	      		    <div class="swiper-wrapper">
	      		    <#if houseTypeMap.houseTypeList??> 
				 		<#list houseTypeMap.houseTypeList as houseTypeBigList> 
				 		<#list houseTypeBigList.houseTypeList as houseType> 
							 <div class="swiper-slide">
			      		          <#if houseType.imgUrl??&&houseType.imgUrl!="">
								 	 <img src="${houseType.imgUrl }?x-oss-process=image/resize,m_pad,h_670,w_550,color_FFFFFF">
								<#else>
				           			  <img src=" ${ctx }/static/front/img/newHouseType.png">
				           		</#if>
			      		          <div class="housetype-swiper">
			      		          	<h4 class="housetype-swiper-title">${houseType.houseName }</h4>
			      		          	<h3 class="housetype-swiper-subtitle"></h3>
			      		          	<p class="housetype-swiper-spec">
			      		          		<span class="spec-title">参考总价</span>
			      		          		<span class="spec-content">
			      		          		
			      		          		<#if houseType.referencePrice??> 
			      		          			<span class="price">${houseType.referencePrice }</span>万
			      		          		<#else>
			      		          			暂无
			      		          		 </#if>
			      		          		</span>
			      		          	</p>
			      		          	<p class="housetype-swiper-spec">
			      		          		<span class="spec-title">居室</span>
			      		          		<span class="spec-content">
			      		          		<#if houseType.room??> 
			      		          			${houseType.room }室
			      		          		</#if>
			      		          		<#if houseType.hall??> 
			      		          			${houseType.hall }厅
			      		          		</#if>
			      		          		<#if houseType.toilet??> 
			      		          			${houseType.toilet }卫
			      		          		</#if>
			      		          		<#if houseType.kitchen??> 
			      		          			${houseType.kitchen }厨
			      		          		</#if>
			      		          		</span>
			      		          	</p>
			      		          	<p class="housetype-swiper-spec">
			      		          		<span class="spec-title">建筑面积</span>
			      		          		<span class="spec-content">
			      		          		<#if houseType.builtUp??> 
			      		          			${houseType.builtUp }㎡	
			      		          		<#else>
			      		          			暂无
			      		          		</#if>
			      		          		</span>
			      		          	</p>
			      		          	<p class="housetype-swiper-spec">
			      		          		<span class="spec-title">朝向</span>
			      		          		<span class="spec-content">
			      		          		<#if houseType.orientationsName??> 
			      		          			${houseType.orientationsName }	
			      		          		<#else>
			      		          			暂无
			      		          		</#if>
			      		          		</span>
			      		          	</p>
			      		          	<p class="housetype-swiper-spec">
			      		          		<span class="spec-title">层高</span>
			      		          		<span class="spec-content">
			      		          		<#if houseType.floor??> 
			      		          			${houseType.floor }	
			      		          		<#else>
			      		          			暂无
			      		          		</#if>
			      		          		</span>
			      		          	</p>
			      		          	<p class="housetype-swiper-spec">
			      		          		<span class="spec-title">户型描述</span>
			      		          		<span class="spec-content">
			      		          		<#if houseType.describes??> 
			      		          			${houseType.describes }	
			      		          		<#else>
			      		          			暂无
			      		          		</#if>
			      		          		</span>
			      		          	</p>
			      		          </div>
			      		       </div>
		      		       </#list>
						</#list>
					 </#if>
	      		       </div>
	      		    </div>
	      		</div>
	      	</div>
	      </div>
	    </div>
	  </div>
    <script>
	    var galleryTop = new Swiper('.gallery-top', {
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        spaceBetween: 10,
	    });
	    var galleryThumbs = new Swiper('.gallery-thumbs', {
	    	nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        spaceBetween: 10,
	        centeredSlides: true,
	        slidesPerView: 'auto',
	        touchRatio: 0.2,
	        slideToClickedSlide: true
	    });
	    galleryTop.params.control = galleryThumbs;
	    galleryThumbs.params.control = galleryTop;  
        $(document).on("click",".typeListImg img",function(e) {
        	var index = $(this).index(".typeListImg img");
    	    $('#album_modal').modal();
    	    $('#album_modal').on('shown.bs.modal', function (e) {
    	      // do something...
    		    var modalGalleryTop = new Swiper('.modal-gallery-top', {
    		    	nextButton: ' .swiper-button-next.album-right',
    		        prevButton: '.swiper-button-prev.album-left',
    		        spaceBetween: 10,
    		        initialSlide :index,
    		    });
    		    modalGalleryTop.params.control = modalGalleryTop;
    	    })

        });
        function setStyle(_this){
        	$(_this).parent().siblings("li").removeClass("active");
        	$(_this).parent().addClass("active");
        }
    </script>
</html>