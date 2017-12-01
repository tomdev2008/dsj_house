<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="modal-header">
	<button type="button" id="closeBtn" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only"></span>
	</button>
	<h4 class="modal-title">回复</h4>
</div>
<div class="modal-body" style="height: 350px">
	<form class="form-horizontal" id="feedbackForm">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2">
				<div class="form-group">
					<label class="col-sm-2 control-label">反馈描述</label>
					<div class="col-sm-6">
						<textarea rows="8" cols="50" name="content">${po.content }</textarea>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
