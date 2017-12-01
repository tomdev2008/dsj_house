$(function() {
	/**
	 * 全选
	 */
	checkboxAllFunc("#checkall", "[name=checkitem]", document);

	$("#reset_btn").on("click", function() {
		$("#searchFromId").reset();
	})

	// 点击查询重新加载
	$("#search_btn").click(function() {
		$("#dataTable").DataTable().ajax.reload();
	});

	$("body").on("click", "#restoreOriginModalConfirm", function() {
		var _ids = ids;
		$.ajax({
			type : "post",
			async : true,
			data : {
				"ids" : _ids
			},
			dataType : "json",
			url : _ctx + "/back/frame/rentHouse/general/origin/restore_origin",
			success : function(resultVo) {
				if (resultVo.status != 200) {
					setErrorContent(resultVo.message);
				} else {
					$("#dataTable").DataTable().ajax.reload();
				}
			}
		})
	});

	// 日期点击事件绑定
	setLayDate();

});

function showOrigin(id) {
	location = _ctx + "/back/frame/rentHouse/general/origin/to_origin_add?show=yes&id="+id;
}

var ids;
function restoreOrigin(id) {
	ids = new Array();
	ids.push(id);
	setModalContent("确认还原该项吗?", "restoreOriginModalConfirm");
}

function restoreOrigins() {
	ids = new Array();
	$("input[name=checkitem]:checked").each(function() {
		ids.push($(this).val());
	});
	if (ids.length == 0) {
		setErrorContent("还没有选择选项！");
	} else {
		setModalContent("确认还原选中项吗?", "restoreOriginModalConfirm");
	}
}
