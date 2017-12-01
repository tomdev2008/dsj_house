var ids = [];
$(function(){
	 $("body").on("click","#delNewHousesModalConfirm",function(){
		 $("#popup_box").show();
			$.ajax({
				type:"post",
				url:_ctx+"/back/newHouse/edit/newHouse_del",
				data:JSON.stringify(ids),
				datatype:"json",
				contentType: 'application/json',  
				success:function(result){
					$("#popup_box").hide();
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
	 $("body").on("click","#downNewHouseModalConfirm",function(){
		 $("#popup_box").show();
			$.ajax({
				type:"post",
				url:_ctx+"/back/newHouse/edit/newHouse_down",
				data:JSON.stringify(ids),
				datatype:"json",
				contentType: 'application/json',  
				success:function(result){
					$("#popup_box").hide();
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
 
});

function delNewHouses(){
	var flag = true;
	  ids = new Array();
	  $("input[name=checkitem]:checked").each(function(){
		  if($(this).attr("isPutSale")==1&&$(this).attr("authStatus")==1){
			  ids.push($(this).val());
		  }else{
			  flag = false;
			  return false;
		  }
		  
	  });
	  if(flag){
		  if(ids.length == 0){
			  setErrorContent("请选择要删除的楼盘");
			  return;
		  }else{
			  setModalContent("确认删除选中楼盘?","delNewHousesModalConfirm");
		  }
	  }else{
		  setErrorContent("只有未曾审核过的楼盘可以删除，即无审核记录的楼盘可以删除。");
	  }
	 
}

function downNewHouses(){
	var flag = true;
	  ids = new Array();
	  $("input[name=checkitem]:checked").each(function(){
		  if($(this).attr("isPutSale")==2&&$(this).attr("authStatus")!=2){
			  ids.push($(this).val());
		  }else{
			  flag = false;
			  return false;
		  }
	  });
	  if(flag){
		  if(ids.length == 0){
			  setErrorContent("请选择要下架的楼盘");
			  return;
		  }else{
			  setModalContent("确认下架选中楼盘?","downNewHouseModalConfirm");
		  }
	  }else{
		  setErrorContent("未上架或待审核的楼盘不可下架");
	  }
}

function addHouse(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_add";
}
function editNewHouse(param){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_update?id="+param;
}
function cancelFun(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_list";
}
