<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	 <h1 class="page-title txt-color-blueDark">
			权证代办人账号查询 
		</h1>
		<ol class="breadcrumb">
			<li>权证代办人账号管理</li>
			<li>权证代办人账号查询 </li>
		</ol>
	     <form id="searchFromId" class="form-inline">
			    	 <div class="form-group mr20">
					        <span class="wenzi6">创建时间:</span>
					        <input value=""  class="small-input form-control laydate-icon" placeholder="开始时间" id="LAY_demorange_ss_one" name="startTime" style="width: 120px;">
					        <span class="wenzi2">~</span>
					         <input value=""  class="small-input form-control laydate-icon" placeholder="结束时间" id="LAY_demorange_ee_one" name="endTime" style="width: 120px;">
	                </div>
	                <div class="form-group mr20">
					        <span class="wenzi6">商家名称:</span>
					         <input type="text" class="small-input form-control" placeholder="商家名称" name="companyName"  style="width: 100px;">
	                </div>
	                 <div class="form-group mr20">
					        <span class="wenzi6">代办人姓名:</span>
					         <input type="text" class="small-input form-control" placeholder="代办人姓名" name="name" style="width: 100px;">
	                </div>
	                <div class="form-group mr20">
					        <span class="wenzi6">手机号:</span>
					         <input type="text" class="small-input form-control" placeholder="手机号" name="tellPhone" style="width: 100px;">
	                </div>
	                <div class="form-group mr20">
							<span class="wenzi6">状态:</span>
							<select name="auditStatus"  class="form-control dsj-inline" style="width:100px;" id="status">
			                      <option value="">请选择</option>
			                      <option value="0">已下架</option>
			                      <option value="1">已上架</option>
			                      <option value="2">已关闭</option>
		                    </select>
	                </div>
                 <div class="btngroup row mt20 text-center">
	            	 	<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
					 <button class="dsj_btn btn btn-default" type="reset">重置</button>
	                </div>
         </form> 
        </div>
      <div class="white-content">
		<div class="row">
				<div class="col-xs-12" style="padding-right:25px;"> 
					<shiro:hasPermission name='property:add'>
			   		  	<button class="dsj_btn btn dsj_btn_green" type="button" onclick="addProperty()" style="width: 150px;">添加代办人</button>
			   		</shiro:hasPermission>
			   		<shiro:hasPermission name='property:reset'>
	                  	<button class="dsj_btn btn btn-default" type="button" onclick="resetPwdMany()">重置密码</button>
	                </shiro:hasPermission>
                 
		 <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     <thead>
				             <tr>
				             	<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
				                 <th>创建时间</th>
				                 <th>商家名称</th>
				                 <th>代办人ID</th>
				                 <th>代办人姓名</th>
				                 <th>手机号</th>
				                 <th>业务范围</th>
				                 <th>服务区域</th>
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
	 </div>
 </div>
 <script type="text/javascript">
 $(function(){
	 setLayDateOne();
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
		      "sAjaxSource": _ctx+"/back/system/property/page/list",
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
		            		 return  "<input name='checkitem' value='"+full.userId+"' type='checkbox' />";
		            	}
		              }, {
		        		"mData" : "createTime",
		        		"aTargets" : [ 1 ]
		        	}, {
		        		"mData" : "companyName",
		        		"aTargets" : [ 2 ]
		        	},{
		        		"mData" : "userId",
		        		"aTargets" : [ 3 ]
		        	},{
		        		"mData" : "name",
		        		"aTargets" : [ 4 ]
		        	},{
		        		"mData" : "tellPhone",
		        		"aTargets" : [ 5 ]
		        	},{
		        		"mData" : "businessName",
		        		"aTargets" : [ 6 ]
		        	},{
		        		"mData" : "areaName",
		        		"aTargets" : [ 7 ]
		        	},{"bSortable": false,
		                  "mRender":function(data,type,full){
			                	if(full.auditStatus==0){
			                		return "已下架";
			                	}else if(full.auditStatus==1){
			                		return "已上架";
			                	}else if(full.auditStatus==2){
			                		return "已关闭";
			                	}
			               }
		           },{"bSortable": false,
		                  "mRender":function(data,type,full){
		                	  var edit = "<shiro:hasPermission name='property:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editProperty("+full.id+")'>编辑</a></shiro:hasPermission>";
		                	  var shangJia = "<shiro:hasPermission name='property:shangJia'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='shangJia("+full.id+")'>上架</a></shiro:hasPermission>";
		                	  var xiaJia = "<shiro:hasPermission name='property:xiaJia'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='xiaJia("+full.id+")'>下架</a></shiro:hasPermission>";
		                	  var qiDong = "<shiro:hasPermission name='property:qiDong'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='shangJia("+full.id+")'>启动</a></shiro:hasPermission>";
		                	  var guanBi = "<shiro:hasPermission name='property:guanBi'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='guanBi("+full.id+")'>关闭</a></shiro:hasPermission>";
		                	  var tuiJian = "<shiro:hasPermission name='property:tuiJian'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='tuiJian("+full.id+")'>推荐</a></shiro:hasPermission>";
		                	  var delTJ = "<shiro:hasPermission name='property:delTJ'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='delTJ("+full.id+")'>取消推荐</a></shiro:hasPermission>";
		                	  if(full.auditStatus==0){
		                		  return edit+shangJia+guanBi;
		                	  }else if(full.auditStatus==1 && full.recommend == 0){
		                		  return edit+xiaJia+guanBi+tuiJian;
		                	  }else if(full.auditStatus==2){
		                		  return edit+qiDong;
		                	  }else if(full.auditStatus==1 && full.recommend == 1){
		                		  return edit+delTJ;
		                	  }
		                  }
	                }
		        	]
		    } );
 })
 </script>
<script src="${ctx}/static/back/system/property/property_list.js"></script>