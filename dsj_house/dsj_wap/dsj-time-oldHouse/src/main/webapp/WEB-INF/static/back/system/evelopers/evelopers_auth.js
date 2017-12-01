var id="";
function cancelFun(){
	location=_ctx+"/back/frame/system/evelopers/evelopers_auth_list"
}
function authYesEveloper(param){
	id=param;
	setModalContent("确认通过选中账号?","accessEveloperModalConfirm");
}
function authNoEveloper(param){
	id=param;
	setModalContent("确认驳回选中账号?","refuseEveloperModalConfirm");
}
$(function(){
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
						cancelFun();
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
						cancelFun();
					}
				}
			})
		  
	  });
})