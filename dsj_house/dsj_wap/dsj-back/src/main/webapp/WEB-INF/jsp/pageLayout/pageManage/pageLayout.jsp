<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

			<div class="col-xs-12 padding-foot">
					<div class="white-head">
						<h1 class="page-title">
							<!-- PAGE HEADER -->
							楼盘确定 
						</h1>
						<!-- breadcrumb -->
						<ol class="breadcrumb">
							<li>页面管理</li>
							<li>首页设置</li>
						</ol>
						<!-- end breadcrumb -->
						<form class="form-horizontal set-index" onsubmit="return false">
							<div class="row">
							    <div class="col-xs-12 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 新房
							    <c:forEach items="${newHouseList}" var="newHouse" varStatus="newHouseLists">
							         <input  name="labelName" value="${newHouse.label}" role="tab" data-toggle="tab" data-target="#${newHouse.id}" placeholder="请输入标签" maxlength="10">
							         <input name="labelValue" value="${newHouse.id}" type="hidden"> 
							    </c:forEach>
									<button class="dsj_btn btn dsj_btn_blue" onclick="updateLabel();" >确定</button>
							    </div>
							</div>
							<div class="row">
								<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="1">
								 <c:forEach items="${newHouseDirectoryList}" var="newHouse" varStatus="newHouseDirectory">
								      <c:if test="${newHouse.labelId == 1}">
								         <div class="col-xs-6">
								         	<div class="embed-responsive embed-responsive-16by9">
								         	       <img class="img-responsive embed-responsive-item" src="${newHouse.picture}" style="width: 500px;height: 500px;">
								         	</div>
								         	<div class="btngroup">
								         		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toNewHouseAdd?id='+${newHouse.pcNewHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateNewHousePage('${newHouse.pcNewHouseId}')">确定</button>
								         	</div>
								         	<p class="mt10">楼盘名称:${newHouse.name}      ${newHouse.wyTypeName}
								         	<c:if test="${newHouse.indexPrice != null ||newHouse.indexPrice != ''}">
								         	<span class="pull-right">${newHouse.priceName}  ${newHouse.indexPrice} ${newHouse.pricedw}</span>
								         	</c:if>
								         	</p>
								         	<p>主力户型:${newHouse.room}室${newHouse.hall}厅${newHouse.toilet}卫
								         	<c:if test="${newHouse.phone != null}">
								         	<span class="pull-right">400电话:400-8986868<span>转</span>${newHouse.phone}</span>
								         	</c:if>
								         	</p>
								         	<p>楼盘卖点:${newHouse.recommend}</p>
								         	<div class="clearfix agent">
								         		<div class="wrapper ">
								     	    		<img src="${newHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								     	    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentAdd?id='+${newHouse.agentId},700)" >编辑</button>
								         		</div>
								         		<div class="groun-right">
								         			<p class="agent_name">${newHouse.agentName}</p>
								         			<div class="ground">
								         			<c:if test="${newHouse.smallIcon != null}"><img  src="${newHouse.smallIcon}"></c:if>
								         			<c:if test="${newHouse.agName != null}">${newHouse.agName}</c:if>
								         			</div>
								         			<div class="content clearfix">
								         				<p>经纪人点评:${newHouse.comment}</p>
								         			</div>
								         		</div>
								         	</div>
								         </div>
								         </c:if>
								   </c:forEach>
								   </div>
								   <div role="tabpanel" class="tab-pane" id="2">
								 <c:forEach items="${newHouseDirectoryList}" var="newHouse" varStatus="newHouseDirectory">
								      <c:if test="${newHouse.labelId == 2}">
								         <div class="col-xs-6">
								         	<div class="embed-responsive embed-responsive-16by9">
								         	       <img class="img-responsive embed-responsive-item" src="${newHouse.picture}" style="width: 500px;height: 500px;">
								         	</div>
								         	<div class="btngroup">
								         		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toNewHouseAdd?id='+${newHouse.pcNewHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateNewHousePage('${newHouse.pcNewHouseId}')">确定</button>
								            </div>
								         	<p class="mt10">楼盘名称:${newHouse.name}     ${newHouse.wyTypeName}
								         	<c:if test="${newHouse.indexPrice != null ||newHouse.indexPrice != ''}">
								         	<span class="pull-right">${newHouse.priceName}  ${newHouse.indexPrice} ${newHouse.pricedw}</span>
								         	</c:if>
								         	</p>
								         	<p>主力户型:${newHouse.room}室${newHouse.hall}厅${newHouse.toilet}卫
								         	<c:if test="${newHouse.phone != null}">
								         	<span class="pull-right">400电话:400-8986868<span>转</span>${newHouse.phone}</span>
								         	</c:if>
								         	</p>
								         	<p>楼盘卖点:${newHouse.recommend}</p>
								         	<div class="clearfix agent">
								         		<div class="wrapper ">
								     	    		<img src="${newHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								     	    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentAdd?id='+${newHouse.agentId},700)" >编辑</button>
								         		</div>
								         		<div class="groun-right">
								         			<p class="agent_name">${newHouse.agentName}</p>
								         			<div class="ground">
								         			<c:if test="${newHouse.smallIcon != null}"><img  src="${newHouse.smallIcon}"></c:if>
								         			<c:if test="${newHouse.agName != null}">${newHouse.agName}</c:if>
								         			</div>
								         			<div class="content clearfix">
								         				<p>经纪人点评:${newHouse.comment}</p>
								         			</div>
								         		</div>
								         	</div>
								         </div>
								         </c:if>
								   </c:forEach>
								    </div>
								   <div role="tabpanel" class="tab-pane" id="3">
								   <c:forEach items="${newHouseDirectoryList}" var="newHouse" varStatus="newHouseDirectory">
								      <c:if test="${newHouse.labelId == 3}">
								         <div class="col-xs-6">
								         	<div class="embed-responsive embed-responsive-16by9">
								         	       <img class="img-responsive embed-responsive-item" src="${newHouse.picture}" style="width: 500px;height: 500px;">
								         	</div>
								         	<div class="btngroup">
								         		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toNewHouseAdd?id='+${newHouse.pcNewHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateNewHousePage('${newHouse.pcNewHouseId}')">确定</button>
								         	</div>
								         	<p class="mt10">楼盘名称:${newHouse.name}     ${newHouse.wyTypeName}
								         	<c:if test="${newHouse.indexPrice != null ||newHouse.indexPrice != ''}">
								         	<span class="pull-right">${newHouse.priceName}  ${newHouse.indexPrice} ${newHouse.pricedw}</span>
								         	</c:if>
								         	</p>
								         	<p>主力户型:${newHouse.room}室${newHouse.hall}厅${newHouse.toilet}卫
								         	<c:if test="${newHouse.phone != null}">
								         	<span class="pull-right">400电话:400-8986868<span>转</span>${newHouse.phone}</span>
								         	</c:if>
								         	</p>
								         	<p>楼盘卖点:${newHouse.recommend}</p>
								         	<div class="clearfix agent">
								         		<div class="wrapper ">
								     	    		<img src="${newHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								     	    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentAdd?id='+${newHouse.agentId},700)" >编辑</button>
								         		</div>
								         		<div class="groun-right">
								         			<p class="agent_name">${newHouse.agentName}</p>
								         			<div class="ground">
								         			<c:if test="${newHouse.smallIcon != null}"><img  src="${newHouse.smallIcon}"></c:if>
								         			<c:if test="${newHouse.agName != null}">${newHouse.agName}</c:if>
								         			</div>
								         			<div class="content clearfix">
								         				<p>经纪人点评:${newHouse.comment}</p>
								         			</div>
								         		</div>
								         	</div>
								         </div>
								         </c:if>
								   </c:forEach>
								    </div>
								   <div role="tabpanel" class="tab-pane" id="4">
								    <c:forEach items="${newHouseDirectoryList}" var="newHouse" varStatus="newHouseDirectory">
								      <c:if test="${newHouse.labelId == 4}">
								         <div class="col-xs-6">
								         	<div class="embed-responsive embed-responsive-16by9">
								         	       <img class="img-responsive embed-responsive-item" src="${newHouse.picture}" style="width: 500px;height: 500px;">
								         	</div>
								         	<div class="btngroup">
								         		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toNewHouseAdd?id='+${newHouse.pcNewHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateNewHousePage('${newHouse.pcNewHouseId}')">确定</button>
								         		</div>
								         		<p class="mt10">楼盘名称:${newHouse.name}     ${newHouse.wyTypeName}
								         	<c:if test="${newHouse.indexPrice != null ||newHouse.indexPrice != ''}">
								         	<span class="pull-right">${newHouse.priceName}  ${newHouse.indexPrice} ${newHouse.pricedw}</span>
								         	</c:if>
								         	</p>
								         	<p>主力户型:${newHouse.room}室${newHouse.hall}厅${newHouse.toilet}卫
								         	<c:if test="${newHouse.phone != null}">
								         	<span class="pull-right">400电话:400-8986868<span>转</span>${newHouse.phone}</span>
								         	</c:if>
								         	</p>
								         	<p>楼盘卖点:${newHouse.recommend}</p>
								         	<div class="clearfix agent">
								         		<div class="wrapper ">
								     	    		<img src="${newHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								     	    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentAdd?id='+${newHouse.agentId},700)" >编辑</button>
								         		</div>
								         		<div class="groun-right">
								         			<p class="agent_name">${newHouse.agentName}</p>
								         			<div class="ground">
								         			<c:if test="${newHouse.smallIcon != null}"><img  src="${newHouse.smallIcon}"></c:if>
								         			<c:if test="${newHouse.agName != null}">${newHouse.agName}</c:if>
								         			</div>
								         			<div class="content clearfix">
								         				<p>经纪人点评:${newHouse.comment}</p>
								         			</div>
								         		</div>
								         	</div>
								         </div>
								         </c:if>
								   </c:forEach>
								    </div>
								</div>
							</div>
							<div class="row">
							    <div class="col-xs-12 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 二手房
							     <c:forEach items="${oldHouseList}" var="oldHouse">
							         <input  name="labelName2" value="${oldHouse.label}" role="tab" data-toggle="tab" data-target="#${oldHouse.id}" placeholder="请输入标签" maxlength="10">
							         <input name="labelValue2" value="${oldHouse.id}" type="hidden">
							    </c:forEach>
							    	<button class="dsj_btn btn dsj_btn_blue" onclick="updateLabel2();">确定</button>
							    </div>
							</div>
							<div class="row">
								<!-- Tab panes -->
								<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="5">
								 <c:forEach items="${oldHouseDirectoryList}" var="oldHouse">
								      <c:if test="${oldHouse.labelId==5}">
								  	    <div class="col-xs-4">
								  	    	<div class="embed-responsive embed-responsive-16by9">
								  	    		<img class="img-responsive embed-responsive-item" src="${oldHouse.picture}" style="width: 300px;height: 200px;">
								  	    	</div>
								  	    	<div class="btngroup">
								  	    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toOldHouseAdd?id='+${oldHouse.pcOldHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateOldHousePage('${oldHouse.pcOldHouseId}')">确定</button>
								  	    		</div>
								  	    	<p class="mt10">楼盘名称:${oldHouse.name}&nbsp&nbsp${oldHouse.recommend}&nbsp&nbsp${oldHouse.price}/元</p>
								  	    	<p>户型:${oldHouse.room}室${oldHouse.hall}厅${oldHouse.toilet}卫&nbsp&nbsp${oldHouse.occupyArea}m²&nbsp&nbsp${oldHouse.typeGroupName}</p>
								  	    	<p>委托中介：
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '1')}">
								  	    	大搜家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '2')}">
								  	    	链家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '3')}">
								  	    	麦田
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '4')}">
								  	    	中原地产
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '5')}">
								  	    	我爱我家
								  	    	</c:if>
								  	    	</p>
								  	    	<div class="clearfix agent">
								  	    		<div class="wrapper">
								  		    		<img src="${oldHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								  		    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentOldHouse?id='+${oldHouse.agentId},700)" >编辑</button>
								  	    		</div>
								  	    		<div class="groun-right2">
								  	    			<c:if test="${oldHouse.smallIcon != null}"><img  src="${oldHouse.smallIcon}"></c:if>
								         			<c:if test="${oldHouse.agName != null}">${oldHouse.agName}</c:if>
								  	    			<div class="content clearfix">
								  	    				<p>经纪人：${oldHouse.agentName}</p>
								  	    				<p>经纪人点评：${oldHouse.comment}</p>
								  	    			</div>
								  	    		</div>
								  	    	</div>
								  	    </div>
								  	    </c:if>
								  </c:forEach>
								  </div>
								  <div role="tabpanel" class="tab-pane" id="6">
								  <c:forEach items="${oldHouseDirectoryList}" var="oldHouse">
								      <c:if test="${oldHouse.labelId==6}">
								  	    <div class="col-xs-4">
								  	    	<div class="embed-responsive embed-responsive-16by9">
								  	    		<img class="img-responsive embed-responsive-item" src="${oldHouse.picture}" style="width: 300px;height: 200px;">
								  	    	</div>
								  	    	<div class="btngroup">
								  	    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toOldHouseAdd?id='+${oldHouse.pcOldHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateOldHousePage('${oldHouse.pcOldHouseId}')">确定</button>
								  	    	</div>
								  	    	<p class="mt10">楼盘名称:${oldHouse.name}&nbsp&nbsp${oldHouse.recommend}&nbsp&nbsp${oldHouse.price}/元</p>
								  	    	<p>户型:${oldHouse.room}室${oldHouse.hall}厅${oldHouse.toilet}卫&nbsp&nbsp${oldHouse.occupyArea}m²&nbsp&nbsp${oldHouse.typeGroupName}</p>
								  	    	<p>委托中介：
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '1')}">
								  	    	大搜家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '2')}">
								  	    	链家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '3')}">
								  	    	麦田
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '4')}">
								  	    	中原地产
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '5')}">
								  	    	我爱我家
								  	    	</c:if>
								  	    	</p>
								  	    	<div class="clearfix agent">
								  	    		<div class="wrapper">
								  		    		<img src="${oldHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								  		    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentOldHouse?id='+${oldHouse.agentId},700)" >编辑</button>
								  	    		</div>
								  	    		<div class="groun-right2">
								  	    			<c:if test="${oldHouse.smallIcon != null}"><img  src="${oldHouse.smallIcon}"></c:if>
								         			<c:if test="${oldHouse.agName != null}">${oldHouse.agName}</c:if>
								  	    			<div class="content clearfix">
								  	    				<p>经纪人：${oldHouse.agentName}</p>
								  	    				<p>经纪人点评：${oldHouse.comment}</p>
								  	    			</div>
								  	    		</div>
								  	    	</div>
								  	    </div>
								  	    </c:if>
								  </c:forEach>
								   </div>
								  <div role="tabpanel" class="tab-pane" id="7">
								   <c:forEach items="${oldHouseDirectoryList}" var="oldHouse">
								      <c:if test="${oldHouse.labelId==7}">
								  	    <div class="col-xs-4">
								  	    	<div class="embed-responsive embed-responsive-16by9">
								  	    		<img class="img-responsive embed-responsive-item" src="${oldHouse.picture}" style="width: 300px;height: 200px;">
								  	    	</div>
								  	    	<div class="btngroup">
								  	    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toOldHouseAdd?id='+${oldHouse.pcOldHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateOldHousePage('${oldHouse.pcOldHouseId}')">确定</button>
								  	    	</div>
								  	    	<p class="mt10">楼盘名称:${oldHouse.name}&nbsp&nbsp${oldHouse.recommend}&nbsp&nbsp${oldHouse.price}/元</p>
								  	    	<p>户型:${oldHouse.room}室${oldHouse.hall}厅${oldHouse.toilet}卫&nbsp&nbsp${oldHouse.occupyArea}m²&nbsp&nbsp${oldHouse.typeGroupName}</p>
								  	    	<p>委托中介：
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '1')}">
								  	    	大搜家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '2')}">
								  	    	链家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '3')}">
								  	    	麦田
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '4')}">
								  	    	中原地产
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '5')}">
								  	    	我爱我家
								  	    	</c:if>
								  	    	</p>
								  	    	<div class="clearfix agent">
								  	    		<div class="wrapper">
								  		    		<img src="${oldHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								  		    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentOldHouse?id='+${oldHouse.agentId},700)" >编辑</button>
								  	    		</div>
								  	    		<div class="groun-right2">
								  	    			<c:if test="${oldHouse.smallIcon != null}"><img  src="${oldHouse.smallIcon}"></c:if>
								         			<c:if test="${oldHouse.agName != null}">${oldHouse.agName}</c:if>
								  	    			<div class="content clearfix">
								  	    				<p>经纪人：${oldHouse.agentName}</p>
								  	    				<p>经纪人点评：${oldHouse.comment}</p>
								  	    			</div>
								  	    		</div>
								  	    	</div>
								  	    </div>
								  	    </c:if>
								  </c:forEach>
								   </div>
								  <div role="tabpanel" class="tab-pane" id="8">
								  <c:forEach items="${oldHouseDirectoryList}" var="oldHouse">
								      <c:if test="${oldHouse.labelId==8}">
								  	    <div class="col-xs-4">
								  	    	<div class="embed-responsive embed-responsive-16by9">
								  	    		<img class="img-responsive embed-responsive-item" src="${oldHouse.picture}" style="width: 300px;height: 200px;">
								  	    	</div>
								  	    	<div class="btngroup">
								  	    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toOldHouseAdd?id='+${oldHouse.pcOldHouseId},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateOldHousePage('${oldHouse.pcOldHouseId}')">确定</button>
								  	    		</div>
								  	    	<p class="mt10">楼盘名称:${oldHouse.name}&nbsp&nbsp${oldHouse.recommend}&nbsp&nbsp${oldHouse.price}/元</p>
								  	    	<p>户型:${oldHouse.room}室${oldHouse.hall}厅${oldHouse.toilet}卫 &nbsp&nbsp${oldHouse.occupyArea}m²&nbsp&nbsp${oldHouse.typeGroupName}</p>
								  	    	<p>委托中介：
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '1')}">
								  	    	大搜家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '2')}">
								  	    	链家
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '3')}">
								  	    	麦田
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '4')}">
								  	    	中原地产
								  	    	</c:if>
								  	    	<c:if test="${fn:contains(oldHouse.companyTypes, '5')}">
								  	    	我爱我家
								  	    	</c:if>
								  	    	</p>
								  	    	<div class="clearfix agent">
								  	    		<div class="wrapper">
								  		    		<img src="${oldHouse.avatarUrl}" class="img-responsive" style="width: 80px;height: 80px;">
								  		    		<button class="dsj_btn btn dsj_btn_blue" type="submit" onclick="openCommonModal('${ctx}/back/pageLayout/toAgentOldHouse?id='+${oldHouse.agentId},700)" >编辑</button>
								  	    		</div>
								  	    		<div class="groun-right2">
								  	    			<c:if test="${oldHouse.smallIcon != null}"><img  src="${oldHouse.smallIcon}"></c:if>
								         			<c:if test="${oldHouse.agName != null}">${oldHouse.agName}</c:if>
								  	    			<div class="content clearfix">
								  	    				<p>经纪人：${oldHouse.agentName}</p>
								  	    				<p>经纪人点评：${oldHouse.comment}</p>
								  	    			</div>
								  	    		</div>
								  	    	</div>
								  	    </div>
								  	    </c:if>
								  </c:forEach>
								   </div>
								</div>

							</div>
