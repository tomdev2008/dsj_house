$.fn.masterAdd = function (option) {
	
	
	this._init = function (id) {
		self=this;
		var data={};
		if(id!=null){
			data={"id":id};
		}
		 $.ajax({
	            type: "GET",
	            url: option.url,
	            async:false,
	            data:data,
	            dataType: "json",
	            success: function(data){
	              self.render(data.data);
	            }
	      });
		 
		 $("#dicSelectId").select2({
			 	placeholder: "小区名称",
				"ajax":{
				    url: _url+"/back/oldHouse/master/getDic",
				    async:false,
				    data: function (params) {
				      var query = { //请求的参数, 关键字和搜索条件之类的
				        name: params.term //select搜索框里面的value
				      }
				      // Query paramters will be ?search=[term]&page=[page]
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
		var source   = $("#master_add").html();
	    var template = Handlebars.compile(source);
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
							$("#oldMasterId").val(resultVo.data);
							setErrorContent("保存成功！");
						}
		  			}
		  		})
		  	}
	 })
}
function saveOrUpdateNext(){
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
							location=_url+"/app/ershou-pic.html?id="+resultVo.data;
						}
		  			}
		  		})
		  	}
	 })
}
function setUnitPrice(){
	if($("#buildAreaId").val()!=''&&$("#priceId").val()){
		$("#unitPriceId").val(Math.ceil((parseInt($("#priceId").val()*10000)/parseInt($("#buildAreaId").val()))));
	}
}

function S1()
{
  var v1=$("#floorNum").val();
  var v2=$("#floor").val();
  if((v1!=null || v1!="") && (v2!=null || v2!="")){
      if(v1<v2)
      {

         alert('总楼层不能小与楼层!');
         document.getElementById("TextBox1").value="";
      }
  }
   
}

function featuresNum4(obj){
	if ($("[name='features']:checked").length > 4) {
		
		$(obj).removeAttr("checked");
		$(obj).prop("checked",false)
		alert("特点最多选择4种！");
		return false;
	}
}