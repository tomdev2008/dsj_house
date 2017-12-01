<#include "common/taglibs.ftl">
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/dsj_agent.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/dsj_newhouse.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/album.css" /> 
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
            <!--  <a href="${ctx }/map/newMap">地图找房</a> -->
            <div class="MapFindHouse"> <a href="${ctx }/map/newMap">地图找房</a></div>
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
	         	<a href="${ctx}/front/newHouse/house_comment?id=${newHouseId}&type=3">楼盘点评</a>
		         <div class="progressTriangle">
		        </div>
	        </li>
	         <li>
                <a href="javascript:void(0);">点评详情</a>
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
	              <span class="activeTriangle">
	              </span>
	            </a>
	           <a href="${ctx }/front/newHouse/house_picture?id=${newHouseId}">楼盘相册
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="${ctx }/front/newHouse/house_comment?id=${newHouseId}&type=3" class="active">楼盘点评
	              <span class="activeTriangle" style="border-top:10px solid #2775e9;">
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
        
     <div class="container comment_details">
  		<!-- 主体内容 -->
        <div class="newhouse_content user_comment" id="dsj_container" >
            
        </div>
        <input type="hidden" id="commentId" value="${commentId}" />
    </div>
    
    <!-- swiper -->
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
	      		</div>
	      	</div>
	      </div>
	    </div>
	  </div>        
	</div> 
    
