
function orderComfirm(user_id){
	location=_ctx+"/front/warrant/orderComfirm?userId="+user_id
		+"&skuId="+$("#skuId").val()+"&areaCodeThree="+$("#areaCodeThree").val();
}
function toFwUserDetail(id){
	location=_ctx+"/front/warrant/fwuserDetail?id="+id+"&spuId="+$("#spuId").val()+"&areaCodeThree="+$("#areaCodeThree").val();
}