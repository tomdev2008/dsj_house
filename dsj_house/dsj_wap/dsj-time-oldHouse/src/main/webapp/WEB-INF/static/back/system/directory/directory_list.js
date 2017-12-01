
var ids;
function delDirectorys(){
	  ids = new Array();
	  $("input[name=checkitem]:checked").each(function(){
		  ids.push($(this).val());
	  });
	  if(ids.length == 0){
		  setErrorContent("请选择要删除的字典");
	  }else{
		  setModalContent("确认删除选中字典?","delFlatModalConfirm");
	  }
}
function addDirectory(){
	location=_ctx+"/back/frame/houseDirectory/to_add_edit";
}
function editDirectory(param){
	location=_ctx+"/back/frame/houseDirectory/to_add_edit?id="+param;
}
