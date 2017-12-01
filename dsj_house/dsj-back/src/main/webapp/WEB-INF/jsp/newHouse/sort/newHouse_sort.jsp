<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/static/front/css/rowReorder.dataTables.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<div class="col-xs-12 row">
                    <div class="white-head">
                        <h1 class="page-title txt-color-blueDark">
                            <!-- PAGE HEADER -->
                        楼盘排序
                        </h1>

                    </div>
                    <div class="white-content">
                        <table id="example" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>楼盘位置</th>
                                    <th>楼盘ID</th>
                                    <th>楼盘名称</th>
                                    <th>楼盘地址</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sortList}" var="item">
				                 <tr>
                                    <td>${item.sortVal }</td>
                                    <td>${item.loupanId}</td>
                                    <td>${item.loupanName}</td>
                                    <td>${item.address}</td>
                                    <td>
                                    <c:if test="${item.id==null}">
                                           <shiro:hasPermission name='newHouse:model'> <a class='dsj_btn btn btn-default'  href='javascript:void(0);'id="${item.id}" onclick="openCommonModal('${ctx}/back/newHouse/sort/toSetUp?id=${item.sortVal}',1000)">设置楼盘</a></shiro:hasPermission>
                                      </c:if>
                                      <c:if test="${item.id!=null}">  
                                            <shiro:hasPermission name='newHouse:up'> <a class='dsj_btn btn btn-default'  href='javascript:void(0);'id="${item.id}" onclick="openCommonModal('${ctx}/back/newHouse/sort/toSetUp?id=${item.sortVal}',1000)">更换楼盘</a></shiro:hasPermission> 
                                            <shiro:hasPermission name='newHouse:cancle'> <a class='dsj_btn btn btn-default'  href='javascript:void(0);'id="${item.id}" onclick="cancel('${item.sortVal}','${item.loupanId}')">取消</a></shiro:hasPermission>
                                      </c:if>
                                    </td>
                                </tr>	 	
				             </c:forEach>
                                
                            </tbody>
                        </table>
                    </div>
                </div>     
<script type="text/javascript">
        var id = '';
        var id_replace = '';
        var sort = "";
        var loupanID="";
        // DO NOT REMOVE : GLOBAL FUNCTIONS!
        
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
            table.on( 'row-reorder', function ( e, diff, edit ) {
            	setTimeout(function(){
            		var array = new Array(20);
            		for(var i=0;i<20;i++){
            			var item = {};
            			item.sortVal = $('tr:eq('+i+')').find('td:eq(0)').text();
            			item.loupanId = $('tr:eq('+i+')').find('td:eq(1)').text();
            			item.id = $('tr:eq('+i+')').find('td:eq(4)').find('a').attr('id');
            			array[i] = item;
            		}
            		changeMore(array);
            	},
            		2000	);

            } );
            
            pageSetUp();
            
        })
        

function cancel(param,ddd){
	sort=param;
	loupanID=ddd;
	setModalContent("确认取消吗？","replaceModalConfirm");
	
}


$("body").on("click","#replaceModalConfirm",function(){
	$("#popup_box").show();
	$.ajax({
		type:"post",
		url:_ctx+"/back/frame/newHouse/sort/delSort",
		data:{
			sort:sort,
	        loupanId:loupanID
		},
		datatype:"json",
		success:function(result){
			if(result.status!=200){
				$("#popup_box").hide();
				 setErrorContent(result.message);
			}else{
					location=_ctx+"/back/frame/newHouse/sort/newHouse_sort";
			}
		}
	})
  
});
function changeMore(array){
	$("#popup_box").show();
	$.ajax({
		type:"post",
		url:_ctx+"/back/newHouse/sort/changeMore",
		
		data:JSON.stringify(array),
		datatype:"json",
		contentType : 'application/json',
		success:function(result){
			$("#popup_box").hide();
		}
	})
}

</script>
<script src="${ctx}/static/back/js/common/dataTables.rowReorder.min.js"></script>
