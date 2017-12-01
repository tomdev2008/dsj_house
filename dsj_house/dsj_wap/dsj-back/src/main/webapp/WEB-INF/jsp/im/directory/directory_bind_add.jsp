<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div id="myModal" >
	<div class="modal-header">
        <h4 class="modal-title">选择经纪人</h4>
    </div>
    <div class="modal-body">
		<form id="roleFrom" class="form-horizontal">
       		<div class="form-group">
                <label  class="col-sm-2 control-label">经纪人名称</label>
                <div class="col-sm-6">
	                <select id="dicSelectId"  class="form-control dsj-inline " class="js-example-basic-multiple" >
						<option value="${vo.agentId}">${vo.agentName}</option>
				 	</select>
              	</div>
           	</div>
         	<input type="hidden" value="${vo.id}" id="id" >
         	<input type="hidden" value="${vo.houseId}" id="houseId" >
         	<input type="hidden" value="${vo.position}" id="position" >
      	</form> 
     </div>
     <div class="modal-footer">
         <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">取消</button>
         <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" id="confirm_btn" onclick="updateAgentBind()">确认</button>
     </div>
</div>
<script>
   $(function(){
	   $.fn.modal.Constructor.prototype.enforceFocus =function(){};
		$("#dicSelectId").select2({
		     dropdownParent:$("#myModal"),
				"ajax":{
				    url: _ctx+"/back/agent/getLikeAgent",
				    data: function (params) {
				      var query = { //请求的参数, 关键字和搜索条件之类的
				        name: params.term //select搜索框里面的value
				      }
				      // Query paramters will be ?search=[term]&page=[page]
				      return query;
				    },
				    delay: 1500,
				    processResults: function (data, params) {
				      //返回的选项必须处理成以下格式
				      var results = data.data;
				      return {
				        results: results  //必须赋值给results并且必须返回一个obj
				      };
				   }
			  }
		});
	})
	
	function updateAgentBind(){
	    $("#confirm_btn").attr("disabled", true); 
		var id = $("#id").val();
		var houseId = $("#houseId").val();
		var position = $("#position").val();
	 	var agentId = $('#dicSelectId').find("option:selected").val();
	   	if(agentId==null || agentId==''){
		   setErrorContent("经纪人ID不能为空");
		   return;
	   	}
	   	$.ajax({
			type : "post",
			url : _ctx+"/back/im/directory/bind_save",
			data : {
				agentId : agentId,
				houseId : houseId,
				position : position,
				id : id
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					$("#confirm_btn").attr("disabled", false); 
					setErrorContent(result.message);
				}else{
					parent.location.reload();
				}
			}
		})
	}
</script>