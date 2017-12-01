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
				<div class="col-xs-12 row" style="margin-top:50px;">
					<input type="hidden" id="newHouseId" value="${newHouseId }">
					<input type="hidden" id="pictureStatus" value="${pictureStatus }">
					<input type="hidden" id="pictureStatusName" value="">
					<div class="white-content">
						<div class="row">
							<form id="wizard-1" novalidate="novalidate"  class="col-xs-8 col-xs-offset-2">
								<div id="bootstrap-wizard-1">
									<div class="tab-content mt30">
										<div class="tab-pane active">
											<div class="row">
											</div>
											<span></span>
											<div class="row mt20">
											<c:forEach var="item" items="${list}"> 
						                      	<div class="col-xs-3">
													<div class="panel panel-default dsj_photo">
														<div class="panel-body">
															<div class="dsj_img_wraper">
																<img class="img-rounded img-responsive" alt="Responsive image" src="${item.pictureUrl}">
															</div>
					 										<p class="mt10" onclick="setDescribes(this)">
																<span>图片描述:${item.describes}</span>
																<span style="display: none;">图片描述:<input onblur="updatePictureDescribes(this)" type="text" style="width:100%;color:black" idVal="${item.id}" value="${item.describes }"></span>
															</p>
															<p class="text-right">
																${item.realname }&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate type="date" value="${item.createTime }" />
															</p>
														</div>
													</div>
													
												</div>
						                      </c:forEach>
											</div>
										</div>
										<div class="text-center">
						                     <button class="btn btn-primary" type="button" onclick="cancelFun()">返回相册</button>
						                </div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
function cancelFun(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_pictures_check?newHouseId="+$("#newHouseId").val();
}
</script>