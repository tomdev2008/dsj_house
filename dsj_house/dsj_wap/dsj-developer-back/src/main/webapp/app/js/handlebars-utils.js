
(function () {
	//用于显示最大页码
	var span = 3;
	
	//循环页数
	Handlebars.registerHelper('page-each', function(pageNo, totalCount, pageSize, options) {
		  var ret = "", formula1 = Math.floor((totalCount + pageSize - 1) / pageSize); //(count)
		  	  formula2 = pageNo > span ? (pageNo - span) : 1; //(begin)
		  	  formula3 = pageNo < (formula1 - span) ? (pageNo + span) : formula1;//(end)
		  	
		  for (var i = formula2, j = formula3; i <= j; i++) {
			  var k = '&nbsp;&nbsp;';
			  if (i == j) {
				  k = '';
			  }
		   	  ret = ret + (i === pageNo ? '<span class="page_select" href="javascript:void(0);">' + i + '</span>&nbsp;&nbsp;' : '<span pageno="' + i + '" href="javascript:void(0);">' + i + '</span>' + k) + options.fn(this);
		  }
		
		  return ret;
	});
	
	//if 判断扩展
	Handlebars.registerHelper('page-if', function(param1, param2, param3, choose, options) {
		//参数最后一位为 选择项
		var len = arguments.length - 2, choose, count, result;
		//动态匹配
		if (len < 3) {
			options = len === 1 ? arguments[len+1] : choose;
			choose = arguments[len];
		}
		//选择公式
		switch(choose) {
			case 1: 
				result = param1 > 1;
				break;
			case 2: 
				result = param1 > param2;
				break;
			case 4: 
				result = param1 - span > 1;
				break;
			case 7: 
				count = Math.floor((param2 + param3 - 1) / param3);
				result = param1 + span < count;
				break;
			case 8: 
				count = Math.floor((param2 + param3 - 1) / param3);
				result = param1 < count;
				break;
		}
		/* 	console.log
		for(var i = 0, len = arguments.length; i < len; i++) {
		//	console.log(result, "arguments[", i ,"]", arguments[i]);
		}*/
		if (result) {
			return options.fn(this);
		}
	});
	//单个取值
	Handlebars.registerHelper('page-formula', function (param1, param2, param3, choose, options) {
		//参数最后一位为 选择项
		var len = arguments.length - 2, choose, result;
		//动态匹配 len < 4 && (choose = arguments[len]);
		if (len < 3) {
			options = len === 1 ? arguments[len+1] : choose;
			choose = arguments[len];
		}
		switch(choose) {
			case 3: 
				result = Math.floor((param1 + param2 - 1) / param2);
				break;
			case 5: 
				result = param1 > param2 ? (param1 - param2) : 1;
				break;
			case 6: 
				result = param1 < (param2 - param3) ? (param1 + 1) : param2;
				break;
		}
		 
		return result;
	});
	
	
	
	/**
	 * shiro register 方法
	 */
	Handlebars.registerHelper('shiro', function (key, options) {
		//验证为空返回
		if (!key) {
			return;
		}
		var $this = this;
		if (_isPermissions(key)) {
			return options.fn(this);
		};
 	});
	/**
	 * 是否有权限
	 * @method _isPermissions
	 * @params key 比较KEY
	 */
	function _isPermissions(key) {
		var result = false;
		$.each(Etrip.authzation.stringPermissions || [], function (index, value) {
			if (key === value) {
				result = true;
				return false;
			}
		})
		return result;
	}
	
	//做加法运算 (只加1)
	Handlebars.registerHelper("add", function (a, num) {
		 a = Handlebars.Utils.isEmpty(a) ? 0 : a;
		 num = Handlebars.Utils.isEmpty(num) ? 0 : num;
		 
		 return (+a) + (+num);			
	});
	
	//做减法运算 (只减1)
	Handlebars.registerHelper("sub", function (a, num) {
		if(!Handlebars.Utils.isEmpty(a) && !Handlebars.Utils.isEmpty(num)){
			return a - num;			
		}
		return "";
	});
	//做乘法
	Handlebars.registerHelper("multi", function (a, num) {
		if(!Handlebars.Utils.isEmpty(a) && !Handlebars.Utils.isEmpty(num)){
			return a * num;
		}
		return "";
	});
	
	//做除法
	Handlebars.registerHelper("division", function (a, num) {
		if(!Handlebars.Utils.isEmpty(a) && !Handlebars.Utils.isEmpty(num)){
			return a / num;			
		}
		return "";
	});
	
	//判断
	Handlebars.registerHelper("eif", function (key1, key2, options) {
		var result = false,
			str = "key1opkey2",
			p = /\$(\d{1})/;
		$.each(options.hash, function (key, value) {
			str = str.replace(key, value);
		});
		
		while (p.test(str)) {
			var sp = p.exec(str);
			str = str.replace("key" + sp[1], "").replace(sp[0], "key" + sp[1]);
		}
		
		str = str.replace("key1", key1).replace("key2", key2);
		try {
			result = eval(str);
		} catch(e) {
		};
		if (result) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});
	
	//截取
	Handlebars.registerHelper("split", function (regex, regex1, data, options) {
		return data.replace(new RegExp(regex,"g"), regex1);
	});
	
	/**
	 * 三元表达式 
	 * * 非空比较 *
	 */
	Handlebars.registerHelper("expr", function (key, key2, options) {
		var str = "keyopkey2 ? 'true' : 'false'";
		$.each(options.hash, function (key, value) {
			str = str.replace(key, value);
		});
		if (typeof key === "string") {
			key = "'" +key+ "'";
		}
		if (typeof key2 === "string") {
			key2 = "'" +key2+ "'";
		}
		str = str.replace("key", key).replace("key2", key2);
		
		try{
			return eval('(' + str + ')');			
		}catch(err){
			return false;
		}
	});
	
	/**
	 * 取出枚举里的数据 + 国际化
	 */
	Handlebars.registerHelper('enum', function (obj, value, options) {
		var result = value;

		$.each(obj || [], function ($key, $value) {
			if ($.trim($value.value) == $.trim(value)) {
				result = $value.key;
				return true;
			}
		});
		
		return result;
	});
	
	/**
	 * 取出枚举里的数据 + 国际化
	 */
	Handlebars.registerHelper('enumCity', function (obj, value, options) {
		var result = value;
		
		$.each(obj || [], function ($key, $value) {
			if ($.trim($value.cityId) == $.trim(value)) {
				result = $value.city;
				return true;
			}
		});
		
		return result;
	});
	
	/**
	 * 取出枚举里的数据 + 国际化
	 * 省份回显
	 */
	Handlebars.registerHelper('enumProvince', function (obj, value, options) {
		var result = value;
		
		$.each(obj || [], function ($key, $value) {
			if ($.trim($value.provinceId) == $.trim(value)) {
				result = $value.province;
				return true;
			}
		});
		
		return result;
	});
	
	/**
	 * 取出枚举里的数据 + 国际化
	 * 地区回显
	 */
	Handlebars.registerHelper('enumArea', function (obj, value, options) {
		var result = value;
		
		$.each(obj || [], function ($key, $value) {
			if ($.trim($value.areaId) == $.trim(value)) {
				result = $value.area;
				return true;
			}
		});
		
		return result;
	});
	
	/**
	 * 取出枚举里的数据 + 国际化 
	 * 例子: CNN/ADT
	 */
	Handlebars.registerHelper('enumBack', function (obj, value, options) {
		var result = value;
		$.each(obj || [], function ($key, $value) {
			if (!$value.value || !value) {
				return false;
			}
			if (value.indexOf($value.value) != -1) {
				result = result.replace(new RegExp($value.value), $value.key);
				return true;
			}
		});
		return result && i18n.t(result);
	});
	
	/**
	 * 取出对象的key - value
	 */
	Handlebars.registerHelper("k_v", function(obj, flag, options) {
	    var buffer = "",
	        key, data, i = 0;
	    
	    if (options.data) {
		    data = Handlebars.createFrame(options.data);
	    }
	    
	    for (key in obj) {
	        if (obj.hasOwnProperty(key)) {
	        	if (data) { data.index = i++; }
	        	/*//如果对象
	        	if (toString.call(obj[key]) === "[object Array]") {
	        		obj[key] = obj[key][0];
	    	    }*/
	            buffer += options.fn({key: key, value: obj[key]}, { data: data });
	            if ($.trim(buffer) && options.hash["break"]){
	    	    	break;
	    	    }
	        }
	    }
	    
	    return buffer;
	});
	
	
	/**
	 * 设置index值
	 */
	Handlebars.registerHelper('setIndex', function(value){
	    this.index = Number(value); //I needed human readable index, not zero based
	});
	
	/**
	 * 格式化日期
	 * **必须确保导入 datePicker 
	 */
	Handlebars.registerHelper('formatDate', function(value, format){
		try{
			value = $.datepicker.formatDate(format, parseISO8601(value));
		} catch(e) {} 
		
		return value;
	});
	
	/**
	 * 格式化日期
	 * **必须确保导入 datePicker  formatDate(new Date(timestamp), '%H:%m:%s');
	 */
	Handlebars.registerHelper('timestamp', function(timestamp, fmt){
		var value = new Date(timestamp);
	    function pad(value) {
	        return (value.toString().length < 2) ? '0' + value : value;
	    }
	    return fmt.replace(/%([a-zA-Z])/g, function (_, fmtCode) {
	        switch (fmtCode) {
	        case 'Y':
	            return value.getFullYear();
	        case 'M':
	            return pad(value.getMonth() + 1);
	        case 'd':
	            return pad(value.getDate());
	        case 'H':
	            return pad(value.getHours());
	        case 'm':
	            return pad(value.getMinutes());
	        case 's':
	            return pad(value.getSeconds());
	        default:
	            
	        }
	    });
		return timestamp;
	});
	
	/**
	 * YYYY-MM-DD
	 */
	function parseISO8601(dateStringInRange) {  
		   var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/,  
		       date = new Date(NaN), month,  
		       parts = isoExp.exec(dateStringInRange);  
		  
		   if(parts) {  
		     month = +parts[2];  
		     date.setFullYear(parts[1], month - 1, parts[3]);  
		     if(month != date.getMonth() + 1) {  
		       date.setTime(NaN);  
		     }  
		   }  
		   return date;  
	 }  
	
	/**
	 * 字符过长替换...
	 */
	Handlebars.registerHelper('repoint', function(value, num, options){
		if (!value) {
			return value;
		}
		if (value.length == num) {
			return value;
		}
		
		value = value.replace(/\r\n|\r|\n/g, "");
		return value.replace(RegExp("^(.{" + num + "})(.)*", "g"), function(all,text,char){
	          return text + "...";
		});
	});
	
	/**
	 * 格式化日期
	 * **必须确保导入 datePicker 
	 * 自动截取 小数点
	 */
	Handlebars.registerHelper('RMBFormat', function(value){
        if(/[^0-9\.]/.test(value)) {
        	return value;
        } 
        
        //数字转换String
        if (Object.prototype.toString.call(value) === "[object Number]") {
        	value = value + "";
        }
        
        var value = value.replace(/\.\d+/g, '');
        
        value=value.replace(/^(\d*)$/,"$1.");
        value=(value).replace(/(\d*\.\d\d)\d*/,"$1");
        value=value.replace(".",",");
        var re=/(\d)(\d{3},)/;
        while(re.test(value)) {
        	value=value.replace(re,"$1,$2");
        }
        value=value.replace(/,(\d\d)$/,".$1");
        return value.replace(/^\./,"0.").replace(/\,$/g, '');
	});
	
	/**
	 * 数组集合取值
	 */
	Handlebars.registerHelper("get", function(obj, key) {
		if (!obj) return;
		return obj[key] || key;
	});
	
	/**
	 * 取评论数
	 */
	Handlebars.registerHelper("getReviewVal", function(obj, id, cdate, key) {
		if (null == obj) {
			return 0;
		}
		
		var name = id + "_" + cdate;
		
		return obj[name][key];
	});
	
	/**
	 * 回复定制 回复显示全部
	 */
	Handlebars.registerHelper("replayCounts", function(obj, id, cdate, key, options) {
		var count = 0;
		if (null != obj) {
			var name = id + "_" + cdate;
			count = obj[name][key];
		}
		if (count) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});
	
	/**
	 * 评论赞是否赞过
	 */
	Handlebars.registerHelper("isPraise", function(apu, retype, rt, obj, options) {
		var uuid = obj.cacheSign,
			oid = obj.objectId,
			userId = $.EU.user.id,
			result = "";
		var flag = true;
		//SPE_2_PRAISE_ff36cc8ff17a47a1bdc1d62aef22aed5_
		$.each(retype || [], function ($key, $value) {
			if ($.trim($value.key) == $.trim(rt)) {
				result = $value.value;
				return true;
			}
		});
		if (result) {
			var tmp = result + "_" + oid + "_" + "PRAISE_" + uuid + "_";
			if (apu[tmp]) {
				for (var a in apu[tmp].split(",")) {
					if (apu[tmp].split(",")[a] == userId) {
						flag = false;
						break;
					}
				}
			}
		} 
		
		if (flag) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});
	
	/**
	 * 数组集合取值
	 */
	Handlebars.registerHelper("curDateCompare", function(date, options) {
		var compare = false,
			result = "";
		try {
			compare = $.datepicker.parseDate("yy-mm-dd", date).getTime() == new Date($.datepicker.formatDate("yy/mm/dd", new Date())+" 00:00:00").getTime();
		} catch (e){
			$.EU.log(e);
		}
		
		if (compare) {
			result = options.fn(result);
		}
		return result;
	});
	
	/**
	 * 数组集合取值
	 */
	Handlebars.registerHelper("set", function(obj, key) {
		this[obj] = key; 
	});
	/**
	 * 取对象长度
	 */
	Handlebars.registerHelper("len", function(n, num, options) {
		var result = "";
		
		if (n > num) {
			result = options.fn(result);
		}
		
		return result;
	});
	/**
	 * 是否已赞
	 */
	Handlebars.registerHelper("isZan", function(zan, id, options) {
		var boolean = false;
		
		$.each((zan && zan.objIds && zan.objIds.split(",")) || [], function (index, key) {
			if (key == id) {
				boolean = true;
				return false;
			}
		});
		
		if (boolean) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});
	/**
	 * 取对象长度
	 */
	Handlebars.registerHelper("getLastNum", function(obj, num, options) {
		var result = "";
		if (obj.length-1 == num) {
			result = options.fn(result);
		}
		return result;
	});
	
	/**
	 * 取对象长度
	 */
	Handlebars.registerHelper("isEmpty", function(obj, options) {
		var result = "";
		
		if ($.isEmptyObject(obj)) {
			result = options.fn(result);
		}
		
		return result;
	});
	/**
	 * 03-10 转换 03月10日
	 */
	Handlebars.registerHelper("dateConvert", function(date, options) {
		
		if (!date) {
			return date;
		}
		
		return date.replace(/(\d{2})-(\d{2})/g, "$1月$2日");;
	});
	
	/**
	 * 03-10 转换 03月10日
	 */
	Handlebars.registerHelper("reponitFN", function(num, flag, options) {
		
	/*	if (!value) {
			return value;
		}
		if (value.length == num) {
			return value;
		}*/
		
		var value = options.fn(this);
		
		value = value.replace(/\r\n|\r|\n/g, "");
		
		value = value.replace(RegExp("^(.{" + num + "})(.)*", "g"), function(all,text,char){
	          return text + "...";
		});
		
		return value;
		
		//return date.replace(/(\d{2})-(\d{2})/g, "$1月$2日");;
	});
	/**
	 * 03-10 转换 03月10日
	 */
	Handlebars.registerHelper("toUpperFN", function(options) {
		var value = options.fn(this);
		return value.toUpperCase();
	});
	
	/**
	 * 循环制定次数
	 */
	Handlebars.registerHelper('specific-each', function(context, num, options) {
		  var fn = options.fn, inverse = options.inverse;
		  var i = 0, ret = "", data;
		  var len = num || context.length;
		 
		  if (options.data) {
		    data = Handlebars.createFrame(options.data);
		  }
		  if(context && typeof context === 'object') {
		    if(context instanceof Array){
	    	 if (num > context.length) {
	    		 len = context.length;
	    	 }
		      for(var j = len; i<j; i++) {
		        if (data) { data.index = i; }
		        ret = ret + fn(context[i], { data: data });
		      }
		    } else {
		      for(var key in context) {
		        if(context.hasOwnProperty(key)) {
		          if (i == num) {
		        	  break;
		          }
		          if(data) { data.key = key; data.index = i; }
		          ret = ret + fn(context[key], {data: data});
		          i++;
		        }
		      }
		    }
		  }

		  if(i === 0){
		    ret = inverse(this);
		  }

		  return ret;
		});
	
	/**
	 * 轮播图 HTML构建
	 */
	Handlebars.registerHelper("sliderHTML", function(arr, idPrex, num, options) {
		var str = '<a id="' + idPrex + '_{{index}}" f-title="{{obj.title}}" f-content="{{obj.content}}" href="javascript:void(0);"><img src="{{obj.thum}}"/></a>';
		var result = [], index = 0;
		if (!arr) return "";
		for (var i = 0; i < arr.length / num; i++) {
            var aArr = [],
            	newsArr = arr.slice(i * num, num * (i+1)),
        		template = Handlebars.compile(str);
            aArr.push("<li>");
            for (var j = 0; j < newsArr.length; j ++) {
                aArr.push(template({"index": index, "obj": newsArr[j]}));
                index++;
            }
			aArr.push("</li>");
            result.push(aArr.join(''));
		}
		return new Handlebars.SafeString(result.join(''));
	});

	/**
	 * 编译HTML
	 */
	Handlebars.registerHelper("evalHtml", function(value) {
		return value && new Handlebars.SafeString(value.replace(/\r\n|\n|\r/g, "</br>"));
	});
	
	/**
	 * 编译HTML
	 */
	Handlebars.registerHelper("convertHtml", function(value) {
		if(value) {
			return new Handlebars.SafeString(value);
		}else{
			return value;
		}
	});
	
	/**
	 * 小写转大写 "".toUpperCase
	 */
	Handlebars.registerHelper("toUpperCase", function(value) {
		return value && value.toUpperCase();
	});
	
	/**
	 * 求时间差
	 */
	Handlebars.registerHelper("delta-T", function(stime, option) {
		var diff = new Date().getTime() - stime;
		var def = "刚刚";
		if (!stime) return def;
		var days = Math.floor(diff / (24 * 3600 * 1000));
		if(days > 0) {
			return days + "天前";
		}
		var diffH = diff % (24 * 3600 * 1000);
		var hours = Math.floor(diffH / (3600 * 1000));
		if(hours > 0) {
			return hours + "小时前";
		}
		var diffM = diffH % (3600 * 1000);
		var minutes = Math.floor(diffM / (60 * 1000));
		if(minutes > 0) {
			return minutes + "分钟前";
		} else {
			return def;
		}
	});
	
	Handlebars.registerHelper('expression', function() {
	    var exps = [];
	 	try{
		 	//最后一个参数作为展示内容，也就是平时的options。不作为逻辑表达式部分
		 	var arg_len = arguments.length;
			var len = arg_len-1;
			for(var j = 0;j<len;j++){
				if(arguments[j]!=null){
					exps.push(arguments[j]);
				}else{
					exps.push("0");
				}
			}
			console.info(exps.join(' '));
			var result = eval(exps.join(' '));
			if (result) {
			  return arguments[len].fn(this);
			} else {
			  return arguments[len].inverse(this);
			}
	 	}catch(e){
	 		throw new Error('Handlerbars Helper "expression" can not deal with wrong expression:'+exps.join(' ')+".");
	 	}
	 });
	
	Handlebars.registerHelper('compare', function(left, operator, right, options) {
        if (arguments.length < 3) {
          throw new Error('Handlerbars Helper "compare" needs 2 parameters');
        }
        var operators = {
          '==':     function(l, r) {return l == r; },
          '===':    function(l, r) {return l === r; },
          '!=':     function(l, r) {return l != r; },
          '!==':    function(l, r) {return l !== r; },
          '<':      function(l, r) {return l < r; },
          '>':      function(l, r) {return l > r; },
          '<=':     function(l, r) {return l <= r; },
          '>=':     function(l, r) {return l >= r; },
          'typeof': function(l, r) {return typeof l == r; }
        };

        if (!operators[operator]) {
          throw new Error('Handlerbars Helper "compare" doesn\'t know the operator ' + operator);
        }

        var result = operators[operator](left, right);

        if (result) {
          return options.fn(this);
        } else {
          return options.inverse(this);
        }
    });
	
	Handlebars.registerHelper('getScriptEnd', function(options) {
        return "</script>";
    });
	
	//返回替换分页内容 data.replace(/{{page_template}}/g, p)
})();
