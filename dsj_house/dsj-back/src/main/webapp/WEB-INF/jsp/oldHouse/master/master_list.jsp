<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12">
<div class="white-head">
	
		<ol class="breadcrumb">
			<li>二手房房源管理</li>
			<li>开发房源查询 </li>
		</ol>
	     <form id="searchFromId" class="form-inline">
			    <div class="form-group mr20">
					<span class="wenzi6">省:</span>
					<select  id="areaOneId" class="form-control dsj-inline" style="width:100px;" name="areaCode1" onchange="getTwoArea()">
						<option value="">全部</option>
						<c:forEach items="${firstAreaList }" var="area">
							<c:if test="${area.areaCode!=1 }">
								<option value="${area.areaCode }">${area.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="form-group mr20">
					<span class="wenzi6">市:</span>
					<select  id="areaTwoId" class="form-control dsj-inline"  name="areaCode2"  onchange="getThreeArea()">
						<option value="">全部</option>
					</select>
				</div>
				<div class="form-group mr20">
					<span class="wenzi6">区:</span>
					<select  id="areaThreeId" class="form-control dsj-inline "  name="areaCode3"  onchange="getFourArea()">
						<option value="">全部</option>
					</select>
				</div>
				<div class="form-group mr20">
					<span class="wenzi6">商圈:</span>
					<select  id="areaFourId" class="form-control dsj-inline " name="tradingAreaId">
						<option value="">全部</option>
					</select>
				</div>
                <div class="form-group mr20">
				        <span class="wenzi6">小区名称或者房源ID:</span>
				         <input type="text" class=" form-control " placeholder="小区名称或者房源ID" name="name"  style="width: 150px;" >
				         
                </div>
                 <div class="form-group mr20">
                 <span class="wenzi6" style="width:100px;">状态:</span>
	                  	  <select name="status" class="form-control" >
							     <option value="">全部</option>
							     <option value="1">未上架</option>
							     <option value="2">已上架</option>
							     <option value="3">已下架</option>
						 </select>
                </div>
               <div class="form-group mt20">
			    		 <span class="wenzi6">面积:</span>
						<input class="small-input9 form-control" name="buildAreaMin" type="text" placeholder="最小面积" data-validate="isNumber9999">
						<span class="wenzi2">~</span>
						<input class="small-input9 form-control" name="buildAreaMax" type="text" placeholder="最大面积" data-validate="isNumber9999">
					
			    		 <span class="wenzi6">价格:</span>
						<input class="small-input9 form-control" name="priceMin" type="text" placeholder="最小价格" data-validate="isNumber999999">
						<span class="wenzi2">~</span>
						<input class="small-input9 form-control" name="priceMax" type="text" placeholder="最大价格" data-validate="isNumber999999">
						 
						 <span class="wenzi6">房型:</span>
						<input class="small-input9 form-control" name="roomMin"   type="text" data-validate="isNumber999" >
						<span class="wenzi2">~</span>
						<input class="small-input9 form-control" name="roomMax"  type="text" data-validate="isNumber999">室
				</div>
				
				 <div class="form-group mt20">
              		 
					 <div class="form-group mr20">
				        <span class="wenzi6">创建时间:</span>
				        <input id="LAY_demorange_ss_two"  class="form-control layDateClass laydate-icon" placeholder="开始时间" name="startCreateTime" style="width:120px;">
				        <span class="wenzi2">~</span>
				         <input id="LAY_demorange_ee_two" class="form-control layDateClass laydate-icon" placeholder="结束时间" name="endCreateTime" style="width: 120px;">
                    </div>
                	
				</div>
				
                <div class="btngroup row mt20 text-center">
            	 	<button class="dsj_btn btn dsj_btn_blue" id="search_btn" type="button">查询</button>
					 <button class="dsj_btn btn btn-default" id="reset_btn" type="reset">重置</button>
                </div>
         </form> 
        </div>
      <div class="white-content">
		<div class="row">
				<div class="col-xs-12" style="padding-right:25px;">
			   		  <shiro:hasPermission name='ershoufang:add'>
			   		  	<button class="dsj_btn btn dsj_btn_green" type="button" onclick="addMaster()" style="width: 150px;">新建房源</button>
			   		  </shiro:hasPermission>
			   		  <shiro:hasPermission name='ershoufang:deletes'>
	                  	<button class="dsj_btn btn btn-default" type="button" onclick="delMasters()">批量删除</button>
	                  </shiro:hasPermission>
                 	
                 	  <shiro:hasPermission name='ershoufang:downs'>
	                  	<button class="dsj_btn btn btn-default" type="button" onclick="upDownMasters(3)">批量下架</button>
	                  </shiro:hasPermission>
                 
		 <!--表格 S-->
		        <div class="slb_table H_slb_table">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable">
		                     
		                </table>
		            </div>
		        </div>
		     </div>
		</div>
	 </div>
 </div>
 <script type="text/javascript">
 var table;
 $(function(){
var columns = [
	{
	"title":"<input id='checkall' type='checkbox' />",
	"mRender":function(data,type,full){
		 return  "<input name='checkitem' value='"+full.id+"' type='checkbox' data-status='"+full.status+"'  />";
	}
  	}, {
	"title":"创建时间",
	"mData" : "createTime"
	}, {
	"title":"房源ID",
	
		 "mRender":function(data,type,full){
			
			 return "<a href='"+_ctx+"/back/frame/oldHouse/master/master_view?id="+full.id+"''>"+full.id+"</a>";
		 }
	},{
	"title":"行政区域",
	"mData" : "areaName3"
	},{
	"title":"商圈",
	"mData" : "tradingAreaName"
	},{
	"title":"小区名称",
	"mData" : "sprayName"
	},{
	"title":"户型",
	 "mRender":function(data,type,full){
		 var room="";
		 if(full.room!=''&&full.room!=null){
			 room+=full.room+"室";
		 }
		  if(full.hall!=''&&full.hall!=null){
				 room+=full.hall+"厅";
		 }
		  if(full.toilet!=''&&full.toilet!=null){
				 room+=full.toilet+"卫";
		 }
		  if(full.kitchen!=''&&full.kitchen!=null){
				 room+=full.kitchen+"厨";
		 }
		 return room;
	 }
	},{
	"title":"面积",
	"mData" : "buildArea"
	},{
	"title":"价格",
	"mData" : "price"
	},{
	"title":"楼层",
	"mData" : "floor"
	},{
	"title":"状态",
	"mData" : "statusName"
	},{
	"title":"操作",
      "mRender":function(data,type,full){
    	  
    	var edit = "<shiro:hasPermission name='ershoufang:edit'><a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='editMaster("+full.id+")'>编辑</a></shiro:hasPermission>";
    	var del = "<shiro:hasPermission name='ershoufang:delete'><a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='delMaster("+full.id+")'>删除</a></shiro:hasPermission>";
      // 	var up = "<shiro:hasPermission name='evelopers:edit'><a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='upOrDownMaster("+full.id+",2)'>上架</a></shiro:hasPermission>";
       	var down = "<shiro:hasPermission name='ershoufang:down'><a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='upOrDownMaster("+full.id+",3)'>下架</a></shiro:hasPermission>";
    	if(full.status==3){
      	 	return edit+del;
    	}else if(full.status==2){
    		return down;
    	}else{
    		return edit+del;
    	}
      }
	}      
];
//searchFromId, columns,url,dataTable)
	table=drawDatatable("searchFromId",columns,"${ctx}/back/oldHouse/master/page/list","dataTable");
 })

 </script>
 <script src="${ctx}/static/back/oldHouse/master/master_list.js?r=12345"></script>
 <script src="${ctx}/static/back/lib/validate/verify.notify.js"></script>
<script src="${ctx}/static/back/lib/validate/verify.notify.ext.js"></script>