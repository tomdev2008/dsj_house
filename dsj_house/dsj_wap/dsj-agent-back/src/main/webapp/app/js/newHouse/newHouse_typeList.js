$.fn.originAdd = function (option) {
	this._init = function (argument) {
		self=this;
		$.ajax({
            type: "GET",
            url: option.url,
            async:false,
            data:{},
            dataType: "json",
            success: function(data){
              self.render(data.data);
            }
		 });
	}
	
	this.render=function(data){
		var source = $("#master_add").html();
        var template = Handlebars.compile(source);
	    var html = template(data);
        $("#dsj_form").prepend(html);
	}
}

function findOneLayOut(param){
	location=_url+"/app/newhouse-updateType.html?id="+param;
}

function addHouseType(param){
	location=_url+"/app/newhouse-addtype.html?id="+param;
}

function submitAddNewHouse(param,totalCount){
	
		$.ajax({
  			type:"post",
  			async:true,
  			data:{},
  			dataType:"json",
  			url:_url+"/agent/back/newHouse/submitAddNewHouse?id="+param,
  			success:function(resultVo){
  				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					 location=_url+"/app/newHouse-responsible.html";
				}
  			}
  		})
	
}
var id="";
var ids="";
function delNewHouseType(param){
	  id=param;
	  ids = new Array();
	  $("input[name=layoutName]:checked").each(function(){
		  ids.push($(this).val());
	  });
	  if(ids.length == 0){
		  setErrorContent("请选择要删除的户型");
		  return;
	  }else{
		  setModelContent("确认删除选中户型?","delNewHouseTypeConfirm");
	  }
}

$(function(){
	 $("body").on("click","#delNewHouseTypeConfirm",function(){
			$.ajax({
				type:"post",
				url:_url+"/agent/back/newHouse/newHouseType_del",
				data:JSON.stringify(ids),
				datatype:"json",
				contentType: 'application/json',  
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						location=_url+"/app/houseList.html?id="+id;
					}
				}
			})
		  
	  });
});

function selectAll(){
	if($("input[type='checkbox']:checked").length==$("input[type='checkbox']").length){
		$("input[type='checkbox']").prop("checked",false);
	}else{
		$("input[type='checkbox']").prop("checked",true);
	}
}

function cancle(){
	location=_url+"/app/newHouse-responsible.html";
}