/**
 * 
 */
var id = "";
$(function(){
	 
	 $("body").on("click","#resetPwdModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/employee/initPassword",
				data:{
					id:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							location=_ctx+"/back/frame/system/employee/employeeList";
						},2000);
					}
				}
			})
		  
	  });
	 $("body").on("click","#delEmployeeModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/employee/deleteUser",
				data:{
					ids:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							location=_ctx+"/back/frame/system/employee/employeeList";
						},2000);
					}
				}
			})
		  
	  });
 
});

function resetPwd(param){
	id= param;
	setModalContent("确认重置初始密码123456吗?","resetPwdModalConfirm");
}

function delEmployee(){
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
	setModalContent("确认删除选中账号?","delEmployeeModalConfirm");
}

function addEmployee(){
	location=_ctx+"/back/frame/system/employee/add";
}
function editEmployee(param){
	location=_ctx+"/back/frame/system/employee/edit?id="+param;
}
