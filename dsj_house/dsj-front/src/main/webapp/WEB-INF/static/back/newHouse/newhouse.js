var houseTypeCommand = {
		pageFirst : 0,
		pageSize : 5,
		room : "",
		newHouseId:null
	}
var pictureData;
$(function(){
	//初始加载全部户型
	getHouseType();
	//点评
	showComment(3);
	
	// swiper
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
    h1 = h*0.2;
    window.onscroll=function(){
            var t = $(document).scrollTop()
            if(t >= h1){
                    $("#nav_ceiling").fadeIn("slow");
            }
            if(t < h1){
                    $("#nav_ceiling").fadeOut("slow");
            }
    }
    //titlt_nav 户型
    $(".titlt_nav1.houseType").delegate("li","click",function(){		
        	if(!$(this).hasClass("navActive")){
    		 $(this).addClass("navActive").siblings().removeClass("navActive");
                getHouseType();
    	}
    });
    
    //titlt_nav 点评
    $(".titlt_nav.comment").delegate("li","click",function(){		
    	if(!$(this).hasClass("navActive")){
    		 $(this).addClass("navActive").siblings().removeClass("navActive");
    		 $(this).closest(".titlt_nav").next(".more").find("a").toggle();
    	}
    });
    
    //地图
    $(".nav-tabs").delegate("li","click",function(){		
    	if(!$(this).hasClass("active")){
    		 $(this).addClass("active").siblings().removeClass("active");
    	}
    });

    var bottom = {
      "preview_window" : 4,
      "distence" : 120,
      "total" : 0,
      "start" : 0,
      "current" : 1,
      "move_right":move_right,
      "move_left":move_left
    }

    var album = {
        albumList : [],
        current:0,
        albumListLength:'',
        albumLength:'',
        albumSelector:".carousel-inner" ,
        albumListSelector:".preview-indicators",
        albumPrevButton:".gallery-top .swiper-button-prev",
        albumNextButton:".gallery-top .swiper-button-next",
        albumListTemplate:function(background ,nameAndNum,index) {
        	if ( index ==0 ) {
            	var str =  '<div data-target="#carousel" class="preview-pic active"><img class="indicateimg" src="' + background +'?x-oss-process=image/resize,m_fixed,h_70,w_100" alt="..."><div class="album_name">'+nameAndNum+'</div></div>';

        	} else {
            	var str =  '<div data-target="#carousel" class="preview-pic"><img class="indicateimg" src="' + background +'?x-oss-process=image/resize,m_fixed,h_70,w_100" alt="..."><div class="album_name">'+nameAndNum+'</div></div>';
        	}
                return str;
        },
        albumTemplate:function(background,index,i) {
        	if ( index ==0 ) {
        		var str = '<div class="item active" imgVal="'+background+'"><img onclick="toPicture('+i+')" src="'+background+'?x-oss-process=image/resize,m_fixed,h_397,w_533" alt="..."></div>';

        	} else {
        		var str = '<div class="item" imgVal="'+background+'"><img onclick="toPicture('+i+')" src="'+background+'?x-oss-process=image/resize,m_fixed,h_397,w_533" alt="..."></div>';
        	}
                return str;
        },
        getAlbums: function () {
                var self = this;
                 $.ajax({
                   type: "POST",
                   url: _ctx+"/front/newHouse/newHouse_picture?id="+$("#newHouseId").val(),
                   dataType: "json",
                   success: function(result){
                 	  if(result.status!=200){
                 		  console.log(result.message);
                 	  }else{
                 		 pictureData = result.data;
                 		 //首页相册轮播
                 		  self.albumList = result.data;
                 		  self.setCover();
                      bottom.total = self.albumList.length;
                      self.albumListLength = self.albumList.length;

                 		  //首页相册展示
                 		  setPicture(1);
                 	  }
                   }
                 });
                
        },
        setCover: function () {
                $(this.albumListSelector).empty()
                var html = "";
                for (var i = 0; i < this.albumList.length; i++) {
                    var name = this.albumList[i].name,
                        length = this.albumList[i].imgList.length,
                        background = this.albumList[i].imgList[0],
                        nameAndNum = name + '('+length+')';
                        html += this.albumListTemplate(background,nameAndNum,i);
                }
                $(this.albumListSelector).html(html)
                this.refresh();
                this.setAlbums();
        },
        init:function () {
          var self = this;
          self.getAlbums();
          $(document).on("click",".preview-pic",function(argument) {
          	$(".preview-pic").removeClass("active");
          	$(this).addClass("active");
          	self.setAlbumsByClick($(this).index())
          })

          $.fn.carousel.Constructor.prototype.next = function () {
            if (this.sliding) return
            if (self.isAbleNext()) {
              self.nextAlbum()
            } else {
              return this.slide('next')
            }
          }
          $.fn.carousel.Constructor.prototype.prev = function () {
            if (this.sliding) return
            if (self.isAblePrev()) {
              self.prevAlbum()
            } else {
              return this.slide('prev')
            }
          }
         },
        setAlbumsByClick: function (index) {
          $(this.albumSelector).empty()
          this.current = index;
          this.albumLength = this.albumList[this.current].imgList.length;
          var html = "";
              src = this.albumList[this.current].imgList[0];
          for (var i = 0; i < this.albumList[this.current].imgList.length; i++) {
                  html += this.albumTemplate(this.albumList[this.current].imgList[i],i,index);
          }
          $(this.albumSelector).html(html)
          this.refresh();
    			this.buttonEvent();                
    		},
        setAlbums: function (argument) {
            $(this.albumSelector).empty()
            // this.current = galleryThumbs.activeIndex;
            this.current = 0;
            this.albumLength = this.albumList[this.current].imgList.length;
            var html = "";
                src = this.albumList[this.current].imgList[0];
            for (var i = 0; i < this.albumList[this.current].imgList.length; i++) {
                    html += this.albumTemplate(this.albumList[this.current].imgList[i],i,0);
            }
            $(this.albumSelector).html(html)
            $("#dsj_bannder_kuang").css("background-image","url("+src+")")
            this.refresh();
			this.buttonEvent();                
		},
    nextAlbum:function(argument) {
      if (this.current + 1 <= this.albumListLength - 1) {
        var next = this.current + 1;
      } else {
        var next = 0
      }
      $(".preview-pic").eq(next).trigger("click");
      // this.setAlbumsByClick(next)
    },
    prevAlbum:function(argument) {
      if ( this.current - 1 >= 0 ) {
       var prev = this.current - 1;
      } else {
       var prev = this.albumListLength -1;
      }
      $(".preview-pic").eq(prev).trigger("click");
      $('.carousel').carousel(this.albumLength-1);
      // this.setAlbumsByClick(prev)

    },
    autoScroll:function(argument) {
      var self = this;
      var $active = $('.carousel .dsj-carousel__inner .active');
      var $items = $active.parent().children('.item');
      var itemIndex = $items.index($active) + 1;
      if(itemIndex == this.albumLength){
        this.nextAlbum()
        return true;
      }
      if(itemIndex == 1){
        this.prevAlbum()
        return true;
      }
      return false;
    },
    isAbleNext:function(argument) {
      var self = this;
      var $active = $('.carousel .dsj-carousel__inner .active');
      var $items = $active.parent().children('.item');
      var itemIndex = $items.index($active) + 1;
      if(itemIndex == this.albumLength){
        return true;
      }
      return false;
    },
    isAblePrev:function(argument) {
      var self = this;
      var $active = $('.carousel .dsj-carousel__inner .active');
      var $items = $active.parent().children('.item');
      var itemIndex = $items.index($active) + 1;
      if(itemIndex == 1){
        return true;
      }
      return false;
    },
		buttonEvent:function(argument) {
        },                
        refresh: function(argument) {
          $('.carousel').carousel()
        }
    }
  album.init();
	function move_right() {
		if ( this.total - this.start > this.preview_window ) {
		  this.start += this.preview_window;
		  $(".preview-indicators").css("margin-left",-this.start*this.distence)
		} else {
		  return;
		}
	}
	function move_left() {
		if ( this.start >= this.preview_window) {
		  this.start -= this.preview_window;
		  $(".preview-indicators").css("margin-left",-this.start*this.distence)
		} else {
		  return
		}
	}
	
	// var move_window_size = whole - preview_window;

	$(document).on("click",".preview-prev",function(argument) {
	  bottom.move_left();
	  // var margin_left = $(".preview-indicators").css("margin-left");
	  // margin_left = parseInt(margin_left);
	  // $(".preview-indicators").css("margin-left",margin_left-107)
	})
	$(document).on("click",".preview-next",function(argument) {
	      bottom.move_right();

	  // var margin_left = $(".preview-indicators").css("margin-left");
	  // margin_left = parseInt(margin_left);
	  // $(".preview-indicators").css("margin-left",margin_left+107)

	})

})
//户型ajax
function getHouseType(){
	houseTypeCommand.newHouseId=$("#newHouseId").val();
	houseTypeCommand.room = $(".houseType li.navActive").attr("room");
	 $.ajax({
         type: "POST",
         url: _ctx+"/front/newHouse/newHouse_type",
         data:houseTypeCommand,
         dataType: "json",
         success: function(result){
       	  if(result.status!=200){
       		  console.log(result.message);
       	  }else{
       		 $("#houseType").empty();
       		 list = result.data;
       		if(null!=list){
	       		 for(var i in list){
	       			 if(i<5){
	       				 var houseTypeStr= "";
	       				 if(null!=list[i].room){
	       					houseTypeStr+=list[i].room+"室"
	       				 }
	       				 
	       				if(null!=list[i].hall){
	       					houseTypeStr+=list[i].hall+"厅"
	       				 }
	       				
	       				if(null!=list[i].toilet){
	       					houseTypeStr+=list[i].toilet+"卫"
	       				 }
	       				var src = "";
	       				if(null!=list[i].imgUrl&&""!=list[i].imgUrl){
	       					src=list[i].imgUrl;
	       				}else{
	       					src = _ctx+"/static/front/img/newHouseType.png"
	       				}
	       				
	       				var jzmj = "";
	       				if(null!=list[i].builtUp&&list[i].builtUp!=""){
	       					jzmj = "<p>建筑面积: "+list[i].builtUp+" ㎡</p>";
	       				}else{
	       					jzmj = "<p>建筑面积: 暂无</p>";
	       				}
	       				
	       				 var str = "<figure><img onclick='houseTypeModal("+JSON.stringify(list[i])+")' src='"+src+"?x-oss-process=image/resize,m_pad,h_290,w_220,color_FFFFFF'/><h4>"+list[i].houseName+" "+houseTypeStr+"</h4>"+jzmj+"</figure>";
		      			  $("#houseType").append(str);
	       			 }
	      		  }
       		}
   			
       	  }
         }
       });
}
//楼盘相册展示
function setPicture(type){
	$("#housePicture").empty();
	//全部相册
	if(type==1){
		$("#allPicture").addClass("navActive").siblings().removeClass("navActive");
		$("#housePictureCount li").eq(0).nextAll().remove();
		var j = 0;
		for(var i in pictureData){
			j=j+pictureData[i].imgList.length;
			var str = "<figure><img onclick='toPicture("+i+")' src='"+pictureData[i].imgList[0]+"?x-oss-process=image/resize,m_fixed,h_180,w_222'/><p>"+pictureData[i].name+"("+pictureData[i].imgList.length+")</p></figure>";
			if(i<5){
				$("#housePicture").append(str);
			}
			$("#housePictureCount").append("<li typeName="+pictureData[i].name+"  onclick=setPicture('"+pictureData[i].name+"')>"+pictureData[i].name+"("+pictureData[i].imgList.length+")"+"</li>");
		}
		$("#allPicture").text("全部相册("+j+")");
	}else{
		//各个相册
		$("li[typeName="+type+"]").addClass("navActive").siblings().removeClass("navActive");
		for(var i in pictureData){
			if(pictureData[i].name==type){
				imgList = pictureData[i].imgList;
				for(var j in imgList){
					var str = "<figure><img class='pictureModal' src='"+imgList[j]+"?x-oss-process=image/resize,m_fixed,h_180,w_222'/><p></p></figure>";
					if(j<5){
						$("#housePicture").append(str);
					}
				}
				return false;
			}
		}
	}
	
}


