<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
			<div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation" class="active"><a href="${ctx }/back/frame/mobileManager/detail">楼盘来电明细</a></li>
					<li role="presentation"><a href="${ctx }/back/frame/mobileManager/detail/mobile_agent_list">经纪人来电明细</a></li>
					<li role="presentation"><a href="${ctx }/back/frame/mobileManager/detail/mobile_property_list">代办人来电明细</a></li>
				</ul>
			</div>
			     <form id="searchFromId" class="form-inline">
		                <div class="form-group mr20">
					        <span class="wenzi6">创建时间:</span>
					        <input  class="small-input form-control laydate-icon" id="LAY_demorange_ss_one"  placeholder="开始时间" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input class="small-input form-control laydate-icon" id="LAY_demorange_ee_one"  placeholder="结束时间" name="endTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group mr20">
		                	<span class="wenzi6">被叫号码:</span>
		                    <input type="text" class="small-input form-control" placeholder="被叫号码" name="dni">
		                    <input type="hidden" value="1" name="type">
		                </div>
	                	 <div class="form-group mr20">
		                	<span class="wenzi6">楼盘编码:</span>
		                    <input type="text" class="small-input form-control" placeholder="楼盘编码" name="houseCode">
		                </div>
			    		 <div class="form-group mr20">
			    		   <span class="wenzi6">楼盘名称:</span>
		                    <input type="text" class="small-input form-control" placeholder="楼盘名称" name="houseName">
		                </div>
	                	 <div class="form-group mr20">
							<span class="wenzi6">小号位置:</span>
							<select class="form-control dsj-inline dsj-width-1" name="channel">
									<option value="">请选择</option>
									<option value="1">PC</option>
									<option value="2">WAP</option>
									<option value="3">APP</option>
						   </select>
		                </div>
		                 <div class="form-group mr20">
							<span class="wenzi6">通话结果:</span>
							<select class="form-control dsj-inline dsj-width-1" name="callresult">
									<option value="">请选择</option>
									<option value="0">成功</option>
									<option value="1">忙</option>
									<option value="2">无应答</option>
									<option value="3">客户提前挂机</option>
									<option value="11">客户主动放弃</option>
									<option value="201">无效分机号</option>
									<option value="1000">非工作时间</option>
									<option value="1002">欠费</option>
						   </select>
		                </div>
		                 <div class="form-group mr20">
			    		   <span class="wenzi6">来电归属地:</span>
		                    <input type="text" class="small-input form-control" placeholder="来电归属地" name="cityname">
		                </div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                     <button class="btn btn-primary" type="button" onclick="exportExcel()">导出</button>
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
					                 <th>主叫号码</th>
					                 <th>被叫号码</th>
					                 <th>楼盘名称</th>
					                 <th>楼盘编码</th>
					                 <th>400号码</th>
					                 <th>小号位置</th>
					                 <th>通话时长</th>
					                 <th>通话结果</th>
					                 <th>开始时间</th>
					                 <th>来电归属地</th>
					                 <th>录音</th>
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
function getRecord(record,ani,startDate){
	if(record){
		window.open(_ctx+"/back/frame/mobileManager/detail/record?record="+record+"&ani="+ani+"&startDate="+startDate);
	}else{
		setErrorContent("没有录音!");
	}
	
}
function exportExcel(){
	 var fromData=serializeForm("searchFromId");
	 location.href = _ctx+"/back/frame/mobileManager/detail/exportNewHouse?"+fromData 
}
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
		      "sAjaxSource": _ctx+"/back/frame/mobileManager/detail/page/list",
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
		        		"mData" : "ani",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "dni",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "houseName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "houseCode",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "extcode",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.channel==1){
			                		return "PC";
			                	}else if(full.channel==2){
			                		return "WAP";
			                	}else{
			                		return "APP";
			                	}
		                  }
		            },{
		        		"mData" : "callertime",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.callresult==0){
			                		return "成功";
			                	}else if(full.callresult==1){
			                		return "忙";
			                	}else if(full.callresult==2){
			                		return "无应答";
			                	}else if(full.callresult==3){
			                		return "客户提前挂机";
			                	}else if(full.callresult==11){
			                		return "客户主动放弃";
			                	}else if(full.callresult==201){
			                		return "无效分机号";
			                	}else if(full.callresult==1000){
			                		return "非工作时间";
			                	}else if(full.callresult==1002){
			                		return "欠费";
			                	}
		                  }
		            },{
		        		"mData" : "startdate",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "cityname",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	  var str = "getRecord('"+full.recorderwav+"','"+full.ani+"','"+full.startdate+"')";
		                	  var down = "<shiro:hasPermission name='mobileMsgList:down'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick=\""+str+"\">下载</a></shiro:hasPermission>";
			                	return down;
		                  }
	                }
		        	]
		    } );
	});
 </script>
