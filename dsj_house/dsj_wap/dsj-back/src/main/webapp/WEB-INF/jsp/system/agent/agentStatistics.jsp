<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			经纪人列表
		</h1>
		<div class="row">
			   <button class="dsj_btn btn btn-default" type="button" onclick="yesterday()">昨日新增</button>
			   <button class="dsj_btn btn dsj_btn_green" type="button">累计数据</button>
		</div> 
			     <form id="searchFromId" class="form-inline">
			     		<div class="form-group mt20">
			    		   <span class="wenzi6">日期:</span>
		                    <input type="text" class="small-input form-control" id="LAY_demorange_zs" name="timeStart">
			    		   <span class="wenzi6">至</span>
		                    <input type="text" class="small-input form-control" id="LAY_demorange_ze" name="timeEnd">
			    
		              	    <span class="wenzi6">经纪人:</span>
		                    <input type="text" class="small-input form-control" placeholder="模糊匹配" id="name" name="name">
		                     <span class="wenzi6">手机号:</span>
		                    <input type="text" class="small-input form-control" maxlength="11" id="phone" name="phone">
		                     
		              	 
			            </div>
			            <div class="form-group mt20">
			            	<span class="wenzi6">公司:</span>
		                    <input type="text" class="small-input form-control" maxlength="20" placeholder="公司(不超过20个字)" id="companyName" name="companyName">
			            	<button class="btn btn-primary" onclick="export1()" type="button">导出</button>
			            </div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
			        
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             
				             	 <th>日期</th>
				                 <th>经纪人姓名</th>
				                 <th>手机号</th>
				                 <th>公司</th>
				                 <th>发布楼盘动态数</th>
				                 <th>发布经纪人动态数</th>
				                 <th>经纪人动态回复数</th>
				                 <th>经纪人动态好评数</th>
								 <th>楼盘点评数</th>
								 <th>楼盘点评回复数</th>
								 <th>楼盘点评好评数</th>
								 <th>新增经纪人等级分值</th>
				             </tr>
				             </thead>
				             <tbody>
				             
				             </tbody>
		                </table>
		            </div>
		        </div>
		 </div>
 </div>	
 <script type="text/javascript">
 $(function(){
		var userManage = {
				getQueryCondition : function(aoData) {
					aoDataChange("searchFromId",aoData);
				}
		};
		//点击查询重新加载
		$("#search_btn").click(function(){
		 	$("#dataTable").DataTable().ajax.reload();
		 	var type =$("#status").val()
		 	if(type==1){
				$("#accessBtn").show()
				$("#refuseBtn").show()
			}else{
				$("#accessBtn").hide()
				$("#refuseBtn").hide()
			}
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
		      "sAjaxSource": _ctx+"/back/frame/agentStatistic/dataList",
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
		            
		            "aoColumns" : [
		            	{
			        		"mData" : "timeString",
			        		"aTargets" : [ 0 ]
			        	},{
		            	"mData" : "name",
		            	"aTargets" : [ 1 ]
		            	
		              }, {
		        		"mData" : "phone",
		        		"aTargets" : [ 2 ]
		        	}, {
		        		"mData" : "companyName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "houseNewsNum",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"aTargets" : [ 5 ],
		        		"mData" : "agentNewsNum"
		        	},{
		        		"mData" : "agentNewsReplyNum",
		        		"aTargets" : [ 6 ]
		        	},{
		        		"mData" : "agentNewsLikeNum",
		        		"aTargets" : [ 7 ]
		        	},{
		        		"mData" : "houseRemarkNum",
		        		"aTargets" : [ 8 ]
		        	},{
		        		"mData" : "houseRemarkReplyNum",
		        		"aTargets" : [ 9 ]
		        	},{
		        		"mData" : "houseRemarkLikeNum",
		        		"aTargets" : [ 10 ]
		        	},{
		        		"mData" : "agentGrade",
		        		"aTargets" : [ 11 ]
		        	}
		        	]
		    } );
	});
 function yesterday(){
	 window.location = _ctx+"/back/frame/agentStatistic/yesterday";
 }
 function export1(){
	 var fromData=serializeForm("searchFromId");
	 location.href = _ctx+"/back/frame/agentStatistic/export?"+fromData 
	 
 }
 
 </script>       
