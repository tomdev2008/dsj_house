function saveAddFlat(){
	 $("#flatAddForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#flatAddForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/flat/flat_add",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							location=_ctx+"/back/frame/system/flat/to_flat_list"
						}
		  			}
		  		})
		  	}
	 })
}
function saveUpdateFlat(){
	 $("#flatUpdateForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#flatUpdateForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/flat/flat_update",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							location=_ctx+"/back/frame/system/flat/to_flat_list"
						}
		  			}
		  		})
		  	}
	 })
}

var _audit ="";

$(function(){
     singleUpload("businessLicencePhotoFilePicker","businessLicencePhotoFileUrl","businessLicencePhotoShowImg",beforeUpload,afterUpload);
     singleUpload("undertakingFilePicker","undertakingFileUrl","undertakingShowImg",beforeUpload,afterUpload);
     
     $("body").on("click","#accessFlatModalConfirm",function(){
    	 	var id = $("#userId").val();
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/flat/flats_audit_"+_audit,
				data:{
					ids:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						location=_ctx+"/back/frame/system/flat/to_flat_audit_list";
					}
				}
			})
		  
	  });
})

function flatAccess(){
	_audit = "yes";
	setModalContent("确认通过审核?","accessFlatModalConfirm");
}
function flatRefuse(){
	_audit = "no";
	setModalContent("确认要驳回?","accessFlatModalConfirm");
}

 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }

 function cancelFun(){
	location=_ctx+"/back/frame/system/flat/to_flat_list"
}
 
 function cancelFun_audit(){
		location=_ctx+"/back/frame/system/flat/to_flat_audit_list"
	}
 
 function authareaTwoIdFun(){
	if($("#areaTwoId").val()==""){
		$("#authareaTwoId").val("");
	}else{
		$("#authareaTwoId").val($("#areaTwoId").val());
	}
}