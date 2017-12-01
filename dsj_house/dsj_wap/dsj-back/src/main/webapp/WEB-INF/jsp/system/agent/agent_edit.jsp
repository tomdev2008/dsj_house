<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<link href="${ctx}/static/front/css/bootstrap-spinner.css" rel="stylesheet">
<link href="${ctx}/static/front/css/selectize.css" rel="stylesheet">
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			经纪人账号编辑
		</h1>
		<ol class="breadcrumb">
			<li>经纪人账号管理</li>
			<li>编辑 </li>
		</ol>
		<form class="form-horizontal" id="agentEditForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">经纪人ID</label>
		                <div class="col-sm-6">
		                  <input name="agentCode" value="${agent.agentCode}"type="text" class="form-control" readonly="readonly" >
		                  <input type="hidden" name="id" value="${agent.id}">
		                  <input type="hidden" name="userId" value="${agent.userId}">
		                  <input type="hidden" name="auditStatus" value="${agent.auditStatus}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传头像</label>
		                 <div class="col-sm-6">
			                 <img id="avatarPhotoShowImg" src="${agent.avatarUrl}"style="width: 80px;height: 80px;"/>
			                 <div id="avatarPhotoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="avatarUrl" data-validate="required" value="${agent.avatarUrl}"style="width: 0px;border: 0px;" type="text" id="avatarPhotoFileUrl" >
		                 </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">姓名</label>
		                <div class="col-sm-6">
		                  <input name="name" type="text" class="form-control" value="${agent.name}" data-validate="maxLength5,required">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">联系方式</label>
		                <div class="col-sm-6">
		                  <input name="tellPhone" type="text" class="form-control" value="${agent.tellPhone}" data-validate="required,phone_new">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">公司</label>
		                <div class="col-sm-6">
		                  <select  name="company"  class="form-control dsj-inline" style="width:200px">
				                       <c:forEach items="${companyList }" var="company">
				                       			<c:if test="${agent.company==company.id}">
				                       				<option selected="selected" value="${company.id }">${company.companyName }</option>
				                       			</c:if>
				                       			<c:if test="${agent.company!=company.id}">
				                       				<option value="${company.id }">${company.companyName }</option>
				                       			</c:if>
				                        	 	
				                        </c:forEach>
			                    </select>
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">所在省市</label>
		              	     <div class="col-sm-6">
			                	<select  id="areaOneId" name="province" onchange="getTwoArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.province}">${agent.provinceName}</option>
				                       <c:forEach items="${firstAreaList }" var="area">
				                       	 	<c:if test="${area.areaCode!=1 }">
				                        	 	<option value="${area.areaCode }">${area.name }</option>
				                        	 </c:if>
				                        </c:forEach>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">市</label>
		              	     <div class="col-sm-6">
								 <select  id="areaTwoId" name="city" onchange="getThreeArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.city}">${agent.cityName}</option>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">行政区</label>
		              	     <div class="col-sm-6">
			                    <select  id="areaThreeId" name="area" onchange="getFourArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.area}">${agent.areaName}</option>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">商圈</label>
		              	     <div class="col-sm-6">
			                    <select  id="areaFourId" name="business"  class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.business}">${agent.businessName}</option>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
			          <label for="beianbianhao" class="col-sm-2 control-label">主营小区</label>
			          <div class="col-sm-6">
			            <div class="estate_tag">
			              <select id="estate" name="mainCommunity" multiple="multiple">
			              </select>
			            </div>
			          </div>
			        </div>
		            <span style="color:red;">温馨提示：如果您是二手房销售，请填写商圈和主营小区，将会展示在前端页面，用户将会更加关注您</span>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">卖新房</label>
		                <div class="col-sm-6">
		                <c:if test="${agent.isSellNewHouse=='1'}">
		                	<input id="isSellNewHouse" name="isSellNewHouse" style="width:20px" type="checkbox" value="1" onclick="showAndHide()" checked="checked" onclick="sellNewHouse()"class="form-control">
		                </c:if>
		                <c:if test="${agent.isSellNewHouse!='1'}">
		                	<input id="isSellNewHouse" name="isSellNewHouse" style="width:20px" type="checkbox" value="1" onclick="showAndHide()" class="form-control">
		                </c:if>
		                  
		                </div>
		            </div>
		            <span style="color:red;">温馨提示：如果您是新房销售，请勾选卖新房，填写销售新房楼盘</span> 
		            <div id='show' class="form-group">
			          	<label for="beianbianhao" class="col-sm-2 control-label">销售新房楼盘</label>
			          	<div class="col-sm-6">
				            <div class="house_tag">
				              	<select id="house" name="sellBuilding" multiple="multiple">
									
				              	</select>
				            </div>
			          	</div>
			        </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">学历</label>
		              	     <div class="col-sm-6">
			                	<select name="education" class="form-control dsj-inline" data-validate="required" style="width:200px">
				                      <option value="">请选择</option>
				                      <c:forEach items="${['研究生及以上','本科','大专','高中及以下']}" var="edu">
				                       	 	<c:if test="${agent.education == edu }">
				                        	 	<option value="${edu }" selected="selected">${edu }</option>
				                        	 </c:if>
				                        	 <c:if test="${agent.education != edu }">
				                        	 	<option value="${edu }">${edu }</option>
				                        	 </c:if>
				                      </c:forEach>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
						<label for="workyears" class="col-sm-2 control-label">工作年限</label>
			          	<div class="input-group spinner col-sm-3" style="padding-left:13px;" data-trigger="spinner" id="spinner">
							<input type="text" id="workyears" name="workyears" class="form-control" value="${workyears}" data-max="60" data-min="1" data-step="1">
							<div class="input-group-addon dsj_spinner">
								<a href="javascript:;" class="spin-up" data-spin="up"><span class="glyphicon glyphicon-menu-up" aria-hidden="true"></span></a>
								<a href="javascript:;" class="spin-down" data-spin="down"><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a>
							</div>
						</div>
        			</div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">成交套数</label>
		                <div class="col-sm-6">
		                  <input type="text" name="deal" value="${agent.deal}" data-validate="isNumber0,max9999,required" class="form-control" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">带看次数</label>
		                <div class="col-sm-6">
		                  <input type="text" name="takeLook" value="${agent.takeLook}" data-validate="isNumber0,max9999,required" class="form-control">
		                </div>
		            </div>
		            <span style="color:red;">注:1.成交套数为整数值 2.带看次数为整数</span>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">身份证号</label>
		                <div class="col-sm-6">
		                  <input type="text" name="IdNumber" class="form-control" value="${agent.idNumber}" data-validate="required,people_card">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传身份证照片</label>
		                 <div class="col-sm-6">
			                 <img id="idCardPhotoShowImg" src="${agent.idCardUrl}"style="width: 80px;height: 80px;"/>
			                 <div id="idCardPhotoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="idCardUrl" value="${agent.idCardUrl}"style="width: 0px;border: 0px;" type="text" id="idCardPhotoFileUrl">
		                 </div>
		            </div>
		             <span style="color:red;">注:图片大小不超过1024KB,尺寸不小于800*600</span>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传工牌或名片照片</label>
		                 <div class="col-sm-6">
			                 <img id="cardPhotoShowImg" src="${agent.cardUrl }"style="width: 80px;height: 80px;"/>
			                 <div id="cardPhotoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="cardUrl" value="${agent.cardUrl }" style="width: 0px;border: 0px;" type="text" id="cardPhotoFileUrl" >
		                 </div>
		            </div>
		          
		            
		             <div class="text-center">
	                     <button class="btn btn-primary" type="button" onclick="saveEditAgent()">确认</button>
	                     <button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script src="${ctx}/static/front/js/selectize.min.js"></script>
