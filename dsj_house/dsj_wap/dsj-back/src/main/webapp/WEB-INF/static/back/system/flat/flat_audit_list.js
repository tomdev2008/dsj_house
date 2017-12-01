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
	 	var type =$("#status").val()
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
	      "sAjaxSource": _ctx+"/back/system/flat/page/list",
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
	        		"mData" : "flatName",
	        		"aTargets" : [ 1 ]
	        	}, {
	        		"mData" : "username",
	        		"aTargets" : [ 2 ]
	        	},{
	        		"mData" : "contact",
	        		"aTargets" : [ 3 ]
	        	},{
	        		"mData" : "contactPhone",
	        		"aTargets" : [ 4 ]
	        	},{"bSortable": false,
	                "mRender":function(data,type,full){
	                	if(full.status==1){
	                		return "未审核";
	                	}else if(full.status==2){
	                		return "已通过";
	                	}else if(full.status==3){
	                		return "已拒绝";
	                	}
	               }
	           },{"bSortable": false,
	                  "mRender":function(data,type,full){
	                	  var audit = "<shiro:hasPermission name='flat:access'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='auditFlat("+full.id+")'>审核</a></shiro:hasPermission>";
	                	  if(full.status!=1){
	                		  return "已处理";
	                	  }else{
	                		  return audit+"";
	                	  }
	                  }
                }
	        	]
	    } );
	 
	 $("body").on("click","#accessOrrefuseFlat",function(){
			$.ajax({
				type:"post",
				url:_ctx+"/back/system/flat/flats_audit_"+_audit,
				data:{
					ids:id
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
	 
	//日期点击事件绑定
	 setLayDate();
});

var _audit = "";

function accessOrrefuseFlats(type){
	 var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要操作的账号");
		  return;
	  }
	  id=ids;
	  if(type==1){
		  _audit = "yes";
		  setModalContent("确认通过选中账号?","accessOrrefuseFlat");
	  }else{
		  _audit = "no";
		  setModalContent("确认拒绝选中账号?","accessOrrefuseFlat");
	  }
}

function auditFlat(param){
	location=_ctx+"/back/frame/system/flat/to_flat_audit?id="+param;
}
