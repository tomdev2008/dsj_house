<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="${newHouse.name},${newHouse.name}楼盘详情,楼盘价格,楼盘详情" name="keywords">
	<meta content="${newHouse.name}楼盘售楼处电话(400-898-6868转${newHouse.pcMobile})，楼盘价格、楼盘地址、楼盘户型图、楼盘交通、楼盘周边配套等楼盘详情信息，尽在大搜家楼盘详情页。" name="description">
	<meta charset="UTF-8">
	<title>${newHouse.name }-${newHouse.name }楼盘详情-大搜家</title>
	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/album.css">
	<script src="${ctx }/static/back/newHouse/newhouse.js"></script>
	<script src="${ctx }/static/back/newHouse/newhouse_search.js"></script>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
 <body>
	  <!--吸顶导航  -->
	  
        <div id="nav_ceiling">
            <!-- 主体导航 -->
             <nav class="newhouse_nav">
                <a href="#dsj" class="active">楼盘首页</a>
                <a href="#house_dynamic">楼盘动态</a>
                <a href="#house_type">楼盘户型</a>
                <a href="#house_message" >推荐经纪人</a>
                <a href="#house_photo">楼盘相册</a>
                <a href="#user_remark">楼盘点评</a>
                <a href="#house_details">楼盘详情</a>
                <a href="#zhouBianPeiTao">周边配套</a>
            </nav>
        </div>
	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	         <h1>
	         	大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	        <span>新房</span>
	      </div>
	      <div class="BHLogoRight">
	        <!-- 输入框搜索按钮 -->
	          <div class="TextSeek">
	            <form class="form-inline" onSubmit="return false;">
	              <div class="form-group">
	                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
	                <div class="input-group">
	                  <input type="text" class="form-control" value="${kw}" name="k" id="inputseek" placeholder="请输入楼盘名称或地址" autocomplete="off">
	                </div>
	                <ul class="grabble_kuang">
                  
                	 </ul>
	              </div>
	              <button type="button" class="seek" onclick="searchResult()">
	              </button>
	            </form>
	          </div>
	          <div class="MapFindHouse">
	            <a href="${ctx }/map/newMap">地图找房</a>
	          </div>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress index_detail_PGress"> 
	      <ul>
	        <li><a href="${ctx}/">大搜家首页</a>
	        <div class="progressTriangle">
	        </div>
	        </li>
	        <li><a href="${ctx}/front/newHouse/list">新房列表</a>
	         <div class="progressTriangle">
	        </div>
	        </li>
	        <li><a href="javascript:void(0);">新房详情</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 新房banner -->
	  	<div id="dsj_bannder_kuang">
	  		<div  class="dsj_kuang_opacity"></div>
	  		<div class="dsj-main rent-details secondhand-details">
			  	<!-- 主体banner -->
			  	<div class="dsj-main-banner">
			  		<div class="banner_left">
		  			<div id="carousel" class="dsj-main-banner newhouse-carousel carousel slide dsj-carousel" data-ride="carousel" data-interval="false">
		  			  <!-- Wrapper for slides -->
		  			  <div class="dsj-carousel__inner pointer">
		  			    <div class="carousel-inner" role="listbox">
			  			      <div class="item active">
			  			        <img src="" alt="...">
			  			      </div>
		  			    </div>

		  			    <!-- Controls -->
		  			    <a class="swiper-button-next swiper-button-white" href="#carousel" role="button" data-slide="next">
		  			    </a>
		  			    <a class="swiper-button-prev swiper-button-white" href="#carousel" role="button" data-slide="prev">
		  			    </a>
		  			  </div>


		  			  <div class="preview-container">
		  			    <div class="preview-indicators left">
			  			      <div data-target="#carousel" data-slide-to="" class="preview-pic active">
			  			        <img class="indicateimg" src="" alt="...">
			  			      </div>
		  			    </div>
		  			    <div class="swiper-button-white preview-prev"></div>
		  			    <div class="swiper-button-white preview-next"></div>
		  			  </div>

		  			</div>
			  		</div>
			  		<div class="banner_right">
			  		<input type="hidden" id="newHouseName" value="${newHouse.name }"> 
		  				<!-- 主体标题 -->
					  	<h2 id="hover_box" class="hover_box">
					  		${newHouse.name }
					  		<input type="hidden" id="newHouseId" value="${newHouse.id }">
					  		<input type="hidden" id="accuracy" value="${newHouse.accuracy}">
					  		<input type="hidden" id="dimension" value="${newHouse.dimension }">
					  	</h2>
					  	<div class="box_hover">
				  		</div>
					  	 <#if newHouse.indexPrice??&&newHouse.indexPrice!=""> 
		      				<p>${newHouse.priceName }<strong>${newHouse.indexPrice }</strong>${newHouse.pricedw }</p>
		      			<#else>
		      				<p><strong>售价待定</strong></p>
		      			</#if>
		      			<div class="rig_cont">
						  		<#if followId??&&followId!=0>
						  			<div class="attention_button" onmouseover="$(this).html('取消关注')" onmouseout="$(this).html('已关注')" onclick="cancelHouse()">
							  			已关注
							  		</div>
							  	<#else>
								  <div class="attention_button" onclick="attentionHouse()">
							  		+  关注房源
							  		</div>
							  	</#if>
		      				<div class="labelling">
		      					<p>
		      					 <#if newHouse.dicTraitList??> 
		      					 	<#list newHouse.dicTraitList as dic><span class="color_${dic_index}">${dic }</span></#list>
		      					</#if>
		      					</p>	
		      				</div>
		      				<div class="bannder_right_content">
			      				<ul>
			      					<li>
			      					    <span>更新时间</span>
			      					    <p><#if newHouse.authTime??>${newHouse.authTime?string("yyyy年MM月dd日")}<#else>暂无</#if></p>
			      					</li>
			      					<li>
			      						<span>售卖状态</span>
			      					    <p><#if newHouse.status==1>待售<#elseif newHouse.status==2>在售<#else>售完</#if></p>
			      					</li>
			      					<li>
			      						<span>楼盘户型</span>
			      					    <p class="huxing_a"><#if newHouse.houseTypeStrPc??&&newHouse.houseTypeStrPc!=''>${newHouse.houseTypeStrPc }<#else>暂无</#if></p>
			      					</li>
			      					<li class="omit_kuang">
			      						<span>楼盘地址</span>
			      						<#if newHouse.address??>
				      						<p class="omitCharacter">[${newHouse.areaLevalThreeName }-${newHouse.tradingAreaName }] ${newHouse.address}</p>
				      						<div class="reverlAll">
			      					    		[${newHouse.areaLevalThreeName }-${newHouse.tradingAreaName }] ${newHouse.address}
			      					    	</div>
				      					    <a class="mapfind" href="${ctx }/front/newHouse/map?id=${newHouse.id}"></a>
			      						<#else>
			      							暂无
			      						</#if>
			      					    
			      					</li>
			      					<li>
			      						<span>最新开盘</span>
			      					    <p >
			      					    <#if newHouse.newOpenTime??&&newHouse.newOpenTime!="">
			      					    	${newHouse.newOpenTime}
			      					    <#else>
				      					    <#if newHouse.openDate??&&newHouse.openDate!="">
				      					  		  ${newHouse.openDate}
				      					    <#else>
				      					    	暂无
				      					    </#if>
			      					    </#if></p>
			      					</li>
			      					<li>
			      						<span>建筑类型</span>
			      					    <p><#if newHouse.achTypeName??>${newHouse.achTypeName}<#else>暂无</#if></p>
			      					</li>
			      					<li class="sellhouse_phone">
			      						<span>售楼处电话</span>
			      					    <p id="phone12_top"><#if newHouse.pcMobile??><strong>400-898-6868</strong><span>转</span><strong>${newHouse.pcMobile}</strong><#else>暂无</#if></p>
			      					</li>
			      				</ul>
						  	</div>
						  	<#if newHouse.agentVoList??>
				    		<#list newHouse.agentVoList as agent> 
					    		<#if agent.isDuty=1>
						    		<div class="bannder_contact newhouse_contact">
						    			<div class="img_vip">
								  			<a target="_blank" href="${ctx }/front/agentIndex/info?userId=${agent.userId }"><img src="${agent.avatarUrl }"></a>
								  			<span class="_vip"></span>
							  			</div>
							  			<div class="contact_text">
							  				<div class="contact_name">
							  					<h4><a target="_blank" href="${ctx }/front/agentIndex/info?userId=${agent.userId }">${agent.name }</a></h4>
							  					<div class="online-consult" onclick="openChatWindow('${agent.username }', '${agent.name }', '400-898-6868转${agent.mobile }', '${agent.avatarUrl }')">
							  						在线咨询
							  					</div>
							  				</div>
							  				<div class="con_right">
							  						<div class="badge_icon"><img src="${agent.smallIcon}"><span>${agent.gradeName }</span></div>
							  						<#if agent.mobile??&&agent.mobile!="">
													<p class="phone_12 owner_phone">400-898-6868 转 ${agent.mobile }</p>
													</#if>
											</div>
							  			</div>
					  				</div>	
				  				</#if>
						  	</#list>
				    	</#if>
		      			</div> 
			      	</div>	
			  	</div>	
			</div>
	  	</div>
	  <!-- 新房主体 -->
	  	<div class="dsj-main">
	        <!-- 主体导航 -->
		  	<nav id="home" class="newhouse_nav">
	            <a class="active" href="#dsj">楼盘首页
	              <span class="activeTriangle"  style="border-top:10px solid #2775e9;">
	              </span>
	            </a>
	            <a href="#house_dynamic">楼盘动态
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#house_type">楼盘户型
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#house_message">推荐经纪人
	            <!-- 三角形样式 -->
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#house_photo">楼盘相册
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#user_remark">楼盘点评
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#house_details">楼盘详情
	              <span class="activeTriangle">
	              </span>
	            </a>
	            <a href="#zhouBianPeiTao">周边配套
	              <span class="activeTriangle">
	              </span>
	            </a>
	        </nav>
	        
	        <!-- 楼盘动态 -->
	        <div class="house_dynamic" id="house_dynamic">
	        	<p>楼盘动态</p>
	        	<div>
	        		<#if newHouse.newsPo??>
		        		<div class="content_h3">
		        		<h3 class="dyname" onclick="newsDetail(${newHouse.newsPo.id},${newHouse.id});">
		        		${newHouse.newsPo.title }
		        		</h3>&nbsp;&nbsp;
		        		<#if newHouse.newsPo.type == 1>
		        			<span class="fabucs">大搜家官方发布</span>
		        		<#elseif newHouse.newsPo.type == 2>
		        			<span calss="fabucs"><a href="${ctx }/front/agentIndex/info?userId=${newHouse.newsPo.createUser}"><b>${newHouse.newsPo.userName}</b>发布</a></span>
		        		<#elseif newHouse.newsPo.type == 3>
		        			<span calss="fabucs">开发商发布</span>
		        		</#if>
		        		<time>${newHouse.newsPo.createTime?string("yyyy-MM-dd")}</time>
		        		<div class="more"><a href="${ctx }/front/newHouse/house_dynamic?id=${newHouse.id}">查看全部</a>
						<div class="progressTriangle">
				        </div>
					</div>
	        		</div>
					
	        		</h3>
	        		<a id="miaoshu" class="a_color6"  onclick="newsDetail(${newHouse.newsPo.id},${newHouse.id});">${newHouse.newsPo.contentst}</a>
	        		<#else>
	        			没有数据
	        		</#if>
	        	</div>
	        </div>
			<!-- 楼盘户型 -->
		    <div class="house_type" id="house_type">
		    	<div class="house_type_tittle">
			    	<h2>楼盘户型</h2>
			        <ul class="titlt_nav1 houseType">
		        		<li class="navActive" room="">全部户型(${newHouse.houseTypeTotalCount })</li>
		        		<#if newHouse.houseTypeList??>
		        				<#list newHouse.houseTypeList as houseType> 
		      						<li room="${houseType.room }">${houseType.roomName }(${houseType.count })</li>
		      					</#list>
		        		</#if>
		        	</ul>
		        	<div class="more"><a  href="${ctx }/front/newHouse/house_type?id=${newHouse.id}">查看全部</a>
							<div class="progressTriangle">
					        </div>
					</div>
		    	</div>
		    	<div class="house_type_Img clearfix" id="houseType">
      				
	      		</div>
			</div>
			<!-- 推荐经纪人 -->
      		<div class="house_message clearfix" id="house_message">
		    	<div class="house_type_tittle">
			    	<h2>推荐经纪人</h2>
			    	<div class="more"><a href="${ctx }/front/newHouse/newHouse_agent?id=${newHouse.id}">查看全部</a>
							<div class="progressTriangle">
					        </div>
					</div>
		    	</div>
		    	<div class="house_type_Img">
		    	
		    	<#if newHouse.agentVoList??>
		    		<#list newHouse.agentVoList as agent> 
			    		<div>
					  		<div class="bannder_contact  bannder_contactmax   contact_hover">
					  			<div class="img_vip img_vip_noborder">
						  			<a target="_blank" href="${ctx }/front/agentIndex/info?userId=${agent.userId }"><img src="${agent.avatarUrl }"></a>
						  			<#if agent.isDuty=1>
							  			<span class="_vip"></span>
						  			</#if>
						  		</div>
								<div class="contact_text contact_text_hover">
									<div class="contact_name margin_10">
										<h4><a target="_blank" href="${ctx }/front/agentIndex/info?userId=${agent.userId }">${agent.name }</a></h4><div class="badge_icon">
										<img style="width: 16px;height: 20px;" src="${agent.smallIcon}"><span>${agent.gradeName}</span></div>
										
									</div>
							  		<div class="on_p">
				  						<div class="online-consult" onclick="openChatWindow('${agent.username }', '${agent.name }', '400-898-6868转${agent.mobile}', '${agent.avatarUrl }')">
							  				在线咨询
							  			</div> 
							  			<#if agent.mobile??&&agent.mobile!="">
										<p class="phone_12">400-898-6868 转 ${agent.mobile }</p>
										</#if>
				  					</div>
									<div class="li_label">
									 <#if agent.featureArray??> 
			      					 	<#list agent.featureArray as label> 
				      						<span>${label }</span>
				      					</#list>
				      				</#if>
									</div>
									
								</div>
							</div>
					  	</div>
				  	</#list>
		    	</#if>
			</div>
			</div>
			<!-- 楼盘相册 -->
		    <div class="house_type house_photo clearfix" id="house_photo">
		    	<div class="house_type_tittle">
			    	<h2>楼盘相册</h2>
			        <ul class="titlt_nav" id="housePictureCount">
		        		<li class="navActive" id="allPicture" onclick="setPicture(1)">全部相册(0)</li>
		        	</ul>
		        	<div class="more"><a href="${ctx }/front/newHouse/house_picture?id=${newHouse.id}">查看全部</a>
							<div class="progressTriangle">
					        </div>
					</div>
		    	</div>
		    	<div class="house_type_Img" id="housePicture">
	      		</div>
			</div>
			<!-- 用户点评 -->
			<div class="house_type user_remark clearfix" id="user_remark">
				<div class="house_type_tittle">
			    	<h2>楼盘点评</h2>
			        <ul class="titlt_nav comment">
		        		<li class="navActive" onclick="showComment(3);">经纪人点评</li>
		        		<li onclick="showComment(4);">用户点评</li>
		        	</ul>
		        	<div class="more">
		        		<a href="${ctx }/front/newHouse/house_comment?id=${newHouse.id}&type=3">查看全部</a>
		        		<a style="display: none;" href="${ctx }/front/newHouse/house_comment?id=${newHouse.id}&type=4">查看全部</a>
							<div class="progressTriangle">
					        </div>
					</div>
		    	</div>
		    </div>
		   	<!-- 楼盘详情 -->
			<div id="house_details" class="house_type house_details clearfix">
				<div class="house_type_tittle">
			    	<h2>楼盘详情</h2>
		        	<div class="more"><a  href="${ctx }/front/newHouse/detail?id=${newHouse.id}">查看全部</a>
							<div class="progressTriangle">
					        </div>
					</div>
		    	</div>
			    <!-- 主体内容 -->
		      	<div class="newhouse_content">
		      		<!-- 模块一 -->
		      		<div class="newhouse_content_list">
		      			<div class="list_content">
		      				<ul>
		      					<li>
		      					    <span>楼盘名称：</span>
		      					    <p>${newHouse.name }</p>
		      					</li>
		      					<li>
		      						<span>楼盘特点：</span>
		      					    <p>
		      					    	 <#if newHouse.dicTraitList??> 
				      					 	<#list newHouse.dicTraitList as dic> 
					      						<span class="color_${dic_index}">${dic }</span>
					      					</#list>
					      				<#else>
		      					  			暂无
				      					</#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>参考单价：</span>
		      						<p>
		      						<#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.referencePriceMin??> 
			      					  		  		<#if wyMsg.referencePriceMin!=wyMsg.referencePriceMax> 
			      					  		  			<strong>${wyMsg.referencePriceMin }-${wyMsg.referencePriceMax }</strong> 元/平&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		<#else>
			      					  		  			<strong>${wyMsg.referencePriceMin }</strong> 元/平 &nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		</#if>
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		  
		      					  		  	
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>参考总价：</span>
		      						<p>
		      						<#if newHouse.wyMsgList??> 
		      					  		<#list newHouse.wyMsgList as wyMsg> 
		      					  		  	${wyMsg.wyTypeName }
			      					  		  	<#if wyMsg.totalPriceMin??> 
			      					  		  		<#if wyMsg.totalPriceMin!=wyMsg.totalPriceMax> 
			      					  		  			<strong>${wyMsg.totalPriceMin }-${wyMsg.totalPriceMax }</strong> 万元/套 &nbsp;&nbsp;&nbsp;&nbsp;
		      					  		  			<#else>
			      					  		  			<strong>${wyMsg.totalPriceMin }</strong> 万元/套 &nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  		</#if>
			      					  		  	<#else>
			      					  		  		暂无&nbsp;&nbsp;&nbsp;&nbsp;
			      					  		  	</#if>
		      					  		  	
		      					  		</#list>
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					  </p>
		      					</li>
		      					<li>
		      						<span>物业类型：</span>
		      					    <p>
		      					    <#if newHouse.wyTypeName??> 
		      					  		${newHouse.wyTypeName }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					    </p>
		      					</li>

		      				</ul>
					  	</div>
					  	<div class="list_content">
							<ul>
		      					<li>
		      					    <span>开发商：</span>
		      					    <p class="developers_name">
		      					    <#if newHouse.developers??> 
		      					  		${newHouse.developers }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>区域位置：</span>
		      					    <p>
		      					    <#if newHouse.areaDetail??> 
		      					  		${newHouse.areaDetail }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>楼盘地址：</span>
		      					    <p>
		      					    <#if newHouse.address??> 
		      					  		${newHouse.address }
		      					  	<#else>
		      					  		暂无
		      					    </#if>
		      					    </p>
		      					</li>
		      					<li>
		      						<span>售楼处电话：</span>
		      					    <p><#if newHouse.pcMobile??><strong id="phone12">400-898-6868  转  ${newHouse.pcMobile}</strong><#else>暂无</#if></p>
		      					</li>
		      				</ul>
				 		</div>
		      		</div>	
				</div>
			</div>
			<!--周边配置 地图 -->
      		<div class="newhouse_content_list  none-border" id="zhouBianPeiTao">
      		<input type="hidden" id="dimension" value="${newHouse.dimension}">
      		<input type="hidden" id="accuracy" value="${newHouse.accuracy}">
      			<h3>周边配套</h3>
      			<div class="map_container" style="position:relative;">
      			<div class="Map_box"  id="mapBox">
	      		</div>	
	      		<div class="assort_tabs">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
					    <li role="presentation" class="active"><a href="javascript:void(0);" onclick="change('交通')" aria-controls="traffic" role="tab" data-toggle="tab">交通</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('医疗')" aria-controls="hospital" role="tab" data-toggle="tab">医疗</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('购物')" aria-controls="shopping" role="tab" data-toggle="tab">购物</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('生活')" aria-controls="live" role="tab" data-toggle="tab">生活</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('娱乐')" aria-controls="recreation" role="tab" data-toggle="tab">娱乐</a></li>
					    <li role="presentation"><a href="javascript:void(0);" onclick="change('教科')" aria-controls="education" role="tab" data-toggle="tab">科教</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
					    <div role="tabpanel" class="tab-pane active" id="traffic">
							<div class="traffic_tabs">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist" id="twoMapTab">
									    <li role="presentation" class="active"><a href="javascript:void(0);" onclick="change('地铁站')" aria-controls="subway" role="tab" data-toggle="tab">地铁站</a></li>
									    <li role="presentation"><a href="javascript:void(0);" onclick="change('公交站')"  aria-controls="public" role="tab" data-toggle="tab">公交站</a></li>
									    <li role="presentation"><a href="javascript:void(0);" onclick="change('火车站')"  aria-controls="train" role="tab" data-toggle="tab">火车站</a></li>
									</ul>
									<div class="tab-content">
									    <div role="tabpanel" class="tab-pane active" >
									    	<ul class="address_list" id="results">
									    	</ul>
									    </div>
									</div>
							</div>
					    </div>
					</div>

					</div>	
					</div>
	      	</div>
	      	<!-- 猜你喜欢 -->
      		<div class="newhouse_content_list guess_yourlike">
      			<h4>猜你喜欢</h4>
      			<div class="house_recommend">
      			<#if newHouse.likeList??> 
			  		<#list newHouse.likeList as house> 
			  		  	<figure>
							<a href="${ctx }/front/newHouse/index_detail?id=${house.id }"><img src="${house.imgUrl }?x-oss-process=image/resize,m_fixed,h_140,w_181" width="350" height="234" /></a>
							<a href="${ctx }/front/newHouse/index_detail?id=${house.id }"><p>${house.name }</p></a>
							<#if house.referencePrice??> 
								<p style="margin-top: 0;">单价<strong>${house.referencePrice }</strong>元/平</p>
							<#elseif house.totalPrice??>
								<p style="margin-top: 0;">总价<strong>${house.totalPrice }</strong>万元</p>
							<#elseif house.aroundPrice??>
								<p style="margin-top: 0;">周边均价<strong>${house.aroundPrice }</strong>元/平</p>
							<#else>
								<p style="margin-top: 0;"><strong>售价待定</strong></p>
				  				
							 </#if>
						</figure>
			  		</#list>
			  	<#else>
			  		暂无
			    </#if>
	      		</div>		
	      	</div>
		</div>
	</div>

