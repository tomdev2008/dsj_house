/**
 * 
 */
var id = "";
var msg = '';
$(function(){
	 $("body").on("click","#resetPwdModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/agent/resetPassword",
				data:{
					id:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							$("#search_btn").click();
						},2000);
					}
				}
			})
		  
	  });
	 $("body").on("click","#delAgentModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/agent/deleteAgent",
				data:{
					ids:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							$("#search_btn").click();
						},2000);
						
					}
				}
			})
		  
	  });
	 $("body").on("click","#passAgentManyModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/agent/auditAgent",
				data:{
					id:id,
					status:1,
					msg:""
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							location=_ctx+"/back/frame/system/agent/notAuditedList";
						},2000);			
					}
				}
			})
		  
	  });
	 $("body").on("click","#refuseAgentManyModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/system/agent/auditAgent",
				data:{
					id:id,
					status:3,
					msg:msg
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							location=_ctx+"/back/frame/system/agent/notAuditedList";
						},2000);
					
					}
				}
			})
		  
	  });
	 
 
});

function resetPwd(param){
	id= param;
	setModalContent("确认重置密码为123456?","resetPwdModalConfirm");
}
function resetPwdMany(){
	var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要重置密码的账号");
		  return;
	  }
	  id=ids;
	setModalContent("确认重置密码为123456?","resetPwdModalConfirm");
}

function delAgent(){
	  var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要删除的账号");
		  return;
	  }
	  id=ids;
	setModalContent("确认删除选中账号?","delAgentModalConfirm");
}

function addAgent(){
	location=_ctx+"/back/frame/system/agent/add";
}
function agentUnApply(){
	location=_ctx+"/back/frame/system/agent/unapply";
}
function editAgent(param){
	location=_ctx+"/back/frame/system/agent/edit?id="+param;
}
function agentInfo(param){
	location=_ctx+"/back/frame/system/agent/agentInfo?id="+param;
}
function auditAgentPage(param){
	location=_ctx+"/back/frame/system/agent/audit?id="+param;
}
function agentList(){
	location=_ctx+"/back/frame/system/agent/agentList";
}
function passAgentMany(){
	var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择通过的的账号");
		  return;
	  }
	  id=ids;
	setModalContent("确认通过？","passAgentManyModalConfirm");
}

function refuseAgentMany(){
	  var ids = '';
	  msg = $('#msg').val();
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要驳回的账号");
		  return;
	  }
	  id=ids;
	  show();
}
function passAgent(param){
	id=param;
	setModalContent("确认通过？","passAgentManyModalConfirm");
}

function refuseAgent(param){
	if($('#content').val()==''){
		alert("驳回理由不可以为空");
	}else{
		$("#authModal").hide();
		id=param;
		msg = $('#content').val();
		setModalContent("确认驳回？","refuseAgentManyModalConfirm");
	}
	

}
function show(){
	$("#authModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true
    });
}
function remove(){
	$('#content').val("");
}

function refuseAgentManyEnsure(){
	if($('#content').val()==''){
		alert("驳回理由不可以为空");
	}else{
		$("#authModal").hide();
		msg = $('#content').val();
		setModalContent("确认驳回？","refuseAgentManyModalConfirm");
	}
}

