<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			用户意见反馈查询
		</h1>
		<ol class="breadcrumb">
			<li>用户意见反馈管理</li>
			<li>用户意见反馈查询 </li>
		</ol>
	     <form id="searchFromId" class="form-inline">
			    	 <div class="form-group mr20">
					        <span class="wenzi6">反馈时间:</span>
					        <input value=""  class="small-input form-control laydate-icon" placeholder="开始时间" id="LAY_demorange_ss_one" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" placeholder="结束时间" id="LAY_demorange_ee_one" name="endTime" style="width: 120px;">
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
				             	 <th>序号</th>
				                 <th>反馈时间</th>
				                 <th>反馈用户昵称</th>
				                 <th>手机号</th>
				                 <th>反馈内容</th>
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
	 setLayDateOne();
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
		      "sAjaxSource": _ctx+"/back/system/feedback/pageList",
		      "language": {
		      "lengthMenu": "",
		      "zeroRecords": "没有找到记录",
		      "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
		      "infoEmpty": "无记录",
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
		        		"aTargets" : [ 0 ]
		        	}, {
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	},{
		        		"mData" : "userName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"aTargets" : [ 4 ],
		        		"mRender":function(data,type,full){
		        			if(full.content.length>20){
		        				return  "<a href='javascript:void(0);' onclick='checkFeedback("+full.id+")'>"+full.content.substring(0,20)+"</a>";
		        			}else{
		        				return  "<a href='javascript:void(0);' onclick='checkFeedback("+full.id+")'>"+full.content+"</a>";
		        			}
		            		 
		            	}
		        	}]
		    } );
 })
 
  function checkFeedback(id){
	 openCommonModal(_ctx+"/back/system/feedback/checkFeedback?id="+id,800)
 }
 </script>
