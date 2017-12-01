function selectWyType(obj,name,newName){
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


function addLoupanName(){
	$("#loupanNameId").append('<select class="form-control dsj-inline dicSelectId" name="loupanName" class="js-example-basic-multiple"  data-validate="required"></select>');
	getSelect();
}

function removeLoupanName(){
	if($("input[name='loupanName']").length>1){
		$("input[name='loupanName']:last").remove();
	}
}

function getSelect(){
	$(".dicSelectId").select2({
		"ajax":{
		    url: _ctx+"/back/newHouse/edit/getDic",
		    data: function (params) {
		      var query = { //请求的参数, 关键字和搜索条件之类的
		        name: params.term //select搜索框里面的value
		      }
		      return query;
		    },
		    delay: 500,
		    processResults: function (data, params) {
		      var resu = data.data;
		      resu.push({id:1,text:"其他"});
		      return {
		        results: resu  //必须赋值给results并且必须返回一个obj
		      };
		    }
		  }
	});
}

function cancelFun(type){
	location=_ctx+"/back/frame/system/property/propertyList"
}
function authThreeIdFun(){
	if($("#areaThreeId").val()==""){
		$("#authAreaThreeId").val("");
	}else{
		$("#authAreaThreeId").val($("#areaThreeId").val());
	}
}
/*
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
							$("#areaTwoId").append("<option value='"+list[i].name+"'>"+list[i].name+"</option>")
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
							$("#areaThreeId").append("<option value='"+list[i].name+"'>"+list[i].name+"</option>")
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
	
}*/