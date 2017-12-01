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
							  	<select class="form-control dsj-inline" style="width:100px" id="propertyRightDemo" data-validate="required">
							 		 	<option value="">请选择</option>
								  	 <c:forEach var="item" items="${propertyRightMap}"> 
								  	 		<option value="${item.key}">${item.value}</option>
				                      </c:forEach>
			                    </select>
							</div>
							
						<div style="display: none;" id="subwayDemoDiv">
							<div class="col-sm-10 col-sm-offset-2 delete">
			                	<span class="wenzi6">地铁:</span>
								<select id="line" class="form-control dsj-inline dsj-width-1" onchange="getLine(0)">
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
						
							<input type="hidden" name="id" id="houseId">
						    <div class="row" style="margin-top:50px;">
						        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 基本信息</div>
						    </div>
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2">
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘名称:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="name" placeholder="楼盘名称" data-validate="required" maxlength="20">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">楼盘编码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" id="houseCode" placeholder="保存后自动显示" value="${vo.code }" readonly="readonly">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">楼盘户型:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" placeholder="户型添加完成后自动显示" readonly="readonly">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">楼盘特点:</label>
						                <div class="col-sm-10">
						                 <c:forEach var="item" items="${dicTraitMap}"> 
					                      	 <label class="checkbox-inline" style="width:130px;margin-left: 0px;">
						                      	<input type="checkbox" name="dicTrait" value="${item.key}" onclick="setDirectoryName(this,'${item.value}','dicTraitName')"> ${item.value}
						                    </label>
					                      </c:forEach>
					                      <input type="text" id="dicTraitNameValidate"  style="width: 0px; border: 0px;">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>物业类型:</label>
						                <div class="col-sm-10" >
						                	 <c:forEach var="item" items="${wyTypeMap}"> 
					                      	 <label class="checkbox-inline">
						                      	 <input type="checkbox"  name="wyType" onclick="selectWyType(this,'${item.value}')" value="${item.key}" wyName='${item.value}'>${item.value}
						                    </label>
					                      </c:forEach>
					                      <input type="text" id="wyTypeNameValidate"  style="width: 0px; border: 0px;" data-validate="required">
						                </div>
						            </div>
						            <div class="form-group form-inline" id="bigReferencePriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">参考单价:</label>
						            
						            </div>
						            <div class="form-group form-inline" id="bigTotalPriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">楼盘总价:</label>
						                
						            </div>
						            <div class="form-group form-inline">
						                <label for="loupantype" class="col-sm-2 control-label">周边单价:</label>
						                <div class="col-sm-10">
						                    <label class="sr-only" for="exampleInputEmail3">0</label>
						                    <input type="text" id="aroundMinPrice" name="aroundMinPrice" class="form-control" placeholder="最低价格 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)"> ~
						                    <input type="text" id="aroundMaxPrice" name="aroundMaxPrice" class="form-control" placeholder="最高价格 (元/m&sup2;)" data-validate="isPrice,maxNumber(999999)">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>开发商:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="developers" maxlength="50" placeholder="开发商" data-validate="required">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘地址:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="address" maxlength="50"  placeholder="楼盘地址" data-validate="required">
						                </div>
						            </div>
						            <div class="form-group form-inline">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘坐标:</label>
						                <div class="col-sm-10">
						                  <a class="btn btn-default" target="_bank" href="http://api.map.baidu.com/lbsapi/getpoint/index.html">获取坐标</a>
						                  <input type="text" class="form-control" name="coordinate"  placeholder="楼盘坐标" data-validate="required,lngLat">
						                 </div>
						            </div>
						            <div class="form-group">
										<label class="col-sm-2 control-label"><span style="color:red">*</span>区域商圈:</label>
										<div class="col-sm-10">
											<span class="wenzi6">环线:</span>
											<select id="lineNum" class="form-control dsj-inline dsj-width-1" name="lineNum">
												<option value="">请选择</option>
												<option value="1">三环以内</option>
												<option value="2">三环至四环</option>
												<option value="3">四环至五环</option>
												<option value="4">五环至六环</option>
												<option value="5">六环以外</option>
											</select>
											<span class="wenzi6">省:</span>
											<select id="areaOneId" class="form-control dsj-inline dsj-width-1" name="areaLevalOne" onchange="getTwoArea()">
												<option value="">请选择</option>
												<c:forEach items="${firstAreaList }" var="area">
													<option value="${area.areaCode }">${area.name }</option>
												</c:forEach>
											</select>
											<span class="wenzi6">市:</span>
											<select id="areaTwoId" class="form-control dsj-inline dsj-width-1" name="areaLevalTwo" onchange="getThreeArea()">
														<option value="">请选择</option>
											</select>
											<span class="wenzi6">区:</span>
											<select id="areaThreeId" class="form-control dsj-inline dsj-width-1" name="areaLevalThree" onchange="getFourArea()">
														<option value="">请选择</option>
											</select>
											<span class="wenzi6">商圈:</span>
											<select id="areaFourId" class="form-control dsj-inline dsj-width-1" name="tradingArea">
													<option value="">请选择</option>
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
						                <div class="col-sm-10">
						                	<span class="wenzi6">地铁:</span>
											<select id="line0" class="form-control dsj-inline dsj-width-1" onchange="getLine(0)">
												<option value="">请选择</option>
												<c:forEach items="${subwayInitList }" var="subway">
													<option value="${subway.id }">${subway.name }</option>
												</c:forEach>
											</select>
											<span class="wenzi6">站点:</span>
											<select id="station0" class="form-control dsj-inline dsj-width-1">
												<option value="">请选择</option>
											</select>
						                  <input type="text" id="distance0" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isNumber" maxlength="15">
						                  <a href="javascript:void(0)" onclick="addSubway()">添加</a> 
						                  <a href="javascript:void(0)" onclick="removeSubway()">删除</a>
						                </div>
						            </div>
						            <div class="form-group form-inline" id="phoneDiv">
						                <label class="col-sm-2 control-label"><span style="color:red">*</span>售楼处电话:</label>
						                <div class="col-sm-10 add">
						                  <input type="text" class="w58 form-control" name="phone" placeholder="售楼处电话" data-validate="required,isPhoneAndMobile">
						                  <a href="javascript:void(0)" onclick="addPhone()">添加</a> 
						                  <a href="javascript:void(0)" onclick="removePhone()">删除</a>
						                </div>
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
						            </div>
						            <div class="form-group form-inline" id="bigMonthPayDiv">
						                <label class="col-sm-2 control-label">月供:</label>
						            </div>
						            <div class="form-group form-inline" id="discountDiv">
						                <label for="loupantype" class="col-sm-2 control-label">楼盘优惠:</label>
						                <div class="col-sm-10">
						                  <input type="text" class="w58 form-control" name="discount"  placeholder="楼盘优惠" maxlength="50">
						                  <a href="javascript:void(0)" onclick="addDiscount()">添加</a> 
						                  <a href="javascript:void(0)" onclick="removeDiscount()">删除</a>
						                </div>
						            </div>
						            <div class="form-group form-inline" id="bigOpenTimeDiv">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>开盘时间:</label>
						                
						            </div>
						            <div class="form-group form-inline" id="bigHandTimeDiv">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>交房时间:</label>
						                
						            </div>
						            <div class="form-group form-inline">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>售楼处地址:</label>
						                <div class="col-sm-10">
						                  <input type="text" class="w58 form-control" name="saleAddress"  placeholder="售楼处地址" data-validate="required,max(50)">
						                  </div>
						            </div>
						            <div class="form-group form-inline" id="prepDiv">
						                <label for="loupantype" class="col-sm-2 control-label">预售许可证:</label>
						                <div class="col-sm-10">
						                  <input type="text" class="w58 form-control" name="prep" placeholder="预售许可证" data-validate="max(40)">
						                   <a href="javascript:void(0)" onclick="addPrep()">添加</a> 
						                  <a href="javascript:void(0)" onclick="removePrep()">删除</a>
						                  </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label"><span style="color:red">*</span>销售状态:</label>
						                <div class="col-sm-6">
						                	<label class="checkbox-inline">
						                      <input type="radio" name="status" value="1" checked="checked"> 待售
						                    </label>
						                    <label class="checkbox-inline">
						                      <input type="radio" name="status" value="2">在售
						                    </label>
						                    <label class="checkbox-inline">
						                      <input type="radio" name="status" value="3"> 售完
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
							                      	  <input type="checkbox" name="achType" value="${item.key}" onclick="setDirectoryName(this,'${item.value}','achTypeName')"> ${item.value}
							                    </label>
						                      </c:forEach>
						                     <input type="text" id="achTypeNameValidate"  style="width: 0px; border: 0px;">
						                </div>
						            </div>
						            <div class="form-group form-inline" id="bigPropertyRightDiv">
						                <label for="loupanid" class="col-sm-2 control-label"><span style="color:red">*</span>产权年限:</label>
						            </div>
						            <div class="form-group form-inline" id="bigPlotRatioDiv">
						                <label for="loupanname" class="col-sm-2 control-label">容积率:</label>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">绿化率:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="greenRate" placeholder="绿化率" data-validate="max(50)">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">规划户数:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="houseCount" placeholder="规划户数">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">楼层状况:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="floorMsg" placeholder="楼层状况" data-validate="max(150)">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">栋座:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="banMsg" placeholder="栋座" data-validate="max(100)">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">工程进度:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="schedule" placeholder="工程进度" data-validate="max(50)">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">物业公司:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="property" placeholder="物业公司" data-validate="max(50)">
						                </div>
						            </div>
						            <div class="form-group form-inline" id="bigPropertyFeeDiv">
						                <label for="loupantype" class="col-sm-2 control-label">物业管理费:</label>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">车位数:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="parkNumber" placeholder="车位数" data-validate="max(50)">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">车位比:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="parkingSpace"  placeholder="车位比" data-validate="max(10)">
						                </div>
						            </div>
						             <div class="text-center">
					                     <button class="btn btn-primary" type="button" onclick="saveAddNewHouse(1)">下一步</button>
					                     <button class="btn btn-primary" type="button" onclick="saveAddNewHouse(2)">保存</button>
					                     <button class="btn btn-default" type="button" onclick="cancelFun()">返回</button>
					                </div>
						        </div>
						    </div>
						</form>
					</div>
					</div>
				</div>
<script src="${ctx}/static/back/newHouse/edit/newHouse_add.js"></script>