<#include "common/taglibs.ftl">
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>大搜家-新房</title>
        <link rel="stylesheet" href="${ctx }/static/front/css/dsj_housemap.css">
    </head>
    <body>
        <div class="map">
            <!--头部  -->
            <div class="m_header_kuang">
                <div class="m_header">
                
                    <h1 class="m_logo">
	         			<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
                    </h1>
                    <div class="m_address">
                     	   北京
                    </div>
                   
                    <ul class="h_nav">
                        <li class="h_nav_active"><a href="${ctx}/map/newMap">新房</a></li>
                        <li><a href="${ctx}/map/oldMap">二手房</a></li>
                    </ul>
                    <ul class="m_login">
                       
             <#if Session.pc_user_session??>
  				<#if pc_user_session.userType==5>
  					<span><a href="${ctx}/front/personCenter/center" >${pc_user_session.username}</a></span>
  				<#else>
  					<span><a>${pc_user_session.username}</a></span>
  				</#if>
     	  		<input type="hidden" id="usernameIM" value="${pc_user_session.username}" />
				<input type="hidden" id="passwordIM" value="${pc_user_session.imPassword}" />
         	  	<input type="hidden" id="userIdIM" value="${pc_user_session.id}" />
	  			<span><a href="javascript:void(0);" onclick="loginOut()">退出</a></span>
	  		<#else>
           <li><a href="${ctx}/login/tologin">登录</a></li>
           <li><a href="${ctx}/login/register">注册</a></li>
           <input type="hidden" id="usernameIM" value="" />
		   <input type="hidden" id="passwordIM" value="" />
		   <input type="hidden" id="userIdIM" value="" />
          </#if>
                       
                    </ul>
                </div>   
            </div>
            <!--主体  -->
            <div class="m_main">
            	<div id="allmap" class="map_kuang">
            	</div>
<!--                 主体form  -->
                <div class="m_nav_kuang">
                    <div class="m_nav">
                        <ul class="nav_event">
<!--                             搜索  -->
                            <li class="m_seek">
                                <form>
                                    <input type="text" id="dicNameId" autocomplete="off" placeholder="请输入楼盘名称或地址">
                                    <p onclick="findDicName()" class="f_button"></p>
                                </form>
                                <ul class="grabble_kuang">
                                    
                                </ul>
                            </li>
                            <!-- 区域/地铁  -->
                            <li class="area_subway"><span>区域</span>
                                <ul class="area_kuang metro_kuang">
                                    <li>
                                        <ul class="kuang_ul1">
                                            <li class="areas" >区域</li>
                                             <!--
                                            <li  class="metro_li" onclick="sub();">地铁</li> 
                                            -->
                                        </ul> 
                                    </li>
                                    <li>
                                     <ul class="kuang_ul2 areas_ul2" id="areaId">
                                          
                                        </ul> 
                                    </li>
                                     <li>
                                        <ul class="kuang_ul2">
                                            <#list subList as subList>
                                            <li onclick="subLine('${subList.id}');">${subList.name}</li>
                                            </#list>
                                        </ul> 
                                    </li>
                                    <li>
                                        <ul class="kuang_ul3" id="subId">
                                           
                                        </ul> 
                                    </li>  
                                </ul> 
                            </li>
<!--                             价格  -->
                            <li class="price"><span>价格</span>
                            <ul class="kuang price_kuang">
                                    <li onclick="findMap(this,1)" class="bule_1">不限</li>
                                    <#list priceMap?keys as key>
                                    <li value="${key}" onclick="findMap(this,1)">${priceMap[key]}</li>
                                    </#list>
                                     <li class="m_seek price_seek">
                                        <form>
                                        <div>
                                            <input type="text" id="inputseek" placeholder="0~1500万">
                                        </div>
                                        <button type="button" class="f_button" onclick="findMap(this,11)"></button>
                                        </form>
                                    </li>
                                </ul> 
                            </li>
<!--                             户型  -->
                            <li class="house_type"><span>户型</span>
                             <ul class="house_type_kuang" style="display: none">
                                    <li onclick="findMap(this,2)" class="bule_1">不限</li>
                                     <#list roomMap?keys as key>
                                     <li value="${key}" onclick="findMap(this,2)">${roomMap[key]}</li>
                                    </#list>
                                </ul> 
                            </li>
<!--                             更多  -->
                            <li class="nav_more"><span>更多</span>
                             <ul class="down_kuang">
                                    <li>
                                    <span>特点：</span>
                                    <p class="fangshi">
                                    <span onclick="findMap(this,4)" class="bule_1">不限</span>
                                     <#list tsMap?keys as key>
                                    <span value="${key}" onclick="findMap(this,4)">${tsMap[key]}</span>
                                    </#list>
                                    </p>
                                    </li>
                                    
                                     <li>
                                    <span>类型：</span>
                                    <p class="fangshi">
                                    <span onclick="findMap(this,5)" class="bule_1">不限</span>
                                     <#list wyMap?keys as key>
                                    <span value="${key}" onclick="findMap(this,5)">${wyMap[key]}</span>
                                    </#list>
                                    </p>
                                    </li>
                              </ul>      
                            </li>
<!--                             清空  -->
                            <li class="empty"><span onclick="clean()">清空</span></li>
                        </ul>
                    </div>
                </div>
<!--                 主体侧栏  -->
                <div class="m_sideba" >
                    <div class="m_sideba_kuang">
                        <div class="s_header" id="titleId">
                            <h3 class="h_title">北京市
                                <div class="list_room"><a href="${ctx}/front/newHouse/list" target="_blank">列表找房</a></div>
                            </h3>
                            <!-- 判断数据为0的时候添加  style="visibility: hidden"-->
                            <ul>
                                <li id="region"></li>
                                <li id="area"></li>
                            </ul>
                        </div>
                        <p class="resourcenunber">可视范围内有<span id="countId"></span>个房源</p>
                        <div class="s_main">
                        <!-- 判断数据为0的时候添加  style="visibility: hidden"-->
                            <ul class="s_sort" style="visibility: inherit" id="inheritId">
                                <li class="s_up" onclick="findMap(this,10)" id="sortId">默认排序</li>
                                <li class="s_up" onclick="findMap(this,7)" id="supId">价格</li>
                                <li class="s_down" onclick="findMap(this,9)" id="supIds">时间</li>
                            </ul>
                            <div class="s_list_kuang">
                             <!-- 判断数据为0的时候添加  class="is_list"-->
                                <div class="s_list is_list" id="ulId">
                                    
                                </div> 
                                 <!-- 判断数据为0的时候添加  show()-->
                                    <div class="not_house" style="display:none" id="notHouseId">未找到符合条件的房源</div>
                            </div>  
                        </div>
                         <div class="f_icon"></div> 
                    </div>
                </div> 
            </div>	
        </div>
