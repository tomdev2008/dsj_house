<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
			<div class="row">
				<ul class="nav nav-tabs button_tab">
					<li role="presentation" class="active"><a href="javascript:void(0);">昨日新增</a></li>
					<li role="presentation"><a href="${ctx }/back/frame/mobileManager/calldata/byNewHouse/newHouseTotal">累计数据</a></li>
				</ul>
			</div>
			<div class="row">
				<ul class="nav nav-tabs button_tab" id="tablist">
					<li class="active"><a href="javascript:void(0);" onclick="changeTab(this,1)">PC端400来电统计</a></li>
					<li><a href="javascript:void(0);" onclick="changeTab(this,2)">WAP端400来电统计</a></li>
				</ul>
			</div>
			     <form id="searchFromId" class="form-inline">
		                <div class="form-group mr20">
					        <span class="wenzi6">时间:</span>
					        <input  class="small-input form-control laydate-icon" id="LAY_demorange_ss_3"  placeholder="开始时间" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input class="small-input form-control laydate-icon" id="LAY_demorange_ee_3"  placeholder="结束时间" name="endTime" style="width: 120px;">
	                		 <input type="hidden" name="channel" value="1">
	                	</div>
	                	<div class="form-group mr20">
		                	<span class="wenzi6">楼盘名称:</span>
		                    <input type="text" class="small-input form-control" placeholder="输入查询楼盘姓名" name="newHouseName">
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
					                 <th>楼盘名称</th>
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
	 setLayDateThree();
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
		      "bFilter": false,  //搜索框
		      "bAutoWidth": true,//关闭后，表格将不会自动计算表格大小
		      "sAjaxDataProp":"data",
		      "bInfo": true,//页脚信息 
		      "iDisplayLength":10,
		      "sAjaxSource": _ctx+"/back/frame/mobileManager/calldata/byNewHouse/yesterdayPageList",
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
		        		"mData" : "newHouseName",
		        		"aTargets" : [ 0 ]
		        	}, {
		        		"mData" : "callCount",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "callSuccess",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "callSuccessLv",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "callBusy",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "callBusyLv",
		        		"aTargets" : [ 5 ]
		        	},{
		        		"mData" : "callNot",
		        		"aTargets" : [ 6 ]
		        	},{
		        		"mData" : "callNotLv",
		        		"aTargets" : [ 7 ]
		        	}
		        	]
		    } );
	});
 function changeTab(_this,channel){
	 $("#tablist").find("li").removeClass("active");
	 $(_this).parent().addClass("active");
	 $("input[name='channel']").val(channel);
	 $("#dataTable").DataTable().ajax.reload();
 }
 function exportExcel(){
	 var fromData=serializeForm("searchFromId");
	 location.href = _ctx+"/back/frame/mobileManager/calldata/byNewHouse/exportYesterday?"+fromData 
 }
 </script>
