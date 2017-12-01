Handlebars.registerHelper({  
    'prettifyDate' : function(timestamp) {//格式化时间  
        var format = 'YYYY-MM-DD';  
        if(arguments.length > 2){  
            format = arguments[1];  
        } 
        
        var result = '';
        var minute = 1000 * 60;
        var hour = minute * 60;
        var day = hour * 24;
        var halfamonth = day * 15;
        var month = day * 30;
        var now = new Date().getTime();
        var diffValue = now - Date.parse(timestamp);
        if(diffValue < 0){return;}
        var monthC =diffValue/month;
        var weekC =diffValue/(7*day);
        var dayC =diffValue/day;
        var hourC =diffValue/hour;
        var minC =diffValue/minute;
        
        
        if(monthC>=1){
            //result="" + parseInt(monthC) + "月前";
        	result =  moment(new Date(timestamp)).format(format);  
        }else if(weekC>=1){
            //result="" + parseInt(weekC) + "周前";
        	result =  moment(new Date(timestamp)).format(format);  
        }else if(dayC>=1){
            //result=""+ parseInt(dayC) +"天前";
        	result =  moment(new Date(timestamp)).format(format);  
        }else if(hourC>=1){
            result=""+ parseInt(hourC) +"小时前";
        }else if(minC>=1){
            result=""+ parseInt(minC) +"分钟前";
        }else{
            result="刚刚";
        }   
        
        return result;
    },
    "addOne" : function(index){
        //返回+1之后的结果
        return index+1;
    },
    "active" : function(p1){
        if(p1==currentPage){
            return "active";
        }else{
            return '';
        }
    },
    "mobile" : function(param){
        if(typeof(param)==='undefined'){
            return "";
        }else{
            return '<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>400-032-4608 转 '+param;
        }
    },
    "color" : function(param){
        var colorArray = ['color_0','color_1','color_2','color_3'];
        var res = param%4;
        if(res==1){
            return colorArray[0];
        }else if(res==2){
            return colorArray[1];
        }else if(res==3){
            return colorArray[2];
        }else if(res==0){
            return colorArray[3];
        }
    },
    "price" : function(p1,p2,p3){
        if (typeof(p2)=='undefined'||p2==null||p2=="") {
            return "<span>售价待定</span>";
        }else{
            return p1+"<span>"+p2+"</span>"+p3;
        }
    },
    "status" : function(param){
        if (typeof(param)==1) {
            return "待售";
        }else if(param==2){
            return "在售";
        }else{
            return "售完"; 
        }
    },
    "commentPic":function(p){
    	if(p!=null&&p!=""){
    		return "<div class='col-xs-2'><div class='dsj_img_container'><img class='img-responsive' src='"+p+"'></div></div>";
    	}else{
    		return "";
    	}
    },
//    "userTypeHandle": function(p){
//        //经纪人 不显示修改昵称
//    	var str = "<li class='update-nice' onclick='replace(\"infomation-nikename\")'>修改昵称</li>";
//        if(p==5){
//            return str;
//        }else{
//            return "";
//        }
//    },
    "pictureHandler":function(p){
        if(p!=null&&p!=''){
            return p+"?x-oss-process=image/resize,m_lfit,h_250,w_170"
        }else{
        	return _ctx+"/static/front/img/default/default_list.png"
        }
    },
    "timeHandler":function(p){
    	return p.split(" ")[0];
    }
}) 
//每页条数
var pageSize = 30;
//当前页模板 id
var currentPageId = '';
//当前页 类型 4:经纪人 3：租房 2:二手房 1:新房
var currentType='';
//请求的 path   follow   lookHistory
var currentUrl ='';

//总数据条数
var totalNum=0;

//当前页码
var currentPage=1;
//页数
var pageNum = 1;
var option = {};
//个人中心首页


//如果是从侧边栏进来的

