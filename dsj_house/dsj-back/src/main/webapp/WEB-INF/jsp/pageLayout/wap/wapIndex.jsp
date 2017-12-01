<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

			<div class="col-xs-12 padding-foot">
					<div class="white-head">
						<h1 class="page-title">
							<!-- PAGE HEADER -->
							WAP首页设置
						</h1>
						<h4 class="page-title">
							<!-- PAGE HEADER -->
							轮播设置
						</h4>
						<div class="row">
							<c:forEach items="${swiperList }" var="item">
								<div class="col-md-3" id="${item.id}">
			                      	 <c:if test="${item.imageUrl!=null}">
			                       	 	<img name="swiper${item.id}" src="${item.imageUrl}" style="width:210px;height:210px">
		                       	 	</c:if>
			                       	 <c:if test="${item.imageUrl==null}">
			                       	 	<img  name="swiper${item.id}" style="width:210px;height:210px">
			                       	 </c:if>
			                       	 <div class="buttons">
			                       	 	轮播图${item.id }
				                       	 <button class="btn btn-primary" onclick="show(${item.id})">编辑</button>
				                       	 <c:if test="${item.status==1}">
				                       	 	<button class="btn btn-primary" onclick="publishSwiper(${item.id})">发布</button>
				                       	 </c:if>
				                       	 <c:if test="${item.status==2}">
				                       	 	<button class="btn btn-default" onclick="cancleSwiper(${item.id})">取消发布</button>
				                       	 </c:if>
			                       	 </div>			                       	 
	                        	 </div>
	                        </c:forEach>
						</div>
						
						<h4 class="page-title">
							<!-- PAGE HEADER -->
							五个页签
						</h4>
						<div class="row">
							<c:forEach items="${LabelList }" var="item">
								<div class="col-md-2 input_css" id="${item.id}" >
			                      	<form>
									  <div class="form-group">
									    <div class="input-group">
									      <input type="text" id="label${item.id }" class="form-control" value="${item.name }" readonly="readonly">
									   	  <button class="btn btn-primary" type="button" onclick="showLabel(${item.id})">编辑</button>
									    </div>
									  </div>
									  <div class="buttons">
				                       	 <button class="btn btn-primary" type="button" onclick="showType(${item.id})">选择类型</button>
				                       	 <c:if test="${item.status==1}">
				                       	 	<button class="btn btn-primary"  type="button"onclick="publishLabel(${item.id})">发布</button>
				                       	 </c:if>
				                       	 <c:if test="${item.status==2}">
				                       	 	<button class="btn btn-default" type="button">已发布</button>
				                       	 </c:if>
			                       	 </div>	
									</form>
   
									    	 
	                        	 </div>
	                        	 
	                        </c:forEach>
						</div>
						<h4 class="page-title">
							<!-- PAGE HEADER -->
							前两项设置
						</h4>
						<div class="row">
							<div class="col-md-12" style="text-align:center">
							选择要编辑的标签页
							<select onchange="changeLabel(this)">
								<c:forEach items="${LabelList }" var="item">
							 		<option value="${item.id}">${item.name}</option>
	                        	</c:forEach>
							</select>
							</div>
							<div id="container" style="text-align:center">
								<button class="btn btn-primary" type="button" onclick="showSetModal(1)">第一项</button>
								<div id="container_1">
								
								</div>
								<button class="btn btn-primary" type="button" onclick="showSetModal(2)">第二项</button>
								<div id="container_2">
								
								</div>
							</div>
						</div>

		                

					</div>
				</div>
<div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="width: 560px;">
		    <div class="modal-header">
			    <div class="row">
			    	<h4 class="modal-title">编辑</h4>
				</div>
			</div>
	      	<div class="modal-body">
				<div class="col-xs-12 row" >
					<div class="row">
		  				 <form id="authForm" class="form-horizontal col-xs-8 col-xs-offset-2">
							<div class="form-group">
				                <label  class="col-sm-3 control-label">*链接</label>
				                <div class="col-sm-9">
				                  <input name="url"type="text" class="form-control" data-validate="required">
				                </div>
				            </div>
						    <div class="form-group">
				                <label  class="col-sm-3 control-label">添加照片</label>
				                 <div class="col-sm-6">
					                 <img id="photoShowImg" style="width: 180px;height: 180px;"/>
					                 <div id="photoFilePicker" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="imageUrl" style="width: 0px;border: 0px;" type="text" id="photoFileUrl" >
				                 </div>
	          					</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
	        	<button class="btn btn-primary" type="button" onclick="update()">提交</button>
	        	<button class="btn btn-default" data-dismiss="modal">取消</button>
	   	    </div>             
		</div>
	</div>