<div id="album_modal" class="album_modal modal fade single-ablum" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="${ctx }/static/front/img/album/album-close.png"></button>
	      <div class="modal-body">
	      	<div>
	      		<div class="swiper-container modal-gallery-top" style="width:1127px;height: 670px;">
	      		    <div class="swiper-wrapper">
	      		       <div class="swiper-slide" style="width:1127px;height: 670px;">
	      		          <img>
	      		       </div>
	      		    </div>
	      		</div>
	      	</div>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div id="houseType_modal" class="album_modal housetype modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="${ctx}/static/front/img/album/album-close.png"></button>
	      </div>
	      <div class="modal-body" style="padding-bottom: 30px;">
	      	<div>
	      		<div class="swiper-container modal-gallery-top" style="width:1127px;height: 670px;">
	      		    <div class="swiper-wrapper">
	      		       <div class="swiper-slide" style="width:1127px;height: 670px;">
	      		          <img id="houseTypeImg" src="">
	      		          <div class="housetype-swiper">
	      		          	<h4 class="housetype-swiper-title" id="houseName"></h4>
	      		          	<h3 class="housetype-swiper-subtitle" ></h3>
	      		          	<p class="housetype-swiper-spec">
	      		          		<span class="spec-title">参考价格</span>
	      		          		<span class="spec-content"><span class="price" id="referencePrice"></span></span>
	      		          	</p>
	      		          	<p class="housetype-swiper-spec">
	      		          		<span class="spec-title">居室</span>
	      		          		<span class="spec-content" id="room"></span>
	      		          	</p>
	      		          	<p class="housetype-swiper-spec">
	      		          		<span class="spec-title">建筑面积</span>
	      		          		<span class="spec-content" id="builtUp"></span>
	      		          	</p>
	      		          	<p class="housetype-swiper-spec">
	      		          		<span class="spec-title">朝向</span>
	      		          		<span class="spec-content" id="orientationsName"></span>
	      		          	</p>
	      		          	<p class="housetype-swiper-spec">
	      		          		<span class="spec-title">层高</span>
	      		          		<span class="spec-content" id="floor"></span>
	      		          	</p>
	      		          	<p class="housetype-swiper-spec">
	      		          		<span class="spec-title">户型描述</span>
	      		          		<span class="spec-content" id="describes"></span>
	      		          	</p>
	      		          </div>
	      		       </div>
	      		    </div>
	      		</div>
	      	</div>
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=10ldxb9hp1tc0PcUKXt71u2wiHy12Lp5"></script>
	<script src="${ctx }/static/back/newHouse/map.js"></script>
	<script src="${ctx }/static/front/js/scrollspy.js"></script>
 <script>
 //关注
  	 function attentionHouse(){
  		 if(user==''){
  			show_box(7);
  		 }else{
  			 $.ajax({
 		  		type:"post",
 		  		url:_ctx+"/front/agentIndex/addFollow",
 		  		data:{
 		  			"objectId":'${newHouse.id }',
 		  			"type":1
 		  		},
 		  		dataType:"json",
 		  		success: function(result){
 		  			if(result.status==200){
		  				location.reload();
		  			}
 		  		}
 		  	});
  		 }
  	
  	 }
  	 //取消关注
  	 function cancelHouse(){
  		 if(user==''){
  			show_box(7);
   		 }else{
   			 $.ajax({
  		  		type:"post",
  		  		url:_ctx+"/front/agentIndex/cancleFollow",
  		  		data:{
  		  			"objectId":'${newHouse.id }',
  		  			"type":1
  		  		},
  		  		dataType:"json",
  		  		success: function(result){
  		  			if(result.status==200){
  		  				location.reload();
  		  			}
  		  		}
  		  	});
   		}
  	 }
	
 		//相册弹框
        $(document).on("click",".pictureModal",function(argument) {
        	var $self = $(this);
        	$('#album_modal .swiper-slide img').attr("src",$self.attr("src"))
    	    $('#album_modal').modal();
    	    $('#album_modal').on('shown.bs.modal', function (e) {})

        });
        $(document).on("click",".pictureNewModal",function(argument) {
        	var $self = $(this);
        	$('#album_modal .swiper-slide img').attr("src",$self.attr("imgVal"))
    	    $('#album_modal').modal();
    	    $('#album_modal').on('shown.bs.modal', function (e) {})

        });
        //户型弹框
        function houseTypeModal(obj){
        	 $('#houseType_modal').modal();
     	    $('#houseType_modal').on('shown.bs.modal', function (e) {
     		   $("#houseTypeImg").attr("src",obj.imgUrl+"?x-oss-process=image/resize,m_pad,h_670,w_550,color_FFFFFF");
     		   $("#houseName").text(obj.houseName);
     		   if(null!=obj.referencePrice){
     			  $("#referencePrice").text(obj.referencePrice+" 万元")
     		   }else{
     			  $("#referencePrice").text("暂无");
     			  $("#referencePrice").removeClass("price");
     		   }
     		   
     		   var str = "";
     		   if(null!=obj.room){
     			  str+=obj.room+"室";
     		   }
     		   if(null!=obj.hall){
     			  str+=obj.hall+"厅";
     		   }
     		  if(null!=obj.toilet){
     			  str+=obj.toilet+"卫";
     		   }
     		 if(null!=obj.kitchen){
    			  str+=obj.kitchen+"厨";
    		   }
     		 if(str!=""){
     			 $("#room").text(str);
     		 }else{
     			 $("#room").text("暂无"); 
     		 }
     		  if(null!=obj.builtUp){
     			 $("#builtUp").text(obj.builtUp + "㎡");
     		   }else{
     			 $("#builtUp").text("暂无");
     		   }
     		  if(null!=obj.orientationsName){
     			  $("#orientationsName").text(obj.orientationsName)
     		   }else{
     			  $("#orientationsName").text("暂无");
     		   }
     		  if(null!=obj.floor){
     			  $("#floor").text(obj.floor)
     		   }else{
     			  $("#floor").text("暂无");
     		   }
     		  if(null!=obj.describes){
     			  $("#describes").text(obj.describes)
     		   }else{
     			  $("#describes").text("暂无");
     		   }
     		   
     	    })
        }
    </script>
    <script>
	    $(function() {
    		isPcOrOther('http://wap.dasoujia.com/mobile/views/dsj-newhouse/new-details.html?id=${newHouse.id }');
		})

		$('body').scrollspy({ target: '#nav_ceiling' });
		$('[data-spy="scroll"]').each(function () {
			var $spy = $(this).scrollspy('refresh');
		})
		$(".omitCharacter").on("mousemove",function(event){
			if($(this).text().trim().length >= 30){
				$(this).next().show();
			}
		});
		$(".omitCharacter").on("mouseout",function(event){
			$(this).next().hide();
		});
		
		$(".hover_box").on("mousemove",function(event){
			if($(this).text().trim().length >= 15){
				$(this).next().show();
			}
		});
		$(".hover_box").on("mouseout",function(event){
				$(this).next().hide();
		});

		/*用js限制字数，超出部分以省略号...显示*/
	    function LimitNumber(txt,idName) {
	        var str = txt;
	        if(str.length>140){
	        	str = str.substr(0,140) + '...' ;
	        	var id=document.getElementById(idName);
	        	id.innerText=str;
	        }
	    };
		var data = $("#miaoshu").text(),
		data = data.replace(" ", ""),
		data = data.replace('\r','')
		data = data.replace(/\n/g,'');
		LimitNumber(data,'miaoshu');
		
		function dyname_name() {
			var names = $(".dyname").html();
			if(names == undefined ){
				return
			}
			if( names.length >30){
	        	 names = names.substr(0,30) + '...' ;
	       	 	var id=$(".dyname");
	        	id.html(names);
	        }
	        
	    };
		dyname_name();
		
		function omit_name() {
			var data = $(".omitCharacter").html();
			if(data == undefined ){
				return
			}
	        if(data.length >30){
	        	 data = data.substr(0,30) + '...' ;
	       	 	var id=$(".omitCharacter");
	        	id.html(data);
	        }
	    };
		omit_name();
		
		$(function(){
			var arr = [
			           {"dsj":10817,"jd":1121,"name":"招商中国玺"},
			           {"dsj":10176,"jd":1120,"name":"亦庄金茂府"},
			           {"dsj":10679,"jd":1119,"name":"中粮天恒天悦壹号"},
			           {"dsj":10912,"jd":1118,"name":"天恒半山世家"},
			           {"dsj":10405,"jd":1117,"name":"天恒摩墅"},
			           {"dsj":10675,"jd":1116,"name":"天恒金融街公园懿府"},
			           {"dsj":10882,"jd":1115,"name":"天恒水岸壹号"},
			           {"dsj":11252,"jd":1114,"name":"天恒京西悦府"},
			           {"dsj":10643,"jd":1113,"name":"中铁华侨城和园"},
			           {"dsj":10667,"jd":1112,"name":"中海墅"},
			           {"dsj":11402,"jd":1111,"name":"旭辉城"},
			           {"dsj":10103,"jd":1110,"name":"泷悦长安·观景华宅"},
			           {"dsj":10325,"jd":1109,"name":"永泰西山御园"},
			           {"dsj":11540,"jd":1108,"name":"融创观澜湖公园壹号"},
			           {"dsj":10327,"jd":1107,"name":"长安天街"},
			           {"dsj":10075,"jd":1106,"name":"首开龙湖天琅"},
			           {"dsj":11546,"jd":1105,"name":"景粼原著"},
			           {"dsj":10398,"jd":1104,"name":"K2十里春风"},
			           {"dsj":11543,"jd":1103,"name":"金科天宸荟"},
			           {"dsj":10812,"jd":1102,"name":"首开保利熙悦诚郡"},
			           {"dsj":11538,"jd":1101,"name":"尚峯壹號"},
			           {"dsj":10017,"jd":1100,"name":"首开香溪郡"},
			           {"dsj":11554,"jd":1122,"name":"中海国际城"},
			           {"dsj":10102,"jd":1123,"name":"林肯公园"},
			           {"dsj":10782,"jd":1124,"name":"远洋天著"},
			           {"dsj":10198,"jd":1125,"name":"益田远洋万和风景"},
			           {"dsj":11545,"jd":1126,"name":"远洋7号"},
			           {"dsj":11558,"jd":1127,"name":"北京城建北京合院"},
			           ];
			for(var i in arr){
				if($("#phone12").text()==("400-898-6868  转 "+" "+arr[i].dsj)){
					$("#phone12").text("400-898-0806 转 "+" "+arr[i].jd);
					$("#phone12_top").html("<strong>400-898-0806</strong><span>转</span><strong>"+arr[i].jd+"</strong>");
					return false;
				}
			}
		})
    </script>
</html>