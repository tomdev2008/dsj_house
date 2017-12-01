//获取二级地区  如获取标准地域 
function getTwoArea(){
	$("#areaThreeId").empty();
	$("#areaThreeId").append("<option value=''></option>");
	
	$("#areaTwoId").empty();
	$("#areaTwoId").append("<option value=''></option>");
	
	var classid=$("#areaOneId").val();
	ajaxArea("areaTwoId",classid);
	
}

//获取二级地区  如获取标准地域  
function getThreeArea(){
	var classid=$("#areaTwoId").val();
	ajaxArea("areaThreeId",classid);
}

function ajaxArea(areaId,classid){
	if(classid!=null && classid !=""){
		$.ajax({
			type:"post",
			url:_url+"/back/common/areaList",
			data:{
				parentId:classid
			},
			datatype:"json",
			success:function(result){
				if(result.status=200){
					var list = result.data;
					for(var i in list){
						$("#"+areaId).append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
					}
				}else{
					//setErrorContent(result.message);
				}
			}
		})
	}
}

//打开modal(1:modalId 2:跳转页面的url 3:控制大小)
function openSelectModal(ModelId,url,widths) {
	$("#"+ModelId).css({'width':widths});
	$("#"+ModelId).css({'margin-left':-(widths/2)});
	$('#'+ModelId).modal({
        backdrop: 'static',
        keyboard: false,
        show: true,
        remote: url
    });
}