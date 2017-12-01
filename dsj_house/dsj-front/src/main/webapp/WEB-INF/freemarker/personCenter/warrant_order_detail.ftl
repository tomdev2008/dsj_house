<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/swiper-3.4.2.min.css">
    <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
    <script src="${ctx }/static/front/js/personCenter/order.js"></script>
  	<div class="warrant">
	  <!-- 经纪人前端logo搜索框 -->
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
	        <li><a href="##" onclick="javascript:window.location.href='${ctx }/'">大搜家首页</a>
	        <div class="progressTriangle">
	         
	        </div>
	        </li>
	        <li><a href="#" onclick="javascript:window.location.href='${ctx }/front/personCenter/center'">个人中心</a>
	        <div class="progressTriangle">
	          
	        </div>
	        </li>
	        <li><a href="#" >订单详情</a>
	        <div class="progressTriangle">
	          
	        </div>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
		  	<div class="flow_center">
		 		<div class="flow_left">
		 			<div class="house_type_tittle">
			    		<h2>服务状态</h2>
			    	</div>
			    	<ul class="flow_list">
			    		<#if nodeList??> 
					  		<#list nodeList as node> 
					  		<li  <#if node.status==2> class="flow_active"</#if>>
					  		    	<h4>${node.pcname }</h4>
					  		    	<#if node.fieldList??> 
						  		    	<#list node.fieldList as field> 
						  		    		<#if field.type == 1> 
						  		    			<p><span>地址：</span>${field.fieldVal }</p>
					  		    			<#else>
						  		    			<p><span>时间：</span>${field.fieldVal}</p>
						  		    		 </#if>
						  		    	</#list>
					  		    	 </#if>
					  		    	<#if node.dealTime??> 
					    				<p> ${node.dealTime?string("yyyy-MM-dd HH:mm:ss")  }</p>
					    			 </#if>
					    			<span class="string <#if node.status==2>string_active</#if>"></span>
					  		</li>
					  		</#list>
					    </#if>
			    		
			    	</ul>
		 		</div>
		 		<div class="flow_right">
		 			<div class="r_message">
			 			<div class="house_type_tittle">
				    		<h2>订单信息</h2>
		 				</div>
		 				<div class="message_detail">
			    				<p><span>订单编号：</span>${detailVo.orderNo }</p>
			    				<p><span>下单时间：</span>${detailVo.createTime?string("yyyy-MM-dd HH:mm:ss") }</p>
			    				<p><span>支付方式：</span>在线支付</p>
			    				<p><span>订单状态：</span>
			    				<#if detailVo.status = 1>
			    					待付款
			    				<#elseif detailVo.status = 2>
			    					已取消
		    					<#elseif detailVo.status = 3>
		    						已过期
		    					<#elseif detailVo.status = 4>
		    						已付款
		    					<#elseif detailVo.status = 5>
		    						退款受理中
		    					<#elseif detailVo.status = 6>
		    						退款完成
		    					<#elseif detailVo.status = 7>
		    						待买家确认
		    					<#elseif detailVo.status = 9>
		    						待评价
			    				<#elseif detailVo.status = 10>
		    						已评价
			    				<#elseif detailVo.status = 12>
		    						服务进行中
			    				<#elseif detailVo.status = 13>
		    						服务进行中
			    				</#if>
			    				
			    				<#if detailVo.status = 1>
			    					<a href="#" onclick="gotoPay(this,${detailVo.orderId})">去付款</a>
									<a href="#" onclick="gotoCancel(this,${detailVo.orderId})">取消订单</a>
			    				<#elseif detailVo.status = 2>
			    					<a href="#" onclick="gotoReOrder(this,${detailVo.id})">重新下单</a>
		    					<#elseif detailVo.status = 3>
		    						<a href="#" onclick="gotoReOrder(this,${detailVo.id})">重新下单</a>
		    					<#elseif detailVo.status = 4>
		    						<a href="#" onclick="gotoRefund(this,${detailVo.orderId})">申请退款</a>
		    					<#elseif detailVo.status = 5>
		    					<#elseif detailVo.status = 6>
		    					<#elseif detailVo.status = 7>
		    						<a href="#" onclick="gotoSucess(this,${detailVo.orderId})">确认完成</a>
									<a href="#" onclick="gotoRefund(this,${detailVo.orderId})">申请退款</a>
		    					<#elseif detailVo.status = 9>
		    						<a href="#" onclick="gotoAddReview(this,${detailVo.orderId})">去评价</a>
			    				<#elseif detailVo.status = 10>
		    						<a href="#" onclick="gotoShowReview(this,${detailVo.orderId})">查看评价</a>
			    				<#elseif detailVo.status = 12>
		    						<a href="#" onclick="gotoRefund(this,${detailVo.orderId})">申请退款</a>
			    				<#elseif detailVo.status = 13>
		    						<a href="#" onclick="gotoRefund(this,${detailVo.orderId})">申请退款</a>
			    				</#if>
			    				<#if detailVo.reviewStatus = 1> 
			    				<p><span>评价时间：</span>${detailVo.reviewDate?string("yyyy-MM-dd HH:mm:ss") }</p>
			    				 </#if>
			    			</div>
		 			</div>
		 			<div class="r_message">
		 				<div class="house_type_tittle">
				    		<h2>服务信息</h2>
		 				</div>
		 				<div class="message_detail">
		    				<p><span>服务地区：</span>${detailVo.areaCodeTwoName }<span class="qu_icon">${detailVo.areaCodeThreeName }</span></p>
		    				<p><span>服务名称：</span>${detailVo.productName }</p>
		    				<p><span>服务费用：</span>${detailVo.payment }元</p>
		    				<p><span>服 务 者 ：</span>${detailVo.realName }</p>
		    				<p><span>联系电话：</span>${detailVo.dbPhone }</p>
			    		</div>
			    	</div>
		 		</div>
		  	</div>
		</div>
	</div>
