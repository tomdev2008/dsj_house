<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
			 <div class="row">
			     <form id="searchFromId" class="form-inline">
		                <div class="form-group mr20">
					        <span class="wenzi6">下单时间:</span>
					        <input value=""  class="small-input form-control layDateClass laydate-icon" placeholder="开始时间" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control layDateClass laydate-icon" placeholder="结束时间" name="endTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group mr20">
		                	<span class="wenzi6">买家手机号:</span>
		                    <input type="text" class="small-input form-control" placeholder="买家手机号" name="phone">
		                </div>
		                <div class="form-group mr20">
		                	<span class="wenzi6">订单编号:</span>
		                    <input type="text" class="small-input form-control" placeholder="订单编号" name="orderNo">
		                </div>
		                <div class="form-group mr20">
		                	<span class="wenzi6">评价状态:</span>
		                    <select class="form-control dsj-inline dsj-width-1" name="reviewStatus">
									<option value="">请选择</option>
									<option value="0">未评价</option>
									<option value="1">已评价</option>
						   </select>
		                </div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset" id="resetBtn">重置</button>
		                </div>
		         </form> 
		      </div>
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
				                 <th>订单编号</th>
				                 <th>下单金额</th>
				                 <th>买家名称</th>
				                 <th>代办人名称</th>
				                 <th>代办公司</th>
				                 <th>订单状态</th>
				                 <th>结果</th>
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
</div>
<script type="text/javascript">
 $(function(){
	 setLayDate();
		var userManage = {
				getQueryCondition : function(aoData) {
					aoDataChange("searchFromId",aoData);
				}
		};
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
		      "sAjaxSource": _ctx+"/back/frame/warrant/evaluate/page/list",
		      "language": {
		      "lengthMenu": "",
		      "zeroRecords": "没有找到记录",
		      "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
		      "infoEmpty": "无记录",
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
		            
		            "aoColumns" : [  {
		        		"mData" : "createTime",
		        		"aTargets" : [ 0 ]
		        	}, {
		        		"mData" : "orderNo",
		        		"aTargets" : [ 1 ]
		        	},{
		        		"mData" : "orderPrice",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "realName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "name",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "companyName",
		        		"aTargets" : [ 5 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.reviewStatus==0){
			                		return "未评价";
			                	}else if(full.reviewStatus==1){
			                		return "已评价";
			                	}
		                  }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.type==1){
			                		return "差评";
			                	}else if(full.type==2){
			                		return "中评";
			                	}else if(full.type==3){
			                		return "好评";
			                	}
		                  }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var down = "<a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='checkEvaluate("+full.orderDetailId+")'>查看</a>";
		                	return down;
		                  }
	                }
		        	]
		    } );
	});
 
 function checkEvaluate(param){
		location=_ctx+"/back/frame/warrant/evaluate/evaluate_check?id="+param;
	}
 </script>
