<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<script type="text/javascript" src="${ctx}/static/back/js/ue1433/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/back/js/ue1433/ueditor.all.js"></script>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			楼盘动态审核
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
		                  <input name="title"type="text" readonly="readonly" class="form-control" value="${houseNews.title}"  data-validate="required">
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
						<button class="btn btn-primary" type="button" onclick="pass(${houseNews.id})">通过</button>
						<button class="btn btn-primary" type="button" onclick="refuse(${houseNews.id})">驳回</button>
						<button class="btn btn-primary" type="button" onclick="yulan()" >预览</button>
	                    <button class="btn btn-default" type="button" onclick="cancle()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
  <div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" id="bohuibox" style="width: 560px;">
                         <div class="modal-header">
	           				<button type="button" id="closeBtn" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                            <h4 class="modal-title">驳回理由</h4>
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				 <form id="authForm" class="col-xs-8 col-xs-offset-2">
										 <div class="form-group">
									        <div class="col-sm-6">
									          <textarea rows="4" cols="35" name="content" id="content2" placeholder="不超过一百个字"></textarea>
									      
									        </div>
									    </div>
									</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				        <button class="btn btn-primary" type="button" onclick="refuseEnsure()">提交</button>
				        <button type="button" id="closeBtn" class="btn btn-default myclousebtn" data-dismiss="modal">取消</button>
				        <!-- <button class="btn btn-default" onclick="remove()" data-dismiss="modal">取消</button> -->
				    </div>
                       
                   </div>
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
		location=_ctx+"/back/frame/content/houseNews/auditList";
	}
	 $("body").on("click","#passModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/content/houseNews/audit",
				data:{
					ids:id,
					status:2,
					msg:""
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent("恭喜您，审核成功");
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							location=_ctx+"/back/frame/content/houseNews/auditList";
						},2000);			
					}
				}
			})
		  
	});
	 $("body").on("click","#refuseModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/frame/content/houseNews/audit",
				data:{
					ids:id,
					status:3,
					msg:msg
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						setErrorContent(result.message);
						setTimeout(function(){
							location=_ctx+"/back/frame/content/houseNews/auditList";
						},2000);
					
					}
				}
			})
		  
		});
	 function pass(param){

			  id=param;
			setModalContent("您确认通过吗？","passModalConfirm");
		}
	 function refuse(param){

		  id=param;
		  show();
	}
	function show(){
		$("#authModal").modal({
			backdrop: 'static',
		    keyboard: false,
		    show: true
	    });
	}
	function remove(){
		$('#content2').val("");
	}
	function refuseEnsure(){
		if($('#content2').val()==''){
			setErrorContent("驳回理由不可以为空");
		}else{
			if($('#content2').val().length>100 ){
				setErrorContent("驳回理由字数过长");
			}else{
				msg = $('#content2').val();
				$(".myclousebtn").click();
				setModalContent("确认驳回？","refuseModalConfirm");
			}
			
		}
	}
	function getByteLen(val) {
	      var len = 0;
	      for (var i = 0; i < val.length; i++) {
	        var a = val.charAt(i);
	        if (a.match(/[^\x00-\xff]/ig) != null) {
	          len += 2;
	        }
	        else {
	          len += 1;
	        }
	      }
	      return len;
	}
	$(function(){
		$(".myclousebtn").click(function(){
			$('#content2').val("");
		})
	})
</script>
<script src="${ctx}/static/back/content/houseNews/houseNews_add.js"></script>