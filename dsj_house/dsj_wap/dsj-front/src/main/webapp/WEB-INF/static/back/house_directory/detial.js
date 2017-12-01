$(function(){
	
	// swiper
    var galleryTop = new Swiper('.gallery-top', {
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
    });
    var galleryThumbs = new Swiper('.gallery-thumbs', {
    	nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        spaceBetween: 10,
        centeredSlides: false,
        slidesPerView: 'auto',
        touchRatio: 0.2,
        slideToClickedSlide: true
    });
    galleryTop.params.control = galleryThumbs;
    galleryThumbs.params.control = galleryTop;  
    
    //侧边栏
    $(".sidebar_nav").delegate("li","mouseover",function(){
            $(this).find(".sidebar_text").show();
    });
    $(".sidebar_nav").delegate("li","mouseout",function(){
            $(this).find(".sidebar_text").hide();
    }); 
    
	//地图
    $(".nav-tabs").delegate("li","click",function(){		
    	if(!$(this).hasClass("active")){
    		 $(this).addClass("active").siblings().removeClass("active");
    	}
    });

})