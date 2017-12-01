<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			个人信息修改
		</h1>
		<div class="row" style="margin-bottom:35px;margin-left:200px">
			   <button class="dsj_btn btn dsj_btn_green" type="button">个人信息</button>
			   <button class="dsj_btn btn btn-default" type="button" onclick="goPasswordPage()">修改密码</button>
		</div> 
		<form class="form-horizontal" id="employeeEditForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">账号</label>
		                <div class="col-sm-6">
		                  <input name="empNum" type="text" class="form-control" value="${emp.empNum}" data-validate="required" readonly="readonly">
		                  <input type="hidden" name="userId" value="${emp.userId}">
		                  <input type="hidden" name="id" value="${emp.id}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">真实姓名</label>
		                <div class="col-sm-6">
		                  <input name="realName" type="text" class="form-control" value="${emp.realName}" data-validate="required" readonly="readonly">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">联系电话</label>
		                <div class="col-sm-6">
		                  <input name="tellPhone" type="text" class="form-control" data-validate="required,phone_new" value="${emp.tellPhone}" readonly="readonly">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">默认角色</label>
		                <div class="col-sm-6" data-maxroleid="${fn:length(roles) }">
			                <c:forEach items="${roles}" var="role" varStatus="status">
				                <c:if test="${role.isChecked == '1'}">
				                	<label><input name="role" type="checkbox" value="${role.id}" disabled="disabled" checked="checked"  onclick="authCheckbox()"/>${role.name}</label>
				                </c:if >
				                <c:if test="${role.isChecked == '0'}">
				                	<label><input name="role" type="checkbox" value="${role.id}"   disabled="disabled" data-validate="rolesRequired#a${status.count}"onclick="authCheckbox()"/>${role.name}</label>
				                </c:if >				               	
			                </c:forEach>
		                </div>
		            </div>
		          
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">出生年份</label>
		                <div class="col-sm-6">
		                  <input name="birthday" type="text" class="form-control" id="LAY_demorange_zs" data-validate="required" value="${emp.birthday}">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">学历</label>
		                <div class="col-sm-6">
		                  <input name="education" type="text" class="form-control" data-validate="required" value="${emp.education}" >
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">专业背景</label>
		                <div class="col-sm-6">
		                  <input name="major" type="text" class="form-control" data-validate="required" value="${emp.major}">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">技能</label>
		                <div class="col-sm-6">
		                  <input name="skilled" type="text" class="form-control"  data-validate="required" value="${emp.skilled}">
		                </div>
		            </div>      
		             <div class="text-center">
		                     <button class="btn btn-primary" type="button" onclick="saveInfo()">确认修改</button>
		             </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script type="text/javascript">
function saveInfo(){
	 $("#employeeEditForm").validate(function (res) {
		  	if(res){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#employeeEditForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/system/employee/editEmployee",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
						}
		  			}
		  		})
		  	}
	 })
	 
	 
}

function goPasswordPage(){
	 window.location=_ctx+"/back/frame/system/employee/passwordPage"
}
</script>
