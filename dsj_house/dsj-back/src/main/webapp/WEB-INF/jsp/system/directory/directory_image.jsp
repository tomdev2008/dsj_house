<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<style>
	 .fsz12{font-size:12px;}
	 .myblue{color: FFFFFF;}
</style>
<div class="col-xs-12 padding-foot">
	<div class="white-content">

		<ol class="breadcrumb">
			<li>楼盘字典</li>
			<li>图片编辑</li>
		</ol>

		<div class="row" style="margin: 20px;">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation">
					<a href="${ctx}/back/frame/houseDirectory/to_add_edit?id=${id}">房源信息</a>
				</li>
				<li role="presentation" class="active">
					<a href="${ctx}/back/frame/houseDirectory/to_image_list/${id}">房源图片</a>
				</li>
			</ul>
			<form id="addForm">
				<div class="row">
					<div class="row col-xs-8 col-xs-offset-2">
						<div class="font16">
							<i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 上传户型图
						</div>
						<c:forEach var="image" items="${houseTypeImages}">
							<div class="col-xs-3">
								<input class="dsj_pic_checkbox" value="${image.id}" name="checkbox_name" type="checkbox">
								<img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${image.pictureUrl}">
								<p class="dsj_img_single_action" >
									<c:if test="${image.isCover ==2 }">
							      		设首页|${image.createPersonName}|<fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd" />
							      	</c:if>
									<c:if test="${image.isCover ==1 }">
							      		<b class="myblue">首页|${image.createPersonName}|<fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd" /></b>
							      	</c:if>
								</p>
								<input type="hidden" value="${image.id}" />
							</div>
						</c:forEach>
						<div id="image_id_housetype" class="col-xs-3">
							<div class="dsj_img_wraper">
								<div class="dsj_img add_more_pic" onclick="openCommonModal('${ctx}/back/houseDirectory/directory_image_add?id=${id}&type=2',1200,12)"></div>
							</div>
						</div>
					</div>
					<div class="row col-xs-8 col-xs-offset-2">
						<div class="font16">
							<i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 上传楼盘图
						</div>
						<input type="hidden" value="${id}" name="id" />
						<c:forEach var="image" items="${buildingImages}">
							<div class="col-xs-3">
								<input class="dsj_pic_checkbox" value="${image.id}" name="checkbox_name" type="checkbox">
								<img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${image.pictureUrl}">
								<p class="dsj_img_single_action" >
									<c:if test="${image.isCover ==2 }">
							      		设首页|${image.createPersonName}|<fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd" />
							      	</c:if>
									<c:if test="${image.isCover ==1 }">
							      		<b class="myblue">首页|${image.createPersonName}|<fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd" /></b>
							      	</c:if>
								</p>
								<input type="hidden" value="${image.id}" />
							</div>
						</c:forEach>

						<div id="image_id_inside" class="col-xs-3">
							<div class="dsj_img_wraper">
								<div class="dsj_img add_more_pic" onclick="openCommonModal('${ctx}/back/houseDirectory/directory_image_add?id=${id}&type=4',1200,12)"></div>
							</div>
						</div>
					</div>
					<div class="row col-xs-8 col-xs-offset-2"">
						<div class="font16">
							<i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 上传配套图
						</div>
						<c:forEach var="image" items="${wholesetImages}">
							<div class="col-xs-3">
								<input class="dsj_pic_checkbox" value="${image.id}" name="checkbox_name" type="checkbox">
								<img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${image.pictureUrl}">
								<p class="dsj_img_single_action" >
									<c:if test="${image.isCover ==2 }">
							      		设首页|${image.createPersonName}|<fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd" />
							      	</c:if>
									<c:if test="${image.isCover ==1 }">
							      		<b class="myblue">首页|${image.createPersonName}|<fmt:formatDate value="${image.createTime}" pattern="yyyy-MM-dd" /></b>
							      	</c:if>
								</p>
								<input type="hidden" value="${image.id}" />
							</div>
						</c:forEach>
						<div id="image_id_outside" class="col-xs-3">
							<div class="dsj_img_wraper">
								<div class="dsj_img add_more_pic" onclick="openCommonModal('${ctx}/back/houseDirectory/directory_image_add?id=${id}&type=5',1200,12)"></div>
							</div>
						</div>
					</div>
				</div>

			</form>
		</div>
		<div class="text-center">
			<!-- <button class="btn btn-primary" type="button" onclick="publishBuilding()">发布房源</button> -->
			<button class="btn btn-primary" type="button" onclick="returnList()">确定</button>
			<button class="btn btn-default" type="button" onclick="deleteById()">刪除</button>
		</div>
		<input type="hidden" id="objId" value="${id}">
	</div>
</div>
<script type="text/javascript">
	var upload_type = 2;//楼盘字典
</script>
<script src="${ctx}/static/back/system/directory/directory_image_list.js"></script>
