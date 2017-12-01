$.verify.addRules({
  isPhone: function(r) {
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
	
	if (!mobile.test(r.val())) {
		return "请输入正确的手机号";
	}
    return true;
  }
});

$.verify.addRules({
  isPhoneAndMobile: function(r) {
	  var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
		var tel = /^0\d{2，3}-?(\d{7,8})|\d{7,8}$/;
		var tel400 = /^400(\d{5,11})$/;  
		
		if (!(tel.test(r.val()) || mobile.test(r.val()) || tel400.test(r.val()))) {
			return "请输入正确的电话";
		}
	    return true;
    return true;
  }
});

$.verify.addRules({
	  isPrice: function(r) {
		  var reg=/^[0-9]{1}\d*(\.\d{1,2})?$/;
		
		if (!reg.test(r.val())) {
			return "只能为两位小数内的数字";
		}
	    return true;
	  }
	});
$.verify.addRules({
	  orderNum: function(r) {
		  var reg=/^([1-9 ]|1[0-9]|20)$/;
		
		if (!reg.test(r.val())) {
			return "只能输入1-20整数字";
		}
	    return true;
	  }
	});

$.verify.addRules({
	discount: function(r) {
		if (r.val() <= 10) {
			return true;
		} else {
			return "请输入小于10的折扣值";
		}
	  }
	});

$.verify.addRules({
	max9999: function(r) {
		if (Number(r.val()) <10000) {
			return true;
		} else {
			return "请输入不超过9999的数";
		}
	  },
	  gt: function(r) {
		  var reg=/^[0-9]\d*(\.\d)?$/;
		  var id = r.args[0];
		  var val = r.val();
		  if (!reg.test(val)) {
			  return "请输入正确数字";
		  }
		  var labelId = $("#"+id).val();
		  if(labelId) {
			  if (Number(val) >= Number(labelId)) {
				  return true;
			  } else {
				  return "请输入大于等于"+ labelId +"的值";
			  }
		  }
	  },
	  lt: function(r) {
		  var reg=/^[0-9]\d*(\.\d)?$/;
		  var id = r.args[0];
		  var val = r.val();
		  if (!reg.test(val)) {
			  return "请输入正确数字";
		  }
		  var labelId = $("#"+id).val();
		  if(labelId) {
			  if (Number(val) <= Number(labelId)) {
				  return true;
			  } else {
				  return "请输入小于等于"+ labelId +"的值";
			  }
		  }
	  }
	});

$.verify.addGroupRules({
	againPass: function(r) {
		var start = r.field("start"),
			end = r.field("end");
		if(start.val() != end.val()) {
			return "两次密码输入不一致！";
		}

		return true;
	},
	num10: function(r) {
		var g = /^[1-9]*[1-9][0-9]*$/;
		if (g.test(r.val()) && r.val() < 9999999999) {
			return true;
		} else {
			return "请输入大于0小于9999999999的数字！";
		}
	}
});

$.verify.addRules({
	  isNumber: function(r) {
		var value = /^[1-9]*[1-9][0-9]*$/;
		if (!value.test(r.val())) {
			return "请输入大于0的正整数数字！";
		}
	    return true;
	  },
	  isNum:function(r){
		  var value = /^[1-9]\d*|0$/;
			if (!value.test(r.val())) {
				return "请输入正确的数字！";
			}
		    return true; 
	  }
	});
$.verify.addRules({
	  maxNumber: function(r) {
		  var maxNum = r.args[0];
			if (parseFloat(r.val()) < maxNum) {
				return true;
			} else {
				return "请输入小于"+maxNum+"的数字！";
			}
		  return true;
	  }
	});
