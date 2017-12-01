<#include "common/taglibs.ftl">
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>大搜家-经纪人</title>
        <link rel="stylesheet" href="${ctx }/static/front/css/dsj_housemap.css">
    </head>
    <body>
        <div class="map">
            <!--头部  -->
            <div class="m_header_kuang">
                <div class="m_header">
                    <h1 class="m_logo">

                    </h1>
                    <div class="m_address">
                        北京
                    </div>
                    <ul class="h_nav">
                        <li>新房</li>
                        <li>二手房</li>
                        <li class="h_nav_active">租房</li>
                    </ul>
                    <ul class="m_login">
                        <li><a href="##">登录</a></li>
                        <li><a href="##">注册</a></li>
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
                                    <input type="text" id="dicNameId" autocomplete="off" placeholder="请输入小区的名称">
                                    <p onclick="findDicName()" class="f_button"></p>
                                </form>
                                <ul class="grabble_kuang">
                                    <li>万科</li>
                                    <li>万科科技</li>
                                    <li>万科集团</li>
                                    <li>万科小程序</li>
                                    <li>万科联盟大队</li>
                                    <li>万科</li>
                                    <li>万科科技</li>
                                    <li>万科集团</li>
                                    <li>万科小程序</li>
                                    <li>万科联盟大队</li>
                                </ul>
                            </li>
                            <!-- 区域/地铁  -->
                            <li class="area_subway"><span>区域/地铁</span>
                                <ul class="area_kuang metro_kuang">
                                    <li>
                                        <ul class="kuang_ul1">
                                            <li class="area_li">区域</li>
                                            <li  class="metro_li">地铁</li> 
                                        </ul> 
                                    </li>
                                     <li>
                                        <ul class="kuang_ul2">
                                            <li><strong>不限</strong></li>
                                            <li>1号线</li>
                                            <li>2号线</li>
                                            <li>3号线</li>
                                            <li>4号线</li>
                                            <li>5号线</li>
                                            <li>6号线</li>
                                            <li>7号线</li>
                                            <li>8号线</li>
                                            <li>9号线</li>
                                            <li>10号线</li>
                                            <li>11号线</li>
                                            <li>12号线</li>
                                            <li>13号线</li>
                                            <li>14号线</li>
                                            <li>15号线</li> 
                                        </ul> 
                                    </li>
                                    <li>
                                        <ul class="kuang_ul3">
                                            <li><strong>不限</strong></li>
                                            <li>四惠</li>
                                            <li>大望路</li>
                                            <li>国货</li>
                                            <li>永安里</li>
                                            <li>建国门</li>
                                            <li>东单</li>
                                            <li>王府井</li>
                                            <li>天安门东</li>
                                            <li>天安门西</li>
                                            <li>西单</li>
                                            <li>复兴门</li>
                                            <li>东单</li>
                                            <li>王府井</li>
                                            <li>天安门东</li>
                                            <li>天安门西</li>
                                            <li>西单</li>
                                        </ul> 
                                    </li>  
                                </ul> 
                            </li>
<!--                             价格  -->
                            <li class="price"><span>价格</span>
                                <ul class="kuang price_kuang">
                                    <li onclick="findMap(this,1)"><strong>不限</strong></li>
                                    <#list rentPriceMap?keys as key>
                                    <li value="${key}" onclick="findMap(this,1)">${rentPriceMap[key]}</li>
                                    </#list>
                                    <li class="m_seek price_seek">
                                    </li>
                                </ul> 
                            </li>
<!--                             户型  -->
                            <li class="house_type"><span>户型</span>
                                <ul class="house_type_kuang" style="display: none">
                                    <li onclick="findMap(this,2)"><strong>不限</strong></li>
                                     <#list roomMap?keys as key>
                                     <li value="${key}" onclick="findMap(this,2)">${roomMap[key]}</li>
                                    </#list>
                                </ul> 
                            </li>
