
$("body").on("click","#publishModal",function(){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/wapIndex/publishSwiper",
		data:{
			id:id
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				location=_ctx+"/back/frame/wapIndex/indexManage";
			}
		}
	})
  
});
$("body").on("click","#publishLabelModal",function(){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/wapIndex/publishLabel",
		data:{
			id:id
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				location=_ctx+"/back/frame/wapIndex/indexManage";
			}
		}
	})
  
});
function beforeUpload()
{
    loading_szyq("上传中...",true);
}

function afterUpload()
{
    loading_szyq(false);
}
function show(param){
	index = param;
	$("#authModal").modal({
		backdrop: true,
	    keyboard: false,
	    show: true
    });
	
	$("#photoShowImg").removeAttr('src');
	$("#photoFileUrl").val("");
	$("input[name='url']").val("");
	$.ajax({
			type:"post",
			async:true,
			data:{id:param},
			dataType:"json",
			url:_ctx+"/back/frame/wapIndex/getThisSwiper",
			success:function(result){
				if(result.status!=200){
				 setErrorContent(result.message);
				}else{
					$("#photoShowImg").attr('src',result.data.imageUrl);
					$("#photoFileUrl").val(result.data.imageUrl);
					$("input[name='url']").val(result.data.url);
					$('#authModal').on('shown.bs.modal', function (e) {
						singleUpload("photoFilePicker","photoFileUrl","photoShowImg",beforeUpload,afterUpload);
					})
				}
			}
		})
	
	    

}
function showLabel(param){
	labelIndex = param;
	reset();
	$("#labelModel").modal({
		backdrop: true,
	    keyboard: false,
	    show: true
    });
	$("input[name='name']").val('');
}
function update(){
	 $("#authForm").validate(function (result) {
		  	if(result){
		  		$('#authModal').modal('hide')
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#authForm").serialize()+"&id="+index,
		  			dataType:"json",
		  			url:_ctx+"/back/frame/wapIndex/updateSwiper",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							//setErrorContent(result.message);
							//$("img[name='swiper"+index+"']").attr("src",$("input[name=imageUrl]").val());
							location=_ctx+"/back/frame/wapIndex/indexManage";
							
						}
		  			}
		  		})
		  	}
	 })
}

function updateLabel(){
	 $("#labelForm").validate(function (result) {
		  	if(result){
		  		$('#labelModel').modal('hide')
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#labelForm").serialize()+"&id="+labelIndex,
		  			dataType:"json",
		  			url:_ctx+"/back/frame/wapIndex/updateLabel",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							//setErrorContent(result.message);
							//$("#label"+labelIndex).val($("input[name='name']").val());
							location=_ctx+"/back/frame/wapIndex/indexManage";
							
							
						}
		  			}
		  		})
		  	}
	 })
}
function publishSwiper(param){
	id = param;
	setModalContent("确认发布?","publishModal");
}



function publishLabel(param){
	id = param;
	setModalContent("确认发布?","publishLabelModal");
}

$('#typeModel').on('hidden.bs.modal', function () {
	reset();
	console.log('reset');
	})
var allType=[]
function showType(param){
	
	var c_includeType='';
	var c_weight='';
	labelIndex = param;
	$.ajax({
		type:"post",
		data:{id:labelIndex},
		async:true,
		dataType:"json",
		url:_ctx+"/back/frame/wapIndex/getLabelById",
		success:function(result){
			if(result.status!=200){
			 setErrorContent(result.message);
			}else{	
				var weightArr=[];
				var includeTypeArr=[];
				var c_includeType=result.data.includeType;
				var c_weight=result.data.weight;
				$("#typeModel").modal({
					backdrop: true,
				    keyboard: false,
				    show: true
			    });
				//
//				if(c_weight.lenght>0){
//					weightArr = c_weight.split(",");
//					for(var i=0;i<weightArr.length;i++){
//						
//					}
//				}
				if(c_includeType!=''){
					includeTypeArr = c_includeType.split(",");
					weightArr = c_weight.split(",");
					
					for(var i=0;i<includeTypeArr.length;i++){
						$("#table [type='radio'][value='"+includeTypeArr[i]+"']").prop("checked",true);
						for(var j=0;j<allType.length;j++){
							if(includeTypeArr[i]==allType[j].id){
								$("#weightInput"+allType[j].groupId).prop("disabled",false).attr("data-validate","required").val(weightArr[i]);
							}
						}
					}
				}
				
				
				
			}
		}
	})
	

}

