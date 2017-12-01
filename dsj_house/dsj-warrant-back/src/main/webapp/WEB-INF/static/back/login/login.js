

var ctx="${ctx}";
function changeImg(ctx){ 
	   var imgSrc = $("#imgObj");     
	   var src = imgSrc.attr("src");
	   imgSrc.attr("src",chgUrl(ctx,src));     
}     
//时间戳     
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
function chgUrl(ctx,url){     
   var timestamp = (new Date()).valueOf();
	 return  ctx+"/login/verifyCode?timestamp=" + timestamp;
}
