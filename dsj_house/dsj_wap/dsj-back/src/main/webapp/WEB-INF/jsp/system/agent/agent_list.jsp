<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			经纪人列表
		</h1>
		<div class="row">
			   <button class="dsj_btn btn dsj_btn_green" type="button" onclick="agentList()">经纪人</button>
			   <button class="dsj_btn btn btn-default" type="button" onclick="agentUnApply()">未认证</button>
		</div> 
			     <form id="searchFromId" class="form-inline">
			     		<div class="form-group mt20">
			    		   <span class="wenzi6">申请时间:</span>
		                    <input type="text" class="small-input form-control" id="LAY_demorange_zs" name="applyTimeStart">
			    		   <span class="wenzi6">至</span>
		                    <input type="text" class="small-input form-control" id="LAY_demorange_ze" name="applyTimeEnd">
			    		   <span class="wenzi6">公司:</span>
		                    <input type="text" class="small-input form-control" maxlength="20" placeholder="公司(不超过20个字)" name="companyName">
		              	    <span class="wenzi6">姓名:</span>
		                    <input type="text" class="small-input form-control" placeholder="模糊匹配" name="name">
		              	 
			            </div>
			            <div class="form-group mt20">
			            		<span class="wenzi6">所在省:</span>
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
			              	    <span class="wenzi6">商圈:</span>
				                    <select  id="areaFourId" name="business" class="form-control dsj-inline" style="width:200px">
					                      <option value="">请选择</option>
				                    </select>
		                	
		                </div>
		                <div class="form-group mt20">
		                	<span class="wenzi6">联系方式:</span>
		                    <input type="text" class="small-input form-control" placeholder="模糊匹配"  name="tellPhone">		            
			                <span class="wenzi6">卖新房:</span>
				            <input name="isSellNewHouse" type="checkbox" value="1"  />
							<span class="wenzi6">审核状态:</span>
							<select name="auditStatus"  class="form-control dsj-inline" style="width:100px;" id="status">
			                      <option value="">请选择</option>
			                      <option value="1">已通过</option>
			                      <option value="2">未审核</option>
			                      <option value="3">已驳回</option>
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
			   		<shiro:hasPermission name='agent:add'><button class="dsj_btn btn dsj_btn_green" type="button" onclick="addAgent()">添加经纪人</button></shiro:hasPermission>
			   		<!-- <button class="dsj_btn btn btn-default" type="button" onclick="delAgent()">批量删除</button> -->
			        <shiro:hasPermission name='agent:resetMany'><button class="dsj_btn btn btn-default" type="button" onclick="resetPwdMany()">重置密码</button></shiro:hasPermission>
			</div>         
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				                 <th>经纪人编号</th>
				                 <th>申请时间</th>
				                 <th>公司</th>
				                 <th>姓名</th>
				                 <th>联系方式</th>
				                 <th>行政区域</th>
				                 <th>商圈</th>
								 <th>主营小区</th>
								 <th>是否卖新房</th>
								 <th>销售新房楼盘</th>
								 <th>审核状态</th>
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
		      "sAjaxSource": _ctx+"/back/frame/system/agent/dataList",
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
		        		"mData" : "agentCode",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "applyTime",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "companyName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"aTargets" : [ 4 ],
		        		"mRender":function(data,type,full){
		            		 return  "<a onclick='agentInfo("+full.id+")'>"+full.name+"</a>";
		            	}
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
		        		"mData" : "auditStatusName",
		        		"aTargets" : [ 11 ]
		        	},{
		        		"mData" : "responName",
		        		"aTargets" : [ 12 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var edit = "<shiro:hasPermission name='agent:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editAgent("+full.id+")'>编辑</a></shiro:hasPermission>";
		                	  var resetPwd = "<shiro:hasPermission name='agent:reset'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='resetPwd("+full.userId+")'>重置密码</a></shiro:hasPermission>";
		                	  var sort = "<shiro:hasPermission name='agent:sort'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='sortPage()'>排序</a></shiro:hasPermission>";	
		                	  if(full.auditStatus==2){
		                		  return resetPwd;
		                	  }else{
		                		  if(full.auditStatus==1){
		                			  return edit+resetPwd+sort;
		                		  }else{
		                			  return edit+resetPwd;
		                		  }
		                		  
		                	  }
		                	
		                	  
		                  }
	                }
		        	]
		    } );
	});
 
 function agentInfo(id){
	 location=_ctx+"/back/frame/system/agent/agentInfo?id="+id;
 }
 function sortPage(){
	 location=_ctx+"/back/frame/system/agent/agentSort";
 }
 
 </script>       
<script src="${ctx}/static/back/system/agent/agent_list.js"></script>