$.verify.addRules({
	  isNumber0: function(r) {
		var value =/^[0-9]+$/;
		
		if (!value.test(r.val())) {
			return "请输入非负整数数！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	  isNumber9999: function(r) {
		var value = /^[0-9]*[1-9][0-9]*$/;
		
		if (!value.test(r.val())) {
			return "请输入大于0的正整数数字！";
		}
		if(r.val()>9999){
			return "请输入小于9999的正整数数字！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	  isNumber999: function(r) {
		var value = /^[0-9]*[1-9][0-9]*$/;
		
		if (!value.test(r.val())) {
			return "请输入大于0的正整数数字！";
		}
		if(r.val()>999){
			return "请输入小于999的正整数数字！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	  isNumber99999: function(r) {
		var value = /^[0-9]*[1-9][0-9]*$/;
		
		if (!value.test(r.val())) {
			return "请输入大于0的正整数数字！";
		}
		if(r.val()>99999){
			return "请输入小于99999的正整数数字！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	  isNumber999999: function(r) {
		var value = /^[0-9]*[1-9][0-9]*$/;
		
		if (!value.test(r.val())) {
			return "请输入大于0的正整数数字！";
		}
		if(r.val()>999999){
			return "请输入小于999999的正整数数字！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	lngLat: function(r) {
		var value = /^(((([0-9]|[1-9][1-9]|1[0-7][0-9])?|180)\.\d{0,6})|(\d|[1-9]\d|1[1-7]\d|0{1,3})|180\.0{0,6}|180),([0-8]?\d{1}\.\d{0,6}|90\.0{0,6}|[0-8]?\d{1}|90)$/;
		
		if (!value.test(r.val())) {
			return "请抓取正确的经纬度！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	onlyNumAndCharacter:function(r){
		var g =/^[a-zA-Z\d]+$/;
		if(!g.test(r.val())){
			return "只能输入数字和字母!";
		}
		return true;
	}
});
$.verify.addGroupRules({
	activityTime: function(r) {
		var startTime = r.field("startTime"),
		endTime = r.field("endTime");
		
	}
});
$.verify.addRules({
	  isUrl: function(r) {
		var url = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;   
		
		if (!url.test(r.val())) {
			return "请输入正确的连接地址";
		}
	    return true;
	  }
	});

$.verify.addRules({
	  notChiese: function(r) {
		var value = /^(\w)+|\.+$/;
		
		if (!value.test(r.val())) {
			return "请输入正确的登录名";
		}
	    return true;
	  }
	});
$.verify.addRules({
	maxLength20: function(r) {
		if (r.val().length > 20) {
			return "最多不得超过20个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength5: function(r) {
		if (r.val().length > 5) {
			return "最多不得超过5个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength17: function(r) {
		if (r.val().length > 17) {
			return "最多不得超过17个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength30: function(r) {
		if (r.val().length > 30) {
			return "最多不得超过30个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength40: function(r) {
		if (r.val().length > 40) {
			return "最多不得超过40个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength45: function(r) {
		if (r.val().length > 45) {
			return "最多不得超过45个字";
		}
		return true;
	}
});
$.verify.addRules({
	minLength4: function(r) {
		if (r.val().length < 4) {
			return "最少不得低于4个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength200: function(r) {
		if (r.val().length > 200) {
			return "最多不得超过200个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength5: function(r) {
		if (r.val().length > 5) {
			return "最多不得超过5个字";
		}
		return true;
	}
});
$.verify.addRules({
	minLength2: function(r) {
		if (r.val().length < 2) {
			return "最少不得低于2个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength50: function(r) {
		if (r.val().length > 50) {
			return "最多不得超过50个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength100: function(r) {
		if (r.val().length > 100) {
			return "最多不得超过100个字";
		}
		return true;
	}
});
$.verify.addRules({
	idCard: function(r) {
		if (r.val()=='') {
			return "请上传身份证照片";
		}
		return true;
	}
});

$.verify.addRules({
	maxLength1000: function(r) {
		if (r.val().length > 1000) {
			return "最多不得超过1000个字";
		}
		return true;
	}
});
$.verify.addRules({
	maxLength2000: function(r) {
		if (r.val().length > 2000) {
			return "最多不得超过2000个字";
		}
		return true;
	}
});


$.verify.addRules({
	lengthMixMax: function(r) {
		var obj=r.field.context.dataset;
		if (r.val().length > obj.max||r.val().length < obj.min) {
			return obj.min+"-"+obj.max+"字符";
		}
		return true;
	}
});

$.verify.addRules({
	lengthMax: function(r) {
		var obj=r.field.context.dataset;
		if (r.val().length > obj.max) {
			return "最多不超过"+obj.max+"字符";
		}
		return true;
	}
});


$.verify.addRules({
	minLength6: function(r) {
		if (r.val().length < 6) {
			return "密码至少6位";
		}
		return true;
	}
});
$.verify.addRules({
	isPerNum: function(r) {
		var g = /^\d+(\.\d+)?$/;
		if (g.test(r.val()) && r.val() < 100 && r.val() > 0) {
			return true;
		} else {
			return "请输入大于0小于100的数字！";
		}
	}
});

$.verify.addGroupRules({
	rolesRequired: function(r) {
		var maxRoleId=$("[data-maxroleid]").attr("data-maxroleid");
		if($("[data-maxroleid] :checked").length==0){
			return "请至少选择一个角色";
		}
	
		return true;
	},
	eTypeRequired: function(r) {
		var eTypeId=$("[data-eTypeid]").attr("data-eTypeid");
		if($("[data-eTypeid] :checked").length==0){
			var msg={}
			msg["a"+eTypeId]="请选择网点类型";
			return msg;
		}
	
		return true;
	}
});



