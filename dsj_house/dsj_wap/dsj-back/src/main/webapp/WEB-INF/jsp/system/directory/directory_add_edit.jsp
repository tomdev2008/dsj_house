<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<ol class="breadcrumb">
			<li>二手房楼盘字典</li>
			<li>编辑</li>
		</ol>
		<div class="row" style="margin: 20px;">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active">
					<a href="${ctx}/back/frame/houseDirectory/to_add_edit?id=${oldDirectory.id}">房源信息</a>
				</li>
				<li role="presentation" >
					<c:if test="${ empty oldDirectory.id }">
						<a >房源图片</a>
					</c:if>
					<c:if test="${ not empty oldDirectory.id }">
						<a href="${ctx}/back/frame/houseDirectory/to_image_list/${oldDirectory.id}">房源图片</a>
					</c:if>
				</li>
			</ul>
			<div style="display: none;" id="subwayDemoDiv">
				<div class="col-sm-10 col-sm-offset-2 delete">
	               	<span class="wenzi6">地铁:</span>
					<select id="line" name="line" class="form-control dsj-inline dsj-width-1" onchange="getLine()">
						<option value="">请选择</option>
						<c:forEach items="${subwayList }" var="subway">
							<option value="${subway.id}" >${subway.name}</option>
						</c:forEach>
					</select>
					<span class="wenzi6">站点:</span>
					<select id="station" name="station" class="form-control dsj-inline dsj-width-1">
						<option value="">请选择</option>
					</select>
	                 <input type="text" id="distance" name="distance" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isPrice,required">
	                 <input type="text" id="subbwayValidate" style="width: 0px; border: 0px;" data-validate="required">
	             </div>
			</div>	
			<form class="form-horizontal" id="addEditForm">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 font16">
						<i class="fa fa-caret-right blue-font" aria-hidden="true"></i>楼盘信息
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2">
						<div style="display: none">
							<input id="oldHouseId" name="id" value="${oldDirectory.id}" />
							<input id="subwayNum" value="${subwayNum}"/>
							<input id="subwayStr" name="subwayStr" value="${subwayStr}"/>
						</div>
						<%-- <div class="form-group">
							<label class="col-sm-2 control-label">证载楼盘名称:</label>
							<div class="col-sm-6">
								<input name="cardName" value="${oldDirectory.cardName}" placeholder="证载楼盘名称" type="text" class="form-control" >
							</div>
						</div> --%>
						<div class="form-group">
							<label class="col-sm-2 control-label">*区域商圈:</label>
							<div class="col-sm-10">
								<span class="wenzi6">省:</span>
								<select id="areaOneId" class="form-control dsj-inline dsj-width-1" name="areaCode1" onchange="getTwoArea()">
									<option value="">请选择</option>
									<c:forEach items="${firstAreaList }" var="area">
										<option value="${area.areaCode }" ${area.areaCode==oldDirectory.areaCode1?'selected="selected"':'' }>${area.name }</option>
									</c:forEach>
								</select>
								<span class="wenzi6">市:</span>
								<select id="areaTwoId" class="form-control dsj-inline dsj-width-1" name="areaCode2" onchange="getThreeArea()">
									<c:choose>
										<c:when test="${empty oldDirectory.areaCode2}">
											<option value="">请选择</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${twoAreaList }" var="area">
												<option value="${area.areaCode}" ${area.areaCode==oldDirectory.areaCode2?'selected="selected"':'' }>${area.name }</option>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
								<span class="wenzi6">区:</span>
								<select id="areaThreeId" class="form-control dsj-inline dsj-width-1" name="areaCode3" onchange="getFourArea()">
									<c:choose>
										<c:when test="${empty oldDirectory.areaCode3}">
											<option value="">请选择</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${threeAreaList }" var="area">
												<option value="${area.areaCode}" ${area.areaCode==oldDirectory.areaCode3?'selected="selected"':'' }>${area.name }</option>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
								<span class="wenzi6">商圈:</span>
								<select id="areaFourId" class="form-control dsj-inline dsj-width-1" name="tradingAreaId">
									<c:choose>
										<c:when test="${empty oldDirectory.tradingAreaId}">
											<option value="">请选择</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${fourAreaList }" var="area">
												<option value="${area.areaCode}" ${area.areaCode==oldDirectory.tradingAreaId?'selected="selected"':'' }>${area.name }</option>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
								<input type="text" id="areaValidate" style="width: 0px; border: 0px;" data-validate="required">
								<input id="areaName1" type="hidden" name="areaName1" />
								<input id="areaName2" type="hidden" name="areaName2" />
								<input id="areaName3" type="hidden" name="areaName3" />
								<input id="tradingAreaName" type="hidden" name="tradingAreaName" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*楼盘字典:</label>
							<div class="col-sm-6">
								<input name="sprayName" value="${oldDirectory.sprayName}" placeholder="喷涂楼盘名称" type="text" class="form-control" data-validate="required" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">推广名称1:</label>
							<div class="col-sm-6">
								<input name="extend1" value="${oldDirectory.extend1}" placeholder="推广名称1" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">推广名称2:</label>
							<div class="col-sm-6">
								<input name="extend2" value="${oldDirectory.extend2}" placeholder="推广名称2" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">开发商名称:</label>
							<div class="col-sm-6">
								<input name="developers" value="${oldDirectory.developers}" placeholder="开发商名称" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系电话:</label>
							<div class="col-sm-6">
								<input name="phone" value="${oldDirectory.phone}" placeholder="联系电话" type="text" class="form-control" data-validate="isPhoneAndMobile">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">楼盘简介:</label>
							<div class="col-sm-6">
								<textarea name="description" class="form-control" placeholder="200字以内" data-validate="maxLength200">${oldDirectory.description}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">售楼处地址:</label>
							<div class="col-sm-6">
								<input name="saleAddress" value="${oldDirectory.saleAddress}" placeholder="售楼处地址" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">开盘时间:</label>
							<div class="col-sm-6">
								<input name="openDate" value="${oldDirectory.openDate}" placeholder="开盘时间" class="small-input form-control laydate-icon layDateClass" id="LAY_demorange_zs" style="width: 120px;" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">交房时间:</label>
							<div class="col-sm-6">
								<input name="handDate" value="${oldDirectory.handDate}" placeholder="交房时间" class="small-input form-control laydate-icon layDateClass" id="LAY_demorange_ze" style="width: 120px;" >
							</div>
						</div>
						<div class="form-group">
							<label for="loupantype" class="col-sm-2 control-label">楼盘特点:</label>
							<div class="col-sm-10">
								<c:forEach var="item" items="${dicTraitMap}">
									<label class="checkbox-inline" style="margin-left: 10px;">
										<input type="checkbox" name="dicTrait" onclick="validateName('dicTrait')" <c:if test='${fn:contains(",".concat(oldDirectory.dicTrait.concat(",")), ",".concat(item.key.concat(",")))}'>checked="checked"</c:if> value="${item.key}">
										${item.value}
									</label>
								</c:forEach>
								<input type="text" id="dicTraitValidate" style="width: 0px; border: 0px;" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">建筑年代:</label>
							<div class="col-sm-6">
								<input name="achYear" value="${oldDirectory.achYear}" placeholder="填写4位数字，如：2017" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">产权性质:</label>
							<div class="col-sm-6">
								<select class="form-control" style="width: 100px" name="propertyRight">
									<c:forEach var="item" items="${propertyRightModeMap}">
										<option <c:if test="${item.key==oldDirectory.propertyRight}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">产权年限:</label>
							<div class="col-sm-6">
								<select class="form-control" style="width: 100px" name=certificate>
									<c:forEach var="item" items="${certificateMap}">
										<option <c:if test="${item.key==oldDirectory.certificate}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="loupantype" class="col-sm-2 control-label">物业类型:</label>
							<%-- <div class="col-sm-10">
								<c:forEach var="item" items="${wyTypeMap}">
									<label class="checkbox-inline" style="margin-left: 10px;">
										<input type="checkbox" name="wyType" onclick="validateName('wyType')" wyName='${item.value}' <c:if test='${fn:contains(",".concat(oldDirectory.wyType.concat(",")), ",".concat(item.key.concat(",")))}'>checked="checked"</c:if> value="${item.key}">${item.value}
									</label>
								</c:forEach>
								<input type="text" id="wyTypeValidate" style="width: 0px; border: 0px;" >
							</div> --%>
							<div class="col-sm-6">
								<select class="form-control" style="width: 100px" name="wyType">
									<c:forEach var="item" items="${wyTypeMap}">
										<option <c:if test="${item.key==oldDirectory.wyType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="loupantype" class="col-sm-2 control-label">建筑类型:</label>
							<%-- <div class="col-sm-10">
								<c:forEach var="item" items="${achTypeMap}">
									<label class="checkbox-inline" style="margin-left: 10px;">
										<input type="checkbox" name="achType" onclick="validateName('achType')" <c:if test='${fn:contains(",".concat(oldDirectory.achType.concat(",")), ",".concat(item.key.concat(",")))}'>checked="checked"</c:if> value="${item.key}">
										${item.value}
									</label>
								</c:forEach>
								<input type="text" id="achTypeValidate" style="width: 0px; border: 0px;" >
							</div> --%>
							<div class="col-sm-6">
								<select class="form-control" style="width: 100px" name="achType">
									<c:forEach var="item" items="${achTypeMap}">
										<option <c:if test="${item.key==oldDirectory.achType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">车位数:</label>
							<div class="col-sm-6">
								<input name="parkNumber" value="${oldDirectory.parkNumber}" placeholder="车位数" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">预售许可证:</label>
							<div class="col-sm-6">
								<input name="prep" value="${oldDirectory.prep}" placeholder="预售许可证" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">楼栋总数:</label>
							<div class="col-sm-6">
								<input name="floorNum" value="${oldDirectory.floorNum}" placeholder="楼栋总数" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">房屋总数:</label>
							<div class="col-sm-6">
								<input name="houseNum" value="${oldDirectory.houseNum}" placeholder="房屋总数" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">工程进度:</label>
							<div class="col-sm-6">
								<input name="schedule" value="${oldDirectory.schedule}" placeholder="工程进度" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">所在环线:</label>
							<div class="col-sm-10">
								<select name="loopLine" class="form-control dsj-inline dsj-width-1" >
									<option value="">请选择</option>
									<option value="1" ${oldDirectory.loopLine eq 1 ? 'selected="selected"' :'' }>三环以内</option>
									<option value="2" ${oldDirectory.loopLine eq 2 ? 'selected="selected"' :'' }>三环至四环</option>
									<option value="3" ${oldDirectory.loopLine eq 3 ? 'selected="selected"' :'' }>四环至五环</option>
									<option value="4" ${oldDirectory.loopLine eq 4 ? 'selected="selected"' :'' }>五环至六环</option>
									<option value="5" ${oldDirectory.loopLine eq 5 ? 'selected="selected"' :'' }>六环以外</option>
								</select>
							</div>
						</div>
						<div class="form-group form-inline">
							<label for="loupantype" class="col-sm-2 control-label">*楼盘坐标:</label>
							<div class="col-sm-10">
								<a class="btn btn-default" target="_bank" href="http://api.map.baidu.com/lbsapi/getpoint/index.html">获取坐标</a>
								<c:if test="${ empty oldDirectory.accuracy}">
									<input name="coordinate" value="" type="text" class="form-control" placeholder="楼盘坐标" data-validate="required,lngLat">
								</c:if>
								<c:if test="${not empty oldDirectory.accuracy}">
									<input name="coordinate" value="${oldDirectory.dimension},${oldDirectory.accuracy}" type="text" class="form-control" placeholder="楼盘坐标" data-validate="required,lngLat">
								</c:if>
							</div>
						</div>
						<div class="form-group form-inline" id="subwayDiv">
			                <label class="col-sm-2 control-label">*地铁站点:</label>
			                <c:choose>
			                	<c:when test="${ subwayNum == null || subwayNum == 0 }">
			                		<div class="col-sm-10 delete">
					                	<span class="wenzi6">地铁:</span>
										<select id="line0" name="line0" class="form-control dsj-inline dsj-width-1" onchange="getLine(0)">
											<option value="">请选择</option>
											<c:forEach items="${subwayList }" var="subway">
												<option value="${subway.id}" >${subway.name}</option>
											</c:forEach>
										</select>
										<span class="wenzi6">站点:</span>
										<select id="station0" name="station0" class="form-control dsj-inline dsj-width-1">
											<option value="">请选择</option>
										</select>
					                  <input type="text" id="distance0" name="distance0" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isPrice,required">
				                  	  <a href="javascript:void(0)" onclick="addSubway()">添加</a> 
				                  	  <a href="javascript:void(0)" onclick="removeSubway()">删除</a>
					                  <input type="text" id="subbwayValidate0" style="width: 0px; border: 0px;" data-validate="required">
					                </div>
			                	</c:when>
			                	<c:otherwise>
			                		<c:forEach var="item" items="${subList}" varStatus="status">
					                	<div class="col-sm-10 delete">
						                	<span class="wenzi6">地铁:</span>
											<select id="line${status.index}" name="line${status.index}" class="form-control dsj-inline dsj-width-1" onchange="getLine(${status.index})">
												<option value="">请选择</option>
												<c:forEach items="${subwayList }" var="subway">
													<option value="${subway.id}" <c:if test="${subway.id ==item['lineId']}" >selected="selected"</c:if> >${subway.name}</option>
												</c:forEach>
											</select>
											<span class="wenzi6">站点:</span>
											<select id="station${status.index}" name="station${status.index}" class="form-control dsj-inline dsj-width-1">
												<option value="">请选择</option>
												<c:forEach items="${item['stationList'] }" var="subway2">
													<option value="${subway2.id}" <c:if test="${subway2.id eq item['stationId']}" >selected="selected"</c:if> >${subway2.name}</option>
												</c:forEach>
											</select>
						                  <input type="text" id="distance${status.index}" name="distance${status.index}" value="${item['distance']}" class="form-control dsj-width-1" placeholder="站点距离(米)" data-validate="isPrice,required">
						                  <c:if test="${status.index eq 0}">
						                  	<a href="javascript:void(0)" onclick="addSubway()">添加</a> 
						                  	<a href="javascript:void(0)" onclick="removeSubway()">删除</a>
						                  </c:if>
						                  <input type="text" id="subbwayValidate${status.index}" style="width: 0px; border: 0px;" data-validate="required">
						                </div>
						             </c:forEach>
			                	</c:otherwise>
			                </c:choose>
			            </div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 font16">
						<i class="fa fa-caret-right" aria-hidden="true"></i>基本信息
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2">
						<div class="form-group">
							<label class="col-sm-2 control-label">物业公司:</label>
							<div class="col-sm-6">
								<input name="property" value="${oldDirectory.property}" placeholder="物业公司" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">占地面积:</label>
							<div class="col-sm-6">
								<input name="occupyArea" value="${oldDirectory.occupyArea}" placeholder="占地面积" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总建筑面积:</label>
							<div class="col-sm-6">
								<input name="builtUp" value="${oldDirectory.builtUp}" placeholder="总建筑面积(平米)" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">容积率:</label>
							<div class="col-sm-6">
								<input name="plotRatio" value="${oldDirectory.plotRatio}" placeholder="容积率" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">绿化率:</label>
							<div class="col-sm-6">
								<input name="greenRate" value="${oldDirectory.greenRate}" placeholder="绿化率" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">小区均价:</label>
							<div class="col-sm-6">
								<input name="averagePrice" value="${oldDirectory.averagePrice}" placeholder="小区均价" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">物业费:</label>
							<div class="col-sm-6">
								<input name="propertyFee" value="${oldDirectory.propertyFee}" placeholder="物业费(元/m&sup2;/月)" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">供暖方式:</label>
							<div class="col-sm-6">
								<select id="heatingMode" class="form-control dsj-inline dsj-width-1" name="heatingMode" >
									<c:forEach items="${heatingModeMap }" var="item">
										<option value="${item.key }" <c:if test="${oldDirectory.heatingMode eq item.key }">selected="selected"</c:if> >${item.value }</option>
									</c:forEach>
								</select>
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">土地使用年限:</label>
							<div class="col-sm-6">
								<input name="landYear" value="${oldDirectory.landYear}" placeholder="土地使用年限" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">地区编码:</label>
							<div class="col-sm-6">
								<input name="postcode" value="${oldDirectory.postcode}" placeholder="地区编码" type="text" class="form-control" >
							</div>
						</div>
						
						<%-- 
						<div class="form-group">
							<label class="col-sm-2 control-label">楼盘别名:</label>
							<div class="col-sm-6">
								<input name="dicAlias" value="${oldDirectory.dicAlias}" placeholder="楼盘别名" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">楼盘曾用名:</label>
							<div class="col-sm-6">
								<input name="oldName" value="${oldDirectory.oldName}" placeholder="楼盘曾用名" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">楼盘地址:</label>
							<div class="col-sm-6">
								<input name="address" value="${oldDirectory.address}" placeholder="楼盘地址" type="text" class="form-control" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">车位比:</label>
							<div class="col-sm-6">
								<input name="parkingSpace" value="${oldDirectory.parkingSpace}" placeholder="车位比" type="text" class="form-control" data-validate="isPrice">
							</div>
						</div> 
						--%>
						<div class="text-center">
							<button class="btn btn-primary" type="button" onclick="saveOrUpdateNext(1)">下一步</button>
							<button class="btn btn-primary" type="button" onclick="saveOrUpdateNext(2)">保存</button>
							<button class="btn btn-default" type="button" onclick="cancel()">取消</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="${ctx}/static/back/system/directory/directory_add.js"></script>