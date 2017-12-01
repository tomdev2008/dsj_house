<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			经纪人动态详情
		</h1>
		<form class="form-horizontal">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">发布时间</label>
		                <div class="col-sm-6">
		                  <input value="${agentNews.timeString}"type="text" class="form-control" readonly="readonly" >
		                  <input type="hidden" name="id" value="${agentNews.id}">
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">公司</label>
		                <div class="col-sm-6">
		                  <input value="${agentNews.company}"type="text" class="form-control" readonly="readonly" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">经纪人编号</label>
		                <div class="col-sm-6">
		                  <input value="${agentNews.agentCode}"type="text" class="form-control" readonly="readonly" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">经纪人</label>
		                <div class="col-sm-6">
		                  <input value="${agentNews.name}"type="text" class="form-control" readonly="readonly" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">手机号</label>
		                <div class="col-sm-6">
		                  <input value="${agentNews.tellPhone}"type="text" class="form-control" readonly="readonly" >
		                </div>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">动态内容</label>
		                
		                <div class="col-sm-9">
		                  <textarea rows="8" cols="20" name="content"  class="form-control" readonly="readonly" >${agentNews.content}</textarea>
		                </div>
		            </div>
		            
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">图片</label>
		                <div class="col-sm-10 row">
		                  <c:forEach items="${agentNews.picturesArr }" var="pic">
				               <div class="col-sm-4" style="margin-bottom:10px;">
			                 		<img id="idCardPhotoShowImg" src="${pic}"style="width: 180px;height: 180px;"/>		                
		                 		</div>
				          </c:forEach>
		                </div>
		            </div>		            
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
	location=_ctx+"/back/frame/content/agentNews/newsList";
}
</script>
