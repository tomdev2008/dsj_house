var status = 0,       //用于侧边栏的状态变量
    lis = $(".nav_event>li"), //获取主体form所有的li
    m_sideba_width = $(".m_sideba").width();
    map_kuang_width = document.documentElement.clientWidth,
    map_width = map_kuang_width -  m_sideba_width;

    $(".map_kuang").attr("style","width:"+map_width+"px"); 
// 点击下拉侧边栏
$(".f_icon").on("click",function(event){
    if(status == 0){
        status = 1;
        $(".list_room").hide();
        $(".m_sideba").attr("style","width:0"); 
        m_sideba_width = $(".m_sideba").width();
        map_width = map_kuang_width -  m_sideba_width;
        $(".map_kuang").attr("style","width:"+map_width+"px"); 
        
    }else{
        status = 0;
        $(".list_room").show();
        $(".m_sideba").removeAttr("style");
        m_sideba_width = $(".m_sideba").width();
        map_width = map_kuang_width -  m_sideba_width;
        $(".map_kuang").attr("style","width:"+map_width+"px"); 
    }
    event.stopPropagation();
});

// 输入框
$(".m_seek").on("click",function(event){
    seekevent();
    event.stopPropagation();
})
// 输入框调用方法
function seekevent(){
    var seekValue = $("#inputseek").val();
    // click事件
    $("#inputseek").on("click",function(){
        $(this).removeAttr("placeholder");
        seekValue =  $.trim($("#inputseek").val());
        if(seekValue != ""){
             $(".grabble_kuang").show();
        }
    })
    // keyup事件
    $("#inputseek").on("keyup",function(){
        seekValue =  $.trim($("#inputseek").val());
        // 发送ajax请求
        if(seekValue != ""){
             $(".grabble_kuang").show();
        }
    })
    // 将div中的内容赋值给input的value
    $(".grabble_kuang").delegate("li","click",function(){
        seekValue = $(this).html();
        $('#inputseek').val(function() {
            return seekValue;
            });
        $(".grabble_kuang").hide();
    })
};



// 区域与地铁
$(".area_subway").on("mouseenter",function(event){
    area_subwayevent();
    event.stopPropagation();
});
$(".area_subway").on("mouseleave ",function(event){
    $(".area_kuang").hide();
    event.stopPropagation();
});
// 区域和地铁
function area_subwayevent(){
       var area_subway_text= $(".area_subway>span").text();
            $(".area_kuang").show();
            $('.area_kuang').attr({"style":"display:block;width:101px",});
            $(".kuang_ul1").show();
            $(".kuang_ul2").hide();
            $(".kuang_ul3").hide();
            event.stopPropagation();
        //鼠标移入到1号ul时给的样式问题 
       $(".kuang_ul1 li").on("mousemove",function(event){
            $(this).attr({ "style": "background-color:#f6f6f6;"}).siblings().removeAttr("style");
            event.stopPropagation();
       });
       $(".kuang_ul1 li").on("click",function(event){
            var  ul1_className = event.target.className;
            $('.area_kuang').attr({"style":"display:block;width:237px",});
            if(ul1_className == "areas"){
                $(".areas_ul2").show();
            }
            event.stopPropagation();
        });
     event.stopPropagation();
 
};

