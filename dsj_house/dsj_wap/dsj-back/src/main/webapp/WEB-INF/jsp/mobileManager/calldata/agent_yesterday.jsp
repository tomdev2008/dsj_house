<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
			<div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation" class="active"><a href="${ctx }/back/frame/mobileManager/calldata/byAgent">昨日新增</a></li>
					<li role="presentation"><a href="${ctx }/back/frame/mobileManager/calldata/byAgent/agentTotal">累计数据</a></li>
				</ul>
			</div>
			     <form id="searchFromId" class="form-inline">
		                <div class="form-group mr20">
					        <span class="wenzi6">时间:</span>
					        <input  class="small-input form-control laydate-icon" id="LAY_demorange_ss_one"  placeholder="开始时间" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input class="small-input form-control laydate-icon" id="LAY_demorange_ee_one"  placeholder="结束时间" name="endTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group mr20">
		                	<span class="wenzi6">经纪人姓名:</span>
		                    <input type="text" class="small-input form-control" placeholder="输入查询经纪人姓名" name="agentName" id="agentName">
		                </div>
			    		 <div class="form-group mr20">
			    		   <span class="wenzi6">手机号:</span>
		                    <input type="text" class="small-input form-control" placeholder="输入查询经纪人手机号" name="tellPhone" id="tellPhone">
		                </div>
		            	<div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                     <button class="btn btn-primary" onclick="exportExcel()" type="button">导出</button>
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
					                 <th>日期</th>
					                 <th>经纪人姓名</th>
					                 <th>手机号</th>
					                 <th>400来电总量</th>
					                 <th>成功量</th>
					                 <th>接通率</th>
					                 <th>未接通量</th>
					                 <th>未接通率</th>
					                 <th>未拨通量</th>
					                 <th>未拨通率</th>
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
 $(function(){
	 setLayDateOne();
		var userManage = {
				getQueryCondition : function(aoData) {
					aoDataChange("searchFromId",aoData);
				}
		};
		//点击查询重新加载
		$("#search_btn").click(function(){
		 	$("#dataTable").DataTable().ajax.reload();
		 	$("#checkall").prop("checked",false);
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
		      "sAjaxSource": _ctx+"/back/frame/mobileManager/calldata/byAgent/yesterdayPageList",
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
		            
		            "aoColumns" : [ {
		        		"mData" : "time",
		        		"aTargets" : [ 0 ]
		        	},{
		        		"mData" : "agentName",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 2 ]
		        	}, {
		        		"mData" : "callCount",
		        		"aTargets" : [ 3 ]
		        	}, {
		        		"mData" : "callSuccess",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "callSuccessLv",
		        		"aTargets" : [ 5 ]
		        	},{
		        		"mData" : "callBusy",
		        		"aTargets" : [ 6 ]
		        	},{
		        		"mData" : "callBusyLv",
		        		"aTargets" : [ 7 ]
		        	},{
		        		"mData" : "callNot",
		        		"aTargets" : [ 8 ]
		        	},{
		        		"mData" : "callNotLv",
		        		"aTargets" : [ 9 ]
		        	}
		        	]
		    } );
	});
 
 
	//导出
 function exportExcel(){
	//if($("#agentName").val()==''&&$("#tellPhone").val()==''&&$("#LAY_demorange_ss_one").val()==''&&$("#LAY_demorange_ee_one").val()==''){
	//	setErrorContent("请选择需要导出的数据");
	//}else{
	 	var fromData=serializeForm("searchFromId");
	 	location.href = _ctx+"/back/frame/mobileManager/calldata/byAgent/exportAgentYesterday?"+fromData; 
	//}
 }
 
 </script>
