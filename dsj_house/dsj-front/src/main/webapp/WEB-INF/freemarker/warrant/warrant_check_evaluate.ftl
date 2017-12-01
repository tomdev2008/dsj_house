<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
	  <!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	         <h1 onclick="javascript:window.location.href='${ctx }/'">大搜家</h1>
	        <span>自交易</span>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress"> 
	      <ul>
	        <li><a href="${ctx }/">大搜家首页</a>
	        <div class="progressTriangle">
	         
	        </div>
	        </li>
	        <li><a href="${ctx }/front/personCenter/center">个人中心</a>
	           <div class="progressTriangle">
	          
	        </div>
	        </li>
	        <li><a href="javascript:void(0);">查看评价</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
		  	<div class="flow_center">
		 		<div class="house_type_tittle">
			    	<h2>查看服务评价</h2>
			    </div>
			    <div class="warrant_form_kuang warrant_evaluate_kuang">
			    	<!-- form -->
			          <form>
			          		<div class="f_cendiv">
			          			服务态度：
								<div id="payment_star" class="select_kind">
					            </div>
			          		</div>
			          		<div class="f_cendiv">
			          			专业水平：
								<div id="major_star"  class="select_kind">
					            </div>
			          		</div>
			          		<div class="f_cendiv">
			          			响应速度：
								<div id="speed_star" class="select_kind">
					            </div>
			          		</div>
			          		<div class="impress_label_kuang">
			          			<span>印象标签：</span>
								<div class="select_kind">
								<#if fwuserComList??>
									<#list fwuserComList as f>
										<#if f.evaluateNum="1">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="服务专业">服务专业
											</label>
										</#if>
										<#if f.evaluateNum="2">
											<label class="label_active">
									  			<input type="checkbox"  name="label" value="经验富足">经验富足
											</label>
										</#if>
										<#if f.evaluateNum="3">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="流程清晰">流程清晰
											</label>
										</#if>
										<#if f.evaluateNum="4">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="踏实稳重">踏实稳重
											</label>
										</#if>
										<#if f.evaluateNum="5">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="认真严谨">认真严谨
											</label>
										</#if>
										<#if f.evaluateNum="6">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="礼貌待客">礼貌待客
											</label>
										</#if>
										<#if f.evaluateNum="7">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="诚信正直">诚信正直
											</label>
										</#if>
										<#if f.evaluateNum="8">
											<label class="label_active">
									  			<input type="checkbox" name="label" value="办事高效">办事高效
											</label>
										</#if>
									</#list>
								</#if>
					            </div>
			          		</div>
			          		<div class="appraise_textarea">
			          			<span>对TA评价：</span>
								<div class="select_kind check_kind">
								${fwuserComment.content}
					            </div>
			          		</div>
			          		<!-- <div class="btn_kuang">
				            	<button type="button" class="btn btn-primary pay_btn">立即支付</button>
			          		</div> -->
			          </form>
			    </div>
		  	</div>
		</div>
<script src="${ctx }/static/front/js/warrant.js"></script>
<script type="text/javascript">
	$("#payment_star").getStar();
	$("#major_star").getStar();
	$("#speed_star").getStar();
	$("#payment_star").unbind("click mouseover mouseout");
	$("#major_star").unbind("click mouseover mouseout");
	$("#speed_star").unbind("click mouseover mouseout");
	$(".select_kind").unbind("click");
	$(function(){
		var attitude = '${fwuserComment.attitude}';
		var num = jiSuan(attitude);
		$("#payment_star li").each(function(x,y){
			if(num-1>x){
				this.className="active";
			}else{
				this.className="clicked active";
				return false;
			}
		})
		
		var major = '${fwuserComment.major}';
		var num = jiSuan(major);
		$("#major_star li").each(function(x,y){
			if(num-1>x){
				this.className="active";
			}else{
				this.className="clicked active";
				return false;
			}
		})
		
		
		var speed = '${fwuserComment.speed}';
		var num = jiSuan(speed);
		$("#speed_star li").each(function(x,y){
			if(num-1>x){
				this.className="active";
			}else{
				this.className="clicked active";
				return false;
			}
		})
	})
	
	function jiSuan(fenZhi){
		if(fenZhi==-2){
			return 1;
		}else if(fenZhi==-1){
			return 2;
		}else if(fenZhi==0){
			return 3;
		}else if(fenZhi==1){
			return 4;
		}else if(fenZhi==2){
			return 5;
		}
	}
</script>