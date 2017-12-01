package com.dsj.data.web.login;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.code.CodeUtils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.common.web.BaseController;
import com.dsj.data.web.login.vo.RegisterVo;
import com.dsj.modules.other.enums.VerifyCodeTypeEnum;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EvelopersVo;


/**
 * 找回密码，注册
 */
@Controller
@RequestMapping(value = "loginuser")
public class LoginUserController  extends BaseController {
	private  final Logger logger = LoggerFactory.getLogger(LoginUserController.class);
	private static String SEESION_PHONE_VCODE ="developer_session_phone_vcode";

	@Autowired
	SmsLogsService smsLogsService;
	@Autowired
	VerifyCodeService verifyCodeService;
	@Autowired
	UserService userService;
	@Autowired
	EvelopersService evelopersService;
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
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		UserPo user = userService.getBy(map);
		if(user==null){
			ajax.setStatusCode(StatusCode.PHONE_NOT_EXIST);
			return ajax;
		}else{
			if(user.getUserType()!=UserType.DEVELOPER.getValue()){
				ajax.setStatusCode(StatusCode.USER_NOT_EXIST);
				ajax.setMessage("该账号非开发商账号");
				return ajax;
			}
		}
		String code=String.valueOf(CodeUtils.getSixCode());
		Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.XCX_ZC,code));
		VerifyCodePo vcode=new VerifyCodePo();
		vcode.setCreateTime(new Date());
		vcode.setPhone(phone);
		vcode.setVerifyCode(code);
		vcode.setType(VerifyCodeTypeEnum.DEVELOPER_FIND_REGISTER.getValue());
		verifyCodeService.saveDynamic(vcode);
		if(b){
			ajax.setStatusCode(StatusCode.SUCCESS);
		}
		return ajax;
	}
	
	/**
	 * 修改手机号发送验证码
	 */
	@RequestMapping("send_vcode_update_phone")
	@ResponseBody
	public AjaxResultVo sendVcodeUpdatePhone(String phone,Model model,HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("phone", phone);
			UserPo user = userService.getBy(map);
			if(user!=null){
				ajax.setStatusCode(StatusCode.PHONE_EXIST_2);
				ajax.setData("手机号已被注册");
				return ajax;
			}
			String code=String.valueOf(CodeUtils.getSixCode());
			Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.XCX_ZC,code));
			VerifyCodePo vcode=new VerifyCodePo();
			vcode.setCreateTime(new Date());
			vcode.setPhone(phone);
			vcode.setVerifyCode(code);
			vcode.setType(VerifyCodeTypeEnum.DEVELOPER_UPDATE_PHONE.getValue());
			verifyCodeService.saveDynamic(vcode);
			if(b){
				ajax.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("修改手机号发送验证码",e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			ajax.setData("对不起，系统异常");
			return ajax;
		}
		return ajax;
	}
	
	/**
	 * 发送验证码
	 */
	@RequestMapping("send_register_vcode")
	@ResponseBody
	public AjaxResultVo send_register_vcode(String phone,Model model,HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		UserPo user = userService.getBy(map);
		if(user!=null){
			if(user.getUserType()==UserType.DEVELOPER.getValue()){
				ajax.setStatusCode(StatusCode.PHONE_EXIST);
				
			}else{
				ajax.setStatusCode(StatusCode.PHONE_EXIST);
				ajax.setMessage("手机号已注册其他平台");
			}
			
		}else{
			String code=String.valueOf(CodeUtils.getSixCode());
			Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.PC_LOGIN,code));
			if(b){
				VerifyCodePo vcode=new VerifyCodePo();
				vcode.setCreateTime(new Date());
				vcode.setPhone(phone);
				vcode.setVerifyCode(code);
				vcode.setType(VerifyCodeTypeEnum.DEVELOPER_REGISTER.getValue());
				verifyCodeService.saveDynamic(vcode);
				ajax.setStatusCode(StatusCode.SUCCESS);
			}
		}
		
		return ajax;
	}
	
	/**
	 * 注册
	 */
	@RequestMapping("register_passsword_verify")
	@ResponseBody
	public AjaxResultVo register(RegisterVo rvo,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		if(StringUtils.isBlank(rvo.getUsername())){
			ajax.setStatusCode(StatusCode.USERNAME_NOT_NULL);
			return ajax;
		}
		if(StringUtils.isBlank(rvo.getPhone())){
			ajax.setStatusCode(StatusCode.PHONE_NOT_NULL);
			return ajax;
		}
		if(StringUtils.isBlank(rvo.getPassword())){
			ajax.setStatusCode(StatusCode.PASSWORD_NOT_NULL);
			return ajax;
		}
		if(StringUtils.isBlank(rvo.getVerifycode())){
			ajax.setStatusCode(StatusCode.VCODE_NOT_NULL);
			return ajax;
		}
		
		
		UserPo userPo=userService.getUserByName(rvo.getUsername());
		if(userPo!=null){
			ajax.setStatusCode(StatusCode.USER_EXIST);
			return ajax;
		}
		UserPo userPhone=userService.getUserByPhone(rvo.getPhone());
		if(userPhone!=null){
			ajax.setStatusCode(StatusCode.PHONE_EXIST);
			return ajax;
		}
		
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(rvo.getPhone(),VerifyCodeTypeEnum.DEVELOPER_REGISTER.getValue());
		String code=rvo.getVerifycode();
		if(vcode==null|| !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
		}else if(code.equals(vcode.getVerifyCode())){
			logger.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
			}else{
				EvelopersVo evo=new EvelopersVo();
				BeanUtils.copyProperties( rvo, evo);
				try {
					evo.setStatus(UserStatusEnum.WAIT.getValue());
					evo.setPhone(rvo.getPhone());
					evo.setOperationPhone(rvo.getPhone());
					evelopersService.saveEvelopersAdd( evo);
				} catch (Exception e) {
					logger.error("注册失败",e);
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					return ajax;
				}
				ajax.setStatusCode(StatusCode.SUCCESS);
			}
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
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.DEVELOPER_FIND_REGISTER.getValue());
		
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
					userService.updatePasswordByPhone(session.getAttribute(SEESION_PHONE_VCODE).toString(),password1,UserType.DEVELOPER.getValue());
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


















