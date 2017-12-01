
function addHouseNews(){
	 $("#houseNewsForm").validate(function (result) {
		  	if(result){
		  		var content = $("#content").val();
		  		content=content.replace(/\n/g, '_@').replace(/\r/g, '_#');
		  		content = content.replace(/_#_@/g, '<br/>');//IE7-8
		  		content = content.replace(/_@/g, '<br/>');//IE9、FF、chrome
		  		content = content.replace(/\s/g, '&nbsp;');//空格处理
		  		$("#content").val(content);
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#houseNewsForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/frame/content/houseNews/addHouseNews",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
							setTimeout(function(){
								location=_ctx+"/back/frame/content/houseNews/newsList";
							},2000);
							
						}
		  			}
		  		})
		  	}
	 })
}
function saveHouseNews(){
	 $("#houseNewsForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#houseNewsForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/frame/content/houseNews/updateHouseNews",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
					
							setTimeout(function(){
								location=_ctx+"/back/frame/content/houseNews/newsList";
							},2000);
						}
		  			}
		  		})
		  	}
	 })
}

$(function(){
	convertText();
     singleUpload("photoFilePicker","photoFileUrl","photoShowImg",beforeUpload,afterUpload);
})
 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }
 
 function cancel(){
	 location=_ctx+"/back/frame/content/houseNews/newsList";
 }
 
 function convertText(){
	 var xx=document.getElementById("contentDis").innerText;
	 document.getElementById("content").innerHTML=xx;
 }
