<#include "common/taglibs.ftl">
	<meta content="房屋过户,房产过户,继承,赠予,企业过户,网上签约,权证服务,夫妻更名,企业产权过户" name="keywords">
	<meta content="北京权威的房产权证网站,为您提供过户、办理房产证、房产信息变更、房产证增名、房产证减名、网上签约、自行成交等服务。" name="description">
	<title>${spu.name}-自交易-大搜家</title>
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
    <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	       		<h1>大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	        <span>自交易</span>
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
	         <li><a href="${ctx }/front/warrant/list">自交易列表</a>
		        <div class="progressTriangle">
		          <div> 
		          </div>
		        </div>
	        </li>
	        <li><a href="javascript:void(0)">自交易详情</a>
	        </li>
	      </ul>
	    </div>
	    <!-- 前端主体 -->
		<div class="dsj-main warrant_cen clearfix">		  	<!-- 主体banner -->
		  	<div class="dsj-main-banner clearfix">
		  		<div class="banner_left">
					<img src="${spu.img}?x-oss-process=image/resize,m_fixed,h_313,w_430">
		  		</div>
		  		<div class="banner_right">
	  				<!-- 主体标题 -->
				  	<h2>
				  		${spu.name }
				  	</h2>
	      			<p>${spu.content }</p>
	      			<div class="w_pace">价&nbsp&nbsp&nbsp&nbsp&nbsp格<strong>${spu.minPrice }</strong>元</div>
	      			<div>
	      			<!-- 表单选项 -->
			  		<form class="dsj_form" onsubmit="return false;">
			            <div class="dsj_form_line dsj_sort">
			            	<p class="pull-down-kuang">
			  					<span class="no_icon">服务类型</span>
								<select class="form-control" id="skuSelect">
								  <#if skuList??> 
							  		<#list skuList as sku> 
							  		    	 <option value="${sku.id}">${sku.typeName}</option>
							  		</#list>
							    </#if>
								</select>
			  				</p>
			  				<p class="pull-down-kuang">
			  					<span class="no_icon">服务区域</span>
			  					<span class="form-control form_control_box">北京</span>
								<select class="form-control" id="areaThreeCode">
								  <#if threeAreaList??> 
							  		<#list threeAreaList as area> 
							  		    	 <option value="${area.areaCode}">${area.name}</option>
							  		</#list>
							    </#if>
								</select>
			  				</p>
			  				<div>
			  					<button class="server_button" onclick="selectFwUser()">选择服务者</button>
			  				</div>
			            </div>
			  		</form>
			  		
			  		<form id="dsj_form" class="dsj_form" onsubmit="return false">
			  			<input type="hidden" name="spuId" value="${spu.id }">
			  			<input type="hidden" name="type" id="type" value="">
	  				</form>
	      			</div> 
		      	</div>	
		  	</div>
		  	<!-- 主体内容 -->
		  	<div class="warrant_content_list clearfix">
		  		<div class="warrant_list_left clearfix">
		  			<div class="list-group list_tab">
		  				<h4>自交易服务</h4>
						  <#if spuList??> 
					  		<#list spuList as spu> 
					  		    	<a href="${ctx }/front/warrant/detail?id=${spu.skuId }" class="list-group-item border-top">${spu_index+1 }. ${spu.name }<span class="item_price">${spu.minPrice }元</span></a>
					  		</#list>
					    </#if>
					</div>
		  			<!-- <div class="list-group list_tab">
		  				<h4>金融服务</h4>
					  <a href="javascript:void(0);" class="list-group-item">
					    1. 房屋抵押贷款
					    <span class="item_price"></span>
					  </a>
					  <a href="javascript:void(0);" class="list-group-item">2. 银行按揭贷款 
					  <span class="item_price"></span></a>
					</div> -->
		  		</div>
		  		<div class="warrant_list_right clearfix">
		  			<!--吸顶导航  -->
					<nav class="warrant_list_nav">
				        <div class="container-fluid">
				            <!-- Collect the nav links, forms, and other content for toggling -->
				            <div class="collapse navbar-collapse" id="navbar-example">
				                <ul class= "nav nav-tabs" role="tablist">
				                    <li class="active"><a href="#dsj">服务描述</a></li>
				                    <li><a href="#serve_safeguard">服务保障</a></li>
				                    <li><a href="#user_comment_top">用户评论(<#if commentMsg.count??>${commentMsg.count}<#else>0</#if>)</a></li>
				                </ul>
				                <a class="s_button" onclick="selectFwUser()">选择服务者</a>
				            </div><!-- /.navbar-collapse -->
				        </div><!-- /.container-fluid -->
				    </nav>
		  			<div id="serve_describe" class="list_kuang">
		  				<div id="serve_describe_top" class="placeholder_icon"></div>
		  				<img alt="" src="${spu.describes}">
		  			</div>
			  		<div id="serve_safeguard_top" class="list_kuang">
			  			<div id="serve_safeguard" class="placeholder_icon"></div>
			  			<img src="${spu.guarantee}?x-oss-process=image/resize,m_fixed,h_529,w_970">
			  		</div>
			  		<div id="user_comment" class="list_kuang warrant_comment clearfix">
			  			<div id="user_comment_top" class="placeholder_icon placeholder_dianping"></div>
			  			<div class="comment_nav">
			  				<a href="javascript:void(0);" onclick="checkComment()" class="active">全部评论(<#if commentMsg.count??>${commentMsg.count}<#else>0</#if>)</a>
			  				<a href="javascript:void(0);" onclick="checkComment(3)">好评(<#if commentMsg.haoPingCount??>${commentMsg.haoPingCount}<#else>0</#if>)</a>
			  				<a href="javascript:void(0);" onclick="checkComment(2)">中评(<#if commentMsg.zhongPingCount??>${commentMsg.zhongPingCount}<#else>0</#if>)</a>
			  				<a href="javascript:void(0);" onclick="checkComment(1)">差评(<#if commentMsg.chaPingCount??>${commentMsg.chaPingCount}<#else>0</#if>)</a>
		  				</div>
		  	 		<!-- 主体内容 -->
	      			<div class="newhouse_content" id="content_containers">
	      		
					</div>			
			  		</div>
		  		</div>
		  	</div>
		</div>
		<script id="dsj_row_template" type="text/x-handlebars-template">
      			<div class="comment_list">
		  					<div class="co_left">
		  						<div class="co_header">
			  						<ul class="evaluate_star">
										<li {{#compare score ">=" 1}} class="active" {{/compare}}></li>
			  							<li {{#compare score ">=" 2}} class="active" {{/compare}}></li>
			  							<li {{#compare score ">=" 3}} class="active" {{/compare}}></li>
			  							<li {{#compare score ">=" 4}} class="active" {{/compare}}></li>
			  							<li {{#compare score ">=" 5}} class="active" {{/compare}}></li>
			  						</ul>
			  						<div class="time_kuang">
			  							{{username}}
			  							<time>{{createTime}}</time>
			  						</div>
		  						</div>
		  						<p>{{content}}</p>
		  						<div class="labelling">
 								{{#each list}}
            						  <span class="color_1">{{lableList}}</span>
             					 {{/each}}
		      					</div>
		  					</div>
		  					<div class="co_right">
		  						<img src="{{avatar}}?x-oss-process=image/resize,m_fixed,h_60,w_60" width="60" height="60">
		  						<div class="d_name">
									<div class="co_name"><span>服务者：</span><span class="warr_name">{{realname}}</span></div>
									<p><span>接单数:{{#compare orderCount "!=" null}}{{orderCount}}{{else}}0{{/compare}}单</span>好评率:<strong>{{#compare haoPingLv "!=" null}}{{haoPingLv}}{{else}}0%{{/compare}}</strong>
									</p>
								</div>
		  					</div>
		  				</div>
    	</script>
	<script id="dsj_page_template" type="text/x-handlebars-template">
      <div class="row dsj_page_block" {{#compare totalPage "<=" 1}}style="display:none;"{{/compare}}>
        <div></div>
        <div class="col-sm-9">
          <nav aria-label="Page navigation">
            <ul class="pagination">
              <li>
                <a class="first" href="#" aria-label="first">
                  <span aria-hidden="true">首页</span>
                </a>
              </li>
              <li>
                <a class="prev" href="#" aria-label="Previous">
                  <span aria-hidden="true">上一页</span>
                </a>
              </li>
              {{#each list}}
              <li><a class="page_button" href="#">{{this}}</a></li>
              {{/each}}
              <li>
                <a class="next" href="#" aria-label="Next">
                  <span aria-hidden="true">下一页</span>
                </a>
              </li>
              <li>
                <a class="last" href="#" aria-label="last">
                  <span aria-hidden="true">尾页</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
        <div class="col-sm-1">
            <span class="yema">共<span>{{totalPage}}</span>页</span>
        </div>
       </div>
      </div>
    </script>
		<script src="${ctx }/static/front/js/handlebars.js"></script>
		<script src="${ctx }/static/front/js/handlebars-utils.js"></script>
		<script src="${ctx }/static/front/js/housepage.js"></script>
		<script src="${ctx }/static/front/js/formUtils.js"></script>
		<script src="${ctx }/static/front/js/bootstrap.js"></script>
    	<script type="text/javascript">
		$(".warrant_list_nav").on("click","li",function(){
			$(this).addClass("active").siblings().removeClass("active");
		});
		$(".comment_nav").on("click","a",function(){
			$(this).addClass("active").siblings().removeClass("active");
		});
		function selectFwUser(){
			location = _ctx+"/front/warrant/toFwUserList?skuId="+$("#skuSelect").val()+"&areaCodeThree="+$("#areaThreeCode").val();
		};
		var houseList;
	    $(document).ready(function(){
  		 var houseOption = {
	              container_id : "content_containers",
	              rowTemplate_id : "dsj_row_template",
	              pageTemplate_id : "dsj_page_template",
	              pagecount : "30",
	              url:_ctx+"/front/warrant/shopCommentList",
	            }
	            houseList = new $.fn.houseList(houseOption);
	            houseList._init();
         	
	    });
	    
		function checkComment(type){
	    	$("#type").val(type);
			houseList.searchAjax();
		};
		
		$(function(){  
			isPcOrOther('http://wap.dasoujia.com/mobile/views/quanzheng/detail.html?id='+$("#skuSelect").val());
			
		    var a = $('.warrant_list_nav'),  
		      b =a.offset();//返回或设置导航栏相对于文档的偏移(位置)  
		  //加个屏幕滚动事件，c是滚动条相当于文档最顶端的距离  
		    $(document).on('scroll',function(){  
		      var c = $(document).scrollTop();  
		  // 当滚动的屏幕距离大于等于导航栏本身离最顶端的距离时（判断条件）给它加样式（根据自己业务的条件加样式，一般如下）
		      if(b.top<=c){  
		        a.css({'position':'fixed','top':'0px'});
		        $(".s_button").show(); 
		        }else{  
		          a.css({'position':'relative','top':'0px'});
		          $(".s_button").hide();   
		        }  
		     });
		  }); 
		  $('body').scrollspy({ target: '#navbar-example' });
        	$('[data-spy="scroll"]').each(function () {
            var $spy = $(this).scrollspy('refresh');
        });
	</script>
