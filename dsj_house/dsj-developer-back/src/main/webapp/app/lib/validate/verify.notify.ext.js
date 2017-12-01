$.verify.addRules({
  isPhone: function(r) {
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
	var tel = /^\d{3,4}-?(\d{7,9})|\d{7,9}$/;  
	
	if (!(tel.test(r.val()) || mobile.test(r.val()))) {
		return "请输入正确的手机号";
	}
    return true;
  }
});

$.verify.addRules({
  isPhoneAndMobile: function(r) {
	var mobile = /^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;   
	
	if (!mobile.test(r.val())) {
		return "请输入正确的电话";
	}
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
	discount: function(r) {
		if (r.val() <= 10) {
			return true;
		} else {
			return "请输入小于10的折扣值";
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
		var value = /^[0-9]*[1-9][0-9]*$/;
		
		if (!value.test(r.val())) {
			return "请输入大于0的正整数数字！";
		}
	    return true;
	  }
	});
$.verify.addRules({
	lngLat: function(r) {
		var value = /^(((\d|[1-9]\d|1[1-7]\d|0)\.\d{0,6})|(\d|[1-9]\d|1[1-7]\d|0{1,3})|180\.0{0,6}|180),([0-8]?\d{1}\.\d{0,6}|90\.0{0,6}|[0-8]?\d{1}|90)$/;
		
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

$.verify.addRules({
	passwordChar:function(r){
		var a =/^[a-zA-Z\d]+$/;
		var g = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
		var v=r.val();
		if(!g.test(v)||!a.test(v)||v.length<6||v.length>18){
			return "密码由6-18位的数字和字母组成";
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
	maxLength200: function(r) {
		if (r.val().length > 200) {
			return "最多不得超过200个字";
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



