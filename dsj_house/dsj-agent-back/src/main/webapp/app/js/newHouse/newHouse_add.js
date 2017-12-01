var _ctx=_url;
function setDirectoryName(obj,name,newName){
	if ('dicTraitName'== newName && $("[name='dicTrait']:checked").length > 4) {
		$(obj).prop("checked",false);
		setErrorContent("特点最多选择4种！");
		return;
	}
	if ('achTypeName'== newName && $("[name='achType']:checked").length > 4) {
		$(obj).prop("checked",false);
		setErrorContent("建筑类型最多选择4种！");
		return;
	}
	
	if($(obj).is(":checked")){
		$(obj).after('<input type="hidden" name="'+newName+'" value="'+name+'">');
	}else{
		$(obj).next().remove();
	}
	if($("input[name='"+newName+"']").length!=0){
		$("#"+newName+"Validate").val("yes");
	}else{
		$("#"+newName+"Validate").val("");
	}
}

//加载单价
function selectWyType(obj,name){
	 //参考单价
	 var referencePriceStr = "";
	//楼盘总价
	 var totalPriceStr = "";
	//最低首付
	 var paymentsStr = "";
	//最低月供
	 var monthPayStr = "";
	//开盘时间
	 var openTimeStr = "";
	//交房时间
	 var handTimeStr = "";
	//产权年限
	 var propertyRightStr = "";
	//容积率
	 var plotRatioStr = "";
	//物业管理费
	 var propertyFeeStr = "";
	 
	  if($(obj).is(":checked")){
		  var classs="col-sm-1 control-label";
		 if($("input[name='wyType']:checked").length!=1){
			 classs = "col-sm-1 dsj_double_label hidetext"
		 }
	 referencePriceStr = '<div class="form-group  left_127" id="'+$(obj).val()+'smallReferencePriceDiv">'+
						 ' <label class="'+classs+'"></label><div class="col-sm-11 form-inline">'+
						 '<label for="house_price" class="col-sm-1 dsj_double_label">'+name+'</label><div class="dsj_double_input">'+
				         '<input id="'+$(obj).val()+'referencePriceMin"   class="dsj_double_left_input form-control">'+
				         ' <input id="'+$(obj).val()+'referencePriceMax"   class="dsj_double_right_input form-control" placeholder="万">'+
				         '</div></div></div>';
	 
		totalPriceStr  = '<div class="form-group  left_127" id="'+$(obj).val()+'smallTotalPriceDiv">'+
						 ' <label class="'+classs+'"></label><div class="col-sm-11 form-inline">'+
						 '<label class="col-sm-1 dsj_double_label">'+name+'</label><div class="dsj_double_input">'+
				         '<input id="'+$(obj).val()+'totalPriceMin"   class="dsj_double_left_input form-control">'+
				         ' <input id="'+$(obj).val()+'totalPriceMax"   class="dsj_double_right_input form-control" placeholder="万">'+
				         '</div></div></div>';
		 paymentsStr =  '<div class="form-group  left_127" id="'+$(obj).val()+'smallPaymentsDiv" ><label class="'+classs+'"></label><div class="col-sm-11 form-inline">'+
          				'<label for="house_price" class="col-sm-1 dsj_double_label">'+name+'</label>'+
          				'<div class="col-sm-6"><input id="'+$(obj).val()+'payments" type="text" class="form-control" placeholder="最低首付 (万)" data-validate="isPrice"></div></div></div>';
		 
		 monthPayStr =  '<div class="form-group  left_127" id="'+$(obj).val()+'smallMonthPayDiv" ><label class="'+classs+'"></label><div class="col-sm-11 form-inline">'+
						'<label for="house_price" class="col-sm-1 dsj_double_label">'+name+'</label>'+
						'<div class="col-sm-6"><input id="'+$(obj).val()+'monthPay" type="text" class="form-control" placeholder="最低月供 (元)" data-validate="isPrice"></div></div></div>';
		 
		 openTimeStr =  '<div class="form-group  left_127 '+$(obj).val()+'smallOpenTimeDiv"><label class="'+classs+'"></label><div class="col-sm-11 form-inline"><label class="col-sm-1 dsj_double_label">'+name+'</label>'+
	         			'<div class="col-sm-2"><input type="text" class="form-control" placeholder="楼栋" id="'+$(obj).val()+'openTimeContent0" style="width: 100%;"></div><div class="pull-left"><span class="form-control-static"> ~ </span></div><div class="col-sm-2"><input class="form-control layDateClass" id="'+$(obj).val()+'openTime0" placeholder="开盘时间" style="width: 100%;"></div><div class="col-sm-1">'+
	         			' <p class="form-control-static"><a href="javascript:void(0)" onclick=addOpenTime('+$(obj).val()+',"'+name+'",this)> 添加 </a><a href="javascript:void(0)" onclick="removeOpenTime('+$(obj).val()+')">删除</a></p></div></div></div>';
		
		 handTimeStr =  '<div class="form-group  left_127 '+$(obj).val()+'smallHandTimeDiv"><label class="'+classs+'"></label><div class="col-sm-11 form-inline"><label class="col-sm-1 dsj_double_label">'+name+'</label>'+
						'<div class="col-sm-2"><input type="text" class="form-control" placeholder="楼栋" id="'+$(obj).val()+'handTimeContent0" style="width: 100%;"></div><div class="pull-left"><span class="form-control-static"> ~ </span></div><div class="col-sm-2"><input class="form-control layDateClass" id="'+$(obj).val()+'handTime0" placeholder="交房时间" style="width: 100%;"></div><div class="col-sm-1">'+
						' <p class="form-control-static"><a href="javascript:void(0)" onclick=addHandTime('+$(obj).val()+',"'+name+'",this)> 添加 </a><a href="javascript:void(0)" onclick="removeHandTime('+$(obj).val()+')">删除</a></p></div></div></div>';
	 propertyRightStr = '<div class="form-group  left_127" id="'+$(obj).val()+'smallPropertyRightDiv"><label class="'+classs+'"><span class="dsj_warn">*</span></label>'+
        				'<div class="col-sm-11 form-inline"><label class="col-sm-1 dsj_double_label">'+name+'</label><div class="col-sm-1">'+
        				$("#propertyRightDemoDiv").html().replace('id="propertyRightDemo"','id="'+$(obj).val()+'propertyRight"')+'</div></div></div>';
		 
		 plotRatioStr = '<div class="form-group  left_127" id="'+$(obj).val()+'smallPlotRatioDiv" ><label class="'+classs+'"></label><div class="col-sm-11 form-inline">'+
						'<label for="house_price" class="col-sm-1 dsj_double_label">'+name+'</label>'+
						'<div class="col-sm-6"><input type="text" class="form-control" id="'+$(obj).val()+'plotRatio" placeholder="容积率"></div></div></div>';
	   propertyFeeStr = '<div class="form-group  left_127" id="'+$(obj).val()+'smallPropertyFeeDiv" ><label class="'+classs+'"></label><div class="col-sm-11 form-inline">'+
						'<label for="house_price" class="col-sm-1 dsj_double_label">'+name+'</label>'+
						'<div class="col-sm-6"><input type="text" class="form-control" id="'+$(obj).val()+'propertyFee" placeholder="物业管理费 (元/m²/月)"></div></div></div>';
		 $("#bigReferencePriceDiv").append(referencePriceStr);
		 $("#bigTotalPriceDiv").append(totalPriceStr);
		 $("#bigPaymentsDiv").append(paymentsStr);
		 $("#bigMonthPayDiv").append(monthPayStr);
		 $("#bigOpenTimeDiv").append(openTimeStr);
		 $("#bigHandTimeDiv").append(handTimeStr);
		 $("#bigPropertyRightDiv").append(propertyRightStr);
		 $("#bigPlotRatioDiv").append(plotRatioStr);
		 $("#bigPropertyFeeDiv").append(propertyFeeStr);
		 $(".hidetext").text("");
	  }else{
		 $("#"+$(obj).val()+"smallReferencePriceDiv").remove();
		 $("#"+$(obj).val()+"smallTotalPriceDiv").remove();
		 $("#"+$(obj).val()+"smallPaymentsDiv").remove();
		 $("#"+$(obj).val()+"smallMonthPayDiv").remove();
		 $("."+$(obj).val()+"smallOpenTimeDiv").remove();
		 $("."+$(obj).val()+"smallHandTimeDiv").remove();
		 $("#"+$(obj).val()+"smallPlotRatioDiv").remove();
		 $("#"+$(obj).val()+"smallPropertyRightDiv").remove();
		 $("#"+$(obj).val()+"smallPropertyFeeDiv").remove();
	  }
	  setDirectoryName(obj,name,"wyTypeName");
	  setLayDate();
}


