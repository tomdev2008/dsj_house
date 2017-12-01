$(function(){
	$(".dsj_img_single_action").on("click",function(){
		if($("#showOrigin").val()=="yes"){
			setErrorContent("查看下不能更改");
			return false;
		}
		$.ajax({
			type:"post",
			async:true,
			data:{"id":$(this).next().val()},
			dataType:"json",
			url:_ctx+"/back/rentHouse/general/origin/set_cover",
			success:function(resultVo){
				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					 location.reload() ;
				}
			}
		})
	});
	
	$("body").on("click","#publishModalConfirm",function(){
		var _id = new Array();
		_id.push($("#objId").val());
		$.ajax({
			type:"post",
			async:true,
			data:{
				"id" : _id,
				"status" : 2
			},
			dataType:"json",
			url:_ctx+"/back/frame/rentHouse/general/origin/origin_upDown",
			success:function(resultVo){
				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					location = _ctx+"/back/frame/rentHouse/general/origin";
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
			url:_ctx+"/back/rentHouse/general/origin/delete_image",
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

function saveOrUpdate(){
	setErrorContent("保存成功");
}

function saveOrUpdateNext(){
	setModalContent("确定发布该房源吗?","publishModalConfirm");
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