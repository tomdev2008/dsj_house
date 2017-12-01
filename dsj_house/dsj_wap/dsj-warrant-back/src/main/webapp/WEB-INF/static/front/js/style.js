// 登陆页面X清空事件
function qingkong() {
  $('#phone').val('')
}




// 点击输入框清除样式
function phone2(){
	
// if (phone1.length>0) {
	var phone1 = document.getElementById('phone');
      // var phone = jQuery("#phone").val();
      var error = document.getElementById('error');
      var errorDiv = document.getElementById('errorDiv');
         var tx = document.getElementById('tx');
         var basic = document.getElementById('basic-addon1');
      
	errorDiv.style.visibility='hidden';
	 tx.src=_ctx+'/static/front/images/login/tuyiyi-com-0.png';
        basic.style.background='#eee';
        basic.style.border='#ccc';
        $('#basic-addon1').css('border','1px solid #ccc')
           // basic.style.borderWidth = "1px"
        // basic.style.borderStyle = "solid"
        basic.style.borderRight = "none"
        // basic.style.borderColor = "#ccc"
        $('#phone').css('border','1px solid #ccc')
          $('#phone').css('color','')
        // phone1.style.borderWidth = "1px"
        // phone1.style.borderStyle = "solid"
        phone1.style.borderRight = "none"
        // phone1.style.borderColor = "#ccc"
        $('#cha').css('border','1px solid #ccc')
        // cha.style.borderWidth='1px'
        // cha.style.borderStyle='solid'
        // cha.style.borderColor='#ccc'
        cha.style.borderLeft='none';

        // errorDiv.style.border = "#ccc"
// }

}

//验证手机号
    function vailPhone(){
    	var phone1 = document.getElementById('phone');
      var phone = jQuery("#phone").val();
      var mima = jQuery("#mima").val();
      var error = document.getElementById('error');
      var errorDiv = document.getElementById('errorDiv');
         var tx = document.getElementById('tx');
         var basic = document.getElementById('basic-addon1');
         var basicmima = document.getElementById('basic-addon2');
         var mima1 = document.getElementById('mima');
         var mm = document.getElementById('mm');
      
      var flag = false;
      var message = "";
      var myreg = /^[0-9A-Za-z_]{5,20}$/;       
      if(phone == ''){
        message = "用户名不能为空！";
        errorDiv.style.visibility='visible';
        tx.src=_ctx+'static/front/images/login/tuyiyi-com-1.png'
        basic.style.background='#f8ccc7';
        basic.style.border='#f8ccc7';
        $('#phone').css('border','1px solid #f8ccc7')
        // phone1.style.borderWidth = "1px"
        // phone1.style.borderStyle = "solid"
        phone1.style.borderRight = "none"
        // phone1.style.borderColor = "#f8ccc7"
        $('cha').css('border','1px solid #f8ccc7')
        // cha.style.borderWidth='1px'
        // cha.style.borderStyle='solid'
        // cha.style.borderColor='#f8ccc7'
        cha.style.borderLeft='none'
        error.innerHTML=message;
        return ;
      }else if(phone.length <5&&phone.length >20){
        message = "请输入格式有误！";
        errorDiv.style.visibility='visible';
        tx.src=_ctx+'/static/front/images/login/tuyiyi-com-1.png'
        basic.style.background='#f8ccc7';
        basic.style.border='#f8ccc7';
        $('#phone').css('border','1px solid #f8ccc7')

        // phone1.style.borderWidth = "1px"
        // phone1.style.borderStyle = "solid"
        phone1.style.borderRight = "none"
        // phone1.style.borderColor = "#f8ccc7"
        phone1.style.color='#cc0000'
        $('cha').css('border','1px solid #f8ccc7')
        // cha.style.borderWidth='1px'
        // cha.style.borderStyle='solid'
        // cha.style.borderColor='#f8ccc7'
        cha.style.borderLeft='none'
        error.innerHTML=message;
        return ;
      }else if(!myreg.test(phone)){
        message = "用户名只能是数字和字母！";
        return ;
      }
      // else if(checkPhoneIsExist()){
      //   message = "该手机号码已经被绑定！";
      // }
      // else{
          // flag = true;
      // }


      // if(!flag){
     //提示错误效果
        //jQuery("#phoneDiv").removeClass().addClass("ui-form-item has-error");
        //jQuery("#phoneP").html("");
        //jQuery("#phoneP").html("<i class=\"icon-error ui-margin-right10\"> <\/i>"+message);
        //jQuery("#phone").focus();
      // }else{
           //提示正确效果
        //jQuery("#phoneDiv").removeClass().addClass("ui-form-item has-success");
        //jQuery("#phoneP").html("");
        //jQuery("#phoneP").html("<i class=\"icon-success ui-margin-right10\"> <\/i>该手机号码可用");
      // }

if (mima=='') {

	message = "密码不能为空！";
	errorDiv.style.visibility='visible';
 		mm.src=_ctx+'/static/front/images/login/8.png'
        basicmima.style.background='#f8ccc7';
        basicmima.style.border='#f8ccc7';
        $('#mima').css('border','1px solid #f8ccc7')
        // mima1.style.borderWidth = "1px"
        // mima1.style.borderStyle = "solid"
        // mima1.style.borderRight = "none"
        // mima1.style.borderColor = "#f8ccc7"
        mima1.style.color='#cc0000'
        mima1.value=message
        error.innerHTML=message;
        return ;
}
if (mima.length<6||mima.length>20){
		message = "请输入正确的密码！";
	errorDiv.style.visibility='visible';
 		mm.src=_ctx+'/static/front/images/login/8.png'
        basicmima.style.background='#f8ccc7';
        basicmima.style.border='#f8ccc7';
        $('#mima').css('border','1px solid #f8ccc7')
        // mima1.style.borderWidth = "1px"
        // mima1.style.borderStyle = "solid"
        // mima1.style.borderRight = "none"
        // mima1.style.borderColor = "#f8ccc7"
        mima1.style.color='#cc0000'
        mima1.value=message
        error.innerHTML=message;
        return ;
}
      
    }
 
 // 点击密码框清除样式
