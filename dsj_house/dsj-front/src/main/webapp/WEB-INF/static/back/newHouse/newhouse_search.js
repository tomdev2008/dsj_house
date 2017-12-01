$(function(){
	 seekevent();
	 $(".grabble_kuang").hide();
	 if (window.location.pathname.indexOf("line") > -1) {
		 $("#search_eare").removeClass("active");
		 $("#search_subway").addClass("active");
	 } else {
		 $("#search_subway").removeClass("active");
		 $("#search_eare").addClass("active");
	 }
})

//输入框
function seekevent(){
    var seekValue = $("#inputseek").val();
    // click事件
    $("#inputseek").on("click",function(){
        seekValue =  $.trim($("#inputseek").val());
        if(seekValue != ""){
                $(".grabble_kuang").show();
        }
    })
    // keyup事件
    $("#inputseek").on("keyup",function(){
        seekValue =  $.trim($("#inputseek").val());
        if(seekValue == ""){
            $(".grabble_kuang").hide();
        }
        // 发送ajax请求
        if(seekValue != ""){
        	$.ajax({
	  			type:"post",
	  			async:true,
	  			data:{"name":seekValue},
	  			dataType:"json",
	  			url:_ctx+"/keyword/search/newHouse",
	  			success:function(resultVo){
	  				if(resultVo.status==200 && resultVo.data!=null){
	  				data=resultVo.data;
	  					$(".grabble_kuang").empty()
	  					for(i=0;i<data.length;i++){
	  						$(".grabble_kuang").append("<li>"+data[i].name+"</li>" );
	  					}
	  					$(".grabble_kuang").show();
					}
	  			}
	  		})
                
        }
    })
    // 将div中的内容赋值给input的value
    $(".grabble_kuang").delegate("li","click",function(){
        seekValue = $(this).html();
        location = _ctx + "/front/newHouse/list?kw="+seekValue;
    })
};
function searchResult(){
	location = _ctx+"/front/newHouse/list?k="+$("#inputseek").val();
}