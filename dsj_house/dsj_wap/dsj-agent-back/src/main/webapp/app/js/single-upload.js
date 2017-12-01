function singleUpload(filePicker,showImg,licenseUri){
	//图片上传
    var fileTypes = ["jpg","jpeg","png"];
    var uploader = WebUploader.create({
        auto: true,
        pick: {
         id:'#'+filePicker,
         multiple :false
         },
         compress:false,
        formData: { },
        swf:"./Uploader.swf",
        chunked: false,
        server: _url+"/back/upload",//上传的URL
        threads:1,
        accept: {
            title: 'Images',
            extensions: fileTypes.join(",")
        },
        // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
        disableGlobalDnd: true,
        fileNumLimit: 50,
        fileSizeLimit: 0,    // 全部文件最大值
        fileSingleSizeLimit: 0   // 单个文件最大值
    });

    uploader.on( 'fileQueued', function( file) {
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                return;
            }
            $("#"+showImg).attr( 'src', src );
        }, 200, 200 );
    });

    uploader.on( 'uploadError', function( file,reason ) {
        alert(' 上传出错');
    });
    
	 // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });

    uploader.onError = function (type){
        if (type=="Q_TYPE_DENIED"){
            //alert("文件类型不支持！请选择jpg、png、jpeg等格式的文件");
            setErrorContent("文件类型不支持！请选择jpg、png、jpeg等格式的文件");
        }
    };

    uploader.on( 'uploadAccept', function( object ,ret ) {
		var url="";
		if(typeof ret=="object"){
			url=ret._raw;
		}else{
			url=ret;
		}
       $("#licenseUri").val(url);
	});
	uploader.on( 'uploadSuccess', function( file ,response  ) {
		var url="";
		if(typeof response=="object"){
			url=response._raw;
		}else{
			url=response;
		}
	    $("#"+licenseUri).val(url);
	});
	
}