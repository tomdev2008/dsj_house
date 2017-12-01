$.fn.commentList = function (option) {
	this.container = option.container;
	this.pageTemplate_selector = "#"+option.pageTemplate_id;
	this.commentTemplate_selector = "#"+option.commentTemplate_id;
	
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


	this._init = function (argument) {
		// welcome searching
		// init page and operation
		var self = this; 
		self.firstSearch();
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
		this.container.html('');
		this.renderList();
		this.pageInfoRender();
	}
	this.renderList = function (argument) {
			var source   = $(this.commentTemplate_selector).html();
			var template = Handlebars.compile(source);
			var html = "";
			for (var i = 0; i < this.list.length; i++) {
				html += template(this.list[i]);
			}
			this.container.prepend(html);
	}

	this.dataFilter = function (data) {
		// body...
		// data type wrong ?
		// right ?
			//
		// this.list = JSON.parse(data);
		this.list = [
			{"price":this.pageOperation.currentPage*1000},
			{"price":10001},
			{"price":10002},
			{"price":10002},
			{"price":10002},
			{"price":10002},
			{"price":10002},
			{"price":10002},
			{"price":10002}
        ];

	    this.pageOperation.totalPage = 20;

	}
	this.pageEvent = function(argument) {
		var self = this;
		this.container.find(".prev").click(function(e) {
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
		this.container.find(".next").click(function(e) {
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
		var source   = $(this.pageTemplate_selector).html();
		var template = Handlebars.compile(source);
		var html =  template(this.pageOperation);
		this.container.append(html);
		this.pageEvent();
	}
}