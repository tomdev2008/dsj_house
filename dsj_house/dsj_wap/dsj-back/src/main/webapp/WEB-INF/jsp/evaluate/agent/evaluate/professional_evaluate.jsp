<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<div class="row">
			<ul class="nav nav-tabs button_tab" role="tablist">
				<li role="presentation"><a
					href="${ctx }/back/frame/evaluate/agent/agentEvaluate">服务态度</a></li>
				<li role="presentation" class="active"><a
					href="${ctx }/back/frame/evaluate/agent/agentEvaluate/professional">专业水平</a></li>
			</ul>
		</div>
    	<form id="searchFromId" class="form-inline">
   			<div class="form-group">
            	<span class="wenzi6">经纪人姓名:</span>
                <input type="text" class="small-input form-control" placeholder="经纪人姓名" name="agentName">
            </div>
  		 	<div class="form-group">
  		   		<span class="wenzi6">经纪人ID:</span>
                <input type="text" class="small-input form-control" placeholder="经纪人ID" name="agentId">
            </div>
            <div class="form-group">
            	<span class="wenzi6">好评率:</span>
                <input type="text" class="small-input form-control" style="width: 40px;" name="minHighRate">%
             		至
                <input type="text" class="small-input form-control" style="width: 40px;" name="maxHighRate">%
            </div>
			<div class="form-group">
         		<span class="wenzi6">中评率:</span>
                <input type="text" class="small-input form-control" style="width: 40px;" name="minMidRate">%
             		至
                <input type="text" class="small-input form-control" style="width: 40px;" name="maxMidRate">%
            </div>
            <div class="form-group">
            	<span class="wenzi6">差评率分:</span>
                <input type="text" class="small-input form-control" style="width: 40px;" name="minDadRate">%
             		至
                <input type="text" class="small-input form-control" style="width: 40px;" name="maxBadRate">%
            </div>
         	<div class="btngroup row mt20 text-center">
                 <button class="btn btn-primary" id="search_btn" type="button">查询</button>
                 <button class="btn btn-default" type="reset">重置</button>
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
				                <th>城市</th>
				                <th>经纪人ID</th>
				                <th>经纪人姓名</th>
				                <th>服务类别</th>
				                <th>好评数</th>
				                <th>中评数</th>
				                <th>差评数</th>
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
	 	$("#checkall").prop("checked",false);
	});
	
	 $('#dataTable').DataTable({
			  "bSort": false,		//取消默认排序查询,否则复选框一列会出现小箭头
			  "bProcessing": false,	//隐藏加载提示,自行处理
		      "serverSide": true,
		      "bProcessing": true,
		      "bLengthChange": true,
		      /*  "bStateSave": true, */
		      "bFilter": false,  //搜索框
		      /*  "sPaginationType" : "extStyle",   */
		      "bAutoWidth": true,//关闭后，表格将不会自动计算表格大小
		      "sAjaxDataProp":"data",
		      "bInfo": true,//页脚信息 
		      "iDisplayLength":10,
		      "sAjaxSource": _ctx + "/back/frame/newHouse/agentEvaluate/evaluateList",
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
	           });
           },
	        
           "aoColumns" : [{
        		"mData" : "cityName",
        		"aTargets" : [ 0 ]
        	}, {
        		"mData" : "agentId",
        		"aTargets" : [ 1 ]
        	},{
        		"mData" : "agentName",
        		"aTargets" : [ 2 ]
        	},{
        		"mData" : "paragraph",
        		"aTargets" : [ 3 ]
        	},{
        		"mData" : "highCount",
        		"aTargets" : [ 4 ]
        	},{
        		"mData" : "midCount",
        		"aTargets" : [ 5 ]
        	},{
        		"mData" : "badCount",
        		"aTargets" : [ 6 ]
        	},{
        		"mData" : "totalCount",
        		"aTargets" : [ 7 ]
        	},{
        		"mData" : "highRate",
        		"aTargets" : [ 8 ]
        	},{
        		"mData" : "midRate",
        		"aTargets" : [ 9 ]
        	},{
        		"mData" : "badRate",
        		"aTargets" : [ 10 ]
        	}]
	    });
	});
 </script>