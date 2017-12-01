<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/static/front/css/rowReorder.dataTables.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<div class="col-xs-12 row">
                    <div class="white-head">
                        <h1 class="page-title txt-color-blueDark">
                        绑定详情
                        </h1>
                    </div>
                    <div class="white-content">
                        <table id="example" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>IM位置</th>
                                    <th>经纪人ID</th>
                                    <th>经纪人姓名</th>
                                    <th>手机号</th>
                                    <th>经纪公司</th>
                                    <th>操作</th>
                                    <th style="display:none;">位置调整</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${voList}" var="item">
				                 <tr>
                                    <td>${item.position}</td>
                                    <td>${item.agentCode}</td>
                                    <td>${item.agentName}</td>
                                    <td>${item.tellPhone}</td>
                                    <td>${item.company}</td>
                                    <td>
	                                    <c:if test="${item.isDuty==0}">
	                                    	<a class='dsj_btn btn btn-default' href='javascript:void(0);' id="${item.id}" onclick="openCommonModal('${ctx}/back/im/directory/bind_add?id=${item.id}',1000)">绑定</a>
	                                        <a class='dsj_btn btn btn-default' href='javascript:void(0);' id="${item.id}" onclick='cancel(${item.id})'>解绑</a>
	                                    </c:if>
	                                    <c:if test="${item.isDuty==1}">
	                                    	<a href='javascript:void(0);' id="${item.id}"></a>
	                                    </c:if>
	                                    <c:if test="${item.isDuty==2}">
	                                    	<a class='dsj_btn btn btn-default' href='javascript:void(0);' id="${item.id}" onclick="openBindAddPage(${item.houseId}, ${item.position})">绑定</a>
	                                    </c:if>
                                    </td>
                                    <td style="display:none;">${item.position}</td>
                                </tr>
				             </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>     
<script type="text/javascript">
     var id = '';
     $(document).ready(function() {
         var table = $('#example').DataTable({
        	 bLengthChange: false,
        	 bFilter: false, 
             rowReorder: true,
             paging: false, // 禁止分页
             bInfo: false,
             columnDefs: [
                 {orderable: true, className: 'reorder', targets: 0},
                 {orderable: false, targets: '_all'}
             ]
         });
         table.on('row-reorder', function (e, diff, edit) {
         	setTimeout(function(){
         		var array = new Array(10);
         		for(var i=1; i<12; i++){
         			var item = {};
         			item.position = $('tr:eq('+i+')').find('td:eq(0)').text();
         			item.id = $('tr:eq('+i+')').find('td:eq(5)').find('a').attr('id');
         			if (item.id) {
         				array[i-1] = item;
         			}
         		}
         		bindSort(array);
         	}, 2000);
         } );
         pageSetUp();
     });

	function bindSort(array){
		$.ajax({
			type : "post",
			url : _ctx + "/back/im/directory/bind_sort",
			data : JSON.stringify(array),
			datatype : "json",
			contentType : 'application/json',
			success : function(result){
				if(result.status!=200){
					setErrorContent(result.message);
				}else{
					//location.reload();
				}
			}
		})
	}
	
	function cancel(id){
		Window.id = id;
		setModalContent("确认解绑吗？", "replaceModalConfirm");
	}
	
	$("body").on("click","#replaceModalConfirm",function(){
		$.ajax({
			type : "post",
			url : _ctx+"/back/frame/im/directory/bind_delete",
			data : {
				id : Window.id
			},
			datatype : "json",
			success : function(result){
				if(result.status!=200){
					 setErrorContent(result.message);
				}else{
					location.reload();
				}
			}
		})
	  
	});
	
	function openBindAddPage(houseId, position){
		var posNew = 0;
		var code = '';
		for(var i=1; i<12; i++){
 			pos = $('tr:eq('+i+')').find('td:eq(0)').text();
 			posOrigin = $('tr:eq('+i+')').find('td:eq(6)').text();
 			if (posOrigin == position) {
 				posNew = pos;
 				break;
 			}
 		}
		var url = '${ctx}/back/im/directory/bind_add?houseId=' + houseId 
				+ '&position=' + posNew;
		openCommonModal(url,1000);
	}
</script>
<script src="${ctx}/static/back/js/common/dataTables.rowReorder.min.js"></script>
