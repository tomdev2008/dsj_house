 var _url="";
 var pathName = window.document.location.pathname;
_url="/" +pathName.split("/")[1];
if(_url=="/app"){
	_url='';
}
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
empty_f("#phone");
empty_f("#user_label");
empty_f(".ursname");
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


	

