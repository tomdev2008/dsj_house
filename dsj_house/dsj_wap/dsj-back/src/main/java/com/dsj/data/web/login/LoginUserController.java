package com.dsj.data.web.login;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.code.CodeUtils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.common.web.BaseController;
import com.dsj.modules.other.enums.VerifyCodeTypeEnum;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.service.UserService;


/**
 * 找回密码，注册
 */
@Controller
@RequestMapping(value = "loginuser")
public class LoginUserController  extends BaseController {
	private  final Logger logger = LoggerFactory.getLogger(LoginUserController.class);

	@Autowired
	SmsLogsService smsLogsService;
	@Autowired
	VerifyCodeService verifyCodeService;
	@Autowired
	UserService userService;
	private static String SEESION_PHONE_VCODE="session_phone_vcode";
	
	/**
	 * 找回密码发送验证码
	 * @author wyt
	 */
	@RequestMapping("to_find_password")
	public String toFindPassword(Model model) {
		return "login/find_password";
	}
	
	/**
	 * 发送验证码
	 */
	@RequestMapping("send_vcode")
	@ResponseBody
	public AjaxResultVo sendVcode(String phone,Model model,HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		ajax.setStatusCode(StatusCode.SUCCESS);
		String code=String.valueOf(CodeUtils.getSixCode());
		Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.XCX_ZC,code));
		VerifyCodePo vcode=new VerifyCodePo();
		vcode.setCreateTime(new Date());
		vcode.setPhone(phone);
		vcode.setVerifyCode(code);
		vcode.setType(VerifyCodeTypeEnum.BACK_FIND_PASSWORD.getValue());
		verifyCodeService.saveDynamic(vcode);
		if(b){
			ajax.setStatusCode(StatusCode.SUCCESS);
		}
		return ajax;
	}
	
	/**
	 * 验证验证码
	 * @param phone
	 * @param code
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("find_passsword_verify")
	@ResponseBody
	public AjaxResultVo findPassswordVerify(String phone,String code,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.BACK_FIND_PASSWORD.getValue());
		
		if(vcode==null|| !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
		}else if(code.equals(vcode.getVerifyCode())){
			logger.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
			}else{
				ajax.setStatusCode(StatusCode.SUCCESS);
				HttpSession session = request.getSession();
				session.setAttribute(SEESION_PHONE_VCODE,phone);
			}
		}
		return ajax;
	}
	
	/**
	 * 重置密码页面
	 * @param phone
	 * @param code
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("to_update_password")
	public String toUpdatePassword(String phone,String code,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		return "login/to_update_password";
	}
	
	/**
	 * 修改密码
	 * @param phone
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("update_password")
	@ResponseBody
	public AjaxResultVo updatePassword(String password1,String password2,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		if(StringUtils.isBlank(password1)&&StringUtils.isBlank(password2)){
			ajax.setStatusCode(StatusCode.PARAMS_ERROR);
		}else{
			if(!password1.equals(password2)){
				ajax.setStatusCode(StatusCode.PASSWORD_NOT_EQ);
			}else{
				HttpSession session = request.getSession();
				if(session.getAttribute(SEESION_PHONE_VCODE)!=null){
					userService.updatePasswordByPhone(session.getAttribute(SEESION_PHONE_VCODE).toString(),password1,UserType.EMPLOYEE.getValue());
					session.removeAttribute(SEESION_PHONE_VCODE);
					ajax.setStatusCode(StatusCode.SUCCESS);
				}else{
					ajax.setStatusCode(StatusCode.SESSION_PHONE_ERROR);
				}
			}
		}
		return ajax;
	}
}


















