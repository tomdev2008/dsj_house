$.fn.HouseComment = function (option) {
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
		  url: self.url+"/back/comment/getAgentComment",
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
		aoData.push({"name":"page","value":self.pageOperation.queryPage});
		aoData=aoDataChange("dsj_form",aoData);
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
			var html = "<table class='table table-bordered table-hover'>\
							<thead>\
								<tr>\
									<th>序号</th>\
									<th>点评时间</th>\
									<th>楼盘名称</th>\
									<th>点评内容</th>\
									<th>操作</th>\
								</tr>\
							</thead><tbody>";
			for (var i = 0; i < this.list.length; i++) {
				this.list[i].index = i+1;
				html += template(this.list[i]);
			}
			html += "</tbody></table>";
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

//新建楼盘动态
function commentDetail(id){
	location = _url+"/app/comment-detail.html?id="+id;
}
