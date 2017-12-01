package com.dsj.data.wap.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.code.CodeUtils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.common.command.LoginCommand;
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.other.enums.VerifyCodeTypeEnum;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.MemberEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.MemberPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.MemberService;
import com.dsj.modules.system.service.UserService;

/**
 * Created by liu on 2017/5/13.
 */

@Controller
@RequestMapping("login")
public class UserLoginController {

    Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    UserService userService;
    
    @Autowired
    SmsLogsService smsLogsService;
    
    @Autowired
    VerifyCodeService verifyCodeService;
    @Autowired
    MemberService memberService;
    @Autowired
    EasemobUserService easemobUserService;

    /**
     * 登录
     * @param command
     * @param request
     * @return
     */
    @RequestMapping("submit_login")
    @ResponseBody
    public AjaxResultVo login(LoginCommand command, HttpServletRequest request,HttpServletResponse response){
    	AjaxResultVo ajax=new AjaxResultVo();
    	Map<String, Object> resultMap = new HashMap<String, Object>();
       /* if(command.getLoginName() == null || command.getLoginName().length() != 11 || !isNumeric(command.getLoginName())){
            map.put("msg","账号输入错误，请输入11位数字！");
            return map;
        }*/
      /*  if(command.getPassword() == null || command.getPassword().length() < 6 || command.getPassword().length() > 16){
            map.put("msg","密码输入错误，请输入6到16位密码！");
            return map;
        }*/
    	
    	
        Map<String,Object> params = new HashMap<String,Object>();
        
        String username=command.getLoginName();
        if(command.getType()==2){
        	username=command.getPhone();
        }
        params.put("username",username);
        params.put("userTypes", UserType.AGENT.getValue()+","+UserType.MEMBER.getValue());
        UserPo customerPo = userService.getBy(params );
        if(command.getType()==2){//快捷登录
        	VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(command.getPhone(),VerifyCodeTypeEnum.WAP_LOGIN.getValue());
        	if(StringUtils.isBlank(command.getVerifyCode())&&vcode==null|| !command.getVerifyCode().equals(vcode.getVerifyCode())){
    			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
    			return ajax;
    		}
        	
	       if(customerPo==null){
	    	   customerPo=new UserPo();
	    	   customerPo.setCreateTime(new Date());
	    	   customerPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
	    	   customerPo.setUserType(UserType.MEMBER.getValue());
	    	   customerPo.setUpdateTime(new Date());
	    	   customerPo.setUsername(command.getPhone());
	    	   customerPo.setPhone(command.getPhone());
	    	   Long userId=userService.saveDynamic(customerPo);
	    	   customerPo.setId(userId);
	    	   
	    	   
	    	   MemberPo member = new MemberPo();
	    	   member.setCreateTime(new Date());
	    	   member.setUserId(userId);
	    	   member.setTellPhone(command.getPhone());
	    	   member.setSignupOrigin(MemberEnum.PC.getCode());
				
				memberService.saveDynamic(member);
	       }else{
	    	   if(customerPo.getUserType()!=UserType.MEMBER.getValue()&&customerPo.getUserType()!=UserType.AGENT.getValue()){
	    		
	    		   if(customerPo.getUserType()==UserType.AGENT.getValue()){
	    			   ajax.setStatus(201);
	    			   ajax.setMessage("您已注册经纪人账号");
	    		   }else  if(customerPo.getUserType()==UserType.EMPLOYEE.getValue()){
	    			   ajax.setStatus(201);
	    			   ajax.setMessage("您已注册员工账号");
	    		   }else if(customerPo.getUserType()==UserType.WARRANT.getValue()){
	    			   ajax.setStatus(201);
	    			   ajax.setMessage("您已注册权证专员账号");
	    		   }else if(customerPo.getUserType()==UserType.DEVELOPER.getValue()){
	    			   ajax.setStatus(201);
	    			   ajax.setMessage("您已注册开发商账号");
	    		   }else {
	    			   ajax.setStatus(201);
	    			   ajax.setMessage("您已注册开其他平台账号");
	    		   }
		   		   return ajax;
	    	   }
	       }
        }else{//密码登录
        	 if(StringUtils.isNotBlank(command.getPassword())){//密码
 		        if(customerPo == null){
 		          ajax.setStatusCode(StatusCode.USERNAME_OR_PASSWORD_ERROR);
 		            return ajax;
 		        }else if(StringUtils.isBlank(customerPo.getPassword())){
 		        	 ajax.setStatusCode(StatusCode.USERNAME_OR_PASSWORD_ERROR);
  		            return ajax;
 		        }else if(!customerPo.getPassword().equals( ShiroSaltAndMd5Utils.getMD5(command.getPassword()))){
 		        	ajax.setStatusCode(StatusCode.USERNAME_OR_PASSWORD_ERROR);
 		        	 return ajax;
 		        }
 	        }
        }
       
        HttpSession session = request.getSession();
        session.setAttribute(BusinessConst.WAP_USER_SIESSION,customerPo);
   
    
       String url="/mobile/views/dsj-index/dsj_index.html";
       // 取出session中登陆跳转地址
       Object obj = session.getAttribute(CommConst.COOKIE_WAP_LOGIN_URL);
       String path = request.getContextPath();
  
       if(obj == null){
    	   url=path+"/";
       } else {
    	   url = (String)obj;
           resultMap.put("url", url);
    	   session.setAttribute(CommConst.COOKIE_WAP_LOGIN_URL, null);
       }
       resultMap.put("user", customerPo);
       ajax.setStatusCode(StatusCode.SUCCESS);  
       ajax.setData(resultMap);
       return ajax;
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
		
		if(user!=null){
			if(user.getUserType()==UserType.AGENT.getValue()){
 			   ajax.setStatus(201);
 			   ajax.setMessage("您已注册经纪人账号");
 			   return ajax;
 		   }else  if(user.getUserType()==UserType.EMPLOYEE.getValue()){
 			   ajax.setStatus(201);
 			   ajax.setMessage("您已注册员工账号");
 			   return ajax;
 		   }else if(user.getUserType()==UserType.WARRANT.getValue()){
 			   ajax.setStatus(201);
 			   ajax.setMessage("您已注册权证专员账号");
 			   return ajax;
 		   }else if(user.getUserType()==UserType.DEVELOPER.getValue()){
 			   ajax.setStatus(201);
 			   ajax.setMessage("您已注册开发商账号");
 			   return ajax;
 		   }
		}
		String code=String.valueOf(CodeUtils.getSixCode());
		Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.PC_LOGIN,code));
		if(b){
			VerifyCodePo vcode=new VerifyCodePo();
			vcode.setCreateTime(new Date());
			vcode.setPhone(phone);
			vcode.setVerifyCode(code);
			vcode.setType(VerifyCodeTypeEnum.WAP_LOGIN.getValue());
			verifyCodeService.saveDynamic(vcode);
			ajax.setStatusCode(StatusCode.SUCCESS);
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
			if(user.getUserType()==5){
				ajax.setStatusCode(StatusCode.PHONE_EXIST);
				return ajax;
			}else{
				ajax.setStatusCode(StatusCode.PHONE_EXIST);
				ajax.setMessage("手机号已注册其他平台");
				return ajax;
			}
		}
	
		String code=String.valueOf(CodeUtils.getSixCode());
		Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.PC_LOGIN,code));
		if(b){
			VerifyCodePo vcode=new VerifyCodePo();
			vcode.setCreateTime(new Date());
			vcode.setPhone(phone);
			vcode.setVerifyCode(code);
			vcode.setType(VerifyCodeTypeEnum.WAP_LOGIN.getValue());
			verifyCodeService.saveDynamic(vcode);
			ajax.setStatusCode(StatusCode.SUCCESS);
		}
		
		return ajax;
	}
	
	/**
	 * 发送验证码
	 */
	@RequestMapping("save_session")
	@ResponseBody
	public AjaxResultVo saveSession(String url,Model model,HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		ajax.setStatusCode(StatusCode.SUCCESS);
		// 未登录尝试自动登录
		HttpSession session = request.getSession();
		session.setAttribute(CommConst.COOKIE_WAP_LOGIN_URL, url);
		return ajax;
	}

     public  boolean isNumeric(String str){
            for (int i = str.length();--i>=0;){
                    if (!Character.isDigit(str.charAt(i))){
                            return false;
                        }
               }
           return true;
     }



 	@RequestMapping("loginOut")
 	@ResponseBody
 	public Map<String, Object> loginOut(HttpServletRequest request) {
 		Map<String, Object> map = new HashMap<String, Object>();
 		HttpSession session = request.getSession();
 		try {
 			session.removeAttribute(BusinessConst.WAP_USER_SIESSION);
 			map.put("msg", "success");
 		} catch (Exception e) {
 			map.put("msg", "退出异常");
 		}
 		return map;
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
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.WAP_LOGIN.getValue());
		
		if(vcode==null|| !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
		}else if(code.equals(vcode.getVerifyCode())){
			logger.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
			}else{
				ajax.setData(phone);
				ajax.setStatusCode(StatusCode.SUCCESS);
//				HttpSession session = request.getSession();
//				session.setAttribute(SEESION_PHONE_VCODE,phone);
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
	public AjaxResultVo updatePassword(String phone,String password1,String password2,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		if(StringUtils.isBlank(password1)&&StringUtils.isBlank(password2)){
			ajax.setStatusCode(StatusCode.PARAMS_ERROR);
		}else{
			if(!password1.equals(password2)){
				ajax.setStatusCode(StatusCode.PASSWORD_NOT_EQ);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("phone", phone);
				UserPo user = userService.getBy(map);
				user.setPassword(ShiroSaltAndMd5Utils.getMD5(password1));
				user.setUpdatePerson(user.getId().intValue());
				userService.updateDynamic(user);
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
					UserPo user = new UserPo();
					user.setCreateTime(new Date());
					user.setPassword(ShiroSaltAndMd5Utils.getMD5(password));
					user.setUsername(phone);
					user.setPhone(phone);
					user.setUserType(UserType.MEMBER.getValue());
					user.setDelFlag(DeleteStatusEnum.NDEL.getValue());
					
					long userId = userService.saveDynamic(user);
					MemberPo member = new MemberPo();
					member.setCreateTime(new Date());
					member.setUserId(userId);
					member.setTellPhone(phone);
					member.setSignupOrigin(MemberEnum.PC.getCode());
					
					memberService.saveDynamic(member);
					ajax.setData(phone);
					ajax.setStatusCode(StatusCode.SUCCESS);
				} catch (Exception e) {
					logger.error("用户pc注册异常",e);
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
				}
				

			}
		}
		return ajax;
	}
	
	/**
	 * wap找回密码--发送验证码
	 */
	@RequestMapping("send_vcode_find_pwd")
	@ResponseBody
	public AjaxResultVo send_vcode_find_pwd(String phone,Model model,HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		UserPo user = userService.getBy(map);
		if(user==null){
			ajax.setStatusCode(StatusCode.PHONE_NOT_EXIST);
			ajax.setData("用户不存在");
		}else{
			String code=String.valueOf(CodeUtils.getSixCode());
			Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.PC_LOGIN,code));
			if(b){
				VerifyCodePo vcode=new VerifyCodePo();
				vcode.setCreateTime(new Date());
				vcode.setPhone(phone);
				vcode.setVerifyCode(code);
				vcode.setType(VerifyCodeTypeEnum.WAP_FIND_PASSWORD.getValue());
				verifyCodeService.saveDynamic(vcode);
				ajax.setStatusCode(StatusCode.SUCCESS);
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
				ajax.setData("对不起，系统异常");
			}
		}
		
		return ajax;
	}
	
	/**
	 * wap找回密码--验证验证码并修改密码
	 * @param phone
	 * @param code
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("wap_register_passsword_verify")
	@ResponseBody
	public AjaxResultVo wap_register_passsword_verify(String phone,String code,String password,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.WAP_FIND_PASSWORD.getValue());
		
		if(vcode==null || !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
			ajax.setData("验证码错误");
		}else if(code.equals(vcode.getVerifyCode())){
			logger.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
				ajax.setData("验证码过期");
			}else{
				try {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("phone", phone);
					UserPo user = userService.getBy(map);
					user.setPassword(ShiroSaltAndMd5Utils.getMD5(password));
					user.setUpdateTime(new Date());
					user.setUpdatePerson(user.getId().intValue());
					userService.updateDynamic(user);
					ajax.setData(phone);
					ajax.setStatusCode(StatusCode.SUCCESS);
				} catch (Exception e) {
					logger.error("用户找回密码异常",e);
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setData("找回密码异常");
				}
			}
		}
		return ajax;
	}
	
	/**
	 * wap注册--发送验证码
	 */
	@RequestMapping("wap_send_register_vcode")
	@ResponseBody
	public AjaxResultVo wap_send_register_vcode(String phone,Model model,HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		UserPo user = userService.getBy(map);
		if(user!=null){
			if(user.getUserType()==5){
				ajax.setStatusCode(StatusCode.PHONE_EXIST);
				ajax.setData("手机号已注册");
				return ajax;
			}else{
				ajax.setStatusCode(StatusCode.PHONE_EXIST);
				ajax.setData("手机号已注册其他平台");
				return ajax;
			}
		}
		
		String code=String.valueOf(CodeUtils.getSixCode());
		Boolean b=smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplate(SMSTemplate.PC_LOGIN,code));
		if(b){
			VerifyCodePo vcode=new VerifyCodePo();
			vcode.setCreateTime(new Date());
			vcode.setPhone(phone);
			vcode.setVerifyCode(code);
			vcode.setType(VerifyCodeTypeEnum.WAP_REGISTER.getValue());
			verifyCodeService.saveDynamic(vcode);
			ajax.setStatusCode(StatusCode.SUCCESS);
		}
		return ajax;
	}
	
	/**
	 * wap注册--验证验证码
	 * @param phone
	 * @param code
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("wap_register_verify")
	@ResponseBody
	public AjaxResultVo wap_register_verify(String phone,String code,String password,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.WAP_REGISTER.getValue());
		
		if(vcode==null || !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
			ajax.setData("验证码错误");
		}else if(code.equals(vcode.getVerifyCode())){
			logger.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
				ajax.setData("验证码过期");
			}else{
				try {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("username", phone);
					UserPo u= userService.getBy(map);
					if(u!=null){
						ajax.setStatusCode(StatusCode.USER_EXIST);
						ajax.setData("手机用户已存在");
					}else{
						UserPo user = new UserPo();
						user.setCreateTime(new Date());
						user.setPassword(ShiroSaltAndMd5Utils.getMD5(password));
						user.setUsername(phone);
						user.setPhone(phone);
						user.setUserType(UserType.MEMBER.getValue());
						user.setDelFlag(DeleteStatusEnum.NDEL.getValue());
						
						long userId = userService.saveDynamic(user);
						MemberPo member = new MemberPo();
						member.setCreateTime(new Date());
						member.setUserId(userId);
						member.setTellPhone(phone);
						member.setSignupOrigin(MemberEnum.PC.getCode());
						
						memberService.saveDynamic(member);
						//IM激活 
						try {
							UserPo po = userService.getById(userId);
							EasemobUserPo easemobUser = new EasemobUserPo();
							easemobUser.setUserName(po.getUsername());
							easemobUser.setPassWord(po.getImPassword());
							//easemobUser.setNickName(po.getRealname());
							Object result = easemobUserService
									.createNewEasemobUserSingle(easemobUser);
							if (StringUtils.isNotEmpty(result.toString()) 
									&& result.toString()
									.indexOf(po.getUsername()) > -1) {
								//easemobUserService.modifyEasemobUserNickNameWithAdminToken(easemobUser);
								po.setMarkFlag(1);
							}
							userService.updateDynamic(po);
						} catch (Exception e) {
							logger.error("用户IM激活失败",e);
						}
						ajax.setData(phone);
						ajax.setStatusCode(StatusCode.SUCCESS);
					}
				} catch (Exception e) {
					logger.error("用户wap注册异常",e);
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setData("wap用户注册异常");
				}
			}
		}
		return ajax;
	}
	
}
