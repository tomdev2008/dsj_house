
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

function recycleMaster(id){
	var ids=new Array();
	 ids.push(id);
	recycleAjax(ids);
}

function recycleMasters(){
	var ids=new Array();
	 $("input[name=checkitem]:checked").each(function(){
		 ids.push($(this).val());
	});
	if(ids.length==0){
		alert("还没有选择选项！");
	}else{
		recycleAjax(ids);
	}
}

function recycleAjax(ids){
	 $.ajax({
			type:"post",
			async:true,
			data:{"ids":ids},
			dataType:"json",
			url:_ctx+"/back/oldHouse/recycle/master/master_recovery",
			success:function(resultVo){
				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					//modalSuccess("发布成功");
					$("#dataTable").DataTable().ajax.reload();
				}
			}
	})
}