//加载楼盘点评 用户点评
function showComment(type){
	$.ajax({
        type: "POST",
        url: _ctx+"/front/comment/getComment",
        data:{
        	newHouseId:$("#newHouseId").val(),
        	objectType:type,
        	pageSize:2,
        	pageFirst:1
        },
        dataType: "json",
        success: function(result){
      		var resHtml = "";
      		var dataArr = result.data;
      		$("#user_remark").children(".remark_content").remove();
      		if(!dataArr) {
      			$("#user_remark").append('<div class="remark_content"><div class="remark_definite">暂无数据</div></div>');
      			return;
      		}
      		for(var i=0; i<dataArr.length; i++){
      			var str = dataArr[i].content;
      			var showAll = "";
      			if(str.length>80){
      				str = str.substr(0,80)+".....";
      				showAll = '<a href="javascript:void(0)" onclick="openAllComment(this)">展开全文</a>';
      			}
      			var commentUserName = "";
      			var online = "";
      			if(dataArr[i].commentUserType == 5){
      				if(dataArr[i].commentNikename){
      					commentUserName = dataArr[i].commentNikename;
      				}else{
      					commentUserName = dealPhoneName(dataArr[i].commentUserName2);
      				}
      			}else{
      				if(dataArr[i].commentUserName){
      					commentUserName = dataArr[i].commentUserName;
      				}else{
      					commentUserName = dealPhoneName(dataArr[i].commentPhone);
      				}
      				if(dataArr[i].commentUserType == 2){
      					online = "<div class='online-consult'>在线咨询</div>";
      				}
      			}
      			var commentUserAvatar = dataArr[i].commentUserAvatar;
      			if(!commentUserAvatar){
      				commentUserAvatar = _ctx+"/static/front/img/z44@3x.png";
      			}
      			var clicktype = dataArr[i].clicktype;
      			var clicktype1 ="";
      			var clicktype2 ="";
      			if(clicktype){
      				if(clicktype == 1) clicktype1 = "thum_Active";
      				if(clicktype == 2) clicktype2 = "thum_Active";
      			}
      			resHtml+=
      			'<div class="remark_content">\
	            	<img src="'+commentUserAvatar+'?x-oss-process=image/resize,m_fixed,h_60,w_60">\
	        		<div class="remark_definite">\
	        			<div>\
	        				<h4>'+commentUserName+'</h4>'+online+'\
	        			</div>\
	    	  			<p class="remark_p" onclick="toRemark(this,'+dataArr[i].id+')" all="'+dataArr[i].content+'">'+str+'</p>\
	    	  			<div>\
	            			<time>'+formatDateTime(dataArr[i].createTime)+'</time>\
	            			<div class="like">\
	            				<div class="dsj_comment" onclick="toRemark(this,'+dataArr[i].id+')">(<span class="dsj_num">'+dataArr[i].commentNum+'</span>)</div>\
	            				<div class="dsj_thumbup '+clicktype1+'" onclick="like(this,'+dataArr[i].id+',1);">(<span class="dsj_num _thumbup">'+dataArr[i].likeNum+'</span>)</div>\
	            				<div class="dsj_thumbdown '+clicktype2+'" onclick="like(this,'+dataArr[i].id+',2);">(<span class="dsj_num _thumbdown">'+dataArr[i].negativeNum+'</span>)</div>\
	            			</div>\
	            		</div> \
	    			</div>\
	    		</div>'
      		}
      		$("#user_remark").append(resHtml);
        }
      });
}

