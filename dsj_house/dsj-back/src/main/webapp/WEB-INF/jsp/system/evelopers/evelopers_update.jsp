<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			开发商账号编辑
		</h1>
		<ol class="breadcrumb">
			<li>开发商账号管理</li>
			<li>编辑</li>
		</ol>
			<form class="form-horizontal" id="evelopersUpdateForm">
			    <div class="row">
			        <div class="col-xs-8 col-xs-offset-2">
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">账号</label>
			                <div class="col-sm-6">
				                <shiro:lacksRole name="admin">
				               		 <input name="username" type="text" class="form-control" placeholder="账号" data-validate="required,zm_num_xhx,xhx_start" value="${evelopersVo.username}" readonly="readonly">
				                </shiro:lacksRole>
			                  <shiro:hasRole name="admin">  
			                  		<input name="username" type="text" class="form-control" placeholder="账号" data-validate="required,zm_num_xhx,xhx_start" value="${evelopersVo.username}">
			                  		<input name="updateWhat" type="hidden" value="1">
			                  </shiro:hasRole>  
			                  <input type="hidden" name="userId" value="${evelopersVo.userId}">
			                  <input type="hidden" name="id" value="${evelopersVo.id}">
			                  <input type="hidden" name="status" value="${evelopersVo.status}">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">开发商企业名称</label>
			                <div class="col-sm-6">
				               <shiro:lacksRole name="admin">
				              		<input name="name" type="text" class="form-control" placeholder="与营业执照一致" data-validate="required" value="${evelopersVo.name}" <c:if test="${evelopersVo.status==2}">readonly="readonly"</c:if>> 
				               </shiro:lacksRole>
				                <shiro:hasRole name="admin">
				                	<input name="name" type="text" class="form-control" placeholder="与营业执照一致" data-validate="required" value="${evelopersVo.name}"> 
				                </shiro:hasRole>  
			                </div>
			            </div>
			            <div class="form-group">
			              	    <label class="col-sm-2 control-label">所在地：</label>
			              	    <div class="col-sm-6">
			              	    <shiro:lacksRole name="admin">
				                	<select  id="areaOneId" name="areaOneId" onchange="getTwoArea()" <c:if test="${evelopersVo.status==2}">disabled="disabled"</c:if> class="form-control dsj-inline" style="width:130px">
					                      <option value="">请选择</option>
					                       <c:forEach items="${firstAreaList }" var="area">
					                       	 	<c:if test="${area.areaCode!=1 }">
					                        	 	<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaOneId?'selected="selected"':'' }>${area.name }</option>
					                        	 </c:if>
					                        </c:forEach>
				                    </select>
				                    <select  id="areaTwoId" name="areaTwoId" <c:if test="${evelopersVo.status==2}">disabled="disabled"</c:if> onchange="getTwoArea()" class="form-control dsj-inline" style="width:130px">
					                      <option value="">请选择</option>
					                        <c:forEach items="${twoAreaList }" var="area">
					                        	 	<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaTwoId?'selected="selected"':'' }>${area.name }</option>
					                        </c:forEach>
				                    </select>
				                    
				                     <select id="areaThreeId" class="form-control dsj-inline dsj-width-1" <c:if test="${evelopersVo.status==2}">disabled="disabled"</c:if> name="areaThreeId" style="width:130px">
											<option value="">请选择</option>
											<c:forEach items="${threeAreaList }" var="area">
												<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaThreeId?'selected="selected"':'' }>${area.name }</option>
											</c:forEach>
									</select>
				                  </shiro:lacksRole>  
				                   <shiro:hasRole name="admin"> 
				                   		<select  id="areaOneId" name="areaOneId" onchange="getTwoArea()" class="form-control dsj-inline" style="width:130px">
					                      <option value="">请选择</option>
						                       <c:forEach items="${firstAreaList }" var="area">
						                       	 	<c:if test="${area.areaCode!=1 }">
						                        	 	<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaOneId?'selected="selected"':'' }>${area.name }</option>
						                        	 </c:if>
						                        </c:forEach>
					                    </select>
					                    <select  id="areaTwoId" name="areaTwoId" onchange="authareaTwoIdFun()" class="form-control dsj-inline" style="width:130px">
						                      <option value="">请选择</option>
						                        <c:forEach items="${twoAreaList }" var="area">
						                        	 	<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaTwoId?'selected="selected"':'' }>${area.name }</option>
						                        </c:forEach>
					                    </select> 
					                    
					                     <select id="areaThreeId" class="form-control dsj-inline dsj-width-1" name="areaThreeId" style="width:130px">
												<option value="">请选择</option>
												<c:forEach items="${threeAreaList }" var="area">
													<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaThreeId?'selected="selected"':'' }>${area.name }</option>
												</c:forEach>
										</select>
				                   </shiro:hasRole>  
				                    <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="authareaTwoId"  data-validate="required" value="${evelopersVo.areaTwoId }">
			           			</div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">企业营业执照</label>
			                <div class="col-sm-6">
			                 <shiro:lacksRole name="admin">
			                  <input name="companyLicenseNum" type="text" class="form-control" placeholder="企业营业执照" data-validate="required" value="${evelopersVo.companyLicenseNum}" <c:if test="${evelopersVo.status==2}">readonly="readonly"</c:if>>
			               </shiro:lacksRole> 
			                <shiro:hasRole name="admin"> 
			               		 <input name="companyLicenseNum" type="text" class="form-control" placeholder="企业营业执照" data-validate="required" value="${evelopersVo.companyLicenseNum}">
			                 </shiro:hasRole>   
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">上传营业执照照片</label>
			                <div class="col-sm-6">
				               <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;" src="${evelopersVo.companyLicensePhoto}"/>
				                <shiro:lacksRole name="admin">
			               			 <c:if test="${evelopersVo.status!=2}"> <div id="companyLicensePhotoFilePicker" style="display: inline-block;"> <button class="btn btn-primary" type="button" >选择图片</button> </div></c:if>
				               	</shiro:lacksRole> 
				                <shiro:hasRole name="admin"> 
	 								<div id="companyLicensePhotoFilePicker" style="display: inline-block;"> <button class="btn btn-primary" type="button" >选择图片</button> </div>
				                 </shiro:hasRole>   
				                 <input name="companyLicensePhoto" style="width: 0px;border: 0px;" type="text" id="companyLicensePhotoFileUrl"  data-validate="required" value="${evelopersVo.companyLicensePhoto}">
			            	</div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">楼盘项目名称</label>
				                         <shiro:lacksRole name="admin">
				                        	 <div class="col-sm-6" id="loupanNameId">
													 <c:forEach items="${evelopersVo.idAndNameList }" var="loupanName">
															<select class="form-control dsj-inline dicSelectId js-example-basic-multiple" name="loupanName" data-validate="required">
									 								<option value="${loupanName.id}">${loupanName.name}</option>
															 </select>
							                        </c:forEach>
							                    </div>     
							                        <c:if test="${evelopersVo.status!=2}">
							                        	<a href="javascript:void(0)" onclick="addLoupanName()" style="line-height: 36px;">添加 </a>
						                  				<a href="javascript:void(0)" onclick="removeLoupanName()" style="line-height: 36px;">减少 </a>
							                        </c:if>
							                        
					               	</shiro:lacksRole> 
					                <shiro:hasRole name="admin"> 
			               				 <div class="col-sm-6" id="loupanNameId">
				                 			 <c:forEach items="${evelopersVo.idAndNameList }" var="loupanName">
														<select class="form-control dsj-inline dicSelectId js-example-basic-multiple" name="loupanName" data-validate="required">
								 								<option value="${loupanName.id}">${loupanName.name}</option>
														 </select>
						                        </c:forEach>
					                      </div>
					                        	<a href="javascript:void(0)" onclick="addLoupanName()" style="line-height: 36px;">添加 </a>
				                  				<a href="javascript:void(0)" onclick="removeLoupanName()" style="line-height: 36px;">减少 </a>
					                 </shiro:hasRole>  
				                        
			                
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">承诺书</label>
			                <div class="col-sm-6">
			                   <button class="btn btn-primary" type="button" href="${ctx}/back/frame/system/evelopers/export">下载承诺书</button>
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
				                <img id="promisePhotoShowImg" style="width: 80px;height: 80px;" src="${evelopersVo.promisePhoto}"/>
				                  <shiro:lacksRole name="admin">
			               			 <c:if test="${evelopersVo.status!=2}"><div id="promisePhotoFilePicker" style="display: inline-block;"> <button class="btn btn-primary" type="button" >选择图片</button> </div></c:if>
				               	</shiro:lacksRole> 
				                <shiro:hasRole name="admin"> 
	 								<div id="promisePhotoFilePicker" style="display: inline-block;">
	 									 <button class="btn btn-primary" type="button" >选择图片</button> 
	 								</div>
				                 </shiro:hasRole>   
				                 <input name="promisePhoto" style="width: 0px;border: 0px;" type="text" id="promisePhotoFileUrl"  data-validate="required" value="${evelopersVo.promisePhoto}">
				             </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">管理人姓名</label>
			                <div class="col-sm-6">
			                  <input type="text" name="operationName" class="form-control" placeholder="管理人姓名" data-validate="required" value="${evelopersVo.operationName}">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">管理人联系电话</label>
			                <div class="col-sm-6">
			                  <input type="text" name="operationPhone" class="form-control" placeholder="管理人联系电话" data-validate="required,phone_new" value="${evelopersVo.operationPhone}">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">管理人身份证号:</label>
			                <div class="col-sm-6">
			                  <input type="text" name="operationCard" class="form-control" placeholder="管理人身份证号" data-validate="required,people_card" value="${evelopersVo.operationCard}">
			                </div>
			            </div>
			             <div class="text-center">
			                     <button class="btn btn-primary" type="button" onclick="saveUpdateEveloper()">确认</button>
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