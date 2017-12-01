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
					<select  id="areaTwoId" class="form-control dsj-inline"  name="areaCode2"  onchange="getThreeArea()">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="form-group mr20">
					<span class="wenzi6">区:</span>
					<select  id="areaThreeId" class="form-control dsj-inline "  name="areaCode3"  onchange="getFourArea()">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="form-group mr20">
					<span class="wenzi6">商圈:</span>
					<select  id="areaFourId" class="form-control dsj-inline " name="tradingAreaId">
						<option value="">请选择</option>
					</select>
				</div>
                <div class="form-group mr20">
				        <span class="wenzi6">小区名称或者房源ID:</span>
				         <input type="text" class=" form-control " placeholder="小区名称或者房源ID" name="name"  style="width: 150px;">
				         
                </div>
                 <div class="form-group mr20">
                 <span class="wenzi6" style="width:100px;">状态:</span>
	                  	  <select name="status" class="form-control" >
							     <option value="">全部</option>
							     <option value="1">未上架</option>
							     <option value="">上架</option>
							     <option value="2">下架</option>
						 </select>
                </div>
               <div class="form-group mt20">
			    		 <span class="wenzi6">面积:</span>
						<input class="small-input9 form-control" name="buildAreaMin" type="text" placeholder="最小面积">
						<span class="wenzi2">~</span>
						<input class="small-input9 form-control" name="buildAreaMax" type="text" placeholder="最大面积">
					
			    		 <span class="wenzi6">价格:</span>
						<input class="small-input9 form-control" name="priceMin" type="text" placeholder="最小价格">
						<span class="wenzi2">~</span>
						<input class="small-input9 form-control" name="priceMax" type="text" placeholder="最大价格">
						 
						 <span class="wenzi6">房型:</span>
						<input class="small-input9 form-control" name="roomMin"   type="text" >
						<span class="wenzi2">~</span>
						<input class="small-input9 form-control" name="roomMax"  type="text" >室
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
			   		 
			   		  <shiro:hasPermission name='master_recycle:deletes'>
	                  	<button class="dsj_btn btn btn-default" type="button" onclick="recycleMaster()">批量恢复</button>
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
		 return  "<input name='checkitem' value='"+full.id+"' type='checkbox'  />";
	}
  	}, {
	"title":"创建时间",
	"mData" : "createTime"
	}, {
	"title":"房源ID",
	 "mRender":function(data,type,full){
			
		 return "<a href='"+_ctx+"/back/frame/oldHouse/master/master_view?id="+full.id+"''>"+full.houseId+"</a>";
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
	"title":"操作",
      "mRender":function(data,type,full){

    	var recycle = "<shiro:hasPermission name='master_recycle:delete'><a class='dsj_btn btn btn-default dsj_btn_width' href='javascript:void(0);' onclick='recycleMaster("+full.id+")'>恢复</a></shiro:hasPermission>";
     	return recycle;
      }      
	}      
];
//searchFromId, columns,url,dataTable)
	table=drawDatatable("searchFromId",columns,"${ctx}/back/oldHouse/recycle/master/page/list","dataTable");
 })

 </script>
 <script src="${ctx}/static/back/oldHouse/master/master_recycle_list.js"></script>