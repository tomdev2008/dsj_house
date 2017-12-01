$.fn.CommentDetailList = function (option) {
	this.container_id_selector = "#"+option.container_id;
	this.dialogTemplate_id_selector = "#"+option.dialogTemplate_id;
	this.pageTemplate_id_selector = "#"+option.pageTemplate_id;
	this.list = [];
	
	this.url = option.url;
	this.commentId = option.commentId;

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
			$(this).closest(".dsj_update_time").next(".dsj_publish").toggleClass("hide");
			//$(this).closest(".dsj_update_time").next(".dsj_publish").next(".dsj_all_reply").toggleClass("hide");
		})
		//初始化评论事件绑定
		/*$(document).on("click",".toggle_reply",function(argument) {
			location= option.url+ "/front/comment/detail?id="+$(this).attr("data-id");
		})*/
		
		$(document).on("refresh",".toggle_reply",function(argument) {
		  var id = $(this).attr("data-id");
		  var typeId = $(this).attr("data-type-id");
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
	this.firstSearch = function(data) {
		this.dataFilter(data)
		this.render();
	}
	this.getPageData = function(argument) {
		var aoData=new Array();
		var self = this;
		$.ajax({
			type: "POST",
			url: self.url+"/front/comment/getComment",
			data: {
				  pageFirst : self.pageOperation.queryPage,
				  pageSize : 1 ,
				  commentId : self.commentId,
				  newHouseId : null,
				  objectType : null
			},
			dataType: "json",
			success: function(data){
			    self.dataFilter(data)
			    self.render();
			}
		});
	}

	this.render = function (argument) {
		// body...
		$(this.container_id_selector).html('');
		this.renderList();
		//this.pageInfoRender();
		initComment(1);
	}
	this.renderList = function (argument) {
		var source   = $(this.dialogTemplate_id_selector).html();
		var template = Handlebars.compile(source);
		var html = "";
		for (var i = 0; i < this.list.length; i++) {
			if(this.list[i].picture && this.list[i].picture.length>0){
				this.list[i].picturesArr = this.list[i].picture.split(",");
			}
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
	if (user == ''){
		show_box(7);
	}else{
		$.ajax({
			type: "POST",
			url: _ctx+"/front/comment/comLike",
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
					num = num.substring(1,num.length-1);
					$(self).closest("a").find(".dsj_num").text("("+(parseInt(num)+1)+")");
					$(self).closest("a").addClass("thum_Active");
				}
			}
		});
	}
}

/**
 * 回复
 * @param newsId 评论id
 * @param replyUserId  被回复的人
 */
function publishComment(self,newsId,replyUser){
	if (user == ''){
		show_box(7);
	}else{
		//回复内容
		var content = $(self).closest(".dsj_publish").find("textarea").val().trim();
		if(content==""){
			$(self).closest(".dsj_publish").find("textarea").val("");
			show_box(3,"不能为空");
			return ;
		}
		if(!(filter(content)=="0")){
			show_box(3,"您的输入包含敏感词汇'"+filter(content)+"'，请重新填写");
			return false;
		};
		//IE9以上、FF、chrome在换行处匹配/\n/
        //IE7-8在换行处先匹配/\r/，再匹配/\n/
		content=content.replace(/\n/g, '_@').replace(/\r/g, '_#');
		content = content.replace(/_#_@/g, '<br/>');//IE7-8
		content = content.replace(/_@/g, '<br/>');//IE9、FF、chrome
		content = content.replace(/\s/g, '&nbsp;');//空格处理
		$.ajax({
			type: "POST",
			url: _ctx+"/front/comment/addComment",
			data: {
				objectId : newsId,
				objectType : 2, //type	1：经纪人动态评论2：楼盘动态评论，3：经纪人对楼盘点评 
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
					commentNum = commentNum.substring(1,commentNum.length-1);
					$(self).closest(".dsj_news_main").find(".dsj_news_time").find(".dsj_comment").find(".dsj_num").text(parseInt(commentNum)+1);
					$(self).closest(".dsj_news_main").find(".toggle_reply").find(".dsj_num").text(parseInt(commentNum)+1);
					//initComment();
					location=_ctx+"/front/comment/detail?id="+$("#commentId").val();
					/*
					$(self).closest(".dsj_news_main").find(".toggle_reply").trigger("refresh");
					*/
				}
			}
		});
	}
}


//输入字数控制
function checkMaxInput(self, maxLen) {
	var num = maxLen-($(self).val().length);
	if(num > -1){
		$(self).closest(".dsj_publish").find(".textNum").text(num);
	}
	if(num<=0){
		$(self).val($(self).val().substring(0,maxLen));
	}
}
//点评输入字数控制
function checkMaxInputDP(self, maxLen) {
	var num = maxLen-($(self).val().length);
	if(num > -1){
		$(self).closest(".user-comment-modal__reply").find(".textNum").text(num);
	}
	if(num<=0){
		$(self).val($(self).val().substring(0,maxLen));
	}
}

function filter(showContent) {
	// 多个敏感词，这里直接以数组的形式展示出来
	var arrMg = minganciArr;
	for (var i = 0; i < arrMg.length; i++) {
		if(showContent.indexOf(arrMg[i])>=0){
			return arrMg[i];
		}
	}
	return "0";
}

// fresh 1 初次加载
function initComment(fresh){
	var reply = $(".dsj_all_reply").find(".toggle_reply");
	var id = reply.attr("data-id");
	var typeId = reply.attr("data-type-id");
	if(fresh==1) reply.parent().next().toggleClass("hide");
	var commentOption = {
		container: reply.parent().next(),
		pageTemplate_id : "dsj_page_template",
		commentTemplate_id : "dsj_comment_template",
		url : _ctx,
		id : id,
		type_id : typeId
	}
	var commentList = new $.fn.commentList(commentOption);
	commentList._init();
}