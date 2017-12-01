var id = "";
$(function(){
	
	setLayDateTwo();
	
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
	      "sAjaxSource": _ctx+"/back/oldHouseRequire/page/list",
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
	            	"aTargets" : [ 0 ],
	            	"mRender":function(data,type,full){
	            		 return  "<input name='checkitem' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
	            	}
	            }, {
	        		"mData" : "createTime",
	        		"aTargets" : [ 1 ]
	        	}, {
	        		"mData" : "requireUseranme",
	        		"aTargets" : [ 2 ]
	        	},{
	        		"mData" : "requirePhone",
	        		"aTargets" : [ 3 ]
	        	},{
	        		"mData" : "tradingArea",
	        		"aTargets" : [ 4 ]
	        	},{
	        		"mData" : "buildingName",
	        		"aTargets" : [ 5 ]
	        	},{
	        		"mData" : "houseType",
	        		"aTargets" : [ 6 ]
	        	},{"bSortable": false,
	                  "mRender":function(data,type,full){
	                	return full.budgetMin + " ~ " + full.budgetMax;
	                  }
                }
	        	/*,{"bSortable": false,
	                  "mRender":function(data,type,full){
	                	  var edit = "<a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='dealRequire("+full.id+")'>处理</a>";
	                	  if(full.status==1){
	                		  return "已处理";
	                	  }else{
	                		  return edit;
	                	  }
	                  }
                }*/
	        	]
	    } );
	 
	$("body").on("click","#dealRequiresModalConfirm",function(){
		var _ids = ids;
		$.ajax({
			type:"post",
			url:_ctx+"/back/oldHouseRequire/dealRequires",
			data:{
				ids:_ids
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
var ids ;
function dealRequire(id){
	ids = new Array();
	ids.push(id);
	setModalContent("确认改为已处理状态吗?","dealRequiresModalConfirm");
}

function deal_requires(){
	ids = new Array();
	$("input[name=checkitem]:checked").each(function(){
		ids.push($(this).val());
	});
	if(ids.length == 0){
		setErrorContent("请选择要处理的条项");
	}else{
		setModalContent("确认删除选中账号?","dealRequiresModalConfirm");
	}
}
