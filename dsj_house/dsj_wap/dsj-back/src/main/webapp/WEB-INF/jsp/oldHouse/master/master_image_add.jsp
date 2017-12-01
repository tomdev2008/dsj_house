<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

 			<div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
               
           </div>
           <div class="modal-body">
	           <div class="col-xs-12 row">
					<div class="white-head">
						<!-- end breadcrumb -->
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2">
							        <div id="uploader">
							            <div class="queueList">
							                <div id="dndArea" class="placeholder">
							                    <div id="filePicker"></div>
							                    <p>或将照片拖到这里，单次最多可选10张</p>
							                </div>
							            </div>
							            <div class="statusBar" style="display:none;">
							                <div class="progress">
							                    <span class="text">0%</span>
							                    <span class="percentage"></span>
							                </div><div class="info"></div>
							                <div class="btns">
							                    <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
							                </div>
							            </div>
							        </div>
						        </div>
						    </div>
					</div>
				</div>
           </div>
           <div class="modal-footer">
               <button type="button" id="closeModelId" class="dsj_btn btn dsj_btn_blue" data-dismiss="modal">返回</button>
           </div>
<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/webuploader.css">
<script type="text/javascript" src="${ctx }/static/front/js/webuploader.js"></script>
<script type="text/javascript" src="${ctx }/static/front/js/upload.js"></script>
<script type="text/javascript">
var oldMasterType="${type}";
var oldMasterId="${id}";
</script>
