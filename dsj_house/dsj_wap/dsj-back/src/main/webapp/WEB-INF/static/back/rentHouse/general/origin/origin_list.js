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

	$("body").on("click", "#delOriginModalConfirm", function() {
		var _ids = ids;
		delAjax(_ids);
	});

	$("body").on("click", "#upOrDownOriginModalConfirm", function() {
		var _id = new Array();
		_id.push(udId);
		$.ajax({
			type : "post",
			async : true,
			data : {
				"id" : _id,
				"status" : udstatus
			},
			dataType : "json",
			url : _ctx + "/back/frame/rentHouse/general/origin/origin_upDown",
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
/**
 * 增加房源
 */
function addOrigin() {
	location = _ctx + "/back/frame/rentHouse/general/origin/to_origin_add";
}

function editOrigin(id) {
	location = _ctx + "/back/frame/rentHouse/general/origin/to_origin_add?id="
			+ id;
}

function showOrigin(id) {
	location = _ctx + "/back/frame/rentHouse/general/origin/to_origin_add?show=yes&id="+id;
}

var ids;
function delOriginById(id) {
	ids = new Array();
	ids.push(id);
	setModalContent("确认删除?", "delOriginModalConfirm");
}

function delOrigin() {
	ids = new Array();
	var mark = false;
	$("input[name=checkitem]:checked").each(function() {
		if($(this).attr("data-status")==2){
			mark = true;
		}
		ids.push($(this).val());
	});
	if(mark){
		setErrorContent("上架的房源不可被删除！");
		return false;
	}
	if (ids.length == 0) {
		setErrorContent("还没有选择选项！");
	} else {
		setModalContent("确认批量删除吗?", "delOriginModalConfirm");
	}
}

function delAjax(ids) {
	$.ajax({
		type : "post",
		async : true,
		data : {
			"ids" : ids
		},
		dataType : "json",
		url : _ctx + "/back/frame/rentHouse/general/origin/origin_delete",
		success : function(resultVo) {
			if (resultVo.status != 200) {
				setErrorContent(resultVo.message);
			} else {
				$("#dataTable").DataTable().ajax.reload();
			}
		}
	})
}

var udId;
var udstatus;
function upOrDownOrigin(id, status) {
	udId = id;
	udstatus = status;
	if (udstatus == 2) {
		setModalContent("确认上架该房源吗?", "upOrDownOriginModalConfirm");
	} else if (udstatus == 3) {
		setModalContent("确认下架该房源吗?", "upOrDownOriginModalConfirm");
	}

}