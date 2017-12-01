//弹出框
    var box_affirm   = ".box_affirm",    //1
        box_defeated = ".box_defeated",  //2
        box_succeed  = ".box_succeed";   //3
    setTimeout(function(){
    	 $(".box_close").on("click",function(event){
    	        $("#popup_box").hide();
    	        event.stopPropagation(); 

    	    });
    	    $(".reset_botton").on("click",function(event){
    	        $("#popup_box").hide();
    	        event.stopPropagation(); 
    	    });
    	    $(".submit_botton").on("click",function(event){
    	         $("#popup_box").hide();
    	         event.stopPropagation();
    	    });
    },2000);
   

    function show_box(state,msg){
        $("#popup_box").show();
        if(state == 1){
            $('#affirm_text').html(msg);
            $(box_affirm).show();
        } else if(state == 2){
            $('#failMsg').html(msg);
            $(box_defeated).show();

        } else{
            $('#successMsg').html(msg);
            $(box_succeed).show();
        }
    }