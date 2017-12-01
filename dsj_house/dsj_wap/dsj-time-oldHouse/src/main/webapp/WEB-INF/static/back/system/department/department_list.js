

function toAdd(){
	openCommonModal(_ctx+'/back/system/department/department_add','500')
}

function edit(id){
	openCommonModal(_ctx+'/back/system/department/department_edit?id='+id,'500')
}
function del(id){
	
	setModalContent("确认删除部门吗?","delModalConfirm");
	$("body").on("click","#delModalConfirm",function(){
		  $.ajax({
				type:"post",
				url:_ctx+"/back/system/department/delete",
				dataType:"json",
				data:{
					id:id
				},
				success:function(data){
					 if(data.status==200){
			        	 table.ajax.reload();
			         }else{
			        	 setErrorContent(data.errormsg);
			         }
				}
			})
	  });
}

