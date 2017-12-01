var clickOnce = true;
function addHouseNews(){
	var html1 = editor.getContentTxt();
	if(html1.trim().length == 0){
		$("#jqk").removeClass("hide");
		return false;
	}
	if( !clickOnce ){
		return false;
	}
	 $("#houseNewsForm").validate(function (result) {
		  	if(result){
		  		var contentTxt = editor.getContentTxt().trim();
		  		var maxLength = 90;
				if(contentTxt!=null){
					if(contentTxt.length > maxLength){
						$("#contentst").val(contentTxt.substring(0,maxLength)+"...");
					}else{
						$("#contentst").val(contentTxt);
					}
				}
				var mypattern = /http:\/\/dasouk\.oss-cn-qingdao.{62,66}\"{1}/;
				var	strcon = editor.getContent().trim();
				var m = strcon.match(mypattern);
				if(m){
					$("#pictureUrl").val(m[0].replace("\"",""));
				}
				clickOnce = false;
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
	var html1 = editor.getContentTxt();
	if(html1.trim().length == 0){
		$("#jqk").removeClass("hide");
		return false;
	}
	if( !clickOnce ){
		return false;
	}
	 $("#houseNewsForm").validate(function (result) {
		  	if(result){
		  		var contentTxt = editor.getContentTxt().trim();
		  		var maxLength = 90;
				if(contentTxt!=null){
					if(contentTxt.length > maxLength){
						$("#contentst").val(contentTxt.substring(0,maxLength)+"...");
					}else{
						$("#contentst").val(contentTxt);
					}
				}
				//第一张图片
				var mypattern = /http:\/\/dasouk\.oss-cn-qingdao.{62,66}\"{1}/;
				var	strcon = editor.getContent().trim();
				var m = strcon.match(mypattern);
				if(m){
					$("#pictureUrl").val(m[0].replace("\"",""));
				}
				clickOnce = false;
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

function yulan(){
	$(document).off('hidden.bs.modal');
	var	conStr = editor.getContent().trim();
	$("#yulan_cc").html(conStr);
	$("#yulanNews").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true
    });
}

$(function(){
	//convertText();
     //singleUpload("photoFilePicker","photoFileUrl","photoShowImg",beforeUpload,afterUpload);
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