if(typeOfPage!=undefined && typeOfPage !='' &&typeOfPage!=null){
    if(typeOfPage=='follow'){       
        $("li[id='follow']").click();
    }else if(typeOfPage=="lookHistory"){
        $("li[id='lookHistory']").click();
    }else if(typeOfPage=="entrust"){
        $("li[id='entrust']").click();
    }
}else{
    var source   = $("#myOrder-head").html();
    var template = Handlebars.compile(source);
    var html = template(option);
    $("#head_content").empty();
    $("#head_content").prepend(html);
        //加载提示模板
    var source1   = $("#loading").html();
    var template1 = Handlebars.compile(source1);
    var html1 = template1(option);
    $("#centre_content").empty();
    $("#centre_content").prepend(html1);
    
    $("#order").click();
    /*
    if("yes" == $("#payId").val()){
    	myReplace('myOrder-head','order',4,1)
    }else{
    	$("#order").click();
    }*/
}


$(".content_title").delegate("li","click",function(){
   $(this).addClass("content_title_active").siblings().removeClass("content_title_active");
});



// request({
//             type:1,
//             page:1
//             },"entrust","myEntrust-sell"
//             );   


function replace(scriptId){
    source   = $("#"+scriptId).html();
    template = Handlebars.compile(source); 
   // option.userType=userType;//agent 2 or member 5
    html = template(option);
    $("#head_content").empty();
    $("#head_content").prepend(html);
    if(scriptId=="infomation-avatar"){
        touxiang();
    }
    
    
}
function myReplace(scriptId,url,type,page,flag){
	currentPage = page;
    currentPageId = scriptId;
    currentType = type;
    currentUrl = url;
    if(flag==1){
        source   = $("#head-"+scriptId).html();
        template = Handlebars.compile(source);
        html = template(option);
        $("#head_content").empty();
        $("#head_content").prepend(html);

        $(".content_title").delegate("li","click",function(){
           $(this).addClass("content_title_active").siblings().removeClass("content_title_active");
        });
        source1   = $("#loading").html();
        template1 = Handlebars.compile(source1);
        html1 = template1(option);
        $("#centre_content").empty();
        $("#centre_content").prepend(html1);

    }else{
        if(scriptId=='myComment'){
            source   = $("#loading").html();
            template = Handlebars.compile(source);
            html = template(option);
            $("#head_content").empty();
            $("#head_content").prepend(html);
            
        }else{
            source1   = $("#loading").html();
            template1 = Handlebars.compile(source1);
            html1 = template1(option);
            $("#centre_content").empty();
            $("#centre_content").prepend(html1);
        }
        
    }

        request({
            type:type,
            page:page,
            pageSize:pageSize
            },url,scriptId
            );   
}
function request(dataParams,url,scriptId){
    $.ajax({
        type:"post",
        url:url,
        data:dataParams,
        datatype:"json",
        success:function(result){
            if(result.status==200){
                option = result.data;
                totalNum = result.data.count;
                pageNum = parseInt(totalNum/pageSize);
                if(totalNum%pageSize!=0){
                    pageNum = pageNum+1;
                }
                var pageList = [];
                for(var i =1;i<=pageNum;i++){
                    pageList.push(i);
                }
                if(totalNum==0){
                    if(currentUrl=='follow'){
                        option.noResult = "无记录";
                        option.className ="notGuangzhu"
                    }else if(currentUrl=='entrust'){
                        option.noResult = "无记录";
                        option.className ="empty_data"
                    }else if(currentUrl=='lookHistory'){
                        option.noResult = "无记录";
                        option.className ="empty_data"
                    }else if(currentUrl=="comment"){
                        option.noResult = "您还没有发布过点评";
                        option.className ="cannot_find"
                    }
                    
                }else{
                    option.noResult = "";
                    option.className =""
                }
                
                option.pageNum = pageNum;
                option.pageList = pageList;
                option.firstPage = 1;
                option.currentPage = currentPage;
                if(scriptId=="myComment"){
                    source   = $("#"+scriptId).html();
                    template = Handlebars.compile(source);
                    html = template(option);
                    $("#head_content").empty();
                    $("#head_content").prepend(html);
                    if(totalNum!=0){
                        //$(".row.dsj_page_block").remove();
                        source   = $("#page").html();
                        template = Handlebars.compile(source);
                        html = template(option);
                        $("#pageContainer").empty();
                        $("#pageContainer").prepend(html);
                    }
                }else if (scriptId=="myOrder-head"){
                    source1  = $("#"+scriptId).html();
                    template1 = Handlebars.compile(source1);            
                    html1 = template1(option);
                    $("#centre_content").empty();
                    $("#centre_content").prepend(html1);
                    $("#allOrderNum").text(option.allOrderNum);
                    $("#waitOrderNum").text(option.waitOrderNum);
                    $("#finishOrderNum").text(option.finishOrderNum);
                    $("#waitViewNum").text(option.waitViewNum);
                    $("#finishViewNum").text(option.finishViewNum);
                    $("#cancelNum").text(option.cancelNum);
                    $("#refundNum").text(option.refundNum);
                    if(totalNum!=0){
                        source   = $("#page").html();
                        template = Handlebars.compile(source);
                        html = template(option);
                        $("#pageContainer").empty();
                        $("#pageContainer").prepend(html);
                    }
                }else{
                    source1  = $("#"+scriptId).html();
                    template1 = Handlebars.compile(source1);            
                    html1 = template1(option);
                    $("#centre_content").empty();
                    $("#centre_content").prepend(html1);
                    if(totalNum!=0){
                        //$(".row.dsj_page_block").remove();
                        source   = $("#page").html();
                        template = Handlebars.compile(source);
                        html = template(option);
                        $("#pageContainer").empty();
                        $("#pageContainer").prepend(html);
                    }
                }
            }else{

            }
            
            
        }
    })
}

