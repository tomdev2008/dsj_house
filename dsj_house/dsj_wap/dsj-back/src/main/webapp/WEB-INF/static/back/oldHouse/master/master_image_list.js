$(function(){
	
	$(".dsj_img_single_action").on("click",function(){
		$.ajax({
			type:"post",
			async:true,
			data:{"id":$(this).next().val()},
			dataType:"json",
			url:_ctx+"/back/oldHouse/master/set_cover",
			success:function(resultVo){
				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					 location.reload() ;
				}
			}
		})
	});
})

function goList(){
	location.href=_ctx+"/back/frame/oldHouse/master";
}

function saveOrUpdate(id,status){
	var msg="确认发布吗?";
	setModalContent(msg,"modalConfirm");
	 
	 $("body").on("click","#modalConfirm",function(){
		 $.ajax({
				type:"post",
				async:true,
				data:{"ids[]":id,"status":status},
				dataType:"json",
				url:_ctx+"/back/oldHouse/master/master_upDown",
				success:function(resultVo){
					if(resultVo.status!=200){
						 setErrorContent(resultVo.message);
					}else{
						modalSuccess("发布成功");
						location.href=_ctx+"/back/frame/oldHouse/master";
					}
				}
		})
	 });
	
}

function cancel(){
	var ids=new Array();
	 $("input[name=checkbox_name]:checked").each(function(){
		 console.info($(this).val());
		 ids.push($(this).val());
	});
	 $.ajax({
			type:"post",
			async:true,
			data:{"ids":ids},
			dataType:"json",
			url:_ctx+"/back/oldHouse/master/delete_master_image",
			success:function(resultVo){
				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					 location.reload() ;
				}
			}
	})
}