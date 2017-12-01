var status = 0,       //用于侧边栏的状态变量
pagenumber = 10,  //用于分页器的是否显示
lis = $(".nav_event>li"); //获取主体form所有的li

 //头部导航 
        $(".h_nav").delegate("li","click",function(){
            $(this).addClass("h_nav_active").siblings().removeClass("h_nav_active");
        });
        // nav整体切换
            //获取主体form所有的li遍历绑定事件
            var lis = $(".nav_event>li");
            lis.each(function(){
                $(this).on("click",function(event){
                    var  oli_classname = $(this).attr("class");// 获取当前对象的class名;
                    //事件当前对象的下拉框显示 
                    switch(oli_classname){
                        case "m_seek":
                                    $(this).children("ul").hide();
                                    $(this).parent().children("ul").hide();
                                    seekevent();
                                    break;
                        case "area_subway":
                                    $(this).siblings().children("ul").hide();
                                    area_subwayevent();
                                    break;
                        case "price":
                                    $(this).siblings().children("ul").hide();
                                    priceevent();
                                    break;
                        case "house_type":
                                    $(this).siblings().children("ul").hide();
                                    house_type_event();
                                    break;
                        case "area":
                                    $(this).siblings().children("ul").hide();
                                    area_event();
                                    break;
                        case "nav_more":
                                    $(this).siblings().children("ul").hide();
                                    nav_more_event();
                                    break;
                        case "empty":
                                    $(this).siblings().children("ul").hide();
                                    empty_event();
                                    break;
                        default :
                                $(this).children("ul").show();
                    }
                    event.stopPropagation();       
                });
            });
            $(".grabble_kuang").hide();
        // 输入框
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
        // 区域和地铁
        function area_subwayevent(){
               var area_subway_text= $(".area_subway>span").text(); 
                    $(".area_subway").attr({ "style": "background-image:url(img/map/m1.png)"});
                    $(".area_kuang").show();
                    $(".kuang_ul1").show();
                    $(".kuang_ul2").hide();
                    $(".kuang_ul3").hide();
                      event.stopPropagation();
               $(".kuang_ul1").delegate("li","mousemove",function(event){
                    $(this).attr({ "style": "background-color:#f6f6f6;"}).siblings().removeAttr("style");
                    event.stopPropagation();
               });
               $(".area_li").on("click",function(event){
                    $('.area_subway>span').html("北京");
                    $(".area_kuang").hide();
                    $(".area_subway").removeAttr("style");
                    event.stopPropagation();
                });
                $(".metro_li").on("click",function(event){
                    $(this).attr({ "style": "background-color:#f6f6f6;"}).siblings().removeAttr("style");
                     $('.area_kuang').attr({"style":"display:block;width:237px",});
                    $(".kuang_ul2").show();
                    $(".kuang_ul3").hide();
                      event.stopPropagation();
               }); 
                $(".kuang_ul2").delegate("li","click",function(event){
                    $(this).attr({ "style": "background-color:#fff;display:block;"}).siblings().removeAttr("style");
                    $('.area_kuang').attr({"style":"display:block;width:371px"});
                    $(".kuang_ul3").show();
                    event.stopPropagation();
               }); 
               $(".kuang_ul3").delegate("li","click",function(event){
                    kuang_ul3Value = $(this).text();
                 
                    $('.area_subway>span').text(function() {
                        return kuang_ul3Value;
                        });
                    $(".area_subway").removeAttr("style");
                    $(".metro_kuang").removeAttr("style");
                    $(".kuang_ul2 li").removeAttr("style");
                    $(".kuang_ul3 li").removeAttr("style");
                    event.stopPropagation();

                }) 
        };
        // 价格
        function  priceevent(){
            var price_text = $(".price span").text(),
                price_value = $(".price_seek input").val();
                $(".price").attr({ "style": "background-image:url(img/map/m1.png)"});
                $(".price").find("ul").show();
            //  将div中的内容赋值给input的value
            $(".price_kuang").delegate("li","click",function(event){
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
                    console.log("li");
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
        function house_type_event(){
            var house_type_text = $(".house_type span").text();
                $(".house_type").attr({ "style": "background-image:url(img/map/m1.png)"});
                $(".house_type").find("ul").show();
            //  将div中的内容赋值给li
            $(".house_type_kuang").delegate("li","click",function(event){
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
        function area_event(){
            var area_text = $(".area span").text(),
                area_value = $(".area_seek input").val();
                $(".area").attr({ "style": "background-image:url(img/map/m1.png)"});
                $(".area").find("ul").show();
            //  将div中的内容赋值给input的value
            $(".mj_kuang").delegate("li","click",function(event){
                var $li = $(this),
                    isWithTwo = $li.is(function() {
                    return  $(this).hasClass("m_seek");
                    }); 
                if ( isWithTwo ) {
                     $(".area_seek button").on("click",function(event){
                        area_value = $(".area_seek input").val();
                        $('.area span').html(function() {
                            return  area_value+"万";
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
        function nav_more_event(){
            var nav_more_text = $(".nav_more>span").text();
                $(".nav_more").attr({ "style": "background-image:url(img/map/m1.png)"});
                $(".nav_more").find(".down_kuang").show();
            //  将div中的内容赋值给li
            $(".down_kuang p").delegate("span,strong","click",function(event){
                    nav_more_text = $(this).html();
                    $('.nav_more>span').html(function() {
                        return nav_more_text;
                    });
                    $(".down_kuang").hide();
                    $(".nav_more").removeAttr("style");
                    event.stopPropagation();
                });
        };
        // 清空
        function empty_event(){
                $("#inputseek").val("");
                $(".area_subway span").html("区域/地铁");
                $(".price span").html("价格");
                $(".house_type span").html("户型");
                $(".area span").html("面积");
                $(".nav_more>span").html("更多");
        };
        // 点击下拉侧边栏
        $(".f_icon").on("click",function(event){
            if(status == 0){
                status = 1;
                $(".resourcenunber").show();
                $(".s_main").show();
                $(".m_sideba").attr("style","height:100%");
            }else{
                status = 0;
                $(".resourcenunber").hide();
                $(".s_main").hide();
                $(".m_sideba").removeAttr("style");
            }
            event.stopPropagation();
        });
