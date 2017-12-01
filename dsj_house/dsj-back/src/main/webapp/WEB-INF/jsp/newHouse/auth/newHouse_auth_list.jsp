<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
		<div class="white-head">
			<div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation" class="active"><a href="${ctx }/back/frame/newHouse/auth">待审核</a></li>
					<li role="presentation"><a href="${ctx }/back/frame/newHouse/auth/newHouse_auth_history_list">已审核</a></li>
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
					        <input value=""  class="small-input form-control laydate-icon"  id="LAY_demorange_ss_one" placeholder="开始时间" name="startCreateTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ee_one"  placeholder="结束时间" name="endCreateTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group mr20">
					        <span class="wenzi6">提交时间:</span>
					        <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ss_two" placeholder="开始时间" name="startOpenTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ee_two" placeholder="结束时间" name="endOpenTime" style="width: 120px;">
	                	</div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
				<div class="row">
					<div class="col-xs-12">
					<shiro:hasPermission name='newHouseAuth:yes'>
						<button class="dsj_btn btn btn-default" type="button" onclick="authNewHouses(3)">批量通过</button>
					</shiro:hasPermission>
					<shiro:hasPermission name='newHouseAuth:no'>
				          <button class="dsj_btn btn btn-default" type="button" onclick="authNewHouses(4)">批量驳回</button>
				    </shiro:hasPermission>
					</div>
			        <!--表格 S-->
			        <div class="slb_table H_slb_table col-xs-12">
			            <div class="table-responsive">
			                <table class="table table-bordered" id="dataTable">
			                     <thead>
					             <tr>
					             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
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
					                 <th>归属人</th>
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
 <div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" style="width: 560px;">
                         <div class="modal-header">
	           <div class="row">
	          	 <h4 class="modal-title" id="authTitle"></h4>
		    	</div>
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				 <form id="authForm" class="col-xs-8 col-xs-offset-2">
										 <div class="form-group">
									        <div class="col-sm-6">
									          <textarea rows="4" cols="35" name="content" id="content"  maxlength="30"></textarea>
									        </div>
									    </div>
									</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				        <button class="btn btn-primary" type="button" onclick="saveAuth()">提交</button>
				        <button class="btn btn-default" data-dismiss="modal">取消</button>
				    </div>
                       
                   </div>
               </div>
</div>
<script type="text/javascript">
 $(function(){
	 setLayDateOne();
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
		      "sAjaxSource": _ctx+"/back/frame/newHouse/auth/page/list",
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
		            		 return  "<input name='checkitem' code='"+full.code+"' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            	}
		              }, {
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "commitTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "code",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "name",
		        		"aTargets" : [ 3 ]
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
		            },{
		        		"mData" : "realname",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var auth = "<shiro:hasPermission name='newHouseAuth:auth'><a class='dsj_btn btn btn-default' href='"+_ctx+"/back/frame/newHouse/auth/newHouse_auth?id="+full.id+"'>审核</a></shiro:hasPermission>";
		                	return auth;
		                	  
		                  }
	                }
		        	]
		    } );
	});
 </script>
<script src="${ctx}/static/back/newHouse/auth/newHouse_auth_list.js"></script>