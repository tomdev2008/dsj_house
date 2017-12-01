<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			员工账号新增
		</h1>
		<ol class="breadcrumb">
			<li>员工账号管理</li>
			<li>新增 </li>
		</ol>
		<form class="form-horizontal" id="employeeAddForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">员工编号</label>
		                <div class="col-sm-6">
		                  <input name="empNum" type="text" class="form-control" value="${empNum}" data-validate="required" readonly="readonly">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">真实姓名</label>
		                <div class="col-sm-6">
		                  <input name="realName" type="text" class="form-control" data-validate="maxLength20,required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">联系电话</label>
		                <div class="col-sm-6">
		                  <input name="tellPhone" type="text" class="form-control" data-validate="required,phone_new">
		                </div>
		            </div>
		            <div class="form-group">
			                <label  class="col-sm-2 control-label">默认角色</label>
			                <div class="col-sm-6" data-maxroleid="${fn:length(roles) }">
								<c:forEach items="${roles}" var="role" varStatus="status">
				                	<input name="role" type="checkbox" value="${role.id}" data-validate="rolesRequired#a${status.count}" onclick="authCheckbox()"/>${role.name}
			                </c:forEach>                  
			                </div> 
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">出生年份</label>
		                <div class="col-sm-6">
		                  <input name="birthday" type="text" class="form-control" id="LAY_demorange_zs">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">学历</label>
		                <div class="col-sm-6">
		                  <input name="education" data-validate="maxLength20" type="text" class="form-control"  >
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">专业背景</label>
		                <div class="col-sm-6">
		                  <input name="major" data-validate="maxLength20" type="text" class="form-control">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">技能</label>
		                <div class="col-sm-6">
		                  <input name="skilled" data-validate="maxLength20" type="text" class="form-control">
		                </div>
		            </div>      
		             <div class="text-center">
		                     <button class="btn btn-primary" type="button" onclick="addEmployee()">确认</button>
		                     <button class="btn btn-default" type="button" onclick="cancel()">取消</button>
		             </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script src="${ctx}/static/back/system/employee/employee_edit.js"></script>
