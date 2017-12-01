<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<ol class="breadcrumb">
			<li>租房房源</li>
			<li>查看</li>
		</ol>
		<div class="row" style="margin: 20px;">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active">
					<a href="${ctx}/back/frame/rentHouse/general/origin/to_origin_add?id=${rentOrigin.id}">房源信息</a>
				</li>
				<li role="presentation">
					<c:if test="${ empty rentOrigin.id }">
						<a >房源图片</a>
					</c:if>
					<c:if test="${ not empty rentOrigin.id }">
						<c:if test="${empty showOrigin }">
							<a href="${ctx}/back/frame/rentHouse/general/origin/to_image_list?id=${rentOrigin.id}">房源图片</a>
						</c:if>
						<c:if test="${not empty showOrigin }">
							<a href="${ctx}/back/frame/rentHouse/general/origin/to_image_list?show=yes&id=${rentOrigin.id}">房源图片</a>
						</c:if>
					</c:if>
				</li>
			</ul>
			<form class="form-horizontal" id="addForm">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 font16">
						<i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 基本信息
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2">
						<div style="display: none">
							<input id="id" name="id" value="${rentOrigin.id}" />
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*小区名称</label>
							<div class="col-sm-6">
								<select readonly="readonly" class="form-control dsj-inline " name="dicId" class="js-example-basic-multiple">
									<c:if test="${dic.id!=null}">
										<option value="${dic.id}">${dic.sprayName}</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*租房类型</label>
							<div class="col-sm-6">
								<c:choose>
									<c:when test="${empty rentOrigin.rentType }">
										<input name="rentType" readonly="readonly" type="radio" checked="checked" value="1">整租
										<input name="rentType" readonly="readonly" type="radio" value="2">合租
									</c:when>
									<c:otherwise>
										<input name="rentType" readonly="readonly" <c:if test="${rentOrigin.rentType eq 1}">checked="checked"</c:if> type="radio" value="1">整租
										<input name="rentType" readonly="readonly" <c:if test="${rentOrigin.rentType eq 2}">checked="checked"</c:if>type="radio" value="2">合租
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*房屋户型：</label>
							<div class="col-sm-10">
								<select name="door" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${roomMap}">
										<option <c:if test="${item.key==rentOrigin.door}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>室 
								<select name="hall" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${roomMap}">
										<option <c:if test="${item.key==rentOrigin.hall}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>厅 
								<select name="toilet" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${roomMap}">
										<option <c:if test="${item.key==rentOrigin.toilet}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>卫 
								<select name="kitchen" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${roomMap}">
										<option <c:if test="${item.key==rentOrigin.kitchen}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>厨
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">房屋情况：</label>
							<div class="col-sm-10">
								房屋类型：<select name="wyType" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${houseTypeMap}">
										<option <c:if test="${item.key==rentOrigin.wyType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select> 
								装修状况：<select name="zxType" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${renvationMap}">
										<option <c:if test="${item.key==rentOrigin.zxType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select> 
								朝向：<select name="orientation" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${orientationsMap}">
										<option <c:if test="${item.key==rentOrigin.orientation}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*楼层</label>
							<div class="col-sm-6">
								<input name="houseFloor" readonly="readonly" value="${rentOrigin.houseFloor}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="isNumber,required">楼 
							\ 共<input name="totalFloors" readonly="readonly" value="${rentOrigin.totalFloors}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="isNumber,required">层
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*房屋面积</label>
							<div class="col-sm-10">
								<input name="area" readonly="readonly" value="${rentOrigin.area}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="required">
							</div>
						</div>
						<div class="form-group" id="genderType">
							<label class="col-sm-2 control-label">*性别限制</label>
							<div class="col-sm-10">
								<select name="genderType" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${sexTypeMap}">
										<option <c:if test="${item.key eq rentOrigin.genderType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">房屋配置:</label>
							<div class="col-sm-10">
								<c:forEach var="item" items="${houseDeployMap}">
									<label class="checkbox-inline">
										<input type="checkbox" readonly="readonly" name="detailPoint" onclick="validateName('detailPoint')" 
											<c:if test='${fn:contains(",".concat(rentOrigin.detailPoint.concat(",")), ",".concat(item.key.concat(",")))}'>checked="checked"</c:if> 
											value="${item.key}">${item.value}
									</label>
								</c:forEach>
								<input type="text" id="detailPointValidate" style="width: 0px; border: 0px;" >
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
							<label class="col-sm-2 control-label">*租金</label>
							<div class="col-sm-9">
								<input name="rentPrice" readonly="readonly"  value="${rentOrigin.rentPrice}" type="text" class="form-control dsj-inline dsj-width-1" data-validate="isPrice,required">元/月
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*付款方式</label>
							<div class="col-sm-9">
								<select name="payType" readonly="readonly" class="form-control dsj-inline dsj-width-1">
									<c:forEach var="item" items="${payTypeMap}">
										<option <c:if test="${item.key eq rentOrigin.payType}">selected="selected"</c:if> value="${item.key}">${item.value}</option>
									</c:forEach>
								</select> 
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
							<label class="col-sm-2 control-label">*房源标题</label>
							<div class="col-sm-6">
								<input name="houseTitle" readonly="readonly" value="${rentOrigin.houseTitle}" type="text" class="form-control" placeholder="推荐标语" data-validate="required">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">房源描述</label>
							<div class="col-sm-6">
								<textarea name="houseDesc" readonly="readonly" class="form-control">${rentOrigin.houseDesc}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">*房源维护人</label>
							<div class="col-sm-6">
								<select readonly="readonly" class="form-control dsj-inline " name="agentUserSelect" class="js-example-basic-multiple"  data-validate="required">
									<c:if test="${agentVo.userId!=null}">
										<option value="${agentVo.userId}">${agentVo.name}-${agentVo.tellPhone}-${agentVo.companyName}</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="text-center">
							<c:if test="${empty showOrigin }">
								<button class="btn btn-primary" type="button" onclick="saveOrUpdate('yes')">下一步</button>
								<button class="btn btn-primary" type="button" onclick="saveOrUpdate()">保存</button>
								<button class="btn btn-default" type="button" onclick="cancel()">取消</button>
							</c:if>
							<c:if test="${not empty showOrigin }">
								<button class="btn btn-default" type="button" onclick="cancel()">返回</button>
							</c:if>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="${ctx}/static/back/rentHouse/general/origin/origin_add.js"></script>
