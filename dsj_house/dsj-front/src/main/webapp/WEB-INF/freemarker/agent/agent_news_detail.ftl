<#include "common/taglibs.ftl">
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/dsj_agent.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/dsj_newhouse.css" />
<link rel="stylesheet" href="${ctx }/static/front/css/album.css">
<div id="dsj">
    <!-- 经纪人前端logo搜索框 -->
    <div class="BHLogo">
        <div class="BHLogoLeft">
            <h1>大搜家</h1>
            <span>经纪人</span></div>
        <div class="BHLogoRight">
            <!-- 输入框搜索按钮 -->
            <div class="TextSeek">
                <form class="form-inline">
                    <div class="form-group">
                        <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="exampleInputAmount" placeholder="请输入地铁线附件的房子开始找房"></div>
                    </div>
                    <button type="submit" class="seek">
                        <!-- <span class="glyphicon glyphicon-search"></span> --></button>
                </form>
            </div>
            <!-- 地图找房 -->
            <div class="MapFindHouse">地图找房</div></div>
    </div>
    <!-- 进程 -->
    <div class="PGress">
        <ul>
            <li>
                <a href="${ctx}/">大搜家首页</a>
                <div class="progressTriangle">
                    <div></div>
                </div>
            </li>
            <li>
                <a href="${ctx}/front/agentIndex/info?userId=${userId}">经纪人首页</a>
                <div class="progressTriangle">
                    <div></div>
                </div>
            </li>
            <li>
                <a href="##">经纪人动态详情</a>
                <div class="progressTriangle">
                    <div></div>
                </div>
            </li>
        </ul>
    </div>
    <!-- 新房主体 -->
    <div class="dsj-main">
        <!-- 主体标题 -->
        <h2>经纪人动态详情</h2>
        
     <div class="container" style="padding-top: 20px;">
  		<!-- 主体内容 -->
        <div class="newhouse_content user_comment" id="dsj_container" >
            
        </div>
        <input type="hidden" id="newsId" value="${newsId}" />
        <input type="hidden" id="userId" value="${userId}" />
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
	      {{#if avatarUrl}}
	    		<img class="img-responsive" src="{{avatarUrl}}?x-oss-process=image/resize,m_mfit,h_60,w_60">
	    	{{else}}
	    		<img class="img-responsive" src="${ctx }/static/front/img/z44@3x.png">
	    	{{/if}}
	    </div>
        <div class="col-xs-11">
          <p class="dsj_update_area" >
			{{name}}
			<div class="online-consult agent-consult" onclick="openChatWindow('{{username}}','{{realname}}','400-898-6868转{{tellPhone}}','{{avatarUrl}}')">在线咨询</div>
			{{#timeSrt-T  createTime}}
			{{/timeSrt-T}}
		  </p>
          <p class="dsj_update_content" data-content="{{content}}" data-mark="more">
			{{#contentLimits content 150 }}
				{{#convertHtml content}}{{/convertHtml}}
			{{/contentLimits}}
          </p>
			{{#contentLengthLimits content 150 }}
				<span class="dsj_update_content_more">展开全文</span>
			{{/contentLengthLimits}}
		{{#if pictures}}
          <div class="dsj_update_pic row">
			{{#each picturesArr}}
        		<div class="col-xs-2">
              		<img class="img-responsive" style="height:135px;" src="{{this}}?x-oss-process=image/resize,m_mfit,h_123,w_123" data-src="{{this}}">
            	</div>
       		{{/each}}
          </div>
		{{/if}}
          <p class="dsj_update_time dsj_news_time clearfix">
            <span class="pull-right">
              <a class="dsj_comment"><span class="dsj_num">({{commentNum}})</span></a>
              <a class="dsj_thumbup {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbup"   onclick="cllickLike(this,{{id}},1,1);" >({{likeNum}})</span></a>
              <a class="dsj_thumbdown {{#compare 2 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbdown"   onclick="cllickLike(this,{{id}},1,2);">({{negativeNum}})</span></a>
        	</span>
          </p>
          <div class="dsj_publish clearfix hide">
            <textarea class="form-control" onkeydown="checkMaxInput(this,100)"  placeholder="@{{name}}"
            	onkeyup="checkMaxInput(this,100)" onfocus="checkMaxInput(this,100)" 
            	onblur="checkMaxInput(this,100);" rows="3"></textarea>
            <p>
              <div class="pull-right">
                	还能再输入<span class="textNum">100</span>字
                <button type="button" onclick="publishComment(this,{{id}},0);" class="primary">
                	  发布
                </button>
              </div>
            </p>
          </div>
          <p class="dsj_all_reply">
			<a class="toggle_reply" data-id="{{id}}" data-type-id="1"  >全部回复 (<span class="dsj_num">{{commentNum}}</span>)</a>
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
            {{commentUserName}}
			{{#if replyUserName }}
				@<span class="at_name">{{replyUserName}}</span>
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
          		<a class="dsj_thumbup thumbup_none {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbup "  onclick="cllickLike(this,{{id}},2,1);">({{likeNum}})</span></a>
      		 </span>
          </p>
          <div class="dsj_publish clearfix hide">
            <textarea class="form-control" onkeydown="checkMaxInput(this,100)"  placeholder="@ {{commentUserName}}"
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
        	查看更多
        	</div>
        </div>
      </div>
    </script>
    
    <script src="${ctx}/static/front/js/handlebars.js"></script>
    <script src="${ctx}/static/front/js/handlebars-utils.js"></script>
    <script src="${ctx}/static/front/js/minganci.js"></script>
    <script src="${ctx}/static/back/agent/agent_news_detail.js"></script>
    <script src="${ctx}/static/back/agent/agent_news_detail2.js"></script>
    
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

        var dialogOption = {
          container_id : "dsj_container",
          dialogTemplate_id : "dsj_agent_news",
          pageTemplate_id : "dsj_page_template",
          commentTemplate_id : "dsj_comment_template",
          newsId : $("#newsId").val(),
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
			//str += '<div class="swiper-slide" style="width:1127px;height: 670px;"><img src="'+imgList[i].src+'"></div>';
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