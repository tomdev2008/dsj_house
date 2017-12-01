<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head" style="height:220px">
		<form class="form-horizontal" id="propertyAndUserPhoto">
			<div class="row" >
				<div class="col-xs-8 col-xs-offset-2" style="top:20px">
					<input name="id" value="${user.id }" type="hidden" />
					<div class="form-group" >
						<label class="col-sm-2 control-label">上传头像</label>
						<div class="col-sm-6">
							<c:if test="${user.avatar=='' && user.avatar==null }">
								<img id="propertyPhotoShowImg"
									style="width: 80px; height: 80px;" />
							</c:if>
							<c:if test="${user.avatar!='' && user.avatar!=null }">
								<img id="propertyPhotoShowImg" src="${user.avatar }"
									style="width: 80px; height: 80px;" />
							</c:if>
							<div id="propertyPhotoFilePicker" style="display: inline-block;">
								<button class="btn btn-primary" type="button" >选择图片</button>
							</div>
							<input name="avatar" value="${user.avatar }"
								style="width: 0px; border: 0px;" type="text"
								id="propertyPhotoFileUrl" data-validate="required">
						</div>
						<div class="row dsj_button_group" >
							<div class="col-sm-12" style="left:175px;top:50px">
								<input  class="btn dsj_button btn-primary" type="button"
								onclick="savePhoto()" value="保存头像">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		singleUpload("propertyPhotoFilePicker", "propertyPhotoFileUrl",
				"propertyPhotoShowImg", beforeUpload, afterUpload);
	})
	function beforeUpload() {
		loading_szyq("上传中...", true);
	}

	function afterUpload() {
		loading_szyq(false);
	}
	function savePhoto(){
		setModalContent("确定要传图片吗?","updatePhotoConfirm");
		 $("body").on("click","#updatePhotoConfirm",function(){
  			  $.ajax({
  		  			type:"post",
  		  			async:true,
  		  			data:$("#propertyAndUserPhoto").serialize(),
  		  			dataType:"json",
  		  			url:_ctx+"/back/person/warrant/upload_photo",
  		  			success:function(resultVo){
  		  				if(resultVo.status!=200){
  							setErrorContent(resultVo.message);
  						}else{
  							$("#isSureCancel").click();
  							location.reload();
  						}
  		  			}
  		  		})
  		  })
	}
</script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>