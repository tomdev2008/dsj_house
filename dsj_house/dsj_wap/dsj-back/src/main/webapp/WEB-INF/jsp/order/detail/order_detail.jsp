<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
 	<div class="white-content">
		<div class="order_main">
		<div class="main_sideleft">
			<p class="sideleft_label">办理动态</p>
			<ul class="nav nav-pills nav-stacked">
			<c:forEach items="${nodeList }" var="node" varStatus="stat">
				 <li role="presentation">
					  	<p class="file_name">${node.nodeName }</p>
					  	<time><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${node.dealTime }"></fmt:formatDate></time>
					  	<p>${node.dealUserName }</p>
					  	<c:if test="${node.status==2 }">
						  	<div class="progress_s progress_active">
						  		<c:if test="${!stat.last}">
						  			<p class="p_active"></p>
						  		</c:if>
						  	</div>
					  	</c:if>
					  	<c:if test="${node.status!=2 }">
						  	<div class="progress_s">
							  	<c:if test="${!stat.last}">
							  		<p></p>
						  		</c:if>
						  	</div>
					  	</c:if>
				  </li>
			</c:forEach>
			 
			</ul>
		</div>
		<div class="main_content">
			<ul class="nav nav-pills" id="nodeList">
			   <li role="presentation" <c:if test="${detailVo.nodeName == '服务开始'||detailVo.nodeName == '服务完成' }">class="active"</c:if>><a href="javascript:void(0);" onclick="getNodeField(0,1,this)">订单详情</a></li>
			   <c:forEach items="${nodeList }" var="node" varStatus="stat">
				   	<c:if test="${!stat.last&&!stat.first}">
				   		<li role="presentation"><a onclick="getNodeField(${node.nodeId },${node.isauth },this)" nodeId="${node.nodeId }" href="javascript:void(0);">${node.nodeName }</a></li>
					</c:if>
				 	
				</c:forEach>
			</ul>
			<div class="content_kuang">
				<input id="nodeId" type="text" style="display: none;">
				<input id="isauth" type="text" style="display: none;">
				<div id="orderDetail">
					<div class="content_div" > 
						<div><span>下单时间：</span>
							<time><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${detailVo.createTime }"></fmt:formatDate></time>
						</div>
						<div><span>订单号：</span>${detailVo.orderNo }</div>
						<div>
							<span>订单状态：</span>
							<c:if test="${detailVo.status == 1}">
			    					待付款
			    				</c:if>
			    				<c:if test="${detailVo.status == 2}">
			    					已取消
			    				</c:if>
		    					<c:if test="${detailVo.status == 3}">
		    						已过期
		    					</c:if>
		    					<c:if test="${detailVo.status == 4}">
		    						已付款
		    					</c:if>
		    					<c:if test="${detailVo.status == 5}">
		    						退款受理中
		    					</c:if>
		    					<c:if test="${detailVo.status == 6}">
		    						退款完成
		    					</c:if>
		    					<c:if test="${detailVo.status == 7}">
		    						待买家确认
		    					</c:if>
		    					<c:if test="${detailVo.status == 9}">
		    						待评价
		    					</c:if>
			    				<c:if test="${detailVo.status == 10}">
		    						已评价
		    					</c:if>
			    				<c:if test="${detailVo.status == 12}">
		    						服务进行中
		    					</c:if>
			    				<c:if test="${detailVo.status == 13}">
		    						服务进行中
			    				</c:if>
						</div>
						<div><span>支付金额：</span>${detailVo.payment }</div>
						<div><span>业务类型：</span>${detailVo.productName }</div>
						<div><span>服务区域：</span>${detailVo.areaName }</div>
						<div><span>买家姓名：</span>${detailVo.username }</div>
						<div><span>买家手机号：</span>${detailVo.phone }</div>
						<div><span>代办人姓名：</span>${detailVo.realName }</div>
						<div><span>代办人手机号：</span>${detailVo.dbPhone }</div>
						<div><span>商家名称：</span>${detailVo.dbCompanyName }</div>
					</div>
					<button class="btn btn-default default_botton" onclick="history.back(-1)" type="button">返回</button>
				</div>	
				<div id="otherField">
				</div>	
			</div>
		</div>
  	</div>
      </div>
 </div>	
  <div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" style="width: 560px;">
                         <div class="modal-header">
	           <div class="row">
	          	 <h4 class="modal-title">审核意见</h4>
		    	</div>
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				 <form id="authForm" class="col-xs-8 col-xs-offset-2">
										 <div class="form-group">
									        <div class="col-sm-6">
									          <textarea rows="4" cols="35" name="content" maxLength="100" data-validate="required,maxLength100" id="content"></textarea>
									          <input type="hidden" id="authResult">
									        </div>
									    </div>
									</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				        <button class="btn btn-primary" type="button" onclick="saveAuth()">提交</button>
				        <button class="btn btn-default" data-dismiss="modal">取消</button>
				    </div>
                       
                   </div>
               </div>
