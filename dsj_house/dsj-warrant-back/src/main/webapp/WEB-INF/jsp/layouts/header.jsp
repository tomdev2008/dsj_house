<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>

			<!-- RIBBON -->
<div id="ribbon">

				<!-- You can also add more buttons to the
				ribbon for further usability

				Example below:-->

	<div class="row top-right">
					<div class="pull-right"><a href="${ctx}/login/logout">安全退出</a></div>
					<div class="pull-right"><i class="fa fa-phone"></i> 4008100686 </div>
					<div class="pull-right"><img src="">${sessionUser.realname }，欢迎您</div>
	</div>

</div>
		  <div id="popup_box" style="display:none">
            <div class="box_style box_wait">
                <div class="loader">
                    <div class="loader-inner ball-spin-fade-loader">
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                </div>
                <div class="wait_content">
                   		 请稍后
                </div>
            </div>     
        </div>