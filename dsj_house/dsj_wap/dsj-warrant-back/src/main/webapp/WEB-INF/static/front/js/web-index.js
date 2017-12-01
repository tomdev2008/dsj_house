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
})