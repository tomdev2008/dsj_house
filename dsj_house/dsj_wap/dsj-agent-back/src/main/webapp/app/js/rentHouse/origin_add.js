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
              
              $("[name='rentType']").change(function(){
            	  $("#genderType").toggle();
              })
              initRentType();
            }
		 });
		
		$("#dicSelectId").select2({
			"ajax":{
			    url: _url+"/back/oldHouse/master/getDic",
			    async:false,
			    data: function (params) {
			      var query = { //请求的参数, 关键字和搜索条件之类的
			        name: params.term //select搜索框里面的value
			      }
			      return query;
			    },
			    delay: 1500,
			    processResults: function (data, params) {
			      //返回的选项必须处理成以下格式
			      var results = data.data;
			      return {
			        results: results  //必须赋值给results并且必须返回一个obj
			      };
			    }
			  }
		});
		
	}
	
	this.render=function(data){
		var source = $("#dsj_info_template").html();
        var template = Handlebars.compile(source);
	    var html = template(data);
        $("#dsj_info_container").prepend(html);
	}

}

function saveOrUpdate(isNext){
	$("#dsj_form").verify();
	$("#dsj_form").validate(function (result) {
		if(result){
			$.ajax({
	  			type:"post",
	  			async:true,
	  			data:$("#dsj_form").serialize(),
	  			dataType:"json",
	  			url:_url+"/back/rentHouse/general/origin/saveOrUpdate",
	  			success:function(resultVo){
	  				if(resultVo.status!=200){
						 setErrorContent(resultVo.message);
					}else{
						if(isNext == 1){
							location=_url+"/app/rent-origin-pic.html?id="+resultVo.data;
						}else{
							$("#id").val(resultVo.data);
							alert("保存成功");
						}
					}
	  			}
			})
		}
	})
}

function toAdd(){
	location=_url+"/app/ershou-form.html";
}

function initRentType(){
	if($("[name='rentType']").filter(':checked').val()==1){
		$("#genderType").toggle();
	}
}

function setDirectoryName(obj,name,newName){
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

function cancel(){
	location=_url+"/app/rent-origin-list.html";
}