<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
		    <div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation"><a href="${ctx }/back/frame/newHouse/auth">待审核</a></li>
					<li role="presentation" class="active"><a href="${ctx }/back/frame/newHouse/auth/newHouse_auth_history_list">已审核</a></li>
				</ul>
			</div>
			     <form id="searchFromId" class="form-inline">
			     		 <div class="form-group mr20">
		                	<span class="wenzi6">楼盘编码:</span>
		                    <input type="text" class="small-input form-control" placeholder="楼盘编码" name="code">
		                </div>
			    		 <div class="form-group mr20">
			    		   <span class="wenzi6">楼盘名称:</span>
		                    <input type="text" class="small-input form-control" placeholder="楼盘名称" name="name">
		                </div>
		          		<div class="form-group mr20">
							<span class="wenzi6">楼盘地址:</span>
		                    <input type="text" class="small-input form-control" placeholder="楼盘地址" name="address">
		                </div>
		                <div class="form-group mr20">
		                	<span class="wenzi6">归属人:</span>
		                    <input type="text" class="small-input form-control" placeholder="归属人" name="realname">
		                </div>
		                <div class="form-group mr20">
					        <span class="wenzi6">创建时间:</span>
					        <input value=""  class="small-input form-control layDateClass laydate-icon" placeholder="开始时间" name="startCreateTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control layDateClass laydate-icon" placeholder="结束时间" name="endCreateTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group mr20">
					        <span class="wenzi6">申请时间:</span>
					        <input value=""  class="small-input form-control layDateClass laydate-icon" placeholder="开始时间" name="startOpenTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control layDateClass laydate-icon" placeholder="结束时间" name="endOpenTime" style="width: 120px;">
	                	</div>
		                 <div class="form-group mr20">
							<span class="wenzi6">审批状态:</span>
							<select class="form-control dsj-inline dsj-width-1" name="authStatus">
												<option value="">请选择</option>
												<option value="2">审核通过</option>
												<option value="3">审核拒绝</option>
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
					                 <th>创建时间</th>
					                 <th>提交时间</th>
					                 <th>楼盘编号</th>
					                 <th>楼盘名称</th>
					                 <th>楼盘地址</th>
					                 <th>物业类型</th>
					                 <th>产权年限(年)</th>
					                 <th>单价(元/平米)</th>
					                 <th>最新开盘时间</th>
					                 <th>销售状态</th>
					                 <th>审核状态</th>
					                 <th>审核人</th>
					                 <th>审核意见</th>
					                 <th>审核时间</th>
					                 <th>归属人</th>
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
		      "sAjaxSource": _ctx+"/back/frame/newHouse/auth/page/history_list",
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
		            
		            "aoColumns" : [ {
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "commitTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "code",
		        		"aTargets" : [ 2 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	return "<a href="+_ctx+"/back/frame/newHouse/auth/newHouse_history_auth?id="+full.id+">"+full.name+"</a>";
			                }
		            },{
		        		"mData" : "address",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "wyTypeName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "propertyRightName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "referencePrice",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.openTime!=null){
			                		return full.openTime;
			                	}else{
			                		return full.openDate;
			                	}
		                  }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.status==1){
			                		return "待售";
			                	}else if(full.status==2){
			                		return "在售";
			                	} else if(full.status==3){
			                		return "售完";
			                	}               	  
			                }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	  	if(full.authStatus==1){
			                		return "待提交";
			                	}else if(full.authStatus==2){
			                		return "待审核";
			                	}else if(full.authStatus==3){
			                		return "通过";
			                	} else if(full.authStatus==4){
			                		return "驳回";
			                	}               	  
			                }
		            },{
		        		"mData" : "authRealName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "content",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "authTime",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "realname",
		        		"aTargets" : [ 3 ]
		        	}
		        	]
		    } );
	});
 </script>
