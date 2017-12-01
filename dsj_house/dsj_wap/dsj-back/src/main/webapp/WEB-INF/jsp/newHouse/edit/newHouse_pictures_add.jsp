<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head ">
		<div class="row">
			<div class="form-bootstrapWizard">
					<ul class="bootstrapWizard form-wizard">
						<li  data-target="#step1"  class="active">
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
							<div class="row">
								<div class="col-xs-12">
									<a id="try" style="margin-left: 0px;" class="dsj_btn btn dsj_btn_green" onclick="openCommonModal('${ctx }/back/newHouse/edit/newHouse_pictures_add_begin',1200,12);">添加图片</a>
								</div>
							</div>
							<div class="row mt20">
							<c:forEach var="item" items="${countList}"> 
		                      	<div class="col-xs-3">
									<div class="panel panel-default dsj_photo dsj_photo_xc">
										<div class="panel-body">
											<a href="${ctx }/back/frame/newHouse/edit/newHouse_pictures_detail?newHouseId=${newHouseId }&pictureStatus=${item.id}">
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
		                     <button class="btn btn-primary" type="button" onclick="toBackUpdateHouse(${newHouseCode })">上一步</button>
		                </div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="ImageModalContent" style="display: none;">
	<div class="modal-header">
           <div class="row">
          	 <h4 class="modal-title">添加图片描述</h4>
	    	</div>
      </div>
      <div class="modal-body">
		<div class="col-xs-12 row" >
			<div >
				<div class="row">
					<form id="picturesDescribeForm" novalidate="novalidate"  class="col-xs-8 col-xs-offset-2" onsubmit="return false;">
						<div id="bootstrap-wizard-1">
							<div class="tab-content mt30">
								<div class="tab-pane active">
									<div class="row mt20"  id="imagesList">
										
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
        <button class="btn btn-primary" type="button" onclick="savePicture()">提交</button>
    </div>
</div>
<script type="text/javascript" src="${ctx }/static/front/js/webuploader.js"></script>
<script>
function toAddHouseType(id){
	var flag = false;
	$("span[countAttr='count']").each(function(){
		if($(this).text()>0){
			flag=true;
			return false;
		}
	})
	if(flag){
		$("#popup_box").show();
		location=_ctx+"/back/frame/newHouse/edit/newHouse_houseTypeList?newHouseId="+id;
	}else{
		setErrorContent("请至少添加一张图片!");
	}
}
function toBackUpdateHouse(id){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_update?id="+id;
}
</script>