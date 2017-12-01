
//针对多选框
$('.more > label').on('click', function (event) {
	var inputItem = $(this).find('input');
	inputItem.prop('checked') ? inputItem.parent().addClass('praise_icon_active') : inputItem.parent().removeClass('praise_icon_active');
	event.stopPropagation();
});

//针对单选框
$('.smile_kuang > label').on('click', function (event) {

	var event = event || window.event;
//		bad_inputs = $(".bad_smile>input"),
		//flag = true;
//	$('.bad_smile').each(function (index, item) {
//		if ($(item).find('input:checked').length >= 1 && flag) {
//			return flag = false;
//		}
//	});
//	if(!flag){
//		$(".more").addClass("bad_icon");
//	}else{
//		$(".more").removeClass("bad_icon");
//	}

	
	if( $(this).text().trim() == "好评"){
		$(this).siblings().removeClass('good_smile_active');
		$(this).siblings().removeClass('bad_smile_active');
		$(this).addClass('tiptop_smile_active');
	}
	 else if($(this).text().trim() == "中评"){
		$(this).siblings().removeClass('tiptop_smile_active');
		$(this).siblings().removeClass('bad_smile_active');
		$(this).addClass('good_smile_active');
	}
	else{
		$(this).siblings().removeClass('good_smile_active');
		$(this).siblings().removeClass('tiptop_smile_active');
		$(this).addClass('bad_smile_active');
	}
	event.stopPropagation();
});




