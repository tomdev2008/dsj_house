<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<link href="${ctx}/static/front/css/bootstrap-spinner.css" rel="stylesheet">
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			经纪人信息
		</h1>
		<ol class="breadcrumb">
			<li>经纪人账号管理</li>
			<li>个人资料 </li>
		</ol>
		<form class="form-horizontal" id="agentEditForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">经纪人ID</label>
		                <div class="col-sm-6">
		                  <input name="agentCode" value="${agent.agentCode}"type="text" class="form-control" readonly="readonly" >
		                  <input type="hidden" name="id" value="${agent.id}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传头像</label>
		                 <div class="col-sm-6">
			                 <img id="avatarPhotoShowImg" src="${agent.avatarUrl}"style="width: 80px;height: 80px;"/>
			                 <input name="avatarUrl" value="${agent.avatarUrl}"style="width: 0px;border: 0px;" type="text" id="avatarPhotoFileUrl" >
		                 </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">姓名</label>
		                <div class="col-sm-6">
		                  <input name="name" type="text" class="form-control" value="${agent.name}" readonly="readonly">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">联系方式</label>
		                <div class="col-sm-6">
		                  <input name="tellPhone" type="text" class="form-control" value="${agent.tellPhone}" readonly="readonly">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">公司</label>
		                <div class="col-sm-6">
		                  <input name="company" type="text" class="form-control" readonly="readonly" value="${agent.companyName}" >
		                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">所在省市</label>
		              	     <div class="col-sm-6">
			                	<select  id="areaOneId" disabled="disabled" name="province" onchange="getTwoArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.province}">${agent.provinceName}</option>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">市</label>
		              	     <div class="col-sm-6">
								 <select  id="areaTwoId" disabled="disabled" name="city" onchange="getThreeArea()" onclick="getTwoArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.city}">${agent.cityName}</option>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">行政区</label>
		              	     <div class="col-sm-6">
			                    <select  id="areaThreeId" disabled="disabled" name="area" onchange="getFourArea()" onclick="getThreeArea()"class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.area}">${agent.areaName}</option>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">商圈</label>
		              	     <div class="col-sm-6">
			                    <select  id="areaFourId" disabled="disabled" name="business" onclick="getFourArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="${agent.business}">${agent.businessName}</option>
			                    </select>
		                    </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">主营小区</label>
	                	<div class="col-sm-6" id="mainCommunity">
	                  		<input type="text" readonly="readonly" style="margin-top: 5px;" value="${agent.mainCommunity}" name="mainCommunity" class="form-control" >
	                	</div>

		                
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">卖新房</label>
		                <div class="col-sm-6">
		                <c:if test="${agent.isSellNewHouse=='1'}">
		                	<input disabled="disabled" id="isSellNewHouse" style="width:20px" name="isSellNewHouse" type="checkbox" value="1" checked="checked" onclick="sellNewHouse()"class="form-control">
		                </c:if>
		                <c:if test="${agent.isSellNewHouse!='1'}">
		                	<input disabled="disabled" id="isSellNewHouse" style="width:20px" name="isSellNewHouse" type="checkbox" value="1" onclick="sellNewHouse()"class="form-control">
		                </c:if>
		                  
		                </div>
		            </div>
		            <c:if test="${agent.isSellNewHouse=='1'}">
	                <div class="form-group" id="show">
	                </c:if>
	                <c:if test="${agent.isSellNewHouse!='1'}">
	                <div class="form-group hide" id="show">
	                </c:if>
		            	<label  class="col-sm-2 control-label">销售新房楼盘</label> 
			                <div class="col-sm-6" id="sellBuilding">
			                  <input type="text" readonly="readonly" style="margin-top: 5px;" value="${agent.sellBuilding }" name="sellBuilding" class="form-control" >
			                </div>
		            </div>
		            <div class="form-group">
		              	    <label class="col-sm-2 control-label">学历</label>
		              	     <div class="col-sm-6">
			                	<select disabled="disabled" name="education" class="form-control dsj-inline" data-validate="required" style="width:200px">
				                      <option value="${agent.education }">${agent.education }</option>
				                      
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
						<label for="workyears" class="col-sm-2 control-label">工作年限</label>
			          	<div class="col-sm-6">
		                  <input type="text" name="workyears" readonly="readonly" value="${agent.workyears}" class="form-control" >
		                </div>
        			</div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">成交套数</label>
		                <div class="col-sm-6">
		                  <input type="text" name="deal" readonly="readonly" value="${agent.deal}" class="form-control" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">带看次数</label>
		                <div class="col-sm-6">
		                  <input type="text" name="takeLook" readonly="readonly" value="${agent.takeLook}" class="form-control">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">身份证号</label>
		                <div class="col-sm-6">
		                  <input type="text" readonly="readonly" name="IdNumber" class="form-control" value="${agent.idNumber}" data-validate="required,people_card">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传身份证照片</label>
		                 <div class="col-sm-6">
			                 <img id="idCardPhotoShowImg" src="${agent.idCardUrl}"style="width: 80px;height: 80px;"/>
			                 <input name="idCardUrl" value="${agent.idCardUrl}"style="width: 0px;border: 0px;" type="text" id="idCardPhotoFileUrl"  data-validate="required">
		                 </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">上传工牌或名片照片</label>
		                 <div class="col-sm-6">
			                 <img id="cardPhotoShowImg" src="${agent.cardUrl }"style="width: 80px;height: 80px;"/>
			                 <input name="cardUrl" value="${agent.cardUrl }" style="width: 0px;border: 0px;" type="text" id="cardPhotoFileUrl" >
		                 </div>
		            </div>
		          
		            
		             <div class="text-center">
	                     <button class="btn btn-default" type="button" onclick="cancelFun()">返回</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script src="${ctx}/static/back/lib/jquery.spinner.min.js"></script>
<script src="${ctx}/static/back/system/agent/agent_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>