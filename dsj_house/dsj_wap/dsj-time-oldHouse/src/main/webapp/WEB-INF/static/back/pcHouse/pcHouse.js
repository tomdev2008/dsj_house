//新房
function updateNewHouse(){
	   var newHouseId = $('#dicSelectId').find("option:selected").val();
	   var newHouseName = $('#dicSelectId').find("option:selected").text();
	   if(newHouseName==null || newHouseName==''){
		   setErrorContent("楼盘名称不能为空");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   var pcNewHouseId=$("#pcNewHouseId").val();
	   var recommend=$("#recommend").val();
	   if(recommend==null || recommend==''){
		   setErrorContent("请填写推荐语");
		   return;
	   }
	   var picture=$("#companyLicensePhotoFileUrl").val();
	   if(picture==null || picture==''){
		   setErrorContent("图片不能为空");
		   return;
	   }
	   var labelId=$("#labelId").val();
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateNewHouse",
			data:{
				newHouseId:newHouseId,
				newHouseName:newHouseName,
				linkAddress:linkAddress,
				id:pcNewHouseId,
				picture:picture,
				recommend:recommend,
				labelId:labelId
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
   }

function updateAgent(){
	 var id=$("#pcAgentId").val();
	 var agentId = $('#dicSelectId').find("option:selected").val();
	   var agentName = $('#dicSelectId').find("option:selected").text();
	   var agentNames=agentName.split("--");
	   if(agentName==null || agentName==''){
		   setErrorContent("经纪人名称不能为空");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return; 
	   }
	   var comment=$("#comment").val();
	   if(comment==null || comment==''){
		   setErrorContent("请填写点评");
		   return;
	   }
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateAgent",
			data:{
				agentId:agentId,
				agentName:agentNames[1],
				linkAddress:linkAddress,
				id:id,
				comment:comment
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}

function updateNewHousePage(param){
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateNewHousePage",
		data:{
			id:param
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
			}
		}
	})
}

//二手房
function updateOldHouse(){
	 var oldHouseId = $('#dicSelectId').find("option:selected").val();
	   var oldHouseName = $('#dicSelectId').find("option:selected").text();
	   if(oldHouseName==null || oldHouseName==''){
		   setErrorContent("楼盘名称不能为空");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return;
	   }
	   var pcOldHouseId=$("#pcOldHouseId").val();
	   var recommend=$("#recommend").val();
	   if(recommend==null || recommend==''){
		   setErrorContent("请填写推荐语");
		   return;
	   }
	   var picture=$("#companyLicensePhotoFileUrl").val();
	   if(picture==null || picture==''){
		   setErrorContent("图片不能为空");
		   return;
	   }
	   var labelId=$("#labelId").val();
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateOldHouse",
			data:{
				objectId:oldHouseId,
				oldHouseName:oldHouseName,
				linkAddress:linkAddress,
				id:pcOldHouseId,
				picture:picture,
				recommend:recommend,
				labelId:labelId
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}

function updateAgentOldHosue(){
	 var id=$("#pcAgentId").val();
	 var agentId = $('#dicSelectId').find("option:selected").val();
	   var agentName = $('#dicSelectId').find("option:selected").text();
	   var agentNames=agentName.split("--");
	   if(agentName==null || agentName==''){
		   setErrorContent("经纪人名称不能为空");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return; 
	   }
	   var comment=$("#comment").val();
	   if(comment==null || comment==''){
		   setErrorContent("请填写点评");
		   return;
	   }
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateAgentOldHouse",
			data:{
				agentId:agentId,
				agentName:agentNames[1],
				linkAddress:linkAddress,
				id:id,
				comment:comment
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}
function updateOldHousePage(param){
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateOldHousePage",
		data:{
			id:param
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
			}
		}
	})
}

//租房
function updateRentHouse(){
	var objectId = $('#dicSelectId').find("option:selected").val();
	   var rentName = $('#dicSelectId').find("option:selected").text();
	   if(rentName==null || rentName==''){
		   setErrorContent("楼盘名称不能为空");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return;
	   }
	   var pcRentHouseId=$("#pcRentHouseId").val();
	   var picture=$("#companyLicensePhotoFileUrl").val();
	   if(picture==null || picture==''){
		   setErrorContent("图片不能为空");
		   return;
	   }
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateRentHouse",
			data:{
				objectId:objectId,
				rentName:rentName,
				linkAddress:linkAddress,
				id:pcRentHouseId,
				picture:picture
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}

function updateRentHousePage(param){
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateRentHousePage",
		data:{
			id:param
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
			}
		}
	})
}

//经纪人
function updateAgentAdd(){
	var id=$("#pcAgentId").val();
	 var agentId = $('#dicSelectId').find("option:selected").val();
	   var agentName = $('#dicSelectId').find("option:selected").text();
	   agentNames=agentName.split("--");
	   if(agentName==null || agentName==''){
		   setErrorContent("经纪人名称不能为空");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return; 
	   }
	   var picture=$("#companyLicensePhotoFileUrl").val();
	   if(picture==null || picture==''){
		   setErrorContent("图片不能为空");
		   return;
	   }
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateAgentAdd",
			data:{
				agentId:agentId,
				agentName:agentNames[1],
				linkAddress:linkAddress,
				picture:picture,
				id:id
				
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}

//权证
function updateWarrant(){
	   var label=$("#label").val();
	   if(label==null || label==''){
		   setErrorContent("请填写产品描述");
		   return;
	   }
	   var price=$("#price").val();
	   if(price==null || price==''){
		   setErrorContent("请填写价格");
		   return;
	   }
	   if(price>90000){
		   setErrorContent("价格不能大于90000元");
		   return; 
	   }
	   var title=$("#title").val();
	   if(title==null || title==''){
		   setErrorContent("请填写标题");
		   return;
	   }
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return;
	   }
	   var pcWarrantId=$("#pcWarrantId").val();
	   var picture=$("#companyLicensePhotoFileUrl").val();
	   if(picture==null || picture==''){
		   setErrorContent("图片不能为空");
		   return;
	   }
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateWarrant",
			data:{
				linkAddress:linkAddress,
				id:pcWarrantId,
				picture:picture,
				label:label,
				price:price,
				title:title
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}

//banner
function updateBanner(){
	   var linkAddress=$("#linkAddress").val();
	   if(linkAddress==null || linkAddress==''){
		   setErrorContent("请填写链接地址");
		   return;
	   }
	   var pcBannerId=$("#pcBannerId").val();
	   var picture=$("#companyLicensePhotoFileUrl").val();
	   if(picture==null || picture==''){
		   setErrorContent("图片不能为空");
		   return;
	   }
	   $.ajax({
			type:"post",
			url:_ctx+"/back/pageLayout/updateBanner",
			data:{
				linkAddress:linkAddress,
				id:pcBannerId,
				picture:picture
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/pageLayout/pageManageMent";
				}
			}
		})
}