<!--                             面积  -->
                            <li class="area"><span>面积</span>
                                <ul class="kuang mj_kuang">
                                    <li onclick="findMap(this,3)"><strong>不限</strong></li>
                                     <#list rentAreaMap?keys as key>
                                     <li value="${key}" onclick="findMap(this,3)">${rentAreaMap[key]}</li>
                                    </#list>
                                    <li class="m_seek area_seek">
                                    </li>
                                </ul> 
                            </li>
<!--                             更多  -->
                            <li class="nav_more"><span>更多</span>
                               <ul class="down_kuang">
                                    <li>
                                    <span>方式：</span>
                                    <p>
                                    <strong onclick="findMap(this,4)">不限</strong>
                                     <#list styleMap?keys as key>
                                    <span value="${key}" onclick="findMap(this,4)">${styleMap[key]}</span>
                                    </#list>
                                    </p>
                                    </li>
                                    <li>
                                    <span>付款：</span>
                                    <p>
                                    <strong onclick="findMap(this,5)">不限</strong>
                                     <#list payTypes?keys as key>
                                    <span value="${key}" onclick="findMap(this,5)">${payTypes[key]}</span>
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
                                <div class="list_room"><a href="##">列表找房</a></div>
                            </h3>
                            <ul>
                                <li id="region"></li>
                                <li id="area"></li>
                            </ul>
                        </div>
                        <p class="resourcenunber">可找范围内有<span id="countId"></span>个房源</p>
                        <div class="s_main">
                            <ul class="s_sort">
                                <li class="s_sort_active" onclick="findMap(this,10)">默认排序</li>
                                <li class="s_up" onclick="findMap(this,7)">价格</li>
                                <li class="s_down" onclick="findMap(this,8)">面积</li>
                                <li class="s_up" onclick="findMap(this,9)">时间</li>
                            </ul>
                            <div class="s_list_kuang">
                                <ul class="s_list" id="ulId">
                                    
                                </ul> 
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
    </body>
     <script type="text/javascript">
    //页数
    var pageNum = 1;
    //区域集合
     var xy=[];
     //商圈集合
     var xy1=[];
     //小区集合
     var xy2=[];
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
	 var status=1;
	 //户型
	 var room=null;
	 //面积
	 var buildArea=null;
	 //价格
     var price=null;
     //付款方式
     var payType=null;
     //租房类型
     var rentType=null;
     //小区名称
     var dicName="";
     //排序
     var ordering=null;
     //清空搜索条件
     function clean(){
       room=null;
       price=null;
       buildArea=null;
       payType=null;
       rentType=null;
       dicName="";
       ordering=null;
        types=1;
        destory();
	    baidu(xy);
	    huixian(types);
     }
     
     //获取区域，商圈，小区集合
     function destory(){
        $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentRegion",
	            async:false,
	            data:{room:room,
	                  buildArea:buildArea,
	                   price:price,
	                   keywords:dicName,
	                   payType:payType,
	                   rentType:rentType,
	                   ordering:ordering
	                   },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                xy=data.data.AreaLatLngGroupList;
	                xy1=data.data.areaList;
	                xy2=data.data.quartersList;
	              }
	            }
	      });
     }
    destory();
    function findDicName(){
       dicName=$("#dicNameId").val();
       if(dicName!=""){
           destory();
            var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length; i++) {
            	map.removeOverlay(allOverlay[i]);
            }
			 var allOverlay = map.getOverlays();
            for (var i = 0; i < allOverlay.length; i++) {
            	map.removeOverlay(allOverlay[i]);
            }
            u = map.getZoom();
            if(u==null || u==11 || u==12||u==13){
               var point = new BMap.Point(116.404, 39.915);
		    	if(u==12){
		    		map.centerAndZoom(point, 12);                       //设立中心点和地图级别，就是初始化地图
		    	}else if(u==13){
		    		map.centerAndZoom(point, 13);                       //设立中心点和地图级别，就是初始化地图
		    	}
		    	baidu(xy);
		    	types=1;
		    	huixian(types);
            }else if(u==14 || u==15){
                var point = new BMap.Point(116.404, 39.915);
		    	if(u==14){
		    		map.centerAndZoom(point, 14);                       //设立中心点和地图级别，就是初始化地图
		    	}else if(u==15){
		    		map.centerAndZoom(point, 15);                       //设立中心点和地图级别，就是初始化地图
		    	}
		    	baidu(xy1);
		    	types=2;
		    	huixian(types);
            }else{	            
				    		for (var i in xy2) {
				 			   pt = new BMap.Point(xy2[i].lng , xy2[i].lat);
				 			   name=xy2[i].name;
				 			   count=xy2[i].count;
				 			   id=xy2[i].id;
						    	var lng = xy2[i].lng;
						    	var lat = xy2[i].lat; 
						    	var point = new BMap.Point(lng,lat);
						    	map.centerAndZoom(point, 16);                       //设立中心点和地图级别，就是初始化地图
						    	
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
		        		    types=3;
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
						        var txt = name, mouseoverTxt = txt + " " + count + "套" ;
						            
						        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

						        map.addOverlay(myCompOverlay);
				    		}
				    		types=3
				    		xiaoqu(types);
				  }  		
       }
    }
    
    function findMap(obj,num){
      if(num==1){
         price=$(obj).val();
         if(price==0){
           price=null;
         }
      }else if(num==2){
          room=$(obj).val();
          if(room==0){
           room=null;
         }
      }else if(num==3){
        buildArea=$(obj).val();
        if(buildArea==0){
           buildArea=null;
         }
      }else if(num==4){
         rentType=$(obj).attr("value");
         if(rentType==0){
           rentType=null;
         }
      }else if(num==5){
         payType=$(obj).attr("value");
         if(payType==0){
           payType=null;
         }
      }else if(num==7){
         ordering=3;
      }else if(num==8){
         ordering=7
      }else if(num==9){
         ordering=1
      }else if(num==10){
        ordering=null;
      }
      destory();
      var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++) {
		      map.removeOverlay(allOverlay[i]);
		}	
	 if(u==null || u<14){
	   types=1;
	    baidu(xy);
	    huixian(types);
	 }else if(u==14 || u==15){
	    types=2;
	    baidu(xy1);
	    huixian(types);
	 }else{
	    for (var i in xy2) {
				 			   pt = new BMap.Point(xy2[i].lng , xy2[i].lat);
				 			   name=xy2[i].name;
				 			   count=xy2[i].count;
				 			   id=xy2[i].id;
						    	var lng = this.point.lng;
						    	var lat = this.point.lat; 
						    	var point = new BMap.Point(lng,lat);
						    	map.centerAndZoom(point, 16);                       //设立中心点和地图级别，就是初始化地图
						    	
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
		        		   			 types=3;
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
						        var txt = name, mouseoverTxt = txt + " " + count + "套" ;
						            
						        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

						        map.addOverlay(myCompOverlay);
				    		}
				    		types=3
				    		xiaoqu(types);
	 }	            
      
    }
    
    var map = new BMap.Map("allmap");    //创建地图容器
    var point = new BMap.Point(116.404, 39.915);        //创建一个点
    map.centerAndZoom(point, 12);                       //设立中心点和地图级别，就是初始化地图
    map.enableScrollWheelZoom(true); 
    map.setMinZoom(11);
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT , type: BMAP_NAVIGATION_CONTROL_SMALL}); 
    map.addControl(top_left_control);        
	map.addControl(top_right_navigation);  
	
	var xy3=[
		{'x':116.21929,'y':39.913385,'name':'八角游乐园','count':180,'id':15},
		{'x':116.242574,'y':39.913053,'name':'八宝山','count':350,'id':16},
		{'x':116.259534,'y':39.913939,'name':'玉泉路','count':450,'id':17},
		{'x':116.280231,'y':39.91405,'name':'五棵松','count':134,'id':19},
		{'x':116.301646	,'y':39.91405,'name':'万寿路','count':35,'id':20},
		{'x':116.3176,'y':39.913939,'name':'公主坟','count':325,'id':21},
		{'x':116.328524	,'y':39.913496,'name':'军事博物馆','count':653,'id':22},
		{'x':116.344118,'y':39.913219,'name':'木樨地','count':754,'id':23},
		{'x':116.359282	,'y':39.913053,'name':'南礼士路','count':160,'id':24},
		{'x':116.363126	,'y':39.913081,'name':'复兴门','count':1150,'id':25},
		{'x':116.383428,'y':39.913385,'name':'西单','count':130,'id':26},
		{'x':116.397801,'y':39.913607,'name':'天安门西','count':60,'id':27},
		{'x':116.41821,'y':39.914603,'name':'天安门东','count':321,'id':28}
	];
	if(status==2){
    		isWheelZoom=false;
    		subway();
	}else{
		baidu(xy);
	    huixian(types);
	}
	
	function baidu(xy){
		for (var i in xy) {
			   pt = new BMap.Point(xy[i].lng , xy[i].lat);
			   name=xy[i].name;
			   count=xy[i].count;
			   id=xy[i].id;
			   var myLabel = new BMap.Label(("<div class='p_kuang'><p class='pclassname' value="+name+">"+name+"</p><p class='pclassname'>"+count+"套</p><input type='hidden' value="+id+"></div>"),     //为lable填写内容
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
						backgroundColor : 'rgba(57,172,106,0.9)',
				        cursor:"pointer",
				        borderRadius:"50%",
				        color:"#fff"
				        
				    });
				   myLabel.setTitle(count);
				   map.addOverlay(myLabel); 
				 //鼠标移上事件（显示区域）
				   myLabel.addEventListener("mouseover", function(){ 
					   this.setStyle({                                   //给label设置样式，任意的CSS都是可以的
				    		backgroundColor : '#FF8C00',
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
				    		backgroundColor : 'rgba(57,172,106,0.9)',
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
				    	$("#titleId").show();
				    	if(u<14){
				    		var allOverlay = map.getOverlays();
				            for (var i = 0; i < allOverlay.length; i++) {
				            	map.removeOverlay(allOverlay[i]);
				            }
					    	var lng = this.point.lng;
					    	var lat = this.point.lat; 
					    	var point = new BMap.Point(lng,lat);
					    	map.centerAndZoom(point, 14);                       //设立中心点和地图级别，就是初始化地图
					    	baidu(xy1);
					    	types=2;
					    	huixian(types);
					    	var label = $(this.content).get(0);
	     			        var areas=$(label).find("p").get(0).innerHTML;
					    	$("#region").text(areas)
				    	}else{
				    		var allOverlay = map.getOverlays();
				            for (var i = 0; i < allOverlay.length; i++) {
				            	map.removeOverlay(allOverlay[i]);
				            }
				    		for (var i in xy2) {
				 			   pt = new BMap.Point(xy2[i].lng , xy2[i].lat);
				 			   name=xy2[i].name;
				 			   count=xy2[i].count;
				 			   id=xy2[i].id;
						    	var lng = this.point.lng;
						    	var lat = this.point.lat; 
						    	var point = new BMap.Point(lng,lat);
						    	map.centerAndZoom(point, 16);                       //设立中心点和地图级别，就是初始化地图
						    	
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
		        		    types=3;
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
						        var txt = name, mouseoverTxt = txt + " " + count + "套" ;
						            
						        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

						        map.addOverlay(myCompOverlay);
				    		}
				    		types=3
				    		xiaoqu(types);
				    		var label = $(this.content).get(0);
	     			        var area=$(label).find("p").get(0).innerHTML;
				    		$("#area").text(area)
				    	}
				    });  
		}
   }
   
   function huixian(types){
	   var counts=null; 
		 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	    var vers = map.getOverlays();
	     array="";
	    for(var i=0;i<vers.length;i++){
		       var po=vers[i].point;
		       var count=vers[i].getTitle(); 
		       if(po==null || po=='' || po==undefined){
		    	   
		       }else if(count==''|| count==undefined){
		    	   
		       }else{
		    	 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
		    	 if(bs.containsPoint(pts)==true){
	     		   counts +=parseInt(count);
	     			var label = $(vers[i].content).get(0);
	     			var ids=$(label).find("input").val();
	     			array +=ids+",";
	     		}
		       }
	    }
	    array=array.substring(0,array.length-1);
	    arrayDrectory(array,types);
	    if(counts==null){
	     counts=0;
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
		       if(po==null || po=='' || po==undefined){
		    	   
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
	    }
	    $("#countId").text(counts);
}

function arrayDrectory(array,types){	
   if(array==''){
     array=0;
   }
   pageNum=1;
   if(types==1){
      $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentArrayDrectory",
	            async:false,
	            data:{areaCode3s:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  buildArea:buildArea,
	                  price:price,
	                  keywords:dicName,
	                  payType:payType,
	                  rentType:rentType,
	                  ordering:ordering},
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
        			str +='<li><img src="'+recordList[i].imageUrl+'"><div class="l_text"><h4>'+recordList[i].dicName+'</h4><div>'+recordList[i].dicName+'<p><span>'+recordList[i].price+'</span>元/月</p></div><ul><li>'+recordList[i].buildArea+'m</li><li>'+recordList[i].room+'室'+recordList[i].hall+'厅</li></ul></div></li>';
  					}
  					$("#ulId").find("li").remove();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }else if(types==2){
     $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentArrayDrectory",
	            async:false,
	            data:{tradingAreaIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  buildArea:buildArea,
	                  price:price,
	                  keywords:dicName,
	                  payType:payType,
	                  rentType:rentType,
	                  ordering:ordering},
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
        			str +='<li><img src="'+recordList[i].imageUrl+'"><div class="l_text"><h4>'+recordList[i].dicName+'</h4><div>'+recordList[i].dicName+'<p><span>'+recordList[i].price+'</span>元/月</p></div><ul><li>'+recordList[i].buildArea+'m</li><li>'+recordList[i].room+'室'+recordList[i].hall+'厅</li></ul></div></li>';
  					}
  					$("#ulId").find("li").remove();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }else if(types==3){
        $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentArrayDrectory",
	            async:false,
	            data:{dicIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  buildArea:buildArea,
	                  price:price,
	                  keywords:dicName,
	                  payType:payType,
	                  rentType:rentType,
	                  ordering:ordering
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	                var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
        			str +='<li><img src="'+recordList[i].imageUrl+'"><div class="l_text"><h4>'+recordList[i].dicName+'</h4><div>'+recordList[i].dicName+'<p><span>'+recordList[i].price+'</span>元/月</p></div><ul><li>'+recordList[i].buildArea+'m</li><li>'+recordList[i].room+'室'+recordList[i].hall+'厅</li></ul></div></li>';
  					}
  					$("#ulId").find("li").remove();
  				    $("#ulId").append(str);
	              }
	            }
	      });
   }
    
}

