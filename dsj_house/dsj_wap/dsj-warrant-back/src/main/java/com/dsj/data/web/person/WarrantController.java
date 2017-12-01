package com.dsj.data.web.person;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.service.UploadService;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.other.enums.VerifyCodeTypeEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 * 权证管理
 */
@Controller
@RequestMapping(value = "back/**/person/warrant")
public class WarrantController {

	private final Logger LOGGER = LoggerFactory.getLogger(WarrantController.class);

	@Autowired
	private UserService userService;

	@Autowired
	VerifyCodeService verifyCodeService;

	@Autowired
	UploadService uploadService;

	@Autowired
	private PropertyService propertyService;
	@RequestMapping("to_update_password")
	public String updatePassword() {
		return "person/update_pwd";
	}

	@RequestMapping("update_password")
	@ResponseBody
	public AjaxResultVo updatePassword(String password, String password1, String password2, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResultVo ajax = new AjaxResultVo();
		if (StringUtils.isBlank(password) && StringUtils.isBlank(password1) && StringUtils.isBlank(password2)) {
			ajax.setStatusCode(StatusCode.PARAMS_ERROR);
		} else {
			if (!password1.equals(password2)) {
				ajax.setStatusCode(StatusCode.PASSWORD_NOT_EQ);
			} else {
				UserPo user = userService.getById(ShiroUtils.getSessionUser().getId());
				password = ShiroSaltAndMd5Utils.getMD5(password);
				if (user.getPassword().equals(password)) {
					password1 = ShiroSaltAndMd5Utils.getMD5(password2);
					user.setPassword(password1);
					userService.updateDynamic(user);
					ajax.setStatusCode(StatusCode.SUCCESS);
				} else {
					ajax.setStatusCode(StatusCode.USER_PASSWORD_ERROR);
				}
			}
		}
		return ajax;
	}

	@RequestMapping("to_upload_photo")
	public String uploadPhoto(Model model) {
		UserPo user = userService.getById(ShiroUtils.getSessionUser().getId().intValue());
		model.addAttribute("user", user);
		return "person/upload_photo";
	}
	
	
	@RequestMapping("upload_photo")
	@ResponseBody
	public AjaxResultVo uploadUpdatePhoto(UserPo user) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateDynamic(user);
		} catch (Exception e) {
			LOGGER.error("修改头像异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		try {
			propertyService.updatePhoto(user);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改头像异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	

	@RequestMapping("to_update_phone")
	public String updatePhone(Model model) {
		UserPo user = userService.getById(ShiroUtils.getSessionUser().getId().intValue());
		model.addAttribute("user", user);
		return "person/update_phone";
	}

	@RequestMapping("to_update_newPhone")
	public String toUpdatePhone() {
		return "person/update_phone_new";
	}
	
	@RequestMapping("updateNewPhone")
	@ResponseBody
	public AjaxResultVo updateNewPhone(String phone,String code,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.WARRANT_FIND_REGISTER.getValue());
		
		if(vcode==null|| !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
		}else if(code.equals(vcode.getVerifyCode())){
			LOGGER.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
			}else{
				PropertyPo propertyPo = new PropertyPo();
				propertyPo.setTellPhone(phone);
				propertyPo.setId(ShiroUtils.getSessionUser().getPropertyId());
				propertyPo.setUpdateTime(new Date());
				
				UserPo user=new UserPo();
				user.setId(ShiroUtils.getSessionUser().getId());
				user.setPhone(phone);
				user.setUsername(phone);
				user.setUpdateTime(new Date());
				user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				propertyService.updateOrUserDynamic(propertyPo,user);
				ajax.setStatusCode(StatusCode.SUCCESS);
				UserPo sessionUser = ShiroUtils.getSessionUser();
				sessionUser.setPhone(phone);
				ShiroUtils.setSessionUser(sessionUser);
			}
		}
	
		return ajax;
	}

}
