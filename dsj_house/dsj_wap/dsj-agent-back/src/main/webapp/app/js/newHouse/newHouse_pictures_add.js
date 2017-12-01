var _url=_url;
function savePicture(){
	var arrList=new Array();
	$("#imagesList").find(".col-xs-3").each(function(){
		picture = newPicture();
		picture.pictureUrl = $(this).find("img").attr("src");
		picture.describes = $(this).find("input").val();
		arrList.push(picture);
	});
	$.ajax({
		type:"post",
		data:JSON.stringify(arrList),
		dataType:"json",
		contentType : 'application/json;charset=utf-8',
		url:_url+"/agent/back/newHouse/newHouse_savePictures",
		success:function(resultVo){
			if(resultVo.status!=200){
				 setErrorContent(resultVo.message);
			}else{
				location.reload();
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


