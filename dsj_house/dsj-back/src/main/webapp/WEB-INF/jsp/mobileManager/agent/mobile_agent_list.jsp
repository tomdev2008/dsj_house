<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
			     <form id="searchFromId" class="form-inline">
			     		 <div class="form-group mr20">
		                	<span class="wenzi6">400号码:</span>
		                    <input type="text" class="small-input form-control" placeholder="400号码" name="mobile">
		                </div>
			    		 <div class="form-group mr20">
			    		   <span class="wenzi6">经纪人名称:</span>
		                    <input type="text" class="small-input form-control" placeholder="经纪人名称" name="agentName">
		                </div>
		          		<div class="form-group mr20">
							<span class="wenzi6">经纪人编码:</span>
		                    <input type="text" class="small-input form-control" placeholder="经纪人编码" name="agentCode">
		                </div>
		                <div class="form-group mr20">
			    		   <span class="wenzi6">手机号:</span>
		                    <input type="text" class="small-input form-control" placeholder="请输入手机号" name="phone">
		                </div>
		                <div class="form-group mr20">
					        <span class="wenzi6">绑定时间:</span>
					        <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ss_one" placeholder="开始时间" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" id="LAY_demorange_ee_one" placeholder="结束时间" name="endTime" style="width: 120px;">
	                	</div>
		                <div class="form-group mr20">
							<span class="wenzi6">号码状态:</span>
							<select class="form-control dsj-inline dsj-width-1" name="status">
									<option value="">请选择</option>
									<option value="1">已绑定</option>
									<option value="2">未绑定</option>
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
						<shiro:hasPermission name='mobileAgent:agent'>
				   		  <button class="dsj_btn btn dsj_btn_green" type="button" onclick="addSmallMobile(2,null)">生成经纪人小号</button>
				   		</shiro:hasPermission>
				     </div>
			        <!--表格 S-->
			        <div class="slb_table H_slb_table col-xs-12">
			            <div class="table-responsive">
			                <table class="table table-bordered" id="dataTable">
			                     <thead>
					             <tr>
					                 <th>400号码</th>
					                 <th>号码状态</th>
					                 <th>经纪人名称</th>
					                 <th>经纪人编码</th>
					                 <th>目标号码</th>
					                 <th>最近绑定时间</th>
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
<script type="text/javascript">
var id;
 $(function(){
	 setLayDateOne();
	 $("body").on("click","#removeBindingModalConfirm",function(){
		 $("#popup_box").show();
		 $.ajax({
				type:"post",
				async:true,
				data:{
					id:id
				},
				dataType:"json",
				url:_ctx+"/back/frame/mobileManager/agent/removeBinding",
				success:function(resultVo){
					$("#popup_box").hide();
					if(resultVo.status!=200){
						 setErrorContent(resultVo.message);
					}else{
						$("#dataTable").DataTable().ajax.reload();
					}
				}
			})
		  
	  });
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
		      "sAjaxSource": _ctx+"/back/frame/mobileManager/agent/page/list",
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
		        
		            "aoColumns" : [{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	return "<a href='javascript:void(0);' onclick='checkHistory("+full.id+")'>400-8986868-"+full.mobile+"</a>";
			                }
		            },{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.status==2){
			                		return "未绑定";
			                	}else{
			                		return"已绑定";
			                	}
		                  }
		            },{
		        		"mData" : "agentName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "agentCode",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "phone",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "lastBindingTime",
		        		"aTargets" : [ 3 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var edit = "<shiro:hasPermission name='mobileAgent:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editBinding("+full.id+")'>编辑</a></shiro:hasPermission>";
		                	var noBinding = "<shiro:hasPermission name='mobileAgent:removeBing'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='removeBinding("+full.id+")'>解绑</a></shiro:hasPermission>";
		                	var Binding = "<shiro:hasPermission name='mobileAgent:bing'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='binding("+full.id+")'>绑定</a></shiro:hasPermission>";
		                	if(full.status==2){
		                		return Binding;
		                	}else{
		                		return edit + noBinding;
		                	}
		                	  
		                  }
	                }
		        	]
		    } );
	});
 
 function binding(mobileId){
	 location = _ctx+"/back/frame/mobileManager/agent/binding?id="+mobileId;
 }
 function editBinding(mobileId){
	 location = _ctx+"/back/frame/mobileManager/agent/editBinding?id="+mobileId;
 }
 function removeBinding(mobileId){
	 id = mobileId;
	 setModalContent("确认解绑吗?","removeBindingModalConfirm");
 }
 function checkHistory(id){
	 openCommonModal(_ctx+"/back/mobileManager/agent/mobile_agent_binding_history?id="+id,1000)
 }
 </script>
<script src="${ctx}/static/back/mobileManager/mobile.js"></script>