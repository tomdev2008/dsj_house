<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

<link rel="stylesheet" type="text/css" href="${ctx}/static/front/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/easyui/css/select_tree.css">
<script  type="text/javascript" src="${ctx}/static/front/js/jquery.min.js"></script>
<script   type="text/javascript" src="${ctx}/static/back/lib/DataTable-1.10.12/datatables.js"></script>
<script  type="text/javascript" src="${ctx}/static/back/lib/dataTables-1.10.7/plugins/integration/bootstrap/2/dataTables.bootstrap.js"></script>
<script src="${ctx}/static/back/js/common/formUtils.js"></script>
<script src="${ctx}/static/back/js/common/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/front/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/static/front/easyui/js/jquery.easyui.min.js"></script>
<script src="${ctx}/static/back/lib/validate/verify.notify.js"></script>
<script src="${ctx}/static/back/lib/validate/verify.notify.ext.js"></script>

<div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
              aria-hidden="true">&times;</span></button>
           <h4 class="modal-title" >部门管理/添加部门</h4>
</div>
 <div class="modal-body">
<form class="form-horizontal" id="addForm">
       
           		 <div class="form-group">
               		 <label  class="col-sm-2 control-label">部门名称</label>
               		 <div class="col-sm-6">
               		 	<input type="hidden" name="id" value="${department.id}"/>
                		<input name="deptName"  value="${department.deptName}" data-validate="required" style="width:100%">
                	</div>
           		 </div>
          
           		 <div class="form-group">
               		 <label  class="col-sm-2 control-label">上级部门</label>
               		 <div class="col-sm-6">
                		<input id="input_tree"  name="parentId" class="easyui-combotree" style="width:100%">
                	</div>
           		 </div>
            	<div class="form-group">
               		 <label  class="col-sm-2 control-label">描述</label>
               		 <div class="col-sm-6">
                		<textarea name="deptMess"  style="width:100%;height:100px">${department.deptMess} </textarea>
                	</div>
           		 </div>
       
</form>
</div>
 <div class="modal-footer">
                            <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">取消</button>
                           <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" onclick="saveOrUpdate()">确认</button>
</div>

<script type="text/javascript">
	$(function(){
		
		$.ajax({
  			type:"post",
  			async:true,
  			data:{
			},
  			dataType:"json",
  			url:_ctx+"/back/system/department/tree/list?id=${department.id}",
  			success:function(data){
  				$('#input_tree').combotree('loadData',
  						data );
  				 $("#input_tree").combotree("setValue", "${department.parentId}");
  				
  			}
  		})
		
  		
	})
function saveOrUpdate(){
		 $("#addForm").validate(function (result) {
			  	if(result){
			  		$.ajax({
							type:"post",
							url: _ctx+"/back/system/department/department_saveOrUpdate",
							dataType:"json",
							data:$("#addForm").serialize(),
							success:function(data){
						         if(data.status==200){
						        	 table.ajax.reload();
									 //closeModel("commonModal");
									 $("#close_btn").click();
						         }else{
						        	setErrorContent(data.errormsg);
						         }
						         
							}
						})
			  	}
			  })
}
</script>