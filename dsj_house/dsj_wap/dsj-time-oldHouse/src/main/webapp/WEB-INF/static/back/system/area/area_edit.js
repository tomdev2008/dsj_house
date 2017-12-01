$(function(){
	$("body").on("click","#accessFlatModalConfirm",function(){
		var id = $("#userId").val();
		$.ajax({
			type:"post",
			url:_ctx+"/back/system/flat/flats_audit_"+_audit,
			data:{
				ids:id
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location=_ctx+"/back/frame/system/flat/to_flat_audit_list";
				}
			}
		})
	});
})

function flatAccess(){
	setModalContent("确认通过审核?","accessFlatModalConfirm");
}
function flatRefuse(){
	setModalContent("确认要驳回?","accessFlatModalConfirm");
}
function getThreeAreaXZQY(){
	var classid=$("#areaTwoId").val();
	if(classid!=""){
			$.ajax({
				type:"post",
				url:_ctx+"/back/tradeArea/getDataByCityId",
				data:{
					areaCode:classid
				},
				datatype:"json",
				success:function(result){
					if(result.status==200){
						var arr = [];
						var map = result.data;
						var areaList = map["areaList"];
						var tradeList = map["tradeList"];
						var city = map["city"];
						//lv:  2-城市   3-县区    4-商圈
						var pobj = {};
						pobj.id=city.id;
						pobj.pId=city.parentId;
						pobj.name=city.name;
						pobj.open=true;
						pobj.isParent=true;
						pobj.lv=2;
						pobj.isCustom = city.isCustom;
						arr.push(pobj);
						
						for(var i in areaList){
							var obj = {};
							obj.id=areaList[i].id;
							obj.pId=areaList[i].parentId;
							obj.name=areaList[i].name;
							obj.isParent=true;
							obj.lv=3;
							obj.isCustom = areaList[i].isCustom;
							arr.push(obj);
						}
						for(var i in tradeList){
							var obj = {};
							obj.id=tradeList[i].id;
							obj.pId=tradeList[i].parentId;
							obj.name=tradeList[i].name;
							obj.lv=4;
							obj.isCustom = 2;
							arr.push(obj);
						}
						initree(arr);
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
}

	var setting = {
		view: {
			selectedMulti: false
		},
		edit: {
			enable: true,
			showRemoveBtn: true,
			showRenameBtn: false,
			removeTitle : "删除"
		},
		data: {
			simpleData: {
				enable: true
			},
			keep: {
				parent: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,//拖拽操作 return false 禁止拖拽
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRename: onRename,
			onRemove: onRemove
		}
	};

	var zNodes = [];
		
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			setErrorContent("节点名称不能为空");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		//ajax edit
		$.ajax({
			type:"post",
			url:_ctx+"/back/tradeArea/editArea",
			data:{
				id : treeNode.id,
				parentId : treeNode.pId,
				name : newName,
				lv : treeNode.lv
			},
			datatype:"json",
			success:function(result){
				if(result.status==200){
					return true;
				}else{
					setErrorContent(result.message);
					return false;
				}
			}
		})
		
	}
	function onRename(event, treeId, treeNode, isCancel) {
		
	}
	
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		if(treeNode.isCustom==1){
			setErrorContent("只能删除自定义节点");
			return false;
		}
		var res = false;
		if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？")){
			$.ajax({
				type:"post",
				async: false,
				url:_ctx+"/back/tradeArea/deleteArea",
				data:{ 
					lv : treeNode.lv,
					id : treeNode.id
				},
				datatype:"json",
				success:function(result){
					if(result.status==200){
						res = true;
					}else{
						setErrorContent(result.data);
					}
				}
			})
		}else{
			return false;
		}
		return res;
	}
	function onRemove(e, treeId, treeNode) {
		
	}
	
	function remove(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			setErrorContent("请先选择一个节点");
			return;
		}
		zTree.removeNode(treeNode,true);
	};
	
	var newCount = 1;
	function add_3_area() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if(nodes.length == 0 || treeNode.lv!=2){
			setErrorContent("请选择城市节点");
			return false;
		}
		var addId ;
		//获取当前库中最大区域id
		$.ajax({
			type:"post",
			async: false,
			url:_ctx+"/back/tradeArea/getMaxAreaID",
			data:{ lv : treeNode.lv },
			datatype:"json",
			success:function(result){
				if(result.status==200){
					addId = result.data;
				}else{
					setErrorContent(result.message);
				}
			}
		})
		if(!addId){
			setErrorContent("新增异常");
			return false;
		}
		treeNode = zTree.addNodes(treeNode, {id: addId ,
												lv:3,
												isParent:true,
												pId:treeNode.id,
												isCustom : 2,
												name:"新增区域" + (newCount++)});
		//新增
		$.ajax({
			type:"post",
			async: false,
			url:_ctx+"/back/tradeArea/addArea",
			data:{
				id : addId,
				parentId : treeNode.id,
				name : "新增区域" + addId,
				areaCode : addId ,
				lv : 3
			},
			datatype:"json",
			success:function(result){
				if(result.status==200){
					setErrorContent("新增成功");
				}else{
					setErrorContent(result.message);
					return false;
				}
			}
		})
		zTree.editName(treeNode[0]);
	};
	
	function edit_3_area() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if(nodes.length == 0 || treeNode.lv!=3){
			setErrorContent("请选择区域节点");
			return false;
		}
		if(treeNode.isCustom==1){
			setErrorContent("只有自定义区域能编辑");
			return false;
		}
		zTree.editName(treeNode);
	};
	
	function add_4_area() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if(nodes.length == 0 || treeNode.lv!=3){
			setErrorContent("请选择区域节点");
			return false;
		}
		var addId ;
		//获取当前库中最大区域id
		$.ajax({
			type:"post",
			async: false,
			url:_ctx+"/back/tradeArea/getMaxAreaID",
			data:{ lv : treeNode.lv },
			datatype:"json",
			success:function(result){
				if(result.status==200){
					addId = result.data;
				}else{
					setErrorContent(result.message);
					return false;
				}
			}
		})
		if(!addId){
			setErrorContent("新增异常");
			return false;
		}
		treeNode = zTree.addNodes(treeNode, {id:addId,
											lv:4,
											isParent:false,
											pId:treeNode.id,
											isCustom : 2,
											name:"新增商圈" + (newCount++)});
		//新增
		$.ajax({
			type:"post",
			async: false,
			url:_ctx+"/back/tradeArea/addArea",
			data:{
				id : addId,
				parentId : treeNode.id,
				name : "新增区域" + addId,
				areaCode : addId ,
				lv : 4
			},
			datatype:"json",
			success:function(result){
				if(result.status==200){
					setErrorContent("新增成功");
				}else{
					setErrorContent(result.message);
					return false;
				}
			}
		})
		zTree.editName(treeNode[0]);
	};
	
	function edit_4_area() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if(nodes.length == 0 || treeNode.lv!=4){
			setErrorContent("请选择商圈节点");
			return false;
		}
		zTree.editName(treeNode);
	};
	
	$(document).ready(function(){
		//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#add_3_area").bind("click", add_3_area);
		$("#edit_3_area").bind("click", edit_3_area);
		$("#add_4_area").bind("click", add_4_area);
		$("#edit_4_area").bind("click", edit_4_area);
		firstini();
	});
	
	function firstini(){
		getThreeAreaXZQY();
	}
	
	function initree(myNodes){
		$.fn.zTree.init($("#treeDemo"), setting, myNodes);
	}