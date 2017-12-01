$(function(){
	$("#dicSelectId").select2({
			"ajax":{
			    url: _ctx+"/back/oldHouse/master/getDic",
			    data: function (params) {
			      var query = { //请求的参数, 关键字和搜索条件之类的
			        name: params.term //select搜索框里面的value
			      }
			      // Query paramters will be ?search=[term]&page=[page]
			      return query;
			    },
			    delay: 500,
			    processResults: function (data, params) {
			      //返回的选项必须处理成以下格式
			      var results = data.data;

			      return {
			        results: results  //必须赋值给results并且必须返回一个obj
			      };
			    }
			  }
	});
	
	$("#agentSelectId").select2({
		"ajax":{
		    url: _ctx+"/back/common/agent/getAgentUser",
		    data: function (params) {
		      var query = { //请求的参数, 关键字和搜索条件之类的
		        name: params.term //select搜索框里面的value
		      }
		      // Query paramters will be ?search=[term]&page=[page]
		      return query;
		    },
		    delay: 500,
		    processResults: function (data, params) {
		      //返回的选项必须处理成以下格式
		      var results = data.data;

		      return {
		        results: results  //必须赋值给results并且必须返回一个obj
		      };
		    }
		}
	});

})

function saveOrUpdate(){
	 $("#addForm").validate(function (result) {		    
		  	if(result){
		  		if(!checkedForm()){
			    	return;
			    }
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#addForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/oldHouse/master/saveOrUpdate",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							modalSuccess("保存成功");
							location=_ctx+"/back/frame/oldHouse/master/master_add?id="+resultVo.data
						}
		  			}
		  		})
		  	}
	 })
}

function saveOrUpdateNext(){
	 $("#addForm").validate(function (result) {
		  	if(result){
		  		if(!checkedForm()){
			    	return;
			    }
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#addForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/oldHouse/master/saveOrUpdate",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							location=_ctx+"/back/frame/oldHouse/master/master_image_list?id="+resultVo.data
						}
		  			}
		  		})
		  	}
	 })
}
function goList(){
	location.href=_ctx+"/back/frame/oldHouse/master";
}

function setUnitPrice(){
	if($("#buildAreaId").val()!=''&&$("#priceId").val()){
		$("#unitPriceId").val(Math.ceil((parseInt($("#priceId").val()*10000)/parseInt($("#buildAreaId").val()))));
	}
}

function checkedForm(){
	if(parseInt($("input[name='floor']").val())>parseInt($("input[name='floorNum']").val())){
		setErrorContent("房源楼层不能大于总楼层");
		$("input[name='floor']")[0].focus();
		return false;
	}
	return true;
}

function featuresNum4(obj){
	if ($("[name='features']:checked").length > 4) {
		console.log(obj);
		$(obj).removeAttr("checked");
		setErrorContent("特点最多选择4种！");
		return;
	}
}
