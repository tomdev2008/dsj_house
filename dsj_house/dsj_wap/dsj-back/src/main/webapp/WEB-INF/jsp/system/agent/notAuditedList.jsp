<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			待审核经纪人
		</h1>
			 	<form id="searchFromId" class="form-inline">
			     	<div class="form-group">
			    		   <span class="wenzi6">申请时间:</span>
		                   <input type="text" class="small-input form-control" id="LAY_demorange_zs" name="applyTimeStart"> 
			    		   <span class="wenzi6">至</span>
		                   <input type="text" class="small-input form-control" id="LAY_demorange_ze" name="applyTimeEnd">		                
			    		   <span class="wenzi6">公司:</span>
		                   <input type="text" class="small-input form-control" placeholder="公司" name="companyName">		                
		                   <span class="wenzi6">姓名:</span>
		                   <input type="text" class="small-input form-control" placeholder="模糊匹配" name="name">
		             </div>
		          		<div class="form-group mt20">
		                	<span class="wenzi6">联系方式:</span>
		                    <input type="text" class="small-input form-control" placeholder="模糊匹配" name="tellPhone">		                
		              	    <span class="wenzi6">所在省市:</span>
			                	<select  id="areaOneId" name="province" onchange="getTwoArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="">请选择</option>
				                       <c:forEach items="${firstAreaList }" var="area">
				                       	 	<c:if test="${area.areaCode!=1 }">
				                        	 	<option value="${area.areaCode }">${area.name }</option>
				                        	 </c:if>
				                        </c:forEach>
			                    </select>		            
		              	    <span class="wenzi6">市:</span>
								<select  id="areaTwoId" name="city" onchange="getThreeArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="">请选择</option>
			                    </select>			            
			              	    <span class="wenzi6">行政区:</span>
			                    <select  id="areaThreeId" name="area" onchange="getFourArea()" class="form-control dsj-inline" style="width:200px">
				                      <option value="">请选择</option>
			                    </select>
			            </div>
			            <div class="form-group mt20">
			              	<span class="wenzi6">商圈:</span>
			                    <select  id="areaFourId" name="business" class="form-control dsj-inline" style="width:200px">
				                      <option value="">请选择</option>
			                    </select>			           
		                	<span class="wenzi6">主营楼盘:</span>
		                    <input type="text" class="small-input form-control" name="mainCommunity">		               
			                <span class="wenzi6">卖新房:</span>
				            <input name="isSellNewHouse" type="checkbox" value="1"  />
		            	</div>
		            	<div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
			<div class="row">
			   		<shiro:hasPermission name='agent:successMany'><button class="dsj_btn btn dsj_btn_green" type="button" onclick="passAgentMany()">批量通过</button></shiro:hasPermission>
			   		<shiro:hasPermission name='agent:refuseMany'><button id="hidden"  class="dsj_btn btn btn-default" type="button" onclick="refuseAgentMany()">批量驳回</button></shiro:hasPermission>
			</div>         
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				                 <th>经纪人ID</th>
				                 <th>申请时间</th>
				                 <th>公司</th>
				                 <th>姓名</th>
				                 <th>联系方式</th>
				                 <th>行政区域</th>
				                 <th>商圈</th>
								 <th>主营小区</th>
								 <th>是否卖新房</th>
								 <th>销售新房楼盘</th>
								 <th>责任楼盘</th>
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
 <div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" style="width: 560px;">
                         <div class="modal-header">
	           <div class="row">
	          	 <h4 class="modal-title">驳回理由</h4>
		    	</div>
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				 <form id="authForm" class="col-xs-8 col-xs-offset-2">
										 <div class="form-group">
									        <div class="col-sm-6">
									          <textarea rows="4" cols="35" name="content" id="content"></textarea>
									      
									        </div>
									    </div>
									</form>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				        <button class="btn btn-primary" type="button" onclick="refuseAgentManyEnsure()">提交</button>
				        <button class="btn btn-default" onclick="remove()" data-dismiss="modal">取消</button>
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
		      "sAjaxSource": _ctx+"/back/frame/system/agent/notAuditDataList",
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
		        		"mData" : "agentCode",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "applyTime",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "companyName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "name",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 5 ]
		        	},{
		        		"mData" : "areaName",
		        		"aTargets" : [ 6 ]
		        	},{
		        		"mData" : "businessName",
		        		"aTargets" : [ 7 ]
		        	},{
		        		"mData" : "mainCommunity",
		        		"aTargets" : [ 8 ]
		        	},{
		        		"mData" : "isSellNewHouseName",
		        		"aTargets" : [ 9 ]
		        	},{
		        		"mData" : "sellBuilding",
		        		"aTargets" : [ 10 ]
		        	},{
		        		"mData" : "dutyBuilding",
		        		"aTargets" : [ 11 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var audit = "<shiro:hasPermission name='agent:audit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='auditAgentPage("+full.id+")'>审核</a></shiro:hasPermission>";                	  
		                	return audit;
		                	  
		                  }
	                }
		        	]
		    } );
	});
 </script>
 <script src="${ctx}/static/back/system/agent/dialog.js"></script>       
<script src="${ctx}/static/back/system/agent/agent_list.js"></script>