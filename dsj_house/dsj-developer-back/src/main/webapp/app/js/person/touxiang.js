
function uploadHeader(){

	 $("#picker").one("click",function(argument) {
        $(".crop_container").show();
      })
      $("#picker").on("click",function(e) {
		$("#uploader label").click();
	  })
      var $wrap = $('#wrap');
      var uploader = WebUploader.create({

          // swf文件路径
          // swf: '/js/Uploader.swf',

          // 文件接收服务端。
          // server: 'http://webuploader.duapp.com/server/fileupload.php',

          // 选择文件的按钮。可选。
          // 内部根据当前运行是创建，可能是input元素，也可能是flash.
          pick: '#uploader',

          // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
          resize: false
      });
      var small,big;
      uploader.on( 'fileQueued', function( file ) {
          uploader.makeThumb( file, function( error, src ) {
              if ( error ) {
                  alert("抱歉无法预览！")
                  return;
              }
              if (small != undefined) {
                  small.changeSrc(src);
              }else {
                  small = resizeableImage('.resize-image-small')();
                  small.changeSrc(src);
                  $('.resize-image-small').show();
              }

          }, 350, 350 );
         
      });

      $('.js-crop').on('click', function(argument) {
        // body...
        
        //var bigArray = Base64Binary.decodeArrayBuffer(big.crop());   
    	  
    	
    	  setModelContent("确定上传吗？","touxiangConfirm");
   		  $("body").on("click","#touxiangConfirm",function(){
   			  
   	        $.ajax({
   	            url:"/dsj-developer-back"+ '/back/person/eveloper/head_portrait',
   	            method: 'POST',
   	            dataType :"json",
   	            data:{"smallBase64":small.crop()},
   	           // contentType: false, // 注意这里应设为false
   	           // processData: false,
   	            cache: false,
   	            success: function(data) {
   	               if(data.status==200){
   	            	$("#isSureCancel").click();
   	            	$("#headerId").attr("src",data.data);
   	            	console.info(data.data)
   	            	show_box(2,"保存成功");
   	               }
   	            },
   	            error: function (jqXHR) {
   	                console.log(JSON.stringify(jqXHR));
   	            }
   	        })
   		  })
   		
      });
}