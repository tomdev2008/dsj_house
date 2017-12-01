$.fn.IndustryHouseNews = function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.dialogTemplate_id_selector = "#"+option.dialogTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	
	this.list = [];
	
	this.url = option.url;

    this.pageOperation = {
	    currentPage:1,
	    queryPage:1,
	    totalPage:1,
	    nextButton:"",
	    prevButton:"",
	 };

	this._init = function (argument) {
		//初始化data
		this.getPageData();
	}
	this.firstSearch = function(data) {
		this.dataFilter(data)
		this.render();
	}
	
	this.getPageData = function(argument) {
		var aoData=new Array();
		var self = this;
		self.formToJson(aoData);
		$.ajax({
		  type: "POST",
		  url: self.url+"/back/houseNews/getIndustryNews",
		  data: {"aoData":JSON.stringify(aoData)},
		  dataType: "json",
		  success: function(data){
		    self.dataFilter(data)
		    self.render();
		  }
		});
	}
	
	this.formToJson = function(aoData){
		var self = this;
		aoData.push({"name":"page","value":self.pageOperation.queryPage,"industry":"yes"});
	}

	this.render = function (argument) {
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
		this.list = data.data;
	    this.pageOperation.totalPage = data.totalPage;

	}
	this.pageEvent = function(argument) {
		var self = this;
		$(this.container_id_selector).find(".prev").click(function(e) {
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

/**
 * 置顶
 */
function setTop(id){
	$.ajax({
		type: "POST",
		url: _url+"/back/houseNews/set_stick",
		data: {
			id:id
		},
		dataType: "json",
		success: function(resultVo){
			if(resultVo.status!=200){
				alert(resultVo.data);
			}else{
				var houseOption = {
	    		  container_id : "container_id",
	              dialogTemplate_id : "dsj_building_news",
	              pageTemplate_id : "dsj_page_template",
	              url : _url
	            }
	            var houseList = new $.fn.HouseNews(houseOption);
	            houseList._init();
			}
		}
	});
}

/**
 * 删除动态
 */
function delNews(id){
	var _ids = new Array();
	_ids.push(id);
	$.ajax({
		type: "POST",
		url: _url+"/back/houseNews/delNews",
		data: {
			ids:_ids
		},
		dataType: "json",
		success: function(resultVo){
			if(resultVo.status!=200){
				alert(resultVo.data);
			}else{
				var houseOption = {
	    		  container_id : "container_id",
	              dialogTemplate_id : "dsj_building_news",
	              pageTemplate_id : "dsj_page_template",
	              url : _url
	            }
	            var houseList = new $.fn.HouseNews(houseOption);
	            houseList._init();
			}
		}
	});
}

//新建楼盘动态
function newHouseNews(id){
	if(id){
		location = _url+"/app/house-news-form.html?id="+id;
	}else{
		location = _url+"/app/house-news-form.html";
	}
}
