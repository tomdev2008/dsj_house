<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<div class="col-xs-12 padding-foot">
	<div class="white-head">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2">
		           <div class="form-group">
		                <label  class="col-sm-2 control-label">订&nbsp;&nbsp;单&nbsp;&nbsp;号：</label>${detailVo.orderNo }
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">购&nbsp;&nbsp;买&nbsp;&nbsp;人：</label>
		                <c:choose>
		                	<c:when test="${detailVo.username==null}">无</c:when>
		                	<c:otherwise>${detailVo.username}</c:otherwise>
		                </c:choose>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">手&nbsp;&nbsp;机&nbsp;&nbsp;号：</label>${detailVo.phone }
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品：</label>${detailVo.productName }
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">下单时间：</label><fmt:formatDate value='${detailVo.createTime }' pattern='yyyy-MM-dd hh:mm:ss'/>
		            </div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">支付金额：</label>${detailVo.payment }
		            </div>
		            
		            <br><br><br>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">评&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</label>
		                <div class="col-sm-6">
		                	<c:choose>
		                		<c:when test="${fwuserCommentVo.type==1 }">差评</c:when>
		                		<c:when test="${fwuserCommentVo.type==2 }">中评</c:when>
		                		<c:when test="${fwuserCommentVo.type==3 }">好评</c:when>
		                		<c:otherwise>无</c:otherwise>
		                	</c:choose>
		                </div>
		            </div>
		            <br><br>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签：</label>
		                <div class="col-sm-6">
		                	<c:if test="${fwuserCommentVo.label=='' }">无&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('1') }">服务专业&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('2') }">经验富足&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('3') }">流程清晰&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('4') }">踏实稳重&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('5') }">认真严谨&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('6') }">礼貌待客&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('7') }">诚信正直&nbsp;&nbsp;</c:if>
		                	<c:if test="${fwuserCommentVo.label.contains('8') }">办事高效&nbsp;&nbsp;</c:if>
		                </div>
		            </div>
		            <br><br>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">感&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;受：</label>
		                <div class="col-sm-6">
		                	<textarea rows="4" cols="70" style="BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;"><c:choose><c:when test="${fwuserCommentVo.content != '' }">${fwuserCommentVo.content }</c:when><c:otherwise>无</c:otherwise></c:choose></textarea>
		                </div>
		            </div>
		        </div>
		    </div>
	</div>
</div>

