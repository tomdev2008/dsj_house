<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		
		<ol class="breadcrumb">
			<li>二手房房源</li>
			<li>详情 </li>
		</ol>
	
		<div class="row" style="margin: 20px;">
						  <!-- Nav tabs -->
						  <ul class="nav nav-tabs" role="tablist">
						    <li role="presentation" class="active">
						    	<a href="${ctx}/back/frame/oldHouse/master/master_view?id=${oldMaster.id}" >房源信息</a>
						    </li>
						    <li role="presentation">
						    	<c:if test="${ empty oldMaster.id }">
									<a >房源图片</a>
								</c:if>
								<c:if test="${ not empty oldMaster.id }">
									<a  href="${ctx}/back/frame/oldHouse/master/master_image_list_view?id=${oldMaster.id}" >房源图片</a>
								</c:if>
						    </li>
						   
						  </ul>
			<form class="form-horizontal" id="addForm">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 基本信息</div>
		   </div>
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		        	<div style="display:none">
		        		<input name="id" value="${oldMaster.id}" />
		        	</div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">备案编号</label>
		                <div class="col-sm-6">
		                  <input name="recordNo"  readonly="readonly"  value="${oldMaster.recordNo}"  type="text" class="form-control" placeholder="备案账号" data-validate="required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">房源ID</label>
		                <div class="col-sm-6">
		                  <input name="houseId"  readonly="readonly" value="${oldMaster.houseId}"  type="text" class="form-control" placeholder="房源ID">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">*小区名称</label>
		                <div class="col-sm-6">
		                  <select readonly="readonly"  id="dicSelectId" class="form-control dsj-inline " name="dicId" class="js-example-basic-multiple" >
 								<c:if test="${dic.id!=null}"><option value="${dic.id}">${dic.sprayName}</option></c:if>
						 </select>
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">*房屋户型：</label>
		              	     <div class="col-sm-10">
			                	<select   name="room" readonly="readonly"   class="form-control dsj-inline dsj-width-1" >
				                      <option value="">请选择</option>
				                      <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.room}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>室
	
			                    <select   name="hall" readonly="readonly"   class="form-control dsj-inline dsj-width-1">
				                      <option value="">请选择</option>
				                         <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.hall}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>厅
			                    <select   readonly="readonly"  name="toilet" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">请选择</option>
				                       <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.toilet}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>卫
			                    <select  readonly="readonly"  name="kitchen"  class="form-control dsj-inline dsj-width-1" >
				                      <option value="">请选择</option>
				                        <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.kitchen}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>厨
		                    </div>
		            </div>
		       
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">房屋情况：</label>
		              	     <div class="col-sm-10">
			                	<select  readonly="readonly"  name="houseType" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">房屋类型</option>
				                      <c:forEach var="item" items="${houseTypeMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.houseType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
	
			                    <select  readonly="readonly"  name="renovation" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">装修状况</option>
				                      <c:forEach var="item" items="${renvationMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.renovation}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
			                    <select readonly="readonly"   name="orientations"  class="form-control dsj-inline dsj-width-1">
				                      <option value="">朝向</option>
				                       <c:forEach var="item" items="${orientationsMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.orientations}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
			                   
		                    </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">*楼层</label>
		                <div class="col-sm-6">
		                  <input readonly="readonly"  name="floor" value="${oldMaster.floor}" type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required">楼
		                  共<input readonly="readonly"  name="floorNum" value="${oldMaster.floorNum}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="required">层
		                 楼层类型
		                 	<select readonly="readonly"  name="floorType"  class="form-control dsj-inline dsj-width-1" >
				                      <option value="">楼层类型</option>
				                       <c:forEach var="item" items="${floorTypeMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.floorType}">selected="selected"</c:if>  value="${item.key}">${item.value}</option>
				                      </c:forEach>
				                      
			                    </select>
		                </div>
		            </div>
		             <div class="form-group">
		              	    <label class="col-sm-2 control-label">户室号：</label>
		              	     <div class="col-sm-10">
		              	     	 <input readonly="readonly"  name="roomNumber1"  value="${oldMaster.roomNumber1Cell }"  type="text" class="form-control dsj-inline dsj-width-1" >
			                	<select readonly="readonly"  name="roomNumber1Cell"  class="form-control dsj-inline dsj-width-1" >
				                       <c:forEach var="item" items="${roomNo1Map}"> 
				                      	<option <c:if test="${item.key==oldMaster.roomNumber1Cell}">selected="selected"</c:if>  value="${item.key}">${item.value}</option>
				                      </c:forEach>
				                      
			                    </select>
								 <input readonly="readonly"  name="roomNumber2"  value="${oldMaster.roomNumber2Cell }" type="text" class="form-control dsj-inline dsj-width-1" >
			                    <select  readonly="readonly"  name="roomNumber2Cell" class="form-control dsj-inline dsj-width-1">
				                       <c:forEach var="item" items="${roomNo2Map}"> 
				                      	<option <c:if test="${item.key==oldMaster.roomNumber2Cell}">selected="selected"</c:if>  value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
			                    <input name="roomNumber" readonly="readonly"  value="${oldMaster.roomNumber}"  type="text" class="form-control dsj-inline dsj-width-1" />室
			                   
		                    </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*建筑面积</label>
		                <div class="col-sm-10">
		                  <input readonly="readonly"  name="buildArea" id="buildAreaId" value="${oldMaster.buildArea}"   type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required">平米
		                  *建筑年代<input name="buildYear" value="${oldMaster.buildYear}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="required">年  填写4位数字，如：2017
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">产权：</label>
		              	     <div class="col-sm-10">
			                	<select   readonly="readonly"  name="certificate"  class="form-control dsj-inline dsj-width-1" >
				                      <option value="">请选择</option>
				                        <c:forEach var="item" items="${certificateMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.certificate}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
		                    </div>
		            </div>
		        </div>
		    </div>
		    <div class="row">
				 <div class="col-xs-8 col-xs-offset-2 font16">价格信息</div>
			 </div>
		     <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		           
		             <div class="form-group">
	                    <label  class="col-sm-2 control-label">*产权证满2:</label>
	                    <div class="col-sm-6">
	                    	 <c:forEach var="item" items="${yesNoMap}"> 
				                  <label class="checkbox-inline">
		                      		<input disabled="disabled"  name="certificateTwo"  <c:if test="${item.key==oldMaster.certificateTwo}">checked="checked"</c:if> type="radio" id="inlineCheckbox1" value="${item.key}">${item.value}
		                    	 </label>
				            </c:forEach>
		                    
		                      <label class="checkbox-inline">
		                      *产权证满5:
		                      </label>
		                    <c:forEach var="item" items="${yesNoMap}"> 
				                 <label class="checkbox-inline">
		                      		<input disabled="disabled"   name="certificateFive"  <c:if test="${item.key==oldMaster.certificateFive}">checked="checked"</c:if> type="radio" id="inlineCheckbox1" value="${item.key}">${item.value}
		                    	 </label>
				            </c:forEach>
	                    </div>
	                </div>
	                 <div class="form-group">
		                <label for="loupantype" class="col-sm-2 control-label">唯一住房:</label>
		                <div class="col-sm-6">
		                    <c:forEach var="item" items="${yesNoMap}"> 
				                 <label class="checkbox-inline">
		                      		<input disabled="disabled"   name="houseOnly"  <c:if test="${item.key==oldMaster.houseOnly}">checked="checked"</c:if> type="radio" id="inlineCheckbox1" value="${item.key}">${item.value}
		                    	 </label>
				            </c:forEach>
		                </div>
					</div>
					 <div class="form-group">
		                <label  class="col-sm-2 control-label">*售价</label>
		                <div class="col-sm-9">
		                  <input readonly="readonly"  name="price" id="priceId" value="${oldMaster.price}" type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required">万元
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*首付</label>
		                <div class="col-sm-9">
		                  <input  readonly="readonly" name="payments"  value="${oldMaster.payments}"  type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required">万元
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*均价</label>
		                <div class="col-sm-9">
		                  <input readonly="readonly"  name="unitPrice" id="unitPriceId" value="${oldMaster.unitPrice}"  type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required">元/平米
		                </div>
		            </div>
		            
		               <div class="form-group">
		                <label  class="col-sm-2 control-label">业主信息</label>
		                <div class="col-sm-10">
		                  业主姓名：<input readonly="readonly"  name="ownerName" id="buildAreaId" value="${oldMaster.ownerName}"   type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required">
		                  业主电话：<input readonly="readonly"  name="ownerPhone" value="${oldMaster.ownerPhone}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="required">
		                </div>
		            </div>
		        </div>
		      </div>
		      
		      <div class="row">
				 <div class="col-xs-8 col-xs-offset-2 font16">详情信息</div>
			 </div>
		     <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label for="loupantype" class="col-sm-2 control-label">房源特色:${oldMaster.features}</label>
		                <div class="col-sm-10">
		                
		                  
		                <c:forEach var="item" items="${featuresMap}"> 
				                <label class="checkbox-inline">
		                      <input disabled="disabled"  type="checkbox" onclick="featuresNum4(this)" 
		                      <c:if test='${fn:contains(",".concat(oldMaster.features.concat(",")), ",".concat(item.key.concat(",")))}'>checked="checked"</c:if> value="${item.key}" 
		                      name="features" > ${item.value}
		                    </label>
				         </c:forEach>
		                    
		                </div>
		            </div>
		            
		            
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*核心卖点</label>
		                <div class="col-sm-6">
		                  <input readonly="readonly"  name="sellingPoint" value="${oldMaster.sellingPoint}" type="text" class="form-control" placeholder="核心卖点" data-validate="required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">*推荐标语</label>
		                <div class="col-sm-6">
		                  <input readonly="readonly"  name="title"  value="${oldMaster.title}" type="text" class="form-control" placeholder="推荐标语" data-validate="required">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">房源详情</label>
		                <div class="col-sm-6">
		                  <textarea readonly="readonly"  name="houseDetail" class="form-control" >${oldMaster.houseDetail}</textarea>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">业主心态</label>
		                <div class="col-sm-6">
		                  <textarea readonly="readonly" name="owneMentality" class="form-control" >${oldMaster.owneMentality}</textarea>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">小区配套</label>
		                <div class="col-sm-6">
		                  <textarea readonly="readonly" name="villageMatching" class="form-control" >${oldMaster.villageMatching}</textarea>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">服务介绍</label>
		                <div class="col-sm-6">
		                  <textarea readonly="readonly" name="serviceIntro" class="form-control" >${oldMaster.serviceIntro}</textarea>
		                </div>
		            </div>
		            
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">经纪人</label>
		                <div class="col-sm-6">
		                  <select readonly="readonly"  id="agentSelectId" class="form-control dsj-inline " name="userId" class="js-example-basic-multiple" >
 										<c:if test="${agent.id!=null}"><option value="${agent.id}">${agent.text}</option></c:if>
						 </select>
		                </div>
		            </div>
		               <div class="text-center">
					    
					           <button class="btn btn-default" type="button" onclick="goList()">取消</button>
					    </div>
		         </div>
		      </div>
		     </form>
	  </div>
	 
	</div>
</div>
<script>
function goList(){
	history.go(-1);
}


</script>

