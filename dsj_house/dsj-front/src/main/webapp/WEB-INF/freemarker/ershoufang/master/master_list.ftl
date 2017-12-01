<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta charset="UTF-8">
	<title>北京二手房-北京二手房价格-大搜家</title>
	<meta content="北京二手房,北京二手房价格,北京二手房出售,热门二手房" name="keywords">
	<meta content="大搜家二手房频道，为购房者提供北京最全的二手房信息，通过地图找房、地铁找房等快速找房工具，让您轻松找到自己的家。" name="description">
	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
</head>
<body>
 <!-- 经纪人前端logo搜索框 -->
   <div class="BHLogo">
     <div class="BHLogoLeft">
       <a href="${ctx}/"><h1>大搜家</h1></a>
       <span>二手房</span>
     </div>
     <div class="BHLogoRight">
       <!-- 输入框搜索按钮 -->
         <div class="TextSeek">
           <form class="form-inline"  action="${ctx}/ershoufang" >
             <div class="form-group">
               <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
               <div class="input-group">
                 <input type="text" class="form-control" autocomplete="off" value="${keywords}" name="k"  id="inputseek" placeholder="请输入小区名称或地址">
               </div>
                <ul class="grabble_kuang">
                  
                 </ul>
             </div>
               <button type="submit" class="seek">
               <!-- <span class="glyphicon glyphicon-search"></span> -->
             </button>
           </form>
         </div>
       <!-- 地图找房 -->
         <div class="MapFindHouse">
           <a href="${ctx}/map/oldMap">地图找房</a>
         </div>
     </div>
   </div>
 <!-- 进程 -->
   <div class="PGress"> 
     <ul>
       <li><a href="${ctx}/">大搜家首页</a>
       <div class="progressTriangle">
         <div> 
         </div>
       </div>
       </li>
       <li><a href="${ctx}/ershoufang">二手房列表</a>
       </li>
      
     </ul>
   </div>
 <!-- 前端主体 -->
 	<div class="dsj-main">
 		<!-- 表单选项 -->
 		<form class="dsj_form">
 			<p class="dsj_form_line">
 				<span>位置:</span>
 				<span class="dsj_form_dropdown active" id="search_eare"><a href="${ctx}/ershoufang" >区域找房</a></span>
 				<span class="dsj_form_dropdown"  id="search_subway" ><a href="${ctx}/ershoufang/line" >地铁找房</a></span>
 			</p>
 			<div id="areaId" class="dsj_form_line small_gap dsj_tab2" <#if line?? > style="display:none" </#if> >
 				<span style="display:none">位置:</span>
 				<span <#if areaCode3?? > <#else> class="active"</#if> ><a href="${ctx}/ershoufang">不限</a></span>
 				<#list areaFirstLists as po>
 				<span <#if po.areaCode ==areaCode3> class="active" </#if> > 
 				
	 				<#if params??>
	 					<a href="${ctx}/ershoufang/d${po.areaCode}/${params}"> ${po.name} </a>
	 				<#else>
	 				<a href="${ctx}/ershoufang/d${po.areaCode}"> ${po.name} </a>
	 				</#if>
 				</span>
 				</#list>
 			</div>
 			
 			<div id="subwayLineId"  class="dsj_form_line small_gap dsj_tab2" <#if line==null > style="display:none" </#if> >
 				<span <#if subwayLineId??> <#else> class="active"  </#if>><a href="${ctx}/ershoufang/line">不限</a></span>
 				<#list subwayLineList as po>
 				
 					<span <#if po.id ==subwayLineId> class="active" </#if> >
 				 		<#if params??>
	 						<a href="${ctx}/ershoufang/line/s${po.id}/${params}"> ${po.name} </a>
	 				<#else>
	 				<a href="${ctx}/ershoufang/line/s${po.id}"> ${po.name} </a>
	 				</#if>
 				 </span>
 				</#list>
 			</div>
 			

 			<div class="tab-content  dsj_tab  dsj_tab2">
 			  <div id="tradingAreaId" role="tabpanel" class="tab-pane  <#if  subwayLineId == null && areaCode3??> active </#if>" >
  				<#if tradeAreaMap?? >
 			  	<span <#if tradingAreaId??> <#else>class="active"  </#if> >全部</span>
 			  	<#list tradeAreaMap?keys as key>
				  		<span class="dsj_index">${key}</span>
				  		<#list tradeAreaMap[key] as po>
							<span <#if po.areaCode ==tradingAreaId></#if>>
						
				 				<#if params??>
				 					<a href="${ctx}/ershoufang/d${areaCode3}_${po.areaCode}/${params}"> ${po.name} </a>
				 				<#else>
				 					<a href="${ctx}/ershoufang/d${areaCode3}_${po.areaCode}"> ${po.name} </a>
				 				</#if>
							
							</span>
 						</#list>
 				</#list>
 				</#if>
 			  </div>
 			  <div id="subwayId" role="tabpanel" class="tab-pane <#if line?? && subwayLineId??> active </#if>" >
 			  	<span <#if subwayId??> <#else>class="active"  </#if>>不限</span>
 			  	<#list subwayList as po>
 				<span <#if po.id ==subwayId> class="active" </#if> > <a href="${ctx}/ershoufang/line/s${subwayLineId}_${po.id}"> ${po.name} </a></span>
 				</#list>
 			  </div>
 			</div>
 			<p class="dsj_form_line">
 				<span>价格:</span>
 				<span <#if params?contains("pr")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_pr}${url_kw}">不限</a></span>
 				<#list priceMap?keys as key>
 					<span <#if params?index_of("pr"+key)!=-1> class="active" </#if> ><a href="${ctx}/ershoufang/${url_pr}pr${key}${url_kw}">  ${priceMap[key]}</a></span>
 				</#list>
 				<span class="double_input_container pull-right">
  				<span class="double_input">
  					<input type="" value="${pmn}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="pmn" id="pmn">
  					<span><span>-</span></span>
  					<input type="" value="${pmx}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="pmx" id="pmx">
  					<span><span>万</span></span>
  				</span>
  				<a href="javascript:void(0);" onclick="priceToUrl()" class="color_blue">确定</a>
 				</span>
 			</p>
 			<p class="dsj_form_line">
 				<span>面积:</span>
 				<span <#if params?contains("ar")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_ar}${url_kw}">不限</a></span>
 					<#list areaMap?keys as key>
 					<span <#if params?index_of("ar"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_ar}ar${key}${url_kw}">  ${areaMap[key]}</a></span>
 				</#list>
 				<span class="double_input_container pull-right">
  				<span class="double_input">
  					<input type="" name="amn" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="${amn}"  id="amn">
  					<span><span>-</span></span>
  					<input type="" name="amx" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="${amx}"  id="amx">
  					<span><span>平</span></span>
  				</span>
  				<a href="javascript:void(0);" onclick="areaToUrl()" class="color_blue">确定</a>
 				</span>
 			</p>
 			<p class="dsj_form_line ">
 				<span>户型:</span>
 				<span <#if params?contains("rm")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_rm}${url_kw}">不限</a></span>
 					<#list roomMap?keys as key>
 					<span <#if params?index_of("rm"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_rm}rm${key}${url_kw}">  ${roomMap[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option" <#if  !isShow??||isShow==false> style="display: none;" </#if> >
 				<span>来源:</span>
 				<span <#if params?contains("ct")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_ct}${url_kw}">不限</a></span>
 				<#list companyTypeMap?keys as key>
 					<span <#if params?index_of("ct"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_ct}ct${key}${url_kw}">  ${companyTypeMap[key]}</a></span>
 				</#list>
 			</p>
 			<div class="dsj_form_line more_option" <#if  !isShow??||isShow==false> style="display: none;" </#if>>
 				<span>类型:</span>
 				<p class="mokunag">
 					<span <#if params?contains("wy")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_wy}${url_kw}">不限</a></span>
 					<#list buildType?keys as key>
 						<span <#if params?index_of("wy"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_wy}wy${key}${url_kw}">  ${buildType[key]}</a></span>
 					</#list>
 				</p>
 			</div>
 		
 			<div class="dsj_form_line more_option" <#if  !isShow??||isShow==false> style="display: none;" </#if>>
 				<span>朝向:</span>
 				<p class="mokunag">
 				<span <#if params?contains("ori")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_ori}${url_kw}">不限</a></span>
 				<#list orientations?keys as key>
 					<span <#if params?index_of("ori"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_ori}ori${key}${url_kw}">  ${orientations[key]}</a></span>
 				</#list>
 				</p>
 			</div>
 			<p class="dsj_form_line more_option" <#if  !isShow??||isShow==false> style="display: none;" </#if>>
 				<span>装修:</span>
 				<span <#if params?contains("rt")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_rt}${url_kw}">不限</a></span>
 				<#list renvation?keys as key>
 					<span <#if params?index_of("rt"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_rt}rt${key}${url_kw}">  ${renvation[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option" <#if  !isShow??||isShow==false> style="display: none;" </#if>>
 				<span>楼层:</span>
 				<span <#if params?contains("ft")<=0> class="active"  </#if> ><a href="${ctx}/ershoufang/${url_tf}${url_kw}">不限</a></span>
 				<#list floorTypeMap?keys as key>
 					<span <#if params?index_of("ft"+key)!=-1> class="active" </#if>><a href="${ctx}/ershoufang/${url_tf}ft${key}${url_kw}">  ${floorTypeMap[key]}</a></span>
 				</#list>
 			</p>
 			<div class="dsj_more_option text-center" >
	  				<div <#if  !isShow??||isShow==false> class="hide" </#if>>收起选项<span class="shouqijiant"  aria-hidden="true"></span></div>
	  				<div <#if  isShow??&&isShow==true> class="hide" </#if>>展开选项<span  aria-hidden="true"></span></div>
	  			</div>
 			<hr class="bottom_hr">
 			
 			<#if conditionMap??  && conditionMap?size gt 0  >	
 			<p class="dsj_form_line dsj_condition">
 				<span>条件:</span>
 				<#--<span>普通住宅<span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></span>-->
 				
 				<#if conditionMap??>	
 				<#list conditionMap?keys as key> 
 					<#if key!=null>	
 					<span>${key}<a href="${ctx}/ershoufang/${conditionMap[key]}"><span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></a> </span>
 					</#if>
 				</#list>
 				</#if>
 				<span class="dsj_clear"><span class="glyphicon glyphicon-trash" aria-hidden="true">
 				
 				</span><a href="${ctx}/ershoufang" style="color: #999;">清空选项</a></span>
 			</p>
 			</#if>
           <p class="dsj_form_line dsj_sort">
           	<span>排序:</span>
           	<span <#if !ordering?? || ordering==1> class="active" </#if> ><a href="${ctx}/ershoufang/${url_orderding}od1">默认排序</a></span>
           	
           	<span  <#if ordering?? && ordering==4> class="active" </#if> class="icon_hover hover_span">
           		<a href="${ctx}/ershoufang/${url_orderding}od4">价格由低到高</a>
          	 	<span  <#if ordering?? && ordering==4> class="active dsj_arrow glyphicon glyphicon-arrow-up"
          	 	 <#else> class=" dsj_arrow glyphicon glyphicon-arrow-up" </#if>
          	 	 aria-hidden="true"  style="margin-right: 0;">
          	 	 </span>
           	</span>
           	

           	
           	<!-- <#if ordering?? && ordering==4 >
           	<span class="icon_hover hover_span"><a href="${ctx}/ershoufang/${url_orderding}od3">价格由高到底</a>
          	 	<span class="active dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
           	</span>
           	</#if>-->
           	 
           <!--	<span class="hover_span"><a href="${ctx}/ershoufang/${url_orderding}od6">时间由新到旧</a>
           	<span class="icon_hover_time" <#if ordering?? && ordering==6> class="active dsj_arrow glyphicon glyphicon-arrow-down" <#else> class="dsj_arrow glyphicon glyphicon-arrow-down" </#if> aria-hidden="true"></span></span>
           -->
          	
          	<!--  	<#if ordering?? && ordering==6>
           	<span class="hover_span"><a href="${ctx}/ershoufang/${url_orderding}od5">时间由旧到新</a><span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
           	</#if> -->
           	
           	<span  <#if ordering?? && ordering==7> class="active" </#if> class="hover_span"><a href="${ctx}/ershoufang/${url_orderding}od7">面积由大到小</a>
          	 	<span icon_hover_time  <#if ordering?? && ordering==7> class="active dsj_arrow glyphicon glyphicon-arrow-down"
          	 	 <#else> class=" dsj_arrow glyphicon glyphicon-arrow-down" </#if>
          	 	 aria-hidden="true"></span>
           	</span>
           	
 				<span class="total pull-right">
 					为您找到<span class="bluefont">${page.totalCount}</span>个房源
 				</span>
           </p>
 		</form>

       <!-- 主体内容 -->
     	<div class="newhouse_content">
     	
     	  	<#if page.recordList?? && page.recordList?size gt 0>
     		<#list page.recordList as listVo>
     		<div class="dsj_row uncentain clearfix"  >
			<div class="dsj_row_img">
				<#if listVo.imageUrl??>
				<a href="${ctx }/oldmaster/${listVo.id}.html" target="_blank"> <img class="img-responsive"  src="${listVo.imageUrl}?x-oss-process=image/resize,m_lfit,h_200,w_280" ></a>
				<#else>
				<a href="${ctx }/oldmaster/${listVo.id}.html" target="_blank"><img class="img-responsive"   src="${ctx}/static/front/img/default/liebiao.jpg" ></a>
				</#if>
			</div>
			
			<div class="dsj_row_content" >
						
				<h4 style="cursor:pointer" onclick="toDetail(${listVo.id})" >${listVo.title}</h4>
				
				
				<p class="clearfix dsj_detail">
					<span>${listVo.buildArea}m<sup>2</sup></span>
					<span>${listVo.room}室${listVo.hall}厅</span>
					<span>${listVo.floorTypeName}层（共${listVo.floorNum}层）</span>
					
				</p>
				<p class="clearfix shanjia">
					<span class="shan">${listVo.areaName3}-${listVo.dicName}</span>
					<span class="pull-right">
						<span class="rent-price">${listVo.price}</span>
						<span class="rent-unit">万</span>
					</span>
				</p>
				<p class="clearfix">
					<span class="li_img_kuang">
						
						<#if listVo.ico??>
							<#list listVo.ico?split(",") as item>
							<img style="width:20px;height:20px" src="${item}">
							</#list>
						</#if>
						
						<#if listVo.companyTypes??>
							<#list listVo.companyTypes?split(",") as item> 
								<#if item==1>
									<img src="${ctx}/static/front/img/company/list/dsj.png">
								</#if>
								<#if item==2>
									<img src="${ctx}/static/front/img/company/list/lianjia.png">
								</#if>
								<#if item==3>
						
									<img src="${ctx}/static/front/img/company/list/maitian.png">
								</#if>
								<#if item==4>
									<img src="${ctx}/static/front/img/company/list/zydc.png">
								</#if>
								<#if item==5>
									<img src="${ctx}/static/front/img/company/list/waiwojia.png">
								</#if>
							</#list>
						</#if>
						
						
						
						<!-- <#if listVo.companyTypeNames??>
								<#list listVo.companyTypeNames?split(",") as str>
									${str}&nbsp;
								</#list>
						  </#if> -->
					</span>
					<span class="single-price pull-right">
						单价：<span>${listVo.unitPrice}</span>元/平
					</span>
				</p>
				<p class="dsj_tag labelling ">
					
					<#if listVo.featuresName??>	
 						<#list listVo.featuresName?split(",") as featureName>
 								<#if featureName?? && featureName!=''>	
								<span class="color_${featureName_index}">
								${featureName}
								</span>
								</#if>
 						</#list>
 					</#if>
 					
				</p>
				</div>
     		</div>
     		
			</#list>
			<#else>
					<div class="isfangyuan">
						没有找到符合条件的房源
					</div>
			</#if>
     		</div>
     		
     		 <#include "tags/pagination.ftl">
     			
      	</div>
	</div>	
