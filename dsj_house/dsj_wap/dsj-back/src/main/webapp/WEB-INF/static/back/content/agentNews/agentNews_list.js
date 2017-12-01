/**
 * 
 */
var id = "";
$(function(){
	 
	 $("body").on("click","#deleteModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/content/agentNews/delete",
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

 
});


function del(param){
	id= param;
	setModalContent("确认删除?","deleteModalConfirm");
}

function delMany(){
	  var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择删除的动态");
		  return;
	  }
	  id=ids;
	setModalContent("确认删除?","deleteModalConfirm");
}
function detail(id){
	location=_ctx+"/back/frame/content/agentNews/detail?id="+id;
}

