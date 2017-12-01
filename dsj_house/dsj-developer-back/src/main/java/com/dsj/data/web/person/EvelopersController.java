package com.dsj.data.web.person;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 * 开发商管理
 */
@Controller
@RequestMapping(value = "back/person/eveloper")
public class EvelopersController {

	private final Logger LOGGER = LoggerFactory.getLogger(EvelopersController.class);

	@Autowired
	private EvelopersService evelopersService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	@Autowired
	VerifyCodeService verifyCodeService;
	
	@Autowired
	UploadService uploadService;
	
	@RequestMapping("get_base_info")
	@ResponseBody
	public AjaxResultVo getBaseInfo(Model model) {
		AjaxResultVo ajaxVo=new AjaxResultVo();
		try{
			
			Map<String, Object> evelopersMap = new HashMap<String, Object>();
			evelopersMap.put("userId", ShiroUtils.getSessionUser().getId());
			EvelopersVo evelopersVo=evelopersService.getVoBy(evelopersMap);
			
			if(evelopersVo.getAreaOneId()!=null){
				AreaPo area=areaService.getById(evelopersVo.getAreaOneId());
				if(area!=null){
					evelopersVo.setAreaName1(area.getName());
				}
			}
			if(evelopersVo.getAreaOneId()!=null){
				AreaPo area=areaService.getById(evelopersVo.getAreaTwoId());
				if(area!=null){
					evelopersVo.setAreaName2(area.getName());
				}
			}
			if(evelopersVo.getAreaOneId()!=null){
				AreaPo area=areaService.getById(evelopersVo.getAreaThreeId());
				
				if(area!=null){
					evelopersVo.setAreaName3(area.getName());
				}
			}
			
			if(StringUtils.isNotBlank(evelopersVo.getLoupanName())){
				String loupanNames=newHouseDirectoryAuthService.getNamesByIds(evelopersVo.getLoupanName());
				
				if((","+evelopersVo.getLoupanName()+",").contains(",0,")){
					NewHouseDirectoryAuthPo newPo=new NewHouseDirectoryAuthPo();
					newPo.setId(0l);
					if(StringUtils.isNotBlank(evelopersVo.getLoupanName())){
						loupanNames+=",其他";
					}else{
						loupanNames+="其他";
					}
				}
				
				evelopersVo.setLoupanNames(loupanNames);
			}
			ajaxVo.setData(evelopersVo);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		}catch(Exception e){
			LOGGER.error("开发商编辑页面错误",e);
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return ajaxVo;
	}

	@RequestMapping("evelopers_edit")
	@ResponseBody
	public AjaxResultVo evelopersEdit(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		AjaxResultVo ajaxVo=new AjaxResultVo();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			hashMap.put("parentId", 0);
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<AreaPo> firstAreaList = areaService.listBy(hashMap);
			resultMap.put("firstAreaList", firstAreaList);
			
			Map<String, Object> evelopersMap = new HashMap<String, Object>();
			evelopersMap.put("userId", ShiroUtils.getSessionUser().getId());
			EvelopersVo evelopersVo=evelopersService.getVoBy(evelopersMap);
			resultMap.put("evelopers", evelopersVo);
			
			if(evelopersVo.getAreaOneId()!=null){
			hashMap.put("parentId", evelopersVo.getAreaOneId());
			List<AreaPo> secondAreaList = areaService.listBy(hashMap);
			resultMap.put("secondAreaList", secondAreaList);
			}
			
			if(evelopersVo.getAreaTwoId()!=null){
				hashMap.put("parentId", evelopersVo.getAreaTwoId());
				List<AreaPo> threeAreaList = areaService.listBy(hashMap);
				resultMap.put("threeAreaList", threeAreaList);
			}
			
			if(StringUtils.isNotBlank(evelopersVo.getLoupanName())){
				List<NewHouseDirectoryAuthPo>  newHouses=newHouseDirectoryAuthService.getByIds(evelopersVo.getLoupanName());
				evelopersVo.setLoupanNames(evelopersVo.getLoupanName());
				resultMap.put("newHouses", newHouses);
				
				if((","+evelopersVo.getLoupanName()+",").contains(",0,")){
					NewHouseDirectoryAuthPo newPo=new NewHouseDirectoryAuthPo();
					newPo.setId(0l);
					newPo.setName("其他");
					newHouses.add(newPo);
				}
			}
			ajaxVo.setData(resultMap);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		}catch(Exception e){
			LOGGER.error("开发商编辑页面错误",e);
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return ajaxVo;
	}

	@RequestMapping("save_evelopers_update")
	@ResponseBody
	public AjaxResultVo saveEvelopersUpdate(HttpServletRequest request, EvelopersVo vo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			//vo.setEvelopersId(ShiroUtils.getSessionUser().getEveloperId());
			vo.setLoupanName(vo.getLoupanIds());
			vo.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
			vo.setId(ShiroUtils.getSessionUser().getEveloperId());
			evelopersService.saveEvelopersUpdate(vo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改开发商账号异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("audit")
	@ResponseBody
	public AjaxResultVo audit(HttpServletRequest request) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			UserPo po=new UserPo();
			po.setId(ShiroUtils.getSessionUser().getId());
			po.setStatus(UserStatusEnum.NO_AUDIT.getValue());
			po.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
			po.setUpdateTime(new Date());
			userService.updateDynamic(po);
			
			
			UserPo userPo=ShiroUtils.getSessionUser();
			userPo.setStatus(UserStatusEnum.NO_AUDIT.getValue());
			ShiroUtils.setSessionUser(userPo);
			 ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("提交开发商审核异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("update_password")
	@ResponseBody
	public AjaxResultVo updatePassword(String password,String password1,String password2,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		if(StringUtils.isBlank(password)&&StringUtils.isBlank(password1)&&StringUtils.isBlank(password2)){
			ajax.setStatusCode(StatusCode.PARAMS_ERROR);
		}else{
			if(!password1.equals(password2)){
				ajax.setStatusCode(StatusCode.PASSWORD_NOT_EQ);
			}else{
				UserPo user=userService.getById(ShiroUtils.getSessionUser().getId());
				password = ShiroSaltAndMd5Utils.getMD5(password);
				if(user.getPassword().equals(password)){
					//Integer salt = ShiroSaltAndMd5Utils.getSalt();
					password1 = ShiroSaltAndMd5Utils.getMD5(password2);
					user.setPassword(password1);
					
					userService.updateDynamic(user);
					ajax.setStatusCode(StatusCode.SUCCESS);
				}else{
					ajax.setStatusCode(StatusCode.USER_PASSWORD_ERROR);
				}
			}
		}
		return ajax;
	}
	
	@RequestMapping("update_phone")
	@ResponseBody
	public AjaxResultVo updatePassword(String phone,String code,Model model,
			HttpServletRequest request,HttpServletResponse response) {
		AjaxResultVo ajax=new AjaxResultVo();
		
		VerifyCodePo vcode=verifyCodeService.getVerifyByPhoneLast(phone,VerifyCodeTypeEnum.DEVELOPER_UPDATE_PHONE.getValue());
		
		if(vcode==null|| !code.equals(vcode.getVerifyCode())){
			ajax.setStatusCode(StatusCode.USER_VCODE_ERROR);
		}else if(code.equals(vcode.getVerifyCode())){
			LOGGER.info(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())+"");
			if(DateUtils.getBetweenMin(new Date(),vcode.getCreateTime())>5*60){
				ajax.setStatusCode(StatusCode.USER_VCODE_OVERTIME);
			}else{
				EvelopersPo evelopers=new EvelopersPo();
				evelopers.setOperationPhone(phone);
				evelopers.setId(ShiroUtils.getSessionUser().getEveloperId());
				evelopers.setUpdateTime(new Date());
				
				UserPo user=new UserPo();
				user.setId(ShiroUtils.getSessionUser().getId());
				user.setPhone(phone);
				user.setUpdateTime(new Date());
				user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				evelopersService.updateOrUserDynamic(evelopers,user);
				ajax.setStatusCode(StatusCode.SUCCESS);
			}
		}
	
		return ajax;
	}
	
	@RequestMapping("head_portrait")
	@ResponseBody
	public AjaxResultVo headPortrait(String smallBase64){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		try{
		String smallImage=uploadService.uploadHeadProtrait(smallBase64);
		UserPo user=new UserPo();
		user.setId(ShiroUtils.getSessionUser().getId());
		user.setAvatar(smallImage);
		userService.updateDynamic(user);
		
		UserPo userPo= ShiroUtils.getSessionUser();
		userPo.setAvatar(smallImage);
		 ShiroUtils.setSessionUser(userPo);
		 
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(smallImage);
		}catch(Exception e){
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("头像上传错误",e);
		}
		return ajaxVo;
	}
	
}
