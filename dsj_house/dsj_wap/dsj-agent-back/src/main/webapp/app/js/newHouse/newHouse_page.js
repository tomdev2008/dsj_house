$.fn.houseList = function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.cardTemplate_id_selector = "#"+option.cardTemplate_id;
	this.rowTemplate_id_selector = "#"+option.rowTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.renderStyle = "card";//"row"
	this._url=_url;
	this.list = [
	];
	// this.list = [{}] ;//{[];[]}
	
	this.url =this._url+"/agent/back/**/newHouse/newHouseDirectoryList";

    this.pageOperation = {
	    currentPage:1,
	    queryPage:1,
	    totalPage:1,
	    nextButton:"",
	    prevButton:"",
	 };


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
	this.firstSearch = function(data,totalPage) {
		// init
		// click
		// 
		// listRender()
		// pageInfoRender
		this.dataFilter(data,totalPage)
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
		   dataType: "json",
		  success: function(data){
			  var newHouse =data.data;
              var totalPage=data.totalPage;
              self.firstSearch(newHouse,totalPage);
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
				if(this.list[i].isDuty==1){
					this.list[i].isDutyString="责任楼盘";
				}
				if(this.list[i].spellName==null){
					this.list[i].recommend="推荐";
					this.list[i].edit=0;
				}else{
					this.list[i].recommend="取消推荐";
					this.list[i].edit=1;
				}
				if(this.list[i].housetypeNames==null){
					this.list[i].housetypeNames="未公布";
				}
				if(this.list[i].oldName==null){
					this.list[i].shelves="展示在货架";
					this.list[i].authStatus=0;
				}else{
					this.list[i].shelves="取消展示";
					this.list[i].authStatus=1;
				}
				var pictures=this.list[i].pictureUrl;
				if(pictures==null){
					this.list[i].pictureUrl="./img/fangchan.jpg";
				}
				html += template(this.list[i]);
			}
			$(this.container_id_selector).prepend(html);
	}

	this.dataFilter = function (data,totalPage,newHouseDirectory) {
		// body...
		// data type wrong ?
		// right ?
			//
		this.list = data;
		this.newHouseDirectory=newHouseDirectory;
	    this.pageOperation.totalPage = totalPage;

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
	            url: self._url+"/agent/back/**/newHouse/newHouseDirectoryList",
	            data: $("form").serialize()+"&page="+self.pageOperation.queryPage,
	            dataType: "json",
	            success: function(data){
	             var newHouse =data.data;
	              var totalPage=data.totalPage;
	              self.firstSearch(newHouse,totalPage);
	            }
	      });
	}
}