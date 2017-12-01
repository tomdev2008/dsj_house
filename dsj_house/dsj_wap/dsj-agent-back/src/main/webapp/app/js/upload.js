
//二手房房源
function successOldMasterUpload(arr,pictureType,objId,objType){
	var data="";
	var path = "";
	if(objType == 1){
		path = "/back/oldHouse/master/update_master_image";
	}else if(objType == 3){
		path = "/back/rentHouse/general/origin/update_origin_image";
	}
	$.ajax({
		type:"post",
		async:false,
		data:{"imageUrls":arr,"pictureType":pictureType,"id":objId},
		dataType:"json",
		url:_url+path,
		success:function(resultVo){
			if(resultVo.status!=200){
				
			}else{
				data=resultVo.data;
			}
		}
	})
	return data;
}

function uploadImage(wrapper,filePicker,pictureType,objId,objType){
	$(document).on("click",wrapper+" .add-more",function(argument) {
        $("#"+filePicker).find("label").trigger('click');;
    })
    var arr = new Array();
    var $wrap = $(wrapper),

        // 图片容器
        $queue = $wrap,

        // 状态栏，包括进度和控制按钮
        // $statusBar = $wrap.find( '.statusBar' ),

        // 文件总体选择信息。
        // $info = $statusBar.find( '.info' ),


        // 没选择文件之前的内容。
        // $placeHolder = $wrap.find( '.placeholder' ),

        // $progress = $statusBar.find( '.progress' ).hide(),

        // 添加的文件数量
        fileCount = 0,

        // 添加的文件总大小
        fileSize = 0,

        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 110 * ratio,
        thumbnailHeight = 110 * ratio,

        // 可能有pedding, ready, uploading, confirm, done.
        state = 'pedding',

        // 所有文件的进度信息，key为file id
        percentages = {},
        // 判断浏览器是否支持图片的base64
        isSupportBase64 = ( function() {
            var data = new Image();
            var support = true;
            data.onload = data.onerror = function() {
                if( this.width != 1 || this.height != 1 ) {
                    support = false;
                }
            }
            data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
            return support;
        } )(),

        // 检测是否已经安装flash，检测flash的版本
        flashVersion = ( function() {
            var version;

            try {
                version = navigator.plugins[ 'Shockwave Flash' ];
                version = version.description;
            } catch ( ex ) {
                try {
                    version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                            .GetVariable('$version');
                } catch ( ex2 ) {
                    version = '0.0';
                }
            }
            version = version.match( /\d+/g );
            return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
        } )(),

        supportTransition = (function(){
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                        'WebkitTransition' in s ||
                        'MozTransition' in s ||
                        'msTransition' in s ||
                        'OTransition' in s;
            s = null;
            return r;
        })(),

        // WebUploader实例
        uploader;


    if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {

        // flash 安装了但是版本过低。
        if (flashVersion) {
            (function(container) {
                window['expressinstallcallback'] = function( state ) {
                    switch(state) {
                        case 'Download.Cancelled':
                            alert('您取消了更新！')
                            break;

                        case 'Download.Failed':
                            alert('安装失败')
                            break;

                        default:
                            alert('安装已成功，请刷新！');
                            break;
                    }
                    delete window['expressinstallcallback'];
                };

                var swf = './expressInstall.swf';
                // insert flash object
                var html = '<object type="application/' +
                        'x-shockwave-flash" data="' +  swf + '" ';

                if (WebUploader.browser.ie) {
                    html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                }

                html += 'width="100%" height="100%" style="outline:0">'  +
                    '<param name="movie" value="' + swf + '" />' +
                    '<param name="wmode" value="transparent" />' +
                    '<param name="allowscriptaccess" value="always" />' +
                '</object>';

                container.html(html);

            })($wrap);

        // 压根就没有安转。
        } else {
            $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
        }

        return;
    } else if (!WebUploader.Uploader.support()) {
        alert( 'Web Uploader 不支持您的浏览器！');
        return;
    }

    // 实例化
    uploader = WebUploader.create({
        pick: {
            id: '#'+filePicker,
            label: '点击选择图片'
        },
        auto:true,
        formData: {
            // uid: 123
        },
        swf: './Uploader.swf',
        chunked: false,
        duplicate :true,
        chunkSize: 512 * 1024,
        server: _url+"/back/upload",
        // runtimeOrder: 'flash',

        // accept: {
        //     title: 'Images',
        //     extensions: 'gif,jpg,jpeg,bmp,png',
        //     mimeTypes: 'image/*'
        // },

        // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
        disableGlobalDnd: true,
        fileNumLimit: 10,
        fileSizeLimit: 200 * 1024 * 1024,    // 200 M
        fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
    });


    uploader.on('ready', function() {
        window.uploader = uploader;
    });
    
    function reader(data){
     
     var source   = $("#dsj_uploader_template").html();
     var template = Handlebars.compile(source);
     var html =  template(data);
     
     
   	 $(wrapper+" .add-more").parent().parent().before($(html));
   }
    // 当有文件添加进来时执行，负责view的创建
    function addFile( file ) {

        // var div = $queue.append(html);

       // var $li = $(html),
     //   $wrap = $li.find( '.dsj_source_img' ),

            // $btns = $$li('<div class="file-panel">' +
            //     '<span class="cancel">删除</span>' +
            //     '<span class="rotateRight">向右旋转</span>' +
            //     '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
            // $prgress = $li.find('p.progress span'),
         
            // $info = $('<p class="error"></p>'),

            showError = function( code ) {
                switch( code ) {
                    case 'exceed_size':
                        text = '文件大小超出';
                        break;

                    case 'interrupt':
                        text = '上传暂停';
                        break;

                    default:
                        text = '上传失败，请重试';
                        break;
                }

                // $info.text( text ).appendTo( $li );
            };

        if ( file.getStatus() === 'invalid' ) {
            showError( file.statusText );
        } else {
            // @todo lazyload
            //$wrap.text( '预览中' );
            uploader.makeThumb( file, function( error, src ) {
                var img;

                if ( error ) {
                    $wrap.text( '不能预览' );
                    return;
                }

                if( isSupportBase64 ) {
                    /*img = $('<img class="img-responsive" src="'+src+'">');
                    $wrap.empty().append( img );*/
                } else {
                    $.ajax('../../server/preview.php', {
                        method: 'POST',
                        data: src,
                        dataType:'json'
                    }).done(function( response ) {
                        if (response.result) {
                            img = $('<img src="'+response.result+'">');
                            $wrap.empty().append( img );
                        } else {
                            $wrap.text("预览出错");
                        }
                    });
                }
            }, thumbnailWidth, thumbnailHeight );

            percentages[ file.id ] = [ file.size, 0 ];
            file.rotation = 0;
        }

        file.on('statuschange', function( cur, prev ) {

            // 成功
            if ( cur === 'error' || cur === 'invalid' ) {
                showError( file.statusText );
                percentages[ file.id ][ 1 ] = 1;
            } else if ( cur === 'interrupt' ) {
                showError( 'interrupt' );
            } else if ( cur === 'queued' ) {
                // $info.remove();
                // $prgress.css('display', 'block');
                percentages[ file.id ][ 1 ] = 0;
            } else if ( cur === 'progress' ) {
                // $info.remove();
                // $prgress.css('display', 'block');
            } else if ( cur === 'complete' ) {
                // $prgress.hide().width(0);
              //  $li.prepend( '<span class="success"></span>' );
            }

           // $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
        });

        
        //$li.appendTo( $queue );
//        .prependTo( );
       // $(wrapper+" .add-more").parent().parent().before($li);
    }
    
  

    // 负责view的销毁
    function removeFile( file ) {
        var $li = $('#'+file.id);

        delete percentages[ file.id ];
        
        $li.off().find('.file-panel').off().end().remove();
    }

    function setState( val ) {
        var file, stats;

        if ( val === state ) {
            return;
        }

        state = val;

        switch ( state ) {
            case 'pedding':
                // $placeHolder.removeClass( 'element-invisible' );
                $queue.hide();
                // $statusBar.addClass( 'element-invisible' );
                uploader.refresh();
                break;

            case 'ready':
                // $placeHolder.addClass( 'element-invisible' );
                // $( '#filePicker2' ).removeClass( 'element-invisible');
                $queue.show();
                // $statusBar.removeClass('element-invisible');
                uploader.refresh();
                break;

            case 'uploading':
                // $( '#filePicker2' ).addClass( 'element-invisible' );
                // $progress.show();
                break;

            case 'paused':
                // $progress.show();
                break;

            case 'confirm':
                // $progress.hide();
                // $( '#filePicker2' ).removeClass( 'element-invisible' );

                stats = uploader.getStats();
                if ( stats.successNum && !stats.uploadFailNum ) {
                    setState( 'finish' );
                    return;
                }
                break;
            case 'finish':
                stats = uploader.getStats();
                if ( stats.successNum ) {
                   // alert( '上传成功' );
                	
                    data=successOldMasterUpload(arr,pictureType,objId,objType);
                    reader(data);
                    arr=new Array();
                } else {
                    // 没有成功的图片，重设
                    state = 'done';
                    location.reload();
                }
                break;
        }

    }

    uploader.onUploadProgress = function( file, percentage ) {
        var $li = $('#'+file.id),
            $percent = $li.find('.progress span');

        $percent.css( 'width', percentage * 100 + '%' );
        percentages[ file.id ][ 1 ] = percentage;
        
    };

    uploader.onFileQueued = function( file ) {
        fileCount++;
        fileSize += file.size;

        if ( fileCount === 1 ) {
            // $placeHolder.addClass( 'element-invisible' );
            // $statusBar.show();
        }

        addFile( file );
        setState( 'ready' );
        
    };

    uploader.onFileDequeued = function( file ) {
        fileCount--;
        fileSize -= file.size;

        if ( !fileCount ) {
            setState( 'pedding' );
        }

        removeFile( file );
        

    };

    uploader.on( 'all', function( type ) {
        var stats;
        switch( type ) {
            case 'uploadFinished':
                setState( 'confirm' );
                break;

            case 'startUpload':
                setState( 'uploading' );
                break;

            case 'stopUpload':
                setState( 'paused' );
                break;

        }
    });

    uploader.onError = function( code ) {
        alert( 'Eroor: ' + code );
    };
    
    uploader.on('uploadSuccess', function( file ,response  ) {
        arr.push(response);
    });
}

