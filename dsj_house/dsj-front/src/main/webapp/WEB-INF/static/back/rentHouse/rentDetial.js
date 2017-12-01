$(function(){
	//房源配置
	peizhi();
	//房源推荐
	recommend();
	
	// swiper
    var galleryTop = new Swiper('.gallery-top', {
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        spaceBetween: 10,
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
    galleryTop.params.control = galleryThumbs;
    galleryThumbs.params.control = galleryTop;  
    
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
    h1 = h*0.3;
    window.onscroll=function(){
            var t = document.body.scrollTop;
            if(t >= h1){
                    $("#nav_ceiling").fadeIn("slow");
            }
            if(t < h1){
                    $("#nav_ceiling").fadeOut("slow");
            }
    }
    
    //地图
    $(".nav-tabs").delegate("li","click",function(){		
    	if(!$(this).hasClass("active")){
    		 $(this).addClass("active").siblings().removeClass("active");
    	}
    });

})

function peizhi(){
	var data_all = $(".house_deploy").attr("data-all");
	var dataArr = data_all.split(",");
	if( !dataArr || dataArr.length==0) return;
	$(".house_deploy").children("a").each(function(){
	    var data = $(this).attr("data-p");
	    for(var i=0 ; i<dataArr.length ; i++){
	    	if(dataArr[i] == data ){
	    		$(this).removeClass("hidden");
	    	}
	    }
	});
}

//房源推荐
function recommend(){
	var rentId = $("#rentId").val();
	$.ajax({
		type: "POST",
		url: _ctx+"/rentHouseDetail/getRecommend",
		data: {
			id : rentId 
		},
		dataType: "json",
		success: function(result){
			if(result.status!=200){
				alert(result.data);
			}else{
				var resHtml = "";
	      		var dataArr = result.data;
	      		if(!dataArr) {
	      			$("#house_recommend").append('<div class="remark_content"><div class="remark_definite">暂无数据</div></div>');
	      			return;
	      		}
	      		for(var i=0; i<dataArr.length; i++){
	      			resHtml += '<figure onclick="toRentDetail('+dataArr[i].id+');">\
									<img src="'+dataArr[i].pictureUrl+'" width="350" height="234" />\
									<p>'+dataArr[i].houseTitle+'</p>\
									<p>'+dataArr[i].tradingAreaName+'-'+dataArr[i].rentPrice+'</p>\
								</figure>'
	      		}
				$("#house_recommend").append(resHtml);
			}
		}
	});
}

function toRentDetail(id){
	location=_ctx+"/rentHouseDetail/detail?id="+id;
}