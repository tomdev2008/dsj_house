<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">品牌公寓账号审核</h1>
		<ol class="breadcrumb">
			<li>品牌公寓账号管理</li>
			<li>品牌公寓账号审核</li>
		</ol>
		<form id="searchFromId" class="form-inline">
			<div class="form-group mr20">
				<span class="wenzi6">创建时间:</span>
				<input value="" class="small-input form-control laydate-icon layDateClass" placeholder="开始时间" id="LAY_demorange_zs" name="startTime" style="width: 120px;">
				<span class="wenzi2">~</span>
				<input value="" class="small-input form-control laydate-icon layDateClass" placeholder="结束时间" id="LAY_demorange_ze" name="endTime" style="width: 120px;">
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">账号名称:</span>
				<input type="text" class="small-input form-control" placeholder="账号模糊查询" name="username" style="width: 100px;">
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">品牌公寓名称：</span>
				<input type="text" class="small-input form-control" placeholder="名称模糊查询" name="flatName" style="width: 100px;">
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">联系人姓名：</span>
				<input type="text" class="small-input form-control" placeholder="联系人模糊查询" name="contact" style="width: 100px;">
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">联系人手机号：</span>
				<input type="text" class="small-input form-control" placeholder="手机号模糊查询" name="contactPhone" style="width: 100px;">
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">审核状态:</span>
				<select name="status" class="form-control dsj-inline" style="width: 100px;" id="status">
					<option value="1">未审核</option>
					<!-- <option value="">请选择</option>
		                      <option value="1">未审核</option>
		                      <option value="2">已通过</option>
		                      <option value="3">已驳回</option> -->
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">省:</span>
				<select id="areaOneId" class="form-control dsj-inline" style="width: 100px;" name="provinceCode" onchange="getTwoArea()">
					<option value="">请选择</option>
					<c:forEach items="${firstAreaList }" var="area">
						<c:if test="${area.areaCode!=1 }">
							<option value="${area.areaCode }">${area.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">市:</span>
				<select id="areaTwoId" class="form-control dsj-inline" style="width: 100px;" name="cityCode">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="btngroup row mt20 text-center">
				<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
				<button class="dsj_btn btn btn-default" type="reset">重置</button>
			</div>
		</form>
	</div>
	<div class="white-content">
		<div class="row">
			<div class="col-xs-12" style="padding-right: 25px;">
				<shiro:hasPermission name='flat:access'>
					<button class="dsj_btn btn btn-default" id="accessBtn" type="button" onclick="accessOrrefuseFlats(1)">批量通过</button>
				</shiro:hasPermission>
				<shiro:hasPermission name='flat:return'>
					<button class="dsj_btn btn btn-default" id="refuseBtn" type="button" onclick="accessOrrefuseFlats(2)">批量驳回</button>
				</shiro:hasPermission>

				<!--  </div> -->

				<!--表格 S-->
				<div class="slb_table H_slb_table">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable">
							<thead>
								<tr>
									<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')" /></th>
									<th>品牌公寓名称</th>
									<th>账号名称</th>
									<th>联系人姓名</th>
									<th>联系人电话</th>
									<th>审核状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/static/back/system/flat/flat_audit_list.js"></script>