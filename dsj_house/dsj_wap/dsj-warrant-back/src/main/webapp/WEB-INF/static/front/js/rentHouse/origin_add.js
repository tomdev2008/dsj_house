$.fn.originAdd = function (option) {
	this._init = function (argument) {
		self=this;
		 $.ajax({
	            type: "GET",
	            url: option.url,
	            data:{id:4},
	            dataType: "json",
	            success: function(data){
	              self.render(data.data);
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

function saveOrUpdate(){
	$("#floor_v").val($("#floorNum").val()+$("#floor").val());
	$("#dsj_form").verify();
	 $("#dsj_form").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#dsj_form").serialize(),
		  			dataType:"json",
		  			url:_url+"/back/oldHouse/master/saveOrUpdate",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							location=_url+"/app/ershou.html";
						}
		  			}
		  		})
		  	}
	 })
}

function toAdd(){
	location=_url+"/app/ershou-form.html";
}