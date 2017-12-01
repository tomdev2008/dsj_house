$.fn.rentList = function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.cardTemplate_id_selector = "#"+option.cardTemplate_id;
	this.rowTemplate_id_selector = "#"+option.rowTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.renderStyle = "card";//"row"
	this.list = [
	];
	
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
	this.firstSearch = function(data) {
		this.dataFilter(data)
		this.render();
	}
	this.someData = function(argument) {

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
			type: "GET",
            url: option.url,
            data: {"aoData":JSON.stringify(aoData)},
            dataType: "json",
            success: function(data){
            	self.firstSearch(data);
            }
		});
	}
}

function upOrDownMaster(id,status){
	var msg="确定下架吗?";
	if(status==2){
		var msg="确定上架吗?";
	}
	setModelContent(msg,"upOrDownMasterModalConfirm");
	var _id = new Array();
	_id.push(id);
	upOrDwonAjax(_id,status)
}

function upOrDownMasters(status){
	var ids=new Array();
	 $("input[name=checkitem]:checked").each(function(){
		 ids.push($(this).val());
	});
	 if(ids.length==0){
			alert("还没有选择选项！");
	}else{
		var msg="确定下架吗?";
		if(status==2){
			var msg="确定上架吗?";
		 }
		setModelContent(msg,"upOrDownMasterModalConfirm");
		upOrDwonAjax(ids,status)
	}
	
}

//上下架
function upOrDwonAjax(ids,status){
	$("body").on("click","#upOrDownMasterModalConfirm",function(){
		$.ajax({
			type:"post",
			async:true,
			data:{"ids":ids,"status":status},
			dataType:"json",
			url:_url+"/back/rentHouse/general/origin/origin_upDown",
			success:function(resultVo){
				if(resultVo.status!=200){
					// setErrorContent(resultVo.message);
				}else{
					$("#isSureCancel").click();
					//location.reload();
					var houseOption = {
						container_id : "dsj_list",
						cardTemplate_id : "dsj_card_template",
						rowTemplate_id : "dsj_row_template",
						pageTemplate_id : "dsj_page_template",
						url : _url+"/back/rentHouse/general/origin/page/list"
			        }
			        var houseList = new $.fn.rentList(houseOption);
			        houseList._init();
			        $('#search-button').click(function() {
			        	houseList.searchAjax();
			        })
				}
			}
		})
	});
}
//推荐
function recommendMaster(id,recommend){
	var _id = new Array();
	_id.push(id);
	$.ajax({
		type:"post",
		async:true,
		data:{"ids":_id,"recommend":recommend},
		dataType:"json",
		url:_url+"/back/rentHouse/general/origin/recommendMaster",
		success:function(resultVo){
			if(resultVo.status!=200){
				// setErrorContent(resultVo.message);
			}else{
				if(resultVo.data>10){
					alert("您已推荐超过10个租房房源");
					return false;
				}
				$("#isSureCancel").click();
				var houseOption = {
					container_id : "dsj_list",
					cardTemplate_id : "dsj_card_template",
					rowTemplate_id : "dsj_row_template",
					pageTemplate_id : "dsj_page_template",
					url : _url+"/back/rentHouse/general/origin/page/list"
		        }
		        var houseList = new $.fn.rentList(houseOption);
		        houseList._init();
		        $('#search-button').click(function() {
		        	houseList.searchAjax();
		        })
			}
		}
	})
}

//展示房源在首页
function showOrigin(id,show){
	var _id = new Array();
	_id.push(id);
	$.ajax({
		type:"post",
		async:true,
		data:{"ids":_id,"show":show},
		dataType:"json",
		url:_url+"/back/rentHouse/general/origin/showOrigin",
		success:function(resultVo){
			if(resultVo.status!=200){
				// setErrorContent(resultVo.message);
			}else{
				if(show == 1 && resultVo.data>= 3){
					alert("您最多可设置3个展示首页的房源");
					return false;
				}
				$("#isSureCancel").click();
				var houseOption = {
					container_id : "dsj_list",
					cardTemplate_id : "dsj_card_template",
					rowTemplate_id : "dsj_row_template",
					pageTemplate_id : "dsj_page_template",
					url : _url+"/back/rentHouse/general/origin/page/list"
		        }
		        var houseList = new $.fn.rentList(houseOption);
		        houseList._init();
		        $('#search-button').click(function() {
		        	houseList.searchAjax();
		        })
			}
		}
	})
}

function editOrigin(id,show){
	if(show){
		location=_url+"/app/rent-origin-form.html?id="+id+"&show="+show;
	}else{
		location=_url+"/app/rent-origin-form.html?id="+id;
	}
	
}
function prep(e){
	e = e || window.event; 
	e.stopPropagation()
}

function newOrigin(){
	location=_url+"/app/rent-origin-form.html";
}