//追加电话
function addPhone(){
	if($("#phoneDiv").find("input[name='phone']").length<10){
		$("#phoneDiv").after('<div class="form-group has-feedback phone"><label  class="col-sm-1 control-label"></label> <div class="col-sm-3">'+
						'<input  type="text" class="form-control"  name="phone" placeholder="售楼处电话" data-validate="required"></div></div>');
	}else{
		 setErrorContent("电话最多追加10个");
	}
}
//删除电话
function removePhone(){
	$(".phone:last").remove();
}

//追加优惠
function addDiscount(){
	if($("#discountDiv").find("input[name='discount']").length<20){
		$("#discountDiv").after(' <div class="form-group has-feedback discount"><label for="inputPassword" class="col-sm-1 control-label"></label> <div class="col-sm-3">'+
			       '<input  type="text" class="form-control"  name="discount" placeholder="楼盘优惠"></div></div>');
	}else{
		 setErrorContent("优惠最多追加20个");
	}
}
//删除优惠
function removeDiscount(){
	$(".discount:last").remove();
}

//追加预售许可证
function addPrep(){
	
	if($("#prepDiv").find("input[name='prep']").length<10){
		var str = ' <div class="form-group has-feedback prep"><label for="inputPassword" class="col-sm-1 control-label"></label> <div class="col-sm-3">'+
		'<input  type="text" class="form-control"  name="prep" placeholder="预售许可证"></div></div>';
		if($(".prep").length>0){
			$("#prepDiv").nextAll(".prep:last").after(str);
		}else{
			$("#prepDiv").after(str);
		}
	}else{
		 setErrorContent("预售许可证最多追加10个");
	}
}
//删除预售许可证
function removePrep(){
	$(".prep:last").remove();
}
//追加地铁
function addSubway(){
	if($("#subwayDiv").find(".sub").length<5){
		var i = 0;
		while(true){
			if(typeof $("#line"+i).val() == "undefined"){
				break;
			}else{
				i++;
			}
		}
		var subway = $("#subwayDemoDiv").html().replace('id="line"','id="line'+i+'"').replace('id="station"','id="station'+i+'"').replace('id="distance"','id="distance'+i+'"').replace('onchange="getLine(0)"','onchange="getLine('+i+')"').replace('class="form-group"','class="form-group subway"');
		if($(".subway").length>0){
			$(".subway:last").after(subway);
		}else{
			$("#subwayDiv").after(subway);
		}
	}else{
		 setErrorContent("地铁站点最多追加5个");
	}
}