function xiaoqu(types){
    var counts=null; 
	 // 获取经纬度范围参数
	 var bs = map.getBounds();   //获取可视区域
	 var vers = map.getOverlays();
	  array="";
	 for(var i=0;i<vers.length;i++){
			var count=vers[i].count;
			var id=vers[i].id;
			if(count==null || count=='' || count==undefined){
				
			}else if(count==''|| count==undefined){
				
			}else{
				 var pts=new BMap.Point(vers[i]._point.lng , vers[i]._point.lat);
   	    	 if(bs.containsPoint(pts)==true){
        		   counts +=parseInt(count);
        			array+=id+",";
        		}
			}
		}
		array=array.substring(0,array.length-1);
		arrayDrectory(array,types);
		if(counts==null){
	     counts=0;
	    }
       $("#countId").text(counts);
}
   
	
	function subway(){
		var allOverlay = map.getOverlays();
        for (var i = 0; i < allOverlay.length; i++) {
        	map.removeOverlay(allOverlay[i]);
        }
        for (var i in xy3) {
			   pt = new BMap.Point(xy3[i].x , xy3[i].y);
			   name=xy3[i].name;
			   count=xy3[i].count;
			   id=xy3[i].id;
		    	var lng = this.point.lng;
		    	var lat = this.point.lat; 
		    	var point = new BMap.Point(lng,lat);
		    	map.centerAndZoom(point, 12);                       //设立中心点和地图级别，就是初始化地图
		    	
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
		          div.style.lineHeight = "25px";
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
						    	 pt = new BMap.Point(xy3[j].x , xy3[j].y);
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
						    	 myLabel.setTitle(name);//为label添加鼠标提示
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
		        var txt = name, mouseoverTxt = txt + " " + count + "套" ;
		            
		        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

		        map.addOverlay(myCompOverlay);
 		}
	}

