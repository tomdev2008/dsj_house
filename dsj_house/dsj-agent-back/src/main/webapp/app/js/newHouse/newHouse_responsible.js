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
	
	this.url =this._url+"/agent/back/**/newHouse/responsible/newHouseResponsibleList";

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
	this.firstSearch = function(data,agentDirList,agentSevlvesList,totalPage) {
		// init
		// click
		// 
		// listRender()
		// pageInfoRender
		this.dataFilter(data,agentDirList,agentSevlvesList,totalPage)
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
				if(this.list[i].spellName==null){
					this.list[i].recommend="推荐";
				}else{
					this.list[i].recommend="取消推荐";
				}
				if(this.list[i].housetypeNames==null){
					this.list[i].housetypeNames="未公布";
				}
				if(this.list[i].oldName==null){
					this.list[i].shelves="展示在货架";
				}else{
					this.list[i].shelves="取消展示";
				}
				if(this.list[i].isTure==1){
					this.list[i].isDutyString="未上架";
				}else if(this.list[i].isTure==2){
					this.list[i].isDutyString="已上架";
				}else if(this.list[i].isTure==3){
					this.list[i].isDutyString="已下架";
				}else if(this.list[i].isTure==4){
					this.list[i].isDutyString="已删除";
				}
				if(this.list[i].authStatus==1){
					this.list[i].maintainName="待提交";
				}else if(this.list[i].authStatus==2){
					this.list[i].maintainName="待审核";
				}else if(this.list[i].authStatus==3){
					this.list[i].maintainName="已通过";
				}else if(this.list[i].authStatus==4){
					this.list[i].maintainName="已驳回";
				}
				
				if(this.list[i].authStatus==1 && this.list[i].isTure==1 ||this.list[i].authStatus==4 && this.list[i].isTure==3 ||this.list[i].authStatus==1 && this.list[i].isTure==3){
					this.list[i].cardName="不为空";
				}else if(this.list[i].authStatus==3 && this.list[i].isTure==2 || this.list[i].authStatus==4 && this.list[i].isTure==2||this.list[i].authStatus==1 && this.list[i].isTure==2){
					this.list[i].realname="dd";
				}else if(this.list[i].authStatus==3 && this.list[i].isTure==3 || this.list[i].authStatus==4 && this.list[i].isTure==1){
					this.list[i].dicAlias="dddd";
				}
				
				var pictures=this.list[i].pictureUrl;
				if(pictures==null){
					this.list[i].pictureUrl="./img/fangchan.jpg";
				}
				html += template(this.list[i]);
			}
			$(this.container_id_selector).prepend(html);
	}

	this.dataFilter = function (data,totalPage) {
		// body...
		// data type wrong ?
		// right ?
			//
		this.list = data;
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
	            url: self._url+"/agent/back/**/newHouse/responsible/newHouseResponsibleList",
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