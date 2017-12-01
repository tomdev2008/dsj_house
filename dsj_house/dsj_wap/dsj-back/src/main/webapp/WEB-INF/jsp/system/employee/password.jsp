<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			个人信息修改
		</h1>
		<div class="row" style="margin-bottom:35px;margin-left:200px">
			   <button class="dsj_btn btn btn-default" type="button" onclick="goEmpInfoPage()">个人信息</button>
			   <button class="dsj_btn btn dsj_btn_green" type="button">修改密码</button>
		</div>
		<form class="form-horizontal" id="passwordForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">原密码</label>
		                <div class="col-sm-6">
		                  <input name="oldPwd" type="password" class="form-control" data-validate="required">
		                  <input type="hidden" name="userId" value="${userId}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">新密码</label>
		                <div class="col-sm-6">
		                  <input name="newPwd" type="password" class="form-control" data-validate="required,againPass#start">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">确认密码</label>
		                <div class="col-sm-6">
		                  <input name="reNewPwd" type="password" class="form-control" data-validate="required,againPass#end">
		                </div>
		            </div>    
		             <div class="text-center">
		                     <button class="btn btn-primary" type="button" onclick="modify()">确认</button>
		                     <button class="btn btn-default" type="button" onclick="cancel()">取消</button>
		             </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script type="text/javascript">
function modify(){
	 $("#passwordForm").validate(function (res) {
		  	if(res){
	  			$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#passwordForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/system/employee/modifyPassWord",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
							setTimeout(function(){
								location=_ctx+"/login/logout"
							},2000);
							
						}
		  			}
		  		})
		  	}
	 })
}
function goEmpInfoPage(){
	window.location=_ctx+"/back/frame/system/employee/editInfo";
}
</script>
