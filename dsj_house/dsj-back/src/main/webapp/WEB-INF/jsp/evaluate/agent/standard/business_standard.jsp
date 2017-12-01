<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<div class="row">
			<ul class="nav nav-tabs button_tab" role="tablist">
				<li role="presentation"><a
					href="${ctx }/back/frame/evaluate/agent/agentStandard">基础分</a></li>
				<li role="presentation" class="active"><a
					href="${ctx }/back/frame/evaluate/agent/agentStandard/business">业务分</a></li>
				<li role="presentation"><a
					href="${ctx }/back/frame/evaluate/agent/agentStandard/evaluate">评价分</a></li>
			</ul>
		</div>
		<div class="form-group">
			<span class="wenzi6">业务分</span>
		</div>
	</div>
	<div class="white-content">
		<!--表格 S-->
		<div class="slb_table H_slb_table">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable">
					<thead>
						<tr>
			                 <th>项目</th>
			                 <th colspan="4">房源</th>
			                 <th>带看量</th>
			                 <th>成交数</th>
			                 <th>培训考试</th>
			                 <th>活跃度</th>
						</tr>
						<tr>
			                 <th>细项</th>
			                 <th>房源信息</th>
			                 <th>业主姓名电话</th>
			                 <th>实堪图片</th>
			                 <th>钥匙</th>
			                 <th>带看量</th>
			                 <th>成交量</th>
			                 <th>次数</th>
			                 <th>发帖数</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<button class="btn btn-primary" type="button" onclick="save()">保存</button>
						<button class="btn btn-default" type="button" onclick="reset()">重置</button>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var startIndex = 0;
	var totalScore = 0;
	$(document).ready(
		function() {
			/* Init DataTables */
			var oTable = $('#dataTable').dataTable(
				{
					"bSort" : false, //取消默认排序查询,否则复选框一列会出现小箭头
					"bProcessing" : false, //隐藏加载提示,自行处理
					"serverSide" : true,
					"bProcessing" : true,
					"bLengthChange" : true,
					/*    "bStateSave": true, */
					"bFilter" : false, //搜索框
					/*  "sPaginationType" : "extStyle",   */
					"bAutoWidth" : true,//关闭后，表格将不会自动计算表格大小
					"sAjaxDataProp" : "data",
					"paging" : false,
					"bInfo" : false,//页脚信息 
					"iDisplayLength" : 10,
					"sAjaxSource" : _ctx
							+ "/back/frame/evaluate/agnet/agentStandard/businessList",
					"language" : {
						"lengthMenu" : "",
						"zeroRecords" : "没有找到记录",
						"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
						"infoEmpty" : "无记录",
						"infoFiltered" : "(从 _MAX_ 条记录过滤)",
						"paginate" : {
							"previous" : "上一页",
							"next" : "下一页",
							"first" : "首页",
							"last" : "尾页",
						}
					},

					"fnServerData" : function(sSource, aoData, fnCallback) {
						$.ajax({
							"type" : 'POST',
							"url" : sSource,
							"dataType" : "json",
							"data" : {
								aoData : JSON.stringify(aoData)
							},
							"success" : function(resp) {
								fnCallback(resp);
							}
						});
					},

					"aoColumns" : [
							{
								"aTargets" : [ 0 ],
								"mRender" : function(data, type, item, full) {
									startIndex = full.settings._iDisplayStart;  
					        		if (startIndex + full.row == 0) {
					        			text = "单日最高值";
					        		} else if (startIndex + full.row == 1) {
					        			text = "次数";
					        		} else {
					        			text = "单次分值";
					        		}
									return text;
								}
							},
							{
								"aTargets" : [ 1 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value1;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id1 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value1 + " />";
										return input;
									}
								}
							},
							{
								"mData" : "value2",
								"aTargets" : [ 2 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value2;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id2 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value2 + " />";
										return input;
									}
								}
							},
							{
								"aTargets" : [ 3 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value3;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id3 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value3 + " />";
										return input;
									}
								}
							},
							{
								"aTargets" : [ 4 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value4;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id4 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value4 + " />";
										return input;
									}
								}
							},
							{
								"aTargets" : [ 5 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value5;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id5 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value5 + " />";
										return input;
									}
								}
							},
							{
								"aTargets" : [ 6 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value6;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id6 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value6 + " />";
										return input;
									}
								}
							},
							{
								"aTargets" : [ 7 ],
								"mRender" : function(data, type, item, full) { 
									if (startIndex + full.row == 2) {
										return item.value7;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id7 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value7 + " />";
										return input;
									}
								}
							},
							{
								"aTargets" : [ 8 ],
								"mRender" : function(data, type, item, full) {
									if (startIndex + full.row == 2) {
										return item.value8;
									} else {
										var input = "<input type='hidden' style='width:60px;height:25px' value=" + item.id8 + " />" 
												+ "<input type='text' maxlength='3' style='width:60px;height:25px' value=" + item.value8 + " />";
										return input;
									}
								}
							}]
						}
				);
		});
	
	function reset() {
		for(var i=1; i<9; i++){
 			$('tr:eq(2)').find('td:eq('+i+')').find('input')[1].value = 0;
 			$('tr:eq(3)').find('td:eq('+i+')').find('input')[1].value = 1;
 		}
	};
	
	function save() {
 		var array = new Array(8);
 		var regular1 =  /^[0-9]+[0-9]*]*$/;  //判断整数 
 		var regular2 =  /^[1-9]+[0-9]*]*$/;  //判断正整数 
 		for(var i=1; i<9; i++){
 			var item = {};
 			item.id = $('tr:eq(2)').find('td:eq('+i+')').find('input')[0].value;
 			item.dailyScore = $('tr:eq(2)').find('td:eq('+i+')').find('input')[1].value;
 			item.count = $('tr:eq(3)').find('td:eq('+i+')').find('input')[1].value;
 			if (!regular1.test(item.dailyScore) || parseInt(item.dailyScore)>999) {
 				alert('单日最高值请输入0到999之间整数');
 				return;
 			}
 			if (!regular2.test(item.count) || parseInt(item.count)<1 
					&& parseInt(item.count)>999) {
 				alert('次数请输入1到999之间正整数');
 				return;
 			}
 			item.score = item.dailyScore/item.count;
 			if (item.dailyScore) {
 				totalScore = totalScore + parseInt(item.dailyScore);
 				array[i-1] = item;
 			}
 		}
 		if (totalScore == 100) {
 			saveStandard(array);
 		} else {
 			alert("总分值不能为 " + totalScore + "分，请配置为100分。");
 			totalScore = 0;
 		}
	};
	
	function saveStandard(array){
		$.ajax({
			type : "post",
			url : _ctx + "/back/frame/evaluate/agnet/agentStandard/saveStandard",
			data : JSON.stringify(array),
			datatype : "json",
			contentType : 'application/json',
			success : function(result){
				if(result.status!=200){
					setErrorContent(result.message);
				}else{
					location.reload();
				}
			}
		})
	}
</script>
