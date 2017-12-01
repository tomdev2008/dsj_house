<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">
<meta name="keywords" content="新房经纪人,二手房经纪人,经纪人动态,经纪人推荐房源,二手房买卖">
<meta name="description" content="独立经纪人的个人店铺，联系电话400-898-6868转${agent.mobile}，汇集经纪人推荐的优质新房、优质二手房。">
<title>${agent.name}-${agent.name}经纪人主页-大搜家</title>
<#include "common/taglibs.ftl">
<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/dsj_agent.css">
<link rel="stylesheet" href="${ctx}/static/front/css/dsj_newhouse.css">
<link rel="stylesheet" href="${ctx }/static/front/css/album.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
   <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
   <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
<!-- 经纪人前端logo搜索框 -->
<div class="BHLogo">
	<div class="BHLogoLeft">

	    <h1><a href="${ctx }/"></a></h1>
	    <span>经纪人</span>
	</div>

	<div class="BHLogoRight">
	   
	    <div class="TextSeek">
	        <form class="form-inline">
	            <div class="form-group">
	                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	                <div class="input-group">
	                    <input type="text" id="agentSearch" class="form-control" placeholder="请输入区域，经纪人，公司">
	                </div>
	            </div>
            <button type="button" onclick="goAgent()" class="seek">

            </button>
	        </form>
    </div>
	    
    <div  class="MapFindHouse">
      <a href="${ctx }/map/newMap">地图找房</a>
    </div>
</div>
</div>
<!-- 进程 -->
<div class="PGress"> 
  <ul>
    <li><a href="${ctx }/">大搜家首页</a>
    <div class="progressTriangle">
      <div> 
      </div>
    </div>
    </li>
    <li><a href="${ctx }/front/agent">经纪人列表</a>
    	<div class="progressTriangle">
	      <div> 
	      </div>
	    </div>
    </li>
    <li>经纪人详情</li>
  </ul>
</div>
</div>
<div class="container" style="position: relative;">
  <div class="dsj_person_detail dsj_card">
    <div class="dsj_card_img">
      <img class="img-responsive person-border" src="${agent.avatarUrl}?x-oss-process=image/resize,m_lfit,h_220,w_220">
      <span class="dsj_badge">
        ${agent.auditName}
      </span>
    </div>
    <div class="dsj_card_title">
      <span>
        ${agent.name}
      </span>
    </div>
    <!--
    <div class="dsj_card_option">
    	<div class="option subcribe cancel"><span>关注他</span></div>
    </div>
    -->
    <#if follow==0>
    <div class="dsj_card_option">
    	<div class="option subcribe" onclick="follow(${agent.userId},${agent.id})"><span>关注TA</span></div>
    </div>
    </#if>

    <#if follow!=0>
      <div class="dsj_card_option">
        <div class="option subcribe already" onmouseover="$(this).html('取消关注')" onmouseout="$(this).html('已关注')" onclick="cancleFollow(${agent.userId},${agent.id})">
          <span>已关注</span>
        </div>
      </div>
    </#if>
    <div class="dsj_card_option">
      <div class="option consult" onclick="consult()">在线咨询</div>
    </div>
    <div class="dsj_card_content">
      <div class="dsj-person">
        <div class="agent_badge">
          <img class="dsj-person__badge" id="rankImg"><span class="dsj-person__title" id="rankName"></span>
        </div>
        <div class="dsj-person__content">
          <span class="dsj-person__item">综合得分：</span>
          <strong class="dsj-person__score" id="rankScore"></strong>分
        </div>
        <div class="dsj-person__content">
          <span class="dsj-person__item">获评次数：</span>
          <span class="dsj-person__num" id="totalCount">0次</span>
        </div>
        <h6 class="blue_label">服务态度</h6>
        <div class="dsj-person__content" id="_service">
          <span class="dsj-person__item margin_r20">好评0</span>
          <span class="dsj-person__item margin_r20">中评0</span>
          <span class="dsj-person__item margin_r20">差评0</span>
        </div>
        <h6 class="blue_label">专业服务</h6>
        <div class="dsj-person__content" id="_major">
          <span class="dsj-person__item margin_r20">好评0</span>
          <span class="dsj-person__item margin_r20">中评0</span>
          <span class="dsj-person__item margin_r20">差评0</span>
        </div>
      </div>
      <div class="table-responsive table-detail">
        <table class="table agent_detail">
          <tbody>
            <tr>
              <th scope="row">所属公司：</th>
              <td>${agent.companyName}</td>
            </tr>
            <tr>
              <th scope="row">主营小区：</th>
              <td>${agent.mainCommunity}</td>
            </tr>
            <tr>
              <th scope="row">从业年限：</th>
              <td>${agent.workyears}年</td>
            </tr>
            <tr>
              <th scope="row">擅长领域：</th>
              <td>${agent.skill}</td>
            </tr>
            <tr>
              <th scope="row">个人签名：</th>
              <td>${agent.signature}</td>
            </tr>

          </tbody>
        </table>
      </div>
      <div class="table-responsive">
        <table class="table prize">
          <tbody>
          		<tr>
					<#list agent.featureArray as f>
						<#if 0<f_index  && (f_index % 2)==0></tr><tr></#if>
						<td class="back_color0">${f}</td>
					</#list>
				</tr>
          </tbody>
        </table>
      </div>
    </div>
    
    <div class="dsj_400phone">
    	400-898-6868转<#if agent.mobile>${agent.mobile}</#if>
    </div>
    
    
  </div>
