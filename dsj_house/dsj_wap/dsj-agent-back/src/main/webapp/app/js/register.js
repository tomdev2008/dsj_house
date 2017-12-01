(function(){
	//清空
	var ursnameCode_value = $(".ursnameCode").val();
	$(".ursnameCode").on("keyup",function(){
		empty_botton();
	});
	function empty_botton(){
		var	ursnameCode_value = $(".ursnameCode").val();
			if(ursnameCode_value != ""){
		         $(".demptyText").show();
		    }
	};
	$(".demptyText").on("click",function(event){
		$(".ursnameCode").val("");
		$(this).hide();
		event.stopPropagation();
	});
})();
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


	