//删除地铁
function removeSubway(){
	$(".subway:last").remove();
}

//追加交房时间
function addHandTime(id,name,obj){
	var i = 0;
	while(true){
		if(typeof $("#"+id+"handTime"+i).val() == "undefined"){
			break;
		}else{
			i++;
		}
	}
	var handTimeStr = '<div class="form-group '+id+'smallHandTimeDiv"><label class="col-sm-1 control-label"></label><div class="col-sm-11 form-inline"><label class="col-sm-1 dsj_double_label">'+name+'</label>'+
	'<div class="col-sm-2"><input type="text" class="form-control" placeholder="楼栋" id="'+id+'handTimeContent'+i+'" style="width: 100%;"></div><div class="pull-left"><span class="form-control-static"> ~ </span></div><div class="col-sm-2"><input class="form-control layDateClass" id="'+id+'handTime'+i+'" placeholder="交房时间" style="width: 100%;"></div><div class="col-sm-1">'
	$("#bigHandTimeDiv").append(handTimeStr);
	setLayDate();
}
//删除交房时间
function removeHandTime(id){
	if($("."+id+"smallHandTimeDiv").length>1){
		$("."+id+"smallHandTimeDiv:last").remove();
	}
}

//追加时间
function addOpenTime(id,name,obj){
	var i = 0;
	while(true){
		if(typeof $("#"+id+"openTime"+i).val() == "undefined"){
			break;
		}else{
			i++;
		}
	}
	
	var openTimeStr = '<div class="form-group '+id+'smallOpenTimeDiv"><label class="col-sm-1 control-label"></label><div class="col-sm-11 form-inline"><label class="col-sm-1 dsj_double_label">'+name+'</label>'+
	'<div class="col-sm-2"><input type="text" class="form-control" placeholder="楼栋" id="'+id+'openTimeContent'+i+'" style="width: 100%;"></div><div class="pull-left"><span class="form-control-static"> ~ </span></div><div class="col-sm-2"><input class="form-control layDateClass" id="'+id+'openTime'+i+'" placeholder="开盘时间" style="width: 100%;"></div><div class="col-sm-1">'
	$("#bigOpenTimeDiv").append(openTimeStr);
	setLayDate();
}
//删除时间
function removeOpenTime(id){
	if($("."+id+"smallOpenTimeDiv").length>1){
		$("."+id+"smallOpenTimeDiv:last").remove();
	}
}

