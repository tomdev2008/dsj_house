
	var map = new BMap.Map("mapBox");//初始化地图
   	var mPoint = new BMap.Point($("#accuracy").val(),$("#dimension").val());
    map.centerAndZoom(mPoint, 16); //地图中心
    map.setCenter(map.pixelToPoint(new BMap.Pixel(810,210)));
    map.enableScrollWheelZoom();  //启用滚轮缩放
    var LabelOptions = {
        offset:new BMap.Size(0,-29),
        position:mPoint,
        enableMassClear:false//调用map.clearOverlays是否清楚，默认ture
    }
    var label = new BMap.Label($("#newHouseName").val(),LabelOptions);//第一个参数为label显示内容
    label.setStyle({color: "#fff",borderRadius: "15px", top: "-29px", fontSize : "12px",  backgroundColor: "rgba(0,0,0,.8)",border:"none",padding: "5px 10px",fontSize: "16px", position: "relative"});
//    label.setContent("nihao");
    //label.setTitle("大搜家");//鼠标移到label后显示内容
    FMarker = new BMap.Marker(mPoint,{enableMassClear:false});//调用map.clearOverlays是否清楚，默认ture
    FMarker.setLabel(label);
    map.addOverlay(FMarker);
    $(function(){
  	  map.addOverlay(label);//添加中心点在地图中的显示
    })
  
    //serachOtions，搜索选项
    var options={
        renderOptions:{
            //map: map, 设置map属性即自动在地图上添加搜索返回的标注
            selectFirstResult:false,//是否显示第一个标注
            //panel:'results' 渲染的div的id
            autoViewport:true//设置视觉窗口是否自动调整
        },
        //pageCapacity:100,//设置返回结果每页容量，上边panel未设置，可以不设置此属性
        onSearchComplete: function(results){      //查询后的回调函数
        	document.getElementById("results").innerHTML="";
            markerList = [];
            if (local.getStatus() == BMAP_STATUS_SUCCESS){      
                    // 判断状态是否正确 
                    //查询参数关键词如果为数组，则返回的结果也是数组
                var s = []; 
                 var x = 1;
                for(var j=0;j<results.length;j++){
                    for (var i = 0; i < results[j].getCurrentNumPois(); i ++){  //getCurrentNumPois()获取返回标点个数
                        var str =results[j].getPoi(i).point.lat+","+results[j].getPoi(i).point.lng+",'"+results[j].getPoi(i).title+"','"+results[j].getPoi(i).address+"'";
                        //构造参数，后变点击和onmouseover用
                        s.push('<li onclick="Click('+str+')" onmouseover="Click('+str+')"><span class="position_icon">'+x+'</span><div><h4>'+results[j].getPoi(i).title+'<span>'+parseInt(map.getDistance(mPoint, results[j].getPoi(i).point))+'米</span></h4><p>'+ results[j].getPoi(i).address+'</p></div></li>'); 
                       	x++;
                        var pic = ""
                        //根据关键词，设置页面标注图标
                        if(results[j].keyword=="地铁站"){
                            pic=_ctx+"/static/front/img/index/ditie.png";
                        }else if(results[j].keyword=="公交站"){
                        	 pic=_ctx+"/static/front/img/index/gongjiao.png";
                        }else if(results[j].keyword=="火车站"){
                        	 pic=_ctx+"/static/front/img/index/ditie.png";
                        }else if(results[j].keyword=="医疗"){
                        	 pic=_ctx+"/static/front/img/index/yaodian.png";
                        }else if(results[j].keyword=="购物"){
                        	 pic=_ctx+"/static/front/img/index/chaoshi.png";
                        }else if(results[j].keyword=="生活"){
                        	 pic=_ctx+"/static/front/img/index/kafeiguan.png";
                        }else if(results[j].keyword=="娱乐"){
                        	 pic=_ctx+"/static/front/img/index/youleyuan.png";
                        }else if(results[j].keyword=="科教"){
                        	 pic=_ctx+"/static/front/img/index/youeryuan.png";
                        }
					    
                        //页面显示标注
                        addMarker(results[j].getPoi(i).point,pic,0,results[j].getPoi(i));   
                    }
                }
                document.getElementById("results").innerHTML = s.join("<br>"); //在页面显示搜索结果    
            }else{
            	 document.getElementById("results").innerHTML = "<p style='text-align: center;margin-top: 126px;color: #999;'>很抱歉，该配套下无相关内容，请查看其他配套</p>" ;
            }     
        } 
    }
    var local = new BMap.LocalSearch(map,options);  //创建搜索
    var initParams = ['地铁站'];    //搜索参数，第一次进入页面的搜索
    local.searchNearby(initParams,mPoint,1500);//执行搜索
    //页面点击关键词后搜索
    function change(data){
    	data = data.replace("交通","地铁站");
    	if(data=="交通"||data=="地铁站"||data=="公交站"||data=="火车站"){
        	$("#twoMapTab").show();
        }else{
        	$("#twoMapTab").hide();
        }
    	var params = data.split(',');
        map.clearOverlays();//清除所有
        local.searchNearby(params,mPoint,1500);   
    }
    var markerList=[];
    //自定义标注
    function addMarker(point, pic,index,data){  // 创建图标对象   
        var myIcon = new BMap.Icon(pic, new BMap.Size(27, 80), {    
            // 指定定位位置。   
            // 当标注显示在地图上时，其所指向的地理位置距离图标左上    
            // 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
            // 图标中央下端的尖角位置。    
            offset: new BMap.Size(0, 0),    
            // 设置图片偏移。   
            // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
            // 需要指定大图的偏移位置，此做法与css sprites技术类似。    
            imageOffset: new BMap.Size(0, 0)   // 设置图片偏移    
        });      
        // 创建标注对象并添加到地图   
        var marker = new BMap.Marker(point, {icon: myIcon,title:data.title});  //title是鼠标移到标点处显示内容
       
        marker.addEventListener("click", function(res){
            //data是地图上标注的信息
            Click(data);
            marker.openInfoWindow(new BMap.InfoWindow(data.address,{
                title:data.title,
                enableAutoPan:true,
                offset:new BMap.Size(0,-38)
            }));
        });
        markerList.push(marker);//保存已添加标注
       
        map.addOverlay(marker);    
    }
    //右侧页面，鼠标点击或者onmouseover时，地图标注处展开信息页面    
    function Click(lat,lng,title,address){
        var data={
            point:{
                lat:lat,
                lng:lng
            },
            title:title,
            address:address
        }
        //判断鼠标点击或者onmouseover的标注
        for(var i=0;i<markerList.length;i++){
            var p = markerList[i].getPosition();
            if(p.lng==data.point.lng&&p.lat==data.point.lat){
                markerList[i].openInfoWindow(new BMap.InfoWindow(data.address,{
                title:data.title,
                enableAutoPan:true,
                }));
            }
        }    
    };
	
