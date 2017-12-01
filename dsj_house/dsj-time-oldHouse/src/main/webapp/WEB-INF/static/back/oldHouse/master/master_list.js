
$(function(){
	/**
	 * 全选
	 */
	checkboxAllFunc("#checkall","[name=checkitem]",document);
	
	$("#reset_btn").on("click",function(){
		$("#searchFromId").reset();
	})
	
	//点击查询重新加载
	$("#search_btn").click(function(){
	 	$("#dataTable").DataTable().ajax.reload();
	});
	
	setLayDateTwo();
});
/**
 * 增加房源
 */
function addMaster(){
	location=_ctx+"/back/frame/oldHouse/master/master_add";
}


function editMaster(id){
	location=_ctx+"/back/frame/oldHouse/master/master_add?id="+id;
}

function delMaster(id){
	var ids=new Array();
	 ids.push(id);
	delAjax(ids);
}

function upOrDownMaster(id,status){
	var msg="确认下架吗?";
	if(status==2){
		 msg="确认上架吗?";
	}
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
						//modalSuccess("发布成功");
						$("#dataTable").DataTable().ajax.reload();
					}
				}
		})
		  
	  });
}

function upDownMasters(status){
	var ids=new Array();
	 $("input[name=checkitem]:checked").each(function(){
		 ids.push($(this).val());
	});
	if(ids.length==0){
		alert("还没有选择选项！");
	}else{
		upOrDownMaster(ids,status);
	}
}

function delMasters(){
	var ids=new Array();
	var count = 0;
	 $("input[name=checkitem]:checked").each(function(){
		 if($(this).data("status")=="2"){
			 count=1;
			 return;
		 }
		 ids.push($(this).val());
	});
	if(count>0){
		alert("上架的房源不能删除！");
		return;
	}
	 
	if(ids.length==0){
		alert("还没有选择选项！");
	}else{
		delAjax(ids);
	}
}

function delAjax(ids){
	
	var msg="确认删除吗?";
	setModalContent(msg,"modalConfirm");
	 
	 $("body").on("click","#modalConfirm",function(){
		 $.ajax({
				type:"post",
				async:true,
				data:{"ids":ids},
				dataType:"json",
				url:_ctx+"/back/oldHouse/master/master_delete",
				success:function(resultVo){
					if(resultVo.status!=200){
						 setErrorContent(resultVo.message);
					}else{
						//modalSuccess("删除成功");
						$("#dataTable").DataTable().ajax.reload();
					}
				}
		})
	 });
	

}