<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div id="myModal" >
			<div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                           <h4 class="modal-title">编辑新房</h4>
                       </div>
                       <div class="modal-body">
    							 <form id="roleFrom" class="form-horizontal">
							          <div class="form-group">
		                               <label  class="col-sm-2 control-label">楼盘名称</label>
		                               <div class="col-sm-6">
						                  <select id="dicSelectId" name="newHouseName" class="form-control dsj-inline " class="js-example-basic-multiple" >
				 								<option value="${pcNewHouse.newHouseId}">${pcNewHouse.newHouseName}</option>
										 </select>
						               </div>
						               </div>
							          <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label0">链接地址：</label>
										    <div class="col-sm-9">
										       <input type="text" placeholder="链接地址" value="${pcNewHouse.linkAddress}" id="linkAddress" class="form-control"  data-validate="required" style="width:400">
										    </div>
							          </div>
							           <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label0">楼盘卖点：</label>
										    <div class="col-sm-9">
										       <input type="text" placeholder="推荐语" value="${pcNewHouse.recommend}" id="recommend" class="form-control"  data-validate="required" style="width:400" maxlength="20">
										    </div>
							          </div>
							           <div class="form-group">
		                                 <label  class="col-sm-2 control-label">上传图片</label>
						                 <div class="col-sm-6">
							                 <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;" src="${pcNewHouse.picture}"/>
							                 <div id="companyLicensePhotoFilePicker" style="display: inline-block;"> 
							     				 <button class="btn btn-primary" type="button" >选择图片</button>            
							                 </div>
							                 <input value="${pcNewHouse.picture}" style="width: 0px;border: 0px;" type="text" id="companyLicensePhotoFileUrl"  data-validate="required">
						                 </div>
		                           </div>
							          <input type="hidden" value="${pcNewHouse.id}" id="pcNewHouseId" > 
							          <input type="hidden" value="${pcNewHouse.labelId}" id="labelId" >     
							       </form> 
                       </div>
                       <div class="modal-footer">
                            <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">取消</button>
                           <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" onclick="updateNewHouse()" id="add">确认</button>
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
   
   $(function(){
	   $.fn.modal.Constructor.prototype.enforceFocus =function(){};
		$("#dicSelectId").select2({
			     dropdownParent:$("#myModal"),
				"ajax":{
				    url: _ctx+"/back/newHouse/edit/getDicIsTure",
				    data: function (params) {
				      var query = { //请求的参数, 关键字和搜索条件之类的
				        name: params.term //select搜索框里面的value
				      }
				      // Query paramters will be ?search=[term]&page=[page]
				      return query;
				    },
				    delay: 1500,
				    processResults: function (data, params) {
				      //返回的选项必须处理成以下格式
				      var results = data.data;

				      return {
				        results: results  //必须赋值给results并且必须返回一个obj
				      };
				    }
				  }
		});
		
	})
 </script>  
<script src="${ctx}/static/back/pcHouse/pcHouse.js"></script>