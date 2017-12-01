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
												<div class="col-xs-12">
													<a id="try" style="margin-left: 0px;" class="dsj_btn btn dsj_btn_green" onclick="openCommonModal('${ctx }/back/newHouse/edit/newHouse_pictures_add_begin',1200,12)">添加</a>
													<a id="try" style="margin-left: 0px;" class="btn btn-default" onclick="delPictures()">删除</a>
													<a id="try" style="margin-left: 0px;" class="btn btn-default" onclick="selectAll()">全选</a>
												</div>
											</div>
											<span></span>
											<div class="row mt20">
											<c:forEach var="item" items="${list}"> 
						                      	<div class="col-xs-3">
													<div class="panel panel-default dsj_photo">
													<input class="dsj_pic_checkbox" type="checkbox" value="${item.id}">
														<div class="panel-body">
															<div class="dsj_img_wraper">
																<img class="img-rounded img-responsive" alt="Responsive image" src="${item.pictureUrl}">
																<c:if test="${item.pictureFrist==2 }">
																	<p class="dsj_img_single_action"><a href="javascript:void(0)" onclick="setFirstPicture(${item.id},${newHouseId })" style="color: white;">设为封面</a></p>
																</c:if>
																<c:if test="${item.pictureFrist==1 }">
																	<p class="dsj_img_single_action">已为封面</p>
																</c:if>
															</div>
					 										<p class="mt10" onclick="setDescribes(this)">
																<span class="dsj-pic-describe">图片描述:${item.describes}</span>
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
					<form id="picturesDescribeForm" novalidate="novalidate"  class="col-xs-8 col-xs-offset-2">
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
		<button class="btn btn-primary" type="button" onclick="javascript:window.history.go( -1 );">上一步</button>
    </div>
</div>
<script type="text/javascript" src="${ctx }/static/front/js/webuploader.js"></script>
<script>
var ids = [];
$(function(){
	 $("body").on("click","#delNewHousesPictureModalConfirm",function(){
		 $("#popup_box").show();
			$.ajax({
				type:"post",
				url:_ctx+"/back/newHouse/edit/newHouse_Picture_del",
				data:JSON.stringify(ids),
				datatype:"json",
				contentType: 'application/json',  
				success:function(result){
					if(result.status!=200){
						$("#popup_box").hide();
						 setErrorContent(result.message);
					}else{
						location.reload();
					}
				}
			})
		  
	  });
})

function cancelFun(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_pictures_add?newHouseId="+$("#newHouseId").val();
}
function delPictures(){
	ids = new Array();
	$(".dsj_pic_checkbox:checked").each(function(){
		 ids.push($(this).val());
	})
		
	if(ids.length == 0){
	setErrorContent("请选择要删除的图片");
	  return;
  }else{
	  setModalContent("确认删除选中图片?","delNewHousesPictureModalConfirm");
  }
}
function selectAll(){
	if($("input[type='checkbox']:checked").length==$("input[type='checkbox']").length){
		$("input[type='checkbox']").prop("checked",false);
	}else{
		$("input[type='checkbox']").prop("checked",true);
	}
}

function setDescribes(obj){
	$(obj).find("span").eq(0).hide()
	$(obj).find("span").eq(1).show();
}
function updatePictureDescribes(obj){
	$("#popup_box").show();
 		$.ajax({
 			type:"post",
 			data:{
 				describes:$(obj).val(),
 				id:$(obj).attr("idVal")
 			},
 			dataType:"json",
 			url:_ctx+"/back/newHouse/edit/newHouse_saveUpdatePicture",
 			success:function(resultVo){
 				$("#popup_box").hide();
 				if(resultVo.status!=200){
					 setErrorContent(resultVo.message);
				}else{
					$(obj).parent().hide();
					$(obj).parent().prev().show();
					$(obj).parent().prev().text("图片描述:"+resultVo.data);
				}
 			}
 		})
}
function setFirstPicture(id,newHouseId){
	$("#popup_box").show();
	$.ajax({
			type:"post",
			data:{
				newHouseId:newHouseId,
				id:id
			},
			dataType:"json",
			url:_ctx+"/back/newHouse/edit/newHouse_savePicture_first",
			success:function(resultVo){
				if(resultVo.status!=200){
					$("#popup_box").hide();
					 setErrorContent(resultVo.message);
				}else{
					location.reload();
				}
			}
		})
}
</script>