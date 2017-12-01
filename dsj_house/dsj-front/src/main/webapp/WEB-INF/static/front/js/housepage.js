$.fn.houseList = function (option) {
	this.pageOperation = {
		    pagecount: "10",
		    currentPage:1,
		    queryPage:1,
		    totalPage:1,
		    nextButton:"",
		    prevButton:"",
		    totalcount:""
		 };
	
	this.container_id_selector = "#"+option.container_id;
	this.rowTemplate_id_selector = "#"+option.rowTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.url=option.url;
	if(option.pagecount !=undefined){
		this.pageOperation.pagecount =  option.pagecount;
	}
	this.list = [
	];
	// this.list = [{}] ;//{[];[]}


	this._init = function (argument) {
		// welcome searching
		// init page and operation
		this.searchAjax();
	}
	this.loading = function(argument) {
		// modal drop animation
	}
	this.empty = function(argument) {
		// nothing to show ,list
		// disable op
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
		var aoData=new Array();
		self.formToJson(aoData);
		$.ajax({
		  type: "POST",
		  url: url,
		  data: {"aoData":JSON.stringify(aoData)},
		  // dataType: "json",
		  success: function(data){
		    // firstSearch(data)

		    self.dataFilter(data)
		    self.render();
		    self.pageOperation.totalPage=data.totalPage;
            self.pageOperation.totalcount=data.iTotalRecords;
            self.firstSearch(data);
		  }
		});
	}
	

	this.formToJson=function(aoData){
		var self = this;
		aoData.push({"name":"page","value":self.pageOperation.queryPage});
		aoData.push({"name":"pageSize","value":self.pageOperation.pagecount});
		
		aoData=aoDataChange("dsj_form",aoData);
	}
	
	this.searchAjax = function(argument){
		var aoData=new Array();
		var self = this;
		self.formToJson(aoData);
	   $.ajax({
            type: "POST",
            url: option.url,
            data: {"aoData":JSON.stringify(aoData)},
            dataType: "json",
            success: function(data){
              self.pageOperation.totalPage=data.totalPage;
              self.pageOperation.totalcount=data.iTotalRecords;
              self.firstSearch(data);
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
		if(null!=this.list){
			this.renderList();
			this.pageInfoRender();
		}
		
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
		// body...
		// data type wrong ?
		// right ?
			//
		this.list = data.data;
		/*this.list = [
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
*/
	    // this.pageOperation.totalPage = 20;

	}
	this.pageEvent = function(argument) {
		var self = this;
		$(this.container_id_selector).find("select").change(function(e) {
			e.preventDefault();
			self.pageOperation.pagecount = $(this).val();
			self.getPageData();
		})

		$(this.container_id_selector).find(".first").click(function(e) {
			e.preventDefault();
			self.pageOperation.queryPage = 1;
			self.pageOperation.currentPage = 1;
			self.getPageData();
		})
		$(this.container_id_selector).find(".last").click(function(e) {
			e.preventDefault();
			self.pageOperation.queryPage = self.pageOperation.totalPage;
			self.pageOperation.currentPage = self.pageOperation.totalPage;
			self.getPageData();
		})
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
		$(this.container_id_selector).find(".page_button").click(function(e) {
			e.preventDefault();

			self.pageOperation.queryPage = $(this).html();
			self.pageOperation.currentPage = $(this).html();

			self.getPageData();
		})

	} 

	this.pageInfoRender = function (pageOperation) {
		// responsible for pageInfo render
		// currentPage & totol page
		// diable prev or next
		// event bind
		if( this.totalPage <= 5 ) {
			this.pageOperation.pagelist
		}else {

		}

		if( this.pageOperation.currentPage ) {
			
		}
		var currentPage = this.pageOperation.currentPage;
		var totalPage = this.pageOperation.totalPage;
		var list = [];

		var start = currentPage - 2;

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
				list[i] = start + i
			}else {
				break;
			}
		}

		if(this.pageOperation.totalPage == 0 || this.pageOperation.totalPage == 1){
			//disable all
		}

		if (this.pageOperation.currentPage == 1){
			//disable prev
		} 
		else if ( this.pageOperation.currentPage == this.pageOperation.totalPage) {
			//disable next
		}
		var option = this.pageOperation;
		option.list = list
		var source   = $(this.pageTemplate_id_selector).html();
		var template = Handlebars.compile(source);
		var html =  template(option);
		$(this.container_id_selector).append(html);
		this.pageEvent();
	}
}
