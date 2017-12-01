<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			开发商账号查询 
		</h1>
		<ol class="breadcrumb">
			<li>开发商账号管理</li>
			<li>未认证开发商账号 </li>
		</ol>
		<div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation"><a href="${ctx }/back/frame/system/evelopers">开发商账号</a></li>
					<li role="presentation" class="active"><a href="javascript:void(0);">未认证开发商账号</a></li>
				</ul>
			</div>
	     <form id="searchFromId"  class="form-inline">
					<div class="form-group mr20">
				        <span class="wenzi6">申请时间:</span>
				        <input value=""  class="small-input form-control laydate-icon" placeholder="开始时间" id="LAY_demorange_zs" name="startTime" style="width: 120px;">
				        <span class="wenzi2">~</span>
				         <input value=""  class="small-input form-control laydate-icon" placeholder="结束时间" id="LAY_demorange_ze" name="endTime" style="width: 120px;">
					</div>
					<div class="form-group mr20">
				        <span class="wenzi6">联系方式:</span>
				         <input type="text" class="small-input form-control" placeholder="联系方式" name="operationPhone"  style="width: 100px;">
				           <input type="hidden" class="small-input form-control" value="4" name="status">
		           </div>
                 <div class="btngroup row mt20 text-center">
	            	 	<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
					 <button class="dsj_btn btn btn-default" type="reset">重置</button>
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
		             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
		                 <th>账号名称</th>
		                 <th>注册时间</th>
		                 <th>联系方式</th>
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
		      "sAjaxSource": _ctx+"/back/system/evelopers/page/list",
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
		            	"aTargets" : [ 0 ],
		            	"mRender":function(data,type,full){
		            		 return  "<input name='checkitem' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            	}
	                },{
		        		"mData" : "username",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "createTime",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "operationPhone",
		        		"aTargets" : [ 7 ]
		        	}
		        	]
		    } );
		 
		//点击查询重新加载
			$("#search_btn").click(function(){
			 	$("#dataTable").DataTable().ajax.reload();
			 	$("#checkall").prop("checked",false);
			});
 })
 </script>
