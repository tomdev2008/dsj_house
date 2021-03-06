<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			添加经纪公司
		</h1>
		<form class="form-horizontal" id="addCompanyForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		        	<div class="form-group">
		              	    <label class="col-sm-2 control-label">*城市</label>
		              	     <div class="col-sm-6">
			                	<select data-validate="required" id="areaOneId" name="city"  class="form-control dsj-inline" style="width:200px">
				                      <option value="">请选择</option>
				                       <c:forEach items="${firstAreaList }" var="area">
				                       	 	<c:if test="${area.areaCode!=1 }">
				                        	 	<option value="${area.name }">${area.name }</option>
				                        	 </c:if>
				                        </c:forEach>
			                    </select>
		                    </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">*公司全称</label>
		                <div class="col-sm-6">
		                  <input name="companyName"type="text" maxLength="25" class="form-control"  data-validate="required">
		                </div>
		            </div>
		             <div class="form-group">
		                <label  class="col-sm-2 control-label">*公司简称</label>
		                <div class="col-sm-6">
		                  <input name="shortName"type="text" maxLength="25" class="form-control"  data-validate="required">
		                </div>
		            </div>

		            <div class="form-group">
		                <label  class="col-sm-2 control-label">公司图标</label>
		                 <div class="col-sm-6">
			                 <img id="photoShowImg" style="width: 180px;height: 180px;"/>
			                 <div id="photoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="ico" style="width: 0px;border: 0px;" type="text" id="photoFileUrl" >
		                 </div>
		            </div>
		          
		            
		             <div class="text-center">
	                     <button class="btn btn-primary" type="button" onclick="add()">确认</button>
	                     <button class="btn btn-default" type="button" onclick="cancel()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>

<script src="${ctx}/static/back/system/company/company_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>