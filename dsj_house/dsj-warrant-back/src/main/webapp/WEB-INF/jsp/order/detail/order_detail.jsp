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
						<div><span>订单号：</span>${detailVo.orderNo }</div>
						<div><span>购买人：</span>${detailVo.username }</div>
						<div><span>手机号：</span>${detailVo.phone }</div>
						<div><span>商品：</span>${detailVo.productName }</div>
						<div><span>下单时间：</span>
						<time><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${detailVo.createTime }"></fmt:formatDate></time>
						</div>
						<div><span>支付金额：</span>${detailVo.payment }</div>
						<div><span>区域：</span>${detailVo.areaName }</div>
					</div>
					<c:if test="${detailVo.nodeName == '服务开始' }">
					  	<div class="buttons">
							<button class="btn btn-default" type="button"  onclick="startFw()">开始服务</button>
						</div>
				  	</c:if>
				  	<c:if test="${detailVo.authStatus == 5 && detailVo.status!=7 }">
					  	<div class="buttons">
							<button class="btn btn-default" type="button" onclick="endFw()">结束服务</button>
						</div>
				  	</c:if>
				  	 <button type="button" class="btn btn-default" onclick="history.back(-1)" type="button">返回</button>
				</div>	
				<form id="otherField">
				</form>	
			</div>
		</div>
  	</div>
      </div>
 </div>	
 <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/order.css">
  <script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
 <script>
 $(function(){
	 
	 $("body").on("click",".image",function(e){
         e.preventDefault();
         deleteImage(this)
     });
	 
	 var currentNodeId = "${detailVo.nodeId}";
	 if(currentNodeId!=null){
		 $("#nodeList li a[nodeId='"+currentNodeId+"']").click();
	 }
	 
	 $("body").on("click","#startFwModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/warrant/order/start_fw_jd",
				data:{
					orderId:"${detailVo.id}",
					currentNodeIdLong:"${detailVo.nodeId}",
					nodeId:"${detailVo.nextNodeId}"
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						location.reload();
					}
				}
			})
		  
	  });
	 
	 $("body").on("click","#endFwModalConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/warrant/order/end_fw_jd",
				data:{
					orderDetailId:"${detailVo.id}",
					id:"${detailVo.orderId}",
					currentNodeId:"${detailVo.nodeId}"
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						location.reload();
					}
				}
			})
		  
	  });
	 $("body").on("click","#authCommitModalConfirm",function(){
		 var orderPo={
				 orderNodeDetail:getAllData(),
				 isauth:$("#isauth").val()
		 }
			$.ajax({
				type:"post",
				url:_ctx+"/back/warrant/order/commit_node",
				data:JSON.stringify(orderPo),
				contentType : 'application/json;charset=utf-8',
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						location.reload();
					}
				}
			})
		  
	  });
 })
 function startFw(){
	 setModalContent("确认要开启服务吗?","startFwModalConfirm");
 }
 function endFw(){
	 setModalContent("确认要结束服务吗?","endFwModalConfirm");
 }
 function beforeUpload()
 {
     loading_szyq("上传中...",true);
 }

 function afterUpload()
 {
     loading_szyq(false);
 }
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
							var hasImg = false;
							var hasTime = false;
							var auth = false;
							for(var i in list){
								fieldName+=","+list[i].id+":"+list[i].fieldName;
								if(list[i].type == 1){
									var isAuth = "";
									if(list[i].fieldName!="备注"){
										isAuth = "data-validate='required'";
									}
									if(list[i].fieldVal!=null){
										str+=list[i].fieldName+': <input type="text" fieldName="'+list[i].fieldName+'" value="'+list[i].fieldVal+'"'+isAuth+' maxlength="100"></br></br>'
									}else{
										str+=list[i].fieldName+': <input type="text" fieldName="'+list[i].fieldName+'"'+isAuth+' maxlength="100"></br></br>'
									}
								
								}else if(list[i].type == 2){
									if(list[i].fieldVal!=null){
										str+=list[i].fieldName+': <input type="text" class="laydate-icon layDateClass" fieldName="'+list[i].fieldName+'" value="'+list[i].fieldVal+'" data-validate="required" maxlength="100"></br></br>'
									}else{
										str+=list[i].fieldName+': <input type="text" class="laydate-icon layDateClass" fieldName="'+list[i].fieldName+'" data-validate="required" maxlength="100"></br></br>'
									}
									hasTime = true;
								}else if(list[i].type == 3){
									var imgStr = "";
									var imgValStr = "";
									if(list[i].fieldVal!=null){
										var imgs = list[i].fieldVal.split(",")
										for(var j in imgs){
											if(!("${detailVo.nodeId}"==nodeId&&"${detailVo.authStatus}"==1)){
												if(!("${detailVo.nodeId}"==nodeId&&"${detailVo.authStatus}"!=1&&"${detailVo.authStatus}"!=2)){
													imgStr+='<a target="_blank" href="'+imgs[j]+'" style="position: relative;display: inline-block;"><img alt="error" style="width:100px;height:100px;margin:10px;" src="'+imgs[j]+'"></a>'
												}else{
													imgStr+='<a target="_blank" href="'+imgs[j]+'" style="position: relative;display: inline-block;"><img alt="error" style="width:100px;height:100px;margin:10px;" src="'+imgs[j]+'"><span style="position: absolute;color: red;top: 10px;right: 15px;z-index:10;" href="javascript:void(0);" class="image">X</span></a>'
												}
												
											}else{
												imgStr+='<a target="_blank" href="'+imgs[j]+'" style="position: relative;display: inline-block;"><img alt="error" style="width:100px;height:100px;margin:10px;" src="'+imgs[j]+'"></a>'
											}
											imgValStr+='<input fieldname="图片" value="'+imgs[j]+'">';
										}
									}
									var btn = "";
									if("${detailVo.nodeId}"==nodeId&&"${detailVo.authStatus}"!=1&&"${detailVo.authStatus}"!=2&&"${type}"==1){
										btn = '<button class="btn btn-primary" type="button" >选择图片</button>';
									}
									
									str+=list[i].fieldName+': <div id="photoFilePicker" style="display: inline-block;">'+btn+'</div><div id="nodePictureVal" style="display:none;">'+imgValStr+'</div><br/><br/><div id="nodePictureShow">'+imgStr+'</div>';
									hasImg = true;
								}
							}
							if(str!=""){
								if("${detailVo.nodeId}"==nodeId&&"${detailVo.authStatus}"!=1&&"${detailVo.authStatus}"!=2&&"${type}"==1){
									str+="<button type='button' onclick=submitAuth('"+fieldName.substr(1)+"')>提交审核</button>"
								}else if("${detailVo.nodeId}"==nodeId&&"${detailVo.authStatus}"==1&&"${type}"==1){
									str+="<button disabled='disabled'>审核中</button>"
									auth = true;
								}else{
									auth = true;
								}
								if(isauth!=2&&"${detailVo.nodeId}">=nodeId){
									str+="&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' onclick='checkAuthHistory("+nodeId+")'>查看审核记录</button>";
								} 
								 
								$("#otherField").append(str);
								
							}
							
							if(hasImg){
								singleUploads("photoFilePicker","nodePictureVal","nodePictureShow","图片",beforeUpload,afterUpload);
							}
							if(hasTime){
								setLayDate();
							}
							if(auth){
								$("input").prop("disabled",true);
							}
							
						}
					}
				}) 
		 
	 }else{
		 $("#orderDetail").show();
		 $("#otherField").hide();
	 }
	 $("#popup_box").hide();
	
 }
 var attrs="";
