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
			data:{},
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
			    	 var hrefa = window.location.pathname;
			    	 if(hrefa.indexOf("basic-info")>0){
			    		 $(".navKuang ul li:eq(1) a").addClass("active");
			    	 }else{
			    		 $(".navKuang ul li:eq(0) a").addClass("active");
			    	 }
			    	// var first=document.body.firstChild;//得到页面的第一个元素 
			    	 //$('body').insertBefore(html,first);//在得到的第一个元素之前插入 
		 	}); 
			
			}
		})
	 
	 require(["text!common/footer.html"], function(tpl) {
		 	$('body').append(tpl);
	    	 var source=$("#footer_template").html();
	    	 var template = Handlebars.compile(source);
			 var html    = template();
	    	 $('body').append(html);
		}); 
		
})