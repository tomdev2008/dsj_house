<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
				<div class="modal-header">
                          <button type="button" id="closeBtn" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
                          <h4 class="modal-title">绑定历史</h4>
                      </div>
                      <div class="modal-body" style="height: 400px">
							<form id="searchFromId" class="form-inline">
				                    <input type="hidden" name="mobileId" value="${id }">
				         	</form> 
					        <!--表格 S-->
					        <div class="slb_table H_slb_table col-xs-12">
					            <div class="table-responsive">
					                <table class="table table-bordered" id="dataTable">
					                     <thead>
							             <tr>
							                 <th>400号码</th>
							                 <th>代办人名称</th>
							                 <th>操作</th>
							                 <th>操作时间</th>
							             </tr>
							             </thead>
							             <tbody>
							             
							             </tbody>
					                </table>
					            </div>
							 </div>
                      </div>
                      <div class="modal-footer">
                          <button type="button" class="dsj_btn btn btn-default" data-dismiss="modal">关闭</button>
                      </div>
<script type="text/javascript">
 $(function(){
		var userManage = {
				getQueryCondition : function(aoData) {
					aoDataChange("searchFromId",aoData);
				}
		};
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
		      "iDisplayLength":5,
		      "sAjaxSource": _ctx+"/back/frame/mobileManager/property/historyPage/list",
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
		        
		            "aoColumns" : [{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	return full.mobile;
			                }
		            },{
		        		"mData" : "propertyName",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.status==1){
			                		return "绑定";
			                	}else{
			                		return"解绑";
			                	}
		                  }
		            },{
		        		"mData" : "createTime",
		        		"aTargets" : [ 3 ]
		        	}]
		    } );
	});
 </script>
