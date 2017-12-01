<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			开发商账号查询
		</h1>
		<ol class="breadcrumb">
			<li>开发商账号管理</li>
			<li>审核</li>
		</ol>
			<form class="form-horizontal" id="evelopersUpdateForm">
			    <div class="row">
			        <div class="col-xs-8 col-xs-offset-2">
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">账号</label>
			                <div class="col-sm-6">
			                  <input name="username" type="text" class="form-control" placeholder="账号" data-validate="required,zm_num_xhx,xhx_start" value="${evelopersVo.username}" readonly="readonly">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">开发商企业名称</label>
			                <div class="col-sm-6">
			                  <input name="name" type="text" class="form-control" placeholder="与营业执照一致" data-validate="required" value="${evelopersVo.name}" readonly="readonly">
			                </div>
			            </div>
			            <div class="form-group">
			              	    <label class="col-sm-2 control-label">所在地：</label>
			              	    <div class="col-sm-6">
				                	<select  id="areaOneId" name="areaOneId" onchange="getTwoArea()" disabled="disabled" class="form-control dsj-inline" style="width:130px">
					                      <option value="">请选择</option>
					                       <c:forEach items="${firstAreaList }" var="area">
					                       	 	<c:if test="${area.areaCode!=1 }">
					                        	 	<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaOneId?'selected="selected"':'' }>${area.name }</option>
					                        	 </c:if>
					                        </c:forEach>
				                    </select>
				                    <select  id="areaTwoId" name="areaTwoId" disabled="disabled" onchange="authareaTwoIdFun()" class="form-control dsj-inline" style="width:130px">
					                      <option value="">请选择</option>
					                        <c:forEach items="${twoAreaList }" var="area">
					                        	 	<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaTwoId?'selected="selected"':'' }>${area.name }</option>
					                        </c:forEach>
				                    </select>
				                    <select id="areaThreeId" class="form-control dsj-inline dsj-width-1" disabled="disabled" name="areaLevalThree" style="width:130px">
												<option value="">请选择</option>
												<c:forEach items="${threeAreaList }" var="area">
													<option value="${area.areaCode }" ${area.areaCode==evelopersVo.areaThreeId?'selected="selected"':'' }>${area.name }</option>
												</c:forEach>
									</select>
				                    <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="authareaTwoId"  data-validate="required" value="${evelopersVo.areaTwoId }">
			           			</div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">企业营业执照</label>
			                <div class="col-sm-6">
			                  <input name="companyLicenseNum" type="text" class="form-control" placeholder="企业营业执照" data-validate="required" value="${evelopersVo.companyLicenseNum}" readonly="readonly">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">营业执照照片</label>
			                <div class="col-sm-6">
				                <a href="${evelopersVo.companyLicensePhoto}" target="blank">
					               <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;" src="${evelopersVo.companyLicensePhoto}"/>
				               </a>
			            	</div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">楼盘项目名称</label>
			                <div class="col-sm-6">
			                 			 <c:forEach items="${evelopersVo.idAndNameList }" var="loupanName">
												<select class="form-control dsj-inline dicSelectId js-example-basic-multiple"  disabled="disabled"  data-validate="required">
						 								<option value="${loupanName.id}">${loupanName.name}</option>
												 </select>
				                        </c:forEach>
			                </div>
			                
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">承诺书照片</label>
			                <div class="col-sm-6">
			               		<a href="${evelopersVo.promisePhoto}" target="blank">
				               		 <img id="promisePhotoShowImg" style="width: 80px;height: 80px;" src="${evelopersVo.promisePhoto}"/>
				                </a>
				             </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">管理人姓名</label>
			                <div class="col-sm-6">
			                  <input type="text" name="operationName" class="form-control" placeholder="管理人姓名" data-validate="required" value="${evelopersVo.operationName}" readonly="readonly">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">管理人联系电话</label>
			                <div class="col-sm-6">
			                  <input type="text" name="operationPhone" class="form-control" placeholder="管理人联系电话" data-validate="required,phone_new" value="${evelopersVo.operationPhone}" readonly="readonly">
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">管理人身份证号:</label>
			                <div class="col-sm-6">
			                  <input type="text" name="operationCard" class="form-control" placeholder="管理人身份证号" data-validate="required,people_card" value="${evelopersVo.operationCard}" readonly="readonly">
			                </div>
			            </div>
			             <div class="form-group">
			                     <button class="btn btn-primary" type="button" onclick="authYesEveloper('${evelopersVo.userId}')">通过</button>
			                     <button class="btn btn-primary" type="button" onclick="authNoEveloper('${evelopersVo.userId}')">驳回</button>
			                     <button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
			                </div>
			        </div>
			    </div>
			</form>
	</div>
</div>
<script src="${ctx}/static/back/system/evelopers/evelopers_auth.js"></script>
