<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

			<div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                           <h4 class="modal-title">添加角色</h4>
                       </div>
                       <div class="modal-body">
    							 <form id="roleFrom" class="form-horizontal">
							 		   <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label">角色名称：</label>
										    <div class="col-sm-9">
										      <input type="text" placeholder="角色名称" class="form-control" name="name" id="roleName" data-validate="required" style="width:140">
										    </div>
							          </div>
							          <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label0">角色编码：</label>
										    <div class="col-sm-9">
										      <input type="text" placeholder="角色编码" name="nameCode" class="form-control" id="nameCode" data-validate="required" style="width:140">
										    </div>
							          </div>
							              
							       </form> 
                       </div>
                       <div class="modal-footer">
                            <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">取消</button>
                           <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" onclick="addRole()" id="add">确认</button>
                       </div>

