<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		
		<ol class="breadcrumb">
			<li>二手房房源</li>
			<li>详情 </li>
		</ol>
	
		<div class="row" style="margin: 20px;">
						  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
		       <li role="presentation" ><a href="${ctx}/back/frame/oldHouse/master/master_view?id=${id}" >房源信息</a></li>
			  <li role="presentation" class="active"><a  href="${ctx}/back/frame/oldHouse/master/master_image_list_view?id=${id}" >房源图片</a></li>
						
		  </ul>
		<form id="addForm">
		  
		<div class="row">
		       <div class="row col-xs-8 col-xs-offset-2">
		        <div class="font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 上传室内图</div>
		   		
		   		<c:forEach  var="image" items="${insideImages}">
		   		 <div class="col-xs-3">
		   		 		<input class="dsj_pic_checkbox" value="${image.id}"  name="checkbox_name" type="checkbox" >
				      <img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${image.pictureUrl}">
				      	<p class="dsj_img_single_action">
						
				      <input type="hidden" value="${image.id}"  />
				 </div>
		   		</c:forEach>
		   		
		        
		   	 </div>
		    <div class="row col-xs-8 col-xs-offset-2">
		        <div class="font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 上传户型图</div>
		      	<c:forEach  var="image" items="${houseTypeImages}">
		   		 <div class="col-xs-3">
		   		  	
				      <img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${image.pictureUrl}">
				      	<p class="dsj_img_single_action">
				      	
					</p>
					<input type="hidden" value="${image.id}"  />
				 </div>
				</c:forEach>
		        <
		    </div>
		   
		    <div class="row col-xs-8 col-xs-offset-2"">
		         <div class="font16"><i class="fa fa-caret-right blue-font" aria-hidden="true"></i> 上传室外图</div>
		       	<c:forEach  var="image" items="${outInsideImages}">
		   		 <div class="col-xs-3">
		   		  	
				      <img class="dsj_img img-rounded img-responsive" alt="Responsive image" src="${image.pictureUrl}">
				      	<p class="dsj_img_single_action">
				      
						<input type="hidden" value="${image.id}"  name="id" />
				 </div>
				</c:forEach>
		       
		    </div>
	  </div>
	 
	 </form>
	 </div>
	   <div class="text-center">
        
           <button class="btn btn-primary" type="button" onclick="goList()">返回列表</button>
	    </div>
  </div>
</div>
<script type="text/javascript">
var upload_type=1;
</script>
 <script src="${ctx}/static/back/oldHouse/master/master_image_list.js"></script>