//创建新的物业信息对象
function newWyMsg(){
	var wyMsg = {
		wyType : null,	
		wyTypeName : null,
		referencePriceMin : null,	
		referencePriceMax : null,	
		totalPriceMin : null,	
		totalPriceMax : null,	
		payments : null,	
		monthPay : null,	
		propertyRight : null,	
		propertyRightName : null,	
		plotRatio : null,	
		propertyFee : null	
	}
	return wyMsg;
}
//动态物业信息赋值
function setWyMsg(){
	var arr = new Array();
	$("input[name='wyType']:checked").each(function(){
		wyMsg = newWyMsg();
		wyMsg.wyType = $(this).val();
		wyMsg.wyTypeName = $(this).attr("wyName");
		if($("#"+$(this).val()+"referencePriceMin").val()!=""){
			wyMsg.referencePriceMin = $("#"+$(this).val()+"referencePriceMin").val();
		}
		if($("#"+$(this).val()+"referencePriceMax").val()!=""){
			wyMsg.referencePriceMax = $("#"+$(this).val()+"referencePriceMax").val();
		}
		if($("#"+$(this).val()+"totalPriceMin").val()!=""){
			wyMsg.totalPriceMin = $("#"+$(this).val()+"totalPriceMin").val();
		}
		if($("#"+$(this).val()+"totalPriceMax").val()!=""){
			wyMsg.totalPriceMax = $("#"+$(this).val()+"totalPriceMax").val();
		}
		if($("#"+$(this).val()+"payments").val()!=""){
			wyMsg.payments = $("#"+$(this).val()+"payments").val();
		}
		if($("#"+$(this).val()+"monthPay").val()!=""){
			wyMsg.monthPay = $("#"+$(this).val()+"monthPay").val();	
		}
		
		wyMsg.propertyRight = $("#"+$(this).val()+"propertyRight").val();	
		wyMsg.propertyRightName = $("#"+$(this).val()+"propertyRight option:selected").text();	
		wyMsg.plotRatio = $("#"+$(this).val()+"plotRatio").val();
		wyMsg.propertyFee = $("#"+$(this).val()+"propertyFee").val();
		arr.push(wyMsg);
	});
	return arr;
}
//创建新开盘时间
function newOpenHandTime(){
	var openHandTimePo = {
		wyType : null,	
		wyTypeName : null,
		type : null,	
		describes : null,	
		openHandTime : ""
	}
	return openHandTimePo;
}
//动态交房开盘时间赋值
function setOpenHandTime(){
	var arr = new Array();
	$("input[name='wyType']:checked").each(function(){
		var i = 0;
		while(true){
			if(typeof $("#"+$(this).val()+"openTime"+i).val() == "undefined"){
				break;
			}else{
				openHandTimePo1 = newOpenHandTime();
				openHandTimePo1.wyType = $(this).val();
				openHandTimePo1.wyTypeName = $(this).attr("wyName");
				openHandTimePo1.type=1;
				openHandTimePo1.describes=$("#"+$(this).val()+"openTimeContent"+i).val();
				
				if($("#"+$(this).val()+"openTime"+i).val()!=""){
					openHandTimePo1.openHandTime=$("#"+$(this).val()+"openTime"+i).val();
				}
				
				arr.push(openHandTimePo1);
				i++;
			}
		}	
		i = 0;
		while(true){
			if(typeof $("#"+$(this).val()+"handTime"+i).val() == "undefined"){
				break;
			}else{
				openHandTimePo2 = newOpenHandTime();
				openHandTimePo2.wyType = $(this).val();
				openHandTimePo2.wyTypeName = $(this).attr("wyName");
				openHandTimePo2.type=2;
				openHandTimePo2.describes=$("#"+$(this).val()+"handTimeContent"+i).val();
				
				if($("#"+$(this).val()+"handTime"+i).val()!=""){
					openHandTimePo2.openHandTime=$("#"+$(this).val()+"handTime"+i).val();
				}
				
				arr.push(openHandTimePo2);
				i++;
			}
		}
		
	});
	return arr;
}

