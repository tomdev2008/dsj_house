<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/swiper-3.4.2.min.css">
    <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
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
	        <li><a href="##">大搜家首页</a>
	        <div class="progressTriangle">
	         
	        </div>
	        </li>
	        <li><a href="##">个人中心</a>
	        <div class="progressTriangle">
	         
	        </div>
	        </li>
	        <li><a href="##">评价</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
		  	<div class="flow_center">
		 		<div class="house_type_tittle">
			    	<h2>服务</h2>
			    </div>
			    <div class="warrant_form_kuang warrant_evaluate_kuang">
			    	<!-- form -->
			          <form id="saveFwuserComment">
						<input type="hidden" name="orderId" value="${orderId}"/>
			          		<div class="f_cendiv">
			          			服务态度：
								<div id="payment_star" class="select_kind" onclick="tdFunction()">
								    <input type="hidden" name="attitude" id="attitude" data-validate="required"/> 
					            </div>
			          		</div>
			          		<div class="f_cendiv">
			          			专业水平：
								<div id="major_star"  class="select_kind" onclick="spFunction()">
								   <input name="major" type="hidden" id="major" data-validate="required"/>  
					            </div>
			          		</div>
			          		<div class="f_cendiv">
			          			响应速度：
								<div id="speed_star" class="select_kind" onclick="sdFunction()">
								    <input name="speed" type="hidden" id="speed" data-validate="required"/>
					            </div>
			          		</div>
			          		<div class="impress_label_kuang">
			          			<span>印象标签：</span>
								<div class="select_kind">
					            	<label>
									  <input type="checkbox" name="label" value="1">服务专业
									</label>
									<label>
									  <input type="checkbox" name="label" value="2">经验富足
									</label>
									<label >
									  <input type="checkbox" name="label" value="3">流程清晰
									</label>
									<label>
									  <input type="checkbox" name="label" value="4">踏实稳重
									</label>
									<label>
									  <input type="checkbox" name="label" value="5">认真严谨
									</label>
									<label >
									  <input type="checkbox" name="label" value="6">礼貌待客
									</label>
									<label>
									  <input type="checkbox" name="label" value="7">诚信正直
									</label>
									<label >
									  <input type="checkbox" name="label" value="8">办事高效
									</label>
					            </div>
			          		</div>
			          		<div class="appraise_textarea">
			          			<span>对TA评价：</span>
								<!-- <div class="select_kind"> -->
					            	<textarea name="content" class="form-control" onkeydown="checkMaxInput(this,300)"  
            							onkeyup="checkMaxInput(this,300)" onfocus="checkMaxInput(this,300)" 
            							onblur="checkMaxInput(this,300);" rows="10" placeholder="说说您对本次服务的感受吧"></textarea>
            						<p>
            							<div class="pull-right">
            								还能再输入<span class="textNum">300</span>字</div>
            						</p>
					           <!--  </div> -->
			          		</div>
			          		<div class="btn_kuang">
				            	<button type="button" onclick="formSubmit()" class="btn btn-primary pay_btn">发表评价</button>
			          		</div>
			          </form>
			    </div>
		  	</div>
	</div>
    <script src="${ctx }/static/front/js/warrant.js"></script>
  	<script type="text/javascript">
  	$("#payment_star").getStar();
  	$("#major_star").getStar();
  	$("#speed_star").getStar();
  	
function tdFunction(){
	var a = $("#payment_star .active").length;
	pingGu(a,"attitude")
}
function spFunction(){
	var a = $("#major_star .active").length;
	pingGu(a,"major")
}
function sdFunction(){
	var a = $("#speed_star .active").length;
	pingGu(a,"speed")
}

function pingGu(num,parm){
	if(num==1){
		$("#"+parm+"").val(-2);
	}else if(num==2){
		$("#"+parm+"").val(-1);
	}else if(num==3){
		$("#"+parm+"").val(0);
	}else if(num==4){
		$("#"+parm+"").val(1);
	}else if(num==5){
		$("#"+parm+"").val(2);
	}
}
function formSubmit(){
	if($("#attitude").val()=='' || $("#major").val()=='' || $("#speed").val()==''){
		alert("请对商家进行评价");
	}else{
		$.ajax({
			type:"post",
			url:_ctx+"/front/warrant/saveComment",
			data:$("#saveFwuserComment").serialize(),
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location = _ctx+"/front/personCenter/center";
				}
			}
		})
	}
}

//输入字数控制
function checkMaxInput(self, maxLen) {
	var num = maxLen-($(self).val().length);
	if(num > -1){
		$(self).closest(".appraise_textarea").find(".textNum").text(num);
	}
	if(num<=0){
		$(self).val($(self).val().substring(0,maxLen));
	}
}  
</script>