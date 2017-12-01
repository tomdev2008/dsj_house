<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

    <body class="container-fluid">
            <div id="content" class="col-xs-12">
               <div>
               <form class="form-horizontal" id="allRole_form">
                  <ul class="nav nav-tabs" role="tablist">
                    <c:forEach items="${functionList}" var="function" varStatus="functionStatus">
                     <c:if test="${functionStatus.index==0}">
   <li role="presentation" class="active"><a href="#${function.id}" aria-controls="${function.id}" role="tab" data-toggle="tab">${function.name}</a></li><input type="hidden" name="functionListId" value="${function.id}">
                     </c:if>
                     <c:if test="${functionStatus.index!=0}">
                        <li role="presentation"><a href="#${function.id}" aria-controls="${function.id}" role="tab" data-toggle="tab">${function.name}</a></li><input type="hidden" name="functionListId" value="${function.id}">
                     </c:if>
                    </c:forEach>
                  </ul>

                  <!-- Tab panes -->
                  <div class="tab-content">
                    <c:forEach items="${functionList}" var="fun" varStatus="funStatus">
                    <c:if test="${funStatus.index==0}">
                       <div role="tabpanel" class="tab-pane active" id="${fun.id}" title="${fun.name}">
                    </c:if>
                    <c:if test="${funStatus.index!=0}">
                        <div role="tabpanel" class="tab-pane" id="${fun.id}" title="${fun.name}">
                    </c:if>
                        <c:forEach items="${fun.children}" var="func">	
                         <input type="hidden" value="${roleId }" name="roleId"/>
                         	<table id="house_condition" class="table table-bordered table-striped" style="margin-bottom:-1px;">
						      <colgroup>
						        <col class="col-xs-2">
						        <col class="col-xs-10">
						      </colgroup>
						      <tbody>
						        <tr>
						          <th scope="row">
						            <label class="checkbox-inline">
                                       <c:choose>
										<c:when test="${func.checked == true }">
											<div class="check cur"><span></span></div>
									 	 	<input   name="functionListId" type="checkbox" checked="checked" value="${func.id }"/>${func.name}
										</c:when>
										<c:otherwise>
											<div class="check"><span></span></div>
									 	 	<input   name="functionListId" type="checkbox" value="${func.id }"/>${func.name}
										</c:otherwise>
									</c:choose>
                                    </label>
						          </th>
						          <td>
						             <c:forEach items="${func.children}" var="funct">
                                        <label class="checkbox-inline btns">
                                       <c:choose>
										<c:when test="${funct.checked == true }">
											<div class="check cur"><span></span></div>
									 	 	<input   name="functionListId" type="checkbox" checked="checked" value="${funct.id }"/>${funct.name}
										</c:when>
										<c:otherwise>
											<div class="check"><span></span></div>
									 	 	<input   name="functionListId" type="checkbox" value="${funct.id }"/>${funct.name}
										</c:otherwise>
										</c:choose>
	                                    </label>
                                     </c:forEach>
                                     <div class="pull-right">
                                       <label class="checkbox-inline">
                                       <div class="check"><span></span></div>
                                        <input onclick="selectAll(this);" type="checkbox"  id="selectId"> 全选/全取消
                                       </label>
                                     </div>
								  </td>
						        </tr>
					          </tbody>
					        </table>
                      </c:forEach>
                       </div>
                     </c:forEach>
                  </div>
                   </form>
                  <div class="row">
                    <div class="col-xs-4 col-xs-offset-4 text-center">
                        <button type="button" class="btn btn-primary" onclick="mysubmit()" id="submitId">提交</button>
                        <button type="button" class="btn btn-default" onclick="closeFunc()">关闭</button>
                    </div>
                   </div>
                </div>
            </div>
        </div>
   <script src="${ctx}/static/back/system/roleFunction/function.js"></script>
    </body>
