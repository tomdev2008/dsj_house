<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/UploadId.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/crop.css">
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			权证代办人账号新增
		</h1>
		<ol class="breadcrumb">
			<li>权证代办人账号管理</li>
			<li>新增 </li>
		</ol>
		<form class="form-horizontal" id="propertyAddForm"  onsubmit="return false">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		        	<div class="form-group">
		                <label  class="col-sm-2 control-label">上传头像</label>
		                 <div class="col-sm-6">
					          <button type ="button" id="picker" class="btn btn-primary dsj_button">上传图片</button>
					          <span><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>  上传文件应大于20K，小于600K</span>
					     </div>
					        <div class="crop_container row" style="display: none;">
					          <div class="col-sm-6">
					            <div class="component">
					              <div class="overlay">
					              </div>
					              <img class="resize-image-small" style="display: none;" src="" alt="image for resizing">
					            </div>
					          </div>
					          <div class="col-sm-6">
					            <div class="component small">
					              <div class="overlay">
					              </div>
					              <img class="resize-image" style="display: none;" src="" alt="image for resizing">
					            </div>
					          </div>
					        </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">头像原图</label>
		                <div class="col-sm-6">
			                 <img id="propertyPhotoShowImg" style="width: 80px;height: 80px;"/>
			                 <div id="propertyPhotoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="avatarMasterUrl" style="width: 0px;border: 0px;" type="text" id="propertyPhotoFileUrl"  data-validate="required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
		                <div class="col-sm-6">
		                  <input name="name" type="text" class="form-control" placeholder="姓名" data-validate="required,maxLength5">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">手&nbsp;&nbsp;机&nbsp;&nbsp;号</label>
		                <div class="col-sm-6">
		                  <input name="tellPhone" type="text" class="form-control" placeholder="手机号码" data-validate="required,phone_new"/>
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家</label>
		                <div class="col-sm-6">
		                  <select name="company" class="form-control dsj-inline" onchange="dianJi(this)" style="width:330px">
				                      <option value="">请选择</option>
				                       <c:forEach items="${companyLisy }" var="company">
				                       		<option value="${company.id }">${company.companyName }</option>
				                       </c:forEach>
			              </select>
			              <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="companyName" name="companyName" data-validate="required"> 
		                </div>
		            </div>
		            <div class="form-group">
						                <label for="loupantype" class="col-sm-2 control-label">业务范围:</label>
						                <div class="col-sm-10" >
						                	 <c:forEach var="item" items="${fwSpuList}">
					                      	 <label class="checkbox-inline">
						                      	 <input type="checkbox"  name="business" onclick="selectWyType(this,'${item.name}','businessName')" value="${item.id}">${item.name}
						                    </label>
					                      </c:forEach>
					                      <input type="text" id="businessNameValidate"  style="width: 0px; border: 0px;" data-validate="required">
						                </div>
						            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">服务区域</label>
		              	    <div class="col-sm-6">
			                	<select  id="areaOneId" name="province" onchange="getTwoArea()" class="form-control dsj-inline" style="width:100px">
				                      <option value="">请选择</option>
				                       <c:forEach items="${firstAreaList }" var="area">
				                       	 	<c:if test="${area.areaCode!=1 }">
				                        	 	<option value="${area.areaCode }">${area.name }</option>
				                        	 </c:if>
				                        </c:forEach>
			               	   </select>
								 <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="provinceName" name="provinceName" > 
	
	
			                    <select  id="areaTwoId" name="city" onchange="getThreeArea()" class="form-control dsj-inline" style="width:100px">
				                      <option value="">请选择</option>
			                    </select>
			                    <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="cityName" name="cityName" > 
			                   
			                   
			                    <select id="areaThreeId" class="form-control dsj-inline" style="width:100px" onchange="quanXuan(this)">
											<option value="">请选择</option>
								</select>
			                    <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="authAreaThreeId"  data-validate="required"> 
			                    <input style="width: 0px;border-color: rgba(255, 255, 255, 0);" type="text" id="areaId" name="area" data-validate="required"> 
		                    </div>
		                    
		            </div>
		            <div id="div">
		            	
		            </div>
		            
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">接&nbsp;&nbsp;单&nbsp;&nbsp;数</label>
		                <div class="col-sm-6">
		                  <input name="deal" type="text" class="form-control" placeholder="接单数" data-validate="isNum">
		                </div>
		            </div>
		          <input type="hidden" id="avatarUrl" name="avatarUrl">
		          <input type="hidden" id="avatarReUrl" name="avatarReUrl">
		            <div class="text-center">
	                     <button class="btn btn-primary" type="button" onclick="saveProperty(this)">确认</button>
	                     <button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script src="${ctx}/static/back/system/property/property_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