</div>
<#if agent.background!=null>
<div class="jumbotron dsj_banner" style="background:url(${agent.background}) no-repeat center;">
<#else>
<div class="jumbotron dsj_banner" style="background:url(${ctx }/static/front/img/default/agent_banner.jpg) no-repeat center;");>
</#if>

</div>
<nav class="navbar navbar-default dsj_static_navbar navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse row">
            <ul class="agent_tab nav navbar-nav dsj_agent col-xs-12" style="padding-left: 300px">
                <li class="active"><a onclick="replace('index',1,1)">主页</a></li>
                <li><a onclick="replace('new',1,1,1)">新房楼盘</a></li>
                <li><a onclick="replace('old',2,1,1)">二手房源</a></li>
             <!--   <li><a onclick="replace('rent',3,1)">租房房源</a></li> -->
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div id="bigContainer">
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
    
<script id="index_house" type="text/x-handlebars-template">
    <div class="container" style="padding-left: 300px;">
        <div class="agent_tab_content">
            <div class="row">
                <h4 class="dsj_title dsj_update margin-top40">重点推荐</h4>
            </div>
            <div class="row dsj_tuijian">
            <#list recommend as r>
                <div class="col-xs-4">
                    <#if r.type==1>
                        <a href="${ctx }/front/newHouse/index_detail?id=${r.houseId}">
                        <div class="dsj_img_container">
                            <img class="img-responsive" src="${r.pictureUrl}?x-oss-process=image/resize,m_lfit,h_282,w_190">
                        </div>
                        <a class="dsj_tuijian_link ellipsis_tuijian_15" href="${ctx }/front/newHouse/index_detail?id=${r.houseId}">${r.title}</a>
                        </a>
                    </#if>
                    <#if r.type==2>
                        <a href="${ctx }/oldmaster/${r.houseId}.html">
                        <div class="dsj_img_container">
                            <img class="img-responsive" src="${r.pictureUrl}?x-oss-process=image/resize,m_lfit,h_282,w_190">
                        </div>
                        <a class="dsj_tuijian_link ellipsis_tuijian_15" href="${ctx }/oldmaster/${r.houseId}.html">${r.title}</a>
                        </a>
                    </#if>
               <!--      <#if r.type==3>
                        <a href="${ctx }/rentHouseDetail/detail?id=${r.houseId}">
                        <div class="dsj_img_container">
                            <img class="img-responsive" src="${r.pictureUrl}">
                        </div>
                        </a>
                        <a class="dsj_tuijian_link ellipsis_tuijian_15" href="${ctx }/rentHouseDetail/detail?id=${r.houseId}">${r.title}</a>
                    </#if> -->
					<#if r.type==5>
                        <a href="${ctx }/front/warrant/detail?id=${r.houseId}">
                        <div class="dsj_img_container">
                            <img class="img-responsive" src="${r.pictureUrl}?x-oss-process=image/resize,m_lfit,h_282,w_190">
                        </div>
                        <a class="dsj_tuijian_link ellipsis_tuijian_15" href="${ctx }/oldmaster/${r.houseId}.html">${r.title}</a>
                        </a>
                    </#if>
                </div>
            </#list>
            </div>


            <div class="row">
            <h4 class="dsj_title dsj_update">最新动态</h4>
        </div>
        <div id="dsj_dialog_container" >
        </div>
         </div>
    </div>  