function mma(){
	 var errorDiv = document.getElementById('errorDiv');
	     var basicmima = document.getElementById('basic-addon2');
         var mima1 = document.getElementById('mima');
         var mm = document.getElementById('mm');
         mm.src=_ctx+'/static/front/images/login/7.png';
	errorDiv.style.visibility='hidden';
	  basicmima.style.background='#eee';
        basicmima.style.border='#ccc';
        $('#basic-addon2').css('border','1px solid #ccc')
        // basicmima.style.borderWidth = "1px"
        // basicmima.style.borderStyle = "solid"
        basicmima.style.borderRight = "none"
        // mima1.style.borderRight = "none"
        // mima1.style.borderColor = "#ccc"
        // mima1.style.color='#ccc'
        mima1.value=''
}

// 忘记密码跳转
function wangji(){
	 window.location=_ctx+'/loginuser/to_find_password';
}

// 获取验证码下一步
function xiayibu() {
	if(vphone()){
	  $.ajax({
          type:"post",
          url:_ctx+"/loginuser/find_passsword_verify",
          dataType:"json",
          data:{"phone":$("#phone").val(),"code":$("#yanzma").val()},
          success:function(data){
              if(data.status == 200){
            	  window.location=_ctx+'/loginuser/to_update_password';
              }else{
                  $("#error").html(data.message);
                  var errorDiv = document.getElementById('errorDiv');
                  errorDiv.style.visibility='visible';
              }
          }
      });
	}
}
// 验证码倒计时
var wait=120;
        function time(o){
            if (wait==0) {
                o.removeAttribute("disabled");    
                o.innerHTML="重新获取";
                o.style.backgroundColor="#c9c9c9";
                wait=60;
            }else{
                o.setAttribute("disabled", true);
                o.innerHTML=wait+"秒后重新获取";
                o.style.backgroundColor="#ececec";
                wait--;
                setTimeout(function(){
                    time(o)
                },1000)
            }
        }

// 验证码样式
function yanzm(){
	var yanzma = document.getElementById('yanzma')
	var basicyanzma = document.getElementById('basic-addon3')
	var basicyanzma = document.getElementById('ma')
	if (yanzma.value=='') {
	message = "请输入验证码！";
	errorDiv.style.visibility='visible';
 		ma.src=_ctx+'/static/front/images/login/8.png'
        basicyanzma.style.background='#f8ccc7';
        basicyanzma.style.border='#f8ccc7';
        $('#yanzma').css('border','1px solid #f8ccc7')
        // yanzma.style.borderWidth = "1px"
        // yanzma.style.borderStyle = "solid"
        // mima1.style.borderRight = "none"
        // yanzma.style.borderColor = "#f8ccc7"
        yanzma.style.color='#cc0000'
        yanzma.value=message
        error.innerHTML=message;
        return ;
}
}

