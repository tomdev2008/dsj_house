//去支付
function gotoPay(self,orderId){
	$(self).attr("disabled",true);
    $.ajax({
        type:"post",
        async:true,
        data:{
            orderId : orderId
        },
        dataType:"json",
        url:_ctx+"/front/warrant/gotoPay",
        success:function(result){
        	if(result.status == 200){
				window.location.href=result.data;
			}else{
				show_box(2,result.data);
			}
        }
    })
}

//重新下单
function gotoReOrder(self,orderDetailId){
	$(self).attr("disabled",true);
    $.ajax({
        type:"post",
        async:true,
        data:{
        	orderDetailId : orderDetailId
        },
        dataType:"json",
        url:_ctx+"/front/personCenter/order/gotoReOrder",
        success:function(result){
        	if(result.status == 200){
        		var resData = result.data;
        		location=_ctx+"/front/warrant/orderComfirm?userId="+ resData.userId
    				+"&skuId="+ resData.skuId +"&areaCodeThree="+ resData.areaCodeThree;
			}else{
				show_box(2,result.data);
			}
        }
    })
}

//确认完成
function gotoSucess(self,orderId){
	show_box(1,"服务已完成？");
	gotoSucessConfim(orderId);
}
function gotoSucessConfim(orderId){
	$("#confimYes").one("click",function(){
		$.ajax({
	        type:"post",
	        async:true,
	        data:{
	        	orderId : orderId
	        },
	        dataType:"json",
	        url:_ctx+"/front/personCenter/order/gotoSucess",
	        success:function(result){
	        	$("#cancelBtn").click();
	        	if(result.status == 200){
	        		location.reload();
				}else{
					show_box(2,result.data);
				}
	        }
	    })
	})
}

//申请退款
function gotoRefund(self,orderId){
	var refundReason = "我要退款";
	show_box(5,"要退款吗？");
	gotoRefundConfim(orderId);
}
function gotoRefundConfim(orderId){
	
	
	$("#tuikuanBtn").on("click",function(){
		$("#tuikuanBtn").attr("disabled", true);
		if($("#inlineRadio5").is(":checked")==true){
			var mtxt = $("#refund_text_1").val();
			if( mtxt.trim().length == 0){
				$("#tuikuanBtn").removeAttr("disabled");
				return false;
			}
		}
		var refundtype = 1;
		var refundReason = "重复下单";
		if($("#inlineRadio1").is(":checked")==true){
			refundReason = "重复下单";
			refundtype = 1;
		}else if($("#inlineRadio2").is(":checked")==true){
			refundReason = "误下单";
			refundtype = 2;
		}else if($("#inlineRadio3").is(":checked")==true){
			refundReason = "不想买了";
			refundtype = 3;
		}else if($("#inlineRadio4").is(":checked")==true){
			refundReason = "服务不满意";
			refundtype = 4;
		}else if($("#inlineRadio5").is(":checked")==true){
			refundReason = $("#refund_text_1").val();
			refundtype = 5;
		}
		
		$.ajax({
	        type:"post",
	        async:true,
	        data:{
	        	orderId : orderId,
	        	refundReason : refundReason,
	        	refundtype : refundtype
	        },
	        dataType:"json",
	        url:_ctx+"/front/personCenter/order/gotoRefund",
	        success:function(result){
	        	$("#cancelBtn").click();
	        	if(result.status == 200){
	        		location.reload();
				}else{
					show_box(2,result.data);
				}
	        }
	    })
	});
}

//取消订单
function gotoCancel(self,orderId){
	show_box(1,"是否取消订单？");
	gotoCancelConfim(orderId);
}
function gotoCancelConfim(orderId){
	$("#confimYes").one("click",function(){
		$.ajax({
	        type:"post",
	        async:true,
	        data:{
	        	orderId : orderId
	        },
	        dataType:"json",
	        url:_ctx+"/front/personCenter/order/gotoCancel",
	        success:function(result){
	        	$("#cancelBtn").click();
	        	if(result.status == 200){
	        		location.reload();
				}else{
					show_box(2,result.data);
				}
	        }
	    })
	})
}

/**
 * 去点评
 */
function gotoAddReview(self,orderId){
	location=_ctx+"/front/warrant/addComment?orderId="+orderId;
}

/**
 * 查看点评
 */
function gotoShowReview(self,orderId){
	location=_ctx+"/front/warrant/checkComment?orderId="+orderId;
}

//输入字数控制
function checkNum(self, maxLen) {
	var num = maxLen-($(self).val().length);
	if(num > -1){
		$(self).closest(".select_kind").find(".textNum").text(num);
	}
	if(num<=0){
		$(self).val($(self).val().substring(0,maxLen));
	}
} 