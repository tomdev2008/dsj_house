<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<div class="row">
			<ul class="nav nav-tabs button_tab" role="tablist">
				<li role="presentation" class="active"><a
					href="${ctx }/back/frame/evaluate/agent/agentStandard">基础分</a></li>
				<li role="presentation"><a
					href="${ctx }/back/frame/evaluate/agent/agentStandard/business">业务分</a></li>
				<li role="presentation"><a
					href="${ctx }/back/frame/evaluate/agent/agentStandard/evaluate">评价分</a></li>
			</ul>
		</div>
		<div class="form-group">
			<span class="wenzi6">基础分</span>
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
			                 <th colspan="3">学历</th>
			                 <th colspan="2">从业年限</th>
						</tr>
						<tr>
			                 <th>细项</th>
			                 <th>本科及以上</th>
			                 <th>专科</th>
			                 <th>高中以下</th>
			                 <th>3年以上</th>
			                 <th>3年以下</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<shiro:hasPermission name='baseStandardSave:evelopers'><button class="btn btn-primary" type="button" onclick="save()">保存</button></shiro:hasPermission>
						<button class="btn btn-default" type="button" onclick="reset()">重置</button>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
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
							+ "/back/frame/evaluate/agnet/agentStandard/baseList",
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
								"mRender" : function(data, type, row, full) {
									var startIndex = full.settings._iDisplayStart;  
					        		if (startIndex + full.row == 0) {
					        			text = "次数";
					        		} else {
					        			text = "分值";
					        		}
									return text;
								}
							},
							{
								"aTargets" : [ 1 ],
								"mRender" : function(data, type, full) {
									var input = "<input type='hidden' style='width:70px;height:30px' value=" + full.id1 + " />" 
											+ "<input type='text' maxlength='4' style='width:70px;height:30px' value=" + full.value1 + " />";
									return input;
								}
							},
							{
								"aTargets" : [ 2 ],
								"mRender" : function(data, type, full) {
									var input = "<input type='hidden' style='width:70px;height:30px' value=" + full.id2 + " />" 
											+ "<input type='text' maxlength='4' style='width:70px;height:30px' value=" + full.value2 + " />";
									return input;
								}
							},
							{
								"aTargets" : [ 3 ],
								"mRender" : function(data, type, full) {
									var input = "<input type='hidden' style='width:70px;height:30px' value=" + full.id3 + " />" 
											+ "<input type='text' maxlength='4' style='width:70px;height:30px' value=" + full.value3 + " />";
									return input;
								}
							},
							{
								"aTargets" : [ 4 ],
								"mRender" : function(data, type, full) {
									var input = "<input type='hidden' style='width:70px;height:30px' value=" + full.id4 + " />" 
											+ "<input type='text' maxlength='4' style='width:70px;height:30px' value=" + full.value4 + " />";
									return input;
								}
							},
							{
								"aTargets" : [ 5 ],
								"mRender" : function(data, type, full) {
									var input = "<input type='hidden' style='width:70px;height:30px' value=" + full.id5 + " />" 
											+ "<input type='text' maxlength='4' style='width:70px;height:30px' value=" + full.value5 + " />";
									return input;
								}
							}]
						}
				);
		});

	function reset() {
		for(var i=1; i<6; i++){
 			$('tr:eq(2)').find('td:eq('+i+')').find('input')[1].value = 1;
 			$('tr:eq(3)').find('td:eq('+i+')').find('input')[1].value = 0;
 		}
	};
	
	function save() {
 		var array = new Array(5);
 		var regular1 =  /^[0-9]+[0-9]*]*$/;  //判断整数 
 		var regular2 =  /^[1-9]+[0-9]*]*$/;  //判断正整数 
 		for(var i=1; i<6; i++){
 			var item = {};
 			item.id = $('tr:eq(2)').find('td:eq('+i+')').find('input')[0].value;
 			item.count = $('tr:eq(2)').find('td:eq('+i+')').find('input')[1].value;
 			item.score = $('tr:eq(3)').find('td:eq('+i+')').find('input')[1].value;
 			if (!regular2.test(item.count) || parseInt(item.count)<1 
 					|| parseInt(item.count)>999) {
 				alert('次数请输入1到999之间正整数');
 				return;
 			}
 			if (i == 1 || i == 4) {
	 			totalScore = totalScore + parseInt(item.score);
	 		}
 			if (!regular1.test(item.score) || parseInt(item.score)>999) {
 				alert('分值请输入0到999之间整数');
 				return;
 			}
 			if (item.count) {
 				array[i-1] = item;
 			}
 		}
 		if (totalScore == 100) {
 			saveStandard(array);
 		} else {
 			alert("基础分总分值不能为 " + totalScore + "分，请配置为100分。");
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
