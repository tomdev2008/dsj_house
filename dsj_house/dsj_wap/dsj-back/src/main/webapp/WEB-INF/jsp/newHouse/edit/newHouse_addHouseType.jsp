<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
	<div class="col-xs-12 padding-foot">
			<div class="white-head">
				<div class="row">
					<div class="form-bootstrapWizard">
						<ul class="bootstrapWizard form-wizard">
							<li  data-target="#step1" class="active">
								<span class="step">1</span> <span class="title">基本信息</span>
							</li>
							<li data-target="#step2" class="active">
								<span class="step">2</span> <span class="title">楼盘相册</span>
							</li>
							<li data-target="#step3" class="active">
								<span class="step">3</span> <span class="title">楼盘户型</span>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
				<form class="form-horizontal" id="layoutForm" style="margin-top:50px;">
				    <input type="hidden" value="${newHouseId}" name="dicId" id="dicId">
				    <div class="row">
				        <div class="col-xs-8 col-xs-offset-2">
				            <div class="form-group">
				                <label  class="col-sm-2 control-label">户型名称:</label>
				                <div class="col-sm-6">
				                  <input type="text" class="form-control" name="houseName" placeholder="户型名称" id="houseName" >
				                </div>
				            </div>
				             <div class="form-group">
				                <label  class="col-sm-2 control-label">预售状态:</label>
				                <div class="col-sm-6">
				                	<label class="checkbox-inline">
				                      <input type="radio" name="houseStatus" value="1" checked="checked"> 主推
				                    </label>
				                    <label class="checkbox-inline">
				                      <input type="radio" name="houseStatus" value="2">待售
				                    </label>
				                    <label class="checkbox-inline">
				                      <input type="radio" name="houseStatus" value="3"> 在售
				                    </label>
				                     <label class="checkbox-inline">
				                      <input type="radio" name="houseStatus" value="4"> 售完
				                    </label>
				                </div>
				            </div>
				             <div class="form-group">
				                <label  class="col-sm-2 control-label">物业类型:</label>
				                <div class="col-sm-6" style="width:80%">
				                	<c:forEach var="item" items="${wyList}"> 
			                      	 <label class="checkbox-inline">
				                      	 <input type="radio"  name="wyType" value="${item.wyType}">${item.wyTypeName}
				                      	 <input type="hidden" name="wyTypeName" value="${item.wyTypeName}">
				                    </label>
			                      </c:forEach>
				                </div>
				            </div>
				            <div class="form-group">
				                <label  class="col-sm-2 control-label">居室:</label>
				                <div class="col-sm-6" style="width:80%">
				                <select class="form-control dsj-inline" style="width:100px" name="room" id="room">
		                          <option value="">--请选择--</option>
		                         <option value="1">1</option>
		                         <option value="2">2</option>  
		                         <option value="3">3</option>
		                          <option value="4">4</option>
		                         <option value="5">5</option>
		                         <option value="6">6</option>
		                          <option value="7">7</option>
		                         <option value="8">8</option>
		                         <option value="9">9</option>
		                         <option value="10">10</option>
	                             </select>室
	                             <select class="form-control dsj-inline" style="width:100px" name="hall" id="hall"> 
		                          <option value="">--请选择--</option>
		                          <option value="0">0</option>
		                         <option value="1">1</option>
		                         <option value="2">2</option>
		                         <option value="3">3</option>
		                          <option value="4">4</option>
		                         <option value="5">5</option>
		                         <option value="6">6</option>
		                          <option value="7">7</option>
		                         <option value="8">8</option>
		                         <option value="9">9</option>
		                         <option value="10">10</option>
	                             </select>厅
	                              <select class="form-control dsj-inline" style="width:100px" name="toilet" id="toilet" >
		                          <option value="">--请选择--</option>
		                          <option value="0">0</option>
		                         <option value="1">1</option>
		                         <option value="2">2</option>
		                         <option value="3">3</option>
		                          <option value="4">4</option>
		                         <option value="5">5</option>
		                         <option value="6">6</option>
		                          <option value="7">7</option>
		                         <option value="8">8</option>
		                         <option value="9">9</option>
		                         <option value="10">10</option>
	                             </select>卫
	                              <select class="form-control dsj-inline" style="width:100px" name="kitchen" id="kitchen">
		                          <option value="">--请选择--</option>
		                          <option value="0">0</option>
		                         <option value="1">1</option>
		                         <option value="2">2</option>
		                         <option value="3">3</option>
		                          <option value="4">4</option>
		                         <option value="5">5</option>
		                         <option value="6">6</option>
		                          <option value="7">7</option>
		                         <option value="8">8</option>
		                         <option value="9">9</option>
		                         <option value="10">10</option>
	                             </select>厨
				                </div>
				            </div>
				             <div class="form-group" >
				                <label  class="col-sm-2 control-label">参考总价:</label>
				                <div class="col-sm-6">
				                <input type="text" style="display: inline-block;width:300px;" class="form-control" id="referencePrice" name="referencePrice" placeholder="参考价格" >万元
				                </div>
				            </div>
				            <div class="form-group" >
				                <label  class="col-sm-2 control-label">建筑面积:</label>
				                <div class="col-sm-6">
				                	<input type="text" style="display: inline-block;width:300px;" class="form-control" id="builtUp" name="builtUp" placeholder="建筑面积" >m²
				                </div>
				            </div>
				            
				             <div class="form-group">
				                <label  class="col-sm-2 control-label">朝向:</label>
				                 <div class="col-sm-6" style="width:80%">
				                     
			                      	 <select class="form-control dsj-inline" style="width:100px" name="orientations" id="orientations">
				                      	 <option value="">--请选择--</option>
				                      	 <c:forEach var="item" items="${orientations}"> 
				                      	 <option value="${item.key}">${item.value}</option>
				                      	 </c:forEach>
				                     </select>
			                         
			                         </div>
				            	</div>
				             <div class="form-group" >
				                <label  class="col-sm-2 control-label">层高:</label>
				                <div class="col-sm-6">
				                <input type="number" style="display: inline-block;width:300px;" class="form-control" name="floor" id="floor" placeholder="层高" >米
				                </div>
				            </div>
				             <div class="form-group" >
				                <label  class="col-sm-2 control-label">户型分布:</label>
				                <div class="col-sm-6">
				                <input type="text" style="width:80%" class="form-control" id="distribution" name="distribution" placeholder="户型分部">
				                </div>
				            </div>
				             <div class="form-group" >
				                <label  class="col-sm-2 control-label">户型描述:</label>
				                <div class="col-sm-6">
				                <textarea rows="4" style="width:80%" id="describes" name="describes" maxlength="200" placeholder="户型描述,最多可输入200字" ></textarea>
				                </div>
				            </div>
				            <div class="form-group">
                                 <label  class="col-sm-2 control-label">户型照片:</label>
				                 <div class="col-sm-6">
					                 <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;"/>
					                 <div id="companyLicensePhotoFilePicker" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="imgUrl" style="width: 0px;border: 0px;" type="text" id="companyLicensePhotoFileUrl" >
				                 </div>
                           </div>
                         </div>
                        </div>
                        </form>
				    </div>
				   <div class="btngroup row mt20 text-center">
                     <button class="btn btn-primary" onclick="saveAddlayout();" type="button" id="submitId">保存</button>
