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
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.other.enums.VerifyCodeTypeEnum;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.modules.system.enums.MemberEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.MemberPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
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
	@Autowired
	AgentService agentService;
	@Autowired
	EasemobUserService easemobUserService;
	
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
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		UserPo user = userService.getBy(map);
		if(user==null){
			ajax.setStatusCode(StatusCode.PHONE_NOT_EXIST);
			return ajax;
		}else{
			if(user.getUserType()!=UserType.AGENT.getValue()){
				ajax.setStatusCode(StatusCode.USER_NOT_EXIST);
				ajax.setMessage("该账号非经纪人账号");
				return ajax;
			}
		}
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
					userService.updatePasswordByPhone(session.getAttribute(SEESION_PHONE_VCODE).toString(),password1,UserType.AGENT.getValue());
					session.removeAttribute(SEESION_PHONE_VCODE);
					ajax.setStatusCode(StatusCode.SUCCESS);
				}else{
					ajax.setStatusCode(StatusCode.SESSION_PHONE_ERROR);
				}
			}
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
			if(user.getUserType()==UserType.AGENT.getValue()){
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
				vcode.setType(VerifyCodeTypeEnum.PC_LOGIN.getValue());
				verifyCodeService.saveDynamic(vcode);
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
	@RequestMapping("register_passsword_verify")
	@ResponseBody
	public AjaxResultVo register_passsword_verify(String phone,String code,String password,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.PC_LOGIN.getValue());
		
		if(vcode==null|| !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
		}else if(code.equals(vcode.getVerifyCode())){
			logger.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
			}else{
				try {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("username", phone);
					UserPo u= userService.getBy(map);
					if(u!=null){
						ajax.setStatusCode(StatusCode.USER_EXIST);
					}else{
						UserPo user = new UserPo();
						user.setCreateTime(new Date());
						user.setPassword(ShiroSaltAndMd5Utils.getMD5(password));
						user.setUsername(phone);
						user.setPhone(phone);
						user.setUserType(UserType.AGENT.getValue());
						user.setDelFlag(DeleteStatusEnum.NDEL.getValue());
						
						long userId = userService.saveDynamic(user);
						AgentPo agent = new AgentPo();
						agent.setAgentCode(Integer.valueOf(agentService.getAgentCode()));
						agent.setTellPhone(phone);
						agent.setAuditStatus(AgentStatus.UN_APPLY.getCode());
						agent.setCreateTime(new Date());
						agent.setIsExternalRegist(AgentStatus.IS_EXTERNAL_REGIST.getCode());
						agent.setSort(AgentStatus.SORT_DEFAULT.getCode());
						agent.setUserId(userId);
						agentService.saveDynamic(agent);
						
						//IM激活 
						/* start */
						try {
							UserPo po = userService.getById(userId);
							EasemobUserPo easemobUser = new EasemobUserPo();
							easemobUser.setUserName(po.getUsername());
							easemobUser.setPassWord(po.getImPassword());
							//easemobUser.setNickName(po.getRealname());
							easemobUserService.createNewEasemobUserSingle(easemobUser);
							//easemobUserService.modifyEasemobUserNickNameWithAdminToken(easemobUser);
							po.setMarkFlag(1);
							userService.updateDynamic(po);
						} catch (Exception e) {
							logger.error("经纪人IM激活失败",e);
						}
						
						/* start */
						
						ajax.setData(phone);
						ajax.setStatusCode(StatusCode.SUCCESS);
					}
					
				} catch (Exception e) {
					logger.error("经纪人后台注册异常",e);
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
				}
				

			}
		}
		return ajax;
	}
}


