<!--         jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="${ctx }/static/front/js/jquery.js"></script>
        <script src="${ctx }/static/front/js/handlebars.js"></script>
        <script src="${ctx }/static/front/js/housemap.js"></script>
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/library/CityList/1.4/src/CityList_min.js"></script>
    </body>
     <script type="text/javascript">
      function res(){
      history.go(0) 
     }
    //页数
    var pageNum = 1;
    //区域集合
     var xy=[];
     //小区集合
     var xy1=[];
     //1:区域，2：商圈 3:小区 （类型）
     var types=1;
     //可视化范围内的id字符串
     var array="";
     //鼠标收缩等级
     var u=null;
     //鼠标收缩默认状态
     var isWheelZoom=true;
     //覆盖物经纬度
	 var pt = null;
	 //覆盖物名字
	 var name=null;
	 //覆盖物数量
	 var count=null;
	 //覆盖物id
	 var id=null;
	 //区域状态1，地铁状态2
	 var statusType=1;
	 //户型
	 var room=null;
	 //价格
     var price=null;
     //楼盘名称
     var keywords="";
     //价格最小
     var pmn=null;
     //价格最大
     var pmx=null;
     //排序
     var ordering=1;
     //特点
     var dictrait="";
     //物业类型
     var wyType=null;
     //地铁线
     var subwayline="";
     //地铁站
     var subway="";
     var subwaylineId="";
     var ids="";
     var xiaoquList=[];
     var arrayList=[];
     var are=[];
     //点击maker,将maker赋值到mak
     var mak=null;
     var map = new BMap.Map("allmap");    //创建地图容器
     //初始化
      $(function(){
         init();
      })
      
        function init(){
             //创建地图，加载插件
	         var point = new BMap.Point(116.404, 39.915);        //创建一个点
	         map.centerAndZoom(point, 12);                       //设立中心点和地图级别，就是初始化地图
	         map.enableScrollWheelZoom(true); 
	         map.setMinZoom(11);
	         var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
	         var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT , type: BMAP_NAVIGATION_CONTROL_SMALL}); 
	         map.addControl(top_left_control);        
	         map.addControl(top_right_navigation); 
	       //取数据
	         destory();
	         if(statusType==2){
	    		isWheelZoom=false;
	    		subwayStation();
	    		$("#sortId").attr('class','s_up_active');
			 }else{
			    destory();
				baidu(xy);
				$("#sortId").attr('class','s_up_active');
			    huixian(types);
			}
      }
     
     //获取区域，商圈，小区集合
     function destory(){
        $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseRegion",
	            async:false,
	            data:{
	                   room:room,
	                   price:price,
	                   pmn:pmn,
	                   pmx:pmx,
	                   wyType:wyType,
	                   keywords:keywords,
	                   ordering:ordering,
	                   dictrait:dictrait,
	                   subwayline:subwayline,
	                   subway:subway,
	                   isSaleOut:2
	                   },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                xy=data.data.AreaLatLngGroupList;
	                xy1=data.data.quartersList;
	              }
	            }
	      });
     }
     
     //点击区域，获取这个区域的坐标和区域下的楼盘坐标
     function destoryArray(){
        $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseRegion",
	            async:false,
	            data:{areaCode3s:ids,
	                   room:room,
	                   price:price,
	                   pmn:pmn,
	                   pmx:pmx,
	                   wyType:wyType,
	                   keywords:keywords,
	                   ordering:ordering,
	                   subwayline:subwayline,
	                   subway:subway,
	                   isSaleOut:2
	                   },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                arrayList=data.data.AreaLatLngGroupList;
	                xiaoquList=data.data.quartersList;
	              }
	            }
	      });
     }
     //点击菜单进行交互
     function findMap(obj,num){
      if(num==1){
         price=$(obj).val();
         if(price==0){
           price=null;
         }else{
           pmn=null;
           pmx=null;
         }
      }else if(num==2){
          room=$(obj).val();
          if(room==0){
           room=null;
         }
      }else if(num==4){
        dictrait=$(obj).attr("value");
         if(dictrait==null || dictrait==''|| dictrait==undefined){
           dictrait='';
         }
      }else if(num==5){
         wyType=$(obj).attr("value");
          if(wyType==0){
           wyType=null;
         }
      }else if(num==7){
         $("#supId").attr('class','s_up_active');
         $("#supIds").attr('class','s_down');
         $("#sortId").attr('class','s_up');
         ordering=2;
      }else if(num==9){
      $('#supIds').attr('class','s_down_active');
      $("#supId").attr('class','s_up');
      $("#sortId").attr('class','s_up');
         ordering=1
      }else if(num==10){
        $("#sortId").attr('class','s_up_active');
        $("#supId").attr('class','s_up');
        $("#supIds").attr('class','s_down');
        ordering=null;
      }else if(num==11){
         var px=$("#inputseek").val();
         if(px=="" || px==null){
             pmn=null;
             pmx=null;
             price=null;
         }else{
          var strs= new Array(); //定义一数组 
           strs=px.split("-");
           pmn=strs[0];
           pmx=strs[1];
           price=null;
         }
      }
      if(statusType==1){
      destory();
      var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++) {
		      map.removeOverlay(allOverlay[i]);
		}	
	 if(u==null || u==11 || u==12){
	   types=1;
	    baidu(xy);
	    huixianArea(types)
	 }else{
	    types=2;
	    baiduClick(xy1);
	    huixianArea(types)
	 }
     }else if(statusType==2){
      arrays();
      destory();
      var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++) {
		      map.removeOverlay(allOverlay[i]);
		}	
		if(u==null || u<16){
	    subwayStation();
	     types=1;
		 subxaioqu();
	   }else{
            var lng = that._point.lng;
	    	var lat = that._point.lat; 
	    	var point = new BMap.Point(lng,lat);
	    	map.centerAndZoom(point, 16);
        for(var j in xy3){
	    	var name=xy3[j].name;
	    	 pt = new BMap.Point(xy3[j].dimension , xy3[j].accuracy);
	    	var myLabel = new BMap.Label("", {offset:new BMap.Size(-60,-60),  position:pt}); 
	    			myLabel.setStyle({ //给label设置样式，任意的CSS都是可以的
	    			color:"red", //颜色
	    			fontSize:"14px", //字号
	    			border:"0", //边
	    			height:"120px", //高度
	    			width:"125px", //宽
	    			textAlign:"center", //文字水平居中显示
	    			lineHeight:"120px", //行高，文字垂直居中显示
	    			background:"url(../static/front/img/map/subway.png) no-repeat center", //背景图片，这是房产标注的关键！
	    			cursor:"pointer"
	    			});
			  map.addOverlay(myLabel); 
        }
    	                    subway="";
				            subwayline=subwaylineId;
				            destory();
					    	queryLists();
					    	subway="";
	                        types=2;
	                        huixian1(types);
	   }
     }
    }
    
    function arrays(){
       var counts=null; 
	 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	   var vers = map.getOverlays();
         array="";
		 for(var i=0;i<vers.length;i++){
 	       var po=vers[i]._point;
 	       var count=vers[i].count;
 	       if(po==null || po=='' || po==undefined){
 	    	   
 	       }else if(count==''|| count==undefined){
 	    	   
 	       }else{
 	    	 var pts=new BMap.Point(vers[i]._point.lng , vers[i]._point.lat);
 	    	 if(bs.containsPoint(pts)==true){
      			var ids=vers[i].id;
      			array+=","+ids+",&";
      		 }
 	       }
 	   }
 	   array=array.substring(0,array.length-1);
 	   subway=array;
 	   subwayline="";
 	   types=1;
    }
     
     //清空搜索条件
     function clean(){
       $("#dicNameId").val("");
       $("#region").html("");
       $("#area").html("");
       room=null;
       price=null;
       pmn=null;
       pmx=null;
       wyType=null;
       keywords="";
       ordering=1;
       dictrait="";
       subwayline="";
       subway="";
       u = map.getZoom();
       var allOverlay = map.getOverlays();
      for (var i = 0; i < allOverlay.length; i++) {
    	map.removeOverlay(allOverlay[i]);
      }
       if(statusType==1){
	        if(u==null || u==12){
	        types=1;
	        destory();
		    baidu(xy);
		    $("#sortId").attr('class','s_up_active');
		    huixian(types);
	       }else{
	          types=2;
	        destory();
		    baiduClick(xy1);
		    $("#sortId").attr('class','s_up_active');
		    huixian(types);
	       }
       }else{
        subwayStation();
         var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length; i++) {
            	map.removeOverlay(allOverlay[i]);
            }
        types=1;
        subxaioqu();
        $("#ulId").empty();
       }
     }
     
     //查询楼盘
     function findDicName(){
        statusType=1;
       keywords=$("#dicNameId").val();
       if(keywords!=""){
           destory();
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length; i++) {
            	map.removeOverlay(allOverlay[i]);
            }
            u = map.getZoom();
            if(u==null || u==12){
                types=1;
               baidu(xy);				    		
            }else{
              types=2;
              destory();
	          baiduClick(xy1);
	          huixianArea(types);
           }
       }
    }
    
    
     //点击地铁，清除覆盖物
      function sub(){
        statusType=2;
        var allOverlay = map.getOverlays();
        for (var i = 0; i < allOverlay.length; i++) {
        	map.removeOverlay(allOverlay[i]);
        }
     }
    
    //地铁
     function subLine(subwayId){
       isWheelZoom=false;
        var allOverlay = map.getOverlays();
        for (var i = 0; i < allOverlay.length; i++) {
        	map.removeOverlay(allOverlay[i]);
        }
        subwayline=subwayId;
        subwaylineId=subwayId;
        $.ajax({
	            type: "POST",
	            url: _ctx+"/map/findNewStations",
	            async:false,
	            data:{subwayId:subwayId
	                   },
	            dataType: "json",
	            success: function(data){
	                xy3=data.data;
	                var counts=null;
	                var count=0;
	                var str='<li><strong>不限</strong></li>';
	                for(var i=0;i<xy3.length;i++){
	                   str+='<li value="'+xy3[i].id+'" onclick="subLineClick('+xy3[i].accuracy+','+xy3[i].dimension+','+xy3[i].id+')">'+xy3[i].name+'</li>'
	                }
	                $("#subId").html("");
	                $("#subId").append(str);
	                subwayStation();
	                types=1;
	                subxaioqu();
	            }
	      });
     }
     
       function ddd(subwayline,subway){
       if(subwayline=="" && subway==""){
           $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	      $("#ulId").empty();
       }else{
       pageNum=1;
        $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  wyType:wyType,
	                  pmn:pmn,
	                  pmx:pmx,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
   					var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  					$("#ulId").empty();
  				    $("#ulId").append(str);
	              }
	            }
	      });
	      }
     }
     
      function subxaioqu(){
         var counts=null; 
	 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	   var vers = map.getOverlays();
         array="";
		 for(var i=0;i<vers.length;i++){
 	       var po=vers[i]._point;
 	       var count=vers[i].count;
 	       if(po==null || po=='' || po==undefined){
 	    	   
 	       }else if(count==''|| count==undefined){
 	    	   
 	       }else{
 	    	 var pts=new BMap.Point(vers[i]._point.lng , vers[i]._point.lat);
 	    	 if(bs.containsPoint(pts)==true){
      		   counts +=parseInt(count);
      			var ids=vers[i].id;
      			array+=","+ids+",&";
      		 }
 	       }
 	   }
 	   if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
 	   $("#countId").text(counts);
 	   array=array.substring(0,array.length-1);
 	   subway=array;
 	   subwayline="";
 	   types=1;
 	    ddd(subwayline,subway);
	 }
     
      function subLineClick(lng,lat,id){
          var allOverlay = map.getOverlays();
				            for (var i = 0; i < allOverlay.length; i++) {
				            	map.removeOverlay(allOverlay[i]);
				            }
						    	var point = new BMap.Point(lng,lat);
						    	map.centerAndZoom(point, 16);
				            for(var j in xy3){
						    	var name=xy3[j].name;
						    	 pt = new BMap.Point(xy3[j].dimension , xy3[j].accuracy);
						    	var myLabel = new BMap.Label("", {offset:new BMap.Size(-60,-60),  position:pt}); 
						    			myLabel.setStyle({ //给label设置样式，任意的CSS都是可以的
						    			color:"red", //颜色
						    			fontSize:"14px", //字号
						    			border:"0", //边
						    			height:"120px", //高度
						    			width:"125px", //宽
						    			textAlign:"center", //文字水平居中显示
						    			lineHeight:"120px", //行高，文字垂直居中显示
						    			background:"url(../static/front/img/map/subway.png) no-repeat center", //背景图片，这是房产标注的关键！
						    			cursor:"pointer"
						    			});
								  map.addOverlay(myLabel); 
				            }
				            subway=id;
				            destory();
				           queryLists();
     }
     
      //地铁小区展示
	function queryLists(){
	   for (var i in xy1) {
				 			   pt = new BMap.Point(xy1[i].lng , xy1[i].lat);
				 			   name=xy1[i].name;
				 			   count=xy1[i].count;
				 			   id=xy1[i].id;
						    	
						    	// 复杂的自定义覆盖物
						        function ComplexCustomOverlay(point, text, mouseoverText,id,count){
						          this._point = point;
						          this._text = text;
						          this._overText = mouseoverText;
						          this.id=id;
						          this.count=count;
						        }
						        ComplexCustomOverlay.prototype = new BMap.Overlay();
						        ComplexCustomOverlay.prototype.initialize = function(map){
						          this._map = map;
						          var div = this._div = document.createElement("div");
						          div.style.position = "absolute";
						          div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
						          div.style.backgroundColor = "#EE5D5B";
						          div.style.border = "1px solid #BC3B3A";
						          div.style.color = "white";
						          div.style.height = "18px";
						          div.style.padding = "2px";
						          div.style.lineHeight = "18px";
						          div.style.whiteSpace = "nowrap";
						          div.style.MozUserSelect = "none";
						          div.style.fontSize = "12px"
						          var span = this._span = document.createElement("span");
						          div.appendChild(span);
						          span.appendChild(document.createTextNode(this._text));      
						          var that = this;

						          var arrow = this._arrow = document.createElement("div");
						          arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
						          arrow.style.position = "absolute";
						          arrow.style.width = "11px";
						          arrow.style.height = "10px";
						          arrow.style.top = "22px";
						          arrow.style.left = "10px";
						          arrow.style.overflow = "hidden";
						          div.appendChild(arrow);
						         
						          div.onmouseover = function(){
						            this.style.backgroundColor = "#6BADCA";
						            this.style.borderColor = "#0000ff";
						            this.getElementsByTagName("span")[0].innerHTML = that._overText;
						            arrow.style.backgroundPosition = "0px -20px";
						          }

						          div.onmouseout = function(){
						            this.style.backgroundColor = "#EE5D5B";
						            this.style.borderColor = "#BC3B3A";
						            this.getElementsByTagName("span")[0].innerHTML = that._text;
						            arrow.style.backgroundPosition = "0px 0px";
						          }
						          
								   div.addEventListener("click", function(){
								   isWheelZoom=false;
								    var array=that.id;
						            count=that.count;
									    if(count==null){
									     count=0;
									    }
									    $("#countId").text(count);
									    types=2;
									    arrayDrectory(array,types);
					              });

						          map.getPanes().labelPane.appendChild(div);
						          
						          return div;
						        }
						        ComplexCustomOverlay.prototype.draw = function(){
						          var map = this._map;
						          var pixel = map.pointToOverlayPixel(this._point);
						          this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
						          this._div.style.top  = pixel.y - 30 + "px";
						        }
						        var txt = name, mouseoverTxt = txt + " " + count + "个楼盘" ;
						            
						        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

						        map.addOverlay(myCompOverlay);
						   }
						    var vers = map.getOverlays();
	}
     
     //加载地铁覆盖物
     function subwayStation(){
                var lng = this.point.lng;
		    	var lat = this.point.lat; 
		    	var point = new BMap.Point(lng,lat);
		    	map.centerAndZoom(point, 12);                       //设立中心点和地图级别，就是初始化地图
        for (var i in xy3) {
			   pt = new BMap.Point(xy3[i].dimension , xy3[i].accuracy);
			   name=xy3[i].name;
			   count=xy3[i].count;
			   if(count==undefined){
			   count=0;
			   }
			   id=xy3[i].id;
		    	if(count==0){
		    	
		    	}else{
		    	// 复杂的自定义覆盖物
		        function ComplexCustomOverlay(point, text, mouseoverText,id,count){
		          this._point = point;
		          this._text = text;
		          this._overText = mouseoverText;
		          this.id=id;
		          this.count=count;
		        }
		        ComplexCustomOverlay.prototype = new BMap.Overlay();
		        ComplexCustomOverlay.prototype.initialize = function(map){
		          this._map = map;
		          var div = this._div = document.createElement("div");
		          div.style.position = "absolute";
		          div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
		          div.style.backgroundColor = "#EE5D5B";
		          div.style.border = "1px solid #BC3B3A";
		          div.style.color = "white";
		          div.style.height = "25px";
		          div.style.padding = "2px";
		          div.style.lineHeight = "20px";
		          div.style.whiteSpace = "nowrap";
		          div.style.MozUserSelect = "none";
		          div.style.fontSize = "15px"
		          var span = this._span = document.createElement("span");
		          div.appendChild(span);
		          span.appendChild(document.createTextNode(this._overText));      
		          var that = this;

		          var arrow = this._arrow = document.createElement("div");
		          arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
		          arrow.style.position = "absolute";
		          arrow.style.width = "11px";
		          arrow.style.height = "10px";
		          arrow.style.top = "22px";
		          arrow.style.left = "10px";
		          arrow.style.overflow = "hidden";
		          div.appendChild(arrow);
		         
		          div.onmouseover = function(){
		            this.style.backgroundColor = "#6BADCA";
		            this.style.borderColor = "#0000ff";
		            this.style.zIndex="10";
		            arrow.style.backgroundPosition = "0px -20px";
		          }

		          div.onmouseout = function(){
		            this.style.backgroundColor = "#EE5D5B";
		            this.style.borderColor = "#BC3B3A";
		            this.style.zIndex="0";
		            arrow.style.backgroundPosition = "0px 0px";
		          }
		          
		          div.addEventListener("click", function(){
		                  isWheelZoom=false;
		        		    var allOverlay = map.getOverlays();
				            for (var i = 0; i < allOverlay.length; i++) {
				            	map.removeOverlay(allOverlay[i]);
				            }
				                var lng = that._point.lng;
						    	var lat = that._point.lat; 
						    	var point = new BMap.Point(lng,lat);
						    	map.centerAndZoom(point, 16);
				            for(var j in xy3){
						    	var name=xy3[j].name;
						    	 pt = new BMap.Point(xy3[j].dimension , xy3[j].accuracy);
						    	var myLabel = new BMap.Label("", {offset:new BMap.Size(-60,-60),  position:pt}); 
						    			myLabel.setStyle({ //给label设置样式，任意的CSS都是可以的
						    			color:"red", //颜色
						    			fontSize:"14px", //字号
						    			border:"0", //边
						    			height:"120px", //高度
						    			width:"125px", //宽
						    			textAlign:"center", //文字水平居中显示
						    			lineHeight:"120px", //行高，文字垂直居中显示
						    			background:"url(../static/front/img/map/subway.png) no-repeat center", //背景图片，这是房产标注的关键！
						    			cursor:"pointer"
						    			});
								  map.addOverlay(myLabel); 
				            }
				             subway="";
				            subwayline=subwaylineId;
				            destory();
					    	queryLists();
					    	subway="";
	                        types=2;
	                        huixian1(types);
			       });

		          map.getPanes().labelPane.appendChild(div);
		          return div;
		        }
		        ComplexCustomOverlay.prototype.draw = function(){
		          var map = this._map;
		          var pixel = map.pointToOverlayPixel(this._point);
		          this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
		          this._div.style.top  = pixel.y - 30 + "px";
		        }
		        var txt = name, mouseoverTxt = txt + " " + count + "个楼盘" ;
		            
		        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

		        map.addOverlay(myCompOverlay);
		      } 
 		}
	}
     
	 //加载区域覆盖物
	function baidu(xy){
	   isWheelZoom=false;
	    are=[];
		for (var i in xy) {
			   pt = new BMap.Point(xy[i].lng , xy[i].lat);
			   name=xy[i].name;
			   count=xy[i].count;
			   id=xy[i].id;
			   are.push(id);
			   if(keywords!=""){
			     if(u==11){
			     map.centerAndZoom(pt, 11);
			     }else if(u==12){
			     map.centerAndZoom(pt, 12);
			     }
			   }
			   var myLabel = new BMap.Label(("<div class='p_kuang'><p class='pclassname' value="+name+">"+name+"</p><p class='pclassname'>"+count+"个楼盘</p><input type='hidden' value="+id+"></div>"),     //为lable填写内容
				        {offset:new BMap.Size(-60,-60),                  //label的偏移量，为了让label的中心显示在点上
				        position:pt});                                //label的位置
				    myLabel.setStyle({                                   //给label设置样式，任意的CSS都是可以的
				        fontSize:"12px",               //字号
				        border:"0",                    //边
				        height:"95px",                //高度
				        width:"100px",                 //宽
				        textAlign:"center",            //文字水平居中显示
				        lineHeight:"75px",            //行高，文字垂直居中显示
				        size: new BMap.Size(92, 92),
						backgroundColor : '#2775e9', 
  						boxShadow: '0px 1px 4.5px 0.5px rgba(0, 0, 0, 0.3)',
				        cursor:"pointer",
				        borderRadius:"50%",
				        color:"#fff"
				        
				    });
				   myLabel.customData={count:count};
				   map.addOverlay(myLabel); 
				 //鼠标移上事件（显示区域）
				   myLabel.addEventListener("mouseover", function(){ 
					   this.setStyle({                                   //给label设置样式，任意的CSS都是可以的
				    		backgroundColor : '#ed543f',
  							boxShadow: '0px 1px 4.5px 0.5px rgba(0, 0, 0, 0.3)',
				    		zIndex:"10"
				        });
				    });
				 
				    //鼠标离开事件（清除区域）
				    myLabel.addEventListener("mouseout", function(){  
				    	this.setStyle({                                   //给label设置样式，任意的CSS都是可以的
				            fontSize:"12px",               //字号
				            border:"0",                    //边
				            height:"95px",                //高度
				            width:"100px",                 //宽
				            textAlign:"center",            //文字水平居中显示
				            lineHeight:"75px",            //行高，文字垂直居中显示
				            size: new BMap.Size(92, 92),
				    		backgroundColor : '#2775e9', 
  							boxShadow: '0px 1px 4.5px 0.5px rgba(0, 0, 0, 0.3)',
				            cursor:"pointer",
				            borderRadius:"50%",
				            color:"#fff",
				            zIndex:"0"
				        });
				    });


				    
				    //点击事件（判断是一级点击还是二级点击）
				    myLabel.addEventListener("click", function(){ 
				    	 u = map.getZoom();
				    	isWheelZoom=false;
				    		var allOverlay = map.getOverlays();
				            for (var i = 0; i < allOverlay.length; i++) {
				            	map.removeOverlay(allOverlay[i]);
				            }
					    	var lng = this.point.lng;
					    	var lat = this.point.lat; 
					    	var point = new BMap.Point(lng,lat);
					    	map.centerAndZoom(point, 13);                       //设立中心点和地图级别，就是初始化地图
					    	var label = $(this.content).get(0);
	     			        var areas=$(label).find("p").get(0).innerHTML;
					    	$("#region").text(areas)
      	                     ids=$(this.content).find("input").val();
      	                     destoryArray();
					    	baiduClick(xiaoquList);
					    	window.setTimeout(show,1000);
					    	function show(){
						    	for(var j=0;j<are.length;j++){
						    	   if(are[j]==ids){
						    	      are.splice(j,1);
						    	   }
					    	}
					    	var areaList="";
					    	for(var j=0;j<are.length;j++){
					    	  areaList +=are[j]+",";
					    	}
					    	ids=areaList.substring(0,areaList.length-1);
					    	destoryArray();
					    	baiduClick(xiaoquList);
					    	types=2;
					    	huixianArea(types);
				    	}
				    });  
		}
		 types=1;
	     huixian(types);
   }
   
   function baiduClick(xy){
		for (var i in xy) {
			   pt = new BMap.Point(xy[i].lng,xy[i].lat);
			   name=xy[i].name;
			   count=xy[i].count;
			   id=xy[i].id;
			   if(keywords!=""){
			   map.centerAndZoom(pt, 12);
			   }
			   var myIcon = new BMap.Icon("http://7xic1p.com1.z0.glb.clouddn.com/markers.png", new BMap.Size(23, 25), {  
                        offset: new BMap.Size(10, 25), // 指定定位位置  
                        imageOffset: new BMap.Size(0, 0 - 10 * 25) // 设置图片偏移  
                    });
			      var  marker=new BMap.Marker(pt,{icon:myIcon});
                      var label = new BMap.Label(("<div style='color:#fff;border:none;'><span style='background-color:red;color:#fff;display: inline-block;padding:5px 10px;' value="+name+">"+name+"</span><span style='background-color:#fff;color:#333;display: inline-block;padding:5px 10px;'>"+count+"个楼盘</span><input type='hidden' value="+id+"></div>"),{offset:new BMap.Size(35,-10),position:pt});
                      label.setStyle({ 
                       	padding:"none",
                      	border:"none",
                      	left:"20px", 
                      	display:"none"  //给label设置样式，任意的CSS都是可以的
                       });
                      label.setTitle(id); 
                      marker.setLabel(label);
                      marker.customData={count:count};
				 //鼠标移上事件（显示区域）
				   marker.addEventListener("mouseover", function(){ 
				     this.z.label.setStyle({ 
				      padding:"none",
                      border:"none",
                      left:"20px", 
                      display:"block"  //给label设置样式，任意的CSS都是可以的
                       });
				    });
				 
				    //鼠标离开事件（清除区域）
				    marker.addEventListener("mouseout", function(){ 
				       if(mak==null){
				          this.z.label.setStyle({    //给label设置样式，任意的CSS都是可以的
                              display:"none"
                             }); 
				       }else{
				       if(this.point.lng==mak.point.lng && this.point.lat==mak.point.lat){
				           this.z.label.setStyle({    //给label设置样式，任意的CSS都是可以的
                              display:"block"
                             }); 
				       }else{
				    	  this.z.label.setStyle({    //给label设置样式，任意的CSS都是可以的
                              display:"none"
                             }); 
                         }  
                       }  
				    });


				    
				    //点击事件（判断是一级点击还是二级点击）
				    marker.addEventListener("click", function(){
				        //如果mak不等于空，删除之前的样式
				          if(mak !=null ){
				           mak.z.label.setStyle({    //给label设置样式，任意的CSS都是可以的
                                      display:"none"
                                      }); 
                           }                
				          mak=this;
				          u = map.getZoom();
				          var lng = this.point.lng;
					      var lat = this.point.lat; 
					      var lab = this.z.label;
				          var label=lab.content;
	     			      var area=$(label).find("span").get(0).innerHTML;
	     			      var id=$(label).find("input").val();
				    	  $("#area").text(area)
				          var point = new BMap.Point(lng,lat);
					      map.centerAndZoom(point, u);                       //设立中心点和地图级别，就是初始化地图
                          types=2;
	                     arrayDrectory(id,types);  
	                     $("#countId").text(count);   
				    });  
				    map.addOverlay(marker);
		}
   }
   
   
   
	 function huixian(types){
	   u = map.getZoom(); // 定义地图缩放等级的变量
	   var counts=null; 
		 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	    var vers = map.getOverlays();
	     array="";
	    for(var i=0;i<vers.length;i++){
		       var po=vers[i].point;
		       var count=vers[i].customData.count; 
		       if(po==null || po=='' || po==undefined){
		    	   
		       }else if(count==''|| count==undefined){
		    	   
		       }else{
		    	 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
		    	 if(bs.containsPoint(pts)==true){
	     		   counts +=parseInt(count);
	     		    if(u==12){
	     		      var label = vers[i].getContent();
	     			var ids=$(label).find("input").val();
	     		    }else{
	     		     var label =vers[i].z.label.content;
      	              var ids=$(label).find("input").val();
	     		    }
	     			
	     			array +=ids+",";
	     		}
		       }
	    }
	    array=array.substring(0,array.length-1);
	    arrayDrectory(array,types);
	    if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
	    $("#countId").text(counts);
}

