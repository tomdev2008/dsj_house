
/**
 * 置顶
 */
function setTop(id){
	var msg="是否置顶?";
	setModelContent(msg,"setTopConfirm");
	setTopAjax(id);
}

function setTopAjax(id){
	$("body").one("click","#setTopConfirm",function(){
		$.ajax({
			type: "POST",
			url: _url+"/back/houseNews/set_stick",
			data: {
				id:id
			},
			dataType: "json",
			success: function(resultVo){
				$("#isSureCancel").click();
				setErrorContent("置顶成功");
				if(resultVo.status!=200){
					alert(resultVo.data);
				}else{
					location.reload();
					/*var houseOption = {
		    		  container_id : "container_id",
		              dialogTemplate_id : "dsj_building_news",
		              pageTemplate_id : "dsj_page_template",
		              url : _url
		            }
		            var houseList = new $.fn.HouseNews(houseOption);
		            houseList._init();*/
				}
			}
		})
	});
}

/**
 * 取消置顶
 */
function setUnTop(id){
	var msg="是否取消置顶?";
	setModelContent(msg,"setUnTopConfirm");
	setUnTopAjax(id);
}

function setUnTopAjax(id){
	$("body").one("click","#setUnTopConfirm",function(){
		$.ajax({
			type: "POST",
			url: _url+"/back/houseNews/set_un_stick",
			data: {
				id:id
			},
			dataType: "json",
			success: function(resultVo){
				$("#isSureCancel").click();
				setErrorContent("取消置顶成功");
				if(resultVo.status!=200){
					alert(resultVo.data);
				}else{
					location.reload();
					/*var houseOption = {
		    		  container_id : "container_id",
		              dialogTemplate_id : "dsj_building_news",
		              pageTemplate_id : "dsj_page_template",
		              url : _url
		            }
		            var houseList = new $.fn.HouseNews(houseOption);
		            houseList._init();*/
				}
			}
		})
	});
}

/**
 * 删除动态
 */
function delNews(id){
	var msg="是否下线?";
	setModelContent(msg,"delNewsConfirm");
	delNewsAjax(id);
}

function delNewsAjax(id){
	$("body").one("click","#delNewsConfirm",function(){
		var _ids = new Array();
		_ids.push(id);
		$.ajax({
			type: "POST",
			url: _url+"/back/houseNews/downNews",
			data: {
				ids:_ids
			},
			dataType: "json",
			success: function(resultVo){
				$("#isSureCancel").click();
				setErrorContent("下线成功");
				if(resultVo.status!=200){
					alert(resultVo.data);
				}else{
					location.reload();
					/*var houseOption = {
		    		  container_id : "container_id",
		              dialogTemplate_id : "dsj_building_news",
		              pageTemplate_id : "dsj_page_template",
		              url : _url
		            }
		            var houseList = new $.fn.HouseNews(houseOption);
		            houseList._init();*/
				}
			}
		})
	});
}

//查看楼盘动态
function showNewHouseNews(id){
	location = _url+"/app/house-news-view.html?id="+id;
}


//编辑楼盘动态
function newHouseNews(id){
	location = _url+"/app/house-news-form.html?id="+id;
}
