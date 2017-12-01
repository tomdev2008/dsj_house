$.fn.houseNewsAdd = function (option) {
	this._init = function (argument) {
		self=this;
		$.ajax({
            type: "GET",
            url: option.url,
            async:false,
            data:{},
            dataType: "json",
            success: function(data){
              self.render(data.data);
            }
		 });
		
		$("#houseSelectId").select2({
			"ajax":{
			    url: _url+"/back/oldHouse/master/getDic",
			    async:false,
			    data: function (params) {
			      var query = { //请求的参数, 关键字和搜索条件之类的
			        name: params.term //select搜索框里面的value
			      }
			      return query;
			    },
			    delay: 1500,
			    processResults: function (data, params) {
			      //返回的选项必须处理成以下格式
			      var results = data.data;
			      return {
			        results: results  //必须赋值给results并且必须返回一个obj
			      };
			    }
			  }
		});
		
	}
	
	this.render=function(data){
		var source = $("#master_add").html();
        var template = Handlebars.compile(source);
	    var html = template(data);
        $("#dsj_form").prepend(html);
        //convertText();
        editor = UE.getEditor('saveEditor');
//      editor = new UE.ui.Editor();
//    	editor.render("saveEditor");
        editor.ready(function() {
        	if($("#ueHidden").text()){
        		editor.setContent($("#ueHidden").text());
        	}
        });
        if("yes" == option.show){
        	editor.ready(function() {
        		editor.setDisabled('fullscreen');
            });
        }
        singleUpload("filePicker1","showImg1","licenseUri1");
        $("#edui72").hide();
	}

}

var clickOnce = true;
function saveOrUpdate(){
	if(!clickOnce){
		return false;
	}
	var html = editor.getContent();
	var html1 = editor.getContentTxt();
	if(html1.trim().length == 0){
		$("#jqk").removeClass("hide");
		return false;
	}
	$("#dsj_form").verify();
	$("#dsj_form").validate(function (result) {
		if(result){
			var msg="您确认提交吗?";
	    	setModelContent(msg,"saveOrUpdateConfirm");
	    	upOrDwonAjax();
		}
	})
}

function upOrDwonAjax(){
	$("body").one("click","#saveOrUpdateConfirm",function(){
		clickOnce = false;
		var contentTxt = editor.getContentTxt().trim();
		var maxLength = 90;
		if(contentTxt!=null){
			if(contentTxt.length > maxLength){
				$("#contentst").val(contentTxt.substring(0,maxLength)+"...");
			}else{
				$("#contentst").val(contentTxt);
			}
		}
		//第一张图片
		var mypattern = /http:\/\/dasouk\.oss-cn-qingdao.{62,66}\"{1}/;
		var	strcon = editor.getContent().trim();
		var m = strcon.match(mypattern);
		if(m){
			$("#pictureUrl").val(m[0].replace("\"",""));
		}
		$.ajax({
			type:"post",
			async:false,
			data:$("#dsj_form").serialize(),
			dataType:"json",
			url:_url+"/back/houseNews/addHouseNews",
			success:function(resultVo){
				$("#isSureCancel").click();
				if(resultVo.status!=200){
					setErrorContent(resultVo.message);
				}else{
					//点击执行确定调用的方法
					setErrorContent("工作人员将在24小时内审核，请您及时关注审核结果。");
					toBack();
				}
			}
		})
	});
}

function toBack(){
	$("body").one("click","#sureButton",function(){
		location = _url+"/app/house-news.html";
	});
}

function cancel(){
	location = _url+"/app/house-news.html";
}

function changeHouseId(self){
	$("#houseId").val($(self).find("option:selected").val());
}

$(function(){
	$("body").on("click","#isSureCancel",function(){
		$("body").off("click","#saveOrUpdateConfirm");
	});
	
	$("body").on("click",".kkclose",function(){
		$("body").off("click","#saveOrUpdateConfirm");
	});
})
 
 function convertText(){
	 var xx=document.getElementById("contentDis").innerText;
	 document.getElementById("content").innerHTML=xx;
 }

function yulan(){
	$(document).off('hidden.bs.modal');
	var	conStr = editor.getContent().trim();
	$("#yulan_cc").html(conStr);
}
