(function ( $ ) {
 
    var shade = "#556b2f";
 
    $.fn.dsj_photo = function( options ) {

    	// Extend our default options with those provided.
	    // Note that the first argument to extend is an empty
	    // object – this is to keep from overriding our "defaults" object.
	    var opts = $.extend( {}, $.fn.hilight.defaults, options );
	 
	    // Our plugin implementation code goes here.


    	// This is the easiest way to have default options.
        var settings = $.extend({
            // These are the defaults.
            color: "#556b2f",
            backgroundColor: "white"
        }, options );
 
        // Greenify the collection based on the settings variable.
        return this.css({
            color: settings.color,
            backgroundColor: settings.backgroundColor
        });
    };
    $.fn.hilight.defaults = {
        foreground: "red",
        background: "yellow"
    };

    
 
}( jQuery ));


state
	initial
	loading
	empty
	first
	some
	too much,page
	error
	correct
	finish

funtcion
	add
	delete
	update
	search
	display