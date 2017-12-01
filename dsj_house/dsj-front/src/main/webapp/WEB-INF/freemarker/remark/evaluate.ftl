<#assign ctx=request.getContextPath()>
<script type="text/javascript">
	var _ctx="${ctx}";
	var userId="${userId}"
</script>
<!DOCTYPE html>
<html>
	<head>
		<title>我的评价</title>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/h5.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/reset.css" />
	</head>
	
	<body>
		<div id="dsj">
			<div class="evaluate_kuang">
				<div class="h5_header">
					我的评价页面
				</div>
				<div class="h5_main">
					<div class="h5_module">
						<div class="mo_left">
							<img src="${agent.avatarUrl}">
						</div>
						<div class="mo_right">
							<div class="agent_name">
								<h4>${agent.name}</h4>
								<div class="grade_icon"><img src="${rankAndScore.smallIcon}"><span>${rankAndScore.name}</span></div>
								</div>
							<div class="agent_sign">
								<div class="agent_approve">${agent.auditName}</div>
								<div class="agent_score">综合得分:<strong>${rankAndScore.totalScore}分</strong></div>
							</div>
							<p><span>${agent.companyName}</span><span class="marl_30">${agent.workyears}年工作经验</span></p>
							<p>精耕区域：<span class="marl_18">${agent.businessName}</span></p>
							<div class="labelling">
							<#list agent.featureArray as f>
								<span class="color_${f_index}">${f}</span>
							</#list>
		      				
		      				</div>
						</div>
					</div>
					<div class="form_kuang">
						<div class="traverse_line">
							<p class="t_line"></p>
							<p class="t_content">对他进行评价</p>
						</div>
						<form>
							<ul class="dsj_menu">
								<li class="single">
									<h3>服务态度</h3>
									<div  class="smile_kuang">	
										<!-- 选中时添加amile_active -->
										<label for="service_opening_1" class="tiptop_smile">
											好评
											<input type="radio" name="service_opening" value="service_1" id="service_opening_1" >
										</label>
										<label for="service_opening_2"  class="good_smile">
											<input type="radio" name="service_opening" value="service_2" id="service_opening_2">
											中评
										</label>
										<label for="service_opening_3"  class="bad_smile">
											<input type="radio" name="service_opening" value="service_3" id="service_opening_3">
											差评
										</label>
									</div>
											
								</li>
								<li class="single">
									<h3>专业水平</h3>
									<div  class="smile_kuang">
										<!--选中添加class="tiptop_smile_active"  -->
										<label for="major_opening_1"  class="tiptop_smile ">
											好评
											<input type="radio" name="major_opening" value="major_1" id="major_opening_1" >
										</label>
										<!--选中添加class="good_smile_active"  -->
										<label for="major_opening_2"  class="good_smile ">
											<input type="radio" name="major_opening" value="major_2" id="major_opening_2">
											中评
										</label>
										<!--选中添加class="bad_smile_active"  -->
										<label for="major_opening_3"  class="bad_smile ">
											<input type="radio" name="major_opening" value="major_3" id="major_opening_3">
											差评
										</label>
									</div>
											
								</li>
								<!-- 点差评的时候，添加class="bad_icon" -->
								<li class="more">
									<label for="house_help_1">全能顾问<span class="praise_icon"></span>
										<input type="checkbox" name="house_help" value="" id="house_help_1" >
									</label>
									<label for="house_help_2">楼盘活地图<span class="praise_icon"></span>
										<input type="checkbox" name="house_help" value="" id="house_help_2">
									</label>
									<label for="house_help_3">税费权威<span class="praise_icon"></span>
										<input type="checkbox" name="house_help" value="" id="house_help_3">
									</label>
									<label for="house_help_4">亲和力爆棚<span class="praise_icon"></span>
										<input type="checkbox" name="house_help" value="" id="house_help_4">
									</label>
									<label for="house_help_5">讲解全面<span class="praise_icon"></span>
										<input type="checkbox" name="house_help" value="" id="house_help_5">
									</label>
									<label for="house_help_6">带看耐心<span class="praise_icon"></span>
										<input type="checkbox" name="house_help" value="" id="house_help_6">
									</label>
								</li>
								<li class="textarea_kung">
									<textarea placeholder="您的建议：" id="remark" maxLength="200" onkeyup="wordNum()"></textarea>
									<div class="residue_kuang">
										您还可以输入<span id="word_200">200<span>个字
									</div>
								</li>
								<div id="success" style="display:none">
									<div class="traverse_line">
										<p class="t_line"></p>
										<p class="t_content">您提出的建议</p>
									</div>
									<div class="you_suggest">
										建议经纪人都是完美主义者!
									</div>
								</div>
								
								
							</ul>
							<!-- 有情况下，添加class="btn_input_active"-->
							<button class="btn_input " type="button" id="submitButtton">提交评价</button>
						</form>
					</div>
				</div>
			</div>
			<div class="min_box" >
				提交失败，请检查您的网络！
				<!-- 提交成功，感谢您的参与！ -->
			</div>
		</div>
	</body>
	 <script src="${ctx}/static/front/js/h5/flexible.js"></script> 
	 <script src="${ctx}/static/front/js/h5/zepto.js"></script>
	 <script src="${ctx}/static/front/js/h5/h5_evaluate.js"></script>
	 <script src="${ctx}/static/front/js/agent.js"></script>
	<script type="text/javascript">
	var content='';
	var word = 0;
	var radio = 0
	var box1=false;
	var box2=false;
	$(function(){

	})
	function wordNum(){
		
		word = $("#remark").val().length;
		$("#word_200").html(200-word);
   		//change();	
   	}
	function change(){
		if(box1&&box2){
			radio=1
		}
		if(radio>0){
   			$("#submitButtton").addClass("btn_input_active");
   			$("#submitButtton").attr("onclick","goRemark();");
   		}else{
   			$("#submitButtton").removeClass("btn_input_active");
   			$("#submitButtton").removeAttr("onclick");
   		}
	}
	 $("input[name='major_opening']").on('click',function(){
		 box1=true;
		 change();	
	 })
	  $("input[name='service_opening']").on('click',function(){
		 box2=true;
		 change();
	 })
	function goRemark(){
		 var data={
				 userId:userId
		 };
		content = $("#remark").val();
		if($("input[name='major_opening']:checked").val()!=null){
				data.major = $("input[name='major_opening']:checked").val().split("_")[1];			
		}
		 if($("input[name='service_opening']:checked").val()!=null){
				data.service = $("input[name='service_opening']:checked").val().split("_")[1];			
		}
		
		$.ajax({
	        type:"post",
	        url:_ctx+"/remark/add",
	        data: data,
	        datatype:"json",
	        success:function(result){
	        	if(result.status==200){
	        		
	        		$(".min_box").html("提交成功，感谢您的参与！").show();

	        		var min_box_width = $(".min_box").width();
	        		// 弹出框动态居中
	        		console.log(min_box_width);
	        		$(".min_box").attr("style","margin-left:-"+min_box_width/2+"px;display:block");
	        		setTimeout(function(){
	        			$(".min_box").hide();
	        			
	        			$(".textarea_kung").remove();
	        			$("#submitButtton").remove();
		        		$("#success").show();
		        		$(".you_suggest").html(content);
		        		//$("input[type='radio']").attr("disabled","disabled");
	        		},2000);
	        		
	        		
	        	}else{
	        		$(".min_box").html("提交失败，请检查您的网络！").show();
	        		var min_box_width = $(".min_box").width();
	        		// 弹出框动态居中
	        		//console.log(min_box_width);
	        		$(".min_box").attr("style","margin-left:-"+min_box_width/2+"px;display:block");
	        		setTimeout(function(){
	        			
	        			$(".min_box").hide();
	        			
	        		},2000)
	        	}
	        }
	     })
	}
	</script>
</html>