<!-- 							<div class="row"> -->
<!-- 							    <div class="col-xs-12 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 租房 -->
<!-- 							    </div> -->
<!-- 							</div> -->
<!-- 							<div class="row"> -->
<%-- 							<c:forEach items="${rentHouseList}" var="rentHouse"> --%>
<!-- 							    <div class="col-xs-4"> -->
<!-- 							    	<div class="embed-responsive embed-responsive-16by9"> -->
<%-- 							    		<img class="img-responsive embed-responsive-item" src="${rentHouse.pictureUrl}"> --%>
<!-- 							    	</div> -->
<!-- 							    	<div class="btngroup"> -->
<%-- 							    		 <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toRentHouseAdd?id='+${rentHouse.pcRentId},700)">编辑</button> --%>
<%-- 								         <button class="dsj_btn btn dsj_btn_blue" onclick="updateRentHousePage('${rentHouse.pcRentId}')">确定</button> --%>
<!-- 							    	</div> -->
<%-- 							    	<p class="mt10">楼盘名称${rentHouse.roomName}<span class="pull-right">价格${rentHouse.rentPrice}</span></p> --%>
<%-- 							    	<p>主力户型:${rentHouse.door}室${rentHouse.hall}厅${rentHouse.toilet}卫</p> --%>
<%-- 							    	<p>地址：${rentHouse.address}</p> --%>
<!-- 							    </div> -->
<%-- 							  </c:forEach> --%>
<!-- 							</div> -->
                               <div class="row">
							    <div class="col-xs-12 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 权证
							    </div>
							   </div>
							 <div class="row">
							 <c:forEach items="${warrantLists}" var="warrant">
							   <div class="col-xs-4">
							  <div class="embed-responsive embed-responsive-16by9">
								  	    		<img class="img-responsive embed-responsive-item" src="${warrant.picture}">
							  </div>
							  <p>${warrant.title}</p><br>
							  <p>${warrant.label}</p><p>${warrant.price}/元</p>
								  	    	<div class="btngroup">
								  	    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toWarrantAdd?id='+${warrant.id},700)">编辑</button>
								         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateWarrantPage('${warrant.id}')">确定</button>
								  	    	</div>
							</div>
							</c:forEach>
							</div>
							<div class="row">
							    <div class="col-xs-12 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 经纪人
							    </div>
							</div>
							<div class="row">
							<c:forEach items="${agentDirectoryList}" var="agent">
							    <div class="col-xs-3">
							    	<div class="embed-responsive embed-responsive-16by9">
							    		<img class="img-responsive embed-responsive-item" src="${agent.cardUrl}">
							    		<p>${agent.name} <span class="pull-right">联络方式:400-8986868转${agent.tellPhone}</span><br>
							    		已认证   
								  	    		<c:if test="${agent.smallIcon != null}"><img  src="${agent.smallIcon}"></c:if>
								         		<c:if test="${agent.agName != null}">${agent.agName}</c:if>
								         		 <span>综合得分：${agent.totalScore}</span>
							    		<c:if test="${agent.workyears != null}">
							    		  从业年限：${agent.workyears}年<br>
							    		</c:if>
							    		 经纪公司：${agent.companyName}<br>
							    		精耕区域：${agent.business}<br>
							    		 ${agent.feature}
							    		</p>
							    	</div>
							    	<div class="btngroup small_button">
							    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toAgentPcAdd?id='+${agent.pcAgentId},700)">编辑</button>
								            <button class="dsj_btn btn dsj_btn_blue" onclick="updateAgentPage('${agent.pcAgentId}')">确定</button>
							    	</div>
							    </div>
							</c:forEach>
							</div>
							<div class="row">
							    <div class="col-xs-12 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> banner
							    </div>
							</div>
							<div class="footer-banner">
		                     <div class="content-card-imgcontianer content-agent__img">
						    <div class="content-agent__img-mask"></div>
						    <img src="${banner.picture}">
					        </div>
					        <div class="btngroup">
		  	    		    <button class="dsj_btn btn dsj_btn_grey"  onclick="openCommonModal('${ctx}/back/pageLayout/toBannerAdd?id='+${banner.id},700)">编辑</button>
		         		    <button class="dsj_btn btn dsj_btn_blue" onclick="updateBannerPage('${banner.id}')">确定</button>
						    </div>
		                   </div>
						</form>
					</div>
				</div>
