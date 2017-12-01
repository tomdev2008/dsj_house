var flag = false;
function savePicture(){
	var arrList=new Array();
	$("#imagesList").find(".col-xs-3").each(function(){
		picture = newPicture();
		picture.pictureUrl = $(this).find("img").attr("src");
		picture.describes = $(this).find("input").val();
		arrList.push(picture);
	});
	$("#popup_box").show();
	$.ajax({
		type:"post",
		data:JSON.stringify(arrList),
		dataType:"json",
		contentType : 'application/json;charset=utf-8',
		url:_ctx+"/back/newHouse/edit/newHouse_savePictures",
		success:function(resultVo){
			if(resultVo.status!=200){
				$("#popup_box").hide();
				 setErrorContent(resultVo.message);
			}else{
				if(flag){
					location=_ctx+"/back/frame/newHouse/edit/newHouse_pictures_detail?newHouseId="+$("#newHouseId").val()+"&pictureStatus="+$("#pictureStatus").val()
				}else{
					location=_ctx+"/back/frame/newHouse/edit/newHouse_pictures_add?newHouseId="+$("#newHouseId").val()
				}
				
				
			}
		}
	})
}
function newPicture(){
	var picture = {
		describes:"",
		objectId:$("#newHouseId").val(),
		pictureStatus:$("#pictureStatus").val(),
		pictureStatusName:$("#pictureStatusName").val(),
		pictureUrl:""
	}
	return picture;
}



$(function(){
	$("#photoType option").each(function(){
		if($(this).val()==$("#pictureStatus").val()){
			$(this).attr("selected",true);
			$("#photoType").prop("disabled",true);
			flag = true;
			return;
		}
	})
})