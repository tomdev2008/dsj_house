function empty_f(elem){
  //清空
  var ursnameCode_value = $(elem).val();
  $(elem).on("keyup",function(){
    empty_botton(elem);
  });
  function empty_botton(elem){
    var ursnameCode_value = $(elem).val();
      if(ursnameCode_value != ""){
          $(elem).next().show();  
        }
  };
  $(".demptyText").on("click",function(event){
    $(elem).val("");
    $(this).hide();
    event.stopPropagation();
  });
};

empty_f(".phone");

empty_f(".ursname");

empty_f(".password");
// 小眼睛
$(".showTextlabel").on("click",function(event){
	var ursnameCode_value = $.trim($(".passwordCode2").val());
	if(ursnameCode_value != ""){
		var checked_input = $("#showText");
		console.log(checked_input[0].checked);
		if(checked_input[0].checked){
			$(this).removeClass("showTextActive");
			$(".passwordCode2").attr("type","password");
		}
		else{
			$(this).addClass("showTextActive");
			$(".passwordCode2").attr("type","text");
		}
	}
	event.stopPropagation();
});
// 验证码
var mybutton = $(".verifyCodeButton").html();
      $(".verifyCodeButton").on("click",function () {
        event.stopPropagation(); 
        mybutton = 120;
        $(".verifyCodeButton").addClass("verifyCodeActive").attr("disabled", true);
        var time = setInterval(function(){
          mybutton--;
          $(".verifyCodeButton").html(mybutton+"s重新获取");
          if(mybutton <= 0){
            clearInterval(time);
            $(".verifyCodeButton").removeClass("verifyCodeActive").html("获取验证码").removeAttr("disabled");
          }
        },1000);
});


	

