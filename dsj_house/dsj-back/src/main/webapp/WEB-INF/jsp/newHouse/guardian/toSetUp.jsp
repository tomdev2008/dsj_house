<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
                   <div class="white-head">
			     <form id="searchFromId" class="form-inline">
			    		 <input type="hidden" value="${id}" id="houseId">
		                <div class="form-group">
		                	<span class="wenzi6">楼盘维护人(经纪人):</span>
		                    <input type="text" maxlength="10" class="small-input form-control" placeholder="楼盘维护人" name="maintainName">
		                </div>
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		         </form> 
		      </div>
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				                 <th>经纪人ID</th>
				                 <th>经纪人名称</th>
				                 <th>所属公司</th>
				                 <th>电话</th>
				                 <th>操作</th>
				             </tr>
				             </thead>
				             <tbody>
				             </tbody>
		                </table>
		            </div>
		        </div>
		         <div class="modal-footer">
                            <button type="button" class="dsj_btn btn btn-default" id="close_btn" data-dismiss="modal">关闭</button>
                  </div>
<script>
var id="";
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
	      "sAjaxSource": _ctx+"/back/newHouse/guardian/findAgrentList",
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
	        		"mData" : "id",
	        		"aTargets" : [ 1 ]
	        	  },{
		        		"mData" : "name",
		        		"aTargets" : [ 2 ]
		          },{
		        		"mData" : "companyName",
		        		"aTargets" : [ 3 ]
		          },{
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 4 ]
		          },{"bSortable": false,
	                  "mRender":function(data,type,full){
	                	var checkOK = "<a class='btn' href='javascript:void(0);' onclick='checkOK("+full.id+")'>确定</a>";
		 	             return checkOK;
	                	 
	                  }
                }
	        	]
	    } );
});

function checkOK(param){
	id= param;
	setModalContent("确认设置为楼盘维护人吗?","checkOKConfirm");
}

$("body").on("click","#checkOKConfirm",function(){
	var houseId=$("#houseId").val();
	$("#popup_box").show();
	$.ajax({
		type:"post",
		url:_ctx+"/back/newHouse/guardian/checkOkHouse",
		data:{
			userId:id,
			houseId:houseId
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				$("#popup_box").hide();
				 setErrorContent(result.message);
			}else{
				location=_ctx+"/back/frame/newHouse/guardian/newHouse_guardian";
			}
		}
	})
  
});

</script>