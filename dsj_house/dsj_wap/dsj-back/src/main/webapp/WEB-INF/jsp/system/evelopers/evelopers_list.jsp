<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			开发商账号查询 
		</h1>
		<ol class="breadcrumb">
			<li>开发商账号管理</li>
			<li>开发商账号查询 </li>
		</ol>
		<div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation" class="active"><a href="javascript:void(0);">开发商账号</a></li>
					<li role="presentation"><a href="${ctx }/back/frame/system/evelopers/evelopers_wait">未认证开发商账号</a></li>
				</ul>
			</div>
	     <form id="searchFromId" class="form-inline">
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
						
	                </div>
	                <div class="form-group mr20">
							<span class="wenzi6">审核状态:</span>
							<select name="status"  class="form-control dsj-inline" style="width:100px;" id="status">
			                      <option value="">请选择</option>
			                      <option value="1">未审核</option>
			                      <option value="2">已通过</option>
			                      <option value="3">已驳回</option>
		                    </select>
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
				<div class="col-xs-12" style="padding-right:25px;">
			   		  <shiro:hasPermission name='evelopers:add'>
			   		  	<button class="dsj_btn btn dsj_btn_green" type="button" onclick="addEveloper()" style="width: 150px;">新建开发商账号</button>
			   		  </shiro:hasPermission>
			   		  <shiro:hasPermission name='evelopers:deletes'>
	                  	<button class="dsj_btn btn btn-default" type="button" onclick="delEveloper()">批量删除</button>
	                  </shiro:hasPermission>
                 
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
				                 <th>所在地</th>
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
			                	}else if(full.status==4){
			                		return "待提交";
			                	}
			               }
		           },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var edit = "<shiro:hasPermission name='evelopers:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editEvelopers("+full.id+")'>编辑</a></shiro:hasPermission>";
		                	  var resetPwd = "<shiro:hasPermission name='evelopers:reset'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='resetPwd("+full.id+")'>重置密码</a></shiro:hasPermission>";
		                	  if(full.status==1){
		                		  return "等待审核";
		                	  }else if(full.status==2){
		                		  return edit+""+resetPwd;
		                	  }else{
		                		  return edit;
		                	  }
		                  }
	                }
		        	]
		    } );
 })
 </script>
<script src="${ctx}/static/back/system/evelopers/evelopers_list.js"></script>