<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/static/front/css/rowReorder.dataTables.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<div class="col-xs-12 row">
                    <div class="white-head">
                        <h1 class="page-title txt-color-blueDark">
                            <!-- PAGE HEADER -->
                        经纪人排序
                        </h1>

                    </div>
                    <div class="white-content">
                        <table id="example" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Seq.</th>
                                    <th>经纪人ID</th>
                                    <th>姓名</th>
                                    <th>电话</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Seq.</th>
                                    <th>经纪人ID</th>
                                    <th>姓名</th>
                                    <th>电话</th>
                                    <th>操作</th>
                                </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${sortList }" var="item">
				                 <tr>
                                    <td>${item.sort }</td>
                                    <td>${item.agentCode }</td>
                                    <td>${item.name}</td>
                                    <td>${item.tellPhone }</td>
                                    <td>
                                    	<c:if test="${item.name==null}">
                                    		<shiro:hasPermission name='sort:add'><button onclick="show()"  class="use" id="${item.id }" type="button"  >添加</button></shiro:hasPermission>
                                    		
                                    	</c:if>
                                    	<c:if test="${item.name!=null}">
                                    		<shiro:hasPermission name='sort:change'><button onclick="show()" class="use" id="${item.id }" type="button"  >更换</button></shiro:hasPermission>
                                    		<shiro:hasPermission name='sort:cancle'><button onclick="cancleSort(${item.id })"  class="use" id="${item.id }" type="button"  >取消</button></shiro:hasPermission>
                                    	</c:if>
                                    </td>
                                </tr>	 	
				             </c:forEach>
                                
                                
                                
                                
                            </tbody>
                        </table>
                    </div>
                </div>    
<div style="display:none;text-align:center;" class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog modal-sm">
                   <div class="modal-content" style="width: 400px;">
                      <div class="modal-header">
	           
				      </div>
				      <div class="modal-body">
							<div class="col-xs-12 row" >
								<div class="row">
					  				
										 <div class="form-group">
									        <div class="col-sm-6">
									          <input type="text" name="name" placeholder="请输入经纪人姓名" id="content">
									        </div>
									        <button class="btn btn-primary" onclick="search()">查找</button>
									    </div>
									    <div id='error' style='color:red'></div>
									
										<table id="table" class="table">
									      
									      <thead>
									        <tr>
									          <th>经纪人编号</th>
									          <th>姓名</th>
									          <th>电话</th>									         
									        </tr>
									      </thead>
									      <tbody>
									        
									      </tbody>
									    </table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
				       <button class="btn btn-primary" type="button" onclick="choose()">提交</button>
				        <button class="btn btn-default" onclick="cancle()"data-dismiss="modal">取消</button>
				    </div>
                       
                   </div>
               </div>
</div> 
<script type="text/javascript">
        var id = '';
        var id_replace = '';
        var sort = "";
        var text ="";
        // DO NOT REMOVE : GLOBAL FUNCTIONS!
        jQuery('.use').on('click', function() {
        	id = $(this).attr('id');
            text = $(this).text();
            sort = $(this).parents('tr').find('.sorting_1').text();

       	});
        
        $(document).ready(function() {
        	
            var table = $('#example').DataTable( {
                rowReorder: {
                },
                paging:false,
                searching:false,
                columnDefs: [
                    { orderable: true, className: 'reorder', targets: 0 },
                    { orderable: false, targets: '_all' }
                ]
            } );
            
            /*
            for ( var i=0, ien=diff.length ; i<ien ; i++ ) {
            console.log($(diff[i].node).find('td:eq(0)').text());
            var rowData = table.row( diff[i].node ).data();
 
            result += rowData[1]+' updated to be in position '+
                diff[i].newData+' (was '+diff[i].oldData+')<br>';
        }
            
            setTimeout(function(){var array = new Array(20);
        		for(var i=2;i<22;i++){
        			var item = {};
        			item.sort = $('tr:eq('+i+')').find('td:eq(0)').text();
        			item.agentCode = $('tr:eq('+i+')').find('td:eq(1)').text();
        			item.id = $('tr:eq('+i+')').find('td:eq(4)').find('button').attr('id');
        			array[i-2] = item;
        		}
        		changeMore(array);
        		},2000)
            */
            table.on( 'row-reorder', function ( e, diff, edit ) {
            	var begin = edit.triggerRow.data()[0];
            	var array = [];
            	for ( var i=0, ien=diff.length ; i<ien ; i++ ) {
            		//var rowData = table.row( diff[i].node ).data();
            		var item = {};
					item.id = $(diff[i].node).find('td:eq(4)').find('button').attr('id');                   
         			item.newSort = diff[i].newData;
         			item.oldSort = diff[i].oldData;
					if(begin == diff[i].oldData){
						item.start = 1;
					}else{
						item.start = 0;
					}
					array.push(item);
                }
            	if(diff.length>0){
            		changeMore(array);	
            	}
            	
            } );
            
            pageSetUp();
            
        })