function formatDateTime(stimeStr) {    
    var stime = stimeStr;
	var diff = new Date().getTime() - stime;
	var def = "刚刚";
	if (!stime) return def;
	var days = Math.floor(diff / (24 * 3600 * 1000));
	if(days > 0) {
		var date =  new Date(stimeStr);
	    var y = 1900+date.getYear();
	    var m = "0"+(date.getMonth()+1);
	    var d = "0"+date.getDate();
	    return y+"年"+m.substring(m.length-2,m.length)+"月"+d.substring(d.length-2,d.length)+"日";
		//return days + "天前";
	}
	var diffH = diff % (24 * 3600 * 1000);
	var hours = Math.floor(diffH / (3600 * 1000));
	if(hours > 0) {
		return hours + "小时前";
	}
	var diffM = diffH % (3600 * 1000);
	var minutes = Math.floor(diffM / (60 * 1000));
	if(minutes > 0) {
		return minutes + "分钟前";
	} else {
		return def;
	}
};

function openAllComment(obj){
	var fun = $(obj).text();
	var allComment = $(obj).parent().attr("all");
	if(fun=="展开全文"){
		$(obj).parent().html(allComment+'<a href="javascript:void(0)" onclick="openAllComment(this)">收起</a>')
	}else{
		$(obj).parent().html(allComment.substr(0,150)+'......<a href="javascript:void(0)" onclick="openAllComment(this)">展开全文</a>');
	}
}

