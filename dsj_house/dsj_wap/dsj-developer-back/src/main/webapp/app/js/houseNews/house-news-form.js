$.fn.originAdd = function (option) {
	this._init = function (argument) {
		self=this;
		$.ajax({
            type: "GET",
            url: option.url,
            async:true,
            data:{},
            dataType: "json",
            success: function(data){
              self.render(data.data);
            }
		 });
	}
	
	this.render=function(data){
		var source = $("#master_add").html();
        var template = Handlebars.compile(source);
	    var html = template(data);
        $("#dsj_form").prepend(html);
        //convertText();
        
        editor = new UE.ui.Editor();
    	editor.render("saveEditor");
        editor.ready(function() {
        	editor.setContent($("#ueHidden").text());
        });
        if("yes" == option.show){
        	editor.ready(function() {
        		editor.setDisabled('fullscreen');
            });
        }
        singleUpload("filePicker1","showImg1","licenseUri1");
	}

}

function saveOrUpdate(){
	if(!confirm('确定要发布吗?')) { 
		return false; 
	}
	var html1 = editor.getContentTxt();
	if(html1.trim().length == 0){
		$("#jqk").removeClass("hide");
		return false;
	}
	
	$("#dsj_form").verify();
	$("#dsj_form").validate(function (result) {
		if(result){
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
	  			async:true,
	  			data:$("#dsj_form").serialize(),
	  			dataType:"json",
	  			url:_url+"/back/houseNews/addHouseNews",
	  			success:function(resultVo){
	  				if(resultVo.status!=200){
						setErrorContent(resultVo.message);
					}else{
						location=_url+"/app/responsible.html";
					}
	  			}
			})
		}
	})
}

function cancel(){
	location=_url+"/app/responsible.html";
}

function changeHouseId(self){
	$("#houseId").val($(self).find("option:selected").val());
}

function convertText(){
	 var xx=document.getElementById("contentDis").innerText;
	 document.getElementById("content").innerHTML=xx;
}

function yulan(){
	$(document).off('hidden.bs.modal');
	var	conStr = editor.getContent().trim();
	$("#yulan_cc").html(conStr);
}