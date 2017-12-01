<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
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
				<span class="wenzi6">等级:</span>
				<select class="form-control dsj-inline dsj-width-1" name="gradeScore">
					<option value="">请选择</option>
					<c:forEach items="${gradeList}" var="item">
						<option value="${item.minScore}-${item.maxScore}">${item.name}</option>
					</c:forEach>
	   			</select>
            </div>
            <div class="form-group">
            	<span class="wenzi6">累计分值:</span>
                <input type="text" class="small-input form-control" style="width: 80px;" name="minTotalScore">
             		至
                <input type="text" class="small-input form-control" style="width: 80px;" name="maxTotalScore">
            </div>
			<div class="form-group">
         		<span class="wenzi6">业务分:</span>
                <input type="text" class="small-input form-control" style="width: 80px;" name="minBusinessScore">
             		至
                <input type="text" class="small-input form-control" style="width: 80px;" name="maxBusinessScore">
            </div>
            <div class="form-group">
            	<span class="wenzi6">基础分:</span>
                <input type="text" class="small-input form-control" style="width: 80px;" name="minBaseScore">
             		至
                <input type="text" class="small-input form-control" style="width: 80px;" name="maxBaseScore">
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
				                <th>等级</th>
				                <th>累计分值</th>
				                <th>基础分</th>
				                <th>业务分</th>
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
		      "sAjaxSource": _ctx + "/back/frame/newHouse/agentInfos/evaluateList",
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
        		"mData" : "gradeName",
        		"aTargets" : [ 3 ]
        	},{
        		"mData" : "totalScore",
        		"aTargets" : [ 4 ]
        	},{
        		"mData" : "baseScore",
        		"aTargets" : [ 5 ]
        	},{
        		"mData" : "businessScore",
        		"aTargets" : [ 6 ]
        	}]
	    });
	});
 </script>