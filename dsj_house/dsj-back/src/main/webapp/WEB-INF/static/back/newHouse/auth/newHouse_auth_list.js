var authNewHouseArr = [];
function saveAuth(){
	$("#authForm").verify();
	$("#authForm").validate(function (result) {
		for(var i in authNewHouseArr){
			authNewHouseArr[i].content=$("#content").val();
		}
		if(result){
			$("#popup_box").show();
			$.ajax({
				type:"post",
				url:_ctx+"/back/newHouse/auth/save_newHouses_auth",
				data:JSON.stringify(authNewHouseArr),
				datatype:"json",
				contentType: 'application/json',  
				success:function(result){
					$("#popup_box").hide();
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#authModal").modal("hide");
						$("#search_btn").click();
					}
				}
			})
		}
	})
	
}
function authNewHouses(type){
	$("#content").val("");
	authNewHouseArr = new Array();
	  $("input[name=checkitem]:checked").each(function(){
		  authNewHouse = newAuthNewHouse();
		  authNewHouse.id = $(this).val();
		  authNewHouse.code = $(this).attr("code");
		  authNewHouse.authStatus = type;
		  authNewHouseArr.push(authNewHouse);
	  });
	  if(authNewHouseArr.length == 0){
		  setErrorContent("请选择要审核的楼盘");
		  return;
	  }else{
		  if(type==3){
			  $("#authTitle").text("审核意见");
			  $("#content").removeAttr("data-validate");
		  }else{
			  $("#authTitle").text("驳回原因");
			  $("#content").attr("data-validate","required");
		  }
		$("#authModal").modal({
			backdrop: 'static',
		    keyboard: false,
		    show: true
	    });
	  }
}
function cancelFun(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_list";
}
function newAuthNewHouse(){
	var authNewHouse = {
			id:null,
			code:null,
			content:null,
			authStatus:null
	}
	return authNewHouse;
}