</script>
<script id="dsj_agent_news" type="text/x-handlebars-template">
  <div class="row dsj_update_detail dsj_news_main">
  
    <div class="col-xs-1">
    	{{#if avatarUrl}}
    		<img class="img-responsive" src="{{avatarUrl}}?x-oss-process=image/resize,m_mfit,h_60,w_60">
    	{{else}}
    		<img class="img-responsive" src="${ctx }/static/front/img/z44@3x.png">
    	{{/if}}
    	 {{#compare stick '==' 1 }}<span class="Stick_up">置顶</span>{{/compare}}
    </div>
    <div class="col-xs-11">
       <!--<p class="dsj_update_area">
       	主营小区：{{mainCommunity}}
       {{realname}}{{shortName}}{{#compare stick '==' 1 }}{{/compare}}
      </p>-->
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
            <div class="col-xs-4">
                <img class="img-responsive" src="{{this}}?x-oss-process=image/resize,m_mfit,h_123,w_123" data-src="{{this}}">
            </div>
        {{/each}}
      </div>
    {{/if}}
      <p class="dsj_update_time dsj_news_time clearfix">
        {{#timeSrt-T  createTime}}
        {{/timeSrt-T}}
        <span class="pull-right">
              <a class="dsj_comment"><span class="dsj_num">({{commentNum}})</span></a>
              <a class="dsj_thumbup {{#compare 1 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbup"  onclick="cllickLike(this,{{id}},1,1);" >({{likeNum}})</span></a>
              <a class="dsj_thumbdown {{#compare 2 '==' clicktype}}thum_Active{{/compare}}"><span class="dsj_num _thumbdown"  onclick="cllickLike(this,{{id}},1,2);">({{negativeNum}})</span></a>
        </span>
      </p>
      <div class="dsj_publish clearfix hide">
        <textarea class="form-control" onkeydown="checkMaxInput(this,100)"   placeholder="@{{realname}}" 
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
        <a class="toggle_reply" data-id="{{id}}" data-type-id="1" data-userId="{{createUser}}">全部回复 (<span class="dsj_num">{{commentNum}}</span>)</a>
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
    <div class="col-xs-11 border_bottom">
      <p class="dsj_reply_name">
        {{commentUserName}}
        {{#if replyUserName }}
            @<span class="at_name">{{replyUserName}}</span>
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
        <textarea class="form-control" onkeydown="checkMaxInput(this,100)"  placeholder="@{{#compare commentUserType '==' 5  }}{{#if commentNikename}}{{commentNikename}}{{else}}{{#dealPhoneNum commentUserName2}}{{/dealPhoneNum}}{{/if}}{{else}}{{#if commentUserName}}{{commentUserName}}{{else}}{{#dealPhoneNum commentPhone}}{{/dealPhoneNum}}{{/if}}{{/compare}}"
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
        <p class="dsj_page"><span class="dsj_current_page">{{currentPage}}</span>/{{totalPage}}</p>
        <ul class="pagination mypagination">
          <li><a class="prev" aria-label="Previous"><span class="glyphicon " aria-hidden="true"></span></a></li>
          <li><a class="next" aria-label="Next"><span class="glyphicon " aria-hidden="true"></span></a></li>
        </ul>
      </nav>
    </div>
  </div>
</script>	

<#include "agent/template.ftl">
<script src="${ctx }/static/front/js/handlebars.js"></script>
<script src="${ctx }/static/front/js/handlebars-utils.js"></script>
<script src="${ctx}/static/front/js/minganci.js"></script>
<script src="${ctx }/static/back/agent/agent.js"></script>
<script src="${ctx }/static/back/agent/agentcomment.js"></script>
<script src="${ctx }/static/front/js/agentIndex/agentIndex.js"></script>

<script type="text/javascript">
	isPcOrOther('http://wap.dasoujia.com/mobile/views/agent-detail/index.html?userId=${agent.userId}#/');
	var showboxfresh = false;
	document.title = '${agent.name}-${agent.name}经纪人主页-大搜家';
	$(function(){
    	replace('index');
	})
 		/*$(document).on("scroll",function() {
            if ($(window).scrollTop() > 160) {
              $(".dsj_person_detail.dsj_card").css({"position":"fixed","top": "0px"})
            }else{
              $(".dsj_person_detail.dsj_card").css({"position":"absolute","top": "40px"})
            }
          })*/

	var agentId = ${agent.userId};
	var id = ${agent.id};
	var loginStatus = ${loginStatus};
	
	var user="${Session.pc_user_session}";
	
	$(function(){
		$.ajax({
            type:"post",
            async: false,
            url:_ctx+"/front/agentIndex/grade",
            data:{
                userId:agentId
            },
            dataType:"json",
            success: function(result){
                if(result.status==200){
                    if(result.data!=0){
                        var count = 0;
                        for(var i=0;i<result.data.length;i++){
                            var item = result.data[i];
                            if(item.paragraph=="专业水平"){
                                $("#_major").empty();
                                $("#_major").html("<span class='dsj-person__item margin_r20'>好评"+parseInt((item.highCount*100)/item.totalCount)+"%</span>"+
                                        "<span class='dsj-person__item margin_r20'>中评"+parseInt((item.midCount*100)/item.totalCount)+"%</span>"+
                                        "<span class='dsj-person__item margin_r20'>差评"+parseInt((item.badCount*100)/item.totalCount)+"%</span>");
                            }
                            if(item.paragraph=="服务态度"){
                                $("#_service").empty();
                                $("#_service").html("<span class='dsj-person__item margin_r20'>好评"+parseInt((item.highCount*100)/item.totalCount)+"%</span>"+
                                        "<span class='dsj-person__item margin_r20'>中评"+parseInt((item.midCount*100)/item.totalCount)+"%</span>"+
                                        "<span class='dsj-person__item margin_r20'>差评"+parseInt((item.badCount*100)/item.totalCount)+"%</span>");
                            }
                            count=count+item.totalCount;

                        }
                        $("#totalCount").html(count+"次");
                    }
                }
            }
        });
        $.ajax({
            type:"post",
            async: false,
            url:_ctx+"/front/agentIndex/getRank",
            data:{
                userId:agentId
            },
            dataType:"json",
            success: function(result){
                if(result.status==200){
                	if(result.data!=null){
	                	$("#rankName").html(result.data.name);
	                    $("#rankScore").html(result.data.totalScore);
	                    $("#rankImg").attr("src",result.data.smallIcon);
                	}
                }
            }
        });
	})
	
	function consult(){
		openChatWindow("${username}", "${agent.name}",  "400-898-6868转${agent.mobile}", "${agent.avatarReUrl}")
	}
	

    //弹出框
    var box_affirm   = ".box_affirm",    //1
        box_defeated = ".box_defeated",  //2
        box_succeed  = ".box_succeed";   //3
    $(".box_close").on("click",function(event){
        $("#popup_box").hide();
        event.stopPropagation(); 
        if(showboxfresh){
        	location="${ctx }/front/agentIndex/info?userId="+agentId;
        }
    });
    $(".reset_botton").on("click",function(event){
        $("#popup_box").hide();
        event.stopPropagation(); 
        if(showboxfresh){
        	location="${ctx }/front/agentIndex/info?userId="+agentId;
        }
    });
    $(".submit_botton").on("click",function(event){
         $("#popup_box").hide();
         event.stopPropagation();
         if(showboxfresh){
        	location="${ctx }/front/agentIndex/info?userId="+agentId;
        }
    });

    
    //
    $(function () {
      	$('.subcribe').click(function(argument) {
      		$(this).toggleClass("loading");
      	})
        $('[data-toggle="popover"]').popover()

        $('.dsj_upload_button').click(function(argument) {
          $('.dsj_upload_pic').toggle();
        })

        
        var tabs = $("ul.agent_tab").find("a"),
            content = $(".agent_tab_content");
        // 遍历赋值index
        for(var i=0;i<tabs.length;i++){
            tabs[i].index=i;
        }
        for(var i=0;i<content.length;i++){
            content[i].index=i;
        }
         // lefttab事件委托
        $("ul.agent_tab").delegate("a","click",function(e){
            var  e = e || window.event,
                 index = e.target.index;
            $(this).parent().addClass("active").siblings().removeClass("active"); 
            $(content).eq(index).show().siblings().hide();
         }); 

    })
     function goAgent(){
     	var search = $('#agentSearch').val();
     	if(search==null ||search==''){
          	window.open("${ctx }/front/agent");
        }else{   
       		window.open("${ctx }/front/agent?k="+search);
        }
     }
    
    $(document).on("mouseover",".already",function(argument) {
      $(this).css({
        "background":"url() no-repeat 24px center #ececec",
        "padding-left": "0px"
      });
      $(this).html("取消关注")
    })
    $(document).on("mouseleave",".already",function(argument) {
      $(this).css({
        "background":"url(${ctx }/static/front/img/add_ok.png) no-repeat 24px center #ececec",
        "padding-left": "20px"
      });
      $(this).html("已关注")
    })
      
	//评论部分
    function indexInit(){
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
    		
    		var dialogOption = {
            container_id : "dsj_dialog_container",
            dialogTemplate_id : "dsj_agent_news",
            pageTemplate_id : "dsj_page_template",
            commentTemplate_id : "dsj_comment_template",
            agentId : agentId,
            url : _ctx
          }

        var dialogList = new $.fn.DialogList(dialogOption);
        dialogList._init();  		
    }
    
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