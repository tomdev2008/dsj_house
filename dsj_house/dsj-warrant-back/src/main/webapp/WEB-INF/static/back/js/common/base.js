$(function(){
	$("#commonModal").on('hidden.bs.modal', function (e) {
		 $(e.target).removeData('bs.modal');
		 $(e.target).find(".modal-content").children().remove();
});
})
//打开公共弹框
function openCommonModal(url,widths,top){
	$("#commonModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true,
        remote: url
    });
	$("#commonModal .modal-dialog").css({'width':widths});
	$("#commonModal").css({'margin':'0 auto'});
	if(top!=null){
		$("#commonModal").css({'top':top+'%'});
	}
}


//关闭modal
function closeModel(modelId){
	
	$(document).on("hidden.bs.modal","#commonModal", function(e) {
	    $(this).removeData("bs.modal");
	    $(e.target).removeData('bs.modal');
	    if(e.target.id!="isSureModal"&&e.target.id!="errorModel"){
	    	if ($(e.target).is(that.$element)) that.ignoreBackdropClick = true
	    	$(e.target).find(".modal-content").children().remove();
	    }
	});
	
	
	$("#"+modelId).modal('hide');

	$(".modal-backdrop").remove();
	
	$(".panel combo-p panel-htop").remove();
}
//全选
function checkAllCheckbox(obj,items){
	if($(obj).is(":checked")){
		$("input[name='"+items+"']").prop("checked",true);
	}else{
		$("input[name='"+items+"']").prop("checked",false);
	}
}

function isChechedFirst(firstId,items){
	var flag = true;
	$("input[name='"+items+"']").each(function(){
		if(!$(this).is(':checked')){
			flag=false;
		}
	})
	if(flag){
		$("#"+firstId).prop("checked",true);
	}else{
		$("#"+firstId).prop("checked",false);
	}
}


/**
 * 页面checkbox形式的全选按钮
 * @param 全选框的#id
 * @param 其他checkbox的name
 * @param document
 */
function checkboxAllFunc(checkAllSelector, checkItemSelector, context) {
	$(context).delegate(checkAllSelector, "click", function(e) {
		var $this = $(this);
		if ($this.is(":checked")) {
			this.setAttribute("checked", true);
			this.checked = true;
			$(context).find(checkItemSelector).each(function(index, v) {
				v.setAttribute("checked", true);
				v.checked = true;
			});
		} else {
			this.removeAttribute("checked");
			this.checked = false;
			$(context).find(checkItemSelector).each(function(index, v) {
				v.removeAttribute("checked");
				v.checked = false;
			});
		}
	});

	$(context).delegate(checkItemSelector, "click", function() {
		var $this = $(this);
		if ($this.is(":checked")) {
			this.setAttribute("checked", true);
			this.checked = true;
		} else {
			this.removeAttribute("checked");
			this.checked = false;
		}
		//alert($(context).find(checkItemSelector + ":checked").size()+" "+$(context).find(checkItemSelector).size())
		if ($(context).find(checkItemSelector).size() == $(context).find(checkItemSelector + ":checked").size()) {
			$(context).find(checkAllSelector).each(function(index, v) {
				v.setAttribute("checked", true);
				v.checked = true;
			});
		} else {
			$(context).find(checkAllSelector).each(function(index, v) {
				v.removeAttribute("checked");
				v.checked = false;
			});

		}
	});
}


//操作前提示modal
function setModalContent(msg,modelId){
	$("#alertModelText").text(msg);
	$("button[name='confirm']").prop("id",modelId);
	$('#isSureModal').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
}
//错误消息提示modal
function setErrorContent(msg){
	$("#errorModelText").text(msg);
	$('#errorModel').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
}
//成功消息提示modal
function modalSuccess(msg){
	$("#successModelText").text(msg);
	$('#successModal').modal({
	    backdrop: true,
	    keyboard: false,
	    show: true
	});
	$("#successModelText").html(msg);

	window.setTimeout(function(){ 
		$('#successModal').modal('hide');
		$("#successModelText").html(msg);
	},1000); 
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
	$("#station"+num).append("<option value=''>请选择</option>");
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