function show(){

	$("#authModal").modal({
		backdrop: 'static',
	    keyboard: false,
	    show: true
    });
}
function cancle(){
	$('#content').val("");
	$('tr[name=row]').remove();
}
function search(){
	if($('#content').val()==''){
   		$('#error').text("请输入经纪人姓名");
   		setTimeout(function(){
   			$('#error').text("");
   		},2000);
   	}else{
   		$('tr[name=row]').remove();
   		$.ajax({
   			type:"post",
   			url:_ctx+"/back/frame/system/agent/search",
   			data:{
   				name:$('#content').val()
   			},
   			datatype:"json",
   			success:function(result){
   				if(result.status!=200){
   					 setErrorContent(result.message);
   				}else{
   					if(result.data.length==0){
   						setErrorContent("无搜索结果");
   					}else{
   						var list = result.data;
   					
   						for(var i=0;i<list.length;i++){
   							$("#table").append("<tr name='row' onclick='getId(this)'><th id="+list[i].id+">"+list[i].agentCode+"</th><th>"+list[i].name+"</th><th>"+list[i].tellPhone+"</th></tr>")
   						}
   					
   						
   					}   					
   					
   				}
   			}
   		})
   	}
}
function choose(){
	//判断是否选中
	
	
	if(id_replace == ''){
		setErrorContent("请选择目标经济人");
	} else{
		$('#content').val("");
		$('tr[name=row]').remove();
		$("#authModal").hide();
		
		if(text=="添加"){
			addSort();
		}else{
			replace();
		
		}
	}
	
}
function getId(obj){
	id_replace = $(obj).find('th:eq(0)').attr('id');
	$(obj).css("color","blue");
}
function replace(){

	setModalContent("确认更换吗？","replaceModalConfirm");
	
}

function addSort(){
	setModalContent("确认添加吗？","addSortModalConfirm");
	
}

$("body").on("click","#replaceModalConfirm",function(){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/system/agent/replace",
		data:{
			id:id,
			idReplace:id_replace,
			sort:sort
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				modalSuccess(result.message);

					location=_ctx+"/back/frame/system/agent/agentSort";

			}
		}
	})
  
});
$("body").on("click","#addSortModalConfirm",function(){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/system/agent/addSort",
		data:{
			idReplace:id_replace,
			sort:sort
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				modalSuccess(result.message);

					location=_ctx+"/back/frame/system/agent/agentSort";

			}
		}
	})
  
});
$("body").on("click","#cancleSortModalConfirm",function(){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/system/agent/cancleSort",
		data:{
			agentId:cancleId
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				modalSuccess(result.message);

					location=_ctx+"/back/frame/system/agent/agentSort";

			}
		}
	})
  
});
function changeMore(array){
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/system/agent/changeMore",
		
		data:JSON.stringify(array),
		datatype:"json",
		contentType : 'application/json',
		success:function(result){
			if(result.status!=200){
				 setErrorContent(result.message);
			}else{
				
//					location=_ctx+"/back/frame/system/agent/agentSort";

			}
		}
	})
}
var cancleId='';
function cancleSort(id){
	cancleId=id;
	setModalContent("确认取消吗？","cancleSortModalConfirm");
}
</script>
<script src="${ctx}/static/back/system/agent/dialog.js"></script>
<script src="${ctx}/static/back/js/common/dataTables.rowReorder.min.js"></script>
