<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

<div class="col-xs-12 padding-foot">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">品牌公寓账号编辑</h1>
		<ol class="breadcrumb">
			<li>品牌公寓账号管理</li>
			<li>编辑</li>
		</ol>
		<form class="form-horizontal" id="flatUpdateForm">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<div class="form-group">
						<label class="col-sm-2 control-label">账号</label>
						<div class="col-sm-6">
							<shiro:hasRole name="admin">
								<input name="username" type="text" class="form-control" placeholder="手机号或邮箱" data-validate="required,zm_num_xhx,xhx_start" value="${flatUserVo.username}" <c:if test="${flatUserVo.status==2}">readonly="readonly"</c:if>>
							</shiro:hasRole>
							<shiro:lacksRole name="admin">
								<input name="username" type="text" class="form-control" placeholder="手机号或邮箱" data-validate="required,zm_num_xhx,xhx_start" value="${flatUserVo.username}" readonly="readonly">
							</shiro:lacksRole>
							<input type="hidden" name="userId" value="${flatUserVo.userId}">
							<input type="hidden" name="id" value="${flatUserVo.id}">
							<input type="hidden" name="status" value="${flatUserVo.status}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">品牌公寓名称</label>
						<div class="col-sm-6">
							<shiro:hasRole name="admin">
								<input name="flatName" type="text" class="form-control" placeholder="与营业执照一致" data-validate="required" value="${flatUserVo.flatName}" <c:if test="${flatUserVo.status==2}">readonly="readonly"</c:if>>
							</shiro:hasRole>
							<shiro:lacksRole name="admin">
								<input name="flatName" type="text" class="form-control" placeholder="与营业执照一致" data-validate="required" value="${flatUserVo.flatName}" readonly="readonly">
							</shiro:lacksRole>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">所在地：</label>
						<div class="col-sm-6">
							<shiro:hasRole name="admin">
								<select id="areaOneId" name="provinceCode" onchange="getTwoArea()" <c:if test="${flatUserVo.status==2}">disabled="disabled"</c:if> class="form-control dsj-inline" style="width: 200px">
							</shiro:hasRole>
							<shiro:lacksRole name="admin">
								<select id="areaOneId" name="provinceCode" onchange="getTwoArea()" disabled="disabled" class="form-control dsj-inline" style="width: 200px">
							</shiro:lacksRole>
							<option value="">请选择</option>
							<c:forEach items="${firstAreaList }" var="area">
								<c:if test="${area.areaCode!=1 }">
									<option value="${area.areaCode }" ${area.areaCode==flatUserVo.provinceCode?'selected="selected"':'' }>${area.name }</option>
								</c:if>
							</c:forEach>
							</select>
							<shiro:hasRole name="admin">
								<select id="areaTwoId" name="cityCode" onchange="authareaTwoIdFun()" <c:if test="${flatUserVo.status==2}">disabled="disabled"</c:if> class="form-control dsj-inline" style="width: 200px">
							</shiro:hasRole>
							<shiro:lacksRole name="admin">
								<select id="areaTwoId" name="cityCode" onchange="authareaTwoIdFun()" disabled="disabled" class="form-control dsj-inline" style="width: 200px">
							</shiro:lacksRole>
							<option value="">请选择</option>
							<c:forEach items="${twoAreaList }" var="area">
								<option value="${area.areaCode }" ${area.areaCode==flatUserVo.cityCode?'selected="selected"':'' }>${area.name }</option>
							</c:forEach>
							</select>
							<input style="width: 0px; border-color: rgba(255, 255, 255, 0);" type="text" id="authareaTwoId" value="${flatUserVo.cityCode }" data-validate="required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">企业营业执照</label>
						<div class="col-sm-6">
							<input name="businessLicence" type="text" class="form-control" placeholder="企业营业执照编号" data-validate="required" value="${flatUserVo.businessLicence}" <c:if test="${flatUserVo.status==2}">readonly="readonly"</c:if>>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">上传营业执照照片</label>
						<div class="col-sm-6">
							<img id="businessLicencePhotoShowImg" style="width: 80px; height: 80px;" src="${flatUserVo.businessLicencePhoto}" />
							<shiro:hasRole name="admin">
								<c:if test="${flatUserVo.status!=2}">
									<div id="businessLicencePhotoFilePicker" style="display: inline-block;">
										<button class="btn btn-primary" type="button">选择图片</button>
									</div>
								</c:if>
							</shiro:hasRole>
							<input name="businessLicencePhoto" style="width: 0px; border: 0px;" type="text" id="businessLicencePhotoFileUrl" data-validate="required" value="${flatUserVo.businessLicencePhoto}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">承诺书</label>
						<div class="col-sm-6">
							<a class="btn btn-primary" href="${ctx}/back/frame/system/evelopers/export" target="_blank">下载承诺书</a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-6">ps:下载文件，盖章后，提交审核。承诺通过这个账号发布的任何信息是属于开发商的企业行为与大搜家无关载.</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">上传承诺书照片</label>
						<div class="col-sm-6">
							<img id="undertakingShowImg" style="width: 80px; height: 80px;" src="${flatUserVo.undertaking}" />
							<shiro:hasRole name="admin">
								<c:if test="${flatUserVo.status!=2}">
									<div id="undertakingFilePicker" style="display: inline-block;">
										<button class="btn btn-primary" type="button">选择图片</button>
									</div>
								</c:if>
							</shiro:hasRole>
							<input name="undertaking" style="width: 0px; border: 0px;" type="text" id="undertakingFileUrl" data-validate="required" value="${flatUserVo.undertaking}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系人姓名</label>
						<div class="col-sm-6">
							<input type="text" name="contact" value="${flatUserVo.contact}" class="form-control" placeholder="姓名" data-validate="required">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-6">
							<input type="text" name="contactPhone" value="${flatUserVo.contactPhone}" class="form-control" placeholder="电话" data-validate="required,phone_new">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">管理人身份证号:</label>
						<div class="col-sm-6">
							<input type="text" name="contactIdCard" value="${flatUserVo.contactIdCard}" class="form-control" placeholder="管理人身份证号" data-validate="required,people_card">
						</div>
					</div>
					<div class="text-center">
						<button class="btn btn-primary" type="button" onclick="saveUpdateFlat()">确认</button>
						<button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="${ctx}/static/back/system/flat/flat_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>