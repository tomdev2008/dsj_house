<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div id="myModal" >
			<div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                           <h4 class="modal-title">编辑banner</h4>
                       </div>
                       <div class="modal-body">
    							 <form id="roleFrom" class="form-horizontal">
							          <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label0">链接地址：</label>
										    <div class="col-sm-9">
										       <input type="text" placeholder="链接地址" value="${banner.linkAddress}" id="linkAddress" class="form-control"  data-validate="required" style="width:400">
										    </div>
							          </div>
							           <div class="form-group">
		                                 <label  class="col-sm-2 control-label">上传图片</label>
						                 <div class="col-sm-6">
							                 <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;" src="${banner.picture}"/>
							                 <div id="companyLicensePhotoFilePicker" style="display: inline-block;"> 
							     				 <button class="btn btn-primary" type="button" >选择图片</button>            
							                 </div>
							                 <input value="${banner.picture}" style="width: 0px;border: 0px;" type="text" id="companyLicensePhotoFileUrl"  data-validate="required">
						                 </div>
		                           </div>
							          <input type="hidden" value="${banner.id}" id="pcBannerId" > 
							       </form> 
                       </div>
                       <div class="modal-footer">
                            <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">取消</button>
                           <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" onclick="updateBanner()" id="add">确认</button>
                       </div>
                   </div>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
<script>
   $(function(){
    singleUpload("companyLicensePhotoFilePicker","companyLicensePhotoFileUrl","companyLicensePhotoShowImg",beforeUpload,afterUpload);
  })
function beforeUpload()
{
    loading_szyq("上传中...",true);
}
   function afterUpload()
   {
       loading_szyq(false);
   }
   
  
		
 </script>  
<script src="${ctx}/static/back/pcHouse/pcHouse.js"></script>