<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			开发商账号新增
		</h1>
		<ol class="breadcrumb">
			<li>开发商账号管理</li>
			<li>新增 </li>
		</ol>
		<form class="form-horizontal" id="evelopersAddForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">账号</label>
		                <div class="col-sm-6">
		                  <input name="username" type="text" class="form-control" placeholder="账号" data-validate="required,zm_num_xhx,xhx_start">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">开发商企业名称</label>
		                <div class="col-sm-6">
		                  <input name="name" type="text" class="form-control" placeholder="与营业执照一致" data-validate="required">
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">所在地：</label>
		              	     <div class="col-sm-6">
			                	<select  id="areaOneId" name="areaOneId" onchange="getTwoArea()" class="form-control dsj-inline" style="width:130px">
				                      <option value="">请选择</option>
				                       <c:forEach items="${firstAreaList }" var="area">
				                       	 	<c:if test="${area.areaCode!=1 }">
				                        	 	<option value="${area.areaCode }">${area.name }</option>
				                        	 </c:if>
				                        </c:forEach>
			                    </select>
	
			                    <select  id="areaTwoId" name="areaTwoId" onchange="getThreeArea()" class="form-control dsj-inline" style="width:130px">
				                      <option value="">请选择</option>
			                    </select>
			                    
			                    <select id="areaThreeId" class="form-control dsj-inline" style="width:130px" name="areaThreeId">
											<option value="">请选择</option>
								</select>
			                    <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="authAreaThreeId"  data-validate="required"> 
		                    </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">企业营业执照</label>
		                <div class="col-sm-6">
		                  <input name="companyLicenseNum" type="text" class="form-control" placeholder="企业营业执照" data-validate="required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传营业执照照片</label>
		                 <div class="col-sm-6">
			                 <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;"/>
			                 <div id="companyLicensePhotoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="companyLicensePhoto" style="width: 0px;border: 0px;" type="text" id="companyLicensePhotoFileUrl"  data-validate="required">
		                 </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">楼盘项目名称</label>
		                <div class="col-sm-6" id="loupanNameId">
		                 <select class="form-control dsj-inline dicSelectId js-example-basic-multiple" name="loupanName"  data-validate="required">
						 </select>
		                </div>
		                 <a href="javascript:void(0)" onclick="addLoupanName()" style="line-height: 36px;">添加 </a>
		                 <a href="javascript:void(0)" onclick="removeLoupanName()" style="line-height: 36px;">减少 </a>
		                
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">承诺书</label>
		                <div class="col-sm-6">
		                   <a class="btn btn-primary" href="${ctx}/back/frame/system/evelopers/export" target="_blank">下载承诺书</a>
		                </div>
		            </div>
		            <div class="form-group">
		            		<label  class="col-sm-2 control-label"></label>
		            		<div class="col-sm-6">
		                		ps:下载文件，盖章后，提交审核。承诺通过这个账号发布的任何信息是属于开发商的企业行为与大搜家无关载.
		                	</div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传承诺书照片</label>
		                 <div class="col-sm-6">
			                 <img id="promisePhotoShowImg" style="width: 80px;height: 80px;"/>
			                 <div id="promisePhotoFilePicker" style="display: inline-block;">
			                 	 <button class="btn btn-primary" type="button" >选择图片</button> 
			                 </div>
			                 <input name="promisePhoto" style="width: 0px;border: 0px;" type="text" id="promisePhotoFileUrl"  data-validate="required">
		                 </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">管理人姓名</label>
		                <div class="col-sm-6">
		                  <input type="text" name="operationName" class="form-control" placeholder="管理人姓名" data-validate="required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">管理人联系电话</label>
		                <div class="col-sm-6">
		                  <input type="text" name="operationPhone" class="form-control" placeholder="管理人联系电话" data-validate="required,phone_new">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">管理人身份证号:</label>
		                <div class="col-sm-6">
		                  <input type="text" name="operationCard" class="form-control" placeholder="管理人身份证号" data-validate="required,people_card">
		                </div>
		            </div>
		             <div class="text-center">
	                     <button class="btn btn-primary" type="button" onclick="saveAddEveloper()">确认</button>
	                     <button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script src="${ctx}/static/back/system/evelopers/evelopers_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>