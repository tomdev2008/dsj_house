var id = "";
$(function(){
	 $("body").on("click","#resetPwdModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/property/resetPassword",
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
							$("#search_btn").click();
						},2000);
					}
				}
			})
		  
	  });
});

function resetPwd(param){
	id= param;
	setModalContent("确认重置密码?","resetPwdModalConfirm");
}

function resetPwdMany(){
	var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要重置密码的账号");
		  return;
	  }
	  id=ids;
	setModalContent("确认重置密码为123456?","resetPwdModalConfirm");
}

function addProperty(){
	location=_ctx+"/back/frame/system/property/property_add";
}
function editProperty(param){
	location=_ctx+"/back/frame/system/property/property_update?id="+param;
}

function shangJia(id){
	$.ajax({
		type:"post",
		url:_ctx+"/back/system/property/shangJiaProperty",
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
}


function xiaJia(id){
	$.ajax({
		type:"post",
		url:_ctx+"/back/system/property/xiaJiaProperty",
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
}

function guanBi(id){
	$.ajax({
		type:"post",
		url:_ctx+"/back/system/property/guanBiProperty",
		data:{
			id:id
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已关闭");
				$("#search_btn").click();
			}
		}
	})
}
function tuiJian(id){
	$.ajax({
		type:"post",
		url:_ctx+"/back/system/property/tuiJianProperty",
		data:{
			id:id
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.data);
			}else{
				 setErrorContent("已推荐到首页");
				 $("#search_btn").click();
			}
		}
	})
}

function authProperty(param){
	location=_ctx+"/back/frame/system/property/property_auth?id="+param;
}


function delTJ(id){
	$.ajax({
		type:"post",
		url:_ctx+"/back/system/property/delTJ",
		data:{
			id:id
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.data);
			}else{
				 setErrorContent("取消成功");
				 $("#search_btn").click();
			}
		}
	})
}
