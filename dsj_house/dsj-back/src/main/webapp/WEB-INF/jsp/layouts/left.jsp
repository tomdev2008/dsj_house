<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
		<aside id="left-panel">

			<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
			<img class="dsj_logo" src="${ctx}/static/front/img/logo_dsj.png">
			<span class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i> </span>
			<nav>
				<!-- 
				NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional href="" links. See documentation for details.
				-->
				<ul>
					<c:forEach items="${menus}" var="vo" varStatus="status">  
					<li >
						<a href="#" title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">${vo.name }</span></a>
						<ul>
							<c:forEach items="${vo.resourcePo}" var="po" varStatus="status"> 
							<shiro:hasPermission name="${po.pattern}">
							<li>
								<a href="${ctx}${po.uri}" title="Dashboard"><span class="menu-item-parent">${po.name }</span></a>
							</li>
							</shiro:hasPermission>
							</c:forEach>
							
						</ul>	
					</li>
					</c:forEach>
				</ul>
			</nav>

		</aside>
		
<script>
	$(function(){
		var url=document.location.href;
		$("nav > ul > li >ul >li").each(function() {
			var thisUrl=$(this).find("a").attr("href");
			if(url.indexOf(thisUrl)>=0&&thisUrl!="/dsj-back"){
				$(this).addClass("active");
				$(this).parents("ul").parents("li").addClass("active");
				$(this).parents("ul").parent("li").addClass("open");
				$(this).parents("ul").css("display","block");
			}
			/* $(this).parents("ul").slideDown(opts.speed);
			$(this).parents("ul").parent("li").find("b:first").html(opts.openedSign);
			$(this).parents("ul").parent("li").addClass("open"); */
		});
		
	})
</script>