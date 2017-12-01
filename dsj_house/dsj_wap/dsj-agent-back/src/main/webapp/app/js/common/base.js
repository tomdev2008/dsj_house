var _ctx=location.pathame;

//操作前提示modal
function setModelContent(msg,modelId){
	$("#alertModelText").text(msg);
	$("button[name='confirm']").prop("id",modelId);
	$('#isSureModal').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
}

//错误提示
//操作前提示modal
function setErrorContent(msg){
	$("#errorText").text(msg);
	$('#errorModal').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
}

//全选
function checkAllCheckbox(obj,items){
	if($(obj).find("input").is(":checked")){
		$(obj).find("input").prop("checked",false);
	}else{
		$(obj).find("input").prop("checked",true);
	}
	if($(obj).find("input").is(":checked")){
		$("input[name='"+items+"']").prop("checked",true);
	}else{
		$("input[name='"+items+"']").prop("checked",false);
	}
}


//打开公共弹框
function openCommonModal(url,widths,top){
	$("#toHtmlModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true,
        remote:url
    });
	$("#toHtmlModal .modal-dialog").css({'width':widths});
	$("#toHtmlModal").css({'margin':'0 auto'});
	if(top!=null){
		$("#toHtmlModal").css({'top':top+'%'});
	}
}

//获取二级地区  如获取标准地域  isCustom=1
function getTwoArea(isCustom){
	var classid=$("#areaOneId").val();
	$("#areaTwoId").empty();
	$("#areaTwoId").append("<option value=''>请选择</option>");
	if($("#areaThreeId").val()){
		$("#areaThreeId").empty();
		$("#areaThreeId").append("<option value=''>请选择</option>");
	}
	if($("#areaFourId").val()){
		$("#areaFourId").empty();
		$("#areaFourId").append("<option value=''>请选择</option>");
	}
	
	if(classid!=""){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/area_list",
				data:{
					areaCode:classid,
					isCustom:isCustom
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							$("#areaTwoId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
	if(typeof $("#authareaTwoId").val()!='undefined'){
		authareaTwoIdFun();
	}
	
}

//获取三级地区 如获取标准地域  isCustom=1
function getThreeArea(isCustom){
	var classid=$("#areaTwoId").val();
	$("#areaThreeId").empty();
	$("#areaThreeId").append("<option value=''>请选择</option>");
	if($("#areaFourId").val()){
		$("#areaFourId").empty();
		$("#areaFourId").append("<option value=''>请选择</option>");
	}
	if(classid!=""){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/area_list",
				data:{
					areaCode:classid,
					isCustom:isCustom
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							$("#areaThreeId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
	
}
//获取商圈 
function getFourArea(){
	var classid=$("#areaThreeId").val();
	$("#areaFourId").empty();
	$("#areaFourId").append("<option value=''>请选择</option>");
	if(classid!=""){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/business",
				data:{
					areaCode:classid
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							$("#areaFourId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
	
}

//获取地铁站
function getLine(num){
	var lineId=$("#line"+num).val();
	$("#station"+num).empty();
	$("#station"+num).append("<option value=''>站点</option>");
	if(lineId!=""){
		$.ajax({
			type:"post",
			url:_ctx+"/back/subway/findStations",
			data:{
				subwayId:lineId
			},
			datatype:"json",
			success:function(result){
				if(result.status = 200 ){
					var list = result.data;
					for(var i in list){
						$("#station"+num).append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
					}
				}else{
					setErrorContent(result.message);
				}
			}
		})
	}
	
}

//序列化转json对象 方法二
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
          
            o[this.name]= o[this.name] + "," +this.value;
        } else {
        	if(this.value!=""&&this.value!=null){
        		 o[this.name] = this.value;
        	}
        }
    });
    return o;
}; 