<script type="text/javascript">

var sellBuilding = "${agent.sellBuilding}";
var mainCommunity = "${agent.mainCommunity}";
var isSellNewHouse = "${agent.isSellNewHouse}";
$(function(){
	if(isSellNewHouse==0){
    	$("#show").addClass("hide");
    }
});
$(function(){
	setTimeout(function(){
	if($("#areaTwoId").val()!=''){
		$.ajax({
				type:"post",
				url:_ctx+"/back/area/area_list",
				data:{
					areaCode:$("#areaTwoId").val(),
					isCustom:1
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							if(list[i].areaCode!=$("#areaThreeId").val()){
								$("#areaThreeId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
							}
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	}
		if($("#areaOneId").val()!=''){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/area_list",
				data:{
					areaCode:$("#areaOneId").val(),
					isCustom:1
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							if(list[i].areaCode!=$("#areaTwoId").val()){
								$("#areaTwoId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
							}	
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
		}
		if($("#areaThreeId").val()!=''){
			$.ajax({
				type:"post",
				url:_ctx+"/back/area/business",
				data:{
					areaCode:$("#areaThreeId").val()
				},
				datatype:"json",
				success:function(result){
					if(result.status=200){
						var list = result.data;
						for(var i in list){
							if(list[i].areaCode!=$("#areaFourId").val()){				
								$("#areaFourId").append("<option value='"+list[i].areaCode+"'>"+list[i].name+"</option>")
							}
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
		}
	},2000);
		
	})



var select = function(sellBuilding,mainCommunity) {
	$('#estate').on('change',function(){
      	if ($("select[name='mainCommunity']").val()=='') {   
      		$('#estate')[0].selectize.clearOptions();
    	}
      	
    });
		$('#house').on('change',function(){
			if ($("select[name='sellBuilding']").val()=='') {   
				$('#house')[0].selectize.clearOptions();
    	}
			
    });
    // body...
    $('#estate').selectize({
    	plugins: ['remove_button'],
        valueField: 'sprayName',
        labelField: 'sprayName',
        searchField: 'sprayName',
        maxItems:5,
        loadingClass:'loading',
        placeholder:'最多选择五个',
        create: false,
        render: {
            option: function(item, escape) {
                return '<option><span class="description">' + item.sprayName+'</span></option>';
            }
        },
        
        load: function(query, callback) {
            if (!query.length) return callback();
            $.ajax({
                url: _ctx+"/back/system/agent/getOldHouseName",
                type: 'POST',
                data:{
					name:$('#estate-selectized').val()
				},
				datatype:"json",
                error: function() {
                    callback();
                },
                success: function(res) {
                    callback(res.data);
                }
            });
        }
    });
    $('#house').selectize({
    	plugins: ['remove_button'],
        valueField: 'name',
        labelField: 'name',
        searchField: 'name',
        maxItems:5,
        loadingClass:'loading',
        placeholder:'最多选择五个',
        create: false,
        render: {
            option: function(item, escape) {
            	//debugger
                return '<option><span class="description">' + item.name+'</span></option>';
            }
        },

        load: function(query, callback) {
            if (!query.length) return callback();
            $.ajax({
                url: _ctx+"/back/system/agent/getNewHouseName",
                type: 'post',
                data:{
					name:$('#house-selectized').val()
				},
				datatype:"json",
                error: function() {
                    callback();
                },
                success: function(res) {
                    callback(res.data);
                }
            });
        }
    });
    if(sellBuilding!=null&&sellBuilding!=''){
    	var arr = sellBuilding.split(',');
    	for(var i=0;i<arr.length;i++){
        	$('#house')[0].selectize.addOption({"name":arr[i]})
        	$('#house')[0].selectize.addItem(arr[i])
    	}
    }
    if(mainCommunity!=null&&mainCommunity!=''){
    	var arr = mainCommunity.split(',');
    	for(var i=0;i<arr.length;i++){
        	$('#estate')[0].selectize.addOption({"sprayName":arr[i]})
        	$('#estate')[0].selectize.addItem(arr[i])
    	}
    }
	
}

select(sellBuilding,mainCommunity);

function showAndHide(){
    $('#show').toggleClass('hide'); 
    $('#house')[0].selectize.clear();
}   
</script>
<script src="${ctx}/static/back/lib/jquery.spinner.min.js"></script>
<script src="${ctx}/static/back/system/agent/agent_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
