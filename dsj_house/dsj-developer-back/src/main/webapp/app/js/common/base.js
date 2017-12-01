

//操作前提示modal
function setModelContent(msg,modelId){
	$("#alertModelText").text(msg);
	$("button[name='confirm']").prop("id",modelId);
	$('#isSureModal').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
}


//错误提示
//操作前提示modal
function setErrorContent(msg){
	$("#errorText").text(msg);
	$('#errorModal').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
}


//全选
function checkAllCheckbox(obj,items){
	if($(obj).find("input").is(":checked")){
		$(obj).find("input").prop("checked",false);
	}else{
		$(obj).find("input").prop("checked",true);
	}
	if($(obj).find("input").is(":checked")){
		$("input[name='"+items+"']").prop("checked",true);
	}else{
		$("input[name='"+items+"']").prop("checked",false);
	}
}


function openCommonModal(commonModal,url,widths,top){
	$("#"+commonModal).modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true,
        remote: url
    });
	$("#"+commonModal+" .modal-dialog").css({'width':widths});
	$("#"+commonModal).css({'margin':'0 auto'});
	if(top!=null){
		$("#"+commonModal).css({'top':top+'%'});
	}
}

function clearData(e){
	$(e.target).removeData('bs.modal');
	  if(e.target.id!="isSureModal"){
	    	$(e.target).find(".modal-content").children().remove();
	    }
}