$(function(){
	var groupLength = 0;
	$(document).on("change","#table [type='radio']",function(){
		$(this).parents('tr').find("[type='text']").prop("disabled",false).attr("data-validate","required");
	})
	$.ajax({
			type:"get",
			async:true,
			dataType:"json",
			url:_ctx+"/back/frame/wapIndex/getTypeGroup",
			success:function(result){
				if(result.status!=200){
				 setErrorContent(result.message);
				}else{
					groupLength = result.data.length;
					for(var i=0;i<result.data.length;i++){
						var temp = "group"+(i+1);
						$("#table tbody").append("<tr><td>"+result.data[i]+"</td><td><input id='weightInput"+(i+1)+"' class='form-control' name='weight' type='text' disabled></td><td id='"+temp+"'></td></tr>")
					}		
					$.ajax({
						type:"get",
						async:true,
						dataType:"json",
						url:_ctx+"/back/frame/wapIndex/getType",
						success:function(result){
							if(result.status!=200){
							 setErrorContent(result.message);
							}else{		
								var types = result.data;
								allType = result.data;
								for(var i=0;i<types.length;i++){
									for(var j = 0;j<groupLength;j++){
										if(types[i].groupId==(j+1)){
											var temphtml = $("#group"+(j+1)).html();
											temphtml = temphtml+"<label><input name='includeType"+(j+1)+"' type='radio' value="+types[i].id+">"+types[i].name+"</label>"
											$("#group"+(j+1)).html(temphtml);
										}
									}
								}
								
							}
						}
					})
				}
			}
	})

})

function reset(){
	$("#table [type='radio']").attr("checked",false);
	$("#table [type='text']").attr("disabled",true).removeAttr("data-validate").val('');
}


function updateTypes(){
//	$("#typesForm").validate(function (result) {
//		alert(result);
//	  	if(result){
//	  		$('#typeModel').modal('hide')
	
	var len = $("#table [type='text'][data-validate='required']").length;
	if(len>0){
		var sum = 0
		for(var i=0;i<len;i++){
			var value = $("#table [type='text'][data-validate='required']").eq(i).val();
			if(value==''){
				alert('请输入权重值');
				return;
			}else{
				if(/^\d+$/.test(value)){
					sum=parseInt(sum)+parseInt(value);
					console.log(sum);
				}else{
					alert('权重值只能为整数')
					return;
				}
			}
		}
		if(sum==10){
			var tempLen = $('input:radio:checked').length;
			var includeType = '';
			for(var k=0;k<tempLen;k++){
				includeType += $('input:radio:checked').eq(k).val()+',';
			}
			includeType=includeType.slice(0,includeType.length-1);
			
			$('#typeModel').modal('hide')
			$.ajax({
	  			type:"post",
	  			async:true,
	  			data:$("#typesForm").serialize()+"&id="+labelIndex+"&includeType="+includeType,
	  			dataType:"json",
	  			url:_ctx+"/back/frame/wapIndex/updateTypes",
	  			success:function(result){
	  				if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						//setErrorContent(result.message);
						//$("#label"+labelIndex).val($("input[name='name']").val());
						location=_ctx+"/back/frame/wapIndex/indexManage";
						
						
					}
	  			}
	  		})
		}else{
			alert('权重值相加应该为10');
		}
	}else{
		alert("请至少选择一项");
	}
	
	  		
//	  	}
// })
}
