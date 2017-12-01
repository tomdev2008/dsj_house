<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div id="myModal" >
			<div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                           <h4 class="modal-title">编辑经纪人</h4>
                       </div>
                       <div class="modal-body">
    							 <form id="roleFrom" class="form-horizontal">
							          <div class="form-group">
		                               <label  class="col-sm-2 control-label">目标经纪人</label>
		                               <div class="col-sm-6">
						                  <select id="dicSelectId" class="form-control dsj-inline " name="dicId" class="js-example-basic-multiple" >
				 								<option value="${pcAgent.agentName}">${pcAgent.agentName}</option>
										 </select>
						               </div>
						               </div>
							          <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label0">链接地址：</label>
										    <div class="col-sm-9">
										       <input type="text" placeholder="链接地址" value="${pcAgent.linkAddress}" id="linkAddress" class="form-control"  data-validate="required" style="width:400">
										    </div>
							          </div>
							           <div class="form-group">
							 		   	   <label for="inputEmail3" class="col-sm-3 control-label0">经纪人点评：</label>
										    <div class="col-sm-9">
										       <textarea rows="4" style="width:80%" id="comment" maxlength="30" placeholder="经纪人点评,最多可输入30字" data-validate="required" maxlength="15">${pcAgent.comment}</textarea>
										    </div>
							          </div>
							          <input type="hidden" value="${pcAgent.id}" id="pcAgentId" name="pcAgentId">
							       </form> 
                       </div>
                       <div class="modal-footer">
                            <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">取消</button>
                           <button type="button" name="confirm" class="dsj_btn btn dsj_btn_blue" onclick="updateAgent()" id="add">确认</button>
                       </div>
                       </div>

<script> 
   $(function(){
	   $.fn.modal.Constructor.prototype.enforceFocus =function(){};
		$("#dicSelectId").select2({
			 dropdownParent:$("#myModal"),
			"ajax":{
				    url: _ctx+"/back/agent/getAgentDic",
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
 </script>  
<script src="${ctx}/static/back/pcHouse/pcHouse.js"></script>