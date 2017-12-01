<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/album.css">
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
  	<div id="photoalbum">
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
	                  <input type="text" class="form-control" value="${keywords}" name="k" id="inputseek" placeholder="请输入楼盘名称或地址" autocomplete="off">
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
                <a href="javascript:void(0);">楼盘相册</a>
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
	            <a href="${ctx }/front/newHouse/house_type?id=${newHouseId}">楼盘户型
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/newHouse_agent?id=${newHouseId}">推荐经纪人
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="javascript:void(0);" class="active">楼盘相册
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
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
	        <!--  -->
	        <ul class="nav nav-pills">
			  <li role="presentation" class="active"><a onclick="setStyle(this)" href="javascript:void(0);">全部相册(${housePictureMap.totalCount })</a></li>
			  
			   <#if housePictureMap.pictureList??> 
			 	<#list housePictureMap.pictureList as picture> 
					  <li role="presentation"><a onclick="setStyle(this)" href="#photpType${picture_index }">${picture.name }(${picture.pictureList?size })</a></li>
				</#list>
			 </#if>
			</ul>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content">
		      	<#if housePictureMap.pictureList??> 
				 	<#list housePictureMap.pictureList as picture> 
					 	<div class="newhouse_content_list none_border" id="photpType${picture_index }">
			      			<h3>${picture.name }(${picture.pictureList?size })</h3>
								 <div class="house_recommend">
								 	<#list picture.pictureList as smallPicture> 
				      				<figure <#if smallPicture_index!=0 && (smallPicture_index+1)%4 == 0> style="margin-right:0" </#if>>
										<img src="${smallPicture.pictureUrl }" width="350" height="234" />
										<p>${smallPicture.describes }</p>
									</figure>
									</#list>
					      		</div>
				      	</div>
						  
					</#list>
				 </#if>
		      	
			</div>	
		</div>

	</div>
<div id="album_modal" class="album_modal single-ablum modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
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
	      		    	<#if housePictureMap.pictureList??> 
						 	<#list housePictureMap.pictureList as picture> 
						 		<#list picture.pictureList as smallPicture> 
				      				<div class="swiper-slide" style="width:1127px;height: 670px;">
				      		          <img src="${smallPicture.pictureUrl }">
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
    	// swiper
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
    	var modalGalleryTop = null;
        $(document).on("click",".newhouse_content img",function(e) {
        	var index = $(this).index(".newhouse_content img");
    	    $('#album_modal').modal();
    	    if(modalGalleryTop == null){
	    	    $('#album_modal').one('shown.bs.modal', function (e) {
	    		    modalGalleryTop = new Swiper('.modal-gallery-top', {
	    		    	nextButton: '.swiper-button-next.album-right',
	    		        prevButton: '.swiper-button-prev.album-left',
	    		        initialSlide :index,
	    		    });
	    		    modalGalleryTop.params.control = modalGalleryTop;
	    	    })
    	    }else {
    	    	modalGalleryTop.slideTo(index);
    	    }
        });
        function setStyle(_this){
        	$(_this).parent().siblings("li").removeClass("active");
        	$(_this).parent().addClass("active");
        }
    </script>