<script src="${ctx}/static/back/pcHouse/pcHouse.js"></script>
<script>
function updateAgentPage(param){
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateAgentPage",
		data:{
			id:param
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
				location=_ctx+"/back/frame/pageLayout/pageManageMent";
			}
		}
	})
}
function updateLabel(){
	var array = new Array();
	for(var i=0;i<4;i++){
		var item = {};
		item.label =$("input[name='labelName']")[i].value;
		item.id = $("input[name='labelValue']")[i].value;
		array[i] = item;
	}
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateLabel",
		data:JSON.stringify(array),
		datatype:"json",
		contentType : 'application/json',
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
				location=_ctx+"/back/frame/pageLayout/pageManageMent";
			}
		}
	})
}

function updateLabel2(){
	var array = new Array();
	for(var i=0;i<4;i++){
		var item = {};
		item.label =$("input[name='labelName2']")[i].value;
		item.id = $("input[name='labelValue2']")[i].value;
		array[i] = item;
	}
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateLabel",
		data:JSON.stringify(array),
		datatype:"json",
		contentType : 'application/json',
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
				location=_ctx+"/back/frame/pageLayout/pageManageMent";
			}
		}
	})
}

// function dddd(id,obj){
// 	var label=$(obj).val();
// 	$.ajax({
// 		type:"post",
// 		url:_ctx+"/back/pageLayout/updateWarrantLabel",
// 		data:{id:id,label:label},
// 		datatype:"json",
// 		success:function(result){
// 			if(result.status!=200){
// 				 setErrorContent(result.message);
// 			}
// 		}
// 	})
// }

function updateWarrantPage(param){
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateWarrantPage",
		data:{
			id:param
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
			}
		}
	})
}

function updateBannerPage(param){
	$.ajax({
		type:"post",
		url:_ctx+"/back/pageLayout/updateBannerPage",
		data:{
			id:param
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("已发布到前台首页");
			}
		}
	})
}
</script>