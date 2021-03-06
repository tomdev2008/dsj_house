$.fn.commentList = function (option) {
	this.container = option.container;
	this.pageTemplate_selector = "#"+option.pageTemplate_id;
	this.commentTemplate_selector = "#"+option.commentTemplate_id;
	
	this.list = [];
	
	this.url =option.url;
	this._id = option.id;
	this._typeId = option.type_id;
    this.pageOperation = {
	    currentPage:1,
	    queryPage:1,
	    totalPage:1,
	    nextButton:"",
	    prevButton:"",
	 };

	this._init = function (argument) {
		this.getPageData();
	}
	this.firstSearch = function(data) {
		this.dataFilter(data)
		this.render();
	}

	this.getPageData = function(argument) {
		var aoData=new Array();
		var self = this;
		$.ajax({
		  type: "POST",
		  url: self.url+"/front/comment/getComment",
		  data: {
			  pageFirst : self.pageOperation.queryPage,
			  pageSize : 3 ,
			  objectType : self._typeId,
			  objectId : self._id
		  },
		  dataType: "json",
		  success: function(data){
		    self.dataFilter(data)
		    self.render();
		  }
		});
	}
	this.render = function (argument) {
		// body...
		this.container.html('');
		this.renderList();
		//this.pageInfoRender();
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
		this.list = data.data;
	    this.pageOperation.totalPage = data.totalPage;
	}
	this.pageEvent = function(argument) {
		var self = this;
		this.container.find(".prev").click(function(e) {
			e.preventDefault();
			if(self.pageOperation.currentPage - 1 <= 0 ){
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