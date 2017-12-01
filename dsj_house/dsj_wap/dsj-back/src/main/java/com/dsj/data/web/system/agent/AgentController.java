package com.dsj.data.web.system.agent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.IdNumberUtil;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.AgentNewsEnum;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.evaluate.service.AgentInfoService;
import com.dsj.modules.newhouse.enums.NewHouseEditEnum;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentSortVo;
import com.dsj.modules.system.vo.AgentVo;


@Controller
@RequestMapping(value = "back/**/agent")
public class AgentController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentService agentService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AgentInfoService agentInfoService;
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	
	@Autowired
	private HouseNewsService houseNewsService;
	@Autowired
	private EasemobUserService easemobUserService;
	/**
	 * 经纪人列表页
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("agentList")
    public ModelAndView agentList(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		mav.addObject("firstAreaList", firstAreaList);
		mav.setViewName("system/agent/agent_list");
		return mav;
	}
	/**
	 * 经纪人列表页
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> dataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("sortApplyTime", 1);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("用户账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	/**
	 * 经纪人添加页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("add")
    public ModelAndView add(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		List<CompanyPo> companyList = null;
		try {
			companyList = companyService.listBy(null);
		} catch (Exception e) {
			LOGGER.error("经纪公司查询异常", e);
		}
		mav.addObject("companyList", companyList);
		mav.addObject("firstAreaList", firstAreaList);
		String agentCode = agentService.getAgentCode();
		mav.addObject("agentCode",agentCode);
		mav.setViewName("system/agent/agent_add");
		return mav;
	}
	/**
	 * 经纪人添加提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addAgent")
	@ResponseBody
    public AjaxResultVo addAgent(AgentPo agent) { 
		AjaxResultVo result = new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", agent.getTellPhone());
		List<UserPo> list = userService.listBy(map);
		if(list.size()>0){
			result.setStatusCode(StatusCode.PHONE_EXIST);
			result.setMessage("手机号已注册");
			return result;
		}else{
			UserPo user = new UserPo();
			user.setAvatar(agent.getAvatarUrl());
			user.setRealname(agent.getName());
			user.setUsername(agent.getTellPhone());
			user.setPhone(agent.getTellPhone());
			user.setCreatePerson(ShiroUtils.getSessionUser().getId().intValue());
			user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
			user.setCreateTime(new Date());
			user.setUserType(UserType.AGENT.getValue());
			try {
				long userId = employeeService.addUser(user);

				agent.setSex(IdNumberUtil.getGenderByIdCard(agent.getIdNumber()));
				agent.setStar(IdNumberUtil.getConstellationById(agent.getIdNumber()));
				agent.setBirthday(IdNumberUtil.getBirthByIdCard(agent.getIdNumber())+"-"+IdNumberUtil.getMonthByIdCard(agent.getIdNumber())+"-"+IdNumberUtil.getDateByIdCard(agent.getIdNumber()));
				agent.setApplyTime(new Date());
				agent.setUserId(userId);
				agent.setCreateTime(new Date());
				agent.setCreateUser(ShiroUtils.getSessionUser().getId().intValue());
				agent.setUpdateTime(new Date());
				agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
				agent.setAuditStatus(AgentStatus.UN_AUDIT.getCode());
				agent.setIsExternalRegist(AgentStatus.NOT_EXTERNAL_REGIST.getCode());
				agent.setSort(AgentStatus.SORT_DEFAULT.getCode());
				agentService.saveDynamic(agent);
				
				Long cityCode = null;
				String cityName = null;
				if(StringUtils.isNotBlank(agent.getCity())){
					Map<String,Object> map1 = new HashMap<String,Object>();
					map1.put("areaCode", agent.getCity());
					AreaPo a = areaService.getBy(map1);
					cityCode = Long.parseLong(agent.getCity());
					cityName = a.getName();
				}
				int education = 0;
				if(agent.getEducation().equals("本科")||agent.getEducation().equals("研究生及以上")){
					education = 1;
				}else if(agent.getEducation().equals("大专")){
					education = 2;
				}else{
					education = 3;
				}
				int workyear = 0;
				if(agent.getWorkyears()>=3){
					workyear = 1;
				}else{
					workyear = 2;
				}
				//经纪人评价体系
				agentInfoService.addEducationScore(agent.getAgentCode().longValue(), agent.getName(), cityCode,cityName, education, ShiroUtils.getSessionUser().getId().intValue());
				agentInfoService.addExperienceScore(agent.getAgentCode().longValue(), agent.getName(), cityCode, cityName, workyear, ShiroUtils.getSessionUser().getId().intValue());
				
				//IM激活 
				/* start */
				try {
					UserPo po = userService.getById(userId);
					EasemobUserPo easemobUser = new EasemobUserPo();
					easemobUser.setUserName(po.getUsername());
					easemobUser.setPassWord(po.getImPassword());
					easemobUser.setNickName(po.getRealname());
					Object res = easemobUserService
							.createNewEasemobUserSingle(easemobUser);
					if (StringUtils.isNotEmpty(po.getRealname()) 
							&& StringUtils.isNotEmpty(result.toString()) 
							&& res.toString().indexOf(po.getUsername()) > -1) {
						easemobUserService
							.modifyEasemobUserNickNameWithAdminToken(
									easemobUser);
						po.setMarkFlag(1);
					}
					userService.updateDynamic(po);
				} catch (Exception e) {
					LOGGER.error("经纪人IM激活失败",e);
				}
				
				/* start */
				result.setStatusCode(StatusCode.SUCCESS);
				result.setMessage("提交成功，等待审核");

			} catch (Exception e) {
				LOGGER.error("添加用户异常", e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}
		}
		
		return result;
	}
	/**
	 * 经纪人信息页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("agentInfo")
    public ModelAndView agentInfo(long id) { 
		ModelAndView mav = new ModelAndView();
		AgentVo agent = agentService.getVoById(id);
		agent.setProvinceName(areaService.findNameByAreaCode(agent.getProvince()));
		agent.setCityName(areaService.findNameByAreaCode(agent.getCity()));
		mav.addObject("agent", agent);

		mav.setViewName("system/agent/agent_info");
		return mav;
	}
	/**
	 * 经纪人编辑页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("edit")
    public ModelAndView edit(long id) { 
		ModelAndView mav = new ModelAndView();
		AgentVo agent = agentService.getVoById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;


		try {
			firstAreaList = areaService.listBy(map);

		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		List<CompanyPo> companyList = null;
		try {
			companyList = companyService.listBy(null);
		} catch (Exception e) {
			LOGGER.error("经纪公司查询异常", e);
		}
		mav.addObject("companyList", companyList);
		agent.setProvinceName(areaService.findNameByAreaCode(agent.getProvince()));
		agent.setCityName(areaService.findNameByAreaCode(agent.getCity()));
		mav.addObject("firstAreaList", firstAreaList);

		mav.addObject("agent", agent);

		mav.setViewName("system/agent/agent_edit");
		return mav;
	}
	/**
	 * 经纪人编辑提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateAgent")
	@ResponseBody
    public AjaxResultVo updateAgent(AgentPo agent) { 
		AjaxResultVo result = new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", agent.getTellPhone());
		UserPo u = userService.getBy(map);
		if(u!=null&&!agent.getUserId().equals(u.getId())){
			result.setStatusCode(StatusCode.PHONE_EXIST);
			result.setMessage("手机号已注册");
			return result;
		}else{
			try {
				UserPo user = new UserPo();
				user.setRealname(agent.getName());
				user.setId(agent.getUserId());
				userService.updateDynamic(user);
				
				agent.setUpdateTime(new Date());
				agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
				if(agent.getAuditStatus()!=AgentStatus.AUDIT_SUCCESS.getCode()){
					agent.setAuditStatus(AgentStatus.UN_AUDIT.getCode());
				}
				agentService.updateDynamic(agent);
				
				//经纪人评价
				Long cityCode = null;
				String cityName = null;
				if(agent.getCity()!=null&&!agent.getCity().equals("")){
					Map<String,Object> map1 = new HashMap<String,Object>();
					map1.put("areaCode", agent.getCity());
					AreaPo a = areaService.getBy(map1);
					cityCode = Long.parseLong(agent.getCity());
					cityName = a.getName();
				}
				int education = 0;
				if(agent.getEducation().equals("本科")||agent.getEducation().equals("研究生及以上")){
					education = 1;
				}else if(agent.getEducation().equals("大专")){
					education = 2;
				}else{
					education = 3;
				}
				int workyear = 0;
				if(agent.getWorkyears()>=3){
					workyear = 1;
				}else{
					workyear = 2;
				}
				//经纪人评价体系
				agentInfoService.addEducationScore(agent.getAgentCode().longValue(), agent.getName(), cityCode,cityName, education, ShiroUtils.getSessionUser().getId().intValue());
				agentInfoService.addExperienceScore(agent.getAgentCode().longValue(), agent.getName(), cityCode, cityName, workyear, ShiroUtils.getSessionUser().getId().intValue());
				//新房solr
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("agentIds", agent.getUserId());
				newHouseDirectoryAuthService.saveNewHouseToSolrByAgentId(map1);
				
				
				result.setStatusCode(StatusCode.SUCCESS);

			} catch (Exception e) {
				LOGGER.error("编辑用户异常", e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}
		}
		
		return result;
	}

	/**
	 * 未认证看经纪人列表页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("unapply")
    public ModelAndView unapply(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/agent/unapply");
		return mav;
	}
	/**
	 * 未认证看经纪人数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("unApplyDataList")
	@ResponseBody
	public PageDateTable<?> unApplydataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("auditStatus", AgentStatus.UN_APPLY.getCode());
		requestMap.put("sortCreateTime", 1);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentService.listNewPage(pageParam, requestMap);
			
		} catch (Exception e) {
			LOGGER.error("用户账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	/**
	 * 未认证看经纪人列表页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("notAuditedList")
    public ModelAndView notAuditList(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		mav.addObject("firstAreaList", firstAreaList);
		mav.setViewName("system/agent/notAuditedList");
		return mav;
	}
    /**
	 * 待审核经纪人数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("notAuditDataList")
	@ResponseBody
	public PageDateTable<?> notAuditDataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("auditStatus", AgentStatus.UN_AUDIT.getCode());
		requestMap.put("sortApplyTime", 1);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentService.listNewPage(pageParam, requestMap);

			
		} catch (Exception e) {
			LOGGER.error("用户账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	/**
	 * 删除agent
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteAgent")
	@ResponseBody
    public AjaxResultVo deleteAgent(String ids) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			agentService.deleteAgent(ids);
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			houseNewsService.updateDeleteByCreateUserIds(idlist,DeleteStatusEnum.DEL.getValue());
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("删除用户异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
					
		return result;
	}
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("resetPassword")
	@ResponseBody
    public AjaxResultVo resetPassword(String id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			agentService.updateResetPwdMany(id);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("重置密码异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 经纪人审核页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("audit")
    public ModelAndView audit(long id) { 
		ModelAndView mav = new ModelAndView();
		AgentVo agent = agentService.getVoById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		agent.setProvinceName(areaService.findNameByAreaCode(agent.getProvince()));
		agent.setCityName(areaService.findNameByAreaCode(agent.getCity()));
		mav.addObject("firstAreaList", firstAreaList);
		mav.addObject("agent", agent);
		mav.setViewName("system/agent/agent_audit");
		return mav;
	}
	/**
	 * 经纪人审核提交页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("auditAgent")
	@ResponseBody
    public AjaxResultVo auditAgent(String id,String status,String msg) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			agentService.updateAuditMany(id,msg,status,ShiroUtils.getSessionUser().getId().intValue());
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("重置密码异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	/**
	 * 经纪人排序页面
	 */
	@RequestMapping("agentSort")
	public ModelAndView agentSort(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/agent/agent_sort");
		List<AgentPo> sortList = agentService.selectSort();

		mav.addObject("sortList", sortList);
		return mav;
	}
	/**
	 * 经纪人排序更换
	 */
	@RequestMapping("replace")
	@ResponseBody
	public AjaxResultVo replace(long id, long idReplace,int sort){
		AjaxResultVo result = new AjaxResultVo();
		try {
			
			AgentPo a = new AgentPo();
			a.setId(id);
			a.setSort(AgentStatus.SORT_DEFAULT.getCode());
			a.setUpdateTime(new Date());
			a.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			agentService.updateDynamic(a);
			

			a.setId(idReplace);
			a.setSort(sort);
			a.setUpdateTime(new Date());
			a.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			agentService.updateDynamic(a);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("替换异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 经纪人排序更换
	 */
	@RequestMapping("addSort")
	@ResponseBody
	public AjaxResultVo addSort(long idReplace,int sort){
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentPo agent = new AgentPo();

			
			agent.setId(idReplace);
			agent.setSort(sort);
			agent.setUpdateTime(new Date());
			agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			agentService.updateDynamic(agent);
				
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("添加异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
			
		return result;
	}
	
	/**
	 * 经纪人排序取消
	 */
	@RequestMapping("cancleSort")
	@ResponseBody
	public AjaxResultVo cancleSort(long agentId){
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentPo agent = new AgentPo();

			
			agent.setId(agentId);
			agent.setSort(AgentStatus.SORT_DEFAULT.getCode());
			agent.setUpdateTime(new Date());
			agent.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			agentService.updateDynamic(agent);
				
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("添加异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
			
		return result;
	}
	/**
	 * 经纪人排序拖动
	 */
	@RequestMapping("changeMore")
	@ResponseBody
	public AjaxResultVo changeMore(@RequestBody List<AgentSortVo> list){
		AjaxResultVo result = new AjaxResultVo();
		try {
			int flag = 1;
			Map<String,Object> map = new HashMap<String,Object>();
			List<Long> idList = new ArrayList<Long>();
			for(AgentSortVo item :list){
				if(String.valueOf(item.getId())!=null){
					if(item.getStart()==1){
						AgentPo a = new AgentPo();
						a.setId(item.getId());
						a.setSort(item.getNewSort());
						agentService.updateDynamic(a);
						if(item.getNewSort()>item.getOldSort()){
							flag = 1; //下移
						}else{
							flag = 2;//上移
						}
					}else{
						idList.add(item.getId());
					}
				}
				
				
			}
			map.put("list",idList);
			map.put("flag",flag);
			agentService.updateSort(map);

			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		

		return result;
	}
	/**
	 * 经纪人排序查找
	 */
	@RequestMapping("search")
	@ResponseBody
	public AjaxResultVo changeMore(String name){
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", name);
			map.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
			map.put("sort", AgentStatus.SORT_DEFAULT.getCode());
			List<AgentPo> agentList = agentService.selectByName(map);

			result.setData(agentList);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		

		return result;
	}
	
	/**
	 *下拉模糊查新
	 * @param 
	 * @return
	 */
	@RequestMapping("getAgent")
	@ResponseBody
	public AjaxResultVo getDic(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("preName", name);
				map.put("delFlag", DeleteStatusEnum.NDEL.getValue());
				List<AgentPo> list = agentService.listNewBy(map);
				for (AgentPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getName());
					vo.setCode(po.getAgentCode().toString());
					vo.setPhone(po.getTellPhone());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	/**
	 *下拉模糊查新[id : name]
	 * @param 
	 * @return
	 */
	@RequestMapping("getAgentDic")
	@ResponseBody
	public AjaxResultVo getAgentDic(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
				map.put("name", name);
				List<AgentPo> list = agentService.getAgent(map);
				for (AgentPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getUserId());
					vo.setText(po.getAgentCode()+"--"+po.getName()+"--"+po.getProvince()+"--"+po.getTellPhone());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	/**
	 *下拉模糊查新[userId : name]
	 * @param 
	 * @return
	 */
	@RequestMapping("getAgentDicByUserId")
	@ResponseBody
	public AjaxResultVo getAgentDicByUserId(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
				map.put("name", name);
				List<AgentPo> list = agentService.getAgent(map);
				for (AgentPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getUserId());
					vo.setText(po.getName());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	/**
	 * 新房楼盘绑定经纪人下拉模糊查新[userId : name]
	 * @param 
	 * @return
	 */
	@RequestMapping("getLikeAgent")
	@ResponseBody
	public AjaxResultVo getLikeAgent(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
				map.put("name", name);
				List<AgentVo> list = agentService.getLikeAgent(map);
				for (AgentVo agentVo : list) {
					SelectVo vo = new SelectVo();
					vo.setId(agentVo.getUserId());
					
					String text = "";
					if (agentVo.getAgentCode() != null) 
						text = agentVo.getAgentCode().toString();
					if (StringUtils.isNotEmpty(agentVo.getName())) {
						if (StringUtils.isNotEmpty(text)) text = text + "，";
						text = text + agentVo.getName();
					}
					if (StringUtils.isNotEmpty(agentVo.getCompanyName())) {
						if (StringUtils.isNotEmpty(text)) text = text + "，";
						text = text + agentVo.getCompanyName();
					}
					if (StringUtils.isNotEmpty(agentVo.getTellPhone())) {
						if (StringUtils.isNotEmpty(text)) text = text + "，";
						text = text + agentVo.getTellPhone();
					}
					vo.setText(text);
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
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

}
