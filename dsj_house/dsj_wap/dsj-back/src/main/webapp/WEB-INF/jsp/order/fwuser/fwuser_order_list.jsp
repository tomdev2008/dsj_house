<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
			<div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation" class="active" onclick="selectType(this,'','')"><a href="javascript:void(0);">全部订单</a></li>
					<li role="presentation" onclick="selectType(this,'1','')"><a href="javascript:void(0);">待付款订单</a></li>
					<li role="presentation" onclick="selectType(this,'4','1')"><a href="javascript:void(0);">待商家开启服务</a></li>
					<li role="presentation" onclick="selectType(this,'','2')"><a href="javascript:void(0);">在办订单</a></li>
					<li role="presentation" onclick="selectType(this,'','3')"><a href="javascript:void(0);">退款订单</a></li>
					<li role="presentation" onclick="selectType(this,'','4')"><a href="javascript:void(0);">待结算订单</a></li>
					<li role="presentation" onclick="selectType(this,'','5')"><a href="javascript:void(0);">已结订单</a></li>
					<li role="presentation" onclick="selectType(this,'','6')"><a href="javascript:void(0);">无效订单</a></li>
				</ul>
			</div>
			     <form id="searchFromId" class="form-inline form_kuang">
		                <div class="form-group mr20">
					        <span class="wenzi6">下单时间:</span>
					        <input value=""  style="width:170px;" class="small-input form-control laydate-icon" id="LAY_demorange_ss_one" placeholder="开始时间" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value="" style="width:170px;" class="small-input form-control laydate-icon" id="LAY_demorange_ee_one" placeholder="结束时间" name="endTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group">
								<span class="wenzi6">区域:</span>
									<select id="areaOneId" class="form-control dsj-inline dsj-width-1" name="areaCodeOne" onchange="getTwoArea()">
										<option value="">请选择</option>
										<c:forEach items="${firstAreaList }" var="area">
											<option value="${area.areaCode }">${area.name }</option>
										</c:forEach>
									</select>
									<select id="areaTwoId" class="form-control dsj-inline dsj-width-1" name="areaCodeTwo" onchange="getThreeArea()">
												<option value="">请选择</option>
									</select>
									<select id="areaThreeId" class="form-control dsj-inline dsj-width-1" name="areaCodeThree">
												<option value="">请选择</option>
									</select>
						</div>
						<div class="form-group mr20 ywjd">
		                	<span class="wenzi6">业务类型:</span>
		                    <select class="form-control dsj-inline dsj-width-1" name="skuId" onchange="getTypeNode()" id="skuId">
										<option value="">商品名称</option>
										<c:forEach items="${skuList }" var="sku">
											<option value="${sku.id }" typeId="${sku.typeId }">${sku.name }</option>
										</c:forEach>
							 </select>
							 <select class="form-control dsj-inline dsj-width-1" name="nodeId" id="typeNode">
										<option value="">业务节点</option>
							  </select>
		                </div>
		               
	                	<div class="form-group mr20">
		                	<span class="wenzi6">买家手机号:</span>
		                    <input type="text" class="small-input form-control" placeholder="买家手机号" name="phone">
		                </div>
		                 <div class="form-group mr20">
		                	<span class="wenzi6">代办人姓名:</span>
		                    <input type="text" class="small-input form-control" placeholder="代办人姓名" name="dbUserName">
		                </div>
		                <div class="form-group mr20">
		                	<span class="wenzi6">代办人手机号:</span>
		                    <input type="text" class="small-input form-control" placeholder="代办人手机号" name="dbPhone">
		                </div>
		                <div class="form-group mr20">
		                	<span class="wenzi6">订单编号:</span>
		                    <input type="text" class="small-input form-control" placeholder="订单编号" name="orderNo">
		                </div>
		                <div class="form-group mr20">
							<span class="wenzi6">商家名称:</span>
							 <input type="text" class="small-input form-control" placeholder="商家名称" name="companyName">
		                </div>
		                <div class="form-group mr20" id="authStatus">
		                	<span class="wenzi6">节点状态:</span>
		                    <select class="form-control dsj-inline dsj-width-1" name="authStatus">
									<option value="">请选择</option>
									<option value="1">待审核</option>
									<option value="2">已结束</option>
									<option value="3">已驳回</option>
									<option value="4">待提交</option>
									<option value="5">待结束</option>
									<option value="6">待开启</option>
						   </select>
		                </div>
		                <input type="text" name="status" id="status"   style="display: none">
		                <input type="text" name="statusone" id="statusone"   style="display: none">
		                <div class="form-group mr20 status" id="status0">
							<span class="wenzi6">订单状态:</span>
							<select class="form-control dsj-inline dsj-width-1" onchange="setStatus(this)">
									<option value="">请选择</option>
									<option value="1">待付款</option>
									<option value="2">已取消</option>
									<option value="3">已过期</option>
									<option value="4">已付款</option>
									<option value="5">退款受理中</option>
									<option value="6">退款完成</option>
									<option value="7">待买家确认</option>
									<option value="9">待评价 </option>
									<option value="10">已评价</option>
									<option value="12">服务进行中</option>
									<option value="13">待结束服务</option>
						   </select>
		                </div>
		                <!-- <div class="form-group mr20 status" id="status1">
							<span class="wenzi6">订单状态:</span>
							<select class="form-control dsj-inline dsj-width-1" onchange="setStatus(this)">
									<option value="">请选择</option>
									<option value="4">待商家确认</option>
									<option value="12">商家已确认</option>
									<option value="13">待结束服务</option>
									<option value="7">待买家确认</option>
						   </select>
		                </div> -->
		                 <div class="form-group mr20 status" id="status2">
							<span class="wenzi6">订单状态:</span>
							<select class="form-control dsj-inline dsj-width-1" onchange="setStatus(this)">
									<option value="">请选择</option>
									<option value="12">服务进行中</option>
									<option value="13">待结束服务</option>
									<option value="7">待买家确认</option>
						   </select>
		                </div>
		                <div class="form-group mr20 status" id="status4">
							<span class="wenzi6">订单状态:</span>
							<select class="form-control dsj-inline dsj-width-1" onchange="setStatus(this)">
									<option value="">请选择</option>
									<option value="9">待评价</option>
									<option value="10">已评价</option>
						   </select>
		                </div>
		                 <div class="form-group mr20 status" id="status5">
							<span class="wenzi6">订单状态:</span>
							<select class="form-control dsj-inline dsj-width-1" onchange="setStatus(this)">
									<option value="">请选择</option>
									<option value="9">待评价</option>
									<option value="10">已评价</option>
									<option value="6">退款完成</option>
						   </select>
		                </div>
		                 <div class="form-group mr20 status" id="status6">
							<span class="wenzi6">订单状态:</span>
							<select class="form-control dsj-inline dsj-width-1" onchange="setStatus(this)">
									<option value="">请选择</option>
									<option value="3">已过期</option>
									<option value="2">已取消</option>
						   </select>
		                </div>
		                <div class="form-group mr20 status" id="refuse" style="display: none;">
							<span class="wenzi6">退款原因:</span>
							<!-- 1重复下单 2 失误下单 3 不想买了 4服务不满意  5其他原因 -->
							<select class="form-control dsj-inline dsj-width-1" name="refundtype">
									<option value="">请选择</option>
									<option value="1">重复下单</option>
									<option value="2">误下单</option>
									<option value="3">误下单</option>
									<option value="4">不想买了</option>
									<option value="5">服务不满意</option>
									<option value="6">其他原因</option>
						   </select>
		                </div>
		                <div class="form-group mr20 status" id="refuseTime" style="display: none;">
					        <span class="wenzi6">退款时间:</span>
					        <input style="width:170px;" class="small-input form-control laydate-icon" id="LAY_demorange_ss_two" placeholder="开始时间" name="startRefuseTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input style="width:170px;" class="small-input form-control laydate-icon" id="LAY_demorange_ee_two" placeholder="结束时间" name="endRefuseTime" style="width: 120px;">
	                	</div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                      <button class="btn btn-default" type=reset style="display: none;">重置</button>
		                     <button class="btn btn-default" type="button" id="resetBtn" onclick="resetForm(this)">重置</button>
		                     <shiro:hasPermission name='fwOrder:export'>
		                      <button class="btn btn-primary" onclick="exportExcel()" type="button">导出</button>
		                     </shiro:hasPermission>
		                </div>
		                
		         </form> 
		      </div>
		       <div class="white-content">
				<div class="row">
			        <!--表格 S-->
			        <div class="slb_table H_slb_table col-xs-12">
			            <div class="table-responsive">
			                <table class="table table-bordered" id="dataTable">
			                     <thead>
					             <tr>
					                 <th>下单时间</th>
					                 <th>下单区域</th>
					                 <th>业务类型</th>
					                 <th>订单编号</th>
					                 <th>订单金额</th>
					                 <th>买家名称</th>
					                 <th>手机号</th>
					                 <th>代办人名称</th>
					                 <th>代办人手机号</th>
					                 <th>商家名称</th>
					                 <th>订单状态</th>
					                 <th>退款时间</th>
					                 <th>退款原因</th>
					                 <th>业务节点</th>
					                 <th>节点状态</th>
					                 <th>操作</th>
					             </tr>
					             </thead>
					             <tbody>
					             
					             </tbody>
			                </table>
			            </div>
			        </div>
		        </div>    
		 </div>
 </div>	