</div>


<div style="display:none;text-align:center;" class="modal fade" id="labelModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="width: 560px;">
		    <div class="modal-header">
			    <div class="row">
			    	<h4 class="modal-title">编辑</h4>
				</div>
			</div>
	      	<div class="modal-body">
				<div class="col-xs-12 row" >
					<div class="row">
		  				 <form id="labelForm" class="form-horizontal col-xs-8 col-xs-offset-2">
							<div class="form-group">
				                <label  class="col-sm-4 control-label">*页签名称</label>
				                <div class="col-sm-8">
				                  <input name="name" type="text" class="form-control" data-validate="required,maxLength5">
				                </div>
				            </div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
	        	<button class="btn btn-primary" type="button" onclick="updateLabel()">提交</button>
	        	<button class="btn btn-default" data-dismiss="modal">取消</button>
	   	    </div>             
		</div>
	</div>
</div>
<div style="display:none;text-align:center;" class="modal fade" id="typeModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" style="width: 1000px;">
		<div class="modal-content" >
		    <div class="modal-header">
			    <div class="row">
			    	<h4 class="modal-title">选择类型</h4>
				</div>
			</div>
			<form id="typesForm" >
	      	<div class="modal-body">
				<div class="col-xs-12 row form-group" >
					<table id="table" class="table table-bordered">
 						<thead>
 							<tr>
 								<td>组</td>
 								<td>权重占比/10</td>
 								<td>类别</td>
 							</tr>
 						</thead>
 						<tbody>
 						</tbody>
					</table>
				</div>
			</div>
			</form>
			<div class="modal-footer">
				<button class="btn btn-primary" type="button" onclick="reset()">重置</button>
	        	<button class="btn btn-primary" type="button" onclick="updateTypes()">提交</button>
	        	<button class="btn btn-default" data-dismiss="modal">取消</button>
	   	    </div>             
		</div>
	</div>
</div>


