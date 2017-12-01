$(function(){
	// swiper
            $('body').scrollspy({ target: '#nav_ceiling' });
        		var galleryTop = new Swiper('.gallery-top', {
        	                nextButton: '.swiper-button-next',
        	                prevButton: '.swiper-button-prev',
        	                spaceBetween: 10
        	        });
        	        var galleryThumbs = new Swiper('.gallery-thumbs', {
        	                nextButton: '.swiper-button-next',
        	                prevButton: '.swiper-button-prev',
        	                spaceBetween: 10,
        	                centeredSlides: true,
        	                slidesPerView: 'auto',
        	                touchRatio: 0.2,
        	                slideToClickedSlide: true
	        });
	//头部导航 
	        $(".newhouse_nav").delegate("a","click",function(){		
	            $(this).addClass("active").siblings().removeClass("active");
		        $(this).children().attr({"style":"border-top:10px solid #2775e9"});
		        $(this).siblings().children().removeAttr("style");
	        });
	//侧边栏
	        $(".sidebar_nav").delegate("li","mouseover",function(){
	                $(this).find(".sidebar_text").show();
	        });
	        $(".sidebar_nav").delegate("li","mouseout",function(){
	                $(this).find(".sidebar_text").hide();
	        }); 
	// 吸顶
	        var h = document.body.clientHeight,
	        h1 = 700;
	        window.onscroll=function(){
	                var t = $(document).scrollTop();
	                if(t >= h1){
	                        $("#nav_ceiling").fadeIn("slow");
	                }
	                if(t < h1){
	                        $("#nav_ceiling").fadeOut("slow");
	                }
	        }
	//titlt_nav
	        $(".titlt_nav").delegate("li","click",function(){		
	                $(this).addClass("navActive").siblings().removeClass("navActive");
	        });

	     




});