//获取地铁
function setSubWay(){
	var arr = new Array();
		var i = 0;
		while(true){
			if(typeof $("#line"+i).val() == "undefined"){
				break;
			}else{
				var subwayFlag = true;
				subWayPo = newSubWay();
				
				if($("#line"+i).val()!=""){
					subWayPo.line = $("#line"+i).val();
					subWayPo.lineName = $("#line"+i+" option:selected").text();
				}
				if($("#station"+i).val()!=null){
					subWayPo.station = $("#station"+i).val();
					subWayPo.stationName = $("#station"+i+" option:selected").text();
				}
				if($("#distance"+i).val()!=null){
					subWayPo.distance = $("#distance"+i).val();
				}
				if(subWayPo.line!=""||subWayPo.station!=""||subWayPo.distance!=""){
					arr.push(subWayPo);
				}
				i++;
			}
		}	
	return arr;
}

//创建新开盘时间
function newSubWay(){
	var subWayPo = {
			line : null,	
			lineName : null,
			station :null,
			stationName:null,	
			distance : null
	}
	return subWayPo;
}
var newHouseVo = {
		newHousePo : null,
		wyMsgList : null,
		openHandTimeList :null,
		subWayList:null
}
//保存/下一步
function saveAddNewHouse(type){
	//区域验证
	if( $("#areaOneId").val()!=""&&
		$("#areaTwoId").val()!=""&&
		$("#areaThreeId").val()!=""&&
		$("#areaFourId").val()!=""&&
		$("#lineNum").val()!="" ){
		$("#areaValidate").val("yes");
	}else{
		$("#areaValidate").val("");
	}
	$("#newHouseForm").verify();
	$("#newHouseForm").validate(function (result) {
		var flag = false;
	  	if(result){
	  		$("#areaName1").val($("#areaOneId").find("option:selected").text());
			$("#areaName2").val($("#areaTwoId").find("option:selected").text());
			$("#areaName3").val($("#areaThreeId").find("option:selected").text());
			$("#tradingAreaName").val($("#areaFourId").find("option:selected").text());
			$("#lineNumName").val($("#lineNum").find("option:selected").text());
			if($("input[name='wyType']:checked").length>0){
				$("input[name='wyType']:checked").each(function(){
		  			if($("#"+$(this).val()+"referencePriceMin").val()!="" && $("#"+$(this).val()+"referencePriceMax").val()!=""){
		  				flag = true;
		  			}else{
		  				flag = false;
		  				return false;
		  			}
		  		});
		  		if(flag==false){
		  			$("input[name='wyType']:checked").each(function(){
			  			if($("#"+$(this).val()+"totalPriceMin").val()!="" && $("#"+$(this).val()+"totalPriceMax").val()!=""){
			  				flag = true;
			  			}else{
			  				flag = false;
			  				return false;
			  			}
			  			
			  		});
		  		}
		  		if(flag==false){
		  			if($("#aroundMinPrice").val()!="" && $("#aroundMaxPrice").val()!=""){
		  				flag = true;
		  			}
		  		}
		  		
		  		if(flag){
		  			newHouseVo.openHandTimeList = JSON.stringify(setOpenHandTime());
		  			newHouseVo.subWayList = JSON.stringify(setSubWay());
			  		newHouseVo.wyMsgList = JSON.stringify(setWyMsg());
			  		newHouseVo.newHousePo=JSON.stringify($("#newHouseForm").serializeObject())
			  		$.ajax({
						type:"post",
						url: _ctx+"/agent/back/newHouse/newHouse_save",
						dataType:"json",
						data:JSON.stringify(newHouseVo),
						contentType : 'application/json;charset=utf-8',
						success:function(resultVo){
							if(resultVo.status!=200){
								 setErrorContent(resultVo.message);
							}else{
								//下一步
								if(type==1){
									location=_ctx+"/app/newHouse-picture.html?id="+resultVo.data;
								}else{
									setErrorContent("保存成功");
									$("#houseId").val(resultVo.data);
								}
							}
						}
					})
		  		}else{
		  			setErrorContent("参考单价,楼盘总价,周边单价必填一组");
		  		}
			}else{
				setErrorContent("物业类型必填一组");
			}
			
	  		
	  		
	  	}
	})
}

function cancelFun(){
	location=_ctx+"/app/newHouse-responsible.html"
}
$(function(){
	 setLayDate();
})