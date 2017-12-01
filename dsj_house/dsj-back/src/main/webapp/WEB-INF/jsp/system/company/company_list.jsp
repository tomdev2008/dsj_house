<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			公司列表
		</h1>
		<div class="row" style="margin-bottom:15px">
			   <button class="dsj_btn btn dsj_btn_green" type="button" onclick="companyList()">经纪公司</button>
			   <button class="dsj_btn btn btn-default" type="button" onclick="businessList()">商家</button>
		</div>
			     <form id="searchFromId" class="form-inline">
			    		 <div class="form-group">
			    		   <span class="wenzi6">公司名称:</span>
		                    <input type="text" class="small-input form-control" name="companyName">
		                </div>
		            	 <div class="btngroup row mt20 text-center">
		                     <button class="btn btn-primary" id="search_btn" type="button">查询</button>
		                     <button class="btn btn-default" type="reset">重置</button>
		                </div>
		         </form> 
		      </div>
		       <div class="white-content">
			<div class="row">
			   		<shiro:hasPermission name='company:add'><button class="dsj_btn btn dsj_btn_green" type="button" onclick="addCompany()">添加经纪公司</button></shiro:hasPermission>
			</div>         
		        <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				                 <th>公司ID</th>
				                 <th>公司全称</th>
				                 <th>公司简称</th>
				                 <th>城市</th>
				                 <th>录入时间</th>
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
		      "sAjaxSource": _ctx+"/back/frame/system/company/dataList",
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
		            		if(full.isBlack==1){
		            			return  "<input name='checkitem' disabled='disabled' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            		}else{
		            			return  "<input name='checkitem' value='"+full.id+"' type='checkbox' onclick=isChechedFirst('checkall','checkitem') />";
		            		}
		            		
		            	}
		              }, {
		        		"mData" : "id",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "companyName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "shortName",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "city",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "createTime",
		        		"aTargets" : [ 5 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
	
		                	button = "<shiro:hasPermission name='company:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editCompany("+full.id+")'>修改</a></shiro:hasPermission>";
          	  
		                	return button;
		                	  
		                  }
	                }
		        	]
		    } );
	});
 
 function addCompany(){
	 location=_ctx+"/back/frame/system/company/addPage";
 }
 function editCompany(param){
	 location=_ctx+"/back/frame/system/company/editPage?id="+param;
 }
 function companyList(){
	 location=_ctx+"/back/frame/system/company/companyList";
 }
 function businessList(){
	 location=_ctx+"/back/frame/system/company/businessList";
 }
 </script>       