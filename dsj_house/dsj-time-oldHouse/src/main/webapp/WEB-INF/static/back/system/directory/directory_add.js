function validateName(name){
	if($("input[name='"+name+"']").is(':checked')){
		$("#"+name+"Validate").val("yes");
	}else{
		$("#"+name+"Validate").val("");
	}
}

function saveOrUpdateNext(isNext){
	//区域验证
	if( $("#lineNum").val()!=""&&
		$("#areaOneId").val()!=""&&
		$("#areaTwoId").val()!=""&&
		$("#areaThreeId").val()!=""&&
		$("#areaFourId").val()!="" ){
		$("#areaValidate").val("yes");
	}else{
		$("#areaValidate").val("");
	}
	//地铁验证
	var subwayNum = $("#subwayDiv .delete").length + 1;
	var subwayStr = "";
	for(var i=0;i<subwayNum;i++){
		if($("#line"+i).val()!=""&&$("#station"+i).val()!=""
			&&$.trim($("#station"+i).val())!=""){
			$("#subbwayValidate"+i).val("yes");
			subwayStr+=$("#line"+i).val()+"-";
			subwayStr+=$("#station"+i).val()+"-";
			subwayStr+=$("#distance"+i).val()+",";
		}else{
			$("#subbwayValidate"+i).val("");
		}
	}
	$("#subwayStr").val(subwayStr);
	$("#addEditForm").validate(function (result) {
		$("#areaName1").val($("#areaOneId").find("option:selected").text());
		$("#areaName2").val($("#areaTwoId").find("option:selected").text());
		$("#areaName3").val($("#areaThreeId").find("option:selected").text());
		$("#tradingAreaName").val($("#areaFourId").find("option:selected").text());
	  	if(result){
	  		$.ajax({
	  			type:"post",
	  			async:true,
	  			data:$("#addEditForm").serialize(),
	  			dataType:"json",
	  			url:_ctx+"/back/houseDirectory/do_add_edit",
	  			success:function(resultVo){
	  				if(resultVo.status!=200){
						 setErrorContent(resultVo.message);
					}else{
						if(isNext==1){
							location=_ctx+"/back/frame/houseDirectory/to_image_list/"+resultVo.data;
						}else{
							setErrorContent("保存成功");
							$("#oldHouseId").val(resultVo.data);
							//location=_ctx+"/back/frame/houseDirectory/to_add_edit?id="+resultVo.data;
						}
					}
	  			}
	  		})
	  	}
	})
}

$(function(){
	//初始验证
	validateName("dicTrait");
	//validateName("achType");
	//validateName("wyType");
	
	//初始地铁选中
	//initSub();
	
	//日期点击事件绑定
	setLayDate();
})

function initSub(){
	var subwayNum = $("#subwayNum").val();
	for( var i = 0 ; i < subwayNum ; i++){
		getLine(i);
	}
}

function cancel(){
	location=_ctx+"/back/frame/houseDirectory/"
}

//追加地铁
function addSubway(){
	var i = 0;
	while(true){
		if(typeof $("#line"+i).val() == "undefined"){
			break;
		}else{
			i++;
		}
	}
	var subway = $("#subwayDemoDiv").html().replace('id="line"','id="line'+i+'"').replace('getLine()','getLine('+i+')').replace('id="station"','id="station'+i+'"')
					.replace('id="distance"','id="distance'+i+'"').replace('id="subbwayValidate"','id="subbwayValidate'+i+'"')
					.replace('name="line"','name="line'+i+'"').replace('name="station"','name="station'+i+'"').replace('name="distance"','name="distance'+i+'"');
	$("#subwayDiv").append(subway);
}

//删除地铁
function removeSubway(){
	if($("#subwayDiv .delete").length>1){
		$("#subwayDiv .delete:last").remove();
	}
}