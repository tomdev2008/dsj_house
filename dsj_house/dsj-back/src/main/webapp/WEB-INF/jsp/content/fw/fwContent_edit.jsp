<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<script type="text/javascript" src="${ctx}/static/back/js/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/back/js/ueditor.all.js"></script>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			编辑权证内容
		</h1>
		<ol class="breadcrumb">
			<li>权证内容管理</li>
			<li>编辑 </li>
		</ol>
		<form class="form-horizontal" id="addFwContentForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		            	<input type="hidden" name="id" value="${fwContent.id }"/>
		                <label  class="col-sm-2 control-label">标题</label>
		                <div class="col-sm-6">
		                  <input name="title" type="text" value="${fwContent.title }" class="form-control" placeholder="标题（3-30字）" data-validate="required">
		                </div>
		            </div>
		        	<div class="form-group">
		                <label  class="col-sm-2 control-label">封面</label>
		                 <div class="col-sm-6">
		                  	<c:if test="${fwContent.photo=='' }">
		                 		<img id="photoShowImg" style="width: 180px;height: 180px;"/>
		                 	</c:if>
		                 	
		                 	<c:if test="${fwContent.photo != '' }">
		                 		<img id="propertyPhotoShowImg" src="${fwContent.photo }" style="width: 80px;height: 80px;"/>
		                 	</c:if>
			                 <div id="propertyPhotoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="photo" style="width: 0px;border: 0px;" type="text" id="propertyPhotoFileUrl">
		                 </div>
		            </div>
		            
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">点赞数</label>
		                 <div class="col-sm-6">
			                 <input name="dianZan" value="${fwContent.dianZan}" type="text" class="form-control" placeholder="点赞数">
		                 </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">阅读数</label>
		                 <div class="col-sm-6">
			                 <input name="read1" type="text" value="${fwContent.read1}"  class="form-control" placeholder="阅读数">
		                 </div>
		            </div>
		            
		            
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">内容</label>
		                <script name="content" id="saveEditor" type="text/plain"
						style="width: 500px; height: 120px;margin-left:130px;z-index:1;">${fwContent.content}</script>
		            </div>
		          
		            <div class="text-center">
		           		 <button class="btn btn-primary" type="button" onclick="faBu()">发布</button>
	                     <button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script type="text/javascript">
	var editor;
	editor = new UE.ui.Editor();
	editor.render("saveEditor");
function faBu(){
	$("#addFwContentForm").validate(function (result) {
	  	if(result){
	  		$.ajax({
	  			type:"post",
	  			async:true,
	  			data:$("#addFwContentForm").serialize(),
	  			dataType:"json",
	  			url:_ctx+"/back/frame/content/fwContent/faBuFwContent",
	  			success:function(resultVo){
	  				if(resultVo.status!=200){
						 setErrorContent(resultVo.message);
					}else{
						setErrorContent("发布成功");
						location=_ctx+"/back/frame/fwContent"
					}
	  			}
	  		})
	  	}
	})
}
$(function(){
     singleUpload("propertyPhotoFilePicker","propertyPhotoFileUrl","propertyPhotoShowImg",beforeUpload,afterUpload);
})
 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }
function cancelFun(type){
	location=_ctx+"/back/frame/content/fwContent"
}
</script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>