function areaClick(lng,lat,obj){
	var area_value = $(obj).text();
    $('.area_subway span').html(function() {
        return  area_value;
    });
    $(".area_kuang").hide();
	var u = map.getZoom();
	var point = new BMap.Point(lat,lng);
	map.centerAndZoom(point, u);
// 价格
$(".price").on("mouseover",function(event){
    priceevent();
    event.stopPropagation();
});
$(".price").on(" mouseout",function(event){
    $(".price_kuang").hide();
    event.stopPropagation();
});
// 价格
function  priceevent(){
    var price_text = $(".price span").text(),
        price_value = $(".price_seek input").val();
        $(".price").find("ul").show();
    //  将div中的内容赋值给input的value
    $(".price_kuang>li").on("click",function(event){
            $(this).addClass("bule_1").siblings().removeClass("bule_1");
            var $li = $(this),
            isWithTwo = $li.is(function() {
            return  $(this).hasClass("m_seek");
            });
        if ( isWithTwo ) {
             $(".price_seek button").on("click",function(event){
                price_value = $(".price_seek input").val();
                $('.price span').html(function() {
                    return  price_value+"万";
                });
                $(".price_kuang").hide();
                $(".price").removeAttr("style");
                event.stopPropagation();
             });
        } else {
            pm_kuangtext = $(this).text();
            $('.price span').html(function() {
                return pm_kuangtext;
            });
            $(".price_kuang").hide();
            $(".price").removeAttr("style");
            event.stopPropagation();
        }
        
    })
};

//户型
$(".house_type").on("mouseover",function(event){
    house_type_event();
    event.stopPropagation();
});
$(".house_type").on(" mouseout",function(event){
    $(".house_type_kuang").hide();
    event.stopPropagation();
});
//户型
function house_type_event(){
    var house_type_text = $(".house_type span").text();
        $(".house_type").find("ul").show();
    //  将div中的内容赋值给li
    $(".house_type_kuang").delegate("li","click",function(event){
            $(this).addClass("bule_1").siblings().removeClass("bule_1");
            house_type_text = $(this).text();
            $('.house_type span').text(function() {
                return house_type_text;
            });
            $(".house_type_kuang").hide();
            $(".house_type").removeAttr("style");
            event.stopPropagation();
        });
};


//面积
$(".area").on("mouseover",function(event){
    area_event();
    event.stopPropagation();
});
$(".area").on(" mouseout",function(event){
    $(".mj_kuang").hide();
    event.stopPropagation();
});
//面积
function area_event(){
    var area_text = $(".area span").text(),
        area_value = $(".area_seek input").val();
        $(".area").find("ul").show();
    //  将div中的内容赋值给input的value
    $(".mj_kuang>li").on("click",function(event){
        $(this).addClass("bule_1").siblings().removeClass("bule_1");
        var $li = $(this),
            isWithTwo = $li.is(function() {
            return  $(this).hasClass("m_seek");
            }); 
        if ( isWithTwo ) {
             $(".area_seek .f_button").on("click",function(event){
                area_value = $(".area_seek input").val();
                $('.area span').html(function() {
                    return  area_value+"m";
                });
                $(".mj_kuang").hide();
                $(".area").removeAttr("style");
                event.stopPropagation();
             });

        } else {
            mj_kuangtext = $(this).text();
            $('.area span').html(function() {
                return mj_kuangtext;
            });
            $(".mj_kuang").hide();
            $(".area").removeAttr("style");
            event.stopPropagation();
        }
    })
};

// 更多
$(".nav_more").on("mouseover",function(event){
    nav_more_event();
    event.stopPropagation();
});
$(".nav_more").on(" mouseout",function(event){
    $(".down_kuang").hide();
    event.stopPropagation();
});
// 更多
function nav_more_event(){
    var nav_more_text = $(".nav_more>span").text();
        $(".nav_more").find(".down_kuang").show();
    //  将div中的内容赋值给li
    $(".down_kuang p").delegate("span,strong","click",function(event){
             $(this).addClass("bule_1").siblings().removeClass("bule_1");
            nav_more_text = $(this).html();
            $('.nav_more>span').html(function() {
                return nav_more_text;
            });
            $(".down_kuang").hide();
            $(".nav_more").removeAttr("style");
            event.stopPropagation();
        });
};

$(".empty").on("click",function(event){
    empty_event();
    event.stopPropagation();
})
// 清空
function empty_event(){
        $("#inputseek").val("");
        $(".area_subway span").html("区域");
        $(".price span").html("价格");
        $(".price_kuang").children("li").eq(0).addClass("bule_1").siblings().removeClass("bule_1");
        $(".house_type span").html("户型");
        $(".house_type_kuang").children("li").eq(0).addClass("bule_1").siblings().removeClass("bule_1");
        $(".area span").html("面积");
         $(".mj_kuang").children("li").eq(0).addClass("bule_1").siblings().removeClass("bule_1");
        $(".nav_more>span").html("更多");
        $(".fangshi").children("span").eq(0).addClass("bule_1").siblings().removeClass("bule_1");
        $(".pingbai").children("span").eq(0).addClass("bule_1").siblings().removeClass("bule_1");
        $(".fukuang").children("span").eq(0).addClass("bule_1").siblings().removeClass("bule_1");
};
// ;(function($){
//         $.fn.extend({
//             dropDownBox : function (){
//                 $(this).on({//绑定多个事件
//                     mouseover : function () {
//                        console.log(1);
//                     },
//                     mouseout : function () {
//                         console.log(2);
//                     }
//                 })
//             }    
//         });
// })(jQuery);
// // 区域/地铁
// $(".area_aubway").dropDownBox();