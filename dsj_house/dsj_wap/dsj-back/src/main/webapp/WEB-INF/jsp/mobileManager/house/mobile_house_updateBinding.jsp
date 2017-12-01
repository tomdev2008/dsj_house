<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
					<div class="white-head">
					<div class="row">
						<form class="form-horizontal" id="bindingForm">
							<div class="row">
						        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 电话管理</div>
						    </div>
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2">
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">400电话:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" value="400-8986868-${mobilePo.mobile }" readonly="readonly">
						                  <input type="hidden" class="form-control" name="mobile" value="${mobilePo.mobile }">
						                  <input type="hidden" class="form-control" name="id" value="${mobilePo.id }">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>小号位置:</label>
						                <div class="col-sm-6">
						               	 <c:if test="${mobilePo.channel==1 }">
						                 	 <input type="text" class="form-control" value="PC" readonly="readonly">
						                  </c:if>
						                  <c:if test="${mobilePo.channel==2 }">
						                 	 <input type="text" class="form-control" value="WAP" readonly="readonly">
						                  </c:if>
						                  <c:if test="${mobilePo.channel==3 }">
						                 	 <input type="text" class="form-control"  value="APP" readonly="readonly">
						                  </c:if>
						                   <input type="hidden" name="channel" value="${mobilePo.channel}">
						                </div>
						            </div>
						            <div class="form-group">
						                <label class="col-sm-2 control-label"><span style="color:red">*</span>楼盘名称:</label>
						                <div class="col-sm-6">
											  <input type="text" class="form-control" value="${mobilePo.houseName }" readonly="readonly">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘编码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="houseCode" value="${mobilePo.houseCode }" readonly="readonly" id="houseCode">
						                </div>
						            </div>
						            <div class="form-group">
										<label class="col-sm-2 control-label"><span style="color:red">*</span>轮询模式:</label>
										<div class="col-sm-10">
											<select id="lineNum" class="form-control dsj-inline dsj-width-1" name="way" data-validate="required">
												<option value="">请选择</option>
												<option value="1" ${mobilePo.way==1?'selected="selected"':'' }>智能ACD</option>
												<option value="2" ${mobilePo.way==2?'selected="selected"':'' }>空闲最长</option>
												<option value="3" ${mobilePo.way==3?'selected="selected"':'' }>一号通</option>
												<option value="5" ${mobilePo.way==5?'selected="selected"':'' }>轮询</option>
												<option value="8" ${mobilePo.way==8?'selected="selected"':'' }>顺序</option>
											</select>
										</div>
									</div>
									<div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>超时时间:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="timeOut" placeholder="必须为正整数(秒)" value="${mobilePo.timeOut }" data-validate="required,isNumber,maxNumber(999)">
						                </div>
						            </div>
						        </div>
						    </div>
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2 font16"><i class="fa fa-caret-right" aria-hidden="true"></i> 绑定电话</div>
						    </div>
						    <div class="row">
						        <div class="col-xs-8 col-xs-offset-2">
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>第一号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" placeholder="例如:13800138000或01056603132" class="form-control" name="phone" value="" data-validate="required,isPhoneAndMobile" id="phone0">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第二号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone1">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第三号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone2">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第四号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone3">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第五号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone4">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第六号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone5">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第七号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone6">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第八号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone7">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第九号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone8">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第十号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone9" >
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>短信号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" placeholder="须为手机号,通话未成功时,挂机短信发送到该号码" data-validate="required" class="form-control" name="msgPhone" value="${mobilePo.msgPhone }">
						                </div>
						            </div>
						    	</div>
						    </div>
				             <div class="text-center">
			                     <button class="btn btn-primary" type="button" onclick="saveBingding()">确定</button>
			                     <button class="btn btn-default" type="button" onclick="cancelFun()">取消</button>
			                </div>
						</form>
					</div>
					</div>
				</div>
<script>
function saveBingding(){
	$("#bindingForm").verify();
	$("#bindingForm").validate(function (result) {
	  	if(result){
	  		$("#popup_box").show();
	  		$.ajax({
	  			type:"post",
	  			async:true,
	  			data:$("#bindingForm").serialize(),
	  			dataType:"json",
	  			url:_ctx+"/back/frame/mobileManager/house/saveUpdateBinding",
	  			success:function(resultVo){
	  				if(resultVo.status!=200){
	  					$("#popup_box").hide();
						 setErrorContent(resultVo.message);
					}else{
						location=_ctx+"/back/frame/mobileManager/house"
					}
	  			}
	  		})
	  	}
	 })
}
function cancelFun(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/mobileManager/house"
}
$(function(){
	var phone ="${mobilePo.phone}";
	var phoneList = phone.split(",");
	for(var j in phoneList){
		if(typeof $("#phone"+j).val() !="undefined"){
			$("#phone"+j).val(phoneList[j])
		}
	}
})
</script>