<div style="display:none;text-align:center;" class="modal fade" id="setModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" >
		<div class="modal-content"  style="width: 560px;" >
		    <div class="modal-header">
			    <div class="row">
			    	<h4 class="modal-title">选择类型</h4>
				</div>
			</div>
	      	<div class="modal-body">
				<div class="col-xs-12 row" >
					<div class="row">
		  				 <form id="itemForm" class="form-horizontal col-xs-8 col-xs-offset-2">
							<div class="form-group">
				                <label  class="col-sm-3 control-label">*描述</label>
				                <div class="col-sm-9">
				                  <input name="title" type="text" class="form-control" data-validate="required,minLength4,maxLength45">
				                </div>
				            </div>
				            <div class="form-group">
				                <label  class="col-sm-3 control-label">*链接</label>
				                <div class="col-sm-9">
				                  <input name="url" type="text" class="form-control" data-validate="required">
				                </div>
				            </div>
				             <div class="form-group">
				                <label  class="col-sm-8 control-label">注：请输入wap端链接地址</label>
				            </div>
				            <div class="form-group">
				                <label  class="col-sm-3 control-label">*链接</label>
				                <div class="col-sm-9" >
				                  <select class="form-control" name="type" onchange="changeDiv(this)">
				                  		<option value="1">大图+文字</option>
				                  		<option value="2">组图+文字</option>
				                  		<option value="3">小图+文字</option>
				                  		<option value="4">文字链</option>
				                  </select>
				                </div>
				            </div>
				            <div id="show1" name="show" style="display:block">
				            	 <div class="form-group">
				                <label  class="col-sm-3 control-label">添加照片</label>
				                 <div class="col-sm-6">
					                 <img name="photoShowImg" id="photoShowImg5" style="width: 180px;height: 180px;"/>
					                 <div id="photoFilePicker5" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="bigPic" style="width: 0px;border: 0px;" type="text" data-validate="required" id="photoFileUrl5" >
				                 </div>
	          					</div>
				            </div>
				            <div id="show2" name="show" style="display:none">
				            	 <div class="form-group">
				                <label  class="col-sm-3 control-label">添加照片</label>
				                 <div class="col-sm-6">
					                 <img name="photoShowImg" id="photoShowImg1" style="width: 180px;height: 180px;"/>
					                 <div id="photoFilePicker1" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="manyPic" style="width: 0px;border: 0px;" data-validate="required" type="text" id="photoFileUrl1" >
				                 </div>
	          					</div>
	          					 <div class="form-group">
				                <label  class="col-sm-3 control-label">添加照片</label>
				                 <div class="col-sm-6">
					                 <img name="photoShowImg" id="photoShowImg2" style="width: 180px;height: 180px;"/>
					                 <div id="photoFilePicker2" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="manyPic" style="width: 0px;border: 0px;" data-validate="required" type="text" id="photoFileUrl2" >
				                 </div>
	          					</div>
	          					 <div class="form-group">
				                <label  class="col-sm-3 control-label">添加照片</label>
				                 <div class="col-sm-6">
					                 <img name="photoShowImg" id="photoShowImg3" style="width: 180px;height: 180px;"/>
					                 <div id="photoFilePicker3" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="manyPic" style="width: 0px;border: 0px;" data-validate="required" type="text" id="photoFileUrl3" >
				                 </div>
	          					</div>
				            </div>
				            <div id="show3" name="show" style="display:none">
				            	 <div class="form-group">
				                <label  class="col-sm-3 control-label">添加照片</label>
				                 <div class="col-sm-6">
					                 <img name="photoShowImg" id="photoShowImg4" style="width: 180px;height: 180px;"/>
					                 <div id="photoFilePicker4" style="display: inline-block;"> 
					     				 <button class="btn btn-primary" type="button" >选择图片</button>            
					                 </div>
					                 <input name="smallPic" style="width: 0px;border: 0px;" data-validate="required" type="text" id="photoFileUrl4" >
				                 </div>
	          					</div>
				            </div>
				            <div id="show4" name="show" style="display:none">
				            	
				            </div>
				            <div class="form-group">
				                <label  class="col-sm-3 control-label">备注</label>
				                <div class="col-sm-9">
				                  <input name="remark" type="text" class="form-control" placeholder="来源或开盘时间">
				                </div>
				            </div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
	        	<button class="btn btn-primary" type="button" onclick="updateItem()">提交</button>
	        	<button class="btn btn-default" data-dismiss="modal">取消</button>
	   	    </div>             
		</div>
	</div>
</div>
<!-- 未设置 -->
<script id="content_0" type="text/x-handlebars-template">
    未设置
</script>
<!-- 大图+文字 -->
<script id="content_1" type="text/x-handlebars-template">

   {{title}}