function huixian1(types){
	   var counts=null; 
		 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	    var vers = map.getOverlays();
	     array="";
	    for(var i=0;i<vers.length;i++){
		       var po=vers[i]._point;
		        var count=vers[i].customData.count; 
		       if(po==null || po=='' || po==undefined){
		    	   
		       }else if(count==null || count==undefined){
		       
		       }else{
		    	 var pts=new BMap.Point(po.lng , po.lat);
		    	 if(bs.containsPoint(pts)==true){
	     		   counts +=parseInt(count);
	     			var label = $(vers[i].content).get(0);
	     			var ids=vers[i].id;
	     			array +=ids+",";
	     		}
		       }
	    }
	    array=array.substring(0,array.length-1);
	    arrayDrectory(array,types);
	    if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
	    $("#countId").text(counts);
}

function huixianArea(types){
	   u = map.getZoom(); // 定义地图缩放等级的变量
	   var counts=null; 
		 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	    var vers = map.getOverlays();
	     array="";
	    for(var i=0;i<vers.length;i++){
		       var po=vers[i].point;
		       var count=vers[i].customData.count; 
		       if(po==null || po=='' || po==undefined){
		    	   
		       }else if(count==''|| count==undefined){
		    	   
		       }else{
		    	 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
		    	 if(bs.containsPoint(pts)==true){
	     		   counts +=parseInt(count);
	     		  var label = vers[i].getContent();
	     	      var ids=$(label).find("input").val();
      			   array+=ids+",";
	     		}
		       }
	    }
	    array=array.substring(0,array.length-1);
	     if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
	    $("#countId").text(counts);
	    dicNameFind(types);
	   
}

