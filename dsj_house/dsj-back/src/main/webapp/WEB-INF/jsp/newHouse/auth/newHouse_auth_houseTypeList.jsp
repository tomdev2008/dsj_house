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
						<li data-target="#step2"  class="active">
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
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id },'${history}');"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span >
																	${layoutType.wyTypeName}
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
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id },'${history}');"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span >
																	 ${layoutType.wyTypeName}
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
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id },'${history}');"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span>
																	${layoutType.wyTypeName}
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
									        <a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id },'${history}');"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"/></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span>
																	 ${layoutType.wyTypeName}
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
															<a href="javascript:void(0)" onclick="findOneLayOut(${layoutType.id},'${history}');"><img class="img-rounded img-responsive" alt="Responsive image" src="${layoutType.imgUrl}"></a>
																<p class="mt10 text-center">
																	<span class="pull-left">${layoutType.houseName}</span>
																	<span>
																	${layoutType.wyTypeName}
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
                    <c:if test="${history=='no' }">
	                     <button type="button" class="btn btn-primary" onclick="auth(3)">通过</button>
	                     <button type="button" class="btn btn-primary" onclick="auth(4)">驳回</button>
	                    <button type="button" class="btn btn-default" onclick="cancelLayout('${history}')">取消</button>
                    </c:if>
                    <c:if test="${history=='yes' }">
                   		 <button type="button" class="btn btn-default" onclick="cancelLayout('${history}')">返回</button>
                    </c:if>
                   </div>
	                </form>
	        </div>
	 </div>
</div>

<div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" style="width: 560px;">
                         <div class="modal-header">
	           <div class="row">
	          	 <h4 class="modal-title" id="authTitle"></h4>
		    	</div>
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				 <form id="authForm" class="col-xs-8 col-xs-offset-2">
										 <div class="form-group">
									        <div class="col-sm-6">
									          <textarea rows="4" cols="35" name="content" id="content" maxlength="30"></textarea>
									          <input type="hidden" name="authStatus" id="authStatus">
									          <input type="hidden" name="id" value="${newHouseId}">
									          <input type="hidden" name="code" value="${code}">
									        </div>
									    </div>
									</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				        <button class="btn btn-primary" type="button" onclick="saveAuth()">提交</button>
				        <button class="btn btn-default" data-dismiss="modal">取消</button>
				    </div>
                       
                   </div>
               </div>
</div>

<script>
function cancelLayout(type){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/auth/";
}
function auth(type){
	$("#content").val("");
	if(type==4){
		$("#authTitle").text("驳回原因");
		$("#content").attr("data-validate","required");
	}else{
		$("#authTitle").text("审批意见");
		$("#content").removeAttr("data-validate");
	}
	$("#authStatus").val(type);
	$("#authModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true
    });
}
function saveAuth(){
	$("#authForm").verify();
	$("#authForm").validate(function (result) {
	  	if(result){
	  		$("#popup_box").show();
	  		$.ajax({
				type:"post",
				url: _ctx+"/back/newHouse/auth/save_newHouse_auth",
				dataType:"json",
				data:$("#authForm").serialize(),
				success:function(resultVo){
					if(resultVo.status!=200){
						$("#popup_box").hide();
						 setErrorContent(resultVo.message);
					}else{
						location = _ctx+"/back/frame/newHouse/auth"
					}
				}
			})
	  	}
	  	
	})
}
function findOneLayOut(param,type){
	$("#popup_box").show();
	if(type=='no'){
		location=_ctx+"/back/frame/newHouse/auth/newHouse_auth_newHouseType?newHouseId="+param;	
	}else{
		location=_ctx+"/back/frame/newHouse/auth/newHouse_auth_history_newHouseType?newHouseId="+param;
	}
	
}
</script>