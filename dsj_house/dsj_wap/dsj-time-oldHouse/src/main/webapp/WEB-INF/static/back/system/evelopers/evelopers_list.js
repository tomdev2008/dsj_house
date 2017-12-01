var id = "";
$(function(){
	 $("body").on("click","#resetPwdModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/evelopers/evelopers_reset",
				data:{
					id:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
	 $("body").on("click","#delEveloperModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/evelopers/evelopers_del",
				data:{
					ids:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
	 $("body").on("click","#accessEveloperModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/evelopers/evelopers_audit_yes",
				data:{
					ids:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
	 $("body").on("click","#refuseEveloperModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/evelopers/evelopers_audit_no",
				data:{
					ids:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
 
});

function resetPwd(param){
	id= param;
	setModalContent("确认重置密码?","resetPwdModalConfirm");
}

function delEveloper(){
	  var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要删除的账号");
		  return;
	  }
	  id=ids;
	setModalContent("确认删除选中账号?","delEveloperModalConfirm");
}
function accessOrrefuseEveloper(type){
	 var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要操作的账号");
		  return;
	  }
	  id=ids;
	  if(type==1){
		  setModalContent("确认通过选中账号?","accessEveloperModalConfirm");
	  }else{
		  setModalContent("确认驳回选中账号?","refuseEveloperModalConfirm");
	  }
}
function addEveloper(){
	location=_ctx+"/back/frame/system/evelopers/evelopers_add";
}
function editEvelopers(param){
	location=_ctx+"/back/frame/system/evelopers/evelopers_update?id="+param;
}

function authEvelopers(param){
	location=_ctx+"/back/frame/system/evelopers/evelopers_auth?id="+param;
}
