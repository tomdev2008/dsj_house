 function mysubmit() {
	 $("#submitId").attr("disabled",true);
		$.post(_ctx+'/back/roleFunction/updateRoleFunction',$("#allRole_form").serialize(),function(data){
			if(data.status==0){
				 setErrorContent(data.message);
				 $("#submitId").removeAttr("disabled");
				setTimeout(function(){history.go(-1);}, 1500)
			}else{
				$("#submitId").removeAttr("disabled");
				 setErrorContent(data.message);
			}
		})
	}
 
function closeFunc(){
	setTimeout(function(){history.go(-1);}, 500)
}

function selectAll(obj){
	if($(obj).is(":checked")){
		$(obj).parent().parent().parent().find("input[type=checkbox]").prop("checked",true);
	}else{
		$(obj).parent().parent().parent().find("input[type=checkbox]").prop("checked",false);
	}
}