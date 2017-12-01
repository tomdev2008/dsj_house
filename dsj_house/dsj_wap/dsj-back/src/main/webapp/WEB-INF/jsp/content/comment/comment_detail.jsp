<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			评论详情
		</h1>
		<form class="form-horizontal">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">发布时间</label>
		                <div class="col-sm-6">
		                  <input value="${comment.timeString}"type="text" class="form-control" readonly="readonly" >
		                  <input type="hidden" name="id" value="${comment.id}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">发布人ID</label>
		                <div class="col-sm-6">
		                  <input value="${comment.commentUser}"type="text" class="form-control" readonly="readonly" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">用户名</label>
		                <div class="col-sm-6">
		                  <input value="${comment.tellPhone}"type="text" class="form-control" readonly="readonly" >
		                </div>
		            </div>
		            <c:if test="${comment.objectType!=1 }">
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">楼盘ID</label>
			                <div class="col-sm-6">
			                  <input value="${comment.houseId}"type="text" class="form-control" readonly="readonly" >
			                </div>
			            </div>
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">楼盘名称</label>
			                <div class="col-sm-6">
			                  <input value="${comment.houseName}"type="text" class="form-control" readonly="readonly" >
			                </div>
			            </div>
		            </c:if>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">内容</label>
		                <div class="col-sm-9">
		                  <textarea rows="8" cols="20" name="content"  class="form-control" readonly="readonly" >${comment.content}</textarea>
		                </div>
		            </div>
		            <c:if test="${comment.objectType==3 ||comment.objectType==4}">
			            <div class="form-group">
			                <label  class="col-sm-2 control-label">图片</label>
			                <div class="col-sm-6">
			                <c:forEach items="${comment.picture.split(',')}" var="pic" varStatus="status">
				                	
				                 <img id="idCardPhotoShowImg" src="${pic}"style="width: 180px;height: 180px;"/>		                
			                 
			                </c:forEach> 
			                </div>
			                 
			            </div>		
		            </c:if>            
		             <div class="text-center">
	                     <button class="btn btn-default" type="button" onclick="back()">返回</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>

<script type="text/javascript">
function back(){
	location=_ctx+"/back/frame/content/comment/commentList";
}
</script>
