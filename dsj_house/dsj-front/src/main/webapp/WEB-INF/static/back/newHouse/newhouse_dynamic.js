function newsDetail(id,newHouseId){
	// 打开页面前设置cookie和数据
	setDynamicNewsCook(id);
	setDynamicNewsData(id);
	//回显样式已点击
	showClicked();
	window.open(_ctx+"/front/newHouse/house_dynamic_detail?id="+id+"&newHouseId="+newHouseId);
}
//回显样式已点击
function showClicked(){
	var dataCk = getCookie(dynamicNewsCookie);
	if(dataCk){
		$(".ndl_center .nalc_name").each(function(){
			if(dataCk.indexOf(","+$(this).attr("data-id")+",")>=0){
				$(this).addClass("visited_999");
			}
		})
		
		$(".like_modle a").each(function(){
			if(dataCk.indexOf(","+$(this).attr("data-id")+",")>=0){
				$(this).addClass("visited_999");
			}
		})
	}
	
}

//页面加载完毕调用
function getdata(){
	$.ajax({
		url : _ctx + "/front/newHouse/house_dynamic_data",
		data:{},
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var dateArr = result.data;
				//把已点击的数据同步到本地
				for(var i = 0; i<dateArr.length ; i++){
					setDynamicNewsCook(dateArr[i].objId);
				}
				//把本地的数据同步到数据库
				var dataCk = getCookie(dynamicNewsCookie);
				setDynamicNewsData(dataCk);
				//回显样式已点击
				showClicked();
			} else {
				//回显样式已点击
				showClicked();
			}
		}
	});
}

//设置楼盘动态的cookie到本地
function setDynamicNewsCook(newsId){
	if(getCookie(dynamicNewsCookie) != null){
		var dataCk = getCookie(dynamicNewsCookie);
		if(dataCk.indexOf(","+newsId+",") == -1){//不包含该动态id
			setCookie(dynamicNewsCookie,dataCk+newsId+",");
		}
	}else{
		setCookie(dynamicNewsCookie,","+newsId+",");
	}
}
//上传楼盘动态到已点击
function setDynamicNewsData(newsStr){
	$.ajax({
		url : _ctx + "/front/newHouse/house_dynamic_update",
		data:{newsStr : newsStr},
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				console.log("suceess");
			} else {
				console.log();
			}
		}
	});
}
window.onload=function(){ 
	getdata();
}