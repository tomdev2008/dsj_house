  <head>
    <meta content="北京业主卖房,北京个人卖房,我要卖房,北京委托卖房" name="keywords">
    <meta content="接受业主委托卖房,提供个人卖房的快捷渠道，提供房屋过户办理服务，让您房产过户、房产更名、产权办理更加安全,放心。" name="description">
  </head> 
    <#include "common/taglibs.ftl">
    <link rel="stylesheet" href="${ctx }/static/front/css/centre.css">
	  <!-- 经纪人前端logo搜索框 -->
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	        <h1>大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	        <span>业主委托</span>
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
	        <li>我要卖房</li>
	      </ul>
	    </div>
	  <!-- pc主体 -->
	  	<div class="centre_main">
            <!-- 标题 -->
			<ul class="centre_tab">
                <li>我要卖房</li>
                <!--<li>我要出租</li>-->
            </ul>	
            <!-- 表单卖房 -->
            <div class="centre_form sellehouse">
                <form id="oldHouse">
                    <div class="form-group">
                    	<div class="input_width">
	                        <label for="exampleInputCity">小区所在城市</label>
	                        <input type="text" class="form-control" name="city" id="exampleInputCity" maxLength="20" placeholder="所在城市">
	                        <div class="demptyText"></div>
	                     </div> 
                    </div>
                    <div class="form-group">
                    <div class="input_width">
                        <label for="exampleInputEstateName">小区名称</label>
                        <input type="text" class="form-control" id="exampleInputEstateName" maxLength="20" name="buildingName">
                        <div class="demptyText"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputName">房屋地址</label>
                        <div class="row">
                            <div class="col-xs-2">
                                <input type="text" class="form-control" name="buildingNum" maxLength="10" placeholder="楼栋号">
                            </div>
                            <div class="col-xs-2">
                                <input type="text" class="form-control" name="unitNum" maxLength="5" placeholder="单元号">
                            </div>
                            <div class="col-xs-2">
                                <input type="text" class="form-control" name="roomNum" maxLength="5" placeholder="门牌号">
                            </div>
                        </div>
                    </div>
                    <div class="form-group ExpectPrice" >
                        <label for="exampleInputExpectPrice1">期望售价</label>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input type="text" id="exampleInputExpectPrice1" maxLength="10" class="form-control" name="expectedPrice">
                                     <div class="input_addon">万元</div> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                    <div class="input_width">
                        <label for="exampleInputusername"> 您的称呼</label>
                        <input type="text" class="form-control" name="entrustUsername" maxLength="20" id="exampleInputusername">
                        <div class="demptyText"></div>
                        </div>
                    </div>
                    <div class="form-group">
                    <div class="input_width">
                        <label for="exampleInputphone"> 手机号码</label>
                        <input type="text" data-validate="phone_new,required" maxLength="11" class="form-control" name="entrustPhone" id="phone1">
                    	<div class="demptyText"></div>
                    	</div>
                    </div>
                    <!-- 验证码 -->
                    <div class="form-group verifyCode">
                        <label for="exampleInputAmount">验证码</label>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input type="text" name="vcode" maxLength="6"  class="form-control" id="vcode1">
                                    <div class="verifyCodeButton" >
                                    	<input type="button"onclick="getVcode(1,this)" value="获取验证码">
                                    </div>  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group buttons">
                        <button type="reset" onclick="cancle()" class="btn reset_botton">取消</button>    
                        <button type="button" onclick="addOldHouse()" class="primary">提交委托</button>
                    </div> 
                    
                </form>
            </div>
            <!--表单出租-->
            <div class="centre_form rentout" style="display:none">
                <form id="rent">
                    <div class="form-group">
                        <label for="exampleInputCity">小区所在城市</label>
                        <input type="text" class="form-control" name="city"id="exampleInputCity" >
                        <div class="demptyText"></div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEstateName">小区名称</label>
                        <input type="text" class="form-control" name="buildingName"id="exampleInputEstateName">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputName">房屋地址</label>
                        <div class="row">
                            <div class="col-xs-2">
                                <input type="text" name="buildingNum" id="exampleInputName" class="form-control" placeholder="楼栋号">
                            </div>
                            <div class="col-xs-2">
                                <input type="text" class="form-control" name="unitNum" placeholder="单元号">
                            </div>
                            <div class="col-xs-2">
                                <input type="text" class="form-control" name="doorNum" placeholder="门牌号">
                            </div>
                        </div>
                    </div>
                    <div class="form-group ExpectPrice" >
                        <label for="exampleInputExpectPrice">期望售价</label>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="expectedPrice" id="exampleInputExpectPrice">
                                     <div class="input_addon">元/月</div> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputusername"> 您的称呼</label>
                        <input type="text" name="entrustUsername" class="form-control" id="exampleInputusername">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputphone"> 手机号码</label>
                        <input type="text" data-validate="required,phone_new" class="form-control" name="entrustPhone"id="phone2">
                    </div>
                    <!-- 验证码 -->
                    <div class="form-group verifyCode">
                        <label for="exampleInputAmount">验证码</label>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="vcode" id="vcode2">
									<div class="verifyCodeButton" >
                                    	<input type="button"onclick="getVcode(2,this)" value="获取验证码">
                                    </div>  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group buttons">
                        <button type="reset" class="btn reset_botton">取消</button>    
                        <button type="button" onclick="addRent()" class="btn submit_botton">提交委托</button>
                    </div> 
                    
                </form>
            </div>
		</div>
		
		
	<script src="${ctx }/static/front/js/validate/verify.notify.ext.js"></script>
	<script src="${ctx }/static/front/js/validate/verify.notify.js"></script>
	<script type="text/javascript" src="${ctx }/static/front/js/register.js"></script>
    <script>
    $(function(){
    	document.title = "业主委托-我要卖房-大搜家";
    })
    function cancle(){
    	window.location="${ctx }/"
    }
     function empty_f(elem){
      //清空
      var ursnameCode_value = $(elem).val();
      $(elem).on("keyup",function(){
        empty_botton(elem);
      });
      function empty_botton(elem){
        var ursnameCode_value = $(elem).val();
          if(ursnameCode_value != ""){
              $(elem).next().show();  
            }
      };
      $(".demptyText").on("click",function(event){
        $(this).prev("input").val("");
        $(this).hide();
        event.stopPropagation();
      });
    };
    empty_f("#exampleInputCity");
     empty_f("#exampleInputEstateName");
      empty_f("#exampleInputusername");
       empty_f("#phone1");
    
    
    
    
        $(".centre_tab").delegate("li","click",function(e){
            var Li_html = $(this).html();
            console.log(Li_html);
           $(this).css({"color": "#2775e9" }).siblings().css({"color":"#333"})
            if(Li_html == "我要出租"){
                $(".rentout").show();
                $(".sellehouse").hide();
            }else{
                $(".sellehouse").show();
                $(".rentout").hide();
            }
        });
        
        function validateChange(){
       		var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
			var tel = /^\d{3,4}-?(\d{7,9})|\d{7,9}$/;  
	
			if (!(tel.test($("#phone2").val()) || mobile.test($("#phone2").val()))) {
				$("#submitButtton").removeClass("btn-primary");
       			$("#submitButtton").removeAttr("onclick");
			}else{
				$("#submitButtton").addClass("btn-primary");
       			$("#submitButtton").attr("onclick","addOldHouse();");
			}
       		var l = $("#vcode1").val().length;
       		if(l!=6){
       			$("#submitButtton").removeClass("btn-primary");
       			$("#submitButtton").removeAttr("onclick");
       		}else{
       			$("#submitButtton").addClass("btn-primary");
       			$("#submitButtton").attr("onclick","addOldHouse();");
       		}

       		
       	}
        
        function addOldHouse(){

        	if($("#vcode1").val()==""||$("#vcode1").val().length!=6){
        		show_box(2,"请输入正确验证码");
        		return;
        	}
        	 var reg=/^[0-9]{1}\d*(\.\d{1,2})?$/;
		
			if (!reg.test($("#exampleInputExpectPrice1").val())) {
				show_box(2,"请输入正确价格");
        		return;
			}
         $("#oldHouse").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#oldHouse").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/front/entrust/addOldHouse",
		  			success:function(result){
		  				if(result.status==200){
			  				show_box(3,result.message);
			  				
		  					setTimeout(function(){
		  						location=_ctx+"/front/entrust/sell";
		  					},3000);
		  					
		  				}else{
		  					show_box(2,result.message);
		  				}
		  				
		  			}
		  		})
		  	}
		  	})
        }
        function addRent(){
        	if($("#vcode2").val()==""||$("#vcode2").val().length!=6){
        		show_box(2,"请输入正确验证码");
        		return;
        	}
	         $("#rent").validate(function (result) {
			  	if(result){
			  		$.ajax({
			  			type:"post",
			  			async:true,
			  			data:$("#rent").serialize(),
			  			dataType:"json",
			  			url:_ctx+"/front/entrust/addRent",
			  			success:function(result){
			  				if(result.status==200){
			  					show_box(3,result.message);
			  					location=_ctx+"/front/entrust/sell";
			  				}else{
			  					show_box(2,result.message);
			  				}
			  				
			  			}
			  		})
			  	}
			  })
        }
        var countdown=60; 
        function settime(obj) { 
			if (countdown <= 0) { 
				obj.removeAttribute("disabled");    
				obj.value="获取验证码"; 
				countdown = 60; 
			} else { 
				obj.setAttribute("disabled", true); 
				obj.value="重新发送(" + countdown + ")"; 
				countdown--; 
				setTimeout(function() { 
					settime(obj);
					},1000)
			} 
		} 
        function getVcode(param,obj){
        	if(param==1){
        		$("#oldHouse").validate(function (result) {
		  			if(result){
		  				settime(obj);
				  		$.ajax({
				  			type:"post",
				  			async:true,
				  			data:{phone:$("#phone1").val(),flag:0},
				  			dataType:"json",
				  			url:_ctx+"/front/entrust/vcode",
				  			success:function(result){
				  				show_box(3,"验证码发送成功");
				  				
				  			}
				  		})
		  			}
		  		})	
        	}else{
        		$("#rent").validate(function (result) {
		  			if(result){
				  		settime(obj);
				  		$.ajax({
				  			type:"post",
				  			async:true,
				  			data:{phone:$("#phone2").val(),flag:0},
				  			dataType:"json",
				  			url:_ctx+"/front/entrust/vcode",
				  			success:function(result){
				  				show_box(3,"验证码发送成功");
				  			}
				  		})
		  			}
		  		})	
        	}
        }
    </script>