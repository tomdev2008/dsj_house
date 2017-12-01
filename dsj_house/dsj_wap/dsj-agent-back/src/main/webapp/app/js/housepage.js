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
	
	this.url ="";
    this.pageOperation = {
	    currentPage:1,
	    queryPage:1,
	    totalPage:1,
	    nextButton:"",
	    prevButton:"",
	 };


	this._init = function (argument) {
		var self = this
		self.renderStyle = getLocalStyle();
		$("#card_view").click(function(argument) {
			self.renderStyle = "card";
			setLocalStyle("card");
			self.render();
		})
		$("#row_view").click(function(argument) {
			self.renderStyle = "row";
			setLocalStyle("row");
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
		var aoData=new Array();
		var self = this;
		self.formToJson(aoData);
		$.ajax({
		  type: "POST",
		  url: option.url,
		  data: {"aoData":JSON.stringify(aoData)},
		  dataType: "json",
		  success: function(data){
			  self.firstSearch(data)
		  }
		});
	}
	
	this.formToJson=function(aoData){
		var self = this;
		aoData.push({"name":"page","value":self.pageOperation.queryPage});
		aoData=aoDataChange("dsj_form",aoData);
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
			var aoData=new Array();
			var self = this;
			self.formToJson(aoData);
		   $.ajax({
	            type: "POST",
	            url: option.url,
	            data: {"aoData":JSON.stringify(aoData)},
	            dataType: "json",
	            success: function(data){
	              self.firstSearch(data);
	            }
	      });
	}
}

function getLocalStyle() {
	var renderStyle = localStorage.renderStyle ? localStorage.renderStyle : "card";
	localStorage.clear();
	return renderStyle;
}

function setLocalStyle(style) {
	localStorage.renderStyle = style;
}


function editMaster(id){
	location=_url+"/app/ershou-edit-form.html?id="+id;
}

function upOrDownMaster(id,status){
	var msg="确定下架吗?";
	if(status==2){
		var msg="确定上架吗?";
	}
	setModelContent(msg,"upOrDownMasterModalConfirm");
	upOrDwonAjax(id,status)
}

function upOrDownMasters(status){
	var ids=new Array();
	 $("input[name=checkitem]:checked").each(function(){
		 ids.push($(this).val());
	});
	 if(ids.length==0){
			alert("还没有选择选项！");
	}else{
		var msg="确定上架吗?";
		if(status==3){
			var msg="确定下架吗?";
			
		 }
		setModelContent(msg,"upOrDownMasterModalConfirm");
		upOrDwonAjax(ids,status)
	}
	
}

function upOrDwonAjax(ids,status){
	  $("body").on("click","#upOrDownMasterModalConfirm",function(){
		  $.ajax({
				type:"post",
				async:true,
				data:{"ids[]":ids,"status":status},
				dataType:"json",
				url:_url+"/back/oldHouse/master/master_upDown",
				success:function(resultVo){
					if(resultVo.status!=200){
						// setErrorContent(resultVo.message);
					}else{
						//modalSuccess("发布成功");
						$("#isSureCancel").click();
						location.reload();
					}
				}
		})
	  });
}

function cancleOrRecommendMaster(id,type){
	var msg="是否推荐?";
	if(type==2){
		msg="是否取消推荐?"
	}
	setModelContent(msg,"cancleOrRecommendMasterModalConfirm");
	  $("body").on("click","#cancleOrRecommendMasterModalConfirm",function(){
		  $.ajax({
				type:"post",
				async:true,
				data:{"id":id,"type":type},
				dataType:"json",
				url:_url+"/back/oldHouse/master/recomment",
				success:function(resultVo){
					if(resultVo.status!=200){
						$("#isSureCancel").click();
						setErrorContent(resultVo.message);
					}else{
						//modalSuccess("发布成功");
						$("#isSureCancel").click();
						location.reload();
					}
				}
		})
	  });
}

function toAdd(){
	location=_url+"/app/ershou-form.html";
}

function view(id){
	location=_url+"/app/ershou-veiw-form.html?id="+id;
}

function showShelvesMaster(id,type){
	var msg="是否消展示?"
	if(type==1){
		msg="是否展示在货架?"
	}
	setModelContent(msg,"showShelvesMasterConfirm");
	  $("body").on("click","#showShelvesMasterConfirm",function(){
		  $.ajax({
				type:"post",
				async:true,
				data:{"id":id,"type":type},
				dataType:"json",
				url:_url+"/back/oldHouse/master/showShelves",
				success:function(resultVo){
					if(resultVo.status!=200){
						$("#isSureCancel").click();
						setErrorContent(resultVo.message);
					}else{
						//modalSuccess("发布成功");
						$("#isSureCancel").click();
						location.reload();
					}
				}
		})
	  });
}


