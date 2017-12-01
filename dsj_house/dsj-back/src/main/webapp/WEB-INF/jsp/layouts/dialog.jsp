<%@page contentType="text/html;charset=UTF-8" %>
<!-- 共用错误提示model -->
<div class="modal fade" style="z-index: 9998;display:none;text-align:center;" id="errorModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                                <h4 class="modal-title">提示</h4>
                            </div>
                            <div class="modal-body">
         							 <span id="errorModelText"></span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="dsj_btn btn dsj_btn_blue" data-dismiss="modal">确定</button>
                            </div>
                        </div>
                    </div>
</div>
<!-- 公用model -->
<div style="display:none;z-index: 9997;text-align:center;" class="modal fade slb_small" id="isSureModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" id="closeBtn" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                                <h4 class="modal-title">提示</h4>
                            </div>
                            <div class="modal-body">
          							<span id="alertModelText"></span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" data-dismiss="modal" id="">确认</button>
                                <button type="button" class="dsj_btn btn btn-default" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </div>
</div>

<!-- 公用model -->
<div style="display:none;z-index: 9997;text-align:center;" class="modal fade slb_small" id="successModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            
                            <div class="modal-body">
          							<span id="successModelText"></span>
                            </div>
                            
                        </div>
                    </div>
</div>

<div class="modal fade" id="commonModal" style="padding-right: 0px;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
           <div class="modal-content" id="modal-content">
         			 
            </div>
      </div>
</div>

<!-- 预览 -->
<div class="modal fade" id="yulanNews" style="padding-right: 0px;" tabindex="-1" role="dialog" aria-labelledby="yulanNews" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 752px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="false">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel233">
					预览
				</h4>
			</div>
			<div class="modal-body" id="yulan_cc">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var _ctx='${ctx}';
</script>