function dicNameFind(types){
   if(array==''){
   $("#ulId").empty();
   }else{
     pageNum=1;
   if(types==1){
      $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{areaCode3s:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					if(rePrice==undefined){
   					   rePrice="<span>售价待定</span>";
   					}else{
   					   rePrice="<span>"+rePrice+"</span>元/m²";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
   					var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+rePrice+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  					$("#ulId").empty();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }else if(types==2){
     $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{dicIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                   pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					if(rePrice==undefined){
   					   rePrice="<span>售价待定</span>";
   					}else{
   					   rePrice="<span>"+rePrice+"</span>元/m²";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
                    var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+rePrice+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  					$("#ulId").empty();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }
   }
}
function arrayDrectory(array,types){	
   if(array==''){
     array=0;
   }
   pageNum=1;
   if(types==1){
      $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{areaCode3s:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
   					var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  					$("#ulId").empty();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }else if(types==2){
     $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{dicIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
                    var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  					$("#ulId").empty();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }
}
	
//鼠标拉到列表页底部加载更多数据
$("#ulId").scroll(function(){
     	   var $this =$(this),
           viewH =$(this).height(),//可见高度
          contentH =$(this).get(0).scrollHeight,//内容高度
          scrollTop =$(this).scrollTop();//滚动高度
           if(statusType==1){
          if(u==null||u==12){
            types=1;
          }else{
            types=2;
          }
        if(scrollTop/(contentH -viewH)>=0.95){ //到达底部100px时,加载新内容
           pageNum++;
           if(types==1){
              $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{areaCode3s:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
   					var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }else if(types==2){
             $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{dicIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
   					var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }
        }
        }else if(statusType==2){
            if(u==null||u==11||u==12|| u==13 || u==14 || u==15){
            types=1;
            }else{
            types=2
            }
           if(scrollTop/(contentH -viewH)>=0.95){ //到达底部100px时,加载新内容
           pageNum++;
           if(types==1){
              $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{
	                  areaCode3s:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
   					var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
   					var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }else if(types==2){
             $.ajax({
	            type: "POST",
	            url: _ctx+"/map/newHouseArrayDrectory",
	            async:false,
	            data:{
	                  dicIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  price:price,
	                  pmn:pmn,
	                  pmx:pmx,
	                  wyType:wyType,
	                  keywords:keywords,
	                  ordering:ordering,
	                  subwayline:subwayline,
	                  subway:subway,
	                  isSaleOut:2
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
   					var url=_ctx+"/front/newHouse/index_detail?id="+recordList[i].id;
        			var rePrice=recordList[i].referencePriceMin;
   					var totalPriceMin=recordList[i].totalPriceMin;
   					var aroundPriceMin=recordList[i].aroundPriceMin;
   					var prices=null;
   					if(rePrice !=undefined){
   					 prices= "单价<span>"+rePrice+"</span>元/m²"
   					}else if(totalPriceMin !=undefined ){
   					prices="总价 <span>"+totalPriceMin+"</span>万元/套"
   					}else if(aroundPriceMin !=undefined){
   					prices="周边均价<span>"+aroundPriceMin+"</span>元/m²"
   					}else{
   					  prices="<span>售价待定</span>";
   					}
   					var status=recordList[i].status;
   					if(status==1){
   					   status="待售";
   					}else if(status==2){
   					  status="在售";
   					}else if(status==3){
   					  status="售完";
   					}
        			var img=recordList[i].imageUrl;
   					if(img==undefined){
   					  img="../static/front/img/default/default_list.png";
   					}
   					var houseType=recordList[i].roomNames;
   					if(houseType==undefined){
   					  houseType="户型暂未公布";
   					}
   					str +='<a href="'+url+'"><img src="'+img+'"><div class="l_text"><h4>'+recordList[i].name+'</h4><div><span class="labelling color_0">'+status+'</span><p>'+prices+'</p></div><ul><li class="word_spec">'+houseType+'</li></ul></div></a>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }
        }
         }
     	});     

</script>
<script type="text/javascript">
//鼠标缩放监听事件
map.addEventListener("zoomend", function(type){
	if (!type.lD) {
	     mak=null;
		 u = map.getZoom(); // 定义地图缩放等级的变量
		if(isWheelZoom==true && statusType==1){
		  $("#region").html("");
          $("#area").html("");
		if(u==11 || u==12){
				var allOverlay = map.getOverlays();
	         for (var i = 0; i < allOverlay.length; i++) {
	         	map.removeOverlay(allOverlay[i]);
	         }
		    	baidu(xy);
		    	types=1;
		    	huixian(types);
		  }else{
			 var allOverlay = map.getOverlays();
	         for (var i = 0; i < allOverlay.length; i++) {
	         	map.removeOverlay(allOverlay[i]);
	         }
		    	baiduClick(xy1);
		    	types=2;
		    	huixian(types);
		    }
		}else if(isWheelZoom==true && statusType==2){
		   $("#region").html("");
           $("#area").html("");
		  if(u<16){
		     var allOverlay = map.getOverlays();
		        for (var i = 0; i < allOverlay.length; i++) {
		        	map.removeOverlay(allOverlay[i]);
		        }
				for (var i in xy3) {
					   pt = new BMap.Point(xy3[i].dimension , xy3[i].accuracy);
					   name=xy3[i].name;
					   count=xy3[i].count;
					   if(count==undefined){
					     count=0;
					   }
					   id=xy3[i].id;
				    	if(count==0){
				    	
				    	}else{
				    	// 复杂的自定义覆盖物
				        function ComplexCustomOverlay(point, text, mouseoverText,id,count){
				          this._point = point;
				          this._text = text;
				          this._overText = mouseoverText;
				          this.id=id;
				          this.count=count;
				        }
				        ComplexCustomOverlay.prototype = new BMap.Overlay();
				        ComplexCustomOverlay.prototype.initialize = function(map){
				          this._map = map;
				          var div = this._div = document.createElement("div");
				          div.style.position = "absolute";
				          div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
				          div.style.backgroundColor = "#EE5D5B";
				          div.style.border = "1px solid #BC3B3A";
				          div.style.color = "white";
				          div.style.height = "25px";
				          div.style.padding = "2px";
				          div.style.lineHeight = "20px";
				          div.style.whiteSpace = "nowrap";
				          div.style.MozUserSelect = "none";
				          div.style.fontSize = "15px"
				          var span = this._span = document.createElement("span");
				          div.appendChild(span);
				          span.appendChild(document.createTextNode(this._overText));      
				          var that = this;

				          var arrow = this._arrow = document.createElement("div");
				          arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
				          arrow.style.position = "absolute";
				          arrow.style.width = "11px";
				          arrow.style.height = "10px";
				          arrow.style.top = "22px";
				          arrow.style.left = "10px";
				          arrow.style.overflow = "hidden";
				          div.appendChild(arrow);
				         
				          div.onmouseover = function(){
				            this.style.backgroundColor = "#6BADCA";
				            this.style.borderColor = "#0000ff";
				            this.style.zIndex="10";
				            arrow.style.backgroundPosition = "0px -20px";
				          }

				          div.onmouseout = function(){
				            this.style.backgroundColor = "#EE5D5B";
				            this.style.borderColor = "#BC3B3A";
				            this.style.zIndex="0";
				            arrow.style.backgroundPosition = "0px 0px";
				          }
				          
				          div.addEventListener("click", function(){
			        		    var allOverlay = map.getOverlays();
					            for (var i = 0; i < allOverlay.length; i++) {
					            	map.removeOverlay(allOverlay[i]);
					            }
					            for(var j in xy3){
					            	var lng = that._point.lng;
							    	var lat = that._point.lat; 
							    	var point = new BMap.Point(lng,lat);
							    	map.centerAndZoom(point, 16);
							    	var name=xy3[j].name;
							    	 pt = new BMap.Point(xy3[j].dimension , xy3[j].accuracy);
							    	var myLabel = new BMap.Label("", {offset:new BMap.Size(-60,-60),  position:pt}); 
							    			myLabel.setStyle({ //给label设置样式，任意的CSS都是可以的
							    			color:"red", //颜色
							    			fontSize:"14px", //字号
							    			border:"0", //边
							    			height:"120px", //高度
							    			width:"125px", //宽
							    			textAlign:"center", //文字水平居中显示
							    			lineHeight:"120px", //行高，文字垂直居中显示
							    			background:"url(../static/front/img/map/subway.png) no-repeat center", //背景图片，这是房产标注的关键！
							    			cursor:"pointer"
							    			});
									  map.addOverlay(myLabel); 
					            }
						    	
				       });

				          map.getPanes().labelPane.appendChild(div);
				          return div;
				        }
				        ComplexCustomOverlay.prototype.draw = function(){
				          var map = this._map;
				          var pixel = map.pointToOverlayPixel(this._point);
				          this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
				          this._div.style.top  = pixel.y - 30 + "px";
				        }
				        var txt = name, mouseoverTxt = txt + " " + count + "个楼盘" ;
				            
				        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

				        map.addOverlay(myCompOverlay);
		 		   }
		 		}
		      types=1;
		      subxaioqu();
		  }else if(u==16){
		           var allOverlay = map.getOverlays();
		            for (var i = 0; i < allOverlay.length; i++) {
		            	map.removeOverlay(allOverlay[i]);
		            }
		            
		            for(var j in xy3){
				    	var name=xy3[j].name;
				    	 pt = new BMap.Point(xy3[j].dimension , xy3[j].accuracy);
				    	var myLabel = new BMap.Label("", {offset:new BMap.Size(-60,-60),  position:pt}); 
				    			myLabel.setStyle({ //给label设置样式，任意的CSS都是可以的
				    			color:"red", //颜色
				    			fontSize:"14px", //字号
				    			border:"0", //边
				    			height:"120px", //高度
				    			width:"125px", //宽
				    			textAlign:"center", //文字水平居中显示
				    			lineHeight:"120px", //行高，文字垂直居中显示
				    			background:"url(../static/front/img/map/subway.png) no-repeat center", //背景图片，这是房产标注的关键！
				    			cursor:"pointer"
				    			});
						  map.addOverlay(myLabel); 
		            }
		            
		            destory();
			        queryLists();
				    types=2;
		 		    huixian1(types);
		  }
		}else{
		   isWheelZoom=true;
		}
    }
});


//拖拽结束事件
map.addEventListener("dragend", function(e){ 
     if(mak==null){
     
     }else{
        mak.z.label.setStyle({    
       //给label设置样式，任意的CSS都是可以的
            display:"none"
       }); 
     }
     
      $("#region").html("");
      $("#area").html("");
	  var counts=null; 
	 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	   var vers = map.getOverlays();
	  u = map.getZoom(); // 定义地图缩放等级的变量
	   if(isWheelZoom==true && statusType==1){
	  if(u==12){
	       array="";
		for(var i=0;i<vers.length;i++){
 	       var po=vers[i].point;
 	        var customData=vers[i].customData; 
 	       if(po==null || po=='' || po==undefined){
 	    	   
 	       }else if(customData==''|| customData==undefined){
 	    	   
 	       }else{
 	    	 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
 	    	 var count=customData.count;
 	    	 if(bs.containsPoint(pts)==true){
      		   counts +=parseInt(count);
      			var label = vers[i].getContent();
	     	    var ids=$(label).find("input").val();
      			array+=ids+",";
      		 }
 	       }
 	   }
 	   if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
 	   $("#countId").text(counts);
 	   array=array.substring(0,array.length-1);
 	   types=1;
 	    if(keywords!=""&&counts!=0){
 	    dicNameFind(types)
       
       }else{
       arrayDrectory(array,types);
       }
 	    
	 }else{
	  array="";
		for(var i=0;i<vers.length;i++){
			 var customData=vers[i].customData; 
      	    var po=vers[i].point;
			if(customData==null || customData=='' || customData==undefined){
				
			}else if(po=='' || po==null || po == undefined){
			
			}else{
				 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
				 var count=customData.count;
     	    	 if(bs.containsPoint(pts)==true){
          		   counts +=parseInt(count);
          		   var label =vers[i].z.label.content;
      	           var id=$(label).find("input").val();
          			array+=id+",";
          		}
			}
		}
		if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
		 $("#countId").text(counts);
		types=2;
		array=array.substring(0,array.length-1);
		if(keywords!=""&&counts!=0){
 	    dicNameFind(types)
       }else{
       arrayDrectory(array,types);
       }
	 
	 }
	 }else if(isWheelZoom==true && statusType==2){
	    if(u==16){
				array="";
		for(var i=0;i<vers.length;i++){
			 var customData=vers[i].customData; 
			var id=vers[i].id;
			if(customData==null || customData=='' || customData==undefined){
				
			}else if(id==''|| id==undefined){
				
			}else{
				 var pts=new BMap.Point(vers[i]._point.lng , vers[i]._point.lat);
				 var count=customData.count;
     	    	 if(bs.containsPoint(pts)==true){
          		   counts +=parseInt(count);
          			array+=id+",";
          		}
			}
		}
		if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
		 $("#countId").text(counts);
		types=2;
		array=array.substring(0,array.length-1);
		if(keywords!=""&&counts!=0){
 	    dicNameFind(types)
       }else{
       subway="";
       arrayDrectory(array,types);
       }
	}else if(u<16){
				 array="";
		 for(var i=0;i<vers.length;i++){
 	       var po=vers[i]._point;
 	        var customData=vers[i].customData; 
 	       if(po==null || po=='' || po==undefined){
 	    	   
 	       }else if(customData==''|| customData==undefined){
 	    	   
 	       }else{
 	    	 var pts=new BMap.Point(vers[i]._point.lng , vers[i]._point.lat);
 	    	 var count=customData.count;
 	    	 if(bs.containsPoint(pts)==true){
      		   counts +=parseInt(count);
      			var ids=vers[i].id;
      			array+=","+ids+",&";
      		 }
 	       }
 	   }
 	   if(counts==null){
	     counts=0;
	      $("#inheritId").css("visibility","hidden");
	      $("#notHouseId").css("display","block");
	    }else{
	      $("#inheritId").css("visibility","inherit");
	      $("#notHouseId").css("display","none");
	    }
 	   $("#countId").text(counts);
 	   array=array.substring(0,array.length-1);
 	   if(array==""){
 	     $("#ulId").empty();
 	   }else{
 	   subway=array;
 	   subwayline="";
 	   types=1;
 	    ddd(subwayline,subway);
 	    }
	   }
	 }else{
	   isWheelZoom=true;
	 }
});  

</script>
<script>
	  function seekevent(){
    var seekValue = $("#dicNameId").val();
     var type=$("#typeId").val();
    // click事件
    $("#dicNameId").on("click",function(){
        $(this).removeAttr("placeholder");
        seekValue =  $.trim($("#dicNameId").val());
        if(seekValue != ""){
                $(".grabble_kuang").show();
        }
    })
    
    // keyup事件
    $("#dicNameId").on("keyup",function(){
        seekValue =  $.trim($("#dicNameId").val());
         if(seekValue == ""){
            $(".grabble_kuang").hide();
        }
        // 发送ajax请求
        if(seekValue != ""){
        	$.ajax({
	  			type:"post",
	  			async:true,
	  			data:{"name":seekValue},
	  			dataType:"json",
	  			url:_ctx+"/keyword/search/newHouse",
	  			success:function(resultVo){
	  				if(resultVo.status==200 && resultVo.data!=null){
	  				data=resultVo.data;
	  					$(".grabble_kuang").empty()
	  					for(i=0;i<data.length;i++){
	  						$(".grabble_kuang").append("<li>"+data[i].name+"</li>" );
	  					}
	  					$(".grabble_kuang").show();
					}
	  			}
	  		})
                
        }
    })
    // 将div中的内容赋值给input的value
    $(".grabble_kuang").delegate("li","click",function(){
        	seekValue = $(this).html();
        	document.getElementById("dicNameId").value=seekValue;
        	 $(".grabble_kuang").hide();
    	})
	};
	
	function List_name(txt,idName) {
	        var str = txt;
	        if(str.length >10){
	        	 str = str.substr(0,10) + '...' ;
	       	 	var id=$(idName);
	        	id.html(str);
	        }
	    }
		var data = $(".ellipsis_newhouse_15").html().trim();
		List_name(data,'.ellipsis_newhouse_15');
		
		function loginOut(){
       		$.ajax({
		  		type:"post",
		  		url:_ctx+"/login/loginOut",
		  		data:{
		  			url:window.location.href
		  		},
		  		dataType:"json",
		  		success: function(result){
		  			window.location.href="${ctx}/map/newMap";
		  		}
		  	});
       	}
	</script>
</html>