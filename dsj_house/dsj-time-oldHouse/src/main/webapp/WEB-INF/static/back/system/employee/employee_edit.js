/**
 * 
 */


function saveEmployee(){
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
							setTimeout(function(){
								location=_ctx+"/back/frame/system/employee/employeeList";
							},2000);
						}
		  			}
		  		})
		  	}
	 })
}
function addEmployee(){
	 $("#employeeAddForm").validate(function (res) {
		  	if(res){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#employeeAddForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/system/employee/addEmployee",
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
		  	}
	 })
}

function cancel(){
	location=_ctx+"/back/frame/system/employee/employeeList"
}

function authCheckbox(){
	var count = $("input[name='role']:checked").length;
	if(count!=0){
		$("#authCount").val(1)
	}else{
		$("#authCount").val("")
	}
}