<header class="BHheader">
    <nav class="headerNav">
        <ul class="headerLeftNav">
           <li><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>北京</li>
           <li><a href="${ctx}/">首页</a></li>
           <li><a href="${ctx}/front/newHouse/list">新房</a></li>
           <li><a href="${ctx}/ershoufang">二手房</a></li>
           <li><a href="${ctx}/front/agent">经纪人</a></li>
           <li><a href="${ctx}/front/entrust/sell">业主委托</a></li>
           <li><a href="${ctx}/warrant/index">自交易</a></li>
         </ul>
         <ul class="headerRightNav">
         <#if Session.pc_user_session??>
         	  <li>
         	  <#if pc_user_session.userType==5>
  					<a href="${ctx}/front/personCenter/center" >${sessionUsername}</a>
  				<#else>
  					<a href="javaScript:void(0)" >${sessionUsername}</a>
  				</#if>
         	  		<input type="hidden" id="usernameIM" value="${pc_user_session.username}" />
					<input type="hidden" id="passwordIM" value="${pc_user_session.imPassword}" />
	         	  	<input type="hidden" id="userIdIM" value="${pc_user_session.id}" />
         	  	<#if pc_user_session.avatar??>
	         	  	<input type="hidden" id="avatarIM" value="${pc_user_session.avatar}" />
         	  	<#else>
          			<input type="hidden" id="avatarIM" value="" />
          		</#if>
         	  </li>
         	   <li><a href="javascript:void(0);" onclick="loginOut()">退出</a></li>
         <#else>
           <li><a href="${ctx}/login/tologin">登录</a></li>
           <li><a href="${ctx}/login/register">注册</a></li>
           <input type="hidden" id="usernameIM" value="" />
		   <input type="hidden" id="passwordIM" value="" />
		   <input type="hidden" id="avatarIM" value="" />
		   <input type="hidden" id="userIdIM" value="" />
          </#if>
          <#if newHouseId??>
          		<input type="hidden" id="houseIdIM" value="${newHouseId}" />
          <#else>
          		<input type="hidden" id="houseIdIM" value="" />
          </#if>
         </ul>
       </nav>
       <script type="text/javascript">
       	function loginOut(){
       		$.ajax({
		  		type:"post",
		  		url:_ctx+"/login/loginOut",
		  		data:{
		  			url:window.location.href
		  		},
		  		dataType:"json",
		  		success: function(result){
		  			window.location.href="${ctx}/";
		  		}
		  	});
       	}
       </script>
   </header>
   <#if Session.pc_user_session??>
         <#if Session.pc_user_session.userType==5>
         	<div class="sidebar_background">
                <ul class="sidebar_nav">
                    <li class="guanzhu">
                        <a></a> 
                        <div class="sidebar_text">
                            <a onclick="goPersonCenter('follow')">我的关注</a>
                            <span></span>
                        </div>    
                    </li>
                    <li class="zuji">
                        <a></a>
                        <div class="sidebar_text">
                                <a onclick="goPersonCenter('lookHistory')">浏览历史</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="weituo">
                        <a></a>
                        <div class="sidebar_text">
                               <a onclick="goPersonCenter('entrust')">业主委托</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="kefu">
                        <a href="##"></a>
                        <div class="sidebar_text side_phone">
                                <a>4008986868转888</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="fankui">
                        <a href="##">
                        	
                        </a>
                        <div class="sidebar_text">
                               	<a onclick="showFeedback()">用户反馈</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="last-top"><a href="#dsj">TOP</a></li>
                </ul>
            </div>
         <#else>
         	<div class="sidebar_background">
                <ul class="sidebar_nav">
                    <li class="kefu">
                        <a href="##"></a>
                        <div class="sidebar_text side_phone">
                                <a>400-898-6868转888</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="fankui">
                        <a href="##">
                        	
                        </a>
                        <div class="sidebar_text">
                               	<a onclick="showFeedback()">用户反馈</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="last-top"><a href="#dsj">TOP</a></li>
                </ul>
            </div>
         </#if> 
   <#else>
            <div class="sidebar_background notLoginSidebar" >
                <ul class="sidebar_nav">
                    <li class="guanzhu">
                        <a href="##"></a> 
                        <div class="sidebar_text">
                            <a href="${ctx}/login/tologin">我的关注</a>
                            <span></span>
                        </div>    
                    </li>
                    <li class="zuji">
                        <a href="##"></a>
                        <div class="sidebar_text">
                                <a href="${ctx}/login/tologin">浏览历史</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="weituo">
                        <a href="##"></a>
                        <div class="sidebar_text">
                               <a href="${ctx}/login/tologin">业主委托</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="kefu">
                        <a href="##"></a>
                        <div class="sidebar_text side_phone">
                                <a>400-898-6868转888</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="fankui">
                        <a href="##">
                        	
                        </a>
                        <div class="sidebar_text">
                               	<a href="${ctx}/login/tologin">用户反馈</a>
                                <span></span>
                        </div>
                    </li>
                    <li class="last-top"><a href="#dsj">TOP</a></li>
                </ul>
            </div> 
   </#if>
 
	<script type="text/javascript">

	
        $(function(){
            var sUserAgent= navigator.userAgent.toLowerCase(); 
            var bIsIpad= sUserAgent.match(/ipad/i) == "ipad"; 
            if ( !bIsIpad ) { 
                $('.sidebar_background').show();  
            }

        })
       	function goPersonCenter(params){

		  window.location=_ctx+"/front/personCenter/center?typeOfPage="+params;

       	}
       	function showFeedback(){
       		show_box(4,'');
       		$("#advice").val("");
       	}
       	function wordNum(){
       	
       		var l = $("#advice").val().length;
       		if(l>0){
       			$("#submitButtton").addClass("btn-primary");
       			$("#submitButtton").attr("onclick","submitAdvice();");
       		}else{
       			$("#submitButtton").removeClass("btn-primary");
       			$("#submitButtton").removeAttr("onclick");
       		}
       		$("#word_300").html(300-l);
       		
       	}
       	function submitAdvice(){
       		 $.ajax({
		        type:"post",
		        url:_ctx+"/front/personCenter/feedback",
		        data: {content:$("#advice").val()},
		        datatype:"json",
		        success:function(result){
		        	if(result.status==200){
                        $("#advice").val("");
                        $(box_feedback).hide();
		        		$("#popup_box").hide();

		        		show_box(3,"提交成功");
		        	}
		        }
		     })
       	}
       	
   </script>
   
   <#include "../common/popup_menu.ftl">
 