<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		
		<ol class="breadcrumb">
			<li>二手房房源</li>
			<li>新增 </li>
		</ol>
	
		<div class="row" style="margin: 20px;">
						  <!-- Nav tabs -->
						  <ul class="nav nav-tabs" role="tablist">
						    <li role="presentation" class="active">
						    	<a href="${ctx}/back/frame/oldHouse/master/master_add?id=${oldMaster.id}" >房源信息</a>
						    </li>
						    <li role="presentation">
						    	<c:if test="${ empty oldMaster.id }">
									<a >房源图片</a>
								</c:if>
								<c:if test="${ not empty oldMaster.id }">
									<a  href="${ctx}/back/frame/oldHouse/master/master_image_list?id=${oldMaster.id}" >房源图片</a>
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
		                  <input name="recordNo" value="${oldMaster.recordNo}"  type="text" class="form-control" placeholder="备案编号" data-validate="maxLength50">
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
		                  <select id="dicSelectId" class="form-control dsj-inline " name="dicId" class="js-example-basic-multiple" data-validate="required" >
 								<c:if test="${dic.id!=null}"><option value="${dic.id}">${dic.sprayName}</option></c:if>
						 </select>
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">*房屋户型：</label>
		              	     <div class="col-sm-10">
			                	<select   name="room"  class="form-control dsj-inline dsj-width-1" >
				                      <option value="">请选择</option>
				                      <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.room}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>室
	
			                    <select   name="hall"  class="form-control dsj-inline dsj-width-1">
				                      <option value="">请选择</option>
				                         <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.hall}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>厅
			                    <select   name="toilet" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">请选择</option>
				                       <c:forEach var="item" items="${roomMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.toilet}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>卫
			                    <select   name="kitchen"  class="form-control dsj-inline dsj-width-1" >
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
			                	<select   name="houseType" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">房屋类型</option>
				                      <c:forEach var="item" items="${houseTypeMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.houseType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
	
			                    <select   name="renovation" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">装修状况</option>
				                      <c:forEach var="item" items="${renvationMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.renovation}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
			                    <select   name="orientations"  class="form-control dsj-inline dsj-width-1">
				                      <option value="">朝向</option>
				                       <c:forEach var="item" items="${orientationsMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.orientations}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
			                   
		                    </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">*楼层</label>
		                <div class="col-sm-8">
		                  <input name="floor" id="floor_num" value="${oldMaster.floor}" type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required,lt(floor_num_total)">楼
		                  共<input name="floorNum" id="floor_num_total" value="${oldMaster.floorNum}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="required,gt(floor_num)">层
		                 楼层类型
		                 	<select  name="floorType"  class="form-control dsj-inline dsj-width-1" >
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
		              	     	 <input name="roomNumber1"  value="${oldMaster.roomNumber1Cell }"  type="text" class="form-control dsj-inline dsj-width-1" data-min="1" data-max="20"  data-validate="lengthMixMax">
			                	<select  name="roomNumber1Cell"  class="form-control dsj-inline dsj-width-1" >
				                       <c:forEach var="item" items="${roomNo1Map}"> 
				                      	<option <c:if test="${item.key==oldMaster.roomNumber1Cell}">selected="selected"</c:if>  value="${item.key}">${item.value}</option>
				                      </c:forEach>
				                      
			                    </select>
								 <input name="roomNumber2"  value="${oldMaster.roomNumber2Cell }" type="text" class="form-control dsj-inline dsj-width-1" data-min="1" data-max="20"  data-validate="lengthMixMax">
			                    <select   name="roomNumber2Cell" class="form-control dsj-inline dsj-width-1">
				                       <c:forEach var="item" items="${roomNo2Map}"> 
				                      	<option <c:if test="${item.key==oldMaster.roomNumber2Cell}">selected="selected"</c:if>  value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
			                    <input name="roomNumber" value="${oldMaster.roomNumber}"  type="text" class="form-control dsj-inline dsj-width-1" data-min="1" data-max="20"  data-validate="lengthMixMax"/>室
			                   
		                    </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*建筑面积</label>
		                <div class="col-sm-10">
		                  <input name="buildArea" onblur="setUnitPrice()" id="buildAreaId" value="<fmt:formatNumber type="number" value="${oldMaster.buildArea}" pattern="#.##"/>"   type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required,isNumber9999">平米
		                  *建筑年代<input name="buildYear" value="${oldMaster.buildYear}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="required,isNumber9999">年  填写4位数字，如：2017
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">产权：</label>
		              	     <div class="col-sm-10">
			                	<select   name="certificate"  class="form-control dsj-inline dsj-width-1" >
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
	                    <label  class="col-sm-2 control-label">是否有钥匙:</label>
	                    <div class="col-sm-7">
	                    	 <c:forEach var="item" items="${yesNoMap}"> 
				                  <label class="checkbox-inline">
		                      		<input name="certificateTwo"  <c:if test="${item.key==oldMaster.isKey}">checked="checked"</c:if> type="radio" id="inlineCheckbox1" value="${item.key}">${item.value}
		                    	 </label>
				            </c:forEach>
		                 
	                    </div>
	                </div>
	                <div class="form-group">
		              	    <label class="col-sm-2 control-label">房龄类型：</label>
		              	     <div class="col-sm-10">
			                	<select   name="certificateType" class="form-control dsj-inline dsj-width-1" >
				                      <option value="">房龄类型</option>
				                      <c:forEach var="item" items="${certificateTypeMap}"> 
				                      	<option <c:if test="${item.key==oldMaster.certificateType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
							</div>
		            </div>
	                 <div class="form-group">
		                <label for="loupantype" class="col-sm-2 control-label">唯一住房:</label>
		                <div class="col-sm-6">
		                    <c:forEach var="item" items="${yesNoMap}"> 
				                 <label class="checkbox-inline">
		                      		<input name="houseOnly"  <c:if test="${item.key==oldMaster.houseOnly}">checked="checked"</c:if> type="radio" id="inlineCheckbox1" value="${item.key}">${item.value}
		                    	 </label>
				            </c:forEach>
		                </div>
					</div>
					
					 <div class="form-group">
		                <label  class="col-sm-2 control-label">*售价</label>
		                <div class="col-sm-9">
		                  <input name="price" onblur="setUnitPrice()" id="priceId" value="<fmt:formatNumber type="number" value="${oldMaster.price}" pattern="#.##"/>" type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required,maxNumber(99999)">万元
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*首付</label>
		                <div class="col-sm-9">
		                  <input name="payments"  value="<fmt:formatNumber type="number" value="${oldMaster.payments}" pattern="#.##"/>"  type="text" class="form-control dsj-inline dsj-width-1"  data-validate="maxNumber(9999)">万元
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*均价</label>
		                <div class="col-sm-9">
		                  <input name="unitPrice" id="unitPriceId" value="<fmt:formatNumber type="number" value="${oldMaster.unitPrice}" pattern="#.##"/>"  type="text" class="form-control dsj-inline dsj-width-1"  data-validate="required,maxNumber(999999)">元
		                </div>
		            </div>
		            
		               <div class="form-group">
		                <label  class="col-sm-2 control-label">业主信息</label>
		                <div class="col-sm-10">
		                  业主姓名：<input name="ownerName" id="buildAreaId" value="${oldMaster.ownerName}"   type="text" class="form-control dsj-inline dsj-width-1" data-min="1" data-max="50"  data-validate="lengthMixMax" >
		                  业主电话：<input name="ownerPhone" value="${oldMaster.ownerPhone}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="isPhoneAndMobile">
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
		                <label for="loupantype" class="col-sm-2 control-label">房源特色:</label>
		                <div class="col-sm-10">
		                
		                <c:forEach var="item" items="${featuresMap}"> 
				                <label class="checkbox-inline">
		                      <input type="checkbox" onclick="featuresNum4(this)" 
		                      <c:if test='${fn:contains(",".concat(oldMaster.features.concat(",")), ",".concat(item.key.concat(",")))}'>checked="checked"</c:if> value="${item.key}" 
		                      name="features" > ${item.value}
		                    </label>
				         </c:forEach>
		                    
		                </div>
		            </div>
		            
		            
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*核心卖点</label>
		                <div class="col-sm-6">
		                  <input name="sellingPoint" value="${oldMaster.sellingPoint}" type="text" class="form-control" placeholder="核心卖点"  data-max="25" data-min="10" data-validate="required,lengthMixMax">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">*推荐标语</label>
		                <div class="col-sm-6">
		                  <input name="title"  value="${oldMaster.title}" type="text" class="form-control" placeholder="推荐标语" data-max="50" data-min="10" data-validate="required,lengthMixMax">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">房源详情</label>
		                <div class="col-sm-6">
		                  <textarea name="houseDetail" class="form-control" maxlength="200" data-max="200" data-validate="lengthMax">${oldMaster.houseDetail}</textarea>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">业主心态</label>
		                <div class="col-sm-6">
		                  <textarea name="owneMentality" class="form-control" maxlength="200" data-max="200" data-validate="lengthMax">${oldMaster.owneMentality}</textarea>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">小区配套</label>
		                <div class="col-sm-6">
		                  <textarea name="villageMatching" class="form-control" maxlength="200"  data-max="200" data-validate="lengthMax">${oldMaster.villageMatching}</textarea>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">服务介绍</label>
		                <div class="col-sm-6">
		                  <textarea name="serviceIntro" class="form-control" maxlength="200" data-max="200" data-validate="lengthMax">${oldMaster.serviceIntro}</textarea>
		                </div>
		            </div>
		            
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">经纪人</label>
		                <div class="col-sm-6">
		                  <select id="agentSelectId" class="form-control dsj-inline " name="userId" class="js-example-basic-multiple" >
 										<c:if test="${agent.id!=null}"><option value="${agent.id}">${agent.text}</option></c:if>
						 </select>
		                </div>
		            </div>
		               <div class="text-center">
					           <button class="btn btn-primary" type="button" onclick="saveOrUpdateNext()">下一步</button>
					           <button class="btn btn-primary" type="button" onclick="saveOrUpdate()">保存</button>
					           <button class="btn btn-default" type="button" onclick="goList()">取消</button>
					    </div>
		         </div>
		      </div>
		     </form>
	  </div>
	 
	</div>
</div>
<script src="${ctx}/static/back/oldHouse/master/master_add.js"></script>
<script src="${ctx}/static/back/lib/validate/verify.notify.js"></script>
<script src="${ctx}/static/back/lib/validate/verify.notify.ext.js"></script>


