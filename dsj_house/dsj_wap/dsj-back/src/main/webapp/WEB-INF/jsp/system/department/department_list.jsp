<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<script type="text/javascript" src="${ctx}/static/front/js/dataTables.treeGrid.js"></script>


<div class="col-xs-12">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
						<!-- PAGE HEADER -->
		系统管理 
		</h1>
						<!-- breadcrumb -->
		<ol class="breadcrumb">
			<li>部门管理</li>
			<li>部门列表</li>
		</ol>
						
	</div>
						
	<div class="white-content">
		<div class="row">
				<button class="dsj_btn btn dsj_btn_green" onclick="toAdd()" >新增部门</button>
				
		</div>
		 <div class="slb_table H_slb_table">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable_tree">
                    
                </table>
            </div>
        </div>			
						
	</div>
</div>
<script type="text/javascript">
var table;
$(function(){
    
	var columns = [
	{
	    title: '',
	    target: 0,
	    className: 'treegrid-control',
	    data: function (item) {
	        if (item.children) {
	            return '<span>+</span>';
	        }
	        return '';
	    }
	},
	{
	    title: '部门名称',
	    target: 1,
	    data: function (item) {
	        return item.deptName;
	    }
	},
	{
	    title: '部门描述',
	    target: 2,
	    data: function (item) {
	        return item.deptMess;
	    }
	},
	{
	    title: '操作',
	    target: 3,
	    data: function (item) {
        	var edit = "<shiro:hasPermission name='department:edit'>"
    	    	+"<a class='dsj_btn btn btn-default'  href='javascript:void(0);' onclick='edit("+item.id+")'>编辑</a>"
    	    	+"</shiro:hasPermission>";
    	   
          	  var del = "<shiro:hasPermission name='department:delete'>"
      	    	+"<a class='dsj_btn btn btn-default'  href='javascript:void(0);' onclick='del("+item.id+")'>删除</a>"
      	    	+"</shiro:hasPermission>";
	        return del+edit;
	    }
	}
	];
 
	
	table=$('#dataTable_tree').DataTable({
		"select": 'single',
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
	    'columns': columns,
	    'sAjaxSource':_ctx+ '/back/system/department/page/list',
	    "fnServerData":function(sSource, aoData, fnCallback) { 
	    	   
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
	    'treeGrid': {
	        'left': 10,
	        'expandIcon': '<span><a href="javascript:void(0);">+</a></span>',
	        'collapseIcon': '<span><a href="javascript:void(0);">-</a></span>'
	    }
	});
	
});

</script>
<script src="${ctx}/static/back/system/department/department_list.js"></script>