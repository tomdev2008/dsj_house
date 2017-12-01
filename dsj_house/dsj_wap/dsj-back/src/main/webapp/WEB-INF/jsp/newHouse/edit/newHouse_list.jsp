<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
			     <form id="searchFromId" class="form-inline">
			     		 <div class="form-group mr20">
		                	<span class="wenzi6">楼盘编码:</span>
		                    <input type="text" class="small-input form-control" placeholder="楼盘编码" name="code" maxlength="30">
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
		               		 <span class="wenzi6">单价:</span>
		                    <input type="text" class="small-input form-control" style="width: 80px;" name="referencePriceMin">
		                		~
		                    <input type="text" class="small-input form-control" style="width: 80px;" name="referencePriceMax">
		                  	  元/平米
		                </div>
		                <div class="form-group mr20">
					        <span class="wenzi6">创建时间:</span>
					        <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ss_one" placeholder="开始时间" name="startCreateTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ee_one" placeholder="结束时间" name="endCreateTime" style="width: 120px;">
	                	</div>
	                	<div class="form-group mr20">
					        <span class="wenzi6">最新开盘时间:</span>
					        <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ss_two" placeholder="开始时间" name="startOpenTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ee_two" placeholder="结束时间" name="endOpenTime" style="width: 120px;">
	                	</div><br/>
		                <div class="form-group mr20">
							<span class="wenzi6">物业类型:</span>
							<select class="form-control dsj-inline dsj-width-1" name="wyType">
												<option value="">全部</option>
												<c:forEach items="${wyTypeMap}" var="item">
													<option value="${item.key}">${item.value}</option>
												</c:forEach>
						   </select>
		                </div>
		                <div class="form-group mr20">
							<span class="wenzi6">产权年限:</span>
							<select class="form-control dsj-inline dsj-width-1" name="propertyRight">
												<option value="">全部</option>
												<c:forEach items="${propertyRightMap}" var="item">
													<option value="${item.key}">${item.value}</option>
												</c:forEach>
						   </select>
		                </div>
		                <div class="form-group mr20">
							<span class="wenzi6">审批状态:</span>
							<select class="form-control dsj-inline dsj-width-1" name="authStatus">
												<option value="">全部</option>
												<option value="1">待提交</option>
												<option value="2">待审核</option>
												<option value="3">已通过</option>
												<option value="4">已驳回</option>
						   </select>
		                </div>
		                <div class="form-group mr20">
							<span class="wenzi6">楼盘状态:</span>
							<select class="form-control dsj-inline dsj-width-1" name="isTrue">
												<option value="">全部</option>
												<option value="1">未上架</option>
												<option value="2">已上架</option>
												<option value="3">已下架</option>
						   </select>
		                </div>
		                <div class="form-group mr20">
							<span class="wenzi6">销售状态:</span>
							<select class="form-control dsj-inline dsj-width-1" name="status">
												<option value="">请选择</option>
												<option value="1">待售</option>
												<option value="2">在售</option>
												<option value="3">售完</option>
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
				<div class="col-xs-12">
				<shiro:hasPermission name='newHouse:add'>
			   		  <button class="dsj_btn btn dsj_btn_green" type="button" onclick="addHouse()">新建楼盘</button>
			   	</shiro:hasPermission>
			   	<shiro:hasPermission name='newHouse:deletes'>
			          <button class="dsj_btn btn btn-default" type="button" onclick="delNewHouses()">批量删除</button>
			    </shiro:hasPermission>
			    <shiro:hasPermission name='newHouse:xiaJias'>
			          <button class="dsj_btn btn btn-default" type="button" onclick="downNewHouses()">批量下架</button>
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
					                 <th>楼盘编号</th>
					                 <th>楼盘名称</th>
					                 <th>楼盘地址</th>
					                 <th>物业类型</th>
					                 <th>产权年限(年)</th>
					                 <th>单价(元/平米)</th>
					                 <th>最新开盘时间</th>
					                 <th>销售状态</th>
					                 <th>审批状态</th>
					                 <th>楼盘状态</th>
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
 <div style="display:none;text-align:center;" class="modal fade" id="sortModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" style="width: 560px;">
                         <div class="modal-header">
	           <div class="row">
	          	 <h4 class="modal-title">楼盘排序</h4>
		    	</div>
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				 <form id="orderForm" class="col-xs-8 col-xs-offset-2">
										 <div class="form-group">
									        <div>
									           <input type="text" class="form-control" placeholder="排序值(1-20)" id="sortId" data-validate="required,orderNum" style="width: 150px;">
									           <input type="hidden" id="newHouseId">
									        </div>
									    </div>
									</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				        <button class="btn btn-primary" type="button" onclick="saveSort()">提交</button>
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
		      "sAjaxSource": _ctx+"/back/frame/newHouse/edit/page/list",
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
		            		 return  "<input name='checkitem' value='"+full.id+"' isPutSale='"+full.isTure+"' authStatus='"+full.authStatus+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            	}
		              }, {
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "code",
		        		"aTargets" : [ 2 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	return "<a href="+_ctx+"/back/frame/newHouse/edit/newHouse_check?id="+full.id+">"+full.name+"</a>";
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
			                		return "已通过";
			                	} else if(full.authStatus==4){
			                		return "<span title='"+full.content+"'>已驳回</span>";
			                	}               	  
			                }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.isTure==1){
			                		return "未上架";
			                	}else if(full.isTure==2){
			                		return "已上架";
			                	}else if(full.isTure==3){
			                		return "已下架";
			                	}else if(full.isTure==4){
			                		return "已删除";
			                	}                	  
			                	
			                	  
			                  }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var edit = "<shiro:hasPermission name='newHouse:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editNewHouse("+full.id+")'>编辑</a></shiro:hasPermission>";
		                	var order = "<shiro:hasPermission name='newHouse:sort'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='sort("+full.id+")'>排序</a></shiro:hasPermission>";
		                	if(full.authStatus==1){
		                		return edit;
		                	}else if(full.authStatus==2){
		                		return '<span style="color:red;">待审核</span>';
		                	}else{
		                		if(full.isTure==2){
		                			return edit+order;
		                		}else{
		                			return edit;
		                		}
		                	}
		                	  
		                  }
	                }
		        	]
		    } );
	});
 
 	function sort(newHouseId){
 		 $("#sortId").val("");
 		$("#newHouseId").val(newHouseId);
		$("#sortModal").modal({
			backdrop: 'static',
		    keyboard: false,
		    show: true
	    });
	}
 	
 	function saveSort(){
 		$("#orderForm").validate(function (result) {
 		  	if(result){
 		  		$("#popup_box").show();
		 		$.ajax({
					type:"post",
					url: _ctx+"/back/newHouse/sort/checkOkHouse",
					dataType:"json",
					data:{
						id:$("#newHouseId").val(),
						sortId:$("#sortId").val()
					},
					success:function(resultVo){
						$("#popup_box").hide();
						if(resultVo.status!=200){
							 setErrorContent(resultVo.message);
						}else{
							 setErrorContent("设置成功");
							
						}
					}
				})
 		  	}})
 	}
 </script>
<script src="${ctx}/static/back/newHouse/edit/newHouse_list.js"></script>