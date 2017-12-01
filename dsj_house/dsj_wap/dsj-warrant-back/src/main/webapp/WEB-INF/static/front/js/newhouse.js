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
	//titlt_nav
	        $(".titlt_nav").delegate("li","click",function(){		
	                $(this).addClass("navActive").siblings().removeClass("navActive");
	        });

	        var album = {
                    albumList : [],
                    current:0,
                    currentPhoto:0,
                    albumListLength:'',
                    albumLength:'',
                    albumSelector:".gallery-top .swiper-wrapper" ,
                    albumListSelector:".gallery-thumbs .swiper-wrapper",
                    albumPrevButton:".gallery-top .swiper-button-prev",
                    albumNextButton:".gallery-top .swiper-button-next",
	                albumListTemplate:function(background ,nameAndNum) {
	                        var str = '<div class="swiper-slide" style="background:url('+background+')no-repeat center ;background-size:100%; "><div class="album_name">'+nameAndNum+'</div></div>';
	                        return str;
	                },
	                albumTemplate:function(background) {
	                        var str = '<div class="swiper-slide" style="background:url('+background+')no-repeat center ;background-size:100%; "></div>';
	                        return str;
	                },
	                getAlbums: function () {
	                        var self = this;
	                        // $.ajax({
	                        //   type: "POST",
	                        //   url: _ctx+"/front/newHouse/newHouse_picture?id="+$("#newHouseId").val(),
	                        //   dataType: "json",
	                        //   success: function(result){
	                        // 	  if(result.status!=200){
	                        // 		  console.log(result.message);
	                        // 	  }else{
	                        // 		  self.albumList = result.data;
	                        // 		  self.setCover();
	                        // 	  }
	                        //   }
	                        // });
                            this.albumList = [
                            {
                                name:"样板间",
                                imglist:["img/login/bannerimg.png"]
                            },
                            {
                                name:"样板间",
                                imglist:["img/login/bannerimg.png","img/fangchan.jpg"]
                            },
                            {
                                name:"样板间",
                                imglist:["img/login/bannerimg.png","img/fangchan.jpg"]
                            },
                            {
                                name:"样板间",
                                imglist:["img/login/bannerimg.png","img/fangchan.jpg"]
                            },
                            {
                                name:"样板间",
                                imglist:["img/login/bannerimg.png","img/fangchan.jpg"]
                            },
                            {
                                name:"样板间",
                                imglist:["img/login/bannerimg.png","img/fangchan.jpg"]
                            }];
                            self.setCover();
	                },
	                setCover: function () {
	                        $(this.albumListSelector).empty()
	                        var html = "";
	                        for (var i = 0; i < this.albumList.length; i++) {
	                            var name = this.albumList[i].name,
	                                length = this.albumList[i].imglist.length,
	                                background = this.albumList[i].imglist[0],
	                                nameAndNum = name + '('+length+')';
	                                html += this.albumListTemplate(background,nameAndNum);
	                        }
	                        $(this.albumListSelector).html(html)
	                        this.refresh();
	                        this.setAlbums();
	                },
	                init:function () {
                        var self = this;
                        self.getAlbums();
                        galleryThumbs.on('tap', function(swiper){
                                galleryTop.slideTo(0);
                                self.setAlbums();
                        })
                        galleryThumbs.on('SlideChangeStart', function(swiper){
                                galleryTop.slideTo(0);
                                self.setAlbums();
                        })
                        galleryTop.on('SlideChangeStart', function(swiper){
                                var photo = galleryTop.activeIndex,
                                    album = galleryThumbs.activeIndex,
                                    src = self.albumList[album].imglist[photo];
                                $(".dsj_kuang_opacity").css("background-image","url("+src+")")
                        })
                        galleryTop.on('ReachEnd', function(swiper){
                                $(self.albumNextButton).off(".dsj");
                                $(self.albumPrevButton).off(".dsj");
                                $(self.albumNextButton).one("click.dsj",function(argument) {
                                    if(self.albumList[self.current].imglist.length == 1){
                                        return;
                                    }
                                    galleryThumbs.slideNext();
                                    galleryTop.slideTo(0);
                                })
                                galleryTop.once("SlideChangeStart",function() {
                                    self.buttonEvent();
                                })
                        })
                        galleryTop.on('ReachBeginning', function(swiper){
                            $(self.albumPrevButton).off(".dsj");
                            $(self.albumNextButton).off(".dsj");
                            $(self.albumPrevButton).one("click.dsj",function(argument) {
                                if(self.albumList[self.current].imglist.length == 1){
                                    return;
                                }
                                galleryThumbs.slidePrev();
                                galleryTop.slideTo(self.albumLength - 1);
                            })
                        })
                },
                setAlbums: function (argument) {
                        var self = this;
                        $(this.albumSelector).empty()
                        this.current = galleryThumbs.activeIndex;
                        this.currentPhoto = galleryTop.activeIndex;
                        this.albumLength = this.albumList[this.current].imglist.length;
                        this.albumListLength = this.albumList.length;
                        var html = "";
                            src = this.albumList[this.current].imglist[0];
                        for (var i = 0; i < this.albumList[this.current].imglist.length; i++) {
                                html += this.albumTemplate(this.albumList[this.current].imglist[i]);
                        }
                        $(this.albumSelector).html(html)
                        $(".dsj_kuang_opacity").css("background-image","url("+src+")")
                        this.refresh();
                        this.buttonEvent();

                },
                buttonEvent:function(argument) {
                    var self = this;
                    this.current = galleryThumbs.activeIndex;
                    this.currentPhoto = galleryTop.activeIndex;
                    this.albumLength = this.albumList[this.current].imglist.length;
                    this.albumListLength = this.albumList.length;

                    if(this.albumList[this.current].imglist.length == 1){
                        $(self.albumPrevButton).off(".dsj");
                        $(self.albumNextButton).off(".dsj");
                        $(this.albumNextButton).one("click.dsj",function(argument) {
                                galleryThumbs.slideNext();
                                galleryTop.slideTo(0);
                        })
                        $(this.albumPrevButton).one("click.dsj",function(argument) {
                                galleryThumbs.slidePrev();
                                galleryTop.slideTo(self.albumLength - 1);
                        })
                    }
                    // real 1st
                    if( this.current == 0 && this.currentPhoto ==0 ) {
                        $(self.albumPrevButton).off(".dsj");
                        $(this.albumPrevButton).one("click.dsj",function(argument) {
                                galleryThumbs.slideTo(self.albumListLength - 1);
                                galleryTop.slideTo(self.albumLength - 1);
                        })
                    }

                    if( (self.current == (self.albumListLength - 1)) && ( galleryTop.activeIndex == (self.albumLength - 1)) ){
                        $(self.albumNextButton).off(".dsj");
                        $(self.albumNextButton).one("click.dsj",function(argument) {
                                galleryThumbs.slideTo(0);
                                galleryTop.slideTo(0);
                        })

                    }

                },
                nextAlbum: function (argument) {
                },
                prevAlbum: function (argument) {
                },
                refresh: function(argument) {
                        galleryTop.update();
                        galleryThumbs.update();
                }
        }
        album.init();




})
