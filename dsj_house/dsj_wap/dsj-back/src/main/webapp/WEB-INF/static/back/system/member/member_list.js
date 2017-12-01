/**
 * 
 */
var id = "";
$(function(){
	 
	 $("body").on("click","#blackModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/member/black",
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
							$("#search_btn").click();
						},2000);
					}
				}
			})
		  
	  });
	 $("body").on("click","#removeBlackModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/member/removeBlack",
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

function removeBlack(param){
	id= param;
	setModalContent("确认解除拉黑?","removeBlackModalConfirm");
}
function black(param){
	id= param;
	setModalContent("确认拉黑选中账号?","blackModalConfirm");
}

function blackMany(){
	  var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择拉黑的账号");
		  return;
	  }
	  id=ids;
	setModalContent("确认拉黑选中账号?","blackModalConfirm");
}


