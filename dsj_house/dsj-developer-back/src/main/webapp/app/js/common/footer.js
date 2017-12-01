$(function(){
	 require(["text!common/footer.html"], function(tpl) {
		 	$('body').append(tpl);
	    	 var source=$("#footer_template").html();
	    	 var template = Handlebars.compile(source);
			 var html    = template();
	    	 $('body').append(html);
		}); 
})