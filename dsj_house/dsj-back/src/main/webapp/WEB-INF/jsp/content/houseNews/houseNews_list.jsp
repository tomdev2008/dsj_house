<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			楼盘动态列表
		</h1>
			     <form id="searchFromId" class="form-inline">
			     		<div class="form-group">
			    		   <span class="wenzi6">发布时间</span>
		                    <input type="text" class="small-input form-control" id="LAY_demorange_ss_two" name="createTimeStart">
		                
			    		   <span class="wenzi6">至</span>
		                    <input type="text" class="small-input form-control" id="LAY_demorange_ee_two" name="createTimeEnd">
		                
			    		   <span class="wenzi6">楼盘ID</span>
		                    <input type="text" class="small-input form-control" name="houseId">
		                
			    		   <span class="wenzi6">楼盘名称</span>
		                    <input type="text" class="small-input form-control" name="houseName">
		                </div>
		                <div class="form-group mt20">
			    		   <span class="wenzi6">开发商名称</span>
		                    <input type="text" class="small-input form-control" name="developersName">
		                
							<span class="wenzi6">审核状态:</span>
							<select name="auditStatus"  class="form-control dsj-inline" style="width:100px;">
			                      <option value="">全部</option>
			                      <option value="1">待审核</option>
			                      <option value="2">已通过</option>
			                      <option value="3">已驳回</option>
		                    </select>
							<span class="wenzi6">状态:</span>
							<select name="upDownLine"  class="form-control dsj-inline" style="width:100px;">
			                      <option value="">全部</option>
			                      <option value="1">上线</option>
			                      <option value="2">下线</option>
			                      <option value="3">未上线</option>
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
					<shiro:hasPermission name='housenews:add'>
						<button class="dsj_btn btn dsj_btn_green" type="button" onclick="addHouseNews()">新建动态</button>
			   		</shiro:hasPermission>		
			   		<shiro:hasPermission name='housenews:downMany'>
			   			<button class="dsj_btn btn dsj_btn_green" type="button" onclick="downMany()">批量下线</button>
					</shiro:hasPermission>
			</div>         
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				               
				                 <th>楼盘ID</th>
				                 <th>楼盘名称</th>
				                 <th>发布时间</th>
				                 <th>动态标题</th>
				                 <th>开发商</th>
				                 <th>审批状态</th>
				                 <th>状态</th>
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
		      "iDisplayLength":20,
		      "sAjaxSource": _ctx+"/back/frame/content/houseNews/newsDataList",
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
		            		if(full.upDownLine!=1){
		            			return  "<input name='checkitem' disabled='disabled' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            		}else{
		            			return  "<input name='checkitem' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            		}
		            		
		            	}
		              }, {
		        		"mData" : "houseId",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "houseName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "createTime",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"aTargets" : [ 4 ],
		        		"mRender":function(data,type,full){
		            		 return  "<a href='"+_ctx+"/back/frame/content/houseNews/detail?id="+full.id+"'>"+full.title+"</a>";
		            	}
		        	},{
		        		"mData" : "developersName",
		        		"aTargets" : [ 5 ]
		        	},{
		        		
		        		"aTargets" : [ 6 ],
		        		"mRender":function(data,type,full){
		        			if(full.auditStatus==3){
		        				return  "<span data-toggle='tooltip' data-placement='top' title='"+full.auditReason+"'>"+full.auditStatusName+"</span>";
		        			}else{
		        				return  "<span>"+full.auditStatusName+"</span>";
		        			}
		            		 
		            	}
		        	
		        	
		        	},{
		        		"mData" : "upDownLineName",
		        		"aTargets" : [ 7 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	  if(full.upDownLine==3){

		                		 button = "<shiro:hasPermission name='housenews:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='edit("+full.id+")'>编辑</a></shiro:hasPermission>"; 
		                		 
		                	  }else if(full.upDownLine==1){
		                		  button = "<shiro:hasPermission name='housenews:down'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='down("+full.id+")'>下线</a></shiro:hasPermission>";
		                	  }else if(full.upDownLine==2){
		                		  button = "";
		                	  }
		                	  
		    	                	  
		                	return button;
		                	  
		                  }
	                }
		        	]
		    } );
	});
 function addHouseNews(){
	 location=_ctx+"/back/frame/content/houseNews/add";
 }
 function edit(id){
	 location=_ctx+"/back/frame/content/houseNews/edit?id="+id;
 }
 var id = "";
 $(function(){
 	 
 	 $("body").on("click","#downModalConfirm",function(){
 			$.ajax({
 				type:"post",
 				url:_ctx+"/back/frame/content/houseNews/down",
 				data:{
 					ids:id
 				},
 				datatype:"json",
 				success:function(result){
 					if(result.status!=200){
 						 setErrorContent(result.message);
 					}else{
 						setErrorContent(result.message);
 						setTimeout(function(){
 							$("#search_btn").click();
 						},2000);
 					}
 				}
 			})
 		  
 	  });

  
 });


 function down(param){
 	id= param;
 	setModalContent("确认删除?","downModalConfirm");
 }

 function downMany(){
 	  var ids = '';
 	  $("input[name=checkitem]:checked").each(function(){
 		  ids = ids + "," + $(this).val();
 	  });
 	  if(ids.length > 0){
 		  ids = ids.substr(1);
 	  }else{
 		  setErrorContent("请选择下线的动态");
 		  return;
 	  }
 	  id=ids;
 	setModalContent("确认下线?","downModalConfirm");
 }
 
 setLayDateTwo();
 </script>       
