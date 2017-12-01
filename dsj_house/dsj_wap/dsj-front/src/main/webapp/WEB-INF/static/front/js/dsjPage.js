$.fn.pager = function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.rowTemplate_id_selector = "#"+option.rowTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.url=option.url;
	this.list = [
	];
	// this.list = [{}] ;//{[];[]}
    this.pageOperation = {
	    pagecount: "10",
	    currentPage:1,
	    queryPage:1,
	    totalPage:1,
	    nextButton:"",
	    prevButton:"",
	    totalcount:"",
	    showPages:[]
	 };
	this._init = function (argument) {
		this.getPageData();
		this.pageEvent();
	}

	this.getPageData = function(argument) {
		var url = this.url;
		var self = this;
		var aoData=new Array();
		self.formToJson(aoData);
	   /* self.pageOperation.totalPage=10;
        self.pageOperation.totalcount=200;
        self.render();*/

		 $.ajax({
		   type: "POST",
		   url: url,
		   data: {"aoData":JSON.stringify(aoData)},
		    dataType: "json",
		   success: function(data){
		     self.dataFilter(data)
		     self.render();
		     self.pageOperation.totalPage=data.totalPage;
            self.pageOperation.totalcount=data.iTotalRecords;
		  }
		 });
	}
	

	this.formToJson=function(aoData){
		var self = this;
		aoData.push({"name":"page","value":self.pageOperation.queryPage});
		aoData.push({"name":"pageSize","value":self.pageOperation.pagecount});
		
		// aoData=aoDataChange("dsj_form",aoData);
	}
	
	this.render = function (argument) {
		// body...
		// $(this.container_id_selector).html('');
		// this.renderList();
		this.pageInfoRender();
	}
	this.renderList = function (argument) {
		var source   = $(this.rowTemplate_id_selector).html();
		var template = Handlebars.compile(source);
		var html = "";
		for (var i = 0; i < this.list.length; i++) {
			html += template(this.list[i]);
		}
	
		$(this.container_id_selector).prepend(html);
	}
	this.dataFilter = function (data) {
		this.list = data.data;
	}
	this.pageEvent = function(argument) {
		var self = this;
		$(this.pageTemplate_id_selector).find("select").change(function(e) {
			e.preventDefault();
			self.pageOperation.pagecount = $(this).val();
			self.getPageData();
		})

		$(this.pageTemplate_id_selector).find(".first").click(function(e) {
			e.preventDefault();
			self.pageOperation.queryPage = 1;
			self.pageOperation.currentPage = 1;
			self.getPageData();
		})
		$(this.pageTemplate_id_selector).find(".last").click(function(e) {
			e.preventDefault();
			self.pageOperation.queryPage = self.pageOperation.totalPage;
			self.pageOperation.currentPage = self.pageOperation.totalPage;
			self.getPageData();
		})
		$(this.pageTemplate_id_selector).find(".prev").click(function(e) {
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
		$(this.pageTemplate_id_selector).find(".next").click(function(e) {
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
		$(this.pageTemplate_id_selector).on("click", ".page_button", function(e) {
			e.preventDefault();
			self.pageOperation.queryPage = parseInt($(e.target).text());
			self.pageOperation.currentPage = parseInt($(e.target).text());

			self.getPageData();
		})

	} 

	this.pageInfoRender = function (pageOperation) {
		var currentPage = this.pageOperation.currentPage;
		var totalPage = this.pageOperation.totalPage;

		var start = currentPage - 2;
		var html = '';

		if ((1 <= currentPage - 2) && (currentPage - 2 <=totalPage)) {
			start = currentPage - 2;
		} else if ((1 <= currentPage - 1) && (currentPage - 1 <=totalPage)){
			start = currentPage - 1;
		} else if ((1 <= currentPage) && (currentPage <=totalPage)){
			start = currentPage ;
		} else if ((1 <= currentPage + 1) && (currentPage + 1 <=totalPage)){
			start = currentPage + 1;
		} else if ((1 <= currentPage + 2) && (currentPage + 2 <=totalPage)){
			start = currentPage + 2;
		} 
		for (var i = 0; i < 5; i++) {
			if( (start + i >= 1) && (start + i <= totalPage)) {
				var page = start + i;
				if ( page == currentPage ) {
					html += '<li class="active"><a href="#" class="page_button">' + page +'</a></li>'
				}else {
					html += '<li><a href="#" class="page_button">' + page +'</a></li>'
				}
			}else {
				break;
			}
		}
		$(this.pageTemplate_id_selector).find(".page_button").parent().remove();
		$(html).insertAfter($(this.pageTemplate_id_selector).find("li:eq(0)"));

		// var source   = $(this.pageTemplate_id_selector).html();
		// var template = Handlebars.compile(source);
		// var html =  template(option);
		// $(this.container_id_selector).append(html);
	}
}