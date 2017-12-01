<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			开发商账号查询 
		</h1>
		<ol class="breadcrumb">
			<li>开发商账号管理</li>
			<li>开发商账号审核 </li>
		</ol>
	     <form id="searchFromId"  class="form-inline">
					<div class="form-group mr20">
				        <span class="wenzi6">申请时间:</span>
				        <input value=""  class="small-input form-control laydate-icon" placeholder="开始时间" id="LAY_demorange_zs" name="startTime" style="width: 120px;">
				        <span class="wenzi2">~</span>
				         <input value=""  class="small-input form-control laydate-icon" placeholder="结束时间" id="LAY_demorange_ze" name="endTime" style="width: 120px;">
					</div>
					<div class="form-group mr20">
				        <span class="wenzi6">开发商名称:</span>
				         <input type="text" class="small-input form-control" placeholder="开发商名称" name="name"  style="width: 100px;">
					</div>
					<div class="form-group mr20">
				        <span class="wenzi6">楼盘项目:</span>
				         <input type="text" class="small-input form-control" placeholder="楼盘项目" name="loupanName" style="width: 100px;">
				         <input type="hidden" class="small-input form-control" value="1" name="status">
					</div>
					<div class="form-group mr20">
						<span class="wenzi6">省:</span>
						<select  id="areaOneId" class="form-control dsj-inline" style="width:100px;" name="areaOneId" onchange="getTwoArea()">
		                      <option value="">请选择</option>
		                       <c:forEach items="${firstAreaList }" var="area">
		                       	 	<c:if test="${area.areaCode!=1 }">
		                        	 	<option value="${area.areaCode }">${area.name }</option>
		                        	 </c:if>
		                        </c:forEach>
	                    </select>
					</div>
					<div class="form-group mr20">
						<span class="wenzi6">市:</span>
						 <select  id="areaTwoId" class="form-control dsj-inline" style="width:100px;" name="areaTwoId">
		                      <option value="">请选择</option>
	                    </select>
					</div>
					
                 <div class="btngroup row mt20 text-center">
	            	 	<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
					 <button class="dsj_btn btn btn-default" type="reset">重置</button>
	                </div>
         </form> 
        </div>
      <div class="white-content">
		<div class="row">
                 <shiro:hasPermission name='evelopers:access'>
                 		<button class="dsj_btn btn btn-default" id="accessBtn" type="button" onclick="accessOrrefuseEveloper(1)">批量通过</button>
                 </shiro:hasPermission>
                 <shiro:hasPermission name='evelopers:return'>
                 		<button class="dsj_btn btn btn-default" id="refuseBtn"  type="button" onclick="accessOrrefuseEveloper(2)">批量驳回</button>
			 </shiro:hasPermission>
		</div>
		<!--表格 S-->
        <div class="slb_table H_slb_table">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable">
                     <thead>
		             <tr>
		             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
		                 <th>申请时间</th>
		                 <th>开发商名称</th>
		                 <th>账号名称</th>
		                 <th>所在地格</th>
		                 <th>楼盘项目</th>
		                 <th>联系人姓名</th>
		                 <th>联系人电话</th>
		                 <th>审核状态</th>
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
		      "sAjaxSource": _ctx+"/back/system/evelopers/page/list",
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
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "name",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "username",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "fullName",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "loupanName",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "operationName",
		        		"aTargets" : [ 5 ]
		        	},{
		        		"mData" : "operationPhone",
		        		"aTargets" : [ 7 ]
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
		                	var auth = "<shiro:hasPermission name='evelopers:auth'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='authEvelopers("+full.id+")'>审核</a></shiro:hasPermission>";
		                	return auth;
		                  }
	                }
		        	]
		    } );
 })
 </script>
<script src="${ctx}/static/back/system/evelopers/evelopers_list.js"></script>