<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
	<div class="white-head">
		<h1 class="page-title txt-color-blueDark">
			二手房楼盘字典查询 
		</h1>
		<ol class="breadcrumb">
			<li>二手房楼盘字典管理</li>
			<li>二手房楼盘字典查询 </li>
		</ol>
		<form id="searchFromId" class="form-inline">
			<div class="form-group mr20">
				<span class="wenzi6">省:</span>
				<select  id="areaOneId" class="form-control dsj-inline" style="width:100px;" name="areaCode1" onchange="getTwoArea()">
					<option value="">请选择</option>
					<c:forEach items="${firstAreaList }" var="area">
						<c:if test="${area.areaCode!=1 }">
							<option value="${area.areaCode }">${area.name }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">市:</span>
				<select  id="areaTwoId" class="form-control dsj-inline" style="width:100px;" name="areaCode2"  onchange="getThreeArea()">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">区:</span>
				<select  id="areaThreeId" class="form-control dsj-inline" style="width:100px;" name="areaCode3"  onchange="getFourArea()">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">商圈:</span>
				<select  id="areaFourId" class="form-control dsj-inline" style="width:100px;" name="tradingAreaId">
					<option value="">请选择</option>
				</select>
			</div>
			<div class="form-group mr20">
				<span class="wenzi6">字典名称:</span>
				<input type="text" class="small-input form-control" placeholder="模糊查询" name="cardName"  style="width: 100px;">
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
				<shiro:hasPermission name='houseDirectory:add'><button class="dsj_btn btn dsj_btn_green" type="button" onclick="addDirectory()" style="width: 150px;">新建</button></shiro:hasPermission>
				<shiro:hasPermission name='houseDirectory:deletes'><button class="dsj_btn btn btn-default" type="button" onclick="delDirectorys()">批量删除</button></shiro:hasPermission>
				<!--表格 S-->
				<div class="slb_table H_slb_table">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable">
							<thead>
								<tr>
									<th><input id="checkall" type="checkbox" onclick="checkAllCheckbox(this,'checkitem')"/></th>
										<th>城市</th>
										<th>区域</th>
										<th>商圈</th>
										<th>楼盘字典</th>
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
<script>
var id = "";
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
	      "sAjaxSource": _ctx+"/back/houseDirectory/page/list",
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
              	},{
	        		"mData" : "areaName2",
	        		"aTargets" : [ 1 ]
	        	},{
	        		"mData" : "areaName3",
	        		"aTargets" : [ 2 ]
	        	},{
	        		"mData" : "tradingAreaName",
	        		"aTargets" : [ 3 ]
	        	},{
	        		"mData" : "sprayName",
	        		"aTargets" : [ 4 ]
	        	},{"bSortable": false,
	                  "mRender":function(data,type,full){
	                	  var edit = "<shiro:hasPermission name='houseDirectory:edit'><a class='dsj_btn btn btn-default' href='javascript:void(0);' onclick='editDirectory("+full.id+")'>编辑</a></shiro:hasPermission>";
	                	  return edit;
	                  }
	            }
        	]
	    } );
	 
	 $("body").on("click","#delFlatModalConfirm",function(){
		 var _ids = ids;
			$.ajax({
				type:"post",
				url:_ctx+"/back/houseDirectory/directory_delete",
				data:{
					ids:_ids
				},
				datatype:"json",
				success:function(result){
					if(result.status!=200){
						 setErrorContent(result.message);
					}else{
						$("#search_btn").click();
					}
				}
			})
		  
	  });
});
</script>
<script src="${ctx}/static/back/system/directory/directory_list.js"></script>