<script type="text/javascript">
function resetForm(_this){
	var statusone = $("#statusone").val();
	$(_this).prev().click();
	$("#statusone").val(statusone);
}
var userManage = {
		getQueryCondition : function(aoData) {
			aoDataChange("searchFromId",aoData);
		}
};
 $(function(){
	 //所有状态隐藏
	 $(".status").hide();
	 $("#status0").show();
	 setLayDateOne();
	 setLayDateTwo();
		//点击查询重新加载
		$("#search_btn").click(function(){
		 	$("#dataTable").DataTable().ajax.reload();
		});
		
		 $('#dataTable').DataTable( {
			  "bSort": false,		//取消默认排序查询,否则复选框一列会出现小箭头
			  "bProcessing": false,	//隐藏加载提示,自行处理
		      "serverSide": true,
		      "bProcessing": true,
		      "bLengthChange": true,
		     /*    "bStateSave": true, */
		      "bFilter": false,  //搜索框
		      /*  "sPaginationType" : "extStyle",   */
		      "bAutoWidth": true,//关闭后，表格将不会自动计算表格大小
		      "sAjaxDataProp":"data",
		      "bInfo": true,//页脚信息 
		      "iDisplayLength":10,
		      "sAjaxSource": _ctx+"/back/frame/order/fwuser/page/list",
		      "language": {
		      "lengthMenu": "",
		      "zeroRecords": "没有找到记录",
		      "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
		      "infoEmpty": "",
		      "infoFiltered": "(从 _MAX_ 条记录过滤)",
		       "paginate": {
		         	   "previous": "上一页",
		                "next": "下一页",
		                "first":"首页",
		                "last":"尾页",
		        	 } 
		    	 },
		      
		       "fnServerData":function(sSource, aoData, fnCallback) { 
		    	   userManage.getQueryCondition(aoData);
		        	 $.ajax({
		        	  "type" : 'POST',  
		              "url" : sSource,
		              "dataType":"json",  
		                "data":{  
		                     aoData:JSON.stringify(aoData)  
		              },  
		                "success":function(resp) {
		                      fnCallback(resp);  
		                  }  
		        });},
		            "aoColumns" : [{
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "areaName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "productName",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	return "<a href='javascript:void(0);' onclick='checkOrder("+full.id+",2)'>"+full.orderNo+"</a>";
		                  }
		            },{
		        		"mData" : "payment",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "username",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "phone",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "dbUserName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "dbPhone",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "dbCompanyName",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                   if(full.status==1){
			                		return "待付款";
			                	}else if(full.status==2){
			                		return "已取消";
			                	}else if(full.status==3){
			                		return "已过期";
			                	}else if(full.status==4){
			                		return "已付款";
			                	}else if(full.status==5){
			                		return "退款受理中";
			                	}else if(full.status==6){
			                		return "退款完成";
			                	}else if(full.status==7){
			                		return "待买家确认";
			                	}else if(full.status==9){
			                		return "待评价";
			                	}else if(full.status==10){
			                		return "已评价";
			                	}else if(full.status==12){
			                		return "服务进行中";
			                	}else if(full.status==13){
			                		return "待结束服务";
			                	}
		                  }
		            },{
		        		"mData" : "refunddate",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "refundReason",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
					     		return full.nodeName;
		                  }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	  if(full.status!=2&&full.status!=2){
		                		  if(full.status!=1){
					                	if(full.authStatus==1){
					                		return "待审核";
					                	}else if(full.authStatus==2){
					                		return "已结束";
					                	}else if(full.authStatus==3){
					                		return "已驳回";
					                	}else if(full.authStatus==4){
					                		return "待提交";
					                	}else if(full.authStatus==5){
					                		return "待结束";
					                	}else if(full.authStatus==6){
					                		return "待开启";
					                	}
				                	  }
		                	  }
		                  }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	  var check = "";
		                	  if(full.authStatus==1&&full.statusone==2){
		                		  check = "<shiro:hasPermission name='fwOrder:audit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='checkOrder("+full.id+",1)'>审核</a></shiro:hasPermission>";
		                	  }else{
		                		  check = "";
		                	  }
		                	  var history = "<shiro:hasPermission name='fwOrder:history'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='history("+full.id+")'>历史操作</a></shiro:hasPermission>";
		                	  if(full.statusone==5){
				                	return history;
		                  	  }else if(full.statusone==2||full.statusone==1){
			                	return check+history;
		                  	  }else if(full.statusone==4){//结算
		                  		 var jiesuan = "<shiro:hasPermission name='fwOrder:jieSuan'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='jiesuan("+full.orderId+")'>确认结算</a></shiro:hasPermission>";
		                  		return jiesuan+check+history; 
		                  	  }else if(full.statusone==3){//退款
		                  		 var refuse = "<shiro:hasPermission name='fwOrder:tuiKuan'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='refuse("+full.orderId+")'>确认退款</a></shiro:hasPermission>";
		                  		 return refuse+check+history;
		                  	  }
		                	
		                  }
	                }
		        	]
		    } );
	});
 
 function getTypeNode(){
	 $("#typeNode").empty();
	 $("#typeNode").append('<option value="">业务节点</option>');
	 if($("#skuId").val()!=""){
		 $.ajax({
				type:"post",
				url:_ctx+"/back/shop/node_list",
				data:{
					typeId:$("#skuId option:selected").attr("typeId")
				},
				datatype:"json",
				success:function(result){
					if(result.status = 200 ){
						var list = result.data;
						for(var i in list){
							$("#typeNode").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>");
						}
					}else{
						setErrorContent(result.message);
					}
				}
			})
	 }
	
 }
 //切换tab
 function selectType(_this,status,statusone){
	 $("#resetBtn").click();
	 
	 $(".button_tab li").removeClass("active");
	 $(_this).addClass("active");
	 $("#status").val(status);
	 $("#statusone").val(statusone);
	 
	 $(".status").hide();
	 if(status==""&&statusone==""){
		 $("#status0").show();
	 }else if(statusone!=""){
		 if(typeof $("#status"+statusone) ){
			 $("#status"+statusone).find("select").val("");
			 $("#status"+statusone).show();
		 }
	 }
	 if(statusone==3||statusone==5){
		 $("#refuse").show();
		 $("#refuseTime").show();
	 }
	 
	 if(statusone==6){
		 $(".ywjd").hide();
	 }else{
		 $(".ywjd").show();
	 }
	 if(status==""&&(statusone==""||statusone==2)){
		 $("#authStatus").show();
	 }else{
		 $("#authStatus").hide();
	 }
	 $("#search_btn").click();
 }
 //子状态
 function setStatus(_this){
	 $("#status").val( $(_this).val());
 }
 
 var id;
 $("body").on("click","#jiesuanModalConfirm",function(){
		$.ajax({
			type:"post",
			url:_ctx+"/back/frame/order/fwuser/jiesuan",
			data:{
				id:id
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					$("#search_btn").click();
				}
			}
		})
	  
	});
 $("body").on("click","#tuikuanModalConfirm",function(){
		$.ajax({
			type:"post",
			url:_ctx+"/back/frame/order/fwuser/tuikuan",
			data:{
				id:id
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					$("#search_btn").click();
				}
			}
		})
	  
	});
 //确认结算
 function jiesuan(param){
	 id = param;
	 setModalContent("确认结算吗？","jiesuanModalConfirm");
 }
//确认退款
 function refuse(param){
	 id = param;
	 setModalContent("确认退款吗？","tuikuanModalConfirm");
 }
 function checkOrder(id,type){
	location =  _ctx+"/back/frame/warrant/order/detail?id="+id+"&type="+type;
 }
 //查看历史
 function history(id){
	 openCommonModal(_ctx+"/back/warrant/order/toGetHistory?orderDetailId="+id,1000)
 }
 //导出
 function exportExcel(){
	 var fromData=serializeForm("searchFromId");
	 location.href = _ctx+"/back/order/fwuser/export?"+fromData 
 }
 </script>
