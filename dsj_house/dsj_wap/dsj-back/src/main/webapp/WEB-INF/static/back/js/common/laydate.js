if (typeof layui == "object") {
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	
	    var start = {
	        min: "1900-01-01 00:00:00", 
	        max: '2099-06-16 23:59:59'
	        , istoday: true
	        ,istime: false
	        ,format: 'YYYY-MM-DD'
	        , choose: function (datas) {
	            end.min = datas; //开始日选好后，重置结束日的最小日期
	            end.start = datas //将结束日的初始值设定为开始日
	        }
	    };
	    
	    var end = {
	        min: "1900-01-01 00:00:00",
	        max: '2099-06-16 23:59:59'
	        , istoday: true
	        ,istime: false
	        ,format: 'YYYY-MM-DD'
	        , choose: function (datas) {
	            start.max = datas; //结束日选好后，重置开始日的最大日期
	        }
	    };
	    
	    
	    $("button[type='reset']").on('click',function(){
	    	start.min="1900-01-01 00:00:00";
	        start.max= '2099-06-16 23:59:59';	
        	end.min="1900-01-01 00:00:00";
	        end.max='2099-06-16 23:59:59';
	    })
	    if (document.getElementById('LAY_demorange_s')) {
	        document.getElementById('LAY_demorange_s').onclick = function () {
	            start.elem = this;
	            laydate(start);
	        };
	    }
	    if (document.getElementById('LAY_demorange_e')) {
	        document.getElementById('LAY_demorange_e').onclick = function () {
	            end.elem = this;
	            laydate(end);
	        };
	    }
	    
	    if (document.getElementById('LAY_demorange_ss')) {
	        document.getElementById('LAY_demorange_ss').onclick = function () {
	        	start.elem = this;
	            start.min='1900-01-01 00:00:00';
	            start.format = 'YYYY-MM-DD hh:mm:ss';
	            laydate(start);
	        };
	    }
	    if (document.getElementById('LAY_demorange_ee')) {
	        document.getElementById('LAY_demorange_ee').onclick = function () {
	        	end.elem = this;
	            end.min='1900-01-01 00:00:00';
	            end.format = 'YYYY-MM-DD hh:mm:ss';
	            laydate(end);
	        };
	    }
	
	    //正常的时间取值  范围1990 - 2099
	    if (document.getElementById('LAY_demorange_zs')) {
	        document.getElementById('LAY_demorange_zs').onclick = function () {
	            start.elem = this;
	            //start.min='1900-01-01 00:00:00';
	            //start.format = 'YYYY-MM-DD hh:mm:ss';
	            start.format = 'YYYY-MM-DD';
	            laydate(start);
	        };
	    }
	
	    if(document.getElementById('LAY_demorange_ze')){
	        document.getElementById('LAY_demorange_ze').onclick = function () {
	            end.elem = this;
	            //end.min=='1900-01-01 00:00:00';
	            //end.format = 'YYYY-MM-DD hh:mm:ss';
	            end.format = 'YYYY-MM-DD';
	            laydate(end);
	        }
	    }
	   
	});
}

function setLayDate(){
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	    $('.layDateClass').on('click',function(){
			    laydate({
			        elem : this,
			        max: '2099-06-16',
			        istoday: true,
			        format: 'YYYY-MM-DD'
			    });
			}
	    );
	})
	
}

function setLayDateOne(){
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	    var startone = {
		        min: "1900-01-01 00:00:00", 
		        max: '2099-06-16 23:59:59'
		        , istoday: true
		        ,istime: false
		        ,format: 'YYYY-MM-DD'
		        , choose: function (datas) {
		        	endone.min = datas; //开始日选好后，重置结束日的最小日期
		        	endone.start = datas //将结束日的初始值设定为开始日
		        }
		    };
		    
		    var endone = {
		        min: "1900-01-01 00:00:00",
		        max: '2099-06-16 23:59:59'
		        , istoday: true
		        ,istime: false
		        ,format: 'YYYY-MM-DD'
		        , choose: function (datas) {
		        	startone.max = datas; //结束日选好后，重置开始日的最大日期
		        }
		    };
		    
	    if (document.getElementById('LAY_demorange_ss_one')) {
	        document.getElementById('LAY_demorange_ss_one').onclick = function () {
	        	startone.elem = this;
	        	startone.format = 'YYYY-MM-DD';
	            laydate(startone);
	        };
	    }
	    if (document.getElementById('LAY_demorange_ee_one')) {
	        document.getElementById('LAY_demorange_ee_one').onclick = function () {
	        	endone.elem = this;
	        	endone.format = 'YYYY-MM-DD';
	            laydate(endone);
	        };
	    }
	})
	
}
function setLayDateTwo(){
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	    var startone = {
		        min: "1900-01-01 00:00:00", 
		        max: '2099-06-16 23:59:59'
		        , istoday: true
		        ,istime: false
		        ,format: 'YYYY-MM-DD'
		        , choose: function (datas) {
		        	endone.min = datas; //开始日选好后，重置结束日的最小日期
		        	endone.start = datas //将结束日的初始值设定为开始日
		        }
		    };
		    
		    var endone = {
		        min: "1900-01-01 00:00:00",
		        max: '2099-06-16 23:59:59'
		        , istoday: true
		        ,istime: false
		        ,format: 'YYYY-MM-DD'
		        , choose: function (datas) {
		        	startone.max = datas; //结束日选好后，重置开始日的最大日期
		        }
		    };
		    
	    if (document.getElementById('LAY_demorange_ss_two')) {
	        document.getElementById('LAY_demorange_ss_two').onclick = function () {
	        	startone.elem = this;
	        	startone.format = 'YYYY-MM-DD';
	            laydate(startone);
	        };
	    }
	    if (document.getElementById('LAY_demorange_ee_two')) {
	        document.getElementById('LAY_demorange_ee_two').onclick = function () {
	        	endone.elem = this;
	        	endone.format = 'YYYY-MM-DD';
	            laydate(endone);
	        };
	    }
	})
	
}
function setLayDateThree(){
	layui.use('laydate', function () {
	    var laydate = layui.laydate;
	    var startone = {
		        min: "1900-01-01 00:00:00", 
		        max: laydate.now()
		        , istoday: true
		        ,istime: false
		        ,format: 'YYYY-MM-DD'
		        , choose: function (datas) {
		        	endone.min = datas; //开始日选好后，重置结束日的最小日期
		        	endone.start = datas //将结束日的初始值设定为开始日
		        }
		    };
		    
		    var endone = {
		        min: "1900-01-01 00:00:00",
		        max: laydate.now()
		        , istoday: true
		        ,istime: false
		        ,format: 'YYYY-MM-DD'
		        , choose: function (datas) {
		        	startone.max = datas; //结束日选好后，重置开始日的最大日期
		        }
		    };
		    
	    if (document.getElementById('LAY_demorange_ss_3')) {
	        document.getElementById('LAY_demorange_ss_3').onclick = function () {
	        	startone.elem = this;
	        	startone.format = 'YYYY-MM-DD';
	            laydate(startone);
	        };
	    }
	    if (document.getElementById('LAY_demorange_ee_3')) {
	        document.getElementById('LAY_demorange_ee_3').onclick = function () {
	        	endone.elem = this;
	        	endone.format = 'YYYY-MM-DD';
	            laydate(endone);
	        };
	    }
	})
	
}
