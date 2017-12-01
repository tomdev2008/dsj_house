package com.dsj.data.web.agent;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.IdNumberUtil;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.sms.SMSUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.service.UploadService;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;
import com.dsj.modules.evaluate.po.AgentGradePo;
import com.dsj.modules.evaluate.service.AgentEvaluateService;
import com.dsj.modules.evaluate.service.AgentGradeService;
import com.dsj.modules.evaluate.service.AgentInfoService;
import com.dsj.modules.mobile400.service.MobileService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentVo;


@Controller
@RequestMapping(value = "back/**/agent")
public class AgentController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentService agentService;

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private UserService userService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AgentEvaluateService agentEvaluateService;
	@Autowired
	private EasemobUserService easemobUserService;
	@Autowired
	private MobileService mobileService;
	@Autowired
	UploadService uploadService;
	@Autowired
	private AgentGradeService agentGradeService;
	@Autowired
	private AgentInfoService agentInfoService;
	
	@Autowired
	private SmsLogsService smsLogsService;
 	/**
	 * 经纪人首页
	 * @param userId
	 * @return
	 */
	@RequestMapping("index")
    public String index() { 
		AgentVo agent = agentService.getByUserId(ShiroUtils.getSessionUser().getId().intValue());
		if(agent.getAuditStatus()==1||agent.getAuditStatus()==2){
			//审核通过或者正在审核
			return "redirect:/app/agent-index.html";
		}else{
			//其他状态
			return "redirect:/app/person-detail.html";
		}
		
	}
	
	/**
	 * 保存经纪人信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("saveAgent")
	@ResponseBody
    public AjaxResultVo saveAgent(AgentPo agent) { 
		AjaxResultVo result = new AjaxResultVo();
		
		try {
			if(agent.getName()!=null){
				UserPo user = new UserPo();
				user.setRealname(agent.getName());
				user.setId(agent.getUserId());
				userService.updateDynamic(user);
				
				
				/*im 更改昵称*/
				UserPo po = userService.getById(agent.getUserId());
				EasemobUserPo easemobUser = new EasemobUserPo();
				easemobUser.setUserName(po.getUsername());
				easemobUser.setNickName(po.getRealname());
				easemobUserService.modifyEasemobUserNickNameWithAdminToken(
						easemobUser);
			}
			
			//经纪人评价
			if(agent.getWorkyears()!=null||agent.getEducation()!=null){
				Long cityCode = null;
				String cityName = null;
				AgentPo po = agentService.getById(agent.getId());
				if(agent.getCity()==null){
					
					if(po.getCity()!=null&&!po.getCity().equals("")){
						Map<String,Object> map1 = new HashMap<String,Object>();
						map1.put("areaCode", po.getCity());
						AreaPo a = areaService.getBy(map1);
						cityCode = Long.parseLong(po.getCity());
						cityName = a.getName();
					}
				}else{
					Map<String,Object> map1 = new HashMap<String,Object>();
					map1.put("areaCode", agent.getCity());
					AreaPo a = areaService.getBy(map1);
					cityCode = Long.parseLong(agent.getCity());
					cityName = a.getName();
				}
				
				if(agent.getEducation()!=null){
					int education = 0;
					if(agent.getEducation().equals("本科")||agent.getEducation().equals("研究生及以上")){
						education = 1;
					}else if(agent.getEducation().equals("大专")){
						education = 2;
					}else{
						education = 3;
					}
					agentInfoService.addEducationScore(po.getAgentCode().longValue(), po.getName(), cityCode,cityName, education, ShiroUtils.getSessionUser().getId().intValue());
				}
				if(agent.getWorkyears()!=null){
					int workyear = 0;
					if(agent.getWorkyears()>=3){
						workyear = 1;
					}else{
						workyear = 2;
					}
					//经纪人评价体系
					
					agentInfoService.addExperienceScore(po.getAgentCode().longValue(), po.getName(), cityCode, cityName, workyear, ShiroUtils.getSessionUser().getId().intValue());
				}
			}
			
			
			
			
			
			if(agent.getIdNumber()!=null){
				agent.setSex(IdNumberUtil.getGenderByIdCard(agent.getIdNumber()));
				agent.setStar(IdNumberUtil.getConstellationById(agent.getIdNumber()));
				agent.setBirthday(IdNumberUtil.getBirthByIdCard(agent.getIdNumber())+"-"+IdNumberUtil.getMonthByIdCard(agent.getIdNumber())+"-"+IdNumberUtil.getDateByIdCard(agent.getIdNumber()));
			}
			
			agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			agent.setUpdateTime(new Date());
			agentService.updateDynamic(agent);
			
			
			
			
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("更新用户信息异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 根据登陆，获取经纪人用户信息
	 * @return
	 */
	@RequestMapping("getAgentInfo")
	@ResponseBody
    public AjaxResultVo getAgentInfo() { 
		AjaxResultVo result = new AjaxResultVo();
		
		try {
			AgentVo agent = agentService.getByUserId(ShiroUtils.getSessionUser().getId().intValue());
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(agent);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

	/**
	 * 保存认证信息，审核状态改为未提交认证
	 * @param agent
	 * @return
	 */
	@RequestMapping("saveAuth")
	@ResponseBody
	public AjaxResultVo saveAuth(AgentPo agent) { 
		AjaxResultVo result = new AjaxResultVo();
		
		try {
			
			agent.setAuditStatus(AgentStatus.UN_APPLY.getCode());
			agent.setUpdateTime(new Date());
			agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			result.setStatusCode(StatusCode.SUCCESS);
			
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 提交认证,审核状态改为待审核
	 * @param agent
	 * @return
	 */
	@RequestMapping("goAuth")
	@ResponseBody
	public AjaxResultVo goAuth(AgentPo agent) { 
		AjaxResultVo result = new AjaxResultVo();
		
		try {
			
			agent.setAuditStatus(AgentStatus.UN_AUDIT.getCode());
			agent.setUpdateTime(new Date());
			agent.setApplyTime(new Date());
			agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			agentService.updateDynamic(agent);
			
			AgentPo a = agentService.getById(agent.getId());
			String str = a.getName()+"@_s_@"+a.getTellPhone();
			/*
			 * 短信通知管理员审核经纪人
			 * */
			List<String> phones = userService.getAdminPhones();
			for(int i=0;i<phones.size();i++){
				//SMSUtil.sendSMS(SMSTemplate.getDrawingTemplateTwo(SMSTemplate.AUDIT_NOTICE, str), phones.get(i));
				
				smsLogsService.saveLogsAndsend( phones.get(i), SMSTemplate.getDrawingTemplateTwo(SMSTemplate.AUDIT_NOTICE, str));
			}
			
			
			result.setStatusCode(StatusCode.SUCCESS);
			
		} catch (Exception e) {
			LOGGER.error("经纪人提交审核异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("areaList")
	@ResponseBody
    public AjaxResultVo areaList(HttpServletRequest request,
            HttpServletResponse response) { 
		AjaxResultVo result = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 110100);
		map.put("isCustom", 1);//1代表常规城市，不传isCustom,默认查找全部，包括自己新增的
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> areaList = null;
		try {
			areaList = areaService.listBy(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(areaList);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * 根据输入,模糊查询新房楼盘名称
	 * @param name
	 * @return
	 */
	@RequestMapping("getNewHouseName")
	@ResponseBody
    public AjaxResultVo getNewHouseName(String name) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			List<NewHouseDirectoryAuthPo> nameList = newHouseDirectoryAuthService.listBy(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(nameList);
		} catch (Exception e) {
			LOGGER.error("楼盘名称查询异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	/**
	 * 根据输入,模糊查询二手房楼盘名称
	 * @param name
	 * @return
	 */
	@RequestMapping("getOldHouseName")
	@ResponseBody
    public AjaxResultVo getOldHouseName(String name) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sprayName", name);
			List<HouseDirectoryPo> nameList = houseDirectoryService.listBy(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(nameList);
		} catch (Exception e) {
			LOGGER.error("楼盘名称查询异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping("changePwd")
	@ResponseBody
    public AjaxResultVo changePwd(long userId,String oldPwd,String newPwd) { 
		AjaxResultVo result = new AjaxResultVo();
		// 修改user表   用了之前员工操作的方法
		boolean isTrue = false;
		UserPo user = userService.getById(userId);
		if(ShiroSaltAndMd5Utils.getMD5(oldPwd).equals(user.getPassword())){
			isTrue = true;
		}
		if(isTrue){
			try {
				user.setPassword(ShiroSaltAndMd5Utils.getMD5(newPwd));
				user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				user.setUpdateTime(new Date());
				userService.updateDynamic(user);
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("修改密码异常", e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}			
		}else{
			result.setStatusCode(StatusCode.NO_RESULT);
			result.setMessage("原密码输入不正确");
			result.setData(null);
		}
		return result;

	}
	/**
	 * 发送短信验证码
	 * @param userId
	 * @param phone
	 * @param step
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("verifyCode")
	@ResponseBody
    public AjaxResultVo verifyCode(long userId,String phone,int step) throws UnsupportedEncodingException { 
		AjaxResultVo result = new AjaxResultVo();
		int code = new Random().nextInt(899999) + 100000;;
		VerifyCodePo co = new VerifyCodePo();
		try {
			if(step==1){
					SMSUtil.sendSMS(SMSTemplate.getDrawingTemplate(SMSTemplate.XCX_ZC, String.valueOf(code)), phone);
					co.setPhone(phone);
					co.setVerifyCode(String.valueOf(code));
					co.setCreateTime(new Date());
					verifyCodeService.save(co);
					result.setStatusCode(StatusCode.SUCCESS);
					result.setMessage("短信发送成功");  
			}
			if(step==2){			
					SMSUtil.sendSMS(SMSTemplate.getDrawingTemplate(SMSTemplate.XCX_ZC, String.valueOf(code)), phone);
					co.setPhone(phone);
					co.setVerifyCode(String.valueOf(code));
					co.setCreateTime(new Date());
					verifyCodeService.save(co);			
					result.setStatusCode(StatusCode.SUCCESS);
					result.setMessage("短信发送成功");
			}
			
		} catch (Exception e) {
			LOGGER.error("发送验证码异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		
		
		return result;

	}
	@RequestMapping("changePhone")
	@ResponseBody
    public AjaxResultVo changePhone(long userId,String phone,int step,Long agentId,String verifyCode) throws UnsupportedEncodingException { 
		AjaxResultVo result = new AjaxResultVo();
		VerifyCodePo v = verifyCodeService.getVerifyByPhoneLast(phone, null);
		
		String oldPhone = "";
		if(v.getVerifyCode().equals(verifyCode)){
			if(step==1){			
				result.setStatusCode(StatusCode.SUCCESS);
			}
			if(step==2){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("phone", phone);
				UserPo user = userService.getBy(map);
				if(user==null){
					UserPo u = userService.getById(userId);
					oldPhone = u.getPhone();
					u.setUsername(phone);
					u.setPhone(phone);
					u.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
					u.setUpdateTime(new Date());
					userService.updateDynamic(u);

					AgentVo a = agentService.getByUserId(userId);
					a.setTellPhone(phone);
					a.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
					a.setUpdateTime(new Date());
					agentService.updateDynamic(a);
					result.setStatusCode(StatusCode.SUCCESS);
					
					//经纪人绑定400修改
					mobileService.updateAgentPhone400(oldPhone,phone,agentId);
				}else{
					result.setStatusCode(StatusCode.PHONE_EXIST);
					result.setMessage("手机号已存在");
				}

				
			}
		}else{
			
			result.setStatusCode(StatusCode.NO_RESULT);
			result.setMessage("验证码输入有误");
		}
		
		
		
		return result;

	}
	/**
	 * 根据父级地区编码查询商圈
	 * 
	 * @param areaCode 父级地区编码
	 * @return
	 */
	@RequestMapping("business")
	@ResponseBody
	public AjaxResultVo business(Integer areaCode) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<TradeAreaPo> list = null;
		try {
			map.put("parentId", areaCode);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			list = tradeAreaService.listBy(map);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	/**
	 * 查询经纪公司
	 * @return
	 */
	@RequestMapping("company")
	@ResponseBody
	public AjaxResultVo company() {
		AjaxResultVo ajax = new AjaxResultVo();

		try {
			List<CompanyPo> companyList = null;

			companyList = companyService.listBy(null);

			ajax.setData(companyList);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("经纪公司查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	/**
	 * 更改背景图片
	 * @return
	 */
	@RequestMapping("background")
	@ResponseBody
	public AjaxResultVo background(AgentPo p) {
		AjaxResultVo ajax = new AjaxResultVo();

		try {
			agentService.updateDynamic(p);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("背景图片更换异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	@RequestMapping("avatar")
	@ResponseBody
	public AjaxResultVo avatar(String taBase64,String reBase64,long id){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		try{
		String taImage=uploadService.uploadHeadProtrait(taBase64);//正方形
		String reImage=uploadService.uploadHeadProtrait(reBase64);//长方形
		UserPo user=new UserPo();
		user.setId(ShiroUtils.getSessionUser().getId());
		user.setAvatar(taImage);
		userService.updateDynamic(user);
		
		AgentPo agent = agentService.getById(id);
		agent.setAvatarUrl(taImage);
		agent.setAvatarReUrl(reImage);
		agentService.updateDynamic(agent);
		
		
		//新房solr
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("agentIds", agent.getUserId());
		newHouseDirectoryAuthService.saveNewHouseToSolrByAgentId(map);
		
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(taImage+"|"+reImage);
		}catch(Exception e){
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("头像上传错误",e);
		}
		return ajaxVo;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("grade")
	@ResponseBody
    public AjaxResultVo grade() { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentVo a = agentService.getByUserId(ShiroUtils.getSessionUser().getId());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("agentId", a.getAgentCode());
			List<AgentEvaluatePo>  list= agentEvaluateService.listBy(map);
			if(list.size()>0){
				result.setData(list);
			}else{
				result.setData(0);//为空
			}
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("评价获取异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;

	}
	
	/**
	 * 获取等级
	 * @return
	 */
	@RequestMapping("getRank")
	@ResponseBody
    public AjaxResultVo getRank() { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentVo a = agentService.getByUserId(ShiroUtils.getSessionUser().getId());
			Map<String,Object> map = new HashMap<String,Object>();
			
			map = agentEvaluateService.getRankAndScore(a.getAgentCode());
			if(map==null){
				map.put("name", "购房咨询师");
				map.put("smallIcon", "http://dasouk.oss-cn-qingdao.aliyuncs.com/upload/pic/20170918151301091510.png");
				map.put("bigIcon", "http://dasouk.oss-cn-qingdao.aliyuncs.com/upload/pic/20170918151302233629.png");
				map.put("totalScore", 0);
				map.put("gradeNo",1);
			}
			result.setData(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取等级异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;

	}
	
	/**
	 * 获取所有等级
	 * @return
	 */
	@RequestMapping("getAllRank")
	@ResponseBody
    public AjaxResultVo getAllRank() { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			List<AgentGradePo> list = agentGradeService.listBy(null);
			
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取所有等级异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;

	}


}