//鼠标拉到列表页底部加载更多数据
$("#ulId").scroll(function(){
     	   var $this =$(this),
           viewH =$(this).height(),//可见高度
          contentH =$(this).get(0).scrollHeight,//内容高度
          scrollTop =$(this).scrollTop();//滚动高度
          if(u==null||u==11||u==12||u==13){
            types=1;
          }else if(u==14||u==15){
            types=2
          }else{
            types=3
          }
        if(scrollTop/(contentH -viewH)>=0.95){ //到达底部100px时,加载新内容
           pageNum++;
           if(types==1){
              $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentArrayDrectory",
	            async:false,
	            data:{areaCode3s:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  buildArea:buildArea,
	                  price:price,
	                  keywords:dicName,
	                  payType:payType,
	                  rentType:rentType,
	                  ordering:ordering
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
        			str +='<li><img src="'+recordList[i].imageUrl+'"><div class="l_text"><h4>'+recordList[i].dicName+'</h4><div>'+recordList[i].dicName+'<p><span>'+recordList[i].price+'</span>元/月</p></div><ul><li>'+recordList[i].buildArea+'m</li><li>'+recordList[i].room+'室'+recordList[i].hall+'厅</li></ul></div></li>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }else if(types==2){
             $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentArrayDrectory",
	            async:false,
	            data:{tradingAreaIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  buildArea:buildArea,
	                  price:price,
	                  keywords:dicName,
	                  payType:payType,
	                  rentType:rentType,
	                  ordering:ordering
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
        			str +='<li><img src="'+recordList[i].imageUrl+'"><div class="l_text"><h4>'+recordList[i].dicName+'</h4><div>'+recordList[i].dicName+'<p><span>'+recordList[i].price+'</span>元/月</p></div><ul><li>'+recordList[i].buildArea+'m</li><li>'+recordList[i].room+'室'+recordList[i].hall+'厅</li></ul></div></li>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }else if(types==3){
             $.ajax({
	            type: "POST",
	            url: _ctx+"/map/rentArrayDrectory",
	            async:false,
	            data:{dicIds:array,
	                  pageNum:pageNum,
	                  types:types,
	                  room:room,
	                  buildArea:buildArea,
	                  price:price,
	                  keywords:dicName,
	                  payType:payType,
	                  rentType:rentType,
	                  ordering:ordering
	                  },
	            dataType: "json",
	            success: function(data){
	              if(data.status==1){
	               var recordList=data.data.recordList;
	                var str="";
   					for(var i=0;i<recordList.length;i++){
        			str +='<li><img src="'+recordList[i].imageUrl+'"><div class="l_text"><h4>'+recordList[i].dicName+'</h4><div>'+recordList[i].dicName+'<p><span>'+recordList[i].price+'</span>元/月</p></div><ul><li>'+recordList[i].buildArea+'m</li><li>'+recordList[i].room+'室'+recordList[i].hall+'厅</li></ul></div></li>';
  					}
  				    $("#ulId").append(str);
	              }
	            }
	       });
           }
        }
     	});     
</script>
<script type="text/javascript">
//鼠标缩放监听事件
map.addEventListener("zoomend", function(type){
	if (!type.lD) {
		 u = map.getZoom(); // 定义地图缩放等级的变量
		if(isWheelZoom==true && status==1){
		 $("#titleId").hide();
		if(u==11||u==12 || u==13){
				var allOverlay = map.getOverlays();
	         for (var i = 0; i < allOverlay.length; i++) {
	         	map.removeOverlay(allOverlay[i]);
	         }
		    	var point = new BMap.Point(116.404, 39.915);
		    	if(u==12){
		    		map.centerAndZoom(point, 12);                       //设立中心点和地图级别，就是初始化地图
		    	}else if(u==13){
		    		map.centerAndZoom(point, 13);                       //设立中心点和地图级别，就是初始化地图
		    	}
		    	baidu(xy);
		    	types=1;
		    	huixian(types);
		}else if(u==14 || u==15){
				var allOverlay = map.getOverlays();
	         for (var i = 0; i < allOverlay.length; i++) {
	         	map.removeOverlay(allOverlay[i]);
	         }
		    	var point = new BMap.Point(116.404, 39.915);
		    	if(u==14){
		    		map.centerAndZoom(point, 14);                       //设立中心点和地图级别，就是初始化地图
		    	}else if(u==15){
		    		map.centerAndZoom(point, 15);                       //设立中心点和地图级别，就是初始化地图
		    	}
		    	baidu(xy1);
		    	types=2;
		    	huixian(types);
		}else if(u==16){
			var allOverlay = map.getOverlays();
	         for (var i = 0; i < allOverlay.length; i++) {
	         	map.removeOverlay(allOverlay[i]);
	         }
	 		for (var i in xy2) {
				   pt = new BMap.Point(xy2[i].lng , xy2[i].lat);
				   name=xy2[i].name;
				   count=xy2[i].count;
				   id=xy2[i].id;
			    	var point = new BMap.Point(116.404, 39.915);
			    		map.centerAndZoom(point, 16);                       //设立中心点和地图级别，就是初始化地图	
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

			          map.getPanes().labelPane.appendChild(div);
			          
			          return div;
			        }
			        ComplexCustomOverlay.prototype.draw = function(){
			          var map = this._map;
			          var pixel = map.pointToOverlayPixel(this._point);
			          this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
			          this._div.style.top  = pixel.y - 30 + "px";
			        }
			        var txt = name, mouseoverTxt = txt + " " + count + "套" ;
			            
			        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

			        map.addOverlay(myCompOverlay);
	 		    }
	 		    types=3;
	 		    xiaoqu(types);
			 }
		}else if(isWheelZoom==true && status==2){
		   $("#titleId").hide();
			if(u==16){
				var allOverlay = map.getOverlays();
	            for (var i = 0; i < allOverlay.length; i++) {
	            	map.removeOverlay(allOverlay[i]);
	            }
				 for (var i in xy3) {
					    pt = new BMap.Point(xy3[i].x , xy3[i].y);
				    	var name=xy3[i].name;
				    	map.centerAndZoom(pt, 16);
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
				    	 myLabel.setTitle(name);//为label添加鼠标提示
						  map.addOverlay(myLabel); 
				 }
			}else if(u<16){
				var allOverlay = map.getOverlays();
		        for (var i = 0; i < allOverlay.length; i++) {
		        	map.removeOverlay(allOverlay[i]);
		        }
				for (var i in xy3) {
					   pt = new BMap.Point(xy3[i].x , xy3[i].y);
					   name=xy3[i].name;
					   count=xy3[i].count;
					   id=xy3[i].id;
				    	if(u==11){
				    		map.centerAndZoom(pt, 11);                       //设立中心点和地图级别，就是初始化地图
				    	}else if(u==12){
				    		map.centerAndZoom(pt, 12);                       //设立中心点和地图级别，就是初始化地图
				    	}else if(u==13){
				    		map.centerAndZoom(pt, 13);                       //设立中心点和地图级别，就是初始化地图
				    	}else if(u==14){
				    		map.centerAndZoom(pt, 14);                       //设立中心点和地图级别，就是初始化地图
				    	}else if(u==15){
				    		map.centerAndZoom(pt, 15);                       //设立中心点和地图级别，就是初始化地图
				    	}
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
				          div.style.lineHeight = "25px";
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
							    	 pt = new BMap.Point(xy3[j].x , xy3[j].y);
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
							    	 myLabel.setTitle(name);//为label添加鼠标提示
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
				        var txt = name, mouseoverTxt = txt + " " + count + "套" ;
				            
				        var myCompOverlay = new ComplexCustomOverlay(pt, name,mouseoverTxt,id,count);

				        map.addOverlay(myCompOverlay);
				        
		 		}
			}
		}else {
			isWheelZoom=true;
		}
    }
});


//拖拽结束事件
map.addEventListener("dragend", function(e){ 
	  var counts=null; 
	 // 获取经纬度范围参数
	   var bs = map.getBounds();   //获取可视区域
	   var vers = map.getOverlays();
	  u = map.getZoom(); // 定义地图缩放等级的变量
	 if(u==14||u==15){
	    array="";
		for(var i=0;i<vers.length;i++){
 	       var po=vers[i].point;
 	       var count=vers[i].getTitle(); 
 	       if(po==null || po=='' || po==undefined){
 	    	   
 	       }else if(count==''|| count==undefined){
 	    	   
 	       }else{
 	    	 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
 	    	 if(bs.containsPoint(pts)==true){
      		   counts +=parseInt(count);
      			var label = $(vers[i].content).get(0);
      			var ids=$(label).find("input").val();
      			array+=ids+",";
      		 }
 	       }
 	   }
 	   if(counts==null){
	     counts=0;
	    }
 	   $("#countId").text(counts);
 	   array=array.substring(0,array.length-1);
 	   types=2;
 	    arrayDrectory(array,types);
		
	 }else if(u==11||u==12 || u==13){
	       array="";
		for(var i=0;i<vers.length;i++){
 	       var po=vers[i].point;
 	       var count=vers[i].getTitle(); 
 	       if(po==null || po=='' || po==undefined){
 	    	   
 	       }else if(count==''|| count==undefined){
 	    	   
 	       }else{
 	    	 var pts=new BMap.Point(vers[i].point.lng , vers[i].point.lat);
 	    	 if(bs.containsPoint(pts)==true){
      		   counts +=parseInt(count);
      			var label = $(vers[i].content).get(0);
      			var ids=$(label).find("input").val();
      			array+=ids+",";
      		 }
 	       }
 	   }
 	   if(counts==null){
	     counts=0;
	    }
 	   $("#countId").text(counts);
 	   array=array.substring(0,array.length-1);
 	   types=1;
 	    arrayDrectory(array,types);
	 }else{
	  array="";
		for(var i=0;i<vers.length;i++){
			var count=vers[i].count;
			var id=vers[i].id;
			if(count==null || count=='' || count==undefined){
				
			}else if(count==''|| count==undefined){
				
			}else{
				 var pts=new BMap.Point(vers[i]._point.lng , vers[i]._point.lat);
     	    	 if(bs.containsPoint(pts)==true){
          		   counts +=parseInt(count);
          			array+=id+",";
          		}
			}
		}
		if(counts==null){
	     counts=0;
	    }
		 $("#countId").text(counts);
		types=3;
		array=array.substring(0,array.length-1);
		arrayDrectory(array,types);
	 
	 }
	 
	  $("#titleId").hide(); 
});  

   
    
</script>
</html>