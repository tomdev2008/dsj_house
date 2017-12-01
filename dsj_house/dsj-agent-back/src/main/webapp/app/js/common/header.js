 var _url="";
 var pathName = window.document.location.pathname;
_url="/" +pathName.split("/")[1];
if(_url=="/app"){
	_url='';
}
$(function(){
	 $.ajax({
			type:"post",
			 async: true,
			 data:{r:Math.random()},
			dataType:"json",
			url:_url+"/back/user/getUser",
			success:function(data){
				
				 require(["text!common/header.html"], function(tpl) {
					   
			    	 $('body').append(tpl);
			    	 var source=$("#header_template").html();
			    	 var template = Handlebars.compile(source);
			    	 var COOKIE_NAME = 'username';  
					 var html    = template(data);
			    	 $('body').prepend(html);
			    	// var first=document.body.firstChild;//得到页面的第一个元素 
			    	 //$('body').insertBefore(html,first);//在得到的第一个元素之前插入 
		 	}); 
			
			}
		})
	 
	
})
function gotoBaseUrl(toUrl){
	location = _url+toUrl;
}

function loginOut(){
	window.location =_url+'/login/logout';
}