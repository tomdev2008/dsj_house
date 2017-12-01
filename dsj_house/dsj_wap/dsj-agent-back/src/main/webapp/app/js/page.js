+function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.cardTemplate_id_selector = "#"+option.cardTemplate_id;
	this.rowTemplate_id_selector = "#"+option.rowTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.renderStyle = "card";//"row"
	this._url=_url;
	this.list = [
	];
	// this.list = [{}] ;//{[];[]}
	
	this.url ="";

    this.pageOperation = {
	    currentPage:1,
	    queryPage:1,
	    totalPage:1,
	    nextButton:"",
	    prevButton:"",
	 };

	var dismiss = '[data-dismiss="alert"]'

	
	var page   = function (el) {
	  $(el).on('click', dismiss, this.close)
	}



	this._init = function (argument) {
		var self = this
		$("#card_view").click(function(argument) {
			self.renderStyle = "card";
			self.render();
		})
		$("#row_view").click(function(argument) {
			self.renderStyle = "row";
			self.render();
		})
		this.searchAjax();
	}
	this.loading = function(argument) {
		// modal drop animation
	}
	this.empty = function(argument) {
		// nothing to show ,list
		// disable op
	}
	this.changeRenderStyle = function(argument) {

	}
	this.firstSearch = function(data) {
		// init
		// click
		// 
		// listRender()
		// pageInfoRender
		this.dataFilter(data)
		this.render();
	}
	this.someData = function(argument) {
		// disable next button
		//

	}
	this.tooMuch = function(argument) {
		// init page
		//
	}
	this.error = function(argument) {
		// wrong search 
		//no ahth
	}
	this.correct = function (argument) {
		// body...
	}
	this.complete = function(argument) {
		//  disable prev & next
	}

	this.getPageData = function(argument) {
		// ajax for search and page
		// data filter()
		var url = this.url;
		var self = this;
		$.ajax({
		  type: "POST",
		  url: url,
		  data: $("form").serialize()+"&page="+self.pageOperation.queryPage,
		  // dataType: "json",
		  success: function(data){
		    // firstSearch(data)

		    self.dataFilter(data)
		    self.render();
		  }
		});
	}


	this.next = function (argument) {
		// body...
	}
	this.prev = function (argument) {
		// body...
	}
	this.render = function (argument) {
		// body...
		$(this.container_id_selector).html('');
		this.renderList();
		this.pageInfoRender();
	}
	this.renderList = function (argument) {
		if (this.renderStyle == "card") {
			var source   = $(this.cardTemplate_id_selector).html();
		} else {
			var source   = $(this.rowTemplate_id_selector).html();

		}
			var template = Handlebars.compile(source);
			var html = "";
			for (var i = 0; i < this.list.length; i++) {
				html += template(this.list[i]);
			}
			$(this.container_id_selector).prepend(html);
	}

	this.dataFilter = function (data) {
		// body...
		// data type wrong ?
		// right ?
			//
		this.list = data;
	    this.pageOperation.totalPage = 20;

	}
	this.pageEvent = function(argument) {
		var self = this;
		$(this.container_id_selector).find(".prev").click(function(e) {
			e.preventDefault();
			if(self.pageOperation.currentPage - 1 < 0 ){
				self.pageOperation.queryPage = 1;
				self.pageOperation.currentPage = 1;
			}
			else {
				self.pageOperation.queryPage = self.pageOperation.currentPage - 1;
				self.pageOperation.currentPage = self.pageOperation.currentPage - 1;
			}
			self.getPageData();
		})
		$(this.container_id_selector).find(".next").click(function(e) {
			e.preventDefault();
			if(self.pageOperation.currentPage + 1 > self.pageOperation.totalPage ){
				self.pageOperation.queryPage = self.pageOperation.totalPage;
				self.pageOperation.currentPage = self.pageOperation.totalPage;
			}
			else {
				self.pageOperation.queryPage = self.pageOperation.currentPage + 1;
				self.pageOperation.currentPage = self.pageOperation.currentPage + 1;
			}
			self.getPageData();
		})
	} 

	this.pageInfoRender = function (pageOperation) {
		// responsible for pageInfo render
		// currentPage & totol page
		// diable prev or next
		// event bind
		if(this.totalPage == 0 || this.totalPage == 1){
			//disable all
		}

		if (this.currentPage == 1){

			//disable prev
		} 
		else if ( this.currentPage == this.totalPage) {
			//disable next
		}
		var source   = $(this.pageTemplate_id_selector).html();
		var template = Handlebars.compile(source);
		var html =  template(this.pageOperation);
		$(this.container_id_selector).append(html);
		this.pageEvent();
	}
	
	this.searchAjax = function(argument){
			self=this;
		   $.ajax({
	            type: "GET",
	            url: self._url+"/back/oldHouse/master/page/list",
	            data: $("form").serialize(),
	            dataType: "json",
	            success: function(data){
	              data =data.data;
	              self.firstSearch(data);
	            }
	      });
	}

	// PLUGIN DEFINITION
	// =======================

	function Plugin(option) {
	  return this.each(function () {
	    var $this = $(this)
	    var data  = $this.data('bs.alert')

	    if (!data) $this.data('bs.alert', (data = new Alert(this)))
	    if (typeof option == 'string') data[option].call($this)
	  })
	}

	var old = $.fn.alert

	$.fn.alert             = Plugin
	$.fn.alert.Constructor = Alert


	// NO CONFLICT
	// =================

	$.fn.alert.noConflict = function () {
	  $.fn.alert = old
	  return this
	}

}(jQuery);