<!--                      <button class="btn btn-default" onclick="cancelLayout();" type="button">取消</button> -->
					<button class="btn btn-primary" type="button" onclick="javascript:window.history.go( -1 );">上一步</button>
                </div>
			</div>
		</div>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
<script>
$(function(){
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

function saveAddlayout(){
	var dicId=$("#dicId").val();
	var houseName=$("#houseName").val();
	var houseStatus=$("input[name='houseStatus']:checked").val();
	var wyType=$("input[name='wyType']:checked").val();
	if(wyType==undefined){
		setErrorContent("物业类型必选");
		return;
	}
	var room=$('#room option:selected').val();
	if(room==""){
		setErrorContent("居室必选");
		return;
	}
	
	
	var hall=$('#hall option:selected').val();
	var toilet=$('#toilet option:selected').val();
	var kitchen=$('#kitchen option:selected').val();
	var referencePrice=$("#referencePrice").val();
	var re = /^[0-9]*[1-9][0-9]*$/;
	if(referencePrice=="" || referencePrice==null){
	}else{
		if(!re.test(referencePrice)){
			setErrorContent("参考价格必须正整数");
			return;
		}
	}
	
	var builtUp=$("#builtUp").val();
	if(builtUp==null || builtUp==""){
	}else{
		if ( !/^\d+|\d+\.\d{1,2}$/gi.test( builtUp ) ) {
			 setErrorContent("建筑面积不合法");
		      return false; 
		  }
	}	
	var orientations=$('#orientations option:selected').val();
	var orientationsName;
	if(orientations!=""){
		orientationsName= $("#orientations option:selected").text();
	}
	var floor=$("#floor").val();
	if(floor==""){
	}else if(floor>100){
		 setErrorContent("层高不能大于100米");
		 return;
	}
	
    var distribution=$("#distribution").val();
    var describes=$("#describes").val();
    var imgUrl=$("#companyLicensePhotoFileUrl").val();
	$("#popup_box").show();
	$.ajax({
		type:"post",
		data:{
			houseName:houseName,
			houseStatus:houseStatus,
			wyType:wyType,
			room:room,
			hall:hall,
			toilet:toilet,
			kitchen:kitchen,
			referencePrice:referencePrice,
			builtUp:builtUp,
			orientations:orientations,
			orientationsName:orientationsName,
			floor:floor,
			distribution:distribution,
			describes:describes,
			dicId:dicId,
			imgUrl:imgUrl
		},
		dataType:"json",
		url:_ctx+"/back/newHouse/edit/save_layout_add",
		success:function(resultVo){
			if(resultVo.status!=200){
				$("#popup_box").hide();
			 setErrorContent(resultVo.message);
		}else{
			 location=_ctx+"/back/frame/newHouse/edit/newHouse_houseTypeList?newHouseId=${newHouseId}";
		 }
		}
	})
	 
}


function cancelLayout(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_houseTypeList";
}
</script>