//顶或踩
function like(self,id,flag){
	if (user == ''){
		show_box(7);
	}else{
		$.ajax({
			type: "POST",
			async: false,
			url: _ctx+"/front/comment/like",
			data: {
				id : id ,
				flag : flag
			},
			dataType: "json",
			success: function(resultVo){
				if(resultVo.status!=200){
					alert(resultVo.data);
				}else{
					//改变点赞的数
					var num = $(self).find(".dsj_num").text();
					$(self).find(".dsj_num").text(parseInt(num)+1);
					$(self).addClass("thum_Active");
				}
			}
		});
	}
}

function toRemark(self,id){
	//location=_ctx+"/front/newHouse/house_comment?id="+$("#newHouseId").val()+"&type="+type;
	location=_ctx+"/front/comment/detail?id="+id+"&newHouseId="+$("#newHouseId").val();
}

function newsDetail(id,newHouseId){
	location=_ctx+"/front/newHouse/house_dynamic_detail?id="+id+"&newHouseId="+newHouseId;
}

function toPicture(id){
	location=_ctx+"/front/newHouse/house_picture?id="+$("#newHouseId").val()+"#photpType"+id;
}

function dealPhoneName(str){
	var reg = /^1[0-9]{10}$/;
	var flag = reg.test(str);
	if(flag){
		//return options.fn(this);//true
		return str.substring(0,3)+"*****"+str.substring(8,11);
	}else{
		//return options.inverse(this);//false
		return str;
	}
}
