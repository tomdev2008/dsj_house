<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
		     <form id="searchFromId" class="form-inline">
		     		 <div class="form-group mr20">
	                	<span class="wenzi6">楼盘编号:</span>
	                    <input type="text" class="small-input form-control" placeholder="楼盘编号" name="code">
	                </div>
		    		<div class="form-group mr20">
		    		   	<span class="wenzi6">楼盘名称:</span>
	                    <input type="text" class="small-input form-control" placeholder="楼盘名称" name="name">
	                </div>
	          		<div class="form-group mr20">
						<span class="wenzi6">绑定状态:</span>
						<select class="form-control dsj-inline dsj-width-1" name="isBind">
							<option value="">请选择</option>
							<c:forEach items="${bindStatus}" var="item">
								<option value="${item.key}">${item.value}</option>
							</c:forEach>
					   </select>
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
					                 <th>序　号</th>
					                 <th>楼盘编号</th>
					                 <th>楼盘名称</th>
					                 <th>绑定状态</th>
					                 <th>IM绑定</th>
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
			 aoDataChange("searchFromId", aoData);
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
		     /*    "bStateSave": true, */
		      "bFilter": false,  //搜索框
		      /*  "sPaginationType" : "extStyle",   */
		      "bAutoWidth": true,//关闭后，表格将不会自动计算表格大小
		      "sAjaxDataProp":"data",
		      "bInfo": true,//页脚信息 
		      "iDisplayLength":10,
		      "sAjaxSource": _ctx+"/back/frame/im/directory/page/list",
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
	    	"mData" : null,
        	"aTargets" : [ 0 ]
	    	,
        	"mRender":function(data,type,row,full){
        		var startIndex = full.settings._iDisplayStart;  
        		return startIndex + full.row + 1;  
        	}
        }, {
	   		"mData" : "code",
	   		"aTargets" : [ 2 ]
	   	}, {
	   		"mData" : "name",
	   		"aTargets" : [ 4 ]
	   	}, {"bSortable": false,
	        "mRender":function(data,type,full){
	           	if(full.isBind==0){
	           		return "未绑定";
	           	}else if(full.isBind==1){
	           		return "已绑定";
	           	}
	        }
	    }, {"bSortable": false,
	        "mRender":function(data,type,full){
	           	var auth = "<shiro:hasPermission name='directoryIM:evelopers'><a class='dsj_btn btn btn-default' href='"+_ctx+"/back/frame/im/directory/directory_bind?id="+full.code+"'>设置</a></shiro:hasPermission>";
	           	return auth;
	        }
	    }]
	});
});
</script>