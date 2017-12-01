function addLoupanName(){
	$("#loupanNameId").append('<select class="form-control dsj-inline dicSelectId" name="loupanName" class="js-example-basic-multiple"  data-validate="required"></select>');
	getSelect();
}

function removeLoupanName(){
	if($("#loupanNameId").find("select[name='loupanName']").length>1){
		$("#loupanNameId").find("select[name='loupanName']:last").remove();
		$("#loupanNameId").find("span[dir='ltr']:last").remove();
	}
}

function saveAddEveloper(){
	authThreeIdFun();
	 $("#evelopersAddForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#evelopersAddForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/system/evelopers/save_evelopers_add",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							location=_ctx+"/back/frame/system/evelopers/evelopers_list"
						}
		  			}
		  		})
		  	}
	 })
}
function saveUpdateEveloper(){
	authThreeIdFun();
	 $("#evelopersUpdateForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#evelopersUpdateForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/system/evelopers/save_evelopers_update",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							location=_ctx+"/back/frame/system/evelopers/evelopers_list"
						}
		  			}
		  		})
		  	}
	 })
}
function getSelect(){
	$(".dicSelectId").select2({
		"ajax":{
		    url: _ctx+"/back/newHouse/edit/getElevDic",
		    data: function (params) {
		      var query = { //请求的参数, 关键字和搜索条件之类的
		        name: params.term //select搜索框里面的value
		      }
		      return query;
		    },
		    delay: 500,
		    processResults: function (data, params) {
		      var resu = data.data;
		      resu.push({id:1,text:"其他"});
		      return {
		        results: resu  //必须赋值给results并且必须返回一个obj
		      };
		    }
		  }
	});
}
$(function(){
	 getSelect();
     singleUpload("promisePhotoFilePicker","promisePhotoFileUrl","promisePhotoShowImg",beforeUpload,afterUpload);
     singleUpload("companyLicensePhotoFilePicker","companyLicensePhotoFileUrl","companyLicensePhotoShowImg",beforeUpload,afterUpload);
})
 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }
function cancelFun(type){
	location=_ctx+"/back/frame/system/evelopers/evelopers_list"
}
function authThreeIdFun(){
	if($("#areaThreeId").val()==""){
		$("#authAreaThreeId").val("");
	}else{
		$("#authAreaThreeId").val($("#areaThreeId").val());
	}
}