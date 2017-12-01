$(function() {
	$("#dicSelectId").select2({
		"ajax" : {
			url : _ctx + "/back/oldHouse/master/getDic",
			data : function(params) {
				var query = { // 请求的参数, 关键字和搜索条件之类的
					name : params.term
				}
				return query;
			},
			delay : 1500,
			processResults : function(data, params) {
				var results = data.data;
				return {
					results : results
				};
			}
		}
	});
	
	$("#agentUserSelect").select2({
		"ajax" : {
			url : _ctx + "/back/rentHouse/general/origin/getAgentUser",
			data : function(params) {
				var query = { // 请求的参数, 关键字和搜索条件之类的
					name : params.term
				}
				return query;
			},
			delay : 1500,
			processResults : function(data, params) {
				var results = data.data;
				return {
					results : results
				};
			}
		}
	});
	
	$("[name='rentType']").change(function(){
	    $("#genderType").toggle();
	})
	initRentType();

})

function initRentType(){
	if($("[name='rentType']").filter(':checked').val()==1){
		$("#genderType").toggle();
	}
}

function saveOrUpdate(isNext) {
	$("#addForm").validate(
		function(result) {
			if (result) {
				$.ajax({
					type : "post",
					async : true,
					data : $("#addForm").serialize(),
					dataType : "json",
					url : _ctx
							+ "/back/rentHouse/general/origin/saveOrUpdate",
					success : function(resultVo) {
						if (resultVo.status != 200) {
							setErrorContent(resultVo.message);
						} else {
							if(isNext == "yes"){
								location = _ctx+"/back/frame/rentHouse/general/origin/to_image_list?id="
									+resultVo.data;
							}else{
								setErrorContent("保存成功");
								$("#id").val(resultVo.data);
							}
							
						}
					}
				})
			}
		})
}

function cancel(){
	location = _ctx+"/back/frame/rentHouse/general/origin";
}