</div>    
	
	<script id="dsj_agent_news" type="text/x-handlebars-template">
      <div class="row dsj_update_detail dsj_news_main">
        <div class="col-xs-1">
          	{{#if commentUserAvatar}}
	    		<img class="img-responsive" src="{{commentUserAvatar}}?x-oss-process=image/resize,m_mfit,h_60,w_60">
	    	{{else}}
	    		<img class="img-responsive" src="${ctx }/static/front/img/z44@3x.png">
	    	{{/if}}
        </div>
        <div class="col-xs-11">
          <p class="dsj_update_title">
           {{#compare commentUserType '==' 5  }}
          		{{#if commentNikename}}
          			{{commentNikename}}
          		{{else}}
          			{{#dealPhoneNum commentUserName2}}
          			{{/dealPhoneNum}}
          		{{/if}}
          	{{else}}
          		{{#if commentUserName}}
          			{{commentUserName}}
          		{{else}}
          			{{#dealPhoneNum commentPhone}}
          			{{/dealPhoneNum}}
          		{{/if}}
          		{{#compare commentUserType '==' 2  }}
          			<div class="online-consult agent-consult" onclick="openChatWindow('{{commentUserName2}}','{{commentUserName}}','400-898-6868转{{commentPhone}}','{{commentUserAvatar}}')">在线咨询</div>
          		{{/compare}}
          	{{/compare}}
          </p>
          <p class="dsj_update_content" data-content="{{content}}" data-mark="more">
			{{#contentLimits content 150 }}
				{{#convertHtml content}}{{/convertHtml}}
			{{/contentLimits}}
          </p>
			{{#contentLengthLimits content 150 }}
				<span class="dsj_update_content_more">展开全文</span>
			{{/contentLengthLimits}}
		{{#if picture}}
          <div class="dsj_update_pic row">
			{{#each picturesArr}}
        		<div class="col-xs-2">
              		<img class="img-responsive" style="height:123px;" src="{{this}}?x-oss-process=image/resize,m_mfit,h_123,w_123" data-src="{{this}}">
            	</div>
       		{{/each}}
          </div>
		{{/if}}
          <p class="dsj_update_time dsj_news_time clearfix">
			{{#timeSrt-T  createTime}}
			{{/timeSrt-T}}
            <span class="pull-right">
              <a class="dsj_comment"><span class=" dsj_num">({{commentNum}})</span></a>
              <a class="dsj_thumbup {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"  ><span   class=" dsj_num _thumbup" onclick="cllickLike(this,{{id}},2,1);" >({{likeNum}})</span></a>
              <a class="dsj_thumbdown {{#compare 2 '==' clicktype}}thum_Active{{/compare}}" ><span  class=" dsj_num _thumbdown"  onclick="cllickLike(this,{{id}},2,1);">({{negativeNum}})</span></a>
            </span>
          </p>
          <div class="dsj_publish clearfix hide">
            <textarea class="form-control" onkeydown="checkMaxInput(this,100)"  placeholder="@{{#compare commentUserType '==' 5  }}{{#if commentNikename}}{{commentNikename}}{{else}}{{#dealPhoneNum commentUserName2}}{{/dealPhoneNum}}{{/if}}{{else}}{{#if commentUserName}}{{commentUserName}}{{else}}{{#dealPhoneNum commentPhone}}{{/dealPhoneNum}}{{/if}}{{/compare}}" 
            	onkeyup="checkMaxInput(this,100)" onfocus="checkMaxInput(this,100)" 
            	onblur="checkMaxInput(this,100);" rows="3"></textarea>
            <p>
              <div class="pull-right">
                	还能再输入<span class="textNum">100</span>字
                <button type="button" onclick="publishComment(this,{{id}},0);" class="primary">
                  <span class="glyphicon" aria-hidden="true"></span>
                	 发布
                </button>
              </div>
            </p>
          </div>
          <p class="dsj_all_reply">
			<a class="toggle_reply" data-id="{{id}}" data-type-id="2" >全部回复 (<span class="dsj_num">{{commentNum}}</span>)</a>
		  </p>
          <div class="dsj_reply hide">
          </div>
        </div>
      </div>
    </script>
    <script id="dsj_comment_template" type="text/x-handlebars-template">
      <div class="row dsj_update_detail">
        <div class="col-xs-1">
          	{{#if commentUserAvatar}}
	    		<img class="img-responsive" src="{{commentUserAvatar}}">
	    	{{else}}
	    		<img class="img-responsive" src="${ctx }/static/front/img/z44@3x.png">
	    	{{/if}}
        </div>
        <div class="col-xs-11">
          <p class="dsj_reply_name">
            {{#compare commentUserType '==' 5  }}
          		{{#if commentNikename}}
          			{{commentNikename}}
          		{{else}}
          			{{#dealPhoneNum commentUserName2}}
          			{{/dealPhoneNum}}
          		{{/if}}
          	{{else}}
          		{{#if commentUserName}}
          			{{commentUserName}}
          		{{else}}
          			{{#dealPhoneNum commentPhone}}
          			{{/dealPhoneNum}}
          		{{/if}}
          	{{/compare}}
			{{#if replyUserName }}
				@<span class="at_name">
					{{#compare replyUserType '==' 5 }}
		          		{{#if replyNikename}}
		          			{{replyNikename}}
		          		{{else}}
		          			{{#dealPhoneNum replyUserName2}}
          					{{/dealPhoneNum}}
		          		{{/if}}
		          	{{else}}
		          		{{#if replyUserName}}
		          			{{replyUserName}}
		          		{{else}}
		          			{{#dealPhoneNum commentPhone}}
          					{{/dealPhoneNum}}
		          		{{/if}}
		          	{{/compare}}
				</span>
			{{/if}}
          </p>
          <p class="dsj_reply_content">
            	{{#convertHtml content}}{{/convertHtml}}
		<span class="dsj_update_content_more"></span>
          </p>
          <p class="dsj_update_time clearfix">
            {{#timeSrt-T  createTime}}
			{{/timeSrt-T}}
        	<span class="pull-right">
              <a class="dsj_comment comment_none"><span>回复</span></a>
              <a class="dsj_thumbup thumbup_none {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbup"   onclick="cllickLike(this,{{id}},2,1);">({{likeNum}})</span></a>
            </span>
          </p>
          <div class="dsj_publish clearfix hide">
            <textarea class="form-control" onkeydown="checkMaxInput(this,100)"  placeholder="@{{#compare commentUserType '==' 5  }}{{#if commentNikename}}{{commentNikename}}{{else}}{{#dealPhoneNum commentUserName2}}{{/dealPhoneNum}}{{/if}}{{else}}{{#if commentUserName}}{{commentUserName}}{{else}}{{#dealPhoneNum commentPhone}}{{/dealPhoneNum}}{{/if}}{{/compare}}"
            	onkeyup="checkMaxInput(this,100)" onfocus="checkMaxInput(this,100)" 
            	onblur="checkMaxInput(this,100);" rows="3" ></textarea>
            <p>
              <div class="pull-right">
                	还能再输入<span class="textNum">100</span>字
                <button type="button" onclick="publishComment(this,{{objectId}},{{commentUser}});" class="primary">
                 		发布
                </button>
              </div>
            </p>
          </div>
        </div>
      </div>
    </script>
    <script id="dsj_page_template" type="text/x-handlebars-template">
      <div class="row">
        <div id="dsj_list_page" class="col-xs-12" style="height: 40px;cursor: pointer;">
        	<div class="load_more text-center">
        	加载更多
        	</div>
        </div>
      </div>
    </script>
    
    <script src="${ctx}/static/front/js/handlebars.js"></script>
    <script src="${ctx}/static/front/js/handlebars-utils.js"></script>
    <script src="${ctx}/static/front/js/minganci.js"></script>
    <script src="${ctx}/static/back/newHouse/newHouse_comment_detail.js"></script>
    <script src="${ctx}/static/back/newHouse/newHouse_comment_detail2.js"></script>
    
    <script type="text/javascript">
		
	$(function () {
      
		$(document).on("click",".dsj_update_content_more",function(argument) {
  			if( "more" == $(this).siblings(".dsj_update_content").data("mark")){
  				$(this).siblings(".dsj_update_content").html($(this).siblings(".dsj_update_content").attr("data-content"));
  				$(this).siblings(".dsj_update_content").data("mark","less");
 	  			$(this).text("收起");
  			}else if( "less" == $(this).siblings(".dsj_update_content").data("mark")){
  				$(this).siblings(".dsj_update_content").html($(this).siblings(".dsj_update_content").attr("data-content").substring(0,150)+"...");
  				$(this).siblings(".dsj_update_content").data("mark","more");
 	  			$(this).text("展开全文");
  			}
  		})
  		
  		$('[data-toggle="popover"]').popover()
  		$('.dsj_upload_button').click(function(argument) {
			$('.dsj_upload_pic').toggle();
        })
        $('.dsj_upload_pic').toggle();

        var dialogOption = {
          container_id : "dsj_container",
          dialogTemplate_id : "dsj_agent_news",
          pageTemplate_id : "dsj_page_template",
          commentTemplate_id : "dsj_comment_template",
          commentId : $("#commentId").val(),
          url : _ctx
        }

        var dialogList = new $.fn.CommentDetailList(dialogOption);
        dialogList._init();
	})
	
	// swiper
	    $(document).on("click",".dsj_update_pic img",function(e) {
			var index = $(this).parent().index();
			var imgList = $(this).closest(".dsj_update_pic").find("img");
			var str = '<div class="swiper-wrapper">';
			for (var i = 0; i < imgList.length; i++) {
				str += '<div class="swiper-slide" style="width:1127px;height: 670px;"><img src="'+imgList[i].getAttribute("data-src")+'"></div>';
			}
			str += "</div>";
			$(".swiper-container").html(str);
		    $('#album_modal').modal();	
		    $('#album_modal').one('shown.bs.modal', function (e) {
		      // do something...
			    var modalGalleryTop = new Swiper('.modal-gallery-top', {
			    	nextButton: ' .swiper-button-next.album-right',
			        prevButton: '.swiper-button-prev.album-left',
			        spaceBetween: 10,
			    });
			    modalGalleryTop.params.control = modalGalleryTop;
				modalGalleryTop.slideTo(index);
		    })	
		});
    </script>