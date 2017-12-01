var ids=[];
var id="";
function addHouseType(param){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_addHouseType?id="+param;
}
function findOneLayOut(param){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_updateNewHouseType?id="+param;
}
function cancelLayout(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_list";
}

function submitAddNewHouse(param){
	
		$("#popup_box").show();
		$.ajax({
			type:"post",
			url:_ctx+"/back/frame/newHouse/edit/submitAddNewHouse?id="+param,
			data:{id:param},
			dataType:"json",
			success:function(result){
				if(result.status!=200){
					$("#popup_box").hide();
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/newHouse/edit";
				}
			}
		})
	
}

function delNewHouseType(param){
	id=param;
	 ids = new Array();
	  $("input[name=layoutName]:checked").each(function(){
		  ids.push($(this).val());
	  });
	  if(ids.length == 0){
		  setErrorContent("请选择要删除的户型");
		  return;
	  }else{
		  setModalContent("确认删除选中户型?","delNewHouseTypeConfirm");
	  }
}

$(function(){
	 $("body").on("click","#delNewHouseTypeConfirm",function(){
		 $("#popup_box").show();
			$.ajax({
				type:"post",
				url:_ctx+"/back/newHouse/edit/newHouseType_del",
				data:JSON.stringify(ids),
				datatype:"json",
				contentType: 'application/json',  
				success:function(result){
					if(result.status!=200){
						$("#popup_box").hide();
						 setErrorContent(result.message);
					}else{
						location=_ctx+"/back/frame/newHouse/edit/newHouse_houseTypeList?newHouseId="+id;
					}
				}
			})
		  
	  });
});