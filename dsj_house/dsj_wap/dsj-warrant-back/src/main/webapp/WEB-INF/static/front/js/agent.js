$.fn.DialogList = function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.dialogTemplate_id_selector = "#"+option.dialogTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	
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
		$(document).on("click",".dsj_update_content_more",function(argument) {
		  $(this).parent().next(".dsj_update_pic").toggleClass("hide")
		})
		$(document).on("click",".dsj_comment",function(argument) {
		  $(this).closest(".dsj_update_time").next(".dsj_publish").toggleClass("hide")
		})

		$(document).on("click",".toggle_reply",function(argument) {
		  $(this).parent().next().toggleClass("hide")
			var commentOption = {
			  container: $(this).parent().next(),
			  pageTemplate_id : "dsj_page_template",
			  commentTemplate_id : "dsj_comment_template",
			  url : "www.baidu.com"
			}
			var commentList = new $.fn.commentList(commentOption);
			commentList._init();
		})



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
			var source   = $(this.dialogTemplate_id_selector).html();
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
}