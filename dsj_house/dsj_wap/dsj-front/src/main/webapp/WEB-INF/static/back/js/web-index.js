$(function(argument) {
	$(document).on("click",".content-list__item a",function(e) {
		e.preventDefault();
	});
	$(document).on("click",".content-list__item",function(e) {
		var arrow = $(this).parent().find(".arrow");
		var index = $(this).index();
		$(this).parent().find(".content-list__item_active").removeClass("content-list__item_active");
		$(this).addClass("content-list__item_active").append(arrow);
		$(this).parentsUntil(".content").parent().find(".content-card-contianer").hide().eq(index).show();
	})
	$(document).on("click",".tab__item a",function(e) {
		e.preventDefault();
	});
	$(document).on("click",".tab__item ",function(e) {
		$(this).parent().find(".tab__item_active").removeClass("tab__item_active");
		$(this).addClass("tab__item_active");
		var placeholder = "";
		if($(this).index() == 0) {
				seekevent();
				 $(".grabble_kuang").hide();
			$("#inputseek").val("");
			placeholder = "请输入楼盘名称或地址";
		}else if($(this).index() == 1) {
				seekevent();
				 $(".grabble_kuang").hide();
			$("#inputseek").val("");
			placeholder = "请输入小区名称或地址";
		}else {
				seekevent();
				 $(".grabble_kuang").hide();
			$("#inputseek").val("");
			placeholder = "请输入区域，经纪人，公司";
		}
		$(".search-input__input").prop("placeholder",placeholder);
		
	})
})