</div>
 <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/order.css">
 <script>
 $(function(){
	 var currentNodeId = "${detailVo.nodeId}";
	 if(currentNodeId!=null){
		 $("#nodeList li a[nodeId='"+currentNodeId+"']").click();
	 }
 })

 function getNodeField(nodeId,isauth,_this){
	 $("#popup_box").show();
	 $("#nodeId").val(nodeId);
	 $("#isauth").val(isauth);
	 $("#nodeList li").removeClass("active");
	 $(_this).parent().addClass("active");
	 if(nodeId!=0){
		 $("#otherField").empty();
		 $("#orderDetail").hide();
		 $("#otherField").show();
			 $.ajax({
					type:"post",
					url:_ctx+"/back/warrant/order/getNodeFields",
					data:{
						nodeId:nodeId,
						orderDetailId:"${detailVo.id}"
					},
					datatype:"json",
					success:function(result){
						if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							var list = result.data;
							var str = "";
							var fieldName = "";
							for(var i in list){
								fieldName+=","+list[i].id+":"+list[i].fieldName;
								if(list[i].type == 1){
									if(list[i].fieldVal!=null){
										str+=list[i].fieldName+': <input readonly="readonly" type="text" fieldName="'+list[i].fieldName+'" value="'+list[i].fieldVal+'"></br></br>'
									}else{
										str+=list[i].fieldName+': <input type="text" fieldName="'+list[i].fieldName+'"></br></br>'
									}
								
								}else if(list[i].type == 2){
									if(list[i].fieldVal!=null){
										str+=list[i].fieldName+': <input type="text" fieldName="'+list[i].fieldName+'" value="'+list[i].fieldVal+'"></br></br>'
									}else{
										str+=list[i].fieldName+': <input type="text" fieldName="'+list[i].fieldName+'"></br></br>'
									}
								}else if(list[i].type == 3){
									var imgStr = "";
									var imgValStr = "";
									if(list[i].fieldVal!=null){
										var imgs = list[i].fieldVal.split(",")
										for(var j in imgs){
											imgStr+='<a target="_blank" href="'+imgs[j]+'"><img alt="error" style="width:100px;height:100px;margin:10px;" src="'+imgs[j]+'"></a>'
											imgValStr+='<input fieldname="图片" value="'+imgs[j]+'">';
										}
									}
									
									str+=list[i].fieldName+': <div id="nodePictureVal" style="display:none;">'+imgValStr+'</div><br/><br/><div id="nodePictureShow">'+imgStr+'</div>';
								}
							}
							if(str!=""){
								if("${detailVo.nodeId}"==nodeId&&"${detailVo.authStatus}"==1&&"${type}"==1){
									str+="<button onclick='toAuth(2)'>通过</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='toAuth(3)'>驳回</button>"
								}
								if(isauth!=2&&"${detailVo.nodeId}">=nodeId){
									str+="&nbsp;&nbsp;&nbsp;&nbsp;<button onclick='checkAuthHistory("+nodeId+")'>查看审核记录</button>";
								} 
								 
								$("#otherField").append(str);
								
							}
							$("input").prop("disabled",true);
							
						}
					}
				}) 
	 }else{
		 $("#orderDetail").show();
		 $("#otherField").hide();
	 }
	 $("#popup_box").hide();
 }
 function checkAuthHistory(nodeId){
	 openCommonModal(_ctx+"/back/warrant/order/toGetAuthHistory?nodeId="+nodeId+"&orderDetailId=${detailVo.id}",1000)
 }
 function toAuth(authResult){
	 $("#authResult").val(authResult);
	 $("#authModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true
    });
 }
 function saveAuth(){
		$("#authForm").verify();
		$("#authForm").validate(function (result) {
			if(result){
				$.ajax({
					type:"post",
					url:_ctx+"/back/warrant/order/auth",
					data:{
						nodeId: $("#nodeId").val(),
						orderId:"${detailVo.orderId}",
						orderDetailId:"${detailVo.id}",
						status:$("#authResult").val(),
						content:$("#content").val()
					},
					datatype:"json",
					success:function(result){
						if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							$("#authModal").modal("hide");
							//location=_ctx+"/back/frame/order/fwuser"
							location.reload();
						}
					}
				})
			}
		})
		
	}
 </script>
