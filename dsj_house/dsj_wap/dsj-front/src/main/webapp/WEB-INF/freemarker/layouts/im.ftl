<link rel="stylesheet" href="${ctx}/static/im/css/im.css">
<div>
	<div class="dsj_chat">
	    <section id='main' class='w100'>
	        <article id='demo' ></article>
	        <article id='components'></article>
	    </section>
	</div>
	<input type="hidden" id="agents"  value="" />
	<input type="hidden" id="contacts"  value="" />
	<input type="hidden" id="urlIM" value="${ctx}/front/im/user/add_contact" />
	<div id="addContact" style="display:;"></div>
	<div class="webim-expend pointer" onClick="openChatWindow()">
	    <img class="webim-expend-ico" src="${ctx}/static/im/dsj/images/custom/contact.png"/>
	    在线咨询
	    <img class="webim-expend-arrow" src="${ctx}/static/im/dsj/images/custom/upper.png"/>
	</div>
</div>

<script src="${ctx}/static/im/dsj/javascript/dist/browser-polyfill.min.js"></script>
<!-- IMjs start -->
<script src="${ctx}/static/im/dsj/javascript/dist/webim.config.js"></script>
<script src='${ctx}/static/im/sdk/dist/strophe-1.2.8.min.js'></script>
<script src='${ctx}/static/im/sdk/dist/websdk-1.4.11.js'></script>
<!-- IMjs end -->
<script src='${ctx}/static/im/webrtc/dist/adapter.js'></script>
<script src='${ctx}/static/im/webrtc/dist/webrtc-1.4.11.js'></script>
<!-- <script src='${ctx}/static/im/dsj/javascript/dist/demo-1.4.11.js'></script> -->
<script type="text/javascript">
	var i = 0;
	var userId = $("#userIdIM").val();
	var houseId = $("#houseIdIM").val();
	var flag = false;
	if (userId) {
		initIM(userId, houseId, flag);
	}
	   
    function openChatWindow(username, realname, phone, avatar){
    	if (!userId) {
    		show_box(7);
    	}else{
    		$(".webim-expend").show();//首页专用显示在线咨询按钮操作
        	if (avatar == "null") {
    			avatar = "";
    		}
        	window.Demo.api.openChatWindow(username, realname, phone, avatar);
        	if (username) {
                flag = true;
                $(".dsj_chat").show();
                $(".webim-expend-arrow").attr("src","${ctx}/static/im/dsj/images/custom/downer.png");
                
                var url = $("#urlIM").val();
    	        $.ajax({
    	            type: "GET",
    	            url: url,
    	            data: {userId:userId, dtype:'jsonp', username:username},
    	            dataType: "jsonp",
    	            crossDomain: true,
    	            success: function(data){
    	                if (!data || data.status != "SUCCESS") {  
    	                     alert(data.message);
    	                }
    	            }
    	        });
            } else {
                if(flag) {
                    flag = false;
                    $(".dsj_chat").hide(); 
                    $(".webim-expend-arrow").attr("src","${ctx}/static/im/dsj/images/custom/upper.png");
                } else {
                    flag = true;
                    $(".dsj_chat").show();
                    $(".webim-expend-arrow").attr("src","${ctx}/static/im/dsj/images/custom/downer.png");
                }
            }
    	}
    }
    
    function initIM(userId, houseId, flag){
        $.ajax({
	        type: "POST",
	        url: "${ctx}/front/im/user/list",
	        data: {userId:userId, houseId:houseId},
	        dataType: "json",
	        crossDomain: true,
	        success: function(data){
	            if (data && data.status==200) {
	            	if (data.data.agents) {
	                	$("#agents").attr('value', JSON.stringify(data.data.agents).replace(/"/g,"'"));
	                }
	                if (data.data.contacts) {
	                	$("#contacts").attr('value', JSON.stringify(data.data.contacts).replace(/"/g,"'"));
	            	}
	            	$.getScript('${ctx}/static/im/dsj/javascript/dist/demo-1.4.11.js', null);
	            } else {  
	            	$.getScript('${ctx}/static/im/dsj/javascript/dist/demo-1.4.11.js', null);
	                alert(data.message);
	            }
	        }
	    });
    }
</script>