</div>
  <script type="text/javascript">
    	$(function(argument) {
    		$(".dsj_more_option").click(function(argument) {
    			$(".more_option").toggle();
    			$(this).find("div").toggleClass("hide");
    		})
    	})
    </script>
<script>
$(function(){
	
	if (window.location.pathname.indexOf("line") > -1) {
		 $("#search_eare").removeClass("active");
		 $("#search_subway").addClass("active");
	 } else {
		 $("#search_subway").removeClass("active");
		 $("#search_eare").addClass("active");
	 }
	
	seekevent();
	 $(".grabble_kuang").hide();
	 
	 isPcOrOther('http://wap.dasoujia.com/mobile/views/dsj-usedhouse/ershou-list.html');
})


function priceToUrl(){
	var urlPr="${url_pr}";
	
	var pmn=$("#pmn").val();
	var pmx=$("#pmx").val();;
	if(pmn!=null&& pmn!= ''&& pmx!=null&&pmx!=null){
		window.location.href="${ctx}/ershoufang/${urlPr}pmn"+pmn+"pmx"+pmx;
	}
	
}

function areaToUrl(){
	var urla="${url_a}";
	
	var amn=$("#amn").val();
	var amx=$("#amx").val();;
	if(amn!=null&& amn!= ''&& amx!=null&&amx!=null){
		window.location.href="${ctx}/ershoufang/${urla}amn"+amn+"amx"+amx;
	}
}

function changeSubway(obj){
	$("#areaId").hide();
	$("#subwayLineId").show();
	$(obj).addClass("active");
	$(obj).siblings().removeClass("active");
	$("#subwayId").addClass("active");
	$("#tradingAreaId").removeClass("active");
}

function changeArea(obj){
	$("#subwayLineId").hide();
	$("#areaId").show();

	$(obj).addClass("active");
	$(obj).siblings().removeClass("active");
	
	$("#tradingAreaId").addClass("active");
	$("#subwayId").removeClass("active");
	
}



function toDetail(id){
	var url='${ctx}'+"/oldmaster"+"/"+id;
	window.location.href=url+".html";
}
</script>
<script src="${ctx }/static/back/oldHouse/ershoufang_search.js"></script>
</body>
  
</html>

