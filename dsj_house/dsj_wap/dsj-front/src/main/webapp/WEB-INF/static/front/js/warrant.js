// 弹出框
    // (function(){
    //     var box_affirm   = ".box_affirm",    //1
    //         box_defeated = ".box_defeated",  //2
    //         box_succeed  = ".box_succeed";   //3
    //     $("body").on("click",function(state){
    //             $("#popup_box").show();
    //             if(state == 1){
    //                 $(box_affirm).show();
    //             } else if(state == 2){
    //                 $(box_defeated).show();
    //             } else{
    //                 $(box_succeed).show();
    //             }
    //     });
    //     $(".box_close").on("click",function(event){
    //          $("#popup_box").hide();
    //          event.stopPropagation(); 
    //     });
    //     $(".reset_botton").on("click",function(event){
    //          $("#popup_box").hide();
    //          event.stopPropagation(); 
    //     });
    //     $(".submit_botton").on("click",function(event){
    //          $("#popup_box").hide();
    //          event.stopPropagation();
    //         //  发送ajax请求
    //     });
    // })();
// lefttab切换
    var lefttabs = $(".centre_lefttab ul").find("li"),
        contentdivs = $(".centre_content").find(".centre_content_div");
    // 遍历赋值index
	for(var i=0;i<lefttabs.length;i++){
		lefttabs[i].index=i;
	}
    for(var i=0;i<contentdivs.length;i++){
		contentdivs[i].index=i;
	}
// lefttab事件委托
   $(".centre_lefttab ul").delegate("li","click",function(e){
       var  e = e || window.event,
            index = e.target.index;
       $(this).addClass("centre_leftActive").siblings().removeClass("centre_leftActive");
       $(contentdivs).eq(index).show().siblings().hide();
    }); 

    $(".content_title").delegate("li","click",function(){
       $(this).css({"borderBottomColor":" #2775e9","color":"#2775e9"}).siblings().css({"borderBottomColor":" transparent","color":"#333"});
    });

// 评价标签多选
    $(".select_kind").on("click",function(){
        var labels = $(".select_kind").find("label");
        labels.each(function(i){
            $(this).find("input").prop('checked') ? $(this).addClass('label_active') : $(this).removeClass('label_active');
        });
    });

    
;(function($){
    $.fn.extend({
        getStar: function(){
            //创建一个ul标签
            var ul = $("<ul class='evaluate_star'>"),
            //白星星
            writeStar = "☆",
            //红星星
            redStar = "★";
            //追加5个li
            for(var i = 0; i < 5; i++){
                ul.append($("<li>"));
            }
            //为li添加样式和内容
            ul.find("li").each(function(){
                $(this).css({
                    "color":"#cccccc",
                    "fontSize": "0",
                    "float": "left" 
                }).text(writeStar);
            });
            //把ul添加到指定的标签里并添加事件
            //用事件委托 提高性能
            $(this).append(ul).on("mouseover", "li", function(){
                $(this).addClass("active")
                .prevAll().addClass("active")
                .end() //回到$(this)链
                .nextAll().removeClass("active");
            }).on("click", "li", function(e){
                //阻止冒泡
                e.stopPropagation();
                //记录一下点击个那个li
                $(this).addClass("clicked").siblings().removeClass("clicked");

            }).on("mouseout", "li", function(){
                //所有的li都变成writeStar
                ul.children().removeClass("active");
                //对被点击的li进行处理
                $(".clicked").addClass("active").prevAll().addClass("active");
            });
        }
    });
})(jQuery);