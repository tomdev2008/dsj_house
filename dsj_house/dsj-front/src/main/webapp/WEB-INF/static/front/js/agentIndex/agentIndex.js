//关注该经纪人
function follow(userId,id){
	if(loginStatus==2){
		//show_box(1,"登录后才可以关注，是否登陆？")
		//$("#confimYes").on("click",function(){
			saveSessionLoginGo()
		//})
		
	}else{
		$.ajax({
	        type:"post",
	        async:true,
	        data:{
	          objectId:userId,
	          type:4
	        },
	        dataType:"json",
	        url:_ctx+"/front/agentIndex/addFollow",
	        success:function(result){
	          if(result.status==200){
	        	  showboxfresh = true;
	            show_box(3,result.message);
	            
	          }
	        }
	    })
	}
    
}
//取消关注
function cancleFollow(userId,id){
    $.ajax({
          type:"post",
          async:true,
          data:{
            objectId:userId,
            type:4
          },
          dataType:"json",
          url:_ctx+"/front/agentIndex/cancleFollow",
          success:function(result){
            if(result.status==200){
            	showboxfresh = true;
              show_box(3,result.message);
              
            }else{
            	showboxfresh = true;
              show_box(3,result.message);
            }
            
          }
    })
}

function saveSessionLoginGo(){
		 $.ajax({
	  		type:"post",
	  		url:_ctx+"/login/save_session",
	  		data:{
	  			url:window.location.href
	  		},
	  		dataType:"json",
	  		success: function(result){
	  			window.location.href=_ctx+"/login/tologin";
	  		}
	  	});
	 }
Handlebars.registerHelper({  
    'prettifyDate' : function(timestamp) {//格式化时间  
        var format = 'YYYY-MM-DD';  
        if(arguments.length > 2){  
            format = arguments[1];  
        }  
        if(timestamp){  
            return moment(new Date(timestamp)).format(format);  
        } else{  
            return '';  
        }  
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
        if (typeof(p2)=='undefined'||p2==null||p2=='') {
            return "<span class='priceUncertain'>售价待定</span>";
        }else{
        	return "<span class='price_1'>"+p1+"</span><span class='price_2'>"+p2+"</span><span class='price_3'>"+p3+"</span>"
        }
    },
    "status" : function(param){
        if (typeof(param)==1) {
            return "<span class='sale sale_wait'>待售</span>";
        }else if(param==2){
            return "<span class='sale sale_on'>在售</span>";
        }else{
            return "<span class='sale sale_finish'>售完</span>";
        }
    },
    "pictureHandler":function(p){
        if(p!=null&&p!=''){
            return p.split(',')[0]+"?x-oss-process=image/resize,m_lfit,h_230,w_164";
        }else{
            return _ctx+"/static/front/img/default/default_list.png"
        }
    },
    "oldHousePic":function(p){
    	if(p!=undefined){
    		return p+"?x-oss-process=image/resize,m_lfit,h_230,w_164";
    	}else{
    		return _ctx+"/static/front/img/default/default_list.png"
    	}
    },
    "ctx":function(){
    	return _ctx;
    }
    

}) 

var option = {
    noResult:''
}

var pageSize = 10;//每页条数
var currentPageId = '';//当前页模板 id
var currentType='';//当前页 3：租房 2:二手房 1:新房
var totalNum=0;//总数据条数
var currentPage=1;//当前页码
var pageNum = 1;//页数
var sort = 0;//默认排序方式


//table页模板切换
function replace(scriptId,type,page,flag){
	//flag tab 切换时才会传 
	if(flag==1){
		oldSort = "";
		 sort = 0;
	}
	 
    currentPageId = scriptId;
    currentType = type;
    if(scriptId=='index'){
        indexInit();
        source   = $("#"+scriptId+"_house").html();
        template = Handlebars.compile(source);
        html = template(option);
        $("#bigContainer").empty();
        $("#bigContainer").prepend(html);
    }else{
        source   = $("#loading").html();
        template = Handlebars.compile(source);
        html = template(option);
        $("#bigContainer").empty();
        $("#bigContainer").prepend(html);
        request(type,page,sort);//第二个参数是当前页,
    }
   
}
//模板页切换 后端请求数据
//type:房源类型 page 页码 sort 排序方式
function request(type,page,sort){
    var dataParam = {
        type:type,
        page:page,
        agentId:agentId,
        pageSize:pageSize
    }
    if(sort==0){//默认排序

    }else if(sort==1){//价格升序
        dataParam.price = 1
    }else if(sort==2){//时间降序
        dataParam.time = 1
    }
    $.ajax({
        type:"post",
        async:true,
        data:dataParam,
        dataType:"json",
        url:_ctx+"/front/agentIndex/getHouse",
        success:function(result){
            if(result.status==200){
            	
                option = result.data;
                //是否展示推荐
                if(result.data.flag==1){
                	if(currentType==1){
                		option.quantity = "大搜家为您推荐的<span class='bluefont'>"+result.data.count+"</span>个楼盘"
                	}else if(currentType==2){
                		option.quantity = "大搜家为您推荐的<span class='bluefont'>"+result.data.count+"</span>个房源"
                	}
                	
                	
                	
                }else{
                	option.quantity = "为您找到<span class='bluefont'>"+result.data.count+"</span>个北京楼盘"
                }
                
                
                //初始分页数据
                totalNum = result.data.count;
                pageNum = parseInt(totalNum/pageSize)
                if(totalNum%pageSize!=0){
                    pageNum = pageNum+1;
                }
                var pageList = [];
                for(var i =1;i<=pageNum;i++){
                    pageList.push(i);
                }
                if(totalNum==0){
                    option.noResult = "无记录";
                    option.height = "500px";
                }else{
                    option.noResult = "";
                    option.height = "";
                }
               // option.ctx = _ctx;
                option.pageNum = pageNum;
                option.pageList = pageList;
                option.currentPage = currentPage; 
                source   = $("#"+currentPageId+"_house").html();
                template = Handlebars.compile(source);
                html = template(option);
                $("#bigContainer").empty();
                $("#bigContainer").prepend(html);
                //排序类别添加样式
                if(type==1){
                	$("span[name='sort1']").eq(oldSort).children().removeClass("active");
                	$("span[name='sort1']").eq(oldSort).removeClass("color_blue");
                	$("span[name='sort1']").eq(sort).addClass("color_blue");
                	$("span[name='sort1']").eq(sort).children().addClass("active");
                }else if(type==2){
                	$("span[name='sort2']").eq(oldSort).children().removeClass("active");
                	$("span[name='sort2']").eq(oldSort).removeClass("color_blue");
                	$("span[name='sort2']").eq(sort).addClass("color_blue");
                	$("span[name='sort2']").eq(sort).children().addClass("active");
                }
               
            	
                //数据条数为0，去掉分页
                if(totalNum==0){
                    $(".dsj_page_container").remove();
                }


            }
          
        }
    })
}
var oldSort = "";
//更改排序方式
function changeSort(scriptId,type,sortNum){
	oldSort = sort;
	//var tempName='sort'+type;
	
	
    sort = sortNum;
    currentPage = 1;
    replace(scriptId,type,currentPage);
}
//换页
function changePage(param){
    if(param=='next'){
        if(currentPage<pageNum){
            replace(currentPageId,currentType,currentPage+1);
            currentPage = currentPage+1;
        }
    }else if(param=='previous'){
        if(currentPage>1){
            replace(currentPageId,currentType,currentPage-1);
            currentPage = currentPage-1;
        }
    }else{
        replace(currentPageId,currentType,param);
        currentPage = param;
    }
}