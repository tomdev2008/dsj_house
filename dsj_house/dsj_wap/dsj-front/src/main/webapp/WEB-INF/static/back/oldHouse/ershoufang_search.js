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
	  			url:_ctx+"/keyword/search",
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
        location = _ctx + "/ershoufang/list?kw="+seekValue;
    })
};