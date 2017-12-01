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
					<li >
						<a href="javascript:void(0);" title="order"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">订单管理</span></a>
						<ul>
							<li>
								<a href="${ctx}/back/frame/warrant/order/order_list" title="order"><span class="menu-item-parent">订单列表</span></a>
							</li>
						</ul>	
					</li>
					<li >
						<a href="javascript:void(0);" title="evaluate"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">商家评价</span></a>
						<ul>
							<li>
								<a href="${ctx}/back/frame/warrant/evaluate" title="evaluate"><span class="menu-item-parent">评价列表</span></a>
							</li>	
						</ul>	
					</li>
					<li >
						<a href="javascript:void(0);" title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">账户设置</span></a>
						<ul>
							<li>
								<a href="${ctx}/back/frame/person/warrant/to_update_password" title="Dashboard"><span class="menu-item-parent">修改密码</span></a>
							</li>
							<li>
								<a href="${ctx}/back/frame/person/warrant/to_upload_photo" title="Dashboard"><span class="menu-item-parent">修改头像</span></a>
							</li>
							<li>
								<a href="${ctx}/back/frame/person/warrant/to_update_phone" title="Dashboard"><span class="menu-item-parent">修改手机号</span></a>
							</li>
						</ul>	
					</li>
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
		});
		
	})
</script>