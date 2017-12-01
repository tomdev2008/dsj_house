<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head ">
		<div class="row">
			<div class="form-bootstrapWizard">
					<ul class="bootstrapWizard form-wizard">
						<li  data-target="#step1" class="active">
							<span class="step">1</span> <span class="title">基本信息</span>
						</li>
						<li data-target="#step2"   class="active">
							<span class="step">2</span> <span class="title">楼盘相册</span>
						</li>
						<li data-target="#step3">
							<span class="step">3</span> <span class="title">楼盘户型</span>
						</li>
					</ul>
					<div class="clearfix"></div>
				</div>
			<form novalidate="novalidate"  class="col-xs-8 col-xs-offset-2" style="margin-top:50px;">
			<input type="hidden" id="newHouseId" value="${newHouseId }">
			<input type="hidden" id="pictureStatus" value="">
			<input type="hidden" id="pictureStatusName" value="">
				<div class="bootstrap-wizard-1">
					<div class="tab-content mt30">
						<div class="tab-pane active">
							<div class="row mt20">
							<c:forEach var="item" items="${countList}"> 
		                      	<div class="col-xs-3">
									<div class="panel panel-default dsj_photo dsj_photo_xc">
										<div class="panel-body">
											<a href="${ctx }/back/frame/newHouse/edit/newHouse_pictures_detail_check?newHouseId=${newHouseId }&pictureStatus=${item.id}">
												<c:if test="${item.pictureUrl==null}">
													<img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${ctx}/static/front/images/newHouse/default.jpg">
												</c:if>
												<c:if test="${item.pictureUrl!=null}">
													<img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${item.pictureUrl}">
												</c:if>
											</a>
											<p class="mt10">
												<span>${item.typegroupname }</span>
												<span class="pull-right" countAttr="count">${item.count }</span>
											</p>
										</div>
									</div>
								</div>
		                      </c:forEach>
							</div>
						</div>
						<div class="text-center">
		                     <button class="btn btn-primary" type="button" onclick="toAddHouseType(${newHouseId })">下一步</button>
		                </div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
function toAddHouseType(id){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_check_houseTypeList?newHouseId="+$("#newHouseId").val();
}
</script>