// 找回密码样式
function queding() {
 var xinmima= $('#xinmima').val()
 var xinmima1= document.getElementById('xinmima')
 var querenmima= document.getElementById('querenmima')
 var errorDiv = document.getElementById('errorDiv');
 var error = document.getElementById('error');
 var xin= document.getElementById('xin')
 // alert(xinmima.length)
if (xinmima==''||xinmima.length<6||xinmima.length>20) {
  errorDiv.style.visibility='visible';
    xinmima1.value="请输入正确的密码！"
    error.innerHTML = "请输入正确的密码！"
    xinmima1.style.color='#cc0000'
    xin.style.color = '#cc0000'
    $('#xinmima').css('border','1px solid #cc0000')
    return;
}
if (querenmima.value =='') {
  errorDiv.style.visibility='visible';
    querenmima.value="请再次确认密码！"
    error.innerHTML = "请再次确认密码！"
    querenmima.style.color='#cc0000'
    $('#que').css('color','#cc0000')
    // que.style.color = '#cc0000'
    $('#querenmima').css('border','1px solid #cc0000')
    return;
}
if (querenmima.value !=xinmima) {
    errorDiv.style.visibility='visible';
    querenmima.value="密码不一致！"
    error.innerHTML = "密码不一致！"
     querenmima.style.color='#cc0000'
    $('#que').css('color','#cc0000')
    $('#querenmima').css('border','1px solid #cc0000')
    return;
}

$.ajax({
    type:"post",
    url:_ctx+"/loginuser/update_password",
    dataType:"json",
    data:{"password1":$("#xinmima").val(),"password2":$("#querenmima").val()},
    success:function(data){
        if(data.status == 200){
        	alert("修改密码成功");
        	 window.location=_ctx+'/login/to_login';
        }else{
        	  $("#error").html(data.message);
              var errorDiv = document.getElementById('errorDiv');
              errorDiv.style.visibility='visible';
        }
    }
});
}




// 点击新密码输入框清除样式
function xin() {
  var xinmima1= document.getElementById('xinmima')
 var xin= document.getElementById('xin')
  var errorDiv = document.getElementById('errorDiv');
 var error = document.getElementById('error');
 errorDiv.style.visibility='hidden';
    xinmima1.style.color='#999999'
    xinmima1.value=''
    xin.style.color = '#999999'
     $('#xinmima').css('border','1px solid #999999')
}
// 点击再次确认输入框清除样式
function que() {
  errorDiv.style.visibility='hidden';
    querenmima.value=""
     querenmima.style.color='#999999'
    $('#que').css('color','#999999')
    $('#querenmima').css('border','1px solid #999999')
}

// 取消按钮
function quxiao() {
	history.go(-1);
}


function vphone(){
	var phone1 = document.getElementById('phone');
	 var phone = jQuery("#phone").val();
	  var basic = document.getElementById('basic-addon1');
      var basicmima = document.getElementById('basic-addon2');
      var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;     
	if(phone == ''){
        message = "手机号码不能为空！";
        errorDiv.style.visibility='visible';
        tx.src=_ctx+'static/front/images/login/tuyiyi-com-1.png'
        basic.style.background='#f8ccc7';
        basic.style.border='#f8ccc7';
        $('#phone').css('border','1px solid #f8ccc7')
        // phone1.style.borderWidth = "1px"
        // phone1.style.borderStyle = "solid"
        phone1.style.borderRight = "none"
        // phone1.style.borderColor = "#f8ccc7"
        $('cha').css('border','1px solid #f8ccc7')
        // cha.style.borderWidth='1px'
        // cha.style.borderStyle='solid'
        // cha.style.borderColor='#f8ccc7'
        cha.style.borderLeft='none'
       $("#error").html(message);
        return false;
      }else if(phone.length !=11){
        message = "请输入有效的手机号码！";
        errorDiv.style.visibility='visible';
        tx.src=_ctx+'/static/front/images/login/tuyiyi-com-1.png'
        basic.style.background='#f8ccc7';
        basic.style.border='#f8ccc7';
        $('#phone').css('border','1px solid #f8ccc7')

        // phone1.style.borderWidth = "1px"
        // phone1.style.borderStyle = "solid"
        phone1.style.borderRight = "none"
        // phone1.style.borderColor = "#f8ccc7"
        phone1.style.color='#cc0000'
        $('cha').css('border','1px solid #f8ccc7')
        // cha.style.borderWidth='1px'
        // cha.style.borderStyle='solid'
        // cha.style.borderColor='#f8ccc7'
        cha.style.borderLeft='none'
        error.innerHTML=message;
        $("#error").html(message);
        return false;
      }else if(!myreg.test(phone)){
        message = "请输入有效的手机号码！";
        $("#error").html(message);
        return false;
      }
	return true;
}

// 获取验证码下一步
function tiJiaoNewPhone(){
	var newPhone = $("#phone").val()
		if(vphone()){
		  $.ajax({
	          type:"post",
	          url:_ctx+"/loginuser/find_passsword_verify",
	          dataType:"json",
	          data:{"phone":newPhone,"code":$("#yanzma").val()},
	          success:function(data){
	              if(data.status == 200){
	            	  alert("修改成功");
	              }else{
	                  $("#error").html(data.message);
	                  var errorDiv = document.getElementById('errorDiv');
	                  errorDiv.style.visibility='visible';
	              }
	          }
	      });
		}
	}
