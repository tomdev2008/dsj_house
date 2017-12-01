;(function($){
		$.fn.extend({
			// 给jQuery原型扩展一个添加气泡的方法
			bindTip:function(){      
				var element = $("<div id='popo'></div>");  //创建一个div
				this.on({      //绑定多个事件            
					keyup:function(){
						$(this).val().length<10?element.html($(this).val()):element.html($(this).val().slice($(this).val().length-10,$(this).val().length));//获取当前对象的val赋值到气泡div中
						$("#popo").size()>0?element.show():$("body").append(element); //如果已经创建了气泡就显示,没有创建气泡就创建气泡
						element.css({  //给气泡定位
							"top":$(this).offset().top - element.outerHeight(),
							"left":$(this).offset().left
						});
					},
					blur:function(){
						element.html("").hide(); //失去焦点的时候让气泡的内容为空且隐藏气泡
					}
					})
			},
			// hover的事件
			bindHover:function(){      
				var element = $("<div id='popo'></div>");  //创建一个div
				this.on({      //绑定多个事件            
					mouseover:function(){
						element.html($(this).text());//获取当前对象的html赋值到气泡div中
						$("#popo").length>0?element.show():$("body").append(element); //如果已经创建了气泡就显示,没有创建气泡就创建气泡
						element.css({  //给气泡定位
							"top":$(this).offset().top - element.outerHeight(),
							"left":$(this).offset().left
						});
					},
					mouseout:function(){
						element.html("").hide(); //失去焦点的时候让气泡的内容为空且隐藏气泡
					}
					})
			},
			// 给jQuery原型扩展一个定时小广告
			dingShi:function(){      
				var dsDiv = $("#dingShi"); //找到定时div
					//调用定时器
					var bot = -300;
					var time1 = setInterval(function(){
						 bot++;
						 dsDiv.css({
						 	bottom:bot+"px"
						 });
						 if(bot>=0){
						 	clearInterval(time1);
						 }
					},10);
					dsDiv.find("span").on({      //绑定多个事件            
						click:function(){   //点击span后让小广告收起
							clearInterval(time1);
							var time2 = setInterval(function(){
							 bot--;
							 dsDiv.css({
							 	bottom:bot+"px"
							 });
							 if(bot<=-300){
							 	clearInterval(time2);
							 	var time3 = setTimeout(function(){
							 		clearInterval(time1);
							 		time1 = setInterval(function(){
										 bot++;
										 dsDiv.css({
										 	bottom:bot+"px"
										 });
										 if(bot>=0){
										 	clearInterval(time1);
										 }
									},10);
							 	},6000000);
							 }
							},10);
						}
					})
			},
			// 给jQuery原型扩展一个随机验证码的方法
			getCode: function(num){
				//如果不传参
				if(num == undefined){
					return this;
				}
				//创建一个div
				var myDiv = $("<div>").css({
					width: '85px',
					height: '30px',
					textAlign:'center',
					lineHeight:'30px',
					borderRadius: '5px',
					backgroundColor: '#d9d9d9',
					margin: '0 10px 0 0',
					padding:' 0 0 0 10px',
					float: 'left',
					display: "flex",
					lineHeight: '30px',
				}),		
				//建立一个字符串包含生成验证码的字符串
				strNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
				//创建span接收每一个数字
				for(var i = 0 ; i < num; i++){
					myDiv.append($("<span>"));
				}
				//把myDiv添加的指定的标签里并指定事件
				myDiv.insertAfter("#yangzma").on("click", function(e){
					//阻止冒泡
					e.stopPropagation();
					//span里面的验证码数字改变,颜色也改变
					myDiv.find("span").each(function(){
						$(this).text(strNum[$.fn.getNum(0, strNum.length)]).css({"color": "rgb(" + $.fn.getNum(0, 256) + ", " + $.fn.getNum(0, 256) + "," + $.fn.getNum(0, 256) + ")", marginRight: "5px",textAlign: "center", display: "block"})
					});
				});
				//用triggerHandler()触发绑定事件
				$(myDiv).insertAfter("#yangzma").triggerHandler("click");
			},
			//给jQuery原型扩展一个随机生成一个min，到max (不包括max)的数的方法
			getNum:function(min, max){
			return Math.floor(Math.random()*(max - min) + min);
			}
		});
	})(jQuery);




