<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta charset="UTF-8">
	<title>大搜家</title>
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
       <h1>大搜家</h1>
       <span>租房</span>
     </div>
     <div class="BHLogoRight">
       <!-- 输入框搜索按钮 -->
         <div class="TextSeek">
           <form class="form-inline"  action="${ctx}/rentHouse" >
             <div class="form-group">
               <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
               <div class="input-group">
                 <input type="text" class="form-control" value="${keywords}" name="k"  id="inputseek" placeholder="请输入地铁线附件的房子开始找房">
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
           地图找房
         </div>
     </div>
   </div>
 <!-- 进程 -->
   <div class="PGress"> 
     <ul>
       <li><a href="##">大搜家首页</a>
       <div class="progressTriangle">
         <div> 
         </div>
       </div>
       </li>
       <li><a href="##">租房列表</a>
       </li>
      
     </ul>
   </div>
 <!-- 前端主体 -->
 	<div class="dsj-main">
 		<!-- 表单选项 -->
 		<form class="dsj_form">
 			<p class="dsj_form_line">
 				<span>位置:</span>
 				<span class="dsj_form_dropdown active" ><a href="${ctx}/rentHouse" >区域找房</a></span>
 				<span class="dsj_form_dropdown"   ><a href="${ctx}/rentHouse/line" >地铁找房</a></span>
 			</p>
 			<p id="areaId" class="dsj_form_line small_gap" <#if line?? > style="display:none" </#if> >
 				<span style="visibility: hidden;">位置:</span>
 				<span class="active"><a href="${ctx}/rentHouse">不限</a></span>
 				<#list areaFirstLists as po>
 				<span <#if po.areaCode ==areaCode3> class="active" </#if> > <a href="${ctx}/rentHouse/d${po.areaCode}"> ${po.name} </a></span>
 				</#list>
 			</p>
 			
 			<p id="subwayLineId"  class="dsj_form_line small_gap" <#if line==null > style="display:none" </#if> >
 				<span style="visibility: hidden;">地铁:</span>
 				<span class="active"><a href="${ctx}/rentHouse/line">不限</span>
 				<#list subwayLineList as po>
 				<span <#if po.id ==subwayLineId> class="active" </#if> > <a href="${ctx}/rentHouse/line/s${po.id}"> ${po.name} </a></span>
 				</#list>
 			</p>
 			

 			<div class="tab-content dsj_tab">
 			  <div id="tradingAreaId" role="tabpanel" class="tab-pane  <#if  subwayLineId == null && areaCode3??> active </#if>" >
  				<div class="arrow" style="left:13%;"></div>
 			  	<span class="active">全部</span>
 			  	<span class="dsj_index">A</span>
 			  		<#list tradeAreaList as po>
 				<span <#if po.areaCode ==tradingAreaId> class="active" </#if>><a href="${ctx}/rentHouse/d${areaCode3}_${po.areaCode}"> ${po.name} </a></span>
 				</#list>
 			  </div>
 			  <div id="subwayId" role="tabpanel" class="tab-pane <#if line?? && subwayLineId??> active </#if>" >
 			  	<div class="arrow" style="left:15%;"></div>
 			  	<span class="active">不限</span>
 			  	<#list subwayList as po>
 				<span <#if po.id ==subwayId> class="active" </#if> > <a href="${ctx}/rentHouse/line/s${subwayLineId}_${po.id}"> ${po.name} </a></span>
 				</#list>
 			  </div>
 			</div>
 			<p class="dsj_form_line">
 				<span>价格:</span>
 				<span <#if params?index_of("pr")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_pr}${url_kw}">不限</a></span>
 				<#list priceMap?keys as key>
 					<span <#if params?index_of("pr"+key)!=-1> class="active" </#if> ><a href="${ctx}/rentHouse/${url_pr}pr${key}${url_kw}">  ${priceMap[key]}</a></span>
 				</#list>
 				<span class="double_input_container pull-right">
  				<span class="double_input">
  					<input type="" value="${pmn}" name="pmn" id="pmn">
  					<span><span>~</span></span>
  					<input type="" value="${pmx}" name="pmx" id="pmx">
  					<span><span>元</span></span>
  				</span>
  				<a href="javascript:void(0);" onclick="priceToUrl()">确定</a>
 				</span>
 			</p>
 			<p class="dsj_form_line">
 				<span>面积:</span>
 				<span <#if params?index_of("a")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_a}${url_kw}">不限</a></span>
 					<#list areaMap?keys as key>
 					<span <#if params?index_of("a"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_a}a${key}${url_kw}">  ${areaMap[key]}</a></span>
 				</#list>
 				<span class="double_input_container pull-right">
  				<span class="double_input">
  					<input type="" name="amn"  value="${amn}"  id="amn">
  					<span><span>~</span></span>
  					<input type="" name="amx" value="${amx}"  id="amx">
  					<span><span>平</span></span>
  				</span>
  				<a href="javascript:void(0);" onclick="areaToUrl()">确定</a>
 				</span>
 			</p>
 			<p class="dsj_form_line ">
 				<span>户型:</span>
 				<span <#if params?index_of("rm")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_rm}">不限</a></span>
 					<#list roomMap?keys as key>
 					<span <#if params?index_of("rm"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_rm}rm${key}">  ${roomMap[key]}</a></span>
 				</#list>
 			</p>
 			<!--
 			<p class="dsj_form_line more_option">
 				<span>来源:</span>
 				<span <#if params?index_of("ct")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_ct}">不限</a></span>
 				<#list companyTypeMap?keys as key>
 					<span <#if params?index_of("ct"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_wy}ct${key}">  ${companyTypeMap[key]}</a></span>
 				</#list>
 			</p>-->
 			<p class="dsj_form_line more_option">
 				<span>类型:</span>
 				<span <#if params?index_of("rtp")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_rtp}">不限</a></span>
 				<#list rentTypeMap?keys as key>
 					<span <#if params?index_of("rtp"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_rtp}rtp${key}">  ${rentTypeMap[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option">
 				<span>房型:</span>
 				<span <#if params?index_of("wy")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_wy}">不限</a></span>
 				<#list wyType?keys as key>
 					<span <#if params?index_of("wy"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_wy}wy${key}">  ${wyType[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option">
 				<span>付款:</span>
 				<span <#if params?index_of("pt")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_pt}">不限</a></span>
 				<#list payType?keys as key>
 					<span <#if params?index_of("pt"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_pt}pt${key}">  ${payType[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option">
 				<span>朝向:</span>
 				<span <#if params?index_of("ori")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_o}">不限</a></span>
 				<#list orientations?keys as key>
 					<span <#if params?index_of("ori"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_o}ori${key}">  ${orientations[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option">
 				<span>装修:</span>
 				<span <#if params?index_of("rt")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_rt}">不限</a></span>
 				<#list renvation?keys as key>
 					<span <#if params?index_of("rt"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_rt}rt${key}">  ${renvation[key]}</a></span>
 				</#list>
 			</p>
 			<p class="dsj_form_line more_option">
 				<span>楼层:</span>
 				<span <#if params?index_of("ft")<=0> class="active"  </#if> ><a href="${ctx}/rentHouse/${url_tf}">不限</a></span>
 				<#list floorTypeMap?keys as key>
 					<span <#if params?index_of("ft"+key)!=-1> class="active" </#if>><a href="${ctx}/rentHouse/${url_ft}ft${key}">  ${floorTypeMap[key]}</a></span>
 				</#list>
 			</p>
 			<div class="dsj_more_option text-center">
 				<div class="">收起选项<span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span></div>
 				<div class="hide">展开选项<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></div>
 			</div>
 			<hr class="bottom_hr">
 			<p class="dsj_form_line dsj_condition">
 				<span>条件:</span>
 				<#--<span>普通住宅<span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></span>-->
 				
 				<#if conditionMap??>	
 				<#list conditionMap?keys as key> 
 					<#if key!=null>	
 					<span>${key}<a href="${ctx}/rentHouse/${conditionMap[key]}"><span class="dsj_remove glyphicon glyphicon-remove" aria-hidden="true"></span></a></span>
 					</#if>
 				</#list>
 				</#if>
 				<span class="dsj_clear"><span class="glyphicon glyphicon-trash" aria-hidden="true">
 				</span><a href="${ctx}/rentHouse">清空选项</a></span>
 			</p>
           <p class="dsj_form_line dsj_sort">
           	<span>排序:</span>
           	<span><a href="${ctx}/rentHouse/${url_orderding}">默认排序</a></span>
           	<span><a href="${ctx}/rentHouse/${url_orderding}od4">价格</a><span class="<#if orderding==4>active</#if> dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span></span>
           	<span><a href="${ctx}/rentHouse/${url_orderding}od3">价格</a><span class="<#if orderding==3>active</#if> dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
           	<span><a href="${ctx}/rentHouse/${url_orderding}od2">时间</a><span class="<#if orderding==2>active</#if> dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span></span>
           	<span><a href="${ctx}/rentHouse/${url_orderding}od1">时间</a><span class="<#if orderding==1>active</#if> dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
			<span class="total pull-right">为您找到<span class="bluefont">${page.totalCount}</span>个楼盘</span>
           </p>
 		</form>

       <!-- 主体内容 -->
     	<div class="newhouse_content">
     		<#list page.recordList as rentVo>
     			<div class="dsj_row uncentain clearfix">
					<div class="dsj_row_img">
						<a href="${ctx}/rentHouseDetail/detail?id=${rentVo.id}"><img class="img-responsive" src="${rentVo.imageUrl}"></a>
					</div>
					<div class="dsj_row_content">
						<h4>
						    ${rentVo.title}
						</h4>
						<p class="clearfix dsj_detail">
							<span>${rentVo.buildArea}m<sup>2</sup></span>
							<span>${rentVo.room}室${rentVo.hall}厅</span>
							<span>
								<#list floorTypeMap?keys as key>
									<#if rentVo.floorType == key>
										${floorTypeMap[key]}
									</#if>
				 				</#list>层（共${rentVo.totalFloors}层）
							</span>
							<span>
								<#if rentVo.rentType == 1>整租</#if>
								<#if rentVo.rentType == 2>合租</#if>
							</span>
							<#list payType?keys as key>
								<#if rentVo.payType == key>
									<span>${payType[key]}</span>
								</#if>
			 				</#list>
						</p>
						<p class="clearfix">
							<span>${rentVo.areaName3}-${rentVo.tradingAreaName}</span>
							<span class="pull-right">
								<span class="rent-price">${rentVo.price}</span>
								<span class="rent-unit">元/月</span>
							</span>
						</p>
						<div class="dsj_contect rent-house clearfix">
							<div class="sale-person">
								<img class="img-responsive" src="${rentVo.agentAvatar}">
							</div>
							<p class="pull-left">
								<span class="sale_name">${rentVo.agentName}</span>
								<span class="sale_company">${rentVo.agentCompany}</span>
								<br>
								<span class="sale_phone">${rentVo.phonel}</span>
							</p>
						</div>
					</div>
	      		</div>
			</#list>
     		</div>
     		
     		 <#include "tags/pagination.ftl">
     			
      	</div>
	</div>	
</div>
<script>
$(function(){
	seekevent();
	 $(".grabble_kuang").hide();
})


function priceToUrl(){
	var urlPr="${url_pr}";
	
	var pmn=$("#pmn").val();
	var pmx=$("#pmx").val();;
	if(pmn!=null&& pmn!= ''&& pmx!=null&&pmx!=null){
		window.location.href="${ctx}/rentHouse/${urlPr}pmn"+pmn+"pmx"+pmx;
	}
	
}

function areaToUrl(){
	var urla="${url_a}";
	
	var amn=$("#amn").val();
	var amx=$("#amx").val();;
	if(amn!=null&& amn!= ''&& amx!=null&&amx!=null){
		window.location.href="${ctx}/rentHouse/${urla}amn"+amn+"amx"+amx;
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

//输入框
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
        	$.ajax({
	  			type:"post",
	  			async:true,
	  			data:{"name":seekValue},
	  			dataType:"json",
	  			url:_ctx+"/keyword/search",
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
        $('#inputseek').val(function() {
            return seekValue;
            });
        $(".grabble_kuang").hide();
    })
};
</script>
  </body>
  
</html>
