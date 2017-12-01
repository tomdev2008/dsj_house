$.fn.DialogList = function (option) {
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
		//初始化动态
		$(document).on("click",".dsj_comment",function(argument) {
			$(this).closest(".dsj_update_time").next(".dsj_publish").toggleClass("hide")
		})
		//初始化评论事件绑定
		$(document).on("click",".toggle_reply",function(argument) {
		  var id = $(this).attr("data-id");
		  var typeId = $(this).attr("data-type-id");
		  $(this).parent().next().toggleClass("hide")
			var commentOption = {
			  container: $(this).parent().next(),
			  pageTemplate_id : "dsj_page_template",
			  commentTemplate_id : "dsj_comment_template",
			  url : option.url,
			  id : id,
			  type_id : typeId
			}
			var commentList = new $.fn.commentList(commentOption);
			commentList._init();
		})
		
		$(document).on("refresh",".toggle_reply",function(argument) {
		  var id = $(this).attr("data-id");
		  var typeId = $(this).attr("data-type-id");
		  //$(this).parent().next().toggleClass("hide")
			var commentOption = {
			  container: $(this).parent().next(),
			  pageTemplate_id : "dsj_page_template",
			  commentTemplate_id : "dsj_comment_template",
			  url : option.url,
			  id : id,
			  type_id : typeId
			}
			var commentList = new $.fn.commentList(commentOption);
			commentList._init();
		})
		this.getPageData();
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
		  url: self.url+"/back/agentNews/getNews",
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
 * type 经纪人动态点赞 1 评论赞 2
 * flag 顶 1  踩 2
 */
function cllickLike(self,id,type,flag){
	$.ajax({
		type: "POST",
		url: _url+"/back/agentNews/like",
		data: {
			id : id ,
			type : type ,
			flag : flag
		},
		dataType: "json",
		success: function(resultVo){
			if(resultVo.status!=200){
				alert(resultVo.data);
			}else{
				//改变点赞的数
				var num = $(self).closest("a").find(".dsj_num").text();
				$(self).closest("a").find(".dsj_num").text(parseInt(num)+1);
			}
		}
	});
}

/**
 * 发布动态
 */
function publishNews(self){
	var content = $("#newsContent").val().trim();
	var picArr = $(".news_pic_upload");
	var pictures = "";
	if(content == ""){
		$("#newsContent").val("");
		alert("不能为空");
		return ;
	} 
	for(var i=0;i<picArr.length; i++){
		pictures+=picArr[i].src+",";
	}
	$.ajax({
		type: "POST",
		url: _url+"/back/agentNews/addAgentNews",
		data: {
			content:content,
			pictures:pictures
		},
		dataType: "json",
		success: function(resultVo){
			if(resultVo.status!=200){
				alert(resultVo.data);
			}else{
				var dialogOption = {
		          container_id : "dsj_dialog_container",
		          dialogTemplate_id : "dsj_agent_news",
		          pageTemplate_id : "dsj_page_template",
		          commentTemplate_id : "dsj_comment_template",
		          url : _url
		        }
		        var dialogList = new $.fn.DialogList(dialogOption);
		        dialogList.getPageData();
		        
		        $("#newsContent").val("");
		        $(".news_pic_upload").closest(".col-xs-4").remove();
		        $('.dsj_upload_pic').css('display','none');
		        $(self).closest(".dsj_publish").find(".textNum").text("2000");
			}
		}
	});
}

/**
 * 发布评论
 * @param newsId 动态id
 * @param replyUserId  被回复的人
 */
function publishComment(self,newsId,replyUser){
	//回复内容
	var content = $(self).closest(".dsj_publish").find("textarea").val().trim();
	if(content==""){
		$(self).closest(".dsj_publish").find("textarea").val("");
		alert("不能为空");
		return ;
	} 
	$.ajax({
		type: "POST",
		url: _url+"/back/comment/addComment",
		data: {
			objectId : newsId,
			objectType : 1, //type	1：经纪人动态评论2：楼盘动态评论，3：经纪人对楼盘点评 
			replyUser : replyUser,
			content : content
		},
		dataType: "json",
		success: function(resultVo){
			if(resultVo.status!=200){
				alert(resultVo.data);
			}else{
				//清空回复内容并隐藏窗口
				$(self).closest(".dsj_publish").find("textarea").val("");
				$(self).closest(".dsj_publish").find(".textNum").text("100");
				$(self).closest(".dsj_publish").toggleClass("hide");
				//改变回复数
				var commentNum = $(self).closest(".dsj_news_main").find(".dsj_news_time").find(".dsj_comment").find(".dsj_num").text();
				if(commentNum == '0'){
					$(self).closest(".dsj_news_main").find(".toggle_reply").toggleClass("hide");
				}
				$(self).closest(".dsj_news_main").find(".dsj_news_time").find(".dsj_comment").find(".dsj_num").text(parseInt(commentNum)+1);
				$(self).closest(".dsj_news_main").find(".toggle_reply").find(".dsj_num").text(parseInt(commentNum)+1);
				$(self).closest(".dsj_news_main").find(".toggle_reply").trigger("refresh");
			}
		}
	});
}


//输入字数控制
function checkMaxInput(self, maxLen) {
	var num = maxLen-$(self).val().length;
	$(self).closest(".dsj_publish").find(".textNum").text(num);
	if(num<=0){
		$(self).val($(self).val().substring(0,maxLen-1));
	}
}  