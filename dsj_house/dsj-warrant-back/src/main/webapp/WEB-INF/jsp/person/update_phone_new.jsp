<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
	<div class="col-xs-12">
		<div class="white-head">
			<form onsubmit="return false;">
			    <div class="errorHint" style="visibility: hidden;"></div>
			     <div class="form-group form-inline">
			       <input type="text" class="form-control" id="phone" placeholder="新手机号">
			     </div>
			     <div class="form-group update_phone form-inline">
			       <input type="text" class="form-control" id="code" placeholder="验证码">
			       <div class="verifyCodeButton" >
			        	<button onclick="toSendVcode()"> 获取验证码</button>
			       </div>
			     </div>
		   		  <button type="button" class="dsj_btn btn btn-primary" onclick="updateNewPhone()" >确定</button>
		   		  <button type="button">取消</button>
		   </form>
		</div>
	</div>

	<script type="text/javascript">
	function toSendVcode(){
	     var myreg = /^[0-9]{11}$/;       
	     var phone=$("#phone").val();
	     if(myreg.test(phone)){
	       $.ajax({
		         type:"post",
		         url:_ctx+"/loginuser/send_vcode",
		         dataType:"json",
		         data:{"phone":$("#phone").val(),"type":5},
		         success:function(data){
		        	  event.stopPropagation(); 
				         mybutton = 120;
				         $(".verifyCodeButton").addClass("verifyCodeActive");
				         $(".verifyCodeButton button").attr("disabled", true)
				         var time = setInterval(function(){
				           mybutton--;
				           $(".verifyCodeButton button").text(mybutton+"s重新获取");
				           if(mybutton <= 0){
				             clearInterval(time);
				             $(".verifyCodeButton").removeClass("verifyCodeActive").html("获取验证码");
				             $(".verifyCodeButton button").text("获取验证码").removeAttr("disabled");
				           }
				         },1000);
		         }
		     });
	       
	     }else{
	       $(".errorHint").css("visibility","visible"); 
	        $(".errorHint").html("您输入的手机号有误，请重新输入");
	     }
	}
	function updateNewPhone(){
		 $.ajax({
	          type:"post",
	          url:_ctx+"/back/person/warrant/updateNewPhone",
	          dataType:"json",
	          data:{"phone":$("#phone").val(),"code":$("#code").val()},
	          success:function(data){
	              if(data.status == 200){
	            	 location=_ctx+"/back/frame/person/warrant/to_update_phone";
	              }else{
	                  $(".errorHint").css("visibility","visible"); 
	  		       	 $(".errorHint").html(data.message);
	              }
	          }
	      });
	}
	</script>

    