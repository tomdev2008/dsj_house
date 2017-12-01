<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<script type="text/javascript" src="${ctx}/static/back/js/ue1433/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/back/js/ue1433/ueditor.all.js"></script>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			楼盘动态详情
		</h1>
		<form class="form-horizontal" id="houseNewsForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2" style="margin-left: 5.666667%;">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">楼盘名称</label>
		                <div class="col-sm-6">
		                  <input id="name"name="houseName"type="text"class="form-control" value="${houseNews.houseName}" readonly="readonly"data-validate="required">
		                	<input type="hidden" name="id" value="${houseNews.id}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">楼盘ID</label>
		                <div class="col-sm-6">
		                  <input name="houseId"type="text" class="form-control" value="${houseNews.houseId}" readonly="readonly"data-validate="required">
		                </div>
		            </div>
					
					 <div class="form-group">
		                <label  class="col-sm-2 control-label">动态标题</label>
		                <div class="col-sm-6">
		                  <input name="title" type="text" readonly="readonly" class="form-control" value="${houseNews.title}"  data-validate="required">
		                </div>
		            </div> 
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">动态内容</label>
		                <div class="col-sm-9">
		                  <script name="content" id="saveEditor" type="text/plain"
							style="width: 800px; height: 600px;margin-left:0px;"></script>
		                </div>
		                <textarea id="ueHidden" style="display:none" >${houseNews.content}</textarea>
		                <input id="contentst" type="hidden" name="contentst" >
		            </div>
					<!-- 
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">添加照片</label>
		                 <div class="col-sm-6">
			                 <c:if test="${houseNews.pictureUrl==''}">
		                 	<img id="photoShowImg" style="width: 180px;height: 180px;"/>
		                 	</c:if>
		                 	
		                 	<c:if test="${houseNews.pictureUrl!=''}">
		                 		<img id="photoShowImg" src="${houseNews.pictureUrl}" style="width: 180px;height: 180px;"/>
		                 	</c:if>

			                 <input name="pictureUrl" style="width: 0px;border: 0px;" type="text" id="photoFileUrl" >
		                 </div>
		            </div>
		          -->
		            
		             <div class="text-center">
	                     <button class="btn btn-primary" type="button" onclick="yulan()" >预览</button>
	                     <button class="btn btn-default" type="button" onclick="cancle()">返回</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>

<script type="text/javascript">

var editor;
editor = new UE.ui.Editor();
editor.render("saveEditor");

editor.ready(function() {
	editor.setContent($("#ueHidden").val());
});
editor.ready(function() {
	editor.setDisabled('fullscreen');
});

	var id="";
	 var msg='';
	function cancle(){
		location=_ctx+"/back/frame/content/houseNews/newsList";
	}
</script>
<script src="${ctx}/static/back/content/houseNews/houseNews_add.js"></script>