<img src="{{picture}}" style="width: 280px;height: 280px;"/>
{{{remark}}}
{{{button status id}}}
</script>
<!-- 组图+文字 -->
<script id="content_2" type="text/x-handlebars-template">

	{{title}}
	{{#each list}}
	<img  src="{{this}}" style="width: 180px;height: 180px;"/>
	{{/each}}
{{{remark}}}
{{{button status id}}}
</script>
<!-- 小图+文字 -->
<script id="content_3" type="text/x-handlebars-template">

{{title}}
    <img src="{{picture}}" style="width: 180px;height: 180px;"/>
{{{remark}}}
{{{button status id}}}

</script>
<!-- 文字链 -->
<script id="content_4" type="text/x-handlebars-template">

   {{title}}
{{{remark}}}
{{{button status id}}}

</script>
   



<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>
<script src="${ctx}/static/back/lib/handlebars/handlebars.js"></script>
<script type="text/javascript">
Handlebars.registerHelper({  
    "button" : function(status,id){
       if(status==1){
    	   return "<button class='btn btn-primary' type='button' onclick='publishItem("+id+")'>发布</button>"
       }else{
    	   return "<button class='btn btn-default' onclick='canclePublishItem("+id+")' type='button'>取消发布</button>"
       }
    },
  
}) 

var index = '';
var labelIndex = 1;
var id = '';
var setIndex = "";
function showSetModal(param){
	setIndex = param;
	$("#setModal").modal({
		backdrop: true,
	    keyboard: false,
	    show: true
    });
	$("input[name='photoShowImg']").removeAttr('src');
	$("input[name='picture1']").val('');
	$("input[name='picture2']").val('');
	$("input[name='picture3']").val('');
	$("input[name='url']").val('');
	$("input[name='title']").val('');
	$("input[name='remark']").val('');
	$('#setModal').on('shown.bs.modal', function (e) {
		//singleUpload("photoFilePicker5","photoFileUrl5","photoShowImg5",beforeUpload,afterUpload);
		if(tempItemArr[index].type==2){
			singleUpload("photoFilePicker1","photoFileUrl1","photoShowImg1",beforeUpload,afterUpload);
			singleUpload("photoFilePicker2","photoFileUrl2","photoShowImg2",beforeUpload,afterUpload);
			singleUpload("photoFilePicker3","photoFileUrl3","photoShowImg3",beforeUpload,afterUpload);
			
		}else if(tempItemArr[index].type==3){
			singleUpload("photoFilePicker4","photoFileUrl4","photoShowImg4",beforeUpload,afterUpload);
		}else if(tempItemArr[index].type==1){
			singleUpload("photoFilePicker5","photoFileUrl5","photoShowImg5",beforeUpload,afterUpload);
		}
	})
	
	//回显
	var index = param-1;
	$("select[name=type]").val(tempItemArr[index].type);
	$("div [name=show]").css("display","none");
	$("#show"+tempItemArr[index].type).css("display","block");
	
	
	
	
	if(tempItemArr[index].type==1){
		$("input[name='title']").val(tempItemArr[index].title);
		$("input[name='remark']").val(tempItemArr[index].remark);
		$("input[name='url']").val(tempItemArr[index].url);
		$("#photoShowImg5").attr("src",tempItemArr[index].picture);
		$("input[name='bigPic']").val(tempItemArr[index].picture);
	}else if(tempItemArr[index].type==2){
		$("input[name='title']").val(tempItemArr[index].title);
		$("input[name='remark']").val(tempItemArr[index].remark);
		$("input[name='url']").val(tempItemArr[index].url);
		if(tempItemArr[index].picture!=null&&tempItemArr[index].picture!=''){
			$("#photoShowImg1").attr("src",tempItemArr[index].picture.split(',')[0]);
			$("#photoShowImg2").attr("src",tempItemArr[index].picture.split(',')[1]);
			$("#photoShowImg3").attr("src",tempItemArr[index].picture.split(',')[2]);
			
			$("input[name='manyPic']").eq(0).val(tempItemArr[index].picture.split(',')[0]);
			$("input[name='manyPic']").eq(1).val(tempItemArr[index].picture.split(',')[1]);
			$("input[name='manyPic']").eq(2).val(tempItemArr[index].picture.split(',')[2]);
		}
	}else if(tempItemArr[index].type==3){
		$("input[name='title']").val(tempItemArr[index].title);
		$("input[name='remark']").val(tempItemArr[index].remark);
		$("input[name='url']").val(tempItemArr[index].url);
		$("#photoShowImg4").attr("src",tempItemArr[index].picture);
		$("input[name='smallPic']").val(tempItemArr[index].picture);
	}else if(tempItemArr[index].type==4){
		$("input[name='title']").val(tempItemArr[index].title);
		$("input[name='remark']").val(tempItemArr[index].remark);
		$("input[name='url']").val(tempItemArr[index].url);
	}
	
}
function changeLabel(obj){
	labelIndex = $(obj).val();
	request(labelIndex);
}

var itemType = '';//推荐 类型
function changeDiv(obj){
	itemType = $(obj).val();
	$("div [name=show]").css("display","none");
	$("#show"+itemType).css("display","block");
	
	if(itemType==2){
		singleUpload("photoFilePicker1","photoFileUrl1","photoShowImg1",beforeUpload,afterUpload);
		singleUpload("photoFilePicker2","photoFileUrl2","photoShowImg2",beforeUpload,afterUpload);
		singleUpload("photoFilePicker3","photoFileUrl3","photoShowImg3",beforeUpload,afterUpload);
		
	}else if(itemType==3){
		singleUpload("photoFilePicker4","photoFileUrl4","photoShowImg4",beforeUpload,afterUpload);
	}else if(itemType==1){
		singleUpload("photoFilePicker5","photoFileUrl5","photoShowImg5",beforeUpload,afterUpload);
	}
	
}

$(function(){
	request(1);
})

var item_id_1='';
var item_id_2='';
function request(param){
	$.ajax({
		type:"post",
		async:true,
		data:{
			labelIndex:param
		},
		dataType:"json",
		url:_ctx+"/back/frame/wapIndex/getItem",
		success:function(result){
			if(result.status!=200){
			 setErrorContent(result.message);
			}else{
				render(result.data);
			}
		}
	})
}


var tempItemArr = [];
function render(data){
	tempItemArr = data;
	var source="";
	var option={};
	
	
	for(var i=0;i<data.length;i++){
		option = data[i];
		if(data[i].type==0){
			source = $("#content_"+data[i].type).html();
		}else if(data[i].type==1){
			source = $("#content_"+data[i].type).html();
		}else if(data[i].type==2){
			source = $("#content_"+data[i].type).html();
			option.list=data[i].picture.split(",");
		}else if(data[i].type==3){
			source = $("#content_"+data[i].type).html();
		}else if(data[i].type==4){
			source = $("#content_"+data[i].type).html();
		}

		var template = Handlebars.compile(source);
		var html = template(option);
		
		if(i==0){
			$("#container_1").empty();
			$("#container_1").prepend(html);
			item_id_1 = data[i].id;
		}else{
			$("#container_2").empty();
			$("#container_2").prepend(html);
			item_id_2 = data[i].id;
		}
	}
}

function updateItem(){
	var id = '';
	
	if(setIndex==1){
		id=item_id_1;
	}else{
		id=item_id_2;
	}

	 $("#itemForm").validate(function (result) {
		  	if(result){
		  		$('#setModal').modal('hide');
		  		$.ajax({
		  			type:"post",
		  			async:true,
		  			data:$("#itemForm").serialize()+"&id="+id,
		  			dataType:"json",
		  			url:_ctx+"/back/frame/wapIndex/updateItem",
		  			success:function(result){
		  				if(result.status!=200){
							 setErrorContent(result.message);
						}else{
							request(labelIndex);
						}
		  			}
		  		})
		  	}
	 })
}

function publishItem(id){
	$.ajax({
			type:"post",
			async:true,
			data:{
				id:id
			},
			dataType:"json",
			url:_ctx+"/back/frame/wapIndex/publishItem",
			success:function(result){
				if(result.status!=200){
				 setErrorContent(result.message);
				}else{
					request(labelIndex);
				}
			}
		})
}
function canclePublishItem(id){
	$.ajax({
			type:"post",
			async:true,
			data:{
				id:id
			},
			dataType:"json",
			url:_ctx+"/back/frame/wapIndex/canclePublishItem",
			success:function(result){
				if(result.status!=200){
				 setErrorContent(result.message);
				}else{
					request(labelIndex);
				}
			}
		})
}

function cancleSwiper(param){
	$.ajax({
		type:"post",
		async:true,
		data:{
			id:param
		},
		dataType:"json",
		url:_ctx+"/back/frame/wapIndex/cancleSwiper",
		success:function(result){
			if(result.status!=200){
			 setErrorContent(result.message);
			}else{
				location=_ctx+"/back/frame/wapIndex/indexManage";
			}
		}
	})
}


</script>
<script src="${ctx}/static/back/wap/wapIndex.js"></script>