<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<ol class="breadcrumb">
			<li>租房管理</li>
			<li>房源查询</li>
		</ol>
		<form id="searchFromId" class="form-inline">
			<div class="form-group mr20">
				<span class="wenzi6">省:</span> <select id="areaOneId" class="form-control dsj-inline" style="width: 100px;" name="areaCode1" onchange="getTwoArea()">
					<option value="">请选择</option>
					<c:forEach items="${firstAreaList }" var="area">
						<c:if test="${area.areaCode!=1 }">
							<option value="${area.areaCode }">${area.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">市:</span> <select id="areaTwoId" class="form-control dsj-inline" name="areaCode2" onchange="getThreeArea()">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">区:</span> <select id="areaThreeId" class="form-control dsj-inline " name="areaCode3" onchange="getFourArea()">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">商圈:</span> <select id="areaFourId" class="form-control dsj-inline " name="tradingAreaId">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">小区名称:</span> <input type="text" class=" form-control " placeholder="模糊查询" name="cardName" style="width: 150px;">
			</div>
			<div class="form-group mr20">
				<span class="wenzi6" style="width: 100px;">状态:</span> <select name="status" class="form-control">
					<option value="">全部</option>
					<option value="1">未上架</option>
					<option value="2">上架</option>
					<option value="3">下架</option>
				</select>
			</div>
			<div class="form-group mt20" style="width: 166px;">
				<!-- <span class="wenzi6">面积:</span> <input class="small-input9 form-control" name="areaMin" type="text" placeholder="最小面积"> <span class="wenzi2">~</span> <input class="small-input9 form-control" name="areaMax" type="text" placeholder="最大面积"> 
				<span class="wenzi6">价格:</span> <input class="small-input9 form-control" name="priceMin" type="text" placeholder="最小价格"> <span class="wenzi2">~</span> <input class="small-input9 form-control" name="priceMax" type="text" placeholder="最大价格">  -->
				<span class="wenzi6">房型:</span> <input class="small-input9 form-control" name="roomMin" type="text"> <span class="wenzi2">~</span> <input class="small-input9 form-control" name="roomMax" type="text">室
			</div>

			<div class="form-group mt20">
				<div class="form-group mr20">
					<span class="wenzi6">创建时间:</span> <input id="LAY_demorange_zs" class="form-control layDateClass laydate-icon" placeholder="开始时间" name="startCreateTime" style="width: 120px;"> <span class="wenzi2">~</span> <input id="LAY_demorange_ze" class="form-control layDateClass laydate-icon" placeholder="结束时间" name="endCreateTime" style="width: 120px;">
				</div>
			</div>

			<div class="btngroup row mt20 text-center">
				<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
				<button class="dsj_btn btn btn-default" id="reset_btn" type="reset">重置</button>
			</div>
		</form>
	</div>
	<div class="white-content">
		<div class="row">
			<div class="col-xs-12" style="padding-right: 25px;">
				<shiro:hasPermission name='evelopers:add'>
					<button class="dsj_btn btn dsj_btn_green" type="button" onclick="addOrigin()" style="width: 150px;">新建房源</button>
				</shiro:hasPermission>
				<shiro:hasPermission name='evelopers:deletes'>
					<button class="dsj_btn btn btn-default" type="button" onclick="delOrigin()">批量删除</button>
				</shiro:hasPermission>

				<!--表格 S-->
				<div class="slb_table H_slb_table">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable">

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var table;
	$(function() {
		var columns = [
				{
					"title" : "<input id='checkall' type='checkbox' />",
					"mRender" : function(data, type, full) {
						return "<input name='checkitem' value='"+full.id+"' data-status='"+full.status+"' type='checkbox'  />";
					}
				},
				{
					"title" : "创建时间",
					"mData" : "createTime"
				},
				{
					"title" : "房源ID",
					"mRender" : function(data, type, full) {
					return "<a href='javascript:void(0);' onclick='showOrigin("+full.id+")'>"+full.id+"</a>";
					}
				},
				{
					"title" : "行政区域",
					"mData" : "areaName3"
				},
				{
					"title" : "商圈",
					"mData" : "tradingAreaName"
				},
				{
					"title" : "小区名称",
					"mData" : "cardName"
				},
				{
					"title" : "房屋类型",
					"mRender" : function(data, type, full) {
						var room = "";
						if (full.door != '' && full.door != null) {
							room += full.door + "室";
						}
						if (full.hall != '' && full.hall != null) {
							room += full.hall + "厅";
						}
						if (full.toilet != '' && full.toilet != null) {
							room += full.toilet + "卫";
						}
						if (full.kitchen != '' && full.kitchen != null) {
							room += full.kitchen + "厨";
						}
						return room;
					}
				},
				{
					"title" : "租金",
					"mData" : "rentPrice"
				},
				{
					"title" : "出租类型",
					"mData" : "rentTypeName"
				},
				{
					"title" : "状态",
					"mData" : "statusName"
				},
				{
					"title" : "操作",
					"mRender" : function(data, type, full) {
						var edit = "<a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='editOrigin("
								+ full.id + ")'>编辑</a>";
						var del = "<a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='delOriginById("
								+ full.id + ")'>删除</a>";
						var up = "<a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='upOrDownOrigin("
								+ full.id + ",2)'>上架</a>";
						var down = "<a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='upOrDownOrigin("
								+ full.id + ",3)'>下架</a>";
						if (full.status == 3) {
							return edit + del + up;
						} else if (full.status == 2) {
							return down;
						} else {
							return edit + del + up;
						}
					}
				} ];
		table = drawDatatable("searchFromId", columns,
				"${ctx}/back/rentHouse/general/origin/page/list", "dataTable");
	})
</script>
<script src="${ctx}/static/back/rentHouse/general/origin/origin_list.js"></script>