//删除图片
function removeImage(obj,objType,id){
	if(objType == 1){
		path = "/back/oldHouse/master/delete_master_image";
	}else if(objType == 3){
		path = "/back/rentHouse/general/origin/delete_image";
	}
	 $.ajax({
			type:"post",
			async:true,
			data:{"ids[]":id},
			dataType:"json",
			url:_url+path,
			success:function(resultVo){
				if(resultVo.status!=200){
					alert("删除失败");
				}else{
					obj.parentNode.parentNode.parentNode.remove();
				}
			}
	})
}

//设置封面
function setCover(obj,objType,id){
	if(objType == 1){
		path = "";
	}else if(objType == 3){
		path = "/back/rentHouse/general/origin/set_cover";
	}
	$.ajax({
		type:"post",
		async:true,
		data:{"id":id},
		dataType:"json",
		url:_url+path,
		success:function(resultVo){
			if(resultVo.status!=200){
				alert("设置失败");
			}else{
				$(".set-cover").css('background-color','');
				obj.style.backgroundColor = "#2775e9";
			}
		}
	})
}

function readerImageList(data,tempId,wrapper){
	var source   = $("#"+tempId).html();
    var template = Handlebars.compile(source);
    var html =  template(data);
    $(wrapper+" .add-more").parent().parent().before($(html));
    
}
