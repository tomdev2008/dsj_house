<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			二手房求购查询 
		</h1>
		<ol class="breadcrumb">
			<li>二手房求购管理</li>
			<li>二手房求购查询 </li>
		</ol>
		<form id="searchFromId" class="form-inline">
			<div class="form-group mr20">
				<span class="wenzi6">求购时间:</span>
				<input value=""  class="small-input form-control laydate-icon" placeholder="开始时间" id="LAY_demorange_ss_two" name="startTime" style="width: 120px;">
				<span class="wenzi2">~</span>
				<input value=""  class="small-input form-control laydate-icon" placeholder="结束时间" id="LAY_demorange_ee_two" name="endTime" style="width: 120px;">
			</div>
			<!-- <div class="form-group mr20">
				<span class="wenzi6">状态:</span>
				<select name="status"  class="form-control dsj-inline" style="width:100px;" id="status">
					<option value="">状态</option>
					<option value="1">已处理</option>
					<option value="2">未处理</option>
				</select>
			</div> -->
			<div class="form-group mr20">
				<span class="wenzi6">小区名称:</span>
				<input type="text" class="small-input form-control" placeholder="小区名称查询" name="buildingName"  style="width: 100px;">
			</div>
			<div class="btngroup row mt20 text-center">
				<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
				<button class="dsj_btn btn btn-default" type="reset">重置</button>
			</div>
		</form> 
	</div>
	<div class="white-content">
		<div class="row">
			<div class="col-xs-12" style="padding-right:25px;">
				<!-- <button class="dsj_btn btn btn-default" type="button" onclick="deal_requires()">批量处理</button> -->
				<!--表格 S-->
				<div class="slb_table H_slb_table">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable">
							<thead>
								<tr>
									<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
										<th>求购时间</th>
										<th>求购人</th>
										<th>手机号</th>
										<th>商圈</th>
										<th>小区名称</th>
										<th>户型</th>
										<th>预算区间</th>
										<!-- <th>操作</th> -->
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
<script src="${ctx}/static/back/oldHouse/require/require_list.js"></script>