<script src="${ctx}/static/back/js/crop.js"></script>
<script src="${ctx}/static/back/js/base64-binary.js"></script>
<script type="text/javascript">
// 获取二级地区  如获取标准地域  isCustom=1 
function getTwoArea(isCustom){
	var classid=$("#areaOneId").val();
	var name = $("#areaOneId").find("option:selected").text();
	$("#provinceName").val(name);
	$("#areaTwoId").empty();
	$("#areaTwoId").append("<option value=''>请选择</option>");
	if($("#areaThreeId").val()){
		$("#areaThreeId").empty();
		$("#areaThreeId").append("<option value=''>请选择</option>");
	}
	if($("#areaFourId").val()){
		$("#areaFourId").empty();
		$("#areaFourId").append("<option value=''>请选择</option>");
	}
	
	if(classid!=""){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/area_list",
				data:{
					areaCode:classid,
					isCustom:isCustom
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							$("#areaTwoId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
}

//获取三级地区 如获取标准地域  isCustom=1
function getThreeArea(isCustom){
	var classid=$("#areaTwoId").val();
	var name = $("#areaTwoId").find("option:selected").text();
	$("#cityName").val(name);
	$("#areaThreeId").empty();
	$("#areaThreeId").append("<option value=''>请选择</option>");
	if($("#areaFourId").val()){
		$("#areaFourId").empty();
		$("#areaFourId").append("<option value=''>请选择</option>");
	}
	if(classid!=""){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/area_list",
				data:{
					areaCode:classid,
					isCustom:1
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						//$("#areaThreeId").append("<option id='xuan' value='quan'>全选</option>");
						for(var i in list){
							$("#areaThreeId").append("<option id='chengShi' value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
	
}
function quanXuan(_this){
	
	id =$(_this).find("option:selected").val();
	name =$(_this).find("option:selected").text();
	/* if(name=="全选"){
		var ids = $("option[id='chengShi']");
		ids.each(function(x,y){
			showSelectArea(y.value,y.text)
		});
	}else  */
	if(name=="请选择"){
		return ;
	}else{
		showSelectArea(id,name);
	}
}
var ids = "";
function showSelectArea(id,name){
	if(!((ids+",").indexOf(","+id+",")>-1)){
		if(ids.length<=14){		//只能选择三个区域的判读
			ids+=','+id;
			$("#div").append("<span id='"+id+"'>"+name+"<input onclick='quDiao("+id+")' type='button' value='x'/></span>");	
		}else{
			setErrorContent("只能选择三个区域");
		}
		$("#areaId").val(ids);
	}
}
function quDiao(id){
	$("#div").find("#"+id).detach();
	var is = $("#areaId").val().replace(','+id,'');
	$("#areaId").val(is);
	ids = is;
}
function dianJi(_this){
	var name = $(_this).find("option:selected").text();
	$("#companyName").val(name);
}

$("#picker").one("click",function(argument) {
    $(".crop_container").show();
  })
  var $wrap = $('#wrap');
  var uploader = WebUploader.create({

      // swf文件路径
      // swf: '/js/Uploader.swf',

      // 文件接收服务端。
      // server: 'http://webuploader.duapp.com/server/fileupload.php',

      // 选择文件的按钮。可选。
      // 内部根据当前运行是创建，可能是input元素，也可能是flash.
      pick: '#picker',

      // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
      resize: false
  });
  var small,big;
  uploader.on( 'fileQueued', function( file ) {
      uploader.makeThumb( file, function( error, src ) {
          if ( error ) {
              alert("抱歉无法预览！")
              return;
          }
          if (small != undefined) {
              small.changeSrc(src);
          }else {
              small = resizeableImage('.resize-image-small')();
              small.changeSrc(src);
              $('.resize-image-small').show();
          }

      }, 350, 350 );
      uploader.makeThumb( file, function( error, src ) {
          if ( error ) {
              alert("抱歉无法预览！")
              return;
          }
          if (big != undefined) {
              big.changeSrc(src);
          }else {
              big = resizeableImage('.resize-image')();
              big.changeSrc(src);
              $('.resize-image').show();
          }

      }, 258, 292 );
  });
  

  function saveProperty(obj){
  	authThreeIdFun();
  	 $("#propertyAddForm").validate(function (result) {
  		  	if(result){
  		  		$(obj).prop('disabled',true);
  		  		$("#avatarUrl").val(small.crop());
  		  		$("#avatarReUrl").val(big.crop());
  		  		$.ajax({
  		  			type:"post",
  		  			async:true,
  		  			data:$("#propertyAddForm").serialize(),
  		  			dataType:"json",
  		  			url:_ctx+"/back/system/property/save_property_add",
  		  			success:function(resultVo){
  		  				if(resultVo.status!=200){
  							 setErrorContent(resultVo.message);
  							 $(obj).prop('disabled',false);
  						}else{
  							location=_ctx+"/back/frame/system/property/propertyList"
  						}
  		  			}
  		  		})
  		  	}
  	 })
  }
  
	$(function(){
		singleUpload("propertyPhotoFilePicker","propertyPhotoFileUrl","propertyPhotoShowImg",beforeUpload,afterUpload);
	})
	function beforeUpload()
	{
	    loading_szyq("上传中...",true);
	}

	function afterUpload()
	{
	    loading_szyq(false);
	}
  
  
</script>