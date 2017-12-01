<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
					<div class="white-head">
						<h1 class="page-title txt-color-blueDark">
							<!-- PAGE HEADER -->
							编辑户型
						</h1>
						<!-- breadcrumb -->
						<ol class="breadcrumb">
							<li>新房管理</li>
							<li>户型查询</li>
							<li>编辑户型</li>
						</ol>
						<!-- end breadcrumb -->
						<form class="form-horizontal" id="layoutForm">
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 基本信息</div>
						    </div>
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2">
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">户型名称:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="houseName" value="${newHouseType.houseName}" placeholder="户型名称" data-validate="required">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">预售状态:</label>
						                <div class="col-sm-6">
						                	<label class="checkbox-inline">
						                      <input type="radio" name="houseStatus" value="1" <c:if test="${newHouseType.houseStatus == 1}">checked</c:if>> 主推
						                    </label>
						                    <label class="checkbox-inline">
						                      <input type="radio" name="houseStatus" value="2" <c:if test="${newHouseType.houseStatus == 2}">checked</c:if>>待售
						                    </label>
						                    <label class="checkbox-inline">
						                      <input type="radio" name="houseStatus" value="3" <c:if test="${newHouseType.houseStatus == 3}">checked</c:if>> 在售
						                    </label>
						                     <label class="checkbox-inline">
						                      <input type="radio" name="houseStatus" value="4" <c:if test="${newHouseType.houseStatus == 4}">checked</c:if>> 售完
						                    </label>
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">物业类型:</label>
						                <div class="col-sm-6" style="width:80%">
						                	<c:forEach var="item" items="${wyList}"> 
					                      	 <label class="checkbox-inline">
						                      	 <input type="radio"  name="wyType" value="${item.wyType}" <c:if test="${newHouseType.wyType == item.wyType}">checked</c:if>>${item.wyTypeName}
						                    </label>
					                      </c:forEach>
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">居室:</label>
						                <div class="col-sm-6" style="width:80%">
						                <select class="form-control dsj-inline" style="width:100px" name="room" id="room">
				                          <option value="--请选择--">--请选择--</option>
				                          <option value="0"  <c:if test="${newHouseType.room == 0}">selected</c:if> >0</option>
				                         <option value="1" <c:if test="${newHouseType.room == 1}">selected</c:if>>1</option>
				                         <option value="2" <c:if test="${newHouseType.room == 2}">selected</c:if>>2</option>
				                         <option value="3" <c:if test="${newHouseType.room == 3}">selected</c:if>>3</option>
				                          <option value="4" <c:if test="${newHouseType.room == 4}">selected</c:if>>4</option>
				                         <option value="5" <c:if test="${newHouseType.room == 5}">selected</c:if>>5</option>
				                         <option value="6" <c:if test="${newHouseType.room == 6}">selected</c:if>>6</option>
				                          <option value="7" <c:if test="${newHouseType.room == 7}">selected</c:if>>7</option>
				                         <option value="8" <c:if test="${newHouseType.room == 8}">selected</c:if>>8</option>
				                         <option value="9" <c:if test="${newHouseType.room == 9}">selected</c:if>>9</option>
				                         <option value="10" <c:if test="${newHouseType.room == 10}">selected</c:if>>10</option>
			                             </select>室
			                             <select class="form-control dsj-inline" style="width:100px" name="hall" >
				                          <option value="--请选择--">--请选择--</option>
				                          <option value="0" <c:if test="${newHouseType.hall == 0}">selected</c:if>>0</option>
				                         <option value="1" <c:if test="${newHouseType.hall == 1}">selected</c:if>>1</option>
				                         <option value="2" <c:if test="${newHouseType.hall == 2}">selected</c:if>>2</option>
				                         <option value="3" <c:if test="${newHouseType.hall == 3}">selected</c:if>>3</option>
				                          <option value="4" <c:if test="${newHouseType.hall ==4}">selected</c:if>>4</option>
				                         <option value="5" <c:if test="${newHouseType.hall == 5}">selected</c:if>>5</option>
				                         <option value="6" <c:if test="${newHouseType.hall == 6}">selected</c:if>>6</option>
				                          <option value="7" <c:if test="${newHouseType.hall ==7}">selected</c:if>>7</option>
				                         <option value="8" <c:if test="${newHouseType.hall == 8}">selected</c:if>>8</option>
				                         <option value="9" <c:if test="${newHouseType.hall == 9}">selected</c:if>>9</option>
				                         <option value="10" <c:if test="${newHouseType.hall == 10}">selected</c:if>>10</option>
			                             </select>厅
			                              <select class="form-control dsj-inline" style="width:100px" name="toilet" id="toilet">
				                          <option value="--请选择--">--请选择--</option>
				                          <option value="0" <c:if test="${newHouseType.toilet == 0}">selected</c:if>>0</option>
				                         <option value="1" <c:if test="${newHouseType.toilet == 1}">selected</c:if>>1</option>
				                         <option value="2" <c:if test="${newHouseType.toilet == 2}">selected</c:if>>2</option>
				                         <option value="3" <c:if test="${newHouseType.toilet == 3}">selected</c:if>>3</option>
				                          <option value="4" <c:if test="${newHouseType.toilet == 4}">selected</c:if>>4</option>
				                         <option value="5" <c:if test="${newHouseType.toilet == 5}">selected</c:if>>5</option>
				                         <option value="6" <c:if test="${newHouseType.toilet == 6}">selected</c:if>>6</option>
				                          <option value="7" <c:if test="${newHouseType.toilet == 7}">selected</c:if>>7</option>
				                         <option value="8" <c:if test="${newHouseType.toilet == 8}">selected</c:if>>8</option>
				                         <option value="9" <c:if test="${newHouseType.toilet == 9}">selected</c:if>>9</option>
				                         <option value="10" <c:if test="${newHouseType.toilet == 10}">selected</c:if>>10</option>
			                             </select>卫
			                              <select class="form-control dsj-inline" style="width:100px" name="kitchen" id="kitchen">
				                          <option value="--请选择--">--请选择--</option>
				                          <option value="0" <c:if test="${newHouseType.kitchen == 0}">selected</c:if>>0</option>
				                         <option value="1" <c:if test="${newHouseType.kitchen == 1}">selected</c:if>>1</option>
				                         <option value="2" <c:if test="${newHouseType.kitchen == 2}">selected</c:if>>2</option>
				                         <option value="3" <c:if test="${newHouseType.kitchen == 3}">selected</c:if>>3</option>
				                          <option value="4" <c:if test="${newHouseType.kitchen == 4}">selected</c:if>>4</option>
				                         <option value="5" <c:if test="${newHouseType.kitchen == 5}">selected</c:if>>5</option>
				                         <option value="6" <c:if test="${newHouseType.kitchen == 6}">selected</c:if>>6</option>
				                          <option value="7" <c:if test="${newHouseType.kitchen == 7}">selected</c:if>>7</option>
				                         <option value="8" <c:if test="${newHouseType.kitchen == 8}">selected</c:if>>8</option>
				                         <option value="9" <c:if test="${newHouseType.kitchen == 9}">selected</c:if>>9</option>
				                         <option value="10" <c:if test="${newHouseType.kitchen == 10}">selected</c:if>>10</option>
			                             </select>厨
						                </div>
						            </div>
						             <div class="form-group form-inline" id="bigReferencePriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">参考价格:</label>
						                <input type="text" class="form-control" name="referencePrice" value="${newHouseType.referencePrice}" placeholder="参考价格" data-validate="isPrice">
						            </div>
						            <div class="form-group form-inline" id="bigReferencePriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">建筑面积:</label>
						                <input type="text" class="form-control" name="builtUp" value="${newHouseType.builtUp}" placeholder="建筑面积" data-validate="isPrice">
						            </div>
						            
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">朝向:</label>
						                <div class="col-sm-6" style="width:80%">
						                <select class="form-control dsj-inline" style="width:100px" name="orientations">
						                <c:forEach var="item" items="${orientations}"> 
				                         <option value="${item.key}" <c:if test="${newHouseType.orientations == item.key}">selected</c:if>>${item.value}</option>
				                        </c:forEach>
			                             </select>
						            </div>
						             <div class="form-group form-inline" id="bigReferencePriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">层高:</label>
						                <input type="text" class="form-control" name="floor" value="${newHouseType.floor}" placeholder="层高" data-validate="required">
						            </div>
						             <div class="form-group form-inline" id="bigReferencePriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">户型分部:</label>
						                <input type="text" style="width:80%" class="form-control" name="distribution" value="${newHouseType.distribution}" placeholder="户型分部" data-validate="required">
						            </div>
						             <div class="form-group form-inline" id="bigReferencePriceDiv">
						                <label for="loupantype" class="col-sm-2 control-label">户型描述:</label>
						                <textarea rows="4" style="width:80%" name="describes" maxlength="200" placeholder="户型描述,最多可输入200字" data-validate="required">${newHouseType.describes}</textarea>
						            </div>
						            <div class="form-group">
		                                 <label  class="col-sm-2 control-label">上传户型照片</label>
						                 <div class="col-sm-6">
							                 <img id="companyLicensePhotoShowImg" style="width: 80px;height: 80px;" src="${newHouseType.imgUrl}"/>
							                 <div id="companyLicensePhotoFilePicker" style="display: inline-block;"> 
							     				 <button class="btn btn-primary" type="button" >选择图片</button>            
							                 </div>
							                 <input name="imgUrl" value="${newHouseType.imgUrl}" style="width: 0px;border: 0px;" type="text" id="companyLicensePhotoFileUrl"  data-validate="required">
						                 </div>
		                           </div>
						    </div>
						</form>
						   <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" onclick="updateAddNewHouse();" type="button" id="submitId">保存</button>
<!-- 		                     <button class="btn btn-default" onclick="cancelLayout();" type="button">取消</button> -->
							<button class="btn btn-primary" type="button" onclick="javascript:window.history.go( -1 );">上一步</button>
		                </div>
					</div>
				</div>
<script src="${ctx}/static/back/newHouse/edit/newHouse_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
<script>
   $(function(){
	   
   })
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
function cancelLayout(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/newHouse/edit/newHouse_houseTypeList?newHouseId=${newHouseId}";
}
function updateAddNewHouse(){
	 $("#layoutForm").validate(function (result) {
		  	if(result){
		  		$("#popup_box").show();
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#layoutForm").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/newHouse/edit/updateAddNewHouse?id=${houseId}",
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
	 })
}
</script>