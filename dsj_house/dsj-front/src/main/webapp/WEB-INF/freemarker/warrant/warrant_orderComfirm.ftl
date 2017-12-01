<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
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
	        <li><a href="${ctx}/">大搜家首页</a>
	        <div class="progressTriangle">
	        </div>
	        </li>
	        <li><a href="${ctx}/warrant/index">自交易服务</a>
	           <div class="progressTriangle">
	        	</div>
	        </li>
	        <li><a href="#">提交订单</a>
	           <div class="progressTriangle">
	        	</div>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
		  	<div class="flow_center">
		 		<div class="house_type_tittle">
			    	<h2>支付方式</h2>
			    </div>
			    <div class="f_cendiv">支付方式：<strong>在线支付</strong></div>
			    <div class="house_type_tittle">
			    	<h2>服务信息</h2>
			    </div>
			    <div class="paylist">
			    	<table class="table table-hover">
				    	<thead>
				    		<tr>
								<th>服务信息</th>
								<th>地区</th>
								<th>公司</th>
								<th>服务者</th>
								<th>服务费</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${spuPo.name}</td>
								<td>${areaPo.name}</td>
								<td>${companyPo.companyName}</td>
								<td>${userPo.realname}</td>
								<td><strong>${skuPo.price}</strong>元</td>
							</tr>
						</tbody>		
					</table>
			    </div>
			    <div class="warrant_form_kuang">
			    	<!-- form -->
			          <form>
				            <!-- 用户协议 -->
				            <div class="checkbox userAgreement">
				              <label>
				                <input type="checkbox" class="read_box" checked> 我已阅读并同意
				              </label>
				              <a class="userAgreement_button">《大搜家自交易服务协议》</a>
				            </div>
				            <!-- 确认 -->
				            <button type="button" class="btn btn-primary countersign">提交订单</button>
			          </form>
			    </div>
		  	</div>
		</div>
	          <!-- 弹出框 -->
        <div id="popup_box" class="popup_box3" style="display: none">
        	<!-- 带按钮 -->
            <div class=" box_style box_affirm" style="display:none">
                <span class="box_close"></span>
                <div class="box_kuang">
                    <div class="box_title"> 
                        <span>确定下架吗?</span>
                    </div> 
                    <p class="hint">下架后，房源将不再展示在前端页面</p>
                    <form>
                    <div class=" buttons">
                            <button type="reset" class="btn reset_botton">取消</button>    
                            <button type="submit" class="btn submit_botton">确认</button>
                        </div>  
                    </form>
                </div>
            </div>
            <div class="box_style box_affirm"  style="display:none">
                <span class="box_close"></span>
                <div class="box_kuang">
                    <div class="box_title" > 
                        <span>您确定要将此房源发布吗?</span>
                    </div> 
                    <form>
                    <div class=" buttons">
                            <button type="reset" class="btn reset_botton">取消</button>    
                            <button type="submit" class="btn submit_botton">确认</button>
                        </div>  
                    </form>
                </div>
            </div> 
            <!-- 失败 -->
            <div class=" box_style box_defeated" style="display:none">
                <span class="box_close"></span>
                <div class="box_kuang">
                    <div class="box_title "> 
                        <span>支付超时，请重新下单！</span>
                    </div> 
                    <div class="box_center">
                    	超过30分钟未完成支付<a href="##">重新下单>></a>
                    </div>
                    <div class="box_center">
                    	<strong>3s</strong>后将自动跳转至大搜家首页
                    </div>
                </div>
            </div>
            <!-- 成功支付 -->
            <div class=" box_style box_succeed" style="display:none" >
                <span class="box_close"></span>
                <div class="box_kuang">
                    <div class="box_title "> 
                        <span>支付成功！</span>
                    </div> 
                    <div class="box_center">
                    	<a>查看订单详情>></a>
                    </div>
                    <div class="box_center box_center2">
                    	<strong>3s</strong>后将自动跳转至大搜家首页
                    </div>
                </div>
            </div>
            <!-- 协议 -->
            <div class=" box_style box_agreement" style="display:none">
                <span class="box_close"></span>
                <div class="agreement_kuang">
                	<h1>大搜家自交易服务协议</h1>
                	<div class="agreement_content">
                		<p>本协议由你与大搜家网站（www.dasoujia.com）所有者大搜家公司（以下简称为“大搜家”）之间就大搜家为你提供居间服务所订立的契约，请你仔细阅读本协议，同意本协议并在大搜家网站支付服务费成功后，本协议即构成对双方有约束力的法律文件。</p>
                		<div>
							<h4>1.居间事项</h4>
							<p>1.1由大搜家为你居间介绍中国境内合法设立的、具有合法资质、信誉良好的服务机构（以下简称“服务者”）。
							</p>
							<p>1.2大搜家有权通过大搜家网站替服务者先行收取服务费，如果该项服务过程中由于增加他项服务或相关机构需收取另外的费用，你应按照该项服务的具体说明或向大搜家客服人员咨询后支付相应的费用，切勿随意支付，否则，大搜家不承担任何责任。本协议项下的服务费，由大搜家于本协议项下的服务全部完成并经你确认后再行支付给服务者。
							</p>
							<p>1.3为更好地为你提供服务，大搜家有义务监督服务者的服务，确保服务者按照大搜家网站上的要求完成相应的服务。
							</p>
							<h4>2.你的权利义务</h4>
							<p>2.1你同意由大搜家居间介绍具有合法资质的服务者为你提供服务。
							</p>
							<p>2.2你有权随时了解服务事项的实际进度及办理结果。
							</p>
							<p>2.3你不得要求大搜家及服务者从事任何违反职业道德或者任何违反法律法规的事宜。
							</p>
							<p>2.4为了保证服务者为你提供的服务合法有效，你应保证向服务者如实提供真实、合法的信息，并保证所提供的相关证照及资料真实合法，如因你提供虚假信息、证照资料等原因导致本协议项下的服务不能或者终止履行，服务者不承担任何责任。
							</p>
							<p>2.5为了更好、更有效地办理服务事项，你应配合服务者完成服务事项过程中应由你配合完成的事务，并提供该服务所需的相关法律文件、证照资料。
							</p>
							<p>2.6如因你自身过错导致本协议第1条项下的服务不能执行、完成，服务者不承担任何责任。
							</p>
							<p>2.7为了提高服务的效率，你同意该服务办理过程中相关信息的传递、事项的确认、文件的提交等事宜可以通过大搜家网站来进行。
							</p>
							<h4>3.大搜家的权利义务</h4>
							<p>3.1大搜家有义务替你选择合适的服务者以谨慎、勤勉、尽职的原则和合法途径为你提供服务，并有义务监督服务者的服务行为，最大限度的维护你的合法权益。
							</p>
							<p>3.2大搜家有权先行替服务者向你收取服务费。
							</p>
							<p>3.3除本协议第4条及该服务具体说明中所需缴纳的费用之外，大搜家在服务者提供服务过程中不再替服务者收取任何其他费用。
							</p>
							<p>3.4你有权利监督为你提供服务的服务者服务过程中的各项行为，当服务者的行为不符合该项服务的要求时，可以向大搜家投诉，大搜家将根据你的请求和实际情况采取必要的措施。
							</p>
							<p>3.5当服务者严重侵犯你的合法权益，或者违反大搜家网站的服务承诺时，你有权终止本协议。
							</p>
							<p>3.6如因服务者工作失误导致服务全部或部分不能完成，服务者需向你退还交纳的全部或部分服务费用，并退还你的全部资料。大搜家协助处理相关的退费手续。如遇不可抗力，或者政策变动等原因，导致该项服务全部或部分不能完成的，大搜家平台及服务者对不能完成的服务不承担任何责任。
							</p>
							<p>3.7服务过程中如需你对相关资料文件签字或盖章确认的，经你确认无误并签字盖章，意味着你对该等资料文件及其内容的真实性予以认可，你需对该等资料文件的真实性负全部责任，如果因为材料不真实造成的一切后果，均由你承担，与大搜家及服务者无关。
							</p>
							<h4>4.服务费的支付</h4>
							<p>4.1通过大搜家网站所提供的线上支付工具（支付宝或网银）进行支付。
							</p>
							<p>4.2当服务者按照大搜家网站的要求完成全部服务事项，并经你确认无误后，由大搜家将服务费用转付给服务者。
							</p>
							<h4>5.保密条款</h4>
							<p>协议各方均有义务对本协议所有内容进行保密，保密期限不限于本协议有效期限，也不因本协议解除、终止、无效或被撤销而失效，即你应对大搜家及服务者提供服务之全过程情况承担保密义务，而大搜家及服务者应对在与你合作中了解到的你的相关信息承担保密义务。
							</p>
							<h4>6.争议解决</h4>
							<p>如因本协议发生争议，双方应本着有利于案件进展，维护你权益的原则友好协商解决，如协商不能达成一致，双方提交北京仲裁委员会仲裁。
							</p>
							<h4>7.其他</h4>
							<p>7.1当你仔细阅读本协议并在大搜家网站上支付服务费成功后，即表示你与大搜家、服务者之间就本协议内容已达成完全一致，已确认由大搜家为你选择服务者开始为你提供服务，本协议即时生效。
							</p>
							<p>7.2本协议于本协议项下全部服务事宜完成之日终止。
							</p>
                		</div>
                	</div>
                </div>
            </div>      
        </div>
	</div>
    <script type="text/javascript">
    	//弹出框
        (function(){
            var box_affirm   = ".box_affirm",    //1
                box_defeated = ".box_defeated",  //2
                box_succeed  = ".box_succeed";   //3
            $(".userAgreement_button").on("click",function(state){
                    $(".popup_box3").show();
                    $(".box_agreement").show();
            });
            $(".box_close").on("click",function(event){
                 $(".popup_box3").hide();
                 event.stopPropagation(); 
            });
            $(".reset_botton").on("click",function(event){
                 $("#popup_box").hide();
                 event.stopPropagation(); 
            });
            $(".submit_botton").on("click",function(event){
                 $("#popup_box").hide();
                 event.stopPropagation();
                //  发送ajax请求
            });
            
            $(".read_box").on("click",function(event){
            	if($(this).prop("checked")){
            		$(".countersign").removeAttr("disabled");            		
            	}else{
            		$(".countersign").attr("disabled","true");
            	}
            });
            
            //下订单，跳支付操作
            $(".countersign").on("click",function(event){
           		$(".countersign").attr("disabled","true");
           		$(".read_box").off("click");
            	if (user == ''){
					show_box(7);
				}else{
	                $.ajax({
						type: "POST",
						url: "${ctx}/front/warrant/doOrder",
						data: {
							fwuserId : ${propertyPo.id},
							areaCodeThree : ${areaPo.id}, 
							areaCodeThreeName : "${areaPo.name}",
							skuId : ${skuPo.id}
						},
						dataType: "json",
						success: function(result){
							if(result.status == 200){
								window.location.href=result.data;
							}else{
								show_box(3,result.data);
								$(".countersign").removeAttr("disabled");
							}
						}
					});
                }
            });
            
        })();
        
        var user="${Session.pc_user_session}";
		function show_box(6){
			$.ajax({
		  		type:"post",
		  		async: false,
		  		url:_ctx+"/login/save_session",
		  		data:{
	  				url:window.location.href
	  			},
		  		dataType:"json",
		  		success: function(result){
	  				window.location.href=_ctx+"/login/tologin";
	  			}
	  		});
		}
        
        
    </script>
