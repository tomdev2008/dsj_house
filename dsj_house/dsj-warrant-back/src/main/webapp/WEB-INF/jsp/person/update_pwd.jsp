<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
		<div class="white-head">
		<div class="row">
			<form id="dsj_passwors_form" class="form-horizontal dsj_form">
		        <h4 class="dsj_title">修改密码</h4>
		        <div class="form-group">
		          <label for="beianbianhao" class="col-sm-2 control-label" >原始密码</label>
		          <div class="col-sm-3">
		            <input type="text" class="form-control" name="password" data-validate="required">
		          </div>
		        </div>
		       <div class="form-group">
		          <label for="beianbianhao" class="col-sm-2 control-label">新密码</label>
		          <div class="col-sm-3">
		           <input type="text" class="form-control" name="password1"  data-validate="required,againPass#start">
		          </div>
		        </div>
				<div class="form-group">
		          <label for="beianbianhao" class="col-sm-2 control-label">确认密码</label>
		          <div class="col-sm-3">
		           	<input type="text" class="form-control" name="password2" data-validate="required,againPass#end">
		          </div>
		        </div>
		        
				<div class="row dsj_button_group">
		            <div class="col-sm-12">
		              <input class="btn dsj_button btn-primary" type="button" onclick="updatePassword()" value="确认">
		            </div>
		          </div>
		      </form>
		</div>
		</div>
	</div>
<script>
function updatePassword(){
	 $("#dsj_passwors_form").verify();
	$("#dsj_passwors_form").validate(function (result) {
	  	if(result){
	  		setModalContent("确定修改密码吗？","updatePasswordConfirm");
	   		  $("body").on("click","#updatePasswordConfirm",function(){
	   			  $.ajax({
	   		  			type:"post",
	   		  			async:true,
	   		  			data:$("#dsj_passwors_form").serialize(),
	   		  			dataType:"json",
	   		  			url:_ctx+"/back/person/warrant/update_password",
	   		  			success:function(resultVo){
	   		  				if(resultVo.status!=200){
	   							setErrorContent(resultVo.message);
	   						}else{
	   							location.reload();
	   						}
	   		  			}
	   		  		})
	   		  })
		}
	 })
}
</script>