function submitAuth(param){
	$("#otherField").verify();
	$("#otherField").validate(function (result) {
		if(typeof $(".btn-primary")){
			if($("input[fieldname='图片']").length==0){
				setErrorContent("请添加图片");
				return false;
			}
			if($("input[fieldname='图片']").length>=20){
				setErrorContent("最多20张图片");
				return false;
			}
		}
	  	if(result){
	  		attrs = param;
	  		setModalContent("确认要提交审核吗?","authCommitModalConfirm");
		}
	 })
	
 }
 function getAllData(){
	 var arr = new Array();
		var attr = attrs.split(",");
		for(var i in attr){
			var newAttr =attr[i].split(":")
			oderDetail = getOrderNodeDetail();
			oderDetail.fieldId = newAttr[0];
			oderDetail.fieldName = "'"+newAttr[1]+"'";
			oderDetail.fieldVal = "'"+getVal(newAttr[1])+"'";
			arr.push(oderDetail);
		}
		return arr;
 }
 function getOrderNodeDetail(){
	 var nodeDetail = {
		     orderId:"${detailVo.orderId}",		// 订单id
			 orderDetailId:"${detailVo.id}",		// 订单详情id
			 nodeId:$("#nodeId").val(),		// 节点id
			 fieldId:null,	// 字段id
			 fieldName:null,		// 字段名称
			 fieldVal:null	
	 }
	 return nodeDetail;
 }
 function getVal(fieldName){
	 var val = "";
	 $("input[fieldName='"+fieldName+"']").each(
		 function(){
			 val+=","+$(this).val();
		 }
	 )
	 if(val!=""){
		 val = val.substr(1);
	 }
	 return val;
}
 function checkAuthHistory(nodeId){
	 openCommonModal(_ctx+"/back/warrant/order/toGetAuthHistory?nodeId="+nodeId+"&orderDetailId=${detailVo.id}",1000)
 }
 function deleteImage(_this){
	 $(_this).parents("a").remove();
	 var src = $(_this).prev("img").attr("src");
	 $("input[value='"+src+"']").remove();
 }
 </script>
