$.fn.dsj_pager = function (option) {
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.container_id_selector = option.container_id;


    this.pageOperation = {
	    currentPage:1,
	    totalPage:1,
	    prev:"",
	    next:""
	 };

	this._init = function (argument) {
		this.pageOperation.totalPage = 55
		this.pageOperation.currentPage=9;
		this.render();
	}
	this.render = function (argument) {
		this.pageInfoRender();
		this.pageEvent();
	}
	this.pageInfoRender = function (pageOperation) {
		var self = this;
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

		if(this.pageOperation.currentPage - 1 < 0 ){
			this.pageOperation.prev = 1;
		}
		else {
			this.pageOperation.prev = this.pageOperation.currentPage - 1;
		}

		if(this.pageOperation.currentPage + 1 > this.pageOperation.totalPage ){
			this.pageOperation.next = this.pageOperation.totalPage;
		}
		else {
			this.pageOperation.next = this.pageOperation.currentPage + 1;
		}



		var option = this.pageOperation;
		option.list = list
		var source   = $(this.pageTemplate_id_selector).html();
		var template = Handlebars.compile(source);
		var html =  template(option);
		$(this.container_id_selector).append(html);
		$(this.container_id_selector).find("li[data-page='"+currentPage+"']").addClass("active");
	}
}