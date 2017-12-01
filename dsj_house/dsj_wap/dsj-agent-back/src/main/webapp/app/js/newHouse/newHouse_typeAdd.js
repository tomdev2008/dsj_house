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
        singleUpload("filePicker1","showImg1","licenseUri1");
	}

}
function setName(_this){
	$(_this).next("input").val($("#orientations option:selected").text());
}
function saveAddlayout(){
	var wyType=$("input[name='wyType']:checked").val();
	if(wyType==undefined){
		setErrorContent("物业类型必选");
		return;
	}
	var room=$('#room').val();
	if(room==""){
		setErrorContent("居室必选");
		return;
	}
	var floor=$("#floor").val();
	if(floor==""){
	}else if(floor>100){
		 setErrorContent("层高不能大于100米");
		 return;
	}
		$("#dsj_form").verify();
		 $("#dsj_form").validate(function (result) {
			  	if(result){
			  		$.ajax({
			  			type:"post",
			  			async:true,
			  			data:$("#dsj_form").serialize(),
			  			dataType:"json",
			  			url:_url+"/agent/back/**/newHouse/save_layout_add",
			  			success:function(resultVo){
			  				if(resultVo.status!=200){
								 setErrorContent(resultVo.message);
							}else{
								 location=_url+"/app/houseList.html?id="+$("#typeId").val();
							}
			  			}
			  		})
			  	}
		 })
}

function saveOrUpdate(){
	$("#dsj_form").verify();
	$("#dsj_form").validate(function (result) {
		if(result){
			$.ajax({
	  			type:"post",
	  			async:true,
	  			data:$("#dsj_form").serialize(),
	  			dataType:"json",
	  			url:_url+"/back/houseNews/addHouseNews",
	  			success:function(resultVo){
	  				if(resultVo.status!=200){
						setErrorContent(resultVo.message);
					}else{
						location=_url+"/app/house-news.html";
					}
	  			}
			})
		}
	})
}

function cancle(){
	 location=_url+"/app/houseList.html?id="+$("#typeId").val();;
}
