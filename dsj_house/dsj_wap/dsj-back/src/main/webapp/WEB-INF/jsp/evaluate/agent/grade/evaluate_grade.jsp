<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-content">
		<!--表格 S-->
		<div class="slb_table H_slb_table">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable">
					<thead>
						<tr>
			                 <th>等级名称</th>
			                 <th>综合得分最小值</th>
			                 <th>综合得分最大值</th>
			                 <th>小图标下</th>
			                 <th>大图标</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
						<shiro:hasPermission name='evaluateGradeSave:evelopers'><button class="btn btn-primary" type="button" onclick="save()">保存</button></shiro:hasPermission>
						<button class="btn btn-default" type="button" onclick="reset()">重置</button>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var scoreTemp = 0; 
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
							+ "/back/frame/evaluate/agnet/agentGrade/gradeList",
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
								"mData" : "name",
								"aTargets" : [ 0 ]
							},
							{
								"aTargets" : [ 1 ],
								"mRender" : function(data, type, item, full) {
									var input = null;
									if (full.row == 0) {
										input = "<input type='hidden' style='width:70px;height:30px' value=" + item.id + " />" 
												+ "<input type='text' style='width:70px;height:30px' disabled value=" + item.minScore + " />";
									} else {
										input = "<input type='hidden' style='width:70px;height:30px' value=" + item.id + " />" 
											+ "<input type='text' style='width:70px;height:30px' value=" + item.minScore + " />";
									}
									return input;
								}
							},
							{
								"aTargets" : [ 2 ],
								"mRender" : function(data, type, full) {
									var input = "<input type='text' style='width:70px;height:30px' value=" + full.maxScore + " />";
									return input;
								}
							},
							{
								"aTargets" : [ 3 ],
								"mRender" : function(data, type, full) {
									var input = "<label for='smallIcon" + full.id + "' id='smallIconFilePicker" + full.id + "' style='display: inline-block;'>"
					                 	+ "<img id='smallIconShowImg" + full.id + "' style='width: 80px;height: 80px;' src='" + full.smallIcon + "'/>" 
				     				 	+ "<button class='btn btn-primary' type='button' id='smallIcon" + full.id + "' style='display:none'></button></label>"
				     				 	+ "<input type='hidden' id='smallIconFileUrl" + full.id + "' value='" + full.smallIcon + "'>";
									singleUpload("smallIconFilePicker" + full.id, "smallIconFileUrl" + full.id, "smallIconShowImg"  + full.id, beforeUpload, afterUpload);
									return input;
								}
							},
							{
								"aTargets" : [ 4 ],
								"mRender" : function(data, type, full) {
									var input = "<label for='bigIcon" + full.id + "' id='bigIconFilePicker" + full.id + "' style='display: inline-block;'>"
					                 	+ "<img id='bigIconShowImg" + full.id + "' style='width: 80px;height: 80px;' src='" + full.bigIcon + "'/>" 
				     				 	+ "<button class='btn btn-primary' type='button' id='bigIcon" + full.id + "' style='display:none'></button></label>"
				     				 	+ "<input type='hidden' id='bigIconFileUrl" + full.id + "' value='" + full.bigIcon + "'>";
									singleUpload("bigIconFilePicker" + full.id, "bigIconFileUrl" + full.id, "bigIconShowImg"  + full.id, beforeUpload, afterUpload);
									return input;
								}
							}]
					}
				);
		});
	
	function beforeUpload(){
	     loading_szyq("上传中...",true);
	}

	function afterUpload(){
	     loading_szyq(false);
	}
	
	function reset() {
		for(var i=1; i<6; i++){
 			$('tr:eq('+i+')').find('td:eq(1)').find('input')[1].value = 0;
 			$('tr:eq('+i+')').find('td:eq(2)').find('input')[0].value = 0;
 		}
	};
	
	function save() {
 		var array = new Array(5);
 		for(var i=1; i<6; i++){
 			var item = {};
 			item.id = $('tr:eq('+i+')').find('td:eq(1)').find('input')[0].value;
 			item.minScore = $('tr:eq('+i+')').find('td:eq(1)').find('input')[1].value;
 			item.maxScore = $('tr:eq('+i+')').find('td:eq(2)').find('input')[0].value;
 			item.smallIcon = $('tr:eq('+i+')').find('td:eq(3)').find('input')[1].value;
 			item.bigIcon = $('tr:eq('+i+')').find('td:eq(4)').find('input')[1].value;
 			if (parseInt(item.minScore) >= parseInt(item.maxScore)) {
 				alert("数据设置错误");
 				return;
 			}
 			if (scoreTemp != 0 && scoreTemp > parseInt(item.minScore)) {
 				alert("数据设置错误");
 				return;
 			}
 			scoreTemp = parseInt(item.maxScore);
 			if (item.id) {
 				array[i-1] = item;
 			}
 		}
 		saveStandard(array);
	};
	
	function saveStandard(array){
		$.ajax({
			type : "post",
			url : _ctx + "/back/frame/evaluate/agnet/agentGrade/saveGrade",
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
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>