<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			员工账号查询
		</h1>
		<ol class="breadcrumb">
			<li>员工账号管理</li>
			<li>员工账号查询 </li>
		</ol>
			     <form id="searchFromId" class="form-inline">
			    		 <div class="form-group">
			    		   <span class="wenzi6">员工编号:</span>
		                    <input type="text" class="small-input form-control" placeholder="员工编号" name="empNum">
		                </div>
		          		<div class="form-group">
							<span class="wenzi6">真实姓名:</span>
		                    <input type="text" class="small-input form-control" maxlength="20" placeholder="真实姓名" name="realName">
		                </div>
		                <div class="form-group">
		                	<span class="wenzi6">手机号:</span>
		                    <input type="text" class="small-input form-control" placeholder="手机号" name="tellPhone">
		                </div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
			<div class="row">
			   		  <shiro:hasPermission name='employee:add'><button class="dsj_btn btn dsj_btn_green" type="button" onclick="addEmployee()">新建账号</button></shiro:hasPermission>
			          <shiro:hasPermission name='employee:delete'><button class="dsj_btn btn btn-default" type="button" onclick="delEmployee()">删除用户</button></shiro:hasPermission>
			</div>         
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				                 <th>员工编号</th>
				                 <th>真实姓名</th>
				                 <th>联系方式</th>
		
				                 <th>默认角色</th>
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
		 	var type =$("#status").val()
		 	if(type==1){
				$("#accessBtn").show()
				$("#refuseBtn").show()
			}else{
				$("#accessBtn").hide()
				$("#refuseBtn").hide()
			}
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
		      "sAjaxSource": _ctx+"/back/frame/system/employee/dataList",
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
		            		 return  "<input name='checkitem' value='"+full.userId+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            	}
		              }, {
		        		"mData" : "empNum",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "realName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "roleName",
		        		"aTargets" : [ 4 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var edit = "<shiro:hasPermission name='employee:detail'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editEmployee("+full.id+")'>编辑</a></shiro:hasPermission>";
		                	  var resetPwd = "<shiro:hasPermission name='employee:reset'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='resetPwd("+full.userId+")'>重置密码</a></shiro:hasPermission>";	                	  
		                	return edit+resetPwd;
		                	  
		                  }
	                }
		        	]
		    } );
	});
 </script>       
<script src="${ctx}/static/back/system/employee/employee_list.js"></script>