<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
        <h1 class="page-title txt-color-blueDark">
			角色管理
		</h1>
		<ol class="breadcrumb">
			<li>系统管理</li>
			<li>角色管理</li>
		</ol>
	      <form id="searchFromId" >
          		<div>
                    <label>角色名称：</label>
                    <input type="text" placeholder="" name="name">
                </div>
            	  <div class="btngroup row mt20 text-center">
	            	 	<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
					 <button class="dsj_btn btn btn-default" type="reset">重置</button>
	              </div>
         </form> 
  </div>
         <shiro:hasPermission name='role:add'><button class="btn btn-primary" type="button" onclick="openCommonModal('${ctx}/back/role/toRoleAdd',500)">添加角色</button></shiro:hasPermission>
        <!--表格 S-->           
        <div class="slb_table H_slb_table">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable">
                     <thead>
		             <tr>
		             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/>全选</th>
		                 <th>角色名称</th>
		                 <th>操作</th>
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
<script>
var id = "";
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
	      "sAjaxSource": _ctx+"/back/role/findAllRole",
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
	              }, {
	        		"mData" : "name",
	        		"aTargets" : [ 1 ]
	        	  },{"bSortable": false,
	                  "mRender":function(data,type,full){
	                	var delRole = "<shiro:hasPermission name='role:delete'><a class='btn' href='javascript:void(0);' onclick='delRole("+full.id+")'>删除</a></shiro:hasPermission>";
	                	  var functionPower = "<shiro:hasPermission name='role:edit'><a class='btn' href='javascript:void(0);' onclick='functionPower("+full.id+")'>功能权限</a></shiro:hasPermission>";
		 	             return delRole+""+functionPower;
	                	 
	                  }
                }
	        	]
	    } );
	 
	 $("body").on("click","#delRoleConfirm",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/role/delRole",
				data:{
					id:id
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
});

function delRole(param){
	id= param;
	setModalContent("确认删除角色吗?","delRoleConfirm");
}


function functionPower(param){
	location=_ctx+"/back/frame/function/findAllFunction?id="+param;
}

function addRole(){
	$("#roleFrom").verify();
	$("#add").attr("disabled", true);
	 $("#roleFrom").validate(function (result) {
		  	if(result){
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#roleFrom").serialize(),
		  			dataType:"json",
		  			url:_ctx+"/back/role/role_add",
		  			success:function(resultVo){
		  				if(resultVo.status!=200){
		  					$("#add").removeAttr("disabled");
		  					 setErrorContent(resultVo.message);
		  				}else{
		  					$("#close_btn").click();
		  					$("#search_btn").click();
		  				}
		  			}
		  		})
		  	}else{
		  		$("#add").removeAttr("disabled");
		  	}
	 })
}


</script>
