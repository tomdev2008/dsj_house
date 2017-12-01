$(function(){
	$(".dsj_img_single_action").on("click",function(){
		$.ajax({
			type:"post",
			async:true,
			data:{"id":$(this).next().val()},
			dataType:"json",
			url:_ctx+"/back/houseDirectory/set_cover",
			success:function(resultVo){
				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					 location.reload() ;
				}
			}
		})
	});
	
	$("body").on("click","#deleteByIdModalConfirm",function(){
		var ids =new Array();
		$("input[name=checkbox_name]:checked").each(function(){
			console.info($(this).val());
				ids.push($(this).val());
			});
		$.ajax({
			type:"post",
			async:true,
			data:{"ids":ids},
			dataType:"json",
			url:_ctx+"/back/houseDirectory/delete_directory_image",
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

function publishBuilding(){
	//验证必须字段
	$.ajax({
		type:"post",
		async:true,
		data:{"id":$("#objId").val()},
		dataType:"json",
		url:_ctx+"/back/houseDirectory/publish_building",
		success:function(resultVo){
			if(resultVo.status!=200){
				setErrorContent(resultVo.message);
			}else{
				setErrorContent("发布成功");
				//location=_ctx+"/back/frame/houseDirectory/";
			}
		}
	})
}

function returnList(){
	location=_ctx+"/back/frame/houseDirectory/";
}

function deleteById(){
	var ids =new Array();
	$("input[name=checkbox_name]:checked").each(function(){
		console.info($(this).val());
			ids.push($(this).val());
		});
	if(ids.length==0){
		setErrorContent("请选择要删除的图片");
		return false;
	}
	setModalContent("确定删除图片吗?","deleteByIdModalConfirm");
}