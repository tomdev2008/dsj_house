function addMainCommunityName(){
	if($("input[name='mainCommunity']").length<5){
		$("#mainCommunity").append('<input type="text" maxlength="20" style="margin-top: 5px;" name="mainCommunity" class="form-control">');
	}
}

function removeMainCommunityName(){
	if($("input[name='mainCommunity']").length>1){
		$("input[name='mainCommunity']:last").remove();
	}
}
function addSellBuildingName(){
	if($("input[name='sellBuilding']").length<5){
		$("#sellBuilding").append('<input type="text" maxlength="20" style="margin-top: 5px;" id="sellBuilding" name="sellBuilding" class="form-control" >');
	}
	
}

function removeSellBuildingName(){
	if($("input[name='sellBuilding']").length>1){
		$("input[name='sellBuilding']:last").remove();
	}
}





function saveAddAgent(obj){
	 $("#agentAddForm").validate(function (result) {
	 	var data = $("#agentAddForm").serialize();
    	if ($("input[name='isSellNewHouse']").is(':checked')) {   
    		if ($("select[name='sellBuilding']").val()==null||$("select[name='sellBuilding']").val()=='') {   
			  	data = data+"&sellBuilding=";
		  	}
	  	}else{
		  	data = data+"&isSellNewHouse="+0+"&sellBuilding=";
	  	}
    	if ($("select[name='mainCommunity']").val()==null||$("select[name='mainCommunity']").val()=='') {   
		  	data = data+"&mainCommunity="
	  	}  
    	 
	  	
		  	if(result){
		  		$(obj).prop('disabled',true);
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:data,
		  			dataType:"json",
		  			url:_ctx+"/back/frame/system/agent/addAgent",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
							 $(obj).prop('disabled',false);
						}else{
							setErrorContent(result.message);
							setTimeout(function(){
								location=_ctx+"/back/frame/system/agent/agentList";
							},2000);
							
						}
		  			}
		  		})
		  	}
	 })
}
function saveEditAgent(){
	$("#agentEditForm").validate(function (result) {

	 	var data = $("#agentEditForm").serialize();
    	if ($("input[name='isSellNewHouse']").is(':checked')) {   
		  	if ($("select[name='sellBuilding']").val()==null||$("select[name='sellBuilding']").val()=='') {   
			  	data = data+"&sellBuilding=";
		  	}
	  	}else{
		  	data = data+"&isSellNewHouse="+0+"&sellBuilding=";
	  	}
    	console.log($("select[name='mainCommunity']").val());
    	if ($("select[name='mainCommunity']").val()==null||$("select[name='mainCommunity']").val()=='') {   
		  	data = data+"&mainCommunity="
	  	}  
    	 
	  	
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:data,
		  			dataType:"json",
		  			url:_ctx+"/back/system/agent/updateAgent",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
							setTimeout(function(){
								location=_ctx+"/back/frame/system/agent/agentList";
							},2000);
						}
		  			}
		  		})
		  	}
	 })
}

$(function(){
     singleUpload("avatarPhotoFilePicker","avatarPhotoFileUrl","avatarPhotoShowImg",beforeUpload,afterUpload);//最后一个参数代表是头像，传任意值
     singleUpload("idCardPhotoFilePicker","idCardPhotoFileUrl","idCardPhotoShowImg",beforeUpload,afterUpload);
     singleUpload("cardPhotoFilePicker","cardPhotoFileUrl","cardPhotoShowImg",beforeUpload,afterUpload);
})
 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }
function cancelFun(){
	location=_ctx+"/back/frame/system/agent/agentList"
}
function cancelAudit(){
	location=_ctx+"/back/frame/system/agent/notAuditedList"
}
function authareaTwoIdFun(){
	if($("#areaTwoId").val()==""){
		$("#authareaTwoId").val("");
	}else{
		$("#authareaTwoId").val($("#areaTwoId").val());
	}
}
function sellNewHouse(){
	$("input[name=sellBuilding]").val('');
            $('#show').toggleClass('hide'); 
       
}