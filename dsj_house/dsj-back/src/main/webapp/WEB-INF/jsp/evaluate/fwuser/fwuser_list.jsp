<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			权证商家评价查询 
		</h1>
		<ol class="breadcrumb">
			<li>权证商家评价管理</li>
			<li>权证商家评价查询 </li>
		</ol>
	     <form id="searchFromId" class="form-inline">
	                <div class="form-group mr20">
					        <span class="wenzi6">商家名称:</span>
					         <input type="text" class="small-input form-control" placeholder="商家名称" name="companyName"  style="width: 100px;">
	                </div>
	                 <div class="form-group mr20">
					         
					        <span class="wenzi6">代办人姓名:</span>
					         <input type="text" class="small-input form-control" placeholder="代办人姓名" name="name" style="width: 100px;">
						
	                </div>
	                <div class="form-group mr20">
					         
					        <span class="wenzi6">代办人手机号:</span>
					         <input type="text" class="small-input form-control" placeholder="代办人手机号" name="tellPhone" style="width: 100px;">
						
	                </div>
	                <div class="form-group mr20">
					        <span class="wenzi6">好评率:</span>
					        <input value=""  class="small-input form-control " name="minHaoPing" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control " name="maxHaoPing" style="width: 120px;">
	                </div>
	                <div class="form-group mr20">
					        <span class="wenzi6">中评率:</span>
					        <input value=""  class="small-input form-control " name="minZhongPing" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control " name="maxZhongPing" style="width: 120px;">
	                </div>
	                <div class="form-group mr20">
					        <span class="wenzi6">差评率:</span>
					        <input value=""  class="small-input form-control " name="minChaPing" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control " name="maxChaPing" style="width: 120px;">
	                </div>
                 <div class="btngroup row mt20 text-center">
	            	 	<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
					 <button class="dsj_btn btn btn-default" type="reset">重置</button>
	                </div>
         </form> 
        </div>
      <div class="white-content">
		<div class="row">
				<div class="col-xs-12" style="padding-right:25px;"> 
		 <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				                 <th>商家名称</th>
				                 <th>代办人姓名</th>
				                 <th>手机号</th>
				                 <th>业务范围</th>
				                 <th>服务区域</th>
				                 <th>接单数</th>
				                 <th>获评次数</th>
				                 <th>好评率</th>
				                 <th>中评率</th>
				                 <th>差评率</th>
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
		      "sAjaxSource": _ctx+"/back/evaluate/fwuser/pageList",
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
		        		"mData" : "companyName",
		        		"aTargets" : [ 0 ]
		        	},{
		        		"mData" : "name",
		        		"aTargets" : [ 1 ]
		        	},{
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "businessName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "areaName",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "deal",
		        		"aTargets" : [ 5 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.count>0){
			                		return full.count;
			                	}else{
			                		return 0;
			                	}
			               }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.haoPingLv != null){
			                		return full.haoPingLv;
			                	}else{
			                		return 0;
			                	}
			               }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.zhongPingLv != null){
			                		return full.zhongPingLv;
			                	}else{
			                		return 0;
			                	}
			               }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.chaPingLv != null){
			                		return full.chaPingLv;
			                	}else{
			                		return 0;
			                	}
			               }
		            }
		        	]
		    } );
 })
 </script>
