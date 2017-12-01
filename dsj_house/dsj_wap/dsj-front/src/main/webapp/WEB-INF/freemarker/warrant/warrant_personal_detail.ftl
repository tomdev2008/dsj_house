<#include "common/taglibs.ftl">
    <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
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
	        <li><a href="javascript:void(0);">自交易代办人详情</a>
	        </li>
	      </ul>
	    </div>
	    <!-- 前端主体 -->
	  	<div class="dsj-main warrant_cen clearfix">
		  	<!-- 主体内容 -->
		  	<div class="personal_index">
		  		<div class="personal_list_left clearfix">
		  			<ul>
		  				<li class="_personal_header">
		  				    <img src="${property.avatarUrl}?x-oss-process=image/resize,m_fixed,h_200,w_200" height="200" width="200">
		  				    <h3>${property.realName }</h3>
		  				    <p>好评率：<span><#if fwuserComment.haoPingLv??>${fwuserComment.haoPingLv}<#else>0%</#if></span></p>
		  				    <p>接单数：<span>${property.orderCount}</span></p>
		  				    <div class="online-consult" onclick="openChatWindow('${property.userName }', '${property.realName }', '${property.phone }', '${property.avatarReUrl }')">咨询TA</div>
		  				</li>
		  				<li class="_their">
		  					<p>所属公司：<span>${property.companyName }</span></p>
		  					<p>所属区域：<span>${property.areaName }</span></p>
		  				</li>
		  				<li >
		  					 <p class="phone_label">${property.phone }</p>
		  				</li>
		  			</ul>
		  		</div>
		  		<div class="personal_list_right clearfix">
		  			<!-- 提供的服务 -->
			  		<div class="flow_center">
				 		<div class="house_type_tittle">
					    	<h2>提供的服务</h2>
					    </div>
					    <div class="warrant_form_kuang warrant_evaluate_kuang">
					    	<!-- form -->
					          <form  onsubmit="return false">
					          		<div class="f_cendiv">
					          			<span>服&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp务:</span>
										<div class="select_kind">
										<#if fwSpuList??> 
											<#list fwSpuList as fwSpu>
												<label onclick="setShopMsg(${fwSpu.minPrice},${fwSpu.skuId })"<#if fwSpu.id==spuId> class="label_active"</#if>> 
													<input type="checkbox" name="inlineRadioOptions">${fwSpu.name }
												</label> 
											</#list>
										 </#if>
							            </div>
					          		</div>
					          		<div class="w_pace">价&nbsp&nbsp&nbsp&nbsp&nbsp格:<strong id="price"></strong>元</div>
					          		<div class="dsj_form_line dsj_sort">
						  				<p class="pull-down-kuang">
						  					<span class="no_icon">服务区域:</span>
						  					<select class="form-control form_control_box">
						  						<option>北京</option>
						  					</select>
											<select class="form-control" id="areaCodeThree">
											<#if threeAreaList??> 
											  <#list threeAreaList as area>
											  	<option value="${area.areaCode }" <#if areaCodeThree == area.areaCode> selected='selected'</#if>>${area.name}</option>
											  </#list>
											  </#if>
											</select>
						  				</p>
						            </div>
					          		<div class="btn_kuang">
						            	<button type="button" class="btn btn-primary pay_btn" onclick="orderComfirm()">立即支付</button>
					          		</div>
					          </form>
					          <form id="dsj_form" class="dsj_form" onsubmit="return false">
						  			<input type="hidden" name="fwUserId" value="${id }">
						  			<input type="hidden" name="type" id="type" value="">
				  				</form>
				  				<input type="hidden" id="skuId" value="">
					    </div>
					    <div class="house_type_tittle">
						    <h2>用户评论</h2>
						</div>
						<div class="row reputation_kuang">
						  	<div class="col-md-6 kuang_1">
						  		<div class="percentage">
						  			<h4>
						  				<#if fwuserComment.haoPingLv??>${fwuserComment.haoPingLv}<#else>0%</#if>
						  			</h4>
						  			<p>好评率</p>
						  		</div>
						  		<ul class="degree">
									<li>
										<span>好评率</span><div class="thread_kuang good_icon"><p></p></div><div class="percentum1 percentum"><#if fwuserComment.haoPingLv??>${fwuserComment.haoPingLv}<#else>0%</#if></div>
									</li>
									<li><span>中评率</span><div class="thread_kuang well_icon"><p></p></div><div class="percentum"><#if fwuserComment.zhongPingLv??>${fwuserComment.zhongPingLv}<#else>0%</#if></div></li>
									<li><span>差评率</span><div class="thread_kuang bad_icon" ><p></p></div><div class="percentum"><#if fwuserComment.chaPingLv??>${fwuserComment.chaPingLv}<#else>0%</#if></div></li>
						  		</ul>
						  	</div>
						  	<div class="col-md-6 kuang_2">
						  		<ul class="comment_tags">
							  		<#if FwuserComList??>
							  			<#list FwuserComList as fc>
							  				<#if fc.evaluateNum=="1">
									  			<li>服务专业(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="2">
									  			<li>经验富足(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="3">
									  			<li>流程清晰(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="4">
									  			<li>踏实稳重(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="5">
									  			<li>认真严谨(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="6">
									  			<li>礼貌待客(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="7">
									  			<li>诚信正直(<span>${fc.count}</span>)</li>
							  				</#if>
							  				<#if fc.evaluateNum=="8">
									  			<li>办事高效(<span>${fc.count}</span>)</li>
							  				</#if>
							  			</#list>
							  		</#if>
							  	</ul>
						  	</div>
						  	<div class="client_impression">
						  		客户印象
						  	</div>
						</div>
						<!-- 用户评论 -->
				  		<div id="user_comment" class="list_kuang warrant_comment clearfix">
				  			<div class="comment_nav">
				  				<a href="javascript:void(0);" onclick="checkComment()" class="active">全部评论(<#if fwuserComment.count??>${fwuserComment.count}<#else>0</#if>)</a>
			  				<a href="javascript:void(0);" onclick="checkComment(3)">好评(<#if fwuserComment.haoPingCount??>${fwuserComment.haoPingCount}<#else>0</#if>)</a>
			  				<a href="javascript:void(0);" onclick="checkComment(2)">中评(<#if fwuserComment.zhongPingCount??>${fwuserComment.zhongPingCount}<#else>0</#if>)</a>
			  				<a href="javascript:void(0);" onclick="checkComment(1)">差评(<#if fwuserComment.chaPingCount??>${fwuserComment.chaPingCount}<#else>0</#if>)</a>
			  				</div>
			  				 <!-- 主体内容 -->
				      	<div class="newhouse_content" id="content_containers">
				      		
						</div>	
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
		  				</div>
    	</script>
	<script id="dsj_page_template" type="text/x-handlebars-template">
      <div class="row dsj_page_block">
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
            <span class="all_num">共<span>{{totalPage}}</span>页</span>
        </div>
       </div>
      </div>
    </script>
    <script src="${ctx }/static/front/js/handlebars.js"></script>
		<script src="${ctx }/static/front/js/handlebars-utils.js"></script>
		<script src="${ctx }/static/front/js/housepage.js"></script>
		<script src="${ctx }/static/front/js/formUtils.js"></script>
    	<script type="text/javascript">
    	 	 $(document).ready(function(){
	    		  if("${spuId}"!=null&&"${spuId}"!=""){
	    			  $(".label_active").click();
	    		  }else{
	    			  $(".select_kind").find("label :first").click();
	    		  }
    		})
    		var labels = $(".select_kind").find("label"),       //tab
    			percentages = $(".degree").find(".percentum");  //好评率

    		// tab切换
			$(".comment_nav").on("click","a",function(){
				$(this).addClass("active").siblings().removeClass("active");
			});
			labels.each(function(i){
				$(this).find("input").change(function() {
					$(this).parent().addClass("label_active").siblings().removeClass("label_active");
				});
			});
			// 获取百分数
			percentages.each(function(i){
				var num = $(this).html(),  //百分数获取
				    widthpre = $(this).prev().width();  //总宽度获取
				    a = parseFloat(num.substring(0 ,num.length -1));  //将字符串变为小数
				    wid = (widthpre * (a/100)).toFixed(0);   
					wid += "px"	;
					$(this).prev().find("p").attr("style","width:"+wid);
				
			});
			function setShopMsg(price,id){
				$("#price").text(price);
				$("#skuId").val(id);
			}
			var houseList;
		    $(document).ready(function(){
	  		 var houseOption = {
		              container_id : "content_containers",
		              rowTemplate_id : "dsj_row_template",
		              pageTemplate_id : "dsj_page_template",
		              url:_ctx+"/front/warrant/shopCommentList",
		            }
		            houseList = new $.fn.houseList(houseOption);
		            houseList._init();
	         	
		    })
		    function checkComment(type){
	    	$("#type").val(type);
			houseList.searchAjax();
		}
		    
		    function orderComfirm(){
		    	location=_ctx+"/front/warrant/orderComfirm?userId=${id}&skuId="+$("#skuId").val()+"&areaCodeThree="+$("#areaCodeThree").val();
		    }
		</script>
