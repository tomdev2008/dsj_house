$.fn.masterAdd = function (option) {
	this._init = function (argument) {
		self=this;
		 $.ajax({
	            type: "GET",
	            url: option.url,
	            data:{},
	            dataType: "json",
	            success: function(data){
	              self.render(data.data);
	            }
	      });
	}
	
	this.render=function(data){
		var source   = $("#master_add").html();
	    var template = Handlebars.compile(source);
	    console.info(JSON.stringify(data));
	    var html    = template(data);
	    $("#dsj_form").append ( html);
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