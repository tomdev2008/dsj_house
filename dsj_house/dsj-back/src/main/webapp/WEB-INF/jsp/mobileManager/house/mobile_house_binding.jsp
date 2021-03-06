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
						                  	<select id="dicSelectId"  class="form-control dsj-inline js-example-basic-multiple" name="houseId" onchange="setOther()" data-validate="required">
											 </select>
											 <input type="hidden" class="form-control" name="houseName" id="houseName">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>楼盘编码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="houseCode" readonly="readonly" id="houseCode">
						                </div>
						            </div>
						            <div class="form-group">
										<label class="col-sm-2 control-label"><span style="color:red">*</span>轮询模式:</label>
										<div class="col-sm-10">
											<select id="lineNum" class="form-control dsj-inline dsj-width-1" name="way" data-validate="required">
												<option value="">请选择</option>
												<option value="1">智能ACD</option>
												<option value="2">空闲最长</option>
												<option value="3">一号通</option>
												<option value="5">轮询</option>
												<option value="8">顺序</option>
											</select>
										</div>
									</div>
									<div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>超时时间:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="timeOut" placeholder="必须为正整数(秒)"  data-validate="required,isNumber,maxNumber(999)">
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
						                  <input type="text" class="form-control" name="phone" id="phone1" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第三号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone2" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第四号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone3" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第五号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone4" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第六号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone5" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第七号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone6" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第八号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone7" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第九号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone8" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						             <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label">第十号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="phone" id="phone9" data-validate="isPhoneAndMobile">
						                </div>
						            </div>
						            <div class="form-group">
						                <label for="loupanname" class="col-sm-2 control-label"><span style="color:red">*</span>短信号码:</label>
						                <div class="col-sm-6">
						                  <input type="text" class="form-control" name="msgPhone" data-validate="required" placeholder="须为手机号,通话未成功时,挂机短信发送到该号码">
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
var result = null;
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
	  			url:_ctx+"/back/frame/mobileManager/house/saveBinding",
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
//设置电话 ,code
function setOther(){
	var houseId = $("#dicSelectId").val();
	if(houseId!=null){
		for(var i in result){
			if(result[i].id==houseId){
				$("#houseCode").val(result[i].code);
				$("#houseName").val(result[i].text)
				if(null!=result[i].phone){
					var phoneList = result[i].phone.split(",")
					for(var j in phoneList){
						if(typeof $("#phone"+j).val() !="undefined"){
							$("#phone"+j).val(phoneList[j])
						}
					}
				}
			}
		}
	}
}
function cancelFun(){
	$("#popup_box").show();
	location=_ctx+"/back/frame/mobileManager/house"
}
$(function(){
	$("#dicSelectId").select2({
			"ajax":{
			    url: _ctx+"/back/newHouse/edit/getDic",
			    data: function (params) {
			      var query = { //请求的参数, 关键字和搜索条件之类的
			        name: params.term //select搜索框里面的value
			      }
			      return query;
			    },
			    delay: 1000,
			    processResults: function (data, params) {
			      //返回的选项必须处理成以下格式
			     result = data.data;
			      return {
			        results: result  //必须赋值给results并且必须返回一个obj
			      };
			    }
			  }
	});
	
})
</script>