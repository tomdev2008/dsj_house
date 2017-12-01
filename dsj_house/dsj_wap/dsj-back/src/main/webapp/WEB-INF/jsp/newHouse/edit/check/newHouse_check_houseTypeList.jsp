<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head">
		<div class="row">
			<div class="form-bootstrapWizard">
					<ul class="bootstrapWizard form-wizard">
						<li  data-target="#step1" class="active">
							<span class="step">1</span> <span class="title">基本信息</span>
						</li>
						<li data-target="#step2" class="active">
							<span class="step">2</span> <span class="title">楼盘相册</span>
						</li>
						<li data-target="#step3"  class="active">
							<span class="step">3</span> <span class="title">楼盘户型</span>
						</li>
					</ul>
					<div class="clearfix"></div>
			</div>
			<form class="form-horizontal dsj_house_tab_form" novalidate="novalidate" style="margin-top:50px;">
					<div class="col-xs-12 mt10">
							<input type="hidden" id="newHouseId" value="${newHouseId}"/>
	                       	<ul class="nav nav-tabs" role="tablist">
							    <li role="presentation" class="active"><a href="#layout1" aria-controls="#layout1" role="tab" data-toggle="tab">1室(${count1})</a></li>
							    <li role="presentation" ><a href="#layout2" aria-controls="#layout2" role="tab" data-toggle="tab">2室(${count2})</a></li>
							    <li role="presentation" ><a href="#layout3" aria-controls="#layout3" role="tab" data-toggle="tab">3室(${count3})</a></li>
							    <li role="presentation" ><a href="#layout4" aria-controls="#layout4" role="tab" data-toggle="tab">4室(${count4})</a></li>
							    <li role="presentation" ><a href="#layout5" aria-controls="#layout5" role="tab" data-toggle="tab">5室及以上(${count5})</a></li>
							  </ul>
							  <input type="hidden" id="totalCount" value="${totalCount}"/>
							  <!-- Tab panes -->
							  <div class="tab-content clearfix mt10">
							    <div role="tabpanel" class="tab-pane active" id="layout1">
							           <c:if test="${houseTypeList1 != null}">
							             <c:forEach items="${houseTypeList1}" var="layoutType">
							               <div class="col-xs-3">
														<div class="panel panel-default dsj_photo">
															<div class="panel-body dsj_img_wraper">
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id });"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span >
																	 <c:if test="${layoutType.wyType == 1}">
																	   住宅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 2}">
																	   独栋别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 3}">
																	   联排别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 4}">
																	   叠拼别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 5}">
																	  商业
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 6}">
																	   办公
																	 </c:if>
																	</span>
																	<span class="pull-right">${layoutType.room}室${layoutType.hall}厅${layoutType.toilet}卫${layoutType.kitchen}厨</span>
																</p>
																<p class="mt10">
																	<span>建筑面积:${layoutType.builtUp}</span>
														            <span class="pull-right">
														            <c:if test="${layoutType.houseStatus==1}">
														                               主推
														            </c:if>
														            <c:if test="${layoutType.houseStatus==2}">
														                              待售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==3}">
														                               在售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==4}">
														                               售完
														            </c:if>
														            </span>
																</p>
															</div>
														</div>
											</div>
							             </c:forEach>
							           </c:if>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="layout2">
							    <c:if test="${houseTypeList2 != null}">
							             <c:forEach items="${houseTypeList2}" var="layoutType">
							                <div class="col-xs-3">
														<div class="panel panel-default dsj_photo">
															<div class="panel-body dsj_img_wraper">
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id });"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span >
																	 <c:if test="${layoutType.wyType == 1}">
																	   住宅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 2}">
																	   独栋别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 3}">
																	   联排别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 4}">
																	   叠拼别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 5}">
																	  商业
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 6}">
																	   办公
																	 </c:if>
																	</span>
																	<span class="pull-right">${layoutType.room}室${layoutType.hall}厅${layoutType.toilet}卫${layoutType.kitchen}厨</span>
																</p>
																<p class="mt10">
																	<span>建筑面积:${layoutType.builtUp}</span>
														            <span class="pull-right">
														            <c:if test="${layoutType.houseStatus==1}">
														                               主推
														            </c:if>
														            <c:if test="${layoutType.houseStatus==2}">
														                              待售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==3}">
														                               在售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==4}">
														                               售完
														            </c:if>
														            </span>
																</p>
															</div>
														</div>
											</div>
							             </c:forEach>
							           </c:if>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="layout3">
							    <c:if test="${houseTypeList3 != null}">
							             <c:forEach items="${houseTypeList3}" var="layoutType">
							                <div class="col-xs-3">
														<div class="panel panel-default dsj_photo">
															<div class="panel-body dsj_img_wraper">
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id });"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span>
																	 <c:if test="${layoutType.wyType == 1}">
																	   住宅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 2}">
																	   独栋别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 3}">
																	   联排别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 4}">
																	   叠拼别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 5}">
																	  商业
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 6}">
																	   办公
																	 </c:if>
																	</span>
																	<span class="pull-right">${layoutType.room}室${layoutType.hall}厅${layoutType.toilet}卫${layoutType.kitchen}厨</span>
																</p>
																<p class="mt10">
																	<span>建筑面积:${layoutType.builtUp}</span>
														            <span class="pull-right">
														            <c:if test="${layoutType.houseStatus==1}">
														                               主推
														            </c:if>
														            <c:if test="${layoutType.houseStatus==2}">
														                              待售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==3}">
														                               在售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==4}">
														                               售完
														            </c:if>
														            </span>
																</p>
															</div>
														</div>
											</div>
							             </c:forEach>
							           </c:if>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="layout4">
							        <c:if test="${houseTypeList4 != null}">
							             <c:forEach items="${houseTypeList4}" var="layoutType">
							                <div class="col-xs-3">
														<div class="panel panel-default dsj_photo">
															<div class="panel-body dsj_img_wraper">
									        <a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id });"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"/></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span>
																	 <c:if test="${layoutType.wyType == 1}">
																	   住宅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 2}">
																	   独栋别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 3}">
																	   联排别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 4}">
																	   叠拼别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 5}">
																	  商业
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 6}">
																	   办公
																	 </c:if>
																	</span>
																	<span class="pull-right">${layoutType.room}室${layoutType.hall}厅${layoutType.toilet}卫${layoutType.kitchen}厨</span>
																</p>
																<p class="mt10">
																	<span>建筑面积:${layoutType.builtUp}</span>
														            <span class="pull-right">
														            <c:if test="${layoutType.houseStatus==1}">
														                               主推
														            </c:if>
														            <c:if test="${layoutType.houseStatus==2}">
														                              待售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==3}">
														                               在售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==4}">
														                               售完
														            </c:if>
														            </span>
																</p>
															</div>
														</div>
											</div>
							             </c:forEach>
							           </c:if>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="layout5">
							    <c:if test="${houseTypeList5 != null}">
							             <c:forEach items="${houseTypeList5}" var="layoutType">
							                <div class="col-xs-3">
														<div class="panel panel-default dsj_photo">
															<div class="panel-body dsj_img_wraper">
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id});"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span>
																	 <c:if test="${layoutType.wyType == 1}">
																	   住宅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 2}">
																	   独栋别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 3}">
																	   联排别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 4}">
																	   叠拼别墅
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 5}">
																	  商业
																	 </c:if>
																	 <c:if test="${layoutType.wyType == 6}">
																	   办公
																	 </c:if>
																	</span>
																	<span class="pull-right">${layoutType.room}室${layoutType.hall}厅${layoutType.toilet}卫${layoutType.kitchen}厨</span>
																</p>
																<p class="mt10">
																	<span>建筑面积:${layoutType.builtUp}</span>
														            <span class="pull-right">
														            <c:if test="${layoutType.houseStatus==1}">
														                               主推
														            </c:if>
														            <c:if test="${layoutType.houseStatus==2}">
														                              待售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==3}">
														                               在售
														            </c:if>
														            <c:if test="${layoutType.houseStatus==4}">
														                               售完
														            </c:if>
														            </span>
																</p>
															</div>
														</div>
											</div>
							             </c:forEach>
							           </c:if>
							    </div>
							  </div>
	                </div>
                    <div class="col-xs-4 col-xs-offset-4 text-center dsj_bottom_button">
                    <button type="button" class="btn btn-default" onclick="cancelLayout()">返回</button>
                   </div>
	                </form>
	        </div>
	 </div>
</div>


<script>
function cancelLayout(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/";
}
function findOneLayOut(param){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_check_newHouseType?id="+param;
}
</script>