<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			区域管理
		</h1>
		<ol class="breadcrumb">
			<li>区域管理</li>
			<li>行政区及商圈编辑 </li>
		</ol>
		<div class="form-inline">
			<div class="form-group mr20">
				<span class="wenzi6">省:</span>
				<select  id="areaOneId" class="form-control dsj-inline" style="width:100px;" name="provinceCode" onchange="getTwoArea()">
					<option value="">请选择</option>
					<c:forEach items="${firstAreaList }" var="area">
						<%-- <c:if test="${area.areaCode!=1 }">
							<option value="${area.areaCode }">${area.name }</option>
						</c:if> --%>
						<c:choose>  
						   <c:when test="${area.areaCode == 110000 }">
						   		<option value="${area.areaCode }" selected="selected">${area.name }</option>
						   </c:when>  
						   <c:otherwise>
						   		<option value="${area.areaCode }">${area.name }</option>
						   </c:otherwise>  
						</c:choose>  
					</c:forEach>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">市:</span>
				<select id="areaTwoId" onchange="getThreeAreaXZQY()" class="form-control dsj-inline" style="width:100px;" name="cityCode">
					<option value="">请选择</option>
					<option value="110100" selected="selected">北京市</option>
				</select>
			</div>
		</div> 
	</div>
	<div class="white-content">
		<div class="row">
			<div class="col-xs-12" style="padding-right:25px;">
				<button class="dsj_btn btn dsj_btn_blue" id="add_3_area" onclick="return false;" type="button">新增区域</button>
				<button class="dsj_btn btn dsj_btn_blue" id="edit_3_area" onclick="return false;" type="button">编辑区域</button>
				<button class="dsj_btn btn dsj_btn_blue" id="add_4_area" onclick="return false;" type="button">新增商圈</button>
				<button class="dsj_btn btn dsj_btn_blue" id="edit_4_area" onclick="return false;" type="button">编辑商圈</button>
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<link rel="stylesheet" href="${ctx}/static/back/lib/ztree/zTreeStyle.css">
<script src="${ctx}/static/back/lib/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/back/lib/ztree/jquery.ztree.exedit.js"></script>
<script src="${ctx}/static/back/system/area/area_edit.js"></script>