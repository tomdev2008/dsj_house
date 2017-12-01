
function add(){
	 $("#addCompanyForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#addCompanyForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/frame/system/company/addCompany",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
							setTimeout(function(){
								location=_ctx+"/back/frame/system/company/companyList";
							},2000);
							
						}
		  			}
		  		})
		  	}
	 })
}
function addBusiness(){
	 $("#addCompanyForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#addCompanyForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/frame/system/company/addBusiness",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
							setTimeout(function(){
								location=_ctx+"/back/frame/system/company/businessList";
							},2000);
							
						}
		  			}
		  		})
		  	}
	 })
}
function editCompany(){
	 $("#editCompanyForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#editCompanyForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/frame/system/company/updateCompany",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
					
							setTimeout(function(){
								location=_ctx+"/back/frame/system/company/companyList";
							},2000);
						}
		  			}
		  		})
		  	}
	 })
}
function editBusiness(){
	 $("#editCompanyForm").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#editCompanyForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/frame/system/company/updateCompany",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							setErrorContent(result.message);
					
							setTimeout(function(){
								location=_ctx+"/back/frame/system/company/businessList";
							},2000);
						}
		  			}
		  		})
		  	}
	 })
}
$(function(){
     singleUpload("photoFilePicker","photoFileUrl","photoShowImg",beforeUpload,afterUpload);
})
 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }
 
 function cancel(){
	 location=_ctx+"/back/frame/system/company/companyList";
 }
 function cancelBusiness(){
	 location=_ctx+"/back/frame/system/company/businessList";
 }