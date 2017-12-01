<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">
<title>大搜家</title>
<#include "common/taglibs.ftl">
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/dsj_agent.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/dsj_newhouse.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/album.css" /> 
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
   <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
   <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
</head>
<body>
    <!-- 经纪人前端logo搜索框 -->
    <div class="BHLogo">
        <div class="BHLogoLeft">
           <h1>大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
           <span>新房</span></div>
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
            <div class="MapFindHouse"> <a href="${ctx }/map/newMap">地图找房</a></div></div>
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
                <a href="javascript:void(0);">楼盘点评</a>
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
	            <a href="javascript:void(0);" class="active">楼盘点评
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
        
     <div class="container" style="padding-top: 20px;">
      <div class="modal fade user-comment-modal" id="myModal" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" onclick="closewindow(this)" data-dismiss="modal" aria-label="Close"><span class="cloed" aria-hidden="true"></span></button>
		        <h4 class="modal-title bold">我要点评</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="clearfix user-comment-modal__reply">
			      	<span class="user-comment-modal__title">点评</span>
			      	<textarea id="newsContent" class="user-comment-modal__content" onkeydown="checkMaxInputDP(this,500)"  
		            	onkeyup="checkMaxInputDP(this,500)" onfocus="checkMaxInputDP(this,500)" 
		            	onblur="checkMaxInputDP(this,500);" ></textarea>
		      		<span class="user-comment-modal__num">还能再输入<span class="textNum">500</span>字</span>
		      	</div>
		      	<div class="clearfix user-comment-modal__reply-pic">
			      	<span class="user-comment-modal__title">添加图片</span><span class="user-comment-modal__info">添加图片  (最多可添加9张图片)</span>
		      		<div class="user-comment-modal__pics">
		      			<!--<div class="user-comment-modal__pics-add"></div>-->
		      			<div id="wrapper1" class="dsj_upload clearfix">
				          <div id="filePicker1"  style="display:none"></div>
				          <div class="col-xs-4 add_pic_btn">
			                <div class="dsj_source_img agent_upload">
			                  <div class="add-more">
			                    <span>+</span>
			                  </div>
			                </div>
			              </div>
				        </div>
		      		</div>
		      	</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary dsj_button" onclick="publishNews(this);" data-dismiss="modal">提交</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<script id="dsj_uploader_template" type="text/x-handlebars-template">
			{{#each this}}
				<div class="col-xs-4">
					<div class="dsj_source_img agent_upload">
						<div class="delete-pic" onclick="removePic(this)">
							<span>&times;<input type="hidden" value=""></span>
						</div>
						<img class="img-responsive news_pic_upload" src="{{this._raw}}">
					</div>
				</div>
			{{/each}}
		</script>
		
		<div class="dsj-comment-tab">
			<#if type == 3 >
				<a class="dsj-comment-tab__item active" 
					href="${ctx}/front/newHouse/house_comment?id=${houseId}&type=3">经纪人点评(${countJjr}) </a>
  				<a class="dsj-comment-tab__item" 
  					href="${ctx}/front/newHouse/house_comment?id=${houseId}&type=4">用户点评(${countPtyh})</a>
			</#if>
			<#if type == 4 >
				<a class="dsj-comment-tab__item" 
					href="${ctx}/front/newHouse/house_comment?id=${houseId}&type=3">经纪人点评(${countJjr}) </a>
  				<a class="dsj-comment-tab__item active" 
  					href="${ctx}/front/newHouse/house_comment?id=${houseId}&type=4">用户点评(${countPtyh})</a>
			</#if>
			<button class="btn btn-success user-comment-button" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>我要点评</button>
  		</div>
  		        
  		<!-- 主体内容 -->
        <div class="newhouse_content user_comment" id="dsj_container" >
            
        </div>
        <input type="hidden" id="houseId" value="${houseId}" />
        <input type="hidden" id="type" value="${type}" />
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
          <div class="dsj_update_title">
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
          				<div class="online-consult agent-consult" onclick="openChatWindow('{{commentUserName2}}','{{commentUserName}}','400-898-6868转{{mobile}}','{{commentUserAvatar}}')">在线咨询</div>
          			{{/compare}}
          		{{/compare}}
          		<!--<div class="online-consult" onclick="openChatWindow('${agent.userName }', '${agent.realName }', '400-898-6868转${agent.mobile}', '${agent.imgUrl }')">在线咨询</div> -->
          </div>
          	
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
              <a class="dsj_comment"><span class="dsj_num">({{commentNum}})</span></a>
              <a class="dsj_thumbup {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbup"  onclick="cllickLike(this,{{id}},2,1);" >({{likeNum}})</span></a>
              <a class="dsj_thumbdown {{#compare 2 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbdown"  onclick="cllickLike(this,{{id}},2,2);">({{negativeNum}})</span></a>
            </span>
          </p>
          <div class="dsj_publish clearfix hide">
            	<textarea class="form-control area_text" onkeydown="checkMaxInput(this,100)"   placeholder="@{{#compare commentUserType '==' 5  }}{{#if commentNikename}}{{commentNikename}}{{else}}{{#dealPhoneNum commentUserName2}}{{/dealPhoneNum}}{{/if}}{{else}}{{#if commentUserName}}{{commentUserName}}{{else}}{{#dealPhoneNum commentPhone}}{{/dealPhoneNum}}{{/if}}{{/compare}}"    
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
          <p class="dsj_all_reply hide">
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
			{{#if replyUser }}
				@<span class="at_name">
					{{#compare replyUserType '==' 5  }}
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
          </p>
          <p class="dsj_update_time clearfix">
            {{#timeSrt-T  createTime}}
			{{/timeSrt-T}}
            <span class="pull-right">
              <a class="dsj_comment comment_none"><span>回复</span></a>
              <a class="dsj_thumbup thumbup_none {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbup"  onclick="cllickLike(this,{{id}},2,1);">({{likeNum}})</span></a>
            </span>
          </p>
          <div class="dsj_publish clearfix hide">
            <textarea class="form-control" onkeydown="checkMaxInput(this,100)"   placeholder="@{{#compare commentUserType '==' 5  }}{{#if commentNikename}}{{commentNikename}}{{else}}{{#dealPhoneNum commentUserName2}}{{/dealPhoneNum}}{{/if}}{{else}}{{#if commentUserName}}{{commentUserName}}{{else}}{{#dealPhoneNum commentPhone}}{{/dealPhoneNum}}{{/if}}{{/compare}}"
            	onkeyup="checkMaxInput(this,100)" onfocus="checkMaxInput(this,100)" 
            	onblur="checkMaxInput(this,100);" rows="3" ></textarea>
            <p>
              <div class="pull-right">
                	还能再输入<span class="textNum">100</span>字
                <button type="button" onclick="publishComment(this,{{objectId}},{{commentUser}});" class="primary">
                  <span class="glyphicon" aria-hidden="true"></span>
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
        <div id="dsj_list_page" class="col-xs-12">
          <nav aria-label="page" class="pull-right">
            <p class="dsj_page">共有{{totalData}}条评论   <span class="dsj_current_page">{{currentPage}}</span>/{{totalPage}}</p>
            <ul class="pagination  mypagination">
              <li><a class="prev" aria-label="Previous"></a></li>
              <li><a class="next" aria-label="Next"></a></li>
            </ul>
          </nav>
        </div>
      </div>
    </script>
    
    <script src="${ctx}/static/front/js/handlebars.js"></script>
    <script src="${ctx}/static/front/js/handlebars-utils.js"></script>
    <script src="${ctx}/static/back/js/webuploader.js"></script>
    <script src="${ctx}/static/front/js/minganci.js"></script>
    <script src="${ctx}/static/back/newHouse/upload_comment_pic.js"></script>
    <script src="${ctx}/static/back/newHouse/newHouse_comment.js"></script>
    <script src="${ctx}/static/back/newHouse/newHouse_comment2.js"></script>
    <script type="text/javascript">
		
	$(function () {
	
		$(document).on("click",".user-comment-button",function(argument) {
			if (user == ''){
				show_box(7);
			}else{
				$("#myModal").modal({
					backdrop: 'static',
				    keyboard: false,
				    show: true
			    })
			}
  		})
		
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
  		
  		uploadImageNews("#wrapper1","filePicker1");
  		
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
          house_id : $("#houseId").val(),
          object_type : $("#type").val(),
          url : _ctx
        }

        var dialogList = new $.fn.DialogList(dialogOption);
        dialogList._init();
      });
      
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
  </body>
</html>