function changePage(param){
    if(param=='next'){
        if(currentPage<pageNum){
            myReplace(currentPageId,currentUrl,currentType,currentPage+1);
            currentPage = currentPage+1;
        }
    }else if(param=='previous'){
        if(currentPage>1){
            myReplace(currentPageId,currentUrl,currentType,currentPage-1);
            currentPage = currentPage-1;
        }
    }else{
        myReplace(currentPageId,currentUrl,currentType,param);
        currentPage = param;
    }
}
function cancleFollow(id,scriptId,type){
    $.ajax({
        type:"post",
        url:_ctx+"/front/personCenter/cancleFollow",
        data:{
            id:id
        },
        datatype:"json",
        success:function(result){
            if(result.status==200){
                myReplace(scriptId,'follow',type,1);
            }
        }
    })
}
function touxiang(){
    $("#picker").one("click",function(argument) {
            $(".crop_container").show();
        })
    var $wrap = $('#wrap');
    var uploader = WebUploader.create({

        // swf文件路径
        // swf: '/js/Uploader.swf',

        // 文件接收服务端。
        // server: 'http://webuploader.duapp.com/server/fileupload.php',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    var small;
    uploader.on( 'fileQueued', function( file ) {
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                alert("抱歉无法预览！")
                return;
            }
            if (small != undefined) {
                small.changeSrc(src);
            }else {
                small = resizeableImage('.resize-image-small')();
                small.changeSrc(src);
                $('.resize-image-small').show();
            }

        }, 180, 180 );
    });

    $('.save_botton').on('click', function(argument) {
      $.ajax({
          url: _ctx+"/front/personCenter/avatar",
          method: 'POST',
          data: {"base64":small.crop()},
          //contentType: false, // 注意这里应设为false
          //processData: false,
          cache: false,
          success: function(data) {
              if(data.status==200){
                 show_box(3,'头像保存成功');
                 $("#avatar").attr('src',data.data); 
                }
          },
          error: function (jqXHR) {
              console.log(JSON.stringify(jqXHR));
          }
      })
    });
}

