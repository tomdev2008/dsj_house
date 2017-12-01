<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
			<div class="white-head">
				<div class="row">
				<div class="form-bootstrapWizard">
						<ul class="bootstrapWizard form-wizard">
							<li  data-target="#step1"  class="active">
								<span class="step">1</span> <span class="title">基本信息</span>
							</li>
							<li data-target="#step2">
								<span class="step">2</span> <span class="title">楼盘相册</span>
							</li>
							<li data-target="#step3">
								<span class="step">3</span> <span class="title">楼盘户型</span>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
				<form class="form-horizontal" id="newHouseForm">
					<div style="display: none;" id="propertyRightDemoDiv">
					  	<select class="form-control dsj-inline" style="width:100px" id="propertyRightDemo"  data-validate="required">
					  		<option value="">请选择</option>
						  	 <c:forEach var="item" items="${propertyRightMap}"> 
						  	 		<option value="${item.key}">${item.value}</option>
		                      </c:forEach>
	                    </select>
					</div>
					<div style="display: none;" id="subwayDemoDiv">
							<div class="col-sm-10 col-sm-offset-2 delete">
			                	<span class="wenzi6">地铁:</span>
								<select id="line" class="form-control dsj-inline dsj-width-1 sub" onchange="getLine(0)">
										<option value="">请选择</option>
										<c:forEach items="${subwayInitList }" var="subway">
											<option value="${subway.id }">${subway.name }</option>
										</c:forEach>
								</select>
								<span class="wenzi6">站点:</span>
								<select id="station" class="form-control dsj-inline dsj-width-1">
										<option value="">请选择</option>
								</select>
			                  <input type="text" id="distance" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isNumber" maxlength="15">
			                </div>
						</div>
					<input type="hidden" name="id" id="houseId" value="${vo.id }">
					<input type="hidden" name="edit"  value="${vo.edit }">
				    <div class="row" style="margin-top:50px;">
				        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 基本信息</div>
				    </div>
				    <div class="row">
				        <div class="col-xs-8 col-xs-offset-2">
				            <div class="form-group">
				                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘名称:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="name" placeholder="楼盘名称" value="${vo.name }" maxlength="20">
				                </div>
				            </div>
				             <div class="form-group">
				                <label for="loupanname" class="col-sm-2 control-label">楼盘编码:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="code" placeholder="楼盘编码" value="${vo.code }" readonly="readonly">
				                </div>
				            </div>
				             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">楼盘户型:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" placeholder="户型添加完成后自动显示" readonly="readonly" value="${vo.housetypeNames }">
						                </div>
						            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">楼盘特点:</label>
				                <div class="col-sm-10">
				                    <c:forEach var="item" items="${dicTraitMap}"> 
			                      	 <label class="checkbox-inline">
										<c:choose>
											<c:when test='${fn:contains(",".concat(vo.dicTrait.concat(",")), ",".concat(item.key.concat(",")))}'>
												 <input checked="checked" type="checkbox" name="dicTrait" value="${item.key}" onclick="setDirectoryName(this,'${item.value}','dicTraitName')"> ${item.value}
												 <input type="hidden" name="dicTraitName" value="${item.value}">
											</c:when>
											<c:otherwise>
												 <input type="checkbox" name="dicTrait" value="${item.key}" onclick="setDirectoryName(this,'${item.value}','dicTraitName')"> ${item.value}
											</c:otherwise>
										</c:choose>	 
				                    </label>
			                      </c:forEach>
			                      <input type="text" id="dicTraitValidate"  style="width: 0px; border: 0px;">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>物业类型:</label>
				                <div class="col-sm-10" >
				                    <c:forEach var="item" items="${wyTypeMap}"> 
			                      	 <label class="checkbox-inline">
			                      	 <c:choose>
											<c:when test='${fn:contains(",".concat(vo.wyType.concat(",")), ",".concat(item.key.concat(",")))}'>
												 <input type="checkbox" checked="checked"  name="wyType" onclick="selectWyType(this,'${item.value}')" value="${item.key}" wyName='${item.value}'>${item.value}
												 <input type="hidden" name="wyTypeName" value="${item.value}">
											</c:when>
											<c:otherwise>
												 <input type="checkbox"  name="wyType" onclick="selectWyType(this,'${item.value}')" value="${item.key}" wyName='${item.value}'>${item.value}
											</c:otherwise>
										</c:choose>	 
				                    </label>
			                      </c:forEach>
			                      <input type="text" id="wyTypeNameValidate"  style="width: 0px; border: 0px;" data-validate="required" value="yes">
				                </div>
				            </div>
				            <div class="form-group form-inline" id="bigReferencePriceDiv">
				                <label for="loupantype" class="col-sm-2 control-label">参考单价:</label>
						             <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
						             	<c:if test="${wyMsgStatus.index==0}">
						                    <div class="col-sm-10" id="${wyMsg.wyType }smallReferencePriceDiv">
													<span class="dsj_span">${wyMsg.wyTypeName }</span>
													<input type="text" class="w20 form-control" id="${wyMsg.wyType }referencePriceMin" value="${wyMsg.referencePriceMin }" placeholder="最低单价 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)" > ~
													<input type="text" class="w20 form-control" id="${wyMsg.wyType }referencePriceMax" value="${wyMsg.referencePriceMax }" placeholder="最高单价 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)">
											</div>
					                     </c:if>
										<c:if test="${wyMsgStatus.index!=0}">
											<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallReferencePriceDiv">
												<span class="dsj_span">${wyMsg.wyTypeName }</span>
												<input type="text" class="w20 form-control" id="${wyMsg.wyType }referencePriceMin" value="${wyMsg.referencePriceMin }" placeholder="最低单价 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)" > ~
												<input type="text" class="w20 form-control" id="${wyMsg.wyType }referencePriceMax" value="${wyMsg.referencePriceMax }" placeholder="最高单价 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)">
											</div>
					                     </c:if>
					                 </c:forEach>
				            </div>
				            <div class="form-group form-inline" id="bigTotalPriceDiv">
				                <label for="loupantype" class="col-sm-2 control-label">楼盘总价:</label>
				                 <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
				                 <c:if test="${wyMsgStatus.index==0}">
				                 			<div class="col-sm-10" id="${wyMsg.wyType }smallTotalPriceDiv">
												<span class="dsj_span">${wyMsg.wyTypeName }</span>
												<input type="text" class="w20 form-control" id="${wyMsg.wyType }totalPriceMin" value="${wyMsg.totalPriceMin }" placeholder="最低总价 (万元/套)" data-validate="isPrice,maxNumber(99999)"> ~
												<input type="text" class="w20 form-control" id="${wyMsg.wyType }totalPriceMax" value="${wyMsg.totalPriceMax }" placeholder="最高总价 (万元/套)" data-validate="isPrice,maxNumber(99999)">
											</div>
					                     </c:if>
										<c:if test="${wyMsgStatus.index!=0}">
											<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallTotalPriceDiv">
												<span class="dsj_span">${wyMsg.wyTypeName }</span>
												<input type="text" class="w20 form-control" id="${wyMsg.wyType }totalPriceMin" value="${wyMsg.totalPriceMin }" placeholder="最低总价  (万元/套)" data-validate="isPrice,maxNumber(99999)"> ~
												<input type="text" class="w20 form-control" id="${wyMsg.wyType }totalPriceMax" value="${wyMsg.totalPriceMax }" placeholder="最高总价 (万元/套)" data-validate="isPrice,maxNumber(99999)">
											</div>
					                     </c:if>
					                 </c:forEach>
				            </div>
				            <div class="form-group form-inline">
				                <label for="loupantype" class="col-sm-2 control-label">周边单价:</label>
				                <div class="col-sm-10">
				                    <label class="sr-only" for="exampleInputEmail3">0</label>
				                    <input type="text" name="aroundMinPrice" class="form-control" value="${vo.aroundMinPrice }" placeholder="最低价格 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)"> ~
				                    <input type="text" name="aroundMaxPrice" class="form-control" value="${vo.aroundMaxPrice }" placeholder="最低价格 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>开发商:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="developers" placeholder="开发商" value="${vo.developers }" data-validate="required" maxlength="50">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘地址:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="address"  placeholder="楼盘地址" value="${vo.address }" maxlength="50" data-validate="required">
				                </div>
				            </div>
				            <div class="form-group form-inline">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘坐标:</label>
				                <div class="col-sm-10">
				                  <a class="btn btn-default" target="_bank" href="http://api.map.baidu.com/lbsapi/getpoint/index.html">获取坐标</a>
				                  <input type="text" class="form-control" name="coordinate"  placeholder="楼盘坐标" value="${vo.accuracy },${vo.dimension }" data-validate="required,lngLat">
				                 </div>
				            </div>
				            <div class="form-group">
										<label class="col-sm-2 control-label"><span style="color:red">*</span>区域商圈:</label>
										<div class="col-sm-10">
											<span class="wenzi6">环线:</span>
											<select id="lineNum" class="form-control dsj-inline dsj-width-1" name="lineNum">
												<option value="">请选择</option>
												<option value="1" ${1==vo.lineNum?'selected="selected"':'' }>三环以内</option>
												<option value="2" ${2==vo.lineNum?'selected="selected"':'' }>三环至四环</option>
												<option value="3" ${3==vo.lineNum?'selected="selected"':'' }>四环至五环</option>
												<option value="4" ${4==vo.lineNum?'selected="selected"':'' }>五环至六环</option>
												<option value="5" ${5==vo.lineNum?'selected="selected"':'' }>六环以外</option>
											</select>
											<span class="wenzi6">省:</span>
											<select id="areaOneId" class="form-control dsj-inline dsj-width-1" name="areaLevalOne" onchange="getTwoArea()">
												<option value="">请选择</option>
												<c:forEach items="${firstAreaList }" var="area">
													<option value="${area.areaCode }" ${area.areaCode==vo.areaLevalOne?'selected="selected"':'' }>${area.name }</option>
												</c:forEach>
											</select>
											<span class="wenzi6">市:</span>
											<select id="areaTwoId" class="form-control dsj-inline dsj-width-1" name="areaLevalTwo" onchange="getThreeArea()">
														<option value="">请选择</option>
													<c:forEach items="${twoAreaList }" var="area">
														<option value="${area.areaCode }" ${area.areaCode==vo.areaLevalTwo?'selected="selected"':'' }>${area.name }</option>
													</c:forEach>
											</select>
											<span class="wenzi6">区:</span>
											<select id="areaThreeId" class="form-control dsj-inline dsj-width-1" name="areaLevalThree" onchange="getFourArea()">
														<option value="">请选择</option>
														<c:forEach items="${threeAreaList }" var="area">
															<option value="${area.areaCode }" ${area.areaCode==vo.areaLevalThree?'selected="selected"':'' }>${area.name }</option>
														</c:forEach>
											</select>
											<span class="wenzi6">商圈:</span>
											<select id="areaFourId" class="form-control dsj-inline dsj-width-1" name="tradingArea">
													<option value="">请选择</option>
													<c:forEach items="${tradeAreaList }" var="area">
															<option value="${area.areaCode }" ${area.areaCode==vo.tradingArea?'selected="selected"':'' }>${area.name }</option>
														</c:forEach>
											</select>
											<input type="text" id="areaValidate" style="width: 0px; border: 0px;" data-validate="required">
											<input id="areaName1" type="hidden" name="areaLevalOneName" />
											<input id="areaName2" type="hidden" name="areaLevalTwoName" />
											<input id="areaName3" type="hidden" name="areaLevalThreeName" />
											<input id="tradingAreaName" type="hidden" name="tradingAreaName" />
											<input id="lineNumName" type="hidden" name="lineNumName" />
										</div>
									</div>
									
									<div class="form-group form-inline" id="subwayDiv">
						                <label class="col-sm-2 control-label">地铁站点:</label>
						                <!-- 没有地铁 -->
						                 <c:if test="${empty vo.subWayList}">
						                   <div class="col-sm-10">
							                	<span class="wenzi6">地铁:</span>
												<select id="line0" class="form-control dsj-inline dsj-width-1 sub" onchange="getLine(0)">
													<option value="">请选择</option>
													<c:forEach items="${subwayInitList }" var="subway">
														<option value="${subway.id }">${subway.name }</option>
													</c:forEach>
												</select>
												<span class="wenzi6">站点:</span>
												<select id="station0" class="form-control dsj-inline dsj-width-1">
													<option value="">请选择</option>
												</select>
							                  <input type="text" id="distance0" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isNumber">
							                  <a href="javascript:void(0)" onclick="addSubway()">添加</a> 
							                  <a href="javascript:void(0)" onclick="removeSubway()">删除</a>
							                </div>
						                  </c:if>
						                
						                <!-- 有地铁 -->
						                <c:forEach items="${vo.subWayList }" var="subways" varStatus="status">
						                <c:if test="${status.index==0}">
							                <div class="col-sm-10">
							                	<span class="wenzi6">地铁:</span>
												<select id="line${status.index}" class="form-control dsj-inline dsj-width-1 sub" onchange="getLine(${status.index})">
													<option value="">请选择</option>
													<c:forEach items="${subwayInitList }" var="subway">
														<option value="${subway.id }"   <c:if test="${subways.line==subway.id}">selected='selected'</c:if>>${subway.name }</option>
													</c:forEach>
												</select>
												<span class="wenzi6">站点:</span>
												<select id="station${status.index}" class="form-control dsj-inline dsj-width-1">
													<option value="">请选择</option>
													<c:forEach items="${subways.stationList }" var="station">
														<option value="${station.id }" <c:if test="${subways.station==station.id}">selected='selected'</c:if>>${station.name }</option>
													</c:forEach>
												</select>
							                  <input type="text" id="distance${status.index}" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isNumber" value="${subways.distance}" maxlength="15">
							                  <a href="javascript:void(0)" onclick="addSubway()">添加</a> 
							                  <a href="javascript:void(0)" onclick="removeSubway()">删除</a>
							                </div>
							                </c:if>
							                <c:if test="${status.index!=0}"> 
							                 <div class="col-sm-10 col-sm-offset-2 delete">
							                	<span class="wenzi6">地铁:</span>
												<select id="line${status.index}" class="form-control dsj-inline dsj-width-1 sub" onchange="getLine(${status.index})">
													<option value="">请选择</option>
													<c:forEach items="${subwayInitList }" var="subway">
														<option value="${subway.id }" <c:if test="${subways.line==subway.id}">selected='selected'</c:if>>${subway.name }</option>
													</c:forEach>
												</select>
												<span class="wenzi6">站点:</span>
												<select id="station${status.index}" class="form-control dsj-inline dsj-width-1">
													<option value="">请选择</option>
													<c:forEach items="${subways.stationList }" var="station">
														<option value="${station.id }" <c:if test="${subways.station==station.id}">selected='selected'</c:if>>${station.name }</option>
													</c:forEach>
												</select>
							                  <input type="text" id="distance${status.index}" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isNumber" value="${subways.distance}">
							                </div>
							                </c:if>
						                 </c:forEach>
						            </div>
						           
						            
				            <div class="form-group form-inline" id="phoneDiv">
				                <label class="col-sm-2 control-label"><span style="color:red">*</span>售楼处电话:</label>
				                 <c:forEach items="${vo.phoneList}" var="phone" varStatus="status">
				                 	<c:if test="${status.index==0}">
							                 	<div class="col-sm-10 add">
										                  <input type="text" class="w58 form-control" name="phone" placeholder="售楼处电话" value="${phone }" data-validate="required,isPhoneAndMobile">
										                  <a href="javascript:void(0)" onclick="addPhone()">添加</a> 
										                  <a href="javascript:void(0)" onclick="removePhone()">删除</a>
							                  </div>
					             	</c:if>
					             	<c:if test="${status.index!=0}"><div class="col-sm-10 col-sm-offset-2 delete"><input type="text" value="${phone }" name="phone" class="w58 form-control" placeholder="售楼处电话" data-validate="required,isPhoneAndMobile"></div></c:if>
					             </c:forEach>
					             <c:if test="${vo.phoneList==null}">
				              			<div class="col-sm-10 add">
									                  <input type="text" class="w58 form-control" name="phone" placeholder="售楼处电话" data-validate="required,isPhoneAndMobile">
									                  <a href="javascript:void(0)" onclick="addPhone()">添加</a> 
									                  <a href="javascript:void(0)" onclick="removePhone()">删除</a>
						                  </div>
					            </c:if> 
				            </div>
				        </div>
				    </div>
				    <div class="row">
				        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 销售信息</div>
				    </div>
				    <div class="row">
				        <div class="col-xs-8 col-xs-offset-2">
				            <div class="form-group form-inline" id="bigPaymentsDiv">
				                <label class="col-sm-2 control-label">最低首付:</label>
				                <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
				                 <c:if test="${wyMsgStatus.index==0}">
			                 			<div class="col-sm-10" id="${wyMsg.wyType }smallPaymentsDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<input type="text" class="form-control" id="${wyMsg.wyType }payments" value="${wyMsg.payments }" placeholder="最低首付 (万)" data-validate="isPrice,maxNumber(9999)">
										</div>
				                     </c:if>
									<c:if test="${wyMsgStatus.index!=0}">
										<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallPaymentsDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<input type="text" class="form-control" id="${wyMsg.wyType }payments" value="${wyMsg.payments }" placeholder="最低首付 (万)" data-validate="isPrice,maxNumber(9999)">
										</div>
				                     </c:if>
				                 </c:forEach>
				            </div>
				            <div class="form-group form-inline" id="bigMonthPayDiv">
				                <label class="col-sm-2 control-label">月供:</label>
				                <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
				                 <c:if test="${wyMsgStatus.index==0}">
			                 			<div class="col-sm-10" id="${wyMsg.wyType }smallMonthPayDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<input type="text" class="form-control" id="${wyMsg.wyType }monthPay" value="${wyMsg.monthPay }" placeholder="最低月供(元)" data-validate="isPrice">
										</div>
				                     </c:if>
									<c:if test="${wyMsgStatus.index!=0}">
										<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallMonthPayDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<input type="text" class="form-control" id="${wyMsg.wyType }monthPay" value="${wyMsg.monthPay }" placeholder="最低月供(元)" data-validate="isPrice">
										</div>
				                     </c:if>
				                 </c:forEach>
				            </div>
				            <div class="form-group form-inline" id="discountDiv">
				                <label for="loupantype" class="col-sm-2 control-label">楼盘优惠:</label>
				                <c:forEach items="${vo.discountList}" var="discount" varStatus="status">
				                <c:if test="${status.index==0}"><div class="col-sm-10 add">
					                  <input type="text" class="w58 form-control" name="discount"  placeholder="楼盘优惠" value="${discount }">
					                  <a href="javascript:void(0)" onclick="addDiscount()">添加</a> 
					                  <a href="javascript:void(0)" onclick="removeDiscount()">删除</a>
					                 </div>
			            		</c:if>
			            		<c:if test="${status.index!=0}">
			            			<div class="col-sm-10 col-sm-offset-2 delete"><input type="text" value="${discount }" name="discount" class="w58 form-control" placeholder="楼盘优惠"></div>
			            		</c:if>
					             </c:forEach>
					              <c:if test="${vo.discountList==null}">
					              <div class="col-sm-10 add">
					                  <input type="text" class="w58 form-control" name="discount"  placeholder="楼盘优惠" value="">
					                  <a href="javascript:void(0)" onclick="addDiscount()">添加</a> 
					                  <a href="javascript:void(0)" onclick="removeDiscount()">删除</a>
					                 </div>
					            </c:if> 
				            </div>
				             <div class="form-group form-inline">
			                	<label for="loupantype" class="col-sm-2 control-label">抓取的开盘时间:</label>
				                  <input type="text" class="w58 form-control" readonly="readonly" value="${vo.openDate }">
				            </div>
				            <div class="form-group form-inline" id="bigOpenTimeDiv">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>开盘时间:</label>
				               	 <c:forEach items="${vo.openTimeList}" var="openHandTimeList" varStatus="openHandTimeStatus1">
				                 	<c:forEach items="${openHandTimeList}" var="openHandTime" varStatus="openHandTimeStatus">
				                		<c:if test="${openHandTimeStatus.index==0}">
					                    	<div <c:if test="${openHandTimeStatus1.index==0}"> class="col-sm-10 ${openHandTime.wyType }smallOpenTimeDiv"  </c:if><c:if test="${openHandTimeStatus1.index!=0}"> class="col-sm-10 col-sm-offset-2 delete ${openHandTime.wyType }smallOpenTimeDiv"  </c:if> id="${openHandTime.wyType }smallOpenTimeDiv">
												<span class="dsj_span">${openHandTime.wyTypeName }</span>
												<input type="text" class="w20 form-control" placeholder="楼栋" id="${openHandTime.wyType }openTimeContent${openHandTimeStatus.index}" value="${openHandTime.describes }"> ~
												<input type="text" class="w20 form-control laydate-icon layDateClass" value="<fmt:formatDate value="${openHandTime.openHandTime }"  pattern="yyyy-MM-dd"/>"   id="${openHandTime.wyType }openTime${openHandTimeStatus.index}" placeholder="开盘时间">
											<a href="javascript:void(0)" onclick="addOpenTime(${openHandTime.wyType },'${openHandTime.wyTypeName }',this)"> 添加 </a><a href="javascript:void(0)" onclick="removeOpenTime(${openHandTime.wyType })">删除</a>
											</div>
										</c:if>
										<c:if test="${openHandTimeStatus.index!=0}">
											<div class="col-sm-10 col-sm-offset-2 delete ${openHandTime.wyType }smallOpenTimeDiv" id="${openHandTime.wyType }smallOpenTimeDiv">
												<span class="dsj_span">${openHandTime.wyTypeName }</span>
												<input type="text" class="w20 form-control" placeholder="楼栋" id="${openHandTime.wyType }openTimeContent${openHandTimeStatus.index}" value="${openHandTime.describes }"> ~
												<input type="text" class="w20 form-control laydate-icon layDateClass" value="<fmt:formatDate value="${openHandTime.openHandTime }"  pattern="yyyy-MM-dd"/>" id="${openHandTime.wyType }openTime${openHandTimeStatus.index}" placeholder="开盘时间">
											</div>
					                     </c:if>
					                  </c:forEach>   
					                 </c:forEach>
					          </div>
			                 <div class="form-group form-inline">
			                	<label for="loupantype" class="col-sm-2 control-label">抓取的交房时间:</label>
				                  <input type="text" class="w58 form-control" readonly="readonly" value="${vo.handDate }">
				            </div>
				            <div class="form-group form-inline" id="bigHandTimeDiv">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>交房时间:</label>
				                 <c:forEach items="${vo.handTimeList}" var="openHandTimeList" varStatus="openHandTimeStatus1">
				                 <c:forEach items="${openHandTimeList}" var="openHandTime" varStatus="openHandTimeStatus">
					                	<c:if test="${openHandTimeStatus.index==0}">
					                    	<div <c:if test="${openHandTimeStatus1.index==0}"> class="col-sm-10 ${openHandTime.wyType }smallHandTimeDiv"  </c:if><c:if test="${openHandTimeStatus1.index!=0}"> class="col-sm-10 col-sm-offset-2 delete ${openHandTime.wyType }smallHandTimeDiv"  </c:if> id="${openHandTime.wyType }smallHandTimeDiv">
												<span class="dsj_span">${openHandTime.wyTypeName }</span>
												<input type="text" class="w20 form-control" placeholder="楼栋" id="${openHandTime.wyType }handTimeContent${openHandTimeStatus.index}" value="${openHandTime.describes }"> ~
												<input type="text" class="w20 form-control laydate-icon layDateClass" value="<fmt:formatDate value="${openHandTime.openHandTime }"  pattern="yyyy-MM-dd"/>" id="${openHandTime.wyType }handTime${openHandTimeStatus.index}" placeholder="开盘时间">
												<a href="javascript:void(0)" onclick="addHandTime(${openHandTime.wyType },'${openHandTime.wyTypeName }',this)"> 添加 </a><a href="javascript:void(0)" onclick="removeHandTime(${openHandTime.wyType })">删除</a>
											</div>
					                     </c:if>
										<c:if test="${openHandTimeStatus.index!=0}">
											<div class="col-sm-10 col-sm-offset-2 delete ${openHandTime.wyType }smallHandTimeDiv" id="${openHandTime.wyType }smallHandTimeDiv">
												<span class="dsj_span">${openHandTime.wyTypeName }</span>
												<input type="text" class="w20 form-control" placeholder="楼栋" id="${openHandTime.wyType }handTimeContent${openHandTimeStatus.index}" value="${openHandTime.describes }"> ~
												<input type="text" class="w20 form-control laydate-icon layDateClass" value="<fmt:formatDate value="${openHandTime.openHandTime }"  pattern="yyyy-MM-dd"/>" id="${openHandTime.wyType }handTime${openHandTimeStatus.index}" placeholder="开盘时间">
											</div>
					                     </c:if>
					                  </c:forEach>
					                 </c:forEach>
				            </div>
				            <div class="form-group form-inline">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>售楼处地址:</label>
				                <div class="col-sm-10">
				                  <input type="text" class="w58 form-control" name="saleAddress"  placeholder="售楼处地址"  value="${vo.saleAddress }" data-validate="max(50)">
				                  </div>
				            </div>
				            <div class="form-group form-inline" id="prepDiv">
				                <label for="loupantype" class="col-sm-2 control-label">预售许可证:</label>
				                <c:forEach items="${vo.prepList}" var="prep" varStatus="status">
					                <c:if test="${status.index==0}">
					                <div class="col-sm-10">
						                  <input type="text" class="w58 form-control" name="prep" placeholder="预售许可证" value="${prep }" data-validate="max(40)">
						                   <a href="javascript:void(0)" onclick="addPrep()">添加</a> 
						                  <a href="javascript:void(0)" onclick="removePrep()">删除</a>
					                  </div>
				                  </c:if>
				                  	<c:if test="${status.index!=0}">
				                  		<div class="col-sm-10 col-sm-offset-2 delete"><input type="text" value="${prep }" name="prep" class="w58 form-control" placeholder="预售许可证"></div>
				                  	</c:if>
					             </c:forEach>
					             <c:if test="${vo.prepList==null}">
					              <div class="col-sm-10">
						                  <input type="text" class="w58 form-control" name="prep" placeholder="预售许可证" >
						                   <a href="javascript:void(0)" onclick="addPrep()">添加</a> 
						                  <a href="javascript:void(0)" onclick="removePrep()">删除</a>
					                  </div>
					             </c:if>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>销售状态:</label>
				                <div class="col-sm-6">
				                	<label class="checkbox-inline">
				                      <input type="radio" name="status" value="1" ${vo.status==1?'checked="checked"':'' }> 待售
				                    </label>
				                    <label class="checkbox-inline">
				                      <input type="radio" name="status" value="2" ${vo.status==2?'checked="checked"':'' }>在售
				                    </label>
				                    <label class="checkbox-inline">
				                      <input type="radio" name="status" value="3" ${vo.status==3?'checked="checked"':'' }> 售完
				                    </label>
				                </div>
				            </div>
				        </div>
				    </div>
				    <div class="row">
				        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 小区情况</div>
				    </div>
				    <div class="row">
				        <div class="col-xs-8 col-xs-offset-2">
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">建筑类型:</label>
				                <div class="col-sm-10">
				                    <c:forEach var="item" items="${achTypeMap}"> 
				                      	 <label class="checkbox-inline">
				                      	 <c:choose>
											<c:when test='${fn:contains(",".concat(vo.achType.concat(",")), ",".concat(item.key.concat(",")))}'>
												 <input checked="checked" type="checkbox" name="achType" value="${item.key}" onclick="setDirectoryName(this,'${item.value}','achTypeName')"> ${item.value}
												  <input type="hidden" name="achTypeName" value="${item.value}">
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="achType" value="${item.key}" onclick="setDirectoryName(this,'${item.value}','achTypeName')"> ${item.value}
											</c:otherwise>
										</c:choose>	
					                      	  
					                    </label>
				                      </c:forEach>
				                </div>
				            </div>
				            <div class="form-group form-inline" id="bigPropertyRightDiv">
				                <label for="loupanid" class="col-sm-2 control-label"><span style="color:red">*</span>产权年限:</label>
				                 <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
				                 <c:if test="${wyMsgStatus.index==0}">
			                 			<div class="col-sm-10" id="${wyMsg.wyType }smallPropertyRightDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<select class="form-control dsj-inline" style="width:100px" id="${wyMsg.wyType }propertyRight"  data-validate="required">
												<option value="">请选择</option>
											  	 <c:forEach var="item" items="${propertyRightMap}"> 
											  	 		<option ${item.key==wyMsg.propertyRight?'selected="selected"':''} value="${item.key}">${item.value}</option>
							                      </c:forEach>
						                    </select>
										</div>
				                     </c:if>
									<c:if test="${wyMsgStatus.index!=0}">
										<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallPropertyRightDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<select class="form-control dsj-inline" style="width:100px" id="${wyMsg.wyType }propertyRight">
												<option value="">请选择</option>
											  	 <c:forEach var="item" items="${propertyRightMap}"> 
											  	 		<option ${item.key==wyMsg.propertyRight?'selected="selected"':''} value="${item.key}">${item.value}</option>
							                      </c:forEach>
						                    </select>
										</div>
				                     </c:if>
				                 </c:forEach>
				            </div>
				            <div class="form-group form-inline" id="bigPlotRatioDiv">
				                <label for="loupanname" class="col-sm-2 control-label">容积率:</label>
				                <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
				                	<c:if test="${wyMsgStatus.index==0}">
					                    	<div class="col-sm-10" id="${wyMsg.wyType }smallPlotRatioDiv">
												<span class="dsj_span">${wyMsg.wyTypeName }</span>
												<input type="text" class="form-control" id="${wyMsg.wyType }plotRatio" placeholder="容积率" value="${wyMsg.plotRatio }" data-validate="max(50)">
											</div>
					                     </c:if>
										<c:if test="${wyMsgStatus.index!=0}">
											<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallPlotRatioDiv">
												<span class="dsj_span">${wyMsg.wyTypeName }</span>
												<input type="text" class="form-control" id="${wyMsg.wyType }plotRatio" placeholder="容积率" value="${wyMsg.plotRatio }" data-validate="max(50)">
											</div>
					                     </c:if>
					                 </c:forEach>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">绿化率:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="greenRate" placeholder="绿化率" value="${vo.greenRate }" data-validate="max(50)">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">规划户数:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="houseCount" placeholder="规划户数" value="${vo.houseCount }" >
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">楼层状况:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="floorMsg" placeholder="楼层状况" value="${vo.floorMsg }" data-validate="max(150)">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">栋座:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="banMsg" placeholder="栋座" value="${vo.banMsg }" data-validate="max(100)">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">工程进度:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="schedule" placeholder="工程进度" value="${vo.schedule }" data-validate="max(50)">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">物业公司:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="property" placeholder="物业公司" value="${vo.property }" data-validate="max(50)">
				                </div>
				            </div>
				            <div class="form-group form-inline" id="bigPropertyFeeDiv">
				                <label for="loupantype" class="col-sm-2 control-label">物业管理费:</label>
				                <c:forEach items="${vo.wyMsgList}" var="wyMsg" varStatus="wyMsgStatus">
				                 <c:if test="${wyMsgStatus.index==0}">
			                 			<div class="col-sm-10" id="${wyMsg.wyType }smallPropertyFeeDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<input type="text" class="form-control" id="${wyMsg.wyType }propertyFee" value="${wyMsg.propertyFee }" placeholder="物业管理费 (元/m²/月)" data-validate="isPrice">
										</div>
				                     </c:if>
									<c:if test="${wyMsgStatus.index!=0}">
										<div class="col-sm-10 col-sm-offset-2 delete" id="${wyMsg.wyType }smallPropertyFeeDiv">
											<span class="dsj_span">${wyMsg.wyTypeName }</span>
											<input type="text" class="form-control" id="${wyMsg.wyType }propertyFee" value="${wyMsg.propertyFee }" placeholder="物业管理费 (元/m²/月)" data-validate="isPrice">
										</div>
				                     </c:if>
				                 </c:forEach>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">车位数:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="parkNumber" placeholder="车位数" value="${vo.parkNumber }" data-validate="max(50)">
				                </div>
				            </div>
				            <div class="form-group">
				                <label for="loupantype" class="col-sm-2 control-label">车位比:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="parkingSpace"  placeholder="车位比" value="${vo.parkingSpace }" data-validate="max(10)">
				                </div>
				            </div>
				             <div class="text-center">
			                     <button class="btn btn-primary" type="button" onclick="saveAddNewHouse(1)">下一步</button>
			                     <c:if test="${vo.isTure!=2 }">
			                     	<button class="btn btn-primary" type="button" onclick="saveAddNewHouse(2)">保存</button>
			                     </c:if>
			                     <button class="btn btn-default" type="button" onclick="cancelFun()">返回</button>
			                </div>
				        </div>
				    </div>
				</form>
			</div>
	</div>
</div>
<script src="${ctx}/static/back/newHouse/edit/newHouse_add.js"></script>
<script>
$(function(){
	 setLayDate();
})
</script>