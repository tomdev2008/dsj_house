<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
               <div class="row">
				<ul class="nav nav-tabs button_tab" role="tablist">
					<li role="presentation"><a href="${ctx }/back/frame/newHouse/guardian/newHouse_guardian">楼盘维护人管理</a></li>
					<li role="presentation" class="active"><a href="${ctx }/back/frame/newHouse/guardian/newHouse_evelopers">开发商管理</a></li>
				</ul>
			</div>
			     <form id="searchFromId" class="form-inline">
			    		 <div class="form-group">
			    		   <span class="wenzi6">楼盘ID:</span>
		                    <input type="text" maxlength="10" class="small-input form-control" placeholder="楼盘ID" name="id">
		                </div>
		          		<div class="form-group">
							<span class="wenzi6">楼盘名称:</span>
		                    <input type="text" maxlength="10" class="small-input form-control" placeholder="楼盘名称" name="name">
		                </div>
		                <div class="form-group">
		                	<span class="wenzi6">开发商:</span>
		                    <input type="text" maxlength="10" class="small-input form-control" placeholder="开发商名字" name="eveloperName">
		                </div>
		               
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				                 <th>楼盘ID</th>
				                 <th>楼盘名称</th>
				                 <th>楼盘状态</th>
				                 <th>开发商</th>
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
		      "sAjaxSource": _ctx+"/back/newHouse/guardian/findEveloper",
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
		        		"mData" : "id",
		        		"aTargets" : [ 1 ]
		        	  },{
			        		"mData" : "name",
			        		"aTargets" : [ 2 ]
			          },{
			        		"mData" : "shelves",
			        		"aTargets" : [ 3 ]
			          },{
			        		"mData" : "eveloperName",
			        		"aTargets" : [ 4 ]
			          },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	var setUp = "<shiro:hasPermission name='newHouse:evelopers'><a class='btn' href='javascript:void(0);' onclick='openCommonModal(\""+_ctx+"/back/newHouse/guardian/toSetUpEvelopers?id="+full.id+"\",1000)'>绑定开发商</a></shiro:hasPermission>";
		                	var Unbound = "";
		                	if (full.eveloperName!=null) {
		                		Unbound = "<shiro:hasPermission name='newHouse:unbounds'><a class='btn' href='javascript:void(0);' onclick='Unbound("+full.id+")'>解绑</a></shiro:hasPermission>";
		                	}
			 	             return setUp+Unbound;
		                	 
		                  }
	                }
		        	]
		    } );
	});
 
 function Unbound(param){
	 id=param;
	 setModalContent("确认解除绑定吗?","unboundNewHouseConfirm");
 }
 $("body").on("click","#unboundNewHouseConfirm",function(){
	 $("#popup_box").show();
		$.ajax({
			type:"post",
			url:_ctx+"/back/newHouse/guardian/unboundEveloper",
			data:{
				id:id
			},
			datatype:"json",
			success:function(result){
				$("#popup_box").hide();
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					$("#search_btn").click();
				}
			}
		})
	  
	});
 
 function newHouse_guardian(){
	 $("#popup_box").show();
	 location=_ctx+"/back/frame/newHouse/guardian/newHouse_guardian";
 }
 function newHouse_evelopers(){
	 $("#popup_box").show();
	 location=_ctx+"/back/frame/newHouse/guardian/newHouse_evelopers";
 }
 </script>       