function saveNikename(){
    var length = $('#newInputNikename').val().length;
    
    if(length<2||length>10){
        $('#nikenameMsg').text("昵称的长度应该为2-10个字符");
        setTimeout(function(){
            $('#nikenameMsg').text("");
        },2000);
        return;
    }else{
        $.ajax({
            url: _ctx+"/front/personCenter/nikename",
            method: 'POST',
            data: {nikename:$('#newInputNikename').val()},
            datatype:"json",
            success: function(data) {
                if(data.status==200){
                    show_box(3,"昵称修改成功");
                    $("#name").html($('#newInputNikename').val()); 
                    replace("infomation-nikename");
                }
            }
        })
    }
} 
function savePwd(){
    if($('#oldInputpwd').val()==''){
        $('#pwdMsg1').text("请输入密码");
        setTimeout(function(){
            $('#pwdMsg1').text("");
        },2000);
        return;
    }
    var patrn=/^(\w){6,20}$/;  
    if (!patrn.exec($('#newInputpwd').val())){
        $('#pwdMsg').text("密码长度应该为6-20位");
        setTimeout(function(){
            $('#pwdMsg').text("");
        },2000);
        return;
    }
    if($('#newInputpwd').val()!=$('#affirmnewInputpwd').val()){
        $('#pwdMsg').text("两次输入的密码不一致");
        setTimeout(function(){
            $('#pwdMsg').text("");
        },2000);
        return;
    }
    $.ajax({
        url: _ctx+"/front/personCenter/password",
        method: 'POST',
        data: {oldpwd:$('#oldInputpwd').val(),newpwd:$('#newInputpwd').val()},
        datatype:"json",
        success: function(data) {
            if(data.status!=200){
                show_box(2,data.message);                   
            }else{
                show_box(3,"密码修改成功");
                replace("infomation-password");
            }
        }
    })

}
function validateYourPhone(){
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test($("#exampleInputphone").val())) 
    { 
        $('#phone').text("请输入正确的手机号");
        setTimeout(function(){
            $('#phone').text("");
        },2000);
        return;
    }
}
function getVcode(obj){
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
    if(!myreg.test($("#exampleInputphone").val())) 
    { 
        $('#phone').text("请输入正确的手机号");
        setTimeout(function(){
            $('#phone').text("");
        },2000);
        return;
    }else{
        settime(obj);
        $.ajax({
            type:"post",
            async:true,
            data:{phone:$("#exampleInputphone").val(),flag:1},
            dataType:"json",
            url:_ctx+"/front/entrust/vcode",
            success:function(result){
                if (result.status==200) {
                    show_box(3,"验证码发送成功");
                }else{
                	countdown=0;
                    show_box(2,result.message);
                }
            }
        })
    }


}
var countdown=60; 
function settime(obj) { 
    if (countdown <= 0) { 
        obj.removeAttribute("disabled");    
        obj.value="获取验证码"; 
        countdown = 60; 
    } else { 
        obj.setAttribute("disabled", true); 
        obj.value="重新发送(" + countdown + ")"; 
        countdown--; 
        setTimeout(function() { 
            settime(obj);
            },1000)
    } 
} 

function savePhone(){
    if($("#exampleInputVcode").val()==""||$("#exampleInputVcode").val().length!=6){
         $('#vcodeMsg').text("请输入正确验证码");
        setTimeout(function(){
            $('#vcodeMsg').text("");
        },2000);
        return;
    }
    $.ajax({
        type:"post",
        async:true,
        data:{
            phone:$("#exampleInputphone").val(),
            vcode:$("#exampleInputVcode").val()
        },
        dataType:"json",
        url:_ctx+"/front/personCenter/phone",
        success:function(result){
            if(result.status==200){
                show_box(3,"手机修改成功");
                replace("infomation-phone");
            }else{
                show_box(2,"手机修改失败");
            }
            
        }
    })
}