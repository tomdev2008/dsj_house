<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			楼盘动态审核列表
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
		               
			    		   <span class="wenzi6">发布人</span>
		                    <input type="text" class="small-input form-control" name="userName">
		                </div>
		                
	                	</div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
			<div class="row">
			   		<shiro:hasPermission name='housenews:successMany'><button class="dsj_btn btn dsj_btn_green" type="button" onclick="passMany()">批量通过</button></shiro:hasPermission>
			   		<shiro:hasPermission name='housenews:refuse'><button id="hidden"  class="dsj_btn btn btn-default" type="button" onclick="refuseMany()">批量驳回</button></shiro:hasPermission>
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
				                 <th>开发商名称</th>
				                 <th>发布人</th>				      
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
				        <button class="btn btn-primary" type="button" onclick="refuseManyEnsure()">提交</button>
				        <button class="btn btn-default" onclick="remove()" data-dismiss="modal">取消</button>
				    </div>
                       
                   </div>
               </div>
</div>
 <script type="text/javascript">
 var id = '';
 var msg='';
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
		      "sAjaxSource": _ctx+"/back/frame/content/houseNews/auditDataList",
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
		            		if(full.auditStatus!=1){
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
		        		"mData" : "title",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "developersName",
		        		"aTargets" : [ 5 ]
		        	},{
		        		"mData" : "userName",
		        		"aTargets" : [ 6 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
								var button = "<shiro:hasPermission name='housenews:audit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='audit("+full.id+")'>审核</a></shiro:hasPermission>";      	  
		                	return button;
		                	  
		                  }
	                }
		        	]
		    } );
	});
 $("body").on("click","#passManyModalConfirm",function(){
		$.ajax({
			type:"post",
			url:_ctx+"/back/frame/content/houseNews/audit",
			data:{
				ids:id,
				status:2,
				msg:""
			},
			datatype:"json",
			success:function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					setErrorContent("恭喜您，审核成功");
					setTimeout(function(){
						location=_ctx+"/back/frame/content/houseNews/auditList";
					},2000);			
				}
			}
		})
	  
});
 function passMany(){
		var ids = '';
		  $("input[name=checkitem]:checked").each(function(){
			  ids = ids + "," + $(this).val();
		  });
		  if(ids.length > 0){
			  ids = ids.substr(1);
		  }else{
			  setErrorContent("请选择要通过的动态");
			  return;
		  }
		  id=ids;
		setModalContent("您确认通过吗？","passManyModalConfirm");
	}
function audit(id){
	location=_ctx+"/back/frame/content/houseNews/auditPage?id="+id;
}

$("body").on("click","#refuseManyModalConfirm",function(){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/content/houseNews/audit",
		data:{
			ids:id,
			status:3,
			msg:msg
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				setErrorContent("恭喜您，审核成功");
				setTimeout(function(){
					location=_ctx+"/back/frame/content/houseNews/auditList";
				},2000);
			
			}
		}
	})
  
});


function refuseMany(){
	  var ids = '';
	  $("input[name=checkitem]:checked").each(function(){
		  ids = ids + "," + $(this).val();
	  });
	  if(ids.length > 0){
		  ids = ids.substr(1);
	  }else{
		  setErrorContent("请选择要驳回的动态");
		  return;
	  }
	  id=ids;
	  show();
}
function show(){
	$("#authModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true
    });
}
function remove(){
	$('#content').val("");
}
function refuseManyEnsure(){
	if($('#content').val()==''){
		alert("驳回理由不可以为空");
	}else{
		$("#authModal").hide();
		msg = $('#content').val();
		setModalContent("确认驳回？","refuseManyModalConfirm");
	}
}

setLayDateTwo();
 </script>       

