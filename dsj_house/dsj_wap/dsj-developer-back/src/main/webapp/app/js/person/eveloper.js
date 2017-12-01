function reader(data){
	var source   = $("#eveloper_compay_template").html();
	var template = Handlebars.compile(source);
	var html    = template(data);
	 $("#dsj_form").append ( html);
	 
	 var newHouses=data.newHouses;
	 var options = [];  
	var ids=[];
	if(newHouses!=null){
		for(i=0;i<newHouses.length;i++){
			 options.push({  
				 id :newHouses[i].id,  
				 name : newHouses[i].name  
				 }); 
			 ids.push(newHouses[i].id);
		}
	}
	 
	 var $selectizeLoupan=$('#loupan_name').selectize({
          plugins: ['remove_button'],
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            create: false,
            render: {
                option: function(item, escape) {
                    return '<option><span class="description">' + item.name+'</span></option>';
                }
            },
            
            load: function(query, callback) {
                if (!query.length) return callback();
                $.ajax({
                    url: _url+"/back/common/getNewHouseName",
                    type: 'POST',
                    data:{
              name:$('#estate-selectized').val()
            },
            datatype:"json",
                    error: function() {
                        callback();
                    },
                    success: function(res) {
                        callback(res.data);
                    }
                });
            }
        });
	 console.info(options);
	 var selectize = $selectizeLoupan[0].selectize;
	 selectize.addOption(options);
	 selectize.setValue(ids);
	  singleUpload("filePicker1","showImg1","licenseUri1");
	  singleUpload("filePicker2","showImg2","licenseUri2");
}

function addEveloper(){
	 loupan_name="";	
	$(".item").each(function(){ //遍历全部option
		
		loupan_name+=$(this).attr("data-value")+","; //获取option的
	});
	
	if(loupan_name.length>0){
		loupan_name=loupan_name.substring(0,loupan_name.length-1);
		$("#loupanIds").val(loupan_name);
	}
	$("#dsj_form").verify();
	 $("#dsj_form").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:false,
		  			data:$("#dsj_form").serialize(),
		  			dataType:"json",
		  			url:_url+"/back/person/eveloper/save_evelopers_update",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							
							show_box(2,"保存成功");
							setTimeout(function () { 
								location=_url+"/app/person-detail.html";
							}, 3000); 
							
						}
		  			}
		  		})
		  	}
	 })
}

